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
import java.io.Serializable;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.measure.NewMeasure;
import org.sonar.api.measures.Metric;
import com.qualinsight.plugins.sonarqube.smell.api.model.SmellType;

@RunWith(MockitoJUnitRunner.class)
public class SmellMeasurerTest {

    private static final Integer EXPECTED_SMELL_TYPE_COUNT = SmellType.values().length;

    private static final Integer EXPECTED_DEBT_PER_SMELL_TYPE = 10;

    private static final Integer EXPECTED_SMELL_TYPE_DEBT = EXPECTED_SMELL_TYPE_COUNT * EXPECTED_DEBT_PER_SMELL_TYPE;

    @Mock
    InputFile inputFile;

    @Mock
    SensorContext sensorContext;

    @Mock
    NewMeasure<Serializable> measure;

    @Test
    public void measure_with_annotatedFile_should_saveExpectedMeasures() {
        Mockito.when(this.inputFile.file())
            .thenReturn(new File("src/test/resources/SmellMeasurerTest_1.java"));
        Mockito.when(this.sensorContext.newMeasure())
            .thenReturn(this.measure);
        Mockito.when(this.measure.forMetric(Mockito.any()))
            .thenReturn(this.measure);
        Mockito.when(this.measure.withValue(Mockito.any()))
            .thenReturn(this.measure);
        Mockito.when(this.measure.on(Mockito.any()))
            .thenReturn(this.measure);
        final SmellMeasurer sut = new SmellMeasurer(this.sensorContext);
        sut.measure(this.inputFile);
        final ArgumentCaptor<Metric> metricCaptor = ArgumentCaptor.forClass(Metric.class);
        final ArgumentCaptor<Serializable> valueCaptor = ArgumentCaptor.forClass(Serializable.class);
        // 2 because of : SMELL_COUNT, SMELL_DEBT
        Mockito.verify(this.sensorContext, Mockito.times(2 + SmellType.values().length))
            .newMeasure();
        Mockito.verify(this.measure, Mockito.times(2 + SmellType.values().length))
            .save();
        Mockito.verify(this.measure, Mockito.times(2 + SmellType.values().length))
            .forMetric(metricCaptor.capture());
        Mockito.verify(this.measure, Mockito.times(2 + SmellType.values().length))
            .withValue(valueCaptor.capture());
        Mockito.verify(this.measure, Mockito.times(2 + SmellType.values().length))
            .on(Mockito.any());
        final List<Metric> capturedMetrics = metricCaptor.getAllValues();
        final List<Serializable> capturedValues = valueCaptor.getAllValues();
        int i = 0;
        while (i < EXPECTED_SMELL_TYPE_COUNT) {
            assertEquals(Integer.valueOf(1), capturedValues.get(i++));
        }
        assertEquals(SmellMetrics.SMELL_COUNT, capturedMetrics.get(i));
        assertEquals(Integer.valueOf(EXPECTED_SMELL_TYPE_COUNT), capturedValues.get(i++));
        assertEquals(SmellMetrics.SMELL_DEBT, capturedMetrics.get(i));
        assertEquals(Long.valueOf(EXPECTED_SMELL_TYPE_DEBT), capturedValues.get(i++));
        Mockito.verifyNoMoreInteractions(this.sensorContext);
    }

    @Test
    public void measure_with_nonAnnotatedFile_should_not_saveAnyMeasure() {
        Mockito.when(this.inputFile.file())
            .thenReturn(new File("src/test/resources/SmellMeasurerTest_2.java"));
        Mockito.when(this.sensorContext.newMeasure())
            .thenReturn(this.measure);
        Mockito.when(this.measure.forMetric(Mockito.any()))
            .thenReturn(this.measure);
        Mockito.when(this.measure.withValue(Mockito.any()))
            .thenReturn(this.measure);
        Mockito.when(this.measure.on(Mockito.any()))
            .thenReturn(this.measure);
        final SmellMeasurer sut = new SmellMeasurer(this.sensorContext);
        sut.measure(this.inputFile);
        final ArgumentCaptor<Serializable> valueCaptor = ArgumentCaptor.forClass(Serializable.class);
        // 2 because of : SMELL_COUNT, SMELL_DEBT
        Mockito.verify(this.sensorContext, Mockito.times(2))
            .newMeasure();
        Mockito.verify(this.measure, Mockito.times(2))
            .save();
        Mockito.verify(this.measure, Mockito.times(2))
            .forMetric(Mockito.any());
        Mockito.verify(this.measure, Mockito.times(2))
            .withValue(valueCaptor.capture());
        Mockito.verify(this.measure, Mockito.times(2))
            .on(Mockito.any());
        final List<Serializable> capturedValues = valueCaptor.getAllValues();
        int i = 0;
        assertEquals(Integer.valueOf(0), capturedValues.get(i++));
        assertEquals(Long.valueOf(0), capturedValues.get(i++));
        Mockito.verifyNoMoreInteractions(this.sensorContext);
    }

    @Test
    public void measure_with_annotatedFile_should_saveExpectedMeasuresAfterHavingRegroupedThemBySmellType() {
        Mockito.when(this.inputFile.file())
            .thenReturn(new File("src/test/resources/SmellMeasurerTest_3.java"));
        Mockito.when(this.sensorContext.newMeasure())
            .thenReturn(this.measure);
        Mockito.when(this.measure.forMetric(Mockito.any()))
            .thenReturn(this.measure);
        Mockito.when(this.measure.withValue(Mockito.any()))
            .thenReturn(this.measure);
        Mockito.when(this.measure.on(Mockito.any()))
            .thenReturn(this.measure);
        final SmellMeasurer sut = new SmellMeasurer(this.sensorContext);
        sut.measure(this.inputFile);
        final ArgumentCaptor<Serializable> valueCaptor = ArgumentCaptor.forClass(Serializable.class);
        Mockito.verify(this.sensorContext, Mockito.times(3))
            .newMeasure();
        Mockito.verify(this.measure, Mockito.times(3))
            .save();
        Mockito.verify(this.measure, Mockito.times(3))
            .forMetric(Mockito.any());
        Mockito.verify(this.measure, Mockito.times(3))
            .withValue(valueCaptor.capture());
        Mockito.verify(this.measure, Mockito.times(3))
            .on(Mockito.any());
        final List<Serializable> capturedValues = valueCaptor.getAllValues();
        int i = 0;
        assertEquals(Integer.valueOf(3), capturedValues.get(i++));
        assertEquals(Integer.valueOf(3), capturedValues.get(i++));
        assertEquals(Long.valueOf(30), capturedValues.get(i++));
        Mockito.verifyNoMoreInteractions(this.sensorContext);
    }

    @Test
    public void measure_with_annotatedFile_should_saveExpectedMeasuresAfterHavingDetectedAllPossibleAnnotations() {
        Mockito.when(this.inputFile.file())
            .thenReturn(new File("src/test/resources/SmellMeasurerTest_4.java"));
        Mockito.when(this.sensorContext.newMeasure())
            .thenReturn(this.measure);
        Mockito.when(this.measure.forMetric(Mockito.any()))
            .thenReturn(this.measure);
        Mockito.when(this.measure.withValue(Mockito.any()))
            .thenReturn(this.measure);
        Mockito.when(this.measure.on(Mockito.any()))
            .thenReturn(this.measure);
        final SmellMeasurer sut = new SmellMeasurer(this.sensorContext);
        sut.measure(this.inputFile);
        // 1 different metric should be saved 1 time but counted as 8
        final ArgumentCaptor<Serializable> valueCaptor = ArgumentCaptor.forClass(Serializable.class);
        // 2 because of : SMELL_COUNT, SMELL_DEBT
        Mockito.verify(this.sensorContext, Mockito.times(3))
            .newMeasure();
        Mockito.verify(this.measure, Mockito.times(3))
            .save();
        Mockito.verify(this.measure, Mockito.times(3))
            .forMetric(Mockito.any());
        Mockito.verify(this.measure, Mockito.times(3))
            .withValue(valueCaptor.capture());
        Mockito.verify(this.measure, Mockito.times(3))
            .on(Mockito.any());
        final List<Serializable> capturedValues = valueCaptor.getAllValues();
        int i = 0;
        assertEquals(Integer.valueOf(8), capturedValues.get(i++));
        assertEquals(Integer.valueOf(8), capturedValues.get(i++));
        assertEquals(Long.valueOf(80), capturedValues.get(i++));
        Mockito.verifyNoMoreInteractions(this.sensorContext);
    }

    @Test
    public void measure_with_annotatedFile_should_saveExpectedMeasuresThatHaveLineBreaksInAnnotations() {
        Mockito.when(this.inputFile.file())
            .thenReturn(new File("src/test/resources/SmellMeasurerTest_5.java"));
        Mockito.when(this.sensorContext.newMeasure())
            .thenReturn(this.measure);
        Mockito.when(this.measure.forMetric(Mockito.any()))
            .thenReturn(this.measure);
        Mockito.when(this.measure.withValue(Mockito.any()))
            .thenReturn(this.measure);
        Mockito.when(this.measure.on(Mockito.any()))
            .thenReturn(this.measure);
        final SmellMeasurer sut = new SmellMeasurer(this.sensorContext);
        sut.measure(this.inputFile);
        // different metrics should be saved, one for each SmellType
        final ArgumentCaptor<Metric> metricCaptor = ArgumentCaptor.forClass(Metric.class);
        final ArgumentCaptor<Serializable> valueCaptor = ArgumentCaptor.forClass(Serializable.class);
        // 2 because of : SMELL_COUNT, SMELL_DEBT
        Mockito.verify(this.sensorContext, Mockito.times(2 + SmellType.values().length))
            .newMeasure();
        Mockito.verify(this.measure, Mockito.times(2 + SmellType.values().length))
            .save();
        Mockito.verify(this.measure, Mockito.times(2 + SmellType.values().length))
            .forMetric(metricCaptor.capture());
        Mockito.verify(this.measure, Mockito.times(2 + SmellType.values().length))
            .withValue(valueCaptor.capture());
        Mockito.verify(this.measure, Mockito.times(2 + SmellType.values().length))
            .on(Mockito.any());
        final List<Metric> capturedMetrics = metricCaptor.getAllValues();
        final List<Serializable> capturedValues = valueCaptor.getAllValues();
        int i = 0;
        while (i < EXPECTED_SMELL_TYPE_COUNT) {
            assertEquals(Integer.valueOf(1), capturedValues.get(i++));
        }
        assertEquals(SmellMetrics.SMELL_COUNT, capturedMetrics.get(i));
        assertEquals(Integer.valueOf(EXPECTED_SMELL_TYPE_COUNT), capturedValues.get(i++));
        assertEquals(SmellMetrics.SMELL_DEBT, capturedMetrics.get(i));
        assertEquals(Long.valueOf(EXPECTED_SMELL_TYPE_DEBT), capturedValues.get(i++));
        Mockito.verifyNoMoreInteractions(this.sensorContext);
    }

}
