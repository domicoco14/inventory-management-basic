package com.inventory.frontend.ui;

import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "in-stock", layout = MainLayout.class)
@PageTitle("In Stock | Inventory")
public class InStockView extends PlaceholderView {

    public InStockView() {
        super("In Stock", VaadinIcon.CUBES);
    }
}
