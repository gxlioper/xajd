package com.zfsoft.xgxt.dtjs.shsjjl.sh;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.dtjs.shsjjl.cssz.CsszService;
import com.zfsoft.xgxt.dtjs.shsjjl.sq.ShsjjlsqService;
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
import xgxt.utils.String.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2019-03-01 09:24
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class ShsjjlshAction extends SuperAction<ShsjjlshForm,ShsjjlshService> {
    private static final String SHSJJL = "shsjjl";
    private ShsjjlshService service = new ShsjjlshService();

    private static final String url = "dtjs_shsjjl_sh.do";
    private static List<HashMap<String, String>> jbxxList = null;
    static {
        BdpzService bdpzService = new BdpzService();
        jbxxList = bdpzService.getJbxxpz(SHSJJL);
    }

    @SystemAuth(url = url)
    public ActionForward getList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ShsjjlshForm model = (ShsjjlshForm) form;
        if (QUERY.equalsIgnoreCase(model.getType())) {
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            List<HashMap<String, String>> resultList = service.getPageList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        // 默认高级查询条件
        SearchModel searchModel = new SearchModel();
        searchModel.setSearch_tj_xn(new String[] { Base.currXn });
        searchModel.setSearch_tj_xq(new String[] { Base.currXq });
        request.setAttribute("searchTj", searchModel);
        request.setAttribute("path", "dtjs_shsjjl_sh.do");
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("shsjjlShList");
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward shsjjlDgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ShsjjlshForm model = (ShsjjlshForm) form;
        if (!StringUtil.isNull(model.getXh())) {
            // 加载学生基本信息
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
            request.setAttribute("jbxx", xsjbxx);
            HashMap<String, String> infoList = service.getInfo(model.getSqid());
            request.setAttribute("rs", StringUtils.formatData(infoList));
        }
        if (SAVE.equalsIgnoreCase(model.getType())) {
            User user = getUser(request);
            // 保存单个审核
            boolean result = service.saveSh(model, user);
            String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(messageKey));
            return null;
        }
        request.setAttribute("jbxxList", jbxxList);
        model = service.getModel(model);
        model.setShid(request.getParameter("shid"));
        request.setAttribute("model", StringUtils.formatData(model));
        return mapping.findForward("shsjjlDgsh");
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward shsjjlPlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ShsjjlshForm model = (ShsjjlshForm) form;
        User user = getUser(request);
        if (SAVE.equalsIgnoreCase(model.getType())) {
            String message = service.savePlsh(model, user);
            response.getWriter().print(getJsonMessage(message));
            return null;
        }
        return mapping.findForward("shsjjlPlsh");
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward cancelSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ShsjjlshForm model = (ShsjjlshForm) form;
        String sqid = request.getParameter("sqid");
        String shzt = request.getParameter("shzt");
        model.setShzt(shzt);
        model.setSqid(sqid);
        // 最后一级撤销
        boolean isSuccess = service.cancel(model);
        String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }
}
