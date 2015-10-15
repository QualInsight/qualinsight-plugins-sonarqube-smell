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
package com.qualinsight.plugins.sonarqube.smell.plugin;

/**
 * Class that provides plugin's property keys
 *
 * @author Michel Pawlak
 */
public final class SmellPropertyKeys {

    /**
     * Property key that allows widget title modification
     */
    public static final String WIDGET_TITLE_KEY = "widget.smells.title";

    /**
     * Property key that allows widget id modification
     */
    public static final String WIDGET_ID_KEY = "widget.smells.id";

    private SmellPropertyKeys() {
    }
}
