/*
 * qualinsight-plugins-sonarqube-smell
 * Copyright (c) 2015, QualInsight
 * http://www.qualinsight.com/
 *
 * This program is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program. If not, you can retrieve a copy
 * from <http://www.gnu.org/licenses/>.
 */
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
