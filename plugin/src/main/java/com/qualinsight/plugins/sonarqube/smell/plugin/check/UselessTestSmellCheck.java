package com.qualinsight.plugins.sonarqube.smell.plugin.check;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.squidbridge.annotations.SqaleLinearRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;
import com.qualinsight.plugins.sonarqube.smell.api.model.SmellType;

@Rule(key = "Smell-0021", name = "Useless test", description = "The test is useless (it tests nothing).", priority = Priority.MAJOR, tags = {
    "tests",
    "logic",
    "confusing"
})
@SqaleLinearRemediation(coeff = "1min", effortToFixDescription = "")
@SqaleSubCharacteristic(value = RulesDefinition.SubCharacteristics.UNIT_TESTS)
public class UselessTestSmellCheck extends AbstractSmellCheck {

    @Override
    public SmellType smellType() {
        return SmellType.USELESS_TEST;
    }

}
