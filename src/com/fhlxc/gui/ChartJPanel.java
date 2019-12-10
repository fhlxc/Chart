package com.fhlxc.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

/**
* @author Xingchao Long
* @date 2019/06/07 19:06:35
* @ClassName ChartJPanel
* @Description 上下两个通道的按钮
*/

@SuppressWarnings("serial")
public class ChartJPanel extends JPanel {
    private final Color lineColor = new Color(255, 152, 0);
    
    private boolean selected;
    private int width;
    private int height;
    private float multiple;
    
    private Point[] points;
    
    public ChartJPanel() {
        setOpaque(false);
        multiple = 0.01f;
    }

    public float getMultiple() {
        return multiple;
    }

    public void setMultiple(float multiple) {
        this.multiple = multiple;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setSizeANDPoints(Point[] points) {
        this.points = points;
        int length = points.length;
        int max = 0;

        for (Point point: points) {
            int n = Math.abs(point.y);
            if (max < n) {
                max = n;
            }
        }
        
        width = (int) (length * 1000 * multiple) + 10;
        height = max + 10;
        setPreferredSize(new Dimension(width, 2 * height));
    }
    
    @Override
    public void paintComponent(Graphics g) {
        if (points != null) {
            int length = points.length;
            g.setColor(lineColor);
            for (int i = 0; i < length - 1; i++) {
                g.drawLine(points[i].x, height + points[i].y, points[i + 1].x, height + points[i + 1].y);
            }
            g.drawLine(0, height, width, height);
        }
    }
}
