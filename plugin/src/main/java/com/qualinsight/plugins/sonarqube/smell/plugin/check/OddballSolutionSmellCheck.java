package com.qualinsight.plugins.sonarqube.smell.plugin.check;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.squidbridge.annotations.SqaleLinearRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;
import com.qualinsight.plugins.sonarqube.smell.api.model.SmellType;

@Rule(
    key = "Smell-0013",
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
