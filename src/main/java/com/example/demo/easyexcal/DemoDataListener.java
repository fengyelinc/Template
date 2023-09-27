//package com.example.demo.easyexcal;
//
//import com.alibaba.excel.context.AnalysisContext;
//import com.alibaba.excel.read.listener.ReadListener;
//import com.alibaba.excel.util.ListUtils;
//import com.alibaba.fastjson.JSON;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//// 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
//public class DemoDataListener implements ReadListener<DemoData> {
//
//    /**
//     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
//     */
//    private static final int BATCH_COUNT = 100;
//    /**
//     * 缓存的数据
//     */
//    private List<DemoData> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
//
//
//    /**
//     * 这个每一条数据解析都会来调用
//     *
//     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
//     * @param context
//     */
//    @Override
//    public void invoke(DemoData data, AnalysisContext context) {
////        System.out.println("解析到一条数据:"+ JSON.toJSONString(data));
////        data.getFillInTime();
////        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
////        String dateStr = sdf.format(date);
//
//        cachedDataList.add(data);
//        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
//        if (cachedDataList.size() >= BATCH_COUNT) {
//            saveData();
//            // 存储完成清理 list
//            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
//        }
//        System.out.println("解析到一条数据:"+ JSON.toJSONString(data));
//
//
//    }
//
//    /**
//     * 所有数据解析完成了 都会来调用
//     *
//     * @param context
//     */
//    @Override
//    public void doAfterAllAnalysed(AnalysisContext context) {
//        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
//        saveData();
//        System.out.println("所有数据解析完成！");
//
//    }
//
//    /**
//     * 加上存储数据库
//     */
//    private void saveData() {
//
//    }
//}