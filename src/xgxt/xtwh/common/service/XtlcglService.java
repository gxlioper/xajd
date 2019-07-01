package xgxt.xtwh.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.xtwh.common.dao.XtlcglDAO;

public class XtlcglService {
	XtlcglDAO dao = new XtlcglDAO();
	
	/**
	 * 获取功能流程级别
	 * @param gnid
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getGnlcjb(String gnid){
		
		return new ArrayList<HashMap<String,String>>();
	}
	
	/**
	 * 获取用户在审核流程中的信息
	 * @param user
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getYhxxForGnlc(User user){
		
		return new HashMap<String, String>();
	}
	
	/**
	 * 获取功能审核岗位名称信息
	 * */
	public String[] getGnshgwmcxx(String cdid){
		return dao.getGnshgwmcxx(cdid);
	}
	
	/**
	 * 获取功能审核岗位id信息
	 * */
	public String[] getGnshgwxx(String cdid){
		return dao.getGnshgwxx(cdid);
	}
	
	/**
	 * 获取某一功能当前用户所在的岗位信息
	 * */
	public List<HashMap<String, String>> getYhgwList(String cdid, String yhm){
		return dao.selectYhgwList(cdid, yhm);
	}
	
	/**
	 * 获取岗位与流程相关信息
	 * */
	public HashMap<String, String> getGwylcxgxx(String lcid, String gwid){
		return dao.selectGwylcxgxx(lcid, gwid);
	}
	
	
	/**
	 * 判断是否最终审核
	 * @param shb 审核业务表
	 * */
	public boolean sfzhyjsh(String cdid, String xtgwid,String shb){	
		boolean result = false;
		HashMap<String, String> lcxxMap = dao.getGnlcxx(cdid);
		//同级别参与方式
		String tjbcyfs = lcxxMap.get("tjbcyfs");
		//参与方式为可不参与 、部分参与 ,最后一级别的审核岗位用户为最终审核
		if("可不参与".equalsIgnoreCase(tjbcyfs) 
				|| "部分参与".equalsIgnoreCase(tjbcyfs)){
			result = dao.lastLevel(lcxxMap.get("lcid"), xtgwid);
		}else if("全部参与".equalsIgnoreCase(tjbcyfs)){		
			//参与方式为 全部参与的，最后一级审核为最后一级别的最后一个用户审核为最终审核
			result = dao.lastStation(lcxxMap.get("lcid"), xtgwid, shb);
		}
		return result;
	}
	
}
