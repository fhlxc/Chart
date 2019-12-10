package com.fhlxc.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

/**
* @author Xingchao Long
* @date 2019/25/07 19:25:34
* @ClassName Button
* @Description 实现自定义的按钮
*/

@SuppressWarnings("serial")
public class Button extends JButton {
    private Color color;
    private Color hoverColor;
    private Color pressColor;
    private String text;
    private Font font;
    private Color fontColor;
    private Color tmpColor;
    private Color borderColor;
    private Image image;
    
    private class MyMouseListener implements MouseListener {
        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                tmpColor = color;
            }
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                tmpColor = pressColor;
            }
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            tmpColor = color;
        }
        
        @Override
        public void mouseEntered(MouseEvent e) {
            tmpColor = hoverColor;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                tmpColor = color;
            }
        }
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setColor(Color color) {
        this.color = color;
        tmpColor = color;
    }

    public void setHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor;
    }

    public void setPressColor(Color pressColor) {
        this.pressColor = pressColor;
    }
    
    public void setxText(String text) {
        this.text = text;
    }
    
    public void setFont(Font font) {
        this.font = font;
    }
    
    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }
    
    public Button() {
        text = "";
        Font font1 = new Font("宋体", Font.PLAIN, 15);
        fontColor = Color.black;
        borderColor = Color.black;
        font = font1;
        addMouseListener(new MyMouseListener());
        setBorderPainted(false);
        setOpaque(false);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        FontMetrics metrics = g.getFontMetrics(font);
        int strWidth = metrics.stringWidth(text);
        int strHeight = metrics.getHeight();
        
        if (image != null) {
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), tmpColor, this);
        }
        
        if (image == null && tmpColor != null) {
            g.setColor(tmpColor);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
        
        if (borderColor != null) {
            g.setColor(borderColor);
            g.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
        }
        
        g.setColor(fontColor);
        g.setFont(font);
        g.drawString(text, (this.getWidth() - strWidth) / 2, (this.getHeight() - strHeight) / 2 + metrics.getAscent());
    }
    
}
