package com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjssq;

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
 * @类功能描述:班级学风建设申请
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-20 14:35
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class BjxfjssqAction extends SuperAction<BjxfjssqForm,BjxfjssqService> {
    private static final String url = "sxzzjy_bjxfjs_bjxfjssq.do";
    private BjxfjssqService service = new BjxfjssqService();

    @SystemAuth(url = url)
    public ActionForward getList(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BjxfjssqForm model = (BjxfjssqForm) form;
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
        BjxfjsjcszService bjxfjsjcszService = new BjxfjsjcszService();
        BjxfjsjcszForm jcszModel = bjxfjsjcszService.getModel();
        request.setAttribute("jcszModel",jcszModel);
        SearchModel searchModel=new SearchModel();
        searchModel.setSearch_tj_xn(new String[]{Base.currXn});
        searchModel.setSearch_tj_xq(new String[]{Base.currXq});
        request.setAttribute("searchTj", searchModel);
        String path = "sxzzjy_bjxfjs_bjxfjssq.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("bjxfjssqList");
    }

    public ActionForward bjxfjssqAdd(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BjxfjssqForm model = (BjxfjssqForm) form;
        User user = getUser(request);
        List<HashMap<String,String>> sblxList = service.getAllSblx();
        request.setAttribute("sblxList",sblxList);
        model.setXn(Base.currXn);
        model.setXq(Base.currXq);
        if (SAVE.equalsIgnoreCase(model.getType())
                || SUBMIT.equalsIgnoreCase(model.getType())) {
            /*if (!isTokenValid(request)){
                response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
                return null;
            }*/

            boolean isExist = false;
            model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
            isExist = service.isExist(model);
            if (!isExist) {
                super.resetToken(request);
                // 添加
                String sqid = UniqID.getInstance().getUniqIDHash();
                model.setSqid(sqid);
                model.setSqr(user.getUserName());
                boolean result = service.saveBjxfjssq(model);
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
        String xnxq = Base.currXn+Base.dqxqmc;
        request.setAttribute("xnxq",xnxq);
        return mapping.findForward("bjxfjssqAdd");
    }

    public ActionForward bjxfjssqUpdate(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BjxfjssqForm model = (BjxfjssqForm) form;
        User user = getUser(request);
        List<HashMap<String,String>> sblxList = service.getAllSblx();
        request.setAttribute("sblxList",sblxList);
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
        BjxfjssqForm myform = service.getModel(model);
        BeanUtils.copyProperties(model, StringUtils.formatData(myform));
        String xnxq = model.getXn()+Base.getXqmcForXqdm(model.getXq());
        request.setAttribute("xnxq",xnxq);
        HashMap<String, String> map = service.getBjInfo(model.getBjdm());
        request.setAttribute("map",map);
        return mapping.findForward("bjxfjssqUpdate");
    }
    public ActionForward bjxfjssqDel(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String values = request.getParameter("values");
        if (!StringUtil.isNull(values)) {
            // 删除
            int num = service.runDelete(values.split(","));
            Map<String, String> map = new HashMap<String, String>();
            map.put("num", num + "");
            JSONObject json = JSONObject.fromObject(map);
            response.getWriter().print(json);
        } else {
            throw new SystemException(MessageKey.SYS_DEL_NULL);
        }
        return null;
    }
    public ActionForward bjxfjssqSubmit(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BjxfjssqForm model = (BjxfjssqForm) form;
        BjxfjssqForm myForm = service.getModel(model);
        boolean result = service.bjxfjssqSubmit(myForm);
        String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    public ActionForward bjxfjssqCancel(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BjxfjssqForm model = (BjxfjssqForm) form;
        // 只有刚提交并且第一级未审核的前提下，申请人可以撤销
        boolean result = service.cancelRecord(model.getSqid(), model.getSplc());
        if (result) {
            // 更新业务状态为'未提交'
            ShlcDao shlcdao = new ShlcDao();
            if(Integer.valueOf(shlcdao.getExistTh(model.getSqid()))>0){
                model.setShzt(Constants.YW_YTH);
            }else{
                model.setShzt(Constants.YW_WTJ);
            }
            service.bjxfjssqCancel(model);
        }
        String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }
    public ActionForward bjxfjssqView(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BjxfjssqForm model = (BjxfjssqForm) form;
        HashMap<String,String> map = service.getBjxfjssqInfo(model);
        HashMap<String, String> bjmap = service.getBjInfo(map.get("bjdm"));
        request.setAttribute("map",map);
        request.setAttribute("shztmc",map.get("shztmc"));
        request.setAttribute("bjmap",bjmap);
        return mapping.findForward("bjxfjssqView");
    }

    /**
     * @描述:选择班级
     * @作者：lgx [工号：1553]
     * @日期：2018/6/22 14:32
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [mapping, form, request, response]
     * @return: org.apache.struts.action.ActionForward
     */
    public ActionForward selectBj(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BjxfjssqForm model = (BjxfjssqForm) form;
        User user = getUser(request);
        if(QUERY.equals(model.getType())){
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            // 查询
            List<HashMap<String, String>> resultList = service.getBjList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        String path = "sxzzjy_bjxfjs_xzbj.do";
        request.setAttribute("path", path);
        return mapping.findForward("selectBj");
    }

    /**
     * @描述:获取班级详细信息
     * @作者：lgx [工号：1553]
     * @日期：2018/6/22 14:32
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [mapping, form, request, response]
     * @return: org.apache.struts.action.ActionForward
     */
    public ActionForward getBjInfo(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BjxfjssqForm model = (BjxfjssqForm) form;
        HashMap<String, String> result = service.getBjInfo(model.getBjdm());
        JSONArray dataList = JSONArray.fromObject(result);
        response.getWriter().print(dataList);
        return null;

    }
    public ActionForward getPrint(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String id =  request.getParameter("sqid");
        File wordFile = getWord(id);
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
                String bjdm = service.getModel(values[i]).getBjdm();
                File file = getWord(values[i]);
                files.add(file);
            }

            File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
            FileUtil.outputZip(response, zipFile);
        }
        return null;
    }

    private File getWord (String id) throws Exception {
        BjxfjssqForm model = new BjxfjssqForm();
        model.setSqid(id);
        HashMap<String, String> map = service.getBjxfjssqInfo(model);
        HashMap<String, String> bjmap = service.getBjInfo(map.get("bjdm"));
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
        file = FreeMarkerUtil.getWordFile(data,"classpath://templates//sxzzjygl",
                    "bjxfjssb_10699.xml",FreeMarkerUtil.getFileName(bjmap.get("bjmc")+"_"+"申报"));
        return file;
    }


}
