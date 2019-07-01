package com.zfsoft.xgxt.rcsw.xsgzqkbb.xsgzqkcgbb;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbsq.XsgzzbsqService;
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

/**
 * 安徽农业大学
 * 学生工作情况常规报表action.
 *
 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
 * @date 2018-04-13 15:12
 */
public class XsgzqkCgbbAction extends SuperAction<XsgzqkCgbbForm, XsgzqkCgbbService> {

    private XsgzqkCgbbService service = new XsgzqkCgbbService();
    private static final String url = "rcsw_xsgzqkbb_cgbb.do";

    /**
     *  转到常规报表列表页面.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-17 15:23
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    @SystemAuth(url = url)
    public ActionForward cgbbList(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // 默认高级查询条件
        SearchModel searchModel = new SearchModel();
        searchModel.setSearch_tj_xn(new String[] { Base.currXn });
        searchModel.setSearch_tj_xq(new String[] { Base.currXq });
        request.setAttribute("searchTj", searchModel);
        request.setAttribute("path", url);

        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("cgbbList");
    }

    /**
     *  获取常规列表json数据.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-17 15:25
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    @SystemAuth(url = url)
    public ActionForward getCgbbListData(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkCgbbForm xsgzqkCgbbForm = (XsgzqkCgbbForm) form;

        // 生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        xsgzqkCgbbForm.setSearchModel(searchModel);
        User user = getUser(request);
        // 查询
        List<HashMap<String, String>> resultList = service.getPageList(xsgzqkCgbbForm, user);
        JSONArray dataList = JSONArray.fromObject(resultList);
        response.getWriter().print(dataList);
        return null;
    }

    /**
     *  转到常规报表增加页面.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-18 16:09
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    @SystemAuth(url = url)
    public ActionForward cgbbAdd(ActionMapping mapping, ActionForm form,
                                         HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkCgbbForm xsgzqkCgbbForm = (XsgzqkCgbbForm) form;
        User user = getUser(request);

        xsgzqkCgbbForm.setBsr(user.getUserName());
        xsgzqkCgbbForm.setBsrmc(user.getRealName());
        xsgzqkCgbbForm.setBsdw(user.getUserDep());
        xsgzqkCgbbForm.setBsdwmc(user.getUserDepName());

        // 学年 学期list
        xsgzqkCgbbForm.setXn(Base.currXn);
        xsgzqkCgbbForm.setXq(Base.currXq);

        if("xx".equals(user.getUserStatus())){
            XsgzzbsqService xsgzzbsqService = new XsgzzbsqService();
            List<HashMap<String,String>> xyList = xsgzzbsqService.getXyList();
            request.setAttribute("xyList",xyList);
        }

        request.setAttribute("xnList", Base.getXnndList());
        request.setAttribute("xqList", Base.getXqList());
        return mapping.findForward("cgbbAdd");
    }

    /**
     *  转到常规报表修改页面.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-18 16:09
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    public ActionForward cgbbEdit(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkCgbbForm xsgzqkCgbbForm = (XsgzqkCgbbForm) form;
        User user = getUser(request);
        HashMap<String,String> cgbb = service.getCgbbById(xsgzqkCgbbForm.getId());
        BeanUtils.copyProperties(xsgzqkCgbbForm, cgbb);

        if("xx".equals(user.getUserStatus())){
            XsgzzbsqService xsgzzbsqService = new XsgzzbsqService();
            List<HashMap<String,String>> xyList = xsgzzbsqService.getXyList();
            request.setAttribute("xyList",xyList);
        }

        request.setAttribute("xnList", Base.getXnndList());
        request.setAttribute("xqList", Base.getXqList());
        return mapping.findForward("cgbbEdit");
    }

    /**
     *  转到常规报表查看页面.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-18 16:09
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    public ActionForward cgbbView(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkCgbbForm xsgzqkCgbbForm = (XsgzqkCgbbForm) form;
        User user = getUser(request);
        HashMap<String,String> cgbb = service.getCgbbById(xsgzqkCgbbForm.getId());
        BeanUtils.copyProperties(xsgzqkCgbbForm, cgbb);

        return mapping.findForward("cgbbView");
    }

    /**
     *  常规报表增加的的保存.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-18 16:10
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    public ActionForward cgbbSaveForAdd(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkCgbbForm xsgzqkCgbbForm = (XsgzqkCgbbForm) form;
        boolean result = service.runInsert(xsgzqkCgbbForm);
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     *  常规报表修改的保存.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-18 16:10
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    public ActionForward cgbbSaveForEdit(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkCgbbForm xsgzqkCgbbForm = (XsgzqkCgbbForm) form;
        boolean result = service.runUpdate(xsgzqkCgbbForm);
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     *  常规报表批量删除.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-19 17:18
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    public ActionForward cgbbDel(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String values = request.getParameter("values");
        if (!StringUtil.isNull(values)){
            int num = service.runDelete(values.split(","));
            boolean result =  num > 0;
            String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num)
                    : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
            response.getWriter().print(getJsonMessage(message));
        } else {
            throw new SystemException(MessageKey.SYS_DEL_NULL);
        }
        return null;
    }

    /**
     *  常规报表通用导出.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-19 19:31
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    @SystemAuth(url = url)
    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkCgbbForm xsgzqkCgbbForm = (XsgzqkCgbbForm) form;

        //生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        xsgzqkCgbbForm.setSearchModel(searchModel);
        User user = getUser(request);
        List<HashMap<String,String>> resultList = service.getAllList(xsgzqkCgbbForm,user);

        //导出功能代码
        IExportService exportService = new ExportExcelImpl();
        ExportModel exportModel = xsgzqkCgbbForm.getExportModel();
        exportModel.setZgh(user.getUserName());//当前操作员
        exportModel.setDataList(resultList);//设置数据
        exportModel.setDcclbh(request.getParameter("dcglbh"));//设置当前导出功能编号
        File file = exportService.getExportFile(exportModel);//生成导出文件
        FileUtil.outputExcel(response, file);
        return null;
    }

}
