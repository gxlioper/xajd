package xgxt.pjpy.hntx.fzxsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.form.SaveForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;

/**
 * Title: ѧ����������ϵͳ Description:���ϴ�ѧ��չ�����ʷ�Service Copyright: Copyright (c) 2010
 * Company: zfsoft Author: sjf Version: 1.0 Time: 2010-08-12
 */
public class FzxszHndxService {
	
	/**
	 * ����ѧ����չ�����ʷ�
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
	 * ���ѧ����չ�����ʷ�
	 * @param rs
	 * @param xn
	 * @return
	 */
	public List<String[]> getXsFzxszf(List<String[]> rs, String xn) {
		String[] colList = new String[]{"xh", "xmdm", "xmmc", "xmjf"};
		StringBuilder query = new StringBuilder(" where (");
		String[] input = new String[rs.size()+1];
		
		/// sqlѧ������
		for(int i=0;i<rs.size();i++){
			if(i==0){
				query.append(" xh=?");
			}else{
				query.append(" or xh=?");
			}
			
			input[i] = rs.get(i)[1];
		}
		
		// ��ѯ����е�ѧ������
		if(StringUtils.isNotNull(xn)){
			query.append(") and xn=?");
		}else{
			query.append(")");
		}
		input[input.length-1] = xn;
		
		return CommonQueryDAO.commonQueryNotFy("hndx_xsfzxszb", query.toString(), input, colList, "");
	}

	/**
	 * ��������ѯ����ϲ���������Ӧ¼��ģ��
	 * @param rs1 ��ѯ���������Ϣ����Ӧview_xsjbxx���������Ϣ
	 * @param rs2 ��ѯ����չ��Ϣ�� ��Ӧhndx_xsfzxszb���������Ϣ
	 * @prram xmList �������Ŀ��Ϣ
	 * @return
	 */
	public List<String[]> changeRs(List<String[]> rs1, List<String[]> rs2, List<HashMap<String, String>> xmList){
		List<String[]> list = new ArrayList<String[]>();
		for(int i=0;i<rs1.size();i++){
			// ������Ϣ����
			String[] str1 = rs1.get(i);
			//  �ϲ��������������ѧ����������Ŀ�����ӷ�
			String[] str = new String[xmList.size()+2];
			// str1[1]�����ѧ�ţ�str1[2]���������
			str[0] = str1[1];
			str[1] = str1[2];
			
			for(int j=0;j<rs2.size();j++){
				// ��չ��Ϣ���飬str2������һ�δ����ѧ�ţ���Ŀ���룬��Ŀ���ƣ���Ŀ�ӷ�
				String[] str2 = rs2.get(j);
				
				// ����ѧ����ͬ �����չ��Ϣ�еļӷ�����ŵ��µ���������
				if(str1[1].equalsIgnoreCase(str2[0])){
					
					// ȷ��������˳��
					for(int n=0;n<xmList.size();n++){
						if(str2[1].equalsIgnoreCase(xmList.get(n).get("xmdm"))){
							// str2[3]��ŵľ�����Ŀ�ļӷ���
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
	 * ����ɾ��ѧ����չ�����ʷ�
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
	 * �Ѵ�view_hndx_xsfzxsz��ѯ������ѧ����Ϣ�����һ������Ӧ��ѯģ��
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
//			// �ϲ���Ϣ�����飬
//			String[] str = new String[4 + xmList.size()];
//			
//			// str1[0]���pkvalue
//			str[0] = str1[1]+str1[3];
//			// str1[1]���xh
//			str[1] = str1[1];
//			// str1[1]���xm
//			str[2] = str1[2];
//			// str1[3]���ѧ��
//			str[3] = str1[3];
//			
//			rs.remove(i--);
//			
//			for(int j=0;j<rs.size();j++){
//				String[] str2 = rs.get(j);
//				
//				if(str1[1].equalsIgnoreCase(str2[1]) && str1[3].equalsIgnoreCase(str2[3])){
//					// ȷ��������˳��
//					for(int n=0;n<xmList.size();n++){
//						if(str2[4].equalsIgnoreCase(xmList.get(n).get("xmdm"))){
//							// str2[5]��ŵľ�����Ŀ�ļӷ���
//							str[n+4] = str2[5];
//						}
//					}
//					// ɾ���ϲ����ģ���������ظ���¼
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
	 * ͨ��ѧ�ţ�ѧ����ĳѧ���ķ�չ�����ʷ�
	 */
	public List<HashMap<String, String>> getXsfzxszfOne(String xh, String xn){
		String query = " where xh=? and xn=?";
		String[] inPutList = new String[]{xh, xn};
		String[] colList = new String[]{"xh","xm","xb","nj","xn","xymc","zymc","bjmc","xmdm","xmmc","xmjf"};
		
		return CommonQueryDAO.commonQueryforList("view_hndx_xsfzxsz", query, inPutList, colList, "");
	}
	
	/**
	 * ��չ��������Ŀά��
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
	 * ��ȡ���з�չ��������Ŀ
	 * @return
	 */
	public List<HashMap<String, String>> getFzxszxm() {
		String[] colList = new String[] { "xmdm", "xmmc", "sxf" };

		return CommonQueryDAO.commonQueryforList("hndx_fzxszxmb", "",
				new String[] {}, colList, "");
	}
	
	/**
	 * ��ȡĳѧ�꣬����ѧ���ķ�չ�����ʷ�
	 * @param xh
	 * @param xn
	 * @return map: keyѧ�ţ�value����
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
