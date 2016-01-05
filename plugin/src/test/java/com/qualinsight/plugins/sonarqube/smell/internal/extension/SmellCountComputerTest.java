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

import java.util.ArrayList;
import java.util.Collection;
import com.google.common.collect.ImmutableList;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import net.jcip.annotations.NotThreadSafe;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalMatchers;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.sonar.api.ce.measure.Component;
import org.sonar.api.ce.measure.Component.Type;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerDefinitionContext;
import org.sonar.api.ce.measure.test.TestMeasure;
import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metric.ValueType;
import com.qualinsight.plugins.sonarqube.smell.api.model.SmellType;
import com.qualinsight.plugins.sonarqube.smell.plugin.extension.SmellCountByTypeMeasuresComputer;
import com.qualinsight.plugins.sonarqube.smell.plugin.extension.SmellMetrics;

@NotThreadSafe
@RunWith(JUnitParamsRunner.class)
public class SmellCountComputerTest {

    private static final Metric<Integer> DUMMY_METRIC = new Metric.Builder("DUMMY", "DUMMY", ValueType.INT).create();

    @SuppressWarnings("rawtypes")
    private static final Iterable<Measure> DUMMY_METRIC_COLLECTION = new ArrayList<Measure>();

    private static final Integer SmellTYPES_COUNT = SmellType.values().length;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    public MeasureComputerDefinitionContext definitionContext;

    @Mock
    public MeasureComputerContext context;

    @Mock
    public Component component;

    @Before
    public void setUp() {
    }

    @Test
    public void parameterizedTests_should_useCorrectMetricsCount() {
        final SoftAssertions softly = new SoftAssertions();
        softly.assertThat(parametersForDecorate_should_saveExpectedMeasureTotal_when_usingAnyMetric().length)
            .as("Count of decorated SmellTypes")
            .isEqualTo(SmellTYPES_COUNT);
        softly.assertAll();
    }

    @Ignore("Test need to be rewritten after migration to SQ 5.2")
    @Test
    @Parameters
    public void compute_should_saveMeasures_when_resourceIsAProject(final Type scope) {
        Mockito.when(this.context.getComponent())
            .thenReturn(this.component);
        Mockito.when(this.component.getType())
            .thenReturn(scope);
        Mockito.when(this.context.getChildrenMeasures(Matchers.any(String.class)))
            .thenReturn(DUMMY_METRIC_COLLECTION);
        final SmellCountByTypeMeasuresComputer sut = new SmellCountByTypeMeasuresComputer();
        sut.compute(this.context);
        Mockito.verify(this.context, Mockito.times(1))
            .getComponent();
        // Write verifications here
    }

    @SuppressWarnings("unused")
    private Object[] parametersForCompute_should_saveMeasures_when_resourceIsAProject() {
        return new Object[] {
            Type.PROJECT,
            Type.MODULE,
            Type.DIRECTORY,
            Type.VIEW,
            Type.SUBVIEW
        };
    }

    @Test
    @Parameters
    public void decorate_shouldNot_saveMeasures_when_resourceIsNotAProject(final Type scope) {
        Mockito.when(this.context.getComponent())
            .thenReturn(this.component);
        Mockito.when(this.component.getType())
            .thenReturn(scope);
        Mockito.when(this.context.getChildrenMeasures(Matchers.any(String.class)))
            .thenReturn(DUMMY_METRIC_COLLECTION);
        final SmellCountByTypeMeasuresComputer sut = new SmellCountByTypeMeasuresComputer();
        sut.compute(this.context);
        Mockito.verify(this.context, Mockito.times(1))
            .getComponent();
        Mockito.verify(this.component, Mockito.times(1))
            .getType();
        Mockito.verifyNoMoreInteractions(this.component, this.context);
    }

    @SuppressWarnings("unused")
    private Object[] parametersForDecorate_shouldNot_saveMeasures_when_resourceIsNotAProject() {
        return new Object[] {
            Type.FILE,
        };
    }

    @Ignore("Test need to be rewritten after migration to SQ 5.2")
    @Test
    @Parameters
    public void decorate_should_saveExpectedMeasureTotal_when_usingAnyMetric(final Metric<Integer> metric, @SuppressWarnings("rawtypes") final Collection<Measure> measures) {
        Mockito.when(this.context.getComponent())
            .thenReturn(this.component);
        Mockito.when(this.component.getType())
            .thenReturn(Type.PROJECT);
        Mockito.when(this.context.getChildrenMeasures(Matchers.eq(metric.getKey())))
            .thenReturn(measures);
        Mockito.when(this.context.getChildrenMeasures(AdditionalMatchers.not(Matchers.eq(metric.getKey()))))
            .thenReturn(null);
        final SmellCountByTypeMeasuresComputer sut = new SmellCountByTypeMeasuresComputer();
        sut.compute(this.context);
        Mockito.verify(this.context, Mockito.times(1))
            .getComponent();
        // Write verifications here
    }

    private Object[] parametersForDecorate_should_saveExpectedMeasureTotal_when_usingAnyMetric() {
        return new Object[] {
            new Object[] {
                SmellMetrics.SMELL_COUNT_ABBREVIATIONS_USAGE,
                dummyMeasures()
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_ANTI_PATTERN,
                dummyMeasures()
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_BAD_DESIGN,
                dummyMeasures()
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_BAD_FRAMEWORK_USAGE,
                dummyMeasures()
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_BAD_LOGGING,
                dummyMeasures()
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_HOW_COMMENT,
                dummyMeasures()
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_INDECENT_EXPOSURE,
                dummyMeasures()
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_MEANINGLESS_COMMENT,
                dummyMeasures()
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_MIDDLE_MAN,
                dummyMeasures()
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_MISSING_IMPLEMENTATION,
                dummyMeasures()
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_MULTIPLE_RESPONSIBILITIES,
                dummyMeasures()
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_NON_EXCEPTION,
                dummyMeasures()
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_ODDBALL_SOLUTION,
                dummyMeasures()
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_OVERCOMPLICATED_ALGORITHM,
                dummyMeasures()
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_PRIMITIVES_OBSESSION,
                dummyMeasures()
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_REFUSED_BEQUEST,
                dummyMeasures()
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_REINVENTED_WHEEL,
                dummyMeasures()
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_SOLUTION_SPRAWL,
                dummyMeasures()
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_SPECULATIVE_GENERALITY,
                dummyMeasures()
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_UNCOMMUNICATIVE_NAME,
                dummyMeasures()
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_USELESS_TEST,
                dummyMeasures()
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_WRONG_LANGUAGE,
                dummyMeasures()
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_WRONG_LOGIC,
                dummyMeasures()
            },
        };
    }

    @Test
    public void decorate_should_saveExpectedMeasureTotal_when_usingAllMetrics() {
        Mockito.when(this.context.getComponent())
            .thenReturn(this.component);
        Mockito.when(this.component.getType())
            .thenReturn(Type.PROJECT);
        Mockito.when(this.context.getChildrenMeasures(Matchers.any(String.class)))
            .thenReturn(dummyMeasures());
        final SmellCountByTypeMeasuresComputer sut = new SmellCountByTypeMeasuresComputer();
        sut.compute(this.context);
        // There are SmellTYPES_COUNT metrics in total
        Mockito.verify(this.context, Mockito.times(1))
            .getComponent();
        Mockito.verify(this.context, Mockito.times(SmellTYPES_COUNT))
            .getChildrenMeasures(Matchers.any(String.class));
        Mockito.verify(this.context, Mockito.times(23))
            .addMeasure(Matchers.any(String.class), Matchers.eq(3));
        Mockito.verifyNoMoreInteractions(this.context);
    }

    @SuppressWarnings("rawtypes")
    private static final Collection<Measure> dummyMeasures() {
        final Measure measure = TestMeasure.createMeasure(1);
        return ImmutableList.<Measure> of(measure, measure, measure);
    }

    @Test
    public void toString_should_return_simpleClassName() {
        Assertions.assertThat(new SmellCountByTypeMeasuresComputer().toString())
            .isEqualTo(SmellCountByTypeMeasuresComputer.class.getSimpleName());
    }
}
