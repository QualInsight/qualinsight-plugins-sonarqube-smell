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

import java.io.File
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.measures.Metric;
import com.qualinsight.plugins.sonarqube.smell.api.model.SmellType;
import com.qualinsight.plugins.sonarqube.smell.plugin.extension.SmellMeasurer;

@RunWith(MockitoJUnitRunner.class)
public class SmellMeasurerTest {

    private static final double EXPECTED_SmellTYPE_COUNT = SmellType.values().length;

    private static final double EXPECTED_DEBT_PER_SMELL_TYPE = 10d;

    private static final double EXPECTED_SmellTYPE_DEBT = EXPECTED_SmellTYPE_COUNT * EXPECTED_DEBT_PER_SMELL_TYPE;

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
        // different metrics should be saved, one for each SmellType
        Mockito.verify(this.sensorContext, Mockito.times(SmellType.values().length))
            .saveMeasure(Matchers.eq(this.inputFile), Matchers.any(Metric.class), Matchers.eq(1d));
        // total debt should be saved once with sum of annotations minutes
        Mockito.verify(this.sensorContext, Mockito.times(1))
            .saveMeasure(Matchers.eq(this.inputFile), Matchers.any(Metric.class), Matchers.eq(EXPECTED_SmellTYPE_DEBT));
        Mockito.verifyNoMoreInteractions(this.sensorContext);
    }

    @Test
    public void measure_with_nonAnnotatedFile_should_not_saveAnyMeasure() {
        Mockito.when(this.inputFile.file())
            .thenReturn(new File("src/test/resources/SmellMeasurerTest_2.java"));
        final SmellMeasurer sut = new SmellMeasurer(this.sensorContext);
        sut.measure(this.inputFile);
        Mockito.verify(this.sensorContext, Mockito.times(1))
            .saveMeasure(Matchers.eq(this.inputFile), Matchers.any(Metric.class), Matchers.eq(0d));
        Mockito.verifyNoMoreInteractions(this.sensorContext);
    }

    @Test
    public void measure_with_annotatedFile_should_saveExpectedMeasuresAfterHavingRegroupedThemBySmellType() {
        Mockito.when(this.inputFile.file())
            .thenReturn(new File("src/test/resources/SmellMeasurerTest_3.java"));
        final SmellMeasurer sut = new SmellMeasurer(this.sensorContext);
        sut.measure(this.inputFile);
        // 1 different metric should be saved 1 time but counted as 3
        Mockito.verify(this.sensorContext, Mockito.times(1))
            .saveMeasure(Matchers.eq(this.inputFile), Matchers.any(Metric.class), Matchers.eq(3d));
        // total debt should be saved once with sum of annotations minutes
        Mockito.verify(this.sensorContext, Mockito.times(1))
            .saveMeasure(Matchers.eq(this.inputFile), Matchers.any(Metric.class), Matchers.eq(30d));
        Mockito.verifyNoMoreInteractions(this.sensorContext);
    }

    @Test
    public void measure_with_annotatedFile_should_saveExpectedMeasuresAfterHavingDetectedAllPossibleAnnotations() {
        Mockito.when(this.inputFile.file())
            .thenReturn(new File("src/test/resources/SmellMeasurerTest_4.java"));
        final SmellMeasurer sut = new SmellMeasurer(this.sensorContext);
        sut.measure(this.inputFile);
        // 1 different metric should be saved 1 time but counted as 8
        Mockito.verify(this.sensorContext, Mockito.times(1))
            .saveMeasure(Matchers.eq(this.inputFile), Matchers.any(Metric.class), Matchers.eq(8d));
        // total debt should be saved once with sum of annotations minutes
        Mockito.verify(this.sensorContext, Mockito.times(1))
            .saveMeasure(Matchers.eq(this.inputFile), Matchers.any(Metric.class), Matchers.eq(80d));
        Mockito.verifyNoMoreInteractions(this.sensorContext);
    }

    @Test
    public void measure_with_annotatedFile_should_saveExpectedMeasuresThatHaveLineBreaksInAnnotations() {
        Mockito.when(this.inputFile.file())
                .thenReturn(new File("src/test/resources/SmellMeasurerTest_5.java"));
        final SmellMeasurer sut = new SmellMeasurer(this.sensorContext);
        sut.measure(this.inputFile);
        // different metrics should be saved, one for each SmellType
        Mockito.verify(this.sensorContext, Mockito.times(SmellType.values().length))
                .saveMeasure(Matchers.eq(this.inputFile), Matchers.any(Metric.class), Matchers.eq(1d));
        // total debt should be saved once with sum of annotations minutes
        Mockito.verify(this.sensorContext, Mockito.times(1))
                .saveMeasure(Matchers.eq(this.inputFile), Matchers.any(Metric.class), Matchers.eq(EXPECTED_SmellTYPE_DEBT));
        Mockito.verifyNoMoreInteractions(this.sensorContext);
    }

}
