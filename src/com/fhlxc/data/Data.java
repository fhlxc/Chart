package com.fhlxc.data;

import java.awt.Point;

import com.fhlxc.gui.ChartJPanel;

/**
* @author Xingchao Long
* @date 2019/46/08 18:46:32
* @ClassName Data
* @Description 存放程序整个运行过程中的数据
*/

public class Data {
    //放大或缩小的倍数
    public static float multiple;
    //当前选中的通道
    public static ChartJPanel currChannel;
    //原始的点的信息
    public static Point[] points;
    //经压缩、微分处理后的点的信息
    public static Point[] processedPoints;
}
