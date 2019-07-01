package com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjssh;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjshb.BjxfjshbForm;
import com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjshb.BjxfjshbService;
import com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjsjcsz.BjxfjsjcszService;
import com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjssq.BjxfjssqForm;
import com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjssq.BjxfjssqService;
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
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:班级学风建设审核
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-22 15:33
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class BjxfjsshAction extends SuperAction<BjxfjsshForm,BjxfjsshService> {
    private BjxfjsshService service = new BjxfjsshService();
    private static final String url = "sxzzjy_bjxfjs_bjxfjssh.do";


    @SystemAuth(url = url)
    public ActionForward getList(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BjxfjsshForm model = (BjxfjsshForm) form;
        User user = getUser(request);
        if (QUERY.equalsIgnoreCase(model.getType())){
            //生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            //查询审核数据
            List<HashMap<String,String>> resultList = service.getPageList(model,user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }

        SearchModel searchModel=new SearchModel();
        searchModel.setSearch_tj_nd(new String[] {Base.currNd});
        searchModel.setSearch_tj_xn(new String[] {Base.currXn});
        request.setAttribute("searchTj", searchModel);
        String path = "sxzzjy_bjxfjs_bjxfjssh.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("bjxfjsshList");
    }

    public ActionForward bjxfjsSh(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BjxfjsshForm model = (BjxfjsshForm) form;
        String bjdm = URLDecoder.decode(model.getBjdm(),   "utf-8");
        model.setBjdm(bjdm);
        if (SAVE.equalsIgnoreCase(model.getType())){

            String num = service.checkExistForSave(model);
            if("0".equals(num)) {
                User user = getUser(request);
                boolean result = service.saveSh(model,user);
                String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
                response.getWriter().print(getJsonMessageByKey(messageKey));
                return null;
            }else {
                response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_YLBX_YLBXSH));
                return null;
            }

        }

        BjxfjssqService sqService = new BjxfjssqService();
        BjxfjssqForm sqForm = new BjxfjssqForm();
        sqForm.setSqid(model.getSqid());
        HashMap<String,String> map = new HashMap<String, String>();
        if("sq".equals(model.getSqlx())){
            map = sqService.getBjxfjssqInfo(sqForm);
            request.setAttribute("map",map);
        }else{
            BjxfjshbService bjxfjshbService = new BjxfjshbService();
            BjxfjshbForm bjxfjshbForm = bjxfjshbService.getModel(model.getSqid());
            request.setAttribute("map",bjxfjshbForm);
        }

        HashMap<String, String> bjmap = sqService.getBjInfo(bjdm);
        request.setAttribute("bjmap",bjmap);
        request.setAttribute("sqlx",model.getSqlx());
        return mapping.findForward("bjxfjsSh");
    }

    public ActionForward cancelBjxfjssh(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BjxfjsshForm model = (BjxfjsshForm) form;
        User user = getUser(request);
        String sqid = request.getParameter("sqid");
        model.setSqid(sqid);
        boolean isSuccess = service.newCancelSh(model, user);
        String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    public ActionForward bjxfjsPlsh(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BjxfjsshForm model = (BjxfjsshForm) form;

        User user = getUser(request);
        if("SAVE".equalsIgnoreCase(model.getType())){
            String message = service.savePlsh(model, user);
            response.getWriter().print(getJsonMessage(message));
            return null;
        }
        return mapping.findForward("bjxfjsPlsh");
    }
}
