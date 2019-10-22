package com.zfsoft.xgxt.zhdj.xsdzbhdygl;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xsgzgl.gyjc.jcsd.CcrcService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;

public class JgdcyglAction extends SuperAction<JgdcyglForm, JgdcyglService> {
    private JgdcyglService service = new JgdcyglService();
    private static final String url = "zhdj_dzdy_jgdcygl.do";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private DzbglService dzbglService = new DzbglService();


    /**
     * //��ȡ��Ա�б�
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getCyList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        JgdcyglForm model = (JgdcyglForm) form;
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
        SearchModel searchModel = new SearchModel();
        searchModel.setSearch_tj_xn(new String[]{Base.currXn});
        searchModel.setSearch_tj_xq(new String[]{Base.currXq});
        request.setAttribute("searchTj", searchModel);
        request.setAttribute("path", url);
        request.setAttribute("userType", user.getUserStatus());
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("search");
    }


    /**
     * //��ת���ҳ��
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        JgdcyglForm model = (JgdcyglForm) form;
        //��֧��
        List<HashMap<String, String>> dzbList = service.getDzbList();
        request.setAttribute("dzbList", dzbList);
        /**
         * ����ά����Сֵ
         */
        request.setAttribute("minDate", GetTime.getTimeByFormat(DATE_FORMAT));
        return mapping.findForward("addPage");
    }


    /**
     * //��ȡѧ����Ϣ
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getJg(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        JgdcyglForm model = (JgdcyglForm) form;
        if (QUERY.equals(model.getType())) {
            // ���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);

            User user = getUser(request);
            // ��ѯ
            List<HashMap<String, String>> resultList = service.getJgList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("url", url);
        SearchModel searchModel = new SearchModel();
        searchModel.setSearch_tj_xn(new String[]{Base.currXn});
        searchModel.setSearch_tj_xq(new String[]{Base.currXq});
        request.setAttribute("searchTj", searchModel);
        request.setAttribute("path", "zhdj_dzdy_jgdcygl.do?method=getJg");

        return mapping.findForward("getJg");
    }


    /**
     * //������Ա��Ϣ
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveCy(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        JgdcyglForm model = (JgdcyglForm) form;
        boolean rs = service.saveCy(model);
        String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }


    /**
     * /ɾ����Ա��Ϣ
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward delCy(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                               HttpServletResponse response) throws Exception {

        JgdcyglForm model = (JgdcyglForm) form;
        boolean rs = service.delCy(model);
        String messageKey = rs ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }


    /**
     * //��ת�޸ĳ�Ա��Ϣ
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updateCy(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        JgdcyglForm model = (JgdcyglForm) form;
        
        List<HashMap<String, String>> cyList = service.getCy(model);
        HashMap<String, String> cyMap = cyList.get(0);
        List<HashMap<String, String>> dzbList = service.getDzbList();
        request.setAttribute("dzbList", dzbList);
        request.setAttribute("cyMap", cyMap);
        return mapping.findForward("update");
    }

    /**
     * //�޸ı����Ա��Ϣ
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updateSaveCy(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        JgdcyglForm model = (JgdcyglForm) form;
        boolean rs = service.updateSava(model);
        String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    public ActionForward exportData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        JgdcyglForm model = (JgdcyglForm) form;

        // ���ɸ߼���ѯ����
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);
        User user = getUser(request);
        List<HashMap<String, String>> resultList = service.getAllList(model, user);// ��ѯ�����м�¼������ҳ
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
