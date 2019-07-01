/**
 * @部门:学工产品事业部
 * @日期：2013-5-16 下午05:06:23 
 */
package com.zfsoft.xgxt.pjpy.tjcx;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.util.MessageResources;

import xgxt.action.Base;
import xgxt.form.User;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WriteException;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 评奖评优 统计查询 学院获奖统计查询
 * @作者： zhangjw
 * @时间： 2013-5-16 下午05:09:00
 * @版本： V5.8.16
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class XyhjtjService {

	private XyhjtjDAO dao = new XyhjtjDAO();

	public List<List<String>> shxjxyCxdc(User user){
		MessageResources message = MessageResources
						.getMessageResources("config.ApplicationResources");
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		List<List<String>> dcList = new ArrayList<List<String>>();
		List<String> topStr = new ArrayList<String>();
		topStr.add(Base.YXPZXY_KEY +"名称");
		topStr.add("专业名称");
		topStr.add("班级名称");
		topStr.add("姓名");
		topStr.add("银行卡号");
		topStr.add("学年");
		topStr.add("学期");
		topStr.add("学号");
		topStr.add("发放时间");
		List<String> pjlxList = dao.getPjlsxxb();
		topStr.addAll(9, pjlxList);
		topStr.add("发放金额");
		dcList.add(topStr);
		List<List<String>> resultList = dao.getPjlsxxbTj(getOutValues(),user);
		dcList.addAll(resultList);
		return dcList;
	}

	private String[] getOutValues() {
		// 封装输出参数
		String[] outValues = { "xymc", "zymc", "bjmc", "xm", "yhkh", "xn",
				"xq", "xh", "hdsj" };
		List<String> outv = new ArrayList<String>();
		for (String val : outValues) {
			outv.add(val);
		}
		List<String> pjlxList = dao.getPjlsxxb();
		outv.addAll(pjlxList);
		outv.add("xmje");
		String[] st = new String[] {};
		// 接收输出参数为数组类型
		st = outv.toArray(st);
		return st;
	}

	public WritableCellFormat getTopCellStyle(WritableSheet ws) {
		WritableCellFormat tempCellFormat = getHeaderCellStyle();
		try {
			tempCellFormat.setAlignment(Alignment.CENTRE);
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return tempCellFormat;
	}

	/**
	 * 表头单元格样式的设定
	 */
	public WritableCellFormat getHeaderCellStyle() {

		/*
		 * WritableFont.createFont("宋体")：设置字体为宋体 10：设置字体大小
		 * WritableFont.BOLD:设置字体加粗（BOLD：加粗 NO_BOLD：不加粗） false：设置非斜体
		 * UnderlineStyle.NO_UNDERLINE：没有下划线
		 */
		WritableFont font = new WritableFont(WritableFont.createFont("宋体"), 10,
				WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);

		WritableCellFormat headerFormat = new WritableCellFormat(
				NumberFormats.TEXT);
		try {
			// 添加字体设置
			headerFormat.setFont(font);
			// 设置单元格背景色：表头为黄色
			headerFormat.setBackground(Colour.GRAY_25);
			// 设置表头表格边框样式
			// 整个表格线为粗线、黑色
			headerFormat.setBorder(Border.ALL, BorderLineStyle.THIN,
					Colour.GRAY_50);
			// 表头内容水平居中显示
			headerFormat.setAlignment(Alignment.CENTRE);
		} catch (WriteException e) {
			System.out.println("表头单元格样式设置失败！");
		}
		return headerFormat;
	}

	/**
	 * 表头单元格样式的设定
	 */
	public WritableCellFormat getBodyCellStyle() {

		/*
		 * WritableFont.createFont("宋体")：设置字体为宋体 10：设置字体大小
		 * WritableFont.NO_BOLD:设置字体非加粗（BOLD：加粗 NO_BOLD：不加粗） false：设置非斜体
		 * UnderlineStyle.NO_UNDERLINE：没有下划线
		 */
		WritableFont font = new WritableFont(WritableFont.createFont("宋体"), 10,
				WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE);

		WritableCellFormat bodyFormat = new WritableCellFormat(font);
		try {
			// 设置单元格背景色：表体为白色
			bodyFormat.setBackground(Colour.WHITE);
			// 设置表头表格边框样式
			// 整个表格线为细线、黑色
			bodyFormat
					.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);

		} catch (WriteException e) {
			System.out.println("表体单元格样式设置失败！");
		}
		return bodyFormat;
	}
}
