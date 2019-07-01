package com.zfsoft.xgxt.cxcy.tjbb;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.cxcy.bzsbwh.bzsbwhjg.BzsbwhjgForm;
import com.zfsoft.xgxt.cxcy.bzsbwh.bzsbwhjg.BzsbwhjgService;
import com.zfsoft.xgxt.cxcy.sbwh.jzsb.JzsbForm;
import com.zfsoft.xgxt.cxcy.sbwh.jzsb.JzsbService;
import com.zfsoft.xgxt.cxcy.sbwh.xmsb.XmsbForm;
import com.zfsoft.xgxt.cxcy.sbwh.xmsb.XmsbService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:创新创业-统计报表
 * @作者： lgx [工号:1553]
 * @时间： 2018-09-10 08:54
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class TjbbAction extends SuperAction<TjbbForm,TjbbService> {
    private TjbbService service = new TjbbService();
    private static final String url = "cxcy_tjbb_tjbb.do";
    private static SearchModel s = new SearchModel();

    public ActionForward getList(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TjbbForm model = (TjbbForm)form;
        if (QUERY.equalsIgnoreCase(model.getType())) {
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            s.setSearch_tj_xn(searchModel.getSearch_tj_xn());
            s.setSearch_tj_xq(searchModel.getSearch_tj_xq());
            s.setSearch_tj_kssj(searchModel.getSearch_tj_kssj());
            s.setSearch_tj_jssj(searchModel.getSearch_tj_jssj());

            User user = getUser(request);
            // 查询
            List<HashMap<String, String>> resultList = service.getPageList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        SearchModel searchModel=new SearchModel();
        searchModel.setSearch_tj_xn(new String[] { Base.currXn });
        searchModel.setSearch_tj_xq(new String[] { Base.currXq });
        request.setAttribute("searchTj", searchModel);
        request.setAttribute("path", url);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("tjbbList");
    }

    public ActionForward getBzList(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TjbbForm myForm = (TjbbForm)form;
        if (QUERY.equalsIgnoreCase(myForm.getType())) {
            // 生成高级查询对象
            BzsbwhjgForm model = new BzsbwhjgForm();
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            s.setInput_mhcx(searchModel.getInput_mhcx());
            BeanUtils.copyProperties(searchModel,s);
            searchModel.setPath("cxcy_bzsbwh_jg.do");
            model.setSearchModel(searchModel);
            User user = getUser(request);
            // 查询
            List<HashMap<String, String>> resultList = new BzsbwhjgService().getPageList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        s.setSearch_tj_xy(new String[]{myForm.getXydm()});
        request.setAttribute("path", "cxcy_tjbb.do?method=getBzList");
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("getBzList");
    }

    public ActionForward getJzList(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TjbbForm myForm = (TjbbForm)form;
        if (QUERY.equalsIgnoreCase(myForm.getType())) {
            // 生成高级查询对象
            JzsbForm model = new JzsbForm();
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            s.setInput_mhcx(searchModel.getInput_mhcx());
            BeanUtils.copyProperties(searchModel,s);
            searchModel.setPath("cxcy_sbwh_jzsb.do");
            model.setSearchModel(searchModel);
            User user = getUser(request);
            // 查询
            List<HashMap<String, String>> resultList = new JzsbService().getPageList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        s.setSearch_tj_xy(new String[]{myForm.getXydm()});
        request.setAttribute("path", "cxcy_tjbb.do?method=getJzList");
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("getJzList");
    }

    public ActionForward getXmList(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TjbbForm myForm = (TjbbForm)form;
        if (QUERY.equalsIgnoreCase(myForm.getType())) {
            // 生成高级查询对象
            XmsbForm model = new XmsbForm();
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            s.setInput_mhcx(searchModel.getInput_mhcx());
            BeanUtils.copyProperties(searchModel,s);
            searchModel.setPath("cxcy_sbwh_xmsb.do");
            model.setSearchModel(searchModel);
            User user = getUser(request);
            // 查询
            List<HashMap<String, String>> resultList = new XmsbService().getPageList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        s.setSearch_tj_xy(new String[]{myForm.getXydm()});
        request.setAttribute("path", "cxcy_tjbb.do?method=getXmList");
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("getXmList");
    }
    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        TjbbForm myForm = (TjbbForm) form;
        String type = myForm.getType();
        User user = getUser(request);
        List<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();
        ExportModel exportModel = new ExportModel();
        IExportService exportService = new ExportExcelImpl();
        // 生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        s.setInput_mhcx(searchModel.getInput_mhcx());
        BeanUtils.copyProperties(searchModel,s);
        if("bz".equals(type)){
            searchModel.setPath("cxcy_bzsbwh_jg.do");
            BzsbwhjgForm model = new BzsbwhjgForm();
            model.setSearchModel(searchModel);
            resultList = new BzsbwhjgService().getAllList(model,user);
            exportModel = model.getExportModel();
        }
        if("jz".equals(type)){
            searchModel.setPath("cxcy_sbwh_jzsb.do");
            JzsbForm model = new JzsbForm();
            model.setSearchModel(searchModel);
            resultList = new JzsbService().getAllList(model,user);
            exportModel = model.getExportModel();
        }
        if("xm".equals(type)){
            searchModel.setPath("cxcy_sbwh_xmsb.do");
            XmsbForm model = new XmsbForm();
            model.setSearchModel(searchModel);
            resultList = new XmsbService().getAllList(model,user);
            exportModel = model.getExportModel();
        }
        exportModel.setZgh(user.getUserName());// 当前操作员
        exportModel.setDataList(resultList);// 设置数据
        exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
        File file = exportService.getExportFile(exportModel);// 生成导出文件
        FileUtil.outputExcel(response, file);
        return null;
    }
}
