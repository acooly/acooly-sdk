/**
 * acooly-components-feature
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-09-17 11:05
 */
package cn.acooly.sdk.message;

import com.github.liaochong.myexcel.core.SaxExcelReader;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.List;

/**
 * @author zhangpu
 * @date 2021-09-17 11:05
 */
@Slf4j
public class FileParses {


    /**
     * 一次性加载Excel文件
     *
     * @param path
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> List<T> loadExcel(String path, Integer sheetIndex, Class<T> tClass) {
        if (sheetIndex == null) {
            sheetIndex = 0;
        }
        List<T> result = SaxExcelReader.of(tClass)
                .sheet(sheetIndex).read(new File(path));

        return result;
    }

    public static <T> List<T> loadExcel(String path, Class<T> tClass) {
        return loadExcel(path, 0, tClass);
    }

}
