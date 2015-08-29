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
package com.qualinsight.plugins.sonarqube.wtf.internal.model;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.batch.fs.InputFile;
import com.google.common.io.Files;
import com.qualinsight.plugins.sonarqube.wtf.api.model.WTFType;
import com.qualinsight.plugins.sonarqube.wtf.internal.extension.WTFMetrics;

public class WTFMeasurer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WTFMeasurer.class);

    private static final String EMPTY_FILE_CONTENT = "";

    private static final String WTF_ANNOTATION_TYPE_DETECTION_REGULAR_EXPRESSION = "@(com\\.qualinsight\\.plugins\\.sonarqube\\.wtf\\.api\\.annotation\\.)?WTF\\(.*type\\s?=\\s?(WTFType\\.)?([A-Z_]+).*?\\)";

    private static final String WTF_ANNOTATION_DEBT_DETECTION_REGULAR_EXPRESSION = "@(com\\.qualinsight\\.plugins\\.sonarqube\\.wtf\\.api\\.annotation\\.)?WTF\\(.*minutes\\s?=\\s?(\\d+).*?\\)";

    private SensorContext context;

    public WTFMeasurer(final SensorContext context) {
        this.context = context;
    }

    public void measure(final InputFile inputFile) {
        final String fileContent = getFileAsString(inputFile.file(), Charsets.UTF_8);
        measureWTFTypes(inputFile, fileContent);
        measureWTFDebt(inputFile, fileContent);
    }

    private void measureWTFTypes(final InputFile inputFile, final String fileContent) {
        final Pattern pattern = Pattern.compile(WTF_ANNOTATION_TYPE_DETECTION_REGULAR_EXPRESSION, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(fileContent);
        final Map<WTFType, Double> fileMeasures = new HashMap<WTFType, Double>();
        while (matcher.find()) {
            final WTFType type = WTFType.valueOf(matcher.group(3));
            if (fileMeasures.containsKey(type)) {
                fileMeasures.put(type, fileMeasures.get(type) + 1);
            } else {
                fileMeasures.put(type, 1.0);
            }
        }
        for (final Entry<WTFType, Double> measure : fileMeasures.entrySet()) {
            final Double value = measure.getValue();
            switch (measure.getKey()) {
                case ANTI_PATTERN:
                    this.context.saveMeasure(inputFile, WTFMetrics.WTF_COUNT_ANTI_PATTERN, value);
                    break;
                case BAD_DESIGN:
                    this.context.saveMeasure(inputFile, WTFMetrics.WTF_COUNT_BAD_DESIGN, value);
                    break;
                case WRONG_LOGIC:
                    this.context.saveMeasure(inputFile, WTFMetrics.WTF_COUNT_WRONG_LOGIC, value);
                    break;
                case USELESS_TEST:
                    this.context.saveMeasure(inputFile, WTFMetrics.WTF_COUNT_USELESS_TEST, value);
                    break;
                case OVERCOMPLICATED_ALGORITHM:
                    this.context.saveMeasure(inputFile, WTFMetrics.WTF_COUNT_OVERCOMPLICATED_ALGORITHM, value);
                    break;
                default:
                    LOGGER.warn("Unexpected non measurable WTFType: {}", measure.getKey());
                    break;
            }
        }
    }

    private void measureWTFDebt(final InputFile inputFile, final String fileContent) {
        final Pattern pattern = Pattern.compile(WTF_ANNOTATION_DEBT_DETECTION_REGULAR_EXPRESSION, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(fileContent);
        Double debt = 0.0;
        while (matcher.find()) {
            debt += Integer.valueOf(matcher.group(2));
        }
        this.context.saveMeasure(inputFile, WTFMetrics.WTF_DEBT, debt);
    }

    private String getFileAsString(final File file, final Charset charset) {
        try {
            return Files.toString(file, charset);
        } catch (final IOException e) {
            LOGGER.warn("A problem occured while reading file.", e);
            return EMPTY_FILE_CONTENT;
        }
    }
}
