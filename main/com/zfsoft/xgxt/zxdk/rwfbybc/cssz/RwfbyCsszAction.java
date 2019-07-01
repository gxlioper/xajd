/**
 * @部门:学工产品事业部
 * @日期：2015-9-7 下午04:02:02 
 */
package com.zfsoft.xgxt.zxdk.rwfbybc.cssz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.util.OptionUtil;
import common.newp.StringUtil;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： ChenQ[工号:856]
 * @时间： 2015-9-7 下午04:02:02
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class RwfbyCsszAction extends SuperAction<RwfbyCssz, RwfbyCsszService> {
	private static final String KGZT_CLOSE = "0";
	
	private static final String url = "zxdk_rwfby_cssz.do";

	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwfbyCssz csszForm = (RwfbyCssz) form;
		RwfbyCsszService service = new RwfbyCsszService();
		RwfbyCssz model = service.getModel();

		if (model != null) {

			if (StringUtil.isNull(csszForm.getSqkg())) {
				csszForm.setSqkg(KGZT_CLOSE);
			}

			BeanUtils.copyProperties(csszForm, model);
		} else {
			csszForm.setSqkg(KGZT_CLOSE);
		}

		XtwhShlcService shlcService = new XtwhShlcService();
		// 基本设置中审核流程列表的取值通用方法
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("zxdk");
		request.setAttribute("shlcList", shlc);

		List<HashMap<String, String>> onOff = new OptionUtil()
				.getOptions(OptionUtil.ONOFF);
		request.setAttribute("onOff", onOff);

		request.setAttribute("path", "zxdk_rwfby_cssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cssz");
	}

}
