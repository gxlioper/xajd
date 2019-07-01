package xgxt.pjpy.zgkd;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.ExcelMethods;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class PjpyZgkdZhszcpService {

	PjpyZgkdZhszcpDAO dao = PjpyZgkdZhszcpDAO.getInstance();
	
	public static PjpyZgkdZhszcpService service = new PjpyZgkdZhszcpService();
	
	public static PjpyZgkdZhszcpService getInstance() {
		return service;
	}
	
	/**
	 * ���ʵȼ������б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSzdjList() throws Exception {
		return dao.getSzdjList();
	}
	
	public HashMap<String, String> getStuInfo(String xh) {
		return dao.getStuInfo(xh);
	}
	
	public List<HashMap<String, String>> szfTitle()throws Exception {
		String[] enList = new String[]{"pk","num","xh","xm","bjmc","xn","pdcpdf","kcxxcjdf","kpm","pd","ddf","sxcpdf","jbszcpdj","jpm",""};
		String[] cnList = new String[]{"pk", "�к�", "ѧ��", "����", "�༶", "ѧ��", "�γ�ѧϰ������","����","Ʒ�²����÷�","���Ĳ����÷�", "�������ʲ�����","����", "��չ���ʲ�����","����", "�������ʵȼ�"};
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		for (int i=0;i<enList.length;i++) {
			HashMap<String, String> tmp = new HashMap<String, String>();
			tmp.put("en", enList[i]);
			tmp.put("cn", cnList[i]);
			rs.add(tmp);
		}
		return rs;
	}
	
	public boolean szfSave(ZhszcpModel model, HttpServletRequest request)
	throws Exception {
		return dao.szfSave(model, request);
	}
	
	public boolean szfUpdate(ZhszcpModel model, String pkValue,
			HttpServletRequest request) throws Exception {
		return dao.szfUpdate(model, pkValue, request);
	}
	
	public HashMap<String, String> szfView(String pkValue) throws Exception {
		return dao.szfView(pkValue);
	}
	
	public String szfDelete(String[] keys, HttpServletRequest request) throws Exception {
		return dao.szfDelete(keys, request);
	}
	
	/**
	 * ��ѯ���
	 * @param model
	 * @param isFdy
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> szfList(ZhszcpModel model, String isFdy, String userName, PjpyZgkdZhszcpActionForm dataSearchForm) throws Exception {
		return dao.szfList(model, isFdy, userName, dataSearchForm);
	}
	public int szfListNum(ZhszcpModel model, String isFdy, String userName) throws Exception {
		return dao.szfListNum(model, isFdy, userName);
	}
	public void szcpHz(ZhszcpModel model, WritableWorkbook wwb) throws Exception {
		ArrayList<ArrayList<String[]>> rs = dao.szcpHz(model);//��ȡѧԺѧ�����ۺ����ʲ�������
		WritableSheet ws = wwb.getSheet(0);
		String xymc = dao.getXymc(model.getXydm());
		WritableCellFormat tStyle = new WritableCellFormat();
 	    WritableCellFormat wcfStyle = new WritableCellFormat();
	    Alignment alignMent = Alignment.CENTRE;//���þ���
	    wcfStyle.setAlignment(alignMent);
	    tStyle.setAlignment(Alignment.CENTRE);
	    tStyle.setBorder(Border.ALL, BorderLineStyle.THIN);//���üӱ߿�
	    WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);//��������
		wfTytle.setBoldStyle(WritableFont.BOLD);//��������Ӵ�
		wfTytle.setPointSize(15);//���������С
		tStyle.setFont(wfTytle);
	    wcfStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
	    ws.addCell(new Label(0,0,xymc + "���ʲ���������ܱ�",tStyle));
		if (rs != null && rs.size()>0) {
			int m = 2;
			for (int i=0;i<rs.size();i++) {
				ArrayList<String[]> rsOneList = rs.get(i);//��ȡÿ��רҵ����
				
				if (rsOneList != null && rsOneList.size()>0) {
					for (int k=0;k<rsOneList.size();k++) {
						String[] tmp = rsOneList.get(k);//��ȡ��������
						int j = 0;
						if (tmp != null && tmp.length>0) {
							for (int l=3;l<tmp.length;l++) {
								ws.addCell(new Label(j,m,tmp[l],wcfStyle));//j++,
								//System.out.print(j + ""  + (m));
								j++;
							}
						}
						m++;
					}
				}
				m++;
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);//������ͻ���
	}
	
	/**
	 * ��ѯѧ���ۺ�������Ϣ
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> szcpInfo(String xh) throws Exception {
		return dao.szcpInfo(xh);
	}
	
	/**
	 * �����ۺ����ʲ���������ʽ
	 * @param pmfs
	 * @param request
	 * @return
	 */
	public boolean saveZhszcpPmfs(String pmfs, HttpServletRequest request) throws Exception {
		return dao.saveZhszcpPmfs(pmfs, request);
	}
	
	/**
	 * ��ѯ�ۺ����ʲ���������ʽ
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> queryZhszcpPmfs() throws Exception {
		return dao.queryZhszcpPmfs();
	}
}
