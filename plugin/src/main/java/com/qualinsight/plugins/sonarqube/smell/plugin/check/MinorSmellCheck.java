package com.qualinsight.plugins.sonarqube.smell.plugin.check;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.squidbridge.annotations.SqaleLinearRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;

import com.qualinsight.plugins.sonarqube.smell.api.model.Severity;

@Rule(
    key = MinorSmellCheck.KEY,
    name = "Code Smell Minor",
    description = "The Smell annotation is meant to help developers inform their fellow team members of development issues and code smells that are not directly detectable by SonarQube. Indeed SonarQube is only able to show issues it has a rule for. It is not able to detect strange behaviors nor sad mistakes. Each Smell is a means provided to inform your fellow developers that you have detected a bad coding practice, and to inform them of your estimation for correcting the issue. Read carefuly the reason bound to this Smell annotation and <a href=\"https://github.com/QualInsight/qualinsight-plugins-sonarqube-smell#available-smell-types\">documentation</a> then try fixing this issue in an upcoming development Sprint.",
    priority = Priority.MINOR,
    tags = {
        "smell"
    })
@SqaleLinearRemediation(coeff = "1min", effortToFixDescription = "")
@SqaleSubCharacteristic(value = RulesDefinition.SubCharacteristics.ERRORS)
public class MinorSmellCheck extends SmellCheck
{

    public static final String KEY = "S0002";

    @Override
    public Severity getSeverity()
    {
        return Severity.MINOR;
    }

}
