package xsgzgl.gygl.cwdh;

import com.zfsoft.ms.mail.util.StringUtils;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xsgzgl.gygl.xjdfygl.ldxxgl.LdxxglForm;
import xsgzgl.gygl.xjdfygl.ldxxgl.LdxxglService;
import xsgzgl.gygl.xjdfygl.qsxxgl.QsxxglService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class CwdhAction extends SuperAction<CwdhForm,CwdhService> {
    private LdxxglService ldxxglService = new LdxxglService();
    private QsxxglService qsxxglService = new QsxxglService();
    public ActionForward cwdh(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
        CwdhForm model = (CwdhForm)form;
        CwdhService service = getService();
        if(SAVE.equals(model.getType())){
            String msg = service.yz(model);
            if("true".equals(msg)){
                boolean flag = service.save(model);
                String key = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
                response.getWriter().print(getJsonMessageByKey(key));
            } else {
                response.getWriter().print(getJsonMessage(msg));
            }

            return null;
        }
        request.setAttribute("xqList",ldxxglService.getXqList());
        return mapping.findForward("cwdh");
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

    //获取床位信息
    public ActionForward getCwxxList(ActionMapping mapping, ActionForm form,
                HttpServletRequest request, HttpServletResponse response) throws Exception {
            String lddm = request.getParameter("lddm");
            String qsh = request.getParameter("qsh");
            final String xh = request.getParameter("xh");
        List<HashMap<String,String>> list = qsxxglService.getQscwList(lddm,qsh);
        if(!StringUtil.isNull(xh)){
            //筛选符合输入学号的床位
            List<HashMap<String,String>> rs = FilterUtils.filter(list, new Test<HashMap<String, String>>() {
                @Override
                public boolean test(HashMap<String, String> item) {
                    if(StringUtils.isNull(item.get("xh")))
                        return false;
                    return item.get("xh").contains(xh);
                }
            });
            response.getWriter().print(JSONArray.fromObject(rs));
            return null;
        }
        response.getWriter().print(JSONArray.fromObject(list));
        return null;
    }
}
