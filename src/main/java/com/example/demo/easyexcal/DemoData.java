package com.example.demo.easyexcal;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.util.Date;

@Data
public class DemoData {

    @ExcelProperty(index = 0)
    private String qid;

    @ExcelProperty(index = 1)
    private String qname;

    @ExcelProperty(index = 2)
    private String qStartTime;

    @ExcelProperty(index = 3)
    private String qEndTime;

    @ExcelProperty(index = 4)
    private String qdesc;

    @ExcelProperty(index = 5)
    private String cname ;

    @ExcelProperty(index = 6)
    private String cphone;

    @ExcelProperty(index = 7)
    private String serialNumber;

    @ExcelProperty(index = 8)
    private String  subjectName;

    @ExcelProperty(index = 9)
    private String  subjectType;

    @ExcelProperty(index = 10)
    private String optionA;

    @ExcelProperty(index = 11)
    private String optionB;

    @ExcelProperty(index = 12)
    private String optionC;

    @ExcelProperty(index = 13)
    private String optionD;

    @ExcelProperty(index = 14)
    private String userName;

    @ExcelProperty(index = 15)
    private String userPhone;

    @DateTimeFormat("yyyy-MM-dd HH:mm")
    @ExcelProperty(index = 16)
    private String  fillInTime;

    @ExcelProperty(index = 17)
    private String Satisfaction;

}
