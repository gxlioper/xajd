package xsgzgl.jcsj.xsxxwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.utils.CommonQueryDAO;

public class XsxxwhDao extends DAO{
	
	
	/**
	 * ��ȡѧ����Ϣ�б�
	 * @param model
	 * @return
	 */
	public List<String[]> getXsxxList(XsxxwhForm model) throws Exception{
		// �߼���ѯ����
//		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// �߼���ѯ����ֵ
//		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		//Ȩ�޿���
//		SearchService searchService=new SearchService();
//		String searchTjQx = searchService.getSearchTjByUser(request, "","bmdm", null); 	//ѧԺ�û�
		
		String searchTj="";
		List<String> inputV=new ArrayList<String>();
		if(!Base.isNull(model.getNj())){
			searchTj+=" and nj=? ";
			inputV.add(model.getNj());
		}
		if(!Base.isNull(model.getBmdm())){
			searchTj+=" and bmdm=? ";
			inputV.add(model.getBmdm());
		}
		if(!Base.isNull(model.getZydm())){
			searchTj+=" and zydm=? ";
			inputV.add(model.getZydm());
		}
		if(!Base.isNull(model.getBjdm())){
			searchTj+=" and bjdm=? ";
			inputV.add(model.getBjdm());
		}
		if(!Base.isNull(model.getQuery_text())){
			searchTj+=" and (xh like '%'||?||'%' or xm like '%'||?||'%' or sfzh like '%'||?||'%')";
			inputV.add(model.getQuery_text());
			inputV.add(model.getQuery_text());
			inputV.add(model.getQuery_text());
		}
		if("��".equals(model.getQuery_sfycsj())){
			searchTj+=" and ycyy is not null ";
		}else if("��".equals(model.getQuery_sfycsj())){
			searchTj+=" and ycyy is null ";
		}
		
		String sql = "select rownum r, a.* from (" +
				"select a.xh,a.xm,a.xb,a.nj,d.bmmc,c.zymc,b.bjmc,e.ycyy," +
				"a.sfzh,b.bjdm,c.zydm,d.bmdm from xsxxb a left join xg_jcsj_bjdmb b " +
				"on a.bjdm=b.bjdm left join xg_jcsj_zydmb c on b.sszydm=c.zydm left join xg_jcsj_bmdmb d on c.ssbmdm=d.bmdm "+ 
				" left join xg_excdatalsb e on a.xh=e.pk and e.ycb='xsxxb' order by ycyy) a where 1 = 1";

		String[] output = new String[] { "xh","xh", "xm", "xb","nj","bmmc","zymc","bjmc","ycyy"};
		return CommonQueryDAO.commonQuery(sql, searchTj, inputV.toArray(new String[]{}),
				output, model);
	}
	
	/**
	 * ɾ��ѧ����Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean deleteXsxxInfo(XsxxwhForm model) throws Exception{
		String sql="delete from xsxxb where xh=?";
		boolean b=this.runUpdate(sql, new String[]{model.getXh()});
		return b;
	}
	
	/**
	 * ����쳣����
	 * @return
	 * @throws Exception 
	 */
	public String checkExceptionData() throws Exception{
		String msg="�����ɣ�";
		String table="xsxxb";
		//����ɵ��쳣����
		String sql="delete from xg_excdatalsb where ycb=?";
		boolean b=runUpdate(sql, new String[]{table});
		if(!b){
			msg="���쳣�������ʧ��";
			return msg;
		}
		//1.����༶�����Ƿ���ڰ༶�������
		String ycyy="�༶���벻���ڰ༶�������";
		sql="insert into xg_excdatalsb(pk,ycyy,ycb) select xh,? ycyy,? ycb from xsxxb a " +
			"where not exists (select 1 from xg_jcsj_bjdmb x where x.bjdm=a.bjdm)";
		b=runUpdate(sql, new String[]{ycyy,table});
		if(!b){
			msg="�༶���벻���ڰ༶������в���ʧ�ܣ�";
			return msg;
		}
		//2.������Ϊ��
		ycyy="����������Ϊ��";
		sql="insert into xg_excdatalsb(pk,ycyy,ycb) select xh,? ycyy,? ycb from xsxxb a " +
			"where not exists (select 1 from xg_excdatalsb x where x.ycb=? and x.pk=a.xh) " +
			"and a.xm is null";
		b=runUpdate(sql, new String[]{ycyy,table,table});
		if(!b){
			msg="����������Ϊ�ղ���ʧ�ܣ�";
			return msg;
		}
		//3.����ѧ���Ա�
		ycyy="�Ա���(�С�Ů)��(1��2)";
		sql="insert into xg_excdatalsb(pk,ycyy,ycb) select xh,? ycyy,? ycb from xsxxb a " +
			"where not exists (select 1 from xg_excdatalsb x where x.ycb=? and x.pk=a.xh) " +
			"and nvl(a.xb,' ') not in('1','2','��','Ů') ";
		b=runUpdate(sql, new String[]{ycyy,table,table});
		if(!b){
			msg="�Ա���(�С�Ů)��(1��2)!";
			return msg;
		}
		return msg;
	}
	
	/**
	 * ��ȡ�����б�
	 * @return
	 */
	public List<HashMap<String,String>> getBmdmList(){
		String sql="select bmdm,bmmc from xg_jcsj_bmdmb where bmlb='5' order by bmmc";
		return this.getList(sql, new String[]{}, new String[]{"bmdm","bmmc"});
	}
	
	/**
	 * ��ȡרҵ�б�
	 * @return
	 */
	public List<HashMap<String,String>> getZydmList(String bmdm){
		String sql="select zydm dm,zymc mc from xg_jcsj_zydmb where ssbmdm=? order by zymc";
		return this.getList(sql, new String[]{bmdm}, new String[]{"dm","mc"});
	}
	
	/**
	 * ��ȡ�༶�б�
	 * @return
	 */
	public List<HashMap<String,String>> getBjdmList(String zydm){
		String sql="select bjdm dm,bjmc mc from xg_jcsj_bjdmb where sszydm=? order by bjmc";
		return this.getList(sql, new String[]{zydm}, new String[]{"dm","mc"});
	}

}
