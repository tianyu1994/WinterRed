package org.codeforworld.winterredserver.enumType;

public enum  CheckStatus {
    WAIT_CHECK("待核查"),CHECK_PASS("真"),CHECK_NO_PASS("假"),UN_KNOWN("存疑");
    CheckStatus(String value) {
        this.value = value;
    }

    private String value;

    public String getName() {
        return value;
    }

    public void setName(String value) {
        this.value = value;
    }

}
