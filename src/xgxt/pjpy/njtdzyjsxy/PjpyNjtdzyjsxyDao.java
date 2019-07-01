package xgxt.pjpy.njtdzyjsxy;

import java.util.ArrayList;
import java.util.HashMap;

import xgxt.DAO.DAO;
import xgxt.action.Base;

/**
 * Title:�Ͼ�����ְҵ����ѧԺ�����������ݲ�����
 * Copyright:Copright(c)2008
 * Company:�����������ӹ������޹�˾
 * @Author:Lp
 * @version 1.0
 * �������ڣ�2008-05-26
 */
public class PjpyNjtdzyjsxyDao  {
	DAO dao = DAO.getInstance();
	/**
	 * @return �Ͼ������ۺ����ʳɼ������ѯ�ı�ͷ��
	 */
	public ArrayList<HashMap<String, String>> getNjtdZhszcjSearchTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] opCols = {"xn","xq","xh","xm","bjmc","zzbx","shgd","zzsj","jtgn","jkfd","ssshbx","xxtd","xxcj","jsjsp",
				          "yysp","zyczjn","tykcj","tzjk","tydl","xlsz","cxcznl","zyjsnl","shsjnl","zzglnl","wttc","tsbz"};
		String[] cnCols = {"ѧ��","ѧ��","ѧ��", "����","�༶����","���α���","��ṫ��","�����ؼ�","�������","���ܶ�","�����������",
				          "ѧϰ̬��","ѧϰ�ɼ�","�����ˮƽ","Ӣ��ˮƽ","רҵ��������","�����γɼ�","���ʽ���","��������","��������","���´�������",
				          "ְҵ��������","���ʵ������","��֯��������","�����س�","�������"};
		for(int i = 0; i < opCols.length; i++) {
			HashMap<String, String> temp = new HashMap<String, String>();
			temp.put("en", opCols[i]);
			temp.put("cn", cnCols[i]);
			result.add(temp);
		}
		return result;
	}
 /**
 * @param pnzModel
 * @return �Ͼ������ۺ����ʳɼ������ѯ�����
 */
    public ArrayList<String[]>getNjtdZhszcjResult(PjpyNjtdzyjsxyZhszcjModel pnzModel){
    	ArrayList<String[]> result =new ArrayList<String[]>();
		String[] colList = {"xn","xq","xh","xm","bjmc","zzbx","shgd","zzsj","jtgn","jkfd","ssshbx","xxtd","xxcj","jsjsp",
		                   "yysp","zyczjn","tykcj","tzjk","tydl","xlsz","cxcznl","zyjsnl","shsjnl","zzglnl","wttc","tsbz"};
		String nj = pnzModel.getNj();
		String xh = pnzModel.getXh();
		String xm = pnzModel.getXm();
		String bjdm = pnzModel.getBjdm();
		String xydm = pnzModel.getXydm();
		String zydm = pnzModel.getZydm();
		String xq = pnzModel.getXq();
		String xn = pnzModel.getXn();
    	String sql = "select xn,xq,xh,xm,bjmc,zzbx,shgd,zzsj,jtgn,jkfd,ssshbx,xxtd,xxcj,jsjsp ,yysp,zyczjn,tykcj,tzjk,tydl,"
    		         +"xlsz,cxcznl,zyjsnl,shsjnl,zzglnl,wttc,tsbz from view_njtdzhszcj ";
    	StringBuffer querry = new StringBuffer();//��ѯ����
    	querry.append(" where 1=1 ");
    	querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
    	querry.append(Base.isNull(xh)?"":" and xh='"+xh+"' ");
    	querry.append(Base.isNull(xm)?"":" and xm like '%"+xm+"%' ");
    	querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
    	querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
    	querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
    	querry.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
    	querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
    	result = dao.rsToVator(sql+querry.toString(),new String[]{},colList);
		return result;
    }
  /**
   * 
   * @param xh
   * @param xn
   * @param xq
   * @return �Ͼ�����ְҵ����ѧԺ����ѧ���ۺ��������۳ɼ������
   */
	public ArrayList<HashMap<String, String>> getNjtdZhszcjJsX(String xh,String xn ,String xq){
		ArrayList<HashMap<String,String>> result =  new ArrayList<HashMap<String,String>>();
		StringBuffer querry = new StringBuffer();		
		querry.append(" where 1=1 ");
 		querry.append(Base.isNull(xh)?"and 1=2 ":"and xh='"+xh+"' ");
 		querry.append(Base.isNull(xq)?"and 1=2 ":"and xq='"+xq+"' ");
 		querry.append(Base.isNull(xn)?"and 1=2 ":"and xn='"+xn+"' ");
 		String sql = " select zzbx,shgd,zzsj,jtgn,jkfd,ssshbx,xxtd,xxcj,jsjsp,yysp,zyczjn,tykcj,tzjk,tydl,xlsz,cxcznl,zyjsnl,shsjnl,"
 			         +"zzglnl,wttc,tsbz from njtd_zhszcjb";
 		String[] colList = new String[]{"zzbx","shgd","zzsj","jtgn","jkfd","ssshbx","xxtd","xxcj","jsjsp","yysp","zyczjn","tykcj",
 			               "tzjk","tydl","xlsz","cxcznl","zyjsnl","shsjnl","zzglnl","wttc","tsbz"};	
 		result = dao.getArrayList(sql+querry.toString(), new String[]{},colList);
		return 	result;
	}	
}
