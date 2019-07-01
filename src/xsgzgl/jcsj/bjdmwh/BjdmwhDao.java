package xsgzgl.jcsj.bjdmwh;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.jcsj.bmdmwh.BmdmwhDao;
import xsgzgl.jcsj.zydmwh.ZydmwhDao;

public class BjdmwhDao extends DAO{
	
	/**
	 * ��ȡ�༶��Ϣ���
	 * @param model
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<String[]> getBjdmList(BjdmwhForm model) throws Exception{
		// �߼���ѯ����
//		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// �߼���ѯ����ֵ
//		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		//Ȩ�޿���
//		SearchService searchService=new SearchService();
//		String searchTjQx = searchService.getSearchTjByUser(request, "","bjdm", null); 	//ѧԺ�û�
		
		String searchTj="";
		List<String> inputV=new ArrayList<String>();
		if(!Base.isNull(model.getQuery_nj())){
			searchTj+=" and nj=? ";
			inputV.add(model.getQuery_nj());
		}
		if(!Base.isNull(model.getQuery_ssbmdm())){
			searchTj+=" and ssbmdm=? ";
			inputV.add(model.getQuery_ssbmdm());
		}
		if(!Base.isNull(model.getQuery_sszydm())){
			searchTj+=" and sszydm=? ";
			inputV.add(model.getQuery_sszydm());
		}
		if(!Base.isNull(model.getQuery_text())){
			searchTj+=" and (bjdm like '%'||?||'%' or bjmc like '%'||?||'%')";
			inputV.add(model.getQuery_text());
			inputV.add(model.getQuery_text());
		}
		if("��".equals(model.getQuery_sfycsj())){
			searchTj+=" and ycyy is not null ";
		}else if("��".equals(model.getQuery_sfycsj())){
			searchTj+=" and ycyy is null ";
		}
		
		String sql = "select rownum r, a.* from (" +
				"select a.*,b.zymc,b.ssbmdm,c.bmmc,d.ycyy from xg_jcsj_bjdmb a " +
				"left join xg_jcsj_zydmb b on a.sszydm=b.zydm left join xg_jcsj_bmdmb c on c.bmdm=b.ssbmdm " +
				"left join xg_excdatalsb d on d.pk=a.bjdm and d.ycb='xg_jcsj_bjdmb' "+
				" order by ycyy,bjdm) a where 1 = 1";

		String[] output = new String[] { "bjdm","sszydm","ssbmdm","bjdm", "bjmc", "zymc","bmmc","nj","ycyy"};
		return CommonQueryDAO.commonQuery(sql, searchTj, inputV.toArray(new String[]{}),
				output, model);
		
		
	}
	
	/**
	 * ����༶��Ϣ
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	public String saveBjdmInfo(BjdmwhForm model,String type) throws Exception{
		String msg="��������";
		boolean b = false;
		String sql;
		if("add".equals(type)){
			sql="select count(1) num from xg_jcsj_bjdmb where bjdm=?";
			String num=this.getOneRs(sql, new String[]{model.getBjdm()}, "num");
			if("0".equals(num)){
				sql="insert into xg_jcsj_bjdmb(bjdm,bjmc,sszydm,nj) values(?,?,?,?)";
				b=this.runUpdate(sql,new String[]{model.getBjdm(),model.getBjmc(),model.getSszydm(),model.getNj()});
				msg=(b?"�����ɹ���":"����ʧ�ܣ�");
			}else{
				msg="�ð༶�����Ѵ��ڣ�";
			}
		}else if("update".equals(type)){
			sql="update xg_jcsj_bjdmb set bjmc=?,sszydm=?,nj=? where bjdm=?";
			b=this.runUpdate(sql,new String[]{model.getBjmc(),model.getSszydm(),model.getNj(),model.getBjdm()});
			msg=(b?"�����ɹ���":"����ʧ�ܣ�");
		}
		return msg;
	}
	
	/**
	 * ɾ���༶��Ϣ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean deleteBjdmInfo(BjdmwhForm model) throws Exception{
		String sql="delete from xg_jcsj_bjdmb where bjdm=?";
		boolean b=this.runUpdate(sql, new String[]{model.getBjdm()});
		return b;
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
	 * ����쳣����
	 * @return
	 * @throws Exception 
	 */
	public String checkExceptionData() throws Exception{
		String msg="�����ɣ�";
		String table="xg_jcsj_bjdmb";
		//����ɵ��쳣����
		String sql="delete from xg_excdatalsb where ycb=?";
		boolean b=runUpdate(sql, new String[]{table});
		if(!b){
			msg="���쳣�������ʧ��";
			return msg;
		}
		//1.רҵ����δȫ��ͨ��������²��ý��а༶������
		String msg_temp=new ZydmwhDao().checkExceptionData();
		if(!msg.equals(msg_temp)){//������Ŵ�����ʧ��
			return "רҵ����δȫ��ͨ��������²��ý��а༶�����⣡רҵ������ʧ�ܣ�"+msg_temp;
		}else{
			sql="select count(1) num from xg_excdatalsb where ycb=?";
			String num=getOneRs(sql, new String[]{"xg_jcsj_zydmb"}, "num");
			if(!"0".equals(num)){
				return "רҵ����δȫ��ͨ��������²��ý��а༶�����⣡רҵ������"+num+"���쳣���ݻ�δ����";
			}
		}
		//2.�༶������רҵ������רҵ������д���
		String ycyy="�༶������רҵ��רҵ������в�����";
		sql="insert into xg_excdatalsb(pk,ycyy,ycb) select bjdm,? ycyy,? ycb from xg_jcsj_bjdmb a " +
				"where exists (select 1 from xg_jcsj_zydmb x where x.zydm=a.sszydm)";
		b=runUpdate(sql, new String[]{ycyy,table});
		if(!b){
			msg="�༶������רҵ��רҵ������в����ڲ���ʧ�ܣ�";
			return msg;
		}
		//3.ͬ�꼶ͬרҵ�µİ༶���Ʋ����ظ�
		ycyy="ͬ�꼶ͬרҵ�µİ༶���Ʋ����ظ�";
		sql="insert into xg_excdatalsb(pk,ycyy,ycb) select bjdm,? ycyy,? ycb from xg_jcsj_bjdmb a " +
		"where not exists (select 1 from xg_excdatalsb x where x.ycb=? and x.pk=a.bjdm) " +
		"and exists (select 1 from (select nj,sszydm,bjmc from xg_jcsj_zydmb group by nj,sszydm,bjmc having count(1)>1) x where x.bjmc=a.bjmc)";
		b=runUpdate(sql, new String[]{ycyy,table,table});
		if(!b){
			msg="ͬ�꼶ͬרҵ�µİ༶���Ʋ����ظ�����ʧ�ܣ�";
			return msg;
		}
		return msg;
	}
}
