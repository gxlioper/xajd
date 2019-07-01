package com.zfsoft.xgxt.dycjgl.sjcx;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.dycjgl.dycjwh.DycjglService;
import net.sf.json.JSONArray;
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

public class SjcxAction extends SuperAction<SjcxForm,SjcxService> {
    private static final String url = "dycjgl_sjcx_sjcx.do";


    public ActionForward sjcxList(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DycjglService service = new DycjglService();
        List<HashMap<String,String>> xmList = service.getXmList();
        request.setAttribute("xmList",xmList);
        String path = "dycjgl_sjcx_sjcx.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("sjcxList");
    }

    public ActionForward sjcxQuery(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        SjcxForm model = (SjcxForm) form;
        SjcxService service = new SjcxService();
        DycjglService dycjservice = new DycjglService();
        List<HashMap<String,String>> xmList = dycjservice.getXmList();
        User user = getUser(request);
        // ���ɸ߼���ѯ����
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);
        //��ѯ�����
        List<HashMap<String,String>> resultList = service.getSjcxList(model,xmList,user);
        JSONArray dataList = JSONArray.fromObject(resultList);
        response.getWriter().print(dataList);
        return null;
    }

    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        SjcxForm model = (SjcxForm) form;
        SjcxService service = new SjcxService();
        DycjglService dycjservice = new DycjglService();
        List<HashMap<String,String>> xmList = dycjservice.getXmList();
        User user = getUser(request);
        //��ѯ�����
        List<HashMap<String,String>> resultList = service.getSjcxNoPageList(model,xmList,user);
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

}
