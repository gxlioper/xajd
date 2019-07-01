package xsgzgl.qgzx.yftssz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.qgzx.gwglnew.QgzxGwglForm;
import xsgzgl.qgzx.gwglnew.QgzxGwglService;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.JsonUtil;
/**
 * 勤工助学-酬金管理-酬金信息管理
 * @author yeyipin
 * @since 2012.9.19
 */
public class QgzxYftsszAjax extends BasicAction {
	/**
	 * 管理员维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 
	 * @throws Exception
	 */
	public ActionForward bcYftssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxYftsszService service = new QgzxYftsszService();
		QgzxYftsszForm model = (QgzxYftsszForm) form;
		List<String[]> params = new ArrayList<String[]>();
		String[] months = model.getMonths();//获取增加数据
		String[] days = model.getDays();
		for (int i = 0; i < months.length; i++) {
			String[] str = new String[3];
			str[0] = months[i];
			str[1] = days[i];
			str[2] = model.getXn();
			params.add(str);
		}
		User user = getUser(request);
		boolean result = service.bcYftssz(params,model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}
