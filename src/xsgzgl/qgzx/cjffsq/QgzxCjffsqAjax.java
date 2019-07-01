package xsgzgl.qgzx.cjffsq;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.zdydr.service.ZdydrService;
import com.zfsoft.xgxt.dekt.xfsq.DektxfsqService;
import com.zfsoft.xgxt.qgzx.xsgw.XsgwshForm;
import com.zfsoft.xgxt.qgzx.xsgw.XsgwshService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.struts.upload.FormFile;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.date.DateUtils;
import xsgzgl.qgzx.QgCommUtil.QgCommUtilf;
import xsgzgl.qgzx.cssz.QgzxCsszService;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.rcsw.tsqktbgl.sh.TsqkshForm;
/**
 * 勤工助学-酬金管理-酬金信息管理
 * @author yeyipin
 * @since 2012.7.23
 */
public class QgzxCjffsqAjax extends SuperAction{
	
	
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
		QgzxCjffsqService service = new QgzxCjffsqService();
		QgzxCjffsqForm myForm = (QgzxCjffsqForm)form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, myForm, request, user);
		String qgzq = QgCommUtilf.getQgzq();
		myForm.getSearchModel().setPath("qgzx_cjffsq_cjxxgl.do");
		if("xq".equals(qgzq)){
			myForm.getSearchModel().setPath("qgzx_cjffsq_cjxxgl_xq.do");
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
		QgzxCjffsqService service = new QgzxCjffsqService();
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
		QgzxCjffsqService service = new QgzxCjffsqService();
		QgzxCjffsqForm myForm = (QgzxCjffsqForm)form;
		myForm.setGwdm(request.getParameter("gwdm"));
		myForm.setZgzt(request.getParameter("zgzt"));
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("qgzx_cjffsq_cjxxgl.do");
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

		QgzxCjffsqService service = new QgzxCjffsqService();
		QgzxCjffsqForm model = (QgzxCjffsqForm)form;
		//传输乱码问题
		model.setXh(service.unicode2Gbk(model.getXh()));
		model.setGwdm(service.unicode2Gbk(model.getGwdm()));
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
		QgzxCjffsqService service = new QgzxCjffsqService();
		QgzxCjffsqForm model = (QgzxCjffsqForm)form;
		model.setPkValue(service.unicode2Gbk(model.getPkValue()));
		User user= getUser(request);
		String message = service.czCjffxx(model,user);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	public ActionForward cxtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxCjffsqService service = new QgzxCjffsqService();
		QgzxCjffsqForm model = (QgzxCjffsqForm)form;
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
		QgzxCjffsqService service = new QgzxCjffsqService();
		QgzxCjffsqForm model = (QgzxCjffsqForm)form;
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
		QgzxCjffsqService service = new QgzxCjffsqService();
		QgzxCjffsqForm model = (QgzxCjffsqForm)form;
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
		QgzxCjffsqService service = new QgzxCjffsqService();
		QgzxCjffsqForm model = (QgzxCjffsqForm)form;
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
		QgzxCjffsqService service = new QgzxCjffsqService();
		QgzxCjffsqForm model = (QgzxCjffsqForm)form;
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
		QgzxCjffsqService service = new QgzxCjffsqService();
		QgzxCjffsqForm model = (QgzxCjffsqForm)form;
		String message = service.scCjbz(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	public ActionForward cjxxSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxCjffsqForm model = (QgzxCjffsqForm)form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// 查询
		QgzxCjffsqService service = new QgzxCjffsqService();
		List<HashMap<String, String>> resultList = service.getCjxxShList(model, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @描述：单个审核
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年6月13日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * ActionForward 返回类型
	 */
	public ActionForward dgsh(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxCjffsqForm model = (QgzxCjffsqForm)form;
		User user = getUser(request);
		// 保存单个审核
		QgzxCjffsqService service = new QgzxCjffsqService();
		boolean result = service.saveSh(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @描述：批量审核///暂时不用
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年6月14日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * ActionForward 返回类型
	 */
	public ActionForward plsh(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxCjffsqForm model = (QgzxCjffsqForm)form;
		User user = getUser(request);
		// 保存单个审核
		QgzxCjffsqService service = new QgzxCjffsqService();
		String message = service.savePlsh(model, user);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	public ActionForward cxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxCjffsqForm model = (QgzxCjffsqForm)form;
		QgzxCjffsqService service = new QgzxCjffsqService();
		User user = getUser(request);
		
		HashMap<String,String> shxx = ShlcUtil.getShxx(model.getShid());
		
		String cancelFlag = service.cxsh(shxx.get("ywid"),model,user);		

		String messageKey=Constants.CANCLE_FLG_SUCCESS.equals(cancelFlag)||Constants.CANCLE_FLG_SUCCESS_ZHYJ.equals(cancelFlag)?
				 MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		//最后一级撤销成功
		if(Constants.CANCLE_FLG_SUCCESS_ZHYJ.equals(cancelFlag)){
			boolean isSuccess = service.cxshdel(model);
			messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	/**
	 * @description	：是否可提交（青岛酒店个性化）
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-21 下午03:02:31
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkSfktj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxCjffsqService service = new QgzxCjffsqService();
		QgzxCjffsqForm model = (QgzxCjffsqForm)form;
		model.setPkValue(service.unicode2Gbk(model.getPkValue()));
		String message = service.sfTj(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	public ActionForward downloadTemplate(ActionMapping mapping, ActionForm form,
										  HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZdydrService zdydrService = new ZdydrService();
		//获取导入模块代码
		String drmkdm = request.getParameter("drmkdm");

		//响应头设置
		response.setHeader("Content-disposition", "attachment;filename="+ URLEncoder.encode(drmkdm+".xls","UTF-8"));
		zdydrService.createWwb(response.getOutputStream(),drmkdm);
		return null;
	}
	public ActionForward downloadImportError(ActionMapping mapping, ActionForm form,
											 HttpServletRequest request, HttpServletResponse response) throws Exception {

		//得到tomcat/webapp/temp/importTemp下错误文件的路径
		String filename = request.getParameter("filename");
		String path = servlet.getServletContext().getRealPath("/temp/importTemp/");
		File file = new File(path,filename);
		if (file.exists()){
			response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode(filename,"utf-8"));
			FileUtil.outputFile(response, file);
		}
		return null;
	}
	public ActionForward saveImport(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response) throws Exception {

		QgzxCjffsqService service = new QgzxCjffsqService();
		//获取导入模块代码
		String drmkdm = request.getParameter("drmkdm");

		//得到FormFile对象，读取上传的Excel文件
		QgzxCjffsqForm model = (QgzxCjffsqForm)form;
		FormFile formFile = model.getImportFile();
		User user = getUser(request);
		//返回保存结果：模版有误、导入成功信息、导入失败信息
		String path = servlet.getServletContext().getRealPath("/temp/importTemp/");
		HashMap<String,Object> resultMap = service.saveImport(formFile.getInputStream(),path,drmkdm,user);

		JSONObject resultJson = JSONObject.fromObject(resultMap);
		response.getWriter().print(resultJson);
		return null;
	}

	public ActionForward exportData(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxCjffsqForm model = (QgzxCjffsqForm)form;

		//生成高级查询对象
		CommService comService = new CommService();
		QgzxCjffsqService service = new QgzxCjffsqService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.export(model,user);

		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcglbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
}
