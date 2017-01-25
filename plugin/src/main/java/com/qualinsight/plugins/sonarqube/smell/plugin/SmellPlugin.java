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

import org.sonar.api.Plugin;
import org.sonar.api.Properties;
import org.sonar.api.Property;
import org.sonar.api.utils.Version;
import com.qualinsight.plugins.sonarqube.smell.plugin.extension.SmellChecksRegistrar;
import com.qualinsight.plugins.sonarqube.smell.plugin.extension.SmellCountByTypeMeasuresComputer;
import com.qualinsight.plugins.sonarqube.smell.plugin.extension.SmellCountTotalMeasureComputer;
import com.qualinsight.plugins.sonarqube.smell.plugin.extension.SmellDebtComputer;
import com.qualinsight.plugins.sonarqube.smell.plugin.extension.SmellMeasuresSensor;
import com.qualinsight.plugins.sonarqube.smell.plugin.extension.SmellMetrics;
import com.qualinsight.plugins.sonarqube.smell.plugin.extension.SmellRulesDefinition;
import com.qualinsight.plugins.sonarqube.smell.plugin.extension.SmellWidget;

/**
 * Core Code Smells SonarPlugin class. It declares all extensions used by the plugin.
 *
 * @author Michel Pawlak
 */
@Properties({
    @Property(key = SmellPropertyKeys.WIDGET_TITLE_KEY, name = "Widget title", defaultValue = "Code Smells")
})
public final class SmellPlugin implements Plugin {

    private static final Version NO_MORE_WIDGETS_VERSION = Version.create(6, 1);

    /**
     * This method is executed at runtime when:
     * <ul>
     * <li>Web Server starts</li>
     * <li>Compute Engine starts</li>
     * <li>Scanner starts</li>
     * </ul>
     *
     * @param context the Context to which extensions have to be added to.
     */
    @Override
    public void define(final Context context) {
        context.addExtension(SmellChecksRegistrar.class);
        context.addExtension(SmellRulesDefinition.class);
        context.addExtension(SmellMetrics.class);
        context.addExtension(SmellMeasuresSensor.class);
        context.addExtension(SmellDebtComputer.class);
        context.addExtension(SmellCountByTypeMeasuresComputer.class);
        context.addExtension(SmellCountTotalMeasureComputer.class);
        if (!context.getSonarQubeVersion()
            .isGreaterThanOrEqual(NO_MORE_WIDGETS_VERSION)) {
            context.addExtension(SmellWidget.class);
        }
    }
}
