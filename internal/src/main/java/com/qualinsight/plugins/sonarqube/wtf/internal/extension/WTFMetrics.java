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
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.CheckForNull;
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
@SuppressWarnings("unchecked")
public final class WTFMetrics implements Metrics {

    private static final List<Metric<Integer>> WTF_METRICS;

    private static final Map<WTFType, Metric<Integer>> WTF_METRICS_BY_WTFTYPE = new EnumMap<WTFType, Metric<Integer>>(WTFType.class);

    /**
     * Metric that tracks the debt related to WTF! issues.
     */
    public static final Metric<Integer> WTF_DEBT = new Metric.Builder("WTF_DEBT", "WTF debt", ValueType.WORK_DUR).setBestValue(0d)
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
        .setDescription("Total number of reported WTF issues.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts WRONG_LOGIC {@link WTFType}
     */
    public static final Metric<Integer> WTF_COUNT_WRONG_LOGIC = new Metric.Builder("WTF_COUNT_WRONG_LOGIC", "WTF wrong logic count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of wrong logics reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts WRONG_LANGUAGE {@link WTFType}
     */
    public static final Metric<Integer> WTF_COUNT_WRONG_LANGUAGE = new Metric.Builder("WTF_COUNT_WRONG_LANGUAGE", "WTF wrong language count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of wrong languages reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts OVERCOMPLICATED_ALGORITHM {@link WTFType}
     */
    public static final Metric<Integer> WTF_COUNT_OVERCOMPLICATED_ALGORITHM = new Metric.Builder("WTF_COUNT_OVERCOMPLICATED_ALGORITHM", "WTF overcomplicated algorithm count", ValueType.INT).setBestValue(
        0d)
        .setDescription("Number of overcomplicated algorithms reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts ANTI_PATTERN {@link WTFType}
     */
    public static final Metric<Integer> WTF_COUNT_ANTI_PATTERN = new Metric.Builder("WTF_COUNT_ANTI_PATTERN", "WTF anti-pattern count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of anti-patterns reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts BAD_DESIGN {@link WTFType}
     */
    public static final Metric<Integer> WTF_COUNT_BAD_DESIGN = new Metric.Builder("WTF_COUNT_BAD_DESIGN", "WTF bad design count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of bad designs reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts USELESS_TEST {@link WTFType}
     */
    public static final Metric<Integer> WTF_COUNT_USELESS_TEST = new Metric.Builder("WTF_COUNT_USELESS_TEST", "WTF useless test count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of useless tests reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts MEANINGLESS_COMMENT {@link WTFType}
     */
    public static final Metric<Integer> WTF_COUNT_MEANINGLESS_COMMENT = new Metric.Builder("WTF_COUNT_MEANINGLESS_COMMENT", "WTF meaningless comment count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of meaningless comments reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts UNCOMMUNICATIVE_NAME {@link WTFType}
     */
    public static final Metric<Integer> WTF_COUNT_UNCOMMUNICATIVE_NAME = new Metric.Builder("WTF_COUNT_UNCOMMUNICATIVE_NAME", "WTF uncommunicative name count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of uncommunicative names reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts SPECULATIVE_GENERALITY {@link WTFType}
     */
    public static final Metric<Integer> WTF_COUNT_SPECULATIVE_GENERALITY = new Metric.Builder("WTF_COUNT_SPECULATIVE_GENERALITY", "WTF speculative generality count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of speculative generalities reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts ODDBALL_SOLUTION {@link WTFType}
     */
    public static final Metric<Integer> WTF_COUNT_ODDBALL_SOLUTION = new Metric.Builder("WTF_COUNT_ODDBALL_SOLUTION", "WTF oddball solution count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of oddball solutions reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts PRIMITIVES_OBSESSION {@link WTFType}
     */
    public static final Metric<Integer> WTF_COUNT_PRIMITIVES_OBSESSION = new Metric.Builder("WTF_COUNT_PRIMITIVES_OBSESSION", "WTF primitives obsession count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of primitives obsessions reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts INDECENT_EXPOSURE {@link WTFType}
     */
    public static final Metric<Integer> WTF_COUNT_INDECENT_EXPOSURE = new Metric.Builder("WTF_COUNT_INDECENT_EXPOSURE", "WTF indecent exposure count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of indecent exposures reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts SOLUTION_SPRAWL {@link WTFType}
     */
    public static final Metric<Integer> WTF_COUNT_SOLUTION_SPRAWL = new Metric.Builder("WTF_COUNT_SOLUTION_SPRAWL", "WTF solution sprawl count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of solution sprawls reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts MIDDLE_MAN {@link WTFType}
     */
    public static final Metric<Integer> WTF_COUNT_MIDDLE_MAN = new Metric.Builder("WTF_COUNT_MIDDLE_MAN", "WTF middle man count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of middle men reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts REFUSED_BEQUEST {@link WTFType}
     */
    public static final Metric<Integer> WTF_COUNT_REFUSED_BEQUEST = new Metric.Builder("WTF_COUNT_REFUSED_BEQUEST", "WTF refused bequest count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of refused bequests reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts NON_EXCEPTION {@link WTFType}
     */
    public static final Metric<Integer> WTF_COUNT_NON_EXCEPTION = new Metric.Builder("WTF_COUNT_NON_EXCEPTION", "WTF non exception count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of non exceptions reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts HOW_COMMENT {@link WTFType}
     */
    public static final Metric<Integer> WTF_COUNT_HOW_COMMENT = new Metric.Builder("WTF_COUNT_HOW_COMMENT", "WTF how comment count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of how comments reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts MISSING_IMPLEMENTATION {@link WTFType}
     */
    public static final Metric<Integer> WTF_COUNT_MISSING_IMPLEMENTATION = new Metric.Builder("WTF_COUNT_MISSING_IMPLEMENTATION", "WTF missing implementation count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of missing implementations reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /*
     * Blocks that populates the list of WTF! Metrics using reflection as well as WTFType to Metric map.
     */
    static {
        WTF_METRICS = new LinkedList<Metric<Integer>>();
        for (final Field field : WTFMetrics.class.getFields()) {
            final String fieldName = field.getName();
            final Metric metric;
            if (!Modifier.isTransient(field.getModifiers()) && Metric.class.isAssignableFrom(field.getType())) {
                try {
                    metric = (Metric) field.get(null);
                    WTF_METRICS.add(metric);
                } catch (final IllegalAccessException e) {
                    throw new IllegalStateException("Introspection error while declaring WTF metrics", e);
                }
                // ugly hack to map WTFTypes to Metrics without having to make the API depend on the SQ API.
                // best approach needs to be discussed.
                if (fieldName.contains("WTF_COUNT_")) {
                    WTF_METRICS_BY_WTFTYPE.put(WTFType.valueOf(fieldName.replace("WTF_COUNT_", "")), metric);
                }
            }
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List<Metric> getMetrics() {
        return ImmutableList.<Metric> copyOf(WTF_METRICS);
    }

    /**
     * Returns a {@link Metric} from a {@link WTFType}
     *
     * @param type {@link WTFType} to convert to a metric
     * @return a {@link Metric} corresponding to the {@link WTFType}
     */
    @CheckForNull
    public static final Metric<Integer> metricFor(final WTFType type) {
        return WTF_METRICS_BY_WTFTYPE.get(type);
    }

}
