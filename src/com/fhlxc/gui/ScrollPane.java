package com.fhlxc.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

import com.fhlxc.data.Data;

/**
* @author Xingchao Long
* @date 2019/18/09 01:18:19
* @ClassName ScrollPane
* @Description 重构的滚动面板
*/

@SuppressWarnings("serial")
public class ScrollPane extends JScrollPane {
    private String text;
    private Color color;
    
    public ScrollPane(String text) {
        this.text = text;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ScrollPane scrollPane = (ScrollPane) e.getSource();
                Component component = scrollPane.getViewport().getView();
                ChartJPanel chartJPanel = (ChartJPanel) component;
                if (chartJPanel != Data.currChannel) {
                    ScrollPane s = (ScrollPane) Data.currChannel.getParent().getParent();
                    s.selected(false);
                    scrollPane.selected(true);
                    Data.currChannel = chartJPanel;
                    Data.multiple = chartJPanel.getMultiple();
                }
            }
        });
    }
    
    public void setBorder(Color color) {
        this.color = color;
        setBorder(BorderFactory.createLineBorder(this.color, 1));
    }
    
    public void selected(boolean select) {
        if (select) {
            setBorder(BorderFactory.createLineBorder(color, 1));
        } else {
            setBorder(BorderFactory.createLineBorder(Color.white, 1));
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        Font font = new Font("宋体", Font.PLAIN, 12);
        g.setFont(font);
        FontMetrics metrics = g.getFontMetrics(font);
        g.drawString(text, 0, metrics.getAscent());
        g.setColor(new Color(255, 152, 0));
    }
}
