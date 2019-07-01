package xsgzgl.qgzx.cjgl;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.date.DateUtils;
import xsgzgl.qgzx.QgCommUtil.QgCommUtilf;
import xsgzgl.qgzx.cssz.QgzxCsszService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 勤工助学-酬金管理-酬金信息管理
 * @author yeyipin
 * @since 2012.7.23
 */
public class QgzxCjglAjax extends BasicAction{
	
	
	/**
	 * 酬金信息查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cjxxCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxCjglService service = new QgzxCjglService();
		QgzxCjglForm myForm = (QgzxCjglForm)form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, myForm, request, user);
		String qgzq = QgCommUtilf.getQgzq();
		myForm.getSearchModel().setPath("qgzx_cjgl_cjxxgl.do");
		if("xq".equals(qgzq)){
			myForm.getSearchModel().setPath("qgzx_cjgl_cjxxgl_xq.do");
		}
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("cjcx");
		// 结果集
		ArrayList<String[]> rsArrList = service.getCjxxList(myForm);
		// 构建结果集
		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	/**
	 * 自动批量提交酬金发放信息（过期的）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward aotoTjCjffxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxCjglService service = new QgzxCjglService();
		QgzxCsszService qgzxCsszService = new QgzxCsszService();
		String sysdate = DateUtils.getDayOfMonth();
		HashMap<String, String> csszInfo = qgzxCsszService.getCssz();
		String jssj = csszInfo.get("jssj");
		if(jssj != null && Integer.parseInt(sysdate)>Integer.parseInt(jssj)){
			service.aotoTjCjffxx();
		}
		return null;
	}
	
	
	/**
	 * 获得岗位人员信息List
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getGwryList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxCjglService service = new QgzxCjglService();
		QgzxCjglForm myForm = (QgzxCjglForm)form;
		myForm.setGwdm(request.getParameter("gwdm"));
		myForm.setZgzt(request.getParameter("zgzt"));
		if("10351".equals(Base.xxdm)){
			if("queryYx".equals(myForm.getDoType())){
				HashMap<String,String> yxMap = service.getYxInfo(myForm);
				int zrs = Integer.valueOf(yxMap.get("zrs"));
				long yxzrs = Math.round(zrs*0.2);
				yxMap.put("yxzrs",String.valueOf(yxzrs));
				JSONObject json = JSONObject.fromObject(yxMap);
				response.getWriter().print(json);
				return null;
			}
			myForm.setXm(java.net.URLDecoder.decode(myForm.getXm(), "utf-8"));

		}
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("qgzx_cjgl_cjxxgl.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("cjff");
		// 结果集
		ArrayList<String[]> rsArrList = service.getGwryList(myForm);
		// 构建结果集
		String spHtml = service.createFFryHTML(rsModel, rsArrList, user,myForm);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	
	/**
	 * 保存(提交)酬金发放信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveCjffxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		QgzxCjglService service = new QgzxCjglService();
		QgzxCjglForm model = (QgzxCjglForm)form;
		//传输乱码问题
		model.setXh(service.unicode2Gbk(model.getXh()));
		model.setGwdm(service.unicode2Gbk(model.getGwdm()));
		model.setGwxz(service.unicode2Gbk(model.getGwxz()));
		model.setGwxn(service.unicode2Gbk(model.getGwxn()));
		model.setGwxq(service.unicode2Gbk(model.getGwxq()));
		model.setGs(service.unicode2Gbk(model.getGs()));
		model.setJe(service.unicode2Gbk(model.getJe()));
		model.setBz(service.unicode2Gbk(model.getBz()));
		model.setKhdj(service.unicode2Gbk(model.getKhdj()));
		model.setCjffr(service.unicode2Gbk(model.getCjffr()));
		model.setJcdlgs(service.unicode2Gbk(model.getJcdlgs()));
		model.setZhdlgs(service.unicode2Gbk(model.getZhdlgs()));
		
		model.setJfhb(service.unicode2Gbk(model.getJfhb()));
		model.setZc(service.unicode2Gbk(model.getZc()));
		
		model.setFfny(service.unicode2Gbk(model.getFfny()));
		model.setYrbm(service.unicode2Gbk(model.getYrbm()));
		model.setYffxh(service.unicode2Gbk(model.getYffxh()));
		model.setYffgw(service.unicode2Gbk(model.getYffgw()));
		String message ="";
		if ("12309".equals(Base.xxdm)) {
			 message = service.saveCjffForWcsy(model);
		}else {
			 message = service.saveCjffxx(model);
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 删除酬金发放信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "访问勤工助学-经费酬金管理-酬金发放-删除values:{pkValue}")
	public ActionForward czCjffxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxCjglService service = new QgzxCjglService();
		QgzxCjglForm model = (QgzxCjglForm)form;
		model.setPkValue(service.unicode2Gbk(model.getPkValue()));
		String message = service.czCjffxx(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	public ActionForward cxtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxCjglService service = new QgzxCjglService();
		QgzxCjglForm model = (QgzxCjglForm)form;
		model.setPkValue(service.unicode2Gbk(model.getPkValue()));
		String  msg = service.cxtj(model);
		Map<String,String> map=new HashMap<String, String>();
		map.put("message",msg);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONObject.fromObject(map));
		return null;
	}
	
	public ActionForward isHaveFfxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxCjglService service = new QgzxCjglService();
		QgzxCjglForm model = (QgzxCjglForm)form;
		model.setPkValue(service.unicode2Gbk(model.getPkValue()));
		Map<String,String> map=new HashMap<String, String>();
		Boolean ish=service.isHaveFfxx(model);
		map.put("message",ish.toString());
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONObject.fromObject(map));
		return null;
	}
	
	/**
	 * 根据学年 用人部门 获得岗位列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getGwdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxCjglService service = new QgzxCjglService();
		QgzxCjglForm model = (QgzxCjglForm)form;
		List<HashMap<String, String>> list = service.getGwdm(model);
		JSONArray jsonArr = JSONArray.fromArray(list.toArray());
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonArr.toString());
		return null;
	}
	
	
	/**
	 * 根据学年 用人部门 获得发放年月
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getFFny(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxCjglService service = new QgzxCjglService();
		QgzxCjglForm model = (QgzxCjglForm)form;
		List<HashMap<String, String>> list = service.getFFny(model);
		JSONArray jsonArr = JSONArray.fromArray(list.toArray());
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonArr.toString());
		return null;
	}
		
	
	/**
	 * 验证酬金发放信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkTjInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxCjglService service = new QgzxCjglService();
		QgzxCjglForm model = (QgzxCjglForm)form;
		//传输乱码问题
		model.setJe(service.unicode2Gbk(model.getJe()));
		model.setZc(service.unicode2Gbk(model.getZc()));
		String message = service.checkTjInfo(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 生成酬金标准（学号，岗位代码）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward scCjbz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxCjglService service = new QgzxCjglService();
		QgzxCjglForm model = (QgzxCjglForm)form;
		String message = service.scCjbz(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
}
