package com.zfsoft.xgxt.xyfd.fdkcwh.fdkcjg;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xyfd.fdkcwh.fdkcsq.FdkcsqForm;
import com.zfsoft.xgxt.xyfd.fdkcwh.fdkcsq.FdkcsqService;
import com.zfsoft.xgxt.xyfd.fdswh.FdsForm;
import com.zfsoft.xgxt.xyfd.fdswh.FdsService;
import common.newp.StringUtil;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * @�๦������:�����γ̽��ά��
 * @���ߣ� llf [����:1754]
 * @ʱ�䣺 2019-08-01 14:36
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class FdkcjgAction extends SuperAction<FdkcjgForm, FdkcjgService> {
    private FdkcjgService service = new FdkcjgService();
    private FdkcsqService fdkcsqService = new FdkcsqService();
    private FdsService fdsService = new FdsService();

    private static final String url = "xyfd_xyfd_fdkcjg.do";

    /**
     * ����б�
     */
    @SystemAuth(url = url)
    public ActionForward fdkcjgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FdkcjgForm model = (FdkcjgForm) form;
        if (QUERY.equalsIgnoreCase(model.getType())) {
            // ���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            // ��ѯ
            List<HashMap<String, String>> resultList = service.getPageList(model, user);

            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        String path = "xyfd_xyfd_fdkcjg.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("fdkcjgList");
    }
    /**
     * �������
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward addFdkcjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FdkcjgForm model = (FdkcjgForm) form;
        User user = getUser(request);
        FdkcsqForm fdkcsqForm = new FdkcsqForm();
        if ("stu".equals(user.getUserType())) {
            throw new Exception("û��Ȩ��");
        }
        boolean isAdmin = fdkcsqService.isAdmin(user);
        request.setAttribute("isAdmin",isAdmin);
        String path = "xyfd_fdkcjg.do?method=addFdkcjg";
        request.setAttribute("path", path);
        return mapping.findForward("addFdkcjg");
    }

    /**
     * �������
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FdkcjgForm model = (FdkcjgForm) form;
        User user = getUser(request);
        boolean result = false;
        model.setMon(org.apache.commons.lang.StringUtils.join(request.getParameterValues("mond"),","));
        model.setTues(org.apache.commons.lang.StringUtils.join(request.getParameterValues("tuesd"),","));
        model.setWed(org.apache.commons.lang.StringUtils.join(request.getParameterValues("wedd"),","));
        model.setThur(org.apache.commons.lang.StringUtils.join(request.getParameterValues("thurd"),","));
        model.setFri(org.apache.commons.lang.StringUtils.join(request.getParameterValues("frid"),","));
        model.setSat(org.apache.commons.lang.StringUtils.join(request.getParameterValues("satd"),","));
        model.setSun(org.apache.commons.lang.StringUtils.join(request.getParameterValues("sund"),","));
        if("add".equals(model.getType())){

            model.setLrr(user.getUserName());
            model.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
            model.setSjly("0");
            result = service.runInsert(model);
        }else{
            result = service.runUpdate(model);
        }
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
                : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }
    /**
     * ����޸�
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward updateFdkcjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FdkcjgForm model = (FdkcjgForm) form;
        model = service.getModel(model);
        request.setAttribute("model",model);//�����γ���Ϣ
        FdkcsqForm fdkcsqForm = new FdkcsqForm();
        BeanUtils.copyProperties(fdkcsqForm,model);
        StringBuilder fdjs = new StringBuilder();
        if(!StringUtil.isNull(fdkcsqForm.getFdjs())&&fdkcsqForm.getFdjs().startsWith("JS")){//��ʦ�ǼǺ�
            String xm = fdkcsqService.getFdjs(fdkcsqForm);
            fdjs.append(fdkcsqForm.getFdjs() + "��" + xm);
        }else if(!StringUtil.isNull(fdkcsqForm.getFdjs())&&fdkcsqForm.getFdjs().startsWith("PB")){//��־Ը�ߵǼǺ�
            String xm = fdkcsqService.getFdjs(fdkcsqForm);
            fdjs.append(fdkcsqForm.getFdjs() + "��" + xm);
        }else {
            throw new Exception("�ǼǺŲ�����");
        }
        request.setAttribute("fdjsxm",fdjs.toString());
        User user = getUser(request);
        boolean isAdmin = fdkcsqService.isAdmin(user);
        request.setAttribute("isAdmin",isAdmin);
        HashMap<String,String> fdjsxx = fdkcsqService.getFdjsxx(fdkcsqForm);//������ʦ����־Ը�ߣ���Ϣ
        request.setAttribute("fdjsxx",fdjsxx);

        return mapping.findForward("updateFdkcjg");
    }
    /**
     * ���ɾ��
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward del(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //���id
        String values = request.getParameter("values");
        if (!StringUtil.isNull(values)) {
            String[] ids = values.split(",");
            int num = service.runDelete(ids);
            boolean result = num > 0;
            String message = result ? MessageUtil.getText(
                    MessageKey.SYS_DEL_NUM, num) : MessageUtil
                    .getText(MessageKey.SYS_DEL_FAIL);
            response.getWriter().print(getJsonMessage(message));
        } else {
            throw new SystemException(MessageKey.SYS_DEL_NULL);
        }
        return null;
    }
    /**
     * ����鿴
     */
    @SystemAuth(url = url)
    public ActionForward viewFdkcjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FdkcjgForm model = (FdkcjgForm)form;
        model = service.getModel(model);
        FdkcsqForm pbsqForm = new FdkcsqForm();

        request.setAttribute("rs", StringUtils.formatData(model));
        return mapping.findForward("viewFdkcjg");
    }
    /**
     * ��־Ը�߽������
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        FdkcjgForm model = (FdkcjgForm) form;
        // ���ɸ߼���ѯ����
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);
        User user = getUser(request);
        // ��ѯ
        List<HashMap<String, String>> resultList = service.getAllList(model,user);// ��ѯ�����м�¼������ҳ
        // �������ܴ���
        IExportService exportService = new ExportExcelImpl();
        ExportModel exportModel = model.getExportModel();
        exportModel.setZgh(user.getUserName());// ��ǰ����Ա
        exportModel.setDataList(resultList);// ��������
        exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
        File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
        FileUtil.outputExcel(response, file);
        return null;
    }

}
