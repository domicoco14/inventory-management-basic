package com.inventory.frontend.ui;

import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "users", layout = MainLayout.class)
@PageTitle("Users | Inventory")
public class UsersView extends PlaceholderView {

    public UsersView() {
        super("Users", VaadinIcon.USERS);
    }
}
