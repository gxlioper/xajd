package com.zfsoft.xgxt.szdw.fdyzyhfz.hjqk;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.szdw.fdyxx.FdyxxService;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * @������TODO
 * @���ߣ�WANCHEN
 * @���ڣ�
 */
public class HjqkAction extends SuperAction<HjqkForm,HjqkService> {

    private FdyxxService fdyxxService = new FdyxxService();

    public ActionForward hjqkList(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response) throws Exception {

        HjqkForm model = (HjqkForm) form;
        if(QUERY.equals(model.getType())){
            // ���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            // ��ѯ
            List<HashMap<String, String>> resultList = getService().getPageList(model,user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path","szdw_fdy_hjqk.do");
        return mapping.findForward("hjqkList");
    }

    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {

        HjqkForm model = (HjqkForm) form;
        User user = getUser(request);
        if(SAVE.equals(model.getType())){
            boolean f = getService().runInsert(model);
            String key = f ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(key));
            return null;
        }

        HashMap<String,String> data = fdyxxService.getFdyInfo1(user.getUserName());
        List<HashMap<String,String>> jldjList = getService().getJldjList();
        List<HashMap<String,String>> pmList = getService().getPmList();
        request.setAttribute("fdyxx",data);
        request.setAttribute("jldjList",jldjList);
        request.setAttribute("pmList",pmList);
        return mapping.findForward("add");
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws Exception {

        HjqkForm model = (HjqkForm) form;
        if(SAVE.equals(model.getType())){
            boolean f = getService().runUpdate(model);
            String key = f ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(key));
            return null;
        }
        HjqkForm result = getService().getModel(model);
        HashMap<String,String> data = fdyxxService.getFdyInfo1(result.getZgh());
        List<HashMap<String,String>> jldjList = getService().getJldjList();
        List<HashMap<String,String>> pmList = getService().getPmList();
        request.setAttribute("fdyxx",data);
        request.setAttribute("jldjList",jldjList);
        request.setAttribute("pmList",pmList);
        request.setAttribute("filepath",result.getFilepath());
        BeanUtils.copyProperties(model,result);
        return mapping.findForward("update");
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response) throws Exception {

        HjqkForm model = (HjqkForm) form;

        HjqkForm result = getService().getModel(model);
        HashMap<String,String> data = fdyxxService.getFdyInfo1(result.getZgh());
        request.setAttribute("fdyxx",data);
        request.setAttribute("model",result);
        return mapping.findForward("view");
    }

    public ActionForward del(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ids = request.getParameter("values");
        int i = getService().runDelete(ids.split(","));
        String msg = i>0 ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,i) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
        response.getWriter().print(getJsonMessage(msg));
        return null;
    }

    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HjqkForm model = (HjqkForm) form;

        //���ɸ߼���ѯ����
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);
        User user = getUser(request);
        List<HashMap<String,String>> resultList = getService().getAllList(model,user);

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
