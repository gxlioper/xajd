package xsgzgl.szdw.general.tjcx.bmqk;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.utils.StringUtil;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xsgzgl.szdw.general.SzdwGeneralForm;
import xsgzgl.szdw.general.inter.tjcx.TjcxBmqkInterface;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ˼������_ͳ�Ʋ�ѯ_�������_ͨ��_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class BmqkService extends CommService implements TjcxBmqkInterface {

	BmqkDAO dao = new BmqkDAO();

	/**
	 * �����������ͳ��DIV
	 * 
	 * @date 2013-01-09
	 * @author ΰ�����
	 */
	public void createBmqkStatDiv(SzdwGeneralForm myForm, BmqkModel model,
			User user, HttpServletResponse response) throws Exception{
		// TODO �Զ����ɷ������
		response.setContentType("text/html;charset=gbk");

		String html = new String();

		List<HashMap<String, String>> list = dao.getBmqkList(myForm, user);
		String[] topTr;
		if(Base.xxdm.equals("12834")){
			topTr =new String[]{"��������","�༶����","�����ӳ��༶��","δ���ӳ��༶��","�����жӳ��༶��","δ���жӳ��༶��"};
		}else {
			topTr =new String[]{"��������","�༶����","���踨��Ա�༶��","δ�踨��Ա�༶��","��������ΰ༶��","δ������ΰ༶��"};
		}
		html=createHtml(topTr,list);
		response.getWriter().print(html.toString());
	}
	
	private String createHtml(String[] topTr, List<HashMap<String, String>> resultList) {
		// TODO �Զ����ɷ������
		StringBuilder html = new StringBuilder();
		html.append("<h3 class=\"datetitle_01\">");
		html.append("<span id=\"span_note\" width=\"100%\">");
		html.append("ͳ�ƽ��&nbsp;&nbsp;");		
		html.append("</span>");
		html.append("</h3>");
		html.append("<span class=\"formbox\">");
		html.append("<table class=\"dateline\" width=\"100%\" id=\"table_rs\" style=\"\">");
		// =========================����===========================
		html.append("<thead>");
		html.append("<tr>");
		if (topTr != null && topTr.length > 0) {
			for (int i = 0; i < topTr.length; i++) {
				if(i == 0){
					html.append("<td width=\"5px\" nowrap=\"nowrap\" ");
				}else{
					html.append("<td style=\"width:"+(100/(topTr.length-1))+"%\"  nowrap=\"nowrap\" ");
				}			
				

				html.append(">");
				html.append(topTr[i]);
				html.append("</td>");
			}
		}

			html.append("</tr>");
			html.append("</thead>");
		if (resultList != null && resultList.size() > 0) {
			html.append("<tbody>");
			
			int[] hjzs = new int[]{0,0,0,0,0};
			
			for (int i = 0; i < resultList.size(); i++) {
				HashMap<String, String> map = resultList.get(i);
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

				//ѧԺ����
				html.append("<td class=\"nowrap\" title=\"").append(map.get("xymc")).append("\">");
				if (map.get("xymc").length() > 16){
					html.append(map.get("xymc").substring(0, 16));
					html.append("...");
				} else {
					html.append(map.get("xymc"));
				}
				html.append("</td>");
				
				//�༶����
				html.append("<td align=\"center\">").append("<a class=\"name\" href=\"javascript:void(0);\"")
					.append(" onclick=\"showBjqkInfo('general_szdw.do?method=getBmtjInfo&xydm=")
					.append(map.get("xydm")).append("');\">")
					.append(map.get("bjdms")).append("</a></td>");
				hjzs[0] = hjzs[0]+Integer.valueOf(map.get("bjdms"));
				
				//���踨��Ա�༶��
				if("11647".equalsIgnoreCase(Base.xxdm)){
					//�㽭��ý���Ի�
					html.append("<td align=\"center\">").append("<a class=\"name\" href=\"javascript:void(0);\"")
						.append(" onclick=\"showBjqkInfo('general_szdw.do?method=getFdyInfo&fdydb=yes&xydm=")
						.append(map.get("xydm")).append("');\">")
						.append(map.get("fdydbs")).append("</a></td>");
					hjzs[1] = hjzs[1]+Integer.valueOf(map.get("fdydbs"));
				}else{
					html.append("<td align=\"center\">").append("<a class=\"name\" href=\"javascript:void(0);\"")
						.append(" onclick=\"showBjqkInfo('general_szdw.do?method=getBmtjInfo&fdydb=yes&xydm=")
						.append(map.get("xydm")).append("');\">")
						.append(map.get("fdydbs")).append("</a></td>");
					hjzs[1] = hjzs[1]+Integer.valueOf(map.get("fdydbs"));
				}
				
				//δ�踨��Ա�İ༶��
				html.append("<td align=\"center\">").append("<a class=\"name\" href=\"javascript:void(0);\"")
					.append(" onclick=\"showBjqkInfo('general_szdw.do?method=getBmtjInfo&fdydb=no&xydm=")
					.append(map.get("xydm")).append("');\">")
					.append(map.get("mfdydbs")).append("</a></td>");
				hjzs[2] = hjzs[2]+Integer.valueOf(map.get("mfdydbs"));
				
				//��������δ�����
				if("11647".equalsIgnoreCase(Base.xxdm)){
					//�㽭��ý���Ի�
					html.append("<td align=\"center\">").append("<a class=\"name\" href=\"javascript:void(0);\"")
					.append(" onclick=\"showBjqkInfo('general_szdw.do?method=getBzrInfo&bzrdb=yes&xydm=")
					.append(map.get("xydm")).append("');\">")
					.append(map.get("bzrdbs")).append("</a></td>");
				hjzs[3] = hjzs[3]+Integer.valueOf(map.get("bzrdbs"));
				}else{
					html.append("<td align=\"center\">").append("<a class=\"name\" href=\"javascript:void(0);\"")
						.append(" onclick=\"showBjqkInfo('general_szdw.do?method=getBmtjInfo&bzrdb=yes&xydm=")
						.append(map.get("xydm")).append("');\">")
						.append(map.get("bzrdbs")).append("</a></td>");
					hjzs[3] = hjzs[3]+Integer.valueOf(map.get("bzrdbs"));
				}
				
				//δ�踨��Ա������
				html.append("<td align=\"center\">").append("<a class=\"name\" href=\"javascript:void(0);\"")
					.append(" onclick=\"showBjqkInfo('general_szdw.do?method=getBmtjInfo&bzrdb=no&xydm=")
					.append(map.get("xydm")).append("');\">")
					.append(map.get("mbzrdbs")).append("</a></td>");
				hjzs[4] = hjzs[4]+Integer.valueOf(map.get("mbzrdbs"));
				
				html.append("</tr>");
			}
			html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
			html.append("<td align=\"left\" nowrap=\"nowrap\">");
			html.append("�ϼ�");
			html.append("</td>");
			
			
			html.append("<td align=\"center\" nowrap=\"nowrap\">")
				.append("<a class=\"name\" href=\"javascript:void(0);\"")
				.append(" onclick=\"showBjqkInfo('general_szdw.do?method=getBmtjInfo")
				.append("');\">").append(hjzs[0]).append("</a></td>");
			
			if("11647".equalsIgnoreCase(Base.xxdm)){
				//�㽭��ý���Ի�
				html.append("<td align=\"center\" nowrap=\"nowrap\">")
					.append("<a class=\"name\" href=\"javascript:void(0);\"")
					.append(" onclick=\"showBjqkInfo('general_szdw.do?method=getFdyInfo&fdydb=yes")
					.append("');\">").append(hjzs[1]).append("</a></td>");
			}else{
				html.append("<td align=\"center\" nowrap=\"nowrap\">")
					.append("<a class=\"name\" href=\"javascript:void(0);\"")
					.append(" onclick=\"showBjqkInfo('general_szdw.do?method=getBmtjInfo&fdydb=yes")
					.append("');\">").append(hjzs[1]).append("</a></td>");
			}
			
			html.append("<td align=\"center\" nowrap=\"nowrap\">")
			.append("<a class=\"name\" href=\"javascript:void(0);\"")
			.append(" onclick=\"showBjqkInfo('general_szdw.do?method=getBmtjInfo&fdydb=no")
			.append("');\">").append(hjzs[2]).append("</a></td>");
			
			if("11647".equalsIgnoreCase(Base.xxdm)){
				//�㽭��ý���Ի�
				html.append("<td align=\"center\" nowrap=\"nowrap\">")
				.append("<a class=\"name\" href=\"javascript:void(0);\"")
				.append(" onclick=\"showBjqkInfo('general_szdw.do?method=getBzrInfo&bzrdb=yes")
				.append("');\">").append(hjzs[3]).append("</a></td>");
			}else{
				html.append("<td align=\"center\" nowrap=\"nowrap\">")
					.append("<a class=\"name\" href=\"javascript:void(0);\"")
					.append(" onclick=\"showBjqkInfo('general_szdw.do?method=getBmtjInfo&bzrdb=yes")
					.append("');\">").append(hjzs[3]).append("</a></td>");
			}
			
			html.append("<td align=\"center\" nowrap=\"nowrap\">")
			.append("<a class=\"name\" href=\"javascript:void(0);\"")
			.append(" onclick=\"showBjqkInfo('general_szdw.do?method=getBmtjInfo&bzrdb=no")
			.append("');\">").append(hjzs[4]).append("</a></td>");
			
			html.append("</tr>");
		}else{
			html.append("</tbody>");
			for(int i=0;i<11;i++){
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				for (int j = 0; j < topTr.length; j++) {
					html.append("<td height=\"29px\" align=\"center\" nowrap=\"nowrap\"></td>");
				}
				html.append("</tr>");
			}
		}
		html.append("<script language=\"javascript\">jQuery(\"#div_rs\").height(jQuery(\"#table_rs\").height()+50);</script>");
		return html.toString();
	}

	public void exportData(ServletOutputStream os,
			SzdwGeneralForm myForm, User user) throws Exception{
		// TODO �Զ����ɷ������
		List<HashMap<String, String>> list = dao.getBmqkList(myForm, user);
		String[] topTr;
		if(Base.xxdm.equals("12834")){
			topTr =new String[]{"��������","�༶����","�����ӳ��༶��","δ���ӳ��༶��","�����жӳ��༶��","δ���жӳ��༶��"};
		}else {
			topTr =new String[]{"��������","�༶����","���踨��Ա�༶��","δ�踨��Ա�༶��","��������ΰ༶��","δ������ΰ༶��"};
		}
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws=wwb.createSheet("�������ͳ��", 0);
		WritableCellFormat wcf=ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.LEFT, VerticalAlignment.CENTRE, true, Border.ALL);
		ws.setColumnView(0, 35);
		for(int i=0;i<topTr.length;i++){
			ws.addCell(new Label(i, 0, topTr[i], wcf));
		}
		if(list!=null&&list.size()>0){
			int[] ins=new int[]{0,0,0,0,0};
			for(int i=0;i<list.size();i++){
				HashMap<String, String> map = list.get(i);
				
				ws.addCell(new Label(0,(i+1),map.get("xymc"),wcf));
				ws.addCell(new Label(1,(i+1),map.get("bjdms"),wcf));
				ins[0] = ins[0]+Integer.valueOf(map.get("bjdms"));
				ws.addCell(new Label(2,(i+1),map.get("fdydbs"),wcf));
				ins[1] = ins[1]+Integer.valueOf(map.get("fdydbs"));
				ws.addCell(new Label(3,(i+1),map.get("mfdydbs"),wcf));
				ins[2] = ins[2]+Integer.valueOf(map.get("mfdydbs"));
				ws.addCell(new Label(4,(i+1),map.get("bzrdbs"),wcf));
				ins[3] = ins[3]+Integer.valueOf(map.get("bzrdbs"));
				ws.addCell(new Label(5,(i+1),map.get("mbzrdbs"),wcf));
				ins[4] = ins[4]+Integer.valueOf(map.get("mbzrdbs"));
			}
			ws.addCell(new Label(0,list.size()+1,"�ϼ�",wcf));
			for(int i=0;i<ins.length;i++){
				ws.addCell(new Label(i+1,list.size()+1,new Integer(ins[i]).toString(),wcf));
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	
	/**
	 * ����ͳ��--��ϸ ��Ϣ--����
	 * @param os
	 * @param myForm
	 * @throws Exception
	 */
	public void exportBmtjInfo(ServletOutputStream os,SzdwGeneralForm myForm) throws Exception{
		myForm.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String, String>> resultList = getBmtjInfo(myForm);
		String[] topTr={"רҵ����","רҵ����","�༶����","�༶����"};
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws=wwb.createSheet("�������ͳ��", 0);
		WritableCellFormat wcf=ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.LEFT, VerticalAlignment.CENTRE, true, Border.ALL);
		
		for(int i=0;i<topTr.length;i++){
			ws.setColumnView(i, 30);
			ws.addCell(new Label(i, 0, topTr[i], wcf));
		}
		if(resultList !=null && resultList.size() > 0){
			for(int i=0;i<resultList.size();i++){
				HashMap<String, String> map = resultList.get(i);
				
				ws.addCell(new Label(0,(i+1),map.get("zydm"),wcf));
				ws.addCell(new Label(1,(i+1),map.get("zymc"),wcf));
				ws.addCell(new Label(2,(i+1),map.get("bjdm"),wcf));
				ws.addCell(new Label(3,(i+1),map.get("bjmc"),wcf));
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * �㽭��ý���Ի�
	 * ��ศ��Աͳ��--��ϸ ��Ϣ--����
	 * @param os
	 * @param myForm
	 * @throws Exception
	 */
	public void exportFdyInfo(ServletOutputStream os,SzdwGeneralForm myForm) throws Exception{
		myForm.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String, String>> resultList = getFdyInfo(myForm);
		String[] topTr={"�꼶","�༶","����Ա","��λ���"};
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws=wwb.createSheet("����Ա���ͳ��", 0);
		WritableCellFormat wcf=ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.LEFT, VerticalAlignment.CENTRE, true, Border.ALL);
		
		for(int i=0;i<topTr.length;i++){
			ws.setColumnView(i, 30);
			ws.addCell(new Label(i, 0, topTr[i], wcf));
		}
		if(resultList !=null && resultList.size() > 0){
			for(int i=0;i<resultList.size();i++){
				HashMap<String, String> map = resultList.get(i);
				
				ws.addCell(new Label(0,(i+1),map.get("nj"),wcf));
				ws.addCell(new Label(1,(i+1),map.get("bjmc"),wcf));
				ws.addCell(new Label(2,(i+1),map.get("fdy"),wcf));
				ws.addCell(new Label(3,(i+1),map.get("yhsf"),wcf));
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * �㽭��ý���Ի�
	 * ��������ͳ��--��ϸ ��Ϣ--����
	 * @param os
	 * @param myForm
	 * @throws Exception
	 */
	public void exportBzrInfo(ServletOutputStream os,SzdwGeneralForm myForm) throws Exception{
		myForm.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String, String>> resultList = getBzrInfo(myForm);
		String[] topTr={"�꼶","�༶","������","��λ���"};
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws=wwb.createSheet("���������ͳ��", 0);
		WritableCellFormat wcf=ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.LEFT, VerticalAlignment.CENTRE, true, Border.ALL);
		
		for(int i=0;i<topTr.length;i++){
			ws.setColumnView(i, 30);
			ws.addCell(new Label(i, 0, topTr[i], wcf));
		}
		if(resultList !=null && resultList.size() > 0){
			for(int i=0;i<resultList.size();i++){
				HashMap<String, String> map = resultList.get(i);
				
				ws.addCell(new Label(0,(i+1),map.get("nj"),wcf));
				ws.addCell(new Label(1,(i+1),map.get("bjmc"),wcf));
				ws.addCell(new Label(2,(i+1),map.get("bzr"),wcf));
				ws.addCell(new Label(3,(i+1),map.get("yhsf"),wcf));
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ����ͳ�����--��ϸ��Ϣ
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String, String>> getBmtjInfo(SzdwGeneralForm myForm)throws Exception{
		
		String[] njArr = StringUtil.isNull(myForm.getNj()) ? new String[]{} : myForm.getNj().split("!!@!!");
		
		return dao.getBmtjInfo(myForm, njArr);
	}
	
	/**
	 * �㽭��ý���Ի�
	 * ���ศ��Աͳ�����--��ϸ��Ϣ
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String, String>> getFdyInfo(SzdwGeneralForm myForm)throws Exception{
		
		String[] njArr = StringUtil.isNull(myForm.getNj()) ? new String[]{} : myForm.getNj().split("!!@!!");
		
		return dao.getFdyInfo(myForm, njArr);
	}
	
	/**
	 * �㽭��ý���Ի�
	 * ���������ͳ�����--��ϸ��Ϣ
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String, String>> getBzrInfo(SzdwGeneralForm myForm)throws Exception{
		
		String[] njArr = StringUtil.isNull(myForm.getNj()) ? new String[]{} : myForm.getNj().split("!!@!!");
		
		return dao.getBzrInfo(myForm, njArr);
	}
}