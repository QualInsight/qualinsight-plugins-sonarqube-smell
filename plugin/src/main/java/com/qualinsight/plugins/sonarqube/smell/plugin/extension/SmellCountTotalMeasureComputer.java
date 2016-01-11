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

import java.util.List;
import com.google.common.collect.ImmutableList;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;

/**
 * {@link MeasureComputer} that aggregates Smell Count measures.
 *
 * @author Michel Pawlak
 */
public final class SmellCountTotalMeasureComputer extends AbstractSmellMeasureComputer {

    private static final List<String> inputMetricsKeys = ImmutableList.copyOf(SmellCountByTypeMeasuresComputer.SMELL_COUNT_INPUT_METRICS_KEYS);

    private static final List<String> outputMetricsKeys = ImmutableList.of(SmellMetrics.SMELL_COUNT.getKey());

    /**
     * {@link SmellCountTotalMeasureComputer} IoC constructor.
     */
    public SmellCountTotalMeasureComputer() {
        super(inputMetricsKeys, outputMetricsKeys, SmellMetricType.INTEGER);
    }

    @Override
    protected Aggregator aggregator(final MeasureComputerContext context, final String metricKey, final SmellMetricType type) {
        final Aggregator aggregator = new Aggregator(context, metricKey, type);
        for (final String inputMetricKey : inputMetricsKeys) {
            final Iterable<Measure> measures = context.getChildrenMeasures(inputMetricKey);
            if (measures != null) {
                for (final Measure measure : measures) {
                    aggregator.aggregate(measure);
                }
            }
        }
        return aggregator;
    }
}
