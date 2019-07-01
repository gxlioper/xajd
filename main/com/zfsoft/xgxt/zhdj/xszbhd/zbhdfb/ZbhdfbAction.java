package com.zfsoft.xgxt.zhdj.xszbhd.zbhdfb;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwjg.OtmMapping;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @类功能描述: 西北工业：智慧党校-学生支部活动管理
 * @作者： lgx [工号:1553]
 * @时间： 2018-5-30 上午09:11:04
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class ZbhdfbAction  extends SuperAction<ZbhdfbForm,ZbhdfbService> {

    private static final String url = "zhdj_zbhd_zbhdfb.do";

    /**
     *
     * @描述:活动列表查询显示
     * @作者：lgx [工号：1553]
     * @日期：2018-6-1 上午09:17:35
     * @修改记录: 修改者名字-修改日期-修改内容
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     *             ActionForward 返回类型
     * @throws
     */
   @SystemAuth(url = url)
    public ActionForward getHdfbList(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZbhdfbService service = new ZbhdfbService();
        ZbhdfbForm model = (ZbhdfbForm) form;
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
        SearchModel searchModel=new SearchModel();
        searchModel.setSearch_tj_xn(new String[]{Base.currXn});
        searchModel.setSearch_tj_xq(new String[]{Base.currXq});
        request.setAttribute("searchTj", searchModel);
        String path = "zhdj_zbhd_zbhdfb.do";
        request.setAttribute("path", path);
       FormModleCommon.commonRequestSet(request);
        return mapping.findForward("hdfbList");
    }
    /**
     *
     * @描述:活动详情查看
     * @作者：lgx [工号：1553]
     * @日期：2018-6-1 上午09:36:03
     * @修改记录: 修改者名字-修改日期-修改内容
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     *             ActionForward 返回类型
     * @throws
     */
    public ActionForward hdDetail(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZbhdfbService service = new ZbhdfbService();
        ZbhdfbForm model = (ZbhdfbForm) form;
        HashMap<String, String> map = service.gethdDetail(model);
        request.setAttribute("map",map);
        return mapping.findForward("hdDetail");
    }
    /**
     *
     * @描述:活动进度查看
     * @作者：lgx [工号：1553]
     * @日期：2018-6-1 上午09:49:45
     * @修改记录: 修改者名字-修改日期-修改内容
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     *             ActionForward 返回类型
     * @throws
     */
    public ActionForward hdjdDetail(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZbhdfbService service = new ZbhdfbService();
        ZbhdfbForm model = (ZbhdfbForm) form;
        if("query".equals(model.getType())) {
            List<HashMap<String, String>> list = service.gethdjdDetail(model);
            HashMap<String, List<HashMap<String, String>>> resultMap = new HashMap<String, List<HashMap<String, String>>>();
            for (HashMap<String, String> hashMap : list) {
                String key = hashMap.get("xymc");
                List<HashMap<String, String>> relist = resultMap.get(key);
                if (relist == null) {
                    relist = new ArrayList<HashMap<String, String>>();
                }
                relist.add(hashMap);
                resultMap.put(key, relist);

            }
            List<IterateModel> iterateModelList = new ArrayList<IterateModel>();
            for (String key : resultMap.keySet()) {
                IterateModel iterateModel = new IterateModel();
                iterateModel.setXymc(key);
                iterateModel.setListSize(resultMap.get(key).size());
                iterateModel.setRslist(resultMap.get(key));
                iterateModelList.add(iterateModel);
            }
            JSONArray dataList = JSONArray.fromObject(iterateModelList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("hdid",model.getHdid());
        return mapping.findForward("hdjdDetail");
    }

    public ActionForward add(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.setAttribute("xn",Base.currXn);
        request.setAttribute("xq","01".equals(Base.currXq) ? "第一学期":"第二学期");
        return mapping.findForward("add");
    }
    public ActionForward update(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZbhdfbService service = new ZbhdfbService();
        ZbhdfbForm model = (ZbhdfbForm) form;
        model = service.getModel(model);
        String xqmc = "";
        if("01".equals(model.getXqdm())) xqmc="第一学期";
        if("02".equals(model.getXqdm())) xqmc="第二学期";
        request.setAttribute("xqmc",xqmc);
        request.setAttribute("model",StringUtils.formatData(model));
        return mapping.findForward("update");
    }
    public ActionForward save(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZbhdfbService service = new ZbhdfbService();
        ZbhdfbForm model = (ZbhdfbForm) form;
        boolean qtdzb = false;
        if("add".equals(model.getType())){
            String qtdzbStr = request.getParameter("qtdzb");
            qtdzb = Boolean.parseBoolean(qtdzbStr);
        }
        boolean result = service.save(model, qtdzb);
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    public ActionForward del(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZbhdfbService service = new ZbhdfbService();
        //获得id
        String values = request.getParameter("values");
        if (!StringUtil.isNull(values)) {
            String[] ids = values.split(",");
            int num = service.runDelete(ids);
            boolean result = num > 0;
            if(result){
                //删除活动后把参加的党支部记录删除
                result = service.delCjDzb(ids);
            }
            String message = result ? MessageUtil.getText(
                    MessageKey.SYS_DEL_NUM, num) : MessageUtil
                    .getText(MessageKey.SYS_DEL_FAIL);
            response.getWriter().print(getJsonMessage(message));
        } else {
            throw new SystemException(MessageKey.SYS_DEL_NULL);
        }
        return null;
    }
    /**
     * @描述:选择党支部
     * @作者：lgx [工号：1553]
     * @日期：2018/6/15 8:51
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [mapping, form, request, response]
     * @return: org.apache.struts.action.ActionForward
     */
    public ActionForward selectDzb(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZbhdfbService service = new ZbhdfbService();
        ZbhdfbForm model = (ZbhdfbForm) form;
        User user = getUser(request);
        String flag = request.getParameter("flag");
        if (QUERY.equals(model.getType())) {
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
                List<HashMap<String, String>> resultList = service.getWxzDzbList(model, user);
                //已选择
                if("yxz".equals(flag)){
                    resultList = service.getYxzDzbList(model, user);
                }
                JSONArray dataList = JSONArray.fromObject(resultList);
                response.getWriter().print(dataList);
                return null;

        }
        String path = "zhdj_zbhdfb_cjry.do";
        request.setAttribute("path", path);
        request.setAttribute("hdid", model.getHdid());
        return mapping.findForward("selectDzb");
    }
/**
 * @描述:验证是否允许删除和修改
 * @作者：lgx [工号：1553]
 * @日期：2018/6/6 14:52
 * @修改记录: 修改者名字-修改日期-修改内容
 * @param: [mapping, form, request, response]
 * @return: org.apache.struts.action.ActionForward
 */
    public ActionForward checkEdit(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZbhdfbService service = new ZbhdfbService();
        int data = 0;
        String type = request.getParameter("type");
        if("del".equals(type)){
            String[] ids = request.getParameter("values").split(",");
            data = service.checkEditforDel(ids);
        }else{
            ZbhdfbForm myForm = (ZbhdfbForm) form;
            ZbhdfbForm model = service.getModel(myForm);
            data = service.checkEdit(model);
        }
        response.getWriter().print(data);
        return null;
    }
    /**
     * @描述:保存已选择的党支部
     * @作者：lgx [工号：1553]
     * @日期：2018/6/6 19:35
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [mapping, form, request, response]
     * @return: org.apache.struts.action.ActionForward
     */
    public ActionForward editDzb(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZbhdfbService service = new ZbhdfbService();
        ZbhdfbForm model = (ZbhdfbForm) form;
        String[] ids = request.getParameter("values").split(",");
        String message = "";
        boolean  result = false;
        if("save".equals(model.getType())){

            result = service.saveDzb(model,ids);
            message = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(message));
        }
        if("del".equals(model.getType())){

            int num = service.delDzb(model,ids);
            result = num > 0;
            message = result ? MessageUtil.getText(
                    MessageKey.SYS_DEL_NUM,num) : MessageUtil
                    .getText(MessageKey.SYS_DEL_FAIL);
            response.getWriter().print(getJsonMessage(message));
        }

        return null;
    }
    /**
     * @描述:查看面向党支部
     * @作者：lgx [工号：1553]
     * @日期：2018/6/7 10:25
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [mapping, form, request, response]
     * @return: org.apache.struts.action.ActionForward
     */
    public ActionForward showDzb(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZbhdfbService service = new ZbhdfbService();
        ZbhdfbForm model = (ZbhdfbForm) form;
        User user = getUser(request);
        if (QUERY.equals(model.getType())) {
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            List<HashMap<String, String>> resultList = service.getYxzDzbList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        String path = "zhdj_zbhdfb_cjry.do";
        request.setAttribute("path", path);
        request.setAttribute("hdid", model.getHdid());
        return mapping.findForward("showDzb");
    }
}
