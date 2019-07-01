package xgxt.wjcf.gzdx;

import java.util.HashMap;
import java.util.List;

import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class WjcfGzdxService {

	/**
	 * ��ѯΥ�ʹ��ֻ�����Ϣ
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryWjcfxx(WjcfGzdxModel model, String[] colList)
			throws Exception {
		WjcfGzdxDAO dao = new WjcfGzdxDAO();
		return dao.queryWjcfxx(model, colList);
	}
	
	//��ѯ�������ݵ���ʼѧ��
	public String queryWjcfMaxMinXn(WjcfGzdxModel model) throws Exception {
		WjcfGzdxDAO dao = new WjcfGzdxDAO();
		return dao.queryWjcfMaxMinXn(model);
	}
	
	/**
	 * Υ�ʹ������ݵ���
	 * @param wwb
	 * @param zhszcpModel
	 * @throws Exception
	 */
	public void wjcfhzPrint(WritableWorkbook wwb, WjcfGzdxModel model) throws Exception {
		String[] colList = new String[] { "r", "xm", "xymc", "nj", "bjmc",
				"cflbmc", "cfyymc", "cfsj" };
		List<String[]> rs = queryWjcfxx(model, colList);
		String xn = queryWjcfMaxMinXn(model);
		String timeTitle = DateUtils.getLyr();
		WritableSheet ws = wwb.getSheet(0);
		
 	    WritableCellFormat wcfStyle = new WritableCellFormat();
 	    WritableCellFormat style = new WritableCellFormat();
 	    WritableFont font = new WritableFont(WritableFont.ARIAL,12);
 	    
	    Alignment alignMent = Alignment.CENTRE;
	    wcfStyle.setAlignment(alignMent);
	    style.setAlignment(alignMent);
	    wcfStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
	    style.setBorder(Border.ALL, BorderLineStyle.THIN);
	    style.setFont(font);
	    
		if (rs != null) {
			if (!StringUtils.isNull(xn)) {
				//����ڶ�����ͷ
			    ws.addCell(new Label(0,1,xn,style));
			}
		    //�����������ͷ
		    ws.addCell(new Label(0,2,"(ͳ�ƽ�ֹ���ڣ�" + timeTitle + ")",style));
		    
			//ѭ�����ÿ������
			for (int i = 0; i < rs.size(); i++) {
				String[] oneRs = rs.get(i);
				int k=0;
				if (oneRs != null && oneRs.length == 8) {
					for (int j=0;j<oneRs.length;j++) {
						ws.addCell(new Label(k,i+4,oneRs[j],wcfStyle));
						//�����п�
						ws.setColumnView(k, (StringUtils.isNull(oneRs[j]) ? 6
								: oneRs[j].length()) * 3);
			    		k++;
					}	
				}
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);//������ͻ���
	}	
	
	/**
	 * ����Υ�ʹ������ݵ���
	 * 
	 * @param wwb
	 * @param zhszcpModel
	 * @throws Exception
	 */
	public void wjcfhzPrintByjwc(WritableWorkbook wwb, WjcfGzdxModel model)
			throws Exception {
		String[] colList = new String[] { "r", "xymc", "xm", "nj", "xh",
				"zymc", "bjmc",
				"cfyymc", "cflbmc"};
		model.setSfjw("��");//ֻ�����������
		List<String[]> rs = queryWjcfxx(model, colList);
		
		WritableSheet ws = wwb.getSheet(0);
		
		String title = "ѧ��ѧ��Υ�ʹ������һ����(����)";
 	    WritableCellFormat wcfStyle = new WritableCellFormat();
 	    WritableCellFormat style = new WritableCellFormat();
 	    WritableFont font = new WritableFont(WritableFont.ARIAL,16);
	    Alignment alignMent = Alignment.CENTRE;
	    wcfStyle.setAlignment(alignMent);
	    style.setAlignment(alignMent);
	    wcfStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
	    style.setBorder(Border.ALL, BorderLineStyle.THIN);
	    style.setFont(font);
	    String xn = " ѧ��� ";
	    String xq = " ";
		if (rs != null) {
			if (!StringUtils.isNull(model.getXn())) {
				xn = model.getXn() + "ѧ���";
			}
			
			if (!StringUtils.isNull(model.getXq())) {
				xq = model.getXq();
			}
			title = xn + xq + title;
			
			//�����һ����ͷ
			ws.addCell(new Label(0,0, title, style));
			
			//ѭ�����ÿ������
			for (int i = 0; i < rs.size(); i++) {
				String[] oneRs = rs.get(i);
				int k=0;
				if (oneRs != null && oneRs.length == 9) {
					for (int j=0;j<oneRs.length;j++) {
						ws.addCell(new Label(k, i+2, oneRs[j], wcfStyle));
						//�����п�
						ws.setColumnView(k, (StringUtils.isNull(oneRs[j]) ? 6
								: oneRs[j].length()) * 3);
			    		k++;
					}	
				}
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);//������ͻ���
	}	
	
	public HashMap<String, String> querySsbprint(String pkValue) {
		WjcfGzdxDAO dao = new WjcfGzdxDAO();
		return dao.querySsbprint(pkValue);
	}
	
	public HashMap<String, String> queryCfbprint(String pkValue) {
		WjcfGzdxDAO dao = new WjcfGzdxDAO();
		return dao.queryCfbprint(pkValue);
	}
}
