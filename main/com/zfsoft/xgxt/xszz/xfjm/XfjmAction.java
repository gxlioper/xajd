package com.zfsoft.xgxt.xszz.xfjm;

import com.alibaba.fastjson.JSON;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������-ѧ�Ѽ���
 * @�๦������: ѧ�Ѽ���������˽��
 * @����: ��ˬ [����:1730]
 * @ʱ��: 2019/7/3 10:49
 */
public class XfjmAction extends SuperAction<XfjmForm,XfjmService> {

    private XfjmService xfjmService = XfjmService.getXfjmService();
    XsxxService xsxxService = new XsxxService();
    /**
     * @����: ��ҳ��ȡѧ�Ѽ����б�(����)
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/3 11:10
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param mapping
	* @param form
	* @param request
	* @param response 
     * @return org.apache.struts.action.ActionForward
     **/
    @SystemAuth(url="xszz_new_xfjmsq.do",rewritable = SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward getPageList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        XfjmForm model = (XfjmForm) form;
        String doType = request.getParameter("doType");
        String status = request.getParameter("status");
        if (QUERY.equalsIgnoreCase(doType)) {
            // ���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            // ��ѯ
            model.setStatus(status);
            List<HashMap<String, String>> resultList = xfjmService.getPageList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("status",status);
        request.setAttribute("path", "xszz_new_xfjm_sq.do");
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("sqList");
    }

    /**
     * @����: ѧ�Ѽ�������б�
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/18 15:32
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param mapping
	* @param form
	* @param request
	* @param response
     * @return org.apache.struts.action.ActionForward
     **/
    @SystemAuth(url="xszz_new_xfjmsh.do",rewritable = SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward getShPageList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        XfjmForm model = (XfjmForm) form;
        String doType = request.getParameter("doType");
        if (QUERY.equalsIgnoreCase(doType)) {
            // ���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            // ��ѯ
            List<HashMap<String, String>> resultList = xfjmService.getShPageList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path", "xszz_new_xfjmsh.do");
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("shList");
    }

    /**
     * @����: ѧ�Ѽ������б�
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/18 15:32
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param mapping
	* @param form
	* @param request
	* @param response
     * @return org.apache.struts.action.ActionForward
     **/
    @SystemAuth(url="xszz_new_xfjmjg.do",rewritable = SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward getJgPageList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        XfjmForm model = (XfjmForm) form;
        String doType = request.getParameter("doType");
        if (QUERY.equalsIgnoreCase(doType)) {
            // ���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            // ��ѯ
            List<HashMap<String, String>> resultList = xfjmService.getJgPageList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path", "xszz_new_xfjmjg.do");
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("jgList");
    }

    /**
     * @����: ѧ�Ѽ���������
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/18 15:32
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param mapping
	* @param form
	* @param request
	* @param response
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward exportData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        XfjmForm model = (XfjmForm) form;
        //���ɸ߼���ѯ����
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);
        User user = getUser(request);
        List<HashMap<String,String>> resultList = xfjmService.getJgList(model, user);//��ѯ�����м�¼������ҳ
        //�������ܴ���
        IExportService exportService = new ExportExcelImpl();
        ExportModel exportModel = model.getExportModel();
        exportModel.setZgh(user.getUserName());//��ǰ����Ա
        exportModel.setDataList(resultList);//��������
        exportModel.setDcclbh(request.getParameter("dcclbh"));//���õ�ǰ�������ܱ��
        File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
        FileUtil.outputExcel(response, file);
        return null;
    }

    /**
     * @����: ������Ϣ��ѯ
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/8 8:57
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param mapping
	 * @param form
	 * @param request
	 * @param response
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward xfjmSq(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        XfjmForm model = (XfjmForm) form;
        User user = getUser(request);
        String status = request.getParameter("status");
        String xh = request.getParameter("xh");
        if("stu".equals(user.getUserType())) {
            xh = user.getUserName();
        }
        String sqid = model.getId();
        HashMap<String,String> sqinfo = new HashMap<>();
        if(StringUtils.isNotNull(sqid)){
            //����id�������ȡ������Ϣ
            sqinfo = xfjmService.getById(sqid);
            request.setAttribute("sqinfo",sqinfo);
            request.setAttribute("shzt",sqinfo.get("shzt"));
            //��ȡרҵ������Ϣ
            HashMap<String,String> pmInfo = xfjmService.getZypmInfo(xh,sqinfo.get("xn"));
            request.setAttribute("pminfo",pmInfo);
        }else{
            //�����ʱ���ȡרҵ����Ĭ��Ϊ��ѧ��
            HashMap<String,String> pmInfo = xfjmService.getZypmInfo(xh,Base.currXn);
            request.setAttribute("pminfo",pmInfo);
        }
        //����ѧ��������Ϣ
        HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
        request.setAttribute("xsjbxx",xsjbxx);
        //�жϱ�ѧ���Ƿ����������϶������¼
        HashMap<String,String> knrsdjg = xfjmService.getKnsrdjg(xh, Base.currXn);
        if(knrsdjg != null && knrsdjg.containsKey("guid")){
            request.setAttribute("knsrdjg",knrsdjg);
            request.setAttribute("sfrdkns","1");
        }else{
            //�Ƿ��϶�������
            request.setAttribute("sfrdkns","0");
        }
        request.setAttribute("status",status);
        return mapping.findForward("xfjmsq");
    }

    /**
     * @����: ѧ�Ѽ������༭
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/18 15:33
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param mapping
	* @param form
	* @param request
	* @param response
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward xfjmJgEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        XfjmForm model = (XfjmForm) form;
        User user = getUser(request);
        String status = request.getParameter("status");
        String xh = request.getParameter("xh");
        String sqid = model.getId();
        HashMap<String,String> sqinfo = new HashMap<>();
        if(StringUtils.isNotNull(sqid)){
            //����id�������ȡ������Ϣ
            sqinfo = xfjmService.getById(sqid);
            request.setAttribute("sqinfo",sqinfo);
            request.setAttribute("shzt",sqinfo.get("shzt"));
        }else{
            request.setAttribute("sqinfo",sqinfo);
        }

        //����ѧ��������Ϣ
        if(StringUtils.isNotNull(xh)){
            HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
            request.setAttribute("xsjbxx",xsjbxx);
            //�жϱ�ѧ���Ƿ����������϶������¼
            HashMap<String,String> knrsdjg = xfjmService.getKnsrdjg(xh, Base.currXn);
            if(knrsdjg != null && knrsdjg.containsKey("guid")){
                request.setAttribute("knsrdjg",knrsdjg);
                request.setAttribute("sfrdkns","1");
            }else{
                //�Ƿ��϶�������
                request.setAttribute("sfrdkns","0");
            }
        }
        BdpzService bdpzService = new BdpzService();
        List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("knsrd");
        request.setAttribute("jbxxList", jbxxList);
        request.setAttribute("status",status);
        request.setAttribute("type",status);
        request.setAttribute("userType",user.getUserType());
        return mapping.findForward("xfjmjgAdd");
    }

    /**
     * @����: ѧ�Ѽ������
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/8 9:14
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param mapping
	 * @param form
	 * @param request
	 * @param response
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward xfjmSh(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        XfjmForm model = (XfjmForm) form;
        User user = getUser(request);
        String status = request.getParameter("status");
        String xh = request.getParameter("xh");
        String shid = request.getParameter("shid");
        String xtgwid = request.getParameter("xtgwid");
        String sqid = model.getId();
        HashMap<String,String> sqinfo = new HashMap<>();
        if(StringUtils.isNotNull(sqid)){
            //����id�������ȡ������Ϣ
            sqinfo = xfjmService.getById(sqid);
            request.setAttribute("sqinfo",sqinfo);
            request.setAttribute("shzt",sqinfo.get("shzt"));
            //��ȡרҵ������Ϣ
            HashMap<String,String> pmInfo = xfjmService.getZypmInfo(xh,sqinfo.get("xn"));
            request.setAttribute("pminfo",pmInfo);
        }
        //����ѧ��������Ϣ
        HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
        request.setAttribute("xsjbxx",xsjbxx);
        //�жϱ�ѧ���Ƿ����������϶������¼
        HashMap<String,String> knrsdjg = xfjmService.getKnsrdjg(xh, Base.currXn);
        if(knrsdjg != null && knrsdjg.containsKey("guid")){
            request.setAttribute("knsrdjg",knrsdjg);
            request.setAttribute("sfrdkns","1");
        }else{
            //�Ƿ��϶�������
            request.setAttribute("sfrdkns","0");
        }
        //�ж��Ƿ������һ�����
        if(sqinfo.containsKey("shlc")){
            String shlc = sqinfo.get("shlc");
            boolean islastShlc = xfjmService.isLastGw(shlc,xtgwid);
            request.setAttribute("islastshl",islastShlc);
        }
        request.setAttribute("xtgwid",xtgwid);
        request.setAttribute("shid",shid);
        request.setAttribute("status",status);
        return mapping.findForward("shEdit");
    }

    /**
     * @����: �����ύ
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/3 14:05
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param mapping
	* @param form
	* @param request
	* @param response 
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward sqSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        XfjmForm model = (XfjmForm) form;
        User user = getUser(request);
        //�����ύ
        Map<String,Object> result = xfjmService.sqSave(model,user);
        response.getWriter().print(JSON.parseObject(JSON.toJSONString(result)));
        return null;
    }
    /**
     * @����: ѧ�Ѽ�����ά��
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/9 20:36
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param mapping
	* @param form
	* @param request
	* @param response
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward jgSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        XfjmForm model = (XfjmForm) form;
        User user = getUser(request);
        //�����ύ
        Map<String,Object> result = xfjmService.jgSave(model,user);
        response.getWriter().print(JSON.parseObject(JSON.toJSONString(result)));
        return null;
    }
    
    /**
     * @����: ��ȡ�������϶���Ϣ
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/9 20:05
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param mapping
	* @param form
	* @param request
	* @param response 
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward getknsrdInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        String xh = request.getParameter("xh");
        HashMap<String, String> knsrdjg = xfjmService.getKnsrdjg(xh, Base.currXn);
        Map<String,Object> result = new HashMap<>();
        if (knsrdjg == null || StringUtils.isNull(knsrdjg.get("guid"))) {
            result.put("code",0);
            result.put("msg","���޼�¼");

        }else{
            result.put("code",1);
            result.put("msg","��ȡ�ɹ���");
            result.put("data",knsrdjg);
        }
        response.getWriter().print(JSON.parseObject(JSON.toJSONString(result)));
        return null;
    }

    /**
     * @����: ����idɾ��ѧ�Ѽ���������Ϣ��δ�ύ/�˻ؼ�¼��
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/3 19:57
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param mapping
	* @param form
	* @param request
	* @param response 
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward removeById(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        String sqid = request.getParameter("id");
        Map<String,Object> result = xfjmService.removeById(sqid);
        response.getWriter().print(JSON.parseObject(JSON.toJSONString(result)));
        return null;
    }

    /**
     * @����: ѧ�Ѽ������Ƴ�
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/18 15:33
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param mapping
	* @param form
	* @param request
	* @param response
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward removeJg(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        String sqid = request.getParameter("id");
        Map<String,Object> result = xfjmService.removeJg(sqid);
        response.getWriter().print(JSON.parseObject(JSON.toJSONString(result)));
        return null;
    }

    /**
     * @����: ����״̬�޸�(�������ύ)
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/3 20:06
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param mapping
	* @param form
	* @param request
	* @param response
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward sqztxg(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        String sqid = request.getParameter("id");
        String shzt = request.getParameter("shzt");
        Map<String,Object> result = xfjmService.sqztxg(sqid,shzt);
        response.getWriter().print(JSON.parseObject(JSON.toJSONString(result)));
        return null;
    }

    /**
     * @����: �����Ϣ����
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/18 15:34
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param mapping
	* @param form
	* @param request
	* @param response
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward shSave(ActionMapping mapping,ActionForm form,HttpServletRequest request,
                                HttpServletResponse response) throws Exception{
        XfjmForm model = (XfjmForm) form;
        User user = getUser(request);
        Map<String,Object> result = xfjmService.shSave(model,user);
        response.getWriter().print(JSON.parseObject(JSON.toJSONString(result)));
        return null;
    }


    /**
     * @����: ��������ѧ�Ѽ�����Ϣ
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/11 9:32
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param mapping
	* @param form
	* @param request
	* @param response 
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward importXfjmjg(ActionMapping mapping,ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String doType = request.getParameter("doType");
        if("import".equalsIgnoreCase(doType)){
            uploadFile(mapping, form, request, response);
            //��������
            String back = xfjmService.importXfjmjg(request,response);
            request.setAttribute("back", back);
            String sfdcExcel = (String)request.getAttribute("sfdcExcel");
            if(sfdcExcel != null && "yes".equals(sfdcExcel)){
                //return mapping.findForward("");
            }
        }
        return mapping.findForward("importXfjmjg");
    }


    private ActionForward uploadFile(ActionMapping mapping,ActionForm form,
                                     HttpServletRequest request,HttpServletResponse response)
            throws Exception{
        String dir = servlet.getServletContext().getRealPath("/upload");
        File file = new File(dir);
        if(!file.exists()){
            file.mkdir();
        }
        HttpSession session = request.getSession();
        String fName = (String)session.getAttribute("userName");
        String tabName = request.getParameter("tabName");
        Timestamp date = new Timestamp(System.currentTimeMillis());
        fName += date.toString().substring(0, 19);
        fName = fName.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
        XfjmForm data = (XfjmForm)form;
        FormFile dataFile = data.getFile();
        if(dataFile == null){
            return mapping.findForward("false");
        }
        int size = dataFile.getFileSize();
        InputStream is = dataFile.getInputStream();
        String filePath = dir +"/"+ fName+".xls";
        OutputStream os = new FileOutputStream(filePath);
        int bytesRead = 0;
        byte[] buffer = new byte[size];
        while ((bytesRead = is.read(buffer, 0, size)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        os.close();
        os.close();
        request.setAttribute("tabName", tabName);
        request.setAttribute("filePath", filePath);
        return mapping.findForward("success");
    }
}
