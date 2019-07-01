package xsgzgl.gygl.xjdfygl.cwxxgl;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.coyote.Response;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class CwxxglAction extends SuperAction<CwxxglForm,CwxxglService> {

    private BdpzService bdpzService = new BdpzService();
    private List<HashMap<String, String>> jbxxList = null;

    public ActionForward cwxxList(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
        CwxxglForm model = (CwxxglForm)form;
        if(QUERY.equals(model.getType())){
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            List<HashMap<String,String>> data = getService().getPageList(model,user);
            response.getWriter().print(JSONArray.fromObject(data));
            return null;
        }
        request.setAttribute("path","gygl_fygl_cwxxgl.do");
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("cwxxList");
    }

    public ActionForward plbl(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
        CwxxglForm model = (CwxxglForm)form;
        CwxxglService service = getService();
        if(SAVE.equals(model.getType())){
            boolean flag = service.plblcw(model);
            String key = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(key));
            return null;
        }
        request.setAttribute("pks",model.getPks());
        request.setAttribute("kcws",model.getPks().split(",").length);
        request.setAttribute("blyyList",service.getBlyyList());
        return mapping.findForward("plbl");
    }

    public ActionForward cwsscsh(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception{
        CwxxglForm model = (CwxxglForm)form;
        CwxxglService service = getService();

        if(SAVE.equals(model.getType())){
            boolean flag = service.cwsscshBc(model);
            String key = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(key));
            return null;
        }
        if(StringUtils.isNotEmpty(model.getPks())){
            request.setAttribute("pks",model.getPks());
            request.setAttribute("xzgs",model.getPks().split(",").length);
        } else {
            request.setAttribute("cxgs",service.cxcwgs());
        }
        return mapping.findForward("cwsscsh");
    }

    public ActionForward rz(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {
        CwxxglForm model = (CwxxglForm) form;
        CwxxglService service = getService();
        if (!StringUtil.isNull(model.getXh())){
            //加载学生基本信息
            XsxxService xsxxService = new XsxxService();
            HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
            request.setAttribute("jbxx", xsjbxx);
        }
        if(SAVE.equals(model.getType())){
            boolean flag = service.rzSave(model);
            String key = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(key));
            return null;
        }
        jbxxList = bdpzService.getJbxxpz("gyglcwxxgl");
        request.setAttribute("jbxxList", jbxxList);
        request.setAttribute("rzyyList",service.getRzyyList());
        CwxxglForm myform = service.getModel(model);
        request.setAttribute("model",myform);
//        BeanUtils.copyProperties(model, xgxt.utils.String.StringUtils.formatData(myform));
        request.setAttribute("pk",model.getPk());
        String path = "gygl_fygl_cwxxgl10698.do?method=rz&pk=" + model.getPk();
        request.setAttribute("path", path);
        return mapping.findForward("rz");
    }

    public ActionForward ts(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request, HttpServletResponse response) throws Exception {
        CwxxglForm model = (CwxxglForm) form;
        CwxxglService service = getService();
        if(SAVE.equals(model.getType())){
            boolean flag = service.tsBc(model);
            String key = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(key));
            return null;
        }
        model.setSfcshcw("1");
        model.setTssj(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        model.setXn(Base.currXn);
        model.setXq(Base.currXq);
        String[] pks = model.getPks().split(",");
        if(pks.length == 1){
            //获取学生信息
            request.setAttribute("xsxx",service.getXsxx(pks[0]));
        }
        request.setAttribute("xzgs",pks.length);
        request.setAttribute("pks",model.getPks());
        request.setAttribute("xhs",model.getXhs());
        request.setAttribute("tsyyList",service.getTsyyList());
        List<HashMap<String,String>> xnList = Base.getXnndList();
        request.setAttribute("xnList", xnList);
        List<HashMap<String,String>> xqList = Base.getXqList();
        request.setAttribute("xqList", xqList);
        return mapping.findForward("ts");
    }

    public ActionForward list(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
        CwxxglForm model = (CwxxglForm)form;
        if(QUERY.equals(model.getType())){
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            List<HashMap<String,String>> data = getService().getzsqxcwList(model,user);
            response.getWriter().print(JSONArray.fromObject(data));
            return null;
        }
        request.setAttribute("path","gygl_fygl_zsqxgl.do");
//        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("list");
    }

    public ActionForward szdqsj(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response) throws Exception{
        CwxxglForm model = (CwxxglForm)form;
        CwxxglService service = getService();

        if(SAVE.equals(model.getType())){
            boolean flag = service.saveDqsj(model);
            String key = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(key));
            return null;
        }
        request.setAttribute("pks",model.getPks());
        request.setAttribute("xzgs",model.getPks().split(",").length);
        return mapping.findForward("szdqsj");
    }

    public ActionForward fyycList(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response) throws Exception{
        CwxxglForm model = (CwxxglForm)form;
        if(QUERY.equals(model.getType())){
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            List<HashMap<String,String>> data = getService().fyycList(model,user);
            response.getWriter().print(JSONArray.fromObject(data));
            return null;
        }
        request.setAttribute("path","gygl_fygl_fyyc.do");
//        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("fyycList");
    }
}
