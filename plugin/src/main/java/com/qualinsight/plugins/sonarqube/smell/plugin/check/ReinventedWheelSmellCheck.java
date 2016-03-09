package com.qualinsight.plugins.sonarqube.smell.plugin.check;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.squidbridge.annotations.SqaleLinearRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;
import com.qualinsight.plugins.sonarqube.smell.api.model.SmellType;

@Rule(key = "Smell-0017", name = "Reinvented wheel", description = "A library does the same job, probably better.", priority = Priority.BLOCKER, tags = {
    "ecosystem"
})
@SqaleLinearRemediation(coeff = "1min", effortToFixDescription = "")
@SqaleSubCharacteristic(value = RulesDefinition.SubCharacteristics.EFFICIENCY_COMPLIANCE)
public class ReinventedWheelSmellCheck extends AbstractSmellCheck {

    @Override
    public SmellType smellType() {
        return SmellType.REINVENTED_WHEEL;
    }

}
