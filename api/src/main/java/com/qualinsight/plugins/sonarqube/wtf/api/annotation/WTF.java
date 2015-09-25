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
package com.qualinsight.plugins.sonarqube.wtf.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.qualinsight.plugins.sonarqube.wtf.api.model.WTFType;

/**
 * Source retention annotation that can be used to report a WTF!, i.e. a code smell that needs to be reported in SonarQube.
 *
 * @author Michel Pawlak
 */
@Retention(RetentionPolicy.SOURCE)
@Target({
    ElementType.PACKAGE,
    ElementType.TYPE,
    ElementType.METHOD,
    ElementType.CONSTRUCTOR,
    ElementType.FIELD,
    ElementType.PARAMETER,
    ElementType.LOCAL_VARIABLE
})
public @interface WTF {

    /**
     * Evaluation of the time in minutes that would be needed to remove the code smell.
     *
     * @return time in minutes that would be needed to remove the code smell.
     */
    public int minutes();

    /**
     * Provides the reason why the developper marked the code as being a code smell.
     *
     * @return the reason that led to reporting the code smell.
     */
    public String reason();

    /**
     * Indicates the category the code smell belongs to.
     *
     * @return the WTF! category the code smell is member of
     */
    public WTFType type();
}
