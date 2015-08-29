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
package com.qualinsight.plugins.sonarqube.wtf.internal.extension;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metric.ValueType;
import org.sonar.api.measures.Metrics;
import org.sonar.api.measures.SumChildValuesFormula;
import com.google.common.collect.ImmutableList;
import com.qualinsight.plugins.sonarqube.wtf.api.annotation.WTF;

/**
 * Provides the {@link Metric}s related to WTF! measurement.
 *
 * @author Michel Pawlak
 */
public final class WTFMetrics implements Metrics {

    /**
     * Metric that tracks the debt related to WTF! issues.
     */
    public static final Metric<Integer> WTF_DEBT = new Metric.Builder("WTF_DEBT", "WTF Debt", ValueType.WORK_DUR).setBestValue(0d)
        .setDescription("WTF technical debt reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_TECHNICAL_DEBT)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that tracks the count of {@link WTF} annotations.
     */
    public static final Metric<Integer> WTF_COUNT = new Metric.Builder("WTF_COUNT", "WTF issues count", ValueType.INT).setBestValue(0d)
        .setDescription("Total number of reported WTF issues")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that tracks the count of WTF! related to a wrong logic.
     */
    public static final Metric<Integer> WTF_COUNT_WRONG_LOGIC = new Metric.Builder("WTF_COUNT_WRONG_LOGIC", "WTF wrong logic count", ValueType.INT).setBestValue(0d)
        .setDescription("Total number of wrong logic cases reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that tracks the count of WTF! related to the usage of an overcomplicated alogrithm.
     */
    public static final Metric<Integer> WTF_COUNT_OVERCOMPLICATED_ALGORITHM = new Metric.Builder("WTF_COUNT_OVERCOMPLICATED_ALGORITHM", "WTF overcomplicated algorithms count", ValueType.INT).setBestValue(
        0d)
        .setDescription("Number of overcompllicated algorithms reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that tracks the count of WTF! related to the usage of an antipattern.
     */
    public static final Metric<Integer> WTF_COUNT_ANTI_PATTERN = new Metric.Builder("WTF_COUNT_ANTI_PATTERN", "WTF anti-patterns count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of anti-paterns reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that tracks the count of WTF! related to bad or poor design.
     */
    public static final Metric<Integer> WTF_COUNT_BAD_DESIGN = new Metric.Builder("WTF_COUNT_BAD_DESIGN", "WTF bad designs count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of bad design reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that tracks the count of useless tests.
     */
    public static final Metric<Integer> WTF_COUNT_USELESS_TEST = new Metric.Builder("WTF_COUNT_USELESS_TEST", "WTF useless tests count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of useless tests reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    private static final List<Metric> WTF_METRICS;

    /*
     * Blocks that populates the list of WTF! Metrics using reflection.
     */
    static {
        WTF_METRICS = new LinkedList<Metric>();
        for (final Field field : WTFMetrics.class.getFields()) {
            if (!Modifier.isTransient(field.getModifiers()) && Metric.class.isAssignableFrom(field.getType())) {
                try {
                    WTF_METRICS.add((Metric) field.get(null));
                } catch (final IllegalAccessException e) {
                    throw new IllegalStateException("Introspection error while declaring WTF metrics", e);
                }
            }
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List<Metric> getMetrics() {
        return ImmutableList.<Metric> copyOf(WTF_METRICS);
    }

}
