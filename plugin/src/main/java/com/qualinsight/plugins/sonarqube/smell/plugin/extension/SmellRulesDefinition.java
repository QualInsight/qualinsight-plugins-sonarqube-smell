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
package com.qualinsight.plugins.sonarqube.smell.plugin.extension;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import org.sonar.api.rule.RuleStatus;
import org.sonar.api.rules.RuleType;
import org.sonar.api.server.debt.DebtRemediationFunction;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.plugins.java.Java;
import org.sonar.squidbridge.annotations.AnnotationBasedRulesDefinition;

/**
 * Defines all {@link org.sonar.check.Rule}s provided by the Code Smells plugin
 *
 * @author Michel Pawlak
 */
public final class SmellRulesDefinition implements RulesDefinition {

    /**
     * Repository key.
     */
    public static final String REPOSITORY_KEY = "qualinsight-smells";

    /**
     * Repository name.
     */
    public static final String REPOSITORY_NAME = "Smells";

    private final Gson gson = new Gson();

    @SuppressWarnings("rawtypes")
    @Override
    public void define(final Context context) {
        final NewRepository repository = context.createRepository(REPOSITORY_KEY, Java.KEY)
            .setName(REPOSITORY_NAME);
        final List<Class> checkClasses = ImmutableList.<Class> builder()
            .addAll(SmellChecksRegistrar.checkClasses())
            .build();
        new AnnotationBasedRulesDefinition(repository, Java.KEY).addRuleClasses(false, checkClasses);
        for (final NewRule rule : repository.rules()) {
            final String metadataKey = rule.key();
            // Setting internal key is essential for rule templates (see SONAR-6162), and it is not done by AnnotationBasedRulesDefinition from
            // sslr-squid-bridge version 2.5.1:
            rule.setInternalKey(metadataKey);
            rule.setHtmlDescription(readRuleDefinitionResource(metadataKey + ".html"));
            addMetadata(rule, metadataKey);
        }
        repository.done();
    }

    @Nullable
    private static String readRuleDefinitionResource(final String fileName) {
        final URL resource = SmellRulesDefinition.class.getResource("/org/sonar/l10n/java/rules/smell/" + fileName);
        if (resource == null) {
            return null;
        }
        try {
            return Resources.toString(resource, StandardCharsets.UTF_8);
        } catch (final IOException e) {
            throw new IllegalStateException("Failed to read: " + resource, e);
        }
    }

    private void addMetadata(final NewRule rule, final String metadataKey) {
        final String json = readRuleDefinitionResource(metadataKey + ".json");
        if (json != null) {
            final RuleMetadata metadata = this.gson.fromJson(json, RuleMetadata.class);
            rule.setSeverity(metadata.defaultSeverity.toUpperCase(Locale.US));
            rule.setName(metadata.title);
            rule.setTags(metadata.tags);
            rule.setStatus(RuleStatus.valueOf(metadata.status.toUpperCase(Locale.US)));
            rule.setType(RuleType.valueOf(metadata.type));

            if (metadata.remediation != null) {
                // metadata.remediation is null for template rules
                rule.setDebtRemediationFunction(metadata.remediation.remediationFunction(rule.debtRemediationFunctions()));
                rule.setGapDescription(metadata.remediation.linearDesc);
            }
        }
    }

    private static class RuleMetadata {

        String title;

        String status;

        @Nullable
        Remediation remediation;

        String[] tags;

        String defaultSeverity;

        String type;
    }

    private static class Remediation {

        String func;

        String constantCost;

        String linearDesc;

        String linearOffset;

        String coeff;

        private DebtRemediationFunction remediationFunction(final DebtRemediationFunctions drf) {
            if (this.func.startsWith("Constant")) {
                return drf.constantPerIssue(this.constantCost.replace("mn", "min"));
            }
            if ("Linear".equals(this.func)) {
                return drf.linear(this.coeff.replace("mn", "min"));
            }
            return drf.linearWithOffset(this.coeff.replace("mn", "min"), this.linearOffset.replace("mn", "min"));
        }
    }
}
