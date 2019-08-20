package com.zfsoft.xgxt.xyfd.pbwh.pbjg;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.dtjs.llxxjl.jg.LlxxjljgForm;
import com.zfsoft.xgxt.dtjs.llxxjl.jg.LlxxjljgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xyfd.fdswh.FdsForm;
import com.zfsoft.xgxt.xyfd.fdswh.FdsService;
import com.zfsoft.xgxt.xyfd.pbwh.pbsq.PbsqForm;
import com.zfsoft.xgxt.xyfd.pbwh.pbsq.PbsqService;
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
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * @类功能描述:
 * @作者： llf [工号:1754]
 * @时间： 2019-08-01 14:36
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class PbjgAction extends SuperAction<PbjgForm, PbjgService> {
    private PbjgService service = new PbjgService();
    private PbsqService pbsqService = new PbsqService();
    private FdsService fdsService = new FdsService();

    private static final String url = "xyfd_xyfd_pbjg.do";

    /**
     * 朋辈志愿者结果列表
     */
    @SystemAuth(url = url)
    public ActionForward pbjgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PbjgForm model = (PbjgForm) form;
        if (QUERY.equalsIgnoreCase(model.getType())) {
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            // 查询
            List<HashMap<String, String>> resultList = service.getPageList(model, user);

            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        String path = "xyfd_xyfd_pbjg.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("pbjgList");
    }
    /**
     * 朋辈志愿者结果增加
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward addPbjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PbjgForm model = (PbjgForm) form;
        User user = getUser(request);
        PbsqForm pbsqForm = new PbsqForm();
        if ("stu".equals(user.getUserType())) {
            throw new Exception("没有权限");
        }
        if(!StringUtil.isNull(model.getXh())){
            pbsqForm.setXh(model.getXh());
            HashMap<String,String> zypmlist = pbsqService.getZypmlist(pbsqForm,new User());//专业排名信息
            List<HashMap<String,String>> jlxxlist = pbsqService.getJlxx(pbsqForm);//奖助学金及表彰奖励
            HashMap<String,String> xsxxlist = pbsqService.getXsxx(pbsqForm);//学生信息
            request.setAttribute("zypmlist",zypmlist);
            StringBuilder jlxx = new StringBuilder();
            for(int i=0;i<jlxxlist.size();i++){
                jlxx.append(jlxxlist.get(i).get("xmmc")+" ");
            }
            request.setAttribute("jlxx",jlxx);
            request.setAttribute("xsxxlist",xsxxlist);
        }

        String path = "xyfd_pbjg.do?method=addPbjg";
        request.setAttribute("path", path);
        return mapping.findForward("addPbjg");
    }
    /**
     * 朋辈志愿者结果查看
     */
    @SystemAuth(url = url)
    public ActionForward viewPbjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PbjgForm model = (PbjgForm)form;
        HashMap<String,String> pbjsxx = service.getPbjs(model);
        BeanUtils.copyProperties(model,pbjsxx);
        PbsqForm pbsqForm = new PbsqForm();
        if(!StringUtil.isNull(model.getXh())) {
            pbsqForm.setXh(model.getXh());

            //==================专业排名信息=====================//
            HashMap<String, String> zypmlist = pbsqService.getZypmlist(pbsqForm, new User());
            request.setAttribute("zypmlist", zypmlist);
            //======================学生信息=====================//
            HashMap<String, String> xsxxlist = pbsqService.getXsxx(pbsqForm);
            request.setAttribute("xsxxlist", xsxxlist);
            //=================奖助学金及表彰奖励====================//
            List<HashMap<String, String>> jlxxlist = pbsqService.getJlxx(pbsqForm);
            StringBuilder jlxx = new StringBuilder();
            for (int i = 0; i < jlxxlist.size(); i++) {
                jlxx.append(jlxxlist.get(i).get("xmmc") + " ");
            }
            request.setAttribute("jlxx", jlxx);
            //======================辅导室信息=====================//
            FdsForm fdsForm = new FdsForm();
            fdsForm.setId(model.getFds());
            HashMap<String, String> fdsxx = fdsService.getFds(fdsForm);
            request.setAttribute("fdsxx", fdsxx);

        }
        request.setAttribute("rs", xgxt.utils.String.StringUtils.formatData(model));
        return mapping.findForward("viewPbjg");
    }
    /**
     * 朋辈志愿者结果保存
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PbjgForm model = (PbjgForm) form;
        User user = getUser(request);
        boolean result = false;
        model.setMon(org.apache.commons.lang.StringUtils.join(request.getParameterValues("mond"),","));
        model.setTues(org.apache.commons.lang.StringUtils.join(request.getParameterValues("tuesd"),","));
        model.setWed(org.apache.commons.lang.StringUtils.join(request.getParameterValues("wedd"),","));
        model.setThur(org.apache.commons.lang.StringUtils.join(request.getParameterValues("thurd"),","));
        model.setFri(org.apache.commons.lang.StringUtils.join(request.getParameterValues("frid"),","));
        model.setSat(org.apache.commons.lang.StringUtils.join(request.getParameterValues("satd"),","));
        model.setSun(org.apache.commons.lang.StringUtils.join(request.getParameterValues("sund"),","));
        if("add".equals(model.getType())){
            String maxDjh = service.getDjh();
            int num = 0;
            String yearLast = new SimpleDateFormat("yy", Locale.CHINESE).format(Calendar.getInstance().getTime());
            if(org.apache.commons.lang.StringUtils.isNotEmpty(maxDjh)&&maxDjh.substring(2,4).equals(yearLast)){
                num = Integer.parseInt(maxDjh.substring(2,maxDjh.length()))+1;
            }else {
                num = Integer.parseInt(yearLast+"0001");
            }
            model.setDjh("PB"+num);    //生成登记号
            model.setLrr(user.getUserName());
            model.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
            model.setSjly("0");
            result = service.runInsert(model);
        }else{
            result = service.runUpdate(model);
        }
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
                : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }
    /**
     * 朋辈志愿者结果修改
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward updatePbjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PbjgForm model = (PbjgForm) form;
        model = service.getModel(model);
        PbsqForm pbsqForm = new PbsqForm();
        if(!StringUtil.isNull(model.getXh())){
            pbsqForm.setXh(model.getXh());
            HashMap<String,String> zypmlist = pbsqService.getZypmlist(pbsqForm,new User());//专业排名信息
            List<HashMap<String,String>> jlxxlist = pbsqService.getJlxx(pbsqForm);//奖助学金及表彰奖励
            HashMap<String,String> xsxxlist = pbsqService.getXsxx(pbsqForm);//学生信息
            request.setAttribute("zypmlist",zypmlist);
            StringBuilder jlxx = new StringBuilder();
            for(int i=0;i<jlxxlist.size();i++){
                jlxx.append(jlxxlist.get(i).get("xmmc")+" ");
            }
            request.setAttribute("jlxx",jlxx);
            request.setAttribute("xsxxlist",xsxxlist);
            FdsForm fdsForm = new FdsForm();
            fdsForm.setId(model.getFds());
            HashMap<String,String> fdsxx = fdsService.getFds(fdsForm);
            request.setAttribute("fdsxx",fdsxx);//辅导室信息
        }else {
            throw new Exception("无学号");
        }
        request.setAttribute("pbxxlist",model);
        return mapping.findForward("updatePbjg");
    }
    /**
     * 朋辈志愿者结果删除
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward del(ActionMapping mapping, ActionForm form,
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
    /**
     * 朋辈志愿者结果导出
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PbjgForm model = (PbjgForm) form;
        // 生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);
        User user = getUser(request);
        // 查询
        List<HashMap<String, String>> resultList = service.getAllList(model,user);// 查询出所有记录，不分页
        // 导出功能代码
        IExportService exportService = new ExportExcelImpl();
        ExportModel exportModel = model.getExportModel();
        exportModel.setZgh(user.getUserName());// 当前操作员
        exportModel.setDataList(resultList);// 设置数据
        exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
        File file = exportService.getExportFile(exportModel);// 生成导出文件
        FileUtil.outputExcel(response, file);
        return null;
    }

}
