package com.inventory.frontend.backend;

import com.inventory.frontend.backend.dto.DailyOrdersVsSales;
import com.inventory.frontend.backend.dto.TodaySummary;
import com.inventory.frontend.backend.dto.TopSellingProduct;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.function.Supplier;

/** The only place the UI talks to the reporting backend. Every method mirrors one backend endpoint. */
@Service
public class BackendClient {

    private final RestClient http;

    public BackendClient(RestClient backendRestClient) {
        this.http = backendRestClient;
    }

    public TodaySummary todaySummary() {
        return call("today summary", () -> http.get().uri("/api/reports/today").retrieve().body(TodaySummary.class));
    }

    public List<DailyOrdersVsSales> ordersVsSales(int days) {
        return call("orders vs sales", () -> http.get()
                .uri("/api/reports/orders-vs-sales?days={days}", days)
                .retrieve().body(new ParameterizedTypeReference<List<DailyOrdersVsSales>>() { }));
    }

    public List<TopSellingProduct> topSelling(int limit) {
        return call("top selling products", () -> http.get()
                .uri("/api/reports/top-selling?limit={limit}", limit)
                .retrieve().body(new ParameterizedTypeReference<List<TopSellingProduct>>() { }));
    }

    private static <T> T call(String what, Supplier<T> request) {
        try {
            return request.get();
        } catch (RestClientException e) {
            throw new BackendUnavailableException("Could not load " + what + " from the reporting backend", e);
        }
    }
}
