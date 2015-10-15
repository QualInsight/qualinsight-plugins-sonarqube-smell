package test;

import com.qualinsight.plugins.sonarqube.wtf.api.annotation.WTF;
import com.qualinsight.plugins.sonarqube.wtf.api.annotation.WTFs;
import com.qualinsight.plugins.sonarqube.wtf.api.model.WTFType;

// Noncompliant @+3 {{Anti-pattern}}
// Noncompliant @+3 {{Anti-pattern}}
@WTFs({
    @WTF(minutes = 10, reason = "Anti-pattern", type = WTFType.ANTI_PATTERN),
    @WTF(minutes = 10, reason = "Anti-pattern", type = WTFType.ANTI_PATTERN)
})
public class WTFCheckTest_2 {

    // Noncompliant @+3 {{Anti-pattern}}
    // Noncompliant @+3 {{Anti-pattern}}
    @WTFs({
        @WTF(minutes = 10, reason = "Anti-pattern", type = WTFType.ANTI_PATTERN),
        @WTF(minutes = 10, reason = "Anti-pattern", type = WTFType.ANTI_PATTERN)
    })
    Object field1;

    // Noncompliant @+3 {{Anti-pattern}}
    // Noncompliant @+3 {{Anti-pattern}}
    @WTFs({
        @WTF(minutes = 10, reason = "Anti-pattern", type = WTFType.ANTI_PATTERN),
        @WTF(minutes = 10, reason = "Anti-pattern", type = WTFType.ANTI_PATTERN)
    })
    public void method() {
    }

    // Noncompliant @+3 {{Anti-pattern}}
    // Noncompliant @+3 {{Anti-pattern}}
    @WTFs({
        @WTF(minutes = 10, reason = "Anti-pattern", type = WTFType.ANTI_PATTERN),
        @WTF(minutes = 10, reason = "Anti-pattern", type = WTFType.ANTI_PATTERN)

    })
    enum EnumType {
        A,
        // Noncompliant @+3 {{Anti-pattern}}
        // Noncompliant @+3 {{Anti-pattern}}
        @WTFs({
            @WTF(minutes = 10, reason = "Anti-pattern", type = WTFType.ANTI_PATTERN),
            @WTF(minutes = 10, reason = "Anti-pattern", type = WTFType.ANTI_PATTERN)

        })
        B
    }

    // Noncompliant @+3 {{Anti-pattern}}
    // Noncompliant @+3 {{Anti-pattern}}
    @WTFs({
        @WTF(minutes = 10, reason = "Anti-pattern", type = WTFType.ANTI_PATTERN),
        @WTF(minutes = 10, reason = "Anti-pattern", type = WTFType.ANTI_PATTERN)

    })
    private class AnnotatedDummy {

        // Noncompliant @+3 {{Anti-pattern}}
        // Noncompliant @+3 {{Anti-pattern}}
        @WTFs({
            @WTF(minutes = 10, reason = "Anti-pattern", type = WTFType.ANTI_PATTERN),
            @WTF(minutes = 10, reason = "Anti-pattern", type = WTFType.ANTI_PATTERN)

        })
        final String s = "";
    }
}