package xgxt.gygl.gyfzr.gyfdy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.utils.CommonQueryDAO;

public class GyglGyfdyDAO {
	
	public List<String[]>getGyfdy(GyglGyfdyForm myForm) throws Exception{
		
		StringBuilder sql=new StringBuilder();
		
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());	
		
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());	
		
		String[]colList={"yhm","xm","bmmc","lds"};
		sql.append(" select rownum r,b.* from( select distinct(yhm)yhm,xm from( ")
		.append("  select * from(select b.yhm, b.xm, b.xb, c.lddm, c.xqdm, c.yqdm,b.bmdm,b.bmmc  ")
		.append(" from xg_gygl_ldglb a left join (select a.yhm,b.xb ,c.bmmc,c.bmdm , ")
		.append("   case when a.xm is null then  b.xm  else a.xm  end xm from yhb a    ")
		.append(" left join fdyxxb b on a.yhm = b.zgh left join zxbz_xxbmdm c on a.szbm=c.bmdm   ")
		.append(" ) b on a.yhm =b.yhm left join sslddmb c on a.lddm = c.lddm )where 1=1  ")
		.append(searchTj)
		.append(")) a left join  ")
		.append(" (select a.yhm,a.xm,a.xb ,count(1)lds,a.bmdm,a.bmmc  ")
		.append("  from (select * from(select b.yhm, b.xm, b.xb, c.lddm, c.xqdm, c.yqdm,b.bmdm,b.bmmc  ")
		.append("  from xg_gygl_ldglb a left join (select a.yhm,b.xb ,c.bmmc,c.bmdm ,   ")
		.append("  case when a.xm is null then  b.xm  else a.xm  end xm from yhb a   ")
		.append("  left join fdyxxb b on a.yhm = b.zgh left join zxbz_xxbmdm c on a.szbm=c.bmdm    ")
		.append("  ) b on a.yhm =b.yhm left join sslddmb c on a.lddm = c.lddm )where 1=1    ")
		.append("  )a group by yhm, xm, xb,bmdm,bmmc )b on a.yhm=b.yhm    ");
		return CommonQueryDAO.commonQuery(sql.toString(), "",inputV , colList, myForm);
		
	}
	
	/**
	 * 获取公寓辅导员管辖楼栋
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getGyfdyGxld(String yhm){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append(" select lddm,ldmc from sslddmb a ");
		sql.append(" where exists (select 1  from xg_gygl_ldglb b ");
		sql.append(" where a.lddm = b.lddm and b.yhm = ? ) ");
		return dao.getList(sql.toString(), new String[]{yhm}, new String[]{"ldmc"});
	}
	
	/**
	 * 获取辅导员信息
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getGyfdyxx(GyglGyfdyForm myForm) throws Exception{
		StringBuilder sql=new StringBuilder();
		
		String[]colList={"yhm","xm","xb","zwmc","bmmc"};
		List<String>inputV=new ArrayList<String>();
		sql.append(" select rownum r,a.* from (select a.yhm,case when b.xm is not null then b.xm else a.xm end xm,  ")
		.append("  case when b.xb is not null then (case (b.xb) ")
		.append(" when '1' then  '男' when '2' then '女'  end)  else '不详' end xb, ")
		.append(" case when b.zwmc is not null then b.zwmc else '不详' end zwmc, ")
		.append(" case when b.zwdm is not null then b.zwdm else '不详' end zwdm, ")
		.append(" case when a.szbm is not null then a.szbm else '不详' end szbm, ")
		.append(" case when c.bmmc is not null then c.bmmc else '不详' end bmmc,c.bmdm ")
		.append(" from yhb a left join (select a.zgh,a.xb,a.xm,b.zwdm,zwmc from fdyxxb a,zwb b where a.zw=b.zwdm ) ")
		.append(" b on a.yhm=b.zgh left join zxbz_xxbmdm c on a.szbm=c.bmdm )a where 1=1 ");
		
		StringBuilder query=new StringBuilder();
		if(!Base.isNull(myForm.getYhm())){
			query.append(" and yhm like ?  ");
			inputV.add("%"+myForm.getYhm()+"%");
		}
		if(!Base.isNull(myForm.getXm())){
			query.append(" and xm like ?  ");
			inputV.add("%"+myForm.getXm()+"%");
		}
		if(!Base.isNull(myForm.getXb())){
			query.append(" and xb=? ");
			inputV.add(myForm.getXb());
		}
		if(!Base.isNull(myForm.getZwdm())){
			query.append(" and zwdm=? ");
			inputV.add(myForm.getZwdm());
		}
		if(!Base.isNull(myForm.getBmdm())){
			query.append(" and bmdm=? ");
			inputV.add(myForm.getBmdm());
		}
		sql.append(query);
		return CommonQueryDAO.commonQuery(sql.toString(), "",inputV.toArray(new String[]{}) , colList, myForm);
	}
	
	/**
	 * 获取职务列表
	 * @param myForm
	 * @return  List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getZwList(GyglGyfdyForm myForm){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append(" select zwdm dm,zwmc mc from zwb ");
		return dao.getList(sql.toString(), new String[]{}, new String[]{"dm","mc"});
	}
	
	/**
	 * 获取部门列表
	 * @param myForm
	 * @return  List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getBmdmList(GyglGyfdyForm myForm){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append(" select bmdm dm,bmmc mc from zxbz_xxbmdm ");
		return dao.getList(sql.toString(), new String[]{}, new String[]{"dm","mc"});
	}
	
	/**
	 * 获取辅导员信息
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,String>getGyfdyMap(GyglGyfdyForm myForm) throws Exception{
		StringBuilder sql=new StringBuilder();
		
		DAO dao=DAO.getInstance();
		String[]colList={"yhm","xm","xb","zwmc","bmmc"};
		List<String>inputV=new ArrayList<String>();
		sql.append(" select  a.* from (select a.yhm,case when b.xm is not null then b.xm else a.xm end xm,  ")
		.append("  case when b.xb is not null then (case (b.xb) ")
		.append(" when '1' then  '男' when '2' then '女'  end)  else '不详' end xb, ")
		.append(" case when b.zwmc is not null then b.zwmc else '不详' end zwmc, ")
		.append(" case when b.zwdm is not null then b.zwdm else '不详' end zwdm, ")
		.append(" case when a.szbm is not null then a.szbm else '不详' end szbm, ")
		.append(" case when c.bmmc is not null then c.bmmc else '不详' end bmmc,c.bmdm ")
		.append(" from yhb a left join (select a.zgh,a.xb,a.xm,b.zwdm,zwmc from fdyxxb a,zwb b where a.zw=b.zwdm ) ")
		.append(" b on a.yhm=b.zgh left join zxbz_xxbmdm c on a.szbm=c.bmdm )a where 1=1 ");
		
		StringBuilder query=new StringBuilder();
		if(!Base.isNull(myForm.getYhm())){
			query.append(" and yhm = ? ");
			inputV.add(myForm.getYhm());
		}
		if(!Base.isNull(myForm.getXm())){
			query.append(" and xm = ? ");
			inputV.add(myForm.getXm());
		}
		if(!Base.isNull(myForm.getXb()) && !"不限制".equalsIgnoreCase(myForm.getXb())){
			query.append(" and xb=? ");
			inputV.add(myForm.getXb());
		}
		if(!Base.isNull(myForm.getZwdm())){
			query.append(" and zwdm=? ");
			inputV.add(myForm.getZwdm());
		}
		if(!Base.isNull(myForm.getBmdm())){
			query.append(" and bmdm=? ");
			inputV.add(myForm.getBmdm());
		}
		sql.append(query);
		System.out.println(sql);
		return dao.getMap(sql.toString(), inputV.toArray(new String[]{}), colList);
	}
	
	/**
	 * 获取校区列表
	 * @return
	 */
	public List<HashMap<String,String>>getXqxx(){

		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append(" select dm xqdm,xqmc from dm_zju_xq ");
		return dao.getList(sql.toString(), new String[]{}, new String[]{"xqdm","xqmc"});
	}
	
	/**
	 * 获取园区列表
	 * @return
	 */
	public List<HashMap<String, String>> getYqxx(GyglGyfdyForm myForm) {

		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		List<String>inputV=new ArrayList<String>();
		sql.append("  select '' yqdm,'---请选择---'yqmc from dual union  ");
		sql.append("  select yqdm,yqmc from yqdmb where 1=1  ");
		if(!Base.isNull(myForm.getXqdm())){
			sql.append(" and xqdm=? ");
			inputV.add(myForm.getXqdm());
		}
		sql.append(" order by yqdm nulls first ");
		return dao.getList(sql.toString(),inputV.toArray(new String[]{}), new String[] {
				"yqdm", "yqmc" });
	}
	
	public List<HashMap<String,String>>getYfpldList(String yhm){

		DAO dao = DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append("  select lddm,ldmc from sslddmb a where   ");
		sql.append(" exists (select 1 from xg_gygl_ldglb b where b.yhm=? and a.lddm=b.lddm) ");
		return dao.getList(sql.toString(),new String[]{yhm}, new String[] {
			"lddm", "ldmc" });
	}
	
	public List<HashMap<String,String>>getWfpldList(String[] cxjg){

		DAO dao = DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		StringBuilder query=new StringBuilder();
		List<String> inputV=new ArrayList<String>();
		
		String yhm=cxjg[0];
		inputV.add(yhm);
		
		String xq=cxjg[1];
		String yq=cxjg[2];
		String xb=cxjg[3];
		String ldmc=cxjg[4];
		
		sql.append("  select lddm,ldmc from sslddmb a where  not exists ");
		
		sql.append(" (select 1 from xg_gygl_ldglb b where b.yhm=? and a.lddm=b.lddm) ");
		
		
		if(!Base.isNull(xq)){
			query.append(" and xqdm=? ");
			inputV.add(xq);
		}
		if(!Base.isNull(yq)){
			query.append(" and yqdm=? ");
			inputV.add(yq);
		}
		if(!Base.isNull(xb) && !"不限制".equalsIgnoreCase(xb)){
			query.append(" and xbxd=? ");
			inputV.add(xb);
		}
		if(!Base.isNull(ldmc)){
			query.append(" and ldmc like ? ");
			inputV.add("%"+ldmc+"%");
		}
		
		sql.append(query);
		return dao.getList(sql.toString(),inputV.toArray(new String[]{}), new String[] {
			"lddm", "ldmc" });
	}
}
