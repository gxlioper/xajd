package xgxt.jxgl.gt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.DAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.jxgl.JxglForm;
import xgxt.pjpy.PjpyTyForm;
import xgxt.utils.ExcelMethods;
import xgxt.utils.Pages;

public class JxglService {

	JxglDAO jxglDao = new JxglDAO();

	/**
	 * @describe ����꼶�б�
	 * @author luo
	 */
	public List<HashMap<String, String>> getNjList() {
		return jxglDao.getNjList();
	}
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

	/**
	 * @describe ����Ŷӻ���ϢList
	 * @author luo
	 */
	public Vector<Object> getTdhjList(JxglForm myForm, String[] colList) {

		return jxglDao.getTdhjList(myForm, colList);
	}
	public int getTdhjListCount(JxglForm myForm, String colList) {

		return jxglDao.getTdhjListCount(myForm, colList);
	}

	/**
	 * @describe ��ý̹���ϢList
	 * @author luo
	 */
	public Vector<Object> getJgxxList(JxglForm myForm, String[] colList) {

		return jxglDao.getJgxxList(myForm, colList);
	}

	/**
	 * @describe ��ȡ��ѵ�ɲ���ϢList
	 * @author luo
	 */
	public Vector<Object> getGbxxList(JxglForm myForm, String[] colList) {

		return jxglDao.getGbxxList(myForm, colList);
	}

	/**
	 * @describe ��ô��ӽ�ʦ��ϢList
	 * @author luo
	 */
	public Vector<Object> getJsxxList(JxglForm myForm, String[] colList) {

		return jxglDao.getJsxxList(myForm, colList);
	}

	/**
	 * @describe ��þ�ѵ����List
	 * @author luo
	 * @throws SQLException
	 */
	public ArrayList<String[]> getArmyStuList(JxglForm myForm,
			String[] colList, String isFdy, String userName)
			throws SQLException {

		return jxglDao.getArmyStuList(myForm, colList, isFdy, userName);
	}

	/**
	 * @describe �õ��ѽ����б�List
	 * @author luo
	 */
	public List<HashMap<String, String>> getLdList(String jxnd) {
		return jxglDao.getLdList(jxnd);
	}

	/**
	 * @describe ����Ŷӻ������б�
	 * @author luo
	 */
	public List<HashMap<String, String>> getTdList(String jxnd) {
		return jxglDao.getTdList(jxnd);
	}

	/**
	 * @describe �õ��ѽ����б�(����С����)
	 * @author luo
	 */
	public List<HashMap<String, String>> getLdListB(String jxnd) {
		return jxglDao.getLdListB(jxnd);
	}

	/**
	 * @describe ��ӽ̹���Ϣ
	 * @author luo
	 * @throws Exception
	 */
	public boolean saveJgxx(JxglForm myForm, HttpServletRequest request)
			throws Exception {

		return jxglDao.saveJgxx(myForm, request);
	}

	// �Ƿ���ڸý̹�
	public boolean isJg(JxglForm jxglform) throws Exception {
		return jxglDao.isJg(jxglform);
	}
	
	/**
	 * @describe ����Ŷӻ���Ϣ
	 * @author luo
	 * @throws Exception
	 */
	public boolean saveTdhjxx(JxglForm myForm, HttpServletRequest request)
			throws Exception {

		return jxglDao.saveTdhjxx(myForm, request);
	}

	/**
	 * @describe ��Ӿ�ѵ�ɲ���Ϣ
	 * @author luo
	 * @throws Exception
	 */
	public boolean saveGbxx(JxglForm myForm, HttpServletRequest request)
			throws Exception {

		return jxglDao.saveGbxx(myForm, request);
	}

	/**
	 * @describe ��Ӵ��ӽ�ʦ��Ϣ
	 * @author luo
	 * @throws Exception
	 */
	public boolean saveJsxx(JxglForm myForm, HttpServletRequest request)
			throws Exception {

		return jxglDao.saveJsxx(myForm, request);
	}

	/**
	 * @describe ɾ����ѵ�ɲ���Ϣ
	 * @author luo
	 * @throws Exception
	 */
	public boolean delGbxx(String zgh, HttpServletRequest request)
			throws Exception {

		return jxglDao.delGbxx(zgh, request);
	}

	/**
	 * @describe ɾ���̹���Ϣ
	 * @author luo
	 * @throws Exception
	 */
	public boolean delJgxx(String jgbh, HttpServletRequest request)
			throws Exception {

		return jxglDao.delJgxx(jgbh, request);
	}

	/**
	 * @describe ɾ���Ŷӻ���Ϣ
	 * @author luo
	 * @throws Exception
	 */
	public boolean delTdxx(String pk, HttpServletRequest request)
			throws Exception {

		return jxglDao.delTdxx(pk, request);
	}

	/**
	 * @describe ɾ�����ӽ�ʦ��Ϣ
	 * @author luo
	 * @throws Exception
	 */
	public boolean delJsxx(String jsdm, HttpServletRequest request)
			throws Exception {

		return jxglDao.delJsxx(jsdm, request);
	}

	/**
	 * @describe ��Ӿ�ѵ�ɼ�
	 * @author luo
	 * @throws Exception
	 */
	public boolean saveJxcj(String[] xhArray, String[] ndArray,
			String[] cjArray, String lrr, HttpServletRequest request)
			throws Exception {

		return jxglDao.saveJxcj(xhArray, ndArray, cjArray, lrr, request);
	}

	/**
	 * @describe ��ȡ��ѵѧ����ϸ
	 * @author luo
	 * @throws Exception
	 */
	public HashMap<String, String> getArmyStuDetail(String doType, String pk,
			String pkValue, String colList[], String xh, String realTable)
			throws Exception {

		return jxglDao.getArmyStuDetail(doType, pk, pkValue, colList, xh,
				realTable);
	}

	/**
	 * @describe ��ȡ�̹���Ϣ
	 * @author luo
	 * @throws Exception
	 */
	public HashMap<String, String> getJgxx(String jgbh) throws Exception {

		return jxglDao.getJgxx(jgbh);
	}

	/**
	 * @describe ��ȡ�Ŷӻ���Ϣ
	 * @author luo
	 * @throws Exception
	 */
	public HashMap<String, String> getTdhjxx(String pk) throws Exception {

		return jxglDao.getTdhjxx(pk);
	}

	/**
	 * @describe ��ȡ�ɲ���Ϣ
	 * @author luo
	 * @throws Exception
	 */
	public HashMap<String, String> getGbxx(String zgh, String gblx)
			throws Exception {
		return jxglDao.getGbxx(zgh, gblx);
	}

	/**
	 * @describe ��ȡ���ӽ�ʦ��Ϣ
	 * @author luo
	 * @throws Exception
	 */
	public HashMap<String, String> getJsxxOne(String jsdm) throws Exception {

		return jxglDao.getJsxxOne(jsdm);
	}

	/**
	 * ���ݹ��Ų�ѯ��ʦ��Ϣ
	 * 
	 * @param jsid
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getJsxx(String jsid) {
		return jxglDao.getJsxx(jsid);
	}

	/**
	 * ���ݹ��Ų�ѯ�ɲ���Ϣ
	 * 
	 * @param jsid
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getGbxx(String jsid) {
		return jxglDao.getGbxx(jsid);
	}

	/**
	 * ����ѧ�Ų�ѯ��ѧ����Ϣ
	 * 
	 * @param jsid
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getXsxx(String xh) {
		return jxglDao.getXsxx(xh);
	}

	/**
	 * ��ʦ������Ϣ��ѯ��ͷ
	 * 
	 * @return List
	 */
	public List getJsghxxTopTr() {
		DAO dao = new DAO();
		String[] col = { "zgh", "xm", "xb", "bmmc", "xl", "sfmc" };
		String[] colCN = dao.getColumnNameCN(col, "view_fdyxx");
		return dao.arrayToList(col, colCN);
	}

	/**
	 * ��ʦ������Ϣ��ѯ
	 */
	public List searchJsghInfo(JxglForm model) {
		return jxglDao.searchJsghInfo(model);
	}

	// �õ���ѵ�༶�����б�
	public String getJxjz(String nj) throws SQLException {
		String sT = jxglDao.getJxjz(nj);
		return sT;
	}

	// �õ�����������
	public HashMap<String, String> getMaxJz() throws SQLException {
		return jxglDao.getMaxJz();
	}

	// �õ��δ��������
	public HashMap<String, String> getnextMaxJz() throws SQLException {
		return jxglDao.getnextMaxJz();
	}

	// �õ���С�����б�
	public List<HashMap<String, String>> getNextMinJz(String jxnd) {
		return jxglDao.getNextMinJz(jxnd);
	}

	// �õ���ѵ�ɼ�¼���б�
	public List<HashMap<String, String>> getCjlrList(JxglForm jxglform,
			String[] colList, String isFdy, String userName) {
		return jxglDao.getCjlrList(jxglform, colList, isFdy, userName);
	}

	// �õ����ƴ���
	public String getJxbzdm() {
		return jxglDao.getJxbzdm();
	}

	// ��ý��Ƶȼ�����
	public String getJzNum() {
		return jxglDao.getJzNum();
	}

	// �õ����Ƶȼ�
	public HashMap<String, String> getBzdj(String sjdm) {
		return jxglDao.getBzdj(sjdm);
	}
//	�õ��ϼ�����ѧ��
	public String getSjXn(String sjdm) {
		return jxglDao.getSjXn(sjdm);
	}
	// ��ô�����ʦ��������Ϣ
	public List<HashMap<String, String>> getJsList(String nj) {
		return jxglDao.getJsList(nj);
	}

	// ��þ�ѵ��װ��������Ϣ
	public List<HashMap<String, String>> getFzList() {
		return jxglDao.getFzList();
	}

	// �õ��ϼ�������ʦ
	public String getJsXm(String jsdm) {
		return jxglDao.getJsXm(jsdm);
	}

	// �õ�������ʦ����
	public String getJsOne(String sjdm) {
		return jxglDao.getJsOne(sjdm);
	}

	// ��ô��ӽ̹���������Ϣ
	public List<HashMap<String, String>> getJgList(String nj) {
		return jxglDao.getJgList(nj);
	}

	// �õ��ϼ����ӽ̹�
	public String getJgOne(String sjdm) {
		return jxglDao.getJgOne(sjdm);
	}

	// �õ��ϼ������Ա�
	public String getSjxb(String sjdm) {
		return jxglDao.getSjxb(sjdm);
	}

	// ��ý�����ϸ
	public HashMap<String, String> getJxjzxx(String bzdm, String nj)
			throws Exception {
		return jxglDao.getJxjzxx(bzdm, nj);
	}

	// ��þ�ѵ���Ƶȼ�
	public List<String> getAllJz() throws SQLException {
		return jxglDao.getAllJz();
	}

	// ���������Ϣ
	public boolean saveJxjz(JxglForm myForm, String doS,
			HttpServletRequest request) throws Exception {
		return jxglDao.saveJxjz(myForm, doS, request);
	}

	// ��ӽ̹�������Ϣ
	public boolean addJgxx(String jgbh, String sfzld, HttpServletRequest request)
			throws Exception {
		return jxglDao.addJgxx(jgbh, sfzld, request);
	}

	// ��ӽ�ʦ������Ϣ
	public boolean addJsxx(String jsdm, String sfzld, HttpServletRequest request)
			throws Exception {
		return jxglDao.addJsxx(jsdm, sfzld, request);
	}

	// δ����༶�б�
	public List getNoFpList(String nj, String xydm, String zydm, String xb,String xn) {
		return jxglDao.getNoFpList(nj, xydm, zydm, xb,xn);
	}

	// �ѷ���༶�б�
	public List getHadFpList(String sjdm, String nj) {
		return jxglDao.getHadFpList(sjdm, nj);
	}

	// ��Ӿ�ѵ����
	public boolean addJxmd(String[] bjdm, String nj, String sjdm, String xb,String xn)
			throws Exception {
		return jxglDao.addJxmd(bjdm, nj, sjdm, xb,xn);
	}

	// ɾ���ñ����µ����а༶
	public boolean delAllBz(String nj, String sjdm) throws Exception {
		return jxglDao.delAllBz(nj, sjdm);
	}

	// ��Ӿ�ѵ����
	public boolean addJxbz(String[] bjdm, String nj, String sjdm, String xb,
			String jsdm, String jgbh,String xn) throws Exception {
		return jxglDao.addJxbz(bjdm, nj, sjdm, xb, jsdm, jgbh,xn);
	}

	// ������Ӹ���̹٣���ʦ
	public int[] addLdJgJs(String[] bjdm, String jgbh, String jsdm, String sjdm)
			throws Exception {
		return jxglDao.addLdJgJs(bjdm, jgbh, jsdm, sjdm);
	}

	// �õ�����Ա������༶�б�
	public List<HashMap<String, String>> getFdyBjList(String zgh) {
		return jxglDao.getFdyBjList(zgh);
	}

	// �õ�����Ա������ѧԺ�б�
	public List<HashMap<String, String>> getFdyXyList(String zgh) {
		return jxglDao.getFdyXyList(zgh);
	}

	// �õ�����Ա������רҵ�б�
	public List<HashMap<String, String>> getFdyZyList(String zgh) {
		return jxglDao.getFdyZyList(zgh);
	}

	// �������ѧԺ�б�
	public List<HashMap<String, String>> getXsXyList(String nj) {
		return jxglDao.getXsXyList(nj);
	}

	// �������רҵ�б�
	public List<HashMap<String, String>> getXsZyList(String nj) {
		return jxglDao.getXsZyList(nj);
	}

	// ����ϼ����Ƶȼ�
	public List<HashMap<String, String>> getSjlJz(String sjdm, String nj)
			throws SQLException {
		return jxglDao.getSjlJz(sjdm, nj);
	}

	// ��þ�ѵְλ�����б�
	public List<HashMap<String, String>> getzwList(String dwdm)
			throws SQLException {
		return jxglDao.getzwList(dwdm);
	}

	// ��ò�ѵ�ɲ�ְ���������б�
	public List<HashMap<String, String>> getBzzwList(String dwdm)
			throws SQLException {
		return jxglDao.getBzzwList(dwdm);
	}

	// ��þ�ѵѧ��
	public String getJxxn(String bzdm) {
		return jxglDao.getJxxn(bzdm);
	}
	// ��þ�ѵ��λ�����б�
	public List<HashMap<String, String>> getdwList() throws SQLException {
		return jxglDao.getdwList();
	}

	// ����ϼ����Ƶȼ�
	public boolean delJxbz(String bzdm, String nj) throws Exception {
		return jxglDao.delJxbz(bzdm, nj);
	}

	// ��ȡ��ѵ�����б�
	public List<HashMap<String, String>> getJxtdjxList() {
		return jxglDao.selectJxtdjxList();
	}

	// ��ѵ�ɲ�ͨѶ¼��ӡ
	@SuppressWarnings("unchecked")
	public void printJxgb(WritableWorkbook wwb, String nd) {
		String xxmc = StandardOperation.getXxmc();

		List jgList = jxglDao.getJggbList(nd);
		List yjList = jxglDao.getYjgbList(nd);
		List ljList = jxglDao.getLjgbList(nd);
		List pjList = jxglDao.getPjgbList(nd);

		try {
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(14);
			wcfTytle.setFont(wfTytle);

			ws.addCell(new Label(0, 0, xxmc + "��ѵ��" + nd + "��ѧ����ѵУ�ڲ�ѵ�ɲ�ͨѶ¼",
					wcfTytle));

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);

			int rownum = 0;

			for (int i = 0; i < jgList.size(); i++) {

				HashMap<String, String> map = new HashMap<String, String>();
				map = (HashMap<String, String>) jgList.get(i);

				ws.addCell(new Label(0, 3 + rownum, String.valueOf(rownum + 1),
						wcfTytle));
				ws.addCell(new Label(1, 3 + rownum, map.get("xm"), wcfTytle));
				ws.addCell(new Label(2, 3 + rownum, map.get("bmmc"), wcfTytle));
				ws.addCell(new Label(3, 3 + rownum, map.get("dwmc"), wcfTytle));
				ws.addCell(new Label(4, 3 + rownum, map.get("zwmc"), wcfTytle));
				ws.addCell(new Label(5, 3 + rownum, map.get("bgdh"), wcfTytle));
				ws.addCell(new Label(6, 3 + rownum, map.get("zzdh"), wcfTytle));
				ws.addCell(new Label(7, 3 + rownum, map.get("sjdh"), wcfTytle));
				ws.addCell(new Label(8, 3 + rownum, map.get("xnh"), wcfTytle));

				rownum++;
			}
			for (int i = 0; i < yjList.size(); i++) {

				HashMap<String, String> yjmap = new HashMap<String, String>();
				yjmap = (HashMap<String, String>) yjList.get(i);

				ws.addCell(new Label(0, 3 + rownum, String.valueOf(rownum + 1),
						wcfTytle));
				ws.addCell(new Label(1, 3 + rownum, yjmap.get("xm"), wcfTytle));
				ws
						.addCell(new Label(2, 3 + rownum, yjmap.get("bmmc"),
								wcfTytle));
				ws
						.addCell(new Label(3, 3 + rownum, yjmap.get("dwmc"),
								wcfTytle));
				ws
						.addCell(new Label(4, 3 + rownum, yjmap.get("zwmc"),
								wcfTytle));
				ws
						.addCell(new Label(5, 3 + rownum, yjmap.get("bgdh"),
								wcfTytle));
				ws
						.addCell(new Label(6, 3 + rownum, yjmap.get("zzdh"),
								wcfTytle));
				ws
						.addCell(new Label(7, 3 + rownum, yjmap.get("sjdh"),
								wcfTytle));
				ws
						.addCell(new Label(8, 3 + rownum, yjmap.get("xnh"),
								wcfTytle));

				rownum++;

				for (int j = 0; j < ljList.size(); j++) {
					HashMap<String, String> ljmap = new HashMap<String, String>();
					ljmap = (HashMap<String, String>) ljList.get(j);

					if (ljmap.get("yjdm").equals(yjmap.get("bzdm"))) {
						ws.addCell(new Label(0, 3 + rownum, String
								.valueOf(rownum + 1), wcfTytle));
						ws.addCell(new Label(1, 3 + rownum, ljmap.get("xm"),
								wcfTytle));
						ws.addCell(new Label(2, 3 + rownum, ljmap.get("xymc"),
								wcfTytle));
						ws.addCell(new Label(3, 3 + rownum, ljmap.get("yjmc"),
								wcfTytle));
						ws.addCell(new Label(4, 3 + rownum, ljmap.get("zwmc"),
								wcfTytle));
						ws.addCell(new Label(5, 3 + rownum, ljmap.get("bgdh"),
								wcfTytle));
						ws.addCell(new Label(6, 3 + rownum, ljmap.get("zzdh"),
								wcfTytle));
						ws.addCell(new Label(7, 3 + rownum, ljmap.get("sjdh"),
								wcfTytle));
						ws.addCell(new Label(8, 3 + rownum, ljmap.get("xnh"),
								wcfTytle));

						rownum++;

						for (int k = 0; k < pjList.size(); k++) {
							HashMap<String, String> pjmap = new HashMap<String, String>();
							pjmap = (HashMap<String, String>) pjList.get(k);

							if (pjmap.get("jxdw").equals(ljmap.get("bzdm"))) {
								ws.addCell(new Label(0, 3 + rownum, String
										.valueOf(rownum + 1), wcfTytle));
								ws.addCell(new Label(1, 3 + rownum, pjmap
										.get("xm"), wcfTytle));
								ws.addCell(new Label(2, 3 + rownum, pjmap
										.get("bmmc"), wcfTytle));
								ws.addCell(new Label(3, 3 + rownum, pjmap
										.get("dwmc"), wcfTytle));
								ws.addCell(new Label(4, 3 + rownum, pjmap
										.get("zwmc"), wcfTytle));
								ws.addCell(new Label(5, 3 + rownum, pjmap
										.get("bgdh"), wcfTytle));
								ws.addCell(new Label(6, 3 + rownum, pjmap
										.get("zzdh"), wcfTytle));
								ws.addCell(new Label(7, 3 + rownum, pjmap
										.get("sjdh"), wcfTytle));
								ws.addCell(new Label(8, 3 + rownum, pjmap
										.get("xnh"), wcfTytle));
								rownum++;
							} else if (pjmap.get("bzdm").equals(
									ljmap.get("bzdm"))) {
								ws.addCell(new Label(0, 3 + rownum, String
										.valueOf(rownum + 1), wcfTytle));
								ws.addCell(new Label(1, 3 + rownum, pjmap
										.get("xm"), wcfTytle));
								ws.addCell(new Label(2, 3 + rownum, pjmap
										.get("bmmc"), wcfTytle));
								ws.addCell(new Label(3, 3 + rownum, pjmap.get(
										"dwmc").replace(pjmap.get("bzmc"), ""),
										wcfTytle));
								ws.addCell(new Label(4, 3 + rownum, pjmap
										.get("zwmc"), wcfTytle));
								ws.addCell(new Label(5, 3 + rownum, pjmap
										.get("bgdh"), wcfTytle));
								ws.addCell(new Label(6, 3 + rownum, pjmap
										.get("zzdh"), wcfTytle));
								ws.addCell(new Label(7, 3 + rownum, pjmap
										.get("sjdh"), wcfTytle));
								ws.addCell(new Label(8, 3 + rownum, pjmap
										.get("xnh"), wcfTytle));
								rownum++;
							}
						}
					}
				}
			}

			int m = 1;
			boolean n = false;
			for (int i = 0; i <= rownum; i++) {
				String d3 = "";
				String d4 = "";

				WritableCell d1 = ws.getWritableCell(3, 3 + i);
				if (i > 0) {
					WritableCell d2 = ws.getWritableCell(3, 3 + i - 1);
					d4 = d2.getContents();
				}
				d3 = d1.getContents();
				if (d3.equals(d4)) {
					m++;
					n = true;
				}
				if ((!d3.equals(d4)) && n) {
					ws.mergeCells(3, 3 + i - m, 3, 3 + i - 1);
					m = 1;
				}
			}

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;
			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	// ��ѵ���ƴ�ӡ
	@SuppressWarnings("unchecked")
	public void printGtJxbz(WritableWorkbook wwb, String xn)
			throws SQLException {
		String xxmc = StandardOperation.getXxmc();

		List<HashMap<String, String>> bzList = jxglDao.getGtBzList(xn);
		List<String> bzdjList = jxglDao.getGtBzdjList();
		String[] table = new String[] { "a", "b", "c", "d", "e", "f", "g", "h",
				"i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
				"u", "v", "w", "x", "y", "z" };
		int span = bzdjList.size();
		try {
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(14);
			wcfTytle.setFont(wfTytle);

			ws.mergeCells(0, 0, span * 2 + 2, 1);
			ws.addCell(new Label(0, 0, xxmc + "��ѵ" + xn + "ѧ��ѧ����ѵ���Ʒ����", wcfTytle));

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);

			int mc = 0;
			for (int i = 0; i < bzdjList.size(); i++) {
				ws.addCell(new Label(i + mc, 2, bzdjList.get(i), wcfTytle));
				ws.addCell(new Label(i + mc + 1, 2, bzdjList.get(i) + "��",
						wcfTytle));
				mc++;
			}
			ws.addCell(new Label(span + mc, 2, "�̹�", wcfTytle));
			ws.addCell(new Label(span + mc + 1, 2, "�Ա�", wcfTytle));
			ws.addCell(new Label(span + mc + 2, 2, "����", wcfTytle));
			mc = 0;

			for (int i = 0; i < bzList.size(); i++) {
				HashMap<String, String> map = bzList.get(i);
				for (int j = 0; j < bzdjList.size(); j++) {
					ws.addCell(new Label(j + mc, 3 + i, map.get(table[j]
							+ "bzdm"), wcfTytle));
					ws.addCell(new Label(j + mc + 1, 3 + i, map.get(table[j]
							+ "bzmc"), wcfTytle));
					mc++;
				}
				ws
						.addCell(new Label(span + mc, 3 + i, map.get("xm"),
								wcfTytle));
				ws.addCell(new Label(span + mc + 1, 3 + i, map.get("xb"),
						wcfTytle));
				ws.addCell(new Label(span + mc + 2, 3 + i, map.get("num"),
						wcfTytle));
				mc = 0;

			}

			for (int j = 0; j < bzdjList.size() * 2;) {
				int m = 1;
				boolean n = false;
				for (int i = 0; i <= bzList.size(); i++) {

					String a3 = "";
					String a4 = "";

					WritableCell a1 = ws.getWritableCell(j, 3 + i);
					if (i > 0) {
						WritableCell a2 = ws.getWritableCell(j, 3 + i - 1);
						a4 = a2.getContents();
					}
					a3 = a1.getContents();
					if (a3.equals(a4)) {
						m++;
						n = true;
					}

					if ((!a3.equals(a4)) && n) {
						ws.mergeCells(j + 1, 3 + i - m, j + 1, 3 + i - 1);
						if (j == bzdjList.size() * 2 - 2) {
							ws.mergeCells(j + 2, 3 + i - m, j + 2, 3 + i - 1);
						}
						m = 1;
					}
				}
				j += 2;
			}

			for (int j = bzdjList.size() * 2 - 2; j >= 0;) {
				ws.removeColumn(j);
				j -= 2;
			}

			wcfTytle = new WritableCellFormat();
			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(14);
			wcfTytle.setFont(wfTytle);

			ws.addCell(new Label(0, 0, xxmc + "��ѵ" + xn + "��ѧ����ѵ����", wcfTytle));

		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	// ��ѵ���ƴ�ӡ(��ְҵ)
	@SuppressWarnings("unchecked")
	public void printJhJxbz(WritableWorkbook wwb, String xn)
			throws SQLException {
		String xxmc = StandardOperation.getXxmc();

		List<HashMap<String, String>> bzList = jxglDao.getJhBzList(xn);

		try {
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(14);
			wcfTytle.setFont(wfTytle);

			ws.mergeCells(0, 0, 13, 1);
			ws.addCell(new Label(0, 0, xxmc + "��ѵ" + xn + "ѧ��ѧ����ѵ����", wcfTytle));

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);

			ws.addCell(new Label(0, 2, "��", wcfTytle));
			ws.addCell(new Label(1, 2, "��", wcfTytle));
			ws.addCell(new Label(2, 2, "�ų�", wcfTytle));
			ws.addCell(new Label(3, 2, "Ӫ", wcfTytle));
			ws.addCell(new Label(4, 2, "Ӫ", wcfTytle));
			ws.addCell(new Label(5, 2, "Ӫ��", wcfTytle));
			ws.addCell(new Label(6, 2, "��", wcfTytle));
			ws.addCell(new Label(7, 2, "��", wcfTytle));
			ws.addCell(new Label(8, 2, "����", wcfTytle));
			ws.addCell(new Label(9, 2, "��", wcfTytle));
			ws.addCell(new Label(10, 2, "��", wcfTytle));
			ws.addCell(new Label(11, 2, "�ų�", wcfTytle));
			ws.addCell(new Label(12, 2, "�Ա�", wcfTytle));
			ws.addCell(new Label(13, 2, "����", wcfTytle));
			

			for (int i = 0; i < bzList.size(); i++) {
				HashMap<String, String> map = bzList.get(i);
				ws.addCell(new Label(0, 3+i, map.get("abzdm"), wcfTytle));
				ws.addCell(new Label(1, 3+i, map.get("abzmc"), wcfTytle));
				ws.addCell(new Label(2, 3+i, map.get("axm"), wcfTytle));
				ws.addCell(new Label(3, 3+i, map.get("bbzdm"), wcfTytle));
				ws.addCell(new Label(4, 3+i, map.get("bbzmc"), wcfTytle));
				ws.addCell(new Label(5, 3+i, map.get("bxm"), wcfTytle));
				ws.addCell(new Label(6, 3+i, map.get("cbzdm"), wcfTytle));
				ws.addCell(new Label(7, 3+i, map.get("cbzmc"), wcfTytle));
				ws.addCell(new Label(8, 3+i, map.get("cxm"), wcfTytle));
				ws.addCell(new Label(9, 3+i, map.get("dbzdm"), wcfTytle));
				ws.addCell(new Label(10, 3+i, map.get("dbzmc"), wcfTytle));
				ws.addCell(new Label(11, 3+i, map.get("xm"), wcfTytle));
				ws.addCell(new Label(12, 3+i, map.get("xb"), wcfTytle));
				ws.addCell(new Label(13, 3+i, map.get("num"), wcfTytle));
			}

//			excel�ϲ���Ԫ��
			int m = 1;	
			int p = 1;
			int s = 1;
			int q = 1;
			
			boolean a = false;
			boolean d = false;
			boolean g = false;
			boolean j = false;
			
			for (int i = 0; i <= bzList.size(); i++) {
				String a3 = "";
				String a4 = "";
				String d3 = "";
				String d4 = "";
				String g3 = "";
				String g4 = "";
				String j3 = "";
				String j4 = "";
				
				WritableCell a1 = ws.getWritableCell(0, 3 + i);
				WritableCell d1 = ws.getWritableCell(3, 3 + i);
				WritableCell g1 = ws.getWritableCell(6, 3 + i);
				WritableCell j1 = ws.getWritableCell(9, 3 + i);
				
				if (i > 0) {
					WritableCell a2 = ws.getWritableCell(0, 3 + i - 1);
					WritableCell d2 = ws.getWritableCell(3, 3 + i - 1);
					WritableCell g2 = ws.getWritableCell(6, 3 + i - 1);
					WritableCell j2 = ws.getWritableCell(9, 3 + i - 1);
					
					a4 = a2.getContents();
					d4 = d2.getContents();
					g4 = g2.getContents();
					j4 = j2.getContents();
				}
				a3 = a1.getContents();
				d3 = d1.getContents();
				g3 = g1.getContents();
				j3 = j1.getContents();
				
				if (a3.equals(a4)) {
					m++;
					a = true;
				}
				if (d3.equals(d4)) {
					p++;
					d = true;
				}
				if (g3.equals(g4)) {
					s++;
					g = true;
				}
				if (j3.equals(j4)) {
					q++;
					j = true;
				}

				if ((!a3.equals(a4)) && a) {
					ws.mergeCells(1, 3 + i - m, 1, 3 + i - 1);
					ws.mergeCells(2, 3 + i - m, 2, 3 + i - 1);
					m = 1;
					a = false;
				}
				if ((!d3.equals(d4)) && d) {
					ws.mergeCells(4, 3 + i - p, 4, 3 + i - 1);
					ws.mergeCells(5, 3 + i - p, 5, 3 + i - 1);
					p = 1;
					d = false;
				}
				if ((!g3.equals(g4)) && g) {
					ws.mergeCells(7, 3 + i - s, 7, 3 + i - 1);
					ws.mergeCells(8, 3 + i - s, 8, 3 + i - 1);
					s = 1;
					g = false;
				}
				if ((!j3.equals(j4)) && j) {
					ws.mergeCells(10, 3 + i - q, 10, 3 + i - 1);
					ws.mergeCells(11, 3 + i - q, 11, 3 + i - 1);
					q = 1;
					j = false;
				}
			}
			ws.removeColumn(9);
			ws.removeColumn(6);
			ws.removeColumn(3);
			ws.removeColumn(0);
			
			wcfTytle = new WritableCellFormat();
			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(14);
			wcfTytle.setFont(wfTytle);

			ws.addCell(new Label(0, 0, xxmc + "��ѵ" + xn + "��ѧ����ѵ����", wcfTytle));

		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	// ��ѵ���ƴ�ӡ(������ѧԺ)
	@SuppressWarnings("unchecked")
	public void printJxbz(WritableWorkbook wwb, String nd) {
		String xxmc = StandardOperation.getXxmc();

		List bzList = jxglDao.getBzList(nd);

		try {
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(14);
			wcfTytle.setFont(wfTytle);

			ws
					.addCell(new Label(0, 0, xxmc + "��ѵ��" + nd + "��ѧ����ѵ����",
							wcfTytle));

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);

			String bjnum = "";
			int yjnum = 0;
			int ljnum = 0;
			int xbnum = 0;
			int pnum = 1;
			List<String> pjList = new ArrayList<String>();
			List<String> pjNumList = new ArrayList<String>();
			List<String> xbList = new ArrayList<String>();
			List<String> ljList = new ArrayList<String>();
			List<String> yjList = new ArrayList<String>();
			for (int i = 0; i < bzList.size(); i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map = (HashMap<String, String>) bzList.get(i);

				if (i > 0) {
					HashMap<String, String> pj = (HashMap<String, String>) bzList
							.get(i - 1);
					if (map.get("yjdm").equals(pj.get("yjdm"))) {
						yjnum += Integer.parseInt(pj.get("bjnum"));
					} else {
						yjnum += Integer.parseInt(pj.get("bjnum"));
						yjList.add("(" + String.valueOf(yjnum) + "��)"
								+ pj.get("fzmc"));
						yjnum = 0;
					}

					if (map.get("pjdm").equals(pj.get("pjdm"))) {
						bjnum += pj.get("bjmc") + "(" + pj.get("bjnum") + "��),";
					} else {
						bjnum = pj.get("pjmc") + ":" + bjnum + pj.get("bjmc")
								+ "(" + pj.get("bjnum") + "��)" + "��";
						pjList.add(bjnum);

						bjnum = "";

					}

					if (map.get("ljdm").equals(pj.get("ljdm"))) {
						ljnum += Integer.parseInt(pj.get("bjnum"));
						if (!map.get("pjdm").equals(pj.get("pjdm"))) {
							pnum++;
						}
						if (map.get("xb").equals(pj.get("xb"))) {
							xbnum += Integer.parseInt(pj.get("bjnum"));
						} else {
							xbnum += Integer.parseInt(pj.get("bjnum"));
							xbList.add(String.valueOf(xbnum));

							xbnum = 0;
						}
					} else {
						ljnum += Integer.parseInt(pj.get("bjnum"));
						ljList.add(String.valueOf(ljnum));
						ljnum = 0;

						pjNumList.add(String.valueOf(pnum));
						pnum = 1;

						xbnum += Integer.parseInt(pj.get("bjnum"));
						xbList.add(String.valueOf(xbnum));

						xbnum = 0;

					}

					if (i == bzList.size() - 1) {
						bjnum = map.get("pjmc") + ":" + bjnum + map.get("bjmc")
								+ "(" + map.get("bjnum") + "��)" + "��";
						pjList.add(bjnum);

						ljnum += Integer.parseInt(map.get("bjnum"));
						ljList.add(String.valueOf(ljnum));

						xbnum += Integer.parseInt(map.get("bjnum"));
						xbList.add(String.valueOf(xbnum));

						yjnum += Integer.parseInt(map.get("bjnum"));
						yjList.add("(" + String.valueOf(yjnum) + "��)"
								+ map.get("fzmc"));

						pjNumList.add(String.valueOf(pnum));

						pnum = 1;
						yjnum = 0;
						bjnum = "";
						ljnum = 0;
						xbnum = 0;
					}
				}

				ws
						.addCell(new Label(0, 3 + i, String.valueOf(i + 1),
								wcfTytle));
				ws.addCell(new Label(1, 3 + i, map.get("xymc"), wcfTytle));
				ws.addCell(new Label(2, 3 + i, map.get("yjmc"), wcfTytle));
				ws.addCell(new Label(3, 3 + i, map.get("yjzdy"), wcfTytle));
				ws.addCell(new Label(4, 3 + i, map.get("ljmc"), wcfTytle));
				ws.addCell(new Label(5, 3 + i, map.get("ljzdy"), wcfTytle));
				ws.addCell(new Label(6, 3 + i, map.get("xb"), wcfTytle));
				// ws.addCell(new Label(7, 3 + i, map.get("pnum"), wcfTytle));
				ws.addCell(new Label(8, 3 + i, map.get("pnum"), wcfTytle));
				ws.addCell(new Label(9, 3 + i, map.get("pjdm"), wcfTytle));

			}

			System.out.println("pjList:" + pjList);
			System.out.println("xbList:" + xbList);
			System.out.println("xbnum:" + xbnum);

			int xyrow = 1;
			int yjrow = 1;
			int ljrow = 1;
			int lzdyrow = 1;
			int xbrow = 1;
			int pjrow = 1;
			int pjnum = 0;
			boolean xy = false;
			boolean yj = false;
			boolean lj = false;
			boolean pj = false;
			// boolean lzdy = false;
			boolean xb = false;
			for (int i = 0; i <= bzList.size(); i++) {

				if (i == 4) {
					lzdyrow++;
				}
				String b3 = "";
				String b4 = "";
				String d3 = "";
				String d4 = "";
				String e3 = "";
				String e4 = "";
				String g3 = "";
				String g4 = "";
				String j3 = "";
				String j4 = "";

				WritableCell b1 = ws.getWritableCell(1, 3 + i);
				WritableCell d1 = ws.getWritableCell(2, 3 + i);
				WritableCell e1 = ws.getWritableCell(4, 3 + i);
				WritableCell g1 = ws.getWritableCell(6, 3 + i);
				WritableCell j1 = ws.getWritableCell(9, 3 + i);
				if (i > 0) {
					WritableCell b2 = ws.getWritableCell(1, 3 + i - 1);
					WritableCell d2 = ws.getWritableCell(2, 3 + i - 1);
					WritableCell e2 = ws.getWritableCell(4, 3 + i - 1);
					WritableCell g2 = ws.getWritableCell(6, 3 + i - 1);
					WritableCell j2 = ws.getWritableCell(9, 3 + i - 1);

					b4 = b2.getContents();
					d4 = d2.getContents();
					e4 = e2.getContents();
					g4 = g2.getContents();
					j4 = j2.getContents();
				}

				b3 = b1.getContents();
				d3 = d1.getContents();
				e3 = e1.getContents();
				g3 = g1.getContents();
				j3 = j1.getContents();

				if (j3.equals(j4)) {
					if (b3.equals(b4)) {
						xyrow++;
						xy = true;
					}
				}
				if (d3.equals(d4)) {
					yjrow++;
					yj = true;
					if (e3.equals(e4)) {
						ljrow++;
						lj = true;
						if (g3.equals(g4)) {
							xbrow++;
							xb = true;
						}
						if (j3.equals(j4)) {
							pjrow++;
							pj = true;
						}
					}
				}

				if (i == 1) {
					if (!b3.equals(b4)) {
						ws
								.addCell(new Label(9, 3, pjList.get(pjnum),
										wcfTytle));
						pjnum++;
					}
				}
				if ((!j3.equals(j4)) && xy) {
					ws.mergeCells(1, 3 + i - xyrow, 1, 3 + i - 1);
					xyrow = 1;
					xy = false;
				}
				if ((!d3.equals(d4)) && yj) {
					ws.mergeCells(2, 3 + i - yjrow, 2, 3 + i - 1);
					ws.addCell(new Label(2, 3 + i - yjrow, d4
							+ yjList.get(yjnum), wcfTytle));
					ws.mergeCells(3, 3 + i - yjrow, 3, 3 + i - 1);
					ws.mergeCells(4, 3 + i - ljrow, 4, 3 + i - 1);
					ws.addCell(new Label(4, 3 + i - ljrow, ljList.get(ljnum),
							wcfTytle));
					ws.mergeCells(5, 3 + i - xbrow, 5, 3 + i - 1);
					ws.mergeCells(6, 3 + i - xbrow, 6, 3 + i - 1);
					ws.mergeCells(7, 3 + i - xbrow, 7, 3 + i - 1);
					ws.addCell(new Label(7, 3 + i - xbrow, xbList.get(xbnum),
							wcfTytle));
					ws.mergeCells(8, 3 + i - ljrow, 8, 3 + i - 1);
					ws.addCell(new Label(8, 3 + i - ljrow, String
							.valueOf(pjNumList.get(ljnum)), wcfTytle));
					ws.mergeCells(9, 3 + i - pjrow, 9, 3 + i - 1);
					ws.addCell(new Label(9, 3 + i - pjrow, pjList.get(pjnum),
							wcfTytle));

					yjnum++;
					ljnum++;
					pjnum++;
					xbnum++;
					ljrow = 1;
					yjrow = 1;
					xbrow = 1;
					pjrow = 1;
					yj = false;
					lj = false;
					pj = false;
					xb = false;
				} else {
					if ((!e3.equals(e4)) && lj) {
						ws.mergeCells(4, 3 + i - ljrow, 4, 3 + i - 1);
						ws.addCell(new Label(4, 3 + i - ljrow, ljList
								.get(ljnum), wcfTytle));
						ws.mergeCells(5, 3 + i - xbrow, 5, 3 + i - 1);
						ws.mergeCells(6, 3 + i - xbrow, 6, 3 + i - 1);
						ws.mergeCells(7, 3 + i - xbrow, 7, 3 + i - 1);
						ws.addCell(new Label(7, 3 + i - xbrow, xbList
								.get(xbnum), wcfTytle));
						ws.mergeCells(8, 3 + i - ljrow, 8, 3 + i - 1);
						ws.addCell(new Label(8, 3 + i - ljrow, String
								.valueOf(pjNumList.get(ljnum)), wcfTytle));
						ws.mergeCells(9, 3 + i - pjrow, 9, 3 + i - 1);
						ws.addCell(new Label(9, 3 + i - pjrow, pjList
								.get(pjnum), wcfTytle));

						ljnum++;
						pjnum++;
						xbnum++;
						ljrow = 1;
						pjrow = 1;
						xbrow = 1;
						lj = false;
						pj = false;
						xb = false;
					} else {
						if ((!g3.equals(g4)) && xb) {
							ws.mergeCells(5, 3 + i - xbrow, 5, 3 + i - 1);
							ws.mergeCells(6, 3 + i - xbrow, 6, 3 + i - 1);
							ws.mergeCells(7, 3 + i - xbrow, 7, 3 + i - 1);
							ws.addCell(new Label(7, 3 + i - xbrow, xbList
									.get(xbnum), wcfTytle));
							xbnum++;
							xbrow = 1;
							xb = false;
						}
						if ((!j3.equals(j4)) && pj) {
							ws.mergeCells(9, 3 + i - pjrow, 9, 3 + i - 1);
							ws.addCell(new Label(9, 3 + i - pjrow, pjList
									.get(pjnum), wcfTytle));
							pjnum++;
							pjrow = 1;
							pj = false;
						}
					}
				}
			}

			System.out.println("xbnum:" + xbnum);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;
			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ���ѧ�������б�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXsLdList() {
		return jxglDao.getXsLdList();
	}
	
	/**
	 * ��þ�ѵ��ҳ
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getResultList(ArrayList<String[]> list,
			JxglForm model) {

		// ��ҳ
		ArrayList<String[]> rsList = new ArrayList<String[]>();

		if (list != null && list.size() > 0) {

			Pages pages = model.getPages();
			pages.setMaxRecord(list.size());
			int start = pages.getStart();
			int size = pages.getPageSize();

			for (int i = start; i < start + size; i++) {
				if (i < list.size()) {
					rsList.add(list.get(i));
				}
			}
		}

		return rsList;
	}
}
