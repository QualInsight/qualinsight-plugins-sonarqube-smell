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

public class WTFMeasuresDecorator implements Decorator {

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
            LOGGER.info("decorating key: {}", resource.getKey());
            Double total = 0d;
            total += MeasureUtils.sum(true, context.getChildrenMeasures(WTFMetrics.WTF_COUNT_WRONG_LOGIC));
            total += MeasureUtils.sum(true, context.getChildrenMeasures(WTFMetrics.WTF_COUNT_ANTI_PATTERN));
            total += MeasureUtils.sum(true, context.getChildrenMeasures(WTFMetrics.WTF_COUNT_BAD_DESIGN));
            total += MeasureUtils.sum(true, context.getChildrenMeasures(WTFMetrics.WTF_COUNT_OVERCOMPLICATED_ALGORITHM));
            total += MeasureUtils.sum(true, context.getChildrenMeasures(WTFMetrics.WTF_COUNT_USELESS_TEST));
            context.saveMeasure(WTFMetrics.WTF_COUNT, total);
            LOGGER.debug("Saved measure for metric '{}' with value '{}'", WTFMetrics.WTF_COUNT.key(), total);
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}
