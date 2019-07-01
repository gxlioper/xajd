package com.zfsoft.xgxt.xlzx.yysq.xstxxx;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.utils.String.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @类功能描述:学生填写信息
 * @作者： lgx [工号:1553]
 * @时间： 2018-12-27 10:45
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class XstxxxAction extends SuperAction<XstxxxForm,XstxxxService> {
    private static XstxxxService service = new XstxxxService();
    public static XstxxxForm getModel(String xh) throws Exception {
        return service.getModel(xh);
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        XstxxxForm model = (XstxxxForm) form;
        try {
            if(StringUtils.isNull(model.getHf()) && StringUtils.isNull(model.getZxmd()) ){
                response.getWriter().print(true);
                return null;
            }
            service.runDelete(new String[]{model.getXh()});
            boolean flag = service.runInsert(model);

            response.getWriter().print(flag);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException(MessageKey.SYS_SAVE_FAIL);
        }
        return null;
    }
}
