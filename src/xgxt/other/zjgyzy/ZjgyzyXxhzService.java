package xgxt.other.zjgyzy;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;
import xgxt.xtwh.zpgl.XtwhZpglService;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;

public class ZjgyzyXxhzService extends BasicService {
	
	ZjgyzyXxhzDAO dao=new ZjgyzyXxhzDAO();
	/**
	 * ѧ������һ��
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getXsrsList(ZjgyzyXxhzForm myForm,BasicModel model,String lx) throws Exception{
		
		return dao.getXsrsList(myForm,model,lx);
	}
	
	/**
	 * ѧ������һ��
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getXsdaList(ZjgyzyXxhzForm myForm,BasicModel model,String lx) throws Exception{
		
		return dao.getXsdaList(myForm,model,lx);
	}
	
	/**
	 * ѧ��ס��һ��
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getXszsList(ZjgyzyXxhzForm myForm,BasicModel model,String lx) throws Exception{
		
		return dao.getXszsList(myForm,model,lx);
	}
	
	
	/**
	 * Υ�ʹ���һ��
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getWjcfList(ZjgyzyXxhzForm myForm,BasicModel model,String lx) throws Exception{
		
		return dao.getWjcfList(myForm,model,lx);
	}
	
	/**
	 * Ƹ�����һ��
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getPyqkList(ZjgyzyXxhzForm myForm,BasicModel model,String lx) throws Exception{
		
		return dao.getPyqkList(myForm,model,lx);
	}
	
	/**
	 * ����Ա��Ϣһ��
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getFdyList(ZjgyzyXxhzForm myForm,BasicModel model,String lx) throws Exception{
		
		return dao.getFdyList(myForm,model,lx);
	}
	
	/**
	 * ���������
	 * 
	 * @author qlj
	 */
	public String createSearchHTML(SearchRsModel rsModel,
			BasicModel model,List<String[]> rsArrList, User user) {
	
		StringBuilder html=new StringBuilder();
		
		String ie=rsModel.getIe();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				
				html.append("<td style=\"width:5px\">");
				
				if("yes".equalsIgnoreCase(rsModel.getCheckBox())){
					html.append("<input type='checkbox' name='div_pkValue'  ");
					html.append("  id='pkValue_"+i+"' ");
					html.append(" value='" +replaceHtml(rs[0]) + "'/> ");	
				}else{
					html.append(replaceHtml(rs[0]));
				}
				html.append("</td>");
				
				
				// --------------------����HTML��չ�ֶ����������------------------------
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\""+100/(rs.length-1)+"%\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
	
					html.append(replaceHtml(rs[j]));

					html.append("</td>");
				}
				
				html.append("</tr>");
			}
		}
		
		return html.toString();

	}

	public void printXsrs(ZjgyzyXxhzForm myForm,BasicModel model,WritableWorkbook wwb) throws Exception {
		
		String[] colList ={"r","xymc","bjmc","zrs","nsrs","nvsrs"}; 
		
		model.setColList(colList);
	
		List<String[]>xsrsList=getXsrsList(myForm, model,"tj");
		
		try {
			WritableSheet ws = wwb.createSheet("ѧ���������һ����", 1);
			
			WritableCellFormat wcf = new WritableCellFormat(); // ���쵥Ԫ���ʽ
			
			wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
			// ��ͷ��Ϣ
			ws.mergeCells(0, 0, 5, 0);
			ws.addCell(new Label(0, 0,Base.currXn+"ѧ���"+Base.getDqxqmc()+"ѧ��ѧ������һ����", wcf));

			// -----------------------ѧ���춯��Ϣ begin--------------------------------
			ws.addCell(new Label(0, 1,"���",wcf));
			ws.addCell(new Label(1, 1,"��Ժ",wcf));
			ws.addCell(new Label(2, 1,"�༶",wcf));
			ws.addCell(new Label(3, 1,"����",wcf));
			ws.addCell(new Label(4, 1,"����",wcf));
			ws.addCell(new Label(5, 1,"Ů��",wcf));
			if(xsrsList!=null && xsrsList.size()>0){
				for (int i = 0; i < xsrsList.size(); i++) {
	
					String[]printArr=xsrsList.get(i);
	
					for(int j=0;j<printArr.length;j++){
						
						ws.addCell(new Label(j, 2 + i,printArr[j],wcf));
						
					}
					
				}
				// -----------------------��ӡʱ��-----------------------
				wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
						Alignment.CENTRE, VerticalAlignment.CENTRE, true, null);
				
				ws.mergeCells(4, 2+xsrsList.size(), 5, 2+xsrsList.size());
				ws.addCell(new Label(4,2+xsrsList.size(), "��ӡ���ڣ�"+GetTime.getNowTime2(), wcf));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	public void printXszs(ZjgyzyXxhzForm myForm,BasicModel model,WritableWorkbook wwb) throws Exception {
		
		String[] colList ={"r","xymc","bjmc","zrs","nsrs","nvsrs","rznsrs","rznvsrs","wrznsrs","wrznsrs"}; 
		
		model.setColList(colList);
	
		List<String[]>xszsList=getXszsList(myForm, model,"tj");
		
		try {
			WritableSheet ws = wwb.createSheet("ѧ��ס�����һ����", 1);
			
			WritableCellFormat wcf = new WritableCellFormat(); // ���쵥Ԫ���ʽ
			
			wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
			// ��ͷ��Ϣ
			ws.mergeCells(0, 0, 9, 0);
			ws.addCell(new Label(0, 0,"ѧ��ס�����һ����", wcf));

			// -----------------------ѧ���춯��Ϣ begin--------------------------------
			ws.addCell(new Label(0, 1,"���",wcf));
			ws.addCell(new Label(1, 1,"��Ժ",wcf));
			ws.addCell(new Label(2, 1,"�༶",wcf));
			ws.addCell(new Label(3, 1,"����",wcf));
			ws.addCell(new Label(4, 1,"����",wcf));
			ws.addCell(new Label(5, 1,"Ů��",wcf));
			ws.addCell(new Label(6, 1,"��ס����",wcf));
			ws.addCell(new Label(7, 1,"��סŮ��",wcf));
			ws.addCell(new Label(8, 1,"δ��ס����",wcf));
			ws.addCell(new Label(9, 1,"δ��סŮ��",wcf));
			
			if(xszsList!=null && xszsList.size()>0){
				for (int i = 0; i < xszsList.size(); i++) {
	
					String[]printArr=xszsList.get(i);
	
					for(int j=0;j<printArr.length;j++){
						
						ws.addCell(new Label(j, 2 + i,printArr[j],wcf));
						
					}
					
				}
				// -----------------------��ӡʱ��-----------------------
				wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
						Alignment.CENTRE, VerticalAlignment.CENTRE, true, null);
				
				ws.mergeCells(8, 2+xszsList.size(), 9, 2+xszsList.size());
				ws.addCell(new Label(8,2+xszsList.size(), "��ӡ���ڣ�"+GetTime.getNowTime2(), wcf));
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	public void printXsda(ZjgyzyXxhzForm myForm,BasicModel model,WritableWorkbook wwb) throws Exception {
		
		String[] colList ={"r","xymc","bjmc","zrs","nsrs","nvsrs"}; 
		
		model.setColList(colList);
	
		List<String[]>xsdaList=getXsdaList(myForm, model,"tj");
		
		try {
			WritableSheet ws = wwb.createSheet("ѧ������һ��", 1);
			
			WritableCellFormat wcf = new WritableCellFormat(); // ���쵥Ԫ���ʽ
			
			wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
			// ��ͷ��Ϣ
			ws.mergeCells(0, 0, 5, 0);
			ws.addCell(new Label(0, 0, "ѧ����������һ����", wcf));

			// -----------------------ѧ���춯��Ϣ begin--------------------------------
			ws.addCell(new Label(0, 1,"���",wcf));
			ws.addCell(new Label(1, 1,"��Ժ",wcf));
			ws.addCell(new Label(2, 1,"�༶",wcf));
			ws.addCell(new Label(3, 1,"����",wcf));
			ws.addCell(new Label(4, 1,"����",wcf));
			ws.addCell(new Label(5, 1,"Ů��",wcf));
			
			if(xsdaList!=null && xsdaList.size()>0){
				for (int i = 0; i < xsdaList.size(); i++) {
	
					String[]printArr=xsdaList.get(i);
	
					for(int j=0;j<printArr.length;j++){
						
						ws.addCell(new Label(j, 2 + i,printArr[j],wcf));
						
					}
					
				}
				// -----------------------��ӡʱ��-----------------------
				wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
						Alignment.CENTRE, VerticalAlignment.CENTRE, true, null);
				
				ws.mergeCells(4, 2+xsdaList.size(), 5, 2+xsdaList.size());
				ws.addCell(new Label(4,2+xsdaList.size(), "��ӡ���ڣ�"+GetTime.getNowTime2(), wcf));
			
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	public void printWjcf(ZjgyzyXxhzForm myForm,BasicModel model,WritableWorkbook wwb) throws Exception {
		
		String[] colList ={"r","nd","xn","xqmc","xh","xm","cflbmc","cfsj","cfyymc","cfwh","bjmc"}; 
		
		model.setColList(colList);
	
		List<String[]>wjcfList=getWjcfList(myForm, model,"tj");
		
		try {
			WritableSheet ws = wwb.createSheet("Υ�ʹ���һ��", 1);
			
			WritableCellFormat wcf = new WritableCellFormat(); // ���쵥Ԫ���ʽ
			
			wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
			// ��ͷ��Ϣ
			ws.mergeCells(0, 0, 10, 0);
			ws.addCell(new Label(0, 0, "ѧ��Υ�ʹ������һ����", wcf));

			// -----------------------ѧ���춯��Ϣ begin--------------------------------
			ws.addCell(new Label(0, 1,"���",wcf));
			ws.addCell(new Label(1, 1,"���",wcf));
			ws.addCell(new Label(2, 1,"ѧ��",wcf));
			ws.addCell(new Label(3, 1,"ѧ��",wcf));
			ws.addCell(new Label(4, 1,"ѧ��",wcf));
			ws.addCell(new Label(5, 1,"����",wcf));
			ws.addCell(new Label(6, 1,"��������",wcf));
			ws.addCell(new Label(7, 1,"��������",wcf));
			ws.addCell(new Label(8, 1,"����ԭ��",wcf));
			ws.addCell(new Label(9, 1,"�����ĺ�",wcf));
			ws.addCell(new Label(10, 1,"�༶",wcf));
			
			if(wjcfList!=null && wjcfList.size()>0){
				for (int i = 0; i < wjcfList.size(); i++) {
	
					String[]printArr=wjcfList.get(i);
	
					for(int j=0;j<printArr.length;j++){
						
						ws.addCell(new Label(j, 2 + i,printArr[j],wcf));
						
					}
					
				}
				// -----------------------��ӡʱ��-----------------------
				wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
						Alignment.CENTRE, VerticalAlignment.CENTRE, true, null);
				
				ws.mergeCells(9, 2+wjcfList.size(), 10, 2+wjcfList.size());
				ws.addCell(new Label(9,2+wjcfList.size(), "��ӡ���ڣ�"+GetTime.getNowTime2(), wcf));
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	public void printFdy(ZjgyzyXxhzForm myForm,BasicModel model,WritableWorkbook wwb) throws Exception {
		
		String[] colList ={"r","xm","xb","jg","zzmm","kzzd3","csrq","xl","xw","sxzy",
				"byyx","zc","zwmc","cjgzrq","kzzd1","bmmc","zyzz","pxqk","fblw","grhjqk","kzzd2","bz"}; 
		
		model.setColList(colList);
	
		List<String[]> xsrsList=getFdyList(myForm, model,"tj");
		
		try {
			WritableSheet ws = wwb.createSheet("����Ա��Ϣһ��", 1);
			
			WritableCellFormat wcf = new WritableCellFormat(); // ���쵥Ԫ���ʽ
			
			wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
			// ��ͷ��Ϣ
			ws.mergeCells(0, 0, 21, 0);
			ws.addCell(new Label(0, 0, "�㽭��ҵְҵ����ѧԺ"+Base.currNd+"��ȸ���Ա��Ϣ�ǼǱ�", wcf));

			// -----------------------ѧ���춯��Ϣ begin--------------------------------
			ws.addCell(new Label(0, 1,"���",wcf));
			ws.addCell(new Label(1, 1,"����",wcf));
			ws.addCell(new Label(2, 1,"�Ա�",wcf));
			ws.addCell(new Label(3, 1,"����",wcf));
			ws.addCell(new Label(4, 1,"������ò",wcf));
			ws.addCell(new Label(5, 1,"����״��",wcf));
			ws.addCell(new Label(6, 1,"��������",wcf));
			ws.addCell(new Label(7, 1,"ѧ��",wcf));
			ws.addCell(new Label(8, 1,"ѧλ",wcf));
			ws.addCell(new Label(9, 1,"��ѧרҵ",wcf));
			ws.addCell(new Label(10, 1,"��ҵԺУ",wcf));
			ws.addCell(new Label(11, 1,"ְ��",wcf));
			ws.addCell(new Label(12, 1,"ְ��",wcf));
			ws.addCell(new Label(13, 1,"�μӹ���ʱ��",wcf));
			ws.addCell(new Label(14, 1,"���¸���Ա����ʱ��",wcf));
			ws.addCell(new Label(15, 1,"���ڷ�Ժ",wcf));
			ws.addCell(new Label(16, 1,"�������Ҫ����",wcf));
			ws.addCell(new Label(17, 1,"���ޡ��μӻ��鼰��ѵ���",wcf));
			ws.addCell(new Label(18, 1,"���ġ��������",wcf));
			ws.addCell(new Label(19, 1,"�����",wcf));
			ws.addCell(new Label(20, 1,"������",wcf));
			ws.addCell(new Label(21, 1,"��ע",wcf));
		
			if(xsrsList!=null && xsrsList.size()>0){
				for (int i = 0; i < xsrsList.size(); i++) {
	
					String[]printArr=xsrsList.get(i);
	
					for(int j=0;j<printArr.length;j++){
						
						ws.addCell(new Label(j, 2 + i,printArr[j],wcf));
						
					}
					
				}
			}
		

		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	public void printPrqk(ZjgyzyXxhzForm myForm,BasicModel model,WritableWorkbook wwb) throws Exception {
		
		String[] colList ={"r","nd","xn","xqmc","xymc","bjmc","fdy","bzr"}; 
		
		model.setColList(colList);
	
		List<String[]>prqkList=getPyqkList(myForm, model,"tj");
		
		try {
			WritableSheet ws = wwb.createSheet("����Ա������Ƹ�����һ��", 1);
			
			WritableCellFormat wcf = new WritableCellFormat(); // ���쵥Ԫ���ʽ
			
			wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
			// ��ͷ��Ϣ
			ws.mergeCells(0, 0, 7, 0);
			ws.addCell(new Label(0, 0, "����Ա������Ƹ��һ����", wcf));

			// -----------------------ѧ���춯��Ϣ begin--------------------------------
			ws.addCell(new Label(0, 1,"���",wcf));
			ws.addCell(new Label(1, 1,"���",wcf));
			ws.addCell(new Label(2, 1,"ѧ��",wcf));
			ws.addCell(new Label(3, 1,"ѧ��",wcf));
			ws.addCell(new Label(4, 1,"���ڷ�Ժ",wcf));
			ws.addCell(new Label(5, 1,"�༶",wcf));
			ws.addCell(new Label(6, 1,"����Ա",wcf));
			ws.addCell(new Label(7, 1,"������",wcf));
			
			if(prqkList!=null && prqkList.size()>0){
				
				for (int i = 0; i < prqkList.size(); i++) {
	
					String[]printArr=prqkList.get(i);
	
					for(int j=0;j<printArr.length;j++){
						
						ws.addCell(new Label(j, 2 + i,printArr[j],wcf));
						
					}
					
				}
				// -----------------------��ӡʱ��-----------------------
				wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
						Alignment.CENTRE, VerticalAlignment.CENTRE, true, null);
				
				ws.mergeCells(6, 2+prqkList.size(), 7, 2+prqkList.size());
				ws.addCell(new Label(6,2+prqkList.size(), "��ӡ���ڣ�"+GetTime.getNowTime2(), wcf));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

}
