/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-24 ����05:26:45 
 */  
package com.zfsoft.xgxt.rcsw.yxybgl.jg;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-3-24 ����05:26:45 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YxybjgService extends SuperServiceImpl<YxybjgForm, YxybjgDao>{
	private YxybjgDao dao = new YxybjgDao();
	
	public boolean isHaveRecordForjg(YxybjgForm form){
		return dao.isHaveRecordForjg(form);
	}
	
	public boolean saveYxybjg(YxybjgForm model) throws Exception {
		boolean result = false;
		if ("save".equals(model.getType())) {
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
		}
		return result;
	}
	
	public Map<String,String> getJgxx(YxybjgForm form){
		return dao.getJgxx(form);
	}
	
	/** 
	 * @����:��ȡ�ϲ�����list(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-30 ����04:58:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getHbdcList(YxybjgForm t, User user) throws Exception{
		return dao.getHbdcList(t, user);
	}
	
	/** 
	 * @����:��ȡ�ϲ�excel����ļ�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-30 ����04:59:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param list
	 * @return
	 * @throws IOException
	 * @throws WriteException
	 * File �������� 
	 * @throws 
	 */
	public File getHbExecl(List<HashMap<String,String>> list) throws IOException, WriteException{
		File file = new File(System.getProperty("java.io.tmpdir"),System.currentTimeMillis()+".xls");
		if(!file.exists()){
			file.createNewFile();
		}
		if(null != list && list.size()>0){			
			WritableWorkbook wwb = Workbook.createWorkbook(file);
			WritableSheet sheet = wwb.createSheet("sheet1", 0);
			WritableFont headFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);
			WritableFont bodyFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false,UnderlineStyle.NO_UNDERLINE);
			WritableCellFormat head_cf = new WritableCellFormat(headFont);//�������ı�ͷ����
			WritableCellFormat body_cf = new WritableCellFormat(bodyFont);//�������ĵ�Ԫ������
			head_cf.setAlignment(jxl.format.Alignment.CENTRE);
			head_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			body_cf.setAlignment(jxl.format.Alignment.LEFT);
			body_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			body_cf.setWrap(true);
			head_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
			body_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
			sheet.setColumnView(0, 12);
			sheet.setColumnView(1, 12);
			sheet.setColumnView(2, 16);
			sheet.setColumnView(3, 12);
			sheet.setColumnView(4, 36);
			sheet.setColumnView(5, 36);
			sheet.setColumnView(6, 36);
			sheet.setColumnView(7, 36);
			sheet.setColumnView(8, 12);
			sheet.setColumnView(9, 16);
			Label label_title1 = new Label(0, 0,"ѧ��",head_cf);
			Label label_title2 = new Label(1, 0,"ѧ��",head_cf);
			Label label_title3 = new Label(2, 0,"ѧԺ",head_cf);
			Label label_title4 = new Label(3, 0,"�·�",head_cf);
			Label label_title5 = new Label(4, 0,"���¹�����չ���",head_cf);
			Label label_title6 = new Label(5, 0,"ѧ����ע�ȵ�",head_cf);
			Label label_title7 = new Label(6, 0,"ѧ��˼�붯̬",head_cf);
			Label label_title8 = new Label(7, 0,"ѧ�����󼰹�������",head_cf);
			Label label_title9 = new Label(8, 0,"��д��",head_cf);
			Label label_title10 = new Label(9, 0,"��дʱ��",head_cf);
			sheet.addCell(label_title1);
			sheet.addCell(label_title2);
			sheet.addCell(label_title3);
			sheet.addCell(label_title4);
			sheet.addCell(label_title5);
			sheet.addCell(label_title6);
			sheet.addCell(label_title7);
			sheet.addCell(label_title8);
			sheet.addCell(label_title9);
			sheet.addCell(label_title10);
			String xymcbz = null;//ÿ��ѭ��Ҫ�Ƚϵ�ѧԺ���ƣ���һ�����ݵ�ѧԺ���ƣ����ڱȽ���һ�����ݵ�ѧԺ���ƣ�
			String yfbz = null;
			StringBuilder xsgzsb = new StringBuilder();	
			int num = 1;//ѧԺ��ż���
			int mergRow=0;//ѧ�������ȵ�ϲ���
			int yfMergRow = 0;//�·ݺϲ���
			for(int i = 0;i<list.size();i++){
				HashMap<String, String> map = list.get(i);
				if(i==0){
					xymcbz = map.get("xymc");
					yfbz = map.get("yf");
					xsgzsb.append(num+"��"+map.get("xsgzrd").replaceAll("<br/>", "\r\n"));					
					mergRow+=1;
					yfMergRow+=1;
					if(i == list.size()-1){
						Label xsgzsbb = new Label(5, 1+i, map.get("xsgzrd").replaceAll("<br/>", "\r\n"), body_cf);
						sheet.addCell(xsgzsbb);
					}
				}else{
					if(!map.get("yf").equalsIgnoreCase(yfbz)){//����·ݲ����		
						xymcbz = map.get("xymc");
						sheet.mergeCells(5,mergRow,5,i);
						sheet.mergeCells(3, yfMergRow, 3, i);
						Label xsgzrd = new Label(5, mergRow, xsgzsb.toString().replaceAll("<br/>", "\r\n"), body_cf);
						Label yf = new Label(3, yfMergRow, yfbz, body_cf);
						sheet.addCell(xsgzrd);
						sheet.addCell(yf);
						yfbz = map.get("yf");
						if(i != list.size()-1){//��Ϊ���һ������
							mergRow = i+1;
							yfMergRow = i+1;
							num=1;						
							xsgzsb = new StringBuilder();														
							xsgzsb.append(num+"��"+map.get("xsgzrd").replaceAll("<br/>", "\r\n"));							
						}else{							
							Label xsgzrdk = new Label(5, i+1, "1��"+map.get("xsgzrd").replaceAll("<br/>", "\r\n"), body_cf);
							Label yff = new Label(3, i+1, map.get("yf"), body_cf);
							sheet.addCell(xsgzrdk);
							sheet.addCell(yff);
						}
					}else{//�·����						
							num++;						
							xsgzsb.append("\r\n"+num+"��"+map.get("xsgzrd").replaceAll("<br/>", "\r\n"));						
							if(i==list.size()-1){//���Ϊ���һ������
								sheet.mergeCells(5,mergRow,5,i+1);
								sheet.mergeCells(3, yfMergRow, 3, i+1);
								Label xsgzrd = new Label(5, mergRow, xsgzsb.toString().replaceAll("<br/>", "\r\n"), body_cf);
								Label yff = new Label(3, yfMergRow, map.get("yf"), body_cf);
								sheet.addCell(xsgzrd);
								sheet.addCell(yff);							
							}
					}
//					if(!map.get("xymc").equalsIgnoreCase(xymcbz)){
//						xymcbz = map.get("xymc");
//						sheet.mergeCells(5,mergRow,5,i);
//						Label xsgzrd = new Label(5, mergRow, xsgzsb.toString().replaceAll("<br/>", "\r\n"), body_cf);						
//						sheet.addCell(xsgzrd);						
//						if(i != list.size()-1){//��Ϊ���һ������
//							mergRow = i+1;
//							num=1;						
//							xsgzsb = new StringBuilder();														
//							xsgzsb.append(num+"��"+map.get("xsgzrd").replaceAll("<br/>", "\r\n"));							
//						}else{							
//							Label xsgzrdk = new Label(5, i+1, "1��"+map.get("xsgzrd").replaceAll("<br/>", "\r\n"), body_cf);							
//							sheet.addCell(xsgzrdk);							
//						}
//					}else{
//						num++;						
//						xsgzsb.append("\r\n"+num+"��"+map.get("xsgzrd").replaceAll("<br/>", "\r\n"));						
//						if(i==list.size()-1){//���Ϊ���һ������
//							sheet.mergeCells(5,mergRow,5,i+1);														
//							Label xsgzrd = new Label(5, mergRow, xsgzsb.toString().replaceAll("<br/>", "\r\n"), body_cf);							
//							sheet.addCell(xsgzrd);							
//						}
//					}
				}
				Label xn = new Label(0, 1+i, map.get("xn"), body_cf);
				Label xqmc = new Label(1, 1+i, map.get("xqmc"), body_cf);
				Label xymc = new Label(2, 1+i, map.get("xymc"), body_cf);
				//Label yf = new Label(3, 1+i, map.get("yf"), body_cf);
				Label bygzkzqk = new Label(4, 1+i, map.get("bygzkzqk").replaceAll("<br/>", "\r\n"), body_cf);
				Label xssxdt = new Label(6, 1+i, map.get("xssxdt").replaceAll("<br/>", "\r\n"), body_cf);
				Label xstsjgzjy = new Label(7, 1+i, map.get("xstsjgzjy").replaceAll("<br/>", "\r\n"), body_cf);
				Label mz = new Label(8, 1+i, map.get("mz"), body_cf);
				Label txsj = new Label(9, 1+i, map.get("txsj"), body_cf);
				sheet.addCell(xn);
				sheet.addCell(xqmc);
				sheet.addCell(xymc);
				//sheet.addCell(yf);
				sheet.addCell(bygzkzqk);
				sheet.addCell(xssxdt);
				sheet.addCell(xstsjgzjy);
				sheet.addCell(mz);
				sheet.addCell(txsj);
			}
			wwb.write();
			wwb.close();
		}
		return file;
	}
}
