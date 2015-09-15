import com.qualinsight.plugins.sonarqube.wtf.api.annotation.WTF;
import com.qualinsight.plugins.sonarqube.wtf.api.model.WTFType;

public class WTFMeasurerTest_1 {
    
    @WTF(minutes=10,reason="",type=WTFType.ABBREVIATIONS_USAGE)
    Object field1;
    
    @WTF(minutes=10,reason="",type=WTFType.ANTI_PATTERN)
    Object field2;
    
    @WTF(minutes=10,reason="",type=WTFType.BAD_DESIGN)
    Object field3;
    
    @WTF(minutes=10,reason="",type=WTFType.BAD_FRAMEWORK_USAGE)
    Object field20;
    
    @WTF(minutes=10,reason="",type=WTFType.BAD_LOGGING)
    Object field4;
    
    @WTF(minutes=10,reason="",type=WTFType.HOW_COMMENT)
    Object field5;
    
    @WTF(minutes=10,reason="",type=WTFType.INDECENT_EXPOSURE)
    Object field6;
    
    @WTF(minutes=10,reason="",type=WTFType.MEANINGLESS_COMMENT)
    Object field7;
    
    @WTF(minutes=10,reason="",type=WTFType.MIDDLE_MAN)
    Object field8;
    
    @WTF(minutes=10,reason="",type=WTFType.MISSING_IMPLEMENTATION)
    Object field9;
    
    @WTF(minutes=10,reason="",type=WTFType.MULTIPLE_RESPONSIBILITIES)
    Object field10;
    
    @WTF(minutes=10,reason="",type=WTFType.NON_EXCEPTION)
    Object field11;
    
    @WTF(minutes=10,reason="",type=WTFType.ODDBALL_SOLUTION)
    Object field12;
    
    @WTF(minutes=10,reason="",type=WTFType.OVERCOMPLICATED_ALGORITHM)
    Object field13;
    
    @WTF(minutes=10,reason="",type=WTFType.PRIMITIVES_OBSESSION)
    Object field14;
    
    @WTF(minutes=10,reason="",type=WTFType.REFUSED_BEQUEST)
    Object field15;
    
    @WTF(minutes=10,reason="",type=WTFType.REINVENTED_WHEEL)
    Object field16;
    
    @WTF(minutes=10,reason="",type=WTFType.SOLUTION_SPRAWL)
    Object field17;
    
    @WTF(minutes=10,reason="",type=WTFType.SPECULATIVE_GENERALITY)
    Object field18;
    
    @WTF(minutes=10,reason="",type=WTFType.UNCOMMUNICATIVE_NAME)
    Object field19;
    
    @WTF(minutes=10,reason="",type=WTFType.USELESS_TEST)
    Object field21;
    
    @WTF(minutes=10,reason="",type=WTFType.WRONG_LANGUAGE)
    Object field22;
    
    @WTF(minutes=10,reason="",type=WTFType.WRONG_LOGIC)
    Object field23;
    
}