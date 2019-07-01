package xgxt.comm.impexp;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import xgxt.action.Base;
import xgxt.comm.CommForm;
import xgxt.comm.CommService;

import java.io.*;    
import jxl.*;    
import jxl.write.*;

public class CommImpExpService extends CommService {

	CommImpExpDAO dao = new CommImpExpDAO();
	
	/**
	 * ��������
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public String impInfo(CommForm model, String filePath) throws Exception {

		File file = new File(filePath);
		
		String message = "";
		
		boolean flag = false;
		
		// ��������
		if (!Base.isNull(filePath)) {

			Workbook book = Workbook.getWorkbook(file);
			// ��õ�һ�����������
			Sheet sheet = book.getSheet(0);
			// ����list
			List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			// ��
			int rows = sheet.getRows();
			// ��
			int column = sheet.getColumns();
			// ��ͷ
			String[] bt = new String[column];

			// ѭ����
			for (int i = 0; i < rows; i++) {
				// ��һ�б��⣬����
				if (i != 0) {
					HashMap<String, String> map = new HashMap<String, String>();
					// ѭ����
					for (int j = 0; j < column; j++) {

						Cell cell = sheet.getCell(j, i);
						String nr = cell.getContents();

						map.put(bt[j], nr);
					}
					list.add(map);
				} else {
					for (int j = 0; j < column; j++) {

						Cell cell = sheet.getCell(j, i);
						String nr = cell.getContents();
						bt[j] = nr;
					}
				}
			}

			//������Ҫ�������Ϣ
			List<HashMap<String, String>> impInfoList = dao.getImpInfoList(bt);

			if (list != null && list.size() > 0 && impInfoList != null
					&& impInfoList.size() > 0) {
				
				ArrayList<String> sqlList = new ArrayList<String>();
				
				StringBuilder sql = new StringBuilder();
				
				List<String> xhList = dao.getExistXhList(list);
				
				for (int i = 0; i < list.size(); i++) {
					
					HashMap<String, String> map = list.get(i);
					
					// ѧ��
					String xh = map.get("ѧ��");
					if(Base.isNull(xh)){
						message = "��" + (i + 1) + "����¼ѧ��Ϊ�գ��޷����룬��ȷ�ϣ�";
						return message;
					}
					// ѧ��
					String xn = map.get("ѧ��");
					xn = Base.isNull(xn) ? Base.getJxjsqxn() : xn;
					// ѧ��
					String xq = map.get("ѧ��");
					xq = Base.isNull(xq) ? Base.getJxjsqxq() : xq;
					// ���
					String nd = map.get("���");
					nd = Base.isNull(nd) ? Base.getJxjsqnd() : nd;
					
					String pk = xh+xn+xq+nd;
					
					if (xhList != null && xhList.size() > 0) {
						
						boolean existXh = false;
						
						for (int j = 0; j < xhList.size(); j++) {
							// �Ƚ�ѧ��
							String bjxh = xhList.get(j);
							if(xh.equalsIgnoreCase(bjxh)){
								existXh = true;
							}
						}
						
						if(!existXh){
							message = "��" + (i + 1) + "����¼ѧ��Ϊ�ڱ�ϵͳ�в����ڣ��޷����룬��ȷ�ϣ�";
							return message;
						}
					}else{
						message = "��" + (i + 1) + "����¼ѧ��Ϊ�ڱ�ϵͳ�в����ڣ��޷����룬��ȷ�ϣ�";
						return message;
					}
					
					for (int j = 0; j < list.size(); j++) {
						HashMap<String, String> jcMap = list.get(j);
						
						// ���ѧ��
						String jcxh = jcMap.get("ѧ��");
						// ѧ��
						String jcxn = jcMap.get("ѧ��");
						jcxn = Base.isNull(jcxn) ? Base.getJxjsqxn() : jcxn;
						// ѧ��
						String jcxq = jcMap.get("ѧ��");
						jcxq = Base.isNull(jcxq) ? Base.getJxjsqxq() : jcxq;
						// ���
						String jcnd = jcMap.get("���");
						jcnd = Base.isNull(jcnd) ? Base.getJxjsqnd() : jcnd;
						
						String jcpk = jcxh+jcxn+jcxq+jcnd;
						
						if (i != j) {
							if (pk.equalsIgnoreCase(jcpk)) {
								message = "��" + (i + 1) + "����¼���" + (j + 1)
										+ "����¼������ͬ������ʧ�ܣ�";
								return message;
							}
						}
					}
					
					for (int j = 0; j < impInfoList.size(); j++) {
								
						HashMap<String, String> info = impInfoList.get(j);
						
						// ��ͼ
						String view = info.get("bm");
						// ��
						String table = view.contains("view_") ? view.replace(
								"view_", "") : view;

						List<HashMap<String, String>> tableInfoList = selectTableInfo(table);

						if (tableInfoList != null && tableInfoList.size() > 0) {
							for (int k = 0; k < tableInfoList.size(); k++) {
								
								HashMap<String, String> tableInfo = tableInfoList.get(k);
								
								String name = tableInfo.get("name");
								String comm = tableInfo.get("comments");
								int len = Integer.parseInt(tableInfo.get("length"));
								
								if ("xh".equalsIgnoreCase(name)
										&& (xh.length() > len)) {
									message = "��" + (i + 1) + "����¼" + comm
											+ "�ֶγ������ݿ�������󳤶ȣ�����ʧ�ܣ�";
									return message;
								}
								if ("xn".equalsIgnoreCase(name)
										&& (xn.length() > len)) {
									message = "��" + (i + 1) + "����¼" + comm
											+ "�ֶγ������ݿ�������󳤶ȣ�����ʧ�ܣ�";
									return message;
								}
								if ("xq".equalsIgnoreCase(name)
										&& (xq.length() > len)) {
									message = "��" + (i + 1) + "����¼" + comm
											+ "�ֶγ������ݿ�������󳤶ȣ�����ʧ�ܣ�";
									return message;
								}
								if ("nd".equalsIgnoreCase(name)
										&& (nd.length() > len)) {
									message = "��" + (i + 1) + "����¼" + comm
											+ "�ֶγ������ݿ�������󳤶ȣ�����ʧ�ܣ�";
									return message;
								}
							}
						} else {
							message = "��" + table + "�����ڣ�����ʧ�ܣ�";
							return message;
						}
						
						
						sql = new StringBuilder();
						
						sql.append(" delete from ");
						sql.append(table);
						sql.append(" where 1 = 1 ");
						sql.append(" and xh = '" + xh + "'");
						sql.append(" and xn = '" + xn + "'");
						sql.append(" and xq = '" + xq + "'");
						sql.append(" and nd = '" + nd + "'");
						
						sqlList.add(sql.toString());
						
						sql = new StringBuilder();
						
						sql.append(" insert into ");
						sql.append(table);
						sql.append("(xh,xn,xq,nd)");
						sql.append("values");
						sql.append("(");
						sql.append("'" + xh + "',");
						sql.append("'" + xn + "',");
						sql.append("'" + xq + "',");
						sql.append("'" + nd + "'");
						sql.append(")");
						
						sqlList.add(sql.toString());
					}
				}
				
				try {
 					String[] actSql = sqlList.toArray(new String[] {});
					flag = dao.saveArrDate(actSql);
				
				} catch (Exception e) {
					message = "����ʧ��";
				}
				
				if (flag) {

					sqlList = new ArrayList<String>();

					for (int i = 0; i < list.size(); i++) {

						HashMap<String, String> map = list.get(i);

						// ѧ��
						String xh = map.get("ѧ��");
						// ѧ��
						String xn = map.get("ѧ��");
						xn = Base.isNull(xn) ? Base.getJxjsqxn() : xn;
						// ѧ��
						String xq = map.get("ѧ��");
						xq = Base.isNull(xq) ? Base.getJxjsqxq() : xq;
						// ���
						String nd = map.get("���");
						nd = Base.isNull(nd) ? Base.getJxjsqnd() : nd;

						for (int j = 0; j < impInfoList.size(); j++) {

							HashMap<String, String> info = impInfoList.get(j);

							// ����
							String mc = info.get("mc");
							// ��ͼ
							String view = info.get("bm");
							// �ֶ�
							String zd = info.get("zd");
							// ����
							String zj = info.get("zj");
							// ����ֵ
							String zjz = xh;

							if (!Base.isNull(zj)) {

								String[] pk = zj.replace("||",",,").split(",,");

								// ���ݸ���ͼ������ƴ������ֵ
								for (String pkValue : pk) {
									if ("xn".equalsIgnoreCase(pkValue)) {
										zjz += xn;
									} else if ("xq".equalsIgnoreCase(pkValue)) {
										zjz += xq;
									} else if ("nd".equalsIgnoreCase(pkValue)) {
										zjz += nd;
									}
								}
							}

							sql = new StringBuilder();
							
							sql.append(" delete from py_bdsz_bcnr a");
							sql.append(" where 1 = 1 ");
							sql.append(" and a.zdid = '" + zd + "'");
							sql.append(" and a.tabname = '" + view + "'");
							sql.append(" and exists (select 1 from ");
							sql.append(view);
							sql.append(" b where a.zbid = b.pk");
							sql.append(" and b.xh||" + zj + " = '" + zjz + "'");
							sql.append(" )");
							
							sqlList.add(sql.toString());
							
							for (int k = 4; k < column; k++) {
			
								String nr = map.get(bt[k]);

								if (bt[k].equals(mc)) {
		
									sql = new StringBuilder();
									
									sql.append(" insert into py_bdsz_bcnr");
									sql.append("(zdid,tabname,zbid,bcnr)");
									sql.append("select ");
									sql.append("'" + zd + "' zdid,");
									sql.append("'" + view + "' tabname,");
									sql.append("pk zbid,");
									sql.append("'" + nr + "' bcnr ");
									sql.append(" from ");
									sql.append(view);
									sql.append(" where 1 = 1 ");
									sql.append(" and xh||" + zj + " = '" + zjz
											+ "'");
									
									sqlList.add(sql.toString());
								}
							}
						}
					}
					
					try {
						String[] actSql = sqlList.toArray(new String[] {});
						flag = dao.saveArrDate(actSql);

					} catch (Exception e) {
						message = "����ʧ��";
					}
				}
			}
			
			file.delete();
		}

		return message;
	}

	public String impInfoForNtzy(CommForm model, String filePath) throws Exception {

		File file = new File(filePath);
		
		String message = "";
		
		boolean flag = false;
		
		// ��������
		if (!Base.isNull(filePath)) {

			Workbook book = Workbook.getWorkbook(file);
			// ��õ�һ�����������
			Sheet sheet = book.getSheet(0);
			// ����list
			List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			// ��
			int rows = sheet.getRows();
			// ��
			int column = sheet.getColumns();
			// ��ͷ
			String[] bt = new String[column];

			// ѭ����
			for (int i = 0; i < rows; i++) {
				// ��һ�б��⣬����
				if (i != 0) {
					HashMap<String, String> map = new HashMap<String, String>();
					// ѭ����
					for (int j = 0; j < column; j++) {

						Cell cell = sheet.getCell(j, i);
						String nr = cell.getContents();

						map.put(bt[j], nr);
					}
					list.add(map);
				} else {
					for (int j = 0; j < column; j++) {

						Cell cell = sheet.getCell(j, i);
						String nr = cell.getContents();
						bt[j] = nr;
					}
				}
			}

			//������Ҫ�������Ϣ
			List<HashMap<String, String>> impInfoList = dao.getImpInfoListForNtzy(bt);

			if (list != null && list.size() > 0 && impInfoList != null
					&& impInfoList.size() > 0) {
				
				ArrayList<String> sqlList = new ArrayList<String>();
				
				StringBuilder sql = new StringBuilder();
				
				List<String> xhList = dao.getExistXhList(list);
				
				for (int i = 0; i < list.size(); i++) {
					
					HashMap<String, String> map = list.get(i);
					
					// ѧ��
					String xh = map.get("ѧ��");
					if(Base.isNull(xh)){
						message = "��" + (i + 1) + "����¼ѧ��Ϊ�գ��޷����룬��ȷ�ϣ�";
						return message;
					}
					// ѧ��
					String xn = map.get("ѧ��");
					xn = Base.isNull(xn) ? Base.getJxjsqxn() : xn;
					// ѧ��
					String xq = map.get("ѧ��");
					xq = Base.isNull(xq) ? Base.getJxjsqxq() : xq;
					// ���
					String nd = map.get("���");
					nd = Base.isNull(nd) ? Base.getJxjsqnd() : nd;
					
					String pk = xh+xn+xq+nd;
					
					if (xhList != null && xhList.size() > 0) {
						
						boolean existXh = false;
						
						for (int j = 0; j < xhList.size(); j++) {
							// �Ƚ�ѧ��
							String bjxh = xhList.get(j);
							if(xh.equalsIgnoreCase(bjxh)){
								existXh = true;
							}
						}
						
						if(!existXh){
							message = "��" + (i + 1) + "����¼ѧ��Ϊ�ڱ�ϵͳ�в����ڣ��޷����룬��ȷ�ϣ�";
							return message;
						}
					}else{
						message = "��" + (i + 1) + "����¼ѧ��Ϊ�ڱ�ϵͳ�в����ڣ��޷����룬��ȷ�ϣ�";
						return message;
					}
					
					for (int j = 0; j < list.size(); j++) {
						HashMap<String, String> jcMap = list.get(j);
						
						// ���ѧ��
						String jcxh = jcMap.get("ѧ��");
						// ѧ��
						String jcxn = jcMap.get("ѧ��");
						jcxn = Base.isNull(jcxn) ? Base.getJxjsqxn() : jcxn;
						// ѧ��
						String jcxq = jcMap.get("ѧ��");
						jcxq = Base.isNull(jcxq) ? Base.getJxjsqxq() : jcxq;
						// ���
						String jcnd = jcMap.get("���");
						jcnd = Base.isNull(jcnd) ? Base.getJxjsqnd() : jcnd;
						
						String jcpk = jcxh+jcxn+jcxq+jcnd;
						
						if (i != j) {
							if (pk.equalsIgnoreCase(jcpk)) {
								message = "��" + (i + 1) + "����¼���" + (j + 1)
										+ "����¼������ͬ������ʧ�ܣ�";
								return message;
							}
						}
					}
					
					for (int j = 0; j < impInfoList.size(); j++) {
								
						HashMap<String, String> info = impInfoList.get(j);
						
						// ��ͼ
						String view = info.get("bm");
						// ��
						String table = view.contains("view_") ? view.replace(
								"view_", "") : view;
						
						String mc = info.get("mc");
						String dm = info.get("dm");
						
						String jb = info.get("jb");
						
						//��ȡ����ķ���
						String fs = map.get(mc);
				
						List<HashMap<String, String>> tableInfoList = selectTableInfo(table);

						if (tableInfoList != null && tableInfoList.size() > 0) {
							for (int k = 0; k < tableInfoList.size(); k++) {
								
								HashMap<String, String> tableInfo = tableInfoList.get(k);
								
								String name = tableInfo.get("name");
								String comm = tableInfo.get("comments");
								int len = Integer.parseInt(tableInfo.get("length"));
								
								if ("xh".equalsIgnoreCase(name)
										&& (xh.length() > len)) {
									message = "��" + (i + 1) + "����¼" + comm
											+ "�ֶγ������ݿ�������󳤶ȣ�����ʧ�ܣ�";
									return message;
								}
								if ("xn".equalsIgnoreCase(name)
										&& (xn.length() > len)) {
									message = "��" + (i + 1) + "����¼" + comm
											+ "�ֶγ������ݿ�������󳤶ȣ�����ʧ�ܣ�";
									return message;
								}
								if ("xq".equalsIgnoreCase(name)
										&& (xq.length() > len)) {
									message = "��" + (i + 1) + "����¼" + comm
											+ "�ֶγ������ݿ�������󳤶ȣ�����ʧ�ܣ�";
									return message;
								}
								if ("nd".equalsIgnoreCase(name)
										&& (nd.length() > len)) {
									message = "��" + (i + 1) + "����¼" + comm
											+ "�ֶγ������ݿ�������󳤶ȣ�����ʧ�ܣ�";
									return message;
								}
							}
						} else {
							message = "��" + table + "�����ڣ�����ʧ�ܣ�";
							return message;
						}
						
						
						sql = new StringBuilder();
						
						sql.append(" delete from ");
						sql.append(table);
						sql.append(" where 1 = 1 ");
						sql.append(" and xh = '" + xh + "'");
						sql.append(" and xn = '" + xn + "'");
						sql.append(" and xq = '" + xq + "'");
						sql.append(" and nd = '" + nd + "'");
						sql.append(" and dm = '" + dm + "'");
						
						sqlList.add(sql.toString());
						
						sql = new StringBuilder();
						
						sql.append(" insert into ");
						sql.append(table);
						sql.append("(xh,xn,xq,nd,dm,jb,fs)");
						sql.append("values");
						sql.append("(");
						sql.append("'" + xh + "',");
						sql.append("'" + xn + "',");
						sql.append("'" + xq + "',");
						sql.append("'" + nd + "',");
						sql.append("'" + dm + "',");
						sql.append("'" + jb + "',");
						sql.append("'" + fs + "'");
						sql.append(")");
						
						sqlList.add(sql.toString());
					}
				}
				
				try {
 					String[] actSql = sqlList.toArray(new String[] {});
					flag = dao.saveArrDate(actSql);
				
				} catch (Exception e) {
					message = "����ʧ��";
				}
			}
			
			file.delete();
		}

		return message;
	}

	
	/**
	 * ��������
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void expInfo(CommForm model, HashMap<String, String> map,
			OutputStream os) throws Exception {

		CommImpExpDAO dao = new CommImpExpDAO();

		// ѧУ����
		// String xxdm = Base.xxdm;
		// ѧУ����
		String xxmc = Base.xxmc;
		// ����
		String title = xxmc + "���������ۺ����ʲ�����Ŀ��������ģ��";
		
		List<HashMap<String,String>> xqList = Base.getXqList();
		String xqCn = "ѧ��";
		
		for(int i=0; i<xqList.size(); i++){
			HashMap<String, String> xqMap = xqList.get(i);
			if(i==0){
				xqCn += "(";
			}
			
			xqCn += xqMap.get("xqdm") + "��" + xqMap.get("xqmc") + "��";
			
			if(i==xqList.size()-1){
				xqCn += ")";
			}
		}
		
		// ���ı���
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();

		HashMap<String, String> trMap = new HashMap<String, String>();
		trMap.put("en", "xh");
		trMap.put("cn", "ѧ��");
		topTr.add(trMap);

		trMap = new HashMap<String, String>();
		trMap.put("en", "xm");
		trMap.put("cn", "����");
		topTr.add(trMap);
		
		trMap = new HashMap<String, String>();
		trMap.put("en", "xn");
		trMap.put("cn", "ѧ��");
		topTr.add(trMap);

		trMap = new HashMap<String, String>();
		trMap.put("en", "xq");
		trMap.put("cn", xqCn);
		topTr.add(trMap);

		trMap = new HashMap<String, String>();
		trMap.put("en", "nd");
		trMap.put("cn", "���");
		topTr.add(trMap);

		topTr.addAll(dao.getPjpyExpTrList(model, map));

		// ��������
		ArrayList<String[]> expList = new ArrayList<String[]>();

		expToExcel(title, topTr, expList, os);
	}
	
	/**
	 * ����excel
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public void createXLS(String sheetName,String filePath, String fileName,
			List<HashMap<String, String>> topTr, List<String[]> list) {

		try {
			
			// Sheet��
			sheetName = Base.isNull(sheetName) ? "Sheet1" : sheetName;
			String file = filePath + "/" + fileName + ".xls";
			// open file.
			WritableWorkbook book = Workbook.createWorkbook(new File(file));

			// create Sheet named "Sheet_1". 0 means this is 1st page.
			WritableSheet sheet = book.createSheet(sheetName, 0);

			if (topTr != null && topTr.size() > 0) {
				for (int i = 0; i < topTr.size(); i++) {
					HashMap<String, String> map = topTr.get(i);
					String mc = map.get("cn");

					Label label = new Label(i, 0, mc);
					sheet.addCell(label);
				}
			}
			
			if (list != null && list.size() > 0) {

				for (int i = 0; i < list.size(); i++) {

					String[] value = list.get(i);

					for (int j = 0; j < topTr.size(); j++) {
						Label label = new Label(j, (i + 1), value[j]);
						sheet.addCell(label);
					}
				}
			}
			
			book.write();
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}    	   
}
