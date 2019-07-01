package com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjshb;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.*;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjsjcsz.GrxfjsjcszForm;
import com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjsjcsz.GrxfjsjcszService;
import com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjsjg.GrxfjsjgForm;
import com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjsjg.GrxfjsjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-08-02 17:24
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class GrxfjshbAction extends SuperAction<GrxfjshbForm,GrxfjshbService> {
    private static final String url = "sxzzjy_grxfjs_grxfjshb.do";
    private GrxfjshbService service = new GrxfjshbService();
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
        GrxfjshbForm model = (GrxfjshbForm) form;
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
        GrxfjsjcszService grxfjsjcszService = new GrxfjsjcszService();
        GrxfjsjcszForm jcszModel = grxfjsjcszService.getModel();
        request.setAttribute("jcszModel",jcszModel);
        String path = "sxzzjy_grxfjs_grxfjshb.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("grxfjshbList");
    }

    public ActionForward grxfjshbAdd(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GrxfjshbForm model = (GrxfjshbForm) form;
        User user = getUser(request);
        if (SAVE.equalsIgnoreCase(model.getType())
                || SUBMIT.equalsIgnoreCase(model.getType())) {
            /*if (!isTokenValid(request)){
                response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
                return null;
            }*/
            boolean isExist = false;
            isExist = service.isExist(model);
            if (!isExist) {
                super.resetToken(request);
                // 添加
                String sqid = UniqID.getInstance().getUniqIDHash();
                model.setSqid(sqid);
                model.setSqr(user.getUserName());
                model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
                boolean result = service.saveGrxfjshb(model);
                String messageKey = "";
                if (SAVE.equalsIgnoreCase(model.getType())) {
                    messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
                } else {
                    messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
                }
                response.getWriter().print(getJsonMessageByKey(messageKey));
                return null;
            } else {
                String message = MessageUtil.getText(MessageKey.RCSW_YLBX_SQ_REPEAT) ;
                response.getWriter().print(getJsonMessage(message));
                return null;
            }
        }
        // 加载学生基本信息
        XsxxService xsxxService = new XsxxService();
        HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
        request.setAttribute("jbxx", xsjbxx);
        //学生基本信息显示配置
        request.setAttribute("jbxxList", jbxxList);
        request.setAttribute("hblx",model.getHblx());
        return mapping.findForward("grxfjshbAdd");
    }

    public ActionForward grxfjshbUpdate(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GrxfjshbForm model = (GrxfjshbForm) form;
        User user = getUser(request);
        if (SAVE.equalsIgnoreCase(model.getType())
                || SUBMIT.equalsIgnoreCase(model.getType())) {
            boolean isExist = false;
            model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
            isExist = service.isExist(model);
            if (!isExist) {
                boolean result = service.updateYlbxsq(model);
                String messageKey = "";
                if (SAVE.equalsIgnoreCase(model.getType())) {
                    messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
                } else {
                    messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
                }
                response.getWriter().print(getJsonMessageByKey(messageKey));
                return null;
            } else {
                String message = MessageUtil.getText(MessageKey.RCSW_YLBX_SQ_REPEAT) ;
                response.getWriter().print(getJsonMessage(message));
                return null;
            }
        }
        // 加载学生基本信息
        XsxxService xsxxService = new XsxxService();
        HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
        request.setAttribute("jbxx", xsjbxx);
        //学生基本信息显示配置
        request.setAttribute("jbxxList", jbxxList);
        GrxfjshbForm myform = service.getModel(model);
        BeanUtils.copyProperties(model, StringUtils.formatData(myform));

        return mapping.findForward("grxfjshbUpdate");
    }
    public ActionForward selectHblx(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GrxfjshbForm model = (GrxfjshbForm) form;
        /*String jgids = request.getParameter("values");
        request.setAttribute("jgids",jgids);*/
        request.setAttribute("type",model.getType());
        return mapping.findForward("selectHblx");
    }
    public ActionForward grxfjshbDel(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String jgids = request.getParameter("values");
        String hblxs = request.getParameter("hblxs");
        if (!StringUtil.isNull(jgids)) {
            // 删除
            int num = service.grxfjshbDel(jgids.split(","),hblxs.split(","));
            Map<String, String> map = new HashMap<String, String>();
            map.put("num", num + "");
            JSONObject json = JSONObject.fromObject(map);
            response.getWriter().print(json);
        } else {
            throw new SystemException(MessageKey.SYS_DEL_NULL);
        }
        return null;
    }
    public ActionForward grxfjshbSubmit(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GrxfjshbForm model = (GrxfjshbForm) form;
        String[] values = request.getParameter("values").split(",");
        boolean result=false;
        for (String value:values) {
            String[] arr = value.split("_");
            model.setHblx(arr[0]);
            model.setSqid(arr[1]);
            GrxfjshbForm myForm = service.getModel(model);
            result = service.grxfjshbSubmit(myForm);
            if (!result){
                String messageKey = MessageKey.SYS_SUBMIT_FAIL;
                response.getWriter().print(getJsonMessageByKey(messageKey));
                return null;
            }
        }
        String messageKey = MessageKey.SYS_SUBMIT_SUCCESS ;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    public ActionForward grxfjshbCancel(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GrxfjshbForm model = (GrxfjshbForm) form;
        String[] splcs = request.getParameter("splcs").split(",");
        String[] sqids = request.getParameter("sqids").split(",");
        boolean result=false;
        for (int i=0;i<sqids.length;i++) {
            model.setSqid(sqids[i]);
            model.setSplc(splcs[i]);
            // 只有刚提交并且第一级未审核的前提下，申请人可以撤销
            result = service.cancelRecord(sqids[i], splcs[i]);
            if (result) {
                // 更新业务状态为'未提交'
                ShlcDao shlcdao = new ShlcDao();
                if(Integer.valueOf(shlcdao.getExistTh(model.getSqid()))>0){
                    model.setShzt(Constants.YW_YTH);
                }else{
                    model.setShzt(Constants.YW_WTJ);
                }
                service.grxfjshbCancel(model);
            }else{
                String messageKey = MessageKey.SYS_CANCEL_FAIL;
                response.getWriter().print(getJsonMessageByKey(messageKey));
                return null;
            }
        }
        String messageKey = MessageKey.SYS_CANCEL_SUCCESS ;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }
    public ActionForward grxfjshbView(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GrxfjshbForm model = (GrxfjshbForm) form;
        HashMap<String, String> nzhbMap = new HashMap<String, String>();
        HashMap<String, String> nzzjMap = new HashMap<String, String>();
        //审核时查看
        if(!StringUtils.isNull(model.getHblx())){
            if("nzhb".equals(model.getHblx())){
                nzhbMap = service.getInfo(model);
            }else{
                nzzjMap = service.getInfo(model);
            }
        }else{
            model.setHblx("nzhb");
            nzhbMap = service.getInfo(model);
            model.setHblx("nzzj");
            nzzjMap = service.getInfo(model);
        }
        request.setAttribute("nzzjMap",nzzjMap);
        request.setAttribute("nzhbMap",nzhbMap);
        request.setAttribute("sfNzhb",nzhbMap.size()>0 ?"1":"0");
        request.setAttribute("sfNzzj",nzzjMap.size()>0 ?"1":"0");

        XsxxService xsxxService = new XsxxService();
        HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
        request.setAttribute("jbxx", xsjbxx);
        //学生基本信息显示配置
        request.setAttribute("jbxxList", jbxxList);
        return mapping.findForward("grxfjshbView");
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
        GrxfjsjgService bservice = new GrxfjsjgService();
        HashMap<String,String> map = service.getWordInfo(jgid,hblx);
        GrxfjsjgForm model = bservice.getModel(jgid);
        XsxxService xsxxService = new XsxxService();
        HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("hbMap",map);
        data.put("pjxfjpm",map.get("pjxfjpm"));
        data.putAll(xsjbxx);
        String tbrq =new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
        data.put("tbrq",tbrq);
        //党员比例
        File file = null;
        if("nzhb".equals(hblx)){
            file = FreeMarkerUtil.getWordFile(data,"classpath://templates//sxzzjygl",
                    "grnzhb_10699.xml",FreeMarkerUtil.getFileName(xsjbxx.get("xh")+"_"+"中期汇报"));
        }
        if("nzzj".equals(hblx)){
            file = FreeMarkerUtil.getWordFile(data,"classpath://templates//sxzzjygl",
                    "grnzzj_10699.xml",FreeMarkerUtil.getFileName(xsjbxx.get("xh")+"_"+"年终总结"));
        }
        return file;
    }
}
