package xgxt.rcsw.hcyhk.hcyhkff;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.rcsw.RcswForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.Pages;

public class HcyhkffDAO extends CommDAO{
	protected DAO dao = DAO.getInstance();

	//	 ===========================学生证注销====================================
	/**
	 * 学生证注销查询
	 * @param model
	 * @param colList
	 * @param lcmc
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryHcyhkff(RcswForm model) throws Exception{
		StringBuilder sql = new StringBuilder();
		StringBuilder query=new StringBuilder();
		String[] queryArr = new String[]{"nj","xydm","zydm","bjdm","sfff"};
		String[] queryLikeArr = new String[]{"xh","xm"};
		String[] inputV ={model.getXn(),model.getXq(),model.getNd()};
		User user=model.getUser();
		String isFdy=user.getFdyQx();
		String isBzr= user.getBzrQx();
		String userName=user.getUserName();
		String userType=user.getUserType();
		String userDep=user.getUserDep();
		
		MakeQuery mQuery=new MakeQuery();
		mQuery.makeQuery(queryArr, queryLikeArr, model);
		sql.append(" select rownum r, xh||ffsj pkValue,a.* from( ");
		sql.append(" select a.xn,a.xq,b.xh,a.bz,a.nd,a.ffsj,a.jbr,a.jtdz, ");
		sql.append(" (select xm from yhb where a.jbr=yhm)jbrxm, ");
		sql.append(" b.xm,b.xb, b.nj,b.xydm, b.xymc,b.zydm, ");
		sql.append(" b.zymc,b.bjdm,b.bjmc ");
		sql.append(" ,(case when a.xh is null then '否' else '是' end )sfff ");
		sql.append(" from  view_xsjbxx b left join (select * from hcyhkffb where  ");
		sql.append(" xn=? and xq=? and nd=?) ");
		sql.append(" a on a.xh = b.xh ");
		sql.append(" )a ");
		
		String []colList={"pkValue","xh","xm","nj","xymc","zymc","bjmc","jbrxm","ffsj","sfff",};
		
		if("true".equalsIgnoreCase(isFdy) || "true".equalsIgnoreCase(isBzr)){
			
			query.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and zgh='"+userName+"' union ");
			query.append(" select 1 from bzrbbb b where a.bjdm=b.bjdm and zgh='"+userName+"' ) ");
		}else if("true".equalsIgnoreCase(isFdy)){
				
			query.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and zgh='"+userName+"' )");

		}else if("true".equalsIgnoreCase(isBzr)){
				
			query.append(" and exists(select 1 from bzrbbb b where a.bjdm=b.bjdm and zgh='"+userName+"' )");

		}else if("xy".equalsIgnoreCase(userType)){
			
			query.append(" and xydm='"+userDep+"' ");
		}
		DAO dao=DAO.getInstance();
		return CommonQueryDAO.commonQuery(sql.toString(), mQuery.getQueryString()+query,
			dao.unionArray(inputV, mQuery.getInputList()), colList, model);
	}
	public List<HashMap<String, String>> getExportList(RcswForm model) throws Exception{
		StringBuilder sql = new StringBuilder();
		StringBuilder query=new StringBuilder();
		String[] queryArr = new String[]{"nj","xydm","zydm","bjdm","sfff"};
		String[] queryLikeArr = new String[]{"xh","xm"};
		String[] inputV ={model.getXn(),model.getXq(),model.getNd()};
		User user=model.getUser();
		String isFdy=user.getFdyQx();
		String isBzr= user.getBzrQx();
		String userName=user.getUserName();
		String userType=user.getUserType();
		String userDep=user.getUserDep();
		
		MakeQuery mQuery=new MakeQuery();
		mQuery.makeQuery(queryArr, queryLikeArr, model);
		sql.append(" select rownum r, xh||ffsj pkValue,a.* from( ");
		sql.append(" select a.xn,a.xq,b.xh,a.bz,a.nd,a.ffsj,a.jbr,a.jtdz,a.qdz,a.zdz,");
		sql.append(" (select xm from yhb where a.jbr=yhm)jbrxm, ");
		sql.append(" b.xm,b.xb, b.nj,b.xydm, b.xymc,b.zydm, ");
		sql.append(" b.zymc,b.bjdm,b.bjmc ");
		sql.append(" ,(case when a.xh is null then '否' else '是' end )sfff ");
		sql.append(" from  view_xsjbxx b left join (select * from hcyhkffb where  ");
		sql.append(" xn=? and xq=? and nd=?) ");
		sql.append(" a on a.xh = b.xh ");
		sql.append(" )a ");
		
		String []colList={"pkValue","xh","xm","nj","xymc","zymc","bjmc","jbrxm","ffsj","sfff","qdz","zdz"};
		
		if("true".equalsIgnoreCase(isFdy) || "true".equalsIgnoreCase(isBzr)){
			
			query.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and zgh='"+userName+"' union ");
			query.append(" select 1 from bzrbbb b where a.bjdm=b.bjdm and zgh='"+userName+"' ) ");
		}else if("true".equalsIgnoreCase(isFdy)){
				
			query.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and zgh='"+userName+"' )");

		}else if("true".equalsIgnoreCase(isBzr)){
				
			query.append(" and exists(select 1 from bzrbbb b where a.bjdm=b.bjdm and zgh='"+userName+"' )");

		}else if("xy".equalsIgnoreCase(userType)){
			
			query.append(" and xydm='"+userDep+"' ");
		}
		sql.append( mQuery.getQueryString()+query);
		DAO dao=DAO.getInstance();
		return dao.getList(sql.toString(),
				dao.unionArray(inputV, mQuery.getInputList()), colList);
		//return CommonQueryDAO.commonQuery(sql.toString(), mQuery.getQueryString()+query,
		//	dao.unionArray(inputV, mQuery.getInputList()), colList, model);
	}
	/**
	 * 学生证批量注册
	 * @param model
	 * @param colList
	 * @param lcmc
	 * @return
	 * @throws Exception
	 */
	public boolean plHcyhkff(RcswForm model) throws Exception{
		
		String[] pkValue = model.getPkV();
		String[] sql = new String[pkValue.length];
		User user=model.getUser();
		CommDAO dao = new CommDAO();
		
		String xn=Base.currXn;
		String xq=Base.currXq;
		String nd=Base.currNd;
		String jbr=user.getUserName();
		String[]xh=pkValue;
		String qdz=model.getQdz();
		String zdz=model.getZdz();
		String bz=model.getBz();
		
		for (int i = 0; i < pkValue.length; i++) {
			
			sql[i] = " insert into hcyhkffb(xh,xn,xq,nd,jbr,qdz,zdz,ffsj ";
			
			if(!Base.isNull(bz)){
				
				sql[i]+=",bz";
			}
			
			sql[i]+=" )values( ";
			sql[i]+="'"+xh[i]+"',";
			sql[i]+="'"+xn+"',";
			sql[i]+="'"+xq+"',";
			sql[i]+="'"+nd+"',";
			sql[i]+="'"+jbr+"',";
			sql[i]+="'"+qdz+"',";
			sql[i]+="'"+zdz+"',";
			sql[i]+="to_char(sysdate,'yyyyMMdd')";
			
			if(!Base.isNull(bz)){
				
				sql[i]+=",'"+bz+"'";
			}
			sql[i]+=")";
		}
		
		return dao.saveArrDate(sql);
	}
	
	/**
	 * 学生证取消注销
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean delHcyhkff(RcswForm model) throws Exception{
		
		String[] pkValue = model.getPkV();
		String[] sql = new String[pkValue.length];
		CommDAO dao = new CommDAO();
		
		for (int i = 0; i < pkValue.length; i++) {
			
			sql[i]=" delete from hcyhkffb where xh||ffsj='"+pkValue[i]+"' ";
		}
		
		return dao.saveArrDate(sql);
	}

	/**
	 * 获取学生证注册信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String>getHcyhkMap(RcswForm model){
		
		DAO dao=DAO.getInstance();
		String pkValue=model.getPkValue();
		StringBuilder sql=new StringBuilder();
		sql.append(" select a.xh||ffsj pkValue,a.xh,xn,xq,nd,jbr,(select xm from yhb where jbr=yhm)jbrxm  ");
		sql.append(" ,qdz,zdz,a.jtdz,a.bz,ffsj,b.xymc,b.zymc,b.bjmc,b.nj,b.xb,b.xm ");
		sql.append(" from hcyhkffb  a left join view_xsjbxx b ");
		sql.append(" on a.xh=b.xh where a.xh||ffsj =? ");
		return dao.getMap(sql.toString(), new String[]{pkValue}, new String[]{"pkValue","xh",
			"xn","xq","nd","jbr","jbrxm","ffsj","bz","jtdz","qdz","zdz","nj",
			"xymc","zymc","bjmc","xm","xb"});
	}
	
	
	/**
	 * 火车优惠卡(2011.12.26 qlj)
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean addHcyhk(RcswForm myForm) throws Exception {
		
		DAO dao=DAO.getInstance();
		User user = myForm.getUser();
		String xn=Base.currXn;
		String xq=Base.currXq;
		String nd=Base.currNd;
		String xh=myForm.getXh();
		String jbr=user.getUserName();
		String bz=myForm.getBz();
		String jtdz=myForm.getJtdz();
		String qdz=myForm.getQdz();
		String zdz = myForm.getZdz();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" insert into hcyhkffb(xn,xq,xh,bz,nd,ffsj,jbr,jtdz,qdz,zdz)values ");
		sql.append(" (?,?,?,?,?,to_char(sysdate,'yyyyMMdd'),?,?,?,?) ");
		
		return dao.runUpdate(sql.toString(),new String[]{xn,xq,xh,bz,nd,jbr,jtdz,qdz,zdz});
	}
	
	/**
	 * 修改火车优惠卡(2011.12.26 qlj)
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateHcyhk(RcswForm myForm) throws Exception {
		
		DAO dao=DAO.getInstance();
		
		String pkValue=myForm.getPkValue();
		String bz=myForm.getBz();
		String jtdz=myForm.getJtdz();
		String qdz=myForm.getQdz();
		String zdz = myForm.getZdz();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" update hcyhkffb set  qdz=? ,zdz=?,jtdz=?,bz=? where xh||ffsj=?  ");
		
		return dao.runUpdate(sql.toString(),new String[]{qdz,zdz,jtdz,bz,pkValue});
	}
}
