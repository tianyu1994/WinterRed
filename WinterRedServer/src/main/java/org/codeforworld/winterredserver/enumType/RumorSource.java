package org.codeforworld.winterredserver.enumType;

public enum RumorSource {
    NETWORK("网络"),USER_ASK("用户提问"),BLOCK_CHAIN("区块链");
    RumorSource(String value) {
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
