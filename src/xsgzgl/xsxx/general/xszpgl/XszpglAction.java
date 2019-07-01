/**
 * @部门:学工产品事业部
 * @日期：2013-9-26 下午01:34:07 
 */  
package xsgzgl.xsxx.general.xszpgl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WritableWorkbook;
import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xgxt.xtwh.zpgl.FileOper;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xucy[工号:754]
 * @时间： 2013-9-26 下午01:34:07 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XszpglAction extends SuperAction{
	
	private static final String url = "xsxx_tygl_xszpdr.do";

	//列表
	@SystemAuth(url = url)
	public ActionForward xszpManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszpglForm myForm = (XszpglForm) form;
		XszpglService service = new XszpglService();
		
		SearchModel searchModel=new SearchModel();
		searchModel.setPath("xsxx_tygl_xszpdr.do");
		request.setAttribute("searchTj", searchModel);
		User user = getUser(request);// 用户对象
		if (QUERY.equals(myForm.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			List<HashMap<String,String>> resultList = service.getPageList(myForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "xsxx_tygl_xszpdr.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xszpManage");
	}
	
	//导入
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-学生信息-学生照片管理-导入PHOTONAMETYPE:{photoNameType}")
	public ActionForward xszpdrManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszpglForm myForm = (XszpglForm) form;
		List<HashMap<String, String>> zpscjgList = new ArrayList<HashMap<String, String>>();
		XszpglService service = new XszpglService();
		String doType = request.getParameter("doType");
		String photoNameType = request.getParameter("photoNameType");
		if ("save".equalsIgnoreCase(doType)) {
			String tempDir = servlet.getServletContext().getRealPath("/zip");
			// 将文件上传并返回文件物理路径
			String tempFilePath = service.fileUp(tempDir, myForm.getFile(),
					myForm.getFile().getFileName(), false);
			// 解压zip
			String photoPath = service.unZip(tempFilePath, tempDir);
			// 照片入库
			zpscjgList = service.savePhoto(photoPath, request, photoNameType);
			// 上传完毕后删除照片和上传的zip
			FileOper.delFile(tempFilePath);
			FileOper.delFiles(photoPath);
		}
		if (zpscjgList != null && zpscjgList.size() > 0) {

			String modelPath = servlet.getServletContext().getRealPath("")
					+ "/print/gygl/gygl_ssnjfbtj.xls";
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
					modelPath), response.getOutputStream());

			service.printZpdr(myForm, photoNameType, request, zpscjgList, wwb);
			return null;
		}
		request.setAttribute("zpType", myForm.getZpType());
		return mapping.findForward("xszpdrManage");
	}
	
	//导出
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xszpdc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszpglForm myForm = (XszpglForm) form;
		XszpglService service = new XszpglService();
		SearchModel searchModel=new SearchModel();
		User user = getUser(request);// 用户对象
		CommService comService = new CommService();
		searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		System.out.println("zpType:"+myForm.getZpType());
		
		if (EXP.equals(myForm.getType())){
			//生成高级查询对象
			service.zpdc(myForm, response,user);
			return null;
		}
		return mapping.findForward("zpdc");
	}
	
}
