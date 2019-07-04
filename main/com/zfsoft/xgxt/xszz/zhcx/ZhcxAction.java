package com.zfsoft.xgxt.xszz.zhcx;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2019/7/3.
 */
public class ZhcxAction extends SuperAction<ZhcxForm,ZhcxService> {


    public ActionForward zhcxList(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception{
        ZhcxForm model = (ZhcxForm)form;
        if(QUERY.equals(model.getType())){
            //���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);

            User user = getUser(request);

            //��ѯ��������б����ݣ���Ӧjson
            List<HashMap<String,String>> resultList = getService().getPageList(model,user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        //Ĭ�ϲ�ѯ����
        SearchModel searchModel=new SearchModel();
        searchModel.setSearch_tj_xn(new String[]{Base.currXn});
        request.setAttribute("searchTj", searchModel);
        String path = "xszz_new_zhcx.do";
        request.setAttribute("path",path);
        return mapping.findForward("zhcxList");
    }

    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ZhcxForm zhcxForm = (ZhcxForm)form;

        //���ݲ�ͬ��������� ȥ�Զ��嵼��
        ZhcxService zhcxService = new ZhcxService();
        //���ɸ߼���ѯ����
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        zhcxForm.setSearchModel(searchModel);
        User user = getUser(request);
        List<HashMap<String,String>> resultList = zhcxService.getAllList(zhcxForm,user);//��ѯ�����м�¼������ҳ


        //�������ܴ���
        IExportService exportService = new ExportExcelImpl();
        ExportModel exportModel = zhcxForm.getExportModel();
        exportModel.setZgh(user.getUserName());//��ǰ����Ա
        exportModel.setDataList(resultList);//��������
        exportModel.setDcclbh(request.getParameter("dcglbh"));//���õ�ǰ�������ܱ��
        File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
        FileUtil.outputExcel(response, file);
        return null;
    }
}
