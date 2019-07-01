/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-31 ����04:41:49 
 */  
package com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqybjg;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
 * @ģ������: ������ҽҩ_ѧ���±�����ģ��
 * @�๦������: TODO(�༶ѧ���±�����-�༶ѧ����) 
 * @���ߣ� ������[����:995]
 * @ʱ�䣺 2016-3-31 ����04:41:49 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BjxqybjgService extends
		SuperServiceImpl<BjxqybjgForm, BjxqybjgDao> {
	
	private BjxqybjgDao dao = new  BjxqybjgDao();
	public static String _BCZSCID="-1";
	
	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����-�༶ѧ����-���ͨ������ɾ��������)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-6 ����10:01:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean deleteExist(BjxqybjgForm model) throws Exception {
		
		return dao.deleteExist(model);
	}
	
	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����-�༶ѧ����-����༶ѧ���±����)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-6 ����10:01:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveBjxqybjg(BjxqybjgForm model,User user) throws Exception {		
		boolean insertResult = super.runInsert(model);
		return insertResult;
	}	
	
	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����-�༶ѧ����-�жϸð�ѧ���±�����Ƿ��Ѿ�����)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-12 ����01:52:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExistByBjxqybjg(BjxqybjgForm model,String type)
	throws Exception {
		boolean flag = false;
		
		if("save".equalsIgnoreCase(type)) {
			String num = dao.checkExistForBjxqybjgSave(model);
			if (!"0".equalsIgnoreCase(num)) {
				flag = true;
			}
		}else{
			String num = dao.checkExistForBjxqybjgUpdate(model);
			if (!"0".equalsIgnoreCase(num)) {
				flag = true;
			}
		}	
		return flag;
	}
	
	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����-�༶ѧ����-�޸İ༶ѧ���±����)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-11 ����08:32:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateBjxqybjg(BjxqybjgForm model) throws Exception {
		boolean updateResult = super.runUpdate(model);
		return updateResult;
	}
	
	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����-�༶ѧ����-����ѧ���±������Ϣ)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-11 ����08:49:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getBjxqybjgInfo(BjxqybjgForm model){	
		return dao.getBjxqybjgInfo(model);
	}
	
	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����-�༶ѧ����-ɾ���༶ѧ���±���� )
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-11 ����10:14:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] deleteBjxqybjg(String[] ids) throws Exception{
		List<String> delId=new ArrayList<String>();//��ɾ����id����
		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			if(dao.isCanDel(str)){
				delId.add(str);//��¼ɾ��id
			}else{
				HashMap<String, String> hm=dao.getBjxqybjg(str);
				noDel.append(hm.get("bjdm"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("bjmc"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?bjxqybjgDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//ȥ�������ය��
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}
	
	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����-�༶ѧ����-ɾ���༶ѧ���±����)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-11 ����10:14:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	private int bjxqybjgDelete(String[] ids) throws Exception {
		return runDelete(ids);
	}
	
	/**
	 * @throws WriteException 
	 * @throws IOException  
	 * @����:��ȡ�ϲ�excel����ļ�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-29 ����05:58:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param list
	 * @return
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
			sheet.setColumnView(2, 8);
			sheet.setColumnView(3, 16);
			sheet.setColumnView(4, 16);
			sheet.setColumnView(5, 16);
			sheet.setColumnView(6, 12);
			sheet.setColumnView(7, 36);
			sheet.setColumnView(8, 36);
			sheet.setColumnView(9, 36);
			sheet.setColumnView(10, 36);
			sheet.setColumnView(11, 20);
			sheet.setColumnView(12, 8);
			Label label_title1 = new Label(0, 0,"ѧ��",head_cf);
			Label label_title2 = new Label(1, 0,"ѧ��",head_cf);
			Label label_title3 = new Label(2, 0,"�꼶",head_cf);
			Label label_title4 = new Label(3, 0,"ѧԺ",head_cf);
			Label label_title5 = new Label(4, 0,"רҵ",head_cf);
			Label label_title6 = new Label(5, 0,"�༶",head_cf);
			Label label_title7 = new Label(6, 0,"�·�",head_cf);
			Label label_title8 = new Label(7, 0,"���¹�����չ���",head_cf);
			Label label_title9 = new Label(8, 0,"ѧ����ע�ȵ�",head_cf);
			Label label_title10 = new Label(9, 0,"ѧ��˼�붯̬",head_cf);
			Label label_title11 = new Label(10, 0,"ѧ�����󼰹�������",head_cf);
			Label label_title12 = new Label(11, 0,"��дʱ��",head_cf);
			Label label_title13 = new Label(12, 0,"��д��",head_cf);
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
			sheet.addCell(label_title11);
			sheet.addCell(label_title12);
			sheet.addCell(label_title13);
			String xymcbz = null;//ÿ��ѭ��Ҫ�Ƚϵ�ѧԺ���ƣ���һ�����ݵ�ѧԺ���ƣ����ڱȽ���һ�����ݵ�ѧԺ���ƣ�
			String yfbz = null;
			StringBuilder bysb = new StringBuilder();
			StringBuilder xsgzsb = new StringBuilder();
			StringBuilder xssxsb = new StringBuilder();
			int num = 1;//�༶��ż���
			int mergRow=0;
			int yfMergRow = 0;
			for(int i = 0;i<list.size();i++){
				HashMap<String, String> map = list.get(i);
				if(i==0){
					xymcbz = map.get("xymc");
					yfbz = map.get("ny");
					bysb.append(num+"��"+map.get("bygzkzqk").replaceAll("<br/>", "\r\n"));
					xsgzsb.append(num+"��"+map.get("xsgzrd").replaceAll("<br/>", "\r\n"));
					xssxsb.append(num+"��"+map.get("xssxdt").replaceAll("<br/>", "\r\n"));
					mergRow+=1;
					yfMergRow+=1;
				}else{
					if(!map.get("ny").equalsIgnoreCase(yfbz)){//�·ݲ���ȵ������
						xymcbz = map.get("xymc");
						sheet.mergeCells(6, yfMergRow, 6, i);
						sheet.mergeCells(7,mergRow,7,i);
						sheet.mergeCells(8,mergRow,8,i);
						sheet.mergeCells(9,mergRow,9,i);
						Label ny = new Label(6, yfMergRow, yfbz, body_cf);
						Label bygzkzqk = new Label(7,mergRow,bysb.toString().replaceAll("<br/>", "\r\n"),body_cf);
						Label xsgzrd = new Label(8, mergRow, xsgzsb.toString().replaceAll("<br/>", "\r\n"), body_cf);
						Label xssxdt = new Label(9, mergRow, xssxsb.toString().replaceAll("<br/>", "\r\n"), body_cf);
						sheet.addCell(bygzkzqk);
						sheet.addCell(xsgzrd);
						sheet.addCell(xssxdt);
						sheet.addCell(ny);
						yfbz = map.get("ny");
						if(i != list.size()-1){//��Ϊ���һ������
							mergRow = i+1;//���ôӵڼ��п�ʼ�ϲ�
							yfMergRow = i+1;
							num=1;
							bysb = new StringBuilder();
							xsgzsb = new StringBuilder();
							xssxsb = new StringBuilder();
							bysb.append(num+"��"+map.get("bygzkzqk").replaceAll("<br/>", "\r\n"));
							xsgzsb.append(num+"��"+map.get("xsgzrd").replaceAll("<br/>", "\r\n"));
							xssxsb.append(num+"��"+map.get("xssxdt").replaceAll("<br/>", "\r\n"));
						}else{
							Label nyy = new Label(6, i+1, map.get("ny"), body_cf);
							Label bygzkzqkk = new Label(7,i+1,"1��"+map.get("bygzkzqk").replaceAll("<br/>", "\r\n"),body_cf);
							Label xsgzrdk = new Label(8, i+1, "1��"+map.get("xsgzrd").replaceAll("<br/>", "\r\n"), body_cf);
							Label xssxdtk = new Label(9, i+1, "1��"+map.get("xssxdt").replaceAll("<br/>", "\r\n"), body_cf);
							sheet.addCell(bygzkzqkk);
							sheet.addCell(xsgzrdk);
							sheet.addCell(xssxdtk);
							sheet.addCell(nyy);
						}
						
					}else{//�·���ȵ������
						if(!map.get("xymc").equalsIgnoreCase(xymcbz)){//ѧԺ���Ʋ�����µ�����£��Ⱥϲ����ϵĸ�����Ԫ�񣬲��������
							xymcbz = map.get("xymc");
							sheet.mergeCells(7,mergRow,7,i);
							sheet.mergeCells(8,mergRow,8,i);
							sheet.mergeCells(9,mergRow,9,i);
							sheet.mergeCells(6, yfMergRow, 6, i);
							Label bygzkzqk = new Label(7,mergRow,bysb.toString().replaceAll("<br/>", "\r\n"),body_cf);
							Label xsgzrd = new Label(8, mergRow, xsgzsb.toString().replaceAll("<br/>", "\r\n"), body_cf);
							Label xssxdt = new Label(9, mergRow, xssxsb.toString().replaceAll("<br/>", "\r\n"), body_cf);
							Label yf = new Label(6,yfMergRow,yfbz,body_cf);
							sheet.addCell(bygzkzqk);
							sheet.addCell(xsgzrd);
							sheet.addCell(xssxdt);
							sheet.addCell(yf);
							if(i != list.size()-1){//��Ϊ���һ������
								mergRow = i+1;//���ôӵڼ��п�ʼ�ϲ�
								yfMergRow = i+1;//�����·ݴӵڼ��п�ʼ�ϲ�
								num=1;
								bysb = new StringBuilder();
								xsgzsb = new StringBuilder();
								xssxsb = new StringBuilder();
								bysb.append(num+"��"+map.get("bygzkzqk").replaceAll("<br/>", "\r\n"));
								xsgzsb.append(num+"��"+map.get("xsgzrd").replaceAll("<br/>", "\r\n"));
								xssxsb.append(num+"��"+map.get("xssxdt").replaceAll("<br/>", "\r\n"));
							}else{
								Label bygzkzqkk = new Label(7,i+1,"1��"+map.get("bygzkzqk").replaceAll("<br/>", "\r\n"),body_cf);
								Label xsgzrdk = new Label(8, i+1, "1��"+map.get("xsgzrd").replaceAll("<br/>", "\r\n"), body_cf);
								Label xssxdtk = new Label(9, i+1, "1��"+map.get("xssxdt").replaceAll("<br/>", "\r\n"), body_cf);
								Label nyy = new Label(6,i+1,map.get("ny"),body_cf);
								sheet.addCell(bygzkzqkk);
								sheet.addCell(xsgzrdk);
								sheet.addCell(xssxdtk);
								sheet.addCell(nyy);
							}
						}else{//ѧԺ������������
							num++;
							bysb.append("\r\n"+num+"��"+map.get("bygzkzqk").replaceAll("<br/>", "\r\n"));
							xsgzsb.append("\r\n"+num+"��"+map.get("xsgzrd").replaceAll("<br/>", "\r\n"));
							xssxsb.append("\r\n"+num+"��"+map.get("xssxdt").replaceAll("<br/>", "\r\n"));
							if(i==list.size()-1){//���Ϊ���һ������
								sheet.mergeCells(6,yfMergRow,6,i+1);
								sheet.mergeCells(7,mergRow,7,i+1);
								sheet.mergeCells(8,mergRow,8,i+1);
								sheet.mergeCells(9,mergRow,9,i+1);
								Label bygzkzqk = new Label(7,mergRow,bysb.toString().replaceAll("<br/>", "\r\n"),body_cf);
								Label xsgzrd = new Label(8, mergRow, xsgzsb.toString().replaceAll("<br/>", "\r\n"), body_cf);
								Label xssxdt = new Label(9, mergRow, xssxsb.toString().replaceAll("<br/>", "\r\n"), body_cf);
								Label nyy = new Label(6,yfMergRow,map.get("ny"),body_cf);
								sheet.addCell(bygzkzqk);
								sheet.addCell(xsgzrd);
								sheet.addCell(xssxdt);
								sheet.addCell(nyy);
							}
						}
					}
//					if(!map.get("xymc").equalsIgnoreCase(xymcbz)){//ѧԺ���Ʋ�����µ�����£��Ⱥϲ����ϵĸ�����Ԫ�񣬲��������
//						xymcbz = map.get("xymc");
//						sheet.mergeCells(7,mergRow,7,i);
//						sheet.mergeCells(8,mergRow,8,i);
//						sheet.mergeCells(9,mergRow,9,i);
//						Label bygzkzqk = new Label(7,mergRow,bysb.toString().replaceAll("<br/>", "\r\n"),body_cf);
//						Label xsgzrd = new Label(8, mergRow, xsgzsb.toString().replaceAll("<br/>", "\r\n"), body_cf);
//						Label xssxdt = new Label(9, mergRow, xssxsb.toString().replaceAll("<br/>", "\r\n"), body_cf);
//						sheet.addCell(bygzkzqk);
//						sheet.addCell(xsgzrd);
//						sheet.addCell(xssxdt);
//						if(i != list.size()-1){//��Ϊ���һ������
//							mergRow = i+1;//���ôӵڼ��п�ʼ�ϲ�
//							num=1;
//							bysb = new StringBuilder();
//							xsgzsb = new StringBuilder();
//							xssxsb = new StringBuilder();
//							bysb.append(num+"��"+map.get("bygzkzqk").replaceAll("<br/>", "\r\n"));
//							xsgzsb.append(num+"��"+map.get("xsgzrd").replaceAll("<br/>", "\r\n"));
//							xssxsb.append(num+"��"+map.get("xssxdt").replaceAll("<br/>", "\r\n"));
//						}else{
//							Label bygzkzqkk = new Label(7,i+1,"1��"+map.get("bygzkzqk").replaceAll("<br/>", "\r\n"),body_cf);
//							Label xsgzrdk = new Label(8, i+1, "1��"+map.get("xsgzrd").replaceAll("<br/>", "\r\n"), body_cf);
//							Label xssxdtk = new Label(9, i+1, "1��"+map.get("xssxdt").replaceAll("<br/>", "\r\n"), body_cf);
//							sheet.addCell(bygzkzqkk);
//							sheet.addCell(xsgzrdk);
//							sheet.addCell(xssxdtk);
//						}
//					}else{//ѧԺ������������
//						num++;
//						bysb.append("\r\n"+num+"��"+map.get("bygzkzqk").replaceAll("<br/>", "\r\n"));
//						xsgzsb.append("\r\n"+num+"��"+map.get("xsgzrd").replaceAll("<br/>", "\r\n"));
//						xssxsb.append("\r\n"+num+"��"+map.get("xssxdt").replaceAll("<br/>", "\r\n"));
//						if(i==list.size()-1){//���Ϊ���һ������
//							sheet.mergeCells(7,mergRow,7,i+1);
//							sheet.mergeCells(8,mergRow,8,i+1);
//							sheet.mergeCells(9,mergRow,9,i+1);
//							Label bygzkzqk = new Label(7,mergRow,bysb.toString().replaceAll("<br/>", "\r\n"),body_cf);
//							Label xsgzrd = new Label(8, mergRow, xsgzsb.toString().replaceAll("<br/>", "\r\n"), body_cf);
//							Label xssxdt = new Label(9, mergRow, xssxsb.toString().replaceAll("<br/>", "\r\n"), body_cf);
//							sheet.addCell(bygzkzqk);
//							sheet.addCell(xsgzrd);
//							sheet.addCell(xssxdt);
//						}
//					}
				}
				Label xn = new Label(0, 1+i, map.get("xn"), body_cf);
				Label xqmc = new Label(1, 1+i, map.get("xqmc"), body_cf);
				Label nj = new Label(2, 1+i, map.get("nj"), body_cf);
				Label xymc = new Label(3, 1+i, map.get("xymc"), body_cf);
				Label zymc = new Label(4, 1+i, map.get("zymc"), body_cf);
				Label bjmc = new Label(5, 1+i, map.get("bjmc"), body_cf);
				//Label ny = new Label(6, 1+i, map.get("ny"), body_cf);
				Label xstsjgzjy = new Label(10, 1+i, map.get("xstsjgzjy").replaceAll("<br/>", "\r\n"), body_cf);
				Label txsj = new Label(11, 1+i, map.get("txsj"), body_cf);
				Label lrrxm = new Label(12, 1+i, map.get("lrrxm"), body_cf);
				sheet.addCell(xn);
				sheet.addCell(xqmc);
				sheet.addCell(nj);
				sheet.addCell(xymc);
				sheet.addCell(zymc);
				sheet.addCell(bjmc);
				//sheet.addCell(ny);
				sheet.addCell(xstsjgzjy);
				sheet.addCell(txsj);
				sheet.addCell(lrrxm);
			}
			wwb.write();
			wwb.close();
		}
		return file;
	}
	
	/** 
	 * @����:��ȡ�ϲ�����list(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-30 ����09:36:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getHbdcList(BjxqybjgForm t, User user) throws Exception{
		return dao.getHbdcList(t, user);
	}
}
