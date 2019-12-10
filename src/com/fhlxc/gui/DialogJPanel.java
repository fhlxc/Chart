package com.fhlxc.gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

/**
* @author Xingchao Long
* @date 2019/27/09 20:27:31
* @ClassName DialogJPanel
* @Description 对话框
*/

@SuppressWarnings("serial")
public class DialogJPanel extends JPanel {
    private Color backgroundColor;
    private Image backgroundImage;
    private JPanel contentJPanel;
    private Label contentImageJLabel;
    private Label contentTextJLabel;
    private JPanel buttonJPanel;
    private Button okButton;
    private Button cancelButton;
    private JDialog myDialog;
    
    private boolean close;
    
    public void setMyDialog(JDialog myDialog) {
        this.myDialog = myDialog;
    }
    
    public void setContentJPanel(String textString, Image image) {
        contentJPanel.setLayout(new BorderLayout(0, 0));
        contentImageJLabel.setColor(backgroundColor);
        contentImageJLabel.setPreferredSize(new Dimension(myDialog.getWidth() / 3 * 1, 0));
        contentImageJLabel.setImage(image);
        
        contentTextJLabel.setColor(backgroundColor);
        contentTextJLabel.setxText(textString);
        
        contentJPanel.add(contentImageJLabel, BorderLayout.WEST);
        contentJPanel.add(contentTextJLabel, BorderLayout.CENTER);
        
        this.add(contentJPanel, BorderLayout.CENTER);
    }
    
    public void setButtonJPanel() {
        Color c = new Color(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue(), 150);
        buttonJPanel.setLayout(new BorderLayout(20, 20));
        buttonJPanel.setBackground(c);
        buttonJPanel.add(okButton, BorderLayout.WEST);
        buttonJPanel.add(cancelButton, BorderLayout.CENTER);
        
        this.add(buttonJPanel, BorderLayout.SOUTH);
    }
    
    public void setOkButton(String text, Color color, Color hoverColor, Color pressColor) {
        
        okButton.setPreferredSize(new Dimension(myDialog.getWidth() / 2, myDialog.getHeight() / 5)); 
        okButton.setxText(text);
        okButton.setColor(color);
        okButton.setPressColor(pressColor);
        okButton.setHoverColor(hoverColor);
        okButton.setBorderColor(null);
        
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close = true;
                myDialog.setVisible(false);
            }
        });
    }
    
    public void setCancelButton(String text, Color color, Color hoverColor, Color pressColor) {
        cancelButton.setxText(text);
        cancelButton.setColor(color);
        cancelButton.setPressColor(pressColor);
        cancelButton.setHoverColor(hoverColor);
        cancelButton.setBorderColor(null);
        
        cancelButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                close = false;
                myDialog.setVisible(false);
            }
        });
    }
    
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    
    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }
    
    public boolean getClose() {
        return close;
    }
    
    public void setClose(boolean close) {
        this.close = close;
    }
    
    public DialogJPanel() {
        setLayout(new BorderLayout(0, 0));
        setOpaque(false);
        contentJPanel = new JPanel();
        contentImageJLabel = new Label();
        contentTextJLabel = new Label();
        buttonJPanel= new JPanel();
        cancelButton = new Button();
        okButton = new Button();
    }
    
    public void paintComponent(Graphics g) {
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), backgroundColor, this);
        } 
        if (backgroundImage == null && backgroundColor != null) {
            g.setColor(backgroundColor);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
    }
}
