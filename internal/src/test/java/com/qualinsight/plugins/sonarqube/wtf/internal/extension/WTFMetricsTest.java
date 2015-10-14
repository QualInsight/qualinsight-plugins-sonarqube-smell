/*
 * qualinsight-plugins-sonarqube-wtf
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
package com.qualinsight.plugins.sonarqube.wtf.internal.extension;

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
import com.qualinsight.plugins.sonarqube.wtf.api.model.WTFType;

@RunWith(JUnitParamsRunner.class)
public class WTFMetricsTest {

    private static final int WTF_CORE_METRICS_COUNT = 2;

    private static final int WTF_TYPE_COUNT = WTFType.values().length;

    private static final int EXPECTED_METRICS_COUNT = WTF_TYPE_COUNT + WTF_CORE_METRICS_COUNT;

    @Test
    public void parameterizedTests_should_useCorrectMetricsCount() {
        final SoftAssertions softly = new SoftAssertions();
        softly.assertThat(parametersForMetricFor_should_return_expectedMetric().length)
            .as("Count of tested entries of WTFType to WTFMetric map")
            .isEqualTo(WTF_TYPE_COUNT);
        softly.assertThat(parametersForGetMetrics_should_return_correctlyConfiguredMetrics().length)
            .as("Count of tested metrics configurations")
            .isEqualTo(EXPECTED_METRICS_COUNT);
        softly.assertAll();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getMetrics_should_return_expectedMetrics() {
        final WTFMetrics sut = new WTFMetrics();
        final List actualMetrics = sut.getMetrics();
        final SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actualMetrics)
            .hasSize(EXPECTED_METRICS_COUNT);
        softly.assertThat(actualMetrics)
            .contains(WTFMetrics.WTF_COUNT);
        softly.assertThat(actualMetrics)
            .contains(WTFMetrics.WTF_DEBT);
        softly.assertThat(actualMetrics)
            .contains(WTFMetrics.WTF_COUNT_ABBREVIATIONS_USAGE);
        softly.assertThat(actualMetrics)
            .contains(WTFMetrics.WTF_COUNT_ANTI_PATTERN);
        softly.assertThat(actualMetrics)
            .contains(WTFMetrics.WTF_COUNT_BAD_DESIGN);
        softly.assertThat(actualMetrics)
            .contains(WTFMetrics.WTF_COUNT_BAD_FRAMEWORK_USAGE);
        softly.assertThat(actualMetrics)
            .contains(WTFMetrics.WTF_COUNT_BAD_LOGGING);
        softly.assertThat(actualMetrics)
            .contains(WTFMetrics.WTF_COUNT_HOW_COMMENT);
        softly.assertThat(actualMetrics)
            .contains(WTFMetrics.WTF_COUNT_INDECENT_EXPOSURE);
        softly.assertThat(actualMetrics)
            .contains(WTFMetrics.WTF_COUNT_MEANINGLESS_COMMENT);
        softly.assertThat(actualMetrics)
            .contains(WTFMetrics.WTF_COUNT_MIDDLE_MAN);
        softly.assertThat(actualMetrics)
            .contains(WTFMetrics.WTF_COUNT_MISSING_IMPLEMENTATION);
        softly.assertThat(actualMetrics)
            .contains(WTFMetrics.WTF_COUNT_MULTIPLE_RESPONSIBILITIES);
        softly.assertThat(actualMetrics)
            .contains(WTFMetrics.WTF_COUNT_NON_EXCEPTION);
        softly.assertThat(actualMetrics)
            .contains(WTFMetrics.WTF_COUNT_ODDBALL_SOLUTION);
        softly.assertThat(actualMetrics)
            .contains(WTFMetrics.WTF_COUNT_OVERCOMPLICATED_ALGORITHM);
        softly.assertThat(actualMetrics)
            .contains(WTFMetrics.WTF_COUNT_PRIMITIVES_OBSESSION);
        softly.assertThat(actualMetrics)
            .contains(WTFMetrics.WTF_COUNT_REFUSED_BEQUEST);
        softly.assertThat(actualMetrics)
            .contains(WTFMetrics.WTF_COUNT_REINVENTED_WHEEL);
        softly.assertThat(actualMetrics)
            .contains(WTFMetrics.WTF_COUNT_SOLUTION_SPRAWL);
        softly.assertThat(actualMetrics)
            .contains(WTFMetrics.WTF_COUNT_SPECULATIVE_GENERALITY);
        softly.assertThat(actualMetrics)
            .contains(WTFMetrics.WTF_COUNT_UNCOMMUNICATIVE_NAME);
        softly.assertThat(actualMetrics)
            .contains(WTFMetrics.WTF_COUNT_USELESS_TEST);
        softly.assertThat(actualMetrics)
            .contains(WTFMetrics.WTF_COUNT_WRONG_LOGIC);
        softly.assertThat(actualMetrics)
            .contains(WTFMetrics.WTF_COUNT_WRONG_LANGUAGE);
        softly.assertAll();
    }

    @Test
    @Parameters
    public void metricFor_should_return_expectedMetric(final WTFType type, final Metric<Integer> expectedMetric) {
        Assertions.assertThat(WTFMetrics.metricFor(type))
            .isEqualTo(expectedMetric);
    }

    private Object[] parametersForMetricFor_should_return_expectedMetric() {
        return new Object[] {
            new Object[] {
                WTFType.ABBREVIATIONS_USAGE,
                WTFMetrics.WTF_COUNT_ABBREVIATIONS_USAGE
            },
            new Object[] {
                WTFType.ANTI_PATTERN,
                WTFMetrics.WTF_COUNT_ANTI_PATTERN
            },
            new Object[] {
                WTFType.BAD_DESIGN,
                WTFMetrics.WTF_COUNT_BAD_DESIGN
            },
            new Object[] {
                WTFType.BAD_FRAMEWORK_USAGE,
                WTFMetrics.WTF_COUNT_BAD_FRAMEWORK_USAGE
            },
            new Object[] {
                WTFType.BAD_LOGGING,
                WTFMetrics.WTF_COUNT_BAD_LOGGING
            },
            new Object[] {
                WTFType.HOW_COMMENT,
                WTFMetrics.WTF_COUNT_HOW_COMMENT
            },
            new Object[] {
                WTFType.INDECENT_EXPOSURE,
                WTFMetrics.WTF_COUNT_INDECENT_EXPOSURE
            },
            new Object[] {
                WTFType.MEANINGLESS_COMMENT,
                WTFMetrics.WTF_COUNT_MEANINGLESS_COMMENT
            },
            new Object[] {
                WTFType.MIDDLE_MAN,
                WTFMetrics.WTF_COUNT_MIDDLE_MAN
            },
            new Object[] {
                WTFType.MISSING_IMPLEMENTATION,
                WTFMetrics.WTF_COUNT_MISSING_IMPLEMENTATION
            },
            new Object[] {
                WTFType.MULTIPLE_RESPONSIBILITIES,
                WTFMetrics.WTF_COUNT_MULTIPLE_RESPONSIBILITIES
            },
            new Object[] {
                WTFType.NON_EXCEPTION,
                WTFMetrics.WTF_COUNT_NON_EXCEPTION
            },
            new Object[] {
                WTFType.ODDBALL_SOLUTION,
                WTFMetrics.WTF_COUNT_ODDBALL_SOLUTION
            },
            new Object[] {
                WTFType.OVERCOMPLICATED_ALGORITHM,
                WTFMetrics.WTF_COUNT_OVERCOMPLICATED_ALGORITHM
            },
            new Object[] {
                WTFType.PRIMITIVES_OBSESSION,
                WTFMetrics.WTF_COUNT_PRIMITIVES_OBSESSION
            },
            new Object[] {
                WTFType.REFUSED_BEQUEST,
                WTFMetrics.WTF_COUNT_REFUSED_BEQUEST
            },
            new Object[] {
                WTFType.REINVENTED_WHEEL,
                WTFMetrics.WTF_COUNT_REINVENTED_WHEEL
            },
            new Object[] {
                WTFType.SOLUTION_SPRAWL,
                WTFMetrics.WTF_COUNT_SOLUTION_SPRAWL
            },
            new Object[] {
                WTFType.SPECULATIVE_GENERALITY,
                WTFMetrics.WTF_COUNT_SPECULATIVE_GENERALITY
            },
            new Object[] {
                WTFType.UNCOMMUNICATIVE_NAME,
                WTFMetrics.WTF_COUNT_UNCOMMUNICATIVE_NAME
            },
            new Object[] {
                WTFType.USELESS_TEST,
                WTFMetrics.WTF_COUNT_USELESS_TEST
            },
            new Object[] {
                WTFType.WRONG_LANGUAGE,
                WTFMetrics.WTF_COUNT_WRONG_LANGUAGE
            },
            new Object[] {
                WTFType.WRONG_LOGIC,
                WTFMetrics.WTF_COUNT_WRONG_LOGIC
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
                WTFMetrics.WTF_COUNT,
                "WTF_COUNT",
                "WTF issues count",
                ValueType.INT,
                0d,
                "Total number of reported WTF issues.",
                Metric.DIRECTION_WORST,
                WTFMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                WTFMetrics.WTF_DEBT,
                "WTF_DEBT",
                "WTF debt",
                ValueType.WORK_DUR,
                0d,
                "WTF technical debt reported by developers.",
                Metric.DIRECTION_WORST,
                CoreMetrics.DOMAIN_TECHNICAL_DEBT,
                SumChildValuesFormula.class
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_ANTI_PATTERN,
                "WTF_COUNT_ANTI_PATTERN",
                "WTF anti-pattern count",
                ValueType.INT,
                0d,
                "Number of anti-patterns reported by developers.",
                Metric.DIRECTION_WORST,
                WTFMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_BAD_DESIGN,
                "WTF_COUNT_BAD_DESIGN",
                "WTF bad design count",
                ValueType.INT,
                0d,
                "Number of bad designs reported by developers.",
                Metric.DIRECTION_WORST,
                WTFMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_INDECENT_EXPOSURE,
                "WTF_COUNT_INDECENT_EXPOSURE",
                "WTF indecent exposure count",
                ValueType.INT,
                0d,
                "Number of indecent exposures reported by developers.",
                Metric.DIRECTION_WORST,
                WTFMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_MEANINGLESS_COMMENT,
                "WTF_COUNT_MEANINGLESS_COMMENT",
                "WTF meaningless comment count",
                ValueType.INT,
                0d,
                "Number of meaningless comments reported by developers.",
                Metric.DIRECTION_WORST,
                WTFMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_MIDDLE_MAN,
                "WTF_COUNT_MIDDLE_MAN",
                "WTF middle man count",
                ValueType.INT,
                0d,
                "Number of middle men reported by developers.",
                Metric.DIRECTION_WORST,
                WTFMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_MULTIPLE_RESPONSIBILITIES,
                "WTF_COUNT_MULTIPLE_RESPONSIBILITIES",
                "WTF multiple responsibilities count",
                ValueType.INT,
                0d,
                "Number of multiple responsibilities reported by developers.",
                Metric.DIRECTION_WORST,
                WTFMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_ODDBALL_SOLUTION,
                "WTF_COUNT_ODDBALL_SOLUTION",
                "WTF oddball solution count",
                ValueType.INT,
                0d,
                "Number of oddball solutions reported by developers.",
                Metric.DIRECTION_WORST,
                WTFMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_OVERCOMPLICATED_ALGORITHM,
                "WTF_COUNT_OVERCOMPLICATED_ALGORITHM",
                "WTF overcomplicated algorithm count",
                ValueType.INT,
                0d,
                "Number of overcomplicated algorithms reported by developers.",
                Metric.DIRECTION_WORST,
                WTFMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_PRIMITIVES_OBSESSION,
                "WTF_COUNT_PRIMITIVES_OBSESSION",
                "WTF primitives obsession count",
                ValueType.INT,
                0d,
                "Number of primitives obsessions reported by developers.",
                Metric.DIRECTION_WORST,
                WTFMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_REFUSED_BEQUEST,
                "WTF_COUNT_REFUSED_BEQUEST",
                "WTF refused bequest count",
                ValueType.INT,
                0d,
                "Number of refused bequests reported by developers.",
                Metric.DIRECTION_WORST,
                WTFMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_SOLUTION_SPRAWL,
                "WTF_COUNT_SOLUTION_SPRAWL",
                "WTF solution sprawl count",
                ValueType.INT,
                0d,
                "Number of solution sprawls reported by developers.",
                Metric.DIRECTION_WORST,
                WTFMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_SPECULATIVE_GENERALITY,
                "WTF_COUNT_SPECULATIVE_GENERALITY",
                "WTF speculative generality count",
                ValueType.INT,
                0d,
                "Number of speculative generalities reported by developers.",
                Metric.DIRECTION_WORST,
                WTFMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_UNCOMMUNICATIVE_NAME,
                "WTF_COUNT_UNCOMMUNICATIVE_NAME",
                "WTF uncommunicative name count",
                ValueType.INT,
                0d,
                "Number of uncommunicative names reported by developers.",
                Metric.DIRECTION_WORST,
                WTFMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_USELESS_TEST,
                "WTF_COUNT_USELESS_TEST",
                "WTF useless test count",
                ValueType.INT,
                0d,
                "Number of useless tests reported by developers.",
                Metric.DIRECTION_WORST,
                WTFMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_WRONG_LOGIC,
                "WTF_COUNT_WRONG_LOGIC",
                "WTF wrong logic count",
                ValueType.INT,
                0d,
                "Number of wrong logics reported by developers.",
                Metric.DIRECTION_WORST,
                WTFMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_HOW_COMMENT,
                "WTF_COUNT_HOW_COMMENT",
                "WTF how comment count",
                ValueType.INT,
                0d,
                "Number of how comments reported by developers.",
                Metric.DIRECTION_WORST,
                WTFMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_MISSING_IMPLEMENTATION,
                "WTF_COUNT_MISSING_IMPLEMENTATION",
                "WTF missing implementation count",
                ValueType.INT,
                0d,
                "Number of missing implementations reported by developers.",
                Metric.DIRECTION_WORST,
                WTFMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_NON_EXCEPTION,
                "WTF_COUNT_NON_EXCEPTION",
                "WTF non exception count",
                ValueType.INT,
                0d,
                "Number of non exceptions reported by developers.",
                Metric.DIRECTION_WORST,
                WTFMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_WRONG_LANGUAGE,
                "WTF_COUNT_WRONG_LANGUAGE",
                "WTF wrong language count",
                ValueType.INT,
                0d,
                "Number of wrong languages reported by developers.",
                Metric.DIRECTION_WORST,
                WTFMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_ABBREVIATIONS_USAGE,
                "WTF_COUNT_ABBREVIATIONS_USAGE",
                "WTF abbreviations usage count",
                ValueType.INT,
                0d,
                "Number of abbreviations usages reported by developers.",
                Metric.DIRECTION_WORST,
                WTFMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_BAD_FRAMEWORK_USAGE,
                "WTF_COUNT_BAD_FRAMEWORK_USAGE",
                "WTF bad framework usage count",
                ValueType.INT,
                0d,
                "Number of bad framework usages reported by developers.",
                Metric.DIRECTION_WORST,
                WTFMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_BAD_LOGGING,
                "WTF_COUNT_BAD_LOGGING",
                "WTF bad logging count",
                ValueType.INT,
                0d,
                "Number of bad loggings reported by developers.",
                Metric.DIRECTION_WORST,
                WTFMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
            new Object[] {
                WTFMetrics.WTF_COUNT_REINVENTED_WHEEL,
                "WTF_COUNT_REINVENTED_WHEEL",
                "WTF reinvented wheel count",
                ValueType.INT,
                0d,
                "Number of reinvented wheels reported by developers.",
                Metric.DIRECTION_WORST,
                WTFMetrics.DOMAIN,
                SumChildValuesFormula.class
            },
        };
    }
}
