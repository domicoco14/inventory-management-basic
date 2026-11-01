package com.inventory.frontend.ui.components;

import com.inventory.frontend.backend.dto.DailyOrdersVsSales;
import com.inventory.frontend.backend.dto.TopSellingProduct;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ChartsTest {

    private static int count(String text, String needle) {
        return text.split(java.util.regex.Pattern.quote(needle), -1).length - 1;
    }

    @Test
    void barsAreDrawnOnlyForNonZeroValues() {
        String html = Charts.ordersVsSales(List.of(
                new DailyOrdersVsSales(LocalDate.of(2026, 10, 27), 2, new BigDecimal("200")),
                new DailyOrdersVsSales(LocalDate.of(2026, 10, 28), 0, BigDecimal.ZERO))).getInnerHtml();

        assertThat(count(html, "<rect")).isEqualTo(2);
        assertThat(html).contains(Charts.ORDERS_COLOR).contains(Charts.SALES_COLOR);
        assertThat(html).contains("2 orders");
    }

    @Test
    void noOrdersShowsAnEmptyMessageInsteadOfAnEmptyChart() {
        String html = Charts.ordersVsSales(List.of()).getInnerHtml();

        assertThat(html).contains("No orders in this period yet");
        assertThat(html).doesNotContain("<svg");
    }

    @Test
    void donutHasOneSlicePerProductAndALegend() {
        String html = Charts.topSelling(List.of(
                new TopSellingProduct(1L, "A", "Mouse", 30L, new BigDecimal("3000")),
                new TopSellingProduct(2L, "B", "Cable", 10L, new BigDecimal("1000")))).getInnerHtml();

        // two slices plus the grey track
        assertThat(count(html, "<circle")).isEqualTo(3);
        assertThat(count(html, "<li>")).isEqualTo(2);
        assertThat(html).contains("75%").contains("25%").contains("40");
    }

    @Test
    void noSalesShowsAnEmptyMessage() {
        assertThat(Charts.topSelling(List.of()).getInnerHtml()).contains("No completed sales yet");
    }

    @Test
    void productNamesAreEscaped() {
        String html = Charts.topSelling(List.of(
                new TopSellingProduct(1L, "A", "<script>alert(1)</script>", 1L, BigDecimal.TEN))).getInnerHtml();

        assertThat(html).doesNotContain("<script>");
        assertThat(html).contains("&lt;script&gt;");
    }
}
