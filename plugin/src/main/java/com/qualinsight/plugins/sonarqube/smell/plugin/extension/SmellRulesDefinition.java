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

import java.util.List;
import com.google.common.collect.ImmutableList;
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
    public static final String REPOSITORY_KEY = "qualinsight-smell";

    /**
     * Repository name.
     */
    public static final String REPOSITORY_NAME = "QualInsight";

    @SuppressWarnings("rawtypes")
    @Override
    public void define(final Context context) {
        final NewRepository repository = context.createRepository(REPOSITORY_KEY, Java.KEY)
            .setName(REPOSITORY_NAME);
        final List<Class> checkClasses = ImmutableList.<Class> builder()
            .addAll(SmellChecksRegistrar.checkClasses())
            .build();
        AnnotationBasedRulesDefinition.load(repository, Java.KEY, checkClasses);
        for (final NewRule rule : repository.rules()) {
            rule.setInternalKey(rule.key());
        }
        repository.done();
    }
}
