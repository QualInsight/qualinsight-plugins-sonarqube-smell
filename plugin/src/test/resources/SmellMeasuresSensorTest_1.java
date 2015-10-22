package test;

import com.qualinsight.plugins.sonarqube.Smell.api.annotation.Smell;
import com.qualinsight.plugins.sonarqube.Smell.api.model.SmellType;

@Smell(minutes=10,reason="Anti-pattern",type=SmellType.ANTI_PATTERN) // Noncompliant {{Anti-pattern}}
public class SmellCheckTest_1 {
    
    @Smell(minutes=10,reason="Anti-pattern",type=SmellType.ANTI_PATTERN) // Noncompliant {{Anti-pattern}}
    Object field1;
    
    @Smell(minutes=10,reason="Anti-pattern",type=SmellType.ANTI_PATTERN) // Noncompliant {{Anti-pattern}}
    public void method() {}
    
    @Smell(minutes=10,reason="Anti-pattern",type=SmellType.ANTI_PATTERN) // Noncompliant {{Anti-pattern}}
    enum EnumType {
        A,
        @Smell(minutes=10,reason="Anti-pattern",type=SmellType.ANTI_PATTERN) // Noncompliant {{Anti-pattern}}
        B
    }
    
    @Smell(minutes=10,reason="Anti-pattern",type=SmellType.ANTI_PATTERN) // Noncompliant {{Anti-pattern}}
    public class AnnotatedDummy {
        @Smell(minutes=10,reason="Anti-pattern",type=SmellType.ANTI_PATTERN) // Noncompliant {{Anti-pattern}}
        String s;
    }
}