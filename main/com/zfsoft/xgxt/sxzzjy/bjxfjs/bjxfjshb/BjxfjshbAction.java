package com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjshb;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.*;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjsjcsz.BjxfjsjcszForm;
import com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjsjcsz.BjxfjsjcszService;
import com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjsjg.BjxfjsjgForm;
import com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjsjg.BjxfjsjgService;
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
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @类功能描述:班级学风建设汇报
 * @作者： lgx [工号:1553]
 * @时间： 2018-07-24 14:21
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class BjxfjshbAction extends SuperAction<BjxfjshbForm,BjxfjshbService> {
    private static final String url = "sxzzjy_bjxfjs_bjxfjshb.do";
    private BjxfjshbService service = new BjxfjshbService();

    @SystemAuth(url = url)
    public ActionForward getList(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BjxfjshbForm model = (BjxfjshbForm) form;
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
        BjxfjsjcszService bjxfjsjcszService = new BjxfjsjcszService();
        BjxfjsjcszForm jcszModel = bjxfjsjcszService.getModel();
        request.setAttribute("jcszModel",jcszModel);
        String path = "sxzzjy_bjxfjs_bjxfjshb.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("bjxfjshbList");
    }

    public ActionForward bjxfjshbAdd(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BjxfjshbForm model = (BjxfjshbForm) form;
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
                boolean result = service.saveBjxfjshb(model);
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
        BjxfjsjgService bjxfjsjgService = new BjxfjsjgService();
        BjxfjsjgForm bjxfjsjgForm = bjxfjsjgService.getModel(model.getJgid());
        HashMap<String, String> bjmap = bjxfjsjgService.getBjInfo(bjxfjsjgForm.getBjdm());
        request.setAttribute("bjmap",bjmap);
        request.setAttribute("hblx",model.getHblx());
        return mapping.findForward("bjxfjshbAdd");
    }

    public ActionForward bjxfjshbUpdate(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BjxfjshbForm model = (BjxfjshbForm) form;
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
        BjxfjshbForm myform = service.getModel(model);
        BeanUtils.copyProperties(model, StringUtils.formatData(myform));
        BjxfjsjgService bjxfjsjgService = new BjxfjsjgService();
        BjxfjsjgForm bjxfjsjgForm = bjxfjsjgService.getModel(model.getJgid());
        HashMap<String, String> bjmap = bjxfjsjgService.getBjInfo(bjxfjsjgForm.getBjdm());
        request.setAttribute("bjmap",bjmap);
        return mapping.findForward("bjxfjshbUpdate");
    }
    public ActionForward selectHblx(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BjxfjshbForm model = (BjxfjshbForm) form;
        /*String jgids = request.getParameter("values");
        request.setAttribute("jgids",jgids);*/
        request.setAttribute("type",model.getType());
        return mapping.findForward("selectHblx");
    }
    public ActionForward bjxfjshbDel(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String jgids = request.getParameter("values");
        String hblxs = request.getParameter("hblxs");
        if (!StringUtil.isNull(jgids)) {
            // 删除
            int num = service.bjxfjshbDel(jgids.split(","),hblxs.split(","));
            Map<String, String> map = new HashMap<String, String>();
            map.put("num", num + "");
            JSONObject json = JSONObject.fromObject(map);
            response.getWriter().print(json);
        } else {
            throw new SystemException(MessageKey.SYS_DEL_NULL);
        }
        return null;
    }
    public ActionForward bjxfjshbSubmit(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BjxfjshbForm model = (BjxfjshbForm) form;
        String[] values = request.getParameter("values").split(",");
        boolean result=false;
        for (String value:values) {
            String[] arr = value.split("_");
            model.setHblx(arr[0]);
            model.setSqid(arr[1]);
            BjxfjshbForm myForm = service.getModel(model);
            result = service.bjxfjshbSubmit(myForm);
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

    public ActionForward bjxfjshbCancel(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BjxfjshbForm model = (BjxfjshbForm) form;
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
                service.bjxfjshbCancel(model);
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
    public ActionForward bjxfjshbView(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BjxfjshbForm model = (BjxfjshbForm) form;
        BjxfjsjgService bjxfjsjgService = new BjxfjsjgService();
        BjxfjsjgForm bjxfjsjgForm = bjxfjsjgService.getModel(model.getJgid());
        HashMap<String, String> bjmap = bjxfjsjgService.getBjInfo(bjxfjsjgForm.getBjdm());
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
        request.setAttribute("bjmap",bjmap);
        request.setAttribute("nzzjMap",nzzjMap);
        request.setAttribute("nzhbMap",nzhbMap);
        request.setAttribute("sfNzhb",nzhbMap.size()>0 ?"1":"0");
        request.setAttribute("sfNzzj",nzzjMap.size()>0 ?"1":"0");
        return mapping.findForward("bjxfjshbView");
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
        BjxfjsjgService bservice = new BjxfjsjgService();
        HashMap<String,String> map = service.getWordInfo(jgid,hblx);
        HashMap<String, String> bjmap = bservice.getBjInfo(map.get("bjdm"));
        Map<String, Object> data = new HashMap<String, Object>();
        data.putAll(bjmap);
        data.putAll(map);
        String tbrq =new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
        data.put("tbrq",tbrq);
        //党员比例
        double dybl = 0;
        int bjzrs = Integer.valueOf(bjmap.get("bjzrs"));
        int dyrs = Integer.valueOf(bjmap.get("dyrs"));
        if(bjzrs != 0 && dyrs != 0){
            dybl = new BigDecimal((float)dyrs*100.0/bjzrs).setScale(2,
                    BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        data.put("dybl",dybl);
        File file = null;
        if("nzhb".equals(hblx)){
            file = FreeMarkerUtil.getWordFile(data,"classpath://templates//sxzzjygl",
                    "nzhb_10699.xml",FreeMarkerUtil.getFileName(bjmap.get("bjmc")+"_"+"中期汇报"));
        }
        if("nzzj".equals(hblx)){
            file = FreeMarkerUtil.getWordFile(data,"classpath://templates//sxzzjygl",
                    "nzzj_10699.xml",FreeMarkerUtil.getFileName(bjmap.get("bjmc")+"_"+"年终总结"));
        }
        return file;
    }

}
