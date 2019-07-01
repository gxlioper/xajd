package xgxt.xsgygl.bjlh.tjbb;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.ExcelMB;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;
import xgxt.xsgygl.dao.gyglDao;

public class TjbbService {

	TjbbDAO dao = new TjbbDAO();
	
	/** ���ع�Ԣ¥���б� */
	public List getGyLdList() {
		gyglDao gyDAO = new gyglDao();
		return gyDAO.getGyLdList();
	}
	
	/**
	 * ��ñ�ҵ����б�
	 * 
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getByndList() {
		return dao.getByndList();
	}
	
	/**
	 * ����������ͳ�Ʊ�
	 */
	public void printSsqkExcel(TjbbModel model, OutputStream os)
			throws Exception {

//		String xydm = model.getXydm();
//		String lddm = model.getLddm();
		SimpleDateFormat f = new SimpleDateFormat("yyyy��MM��dd��");
		String time = f.format(new Date());
		
		String title = "����������ͳ�Ʊ�                                 "
			+"���ڣ�"+time;

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		
		// �������
		List<HashMap<String, String>> list = dao.queryLdfjsxx(model);//dao.getSsqkList(lddm);//¥��������
		List<HashMap<String, String>> xyList = new ArrayList<HashMap<String, String>>();
		
		xyList = dao.getXymcListByxydm(model.getXydmArr());
		
		List<String> xydmlist = new ArrayList<String>();
		
		int titSize = 0;
		if (xyList != null && xyList.size() > 0) {
			titSize = xyList.size();
		}
		
		WritableSheet ws = wwb.createSheet("����������ͳ�Ʊ�", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 3 + titSize * 4, 1);
		ex.printToOneCell_multy(ws, title, 0, 0, 10, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);
		
		ws.addCell(new Label(0, 2, "¥����", wcf2));
		ws.mergeCells(0, 2, 0, 3);
		ws.addCell(new Label(1, 2, "������", wcf2));
		ws.mergeCells(1, 2, 1, 3);
		ws.addCell(new Label(2, 2, "�ܴ�λ��", wcf2));
		ws.mergeCells(2, 2, 2, 3);
		
		if (xyList != null && xyList.size() > 0) {
			int j = 0;
			for (int i = 0; i < xyList.size(); i++) {
				HashMap<String, String> map = xyList.get(i);
				xydmlist.add(map.get("xydm"));
				ws.addCell(new Label(3 + i + j, 2, map.get("xymc"), wcf2)); 
				ws.addCell(new Label(4 + i + j, 2, "", wcf2)); 
				ws.addCell(new Label(5 + i + j, 2, "", wcf2)); 
				ws.addCell(new Label(6 + i + j, 2, "", wcf2)); 
				ws.mergeCells(3 + i + j, 2, 6 + i + j, 2);
				ws.addCell(new Label(3 + i + j, 3, "���ô�λ��", wcf2)); 
				ws.addCell(new Label(4 + i + j, 3, "���λ��", wcf2)); 
				ws.addCell(new Label(5 + i + j, 3, "ѧԺ���ִ�λ��", wcf2)); 
				ws.addCell(new Label(6 + i + j, 3, "�ܴ�λ��", wcf2)); 
				j += 3;
			}
			ws.addCell(new Label(3 + titSize * 4, 2, "�޹ܰ���ִ�λ��", wcf2)); 
			ws.mergeCells(3 + titSize * 4, 2, 3 + titSize * 4, 3);
		}
		
		if (list != null && list.size() > 0) {
			
			int fjs =0;
			int zcws = 0;
			int dfpcws = 0;
			for (int i = 0; i < list.size(); i++) {
				HashMap<String,String> map = list.get(i);
				ws.addCell(new Label(0, 4 + i, map.get("ldmc"), wcf2)); // ¥������
				ws.addCell(new Label(1, 4 + i, map.get("fjs"), wcf2)); // ������:��¥��Դ���з�������
				fjs+=Integer.parseInt(map.get("fjs"));
				ws.addCell(new Label(2, 4 + i, map.get("zcws"), wcf2)); // �ܴ�λ������¥�ܴ�λ����ȥѧ������סѧ������
				zcws+=Integer.parseInt(map.get("zcws"));
				ws.addCell(new Label(3 + titSize * 4, 4 + i, map.get("dfpcws"), wcf2)); //�޹ܰ���ִ�λ������¥�ܴ�λ����ȥѧ������סѧ������
				dfpcws+=Integer.parseInt(map.get("dfpcws"));
			}
			ws.addCell(new Label(0, 4 + list.size(), "�ܼ�",wcf2)); 
			ws.addCell(new Label(1, 4 + list.size(), String.valueOf(fjs),wcf2)); // �������ϼ�
			ws.addCell(new Label(2, 4 + list.size(), String.valueOf(zcws),wcf2)); // �ܴ�λ���ϼ�
			ws.addCell(new Label(3 + titSize * 4, 4 + list.size(), String.valueOf(dfpcws),wcf2)); // �޹ܰ���ִ�λ���ϼ�
		}
		
		List<HashMap<String, String>> fjList = dao.queryLdxyfjsxx(model);//dao.getFjqk(xydm, lddm);
		if (fjList != null && fjList.size() > 0) {
			int i=0;
			int m= 0;
//			int n = 0;
//			int k = 0;
			int yycws =0;
			int xlcws = 0;
			int dfcws = 0;
			int zcws = 0;
			for (int j = 0; j < fjList.size(); j++) {
				HashMap<String,String> map = fjList.get(j);
				if(j>0){
					HashMap<String,String> map1 = fjList.get(j-1);
					if (!map.get("xydm").equalsIgnoreCase(map1.get("xydm"))) {
						ws.addCell(new Label(3 + i , 4 + list.size(), String.valueOf(yycws),wcf2)); //���ô�λ���ϼ�
						ws.addCell(new Label(4 + i , 4 + list.size(), String.valueOf(xlcws),wcf2)); //���λ���ϼ�
						ws.addCell(new Label(5 + i , 4 + list.size(), String.valueOf(dfcws),wcf2)); //ѧԺ���ִ�λ���ϼ�
						ws.addCell(new Label(6 + i , 4 + list.size(), String.valueOf(zcws),wcf2)); //�ܴ�λ���ϼ�
						i+=4;
						m=0;
						yycws =0;
						xlcws = 0;
						dfcws = 0;
						zcws = 0;
					}else{
						m++;
					}
				}
				ws.addCell(new Label(3 + i, 4 + m, map.get("yzrs"),wcf2)); //���ô�λ������Ժ����ס��¥��ѧ������
				yycws+=Integer.parseInt(map.get("yzrs"));
				ws.addCell(new Label(4 + i, 4 + m, map.get("xlcws"),wcf2)); //���λ������¥��Դ�������λ���� 
				xlcws+=Integer.parseInt(map.get("xlcws"));
				
				ws.addCell(new Label(6 + i, 4 + m, map.get("zcw"),wcf2)); //�ܴ�λ������¥�ѻ��ָ�Ժ��λ����
				zcws+=Integer.parseInt(map.get("zcw"));	
				
				String dfp = String.valueOf(Integer.parseInt(map.get("zcw"))- Integer.parseInt(map.get("yzrs"))- Integer.parseInt(map.get("xlcws")));
				ws.addCell(new Label(5 + i, 4 + m, dfp,wcf2)); //ѧԺ���ִ�λ������¥�ѻ��ָ�Ժ��λ������ȥ����סѧ������
				dfcws+=Integer.parseInt(dfp);
			
				if(j==fjList.size()-1){
					ws.addCell(new Label(3 + i , 4 + list.size(), String.valueOf(yycws),wcf2)); //���ô�λ���ϼ�
					ws.addCell(new Label(4 + i , 4 + list.size(), String.valueOf(xlcws),wcf2)); //���λ���ϼ�
					ws.addCell(new Label(5 + i , 4 + list.size(), String.valueOf(dfcws),wcf2)); //ѧԺ���ִ�λ���ϼ�
					ws.addCell(new Label(6 + i , 4 + list.size(), String.valueOf(zcws),wcf2)); //�ܴ�λ���ϼ�
				}
			}

		}
//		if (xyList != null && xyList.size() > 0) {
//			for (int i = 0; i < xyList.size(); i++) {
//				//xydm = xyList.get(i).get("xydm");
//				//long t1 = System.currentTimeMillis();
//				List<HashMap<String, String>> fjList = dao.getFjqk(xydm, lddm);
//				//System.out.println(System.currentTimeMillis() - t1);
//				int yycws =0;
//				int xlcws = 0;
//				int dfcws = 0;
//				int zcws = 0;
//				for (int j = 0; j < fjList.size(); j++) {
//					HashMap<String,String> map = fjList.get(j);
//					ws.addCell(new Label(3 + i + k, 4 + j, map.get("yycws"),wcf2)); //���ô�λ������Ժ����ס��¥��ѧ������
//					yycws+=Integer.parseInt(map.get("yycws"));
//					ws.addCell(new Label(4 + i + k, 4 + j, map.get("xlcws"),wcf2)); //���λ������¥��Դ�������λ���� 
//					xlcws+=Integer.parseInt(map.get("xlcws"));
//					ws.addCell(new Label(5 + i + k, 4 + j, map.get("dfcws"),wcf2)); //ѧԺ���ִ�λ������¥�ѻ��ָ�Ժ��λ������ȥ����סѧ������
//					dfcws+=Integer.parseInt(map.get("dfcws"));
//					ws.addCell(new Label(6 + i + k, 4 + j, map.get("zcws"),wcf2)); //�ܴ�λ������¥�ѻ��ָ�Ժ��λ����
//					zcws+=Integer.parseInt(map.get("zcws"));	
//				}
//				ws.addCell(new Label(3 + i + k, 4 + fjList.size(), String.valueOf(yycws),wcf2)); //���ô�λ���ϼ�
//				ws.addCell(new Label(4 + i + k, 4 + fjList.size(), String.valueOf(xlcws),wcf2)); //���λ���ϼ�
//				ws.addCell(new Label(5 + i + k, 4 + fjList.size(), String.valueOf(dfcws),wcf2)); //ѧԺ���ִ�λ���ϼ�
//				ws.addCell(new Label(6 + i + k, 4 + fjList.size(), String.valueOf(zcws),wcf2)); //�ܴ�λ���ϼ�
//				k += 3;
//			}
//		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * �ɷ��䴲λ��ͳ�Ʊ�
	 */
	public void printCwfpExcel(TjbbModel model, OutputStream os)
			throws Exception {

//		String xydm = model.getXydm();
//		String lddm = model.getLddm();
		SimpleDateFormat f = new SimpleDateFormat("yyyy��MM��dd��");
		String time = f.format(new Date());
//		String bynd= model.getByny();

		List<HashMap<String, String>> xyList = new ArrayList<HashMap<String, String>>();
		List<String> xydmlist = new ArrayList<String>();
		
		xyList = dao.getXymcListByxydm(model.getXydmArr());
		
		int titSize = 0;
		if (xyList != null && xyList.size() > 0) {
			titSize = xyList.size();
		}
		
		String title = "�ɷ��䴲λ��ͳ�Ʊ�  "+"���ڣ�"+time;

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		
		// �������
		List<HashMap<String, String>> list = dao.queryLdkfpcwxx(model);
	
		WritableSheet ws = wwb.createSheet("�ɷ��䴲λ��ͳ�Ʊ�", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 3 + titSize, 1);
		ex.printToOneCell_multy(ws, title, 0, 0, 10, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);
		
		ws.addCell(new Label(0, 2, "¥����", wcf2)); 
		ws.addCell(new Label(1, 2, "������", wcf2)); 
		ws.addCell(new Label(2, 2, "�ܴ�λ��", wcf2)); 
	
		if (xyList != null && xyList.size() > 0) {
			for (int i = 0; i < xyList.size(); i++) {
				HashMap<String, String> map = xyList.get(i);
				xydmlist.add(map.get("xydm"));
				ws.addCell(new Label(3+i, 2, map.get("xymc") + "��λ��", wcf2)); 
			}
			ws.addCell(new Label(3 + titSize, 2, "�޹ܰ���ִ�λ��", wcf2)); 
		}
		
		if (list != null && list.size() > 0) {	
			int fjs =0;
			int zcws = 0;
			int dfpcws = 0;
			for (int i = 0; i < list.size(); i++) {
				HashMap<String,String> map = list.get(i);
				ws.addCell(new Label(0, 3 + i, map.get("ldmc"), wcf2)); // ¥������
				ws.addCell(new Label(1, 3 + i, map.get("fjs"), wcf2)); // ������:��¥��Դ���з�������
				fjs+=Integer.parseInt(map.get("fjs"));
				ws.addCell(new Label(2, 3 + i, map.get("zcws"), wcf2)); // �ܴ�λ������¥�ܴ�λ����ȥѧ������סѧ������
				zcws+=Integer.parseInt(map.get("zcws"));
				ws.addCell(new Label(3 + titSize, 3 + i, map.get("dfpcws"), wcf2)); //�޹ܰ���ִ�λ������¥�ܴ�λ����ȥѧ������סѧ������
				dfpcws+=Integer.parseInt(map.get("dfpcws"));
			}
			ws.addCell(new Label(0, 3 + list.size(), "�ܼ�",wcf2)); 
			ws.addCell(new Label(1, 3 + list.size(), String.valueOf(fjs),wcf2));  // �������ϼ�
			ws.addCell(new Label(2, 3 + list.size(), String.valueOf(zcws),wcf2)); // �ܴ�λ���ϼ�
			ws.addCell(new Label(3 + titSize, 3 + list.size(), String.valueOf(dfpcws),wcf2));  // �޹ܰ���ִ�λ���ϼ�

		}

		List<HashMap<String, String>> cwlist = queryLdxydfpxx(model);
		if (cwlist != null && cwlist.size() > 0) {
			int cws = 0;
			int i=0;
			int m= 0;
			for (int j = 0; j < cwlist.size(); j++) {
				HashMap<String, String> map = cwlist.get(j);
				if(j>0){
					HashMap<String,String> map1 = cwlist.get(j-1);
					if (!map.get("xydm").equalsIgnoreCase(map1.get("xydm"))) {
						ws.addCell(new Label(3 + i, 3 + list.size(), String
								.valueOf(cws), wcf2)); 
						i++;
						m=0;
						cws =0;
					}else{
						m++;
					}
				}
				String dfp = String.valueOf(Integer.parseInt(map.get("dfpcw"))
						+ Integer.parseInt(map.get("bysrs")));
				ws.addCell(new Label(3 + i, 3 + m, dfp, wcf2));// ѧԺ��λ��:
				cws += Integer.parseInt(dfp);// ѧԺ���ִ�λ��+�����ҵ����λ��												
				if(j==cwlist.size()-1){
					ws.addCell(new Label(3 + i, 3 + list.size(), String
							.valueOf(cws), wcf2)); 
				}
			}
		}
//		ws.addCell(new Label(3 + i, 3 + cwlist.size(), String.valueOf(cws),
//				wcf2)); 
//		if (xyList != null && xyList.size() > 0) {
//			for (int i = 0; i < xyList.size(); i++) {
//				xydm = xyList.get(i).get("xydm");
//				//long t1 = System.currentTimeMillis();
//				List<HashMap<String, String>> cwlist = dao.getCwfp(xydm, lddm);
//				//System.out.println(System.currentTimeMillis() - t1);
//				int cws = 0;
//				for (int j = 0; j < cwlist.size(); j++) {
//					HashMap<String, String> map = cwlist.get(j);
//					ws.addCell(new Label(3 + i, 3 + j, map.get("cws"), wcf2)); 
//					cws += Integer.parseInt(map.get("cws"));// ѧԺ��λ��: ѧԺ���ִ�λ��+�����ҵ����λ��
//				}
//				ws.addCell(new Label(3 + i, 3 + cwlist.size(), String
//						.valueOf(cws), wcf2)); 
//			}
//		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ��ҳ���ȡ��ѧԺ�����б�,ƥ��ѧԺ����
	 * @param xyArr
	 * @param xyList
	 * @return
	 */
	public List<HashMap<String, String>> pagexyList(String[] xyArr, List<HashMap<String, String>> xyList) {
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String,String>>();
		if (xyArr != null && xyArr.length > 0) {
			for (int i=0;i<xyArr.length;i++) {
				for (HashMap<String, String> map : xyList) {
					if (StringUtils.isNotNull(xyArr[i]) && xyArr[i].equalsIgnoreCase(map.get("xydm"))) {
						rs.add(map);
					}
				}
			}
		}
		return rs;
	}
	
	public String getXymcByxydm(String xydm, List<HashMap<String, String>> xyList) {
		for (HashMap<String, String> map : xyList) {
			if (StringUtils.isNotNull(xydm) && xydm.equalsIgnoreCase(map.get("xydm"))) {
				return map.get("xymc");
			}
		}
		return "";
	}
	
	/**
	 * ����������������Ϣ
	 * @param xyList
	 * @param model
	 * @param os
	 * @throws Exception
	 */
	public void expSsjbqkxx(TjbbModel model,
			OutputStream os) throws Exception {
		
		SimpleDateFormat f = new SimpleDateFormat("yyyy��MM��dd��");
		String time = f.format(new Date());
		
		String title = "����������ͳ�Ʊ�                                 "
			+"���ڣ�"+time;

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		
		List<String[]> ldxyfjsList = null;//dao.queryLdxyfjsxx(model);//¥��ѧԺ��������ϸ
		List<String[]> ldfjsList = null;//dao.queryLdfjsxx(model);//¥����������
		
		int titSize = 0;
		String[] xyArr = model.getXydmArr();
		List<HashMap<String, String>> outputXyList = dao.getXymcListByxydm(xyArr);
		if (outputXyList != null && outputXyList.size() > 0) {
			titSize = outputXyList.size();
		}
		
		WritableSheet ws = wwb.createSheet("����������ͳ�Ʊ�", 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 3 + titSize * 4, 1);
		ex.printToOneCell_multy(ws, title, 0, 0, 10, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);
		
		ws.addCell(new Label(0, 2, "¥����", wcf2));
		ws.mergeCells(0, 2, 0, 3);
		ws.addCell(new Label(1, 2, "������", wcf2));
		ws.mergeCells(1, 2, 1, 3);
		ws.addCell(new Label(2, 2, "�ܴ�λ��", wcf2));
		ws.mergeCells(2, 2, 2, 3);
		ws.addCell(new Label(3 + titSize * 4, 2, "�޹ܰ���ִ�λ��", wcf2)); 
		ws.mergeCells(3 + titSize * 4, 2, 3 + titSize * 4, 3);
		
		//��һ�����ÿ��,��������,�ܴ�λ��,�մ�λ��,
		int colset = 1;
		if (ldfjsList != null) {
			for (int i=0;i<ldfjsList.size();i++) {
				String[] ldArr = ldfjsList.get(i);
				if (ldArr != null && ldArr.length == 5) {
					String lddm = StringUtils.isNull(ldArr[0]) ? "" : ldArr[0];
					ws.addCell(new Label(0, i+4, ldArr[1], wcf2));
					ws.addCell(new Label(1, i+4, ldArr[2], wcf2));
					ws.addCell(new Label(2, i+4, ldArr[3], wcf2));
					ws.addCell(new Label(3 + titSize * 4, i+4, ldArr[4],wcf2));
					
					//�����������ѧԺ�Ĵ�λ��ϸ��Ϣ
					if (ldxyfjsList != null) {
						for (int j=0;j<ldxyfjsList.size();j++) {
							String[] ldxyData = ldxyfjsList.get(j);
							if (ldxyData != null && ldxyData.length == 6) {
								if (lddm.equalsIgnoreCase(ldxyData[0])) {
									if (colset == 1) {
										ws.addCell(new Label(3,i+4,ldxyData[2]));
										ws.addCell(new Label(4,i+4,ldxyData[3]));
										ws.addCell(new Label(5,i+4,ldxyData[4]));
										ws.addCell(new Label(6,i+4,ldxyData[5]));
									} else {
										ws.addCell(new Label(3 + colset * 4,i+4,ldxyData[2]));
										ws.addCell(new Label(4 + colset * 4,i+4,ldxyData[3]));
										ws.addCell(new Label(5 + colset * 4,i+4,ldxyData[4]));
										ws.addCell(new Label(6 + colset * 4,i+4,ldxyData[5]));
									}
								}
							}
						}
					}
					
				}
				colset ++;
			}
			
			//�ڶ��������ѧԺ���Ʊ���
			for (int j=0;j<outputXyList.size();j++) {
				HashMap<String, String> map = outputXyList.get(j);
				//���ѧԺ����
				if (j == 0) {
					ws.addCell(new Label(3,2,map.get("xymc"),wcf2));
					ws.mergeCells(3, 2, 3+3, 2);
					ws.addCell(new Label(3,3,"���ô�λ��",wcf2));
					ws.addCell(new Label(4,3,"���λ��",wcf2));
					ws.addCell(new Label(5,3,"ѧԺ�����䴲λ��",wcf2));
					ws.addCell(new Label(6,3,"�ܴ�λ��",wcf2));
					
				} else {
					ws.addCell(new Label(3+j*4,2,map.get("xymc"),wcf2));
					ws.mergeCells(3+j*4, 2, 6+j*4, 2);
					ws.addCell(new Label(3 + j*4,3,"���ô�λ��",wcf2));
					ws.addCell(new Label(3 + j*4 + 1,3,"���λ��",wcf2));
					ws.addCell(new Label(3 + j*4 + 2,3,"ѧԺ�����䴲λ��",wcf2));
					ws.addCell(new Label(3 + j*4 + 3,3,"�ܴ�λ��",wcf2));
				}
			}
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ����ѧԺ��¥���ı�ҵ�������ϲ���¥��ѧԺ�ɷ��䴲λ��Ϣ����
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> queryLdxydfpxx(TjbbModel model) throws Exception{
		List<HashMap<String, String>> ldxydfpList = dao.queryLdxydfpfjsxx(model);
		List<HashMap<String, String>> bysList = dao.queryBysfjsxx(model.getByny());
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String,String>>();
		if (ldxydfpList != null && ldxydfpList.size() > 0) {
			for (HashMap<String, String> ldMap : ldxydfpList) {
				String lddm = StringUtils.isNull(ldMap.get("lddm")) ? "" : ldMap.get("lddm");
				String xydm = StringUtils.isNull(ldMap.get("xydm")) ? "" : ldMap.get("xydm");
				if (bysList != null && bysList.size() > 0) {
					for (HashMap<String, String> bysMap : bysList) {
						if (lddm.equalsIgnoreCase(bysMap.get("lddm")) && xydm.equalsIgnoreCase(bysMap.get("xydm"))) {
							ldMap.put("bysrs", bysMap.get("bys"));
						}
					}
				}
				rs.add(ldMap);
			}
		}
		return rs;
	}

}
