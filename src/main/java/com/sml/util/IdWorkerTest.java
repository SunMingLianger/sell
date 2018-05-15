package com.sml.util;

import com.baomidou.mybatisplus.toolkit.IdWorker;

/**
 * Created by 神迷的亮
 * 2018-05-15 16:39
 */
public class IdWorkerTest
{
    public static void main(String[] args)
    {
        System.out.println(IdWorker.getId());

        System.out.println(IdWorker.get32UUID());

        System.out.println(IdWorker.getIdStr());
    }
}
