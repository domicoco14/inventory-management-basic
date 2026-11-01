package com.inventory.frontend.ui;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

/** Stand-in for a sidebar screen that belongs to another group and is not built yet. */
abstract class PlaceholderView extends Div {

    PlaceholderView(String title, VaadinIcon icon) {
        addClassName("page");
        Div bubble = new Div(new Icon(icon));
        bubble.addClassName("placeholder-icon");
        Paragraph text = new Paragraph("This screen has not been built yet.");
        Div panel = new Div(bubble, new H2(title), text);
        panel.addClassName("placeholder");
        add(panel);
    }
}
