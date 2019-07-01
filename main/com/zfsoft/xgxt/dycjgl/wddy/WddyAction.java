package com.zfsoft.xgxt.dycjgl.wddy;

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
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;

public class WddyAction extends SuperAction<WddyForm,WddyService> {
    private static final String url = "dycjgl_wddy_wddy.do";


    public ActionForward wddyList(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DycjglService service = new DycjglService();
        String path = "dycjgl_wddy_wddy.do";
        request.setAttribute("path", path);
        List<HashMap<String,String>> xmList = service.getXmList();
        request.setAttribute("xmList",xmList);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("wddyList");
    }

    public ActionForward wddyQuery(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        WddyForm model = (WddyForm) form;
        WddyService service = new WddyService();
        DycjglService dycjservice = new DycjglService();
        List<HashMap<String,String>> xmList = dycjservice.getXmList();
        User user = getUser(request);
        //查询结果集
        List<HashMap<String,String>> resultList = service.getWddyList(model,xmList,user);
        JSONArray dataList = JSONArray.fromObject(resultList);
        response.getWriter().print(dataList);
        return null;
    }

    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        WddyForm model = (WddyForm) form;
        WddyService service = new WddyService();
        DycjglService dycjservice = new DycjglService();
        List<HashMap<String,String>> xmList = dycjservice.getXmList();
        User user = getUser(request);
        //查询结果集
        List<HashMap<String,String>> resultList = service.getWddyNoPageList(model,xmList,user);
        //导出功能代码
        IExportService exportService = new ExportExcelImpl();
        ExportModel exportModel = model.getExportModel();
        exportModel.setZgh(user.getUserName());//当前操作员
        exportModel.setDataList(resultList);//设置数据
        exportModel.setDcclbh(request.getParameter("dcclbh"));//设置当前导出功能编号
        File file = exportService.getExportFile(exportModel);//生成导出文件
        FileUtil.outputExcel(response, file);
        return null;
    }






}
