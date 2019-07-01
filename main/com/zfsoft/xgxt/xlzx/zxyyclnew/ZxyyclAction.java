package com.zfsoft.xgxt.xlzx.zxyyclnew;

import java.net.URLDecoder;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.String.StringUtils;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;

/** 
 * 咨询预约处理 
 */

public class ZxyyclAction extends SuperAction{

	private static ZxyyclService service = new ZxyyclService();

	

	/** 
	 * 保存咨询信息
	 */
	public ActionForward saveXlzxInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		ZxyyclForm model = (ZxyyclForm) form;
		if(StringUtils.isNotNull(model.getZxdz())){
			model.setZxdz(URLDecoder.decode((URLDecoder.decode(model.getZxdz(),"UTF-8")),"UTF-8"));
		}
		if(StringUtils.isNotNull(model.getBz())){
			model.setBz(URLDecoder.decode((URLDecoder.decode(model.getBz(),"UTF-8")),"UTF-8"));
		}
		try {
			boolean flag = service.saveXlzxInfo(model);
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		return null;
	}
	/** 
	 * 咨询师或管理员新增预约咨询信息
	 */
	public ActionForward  saveYyzxInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxyyclForm model = (ZxyyclForm) form;
		if(StringUtils.isNotNull(model.getZxdz())){
			model.setZxdz(URLDecoder.decode((URLDecoder.decode(model.getZxdz(),"UTF-8")),"UTF-8"));
		}
		if(StringUtils.isNotNull(model.getBz())){
			model.setBz(URLDecoder.decode((URLDecoder.decode(model.getBz(),"UTF-8")),"UTF-8"));
		}
		String jzxm = request.getParameter("jzxm");
		HashMap<String,String > map = new HashMap<String, String>();
 		if(StringUtils.isNotNull(jzxm)){
			map.put("jzxm",jzxm);
			map.put("jzxb",request.getParameter("jzxb"));
			map.put("jzlxdh",request.getParameter("jzlxdh"));
			map.put("gx",request.getParameter("gx"));
			map.put("jzdzyx",request.getParameter("jzdzyx"));
			map.put("jtjq",request.getParameter("jtjq"));
			map.put("fqzy",request.getParameter("fqzy"));
			map.put("fxl",request.getParameter("fxl"));
			map.put("mqzy",request.getParameter("mqzy"));
			map.put("mxl",request.getParameter("mxl"));
			map.put("jtdz",request.getParameter("jtdz"));
			map.put("xssfzx",request.getParameter("xssfzx"));
			map.put("fdysfzx",request.getParameter("fdysfzx"));
			map.put("lfmd",request.getParameter("lfmd"));
		}
		try {
			boolean flag = service.saveYyzxInfo( model,map);
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		return null;
	}
	
	/** 
	 * 删除咨询信息
	 */
	public ActionForward delZxInfoByYyid(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxyyclForm model = (ZxyyclForm) form;
		String[] yyids = new String[]{model.getYyid()};
		try {
			int count = service.delZxInfoByYyid(yyids);
			if(count>0){
				response.getWriter().print(true);
			}else{
				response.getWriter().print(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		return null;
	}
	/** 
	 * 修改咨询信息	 
	 */
	public ActionForward updateXlzxInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxyyclForm model = (ZxyyclForm) form;
		if(StringUtils.isNotNull(model.getZxdz())){
			model.setZxdz(URLDecoder.decode((URLDecoder.decode(model.getZxdz(),"UTF-8")),"UTF-8"));
		}
		if(StringUtils.isNotNull(model.getBz())){
			model.setBz(URLDecoder.decode((URLDecoder.decode(model.getBz(),"UTF-8")),"UTF-8"));
		}
		String jzxxid = request.getParameter("jzxxid");
		HashMap<String,String > map = new HashMap<String, String>();
		if(StringUtils.isNotNull(jzxxid)){
			map.put("id",request.getParameter("jzxxid"));
			map.put("sqid",request.getParameter("jzxxsqid"));
			map.put("jzxm",request.getParameter("jzxm"));
			map.put("jzxb",request.getParameter("jzxb"));
			map.put("jzlxdh",request.getParameter("jzlxdh"));
			map.put("gx",request.getParameter("gx"));
			map.put("jzdzyx",request.getParameter("jzdzyx"));
			map.put("jtjq",request.getParameter("jtjq"));
			map.put("fqzy",request.getParameter("fqzy"));
			map.put("fxl",request.getParameter("fxl"));
			map.put("mqzy",request.getParameter("mqzy"));
			map.put("mxl",request.getParameter("mxl"));
			map.put("jtdz",request.getParameter("jtdz"));
			map.put("xssfzx",request.getParameter("xssfzx"));
			map.put("fdysfzx",request.getParameter("fdysfzx"));
			map.put("lfmd",request.getParameter("lfmd"));
		}
		try {
			boolean flag = service.updateXlzxInfo(model,map);
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		return null;
	}
	
}
