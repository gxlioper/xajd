/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-5-16 ����05:06:23 
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
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: �������� ͳ�Ʋ�ѯ ѧԺ��ͳ�Ʋ�ѯ
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-5-16 ����05:09:00
 * @�汾�� V5.8.16
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class XyhjtjService {

	private XyhjtjDAO dao = new XyhjtjDAO();

	public List<List<String>> shxjxyCxdc(User user){
		MessageResources message = MessageResources
						.getMessageResources("config.ApplicationResources");
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		List<List<String>> dcList = new ArrayList<List<String>>();
		List<String> topStr = new ArrayList<String>();
		topStr.add(Base.YXPZXY_KEY +"����");
		topStr.add("רҵ����");
		topStr.add("�༶����");
		topStr.add("����");
		topStr.add("���п���");
		topStr.add("ѧ��");
		topStr.add("ѧ��");
		topStr.add("ѧ��");
		topStr.add("����ʱ��");
		List<String> pjlxList = dao.getPjlsxxb();
		topStr.addAll(9, pjlxList);
		topStr.add("���Ž��");
		dcList.add(topStr);
		List<List<String>> resultList = dao.getPjlsxxbTj(getOutValues(),user);
		dcList.addAll(resultList);
		return dcList;
	}

	private String[] getOutValues() {
		// ��װ�������
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
		// �����������Ϊ��������
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
	 * ��ͷ��Ԫ����ʽ���趨
	 */
	public WritableCellFormat getHeaderCellStyle() {

		/*
		 * WritableFont.createFont("����")����������Ϊ���� 10�����������С
		 * WritableFont.BOLD:��������Ӵ֣�BOLD���Ӵ� NO_BOLD�����Ӵ֣� false�����÷�б��
		 * UnderlineStyle.NO_UNDERLINE��û���»���
		 */
		WritableFont font = new WritableFont(WritableFont.createFont("����"), 10,
				WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);

		WritableCellFormat headerFormat = new WritableCellFormat(
				NumberFormats.TEXT);
		try {
			// �����������
			headerFormat.setFont(font);
			// ���õ�Ԫ�񱳾�ɫ����ͷΪ��ɫ
			headerFormat.setBackground(Colour.GRAY_25);
			// ���ñ�ͷ���߿���ʽ
			// ���������Ϊ���ߡ���ɫ
			headerFormat.setBorder(Border.ALL, BorderLineStyle.THIN,
					Colour.GRAY_50);
			// ��ͷ����ˮƽ������ʾ
			headerFormat.setAlignment(Alignment.CENTRE);
		} catch (WriteException e) {
			System.out.println("��ͷ��Ԫ����ʽ����ʧ�ܣ�");
		}
		return headerFormat;
	}

	/**
	 * ��ͷ��Ԫ����ʽ���趨
	 */
	public WritableCellFormat getBodyCellStyle() {

		/*
		 * WritableFont.createFont("����")����������Ϊ���� 10�����������С
		 * WritableFont.NO_BOLD:��������ǼӴ֣�BOLD���Ӵ� NO_BOLD�����Ӵ֣� false�����÷�б��
		 * UnderlineStyle.NO_UNDERLINE��û���»���
		 */
		WritableFont font = new WritableFont(WritableFont.createFont("����"), 10,
				WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE);

		WritableCellFormat bodyFormat = new WritableCellFormat(font);
		try {
			// ���õ�Ԫ�񱳾�ɫ������Ϊ��ɫ
			bodyFormat.setBackground(Colour.WHITE);
			// ���ñ�ͷ���߿���ʽ
			// ���������Ϊϸ�ߡ���ɫ
			bodyFormat
					.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);

		} catch (WriteException e) {
			System.out.println("���嵥Ԫ����ʽ����ʧ�ܣ�");
		}
		return bodyFormat;
	}
}
