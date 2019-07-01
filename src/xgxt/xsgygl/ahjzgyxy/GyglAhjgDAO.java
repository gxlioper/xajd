/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description:���ս�����ҵѧԺ��Ԣ����DAO </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-7-1 ����01:54:37</p>
 */
package xgxt.xsgygl.ahjzgyxy;

import java.util.ArrayList;
import java.util.HashMap;

import xgxt.DAO.DAO;
import xgxt.action.Base;

public class GyglAhjgDAO{
   /**���������걨��Ϣ����*/
   public boolean sbXxSave(GyglAhjgWmqssbModel mgawModel) throws Exception{
	   DAO dao       = DAO.getInstance();
	   boolean done = false;
	   String xn   = mgawModel.getXn();
	   String xq   = mgawModel.getXq();
	   String lddm = mgawModel.getLddm();
	   String qsh  = mgawModel.getQsh();
	   String sj   = mgawModel.getPysj();
	   String bz   = mgawModel.getBz();
	   String sbr  = mgawModel.getXm();
	   String qslb = mgawModel.getQslb();
	   String ssbh = dao.getOneRs("select ssbh from ssxxb where lddm=? and qsh=?",new String[]{lddm,qsh},"ssbh");
	   String sql  = " delete from wmqssbb where xn||xq||ssbh=? ";
	   done = dao.runUpdate(sql,new String[]{xn+xq+ssbh});
	   if(done){
		   sql = "insert into wmqssbb(xn,xq,pysj,ssbh,bz,xm,qslb)values(?,?,?,?,?,?,?)";
		   done = dao.runUpdate(sql,new String[]{xn,xq,sj,ssbh,bz,sbr,qslb});
	   }
	   return done;	   
   }
   /**�������������걨��Ϣ����*/ 
   public HashMap<String,String> getWmQsSbXx(String pkValue){   
	   DAO dao = DAO.getInstance();
	   String sql = " select xn,xq,pysj,ssbh,bz,xysh,xxsh,xm,qslb from wmqssbb where xn||xq||ssbh=? ";
	   return dao.getMap(sql, new String[]{pkValue}, 
	   new String[]{"xn","xq","pysj","ssbh","bz","xysh","xxsh","xm","qslb"});	   
   }
   /**����ѧԺ��˲�ѯ���*/
   public ArrayList<String[]> getwmQsSbXyShSearch(GyglAhjgWmqssbModel mgawModel) {
	   DAO dao = DAO.getInstance();
	   ArrayList<String[]> rs = new ArrayList<String[]>();
	   String xn     = mgawModel.getXn();//ѧ��
	   String xq     = mgawModel.getXq();//ѧ��
	   String lddm   = mgawModel.getLddm();//¥������
	   String qsh    = mgawModel.getQsh();//���Һ�
	   String pysj   = mgawModel.getPysj();//����ʱ��
	   String yesNo  = mgawModel.getYesNo();//��˱�־
	   String sql    =  "";
	   String[] colList = null;
	   StringBuffer querry = new StringBuffer();
	   //��ѯ����
	   querry.append(" where 1=1 ");
	   querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
	   querry.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
	   querry.append(Base.isNull(lddm)?"":" and lddm='"+lddm+"' ");
	   querry.append(Base.isNull(qsh)?"":" and qsh='"+qsh+"' ");
	   querry.append(Base.isNull(pysj)?"":" and pysj='"+pysj+"' ");
	   querry.append(Base.isNull(yesNo)?"":" and xysh='"+yesNo+"' ");
	   querry.append(" order by pysj asc ");
	   colList = new String[]{"prkey", "bgcolor","xn","xq","ldmc","qsh","pysj","xysh"};//��ѯ��ʾ�ֶ� 
	   sql     = " select xn||xq||ssbh prkey,(case when xysh='ͨ��' then '#FFFFFF' else '#CCCCCC' end)bgcolor," 
		       + " xn,xq,ldmc,qsh,pysj,xysh from view_wmqssbxx ";
	   //ִ�в�ѯ
	   rs = dao.rsToVator(sql + querry.toString(), new String[]{},colList);
	   return rs;
   }
   public ArrayList<String[]> getwmqsXXshSearch(GyglAhjgWmqssbModel mgawModel) {
	   DAO dao = DAO.getInstance();
	   ArrayList<String[]> rs = new ArrayList<String[]>();
	   String xn     = mgawModel.getXn();//ѧ��
	   String xq     = mgawModel.getXq();//ѧ��
	   String lddm   = mgawModel.getLddm();//¥������
	   String qsh    = mgawModel.getQsh();//���Һ�
	   String pysj   = mgawModel.getPysj();//����ʱ��
	   String yesNo  = mgawModel.getYesNo();//��˱�־
	   String sql    =  "";
	   String[] colList = null;
	   StringBuffer querry = new StringBuffer();
	   //��ѯ����
	   querry.append(" where 1=1 ");
	   querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
	   querry.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
	   querry.append(Base.isNull(lddm)?"":" and lddm='"+lddm+"' ");
	   querry.append(Base.isNull(qsh)?"":" and qsh='"+qsh+"' ");
	   querry.append(Base.isNull(pysj)?"":" and pysj='"+pysj+"' ");
	   querry.append(Base.isNull(yesNo)?"":" and xxsh='"+yesNo+"' ");
	   querry.append(" and xysh='ͨ��'");
	   querry.append(" order by pysj asc ");
	   colList = new String[]{"prkey", "bgcolor","xn","xq","ldmc","qsh","pysj","xysh","xxsh"};//��ѯ��ʾ�ֶ� 
	   sql     = " select xn||xq||ssbh prkey,(case when xxsh='ͨ��' then '#FFFFFF' else '#CCCCCC' end )bgcolor,"
		       +"  xn,xq,ldmc,qsh,pysj,xysh,xxsh from view_wmqssbxx ";  
//	   ִ�в�ѯ
	   rs = dao.rsToVator(sql + querry.toString(), new String[]{},colList);
	   return rs;

   }
   /**
	 * @return ����������˲�ѯ�ı�ͷ
	 */
	public ArrayList<HashMap<String, String>> getwmQsShTitle(String userType) {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] colList   = null;
		String[] colListCN = null;
		// �����뷽��getwmQsSbXyShSearch�е��������һ��
		if(userType.equalsIgnoreCase("xy")){//ѧԺ�û�
			colList = new String[]{"prkey", "bgcolor","xn","xq","ldmc","qsh","pysj","xysh"};//��ѯ��ʾ�ֶ� 
			colListCN = new String[]{ "����", "����ɫ", "ѧ��", "ѧ��", "¥��", "�����", "����ʱ��","Ժϵ���"};
		}else if(userType.equalsIgnoreCase("xx")
				||userType.equalsIgnoreCase("admin")){ //ѧУ�����Ա�û�
			colList = new String[]{"prkey", "bgcolor","xn","xq","ldmc","qsh","pysj","xysh","xxsh"};//��ѯ��ʾ�ֶ� 
			colListCN = new String[]{ "����", "����ɫ", "ѧ��", "ѧ��", "¥��", "�����",  "����ʱ��","Ժϵ���","ѧУ���"};

		}
		for (int i = 0; i < colList.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", colList[i]);
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
	
	public HashMap<String,String> wmSbOneInfo(String pkValue,String userType){
		DAO dao                    = DAO.getInstance();
		HashMap<String,String> map = new HashMap<String,String>();
		String pk                  = "xn||xq||ssbh";
		String[] colList           = {"xn","xq","pysj","ssbh","bz","xm","yesNo","lddm","ldmc","qsh"};
		String sql                 = "";
		if(userType.equalsIgnoreCase("xy")){//��ͬ�����û����صĲ�ͬ�����״̬
			sql = "select xn,xq,pysj,ssbh,bz,xm,xysh yesNo,lddm,ldmc,qsh from view_wmqssbxx where "+pk+"=? ";
		}else{
			sql = "select xn,xq,pysj,ssbh,bz,xm,xxsh yesNo,lddm,ldmc,qsh from view_wmqssbxx where "+pk+"=? ";
		}		              
	    map = dao.getMap(sql,new String[]{pkValue},colList);
		return map;	
	}
	/**���ظ�����ѧ��Υ�ʹ�����Ϣ*/
	public HashMap<String,String>wmQsWjInfo(String pkValue){
		DAO dao       = DAO.getInstance();
		HashMap<String,String> map = new HashMap<String,String>();
		String pk     = "xn||xq||ssbh";
		String sql    = "select xn,xq,ssbh from wmqssbb where "+pk+" =?";
		String[] temA = dao.getOneRs(sql,new String[]{pkValue},new String[]{"xn","xq","ssbh"});
		if(temA != null){
			//��ѧ��ѧ�ڸ�������Υ�ͼ�¼��������
			sql = " select count(a.xh) wjcfrs from( select distinct(a.xh),b.ssbh from xszsxxb a,wmqssbb b where a.ssbh=b.ssbh  "
				+ " ) a,view_wjcf b where a.xh=b.xh and b.xn||b.xq||a.ssbh=?  ";
			String wjrs = dao.getOneRs(sql,new String[]{pkValue},"wjcfrs");
		    map.put("wjrs",wjrs);
		    //������Ŀǰ��ס����
		    sql = " select count(*) rzrs from xszsxxb where ssbh=? ";
		    String rzrs = dao.getOneRs(sql,new String[]{temA[2]},"rzrs");
		    map.put("rzrs",rzrs);
		}
		return map;
	}
	/**���������걨Ժϵ���*/
	public boolean wmqsXyShSave(String pkValue,String yesNo) throws Exception{
		DAO dao       = DAO.getInstance();
		String pk     = "xn||xq||ssbh";
		String sql    = " update wmqssbb set xysh=? where "+pk+"=? ";
		return dao.runUpdate(sql,new String[]{yesNo,pkValue});
	}
	/**���������걨ѧУ���*/
	public boolean wmqsXxShSave(String pkValue,String yesNo) throws Exception{
		DAO dao       = DAO.getInstance();
		boolean done = false;
		String pk     = "xn||xq||ssbh";
		String sql    = " update wmqssbb set xxsh=? where "+pk+"=? ";
		done =  dao.runUpdate(sql,new String[]{yesNo,pkValue});
		if(done){//���ͨ��ʱ����Ϣ���浽����������Ϣά����
			if(yesNo.equalsIgnoreCase("ͨ��")){
				done = dao.runUpdate("delete from wmqspbb where "+pk+"=?",new String[]{pkValue});
				if(done){
					sql = "insert into wmqspbb(xn,xq,pysj,ssbh,bz) select xn,xq,pysj,ssbh,bz from wmqssbb where "+pk+"=?";
					done = dao.runUpdate(sql, new String[]{pkValue});
				}
			}else {//
				done = dao.runUpdate("delete from wmqspbb where "+pk+"=?",new String[]{pkValue});
			}
		}	
		return done;
	}
	/**���ر�ѧ�����������ұ������������Ϣ*/
	public HashMap<String,String> wmQsBlInf(){
		DAO dao    = DAO.getInstance();
		HashMap<String,String> map = new HashMap<String,String>();
	    String sql = "select (select count(*) from view_wmqspbxx d where d.xn=b.xn)ybqss,qszs ,wmqsbz||'%'  bz ,xn,"
				   +" to_char(qszs*wmqsbz/100,'99999999999999') yxqss from (select count(*) qszs from ssxxb ) a,gygl_csszb b where xn =? and xq=?";
		map = dao.getMap(sql, new String[]{Base.currXn,Base.currXq},new String[]{"xn","ybqss","qszs","bz","yxqss"});
		
		if(map.size()!=0){
			if(Integer.parseInt(map.get("ybqss").trim())-Integer.parseInt(map.get("yxqss").trim())>=0){
				map.put("sxbz","1");//"���ޱ�־",�������������Ѵﵽ�򳬹�����
			}else{
				map.put("sxbz","0");//������������δ�ﵽ����
			}
			map.put("Set","yes");
		}else{
			map.put("Set","no");
		}
		return map;
	}
	
	
}
