package xsgzgl.qgzx.yftssz;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.QgCommUtil.QgCommUtilf;
import xsgzgl.qgzx.cssz.QgzxCsszService;
import xsgzgl.qgzx.glygl.QgzxGlyglService;
import xsgzgl.qgzx.glygl.XqglyService;
import xsgzgl.qgzx.gwglnew.QgzxGwglForm;
import xsgzgl.qgzx.gwglnew.QgzxGwglService;
import xsgzgl.qgzx.jcdmwh.QgzxJcdmwhService;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.export.util.DateUtils;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.jskp.lxsh.LxshForm;
import com.zfsoft.xgxt.jskp.lxsq.LxsqForm;
/**
 * 勤工助学-勤工岗位管理-月份天数设置
 */
@SuppressWarnings("unchecked")
public class QgzxYftsszAction extends SuperAction {
	/**
	 * 月份天数设置
	 * 2018-8-15 
	 */
	public ActionForward yftssz(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxYftsszForm model = (QgzxYftsszForm) form;
		QgzxYftsszService service = new QgzxYftsszService();
		User user = getUser(request);
		//获取base中当年年度，做年度list，只存当前、前后两年
		request.setAttribute("xnList", Base.getXnndList());
		ArrayList<HashMap<String, String>> gdgList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> xnxqMap = new HashMap<String, String>();
		String xn = null;
		int currentNd = Base.currNd != null ? Integer.parseInt(Base.currNd) : Integer.parseInt(DealString.getDateTime().substring(0,4));
		for (int i = currentNd - 2; i < currentNd + 1; i++) {
			xnxqMap = new HashMap<String, String>();
			xn = String.valueOf(i) + "-" + String.valueOf(i + 1);
			xnxqMap.put("xn", xn);
			gdgList.add(xnxqMap);
		}
		request.setAttribute("xnList", gdgList);
		// 查询
		//判断是第一次点击月份天数设置，还是选择年份后的调用
		boolean isFirst = false;
		if(model.getXn() == null){
			model.setXn(String.valueOf(currentNd-1) + "-" + String.valueOf(currentNd));
			isFirst = true;
		}
		
		String[] arr = service.getYftsszArr(model, user);
		if(isFirst){
			request.setAttribute("arr",arr);
			return mapping.findForward("yftssz");
		} else {
			String data = Arrays.toString(arr);
			response.getWriter().print(data);
			return null;
		}
		
	}
}
