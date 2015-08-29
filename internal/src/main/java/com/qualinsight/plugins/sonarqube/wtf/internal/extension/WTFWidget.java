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

import org.sonar.api.web.AbstractRubyTemplate;
import org.sonar.api.web.RubyRailsWidget;
import org.sonar.api.web.WidgetCategory;
import org.sonar.api.web.WidgetScope;

/**
 * WTF! widget to be displayed on SonarQube dashboard.
 * 
 * @author Michel Pawlak
 */
@WidgetCategory("QualInsight")
@WidgetScope({
    "PROJECT"
})
public final class WTFWidget extends AbstractRubyTemplate implements RubyRailsWidget {

    @Override
    public String getId() {
        return "wtf";
    }

    @Override
    public String getTitle() {
        return "WTF! Measures";
    }

    @Override
    protected String getTemplatePath() {
        return "/com/qualinsight/plugins/sonarqube/wtf/internal/ui/extension/wtf.measures.widget.html.erb";
    }

}
