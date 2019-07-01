package xgxt.jygl.njjs;

import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
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
import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.utils.ExcelMethods;

public class NjjsJyglService {
	private NjjsJyglDAO jyglDao = new NjjsJyglDAO();		// �Ͼ���ʦ��ҵ����DAO
	
	/**
	 * ��ҵȥ����ܲ�ѯ
	 * @param model
	 * @return
	 */
	public List<String[]> byqxhzQuery(NjjsJyglForm model){
		return jyglDao.byqxhzQuery(model);
	}
	
	/**
	 * ��ȡѧ��List
	 * @return
	 */
	public List<HashMap<String, String>> getXlList(){
		return jyglDao.getXlList();
	}
	
	/**
	 *��ȡ������Σ����ܵȼ���List
	 * @return
	 */
	public List<HashMap<String, String>> getPyccList(){
		return jyglDao.getPyccList();
	}
	
	/**
	 * ��ȡ������ʽList
	 * @return
	 */
	public List<HashMap<String, String>> getPyfsList(){
		return jyglDao.getPyfsList();
	}
	
	/**
	 * ��ȡ��ҵȥ��List
	 * @return
	 */
	public List<HashMap<String, String>> getByqxList(){
		return jyglDao.getByqxList();
	}
	
	/**
	 * ��ȡ��ҵ���List
	 * @return
	 */
	public List<HashMap<String, String>> getByndList(){
		return jyglDao.getByndList();
	}
	
	/**
	 * ��ҵ��ѧ������
	 * @param model
	 * @return
	 */
	public List<String[]> xssbQuery(NjjsJyglModel model){
		return jyglDao.xssbQuery(model);
	}
	
	/**
	 * ��ҵ����ѯ
	 * @param model
	 * @return
	 */
	public List<String[]> bysQuery(NjjsJyglModel model){
		return jyglDao.bysQuery(model);
	}
	
	/**
	 * �ϱ�ѧ����ѯ
	 * @param model
	 * @return
	 */
	public List<String[]> sbxsQuery(NjjsJyglModel model){
		return jyglDao.sbxsQuery(model);
	}
	
	/**
	 * ѧ��ʵϰ��ѯ
	 * @param model
	 * @return
	 */
	public List<String[]> xssxQuery(NjjsJyglModel model){
		return jyglDao.xssxQuery(model);
	}
	
	/**
	 * ����ѧ���ϱ�
	 * @param model
	 * @return
	 */
	public boolean saveXssb(NjjsJyglModel model){
		return jyglDao.saveXssb(model);
	}
	
	/**
	 * �޸�ѧ���ϱ�
	 * @param model
	 * @return
	 */
	public boolean updateXssb(NjjsJyglModel model){
		return jyglDao.updateXssb(model);
	}
	
	/**
	 * �����ҵȥ��
	 * @param model
	 * @return
	 */
	public boolean saveByqx(NjjsJyglModel model){
		return jyglDao.saveByqx(model);
	}
	
	/**
	 * ����ѧ��ʵϰ
	 * @param model
	 * @return
	 */
	public boolean saveXssx(NjjsJyglModel model){
		return jyglDao.saveXssx(model);
	}
	
	/**
	 * ����ѧ��ʵϰ
	 * @param model
	 * @return
	 */
	public boolean updateXssx(NjjsJyglModel model){
		return jyglDao.updateXssx(model);
	}
	
	/**
	 * ��ȡѧ����Ϣ
	 * @param xh
	 * @return
	 */
	public Map<String, String> getStuInfo(String xh){
		return jyglDao.getStuInfo(xh);
	}
	
	public Map<String, String> getXssxInfo(String xh){
		return jyglDao.getXssxInfo(xh);
	}
	
	/**
	 * ��ȡ��ҵ����Ϣ
	 * @param xh
	 * @return
	 */
	public Map<String, String> getBysInfo(String xh){
		return jyglDao.getBysInfo(xh);
	}
	
	public List<HashMap<String, String>> getTopTr(String lx,RequestForm rForm) {

		DAO dao = DAO.getInstance();
		
		String[]en=null;
		String[]cn=null;
		if("dxsxgl".equalsIgnoreCase(lx)){
			 en = new String[] { "xh", "xm", "xymc", "zymc", "bjmc", "jyfs","dwmc" };
			 cn = new String[] { "ѧ��", "����", Base.YXPZXY_KEY, "רҵ", "�༶", "��ҵ��ʽ","��λ����" };
		}else if("byqx".equalsIgnoreCase(lx)){
			 en = new String[]{"xh", "xm", "xb", "bjmc", "bynd", "byqxmc", "sfsb" };
			 cn = new String[] { "ѧ��", "����", "�Ա�", "�༶", "��ҵ���", "��ҵȥ��", "�Ƿ����ϱ�"};
		}else if("xssb".equalsIgnoreCase(lx)){
			 en = new String[]{"xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc"};
			 cn = new String[]{"ѧ��", "����", "�Ա�", "�꼶", Base.YXPZXY_KEY, "רҵ", "�༶"};
		}else if("xssx".equalsIgnoreCase(lx)){
			 en = new String[]{"xh", "xm", "nj", "xymc", "zymc", "bjmc", "sfsb"};
			 cn = new String[] {"ѧ��", "����", "�꼶", Base.YXPZXY_KEY, "רҵ", "�༶", "�Ƿ���¼��"};
		}else if("sbxs".equalsIgnoreCase(lx)){
			en = new String[]{"xh", "xm", "nj", "xymc", "zymc", "bjmc", "bynd" };;
			cn = new String[] {"ѧ��", "����", "�꼶", Base.YXPZXY_KEY, "רҵ", "�༶", "��ҵ���"};
		}
		
		rForm.setColList(en);
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * ɾ���ϱ�ѧ��
	 * @param xhArr
	 * @return
	 */
	public boolean delSbxs(String[] xhArr){
		List<String[]> list = new ArrayList<String[]>();
		
		for(int i=0; i<xhArr.length; i++){
			list.add(new String[]{xhArr[i]});
		}
		
		return jyglDao.delSbxs(list);
	}
	
	/**
	 * ɾ���ϱ�ѧ��
	 * @param xhArr
	 * @return
	 */
	public boolean delByqx(String[] xhArr){
		List<String[]> list = new ArrayList<String[]>();
		
		for(int i=0; i<xhArr.length; i++){
			list.add(new String[]{xhArr[i]});
		}
		
		return jyglDao.delByqx(list);
	}
	
	/**
	 * ɾ��ѧ��ʵϰ
	 * @param xhArr
	 * @return
	 */
	public boolean delXssx(String[] xhArr){
		List<String[]> list = new ArrayList<String[]>();
		
		for(int i=0; i<xhArr.length; i++){
			list.add(new String[]{xhArr[i]});
		}
		
		return jyglDao.delXssx(list);
	}
	
	/**
	 * ��ҵȥ�򵼳�
	 * @param model
	 * @param os
	 * @throws Exception 
	 */
	public void byqxdc(NjjsJyglModel model,OutputStream os) throws Exception{
		String bjmc = model.getBjmc();
		String title = Base.currNd+"���ҵ����ҵ������";
		
		List<HashMap<String, String>> list = jyglDao.getByqxDcInfo(model);
		
		String[] colNameCN = new String[]{"��  ��", "��  ��" , "��  ��", "��������", "��ͥסַ�������ɳ���", "��ҵ��λ", "��ϵ�绰"};
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(bjmc, 0);
		
		WritableCellFormat titleFormat = ExcelMethods.getWcf(WritableFont.ARIAL, 14, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.NONE); // ���쵥Ԫ���ʽ
		
		WritableCellFormat cellFormat = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL); // ���쵥Ԫ���ʽ
		
		WritableCellFormat headFormat = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.LEFT, VerticalAlignment.CENTRE, true, Border.NONE); // ���쵥Ԫ���ʽ
		
		ws.mergeCells(0, 0, colNameCN.length-1, 0);
		ws.mergeCells(0, 1, colNameCN.length-2, 1);
		
		ws.addCell(new Label(0, 0, title, titleFormat));
		ws.addCell(new Label(0, 1, "ѧУ�����£����Ͼ���ʦѧԺ", headFormat));
		ws.addCell(new Label(colNameCN.length-1, 1, "�༶��"+bjmc, headFormat));
		
		try {
			for (int m = 0; m < colNameCN.length; m++) {
				ws.addCell(new Label(m, 2, colNameCN[m], cellFormat));
			}
			
			for (int i = 0; i < list.size(); i++) {
				Map<String, String> map = list.get(i);
				ws.addCell(new Label(0, i + 3, map.get("count"), cellFormat));
				ws.addCell(new Label(1, i + 3, map.get("xm"), cellFormat));
				ws.addCell(new Label(2, i + 3, map.get("xb"), cellFormat));
				ws.addCell(new Label(3, i + 3, map.get("csrq"), cellFormat));
				ws.addCell(new Label(4, i + 3, map.get("jtszd"), cellFormat));
				ws.addCell(new Label(5, i + 3, map.get("jydw"), cellFormat));
				ws.addCell(new Label(6, i + 3, map.get("lxdh"), cellFormat));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���ݵ���ʧ��!");
		} finally {
			wwb.write();
			wwb.close();
		}
		
	}
	
		public void xssxExp(NjjsJyglModel model ,WritableWorkbook wwb)throws Exception {
		List<String[]> list = new ArrayList<String[]>();
		list = jyglDao.xssxExp(model);
		HashMap<String,String> bjxx = jyglDao.getBjxx(model);
		// ��·�
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy��MM��");
		String tbyf = format.format(date);

		WritableSheet ws = wwb.createSheet("sheet1", 0);
		try {
	//		ws.setPageSetup(PageOrientation.LANDSCAPE.LANDSCAPE,PaperSize.A4,0.5d,0.5d);
	
			
			
			// ������һ��
			Label titleCell = new Label(0, 0, bjxx.get("xymc")+bjxx.get("bjmc")+"��ѧ����ҵ����ʵϰ��������±���         "+tbyf);
			
			WritableCellFormat wcFormat = new WritableCellFormat();
			wcFormat = new WritableCellFormat();
			wcFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			wcFormat.setAlignment(Alignment.CENTRE);
			wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			WritableFont font = new WritableFont(WritableFont.ARIAL, 16);
			font.setBoldStyle(WritableFont.BOLD);
			wcFormat.setFont(font);
			
			ws.mergeCells(0, 0, 12, 0);
			titleCell.setCellFormat(wcFormat);
			ws.addCell(titleCell);
	
			// �б���
			//��ͷ������ʽ
			wcFormat = new WritableCellFormat();
			wcFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			wcFormat.setAlignment(Alignment.CENTRE);
			wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			font = new WritableFont(WritableFont.ARIAL, 10);
			font.setBoldStyle(WritableFont.BOLD);
			wcFormat.setFont(font);
			
			String[] bt = new String[]{"���","����","��λ����","��ҵ��ʽ","��ַ","����","����","��λ",
					"�Ͷ�����","ʳ������","��ͨ״��","ѧ����ϵ�绰","ʵϰ��λ�䶯���"};
			for(int i=0;i<bt.length;i++){
				titleCell = new Label(i, 1, bt[i]);
				titleCell.setCellFormat(wcFormat);
				ws.addCell(titleCell);
			}
			
			
			//����������ʽ
			wcFormat = new WritableCellFormat();
			wcFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			wcFormat.setAlignment(Alignment.CENTRE);
			wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			font = new WritableFont(WritableFont.ARIAL, 10);
			wcFormat.setFont(font);
			if (list.size() != 0) {
				for (int k=0;k<list.size();k++) {
					String[] map = list.get(k);
					for(int n=0;n<map.length;n++){
						titleCell = new Label(n, k+2, map[n]);
						titleCell.setCellFormat(wcFormat);
						ws.addCell(titleCell);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			wwb.write();
			wwb.close();
		}
		
	}
	
	/**
	 * ��ȡʡList
	 * @return
	 */
	public List<HashMap<String, String>> getShenList(){
		return jyglDao.getShenList();
	}
	
	/**
	 * ��ȡ��List
	 * @return
	 */
	public List<HashMap<String, String>> getShiList(String shen){
		return jyglDao.getShiList(shen);
	}
	
	/**
	 * ��ȡ��List
	 * @return
	 */
	public List<HashMap<String, String>> getXianList(String shen,String shi){
		return jyglDao.getXianList(shen,shi);
	}
	
	/**
	 * ����ѧ���ļ�ͥ��ַ����ϵ�绰
	 * @param xh
	 * @param jtdz
	 * @param lxdh
	 * @return
	 */
	public boolean saveXsJtdzAndLxdh(String xh,String jtdz,String lxdh){
		if(Base.isNull(jtdz)&&Base.isNull(lxdh)){
			return true;
		}
		return jyglDao.saveXsJtdzAndLxdh(xh,jtdz,lxdh);
	}
}
