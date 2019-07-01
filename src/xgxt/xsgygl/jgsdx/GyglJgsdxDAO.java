/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-7-10 ����02:15:50</p>
 */
package xgxt.xsgygl.jgsdx;

import java.util.ArrayList;
import java.util.HashMap;

import xgxt.DAO.DAO;
import xgxt.action.Base;


public class GyglJgsdxDAO {
	/**���������걨��Ϣ����*/
	public boolean sbXxSave(GyglJgsdxWmqssbModel Model) throws Exception{
		DAO dao       = DAO.getInstance();
		boolean done = false;
		String xn     = Model.getXn();
		String xq     = Model.getXq();
		String lddm   = Model.getLddm();
		String qsh    = Model.getQsh();
		String sj     = Model.getPysj();
		String bz     = Model.getBz();
		String sbr    = Model.getXm();
		String ssbh   = dao.getOneRs("select ssbh from ssxxb where lddm=? and qsh=?",new String[]{lddm,qsh},"ssbh");
		String sql    = " delete from wmqssbb where xn||xq||ssbh=? ";
		done          = dao.runUpdate(sql,new String[]{xn+xq+ssbh});
		if(done){
			sql  = "insert into wmqssbb(xn,xq,pysj,ssbh,bz,xm)values(?,?,?,?,?,?)";
			done = dao.runUpdate(sql,new String[]{xn,xq,sj,ssbh,bz,sbr});
		}
		return done;	   
	}
	/**�������������걨��Ϣ����*/ 
	public HashMap<String,String> getWmQsSbXx(String pkValue){   
		DAO dao    = DAO.getInstance();
		String sql = " select xn,xq,pysj,ssbh,bz,xysh,xxsh,xm from wmqssbb where xn||xq||ssbh=? ";
		return dao.getMap(sql, new String[]{pkValue}, new String[]{"xn","xq","pysj","ssbh","bz","xysh","xxsh","xm"});	   
	}
	/**���ظ���Ա�û��������Ҳ�ѯ���*/
	public ArrayList<String[]> getwmQsSbFdyshRes(GyglJgsdxWmqssbModel Model){
		DAO dao                = DAO.getInstance();		
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String querry          =  whereSql(Model);
		String yesNo           = Model.getYesNo();//��˱�־
		querry           +=Base.isNull(yesNo)?"":" and fdysh='"+yesNo+"' ";
		querry           += " order by pysj asc  ";
		String[] colList = new String[]{"prkey", "bgcolor","xn","xq","ldmc","qsh","pysj","fdysh"};//��ѯ��ʾ�ֶ�
		String sql       = " select xn||xq||ssbh prkey,(case when fdysh='ͨ��' then '#FFFFFF' else '#CCCCCC' end)bgcolor ," 
			+"xn,xq,ldmc,qsh,pysj,fdysh from view_wmqssbxx ";
//		ִ�в�ѯ
		rs = dao.rsToVator(sql + querry, new String[]{},colList);
		return rs;
	}
	/**����Ժϵ�û��������Ҳ�ѯ���*/
	public ArrayList<String[]> getwmQsSbXyshRes(GyglJgsdxWmqssbModel Model){
		DAO dao                = DAO.getInstance();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String querry          = whereSql(Model);
		String yesNo           = Model.getYesNo();//��˱�־
		querry            +=Base.isNull(yesNo)?"":" and xysh='"+yesNo+"' ";
		querry            += " and fdysh='ͨ��' ";
		querry            += " order by pysj asc  ";
		String[] colList  = new String[]{"prkey", "bgcolor","xn","xq","ldmc","qsh","pysj","fdysh","xysh"};//��ѯ��ʾ�ֶ�
		String sql        = " select xn||xq||ssbh prkey,(case when xysh='ͨ��' then '#FFFFFF' else '#CCCCCC' end)bgcolor," 
			+" xn,xq,ldmc,qsh,pysj,fdysh,xysh from view_wmqssbxx ";
//		ִ�в�ѯ
		rs = dao.rsToVator(sql + querry, new String[]{},colList);		
		return rs;
	}
	/**����ѧУ�û��������Ҳ�ѯ���*/
	public ArrayList<String[]> getwmQsSbXxshRes(GyglJgsdxWmqssbModel Model){
		DAO dao                = DAO.getInstance();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String querry          = whereSql(Model);
		String yesNo           = Model.getYesNo();//��˱�־
		querry            +=Base.isNull(yesNo)?"":" and xxsh='"+yesNo+"' ";
		querry            += " and xysh='ͨ��' ";
		querry            += " order by pysj asc  ";
		String[] colList  = new String[]{"prkey", "bgcolor","xn","xq","ldmc","qsh","pysj","fdysh","xysh","xxsh"};//��ѯ��ʾ�ֶ�
		String sql        = " select xn||xq||ssbh prkey,(case when xxsh='ͨ��' then '#FFFFFF' else '#CCCCCC' end)bgcolor," 
			+" xn,xq,ldmc,qsh,pysj,fdysh,xysh,xxsh from view_wmqssbxx ";
//		ִ�в�ѯ
		rs = dao.rsToVator(sql + querry, new String[]{},colList);		
		return rs;
	}
	/**��ѯ����*/
	public String whereSql (GyglJgsdxWmqssbModel Model){		
		StringBuffer querry = new StringBuffer();
		String xn     = Model.getXn();//ѧ��
		String xq     = Model.getXq();//ѧ��
		String lddm   = Model.getLddm();//¥������
		String qsh    = Model.getQsh();//���Һ�
		String pysj   = Model.getPysj();//����ʱ��
		
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
		querry.append(Base.isNull(lddm)?"":" and lddm='"+lddm+"' ");
		querry.append(Base.isNull(qsh)?"":" and qsh='"+qsh+"' ");
		querry.append(Base.isNull(pysj)?"":" and pysj='"+pysj+"' ");		
		return querry.toString();
	}
	/**
	 *  @return ����������˸���Ա��ѯ�ı�ͷ
	 */
	public ArrayList<HashMap<String, String>> getwmQsShFdyTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] colList   = null;
		String[] colListCN = null;
		// �����뷽��getwmQsSbXyShSearch�е��������һ��
		colList = new String[]{"prkey", "bgcolor","xn","xq","ldmc","qsh","pysj","fdysh"};//��ѯ��ʾ�ֶ�
		colListCN = new String[]{ "����", "����ɫ", "ѧ��", "ѧ��", "¥��", "�����", "����ʱ��","����Ա���"};
		for (int i = 0; i < colList.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", colList[i]);
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
	/**
	 * @return �����������ѧԺ��ѯ�ı�ͷ
	 */
	public ArrayList<HashMap<String, String>> getwmQsShXyTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] colList   = null;
		String[] colListCN = null;
		// �����뷽��getwmQsSbXyShSearch�е��������һ��
		colList = new String[]{"prkey", "bgcolor","xn","xq","ldmc","qsh","pysj","fdysh","xysh"};//��ѯ��ʾ�ֶ�
		colListCN = new String[]{ "����", "����ɫ", "ѧ��", "ѧ��", "¥��", "�����", "����ʱ��","����Ա���","Ժϵ���"};
		for (int i = 0; i < colList.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", colList[i]);
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
	/**
	 * @return �����������ѧУ��ѯ�ı�ͷ
	 */
	public ArrayList<HashMap<String, String>> getwmQsShXxTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] colList   = null;
		String[] colListCN = null;
		// �����뷽��getwmQsSbXyShSearch�е��������һ��
		colList = new String[]{"prkey", "bgcolor","xn","xq","ldmc","qsh","pysj","fdysh","xysh","xxsh"};//��ѯ��ʾ�ֶ�
		colListCN = new String[]{ "����", "����ɫ", "ѧ��", "ѧ��", "¥��", "�����", "����ʱ��","����Ա���","Ժϵ���","ѧУ���"};
		for (int i = 0; i < colList.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", colList[i]);
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
	
	public HashMap<String,String> wmSbOneInfo(String pkValue,String shType){
		DAO dao                    = DAO.getInstance();
		HashMap<String,String> map = new HashMap<String,String>();
		String pk                  = "xn||xq||ssbh";
		String[] colList           = {"xn","xq","pysj","ssbh","bz","xm","yesNo","lddm","ldmc","qsh"};
		String sql                 = "";		
		sql = "select xn,xq,pysj,ssbh,bz,xm,"+shType+" yesNo,lddm,ldmc,qsh from view_wmqssbxx where "+pk+"=? ";		              
	    map = dao.getMap(sql,new String[]{pkValue},colList);
		return map;	
	}
	
	/**���������걨����Ա���*/
	public boolean wmqsFdyShSave(String pkValue,String yesNo) throws Exception{
		DAO dao       = DAO.getInstance();
		String pk     = "xn||xq||ssbh";
		String sql    = " update wmqssbb set fdysh=? where "+pk+"=? ";
		return dao.runUpdate(sql,new String[]{yesNo,pkValue});
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
			}else if(yesNo.equalsIgnoreCase("��ͨ��")){//
				done = dao.runUpdate("delete from wmqspbb where "+pk+"=?",new String[]{pkValue});
			}
		}
		return done;
	}
	public String isUserReq(String pkValue, String userName) {//�жϸ�����¼�Ƿ��ǵ�ǰ�û��걨
		String str = "";
		String pk  = "xn||xq||ssbh";
		DAO dao    = DAO.getInstance();
		String sql = "select count(*) ct from wmqssbb where "+pk+" =?  and xm=? ";
		str = dao.getOneRs(sql, new String[]{pkValue,userName},"ct");
		str = (str=="0"||str.equalsIgnoreCase("0"))?"no":"yes";
		return str;
	}
}
