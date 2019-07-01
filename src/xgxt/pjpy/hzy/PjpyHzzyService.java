
package xgxt.pjpy.hzy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 杭州职院评奖评优Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-08-19</p>
 */
public class PjpyHzzyService {

	PjpyHzzyDAO dao = null;//数据操作DAO
	
	/**
	 * 
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTableQryTitle(String tableName) throws Exception {
		dao = new PjpyHzzyDAO();
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = null;
		String[] cnList = null;
		if (StringUtils.isEqual(tableName, "cjb")) {
			enList = new String[]{"rownum", "xh", "xm", "xn", "xq", "bjmc", "kcmc", "cj"};
			cnList = new String[]{"行号", "学号", "姓名", "学年", "学期", "班级名称", "课程名称", "成绩"};
		}
		if (StringUtils.isEqual(tableName, "zhszcp")) {
			enList = new String[]{"xh||xn||xq||nd", "rownum", "xh", "xm", "xn", "nd","xq", "bjmc", "xydykpf","zcj","tcj" ,"gzxxcx","zhszcpzf"};
			cnList = new String[]{"pk", "行号", "学号", "姓名", "学年","年度", "学期", "班级名称", "学院德育分", "智成绩", "体成绩", "学习创新分", "测评总分"};
		}
		topList = dao.getTableQryTitle(enList, cnList);
		return topList;
	}
	
	/**
	 * 获取成绩表查询结果
	 * @param cjbModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCjbQryResult(CjbModel cjbModel) throws Exception {
		dao = new PjpyHzzyDAO();
		return dao.getCjbQryResult(cjbModel);
	}
	
	/**
	 * 综合素质测评查询结果
	 * @param zhszcpModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZhszcpQryResult(ZhszcpModel zhszcpModel) throws Exception {
		dao = new PjpyHzzyDAO();
		return dao.getZhszcpQryResult(zhszcpModel);
	}
	
	/**
	 * 该生在奖学金申请学年度的综合素质分
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuXx(String xh) throws Exception {
		dao = new PjpyHzzyDAO();
		return dao.getStuXx(xh);
	}
	
	/**
	 * 综合素质测评保存
	 * @param zhszcpModel
	 * @return
	 * @throws Exception
	 */
	public boolean zhszcpSave(ZhszcpModel zhszcpModel, HttpServletRequest request) throws Exception {
		dao = new PjpyHzzyDAO();
		return dao.zhszcpSave(zhszcpModel, request);
	}
	
	/**
	 * 综合素质测评批量删除
	 * @param keys
	 * @param request
	 * @return NULL删除成功，NOT NULL 删除失败
	 * @throws Exception
	 */
	public String zhszcpDel(String[] keys, HttpServletRequest request) throws Exception {
		dao = new PjpyHzzyDAO();
		return dao.zhszcpDel(keys, request);
	}
	
	/**
	 * 通过主键获取学生综合素质测评成绩
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZhszcpXx(String pkValue) throws Exception {
		dao = new PjpyHzzyDAO();
		return dao.getZhszcpXx(pkValue);
	}
	
	/**
	 * 综合素质测评成绩修改保存
	 * @param zhszcpModel
	 * @param pkValue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean zhszcpModi(ZhszcpModel zhszcpModel, String pkValue, HttpServletRequest request) throws Exception {
		dao = new PjpyHzzyDAO();
		return dao.zhszcpModi(zhszcpModel, pkValue, request);
	}
	
	/**
	 * 荣誉称号打印
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> rychPrint(String pkValue) throws Exception {
		dao = new PjpyHzzyDAO();
		return dao.rychPrint(pkValue);
	}
	
	public String getPks(String[] keys) throws Exception {
		String pks = "";
		if (keys != null && keys.length > 0) {
			for (int i=0;i<keys.length;i++) {
				if (!StringUtils.isNull(keys[i])) {
					pks += (keys[i]+"-");
				}
			}
			return pks;
		} else {
			return "";
		}
	}
	
	public boolean updateQm(String qm, String keys, String userType, String isFdy, String tableName) throws Exception {
		dao = new PjpyHzzyDAO();
		return dao.updateQm(qm, keys, userType, isFdy, tableName);
	}
}
