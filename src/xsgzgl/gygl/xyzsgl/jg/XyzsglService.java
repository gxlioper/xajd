package xsgzgl.gygl.xyzsgl.jg;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2015-5-20 下午01:51:42 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class XyzsglService  extends SuperServiceImpl<XyzsglForm, XyzsglDao> {
	XyzsglDao dao = new XyzsglDao();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	public List<HashMap<String,String>> getZyjzxx(XyzsglForm model){
		return dao.getZyjzxx(model);
	}
	
	/**
	 * 判断当前学生是否有校外住宿结果
	 */
	public boolean checkExistForSave(XyzsglForm model){
		return dao.checkExistForSave(model);
	}

	/**
	 * 
	 * 保存增加结果
	 */
	public boolean saveZsjg(XyzsglForm model, User user) throws Exception {
		model.setXn(Base.currXn);
		model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
//		boolean flag = dao.saveZsjg(model, user);
		boolean flag = dao.runInsert(model);
		return flag;
	}
	/**
	 * 
	 * 保存修改结果
	 */
	public boolean saveZsjgUpdate(XyzsglForm model, User user) throws Exception {
		boolean flag = dao.runUpdate(model);
		return flag;
	}
	/*
	 * 导出申请表的时候获取学生基本信息以及住宿申请信息
	 */
	public HashMap<String, String> getXyzsxxMap(XyzsglForm model, User user) throws Exception {
		
		HashMap<String, String> xsxxmap = dao.getXyzsxxMap(model, user);
	
		return xsxxmap;
	}
	
   public HashMap<String, String> getXyzsxxMap1(XyzsglForm model, User user) throws Exception {
		
		HashMap<String, String> xsxxmap = dao.getXyzsxxMap1(model, user);
	
		return xsxxmap;
	}
	
	/**
	 *获取学历代码和学历名称map,给下拉框使用
     *
	 */
	public List<HashMap<String, String>> getXl(XyzsglForm model) {
		return dao.getXl(model);
	}
	
	/**
	 * 查看时显示学历名称
	 */
	public HashMap<String, String> getXlCk(XyzsglForm model) {
		return dao.getXlCk(model);
	}
	
	/**
	 * 在外居住原因
	 */
	public HashMap<String, String> getXyZsyy(XyzsglForm model) {
		return dao.getXyZsyy(model);
	}
}
