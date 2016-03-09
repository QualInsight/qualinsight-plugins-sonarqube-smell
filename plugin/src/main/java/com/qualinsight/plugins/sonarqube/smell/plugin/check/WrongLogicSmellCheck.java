package com.qualinsight.plugins.sonarqube.smell.plugin.check;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.squidbridge.annotations.SqaleLinearRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;
import com.qualinsight.plugins.sonarqube.smell.api.model.SmellType;

@Rule(key = "Smell-0023", name = "Wrong logic", description = "Wrong (business) logic is being used.", priority = Priority.BLOCKER, tags = {
    "logic",
    "bug"
})
@SqaleLinearRemediation(coeff = "1min", effortToFixDescription = "")
@SqaleSubCharacteristic(value = RulesDefinition.SubCharacteristics.ERRORS)
public class WrongLogicSmellCheck extends AbstractSmellCheck {

    @Override
    public SmellType smellType() {
        return SmellType.WRONG_LOGIC;
    }

}
