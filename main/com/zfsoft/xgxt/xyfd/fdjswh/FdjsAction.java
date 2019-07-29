package com.zfsoft.xgxt.xyfd.fdjswh;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.szdw.fdyxx.FdyxxService;
import com.zfsoft.xgxt.xyfd.fdswh.FdsForm;
import com.zfsoft.xgxt.xyfd.fdswh.FdsService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by llf on 2019/6/24.
 */
public class FdjsAction extends SuperAction<FdjsForm, FdjsService> {

    private FdjsService fdjsService = new FdjsService();
    /**
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward fdjsList(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
        FdjsForm model = (FdjsForm)form;

        if (QUERY.equalsIgnoreCase(model.getType())){
            //生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            List<HashMap<String,String>> resultList = getService().getPageList(model,user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        String path = "xyfd_xyfd_fdjswh.do";
        request.setAttribute("path", path);
        return mapping.findForward("fdjsList");
    }

    /**
     * 新增辅导教师
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addfdjs(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws Exception{
        FdjsForm model = (FdjsForm)form;
        if(!StringUtil.isNull(model.getZgh())){
            FdyxxService fdyxxService = new FdyxxService();
            HashMap<String,String> jsxx =  fdyxxService.getFdyInfo(model.getZgh());
            request.setAttribute("jsxx",jsxx);
        }
        if (SAVE.equalsIgnoreCase(model.getType())){
            model.setMon(StringUtils.join(request.getParameterValues("mond"),","));
            model.setTues(StringUtils.join(request.getParameterValues("tuesd"),","));
            model.setWed(StringUtils.join(request.getParameterValues("wedd"),","));
            model.setThur(StringUtils.join(request.getParameterValues("thurd"),","));
            model.setFri(StringUtils.join(request.getParameterValues("frid"),","));
            model.setSat(StringUtils.join(request.getParameterValues("satd"),","));
            model.setSun(StringUtils.join(request.getParameterValues("sund"),","));
            boolean flag = fdjsService.saveFds(model);
            String key = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(key));
            return null;
        }
        return mapping.findForward("addfdjs");
    }

    /**
     * 修改辅导教师
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updatefdjs(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws Exception{
        FdjsForm model = (FdjsForm)form;
        if (UPDATE.equalsIgnoreCase(model.getType())){
            model.setMon(StringUtils.join(request.getParameterValues("mond"),","));
            model.setTues(StringUtils.join(request.getParameterValues("tuesd"),","));
            model.setWed(StringUtils.join(request.getParameterValues("wedd"),","));
            model.setThur(StringUtils.join(request.getParameterValues("thurd"),","));
            model.setFri(StringUtils.join(request.getParameterValues("frid"),","));
            model.setSat(StringUtils.join(request.getParameterValues("satd"),","));
            model.setSun(StringUtils.join(request.getParameterValues("sund"),","));
            boolean flag = fdjsService.updateFdjs(model);
            String key = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(key));
            return null;
        }
        HashMap<String,String> fdjsxx = fdjsService.getFdjs(model);
        request.setAttribute("fdjsxx",fdjsxx);//辅导教师信息
        BeanUtils.copyProperties(model,fdjsxx);
        if(!StringUtil.isNull(model.getZgh())){
            HashMap<String,String> jsxx =  fdjsService.getTea(model);
            request.setAttribute("jsxx",jsxx);//教师信息
        }
        FdsService fdsService = new FdsService();
        FdsForm fdsForm = new FdsForm();
        fdsForm.setId(model.getFds());
        HashMap<String,String> fdsxx = fdsService.getFds(fdsForm);
        request.setAttribute("fdsxx",fdsxx);//辅导室信息
        return mapping.findForward("updatefdjs");
    }

    /**
     * 删除辅导教师
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteFdjs(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception{
        String values = request.getParameter("values");
        if (!StringUtil.isNull(values)){
            String[] mess = fdjsService.deleteFdjs(values.split(","));
            if(null==mess||mess.length!=2){
                String message= MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
                response.getWriter().print(getJsonMessage(message));
                return null;
            }
            Map<String, String> map = new HashMap<String, String>();
            map.put("num",mess[0]);
            map.put("nodel",mess[1]);
            JSONObject json = JSONObject.fromObject(map);
            response.getWriter().print(json);
        } else {
            throw new SystemException(MessageKey.SYS_DEL_NULL);
        }
        return null;
    }

    /**
     * 选择教师
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward selectTeacher(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response) throws Exception {

        String type = request.getParameter("type");
        if(QUERY.equals(type)){
            FdjsForm model = (FdjsForm) form;
            //生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            FdjsService service = new FdjsService();
            List<HashMap<String,String>> resultList = service.getAllTeacher(model);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path","qgzx_jcdmwh_ajax.do?method=selectTeacher");
        return mapping.findForward("selectTeacher");
    }

    /**
     * 选择辅导室
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward selectFds(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response) throws Exception {

        String type = request.getParameter("type");
        if(QUERY.equals(type)){
            FdjsForm model = (FdjsForm) form;
            //生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            FdjsService service = new FdjsService();
            List<HashMap<String,String>> resultList = service.getKxFds(model);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path","xyfd_xyfd_fdswh.do");
        return mapping.findForward("selectFds");
    }

    /**
     * 修改辅导教师
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward fdjsView(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception{
        FdjsForm model = (FdjsForm)form;
        HashMap<String,String> fdjsxx = fdjsService.getFdjs(model);
        request.setAttribute("fdjsxx",fdjsxx);//辅导教师信息
        BeanUtils.copyProperties(model,fdjsxx);
        if(!StringUtil.isNull(model.getZgh())){
            HashMap<String,String> jsxx =  fdjsService.getTea(model);
            request.setAttribute("jsxx",jsxx);//教师信息
        }
        FdsService fdsService = new FdsService();
        FdsForm fdsForm = new FdsForm();
        fdsForm.setId(model.getFds());
        HashMap<String,String> fdsxx = fdsService.getFds(fdsForm);
        request.setAttribute("fdsxx",fdsxx);//辅导室信息
        return mapping.findForward("fdjsView");
    }
}
