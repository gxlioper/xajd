package xgxt.pjpy.zjcm;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.String.StringUtils;

public class ZhszcpService {

	ZhszcpDAO dao = ZhszcpDAO.getInstance();
	
	public static ZhszcpService service = new ZhszcpService();
	
	public static ZhszcpService getInstance() {
		return service;
	}
	
	/**
	 * 通过数据类型返回表名
	 * @param szlx
	 * @return
	 */
	public String getTableName(String szlx) {
		if ("0".equalsIgnoreCase(szlx)) {
			return "zjcm_dycjb";
		} else if ("1".equalsIgnoreCase(szlx)) {
			return "zjcm_zycjb";
		} else if ("2".equalsIgnoreCase(szlx)) {
			return "zjcm_tycjb";
		} else if ("3".equalsIgnoreCase(szlx)) {
			return "zjcm_sjcxcjb";
		} else if ("4".equalsIgnoreCase(szlx)) {
			return "zjcm_zhszcpb";
		} else {
			return "zjcm_dycjb";
		}
	}
	
	/**
	 * 通过数据类型返回表名
	 * @param szlx
	 * @return
	 */
	public String getTitName(String szlx) {
		if ("0".equalsIgnoreCase(szlx)) {
			return "德育成绩";
		} else if ("1".equalsIgnoreCase(szlx)) {
			return "智育成绩";
		} else if ("2".equalsIgnoreCase(szlx)) {
			return "体育成绩";
		} else if ("3".equalsIgnoreCase(szlx)) {
			return "实践创新成绩";
		} else if ("4".equalsIgnoreCase(szlx)) {
			return "综合测评成绩";
		} else {
			return "德育成绩";
		}
	}
	
	/**
	 * 通过数据类型返回表名
	 * @param szlx
	 * @return
	 */
	public String getTitNameBytable(String tableName) {
		if ("zjcm_dycjb".equalsIgnoreCase(tableName)) {
			return "德育成绩";
		} else if ("zjcm_zycjb".equalsIgnoreCase(tableName)) {
			return "智育成绩";
		} else if ("zjcm_tycjb".equalsIgnoreCase(tableName)) {
			return "体育成绩";
		} else if ("zjcm_sjcxcjb".equalsIgnoreCase(tableName)) {
			return "实践创新成绩";
		} else if ("zjcm_zhszcpb".equalsIgnoreCase(tableName)) {
			return "综合测评成绩";
		} else {
			return "德育成绩";
		}
	}
	
	/**
	 * 查询表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTitleName(String szlx)
			throws Exception {
		return dao.getTitleName(szlx);
	}
	
	/**
	 * 查询结果
	 * 
	 * @param model
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getResult(ZhszcpModel model, String tableName,
			PjpyZjcmActionForm dataSearchForm, String isFdy, String userName) throws Exception {
		return dao.getResult(model, tableName, dataSearchForm, isFdy, userName);
	}

	/**
	 * 查询结果记录数
	 * 
	 * @param model
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public int getResultNum(ZhszcpModel model, String tableName, String isFdy, String userName)
			throws Exception {
		return dao.getResultNum(model, tableName, isFdy, userName);
	}
	
	/**
	 * 素质分保存
	 * @param model
	 * @param tableName
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean save(ZhszcpModel model, String tableName,
			HttpServletRequest request) throws Exception {
		return dao.save(model, tableName, request);
	}
	
	/**
	 * 素质分修改
	 * @param model
	 * @param tableName
	 * @param request
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean update(ZhszcpModel model, String tableName,
			HttpServletRequest request, String pkValue) throws Exception {
		return dao.update(model, tableName, request, pkValue);
	}
	
	/**
	 * 删除
	 * @param keys
	 * @param tableName
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String delete(String[] keys, String tableName,
			HttpServletRequest request) throws Exception {
		return dao.delete(keys, tableName, request);
	}
	
	/**
	 * 修改显示
	 * 
	 * @param pkValue
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> view(String pkValue, String tableName)
			throws Exception {
		return dao.view(pkValue, tableName);
	}
	
	/**
	 * 综合素质分的自动计算
	 * @param xn
	 * @param xq
	 * @param xydm
	 * @return
	 * @throws Exception
	 */
	public boolean zhszAutoCount(String xn , String xq, String xydm) throws Exception {
		return dao.zhszAutoCount(xn, xq, xydm);
	}
	
	/**
	 * 综合素质测评比例
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZhszcpBlxx() throws Exception {
		return dao.getZhszcpBlxx();
	}
	
	/**
	 * 保存综合测评比例
	 * 
	 * @param pkValue
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveZhcpbl(String pkValue, ZhszcpModel model,
			HttpServletRequest request) throws Exception {
		return dao.saveZhcpbl(pkValue, model, request);
	}
	
	/**
	 * 检查比例数据是否有设置
	 * @return
	 * @throws Exception
	 */
	public boolean chgZhszcpBlExists() throws Exception {
		return dao.chgZhszcpBlExists();
	}
	
	/**
	 * 获取班主任所带班级列表
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getFdybjList(String userName)
			throws Exception {
		return dao.getFdybjList(userName);
	}
	
	public HashMap<String, String> getJxjsqxnxq() throws Exception {
		return dao.getJxjsqxnxq();
	}
	
	public HashMap<String, String> getXy(String xh, String pkValue) throws Exception {
		return dao.getXy(xh, pkValue);
	}
	
	/**
	 * 格式化日期年月增加年,月
	 * 格式为2009-10-20 返回2009年10月
	 * 格式为20091020 返加2009年10月
	 * @param rxrq
	 * @return
	 * @throws Exception
	 */
	public String returnNy(String ny) throws Exception {
		String rxn = "";
		String rxy = "";
		if (!StringUtils.isNull(ny)) {
			if (ny.indexOf("-") != -1) {//格式如:2009-01-20
				if (ny.length()>=7) {
					rxn = ny.substring(0, 4);
					rxy = ny.substring(5, 7);
				}
			} else {//格式如:20091020
				if (ny.length()>=6) {
					rxn = ny.substring(0, 4);
					rxy = ny.substring(4, 6);
				}
			}
			return rxn + " 年 " + rxy + " 月";
		}
		return "";
	}
}
