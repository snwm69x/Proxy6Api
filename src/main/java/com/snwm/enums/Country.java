package com.snwm.enums;

public enum Country {

    UNITED_KINGDOM("gb"),
    RUSSIA("ru"),
    NETHERLANDS("nl"),
    GERMANY("de"),
    CANADA("ca"),
    UNITED_STATES("us"),
    AUSTRALIA("au"),
    JAPAN("jp"),
    SINGAPORE("sg"),
    FRANCE("fr"),
    SEYCHELLES("sc"),
    SAUDI_ARABIA("sa"),
    EGYPT("eg"),
    CHINA("cn"),
    UNITED_ARAB_EMIRATES("ae"),
    ISRAEL("il"),
    TURKEY("tr"),
    MEXICO("mx"),
    NIGERIA("ng"),
    MALAYSIA("my"),
    TAJIKISTAN("tj"),
    BRAZIL("br"),
    BELARUS("by"),
    HONG_KONG("hk"),
    TURKMENISTAN("tm"),
    KYRGYZSTAN("kg"),
    UZBEKISTAN("uz"),
    CHILE("cl"),
    IRELAND("ie"),
    FINLAND("fi"),
    LATVIA("lv"),
    THAILAND("th"),
    DENMARK("dk"),
    GEORGIA("ge"),
    ROMANIA("ro"),
    PORTUGAL("pt"),
    LITHUANIA("lt"),
    TAIWAN("tw"),
    BULGARIA("bg"),
    SOUTH_AFRICA("za"),
    BANGLADESH("bd"),
    VIETNAM("vn"),
    CYPRUS("cy"),
    ARMENIA("am"),
    INDIA("in"),
    UKRAINE("ua"),
    SERBIA("rs"),
    KAZAKHSTAN("kz"),
    SWEDEN("se"),
    SOUTH_KOREA("kr"),
    INDONESIA("id"),
    PHILIPPINES("ph"),
    BELGIUM("be"),
    MOLDOVA("md"),
    POLAND("pl"),
    ITALY("it"),
    SLOVENIA("si"),
    SPAIN("es"),
    GREECE("gr"),
    SWITZERLAND("ch"),
    AUSTRIA("at"),
    CZECH_REPUBLIC("cz"),
    NORWAY("no"),
    ESTONIA("ee");

    private String code;

    Country(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Country fromCode(String code) {
        for (Country country : values()) {
            if (country.getCode().equals(code)) {
                return country;
            }
        }
        throw new IllegalArgumentException("Invalid country code: " + code);
    }
}
