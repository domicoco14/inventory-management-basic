package com.inventory.frontend.ui.components;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public final class Format {

    private Format() {
    }

    public static String number(BigDecimal value) {
        return new DecimalFormat("#,##0", DecimalFormatSymbols.getInstance(Locale.US)).format(value);
    }

    public static String number(long value) {
        return number(BigDecimal.valueOf(value));
    }

    public static String money(BigDecimal value) {
        String formatted = number(value.abs());
        return (value.signum() < 0 ? "-" : "") + "₦" + formatted;
    }

    /** Percentage with an explicit sign, one decimal: +12.0%, -5.5%, 0.0%. */
    public static String percent(BigDecimal value) {
        BigDecimal rounded = value.setScale(1, RoundingMode.HALF_UP);
        return (rounded.signum() > 0 ? "+" : "") + rounded.toPlainString() + "%";
    }
}
