package com.zfsoft.xgxt.base.util;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.artofsolving.jodconverter.util.PlatformUtils;

import java.io.File;

public class Office2PdfUtil {

    /*转PDF格式值*/
    private static final int wdFormatPDF = 17;
    private static final int xlFormatPDF = 0;
    private static final int ppFormatPDF = 32;

    /**
     * 将Office文档转换为PDF
     * Linux环境下使用到OpenOffice或LibreOffice
     * Windows环境下使用到msOffice
     * 均支持doc,docx,xls,xlsx,ppt,pptx（其实也支持txt，需转码ANSI2UTF8，这里不作实现）
     * 使用jodconverter需要传入officeHome
     */
    public static boolean office2pdf(File inputFile,File pdfFile,String officeHome) {

        boolean result = false;

        //先判断文件是否存在
        if(inputFile.exists()){
            //再判断平台环境
            if(PlatformUtils.isLinux()){
                result = convert2pdfByJodconverter(inputFile,pdfFile,officeHome);
            }else if(PlatformUtils.isWindows()){
//                result = convert2pdfByJodconverter(inputFile,pdfFile);
                String inputFilePath = inputFile.getAbsolutePath();
                String pdfFilePath = pdfFile.getAbsolutePath();
                result = convert2pdfByJacob(inputFilePath,pdfFilePath);
            }
        }
        return result;
    }

    private static boolean convert2pdfByJodconverter(File inputFile, File pdfFile,String officeHome) {

        DefaultOfficeManagerConfiguration config = new DefaultOfficeManagerConfiguration();
        config.setOfficeHome(officeHome);

        OfficeManager officeManager = config.buildOfficeManager();
        officeManager.start();

        OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
        converter.convert(inputFile, pdfFile);

        officeManager.stop();
        return true;
    }

    private static boolean convert2pdfByJacob(String inputFilePath, String pdfFilePath) {

        boolean result = false;
        String suffix = inputFilePath.substring(inputFilePath.lastIndexOf(".")+1);

        if (suffix.equals("doc") || suffix.equals("docx") || suffix.equals("txt")) {
            result = word2pdf(inputFilePath, pdfFilePath);
        } else if (suffix.equals("ppt") || suffix.equals("pptx")) {
            result = ppt2pdf(inputFilePath, pdfFilePath);
        } else if (suffix.equals("xls") || suffix.equals("xlsx")) {
            result = excel2pdf(inputFilePath, pdfFilePath);
        }

        return result;
    }

    /**
     * Word文档转换
     */
    private static boolean word2pdf(String inputFilePath, String pdfFilePath) {

        boolean result = true;
        ComThread.InitSTA();

        ActiveXComponent app = null;
        Dispatch doc = null;
        try {
            app = new ActiveXComponent("Word.Application");// 创建一个word对象
            app.setProperty("Visible", new Variant(false)); // 不可见打开word
            app.setProperty("AutomationSecurity", new Variant(3)); // 禁用宏
            Dispatch docs = app.getProperty("Documents").toDispatch();// 获取文挡属性

            // Object[]第三个参数是表示“是否只读方式打开”
            // 调用Documents对象中Open方法打开文档，并返回打开的文档对象Document
            doc = Dispatch.call(docs, "Open", inputFilePath, false, true).toDispatch();
            // 调用Document对象的SaveAs方法，将文档保存为pdf格式
            Dispatch.call(doc, "SaveAs", pdfFilePath, wdFormatPDF);//word保存为pdf格式宏，值为17
//            Dispatch.call(doc, "ExportAsFixedFormat", pdfFile, wdFormatPDF); // word保存为pdf格式宏，值为17
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
            Dispatch.call(doc, "Close", false);
            if (app != null){
                app.invoke("Quit", new Variant[] {});
            }

        }
        // 如果没有这句话,winword.exe进程将不会关闭
        ComThread.Release();
        ComThread.quitMainSTA();
        return result;
    }

    /**
     * PPT文档转换
     */
    private static boolean ppt2pdf(String inputFilePath, String pdfFilePath) {

        boolean result = true;
        ComThread.InitSTA();

        ActiveXComponent app = null;
        Dispatch ppt = null;
        try {
            app = new ActiveXComponent("PowerPoint.Application");// 创建一个PPT对象
            // app.setProperty("Visible", new Variant(false)); // 不可见打开（PPT转换不运行隐藏，所以这里要注释掉）
            // app.setProperty("AutomationSecurity", new Variant(3)); // 禁用宏
            Dispatch ppts = app.getProperty("Presentations").toDispatch();// 获取文挡属性

            // 调用Documents对象中Open方法打开文档，并返回打开的文档对象Document
            ppt = Dispatch.call(ppts, "Open", inputFilePath,
                    true,// ReadOnly
                    true,// Untitled指定文件是否有标题
                    false// WithWindow指定文件是否可见
            ).toDispatch();

            Dispatch.call(ppt, "SaveAs", pdfFilePath, ppFormatPDF);
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        } finally {
            Dispatch.call(ppt, "Close");
            if (app != null){
                app.invoke("Quit", new Variant[] {});
            }
        }
        ComThread.Release();
        ComThread.quitMainSTA();
        return result;
    }

    /**
     * Excel文档转换
     */
    private static boolean excel2pdf(String  inputFilePath, String pdfFilePath) {

        boolean result = true;
        ComThread.InitSTA();

        ActiveXComponent app = null;
        Dispatch excel = null;
        try {
            app = new ActiveXComponent("Excel.Application");// 创建一个PPT对象
            app.setProperty("Visible", new Variant(false)); // 不可见打开
            // app.setProperty("AutomationSecurity", new Variant(3)); // 禁用宏
            Dispatch excels = app.getProperty("Workbooks").toDispatch();// 获取文挡属性

            // 调用Documents对象中Open方法打开文档，并返回打开的文档对象Document
            excel = Dispatch.call(excels, "Open", inputFilePath, false, true).toDispatch();
            // 调用Document对象方法，将文档保存为pdf格式
            // Excel 不能调用SaveAs方法
            Dispatch.call(excel, "ExportAsFixedFormat", xlFormatPDF, pdfFilePath);
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        } finally {
            Dispatch.call(excel, "Close", false);
            if (app != null){
                app.invoke("Quit", new Variant[] {});
            }
        }
        ComThread.Release();
        ComThread.quitMainSTA();
        return result;
    }
}
