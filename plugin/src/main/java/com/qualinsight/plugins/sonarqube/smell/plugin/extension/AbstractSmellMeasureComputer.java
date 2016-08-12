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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.ce.measure.Component;
import org.sonar.api.ce.measure.Component.Type;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;
import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metric.ValueType;

/**
 * {@link MeasureComputer} that aggregates Smell measures at project level.
 *
 * @author Michel Pawlak
 */
abstract class AbstractSmellMeasureComputer implements MeasureComputer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractSmellMeasureComputer.class);

    private final List<String> outputMetricsKeys;

    private final List<String> inputMetricsKeys;

    /**
     * {@link AbstractSmellMeasureComputer} IoC constructor.
     */
    public AbstractSmellMeasureComputer(final List<String> inputMetricsKeys, final List<String> outputMetricsKeys) {
        this.inputMetricsKeys = inputMetricsKeys;
        this.outputMetricsKeys = outputMetricsKeys;
    }

    /**
     * Provides a {@link List} of {@link Metric} keys for which the {@link MeasureComputer} computes and writes measures.
     *
     * @return output metrics keys
     */
    public List<String> getOutputMetricsKeys() {
        return this.outputMetricsKeys;
    }

    /**
     * Provides a {@link List} of {@link Metric} keys the {@link MeasureComputer} uses to compute measures.
     *
     * @return input metrics keys
     */
    public List<String> getInputMetricsKeys() {
        return this.inputMetricsKeys;
    }

    @Override
    public MeasureComputerDefinition define(final MeasureComputerDefinitionContext definitionContext) {
        return definitionContext.newDefinitionBuilder()
            .setInputMetrics(this.inputMetricsKeys.toArray(new String[0]))
            .setOutputMetrics(this.outputMetricsKeys.toArray(new String[0]))
            .build();
    }

    /**
     * Computes measure by summing all children measures.
     *
     * @param context {@link MeasureComputerContext} to which the measure has to be saved to.
     */
    @Override
    public void compute(final MeasureComputerContext context) {
        final Component component = context.getComponent();
        if (component.getType() != Type.FILE) {
            compute(component, context);
        }
    }

    private void compute(final Component component, final MeasureComputerContext context) {
        LOGGER.info("Computing measures for component '{}' and level {})", component.getKey(), component.getType());
        for (final String outputMetricKey : this.outputMetricsKeys) {
            final Aggregator computer = aggregator(context, outputMetricKey);
            LOGGER.info("Computed measure '{}': {})", outputMetricKey, computer.getResult());
            computer.addMeasureToContext();
        }
    }

    /**
     * Retrieves an {@link Aggregator} able to aggregate {@link Measure}s.
     *
     * @param context {@link MeasureComputerContext} to which aggregated result has to be saved.
     * @param metricKey key of the {@link Metric} for which {@link Measure}s are aggregated.
     * @return {@link Aggregator} instance.
     */
    protected Aggregator aggregator(final MeasureComputerContext context, final String metricKey) {
        final Aggregator aggregator = new Aggregator(context, metricKey);
        final Iterable<Measure> measures = context.getChildrenMeasures(metricKey);
        LOGGER.info("current metric: '{}'", metricKey);
        if (measures != null) {
            for (final Measure measure : measures) {
                aggregator.aggregate(measure);
            }
        }
        return aggregator;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    /**
     * {@link Measure} aggregation class.
     *
     * @author Michel Pawlak
     */
    class Aggregator {

        private Number value = 0;

        private String metricKey;

        private ValueType valueType;

        private MeasureComputerContext context;

        /**
         * {@link Aggregator} contructor.
         *
         * @param context {@link MeasureComputerContext} to which aggregated result has to be saved.
         * @param metricKey key of the {@link Metric} for which {@link Measure}s are aggregated.
         */
        public Aggregator(final MeasureComputerContext context, final String metricKey) {
            this.context = context;
            this.metricKey = metricKey;
            this.valueType = SmellMetrics.metricFor(metricKey)
                .getType();
            switch (this.valueType) {
                case INT:
                    this.value = Integer.valueOf(0);
                    break;
                case WORK_DUR:
                    this.value = Long.valueOf(0);
                    break;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        /**
         * Retrieves the result aggregated by this {@link Aggregator}
         *
         * @return aggregation result as a {@link Number}
         */
        public Number getResult() {
            return this.value;
        }

        /**
         * Aggregates a {@link Measure} to the result held by the {@link Aggregator}.
         *
         * @param measure {@link Measure} from which the value to be aggregated has to be extracted.
         */
        public void aggregate(final Measure measure) {
            switch (this.valueType) {
                case INT:
                    this.value = this.value.intValue() + measure.getIntValue();
                    break;
                case WORK_DUR:
                    this.value = this.value.longValue() + measure.getLongValue();
                    break;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        /**
         * Saves the aggregation result to the {@link MeasureComputerContext}.
         */
        public void addMeasureToContext() {
            switch (this.valueType) {
                case INT:
                    this.context.addMeasure(this.metricKey, this.value.intValue());
                    break;
                case WORK_DUR:
                    this.context.addMeasure(this.metricKey, this.value.longValue());
                    break;
                default:
                    throw new UnsupportedOperationException();
            }
        }

    }

}
