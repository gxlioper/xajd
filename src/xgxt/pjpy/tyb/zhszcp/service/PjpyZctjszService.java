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
	 * ��ѯ�۲���������ͷ
	 * @return
	 */
	public List<HashMap<String, String>> queryEjTitle() {
		return dao.queryEjTitle();
	}
	
	/**
	 * ͨ���۲���������ѯ�۲���Ϣ
	 * @param dm
	 * @return
	 */
	public HashMap<String, String> queryZcejdmxx(String dm) {
		return dao.queryZcejdmxx(dm);
	}

	/**
	 * ����2���۲��������������Ϣ ��ɾ��������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean save2jZhszcpdmxx(PjpyZctjszModel model) throws Exception{
		return dao.save2jZhszcpdmxx(model);
	}
	
	/**
	 * ͨ�����������ѯ�����Ӵ�����Ϣ ��ƴ�ӳ��ַ���
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
	 * ͨ�����뼶���ѯ����������Ϣ
	 * @param dmjb ��Ϊ1��2��3��4��
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> queryZcsjdmxx(String dmjb, boolean isSfwh) throws Exception{
		return dao.queryZcsjdmxx(dmjb, isSfwh);
	}
	
	/**
	 * ͨ���������ʹ����ѯ�۲������Ϣ
	 * @param dmlb
	 * @param dm
	 * @return
	 */
	public List<HashMap<String, String>> queryZcDmxxList(String dm) {
		return dao.queryZcdmxxList(dm);
	}
	
	/**
	 * ͨ��������ͱ�����ѯ��˼���
	 * 
	 * @param dm
	 * @param tableName
	 * @returnString
	 */
	public String queryZhszcpxmShjb(String fdm, String tableName){
		return dao.queryZhszcpxmShjb(fdm, tableName);
	}
	
	/**
	 * ͨ��������ѯ������ϸ��Ϣ
	 * @param tableName
	 * @return
	 */
	public List<HashMap<String, String>> queryZhszcpxm(String tableName) {
		return dao.queryZhszcpxm(tableName);
	}	
	
	/**
	 * �޸ĸ������������˼�����Ϣ
	 * @param dmArray
	 * @param bmArray
	 * @param fdmArray
	 * @return
	 */
	public boolean updateFdmbmAndShjb(PjpyZhszcpModel model) {
		return dao.updateFdmbmAndShjb(model);
	}
	
	/**
	 * �����۲����������
	 * @param wwb
	 * @throws Exception 
	 */
	public void expTjjcResult(WritableWorkbook wwb) throws Exception {
		//��ȡ��һ��Ҫ�����ĵ�Ԫ��
		WritableSheet ws = wwb.createSheet("�۲��������ü����", 0);
		//��ѯ�������
		List<String[]> rs = dao.queryZctjjcResult();
		
		// ��ȡҪ�������ݵ���ʽ
		WritableCellFormat format = ExcelMB.getWritableCellFormat(12, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, Border.ALL);
		
		//д���ͷ��EXCEL��
		String[] titleArray = {"�����������", "�۲���Ŀ����", "�۲���Ŀ����", "���ñ���", "�����ֶ�", "�ϼ���Ŀ����", "�Ƿ�����ά��", "��˼���", "����"};
		List<String[]> titleList = new ArrayList<String[]>();
		titleList.add(titleArray);
		ExcelMB.writeDataToCellByIterator(ws, titleList, 0, format);
		
		// ����������д��EXCEL��
		ExcelMB.writeDataToCellByIterator(ws, rs, 1, format);
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ִ���۲������������
	 * @return
	 * @throws Exception
	 */
	public void zctjSjCheckResult(WritableWorkbook wwb) throws Exception{
		
		//ִ�м�����
		boolean result = dao.zctjSjCheckResult();
		
		if (result) {//���ɹ�,����������
			expTjjcResult(wwb);
		} else {//���ʧ��,����ʧ����Ϣ�����
			Map<String, String> map = new HashMap<String, String>(){};
			map.put("msg",
					"�۲��������ü������г�������,�����Ǵ洢����(PROC_PJPY_ZCTJPZJC)����ʱ������.....");
			result = dao.insertSjjcErrorMsg(map);
			if (result) {
				expTjjcResult(wwb);
			}
		}
		
		//���ɾ�����еļ��������
		dao.deleteAllZcsjjcResult();
	}
	
	/**
	 * ��ѯ����Ҫ��������ά���Ĵ�����Ϣ
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
	 * �޸Ķ����۲������Ϣ
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
	 * �޸��ļ��۲������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean update4jZcdmxx(PjpyZctjszModel model) throws Exception{
		return dao.update4jZcdmxx(model);
	}
	
	/**
	 * �޸��۲������Ϣ(2,3,4��)
	 * @param type
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean updateZcdmxx(String type, PjpyZctjszModel model) throws Exception{
		boolean result = false;
		if ("2".equalsIgnoreCase(type)) {//�������
			result = update2jZcdmxx(model);
		} else if ("3".equalsIgnoreCase(type)) {//��������
			result = update3jZcdmxx(model);
		} else if ("4".equalsIgnoreCase(type)) {//�����ļ�
			result = update4jZcdmxx(model);
		}
		return result;
	}
	
}
