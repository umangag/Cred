package com.niyopay.cred;

/**
 * Created by umangagarwal on 31,July,2019
 */
public class CommonUtils {

    public static int setCardType(String cardNumber) {

        if (cardNumber.matches("^4[0-9]{6,}$")) {
            return R.drawable.ic_visa;
        }

        if (cardNumber.matches("^5[1-5][0-9]{5,}|222[1-9][0-9]{3,}|22[3-9][0-9]{4,}|2[3-6][0-9]{5,}|27[01][0-9]{4,}|2720[0-9]{3,}$")) {
            return R.drawable.ic_mastercard;
        }

        if (cardNumber.matches("^3[47][0-9]{5,}$")) {
            return R.drawable.ic_amex;
        }

        if (cardNumber.matches("^3(?:0[0-5]|[68][0-9])[0-9]{4,}$")) {
            return R.drawable.ic_diners_club;
        }

        return R.drawable.ic_credit_card;
    }

    public static boolean isCardValid(String inputText) {

        System.out.println("MainActivity.checkCardValidInvalid::  " + inputText.replaceAll("\\s", ""));

        inputText = inputText.replaceAll("\\s", "");

        String reverse = "";

        for (int i = inputText.length() - 2; i >= 0; i--) {
            reverse = reverse + inputText.charAt(i);
        }


        int sumAllNumbers = 0;

        for (int i = 0; i < reverse.length(); i++) {
            int c = Integer.valueOf(reverse.charAt(i));
            int multiply = c * 2;

            if (multiply > 9) {
                sumAllNumbers = sumAllNumbers + (multiply - 9);
            } else {
                sumAllNumbers = sumAllNumbers + (multiply);
            }

        }

        int lastDigitOfCard = Integer.parseInt(String.valueOf(inputText.charAt(inputText.length() - 1)));

        return sumAllNumbers % 10 == lastDigitOfCard;
    }
}
