package xgxt.xsgygl.bjlh.fyk;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.utils.ExcelMethods;
import xgxt.xsgygl.bjlh.BjlhGyglDAO;
import xgxt.xsgygl.bjlh.BjlhGyglForm;
import xgxt.utils.String.StringUtils;

public class FykService {
	
	//	=======================���ķָ��ߣ�����luojw��===================================
	
	FykDAO dao = new FykDAO();
	
	public static final String TWDM = BjlhGyglDAO.TWDM;//��ί���Ŵ���
	public static final String TYDM = BjlhGyglDAO.TYDM;;//������ѧ�����Ŵ���
	public static final String KYDM = BjlhGyglDAO.KYDM;//���в��Ŵ���
	public static final String CJDM = BjlhGyglDAO.CJDM;//�ɽ̲��Ŵ���
	
	/**
	 * ���������б�
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception 
	 */
	public  void setList(BjlhGyglForm myForm,HttpServletRequest request) throws Exception {
		dao.setList(myForm, request);
	}
	
	/**
	 * ��ù�Ԣ��������б�
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getGyglList(String tableName, FykModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getGyglList(tableName, model, colList);
	}
	
	/**
	 * ���ѧ��ס���б�
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getXszsList(String[] colList,String pk) {
		return dao.getXszsList(colList, pk);
	}
	
	/**
	 * ����ͼ��ͳ�ƽ����
	 * 
	 * @author luojw
	 */
	public List<Object> setTbtj(FykModel model){
		
		List<Object> xyRs = new ArrayList<Object>();
		List<HashMap<String, String>> xyxxList = dao.getXyxxList(model);
		List<HashMap<String, String>> xqxxList = dao.getXqxxList(model);
		List<HashMap<String, String>> ldxxList = dao.getLdxxList(model);
		List<HashMap<String, String>> csxxList = dao.getCsxxList(model);
		List<HashMap<String, String>> qsxxList = dao.getQsxxList(model);
		
		if (xyxxList != null && xyxxList.size() > 0) {
			xyRs = appendXyList(xyxxList, xqxxList, ldxxList, csxxList, qsxxList);	
//			List<Object> qsRs = appendQsList1(csxxList, qsxxList); //����������Ϣ
//			List<Object> csRs = appendCsList1(ldxxList, csxxList, qsRs); //���ò�����Ϣ
//			List<Object> ldRs = appendLdList1(xqxxList, ldxxList, csRs); //����¥����Ϣ
//			List<Object> xqRs = appendXqList1(xyxxList, xqxxList, ldRs); //����У����Ϣ
//			xyRs = appendXyList1(xyxxList, xqRs); //����ѧԺ��Ϣ
		}	
		
		return xyRs;
	}

	/**
	 * ����ѧԺ��Ϣ�б�
	 * 
	 * @author luojw
	 */
	private List<Object> appendXyList(
			List<HashMap<String, String>> xyxxList,
			List<HashMap<String, String>> xqxxList,
			List<HashMap<String, String>> ldxxList,
			List<HashMap<String, String>> csxxList,
			List<HashMap<String, String>> qsxxList) {
		List<Object> rs = new ArrayList<Object>();
		List<HashMap<String, String>> xqxyList;
		List<Object> xqRs;
		for (int i = 0; i < xyxxList.size(); i++) {

			HashMap<String, Object> map = new HashMap<String, Object>();
			HashMap<String, String> xy = xyxxList.get(i);
			xqxyList = new ArrayList<HashMap<String, String>>();
			xqRs = new Vector<Object>();

			appendXqList(xqxxList, ldxxList, csxxList, qsxxList, xqxyList,xqRs, xy);		
			
			map.put("xymc", xy.get("xymc"));
			map.put("fjs", xy.get("fjs"));
			map.put("zcws", xy.get("zcws"));
			map.put("kcws", xy.get("kcws"));
			map.put("xlcws", xy.get("xlcws"));
			map.put("xqRs", xqRs);
			rs.add(map);
		}
		return rs;
	}

	/**
	 * ����У����Ϣ�б�
	 * 
	 * @author luojw
	 */
	private void appendXqList(List<HashMap<String, String>> xqxxList,
			List<HashMap<String, String>> ldxxList,
			List<HashMap<String, String>> csxxList,
			List<HashMap<String, String>> qsxxList,
			List<HashMap<String, String>> xqxyList, List<Object> xqRs,
			HashMap<String, String> xy) {
		List<HashMap<String, String>> xyxqldList;
		List<Object> ldRs;
		for (int j = 0; j < xqxxList.size(); j++) {
			
			HashMap<String, String> xq = xqxxList.get(j);
			HashMap<String, Object> map1 = new HashMap<String, Object>();
			
			if (xy.get("xydm").equalsIgnoreCase(xq.get("xydm"))) {
					
				ldRs =new Vector<Object>();
				xyxqldList = new ArrayList<HashMap<String,String>>();	
				xqxyList.add(xq);
				
				appendLdList(ldxxList, csxxList, qsxxList, xyxqldList, ldRs, xq);
				
				map1.put("xqMap", xq);
				map1.put("ldRs", ldRs);
				xqRs.add(map1);
			}	
		}
	}

	/**
	 * ����¥����Ϣ�б�
	 * 
	 * @author luojw
	 */
	private void appendLdList(List<HashMap<String, String>> ldxxList,
			List<HashMap<String, String>> csxxList,
			List<HashMap<String, String>> qsxxList,
			List<HashMap<String, String>> xyxqldList, List<Object> ldRs,
			HashMap<String, String> xq) {
		List<HashMap<String, String>> xyxqldcsList;
		List<Object> csRs;
		for (int k = 0; k < ldxxList.size(); k++) {
			HashMap<String, String> ld = ldxxList.get(k);
			HashMap<String, Object> map2 = new HashMap<String, Object>();
			
			if (ld.get("xydm").equalsIgnoreCase(xq.get("xydm")) && 
			    ld.get("xqdm").equalsIgnoreCase(xq.get("xqdm"))) {
				
				csRs =new Vector<Object>();
				xyxqldcsList = new ArrayList<HashMap<String,String>>();	
				xyxqldList.add(ld);
				
				appendCsList(csxxList, qsxxList, xyxqldcsList, csRs, ld);
				
				map2.put("ldMap", ld);
				map2.put("csRs", csRs);
										
				ldRs.add(map2);
			}	
		}
	}

	/**
	 * ���ò�����Ϣ�б�
	 * 
	 * @author luojw
	 */
	private void appendCsList(List<HashMap<String, String>> csxxList,
			List<HashMap<String, String>> qsxxList,
			List<HashMap<String, String>> xyxqldcsList, List<Object> csRs,
			HashMap<String, String> ld) {
		List<HashMap<String, String>> xyxqldqsList;
		List<Object> qsRs;
		for (int m = 0; m < csxxList.size(); m++) {
			HashMap<String, String> cs = csxxList.get(m);
			HashMap<String, Object> map3 = new HashMap<String, Object>();
			
			if (cs.get("xydm").equalsIgnoreCase(ld.get("xydm")) && 
				cs.get("xqdm").equalsIgnoreCase(ld.get("xqdm")) &&
				cs.get("lddm").equalsIgnoreCase(ld.get("lddm"))) {	
				qsRs =new Vector<Object>();
				xyxqldcsList.add(cs);
				
				xyxqldqsList = new ArrayList<HashMap<String,String>>();
				
				appendQsList(qsxxList, xyxqldqsList, cs);
				
				qsRs.add(xyxqldqsList);
				map3.put("csMap",cs);
				map3.put("qsRs",qsRs);
				
				csRs.add(map3);
			}
		}
	}

	/**
	 * ����������Ϣ�б�
	 * 
	 * @author luojw
	 */
	private void appendQsList(List<HashMap<String, String>> qsxxList,
			List<HashMap<String, String>> xyxqldqsList,
			HashMap<String, String> cs) {
		for (int n = 0; n < qsxxList.size(); n++) {
			HashMap<String, String> qs = qsxxList.get(n);
			if (qs.get("xydm").equalsIgnoreCase(cs.get("xydm")) && 
				qs.get("xqdm").equalsIgnoreCase(cs.get("xqdm")) &&
				qs.get("lddm").equalsIgnoreCase(cs.get("lddm")) &&
				qs.get("cs").equalsIgnoreCase(cs.get("cs"))) {
				xyxqldqsList.add(qs);
			}				
		}
	}

	/**
	 * ����ѧԺ��Ϣ�б�
	 * 
	 * @author luojw
	 */
	@SuppressWarnings("unused")
	private List<Object> appendXyList1(List<HashMap<String, String>> xyxxList,
			List<Object> xqRs) {
		List<Object> xyRs = new ArrayList<Object>();
		int n = 0;
		for (int i = 0; i < xyxxList.size(); i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			HashMap<String, String> xy = xyxxList.get(i);

			map.put("xymc", xy.get("xymc"));
			map.put("fjs", xy.get("fjs"));
			map.put("zcws", xy.get("zcws"));
			map.put("kcws", xy.get("kcws"));
			map.put("xlcws", xy.get("xlcws"));
			for (int j = 0; j < xqRs.size(); j++) {
			
			}
			map.put("xqRs", xqRs.get(n));
			n++;
			xyRs.add(map);
		}
		return xyRs;
	}
	
	/**
	 * ����У����Ϣ�б�
	 * 
	 * @author luojw
	 */
	@SuppressWarnings("unused")
	private List<Object> appendXqList1(List<HashMap<String, String>> xyxxList,
			List<HashMap<String, String>> xqxxList, List<Object> ldRs) {
		List<Object> xqRs = new ArrayList<Object>();
		int n = 0;
		for (int i = 0; i < xyxxList.size(); i++) {
			HashMap<String, String> xy = xyxxList.get(i);
			for (int j = 0; j < xqxxList.size(); j++) {
				HashMap<String, String> xq = xqxxList.get(j);
				HashMap<String, Object> map = new HashMap<String, Object>();

				if (xy.get("xydm").equalsIgnoreCase(xq.get("xydm"))) {

					map.put("xqMap", xq);
					map.put("ldRs", ldRs.get(n));
					n++;
					xqRs.add(map);
				}
			}
		}
		return xqRs;
	}
	
	/**
	 * ����¥����Ϣ�б�
	 * 
	 * @author luojw
	 */
	@SuppressWarnings("unused")
	private List<Object> appendLdList1(List<HashMap<String, String>> xqxxList,
			List<HashMap<String, String>> ldxxList, List<Object> csRs) {
		List<Object> ldRs = new ArrayList<Object>();
		int n = 0;
		for (int i = 0; i < xqxxList.size(); i++) {
			HashMap<String, String> xq = xqxxList.get(i);
			for (int j = 0; j < ldxxList.size(); j++) {
				HashMap<String, String> ld = ldxxList.get(j);
				HashMap<String, Object> map = new HashMap<String, Object>();

				if (ld.get("xydm").equalsIgnoreCase(xq.get("xydm"))
						&& ld.get("xqdm").equalsIgnoreCase(xq.get("xqdm"))) {

					map.put("ldMap", ld);
					map.put("csRs", csRs.get(n));
					n++;
					ldRs.add(map);
				}
			}
		}
		return ldRs;
	}
	
	/**
	 * ���ò�����Ϣ�б�
	 * 
	 * @author luojw
	 */
	@SuppressWarnings("unused")
	private List<Object> appendCsList1(List<HashMap<String, String>> ldxxList,
			List<HashMap<String, String>> csxxList, List<Object> qsRs) {
		List<Object> csRs = new ArrayList<Object>();
		int n = 0;
		for (int i = 0; i < ldxxList.size(); i++) {
			HashMap<String, String> ld = ldxxList.get(i);
			for (int j = 0; j < csxxList.size(); j++) {
				HashMap<String, String> cs = csxxList.get(j);
				HashMap<String, Object> map = new HashMap<String, Object>();

				if (cs.get("xydm").equalsIgnoreCase(ld.get("xydm"))
						&& cs.get("xqdm").equalsIgnoreCase(ld.get("xqdm"))
						&& cs.get("lddm").equalsIgnoreCase(ld.get("lddm"))) {

					map.put("csMap", cs);
					map.put("qsRs", qsRs.get(n));
					n++;
					csRs.add(map);
				}
			}
		}
		return csRs;
	}
	
	/**
	 * ����������Ϣ�б�
	 * 
	 * @author luojw
	 */
	@SuppressWarnings("unused")
	private List<Object> appendQsList1(List<HashMap<String, String>> csxxList,
			List<HashMap<String, String>> qsxxList) {
		List<Object> qsRs = new ArrayList<Object>();
		for (int i = 0; i < csxxList.size(); i++) {
			HashMap<String, String> cs = csxxList.get(i);
			List<HashMap<String, String>> xyxqldqsList = new ArrayList<HashMap<String, String>>();
			for (int j = 0; j < qsxxList.size(); j++) {
				HashMap<String, String> qs = qsxxList.get(j);
				if (qs.get("xydm").equalsIgnoreCase(cs.get("xydm"))
						&& qs.get("xqdm").equalsIgnoreCase(cs.get("xqdm"))
						&& qs.get("lddm").equalsIgnoreCase(cs.get("lddm"))
						&& qs.get("cs").equalsIgnoreCase(cs.get("cs"))) {
					xyxqldqsList.add(qs);
				}
			}
			qsRs.add(xyxqldqsList);
		}
		return qsRs;
	}
	
	/**
	 * ����������ͳ�Ʊ�
	 */
	public void printTbtj(FykModel model, OutputStream os)
			throws Exception {

		List<HashMap<String, String>> xyxxList = dao.getXyxxList(model);
		List<HashMap<String, String>> xqxxList = dao.getXqxxList(model);
		List<HashMap<String, String>> ldxxList = dao.getLdxxList(model);
		List<HashMap<String, String>> csxxList = dao.getCsxxList(model);
		List<HashMap<String, String>> qsxxList = dao.getQsxxList(model);
		List<HashMap<String, String>> printList = new ArrayList<HashMap<String, String>>(0);
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		
		// �������
		String title = "��Դ����䣨ͼ��ͳ�Ʊ�";
		WritableSheet ws = wwb.createSheet("��Դ����䣨ͼ��ͳ�Ʊ�", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		WritableCellFormat wcf3 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf3 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 10, 1);
		ex.printToOneCell_multy(ws, title, 0, 0, 12, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);
		if (xyxxList != null && xyxxList.size() > 0) {
			
			for (int i = 0; i < xyxxList.size(); i++) {
				
				HashMap<String, String> xy = xyxxList.get(i);
				xy.put("lx", "xy");
				printList.add(xy);
				
				if (xqxxList != null && xqxxList.size() > 0) {
					for (int j = 0; j < xqxxList.size(); j++) {
						
						HashMap<String, String> xq = xqxxList.get(j);
						
						if (xy.get("xydm").equalsIgnoreCase(xq.get("xydm"))) {
							xq.put("lx", "xq");
							printList.add(xq);

							if (ldxxList != null && ldxxList.size() > 0) {
								for (int k = 0; k < ldxxList.size(); k++) {
									HashMap<String, String> ld = ldxxList.get(k);
									if (ld.get("xydm").equalsIgnoreCase(xq.get("xydm"))&& 
										ld.get("xqdm").equalsIgnoreCase(xq.get("xqdm"))) {
										ld.put("lx", "ld");
										printList.add(ld);

										if (csxxList != null&& csxxList.size() > 0) {
											for (int m = 0; m < csxxList.size(); m++) {
												HashMap<String, String> cs = csxxList.get(m);
												if (cs.get("xydm").equalsIgnoreCase(ld.get("xydm"))&& 
													cs.get("xqdm").equalsIgnoreCase(ld.get("xqdm"))&&
													cs.get("lddm").equalsIgnoreCase(ld.get("lddm"))) {
													cs.put("lx", "cs");
													printList.add(cs);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		if (printList != null && printList.size() > 0) {
			int num = 0;
			int cs = 0;
			int qs = 0;
			int qsRow = 0;
			StringBuffer nr = new StringBuffer();
			for (int i = 0; i < printList.size(); i++) {
				
				HashMap<String, String> map = printList.get(i);
				String lx = map.get("lx");
				
				nr = new StringBuffer();
				nr.append("������:"+ map.get("fjs"));
				nr.append("��λ��:"+ map.get("zcws"));
				nr.append("�մ�λ��:"+ map.get("kcws"));
				nr.append("���λ��:"+ map.get("xlcws"));
				
				if ("xy".equalsIgnoreCase(lx)) {	
					ws.addCell(new Label(0, 2 + i + num + cs + qsRow*5, map.get("xymc"), wcf2));
					ws.mergeCells(0, 2 + i + num + cs + qsRow*5,10, 2 + i + num + cs + qsRow*5);
					num++;
				} else if ("xq".equalsIgnoreCase(lx)) {
					ws.addCell(new Label(0, 2 + i + num + cs + qsRow*5, map.get("xqmc"), wcf2));
					ws.mergeCells(0, 2 + i + num + cs+ qsRow*5,10, 2 + i + num + cs+ qsRow*5);
					num++;
				} else if ("ld".equalsIgnoreCase(lx)) {
					ws.addCell(new Label(0, 2 + i + num + cs + qsRow*5, map.get("ldmc"), wcf2));
					ws.mergeCells(0, 2 + i + num + cs+ qsRow*5,10, 2 + i + num + cs+ qsRow*5);
					num++;
				} else if ("cs".equalsIgnoreCase(lx)) {
					qs = cs;
					ws.addCell(new Label(0, 2 + i + num + cs + qsRow*5, "��"+map.get("cs")+"��", wcf2));
					num++;
					ws.addCell(new Label(0, 2 + i + num + cs + qsRow*5, "������:"+ map.get("fjs"), wcf2));
					cs ++;
					ws.addCell(new Label(0, 2 + i + num + cs + qsRow*5, "��λ��:"+ map.get("zcws"), wcf2));
					cs ++;
					ws.addCell(new Label(0, 2 + i + num + cs + qsRow*5, "�մ�λ��:"+ map.get("kcws"), wcf2));
					cs ++;
					ws.addCell(new Label(0, 2 + i + num + cs + qsRow*5, "���λ��:"+ map.get("xlcws"), wcf2));
					if (qsxxList != null && qsxxList.size() > 0) {
						int qss = 0;
						int qscs = num;
						for (int j = 0; j < qsxxList.size(); j++) {
							int m = 1;
							HashMap<String, String> qsMap = qsxxList.get(j);
							if(qsMap.get("xydm").equalsIgnoreCase(map.get("xydm")) &&
							   qsMap.get("xqdm").equalsIgnoreCase(map.get("xqdm")) &&
							   qsMap.get("lddm").equalsIgnoreCase(map.get("lddm")) &&
							   qsMap.get("cs").equalsIgnoreCase(map.get("cs")) ){
								
								if (qss !=0 && qss % 10 == 0) {
									qsRow++;
									qss = 0;
									ws.addCell(new Label(0, 1 + i + qscs + qs+qsRow*5, "", wcf2));
									ws.addCell(new Label(0, 1 + i + qscs + qs + 1+qsRow*5, "", wcf2));
									ws.addCell(new Label(0, 1 + i + qscs + qs + 2+qsRow*5, "", wcf2));
									ws.addCell(new Label(0, 1 + i + qscs + qs + 3+qsRow*5, "", wcf2));
									ws.addCell(new Label(0, 1 + i + qscs + qs + 4+qsRow*5, "", wcf2));
								}
								ws.addCell(new Label(1+qss, 1 + i + qscs + qs+qsRow*5, qsMap.get("qsh")+"("+qsMap.get("zt")+")", wcf3));
								ws.addCell(new Label(1+qss, 1 + i + qscs + qs + m+qsRow*5, "�ܴ�λ��:"+ qsMap.get("cws"), wcf2));
								m++;
								ws.addCell(new Label(1+qss, 1 + i + qscs + qs + m+qsRow*5, "���λ��:"+ qsMap.get("xlcws"), wcf2));
								m++;
								ws.addCell(new Label(1+qss, 1 + i + qscs + qs + m+qsRow*5, "��ס��λ:"+ qsMap.get("bxyyzrs"), wcf2));
								m++;
								ws.addCell(new Label(1+qss, 1 + i + qscs + qs + m+qsRow*5, "���д�λ:"+ qsMap.get("kcws"), wcf2));
								qss++;
							}
						}
						
						for (int j = 0; j < (10 - qss % 10); j++) {
							ws.addCell(new Label(1+qss+j, 1 + i + qscs + qs+qsRow*5, "", wcf2));
							ws.addCell(new Label(1+qss+j, 1 + i + qscs + qs + 1+qsRow*5, "", wcf2));
							ws.addCell(new Label(1+qss+j, 1 + i + qscs + qs + 2+qsRow*5, "", wcf2));
							ws.addCell(new Label(1+qss+j, 1 + i + qscs + qs + 3+qsRow*5, "", wcf2));
							ws.addCell(new Label(1+qss+j, 1 + i + qscs + qs + 4+qsRow*5, "", wcf2));
						}
					}			
				}
				if (!"cs".equalsIgnoreCase(lx)) {
					ws.addCell(new Label(0, 2 + i + num + cs + qsRow*5, nr.toString(), wcf2));
					ws.mergeCells(0, 2 + i + num + cs + qsRow*5, 10, 2 + i + num + cs + qsRow*5);
				}
				
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	//	=======================���ķָ��ߣ�����luojw��===================================
	
	/**
	 * ͨ��ѧԺ�����ѯѧԺ����
	 * @param xydm  ҳ�洫������ѧԺ����
	 * @param xyList ѧԺ�б�
	 * @return
	 */
	public String getXymc(String xydm, String fpbj, List<HashMap<String, String>> xyList) {
			if (TWDM.equalsIgnoreCase(fpbj)) {
				return "��ί";
			} else if (TYDM.equalsIgnoreCase(fpbj)) {
				return "������ѧ��";
			} else if (KYDM.equalsIgnoreCase(fpbj)) {
				return "���д�";
			} else if (CJDM.equalsIgnoreCase(fpbj)) {
				return "���˽�����";
			}
			for (int i = 0; i < xyList.size(); i++) {
				if (!StringUtils.isNull(xydm) && xydm.equalsIgnoreCase(xyList.get(i).get("xydm"))) {
					return xyList.get(i).get("xymc");
				}
			}
			return "";
	}
	
	/**
	 * ͨ��У�������ѯУ������
	 * @param xqdm
	 * @param xqdmList
	 * @return
	 */
	public String getXqdmmc(String xqdm, List<HashMap<String, String>> xqdmList) {
		if (StringUtils.isNull(xqdm) || xqdmList == null) {
			return "";
		} else {
			for (HashMap<String, String> map : xqdmList) {			
				if (xqdm.equalsIgnoreCase(map.get("dm"))) {
					return map.get("mc");
				}
			}
			return "";
		}
	}
	
	/**
	 * ���ѧԺ�б�Ϊ�գ���������ѧԺ����ķ�����������λ�������մ�λ���������λ����
	 * 
	 * @param model
	 * @return
	 */
	public List<String[]> queryXyfjsInfo(FykModel model) throws Exception {
		return dao.queryXyfjsInfo(model);
	}
	
	/**
	 * ��ѯһ��ѧԺ����ĸ�У������ķ�����������λ�������մ�λ���������λ����
	 * @param xqdmList
	 * @param model
	 * @return
	 */
	public List<String[]> queryXyXqfjsInfo(FykModel model) throws Exception {
		return dao.queryXyXqfjsInfo(model);
	}
	
	/**
	 * ��ѯһ��ѧԺ����ĸ�У�������¥������ķ�����������λ�������մ�λ���������λ����
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXyXqLdfjsInfo(FykModel model) throws Exception {
		return dao.queryXyXqLdfjsInfo(model);
	}
	
	/**
	 * ��Դ���ѯ��Ϣ
	 * @param model
	 * @param xqdmList
	 * @param xydmList
	 * @param xyList
	 * @param type ��������ҳ��չ��ʱҪ����ʽ������ʱ���ü�
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryTjxx(FykModel model, List<HashMap<String, String>> xqdmList,
			List<HashMap<String, String>> xyList, boolean type) throws Exception{
		//���������������ѯ
		if ("������".equalsIgnoreCase(model.getZt())) {
			return queryBlfyxx(model, type);
		} else {//�������ݲ�ѯ
			if ("��ѧԺ".equalsIgnoreCase(model.getSclx())) {//��ѧԺ�������
				return xyxqldFjshz(model, xqdmList, type);
			} else {//��У���������
				return xqldxyFjstj(model, xqdmList, xyList, type);
			}
		}
	}
	
	/**
	 * ѧԺУ��¥��������� 
	 *    ����ѭ��ѧԺ�ܷ�����
	 *       ��ѭ��ѧԺ�����У���ܷ�����
	 *          ��ѭ��ѧԺ�����У�������¥���ķ�����
	 * @param model
	 * @param xqdmList
	 * @return  ѧԺУ��¥���б����� ��ʽ:List<String[]>
	 * @throws Exception
	 */
	public List<String[]> xyxqldFjshz(FykModel model,
			List<HashMap<String, String>> xqdmList, boolean type) throws Exception {
		List<String[]> rs = new ArrayList<String[]>();
		
		//ѧԺ��������
		List<String[]> xyfjsList = queryXyfjsInfo(model);
		//ѧԺ����ĸ�У��������
		List<String[]> xyxqfjsList = queryXyXqfjsInfo(model);
		//ѧԺ����ĸ�У����¥��������
		List<String[]> xyxqldfjsList = queryXyXqLdfjsInfo(model); 
		if (xyfjsList != null) {
			for (int l=0;l<xyfjsList.size();l++) {
				String[] xyOnedata = xyfjsList.get(l);
				if (xyOnedata != null && xyOnedata.length == 6) {
					String xydm = xyOnedata[0];
					rs.add(getXyfjsList(xyOnedata,type));
					if (xyxqfjsList != null) {
						for (int i=0;i<xyxqfjsList.size();i++) {
							String[] oneData = xyxqfjsList.get(i);
							if (oneData != null && oneData.length == 6) {
								if (StringUtils.isNull(oneData[0])) {
									continue;
								} else {
									//ѭ��У������
									String xqdm = StringUtils.isNull(oneData[1]) ? "" : oneData[1];
									String xqmc = getXqdmmc(xqdm, xqdmList);
									if (xydm.equalsIgnoreCase(oneData[0])) {
										//��ÿ��У�����������ӽ�ȥ
										rs.add(getXqfjsArray(oneData, xqmc,type));
										if (xyxqldfjsList != null) {
											for (int j=0;j<xyxqldfjsList.size();j++) {
												//ѭ��У����¥������
												String[] xyxqldfjsoneData = xyxqldfjsList.get(j);
												if (xyxqldfjsoneData != null && xyxqldfjsoneData.length == 8) {
													//У��������¥����������ͬ�Ļ�
													if (xydm.equalsIgnoreCase(xyxqldfjsoneData[0]) && xqdm.equalsIgnoreCase(xyxqldfjsoneData[1])) {
														//ѭ��ÿ��¥������ķ��������ӽ�ȥ
														rs.add(getLdfjsList(xyxqldfjsoneData,type));
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return rs;
	}
	
	/**
	 * ��ÿ��¥������ķ�������תΪ�б�
	 * @param xyxqldfjsoneData
	 * @return
	 */
	public String[] getLdfjsList(String[] xyxqldfjsoneData, boolean type) {
		String[] oneData = new String[xyxqldfjsoneData.length - 3];
		for (int i=3;i<xyxqldfjsoneData.length;i++) {
			if (i==3) {
				if (type) {
					oneData[i-3] = "<font color='green'><p align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + xyxqldfjsoneData[i] + "</p></font>";
				} else {
					oneData[i-3] = xyxqldfjsoneData[i];
				}
			} else {
				oneData[i-3] = xyxqldfjsoneData[i];
			}
		}
		return oneData;
	}
	
	/**
	 * ��ÿ��¥������ķ�������תΪ�б�
	 * @param xyxqldfjsoneData
	 * @return
	 */
	public String[] getXyfjsList(String[] xyfjsoneData, boolean type) {
		String[] oneData = new String[xyfjsoneData.length - 1];
		for (int i=1;i<xyfjsoneData.length;i++) {
			if (i==1) {
				if (type) {
					oneData[i-1] = "<b><p align='left' style='font-size: 20px'>" + xyfjsoneData[i] + "</p></b>";
				} else {
					oneData[i-1] = xyfjsoneData[i];
				}
			} else {
				oneData[i-1] = xyfjsoneData[i];
			}
		}
		return oneData;
	}
	
	public String[] getXqfjsArray(String[] oneData, String xqmc, boolean type) {
		String[] rs = new String[oneData.length-1];
		if (type) {
			rs[0] = "<font color='red'><p align='left' style='font-size: 15px'>&nbsp;&nbsp;&nbsp;&nbsp;" + xqmc + "</p></font>";
		} else {
			rs[0] = xqmc;
		}
		for (int i=2;i<oneData.length;i++) {
			rs[i-1] = oneData[i];
		}
		return rs;
	}
	
	public List<HashMap<String, String>> getSclxList() {
		String[] en  = new String[]{"��ѧԺ", "��У��"};
		return dao.getList(en, en);
	}
	
	/**
	 * ��У��,¥��,ѧԺͳ�Ʒ�������Ϣ
	 *    ��ʽ : У��������
	 *             ¥��������
	 *                ѧԺ������
	 * @param model
	 * @return
	 */
	public List<String[]> xqldxyFjstj(FykModel model,
			List<HashMap<String, String>> xqdmList,
			List<HashMap<String, String>> xyList, boolean type) throws Exception {
		List<String[]> rs = new ArrayList<String[]>();
		
		//У��������
		List<String[]> xqfjsList = dao.queryXqfjs(model);
		//У��¥��������
		List<String[]> xqldfjsList = dao.queryXqldfjs(model);
		//У��¥��ѧԺ������
		List<String[]> xqldxyfjsList = dao.queryXqldxyfjs(model);
		
		appendFjtjMehto(xqdmList, xyList, type, rs, xqfjsList, xqldfjsList, xqldxyfjsList);
		return rs;
	}

	/**
	 * ����ѯ�����Ľ����������װ
	 * ��ʽ : У��������
	 *             ¥��������
	 *                ѧԺ������
	 * @param xqdmList
	 * @param xyList
	 * @param type
	 * @param rs
	 * @param xqfjsList
	 * @param xqldfjsList
	 * @param xqldxyfjsList
	 */
	private void appendFjtjMehto(List<HashMap<String, String>> xqdmList, List<HashMap<String, String>> xyList, boolean type, List<String[]> rs, List<String[]> xqfjsList, List<String[]> xqldfjsList, List<String[]> xqldxyfjsList) {
		if (xqfjsList != null && xqfjsList.size() > 0) {
			//��һ��ѭ��У��������
			for (int i=0;i<xqfjsList.size();i++) {
				String[] xqfjsOneData = xqfjsList.get(i);
				if (xqfjsOneData != null && xqfjsOneData.length == 5) {
					String xqdm = xqfjsOneData[0];
					rs.add(getEveryXqfjs(xqfjsOneData, xqdmList,type));
					//�ڶ���ѭ��У�������¥��������
					if (xqldfjsList != null) {
						for (int j=0;j<xqldfjsList.size();j++) {
							String[] xqldfjsOneData = xqldfjsList.get(j);
							if (xqldfjsOneData != null && xqldfjsOneData.length == 7) {
								if (xqdm.equalsIgnoreCase(xqldfjsOneData[0])) {
									String lddm = xqldfjsOneData[1];
									rs.add(getEveryXqldfjs(xqldfjsOneData,type));
									//������ѭ��У������¥�������ѧԺ������
									for (int k=0;k<xqldxyfjsList.size();k++) {
										String[] xqldxyfjsOneData = xqldxyfjsList.get(k);
										if (xqldxyfjsOneData != null && xqldxyfjsOneData.length == 7) {
											if (xqdm.equalsIgnoreCase(xqldxyfjsOneData[0]) && lddm.equalsIgnoreCase(xqldxyfjsOneData[1])) {
												rs.add(getEveryXqldxyfjs(xqldxyfjsOneData, xyList,type));
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * ����ѯ������ÿ��У����������У�������滻��У������
	 * @param array
	 * @param xqdmList
	 * @return
	 */
	public String[] getEveryXqfjs(String[] array, List<HashMap<String, String>> xqdmList, boolean type) {
		String xqdm = StringUtils.isNull(array[0]) ? "" : array[0].trim();
		if (xqdmList != null) {
			for (HashMap<String, String> map : xqdmList) {
				if (xqdm.equalsIgnoreCase(map.get("dm"))) {
					if (type) {
						array[0] = "<b><p align='left' style='font-size: 20px'>" + map.get("mc") + "</p></b>";
					} else {
						array[0] = map.get("mc");
					}
					break;
				}
			}
		}
		return array;
	}
	
	/**
	 * ����ѯ������ÿ��У��¥����������¥�������滻��¥������
	 * @param array
	 * @return
	 */
	public String[] getEveryXqldfjs(String[] array, boolean type) {
		String[] rs = new String[array.length - 2];
		for (int i=2;i<array.length;i++) {
			if (i==2) {
				if (type) {
					rs[i-2] = "<font color='red'><p align='left' style='font-size: 15px'>&nbsp;&nbsp;&nbsp;&nbsp;" + array[i] + "</p></font>";
				} else {
					rs[i-2] = array[i];
				}
			} else {
				rs[i-2] = array[i];
			}
		}
		return rs;
	}
	
	/**
	 * ����ѯ������ÿ��У��¥��ѧԺ��������ѧԺ�����滻��ѧԺ����
	 * @param array
	 * @param xyList
	 * @return
	 */
	public String[] getEveryXqldxyfjs(String[] array,
			List<HashMap<String, String>> xyList, boolean type) {
		String[] rs = new String[array.length - 2];
		for (int i = 2; i < array.length; i++) {
			if (i == 2) {
				if (type) {
					String xymc = "<font color='green'><p align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
					if (TWDM.equalsIgnoreCase(array[i])) {
						xymc += "��ί";
					} else if (TYDM.equalsIgnoreCase(array[i])) {
						xymc += "������ѧ��";
					} else if (KYDM.equalsIgnoreCase(array[i])) {
						xymc += "���д�";
					} else if (CJDM.equalsIgnoreCase(array[i])) {
						xymc += "���˽�����";
					} else {
						for (HashMap<String, String> map : xyList) {
							if (StringUtils.isNotNull(array[i])
									&& array[i].equalsIgnoreCase(map.get("xydm"))) {
								xymc += map.get("xymc");
							}
						}
					}
					xymc += "</p></font>";
					rs[i - 2] = xymc;
				} else {
					String xymc = "";
					if (TWDM.equalsIgnoreCase(array[i])) {
						xymc += "��ί";
					} else if (TYDM.equalsIgnoreCase(array[i])) {
						xymc += "������ѧ��";
					} else if (KYDM.equalsIgnoreCase(array[i])) {
						xymc += "���д�";
					} else if (CJDM.equalsIgnoreCase(array[i])) {
						xymc += "���˽�����";
					} else {
						for (HashMap<String, String> map : xyList) {
							if (StringUtils.isNotNull(array[i])
									&& array[i].equalsIgnoreCase(map.get("xydm"))) {
								xymc += map.get("xymc");
							}
						}
					}
					rs[i - 2] = xymc;
				}
				
			} else {
				rs[i - 2] = array[i];
			}
		}
		return rs;
	}
	
	/**
	 * ������Դ��ͳ������
	 * @param wwb
	 * @param model
	 * @param xqdmList
	 * @param xyList
	 * @throws Exception
	 */
	public void expQueryFytjxx(WritableWorkbook wwb, FykModel model, List<HashMap<String, String>> xqdmList,
			List<HashMap<String, String>> xyList) throws Exception{
		//��ѯ�����
		List<String[]> rs = queryTjxx(model, xqdmList, xyList,false);
		WritableSheet ws = wwb.createSheet(Base.xxmc + "��Դ��ͳ����Ϣ", 0);
		WritableCellFormat wcfStyle = new WritableCellFormat();
		WritableCellFormat style = new WritableCellFormat();
		WritableFont font = new WritableFont(WritableFont.ARIAL, 13);

		//�����ʽ����ʽ
		Alignment alignMent = Alignment.CENTRE;
		VerticalAlignment va = VerticalAlignment.CENTRE;
		style.setAlignment(alignMent);
		style.setBorder(Border.ALL, BorderLineStyle.THIN);
		style.setFont(font);
		style.setVerticalAlignment(va);
		
		Alignment am = Alignment.LEFT;
		wcfStyle.setFont(font);
		wcfStyle.setVerticalAlignment(va);
		wcfStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
		wcfStyle.setAlignment(am);
		
		//�����ͷ
		ExcelMB ex = new ExcelMB();
		ex.printToOneCell_multy(ws, Base.xxmc + "��Դ��ͳ����Ϣ", 0, 0, 12, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);
		//ws.addCell(new Label(0,0,Base.xxmc + "��Դ��ͳ����Ϣ",style));
		ws.mergeCells(0, 0, 7, 0);

		if (rs != null && rs.size() > 0) {
			for (int i=0;i<rs.size();i++) {
				String[] oneData = rs.get(i);
				if (oneData != null && oneData.length == 5) {
					if (i==0) {
						//��һ�����ʱ
						//ֻ���ѧԺ��У����¥������
						ws.addCell(new Label(0,i+1,oneData[0],wcfStyle)); 
						ws.mergeCells(0, i+1, 7, i+1);//�ϲ���Ԫ��
						
						//������巿������
						ws.addCell(new Label(0,i+2,"��������",style));   
						ws.addCell(new Label(1,i+2,oneData[1],style));   
						ws.addCell(new Label(2,i+2,"��λ����",style));
						ws.addCell(new Label(3,i+2,oneData[2],style));
						ws.addCell(new Label(4,i+2,"�մ�λ����",style));
						ws.addCell(new Label(5,i+2,oneData[3],style));
						ws.addCell(new Label(6,i+2,"���λ����",style));
						ws.addCell(new Label(7,i+2,oneData[4],style));
					} else {
						//ֻ���ѧԺ��У����¥������
						ws.addCell(new Label(0,i*2+1,oneData[0],wcfStyle));  
						ws.mergeCells(0, i*2+1, 7, i*2+1);//�ϲ���Ԫ��
						
						//������巿������
						ws.addCell(new Label(0,i*2+2,"��������",style));   
						ws.addCell(new Label(1,i*2+2,oneData[1],style));   
						ws.addCell(new Label(2,i*2+2,"��λ����",style));
						ws.addCell(new Label(3,i*2+2,oneData[2],style));
						ws.addCell(new Label(4,i*2+2,"�մ�λ����",style));
						ws.addCell(new Label(5,i*2+2,oneData[3],style));
						ws.addCell(new Label(6,i*2+2,"���λ����",style));
						ws.addCell(new Label(7,i*2+2,oneData[4],style));
					}
					ws.setColumnView(i+2, 15);
				}
			}
		} else {
			ws.addCell(new Label(0,1,"���޼�¼",style));
			ws.mergeCells(0, 1, 7, 1);
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);//������ͻ���
	}
	
	/**
	 * ��ѯ����У��������ͳ����Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryBlfyxx(FykModel model, boolean type) throws Exception{
		List<String[]> rs = new ArrayList<String[]>();
		//У����������Ϣ
		List<String[]> blxqfjsList = dao.queryBlXqfjs(model);
		//У��¥����������Ϣ
		List<String[]> blxqldfjsList = dao.queryBlxqldfjs(model);
		if (blxqfjsList != null) {
			//��һ��ѭ��У��������
			for (int i=0;i<blxqfjsList.size();i++) {
				String[] xqfjsOneData = blxqfjsList.get(i);
				if (xqfjsOneData != null && xqfjsOneData.length == 6) {
					String xqdm = xqfjsOneData[0];
					rs.add(getEveryBlxqfjs(xqfjsOneData, type));
					if (blxqldfjsList != null) {
						//�ڶ���ѭ��У��¥��������
						for (int j=0;j<blxqldfjsList.size();j++) {
							String[] blxqldfjsOneData = blxqldfjsList.get(j);
							if (blxqldfjsOneData != null && blxqldfjsOneData.length == 7) {
								if (xqdm.equalsIgnoreCase(blxqldfjsOneData[0])) {
									rs.add(getEveryXqldfjs(blxqldfjsOneData, type));
								}
							}
						}
					}
				}
			}
		}
		return rs;
	}
	
	/**
	 * ��ȡ����У��������
	 * @param array
	 * @return
	 */
	public String[] getEveryBlxqfjs(String[] array, boolean type) {
		String[] rs = new String[array.length - 1];
		for (int i=1;i<array.length;i++) {
			if (i==1 && type) {
				rs[i - 1] = "<b><p align='left' style='font-size: 20px'>"
						+ array[i] + "</p></b>";
			} else {
				rs[i-1] = array[i];
			}
		}
		return rs;
	}
}
