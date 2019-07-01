package xgxt.sxjy.jsxx;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.form.SxjyForm;

public class SxjyService {

	SxjyDAO sxjyDAO = new SxjyDAO();

	/**
	 * @describe 获得表头
	 * @author luo
	 */
	public List<HashMap<String, String>> getTopTr(String tableName,
			String[] colList) {
		DAO dao = DAO.getInstance();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);
		return topTr;
	}

	// 获得学生基本西西
	public HashMap<String, String> getXsjbxx(String xh) {
		return sxjyDAO.getXsjbxx(xh);
	}

	// 获得学生问卷调查情况
	public HashMap<String, String> getWjdcQk(String xh, String sjlsh) {
		return sxjyDAO.getWjdcQk(xh, sjlsh);
	}

	// 获取新生问卷表详细信息
	public HashMap<String, String> getWjmc(String sjlsh) {
		return sxjyDAO.getWjmc(sjlsh);
	}

	// 获得填写的问卷调查名称
	public HashMap<String, String> getXswjbDetail(String pkValue) {
		return sxjyDAO.getXswjbDetail(pkValue);
	}

	// 获得该问卷的试题内容
	public HashMap<String, String> getSt(String stbh, String num) {
		return sxjyDAO.getSt(stbh, num);
	}

	// 获得试题已选择答案
	public HashMap<String, String> getStDa(String sjlsh, String num, String hdr) {
		return sxjyDAO.getStDa(sjlsh, num, hdr);
	}

	// 获取试题详细信息
	public HashMap<String, String> getStDetail(String stbh) {
		return sxjyDAO.getStDetail(stbh);
	}

	// 得到流水号代码
	public String getSjLsh() {
		return sxjyDAO.getSjLsh();
	}

	// 得到试题内容代码
	public String getNrLsh() {
		return sxjyDAO.getNrLsh();
	}

	// 获得该问卷的试题数目
	public String getStNum(String sjlsh) {
		return sxjyDAO.getStNum(sjlsh);
	}

	// 得到系统时间
	public String getNowDay() {
		return sxjyDAO.getNowDay();
	}

	// 保存新生调查表
	public boolean saveSjxx(SxjyForm myForm, HttpServletRequest request)
			throws Exception {
		return sxjyDAO.saveSjxx(myForm, request);
	}

	// 保存调查表内容
	public boolean saveNrxx(SxjyForm myForm, HttpServletRequest request)
			throws Exception {
		return sxjyDAO.saveNrxx(myForm, request);
	}

	// 保存组卷信息
	public boolean saveZj(SxjyForm myForm, HttpServletRequest request)
			throws Exception {
		return sxjyDAO.saveZj(myForm, request);
	}

	// 保存试题回答信息
	public boolean saveTx(SxjyForm myForm, HttpServletRequest request)
			throws Exception {
		return sxjyDAO.saveTx(myForm, request);
	}

	// 删除新生调查表
	public boolean delSjxx(String pkValue, HttpServletRequest request)
			throws Exception {
		return sxjyDAO.delSjxx(pkValue, request);
	}

	// 删除试题
	public boolean delSt(String pkValue) throws Exception {
		return sxjyDAO.delSt(pkValue);
	}

	// 获得新生问卷调查List
	public Vector<Object> getXswjdcList(SxjyForm myForm, String[] colList,
			String userType) {

		return sxjyDAO.getXswjdcList(myForm, colList, userType);
	}

	// 获取问卷表信息List
	public Vector<Object> getXswjdcQkList(SxjyForm myForm, String[] colList) {

		return sxjyDAO.getXswjdcQkList(myForm, colList);
	}

	// 获得问卷调查表名下拉列表
	public List<HashMap<String, String>> getWjdcb() {

		return sxjyDAO.getWjdcb();
	}

	// 获得已存在全部试题
	public List<HashMap<String, String>> getAllStList() {

		return sxjyDAO.getAllStList();
	}
}