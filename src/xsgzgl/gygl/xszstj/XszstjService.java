package xsgzgl.gygl.xszstj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.ExcelMB;
import xgxt.utils.ExcelMethods;
import xsgzgl.gygl.comm.GyglNewService;

public class XszstjService extends GyglNewService{
	
	private XszstjDao dao=new XszstjDao();
	
	/**
	 * ��ȡѧ��ס��ͳ����Ϣ�б�
	 * @return
	 */
	public List<HashMap<String, String>> getXszstjxxList(XszstjForm myForm,HttpServletRequest request) throws Exception{
		return dao.getXszstjxxList(myForm,request);
	}
	
	/**
	 * ��ȡѧ��ס��ͳ����Ϣ�б����飩
	 * @return
	 */
	public List<HashMap<String, String>> getXszstjDetailxxList(XszstjForm myForm){
		return dao.getXszstjDetailxxList(myForm);
	}
	
	public ArrayList<String[]> getXszstjXsxxList(XszstjForm myForm, HttpServletRequest request) 
	throws Exception{	
		return dao.getXszstjXsxxList(myForm, request);
	}

	public Object getXyList(HttpServletRequest request) {
		return dao.getXyList(request);
	}

	/**
	 * �������ҷֲ�ͳ�Ʊ�
	 * @author wujian
	 */
	public void expQsfbb(XszstjForm model, ServletOutputStream os,String xymc) throws Exception {
		String title = xymc+"ѧ�����ҷֲ���";
		// �̶���
		String[] gdName = new String[] { "ѧԺ", "¥��", "����","�Ա�","��������","��ע","�༶","����Ա"};
		// ���������ͷ
		List<HashMap<String, String>> topTr = getTopList(gdName);
		// ��������Ĺ̶�����
		ArrayList<String[]> gdlist = dao.expQsfbb(model,xymc);

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(title, 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// ����ͷ
		ExcelMB excel = new ExcelMB();
		excel.printTitle(ws, title, 8, 800);// ����
		
		if (topTr != null && topTr.size() > 0) {
			for (int i = 0; i < topTr.size(); i++) {
				ws.setColumnView(i, 25);
				HashMap<String, String> map = topTr.get(i);
				ws.addCell(new Label(i, 1, map.get("cn"), wcf2));
			}
		}
		// �������
		if (gdlist != null && gdlist.size() > 0) {
			for (int i = 0; i < gdlist.size(); i++) {
				String[] info = gdlist.get(i);
				if (info != null && info.length > 0) {
					for (int j = 0; j < info.length; j++) {
						ws.addCell(new Label(j, i + 2, info[j], wcf2));
					}
				}
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	public List<HashMap<String, String>> getXszstjxxExportList(XszstjForm myForm,HttpServletRequest request) throws Exception{
		return dao.getXszstjxxExportList(myForm,request);
	}
	
	public List<HashMap<String, String>> getTbtj() throws Exception{
		return dao.getTbtj();
	}

	/** 
	 * @����:ס�޸ſ�ͳ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-5-18 ����05:19:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getZsgktj() {
		return dao.getZsgktj();
	}
	
	/**
	 * 
	 * @����:��ȡ����¥��Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-5-19 ����10:30:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getSslInfo(){
		return dao.getSslInfo();
	}
	
	
	/**
	 * 
	 * @����:��ȡ����¥����Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-5-19 ����10:30:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getSslcInfo(){
		return dao.getSslcInfo();
	}

	/** 
	 * @����:��ϵ����ֲ�ͼ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-6-1 ����10:27:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getGxsstj() throws Exception {
		return dao.getGxsstj();
	}

	/** 
	 *��ϵ�մ�λ��Ϣ
	 */
	public List<HashMap<String, String>> getGxkcwxx() throws Exception {
		return dao.getGxkcwxx();
	}
	
}