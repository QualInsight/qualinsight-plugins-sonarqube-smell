/*
 * This file is part of qualinsight-plugins-sonarqube-wtf-internal.
 *
 * qualinsight-plugins-sonarqube-wtf-internal is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * qualinsight-plugins-sonarqube-wtf-internal is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with qualinsight-plugins-sonarqube-wtf-internal.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.qualinsight.plugins.sonarqube.wtf.internal.check;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.AnnotationTree;
import org.sonar.plugins.java.api.tree.Arguments;
import org.sonar.plugins.java.api.tree.AssignmentExpressionTree;
import org.sonar.plugins.java.api.tree.ExpressionTree;
import org.sonar.plugins.java.api.tree.IdentifierTree;
import org.sonar.plugins.java.api.tree.LiteralTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;
import org.sonar.squidbridge.annotations.SqaleLinearRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;
import com.google.common.collect.ImmutableList;

@Rule(
    key = WTFCheck.KEY,
    name = "WTF!",
    description = "The WTF! annotation stands for \"what the fuck!\". It is meant to help developers inform their fellow team members of development issues and code smells that are not directly detectable by SonarQube. Indeed SonarQube is only able to show issues it has a rule for. It is not able to detect idiotic behaviors nor sad mistakes. Each WTF a means provided to inform your fellow developer that you have detected a WTF coding practice, and to inform them of your estimation for correcting the issue. Read carefuly the reason bound to this WTF! annotation and try fixing this issue in an upcoming development Sprint.",
    priority = Priority.MAJOR,
    tags = {
        "qualinsight",
        "wtf"
    })
@SqaleLinearRemediation(coeff = "1min", effortToFixDescription = "")
@SqaleSubCharacteristic(value = RulesDefinition.SubCharacteristics.ERRORS)
public class WTFCheck extends IssuableSubscriptionVisitor {

    private static final Logger LOGGER = LoggerFactory.getLogger(WTFCheck.class);

    public static final String KEY = "W0001";

    @Override
    public List<Kind> nodesToVisit() {
        return ImmutableList.<Kind> of(Kind.ANNOTATION);
    }

    @Override
    public void visitNode(final Tree tree) {
        final AnnotationTree annotationTree = (AnnotationTree) tree;
        if (annotationTree.annotationType()
            .symbolType()
            .is("com.qualinsight.plugins.sonarqube.wtf.api.annotation.WTF")) {
            handleWTFAnnotation(annotationTree);
        }
    }

    private void handleWTFAnnotation(final AnnotationTree annotationTree) {
        String message = "";
        Double minutes = 0.0;
        final Arguments arguments = annotationTree.arguments();
        for (final ExpressionTree expressionTree : arguments) {
            if (expressionTree.is(Tree.Kind.ASSIGNMENT)) {
                final AssignmentExpressionTree aet = (AssignmentExpressionTree) expressionTree;
                final String variable = ((IdentifierTree) aet.variable()).name();
                switch (variable) {
                    case "minutes":
                        minutes += Integer.valueOf(((LiteralTree) aet.expression()).value());
                        LOGGER.info("{} = {}", variable, minutes);
                        break;
                    case "reason":
                        message = ((LiteralTree) aet.expression()).value();
                        LOGGER.info("{} = {}", variable, message);
                        break;
                    default:
                        break;
                }
            }
        }
        addIssue(annotationTree, message, minutes);
    }
}
