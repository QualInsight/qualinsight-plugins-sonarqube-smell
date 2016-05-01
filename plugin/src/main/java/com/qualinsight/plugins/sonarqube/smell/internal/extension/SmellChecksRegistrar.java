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

import java.util.List;
import com.google.common.collect.Lists;
import org.sonar.plugins.java.api.CheckRegistrar;
import org.sonar.plugins.java.api.JavaCheck;
import com.qualinsight.plugins.sonarqube.smell.api.annotation.Smell;
import com.qualinsight.plugins.sonarqube.smell.internal.check.AbbreviationsUsageSmellCheck;
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
        checkClasses.add(AbbreviationsUsageSmellCheck.class);
        checkClasses.add(AntiPatternSmellCheck.class);
        checkClasses.add(BadDesignSmellCheck.class);
        checkClasses.add(BadFrameworkUsageSmellCheck.class);
        checkClasses.add(BadLoggingSmellCheck.class);
        checkClasses.add(HowCommentSmellCheck.class);
        checkClasses.add(IndecentExposureSmellCheck.class);
        checkClasses.add(MeaninglessCommentSmellCheck.class);
        checkClasses.add(MiddleManSmellCheck.class);
        checkClasses.add(MissingImplementationSmellCheck.class);
        checkClasses.add(MultipleResponsibilitiesSmellCheck.class);
        checkClasses.add(NonExceptionSmellCheck.class);
        checkClasses.add(OddballSolutionSmellCheck.class);
        checkClasses.add(OvercomplicatedAlgorithmSmellCheck.class);
        checkClasses.add(PrimitivesObsessionSmellCheck.class);
        checkClasses.add(RefusedBequestSmellCheck.class);
        checkClasses.add(ReinventedWheelSmellCheck.class);
        checkClasses.add(SolutionSprawlSmellCheck.class);
        checkClasses.add(SpeculativeGeneralitySmellCheck.class);
        checkClasses.add(UncommunicativeNameSmellCheck.class);
        checkClasses.add(UselessTestSmellCheck.class);
        checkClasses.add(WrongLanguageSmellCheck.class);
        checkClasses.add(WrongLogicSmellCheck.class);
        testCheckClasses = Lists.<Class<? extends JavaCheck>> newArrayList();
        testCheckClasses.add(AbbreviationsUsageSmellCheck.class);
        testCheckClasses.add(AntiPatternSmellCheck.class);
        testCheckClasses.add(BadDesignSmellCheck.class);
        testCheckClasses.add(BadFrameworkUsageSmellCheck.class);
        testCheckClasses.add(BadLoggingSmellCheck.class);
        testCheckClasses.add(HowCommentSmellCheck.class);
        testCheckClasses.add(IndecentExposureSmellCheck.class);
        testCheckClasses.add(MeaninglessCommentSmellCheck.class);
        testCheckClasses.add(MiddleManSmellCheck.class);
        testCheckClasses.add(MissingImplementationSmellCheck.class);
        testCheckClasses.add(MultipleResponsibilitiesSmellCheck.class);
        testCheckClasses.add(NonExceptionSmellCheck.class);
        testCheckClasses.add(OddballSolutionSmellCheck.class);
        testCheckClasses.add(OvercomplicatedAlgorithmSmellCheck.class);
        testCheckClasses.add(PrimitivesObsessionSmellCheck.class);
        testCheckClasses.add(RefusedBequestSmellCheck.class);
        testCheckClasses.add(ReinventedWheelSmellCheck.class);
        testCheckClasses.add(SolutionSprawlSmellCheck.class);
        testCheckClasses.add(SpeculativeGeneralitySmellCheck.class);
        testCheckClasses.add(UncommunicativeNameSmellCheck.class);
        testCheckClasses.add(UselessTestSmellCheck.class);
        testCheckClasses.add(WrongLanguageSmellCheck.class);
        testCheckClasses.add(WrongLogicSmellCheck.class);
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
