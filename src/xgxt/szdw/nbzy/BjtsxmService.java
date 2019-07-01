package xgxt.szdw.nbzy;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;

//
public class BjtsxmService {
	BjtsxmDAO dao = new BjtsxmDAO();

	// ��ñ�ͷ
	public List<HashMap<String, String>> getTopTr(String tableName,
			String[] colList) {
		DAO dao = DAO.getInstance();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);
		return topTr;
	}

	// ��ȡ�೤���ڰ༶��ϸ��Ϣ
	public HashMap<String, String> getSzbjDetail(String xh) {
		return dao.getSzbjDetail(xh);
	}

	// ��ȡ��ɫ��Ŀ���������ϸ
	public List<HashMap<String, String>> getTsxmjsjdDetail(String xmdm) {
		return dao.getTsxmjsjdDetail(xmdm);
	}

	// ��ȡ¥���б�
	public List<HashMap<String, String>> ldList() {
		return dao.ldList();
	}

	// ��ȡ�����б�
	public List<HashMap<String, String>> csList() {
		return dao.csList();
	}

	// ��ȡ�����б�
	public List<HashMap<String, String>> qsList() {
		return dao.qsList();
	}

//	 ��ȡ���Ҳ���
	public String getCs(String ssbh) {
		return dao.getCs(ssbh);
	}
	// �ж��Ƿ�೤
	public boolean isBz(String xh) {
		return dao.isBz(xh);
	}

	// ��ð����������༶
	public List getBzrBj(String zgh) throws SQLException {

		return dao.getBzrBj(zgh);
	}

	// ����༶��ɫ��Ŀ�걨
	public boolean saveBjtsxmSb(BjtsxmForm myForm,String pkValue, HttpServletRequest request) throws Exception {
		return dao.saveBjtsxmSb(myForm, pkValue,request);
	}

	// ����༶��ɫ��Ŀ��������걨
	public boolean saveBjtsxmJd(BjtsxmForm myForm, HttpServletRequest request)
			throws Exception {
		return dao.saveBjtsxmJd(myForm, request);
	}

	// ����༶��ɫ��Ŀ������
	public boolean saveBjtsxmSh(String tableName, String pkValue,
			String[] columns, String[] values, HttpServletRequest request)
			throws Exception {
		return dao.saveBjtsxmSh(tableName, pkValue, columns, values, request);
	}

	// ����༶��ɫ��Ŀ����������
	public boolean saveBjtsxmjdSh(String pkValue, String[] columns,
			String[] values, HttpServletRequest request) throws Exception {
		return dao.saveBjtsxmjdSh(pkValue, columns, values, request);
	}

	// ɾ��������Ϣ
	public boolean delxmSq(String tableName,String xmdm, HttpServletRequest request)
			throws Exception {
		return dao.delxmSq(tableName,xmdm, request);
	}

	// �Ƿ�ͨ�����
	public boolean noSh(String tableName, String xmdm) {
		return dao.noSh(tableName, xmdm);
	}
	// ���ϵͳʱ��
	public String GetSysTime() {
		return dao.GetSysTime();
	}

	// ��ȡ���ͨ������ɫ��Ŀ��ϢList
	public List<HashMap<String, String>> getTsxmJdList(BjtsxmForm myForm,
			String[] colList, String userName, String userType)
			throws SQLException {
		return dao.getTsxmJdList(myForm, colList, userName, userType);
	}

	// ��ȡ��ɫ��Ŀ�걨�����ϢList
	public List<HashMap<String, String>> getTsxmsbjgList(BjtsxmForm myForm,
			String[] colList, String userName, String userType)
			throws SQLException {
		return dao.getTsxmsbjgList(myForm, colList, userName, userType);
	}

	// ��ȡ��ɫ��Ŀ����������ϢList
	public List<HashMap<String, String>> getTsxmjsysList(BjtsxmForm myForm,
			String[] colList, String userName, String userType)
			throws SQLException {
		return dao.getTsxmjsysList(myForm, colList, userName, userType);
	}

	// ��ȡ��ɫ��Ŀ��ϸ
	public HashMap<String, String> getTsxmsbDetail(String xmdm) {
		return dao.getTsxmsbDetail(xmdm);
	}

	// ��ȡ��ɫ��Ŀ������ϸ
	public HashMap<String, String> getTsxmYsDetail(String xmdm) {
		return dao.getTsxmYsDetail(xmdm);
	}

	// ��ȡ��ɫ��Ŀ����������ϸ
	public HashMap<String, String> getTsxmYqysDetail(String xmdm) {
		return dao.getTsxmYqysDetail(xmdm);
	}

	// �೤���ڰ༶ȫ��ͬѧ�б�
	public List<HashMap<String, String>> getRyList(String bzxh) {
		return dao.getRyList(bzxh);
	}

	// ����༶��ɫ��Ŀ����
	public boolean saveBjtsxmYs(BjtsxmForm myForm, HttpServletRequest request)
			throws Exception {
		return dao.saveBjtsxmYs(myForm, request);
	}

	// ����༶��ɫ��Ŀ��������
	public boolean saveBjtsxmYqys(BjtsxmForm myForm, HttpServletRequest request)
			throws Exception {
		return dao.saveBjtsxmYqys(myForm, request);
	}

	// ��ȡ��ɫ��Ŀ���հ༶ͬѧ���
	public List<HashMap<String, String>> getTsxmYsTxyj(String xmdm) {
		return dao.getTsxmYsTxyj(xmdm);
	}

	// ��ð༶ͬѧ����
	public String getXsxm(String xh) {
		return dao.getXsxm(xh);
	}

	// ��ȡ��ɫ��Ŀ����������ϢList
	public List<HashMap<String, String>> getTsxmYqys(BjtsxmForm myForm,
			String[] colList, String userName, String userType)
			throws SQLException {
		return dao.getTsxmYqys(myForm, colList, userName, userType);
	}
}