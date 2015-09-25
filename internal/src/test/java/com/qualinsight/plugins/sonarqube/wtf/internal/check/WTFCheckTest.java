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
package com.qualinsight.plugins.sonarqube.wtf.internal.check;

import org.junit.Test;
import com.qualinsight.libs.sonarqube.test.check.JavaCheckAssertions;

public class WTFCheckTest {

    @Test
    public void check_shouldNot_raiseIssues_when_noAnnotationIsFound() {
        JavaCheckAssertions.assertHasNoIssue("src/test/resources/WTFCheckTest_1.java", new WTFCheck());
    }

    @Test
    public void check_should_raiseIssues_when_multiAnnotationsAreFound() {
        JavaCheckAssertions.assertHasIssues("src/test/resources/WTFCheckTest_2.java", new WTFCheck());
    }

    @Test
    public void check_should_raiseIssues_when_annotationAreFound() {
        JavaCheckAssertions.assertHasIssues("src/test/resources/WTFCheckTest_3.java", new WTFCheck());
    }

}
