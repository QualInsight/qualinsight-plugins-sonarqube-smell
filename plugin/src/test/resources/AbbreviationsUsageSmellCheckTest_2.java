package test;

import com.qualinsight.plugins.sonarqube.smell.api.annotation.Smell;
import com.qualinsight.plugins.sonarqube.smell.api.annotation.Smells;
import com.qualinsight.plugins.sonarqube.smell.api.model.SmellType;

// Noncompliant @+3 {{Abbreviation Usage}}
@Smells({
    @Smell(minutes = 10, reason = "Anti-pattern", type = SmellType.ANTI_PATTERN),
    @Smell(minutes = 10, reason = "Abbreviation Usage", type = SmellType.ABBREVIATIONS_USAGE)
})
public class SmellCheckTest_2 {

    // Noncompliant @+3 {{Abbreviation Usage}}
    @Smells({
        @Smell(minutes = 10, reason = "Anti-pattern", type = SmellType.ANTI_PATTERN),
        @Smell(minutes = 10, reason = "Abbreviation Usage", type = SmellType.ABBREVIATIONS_USAGE)
    })
    Object field1;

    // Noncompliant @+3 {{Abbreviation Usage}}
    @Smells({
        @Smell(minutes = 10, reason = "Anti-pattern", type = SmellType.ANTI_PATTERN),
        @Smell(minutes = 10, reason = "Abbreviation Usage", type = SmellType.ABBREVIATIONS_USAGE)
    })
    public void method() {
    }

    // Noncompliant @+3 {{Abbreviation Usage}}
    @Smells({
        @Smell(minutes = 10, reason = "Anti-pattern", type = SmellType.ANTI_PATTERN),
        @Smell(minutes = 10, reason = "Abbreviation Usage", type = SmellType.ABBREVIATIONS_USAGE)
    })
    enum EnumType {
        A,
        // Noncompliant @+3 {{Abbreviation Usage}}
        @Smells({
            @Smell(minutes = 10, reason = "Anti-pattern", type = SmellType.ANTI_PATTERN),
            @Smell(minutes = 10, reason = "Abbreviation Usage", type = SmellType.ABBREVIATIONS_USAGE)
        })
        B
    }

    // Noncompliant @+3 {{Abbreviation Usage}}
    @Smells({
        @Smell(minutes = 10, reason = "Anti-pattern", type = SmellType.ANTI_PATTERN),
        @Smell(minutes = 10, reason = "Abbreviation Usage", type = SmellType.ABBREVIATIONS_USAGE)
    })
    private class AnnotatedDummy {

        // Noncompliant @+3 {{Abbreviation Usage}}
        @Smells({
            @Smell(minutes = 10, reason = "Anti-pattern", type = SmellType.ANTI_PATTERN),
            @Smell(minutes = 10, reason = "Abbreviation Usage", type = SmellType.ABBREVIATIONS_USAGE)
        })
        final String s = "";
    }

    @Smells({
        @Smell(minutes = 10, reason = "Anti-pattern", type = SmellType.ANTI_PATTERN),
    })
    private class AnotherAnnotatedDummy {

        @Smells({
            @Smell(minutes = 10, reason = "Anti-pattern", type = SmellType.ANTI_PATTERN),
        })
        final String s = "";
    }
}