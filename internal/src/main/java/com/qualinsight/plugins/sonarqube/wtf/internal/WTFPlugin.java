/*
 * This file is part of qualinsight-plugins-sonarqube-wtf-internal.
 *
 * qualinsight-plugins-sonarqube-wtf-internal is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * qualinsight-plugins-sonarqube-wtf-internal is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with qualinsight-plugins-sonarqube-wtf-internal.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.qualinsight.plugins.sonarqube.wtf.internal;

import java.util.List;
import org.sonar.api.SonarPlugin;
import com.google.common.collect.ImmutableList;
import com.qualinsight.plugins.sonarqube.wtf.internal.extension.WTFChecksRegistrar;
import com.qualinsight.plugins.sonarqube.wtf.internal.extension.WTFMeasuresDecorator;
import com.qualinsight.plugins.sonarqube.wtf.internal.extension.WTFMeasuresSensor;
import com.qualinsight.plugins.sonarqube.wtf.internal.extension.WTFMetrics;
import com.qualinsight.plugins.sonarqube.wtf.internal.extension.WTFRulesDefinition;
import com.qualinsight.plugins.sonarqube.wtf.internal.extension.WTFWidget;

public class WTFPlugin extends SonarPlugin {

    @SuppressWarnings("rawtypes")
    @Override
    public List getExtensions() {
        return ImmutableList.builder()
            .add(WTFChecksRegistrar.class)
            .add(WTFRulesDefinition.class)
            .add(WTFMetrics.class)
            .add(WTFMeasuresSensor.class)
            .add(WTFMeasuresDecorator.class)
            .add(WTFWidget.class)
            .build();
    }
}
