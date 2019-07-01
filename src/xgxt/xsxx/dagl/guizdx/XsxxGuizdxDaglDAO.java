package xgxt.xsxx.dagl.guizdx;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class XsxxGuizdxDaglDAO extends CommDAO {
	
	DAO dao=DAO.getInstance();
	
	//---------------------------档案类型 begin-----------------------------
	
	/**
	 * 学生档案信息代码表
	 * @param model
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public List<String[]>getDadmbInfo(XsxxGuizdxDaglForm model,String[]colList) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		
		MakeQuery mQuery=new MakeQuery();
		String []inputV={"lx"};
		String []inputLikeV={"mc"};
		
		mQuery.makeQuery(inputV, inputLikeV, model);
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select rownum r,a.dm,a.mc,case when b.dm is not ");
		sql.append("null then 'disabled'  else '' end disabled, ");
		sql.append(" case when lx='001' then '新生档案' ");
		sql.append(" when lx='002' then '在校档案' ");
		sql.append(" when lx='003' then '毕业转档' end lx ");
		sql.append(" from  xg_gzdx_xsxx_xsdadmb a ");
		sql.append(" left join( ");
		sql.append(" select distinct(dm) from( ");
		sql.append(" select dm from xg_gzdx_xsxx_xsrxdab  ");
		sql.append(" union ");
		sql.append(" select dm from xg_gzdx_xsxx_xszxqjdab ");
		sql.append(" union ");
		sql.append(" select dm from xg_gzdx_xsxx_xsbyzdb))b on a.dm=b.dm ");
		
		return CommonQueryDAO.commonQuery(sql.toString(),
				mQuery.getQueryString(), mQuery.getInputList(), colList, model);
	}
	
	/**
	 * 增加档案代码信息
	 * @return
	 * @throws Exception 
	 */
	public boolean addDadmb(XsxxGuizdxDaglForm model) throws Exception{
		
		StringBuilder sql=new StringBuilder();
		
		String mc=model.getMc();
		String lx=model.getLx();
		
		sql.append(" insert into xg_gzdx_xsxx_xsdadmb(mc,lx)values(?,?) ");
		return dao.runUpdate(sql.toString(),new String[]{mc,lx});
		
	}
	
	/**
	 * 增加档案代码信息
	 * @return
	 * @throws Exception 
	 */
	public boolean updateDadmb(XsxxGuizdxDaglForm model) throws Exception{
		
		StringBuilder sql=new StringBuilder();
		
		String mc=model.getMc();
		String lx=model.getLx();
		String dm=model.getDm();
		
		if(!Base.isNull(dm)){
			
			sql.append(" update xg_gzdx_xsxx_xsdadmb set mc=? , lx=? where dm=? ");
		}
		
		return dao.runUpdate(sql.toString(),new String[]{mc,lx,dm});
		
	}
	
	/**
	 * 查询单条档案代码表信息
	 * @return
	 * @throws Exception 
	 */
	public HashMap<String,String> getOnDadmb(XsxxGuizdxDaglForm model) throws Exception{
		
		StringBuilder sql=new StringBuilder();
		String[]pkValue=model.getPkValue();
		
		String pk=pkValue[0];
		
		sql.append(" select * from xg_gzdx_xsxx_xsdadmb where dm=? ");
		
		return dao.getMap(sql.toString(), new String[]{pk}, new String[]{"dm","mc","lx"});
		
	}
	
	/**
	 * 查询单条档案代码表信息
	 * @return
	 * @throws Exception 
	 */
	public boolean delDadmb(XsxxGuizdxDaglForm model) throws Exception{
		
		
		String[]pkValue=model.getPkValue();
		String[]sql=new String[pkValue.length];
		for(int i=0;i<pkValue.length;i++){
			
			sql[i]=" delete from xg_gzdx_xsxx_xsdadmb where dm='"+pkValue[i]+"' ";
		}
		
		return dao.saveArrDate(sql);
		
	}
	
	/**
	 * 根据档案信息by档案类型
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>>getDaxxByLx(XsxxGuizdxDaglForm model){
		
		StringBuilder sql=new StringBuilder();
		String lx=model.getLx();
		sql.append(" select dm,mc from xg_gzdx_xsxx_xsdadmb where lx=? ");
		return dao.getList(sql.toString(), new String[]{lx}, new String[]{"dm","mc"});
	}
	
//	---------------------------档案类型 end-----------------------------
	
	
	// ------------------新生档案 begin-------------------------
	/**
	 * 获取新生入学档案信息
	 * @param model
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public List<String[]>getXsrxdaInfo(XsxxGuizdxDaglForm model,String[]colList) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		
		MakeQuery mQuery=new MakeQuery();
		String []inputV={"nj","xydm","zydm","bjdm"};
		String []inputLikeV={"xh","xm"};
		
		mQuery.makeQuery(inputV, inputLikeV, model);
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select  a.*,rownum r from ( ");
		sql.append(" select a.xh,a.xm,a.bjdm,a.nj,c.xydm,c.xymc,c.zydm,c.zymc, ");
		sql.append(" c.bjmc,b.jlsj,b.jlr,case when b.mc  ");
		sql.append(" is null then '无档案信息' else b.mc end mc from ( ");
		sql.append(" select xh,xm,xbm xb,to_char(nj) nj,bmdm xydm,zydm,bjdm ");
		sql.append(" from bks_xsjbxx a where not exists ");
		sql.append(" (select 1 from xsxxb b where a.xh = b.xh) ");
		sql.append(" union all  select a.xh, a.xm, a.xb, ");
		sql.append(" a.nj, a.xydm,a.zydm, a.bjdm from xsxxb a ");
		sql.append(" where (sfyby = '否' or sfyby is null) ");
		sql.append(" and (sfzx = '在校' or sfzx is null) ");
		sql.append(" ) a left join (select xh,jlsj,jlr,mc from(select a.xh,a.jlsj,a.jlr, ");
		sql.append(" to_char(WM_CONCAT(a.mc) ");
		sql.append(" over(partition by xh)) mc from ");
		sql.append(" ( select a.xh, a.dm, a.jlsj, a.jlr, b.mc ");
		sql.append(" from xg_gzdx_xsxx_xsrxdab a ");
		sql.append(" left join xg_gzdx_xsxx_xsdadmb b on a.dm = b.dm ");
		sql.append(" where b.lx = '001')a)group by xh,jlsj,jlr,mc)b on a.xh=b.xh ");
		sql.append(" left join (select a.nj, b.bmdm xydm, b.bmmc xymc, ");
		sql.append(" b.zydm, b.zymc, a.bjdm, a.bjmc from (select a.* ");
		sql.append("  from bks_bjdm a) a,(select a.zydm, a.zymc, b.bmdm, b.bmmc  from bks_zydm a, zxbz_xxbmdm b ");
		sql.append("  where a.bmdm = b.bmdm) b where a.zydm = b.zydm ");
		sql.append(" ) c on a.bjdm=c.bjdm ");
		sql.append("  )a  ");

		return CommonQueryDAO.commonQuery(sql.toString(),
				mQuery.getQueryString()+getUserSql(model), mQuery.getInputList(), colList, model);
	}
	
	/**
	 * 单个保存新生档案
	 * @return
	 * @throws Exception 
	 */
	public boolean saveXsdaByOne(XsxxGuizdxDaglForm model) throws Exception{
		User user=model.getUser();
		String userName=user.getUserName();
		String[]xsdaInfo = model.getXsdaInfo();
		
		int num=0;
		
		// -----------判断是否有档案维护信息，如果为空则清空操作---------------
		if(xsdaInfo!=null && xsdaInfo.length>0){
			num = xsdaInfo.length;
		}
		// ---------一条删除语句 多条增加语句--------------
		String[]delSql=new String[num+1];
		
		delSql[0]=" delete from xg_gzdx_xsxx_xsrxdab where xh='"+model.getXh()+"' ";
		if(xsdaInfo!=null && xsdaInfo.length>0){
			for(int i=1;i<=xsdaInfo.length;i++){
				delSql[i]=" insert into xg_gzdx_xsxx_xsrxdab(xh,dm,jlsj,jlr)";
				delSql[i]+=" values('"+model.getXh()+"','"+xsdaInfo[i-1]+"',sysdate,'"+userName+"') ";
			}
		}
		return saveArrDate(delSql);
	}
	
	/**
	 * 获取单个新生档案情况
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>>getXsdaByOne(XsxxGuizdxDaglForm model){
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.dm, b.mc ");
		sql.append(" from xg_gzdx_xsxx_xsrxdab a ");
		sql.append(" left join xg_gzdx_xsxx_xsdadmb b on a.dm = b.dm where xh=? ");
		return dao.getList(sql.toString(), new String[]{model.getXh()}, new String[]{"dm","mc"});
	}
	// ---------------------新生档案 end-------------------
	
	// ------------------在校生档案 begin------------------
	/**
	 * 获取在校生档案信息
	 * @param model
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public List<String[]>getZxdaInfo(XsxxGuizdxDaglForm model,String[]colList) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		
		MakeQuery mQuery=new MakeQuery();
		String []inputV={"nj","xydm","zydm","bjdm"};
		String []inputLikeV={"xh","xm"};
		
		mQuery.makeQuery(inputV, inputLikeV, model);
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select rownum r,a.* from ( ");
		sql.append(" select a.xh,a.xm,a.bjdm,a.nj,c.xydm,c.xymc,c.zydm,c.zymc, ");
		sql.append(" c.bjmc,b.jlsj,b.jlr,case when b.mc  ");
		sql.append(" is null then '无档案信息' else b.mc end mc from ( ");
		sql.append(" select xh,xm,xbm xb,to_char(nj) nj,bmdm xydm,zydm,bjdm ");
		sql.append(" from bks_xsjbxx a where not exists ");
		sql.append(" (select 1 from xsxxb b where a.xh = b.xh) ");
		sql.append(" union all  select a.xh, a.xm, a.xb, ");
		sql.append(" a.nj, a.xydm,a.zydm, a.bjdm from xsxxb a ");
		sql.append(" where (sfyby = '否' or sfyby is null) ");
		sql.append(" and (sfzx = '在校' or sfzx is null) ");
		sql.append(" ) a left join (select xh,jlsj,jlr,mc from(select a.xh,a.jlsj,a.jlr, ");
		sql.append(" to_char(WM_CONCAT(a.mc) ");
		sql.append(" over(partition by xh)) mc from ");
		sql.append(" ( select a.xh, a.dm, a.jlsj, a.jlr, b.mc ");
		sql.append(" from xg_gzdx_xsxx_xszxqjdab a ");
		sql.append(" left join xg_gzdx_xsxx_xsdadmb b on a.dm = b.dm ");
		sql.append(" where b.lx = '002')a)group by xh,jlsj,jlr,mc)b on a.xh=b.xh ");
		sql.append(" left join (select a.nj, b.bmdm xydm, b.bmmc xymc, ");
		sql.append(" b.zydm, b.zymc, a.bjdm, a.bjmc from (select a.* ");
		sql.append("  from bks_bjdm a) a,(select a.zydm, a.zymc, b.bmdm, b.bmmc  from bks_zydm a, zxbz_xxbmdm b ");
		sql.append("  where a.bmdm = b.bmdm) b where a.zydm = b.zydm ");
		sql.append(" ) c on a.bjdm=c.bjdm ");
		sql.append(" )a ");

		return CommonQueryDAO.commonQuery(sql.toString(),
				mQuery.getQueryString()+getUserSql(model), mQuery.getInputList(), colList, model);
	}
	
	/**
	 * 单个保存在校生档案
	 * @return
	 * @throws Exception 
	 */
	public boolean saveZxdaByOne(XsxxGuizdxDaglForm model) throws Exception{
		User user=model.getUser();
		String userName=user.getUserName();
		String[]xsdaInfo = model.getXsdaInfo();
		
		// ---------一条删除语句 多条增加语句--------------
		int num=0;
		if(xsdaInfo!=null && xsdaInfo.length>0){
			num=xsdaInfo.length;
		}
		String[]delSql=new String[num+1];
		
		delSql[0]=" delete from xg_gzdx_xsxx_xszxqjdab where xh='"+model.getXh()+"' ";
		for(int i=1;i<=num;i++){
			delSql[i]=" insert into xg_gzdx_xsxx_xszxqjdab(xh,dm,jlsj,jlr)";
			delSql[i]+=" values('"+model.getXh()+"','"+xsdaInfo[i-1]+"',sysdate,'"+userName+"') ";
		}
		
		return saveArrDate(delSql);
	}
	
	/**
	 * 获取单个在校档案情况
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>>getZxdaByOne(XsxxGuizdxDaglForm model){
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.dm, b.mc ");
		sql.append(" from xg_gzdx_xsxx_xszxqjdab a ");
		sql.append(" left join xg_gzdx_xsxx_xsdadmb b on a.dm = b.dm where xh=? ");
		return dao.getList(sql.toString(), new String[]{model.getXh()}, new String[]{"dm","mc"});
	}
	// ---------------------在校生档案 end-------------------
	
	//	 ------------------毕业生档案 begin------------------
	/**
	 * 获取毕业生档案信息
	 * @param model
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public List<String[]>getBysdaInfo(XsxxGuizdxDaglForm model,String[]colList) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		
		MakeQuery mQuery=new MakeQuery();
		String []inputV={"nj","xydm","zydm","bjdm"};
		String []inputLikeV={"xh","xm"};
		
		mQuery.makeQuery(inputV, inputLikeV, model);
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select rownum r,a.* from ( ");
		sql.append(" select a.xh,a.xm,a.bjdm,a.nj,c.xydm,c.xymc,c.zydm,c.zymc,d.sfbrtj,d.datddz,d.jsr, ");
		sql.append(" c.bjmc,case when b.mc  ");
		sql.append(" is null then '无档案信息' else b.mc end mc from ( ");
		sql.append(" select xh,xm,xbm xb,to_char(nj) nj,bmdm xydm,zydm,bjdm ");
		sql.append(" from bks_xsjbxx a where not exists ");
		sql.append(" (select 1 from xsxxb b where a.xh = b.xh) ");
		sql.append(" union all  select a.xh, a.xm, a.xb, ");
		sql.append(" a.nj, a.xydm,a.zydm, a.bjdm from xsxxb a ");
		sql.append(" where (sfyby = '否' or sfyby is null) ");
		sql.append(" and (sfzx = '在校' or sfzx is null) ");
		sql.append(" ) a left join (select xh,mc from(select a.xh, ");
		sql.append(" to_char(WM_CONCAT(a.mc) ");
		sql.append(" over(partition by xh)) mc from ");
		sql.append(" ( select a.xh, a.dm,b.mc ");
		sql.append(" from xg_gzdx_xsxx_xsbyzdb a ");
		sql.append(" left join xg_gzdx_xsxx_xsdadmb b on a.dm = b.dm ");
		sql.append(" where b.lx = '003')a)group by xh,mc)b on a.xh=b.xh ");
		sql.append(" left join (select a.nj, b.bmdm xydm, b.bmmc xymc, ");
		sql.append(" b.zydm, b.zymc, a.bjdm, a.bjmc from (select a.* ");
		sql.append("  from bks_bjdm a) a,(select a.zydm, a.zymc, b.bmdm, b.bmmc  from bks_zydm a, zxbz_xxbmdm b ");
		sql.append("  where a.bmdm = b.bmdm) b where a.zydm = b.zydm ");
		sql.append(" ) c on a.bjdm=c.bjdm ");
		
		sql.append("  left join xg_gzdx_xsxx_xsbydab d on a.xh=d.xh ");
		sql.append(" )a ");

		return CommonQueryDAO.commonQuery(sql.toString(),
				mQuery.getQueryString()+getUserSql(model), mQuery.getInputList(), colList, model);
	}
	
	/**
	 * 增加档案代码信息
	 * @return
	 * @throws Exception 
	 */
	public boolean addBysda(XsxxGuizdxDaglForm model) throws Exception{
		
		StringBuilder sql=new StringBuilder();
		User user=model.getUser();
		String xh=model.getXh();
		String sfbrtd=model.getSfbrtj();
		String datddz=model.getDatddz();
		String userName=user.getUserName();
		
		sql.append(" insert into xg_gzdx_xsxx_xsbydab(xh,jlsj,sfbrtj,datddz,jsr)values(?,sysdate,?,?,?) ");
		
		return dao.runUpdate(sql.toString(),new String[]{xh,sfbrtd,datddz,userName});
		
	}
	
	/**
	 * 增加档案代码信息
	 * @return
	 * @throws Exception 
	 */
	public boolean delBysda(XsxxGuizdxDaglForm model) throws Exception{
		
		StringBuilder sql=new StringBuilder();
		String xh=model.getXh();
		
		sql.append(" delete from  xg_gzdx_xsxx_xsbydab where xh=? ");
		
		return dao.runUpdate(sql.toString(),new String[]{xh});
		
	}
	
	/**
	 * 增加档案代码信息
	 * @return
	 * @throws Exception 
	 */
	public boolean updateBysda(XsxxGuizdxDaglForm model) throws Exception{
		
		StringBuilder sql=new StringBuilder();
		
		String mc=model.getMc();
		String lx=model.getLx();
		String dm=model.getDm();
		
		if(!Base.isNull(dm)){
			
			sql.append(" update xg_gzdx_xsxx_xsbydab set sfbrtj=? , datddz=?,jsr=? where xh=? ");
		}
		
		return dao.runUpdate(sql.toString(),new String[]{mc,lx,dm});
		
	}
	
	/**
	 * 查询单条档案代码表信息
	 * @return
	 * @throws Exception 
	 */
	public HashMap<String,String> getOneBysda(XsxxGuizdxDaglForm model) throws Exception{
		
		StringBuilder sql=new StringBuilder();
		String xh=model.getXh();
		
		sql.append(" select a.xh,a.xm,a.nj,a.xydm,a.xymc, ");
		sql.append(" a.zydm,a.zymc,a.bjdm,a.bjmc, ");
		sql.append(" b.sfbrtj,b.datddz,b.jsr from view_xsjbxx a ");
		sql.append(" left join xg_gzdx_xsxx_xsbydab b on a.xh = b.xh ");
		sql.append(" where a.xh=? ");
		
		String []colList={"xh","xm","nj","xydm","xymc","zydm","zymc","bjdm","bjmc","sfbrtj",
				"datddz","jsr"};
		
		return dao.getMap(sql.toString(), new String[]{xh}, colList);
		
	}
	
	/**
	 * 查询单条档案代码表信息
	 * @return
	 * @throws Exception 
	 */
	public boolean delByda(XsxxGuizdxDaglForm model) throws Exception{
		
		
		String[]pkValue=model.getPkValue();
		String[]sql=new String[pkValue.length];
		for(int i=0;i<pkValue.length;i++){
			
			sql[i]=" delete from xg_gzdx_xsxx_xsbydab where xh='"+pkValue[i]+"' ";
		}
		
		return dao.saveArrDate(sql);
		
	}
	
	/**
	 * 获取单个在校档案情况
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>>getBydaByOne(XsxxGuizdxDaglForm model){

		StringBuilder sql = new StringBuilder();
		sql.append(" select a.dm, b.mc ");
		sql.append(" from xg_gzdx_xsxx_xsbyzdb a ");
		sql.append(" left join xg_gzdx_xsxx_xsdadmb b on a.dm = b.dm where xh=? ");
		return dao.getList(sql.toString(), new String[]{model.getXh()}, new String[]{"dm","mc"});
	}
	// ---------------------在校生档案 end-------------------
	
	
	//	 ------------------结果查询 begin------------------
	/**
	 * 获取毕业生档案信息
	 * @param model
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public List<String[]>getDacxInfo(XsxxGuizdxDaglForm model,String[]colList) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		
		MakeQuery mQuery=new MakeQuery();
		String []inputV={"nj","xydm","zydm","bjdm"};
		String []inputLikeV={"xh","xm"};
		
		mQuery.makeQuery(inputV, inputLikeV, model);
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select rownum r,a.* from( ");
		sql.append(" select a.xh,a.xm,a.bjdm,a.nj, ");
		sql.append(" c.xydm,c.xymc,c.zydm,c.zymc,c.bjmc, ");
		sql.append(" case when b.zxda is null then '无档案信息' else  b.zxda  end zxda, ");
		sql.append(" case when d.xsda is null then '无档案信息' else d.xsda end xsda, ");
		sql.append(" case when f.byda is null then '无档案信息' else f.byda end byda, ");
		sql.append(" e.sfbrtj,e.datddz,e.jsr  from ( ");
       
		// ---------------------学生基本信息 begin------------------------
		sql.append(" select xh, xm, xbm xb, to_char(nj) nj, bmdm xydm, zydm, bjdm ");
		sql.append(" from bks_xsjbxx a ");
		sql.append(" where not exists (select 1 from xsxxb b where a.xh = b.xh) ");
		sql.append(" union all select a.xh, a.xm, a.xb, a.nj, a.xydm, a.zydm, a.bjdm ");
		sql.append(" from xsxxb a where (sfyby = '否' or sfyby is null) ");
		sql.append(" and (sfzx = '在校' or sfzx is null)) a ");
		// ---------------------学生基本信息 end------------------------
		
		// ----------------------新生在校期间档案 begin-----------------------
		sql.append(" left join (select xh, jlsj, jlr, mc zxda ");
		sql.append(" from (select a.xh,a.jlsj,a.jlr,to_char(WM_CONCAT(a.mc) ");
		sql.append(" over(partition by xh)) mc from (select a.xh, a.dm, a.jlsj, a.jlr, b.mc ");
		sql.append(" from xg_gzdx_xsxx_xszxqjdab a left join  ");
		sql.append(" xg_gzdx_xsxx_xsdadmb b on a.dm = b.dm  where b.lx = '002') a) ");
		sql.append("  group by xh, jlsj, jlr, mc) b on a.xh = b.xh ");
		// ----------------------新生在校期间档案 end-----------------------
		
		// ----------------------学生学院、专业、班级 begin-----------------------
		sql.append(" left join (select a.nj,b.bmdm xydm,b.bmmc xymc,b.zydm,b.zymc, a.bjdm, ");
		sql.append(" a.bjmc from (select a.* from bks_bjdm a) a, ");
		sql.append(" (select a.zydm, a.zymc, b.bmdm, b.bmmc ");
		sql.append(" from bks_zydm a, zxbz_xxbmdm b where a.bmdm = b.bmdm) b ");
		sql.append(" where a.zydm = b.zydm) c on a.bjdm = c.bjdm ");
		// ----------------------学生学院、专业、班级 end-----------------------
		
		// ----------------------新生档案 begin-----------------------
		sql.append(" left join (select xh, jlsj, jlr, mc xsda ");
		sql.append(" from (select a.xh,a.jlsj,a.jlr, ");
		sql.append(" to_char(WM_CONCAT(a.mc) over(partition by xh)) mc ");
		sql.append(" from (select a.xh, a.dm, a.jlsj, a.jlr, b.mc ");
		sql.append(" from xg_gzdx_xsxx_xsrxdab a left join  ");
		sql.append(" xg_gzdx_xsxx_xsdadmb b on a.dm = b.dm  where b.lx = '001') a) ");
		sql.append(" group by xh, jlsj, jlr, mc) d on a.xh = d.xh ");
		
		sql.append(" left join  (select b.xh, b.sfbrtj,b.datddz, ");
		sql.append(" b.jsr from xg_gzdx_xsxx_xsbydab b)e on a.xh=e.xh ");
		// ----------------------新生档案 end-----------------------
		
		sql.append(" left join (select xh, jlsj, jlr, mc byda ");
		sql.append(" from (select a.xh,a.jlsj,a.jlr, ");
		sql.append(" to_char(WM_CONCAT(a.mc) over(partition by xh)) mc ");
		sql.append(" from (select a.xh, a.dm, a.jlsj, a.jlr, b.mc ");
		sql.append(" from xg_gzdx_xsxx_xsbyzdb a left join  ");
		sql.append(" xg_gzdx_xsxx_xsdadmb b on a.dm = b.dm  where b.lx = '003') a) ");
		sql.append(" group by xh, jlsj, jlr, mc) f on a.xh = f.xh ");
		sql.append(" )a ");
		return CommonQueryDAO.commonQuery(sql.toString(),
				mQuery.getQueryString()+getUserSql(model), mQuery.getInputList(), colList, model);
	}
	
	public String getUserSql(XsxxGuizdxDaglForm model){
		User user=model.getUser();
		String fdyQx=user.getFdyQx();
		String bzrQx=user.getBzrQx();
		String userName=user.getUserName();
		String userDep=user.getUserDep();
		String userType=user.getUserType();
		StringBuilder userQuery=new StringBuilder();
		if("true".equalsIgnoreCase(fdyQx) && "true".equalsIgnoreCase(bzrQx)){
			userQuery.append(" and exists(select 1 from fdybjb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ");
			userQuery.append("  union select 1 from bzrbbb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ");
			userQuery.append(" ) ");
		}else if("true".equalsIgnoreCase(fdyQx)){
			userQuery.append(" and exists(select 1 from fdybjb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ) ");
		}else if("true".equalsIgnoreCase(bzrQx)){
			userQuery.append(" and exists(select 1 from bzrbbb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ) ");
		}else if("xy".equalsIgnoreCase(userType)){
			userQuery.append(" and xydm='"+userDep+"' ");
		}
		return userQuery.toString();
	}
}
