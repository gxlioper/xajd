package com.zfsoft.xgxt.zhdj.xszbhd.zbhdsb;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
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
import xgxt.utils.String.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:支部活动上报
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-07 10:52
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class ZbhdsbAction extends SuperAction<ZbhdsbForm,ZbhdsbService> {
    private  ZbhdsbService service = new ZbhdsbService();
    private static final String url = "zhdj_zbhd_zbhdsb.do";

    /**
     * @描述:上报列表
     * @作者：lgx [工号：1553]
     * @日期：2018/6/7 15:48
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [model, user]
     * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     */
    @SystemAuth(url = url)
    public ActionForward getSbList(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZbhdsbForm model = (ZbhdsbForm) form;
        User user = getUser(request);

        if (QUERY.equals(model.getType())) {
            String flag = request.getParameter("flag");
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            // 查询
            List<HashMap<String, String>> resultList = service.getPageList(model, user);
            //限时上报列表
            if("xssb".equals(flag))
                resultList = service.getXssbList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        SearchModel searchModel=new SearchModel();
        searchModel.setSearch_tj_xn(new String[]{Base.currXn});
        searchModel.setSearch_tj_xq(new String[]{Base.currXq});
        request.setAttribute("searchTj", searchModel);
        String path = "zhdj_zbhd_zbhdsb.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("hdsbList");
    }

    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZbhdsbForm model = (ZbhdsbForm) form;
        User user = getUser(request);
        HashMap<String,String> map = service.getInfo(model,user.getUserName());
        //三会一课/党日活动
        List<HashMap<String,String>> drhdList = service.getAllDrhd();
        //活动类型
        List<HashMap<String,String>> hdlxList = service.getAllHdlx();
        String xn = Base.currXn;
        String xq = Base.currXq;
        if("xssb".equals(model.getType())){
            xn = map.get("xn");
            xq = map.get("xqdm");
        }
        request.setAttribute("drhdList",drhdList);
        request.setAttribute("hdlxList",hdlxList);
        request.setAttribute("model",model);
        request.setAttribute("type", model.getType());
        request.setAttribute("map",map);
        request.setAttribute("xn",xn);
        request.setAttribute("xqmc","01".equals(xq) ? "第一学期":"第二学期");
        return mapping.findForward("add");
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZbhdsbForm model = (ZbhdsbForm) form;
        User user = getUser(request);
        //三会一课/党日活动
        List<HashMap<String,String>> drhdList = service.getAllDrhd();
        //活动类型
        List<HashMap<String,String>> hdlxList = service.getAllHdlx();
        HashMap<String,String> hashMap = service.getInfo(model,user.getUserName());
        if("xssb".equals(model.getType())){
            HashMap<String,String> map = service.getXssbModel(model);
            map.put("hdzt",hashMap.get("hdzt"));
            BeanUtils.copyProperties(model,StringUtils.formatData(map));

        }else{
            ZbhdsbForm myForm = service.getModel(model);
            BeanUtils.copyProperties(model,StringUtils.formatData(myForm));
        }

        request.setAttribute("drhdList",drhdList);
        request.setAttribute("hdlxList",hdlxList);
        request.setAttribute("map",hashMap);
        request.setAttribute("xn",model.getXn());
        request.setAttribute("type", model.getType());
        request.setAttribute("hdzt", model.getHdzt());
        request.setAttribute("xqmc","01".equals(model.getXqdm()) ? "第一学期":"第二学期");
        return mapping.findForward("update");
    }
    public ActionForward del(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZbhdsbForm model = (ZbhdsbForm) form;
        String values = request.getParameter("values");
        if (!StringUtil.isNull(values)) {
            String[] ids = values.split(",");
            int num = 0;
            if("xssb".equals(model.getType())){
                num = service.deleteXssb(ids);
            }else{
                num = service.runDelete(ids);
            }


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
    public ActionForward view(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZbhdsbForm model = (ZbhdsbForm) form;
        User user = getUser(request);
        HashMap<String,String> map = service.getHdsbInfo(model,user);
        request.setAttribute("map",StringUtils.formatData(map));
        return mapping.findForward("view");
    }
    public ActionForward save(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZbhdsbForm model = (ZbhdsbForm) form;
        User user = getUser(request);
        String flag = request.getParameter("flag");
        if("add".equals(flag)){
            HashMap<String,String> map = service.getInfo(model,user.getUserName());
            if("xssb".equals(model.getType())){
                model.setXn(map.get("xn"));
                model.setXqdm(map.get("xqdm"));
                model.setHdid(map.get("hdid"));
            }else{
                model.setXn(Base.currXn);
                model.setXqdm(Base.currXq);
            }
        }
        boolean result = service.save(model);
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }
}
