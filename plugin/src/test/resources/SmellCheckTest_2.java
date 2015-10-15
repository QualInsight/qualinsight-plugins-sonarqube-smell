package test;

import com.qualinsight.plugins.sonarqube.smell.api.annotation.Smell;
import com.qualinsight.plugins.sonarqube.smell.api.annotation.Smells;
import com.qualinsight.plugins.sonarqube.smell.api.model.SmellType;

// Noncompliant @+3 {{Anti-pattern}}
// Noncompliant @+3 {{Anti-pattern}}
@Smells({
    @Smell(minutes = 10, reason = "Anti-pattern", type = SmellType.ANTI_PATTERN),
    @Smell(minutes = 10, reason = "Anti-pattern", type = SmellType.ANTI_PATTERN)
})
public class SmellCheckTest_2 {

    // Noncompliant @+3 {{Anti-pattern}}
    // Noncompliant @+3 {{Anti-pattern}}
    @Smells({
        @Smell(minutes = 10, reason = "Anti-pattern", type = SmellType.ANTI_PATTERN),
        @Smell(minutes = 10, reason = "Anti-pattern", type = SmellType.ANTI_PATTERN)
    })
    Object field1;

    // Noncompliant @+3 {{Anti-pattern}}
    // Noncompliant @+3 {{Anti-pattern}}
    @Smells({
        @Smell(minutes = 10, reason = "Anti-pattern", type = SmellType.ANTI_PATTERN),
        @Smell(minutes = 10, reason = "Anti-pattern", type = SmellType.ANTI_PATTERN)
    })
    public void method() {
    }

    // Noncompliant @+3 {{Anti-pattern}}
    // Noncompliant @+3 {{Anti-pattern}}
    @Smells({
        @Smell(minutes = 10, reason = "Anti-pattern", type = SmellType.ANTI_PATTERN),
        @Smell(minutes = 10, reason = "Anti-pattern", type = SmellType.ANTI_PATTERN)

    })
    enum EnumType {
        A,
        // Noncompliant @+3 {{Anti-pattern}}
        // Noncompliant @+3 {{Anti-pattern}}
        @Smells({
            @Smell(minutes = 10, reason = "Anti-pattern", type = SmellType.ANTI_PATTERN),
            @Smell(minutes = 10, reason = "Anti-pattern", type = SmellType.ANTI_PATTERN)

        })
        B
    }

    // Noncompliant @+3 {{Anti-pattern}}
    // Noncompliant @+3 {{Anti-pattern}}
    @Smells({
        @Smell(minutes = 10, reason = "Anti-pattern", type = SmellType.ANTI_PATTERN),
        @Smell(minutes = 10, reason = "Anti-pattern", type = SmellType.ANTI_PATTERN)

    })
    private class AnnotatedDummy {

        // Noncompliant @+3 {{Anti-pattern}}
        // Noncompliant @+3 {{Anti-pattern}}
        @Smells({
            @Smell(minutes = 10, reason = "Anti-pattern", type = SmellType.ANTI_PATTERN),
            @Smell(minutes = 10, reason = "Anti-pattern", type = SmellType.ANTI_PATTERN)

        })
        final String s = "";
    }
}