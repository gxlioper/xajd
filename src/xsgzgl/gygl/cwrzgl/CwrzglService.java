package xsgzgl.gygl.cwrzgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xsgzgl.gygl.comm.GyglNewService;

public class CwrzglService extends GyglNewService{
	
	CwrzglDao dao = new CwrzglDao();
	public ArrayList<String[]> getCwrzglInfoList(CwrzglForm myForm,HttpServletRequest request) 
		throws Exception{		
		return dao.getCwrzglInfoList(myForm,request);
	}	
	
	/**
	 * 统计楼栋的分配信息
	 * @param request
	 * @param lddm
	 */
	public void tjldFpxx(HttpServletRequest request,CwrzglForm myForm){
		dao.tjldFpxx(request, myForm);
	}
	
	/**
	 * 获取楼栋列表
	 * @return
	 */
	public List<HashMap<String,String>> getLdList(CwrzglForm myForm){
		return dao.getLdList(myForm);
	}
	
	/**
	 * 保存学生入住分配信息
	 * @param request
	 * @return
	 */
	public boolean saveXsrzfpxx(HttpServletRequest request,CwrzglForm myForm){
		return dao.saveXsrzfpxx(request,myForm);
	}
	
	/**
	 * 取消学生入住信息
	 * @param request
	 * @return
	 */
	public boolean qxXsrzfpxx(HttpServletRequest request,CwrzglForm myForm){
		return dao.qxXsrzfpxx(request,myForm);
	}
	
	/**
	 * 获取指定学院年级的统计人数
	 * @param inputV
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,String> getCwfpglInfo(CwrzglForm myForm){		
		return dao.getCwfpglInfo(myForm);
	}
	
	/**
	 * 获取学院年级性别的楼栋分配情况
	 * @param inputValue
	 * @return
	 */
	public List<HashMap<String,String>> getXynjxbLdfpxx(CwrzglForm myForm){
		return dao.getXynjxbLdfpxx(myForm);
	}
	
	/**
	 * 导入数据
	 * @param filePath
	 * @param request
	 * @return
	 */
	public String importData(HttpServletRequest request,HttpServletResponse response){
		return dao.importData(request,response);
	}
	
	/**
	 * 是否可取消入住
	 * @param myForm
	 * @return
	 */
	public boolean isKqxrz(CwrzglForm myForm){
		return dao.isKqxrz(myForm);
	}
	
	//#############################################以下为学院部分
	
	/**
	 * 床位分配管理信息列表（学院）
	 */
	public ArrayList<String[]> getCwfpglInfoList_xy(HttpServletRequest request,CwrzglForm myForm) 
	throws Exception{		
		return dao.getCwrzglInfoList_xy(request,myForm);
	}	
	
	/**
	 * 统计楼栋的分配信息（学院）
	 * @param request
	 * @param lddm
	 */
	public void tjldFpxx_xy(HttpServletRequest request,CwrzglForm myForm){
		dao.tjldFpxx_xy(request, myForm);
	}
	
	/**
	 * 获取楼栋列表（学院）
	 * @return
	 */
	public List<HashMap<String,String>> getLdList_xy(CwrzglForm myForm){
		return dao.getLdList_xy(myForm);
	}
	
	/**
	 *  根据性别、班级代码，获取分配到专业的楼栋列表（已专业分配方式）
	 * @return
	 */
	public List<HashMap<String,String>> getLdList_zy(CwrzglForm myForm){
		return dao.getLdList_zy(myForm);
	}
	
	/**
	 * 获取指定学院年级的统计人数（学院）
	 * @param inputV
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,String> getCwfpglInfo_xy(CwrzglForm myForm){		
		return dao.getCwfpglInfo_xy(myForm);
	}
	
	/**
	 * 获取学院年级性别的楼栋分配情况（学院）
	 * @param inputValue
	 * @return
	 */
	public List<HashMap<String,String>> getXynjxbLdfpxx_xy(CwrzglForm myForm){
		return dao.getXynjxbLdfpxx_xy(myForm);
	}
	
	/**
	 * 获取学院年级性别的楼栋分配情况（专业分配方式）
	 * @param inputValue
	 * @return
	 */
	public List<HashMap<String,String>> getXynjxbLdfpxx_zy(CwrzglForm myForm){
		return dao.getXynjxbLdfpxx_zy(myForm);
	}

	/** 
	 * @描述:(床位入住管理-获取当前专业的床位信息)以专业对象分配床位方式
	 * @作者：cmj[工号：913]
	 * @日期：2013-9-5 下午03:53:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @param myForm
	 * void 返回类型 
	 * @throws 
	 */
	public void tjldFpxx_zy(HttpServletRequest request, CwrzglForm myForm) {
		dao.tjldFpxx_zy(request,myForm);
	}
	
	/** 
	 * @描述:获取语种类别(新疆医科大学厚博学院个性化专用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-3-24 上午11:00:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getYzlbList() {
		return dao.getYzlbList();
	}
}
