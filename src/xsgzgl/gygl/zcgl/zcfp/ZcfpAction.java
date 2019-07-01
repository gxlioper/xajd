package xsgzgl.gygl.zcgl.zcfp;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xsgzgl.gygl.xjdfygl.ldxxgl.LdxxglForm;
import xsgzgl.gygl.xjdfygl.ldxxgl.LdxxglService;
import xsgzgl.gygl.xjdfygl.qsxxgl.QsxxglForm;
import xsgzgl.gygl.xjdfygl.qsxxgl.QsxxglService;
import xsgzgl.gygl.zcgl.zcxx.ZcxxService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class ZcfpAction extends SuperAction<ZcfpForm,ZcfpService> {
    private LdxxglService ldxxglService = new LdxxglService();
    private QsxxglService qsxxglService = new QsxxglService();
    private ZcxxService zcxxService = new ZcxxService();
    public ActionForward zcfpList(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
        ZcfpForm model = (ZcfpForm)form;
        if(QUERY.equals(model.getType())){
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            List<HashMap<String,String>> data = getService().zcfpList(model,user);
            response.getWriter().print(JSONArray.fromObject(data));
            return null;
        }
        request.setAttribute("path","gygl_zcgl_zcfpwh.do");
        return mapping.findForward("zcfpList");
    }
    public ActionForward fp(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
        ZcfpForm model = (ZcfpForm)form;
        if(SAVE.equalsIgnoreCase(model.getType())){
            String msg = getService().yz(model);
            if("true".equalsIgnoreCase(msg)){
                boolean flag = getService().save(model);
                String key = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
                response.getWriter().print(getJsonMessageByKey(key));
            } else {
                response.getWriter().print(getJsonMessage(msg));
            }
            return null;
        }
        request.setAttribute("xqList",ldxxglService.getXqList());
        return mapping.findForward("fp");
    }
    public ActionForward update(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request, HttpServletResponse response) throws Exception{
        ZcfpForm model = (ZcfpForm)form;
        if(SAVE.equalsIgnoreCase(model.getType())){
            String msg = getService().yz(model);
            if("true".equalsIgnoreCase(msg)){
                boolean flag = getService().save(model);
                String key = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
                response.getWriter().print(getJsonMessageByKey(key));
            } else {
                response.getWriter().print(getJsonMessage(msg));
            }
            return null;
        }
        String pk = request.getParameter("pk");
        QsxxglForm qsxxglForm = new QsxxglForm();
        String[] id = pk.split("@!!!");
        qsxxglForm.setLddm(id[0]);
        qsxxglForm.setQsh(id[1]);
        QsxxglForm data = qsxxglService.getModel(qsxxglForm);
        //初始化信息
        request.setAttribute("xqList",ldxxglService.getXqList());
        request.setAttribute("ldList",ldxxglService.getLdxxByXq(data.getXqdm()));
        LdxxglForm ld = ldxxglService.getModel(data.getLddm());
        request.setAttribute("lcList",qsxxglService.lccsh(ld.getLdcs(),ld.getQsch(),"1".equals(ld.getSfhlc())));
        request.setAttribute("qsList",qsxxglService.getQsxxListByLddmAndCh(data.getLddm(),data.getCh()));
        //初始化信息
        request.setAttribute("rs",data);
        return mapping.findForward("update");
    }
    public ActionForward del(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request, HttpServletResponse response) throws Exception{
        String ids = request.getParameter("ids");
        boolean flag = getService().del(ids.split(","));
        String key = flag ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
        response.getWriter().print(getJsonMessageByKey(key));
        return null;
    }
    public ActionForward getDataForUpdate(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest request, HttpServletResponse response) throws Exception{
        String lddm = request.getParameter("lddm");
        String qsh = request.getParameter("qsh");
        List<HashMap<String,String>> rs = getService().getQsZclist(lddm,qsh);
        response.getWriter().print(JSONArray.fromObject(rs));
        return null;
    }
    public ActionForward getLddxxList(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response) throws Exception {
        String xqdm = request.getParameter("xqdm");
        List<HashMap<String,String>> list = ldxxglService.getLdxxByXq(xqdm);
        response.getWriter().print(JSONArray.fromObject(list));
        return null;
    }

    //楼栋层数获取
    public ActionForward getLddcs(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
        String lddm = request.getParameter("lddm");
        LdxxglService service = new LdxxglService();
        LdxxglForm ldxx = service.getModel(lddm);
        List<String> data = qsxxglService.lccsh(ldxx.getLdcs(),ldxx.getQsch(),"1".equals(ldxx.getSfhlc()));
        response.getWriter().print(JSONArray.fromObject(data));
        return null;
    }
    //获取寝室信息
    public ActionForward getQsxxList(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response) throws Exception {
        String lddm = request.getParameter("lddm");
        String ch = request.getParameter("ch");
        List<HashMap<String,String>> list = qsxxglService.getQsxxListByLddmAndCh(lddm,ch);
        response.getWriter().print(JSONArray.fromObject(list));
        return null;
    }
    //资产类型
    public ActionForward getZclxList(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<HashMap<String,String>> data = zcxxService.getLxdmList();
        response.getWriter().print(JSONArray.fromObject(data));
        return null;
    }
    //资产信息
    public ActionForward getZcxxList(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response) throws Exception {
        String lxdm = request.getParameter("lxdm");
        List<HashMap<String,String>> data = zcxxService.getZcxxByLxdm(lxdm);
        response.getWriter().print(JSONArray.fromObject(data));
        return null;
    }
}
