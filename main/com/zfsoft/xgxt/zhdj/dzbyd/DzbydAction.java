package com.zfsoft.xgxt.zhdj.dzbyd;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import net.sf.json.JSONArray;
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

public class DzbydAction extends SuperAction<DzbydForm, DzbydService> {

    private DzbydService service = new DzbydService();
    private static final String url = "zhdj_dzdy_dzbyd.do";
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @description: TODO ��֧����Ա�춯�б�
     * @author Wang ChenHui
     * @date 2019/5/22 14:21
     */
    public ActionForward getDzbydList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        DzbydForm model = (DzbydForm) form;
        User user = getUser(request);
        if (QUERY.equalsIgnoreCase(model.getType())) {
            // ��ѯ
            List<HashMap<String, String>> resultList = service.getPageList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        //���û�����û�ҳ��
        request.setAttribute("userType", user.getUserStatus());
        request.setAttribute("path", url);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("search");
    }

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @description: TODO ��֧���춯����ҳ��
     * @author Wang ChenHui
     * @date 2019/5/22 20:17
     */
    public ActionForward getDzbydInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        DzbydForm model = (DzbydForm) form;
        User user = getUser(request);
        HashMap<String, String> dzbydInfo = service.getDzbydInfo(model);
        request.setAttribute("dzbydInfo", dzbydInfo);
        return mapping.findForward("dzbydCk");
    }

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @description: TODO �޸ĵ�֧���춯ҳ��
     * @author Wang ChenHui
     * @date 2019/5/23 10:15
     */
    public ActionForward updateDzbyd(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        DzbydForm model = (DzbydForm) form;
        User user = getUser(request);
        HashMap<String, String> dzbydInfo = service.getDzbydInfo(model);
        request.setAttribute("dzbydInfo", dzbydInfo);
        //ѧ����֧����Ϣ
        List<HashMap<String, String>> dzbList = service.getXsDzb(model);
        request.setAttribute("dzbList",dzbList);
        return mapping.findForward("dzbydUpdate");
    }

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @description: TODO ���浳֧������
     * @author Wang ChenHui
     * @date 2019/5/23 10:24
     */
    public ActionForward bc(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
        DzbydForm model = (DzbydForm) form;
        boolean rs = service.bc(model);
        String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @description: TODO ɾ���춯���
     * @author Wang ChenHui
     * @date 2019/5/23 10:41
     */
    public ActionForward del(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        DzbydForm model = (DzbydForm) form;
        String values = request.getParameter("values");
        if (!StringUtil.isNull(values)) {
            boolean result = service.deleteYd(values.split(","));
            String messageKey = result ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
            response.getWriter().print(getJsonMessageByKey(messageKey));
        } else {
            throw new SystemException(MessageKey.SYS_DEL_NULL);
        }
        return null;
    }

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @description: TODO �����춯���
     * @author Wang ChenHui
     * @date 2019/5/23 10:42
     */
    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        DzbydForm model = (DzbydForm) form;

        //���ɸ߼���ѯ����
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);
        User user = getUser(request);
        List<HashMap<String, String>> resultList = service.getDCList(model, user);

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
}
