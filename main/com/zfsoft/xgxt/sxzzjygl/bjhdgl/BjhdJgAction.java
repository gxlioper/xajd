package com.zfsoft.xgxt.sxzzjygl.bjhdgl;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.sxzzjygl.ztbhgl.ZtbhJgForm;
import com.zfsoft.xgxt.sxzzjygl.ztbhgl.ZtbhJgService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
/**
 *�༶����
 */
public class BjhdJgAction extends SuperAction<BjhdJgForm,BjhdJgService> {
    BjhdJgService service = new BjhdJgService();
    private static final String url = "sxzzjy_bjhdgl_bjhdjg.do";

    /**
     *����б�
     */
    @SystemAuth(url = url)
    public ActionForward bjhdJg(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        BjhdJgForm model = (BjhdJgForm) form;
        User user = getUser(request);
        if (QUERY.equalsIgnoreCase(model.getType())) {
            // ���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            // ��ѯ
            List<HashMap<String, String>> resultList = service.getPageList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        //���û�����û�ҳ��
        request.setAttribute("path", url);
        request.setAttribute("userType", user.getUserStatus());
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("bjhdJg");
    }

    /**
     *������ҳ��
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward bjhdJgAdd(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("bjhdJgAdd");

    }

    /**
     *�������
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward bjhdJgSave(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BjhdJgForm model = (BjhdJgForm) form;
        User user = getUser(request);
        model.setLrr(user.getUserName());
        boolean result = service.bjhdJgSave(model);
        String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     *��ת����޸�ҳ��
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward bjhdJgEdit(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        BjhdJgForm model = (BjhdJgForm) form;
        BjhdJgForm myForm = service.getModel(model);
        if (myForm!=null) {
            BeanUtils.copyProperties(model,myForm);
        }
        String[] hdfzr =service.getHdfzr(model);
        request.setAttribute("hdfzr",hdfzr[0]);
        String[] bjmc =service.getBjmc(model);
        request.setAttribute("bjmc",bjmc[0]);
        return mapping.findForward("bjhdJgEdit");
    }


    /**
     *�޸Ľ������
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward bjhdJgSaveForEdit(ActionMapping mapping, ActionForm form,
                                           HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        BjhdJgForm model = (BjhdJgForm) form;
        User user = getUser(request);
        model.setLrr(user.getUserName());
        boolean result = service.runUpdateTrim(model);
        String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     *���ɾ��
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward bjhdJgDel(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception {

        String values = request.getParameter("values");
        if (!StringUtil.isNull(values)) {
            String[] ids = values.split(",");
            int num = service.runDelete(ids);
            boolean result = num > 0;
            String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
            response.getWriter().print(getJsonMessage(message));
        } else {
            throw new SystemException(MessageKey.SYS_DEL_NULL);
        }
        return null;
    }

    /**
     *�ϴ�����
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward  bjhdJgUpload(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        BjhdJgForm model = (BjhdJgForm) form;
        BjhdJgForm myForm = service.getModel(model);
        if (myForm!=null) {
            BeanUtils.copyProperties(model,myForm);
        }
        String[] hdfzr =service.getHdfzr(model);
        request.setAttribute("hdfzr",hdfzr[0]);
        String[] bjmc =service.getBjmc(model);
        request.setAttribute("bjmc",bjmc[0]);
        request.setAttribute("model",model);
        return mapping.findForward("bjhdJgUpload");
    }

    /**
     *�϶���Ʒ�༶�
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward rdjpbjhd(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BjhdJgForm model = (BjhdJgForm) form;
        BjhdJgForm myForm = service.getModel(model);
        if (myForm!=null) {
            BeanUtils.copyProperties(model,myForm);
        }
        request.setAttribute("sfjpSelect",model.getSfjp());
        return mapping.findForward("rdjpbjhd");

    }

    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BjhdJgForm model = (BjhdJgForm) form;

        //���ɸ߼���ѯ����
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);
        User user = getUser(request);
        List<HashMap<String,String>> resultList = service.getDCList(model,user);

        //�������ܴ���
        IExportService exportService = new ExportExcelImpl();
        ExportModel exportModel = model.getExportModel();
        exportModel.setZgh(user.getUserName());//��ǰ����Ա
        exportModel.setDataList(resultList);//��������
        exportModel.setDcclbh(request.getParameter("dcglbh"));//���õ�ǰ�������ܱ��
        File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
        FileUtil.outputExcel(response, file);
        return null;
    }


    /**
     *�����϶�
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward bjhdJgPlrd(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        return mapping.findForward("bjhdJgPlrd");
    }


    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward saveForPlRd(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        BjhdJgForm model = (BjhdJgForm) form;
        String sfjp = model.getSfjp();
        String[] jgids = model.getJgids();
        boolean result = false;
        for (int i = 0; i <jgids.length ; i++) {
            BjhdJgForm newBjhdJgForm = new BjhdJgForm();
            newBjhdJgForm.setJgid(jgids[i]);
            newBjhdJgForm.setSfjp(sfjp);
            result =service.runUpdate(newBjhdJgForm);
        }
        String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward getHdInfo(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        BjhdJgForm model = (BjhdJgForm) form;
        BjhdJgForm myForm = service.getModel(model);
        if (myForm!=null) {
            BeanUtils.copyProperties(model,myForm);
        }
        String[] hdfzr =service.getHdfzr(model);
        request.setAttribute("hdfzr",hdfzr[0]);
        String[] bjmc =service.getBjmc(model);
        request.setAttribute("bjmc",bjmc[0]);
        request.setAttribute("model",model);
        request.setAttribute("fj",myForm.getFj());
        return mapping.findForward("getHdInfo");
    }

}
