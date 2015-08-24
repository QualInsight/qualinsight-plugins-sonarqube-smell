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

import java.util.List;
import org.sonar.plugins.java.api.CheckRegistrar;
import org.sonar.plugins.java.api.JavaCheck;
import com.google.common.collect.Lists;
import com.qualinsight.plugins.sonarqube.wtf.api.annotation.WTF;
import com.qualinsight.plugins.sonarqube.wtf.api.model.WTFType;
import com.qualinsight.plugins.sonarqube.wtf.internal.check.WTFCheck;

@WTF(minutes = 20, reason = "some reason", type = WTFType.ANTI_PATTERN)
public class WTFChecksRegistrar implements CheckRegistrar {

    @WTF(minutes = 10, reason = "some reason", type = WTFType.BAD_DESIGN)
    private static final List<Class<? extends JavaCheck>> checkClasses;

    /*
     * Block that builds the list of JavaCheck once and for all.
     */
    static {
        checkClasses = Lists.<Class<? extends JavaCheck>> newArrayList();
        checkClasses.add(WTFCheck.class);
    }

    @WTF(minutes = 15, reason = "some reason", type = WTFType.WRONG_ALGORITHM)
    @Override
    public void register(final RegistrarContext registrarContext) {
        registrarContext.registerClassesForRepository(WTFRulesDefinition.REPOSITORY_KEY, checkClasses());
    }

    public static Iterable<Class<? extends JavaCheck>> checkClasses() {
        return checkClasses;
    }

}
