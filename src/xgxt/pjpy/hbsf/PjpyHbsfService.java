
package xgxt.pjpy.hbsf;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.pjpy.tablesservice.PjpyService;
import xgxt.utils.ExcelMethods;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ����ʦ��ѧԺ��������Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-10</p>
 */
public class PjpyHbsfService extends PjpyService{

	PjpyHbsfDAO dao = null;//���ݲ���DAO
	
	/**
	 * ��ȡ��ѧ�б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjList() throws Exception {
		dao = new PjpyHbsfDAO();
		return dao.getJxjList();
	}
	
	/**
	 * ͨ��ѧ�Ż�ȡ���������Ϣ(�������Ա����䣬ѧԺ��רҵ���༶��)
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuInfo(String xh) throws Exception {
		dao = new PjpyHbsfDAO();
		return dao.getStuInfo(xh);
	}
	
	/**
	 * ͨ����ѧ������ȡ��ѧ�����
	 * @param jxjdm
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjJelb(String jxjdm) throws Exception {
		dao = new PjpyHbsfDAO();
		return dao.getJxjJelb(jxjdm);
	}
	
	/**
	 * ��ȡ��ѧ������ѧ�����
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjSqxnnd() throws Exception {
		dao = new PjpyHbsfDAO();
		return dao.getJxjSqxnnd();
	}
	
	/**
	 * ��齱ѧ�����������Ƿ��ظ�
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean chkDataExists(String pkValue) throws Exception {
		dao = new PjpyHbsfDAO();
		return dao.chkDataExists("xsjxjb", "xn||nd||xh||jxjdm", pkValue);
	}
	
	/**
	 * ��ѧ�����뱣��
	 * @param jxjsqModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean jxjsqSave(JxjsqSaveModel jxjsqModel, HttpServletRequest request) throws Exception {
		dao = new PjpyHbsfDAO();
		return dao.jxjsqSave(jxjsqModel, request);
	}
	
	/**
	 * �ۺ����ʲ�ѯ��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZhszcpTitle() throws Exception {
		dao = new PjpyHbsfDAO();
		return dao.getZhszcpTitle();
	}
	
	/**
	 * �ۺ����ʲ�ѯ���
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZhszcpResult(ZhszcpQryModel zhszcpModel) throws Exception {
		dao = new PjpyHbsfDAO();
		return dao.getZhszcpResult(zhszcpModel);
	}
	
	/**
	 * �ۺ����ʲ�����Ϣ����
	 * @param zhszcpModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean zhszcpSave(ZhszcpSaveModel zhszcpModel, HttpServletRequest request) throws Exception {
		dao = new PjpyHbsfDAO();
		return dao.zhszcpSave(zhszcpModel, request);
	}

	/**
	 * �ۺ����ʲ�������ɾ��
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String zhszcpDel(String[] keys, HttpServletRequest request) throws Exception {
		dao = new PjpyHbsfDAO();
		return dao.zhszcpDel(keys, request);
	}
	
	/**
	 * ͨ��������ȡ�ۺ�������ϸ��Ϣ
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZhszcpInfoByPk(String pkValue) throws Exception {
		dao = new PjpyHbsfDAO();
		return dao.getZhszcpInfoByPk(pkValue);
	}
	
	/**
	 * �ۺ����ʲ�����������
	 * @param wwb
	 * @param xydm
	 * @param zydm
	 * @param bjdm
	 * @throws Exception
	 */
	public void printZhszcp(WritableWorkbook wwb, String xydm, String zydm, String bjdm, String xn) throws Exception {
		dao = new PjpyHbsfDAO();
		//�������ݼ�
		List<HashMap<String, String>> resList = dao.getZhszcpInfoByBj(xydm, zydm, bjdm, xn);
		WritableSheet ws = wwb.getSheet(0);
		WritableCellFormat tStyle = new WritableCellFormat();
 	    WritableCellFormat wcfStyle = new WritableCellFormat();
	    Alignment alignMent = Alignment.CENTRE;
	    wcfStyle.setAlignment(alignMent);
	    tStyle.setAlignment(Alignment.CENTRE);
	    wcfStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
	    HashMap<String, String> tmpMap = new HashMap<String, String>();
	    HashMap<String, String> tempMap = new HashMap<String, String>();
	    if (resList != null && resList.size() > 0) {
	    	tempMap = resList.get(0);//��ȡ��ͷ���ݼ�
	    	ws.addCell(new Label(1,1,tempMap.get("xymc"),tStyle));
		    ws.addCell(new Label(6,1,tempMap.get("zymc"),tStyle));
		    ws.addCell(new Label(13,1,tempMap.get("bjmc"),tStyle));
		    ws.addCell(new Label(18,1,tempMap.get("xn"),tStyle));
 	    for (int i = 0; i < resList.size(); i++) {
 	    	tmpMap = resList.get(i);//��ȡÿ�����ݼ�
	    	for (int j = 0; j < 1; j++) {//ѭ�����������
	    		ws.addCell(new Label(j,i+6,tmpMap.get("xh"),wcfStyle));
	    		ws.addCell(new Label(j+1,i+6,tmpMap.get("xm"),wcfStyle));
	    		ws.addCell(new Label(j+2,i+6,tmpMap.get("zpf"),wcfStyle));
	    		ws.addCell(new Label(j+3,i+6,tmpMap.get("sxddbx"),wcfStyle));
	    		ws.addCell(new Label(j+4,i+6,tmpMap.get("zzllxx"),wcfStyle));
	    		ws.addCell(new Label(j+5,i+6,tmpMap.get("ssjsqk"),wcfStyle));
	    		ws.addCell(new Label(j+6,i+6,tmpMap.get("shsjhd"),wcfStyle));
	    		ws.addCell(new Label(j+7,i+6,tmpMap.get("gbrzbx"),wcfStyle));
	    		ws.addCell(new Label(j+8,i+6,tmpMap.get("qttcsj"),wcfStyle));
	    		ws.addCell(new Label(j+9,i+6,tmpMap.get("dcj"),wcfStyle));
	    		ws.addCell(new Label(j+10,i+6,tmpMap.get("kcjqpfj"),wcfStyle));
	    		ws.addCell(new Label(j+11,i+6,tmpMap.get("kcjqpfjpm"),wcfStyle));
	    		ws.addCell(new Label(j+12,i+6,tmpMap.get("zytz"),wcfStyle));
	    		ws.addCell(new Label(j+13,i+6,tmpMap.get("yyjn"),wcfStyle));
	    		ws.addCell(new Label(j+14,i+6,tmpMap.get("jsjjn"),wcfStyle));
	    		ws.addCell(new Label(j+15,i+6,tmpMap.get("zyjn"),wcfStyle));
	    		ws.addCell(new Label(j+16,i+6,tmpMap.get("zyjn"),wcfStyle));
	    		ws.addCell(new Label(j+17,i+6,tmpMap.get("zyjn"),wcfStyle));
	    		ws.addCell(new Label(j+18,i+6,tmpMap.get("zcj"),wcfStyle));
	    		ws.addCell(new Label(j+19,i+6,tmpMap.get("tydb"),wcfStyle));
	    		ws.addCell(new Label(j+20,i+6,tmpMap.get("tyhd"),wcfStyle));
	    		ws.addCell(new Label(j+21,i+6,tmpMap.get("tsqk"),wcfStyle));
	    		ws.addCell(new Label(j+22,i+6,tmpMap.get("stszzf"),wcfStyle));
	    		ws.addCell(new Label(j+23,i+6,tmpMap.get("xljkhd"),wcfStyle));
	    		ws.addCell(new Label(j+24,i+6,tmpMap.get("xlszzk"),wcfStyle));
	    		ws.addCell(new Label(j+25,i+6,tmpMap.get("xlszzf"),wcfStyle));
	    		ws.addCell(new Label(j+26,i+6,tmpMap.get("tcj"),wcfStyle));
	    		ws.addCell(new Label(j+27,i+6,tmpMap.get("zhszcpzf"),wcfStyle));
	    		ws.addCell(new Label(j+28,i+6,tmpMap.get("zhcppm"),wcfStyle));
	    		ws.addCell(new Label(j+29,i+6,tmpMap.get("bjghkms"),wcfStyle));
	    		ws.addCell(new Label(j+30,i+6,tmpMap.get("cfjz"),wcfStyle));
	    		ws.addCell(new Label(j+31,i+6,tmpMap.get("cpjg"),wcfStyle));
	    		}
 	    	}
	    }
	    ExcelMethods.submitWritableWorkbookOperations(wwb);//������ͻ���
	}
}
