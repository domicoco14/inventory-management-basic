package com.inventory.frontend.ui;

import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "orders", layout = MainLayout.class)
@PageTitle("Orders | Inventory")
public class OrdersView extends PlaceholderView {

    public OrdersView() {
        super("Orders", VaadinIcon.CLIPBOARD_TEXT);
    }
}
