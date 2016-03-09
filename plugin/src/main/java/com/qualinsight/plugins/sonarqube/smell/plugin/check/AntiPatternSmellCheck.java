package com.qualinsight.plugins.sonarqube.smell.plugin.check;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.squidbridge.annotations.SqaleLinearRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;
import com.qualinsight.plugins.sonarqube.smell.api.model.SmellType;

@Rule(key = "Smell-0002", name = "Anti-pattern", description = "An anti-pattern has been used.", priority = Priority.BLOCKER, tags = {
    "design",
    "bad-practice"
})
@SqaleLinearRemediation(coeff = "1min", effortToFixDescription = "")
@SqaleSubCharacteristic(value = RulesDefinition.SubCharacteristics.ERRORS)
public class AntiPatternSmellCheck extends AbstractSmellCheck {

    @Override
    public SmellType smellType() {
        return SmellType.ANTI_PATTERN;
    }

}
