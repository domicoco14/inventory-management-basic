package com.inventory.frontend.ui;

import com.inventory.frontend.backend.BackendClient;
import com.inventory.frontend.backend.BackendUnavailableException;
import com.inventory.frontend.backend.dto.TodaySummary;
import com.inventory.frontend.ui.components.Charts;
import com.inventory.frontend.ui.components.Format;
import com.inventory.frontend.ui.components.KpiCard;
import com.inventory.frontend.ui.components.KpiCard.Tone;
import com.inventory.frontend.ui.components.Panels;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Report statistics | Inventory")
public class ReportStatisticsView extends Div {

    private static final int[] RANGES = {7, 14, 30};

    private final BackendClient backend;
    private final Div chartHolder = new Div();

    public ReportStatisticsView(BackendClient backend) {
        this.backend = backend;
        addClassName("page");
        add(Panels.pageHeading("Report statistics", "Today compared with yesterday"));
        try {
            TodaySummary today = backend.todaySummary();
            add(kpis(today), charts());
        } catch (BackendUnavailableException e) {
            add(Panels.backendDown());
        }
    }

    private static Div kpis(TodaySummary t) {
        Div row = new Div(
                new KpiCard("Today's sales", t.sales(), Format.number(t.sales().value()) + " units",
                        VaadinIcon.CART, Tone.BLUE),
                new KpiCard("Today's total orders", t.totalOrders(), Format.number(t.totalOrders().value()),
                        VaadinIcon.CLIPBOARD_TEXT, Tone.VIOLET),
                new KpiCard("Today's revenue", t.revenue(), Format.money(t.revenue().value()),
                        VaadinIcon.WALLET, Tone.GREEN),
                new KpiCard("Today's customers", t.customers(), Format.number(t.customers().value()),
                        VaadinIcon.USERS, Tone.AMBER));
        row.addClassName("kpi-grid");
        return row;
    }

    private Div charts() {
        Tabs ranges = new Tabs(new Tab("7 days"), new Tab("14 days"), new Tab("30 days"));
        ranges.addClassName("range-tabs");
        ranges.addSelectedChangeListener(e -> showOrdersVsSales(RANGES[Math.max(0, ranges.getSelectedIndex())]));

        Div legend = new Div(Panels.legendItem(Charts.ORDERS_COLOR, "Orders"),
                Panels.legendItem(Charts.SALES_COLOR, "Sales"));
        legend.addClassName("legend-row");

        showOrdersVsSales(RANGES[0]);

        Div body = new Div(ranges, chartHolder);
        Div grid = new Div(
                Panels.card("Orders vs Sales", body, legend),
                Panels.card("Top selling products", topSellingBody()));
        grid.addClassNames("two-column", "wide-left");
        return grid;
    }

    private void showOrdersVsSales(int days) {
        chartHolder.removeAll();
        try {
            chartHolder.add(Charts.ordersVsSales(backend.ordersVsSales(days)));
        } catch (BackendUnavailableException e) {
            chartHolder.add(Panels.backendDown());
        }
    }

    private Div topSellingBody() {
        Div body = new Div();
        try {
            body.add(Charts.topSelling(backend.topSelling(5)));
        } catch (BackendUnavailableException e) {
            body.add(Panels.backendDown());
        }
        return body;
    }
}
