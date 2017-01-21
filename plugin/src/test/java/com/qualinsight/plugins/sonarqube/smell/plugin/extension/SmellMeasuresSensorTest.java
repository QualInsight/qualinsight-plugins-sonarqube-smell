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

import static org.junit.Assert.assertEquals;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import com.google.common.collect.ImmutableList;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.sonar.api.batch.fs.FilePredicate;
import org.sonar.api.batch.fs.FilePredicates;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.measure.NewMeasure;
import org.sonar.api.measures.Metric;
import org.sonar.plugins.java.Java;

@RunWith(MockitoJUnitRunner.class)
public class SmellMeasuresSensorTest {

    @Mock
    public FileSystem fs;

    @Mock
    public FilePredicates predicates;

    @Mock
    public FilePredicate predicate;

    @Mock
    public InputFile inputFile;

    @Mock
    public NewMeasure<Serializable> measure;

    @Mock
    public SensorContext context;

    @Before
    public void setUp() {
        Mockito.when(this.fs.predicates())
            .thenReturn(this.predicates);
        Mockito.when(this.predicates.hasLanguage(Matchers.eq(Java.KEY)))
            .thenReturn(this.predicate);
    }

    @Test
    public void analyse_should_saveMeasures() throws IOException {
        final SmellMeasuresSensor sut = new SmellMeasuresSensor(this.fs);
        final File testFile = new File("src/test/resources/SmellMeasuresSensorTest_1.java");
        Mockito.when(this.fs.inputFiles(this.predicate))
            .thenReturn(ImmutableList.<InputFile> of(this.inputFile));
        Mockito.when(this.inputFile.file())
            .thenReturn(testFile);
        Mockito.when(this.inputFile.absolutePath())
            .thenReturn(testFile.getAbsolutePath());
        Mockito.when(this.context.newMeasure())
            .thenReturn(this.measure);
        Mockito.when(this.measure.forMetric(Mockito.any()))
            .thenReturn(this.measure);
        Mockito.when(this.measure.withValue(Mockito.any()))
            .thenReturn(this.measure);
        Mockito.when(this.measure.on(Mockito.any()))
            .thenReturn(this.measure);
        sut.execute(this.context);
        Mockito.verify(this.inputFile, Mockito.times(1))
            .file();
        final ArgumentCaptor<Metric> metricCaptor = ArgumentCaptor.forClass(Metric.class);
        final ArgumentCaptor<Serializable> valueCaptor = ArgumentCaptor.forClass(Serializable.class);
        // 3 because of : SMELL_COUNT_ANTI_PATTERN, SMELL_COUNT, SMELL_DEBT
        Mockito.verify(this.context, Mockito.times(3))
            .newMeasure();
        Mockito.verify(this.measure, Mockito.times(3))
            .save();
        Mockito.verify(this.measure, Mockito.times(3))
            .forMetric(metricCaptor.capture());
        final List<Metric> capturedMetrics = metricCaptor.getAllValues();
        assertEquals(SmellMetrics.SMELL_COUNT_ANTI_PATTERN, capturedMetrics.get(0));
        assertEquals(SmellMetrics.SMELL_COUNT, capturedMetrics.get(1));
        assertEquals(SmellMetrics.SMELL_DEBT, capturedMetrics.get(2));
        Mockito.verify(this.measure, Mockito.times(3))
            .withValue(valueCaptor.capture());
        final List<Serializable> capturedValues = valueCaptor.getAllValues();
        assertEquals(Integer.valueOf(7), capturedValues.get(0));
        assertEquals(Integer.valueOf(7), capturedValues.get(1));
        assertEquals(Long.valueOf(70), capturedValues.get(2));
        Mockito.verify(this.measure, Mockito.times(3))
            .on(Mockito.any());
        Mockito.verifyNoMoreInteractions(this.context);
    }

    @Test
    public void toString_should_return_simpleClassName() {
        Assertions.assertThat(new SmellMeasuresSensor(this.fs).toString())
            .isEqualTo(SmellMeasuresSensor.class.getSimpleName());
    }

}
