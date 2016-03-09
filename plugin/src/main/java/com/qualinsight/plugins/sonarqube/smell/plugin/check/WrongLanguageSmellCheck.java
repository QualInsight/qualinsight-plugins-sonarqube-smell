package com.qualinsight.plugins.sonarqube.smell.plugin.check;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.squidbridge.annotations.SqaleLinearRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;
import com.qualinsight.plugins.sonarqube.smell.api.model.SmellType;

@Rule(key = "Smell-0022", name = "Wrong language", description = "Wrong language (french, english, german...) is being used.", priority = Priority.MAJOR, tags = {
    "documentation",
    "confusing"
})
@SqaleLinearRemediation(coeff = "1min", effortToFixDescription = "")
@SqaleSubCharacteristic(value = RulesDefinition.SubCharacteristics.UNDERSTANDABILITY)
public class WrongLanguageSmellCheck extends AbstractSmellCheck {

    @Override
    public SmellType smellType() {
        return SmellType.WRONG_LANGUAGE;
    }

}
