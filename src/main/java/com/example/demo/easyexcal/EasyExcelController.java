package com.example.demo.easyexcal;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RestController
public class EasyExcelController {

    @GetMapping("/export")
    public void export(String taskId, HttpServletResponse response,
                       HttpServletRequest request) throws IOException {
        List<HdQuestionTaskDTO> result = new ArrayList<>();

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("问卷调查数据统计", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), HdQuestionTaskDTO.class)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).sheet("作品").doWrite(result);
    }
}
