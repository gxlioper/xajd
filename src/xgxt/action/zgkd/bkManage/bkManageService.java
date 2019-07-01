/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description:板块管理Service </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-7-7 上午11:21:10</p>
 */
package xgxt.action.zgkd.bkManage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.zgkd.SyltForm;

public class bkManageService {
	/** 模块所用到列表 */

	bkManageDao dao = null;

	/**
	 * 获取页面列表（用户列表，版块列表）
	 * 
	 * @param index
	 * @return
	 */
	public List<HashMap<String, String>> getCommonList(int index) {
		List<HashMap<String, String>> list = null;
		bkManageDao dao = new bkManageDao();
		if (index == 1) {
			list = dao.getCommonList(1);
		} else {
			list = dao.getCommonList(2);
		}
		return list;
	}

	/**
	 * 获取版块管理员匹配查询表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getBkGlyppTitle() throws Exception {
		dao = new bkManageDao();
		return dao.getBkGlyppTitle();
	}

	/**
	 * 获取版块管理员匹配查询结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getBkGlyppResult(SyltForm bkglModel) throws Exception {
		dao = new bkManageDao();
		return dao.getBkGlyppResult(bkglModel);
	}

	/**
	 * 验证用户是否注册
	 * 
	 * @param yhm
	 * @return
	 * @throws Exception
	 */
	public boolean chkYhmisReg(String yhm) throws Exception {
		dao = new bkManageDao();
		return dao.chkYhmisReg(yhm);
	}

	/**
	 * 获取用户其它信息:昵称,个人签名
	 * 
	 * @param yhm
	 * @return
	 * @throws Exception
	 */
	public String[] getYhInfo(String yhm) throws Exception {
		dao = new bkManageDao();
		return dao.getYhInfo(yhm);
	}

	/**
	 * 版块管理信息保存
	 * 
	 * @param bkglModel
	 * @return
	 * @throws Exception
	 */
	public boolean bkglInfoSave(BkglyPpModel bkglModel,
			HttpServletRequest request) throws Exception {
		dao = new bkManageDao();
		return dao.bkglInfoSave(bkglModel, request);
	}

	/**
	 * 获取版块管理员匹配信息
	 * 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getBkglyPpInfo(String pkValue)
			throws Exception {
		dao = new bkManageDao();
		return dao.getBkglyPpInfo(pkValue);
	}

	/**
	 * 版块管理员匹配信息批量删除
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String delBkglyPpInfo(String[] keys, HttpServletRequest request)
			throws Exception {
		dao = new bkManageDao();
		return dao.delBkglyPpInfo(keys, request);
	}

	/**
	 * 版块管理员匹配信息修改
	 * 
	 * @param pkValue
	 * @param yhm
	 * @param bkdm
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean bkglInfoModi(String pkValue, String yhm, String bkdm,
			HttpServletRequest request) throws Exception {
		dao = new bkManageDao();
		return dao.bkglInfoModi(pkValue, yhm, bkdm, request);
	}

	/**
	 * 获取用户姓名
	 * 
	 * @param yhm
	 * @return
	 * @throws Exception
	 */
	public String[] getYhxm(String yhm) throws Exception {
		dao = new bkManageDao();
		return dao.getYhxm(yhm);
	}

	/** 获取板块维护查询结果 */
	public ArrayList<String[]> getBKResult(SyltForm syltForm) {
		ArrayList<String[]> result = new ArrayList<String[]>();
		bkManageDao dao = new bkManageDao();
		result = dao.BKResult(syltForm);
		return result;
	}

	public List<HashMap<String, String>> getBkDmMcList() {
		bkManageDao dao = new  bkManageDao();
		return dao.getBkDmMcList();
	}

	/** 获取板块维护查询结果表头 */
	public ArrayList<HashMap<String, String>> getBKWHSearchTitle() {
		ArrayList<HashMap<String, String>> result = 
			new ArrayList<HashMap<String, String>>();
		bkManageDao dao = new bkManageDao();
		result = dao.BKResultTitle();
		return result;
	}

	/**
	 * 板块添加保存
	 * 
	 * @throws Exception
	 */
	public boolean bkInfoSave(BkWeiHuModel model, HttpServletRequest request)
			throws Exception {
		dao = new bkManageDao();
		return dao.bkwhaddsave(model, request);
	}

	/** 返回单个板块信息 */
	public HashMap<String, String> viewBKInfo(String pkValue) {
		HashMap<String, String> map = new HashMap<String, String>();
		dao = new bkManageDao();
		map = dao.viewBKInfo(pkValue);
		return map;
	}

	/**
	 * 板块信息批量删除
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public int bkInFoDel(String[] keys, HttpServletRequest request)
			throws Exception {
		dao = new bkManageDao();
		return dao.bkInFoDel(keys, request);
	}
}
