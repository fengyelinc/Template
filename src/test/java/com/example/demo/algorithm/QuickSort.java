package com.example.demo.algorithm;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * 排序
 */
@SpringBootTest
public class QuickSort {

    /**
     * 快速排序，双指针递归
     */
    @Test
    public void test1() {
        int[] array = {7, 1, 3, 5, 13, 9, 3, 6, 11};
        int len;
        if (array == null || (len = array.length) == 0 || len == 1) {
            return;
        }
        sort2(array, 0, len - 1);
        Arrays.stream(array).forEach(System.out::println);
    }

    //从大到小
    public void sort(int[] array, int left, int right) {
        if (left > right) {
            return;
        }
        //存放基准数
        int base = array[left];
        int i = left, j = right;
        while (i != j) {
            //先从右边往左找，直到找到比base小的值
            while (array[j] <= base && i < j) {
                j--;
                System.out.println("j=" + j + "------array[j]------->" + array[j]);
            }
            //再从左往右边找，直到找到比base值大的数
            while (array[i] >= base && i < j) {
                i++;
                System.out.println("i=" + i + "" + "------array[i]------->" + array[i]);
            }
            // 上面的循环结束表示找到了位置或者(i>=j)了，交换两个数在数组中的位置
            if (i < j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }

        }
        // 将基准数放到中间的位置（基准数归位）
        array[left] = array[i];
        array[i] = base;

        // 递归，继续向基准的左右两边执行和上面同样的操作
        // i的索引处为上面已确定好的基准值的位置，无需再处理
        sort(array, left, i - 1);
        sort(array, i + 1, right);
    }

    //从小到大
    public void sort2(int[] array, int left, int right) {
        //跳出递归
        if (left >= right) {
            return;
        }
        int base = array[left];
        int i = left, j = right;
        while (i != j) {
            //从右开始找比base大的数
            while (array[j] >= base && i < j) {
                j--;
            }
            //从左开始找比base小的数
            while (array[i] <= base && i < j) {
                i++;
            }
            if (i < j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        //base右边已完成大小的交换，然后把base放到中间的位置上
        array[left] = array[i];
        array[i] = base;

        //base左右进行递归
        sort2(array, left, i - 1);
        sort2(array, i + 1, right);

    }

    /**
     * 冒泡排序
     */
    @Test
    public void test2() {
        int[] array = {7, 1, 3, 5, 13, 9, 3, 6, 11};
        bubbleSort(array);
        Arrays.stream(array).forEach(System.out::println);
    }

    public void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }


    public int getNext(int numb, int factor) {
        int a[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int index = numb - 1;
        int next;
        if (numb + factor < 12) {
            next = index + factor;
        } else {
            next = numb + factor - 12 - 1;
        }
        return next;
    }

    public List<Integer> getMounths(int numb, int factor) {
        List<Integer> mounths = new ArrayList<>();
        int cycles = 12 / factor;
        while (cycles > 0) {
            int next = getNext(numb, factor);
            numb = next + 1;
            mounths.add(next + 1);
            cycles--;
        }
        return mounths;
    }

    @Test
    public void test3() {
        List<Integer> mounths = getMounths(7, 3);
        mounths.stream().forEach(System.out::println);
    }


    @Test
    public void test4() {
        String[] h1 = {"姓名", "性别", "年龄", "联系电话", "部门/学院班级", "辅导员姓名", "辅导员电话", "宿舍", "家庭住址", "既往史", "诊断", "确诊时间", "确诊医院", "发病经过", "去过地方", "在哪治疗"};
        String[] h2 = {"接触者姓名", "手机号码", "是否得过水痘", "家庭住址", "未得水痘同学隔离地点"};
        int max = 9;
        String[] target = new String[h1.length + 9 * h2.length];
        System.arraycopy(h1, 0, target, 0, h1.length);
        int index = 0;
        for (int i = 0; i < max; i++) {
            if (i == 0) {
                index = h1.length;
            } else {
                index += h2.length;
            }
            System.arraycopy(h2, 0, target, index, h2.length);
        }

        Arrays.stream(target).forEach(System.out::println);
    }

    @Test
    public void test5() {
        String[] h1 = {"姓名", "性别", "年龄", "联系电话", "部门/学院班级", "辅导员姓名", "辅导员电话", "宿舍", "家庭住址", "既往史", "诊断", "确诊时间", "确诊医院", "发病经过", "去过地方", "在哪治疗"};
        String[] h2 = {"接触者姓名", "手机号码", "是否得过水痘", "家庭住址", "未得水痘同学隔离地点"};
        List<Object> target = new ArrayList<>();
        List<String> list1 = Arrays.asList(h1);
        target.addAll(list1);
        List<String> list2 = Arrays.asList(h2);
        target.addAll(list2);
        String[] temp = new String[16];
        String[] objects = (String[])target.toArray(temp);
        Arrays.stream(objects).forEach(System.out::println);

    }
}
