/**
 * @部门：学工产品事业部
 * @日期：2016年11月17日
 */  
package com.zfsoft.xgxt.dekt.xfsq;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.zdydr.service.ZdydrService;
import com.zfsoft.xgxt.dekt.xmwh.DektxmwhForm;
import com.zfsoft.xgxt.dekt.xmwh.DektxmwhService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @系统名称：学生工作管理系统
 * @模块名称：第二课堂-项目维护 管理模块
 * @类功能描述：
 * @作者：卓耐[工号:1391]
 * @时间：2017年7月24日
 * @版本：V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class DektxfsqAction extends SuperAction<DektxfsqForm,DektxfsqService> {
	private DektxfsqService dektxfsqService = new  DektxfsqService();
	private DektxmwhService dektxmwhService = new  DektxmwhService();
	private ZdydrService zdydrService = new ZdydrService();
	private static final String url = "dekt_xfsq_list.do";
	
	public ActionForward xfsqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xfsqList");
	}
	
	
	public ActionForward getXfsqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DektxfsqForm model = (DektxfsqForm) form;
		User user = getUser(request);
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		// 查询
		List<HashMap<String, String>> resultList = dektxfsqService.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	
	public ActionForward xfsqSqlb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("path", "dekt_xfsq_sqlb.do");
		String xmdl = request.getParameter("type");
		FormModleCommon.commonRequestSet(request);
		List<HashMap<String,Object>> sqlb=new ArrayList<HashMap<String,Object>>();
		List<HashMap<String, String>> lxList=dektxmwhService.getLx(xmdl);
		for(HashMap<String, String> lxmap:lxList){
			HashMap<String,Object>map=new HashMap<String,Object>();
			map.put("lx", lxmap.get("lx"));
			map.put("tb", lxmap.get("tb"));
			map.put("rdxm", dektxmwhService.getRdxm(lxmap.get("lx")));
			sqlb.add(map);
		}
		request.setAttribute("sqlb", sqlb);
		
		return mapping.findForward("xfsqSqlb");
	}
	
	public ActionForward xfsqView(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DektxfsqForm model = (DektxfsqForm) form;
		XsxxService xsxxService = new XsxxService();
		//学生信息
		HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz("dektxfgl");
		request.setAttribute("jbxxList", jbxxList);
		//业务信息
		request.setAttribute("model", dektxfsqService.getView(model));
		return mapping.findForward("xfsqView");
	}
	
	
	
	public ActionForward xfsqAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String lx=URLDecoder.decode(request.getParameter("lx"),"UTF-8");
		request.setAttribute("lx", lx);
		DektxmwhForm xmwhForm=new DektxmwhForm();
		xmwhForm.setLx(lx);
		List<HashMap<String,String>> xmlist=dektxmwhService.getAllList(xmwhForm);
		request.setAttribute("xmlist", xmlist);
		request.setAttribute("rdxmlist", dektxmwhService.getRdxm(lx));
		return mapping.findForward("xfsqAdd");
	}

	public ActionForward getRdnrbzList(ActionMapping mapping, ActionForm form,
									   HttpServletRequest request, HttpServletResponse response) throws Exception {
		String lx=request.getParameter("lx");
		String rdxm=URLDecoder.decode(request.getParameter("rdxm"),"UTF-8");
		List<HashMap<String, String>> list=dektxmwhService.getRdnrbz(lx, rdxm);
		JSONArray jsonArr = JSONArray.fromArray(list.toArray());
		response.getWriter().write(jsonArr.toString());
		return null;
	}
	
	
	public ActionForward getDjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String lx=URLDecoder.decode(request.getParameter("lx"),"UTF-8");
		String rdxm=request.getParameter("rdxm");
		String rdnrbz=request.getParameter("rdnrbz");
		List<HashMap<String, String>> list=dektxmwhService.getDj(lx, rdxm,rdnrbz);
		JSONArray jsonArr = JSONArray.fromArray(list.toArray());
		response.getWriter().write(jsonArr.toString());
		return null;
	}
	
	
	public ActionForward xfsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DektxfsqForm myForm = (DektxfsqForm) form;
		User user= getUser(request);
		myForm.setXh(user.getUserName());
		// 唯一性判断
		if (!dektxfsqService.checkExist(myForm)) {
			//暂不做修改功能
			//boolean result =StringUtils.isNull(myForm.getSqid())?dektxfsqService.runInsert(myForm):dektxfsqService.runUpdate(myForm);
			String sqid= UniqID.getInstance().getUniqIDHash();
			myForm.setSqid(sqid);
			boolean result=dektxfsqService.saveSq(myForm);
			if(result){
				result=dektxfsqService.tjSq(myForm);
			}
			String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS:MessageKey.SYS_SUBMIT_FAIL;
			response.getWriter().print(getJsonResult(messageKey,result));
		} else {
			response.getWriter().print(getJsonResult(MessageKey.SYS_AUD_DOUBLE, false));
		}
		return null;
	}

	public ActionForward xfSqSubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		DektxfsqForm myForm = (DektxfsqForm) form;
		boolean result=dektxfsqService.tjSq(myForm);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DektxfsqForm model = (DektxfsqForm) form;
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = dektxfsqService.getAllList(model,user);// 查询出所有记录，不分页
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// 当前操作员
		exportModel.setDataList(resultList);// 设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);// 生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * @描述：删除/暂时不用
	 * @作者：zhuon[工号:1391]
	 * @日期：2017年7月25日
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	public ActionForward DektxfsqDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = dektxfsqService.runDelete(ids);
			String message = num > 0 ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}


	/**
	 *  个性化导入页面.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-10-09 13:57
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return org.apache.struts.action.ActionForward
	 * @throw
	 */
	public ActionForward xfsqZdyImport(ActionMapping mapping, ActionForm form,
							HttpServletRequest request, HttpServletResponse response) throws Exception {

        //获取导入模块代码
        String drmkdm = request.getParameter("drmkdm");
        //查询模版信息
        HashMap<String,String> drmbxx = zdydrService.getDrmbxx(drmkdm);
        //查询导入规则信息
        List<HashMap<String,String>>  drgzxxList =  zdydrService.getDrgzxxList(drmkdm);

        request.setAttribute("drmbxx", drmbxx);
        request.setAttribute("drgzxxList", drgzxxList);
        return mapping.findForward("xfsqImport");
	}

	/**
	 *  个性化导入模版下载.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-10-09 14:32
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return org.apache.struts.action.ActionForward
	 * @throw
	 */
	public ActionForward downloadTemplate(ActionMapping mapping, ActionForm form,
									  HttpServletRequest request, HttpServletResponse response) throws Exception{

        //获取导入模块代码
        String drmkdm = request.getParameter("drmkdm");

		//响应头设置
		response.setHeader("Content-disposition", "attachment;filename="+ URLEncoder.encode(drmkdm+".xls","UTF-8"));
		dektxfsqService.createWwb(response.getOutputStream(),drmkdm);
		return null;
	}

    /**
     *  保存导入.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2017-10-10 20:13
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    public ActionForward saveImport(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {

        //获取导入模块代码
        String drmkdm = request.getParameter("drmkdm");

        //得到FormFile对象，读取上传的Excel文件
        DektxfsqForm dektxfsqForm = (DektxfsqForm)form;
        FormFile formFile = dektxfsqForm.getImportFile();

        //返回保存结果：模版有误、导入成功信息、导入失败信息
        String path = servlet.getServletContext().getRealPath("/temp/importTemp/");
        HashMap<String,Object> resultMap = dektxfsqService.saveImport(formFile.getInputStream(),path,drmkdm);

        JSONObject resultJson = JSONObject.fromObject(resultMap);
        response.getWriter().print(resultJson);
        return null;
    }

    /**
     *  下载导入错误文件.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2017-10-10 20:14
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
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
}