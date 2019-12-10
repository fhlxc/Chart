package com.fhlxc.gui;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
* @author Xingchao Long
* @date 2019/30/09 20:30:29
* @ClassName Dialog.java
* @Description 类描述
*/

@SuppressWarnings("serial")
public class Dialog extends JDialog {
    private DialogJPanel dialogJPanel;
    
    public Dialog(JFrame frame) {
        dialogJPanel = new DialogJPanel();
        setModal(true);
        setVisible(false);
        setIconImage(new ImageIcon("image/startup.png").getImage());
        int width = frame.getWidth() / 2;
        int height = frame.getHeight() / 4;
        setBounds((frame.getWidth() - width) / 2 + frame.getX(), (frame.getHeight() - height) / 2 + frame.getY(), width, height);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                dialogJPanel.setClose(false);
            }
        });
    }
    
    public void setDialog(String text, String image) {
        dialogJPanel.setMyDialog(this);
        dialogJPanel.setBackgroundColor(Color.white);
        dialogJPanel.setContentJPanel(text, new ImageIcon(image).getImage());
        dialogJPanel.setOkButton("Ok", Color.white, new Color(255, 152, 0), new Color(204, 122, 0));
        dialogJPanel.setCancelButton("Cancel", Color.white, new Color(255, 152, 0), new Color(204, 122, 0));
        dialogJPanel.setButtonJPanel();
        setContentPane(dialogJPanel);
    }
    
    public DialogJPanel getDialogJPanel() {
        return dialogJPanel;
    }
}
