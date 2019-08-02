package com.zfsoft.xgxt.zhdj.dzzhd;

import com.alibaba.fastjson.JSON;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.base.DealString;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 党团建设-党组织生活管理-党组织活动管理
 * @类功能描述: 党团建设活动管理
 * @作者: 何爽 [工号:1730]
 * @时间: 2019/7/19 16:02
 */
public class DzzhdAction extends SuperAction<DzzhdForm,DzzhdService> {

    private DzzhdService dzzhdService = DzzhdService.getDzzhdService();
    private static final String hdfbUrl = "zhdj_dzzhd_hdfb.do";

    /**
     * @描述: 活动发布列表获取
     * @作者: 何爽[工号:1730]
     * @日期: 2019/7/19 16:33
     * @修改记录: 修改人-修改时间-修改描述
     * @param mapping
	* @param form
	* @param request
	* @param response
     * @return org.apache.struts.action.ActionForward
     **/
    @SystemAuth(url=hdfbUrl,rewritable = SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward getFbPageList(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {
        DzzhdForm model = (DzzhdForm) form;
        String doType = request.getParameter("doType");
        User user = getUser(request);
        if(QUERY.equals(doType)){
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            List<HashMap<String, String>> resultList = dzzhdService.getFbPageList(model,user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path", "zhdj_dzzhd_hdfb.do");
        request.setAttribute("userType",user.getUserType());
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("getFbPageList");
    }

    /**
     * @描述: 活动结果列表
     * @作者: 何爽[工号:1730]
     * @日期: 2019/8/1 15:29
     * @修改记录: 修改人-修改时间-修改描述
     * @param mapping
	* @param form
	* @param request
	* @param response 
     * @return org.apache.struts.action.ActionForward
     **/
    @SystemAuth(url=hdfbUrl,rewritable = SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward getHdPageList(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {
        DzzhdForm model = (DzzhdForm) form;
        String doType = request.getParameter("doType");
        User user = getUser(request);
        if(QUERY.equals(doType)){
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            List<HashMap<String, String>> resultList = dzzhdService.getFbPageList(model,user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path", "zhdj_dzzhd_hdfb.do");
        request.setAttribute("userType",user.getUserType());
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("getHdPageList");
    }

    /**
     * @描述: 信息查看
     * @作者: 何爽[工号:1730]
     * @日期: 2019/7/19 16:37
     * @修改记录: 修改人-修改时间-修改描述
     * @param mapping
	* @param form
	* @param request
	* @param response
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward hdInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        DzzhdForm model = (DzzhdForm) form;
        User user = getUser(request);
        String hdId = model.getId();
        String status = request.getParameter("status");
        if(StringUtils.isNotNull(hdId)){
            //申请id存在则获取申请信息
            HashMap<String,String> data = dzzhdService.getById(hdId);
            request.setAttribute("data",data);
            if(QUERY.equals(status)){
                Map<String,Object> resultMap = new HashMap<>();
                resultMap.put("code",1);
                resultMap.put("msg","获取成功!");
                resultMap.put("data",data);
                response.getWriter().print(JSON.parseObject(JSON.toJSONString(resultMap)));
                return null;
            }
        }
        request.setAttribute("status",status);
        request.setAttribute("mxdxList",dzzhdService.getZzmmList());
        return mapping.findForward("dzzhdEdit");
    }

    /**
     * @描述: 活动信息保存
     * @作者: 何爽[工号:1730]
     * @日期: 2019/7/22 8:49
     * @修改记录: 修改人-修改时间-修改描述
     * @param mapping
	* @param form
	* @param request
	* @param response
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward hdSave(ActionMapping mapping,ActionForm form,HttpServletRequest request,
                                HttpServletResponse response) throws Exception{
        DzzhdForm model = (DzzhdForm) form;
        String hdnr = DealString.toGBK(model.getHdnr());
        model.setHdnr(hdnr);
        User user = getUser(request);
        HashMap<String,Object> data = dzzhdService.hdSave(model,user);
        response.getWriter().print(JSON.parseObject(JSON.toJSONString(data)));
        return null;
    }

    /**
     * @描述: 活动信息删除
     * @作者: 何爽[工号:1730]
     * @日期: 2019/7/22 8:50
     * @修改记录: 修改人-修改时间-修改描述
     * @param mapping
	* @param form
	* @param request
	* @param response
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward removeById(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        Map<String,Object> result = dzzhdService.removeById(id);
        response.getWriter().print(JSON.parseObject(JSON.toJSONString(result)));
        return null;
    }

    /**
     * @描述: 活动人员列表
     * @作者: 何爽[工号:1730]
     * @日期: 2019/8/1 15:49
     * @修改记录: 修改人-修改时间-修改描述
     * @param mapping
	* @param form
	* @param request
	* @param response
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward getHdryList(ActionMapping mapping,ActionForm form,HttpServletRequest request,
                                     HttpServletResponse response) throws Exception{
        DzzhdForm myForm = (DzzhdForm) form;
        String joinStatus = request.getParameter("joinStatus");//加入状态 0 可添加 1已添加
        String hdid = request.getParameter("hdid");//活动id
        myForm.setId(hdid);
        myForm.setJoinStatus(joinStatus);
        String doType = request.getParameter("doType");
        User user = getUser(request);
        if(QUERY.equals(doType)){
            //获取活动人员信息列表
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            myForm.setSearchModel(searchModel);
            List<HashMap<String,String>> xsxxList = dzzhdService.getHdRyxxList(myForm,user);
            JSONArray dataList = JSONArray.fromObject(xsxxList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path","zhdj_dzzhd_hdry.do");
        request.setAttribute("joinStatus",joinStatus);
        request.setAttribute("hdid",hdid);
        return mapping.findForward("hdryList");
    }

    //活动人员批量添加 hdRySave
    public ActionForward hdRySave(ActionMapping mapping,ActionForm form,HttpServletRequest request,
                                  HttpServletResponse response) throws Exception{
        String hdid = request.getParameter("hdid");
        String xhs = request.getParameter("xhs");
        Map<String,Object> resultMap = new HashMap<>();
        if(StringUtils.isNull(hdid) || StringUtils.isNull(xhs)){
            resultMap.put("code",0);
            resultMap.put("msg","提交信息有误!");
        }else{
            boolean flag = dzzhdService.batchAdd(hdid,xhs);
            if(flag){
                resultMap.put("code",1);
                resultMap.put("msg","添加成功!");
            }else{
                resultMap.put("code",0);
                resultMap.put("msg","添加失败!");
            }
        }
        response.getWriter().print(JSON.parseObject(JSON.toJSONString(resultMap)));
        return null;
    }
    //活动人员批量删除 hdRyRemove
    public ActionForm hdRyRemove(ActionMapping mapping,ActionForm form,HttpServletRequest request,
                                 HttpServletResponse response) throws Exception{
        String hdid = request.getParameter("hdid");
        String xhs = request.getParameter("xhs");
        Map<String,Object> resultMap = new HashMap<>();
        if(StringUtils.isNull(hdid) || StringUtils.isNull(xhs)){
            resultMap.put("code",0);
            resultMap.put("msg","提交信息有误!");
        }else{
            boolean flag = dzzhdService.batchRemove(hdid,xhs);
            if(flag){
                resultMap.put("code",1);
                resultMap.put("msg","移除成功!");
            }else{
                resultMap.put("code",0);
                resultMap.put("msg","移除失败!");
            }
        }
        response.getWriter().print(JSON.parseObject(JSON.toJSONString(resultMap)));
        return null;
    }

    //活动心得
    public ActionForward hdXdInfo(ActionMapping mapping,ActionForm form,HttpServletRequest request,
                                  HttpServletResponse response) throws Exception{
        String hdid = request.getParameter("hdid");
        String xh = request.getParameter("xh");
        String status = request.getParameter("status");
        User user = getUser(request);
        if(StringUtils.isNull(xh) && "stu".equals(user.getUserType())){
            xh = user.getUserName();
        }
        //获取活动人员信息
        HashMap<String,String> result = dzzhdService.getHdxdInfo(hdid,xh);
        if(SAVE.equals(status)){
            Map<String,Object> resultMap = new HashMap<>();
            String fj = request.getParameter("fj");
            String hdxd = DealString.toGBK(request.getParameter("hdxd"));
            HashMap<String,String> data = new HashMap<>();
            data.put("hdxd",hdxd);//活动心得
            data.put("fj",fj);//附件信息
            data.put("id",result.get("id"));//活动心得id
            data.put("tjsj", DateTranCnDate.timeStampToDate(System.currentTimeMillis()));//提交时间
            boolean flag  = dzzhdService.saveHdxd(data);
            if(flag){
                resultMap.put("code",1);
                resultMap.put("msg","提交成功!");
            }else{
                resultMap.put("code",0);
                resultMap.put("msg","提交失败!");
            }
            response.getWriter().print(JSON.parseObject(JSON.toJSONString(resultMap)));
            return null;
        }
        request.setAttribute("status",status);
        request.setAttribute("data",result);
        return mapping.findForward("hdXdEdit");
    }
}
