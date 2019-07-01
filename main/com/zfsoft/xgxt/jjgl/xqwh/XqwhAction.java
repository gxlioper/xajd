/**
 * @部门:学工产品事业部
 * @日期：2014-8-27 下午04:22:43
 */
package com.zfsoft.xgxt.jjgl.xqwh;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.jjgl.jjnj.JjnjForm;
import com.zfsoft.xgxt.jjgl.jjnj.JjnjService;
import com.zfsoft.xgxt.jjgl.jjxk.JjxkForm;
import com.zfsoft.xgxt.jjgl.jjxk.JjxkService;
import com.zfsoft.xgxt.jjgl.jjxq.JjxqService;
import com.zfsoft.xgxt.jjgl.jjzg.JjzgService;
import com.zfsoft.xgxt.jjgl.zcyh.ZcyhService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.date.DateUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-8-27 下午04:22:43 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XqwhAction extends SuperAction<XqwhForm, XqwhService> {

    /**
     *  @属性： PATH 路径
     */
    private static final String PATH = "jjgl_xqwh.do";

    private static final String url = "jjgl_xqwh.do";

    /**
     *
     * @描述:需求审核页面
     * @作者：张小彬[工号：1036]
     * @日期：2014-8-26 下午06:43:32
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * ActionForward 返回类型
     * @throws
     */
    @SystemAuth(url = url)
    public ActionForward xqwhCx(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        User user = getUser(request);

        if ("stu".equalsIgnoreCase(user.getUserType())) {
            String msg = "该模块不允户访问，请确认！";
            request.setAttribute("yhInfo", msg);
            return new ActionForward("/yhInfo.do", false);
        }
        //家教年级列表
        JjnjService jjnjService = new JjnjService();
        List<HashMap<String, String>> jjnjList = jjnjService.getJjnjList();
        request.setAttribute("jjnjList", jjnjList);

        //家教学科列表
        JjxkService jjxkService = new JjxkService();
        List<HashMap<String, String>> jjxkList = jjxkService.getJjxkList();
        request.setAttribute("jjxkList", jjxkList);
        // 获取用户（是否可写）权限  和 title
        request.setAttribute("path", PATH);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("xqwhCx");
    }


    /**
     *
     * @描述:查询家教需求列表
     * @作者：张小彬[工号：1036]
     * @日期：2014-8-27 上午09:43:49
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * ActionForward 返回类型
     * @throws
     */
    @SystemAuth(url = url)
    public ActionForward queryXqList(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XqwhForm model = (XqwhForm) form;

        //查询
        List<HashMap<String, String>> resultList = getService().getPageList(model);
        if (resultList == null) {
            resultList = new ArrayList<>();
        }
        JSONArray dataList = JSONArray.fromObject(resultList);
        response.getWriter().print(dataList);

        return null;
    }

    /**
     *
     * @描述:分配家教老师
     * @作者：张小彬[工号：1036]
     * @日期：2014-8-28 上午10:35:13
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * ActionForward 返回类型
     * @throws
     */
    @SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
    public ActionForward assign(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        XqwhForm model = (XqwhForm) form;
        //需求id参数
        String xqid = model.getXqid();
        HashMap<String, String> xqModelMap = null;
        HashMap<String, String> znxxMap = null;
        List<HashMap<String, String>> xqXsSqList = null;
        //家教需求信息
        if (StringUtils.isNotBlank(xqid)) {
            xqModelMap = new JjxqService().getModelMap(xqid);
        }
        //子女信息
        if (xqModelMap != null && StringUtils.isNotBlank(xqModelMap.get("znid"))) {
            znxxMap = new ZcyhService().getZnxxMapById(xqModelMap.get("znid"));
        }
        //申请人列表
        if (StringUtils.isNotBlank(xqid)) {
            xqXsSqList = getService().getXqXsSqList(xqid);
        }
        request.setAttribute("xqXsSqList", xqXsSqList);
        request.setAttribute("znxxMap", xgxt.utils.String.StringUtils.formatData(znxxMap));
        request.setAttribute("xqModelMap", xgxt.utils.String.StringUtils.formatData(xqModelMap));
        return mapping.findForward("assign");
    }

    /**
     *
     * @描述:家教状态提交
     * @作者：张小彬[工号：1036]
     * @日期：2014-8-28 上午10:35:13
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * ActionForward 返回类型
     * @throws
     */
    @SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
    public ActionForward changeJJzt(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        XqwhForm model = (XqwhForm) form;
        String xqid = model.getXqid();
        //查询需求信息
        if (StringUtils.isNotBlank(xqid)) {
            HashMap<String, String> xqxxDetailsByXqid = getService().getXqxxDetailsByXqid(xqid);
            request.setAttribute("xqxxMap", xgxt.utils.String.StringUtils.formatData(xqxxDetailsByXqid));
        }
        return mapping.findForward("xgzt");
    }

    /**
     *
     * @描述:修改家教状态提交
     * @作者：张小彬[工号：1036]
     * @日期：2014-8-28 上午10:35:13
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * ActionForward 返回类型
     * @throws
     */
    @SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
    public ActionForward changeJJztSubmit(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        XqwhForm model = (XqwhForm) form;
        //执行
        HashMap<String, String> assign = getService().changeJjzt(model);
        JSONObject message = JSONObject.fromMap(assign);
        response.getWriter().print(message);
        return null;
    }


    /**
     *
     * @描述:手工分配家教老师
     * @作者：张小彬[工号：1036]
     * @日期：2014-8-28 上午10:35:13
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * ActionForward 返回类型
     * @throws
     */
    @SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
    public ActionForward assignManual(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        XqwhForm model = (XqwhForm) form;
        //需求id参数
        String xqid = model.getXqid();
        HashMap<String, String> jjlsxxMap = null;
        Map<String, String> xqwhMap = getService().getXqwhMap(xqid);
        request.setAttribute("xqwhMap", xgxt.utils.String.StringUtils.formatData(xqwhMap));
        //申请人信息
        if (StringUtils.isNotBlank(model.getXh())) {
            jjlsxxMap = new JjzgService().getModelMap(model.getXh());
        }
        request.setAttribute("jjlsxxMap", xgxt.utils.String.StringUtils.formatData(jjlsxxMap));
        //默认高级查询条件
        SearchModel searchModel = new SearchModel();
        request.setAttribute("searchTj", searchModel);
        request.setAttribute("path", URLEncoder.encode("jjgl_xqwhgl.do?method=assignManual&xqid=" + xqid, "gbk"));
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("assignManual");
    }


    /**
     *
     * @描述:手工分配家教老师提交
     * @作者：张小彬[工号：1036]
     * @日期：2014-8-28 上午10:35:13
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * ActionForward 返回类型
     * @throws
     */
    @SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
    public ActionForward assignManualSubmit(ActionMapping mapping, ActionForm form,
                                            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        XqwhForm model = (XqwhForm) form;
        //执行
        HashMap<String, String> assign = getService().assignManual(model);
        JSONObject message = JSONObject.fromMap(assign);
        response.getWriter().print(message);
        return null;
    }


    /**
     *
     * @描述:提交分配家教老师
     * @作者：张小彬[工号：1036]
     * @日期：2014-8-28 下午03:25:02
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * ActionForward 返回类型
     * @throws
     */
    @SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
    public ActionForward assignSubmit(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        XqwhForm model = (XqwhForm) form;
        //执行
        HashMap<String, String> assign = getService().assign(model);
        JSONObject message = JSONObject.fromMap(assign);
        response.getWriter().print(message);
        return null;
    }


    /**
     *
     * @描述:查看投诉信息
     * @作者：张小彬[工号：1036]
     * @日期：2014-8-27 上午09:43:49
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * ActionForward 返回类型
     * @throws
     */
    @SystemAuth(url = url)
    public ActionForward viewTsxx(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XqwhForm model = (XqwhForm) form;
        if (StringUtils.isNotBlank(model.getXqid())) {
            //查询
            List<HashMap<String, String>> tsfkxxList = getService().getTsxxList(model.getXqid());
            request.setAttribute("tsfkxxList", tsfkxxList);
        }

        return mapping.findForward("tsfk");
    }

    /**
     *
     * @描述:投诉反馈提交
     * @作者：张小彬[工号：1036]
     * @日期：2014-8-30 上午10:45:06
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * ActionForward 返回类型
     * @throws
     */
    @SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
    public ActionForward tsfkSubmit(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        XqwhForm model = (XqwhForm) form;
        model.setFksj(DateUtils.getCurrTime());
        JSONObject message = null;
        boolean isSuccess = getService().updateTsxx(model);
        message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
        response.getWriter().print(message);
        return null;
    }


    /**
     *
     * @描述:查看评价信息
     * @作者：张小彬[工号：1036]
     * @日期：2014-8-27 上午09:43:49
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * ActionForward 返回类型
     * @throws
     */
    @SystemAuth(url = url)
    public ActionForward viewPjxx(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XqwhForm model = (XqwhForm) form;
        if (StringUtils.isNotBlank(model.getXqid())) {
            //查询
            HashMap<String, String> pjxxModel = getService().getPjxxList(model.getXqid());
            request.setAttribute("pjxxModel", xgxt.utils.String.StringUtils.formatData(pjxxModel));
        }

        return mapping.findForward("ckpj");
    }


    /**
     *
     * @描述:查看
     * @作者：张小彬[工号：1036]
     * @日期：2014-8-28 上午10:35:13
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * ActionForward 返回类型
     * @throws
     */
    @SystemAuth(url = url)
    public ActionForward ck(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        XqwhForm xqwhForm = (XqwhForm) form;
        //需求id参数
        String xqid = xqwhForm.getXqid();

        Map<String, String> xqwhMap = getService().getXqwhMap(xqid);
        request.setAttribute("xqwhMap", xqwhMap);

        return mapping.findForward("ck");
    }

    /**
     * 家教需求增加页面.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
    public ActionForward xqwhAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        //登记人
        request.setAttribute("djr", getUser(request).getRealName());

        //家教年级列表
        JjnjService jjnjService = new JjnjService();
        List<HashMap<String, String>> jjnjList = jjnjService.getJjnjList();
        request.setAttribute("jjnjList", jjnjList);

        //家教学科列表
        JjxkService jjxkService = new JjxkService();
        List<HashMap<String, String>> jjxkList = jjxkService.getJjxkList();
        request.setAttribute("jjxkList", jjxkList);
        return mapping.findForward("xqwhAdd");
    }

    /**
     * 家教需求修改页面.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
    public ActionForward xqwhEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        XqwhForm xqwhForm = (XqwhForm) form;
        //需求id参数
        String xqid = xqwhForm.getXqid();

        Map<String, String> xqwhMap = getService().getXqwhMap(xqid);
        request.setAttribute("xqwhMap", xqwhMap);

        //子女信息
        List<HashMap<String, String>> znxxList = new ZcyhService().getZnxxListByPid(xqwhMap.get("sqr"));
        request.setAttribute("znxxList", znxxList);

        //家教年级列表
        JjnjService jjnjService = new JjnjService();
        List<HashMap<String, String>> jjnjList = jjnjService.getAllList(new JjnjForm());
        request.setAttribute("jjnjList", jjnjList);

        //家教学科列表
        JjxkService jjxkService = new JjxkService();
        List<HashMap<String, String>> jjxkList = jjxkService.getAllList(new JjxkForm());
        request.setAttribute("jjxkList", jjxkList);
        return mapping.findForward("xqwhEdit");
    }

    /**
     * 家教需求删除.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
    public ActionForward xqwhDel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String values = request.getParameter("values");
        if (!StringUtil.isNull(values)) {
            int num = getService().xqwhDel(values.split(","));
            boolean result = num > 0;
            String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num)
                    : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
            response.getWriter().print(getJsonMessage(message));
        } else {
            throw new SystemException(MessageKey.SYS_DEL_NULL);
        }
        return null;
    }

    /**
     * 跳转到选择家长信息列表页面.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showJzxxList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return mapping.findForward("showJzxxList");
    }

    /**
     * 家教需求（增加）的保存.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
    public ActionForward xqwhSaveForAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        XqwhForm xqwhForm = (XqwhForm) form;
        boolean result = getService().xqwhSaveForAdd(xqwhForm);
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     * 家教需求（修改）的保存.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
    public ActionForward xqwhSaveForEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        XqwhForm xqwhForm = (XqwhForm) form;
        boolean result = getService().xqwhSaveForEdit(xqwhForm);
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    public ActionForward downXys(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getSession().getServletContext().getRealPath(
                "/temp/mb/")+"/jjxys.doc";
        logger.info("path = "+path);
        if (xgxt.utils.String.StringUtils.isNotNull(path)){
            File file = new File(path);
            if (file.exists()){
                response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode("jjxys.doc","utf-8"));
                FileUtil.outputFile(response, file);
            }
        }
        return null;
    }

}
