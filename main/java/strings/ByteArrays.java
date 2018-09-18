package strings;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

public class ByteArrays {
    private static final SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args){
	String myString = "An Incident is created for <CRITICAL> \nThe metric redis write with thresholds delete_time.delete_time crossed the critical threshold 0.001. Current utilization is 0.00909\n\nAdditional Details:\n orgId:b1ecc696-e9ed-4d1f-98d2-9c7080b5dac6:FIXSTREAM\n siteId:9aa19b9e-e7f8-4904-934b-eaf47f02e06b:US\n orgId:172.16.5.201\n severity: 7\n eventType:13\n orgId:e04e4e16-687a-483b-a775-fe1a5ec0b36d\n count:1\n eventCategory: 6\n parentId: b1ecc696-e9ed-4d1f-98d2-9c7080b5dac6:9aa19b9e-e7f8-4904-934b-eaf47f02e06b:e04e4e16-687a-483b-a775-fe1a5ec0b36d\n devType:Compute\n devSubType:VM\n hostName: qa-cluster-nce1\n faultName:redis write with thresholds delete_time\n service:redis\n isAlertable:true\n autoCleared: true\n affectedObject:delete_time\n affectedObjectOID:nagios\n sourceType: 1\n sourceName:APIConnector\n firstOccurred:1520249354000\n lastOccurred: 1520249354000\n probableCause:Threshold is crossed for the metric redis write with thresholds delete_time.delete_time.";
	//String[] lines = myString.split(System.getProperty("line.separator"));
	String[] lines = myString.split("\n");
	
	for(String line : lines){
	    if (line.contains("firstOccurred") || line.contains("lastOccurred")){
		String[] parts = line.split(":");
		String newTime = covertEpocToReadable(parts[1].trim());
		String newTime2 = covertEpocToReadable2(parts[1].trim(),simpleFormat,"America/Los_Angeles" );
		
		System.out.println(newTime+", "+newTime2);	
	    }
	}
	  
	
	
//	
//	StringBuilder sb = new StringBuilder();
//	for(String line : lines)
//	    sb.append(line).append("\n");
//	System.out.println(sb.toString());
	
    }
    
    public static String covertEpocToReadable(String timeString) {

        try {
            long epocTime = Long.valueOf(timeString);
            int length = timeString.length();
            if (length == 10)
                epocTime *= 1000L;
            Date date = new Date(epocTime);
            DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            format.setTimeZone(TimeZone.getTimeZone("UTC"));
            String result = format.format(date);
            return result;
        } catch (Exception e) {
            System.out.println("convert time string exception");
        }

        return null;

    }
    
    public static String covertEpocToReadable2(String timeString, DateFormat format, String zone) {

        try {
            long epocTime = Long.valueOf(timeString);
            int length = timeString.length();
            if (length == 10)
                epocTime *= 1000L;
            Date date = new Date(epocTime);
            //DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            format.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
            String result = format.format(date);
            return result;
        } catch (Exception e) {
            System.out.println("convert time string exception");
        }

        return null;

    }

}
