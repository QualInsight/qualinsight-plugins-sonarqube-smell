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
import java.io.IOException;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.batch.fs.FilePredicate;
import org.sonar.api.batch.fs.FilePredicates;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.plugins.java.Java;
import com.google.common.collect.ImmutableList;

@RunWith(MockitoJUnitRunner.class)
public class WTFMeasuresSensorTest {

    @Mock
    public FileSystem fs;

    @Mock
    public FilePredicates predicates;

    @Mock
    public FilePredicate predicate;

    @Mock
    public InputFile inputFile;

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
        final WTFMeasuresSensor sut = new WTFMeasuresSensor(this.fs);
        final File testFile = new File("src/test/resources/WTFMeasuresSensorTest_1.java");
        Mockito.when(this.fs.inputFiles(this.predicate))
            .thenReturn(ImmutableList.<InputFile> of(this.inputFile));
        Mockito.when(this.inputFile.file())
            .thenReturn(testFile);
        sut.analyse(null, this.context);
        Mockito.verify(this.inputFile, Mockito.times(1))
            .file();
        Mockito.verify(this.context, Mockito.times(1))
            .saveMeasure(Matchers.eq(this.inputFile), Matchers.eq(WTFMetrics.WTF_COUNT_ANTI_PATTERN), Matchers.eq(7d));
        Mockito.verify(this.context, Mockito.times(1))
            .saveMeasure(Matchers.eq(this.inputFile), Matchers.eq(WTFMetrics.WTF_DEBT), Matchers.eq(70d));
        Mockito.verifyNoMoreInteractions(this.context);
    }

    @Test
    public void toString_should_return_simpleClassName() {
        Assertions.assertThat(new WTFMeasuresSensor(this.fs).toString())
            .isEqualTo(WTFMeasuresSensor.class.getSimpleName());
    }

}
