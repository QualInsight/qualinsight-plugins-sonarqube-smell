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
package com.qualinsight.plugins.sonarqube.smell.internal.extension;

import java.util.List;
import com.google.common.collect.ImmutableList;
import org.sonar.api.ce.measure.MeasureComputer;

/**
 * {@link MeasureComputer} that aggregates Smell debt measure.
 *
 * @author Michel Pawlak
 */
public final class SmellDebtComputer extends AbstractSmellMeasureComputer {

    private static final List<String> inputMetricsKeys = ImmutableList.of(SmellMetrics.SMELL_DEBT.getKey());

    private static final List<String> outputMetricsKeys = ImmutableList.of(SmellMetrics.SMELL_DEBT.getKey());

    /**
     * {@link SmellDebtComputer} IoC constructor.
     */
    public SmellDebtComputer() {
        super(inputMetricsKeys, outputMetricsKeys);
    }

}
