package com.yixihan.day1229;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * description
 *
 * @author yixihan
 * @date 2022/12/29 23:06
 */
public class EasyReferences {
    
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream ("D:\\GraduationProjects\\提交文档\\曾思彤\\开题答辩\\references.txt");
        byte[] buffer = new byte[1024];
        StringBuilder sb = new StringBuilder ();
        while ((fis.read (buffer) != -1)) {
            sb.append (new String (buffer));
            Arrays.fill (buffer, (byte) 0);
        }
        sb.append (new String (buffer));
        int count = 1;
        sb.insert (0, "[" + count++ + "]");
        for (int i = 0; i < sb.length () - 3; i++) {
            if (sb.charAt (i) == '\n' && sb.charAt (i + 2) == '\n') {
                sb.replace (i + 1, i + 3, "[" + count++ + "]");
            }
        }
        System.out.println (sb);
    }
}
