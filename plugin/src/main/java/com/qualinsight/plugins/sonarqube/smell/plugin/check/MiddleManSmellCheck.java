package com.qualinsight.plugins.sonarqube.smell.plugin.check;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.squidbridge.annotations.SqaleLinearRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;
import com.qualinsight.plugins.sonarqube.smell.api.model.SmellType;

@Rule(
    key = "Smell-0009",
    name = "Middle man",
    description = "The class delegates all its work, is it really needed ? Cut out the middle man unless you really need a wrapper.",
    priority = Priority.CRITICAL,
    tags = {
        "design",
        "bad-practice"
    })
@SqaleLinearRemediation(coeff = "1min", effortToFixDescription = "")
@SqaleSubCharacteristic(value = RulesDefinition.SubCharacteristics.ARCHITECTURE_RELIABILITY)
public class MiddleManSmellCheck extends AbstractSmellCheck {

    @Override
    public SmellType smellType() {
        return SmellType.MIDDLE_MAN;
    }

}
