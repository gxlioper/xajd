package com.zfsoft.xgxt.rcsw.rcxwwhnew.comm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.Office2PdfUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ResourceBundle;

/**
 * @�๦������:Ԥ��
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-07-05 09:12
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class YlAction extends SuperAction {
    private Log logger = LogFactory.getLog(YlAction.class);

    private static ResourceBundle resource = ResourceBundle.getBundle("config/fileUploadConfig");

    private String basePath = null;

    private static final String PRIFEX_ZF = "ZFXG_UPLOADFILES";
    private static final String CONVERT = "convert_pdf";
    /**
     * @���� ��TODO�����µ�ǰ���췽��
     */
    public YlAction() {
        super();
        try {
            basePath = resource.getString("filesys.local.dir");
            //���û�и����ļ��洢·������Ĭ�ϸ�ϵͳ�û�Ŀ¼
            //Windows �û�Ŀ¼��
            if(StringUtils.isBlank(basePath)){
                basePath = System.getProperty("user.home") +File.separator + PRIFEX_ZF;
            }
            logger.info("�ϴ�����·��:---" + basePath + "---");
        } catch (Exception e) {
            e.printStackTrace();
            basePath = System.getProperty("user.home") +File.separator + PRIFEX_ZF;
            logger.info("�ϴ�����·��:---" + basePath + "---");
        }

    }
    /**
     *  Ԥ��.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-01-22 11:19
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    public ActionForward preview(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String fjlj = request.getParameter("fjlj");
        fjlj = fjlj.replaceAll("\\\\","!!");
        String fjmc = request.getParameter("fjmc");
        fjmc=new String(fjmc.getBytes("iso8859-1"),"UTF-8");
        if(StringUtils.isNotBlank(fjlj)){
            String ext=fjmc.substring(fjmc.lastIndexOf(".")+1);
            //��Ҫ�ж�ext�Ƿ�֧��Ԥ��
            if(resource.containsKey(ext)){
                String cvt = resource.getString(ext);
                request.setAttribute("fjlj",fjlj);
                request.setAttribute("fjmc",fjmc);
                if("pdf".equals(cvt)){
                    return mapping.findForward("previewPdf");
                }else{
                    //��������ͼƬ��ʽ
                    return mapping.findForward("previewImage");
                }
            }
        }

        return null;
    }

    public ActionForward getPdfFile(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String fjlj = request.getParameter("fjlj");
        fjlj = fjlj.replaceAll("!!","\\\\");
        fjlj=new String(fjlj.getBytes("ISO8859-1"),"UTF-8");
        String fjmc = request.getParameter("fjmc");

        String filename=fjlj.substring(fjlj.lastIndexOf("\\")+1,fjlj.lastIndexOf("."));
        String fileQm = fjlj.substring(fjlj.lastIndexOf("\\")+1);
        fjmc=new String(fjmc.getBytes("iso8859-1"),"UTF-8");
        if(StringUtils.isNotBlank(fjlj)){
            String ext=fjmc.substring(fjmc.lastIndexOf(".")+1);
                String cvt = resource.getString(ext);
                InputStream is = null;
                if("pdf".equals(ext)){
                    //����ת��
                    try {
                        is = new FileInputStream(new File(basePath + File.separator + filename));
                        //����ļ������ڣ�ȥϵͳ�û�Ĭ��·���²���
                    } catch (FileNotFoundException e) {

                        if(!StringUtils.equals(System.getProperty("user.home") +File.separator + PRIFEX_ZF, basePath)){
                            is = new FileInputStream(new File(System.getProperty("user.home") +File.separator + PRIFEX_ZF + File.separator +filename));
                        }else{
                            throw new Exception(e);
                        }

                    }

                }else if("pdf".equals(cvt)){
                    //��Ҫת��Ϊpdf
                    File baseDir = new File(basePath);
                    if(!baseDir.exists()){
                        if(!StringUtils.equals(System.getProperty("user.home") +File.separator + PRIFEX_ZF, basePath)){
                            baseDir = new File(System.getProperty("user.home") +File.separator + PRIFEX_ZF );
                        }else{
                            throw new Exception("�ļ�·��������");
                        }
                    }
                    File file = new File(baseDir.getAbsolutePath(),fileQm);
                    File pdfFile = new File(baseDir.getAbsolutePath()+File.separator+CONVERT, filename+".pdf");
                    if(!pdfFile.getParentFile().exists()){
                        pdfFile.getParentFile().mkdir();
                    }else {
                        //ת��ǰ�ж� ת���ļ����ļ����Ƿ�����
                        String convertCacheNum = resource.getString("convertCacheNum");
                        if(StringUtils.isNotBlank(convertCacheNum)){
                            int n = Integer.parseInt(convertCacheNum);
                            if(pdfFile.getParentFile().listFiles().length > n){
                                FileUtil.delAllFile(pdfFile.getParent());
                            }
                        }
                    }
                    //ת������
                    if(!pdfFile.exists()&&file.exists()){
                        //Linux��������ҪofficeHomeֵ����Ϊ�գ������������ļ�������
                        //Windows������officeHomeֵΪ�ռ��ɣ������������ļ������ø���
                        String officeHome = resource.getString("officeHome");
                        Office2PdfUtil.office2pdf(file,pdfFile,officeHome);
                    }
                    is = new FileInputStream(pdfFile);
                }

                if(is != null){
                    byte[] b = new byte[2048];
                    response.setHeader("Content-Disposition", "attachment;filename="
                            + encodingFileName(filename));

                    OutputStream os = response.getOutputStream();
                    int len;
                    while ((len = is.read(b)) > 0) {
                        os.write(b, 0, len);
                    }
                    os.flush();
                    os.close();
                    is.close();
                }
            }


        return null;
    }

    public ActionForward getImgFile(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String fjlj = request.getParameter("fjlj");
        fjlj = fjlj.replaceAll("!!","\\\\");
        fjlj=new String(fjlj.getBytes("ISO8859-1"),"UTF-8");
        String filename=fjlj.substring(fjlj.lastIndexOf("\\")+1,fjlj.lastIndexOf("."));
        String fileQm = fjlj.substring(fjlj.lastIndexOf("\\")+1);
        if(StringUtils.isNotBlank(fjlj)){

                InputStream is;
                try {
                    is = new FileInputStream(new File(basePath + File.separator + fileQm));
                    //����ļ������ڣ�ȥϵͳ�û�Ĭ��·���²���
                } catch (FileNotFoundException e) {

                    if(!StringUtils.equals(System.getProperty("user.home") +File.separator + PRIFEX_ZF, basePath)){
                        is = new FileInputStream(new File(System.getProperty("user.home") +File.separator + PRIFEX_ZF + File.separator + fileQm));
                    }else{
                        throw new Exception(e);
                    }

                }

                byte[] b = new byte[2048];

                response.setHeader("Content-Disposition", "attachment;filename="
                        + encodingFileName(filename));
//				response.setContentType("image/jpeg");

                OutputStream os = response.getOutputStream();
                int len;
                while ((len = is.read(b)) > 0) {
                    os.write(b, 0, len);
                }
                os.flush();
                os.close();
                is.close();
            }

        return null;
    }

    /**
     *
     * @����:response.setHeader()���������ļ������봦��
     * @���ߣ�taogj[���ţ�1075]
     * @���ڣ�2016-11-29 ����08:14:54
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param fileName
     * @return
     * @throws Exception
     * String ��������
     * @throws
     */
    private String encodingFileName(String fileName) throws Exception {

        String returnFileName = "";

        try {
            returnFileName = URLEncoder.encode(fileName, "UTF-8");
            returnFileName = StringUtils.replace(returnFileName, "+", "%20");
            if (returnFileName.length() > 150) {
                returnFileName = new String(fileName.getBytes("GB2312"), "ISO8859-1");
                returnFileName = StringUtils.replace(returnFileName, " ", "%20");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception(e);
        }

        return returnFileName;
    }

}



