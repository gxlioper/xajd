package com.zfsoft.xgxt.sxzzjy.mxbzb.xymxbzbgl;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:校园明星榜中榜管理
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-27 11:41
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class XymxbzbglAction extends SuperAction<XymxbzbglForm,XymxbzbglService> {
    private XymxbzbglService service = new XymxbzbglService();
    private static final String url = "sxzzjy_mxbzb_xymxbzbgl.do";


    @SystemAuth(url = url)
    public ActionForward getList(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        XymxbzbglForm model = (XymxbzbglForm) form;
        User user = getUser(request);
        if (QUERY.equals(model.getType())) {
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            // 查询
            List<HashMap<String, String>> resultList = service.getPageList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        String path = "sxzzjy_mxbzb_xymxbzbgl.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("xymxbzbglList");
    }

    public ActionForward xymxbzbglAdd(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        XymxbzbglForm model = (XymxbzbglForm) form;
        User user = getUser(request);
        List<HashMap<String,String>> lbList = service.getAlllb();
        request.setAttribute("lbList",lbList);
        return mapping.findForward("xymxbzbglAdd");
    }

    public ActionForward xymxbzbglUpdate(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        XymxbzbglForm model = (XymxbzbglForm) form;
        User user = getUser(request);
        //XymxbzbglForm myForm = service.getXymxbzbglInfo(model);
        List<HashMap<String,String>> lbList = service.getAlllb();
        request.setAttribute("lbList",lbList);
        XymxbzbglForm myForm = service.getModel(model.getNewsid());
        BeanUtils.copyProperties(model,myForm);
        return mapping.findForward("xymxbzbglUpdate");
    }

    public ActionForward xymxbzbglSave(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        XymxbzbglForm model = (XymxbzbglForm) form;
        User user = getUser(request);
        boolean result = false;
        if ("add".equals(model.getType())) {
            result = service.xymxbzbglInsert(model,user);
        }
        if ("update".equals(model.getType())) {
            result = service.xymxbzbglUpdate(model);
        }
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }
    public ActionForward xymxbzbglDel(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //获得id
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

    public ActionForward xymxbzbglView(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        XymxbzbglForm model = (XymxbzbglForm) form;
        HashMap<String,String> map = service.getxymxbzbglInfo(model);
        request.setAttribute("map",map);
        return mapping.findForward("xymxbzbglView");
    }

    /**
     * @描述:发布
     * @作者：lgx [工号：1553]
     * @日期：2018/6/28 14:41
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [mapping, form, request, response]
     * @return: org.apache.struts.action.ActionForward
     */
    public ActionForward xymxbzbglFb(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String values = request.getParameter("values");
        String[] ids = values.split(",");
        String message = service.xymxbzbglFb(ids);
        response.getWriter().print(getJsonMessage(message));
        return null;
    }
    /**
     * @描述:取消发布
     * @作者：lgx [工号：1553]
     * @日期：2018/6/28 15:08
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [mapping, form, request, response]
     * @return: org.apache.struts.action.ActionForward
     */
    public ActionForward xymxbzbglQxfb(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String values = request.getParameter("values");
        String[] ids = values.split(",");
        String message = service.xymxbzbglQxfb(ids);
        response.getWriter().print(getJsonMessage(message));
        return null;
    }
    /**
     * @描述:置顶
     * @作者：lgx [工号：1553]
     * @日期：2018/6/28 15:09
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [mapping, form, request, response]
     * @return: org.apache.struts.action.ActionForward
     */
    public ActionForward xymxbzbglZd(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String values = request.getParameter("values");
        String[] ids = values.split(",");
        String message = service.xymxbzbglZd(ids);
        response.getWriter().print(getJsonMessage(message));
        return null;
    }
    /**
     * @描述:取消置顶
     * @作者：lgx [工号：1553]
     * @日期：2018/6/28 15:09
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [mapping, form, request, response]
     * @return: org.apache.struts.action.ActionForward
     */
    public ActionForward xymxbzbglQxzd(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String values = request.getParameter("values");
        String[] ids = values.split(",");
        String message = service.xymxbzbglQxzd(ids);
        response.getWriter().print(getJsonMessage(message));
        return null;
    }

    /**
     * @描述:校园明星榜中榜获取展示的类别
     * @作者：lgx [工号：1553]
     * @日期：2018/6/29 13:57
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [mapping, form, request, response]
     * @return: org.apache.struts.action.ActionForward
     */
    public ActionForward getAlbForView(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List<HashMap<String, String>> resultList = service.getAlbForView();
        JSONArray dataList = JSONArray.fromObject(resultList);
        response.getWriter().print(dataList);
        return null;
    }

    /**
     * @描述:校园明星榜中榜跳转
     * @作者：lgx [工号：1553]
     * @日期：2018/6/28 17:07
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [mapping, form, request, response]
     * @return: org.apache.struts.action.ActionForward
     */
    public ActionForward xymxbzb(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("xymxbzb");
    }

    /**
     * @描述:校园明星榜中榜展示获取列表
     * @作者：lgx [工号：1553]
     * @日期：2018/6/28 17:05
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [mapping, form, request, response]
     * @return: org.apache.struts.action.ActionForward
     */
    public ActionForward getNewsList(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String typedm = request.getParameter("typedm");
        String size = request.getParameter("size");
        List<HashMap<String, String>> resultList = service.getNewsList(typedm,size);
        JSONArray dataList = JSONArray.fromObject(resultList);
        response.getWriter().print(dataList);
        return null;
    }

    /**
     * @描述:更多
     * @作者：lgx [工号：1553]
     * @日期：2018/6/29 9:07
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [mapping, form, request, response]
     * @return: org.apache.struts.action.ActionForward
     */
    public ActionForward getNewsmore(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        XymxbzbglForm model = (XymxbzbglForm) form;
        User user = getUser(request);
        if (QUERY.equals(model.getType())) {
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            // 查询
            List<HashMap<String, String>> resultList = service.getNewsmore(model);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        SearchModel searchModel=new SearchModel();
        searchModel.setSearch_tj_newstype(new String[]{model.getNewstype()});
        request.setAttribute("searchTj", searchModel);
        String path = "sxzzjy_xymxbzb_newsmore.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("getNewsmore");
    }

    public ActionForward addYdr(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        XymxbzbglForm model = (XymxbzbglForm) form;
        User user = getUser(request);
        boolean rs = service.addYdr(model,user);
        return null;
    }

    public ActionForward getYydmd(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        XymxbzbglForm model = (XymxbzbglForm) form;
        User user = getUser(request);
        if (QUERY.equals(model.getType())) {
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            // 查询
            List<HashMap<String, String>> resultList = service.getYydmd(model);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }

        return mapping.findForward("yydmd");
    }
}
