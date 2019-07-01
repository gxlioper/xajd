package xgxt.pjpy.hntx.fzxsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.form.SaveForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;

/**
 * Title: 学生工作管理系统 Description:海南大学发展性素质分Service Copyright: Copyright (c) 2010
 * Company: zfsoft Author: sjf Version: 1.0 Time: 2010-08-12
 */
public class FzxszHndxService {
	
	/**
	 * 储存学生发展性素质分
	 * @param model
	 * @return
	 */
	public boolean saveXsFzxszf(FzxszModel model){
		boolean flag = false;
		SaveForm saveForm = new SaveForm();

		String pk = "xh||xn||xmdm";
		String[] xh = model.getXh();
		String xn = model.getXn();
		String[] xmdm = model.getXmdm();
		
		String[] pkValue = new String[xh.length];
		
		for(int i=0;i<xh.length;i++){
			pkValue[i] = xh[i] + xn + xmdm[i];
		}
		
		String[] arrzd = new String[] {"xmdm","xmmc", "xmjf","xh"};
		String[] onezd = new String[] {"xn"};

		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setTableName("hndx_xsfzxszb");

		DAO dao = DAO.getInstance();

		try {
			flag = dao.saveData(saveForm, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 获得学生发展性素质分
	 * @param rs
	 * @param xn
	 * @return
	 */
	public List<String[]> getXsFzxszf(List<String[]> rs, String xn) {
		String[] colList = new String[]{"xh", "xmdm", "xmmc", "xmjf"};
		StringBuilder query = new StringBuilder(" where (");
		String[] input = new String[rs.size()+1];
		
		/// sql学号条件
		for(int i=0;i<rs.size();i++){
			if(i==0){
				query.append(" xh=?");
			}else{
				query.append(" or xh=?");
			}
			
			input[i] = rs.get(i)[1];
		}
		
		// 查询语句中的学年条件
		if(StringUtils.isNotNull(xn)){
			query.append(") and xn=?");
		}else{
			query.append(")");
		}
		input[input.length-1] = xn;
		
		return CommonQueryDAO.commonQueryNotFy("hndx_xsfzxszb", query.toString(), input, colList, "");
	}

	/**
	 * 把两个查询结果合并起来，对应录入模块
	 * @param rs1 查询的最基本信息，对应view_xsjbxx查出来的信息
	 * @param rs2 查询的扩展信息， 对应hndx_xsfzxszb查出来的信息
	 * @prram xmList 存放着项目信息
	 * @return
	 */
	public List<String[]> changeRs(List<String[]> rs1, List<String[]> rs2, List<HashMap<String, String>> xmList){
		List<String[]> list = new ArrayList<String[]>();
		for(int i=0;i<rs1.size();i++){
			// 基本信息数组
			String[] str1 = rs1.get(i);
			//  合并后的数组里面存放学号与所有项目的所加分
			String[] str = new String[xmList.size()+2];
			// str1[1]存放着学号，str1[2]存放着姓名
			str[0] = str1[1];
			str[1] = str1[2];
			
			for(int j=0;j<rs2.size();j++){
				// 扩展信息数组，str2数组中一次存放着学号，项目代码，项目名称，项目加分
				String[] str2 = rs2.get(j);
				
				// 两个学号相同 则把扩展信息中的加分数存放到新的数组中区
				if(str1[1].equalsIgnoreCase(str2[0])){
					
					// 确定分数的顺序
					for(int n=0;n<xmList.size();n++){
						if(str2[1].equalsIgnoreCase(xmList.get(n).get("xmdm"))){
							// str2[3]存放的具体项目的加分数
							str[n+2] = str2[3];
						}
					}
				}
			}
			
			list.add(str);
		}
		return list;
	}
	
	/**
	 * 批量删除学生发展性素质分
	 * @param xh
	 * @param xn
	 * @return
	 */
	public boolean delXsfzxsz(String[] xh, String xn){
		boolean flag = false;
		
		SaveForm saveForm = new SaveForm();
		String pk = "xh||xn";
		String[] pkValue = new String[xh.length];
		
		for(int i=0;i<pkValue.length;i++){
			pkValue[i] = xh[i]+xn;
		}
		
		saveForm.setTableName("hndx_xsfzxszb");
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		DAO dao = DAO.getInstance();
		try {
			flag = dao.delDate(saveForm, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 把从view_hndx_xsfzxsz查询出来的学生信息，组成一条，对应查询模块
	 * @param rs1
	 * @param xmList
	 * @return
	 */
//	public List<String[]> unionRs(List<String[]> rs, List<HashMap<String, String>> xmList){
//		List<String[]> list = new ArrayList<String[]>();
//		
//		for(int i=0;i<rs.size();i++){
//			String[] str1 = rs.get(i);
//			
//			// 合并信息后数组，
//			String[] str = new String[4 + xmList.size()];
//			
//			// str1[0]存放pkvalue
//			str[0] = str1[1]+str1[3];
//			// str1[1]存放xh
//			str[1] = str1[1];
//			// str1[1]存放xm
//			str[2] = str1[2];
//			// str1[3]存放学年
//			str[3] = str1[3];
//			
//			rs.remove(i--);
//			
//			for(int j=0;j<rs.size();j++){
//				String[] str2 = rs.get(j);
//				
//				if(str1[1].equalsIgnoreCase(str2[1]) && str1[3].equalsIgnoreCase(str2[3])){
//					// 确定分数的顺序
//					for(int n=0;n<xmList.size();n++){
//						if(str2[4].equalsIgnoreCase(xmList.get(n).get("xmdm"))){
//							// str2[5]存放的具体项目的加分数
//							str[n+4] = str2[5];
//						}
//					}
//					// 删除合并过的，避免产生重复记录
//					rs.remove(j--);
//				}
//			}
//			
//			list.add(str);
//		}
//		
//		return list;
//	}
	
	
	/**
	 * 通过学号，学年获得某学生的发展性素质分
	 */
	public List<HashMap<String, String>> getXsfzxszfOne(String xh, String xn){
		String query = " where xh=? and xn=?";
		String[] inPutList = new String[]{xh, xn};
		String[] colList = new String[]{"xh","xm","xb","nj","xn","xymc","zymc","bjmc","xmdm","xmmc","xmjf"};
		
		return CommonQueryDAO.commonQueryforList("view_hndx_xsfzxsz", query, inPutList, colList, "");
	}
	
	/**
	 * 发展性素质项目维护
	 * 
	 * @param model
	 * @return
	 */
	public boolean saveFzxszxm(FzxszModel model) {
		boolean flag = false;
		SaveForm saveForm = new SaveForm();

		String pk = "1";
		String[] pkValue = new String[] { "1" };
		String[] arrzd = new String[] { "xmmc", "sxf" };
		String[] onezd = new String[] {};

		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setTableName("hndx_fzxszxmb");

		DAO dao = DAO.getInstance();

		try {
			flag = dao.saveData(saveForm, model);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * 获取所有发展性素质项目
	 * @return
	 */
	public List<HashMap<String, String>> getFzxszxm() {
		String[] colList = new String[] { "xmdm", "xmmc", "sxf" };

		return CommonQueryDAO.commonQueryforList("hndx_fzxszxmb", "",
				new String[] {}, colList, "");
	}
	
	/**
	 * 获取某学年，所有学生的发展性素质分
	 * @param xh
	 * @param xn
	 * @return map: key学号，value分数
	 */
	public List<HashMap<String,String>> getFzxszf(String xn){
		StringBuilder sql = new StringBuilder();
		sql.append("select xh,nvl(sum(nvl(xmjf,0)),0) fzxzs")
			.append(" from hndx_xsfzxszb")
			.append(" where xn=? group by xh");
		
		String[] input = new String[]{xn};
		
		DAO dao = DAO.getInstance();
		return dao.getList(sql.toString(), input, new String[]{"xh", "fzxzf"});
		
	
	}
}
