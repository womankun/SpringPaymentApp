package com.example.payment.model;

public enum CardBrand {
    VISA("VISA", new String[]{"4"}),
    MASTER("MASTER", new String[]{"51", "52", "53", "54", "55"}),
    JCB("JCB", new String[]{"3528", "3589"}), // 実際は範囲で扱う方が正確
    AMEX("AMEX", new String[]{"34", "37"}),
    DINERS("DINERS", new String[]{"30", "36", "38"});

    private final String name;
    private final String[] prefixes;

    CardBrand(String name, String[] prefixes) {
        this.name = name;
        this.prefixes = prefixes;
    }

    public String getName() {
        return name;
    }

    public static CardBrand detect(String cardNumber) {
        if (cardNumber == null) return null;

        for (CardBrand brand : CardBrand.values()) {
            for (String prefix : brand.prefixes) {
                if (cardNumber.startsWith(prefix)) {
                    return brand;
                }
            }
        }
        return null;
    }
}