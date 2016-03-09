package com.qualinsight.plugins.sonarqube.smell.plugin.check;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.squidbridge.annotations.SqaleLinearRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;
import com.qualinsight.plugins.sonarqube.smell.api.model.SmellType;

@Rule(key = "Smell-0011", name = "Multiple responsibilities", description = "A class or method has multiple responsibilities.", priority = Priority.BLOCKER, tags = {
    "design",
    "confusing",
    "bad-practice"
})
@SqaleLinearRemediation(coeff = "1min", effortToFixDescription = "")
@SqaleSubCharacteristic(value = RulesDefinition.SubCharacteristics.ARCHITECTURE_RELIABILITY)
public class MultipleResponsibilitiesSmellCheck extends AbstractSmellCheck {

    @Override
    public SmellType smellType() {
        return SmellType.MULTIPLE_RESPONSIBILITIES;
    }

}
