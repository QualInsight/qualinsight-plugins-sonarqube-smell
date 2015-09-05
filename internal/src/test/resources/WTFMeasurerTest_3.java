import com.qualinsight.plugins.sonarqube.wtf.api.annotation.WTF;
import com.qualinsight.plugins.sonarqube.wtf.api.model.WTFType;

@WTF(minutes=10,reason="",type=WTFType.ANTI_PATTERN)
public class WTFMeasurerTest_3 {
    
    @WTF(minutes=10,reason="",type=WTFType.ANTI_PATTERN)
    Object field1;
    
    @WTF(minutes=10,reason="",type=WTFType.ANTI_PATTERN)
    Object field2;
   
}