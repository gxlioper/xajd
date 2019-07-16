package com.zfsoft.xgxt.dtjs.zhcx;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:日常教育综合查询
 * @作者： llf [工号:1754]
 * @时间： 2019-07-15 14:36
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class ZhcxAction extends SuperAction<ZhcxForm,ZhcxService> {

    private static List<HashMap<String, String>> jbxxList = null;
    static {
        BdpzService bdpzService = new BdpzService();
        jbxxList = bdpzService.getJbxxpz("shsjjl");
    }
    public ActionForward rcjyList(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZhcxService service = new ZhcxService();
        ZhcxForm model = (ZhcxForm)form;
        if (QUERY.equals(model.getType())){
            //生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            //查询
            List<HashMap<String,String>> resultList = service.getPageList(model,user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        SearchModel searchModel = new SearchModel();
        searchModel.setSearch_tj_zm(new String[]{"01","02"});
        request.setAttribute("searchTj", searchModel);
        request.setAttribute("path","dtjs_rcjy_zhcx.do");
        return mapping.findForward("rcjyList");
    }
    /**
     *查看日常教育记录
     */
    public ActionForward rcjyView(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZhcxForm model = (ZhcxForm)form;
        ZhcxService service = new ZhcxService();
        if (StringUtils.isNotNull(model.getXh())){
            // 加载学生基本信息
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
            request.setAttribute("jbxx", xsjbxx);
            //加载理论学习记录
            List<HashMap<String,String>> llxxList = service.getllxxListByXh(model);
            request.setAttribute("llxxList",llxxList);
            //加载社会实践记录
            List<HashMap<String,String>> shsjList = service.getshsjListByXh(model);
            request.setAttribute("shsjList",shsjList);
            //加载志愿服务记录
            List<HashMap<String,String>> zyfwList = service.getzyfwListByXh(model);
            request.setAttribute("zyfwList",zyfwList);
        }else {
            throw new Exception();
        }
        // 学生基本信息显示配置
        request.setAttribute("jbxxList", jbxxList);

        return mapping.findForward("rcjyView");
    }

    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZhcxForm model = (ZhcxForm)form;
        ZhcxService service = new ZhcxService();
        //生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);
        User user = getUser(request);
        List<HashMap<String,String>> resultList = service.getAllList(model,user);

        //导出功能代码
        IExportService exportService = new ExportExcelImpl();
        ExportModel exportModel = model.getExportModel();
        exportModel.setZgh(user.getUserName());//当前操作员
        exportModel.setDataList(resultList);//设置数据
        exportModel.setDcclbh(request.getParameter("dcglbh"));//设置当前导出功能编号
        File file = exportService.getExportFile(exportModel);//生成导出文件
        FileUtil.outputExcel(response, file);
        return null;
    }
}
