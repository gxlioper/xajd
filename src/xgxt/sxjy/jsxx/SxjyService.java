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
	 * @describe ��ñ�ͷ
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

	// ���ѧ����������
	public HashMap<String, String> getXsjbxx(String xh) {
		return sxjyDAO.getXsjbxx(xh);
	}

	// ���ѧ���ʾ�������
	public HashMap<String, String> getWjdcQk(String xh, String sjlsh) {
		return sxjyDAO.getWjdcQk(xh, sjlsh);
	}

	// ��ȡ�����ʾ����ϸ��Ϣ
	public HashMap<String, String> getWjmc(String sjlsh) {
		return sxjyDAO.getWjmc(sjlsh);
	}

	// �����д���ʾ��������
	public HashMap<String, String> getXswjbDetail(String pkValue) {
		return sxjyDAO.getXswjbDetail(pkValue);
	}

	// ��ø��ʾ����������
	public HashMap<String, String> getSt(String stbh, String num) {
		return sxjyDAO.getSt(stbh, num);
	}

	// ���������ѡ���
	public HashMap<String, String> getStDa(String sjlsh, String num, String hdr) {
		return sxjyDAO.getStDa(sjlsh, num, hdr);
	}

	// ��ȡ������ϸ��Ϣ
	public HashMap<String, String> getStDetail(String stbh) {
		return sxjyDAO.getStDetail(stbh);
	}

	// �õ���ˮ�Ŵ���
	public String getSjLsh() {
		return sxjyDAO.getSjLsh();
	}

	// �õ��������ݴ���
	public String getNrLsh() {
		return sxjyDAO.getNrLsh();
	}

	// ��ø��ʾ��������Ŀ
	public String getStNum(String sjlsh) {
		return sxjyDAO.getStNum(sjlsh);
	}

	// �õ�ϵͳʱ��
	public String getNowDay() {
		return sxjyDAO.getNowDay();
	}

	// �������������
	public boolean saveSjxx(SxjyForm myForm, HttpServletRequest request)
			throws Exception {
		return sxjyDAO.saveSjxx(myForm, request);
	}

	// ������������
	public boolean saveNrxx(SxjyForm myForm, HttpServletRequest request)
			throws Exception {
		return sxjyDAO.saveNrxx(myForm, request);
	}

	// ���������Ϣ
	public boolean saveZj(SxjyForm myForm, HttpServletRequest request)
			throws Exception {
		return sxjyDAO.saveZj(myForm, request);
	}

	// ��������ش���Ϣ
	public boolean saveTx(SxjyForm myForm, HttpServletRequest request)
			throws Exception {
		return sxjyDAO.saveTx(myForm, request);
	}

	// ɾ�����������
	public boolean delSjxx(String pkValue, HttpServletRequest request)
			throws Exception {
		return sxjyDAO.delSjxx(pkValue, request);
	}

	// ɾ������
	public boolean delSt(String pkValue) throws Exception {
		return sxjyDAO.delSt(pkValue);
	}

	// ��������ʾ����List
	public Vector<Object> getXswjdcList(SxjyForm myForm, String[] colList,
			String userType) {

		return sxjyDAO.getXswjdcList(myForm, colList, userType);
	}

	// ��ȡ�ʾ����ϢList
	public Vector<Object> getXswjdcQkList(SxjyForm myForm, String[] colList) {

		return sxjyDAO.getXswjdcQkList(myForm, colList);
	}

	// ����ʾ������������б�
	public List<HashMap<String, String>> getWjdcb() {

		return sxjyDAO.getWjdcb();
	}

	// ����Ѵ���ȫ������
	public List<HashMap<String, String>> getAllStList() {

		return sxjyDAO.getAllStList();
	}
}