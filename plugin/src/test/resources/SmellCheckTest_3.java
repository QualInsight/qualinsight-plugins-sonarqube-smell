package test;

import com.qualinsight.plugins.sonarqube.smell.api.annotation.Smell;
import com.qualinsight.plugins.sonarqube.smell.api.model.SmellType;

// Noncompliant @+1 {{Anti-pattern}}
@Smell(minutes = 10, reason = "Anti-pattern", type = SmellType.ANTI_PATTERN)
public class SmellCheckTest_3 {

    public static final String CONSTANT1 = "Anti-";

    public static final String CONSTANT2 = "pattern";

    public static final String CONSTANT3 = "Anti-pattern";

    // Noncompliant @+1 {{Anti-pattern}}
    @Smell(minutes = 10, reason = "Anti-pattern", type = SmellType.ANTI_PATTERN)
    Object field1;

    // Noncompliant @+1 {{Anti-pattern}}
    @Smell(minutes = 10, reason = "Anti-pattern", type = SmellType.ANTI_PATTERN)
    public void method() {
    }

    // Noncompliant @+1 {{Anti-pattern}}
    @Smell(minutes = 10, reason = "Anti-" + "pattern", type = SmellType.ANTI_PATTERN)
    public void method2() {
    }

    // Noncompliant @+1 {{Anti-pattern}}
    @Smell(minutes = 10, reason = "Anti" + "-" + "pattern", type = SmellType.ANTI_PATTERN)
    public void method3() {
    }

    // Noncompliant @+1 {{Anti-pattern}}
    @Smell(minutes = 10, reason = CONSTANT1 + "pattern", type = SmellType.ANTI_PATTERN)
    public void method4() {
    }

    // Noncompliant @+1 {{Anti-pattern}}
    @Smell(minutes = 10, reason = "Anti-" + CONSTANT2, type = SmellType.ANTI_PATTERN)
    public void method5() {
    }

    // Noncompliant @+1 {{Anti-pattern}}
    @Smell(minutes = 10, reason = CONSTANT1 + CONSTANT2, type = SmellType.ANTI_PATTERN)
    public void method6() {
    }

    // Noncompliant @+1 {{Anti-pattern}}
    @Smell(minutes = 10, reason = CONSTANT3, type = SmellType.ANTI_PATTERN)
    public void method7() {
    }

    // Noncompliant @+1 {{Anti-pattern}}
    @Smell(minutes = 10, reason = "Anti-pattern", type = SmellType.ANTI_PATTERN)
    enum EnumType {
        A,
        // Noncompliant @+1 {{Anti-pattern}}
        @Smell(minutes = 10, reason = "Anti-pattern", type = SmellType.ANTI_PATTERN)
        B
    }

    // Noncompliant @+1 {{Anti-pattern}}
    @Smell(minutes = 10, reason = "Anti-pattern", type = SmellType.ANTI_PATTERN)
    public class AnnotatedDummy {

        // Noncompliant @+1 {{Anti-pattern}}
        @Smell(minutes = 10, reason = "Anti-pattern", type = SmellType.ANTI_PATTERN)
        String s;
    }
}