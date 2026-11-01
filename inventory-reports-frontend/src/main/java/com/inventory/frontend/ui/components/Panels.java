package com.inventory.frontend.ui.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

/** Small building blocks shared by the screens. */
public final class Panels {

    private Panels() {
    }

    /** A white rounded card with a title row; extra header content (tabs, links) goes on the right. */
    public static Div card(String title, Component body, Component... headerExtras) {
        Div card = new Div();
        card.addClassName("card");
        H3 heading = new H3(title);
        Div header = new Div(heading);
        header.addClassName("card-header");
        for (Component extra : headerExtras) {
            header.add(extra);
        }
        card.add(header, body);
        return card;
    }

    /** A coloured dot with a label, for chart legends. */
    public static Span legendItem(String colour, String label) {
        Span dot = new Span();
        dot.addClassName("dot");
        dot.getStyle().set("background", colour);
        Span item = new Span(dot, new Span(label));
        item.addClassName("legend-item");
        return item;
    }

    /** The big title and grey subtitle at the top of a screen. */
    public static Div pageHeading(String title, String subtitle) {
        Paragraph sub = new Paragraph(subtitle);
        sub.addClassName("page-subtitle");
        Div heading = new Div(new H1(title), sub);
        heading.addClassName("page-heading");
        return heading;
    }

    /** Shown in place of a screen's content when the reporting backend cannot be reached. */
    public static Div backendDown() {
        Icon icon = new Icon(VaadinIcon.EXCLAMATION_CIRCLE);
        Paragraph title = new Paragraph("The reporting service is not reachable");
        title.addClassName("error-title");
        Paragraph hint = new Paragraph("Start inventory-reports-backend and try again.");
        Button retry = new Button("Try again", new Icon(VaadinIcon.REFRESH),
                e -> UI.getCurrent().getPage().reload());
        retry.addClassName("btn-primary");
        Div panel = new Div(icon, title, hint, retry);
        panel.addClassName("error-panel");
        return panel;
    }
}
