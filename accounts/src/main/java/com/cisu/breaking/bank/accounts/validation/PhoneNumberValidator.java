package com.cisu.breaking.bank.accounts.validation;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
    private final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) return true;
        try {
            // "ZZ" σημαίνει: "Βασίσου στο '+' για να βρεις τη χώρα".
            var number = phoneUtil.parse(value, "ZZ");
            return phoneUtil.isValidNumber(number);
        } catch (NumberParseException e) {
            return false;
        }
    }
//    @Override
//    public boolean isValid(String value, ConstraintValidatorContext context) {
//        if (value == null || value.isBlank()) return true;
//        String cleanedValue = value.trim();
//        if (cleanedValue.startsWith(" ")) {
//            cleanedValue = cleanedValue.replaceFirst(" ", "+");
//        }
//
//        try {
//            var number = phoneUtil.parse(cleanedValue, "ZZ");
//            return phoneUtil.isValidNumber(number);
//        } catch (NumberParseException e) {
//            return false;
//        }
//    }
}