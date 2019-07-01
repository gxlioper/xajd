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

    /*תPDF��ʽֵ*/
    private static final int wdFormatPDF = 17;
    private static final int xlFormatPDF = 0;
    private static final int ppFormatPDF = 32;

    /**
     * ��Office�ĵ�ת��ΪPDF
     * Linux������ʹ�õ�OpenOffice��LibreOffice
     * Windows������ʹ�õ�msOffice
     * ��֧��doc,docx,xls,xlsx,ppt,pptx����ʵҲ֧��txt����ת��ANSI2UTF8�����ﲻ��ʵ�֣�
     * ʹ��jodconverter��Ҫ����officeHome
     */
    public static boolean office2pdf(File inputFile,File pdfFile,String officeHome) {

        boolean result = false;

        //���ж��ļ��Ƿ����
        if(inputFile.exists()){
            //���ж�ƽ̨����
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
     * Word�ĵ�ת��
     */
    private static boolean word2pdf(String inputFilePath, String pdfFilePath) {

        boolean result = true;
        ComThread.InitSTA();

        ActiveXComponent app = null;
        Dispatch doc = null;
        try {
            app = new ActiveXComponent("Word.Application");// ����һ��word����
            app.setProperty("Visible", new Variant(false)); // ���ɼ���word
            app.setProperty("AutomationSecurity", new Variant(3)); // ���ú�
            Dispatch docs = app.getProperty("Documents").toDispatch();// ��ȡ�ĵ�����

            // Object[]�����������Ǳ�ʾ���Ƿ�ֻ����ʽ�򿪡�
            // ����Documents������Open�������ĵ��������ش򿪵��ĵ�����Document
            doc = Dispatch.call(docs, "Open", inputFilePath, false, true).toDispatch();
            // ����Document�����SaveAs���������ĵ�����Ϊpdf��ʽ
            Dispatch.call(doc, "SaveAs", pdfFilePath, wdFormatPDF);//word����Ϊpdf��ʽ�ֵ꣬Ϊ17
//            Dispatch.call(doc, "ExportAsFixedFormat", pdfFile, wdFormatPDF); // word����Ϊpdf��ʽ�ֵ꣬Ϊ17
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
            Dispatch.call(doc, "Close", false);
            if (app != null){
                app.invoke("Quit", new Variant[] {});
            }

        }
        // ���û����仰,winword.exe���̽�����ر�
        ComThread.Release();
        ComThread.quitMainSTA();
        return result;
    }

    /**
     * PPT�ĵ�ת��
     */
    private static boolean ppt2pdf(String inputFilePath, String pdfFilePath) {

        boolean result = true;
        ComThread.InitSTA();

        ActiveXComponent app = null;
        Dispatch ppt = null;
        try {
            app = new ActiveXComponent("PowerPoint.Application");// ����һ��PPT����
            // app.setProperty("Visible", new Variant(false)); // ���ɼ��򿪣�PPTת�����������أ���������Ҫע�͵���
            // app.setProperty("AutomationSecurity", new Variant(3)); // ���ú�
            Dispatch ppts = app.getProperty("Presentations").toDispatch();// ��ȡ�ĵ�����

            // ����Documents������Open�������ĵ��������ش򿪵��ĵ�����Document
            ppt = Dispatch.call(ppts, "Open", inputFilePath,
                    true,// ReadOnly
                    true,// Untitledָ���ļ��Ƿ��б���
                    false// WithWindowָ���ļ��Ƿ�ɼ�
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
     * Excel�ĵ�ת��
     */
    private static boolean excel2pdf(String  inputFilePath, String pdfFilePath) {

        boolean result = true;
        ComThread.InitSTA();

        ActiveXComponent app = null;
        Dispatch excel = null;
        try {
            app = new ActiveXComponent("Excel.Application");// ����һ��PPT����
            app.setProperty("Visible", new Variant(false)); // ���ɼ���
            // app.setProperty("AutomationSecurity", new Variant(3)); // ���ú�
            Dispatch excels = app.getProperty("Workbooks").toDispatch();// ��ȡ�ĵ�����

            // ����Documents������Open�������ĵ��������ش򿪵��ĵ�����Document
            excel = Dispatch.call(excels, "Open", inputFilePath, false, true).toDispatch();
            // ����Document���󷽷������ĵ�����Ϊpdf��ʽ
            // Excel ���ܵ���SaveAs����
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
