package org.codeforworld.winterredserver.request;

import lombok.Data;

import java.util.List;

@Data
public class SubscribeRequest {
    private String email;
    private String identifyingCode;
    private List<Integer> professionalFieldIdList;
}
