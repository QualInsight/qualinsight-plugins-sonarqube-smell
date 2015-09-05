import com.qualinsight.plugins.sonarqube.wtf.api.annotation.WTF;
import com.qualinsight.plugins.sonarqube.wtf.api.model.WTFType;

@WTF(minutes=10,reason="",type=WTFType.ANTI_PATTERN)
public class WTFMeasurerTest_1 {
    
    @WTF(minutes=10,reason="",type=WTFType.BAD_DESIGN)
    Object field1;
    
    @WTF(minutes=10,reason="",type=WTFType.INDECENT_EXPOSURE)
    Object field2;
    
    @WTF(minutes=10,reason="",type=WTFType.MEANINGLESS_COMMENT)
    Object field3;
    
    @WTF(minutes=10,reason="",type=WTFType.MIDDLE_MAN)
    Object field4;
    
    @WTF(minutes=10,reason="",type=WTFType.ODDBALL_SOLUTION)
    Object field5;
    
    @WTF(minutes=10,reason="",type=WTFType.OVERCOMPLICATED_ALGORITHM)
    Object field6;
    
    @WTF(minutes=10,reason="",type=WTFType.PRIMITIVES_OBSESSION)
    Object field7;
    
    @WTF(minutes=10,reason="",type=WTFType.REFUSED_BEQUEST)
    Object field8;
    
    @WTF(minutes=10,reason="",type=WTFType.SOLUTION_SPRAWL)
    Object field9;
    
    @WTF(minutes=10,reason="",type=WTFType.SPECULATIVE_GENERALITY)
    Object field10;
    
    @WTF(minutes=10,reason="",type=WTFType.UNCOMMUNICATIVE_NAME)
    Object field11;
    
    @WTF(minutes=10,reason="",type=WTFType.USELESS_TEST)
    Object field12;
    
    @WTF(minutes=10,reason="",type=WTFType.WRONG_LOGIC)
    Object field13;
    
}