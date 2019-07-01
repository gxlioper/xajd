package xgxt.pjpy.zjlg.dycj;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.ExcelMethods;
import xgxt.utils.UserTypePd;
import xgxt.utils.date.DateUtils;


public class DycjService {

	DycjDAO dao = new DycjDAO();
	
	/**
	 * ƽʱ�ֲ�ѯ���
	 * @param model
	 * @param whereSql
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryPsfResult(DycjModel model,
			String isFdy, String userName) throws Exception {
		String fdySql = getFdySql(isFdy, userName);
		return dao.queryPsfResult(model, fdySql);
	}
	
	/**
	 * �����ֲ�ѯ���
	 * @param model
	 * @param isFdy
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryWsfResult(DycjModel model,
			String isFdy, String userName) throws Exception {
		String fdySql = getFdySql(isFdy, userName);
		return dao.queryWsfResult(model, fdySql);
	}
	
	/**
	 * ���ڷֲ�ѯ���
	 * @param model
	 * @param isFdy
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryKqfResult(DycjModel model,
			String isFdy, String userName) throws Exception {
		String fdySql = getFdySql(isFdy, userName);
		return dao.queryKqfResult(model, fdySql);
	}
	
	/**
	 * �����ֲܷ�ѯ���
	 * @param model
	 * @param isFdy
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryDyzfResult(DycjModel model,
			String isFdy, String userName) throws Exception {
		String fdySql = getFdySql(isFdy, userName);
		return dao.queryDyfResult(model, fdySql);
	}
	
	/**
	 * �����ֲܷ�ѯ���
	 * @param model
	 * @param isFdy
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryZjlgDyzfResult(DycjModel model,
			String isFdy, String userName) throws Exception {
		String fdySql = getFdySql(isFdy, userName);
		return dao.queryZjlgDyfResult(model, fdySql);
	}

	private String getFdySql(String isFdy, String userName) {
		String fdySql = UserTypePd.isFdy(isFdy) ? " and exists(select 1 from fdybjb c "
				+ "where a.bjdm=c.bjdm and c.zgh='"
				+ userName
				+ "')"
				: "" ;
		return fdySql;
	}
	
	/**
	 * ƽʱ�ֲ�ѯ��ͷ
	 * @return
	 */
	public List<HashMap<String, String>> queryPsfTitle() {
		String[] en = { "pk","dis",
				"r", "xh", "xm", "bjmc", "xn", "zwpyf", "bjpyf", "xyfjf",
				"xysh"};
		String[] cn = { "pk","dis",
				"�к�", "ѧ��", "����", "�༶", "ѧ��", "���������", "�༶�����", "ѧԺ���ӷ�",
				"ѧԺ���"};
		DAO dao = DAO.getInstance();
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * �����ֲ�ѯ��ͷ
	 * @return
	 */
	public List<HashMap<String, String>> queryWsfTitle() {
		String[] en = {  "pk","dis",
				"r", "xh", "xm", "bjmc", "xn", "qsf", "iszds", "xyfjf",
				"xysh" };
		String[] cn = { "pk","dis",
				"�к�", "ѧ��", "����", "�༶", "ѧ��", "����ƽʱ��", "�Ƿ��߶���", "ѧԺ���ӷ�",
				"ѧԺ���"};
		DAO dao = DAO.getInstance();
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * ���ڷֲ�ѯ��ͷ
	 * @return
	 */
	public List<HashMap<String, String>> queryKqfTitle() {
		String[] en = {  "pk",
				"r", "xh", "xm", "bjmc", "xn", "kqf"};
		String[] cn = { "pk",
				"�к�", "ѧ��", "����", "�༶", "ѧ��", "���ڷ�"};
		DAO dao = DAO.getInstance();
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * �����ֲܷ�ѯ��ͷ
	 * @return
	 */
	public List<HashMap<String, String>> queryDyzfTitle() {
		String[] en = {   "pk",
				"r", "xh", "xm", "bjmc", "xn", "psfzf", "wsfzf", "iszds", "kqzpf", "zf"};
		String[] cn = {  "pk",
				"�к�", "ѧ��", "����", "�༶", "ѧ��", "ƽʱ���ܷ�", "�������ܷ�", "�Ƿ��߶���", "���ڷ��ܷ�", "�����ܷ�"};
		DAO dao = DAO.getInstance();
		return dao.arrayToList(en, cn);
	}
	/**
	 * �㽭�������ֲܷ�ѯ��ͷ
	 * @return
	 */
	public List<HashMap<String, String>> queryZjlgDyzfTitle() {
		String[] en = {   "pk",
				"r", "xh", "xm", "bjmc", "xn","psfzf", "wsfzf", "iszds", "kqzpf","zpf"};
		String[] cn = {  "pk",
				"�к�", "ѧ��", "����", "�༶", "ѧ��", "ƽʱ���ܷ�", "�������ܷ�", "�Ƿ��߶���", "���ڷ��ܷ�", "�����ܷ�"};
		DAO dao = DAO.getInstance();
		return dao.arrayToList(en, cn);
	}
	/**
	 * ����ƽʱ�ֱ���������Ϣ
	 * @param userName
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean savePsfblxx(String userName, DycjModel model)
			throws Exception {
		return dao.savePsfblxx(userName, model);
	}
	
	/**
	 * �����ۺ����ʲ����ܷ�
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean countZhszcpf(DycjModel model) throws Exception {
		if(StringUtil.isNull(model.getQueryequals_nj())){
			model.setQueryequals_nj("all");
		}
		if(StringUtil.isNull(model.getQueryequals_xydm())){
			model.setQueryequals_xydm("all");
		}
		if(StringUtil.isNull(model.getQueryequals_zydm())){
			model.setQueryequals_zydm("all");
		}
		if(StringUtil.isNull(model.getQueryequals_bjdm())){
			model.setQueryequals_bjdm("all");
		}
		
		return dao.countZhszcpf(model);
	}
	
	/**
	 * �����۲�������Ϣ
	 * @param wwb
	 * @param model
	 * @param isFdy
	 * @param userName
	 * @throws Exception
	 */
	public void exprotZhszcpinfo(WritableWorkbook wwb, DycjModel model,
			String isFdy, String userName) throws Exception {
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

		ws.addCell(new Label(0, 0, Base.xxmc  + model.getXn() + "ѧ���ۺϲ������ܱ�",
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

		ws.addCell(new Label(0, 2, DateUtils.getLyr(),
				wcfTytle));
		
		/* ��ѯ���������Ϣ */
		List<String[]> rs = queryZhszcppmxx(model, isFdy, userName);
		
		/* �����ѯ�����Ϣ */
		if (!rs.isEmpty()) {
			WritableCellFormat format = ExcelMB.getWritableCellFormat(11,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);
			ExcelMB.writeDataToCellByIterator(ws, rs, 0, 5, format);
		}
		
		//��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ��ѯ�ۺ�������Ϣ
	 * @param model
	 * @param whereSql
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryZhszcppmxx(DycjModel model, String isFdy, String userName) throws Exception {
		String fdySql = getFdySql(isFdy, userName);
		return dao.queryZhszcppmxx(model, fdySql);
	}
}
