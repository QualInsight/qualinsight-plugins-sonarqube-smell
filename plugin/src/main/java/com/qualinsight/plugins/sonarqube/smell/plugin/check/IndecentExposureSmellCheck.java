package com.qualinsight.plugins.sonarqube.smell.plugin.check;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.squidbridge.annotations.SqaleLinearRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;
import com.qualinsight.plugins.sonarqube.smell.api.model.SmellType;

@Rule(
    key = "Smell-0007",
    name = "Indecent exposure",
    description = "The class unnecessarily exposes its internals. Aggressively refactor classes to minimize its public surface. You should have a compelling reason for every item you make public. If you don't, hide it.",
    priority = Priority.CRITICAL,
    tags = {
        "design",
        "bad-practice"
    })
@SqaleLinearRemediation(coeff = "1min", effortToFixDescription = "")
@SqaleSubCharacteristic(value = RulesDefinition.SubCharacteristics.ARCHITECTURE_RELIABILITY)
public class IndecentExposureSmellCheck extends AbstractSmellCheck {

    @Override
    public SmellType smellType() {
        return SmellType.INDECENT_EXPOSURE;
    }

}
