package com.qualinsight.plugins.sonarqube.smell.plugin.check;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.squidbridge.annotations.SqaleLinearRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;
import com.qualinsight.plugins.sonarqube.smell.api.model.SmellType;

@Rule(key = "Smell-0001", name = "Abbreviations usage", description = "Confusing abbreviations are being used instead of explicit names.", priority = Priority.MAJOR, tags = {
    "convention",
    "brain-overload",
    "bad-practice"
})
@SqaleLinearRemediation(coeff = "1min", effortToFixDescription = "")
@SqaleSubCharacteristic(value = RulesDefinition.SubCharacteristics.UNDERSTANDABILITY)
public class AbbreviationsUsageSmellCheck extends AbstractSmellCheck {

    @Override
    public SmellType smellType() {
        return SmellType.ABBREVIATIONS_USAGE;
    }

}
