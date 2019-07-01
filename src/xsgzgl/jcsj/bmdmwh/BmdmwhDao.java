package xsgzgl.jcsj.bmdmwh;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.utils.CommonQueryDAO;

public class BmdmwhDao extends DAO{
	
	/**
	 * ��ȡ������Ϣ���
	 * @param model
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<String[]> getBmdmList(BmdmwhForm model) throws Exception{
		// �߼���ѯ����
//		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// �߼���ѯ����ֵ
//		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		//Ȩ�޿���
//		SearchService searchService=new SearchService();
//		String searchTjQx = searchService.getSearchTjByUser(request, "","bmdm", null); 	//ѧԺ�û�
		
		String searchTj="";
		List<String> inputV=new ArrayList<String>();
		if(!Base.isNull(model.getQuery_bmlb())){
			searchTj+=" and bmlb=? ";
			inputV.add(model.getQuery_bmlb());
		}
		if(!Base.isNull(model.getQuery_text())){
			searchTj+=" and (bmdm like '%'||?||'%' or bmmc like '%'||?||'%')";
			inputV.add(model.getQuery_text());
			inputV.add(model.getQuery_text());
		}
		if("��".equals(model.getQuery_sfycsj())){
			searchTj+=" and ycyy is not null ";
		}else if("��".equals(model.getQuery_sfycsj())){
			searchTj+=" and ycyy is null ";
		}
		
		String sql = "select rownum r, a.* from (" +
				"select a.*,(case when bmlb='1' then '����' when bmlb='5' then 'ѧԺ' else '' end) bmlbmc," +
				"b.ycyy from xg_jcsj_bmdmb a left join xg_excdatalsb b on a.bmdm=b.pk and b.ycb='xg_jcsj_bmdmb' order by ycyy,bmdm) a where 1 = 1";

		String[] output = new String[] { "bmdm","bmdm", "bmmc", "bmlb","bmlbmc","ycyy"};
		return CommonQueryDAO.commonQuery(sql, searchTj, inputV.toArray(new String[]{}),
				output, model);
		
		
	}
	
	/**
	 * ���沿����Ϣ
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	public String saveBmdmInfo(BmdmwhForm model,String type) throws Exception{
		String msg="��������";
		boolean b = false;
		String sql;
		if("add".equals(type)){
			sql="select count(1) num from xg_jcsj_bmdmb where bmdm=?";
			String num=this.getOneRs(sql, new String[]{model.getBmdm()}, "num");
			if("0".equals(num)){
				sql="insert into xg_jcsj_bmdmb(bmdm,bmmc,bmlb) values(?,?,?)";
				b=this.runUpdate(sql,new String[]{model.getBmdm(),model.getBmmc(),model.getBmlb()});
				msg=(b?"�����ɹ���":"����ʧ�ܣ�");
			}else{
				msg="�ò��Ŵ����Ѵ��ڣ�";
			}
		}else if("update".equals(type)){
			sql="update xg_jcsj_bmdmb set bmmc=?,bmlb=? where bmdm=?";
			b=this.runUpdate(sql,new String[]{model.getBmmc(),model.getBmlb(),model.getBmdm()});
			msg=(b?"�����ɹ���":"����ʧ�ܣ�");
		}
		return msg;
	}
	
	/**
	 * ɾ��������Ϣ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean deleteBmdmInfo(BmdmwhForm model) throws Exception{
		String sql="delete from xg_jcsj_bmdmb where bmdm=?";
		boolean b=this.runUpdate(sql, new String[]{model.getBmdm()});
		return b;
	}
	
	/**
	 * ����쳣����
	 * @return
	 * @throws Exception 
	 */
	public String checkExceptionData() throws Exception{
		String msg="�����ɣ�";
		String table="xg_jcsj_bmdmb";
		//����ɵ��쳣����
		String sql="delete from xg_excdatalsb where ycb=?";
		boolean b=runUpdate(sql, new String[]{table});
		if(!b){
			msg="���쳣�������ʧ��";
			return msg;
		}
		//1.���������ظ�
		String ycyy="���������ظ�";
		sql="insert into xg_excdatalsb(pk,ycyy,ycb) select bmdm,? ycyy,? ycb from xg_jcsj_bmdmb a " +
			"where exists (select 1 from (select bmmc from xg_jcsj_bmdmb group by bmmc having count(1)>1) x where x.bmmc=a.bmmc)";
		b=runUpdate(sql, new String[]{ycyy,table});
		if(!b){
			msg="���������ظ�����ʧ�ܣ�";
			return msg;
		}
		//2.�����������1��5�����Ż�ѧԺ��
		ycyy="�����������1��5�����Ż�ѧԺ��";
		sql="insert into xg_excdatalsb(pk,ycyy,ycb) select bmdm,? ycyy,? ycb from xg_jcsj_bmdmb a " +
		"where not exists (select 1 from xg_excdatalsb x where x.ycb=? and x.pk=a.bmdm) " +
		"and bmlb not in ('1','5')";
		b=runUpdate(sql, new String[]{ycyy,table,table});
		if(!b){
			msg="�����������1��5�����Ż�ѧԺ������ʧ�ܣ�";
			return msg;
		}
		//2.��������ѧԺ�����ò���û��רҵ
		ycyy="��������ѧԺ�����ò���û��רҵ";
		sql="insert into xg_excdatalsb(pk,ycyy,ycb) select distinct bmdm,? ycyy,? ycb from xg_jcsj_bmdmb a ,xg_jcsj_zydmb b " +
		"where not exists (select 1 from xg_excdatalsb x where x.ycb=? and x.pk=a.bmdm) and a.bmdm=b.ssbmdm and a.bmlb='5' ";
		b=runUpdate(sql, new String[]{ycyy,table,table});
		if(!b){
			msg="��������ѧԺ�����ò���û��רҵ����ʧ�ܣ�";
			return msg;
		}
		return msg;
	}

}
