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

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.sonar.plugins.java.api.CheckRegistrar.RegistrarContext;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.AbbreviationsUsageSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.AntiPatternSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.BadDesignSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.BadFrameworkUsageSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.BadLoggingSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.HowCommentSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.IndecentExposureSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.MeaninglessCommentSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.MiddleManSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.MissingImplementationSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.MultipleResponsibilitiesSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.NonExceptionSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.OddballSolutionSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.OvercomplicatedAlgorithmSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.PrimitivesObsessionSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.RefusedBequestSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.ReinventedWheelSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.SolutionSprawlSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.SpeculativeGeneralitySmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.UncommunicativeNameSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.UselessTestSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.WrongLanguageSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.WrongLogicSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.extension.SmellChecksRegistrar;
import com.qualinsight.plugins.sonarqube.smell.plugin.extension.SmellRulesDefinition;

public class SmellChecksRegistrarTest {

    @SuppressWarnings("unchecked")
    @Test
    public void checkClasses_should_return_expectedChecks() {
        Assertions.assertThat(SmellChecksRegistrar.checkClasses())
            .containsExactly(AbbreviationsUsageSmellCheck.class, AntiPatternSmellCheck.class, BadDesignSmellCheck.class, BadFrameworkUsageSmellCheck.class, BadLoggingSmellCheck.class,
                HowCommentSmellCheck.class, IndecentExposureSmellCheck.class, MeaninglessCommentSmellCheck.class, MiddleManSmellCheck.class, MissingImplementationSmellCheck.class,
                MultipleResponsibilitiesSmellCheck.class, NonExceptionSmellCheck.class, OddballSolutionSmellCheck.class, OvercomplicatedAlgorithmSmellCheck.class, PrimitivesObsessionSmellCheck.class,
                RefusedBequestSmellCheck.class, ReinventedWheelSmellCheck.class, SolutionSprawlSmellCheck.class, SpeculativeGeneralitySmellCheck.class, UncommunicativeNameSmellCheck.class,
                UselessTestSmellCheck.class, WrongLanguageSmellCheck.class, WrongLogicSmellCheck.class);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testCheckClasses_should_return_expectedChecks() {
        Assertions.assertThat(SmellChecksRegistrar.testCheckClasses())
            .containsExactly(AbbreviationsUsageSmellCheck.class, AntiPatternSmellCheck.class, BadDesignSmellCheck.class, BadFrameworkUsageSmellCheck.class, BadLoggingSmellCheck.class,
                HowCommentSmellCheck.class, IndecentExposureSmellCheck.class, MeaninglessCommentSmellCheck.class, MiddleManSmellCheck.class, MissingImplementationSmellCheck.class,
                MultipleResponsibilitiesSmellCheck.class, NonExceptionSmellCheck.class, OddballSolutionSmellCheck.class, OvercomplicatedAlgorithmSmellCheck.class, PrimitivesObsessionSmellCheck.class,
                RefusedBequestSmellCheck.class, ReinventedWheelSmellCheck.class, SolutionSprawlSmellCheck.class, SpeculativeGeneralitySmellCheck.class, UncommunicativeNameSmellCheck.class,
                UselessTestSmellCheck.class, WrongLanguageSmellCheck.class, WrongLogicSmellCheck.class);
    }

    @Test
    public void register_should_registerChecks() {
        final RegistrarContext context = new RegistrarContext();
        final SmellChecksRegistrar registrar = new SmellChecksRegistrar();
        registrar.register(context);
        final SoftAssertions softly = new SoftAssertions();
        softly.assertThat(context.checkClasses())
            .containsExactlyElementsOf(SmellChecksRegistrar.checkClasses());
        softly.assertThat(context.testCheckClasses())
            .containsExactlyElementsOf(SmellChecksRegistrar.testCheckClasses());
        softly.assertThat(context.repositoryKey())
            .isEqualTo(SmellRulesDefinition.REPOSITORY_KEY);
        softly.assertAll();
    }
}
