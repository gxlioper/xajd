
package xgxt.hzjy.dao;

import xgxt.DAO.DAO;
import xgxt.action.Base;

public class HzjyDao {
	   
	//dwr ���������ɼ�¼��������Ϣ���
	   public String[] getHzdwscore(String level){	   
		   DAO dao  = DAO.getInstance();

		   String sql = "select * from hzjy_realscoresetb where savetype=? and lev=?";
		   
		   String[] scoreinfo = dao.getOneRs(sql, new String[]{ "������λ����",level }, new String[]{
				   "score","lev","describe"
		   });
		   
		   if("".equals(level)){
			   String[] scoreinfonull = {"","",""};
			   scoreinfo = scoreinfonull;
			   
		   }
		   
		   return scoreinfo;
	   }
	   
	   
	 //dwr Э��Ա�������
	   
      public String[] getXtyscore(String level){
		   

		   
		   DAO dao  = DAO.getInstance();

		   String sql = "select * from hzjy_realscoresetb where savetype=? and lev=?";
		   
		   String[] scoreinfo = dao.getOneRs(sql, new String[]{ "Э��Ա����",level }, new String[]{
				   "score","lev","describe"
		   });
		   
		   if("".equals(level)){
			   String[] scoreinfonull = {"","",""};
			   scoreinfo = scoreinfonull;
			   
		   }
		  
		   return scoreinfo;
	   }
      
 	 //dwr ҵ�񱨸��������
	   
      public String[] getYwbgscore(String level){
		   

		   
		   DAO dao  = DAO.getInstance();

		   String sql = "select * from hzjy_realscoresetb where savetype=? and lev=?";
		   
		   String[] scoreinfo = dao.getOneRs(sql, new String[]{ "ҵ�񱨸�����",level }, new String[]{
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
    	  String hzdwscore = dao.getOneRs(sql, new String[]{ "������λ����",hzdwlevel }, "score");
    	  
    	  String xtyscore = dao.getOneRs(sql, new String[]{ "Э��Ա����",xtylevel }, "score");
    	  
    	  String ywbgscore = dao.getOneRs(sql, new String[]{ "ҵ�񱨸�����",ywbglevel }, "score");
    	  
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
	   //��ø�ѧԺ���úõ�ʱ���
      public String[] getSjd(String xydm){
    	  DAO dao = DAO.getInstance();
    	  
    	  String sql = "select hzjykssj,hzjyjssj from hzjy_xysjszb where xydm=?";
    	  
    	  String[] sjdinfo = dao.getOneRs(sql, new String[]{ xydm }, new String[]{ "hzjykssj","hzjyjssj"}); 
    	  
    	  if(null==sjdinfo){
    		  sjdinfo = new String[]{"",""};
    	  }  
    	  return sjdinfo;
      }
      //�û������Ƿ���ڸ��û�
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
