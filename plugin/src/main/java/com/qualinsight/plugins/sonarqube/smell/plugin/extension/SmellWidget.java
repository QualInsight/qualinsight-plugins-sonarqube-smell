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
/*
 $ * qualinsight-plugins-sonarqube-smell
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

import org.sonar.api.config.Settings;
import org.sonar.api.web.AbstractRubyTemplate;
import org.sonar.api.web.RubyRailsWidget;
import org.sonar.api.web.WidgetCategory;
import org.sonar.api.web.WidgetScope;
import com.qualinsight.plugins.sonarqube.smell.plugin.SmellPropertyKeys;

/**
 * Smell widget to be displayed on SonarQube dashboard.
 *
 * @author Michel Pawlak
 */
@WidgetCategory("QualInsight")
@WidgetScope({
    "PROJECT"
})
public final class SmellWidget extends AbstractRubyTemplate implements RubyRailsWidget {

    private String title;

    /**
     * {@link SmellWidget} IoC constructor.
     *
     * @param settings SonarQube settings
     */
    public SmellWidget(final Settings settings) {
        this.title = settings.getString(SmellPropertyKeys.WIDGET_TITLE_KEY);
    }

    @Override
    public String getId() {
        return "smells";
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    protected String getTemplatePath() {
        return "/com/qualinsight/plugins/sonarqube/smell/internal/ui/extension/smells.measures.widget.html.erb";
    }

}
