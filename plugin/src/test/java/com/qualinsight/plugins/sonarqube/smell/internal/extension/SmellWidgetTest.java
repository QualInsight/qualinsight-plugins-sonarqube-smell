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
package com.qualinsight.plugins.sonarqube.smell.internal.extension;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.sonar.api.config.Settings;
import com.qualinsight.plugins.sonarqube.smell.internal.SmellPropertyKeys;
import com.qualinsight.plugins.sonarqube.smell.internal.extension.SmellWidget;

@RunWith(MockitoJUnitRunner.class)
public class SmellWidgetTest {

    private static final String EXPECTED_ID = "smells";

    private static final String EXPECTED_TITLE = "Code Smells";

    private static final String EXPECTED_TEMPLATE_PATH = "/com/qualinsight/plugins/sonarqube/smell/internal/ui/extension/smells.measures.widget.html.erb";

    @Mock
    private Settings settings;

    private SmellWidget sut;

    @Before
    public void setUp() {
        Mockito.when(this.settings.getString(SmellPropertyKeys.WIDGET_TITLE_KEY))
            .thenReturn(EXPECTED_TITLE);
        this.sut = new SmellWidget(this.settings);
    }

    @Test
    public void getId_should_return_expectedId() {
        Assertions.assertThat(this.sut.getId())
            .isEqualTo(EXPECTED_ID);
    }

    @Test
    public void getTitle_should_return_expectedTitle() {
        Assertions.assertThat(this.sut.getTitle())
            .isEqualTo(EXPECTED_TITLE);
    }

    @Test
    public void getTemplatePath_should_return_expectedTemplatePath() {
        Assertions.assertThat(this.sut.getTemplatePath())
            .isEqualTo(EXPECTED_TEMPLATE_PATH);
    }

}
