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
package com.qualinsight.plugins.sonarqube.smell.internal.check;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.sonar.plugins.java.api.tree.MemberSelectExpressionTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;
import org.sonar.plugins.java.api.tree.VariableTree;
import com.qualinsight.plugins.sonarqube.smell.api.model.SmellType;

/**
 * SonarQube {@link Rule} to be used to report code smells as SonarQube issues.
 *
 * @author Michel Pawlak
 */
public abstract class AbstractSmellCheck extends IssuableSubscriptionVisitor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractSmellCheck.class);

    private static final Pattern PATTERN = Pattern.compile("^\"(.*)\"$");

    /**
     * Returns the {@link SmellType} the check reports.
     * 
     * @return {@link SmellType} the check reports.
     */
    public abstract SmellType smellType();

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
        SmellType type = null;
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
                    case "type":
                        type = SmellType.valueOf(((MemberSelectExpressionTree) aet.expression()).identifier()
                            .name());
                        break;
                    default:
                        break;
                }
            }
        }
        if (smellType().equals(type)) {
            final Matcher matcher = PATTERN.matcher(message);
            if (matcher.matches()) {
                message = matcher.group(1);
            }
            addIssue(annotationTree, message, minutes);
        }
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
