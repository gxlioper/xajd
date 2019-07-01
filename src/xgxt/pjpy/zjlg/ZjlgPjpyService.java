package xgxt.pjpy.zjlg;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.ExcelMethods;

public class ZjlgPjpyService {

	ZjlgPjpyDAO dao = new ZjlgPjpyDAO();

	/**
	 * @author luo
	 * @describe ��ñ�ͷ
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
	 * @author luo
	 * @describe ȡ�����ݿ�ϵͳʱ��
	 */
	public String getSysTime() throws SQLException {		
		return dao.getSysTime();
	}
	/**
	 * @author luo
	 * @describe �Ƿ������
	 */
	public boolean isBzrBj(String zgh) throws SQLException {
		return dao.isBzrBj(zgh);
	}

	/**
	 * @author luo
	 * @describe �������Ƿ����ñ���
	 */
	public boolean isDySz(String xn) {
		return dao.isDySz(xn);
	}

	/**
	 * @author luo
	 * @describe ��������ֶ��б�
	 */
	public List<HashMap<String, String>> getZdList(String szlx) throws SQLException {
		return dao.getZdList(szlx);
	}

	/**
	 * @author luo
	 * @describe ��ý�ѧ������б�
	 */
	public List<HashMap<String, String>> getJxjlbList() throws SQLException {
		return dao.getJxjlbList();
	}

	/**
	 * @author luo
	 * @describe ��õ�������ɸ�����߷�����
	 */
	public HashMap<String, String> getDyZgf(String xn) throws SQLException {
		return dao.getDyZgf(xn);
	}

	/**
	 * @author luo
	 * @describe �������
	 */
	public List<HashMap<String, String>> getTjList(ZjlgPjpyModel model,
			String szlx, String[] colList) throws SQLException {
		return dao.getTjList(model, szlx, colList);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe ɾ������
	 */
	public boolean delTj(String pk, HttpServletRequest request)
	throws Exception {
		return dao.delTj(pk, request);
	}
	/**
	 * @author luo
	 * @describe ��ѧ���Ƿ��Ѿ����й����������ֵ�����
	 */
	public boolean isDySz(String zgh, String xn) throws SQLException {
		return dao.isDySz(zgh, xn);
	}

	/**
	 * @author luo
	 * @describe ��ð༶���ڷ�
	 */
	public List<HashMap<String, String>> getKqfList(ZjlgPjpyModel model,ZjlgPjpyForm myForm,
			String zgh, String[] colList, String userType) throws SQLException {
		return dao.getKqfList(model, myForm,zgh, colList, userType);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe �������濼�ڷ�
	 */
	public boolean saveKqf(ZjlgPjpyModel model) throws Exception {
		return dao.saveKqf(model);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe ����ƽʱ�ֱ�������
	 */
	public boolean saveBlSz(String xn, String zgh, ZjlgPjpyModel model,
			HttpServletRequest request) throws Exception {
		return dao.saveBlSz(xn, zgh, model, request);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe ���������������
	 */
	public boolean saveDyBlSz(String xn, ZjlgPjpyModel model,
			HttpServletRequest request) throws Exception {
		return dao.saveDyBlSz(xn, model, request);
	}
	/**
	 * @author luo
	 * @throws Exception
	 * @describe ��ȡƽʱ�ֱ�������
	 */
	public HashMap<String, String> getBlSz(String xn, String zgh)
	throws Exception {
		return dao.getBlSz(xn, zgh);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe ��ȡ�����ֱ�������
	 */
	public HashMap<String, String> getDyBlSz(String xn) throws Exception {
		return dao.getDyBlSz(xn);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe ��ȡ�ۺ����ʱ�������
	 */
	public HashMap<String, String> getZhBlSz(String xn) throws Exception {
		return dao.getZhBlSz(xn);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe �����ۺ����ʱ�������
	 */
	public boolean saveZhBlSz(String xn, ZjlgPjpyModel model,
			HttpServletRequest request) throws Exception {
		return dao.saveZhBlSz(xn, model, request);
	}
	/**
	 * @author luo
	 * @describe ��ð༶���ڷ�
	 */
	public List<HashMap<String, String>> getPsfList(ZjlgPjpyModel model,
			ZjlgPjpyForm myForm, String zgh, String[] colList, String userType)
			throws SQLException {
		return dao.getPsfList(model, myForm, zgh, colList, userType);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe ��������ƽʱ��
	 */
	public boolean savePsf(ZjlgPjpyModel model,String userType) throws Exception {
		return dao.savePsf(model,userType);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe ��������ƽʱ��(ѧԺ���ӷ�)
	 */
	public boolean savePsfXyfjf(ZjlgPjpyModel model) throws Exception {
		return dao.savePsfXyfjf(model);
	}

	/**
	 * @author luning
	 * @param myForm 
	 * @throws Exception
	 * @describe ��ѧ�����������б�
	 */
	public ArrayList<String[]> getJxjRsList(ZjlgPjpyModel myModel, ZjlgPjpyForm myForm) throws Exception {
		return dao.getJxjRsList(myModel,myForm);
	}

	/**
	 * @author luning
	 * @param bmlb 
	 * @throws Exception
	 * @describe ��ѧ�����������ͷ
	 */
	public List<HashMap<String, String>> getJxjRsTopTr(String bmlb) {

		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;

		//������ʾ��ʽ���ر�ͷ
		if(bmlb.equalsIgnoreCase("xydm")){
			colList = new String[]{"pk","xymc","cprs","jxjmc","jxjbl","jxjrs"}; 
			colListCN = new String[]{"pk","ѧԺ����","��������","��ѧ������","��ѧ�����","��ѧ������"}; 
		}else if ((bmlb.equalsIgnoreCase("bjdm"))){
			colList = new String[]{"pk","bjmc","cprs","jxjmc","jxjbl","jxjrs","cpzmc","cpzjxjrs"}; 
			colListCN = new String[]{"pk","�༶����","��������","��ѧ������","��ѧ�����","��ѧ������","����������","�����齱ѧ������"}; 
		}else if ((bmlb.equalsIgnoreCase("cpzdm"))){
			colList = new String[]{"pk","cpzmc","xymc","jxjmc","cprs","jxjbl","jxjrs"}; 
			colListCN = new String[]{"pk","����������","ѧԺ����","��ѧ������","��������","��ѧ�����","��ѧ������"}; 
		}

		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);

		return topTr;
	}

	/**
	 * @author luning
	 * @param myForm 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws Exception
	 * @describe �����ƺ������б�
	 */
	public ArrayList<String[]> getRychRsList(ZjlgPjpyModel myModel, ZjlgPjpyForm myForm) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		return dao.getRychRsList(myModel,myForm);
	}

	/**
	 * @author luning
	 * @param bmlb 
	 * @throws Exception
	 * @describe �����ƺ������ͷ
	 */
	public List<HashMap<String, String>> getRychRsTopTr(String bmlb) {
		DAO dao = DAO.getInstance();
		String[] colList = new String[]{"pk","bmmc","rychmc","cprs","rychbl","rychrs"}; 
		String[] colListCN = null;

		//������ʾ��ʽ���ر�ͷ
		if(bmlb.equalsIgnoreCase("xydm")){
			colListCN = new String[]{"pk","ѧԺ����","�����ƺ�����","��������","�����ƺű���","�����ƺ�����"}; 
		}else if ((bmlb.equalsIgnoreCase("bjdm"))){
			colListCN = new String[]{"pk","�༶����","�����ƺ�����","��������","�����ƺű���","�����ƺ�����"}; 
		}else if ((bmlb.equalsIgnoreCase("zydm"))){
			colListCN = new String[]{"pk","רҵ����","�����ƺ�����","��������","�����ƺű���","�����ƺ�����"}; 
		}

		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);

		return topTr;
	}

	/**
	 * @author luning
	 * @param myForm 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws Exception
	 * @describe �Ƚ��༶�����б�
	 */
	public ArrayList<String[]> getXjbjRsList(ZjlgPjpyModel myModel, ZjlgPjpyForm myForm) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		return dao.getXjbjRsList(myModel,myForm);
	}

	/**
	 * @author luning
	 * @throws Exception
	 * @describe �Ƚ��༶�����ͷ
	 */
	public List<HashMap<String, String>> getXjbjRsTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;

		//������ʾ��ʽ���ر�ͷ
		colList = new String[]{"pk","xn","xymc","bjgs","szbl","me"}; 
		colListCN = new String[]{"pk","ѧ��","ѧԺ����","�༶����","���ñ���","����"}; 
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);
		return topTr;
	}

	public List<HashMap<String, String>> getCpzBjList(ZjlgPjpyModel myModel) {
		String xn = Base.getJxjsqxn();
		String xydm = DealString.toGBK(myModel.getXydm());
		String cpzdm = DealString.toGBK(myModel.getCpzdm());
		return dao.getCpzBjList(xn,xydm,cpzdm);
	}

	public List<HashMap<String, String>> getCpzList(ZjlgPjpyModel myModel) throws Exception {
		String xn = myModel.getXn();
		String xydm = DealString.toGBK(myModel.getXydm());
		return dao.getCpzList(xn,xydm);
	}

	/**
	 * @author luning
	 * @throws Exception
	 * @describe ���Ӳ�����
	 */
	public boolean addCpz(HttpServletRequest request) {
		String tableName = "zjlg_cpzszb";
		String cpzdm = DealString.toGBK(request.getParameter("zjcpzdm"));
		String cpzmc = DealString.toGBK(request.getParameter("cpzmc"));
		String cpzxydm = request.getParameter("cpzxydm");
		HttpSession session =request.getSession();
		String userType = (String)session.getAttribute("userType");
		String userDep = (String)session.getAttribute("userDep");
		if (userType.equalsIgnoreCase("xy")
				&& (cpzxydm == null || cpzxydm.equalsIgnoreCase(""))) {
			cpzxydm = userDep;
		}
		String xn = Base.getJxjsqxn();
		boolean update = StandardOperation.insert(tableName, new String[]{"xydm","cpzdm","xn","cpzmc"}, new String[]{cpzxydm,cpzxydm+cpzdm,xn,cpzmc}, request);
		return update;
	}

	public List<HashMap<String, String>> getWfpBjList(ZjlgPjpyModel myModel) {
		// TODO �Զ����ɷ������
		String xydm = myModel.getXydm();
		String zydm = myModel.getZydm();
		String nj   = myModel.getNj();
		String xn   = Base.getJxjsqxn();
		return dao.getWfpBjList(xydm,zydm,nj,xn);
	}

	public boolean saveCpzFp(HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		String xn = Base.getJxjsqxn();
		String xydm = request.getParameter("xydm");
		HttpSession session =request.getSession();
		String userType = (String)session.getAttribute("userType");
		String userDep = (String)session.getAttribute("userDep");
		if (userType.equalsIgnoreCase("xy")
				&& (xydm == null || xydm.equalsIgnoreCase(""))) {
			xydm = userDep;
		}
		String cpzdm = DealString.toGBK(request.getParameter("cpzdm"));
		String bjdms = DealString.toGBK(request.getParameter("bjdms"));
		return dao.saveCpzFp(xn,cpzdm,bjdms,xydm,request);
	}

	public boolean jxjcsh() throws Exception {
		// TODO �Զ����ɷ������
		return dao.jxjcsh();
	}

	public boolean plszSave(ZjlgPjpyModel myModel, String userType) throws Exception {
		//�������ñ���
		return dao.plszSave(myModel,userType);
	}

	public boolean saveJxjRs(String pk, ZjlgPjpyModel myModel, HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		return dao.saveJxjRs(pk,myModel,request);
	}

	public boolean saveJxjXn(String jxjxn, HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������

		boolean updated = dao.saveJxjXn(jxjxn,request);
		if(updated){
			Base.setJxjsqxn(jxjxn);
		}
		return updated;
	}


	/**
	 * @author luo
	 * @describe ��ð༶������
	 */
	public List<HashMap<String, String>> getWsfList(ZjlgPjpyModel model,
			ZjlgPjpyForm myForm, String zgh, String[] colList, String userType)
			throws SQLException {
		return dao.getWsfList(model, myForm, zgh, colList, userType);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe ��������������
	 */
	public boolean saveWsf(ZjlgPjpyModel model,String userType) throws Exception {
		return dao.saveWsf(model,userType);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe ��������������(ѧԺ���ӷ�)
	 */
	public boolean saveWsfXyfjf(ZjlgPjpyModel model) throws Exception {
		return dao.saveWsfXyfjf(model);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe �ж��Ƿ����ù�������������߷�
	 */
	public boolean isMaxDy(String xn) {
		return dao.isMaxDy(xn);
	}
	/**
	 * @author luo
	 * @describe ��õ��������ܷ�
	 */
	public List<HashMap<String, String>> getZfList(ZjlgPjpyModel model,
			ZjlgPjpyForm myForm, String zgh, String[] colList, String userType,
			String userDep) throws SQLException {
		return dao.getZfList(model, myForm, zgh, colList, userType, userDep);
	}

	/**
	 * @author luo
	 * @describe ����ۺ����ʲ�����
	 */
	public List<HashMap<String, String>>  getZhszcpfList(ZjlgPjpyModel model,ZjlgPjpyForm myForm,
			String zgh, String[] colList,String userType, String isBzr) throws SQLException {
		return dao.getZhszcpfList(model,myForm, zgh, colList, userType, isBzr);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe �����ۺ����ʲ�����
	 */
	public boolean saveZhszcpf(ZjlgPjpyModel model) throws Exception {
		return dao.saveZhszcpf(model);
	}

	/**
	 * @author luo
	 * @describe ����ۺ����ʲ�����ӡ�б�
	 */
	public List<HashMap<String, String>> getZhszfList(ZjlgPjpyModel model)
	throws SQLException {
		return dao.getZhczPrintList(model);
	}

	/**
	 * @author luo
	 * @throws SQLException 
	 * @describe �ۺ����ʲ��������ӡ
	 */
	public void printZhszcp(WritableWorkbook wwb, String xn,String zydm,String bjdm) throws SQLException {

		String xxmc = StandardOperation.getXxmc();
		ZjlgPjpyModel myModel = new ZjlgPjpyModel();
		myModel.setXn(xn);
		myModel.setZydm(zydm);
		myModel.setBjdm(bjdm);

		List<HashMap<String, String>> list = dao.getZhczPrintList(myModel);
		String systime = getSysTime();
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

			ws.addCell(new Label(0, 0, xxmc  + xn + "ѧ���ۺϲ������ܱ�",
					wcfTytle));

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.RIGHT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.RIGHT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);

			ws.addCell(new Label(0, 2, systime,
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

			for (int i = 0; i < list.size(); i++) {

				HashMap<String, String> map = new HashMap<String, String>();
				map = (HashMap<String, String>) list.get(i);

				ws.addCell(new Label(0, 5 + rownum, map.get("pm"), wcfTytle));
				ws.addCell(new Label(1, 5 + rownum, map.get("xh"), wcfTytle));
				ws.addCell(new Label(2, 5 + rownum, map.get("bjmc"), wcfTytle));
				ws.addCell(new Label(3, 5 + rownum, map.get("xm"), wcfTytle));
				ws.addCell(new Label(4, 5 + rownum, map.get("dycpf"), wcfTytle));
				ws.addCell(new Label(5, 5 + rownum, map.get("dypm"), wcfTytle));
				ws.addCell(new Label(6, 5 + rownum, map.get("zycpf"), wcfTytle));
				ws.addCell(new Label(7, 5 + rownum, map.get("zypm"), wcfTytle));
				ws.addCell(new Label(8, 5 + rownum, map.get("bjg"), wcfTytle));
				ws.addCell(new Label(9, 5 + rownum, map.get("zhcpf"), wcfTytle));

				rownum++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);

	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe ������������
	 */
	public boolean saveTjsz(String xn,String szlx,String jxjdm,String zdm,String tj,String tjlx,String tjz,
			HttpServletRequest request) throws Exception {
		return dao.saveTjsz(xn, szlx, jxjdm, zdm,tj,tjlx,tjz, request);
	}

	/**
	 * @author luo
	 * @describe ���������� 
	 * @ xn(ѧ��) 
	 * @ szlx(�����ƺţ�rych���Ƚ��༶��xjbj����ѧ��jxj��)
	 * @ jxjdm(��ѧ����룬���������ͷǽ�ѧ��Ϊ��)
	 * @ dyf(����������)
	 */
	public boolean dyfTj(String xn, String szlx,String jxjdm, String dyf) {
		if(Base.isNull(dyf)){
			dyf="0";
		}
		return dao.dyfTj(xn, szlx, jxjdm,dyf);
	}

	/**
	 * @author luo
	 * @describe ������������ 
	 * @ xn(ѧ��) 
	 * @ szlx(�����ƺţ�rych���Ƚ��༶��xjbj����ѧ��jxj��)
	 * @ jxjdm(��ѧ����룬���������ͷǽ�ѧ��Ϊ��)
	 * @ zyf(����������)
	 */
	public boolean zyfTj(String xn, String szlx,String jxjdm, String zyf) {
		if(Base.isNull(zyf)){
			zyf="0";
		}
		return dao.zyfTj(xn, szlx, jxjdm,zyf);
	}

	/**
	 * @author luo
	 * @describe �ۺ����ʷ�����
	 * @ xn(ѧ��) 
	 * @ szlx(�����ƺţ�rych���Ƚ��༶��xjbj����ѧ��jxj��)
	 * @ jxjdm(��ѧ����룬���������ͷǽ�ѧ��Ϊ��)
	 * @ zhszf(�ۺ����ʲ�����)
	 */
	public boolean zhszfTj(String xn, String szlx,String jxjdm, String zhszf) {
		if(Base.isNull(zhszf)){
			zhszf="0";
		}
		return dao.zhszfTj(xn, szlx, jxjdm, zhszf);
	}

	/**
	 * @author luo
	 * @throws SQLException 
	 * @describe �����ɼ���������
	 * @ xh(ѧ��)
	 * @ xn(ѧ��) 
	 * @ szlx(�����ƺţ�rych���Ƚ��༶��xjbj����ѧ��jxj��)
	 * @ jxjdm(��ѧ����룬���������ͷǽ�ѧ��Ϊ��)
	 * @ cpzdm(���������)
	 */
	public boolean dyfpmTj(String xh, String xn, String szlx, String jxjdm,
			String cpzdm) throws SQLException {
		return dao.dyfpmTj(xh, xn, szlx, jxjdm, cpzdm);
	}

	/**
	 * @author luo
	 * @throws SQLException 
	 * @describe �����ɼ���������
	 * @ xh(ѧ��)
	 * @ xn(ѧ��) 
	 * @ szlx(�����ƺţ�rych���Ƚ��༶��xjbj����ѧ��jxj��)
	 * @ jxjdm(��ѧ����룬���������ͷǽ�ѧ��Ϊ��)
	 * @ cpzdm(���������)
	 */
	public boolean zyfpmTj(String xh, String xn, String szlx, String jxjdm,
			String cpzdm) throws SQLException {
		return dao.zyfpmTj(xh, xn, szlx, jxjdm, cpzdm);
	}

	/**
	 * @author luo
	 * @throws SQLException 
	 * @describe �ۺ����ʲ����ɼ���������
	 * @ xh(ѧ��)
	 * @ xn(ѧ��) 
	 * @ szlx(�����ƺţ�rych���Ƚ��༶��xjbj����ѧ��jxj��)
	 * @ jxjdm(��ѧ����룬���������ͷǽ�ѧ��Ϊ��)
	 * @ cpzdm(���������)
	 */
	public boolean zhpmTj(String xh, String xn, String szlx, String jxjdm,
			String cpzdm) throws SQLException {
		return dao.zhpmTj(xh, xn, szlx, jxjdm, cpzdm);
	}

	/**
	 * @author luo
	 * @describe �༶ƽ��������
	 * @ xn(ѧ��) 
	 * @ szlx(�����ƺţ�rych���Ƚ��༶��xjbj����ѧ��jxj��)
	 * @ jxjdm(��ѧ����룬���������ͷǽ�ѧ��Ϊ��)
	 * @ bjdm(�༶���룬�ð༶��ƽ����)
	 */
	public boolean bjpjfTj(String xn, String szlx, String jxjdm,
			String bjdm) {
		return dao.bjpjfTj(xn, szlx, jxjdm, bjdm);
	}

	/**
	 * @author luo
	 * @describe ������������
	 * @ xn(ѧ��) 
	 * @ szlx(�����ƺţ�rych���Ƚ��༶��xjbj����ѧ��jxj��)
	 * @ jxjdm(��ѧ����룬���������ͷǽ�ѧ��Ϊ��)
	 * @ bjdm(�༶���룬�ð༶�Ĳ�������)
	 */
	public boolean bjglTj(String xn, String szlx, String jxjdm,
			String bjdm) {
		return dao.bjglTj(xn, szlx, jxjdm, bjdm);
	}

	/**
	 * @author luo
	 * @describe �������Ҹ�������
	 * @ xn(ѧ��) 
	 * @ szlx(�����ƺţ�rych���Ƚ��༶��xjbj����ѧ��jxj��)
	 * @ jxjdm(��ѧ����룬���������ͷǽ�ѧ��Ϊ��)
	 * @ wmqsgs(������������)
	 */
	public boolean wmqsTj(String xn, String szlx,String jxjdm, String wmqsgs) {
		return dao.wmqsTj(xn, szlx, jxjdm, wmqsgs);
	}

	/**
	 * @author luo
	 * @describe A���������Ҹ�������
	 * @ xn(ѧ��) 
	 * @ szlx(�����ƺţ�rych���Ƚ��༶��xjbj����ѧ��jxj��)
	 * @ jxjdm(��ѧ����룬���������ͷǽ�ѧ��Ϊ��)
	 * @ ajqsgs(A��������������)
	 */
	public boolean ajqsgsTj(String xn, String szlx,String jxjdm, String ajqsgs) {
		return dao.ajqsgsTj(xn, szlx, jxjdm, ajqsgs);
	}

	/**
	 * @author luo
	 * @describe �����ɼ���������
	 * @ xn(ѧ��) 
	 * @ szlx(�����ƺţ�rych���Ƚ��༶��xjbj����ѧ��jxj��)
	 * @ jxjdm(��ѧ����룬���������ͷǽ�ѧ��Ϊ��)
	 * @ bfb(ȥ��רҵ�����ٷֱ�)
	 * @ qbfb(ǧ��רҵ�����ٷֱ�)
	 */
	public boolean zypmdzTj(String xn, String szlx,String jxjdm, String bfb,String qbfb) {
		return dao.zypmdzTj(xn, szlx, jxjdm, bfb, qbfb);
	}

	/**
	 * @author luo
	 * @describe ����ɼ�����
	 * @ xn(ѧ��) 
	 * @ szlx(�����ƺţ�rych���Ƚ��༶��xjbj����ѧ��jxj��)
	 * @ jxjdm(��ѧ����룬���������ͷǽ�ѧ��Ϊ��)
	 * @ wycj(����ɼ�)
	 */
	public boolean wycjTj(String xn, String szlx,String jxjdm, String wycj) {
		return dao.wycjTj(xn, szlx, jxjdm, wycj);
	}

	/**
	 * @author luo
	 * @describe �Ƿ�����༶����
	 * @ xn(ѧ��) 
	 * @ szlx(�����ƺţ�rych���Ƚ��༶��xjbj����ѧ��jxj��)
	 * @ jxjdm(��ѧ����룬���������ͷǽ�ѧ��Ϊ��)
	 * @ isorno("��"or"��")
	 */
	public boolean sfyxbjTj(String xn, String szlx,String jxjdm, String isorno) {
		return dao.sfyxbjTj(xn, szlx, jxjdm, isorno);
	}
	/**
	 * @author luo
	 * @describe ��ø���������ٷֱ�
	 */
	public String getZybfb(String xn,String xh,String cpzdm) throws SQLException {
		return dao.getZybfb(xn, xh, cpzdm);
	}
	/**
	 * @author luo
	 * @describe �Ƿ����������������
	 */
	public boolean iszydztj(String xn, String jxjdm) throws SQLException {
		return dao.iszydztj(xn, jxjdm);
	}

	/**
	 * ��ȡ�����ƺ��б�
	 * @return
	 */
	public List<HashMap<String,String>>serv_getRychList(){
		return dao.getRychList();
	}
	/**
	 * ��ȡѧ�������Ϣ
	 */
	public HashMap<String,String> serv_getXsInfo(String xh){
		String querry = " where xh='"+xh+"' ";
		return dao.dao_getInfo("view_xsxxb", querry);
	}
	/**
	 * jxjdm��ȡ·��
	 */
	public HashMap<String,String> getJxjlj(String jxjdm){
		return dao.dao_getJxjlj(jxjdm);
	}
	/**
	 * �жϸ��������������ƺ��Ƿ��ظ����Ƿ�ͨ�����
	 */
	public HashMap<String,String> serv_rychSqPd(RychModel model){
		String pk = "xh||xn||rychdm";
		String querry = " where  "+pk+"='"+model.getXh()+Base.getJxjsqxn()+model.getRychdm()+"' and xysh='ͨ��' ";
		return dao.dao_getInfo("zjlg_xsrychb", querry);
	}
	/**
	 *�����ƺ�������Ϣ���� 
	 * @throws Exception 
	 */
	public boolean serv_rychSave(RychModel model) throws Exception{
		return dao.dao_rychSave(model);
	}
	/**
	 * �����ƺ�������Ϣ��ѯ��ͷ
	 */
	public ArrayList<HashMap<String, String>> serv_getRychQerrTitle() {
		String[] colListCN = new String[]{"����","ѧ��","ѧ��","����","�����ƺ�","����ʱ��","Ժϵ���","ѧУ���"};
		return dao.dao_getSearchTitle(colListCN);
	}
	/**
	 * �����ƺ�������Ϣ��ѯ
	 */
	public ArrayList<String[]> serv_rychDefault(RychModel model){
		return dao.dao_rychDefault(model);
	}
	/**
	 *ѧ�������ƺ���Ϣ��ӱ��� 
	 */
	public boolean serv_rychAddSave(RychModel model) throws Exception{
		return dao.dao_rychAddSave(model);
	}
	/**
	 * ��ȡѧ�������Ϣ
	 */
	public HashMap<String,String> serv_getRychxxForXh(String pkValue){
		String querry = " where xh||xn||rychdm='"+pkValue+"' ";
		return dao.dao_getInfo("view_zjlg_xsrychxx", querry);
	}
	/**
	 *ѧ�������ƺ���Ϣ �޸ı��� 
	 */
	public boolean serv_rychModi(RychModel model,String pkValue) throws Exception{
		return dao.dao_rychAddModi(model,pkValue);
	}
	/**
	 *ѧ�������ƺ���Ϣ ɾ��
	 */
	public boolean serv_rychDelete(String delPk) throws Exception{
		return dao.dao_rychDelete(delPk);
	}
	/**
	 * ��ȡ����б�
	 */
	public List<HashMap<String, String>> serv_getChkList() {
		return dao.getChkList();
	}
	/**
	 * �����ƺ������Ϣ��ѯ
	 */
	public ArrayList<String[]> serv_rychCkDefault(RychModel model,String userType){
		return dao.dao_rychCkDefault(model, userType);
	}
	/**
	 * �����ƺ�������Ϣ��ѯ��ͷ
	 */
	public ArrayList<HashMap<String, String>> serv_getRychCkQerrTitle() {
		//String[] colList = new String[]{"pk","xn","xh","xm","rychmc","sqsj","xysh","xxsh","zycpf","dycpf","zhcpf","xypm","zypm","bjpm"};
		String[] colListCN = new String[]{"����","ѧ��","ѧ��","����","�����ƺ�","����ʱ��","Ժϵ���","ѧУ���","������","������","�ۺϲ�����","���ڲ���������"};
		return dao.dao_getSearchTitle(colListCN);
	}
	/**
	 * �����ƺ����
	 */
	public boolean serv_rychCk(String pkVStr,String userType,String check) throws Exception{
		return dao.dao_rychCk(pkVStr, userType, check);
	}
	/**
	 * ��ѧ���б�
	 * @param jxjlbdm 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjList(String jxjlbdm) throws Exception {
		dao = new ZjlgPjpyDAO();
		return dao.getJxjList(jxjlbdm);
	}
	/**
	 * ��ѧ���б�
	 * @param jxjlbdm 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> gettycj(String xh,String xn) throws Exception {
		return dao.gettycj(xh,xn);
	}
	/**
	*ѧ����ѧ����ӱ��� 
	*/
		public boolean dao_jxjAddSave(ZjlgPjpyModel model) throws Exception{
			return dao.dao_jxjAddSave(model);
		}
     /**
	*ѧ����ѧ���Ƿ��ظ��ύ 
	*/
		public boolean dao_jxjiscftj(ZjlgPjpyModel model) throws Exception{
			return dao.dao_jxjiscftj(model);
		}
	/**
	 * ��ѧ���ѯ��ͷ��ѯ��ͷ
	 */
	public ArrayList<HashMap<String, String>> getjxjTitle() {
		String[] colListCN = new String[]{"����","ѧ��","ѧ��","����","��ѧ����Ŀ","��ѧ�����","����ʱ��","Ժϵ���","ѧУ���"};
		return dao.dao_getSearchTitle(colListCN);
	}
	/**
	 * ����׷�ٲ�ѯ��ͷ
	 */
	public ArrayList<HashMap<String, String>> getWorkFlowTitle() {
		String[] colListCN = new String[]{"��������","�����","���ʱ��","����״̬","���"};
		return dao.dao_getSearchTitle(colListCN);
	}

	/**
	* ��ѧ���ѯ��ͷ��ѯ��ͷ
	*/
	public ArrayList<HashMap<String, String>> getjxjshTitle(String userType) {
		String[] colListCN;
		if("xy".equals(userType)){
			colListCN = new String[]{"����","ѧ��","����","��ѧ�����","����ʱ��","�༶����","Ժϵ���","�����ɼ�","�����ɼ�","�����ɼ�","�ۺϲ�������"};
		}else{
			colListCN = new String[]{"����","ѧ��","����","��ѧ�����","����ʱ��","�༶����","Ժϵ���","ѧУ���","�����ɼ�","�����ɼ�","�����ɼ�","�ۺϲ�������"};
		}
		return dao.dao_getSearchTitle(colListCN);
	}
			
	/**
	* ��ѧ�𷢷ű�ͷ��ѯ��ͷ
	*/
	public ArrayList<HashMap<String, String>> getjxjffTitle(String userType) {
		String[] colListCN;
		if("xy".equals(userType)){
			colListCN = new String[]{"ѧ��","����","��ѧ������","�������","ѧԺ����","�༶����"};
		}else{
			colListCN = new String[]{"ѧ��","����","��ѧ������","�������","ѧԺ����","�༶����"};
		}
		return dao.dao_getSearchTitle(colListCN);
	}
	/**
	 * ��ѧ���ѯ
	 */
	public ArrayList<String[]> jxj_query(ZjlgPjpyForm myForm,ZjlgPjpyModel model,String userType){
		return dao.jxj_query(myForm,model, userType);
	}
	/**
	 * ��ѧ���¼��ѯ
	 */
	public String jxj_queryrsunm(ZjlgPjpyForm myForm,ZjlgPjpyModel model,String userType){
		return dao.jxj_queryrsunm(myForm,model, userType);
	}
	/**
	 * ��ѧ���ѯview
	 */
	public HashMap<String, String> jxj_view(String pk,String jxjcxzj){
		return dao.jxj_view(pk,jxjcxzj);
	}
	/**
	 * ��ѧ����˲�ѯ
	 */
	public ArrayList<String[]> jxj_shquery(ZjlgPjpyForm myForm,ZjlgPjpyModel model,String userType){
		return dao.jxj_shquery(myForm,model, userType);
	}
	/**
	 * ��ѧ�𷢷Ų�ѯ
	 */
	public ArrayList<String[]> jxj_ffquery(ZjlgPjpyForm myForm,ZjlgPjpyModel model,String userType){
		return dao.jxj_ffquery(myForm,model, userType);
	}
	/**
	 * ��ѧ�𵼳�
	 * @throws IOException 
	 * @throws WriteException 
	 */
	public void jxj_DataExport(ZjlgPjpyForm myForm,ZjlgPjpyModel model,String jxjdm,HttpServletResponse response) throws WriteException, IOException{
		dao.jxj_DataExport(myForm,model,jxjdm,response);
	}
	/**
	 * ��ȡ�༶������Ϣ
	 */
	public HashMap<String,String> serv_getBjxx(String pkValue){
		String querry = " where bjdm='"+pkValue+"' ";
		return dao.dao_getInfo("view_njxyzybj", querry);
	}
	/**
	 * ��ȡ�༶����Ա
	 */
	public String serv_getFdyxxForBj(String pkValue) throws Exception{
		return dao.dao_getFdyForBjdm(pkValue);
	}
	/**
	 * ͳ�ư༶����
	 */
	public String serv_getXhNum(String bjdm){
		return dao.dao_getXhNum(bjdm);
	}
	/**
	 * �Ƚ��༶���뱣��
	 */
	public boolean serv_xjbjsqSave(XjbjModel model) throws Exception{
		return dao.dao_xjbjsqSave(model);
	}
	/** 
	 * Method ����ɾ��
	 * @param form ����
	 * @return void
	 * @throws Exception 
	 */
	public String getAllDelList(String pks) throws Exception {
		String whichpk = dao.getAllDelList(pks);
		return whichpk;
	}

	public boolean xjbjPlszSave(ZjlgPjpyModel myModel) throws Exception {
		//�Ƚ��༶�������ñ���
		return dao.xjbjPlszSave(myModel);
	}

	public boolean saveXjbjMe(String pk, ZjlgPjpyModel myModel, HttpServletRequest request) throws Exception {
		// �Ƚ��༶������������
		String me = myModel.getMe();
		return dao.saveXjbjMe(pk,me,request);
	}

	public String getXjbjMe(String xn,String xydm) {
		// ����ѧԺ�Ƚ��༶����
		return dao.getXjbjMe(xn,xydm);
	}	
	/**
	 * �Ƚ��༶������Ϣ��ѯ��ͷ
	 */
	public ArrayList<HashMap<String, String>> serv_getXjbjQerrTitle() {
		String[] colListCN = new String[]{"����","ѧ��","�༶","�꼶","Ժϵ","����ʱ��","Ժϵ���","ѧУ���"};
		return dao.dao_getSearchTitle(colListCN);
	}

	/**
	 * �Ƚ��༶������Ϣ��ѯ��ͷ
	 */
	public ArrayList<String[]> serv_xjbjDefault(XjbjModel model) {
		return dao.dao_xjbjDefault(model);
	}
	/**
	 * �Ƚ��༶��Ϣ���
	 */
	public boolean serv_xjbjAddSave(XjbjModel model) throws Exception{
		return dao.dao_xjbjAddSave(model);
	}
	/**
	 * ��ȡ�Ƚ��༶�����Ϣ
	 */
	public HashMap<String,String> serv_getXjbjxxForBj(String pkValue){
		String querry = " where bjdm||xn='"+pkValue+"' ";
		return dao.dao_getInfo("view_zjlg_xjbjxx", querry);
	}
	/**
	 * �Ƚ��༶��Ϣ�޸Ľ������
	 */
	public boolean serv_xjbjModiSave(XjbjModel  model,String pkValue) throws Exception{
		return dao.dao_xjbjModiSave(model, pkValue);
	}
	/**
	 *�Ƚ��༶��Ϣ ɾ��
	 */
	public boolean serv_xjbjDelete(String delPk) throws Exception{
		return dao.dao_xjbjDelete(delPk);
	}
	/**
	 * �Ƚ��༶�����Ϣ��ѯ
	 */
	public ArrayList<String[]> serv_xjbjCkDefault(XjbjModel model,String userType){
		return dao.dao_xjbjCkDefault(model, userType);
	}
	/**
	 * �Ƚ��༶���
	 */
	public boolean serv_xjbjCk(String pkVStr,String userType,String check) throws Exception{
		return dao.dao_xjbjCk(pkVStr, userType, check);
	}
	/**
	 * ����ѧ�Ż�ȡ��سɼ�
	 */
	public HashMap<String,String> getStuCjForXh(ZjlgPjpyModel model,String xn,String xh) throws SQLException{
		HashMap<String,String> map = new HashMap<String,String>();		
		model.setXn(xn);
		model.setXh(xh);
		List<HashMap<String,String>> zhcj = dao.getZhczPrintList(model);
		if(zhcj != null){
			if (zhcj.size()>0 ){
				map = zhcj.get(0);
			}
		}
		return map;
	}
	/**
	 * ����ѧ�Ż�ȡ���������
	 */
	public String getCpzdmForXh(String xh,String xn) throws SQLException{		
		return dao.dao_getCpzdmForXh(xh,xn);
	}
	/**
	 * ��ѧ���������
	 */
	public boolean serv_JxjSqCk(String pkVStr,String userType,String check,String shyj,String workFlowName,String checkName) throws Exception{
		return dao.dao_jxjSqCk(pkVStr, userType, check,shyj,workFlowName,checkName);
	}
	/**
	 * ���
	 */
	public boolean serv_audit(String pkVStr,String userType,String check,String shyj,String workFlowName,String checkName,String tableName) throws Exception{
		return dao.dao_audit(pkVStr, userType, check,shyj,workFlowName,checkName,tableName);
	}
	/**
	 * ��ѧ���������--�˻�
	 * author lyl
	 */
	public boolean serv_JxjBack(String pkVStr,String userType,String check,String thly,String workFlowName,String checkName,String tableName) throws Exception{
		return dao.dao_jxjBack(pkVStr, userType, check,thly,workFlowName,checkName,tableName);
	}
	/**
	 * ����׷��
	 * author lyl
	 */
	public ArrayList<String[]> workFlowQuery(String pkVStr,String tableName) throws Exception{
		return dao.dao_workFlowQuery(pkVStr,tableName);
	}
	
	/**
	 * ���ݽ�ѧ��������ȡxh��xn,jxjdm
	 */
	public HashMap<String, String> jxj_getxhxnjxjdm(String pkVStr) throws Exception{
		return dao.jxj_getxhxnjxjdm(pkVStr);
	}

	public boolean rychcsh() throws Exception {
		// �����ƺų�ʼ��
		return dao.rychcsh();
	}

	public boolean rychPlszSave(ZjlgPjpyModel myModel) throws Exception {
		// TODO �Զ����ɷ������
		return dao.rychPlszSave(myModel);
	}

	public boolean saveRychRs(String pk, ZjlgPjpyModel myModel, HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		return dao.saveRychRS(pk,myModel,request);
	}

	public String getRychMe (String bmlb,String bmdm,String rychdm,String xn) throws Exception {
		//��ȡ�����ƺ����� bmlbΪѧԺΪ"xydm",רҵΪ"zydm",�༶Ϊ"bjdm"
		return dao.getRychMe(bmlb, bmdm, rychdm, xn);
	}

	public boolean delCpz(HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		return dao.delCpz(request);
	}
	public String getYycj (String xh,String xn) throws Exception {
		// ��ѧ������ bmlbΪѧԺΪ"xydm",������Ϊ"cpzdm",�༶Ϊ"bjdm"
		return dao.getYycj(xh, xn);
	}
		
	public boolean getJxjMe (String bmlb,String bmdm,String jxjdm,String xn) throws Exception {
		// ��ѧ������ bmlbΪѧԺΪ"xydm",������Ϊ"cpzdm",�༶Ϊ"bjdm"
		return dao.getJxjMe(bmlb, bmdm, jxjdm, xn);
	}
	
	public String getIsbys (String xh) throws Exception {
		// �Ƿ��ҵ��
		return dao.getIsbys(xh);
	}
	public boolean getIsjxjjd (String xh,String xn,String jxjdm,String usertype) throws Exception {
		// ��ѧ�����ж�
		return dao.getIsjxjjd(xh,xn,jxjdm,usertype);
	}
	public ArrayList<HashMap<String, String>> getCfqk (String xh,String xn,String xq) throws Exception {
		// ����
		return dao.getCfqk(xh,xn,xq);
	}
	/**
	 * �����ƺ����������ж�
	 *  @param rychdm �����ƺŴ���
	 * @param xh ѧ��
	 * @param xn ѧ��
	 * @param zyfwdm ��Դ��Χ���루�����飩
	 * @param dycj �����ɼ�
	 * @param zycj �����ɼ�
	 * @param zhcj �ۺϲ����ɼ�
	 * @return
	 * @throws SQLException
	 */
	public boolean rychSqTjPd(String rychdm,String xh,String xn,String zyfwdm,String dycj,String zycj,String zhcj,String tycj) throws SQLException{	
		//������һ���������������false
		String szlx = "rych";
		if(!dao.dyfTj(xn, szlx, rychdm, dycj)){// ������
			return false;
		}else if(!dao.zyfTj(xn,szlx,rychdm,zycj)){//������
			return false;
		}else if(!dao.zhszfTj(xn,szlx,rychdm,zhcj)){//�ۺ����ʲ�����
			return false;
		}else if(!dao.dyfpmTj(xh, xn,szlx, rychdm,zyfwdm)){//�����ɼ�����
			return false;
		}else if(!dao.zyfpmTj(xh, xn,szlx,rychdm,zyfwdm)){//����������
			return false;
		}else if(!dao.zhpmTj(xh, xn, szlx,rychdm,zyfwdm)){//�ۺ����ʲ���������
			return false;
		}
		else if(!dao.tyfTj(xn, szlx, rychdm, tycj)){
			return false;
		}
		else {
			return true;
		}		
	}
	/**
	 * �Ƚ��༶���������ж�
	 * @param bjdm �༶����
	 * @param xn ѧ��
	 * @param wmqsgs �������Ҹ���
	 * @param ajqsgs A�����Ҹ���
	 * @return
	 */
	public boolean xjbjSqTjPd(String bjdm,String xn,String wmqsgs,String ajqsgs,String sfyxbj ){
//		������һ���������������false
		String szlx = "xjbj";
		if(!dao.bjpjfTj(xn, szlx,"", bjdm)){//�༶ƽ���ɼ�
			return false;
		}else if(!dao.bjglTj(xn,szlx,"", bjdm)){//�༶��������
			return false;
		}else if(!dao.wmqsTj(xn,szlx, "",wmqsgs)){ //�������Ҹ�������
			return false;
		}else if(!dao.ajqsgsTj(xn,szlx,"", ajqsgs)){//A�����Ҹ���
			return false;
		}else if(!dao.sfyxbjTj(xn, szlx, "", sfyxbj)){//
		    return false;
		}else{
			return true;
		}
	}
//	public boolean rychCkRsPd(String xh,String){

//	}
	/**
	 * �Ƚ��༶��˰༶���޶�
	 * @param xn
	 * @param xydm
	 * @return
	 */
	public boolean xjbjChBjsPd(String xn,String xydm,String pkVStr){
		return dao.xjbjBjsXd(xn, xydm,pkVStr);
	}
	/**��ȡ�༶ƽ����
	 * @author luo
	 */
	public String serv_getbjpjf(String xn,String bjdm ){
		return dao.getbjpjf(xn, bjdm);
	}
	/**��ȡ�༶��������
	 * @author luo
	 */
	public String serv_getbjbjdl(String xn,String bjdm ){
		return dao.getbjbjdl(xn, bjdm);
	}
	public boolean serv_rychCkRsXd(String rychdm,String xn,String xydm,String shfs,String zydm,String bjdm,String pkVStr) throws Exception{
		return dao.rychCkRsXd(rychdm, xn, xydm, shfs, zydm, bjdm, pkVStr);
	}
	/**
	 * ��ȡ�����ƺű���·��
	 */
	public HashMap<String,String> getRychBb(String rychdm){
		return dao.dao_getRychBb(rychdm);
	}
	/**
	 * �Ƿ�����ۺϲ�����
	 */
	public boolean dao_jxjiscftj(String rychdm){
		return dao.dao_getiszhcpf(rychdm);
	}
	/**
	 * @author luo
	 * @throws Exception 
	 * @describe ��ѧ����ܱ����ӡ
	 */
	public void jxjhzPrint(WritableWorkbook wwb, String xn,String zydm,String bjdm,String xnIn, String xyIn,
			String zyIn, String bjIn,String cpzdm,String xh,String xm,String jxjdm) throws Exception {

		String xxmc = StandardOperation.getXxmc();
		ZjlgPjpyModel myModel = new ZjlgPjpyModel();
		myModel.setXn(xn);
		myModel.setZydm(zydm);
		myModel.setBjdm(bjdm);
		String xsxn = (Base.isNull(xnIn))?"%":xnIn;
		String xy = (Base.isNull(xyIn))?"%":xyIn;
		String zy = (Base.isNull(zyIn))?"%":zyIn;
		String bj = (Base.isNull(bjIn))?"%":bjIn;
		String cpz = (Base.isNull(cpzdm)?"%":cpzdm);
//		xnIn = (Base.isNull(xnIn))?"'%'":xnIn;
//		xyIn = (Base.isNull(xyIn))?"'%'":xyIn;
//		zyIn = (Base.isNull(zyIn))?"'%'":zyIn;
//		bjIn = (Base.isNull(bjIn))?"'%'":bjIn;
		List<HashMap<String, String>> list = dao.getBbdyList(xsxn,xy,zy,bj,cpz,xh,xm,jxjdm);
		//boolean bool = dao.createZXFdytjb(xnIn, xyIn, zyIn, bjIn);
		String systime = getSysTime();
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

			ws.addCell(new Label(0, 0, xxmc  + xn + "ѧ���ۺϲ������ܱ�",
					wcfTytle));

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.RIGHT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.RIGHT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);
			
			ws.addCell(new Label(0, 2, systime,
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

			for (int i = 0; i < list.size(); i++) {

				HashMap<String, String> map = new HashMap<String, String>();
				map = (HashMap<String, String>) list.get(i);

				ws.addCell(new Label(0, 5 + rownum, map.get("pm"), wcfTytle));
				ws.addCell(new Label(1, 5 + rownum, map.get("xh"), wcfTytle));
				ws.addCell(new Label(2, 5 + rownum, map.get("bjmc"), wcfTytle));
				ws.addCell(new Label(3, 5 + rownum, map.get("xm"), wcfTytle));
				ws.addCell(new Label(4, 5 + rownum, map.get("dycpf"), wcfTytle));
				ws.addCell(new Label(5, 5 + rownum, map.get("dypm"), wcfTytle));
				ws.addCell(new Label(6, 5 + rownum, map.get("zycpf"), wcfTytle));
				ws.addCell(new Label(7, 5 + rownum, map.get("zypm"), wcfTytle));
				ws.addCell(new Label(8, 5 + rownum, map.get("bjg"), wcfTytle));
				ws.addCell(new Label(9, 5 + rownum, map.get("zhcpf"), wcfTytle));
				ws.addCell(new Label(10, 5 + rownum, map.get("jxjmc"), wcfTytle));
				ws.addCell(new Label(11, 5 + rownum, map.get("jlje"), wcfTytle));
				ws.addCell(new Label(12, 5 + rownum, map.get("rych"), wcfTytle));
				ws.addCell(new Label(13, 5 + rownum, map.get("tycj"), wcfTytle));
				ws.addCell(new Label(14, 5 + rownum, map.get("yycj"), wcfTytle));
				ws.addCell(new Label(15, 5 + rownum, map.get("jsjcj"), wcfTytle));
				ws.addCell(new Label(16, 5 + rownum, map.get("wjcf"), wcfTytle));
				ws.addCell(new Label(17, 5 + rownum, map.get(""), wcfTytle));
				
				rownum++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * ��ȡĳ��������ѧ���б�
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>> serv_getRhJxjList(String xh){
		return dao.getRhJxjList(xh);
	} 
	/**
	 * ��ȡ�����ƺ���ͨ������
	 */
	public String serv_getRychPssRs(String rychdm,String xn,String xydm,String zydm,String bjdm){		
		return dao.getRychPssRs(rychdm,xn,xydm,zydm,bjdm);
	}
	
	/**
	 * ��������������֤(��������)
	 * @param myModel 
	 */
	public String getJxjRstxPlsz(ZjlgPjpyModel myModel){
		String xydm = myModel.getXydm();
		String cpzdm = myModel.getCpzdm();
		String bjdm = myModel.getBjdm();
		String jxjdm = myModel.getJxjdm();
		String xn =  Base.getJxjsqxn();
		if(bjdm!=null&&!bjdm.equalsIgnoreCase("")){
			return dao.getJxjRstxForXydm(xydm, xn, jxjdm, "bjdm");
		}else if(cpzdm!=null&&!cpzdm.equalsIgnoreCase("")){
			return dao.getJxjRstxForXydm(xydm, xn, jxjdm, "cpzdm");
		}else{
			return null;
		}
	}
	
	/**
	 * ��������������֤(��������)
	 * @param myModel 
	 */
	public String getJxjRstxOne(ZjlgPjpyModel myModel){		
		String xydm = myModel.getXydm();
		String bmlb = myModel.getBmlb();
		String jxjdm = myModel.getJxjdm();
		String xn =  Base.getJxjsqxn();
		if(!bmlb.equalsIgnoreCase("xydm")){
			return dao.getJxjRstxForXydm(xydm, xn, jxjdm, bmlb);
		}else{
			return null;
		}
	}

	
	public List<HashMap<String, String>> getPjfsList() {
		return dao.getPjfsList();
	}

	public boolean updatePjfs(ZjlgPjpyModel myModel) throws Exception {
		return dao.updatePjfs(myModel);
	}
	 /**
     * ͨ��ѧ�š�ѧ��ȡ��������ɼ�
     * @param 
     * @return
     */
	public String serv_getTyCj(String xh,String xn){
		return dao.dao_getTyCj(xh, xn);
	}
	public String isExistDj(List<HashMap<String,String>> listMap,String str){
		  for (HashMap<String, String> hashMap : listMap) {
			       if(hashMap.get("djksmc").equals(str)){
			    	   return hashMap.get("cj");
			       }
		   }
		  return null;
	}
	/*
	 * ����ѧ�Ż�ȡ ������ɼ���Ӣ��ȼ��ɼ�
	 */
	public HashMap<String,String> getStuDjForXh(String xn,String xh) throws SQLException{
		HashMap<String,String> map = new HashMap<String,String>();		
		List<HashMap<String,String>> JsjYyDjCj = dao.getJsjYyDjList(xn,xh);
		if(isExistDj(JsjYyDjCj, "CET6")!=null){
			  map.put("yydjcj6", ("����"+isExistDj(JsjYyDjCj, "CET6")));
		}
		else if(isExistDj(JsjYyDjCj, "CET4")!=null){
			  map.put("yydjcj4", ("����"+isExistDj(JsjYyDjCj, "CET6")));
		}
		if(isExistDj(JsjYyDjCj, "�ļ�")!=null){
			map.put("jsj4", ("�ļ�"+isExistDj(JsjYyDjCj, "�ļ�")));
		}
		else if(isExistDj(JsjYyDjCj, "����")!=null){
			map.put("jsj3", ("����"+isExistDj(JsjYyDjCj, "����")));
		}
		else if(isExistDj(JsjYyDjCj, "����")!=null){
			map.put("jsj2", ("����"+isExistDj(JsjYyDjCj, "����")));
		}
		else if(isExistDj(JsjYyDjCj, "һ��")!=null){
			map.put("jsj1", ("һ��"+isExistDj(JsjYyDjCj, "һ��")));
		}
		if(map.get("yydjcj6")!=null){
			map.put("yydjcj", map.get("yydjcj6"));
		}else{
			map.put("yydjcj", map.get("yydjcj4"));
		}
		if(map.get("jsj4")!=null){
			map.put("jsjdjcj", map.get("jsj4"));
		}
		else if(map.get("jsj3")!=null){
			map.put("jsjdjcj", map.get("jsj3"));
		}
		else if(map.get("jsj2")!=null){
			map.put("jsjdjcj", map.get("jsj2"));
		}
		else if(map.get("jsj1")!=null){
			map.put("jsjdjcj", map.get("jsj1"));
		}
		return map;
	}
	/**�㽭������ȡzjlg_zpf
     * ͨ��ѧ�š�ѧ��ȡ��ص����ɼ�
     * @param 
     * @return
     */
	public String serv_getZjlgDyCj(String xh,String xn){
		return dao.dao_getZjlgDyCj(xh, xn);
	}
	
	/**
     * ��ȡѧԺ�����������뽱ѧ������
     * @param 
     * @return
     */
	public HashMap<String,String> getJxjSqQk(String bmlb,String xydm,String bmdm,String jxjdm,String xn){
		List<HashMap<String,String>>jxjSqqkList=dao.getJxjSqQk(bmlb, xydm, bmdm, jxjdm, xn);
		
		HashMap<String,String>sqrsMap=new HashMap<String,String>();
		for(int i=0;i<jxjSqqkList.size();i++){
			HashMap<String,String>jxjSqMap=jxjSqqkList.get(i);
			if(i==0){
				sqrsMap.put("xyjxjrs", jxjSqMap.get("jxjrs"));
				sqrsMap.put("xysqrs", jxjSqMap.get("sqrs"));
				sqrsMap.put("xyksqrs", jxjSqMap.get("ksqrs"));
			}else{
				sqrsMap.put("cpzjxjrs", jxjSqMap.get("jxjrs"));
				sqrsMap.put("cpzsqrs", jxjSqMap.get("sqrs"));
				sqrsMap.put("cpzksqrs", jxjSqMap.get("ksqrs"));
			}
		}
		return sqrsMap;
	}
}
