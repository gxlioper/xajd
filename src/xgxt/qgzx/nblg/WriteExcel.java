
package xgxt.qgzx.nblg;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;

public class WriteExcel  {
	
	/**
	 * �ڵ�һ�д�ӡ����
	 * @param ws     ָ����д�Ĺ�����
	 * @param title   ����
	 * @param cols    һ���ϲ�������
	 * @return
	 */
	public void printTitle(WritableSheet ws,String title,int cols) throws Exception {
		WritableCellFormat wcf = new WritableCellFormat();
		WritableFont wf = new WritableFont(WritableFont.ARIAL);
		wf.setBoldStyle(WritableFont.BOLD);
		wf.setPointSize(18);
		wcf.setAlignment(Alignment.CENTRE);
		wcf.setWrap(true);
		wcf.setFont(wf);
		ws.mergeCells(0, 0, cols-1, 0);
		ws.addCell(new Label(0,0,title,wcf));
	}
	
	/**
	 * @param ws       ��д��sheet
	 * @param content  ���������
	 * @param cols     ָ������
	 * @param column   ָ��д�����ݵ���
	 * @param row      ָ��д�����ݵ���
	 */
	public void printJxjmc(WritableSheet ws,String content,int cols,int column,int row) throws Exception {
		WritableCellFormat wcf = new WritableCellFormat();
		WritableFont wf = new WritableFont(WritableFont.ARIAL);
		wf.setBoldStyle(WritableFont.BOLD);
		wf.setPointSize(14);
		wcf.setAlignment(Alignment.LEFT);
		wcf.setWrap(true);
		wcf.setFont(wf);
		for(int i=0;i<cols;i++){
			ws.addCell(new Label(i,row," "));
		}
		ws.mergeCells(0, row, cols-1, row);
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
	
	//����¼��
	public void printToOneCell(WritableSheet ws,String content,int column,int row) throws Exception {
		WritableCellFormat wcf = new WritableCellFormat();
		WritableFont wf = new WritableFont(WritableFont.ARIAL);
		wf.setPointSize(14);
		wcf.setAlignment(Alignment.CENTRE);
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
		wcf.setLocked(true);
		wcf.setFont(wf);
		if(border){
			wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
		}
		wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
		ws.addCell(new Label(column,row,content,wcf));
	}
}

