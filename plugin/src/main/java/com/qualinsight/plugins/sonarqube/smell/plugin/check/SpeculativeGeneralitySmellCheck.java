package com.qualinsight.plugins.sonarqube.smell.plugin.check;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.squidbridge.annotations.SqaleLinearRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;
import com.qualinsight.plugins.sonarqube.smell.api.model.SmellType;

@Rule(
    key = "Smell-0019",
    name = "Speculative generality",
    description = "The code is written thinking about tomorrow's problems. Write code to solve today's problems, and worry about tomorrow's problems when they actually materialize. Everyone loses in the 'what if...' school of design.",
    priority = Priority.CRITICAL,
    tags = {
        "sdlc",
        "brain-overload"
    })
@SqaleLinearRemediation(coeff = "1min", effortToFixDescription = "")
@SqaleSubCharacteristic(value = RulesDefinition.SubCharacteristics.EFFICIENCY_COMPLIANCE)
public class SpeculativeGeneralitySmellCheck extends AbstractSmellCheck {

    @Override
    public SmellType smellType() {
        return SmellType.SPECULATIVE_GENERALITY;
    }

}
