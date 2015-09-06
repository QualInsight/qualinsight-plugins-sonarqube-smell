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

import java.util.Collection;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalMatchers;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.sonar.api.batch.DecoratorContext;
import org.sonar.api.batch.fs.FilePredicate;
import org.sonar.api.batch.fs.FilePredicates;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.measures.Measure;
import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metric.ValueType;
import org.sonar.plugins.java.Java;
import com.google.common.collect.ImmutableList;

@RunWith(JUnitParamsRunner.class)
public class WTFMeasuresDecoratorTest {

    private static final Metric<Integer> DUMMY_METRIC = new Metric.Builder("DUMMY", "DUMMY", ValueType.INT).create();

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    public DecoratorContext context;

    @Mock
    public FileSystem fs;

    @Mock
    public FilePredicates predicates;

    @Mock
    public FilePredicate predicate;

    @Before
    public void setUp() {
        Mockito.when(this.fs.predicates())
            .thenReturn(this.predicates);
        Mockito.when(this.predicates.hasLanguage(Matchers.eq(Java.KEY)))
            .thenReturn(this.predicate);
    }

    @Test
    @Parameters
    public void decorate_should_saveExpectedMeasureTotal_when_usingAnyMetric(final Metric<Integer> metric, @SuppressWarnings("rawtypes") final Collection<Measure> measures) {
        Mockito.when(this.context.getChildrenMeasures(Matchers.eq(metric)))
            .thenReturn(measures);
        Mockito.when(this.context.getChildrenMeasures(AdditionalMatchers.not(Matchers.eq(metric))))
            .thenReturn(null);
        final WTFMeasuresDecorator sut = new WTFMeasuresDecorator(this.fs);
        sut.decorate(this.context);
        // There are 18 metrics in total
        Mockito.verify(this.context, Mockito.times(18))
            .getChildrenMeasures(Matchers.any(Metric.class));
        Mockito.verify(this.context, Mockito.times(1))
            .saveMeasure(Matchers.eq(WTFMetrics.WTF_COUNT), Matchers.eq(3d));
        Mockito.verifyNoMoreInteractions(this.context);
    }

    @SuppressWarnings("unused")
    private Object[] parametersForDecorate_should_saveExpectedMeasureTotal_when_usingAnyMetric() {
        return new Object[] {
            new Object[] {
                WTFMetrics.WTF_COUNT_ANTI_PATTERN,
                dummyMeasures(WTFMetrics.WTF_COUNT_ANTI_PATTERN)
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_BAD_DESIGN,
                dummyMeasures(WTFMetrics.WTF_COUNT_BAD_DESIGN)
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_INDECENT_EXPOSURE,
                dummyMeasures(WTFMetrics.WTF_COUNT_INDECENT_EXPOSURE)
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_MEANINGLESS_COMMENT,
                dummyMeasures(WTFMetrics.WTF_COUNT_MEANINGLESS_COMMENT)
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_MIDDLE_MAN,
                dummyMeasures(WTFMetrics.WTF_COUNT_MIDDLE_MAN)
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_ODDBALL_SOLUTION,
                dummyMeasures(WTFMetrics.WTF_COUNT_ODDBALL_SOLUTION)
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_OVERCOMPLICATED_ALGORITHM,
                dummyMeasures(WTFMetrics.WTF_COUNT_OVERCOMPLICATED_ALGORITHM)
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_PRIMITIVES_OBSESSION,
                dummyMeasures(WTFMetrics.WTF_COUNT_PRIMITIVES_OBSESSION)
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_REFUSED_BEQUEST,
                dummyMeasures(WTFMetrics.WTF_COUNT_REFUSED_BEQUEST)
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_SOLUTION_SPRAWL,
                dummyMeasures(WTFMetrics.WTF_COUNT_SOLUTION_SPRAWL)
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_SPECULATIVE_GENERALITY,
                dummyMeasures(WTFMetrics.WTF_COUNT_SPECULATIVE_GENERALITY)
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_UNCOMMUNICATIVE_NAME,
                dummyMeasures(WTFMetrics.WTF_COUNT_UNCOMMUNICATIVE_NAME)
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_USELESS_TEST,
                dummyMeasures(WTFMetrics.WTF_COUNT_USELESS_TEST)
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_WRONG_LOGIC,
                dummyMeasures(WTFMetrics.WTF_COUNT_WRONG_LOGIC)
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_HOW_COMMENT,
                dummyMeasures(WTFMetrics.WTF_COUNT_HOW_COMMENT)
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_MISSING_IMPLEMENTATION,
                dummyMeasures(WTFMetrics.WTF_COUNT_MISSING_IMPLEMENTATION)
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_NON_EXCEPTION,
                dummyMeasures(WTFMetrics.WTF_COUNT_NON_EXCEPTION)
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_WRONG_LANGUAGE,
                dummyMeasures(WTFMetrics.WTF_COUNT_WRONG_LANGUAGE)
            },
        };
    }

    @Test
    public void decorate_should_saveExpectedMeasureTotal_when_usingAllMetrics() {
        Mockito.when(this.context.getChildrenMeasures(Matchers.any(Metric.class)))
            .thenReturn(dummyMeasures(WTFMeasuresDecoratorTest.DUMMY_METRIC));
        final WTFMeasuresDecorator sut = new WTFMeasuresDecorator(this.fs);
        sut.decorate(this.context);
        // There are 18 metrics in total
        Mockito.verify(this.context, Mockito.times(18))
            .getChildrenMeasures(Matchers.any(Metric.class));
        Mockito.verify(this.context, Mockito.times(1))
            .saveMeasure(Matchers.eq(WTFMetrics.WTF_COUNT), Matchers.eq(54d));
        Mockito.verifyNoMoreInteractions(this.context);
    }

    @SuppressWarnings("rawtypes")
    private static final Collection<Measure> dummyMeasures(final Metric<Integer> metric) {
        final Measure measure = new Measure<Integer>(metric, 1d);
        return ImmutableList.<Measure> of(measure, measure, measure);
    }

    @Test
    public void toString_should_return_simpleClassName() {
        Assertions.assertThat(new WTFMeasuresDecorator(this.fs).toString())
            .isEqualTo(WTFMeasuresDecorator.class.getSimpleName());
    }
}
