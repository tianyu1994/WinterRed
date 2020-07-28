package org.codeforworld.winterredserver.enumType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static List<String> getAllNames(){
        List<String> nameList = new ArrayList<>();
        List<CheckStatus> valueList = Arrays.asList(CheckStatus.values());
        for (CheckStatus checkStatus : valueList) {
            nameList.add(checkStatus.getName());
        }
        return nameList;
    }
}
