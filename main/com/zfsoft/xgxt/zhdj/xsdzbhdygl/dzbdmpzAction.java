package com.zfsoft.xgxt.zhdj.xsdzbhdygl;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;

public class dzbdmpzAction extends SuperAction<dzbdmpzForm, dzbdmpzService> {
    private dzbdmpzService service = new dzbdmpzService();
    private static final String url = "zhdj_dzdy_dmpz.do";

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception ActionForward ��������
     * @throws
     * @����: ��ȡ��֧�������б�
     * @���ߣ������
     * @���ڣ�2018-6-4 ����11:55:56
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */

    public ActionForward getDmpzList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        dzbdmpzForm model = (dzbdmpzForm) form;
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
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("search");
    }


    /**
     * ��ת��֧�������ҳ��
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addDmpz(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        return mapping.findForward("addPage");
    }


    public ActionForward getXy(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        dzbdmpzForm myForm = (dzbdmpzForm) form;
        User user = getUser(request);
        if (QUERY.equals(myForm.getType())) {
            // ���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            myForm.setSearchModel(searchModel);
            // ��ѯ
            List<HashMap<String, String>> resultList = service.getXyList(myForm, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        // Ĭ�ϸ߼���ѯ����
        SearchModel searchModel = new SearchModel();
        searchModel.setSearch_tj_xn(new String[]{Base.currXn});
        searchModel.setSearch_tj_xq(new String[]{Base.currXq});
        request.setAttribute("searchTj", searchModel);
        request.setAttribute("path", "zhdj_dzdy_dmpz.do?method=getXy");
        request.setAttribute("userType", user.getUserStatus());
        request.setAttribute("xydm", user.getUserDep());
        request.setAttribute("url", url);
        return mapping.findForward("getXy");
    }

    /**
     * ������֧������
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveDm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        dzbdmpzForm model = (dzbdmpzForm) form;
        String xydm = request.getParameter("xydm");
        model.setXydm(xydm);
        boolean rs = service.saveDm(model);
        String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     * ��ת��֧�����޸�ҳ��
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {//�ж�Ҫɾ�����ճ��Ƿ��������

        dzbdmpzForm model = (dzbdmpzForm) form;
        dzbdmpzForm myForm = service.getModel(model);
        List<HashMap<String, String>> resultList = service.getXymc(myForm);
        String symc = resultList.get(0).get("symc");
        request.setAttribute("symc", symc);
        if (myForm != null) {
            BeanUtils.copyProperties(model, myForm);

        } else {
            request.setAttribute("message", "��ǰѡ���޸ĵĴ��뱻�����û�ɾ����");
            return mapping.findForward("error");
        }
        request.setAttribute("dm", myForm.getDm());

        return mapping.findForward("update");
    }

    /**
     * �޸ĵ�֧������
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updateSavaDm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        dzbdmpzForm model = (dzbdmpzForm) form;
        String xydm = request.getParameter("xydm");
        model.setXydm(xydm);
        boolean rs = service.updateSava(model);
        String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     * ɾ����֧������
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward delDm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                               HttpServletResponse response) throws Exception {//�ж�Ҫɾ�����ճ��Ƿ��������

        dzbdmpzForm model = (dzbdmpzForm) form;
        //���ô����Ƿ�ʹ��
        boolean rs = service.delDm(model);
        String messageKey = rs ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    public ActionForward getSy(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        dzbdmpzForm myForm = (dzbdmpzForm) form;
        User user = getUser(request);
        if (QUERY.equals(myForm.getType())) {
            // ���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            myForm.setSearchModel(searchModel);
            // ��ѯ
            List<HashMap<String, String>> resultList = service.getSyList(myForm, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        // Ĭ�ϸ߼���ѯ����
        SearchModel searchModel = new SearchModel();
        searchModel.setSearch_tj_xn(new String[]{Base.currXn});
        searchModel.setSearch_tj_xq(new String[]{Base.currXq});
        request.setAttribute("searchTj", searchModel);
        request.setAttribute("path", "zhdj_dzdy_dmpz.do?method=getSy");
        request.setAttribute("userType", user.getUserStatus());
        request.setAttribute("sydm", user.getUserDep());
        request.setAttribute("url", url);
        return mapping.findForward("getSy");
    }

    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        dzbdmpzForm model = (dzbdmpzForm) form;

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

    /**
     * ��* @description: TODO ����֧����ҳ��
     *
     * @param mapping
     * @param form    ��* @return
     *                ��* @author Wang ChenHui
     *                ��* @date 2019/5/21 14:31
     */
    public ActionForward hjDzz(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        dzbdmpzForm model = (dzbdmpzForm) form;
        dzbdmpzForm myForm = service.getModel(model);
        //��ȡ����֧�б�����
        List<HashMap<String, String>> list = service.getDzzList(model);
        HashMap<String, String> map = list.get(0);
        request.setAttribute("dzzid", map.get("dm"));
        request.setAttribute("dzzmc", map.get("mc"));
        request.setAttribute("symc", map.get("symc"));
        request.setAttribute("clsj", map.get("clsj"));
        request.setAttribute("hjsj", map.get("hjsj"));
        request.setAttribute("model", list);
        List<HashMap<String, String>> dzzlist = service.getDzzDe(model.getDm());
        request.setAttribute("dzzList", dzzlist);
        if (myForm != null) {
            BeanUtils.copyProperties(model, myForm);
        } else {
            request.setAttribute("message", "��ǰѡ���޸ĵĴ��뱻�����û�ɾ����");
            return mapping.findForward("error");
        }
        return mapping.findForward("hjDzz");
    }

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @description: TODO ����֧����
     * @author Wang ChenHui
     * @date 2019/5/21 15:39
     */
    public ActionForward hjAddDzz(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        dzbdmpzForm model = (dzbdmpzForm) form;
        boolean rs = false;
        rs = service.hjDzz(model);
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
     * @description: TODO ���쵳֧����Ϣ
     * @author Wang ChenHui
     * @date 2019/5/23 16:26
     */
    public ActionForward ljDzz(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        dzbdmpzForm model = (dzbdmpzForm) form;
        dzbdmpzForm myForm = service.getModel(model);
        //��ȡ����֧�б�����
        List<HashMap<String, String>> list = service.getDzzList(model);
        HashMap<String, String> map = list.get(0);
        request.setAttribute("dzzid", map.get("dm"));
        request.setAttribute("dzzmc", map.get("mc"));
        request.setAttribute("symc", map.get("symc"));
        request.setAttribute("clsj", map.get("clsj"));
        List<HashMap<String, String>> dzzlist = service.getljDzz(model.getDm());
        request.setAttribute("dzzList", dzzlist);
        if (myForm != null) {
            BeanUtils.copyProperties(model, myForm);
        } else {
            request.setAttribute("message", "��ǰѡ���޸ĵĴ��뱻�����û�ɾ����");
            return mapping.findForward("error");
        }
        return mapping.findForward("ljDzz");
    }
}
