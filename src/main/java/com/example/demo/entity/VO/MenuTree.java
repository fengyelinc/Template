package com.example.demo.entity.VO;

import lombok.Data;

import java.util.List;

@Data
public class MenuTree {
    private String id;
    private String name;
//    private Long parentId;
    private Long level;
//    private String href;
//    private String icon;
//    private String remarks;
    private List<MenuTree> childern;
}
