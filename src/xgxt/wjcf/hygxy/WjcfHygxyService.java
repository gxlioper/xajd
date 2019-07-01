
package xgxt.wjcf.hygxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 淮阴工学院违纪处分Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-09-23</p>
 */
public class WjcfHygxyService {

	WjcfHygxyDAO dao = null;//数据操作通用DAO
	
	/**
	 * 通过表名得到其查询表头
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getQueryTitle(String tableName) throws Exception {
		dao = new WjcfHygxyDAO();
		List<HashMap<String, String>> titleList = null;
		String[] enList = null;//英文列表
		String[] cnList = null;//中文列表
		if (!StringUtils.isNull(tableName)) {
			if (StringUtils.isEqual(tableName, "wjcf_gzjyb")) {
				enList = new String[] { "pk", "rownum", "xh", "xm", "nj", "xn",
						"xq", "bjmc", "jyzt" };
				cnList = new String[] { "pk", "行号", "学号", "姓名", "年级", "学年",
						"学期", "班级名称", "教育主题" };
			}
		}
		titleList = dao.getQueryTitle(enList, cnList);
		return titleList;
	}
	
	/**
	 * 跟踪教育查询结果
	 * @param gzjyModel
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getGzjyQueryResult(WjcfGzjyModel gzjyModel) throws Exception{
		dao = new WjcfHygxyDAO();
		return dao.getGzjyQueryResult(gzjyModel);
	}
	
	/**
	 * 获取学生违纪信息
	 * @param xh
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuWjxx(String xh, String pkValue) throws Exception {
		dao = new WjcfHygxyDAO();
		return dao.getStuWjxx(xh, pkValue);
	}

	/**
	 * 保存跟踪教育信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveGzjy(WjcfGzjyModel model, HttpServletRequest request) throws Exception {
		dao = new WjcfHygxyDAO();
		return dao.saveGzjy(model, request);
	}
	
	/**
	 * 显示单个跟踪教育信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> viewGzjy(String pkValue) throws Exception {
		dao = new WjcfHygxyDAO();
		return dao.viewGzjy(pkValue);
	}
	
	/**
	 * 批量删除跟踪教育信息
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String deleteGzjy(String[] keys, HttpServletRequest request) throws Exception {
		dao = new WjcfHygxyDAO();
		return dao.deleteGzjy(keys, request);
	}
	
	/**
	 * 修改保存跟踪教育信息
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean updateGzjy(WjcfGzjyModel model, String pkValue, HttpServletRequest request) throws Exception {
		dao = new WjcfHygxyDAO();
		return dao.updateGzjy(model, pkValue, request);
	}
	
	/**
	 * 数组转字符串
	 * @param list
	 * @return
	 */
	public String listToString(String[] list) {
		dao = new WjcfHygxyDAO();
		if (list != null && list.length > 0) {
			String str= "";
			for (String s : list) {
				str = str + s + "!"; 
			}
			return StringUtils.isNull(str) ? "" : str.substring(0, str.length() - 1);
		} 
		return "";
	}
	/**
	 * 批量发文
	 * @param pkVal
	 * @param cfwh
	 * @param cfsj
	 * @return
	 * @throws Exception
	 */
	public boolean plfw(String pkVal, String cfwh, String cfsj) throws Exception{
		dao = new WjcfHygxyDAO();
		return dao.plfw(pkVal, cfwh, cfsj);
	}
	
	public List<String[]> wyhslQuery(WjcfGzjyModel model) throws Exception {
		dao = new WjcfHygxyDAO();
		return dao.wyhslQuery(model);
	}
	
	public List<HashMap<String, String>> wyhslTitle() {
		dao = new WjcfHygxyDAO();
		return dao.wyhslTitle();
	}
	
	public HashMap<String, String> wyhslone(String pkValue) {
		dao = new WjcfHygxyDAO();
		return dao.wyhslone(pkValue);
	}
	
	public boolean updateWhysl(WjcfGzjyModel model, String pkValue, HttpServletRequest request) throws Exception{
		dao = new WjcfHygxyDAO();
		return dao.updateWhysl(model, pkValue, request);
	}
	
	/**
	 * 批量受理
	 * @param pkVal
	 * @param cfwh
	 * @param cfsj
	 * @return
	 * @throws Exception
	 */
	public boolean plsl(String pkVal, String wyhsl, String wyhsllr) throws Exception{
		dao = new WjcfHygxyDAO();
		return dao.plsl(pkVal, wyhsl, wyhsllr);
	}
}
