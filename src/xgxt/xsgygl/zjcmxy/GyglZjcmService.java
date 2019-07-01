/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-11-17 下午02:42:33</p>
 */
package xgxt.xsgygl.zjcmxy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.action.Base;

//TODO: Auto-generated Javadoc
public class GyglZjcmService {


	/**
	 *住宿纪律处理查询表头
	 */
	public List<HashMap<String, String>> getZsjlTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"pk","xn","xqmc","wjsj","ldmc","qsh","wjlbmc","lrrxm","sfcf","xycljgmc","xxsh","xxcljg"};
		colListCN = new String[]{"pk","学年","学期","违纪时间","楼栋","寝室号","违纪类别","违纪录入人","是否处理","处理结果","学校审核","学校意见"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
	 *住宿纪律处理查询
	 */
	public  ArrayList<String[]> serv_getZsjlClQue(GyglZsjlClModel model){
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.dao_getZsjlClQue(model);
	}
	public  List<HashMap<String, String>> ser_getCljgList(){
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.getCljgList();
	}
	public  List<HashMap<String, String>> ser_getZsjlXsList(String pkValue,String cfyf) {
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.getZsjlXsList(pkValue,cfyf);
	}
	public  String getZsjlCfXsList(String pkValue) throws SQLException {
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.getZsjlCfXsList(pkValue);
	}
	public boolean ser_disposeSave(String sfcf,String xycljg,String dcqk,String[] pks,String pkValue) throws Exception{
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.disposeSave(sfcf, xycljg, dcqk, pks,pkValue);
	}
	/**
	 *住宿纪律处理审核查询表头 
	 */
	public List<HashMap<String, String>> getZsjlChkTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"pk","xn","xqmc","wjsj","ldmc","qsh","wjlbmc","lrrxm","sfcf","xycljgmc","xxsh"};
		colListCN = new String[]{"pk","学年","学期","违纪时间","楼栋","寝室号","违纪类别","违纪录入人","是否处理","处理结果","学校审核"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
	 *住宿纪律处理查询
	 */
	public  ArrayList<String[]> serv_getZsjlChkQue(GyglZsjlClModel model){
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.dao_getZsjlChkQue(model);
	}
	public boolean serv_doCheckSave(String xxcljg,String yesNo,String pkValue) throws Exception{		
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.doCheckSave(xxcljg, yesNo, pkValue);
	}
	public boolean serv_plCheckSave(String pkValue,String  check) throws SQLException{
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.plCheckSave(pkValue, check);
	}
	/**
	 * Serv_set ld wjlb.获取文件类别
	 * 
	 * @param request the request
	 */
	public List<HashMap<String, String>> serv_setLdWjlb(){
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.dao_setLdWjlb();
	}
	/**
	 * Serv_set ld wjlb.获取楼栋代码
	 * 
	 * @param request the request
	 */
	public List<HashMap<String, String>> ser_setLdlist(){
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.dao_setLdlist();
	}
	/**
	 * Serv_get xs info.根据学号获取学生信息
	 * 
	 * @param xh the xh
	 */
	public HashMap<String, String> serv_getXsInfo(String xh){
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.dao_getXsInfo(xh);
	}
	public boolean serv_saveXsInfo(zsjlModel model,HttpServletRequest request,String pk) throws Exception{
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.dao_saveXsInfo(model,request,pk);
	}
	/**
	 * .获取表头
	 */
	public List<HashMap<String,String>> getToptrTitle(String tableName,String[] colList) throws Exception {
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.getToptrTitle(tableName,colList);
	}
	/**
	 * .查询
	 */
	public ArrayList<String[]> ser_xlzxsQuery(zsjlModel model,String tableName) throws Exception {
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.dao_getQuery(model,tableName);
	}
	/**
	 * .查询违纪类别
	 */
	public List<HashMap<String, String>> ser_wjlbList() throws Exception {
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.dao_wjlbList();
	}
	/**
	 * .删除
	 */
	public boolean ser_xlzxsdel(String pk,HttpServletRequest request) throws Exception {
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.dao_getDel(pk,request);
	}
	/**
	 * Serv_get xs info.根据主键获取学生信息
	 * 
	 * @param xh the xh
	 */
	public HashMap<String, String> serv_getpkForXsInfo(String pk){
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.dao_getpkForXsInfo(pk);
	}
	/**
	 * .数据导出
	 */
	public void dao_expData(String tabName,HttpServletResponse response,zsjlModel model) throws Exception {
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		WritableSheet ws = wwb.createSheet("数据导出", 0);
		String[] ColumnName = new String[]{"xh","xn","xqmc","xm","xb","nj","xymc","zymc","bjmc","ldmc","jg",
				"mz","cs","qsh","cwh","ssbh","wjsy","wjsj","xxsh","wjlbmc","lrr","lrrxm"};
		String[] ColumnNameCN  = {"学号","学年","学期","姓名","性别","年级",Base.YXPZXY_KEY+"名称","专业名称","班级名称",
				"楼栋名称","籍贯","民族","楼层","寝室号","床位号","宿舍编号","违纪说明","时间","学校审核",
				"纪律名称","录入人用户名","录入人用户名"};
		try {
			Vector<Object> vec = new Vector<Object>();
			for (int m = 0; m < ColumnNameCN.length; m++) {
				ws.addCell(new Label(m, 0, ColumnNameCN[m]));
			}
			int k = ColumnName.length;
			vec = dao.dao_expData(tabName, ColumnName, model);
			for (int i = 0; i < vec.size(); i++) {
				String[] tmp = (String[]) vec.toArray()[i];
				for (int j = 0; j < k; j++) {
					ws.addCell(new Label(j, i + 1, tmp[j]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据导出失败!");
		} finally {
			wwb.write();
			wwb.close();
		}
	}
	public List<HashMap<String,String>> serv_getWjlbList(){
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.getWjlbList();
	}
	public ArrayList<String[]> serv_zsjlStatQue(GyglZsjlClModel model) throws  Exception{
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.dao_zsjlStatQue(model);
	}
	/**
	 *住宿纪律处理查询表头
	 */
	public List<HashMap<String, String>> getZsjlStatTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"r","xn","xqmc","xh","xm","xb","nj","bjmc","ldmc","qsh","cwh","wjsj","wjlbmc","lrrxm","sfcf","xycljg","xxsh"};
		colListCN = new String[]{"行号","学年","学期","学号","姓名","性别","年级","班级","楼栋","寝室号","床位号","违纪时间","纪律类别","违纪信息录入人","是否处分","处理结果","学校审核"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
	 * 统计数据导出
	 */
	public void ser_expStatData(WritableWorkbook wwb,GyglZsjlClModel model) throws Exception {
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		Vector<Object> vec = new Vector<Object>();

		WritableSheet ws = wwb.createSheet("楼层长队伍数据", 0);
		String[] ColumnName = new String[]{"xh","xn","xqmc","xm","xb","nj","xymc","zymc","bjmc","ldmc","jg",
				"mz","cs","qsh","cwh","ssbh","wjsy","wjsj","wjlbmc","lrr","lrrxm","sfcf","xycljgmc","xxsh","xxcljg"};
		String[] ColumnNameCN  = {"学号","学年","学期","姓名","性别","年级",Base.YXPZXY_KEY+"名称","专业名称","班级名称",
				"楼栋名称","籍贯","民族","楼层","寝室号","床位号","宿舍编号","违纪说明","时间",
				"纪律名称","录入人用户名","录入人用户名","是否处分","处理结果","学校审核","学校意见"};
		for (int m = 0; m < ColumnNameCN.length; m++) {
			ws.addCell(new Label(m, 0, ColumnNameCN[m]));
		}
		vec = dao.dao_expStatData(model, ColumnName);
		int k = ColumnName.length;
		for (int i = 0; i < vec.size(); i++) {
			String[] tmp = (String[]) vec.toArray()[i];
			for (int j = 0; j < k; j++) {
				ws.addCell(new Label(j, i + 1, tmp[j]));
			}
		}
	}
	
}
