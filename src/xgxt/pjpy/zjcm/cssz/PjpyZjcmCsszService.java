package xgxt.pjpy.zjcm.cssz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.pjpy.guizdx.PjpyGuizdxService;
import xgxt.pjpy.guizhdx.GuizhdxDAO;
import xgxt.pjpy.tyb.zhszcp.service.PjpyZhcpjxjService;
import xgxt.utils.Arrays2;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

import common.Globals;
import common.GlobalsVariable;

public class PjpyZjcmCsszService {

	PjpyZjcmCsszDAO dao = new PjpyZjcmCsszDAO();
	
	/**
	 * ����Ӣ,�������鷵���б�
	 * @param en
	 * @param cn
	 * @return
	 */
	public List<HashMap<String, String>> getList(String[] en, String[] cn) {
		if (en != null && cn != null && en.length == cn.length) {
			List<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
			for (int i = 0; i < en.length; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("dm", en[i]);
				map.put("mc", cn[i]);
				rs.add(map);
			}
			return rs;
		} else {
			return null;
		}
	}
	
	/**
	 * ������Χ�б�
	 * hasNullOption �Ƿ��п�ѡ��
	 * @return
	 */
	public List<HashMap<String, String>> getCpfwList(boolean hasNullOption) {
		String[] en = {"xy", "zy", "bj"};
		String[] cn = {"ѧԺ", "רҵ", "�༶"};
		if(Globals.XXDM_NTZYDX.equalsIgnoreCase(Base.xxdm)){
			en = new String[]{"xy"};
			cn = new String[]{"ѧԺ"};
		}
		List<HashMap<String, String>> rs = getNullOptionList(hasNullOption);
		rs.addAll(getList(en, cn));
		return rs;
	}
	
	/**
	 * ��ѧ��,�����ƺ��б�
	 * @return
	 */
	public List<HashMap<String, String>> getJxjRychlbList(boolean hasNullOption) {
		String[] en = { GlobalsVariable.PJPY_JXJ, GlobalsVariable.PJPY_RYCH };
		String[] cn = { "��ѧ��", "�����ƺ�" };
		List<HashMap<String, String>> rs = getNullOptionList(hasNullOption);
		rs.addAll(getList(en, cn));
		return rs;
	}
	
	/**
	 * ��ѡ���б�
	 * @param hasNullOption
	 * @return
	 */
	public List<HashMap<String, String>> getNullOptionList(boolean hasNullOption) {
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		if (hasNullOption) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dm", "");
			map.put("mc", "");
			rs.add(map);
		}
		return rs;
	}
	
	/**
	 * ��ѧ��,�����ƺ��б�
	 * 
	 * @param key
	 * @param jxjlb
	 * @return
	 */
	public List<HashMap<String, String>> getDmList(String key, String jxjlb) {		
		if (GlobalsVariable.PJPY_JXJ.equalsIgnoreCase(key)) {
			return getJxjList(true, jxjlb);
		}else if(GlobalsVariable.PJPY_ZHCPJXJ.equalsIgnoreCase(key)){
			//�ۺϲ�����ѧ��
			DAO dao = DAO.getInstance();
			return dao.getWhList("zhcpjxjdmb", "jxjdm", "jxjmc", "", "", "");
		}else {
			return getRychList(true);
		}
	}
	
	/**
	 * ��ѧ������б�
	 * @return
	 */
	public List<HashMap<String, String>> getJxjlbList(boolean hasNullOption) {
		List<HashMap<String, String>> rs = getNullOptionList(hasNullOption);
		rs.addAll(dao.getJxjlbList());
		return rs;
	}
	
	/**
	 * ͨ������ѯ��ѧ������б�
	 * @param hasNullOption
	 * @param jxjlb
	 * @return
	 */
	public List<HashMap<String, String>> getJxjList(boolean hasNullOption, String jxjlb) {
		List<HashMap<String, String>> rs = getNullOptionList(hasNullOption);
		rs.addAll(dao.getJxjList(jxjlb));
		return rs;
	}
	
	/**
	 * ��ѯ�����ƺ��б�
	 * @param hasNullOption
	 * @return
	 */
	public List<HashMap<String, String>> getRychList(boolean hasNullOption) {
		List<HashMap<String, String>> rs = getNullOptionList(hasNullOption);
		rs.addAll(dao.getRychList());
		return rs;
	}
	
	/**
	 * ��ѯ��ѧ��������ͷ��Ϣ
	 * @return
	 */
	public List<HashMap<String, String>> queryJxjrsTitle(String lb) {
		String[] en = {"pk", "r", "xn", "nd", "xq", "bmmc",
				"nj", "jxjlbmc", "mc", "cprs", "jxjbl", "jsrs","jxjrs"};
		String[] cn = {"pk", "�к�", "ѧ��", "���", "ѧ��", "��������",
				"�꼶", "��ѧ�����", "��ѧ��", "��������", "����(%)", "��������","��������"};
		if (GlobalsVariable.PJPY_RYCH.equalsIgnoreCase(lb)) {
			en = new String[]{"pk", "r", "xn", "nd", "xq", "bmmc",
					"nj", "mc", "cprs", "jxjbl", "jsrs","jxjrs"};
			cn = new String[]{"pk", "�к�", "ѧ��", "���", "ѧ��", "��������",
					"�꼶", "�����ƺ�", "��������", "����(%)", "��������","��������"};
		}else if (GlobalsVariable.PJPY_ZHCPJXJ.equalsIgnoreCase(lb)) {
			en = new String[]{"pk", "r", "xn", "nd", "xq", "bmmc",
					"nj", "mc", "cprs", "jxjbl", "jsrs","jxjrs"};
			cn = new String[]{"pk", "�к�", "ѧ��", "���", "ѧ��", "��������",
					"�꼶", "��ѧ��", "��������", "����(%)", "��������","��������"};
		}
		DAO dao = DAO.getInstance();
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * ��ѯ��ѧ��������ѯ���
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryJxjrsResult(PjpyZjcmCsszModel model, String lb)
			throws Exception {
		String[] colList = new String[] { "pk", "r", "xn", "nd", "xqmc", "bmmc",
				"nj", "jxjlbmc", "mc", "cprs", "jxjbl", "jsrs","jxjrs" };
		if (GlobalsVariable.PJPY_RYCH.equalsIgnoreCase(lb)) {
			colList = new String[] { "pk", "r", "xn", "nd", "xqmc", "bmmc",
					"nj", "mc", "cprs", "jxjbl", "jsrs","jxjrs" };
		}
		if(GlobalsVariable.PJPY_ZHCPJXJ.equalsIgnoreCase(lb)){
			colList = new String[] { "pk", "r", "xn", "nd", "xqmc", "bmmc",
					"nj", "mc", "cprs", "jxjbl", "jsrs","jxjrs" };
		}
		return dao.queryJxjrsResult(model,colList);
	}
	
	/**
	 * ��ʼ����������
	 * @param lb
	 * @return
	 * @throws Exception
	 */
	public boolean baseDataInit(String lb) throws Exception{
		boolean flag = false;
		if(Globals.XXDM_NTZYDX.equalsIgnoreCase(Base.xxdm)){
			flag = dao.baseZhcpjxjDataInit(lb);
		}else{
			flag = dao.baseDataInit(lb);
		}
		return flag; 
	}
	
	/**
	 * ��ѧ������������������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean updateJxjrs(PjpyZjcmCsszModel model) throws Exception{
		return dao.updateJxjrs(model);
	}
	
	/**
	 * ��ѯ������ѧ��������Ϣ
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> queryDgJxjrsxx(String pkValue) {
		return dao.queryDgJxjrsxx(pkValue);
	}
	
	/**
	 * �޸Ľ�ѧ�𵥸�����������Ϣ
	 * @param pkValue
	 * @param jxjrs
	 * @return
	 * @throws Exception
	 */
	public boolean updateJxjDgtzrs(String pkValue, String jxjrs) throws Exception{
		return dao.updateJxjDgtzrs(pkValue, jxjrs);
	}
	
	/**
	 * ������ѧ��������������
	 * @param wwb
	 * @param model
	 */
	public void exportJxjblFpb(WritableWorkbook wwb, PjpyZjcmCsszModel model)
			throws Exception {
		List<String[]> rs = dao.queryJxjRychBlRs(model);
		WritableSheet ws = wwb.createSheet(model.getXymc() + "��ѧ�����߱���", 0);
		
		//��ȡ��ͷ��ʽ
		WritableCellFormat titleFormat = ExcelMB.getWritableCellFormat(15, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, Border.ALL);
		
		WritableCellFormat ejtitleFormat = ExcelMB.getWritableCellFormat(12, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, Border.ALL);
		

		//�����һ����ͷ
		ExcelMB.writeTitleToCell(ws, getJxjblFpbTitle(model), 0, 0, 9, 2, titleFormat);
		
		//����ڶ�����ͷ
		ExcelMB.writeEjTitleToCell(ws, getJxjblFpbEjTitle(model), 0, 3,
				ejtitleFormat);
		
		//д�����ݵ���Ԫ��
		writeResultDataToCell(rs, ws, ExcelMB.getWritableCellFormat(10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, Border.ALL));
		
		//���ѧԺ����
		ws.addCell(new Label(0, 4, model.getXymc(),ejtitleFormat));
		
		//�������õ�1��2��3�е�Ԫ���б���
		ws.setColumnView(0, 15);
		ws.setColumnView(1, 20);
		ws.setColumnView(2, 10);
		
		//�ϲ�ѧԺ���Ÿɣ��Ÿ�ѧ����Ԫ��
		int height = rs.isEmpty() ? 4 : rs.size();
		ws.mergeCells(0, 4, 0, 4 + height);
		ws.mergeCells(8, 4, 8, 4 + height);
		ws.mergeCells(9, 4, 9, 4 + height);
		
		ws.addCell(new Label(1,4 + height, "�ϼ�", ejtitleFormat));
		
		//�Ÿ��������
		String yhrs = dao.queryYgblRs(model);
		ws.addCell(new Label(8, 4, yhrs, ejtitleFormat));
		
		//����ϼ�������
		ExcelMB.writeDataToCellByIterator(ws, countFpbzrs(rs), 2, 4+height, ejtitleFormat);
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	//��ѧ����������һ����ͷ
	private String getJxjblFpbTitle(PjpyZjcmCsszModel model) {
		StringBuffer title = new StringBuffer(Base.xxmc);
		title.append(model.getXn());
		title.append("ѧ���");
		title.append(model.getXq());
		title.append("ѧ��ȫУ���ཱѧ�����߱���");
		return title.toString();
	}
	
	/**
	 * ѭ��д�����ݵ���Ԫ����
	 * @param rs
	 * @param ws
	 * @param format
	 */
	private void writeResultDataToCell(List<String[]> rs, WritableSheet ws,
			WritableCellFormat format) {
		if (!rs.isEmpty()) {
			try {
				ExcelMB.writeDataToCellByIterator(ws, rs, 1, 4, format);
			} catch (Exception e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}
	}
	
	//��ѧ���������������ͷ
	private String[] getJxjblFpbEjTitle(PjpyZjcmCsszModel model) {
		String[] titleArray = new String[] { "ѧԺ", "�༶", "����", "һ�Ƚ�", "���Ƚ�",
				"���Ƚ�", "��Ṥ�����㽱", "����ѧ��", "����ѧ���ɲ�", "����ѧ���ɲ�(����ѧ��)25%" };
		String[] rs = dao.getJxjRychBlTitle(model);
		if (rs != null && rs.length > 0) {
			titleArray[3] += StringUtils.isNotNull(rs[0]) ? rs[0] : "";
			titleArray[4] += StringUtils.isNotNull(rs[1]) ? rs[1] : "";
			titleArray[5] += StringUtils.isNotNull(rs[2]) ? rs[2] : "";
			titleArray[6] += StringUtils.isNotNull(rs[3]) ? rs[3] : "";
			titleArray[7] += StringUtils.isNotNull(rs[4]) ? rs[4] : "";
			titleArray[8] += StringUtils.isNotNull(rs[5]) ? rs[5] : "";
		}
		return titleArray;
	}
	
	/**
	 * �ϼƷ����������
	 * @param rs
	 * @return
	 */
	public List<String[]> countFpbzrs(List<String[]> rs) {
		int[] data = {0, 0, 0, 0, 0, 0};
		if (!rs.isEmpty()) {
			for (int i=0;i<rs.size();i++) {
				String[] oneData = rs.get(i);
				if (oneData != null && oneData.length == 7) {
					for (int k=0;k<data.length;k++) {
						data[k] += StringUtils.isNull(oneData[k+1]) ? 0 : Integer
								.parseInt(oneData[k+1]);
					}
				}
			}
		}
		List<String[]> result = new ArrayList<String[]>();
		result.add(Arrays2.intArrToStrArr(data));
		return result;
	}
}
