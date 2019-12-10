package com.fhlxc.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.fhlxc.data.Data;

/**
* @author Xingchao Long
* @date 2019/17/26 18:17:23
* @ClassName MainWindow
* @Description 存放主界面
*/

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
    private final int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private final int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    private final int initialWidth = 1024;
    private final int intialHeight = 768;
    private final int x = (screenWidth - initialWidth) / 2;
    private final int y = (screenHeight - intialHeight) / 2;
    
    private JPanel contentPane;
    private DisplayChartJPanel displayChartJPanel;
    private ButtonJPanel buttonJPanel;
    
    private final String startupImage = "image/startup.png";
    private final Color buttonColor = Color.white;
    private final Color buttonHoverColor = new Color(255, 152, 0);
    private final Color buttonPressColor = new Color(204, 122, 0);
    
    public static Dialog dialog;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainWindow frame = new MainWindow();
                try {
                    frame.setVisible(true);
                    dialog = new Dialog(frame);
                    dialog.setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                    frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            }
        });
    }

    public MainWindow() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setIconImage(new ImageIcon(startupImage).getImage());
        setBounds(x, y, initialWidth, intialHeight);
        
        contentPane = new JPanel();
        displayChartJPanel = new DisplayChartJPanel();
        buttonJPanel = new ButtonJPanel(buttonColor, buttonHoverColor, buttonPressColor, 137, 30);
        
        buttonJPanel.setChartJPanel(displayChartJPanel.getChartJPanel1(), displayChartJPanel.getChartJPanel2());
        buttonJPanel.setFrame(this);
        Data.currChannel = displayChartJPanel.getChartJPanel1();
        Data.multiple = Data.currChannel.getMultiple();
        
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(15, 0));
        contentPane.setBackground(Color.white);
        
        contentPane.add(displayChartJPanel, BorderLayout.CENTER);
        contentPane.add(buttonJPanel, BorderLayout.EAST);
        
        setContentPane(contentPane);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                MainWindow.dialog.setDialog("确定关闭对话框吗？", "image/warning.png");
                MainWindow.dialog.setVisible(true);
                if (MainWindow.dialog.getDialogJPanel().getClose()) {
                    System.exit(0);
                }
            }
        });
    }
}
