package xgxt.qgzx.comm.gwsqwh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.base.BaseDAO;
import xgxt.utils.String.StringUtils;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 勤工助学_学生岗位申请-service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author lt
 * @version 1.0
 */
public class QgzxXsgwsqService {

	QgzxXsgwsqDao qgzxDao = new QgzxXsgwsqDao();
	
	/**
	 * 通过表名查询列表数据，适用于下拉列表数据
	 * @param tableName
	 * @param outZdArray
	 * @param wheresql
	 * @return
	 */
	public List<HashMap<String, String>> getLbList(String tableName, String[] outZdArray, String wheresql) {
		return qgzxDao.getLbList(tableName, outZdArray, wheresql);
	}
	
	/**
	 * 岗位性质列表下拉数据
	 * @return
	 */
	public List<HashMap<String, String>> getGwxzList() {
		return getLbList("gwxzdmb", new String[]{"gwxzdm", "gwxzmc"}, "");
	}
	
	/**
	 * 学生用户的岗位申请信息表头
	 * @return
	 */
	public List<HashMap<String, String>> queryXsgwsqxxByStuTitle() {
		DAO dao = DAO.getInstance();
		return dao.arrayToList(new String[]{ "pk",  "gwxzmc", "yrdwmc", "gwdm",
				"sqsyrs", "sqsyknss", "gwsqjssj", "shzt", "cz" }, new String[]{"pk", "岗位性质", "用人单位", "岗位名称",
				"需要人数", "需要困难生人数", "申请结束时间", "目前审核状态", "操作" });
	}
	
	/**
	 * 老师用户的岗位申请信息表头
	 * @return
	 */
	public List<HashMap<String, String>> queryXsgwsqxxByTeaTitle() {
		DAO dao = DAO.getInstance();
		return dao.arrayToList(new String[]{ "pk",  "gwxzmc", "yrdwmc", "gwdm",
				"sqsyrs", "sqsyknss", "gwsqjssj", "cz" }, new String[]{"pk", "岗位性质", "用人单位", "岗位名称",
				"需要人数", "需要困难生人数", "申请结束时间", "操作" });
	}
	
	/**
	 *查询岗位待申请信息
	 * 
	 * @param qgzxForm
	 * @return
	 */
	public List<HashMap<String, String>> queryXsgwxxByResult(
			QgzxXsgwsqForm qgzxForm) {
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String,String>>();
		if (StringUtils.isNotNull(qgzxForm.getXh())) {//学生申请结果
			//岗位信息列表
			rs = qgzxDao.queryXsgwxxByStu(qgzxForm);
			
			//黑名单标记
			boolean ishmd = queryStuIshmd(qgzxForm);
			
			DAO dao = DAO.getInstance();
			boolean iskns = dao.isKns(qgzxForm.getXh());
			if (rs != null && rs.size() > 0) {
				for (HashMap<String, String> map : rs) {
					if (ishmd) {//黑名单标记
						map.put("cz", "hmd");
					} else {
						if (map.get("sqsyrs").equalsIgnoreCase(map.get("sqsyknss")) && !iskns) {//当使用人数与困难生相等时只有困难才能申请
							map.put("cz", "bksq");
						} else if (!StringUtils.isNull(map.get("xyyj"))
								&& !StringUtils.isNull(map.get("xxyj"))
								&& (!"未审核".equalsIgnoreCase(map.get("xyyj"))
								|| !"未审核".equalsIgnoreCase(map.get("xxyj")))) {//不可修改
							map.put("cz", "ck");
						} else if ("未审核".equalsIgnoreCase(map.get("xyyj")) && "未审核".equalsIgnoreCase(map.get("xxyj"))) {//可修改
							map.put("cz", "xg");
						}
					}
				}
			}
		} else {//老师申请
			rs = qgzxDao.queryXsgwsqxx(qgzxForm);
		}
		if (rs != null && rs.size() > 0) {
			for (HashMap<String, String> map : rs) {
				if (!StringUtils.isNull(map.get("sqjssj")) && map.get("sqjssj").length() == 14) {
					String time = map.get("sqjssj");
					map.put("sqjssj", time.substring(0, 4) + "年"
							+ time.substring(4, 6) + "月"
							+ time.substring(6, 8) + "日 "
							+ time.substring(8, 10) + ":"
							+ time.substring(10, 12) + ":"
							+ time.substring(12, 14));
				}
			}
		}
		return rs;
	}
	
	/**
	 * 获取岗位详细信息
	 * @param form
	 * @return
	 */
	public Map<String, String> getGwxx(QgzxXsgwsqForm form){
		return qgzxDao.viewGwxx(form.getGwdmsbsj());
	}
	
	/**
	 * 获取学生信息
	 * @param xh 学号
	 * @return
	 */
	public Map<String, String> getXsxx(String xh){
		return qgzxDao.viewXsxx(xh);
	}
	
	/**
	 * 获取学生岗位申请信息
	 * @param xh 学号
	 * @param pkValue 岗位代码+岗位上报时间
	 * @return
	 */
	public Map<String, String> getSqxx(String xh,String pkValue){
		return qgzxDao.viewSqxx(xh, pkValue);
	}
	
	/**
	 * 根据学号获取该生是否已经开始审核，若为0则未开始审核，可以修改申请信息；否则不能修改申请信息
	 * @param xh 学号
	 * @return
	 */
	public String getShCount(String xh,String pkValue){
		return qgzxDao.getShCount(xh,pkValue);
	}
	
	/**
	 * 申请结果查询列表
	 * @param form
	 * @param userType
	 * @return
	 */
	public List<String[]> queryXsgwsqxx(QgzxXsgwsqForm form, String userType) throws Exception {
		if ("stu".equalsIgnoreCase(userType)) {
			return qgzxDao.queryXsgwsqxxByStu(form);
		} else if ("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)) {
			return qgzxDao.queryXsgwsqxxByYrdw(form);
		} else {
			return qgzxDao.queryXsgwsqxxByYrdw(form);
		}
	}
	
	/**
	 * 申请结果查询表头
	 * @return
	 */
	public List<HashMap<String, String>> queryXsgwsqTitle() {
		DAO dao = DAO.getInstance();
		return dao.arrayToList(new String[] { "pk", "dis", "r", "xn", "nd",
				"xqmc", "xh", "xm", "bjmc", "gwdm", "sfpks", "xyyj", "xxyj" },
				new String[] { "pk", "dis", "行号", "学年", "年度", "学期", "学号", "姓名",
						"班级", "岗位名称", "是否贫困生", "用人单位审核", "学校审核" });

	}
	
	/**
	 * 删除学生岗位申请信息
	 * @param qgzxForm
	 * @return
	 * @throws SQLException
	 */
	public String deleteXsgwsqxx(QgzxXsgwsqForm qgzxForm) throws SQLException {
		if (qgzxForm.getPrimarykey_cbv() == null) {
			return "error";
		}
		int[] result = qgzxDao.deleteXsgwsqxx(qgzxForm);
		String flag = "";
		for (int j = 0; j < result.length; j++) {
			if (result[j] > -1) {
				
			} else {
				flag += (j+1) + ",";
			}
		}
		return flag != "" ? flag.substring(0,flag.length() - 1) : "succ";
	}
	
	/**
	 * 反馈操作提示信息
	 * @param msg
	 * @return
	 */
	public String promptMsg(String msg) {
		if ("succ".equalsIgnoreCase(msg)) {
			return "操作成功！";
		} else if ("error".equalsIgnoreCase(msg)) {
			return "操作失败！";
		} else {
			return "操作完成，其中第" + msg + "条数据删除失败！";
		}
	}
	
	/**
	 * 验证学生是否黑名单
	 * @param form
	 * @return
	 */
	public boolean queryStuIshmd(QgzxXsgwsqForm form) {
		BaseDAO dao = new BaseDAO();
		return dao.checkExists("qgzx_zgdzdx_hmdb", "xh", form.getXh());
	}
	
	public HashMap<String, String> queryGwsqsjb() {
		return qgzxDao.queryGwsqsjb();
	}
}
