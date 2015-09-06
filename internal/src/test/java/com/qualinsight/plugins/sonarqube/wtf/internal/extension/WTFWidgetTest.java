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

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class WTFWidgetTest {

    private static final Object EXPECTED_ID = "wtf";

    private static final Object EXPECTED_TITLE = "WTF! Measures";

    private static final Object EXPECTED_TEMPLATE_PATH = "/com/qualinsight/plugins/sonarqube/wtf/internal/ui/extension/wtf.measures.widget.html.erb";

    private WTFWidget sut;

    @Before
    public void setUp() {
        this.sut = new WTFWidget();
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
