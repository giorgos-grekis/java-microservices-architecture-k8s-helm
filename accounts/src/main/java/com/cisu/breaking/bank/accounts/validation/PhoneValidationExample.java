package com.cisu.breaking.bank.accounts.validation;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public class PhoneValidationExample {

    public static void main(String[] args) {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

        String numberStr = "+306912345678";

        try {
            PhoneNumber phoneNumber = phoneUtil.parse(numberStr, "GR");

            boolean isValid = phoneUtil.isValidNumber(phoneNumber);

            PhoneNumberUtil.PhoneNumberType type = phoneUtil.getNumberType(phoneNumber);
            boolean isMobile = (type == PhoneNumberUtil.PhoneNumberType.MOBILE);

            if (isValid && isMobile) {
                System.out.println("Είναι ένα 100% έγκυρο κινητό τηλέφωνο!");
            } else {
                System.out.println("Το τηλέφωνο δεν είναι έγκυρο κινητό.");
            }

        } catch (NumberParseException e) {
            System.err.println("Η μορφή του τηλεφώνου δεν μπορούσε καν να διαβαστεί: " + e.toString());
        }
    }
}