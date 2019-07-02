package com.zfsoft.xgxt.xyfd.fdswh;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by llf on 2019/6/24.
 */
public class FdsAction extends SuperAction<FdsForm,FdsService> {

    private FdsService fdsService = new FdsService();
    /**
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward fdsList(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
        FdsForm model = (FdsForm)form;
        if (QUERY.equalsIgnoreCase(model.getType())){
            //���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            List<HashMap<String,String>> resultList = getService().getPageList(model,user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        String path = "xyfd_xyfd_fdswh.do";
        request.setAttribute("path", path);
        return mapping.findForward("fdsList");
    }

    /**
     * ����������
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addfds(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws Exception{
        FdsForm model = (FdsForm)form;
        if (SAVE.equalsIgnoreCase(model.getType())){
            boolean flag = fdsService.saveFds(model);
            String key = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(key));
            return null;
        }
        return mapping.findForward("addfds");
    }

    /**
     * �޸ĸ�����
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updatefds(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws Exception{
        FdsForm model = (FdsForm)form;
        HashMap<String,String> fdsxx = fdsService.getFds(model);
        request.setAttribute("fdsxx",fdsxx);
        if (UPDATE.equalsIgnoreCase(model.getType())){
            boolean flag = fdsService.updateFds(model);
            String key = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(key));
            return null;
        }
        BeanUtils.copyProperties(model,fdsxx);
        return mapping.findForward("updatefds");
    }

    /**
     * ɾ��������
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteFds(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception{
        String values = request.getParameter("values");
        if (!StringUtil.isNull(values)){
            String[] mess = fdsService.deleteFds(values.split(","));
            if(null==mess||mess.length!=2){
                String message= MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
                response.getWriter().print(getJsonMessage(message));
                return null;
            }
            Map<String, String> map = new HashMap<String, String>();
            map.put("num",mess[0]);
            map.put("nodel",mess[1]);
            JSONObject json = JSONObject.fromObject(map);
            response.getWriter().print(json);
        } else {
            throw new SystemException(MessageKey.SYS_DEL_NULL);
        }
        return null;
    }
}
