package com.qualinsight.plugins.sonarqube.smell.plugin.check;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.squidbridge.annotations.SqaleLinearRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;
import com.qualinsight.plugins.sonarqube.smell.api.model.SmellType;

@Rule(
    key = "Smell-0008",
    name = "Meaningless comment",
    description = "The class delegates all its work, is it really needed ? Cut out the middle man unless you really need a wrapper.",
    priority = Priority.MAJOR,
    tags = {
        "documentation",
        "confusing"
    })
@SqaleLinearRemediation(coeff = "1min", effortToFixDescription = "")
@SqaleSubCharacteristic(value = RulesDefinition.SubCharacteristics.UNDERSTANDABILITY)
public class MeaninglessCommentSmellCheck extends AbstractSmellCheck {

    @Override
    public SmellType smellType() {
        return SmellType.MEANINGLESS_COMMENT;
    }

}
