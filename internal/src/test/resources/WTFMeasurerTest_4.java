@WTF(minutes=10,reason="",type=WTFType.ANTI_PATTERN)
package test;

import com.qualinsight.plugins.sonarqube.wtf.api.annotation.WTF;
import com.qualinsight.plugins.sonarqube.wtf.api.model.WTFType;

@WTF(minutes=10,reason="",type=WTFType.ANTI_PATTERN)
public class WTFMeasurerTest_4 {
    
    @WTF(minutes=10,reason="",type=WTFType.ANTI_PATTERN)
    Object field1;
    
    @WTF(minutes=10,reason="",type=WTFType.ANTI_PATTERN)
    public void method() {}
    
    @WTF(minutes=10,reason="",type=WTFType.ANTI_PATTERN)
    enum EnumType {
        A,
        @WTF(minutes=10,reason="",type=WTFType.ANTI_PATTERN)
        B
    }
    
    @WTF(minutes=10,reason="",type=WTFType.ANTI_PATTERN)
    public AnnotatedDummy() {
        @WTF(minutes=10,reason="",type=WTFType.ANTI_PATTERN)
        String s;
    }
}