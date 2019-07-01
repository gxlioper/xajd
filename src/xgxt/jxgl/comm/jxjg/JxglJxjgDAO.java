package xgxt.jxgl.comm.jxjg;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.form.User;
import xgxt.jxgl.comm.JxglCommDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 军训管理_军训结果_DAO类
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

public class JxglJxjgDAO extends JxglCommDAO {

	/**
	 * 加载军训编制列表
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>>getXxbzList(){
		
		DAO dao=DAO.getInstance();
		StringBuilder sql= new StringBuilder();
		sql.append(" select bzdm,bzmc,sjdm,bzdj from jxbzdmb a ");
		sql.append(" where not exists (select 1 from jxbzdmb b");
		sql.append(" where b.bzdj = (select max(bzdj) from jxbzdmb)");
		sql.append(" and a.bzdm=b.bzdm) order by bzdj ");
		
		return dao.getList(sql.toString(), new String[]{}, new String[]{"bzdm","bzmc","sjdm","bzdj"});
	}
	
	/**
	 * 获取军训名单结果集
	 * @param model
	 * @param topTr
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJxmdList(JxglJxjgForm model,
			List<HashMap<String, String>> topTr,String sfdc) throws Exception {

		StringBuilder sql = new StringBuilder();
		StringBuilder xszd = new StringBuilder();
		StringBuilder joinSql = new StringBuilder();
		String num = getJxjzdj();
		int numInt = Integer.parseInt(num);
		char edh = 'a';
		
		String[]tj={"xydm","nj","zydm","bjdm","dm1","dm2","dm3"};
		String[]likeTj={"xh","xm"};
		MakeQuery mQuery=new MakeQuery();
		mQuery.makeQuery(tj, likeTj, model);
		
		sql.append(" select rownum r,a.* from ( ");
		sql.append(" select c.xh,c.xm,ldbh,b.*,c.nj,c.xymc,c.xydm,c.zymc,c.zydm,c.bjdm,c.bjmc ");
		sql.append(" from  jxgl_jxmdb a left join ");
		sql.append(" (select ");

		for (int i = numInt; i > 0; i--) {
			if (i == numInt) {
				xszd.append("dm" + i + ",");
				xszd.append("mc" + i);
				joinSql.append(" (select xn,bzdm,bzmc,sjdm, bzdm dm" + i
						+ ",bzmc mc" + i + " from jxbzdmb where  bzdj= ");
				joinSql.append("'" + i + "') ");
				joinSql.append(edh);
			} else {

				joinSql.append(" left join ");
				joinSql.append(" (select  xn,bzdm,bzmc,sjdm, bzdm dm" + i
						+ ",bzmc mc" + i + " from jxbzdmb where  bzdj= ");
				joinSql.append("'" + i + "') ");
				char edhS = edh;
				edhS++;
				joinSql.append(edhS);
				joinSql.append(" on ");
				joinSql.append(edh + ".sjdm= ");
				joinSql.append(edhS + ".bzdm and ");
				joinSql.append(edh + ".xn= ");
				joinSql.append(edhS + ".xn ");
				edh++;
				xszd.append(",dm" + i);
				xszd.append(",mc" + i);
			}
		}
		sql.append("xn,"+xszd);
		sql.append(" from ");
		sql.append(joinSql);
		sql.append(" ) b on a.ldbh=b.dm" + numInt);
		sql.append(" and a.xn=b.xn ");
		sql.append(" left join view_xsjbxx c on a.xh=c.xh) a ");
		
		String[]colList=new String[topTr.size()];
		for(int i=0;i<colList.length;i++){
			HashMap<String,String>topMap=topTr.get(i);
			colList[i]=topMap.get("en");
		}
		
		User user=model.getUser();
		String userStatus=user.getUserStatus();
		String userDep=user.getUserDep();
		String userName=user.getUserName();
		
		sql.append(mQuery.getQueryString());
		if("xy".equalsIgnoreCase(userStatus)){
			sql.append(" and exists(select 1 from view_njxyzybj b where xydm='"+userDep+"' and a.xydm=b.xydm) ");
			
		}else if("jd".equalsIgnoreCase(userStatus)){
			sql.append(" and ( exists(select 1 from fdybjb c where zgh='"+userName+"'  and a.bjdm=c.bjdm) ");
			sql.append(" or exists(select 1 from bzrbbb c where zgh='"+userName+"'  and a.bjdm=c.bjdm)) ");
		}else if("fdy".equalsIgnoreCase(userStatus)){
			sql.append(" and exists(select 1 from fdybjb c where zgh='"+userName+"'  and a.bjdm=c.bjdm) ");
			
		}else if("bzr".equalsIgnoreCase(userStatus)){
			sql.append(" and exists(select 1 from bzrbbb c where zgh='"+userName+"'  and a.bjdm=c.bjdm) ");
			
		}

		if(!"yes".equalsIgnoreCase(sfdc)){
			return CommonQueryDAO.commonQuery(sql.toString(), "",mQuery.getInputList(),
					colList, model);
		}else{
			return CommonQueryDAO.commonQueryNotFy("", "", mQuery.getInputList(), colList, sql.toString(), model);
		}
	}
	
	/**
	 * 获取军训建制等级(总级数)
	 * @return String
	 */
	public String getJxjzdj(){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append(" select count(1)num from jxjzdj where jzdm<>(select max(jzdm) from  jxjzdj )");
		return dao.getOneRs(sql.toString(), new String[]{}, "num");
	}
	
	/**
	 * 获取军训建制信息
	 */
	public List<HashMap<String,String>>getJxjz(){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append(" select jzdm,jzmc from jxjzdj where jzdm<>(select max(jzdm) from  jxjzdj )");
		return dao.getList(sql.toString(), new String[]{}, new String[]{"jzdm","jzmc"});
	}
	
	/**
	 * 获取学生军训编制
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXsJxbz(JxglJxjgForm model) throws Exception {
		
		DAO dao=DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		StringBuilder joinSql = new StringBuilder();
		String num = getJxjzdj();
		int numInt = Integer.parseInt(num);
		char edh = 'a';
		
		List<String>outPut=new ArrayList<String>();
		outPut.add("xn");
		sql.append(" select a.xh,a.xn,b.jzmc1,b.jzmc2,b.jzmc3,b.jsdm,b.jgbh from jxgl_jxmdb a left join( ");
		
		sql.append(" select * from  ");

		for (int i = 1; i <= numInt; i++) {
			if (i == 1) {
				sql.append(" (select jsdm,jgbh,bzdm,bzmc,bzdm jzdm1,bzmc jzmc1,bzdj,xn  ");
				sql.append(" from jxbzdmb where bzdj='"+i+"')");
				sql.append(edh);
				joinSql.append(" on (a.ldbh=jzdm1 ");
				outPut.add("jzmc1");
			} else {
				char edhs=edh;
				edhs++;
				sql.append(" left join ");
				sql.append(" (select bzdm,bzmc,bzdm jzdm"+i+",bzmc jzmc"+i+",bzdj,xn,sjdm  ");
				sql.append(" from jxbzdmb where bzdj='"+i+"')"+edhs);
				
				sql.append(" on "+edh+".bzdm="+edhs+".sjdm");
				sql.append(" and "+edh+".xn="+edhs+".xn");
				joinSql.append(" or a.ldbh=jzdm"+i);
				outPut.add("jzmc"+i);
;				edh++;
			}
		}
		joinSql.append(" ) ");
		sql.append(" )b ");
		sql.append(joinSql);
		sql.append(" and a.xn=b.xn where xh='"+model.getXh()+"' ");
		
		String[]jgArr={"jsdm","jgbh"};
		String[]jgjArr=new String[jgArr.length+outPut.size()];
		jgjArr=dao.unionArray(outPut.toArray(new String[]{}), jgArr);
		
		return dao.getMap(sql.toString(), new String[]{}, jgjArr);
	}
	
	
}
