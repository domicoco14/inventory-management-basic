package com.inventory.frontend.ui.components;

import com.inventory.frontend.backend.dto.KpiMetric;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

import java.math.BigDecimal;

/** A headline number with an icon bubble and, optionally, a coloured change chip. */
public class KpiCard extends Div {

    public enum Tone { VIOLET, BLUE, AMBER, GREEN, RED }

    public KpiCard(String label, String value, VaadinIcon icon, Tone tone) {
        addClassNames("kpi-card");
        Div bubble = new Div(new Icon(icon));
        bubble.addClassNames("kpi-icon", "tone-" + tone.name().toLowerCase());
        Span name = new Span(label);
        name.addClassName("kpi-label");
        Div top = new Div(bubble, name);
        top.addClassName("kpi-top");

        Span number = new Span(value);
        number.addClassName("kpi-value");
        add(top, number);
    }

    public KpiCard(String label, KpiMetric metric, String formattedValue, VaadinIcon icon, Tone tone) {
        this(label, formattedValue, icon, tone);
        add(changeChip(metric.changePercent()));
    }

    private static Span changeChip(BigDecimal change) {
        boolean down = change.signum() < 0;
        Icon arrow = new Icon(down ? VaadinIcon.ARROW_DOWN : VaadinIcon.ARROW_UP);
        Span text = new Span(Format.percent(change) + " vs yesterday");
        Span chip = new Span(arrow, text);
        chip.addClassNames("kpi-change", down ? "down" : "up");
        return chip;
    }
}
