package com.fhlxc.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;

/**
* @author Xingchao Long
* @date 2019/28/09 20:28:47
* @ClassName Label
* @Description 自定义标签，存放图片和文字内容
*/

@SuppressWarnings("serial")
public class Label extends Button {
    private Color color;
    private String text;
    private Image image;
    private Font font;
    private Color fontColor;
    
    private boolean left;
    
    public void setLeft(boolean left) {
        this.left = left;
    }
     
    public void setColor(Color color) {
        this.color = color;
    }
    
    public void setxText(String text) {
        this.text = text;
    }
    
    public void setImage(Image image) {
        this.image = image;
    }
    
    public void setFont(Font font) {
        this.font = font;
    }
    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }
    
    public Label() {
        this.text = "";
        setBorderPainted(false);
        setOpaque(false);
        fontColor = Color.black;
        Font font1 = new Font("宋体", Font.PLAIN, 15);
        font = font1;
    }
    
    public void paintComponent(Graphics g) {
        if (image != null) {
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), color, this);
        }
        
        if (image == null && color != null) {
            g.setColor(color);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
        g.setColor(fontColor);
        g.setFont(font);
        FontMetrics metrics = g.getFontMetrics(font);
        int strWidth = metrics.stringWidth(text);
        int strHeight = metrics.getHeight();
        if (left) {
            g.drawString(text, 0, (this.getHeight() - strHeight) / 2 + metrics.getAscent());
        } else {
            g.drawString(text, (this.getWidth() - strWidth) / 2, (this.getHeight() - strHeight) / 2 + metrics.getAscent());
        }
    }
}
