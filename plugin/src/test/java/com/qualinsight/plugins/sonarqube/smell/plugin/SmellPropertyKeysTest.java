package com.qualinsight.plugins.sonarqube.smell.plugin;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class SmellPropertyKeysTest {

    public static final String EXPECTED_WIDGET_TITLE_KEY = "widget.smells.title";

    public static final String EXPECTED_WIDGET_ID_KEY = "widget.smells.id";

    @Test
    public void constant_WIDGET_TITLE_KEY_shouldHave_expectedValue() {
        Assertions.assertThat(SmellPropertyKeys.WIDGET_TITLE_KEY)
            .isEqualTo(EXPECTED_WIDGET_TITLE_KEY);
    }

    @Test
    public void constant_WIDGET_ID_KEY_shouldHave_expectedValue() {
        Assertions.assertThat(SmellPropertyKeys.WIDGET_ID_KEY)
            .isEqualTo(EXPECTED_WIDGET_ID_KEY);
    }

}
