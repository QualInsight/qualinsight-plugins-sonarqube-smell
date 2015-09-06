package test;

import com.qualinsight.plugins.sonarqube.wtf.api.annotation.WTF;
import com.qualinsight.plugins.sonarqube.wtf.api.model.WTFType;

@WTF(minutes=10,reason="Anti-pattern",type=WTFType.ANTI_PATTERN) // Noncompliant {{Anti-pattern}}
public class WTFCheckTest_1 {
    
    @WTF(minutes=10,reason="Anti-pattern",type=WTFType.ANTI_PATTERN) // Noncompliant {{Anti-pattern}}
    Object field1;
    
    @WTF(minutes=10,reason="Anti-pattern",type=WTFType.ANTI_PATTERN) // Noncompliant {{Anti-pattern}}
    public void method() {}
    
    @WTF(minutes=10,reason="Anti-pattern",type=WTFType.ANTI_PATTERN) // Noncompliant {{Anti-pattern}}
    enum EnumType {
        A,
        @WTF(minutes=10,reason="Anti-pattern",type=WTFType.ANTI_PATTERN) // Noncompliant {{Anti-pattern}}
        B
    }
    
    @WTF(minutes=10,reason="Anti-pattern",type=WTFType.ANTI_PATTERN) // Noncompliant {{Anti-pattern}}
    public class AnnotatedDummy {
        @WTF(minutes=10,reason="Anti-pattern",type=WTFType.ANTI_PATTERN) // Noncompliant {{Anti-pattern}}
        String s;
    }
}