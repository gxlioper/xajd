package xgxt.xljk.zxzx.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.xljk.zxzx.form.Xljk_zxspp_Form;
import xgxt.xljk.zxzx.util.Xljk_DAO;

public class xljk_xzsxx_ppService {
	
	/**
	 * 辅导员列表
	 */
	public List<HashMap<String,String>>getFdyList(){
		Xljk_DAO dao=new Xljk_DAO();
		return dao.getFdyList();
	}
	
	/**
	 * 心理咨询师列表
	 */
	public List<HashMap<String,String>>getZxsList(){
		Xljk_DAO dao=new Xljk_DAO();
		return dao.getZxsList();
	}
	
	/**
	 * 获取已匹配职工信息的咨询师信息
	 * 2010.10.21 qlj
	 */
	public List<HashMap<String,String>>getPpzxsList(){
		Xljk_DAO dao=new Xljk_DAO();
		return dao.getPpzxsList();
	}
	
	/**
	 * 根据ZGH，XM，BMDM查询FDY信息
	 * 
	 */
	public List<HashMap<String,String>>getFdyListByTj(String zgh,String xm,
			String bmdm){
		Xljk_DAO dao=new Xljk_DAO();
		return dao.getFdyListByTj(zgh, xm, bmdm);
	}
	
	/**
	 * 根据zxxxm,zxxbh查询咨询师信息
	 * 
	 */
	public List<HashMap<String,String>>getZxsListByTj(String zxxbh,String zxxxm){
		Xljk_DAO dao=new Xljk_DAO();
		return dao.getZxsListByTj(zxxbh, zxxxm);
	}
	
	/**
	 * 批量更新操作
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
