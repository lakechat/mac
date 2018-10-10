package strings;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class VROPSKeyObject {

    private static final Logger logger = LogManager.getLogger(VROPSKeyObject.class);
    private String key;

    @JsonIgnore
    private String[] values;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getServiceName() {
        if (this.values == null || values.length == 0)
            getValues();
        return values[0];
    }

    public String getGroupName() {
        if (this.values == null || values.length == 0)
            getValues();
        return values[1];
    }

    public String getMetricName() {
        if (this.values == null || values.length == 0)
            getValues();
        return values[2];
    }

    private void getValues() {
        try {
            values = new String[3];
            if (this.key != null && this.key.length() > 0) {
                String[] tmpStrings_1 = this.key.split("\\|");
                if (tmpStrings_1 != null && tmpStrings_1.length == 2) {
                    // fill metric name first
                    this.values[2] = tmpStrings_1[1];
                    // now try group and isntance
                    String[] tmpStrings_2 = tmpStrings_1[0].split("\\:");
                    if (tmpStrings_2[0].length() > 0)
                        this.values[0] = tmpStrings_2[0];
                    else{
                        this.values[0] = "default";
                        this.values[1] = "default";
                    }
                    if (tmpStrings_2.length > 1)
                        this.values[1] = tmpStrings_2[1];
                    else
                        this.values[1] = "default";
                }
                else if(tmpStrings_1.length==1){
                    this.values[2] = tmpStrings_1[0];
                    this.values[0] = "default";
                    this.values[1] = "default";
                }
            }
        } catch (Exception e) {
            logger.error(" parsing vrops key object exception: " + this.key);
        }
    }

}

