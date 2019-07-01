package xgxt.xsgygl.zjcm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.SaveForm;
import xgxt.pjpy.cqdzgc.PjpyCqdzgcDao;
import xgxt.pjpy.cqdzgc.PjpyCqdzgcForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;
import xgxt.xsgygl.GyglTyForm;
import xgxt.xsgygl.GyglTyService;

public class ZjcmGyglService extends GyglTyService {

	ZjcmGyglDAO dao = new ZjcmGyglDAO();

	/**
	 * �ж����ݿ����������ȼ��Ƿ�ά��
	 * 
	 * @author luo
	 */
	public Boolean isWsjcdj() {
		return dao.isWsjcdj();
	}
	
	/**
	 * �����ܴα���Ϣ
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public Boolean updateZc(ZjcmGyglModel model) throws Exception {

		String xqzs = dao.getXtZsInfo().get("xqzs");
		String xn = Base.currXn;
		String xq = Base.currXq;
		xqzs = Base.isNull(xqzs) ? "0" : xqzs;
		
		boolean result = true;

		if (dao.isZcYz(xqzs, xn, xq)) {

			String realTable = "gygl_xnxqzsb";

			if (!"0".equalsIgnoreCase(xqzs)) {
				int zs = Integer.parseInt(xqzs);
				String[] zc = new String[zs];
				for (int i = 0; i < zs; i++) {
					if (i < 9) {
						zc[i] = "0" + String.valueOf(i + 1);
					}else{
						zc[i] = String.valueOf(i + 1);
					}
				}

				String[] arrzd = new String[] { "zc" };
				String[] onezd = new String[] { "xn", "xq" };
				String pk = "xn||xq";
				String pkValue = xn + xq;

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setArrzd(arrzd);
				saveForm.setOnezd(onezd);
				saveForm.setPk(pk);
				saveForm.setPkValue(new String[] { pkValue });

				model.setXn(xn);
				model.setXq(xq);
				//model.setZc(zc);

				result = saveGygl(saveForm, model);
			} else {
				String pk = "xn||xq";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(new String[] { xn + xq });

				result = delGygl(saveForm, model);
			}
		}

		return result;
	}
	
	/**
	 * ���ر����ܴ�
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public String getNowZc() throws Exception {
		
		String nowTime = getNowTime("2");
		int dqzc = GetTime.getDqzs(nowTime);
		String zc = (dqzc < 10) ? "0" + String.valueOf(dqzc) : String.valueOf(dqzc);
		
		return zc;
	}

	/**
	 * �����������б�
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getWsjcList(String tableName, ZjcmGyglModel model,
			String[] colList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		//�����������б�
		String kssj = model.getKssj();
		String jssj = model.getJssj();
		String other_query = "";
		
		if (!Base.isNull(kssj)) {
			other_query += " and jcsj >='" + kssj + "'";
		}
		if (!Base.isNull(jssj)) {
			other_query += " and jcsj <='" + jssj + "'";
		}
		
		ArrayList<String[]> list = dao.getGyglList(tableName, model, colList, other_query);
		ArrayList<String[]> wsjcList = new ArrayList<String[]>();
		if (list != null && list.size() != 0) {
			//����������ȼ��б�
			List<HashMap<String, String>> wsdjList = getWsjcList();

			for (int i = 0; i < list.size(); i++) {
				//�ع��������ʹ��������б�����һ��ֵ���������ȼ���
				String[] rs = new String[list.get(i).length + 1];
				for (int k = 0; k < list.get(i).length; k++) {
					rs[k] = list.get(i)[k];
				}
				String fs = rs[8];
				if (!Base.isNull(fs)) {
					if (wsdjList != null && wsdjList.size() != 0) {
						for (int j = 0; j < wsdjList.size(); j++) {
							HashMap<String, String> map = wsdjList.get(j);
							// �ȼ�����ֵ
							int minFs = Integer.parseInt(map.get("xxf"));
							// �ȼ�����ֵ
							int maxFs = Integer.parseInt(map.get("sxf"));
							// �жϷ����Ƿ���������
							if (Integer.parseInt(fs) >= minFs && Integer.parseInt(fs) <= maxFs) {
								rs[list.get(i).length] = map.get("mc");
							}
						}
					}
				}
				wsjcList.add(rs);
			}
		}
		return wsjcList;
	}
	
	/**
	 * ����������ȼ��б�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getWsjcList() {
		String tableName = "zjcm_wsjcdjb";
		String[] colList = new String[] { "mc", "xxf", "sxf" };
		String query = " order by to_number(sxf) desc";
		List<HashMap<String, String>> wsdjList = CommonQueryDAO.commonQueryforList(tableName, query, new String[] {}, colList,"");
		return wsdjList;
	}
	
	/**
	 * ����������ȼ���ʾ��Ϣ
	 * 
	 * @author luojw
	 */
	public String getWsdjMsg() {
		StringBuffer msg = new StringBuffer();
		List<HashMap<String, String>> wsdjList = getWsjcList();
		if (wsdjList != null && wsdjList.size() > 0) {
			for (int i = 0; i < wsdjList.size(); i++) {
				HashMap<String,String> map = wsdjList.get(i);
				msg.append(map.get("mc"));
				msg.append("��");
				msg.append(map.get("xxf"));
				msg.append("-");
				msg.append(map.get("sxf"));
				msg.append("��");
			}
		}
		return msg.toString();
	}
	
	/**
	 * �޸���������
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public Boolean updateWsjcf(String fs, String pkValue,
			HttpServletRequest request) throws Exception {
		return dao.updateWsjcf(fs, pkValue, request);
	}
	
	
	/**
	 * ��ӡ�������ͳ�Ʊ�
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void printWsjcExcel(ZjcmGyglModel model, OutputStream os)
			throws Exception {

		String seachXy = model.getXydm();
		String seachNj = model.getNj();
		String xn = model.getXn();
		String xq = model.getXq();
		String bblx = model.getBblx();
		String needNj = model.getNeedNj();
		int num1 = ("no".equalsIgnoreCase(needNj)) ? 4 : 5;
		int num2 = ("no".equalsIgnoreCase(needNj)) ? 1 : 0;
		String[] arrXy= null;
		String[] arrNj= null;
		
		String xqmc = getOneValue("xqdzb", "xqmc", "xqdm", xq);
		
		// ���ñ���
		StringBuffer title =new StringBuffer();
		title.append(xn);
		title.append("ѧ��");
		title.append(xqmc);
		title.append("ѧ��");
		title.append("��������");
		title.append(bblx);
		title.append("��");

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		
		WritableSheet ws = wwb.createSheet("�������" + bblx + "��", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, num1, 1);
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 12, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);

		// ����ͷ
		ws.addCell(new Label(0, 2, Base.YXPZXY_KEY, wcf2));  
		if (num2 == 0) {
			ws.addCell(new Label(1, 2, "�꼶", wcf2)); 
		}
		ws.addCell(new Label(2 - num2, 2, "��������Ŀ", wcf2));
		ws.addCell(new Label(3 - num2, 2, bblx.subSequence(0, bblx.length() - 1)+ "������Ŀ", wcf2));
		ws.addCell(new Label(4 - num2, 2, bblx, wcf2));
		ws.addCell(new Label(5 - num2, 2, bblx + "����", wcf2)); 
		
		List<HashMap<String,String>> list = getWsjcTjList(model);
		if (list != null && list.size() > 0) {
			
			arrXy= new String[list.size()];
			arrNj= new String[list.size()];
			
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);

				String xydm = map.get("xydm");
				String xymc = map.get("xymc");
				String nj = map.get("nj");
				String zqss = map.get("zs");
				String zs = map.get("cs");
				String bl = ("0".equalsIgnoreCase(map.get("bl"))) ? "0" : map.get("bl") + "%";
				
				ws.addCell(new Label(0, 3 + i, xymc, wcf2));
				if (num2 == 0) {
					ws.addCell(new Label(1, 3 + i, nj, wcf2));
				}
				ws.addCell(new Label(2 - num2, 3 + i, zqss, wcf2));
				ws.addCell(new Label(3 - num2, 3 + i, zs, wcf2));
				ws.addCell(new Label(4 - num2, 3 + i, bl, wcf2));
				ws.addCell(new Label(5 - num2, 3 + i, String.valueOf(i + 1),wcf2));
				ws.addCell(new Label(6 - num2, 3 + i, xydm,wcf2));
			}
		}
		
		if (list != null && list.size() > 0) {

			String before = "";
			String after = "";
			String pm = "";
			String xydm = "";
			String nj = "";
			
			WritableCell wc = null;
			
			for (int i = 0; i < list.size(); i++) {
				wc = ws.getWritableCell(num1-1, 3 + i);
				before = wc.getContents();
				if (i > 0) {
					wc = ws.getWritableCell(num1-1, 3 + i - 1);
					after = wc.getContents();
					wc = ws.getWritableCell(num1, 3 + i - 1);
					pm = wc.getContents();
				}
				if (before.equalsIgnoreCase(after)) {
					ws.addCell(new Label(num1, 3 + i, pm, wcf2));
				}
				
				wc = ws.getWritableCell(num1 + 1, 3 + i);
				xydm = wc.getContents();
				arrXy[i] = xydm;
				
				wc = ws.getWritableCell(1, 3 + i);
				nj = wc.getContents();
				arrNj[i] = nj;
			}
		}
		
		if (list != null && list.size() > 0) {
			
			if (!Base.isNull(seachXy) || !Base.isNull(seachNj)) {
				for (int i = arrXy.length - 1; i >= 0; i--) {
					if ("no".equalsIgnoreCase(needNj)) {
						if (!seachXy.equalsIgnoreCase(arrXy[i])) {
							ws.removeRow(i + 3);
						}
					} else {
						if ((!seachXy.equalsIgnoreCase(arrXy[i]) && Base.isNull(seachNj))
								|| (!seachNj.equalsIgnoreCase(arrNj[i]) && Base.isNull(seachXy))
								|| (!Base.isNull(seachXy)&& !Base.isNull(seachNj)&& (!seachNj.equalsIgnoreCase(arrNj[i]) || (!seachXy.equalsIgnoreCase(arrXy[i]))))) {
							ws.removeRow(i + 3);
						}else{
							int a =0;a++;
						}
					}
				}
			}
			ws.removeColumn(num1 + 1);
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ���ͳ������
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWsjcTjList(ZjcmGyglModel model) {

		String needNj = model.getNeedNj();
		
		List<HashMap<String, String>> zqsList = dao.getZqsList(model);
		List<HashMap<String, String>> tjqsList = dao.getTjList(model);
		List<HashMap<String, String>> list1 = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> list2 = new ArrayList<HashMap<String, String>>();
		
		if (zqsList != null && zqsList.size() > 0) {
			for (int i = 0; i < zqsList.size(); i++) {
				HashMap<String, String> map1 = zqsList.get(i);
				int zs =  Integer.parseInt(map1.get("num"));
				int cs = 0;
				float bl = 0;
				if (tjqsList != null && tjqsList.size() > 0) {
					for (int j = 0; j < tjqsList.size(); j++) {
						HashMap<String, String> map2 = tjqsList.get(j);
						if ("no".equalsIgnoreCase(needNj)) {
							if (map2.get("xydm").equalsIgnoreCase(map1.get("xydm"))) {
								cs = Integer.parseInt(map2.get("num"));
								bl = Float.parseFloat(map2.get("num")) / Float.parseFloat(map1.get("num")) * 100;
							}
						} else {
							if (map2.get("xydm").equalsIgnoreCase(map1.get("xydm")) &&
								map2.get("nj").equalsIgnoreCase(map1.get("nj"))) {
								cs = Integer.parseInt(map2.get("num"));
								bl = Float.parseFloat(map2.get("num")) / Float.parseFloat(map1.get("num")) * 100;
							}
						}
					}
				}
				String xsbl = (bl == 0) ? "0" : String.valueOf(bl);
				xsbl = (xsbl.length() > 4) ? xsbl.substring(0, 4) : xsbl;
				
				map1.put("zs", String.valueOf(zs));
				map1.put("cs", String.valueOf(cs));
				map1.put("bl", xsbl);
				list1.add(map1);
			}
		}
				
		Collections.sort(list1, new MyCompare());
		for(HashMap<String,String> n : list1){
			list2.add(n);
		}
		
		return list2;
	}
	
	/**
	 * ��listֵ�ý�������
	 * 
	 * @author luo
	 */
	static class MyCompare implements Comparator {

		public int compare(Object o1, Object o2) {
			if (o1 instanceof Map && o2 instanceof Map) {
				Map m1 = (Map) o1;
				Map m2 = (Map) o2;

				String b1 = (String) m1.get("bl");
				String b2 = (String) m2.get("bl");

				float bl1 = Float.parseFloat(b1 == null ? "0" : b1);
				float bl2 = Float.parseFloat(b2 == null ? "0" : b2);

				return bl1 > bl2 ? 0 : 1;
			}
			return 0;
		}
	}
	
//	public static void main(String...s){

//	}
	
	/**
	 * ���湫Ԣ����ѧ������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveBxSq(GyglTyForm model, HttpServletRequest request)
			throws Exception {

		String[] onezd = new String[] { "xh", "xwsj", "xwkssj", "xwjssj",
				"bxnr", "bxlxdh" };

		String tableName = "gygl_gybxb";

		String pk = "xh||bxsj";

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { model.getXh() });

		return saveGygl(saveForm, model, request);
	}
	
	/**
	 * ��ù�Ԣ����ѧ������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public HashMap<String,String> getBxInfo(GyglTyForm model)
			throws Exception {

		String tableName = "gygl_gybxb";
		String xh = model.getXh();
		
		String[] colList = getTableZd(tableName);
		String query = " and bxsj = (select max(bxsj) from " + tableName
				+ " where xh = '" + xh + "')";

		HashMap<String, String> map = CommonQueryDAO.commonQueryOne2(tableName,
				colList, "xh", xh, query);

		return map;
	}
	
	/**
	 * ��ù�Ԣ����ѧ�����
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public HashMap<String, String> getBxshInfo(GyglTyForm model)
			throws Exception {

		String tableName = "view_gygl_zjcm_gybx";
		String pk = model.getPkValue();

		String[] colList = getTableZd(tableName);
		String query = "";

		HashMap<String, String> map = CommonQueryDAO.commonQueryOne2(tableName,
				colList, "pk", pk, query);

		return map;
	}
	
	/**
	 * ���湫Ԣ����ѧ�����
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveBxSh(GyglTyForm model, HttpServletRequest request)
			throws Exception {

		// ѧ��
		String xh = model.getXh();
		// ����ʱ��
		String bxsj = model.getBxsj();
		// �Ƿ��շ�
		String sfsf = model.getSfsf();
		// ά����Ա
		String wxry = model.getWxry();
		// ά�޷���
		String wxfy = model.getWxfy();
		// �Ƿ��깤
		String sfwg = model.getSfwg();
		// �깤ʱ��
		String wgsj = model.getWgsj();
		// ������
		String cljg = model.getCljg();
		// �ύʱ��(ʱ)
		String wgsjh = model.getWgsjh();
		// �ύʱ��(��)
		String wgsjm = model.getWgsjm();
		// ���״̬
		String shzt = request.getParameter("shzt");
		if ("tg".equalsIgnoreCase(shzt)) {
			shzt = "ͨ��";
		} else if ("btg".equalsIgnoreCase(shzt)) {
			shzt = "��ͨ��";
		}else{
			shzt = "δ���";
		}
		
		String tableName = "gygl_gybxb";

		String[] columns = new String[] { "sfsf", "wxry", "wxfy", "sfwg",
				"wgsj", "wgsjh","wgsjm","cljg", "shzt" };
		String[] values = new String[] { sfsf, wxry, wxfy, sfwg, wgsj, wgsjh,
				wgsjm, cljg, shzt };

		String primaryKey = "xh||bxsj";

		String pkValue = xh + bxsj;

		boolean flag = StandardOperation.update(tableName, columns, values,
				primaryKey, pkValue, request);

		return flag;
	}
	
	/**
	 * ���湫Ԣ����ѧ������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveBxPj(GyglTyForm model, HttpServletRequest request)
			throws Exception {

		// ѧ��
		String xh = model.getXh();
		// ����ʱ��
		String bxsj = model.getBxsj();
		// ����̬��
		String fwtd = model.getFwtd();
		// ��������
		String fwzl = model.getFwzl();
		// �Ƿ�ʱ
		String sfjs = model.getSfjs();
		// �ֳ�����
		String xcql = model.getXcql();
		// �û����
		String yhjy = model.getYhjy();

		String tableName = "gygl_gybxb";

		String[] columns = new String[] { "fwtd", "fwzl", "sfjs", "xcql",
				"yhjy" };

		String[] values = new String[] { fwtd, fwzl, sfjs, xcql, yhjy };

		String primaryKey = "xh||bxsj";

		String pkValue = xh + bxsj;

		boolean flag = StandardOperation.update(tableName, columns, values,
				primaryKey, pkValue, request);

		return flag;
	}
	
	/**
	 * ���湫Ԣ���޲���
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveBxcl(GyglTyForm model) throws Exception {

		String[] arrzd = new String[] { "cllx", "clmc", "clsl", "cldj" };
		String[] onezd = new String[] { "xh", "bxsj" };

		String tableName = "gygl_bxclb";

		String pk = "xh||bxsj";
		String pkValue = model.getXh() + model.getBxsj();

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { pkValue });

		return saveGygl(saveForm, model);
	}
	
	/**
	 * ���ͳ�������ͷ
	 * 
	 * @author luo
	 */
	public List<HashMap<String, String>> getBxTjTop(GyglTyForm model) {

		DAO dao = DAO.getInstance();

		ArrayList<String> colListCN = new ArrayList<String>();
		ArrayList<String> colListEN = new ArrayList<String>();

		// ͳ�Ʒ�ʽ
		String tjfs = model.getTjfs();
		// ͳ�Ʒ�Χ
		String tjfw = model.getTjfw();
		// �꼶
		String nj = model.getNj();
		// ѧԺ
		String xydm = model.getXydm();
		// רҵ
		String zydm = model.getZydm();
		// �༶
		String bjdm = model.getBjdm();
		// У��
		String xqdm = model.getXqdm();
		// ¥��
		String lddm = model.getLddm();
		// ����
		String cs = model.getCs();
		// ����
		String qsh = model.getQsh();
		// ѧ��
		String xh = model.getXh();
		// ����
		String xm = model.getXm();

		if ("bxtjfw_ss".equalsIgnoreCase(tjfs)) {

			if ("nj".equalsIgnoreCase(tjfw)) {// ͳ�Ʒ�Χ���꼶��
				colListCN.add("�꼶");
				colListEN.add("nj");
			} else if ("xy".equalsIgnoreCase(tjfw)) {// ͳ�Ʒ�Χ��ѧԺ��
				colListCN.add("Ժϵ");
				colListEN.add("xymc");
			} else if ("zy".equalsIgnoreCase(tjfw)) {// ͳ�Ʒ�Χ��רҵ��
				colListCN.add("רҵ");
				colListEN.add("zymc");
			} else if ("bj".equalsIgnoreCase(tjfw)) {// ͳ�Ʒ�Χ���༶��
				colListCN.add("�༶");
				colListEN.add("bjmc");
			} else if ("ld".equalsIgnoreCase(tjfw)) {// ͳ�Ʒ�Χ��¥����
				colListCN.add("¥��");
				colListEN.add("lddm");
			}
		}
		if (!Base.isNull(nj) && !"nj".equalsIgnoreCase(tjfw)) {
			colListCN.add("�꼶");
			colListEN.add("nj");
		}

		if (!Base.isNull(xydm) && !"xy".equalsIgnoreCase(tjfw)) {
			colListCN.add("Ժϵ����");
			colListEN.add("xymc");
		}

		if (!Base.isNull(zydm) && !"zy".equalsIgnoreCase(tjfw)) {
			colListCN.add("רҵ����");
			colListEN.add("zymc");
		}

		if (!Base.isNull(bjdm) && !"bj".equalsIgnoreCase(tjfw)) {
			colListCN.add("�༶����");
			colListEN.add("bjmc");
		}

		if (!Base.isNull(xqdm)) {
			colListCN.add("У��");
			colListEN.add("xqmc");
		}

		if (!Base.isNull(lddm) && !"ld".equalsIgnoreCase(tjfw)) {
			colListCN.add("¥��");
			colListEN.add("ldmc");
		}

		if (!Base.isNull(cs)) {
			colListCN.add("����");
			colListEN.add("cs");
		}

		if (!Base.isNull(qsh)) {
			colListCN.add("����");
			colListEN.add("qsh");
		}

		
		if (!Base.isNull(xh) && !Base.isNull(xm)) {
			colListCN.add("ѧ��");
			colListCN.add("����");
			colListEN.add("xm");
			colListEN.add("xh");
		} else if (!Base.isNull(xh)) {
			colListCN.add("ѧ��");
			colListCN.add("����");
			colListEN.add("xm");
			colListEN.add("xh");
		} else if (!Base.isNull(xm)) {
			colListCN.add("ѧ��");
			colListCN.add("����");
			colListEN.add("xm");
			colListEN.add("xh");
		}

		if ("bxtjfw_ss".equalsIgnoreCase(tjfs)) {
			colListCN.add("��������");
			colListEN.add("bxrs");
			colListCN.add("��ά��");
			colListEN.add("ywx");
			colListCN.add("δά��");
			colListEN.add("wwx");
			colListCN.add("������");
			colListEN.add("ypj");
			colListCN.add("δ����");
			colListEN.add("wpj");
		} else {
			if ("cllx".equalsIgnoreCase(tjfw)) {// ͳ�Ʒ�Χ���������ͣ�
				colListCN.add("��������");
				colListEN.add("clle");
				
				colListCN.add("����");
				colListEN.add("sl");
				
				colListCN.add("�ܼƽ��");
				colListEN.add("zj");
			} else {
				
				colListCN.add("��������");
				colListEN.add("clmc");
				
				colListCN.add("��������");
				colListEN.add("clle");
				
				colListCN.add("����");
				colListEN.add("sl");
				
				colListCN.add("�ܼƽ��");
				colListEN.add("zj");
			}
		}

		return dao.arrayToList(colListEN.toArray(new String[] {}), colListCN
				.toArray(new String[] {}));
	}
	
	/**
	 * ��ù�Ԣ����ͳ��
	 * 
	 * @author luo
	 */
	public ArrayList<String[]> getBxTjList(GyglTyForm model) {

		// ͳ�Ʒ�ʽ
		String tjfs = model.getTjfs();

		if ("bxtjfw_ss".equalsIgnoreCase(tjfs)) {
			return getBxTjSsList(model);
		} else {
			return getBxTjClList(model);
		}
	}

	/**
	 * ��ù�Ԣ��������ͳ���б�
	 * 
	 * @author luo
	 */
	public ArrayList<String[]> getBxTjSsList(GyglTyForm model) {

		ArrayList<String[]> list = dao.getBxTjSsList(model);

		if (list != null && list.size() > 0) {
			int bxRs = 0;// ��������
			int ywxRs = 0;// ��ά��
			int wwxRs = 0;// δά��
			int ypjRs = 0;// ������
			int wpjRs = 0;// δ����
			int arrSize = 0;

			for (String[] results : list) {

				if (bxRs == 0) {
					arrSize = results.length;
				}

				String bx = results[results.length - 5];
				String ywx = results[results.length - 4];
				String wwx = results[results.length - 3];
				String ypj = results[results.length - 2];
				String wpj = results[results.length - 1];

				if (!Base.isNull(bx)) {
					bxRs += Integer.parseInt(bx);
					ywxRs += Integer.parseInt(ywx);
					wwxRs += Integer.parseInt(wwx);
					ypjRs += Integer.parseInt(ypj);
					wpjRs += Integer.parseInt(wpj);
				}
			}

			String[] outPut = new String[arrSize];
			outPut[0] = "�ܼ�";
			if (arrSize > 5) {
				for (int i = 1; i < arrSize - 5; i++) {
					outPut[i] = "";
				}
			}
			outPut[arrSize - 5] = String.valueOf(bxRs);
			outPut[arrSize - 4] = String.valueOf(ywxRs);
			outPut[arrSize - 3] = String.valueOf(wwxRs);
			outPut[arrSize - 2] = String.valueOf(ypjRs);
			outPut[arrSize - 1] = String.valueOf(wpjRs);

			list.add(outPut);
		}

		return list;
	}

	/**
	 * ��ù�Ԣ���޲���ͳ���б�
	 * 
	 * @author luo
	 */
	public ArrayList<String[]> getBxTjClList(GyglTyForm model) {

		ArrayList<String[]> list = dao.getBxTjClList(model);

		if (list != null && list.size() > 0) {
			int clsl = 0;// ��������
			float zjje = 0;// �ܼƽ��
			int arrSize = 0;

			for (String[] results : list) {

				if (clsl == 0) {
					arrSize = results.length;
				}

				String sl = results[results.length - 2];
				String zj = results[results.length - 1];

				if (!Base.isNull(sl)) {
					clsl += Integer.parseInt(sl);
					zjje += Float.parseFloat(zj);
				}
			}

			String[] outPut = new String[arrSize];
			outPut[0] = "�ܼ�";

			if (arrSize > 2) {
				for (int i = 1; i < arrSize - 2; i++) {
					outPut[i] = "";
				}
			}

			outPut[arrSize - 2] = String.valueOf(clsl);
			outPut[arrSize - 1] = String.valueOf(zjje);

			list.add(outPut);
		}

		return list;
	}
	
	/**
	 * ��������
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void expGybxInfo(GyglTyForm model, OutputStream os) throws Exception {

		// ��Ŀ����
		String title = "��Ԣ����ͳ��";

		// ���ı���
		List<HashMap<String, String>> topTr = getBxTjTop(model);

		// ������������
		ArrayList<String[]> list = getBxTjList(model);

		//ͳ�Ʒ�ʽ
		String tjfs = model.getTjfs();
		
		int[] hbwz = null;
		
		if (topTr != null && topTr.size() > 0) {
			if ("bxtjfw_ss".equalsIgnoreCase(tjfs)) {
				hbwz = new int[5];
				hbwz[0] = topTr.size() - 5;
				hbwz[1] = topTr.size() - 4;
				hbwz[2] = topTr.size() - 3;
				hbwz[3] = topTr.size() - 2;
				hbwz[4] = topTr.size() - 1;
			}else{
				hbwz = new int[2];
				hbwz[0] = topTr.size() - 2;
				hbwz[1] = topTr.size() - 1;
			}
		}
		
		expToExcel(title, topTr, list, os, "bhb", hbwz);
	}
	
	
	
	/**
	 * �������¼�루�б�
	 * 
	 * @author quph
	 */
	public ArrayList<String[]> getWsjcQueryList(String tableName, ZjcmGyglModel model,
			String[] colList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		ArrayList<String[]> list = dao.getWsjcQueryList(tableName, model, colList);
		ArrayList<String[]> wsjcList = new ArrayList<String[]>();
		if (list != null && list.size() != 0) {
			//����������ȼ��б�
			List<HashMap<String, String>> wsdjList = getWsjcList();

			for (int i = 0; i < list.size(); i++) {
				//�ع��������ʹ��������б�����һ��ֵ���������ȼ���
				String[] rs = new String[list.get(i).length + 1];
				for (int k = 0; k < list.get(i).length; k++) {
					rs[k] = list.get(i)[k];
				}
				String fs = rs[8];
				if (!Base.isNull(fs)) {
					if (wsdjList != null && wsdjList.size() != 0) {
						for (int j = 0; j < wsdjList.size(); j++) {
							HashMap<String, String> map = wsdjList.get(j);
							// �ȼ�����ֵ
							int minFs = Integer.parseInt(map.get("xxf"));
							// �ȼ�����ֵ
							int maxFs = Integer.parseInt(map.get("sxf"));
							// �жϷ����Ƿ���������
							if (Integer.parseInt(fs) >= minFs && Integer.parseInt(fs) <= maxFs) {
								rs[list.get(i).length] = map.get("mc");
							}
						}
					}
				}
				wsjcList.add(rs);
			}
		}
		return wsjcList;
	}
	
	public void wstjManage(GyglTyForm form,
			HttpServletRequest request, WritableWorkbook wwb)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		//������
		HashMap<String,String>timesMap=dao.getMaxTimes(form);
		int maxTimes=Integer.parseInt(timesMap.get("zcs"));
		
		//���������Ϣ
		List<HashMap<String,String>>wsjcxxList=dao.getWsjcxx(form);
		//������Ϣ
		List<HashMap<String,String>>xszsxxList=dao.getXszsxx(form);
		try {
			// ����xls��SHEET����
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			// ���ö��뷽ʽ
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(16);
			wcfTytle.setFont(wfTytle);
			ws.mergeCells(0, 0,12+maxTimes, 0);
			ws.addCell(new Label(0,0,"����ʳƷְҵ����ѧԺѧ��ס�����ͳ�Ʊ�",wcfTytle));
			
			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setWrap(true);
//			 ���ñ��߿�
			 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
	

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);
			
			ws.addCell(new Label(0,1,"ѧ��",wcfTytle));
			ws.addCell(new Label(1,1,"����",wcfTytle));
			ws.addCell(new Label(2,1,"�Ա�",wcfTytle));
			ws.addCell(new Label(3,1,"Ժϵ����",wcfTytle));
			ws.addCell(new Label(4,1,"�꼶",wcfTytle));
			ws.addCell(new Label(5,1,"�༶����",wcfTytle));
			ws.addCell(new Label(6,1,"���֤��",wcfTytle));
			ws.addCell(new Label(7,1,"����¥��",wcfTytle));
			ws.addCell(new Label(8,1,"���Һ�",wcfTytle));
			ws.addCell(new Label(9,1,"��λ��",wcfTytle));
			
			
			for(int i=0;i<maxTimes;i++){
				ws.addCell(new Label(10+i,1,"��"+(i+1)+"�μ��",wcfTytle));
			}
			
			for(int i=0;i<xszsxxList.size();i++){
				for(int j=0;j<maxTimes;j++){
					ws.addCell(new Label(10+j,2+i,"0",wcfTytle));
				}
			}
			ws.addCell(new Label(10+maxTimes,1,"ס�޷�",wcfTytle));
			ws.addCell(new Label(11+maxTimes,1,"У������",wcfTytle));
			ws.addCell(new Label(12+maxTimes,1,"���ҵ绰",wcfTytle));
			for(int i=0;i<xszsxxList.size();i++){
				HashMap<String,String>xszsxxMap=xszsxxList.get(i);
				ws.addCell(new Label(0,2+i,xszsxxMap.get("xh"),wcfTytle));
				ws.addCell(new Label(1,2+i,xszsxxMap.get("xm"),wcfTytle));
				ws.addCell(new Label(2,2+i,xszsxxMap.get("xb"),wcfTytle));
				ws.addCell(new Label(3,2+i,xszsxxMap.get("xymc"),wcfTytle));
				ws.addCell(new Label(4,2+i,xszsxxMap.get("nj"),wcfTytle));
				ws.addCell(new Label(5,2+i,xszsxxMap.get("bjmc"),wcfTytle));
				ws.addCell(new Label(6,2+i,xszsxxMap.get("sfzh"),wcfTytle));
				ws.addCell(new Label(7,2+i,xszsxxMap.get("ldmc"),wcfTytle));
				ws.addCell(new Label(8,2+i,xszsxxMap.get("qsh"),wcfTytle));
				ws.addCell(new Label(9,2+i,xszsxxMap.get("cwh"),wcfTytle));
				int cols=0;
				for(int j=0;j<wsjcxxList.size();j++){
					HashMap<String,String>wsjcxxMap=wsjcxxList.get(j);
					if(wsjcxxMap.get("qsh").equalsIgnoreCase(xszsxxMap.get("qsh"))
							&& wsjcxxMap.get("lddm").equalsIgnoreCase(xszsxxMap.get("lddm"))){
						cols+=1;
						ws.addCell(new Label(9+cols,2+i,wsjcxxMap.get("fs"),wcfTytle));
					}
				}
				ws.addCell(new Label(10+maxTimes,2+i,xszsxxMap.get("sfbz"),wcfTytle));
				ws.addCell(new Label(11+maxTimes,2+i,xszsxxMap.get("xqdm"),wcfTytle));
				ws.addCell(new Label(12+maxTimes,2+i,xszsxxMap.get("lxdh"),wcfTytle));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
}
