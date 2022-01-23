package cn.settile.lzjyzq2.compressor.dialog;

import cn.settile.lzjyzq2.compressor.form.CompressDialogPanel;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class CompressDialog extends DialogWrapper {

    /**
     * 选中的文件或文件夹
     */
    private VirtualFile targetFile;

    public CompressDialog() {
        super(true);
        init();
    }

    @Override
    protected void init() {
        super.init();
        setTitle("准备压缩");
    }

    public CompressDialog(VirtualFile virtualFile){
        super(true);
        init();
        this.targetFile = virtualFile;
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return new CompressDialogPanel(targetFile);
    }

    public VirtualFile getTargetFile() {
        return targetFile;
    }

    public void setTargetFile(VirtualFile targetFile) {
        this.targetFile = targetFile;
    }

    @Override
    protected Action @NotNull [] createActions() {
        return new Action[]{new CompressAndGenerateAction(),new CompressAction(),new OptionAction(),new GenerateAction()};
    }

    /**
     * 压缩操作
     */
    protected class CompressAction extends DialogWrapper.DialogWrapperAction {

        protected CompressAction() {
            super("压缩");
        }

        @Override
        protected void doAction(ActionEvent e) {

        }
    }

    /**
     * 生成操作
     */
    protected class GenerateAction extends DialogWrapper.DialogWrapperAction {

        protected GenerateAction() {
            super("生成");
        }

        @Override
        protected void doAction(ActionEvent e) {

        }
    }

    /**
     * 配置操作
     */
    protected class OptionAction extends DialogWrapper.DialogWrapperAction {

        protected OptionAction() {
            super("生成");
        }

        @Override
        protected void doAction(ActionEvent e) {

        }
    }

    /**
     * 压缩与配置操作
     */
    protected class CompressAndGenerateAction extends DialogWrapper.DialogWrapperAction {

        protected CompressAndGenerateAction() {
            super("生成");
        }

        @Override
        protected void doAction(ActionEvent e) {

        }
    }
}


