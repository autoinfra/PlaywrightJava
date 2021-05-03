package listeners.Elastic;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Elastic_Json_TestStatus {

    private String testClass;
    private String description;
    private String status;
    private String executionTime;


}