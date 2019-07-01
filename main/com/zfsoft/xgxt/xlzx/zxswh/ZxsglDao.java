package com.zfsoft.xgxt.xlzx.zxswh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询管理模块
 * @类功能描述: (咨询师信息管理) 
 * @作者：whj[工号：1004]
 * @时间： 2013-8-14 下午02:41:22 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class ZxsglDao extends SuperDAOImpl<ZxsglForm> {
	
	/**
	 * 根据职工号查询咨询师信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getZxsInfoByZgh(String zgh){
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select a.* from xg_view_xlzx_zxsxx a where a.datazt='1' and a.zgh = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{zgh});
	}
	
	/**
	 * 根据职工号查询教师信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getFdyInfo(String zgh){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select b.zgh,b.xm,b.xb,b.csrq, trunc((to_char(sysdate, 'yyyymmdd') - (to_char(to_date(b.csrq,'yyyy-mm-dd'), 'yyyymmdd')))/10000) age, b.bmdm, b.bmmc,b.lxdh" );
		sql.append(" from view_fdyxx b" ); 
		sql.append(" where b.zgh = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{zgh});
	}

	
	/**
	 * 保存至咨询师信息表
	 * 
	 * @author wanghj
	 * @throws Exception
	 */
	public boolean saveZxsInfo(ZxsglForm model)
			throws Exception {
		boolean flag = false;
		if("11527".equals(Base.xxdm)){
			String[] input = {model.getZgh(),model.getLxdh(),
					model.getAddress(),model.getZxslv(),model.getZxszg(),model.getKjdrs(),
					model.getStatus(),model.getCreatebh(),
					model.getCreatemc(),
					model.getZxsjj(),model.getXq(),
					model.getSclydm()};
			String sql = " insert into xg_xlzx_zxsxxb (zgh,lxdh,address,zxslv,zxszg,kjdrs,status,createsj,createbh,createmc,zxsjj,xq,sclydm) values(?,?,?,?,?,?,?,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),?,?,?,?,?)";
			flag = dao.insert(sql, input);
		}else{
			String[] input = {model.getZgh(),model.getLxdh(),
					model.getAddress(),model.getZxslv(),model.getZxszg(),model.getKjdrs(),
					model.getStatus(),model.getCreatebh(),
					model.getCreatemc(),
					model.getZxsjj(),model.getXq()};
			String sql = " insert into xg_xlzx_zxsxxb (zgh,lxdh,address,zxslv,zxszg,kjdrs,status,createsj,createbh,createmc,zxsjj,xq) values(?,?,?,?,?,?,?,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),?,?,?,?)";
			flag = dao.insert(sql, input);
		}
		
		
		
		
		return flag;
	}
	
	
	/**
	 * 
	 * @描述:删除咨询师信息
	 * @作者：1004
	 * @日期：2013-8-13 上午10:39:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int delZxsByZgh(String[] zgh) throws Exception {
		if (zgh == null || zgh.length == 0){
			throw new NullPointerException();
		}
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_xlzx_zxsxxb");
		sql.append(" where  ");
		//sql.append("(");
		
		for (int i = 0 , n = zgh.length ; i < n ; i++){
			sql.append("zgh=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		
		//sql.append(")");
		return dao.runDelete(sql.toString(), zgh);
	}
	
	
	/**
	 * 
	 * @描述:根据职工号查询咨询师信息
	 * @作者：1004
	 * @日期：2013-8-13 上午10:39:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getZxsInfoByZgh(String[] zgh,String xq) throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from xg_view_xlzx_zxsxx");
		sql.append(" where ( ");
		for (int i = 0 , n = zgh.length ; i < n ; i++){
			sql.append("zgh=?");
			params.add(zgh[i]);
			if (i != n-1){
				sql.append(" or ");
			}
		}
		sql.append(" ) ");
		if(xq != "" && xq != null) {
			sql.append(" and xq = ? ");
			params.add(xq);
		}
		return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
	}

	/**
	 * 根据职工号,日期查询咨询师
	 */
	public List<HashMap<String, String>> getZxsListYyfk(String[] zghs, String zxrq){
		List<String> params = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select t.zgh,t1.lxdh,t.address,t1.xm,t1.xb,t1.bmmc,t.kjdrs,t.zxsjj,");
	    sql.append("(case when t.kjdrs is null then '无上限' else '上限'||t.kjdrs||'人' end) kjdrsms,");
	    sql.append("(select count(g.id) from XG_XLZX_XLZXB g where g.apzxs = t.zgh and g.zxrq = ?) yaprs ");
		sql.append(" ,'' yyrq " );
	    
	    // sql.append("from XG_XLZX_ZXSXXB t left join view_fdyxx t1 on t.zgh = t1.zgh where t.status='1' ) a where to_number(a.kjdrs) > to_number(a.yaprs) or a.kjdrs is null ");
		sql.append("from XG_XLZX_ZXSXXB t left join view_fdyxx t1 on t.zgh = t1.zgh where t.status='1' ) ");
		sql.append(" where  ");
		params.add(zxrq);
		for (int i = 0 , n = zghs.length ; i < n ; i++){
			sql.append("zgh=?");
			params.add(zghs[i]);
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @描述:条件查询咨询师信息
	 * @作者：1004
	 * @日期：2013-8-13 上午10:39:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getZxsList(String status) throws Exception {
		
		String[] outputValue = {"zgh","xm","xb","age","bmdm","bmmc","lxdh","address","zxszg","zxsjj"};
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from xg_view_xlzx_zxsxx");
		sql.append(" where  1=1 ");
		sql.append(" and datazt=1 and status=?  order by zgh asc ");
		return dao.getList(sql.toString(), new String[]{status}, outputValue);
	}
	/**
	 * 根据咨询师状态查询咨询师信息（当天是否有学生预约）
	 */
	public List<HashMap<String,String>> getZxsListXssq(String status, String pbdate) throws Exception {
		
		String[] outputValue = {"zgh","xm","xb","age","bmdm","bmmc","lxdh","address","zxszg","zxsjj","checkboxzt"};
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,case when (b.pbnum is null or b.pbnum = 0) then '' else 'disabled' end checkboxzt from ( ");
		sql.append("select * from xg_view_xlzx_zxsxx");
		sql.append(" where  1=1 ");
		sql.append(" and datazt=1 and status=? ");
		sql.append(" ) a left join ( ");
		sql.append(" select count(1) pbnum,zgh from XG_XLZX_YYSQB where yyzxrq=? and status in ('1','2') group by zgh ");
		sql.append(" ) b on a.zgh=b.zgh ");
		
		sql.append(" order by a.zgh asc ");
		return dao.getList(sql.toString(), new String[]{status, pbdate}, outputValue);
	}
	
	public boolean updateBatchZgStatus(String[] zgh,String status) throws Exception{
		
		StringBuffer _sql = new StringBuffer();
		_sql.append(" update xg_xlzx_zxsxxb set ");
		
		if(!StringUtil.isNull(status)){
			_sql.append(" status = '" +status+"' , ");
		}
		//什么也没修改，则return
		if(_sql.lastIndexOf(",")==-1){
			return false;
		}
		//去掉最后一个","
		String sqls = _sql.substring(0, _sql.lastIndexOf(","))+" where ";
		StringBuffer sql = new StringBuffer();
		sql.append(sqls);
		for (int i = 0 , n = zgh.length ; i < n ; i++){
			sql.append("zgh = ?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		boolean flag = dao.runUpdate(sql.toString(), zgh);
		return  flag;
		
	}
	public boolean updateZxsInfo(ZxsglForm model) throws Exception{
		
		HashMap<String,String>  zxsList = this.getZxsInfoByZgh(model.getZgh());
		if(model.getZgh()==null || model.getZgh().equals("") || zxsList==null || zxsList.size()==0){
			return false;	
		}
		boolean flag = false;
		StringBuffer sql = new StringBuffer();
		if("11527".equals(Base.xxdm)){
			String[] inputVlue = {model.getStatus(),model.getAddress(),model.getZxslv(),model.getZxszg(),model.getKjdrs(),model.getZxsjj(),model.getDatazt(),model.getXq(),model.getSclydm(), model.getZgh()};
			sql.append(" update xg_xlzx_zxsxxb set status = ?,address=?,zxslv=?,zxszg=?,kjdrs=?,zxsjj=?,datazt=?,xq=?,sclydm=? where zgh=?");
			flag = dao.runUpdate(sql.toString(), inputVlue);
		}else{
			String[] inputVlue = {model.getStatus(),model.getAddress(),model.getZxslv(),model.getZxszg(),model.getKjdrs(),model.getZxsjj(),model.getDatazt(),model.getXq(), model.getZgh()};
			sql.append(" update xg_xlzx_zxsxxb set status = ?,address=?,zxslv=?,zxszg=?,kjdrs=?,zxsjj=?,datazt=?,xq=? where zgh=?");
			flag = dao.runUpdate(sql.toString(), inputVlue);
		}
		
		return flag;
	}

	@Override
	protected void setTableInfo() {
		super.setKey("zgh");
		super.setTableName("xg_xlzx_zxsxxb");
	}

	/**
	 * 取得面页信息
	 */
	public List<HashMap<String, String>> getPageList(ZxsglForm model)
			throws Exception {
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t.*,rownum r from ( select a.* from xg_view_xlzx_zxsxx a where a.datazt='1' ) t where 1=1 ");
		// 在岗状态设定
		if(StringUtils.isNotNull(model.getStatus())){
			sql.append(" and t.status = '"+ model.getStatus() + "' ");
		}
		sql.append(searchTj);
		sql.append(" order by bmdm,zgh ");
		
		return getPageList(model, sql.toString(), inputV);
	}

	/**
	 * 取得面页信息
	 */
	@Override
	public List<HashMap<String, String>> getPageList(ZxsglForm model, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t.*,rownum r from ( select a.* from xg_view_xlzx_zxsxx a where a.datazt='1' ) t where 1=1 ");

		// 在岗状态设定
		if(StringUtils.isNotNull(model.getStatus())){
			sql.append(" and t.status = '"+ model.getStatus() + "' ");
		}
		//湖南城市学院过滤条件拼接
		if("11527".equals(Base.xxdm)){
			
			//生成高级查询相关条件、条件值 
			StringBuilder scly = new StringBuilder();
			//湖南城市学院高级查询的条件筛选（在擅长领域中实现多项选择筛选方式）
			SearchModel sm = model.getSearchModel();
			if(null != sm.getSearch_tj_scly()){
				String[] scVaule = sm.getSearch_tj_scly();
				scly.append("and 1=1 and (");
				for(int i = 0;i<scVaule.length;i++){
					scly.append("sclydm like '%"+scVaule[i]+"%'");
						if(i != scVaule.length - 1){
							scly.append(" or ");
						}
				}
				scly.append(" ) ");
				sm.setSearch_tj_scly(null);
				
			}
			sql.append(scly);
		}
		sql.append(searchTj);
		sql.append(" order by bmdm,zgh ");
		
		return getPageList(model, sql.toString(), inputV);
	}	
	
	/**
	 * 查出所有咨询师
	 */
	public List<HashMap<String, String>> getZxsxxAllList(){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select t.zgh,t.lxdh,t.address,t1.xm,t1.xb,t1.bmmc,t.kjdrs,t.zxsjj,");
	    sql.append("(case when t.kjdrs is null then '安排无上限' else '安排上限'||t.kjdrs||'人' end) kjdrsms,");
	    sql.append("(select count(g.zxid) from XG_XLZX_XLZXB g where g.zxs = t.zgh and g.zzaprq = to_char(sysdate, 'yyyy-mm-dd')) yaprs ");
		sql.append(" ,'' yyrq " );
	    
	    // sql.append("from XG_XLZX_ZXSXXB t left join view_fdyxx t1 on t.zgh = t1.zgh where t.status='1' ) a where to_number(a.kjdrs) > to_number(a.yaprs) or a.kjdrs is null ");
		sql.append("from XG_XLZX_ZXSXXB t left join view_fdyxx t1 on t.zgh = t1.zgh where t.status='1' ) ");
			
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	/**
	 * 查出所有咨询师  (根据预约时间)
	 */
	public List<HashMap<String, String>> getZxsxxAllByYysjList(String yysj){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select t.zgh,t.lxdh,t.address,t1.xm,t1.xb,t1.bmmc,t.kjdrs,");
		sql.append("(case when t.kjdrs is null then '安排无上限' else '安排上限'||t.kjdrs||'人' end) kjdrsms,");
		sql.append(" (select count(zxid) ");
		sql.append("  from ((select zxid, zzaprq,zxs from XG_XLZX_XLZXB where sqid is null) ");
		sql.append("         union (select a.zxid, a.zzaprq,a.zxs ");
		sql.append("                   from XG_XLZX_XLZXB a ");
		sql.append("                  inner join XG_XLZX_YYSQB b ");
		sql.append("                    on b.sqid = a.sqid	 ");
		sql.append("                 where b.yyzt = '2')) a ");
		sql.append("   where a.zzaprq = '" + yysj + "' and a.zxs = t.zgh ");
		sql.append("     ) yaprs ");
		sql.append(" ,'" +yysj + "' yyrq " );
		//sql.append("(select count(g.zxid) from XG_XLZX_XLZXB g where g.zxs = t.zgh and g.zzaprq = '"+yysj+"') yaprs ");
		//sql.append("from XG_XLZX_ZXSXXB t left join view_fdyxx t1 on t.zgh = t1.zgh where t.status='1' ) a where to_number(a.kjdrs) > to_number(a.yaprs) or a.kjdrs is null ");
		sql.append("from XG_XLZX_ZXSXXB t left join view_fdyxx t1 on t.zgh = t1.zgh where t.status='1' ) a ");		
		List<HashMap<String, String>> list=dao.getListNotOut(sql.toString(), new String[]{});
		return list;
	}
	
	/**
	 * 
	 * @描述: 校区列表
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-9-6 下午02:34:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXq() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select dm,xqmc from dm_zju_xq order by dm asc");
		return dao.getListNotOut(sql.toString(), new String[] {});
	}
	
	/**
	 * 
	 * @描述: 时间段预约咨询师列表查询
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-27 下午04:04:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @param xq
	 * @param pbdate
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getZxsInfoByZghForsjdYy(String[] zgh,String xq,String pbdate) throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.zgh,");
		sql.append(" b.xm,");
		sql.append(" b.xb,");
		sql.append(" b.csrq,");
		sql.append(" trunc((to_char(sysdate, 'yyyymmdd') -");
		sql.append(" (to_char(to_date(b.csrq,'yyyy-mm-dd'), 'yyyymmdd'))) / 10000) age, ");
		sql.append(" b.bmdm,");
		sql.append(" b.bmmc,");
		sql.append(" b.lxdh fdydh,");
		sql.append(" b.lxdh,");
		sql.append(" a.address,");
		sql.append(" nvl(x.kjdrs,0) kjdrs,");
		sql.append(" a.status,");
		sql.append(" (case a.status");
		sql.append(" when '1' then");
		sql.append("  '在岗'");
		sql.append(" when '2' then");
		sql.append(" '不在岗'");
		sql.append(" else");
		sql.append(" a.status");
		sql.append(" end) statusmc,");
		sql.append(" a.createsj,");
		sql.append(" a.zxsjj,");
		sql.append(" a.zxszg,");
		sql.append(" x.xqdm xq,");
		sql.append(" c.xqmc,");
		sql.append(" a.datazt,");
		sql.append(" case");
		sql.append(" when length(a.zxszg) > 10 then");
		sql.append(" substr(a.zxszg, 0, 10) || '...'");
		sql.append(" else");
		sql.append(" a.zxszg");
		sql.append(" end zxszg_info");
		sql.append(" from xg_xlzx_zxsxxb a");
		sql.append(" left join view_fdyxx b");
		sql.append(" on a.zgh = b.zgh");
		sql.append(" left join(");
		sql.append(" select distinct  t.pbid,t.zxs zgh,t.xqdm,count(1)kjdrs from xg_xlzx_zxspbsjb t");
		sql.append(" left join xg_xlzx_zxspbb t1");
		sql.append(" on t.pbid = t1.id");
		sql.append(" where t1.pbdate = ?");
		params.add(pbdate);
		sql.append(" group by t.pbid,t.zxs,t.xqdm ) x");
		sql.append(" on a.zgh = x.zgh");
		sql.append(" left join dm_zju_xq c");
		sql.append(" on x.xqdm = c.dm");
		sql.append(" where ( ");
		for (int i = 0 , n = zgh.length ; i < n ; i++){
			sql.append("a.zgh=?");
			params.add(zgh[i]);
			if (i != n-1){
				sql.append(" or ");
			}
		}
		sql.append(") ");
		if(xq != "" && xq != null) {
			sql.append(" and x.xqdm = ? ");
			params.add(xq);
		}
		return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @描述: 时间段预约反馈查询反馈可预约学生
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-30 上午10:12:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zghs
	 * @param zxrq
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZxsListYyfkForSjd(String[] zghs, String zxrq){
		List<String> params = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.zgh,");
		sql.append(" t1.lxdh,");
		sql.append(" t.address,");
		sql.append(" t1.xm,");
		sql.append(" t1.xb,");
		sql.append(" t1.bmmc,");
		sql.append(" nvl(z.kjdrs,'0')kjdrs,");
		sql.append(" t.zxsjj,");
		sql.append(" (case");
		sql.append(" when z.kjdrs is null then");
		sql.append(" '0人'");
		sql.append(" else");
		sql.append(" '上限' || z.kjdrs || '人'");
		sql.append(" end) kjdrsms,");
		sql.append(" (select count(g.id)");
		sql.append(" from (");
		sql.append(" select id,decode(nvl(u.ZXRQ,'0'),'0',u.YYZXRQ,u.ZXRQ) zxrq,decode(nvl(u.ZXRQ,'0'),'0',u.ZGH,u.APZXS)  apzxs");
		sql.append(" from view_new_dc_xlzx_yysq u where status in ('1','2')");
		sql.append(" ) g");
		sql.append(" where g.apzxs = t.zgh");
		sql.append(" and g.zxrq = ?) yaprs,");
		params.add(zxrq);
		sql.append(" '' yyrq,");
		sql.append("  f.xqmc");
		sql.append(" from XG_XLZX_ZXSXXB t");
		sql.append(" left join view_fdyxx t1");
		sql.append(" on t.zgh = t1.zgh");
		sql.append(" left join (select zxs zgh,count(1)kjdrs,x.xqdm from xg_xlzx_zxspbsjb x where exists (select 1 from xg_xlzx_zxspbb y where x.pbid = y.id and y.pbdate = ?) group by zxs,xqdm)z");
		params.add(zxrq);
		sql.append(" on t.zgh = z.zgh");
		sql.append(" left join dm_zju_xq f");
		sql.append(" on z.xqdm = f.dm");
		sql.append(" where t.status = '1'");
		if(zghs != null && zghs.length >0){
			sql.append(" and t.zgh in (");
			for (int i = 0; i < zghs.length; i++) {
				sql.append("?");
				params.add(zghs[i]);
				if(i != zghs.length-1){
					sql.append(",");
				}
			}
			sql.append(")");
			
		}
		sql.append(" ");
		return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-3-5 上午09:40:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getSclyList() {
		String sql = "select sclydm,sclymc from tsxs_sclyb";
		return dao.getListNotOut(sql, new String[]{});
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018-3-5 上午10:07:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sclydm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getSclyList(String[] sclydms) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select sclydm,sclymc from tsxs_sclyb where ");
		
		for (int i = 0 , n = sclydms.length ; i < n ; i++){
			sql.append("  sclydm =? ");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		return dao.getListNotOut(sql.toString(), sclydms);
	}

}
