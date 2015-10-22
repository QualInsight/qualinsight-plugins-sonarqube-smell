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

import java.lang.reflect.Field;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.CheckForNull;
import com.google.common.collect.ImmutableList;
import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metric.ValueType;
import org.sonar.api.measures.Metrics;
import org.sonar.api.measures.SumChildValuesFormula;
import com.qualinsight.plugins.sonarqube.smell.api.annotation.Smell;
import com.qualinsight.plugins.sonarqube.smell.api.model.SmellType;

/**
 * Provides the {@link Metric}s related to Smell measurement.
 *
 * @author Michel Pawlak
 */
@SuppressWarnings("unchecked")
public final class SmellMetrics implements Metrics {

    public static final String DOMAIN = "Code Smells";

    private static final List<Metric<Integer>> SMELL_METRICS;

    private static final Map<SmellType, Metric<Integer>> SMELL_METRICS_BY_TYPE = new EnumMap<SmellType, Metric<Integer>>(SmellType.class);

    /**
     * Metric that tracks the debt related to Smell issues.
     */
    public static final Metric<Integer> SMELL_DEBT = new Metric.Builder("SMELL_DEBT", "Debt", ValueType.WORK_DUR).setBestValue(0d)
        .setDescription("Technical debt reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_TECHNICAL_DEBT)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that tracks the count of {@link Smell} annotations.
     */
    public static final Metric<Integer> SMELL_COUNT = new Metric.Builder("SMELL_COUNT", "Smells count", ValueType.INT).setBestValue(0d)
        .setDescription("Total number of reported code smells.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(DOMAIN)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts WRONG_LOGIC {@link SmellType}
     */
    public static final Metric<Integer> SMELL_COUNT_WRONG_LOGIC = new Metric.Builder("SMELL_COUNT_WRONG_LOGIC", "Wrong logic count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of wrong logics reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(DOMAIN)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts WRONG_LANGUAGE {@link SmellType}
     */
    public static final Metric<Integer> SMELL_COUNT_WRONG_LANGUAGE = new Metric.Builder("SMELL_COUNT_WRONG_LANGUAGE", "Wrong language count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of wrong languages reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(DOMAIN)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts OVERCOMPLICATED_ALGORITHM {@link SmellType}
     */
    public static final Metric<Integer> SMELL_COUNT_OVERCOMPLICATED_ALGORITHM = new Metric.Builder("SMELL_COUNT_OVERCOMPLICATED_ALGORITHM", "Overcomplicated algorithm count", ValueType.INT).setBestValue(
        0d)
        .setDescription("Number of overcomplicated algorithms reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(DOMAIN)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts ANTI_PATTERN {@link SmellType}
     */
    public static final Metric<Integer> SMELL_COUNT_ANTI_PATTERN = new Metric.Builder("SMELL_COUNT_ANTI_PATTERN", "Anti-pattern count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of anti-patterns reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(DOMAIN)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts BAD_DESIGN {@link SmellType}
     */
    public static final Metric<Integer> SMELL_COUNT_BAD_DESIGN = new Metric.Builder("SMELL_COUNT_BAD_DESIGN", "Bad design count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of bad designs reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(DOMAIN)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts USELESS_TEST {@link SmellType}
     */
    public static final Metric<Integer> SMELL_COUNT_USELESS_TEST = new Metric.Builder("SMELL_COUNT_USELESS_TEST", "Useless test count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of useless tests reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(DOMAIN)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts MEANINGLESS_COMMENT {@link SmellType}
     */
    public static final Metric<Integer> SMELL_COUNT_MEANINGLESS_COMMENT = new Metric.Builder("SMELL_COUNT_MEANINGLESS_COMMENT", "Meaningless comment count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of meaningless comments reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(DOMAIN)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts UNCOMMUNICATIVE_NAME {@link SmellType}
     */
    public static final Metric<Integer> SMELL_COUNT_UNCOMMUNICATIVE_NAME = new Metric.Builder("SMELL_COUNT_UNCOMMUNICATIVE_NAME", "Uncommunicative name count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of uncommunicative names reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(DOMAIN)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts SPECULATIVE_GENERALITY {@link SmellType}
     */
    public static final Metric<Integer> SMELL_COUNT_SPECULATIVE_GENERALITY = new Metric.Builder("SMELL_COUNT_SPECULATIVE_GENERALITY", "Speculative generality count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of speculative generalities reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(DOMAIN)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts ODDBALL_SOLUTION {@link SmellType}
     */
    public static final Metric<Integer> SMELL_COUNT_ODDBALL_SOLUTION = new Metric.Builder("SMELL_COUNT_ODDBALL_SOLUTION", "Oddball solution count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of oddball solutions reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(DOMAIN)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts PRIMITIVES_OBSESSION {@link SmellType}
     */
    public static final Metric<Integer> SMELL_COUNT_PRIMITIVES_OBSESSION = new Metric.Builder("SMELL_COUNT_PRIMITIVES_OBSESSION", "Primitives obsession count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of primitives obsessions reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(DOMAIN)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts INDECENT_EXPOSURE {@link SmellType}
     */
    public static final Metric<Integer> SMELL_COUNT_INDECENT_EXPOSURE = new Metric.Builder("SMELL_COUNT_INDECENT_EXPOSURE", "Indecent exposure count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of indecent exposures reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(DOMAIN)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts SOLUTION_SPRAWL {@link SmellType}
     */
    public static final Metric<Integer> SMELL_COUNT_SOLUTION_SPRAWL = new Metric.Builder("SMELL_COUNT_SOLUTION_SPRAWL", "Solution sprawl count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of solution sprawls reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(DOMAIN)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts MIDDLE_MAN {@link SmellType}
     */
    public static final Metric<Integer> SMELL_COUNT_MIDDLE_MAN = new Metric.Builder("SMELL_COUNT_MIDDLE_MAN", "Middle man count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of middle men reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(DOMAIN)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts REFUSED_BEQUEST {@link SmellType}
     */
    public static final Metric<Integer> SMELL_COUNT_REFUSED_BEQUEST = new Metric.Builder("SMELL_COUNT_REFUSED_BEQUEST", "Refused bequest count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of refused bequests reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(DOMAIN)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts NON_EXCEPTION {@link SmellType}
     */
    public static final Metric<Integer> SMELL_COUNT_NON_EXCEPTION = new Metric.Builder("SMELL_COUNT_NON_EXCEPTION", "Non exception count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of non exceptions reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(DOMAIN)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts HOW_COMMENT {@link SmellType}
     */
    public static final Metric<Integer> SMELL_COUNT_HOW_COMMENT = new Metric.Builder("SMELL_COUNT_HOW_COMMENT", "How comment count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of how comments reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(DOMAIN)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts MISSING_IMPLEMENTATION {@link SmellType}
     */
    public static final Metric<Integer> SMELL_COUNT_MISSING_IMPLEMENTATION = new Metric.Builder("SMELL_COUNT_MISSING_IMPLEMENTATION", "Missing implementation count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of missing implementations reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(DOMAIN)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts MULTIPLE_RESPONSIBILITIES {@link SmellType}
     */
    public static final Metric<Integer> SMELL_COUNT_MULTIPLE_RESPONSIBILITIES = new Metric.Builder("SMELL_COUNT_MULTIPLE_RESPONSIBILITIES", "Multiple responsibilities count", ValueType.INT).setBestValue(
        0d)
        .setDescription("Number of multiple responsibilities reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(DOMAIN)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts ABBREVIATIONS_USAGE {@link SmellType}
     */
    public static final Metric<Integer> SMELL_COUNT_ABBREVIATIONS_USAGE = new Metric.Builder("SMELL_COUNT_ABBREVIATIONS_USAGE", "Abbreviations usage count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of abbreviations usages reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(DOMAIN)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts BAD_LOGGING {@link SmellType}
     */
    public static final Metric<Integer> SMELL_COUNT_BAD_LOGGING = new Metric.Builder("SMELL_COUNT_BAD_LOGGING", "Bad logging count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of bad loggings reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(DOMAIN)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts REINVENTED_WHEEL {@link SmellType}
     */
    public static final Metric<Integer> SMELL_COUNT_REINVENTED_WHEEL = new Metric.Builder("SMELL_COUNT_REINVENTED_WHEEL", "Reinvented wheel count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of reinvented wheels reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(DOMAIN)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /**
     * Metric that counts BAD_FRAMEWORK_USAGE {@link SmellType}
     */
    public static final Metric<Integer> SMELL_COUNT_BAD_FRAMEWORK_USAGE = new Metric.Builder("SMELL_COUNT_BAD_FRAMEWORK_USAGE", "Bad framework usage count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of bad framework usages reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(DOMAIN)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    /*
     * Blocks that populates the list of Smell Metrics using reflection as well as SmellType to Metric map.
     */
    static {
        SMELL_METRICS = new LinkedList<Metric<Integer>>();
        for (final Field field : SmellMetrics.class.getFields()) {
            final String fieldName = field.getName();
            final Metric metric;
            if (Metric.class.isAssignableFrom(field.getType())) {
                try {
                    metric = (Metric) field.get(null);
                    SMELL_METRICS.add(metric);
                } catch (final IllegalAccessException e) {
                    throw new IllegalStateException("Introspection error while declaring Smell Metrics", e);
                }
                // ugly hack to map SmellTypes to Metrics without having to make the API depend on the SQ API.
                // best approach needs to be discussed.
                if (fieldName.contains("SMELL_COUNT_")) {
                    SMELL_METRICS_BY_TYPE.put(SmellType.valueOf(fieldName.replace("SMELL_COUNT_", "")), metric);
                }
            }
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List<Metric> getMetrics() {
        return ImmutableList.<Metric> copyOf(SMELL_METRICS);
    }

    /**
     * Returns a {@link Metric} from a {@link SmellType}
     *
     * @param type {@link SmellType} to convert to a metric
     * @return a {@link Metric} corresponding to the {@link SmellType}
     */
    @CheckForNull
    public static final Metric<Integer> metricFor(final SmellType type) {
        return SMELL_METRICS_BY_TYPE.get(type);
    }

}
