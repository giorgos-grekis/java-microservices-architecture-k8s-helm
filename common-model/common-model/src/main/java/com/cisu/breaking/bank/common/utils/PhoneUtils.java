package main.java.com.cisu.breaking.bank.common.utils;

public class PhoneUtils {
    public static String format(String mobileNumber) {
        if (mobileNumber != null && mobileNumber.startsWith(" ")) {
            mobileNumber = mobileNumber.replaceFirst(" ", "+");
        }
        return mobileNumber != null ? mobileNumber.trim() : null;
    }
}