package xgxt.pjpy.njtdzyjsxy;

import java.util.ArrayList;
import java.util.HashMap;

import xgxt.DAO.DAO;
import xgxt.action.Base;

/**
 * Title:南京铁道职业技术学院评奖评优数据操作类
 * Copyright:Copright(c)2008
 * Company:杭州正方电子工程有限公司
 * @Author:Lp
 * @version 1.0
 * 生成日期：2008-05-26
 */
public class PjpyNjtdzyjsxyDao  {
	DAO dao = DAO.getInstance();
	/**
	 * @return 南京铁道综合素质成绩导入查询的表头集
	 */
	public ArrayList<HashMap<String, String>> getNjtdZhszcjSearchTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] opCols = {"xn","xq","xh","xm","bjmc","zzbx","shgd","zzsj","jtgn","jkfd","ssshbx","xxtd","xxcj","jsjsp",
				          "yysp","zyczjn","tykcj","tzjk","tydl","xlsz","cxcznl","zyjsnl","shsjnl","zzglnl","wttc","tsbz"};
		String[] cnCols = {"学年","学期","学号", "姓名","班级名称","政治表现","社会公德","尊章守纪","集体观念","艰苦奋斗","宿舍生活表现",
				          "学习态度","学习成绩","计算机水平","英语水平","专业操作技能","体育课成绩","体质健康","体育锻炼","心里素质","创新创造能力",
				          "职业技术能力","社会实践能力","组织管理能力","文体特长","特殊表彰"};
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
 * @return 南京铁道综合素质成绩导入查询结果集
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
    	StringBuffer querry = new StringBuffer();//查询条件
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
   * @return 南京铁道职业技术学院单个学生综合素质评价成绩结果集
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
