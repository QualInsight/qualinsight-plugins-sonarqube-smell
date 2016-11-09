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
package com.qualinsight.plugins.sonarqube.smell.plugin.check;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.squidbridge.annotations.SqaleLinearRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;
import com.qualinsight.plugins.sonarqube.smell.api.model.SmellType;

/**
 * Check for ODDBALL_SOLUTION smell type.
 *
 * @author Michel Pawlak
 */
@Rule(
    key = "0013",
    name = "Oddball solution",
    description = "The problem is solved one way throughout the system and the same problem is solved another way in the same system. One of the solutions is the oddball or the inconsistent solution and has to be eliminated.",
    priority = Priority.CRITICAL,
    tags = {
        "design"
    })
@SqaleLinearRemediation(coeff = "1min", effortToFixDescription = "")
@SqaleSubCharacteristic(value = RulesDefinition.SubCharacteristics.MAINTAINABILITY_COMPLIANCE)
public class OddballSolutionSmellCheck extends AbstractSmellCheck {

    @Override
    public SmellType smellType() {
        return SmellType.ODDBALL_SOLUTION;
    }

}
