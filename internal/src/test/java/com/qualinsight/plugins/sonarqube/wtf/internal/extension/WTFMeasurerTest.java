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

import java.io.File;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.measures.Metric;
import com.qualinsight.plugins.sonarqube.wtf.internal.extension.WTFMeasurer;

@RunWith(MockitoJUnitRunner.class)
public class WTFMeasurerTest {

    @Mock
    InputFile inputFile;

    @Mock
    SensorContext sensorContext;

    @Test
    public void measure_with_annotatedFile_should_saveExpectedMeasures() {
        Mockito.when(this.inputFile.file())
            .thenReturn(new File("src/test/resources/WTFMeasurerTest_1.java"));
        final WTFMeasurer sut = new WTFMeasurer(this.sensorContext);
        sut.measure(this.inputFile);
        // 14 different metrics should be saved, one for each WTFType
        Mockito.verify(this.sensorContext, Mockito.times(14))
            .saveMeasure(Matchers.eq(this.inputFile), Matchers.any(Metric.class), Matchers.eq(1d));
        // total debt should be saved once with sum of annotations minutes
        Mockito.verify(this.sensorContext, Mockito.times(1))
            .saveMeasure(Matchers.eq(this.inputFile), Matchers.any(Metric.class), Matchers.eq(140d));
        Mockito.verifyNoMoreInteractions(this.sensorContext);
    }

    @Test
    public void measure_with_nonAnnotatedFile_should_not_saveAnyMeasure() {
        Mockito.when(this.inputFile.file())
            .thenReturn(new File("src/test/resources/WTFMeasurerTest_2.java"));
        final WTFMeasurer sut = new WTFMeasurer(this.sensorContext);
        sut.measure(this.inputFile);
        Mockito.verify(this.sensorContext, Mockito.times(1))
            .saveMeasure(Matchers.eq(this.inputFile), Matchers.any(Metric.class), Matchers.eq(0d));
        Mockito.verifyNoMoreInteractions(this.sensorContext);
    }

    @Test
    public void measure_with_annotatedFile_should_saveExpectedMeasuresAfterHavingRegroupedThemByWTFType() {
        Mockito.when(this.inputFile.file())
            .thenReturn(new File("src/test/resources/WTFMeasurerTest_3.java"));
        final WTFMeasurer sut = new WTFMeasurer(this.sensorContext);
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
            .thenReturn(new File("src/test/resources/WTFMeasurerTest_4.java"));
        final WTFMeasurer sut = new WTFMeasurer(this.sensorContext);
        sut.measure(this.inputFile);
        // 1 different metric should be saved 1 time but counted as 8
        Mockito.verify(this.sensorContext, Mockito.times(1))
            .saveMeasure(Matchers.eq(this.inputFile), Matchers.any(Metric.class), Matchers.eq(8d));
        // total debt should be saved once with sum of annotations minutes
        Mockito.verify(this.sensorContext, Mockito.times(1))
            .saveMeasure(Matchers.eq(this.inputFile), Matchers.any(Metric.class), Matchers.eq(80d));
        Mockito.verifyNoMoreInteractions(this.sensorContext);
    }

}
