package com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjsjg;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjshb.GrxfjshbService;
import com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjshb.GrxfjshbjgForm;
import com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjshb.GrxfjshbjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
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
import java.util.*;

/**
 * @类功能描述:个人学风建设结果
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-26 16:06
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class GrxfjsjgAction extends SuperAction<GrxfjsjgForm,GrxfjsjgService> {
    private  GrxfjsjgService service = new GrxfjsjgService();

    private static final String url = "sxzzjy_grxfjs_grxfjsjg.do";

    private static final String GRXFJS = "grxfjs";
    private static List<HashMap<String, String>> jbxxList = null;
    static {
        BdpzService bdpzService = new BdpzService();
        // 学生基本信息显示配置
        jbxxList = bdpzService.getJbxxpz(GRXFJS);
    }


    @SystemAuth(url = url)
    public ActionForward getList(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GrxfjsjgForm model = (GrxfjsjgForm) form;
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
        String path = "sxzzjy_grxfjs_grxfjsjg.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("grxfjsjgList");
    }
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward grxfjsjgAdd(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GrxfjsjgForm model = (GrxfjsjgForm) form;
        User user = getUser(request);
        if ("stu".equals(user.getUserType())){
            model.setXh(user.getUserName());
        }
        if (!StringUtil.isNull(model.getXh())) {
            // 加载学生基本信息
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
            request.setAttribute("jbxx", xsjbxx);
        }
        //学生基本信息显示配置
        request.setAttribute("jbxxList", jbxxList);
        String path = "sxzzjy_grxfjsjg.do?method=grxfjsjgAdd";
        request.setAttribute("path", path);
        List<HashMap<String,String>> sblxList = service.getAllSblx();
        request.setAttribute("sblxList",sblxList);
        model.setXn(Base.currXn);
        model.setXq(Base.currXq);
        String xnxq = Base.currXn+Base.dqxqmc;
        request.setAttribute("xnxq",xnxq);
        return mapping.findForward("grxfjsjgAdd");
    }
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward grxfjsjgUpdate(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GrxfjsjgForm model = (GrxfjsjgForm) form;
        User user = getUser(request);
        List<HashMap<String,String>> sblxList = service.getAllSblx();
        request.setAttribute("sblxList",sblxList);
        HashMap<String,String> jgmap = service.getGrxfjsjgInfo(model);
        BeanUtils.copyProperties(model, StringUtils.formatData(jgmap));
        if (!StringUtil.isNull(model.getXh())) {
            // 加载学生基本信息
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
            request.setAttribute("jbxx", xsjbxx);
        }
        //学生基本信息显示配置
        request.setAttribute("jbxxList", jbxxList);
        String path = "sxzzjy_grxfjsjg.do?method=grxfjsjgUpdate";
        request.setAttribute("path", path);

        request.setAttribute("map",jgmap);
        request.setAttribute("sjly",jgmap.get("sjly"));
        return mapping.findForward("grxfjsjgUpdate");
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    @SystemLog(description="思想政治教育管理-个人学风建设管理-个人学分建设结果-保存 JGID:{jgid},XH:{xh},XFJSMC:{xfjsmc},SBLX:{sblx},BXNMB:{bxnmb},JSSL:{jssl}")
    public ActionForward grxfjsjgSave(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GrxfjsjgForm model = (GrxfjsjgForm) form;
        User user = getUser(request);
        boolean result = false;
        model.setLrsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));//录入时间
        model.setLrr(user.getUserName());//录入人
        boolean isExist = false;
        isExist = service.isExist(model);
        if (!isExist) {
            if ("add".equals(model.getType())) {
                model.setSjly("0");//数据来源
                result = service.runInsert(model);
            }
            if ("update".equals(model.getType())) {
                result = service.runUpdate(model);
            }
            String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(messageKey));
        }else{
            String message = MessageUtil.getText(MessageKey.RCSW_YLBX_SQ_REPEAT) ;
            response.getWriter().print(getJsonMessage(message));
        }

        return null;
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    @SystemLog(description="思想政治教育管理-个人学风建设管理-个人学分建设结果-删除 VALUES:{values}")
    public ActionForward grxfjsjgDel(ActionMapping mapping, ActionForm form,
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

    public ActionForward grxfjsjgView(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GrxfjsjgForm model = (GrxfjsjgForm) form;
        HashMap<String,String> map = service.getGrxfjsjgInfo(model);
        if (!StringUtil.isNull(map.get("xh"))) {
            // 加载学生基本信息
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(map.get("xh"));
            request.setAttribute("jbxx", xsjbxx);
        }
        //学生基本信息显示配置
        request.setAttribute("jbxxList", jbxxList);
        String path = "sxzzjy_grxfjsjg.do?method=grxfjsjgView";
        request.setAttribute("path", path);
        GrxfjshbjgService gservice = new GrxfjshbjgService();
        GrxfjshbjgForm gform = new GrxfjshbjgForm();
        gform.setJgid(model.getJgid());
        gform.setHblx("nzhb");
        HashMap<String,String> nzhbMap = gservice.getInfo(gform);
        gform.setHblx("nzzj");
        HashMap<String,String> nzzjMap = gservice.getInfo(gform);
        request.setAttribute("nzzjMap",nzzjMap);
        request.setAttribute("nzhbMap",nzhbMap);

        request.setAttribute("map",map);
        return mapping.findForward("grxfjsjgView");
    }


    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GrxfjsjgForm model = (GrxfjsjgForm) form;
        // 生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);
        User user = getUser(request);
        model.getPages().setPageSize(Integer.MAX_VALUE);
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

    public ActionForward getPrint(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String jgid =  request.getParameter("jgid");
        String hblx =  request.getParameter("hblx");
        File wordFile = getWord(jgid,hblx);
        FileUtil.outputWord(response, wordFile);
        return null;
    }

    /**
     * @描述:
     * @作者：lgx [工号：1553]
     * @日期：2018/7/31 15:33
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [mapping, form, request, response]
     * @return: org.apache.struts.action.ActionForward
     */
    public ActionForward getPrintZip(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String value = request.getParameter("value");
        String hblx = request.getParameter("hblx");
        if (StringUtils.isNotNull(value)){
            String[] values = value.split(",");
            List<File> files = new ArrayList<File>();
            for (int i = 0 , n = values.length ; i < n ; i++){
                File file = getWord(values[i],hblx);
                files.add(file);
            }

            File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
            FileUtil.outputZip(response, zipFile);
        }
        return null;
    }

    private File getWord (String jgid,String hblx) throws Exception {
        GrxfjshbjgService gservice = new GrxfjshbjgService();
        HashMap<String,String> map = gservice.getWordInfo(jgid,hblx);
        GrxfjsjgForm model = new GrxfjsjgForm();
        model.setJgid(jgid);
        HashMap<String,String> jgMap = service.getGrxfjsjgInfo(model);
        XsxxService xsxxService = new XsxxService();
        HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(jgMap.get("xh"));

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("hbMap",map);
        data.put("model",jgMap);
        data.putAll(xsjbxx);
        String tbrq =new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
        data.put("tbrq",tbrq);
        File file = null;
        if("nzhb".equals(hblx)){
            file = FreeMarkerUtil.getWordFile(data,"classpath://templates//sxzzjygl",
                    "grnzhb_10699.xml",FreeMarkerUtil.getFileName(xsjbxx.get("xh")+"_"+"中期汇报"));
        }
        if("nzzj".equals(hblx)){
            file = FreeMarkerUtil.getWordFile(data,"classpath://templates//sxzzjygl",
                    "grnzzj_10699.xml",FreeMarkerUtil.getFileName(xsjbxx.get("xh")+"_"+"年终总结"));
        }
        if("sq".equals(hblx)){
            file = FreeMarkerUtil.getWordFile(data,"classpath://templates//sxzzjygl",
                    "grxfjssq_10699.xml",FreeMarkerUtil.getFileName(xsjbxx.get("xh")+"_"+"申请"));
        }
        return file;
    }
}
