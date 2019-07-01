package xsgzgl.xtwh.general.xssjtb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchService;
/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ϵͳά��_ѧ�����ݼ��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author xucy
 * @version 1.0
 */
public class XssjtbService  extends CommService{
	
	protected static HashMap<String, List<HashMap<String, String>>> zyMap = new HashMap<String, List<HashMap<String,String>>>();
	
	public static HashMap<String, List<HashMap<String, String>>> bjMap = new HashMap<String, List<HashMap<String,String>>>();
	
	XssjtbDao dao= new  XssjtbDao();
	
	/**
	 * ����ͬ��_��־����
	 */
	public List<String[]> getSjjcRzList(XssjtbForm myForm)throws Exception{
		return dao.getSjjcRzList(myForm);
	}
	
	/**
	 * ����ͬ��_�쳣����
	 */
	public List<String[]> getYcsjList(XssjtbForm myForm)throws Exception{
		return dao.getYcsjList(myForm);
	}
	
	public List<HashMap<String,String>> getTopTr(XssjtbForm myForm){
		DAO dao = DAO.getInstance();
		String[] colListCN = null;
		String[] colListEN = null;
		colListCN=new String[]{"ͬ��ʱ��","��־��Ϣ"};
		colListEN= new String[]{"jcsj", "jcnr"};
		return dao.arrayToList(colListEN, colListCN);
	}
	
	/**
	 * �쳣���������б��ͷ
	 */
	public List<HashMap<String,String>> getCkTopTr(XssjtbForm myForm){

		DAO dao = DAO.getInstance();
		String[] colListCN = null;
		String[] colListEN = null;
		
		if("xg_xtwh_bmdmb".equals(myForm.getJcb())){
			colListCN=new String[]{"���Ŵ���","��������","�쳣ԭ��"};
			colListEN= new String[]{"zj","bmmc","ycyy"};
		}if("xg_xtwh_zydmb".equals(myForm.getJcb())){
			colListCN=new String[]{"רҵ����","רҵ����","���Ŵ���","��������","�쳣ԭ��"};
			colListEN= new String[]{"zj","zymc","bmdm","bmmc","ycyy"};
		}if("xg_xtwh_bjdmb".equals(myForm.getJcb())){
			colListCN=new String[]{"�༶����","�༶����","רҵ����","רҵ����","�꼶","�쳣ԭ��"};
			colListEN= new String[]{"zj","bjdm","bjmc","zydm","zymc","nj","ycyy"};
		}if("xg_xtwh_xsxxb".equals(myForm.getJcb())){
			colListCN=new String[]{"ѧ��","����","�Ա�","�쳣ԭ��"};
			colListEN= new String[]{"zj","xm","xb","ycyy"};
		}
		return dao.arrayToList(colListEN, colListCN);
	}
	
	/**
	 * ִ������ͬ��
	 */
	public boolean cshsjTb()throws Exception{
		boolean flag = false;
		flag = dao.cshsjTb();
		return flag;
	}
	
	/**
	 * ִ������ͬ��
	 */
	public boolean xssjTb()throws Exception{
		boolean flag = false;
		flag = dao.xysjTb();
		if(flag){
			flag = dao.zysjTb();
		}
		if(flag){
			flag = dao.bjsjTb();	
		}
		if(flag){
			flag = dao.xssjTb();
		}
		//���س�ʼ������
		if(flag){
			initialNj();
			initialXy();
			initialZy();
			initialBj();
			initalSearch();
		}
		return flag;
	}
	
	//�꼶�б�
	public void initialNj(){
		Base.setNjallList(dao.getNjallList());
	}
	
	//ѧԺ�б�
	public void initialXy(){
		Base.setXyallList(dao.getXyallList());
	}
	
	//רҵ�б�
	public void initialZy(){
		String xydm;
		List<HashMap<String, String>> nullZyList = dao.getZyallList("");
		zyMap.put("", nullZyList);
		for(HashMap<String, String> xy : dao.getXyallList()){
			xydm = xy.get("xydm");
			List<HashMap<String, String>> subZyList = dao.getZyallList(xydm);
			zyMap.put(xydm, subZyList);
		}
		Base.setZyallMap(zyMap);
	}
	
	//�༶�б�
	public void initialBj(){
		String xydm;
		String zydm;
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> allBjList = dao.getBjAllList();
		bjMap.put("!!!!", allBjList);
		for (HashMap<String,String> xy : dao.getXyallList()){
			//xydm!!!! ��Ӧ�İ༶�б�
			xydm = xy.get("xydm");
			List<HashMap<String,String>> bjList = new ArrayList<HashMap<String,String>>(); 
			for (HashMap<String,String> map : allBjList){
				if (xydm.equalsIgnoreCase(map.get("xydm"))){
					bjList.add(map);
				}
			}
			bjMap.put(xydm+"!!!!", bjList);
			for(HashMap<String, String> zyMaps : zyMap.get(xydm)){
				//xydm!!zydm!! ��Ӧ�İ༶�б�
				zydm = zyMaps.get("zydm");
				List<HashMap<String, String>> subBjList = new ArrayList<HashMap<String,String>>(); 
				for (HashMap<String,String> map : allBjList){
					if (xydm.equalsIgnoreCase(map.get("xydm")) && zydm.equalsIgnoreCase(map.get("zydm"))){
						subBjList.add(map);
					}
				}
				bjMap.put(xydm+"!!"+zydm+"!!", subBjList);
				bjMap.put("!!"+zydm+"!!", subBjList);
				
				for(HashMap<String, String> njMap : dao.getNjallList()){
					
					String nj = njMap.get("nj");
					 List<HashMap<String, String>> bjListForNj = new ArrayList<HashMap<String,String>>();                   
					 List<HashMap<String, String>> bjListForNjXy = new ArrayList<HashMap<String,String>>();                   
					 List<HashMap<String, String>> bjListForNjXyZy = new ArrayList<HashMap<String,String>>(); 
					 for (HashMap<String, String> map : allBjList) {
						
						if (nj.equalsIgnoreCase(map.get("nj"))){
							bjListForNj.add(map);
						} 
						
						if (nj.equalsIgnoreCase(map.get("nj"))
								&& xydm.equalsIgnoreCase(map.get("xydm"))){
							bjListForNjXy.add(map);
						}
						
						if (xydm.equalsIgnoreCase(map.get("xydm"))
								&& zydm.equalsIgnoreCase(map.get("zydm"))
								&& nj.equalsIgnoreCase(map.get("nj"))) {
							bjListForNjXyZy.add(map);
						}
					}
					 //xydm!!zydm!!nj ��Ӧ�İ༶�б�
					bjMap.put(xydm+"!!"+zydm+"!!"+nj, bjListForNjXyZy);
					//!!zydm!!nj ��Ӧ�İ༶�б�
					bjMap.put("!!"+zydm+"!!"+nj, bjListForNjXyZy);
					//!!!!nj ��Ӧ�İ༶�б�
					bjMap.put("!!!!"+nj, bjListForNj);
					//xydm!!!!nj  ��Ӧ�İ༶�б�
					bjMap.put(xydm+"!!!!"+nj, bjListForNjXy);
				}
			}
		}
		Base.setBjallMap(bjMap);
	}
	
	//�߼���ѯ����
	public void initalSearch(){
		SearchService search = new SearchService();
		// ѧԺ
		List<HashMap<String, Object>> xyTjList = search.getNewXyPxList("","","","");
		// רҵ
		List<HashMap<String, Object>> zyTjList = search.getNewZyPxList(null,"","","","");
		// �༶
		List<HashMap<String, Object>> bjTjList = search.getNewBjPxList(null,"","","","");
		
		SearchService.setXyTjList(xyTjList);
		SearchService.setZyTjList(zyTjList);
		SearchService.setBjTjList(bjTjList);
	}
	
	/**
	 * ��ȡ������ͷ
	 */
	public String[] getExportTop(XssjtbForm myForm){
		String[] title = null;
		if("xg_xtwh_bmdmb".equals(myForm.getJcb())){
			title = new String[]{"�쳣��","���Ŵ���","��������","�쳣ԭ��"};
		}
		if("xg_xtwh_zydmb".equals(myForm.getJcb())){
			title = new String[]{"�쳣��","רҵ����","רҵ����","���Ŵ���","��������","�쳣ԭ��"};
		}
		if("xg_xtwh_bjdmb".equals(myForm.getJcb())){
			title = new String[]{"�쳣��","�༶����","�༶����","רҵ����","רҵ����","�꼶","�쳣ԭ��"};
		}if("xg_xtwh_xsxxb".equals(myForm.getJcb())){
			title = new String[]{"�쳣��","ѧ��","����","�Ա�","�쳣ԭ��"};
		}
		return title;
	}

	public static HashMap<String, List<HashMap<String, String>>> getZyMap() {
		return zyMap;
	}

	public static void setZyMap(HashMap<String, List<HashMap<String, String>>> zyMap) {
		XssjtbService.zyMap = zyMap;
	}

	public static HashMap<String, List<HashMap<String, String>>> getBjMap() {
		return bjMap;
	}

	public static void setBjMap(HashMap<String, List<HashMap<String, String>>> bjMap) {
		XssjtbService.bjMap = bjMap;
	}
	
}
