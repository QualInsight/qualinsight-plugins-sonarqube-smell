package com.qualinsight.plugins.sonarqube.smell.plugin.check;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.squidbridge.annotations.SqaleLinearRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;
import com.qualinsight.plugins.sonarqube.smell.api.model.SmellType;

@Rule(key = "Smell-0004", name = "Bad framework usage", description = "A framework is not used the way it should.", priority = Priority.BLOCKER, tags = {
    "ecosytem"
})
@SqaleLinearRemediation(coeff = "1min", effortToFixDescription = "")
@SqaleSubCharacteristic(value = RulesDefinition.SubCharacteristics.ERRORS)
public class BadFrameworkUsageSmellCheck extends AbstractSmellCheck {

    @Override
    public SmellType smellType() {
        return SmellType.BAD_FRAMEWORK_USAGE;
    }

}
