package com.example.demo;

import com.alibaba.excel.EasyExcel;
import com.example.demo.easyexcal.DemoData;
import com.example.demo.easyexcal.DemoDataListener;
import com.example.demo.easyexcal.NoModelDataListener;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EasyExcalTest {

    private String fileName ="src/main/resources/测试.xlsx";

    @Test
    public void simpleRead(){
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
//        EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().headRowNumber(2).doRead();

        EasyExcel.read(fileName, new NoModelDataListener()).sheet().headRowNumber(2).doRead();

    }



}
