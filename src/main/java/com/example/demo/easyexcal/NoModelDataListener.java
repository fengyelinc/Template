package com.example.demo.easyexcal;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.ListUtils;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class NoModelDataListener extends AnalysisEventListener<Map<Integer, String>> {
    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    private List<Map<Integer, String>> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    private List<String> title = new ArrayList<>();

    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext context) {
        Set<Integer> keySets = data.keySet();
        if (data.get(14).equals("填写时间")) {
            for (int i = 15; i < keySets.size(); i++) {
//                System.out.println(data.get(i));
                title.add(data.get(i));

            }
            title.forEach(System.out::println);
        } else {
            for (int i = 0; i < keySets.size(); i++) {
//                System.out.println(data.get(i));
            }
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        title = new ArrayList<>();
        log.info("所有数据解析完成！");

    }


}
