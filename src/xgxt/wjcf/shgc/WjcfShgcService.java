package xgxt.wjcf.shgc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description: 上海工程技术大学违纪处分Service
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: 李涛
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time: 2008-05-19
 * </p>
 */
public class WjcfShgcService {

	WjcfShgcDAO dao = null;// 公用DAO

	/**
	 * 获取处分类别列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCflbList() throws Exception {
		dao = new WjcfShgcDAO();
		List<HashMap<String, String>> cflbList = new ArrayList<HashMap<String, String>>();
		cflbList = dao.getCflbList();
		return cflbList;
	}

	/**
	 * 获取处分原因列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCfyyList() throws Exception {
		dao = new WjcfShgcDAO();
		List<HashMap<String, String>> cfyyList = new ArrayList<HashMap<String, String>>();
		cfyyList = dao.getCfyyList();
		return cfyyList;
	}

	/**
	 * 获取学生信息
	 * 
	 * @param xh
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getQryStuInfo(String xh, String userType)
			throws Exception {
		HashMap<String, String> map = null;
		dao = new WjcfShgcDAO();
		map = dao.getQryStuInfo(xh, userType);
		return map;
	}

	/**
	 * 保存学院申报信息
	 * 
	 * @param shgcxysbModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveXysbXx(WjcfShgcXysbModel shgcxysbModel, String xn,
			String nd) throws Exception {
		dao = new WjcfShgcDAO();
		boolean flag = false;
		flag = dao.saveXysbXx(shgcxysbModel, xn, nd);
		return flag;
	}

	/**
	 * 获取当前学年年度列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String[] getXnNdList() throws Exception {
		dao = new WjcfShgcDAO();
		String[] xnndList = null;
		xnndList = dao.getXnNdList();
		return xnndList;
	}

	/**
	 * 获取表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getSearchTitle() throws Exception {
		dao = new WjcfShgcDAO();
		ArrayList<HashMap<String, String>> result = dao.getSearchTitle();
		return result;
	}

	/**
	 * 获取结果
	 * 
	 * @param shgcxxshqryModel
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getSearchResult(
			WjcfShgcXxshQryModel shgcxxshqryModel) throws Exception {
		dao = new WjcfShgcDAO();
		ArrayList<String[]> result = dao.getSearchResult(shgcxxshqryModel);
		return result;
	}

	/**
	 * 学校审核列表
	 * 
	 * @param type
	 * @return
	 */
	public List<HashMap<String, String>> getChkList(int type) {
		dao = new WjcfShgcDAO();
		List<HashMap<String, String>> map = dao.getChkList(3);
		return map;
	}

	/**
	 * 获取学校审核的单个学生信息
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXxShInfo(String pkVal) throws Exception {
		dao = new WjcfShgcDAO();
		HashMap<String, String> map = dao.getXxShInfo(pkVal);
		return map;
	}

	/**
	 * 违纪处分学校审核信息保存
	 * 
	 * @param shgcxxshModel
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public boolean saveXxshInfo(WjcfShgcXxshModel shgcxxshModel, String pkVal)
			throws Exception {
		dao = new WjcfShgcDAO();
		boolean flag = dao.saveXxshInfo(shgcxxshModel, pkVal);
		;
		return flag;
	}

	/**
	 * 批量删除学校审核信息
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String delxxshInfo(String[] keys) throws Exception {
		dao = new WjcfShgcDAO();
		String pk = dao.delxxshInfo(keys);
		return pk;
	}

	/**
	 * 通过PKVAL获取学生信息
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getwjcfysbInfo(String pkVal, String xh, String cflb, String cfyy)
			throws Exception {
		dao = new WjcfShgcDAO();
		HashMap<String, String> map = dao.getwjcfysbInfo(pkVal, xh, cflb, cfyy);
		return map;
	}

	/**
	 * 获取日期和文号维护查询表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getSearchTitle1()
			throws Exception {
		dao = new WjcfShgcDAO();
		ArrayList<HashMap<String, String>> result = dao.getSearchTitle1();
		return result;
	}

	/**
	 * 日期和文号维护查询结果
	 * 
	 * @param shgcxxshqryModel
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getSearchResult1(
			WjcfShgcXxshQryModel shgcxxshqryModel) throws Exception {
		dao = new WjcfShgcDAO();
		ArrayList<String[]> result = dao.getSearchResult1(shgcxxshqryModel);
		return result;
	}

	/**
	 * 日期和文号维护
	 * 
	 * @param pkVal
	 * @param cfsj
	 * @param cfwh
	 * @return
	 * @throws Exception
	 */
	public boolean wjcfrqwhsh(String pkVal, String cfsj, String cfwh, String cflb, String cfyy)
			throws Exception {
		dao = new WjcfShgcDAO();
		boolean flag = dao.wjcfrqwhsh(pkVal, cfsj, cfwh,cflb,cfyy);
		return flag;
	}

	/**
	 * 获取处分原因
	 * 
	 * @param cfyy
	 * @return
	 * @throws Exception
	 */
	public String getCfyy(String cfyy) throws Exception {
		dao = new WjcfShgcDAO();
		String cfyydm = dao.getCfYy(cfyy);
		return cfyydm;
	}

	/**
	 * 考试作弊数据维护查询表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSearchTitle2() throws Exception {
		dao = new WjcfShgcDAO();
		return dao.getSearchTitle2();
	}

	/**
	 * 考试作弊数据维护查询结果
	 * 
	 * @param shgcxxshqryModel
	 * @param cfyydm
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getSearchResult2(
			WjcfShgcXxshQryModel shgcxxshqryModel, String cfyydm)
			throws Exception {
		dao = new WjcfShgcDAO();
		return dao.getSearchResult2(shgcxxshqryModel, cfyydm);
	}

	/**
	 * 通过PK，PKVAL，TABLENAME获取学生信息公用方法
	 * 
	 * @param pk
	 * @param pkVal
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getxxInfo(String pk, String pkVal,
			String tableName) throws Exception {
		dao = new WjcfShgcDAO();
		return dao.getxxinfo(pk, pkVal, tableName);
	}

	/**
	 * 获取学生年龄
	 * 
	 * @param rs
	 * @return
	 * @throws Exception
	 */
	public String getxxNn(HashMap<String, String> rs) throws Exception {
		dao = new WjcfShgcDAO();
		return dao.getxxNn(rs);
	}

	/**
	 * 未通过审核信息表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSearchTitle3() throws Exception {
		dao = new WjcfShgcDAO();
		return dao.getSearchTitle3();
	}

	/**
	 * 未通过审核信息查询结果
	 * 
	 * @param shgcxxshqryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getSearchResult3(WjcfShgcXxshQryModel shgcxxshqryModel)
			throws Exception {
		dao = new WjcfShgcDAO();
		return dao.getSearchResult3(shgcxxshqryModel);
	}

	/**
	 * 未通过审核信息批量删除
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String deleteWtgWjxx(String[] keys) throws Exception {
		dao = new WjcfShgcDAO();
		return dao.deleteWtgWjxx(keys);
	}

	/**
	 * 公用方法通过PK，PKVAL，tableName，COLLIST,TYPE获取查询出来的数据
	 * 
	 * @param pk
	 * @param pkVal
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getFindResult(String pk, String pkVal,
			String tableName, int type) throws Exception {
		dao = new WjcfShgcDAO();
		HashMap<String, String> result = new HashMap<String, String>();
		String[] colList = null;
		if (tableName.equalsIgnoreCase("view_wjcf_xsssxx") && (type == 1)) {// 通过表名及类型来判断输出列名
			colList = new String[] { "XH", "XM", "XB", "NJ", "XN", "XYMC",
					"ZYMC", "BJMC", "SSSJ", "SH", "JD", "CFLBMC", "CFYYMC",
					"CFSJ", "CFWH", "JDWH", "JDSJ", "TLLY", "JDLY", "SLQK",
					"SLRQ", "SLTZS", "YQ" };
		} else if(tableName.equalsIgnoreCase("view_wjcf_xsssxx") && (type == 2)) {
			colList = new String[] { "XH", "XM", "XB", "NJ", "XN", "XYMC",
					"ZYMC", "BJMC", "SSSJ", "SH", "JD", "CFLBMC", "CFYYMC",
					"CFSJ", "CFWH", "JDWH", "JDSJ", "TLLY", "JDLY", "SLQK",
					"SLRQ", "SLTZS", "YQ" ,"FCRQ","MQZT","CSQK","FCQK","FCTZS"};
		}//end if
		result = dao.getFindResult(pk, pkVal, tableName, colList);
		return result;
	}

	// 获取文件上传列表
	public List<HashMap<String, String>> GetFileList(String pkValue) {
		dao = new WjcfShgcDAO();
		return dao.getFileList(pkValue);
	}

	/**
	 * 通过INT PARAM 来输出不同的列表
	 */
	public List<HashMap<String, String>> getChkList1(int type) {
		dao = new WjcfShgcDAO();
		return dao.getChkList1(type);
	}
	
	/**
	 * 保存申诉受理信息
	 * @param shgcssslModel
	 * @param pkVal
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveSsSlXx(WjcfShgcSsSlModel shgcssslModel, String pkVal, HttpServletRequest request) throws Exception {
		dao = new WjcfShgcDAO();
		return dao.saveSsSlXx(shgcssslModel, pkVal, request);
	}
	
	/*public String getSlqk(String slqk) throws Exception {
		//如果为空就RETURN 等待受理
		if (StringUtils.isNull(slqk)) {
			return "0";
		}
		if (StringUtils.isEqual(slqk, "等待受理")) {
			return "0";
		}else if (StringUtils.isEqual(slqk, "已受理")) {
			return "1";
		}else {
			return "2";
		}//end if
	}*/
	
	/**
	 * 保存申诉决定信息
	 * 
	 * @param shgcssjdModel
	 * @param pkVal
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveSsJdXx(WjcfShgcSsJdModel shgcssjdModel, String pkVal,
			HttpServletRequest request) throws Exception {
		dao = new WjcfShgcDAO();
		return dao.saveSsJdXx(shgcssjdModel, pkVal, request);
	}
	
	public List<String[]> kswjQryRes(KswjModel model) throws Exception {
		dao = new WjcfShgcDAO();
		return dao.kswjQryRes(model);
	}
	
	/**
	 * 考试违纪查询表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> kswjTitle() throws Exception {
		dao = new WjcfShgcDAO();
		return dao.kswjTitle();
	}
	
	/**
	 * 考试违纪处分原因列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKscfyyList() throws Exception {
		dao = new WjcfShgcDAO();
		return dao.getKscfyyList();
	}
	
	/**
	 * 考试违纪处分类别列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKscflbList() throws Exception {
		dao = new WjcfShgcDAO();
		return dao.getKscflbList();
	}
	
	public HashMap<String, String> getStuDetails(String xh) throws Exception {
		dao = new WjcfShgcDAO();
		return dao.getStuDetails(xh);
	}
	
	public boolean savekswjInfo(KswjModel model, HttpServletRequest request) throws Exception {
		dao = new WjcfShgcDAO();
		return dao.savekswjInfo(model, request);
	}
	
	public String kswjDel(String[] keys, HttpServletRequest request) throws Exception {
		dao = new WjcfShgcDAO();
		return dao.kswjDel(keys, request);
	}
	
	public HashMap<String, String> kswjModi(String pkValue) throws Exception {
		dao = new WjcfShgcDAO();
		return dao.kswjModi(pkValue);
	}
	
	public boolean kswjModisave(String pkValue, KswjModel model, HttpServletRequest request) throws Exception {
		dao = new WjcfShgcDAO();
		return dao.kswjModisave(pkValue, model, request);
	}
	
	public List<HashMap<String, String>> kswjgzjyTitle() throws Exception {
		dao = new WjcfShgcDAO();
		return dao.kswjgzjyTitle();
	}
	
	public List<String[]> kswjgzjyResult(KswjModel model) throws Exception {
		dao = new WjcfShgcDAO();
		return dao.kswjgzjyResult(model);
	}
	
	public HashMap<String, String> viewKswjgzjyxx(String pkValue) throws Exception {
		dao = new WjcfShgcDAO();
		return dao.viewKswjgzjyxx(pkValue);
	}
	
	public boolean kswjGzjysaveres(String pkValue, KswjModel model, HttpServletRequest request) throws Exception {
		dao = new WjcfShgcDAO();
		return dao.kswjGzjysaveres(pkValue, model, request);
	}
	
	public String wjshres(String[] keys, HttpServletRequest request, String ress) throws Exception {
		dao = new WjcfShgcDAO();
		return dao.wjshres(keys, request, ress);
	}
	
	public String sjzy(String[] keys) throws Exception {
		dao = new WjcfShgcDAO();
		return dao.sjzy(keys);
	}
	
	public HashMap<String, String> wtgview(String pkValue) throws Exception {
		dao = new WjcfShgcDAO();
		return dao.wtgview(pkValue);
	}
}
