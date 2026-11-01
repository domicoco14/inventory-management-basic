package com.inventory.frontend.ui.components;

import com.inventory.frontend.backend.dto.DailyOrdersVsSales;
import com.inventory.frontend.backend.dto.TopSellingProduct;
import com.vaadin.flow.component.Html;
import org.springframework.web.util.HtmlUtils;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

/** Charts drawn as inline SVG, so the UI needs no chart library and every value is readable in tests. */
public final class Charts {

    public static final String ORDERS_COLOR = "#FFD84D";
    public static final String SALES_COLOR = "#6272FF";
    static final List<String> PALETTE = List.of("#6272FF", "#FFD84D", "#2EC4B6", "#FF7A59", "#A66CFF");

    private static final DateTimeFormatter DAY = DateTimeFormatter.ofPattern("EEE d", Locale.ENGLISH);
    private static final DateTimeFormatter DAY_LONG = DateTimeFormatter.ofPattern("EEEE d MMM", Locale.ENGLISH);

    private Charts() {
    }

    /**
     * Paired bars per day: orders placed (yellow) and completed sales value (blue). The two series have different
     * units, so each is scaled to its own maximum; hover a bar for the real figure.
     */
    public static Html ordersVsSales(List<DailyOrdersVsSales> days) {
        if (days.isEmpty()) {
            return empty("No orders in this period yet");
        }
        double width = 760, height = 300, padX = 8, padTop = 16, padBottom = 34;
        double plotWidth = width - 2 * padX;
        double plotHeight = height - padTop - padBottom;
        double maxOrders = Math.max(1, days.stream().mapToLong(DailyOrdersVsSales::orders).max().orElse(1));
        double maxSales = Math.max(1, days.stream().map(DailyOrdersVsSales::sales)
                .mapToDouble(BigDecimal::doubleValue).max().orElse(1));
        double groupWidth = plotWidth / days.size();
        double barWidth = Math.min(34, groupWidth * 0.3);
        double gap = 4;
        int labelEvery = (int) Math.ceil(days.size() / 10.0);

        StringBuilder svg = new StringBuilder();
        svg.append(String.format(Locale.ROOT,
                "<svg class=\"chart-svg\" viewBox=\"0 0 %.0f %.0f\" role=\"img\" aria-label=\"Orders versus sales per day\">",
                width, height));
        for (int i = 0; i <= 4; i++) {
            double y = padTop + plotHeight * i / 4;
            svg.append(String.format(Locale.ROOT,
                    "<line class=\"grid\" x1=\"%.1f\" x2=\"%.1f\" y1=\"%.1f\" y2=\"%.1f\"/>", padX, width - padX, y, y));
        }
        for (int i = 0; i < days.size(); i++) {
            DailyOrdersVsSales day = days.get(i);
            double centre = padX + groupWidth * i + groupWidth / 2;
            double baseline = padTop + plotHeight;
            String when = HtmlUtils.htmlEscape(day.date().format(DAY_LONG));

            double ordersHeight = plotHeight * day.orders() / maxOrders;
            if (ordersHeight > 0) {
                svg.append(bar(centre - barWidth - gap / 2, baseline - ordersHeight, barWidth, ordersHeight,
                        ORDERS_COLOR, when + ": " + day.orders() + (day.orders() == 1 ? " order" : " orders")));
            }
            double salesHeight = plotHeight * day.sales().doubleValue() / maxSales;
            if (salesHeight > 0) {
                svg.append(bar(centre + gap / 2, baseline - salesHeight, barWidth, salesHeight,
                        SALES_COLOR, when + ": " + HtmlUtils.htmlEscape(Format.money(day.sales())) + " in sales"));
            }
            if (i % labelEvery == 0 || i == days.size() - 1) {
                svg.append(String.format(Locale.ROOT,
                        "<text class=\"axis\" x=\"%.1f\" y=\"%.1f\" text-anchor=\"middle\">%s</text>",
                        centre, height - 10, HtmlUtils.htmlEscape(day.date().format(DAY))));
            }
        }
        svg.append("</svg>");
        return new Html("<div class=\"chart\">" + svg + "</div>");
    }

    /** Donut of revenue share per product, with a legend listing units sold. */
    public static Html topSelling(List<TopSellingProduct> products) {
        double totalRevenue = products.stream().map(TopSellingProduct::revenue).mapToDouble(BigDecimal::doubleValue).sum();
        if (products.isEmpty() || totalRevenue <= 0) {
            return empty("No completed sales yet");
        }
        double radius = 70, circumference = 2 * Math.PI * radius;
        long totalUnits = products.stream().mapToLong(TopSellingProduct::unitsSold).sum();

        StringBuilder svg = new StringBuilder("<svg class=\"donut-svg\" viewBox=\"0 0 200 200\" role=\"img\" "
                + "aria-label=\"Share of revenue by product\">");
        svg.append(String.format(Locale.ROOT,
                "<circle class=\"donut-track\" cx=\"100\" cy=\"100\" r=\"%.1f\" fill=\"none\" stroke-width=\"26\"/>", radius));
        StringBuilder legend = new StringBuilder("<ul class=\"legend\">");
        double offset = 0;
        for (int i = 0; i < products.size(); i++) {
            TopSellingProduct product = products.get(i);
            String colour = PALETTE.get(i % PALETTE.size());
            String name = HtmlUtils.htmlEscape(product.name());
            double share = product.revenue().doubleValue() / totalRevenue;
            double length = Math.max(0, circumference * share - (products.size() > 1 ? 2 : 0));
            svg.append(String.format(Locale.ROOT,
                    "<circle cx=\"100\" cy=\"100\" r=\"%.1f\" fill=\"none\" stroke=\"%s\" stroke-width=\"26\" "
                            + "stroke-dasharray=\"%.2f %.2f\" stroke-dashoffset=\"%.2f\" transform=\"rotate(-90 100 100)\">"
                            + "<title>%s: %s</title></circle>",
                    radius, colour, length, circumference - length, -offset, name,
                    HtmlUtils.htmlEscape(Format.money(product.revenue()))));
            offset += circumference * share;
            legend.append(String.format(Locale.ROOT,
                    "<li><span class=\"dot\" style=\"background:%s\"></span><span class=\"name\">%s</span>"
                            + "<span class=\"share\">%d%%</span></li>",
                    colour, name, Math.round(share * 100)));
        }
        svg.append("<text class=\"donut-total\" x=\"100\" y=\"102\" text-anchor=\"middle\">")
                .append(Format.number(totalUnits)).append("</text>")
                .append("<text class=\"donut-sub\" x=\"100\" y=\"122\" text-anchor=\"middle\">units sold</text></svg>");
        legend.append("</ul>");
        return new Html("<div class=\"donut\">" + svg + legend + "</div>");
    }

    private static String bar(double x, double y, double width, double height, String colour, String tooltip) {
        return String.format(Locale.ROOT,
                "<rect x=\"%.1f\" y=\"%.1f\" width=\"%.1f\" height=\"%.1f\" rx=\"6\" fill=\"%s\"><title>%s</title></rect>",
                x, y, width, height, colour, tooltip);
    }

    private static Html empty(String message) {
        return new Html("<div class=\"chart-empty\">" + HtmlUtils.htmlEscape(message) + "</div>");
    }
}
