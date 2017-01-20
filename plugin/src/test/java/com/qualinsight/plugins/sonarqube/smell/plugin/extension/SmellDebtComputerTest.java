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
package com.qualinsight.plugins.sonarqube.smell.plugin.extension;

import net.jcip.annotations.NotThreadSafe;
import org.sonar.api.measures.Metric.ValueType;

@NotThreadSafe
// @Ignore("not working")
public class SmellDebtComputerTest extends AbstractSmellMeasureComputerTest {

    @Override
    protected AbstractSmellMeasureComputer sut() {
        return new SmellDebtComputer();
    }

    @Override
    protected Integer expectedSavedMeasuresCount() {
        return 1;
    }

    @Override
    protected Integer expectedGetChildrenMeasuresCount() {
        return 1;
    }

    @Override
    protected ValueType metricValueType() {
        return ValueType.WORK_DUR;
    }

    @Override
    protected Integer expectedSavedMeasureIntValue() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Long expectedSavedMeasureLongValue() {
        return Long.valueOf(dummyMeasures(metricValueType()).size());
    }
}
