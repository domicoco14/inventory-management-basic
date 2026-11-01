package com.inventory.frontend.backend.dto;

/** GET /api/reports/today: the four cards on Report statistics, each compared with yesterday. */
public record TodaySummary(KpiMetric sales, KpiMetric totalOrders, KpiMetric revenue, KpiMetric customers) {
}
