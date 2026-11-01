package com.inventory.frontend.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/** GET /api/reports/orders-vs-sales: one point of the bar chart. */
public record DailyOrdersVsSales(LocalDate date, long orders, BigDecimal sales) {
}
