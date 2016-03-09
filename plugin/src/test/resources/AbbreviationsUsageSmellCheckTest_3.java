package test;

import com.qualinsight.plugins.sonarqube.smell.api.annotation.Smell;
import com.qualinsight.plugins.sonarqube.smell.api.model.SmellType;

// Noncompliant @+1 {{Abbreviation Usage}}
@Smell(minutes = 10, reason = "Abbreviation Usage", type = SmellType.ABBREVIATIONS_USAGE)
public class SmellCheckTest_3 {

    public static final String CONSTANT1 = "Abbreviation";

    public static final String CONSTANT2 = " Usage";

    public static final String CONSTANT3 = "Abbreviation Usage";

    // Noncompliant @+1 {{Abbreviation Usage}}
    @Smell(minutes = 10, reason = "Abbreviation Usage", type = SmellType.ABBREVIATIONS_USAGE)
    Object field1;

    // Noncompliant @+1 {{Abbreviation Usage}}
    @Smell(minutes = 10, reason = "Abbreviation Usage", type = SmellType.ABBREVIATIONS_USAGE)
    public void method() {
    }

    // Noncompliant @+1 {{Abbreviation Usage}}
    @Smell(minutes = 10, reason = "Abbreviation" + " Usage", type = SmellType.ABBREVIATIONS_USAGE)
    public void method2() {
    }

    // Noncompliant @+1 {{Abbreviation Usage}}
    @Smell(minutes = 10, reason = "Abbreviation" + " " + "Usage", type = SmellType.ABBREVIATIONS_USAGE)
    public void method3() {
    }

    // Noncompliant @+1 {{Abbreviation Usage}}
    @Smell(minutes = 10, reason = CONSTANT1 + " Usage", type = SmellType.ABBREVIATIONS_USAGE)
    public void method4() {
    }

    // Noncompliant @+1 {{Abbreviation Usage}}
    @Smell(minutes = 10, reason = "Abbreviation" + CONSTANT2, type = SmellType.ABBREVIATIONS_USAGE)
    public void method5() {
    }

    // Noncompliant @+1 {{Abbreviation Usage}}
    @Smell(minutes = 10, reason = CONSTANT1 + CONSTANT2, type = SmellType.ABBREVIATIONS_USAGE)
    public void method6() {
    }

    // Noncompliant @+1 {{Abbreviation Usage}}
    @Smell(minutes = 10, reason = CONSTANT3, type = SmellType.ABBREVIATIONS_USAGE)
    public void method7() {
    }

    // Noncompliant @+1 {{Abbreviation Usage}}
    @Smell(minutes = 10, reason = "Abbreviation Usage", type = SmellType.ABBREVIATIONS_USAGE)
    enum EnumType {
        A,
        // Noncompliant @+1 {{Abbreviation Usage}}
        @Smell(minutes = 10, reason = "Abbreviation Usage", type = SmellType.ABBREVIATIONS_USAGE)
        B
    }

    // Noncompliant @+1 {{Abbreviation Usage}}
    @Smell(minutes = 10, reason = "Abbreviation Usage", type = SmellType.ABBREVIATIONS_USAGE)
    public class AnnotatedDummy {

        // Noncompliant @+1 {{Abbreviation Usage}}
        @Smell(minutes = 10, reason = "Abbreviation Usage", type = SmellType.ABBREVIATIONS_USAGE)
        String s;
    }
}