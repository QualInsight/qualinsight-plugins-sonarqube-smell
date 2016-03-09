package com.qualinsight.plugins.sonarqube.smell.plugin.check;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.squidbridge.annotations.SqaleLinearRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;
import com.qualinsight.plugins.sonarqube.smell.api.model.SmellType;

@Rule(key = "Smell-0012", name = "Non exception", description = "Exception mechanism usage for non exceptional cases.", priority = Priority.MAJOR, tags = {
    "design",
    "performance",
    "bad-practice",
    "error-handling"
})
@SqaleLinearRemediation(coeff = "1min", effortToFixDescription = "")
@SqaleSubCharacteristic(value = RulesDefinition.SubCharacteristics.EXCEPTION_HANDLING)
public class NonExceptionSmellCheck extends AbstractSmellCheck {

    @Override
    public SmellType smellType() {
        return SmellType.NON_EXCEPTION;
    }

}
