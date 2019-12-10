package com.fhlxc.gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
* @author Xingchao Long
* @date 2019/11/07 19:11:35
* @ClassName displayChartJPanel
* @Description 显示两个通道的面板
*/

@SuppressWarnings("serial")
public class DisplayChartJPanel extends JPanel {
    private ScrollPane scrollPane1;
    private ScrollPane scrollPane2;
    private ChartJPanel chartJPanel1;
    private ChartJPanel chartJPanel2;
    
    public DisplayChartJPanel() {
        setOpaque(false);
        setLayout(new GridLayout(2, 1));
        setChannel1("通道1");
        setChannel2("通道2");
    }
    
    private void setChannel1(String text) {
        scrollPane1 = new ScrollPane(text);
        chartJPanel1 = new ChartJPanel();
        
        scrollPane1.setOpaque(false);
        scrollPane1.setBorder(new Color(255, 152, 0));
        scrollPane1.getVerticalScrollBar().setUI(new ScrollBarUI(new ImageIcon("image/up.png").getImage(), new ImageIcon("image/down.png").getImage()));
        scrollPane1.getVerticalScrollBar().setUnitIncrement(15);
        scrollPane1.getHorizontalScrollBar().setUI(new ScrollBarUI(new ImageIcon("image/left.png").getImage(), new ImageIcon("image/right.png").getImage()));
        scrollPane1.getHorizontalScrollBar().setUnitIncrement(15);
        scrollPane1.setViewportView(chartJPanel1);
        scrollPane1.getViewport().setOpaque(false);
        
        add(scrollPane1);
    }
    
    public ChartJPanel getChartJPanel1() {
        return chartJPanel1;
    }

    public ChartJPanel getChartJPanel2() {
        return chartJPanel2;
    }

    private void setChannel2(String text) {
        scrollPane2 = new ScrollPane(text);
        chartJPanel2 = new ChartJPanel();
        
        scrollPane2.setOpaque(false);
        scrollPane2.setBorder(new Color(255, 152, 0));
        scrollPane2.selected(false);
        scrollPane2.getVerticalScrollBar().setUI(new ScrollBarUI(new ImageIcon("image/up.png").getImage(), new ImageIcon("image/down.png").getImage()));
        scrollPane2.getVerticalScrollBar().setUnitIncrement(15);
        scrollPane2.getHorizontalScrollBar().setUI(new ScrollBarUI(new ImageIcon("image/left.png").getImage(), new ImageIcon("image/right.png").getImage()));
        scrollPane2.getHorizontalScrollBar().setUnitIncrement(15);
        scrollPane2.setViewportView(chartJPanel2);
        scrollPane2.getViewport().setOpaque(false);
        
        add(scrollPane2);
    }
}
