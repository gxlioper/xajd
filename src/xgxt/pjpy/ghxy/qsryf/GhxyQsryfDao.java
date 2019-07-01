package xgxt.pjpy.ghxy.qsryf;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.DAO.DAO;
import xgxt.pjpy.ghxy.bjryf.GhxyBjryfModel;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class GhxyQsryfDao {
	
	/**
	 * ��ȡ�����б�
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<String[]>getQsList(GhxyQsryfForm form,String userName) throws Exception{
		GhxyQsryfModel ghxyQsryfModel=new GhxyQsryfModel();
		BeanUtils.copyProperties(ghxyQsryfModel,form);
		MakeQuery makeQuery=new MakeQuery();
		String []colList={"lddm","cs","qsh"};
		makeQuery.makeQuery(colList, new String[]{}, ghxyQsryfModel);
		String sql="select ldmc, ssbh, lddm, cs, qsh,noxx, rownum r from (select a.lddm, a.qsh, a.cs, a.ssbh, a.ldmc,(case when c.ssbh is null then '������û�з��䣬ά��������Ч' else '�����ҿ���ά������' end)noxx  from view_ssxx a left join ssfpb c on a.ssbh=c.ssbh where exists ( select * from view_gyfdyxx b where a.xqdm = b.xqdm  and a.lddm = b.lddm and b.yhm = '"+userName+"'))";
		return CommonQueryDAO.commonQuery(sql,makeQuery.getQueryString() ,makeQuery.getInputList(),
				new String[]{"ssbh","ldmc","cs","qsh","noxx"},ghxyQsryfModel);
	}
	
	
	
	/**
	 * ��ȡָ������
	 */
	public List<HashMap<String,String>>getQszb(){
		DAO dao=DAO.getInstance();
		String sql="select zbdm,zbnr from qszbdmb";
		return dao.getList(sql, new String[]{}, new String[]{"zbdm","zbnr"});
	}
	
	/**
	 * �������һ�ȡ���������ֱ���Ϣ
	 */
	public List<HashMap<String,String>>getZblb(String sql){
		DAO dao=DAO.getInstance();
		return dao.getList(sql, new String[]{}, new String[]{"plssbh","xmdm","xmfz","pc","xn","xq"});
	}
	
	/**
	 * ��ȡ�������������α���Ϣ
	 */
	public List<HashMap<String,String>>getRyfpc(){
		DAO dao=DAO.getInstance();
		String sql="select pcdm,pcmc from qsryfpcb";
		return dao.getList(sql,new String[]{},new String[]{"pcdm","pcmc"});
	}
	
	/**
	 * ��ȡ���Ҽӷ���Ϣ
	 */
	public List<String[]>getBjjfxx(String bjdm){
		CommonQueryDAO dao=new CommonQueryDAO();
		String query="";
		String sql="(select * from view_ghxy_qsryf where plssbh=?)";
		return dao.commonQueryNotFy(sql,query ,new String[]{bjdm},new String[]{"xn","xq","pcmc","ldmc","qsh","xmmc","xmfz"},null);
	}
	
	/**
	 * ��ȡ���Ҽӷ���Ϣ
	 */
	public List<String[]>getBlszxx(){
		CommonQueryDAO dao=new CommonQueryDAO();
		String query="";
		String sql="(select * from view_ghxy_qsryfbl )";
		return dao.commonQueryNotFy(sql,query ,new String[]{},new String[]{"djdm","djmc","jf","bjbl"},null);
	}
	
	/**
	 * �������������ñ�
	 * @return
	 */
	public List<HashMap<String,String>>ryfdjList(){
		DAO dao=DAO.getInstance();
		String sql="select djdm,djmc from qsryfszb";
		return dao.getList(sql,new String[]{},new String[]{"djdm","djmc"});
	}
	
	/**
	 * ��ȡ�꼶��������
	 */
	public List<HashMap<String,String>>getZQsByNj(String nj,String xqqdm){
		DAO dao=DAO.getInstance();
		String sql="select count(*)zqss from view_ssxx a,ssfpb b where b.nj=? and a.yqdm=? and a.ssbh=b.ssbh";
		return dao.getList(sql,new String[]{nj,xqqdm},new String[]{"zqss"});
	}
	
	/**
	 * ���������ȼ���ȡ���������ȼ�����
	 */
	public List<HashMap<String,String>>getQsRyfSl(String nj,String djdm,String pc){
		DAO dao=DAO.getInstance();
		String sql="select count(*)rydj from view_ghxy_qsryfsh where nj=? and djdm=? and pc=?";
		return dao.getList(sql,new String[]{nj,djdm,pc},new String[]{"rydj"});
	}
	
	/**
	 * �ж��Ƿ񳬳����ȼ�������
	 */
	public boolean checkDjSl(String djdm,float djbl){
		DAO dao=DAO.getInstance();
		String djblStr=""+djbl;
		String sql="select count(*)djsl from ghxy_qsryfblsz where djdm=? and to_number(bjbl)> ? ";
		List<HashMap<String,String>>list=dao.getList(sql,new String[]{djdm,djblStr},new String[]{"djsl"});
		HashMap<String,String>map=list.get(0);
		if("0".equals(map.get("djsl"))){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * ѧ��
	 */
	public List<HashMap<String,String>>yqqList(){
		DAO dao=DAO.getInstance();
		String sql="select yqdm yqqdm,yqmc yqqmc from yqdmb";
		return dao.getList(sql, new String[]{}, new String[]{"yqqdm","yqqmc"});
	}
	
	/**
	 *�ж����ҵ��������Ƿ����
	 */
	public HashMap<String,String> checkQsryf(String ssbh,String pc,String xn,String xq){
		DAO dao=DAO.getInstance();
		String sql="select qyfdysh,xxsh from view_ghxy_qsryfsh where plssbh=? and pc=? and xn=? and xq=?";
		List<HashMap<String,String>>list= dao.getList(sql, new String[]{ssbh,pc,xn,xq}, new String[]{"qyfdysh","xxsh"});
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * ���ҹ���Ա
	 * 
	 */
	public boolean isGygly(String userName){
		DAO dao=DAO.getInstance();
		String sql="select * from view_gyfdyxx where yhm=?";
		List<HashMap<String,String>>list=dao.getList(sql,new String[]{userName}, new String[]{});
		if(list.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * ��ʼ������
	 */
	public HashMap<String,String>initCs(String lddm){
		DAO dao=DAO.getInstance();
		String sql="SELECT cs FROM SSLDDMB WHERE LDDM=?";
		List<HashMap<String,String>>list=dao.getList(sql,new String[]{lddm}, new String[]{"cs"});
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * ��ʼ���Һ�
	 */
	public HashMap<String,String>initCs(String lddm,String cs){
		DAO dao=DAO.getInstance();
		String sql="select * from view_ssxx where lddm=? and cs=? ";
		List<HashMap<String,String>>list=dao.getList(sql,new String[]{lddm}, new String[]{"cs"});
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
}
