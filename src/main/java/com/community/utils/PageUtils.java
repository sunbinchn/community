package com.community.utils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

public class PageUtils {
    private static final Integer PAGE_SIZE = 10;
    private static final Integer NAVIGATE_PAGES = 10;

    public static <T> PageInfo<T> getPageList(Integer pageNum, List<T> list) {
        PageHelper.startPage(pageNum, PAGE_SIZE);
        return new PageInfo<T>(list, NAVIGATE_PAGES);
    }
}