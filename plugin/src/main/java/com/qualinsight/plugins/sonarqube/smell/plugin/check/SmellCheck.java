/*
 * qualinsight-plugins-sonarqube-smell
 * Copyright (c) 2015, QualInsight
 * http://www.qualinsight.com/
 *
 * This program is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program. If not, you can retrieve a copy
 * from <http://www.gnu.org/licenses/>.
 */
package com.qualinsight.plugins.sonarqube.smell.plugin.check;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.semantic.Type;
import org.sonar.plugins.java.api.tree.AnnotationTree;
import org.sonar.plugins.java.api.tree.Arguments;
import org.sonar.plugins.java.api.tree.AssignmentExpressionTree;
import org.sonar.plugins.java.api.tree.BinaryExpressionTree;
import org.sonar.plugins.java.api.tree.ExpressionTree;
import org.sonar.plugins.java.api.tree.IdentifierTree;
import org.sonar.plugins.java.api.tree.LiteralTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;
import org.sonar.plugins.java.api.tree.VariableTree;
import org.sonar.squidbridge.annotations.SqaleLinearRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;

/**
 * SonarQube {@link Rule} to be used to report code smells as SonarQube issues.
 *
 * @author Michel Pawlak
 */
@Rule(
    key = SmellCheck.KEY,
    name = "Code Smell",
    description = "The Smell annotation is meant to help developers inform their fellow team members of development issues and code smells that are not directly detectable by SonarQube. Indeed SonarQube is only able to show issues it has a rule for. It is not able to detect idiotic behaviors nor sad mistakes. Each Smell is a means provided to inform your fellow developers that you have detected a bad coding practice, and to inform them of your estimation for correcting the issue. Read carefuly the reason bound to this Smell annotation and try fixing this issue in an upcoming development Sprint.",
    priority = Priority.MAJOR,
    tags = {
        "qualinsight",
        "smell"
    })
@SqaleLinearRemediation(coeff = "1min", effortToFixDescription = "")
@SqaleSubCharacteristic(value = RulesDefinition.SubCharacteristics.ERRORS)
public final class SmellCheck extends IssuableSubscriptionVisitor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmellCheck.class);

    private static final Pattern PATTERN = Pattern.compile("^\"(.*)\"$");

    /**
     * Key of the check
     */
    public static final String KEY = "S0001";

    @Override
    public List<Kind> nodesToVisit() {
        return ImmutableList.<Kind> of(Kind.ANNOTATION);
    }

    @Override
    public void visitNode(final Tree tree) {
        final AnnotationTree annotationTree = (AnnotationTree) tree;
        final Type symbolType = annotationTree.annotationType()
            .symbolType();
        if (symbolType.is("com.qualinsight.plugins.sonarqube.smell.api.annotation.Smell")) {
            handleSmellAnnotation(annotationTree);
        }
    }

    private void handleSmellAnnotation(final AnnotationTree annotationTree) {
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
                        LOGGER.debug("{} = {}", variable, minutes);
                        break;
                    case "reason":
                        message = extractMessage(aet.expression());
                        LOGGER.debug("{} = {}", variable, message);
                        break;
                    default:
                        break;
                }
            }
        }
        final Matcher matcher = PATTERN.matcher(message);
        if (matcher.matches()) {
            message = matcher.group(1);
        }
        addIssue(annotationTree, message, minutes);
    }

    private static final String extractMessage(final ExpressionTree expressionTree) {
        String message = "";
        switch (expressionTree.kind()) {
            case STRING_LITERAL:
                message = ((LiteralTree) expressionTree).value();
                break;
            case PLUS:
                final BinaryExpressionTree bet = (BinaryExpressionTree) expressionTree;
                message = extractMessage(bet.leftOperand()) + extractMessage(bet.rightOperand());
                break;
            case IDENTIFIER:
                final IdentifierTree it = (IdentifierTree) expressionTree;
                message = extractMessage(((VariableTree) (it.symbol().declaration())).initializer());
                break;
            default:
                LOGGER.warn("Cannot extract message due to unexpected expressionTree kind: {}", expressionTree.kind());
                break;
        }
        return trimQuotes(message);
    }

    private static final String trimQuotes(final String message) {
        String result = message;
        final Pattern pattern = Pattern.compile("\"(.*)\"");
        final Matcher matcher = pattern.matcher(message);
        if (matcher.matches()) {
            result = matcher.group(1);
        }
        return result;
    }
}
