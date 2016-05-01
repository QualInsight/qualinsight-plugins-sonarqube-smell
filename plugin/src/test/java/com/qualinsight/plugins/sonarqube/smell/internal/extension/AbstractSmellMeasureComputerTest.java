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
import com.qualinsight.plugins.sonarqube.smell.internal.extension.AbstractSmellMeasureComputer;
import com.google.common.collect.ImmutableList;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import net.jcip.annotations.NotThreadSafe;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
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

@NotThreadSafe
@RunWith(JUnitParamsRunner.class)
public abstract class AbstractSmellMeasureComputerTest {

    protected static final Metric<Integer> dummyMetric = new Metric.Builder("DUMMY", "DUMMY", ValueType.INT).create();

    private static final Iterable<Measure> DUMMY_METRIC_COLLECTION = new ArrayList<Measure>();

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
    @Parameters
    public void compute_should_saveMeasures_when_resourceIsAProject(final Type scope) {
        Mockito.when(this.context.getComponent())
            .thenReturn(this.component);
        Mockito.when(this.component.getType())
            .thenReturn(scope);
        Mockito.when(this.context.getChildrenMeasures(Matchers.any(String.class)))
            .thenReturn(DUMMY_METRIC_COLLECTION);
        final AbstractSmellMeasureComputer sut = sut();
        sut.compute(this.context);

        Mockito.verify(this.context, Mockito.times(1))
            .getComponent();
        Mockito.verify(this.context, Mockito.times(sut.getOutputMetricsKeys()
            .size()))
            .addMeasure(Matchers.anyString(), Matchers.eq(0));
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
        final AbstractSmellMeasureComputer sut = sut();
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

    @Test
    public void decorate_should_saveExpectedMeasureTotal() {
        Mockito.when(this.context.getComponent())
            .thenReturn(this.component);
        Mockito.when(this.component.getType())
            .thenReturn(Type.PROJECT);
        Mockito.when(this.context.getChildrenMeasures(Matchers.any(String.class)))
            .thenReturn(dummyMeasures());
        final AbstractSmellMeasureComputer sut = sut();
        sut.compute(this.context);
        // There are SmellTYPES_COUNT metrics in total
        Mockito.verify(this.context, Mockito.times(1))
            .getComponent();
        Mockito.verify(this.context, Mockito.times(expectedGetChildrenMeasuresCount()))
            .getChildrenMeasures(Matchers.any(String.class));
        Mockito.verify(this.context, Mockito.times(expectedSavedMeasuresCount()))
            .addMeasure(Matchers.any(String.class), Matchers.eq(expectedSavedMeasureValue()));
        Mockito.verifyNoMoreInteractions(this.context);
    }

    protected static final Collection<Measure> dummyMeasures() {
        final Measure measure = TestMeasure.createMeasure(1);
        return ImmutableList.<Measure> of(measure, measure, measure);
    }

    @Test
    public void toString_should_return_simpleClassName() {
        Assertions.assertThat(sut().toString())
            .isEqualTo(sut().getClass()
                .getSimpleName());
    }

    protected abstract AbstractSmellMeasureComputer sut();

    protected abstract Integer expectedGetChildrenMeasuresCount();

    protected abstract Integer expectedSavedMeasuresCount();

    protected abstract Integer expectedSavedMeasureValue();

}
