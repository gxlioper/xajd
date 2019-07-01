package com.zfsoft.xgxt.base.export.excel.template;

import java.util.List;

import jxl.Cell;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;

/**
 * Excelģ�彨����
 * 
 * @author JiangDong.Yi
 * 
 */
public class ImportTemplateBuilder extends ATemplateBuilder {
	public List<String[]> postilList;

	/**
	 * ��ʼ����ע�б�
	 * 
	 * @param postilList
	 */
	public ImportTemplateBuilder(List<String[]> postilList) {
		this.postilList = postilList;
	}

	/**
	 * �����ʽ
	 */
	public void buildFormat() throws Exception {
		// �õ�����
		int columnum = sheet.getColumns();
		// �õ�����
		int rownum = sheet.getRows();
		// ��Ԫ��
		Cell cell = null;

		// ��������
		WritableCellFormat wcf = new WritableCellFormat();
		WritableFont wf = new WritableFont(WritableFont.ARIAL);
		wf.setBoldStyle(WritableFont.BOLD);
		wf.setPointSize(10);
		wcf.setFont(wf);
		wcf.setAlignment(Alignment.CENTRE);
		wcf.setBackground(Colour.GREEN);

		// ѭ�����ж�д && i != 1 ����ģ����ֻ���ñ�ͷ��ʽ
		for (int i = 0; i < rownum && i != 1; i++) {
			for (int j = 0; j < columnum; j++) {
				cell = sheet.getCell(j, i);
				sheet.addCell(new Label(j, i, cell.getContents(), wcf));
			}
		}

	}

	/**
	 * ������ע
	 */
	public void buildPostil() throws Exception {
		if (postilList == null || postilList.size() == 0) {
			return;
		}
		WritableCellFeatures wcfeat = null;
		WritableCell wc = null;
		for (int i = 0; i < postilList.size(); i++) {

			for (int j = 0; j < postilList.get(i).length; j++) {
				if (postilList.get(i)[j] == null
						|| "".equals(postilList.get(i)[j])) {
					continue;
				}

				wcfeat = new WritableCellFeatures();
				wc = sheet.getWritableCell(j, i);
				wcfeat.setComment(postilList.get(i)[j]);
				// д����ע
				wc.setCellFeatures(wcfeat);
			}
		}
	}
}
