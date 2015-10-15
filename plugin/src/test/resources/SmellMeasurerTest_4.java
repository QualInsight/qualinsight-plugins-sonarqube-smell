@Smell(minutes=10,reason="",type=SmellType.ANTI_PATTERN)
package test;

import com.qualinsight.plugins.sonarqube.Smell.api.annotation.Smell;
import com.qualinsight.plugins.sonarqube.Smell.api.model.SmellType;

@Smell(minutes=10,reason="",type=SmellType.ANTI_PATTERN)
public class SmellMeasurerTest_4 {
    
    @Smell(minutes=10,reason="",type=SmellType.ANTI_PATTERN)
    Object field1;
    
    @Smell(minutes=10,reason="",type=SmellType.ANTI_PATTERN)
    public void method() {}
    
    @Smell(minutes=10,reason="",type=SmellType.ANTI_PATTERN)
    enum EnumType {
        A,
        @Smell(minutes=10,reason="",type=SmellType.ANTI_PATTERN)
        B
    }
    
    @Smell(minutes=10,reason="",type=SmellType.ANTI_PATTERN)
    public AnnotatedDummy() {
        @Smell(minutes=10,reason="",type=SmellType.ANTI_PATTERN)
        String s;
    }
}