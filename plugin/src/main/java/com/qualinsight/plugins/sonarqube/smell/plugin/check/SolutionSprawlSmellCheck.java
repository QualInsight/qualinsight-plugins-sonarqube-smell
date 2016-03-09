package com.qualinsight.plugins.sonarqube.smell.plugin.check;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.squidbridge.annotations.SqaleLinearRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;
import com.qualinsight.plugins.sonarqube.smell.api.model.SmellType;

@Rule(
    key = "Smell-0018",
    name = "Solution sprawl",
    description = "It takes too many classes to do anything useful, you might have solution sprawl. Consider simplifying and consolidating your design.",
    priority = Priority.CRITICAL,
    tags = {
        "design",
        "testability",
        "brain-overload"
    })
@SqaleLinearRemediation(coeff = "1min", effortToFixDescription = "")
@SqaleSubCharacteristic(value = RulesDefinition.SubCharacteristics.EFFICIENCY_COMPLIANCE)
public class SolutionSprawlSmellCheck extends AbstractSmellCheck {

    @Override
    public SmellType smellType() {
        return SmellType.SOLUTION_SPRAWL;
    }

}
