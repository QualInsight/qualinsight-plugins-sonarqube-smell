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
package com.qualinsight.plugins.sonarqube.smell.internal.check;

import org.junit.Ignore;
import org.junit.Test;
import com.qualinsight.libs.sonarqube.test.check.JavaCheckAssertions;
import com.qualinsight.plugins.sonarqube.smell.plugin.check.SmellCheck;

@Ignore("Test need to be rewritten after migration to SQ 5.2")
public class SmellCheckTest {

    @Test
    public void check_shouldNot_raiseIssues_when_noAnnotationIsFound() {
        JavaCheckAssertions.assertHasNoIssue("src/test/resources/SmellCheckTest_1.java", new SmellCheck());
    }

    @Test
    public void check_should_raiseIssues_when_multiAnnotationsAreFound() {
        JavaCheckAssertions.assertHasIssues("src/test/resources/SmellCheckTest_2.java", new SmellCheck());
    }

    @Test
    public void check_should_raiseIssues_when_annotationAreFound() {
        JavaCheckAssertions.assertHasIssues("src/test/resources/SmellCheckTest_3.java", new SmellCheck());
    }

}
