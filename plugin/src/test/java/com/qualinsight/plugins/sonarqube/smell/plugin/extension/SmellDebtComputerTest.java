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

import com.qualinsight.plugins.sonarqube.smell.plugin.extension.AbstractSmellMeasureComputer;
import com.qualinsight.plugins.sonarqube.smell.plugin.extension.SmellDebtComputer;
import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class SmellDebtComputerTest extends AbstractSmellMeasureComputerTest {

    @Override
    protected AbstractSmellMeasureComputer sut() {
        return new SmellDebtComputer();
    }

    @Override
    protected Integer expectedSavedMeasuresCount() {
        return 1;
    }

    @Override
    protected Integer expectedGetChildrenMeasuresCount() {
        return 1;
    }

    @Override
    protected Integer expectedSavedMeasureValue() {
        return dummyMeasures().size();
    }
}
