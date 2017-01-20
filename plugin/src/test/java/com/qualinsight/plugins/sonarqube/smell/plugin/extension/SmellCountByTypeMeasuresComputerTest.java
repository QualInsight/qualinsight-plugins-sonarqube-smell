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

import java.util.Collection;
import junitparams.Parameters;
import net.jcip.annotations.NotThreadSafe;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.mockito.AdditionalMatchers;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.sonar.api.ce.measure.Component.Type;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metric.ValueType;

@NotThreadSafe
public class SmellCountByTypeMeasuresComputerTest extends AbstractSmellMeasureComputerTest {

    @Override
    protected AbstractSmellMeasureComputer sut() {
        return new SmellCountByTypeMeasuresComputer();
    }

    @Test
    public void parameterizedTests_should_useCorrectMetricsCount() {
        final SoftAssertions softly = new SoftAssertions();
        softly.assertThat(parametersForDecorate_should_saveExpectedMeasureTotal_when_usingAnyMetric().length)
            .as("Count of decorated SmellTypes")
            .isEqualTo(sut().getOutputMetricsKeys()
                .size());
        softly.assertAll();
    }

    @Test
    @Parameters
    public void decorate_should_saveExpectedMeasureTotal_when_usingAnyMetric(final Metric<Integer> metric, final Collection<Measure> measures) {
        Mockito.when(this.context.getComponent())
            .thenReturn(this.component);
        Mockito.when(this.component.getType())
            .thenReturn(Type.PROJECT);
        Mockito.when(this.context.getChildrenMeasures(Matchers.eq(metric.getKey())))
            .thenReturn(measures);
        Mockito.when(this.context.getChildrenMeasures(AdditionalMatchers.not(Matchers.eq(metric.getKey()))))
            .thenReturn(null);
        final AbstractSmellMeasureComputer sut = sut();
        sut.compute(this.context);
        Mockito.verify(this.context, Mockito.times(1))
            .getComponent();
        Mockito.verify(this.context, Mockito.times(1))
            .getChildrenMeasures(Matchers.eq(metric.getKey()));
        Mockito.verify(this.context, Mockito.times(1))
            .addMeasure(Matchers.eq(metric.getKey()), Matchers.eq(3));
        Mockito.verify(this.context, Mockito.times(sut.getInputMetricsKeys()
            .size() - 1))
            .addMeasure(AdditionalMatchers.not(Matchers.eq(metric.getKey())), Matchers.eq(0));
    }

    protected Object[] parametersForDecorate_should_saveExpectedMeasureTotal_when_usingAnyMetric() {
        return new Object[] {
            new Object[] {
                SmellMetrics.SMELL_COUNT_ABBREVIATIONS_USAGE,
                dummyMeasures(ValueType.INT)
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_ANTI_PATTERN,
                dummyMeasures(ValueType.INT)
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_BAD_DESIGN,
                dummyMeasures(ValueType.INT)
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_BAD_FRAMEWORK_USAGE,
                dummyMeasures(ValueType.INT)
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_BAD_LOGGING,
                dummyMeasures(ValueType.INT)
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_HOW_COMMENT,
                dummyMeasures(ValueType.INT)
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_INDECENT_EXPOSURE,
                dummyMeasures(ValueType.INT)
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_MEANINGLESS_COMMENT,
                dummyMeasures(ValueType.INT)
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_MIDDLE_MAN,
                dummyMeasures(ValueType.INT)
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_MISSING_IMPLEMENTATION,
                dummyMeasures(ValueType.INT)
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_MULTIPLE_RESPONSIBILITIES,
                dummyMeasures(ValueType.INT)
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_NON_EXCEPTION,
                dummyMeasures(ValueType.INT)
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_ODDBALL_SOLUTION,
                dummyMeasures(ValueType.INT)
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_OVERCOMPLICATED_ALGORITHM,
                dummyMeasures(ValueType.INT)
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_PRIMITIVES_OBSESSION,
                dummyMeasures(ValueType.INT)
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_REFUSED_BEQUEST,
                dummyMeasures(ValueType.INT)
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_REINVENTED_WHEEL,
                dummyMeasures(ValueType.INT)
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_SOLUTION_SPRAWL,
                dummyMeasures(ValueType.INT)
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_SPECULATIVE_GENERALITY,
                dummyMeasures(ValueType.INT)
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_UNCOMMUNICATIVE_NAME,
                dummyMeasures(ValueType.INT)
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_USELESS_TEST,
                dummyMeasures(ValueType.INT)
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_WRONG_LANGUAGE,
                dummyMeasures(ValueType.INT)
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_WRONG_LOGIC,
                dummyMeasures(ValueType.INT)
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_MISSING_DOCUMENTATION,
                dummyMeasures(ValueType.INT)
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_MISSING_TEST,
                dummyMeasures(ValueType.INT)
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_OTHER,
                dummyMeasures(ValueType.INT)
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_NON_COMPLIANCE_WITH_STANDARDS,
                dummyMeasures(ValueType.INT)
            },
        };
    }

    @Override
    protected Integer expectedGetChildrenMeasuresCount() {
        // once per Metric
        return sut().getOutputMetricsKeys()
            .size();
    }

    @Override
    protected Integer expectedSavedMeasuresCount() {
        // once per Metric
        return sut().getOutputMetricsKeys()
            .size();
    }

    @Override
    protected ValueType metricValueType() {
        return ValueType.INT;
    }

    @Override
    protected Long expectedSavedMeasureLongValue() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Integer expectedSavedMeasureIntValue() {
        return dummyMeasures(ValueType.INT).size();
    }

}
