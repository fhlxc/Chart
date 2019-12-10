package com.fhlxc.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.fhlxc.data.Data;
import com.fhlxc.process.DataProcess;

/**
* @author Xingchao Long
* @date 2019/03/07 19:03:06
* @ClassName ButtonJPanel
* @Description 右侧的几个功能按钮
*/

@SuppressWarnings("serial")
public class ButtonJPanel extends JPanel {
    private final int gap = 10;
    
    private int buttonWidth;
    private int buttonHeight;
    
    private Button loadFileButton;
    private Button enlargeButton;
    private Button shrinkButton;
    private Button compressButton;
    private Button differentialButton;
    
    private Color buttonColor;
    private Color buttonHoverColor;
    private Color buttonPressColor;
    
    private ChartJPanel chartJPanel1;
    private ChartJPanel chartJPanel2;
    private JFrame frame;
    
    public ButtonJPanel(Color color, Color hoverColor, Color pressColor, int width, int height) {
        buttonColor = color;
        buttonHoverColor = hoverColor;
        buttonPressColor = pressColor;
        buttonWidth = width;
        buttonHeight = height;
        
        setLayout(new VFlowLayout(VFlowLayout.MIDDLE, false, false));
        setOpaque(false);
        setPreferredSize(new Dimension(buttonWidth + 2 * gap, buttonHeight));
        
        setLoadFileButton();
        setEnlargeButton();
        setShrinkButton();
        setCompressButton();
        setDifferentialButton();
    }
    
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
    
    public void setChartJPanel(ChartJPanel chartJPanel1, ChartJPanel chartJPanel2) {
        this.chartJPanel1 = chartJPanel1;
        this.chartJPanel2 = chartJPanel2;
    }
    
    public void setEnabled(boolean enable) {
        loadFileButton.setEnabled(enable);
        enlargeButton.setEnabled(enable);
        shrinkButton.setEnabled(enable);
        compressButton.setEnabled(enable);
        differentialButton.setEnabled(enable);
    }
    
    private void setLoadFileButton() {
        loadFileButton = new Button();
        loadFileButton.setColor(buttonColor);
        loadFileButton.setHoverColor(buttonHoverColor);
        loadFileButton.setPressColor(buttonPressColor);
        loadFileButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        loadFileButton.setBorderColor(buttonHoverColor);
        loadFileButton.setxText("加载文件");
        loadFileButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                FileDialog dialog = new FileDialog(new Frame(), "选择要加载的文件", FileDialog.LOAD);
                dialog.setDirectory(System.getProperty("user.home") + "\\desktop");
                dialog.setVisible(true);
                String path = dialog.getDirectory() + dialog.getFile();
                if (DataProcess.checkFile(path)) {
                    frame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                    setEnabled(false);
                    DataProcess.initialData(path);
                    DataProcess.loadData(path);
                    chartJPanel1.setMultiple(Data.multiple);
                    chartJPanel1.setSizeANDPoints(DataProcess.multipleData(Data.points));
                    chartJPanel1.updateUI();
                    frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    setEnabled(true);
                }
            }
        });
        
        add(loadFileButton);
    }
    
    private void setEnlargeButton() {
        enlargeButton = new Button();
        enlargeButton.setColor(buttonColor);
        enlargeButton.setHoverColor(buttonHoverColor);
        enlargeButton.setPressColor(buttonPressColor);
        enlargeButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        enlargeButton.setBorderColor(buttonHoverColor);
        enlargeButton.setxText("放大");
        enlargeButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Data.points == null) {
                    MainWindow.dialog.setDialog("请先加载文件", "image/error.png");
                    MainWindow.dialog.setVisible(true);
                    return;
                }
                if (Data.multiple > 0.1) {
                    enlargeButton.setEnabled(false);
                    shrinkButton.setEnabled(true);
                } else {
                    Data.multiple = Data.multiple * 2;
                    Data.currChannel.setMultiple(Data.multiple);
                    if (Data.currChannel == chartJPanel1) {
                        Data.currChannel.setSizeANDPoints(DataProcess.multipleData(Data.points));
                    }
                    if (Data.currChannel == chartJPanel2) {
                        if (Data.processedPoints == null) {
                            MainWindow.dialog.setDialog("请先处理数据", "image/error.png");
                            MainWindow.dialog.setVisible(true);
                            return;
                        }
                        Data.currChannel.setSizeANDPoints(DataProcess.multipleData(Data.processedPoints));
                    }
                    Data.currChannel.updateUI();
                }
            }
        });
        
        add(enlargeButton);
    }
    
    private void setShrinkButton() {
        shrinkButton = new Button();
        shrinkButton.setColor(buttonColor);
        shrinkButton.setHoverColor(buttonHoverColor);
        shrinkButton.setPressColor(buttonPressColor);
        shrinkButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        shrinkButton.setBorderColor(buttonHoverColor);
        shrinkButton.setxText("缩小");
        shrinkButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Data.points == null) {
                    MainWindow.dialog.setDialog("请先加载文件", "image/error.png");
                    MainWindow.dialog.setVisible(true);
                    return;
                }
                if (Data.multiple < 0.001) {
                    enlargeButton.setEnabled(true);
                    shrinkButton.setEnabled(false);
                } else {
                    Data.multiple = Data.multiple * 0.5f;
                    Data.currChannel.setMultiple(Data.multiple);
                    if (Data.currChannel == chartJPanel1) {
                        Data.currChannel.setSizeANDPoints(DataProcess.multipleData(Data.points));
                    }
                    if (Data.currChannel == chartJPanel2) {
                        if (Data.processedPoints == null) {
                            MainWindow.dialog.setDialog("请先处理数据", "image/error.png");
                            MainWindow.dialog.setVisible(true);
                            return;
                        }
                        Data.currChannel.setSizeANDPoints(DataProcess.multipleData(Data.processedPoints));
                    }
                    Data.currChannel.updateUI();
                }
            }
        });
        
        add(shrinkButton);
    }
    
    private void setCompressButton() {
        compressButton = new Button();
        compressButton.setColor(buttonColor);
        compressButton.setHoverColor(buttonHoverColor);
        compressButton.setPressColor(buttonPressColor);
        compressButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        compressButton.setBorderColor(buttonHoverColor);
        compressButton.setxText("压缩");
        compressButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Data.points == null) {
                    MainWindow.dialog.setDialog("请先加载文件", "image/error.png");
                    MainWindow.dialog.setVisible(true);
                    return;
                }
                Data.currChannel = chartJPanel2;
                ScrollPane s1 = (ScrollPane) chartJPanel1.getParent().getParent();
                s1.selected(false);
                ScrollPane s2 = (ScrollPane) chartJPanel2.getParent().getParent();
                s2.selected(true);
                DataProcess.compressData();
                chartJPanel2.setSizeANDPoints(DataProcess.multipleData(Data.processedPoints));
                chartJPanel2.updateUI();
                differentialButton.setEnabled(true);
                compressButton.setEnabled(false);
            }
        });
        
        add(compressButton);
    }
    
    private void setDifferentialButton() {
        differentialButton = new Button();
        differentialButton.setColor(buttonColor);
        differentialButton.setHoverColor(buttonHoverColor);
        differentialButton.setPressColor(buttonPressColor);
        differentialButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        differentialButton.setBorderColor(buttonHoverColor);
        differentialButton.setxText("微分变化");
        differentialButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Data.points == null) {
                    MainWindow.dialog.setDialog("请先加载文件", "image/error.png");
                    MainWindow.dialog.setVisible(true);
                    return;
                }
                Data.currChannel = chartJPanel2;
                ScrollPane s1 = (ScrollPane) chartJPanel1.getParent().getParent();
                s1.selected(false);
                ScrollPane s2 = (ScrollPane) chartJPanel2.getParent().getParent();
                s2.selected(true);
                DataProcess.differentialData();
                chartJPanel2.setSizeANDPoints(DataProcess.multipleData(Data.processedPoints));
                chartJPanel2.updateUI();
                differentialButton.setEnabled(false);
                compressButton.setEnabled(true);
            }
        });
        
        add(differentialButton);
    }
}
