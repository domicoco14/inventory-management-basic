package com.inventory.frontend.backend;

import com.inventory.frontend.backend.dto.DailyOrdersVsSales;
import com.inventory.frontend.backend.dto.TodaySummary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/** Checks the client reads the exact JSON shapes the backend sends. */
class BackendClientTest {

    private MockRestServiceServer server;
    private BackendClient client;

    @BeforeEach
    void setUp() {
        RestClient.Builder builder = RestClient.builder().baseUrl("http://backend");
        server = MockRestServiceServer.bindTo(builder).build();
        client = new BackendClient(builder.build());
    }

    @Test
    void readsTodaySummary() {
        server.expect(requestTo("http://backend/api/reports/today")).andRespond(withSuccess("""
                {"sales":{"value":29,"changePercent":93.3},
                 "totalOrders":{"value":5,"changePercent":25.0},
                 "revenue":{"value":250900.00,"changePercent":57.9},
                 "customers":{"value":4,"changePercent":-33.3}}""", MediaType.APPLICATION_JSON));

        TodaySummary summary = client.todaySummary();

        assertThat(summary.sales().value()).isEqualByComparingTo("29");
        assertThat(summary.revenue().value()).isEqualByComparingTo("250900");
        assertThat(summary.customers().changePercent()).isEqualByComparingTo("-33.3");
    }

    @Test
    void readsOrdersVsSalesWithDates() {
        server.expect(requestTo("http://backend/api/reports/orders-vs-sales?days=2")).andRespond(withSuccess("""
                [{"date":"2026-10-27","orders":3,"sales":1500.50},
                 {"date":"2026-10-28","orders":0,"sales":0}]""", MediaType.APPLICATION_JSON));

        List<DailyOrdersVsSales> days = client.ordersVsSales(2);

        assertThat(days).hasSize(2);
        assertThat(days.get(0).date()).isEqualTo(LocalDate.of(2026, 10, 27));
        assertThat(days.get(0).sales()).isEqualByComparingTo(new BigDecimal("1500.50"));
    }

    @Test
    void aFailingBackendBecomesBackendUnavailable() {
        server.expect(requestTo("http://backend/api/reports/top-selling?limit=5"))
                .andRespond(withStatus(HttpStatus.INTERNAL_SERVER_ERROR));

        assertThatThrownBy(() -> client.topSelling(5)).isInstanceOf(BackendUnavailableException.class);
    }

    @Test
    void anUnreachableBackendBecomesBackendUnavailable() {
        BackendClient offline = new BackendClient(RestClient.builder().baseUrl("http://localhost:1").build());

        assertThatThrownBy(offline::todaySummary).isInstanceOf(BackendUnavailableException.class);
    }
}
