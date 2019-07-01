package xsgzgl.gygl.xjdfygl.qsxxgl;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
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
import xgxt.utils.FormModleCommon;
import xsgzgl.gygl.xjdfygl.ldxxgl.LdxxglForm;
import xsgzgl.gygl.xjdfygl.ldxxgl.LdxxglService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class QsxxglAction extends SuperAction<QsxxglForm,QsxxglService> {

    public ActionForward qsxxList(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
        QsxxglForm model = (QsxxglForm)form;
        if(QUERY.equals(model.getType())){
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            List<HashMap<String,String>> data = getService().getPageList(model,user);
            response.getWriter().print(JSONArray.fromObject(data));
            return null;
        }
        request.setAttribute("path","gygl_fygl_qsxxgl.do");
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("qsxxList");
    }
    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response) throws Exception{
        QsxxglForm model = (QsxxglForm)form;
        LdxxglService service = new LdxxglService();
        if(SAVE.equals(model.getType())){
            boolean flag = getService().runInsert(model);
            String key = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(key));
            return null;
        }
        model.setQsxb("1");
        model.setSfykt("1");
        model.setSfywsj("1");
        model.setWsjwz("01");
        request.setAttribute("xqList",service.getXqList());
//        request.setAttribute("ldList",service.getAllList(new LdxxglForm()));
//        request.setAttribute("szlcList",getService().lccsh());
        return mapping.findForward("add");
    }

    public ActionForward getLddxxList(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response) throws Exception{
        String xqdm = request.getParameter("xqdm");
        LdxxglService service = new LdxxglService();
        List<HashMap<String,String>> list = service.getLdxxByXq(xqdm);
        response.getWriter().print(JSONArray.fromObject(list));
        return null;
    }
    //楼栋层数获取
    public ActionForward getLddcs(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response) throws Exception{
        String lddm = request.getParameter("lddm");
        LdxxglService service = new LdxxglService();
        LdxxglForm ldxx = service.getModel(lddm);
        List<String> data = getService().lccsh(ldxx.getLdcs(),ldxx.getQsch(),"1".equals(ldxx.getSfhlc()));
        response.getWriter().print(JSONArray.fromObject(data));
        return null;
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws Exception{
        QsxxglForm model = (QsxxglForm)form;
        LdxxglService service = new LdxxglService();
        if(SAVE.equals(model.getType())){
            boolean flag = getService().runUpdate(model);
            String key = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(key));
            return null;
        }

        QsxxglForm data = getService().getModel(model);
        BeanUtils.copyProperties(model,data);
        request.setAttribute("xqmc",data.getXqmc());
        request.setAttribute("ldmc",data.getLdmc());
        request.setAttribute("ch",data.getCh());
        request.setAttribute("qsh",data.getQsh());
        return mapping.findForward("update");
    }

    public ActionForward del(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response) throws Exception{
        String pk = request.getParameter("values");
        boolean f = getService().del(pk.split(","));
        String msg = f ? MessageUtil.getText(MessageKey.SYS_DEL_SUCCESS) :
                MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
        response.getWriter().print(JSONObject.fromObject(getJsonMessage(msg)));
        return null;
    }

    public ActionForward plxgqs(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response) throws Exception{
        QsxxglForm model = (QsxxglForm)form;
        if(SAVE.equals(model.getType())){
            boolean f = getService().plxgqs(model);
            String msg = f ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(msg));
            return null;
        }
        request.setAttribute("pks",model.getPks());
        return mapping.findForward("plxgqs");
    }

    public ActionForward xgcw(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws Exception{
        String pk = request.getParameter("pk");
        String[] pkArray = pk.split("@!!!");
        String type = request.getParameter("type");
        QsxxglService service = getService();
        if(QUERY.equals(type)){

            List<HashMap<String,String>> data = service.getQscwList(pkArray[0],pkArray[1]);
            response.getWriter().print(JSONArray.fromObject(data));
            return null;
        } else if(DEL.equals(type)){
            String cwh = request.getParameter("cwh");
            boolean f = service.delQsCw(pkArray[0],pkArray[1],cwh);
            Map<String,String> map = new HashMap<String, String>();
            if(f){
                map.put("status","success");
            } else {
                map.put("status","fail");
            }
            response.getWriter().print(JSONObject.fromObject(map));
            return null;
        } else if(SAVE.equals(type)){
            String cwh = request.getParameter("cwh");
            if(!service.isExistCwh(pkArray[0],pkArray[1],cwh)){
                boolean f = service.addQsCw(pkArray[0],pkArray[1],cwh);
                Map<String,String> map = new HashMap<String, String>();
                if(f){
                    map.put("status","success");
                } else {
                    map.put("status","fail");
                }
                response.getWriter().print(JSONObject.fromObject(map));
                return null;
            } else {
                Map<String,String> map = new HashMap<String, String>();
                map.put("status","fail");
                map.put("message","床位号存在！");
                response.getWriter().print(JSONObject.fromObject(map));
                return null;
            }
        }

        QsxxglForm model = new QsxxglForm();
        model.setLddm(pkArray[0]);
        model.setQsh(pkArray[1]);
        QsxxglForm data = service.getModel(model);
        request.setAttribute("data",data);
        request.setAttribute("pk",pk);
        return mapping.findForward("xgcw");
    }

    public ActionForward qssscsh(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response) throws Exception{

        QsxxglForm model = (QsxxglForm)form;
        QsxxglService service = getService();
        if(SAVE.equals(model.getType())){
            boolean flag = service.qssscshBc(model);
            String msg = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(msg));
            return null;
        }
        if(StringUtils.isNotEmpty(model.getPks())){
            request.setAttribute("pks",model.getPks());
            request.setAttribute("xzgs",model.getPks().split(",").length);
        } else {
            request.setAttribute("cxgs",service.cxwrzqsgs());
        }
        return mapping.findForward("qssscsh");
    }

    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        QsxxglForm model = (QsxxglForm)form;

        // 生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);

        User user = getUser(request);
        // 查询
        List<HashMap<String, String>> resultList = getService().getAllList(model,user);// 查询出所有记录，不分页

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

    public ActionForward qsfpList(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
        QsxxglForm model = (QsxxglForm)form;
        if(QUERY.equals(model.getType())){
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            List<HashMap<String,String>> data = getService().getQsfpList(model,user);
            response.getWriter().print(JSONArray.fromObject(data));
            return null;
        }
        request.setAttribute("path","gygl_zsgl_qsfp.do");
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("qsfpList");
    }

    public ActionForward qsfp(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
        QsxxglForm model = (QsxxglForm)form;
        QsxxglService service = getService();
        if(SAVE.equals(model.getType())){
            boolean flag = service.fpBc(model);
            String msg = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(msg));
            return null;
        }
        request.setAttribute("pks",model.getPks());
        request.setAttribute("xzgs",model.getPks().split(",").length);
        request.setAttribute("xyList",JSONArray.fromObject(service.getXysy("xy")));
        request.setAttribute("syList",JSONArray.fromObject(service.getXysy("sy")));
        return mapping.findForward("qsfp");
    }
}
