package com.eazybytes.jobportal.constant;

public class ApplicationConstants {  

    private ApplicationConstants() {
       throw new AssertionError("Utility class should not be instantiated");
    }

    public static final String JWT_SECRET_KEY= "JWT_SECRET";
    public static final String JWT_SECRET_DEFAULT_VALUE ="jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";

    public static final String JWT_HEADER="Authorization";
    public static final String ROLE_JOB_SEEKER = "ROLE_JOB_SEEKER";
    public static final String JOB_STATUS_ACTIVE = "ACTIVE";

    public static final String CONTACT_STATUS_NEW = "NEW";
    public static String CONTACT_STATUS_RESOLVED = "CLOSED";

    public static String SYSTEM = "SYSTEM";
    
}
