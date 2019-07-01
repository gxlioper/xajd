package xsgzgl.pjpy.general.wdpj.xmsh;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.pjlc.xmsh.PjpyXmshForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.GetTime;
import xsgzgl.pjpy.general.PjpyGeneralForm;

public class WdpjXmshDAO {
	
	// ===========================评奖项目审核 结果集 begin =============================
	/**
	 * 获得需本用户审核项目
	 * 
	 * @author qlj
	 */
	public List<HashMap<String,String>> getCshXmList(WdpjXmshModel model,User user) {

		PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		
		String userName=user.getUserName();
	
		StringBuilder sql = new StringBuilder();

		sql.append(" select a.xmdm, a.xmmc ");
		sql.append(" from xg_pjpy_pjxmwhb a, xg_xtwh_spbz b, xg_xtwh_spgwyh c ");
		sql.append(" where a.lcid = b.splc ");
		sql.append(" and b.spgw = c.spgw ");
		sql.append(" and c.spyh = ? and a.pjxn=? and a.pjxq=? and a.pjnd=? ");
		sql.append(" group by a.xmdm,a.xmmc  ");
		
		DAO dao = DAO.getInstance();

		return dao.getList(sql.toString(), new String[]{userName,pjxn,pjxq,pjnd}, new String[]{"xmdm","xmmc"});
	}
	
	/**
	 * 获得需本用户审核项目
	 * (考虑审核开关及时间控制)
	 * @author qlj
	 */
	public List<HashMap<String,String>> getShxmList(WdpjXmshModel model,User user) {

		PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		
		String userName=user.getUserName();
	
		StringBuilder sql = new StringBuilder();

		sql.append(" select a.xmdm,");
		sql.append(" case when length(a.xmmc)>7 then substr(a.xmmc,0,7)||'...' else a.xmmc end xmmc,xmmc xmmcxx, ");
		sql.append(" case when b.xmdm is null then 'no' else 'yes' end shkz from ( ");
		sql.append(" select a.xmdm, a.xmmc ");
		sql.append(" from xg_pjpy_pjxmwhb a, xg_xtwh_spbz b, xg_xtwh_spgwyh c ");
		sql.append(" where a.lcid = b.splc ");
		sql.append(" and b.spgw = c.spgw ");
		sql.append(" and c.spyh = ? and a.pjxn=? and a.pjxq=? and a.pjnd=?  ");
		sql.append(" group by a.xmdm,a.xmmc)a left join(select * from xg_pjpy_sjszb  ");
		sql.append(" where (sqkssj is not null and sqkssj<=to_char(sysdate,'yyyyMMdd') ");
		sql.append(" or sqkssj is null) ");
		sql.append(" and (sqjssj is not null and sqjssj>=to_char(sysdate,'yyyyMMdd')  ");
		sql.append(" or sqjssj is null) ");
		sql.append(" and shkzkg='yes' and pjxn=? and pjxq=? and pjnd=? ");
		sql.append(" )b on a.xmdm=b.xmdm ");
		DAO dao = DAO.getInstance();

		return dao.getList(sql.toString(), new String[]{userName,pjxn,pjxq,pjnd,pjxn,pjxq,pjnd}, new String[]{"xmdm","xmmc","xmmcxx","shkz"});
	}
	
	/**
	 * 获取本级岗位审核信息
	 * @author qlj
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getWdpjXmshList(PjpyGeneralForm model,WdpjXmshModel xmshModel, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
		
		SearchModel searchModel = model.getSearchModel();
		
		DAO dao=DAO.getInstance();
		
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		
		String xmdm= xmshModel.getXmdm();
		
		String spgw = xmshModel.getSpgw();
		
		HashMap<String,String> nextSpgw =getNextSpMap(xmshModel, user);
		
		HashMap<String,String> higherUpSpgw =getHigherUpSpMap(xmshModel, user);
		//	 ====================过滤条件===================================
		
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(searchModel);

		String query = " where 1 = 1 ";
		query += searchTj;
		query += searchTjByUser;
		// ====================过滤条件 end================================
		
		// ====================SQL拼装================================
		StringBuilder tableSql = new StringBuilder();

		tableSql.append(" select a.*,rownum r from( "); 
		tableSql.append(" select a.*,case when b.xh is not null then "); 
		tableSql.append(" 'disabled' else '' end sfsh from(  "); 
		tableSql.append(" select a.xh pkValue, a.xmdm,a.pjxn,a.pjxq, "); 
		tableSql.append(" a.pjnd,a.xh,a.sqsj,c.xydm, "); 
		tableSql.append(" case when b.shzt='wsh' then '未审核' ");
		tableSql.append("  when b.shzt='tg' then '通过' ");
		tableSql.append("  when b.shzt='btg' then '不通过' ");
		tableSql.append("  when b.shzt='xcs' then '需重审' ");
		tableSql.append("  when b.shzt='th' then '退回' end shzt, ");
		tableSql.append(" c.zydm,c.bjdm,c.xymc,c.zymc,c.bjmc,c.nj,c.xm "); 
		tableSql.append(" from xg_pjpy_pjxmsqb a, xg_pjpy_pjxmshb b, xg_view_pjpy_pjryk c "); 
		tableSql.append(" where b.xtgwid =? "); 
		tableSql.append(" and b.xmdm = ? "); 
		tableSql.append(" and a.xmdm = b.xmdm and a.pjxn = b.pjxn  and a.pjxq = b.pjxq "); 
		tableSql.append(" and a.pjnd = b.pjnd  and a.xh = b.xh and a.xh = c.xh "); 
		tableSql.append(" and a.pjxn = ? ");
		tableSql.append(" and a.pjxq = ? ");
		tableSql.append(" and a.pjnd = ? ");
		
		String higherUpgw=higherUpSpgw.get("spgw");
		if(!Base.isNull(higherUpgw)){
			tableSql.append(" and exists( select 1 from xg_pjpy_pjxmshb x where x.xmdm='"+xmdm+"'  ");
			tableSql.append(" and b.pjxn=x.pjxn and b.pjxq=x.pjxq and b.pjnd=x.pjnd and b.xh=x.xh ");
			tableSql.append(" and xtgwid='"+higherUpgw+"' and b.xmdm=x.xmdm  and shzt='tg' )");
		}
		tableSql.append("  order by c.nj, c.xydm, c.zydm, c.bjdm, a.xh ");
		
		// --------------------------下级审核是否已审核-------------------------------
		tableSql.append(" )a left join ( ");
		tableSql.append("  select xh from xg_pjpy_pjxmshb ");
		tableSql.append("  where xtgwid = ? ");
		tableSql.append("  and xmdm = ? ");
		tableSql.append("  and (shzt='tg' or shzt='btg') )b on a.xh=b.xh order by a.pjxn desc,a.xh ) a ");
		// --------------------------下级审核是否已审核-------------------------------
		
		tableSql.append(query);
		
		// ====================SQL拼装 end================================

		String[] colList = new String[] {"sfsh","pkValue", "xh", "xm", "nj", "bjmc", "sqsj","shzt"};
		
		String[]inputZ={spgw,xmdm,pjxn,pjxq,pjnd,nextSpgw.get("spgw"),xmdm};
		
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(model.getPages(), tableSql.toString(),dao.unionArray(inputZ, inputV) ,
						colList);

		return list;
	}
	
	// ===========================评奖项目审核 结果集 end =============================
	
	
	// ===========================评奖岗位信息 begin===============================
	/**
	 * 获取本用户在指定评奖
	 * 项目中的审核岗位信息
	 * (考虑一人多岗)
	 * @author qlj
	 */
	public List<HashMap<String,String>> getSpgwList(WdpjXmshModel model,User user) {

		PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
		// 项目代码
		String xmdm = model.getXmdm();
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		
		String userName=user.getUserName();
	
		StringBuilder sql = new StringBuilder();

		sql.append("  select c.id,c.mc ");
		sql.append("  from xg_pjpy_pjxmwhb a,xg_xtwh_spbz b ,xg_xtwh_spgw c,xg_xtwh_spgwyh d ");
		sql.append("  where a.lcid=b.splc and b.spgw=c.id ");
		sql.append("  and b.spgw=d.spgw and d.spyh =?  ");
		sql.append("  and  a.xmdm = ?  and a.pjxn=? and a.pjxq=? and a.pjnd=? ");
		sql.append("  order by b.xh");
		
		DAO dao = DAO.getInstance();

		return dao.getList(sql.toString(), new String[]{userName,xmdm,pjxn,pjxq,pjnd}, new String[]{"id","mc"});
	}
	
	/**
	 * 根据评奖项目、当前审核
	 * 岗位获取下级审批岗位
	 * 
	 * @author qlj
	 */
	public HashMap<String,String> getNextSpMap(WdpjXmshModel model,User user) {

		PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
		// 项目代码
		String xmdm = model.getXmdm();
		
		String spgw = model.getSpgw(); 
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
	
		StringBuilder sql = new StringBuilder();

		sql.append(" select * from xg_xtwh_spbz a ");
		sql.append(" where splc = (select lcid ");
		sql.append(" from xg_pjpy_pjxmwhb ");
		sql.append(" where xmdm = ? and pjxn=? and pjxq=? and pjnd=?) ");
		sql.append(" and exists(select * from xg_xtwh_spbz  b ");
		sql.append(" where splc = (select lcid ");
		sql.append(" from xg_pjpy_pjxmwhb ");
		sql.append(" where xmdm = ?  ");
		sql.append(" and spgw=? )  ");
		sql.append(" and a.xh-1=b.xh ) ");
		
		DAO dao = DAO.getInstance();

		return dao.getMapNotOut(sql.toString(), new String[]{xmdm,pjxn,pjxq,pjnd,xmdm,spgw});
	}
	
	/**
	 * 根据评奖项目、当前审核
	 * 岗位获取下级审批岗位
	 * @author qlj
	 */
	public HashMap<String,String> getHigherUpSpMap(WdpjXmshModel model,User user) {

		PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
		// 项目代码
		String xmdm = model.getXmdm();
		
		String spgw = model.getSpgw(); 
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
	
		StringBuilder sql = new StringBuilder();

		sql.append(" select * from xg_xtwh_spbz a ");
		sql.append(" where splc = (select lcid ");
		sql.append(" from xg_pjpy_pjxmwhb ");
		sql.append(" where xmdm = ? and pjxn=? and pjxq=? and pjnd=?) ");
		sql.append(" and exists(select * from xg_xtwh_spbz  b ");
		sql.append(" where splc = (select lcid ");
		sql.append(" from xg_pjpy_pjxmwhb ");
		sql.append(" where xmdm = ?  ");
		sql.append(" and spgw=? )  ");
		sql.append(" and a.xh+1=b.xh ) ");
		
		DAO dao = DAO.getInstance();

		return dao.getMapNotOut(sql.toString(), new String[]{xmdm,pjxn,pjxq,pjnd,xmdm,spgw});
	}
	
	// ===========================评奖岗位信息 end===============================
	
	
	// ===========================评奖项目审核详细 begin===============================
	/**
	 * 获取评奖项目详细审核信息
	 * (根据审核岗位动态出)
	 * @author qlj
	 */
	public List<HashMap<String,String>> getXmshInfo(WdpjXmshModel model,User user) {

		// 项目代码
		String xmdm = model.getXmdm();
		// 审批岗位
		String spgw = model.getSpgw(); 
		// 学号
		String xh=model.getXh()[0];
	
		StringBuilder sql = new StringBuilder();

		List<String>inputV=new ArrayList<String>();
		
		sql.append(" select spgw,mc gwmc,shzt,shsj,shyj,shr, ");
		sql.append(" (select xm from yhb t where b.shr=t.yhm)shrxm ");       
		sql.append(" from ( select spgw,mc from xg_xtwh_spbz a,xg_xtwh_spgw c where splc= ");             
		sql.append(" (select lcid from xg_pjpy_pjxmwhb where xmdm=?) ");   
		sql.append(" and (exists( select 1 from xg_xtwh_spbz b where splc= ");   
		sql.append(" (select lcid from xg_pjpy_pjxmwhb where xmdm=?  )  ");  
		
		inputV.add(xmdm);
		inputV.add(xmdm);
		if(!Base.isNull(spgw)){
			
			sql.append(" and spgw=? ");
			inputV.add(spgw);
		}
		
		sql.append(" and a.xh<=b.xh) ");  
		sql.append(" or exists ");  
		sql.append(" (select 1  from xg_pjpy_pjxmshb b "); 
		sql.append(" where c.id = b.xtgwid "); 
		sql.append(" and b.xh = ? "); 
		sql.append(" and b.xmdm = ? "); 
		sql.append(" and shzt='th')) "); 
		sql.append(" and a.spgw=c.id order by xh)a left join xg_pjpy_pjxmshb b ");   
		sql.append(" on a.spgw=b.xtgwid  where b.xh=? and b.xmdm=? ");   
 
		DAO dao = DAO.getInstance();
		inputV.add(xh);
		inputV.add(xmdm);
		inputV.add(xh);
		inputV.add(xmdm);
		return dao.getListNotOut(sql.toString(), inputV.toArray(new String[]{}));
	}
	
	/**
	 * 获取学生评奖申请详细
	 * 信息（包括学生基本信息）
	 * 
	 * @author qlj
	 */
	public HashMap<String,String> getXmsqInfo(WdpjXmshModel model,User user) {
		
		PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
		// 项目代码
		String xmdm = model.getXmdm();
		// 学号
		String xh=model.getXh()[0];
		
		String pjxn = jbszModel.getPjxn();
		
		String pjxq = jbszModel.getPjxq();
		
		String pjnd = jbszModel.getPjnd();
	
		StringBuilder sql = new StringBuilder();

		sql.append(" select a.pjxn,a.pjxq,a.pjnd,a.xh, ");   
		sql.append(" a.sqsj,a.sqly,b.xmmc,b.xmsm,c.nj,c.xm,c.xymc,c.zymc,c.bjmc ");   
		sql.append(" from xg_pjpy_pjxmsqb a left join xg_pjpy_pjxmwhb b ");   
		sql.append(" on a.xmdm=b.xmdm and a.pjxn=b.pjxn and a.pjxq=b.pjxq ");   
		sql.append(" and a.pjnd=b.pjnd left join xg_view_pjpy_pjryk c on a.xh=c.xh   ");   
		sql.append(" where a.xmdm = ? ");   
		sql.append(" and a.pjxn = ? ");   
		sql.append(" and a.pjxq = ? ");   
		sql.append(" and a.pjnd = ? ");   
		sql.append(" and a.xh = ? ");   
 
		DAO dao = DAO.getInstance();

		return dao.getMapNotOut(sql.toString(), new String[]{xmdm,pjxn,pjxq,pjnd,xh});
	}
	
	// ===========================评奖项目审核详细 end===============================
	
	
	// ===========================审核状态修改 begin===============================
	/**
	 * 修改审核状态
	 * (单个与批量审核皆可使用)
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean updateShzt(WdpjXmshModel model,User user) throws Exception{
		
		DAO dao=DAO.getInstance();
		
		PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
		
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		
		StringBuilder sql=new StringBuilder();
		
		ArrayList<String>inputV=new ArrayList<String>();
		
		String shzt=model.getShzt();
		
		String shyj=model.getShyj();
		
		String userName=user.getUserName();
		
		String xmdm=model.getXmdm();
		
		String spgw=model.getSpgw();
		
		String[]xh=model.getXh();
		
		sql.append(" update xg_pjpy_pjxmshb set shzt=?,shr=?,shsj=?, ");
		sql.append(" shyj=? where xmdm=? and xtgwid=?  ");
		sql.append(" and pjxn=? and pjxq=? and pjnd=? ");
		sql.append(" and xh in ( ");
		
		inputV.add(shzt);
		inputV.add(userName);
		inputV.add(GetTime.getNowTime2());
		inputV.add(shyj);
		inputV.add(xmdm);
		inputV.add(spgw);
		
		inputV.add(pjxn);
		inputV.add(pjxq);
		inputV.add(pjnd);
		
		for(int i=0;i<xh.length;i++){
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
			inputV.add(xh[i]);
		}
		sql.append(" ) ");
		
		return dao.runUpdate(sql.toString(), inputV);
		
	}
	
	/**
	 * 审核时如果为退回状态
	 * 上级审核需要修改为需重审
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean updateThzt(WdpjXmshModel model,User user) throws Exception{
		
		DAO dao=DAO.getInstance();
		
		PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
		
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		
		StringBuilder sql=new StringBuilder();
		
		ArrayList<String>inputV=new ArrayList<String>();
	
		String xmdm=model.getXmdm();
		
		String spgw=getHigherUpSpMap(model,user).get("spgw");
		
		String[]xh=model.getXh();
		
		sql.append(" update xg_pjpy_pjxmshb set shzt='xcs',shr='',shsj='', ");
		sql.append(" shyj='' where xmdm=? and xtgwid=?  ");
		sql.append(" and pjxn=? and pjxq=? and pjnd=? ");
		sql.append(" and xh in ( ");
	
		inputV.add(xmdm);
		inputV.add(spgw);
		
		inputV.add(pjxn);
		inputV.add(pjxq);
		inputV.add(pjnd);
		
		for(int i=0;i<xh.length;i++){
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
			inputV.add(xh[i]);
		}
		sql.append(" ) ");
		
		return dao.runUpdate(sql.toString(), inputV);
		
	}
	
	/**
	 * 获取项目最高审核岗位级别
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,String> getMaxSpxh(WdpjXmshModel model,User user) throws Exception{
		
		DAO dao=DAO.getInstance();
		
		PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
		
		String pjxn=jbszModel.getPjxn();
		String pjxq=jbszModel.getPjxq();
		String pjnd=jbszModel.getPjnd();
		
		String xmdm=model.getXmdm();
		
		StringBuilder sql=new StringBuilder();
		// 评奖学年
		sql.append(" select splc,max(xh)xh ");
		sql.append(" from xg_xtwh_spbz ");
		sql.append(" where splc = (select lcid ");
		sql.append(" from xg_pjpy_pjxmwhb ");
		sql.append(" where xmdm = ? and pjxn=? and pjxq=? and pjnd=? ) ");
		sql.append(" group by splc");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xmdm,pjxn,pjxq,pjnd});
		
	}
	
	/**
	 * 获取项目最高审核岗位级别
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean updateSqjg(WdpjXmshModel model,User user) throws Exception{
		
		DAO dao=DAO.getInstance();
		
		PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
		
		String pjxn=jbszModel.getPjxn();
		String pjxq=jbszModel.getPjxq();
		String pjnd=jbszModel.getPjnd();
		
		String xmdm=model.getXmdm();
		
		HashMap<String,String>maxSp=getMaxSpxh(model, user);
		
		String maxxh=maxSp.get("xh");
		
		StringBuilder sql=new StringBuilder();
		// 评奖学年
		sql.append(" update xg_pjpy_pjxmsqb b set sqjg= ");
		sql.append(" (select case when tg = ? then 'tg' when btg >= 1 then 'btg'  ");
		sql.append(" when th >= 1 then 'shz'  when wsh = ? then 'wsh' else 'shz' end shzt ");
		sql.append(" from (select xh,xmdm,sum(tg)tg,sum(btg)btg,sum(wsh)wsh,sum(th)th from( ");
		sql.append(" select xh,xmdm,case when shzt='tg' then 1 else 0 end tg, ");
		sql.append(" case when shzt='btg' then 1 else 0  end btg, ");
		sql.append(" case when shzt='wsh' then 1 else 0  end wsh, ");
		sql.append(" case when shzt='th' then 1 else 0  end  th ");
		sql.append(" from xg_pjpy_pjxmshb where pjxn=? and pjxq=? and pjnd=? ");
		sql.append(" and xmdm=?) group by xh,xmdm)a where ");
		sql.append(" a.xmdm=b.xmdm and a.xh=b.xh) ");
		sql.append(" where b.pjxn=? and b.pjxq=? and b.pjnd=? ");
		sql.append(" and exists(select 1 from xg_pjpy_pjxmshb c where b.xh=c.xh ");
		sql.append(" and b.pjxn=c.pjxn and b.pjxq=c.pjxq and b.pjnd=c.pjnd and b.xmdm=c.xmdm ");
		sql.append(" and c.pjxn = ? ");
		sql.append(" and c.pjxq = ? ");
		sql.append(" and c.pjnd = ? ");
		sql.append(" and c.xmdm = ? )");

		return dao.runUpdate(sql.toString(), new String[] { maxxh, maxxh, pjxn,
				pjxq, pjnd, xmdm, pjxn, pjxq, pjnd, pjxn, pjxq, pjnd, xmdm });
		
	}
	
	public boolean resultShzt(WdpjXmshModel model,User user) throws Exception{
		
		DAO dao=DAO.getInstance();
		
		PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
		
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		
		StringBuilder sql=new StringBuilder();
	
		String xmdm=model.getXmdm();
		
		String[] xh=model.getXh();
		
		sql.append(" update xg_pjpy_pjxmshb set shzt='xcs'  ");
		sql.append(" where xmdm=? and pjxn=?  ");
		sql.append(" and pjxq=? and pjnd=? ");
		sql.append(" and shzt='th' ");
		sql.append(" and shzt<>'wsh' ");
		sql.append(" and xh in ( ");
		for(int i=0;i<xh.length;i++){
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
		}
		sql.append("  ) ");
		
		return dao.runUpdate(sql.toString(),dao.unionArray(new String[]{xmdm,pjxn,pjxq,pjnd},xh));
	}
	// ===========================审核状态修改 end===============================
}
