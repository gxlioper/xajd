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
import xgxt.utils.GetTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;

public class DzbglAction extends SuperAction<DzbglForm, DzbglService> {
    private DzbglService service = new DzbglService();
    private static final String url = "zhdj_dzdy_dzbgl.do";
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * ��ȡ��֧���б�
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getDzbList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        DzbglForm model = (DzbglForm) form;
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
        // Ĭ�ϸ߼���ѯ����
        SearchModel searchModel = new SearchModel();
        request.setAttribute("searchTj", searchModel);
        request.setAttribute("path", url);
        request.setAttribute("userType", user.getUserStatus());
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("search");
    }

    /**
     * ��ת���ҳ��
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
        DzbglForm model = (DzbglForm) form;
        if (QUERY.equals(model.getType())) {
            //��������ѧ������
            List<HashMap<String, String>> jcdwList = service.getJcdw();
            JSONArray dataList = JSONArray.fromObject(jcdwList);
            response.getWriter().print(dataList);
            return null;
        }
        /**
         * ����ά����Сֵ
         */
        List<HashMap<String, String>> jgdzbList = service.getJgdzbList();
        request.setAttribute("jgdzbList", jgdzbList);
        String jgcount = service.getJgCount();
        request.setAttribute("jgcount", jgcount);
        List<HashMap<String, String>> xsdzbList = service.getXsdzbList();
        request.setAttribute("xsdzbList", xsdzbList);
        String xscount = service.getXsCount();
        request.setAttribute("xscount", xscount);
        request.setAttribute("minDate", GetTime.getTimeByFormat(DATE_FORMAT));
        return mapping.findForward("addPage");
    }

    /**
     * ��ȡ��ʦ��Ϣ�б�
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getZg(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        DzbglForm myForm = (DzbglForm) form;
        String aaa = request.getParameter("aaa");
        if (QUERY.equals(myForm.getType())) {
            // ���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            myForm.setSearchModel(searchModel);

            User user = getUser(request);
            // ��ѯ
            List<HashMap<String, String>> resultList = service.getZgList(myForm, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        // Ĭ�ϸ߼���ѯ����
        SearchModel searchModel = new SearchModel();
        searchModel.setSearch_tj_xn(new String[]{Base.currXn});
        searchModel.setSearch_tj_xq(new String[]{Base.currXq});
        request.setAttribute("searchTj", searchModel);
        request.setAttribute("path", "zhdj_dzdy_dzbgl.do?method=getZg");
        request.setAttribute("zwlx", myForm.getZwlx());
        request.setAttribute("url", url);
        request.setAttribute("aaa", aaa);
        return mapping.findForward("getZg");
    }


    /**
     * ������֧����Ϣ
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveDzb(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        DzbglForm model = (DzbglForm) form;
        boolean rs = service.saveDzb(model);
        String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     * ��ת��֧���޸�ҳ��
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updateDzb(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        DzbglForm model = (DzbglForm) form;
        DzbglForm myForm = service.getModel(model);
        List<HashMap<String, String>> dzbList = service.getDzb(model);
        HashMap<String, String> map = dzbList.get(0);
        request.setAttribute("dzbmc", myForm.getDzbmc());
        request.setAttribute("dzbdm", map.get("xx"));
        request.setAttribute("clsj", myForm.getClsj());
        request.setAttribute("dzbid", myForm.getDzbid());
        request.setAttribute("dzbhjid", myForm.getDzbhjid());
        request.setAttribute("hjsj", myForm.getHjsj());
        request.setAttribute("symc", map.get("symc"));
        request.setAttribute("dzblx", map.get("dzblx"));
        request.setAttribute("jcdwList",service.getJcdw());

        List<HashMap<String, String>> jgdzbList = service.getJgdzbZwList(myForm);
        request.setAttribute("jgdzbList", jgdzbList);
        List<HashMap<String, String>> xsdzbList = service.getXsdzbZwList(myForm);
        request.setAttribute("xsdzbList", xsdzbList);
        String jgcount = service.getJgCount();
        request.setAttribute("jgcount", jgcount);
        String xscount = service.getXsCount();
        request.setAttribute("xscount", xscount);
        if (myForm != null) {
            BeanUtils.copyProperties(model, myForm);
        } else {
            request.setAttribute("message", "��ǰѡ���޸ĵĴ��뱻�����û�ɾ����");
            return mapping.findForward("error");
        }
        return mapping.findForward("update");
    }


    /**
     * �޸ı��浳֧��
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updateSaveDzb(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        DzbglForm model = (DzbglForm) form;
        String xydm = request.getParameter("xydm");
        model.setXydm(xydm);
        boolean rs = service.updateSava(model);
        String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }


    /**
     * ��֧��������Ϣҳ��
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward hjDzb(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        DzbglForm model = (DzbglForm) form;
        DzbglForm myForm = service.getModel(model);
        List<HashMap<String, String>> dzbList = service.getDzb(model);
        HashMap<String, String> map = dzbList.get(0);
        request.setAttribute("dzbmc", myForm.getDzbmc());
        request.setAttribute("dzbdm", map.get("xx"));
        request.setAttribute("jcdwdm", myForm.getJcdwdm());
        request.setAttribute("symc", map.get("symc"));
        request.setAttribute("clsj", myForm.getClsj());
        request.setAttribute("dzbid", myForm.getDzbid());
        request.setAttribute("hjsj", myForm.getHjsj());
        request.setAttribute("dzblx", map.get("dzblx"));
        request.setAttribute("dzbhjid", myForm.getDzbhjid());
        List<HashMap<String, String>> jgdzbList = service.getJgdzbZwList(myForm);
        request.setAttribute("jgdzbList", jgdzbList);
        List<HashMap<String, String>> xsdzbList = service.getXsdzbZwList(myForm);
        request.setAttribute("xsdzbList", xsdzbList);
        String jgcount = service.getJgCount();
        request.setAttribute("jgcount", jgcount);
        String xscount = service.getXsCount();
        request.setAttribute("xscount", xscount);
        if (myForm != null) {
            BeanUtils.copyProperties(model, myForm);
        } else {
            request.setAttribute("message", "��ǰѡ���޸ĵĴ��뱻�����û�ɾ����");
            return mapping.findForward("error");
        }
        return mapping.findForward("hjDzb");
    }


    /**
     * ��֧�����챣����Ϣ
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward hjAddDzb(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        DzbglForm model = (DzbglForm) form;
        boolean rs = service.hjDzb(model);
        String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }


    /**
     * ��֧��������Ϣ�鿴
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward ljDzb(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        DzbglForm model = (DzbglForm) form;
        DzbglForm myForm = service.getModel(model);
        List<HashMap<String, String>> dzbList = service.getljDzbList(myForm);
        HashMap<String, String> map = dzbList.get(0);
        request.setAttribute("dzbmc", map.get("dzbmc"));
        request.setAttribute("clsj", map.get("clsj"));
        request.setAttribute("dzbList", dzbList);
        return mapping.findForward("ljDzb");
    }

    /**
     * ɾ����֧����Ϣ
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward delDzb(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {

        DzbglForm model = (DzbglForm) form;
        boolean rs = service.delCy(model);
        String messageKey = rs ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }


    /**
     * ��ȡ��֧����ϸ��Ϣ
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getDzbInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        DzbglForm model = (DzbglForm) form;
        User user = getUser(request);
        //��֧������Ϣ
        if (model.getDzbhjid() == null || model.getDzbhjid() == "") {
            List<HashMap<String, String>> noHjIdList = service.getInfoNoHjId(model);
            HashMap<String, String> noHjidMap = noHjIdList.get(0);
            model.setDzbhjid(noHjidMap.get("dzbhjid"));
            model.setDzbid(noHjidMap.get("dzbid"));

        }
        List<HashMap<String, String>> dzbInfoList = service.getdzbInfo(model);
        request.setAttribute("dzbInfo", dzbInfoList.get(0));
        // ��ѯ��֧��ְ����Ϣ
        String dzblx = dzbInfoList.get(0).get("dzblx");
        if (dzblx.equals("ѧ����֧��")) {
            List<HashMap<String, String>> dzbZwList = service.getDzbZwList(model, user);
            request.setAttribute("dzbZwList", dzbZwList);
            List<HashMap<String, String>> cyInfoList = service.getDzbCy(model, user);
            HashMap<String, String> rs = service.cjcyCk(cyInfoList);
            request.setAttribute("rs", rs);
            request.setAttribute("dzblx", dzblx);
            return mapping.findForward("dzbCk");
        } else if (dzblx.equals("�̹���֧��")) {
            List<HashMap<String, String>> dzbZwList = service.getDzbZwList(model, user);
            request.setAttribute("dzbZwList", dzbZwList);
            List<HashMap<String, String>> cyInfoList = service.getJgdzbCy(model, user);
            HashMap<String, String> rs = service.cjcyCk(cyInfoList);
            request.setAttribute("rs", rs);
            request.setAttribute("dzblx", dzblx);
            return mapping.findForward("dzbCk");
        }
        return null;
    }


    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        DzbglForm model = (DzbglForm) form;

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
     * //��ȡѧ����Ϣ
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getXx(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        DzbglForm model = (DzbglForm) form;
        if (QUERY.equals(model.getType())) {
            // ���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);

            User user = getUser(request);
            // ��ѯ
            List<HashMap<String, String>> resultList = service.getXxList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("url", url);
        SearchModel searchModel = new SearchModel();
        searchModel.setSearch_tj_xn(new String[]{Base.currXn});
        searchModel.setSearch_tj_xq(new String[]{Base.currXq});
        request.setAttribute("searchTj", searchModel);
        String bbb = request.getParameter("bbb");
        request.setAttribute("bbb", bbb);
        request.setAttribute("path", "zhdj_dzdy_dzbgl.do?method=getXx");

        return mapping.findForward("getXx");
    }
}
