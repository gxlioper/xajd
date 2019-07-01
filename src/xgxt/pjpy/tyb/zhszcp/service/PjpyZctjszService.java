package xgxt.pjpy.tyb.zhszcp.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.pjpy.tyb.zhszcp.dao.PjpyZctjszDAO;
import xgxt.pjpy.tyb.zhszcp.model.PjpyZctjszModel;
import xgxt.pjpy.tyb.zhszcp.model.PjpyZhszcpModel;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

public class PjpyZctjszService {

	PjpyZctjszDAO dao = new PjpyZctjszDAO();
	
	/**
	 * 查询综测二级代码表头
	 * @return
	 */
	public List<HashMap<String, String>> queryEjTitle() {
		return dao.queryEjTitle();
	}
	
	/**
	 * 通过综测二级代码查询综测信息
	 * @param dm
	 * @return
	 */
	public HashMap<String, String> queryZcejdmxx(String dm) {
		return dao.queryZcejdmxx(dm);
	}

	/**
	 * 保存2级综测代码设置条件信息 先删除后增加
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean save2jZhszcpdmxx(PjpyZctjszModel model) throws Exception{
		return dao.save2jZhszcpdmxx(model);
	}
	
	/**
	 * 通过二级代码查询三级子代码信息 并拼接成字符串
	 * @param dm
	 * @return
	 */
	public String query3jZcdmStr(String dm) {
		String str = "";
		List<HashMap<String, String>> rs = dao.query3jZcdm(dm);
		if (!rs.isEmpty()) {
			for (HashMap<String, String> map : rs) {
				if (!map.isEmpty()) {
					str += map.get("dm") + "!@";
				}
			}
		}
		
		return StringUtils.isNotNull(str) ? str.substring(0, str.length() - 2) : "";
	}
	
	/**
	 * 通过代码级别查询代码名称信息
	 * @param dmjb 分为1，2，3，4级
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> queryZcsjdmxx(String dmjb, boolean isSfwh) throws Exception{
		return dao.queryZcsjdmxx(dmjb, isSfwh);
	}
	
	/**
	 * 通过代码类别和代码查询综测代码信息
	 * @param dmlb
	 * @param dm
	 * @return
	 */
	public List<HashMap<String, String>> queryZcDmxxList(String dm) {
		return dao.queryZcdmxxList(dm);
	}
	
	/**
	 * 通过父代码和表名查询审核级别
	 * 
	 * @param dm
	 * @param tableName
	 * @returnString
	 */
	public String queryZhszcpxmShjb(String fdm, String tableName){
		return dao.queryZhszcpxmShjb(fdm, tableName);
	}
	
	/**
	 * 通过表名查询代码详细信息
	 * @param tableName
	 * @return
	 */
	public List<HashMap<String, String>> queryZhszcpxm(String tableName) {
		return dao.queryZhszcpxm(tableName);
	}	
	
	/**
	 * 修改父代码表名和审核级别信息
	 * @param dmArray
	 * @param bmArray
	 * @param fdmArray
	 * @return
	 */
	public boolean updateFdmbmAndShjb(PjpyZhszcpModel model) {
		return dao.updateFdmbmAndShjb(model);
	}
	
	/**
	 * 导入综测条件检测结果
	 * @param wwb
	 * @throws Exception 
	 */
	public void expTjjcResult(WritableWorkbook wwb) throws Exception {
		//获取第一个要导出的单元格
		WritableSheet ws = wwb.createSheet("综测条件配置检测结果", 0);
		//查询检测数据
		List<String[]> rs = dao.queryZctjjcResult();
		
		// 获取要导出数据的样式
		WritableCellFormat format = ExcelMB.getWritableCellFormat(12, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, Border.ALL);
		
		//写入表头到EXCEL中
		String[] titleArray = {"检测问题描述", "综测项目代码", "综测项目名称", "配置表名", "配置字段", "上级项目代码", "是否数据维护", "审核级别", "条件"};
		List<String[]> titleList = new ArrayList<String[]>();
		titleList.add(titleArray);
		ExcelMB.writeDataToCellByIterator(ws, titleList, 0, format);
		
		// 将导出数据写入EXCEL中
		ExcelMB.writeDataToCellByIterator(ws, rs, 1, format);
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 执行综测配置条件检测
	 * @return
	 * @throws Exception
	 */
	public void zctjSjCheckResult(WritableWorkbook wwb) throws Exception{
		
		//执行检测操作
		boolean result = dao.zctjSjCheckResult();
		
		if (result) {//检测成功,输出检测数据
			expTjjcResult(wwb);
		} else {//检测失败,插入失败信息并输出
			Map<String, String> map = new HashMap<String, String>(){};
			map.put("msg",
					"综测条件配置检测过程中出现问题,可能是存储过程(PROC_PJPY_ZCTJPZJC)运行时出错了.....");
			result = dao.insertSjjcErrorMsg(map);
			if (result) {
				expTjjcResult(wwb);
			}
		}
		
		//最后删除所有的检测结果数据
		dao.deleteAllZcsjjcResult();
	}
	
	/**
	 * 查询三级要进行数据维护的代码信息
	 * @return
	 */
	public List<HashMap<String, String>> query3jwhdmxx() {
		return dao.query3jwhdmxx();
	}
	
	public boolean update2jDmwhxx() throws Exception{
		
		List<HashMap<String, String>> rs = query3jwhdmxx(); 
		if (rs != null && !rs.isEmpty()) {
			String[] sqlArr = new String[rs.size()];
			for (int i=0;i<rs.size();i++) {
				HashMap<String, String> map = rs.get(i);
				if (map != null) {
					StringBuilder sql = new StringBuilder(" update pjpy_zctjszb set bm='");
					sql.append(map.get("bm"));
					sql.append("',shjb='");
					sql.append(map.get("shjb"));
					sql.append("',sfwh='");
					sql.append(map.get("sfwh"));
					sql.append("' where dm='");
					sql.append(map.get("fdm"));
					sql.append("'");
					sqlArr[i] = sql.toString();
				}
			}
			DAO dao = DAO.getInstance();
			return dao.checkBatch(dao.runBatch(sqlArr));
		}
		return false;
	}
	
	/**
	 * 修改二级综测代码信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean update2jZcdmxx(PjpyZctjszModel model) throws Exception{
		return dao.update2jZcdmxx(model);
	}
	
	public boolean update3jZcdmxx(PjpyZctjszModel model) throws Exception{
		return dao.update3jZcdmxx(model);
	}
	
	/**
	 * 修改四级综测代码信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean update4jZcdmxx(PjpyZctjszModel model) throws Exception{
		return dao.update4jZcdmxx(model);
	}
	
	/**
	 * 修改综测代码信息(2,3,4级)
	 * @param type
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean updateZcdmxx(String type, PjpyZctjszModel model) throws Exception{
		boolean result = false;
		if ("2".equalsIgnoreCase(type)) {//保存二级
			result = update2jZcdmxx(model);
		} else if ("3".equalsIgnoreCase(type)) {//保存三级
			result = update3jZcdmxx(model);
		} else if ("4".equalsIgnoreCase(type)) {//保存四级
			result = update4jZcdmxx(model);
		}
		return result;
	}
	
}
