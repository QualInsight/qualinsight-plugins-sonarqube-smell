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
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.measures.Measure;
import org.sonar.api.resources.Resource;
import com.qualinsight.plugins.sonarqube.smell.api.model.SmellType;
import com.qualinsight.plugins.sonarqube.smell.plugin.extension.SmellMeasurer;

@RunWith(MockitoJUnitRunner.class)
public class SmellMeasurerTest {

    private static final Integer EXPECTED_SMELL_TYPE_COUNT = SmellType.values().length;

    private static final Integer EXPECTED_DEBT_PER_SMELL_TYPE = 10;

    private static final Integer EXPECTED_SMELL_TYPE_DEBT = EXPECTED_SMELL_TYPE_COUNT * EXPECTED_DEBT_PER_SMELL_TYPE;

    @Mock
    InputFile inputFile;

    @Mock
    SensorContext sensorContext;

    @Test
    public void measure_with_annotatedFile_should_saveExpectedMeasures() {
        Mockito.when(this.inputFile.file())
            .thenReturn(new File("src/test/resources/SmellMeasurerTest_1.java"));
        final SmellMeasurer sut = new SmellMeasurer(this.sensorContext);
        sut.measure(this.inputFile);
        final ArgumentCaptor<Measure> captor = ArgumentCaptor.forClass(Measure.class);
        final Resource resource = Mockito.verify(this.sensorContext, Mockito.times(2))
            .getResource(Matchers.eq(this.inputFile));
        Mockito.verify(this.sensorContext, Mockito.times(SmellType.values().length + 2))
            .saveMeasure(Matchers.eq(resource), captor.capture());
        Mockito.verifyNoMoreInteractions(this.sensorContext);
        final List<Measure> measures = captor.getAllValues();
        int i = 0;
        while (i < EXPECTED_SMELL_TYPE_COUNT) {
            assertEquals(Integer.valueOf(1), measures.get(i++)
                .getIntValue());
        }
        assertEquals(EXPECTED_SMELL_TYPE_COUNT, measures.get(i++)
            .getIntValue());
        assertEquals(EXPECTED_SMELL_TYPE_DEBT, measures.get(i++)
            .getIntValue());
    }

    @Test
    public void measure_with_nonAnnotatedFile_should_not_saveAnyMeasure() {
        Mockito.when(this.inputFile.file())
            .thenReturn(new File("src/test/resources/SmellMeasurerTest_2.java"));
        final SmellMeasurer sut = new SmellMeasurer(this.sensorContext);
        sut.measure(this.inputFile);
        final ArgumentCaptor<Measure> captor = ArgumentCaptor.forClass(Measure.class);
        final Resource resource = Mockito.verify(this.sensorContext, Mockito.times(2))
            .getResource(Matchers.eq(this.inputFile));
        Mockito.verify(this.sensorContext, Mockito.times(2))
            .saveMeasure(Matchers.eq(resource), captor.capture());
        Mockito.verifyNoMoreInteractions(this.sensorContext);
        final List<Measure> measures = captor.getAllValues();
        int i = 0;
        // 1 different metric should be saved 1 time but counted as 3
        assertEquals(Integer.valueOf(0), measures.get(i++)
            .getIntValue());
        assertEquals(Integer.valueOf(0), measures.get(i++)
            .getIntValue());
    }

    @Test
    public void measure_with_annotatedFile_should_saveExpectedMeasuresAfterHavingRegroupedThemBySmellType() {
        Mockito.when(this.inputFile.file())
            .thenReturn(new File("src/test/resources/SmellMeasurerTest_3.java"));
        final SmellMeasurer sut = new SmellMeasurer(this.sensorContext);
        sut.measure(this.inputFile);
        final ArgumentCaptor<Measure> captor = ArgumentCaptor.forClass(Measure.class);
        final Resource resource = Mockito.verify(this.sensorContext, Mockito.times(2))
            .getResource(Matchers.eq(this.inputFile));
        Mockito.verify(this.sensorContext, Mockito.times(3))
            .saveMeasure(Matchers.eq(resource), captor.capture());
        Mockito.verifyNoMoreInteractions(this.sensorContext);
        final List<Measure> measures = captor.getAllValues();
        int i = 0;
        // 1 different metric should be saved 1 time but counted as 3
        assertEquals(Integer.valueOf(3), measures.get(i++)
            .getIntValue());
        assertEquals(Integer.valueOf(3), measures.get(i++)
            .getIntValue());
        assertEquals(Integer.valueOf(30), measures.get(i++)
            .getIntValue());
    }

    @Test
    public void measure_with_annotatedFile_should_saveExpectedMeasuresAfterHavingDetectedAllPossibleAnnotations() {
        Mockito.when(this.inputFile.file())
            .thenReturn(new File("src/test/resources/SmellMeasurerTest_4.java"));
        final SmellMeasurer sut = new SmellMeasurer(this.sensorContext);
        sut.measure(this.inputFile);
        // 1 different metric should be saved 1 time but counted as 8
        final ArgumentCaptor<Measure> captor = ArgumentCaptor.forClass(Measure.class);
        final Resource resource = Mockito.verify(this.sensorContext, Mockito.times(2))
            .getResource(Matchers.eq(this.inputFile));
        Mockito.verify(this.sensorContext, Mockito.times(3))
            .saveMeasure(Matchers.eq(resource), captor.capture());
        Mockito.verifyNoMoreInteractions(this.sensorContext);
        final List<Measure> measures = captor.getAllValues();
        int i = 0;
        assertEquals(Integer.valueOf(8), measures.get(i++)
            .getIntValue());
        assertEquals(Integer.valueOf(8), measures.get(i++)
            .getIntValue());
        assertEquals(Integer.valueOf(80), measures.get(i++)
            .getIntValue());
    }

    @Test
    public void measure_with_annotatedFile_should_saveExpectedMeasuresThatHaveLineBreaksInAnnotations() {
        Mockito.when(this.inputFile.file())
            .thenReturn(new File("src/test/resources/SmellMeasurerTest_5.java"));
        final SmellMeasurer sut = new SmellMeasurer(this.sensorContext);
        sut.measure(this.inputFile);
        // different metrics should be saved, one for each SmellType
        final ArgumentCaptor<Measure> captor = ArgumentCaptor.forClass(Measure.class);
        final Resource resource = Mockito.verify(this.sensorContext, Mockito.times(2))
            .getResource(Matchers.eq(this.inputFile));
        // here +2 because of SMELL_COUNT + SMELL_DEBT measures
        Mockito.verify(this.sensorContext, Mockito.times(SmellType.values().length + 2))
            .saveMeasure(Matchers.eq(resource), captor.capture());
        Mockito.verifyNoMoreInteractions(this.sensorContext);
        final List<Measure> measures = captor.getAllValues();
        int i = 0;
        while (i < EXPECTED_SMELL_TYPE_COUNT) {
            assertEquals(Integer.valueOf(1), measures.get(i++)
                .getIntValue());
        }
        assertEquals(EXPECTED_SMELL_TYPE_COUNT, measures.get(i++)
            .getIntValue());
        assertEquals(EXPECTED_SMELL_TYPE_DEBT, measures.get(i++)
            .getIntValue());
    }

}
