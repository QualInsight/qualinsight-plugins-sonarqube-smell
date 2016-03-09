package com.qualinsight.plugins.sonarqube.smell.plugin.check;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.squidbridge.annotations.SqaleLinearRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;
import com.qualinsight.plugins.sonarqube.smell.api.model.SmellType;

@Rule(key = "Smell-0006", name = "How comment", description = "The comment or documentation text focuses on the 'how' instead of the 'why'.", priority = Priority.MAJOR, tags = {
    "documentation",
    "bad practice"
})
@SqaleLinearRemediation(coeff = "1min", effortToFixDescription = "")
@SqaleSubCharacteristic(value = RulesDefinition.SubCharacteristics.UNDERSTANDABILITY)
public class HowCommentSmellCheck extends AbstractSmellCheck {

    @Override
    public SmellType smellType() {
        return SmellType.HOW_COMMENT;
    }

}
