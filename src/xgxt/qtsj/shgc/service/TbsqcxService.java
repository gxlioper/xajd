package xgxt.qtsj.shgc.service;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.qtsj.shgc.form.ShgcTbxxForm;
import xgxt.studentInfo.dao.StuInfoDAO;


/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �Ϻ����̱��չ���ģ��Ͷ�������ѯService</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-17</p>
 */
public class TbsqcxService {
	
	/**
	 * ģ����ѯѧ������״̬��Ϣ 
	 * @param model
	 * @return String
	 * */
	@SuppressWarnings("unchecked")
	public ShgcTbxxForm queryTbxx(ShgcTbxxForm model){	
		TbxxwhService tbwhService = new TbxxwhService();
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select xh, xm, nd, bxgsmc, max(sqsj)sqsj, max(xxsh) xxsh,max(xysh)xysh,max(xxyj)xxyj,max(xyyj)xyyj";
		DAO dao = DAO.getInstance();
		List bxxzList = null;
		List rs = null;
		String sCondition = "";
		String[] colList = {"xh","xm","nd","bxgsmc","sqsj"};
		String[] colListCN = null;
		
		sCondition = tbwhService.getQueryCondition(model);
		bxxzList = tbwhService.getBxxzList();
		String[] output = new String[9+bxxzList.size()];
		for(int i=0; i<colList.length; i++){
			output[i] = colList[i];
		}
		//ѭ�����Ͷ������
		for(int i=0; i<bxxzList.size(); i++){
			map = (HashMap<String, String>)bxxzList.get(i);
			String bxxzmc = map.get("bxxzmc");
			sql += ",max(decode(bxxzmc,'" + bxxzmc +"','"+ bxxzmc +"','')) Ͷ������"+ (i+1);
						
			//����ֶ�
			output[5+i]="Ͷ������" + (i+1);
		}
				
		sql = sql + " from View_Xsbxxx " + sCondition + " and sqsj is not null group by xh,xm,nd,bxgsmc";	
		
		output[5+bxxzList.size()] = "xysh";
		output[6+bxxzList.size()] = "xxsh";
		output[7+bxxzList.size()] = "xyyj";
		output[8+bxxzList.size()] = "xxyj";
		
		rs = dao.rsToVator(sql, new String[]{}, output);
		model.setRs(rs);
		
		//��ȡ��ͷ
		colListCN = dao.getColumnNameCN(output, "view_xsbxxx");
		List topTr = dao.arrayToList(output, colListCN);		
		model.setTopTr(topTr);		
		
		return model;
	}
	
	/**
	 * Ͷ����Ϣά�����ݵ���
	 * @param model
	 * @return String
	 * */
	@SuppressWarnings("unchecked")
	public String expData(ShgcTbxxForm model){	
		HashMap<String, String> map = new HashMap<String, String>();
		TbxxwhService tbService = new TbxxwhService();
		String sql = "select xh, xm, nd, bxgsmc, max(sqsj)sqsj, max(xxsh) xxsh";
		List bxxzList = null;
		String sCondition = "";
		
		sCondition = tbService.getQueryCondition(model);
		bxxzList = tbService.getBxxzList();	
		
		//ѭ�����Ͷ������
		for(int i=0; i<bxxzList.size(); i++){
			map = (HashMap<String, String>)bxxzList.get(i);
			String bxxzmc = map.get("bxxzmc");
			sql += ",max(decode(bxxzmc,'" + bxxzmc +"','"+ bxxzmc +"','')) Ͷ������"+ (i+1);		
			
		}		
		sql = sql + " from View_Xsbxxx " + sCondition + " and sqsj is not null group by xh,xm,nd,bxgsmc";		
		return sql;
	}
	
	/**
	 * Ͷ����Ϣ��˲�ѯ
	 * @param model
	 * @return String
	 * */
	@SuppressWarnings("unchecked")
	public ShgcTbxxForm audtingQue(ShgcTbxxForm model){	
		HashMap<String, String> map = new HashMap<String, String>();
		TbxxwhService tbService = new TbxxwhService();
		DAO dao = DAO.getInstance();
		String sCondition = tbService.getQueryCondition(model);
		String userType = model.getUserType();
		List rs = null;
		String[] colList = {"bgcolor", "����", "�к�","xh","xm","nd","bxgsmc","sqsj"};
		String[] colListCN = null;
		
		String sql = "select xh, xm, nd, bxgsmc, max(sqsj)sqsj, max(xxsh) xxsh, max(xysh) xysh,max(xyyj)xyyj, max(xxyj) xxyj";
		sql = "select rownum �к�,(case nvl(a.xxsh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
			+ " a.* from(select xh||nd ����, xh, xm, nd, bxgsmc, max(sqsj)sqsj, max(xxsh) xxsh,max(xysh) xysh,max(xxyj)xxyj,max(xyyj)xyyj" ;
		if(userType != null && "xy".equalsIgnoreCase(userType)){
			sql = "select rownum �к�,(case nvl(a.xysh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
				+ " a.* from(select xh||nd ����, xh, xm, nd, bxgsmc, max(sqsj)sqsj, max(xxsh) xxsh,max(xysh) xysh,max(xxyj)xxyj,max(xyyj)xyyj" ; 
		}
		
		List bxxzList = null;
		bxxzList = tbService.getBxxzList();
		String[] output = new String[12+bxxzList.size()];
		for(int i=0; i<colList.length; i++){
			output[i] = colList[i];
		}
		//ѭ�����Ͷ������
		for(int i=0; i<bxxzList.size(); i++){
			map = (HashMap<String, String>)bxxzList.get(i);
			String bxxzmc = map.get("bxxzmc");
			sql += ",max(decode(bxxzmc,'" + bxxzmc +"','"+ bxxzmc +"','')) Ͷ������"+ (i+1);
			
			//����ֶ�
			output[8+i]="Ͷ������" + (i+1);
		}	
		
		output[8+bxxzList.size()] = "xysh";
		output[9+bxxzList.size()] = "xxsh";
		output[10+bxxzList.size()] = "xyyj";
		output[11+bxxzList.size()] = "xxyj";
		
		if(userType != null && userType.equalsIgnoreCase("xy")){
			sql = sql + " from View_Xsbxxx a" + sCondition + " and sqsj is not null group by xh,xm,nd,bxgsmc order by xysh desc) a ";
		}else{
			sql = sql + " from View_Xsbxxx a" + sCondition + " and sqsj is not null and xysh='ͨ��' group by xh,xm,nd,bxgsmc order by xxsh desc) a ";
		}
		rs = dao.rsToVator(sql, new String[]{}, output);
		
		//��ȡ��ͷ
		colListCN = dao.getColumnNameCN(output, "view_xsbxxx");
		List topTr = dao.arrayToList(output, colListCN);
		
		model.setRs(rs);
		model.setTopTr(topTr);
		return model;
	}
	
	
	/**
	 * ��ȡ��ǰʱ��
	 * @return String
	 * */
	public String getCurrDate(){
		String date = "";
		date = StuInfoDAO.getCurrDate();
		return date;
	}
}
