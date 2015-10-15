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

import java.util.List;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Formula;
import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metric.ValueType;
import org.sonar.api.measures.SumChildValuesFormula;
import com.qualinsight.plugins.sonarqube.smell.api.model.SmellType;
import com.qualinsight.plugins.sonarqube.smell.plugin.extension.SmellMetrics;

@RunWith(JUnitParamsRunner.class)
public class SmellMetricsTest {

    private static final int SMELL_CORE_METRICS_COUNT = 2;

    private static final int SMELL_TYPE_COUNT = SmellType.values().length;

    private static final int EXPECTED_METRICS_COUNT = SMELL_TYPE_COUNT + SMELL_CORE_METRICS_COUNT;

    @Test
    public void parameterizedTests_should_useCorrectMetricsCount() {
        final SoftAssertions softly = new SoftAssertions();
        softly.assertThat(parametersForMetricFor_should_return_expectedMetric().length)
            .as("Count of tested entries of SmellType to SmellMetric map")
            .isEqualTo(SMELL_TYPE_COUNT);
        softly.assertThat(parametersForGetMetrics_should_return_correctlyConfiguredMetrics().length)
            .as("Count of tested metrics configurations")
            .isEqualTo(EXPECTED_METRICS_COUNT);
        softly.assertAll();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getMetrics_should_return_expectedMetrics() {
        final SmellMetrics sut = new SmellMetrics();
        final List actualMetrics = sut.getMetrics();
        final SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actualMetrics)
            .hasSize(EXPECTED_METRICS_COUNT);
        softly.assertThat(actualMetrics)
            .contains(SmellMetrics.SMELL_COUNT);
        softly.assertThat(actualMetrics)
            .contains(SmellMetrics.SMELL_DEBT);
        softly.assertThat(actualMetrics)
            .contains(SmellMetrics.SMELL_COUNT_ABBREVIATIONS_USAGE);
        softly.assertThat(actualMetrics)
            .contains(SmellMetrics.SMELL_COUNT_ANTI_PATTERN);
        softly.assertThat(actualMetrics)
            .contains(SmellMetrics.SMELL_COUNT_BAD_DESIGN);
        softly.assertThat(actualMetrics)
            .contains(SmellMetrics.SMELL_COUNT_BAD_FRAMEWORK_USAGE);
        softly.assertThat(actualMetrics)
            .contains(SmellMetrics.SMELL_COUNT_BAD_LOGGING);
        softly.assertThat(actualMetrics)
            .contains(SmellMetrics.SMELL_COUNT_HOW_COMMENT);
        softly.assertThat(actualMetrics)
            .contains(SmellMetrics.SMELL_COUNT_INDECENT_EXPOSURE);
        softly.assertThat(actualMetrics)
            .contains(SmellMetrics.SMELL_COUNT_MEANINGLESS_COMMENT);
        softly.assertThat(actualMetrics)
            .contains(SmellMetrics.SMELL_COUNT_MIDDLE_MAN);
        softly.assertThat(actualMetrics)
            .contains(SmellMetrics.SMELL_COUNT_MISSING_IMPLEMENTATION);
        softly.assertThat(actualMetrics)
            .contains(SmellMetrics.SMELL_COUNT_MULTIPLE_RESPONSIBILITIES);
        softly.assertThat(actualMetrics)
            .contains(SmellMetrics.SMELL_COUNT_NON_EXCEPTION);
        softly.assertThat(actualMetrics)
            .contains(SmellMetrics.SMELL_COUNT_ODDBALL_SOLUTION);
        softly.assertThat(actualMetrics)
            .contains(SmellMetrics.SMELL_COUNT_OVERCOMPLICATED_ALGORITHM);
        softly.assertThat(actualMetrics)
            .contains(SmellMetrics.SMELL_COUNT_PRIMITIVES_OBSESSION);
        softly.assertThat(actualMetrics)
            .contains(SmellMetrics.SMELL_COUNT_REFUSED_BEQUEST);
        softly.assertThat(actualMetrics)
            .contains(SmellMetrics.SMELL_COUNT_REINVENTED_WHEEL);
        softly.assertThat(actualMetrics)
            .contains(SmellMetrics.SMELL_COUNT_SOLUTION_SPRAWL);
        softly.assertThat(actualMetrics)
            .contains(SmellMetrics.SMELL_COUNT_SPECULATIVE_GENERALITY);
        softly.assertThat(actualMetrics)
            .contains(SmellMetrics.SMELL_COUNT_UNCOMMUNICATIVE_NAME);
        softly.assertThat(actualMetrics)
            .contains(SmellMetrics.SMELL_COUNT_USELESS_TEST);
        softly.assertThat(actualMetrics)
            .contains(SmellMetrics.SMELL_COUNT_WRONG_LOGIC);
        softly.assertThat(actualMetrics)
            .contains(SmellMetrics.SMELL_COUNT_WRONG_LANGUAGE);
        softly.assertAll();
    }

    @Test
    @Parameters
    public void metricFor_should_return_expectedMetric(final SmellType type, final Metric<Integer> expectedMetric) {
        Assertions.assertThat(SmellMetrics.metricFor(type))
            .isEqualTo(expectedMetric);
    }

    private Object[] parametersForMetricFor_should_return_expectedMetric() {
        return new Object[] {
            new Object[] {
                SmellType.ABBREVIATIONS_USAGE,
                SmellMetrics.SMELL_COUNT_ABBREVIATIONS_USAGE
            },
            new Object[] {
                SmellType.ANTI_PATTERN,
                SmellMetrics.SMELL_COUNT_ANTI_PATTERN
            },
            new Object[] {
                SmellType.BAD_DESIGN,
                SmellMetrics.SMELL_COUNT_BAD_DESIGN
            },
            new Object[] {
                SmellType.BAD_FRAMEWORK_USAGE,
                SmellMetrics.SMELL_COUNT_BAD_FRAMEWORK_USAGE
            },
            new Object[] {
                SmellType.BAD_LOGGING,
                SmellMetrics.SMELL_COUNT_BAD_LOGGING
            },
            new Object[] {
                SmellType.HOW_COMMENT,
                SmellMetrics.SMELL_COUNT_HOW_COMMENT
            },
            new Object[] {
                SmellType.INDECENT_EXPOSURE,
                SmellMetrics.SMELL_COUNT_INDECENT_EXPOSURE
            },
            new Object[] {
                SmellType.MEANINGLESS_COMMENT,
                SmellMetrics.SMELL_COUNT_MEANINGLESS_COMMENT
            },
            new Object[] {
                SmellType.MIDDLE_MAN,
                SmellMetrics.SMELL_COUNT_MIDDLE_MAN
            },
            new Object[] {
                SmellType.MISSING_IMPLEMENTATION,
                SmellMetrics.SMELL_COUNT_MISSING_IMPLEMENTATION
            },
            new Object[] {
                SmellType.MULTIPLE_RESPONSIBILITIES,
                SmellMetrics.SMELL_COUNT_MULTIPLE_RESPONSIBILITIES
            },
            new Object[] {
                SmellType.NON_EXCEPTION,
                SmellMetrics.SMELL_COUNT_NON_EXCEPTION
            },
            new Object[] {
                SmellType.ODDBALL_SOLUTION,
                SmellMetrics.SMELL_COUNT_ODDBALL_SOLUTION
            },
            new Object[] {
                SmellType.OVERCOMPLICATED_ALGORITHM,
                SmellMetrics.SMELL_COUNT_OVERCOMPLICATED_ALGORITHM
            },
            new Object[] {
                SmellType.PRIMITIVES_OBSESSION,
                SmellMetrics.SMELL_COUNT_PRIMITIVES_OBSESSION
            },
            new Object[] {
                SmellType.REFUSED_BEQUEST,
                SmellMetrics.SMELL_COUNT_REFUSED_BEQUEST
            },
            new Object[] {
                SmellType.REINVENTED_WHEEL,
                SmellMetrics.SMELL_COUNT_REINVENTED_WHEEL
            },
            new Object[] {
                SmellType.SOLUTION_SPRAWL,
                SmellMetrics.SMELL_COUNT_SOLUTION_SPRAWL
            },
            new Object[] {
                SmellType.SPECULATIVE_GENERALITY,
                SmellMetrics.SMELL_COUNT_SPECULATIVE_GENERALITY
            },
            new Object[] {
                SmellType.UNCOMMUNICATIVE_NAME,
                SmellMetrics.SMELL_COUNT_UNCOMMUNICATIVE_NAME
            },
            new Object[] {
                SmellType.USELESS_TEST,
                SmellMetrics.SMELL_COUNT_USELESS_TEST
            },
            new Object[] {
                SmellType.WRONG_LANGUAGE,
                SmellMetrics.SMELL_COUNT_WRONG_LANGUAGE
            },
            new Object[] {
                SmellType.WRONG_LOGIC,
                SmellMetrics.SMELL_COUNT_WRONG_LOGIC
            },
        };
    }

    @Test
    @Parameters
    public void getMetrics_should_return_correctlyConfiguredMetrics(final Metric<Integer> metric, final String expectedKey, final String expectedName, final ValueType expectedValueType,
        final Double expectedBestValue, final String expectedDescription, final Integer expectedDirection, final String expectedDomain, final Class<? extends Formula> expectedFormula) {
        final SoftAssertions softly = new SoftAssertions();
        softly.assertThat(metric.getKey())
            .isEqualTo(expectedKey);
        softly.assertThat(metric.getName())
            .isEqualTo(expectedName);
        softly.assertThat(metric.getType())
            .isEqualTo(expectedValueType);
        softly.assertThat(metric.getBestValue())
            .isEqualTo(expectedBestValue);
        softly.assertThat(metric.getDescription())
            .isEqualTo(expectedDescription);
        softly.assertThat(metric.getDirection())
            .isEqualTo(expectedDirection);
        softly.assertThat(metric.getDomain())
            .isEqualTo(expectedDomain);
        softly.assertThat(metric.getFormula())
            .isExactlyInstanceOf(expectedFormula);
        softly.assertAll();
    }

    private Object[] parametersForGetMetrics_should_return_correctlyConfiguredMetrics() {
        return new Object[] {
            new Object[] {
                SmellMetrics.SMELL_COUNT,
                "SMELL_COUNT",
                "Smells count",
                ValueType.INT,
                0d,
                "Total number of reported code smells.",
                Metric.DIRECTION_WORST,
                SmellMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                SmellMetrics.SMELL_DEBT,
                "SMELL_DEBT",
                "Debt",
                ValueType.WORK_DUR,
                0d,
                "Technical debt reported by developers.",
                Metric.DIRECTION_WORST,
                CoreMetrics.DOMAIN_TECHNICAL_DEBT,
                SumChildValuesFormula.class
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_ANTI_PATTERN,
                "SMELL_COUNT_ANTI_PATTERN",
                "Anti-pattern count",
                ValueType.INT,
                0d,
                "Number of anti-patterns reported by developers.",
                Metric.DIRECTION_WORST,
                SmellMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_BAD_DESIGN,
                "SMELL_COUNT_BAD_DESIGN",
                "Bad design count",
                ValueType.INT,
                0d,
                "Number of bad designs reported by developers.",
                Metric.DIRECTION_WORST,
                SmellMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_INDECENT_EXPOSURE,
                "SMELL_COUNT_INDECENT_EXPOSURE",
                "Indecent exposure count",
                ValueType.INT,
                0d,
                "Number of indecent exposures reported by developers.",
                Metric.DIRECTION_WORST,
                SmellMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_MEANINGLESS_COMMENT,
                "SMELL_COUNT_MEANINGLESS_COMMENT",
                "Meaningless comment count",
                ValueType.INT,
                0d,
                "Number of meaningless comments reported by developers.",
                Metric.DIRECTION_WORST,
                SmellMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_MIDDLE_MAN,
                "SMELL_COUNT_MIDDLE_MAN",
                "Middle man count",
                ValueType.INT,
                0d,
                "Number of middle men reported by developers.",
                Metric.DIRECTION_WORST,
                SmellMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_MULTIPLE_RESPONSIBILITIES,
                "SMELL_COUNT_MULTIPLE_RESPONSIBILITIES",
                "Multiple responsibilities count",
                ValueType.INT,
                0d,
                "Number of multiple responsibilities reported by developers.",
                Metric.DIRECTION_WORST,
                SmellMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_ODDBALL_SOLUTION,
                "SMELL_COUNT_ODDBALL_SOLUTION",
                "Oddball solution count",
                ValueType.INT,
                0d,
                "Number of oddball solutions reported by developers.",
                Metric.DIRECTION_WORST,
                SmellMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_OVERCOMPLICATED_ALGORITHM,
                "SMELL_COUNT_OVERCOMPLICATED_ALGORITHM",
                "Overcomplicated algorithm count",
                ValueType.INT,
                0d,
                "Number of overcomplicated algorithms reported by developers.",
                Metric.DIRECTION_WORST,
                SmellMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_PRIMITIVES_OBSESSION,
                "SMELL_COUNT_PRIMITIVES_OBSESSION",
                "Primitives obsession count",
                ValueType.INT,
                0d,
                "Number of primitives obsessions reported by developers.",
                Metric.DIRECTION_WORST,
                SmellMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_REFUSED_BEQUEST,
                "SMELL_COUNT_REFUSED_BEQUEST",
                "Refused bequest count",
                ValueType.INT,
                0d,
                "Number of refused bequests reported by developers.",
                Metric.DIRECTION_WORST,
                SmellMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_SOLUTION_SPRAWL,
                "SMELL_COUNT_SOLUTION_SPRAWL",
                "Solution sprawl count",
                ValueType.INT,
                0d,
                "Number of solution sprawls reported by developers.",
                Metric.DIRECTION_WORST,
                SmellMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_SPECULATIVE_GENERALITY,
                "SMELL_COUNT_SPECULATIVE_GENERALITY",
                "Speculative generality count",
                ValueType.INT,
                0d,
                "Number of speculative generalities reported by developers.",
                Metric.DIRECTION_WORST,
                SmellMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_UNCOMMUNICATIVE_NAME,
                "SMELL_COUNT_UNCOMMUNICATIVE_NAME",
                "Uncommunicative name count",
                ValueType.INT,
                0d,
                "Number of uncommunicative names reported by developers.",
                Metric.DIRECTION_WORST,
                SmellMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_USELESS_TEST,
                "SMELL_COUNT_USELESS_TEST",
                "Useless test count",
                ValueType.INT,
                0d,
                "Number of useless tests reported by developers.",
                Metric.DIRECTION_WORST,
                SmellMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_WRONG_LOGIC,
                "SMELL_COUNT_WRONG_LOGIC",
                "Wrong logic count",
                ValueType.INT,
                0d,
                "Number of wrong logics reported by developers.",
                Metric.DIRECTION_WORST,
                SmellMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_HOW_COMMENT,
                "SMELL_COUNT_HOW_COMMENT",
                "How comment count",
                ValueType.INT,
                0d,
                "Number of how comments reported by developers.",
                Metric.DIRECTION_WORST,
                SmellMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_MISSING_IMPLEMENTATION,
                "SMELL_COUNT_MISSING_IMPLEMENTATION",
                "Missing implementation count",
                ValueType.INT,
                0d,
                "Number of missing implementations reported by developers.",
                Metric.DIRECTION_WORST,
                SmellMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_NON_EXCEPTION,
                "SMELL_COUNT_NON_EXCEPTION",
                "Non exception count",
                ValueType.INT,
                0d,
                "Number of non exceptions reported by developers.",
                Metric.DIRECTION_WORST,
                SmellMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_WRONG_LANGUAGE,
                "SMELL_COUNT_WRONG_LANGUAGE",
                "Wrong language count",
                ValueType.INT,
                0d,
                "Number of wrong languages reported by developers.",
                Metric.DIRECTION_WORST,
                SmellMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_ABBREVIATIONS_USAGE,
                "SMELL_COUNT_ABBREVIATIONS_USAGE",
                "Abbreviations usage count",
                ValueType.INT,
                0d,
                "Number of abbreviations usages reported by developers.",
                Metric.DIRECTION_WORST,
                SmellMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_BAD_FRAMEWORK_USAGE,
                "SMELL_COUNT_BAD_FRAMEWORK_USAGE",
                "Bad framework usage count",
                ValueType.INT,
                0d,
                "Number of bad framework usages reported by developers.",
                Metric.DIRECTION_WORST,
                SmellMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_BAD_LOGGING,
                "SMELL_COUNT_BAD_LOGGING",
                "Bad logging count",
                ValueType.INT,
                0d,
                "Number of bad loggings reported by developers.",
                Metric.DIRECTION_WORST,
                SmellMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                SmellMetrics.SMELL_COUNT_REINVENTED_WHEEL,
                "SMELL_COUNT_REINVENTED_WHEEL",
                "Reinvented wheel count",
                ValueType.INT,
                0d,
                "Number of reinvented wheels reported by developers.",
                Metric.DIRECTION_WORST,
                SmellMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
        };
    }
}
