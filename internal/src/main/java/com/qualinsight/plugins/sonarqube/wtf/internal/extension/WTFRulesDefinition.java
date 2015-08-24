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
package com.qualinsight.plugins.sonarqube.wtf.internal.extension;

import java.util.List;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.plugins.java.Java;
import org.sonar.squidbridge.annotations.AnnotationBasedRulesDefinition;
import com.google.common.collect.ImmutableList;

public class WTFRulesDefinition implements RulesDefinition {

    public static final String REPOSITORY_KEY = "qualinsight-wtf";

    public static final String REPOSITORY_NAME = "QualInsight";

    @SuppressWarnings("rawtypes")
    @Override
    public void define(final Context context) {
        final NewRepository repository = context.createRepository(REPOSITORY_KEY, Java.KEY)
            .setName(REPOSITORY_NAME);
        final List<Class> checkClasses = ImmutableList.<Class> builder()
            .addAll(WTFChecksRegistrar.checkClasses())
            .build();
        AnnotationBasedRulesDefinition.load(repository, Java.KEY, checkClasses);
        for (final NewRule rule : repository.rules()) {
            rule.setInternalKey(rule.key());
        }
        repository.done();
    }
}
