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

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.common.io.Files;
import org.apache.commons.io.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.measures.Measure;
import com.qualinsight.plugins.sonarqube.smell.api.annotation.Smell;
import com.qualinsight.plugins.sonarqube.smell.api.model.SmellType;

/**
 * Helper class that scans {@link InputFile}s for the presence of {@link Smell} annotations and saves {@link Measure}s accordingly.
 *
 * @author Michel Pawlak
 */
public final class SmellMeasurer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmellMeasurer.class);

    private static final String EMPTY_FILE_CONTENT = "";

    private static final String SMELL_ANNOTATION_TYPE_DETECTION_REGULAR_EXPRESSION = "@(com\\.qualinsight\\.plugins\\.sonarqube\\.smell\\.api\\.annotation\\.)?Smell\\((?s).*?type\\s?=\\s?(SmellType\\.)?([A-Z_]+).*?\\)";

    private static final String SMELL_ANNOTATION_DEBT_DETECTION_REGULAR_EXPRESSION = "@(com\\.qualinsight\\.plugins\\.sonarqube\\.smell\\.api\\.annotation\\.)?Smell\\((?s).*?minutes\\s?=\\s?(\\d+).*?\\)";

    private SensorContext context;

    /**
     * {@link SmellMeasurer} constructor
     *
     * @param context context to be used to save {@link Measure}.
     */
    public SmellMeasurer(final SensorContext context) {
        this.context = context;
    }

    /**
     * Launches the measuring of a give {@link InputFile}.
     *
     * @param inputFile {@link InputFile} to be scanned for {@link Smell} annotation presence.
     */
    public void measure(final InputFile inputFile) {
        final String fileContent = getFileAsString(inputFile.file(), Charsets.UTF_8);
        measureSmellTypes(inputFile, fileContent);
        measureSmellDebt(inputFile, fileContent);
    }

    private void measureSmellTypes(final InputFile inputFile, final String fileContent) {
        final Map<SmellType, Double> fileMeasures = parseAnnotations(fileContent);
        for (final Entry<SmellType, Double> measure : fileMeasures.entrySet()) {
            final Double value = measure.getValue();
            this.context.saveMeasure(inputFile, SmellMetrics.metricFor(measure.getKey()), value);
        }
    }

    private Map<SmellType, Double> parseAnnotations(final String fileContent) {
        final Pattern pattern = Pattern.compile(SMELL_ANNOTATION_TYPE_DETECTION_REGULAR_EXPRESSION, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(fileContent);
        final Map<SmellType, Double> fileMeasures = new EnumMap<SmellType, Double>(SmellType.class);
        while (matcher.find()) {
            final SmellType type = SmellType.valueOf(matcher.group(3));
            if (fileMeasures.containsKey(type)) {
                fileMeasures.put(type, fileMeasures.get(type) + 1);
            } else {
                fileMeasures.put(type, 1.0);
            }
        }
        return fileMeasures;
    }

    private void measureSmellDebt(final InputFile inputFile, final String fileContent) {
        final Pattern pattern = Pattern.compile(SMELL_ANNOTATION_DEBT_DETECTION_REGULAR_EXPRESSION, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(fileContent);
        Double debt = 0.0;
        while (matcher.find()) {
            debt += Integer.valueOf(matcher.group(2));
        }
        this.context.saveMeasure(inputFile, SmellMetrics.SMELL_DEBT, debt);
    }

    private static String getFileAsString(final File file, final Charset charset) {
        try {
            return Files.toString(file, charset);
        } catch (final IOException e) {
            LOGGER.warn("A problem occured while reading file.", e);
            return EMPTY_FILE_CONTENT;
        }
    }
}
