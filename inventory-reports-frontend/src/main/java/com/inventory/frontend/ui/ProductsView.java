package com.inventory.frontend.ui;

import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "products", layout = MainLayout.class)
@PageTitle("Products | Inventory")
public class ProductsView extends PlaceholderView {

    public ProductsView() {
        super("Products", VaadinIcon.PACKAGE);
    }
}
