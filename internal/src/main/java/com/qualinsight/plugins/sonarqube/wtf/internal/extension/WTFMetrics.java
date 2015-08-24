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

public class WTFMetrics implements Metrics {

    public static final Metric<Integer> WTF_DEBT = new Metric.Builder("WTF_DEBT", "WTF Debt", ValueType.WORK_DUR).setBestValue(0d)
        .setDescription("WTF technical debt reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_TECHNICAL_DEBT)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    public static final Metric<Integer> WTF_COUNT = new Metric.Builder("WTF_COUNT", "WTF issues count", ValueType.INT).setBestValue(0d)
        .setDescription("Total number of reported WTF issues")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    public static final Metric<Integer> WTF_COUNT_WRONG_ALGORITHM = new Metric.Builder("WTF_COUNT_WRONG_ALGORITHM", "WTF wrong algorithms count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of wrong algorithms reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    public static final Metric<Integer> WTF_COUNT_ANTI_PATTERN = new Metric.Builder("WTF_COUNT_ANTI_PATTERN", "WTF anti-patterns count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of anti-paterns reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    public static final Metric<Integer> WTF_COUNT_BAD_DESIGN = new Metric.Builder("WTF_COUNT_BAD_DESIGN", "WTF bad designs count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of bad design reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    public static final Metric<Integer> WTF_COUNT_USELESS_TEST = new Metric.Builder("WTF_COUNT_USELESS_TEST", "WTF useless tests count", ValueType.INT).setBestValue(0d)
        .setDescription("Number of useless tests reported by developers.")
        .setDirection(Metric.DIRECTION_WORST)
        .setDomain(CoreMetrics.DOMAIN_SIZE)
        .setOptimizedBestValue(true)
        .setFormula(new SumChildValuesFormula(true))
        .create();

    private static final List<Metric> WTF_METRICS;

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
