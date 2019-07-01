package xsgzgl.pjpy.general.tjcx.hjmdtj;

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

public class HjmdtjService extends BasicService {
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");

	/**
	 * 获奖名单统计首页头部
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
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		String[] en = new String[] {"xh","xm","sfzh","nj","xymc","zymc","bjmc","xb","mzmc","rxrq","xmmc","xmje","yhkh"};
		String[] cn = new String[] {"学号","姓名","身份证","年级",Base.YXPZXY_KEY,"专业","班级","性别","民族","入学日期","获奖项目","金额","卡号"};
		return dao.arrayToList(en, cn);
	}
	

	/**
	 * 获奖名单统计首页数据查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getHjmdtjCx(HjmdtjForm myForm) throws Exception {
		HjmdtjDAO dao = new HjmdtjDAO();
		return dao.getHjmdtjCx(myForm);
	}
	
	/**
	 * 获奖名单统计首页数据查询(合计)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public String getHjmdtjCxAll(HjmdtjForm myForm) throws Exception {
		HjmdtjDAO dao = new HjmdtjDAO();
		return dao.getHjmdtjCxAll(myForm);
	}

	/**
	 * 获奖名单统计首页表单
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String createSearchHTML(HjmdtjForm myForm, SearchRsModel rsModel,
			ArrayList<String[]> rsArrList,String title, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		//html.append("<td align=\"middle\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">"+title+"</td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
		if (rsArrList != null && rsArrList.size() > 0) {
			//html.append("<thead align=\"center\"><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">学号</td><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">姓名</td><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">身份证</td><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">专业</td><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">性别</td><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">民族</td><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">入学日期</td><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">获奖项目</td><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">金额</td><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">银行卡号</td></thead></tr>");
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				for (int j = 0; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length) + "%\" ");
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

	/**
	 * 获奖名单统计首页数据导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void expHjmdtj(ServletOutputStream os, HjmdtjForm myForm,String xmmcrs) throws Exception {
		HjmdtjDAO dao = new HjmdtjDAO();
		String title = xmmcrs;
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		// 固定列
		String[] gdName = new String[] {"学号","姓名","身份证","年级",Base.YXPZXY_KEY,"专业","班级","性别","民族","入学日期","获奖项目","金额","卡号"};
		// 导出报表表头
		List<HashMap<String, String>> topTr = getTopList(gdName);
		// 导出报表的固定数据
		
		//------2013-3-22 edit--qph--begin---------------
		//to原作者：导出数据调查询数据结果集的方式可赞，但分页就是你的不对了。
		myForm.getPages().setPageSize(Integer.MAX_VALUE);
		//------2013-3-22 edit---qph--end--------------
		
		ArrayList<String[]> gdlist = dao.getHjmdtjCx(myForm);

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableCellFormat wcFormate=new WritableCellFormat();
		Alignment alignMent = Alignment.CENTRE;
		VerticalAlignment vag = VerticalAlignment.CENTRE;
		//格式
		wcFormate.setVerticalAlignment(vag);
		wcFormate.setAlignment(alignMent);
		//字体
		WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
		wfTytle.setBoldStyle(WritableFont.BOLD);
		wfTytle.setPointSize(16);
		wcFormate.setFont(wfTytle);
		WritableSheet ws = wwb.createSheet(title, 0);
		ws.mergeCells(0, 0,gdName.length-1,0 );
		ws.addCell(new Label(0, 0, title, wcFormate));
		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		if (topTr != null && topTr.size() > 0) {
			for (int i = 0; i < topTr.size(); i++) {
				HashMap<String, String> map = topTr.get(i);
				ws.setColumnView(i, 25);
				ws.addCell(new Label(i, 1, map.get("cn"), wcf2));
			}
		}
		// 填充内容
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
	
	/**
	 * 获得评奖历史信息中默认第一个作为首次查询的条件：评奖项目
	 * @return
	 */
	public String getPjlsxmMrtj(){
		HjmdtjDAO dao = new HjmdtjDAO();
		return dao.getPjlsxmMrtj();
	}
	
	/**
	 * 获得评奖历史信息中默认第一个作为首次查询的条件：评奖时间
	 * @return
	 */
	public String getPjzqMrtj(){
		HjmdtjDAO dao = new HjmdtjDAO();
		return dao.getPjzqMrtj();
	}
}