package com.zfsoft.xgxt.cxcy.bzsbwh.bzsbwhjg;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;
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

/**
 * @类功能描述:创新创业补助上报结果
 * @作者： lgx [工号:1553]
 * @时间： 2018-09-06 10:58
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class BzsbwhjgAction extends SuperAction<BzsbwhjgForm,BzsbwhjgService> {
    private static final String url = "cxcy_bzsbwh_jg.do";
    private BzsbwhjgService service = new BzsbwhjgService();
    private static final String CXCY = "cxcy";
    private static List<HashMap<String, String>> jbxxList = null;
    static{
        BdpzService bdpzService = new BdpzService();
		/*学生基本信息显示配置*/
        jbxxList = bdpzService.getJbxxpz(CXCY);
    }

    public ActionForward getList(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BzsbwhjgForm model = (BzsbwhjgForm)form;
        if (QUERY.equalsIgnoreCase(model.getType())) {
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
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
        return mapping.findForward("bzsbwhjgList");
    }
    public ActionForward bzsbwhjgAdd(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BzsbwhjgForm model = (BzsbwhjgForm)form;
        User user = getUser(request);
        if ("stu".equals(user.getUserType())) {
            model.setXh(user.getUserName());
        }
        if (!StringUtil.isNull(model.getXh())) {
            // 加载学生基本信息
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
            request.setAttribute("jbxx", xsjbxx);
        }
        request.setAttribute("jbxxList", jbxxList);
        String path = "cxcy_bzsbwhjg.do?method=bzsbwhjgAdd";
        request.setAttribute("xn",Base.currXn);
        request.setAttribute("xq",Base.currXq);
        request.setAttribute("xqmc",Base.getXqmcForXqdm(Base.currXq));
        request.setAttribute("path", path);
        return mapping.findForward("bzsbwhjgAdd");
    }

    public ActionForward bzsbwhjgUpdate(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BzsbwhjgForm model = (BzsbwhjgForm)form;
        User user = getUser(request);
        HashMap<String,String> map = service.getInfoById(model.getJgid());
        // 加载学生基本信息
        XsxxService xsxxService = new XsxxService();
        HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(map.get("xh"));
        request.setAttribute("jbxx", xsjbxx);
        request.setAttribute("jbxxList", jbxxList);
        request.setAttribute("map", map);
        return mapping.findForward("bzsbwhjgUpdate");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BzsbwhjgForm model = (BzsbwhjgForm)form;
        User user = getUser(request);
        boolean rs = true;
        try {
            rs = service.save(model,user);
        }catch (SystemException e) {
            response.getWriter().print(getJsonMessage(e.getMessage()));
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    public ActionForward bzsbwhjgDel(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String values = request.getParameter("values");
        if (!StringUtil.isNull(values)) {
            String[] ids = values.split(",");
            int num = service.runDelete(ids);
            boolean result = num > 0;
            String message = result ? MessageUtil.getText(
                    MessageKey.SYS_DEL_NUM, num) : MessageUtil
                    .getText(MessageKey.SYS_DEL_FAIL);
            response.getWriter().print(getJsonMessage(message));
        } else {
            throw new SystemException(MessageKey.SYS_DEL_NULL);
        }
        return null;
    }

    public ActionForward bzsbwhjgView(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BzsbwhjgForm model = (BzsbwhjgForm) form;
        HashMap<String,String> map = service.getInfoById(model.getJgid());
        // 加载学生基本信息
        XsxxService xsxxService = new XsxxService();
        HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(map.get("xh"));
        request.setAttribute("jbxx", xsjbxx);
        request.setAttribute("jbxxList", jbxxList);
        request.setAttribute("map",map);
        return mapping.findForward("bzsbwhjgView");
    }


    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        BzsbwhjgForm model = (BzsbwhjgForm) form;
        // 生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);

        User user = getUser(request);
        // 查询
        List<HashMap<String, String>> resultList = service.getAllList(model,
                user);// 查询出所有记录，不分页
        // 导出功能代码
        IExportService exportService = new ExportExcelImpl();
        ExportModel exportModel = model.getExportModel();
        exportModel.setZgh(user.getUserName());// 当前操作员
        exportModel.setDataList(resultList);// 设置数据
        exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
        File file = exportService.getExportFile(exportModel);// 生成导出文件
        FileUtil.outputExcel(response, file);
        return null;
    }
}
