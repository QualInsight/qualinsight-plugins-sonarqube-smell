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
import com.qualinsight.plugins.sonarqube.wtf.api.model.WTFType;

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
     * Metric that counts WRONG_LOGIC {@link WTFType}
     */
    public static final Metric<Integer> WTF_COUNT_WRONG_LOGIC = new Metric.Builder("WTF_COUNT_WRONG_LOGIC", "WTF wrong logic count", ValueType.INT).setBestValue(0d)
        .setDescription("Total number of wrong logic cases reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts OVERCOMPLICATED_ALGORITHM {@link WTFType}
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
     * Metric that counts ANTI_PATTERN {@link WTFType}
     */
    public static final Metric<Integer> WTF_COUNT_ANTI_PATTERN = new Metric.Builder("WTF_COUNT_ANTI_PATTERN", "WTF anti-patterns count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of anti-paterns reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts BAD_DESIGN {@link WTFType}
     */
    public static final Metric<Integer> WTF_COUNT_BAD_DESIGN = new Metric.Builder("WTF_COUNT_BAD_DESIGN", "WTF bad designs count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of bad design reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts USELESS_TEST {@link WTFType}
     */
    public static final Metric<Integer> WTF_COUNT_USELESS_TEST = new Metric.Builder("WTF_COUNT_USELESS_TEST", "WTF useless tests count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of useless tests reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts MEANINGLESS_COMMENT {@link WTFType}
     */
    public static final Metric<Integer> WTF_COUNT_MEANINGLESS_COMMENT = new Metric.Builder("WTF_COUNT_MEANINGLESS_COMMENT", "WTF meaningless comments count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of meaningless comments reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts UNCOMMUNICATIVE_NAME {@link WTFType}
     */
    public static final Metric<Integer> WTF_COUNT_UNCOMMUNICATIVE_NAME = new Metric.Builder("WTF_COUNT_UNCOMMUNICATIVE_NAME", "WTF uncommunicative names count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of uncommunicative names reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts SPECULATIVE_GENERALITY {@link WTFType}
     */
    public static final Metric<Integer> WTF_COUNT_SPECULATIVE_GENERALITY = new Metric.Builder("WTF_COUNT_SPECULATIVE_GENERALITY", "WTF speculative generalities count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of speculative generalities reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts ODDBALL_SOLUTION {@link WTFType}
     */
    public static final Metric<Integer> WTF_COUNT_ODDBALL_SOLUTION = new Metric.Builder("WTF_COUNT_ODDBALL_SOLUTION", "WTF oddball solutions count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of oddball solutions reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts PRIMITIVES_OBSESSION {@link WTFType}
     */
    public static final Metric<Integer> WTF_COUNT_PRIMITIVES_OBSESSION = new Metric.Builder("WTF_COUNT_PRIMITIVES_OBSESSION", "WTF primitives obsessions count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of primitives obsessions reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts INDECENT_EXPOSURE {@link WTFType}
     */
    public static final Metric<Integer> WTF_COUNT_INDECENT_EXPOSURE = new Metric.Builder("WTF_COUNT_INDECENT_EXPOSURE", "WTF indecent exposures count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of indecent exposures reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts SOLUTION_SPRAWL {@link WTFType}
     */
    public static final Metric<Integer> WTF_COUNT_SOLUTION_SPRAWL = new Metric.Builder("WTF_COUNT_SOLUTION_SPRAWL", "WTF solution sprawls count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of solution sprawls reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts MIDDLE_MAN {@link WTFType}
     */
    public static final Metric<Integer> WTF_COUNT_MIDDLE_MAN = new Metric.Builder("WTF_COUNT_MIDDLE_MAN", "WTF middle mans count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of middle mans reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts REFUSED_BEQUEST {@link WTFType}
     */
    public static final Metric<Integer> WTF_COUNT_REFUSED_BEQUEST = new Metric.Builder("WTF_COUNT_REFUSED_BEQUEST", "WTF refused bequests count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of refused bequests reported by developers.")
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
