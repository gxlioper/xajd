
package xgxt.hzjy.dao;

import xgxt.DAO.DAO;
import xgxt.action.Base;

public class HzjyDao {
	   
	//dwr 合作教育成绩录入其他信息填充
	   public String[] getHzdwscore(String level){	   
		   DAO dao  = DAO.getInstance();

		   String sql = "select * from hzjy_realscoresetb where savetype=? and lev=?";
		   
		   String[] scoreinfo = dao.getOneRs(sql, new String[]{ "合作单位评分",level }, new String[]{
				   "score","lev","describe"
		   });
		   
		   if("".equals(level)){
			   String[] scoreinfonull = {"","",""};
			   scoreinfo = scoreinfonull;
			   
		   }
		   
		   return scoreinfo;
	   }
	   
	   
	 //dwr 协调员评分填充
	   
      public String[] getXtyscore(String level){
		   

		   
		   DAO dao  = DAO.getInstance();

		   String sql = "select * from hzjy_realscoresetb where savetype=? and lev=?";
		   
		   String[] scoreinfo = dao.getOneRs(sql, new String[]{ "协调员评分",level }, new String[]{
				   "score","lev","describe"
		   });
		   
		   if("".equals(level)){
			   String[] scoreinfonull = {"","",""};
			   scoreinfo = scoreinfonull;
			   
		   }
		  
		   return scoreinfo;
	   }
      
 	 //dwr 业务报告评分填充
	   
      public String[] getYwbgscore(String level){
		   

		   
		   DAO dao  = DAO.getInstance();

		   String sql = "select * from hzjy_realscoresetb where savetype=? and lev=?";
		   
		   String[] scoreinfo = dao.getOneRs(sql, new String[]{ "业务报告评分",level }, new String[]{
				   "score","lev","describe"
		   });
		   
		   if("".equals(level)){
			   String[] scoreinfonull = {"","",""};
			   scoreinfo = scoreinfonull;
			   
		   }
		  
		   return scoreinfo;
	   }       
      
      public String[] getAllscore(String hzdwlevel,String xtylevel,String ywbglevel){
    	  
    	  DAO dao = DAO.getInstance();
    	  
    	  String sql ="select score from hzjy_realscoresetb where savetype=? and lev=?";
    	  String hzdwscore = dao.getOneRs(sql, new String[]{ "合作单位评分",hzdwlevel }, "score");
    	  
    	  String xtyscore = dao.getOneRs(sql, new String[]{ "协调员评分",xtylevel }, "score");
    	  
    	  String ywbgscore = dao.getOneRs(sql, new String[]{ "业务报告评分",ywbglevel }, "score");
    	  
    	  int intcountscore = 0;
    	  String strcountscore ="";
    	  String countlevel = "";
    	  
    	  if(null!=hzdwscore&&!"".equals(hzdwscore)){
    		  intcountscore += Integer.parseInt(hzdwscore);
    	  }
    	  
    	  if(null!=xtyscore&&!"".equals(xtyscore)){
    		  intcountscore += Integer.parseInt(xtyscore);
    	  }
    	  
    	  if(null!=ywbgscore&&!"".equals(ywbgscore)){
    		  intcountscore += Integer.parseInt(ywbgscore);
    	  }
    	  
    	  if(intcountscore>=90){
    		  countlevel = "A";
    	  }
    	  
    	  if(intcountscore>=85&&intcountscore<=89){
    		  countlevel = "A-";
    	  }
    	  
    	  if(intcountscore>=82&&intcountscore<=84){
    		  countlevel = "B+";
    	  }
    	  
    	  if(intcountscore>=78&&intcountscore<=81){
    		  countlevel = "B";
    	  }
    	  
    	  if(intcountscore>=75&&intcountscore<=77){
    		  countlevel = "B-";
    	  }
    	  if(intcountscore>=71&&intcountscore<=74){
    		  countlevel = "C+";
    	  }
    	  if(intcountscore>=66&&intcountscore<=70){
    		  countlevel = "C";
    	  }
    	  if(intcountscore>=62&&intcountscore<=65){
    		  countlevel = "C-";
    	  }
    	  if(intcountscore>=60&&intcountscore<=61){
    		  countlevel = "D";
    	  }	  
    	  if(intcountscore<60){
    		  countlevel = "F";
    	  }
    	  
    	  strcountscore=String.valueOf(intcountscore);
    	  
    	  
    	  String[] allscoreinfo = { strcountscore,countlevel };
    	  
    	  
    	  
    	  return allscoreinfo;
      }
	   //获得各学院设置好的时间段
      public String[] getSjd(String xydm){
    	  DAO dao = DAO.getInstance();
    	  
    	  String sql = "select hzjykssj,hzjyjssj from hzjy_xysjszb where xydm=?";
    	  
    	  String[] sjdinfo = dao.getOneRs(sql, new String[]{ xydm }, new String[]{ "hzjykssj","hzjyjssj"}); 
    	  
    	  if(null==sjdinfo){
    		  sjdinfo = new String[]{"",""};
    	  }  
    	  return sjdinfo;
      }
      //用户表中是否存在该用户
      public boolean checkExist(String xtyyhm){
    	  DAO dao = DAO.getInstance();
    	  String sql = "select yhm from yhb where yhm=?";
    	  String yhm = dao.getOneRs(sql, new String[]{xtyyhm}, "yhm");
    	  if(Base.isNull(yhm)){
    		  return false;
    	  }else{
    		  return true;
    	  }	  
      }
}
