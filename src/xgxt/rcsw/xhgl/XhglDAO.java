package xgxt.rcsw.xhgl;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class XhglDAO {
	
	
	
	// ====================================校徽发放 begin============================================
	/**
	 * 校徽管理查询
	 * @param model
	 * @param colList
	 * @param lcmc
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXhgl(XhglForm model) throws Exception{
		StringBuilder sql = new StringBuilder();
		StringBuilder query=new StringBuilder();
		String[] queryArr = new String[]{"nj","xydm","zydm","bjdm","sfff"};
		String[] queryLikeArr = new String[]{"xh","xm"};
		User user=model.getUser();
		String isFdy=user.getFdyQx();
		String isBzr= user.getBzrQx();
		String userName=user.getUserName();
		String userType=user.getUserType();
		String userDep=user.getUserDep();
		
		MakeQuery mQuery=new MakeQuery();
		mQuery.makeQuery(queryArr, queryLikeArr, model);
		sql.append(" select rownum r,a.* from ( ");
		sql.append(" select a.xh||ffsj pkValue,ffsj,a.xh,a.xm,a.xb,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc, ");
		sql.append("(select jbrxm from jbrxxb where jbrgh=jbr)jbrxm, ");
		sql.append(" case when b.xh is null then '否' else '是' end sfff ");
		sql.append(" from view_xsjbxx a left join xhffb b on a.xh=b.xh ");
		sql.append(" )a ");
		
		String []colList={"pkValue","xh","xm","nj","xymc","zymc","bjmc","jbrxm","ffsj","sfff"};
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
		return CommonQueryDAO.commonQuery(sql.toString(), mQuery.getQueryString()+query,
			mQuery.getInputList(), colList, model);
	}
	
	/**
	 * 校徽批量发放
	 * @param model
	 * @param colList
	 * @param lcmc
	 * @return
	 * @throws Exception
	 */
	public boolean xhffBatch(XhglForm model) throws Exception{
		
		String[] pkValue = model.getPkV();
		String[] sql = new String[pkValue.length];
		User user=model.getUser();
		CommDAO dao = new CommDAO();
		
		String xn=Base.currXn;
		String xq=Base.currXq;
		String nd=Base.currNd;
		String jbr=model.getSelect_jbr();
		String[]xh=pkValue;
		String bz=model.getBz();
		String ffsj=model.getFfsj();
		
		for (int i = 0; i < pkValue.length; i++) {
			
			sql[i] = " insert into xhffb(xh,xn,xq,nd,jbr,ffsj ";
			
			if(!Base.isNull(bz)){
				
				sql[i]+=",bz";
			}
			
			sql[i]+=" )values( ";
			sql[i]+="'"+xh[i]+"',";
			sql[i]+="'"+xn+"',";
			sql[i]+="'"+xq+"',";
			sql[i]+="'"+nd+"',";
			sql[i]+="'"+jbr+"',";
			sql[i]+="'"+ffsj+"'";
			
			if(!Base.isNull(bz)){
				
				sql[i]+=",'"+bz+"'";
			}
			sql[i]+=")";
		}
		
		return dao.saveArrDate(sql);
	}
	
	/**
	 * 取消发放（批量）
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean qxxhffBatch(XhglForm model) throws Exception{
		
		String[] pkValue = model.getPkV();
		String[] sql = new String[pkValue.length];
		CommDAO dao = new CommDAO();
		
		for (int i = 0; i < pkValue.length; i++) {
			
			sql[i]=" delete from xhffb where xh||ffsj='"+pkValue[i]+"' ";
		}
		
		return dao.saveArrDate(sql);
	}
	

	/**
	 * 单个增加校徽发放(2011.12.26 qlj)
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean addXhff(XhglForm myForm) throws Exception {
		
		DAO dao=DAO.getInstance();
		User user = myForm.getUser();
		String xn=Base.currXn;
		String xq=Base.currXq;
		String nd=Base.currNd;
		String xh=myForm.getXh();
		String jbr=user.getUserName();
		String bz=myForm.getBz();
		String ffsj=myForm.getFfsj();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" insert into xhffb(xn,xq,xh,bz,nd,ffsj,jbr)values ");
		sql.append(" (?,?,?,?,?,?,?) ");
		
		return dao.runUpdate(sql.toString(),new String[]{xn,xq,xh,bz,nd,ffsj,jbr});
	}
	
	/**
	 * 修改校徽发放(2011.12.26 qlj)
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateXhff(XhglForm myForm) throws Exception {
		
		DAO dao=DAO.getInstance();
		String pkValue=myForm.getPkValue();
		String bz=myForm.getBz();
		String jbr=myForm.getSelect_jbr();
		StringBuilder sql=new StringBuilder();
		
		sql.append(" update xhffb set bz = ?,jbr=? ");
		sql.append(" where xh||ffsj=?  ");
		
		return dao.runUpdate(sql.toString(),new String[]{bz,jbr,pkValue});
	}
	
	/**
	 * 获取校徽发放记录(2011.12.26 qlj)
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public HashMap<String,String> getXhglMap(XhglForm myForm) throws Exception {
		
		DAO dao=DAO.getInstance();
		String pkValue=myForm.getPkValue();
		StringBuilder sql=new StringBuilder();
		sql.append(" select a.xh||ffsj pkValue,a.xh,xn,xq,nd,jbr,(select jbrxm from jbrxxb where jbrgh=jbr)jbrxm  ");
		sql.append(" ,a.bz,ffsj,b.xymc,b.zymc,b.bjmc,b.nj,b.xb,b.xm ");
		sql.append(" from xhffb  a left join view_xsjbxx b ");
		sql.append(" on a.xh=b.xh where a.xh||ffsj =? ");
		return dao.getMap(sql.toString(), new String[]{pkValue}, new String[]{"pkValue","xh",
			"xn","xq","nd","jbr","jbrxm","ffsj","bz","nj",
			"xymc","zymc","bjmc","xm","xb"});
	}
	//	====================================校徽发放 end============================================	
	

}
