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

import java.util.List;
import com.google.common.collect.Lists;
import org.sonar.api.batch.fs.FilePredicate;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import org.sonar.check.Rule;
import org.sonar.plugins.java.Java;
import com.qualinsight.plugins.sonarqube.smell.api.annotation.Smell;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.AbstractSmellCheck;

/**
 * {@link Sensor} that launches the scan of Java source files in order to detect {@link Smell} annotations regardless of the activation of the {@link AbstractSmellCheck} {@link Rule}.
 *
 * @author Michel Pawlak
 */
public final class SmellMeasuresSensor implements Sensor {

    private FileSystem fileSystem;

    private FilePredicate javaFilesPredicate;

    /**
     * {@link SmellMeasuresSensor} IoC constructor.
     *
     * @param fileSystem SonarQube {@link FileSystem}
     */
    public SmellMeasuresSensor(final FileSystem fileSystem) {
        this.fileSystem = fileSystem;
        this.javaFilesPredicate = this.fileSystem.predicates()
            .hasLanguage(Java.KEY);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    @Override
    public void describe(final SensorDescriptor descriptor) {
        descriptor.name(this.getClass()
            .getSimpleName());
        descriptor.onlyOnLanguage(Java.KEY);
    }

    @Override
    public void execute(final SensorContext context) {
        final List<InputFile> inputFiles = Lists.newArrayList(this.fileSystem.inputFiles(this.javaFilesPredicate));
        final SmellMeasurer measurer = new SmellMeasurer(context);
        for (final InputFile inputFile : inputFiles) {
            measurer.measure(inputFile);
        }
    }

}
