/*
 * This file is part of qualinsight-plugins-sonarqube-wtf-api.
 *
 * qualinsight-plugins-sonarqube-wtf-api is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * qualinsight-plugins-sonarqube-wtf-api is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with qualinsight-plugins-sonarqube-wtf-api.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.qualinsight.plugins.sonarqube.wtf.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Source retention annotation that can be used to report multiple WTFs.
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
public @interface WTFs {

    /**
     * All WTF annotations that have been put on the annotated element.
     *
     * @return Array of WTF annotations
     */
    public WTF[] value();

}
