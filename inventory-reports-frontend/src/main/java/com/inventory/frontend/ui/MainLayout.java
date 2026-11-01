package com.inventory.frontend.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Nav;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;

import java.util.List;

/**
 * The frame from the Figma design: a header with search, bell and profile, and a narrow dark sidebar with
 * icon-over-label items. The page content goes in the grey area to the right.
 */
public class MainLayout extends Div implements RouterLayout {

    private record NavItem(String label, VaadinIcon icon, Class<? extends Component> target) {
    }

    private final Div content = new Div();

    public MainLayout() {
        addClassName("app");
        content.addClassName("content");

        Div body = new Div(sidebar(), content);
        body.addClassName("app-body");
        add(header(), body);
    }

    private Div header() {
        Button search = iconButton(VaadinIcon.SEARCH, "Search");
        search.addClassName("search-button");
        Button bell = iconButton(VaadinIcon.BELL, "Notifications");

        Span avatar = new Span(new Icon(VaadinIcon.USER));
        avatar.addClassName("avatar");

        Div actions = new Div(search, bell, avatar);
        actions.addClassName("header-actions");

        Div header = new Div(actions);
        header.addClassName("app-header");
        return header;
    }

    private Nav sidebar() {
        Nav nav = new Nav();
        nav.addClassName("sidebar");
        List<NavItem> items = List.of(
                new NavItem("In Stock", VaadinIcon.CUBES, InStockView.class),
                new NavItem("Products", VaadinIcon.PACKAGE, ProductsView.class),
                new NavItem("Sales", VaadinIcon.CHART, ReportStatisticsView.class),
                new NavItem("Orders", VaadinIcon.CLIPBOARD_TEXT, OrdersView.class),
                new NavItem("Users", VaadinIcon.USERS, UsersView.class));
        for (NavItem item : items) {
            RouterLink link = new RouterLink(item.target());
            link.add(new Icon(item.icon()), new Span(item.label()));
            link.addClassName("nav-item");
            link.setHighlightCondition(HighlightConditions.sameLocation());
            nav.add(link);
        }
        return nav;
    }

    private static Button iconButton(VaadinIcon icon, String label) {
        Button button = new Button(new Icon(icon));
        button.addClassName("icon-button");
        button.getElement().setAttribute("aria-label", label);
        return button;
    }

    @Override
    public void showRouterLayoutContent(HasElement routeContent) {
        content.removeAll();
        if (routeContent != null) {
            content.getElement().appendChild(routeContent.getElement());
        }
    }
}
