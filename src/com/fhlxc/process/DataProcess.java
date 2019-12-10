package com.fhlxc.process;

import java.awt.Point;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.BitSet;

import com.fhlxc.data.Data;
import com.fhlxc.gui.MainWindow;

/**
* @author Xingchao Long
* @date 2019/47/08 18:47:29
* @ClassName DataProcess
* @Description 读取并处理数据
*/

public class DataProcess {
    //一个16位的二进制数据
    private static BitSet bitSet = new BitSet(16);
    
    //将byte的内容拼接起来
    private static void setByte(byte[] bytes) {
        int i = 0;
        bitSet.clear();
        for (byte b: bytes) {
            if ((b & 0x01) == 0x01) {
                bitSet.set(i);
            }
            i++;
            
            if ((b & 0x02) == 0x02) {
                bitSet.set(i);
            }
            i++;
            
            if ((b & 0x04) == 0x04) {
                bitSet.set(i);
            }
            i++;
            
            if ((b & 0x08) == 0x08) {
                bitSet.set(i);
            }
            i++;
            
            if ((b & 0x10) == 0x10) {
                bitSet.set(i);
            }
            i++;
            
            if ((b & 0x20) == 0x20) {
                bitSet.set(i);
            }
            i++;
            
            if ((b & 0x40) == 0x40) {
                bitSet.set(i);
            }
            i++;
            
            if ((b & 0x80) == 0x80) {
                bitSet.set(i);
            }
            i++;
        }
    }
    
    //计算2的n次方
    private static int twoN(int n) {
        if (n == 0) {
            return 1;
        }
        
        if (n == 1) {
            return 2;
        }
        
        return 2 * twoN(n - 1);
    }
    
    //将16位的二进制数转为整数
    private static int bytesToInt() {
        int number = 0;
        for (int i = 0; i < 15; i++) {
            if (bitSet.get(i) == true) {
                number = number + twoN(i);
            }
        }
        
        if (bitSet.get(15) == true) {
            number = -number;
        }
        
        return number;
    }
    
    //检查文件是否超过限制的大小
    public static boolean checkFile(String path) {
        File file = new File(path);
        if (file.length() / 1024 / 1024> 1024) {
            MainWindow.dialog.setDialog("文件过大", "image/error.png");
            MainWindow.dialog.setVisible(true);
            return false;
        }
        return true;
    }
    
    //初始化点，依据文件的长度，初始points
    public static void initialData(String path) {
        File file = new File(path);
        
        int length = (int) (file.length() / 2);
        if (file.length() % 2 != 0) {
            length = length + 1;
        }
        Point[] points = new Point[length];
        
        for (int i = 0; i < length; i++) {
            points[i] = new Point(0, 0);
        }
        
        Data.points = points;
    }
    
    //加载点，从文件中读取信息到points
    public static void loadData(String path) {
        File file = new File(path);
        
        try {
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            int i = 0;
            while (true) {
                byte[] bytes = new byte[2];
                int b = inputStream.read(bytes);
                if (b == -1) {
                    break;
                }
                setByte(bytes);
                Data.points[i].y = bytesToInt();
                Data.points[i].x = i;
                i++;
                //System.out.println(i);
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            MainWindow.dialog.setDialog("文件找不到", "image/error.png");
            MainWindow.dialog.setVisible(true);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            MainWindow.dialog.setDialog("文件读写操作错误", "image/error.png");
            MainWindow.dialog.setVisible(true);
        }
    }
    
    //以一定得倍数重新给点赋值
    public static Point[] multipleData(Point[] p) {
        int length = p.length;
        
        Point[] points = new Point[length];
        
        for (int i = 0; i < length; i++) {
            points[i] = new Point(0, 0);
        }
        
        int i = 0;
        for (Point point: points) {
            point.x = (int) (p[i].x * 1000 * Data.multiple);
            point.y = (int) (p[i].y * Data.multiple);
            i++;
        }
        return points;
    }
    
    //波的横向压缩，即缩小x
    public static void compressData() {
        int length = Data.points.length;
        
        Point[] points = new Point[length];
        
        for (int i = 0; i < length; i++) {
            points[i] = new Point(0, 0);
        }
        
        int i = 0;
        for (Point point: points) {
            point.y = Data.points[i].y;
            point.x = Data.points[i].x - 100;
            i++;
        }
        Data.processedPoints = points;
    }
    
    //做微分变化，公式为f((x1+x2) / 2) = (f(x1) - f(x2)) / (x1 - x2)
    public static void differentialData() {
        int length = Data.points.length;
        
        Point[] points = new Point[length - 1];
        
        for (int i = 0; i < length - 1; i++) {
            points[i] = new Point(0, 0);
        }
        
        for (int i = 0; i < points.length - 1; i++) {
            points[i].x = (Data.points[i].x + Data.points[i + 1].x) / 2;
            points[i].y = (Data.points[i].y - Data.points[i + 1].y) / (Data.points[i].x - Data.points[i + 1].x);
        }
        
        Data.processedPoints = points;
    }
}
