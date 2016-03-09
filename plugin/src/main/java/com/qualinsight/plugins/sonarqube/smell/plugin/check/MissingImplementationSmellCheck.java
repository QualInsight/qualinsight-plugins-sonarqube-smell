package com.qualinsight.plugins.sonarqube.smell.plugin.check;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.squidbridge.annotations.SqaleLinearRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;
import com.qualinsight.plugins.sonarqube.smell.api.model.SmellType;

@Rule(key = "Smell-0010", name = "Missing implementation", description = "A method's implementation is missing.", priority = Priority.MINOR, tags = {
    "todo"
})
@SqaleLinearRemediation(coeff = "1min", effortToFixDescription = "")
@SqaleSubCharacteristic(value = RulesDefinition.SubCharacteristics.ERRORS)
public class MissingImplementationSmellCheck extends AbstractSmellCheck {

    @Override
    public SmellType smellType() {
        return SmellType.MISSING_IMPLEMENTATION;
    }

}
