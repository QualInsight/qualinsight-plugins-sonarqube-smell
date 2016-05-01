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
package com.qualinsight.plugins.sonarqube.smell.internal.extension;

import com.google.common.collect.ImmutableList;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.sonar.api.rule.RuleStatus;
import org.sonar.api.server.debt.DebtRemediationFunction;
import org.sonar.api.server.rule.RulesDefinition.Context;
import org.sonar.api.server.rule.RulesDefinition.DebtRemediationFunctions;
import org.sonar.api.server.rule.RulesDefinition.NewRepository;
import org.sonar.api.server.rule.RulesDefinition.NewRule;
import org.sonar.plugins.java.Java;
import com.qualinsight.plugins.sonarqube.smell.internal.extension.SmellRulesDefinition;

@RunWith(MockitoJUnitRunner.class)
public class SmellRulesDefinitionTest {

    private static final String EXPECTED_REPOSITORY_KEY = "qualinsight-smells";

    private static final String EXPECTED_REPOSITORY_NAME = "Smells";

    private static final String RULE_KEY = "0023";

    @Mock
    public Context context;

    @Mock
    public NewRepository repository;

    @Mock
    public NewRule rule;

    @Mock
    public DebtRemediationFunctions functions;

    @Captor
    public ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

    @Test
    public void constant_REPOSITORY_KEY_should_have_expectedValue() {
        Assertions.assertThat(SmellRulesDefinition.REPOSITORY_KEY)
            .isEqualTo(EXPECTED_REPOSITORY_KEY);
    }

    @Test
    public void constant_REPOSITORY_NAME_should_have_expectedValue() {
        Assertions.assertThat(SmellRulesDefinition.REPOSITORY_NAME)
            .isEqualTo(EXPECTED_REPOSITORY_NAME);
    }

    /*
     * Is it even possible to simplify this ugly test ?
     */
    @Test
    public void define_should_createRepositoryAndRegisterAllSmellPluginChecks() {
        Mockito.when(this.repository.key())
            .thenReturn(EXPECTED_REPOSITORY_KEY);
        Mockito.when(this.rule.key())
            .thenReturn(RULE_KEY);
        // Some plumbering...
        Mockito.when(this.context.createRepository(Matchers.anyString(), Matchers.anyString()))
            .thenReturn(this.repository);
        Mockito.when(this.repository.setName(Matchers.anyString()))
            .thenReturn(this.repository);
        Mockito.when(this.repository.rules())
            .thenReturn(ImmutableList.<NewRule> of(this.rule));
        Mockito.when(this.repository.createRule(Matchers.anyString()))
            .thenReturn(this.rule);
        Mockito.when(this.repository.rule(Matchers.anyString()))
            .thenReturn(this.rule);
        Mockito.when(this.rule.setName(Matchers.anyString()))
            .thenReturn(this.rule);
        Mockito.when(this.rule.setHtmlDescription(Matchers.anyString()))
            .thenReturn(this.rule);
        Mockito.when(this.rule.setSeverity(Matchers.anyString()))
            .thenReturn(this.rule);
        Mockito.when(this.rule.setTemplate(Matchers.anyBoolean()))
            .thenReturn(this.rule);
        Mockito.when(this.rule.setStatus(Matchers.any(RuleStatus.class)))
            .thenReturn(this.rule);
        Mockito.when(this.rule.setTags(Matchers.anyString()))
            .thenReturn(this.rule);
        Mockito.when(this.rule.setDebtSubCharacteristic(Matchers.anyString()))
            .thenReturn(this.rule);
        Mockito.when(this.rule.setDebtRemediationFunction(Matchers.any(DebtRemediationFunction.class)))
            .thenReturn(this.rule);
        Mockito.when(this.rule.debtRemediationFunctions())
            .thenReturn(this.functions);
        Mockito.when(this.rule.setEffortToFixDescription(Matchers.anyString()))
            .thenReturn(this.rule);
        // Execute
        final SmellRulesDefinition sut = new SmellRulesDefinition();
        sut.define(this.context);
        // Verify behavior
        Mockito.verify(this.context, Mockito.times(1))
            .createRepository(Matchers.eq(EXPECTED_REPOSITORY_KEY), Matchers.eq(Java.KEY));
        Mockito.verify(this.repository, Mockito.times(1))
            .setName(Matchers.eq(EXPECTED_REPOSITORY_NAME));
        Mockito.verify(this.repository, Mockito.times(23))
            .createRule(this.captor.capture());
        Assertions.assertThat(this.captor.getValue())
            .isEqualTo(RULE_KEY);
        Mockito.verify(this.rule, Mockito.times(24))
            .key();
        Mockito.verify(this.rule, Mockito.times(1))
            .setInternalKey(Matchers.eq(RULE_KEY));
        Mockito.verify(this.repository, Mockito.times(1))
            .done();
    }

}
