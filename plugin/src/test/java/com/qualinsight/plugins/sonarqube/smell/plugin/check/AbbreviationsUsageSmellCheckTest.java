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
package com.qualinsight.plugins.sonarqube.smell.plugin.check;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class AbbreviationsUsageSmellCheckTest {

    @Test
    public void check_shouldNot_raiseIssues_when_noAnnotationIsFound() {
        JavaCheckVerifier.verifyNoIssue("src/test/resources/AbbreviationsUsageSmellCheckTest_1.java", new AbbreviationsUsageSmellCheck());
    }

    @Test
    public void check_should_raiseIssues_when_multiAnnotationsAreFound() {
        JavaCheckVerifier.verify("src/test/resources/AbbreviationsUsageSmellCheckTest_2.java", new AbbreviationsUsageSmellCheck());
    }

    @Test
    public void check_should_raiseIssues_when_annotationAreFound() {
        JavaCheckVerifier.verify("src/test/resources/AbbreviationsUsageSmellCheckTest_3.java", new AbbreviationsUsageSmellCheck());
    }

}
