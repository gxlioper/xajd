
package xgxt.wjcf.jgsdx;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 井冈山大学违纪处分Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-24</p>
 */
public class WjcfJgsdxService {

	WjcfJgsdxDAO dao = null;//公用的数据操作DAO
	
	/**
	 * 通过学号获取学生相关信息
	 * getStuInfo ---- 获取学生信息
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuInfo(String xh) throws Exception {
		dao = new WjcfJgsdxDAO();
		return dao.getStuInfo(xh);
	}
	
	/**
	 * 保存前检查学生处分时间是否满一年
	 * @param cfsj
	 * @return
	 * @throws Exception
	 */
	public boolean chkStuTj(String cfsj) throws Exception {
		dao = new WjcfJgsdxDAO();
		return dao.chkStuTj(cfsj);
	}
	
	/**
	 * 保存撤消处分信息
	 * @param cxcfSaveModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveCxcfSqlInfo(CxcfSqSaveModel cxcfSaveModel, HttpServletRequest request) throws Exception {
		dao = new WjcfJgsdxDAO();
		return dao.saveCxcfSqlInfo(cxcfSaveModel, request);
	}
	
	/**
	 * 撤消处分查询表头
	 * getCxcfSearchTitle ---- 撤消处分查询表头 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCxcfSearchTitle() throws Exception {
		dao = new WjcfJgsdxDAO();
		return dao.getCxcfSearchTitle();
	}
	
	/**
	 * 撤消处分查询结果
	 * getCxcfSearchResult ---- 撤消处分查询结果
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCxcfSearchResult(CxcfQryModel cxcfModel) throws Exception {
		dao = new WjcfJgsdxDAO();
		return dao.getCxcfSearchResult(cxcfModel);
	}
	
	/**
	 * 通过主键获取学生相关信息
	 * getStuInfo1 ---- 通过主键获取学生相关信息 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuInfo1(String pkValue) throws Exception {
		dao = new WjcfJgsdxDAO();
		return dao.getStuInfo1(pkValue);
	}
	
	/**
	 * 撤消处分批量删除
	 * wjcfCxcfPlDel ---- 违纪处分撤消处分批量删除 
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String wjcfCxcfPlDel(String[] keys, HttpServletRequest request) throws Exception {
		dao = new WjcfJgsdxDAO();
		return dao.wjcfCxcfPlDel(keys, request);
	}
	
	/**
	 * 撤消处分审批查询表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCxcfSpTitle() throws Exception {
		dao = new WjcfJgsdxDAO();
		return dao.getCxcfSpTitle();
	}
	
	/**
	 * 撤消处分审批查询结果
	 * @param cxcfModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCxcfSpResult(CxcfQryModel cxcfModel) throws Exception {
		dao = new WjcfJgsdxDAO();
		return dao.getCxcfSpResult(cxcfModel);
	}
	
	/**
	 * 通过主键获取撤消处分相关信息
	 * getCxcfInfoByPk ---- 通过主键获取撤消处分相关信息 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getCxcfInfoByPk(String pkValue) throws Exception {
		dao = new WjcfJgsdxDAO();
		return dao.getCxcfInfoByPk(pkValue);
	}
	
	/**
	 * 返回审核列表
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getChList(int type) throws Exception {
		dao = new WjcfJgsdxDAO();
		return dao.getChList(type);
	}
	
	/**
	 * 撤消处分审批
	 * cxcfSp ---- 撤消处分审批 
	 * @param cxcfsqModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean cxcfSp(CxcfSpSaveModel cxcfsqModel, HttpServletRequest request) throws Exception {
		dao = new WjcfJgsdxDAO();
		return dao.cxcfSp(cxcfsqModel, request);
	}
	
	/**
	 * 学生违纪处分相关信息
	 * getStuWjcfinfo ---- 学生违纪处分相关信息 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getStuWjcfinfo(String xh) throws Exception {
		dao = new WjcfJgsdxDAO();
		return dao.getStuWjcfinfo(xh);
	}
	
	/**
	 * 学生违纪处分相关信息表头
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getStuWjcfTit(String xh) throws Exception {
		dao = new WjcfJgsdxDAO();
		return dao.getStuWjcfTit(xh);
	}
}
