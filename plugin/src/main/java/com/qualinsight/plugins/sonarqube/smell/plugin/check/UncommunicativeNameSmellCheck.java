package com.qualinsight.plugins.sonarqube.smell.plugin.check;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.squidbridge.annotations.SqaleLinearRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;
import com.qualinsight.plugins.sonarqube.smell.api.model.SmellType;

@Rule(
    key = "Smell-0020",
    name = "Uncommunicative name",
    description = "The class, interface, method, field, variable or parameter name should be renamed in order to make it describe what it does or what it represents.",
    priority = Priority.CRITICAL,
    tags = {
        "documentation",
        "confusing"
    })
@SqaleLinearRemediation(coeff = "1min", effortToFixDescription = "")
@SqaleSubCharacteristic(value = RulesDefinition.SubCharacteristics.UNDERSTANDABILITY)
public class UncommunicativeNameSmellCheck extends AbstractSmellCheck {

    @Override
    public SmellType smellType() {
        return SmellType.UNCOMMUNICATIVE_NAME;
    }

}
