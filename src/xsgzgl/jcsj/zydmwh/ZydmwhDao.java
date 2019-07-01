package xsgzgl.jcsj.zydmwh;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.jcsj.bmdmwh.BmdmwhDao;

public class ZydmwhDao extends DAO{
	
	/**
	 * ��ȡרҵ��Ϣ���
	 * @param model
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<String[]> getZydmList(ZydmwhForm model) throws Exception{
		// �߼���ѯ����
//		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// �߼���ѯ����ֵ
//		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		//Ȩ�޿���
//		SearchService searchService=new SearchService();
//		String searchTjQx = searchService.getSearchTjByUser(request, "","zydm", null); 	//ѧԺ�û�
		
		String searchTj="";
		List<String> inputV=new ArrayList<String>();
		if(!Base.isNull(model.getQuery_ssbmdm())){
			searchTj+=" and ssbmdm=? ";
			inputV.add(model.getQuery_ssbmdm());
		}
		if(!Base.isNull(model.getQuery_text())){
			searchTj+=" and (zydm like '%'||?||'%' or zymc like '%'||?||'%')";
			inputV.add(model.getQuery_text());
			inputV.add(model.getQuery_text());
		}
		if("��".equals(model.getQuery_sfycsj())){
			searchTj+=" and ycyy is not null ";
		}else if("��".equals(model.getQuery_sfycsj())){
			searchTj+=" and ycyy is null ";
		}
		
		String sql = "select rownum r, a.* from (" +
				"select a.*,(select bmmc from xg_jcsj_bmdmb x where x.bmdm=a.ssbmdm) bmmc,b.ycyy from xg_jcsj_zydmb a " +
				"left join xg_excdatalsb b on a.zydm=b.pk and b.ycb='xg_jcsj_zydmb' "+
				" order by ycyy,zydm ) a where 1 = 1";

		String[] output = new String[] { "zydm","ssbmdm","zydm", "zymc", "bmmc","xz","ycyy"};
		return CommonQueryDAO.commonQuery(sql, searchTj, inputV.toArray(new String[]{}),
				output, model);
		
		
	}
	
	/**
	 * ����רҵ��Ϣ
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	public String saveZydmInfo(ZydmwhForm model,String type) throws Exception{
		String msg="��������";
		boolean b = false;
		String sql;
		if("add".equals(type)){
			sql="select count(1) num from xg_jcsj_zydmb where zydm=?";
			String num=this.getOneRs(sql, new String[]{model.getZydm()}, "num");
			if("0".equals(num)){
				sql="insert into xg_jcsj_zydmb(zydm,zymc,ssbmdm,xz) values(?,?,?,?)";
				b=this.runUpdate(sql,new String[]{model.getZydm(),model.getZymc(),model.getSsbmdm(),model.getXz()});
				msg=(b?"�����ɹ���":"����ʧ�ܣ�");
			}else{
				msg="��רҵ�����Ѵ��ڣ�";
			}
		}else if("update".equals(type)){
			sql="update xg_jcsj_zydmb set zymc=?,ssbmdm=?,xz=? where zydm=?";
			b=this.runUpdate(sql,new String[]{model.getZymc(),model.getSsbmdm(),model.getXz(),model.getZydm()});
			msg=(b?"�����ɹ���":"����ʧ�ܣ�");
		}
		return msg;
	}
	
	/**
	 * ɾ��רҵ��Ϣ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean deleteZydmInfo(ZydmwhForm model) throws Exception{
		String sql="delete from xg_jcsj_zydmb where zydm=?";
		boolean b=this.runUpdate(sql, new String[]{model.getZydm()});
		return b;
	}
	
	/**
	 * ��ȡ�����б�
	 * @return
	 */
	public List<HashMap<String,String>> getBmdmList(){
		String sql="select bmdm,bmmc from xg_jcsj_bmdmb where bmlb='5'";
		return this.getList(sql, new String[]{}, new String[]{"bmdm","bmmc"});
	}
	

	/**
	 * ����쳣����
	 * @return
	 * @throws Exception 
	 */
	public String checkExceptionData() throws Exception{
		String msg="�����ɣ�";
		String table="xg_jcsj_zydmb";
		//����ɵ��쳣����
		String sql="delete from xg_excdatalsb where ycb=?";
		boolean b=runUpdate(sql, new String[]{table});
		if(!b){
			msg="���쳣�������ʧ��";
			return msg;
		}
		//1.���Ŵ���δȫ��ͨ��������²��ý���רҵ������
		String msg_temp=new BmdmwhDao().checkExceptionData();
		if(!msg.equals(msg_temp)){//������Ŵ�����ʧ��
			return "���Ŵ���δȫ��ͨ��������²��ý���רҵ�����⣡���Ŵ�����ʧ�ܣ�"+msg_temp;
		}else{
			sql="select count(1) num from xg_excdatalsb where ycb=?";
			String num=getOneRs(sql, new String[]{"xg_jcsj_bmdmb"}, "num");
			if(!"0".equals(num)){
				return "���Ŵ���δȫ��ͨ��������²��ý���רҵ�����⣡���Ŵ�����"+num+"���쳣���ݻ�δ����";
			}
		}
		//2.רҵ���������ű����ڲ��Ŵ�������Ѵ����Ҹò��Ŵ���Ĳ������Ϊ5��ѧԺ��
		String ycyy="רҵ���������Ų��ڲ��Ŵ�����л�ò��Ŵ���Ĳ������Ϊ����5��ѧԺ��";
		sql="insert into xg_excdatalsb(pk,ycyy,ycb) select zydm,? ycyy,? ycb from xg_jcsj_zydmb a " +
				"where not exists (select 1 from xg_jcsj_bmdmb x where x.bmdm=a.ssbmdm and x.bmlb='5')";
		b=runUpdate(sql, new String[]{ycyy,table});
		if(!b){
			msg="רҵ���������Ų��ڲ��Ŵ�����л�ò��Ŵ���Ĳ������Ϊ����5��ѧԺ������ʧ�ܣ�";
			return msg;
		}
		//3.ͬ�����µ�רҵ���Ʋ����ظ�
		ycyy="ͬ�����µ�רҵ���Ʋ����ظ�";
		sql="insert into xg_excdatalsb(pk,ycyy,ycb) select zydm,? ycyy,? ycb from xg_jcsj_zydmb a " +
		"where not exists (select 1 from xg_excdatalsb x where x.ycb=? and x.pk=a.zydm) " +
		"and exists (select 1 from (select ssbmdm,zymc from xg_jcsj_zydmb group by ssbmdm,zymc having count(1)>1) x where x.zymc=a.zymc)";
		b=runUpdate(sql, new String[]{ycyy,table,table});
		if(!b){
			msg="ͬ������רҵ�����ظ�����ʧ�ܣ�";
			return msg;
		}
		//4.ѧ�Ʒǿ�������
		ycyy="ѧ��Ϊ�ջ�����ֻ򲻺���";
		sql="insert into xg_excdatalsb(pk,ycyy,ycb) select zydm,? ycyy,? ycb from xg_jcsj_zydmb a " +
		"where not exists (select 1 from xg_excdatalsb x where x.ycb=? and x.pk=a.zydm) " +
		"and xz is null or length(xz)<>1 or (xz<'0' or xz>'9')";
		b=runUpdate(sql, new String[]{ycyy,table,table});
		if(!b){
			msg="ѧ��Ϊ�ջ�����ֻ򲻺������ʧ�ܣ�";
			return msg;
		}
		return msg;
	}

}
