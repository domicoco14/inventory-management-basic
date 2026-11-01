package com.inventory.frontend.ui.components;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class FormatTest {

    @Test
    void moneyUsesNairaAndThousandsSeparators() {
        assertThat(Format.money(new BigDecimal("250900.00"))).isEqualTo("₦250,900");
    }

    @Test
    void negativeMoneyKeepsTheSignInFront() {
        assertThat(Format.money(new BigDecimal("-1200"))).isEqualTo("-₦1,200");
    }

    @Test
    void percentAlwaysShowsSignAndOneDecimal() {
        assertThat(Format.percent(new BigDecimal("12"))).isEqualTo("+12.0%");
        assertThat(Format.percent(new BigDecimal("-5.55"))).isEqualTo("-5.6%");
        assertThat(Format.percent(BigDecimal.ZERO)).isEqualTo("0.0%");
    }

    @Test
    void numbersAreGrouped() {
        assertThat(Format.number(1234567L)).isEqualTo("1,234,567");
    }
}
