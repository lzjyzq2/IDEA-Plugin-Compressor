/*
 * Created by JFormDesigner on Sun Jan 23 00:22:27 CST 2022
 */

package cn.settile.lzjyzq2.compressor.form;

import javax.swing.*;
import javax.swing.GroupLayout;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.*;
import com.intellij.ui.components.*;
import com.intellij.ui.treeStructure.*;

/**
 * @author lzjyzq2
 */
public class CompressDialogPanel extends JPanel {

    /**
     * 目标目录
     */
    private VirtualFile targetFile;

    /**
     * 预览文件目录树
     */
    private CollectionListModel<String> treeModel = null;

    public CompressDialogPanel() {
        initComponents();
    }

    public CompressDialogPanel(VirtualFile virtualFile){
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        chooseOutputBtn = new JButton();
        scrollPane1 = new JBScrollPane();
        outputTree = new Tree();
        outputPathEditor = new EditorTextField();

        //======== this ========

        //---- label1 ----
        label1.setText("Output");

        //---- chooseOutputBtn ----
        chooseOutputBtn.setText("...");

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(outputTree);
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(label1)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(outputPathEditor, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(chooseOutputBtn, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(7, 7, 7)
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup()
                                .addComponent(outputPathEditor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                            .addContainerGap())
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(chooseOutputBtn, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                            .addGap(279, 279, 279))))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JButton chooseOutputBtn;
    private JBScrollPane scrollPane1;
    private Tree outputTree;
    private EditorTextField outputPathEditor;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    public VirtualFile getTargetFile() {
        return targetFile;
    }

    public void setTargetFile(VirtualFile targetFile) {
        this.targetFile = targetFile;
    }

    public void refresh(){
        if(targetFile.exists()){
            String path = targetFile.getPath();

        }
    }
}
