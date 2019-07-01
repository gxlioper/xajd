package com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjsjg;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.*;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjshb.BjxfjshbjgForm;
import com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjshb.BjxfjshbjgService;
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
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @类功能描述:班级学风建设结果
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-22 17:23
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class BjxfjsjgAction extends SuperAction<BjxfjsjgForm,BjxfjsjgService>{
    private BjxfjsjgService service = new BjxfjsjgService();
    private static final String url = "sxzzjy_bjxfjs_bjxfjsjg.do";


    @SystemAuth(url = url)
    public ActionForward getList(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BjxfjsjgForm model = (BjxfjsjgForm) form;
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
        String path = "sxzzjy_bjxfjs_bjxfjsjg.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("bjxfjsjgList");
    }

    public ActionForward bjxfjsjgAdd(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BjxfjsjgForm model = (BjxfjsjgForm) form;
        User user = getUser(request);
        List<HashMap<String,String>> sblxList = service.getAllSblx();
        request.setAttribute("sblxList",sblxList);
        model.setXn(Base.currXn);
        model.setXq(Base.currXq);
        String xnxq = Base.currXn+Base.dqxqmc;
        request.setAttribute("xnxq",xnxq);
        return mapping.findForward("bjxfjsjgAdd");
    }

    public ActionForward bjxfjsjgUpdate(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BjxfjsjgForm model = (BjxfjsjgForm) form;
        User user = getUser(request);
        List<HashMap<String,String>> sblxList = service.getAllSblx();
        request.setAttribute("sblxList",sblxList);
        HashMap<String,String> jgmap = service.getBjxfjsjgInfo(model);
        BeanUtils.copyProperties(model, StringUtils.formatData(jgmap));
        HashMap<String, String> map = service.getBjInfo(model.getBjdm());
        request.setAttribute("map",map);
        request.setAttribute("jgmap",jgmap);
        request.setAttribute("sjly",jgmap.get("sjly"));
        return mapping.findForward("bjxfjsjgUpdate");
    }
    public ActionForward bjxfjsjgSave(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BjxfjsjgForm model = (BjxfjsjgForm) form;
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
    public ActionForward bjxfjsjgDel(ActionMapping mapping, ActionForm form,
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

    public ActionForward bjxfjsjgView(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BjxfjsjgForm model = (BjxfjsjgForm) form;
        HashMap<String,String> map = service.getBjxfjsjgInfo(model);
        HashMap<String, String> bjmap = service.getBjInfo(map.get("bjdm"));
        BjxfjshbjgService bservice = new BjxfjshbjgService();
        BjxfjshbjgForm bform = new BjxfjshbjgForm();
        bform.setJgid(model.getJgid());
        bform.setHblx("nzhb");
        HashMap<String,String> nzhbMap = bservice.getInfo(bform);
        bform.setHblx("nzzj");
        HashMap<String,String> nzzjMap = bservice.getInfo(bform);
        request.setAttribute("nzzjMap",nzzjMap);
        request.setAttribute("nzhbMap",nzhbMap);
        request.setAttribute("map",map);
        request.setAttribute("bjmap",bjmap);
        return mapping.findForward("bjxfjsjgView");
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
        BjxfjsjgForm model = (BjxfjsjgForm) form;
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
        BjxfjsjgForm model = (BjxfjsjgForm) form;
        HashMap<String, String> result = service.getBjInfo(model.getBjdm());
        JSONArray dataList = JSONArray.fromObject(result);
        response.getWriter().print(dataList);
        return null;

    }

    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BjxfjsjgForm model = (BjxfjsjgForm) form;
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

        String id =  request.getParameter("jgid");
        String hblx =  request.getParameter("hblx");
        String bjdm = request.getParameter("bjdm");
        File wordFile = getWord(id,hblx,bjdm);
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
                File file = getWord(values[i],hblx,bjdm);
                files.add(file);
            }

            File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
            FileUtil.outputZip(response, zipFile);
        }
        return null;
    }

    private File getWord (String id,String hblx,String bjdm) throws Exception {

        HashMap<String, String> bjmap = service.getBjInfo(bjdm);
        BjxfjshbjgService bservice = new BjxfjshbjgService();
        HashMap<String, String> map = new HashMap<String, String>();
        if("sq".equals(hblx)){
            BjxfjsjgForm bjxfjsjgForm = new BjxfjsjgForm();
            bjxfjsjgForm.setJgid(id);
            map = service.getBjxfjsjgInfo(bjxfjsjgForm);
        }else{
            map = bservice.getWordInfo(id,hblx);
        }


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
        if("sq".equals(hblx)){
            file = FreeMarkerUtil.getWordFile(data,"classpath://templates//sxzzjygl",
                    "bjxfjssb_10699.xml",FreeMarkerUtil.getFileName(bjmap.get("bjmc")+"_"+"申报"));
        }
        return file;
    }
}
