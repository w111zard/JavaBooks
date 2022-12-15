package com.example.javabooks.libs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
    Класс предоставляет методы для валидации вводимых данных
*/

public class Validator {
    private static final int MIN_TEXT_LENGTH = 4; // минимальная длина текста
    private static final int MAX_TEXT_LENGTH = 64; // максимальная длина текста

    // регулярное выражение для проверки email
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static String checkText(String text) {
        if (text.isBlank()) {
            return "Не может быть пустым";
        }
        if (text.length() < MIN_TEXT_LENGTH) {
            return "Длина должна быть не меньше " + MIN_TEXT_LENGTH + " символов";
        }
        if (text.length() > MAX_TEXT_LENGTH) {
            return "Длина должна быть не больше " + MAX_TEXT_LENGTH + " символов";
        }
        return null;
    }

    public static String checkEmail(String email) {
        String error = Validator.checkText(email);
        if (error != null) {
            return error;
        }
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);

        if (! matcher.find()) {
            return "Некорректный e-mail";
        }

        return null;
    }

    public static String checkNumber(String number) {
        try {
            int numberInt = Integer.parseInt(number);
            return null;
        } catch (NumberFormatException exception) {
            return "Неверный формат числа";
        }
    }
}
