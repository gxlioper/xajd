package xgxt.szdw.nbzy;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;

//
public class BjtsxmService {
	BjtsxmDAO dao = new BjtsxmDAO();

	// 获得表头
	public List<HashMap<String, String>> getTopTr(String tableName,
			String[] colList) {
		DAO dao = DAO.getInstance();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);
		return topTr;
	}

	// 获取班长所在班级详细信息
	public HashMap<String, String> getSzbjDetail(String xh) {
		return dao.getSzbjDetail(xh);
	}

	// 获取特色项目建设进度详细
	public List<HashMap<String, String>> getTsxmjsjdDetail(String xmdm) {
		return dao.getTsxmjsjdDetail(xmdm);
	}

	// 获取楼栋列表
	public List<HashMap<String, String>> ldList() {
		return dao.ldList();
	}

	// 获取层数列表
	public List<HashMap<String, String>> csList() {
		return dao.csList();
	}

	// 获取寝室列表
	public List<HashMap<String, String>> qsList() {
		return dao.qsList();
	}

//	 获取寝室层数
	public String getCs(String ssbh) {
		return dao.getCs(ssbh);
	}
	// 判断是否班长
	public boolean isBz(String xh) {
		return dao.isBz(xh);
	}

	// 获得班主任所带班级
	public List getBzrBj(String zgh) throws SQLException {

		return dao.getBzrBj(zgh);
	}

	// 保存班级特色项目申报
	public boolean saveBjtsxmSb(BjtsxmForm myForm,String pkValue, HttpServletRequest request) throws Exception {
		return dao.saveBjtsxmSb(myForm, pkValue,request);
	}

	// 保存班级特色项目建设进度申报
	public boolean saveBjtsxmJd(BjtsxmForm myForm, HttpServletRequest request)
			throws Exception {
		return dao.saveBjtsxmJd(myForm, request);
	}

	// 保存班级特色项目审核意见
	public boolean saveBjtsxmSh(String tableName, String pkValue,
			String[] columns, String[] values, HttpServletRequest request)
			throws Exception {
		return dao.saveBjtsxmSh(tableName, pkValue, columns, values, request);
	}

	// 保存班级特色项目建设进度审核
	public boolean saveBjtsxmjdSh(String pkValue, String[] columns,
			String[] values, HttpServletRequest request) throws Exception {
		return dao.saveBjtsxmjdSh(pkValue, columns, values, request);
	}

	// 删除申请信息
	public boolean delxmSq(String tableName,String xmdm, HttpServletRequest request)
			throws Exception {
		return dao.delxmSq(tableName,xmdm, request);
	}

	// 是否通过审核
	public boolean noSh(String tableName, String xmdm) {
		return dao.noSh(tableName, xmdm);
	}
	// 获得系统时间
	public String GetSysTime() {
		return dao.GetSysTime();
	}

	// 获取审核通过的特色项目信息List
	public List<HashMap<String, String>> getTsxmJdList(BjtsxmForm myForm,
			String[] colList, String userName, String userType)
			throws SQLException {
		return dao.getTsxmJdList(myForm, colList, userName, userType);
	}

	// 获取特色项目申报结果信息List
	public List<HashMap<String, String>> getTsxmsbjgList(BjtsxmForm myForm,
			String[] colList, String userName, String userType)
			throws SQLException {
		return dao.getTsxmsbjgList(myForm, colList, userName, userType);
	}

	// 获取特色项目建设验收信息List
	public List<HashMap<String, String>> getTsxmjsysList(BjtsxmForm myForm,
			String[] colList, String userName, String userType)
			throws SQLException {
		return dao.getTsxmjsysList(myForm, colList, userName, userType);
	}

	// 获取特色项目详细
	public HashMap<String, String> getTsxmsbDetail(String xmdm) {
		return dao.getTsxmsbDetail(xmdm);
	}

	// 获取特色项目验收详细
	public HashMap<String, String> getTsxmYsDetail(String xmdm) {
		return dao.getTsxmYsDetail(xmdm);
	}

	// 获取特色项目延期验收详细
	public HashMap<String, String> getTsxmYqysDetail(String xmdm) {
		return dao.getTsxmYqysDetail(xmdm);
	}

	// 班长所在班级全部同学列表
	public List<HashMap<String, String>> getRyList(String bzxh) {
		return dao.getRyList(bzxh);
	}

	// 保存班级特色项目验收
	public boolean saveBjtsxmYs(BjtsxmForm myForm, HttpServletRequest request)
			throws Exception {
		return dao.saveBjtsxmYs(myForm, request);
	}

	// 保存班级特色项目延期验收
	public boolean saveBjtsxmYqys(BjtsxmForm myForm, HttpServletRequest request)
			throws Exception {
		return dao.saveBjtsxmYqys(myForm, request);
	}

	// 获取特色项目验收班级同学意见
	public List<HashMap<String, String>> getTsxmYsTxyj(String xmdm) {
		return dao.getTsxmYsTxyj(xmdm);
	}

	// 获得班级同学姓名
	public String getXsxm(String xh) {
		return dao.getXsxm(xh);
	}

	// 获取特色项目建设验收信息List
	public List<HashMap<String, String>> getTsxmYqys(BjtsxmForm myForm,
			String[] colList, String userName, String userType)
			throws SQLException {
		return dao.getTsxmYqys(myForm, colList, userName, userType);
	}
}