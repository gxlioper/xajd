package xgxt.pjpy.comm.zhcp.pdbx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjpyCommDAO;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszForm;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.pjpy.general.PjpyGeneralForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 综合素质测评_品德表现_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class ZhcpPdbxDAO extends PjpyCommDAO {

	/**
	 * 获取评分人A所需要评价的所有学生
	 * @param model
	 * @param user
	 * @param colList
	 * @return  List<String[]>
	 * @throws Exception
	 */
	public List<String[]>getXspjPdbx(ZhcpPdbxForm model,User user,String[] colList) throws Exception{
		
		List<HashMap<String, String>>top=getTop(model, user);
		String userName=user.getUserName();
		StringBuilder sql=new StringBuilder();
		
		String zcxn=ZhcpJbszForm.zcjbszModel.getPjxn();
		String zcxq=ZhcpJbszForm.zcjbszModel.getPjxq();
		String zcnd=ZhcpJbszForm.zcjbszModel.getPjnd();
		
		// ==================总分字段计算===================
		String zfzd="  ";
		for(int i=0;i<top.size();i++){
			HashMap<String, String>topMap=top.get(i);
			if(i!=0){
				
				zfzd+="+";
			}
			zfzd+=" to_number(nvl("+topMap.get("xmdm")+",0))";
		}
		// ==================总分字段计算end===================
		
		
		sql.append(" select rownum r,a.xn||'!!@@!!'||a.xq||'!!@@!!'||a.nd||'!!@@!!'||pfr||'!!@@!!'||bpfr pkValue," );
		sql.append(" a.*,b.xm bpfrxm, (select xqmc from xqdzb where a.xq=xqdm)xqmc");
		sql.append("  from( select  a.*," );
		sql.append(zfzd);
		sql.append(" zf,case when sftj='否'  then '' else(case when jsqr='退回' then '' else 'disabled' end) end disabled from xg_pjpy_pdbxhpb a where 1=1 ");
		sql.append(" and xn=? and xq=? and nd=? and pfr=? )a left join ");
		sql.append(" xg_pjpy_pjrykb b on a.bpfr=b.xh ");
		
		
		String[]inPutList={zcxn,zcxq,zcnd,userName};
		return CommonQueryDAO.commonQueryNotFy("xg_pjpy_pdbxhpb", "", inPutList, colList, sql.toString(), model);
	}
	
	/**
	 * 获取表头字段
	 * @param model
	 * @param user
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getTop(ZhcpPdbxForm model, User user) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		// ==================综测周期==========================
		String zcxn=ZhcpJbszForm.zcjbszModel.getPjxn();
		String zcxq=ZhcpJbszForm.zcjbszModel.getPjxq();
		String zcnd=ZhcpJbszForm.zcjbszModel.getPjnd();
		// ==================综测周期end==========================
		
		sql.append(" select xmdm,xmmc from xg_pjpy_pdbxhpxmb where 1=1  ");
		sql.append(" and xn=? and xq=? and nd=? ");
		sql.append(" order by xmdm ");
		return dao.getList(sql.toString(), new String[] {zcxn,zcxq,zcnd}, new String[] {
				"xmdm", "xmmc" });
	}
	
	/**
	 * 修改品德表现分
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean updatePdbxf(ZhcpPdbxForm model, User user,String xglx) throws Exception {
		

		List<String[]> params = new ArrayList<String[]>();
		int len = model.getPrimary_key().length;
		String  sql[] = new String[len];
		
		String jl="";
		if("xiugai".equalsIgnoreCase(xglx)){
			jl="";
		}else if("tijiao".equalsIgnoreCase(xglx)){
			jl="0";
		}
		
		long t1 = System.currentTimeMillis();
		
		for (int i = 0; i < len; i++) {
			
			sql[i]="";
			String zd1=model.getZd1()!=null && !Base.isNull(model.getZd1()[i]) ? model.getZd1()[i] : jl;
			String zd2=model.getZd2()!=null && !Base.isNull(model.getZd2()[i]) ? model.getZd2()[i] : jl;
			String zd3=model.getZd3()!=null && !Base.isNull(model.getZd3()[i]) ? model.getZd3()[i] : jl;
			String zd4=model.getZd4()!=null && !Base.isNull(model.getZd4()[i]) ? model.getZd4()[i] : jl;
			String zd5=model.getZd5()!=null && !Base.isNull(model.getZd5()[i]) ? model.getZd5()[i] : jl;
			String zd6=model.getZd6()!=null && !Base.isNull(model.getZd6()[i]) ? model.getZd6()[i] : jl;
			String zd7=model.getZd7()!=null && !Base.isNull(model.getZd7()[i]) ? model.getZd7()[i] : jl;
			String zd8=model.getZd8()!=null && !Base.isNull(model.getZd8()[i]) ? model.getZd8()[i] : jl;
			String zd9=model.getZd9()!=null && !Base.isNull(model.getZd9()[i]) ? model.getZd9()[i] : jl;
			String zd10=model.getZd10()!=null && !Base.isNull(model.getZd10()[i]) ? model.getZd10()[i] : jl;
			String key=model.getPrimary_key()[i];
			String[]pkValue=key.split("!!@@!!");
			
			sql[i]+=" update xg_pjpy_pdbxhpb set ";
			sql[i]+=" zd1='"+zd1+"' , zd2='"+zd2+"' , zd3='"+zd3+"' , zd4='"+zd4+"', ";
			sql[i]+=" zd5='"+zd5+"' , zd6='"+zd6+"' , zd7='"+zd7+"' , zd8='"+zd8+"', ";
			sql[i]+=" zd9='"+zd9+"' , zd10='"+zd10+"'  ";
			sql[i]+=" where xn= '"+pkValue[0]+"' ";
			sql[i]+=" and xq= '"+pkValue[1]+"' ";
			sql[i]+=" and nd= '"+pkValue[2]+"' ";
			sql[i]+=" and pfr= '"+pkValue[3]+"' ";
			sql[i]+=" and bpfr= '"+pkValue[4]+"' ";
			
			System.out.println(sql[i]+";");
		}
		
		System.out.println(System.currentTimeMillis() - t1);
		
		return this.saveArrDate(sql);
	}
	
	
	/**
	 * 修改学生提交状态
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean updateXstjzt(ZhcpPdbxForm model, User user) throws Exception {

		DAO dao=DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		String zcxn=ZhcpJbszForm.zcjbszModel.getPjxn();
		String zcxq=ZhcpJbszForm.zcjbszModel.getPjxq();
		String zcnd=ZhcpJbszForm.zcjbszModel.getPjnd();
		
		sql.append(" update xg_pjpy_pdbxhpb set ");
		sql.append(" sftj=(select case when jsqr='退回' then '重新评分' else '是' end  ");
		sql.append(" from xg_pjpy_pdbxhpb where pfr=? and rownum=1 ),jsqr='否' ");
		
		sql.append(" where xn='"+zcxn+"'");
		sql.append(" and xq='"+zcxq+"'");
		sql.append(" and nd='"+zcnd+"'");
		sql.append(" and pfr='"+model.getPfr()+"'");

		return dao.runUpdate(sql.toString(), new String[]{user.getUserName()});
	}
	
	/**
	 * 修改学生提交状态
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getLsckpf(ZhcpPdbxForm model, User user) throws Exception {
		
		SearchService searchService=new SearchService();
		//不需要考虑的权限
		HashMap<String,String> notCtrlStatus = new HashMap<String,String>();
		notCtrlStatus.put("gygly", "yes");
		user.setNotCtrlStatus(notCtrlStatus);
		
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = searchService.getSearchTjByUser("a", user);	
		
		String[] inputSearch = SearchService.getTjInput(model.getSearchModel());
		
		String zcxn=ZhcpJbszForm.zcjbszModel.getPjxn();
		String zcxq=ZhcpJbszForm.zcjbszModel.getPjxq();
		String zcnd=ZhcpJbszForm.zcjbszModel.getPjnd();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select rownum r,case when sfpf='否' then 'disabled' else ''end  disabled,a.* from( ");
		sql.append(" select a.xh,a.xm,a.nj,a.xydm,a.xymc,a.zydm,a.zymc, ");
		sql.append(" nvl((select xm from yhb c where b.qrr=c.yhm),'未确认')qrrxm, ");
		sql.append(" a.bjdm,a.bjmc,b.sftj sfpf,b.jsqr sfqr,b.qrr ");
		sql.append(" from view_xsjbxx a left join( ");
		sql.append(" select xn,xq,nd,pfr,sftj,jsqr,qrr from ");
		sql.append(" xg_pjpy_pdbxhpb where xn='"+zcxn+"' and xq='"+zcxq+"' and nd='"+zcnd+"' group by xn,xq,nd,pfr,sftj,jsqr,qrr)b ");
		sql.append(" on a.xh=b.pfr ");
		sql.append(" )a where 1=1 ");
		sql.append(" and exists( select 1 from xg_pjpy_dypfszb b where b.xn='"+zcxn+"' ");
		sql.append(" and b.xq='"+zcxq+"' and b.nd='"+zcnd+"' and b.bjdm=a.bjdm and b.kgzt='no' ) ");
		
		String[]colList={"disabled","xh","xm","nj","xymc","zymc","bjmc","sfpf","sfqr","qrrxm"};
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjByUser, inputSearch, colList, model);
	}
	
	/**
	 * 获取评分人A所需要评价的所有学生(老师查看)
	 * @param model
	 * @param user
	 * @param colList
	 * @return  List<String[]>
	 * @throws Exception
	 */
	public List<String[]>getLcckPdbx(ZhcpPdbxForm model,User user,String[] colList) throws Exception{
		
		List<HashMap<String, String>>top=getTop(model, user);
		String userName=model.getPfr();
		StringBuilder sql=new StringBuilder();
		
		// ==================综测周期==========================
		String zcxn=ZhcpJbszForm.zcjbszModel.getPjxn();
		String zcxq=ZhcpJbszForm.zcjbszModel.getPjxq();
		String zcnd=ZhcpJbszForm.zcjbszModel.getPjnd();
		// ==================综测周期end==========================
		
		// ==================总分字段计算===================
		String zfzd=" nvl( ";
		for(int i=0;i<top.size();i++){
			HashMap<String, String>topMap=top.get(i);
			if(i!=0){
				
				zfzd+="+";
			}
			zfzd+=" to_number("+topMap.get("xmdm")+")";
		}
		zfzd+=",0)";
		// ==================总分字段计算end===================
		
		
		sql.append(" select rownum r,''disabled,a.xn||'!!@@!!'||a.xq||'!!@@!!'||a.nd||'!!@@!!'||pfr||'!!@@!!'||bpfr pkValue," );
		sql.append(" a.*,b.xm bpfrxm, (select xqmc from xqdzb where a.xq=xqdm)xqmc");
		sql.append("  from( select  a.*," );
		sql.append(zfzd);
		sql.append(" zf from xg_pjpy_pdbxhpb a where 1=1 ");
		sql.append(" and xn=? and xq=? and nd=? and pfr=? )a left join ");
		sql.append(" view_xsjbxx b on a.bpfr=b.xh ");
		
		String[]inPutList={zcxn,zcxq,zcnd,userName};
		return CommonQueryDAO.commonQueryNotFy("xg_pjpy_pdbxhpb", "", inPutList, colList, sql.toString(), model);
	}
	
	/**
	 * 单个评价学生评价结果确认(取消确认)
	 * 根据前台传值
	 * @param model
	 * @param user
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateQrzt(ZhcpPdbxForm model,User user) throws Exception{
		DAO dao=DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		StringBuilder pkValue=new StringBuilder();
		
		// ==================综测周期==========================
		String zcxn=ZhcpJbszForm.zcjbszModel.getPjxn();
		String zcxq=ZhcpJbszForm.zcjbszModel.getPjxq();
		String zcnd=ZhcpJbszForm.zcjbszModel.getPjnd();
		// ==================综测周期end==========================
		
		sql.append(" update xg_pjpy_pdbxhpb set ");
		sql.append(" jsqr='"+model.getJsqr()+"' ,qrr='"+user.getUserName()+"' ");
		if("是".equalsIgnoreCase(model.getJsqr())){
			
			sql.append(" ,sftj='是' ");
		}
		sql.append(" where xn||'!!@@!!'||xq||'!!@@!!'||nd||'!!@@!!'||pfr= ");
		
		
		pkValue.append("'"+zcxn+"'");
		pkValue.append("||'!!@@!!'||");
		pkValue.append("'"+zcxq+"'");
		pkValue.append("||'!!@@!!'||");
		pkValue.append("'"+zcnd+"'");
		pkValue.append("||'!!@@!!'||");
		pkValue.append("'"+model.getPfr()+"'");
		sql.append(pkValue);
		
		return dao.runUpdate(sql.toString(), new String[]{});
	}
	
	/**
	 * 获取可否评分（0：不可评，1：可以）
	 * @param model
	 * @param user
	 * @return 
	 * @throws Exception
	 */
	public String getDypfsz (ZhcpPdbxForm model, User user) throws Exception {
		
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		
		String zcxn=ZhcpJbszForm.zcjbszModel.getPjxn();
		String zcxq=ZhcpJbszForm.zcjbszModel.getPjxq();
		String zcnd=ZhcpJbszForm.zcjbszModel.getPjnd();
		
		sql.append(" select b.* from view_xsjbxx  a left join ( ");
		sql.append(" select kgzt,bjdm from xg_pjpy_dypfszb  ");
		sql.append(" where bjdm='"+model.getBjdm()+"' and xn='"+zcxn+"' ");
		sql.append(" and xq='"+zcxq+"' and nd='"+zcnd+"' )b  ");
		sql.append(" on a.bjdm=b.bjdm where a.xh='"+model.getPfr()+"'  ");
		
		return dao.getOneRs(sql.toString(), new String[]{}, "kgzt");
	}
	
	/**
	 * 批量评价学生评价结果确认(取消确认)
	 * 根据前台传值
	 * @param model
	 * @param user
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateZt(ZhcpPdbxForm model,User user) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		List<String[]>pkValue=new ArrayList<String[]>();
		String zcxn=ZhcpJbszForm.zcjbszModel.getPjxn();
		String zcxq=ZhcpJbszForm.zcjbszModel.getPjxq();
		String zcnd=ZhcpJbszForm.zcjbszModel.getPjnd();
		
		sql.append(" update xg_pjpy_pdbxhpb set ");
		sql.append(" jsqr='退回',qrr='"+user.getUserName()+"' ");
		sql.append(" where xn||'!!@@!!'||xq||'!!@@!!'||nd||'!!@@!!'||pfr= ? ");
		
		for(int i=0;i<model.getPrimary_key().length;i++){
			
			pkValue.add(new String[]{zcxn + "!!@@!!" + zcxq + "!!@@!!"
					+ zcnd + "!!@@!!" + model.getPrimary_key()[i]});
		}
		
		return this.saveArrDate(sql.toString(), pkValue, "xg_pjpy_pdbxhpb", user);
	}
	
	/**
	 * 评价人信息
	 * @param model
	 * @param user
	 * @return
	 */
	public HashMap<String,String>getPjrxx(ZhcpPdbxForm model,User user){
		
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append(" select a.*, b.jsqr,b.sftj,(select xm from yhb c where c.yhm=b.qrr)jsxm from (select xh, xm, xymc, zymc, bjmc ");
		sql.append(" ,bjdm ");
		sql.append("  from view_xsjbxx a where xh = ? ) a ");
		sql.append("  left join (select pfr, jsqr,qrr,sftj  from xg_pjpy_pdbxhpb ");
		sql.append(" where pfr = ? and rownum = 1) b on a.xh = b.pfr ");
		String[]outputValue={"xh","xm","xymc","zymc","bjmc","jsqr","bjdm","jsxm","sftj"};
		
		return dao.getMap(sql.toString(), new String[]{user.getUserName(),user.getUserName()}, outputValue);
	} 
	
	/**
	 * 获取指定综测周期项目信息
	 * @param model
	 * @param user
	 * @return  HashMap<String,String>
	 */
	public HashMap<String,String> getXmszbInfo(ZhcpPdbxForm model,User user) {
		
		StringBuilder pkValue=new StringBuilder();
		//综测详细周期
		String xn=ZhcpJbszForm.zcjbszModel.getPjxn();
		String xq=ZhcpJbszForm.zcjbszModel.getPjxq();
		String nd=ZhcpJbszForm.zcjbszModel.getPjnd();
		
		pkValue.append(xn);
		pkValue.append("!!@@!!");
		pkValue.append(xq);
		pkValue.append("!!@@!!");
		pkValue.append(nd);
		pkValue.append("!!@@!!");
		pkValue.append("xg_view_zhcp_pdpjf");
		
		String[]colList={"xmdm","lyb","zd","glxn","glxq","glnd"};
		return getRsInfo("xg_pjpy_zcxmb", "xn||'!!@@!!'||xq||'!!@@!!'||nd||'!!@@!!'||lyb", pkValue.toString(), colList);
	}
	
	/**
	 * 获取指定综测周期项目信息
	 * @param model
	 * @param user
	 * @return  HashMap<String,String>
	 * @throws Exception 
	 */
	public boolean pdfjs(ZhcpPdbxForm model,User user) throws Exception {
		
		PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
		//获取综测项目测试
		DAO dao=DAO.getInstance();
		//HashMap<String,String>xmszMap=getXmszbInfo(model,user);
		String xmdm="zd5";
		String lyb="xg_view_zhcp_pdpjf";
		String zd="pjf";
		
		StringBuilder sql=new StringBuilder();
		StringBuilder query=new StringBuilder();
		List<String>inputV=new ArrayList<String>();
		
		// =================判断关联学年、学期、年度========================
		query.append(" and xn= ? ");
		query.append(" and xq= ? ");
		query.append(" and nd= ? ");
		
		inputV.add(jbszModel.getPjxn());
		inputV.add(jbszModel.getPjxq());
		inputV.add(jbszModel.getPjnd());
		
		// =================判断关联学年、学期、年度end========================
		sql.append(" update xg_pjpy_zhcpb a set ");
		sql.append(" "+xmdm+"=(select nvl("+zd+",0) from "+lyb+" b where a.xh=b.bpfr ");
		sql.append(query);
		sql.append(") ");
		sql.append(" where  exists(select 1 from xg_pjpy_pjrykb c where 1=1 ");
		for(int i=0;i<model.getBjdmArr().length;i++){
			if(i==0){
				sql.append(" and ( ");
			}else{
				sql.append(" or ");
			}
			sql.append(" bjdm = ? ");
			inputV.add(model.getBjdmArr()[i]);
		}
		sql.append(" ) ");
		sql.append(" and a.xh=c.xh ) ");
		sql.append(query);
		
		inputV.add(jbszModel.getPjxn());
		inputV.add(jbszModel.getPjxq());
		inputV.add(jbszModel.getPjnd());
		
		return dao.runUpdate(sql.toString(), inputV.toArray(new String[]{}));
		
	}
	
	/**
	 * 获取辅导员所带班级评分情况
	 * @param model
	 * @param user
	 * @return  List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>pdbxfTj(ZhcpPdbxForm model,User user){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		String zcxn=ZhcpJbszForm.zcjbszModel.getPjxn();
		String zcxq=ZhcpJbszForm.zcjbszModel.getPjxq();
		String zcnd=ZhcpJbszForm.zcjbszModel.getPjnd();
		//获取辅导员所带班级
		sql.append(" select bjdm,bjmc,nvl(bjrs,0)bjrs,nvl(qrrs,0)qrrs,nvl(wtjrs,0)wtjrs,nvl(wqrrs,0)wqrrs ");
		sql.append(" ,nvl(tjrs,0)tjrs from( ");
		sql.append(" select b.bjdm,b.bjmc,bjrs,tjrs,qrrs,(bjrs-tjrs)wtjrs,(bjrs-qrrs)wqrrs from ");
		sql.append(" (select bjdm from fdybjb where zgh=? union select bjdm from bzrbbb where zgh=? )a  ");
		sql.append(" left join view_njxyzybj b on a.bjdm=b.bjdm ");
		
		//班级人数
		sql.append(" left join (select bjdm,nvl(count(1),0)bjrs from  ( ");
		
		sql.append("  select xh,bjdm from xsxxb    ");
		sql.append(" where (sfyby = '否' or sfyby is null)  and (sfzx = '在校' or sfzx is null) ");
		sql.append(" union select xh,bjdm from bks_xsjbxx a where not exists(select 1 from xsxxb b where a.xh=b.xh)  ");
		
		sql.append(" )where 1=1  ");
		sql.append(" group by bjdm)c on b.bjdm=c.bjdm ");
		
		//提交人数
		sql.append(" left join (select bjdm,nvl(count(1),0)tjrs from( ");
		sql.append(" select bjdm from (select xn,xq,nd,pfr,sftj,jsqr ");
		sql.append(" from xg_pjpy_pdbxhpb where xn='"+zcxn+"' and xq='"+zcxq+"' and nd='"+zcnd+"' group by xn,xq,nd,pfr,sftj,jsqr ");
		sql.append(" )a left join ( ");

		sql.append("   select xh,bjdm from xsxxb    ");
		sql.append(" where (sfyby = '否' or sfyby is null)  and (sfzx = '在校' or sfzx is null) ");
		sql.append(" union select xh,bjdm from bks_xsjbxx a where not exists(select 1 from xsxxb b where a.xh=b.xh)  ");
		
		sql.append(" ) b on a.pfr=b.xh ");
		sql.append(" where  a.sftj='是')group by bjdm)d on a.bjdm=d.bjdm ");
		
		//确认人数
		sql.append(" left join (select bjdm,nvl(count(1),0)qrrs from( ");
		sql.append(" select bjdm from (select xn,xq,nd,pfr,sftj,jsqr ");
		sql.append(" from xg_pjpy_pdbxhpb where xn='"+zcxn+"' and xq='"+zcxq+"' and nd='"+zcnd+"' group by xn,xq,nd,pfr,sftj,jsqr ");
		sql.append(" )a left join ( ");

		sql.append("  select xh,bjdm from xsxxb     ");
		sql.append(" where (sfyby = '否' or sfyby is null)  and (sfzx = '在校' or sfzx is null) ");
		sql.append(" union select xh,bjdm from bks_xsjbxx a where not exists(select 1 from xsxxb b where a.xh=b.xh)  ");
		
		sql.append(" ) b on a.pfr=b.xh ");
		sql.append(" where  a.jsqr='是')group by bjdm)e on a.bjdm=e.bjdm) ");
		
		String[]outputValue={"bjdm","bjmc","bjrs","tjrs","qrrs","wtjrs","wqrrs"};
		
		return dao.getList(sql.toString(), new String[]{user.getUserName(),user.getUserName()}, outputValue);
	}
	
	/**
	 * 获取品德表现分设置信息
	 * @param model
	 * @param user
	 * @return  List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getPdbxSz(ZhcpPdbxForm model,User user){
		
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		// 综测详细周期
		String xn=ZhcpJbszForm.zcjbszModel.getPjxn();
		String xq=ZhcpJbszForm.zcjbszModel.getPjxq();
		String nd=ZhcpJbszForm.zcjbszModel.getPjnd();
		
		sql.append(" select xmdm,xmmc,xxf,sxf from xg_pjpy_pdbxhpxmb where 1=1 ");
		sql.append(" and xn ='"+xn+"'");
		sql.append(" and xq ='"+xq+"'");
		sql.append(" and nd ='"+nd+"'");
		
		return dao.getList(sql.toString(), new String[]{}, new String[]{"xmdm","xmmc","xxf","sxf"});
	}
}
