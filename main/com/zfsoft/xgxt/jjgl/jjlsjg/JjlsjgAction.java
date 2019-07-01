package com.zfsoft.xgxt.jjgl.jjlsjg;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.jjgl.jjnj.JjnjForm;
import com.zfsoft.xgxt.jjgl.jjnj.JjnjService;
import com.zfsoft.xgxt.jjgl.jjxk.JjxkForm;
import com.zfsoft.xgxt.jjgl.jjxk.JjxkService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.zyfwgl.zyfwjg.ZyfwJgForm;
import common.newp.StringUtil;
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
import xgxt.utils.date.DateUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 西交大：家教老师结果action.
 *
 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
 * @date 2018-11-19 16:42
 */
public class JjlsjgAction extends SuperAction<JjlsjgForm,JjlsjgService> {

    private final String JJGL = "jjgl";
    private JjlsjgService jjlsjgService = new JjlsjgService();
    private static final String url = "jjgl_jjlsjg.do";

    /**
     * 跳转到家教老师结果列表页面.
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throw Exception
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-11-20 14:16
     */
    @SystemAuth(url = url)
    public ActionForward jjlsjgList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        request.setAttribute("path", url);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("jjlsjgList");
    }

    /**
     * 获取家教老师结果列表JSON数据.
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throw Exception
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-11-20 14:21
     */
    @SystemAuth(url = url)
    public ActionForward getJjlsjgListData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {

        JjlsjgForm jjlsjgForm = (JjlsjgForm) form;

        // 生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        jjlsjgForm.setSearchModel(searchModel);
        User user = getUser(request);
        // 查询
        List<HashMap<String, String>> resultList = jjlsjgService.getPageList(jjlsjgForm, user);
        JSONArray dataList = JSONArray.fromObject(resultList);
        response.getWriter().print(dataList);
        return null;
    }

    /**
     * @描述:家教老师结果的查看
     * @作者：xuwen[工号：1426]
     * @日期：2017年5月9日 下午4:43:20
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
    public ActionForward jjlsjgShow(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        JjlsjgForm jjlsjgForm = (JjlsjgForm) form;
        JjlsjgForm model = jjlsjgService.getModel(jjlsjgForm.getXh());
        if(StringUtils.isNotNull(model.getXh())){
            BeanUtils.copyProperties(jjlsjgForm, StringUtils.formatData(model));
            // 加载学生基本信息
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
            request.setAttribute("jbxx", xsjbxx);
        }
        // 学生基本信息显示配置
        BdpzService bdpzService = new BdpzService();
        List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(JJGL);
        request.setAttribute("jbxxList", jbxxList);
        return mapping.findForward("jjlsjgShow");
    }

    /**
     * 跳转到家教老师结果增加页面.
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throw
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-11-20 17:58
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward jjlsjgAdd(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        JjlsjgForm jjlsjgForm = (JjlsjgForm) form;
        User user = getUser(request);
        if ("stu".equals(user.getUserType())) {
            jjlsjgForm.setXh(user.getUserName());
        }
        if (!StringUtil.isNull(jjlsjgForm.getXh())) {
            // 加载学生基本信息
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(jjlsjgForm.getXh());
            request.setAttribute("jbxx", xsjbxx);
        }

        String path = "jjgl_jjlsjggl.do?method=jjlsjgAdd";
        request.setAttribute("path", path);
        // 学生基本信息显示配置
        BdpzService bdpzService = new BdpzService();
        List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(JJGL);
        request.setAttribute("jbxxList", jbxxList);

        //家教年级
        List<HashMap<String,String>> jjnjList = new JjnjService().getAllList(new JjnjForm());
        request.setAttribute("jjnjList",jjnjList);

        //家教学科
        List<HashMap<String,String>> jjxkList = new JjxkService().getAllList(new JjxkForm());
        request.setAttribute("jjxkList",jjxkList);

        return mapping.findForward("jjlsjgAdd");
    }

    /**
     * @描述:家教老师结果（新增）的保存
     * @作者：xuwen[工号：1426]
     * @日期：2017年5月5日 下午3:22:06
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
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward jjlsjgSaveForAdd(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        JjlsjgForm jjlsjgForm = (JjlsjgForm) form;
        boolean isRepeat = jjlsjgService.isRepeat(jjlsjgForm);
        if (isRepeat) {
            response.getWriter().print(getJsonMessage("家教老师已存在"));
            return null;
        }
        jjlsjgForm.setDjr(getUser(request).getUserName());
        jjlsjgForm.setDjsj(DateUtils.getCurrTime());
        boolean result = jjlsjgService.runInsert(jjlsjgForm);
        String messageKey = result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     * @描述:转到家教老师结果修改弹框页面
     * @作者：xuwen[工号：1426]
     * @日期：2017年5月5日 下午2:04:19
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
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward jjlsjgEdit(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        JjlsjgForm jjlsjgForm = (JjlsjgForm) form;
        JjlsjgForm model = jjlsjgService.getModel(jjlsjgForm);
        if (StringUtils.isNotNull(model.getXh())) {
            BeanUtils.copyProperties(jjlsjgForm, StringUtils.formatData(model));
            // 加载学生基本信息
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
            request.setAttribute("jbxx", xsjbxx);
        }
        BdpzService bdpzService = new BdpzService();
        List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(JJGL);
        request.setAttribute("jbxxList", jbxxList);

        //家教年级
        List<HashMap<String,String>> jjnjList = new JjnjService().getAllList(new JjnjForm());
        request.setAttribute("jjnjList",jjnjList);

        //家教学科
        List<HashMap<String,String>> jjxkList = new JjxkService().getAllList(new JjxkForm());
        request.setAttribute("jjxkList",jjxkList);

        //处理擅长科目
        String [] sckm = jjlsjgForm.getSckm();
        if(sckm != null && sckm.length != 0){
            List<String> sckmList = Arrays.asList(sckm);
            for(HashMap<String,String> jjxk:jjxkList){
                if(sckmList.contains(jjxk.get("jjxkmc"))){
                    jjxk.put("checked","1");
                }
            }
        }

        String path = "jjgl_jjlsjggl.do?method=jjlsjgEdit";
        request.setAttribute("path", path);
        return mapping.findForward("jjlsjgEdit");
    }

    /**
     * @描述:家教老师结果（修改）的保存
     * @作者：xuwen[工号：1426]
     * @日期：2017年5月5日 下午3:22:06
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
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward jjlsjgSaveForEdit(ActionMapping mapping, ActionForm form,
                                           HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        JjlsjgForm jjlsjgForm = (JjlsjgForm) form;

        boolean result = jjlsjgService.runUpdate(jjlsjgForm);
        String messageKey = result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     * @描述:家教老师结果的删除（可批量）
     * @作者：xuwen[工号：1426]
     * @日期：2017年5月9日 上午9:50:05
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
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward jjlsjgDel(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception {

        String values = request.getParameter("values");
        if (!StringUtil.isNull(values)) {
            String[] ids = values.split(",");
            int num = jjlsjgService.runDelete(ids);
            boolean result = num > 0;
            String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
            response.getWriter().print(getJsonMessage(message));
        } else {
            throw new SystemException(MessageKey.SYS_DEL_NULL);
        }
        return null;
    }

    /**
     * @描述:家教老师结果的导出
     * @作者：xuwen[工号：1426]
     * @日期：2017年5月9日 下午2:22:44
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
    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {

        JjlsjgForm jjlsjgForm = (JjlsjgForm) form;
        String dcclbh = request.getParameter("dcclbh");

        // 生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        jjlsjgForm.setSearchModel(searchModel);
        User user = getUser(request);
        List<HashMap<String, String>> resultList = jjlsjgService.getAllList(jjlsjgForm,user);// 查询出所有记录，不分页
        // 导出功能代码
        IExportService exportService = new ExportExcelImpl();
        ExportModel exportModel = jjlsjgForm.getExportModel();
        exportModel.setZgh(user.getUserName());// 当前操作员
        exportModel.setDataList(resultList);// 设置数据
        exportModel.setDcclbh(dcclbh);// 设置当前导出功能编号
        File file = exportService.getExportFile(exportModel);// 生成导出文件
        FileUtil.outputExcel(response, file);
        return null;
    }
}
