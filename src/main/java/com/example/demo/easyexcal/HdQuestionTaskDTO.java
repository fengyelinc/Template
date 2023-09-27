package com.example.demo.easyexcal;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import com.alibaba.excel.enums.poi.FillPatternTypeEnum;
import lombok.Data;

import java.util.Date;

@Data
@HeadStyle(fillPatternType = FillPatternTypeEnum.SOLID_FOREGROUND, fillForegroundColor = 17)
public class HdQuestionTaskDTO {


    @ExcelProperty({"问卷调查机构列表", "机构名称"})
    private String companyName;

    @ExcelProperty({"问卷调查机构列表", "调查人数"})
    private String total;

    @ExcelProperty({"问卷调查机构列表", "状态"})
    private String status;

    @ExcelProperty({"问卷调查机构列表", "评分"})
    private Double score;

    @ExcelProperty({"问卷调查机构列表", "提交时间"})
    private Date commitTime;

    @ExcelProperty({"机构调查明细", "手机号"})
    private String phone;

    @ExcelProperty({"机构调查明细", "姓名"})
    private String name;

    @ExcelProperty({"机构调查明细", "资金账号"})
    private String fundAccount;

    @ExcelProperty({"机构调查明细", "股东账号"})
    private String gdAccount;

    @ExcelProperty({"机构调查明细", "调查时间"})
    private Date investigateTime;
}
