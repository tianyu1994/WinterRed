package org.codeforworld.winterredserver.enumType;

public enum  CheckStatus {
    WAIT_CHECK("待核查"),CHECK_PASS("核查通过"),CHECK_NO_PASS("核查不通过");

    private CheckStatus(String value) {
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
