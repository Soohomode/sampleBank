package com.dayone.model;

import lombok.Builder;
import lombok.Data;

@Data // getter, setter, toString 등 포함된 어노테이션
@Builder // 디자인 패턴중 빌더패턴이 있는데, 해당 클래스에서 사용할수 있게 하는 어노테이션
public class Company {

    private String ticker;
    private String name;

}
