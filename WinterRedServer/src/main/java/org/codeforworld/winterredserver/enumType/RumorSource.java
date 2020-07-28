package org.codeforworld.winterredserver.enumType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static List<String> getAllNames(){
        List<String> nameList = new ArrayList<>();
        List<RumorSource> valueList = Arrays.asList(RumorSource.values());
        for (RumorSource rumorSource : valueList) {
            nameList.add(rumorSource.getName());
        }
        return nameList;
    }
}
