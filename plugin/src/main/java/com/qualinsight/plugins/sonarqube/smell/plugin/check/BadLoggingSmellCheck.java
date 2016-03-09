package com.qualinsight.plugins.sonarqube.smell.plugin.check;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.squidbridge.annotations.SqaleLinearRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;
import com.qualinsight.plugins.sonarqube.smell.api.model.SmellType;

@Rule(key = "Smell-0005", name = "Bad Logging", description = "The logging message, level, is inappropriate or the log is redundant.", priority = Priority.MAJOR, tags = {
    "logging",
    "performance"
})
@SqaleLinearRemediation(coeff = "1min", effortToFixDescription = "")
@SqaleSubCharacteristic(value = RulesDefinition.SubCharacteristics.USABILITY_COMPLIANCE)
public class BadLoggingSmellCheck extends AbstractSmellCheck {

    @Override
    public SmellType smellType() {
        return SmellType.BAD_LOGGING;
    }

}
