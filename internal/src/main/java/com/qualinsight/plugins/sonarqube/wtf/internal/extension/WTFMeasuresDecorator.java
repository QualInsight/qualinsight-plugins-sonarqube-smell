/*
 * qualinsight-plugins-sonarqube-wtf
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
package com.qualinsight.plugins.sonarqube.wtf.internal.extension;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.Decorator;
import org.sonar.api.batch.DecoratorContext;
import org.sonar.api.batch.fs.FilePredicate;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.measures.MeasureUtils;
import org.sonar.api.resources.Project;
import org.sonar.api.resources.Resource;
import org.sonar.api.resources.ResourceUtils;
import org.sonar.plugins.java.Java;
import com.google.common.annotations.VisibleForTesting;

/**
 * {@link Decorator} that aggregates WTF! measures at project level.
 *
 * @author Michel Pawlak
 */
public final class WTFMeasuresDecorator implements Decorator {

    private static final Logger LOGGER = LoggerFactory.getLogger(WTFMeasuresDecorator.class);

    private FileSystem fileSystem;

    private FilePredicate javaFilesPredicate;

    /**
     * {@link WTFMeasuresDecorator} IoC constructor.
     *
     * @param fileSystem SonarQube {@link FileSystem}
     */
    public WTFMeasuresDecorator(final FileSystem fileSystem) {
        this.fileSystem = fileSystem;
        this.javaFilesPredicate = this.fileSystem.predicates()
            .hasLanguage(Java.KEY);
    }

    @Override
    public boolean shouldExecuteOnProject(final Project project) {
        return this.fileSystem.hasFiles(this.javaFilesPredicate);
    }

    @Override
    public void decorate(final Resource resource, final DecoratorContext context) {
        if (ResourceUtils.isProject(resource)) {
            LOGGER.debug("decorating key: {}", resource.getKey());
            decorate(context);
        }
    }

    /**
     * Computes WTF_COUNT metric by summing all children WTF measures.
     *
     * @param context {@link DecoratorContext} to which the measure has to be saved to.
     */
    @VisibleForTesting
    public void decorate(final DecoratorContext context) {
        Double total = 0d;
        total += MeasureUtils.sum(true, context.getChildrenMeasures(WTFMetrics.WTF_COUNT_ABBREVIATIONS_USAGE));
        total += MeasureUtils.sum(true, context.getChildrenMeasures(WTFMetrics.WTF_COUNT_ANTI_PATTERN));
        total += MeasureUtils.sum(true, context.getChildrenMeasures(WTFMetrics.WTF_COUNT_BAD_DESIGN));
        total += MeasureUtils.sum(true, context.getChildrenMeasures(WTFMetrics.WTF_COUNT_BAD_LOGGING));
        total += MeasureUtils.sum(true, context.getChildrenMeasures(WTFMetrics.WTF_COUNT_HOW_COMMENT));
        total += MeasureUtils.sum(true, context.getChildrenMeasures(WTFMetrics.WTF_COUNT_INDECENT_EXPOSURE));
        total += MeasureUtils.sum(true, context.getChildrenMeasures(WTFMetrics.WTF_COUNT_MEANINGLESS_COMMENT));
        total += MeasureUtils.sum(true, context.getChildrenMeasures(WTFMetrics.WTF_COUNT_MIDDLE_MAN));
        total += MeasureUtils.sum(true, context.getChildrenMeasures(WTFMetrics.WTF_COUNT_MISSING_IMPLEMENTATION));
        total += MeasureUtils.sum(true, context.getChildrenMeasures(WTFMetrics.WTF_COUNT_MULTIPLE_RESPONSIBILITIES));
        total += MeasureUtils.sum(true, context.getChildrenMeasures(WTFMetrics.WTF_COUNT_NON_EXCEPTION));
        total += MeasureUtils.sum(true, context.getChildrenMeasures(WTFMetrics.WTF_COUNT_ODDBALL_SOLUTION));
        total += MeasureUtils.sum(true, context.getChildrenMeasures(WTFMetrics.WTF_COUNT_OVERCOMPLICATED_ALGORITHM));
        total += MeasureUtils.sum(true, context.getChildrenMeasures(WTFMetrics.WTF_COUNT_PRIMITIVES_OBSESSION));
        total += MeasureUtils.sum(true, context.getChildrenMeasures(WTFMetrics.WTF_COUNT_REFUSED_BEQUEST));
        total += MeasureUtils.sum(true, context.getChildrenMeasures(WTFMetrics.WTF_COUNT_REINVENTED_WHEEL));
        total += MeasureUtils.sum(true, context.getChildrenMeasures(WTFMetrics.WTF_COUNT_SOLUTION_SPRAWL));
        total += MeasureUtils.sum(true, context.getChildrenMeasures(WTFMetrics.WTF_COUNT_SPECULATIVE_GENERALITY));
        total += MeasureUtils.sum(true, context.getChildrenMeasures(WTFMetrics.WTF_COUNT_UNCOMMUNICATIVE_NAME));
        total += MeasureUtils.sum(true, context.getChildrenMeasures(WTFMetrics.WTF_COUNT_BAD_FRAMEWORK_USAGE));
        total += MeasureUtils.sum(true, context.getChildrenMeasures(WTFMetrics.WTF_COUNT_USELESS_TEST));
        total += MeasureUtils.sum(true, context.getChildrenMeasures(WTFMetrics.WTF_COUNT_WRONG_LANGUAGE));
        total += MeasureUtils.sum(true, context.getChildrenMeasures(WTFMetrics.WTF_COUNT_WRONG_LOGIC));
        context.saveMeasure(WTFMetrics.WTF_COUNT, total);
        LOGGER.debug("Saved measure for metric '{}' with value '{}'", WTFMetrics.WTF_COUNT.key(), total);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}
