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
import com.google.common.collect.Lists;
import org.sonar.plugins.java.api.CheckRegistrar;
import org.sonar.plugins.java.api.JavaCheck;
import com.qualinsight.plugins.sonarqube.smell.api.annotation.Smell;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.AbbreviationsUsageSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.AntiPatternSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.BadDesignSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.BadFrameworkUsageSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.BadLoggingSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.HowCommentSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.IndecentExposureSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.MeaninglessCommentSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.MiddleManSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.MissingDocumentationSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.MissingImplementationSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.MissingTestSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.MultipleResponsibilitiesSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.NonComplianceWithStandardsSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.NonExceptionSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.OddballSolutionSmellCheck;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.OtherSmellCheck;
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

/**
 * Code Smells plugin {@link CheckRegistrar} that registers all Smell related checks. Current implementation registers the {@link Smell} annotation detection check for both source and test classes.
 *
 * @author Michel Pawlak
 */
public final class SmellChecksRegistrar implements CheckRegistrar {

    private static final List<Class<? extends JavaCheck>> checkClasses;

    private static final List<Class<? extends JavaCheck>> testCheckClasses;

    /*
     * Block that builds the list of JavaCheck once and for all.
     */
    static {
        checkClasses = Lists.<Class<? extends JavaCheck>> newArrayList();
        // 0001
        checkClasses.add(AbbreviationsUsageSmellCheck.class);
        // 0002
        checkClasses.add(AntiPatternSmellCheck.class);
        // 0003
        checkClasses.add(BadDesignSmellCheck.class);
        // 0004
        checkClasses.add(BadFrameworkUsageSmellCheck.class);
        // 0005
        checkClasses.add(BadLoggingSmellCheck.class);
        // 0006
        checkClasses.add(HowCommentSmellCheck.class);
        // 0007
        checkClasses.add(IndecentExposureSmellCheck.class);
        // 0008
        checkClasses.add(MeaninglessCommentSmellCheck.class);
        // 0009
        checkClasses.add(MiddleManSmellCheck.class);
        // 0010
        checkClasses.add(MissingImplementationSmellCheck.class);
        // 0011
        checkClasses.add(MultipleResponsibilitiesSmellCheck.class);
        // 0012
        checkClasses.add(NonExceptionSmellCheck.class);
        // 0013
        checkClasses.add(OddballSolutionSmellCheck.class);
        // 0014
        checkClasses.add(OvercomplicatedAlgorithmSmellCheck.class);
        // 0015
        checkClasses.add(PrimitivesObsessionSmellCheck.class);
        // 0016
        checkClasses.add(RefusedBequestSmellCheck.class);
        // 0017
        checkClasses.add(ReinventedWheelSmellCheck.class);
        // 0018
        checkClasses.add(SolutionSprawlSmellCheck.class);
        // 0019
        checkClasses.add(SpeculativeGeneralitySmellCheck.class);
        // 0020
        checkClasses.add(UncommunicativeNameSmellCheck.class);
        // 0021
        checkClasses.add(UselessTestSmellCheck.class);
        // 0022
        checkClasses.add(WrongLanguageSmellCheck.class);
        // 0023
        checkClasses.add(WrongLogicSmellCheck.class);
        // 0024
        checkClasses.add(MissingDocumentationSmellCheck.class);
        // 0025
        checkClasses.add(MissingTestSmellCheck.class);
        // 0026
        checkClasses.add(OtherSmellCheck.class);
        // 0027
        checkClasses.add(NonComplianceWithStandardsSmellCheck.class);

        testCheckClasses = Lists.<Class<? extends JavaCheck>> newArrayList();
        // 0001
        testCheckClasses.add(AbbreviationsUsageSmellCheck.class);
        // 0002
        testCheckClasses.add(AntiPatternSmellCheck.class);
        // 0003
        testCheckClasses.add(BadDesignSmellCheck.class);
        // 0004
        testCheckClasses.add(BadFrameworkUsageSmellCheck.class);
        // 0005
        testCheckClasses.add(BadLoggingSmellCheck.class);
        // 0006
        testCheckClasses.add(HowCommentSmellCheck.class);
        // 0007
        testCheckClasses.add(IndecentExposureSmellCheck.class);
        // 0008
        testCheckClasses.add(MeaninglessCommentSmellCheck.class);
        // 0009
        testCheckClasses.add(MiddleManSmellCheck.class);
        // 0010
        testCheckClasses.add(MissingImplementationSmellCheck.class);
        // 0011
        testCheckClasses.add(MultipleResponsibilitiesSmellCheck.class);
        // 0012
        testCheckClasses.add(NonExceptionSmellCheck.class);
        // 0013
        testCheckClasses.add(OddballSolutionSmellCheck.class);
        // 0014
        testCheckClasses.add(OvercomplicatedAlgorithmSmellCheck.class);
        // 0015
        testCheckClasses.add(PrimitivesObsessionSmellCheck.class);
        // 0016
        testCheckClasses.add(RefusedBequestSmellCheck.class);
        // 0017
        testCheckClasses.add(ReinventedWheelSmellCheck.class);
        // 0018
        testCheckClasses.add(SolutionSprawlSmellCheck.class);
        // 0019
        testCheckClasses.add(SpeculativeGeneralitySmellCheck.class);
        // 0020
        testCheckClasses.add(UncommunicativeNameSmellCheck.class);
        // 0021
        testCheckClasses.add(UselessTestSmellCheck.class);
        // 0022
        testCheckClasses.add(WrongLanguageSmellCheck.class);
        // 0023
        testCheckClasses.add(WrongLogicSmellCheck.class);
        // 0024
        testCheckClasses.add(MissingDocumentationSmellCheck.class);
        // 0025
        testCheckClasses.add(MissingTestSmellCheck.class);
        // 0026
        testCheckClasses.add(OtherSmellCheck.class);
        // 0027
        testCheckClasses.add(NonComplianceWithStandardsSmellCheck.class);
    }

    @Override
    public void register(final RegistrarContext registrarContext) {
        registrarContext.registerClassesForRepository(SmellRulesDefinition.REPOSITORY_KEY, checkClasses(), testCheckClasses());
    }

    /**
     * Returns all {@link JavaCheck} that need to be applied to source code.
     *
     * @return checks to be applied to source code
     */
    public static Iterable<Class<? extends JavaCheck>> checkClasses() {
        return checkClasses;
    }

    /**
     * Returns all {@link JavaCheck} that need to be applied to test code.
     *
     * @return checks to be applied to test code
     */
    public static Iterable<Class<? extends JavaCheck>> testCheckClasses() {
        return testCheckClasses;
    }

}
