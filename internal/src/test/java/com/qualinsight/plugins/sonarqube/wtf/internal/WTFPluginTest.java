/*
 * qualinsight-plugins-sonarqube-wtf
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
package com.qualinsight.plugins.sonarqube.wtf.internal;

import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import com.qualinsight.plugins.sonarqube.wtf.internal.extension.WTFChecksRegistrar;
import com.qualinsight.plugins.sonarqube.wtf.internal.extension.WTFMeasuresDecorator;
import com.qualinsight.plugins.sonarqube.wtf.internal.extension.WTFMeasuresSensor;
import com.qualinsight.plugins.sonarqube.wtf.internal.extension.WTFMetrics;
import com.qualinsight.plugins.sonarqube.wtf.internal.extension.WTFRulesDefinition;
import com.qualinsight.plugins.sonarqube.wtf.internal.extension.WTFWidget;

public class WTFPluginTest {

    private static final int EXPECTED_EXTENSIONS_COUNT = 6;

    @SuppressWarnings("unchecked")
    @Test
    public void getExtensions_should_return_expectedExtensions() {
        final WTFPlugin sut = new WTFPlugin();
        @SuppressWarnings("rawtypes")
        final List actualExtensions = sut.getExtensions();
        final SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actualExtensions)
            .hasSize(EXPECTED_EXTENSIONS_COUNT);
        softly.assertThat(actualExtensions)
            .contains(WTFChecksRegistrar.class);
        softly.assertThat(actualExtensions)
            .contains(WTFMetrics.class);
        softly.assertThat(actualExtensions)
            .contains(WTFWidget.class);
        softly.assertThat(actualExtensions)
            .contains(WTFRulesDefinition.class);
        softly.assertThat(actualExtensions)
            .contains(WTFMeasuresSensor.class);
        softly.assertThat(actualExtensions)
            .contains(WTFMeasuresDecorator.class);
        softly.assertAll();
    }

}
