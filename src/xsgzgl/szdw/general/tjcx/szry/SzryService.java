package xsgzgl.szdw.general.tjcx.szry;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xsgzgl.szdw.general.SzdwGeneralForm;
import xsgzgl.szdw.general.inter.SzdwSzbbInterface;
import xsgzgl.szdw.general.inter.tjcx.TjcxSzryInterface;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 思政队伍_统计查询_思政人员_通用_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class SzryService extends CommService implements TjcxSzryInterface {

	SzryDAO dao = new SzryDAO();

	/**
	 * 构建思政人员统计HTML
	 * 
	 * @date 2013-01-11
	 * @author 伟大的骆
	 */
	public void createSzryStatDiv(SzdwGeneralForm myForm, SzryModel model,
			User user, HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=gbk");

		String html = new String();

		List<HashMap<String, String>> list = dao.getSzryList(myForm, model,
				user);
		String[] topTr;
		if(Base.xxdm.equals("12834")){
			topTr=new String[]{"所属部门","总人数","男","女","担任大队长人数","担任中队长人数","兼任院系人数"};
		}else {
			topTr=new String[]{"所属部门","总人数","男","女","担任辅导员人数","担任班主任人数","兼任院系人数"};
		}
		html=createHtml(topTr,list);
		response.getWriter().print(html.toString());
	}

	private String createHtml(String[] topTr, List<HashMap<String, String>> rsArrList) {
		// TODO 自动生成方法存根
		StringBuilder html = new StringBuilder();
		html.append("<h3 class=\"datetitle_01\">");
		html.append("<span id=\"span_note\" width=\"100%\">");
		html.append("统计结果&nbsp;&nbsp;");		
		html.append("</span>");
		html.append("</h3>");
		html.append("<span class=\"formbox\">");
		html.append("<table class=\"dateline\" width=\"100%\" id=\"table_rs\" style=\"\">");
		// =========================标题===========================
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
		if (rsArrList != null && rsArrList.size() > 0) {
			html.append("<tbody>");
			int[] hjzs = new int[]{0,0,0,0,0,0};
			for (int i = 0; i < rsArrList.size(); i++) {
				
				HashMap<String, String> map = rsArrList.get(i);
				
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

				//部门名称 
				html.append("<td class=\"nowrap\" title=\"").append(map.get("bmmc")).append("\">");
				if(map.get("bmmc").length()>16){
					html.append(map.get("bmmc").substring(0, 16)+"...");
				}else{
					html.append(map.get("bmmc"));
				}
				html.append("</td>");
				
				//总人数
				html.append("<td align=\"center\">")
					.append("<a class=\"name\" href=\"javascript:void(0);\"")
					.append(" onclick=\"showSzryInfo('general_szdw.do?method=getSzryInfo&bmdm=")
					.append(map.get("bmdm")).append("');\">");
				html.append(map.get("zrs"));
				html.append("</a></td>");
				
				hjzs[0] = hjzs[0]+Integer.valueOf(map.get("zrs"));
				
				//男
				html.append("<td align=\"center\">")
					.append("<a class=\"name\" href=\"javascript:void(0);\"")
					.append(" onclick=\"showSzryInfo('general_szdw.do?method=getSzryInfo&xb=1&bmdm=")
					.append(map.get("bmdm")).append("');\">");
				html.append(map.get("nans"));
				html.append("</a></td>");
				
				hjzs[1] = hjzs[1]+Integer.valueOf(map.get("nans"));
				
				//女
				html.append("<td align=\"center\">")
					.append("<a class=\"name\" href=\"javascript:void(0);\"")
					.append(" onclick=\"showSzryInfo('general_szdw.do?method=getSzryInfo&xb=2&bmdm=")
					.append(map.get("bmdm")).append("');\">");
				html.append(map.get("nvs"));
				html.append("</a></td>");
				
				hjzs[2] = hjzs[2]+Integer.valueOf(map.get("nvs"));
				
				//担任辅导员人数
				html.append("<td align=\"center\">")
					.append("<a class=\"name\" href=\"javascript:void(0);\"")
					.append(" onclick=\"showSzryInfo('general_szdw.do?method=getSzryInfo&fdydb=yes&bmdm=")
					.append(map.get("bmdm")).append("');\">");
				html.append(map.get("fdys"));
				html.append("</a></td>");
				
				hjzs[3] = hjzs[3]+Integer.valueOf(map.get("fdys"));
				
				//担任班主任人数
				html.append("<td align=\"center\">")
					.append("<a class=\"name\" href=\"javascript:void(0);\"")
					.append(" onclick=\"showSzryInfo('general_szdw.do?method=getSzryInfo&bzrdb=yes&bmdm=")
					.append(map.get("bmdm")).append("');\">");
				html.append(map.get("bzrs"));
				html.append("</a></td>");
				
				hjzs[4] = hjzs[4]+Integer.valueOf(map.get("bzrs"));
				
				//兼任院系数
				html.append("<td align=\"center\">")
					.append("<a class=\"name\" href=\"javascript:void(0);\"")
					.append(" onclick=\"showSzryInfo('general_szdw.do?method=getSzryInfo&jryx=true&bmdm=")
					.append(map.get("bmdm")).append("');\">");
				html.append(map.get("jrs"));
				html.append("</a></td>");
				
				hjzs[5] = hjzs[5]+Integer.valueOf(map.get("jrs"));
				
				html.append("</tr>");
			}
			html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
			html.append("<td align=\"left\" nowrap=\"nowrap\">");
			html.append("合计");
			html.append("</td>");
			
			html.append("<td align=\"center\">")
				.append("<a class=\"name\" href=\"javascript:void(0);\"")
				.append(" onclick=\"showSzryInfo('general_szdw.do?method=getSzryInfo');\">")
				.append(hjzs[0]).append("</a></td>");
			html.append("<td align=\"center\">")
				.append("<a class=\"name\" href=\"javascript:void(0);\"")
				.append(" onclick=\"showSzryInfo('general_szdw.do?method=getSzryInfo&xb=1');\">")
				.append(hjzs[1]).append("</a></td>");
			html.append("<td align=\"center\">")
				.append("<a class=\"name\" href=\"javascript:void(0);\"")
				.append(" onclick=\"showSzryInfo('general_szdw.do?method=getSzryInfo&xb=2');\">")
				.append(hjzs[2]).append("</a></td>");
			html.append("<td align=\"center\">")
				.append("<a class=\"name\" href=\"javascript:void(0);\"")
				.append(" onclick=\"showSzryInfo('general_szdw.do?method=getSzryInfo&fdydb=yes');\">")
				.append(hjzs[3]).append("</a></td>");
			html.append("<td align=\"center\">")
				.append("<a class=\"name\" href=\"javascript:void(0);\"")
				.append(" onclick=\"showSzryInfo('general_szdw.do?method=getSzryInfo&bzrdb=yes');\">")
				.append(hjzs[4]).append("</a></td>");
			html.append("<td align=\"center\">")
				.append("<a class=\"name\" href=\"javascript:void(0);\"")
				.append(" onclick=\"showSzryInfo('general_szdw.do?method=getSzryInfo&jryx=true');\">")
				.append(hjzs[5]).append("</a></td>");
			
			html.append("</tr>");
		}else{
		html.append("</tbody>");
			for(int i=0;i<11;i++){
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				for (int j = 0; j < topTr.length; j++) {
					html.append("<td height=\"29px\" align=\"left\" nowrap=\"nowrap\"></td>");
				}
				html.append("</tr>");
			}
		}
		html.append("<script language=\"javascript\">jQuery(\"#div_rs\").height(jQuery(\"#table_rs\").height()+50);</script>");
		return html.toString();
	}
	
	public void exportData(ServletOutputStream os, SzdwGeneralForm myForm,User user) throws Exception{
		SzryModel model = new SzryModel();
		List<HashMap<String, String>> list = dao.getSzryList(myForm, model,
				user);
		String[] topTr;
		if(Base.xxdm.equals("12834")){
			topTr=new String[]{"所属部门","总人数","男","女","担任大队长人数","担任中队长人数","兼任院系人数"};
		}else {
			topTr=new String[]{"所属部门","总人数","男","女","担任辅导员人数","担任班主任人数","兼任院系人数"};
		}
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws=wwb.createSheet("思政人员统计", 0);
		WritableCellFormat wcf=ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.LEFT, VerticalAlignment.CENTRE, true, Border.ALL);
		ws.setColumnView(0, 40);
		for(int i=0;i<topTr.length;i++){
			ws.addCell(new Label(i, 0, topTr[i], wcf));
		}
		if(list!=null&&list.size()>0){
			int[] hjzs = new int[]{0,0,0,0,0,0};
			
			for(int i=0;i<list.size();i++){
				
				HashMap<String, String> map = list.get(i);
				
				ws.addCell(new Label(0,(i+1),map.get("bmmc"),wcf));
				ws.addCell(new Label(1,(i+1),map.get("zrs"),wcf));
				hjzs[0] = hjzs[0]+Integer.valueOf(map.get("zrs"));
				
				ws.addCell(new Label(2,(i+1),map.get("nans"),wcf));
				hjzs[1] = hjzs[1]+Integer.valueOf(map.get("nans"));
				
				ws.addCell(new Label(3,(i+1),map.get("nvs"),wcf));
				hjzs[2] = hjzs[2]+Integer.valueOf(map.get("nvs"));
				
				ws.addCell(new Label(4,(i+1),map.get("fdys"),wcf));
				hjzs[3] = hjzs[3]+Integer.valueOf(map.get("fdys"));
				
				ws.addCell(new Label(5,(i+1),map.get("bzrs"),wcf));
				hjzs[4] = hjzs[4]+Integer.valueOf(map.get("bzrs"));
				
				ws.addCell(new Label(6,(i+1),map.get("jrs"),wcf));
				hjzs[5] = hjzs[5]+Integer.valueOf(map.get("jrs"));
				
			}
			ws.addCell(new Label(0,list.size()+1,"合计",wcf));
			for(int i=0;i<hjzs.length;i++){
				ws.addCell(new Label(i+1,list.size()+1,String.valueOf(hjzs[i]),wcf));
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	
	/**
	 * 思政人员统计--详细数据
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String, String>> getSzryInfo(SzdwGeneralForm myForm)throws Exception{
		
		return dao.getSzryInfo(myForm);
	}
	
	
	/**
	 * 思政人员统计--详细信息--导出
	 * @param os
	 * @param myForm
	 * @throws Exception
	 */
	public void exportSzrytjInfo(ServletOutputStream os,SzdwGeneralForm myForm) throws Exception{
		myForm.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String, String>> resultList = getSzryInfo(myForm);
		String[] topTr={"职工号","姓名","部门代码","部门名称","联系电话"};
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws=wwb.createSheet("思政人员统计", 0);
		WritableCellFormat wcf=ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.LEFT, VerticalAlignment.CENTRE, true, Border.ALL);
		
		for(int i=0;i<topTr.length;i++){
			ws.setColumnView(i, 30);
			ws.addCell(new Label(i, 0, topTr[i], wcf));
		}
		if(resultList !=null && resultList.size() > 0){
			for(int i=0;i<resultList.size();i++){
				HashMap<String, String> map = resultList.get(i);
				
				ws.addCell(new Label(0,(i+1),map.get("zgh"),wcf));
				ws.addCell(new Label(1,(i+1),map.get("xm"),wcf));
				ws.addCell(new Label(2,(i+1),map.get("bmdm"),wcf));
				ws.addCell(new Label(3,(i+1),map.get("bmmc"),wcf));
				ws.addCell(new Label(4,(i+1),map.get("lxdh"),wcf));
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
}