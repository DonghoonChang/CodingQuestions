package LeetCode.Medium;

/*
    Time taken: 0:28
    Runtime: 8 ms, faster than 23.55% of Java online submissions for Validate IP Address.
    Memory Usage: 42.4 MB, less than 42.34% of Java online submissions for Validate IP Address.
 */
public class ValidateIpAddress {
    public String validIPAddress(String queryIP) {
        boolean hasDots = queryIP.length() - queryIP.replace(".", "").length() == 3;
        boolean hasColons = queryIP.length() - queryIP.replace(":", "").length() == 7;

        if(!(hasDots ^ hasColons)){
            return "Neither";
        }

        if(hasDots){
            return validateIpV4(queryIP) ? "IPv4" : "Neither";
        }

        if(hasColons){
            return validateIpV6(queryIP) ? "IPv6" : "Neither";
        }

        return "Neither";
    }

    private boolean validateIpV4(String s){
        String[] segments = s.split("\\.");

        if(segments.length != 4){
            return false;
        }

        for(String segment: segments){
            if(!isValidIPv4Segment(segment)){
                return false;
            }
        }

        return true;
    }

    private boolean validateIpV6(String s){
        String[] segments = s.split(":");

        if(segments.length != 8){
            return false;
        }

        for(String segment: segments){
            if(!isValidIPv6Segment(segment)){
                return false;
            }
        }

        return true;
    }


    private static boolean isValidIPv4Segment(String segment) {
        if (segment.length() > 3) {
            return false;
        }

        if (segment.length() == 0) {
            return false;
        }

        if (!segment.matches("\\d+")) {
            return false;
        }

        if (segment.startsWith("0") && segment.length() > 1) {
            return false;
        }

        int val = Integer.parseInt(segment);
        return val <= 255 && val >= 0;
    }

    private static boolean isValidIPv6Segment(String segment) {
        if (segment.length() > 4) {
            return false;
        }

        if (segment.length() == 0) {
            return false;
        }

        return segment.matches("[\\da-fA-F]*");
    }

    public static void main(String[] args) {
//        String queryIp = "192.168.1.0";
        String queryIp = "2001:0db8:85a3:0:0:8A2E:0370:7334";
        String result = (new ValidateIpAddress()).validIPAddress(queryIp);

        System.out.println(result);
    }
}
