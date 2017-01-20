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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.sonar.api.Plugin.Context;
import org.sonar.api.utils.Version;
import com.qualinsight.plugins.sonarqube.smell.plugin.extension.SmellChecksRegistrar;
import com.qualinsight.plugins.sonarqube.smell.plugin.extension.SmellCountByTypeMeasuresComputer;
import com.qualinsight.plugins.sonarqube.smell.plugin.extension.SmellCountTotalMeasureComputer;
import com.qualinsight.plugins.sonarqube.smell.plugin.extension.SmellDebtComputer;
import com.qualinsight.plugins.sonarqube.smell.plugin.extension.SmellMeasuresSensor;
import com.qualinsight.plugins.sonarqube.smell.plugin.extension.SmellMetrics;
import com.qualinsight.plugins.sonarqube.smell.plugin.extension.SmellRulesDefinition;
import com.qualinsight.plugins.sonarqube.smell.plugin.extension.SmellWidget;

@RunWith(MockitoJUnitRunner.class)
public class SmellPluginTest {

    private static final Version NO_MORE_WIDGETS_VERSION = Version.create(6, 1);

    private static final Version STILL_WIDGETS_VERSION = Version.create(6, 0);

    @Mock
    private Context context;

    @Test
    public void define_should_addExpectedExtensions_when_usingSQSmallerThan61() {
        Mockito.when(this.context.getSonarQubeVersion())
            .thenReturn(STILL_WIDGETS_VERSION);
        final SmellPlugin sut = new SmellPlugin();
        sut.define(this.context);
        verify(this.context).addExtension(Mockito.same(SmellChecksRegistrar.class));
        verify(this.context).addExtension(Mockito.same(SmellRulesDefinition.class));
        verify(this.context).addExtension(Mockito.same(SmellMetrics.class));
        verify(this.context).addExtension(Mockito.same(SmellMeasuresSensor.class));
        verify(this.context).addExtension(Mockito.same(SmellDebtComputer.class));
        verify(this.context).addExtension(Mockito.same(SmellCountByTypeMeasuresComputer.class));
        verify(this.context).addExtension(Mockito.same(SmellCountTotalMeasureComputer.class));
        verify(this.context).addExtension(Mockito.same(SmellWidget.class));
        verify(this.context).getSonarQubeVersion();
        verifyNoMoreInteractions(this.context);
    }

    @Test
    public void define_should_addExpectedExtensions_when_usingSQGreaterOrEqualTo61() {
        Mockito.when(this.context.getSonarQubeVersion())
            .thenReturn(NO_MORE_WIDGETS_VERSION);
        final SmellPlugin sut = new SmellPlugin();
        sut.define(this.context);
        verify(this.context).addExtension(Mockito.same(SmellChecksRegistrar.class));
        verify(this.context).addExtension(Mockito.same(SmellRulesDefinition.class));
        verify(this.context).addExtension(Mockito.same(SmellMetrics.class));
        verify(this.context).addExtension(Mockito.same(SmellMeasuresSensor.class));
        verify(this.context).addExtension(Mockito.same(SmellDebtComputer.class));
        verify(this.context).addExtension(Mockito.same(SmellCountByTypeMeasuresComputer.class));
        verify(this.context).addExtension(Mockito.same(SmellCountTotalMeasureComputer.class));
        verify(this.context).getSonarQubeVersion();
        verifyNoMoreInteractions(this.context);
    }

}
