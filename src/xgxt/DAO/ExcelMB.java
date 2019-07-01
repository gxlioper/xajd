/**
 * 
 */
package xgxt.DAO;

import java.util.List;

import xgxt.utils.String.StringUtils;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * @author Administrator
 *
 */
public class ExcelMB {
	/**
	 * �ڵ�һ�д�ӡ����
	 * @param ws     ָ����д�Ĺ�����
	 * @param title   ����
	 * @param cols    һ���ϲ�������
	 * @param cols    �и߶�
	 * @return
	 */
	public void printTitle(WritableSheet ws,String title,int cols,int hight) throws Exception {
		WritableCellFormat wcf = new WritableCellFormat();
		WritableFont wf = new WritableFont(WritableFont.ARIAL);
		wf.setBoldStyle(WritableFont.BOLD);
		wf.setPointSize(18);
		wcf.setAlignment(Alignment.CENTRE);
		wcf.setWrap(true);
		wcf.setFont(wf);
		ws.mergeCells(0, 0, cols-1, 0); 
		ws.setRowView(0, hight); // ����ָ���и�
		ws.addCell(new Label(0,0,title,wcf));
	}
	
	/**
	 * @param ws       ��д��sheet
	 * @param content  ���������
	 * @param cols     ָ������
	 * @param column   ָ��д�����ݵ���
	 * @param row      ָ��д�����ݵ���
	 * @param pontSize �����С
	 * @param bold     �Ƿ����
	 * @param align    ��Ԫ����λ�ã����ҡ����У�
	 * @param br       �Ƿ��Զ�����
	 */
	public void printThead(WritableSheet ws,String content,int cols,int column,int row,int pontSize,boolean bold ,Alignment align,boolean br) throws Exception {
		WritableCellFormat wcf = new WritableCellFormat();
		WritableFont wf = new WritableFont(WritableFont.ARIAL);
		if(bold){
		wf.setBoldStyle(WritableFont.BOLD);
		}
		wf.setPointSize(pontSize);
		wcf.setAlignment(Alignment.LEFT);
		wcf.setWrap(true);
		wcf.setFont(wf);
		ws.mergeCells(column, row, column+cols-1, row);
		ws.addCell(new Label(column,row,content));
		ws.addCell(new Label(column,row,content,wcf));
	}
	

	public void unitiveFormat(WritableSheet ws) throws Exception {
		WritableCellFormat wcf = new WritableCellFormat();
		WritableFont wf = new WritableFont(WritableFont.ARIAL);
		wf.setBoldStyle(WritableFont.BOLD);
		wf.setPointSize(14);
		wcf.setAlignment(Alignment.LEFT);
		wcf.setWrap(true);
		wcf.setFont(wf);		
	}
	
	/**
	 * @param ws       ��д��sheet
	 * @param content  ���������
	 * @param column   ָ��д�����ݵ���
	 * @param row      ָ��д�����ݵ���
	 * @param pontSize �����С
	 * @param bold     �Ƿ����
	 * @param align    ��Ԫ����λ�ã����ҡ����У�
	 * @param br       �Ƿ��Զ�����
	 */

	public void printToOneCell(WritableSheet ws,String content,int column,int row,int pontSize,boolean bold ,Alignment align,boolean br) throws Exception {
		WritableCellFormat wcf = new WritableCellFormat();
		WritableFont wf = new WritableFont(WritableFont.ARIAL);
		if(bold){
			wf.setBoldStyle(WritableFont.BOLD);
		}
		wf.setPointSize(pontSize);
		wcf.setAlignment(Alignment.LEFT);
		wcf.setWrap(true);		
		wcf.setFont(wf);
		ws.addCell(new Label(column,row,content,wcf));
	}
	//����¼��
	public void printToOneCell_self(WritableSheet ws,String content,int column,int row,int fontsize,boolean warp,boolean border) throws Exception {
		WritableCellFormat wcf = new WritableCellFormat();
		WritableFont wf = new WritableFont(WritableFont.ARIAL);
		wf.setPointSize(fontsize);
		wcf.setAlignment(Alignment.CENTRE);
		wcf.setWrap(warp);
		wcf.setLocked(false);
		wcf.setFont(wf);
		if(border){
			wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
		}
		wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
		ws.addCell(new Label(column,row,content,wcf));
	}
	
	/**
	 * @param ws
	 *            ��д��sheet
	 * @param content
	 *            ���������
	 * @param column
	 *            ָ��д�����ݵ���
	 * @param row
	 *            ָ��д�����ݵ���
	 * @param pontSize
	 *            �����С
	 * @param bold
	 *            �Ƿ����
	 * @param align
	 *            ��Ԫ����λ�ã����ҡ����У�
	 * @param valign
	 *            ��Ԫ��������λ�ã��ϡ��¡����У�
	 * @param wrap
	 *            �Ƿ��Զ�����
	 * @param cols
	 *            �и߶�
	 * @param border
	 *            �߿�
	 */

	public void printToOneCell_multy(WritableSheet ws, String content,
			int column, int row, int pontSize, boolean bold, Alignment align,
			VerticalAlignment valign,
			boolean wrap, int hight, Border border) throws Exception {
		WritableCellFormat wcf = new WritableCellFormat();
		WritableFont wf = new WritableFont(WritableFont.ARIAL);
		if (bold) {
			wf.setBoldStyle(WritableFont.BOLD);
		}
		wcf.setBorder(border, BorderLineStyle.THIN);
		wf.setPointSize(pontSize);
		wcf.setAlignment(align);
		wcf.setVerticalAlignment(valign);
		wcf.setWrap(true);
		wcf.setFont(wf);
		ws.setRowView(0, hight); // ����ָ���и�
		ws.addCell(new Label(column, row, content, wcf));
	}
	
	/**
	 * ������ز������ص�Ԫд����ʽ
	 * @param pontSize  �����С
	 * @param bold  �Ƿ�Ӵ�
	 * @param align ��Ԫ�����Ҷ���
	 * @param valign ��Ԫ�����¶���
	 * @param border �Ƿ���ʾ�߿�
	 * @return
	 * @throws Exception
	 */
	public static WritableCellFormat getWritableCellFormat(int pontSize,
			boolean bold, Alignment align, VerticalAlignment valign,
			Border border) throws Exception {
		WritableCellFormat wcf = new WritableCellFormat();
		WritableFont wf = new WritableFont(WritableFont.ARIAL);
		if (bold) {
			wf.setBoldStyle(WritableFont.BOLD);
		}
		wcf.setBorder(border, BorderLineStyle.THIN);
		wf.setPointSize(pontSize);
		wcf.setAlignment(align);
		wcf.setVerticalAlignment(valign);
		wcf.setWrap(true);
		wcf.setFont(wf);
		
		return wcf;
	}
	
	/**
	 * д���ͷ��EXCEL���У�ֻ�����ڵ�һ������
	 * @param ws  Ҫ�����EXCEL��Ԫ
	 * @param title Ҫ����ı�ͷ�ַ���
	 * @param x  Ҫ�����X������λ��
	 * @param y  Ҫ�����Y������λ��
	 * @param unionX Ҫ�ϲ���X������
	 * @param unionY Ҫ�ϲ���Y������
	 * @param format  ���������ʽ
	 */
	public static void writeTitleToCell(WritableSheet ws, String title, int x,
			int y, int unionX, int unionY, WritableCellFormat format)
			throws Exception {
		if (StringUtils.isNotNull(title)) {
			ws.addCell(new Label(x,y,title,format));
			ws.mergeCells(0, 0, unionX, unionY);
		}
	}
	
	/**
	 * д���ͷ��EXCEL����,ֻ�����ڵڶ�����ͷ  ��ͷ���ݱ���Ϊ����
	 * @param ws  Ҫ�����EXCEL��Ԫ
	 * @param title Ҫ����ı�ͷ�ַ���
	 * @param x  Ҫ�����X������λ��
	 * @param y  Ҫ�����Y������λ��
	 * @param format  ���������ʽ
	 */
	public static void writeEjTitleToCell(WritableSheet ws, String[] title,
			int x, int y, WritableCellFormat format) {
		try {
			if (title != null && title.length > 0) {
				// ѭ��д��ÿһ����Ԫ����
				for (int i = 0; i < title.length; i++) {
					ws.addCell(new Label(x + i, y, title[i], format));
					ws.setColumnView(x + i, StringUtils.isNull(title[i]) ? 6
							: title[i].length() * 2);
				}
			}
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ѭ�������ѯ���ݵ�EXCEL���У�ֻ�����й����ѭ�� X��Ĭ�ϴ�0��ʼ���
	 * @param ws  Ҫ�����EXCEL��Ԫ
	 * @param data  ��������ݣ���ʽ������ List<String[]>
	 * @param y  ������и�
	 * @param format  ���������ʽ
	 * @throws Exception
	 */
	public static void writeDataToCellByIterator(WritableSheet ws,
			List<String[]> data, int y, WritableCellFormat format)
			throws Exception {
		if (data != null && !data.isEmpty()) {
			for (int i = 0; i < data.size(); i++) {
				String[] oneRs = data.get(i);
				int k = 0;
				if (oneRs != null && oneRs.length > 0) {
					for (String value : oneRs) {
						ws.addCell(new Label(k, i + y, value, format));
						//�����п�
						ws.setColumnView(k, (StringUtils.isNull(value) ? 6
								: value.length()) * 3);
						k++;
					}
				}
			}
		}
	}
	
	/**
	 * ѭ�������ѯ���ݵ�EXCEL���У�ֻ�����й����ѭ��
	 * @param ws  Ҫ�����EXCEL��Ԫ
	 * @param data  ��������ݣ���ʽ������ List<String[]>
	 * @param x �����X��ʼλ��
	 * @param y  ������и�
	 * @param format  ���������ʽ
	 * @throws Exception
	 */
	public static void writeDataToCellByIterator(WritableSheet ws,
			List<String[]> data, int x, int y, WritableCellFormat format)
			throws Exception {
		if (data != null && !data.isEmpty()) {
			for (int i = 0; i < data.size(); i++) {
				String[] oneRs = data.get(i);
				int k = x;
				if (oneRs != null && oneRs.length > 0) {
					for (String value : oneRs) {
						ws.addCell(new Label(k, i + y, value, format));
						//�����п�
						k++;
					}
				}
			}
		}
	}
	
	/**
	 * ѭ�������ѯ���ݵ�EXCEL���У�ֻ�����й����ѭ��
	 * @param ws  Ҫ�����EXCEL��Ԫ
	 * @param data  ��������ݣ���ʽ������ List<String[]>
	 * @param dataLen Ҫ���ÿһ�����ݵĳ���
	 * @param x �����X��ʼλ��
	 * @param y  ������и�
	 * @param format  ���������ʽ
	 * @throws Exception
	 */
	public static void writeDataToCellByIterator(WritableSheet ws,
			List<String[]> data, int dataLen,int x, int y, WritableCellFormat format)
			throws Exception {
		if (data != null && !data.isEmpty()) {
			for (int i = 0; i < data.size(); i++) {
				String[] oneRs = data.get(i);
				int k = x;
				if (oneRs != null && oneRs.length > 0 && dataLen < oneRs.length) {
					for (int l=0;l<dataLen;l++) {
						ws.addCell(new Label(k, i + y, oneRs[l], format));
						//�����п�						
						k++;
					}
				}
			}
		}
	}
	
	/**
	 * �ϲ������ĵ�Ԫ������
	 * @param rs  ���ݼ�
	 * @param ws  ��Ԫ��
	 * @param col   �����
	 * @param row   �����
	 * @param format ��ʽ
	 * @throws Exception
	 */
	public static void mergeCellsData(List<String[]> rs, WritableSheet ws, int col,
			int row, WritableCellFormat format) throws Exception{
		int hbhs = 1;//�ϲ�����
		boolean bz = false;//�ϲ���־
		if (rs != null && !rs.isEmpty()) {
			for (int i = 0; i <= rs.size(); i++) {
				String currCellStr = "";//��ǰ��Ԫ���ַ���
				String beforeCellStr = "";//��һ��Ԫ���ַ���
				WritableCell currCell = ws.getWritableCell(col, row + i);
				currCellStr = currCell.getContents();
				
				if (i > 0) {
					WritableCell beforeCell = ws.getWritableCell(col, row - 1 + i);
					beforeCellStr = beforeCell.getContents();
				}
				
				if (currCellStr.equals(beforeCellStr)) {
					hbhs++;
					bz = true;
				}
				//�ж��Ƿ�úϲ�
				if ((!currCellStr.equals(beforeCellStr)) && bz) {
					String data = (rs.get(i-1) != null && rs.get(i-1).length > col) ? rs.get(i-1)[col] : "";
					ws.addCell(new Label(col, row + i - hbhs, data, format));
					ws.mergeCells(col, row + i - hbhs, col, row + i - 1);
					// ��Ԫ��ϲ������ò���
					hbhs = 1;
					bz = false;
				}
			}
		}
	}
	
	/**
	 * �ϲ���Ԫ��
	 * @param ws Excelģ��
	 * @param size ��Ҫ�ϲ���������
	 * @param col �ڼ�����Ԫ��
	 * @param row �ڼ�����
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public static void mergeCells(WritableSheet ws,int size,int col,int row) 
									throws RowsExceededException, WriteException {
		
		int count = 1;
		boolean isHb = false;
		
		for ( int i = 0 ; i < size ; i++) {
			WritableCell currCell = ws.getWritableCell(col, row+i );//��ǰ��Ԫ��
			WritableCell beforeCell = ws.getWritableCell(col, row+i - 1);//��һ��Ԫ��
			String currCellStr = currCell.getContents();
			String beforeCellStr = beforeCell.getContents();
			
			if (currCellStr.equals(beforeCellStr)) {
				count++;
				isHb = true;
			}
			
			//�ж��Ƿ�úϲ�
			if ((!currCellStr.equals(beforeCellStr)) && isHb) {
				ws.mergeCells(col, row + i - count, col, row + i - 1);
				// ��Ԫ��ϲ������ò���
				count = 1;
				isHb = false;
			}
		}
	}
}
