import com.qualinsight.plugins.sonarqube.Smell.api.annotation.Smell;
import com.qualinsight.plugins.sonarqube.Smell.api.model.SmellType;

public class SmellMeasurerTest_1 {
    
    @Smell(minutes=10,
           type=SmellType.ABBREVIATIONS_USAGE,
           reason="")
    Object field1;
    
    @Smell(type=SmellType.ANTI_PATTERN,
           minutes=10,
           reason="")
    Object field2;
    
    @Smell(minutes=10,
           reason="",
           type=SmellType.BAD_DESIGN)
    Object field3;
    
    @Smell(minutes=10,
           reason="",
           type=SmellType.BAD_FRAMEWORK_USAGE)
    Object field20;
    
    @Smell(minutes=10,
           reason="",
           type=SmellType.BAD_LOGGING)
    Object field4;
    
    @Smell(minutes=10,
           reason="",
           type=SmellType.HOW_COMMENT)
    Object field5;
    
    @Smell(minutes=10,
           reason="",
           type=SmellType.INDECENT_EXPOSURE)
    Object field6;
    
    @Smell(minutes=10,
           reason="",
           type=SmellType.MEANINGLESS_COMMENT)
    Object field7;
    
    @Smell(minutes=10,
           reason="",
           type=SmellType.MIDDLE_MAN)
    Object field8;
    
    @Smell(minutes=10,
           reason="",
           type=SmellType.MISSING_IMPLEMENTATION)
    Object field9;
    
    @Smell(minutes=10,
           reason="",
           type=SmellType.MULTIPLE_RESPONSIBILITIES)
    Object field10;
    
    @Smell(minutes=10,
           reason="",
           type=SmellType.NON_EXCEPTION)
    Object field11;
    
    @Smell(minutes=10,
           reason="",
           type=SmellType.ODDBALL_SOLUTION)
    Object field12;
    
    @Smell(minutes=10,
           reason="",
           type=SmellType.OVERCOMPLICATED_ALGORITHM)
    Object field13;
    
    @Smell(minutes=10,
           reason="",
           type=SmellType.PRIMITIVES_OBSESSION)
    Object field14;
    
    @Smell(minutes=10,
           reason="",
           type=SmellType.REFUSED_BEQUEST)
    Object field15;
    
    @Smell(minutes=10,
           reason="",
           type=SmellType.REINVENTED_WHEEL)
    Object field16;
    
    @Smell(minutes=10,
           reason="",
           type=SmellType.SOLUTION_SPRAWL)
    Object field17;
    
    @Smell(minutes=10,
           reason="",
           type=SmellType.SPECULATIVE_GENERALITY)
    Object field18;
    
    @Smell(minutes=10,
           reason="",
           type=SmellType.UNCOMMUNICATIVE_NAME)
    Object field19;
    
    @Smell(minutes=10,
           reason="",
           type=SmellType.USELESS_TEST)
    Object field21;
    
    @Smell(minutes=10,
           reason="",
           type=SmellType.WRONG_LANGUAGE)
    Object field22;
    
    @Smell(minutes=10,
        reason="",
        type=SmellType.WRONG_LOGIC)
    Object field23;
 
    @Smell(minutes=10,
        reason="",
        type=SmellType.MISSING_DOCUMENTATION)
    Object field24;
 
    @Smell(minutes=10,
        reason="",
        type=SmellType.MISSING_TEST)
    Object field25;
 
    @Smell(minutes=10,
        reason="",
        type=SmellType.OTHER)
    Object field26;
 
    @Smell(minutes=10,
        reason="",
        type=SmellType.NON_COMPLIANCE_WITH_STANDARDS)
    Object field27;
 
}