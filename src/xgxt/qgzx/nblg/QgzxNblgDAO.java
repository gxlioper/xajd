package xgxt.qgzx.nblg;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.Workbook;

import xgxt.DAO.DAO;
import xgxt.qgzx.dao.QgzxDao;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �������ڹ���ѧģ��DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-01-14</p>
 */
public class QgzxNblgDAO extends DAO {
	
	/**
	 * ��ѯѧ���Ļ�����Ϣ
	 * @param xh
	 * @return  HashMap<String, String>
	 * */
	public HashMap<String, String> getStuInfo(String xh){
		HashMap<String, String> map = new HashMap<String, String>();
		String[] columns = {"xh", "xm", "xb", "xymc", "zymc", "bjmc", "nj", "ssbh", "lxdh", "kh"};;
		String sql = "select xh,xm,xb,xymc,zymc,bjmc,nj,ssbh,lxdh,kh from view_xsxxb where xh=?";
		map = getMap(sql, new String[]{xh}, columns);
		return map;
	}
	
	/**
	 * ��ѯ��λ����
	 * @param pkValue
	 * @return String
	 * */
	public String getGwmc(String pkValue){
		String sql = "select distinct gwdm from view_gwxx where gwdm||'-'||gwsbsj=?";
		return getOneRs(sql, new String[]{pkValue}, "gwdm");
	}
	
	/**
	 * ����Ƿ���������λ
	 * @param model
	 * @return boolean
	 * */
	public boolean checkOtherPost(QgzxNblgForm model){
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String pkValue = model.getXmdm();
		
		String sql = "select count(*) num from view_xsgwxx where xn=? and nd=? and xq=? and gwdm||'-'||gwsbsj<>?";
		String result = getOneRs(sql, new String[]{xn, nd, xq, pkValue}, "num");
		result = result==null || "".equalsIgnoreCase(result) ? "0" : result;
		
		return Integer.parseInt(result)>0 ? true : false;		
	}
	
	/**
	 * ��ѯ��������
	 * @param bmdm
	 * @return String
	 * */
	public String getBmmc(String bmdm){
		String sql = "select bmmc from zxbz_xxbmdm where bmdm=?";
		return getOneRs(sql, new String[]{bmdm}, "bmmc");
	}
	
	/**
	 * ���ݸ�λ��Ϣ��ȡ��λ��������Ϣ
	 * @param model
	 * */
	public String getKhr(QgzxNblgForm model){
		String sql = "select fzr from view_gwxx where gwdm=? and gwsbsj=?";
		return getOneRs(sql, new String[]{model.getGwdm(), model.getGwsbsj()}, "fzr");			
	}
	
	/**
	 * ������洢��ֵ����洢��������
	 * @param HashMap<String, String>
	 * */
	public List<HashMap<String, String>> loadList(HashMap<String, String> map){
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();		
		String time = map.get("time");
		String year = time.substring(0,4);
		String month = time.substring(4,time.length());
		HashMap<String, String> oMap = new HashMap<String, String>();
		oMap = getMap("select gzqk,bz from xsgzkhb where xh=? and gwdm=? and sbsj=? and yf=?", 
				new String[]{map.get("xh"), map.get("gwdm"), map.get("gwsbsj"), year+month}, new String[]{"gzqk","bz"});
		for(int i=0; i<31; i++){			
			if(map.get("day" + (i+1)) != null && !map.get("day" + (i+1)).equalsIgnoreCase("") && !map.get("day" + (i+1)).equalsIgnoreCase("0")){
				HashMap<String, String> tmpMap = new HashMap<String, String>();
				tmpMap.put("time", year + "��" + month + "��" + (i+1) + "��" );
				tmpMap.put("gzsj", map.get("day" + (i+1)));
				tmpMap.put("gzqk", oMap.get("gzqk"));
				tmpMap.put("bz", oMap.get("bz"));
				list.add(tmpMap);
			}
		}
		return list;
	}
	
	/**
	 * �������ڱ�
	 * @param model
	 * */
	public void printYkqb_db(QgzxNblgForm model){
		WritableWorkbook wwb = null;
		OutputStream os = model.getOs();
		WriteExcel t = new WriteExcel();
		String tem = "";
		try {
			wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("�ڹ���ѧ���ڱ�", 0);
			ws.mergeCells(0, 0, 7, 1);
			t.printTitle(ws, "�㽭��ѧ������ѧԺѧ���ڹ���ѧ���ڱ�", 8);
			ws.mergeCells(0, 2, 7, 3);
			tem = "��������:" + getBmmc(model.getBmdm()) + "              ��λ����:"+model.getGwdm()+"             ������:" + getKhr(model);
			t.printToOneCell(ws, tem, 0, 2);
			t.printToOneCell_self(ws, "ѧ������", 0, 4, 14, false, true);
			t.printToOneCell_self(ws, "�Ա�", 1, 4, 14, false, true);
			t.printToOneCell_self(ws, "ѧ��", 2, 4, 14, false, true);
			t.printToOneCell_self(ws, "��Ժ", 3, 4, 14, false, true);
			t.printToOneCell_self(ws, "רҵ�༶", 4, 4, 14, false, true);
			t.printToOneCell_self(ws, "ũ�п���", 5, 4, 14, false, true);
			ws.mergeCells(6, 4, 7, 4);
			t.printToOneCell_self(ws, "��ϵ�绰", 6, 4, 14, false, true);
			HashMap<String, String> map = getStuInfo(model.getXh());
			
			t.printToOneCell_self(ws, map.get("xm"), 0, 5, 14, false, true);
			t.printToOneCell_self(ws, map.get("xb"), 1, 5, 14, false, true);
			t.printToOneCell_self(ws, map.get("xh"), 2, 5, 14, false, true);
			t.printToOneCell_self(ws, map.get("xymc"), 3, 5, 14, false, true);
			t.printToOneCell_self(ws, map.get("zymc")+"-"+map.get("bjmc"), 4, 5, 14, false, true);
			t.printToOneCell_self(ws, map.get("kh"), 5, 5, 14, false, true);
			ws.mergeCells(6, 5, 7, 5);		
			t.printToOneCell_self(ws, map.get("lxdh"), 6, 5, 14, false, true);
			
			t.printToOneCell_self(ws, "����ʱ��", 0, 6, 14, false, true);
			t.printToOneCell_self(ws, "�ڹ���ѧʱ�䣨Сʱ��", 1, 6, 14, false, true);
			t.printToOneCell_self(ws, "������", 2, 6, 14, false, true);
			t.printToOneCell_self(ws, "��ע", 3, 6, 14, false, true);
			t.printToOneCell_self(ws, "����ʱ��", 4, 6, 14, false, true);
			t.printToOneCell_self(ws, "�ڹ���ѧʱ�䣨Сʱ��", 5, 6, 14, false, true);
			t.printToOneCell_self(ws, "������", 6, 6, 14, false, true);
			t.printToOneCell_self(ws, "��ע", 7, 6, 14, false, true);	
			String sql = "select * from xskhyb where xh=? and time=?";			
			List<HashMap<String, String>> list = getListNotOut(sql, new String[]{model.getXh(),GetTime.getNowYear()+GetTime.getNowMonth()});
			HashMap<String, String> tmpMap = new HashMap<String, String>();
			tmpMap = list.get(0);
			tmpMap.put("gwdm", model.getGwdm());
			tmpMap.put("gwsbsj", model.getGwsbsj());
			list = loadList(tmpMap);
			if(list.size()==1){
				tmpMap = list.get(0);
				t.printToOneCell_self(ws, tmpMap.get("time"),0 , 7, 12, false, true);
				t.printToOneCell_self(ws, tmpMap.get("gzsj"),1 , 7, 12, false, true);
			}else{
				if(list.size()/2%2==1){//ż��
					for(int i=0; i<list.size()/2; i++){
						tmpMap = list.get(i);
						t.printToOneCell_self(ws, tmpMap.get("time"),0 , 7+i, 12, false, true);
						t.printToOneCell_self(ws, tmpMap.get("gzsj"),1 , 7+i, 12, false, true);
						t.printToOneCell_self(ws, tmpMap.get("gzqk"),2 , 7+i, 12, false, true);
						t.printToOneCell_self(ws, tmpMap.get("bz"),3 , 7+i, 12, false, true);
					}
					for(int i=0; i<list.size()/2; i++){
						tmpMap = list.get(list.size()/2+i);
						t.printToOneCell_self(ws, tmpMap.get("time"),4 , 7+i, 12, false, true);
						t.printToOneCell_self(ws, tmpMap.get("gzsj"),5 , 7+i, 12, false, true);
						t.printToOneCell_self(ws, tmpMap.get("gzqk"),6 , 7+i, 12, false, true);
						t.printToOneCell_self(ws, tmpMap.get("bz"),7 , 7+i, 12, false, true);
					}
				}else{//����
					for(int i=0; i<list.size()/2+1; i++){
						tmpMap = list.get(i);
						t.printToOneCell_self(ws, tmpMap.get("time"),0 , 7+i, 12, false, true);
						t.printToOneCell_self(ws, tmpMap.get("gzsj"),1 , 7+i, 12, false, true);
						t.printToOneCell_self(ws, tmpMap.get("gzqk"),2 , 7+i, 12, false, true);
						t.printToOneCell_self(ws, tmpMap.get("bz"),3 , 7+i, 12, false, true);
					}
					for(int i=0; i<list.size()/2; i++){
						tmpMap = list.get(list.size()/2+i+1);
						t.printToOneCell_self(ws, tmpMap.get("time"),4 , 7+i, 12, false, true);
						t.printToOneCell_self(ws, tmpMap.get("gzsj"),5 , 7+i, 12, false, true);
						t.printToOneCell_self(ws, tmpMap.get("gzqk"),6 , 7+i, 12, false, true);
						t.printToOneCell_self(ws, tmpMap.get("bz"),7 , 7+i, 12, false, true);
					}
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ExcelMethods.submitWritableWorkbookOperations(wwb);
		}
	}
	
	/**
	 * ��Ա���ܱ�
	 * @param model
	 * */
	public void printYyhz_db(QgzxNblgForm model){
		WritableWorkbook wwb = null;
		OutputStream os = model.getOs();
		WriteExcel t = new WriteExcel();
		String tem = "";
		String sql = "";
		String year = GetTime.getNowYear();
		String month = GetTime.getNowMonth();
		String day = GetTime.getNowDay();
		HashMap<String, String> yrdwMap = new HashMap<String, String>();
		String yrdwmc = "";
		String yrdwdm = "";
		QgzxDao qgzxDao = new QgzxDao();
		if(!qgzxDao.isYrdwUser(model.getUserName())){
			//���˵�λ�û�
			yrdwMap = getMap("select yrdwmc,yrdwdm from yrdwdmb where dlm=?", new String[]{model.getUserName()}, new String[]{"yrdwmc", "yrdwdm"});
			yrdwmc = yrdwMap.get("yrdwmc");
			yrdwdm = yrdwMap.get("yrdwdm");
			yrdwmc = yrdwmc == null ? "" : yrdwmc;
		}
		try {
			wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("�ڹ���ѧ��Ա���ܱ�", 0);
			ws.mergeCells(0, 0, 10, 1);
			t.printTitle(ws, "�㽭��ѧ������ѧԺ���˵�λ�ڹ���ѧ��λ��Ա���ܱ�", 11);
			ws.mergeCells(0, 2, 10, 3);
			tem ="���ţ����£���" + yrdwmc + "          " +  model.getXn()+"ѧ���"+model.getXq()+"ѧ��             ���ʱ�䣺" + year + "���� " + month + "���� " + day + "����";
			ws.mergeCells(0, 4, 1, 4);
			ws.mergeCells(3, 4, 4, 4);
			ws.mergeCells(5, 4, 6, 4);
			ws.mergeCells(8, 4, 10, 4);
			t.printToOneCell_self(ws, tem, 0, 2, 12, false, true);
			t.printToOneCell_self(ws, "������ϵ������", 0, 4, 12, false, true);
			t.printToOneCell_self(ws, "�칫�ҵص�", 3, 4, 12, false, true);
			t.printToOneCell_self(ws, "�칫�绰���ֻ�", 7, 4, 12, false, true);
			t.printToOneCell_self(ws, "���", 0, 5, 12, false, true);
			t.printToOneCell_self(ws, "���˵�λ", 1, 5, 12, false, true);
			t.printToOneCell_self(ws, "ѧ������", 2, 5, 12, false, true);
			t.printToOneCell_self(ws, "�Ա�", 3, 5, 12, false, true);
			t.printToOneCell_self(ws, "���ڷ�Ժ", 4, 5, 12, false, true);
			t.printToOneCell_self(ws, "רҵ�༶", 5, 5, 12, false, true);
			t.printToOneCell_self(ws, "�Ƿ�ƶ����", 6, 5, 12, false, true);
			t.printToOneCell_self(ws, "������λ", 7, 5, 12, false, true);
			t.printToOneCell_self(ws, "ũ�п���", 8, 5, 12, false, true);
			t.printToOneCell_self(ws, "������", 9, 5, 12, false, true);
			t.printToOneCell_self(ws, "��ϵ�绰", 10, 5, 12, false, true);
			String[] array = new String[]{"rownum","yrdw","xm","xb","xymc","bjmc","sfpks","gwdm","kh","khr","lxdh"};
			sql = "select rownum,xm,xb,xymc,bjmc,sfpks,gwdm,(select kh from view_xsxxb b where a.xh=b.xh) kh,lxdh" +
					",(select fzr from view_gwxx b where a.gwdm=b.gwdm and a.gwsbsj=b.gwsbsj) khr," +
					"(select yrdwmc from yrdwdmb b,gwxxb c where b.yrdwdm=c.sqdw and c.gwdm=a.gwdm  ) yrdw " +
					"from view_xsgwxx a where xn=? and xq=? and xxyj='ͨ��'";
			if(!qgzxDao.isYrdwUser(model.getUserName())){
				//���˵�λ�û���¼ֻ��ʾ�����˵�λ��Ϣ
				sql += " and yrdwdm='" + yrdwdm + "'";
			}
			List<HashMap<String, String>> list = getListNotOut(sql, new String[]{model.getXn(),model.getXq()});
			int length = list == null? 0 :list.size();
			for(int i=0;i<length;i++){
				HashMap<String, String> map = (HashMap<String, String>)list.get(i);
				map.put("rownum", i+"");
				for(int j = 0;j<array.length;j++){
					t.printToOneCell_self(ws, map.get(array[j]), j, 6+i, 10, false, true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ExcelMethods.submitWritableWorkbookOperations(wwb);
		}
	}
	
	/**
	 * �ڹ���ѧ�����±�
	 * @param model
	 * */
	public void printYbb_db(QgzxNblgForm model){
		WritableWorkbook wwb = null;
		OutputStream os = model.getOs();
		WriteExcel t = new WriteExcel();
		String tem = "";
		String sql = "";
//		String year = GetTime.getNowYear();
		String month = getOneRs("select cjffsj from gwsqsjb", new String[]{}, "cjffsj");
		String nd = GetTime.getNowYear();
		String yf = GetTime.getNowMonth();
		if (month != null && !"".equalsIgnoreCase(month)) {
			nd = month.substring(0,4);
			yf = month.substring(4,6);
		}
		HashMap<String, String> yrdwMap = new HashMap<String, String>();
		String yrdwmc = "";
		String yrdwdm = "";
		QgzxDao qgzxDao = new QgzxDao();
		
		if(month != null && month.length()==1){
			month = "0" + month;
		}
		if(qgzxDao.isYrdwUser(model.getUserName())){
			//���˵�λ�û�
			yrdwMap = getMap("select yrdwmc,yrdwdm from yrdwdmb where dlm=?", new String[]{model.getUserName()}, new String[]{"yrdwmc", "yrdwdm"});
			yrdwmc = yrdwMap.get("yrdwmc");
			yrdwdm = yrdwMap.get("yrdwdm");
			yrdwmc = yrdwmc == null ? "" : yrdwmc;
		}
		try {
			wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("�ڹ���ѧ�±���", 0);
			ws.mergeCells(0, 0, 10, 1);
			t.printTitle(ws, "�㽭��ѧ������ѧԺ�ڹ���ѧ�±���", 11);
			
			ws.mergeCells(0, 2, 10, 3);
			tem ="���ţ����£���" + yrdwmc + "                     ������" + nd + "�ꡡ��" + yf + "��";
			t.printToOneCell_self(ws, tem, 0, 2, 14, false, true);
			
			t.printToOneCell_self(ws, "���", 0, 4, 12, false, true);
			t.printToOneCell_self(ws, "ѧ��", 1, 4, 12, false, true);
			t.printToOneCell_self(ws, "����", 2, 4, 12, false, true);
			t.printToOneCell_self(ws, "רҵ�༶ ", 3, 4, 12, false, true);
			t.printToOneCell_self(ws, "ũ�п���", 4, 4, 12, false, true);
			t.printToOneCell_self(ws, "������λ", 5, 4, 12, false, true);
			t.printToOneCell_self(ws, "ÿ���ڹ���ѧʱ�䣨Сʱ��", 6, 4, 12, false, true);
			t.printToOneCell_self(ws, "������", 7, 4, 12, false, true);
			t.printToOneCell_self(ws, "�����Ԫ��", 8, 4, 12, false, true);
			t.printToOneCell_self(ws, "������ǩ��", 9, 4, 12, false, true);
			t.printToOneCell_self(ws, "��ע", 10, 4, 12, false, true);
			//��������
			String[] array = new String[]{"rownum","xh","xm","zybj","kh","gwdm","gzsj","wcqk","cjje","khr","bz"};
			sql = "select rownum,xh,xm,xb,zymc||bjmc zybj,(select kh from view_xsxxb b where a.xh=b.xh) kh,gwdm,gzsj,'' wcqk,cjje," +
					"(select fzr from view_gwxx b where b.gwdm=a.gwdm and b.gwsbsj=a.sqsj)khr,bz from view_xscjff a where nd = '" + nd +"' and yf ='" +yf + "'";
			if(!qgzxDao.isYrdwUser(model.getUserName())){
//				���˵�λ�û���¼ֻ��ʾ�����˵�λ��Ϣ
				sql += " and sqdw='" + yrdwdm + "'";
			}
			
			List<HashMap<String, String>> list = getListNotOut(sql, new String[]{});
			System.out.println(sql);
			int length = list == null? 0 :list.size();
			for(int i=0;i<length;i++){
				HashMap<String, String> map = (HashMap<String, String>)list.get(i);
				map.put("rownum", i+"");
				for(int j = 0;j<array.length;j++){
					t.printToOneCell_self(ws, map.get(array[j]), j, 5+i, 10, false, true);
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ExcelMethods.submitWritableWorkbookOperations(wwb);
		}
	}
}
