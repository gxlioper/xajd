package com.zfsoft.xgxt.hdgl.hdgltjcx;

import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.hdgl.jzjh.JzjhForm;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.BaseAction;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;

public class TjAction extends BaseAction{
    private static final String url = "hdgl_hdgl_sgybtj.do";
    TjService service = new TjService();

    public ActionForward tjList(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws Exception {

        TjForm t = (TjForm)form;
        if(QUERY.equalsIgnoreCase(t.getType())){
            User user = getUser(request);

            //���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            t.setSearchModel(searchModel);
            List<HashMap<String,String>> resultList = service.getPageList(t,user);

            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);

            return null;
        }
        request.setAttribute("path", url);
        FormModleCommon.commonRequestSet(request);
        SearchModel searchModel=new SearchModel();
        request.setAttribute("searchTj", searchModel);
        return mapping.findForward("tjcx");
    }

    /**
     * @description	�� ����
     * @author 		�� CP��1352��
     * @date 		��2018-3-13 ����04:40:10
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TjForm t=(TjForm) form;
        User user = getUser(request);
        //���ɸ߼���ѯ����
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        t.setSearchModel(searchModel);
        List<HashMap<String,String>> resultList = service.getAllList(t,user);//��ѯ�����м�¼������ҳ
        //�������ܴ���
        IExportService exportService = new ExportExcelImpl();
        ExportModel exportModel = t.getExportModel();
        exportModel.setZgh(user.getUserName());//��ǰ����Ա
        exportModel.setDataList(resultList);//��������
        exportModel.setDcclbh(request.getParameter("dcclbh"));//���õ�ǰ�������ܱ��
        File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
        FileUtil.outputExcel(response, file);
        return null;
    }
}
