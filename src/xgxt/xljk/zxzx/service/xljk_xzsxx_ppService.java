package xgxt.xljk.zxzx.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.xljk.zxzx.form.Xljk_zxspp_Form;
import xgxt.xljk.zxzx.util.Xljk_DAO;

public class xljk_xzsxx_ppService {
	
	/**
	 * ����Ա�б�
	 */
	public List<HashMap<String,String>>getFdyList(){
		Xljk_DAO dao=new Xljk_DAO();
		return dao.getFdyList();
	}
	
	/**
	 * ������ѯʦ�б�
	 */
	public List<HashMap<String,String>>getZxsList(){
		Xljk_DAO dao=new Xljk_DAO();
		return dao.getZxsList();
	}
	
	/**
	 * ��ȡ��ƥ��ְ����Ϣ����ѯʦ��Ϣ
	 * 2010.10.21 qlj
	 */
	public List<HashMap<String,String>>getPpzxsList(){
		Xljk_DAO dao=new Xljk_DAO();
		return dao.getPpzxsList();
	}
	
	/**
	 * ����ZGH��XM��BMDM��ѯFDY��Ϣ
	 * 
	 */
	public List<HashMap<String,String>>getFdyListByTj(String zgh,String xm,
			String bmdm){
		Xljk_DAO dao=new Xljk_DAO();
		return dao.getFdyListByTj(zgh, xm, bmdm);
	}
	
	/**
	 * ����zxxxm,zxxbh��ѯ��ѯʦ��Ϣ
	 * 
	 */
	public List<HashMap<String,String>>getZxsListByTj(String zxxbh,String zxxxm){
		Xljk_DAO dao=new Xljk_DAO();
		return dao.getZxsListByTj(zxxbh, zxxxm);
	}
	
	/**
	 * �������²���
	 * @throws Exception 
	 * 
	 */
	public boolean  plUpdate(Xljk_zxspp_Form zxspp_form) throws Exception{
		Xljk_DAO dao=new Xljk_DAO();
		String []ppzxsxx=zxspp_form.getPpzxsxx();
		List<HashMap<String,String>>list=new ArrayList<HashMap<String,String>>();
		if(ppzxsxx!=null && ppzxsxx.length>0){
			for(int i=0;i<ppzxsxx.length;i++){
				String[] str=ppzxsxx[i].split("!!SplitTwo!!");//
				String []inputStr=str[0].split(",");
				String []queryStr=str[1].split(",");
				HashMap<String,String>hashMap=new HashMap<String,String>();
				hashMap.put("firstquery", queryStr[0]);
				hashMap.put("secundquery", queryStr[1]);
				hashMap.put("firstinput", inputStr[0]);
				hashMap.put("secundinput", inputStr[1]);
				list.add(hashMap);
			}
			
		}
		return dao.plUpdate(list);
	}
}
