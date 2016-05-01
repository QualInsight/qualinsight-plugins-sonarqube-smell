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
import com.google.common.collect.ImmutableList;
import org.sonar.api.ce.measure.MeasureComputer;

/**
 * {@link MeasureComputer} that aggregates Smell count measures by type.
 *
 * @author Michel Pawlak
 */
public final class SmellCountByTypeMeasuresComputer extends AbstractSmellMeasureComputer {

    public static final List<String> SMELL_COUNT_INPUT_METRICS_KEYS = ImmutableList.of(SmellMetrics.SMELL_COUNT_ABBREVIATIONS_USAGE.getKey(), SmellMetrics.SMELL_COUNT_ANTI_PATTERN.getKey(),
        SmellMetrics.SMELL_COUNT_BAD_DESIGN.getKey(), SmellMetrics.SMELL_COUNT_BAD_LOGGING.getKey(), SmellMetrics.SMELL_COUNT_HOW_COMMENT.getKey(),
        SmellMetrics.SMELL_COUNT_INDECENT_EXPOSURE.getKey(), SmellMetrics.SMELL_COUNT_MEANINGLESS_COMMENT.getKey(), SmellMetrics.SMELL_COUNT_MIDDLE_MAN.getKey(),
        SmellMetrics.SMELL_COUNT_MISSING_IMPLEMENTATION.getKey(), SmellMetrics.SMELL_COUNT_MULTIPLE_RESPONSIBILITIES.getKey(), SmellMetrics.SMELL_COUNT_NON_EXCEPTION.getKey(),
        SmellMetrics.SMELL_COUNT_ODDBALL_SOLUTION.getKey(), SmellMetrics.SMELL_COUNT_OVERCOMPLICATED_ALGORITHM.getKey(), SmellMetrics.SMELL_COUNT_PRIMITIVES_OBSESSION.getKey(),
        SmellMetrics.SMELL_COUNT_REFUSED_BEQUEST.getKey(), SmellMetrics.SMELL_COUNT_REINVENTED_WHEEL.getKey(), SmellMetrics.SMELL_COUNT_SOLUTION_SPRAWL.getKey(),
        SmellMetrics.SMELL_COUNT_SPECULATIVE_GENERALITY.getKey(), SmellMetrics.SMELL_COUNT_UNCOMMUNICATIVE_NAME.getKey(), SmellMetrics.SMELL_COUNT_BAD_FRAMEWORK_USAGE.getKey(),
        SmellMetrics.SMELL_COUNT_USELESS_TEST.getKey(), SmellMetrics.SMELL_COUNT_WRONG_LANGUAGE.getKey(), SmellMetrics.SMELL_COUNT_WRONG_LOGIC.getKey());

    public static final List<String> SMELL_COUNT_OUTPUT_METRICS_KEYS = ImmutableList.copyOf(SMELL_COUNT_INPUT_METRICS_KEYS);

    /**
     * {@link SmellCountByTypeMeasuresComputer} IoC constructor.
     */
    public SmellCountByTypeMeasuresComputer() {
        super(SMELL_COUNT_INPUT_METRICS_KEYS, SMELL_COUNT_OUTPUT_METRICS_KEYS);
    }

}
