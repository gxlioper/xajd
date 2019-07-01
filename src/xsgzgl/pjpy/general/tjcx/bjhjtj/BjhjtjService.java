package xsgzgl.pjpy.general.tjcx.bjhjtj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletOutputStream;

import org.apache.struts.util.MessageResources;

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
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xsgzgl.comm.BasicService;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: wujian
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-7-28 下午14:19:22
 * </p>
 */

public class BjhjtjService extends BasicService {
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");

	/**
	 * 学院获奖统计首页头部
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTopTr() {
		DAO dao = DAO.getInstance();
		String[] en = new String[] {};
		String[] cn = new String[] {};
		return dao.arrayToList(en, cn);
	}

	/**
	 * 学院获奖统计首页数据查询
	 * @param xmjhAll 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getBjhjtjCx(BjhjtjForm myForm, ArrayList<String[]> xmjhAll) throws Exception {
		BjhjtjDAO dao = new BjhjtjDAO();
		return dao.getBjhjtjCx(myForm,xmjhAll);
	}

	/**
	 * 学院获奖统计首页表单
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String createSearchHTML(BjhjtjForm myForm, SearchRsModel rsModel,
			ArrayList<String[]> rsArrList, ArrayList<String[]> xmjhAll, User user) throws Exception {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		String[] xmjh = myForm.getSearchModel().getSearch_tj_pjlsxm();
		if(xmjh==null){
			int xmjhLength = xmjhAll.size();
			xmjh = new String[xmjhLength];
			for(int i=0;i<xmjhLength;i++){
				xmjh[i]=xmjhAll.get(i)[0];
			}
		}
		if (rsArrList != null && rsArrList.size() > 0) {
			for(int i=0;i<(xmjh.length+2)*2;i++){
				html.append("<td align=\"middle\" nowrap=\"nowrap\" width=\"" + 100 / ((xmjh.length+2)*2-1) + "%\"></td> ");
			}
			html.append("</tr>");
			html.append("<thead align=\"center\"><td align=\"middle\" nowrap=\"nowrap\" height=\"28.5px\" rowspan=\"2\">班级</td>");
			for(String dqxm : xmjh){
				html.append("<td align=\"middle\" nowrap=\"nowrap\" height=\"28.5px\" colspan=\"2\">"+dqxm+"</td>");
			}
			html.append("<td align=\"middle\" nowrap=\"nowrap\" height=\"28.5px\" colspan=\"2\">合计</td></tr>");
			for(int i=0;i<=xmjh.length;i++){
				html.append("<td align=\"middle\" nowrap=\"nowrap\" height=\"28.5px\">人数</td>");
				html.append("<td align=\"middle\" nowrap=\"nowrap\" height=\"28.5px\">金额</td>");
			}
			html.append("</thead></tr>");
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				for (int j = 2; j < rs.length; j++) {
					html.append("<td align=\"middle\" nowrap=\"nowrap\" ");
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					html.append(replaceHtml(rs[j]));
//					if(j>1){
//						rsAll[j-2] =rsAll[j-2]+Integer.parseInt(rs[j]);
//					}
					html.append("</td>");
				}
				html.append("</tr>");
			}
//			html.append("<td align=\"middle\" nowrap=\"nowrap\" height=\"28.5px\" colspan=\"2\">总计</td>");
//			for (int j = 0; j < rsAll.length; j++) {
//				html.append("<td align=\"middle\" nowrap=\"nowrap\" height=\"28.5px\">"+ rsAll[j] +"</td>");
//			}
		}
		return html.toString();
	}

	/**
	 * 学院获奖统计首页导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void expBjhjtj(ServletOutputStream os, BjhjtjForm myForm) throws Exception {
		BjhjtjDAO dao = new BjhjtjDAO();
		String[] pjsj = myForm.getSearchModel().getSearch_tj_pjzq();
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		String title = Base.YXPZXY_KEY+"获奖统计";
		String tit=pjsj[0]+"学年学校奖学金分配表";
		ArrayList<String[]> xmjhAll2 = (ArrayList<String[]>) getXmjhAll(myForm);
		ArrayList<String[]> rsArrList = dao.getBjhjtjCx(myForm,xmjhAll2);
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(title, 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		Alignment alignMent = Alignment.CENTRE;
		VerticalAlignment vag = VerticalAlignment.CENTRE;
		//格式
		wcf2.setVerticalAlignment(vag);
		wcf2.setAlignment(alignMent);
		//字体
		WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
		wfTytle.setBoldStyle(WritableFont.BOLD);
		wfTytle.setPointSize(16);
		wcf2.setFont(wfTytle);
		ws.mergeCells(0, 0,2*xmjhAll2.size()+2,0 );
		ws.addCell(new Label(0, 0, tit, wcf2));
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		ws.mergeCells(0, 1, 0, 2);
		ws.addCell(new Label(0,1,"班级",wcf2));
//		ws.mergeCells(1, 1, 1, 2);
//		ws.addCell(new Label(1,1,"学院",wcf2));
		String[] xmjh = myForm.getSearchModel().getSearch_tj_pjlsxm();
		if(xmjh==null){
			int xmjhLength = xmjhAll2.size();
			xmjh = new String[xmjhLength];
			for(int i=0;i<xmjhLength;i++){
				xmjh[i]=xmjhAll2.get(i)[0];
			}
		}
		int i=1;
		int z=0;
		for(String dqxm : xmjh){
			ws.mergeCells((i*2)-1,1,(i*2),1);
			ws.addCell(new Label((i*2)-1,1,dqxm,wcf2));
			i++;
		}
		ws.mergeCells((i*2)-1,1,(i*2),1);
		ws.addCell(new Label((i*2)-1,1,"合计",wcf2));
		for(int j=0;j<=xmjh.length;j++){
			ws.addCell(new Label((j*2)+1,2,"人数",wcf2));
			ws.addCell(new Label((j*2)+2,2,"金额",wcf2));
		}
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int n = 0; n < rsArrList.size(); n++) {
				String[] info = rsArrList.get(n);
				if (info != null && info.length > 0) {
					for (int m = 2; m < info.length; m++) {
						ws.addCell(new Label(m-2, z + 3, info[m], wcf2));
					}
				}
				z++;
			}
//			ws.mergeCells(0,z+2,1,z+2);
//			ws.addCell(new Label(0,z+2,"总计",wcf2));
//			for (int j = 0; j < rsAll.length; j++) {
//				ws.addCell(new Label(j+2,z+2,String.valueOf(rsAll[j]),wcf2));
//			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	public ArrayList<String[]> getXmjhAll(BjhjtjForm myForm) throws Exception {
		BjhjtjDAO dao = new BjhjtjDAO();
		return dao.getXmjhAll(myForm);
	}
}