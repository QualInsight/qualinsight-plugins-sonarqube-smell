import com.qualinsight.plugins.sonarqube.Smell.api.annotation.Smell;
import com.qualinsight.plugins.sonarqube.Smell.api.model.SmellType;

@Smell(minutes=10,reason="",type=SmellType.ANTI_PATTERN)
public class SmellMeasurerTest_3 {
    
    @Smell(minutes=10,reason="",type=SmellType.ANTI_PATTERN)
    Object field1;
    
    @Smell(minutes=10,reason="",type=SmellType.ANTI_PATTERN)
    Object field2;
   
}