package xsgzgl.gygl.cwfpgl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mortbay.http.HttpRequest;

import xgxt.action.Base;
import xsgzgl.gygl.comm.GyglNewService;

public class CwfpglService extends GyglNewService{
	
	CwfpglDao dao = new CwfpglDao();
	/**
	 * 
	 * 取得年级学院统计
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * ArrayList<String[]> 返回类型 
	 * @throws
	 */
	public ArrayList<String[]> getCwfpglInfoList(CwfpglForm myForm,HttpServletRequest request) 
		throws Exception{
		
		return dao.getCwfpglInfoList(myForm,request);
	}

	/**
	 * 统计楼栋的分配信息
	 * @param request
	 * @param lddm
	 */
	public void tjldFpxx(HttpServletRequest request,CwfpglForm myForm){
		dao.tjldFpxx(request, myForm);
	}
	
	/**
	 * 获取楼栋列表
	 * @return
	 */
	public List<HashMap<String,String>> getLdList(String ldxb){
		return dao.getLdList(ldxb);
	}
	
	/**
	 * 获取楼栋列表(用户如果是公寓管理员，公寓管理员数据范围控制)
	 * @return
	 */
	public List<HashMap<String,String>> getLdListByGyfdy(HttpServletRequest request,String ldxb){
		String cwzt = request.getParameter("cwzt");
		String xydm = request.getParameter("xydm");
		return dao.getLdListByGyfdy(request,ldxb,cwzt,xydm);
	}
	
	/**
	 * 获取所选学院已分配楼栋列表(用户如果是公寓管理员，公寓管理员数据范围控制)
	 * @return
	 */
	public List<HashMap<String,String>> getCwfpLdListByGyfdy(HttpServletRequest request,String ldxb){
		String primarykey_checkVal= request.getParameter("primarykey_checkVal");
		String xy = request.getParameter("xydm");
		if(!Base.isNull(primarykey_checkVal)){
			String[] temp = primarykey_checkVal.split("_");
			xy=temp[0];
		}
		return dao.getCwfpLdListByGyfdy(request,ldxb,xy);
	}
	
	/**
	 * 保存寝室床位分配信息
	 * @param request
	 * @return
	 */
	public boolean saveQscwfpxx(HttpServletRequest request,CwfpglForm myForm){
		return dao.saveQscwfpxx(request,myForm);
	}
	
	/**
	 * 取消寝室床位分配信息
	 * @param request
	 * @return
	 */
	public boolean qxQscwfpxx(HttpServletRequest request,CwfpglForm myForm){
		return dao.qxQscwfpxx(request,myForm);
	}
	
	/**
	 * 获取指定学院年级的统计人数
	 * @param inputV
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,String> getCwfpglInfo(CwfpglForm myForm){		
		return dao.getCwfpglInfo(myForm);
	}
	
	/**
	 * 获取学院年级性别的楼栋分配情况
	 * @param inputValue
	 * @return
	 */
	public List<HashMap<String,String>> getXynjxbLdfpxx(CwfpglForm myForm){
		return dao.getXynjxbLdfpxx(myForm);
	}

	public List<HashMap<String,String>> loadLdlist(CwfpglForm myForm){
		return dao.loadLdlist(myForm);
	}
	/**
	 * 
	 * @描述:联动加载楼栋
	 * @作者：xiaxia
	 * @日期：2014-9-12 下午03:23:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> cwfpglInitLdList(HttpServletRequest request ){
		String xydm = request.getParameter("xydm");
		String cwzt = request.getParameter("cwzt");
		String zydm = request.getParameter("zydm");
		String xbdm = request.getParameter("xbdm");
		return dao.cwfpglInitLdList(request,xydm, zydm, cwzt,xbdm);
		
	}
	/**
	 * 床位分配管理信息列表
	 * 取得年级学院专业统计
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getCwfpglInfoList_zy(CwfpglForm myForm) 
	throws Exception{
	
		return dao.getCwfpglInfoList_zy(myForm);
	}
	
	/**
	 * 床位分配管理信息列表（学院）
	 * 取得年级学院专业班级统计
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getCwfpglInfoList_xy(CwfpglForm myForm) 
	throws Exception{
	
		return dao.getCwfpglInfoList_xy(myForm);
	}
	
	/**
	 * 统计楼栋的分配信息(按专业统计)
	 * @param request
	 * @param lddm
	 */
	public void tjldFpxx_zy(HttpServletRequest request,CwfpglForm myForm){
		dao.tjldFpxx_zy(request, myForm);
	}
	
	/**
	 * 统计楼栋的分配信息(学院)
	 * @param request
	 * @param lddm
	 */
	public void tjldFpxx_xy(HttpServletRequest request,CwfpglForm myForm){
		dao.tjldFpxx_xy(request, myForm);
	}
	
	/**
	 * 获取楼栋列表(学院)
	 * @return
	 */
	public List<HashMap<String,String>> getLdList_xy(CwfpglForm myForm){
		return dao.getLdList_xy(myForm);
	}
	/**
	 * 保存寝室床位分配信息(按專業保存)
	 * @param request
	 * @return
	 */
	public boolean saveQscwfpxx_zy(HttpServletRequest request,CwfpglForm myForm){
		return dao.saveQscwfpxx_zy(request,myForm);
	}
	
	/**
	 * 保存寝室床位分配信息(学院)
	 * @param request
	 * @return
	 */
	public boolean saveQscwfpxx_xy(HttpServletRequest request,CwfpglForm myForm){
		return dao.saveQscwfpxx_xy(request,myForm);
	}
	
	/**
	 * 取消寝室床位分配信息(按專業取消分配)
	 * @param request
	 * @return
	 */
	public boolean qxQscwfpxx_zy(HttpServletRequest request,CwfpglForm myForm){
		return dao.qxQscwfpxx_zy(request,myForm);
	}
	
	/**
	 * 取消寝室床位分配信息(学院)
	 * @param request
	 * @return
	 */
	public boolean qxQscwfpxx_xy(HttpServletRequest request,CwfpglForm myForm){
		return dao.qxQscwfpxx_xy(request,myForm);
	}
	/**
	 * 获取指定学院专业年级的统计人数
	 * @param inputV
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,String> getCwfpglInfo_zy(CwfpglForm myForm){		
		return dao.getCwfpglInfo_zy(myForm);
	}
	
	/**
	 * 获取指定学院年级的统计人数（学院）
	 * @param inputV
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,String> getCwfpglInfo_xy(CwfpglForm myForm){		
		return dao.getCwfpglInfo_xy(myForm);
	}
	/**
	 * 获取学院专业年级性别的楼栋分配情况
	 * @param inputValue
	 * @return
	 */
	public List<HashMap<String,String>> getXynjxbLdfpxx_zy(CwfpglForm myForm){
		return dao.getXynjxbLdfpxx_zy(myForm);
	}
	/**
	 * 获取学院年级性别的楼栋分配情况
	 * @param inputValue
	 * @return
	 */
	public List<HashMap<String,String>> getXynjxbLdfpxx_xy(CwfpglForm myForm){
		return dao.getXynjxbLdfpxx_xy(myForm);
	}
	
	/**
	 * 加载楼栋列表（学院）
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String,String>> loadLdlist_xy(CwfpglForm myForm){
		return dao.loadLdlist_xy(myForm);
	}
}
