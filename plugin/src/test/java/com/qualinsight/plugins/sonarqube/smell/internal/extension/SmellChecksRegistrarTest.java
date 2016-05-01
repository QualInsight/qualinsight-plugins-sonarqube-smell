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

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import net.jcip.annotations.NotThreadSafe;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sonar.plugins.java.api.CheckRegistrar.RegistrarContext;
import com.qualinsight.plugins.sonarqube.smell.api.model.SmellType;
import com.qualinsight.plugins.sonarqube.smell.internal.check.AbbreviationsUsageSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.internal.check.AbstractSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.internal.check.AntiPatternSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.internal.check.BadDesignSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.internal.check.BadFrameworkUsageSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.internal.check.BadLoggingSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.internal.check.HowCommentSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.internal.check.IndecentExposureSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.internal.check.MeaninglessCommentSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.internal.check.MiddleManSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.internal.check.MissingImplementationSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.internal.check.MultipleResponsibilitiesSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.internal.check.NonExceptionSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.internal.check.OddballSolutionSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.internal.check.OvercomplicatedAlgorithmSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.internal.check.PrimitivesObsessionSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.internal.check.RefusedBequestSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.internal.check.ReinventedWheelSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.internal.check.SolutionSprawlSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.internal.check.SpeculativeGeneralitySmellCheck;
import com.qualinsight.plugins.sonarqube.smell.internal.check.UncommunicativeNameSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.internal.check.UselessTestSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.internal.check.WrongLanguageSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.internal.check.WrongLogicSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.internal.extension.SmellChecksRegistrar;
import com.qualinsight.plugins.sonarqube.smell.internal.extension.SmellRulesDefinition;

@NotThreadSafe
@RunWith(JUnitParamsRunner.class)
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

    @Test
    @Parameters
    public void register_should_registerChecksHandlingingCorrectSmellType(final SmellType type, final AbstractSmellCheck check) {
        assert (check.smellType().equals(type));
    }

    private Object[] parametersForRegister_should_registerChecksHandlingingCorrectSmellType() {
        return new Object[] {
            new Object[] {
                SmellType.ABBREVIATIONS_USAGE,
                new AbbreviationsUsageSmellCheck()
            },
            new Object[] {
                SmellType.ANTI_PATTERN,
                new AntiPatternSmellCheck()
            },
            new Object[] {
                SmellType.BAD_DESIGN,
                new BadDesignSmellCheck()
            },
            new Object[] {
                SmellType.BAD_FRAMEWORK_USAGE,
                new BadFrameworkUsageSmellCheck()
            },
            new Object[] {
                SmellType.BAD_LOGGING,
                new BadLoggingSmellCheck()
            },
            new Object[] {
                SmellType.HOW_COMMENT,
                new HowCommentSmellCheck()
            },
            new Object[] {
                SmellType.INDECENT_EXPOSURE,
                new IndecentExposureSmellCheck()
            },
            new Object[] {
                SmellType.MEANINGLESS_COMMENT,
                new MeaninglessCommentSmellCheck()
            },
            new Object[] {
                SmellType.MIDDLE_MAN,
                new MiddleManSmellCheck()
            },
            new Object[] {
                SmellType.MISSING_IMPLEMENTATION,
                new MissingImplementationSmellCheck()
            },
            new Object[] {
                SmellType.MULTIPLE_RESPONSIBILITIES,
                new MultipleResponsibilitiesSmellCheck()
            },
            new Object[] {
                SmellType.NON_EXCEPTION,
                new NonExceptionSmellCheck()
            },
            new Object[] {
                SmellType.ODDBALL_SOLUTION,
                new OddballSolutionSmellCheck()
            },
            new Object[] {
                SmellType.OVERCOMPLICATED_ALGORITHM,
                new OvercomplicatedAlgorithmSmellCheck()
            },
            new Object[] {
                SmellType.PRIMITIVES_OBSESSION,
                new PrimitivesObsessionSmellCheck()
            },
            new Object[] {
                SmellType.REFUSED_BEQUEST,
                new RefusedBequestSmellCheck()
            },
            new Object[] {
                SmellType.REINVENTED_WHEEL,
                new ReinventedWheelSmellCheck()
            },
            new Object[] {
                SmellType.SOLUTION_SPRAWL,
                new SolutionSprawlSmellCheck()
            },
            new Object[] {
                SmellType.SPECULATIVE_GENERALITY,
                new SpeculativeGeneralitySmellCheck()
            },
            new Object[] {
                SmellType.UNCOMMUNICATIVE_NAME,
                new UncommunicativeNameSmellCheck()
            },
            new Object[] {
                SmellType.USELESS_TEST,
                new UselessTestSmellCheck()
            },
            new Object[] {
                SmellType.WRONG_LANGUAGE,
                new WrongLanguageSmellCheck()
            },
            new Object[] {
                SmellType.WRONG_LOGIC,
                new WrongLogicSmellCheck()
            },
        };
    }

}
