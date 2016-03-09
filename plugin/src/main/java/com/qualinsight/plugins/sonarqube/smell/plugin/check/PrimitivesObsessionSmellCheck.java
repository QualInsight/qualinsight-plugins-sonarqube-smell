package com.qualinsight.plugins.sonarqube.smell.plugin.check;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.squidbridge.annotations.SqaleLinearRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;
import com.qualinsight.plugins.sonarqube.smell.api.model.SmellType;

@Rule(
    key = "Smell-0015",
    name = "Primitives obsession",
    description = "Primitives, which include integers, Strings, doubles, arrays and other low-level language elements, are generic because many people use them. Classes, on the other hand, may be as specific as you need them to be, since you create them for specific purposes. In many cases, classes provide a simpler and more natural way to model things than primitives. In addition, once you create a class, you’ll often discover how other code in a system belongs in that class. Fowler and Beck explain how primitive obsession manifests itself when code relies too much on primitives. This typically occurs when you haven’t yet seen how a higher-level abstraction can clarify or simplify your code.",
    priority = Priority.CRITICAL,
    tags = {
        "design",
        "bad-practice"
    })
@SqaleLinearRemediation(coeff = "1min", effortToFixDescription = "")
@SqaleSubCharacteristic(value = RulesDefinition.SubCharacteristics.ARCHITECTURE_RELIABILITY)
public class PrimitivesObsessionSmellCheck extends AbstractSmellCheck {

    @Override
    public SmellType smellType() {
        return SmellType.PRIMITIVES_OBSESSION;
    }

}
