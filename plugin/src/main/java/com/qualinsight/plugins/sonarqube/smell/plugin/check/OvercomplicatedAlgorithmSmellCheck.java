package com.qualinsight.plugins.sonarqube.smell.plugin.check;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.squidbridge.annotations.SqaleLinearRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;
import com.qualinsight.plugins.sonarqube.smell.api.model.SmellType;

@Rule(key = "Smell-0014", name = "Overcomplicated algorithm", description = "There is a way to simplify this algorithm.", priority = Priority.MAJOR, tags = {
    "logic",
    "testability",
    "performance",
    "brain-overload"
})
@SqaleLinearRemediation(coeff = "1min", effortToFixDescription = "")
@SqaleSubCharacteristic(value = RulesDefinition.SubCharacteristics.MAINTAINABILITY_COMPLIANCE)
public class OvercomplicatedAlgorithmSmellCheck extends AbstractSmellCheck {

    @Override
    public SmellType smellType() {
        return SmellType.OVERCOMPLICATED_ALGORITHM;
    }

}
