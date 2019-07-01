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
 * @类功能描述:预览
 * @作者： lgx [工号:1553]
 * @时间： 2018-07-05 09:12
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class YlAction extends SuperAction {
    private Log logger = LogFactory.getLog(YlAction.class);

    private static ResourceBundle resource = ResourceBundle.getBundle("config/fileUploadConfig");

    private String basePath = null;

    private static final String PRIFEX_ZF = "ZFXG_UPLOADFILES";
    private static final String CONVERT = "convert_pdf";
    /**
     * @描述 ：TODO描述下当前构造方法
     */
    public YlAction() {
        super();
        try {
            basePath = resource.getString("filesys.local.dir");
            //如果没有给定文件存储路径，就默认给系统用户目录
            //Windows 用户目录：
            if(StringUtils.isBlank(basePath)){
                basePath = System.getProperty("user.home") +File.separator + PRIFEX_ZF;
            }
            logger.info("上传附件路径:---" + basePath + "---");
        } catch (Exception e) {
            e.printStackTrace();
            basePath = System.getProperty("user.home") +File.separator + PRIFEX_ZF;
            logger.info("上传附件路径:---" + basePath + "---");
        }

    }
    /**
     *  预览.
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
            //需要判断ext是否支持预览
            if(resource.containsKey(ext)){
                String cvt = resource.getString(ext);
                request.setAttribute("fjlj",fjlj);
                request.setAttribute("fjmc",fjmc);
                if("pdf".equals(cvt)){
                    return mapping.findForward("previewPdf");
                }else{
                    //其他各种图片格式
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
                    //无需转换
                    try {
                        is = new FileInputStream(new File(basePath + File.separator + filename));
                        //如果文件不存在，去系统用户默认路径下查找
                    } catch (FileNotFoundException e) {

                        if(!StringUtils.equals(System.getProperty("user.home") +File.separator + PRIFEX_ZF, basePath)){
                            is = new FileInputStream(new File(System.getProperty("user.home") +File.separator + PRIFEX_ZF + File.separator +filename));
                        }else{
                            throw new Exception(e);
                        }

                    }

                }else if("pdf".equals(cvt)){
                    //需要转换为pdf
                    File baseDir = new File(basePath);
                    if(!baseDir.exists()){
                        if(!StringUtils.equals(System.getProperty("user.home") +File.separator + PRIFEX_ZF, basePath)){
                            baseDir = new File(System.getProperty("user.home") +File.separator + PRIFEX_ZF );
                        }else{
                            throw new Exception("文件路径不存在");
                        }
                    }
                    File file = new File(baseDir.getAbsolutePath(),fileQm);
                    File pdfFile = new File(baseDir.getAbsolutePath()+File.separator+CONVERT, filename+".pdf");
                    if(!pdfFile.getParentFile().exists()){
                        pdfFile.getParentFile().mkdir();
                    }else {
                        //转换前判断 转换文件夹文件数是否已满
                        String convertCacheNum = resource.getString("convertCacheNum");
                        if(StringUtils.isNotBlank(convertCacheNum)){
                            int n = Integer.parseInt(convertCacheNum);
                            if(pdfFile.getParentFile().listFiles().length > n){
                                FileUtil.delAllFile(pdfFile.getParent());
                            }
                        }
                    }
                    //转换操作
                    if(!pdfFile.exists()&&file.exists()){
                        //Linux服务器需要officeHome值不能为空，必须在配置文件中配置
                        //Windows服务器officeHome值为空即可，不必在配置文件中配置该项
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
                    //如果文件不存在，去系统用户默认路径下查找
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
     * @描述:response.setHeader()下载中文文件名乱码处理
     * @作者：taogj[工号：1075]
     * @日期：2016-11-29 下午08:14:54
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param fileName
     * @return
     * @throws Exception
     * String 返回类型
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



