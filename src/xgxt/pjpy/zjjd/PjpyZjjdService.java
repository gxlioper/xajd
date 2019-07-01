
package xgxt.pjpy.zjjd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.pjpy.commonutils.PjpyCommonDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �㽭������������Service
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-08-05</p>
 */
public class PjpyZjjdService {
	
	PjpyZjjdDAO dao = null;//���ݲ�����
	
	/**
	 * ͨ��ѧ�ڻ�ȡ�����б�
	 * @return
	 */
	public List<HashMap<String, String>> getYfList(String xq) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getYfList(xq);
	}
	
	/**
	 * У԰���ֲַ�ѯ���
	 * @param xybxfModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXybxfResult(XybxfModel xybxfModel) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getXybxfResult(xybxfModel);
	}
	
	/**
	 * У԰���ֲַ�ѯ��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXybxfTitle() throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getXybxfTitle();
	}
	
	/**
	 * ͨ��ѧ�Ż�ȡ���������Ϣ
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuInfo(String xh) throws Exception {
		PjpyCommonDAO commonDao = new PjpyCommonDAO();
		return commonDao.getStuInfo(xh);
	}
	
	/**
	 * У԰���ֵַĵ�������,��ɾ�������
	 * @param xybxfModel
	 * @return 
	 * @throws Exception
	 */
	public boolean xybxfSave(XybxfModel xybxfModel, HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.xybxfSave(xybxfModel, request);
	}
	
	/**
	 * ͨ��������ȡѧ��У԰���ַ���Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXybxfInfo(String pkValue) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getXybxfInfo(pkValue);
	}
	
	/**
	 * У԰���ֵַĵ����޸ķ�Ϊ�޸�������δ�޸�����
	 * @param xybxfModel
	 * @param pkValue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean xybxfModi(XybxfModel xybxfModel, String pkValue,
			HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.xybxfModi(xybxfModel, pkValue, request);
	}
	
	/**
	 * У԰���ֵַ�����ɾ��
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String xybxfDel(String[] keys, HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.xybxfDel(keys, request);
	}
	
	/**
	 * У԰���ַ���˽��ѧԺ��ѧУ���
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String xybxfSh(String[] keys, String sJg, String userType,
			HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		sJg = StringUtils.isEqual(sJg, "tg") ? "ͨ��" : (StringUtils.isEqual(sJg,
				"btg") ? "��ͨ��" : "δ���");
		if (StringUtils.isEqual(userType, "xy")) {//ѧԺ���
			return dao.xybxfShByxy(keys, sJg, request);
		}
		if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {
			return dao.xybxfShByxx(keys, sJg, request);
		}
		return "";
	}
	
	/**
	 * У԰���ַ���˲�ѯ
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> xybxfshQryTitle(String userType) throws Exception {
		dao = new PjpyZjjdDAO();
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		if (StringUtils.isEqual(userType, "xy")) {
			topList = dao.xyXybxfshQryTitle();
		}
		if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {
			topList = dao.xxXybxfshQryTitle();
		}
		return topList;
	}
	
	/**
	 * У԰���ַ���˲�ѯ���
	 * @param xybxfModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> xybxfshQryResult(XybxfModel xybxfModel, String userType) throws Exception {
		dao = new PjpyZjjdDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if (StringUtils.isEqual(userType, "xy")) {
			resList = dao.xyXybxfshQryResult(xybxfModel);
		}
		if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {
			resList = dao.xxXybxfshQryResult(xybxfModel);
		}
		return resList;
	}
	
	/**
	 * ��ȡ����б�
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getChkList(int type) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getChkList(type);
	}
	
	/**
	 * У԰���ַ���˲�ѯ
	 * @param userType
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> xybxfshView(String userType, String pkValue) throws Exception {
		dao = new PjpyZjjdDAO();
		HashMap<String, String> resMap = new HashMap<String, String>();
		if (StringUtils.isEqual(userType, "xy")) {
			resMap = dao.xyXybxfshRes(pkValue);
		}
		if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {
			resMap = dao.xxXybxfshRes(pkValue);
		}
		return resMap;
	}
	
	/**
	 * �������У԰���ַ�
	 * @param pkValue
	 * @param sh
	 * @param yj
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean xybxfShOne(String pkValue, String userType, String sh,
			String yj, HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		boolean bFlag = false;
		if (StringUtils.isEqual(userType, "xy")) {
			bFlag = dao.xybxfShOneByXy(pkValue, sh, yj, request);
		}
		if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {
			bFlag = dao.xybxfShOneByXx(pkValue, sh, yj, request);
		}
		return bFlag;
	}
	
	/**
	 * ��ȡѧ���ɲ��������ӷֲ�ѯ��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXsgbdyQryTitle() throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getXsgbdyQryTitle();
	}
	
	/**
	 * ��ȡѧ���ɲ��������ӷֲ�ѯ���
	 * @param xsgbModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXsgbdyQryResult(XsgbdyfjfModel xsgbModel) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getXsgbdyQryResult(xsgbModel);
	}
	
	/**
	 * ��ȡ�ȼ��б�
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getDjList(int type) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getDjList(1);
	}
	
	/**
	 * ѧ���ɲ��������ӷֵ�������
	 * @param xsgbModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveXsgbdyfjf(XsgbdyfjfModel xsgbModel, HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.saveXsgbdyfjf(xsgbModel, request);
	}
	
	/**
	 * ��ȡѧ���ɲ���������Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXsgbdyfxx(String pkValue) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getXsgbdyfxx(pkValue);
	}
	
	/**
	 * ѧ���ɲ��������޸ı���
	 * @param pkValue
	 * @param xsgbModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean xsgbdyfModi(String pkValue, XsgbdyfjfModel xsgbModel,
			HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.xsgbdyfModi(pkValue, xsgbModel, request);
	}
	
	/**
	 * ѧ���ɲ�����������ɾ��
	 * @param keys
	 * @param request
	 * @return ��¼ɾ�����ɹ������ص�����
	 * @throws Exception
	 */
	public String xsgbdyDel(String[] keys, HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.xsgbdyDel(keys, request);
	}
	
	/**
	 * �����������LIST��ѯ��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getQryTitle(String tableName) throws Exception {
		dao = new PjpyZjjdDAO();
		String[] enList = null;
		String[] cnList = null;
		//�ۺ����ʲ������ѯ��ͷ
		if (!StringUtils.isNull(tableName) && StringUtils.isEqual(tableName, "zhszcp")) {
			enList = new String[]{"pk", "rownum", "xh", "xn", "xq", "xm", "bjmc", "dyxj", "zyxj", "tyxj", "zhszcpzf", "zhszcpmc"};
			cnList = new String[]{"pk", "�к�", "ѧ��", "ѧ��", "ѧ��", "����", "�༶", "�����ܷ�", "�����ܷ�", "�����ܷ�", "�ۺ������ܷ�", "�ۺ����ʰ༶����"};
		}
		if (StringUtils.isEqual(tableName, "xsjxjb")) {
			enList = new String[]{"pk", "rownum", "nd", "xn", "xh", "xq", "xm", "bjmc","jxjmc", "xysh", "xxsh"};
			cnList = new String[]{"pk", "�к�", "���", "ѧ��", "ѧ��", "ѧ��", "����", "�༶����", "��ѧ������", "ѧԺ���", "ѧУ���"};
		}
		if (StringUtils.isEqual(tableName, "xsrychb")) {
			enList = new String[]{"pk", "rownum", "nd", "xn", "xh", "xq", "xm", "bjmc","rychmc", "xysh", "xxsh"};
			cnList = new String[]{"pk", "�к�", "���", "ѧ��", "ѧ��", "ѧ��", "����", "�༶����", "�����ƺ�����", "ѧԺ���", "ѧУ���"};
		}
		if (StringUtils.isEqual(tableName, "stusqxx")) {
			enList = new String[]{"xh||xn||xq||nd||jxjdm", "rownum","xn", "xq","nd","jxjmc", "xysh", "xxsh"};
			cnList = new String[]{"pk","�к�", "ѧ��","ѧ��","���", "��ѧ��", "ѧԺ���", "ѧУ���"};
		}
		return dao.getQryTitle(enList, cnList);
	}
	
	/**
	 * �ۺ����ʲ�����ѯ���
	 * @param zhszcpModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZhszcpQryResult(ZhszcpModel zhszcpModel) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getZhszcpQryResult(zhszcpModel);
	}
	
	/**
	 * ��ȡѧ��ѧ��ѧ����ر��ַ�
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZhszcpf(String xh, String xn , String xq) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getZhszcpf(xh, xn, xq);
	}
	
	public List<String[]> getcjList(String xh, String xn, String xq) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getcjList(xh, xn, xq);
	}
	
	/**
	 * �ۺ����ʲ�������
	 * @param zhszcpModel
	 * @return
	 * @throws Exception
	 */
	public boolean zhszcpSave(ZhszcpModel zhszcpModel, HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.zhszcpSave(zhszcpModel, request);
	}
	
	/**
	 * �ۺ���������ɾ��
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String zhszcpDel(String[] keys, HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.zhszcpDel(keys, request);
	}
	
	/**
	 * �ۺ����ʲ����޸���ʾ��ϸ��Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZhszcpInfo(String pkValue) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getZhszcpInfo(pkValue);
	}
	
	/**
	 * �ۺ����ʲ��������޸�
	 * @param zhszcpModel
	 * @param requet
	 * @return
	 * @throws Exception
	 */
	public boolean zhszcpModi(String pkValue, ZhszcpModel zhszcpModel,
			HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.zhszcpModi(pkValue, zhszcpModel, request);
	}
	
	/**
	 * �ۺ����ʲ������ݵ���
	 * @param wwb
	 * @param zhszcpModel
	 * @throws Exception
	 */
	public void zhszcpPrint(WritableWorkbook wwb, ZhszcpModel zhszcpModel) throws Exception {
		dao = new PjpyZjjdDAO();
		//��ȡҪ����������
		List<String[]> resList = dao.zhszcpDataExpQry(zhszcpModel);
		String[] xnxqbjList = new String[3];
		if (resList != null && resList.size() > 0) {
			String[] tmpList = resList.get(0);
			for (int i = 0; i < xnxqbjList.length; i++) {
				xnxqbjList[i] = tmpList[i];//��ȡѧ�꣬ѧ�ڣ��༶��Ϣ��Ϊ�������
			}
			WritableSheet ws = wwb.getSheet(0);
		
			WritableCellFormat tStyle = new WritableCellFormat();
	 	    WritableCellFormat wcfStyle = new WritableCellFormat();
		    Alignment alignMent = Alignment.CENTRE;
		    wcfStyle.setAlignment(alignMent);
		    tStyle.setAlignment(Alignment.LEFT);
		    wcfStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
		    ws.addCell(new Label(0, 0, String.format("%1$s ѧ��� %2$s ѧ�ڵ¡��ǡ����ۺϲ������ܱ�",
					xnxqbjList[0], xnxqbjList[1]), wcfStyle));//�����ͷ
		    ws
					.addCell(new Label(1, 1, String.format("%1$s", xnxqbjList[2]),
							tStyle));//����༶
		    String[] tempList = null;
		    for (int i = 0; i < resList.size(); i++) {
		    	tempList = resList.get(i);//��ȡ��������
		    	int k = 0;
		    	for (int j = 3; j < tempList.length; j++) { //���ÿ������
		    		ws.addCell(new Label(k,i+4,tempList[j],wcfStyle));
		    		k++;
		    	}
		    }
		}
		 ExcelMethods.submitWritableWorkbookOperations(wwb);//������ͻ���
	}
	
	/**
	 * ��ȡ���˽�ѧ��������Ϣ
	 * @param jxjpdModel
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjpdxx(JxjpdxxModel jxjpdModel) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getJxjpdxx(jxjpdModel);
	}
	
	/**
	 * ��ȡѧ�ڴ���
	 * @param jxjpdModel
	 * @return
	 * @throws Exception
	 */
	public String getXqmc(String xqdm) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getXqmc(xqdm);
	}
	/**
	 * ��ѧ�����뱣��
	 * @param jxjpdModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean jxjsqSave(JxjpdxxModel jxjpdModel, HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.jxjsqSave(jxjpdModel, request);
	}
	
	/**
	 * ��ȡ��ѧ������ѧ�꣬��ȣ�ѧ��
	 * @return
	 * @throws Exception
	 */
	public String[] getJxjsqxnxqnd() throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getJxjsqxnxqnd();
	}
	
	/**
	 * ��ѧ���ѯ���
	 * @param jxjpdModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJxjsqQryResult(JxjpdxxModel jxjpdModel) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getJxjsqQryResult(jxjpdModel);
	}
	
	/**
	 * ��ѧ������ɾ��
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String jxjDel(String[] keys, HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.jxjDel(keys, request);
	}
	
	/**
	 * ��ѧ���޸���ʾ��ϸ��Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjModixx(String pkValue) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getJxjModixx(pkValue);
	}
	
	/**
	 * ��ѧ������Ƿ�ͨ��
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public String[] jxjshResult(String pkValue) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.jxjshResult(pkValue);
	}
	
	/**
	 * ��ѧ���޸ı���
	 * @param pkValue
	 * @param jxjpdModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean jxjModi(String pkValue, JxjpdxxModel jxjpdModel,
			HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.jxjModi(pkValue, jxjpdModel, request);
	}
	
	/**
	 * ��ѧ�����ݵ���
	 * @param wwb
	 * @param jxjpdModel
	 * @throws Exception
	 */
	public void jxjPrint(WritableWorkbook wwb, JxjpdxxModel jxjpdModel) throws Exception {
		dao = new PjpyZjjdDAO();
		//��ȡҪ����������
		List<String[]> resList = dao.jxjExpData(jxjpdModel);
		String bjrs = "";//�༶����
		String tabTitle = "";//��ͷ
		if (resList != null && resList.size() > 0) {
			String[] tmpList = resList.get(0);
			bjrs = dao.getBjrs(tmpList != null ? tmpList[0] : "") + " ��";
			tabTitle = String.format("%1$s �༶ %2$s ѧ��� %3$s ѧ��ѧ����ѧ��������",
					tmpList != null ? tmpList[0] : "",
					tmpList != null ? tmpList[1] : "",
					tmpList != null ? tmpList[2] : "");
			
			WritableSheet ws = wwb.getSheet(0);//д�뵽��һ��sheet
			
			WritableCellFormat tStyle = new WritableCellFormat();
	 	    WritableCellFormat wcfStyle = new WritableCellFormat();
		    Alignment alignMent = Alignment.CENTRE;
		    wcfStyle.setAlignment(alignMent);//����������ݾ���
		    tStyle.setAlignment(Alignment.LEFT);
		    wcfStyle.setBorder(Border.ALL, BorderLineStyle.THIN);//���ñ߿򼰱߿�����
		    tStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
		    ws.addCell(new Label(0, 0, tabTitle, wcfStyle));//�����ͷ
		    ws.addCell(new Label(1, 1, bjrs, tStyle));//����༶����
		    for (int i = 0; i < resList.size(); i++) {
		    	String[] tempList = resList.get(i);//��ȡ��������
		    	int k = 0;
		    	for (int j = 3; j < tempList.length; j++) { //���ÿ������
		    		ws.addCell(new Label(k,i+3,tempList[j],wcfStyle));
		    		k++;
		    	}
		    }
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);//������ͻ���
	}
	
	/**
	 * �����ƺ���ѡ����
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXxtj(String xh) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getXxtj(xh);
	}
	
	/**
	 * �����ƺű���
	 */
	public boolean rychSave(RychModel rychModel, HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.rychSave(rychModel, request);
	}
	
	/**
	 * �����ƺŲ�ѯ���
	 * @param rychModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> rychQryResult(RychModel rychModel) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.rychQryResult(rychModel);
	}
	
	/**
	 * �����ƺ��޸���ʾ��ϸ��Ϣ
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getRychXx(String pkValue) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getRychXx(pkValue);
	}
	
	/**
	 * �����ƺ��޸�
	 * @param rychModel
	 * @param pkVlue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean rychModi(RychModel rychModel, String pkValue ,HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.rychModi(rychModel, pkValue, request);
	}
	
	/**
	 * �����ƺ�����ɾ��
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String rychDel(String[] keys, HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.rychDel(keys, request);
	}
	
	/**
	 * ѧ����ѧ���ѯ��Ϣ
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> stuJxjSqxx(String xh) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.stuJxjSqxx(xh);
	}
	
//	�Ƿ��ѧ���ɼ�
	public boolean stuCjFlag(String xh, String xn, String xq) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.stuCjFlag(xh, xn, xq);
	}
	
	// ���湫Ԣ���ַ�
	public boolean saveGybxf(PjpyZjjdActionForm myForm,
			HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.saveGybxf(myForm, request);
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

	// ��Ԣ���ַ���ϢList
	public Vector<Object> getGybxfList(PjpyZjjdActionForm myForm) {
		dao = new PjpyZjjdDAO();
		return dao.getGybxfList(myForm);
	}
	
	//	���湫Ԣ���ַ�
	public HashMap<String, String> getGybxfOne(String pk) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getGybxfOne(pk);
	}
	
	// ɾ����Ԣ���ַ�
	public boolean delGybxf(String pk, HttpServletRequest request)
			throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.delGybxf(pk, request);
	}
}
