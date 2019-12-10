package com.fhlxc.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
* @author Xingchao Long
* @date 2019/36/10 18:36:14
* @ClassName ScrollBarUI
* @Description 实现自定义的滚动条
*/

public class ScrollBarUI extends BasicScrollBarUI {
    private Image image1;
    private Image image2;
    
    public ScrollBarUI(Image image1, Image image2) {
        this.image1 = image1;
        this.image2 = image2;
    }
    
    public Dimension getPreferredSize(JComponent c) {
        
        if (this.scrollbar.getOrientation() == JScrollBar.VERTICAL) {
            c.setPreferredSize(new Dimension(10, 0));
        }
 
        if (this.scrollbar.getOrientation() == JScrollBar.HORIZONTAL) {
            c.setPreferredSize(new Dimension(0, 10));
        }
        return super.getPreferredSize(c);
    }
    
    public void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        Graphics2D g2 = (Graphics2D) g;
        GradientPaint gp = null;
        
        if (this.scrollbar.getOrientation() == JScrollBar.VERTICAL) {
            gp = new GradientPaint(0, 0, Color.white, trackBounds.width, 0, Color.white);
        }
 
        if (this.scrollbar.getOrientation() == JScrollBar.HORIZONTAL) {
            gp = new GradientPaint(0, 0, Color.white, trackBounds.height, 0, Color.white);
        }
        
        g2.setPaint(gp);
        g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
        
        if (trackHighlight == BasicScrollBarUI.DECREASE_HIGHLIGHT) {
            this.paintDecreaseHighlight(g);
        }
 
        if (trackHighlight == BasicScrollBarUI.INCREASE_HIGHLIGHT) {
            this.paintIncreaseHighlight(g);
        }
    }
    
    public void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        g.translate(thumbBounds.x, thumbBounds.y);
        g.setColor(new Color(255, 152, 0));
        g.fillRoundRect(0, 0, 10, thumbBounds.height - 1, 5, 5);
        
        if (this.scrollbar.getOrientation() == JScrollBar.VERTICAL) {
            g.fillRoundRect(0, 0, 10, thumbBounds.height - 1, 5, 5);
        }
 
        if (this.scrollbar.getOrientation() == JScrollBar.HORIZONTAL) {
            g.fillRoundRect(0, 0, thumbBounds.width - 1, 10, 5, 5);
        }
    }
    
    public Button createDecreaseButton(int orientation) {
        Button upButton = new Button();
        upButton.setPressColor(new Color(204, 122, 0));
        upButton.setHoverColor(new Color(255, 152, 0));
        upButton.setColor(Color.white);
        upButton.setImage(image1);
        upButton.setBorderColor(null);
        return upButton;
    }
    
    public Button createIncreaseButton(int orientation) {
        Button downButton = new Button();
        downButton.setPressColor(new Color(204, 122, 0));
        downButton.setHoverColor(new Color(255, 152, 0));
        downButton.setColor(Color.white);
        downButton.setImage(image2);
        downButton.setBorderColor(null);
        return downButton;
    }
}
