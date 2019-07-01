package xgxt.pjpy.szyqxy.zhszcp;

import java.io.OutputStream;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;

import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;
public class PjpySzyqxyZhszcpDAO {

	private DAO dao = DAO.getInstance();
	
	@SuppressWarnings("unused")
	private ArrayList<String> values = null;
	
	public static PjpySzyqxyZhszcpDAO mydao = new PjpySzyqxyZhszcpDAO();
	
	public static PjpySzyqxyZhszcpDAO getInstance() {
		return mydao;
	}
	/**
	 * 获取表头
	 * @param en
	 * @param cn
	 * @return
	 */
	public List<HashMap<String, String>>  getTitle(String[] en, String[] cn){
		return  dao.arrayToList(en, cn);
	}
	/**
	 * 获取相关信息
	 * @param table 表名
	 * @param querry 条件
	 * @return
	 */
	public HashMap<String,String> dao_getInfo(String table,String querry){
		DAO dao = DAO.getInstance();
		String[] colList =  dao.getColumnName("select xh,xb,xm,nj,xymc,zymc,bjmc from "+table);
		for(int i=0;i<colList.length;i++){
			colList[i]=colList[i].toLowerCase();
		}
		String sql = "select xh,xb,xm,nj,xymc,zymc,bjmc from "+table;
		return dao.getMap(sql+querry.toString(),new String[]{},colList);
	}
	/**
	 * 组织能力活动审核查询
	 */
	public ArrayList<String[]> dao_zznlChkSearch(ZhszcpModel model){
		ArrayList<String[]> result = new ArrayList<String[]>();
		String xh = model.getXh();
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xm   = model.getXm();
		String xn   = model.getXn();
		String xq   = model.getXq();
		xh = Base.isNull(xh) ? "%" : xh ;
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";		
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		StringBuffer querry2 = new StringBuffer(" where 1=1 ");
		querry.append(" and a.xh like ?");
		querry.append(" and xm like ?");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
		querry2.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry2.append(Base.isNull(xn)?"":" and xq='"+xq+"' ");	
		String[] colList = new String[]{"pk","xn","xq","xh","xm","nj","xymc","bjmc","zf","hdf","sh"};
		StringBuffer  sql = new StringBuffer();
		sql.append("select rownum r,a.xh||a.xn||a.xq pk,a.xn,(select xqmc from xqdzb d where a.xq=d.xqdm)xq,a.xh,nj,xydm,zydm,bjdm,xm,xymc,bjmc,b.zf,nvl(c.hdf,0) hdf, ");
		sql.append("( case when (select count(xh) from szyc_zznlfzb d "+querry2+" and d.xh=a.xh and xxsh='通过')=a.cout then '全部通过' ");
		sql.append("when (select count(xh) from szyc_zznlfzb d "+querry2+" and d.xh=a.xh and xxsh='未审核')=a.cout then '未审核'  ");
		sql.append("when (select count(xh) from szyc_zznlfzb d "+querry2+" and d.xh=a.xh and xxsh='不通过')=a.cout then '全部不通过' else '部分通过' end  ");
		sql.append(")sh from (select a.xn,a.xq,a.xh,nj,xydm,zydm,bjdm,xm,xymc,bjmc, ");
		sql.append("(select count(xh) from szyc_zznlfzb d  "+querry2+"  and d.xh=a.xh )cout ");
		sql.append("from (select  distinct xh,xn,xq from szyc_zznlfzb  "+querry2+" )a left join view_xsxxb b on a.xh=b.xh )a ");
		
		sql.append("left join (select xh, xn, xq, ");
		sql.append("sum(pf) zf from (select xh, xn, xq, case when jjf = '加分' then shfz else '-' || shfz end pf ");
		sql.append("from szyc_zznlfzb) group by xh, xn, xq) b on a.xn = b.xn and a.xq = b.xq and a.xh = b.xh ");
		
		sql.append("left join (select xh, xn,xq, ");
		sql.append("sum(pf) hdf from (select xh, xn, xq, case when jjf = '加分' then shfz else '-' || shfz end pf ");
		sql.append("from szyc_zznlfzb where xxsh='通过') group by xh, xn, xq) c on a.xn = c.xn and a.xq = c.xq and a.xh = c.xh");
		
		try {
			result = CommonQueryDAO.commonQuery(sql.toString(), querry.toString(), new String[]{xh,xm},colList, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}	
	/**
	 * 组织能力活动审核查询
	 */
	public ArrayList<String[]> dao_xthdChkSearch(ZhszcpModel model,String xxk){
		ArrayList<String[]> result = new ArrayList<String[]>();
		String xh = model.getXh();
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xm   = model.getXm();
		String xn   = model.getXn();
		String xq   = model.getXq();
		String tableName = "szyq_dshdjzb";
		if("ivlt".equals(xxk)){
			tableName = "szyq_ivtltb";
		}else if("wthd".equals(xxk)){
			tableName = "szyq_xthddjb";
		}else if("yybd".equals(xxk)){
			tableName = "szyq_yybdjzb";
		}else{
			tableName = "szyq_dshdjzb";
		}
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		StringBuffer querry2 = new StringBuffer(" where 1=1 ");
		querry.append(Base.isNull(xh)?"":" and a.xh='"+xh.trim()+"' ");
		querry.append(Base.isNull(nj)?"":" and a.nj='"+nj+"' ");
		querry.append(Base.isNull(xydm)?"":" and a.xydm='"+xydm+"' ");
		querry.append(Base.isNull(zydm)?"":" and a.zydm='"+zydm+"' ");
		querry.append(Base.isNull(bjdm)?"":" and a.bjdm='"+bjdm+"' ");
		querry.append(Base.isNull(xm)?"":" and a.xm like '%"+xm.trim()+"%' ");	
		querry2.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry2.append(Base.isNull(xn)?"":" and xq='"+xq+"' ");	
		String[] colList = new String[]{"pk","xn","xq","xh","xm","nj","xymc","bjmc","zf","hdf","sh"};
		StringBuffer  sql = new StringBuffer();
		sql.append("select rownum r,a.xh||a.xn||a.xq pk,a.xn,(select xqmc from xqdzb d where a.xq=d.xqdm)xq,a.xh,a.nj,a.xydm,a.zydm,a.bjdm,a.xm,a.xymc,a.bjmc,b.zf,nvl(c.hdf,0) hdf, ");
		sql.append("( case when (select count(xh) from "+tableName+" d "+querry2+" and d.xh=a.xh and xxsh='通过')=a.cout then '全部通过' ");
		sql.append("when (select count(xh) from "+tableName+" d "+querry2+" and d.xh=a.xh and xxsh='未审核')=a.cout then '未审核'  ");
		sql.append("when (select count(xh) from "+tableName+" d "+querry2+" and d.xh=a.xh and xxsh='不通过')=a.cout then '全部不通过' else '部分通过' end  ");
		sql.append(")sh from (select a.xn,a.xq,a.xh,nj,xydm,zydm,bjdm,xm,xymc,bjmc, ");
		sql.append("(select count(xh) from "+tableName+" d  "+querry2+"  and d.xh=a.xh )cout ");
		sql.append("from (select  distinct xh,xn,xq from "+tableName+"  "+querry2+" )a left join view_xsxxb b on a.xh=b.xh )a ");
		
		sql.append("left join (select xh, xn, xq, ");
		sql.append("sum(pf) zf from (select xh, xn, xq, case when jjf = '加分' then pf else '-' || pf end pf ");
		sql.append("from "+tableName+") group by xh, xn, xq) b on a.xn = b.xn and a.xq = b.xq and a.xh = b.xh ");
		
		sql.append("left join (select xh, xn,xq, ");
		sql.append("sum(pf) hdf from (select xh, xn, xq, case when jjf = '加分' then pf else '-' || pf end pf ");
		sql.append("from "+tableName+" where xxsh='通过') group by xh, xn, xq) c on a.xn = c.xn and a.xq = c.xq and a.xh = c.xh");
		//System.out.println(sql);
		result = null;
		
		try {
			result = CommonQueryDAO.commonQuery(sql.toString(), querry.toString(), new String[]{},colList, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}	
	/**
	 * 组织能力活动信息列表
	 */
	public ArrayList<String[]> dao_zznlViewAndChkSearch(String xh,String xn,String xq){
        StringBuffer sql = new StringBuffer();
        String[]colList=new String[]{"id","r","hdzt","hdrq","hddj","jjf","shfz","xxsh"};
        sql.append("select id, rownum r,hdzt,hddj,hdpf,hdrq,jjf,shfz,xxsh from szyc_zznlfzb where xh=? and xn=? and xq=?");
        return dao.rsToVator(sql.toString(), new String[]{xh,xn,xq},colList);
	}
	/**
	 * 学团活动活动信息列表
	 */
	public ArrayList<String[]> dao_zznlViewAndChkSearch(String xh,String xn,String xq,String xxk){
        StringBuffer sql = new StringBuffer();
        String tableName = "szyq_dshdjzb";
        String[]colList;
		if("ivlt".equals(xxk)){
			tableName = "szyq_ivtltb";
			 colList=new String[]{"id","r","jztm","xthdrq","jcdj","ccdj","jjf","pf","xxsh"};
		     sql.append("select id, rownum r,jztm,xthdrq,jcdj,ccdj,jjf,pf,xxsh from "+tableName+" where xh=? and xn=? and xq=?");
		}else if("wthd".equals(xxk)){
			tableName = "szyq_xthddjb";
			 colList=new String[]{"id","r","hdnr","xthdrq","jldj","jjf","pf","xxsh"};
		     sql.append("select id, rownum r,hdnr,xthdrq,jldj,jjf,pf,xxsh from "+tableName+" where xh=? and xn=? and xq=?");
		}else if("yybd".equals(xxk)){
			tableName = "szyq_yybdjzb";
			 colList=new String[]{"id","r","yybdnr","xthdrq","jjf","pf","xxsh"};
		     sql.append("select id, rownum r,yybdnr,xthdrq,jjf,pf,xxsh from "+tableName+" where xh=? and xn=? and xq=?");
		}else{
			tableName = "szyq_dshdjzb";
			 colList=new String[]{"id","r","dsmc","dsrq","dsxd","sfhj","jjf","pf","xxsh"};
		     sql.append("select id, rownum r,dsmc,dsrq,dsxd,sfhj,jjf,pf,xxsh from "+tableName+" where xh=? and xn=? and xq=?");
		}
        return dao.rsToVator(sql.toString(), new String[]{xh,xn,xq},colList);
	}
	/**
	 * 组织能力活动审核
	 */
	public boolean dao_zznlChk(String pkVStr,String shType) throws Exception{
		DAO dao = DAO.getInstance();	
		shType = ("yes".equalsIgnoreCase(shType))?"通过":"不通过";
		String[] pkValueA = pkVStr.split("!!");		
		String[] updtPkSql  = new String[pkValueA.length];	
		for (int i = 0; i < pkValueA.length; i++) {
			updtPkSql[i] = Base.isNull(pkValueA[i])?"":"update szyc_zznlfzb set xxsh='"+shType+"'  where id= '"+pkValueA[i]+"'";							
		}              
		boolean doFlag = false;
		int[] array = dao.runBatch(updtPkSql);
		doFlag = dao.checkBatch(array);   
		return doFlag;
	}
	/**
	 * 文团活动审核
	 */
	public boolean dao_wthdChk(String pkVStr,String shType,String xxk) throws Exception{
		DAO dao = DAO.getInstance();	
		shType = ("yes".equalsIgnoreCase(shType))?"通过":"不通过";
		String[] pkValueA = pkVStr.split("!!");		
		String[] updtPkSql  = new String[pkValueA.length];	
		String tableName = "szyq_dshdjzb";
        if("ivlt".equals(xxk)){
			tableName = "szyq_ivtltb";
		}else if("wthd".equals(xxk)){
			tableName = "szyq_xthddjb";
		}else if("yybd".equals(xxk)){
			tableName = "szyq_yybdjzb";
		}else{
			tableName = "szyq_dshdjzb";
		}
		for (int i = 0; i < pkValueA.length; i++) {
			updtPkSql[i] = Base.isNull(pkValueA[i])?"":"update "+tableName+" set xxsh='"+shType+"'  where id= '"+pkValueA[i]+"'";							
		}              
		boolean doFlag = false;
		int[] array = dao.runBatch(updtPkSql);
		doFlag = dao.checkBatch(array);   
		return doFlag;
	}

	//组织能力增加
	public String dao_addZznl(ZznlModel model,HttpServletRequest request){
		String sFlag = "";
		dao = DAO.getInstance();
		String[] sqlList = new String[model.getHdzt().length+1];
		StringBuffer sql = null;
		try {
			StringBuffer buff = new StringBuffer();
			buff.append(model.getXh()[0]).append(model.getXn()).append(model.getXq());
			StandardOperation.delete("szyc_zznlfzb", "xh||xn||xq", buff.toString(), request);
		} catch (Exception e1) {
			// TODO 自动生成 catch 块
			e1.printStackTrace();
		}
		for (int i = 0; i < model.getHdzt().length; i++) {
			sql = new StringBuffer();
			sql.append("insert into szyc_zznlfzb (xh,xn,xq,hdzt,hddj,hdrq,hdpf,shfz,jjf,xxsh) values('");
			sql.append(model.getXh()[0]);
			sql.append("','");
			sql.append(model.getXn());
			sql.append("','");
			sql.append(model.getXq());
			sql.append("','");
			sql.append(DealString.toGBK(model.getHdzt()[i]).replace("'", "‘"));
			sql.append("','");
			sql.append(DealString.toGBK(model.getHddj()[i]).replace("'", "‘"));
			sql.append("','");
			sql.append(DealString.toGBK(model.getHdrq()[i]));
			sql.append("','");
			//sql.append(DealString.toGBK(model.getHdpf()[i]));
			sql.append("','");
			sql.append(DealString.toGBK(model.getShfz()[i]));
			sql.append("','");
			sql.append(DealString.toGBK(model.getJjf()[i]));
			sql.append("','");
			sql.append(DealString.toGBK(model.getXxsh()[i]));
			sql.append("')");
			if (StringUtils.isNull(model.getHdzt()[i])) {//为空则不记录
				sql = new StringBuffer("");				
			}
			sqlList[i] = sql.toString();
		}
		//String updateSql = "update sxzzddszb set shzt = '未审核' where xh = '"+model.getXh()+"' and xn = '"+model.getXn()+"'";
		//sqlList[model.getFs().length] = updateSql;
		int[] iFlag = null;
		try {
			iFlag = dao.runBatch(sqlList);
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}//批量插入
		for (int i=0;i<iFlag.length;i++) {
			if(iFlag[i] <= -1){//记录操作失败的行号
				sFlag = (iFlag[i] + 1) + ",";
			}
		}
		if (!StringUtils.isNull(sFlag)) {
			return sFlag.substring(0, sFlag.length() - 1);
		} else {
			return "";
		}
	}
	
	//组织能力查询
	public ArrayList<String[]> dao_QueryZznl(ZznlModel model,String userType,String userName){
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		if (model.getXh() != null && model.getXh().length > 0) {
			querry.append(" and a.xh='" + model.getXh()[0].trim() + "' ");
		}
		querry.append(Base.isNull(model.getXn())?"":" and a.xn='"+model.getXn()+"' ");
		querry.append(Base.isNull(model.getNj())?"":" and a.nj='"+model.getNj()+"' ");
		querry.append(Base.isNull(model.getXydm())?"":" and a.xydm='"+model.getXydm()+"' ");
		querry.append(Base.isNull(model.getZydm())?"":" and a.zydm='"+model.getZydm()+"' ");
		querry.append(Base.isNull(model.getBjdm())?"":" and a.bjdm='"+model.getBjdm()+"' ");
		querry.append(Base.isNull(model.getXm())?"":" and a.xm like '%"+model.getXm()+"%' ");
		querry.append(Base.isNull(model.getXq())?"":" and a.xq='"+getXqmc(model.getXq())+"' ");
		querry.append("bzr".equalsIgnoreCase(userType) ? " and exists (select 1 from fdybjb b where zgh like '"+ userName + "' and a.bjdm = b.bjdm)": "");	
		
		StringBuffer sb = new StringBuffer();
		sb.append("left join (select xh, xn, (select xqmc from xqdzb where xq = xqdm) xq, ");
		sb.append("sum(shfz) zf from (select xh, xn, xq, case when jjf = '加分' then shfz else '-' || shfz end shfz ");
		sb.append("from szyc_zznlfzb) group by xh, xn, xq) b on a.xn = b.xn and a.xq = b.xq and a.xh = b.xh ");
		
		sb.append("left join (select xh, xn, (select xqmc from xqdzb where xq = xqdm) xq, ");
		sb.append("sum(shfz) hdf from (select xh, xn, xq, case when jjf = '加分' then shfz else '-' || shfz end shfz ");
		sb.append("from szyc_zznlfzb where xxsh='通过') group by xh, xn, xq) c on a.xn = c.xn and a.xq = c.xq and a.xh = c.xh");
		
		String sql = "select a.*,b.zf,nvl(c.hdf,0) hdf from (select a.xh||xn||xq pk,a.xh,xn,(select xqmc from xqdzb b where a.xq=b.xqdm) xq,xm,nj,xydm,xymc,zydm,zymc,bjdm,bjmc from "
				+ "(select distinct xh,xn,xq from szyc_zznlfzb) a left outer join view_xsxxb b on a.xh=b.xh) a "
				+ sb.toString() + querry.toString();
		String[] inputValue = {} ;
		String[] outputValue = {"pk","xh","xn","xq","xm","xymc","bjmc","zf","hdf" };
		ArrayList<String[]> rs = dao.rsToVator(sql, inputValue, outputValue);

		return rs;
	}
	//学团活动查询
	public ArrayList<String[]> dao_QueryXthd(XthdModel model,String xxk){
		
		String userName = model.getUserName();
		String userType = model.getUserType();
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		String bzr = " and exists (select 1 from fdybjb b where zgh like '"
			+ userName + "' and a.bjdm = b.bjdm)";
		if (model.getXh() != null && model.getXh().length > 0) {
			querry.append(" and a.xh='"+ model.getXh()[0].trim() + "' ");
		}
		querry.append(Base.isNull(model.getXn())?"":" and a.xn='"+model.getXn()+"' ");
		querry.append(Base.isNull(model.getNj())?"":" and a.nj='"+model.getNj()+"' ");
		querry.append(Base.isNull(model.getXydm())?"":" and a.xydm='"+model.getXydm()+"' ");
		querry.append(Base.isNull(model.getZydm())?"":" and a.zydm='"+model.getZydm()+"' ");
		querry.append(Base.isNull(model.getBjdm())?"":" and a.bjdm='"+model.getBjdm()+"' ");
		querry.append(Base.isNull(model.getXm())?"":" and a.xm like '%"+model.getXm()+"%' ");
		querry.append(Base.isNull(model.getXq())?"":" and a.xq='"+getXqmc(model.getXq())+"' ");
		querry.append("bzr".equalsIgnoreCase(userType)?bzr:"");
		String sql="";
		String[] outputValue;
		StringBuffer sb = new StringBuffer();
		if("ivlt".equals(xxk)){
			
			sb.append("left join (select xh, xn, (select xqmc from xqdzb where xq = xqdm) xq, ");
			sb.append("sum(pf) zf from (select xh, xn, xq, case when jjf = '加分' then pf else '-' || pf end pf ");
			sb.append("from szyq_ivtltb) group by xh, xn, xq) b on a.xn = b.xn and a.xq = b.xq and a.xh = b.xh ");
			
			sb.append("left join (select xh, xn, (select xqmc from xqdzb where xq = xqdm) xq, ");
			sb.append("sum(pf) hdf from (select xh, xn, xq, case when jjf = '加分' then pf else '-' || pf end pf ");
			sb.append("from szyq_ivtltb where xxsh='通过') group by xh, xn, xq) c on a.xn = c.xn and a.xq = c.xq and a.xh = c.xh");
			
			sql = "select rownum r,a.*,b.zf,nvl(c.hdf,0) hdf from (select a.xh||xn||xq pk,a.xh,xn,(select xqmc from xqdzb b where a.xq=b.xqdm) xq,xm,nj,xydm,xymc,zydm,zymc,bjdm,bjmc from "
					+ "(select distinct xh,xn,xq from szyq_ivtltb) a left outer join view_xsxxb b on a.xh=b.xh) a "
					+ sb.toString() + querry.toString();
			outputValue = new String[] { "pk", "xh", "xn", "xq", "xm", "xymc","bjmc","zf","hdf" };
		}else if("wthd".equals(xxk)){
			
			sb.append("left join (select xh, xn, (select xqmc from xqdzb where xq = xqdm) xq, ");
			sb.append("sum(pf) zf from (select xh, xn, xq, case when jjf = '加分' then pf else '-' || pf end pf ");
			sb.append("from szyq_xthddjb) group by xh, xn, xq) b on a.xn = b.xn and a.xq = b.xq and a.xh = b.xh ");
			
			sb.append("left join (select xh, xn, (select xqmc from xqdzb where xq = xqdm) xq, ");
			sb.append("sum(pf) hdf from (select xh, xn, xq, case when jjf = '加分' then pf else '-' || pf end pf ");
			sb.append("from szyq_xthddjb where xxsh='通过') group by xh, xn, xq) c on a.xn = c.xn and a.xq = c.xq and a.xh = c.xh");
			
			sql = "select rownum r,a.*,b.zf,nvl(c.hdf,0) hdf  from (select a.xh||xn||xq pk,a.xh,xn,(select xqmc from xqdzb b where a.xq=b.xqdm) xq,xm,nj,xydm,xymc,zydm,zymc,bjdm,bjmc from "
					+ "(select distinct xh,xn,xq from szyq_xthddjb) a left outer join view_xsxxb b on a.xh=b.xh) a "
					+ sb.toString() + querry.toString();
			outputValue = new String[]{"pk","xh","xn","xq","xm","xymc","bjmc","zf","hdf"};
		}else if("yybd".equals(xxk)){
			
			sb.append("left join (select xh, xn, (select xqmc from xqdzb where xq = xqdm) xq, ");
			sb.append("sum(pf) zf from (select xh, xn, xq, case when jjf = '加分' then pf else '-' || pf end pf ");
			sb.append("from szyq_yybdjzb) group by xh, xn, xq) b on a.xn = b.xn and a.xq = b.xq and a.xh = b.xh ");
			
			sb.append("left join (select xh, xn, (select xqmc from xqdzb where xq = xqdm) xq, ");
			sb.append("sum(pf) hdf from (select xh, xn, xq, case when jjf = '加分' then pf else '-' || pf end pf ");
			sb.append("from szyq_yybdjzb where xxsh='通过') group by xh, xn, xq) c on a.xn = c.xn and a.xq = c.xq and a.xh = c.xh");
			
			sql = "select rownum r,a.*,b.zf,nvl(c.hdf,0) hdf from (select a.xh||xn||xq pk,a.xh,xn,(select xqmc from xqdzb b where a.xq=b.xqdm) xq,xm,nj,xydm,xymc,zydm,zymc,bjdm,bjmc from "
					+ "(select distinct xh,xn,xq from szyq_yybdjzb) a left outer join view_xsxxb b on a.xh=b.xh) a "
					+ sb.toString() + querry.toString();
			outputValue = new String[]{"pk","xh","xn","xq","xm","xymc","bjmc","zf","hdf"};
		}else{
			
			sb.append("left join (select xh, xn, (select xqmc from xqdzb where xq = xqdm) xq, ");
			sb.append("sum(pf) zf from (select xh, xn, xq, case when jjf = '加分' then pf else '-' || pf end pf ");
			sb.append("from szyq_dshdjzb) group by xh, xn, xq) b on a.xn = b.xn and a.xq = b.xq and a.xh = b.xh ");
			
			sb.append("left join (select xh, xn, (select xqmc from xqdzb where xq = xqdm) xq, ");
			sb.append("sum(pf) hdf from (select xh, xn, xq, case when jjf = '加分' then pf else '-' || pf end pf ");
			sb.append("from szyq_dshdjzb where xxsh='通过') group by xh, xn, xq) c on a.xn = c.xn and a.xq = c.xq and a.xh = c.xh");
			
			sql = "select rownum r,a.*,b.zf,nvl(c.hdf,0) hdf from (select a.xh||xn||xq pk,a.xh,xn,(select xqmc from xqdzb b where a.xq=b.xqdm) xq,xm,nj,xydm,xymc,zydm,zymc,bjdm,bjmc from "
					+ "(select distinct xh,xn,xq from szyq_dshdjzb) a left outer join view_xsxxb b on a.xh=b.xh) a "
					+ sb.toString() + querry.toString();
			
			outputValue = new String[]{"pk","xh","xn","xq","xm","xymc","bjmc","zf","hdf"};
		}
		String[] inputValue = {} ;
		ArrayList<String[]> rs = dao.rsToVator(sql, inputValue, outputValue);
		return rs;
	}
	//学号刷学生信息
	public HashMap<String,String> dao_getXsxxInfo(String xh){
		HashMap<String,String> map = new HashMap<String, String>();
		String sql = "select * from view_xsxxb where xh=?";
		String[] output = new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc"};
		String[] outputValue = new String[output.length];
		if(StringUtils.isArrayNotNull(output)){
			for(int i = 0;i<output.length;i++){
				outputValue[i] = output[i].toLowerCase();
			}
		}
		map = dao.getMap(sql, new String[]{xh}, outputValue);
		return map;
	}
	//获得学生组织能力信息
	public ArrayList<HashMap<String, String>> dao_getXszznl(String pk){
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		String sql = "select rownum rnum,a.* from szyc_zznlfzb a where a.xh||a.xn||a.xq=?";
		String[] outputValue = {"rnum","hdzt","hddj","hdpf","hdrq","shfz","jjf"};
		rs = dao.getArrayList(sql, new String[]{pk}, outputValue);
		return rs;
	}
	//获得学生组织能力删除
	public String dao_getZznlDel(String[] keys){

		StringBuffer sb = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// 得到主键
			sql = "delete from szyc_zznlfzb where xh||xn||xq = '" + pk + "'";
			// 把主键组合成sql语句
			sb.append(sql).append("!!#!!");
		}
		// sql语句拆分成数组
		pksql = sb.toString().split("!!#!!");
		int[] judge = null;
		try {
			judge = dao.runBatch(pksql);
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		String whichpk = "";
		// 检查哪一条删除失败
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] < 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
			}
		}
		return whichpk;
	}
	
	//学团活动删除
	public String dao_getXthdDel(String[] keys,String xxk){

		StringBuffer sb = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		String tableName="";
		if("ivlt".equals(xxk)){
			tableName = "szyq_ivtltb";
		}else if("wthd".equals(xxk)){
			tableName = "szyq_xthddjb";
		}else if("yybd".equals(xxk)){
			tableName = "szyq_yybdjzb";
		}else if("dshd".equals(xxk)){
			tableName = "szyq_dshdjzb";
		}
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// 得到主键
			sql = "delete from "+tableName+" where xh||xn||xq = '" + pk + "'";
			// 把主键组合成sql语句
			sb.append(sql).append("!!#!!");
		}
		// sql语句拆分成数组
		pksql = sb.toString().split("!!#!!");
		int[] judge = null;
		try {
			judge = dao.runBatch(pksql);
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		String whichpk = "";
		// 检查哪一条删除失败
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] < 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
			}
		}
		return whichpk;
	}
	/**
	 * 获取查询表头
	 * @param colListCN
	 * @return
	 */
	public ArrayList<HashMap<String, String>> dao_getSearchTitle(String[] colListCN ) {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < colListCN.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
		
		//学团活动
	public String dao_addXthd(XthdModel model,HttpServletRequest request,String xxk) throws Exception{
		String sFlag = "";
		dao = DAO.getInstance();
		String[] sqlList = null ;
		StringBuffer sql = null;
		String tableName = "szyq_dshdjzb";
		if("ivlt".equals(xxk)){
			tableName = "szyq_ivtltb";
			sqlList = new String[model.getJztm().length+1];
		}else if("wthd".equals(xxk)){
			tableName = "szyq_xthddjb";
			sqlList = new String[model.getHdnr().length+1];
		}else if("yybd".equals(xxk)){
			tableName = "szyq_yybdjzb";
			sqlList = new String[model.getYybdnr().length+1];
		}else{
			tableName = "szyq_dshdjzb";
			sqlList = new String[model.getDsmc().length+1];
		}
		if("szyq_dshdjzb".equals(tableName)){
			StringBuffer buff = new StringBuffer();
			buff.append(model.getXh()[0]).append(model.getXn()).append(model.getXq());
			StandardOperation.delete("szyq_dshdjzb", "xh||xn||xq", buff.toString(), request);
			for (int i = 0; i < model.getDsmc().length; i++) {
				sql = new StringBuffer();
				sql.append("insert into szyq_dshdjzb (xh,xn,xq,dsmc,dsrq,dsxd,sfhj,jjf,pf,xxsh) values('");
				sql.append(model.getXh()[0]);
				sql.append("','");
				sql.append(model.getXn());
				sql.append("','");
				sql.append(model.getXq());
				sql.append("','");
				sql.append(DealString.toGBK(model.getDsmc()[i]).replace("'", "‘"));
				sql.append("','");
				sql.append(DealString.toGBK(model.getDsrq()[i]));
				sql.append("','");
				sql.append(DealString.toGBK(model.getDsxd()[i]).replace("'", "‘"));
				sql.append("','");
				sql.append(DealString.toGBK(model.getSfhj()[i]));
				sql.append("','");
				sql.append(DealString.toGBK(model.getJjf()[i]).replace("'", "‘"));
				sql.append("','");
				sql.append(DealString.toGBK(model.getPf()[i]).replace("'", "‘"));
				sql.append("','");
				sql.append(DealString.toGBK(model.getXxsh()[i]));
				sql.append("')");
				if (StringUtils.isNull(model.getDsmc()[i])) {//为空则不记录
					sql = new StringBuffer("");				
				}
				sqlList[i] = sql.toString();
			}
		}
		if("szyq_yybdjzb".equals(tableName)){
			StringBuffer buff = new StringBuffer();
			buff.append(model.getXh()[0]).append(model.getXn()).append(model.getXq());
			StandardOperation.delete("szyq_yybdjzb", "xh||xn||xq", buff.toString(), request);
			for (int i = 0; i < model.getYybdnr().length; i++) {
				sql = new StringBuffer();
				sql.append("insert into szyq_yybdjzb (xh,xn,xq,yybdnr,xthdrq,jjf,pf,xxsh) values('");
				sql.append(model.getXh()[0]);
				sql.append("','");
				sql.append(model.getXn());
				sql.append("','");
				sql.append(model.getXq());
				sql.append("','");
				sql.append(DealString.toGBK(model.getYybdnr()[i]).replace("'", "‘"));
				sql.append("','");
				sql.append(DealString.toGBK(model.getXthdrq()[i]));
				sql.append("','");
				sql.append(DealString.toGBK(model.getJjf()[i]));
				sql.append("','");
				sql.append(DealString.toGBK(model.getPf()[i]));
				sql.append("','");
				sql.append(DealString.toGBK(model.getXxsh()[i]));
				sql.append("')");
				if (StringUtils.isNull(model.getYybdnr()[i])) {//为空则不记录
					sql = new StringBuffer("");				
				}
				sqlList[i] = sql.toString();
			}
		}
		if("szyq_ivtltb".equals(tableName)){
			StringBuffer buff = new StringBuffer();
			buff.append(model.getXh()[0]).append(model.getXn()).append(model.getXq());
			StandardOperation.delete("szyq_ivtltb", "xh||xn||xq", buff.toString(), request);
			for (int i = 0; i < model.getJztm().length; i++) {
				sql = new StringBuffer();
				sql.append("insert into szyq_ivtltb (xh,xn,xq,jztm,xthdrq,jcdj,ccdj,jjf,pf,xxsh) values('");
				sql.append(model.getXh()[0]);
				sql.append("','");
				sql.append(model.getXn());
				sql.append("','");
				sql.append(model.getXq());
				sql.append("','");
				sql.append(DealString.toGBK(model.getJztm()[i]).replace("'", "‘"));
				sql.append("','");
				sql.append(DealString.toGBK(model.getXthdrq()[i]));
				sql.append("','");
				sql.append(DealString.toGBK(model.getJcdj()[i]).replace("'", "‘"));
				sql.append("','");
				sql.append(DealString.toGBK(model.getCcdj()[i]).replace("'", "‘"));
				sql.append("','");
				sql.append(DealString.toGBK(model.getJjf()[i]));
				sql.append("','");
				sql.append(DealString.toGBK(model.getPf()[i]));
				sql.append("','");
				sql.append(DealString.toGBK(model.getXxsh()[i]));
				sql.append("')");
				if (StringUtils.isNull(model.getJztm()[i])) {//为空则不记录
					sql = new StringBuffer("");				
				}
				sqlList[i] = sql.toString();
			}
		}
		if("szyq_xthddjb".equals(tableName)){
			StringBuffer buff = new StringBuffer();
			buff.append(model.getXh()[0]).append(model.getXn()).append(model.getXq());
			StandardOperation.delete("szyq_xthddjb", "xh||xn||xq", buff.toString(), request);
			for (int i = 0; i < model.getHdnr().length; i++) {
				sql = new StringBuffer();
				sql.append("insert into szyq_xthddjb (xh,xn,xq,hdnr,xthdrq,jldj,jjf,pf,xxsh) values('");
				sql.append(model.getXh()[0]);
				sql.append("','");
				sql.append(model.getXn());
				sql.append("','");
				sql.append(model.getXq());
				sql.append("','");
				sql.append(DealString.toGBK(model.getHdnr()[i]).replace("'", "‘"));
				sql.append("','");
				sql.append(DealString.toGBK(model.getXthdrq()[i]));
				sql.append("','");
				sql.append(DealString.toGBK(model.getJldj()[i]).replace("'", "‘"));
				sql.append("','");
				sql.append(DealString.toGBK(model.getJjf()[i]));
				sql.append("','");
				sql.append(DealString.toGBK(model.getPf()[i]));
				sql.append("','");
				sql.append(DealString.toGBK(model.getXxsh()[i]));
				sql.append("')");
				if (StringUtils.isNull(model.getHdnr()[i])) {//为空则不记录
					sql = new StringBuffer("");				
				}
				sqlList[i] = sql.toString();
			}
		}
		//String updateSql = "update sxzzddszb set shzt = '未审核' where xh = '"+model.getXh()+"' and xn = '"+model.getXn()+"'";
		//sqlList[model.getFs().length] = updateSql;
		int[] iFlag = null;
		try {
			iFlag = dao.runBatch(sqlList);
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}//批量插入
		for (int i=0;i<iFlag.length;i++) {
			if(iFlag[i] <= -1){//记录操作失败的行号
				sFlag = (iFlag[i] + 1) + ",";
			}
		}
		if (!StringUtils.isNull(sFlag)) {
			return sFlag.substring(0, sFlag.length() - 1);
		} else {
			return "";
		}
	}
	//获得学团活动信息
	public ArrayList<HashMap<String, String>> dao_getXsXthd(String pk,String xxk){
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		String sql="";
		String[] outputValue;
		if("ivlt".equals(xxk)){
			sql = "select rownum rnum,a.* from szyq_ivtltb a where a.xh||a.xn||a.xq=?";
			outputValue = new String[]{"rnum","jztm","xthdrq","jcdj","ccdj","jjf","pf"};
		}else if("wthd".equals(xxk)){
			sql = "select rownum rnum,a.* from szyq_xthddjb a where a.xh||a.xn||a.xq=?";
			outputValue = new String[]{"rnum","hdnr","xthdrq","jldj","jjf","pf"};
		}else if("yybd".equals(xxk)){
			sql = "select rownum rnum,a.* from szyq_yybdjzb a where a.xh||a.xn||a.xq=?";
			outputValue = new String[]{"rnum","yybdnr","xthdrq","jjf","pf"};
		}else{
			sql = "select rownum rnum,a.* from szyq_dshdjzb a where a.xh||a.xn||a.xq=?";
			outputValue = new String[]{"rnum","dsmc","dsrq","dsxd","sfhj","jjf","pf"};
		}
		
		rs = dao.getArrayList(sql, new String[]{pk}, outputValue);
		return rs;
	}
	//获得学团活动信息
	public ArrayList<HashMap<String, String>> dao_getXsXthdDwr(String xh,String xn,String xq,String xxk){
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		String sql="";
		String xsxq = "";
		try {
			xsxq = getXqdm(xq);
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		StringBuffer pk = new StringBuffer();
		pk.append(xh).append(xn).append(xsxq);
		String[] outputValue;
		if("ivlt".equals(xxk)){
			sql = "select rownum rnum,a.* from szyq_ivtltb a where a.xh||a.xn||a.xq=? order by xxsh";
			outputValue = new String[]{"rnum","jztm","xthdrq","jcdj","ccdj","xxsh","jjf","pf"};
		}else if("wthd".equals(xxk)){
			sql = "select rownum rnum,a.* from szyq_xthddjb a where a.xh||a.xn||a.xq=? order by xxsh";
			outputValue = new String[]{"rnum","hdnr","xthdrq","jldj","jjf","pf","xxsh"};
		}else if("yybd".equals(xxk)){
			sql = "select rownum rnum,a.* from szyq_yybdjzb a where a.xh||a.xn||a.xq=? order by xxsh";
			outputValue = new String[]{"rnum","yybdnr","xthdrq","jjf","pf","xxsh"};
		}else{
			sql = "select rownum rnum,a.* from szyq_dshdjzb a where a.xh||a.xn||a.xq=? order by xxsh";
			outputValue = new String[]{"rnum","dsmc","dsrq","dsxd","sfhj","jjf","pf","xxsh" };
		}
		
		rs = dao.getArrayList(sql, new String[]{pk.toString()}, outputValue);
		return rs;
	}
	//获得学团活动信息
	public ArrayList<HashMap<String, String>> dao_getZZnlDwr(String xh,String xn,String xq){
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		String sql="";
		String xsxq = "";
		try {
			xsxq = getXqdm(xq);
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		StringBuffer pk = new StringBuffer();
		pk.append(xh).append(xn).append(xsxq);
		String[] outputValue;
		sql = "select rownum rnum,a.* from szyc_zznlfzb a where a.xh||a.xn||a.xq=? order by xxsh";
		outputValue = new String[]{"rnum","hdzt","hdrq","hddj","hdpf","jjf","shfz","xxsh" };
		rs = dao.getArrayList(sql, new String[]{pk.toString()}, outputValue);
		return rs;
	}
	/**
	 * 获取班主任下属班级
	 * @param userName
	 * @return
	 * @throws SQLException 
	 */
	public List<String>  getBzrBjList(String userName) throws SQLException{
		String sql = "select bjdm from fdybjb where zgh=?";
		return  dao.getList(sql, new String[]{userName}, "bjdm");
	}
	
	/**
	 * 获取学期名称
	 * @param xqdm
	 * @return
	 * @throws SQLException 
	 */
	public String  getXqmc(String xqdm){
		String sql = "select xqmc from xqdzb where xqdm=?";
		return  dao.getOneRs(sql, new String[]{xqdm}, "xqmc");
	}
	/**
	 * 获取学期代码
	 * @param xqdm
	 * @return
	 * @throws SQLException 
	 */
	public String  getXqdm(String xqmc) throws SQLException{
		String sql = "select xqdm from xqdzb where xqmc like ?";
		return  dao.getOneRs(sql, new String[]{xqmc}, "xqdm");
	}
	
	/**
	 * 5S分值查询
	 */
	public ArrayList<String[]> dao_Query5S(FiveSModel model,String userType,String userName){
		
		String xh = DealString.toGBK(model.getFivexh().trim());
		String xm = DealString.toGBK(model.getXm().trim());
		String bzr = " and exists (select 1 from fdybjb b where zgh like '"
				+ userName + "' and a.bjdm = b.bjdm)";
		xh = Base.isNull(xh) ? "%" : "%" + xh + "%";
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";
		
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		
		querry.append(Base.isNull(model.getXn())?"":" and a.xn='"+model.getXn()+"' ");
		querry.append(Base.isNull(model.getNj())?"":" and nj='"+model.getNj()+"' ");
		querry.append(Base.isNull(model.getXydm())?"":" and xydm='"+model.getXydm()+"' ");
		querry.append(Base.isNull(model.getZydm())?"":" and zydm='"+model.getZydm()+"' ");
		querry.append(Base.isNull(model.getBjdm())?"":" and bjdm='"+model.getBjdm()+"' ");
		querry.append(Base.isNull(model.getXq())?"":" and a.xq='"+getXqmc(model.getXq())+"' ");
		querry.append(" and a.xh like ?");
		querry.append(" and a.xm like ?");
		querry.append("bzr".equalsIgnoreCase(userType)?bzr:"");
		
		StringBuffer sql = new StringBuffer();
		sql.append("select a.*,nvl(b.zf,0) zf,nvl(c.zf,0) wsf from (select a.xh||xn||xq pk,a.xh,xn,(select xqmc from xqdzb b ");
		sql.append("where a.xq=b.xqdm) xq,xm,nj,xydm,xymc,zydm,zymc,bjdm,bjmc from ");
		sql.append("(select distinct xh,xn,xq from szyc_5sb) a left outer join view_xsxxb b on a.xh=b.xh) a ");
		
		sql.append("left join (select xh, xn, (select xqmc from xqdzb where xq = xqdm) xq, ");
		sql.append("sum(fz) zf from (select xh, xn, xq, case when jjf = '加分' then fz else '-' || fz end fz ");
		sql.append("from szyc_5sb) group by xh, xn, xq) b on a.xn = b.xn and a.xq = b.xq and a.xh = b.xh ");
		
		sql.append("left join (select xh, xn, (select xqmc from xqdzb where xq = xqdm) xq, ");
		sql.append("sum(fz) zf from (select xh, xn, xq, case when jjf = '加分' then fz else '-' || fz end fz ");
		sql.append("from szyc_5sb where xxsh='通过') group by xh, xn, xq) c on a.xn = c.xn and a.xq = c.xq and a.xh = c.xh");
		sql.append(querry.toString());
		
		String[] inputValue = {xh,xm} ;
		String[] outputValue = {"pk","xh","xn","xq","xm","xymc","bjmc","zf","wsf"};
		ArrayList<String[]> rs = dao.rsToVator(sql.toString(), inputValue, outputValue);
		System.out.println(sql);
		return rs;
	}
	
	/**
	 * 5S分值审核查询
	 * @throws Exception 
	 */
	public ArrayList<String[]> dao_View5S(FiveSModel model){
		
		String xh = DealString.toGBK(model.getFivexh().trim());
		String xm = DealString.toGBK(model.getXm().trim());
		String shzt = DealString.toGBK(model.getShzt());
		xh = Base.isNull(xh) ? "%" : "%" + xh + "%";
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";
		
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		
		querry.append(Base.isNull(model.getXn())?"":" and a.xn='"+model.getXn()+"' ");
		querry.append(Base.isNull(model.getNj())?"":" and nj='"+model.getNj()+"' ");
		querry.append(Base.isNull(model.getXydm())?"":" and xydm='"+model.getXydm()+"' ");
		querry.append(Base.isNull(model.getZydm())?"":" and zydm='"+model.getZydm()+"' ");
		querry.append(Base.isNull(model.getBjdm())?"":" and bjdm='"+model.getBjdm()+"' ");
		querry.append(Base.isNull(model.getXq())?"":" and a.xq='"+getXqmc(model.getXq())+"' ");
		querry.append(" and a.xh like ?");
		querry.append(" and a.xm like ?");
		
		StringBuffer sql = new StringBuffer();
		sql.append("select rownum r,a.* from (select a.*,case when b.wsh >0 and b.tg = 0 and b.btg=0 then '未审核'");
		sql.append(" when b.wsh =0 and b.tg > 0 and b.btg=0 then '通过'");
		sql.append(" when b.wsh =0 and b.tg = 0 and b.btg>0 then '不通过'");
		sql.append(" when b.tg <> 0 then '部分通过'");
		sql.append(" when b.wsh <>0 and b.tg = 0 and b.btg<>0 then '部分未审核' end xxsh from (select a.*,nvl(b.zf,0) wsf,nvl(c.zf,0) zf");
		sql.append(" from (select a.xh||xn||xq pk,a.xh,xn,(select xqmc from xqdzb b ");
		sql.append(" where a.xq=b.xqdm) xq,xm,nj,xydm,xymc,zydm,zymc,bjdm,bjmc from ");
		sql.append(" (select distinct xh,xn,xq from szyc_5sb) a left outer join view_xsxxb b on a.xh=b.xh) a");
		sql.append(" left join (select xh, xn, (select xqmc from xqdzb where xq = xqdm) xq,");
		sql.append(" sum(fz) zf from (select xh, xn, xq, case when jjf = '加分' then fz else '-' || fz end fz");
		sql.append(" from szyc_5sb where xxsh = '通过') group by xh, xn, xq) b on a.xn = b.xn and a.xq = b.xq and a.xh = b.xh");
		sql.append(" left join (select xh, xn, (select xqmc from xqdzb where xq = xqdm) xq,");
		sql.append(" sum(fz) zf from (select xh, xn, xq, case when jjf = '加分' then fz else '-' || fz end fz");
		sql.append(" from szyc_5sb) group by xh, xn, xq) c on a.xn = c.xn and a.xq = c.xq and a.xh = c.xh");
		sql.append(querry.toString());
		sql.append(" ) a left join (select pk,sum(wsh) wsh,sum(tg) tg,sum(btg) btg");
		sql.append(" from (select pk, case when xxsh = '未审核' then 1 else 0 end wsh,");
		sql.append(" case when xxsh = '通过' then 1 else 0 end tg,");
		sql.append(" case when xxsh = '不通过' then 1 else 0 end btg");
		sql.append(" from (select xh || xn || xq pk, xxsh from szyc_5sb)) group by pk) b on a.pk = b.pk) a");
		sql.append(Base.isNull(shzt) ? "" : " where xxsh='" + shzt + "'");
		
		String[] inputValue = {xh,xm} ;
		String[] outputValue = { "pk", "xh", "xn", "xq", "xm", "xymc", "bjmc",
				"zf", "wsf", "xxsh" };
		ArrayList<String[]> rs = null;
		try {
			rs = CommonQueryDAO.commonQuery(sql.toString(), "", inputValue, outputValue, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(sql);
		return rs;
	}
	
	/**
	 * 5s分值增加
	 * 
	 * @throws SQLException
	 */
	public boolean dao_add5s(FiveSModel model, HttpServletRequest request)
			throws SQLException {
		boolean flg = false;
		dao = DAO.getInstance();
		String[] sqlList = new String[model.getFzxm().length + 1];
		StringBuffer sql = null;
		try {
			StringBuffer buff = new StringBuffer();
			buff.append(model.getXh()[0]).append(model.getXn()).append(
					model.getXq());
			StandardOperation.delete("szyc_5sb", "xh||xn||xq", buff.toString(),
					request);
		} catch (Exception e1) {
			// TODO 自动生成 catch 块
			e1.printStackTrace();
		}
		for (int i = 0; i < model.getFzxm().length; i++) {
			sql = new StringBuffer();
			sql.append("insert into szyc_5sb (xh,xn,xq,fzxm,jjf,fz,lrrq,yy,xxsh) values('");
			sql.append(model.getXh()[0]);
			sql.append("','");
			sql.append(model.getXn());
			sql.append("','");
			sql.append(model.getXq());
			sql.append("','");
			sql.append(model.getFzxm()[i]);
			sql.append("','");
			sql.append(model.getJjf()[i]);
			sql.append("','");
			sql.append(model.getFz()[i]);
			sql.append("','");
			sql.append(model.getLrrq()[i]);
			sql.append("','");
			sql.append(DealString.toGBK(model.getYy()[i]).replace("'","‘"));
			sql.append("','");
			sql.append(DealString.toGBK(model.getXxsh()[i]));
			sql.append("')");
			if (StringUtils.isNull(model.getFzxm()[i])) {// 为空则不记录
				sql = new StringBuffer("");
			}
			sqlList[i] = sql.toString();
		}
		// String updateSql = "update sxzzddszb set shzt = '未审核' where xh =
		// '"+model.getXh()+"' and xn = '"+model.getXn()+"'";
		// sqlList[model.getFs().length] = updateSql;
		int[] res = dao.runBatch(sqlList);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}

		return flg;
	}
	
	/**
	 * 5s分值审核
	 * 
	 * @throws SQLException
	 */
	public boolean dao_sh5s(String[] key, String shzt) throws SQLException {
		boolean flg = false;
		dao = DAO.getInstance();
		if (!Base.isNull(shzt)) {
			shzt = "yes".equalsIgnoreCase(shzt) ? "通过" : "不通过";
		}
		StringBuffer sql = new StringBuffer();
		String[] sqlList = null;
		if (key != null && key.length > 0) {
			sqlList = new String[key.length];
			for (int i = 0; i < key.length; i++) {
				sql = new StringBuffer();
				sql.append("update szyc_5sb set xxsh='");
				sql.append(shzt);
				sql.append("' where id='");
				sql.append(key[i]);
				sql.append("'");
				sqlList[i] = sql.toString();
			}
		}
		int[] res = dao.runBatch(sqlList);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}

		return flg;
	}
	
	/**
	 * 获得审核通过5s分
	 * 
	 * @throws SQLException
	 * 
	 */
	public boolean dao_get5sfzList(String[] key, String pk, String kjf)
			throws SQLException {

		String sql = "select jjf,fz from szyc_5sb where xxsh='通过' and xh||xn||xq=?";
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { pk }, new String[] { "jjf", "fz" });
		float zf = 0;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String jjf = list.get(i).get("jjf");
				String fz = list.get(i).get("fz");
				if ("加分".equalsIgnoreCase(jjf)) {
					zf += Base.isNull(fz) ? 0 : Float.parseFloat(fz);
				} else {
					zf -= Base.isNull(fz) ? 0 : Float.parseFloat(fz);
				}
			}
		}
		if (key != null && key.length > 0) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < key.length; i++) {
				if (i == 0) {
					sb.append("(id='" + key[i] + "'");
				} else if (i == key.length - 1) {
					sb.append(" or id='" + key[i] + "')");
				} else {
					sb.append(" or id='" + key[i] + "'");
				}
			}
			if (key.length == 1) {
				sb.append(")");
			}
			sql = "select jjf,fz from szyc_5sb where xxsh<>'通过' and xh||xn||xq=? and "
					+ sb.toString();
			List<HashMap<String, String>> jjfList = dao.getList(sql.toString(),
					new String[] { pk }, new String[] { "jjf", "fz" });
			if (jjfList != null && jjfList.size() > 0) {
				for (int i = 0; i < jjfList.size(); i++) {
					String jjf = jjfList.get(i).get("jjf");
					String fz = jjfList.get(i).get("fz");
					if ("加分".equalsIgnoreCase(jjf)) {
						zf += Base.isNull(fz) ? 0 : Float.parseFloat(fz);
					} else {
						zf -= Base.isNull(fz) ? 0 : Float.parseFloat(fz);
					}
				}
			}
		}
		if (!Base.isNull(kjf)) {
			if (zf > Float.parseFloat(kjf)) {
				return false;
			}
		}

		return true;
	}
	
	/**
	 * 5s分值删除
	 * 
	 * @throws SQLException
	 */
	public boolean dao_del5s(String pks) throws SQLException {

		boolean flg = false;
		StringBuffer sb = new StringBuffer();
		String[] keys = pks.split("!!#!!");
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// 得到主键
			sql = "delete from szyc_5sb where xh||xn||xq = '" + pk + "'";
			// 把主键组合成sql语句
			sb.append(sql).append("!!#!!");
		}
		// sql语句拆分成数组
		String[] pksql = sb.toString().split("!!#!!");
		int[] res = dao.runBatch(pksql);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}

		return flg;
	}
	
	/**
	 * 获得学生5s分值
	 * 
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> dao_get5s(String pk,
			HttpServletRequest request) throws SQLException {
		String sql = "select fzxm,jjf,fz,lrrq,yy,xxsh from szyc_5sb where xh||xn||xq=? order by xxsh,fzxm";
		return dao.getList(sql, new String[] { pk }, new String[] { "fzxm",
				"jjf", "fz","lrrq","yy","xxsh" });
	}
	
	/**
	 * 获得该学期月份
	 * 
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> dao_getYf(String xq) throws SQLException {
		String xqmc=getXqmc(xq);
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		if("春".equalsIgnoreCase(xqmc)){
			for(int i=2;i<7;i++){
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("yfdm", "0" + String.valueOf(i));
				map.put("yfmc", String.valueOf(i)+"月");
				list.add(map);
			}
		} else if ("秋".equalsIgnoreCase(xqmc)) {
			for (int i = 9; i <= 12; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				if (i < 10) {
					map.put("yfdm", "0" + String.valueOf(i));
				} else {
					map.put("yfdm", String.valueOf(i));
				}
				map.put("yfmc", String.valueOf(i) + "月");
				list.add(map);
				if (i == 12) {
					map = new HashMap<String, String>();
					map.put("yfdm", "1");
					map.put("yfmc", "1月");
					list.add(map);
				}
			}
		}
		return list;
	}
	
	/**
	 * 获得周数
	 * 
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> dao_getZc() throws SQLException {
		String sql = "select xqzs from xtszb where rownum=1";
		String xqzs = dao.getOneRs(sql, new String[] {}, "xqzs");
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		if (!Base.isNull(xqzs)) {
			for (int i = 1; i <= Integer.parseInt(xqzs); i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("zcdm", String.valueOf(i));
				map.put("zcmc", "第" + String.valueOf(i) + "周");
				list.add(map);
			}
		}
		return list;
	}
	
	/**
	 * 获得班级名称
	 * 
	 * @throws SQLException
	 */
	public String dao_getBjmc(String[] arrBj) throws SQLException {
		StringBuffer sq = new StringBuffer();
		StringBuffer bjmc = new StringBuffer();
		for (int i = 0; i < arrBj.length; i++) {
			if (i == 0) {
				sq.append(" where bjdm = '" + arrBj[i] + "'");
			} else {
				sq.append(" or bjdm = '" + arrBj[i] + "'");
			}
		}
		if (arrBj.length == 1) {
			//sq.append(")");
		}
		String sql = "select bjmc from view_njxyzybj";
		List<String> bjInfo = dao.getList(sql + sq.toString(), new String[] {},
				"bjmc");

		if (bjInfo != null && bjInfo.size() > 0) {
			for (int i = 0; i < bjInfo.size(); i++) {
				if (i == bjInfo.size() - 1) {
					bjmc.append(bjInfo.get(i));
				} else {
					bjmc.append(bjInfo.get(i) + ",");
				}
			}
		}
		return bjmc.toString();
	}
		
	/**
	 * 获得5s学期汇总List
	 * 
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> dao_getXqhzList(String xn,String xq,String[] arrBj)
			throws SQLException {
		List<HashMap<String, String>> xqList = dao_getYf(xq);
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from(select a.xh,a.xm,a.bjdm,a.one,a.two,a.thr,a.four,a.fiv,(b.jf - b.kf) grf,");
		sql.append(" (a.one + a.two + a.thr + a.four + a.fiv) zf from (select xh,");
		sql.append(" xm,bjdm, sum(onejf) - sum(onekf) one, sum(twojf) - sum(twokf) two,");
		sql.append(" sum(thrjf) - sum(thrkf) thr, sum(forjf) - sum(forkf) four,");
		sql.append(" sum(fivjf) - sum(fivkf) fiv from (select xh,xm,bjdm,");
		sql.append(" case when yf = '"+xqList.get(0).get("yfdm")+"' then jf else 0 end onejf,");
		sql.append(" case when yf = '"+xqList.get(0).get("yfdm")+"' then kf else 0 end onekf,");
		sql.append(" case when yf = '"+xqList.get(1).get("yfdm")+"' then jf else 0 end twojf,");
		sql.append(" case when yf = '"+xqList.get(1).get("yfdm")+"' then kf else 0 end twokf,");
		sql.append(" case when yf = '"+xqList.get(2).get("yfdm")+"' then jf else 0 end thrjf,");
		sql.append(" case when yf = '"+xqList.get(2).get("yfdm")+"' then kf else 0 end thrkf,");
		sql.append(" case when yf = '"+xqList.get(3).get("yfdm")+"' then jf else 0 end forjf,");
		sql.append(" case when yf = '"+xqList.get(3).get("yfdm")+"' then kf else 0 end forkf,");
		sql.append(" case when yf = '"+xqList.get(4).get("yfdm")+"' then jf else 0 end fivjf,");
		sql.append(" case when yf = '"+xqList.get(4).get("yfdm")+"' then kf else 0 end fivkf");
		sql.append(" from (select xh, xm,bjdm, yf, sum(jf) jf, sum(kf) kf");
		sql.append(" from (select xh,xm,bjdm,yf,case when jjf = '加分' then fz else");
		sql.append(" '0' end jf,case when jjf = '减分' then fz else '0'");
		sql.append(" end kf from (select a.xh, b.xm, b.bjdm,a.xn, a.xq, a.jjf, a.fz,a.xxsh,");
		sql.append(" substr(a.lrrq, 5, 2) yf from szyc_5sb a left join view_xsjbxx b ");
		sql.append(" on a.xh = b.xh) where xn = '"+xn+"' and xq = '"+xq+"' and xxsh='通过' order by yf)");
		sql.append(" group by xh, xm, yf,bjdm))  group by xh, xm,bjdm) a left join ");
		sql.append(" (select xh, xn, xq, yf, sum(jf) jf, sum(kf) kf from (select xh, ");
		sql.append(" xn, xq, yf, case when jjf = '加分' then fz else '0' end jf, ");
		sql.append(" case when jjf = '减分' then fz else '0' end kf from (select a.xh, ");
		sql.append(" a.xn, a.xq, a.jjf, a.fz, a.xxsh, substr(a.lrrq, 5, 2) yf  ");
		sql.append(" from szyc_5sb a where a.fzxm = 'grszf' and a.xxsh = '通过')) group by xn, xq, xh, yf) ");	
		sql.append(" b on a.xh = b.xh and b.xn = '"+xn+"' and b.xq = '"+xq+"') where");
		for (int i = 0; i < arrBj.length; i++) {
			if (i == 0) {
				sql.append(" bjdm = '" + arrBj[i] + "'");
			} else {
				sql.append(" or bjdm = '" + arrBj[i] + "'");
			}
		}
		//System.out.println(sql.toString());
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] {"xh","xm","one","two","thr","four","fiv","grf","zf"});
		return list;
	}
	
	/**
	 * 获得5s月度汇总List
	 * 
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> dao_getYdhzList(String xn, String xq,
			String yf, String[] arrBj) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("select xh, xm, lrrq, yy, fzxm,jf,kf,(jf-kf) zf");
		sql.append(" from (select xh, xm, yy, fzxm, lrrq, bjdm, yf, jf, kf");
		sql.append(" from (select xh,yy,xm,fzxm,lrrq,bjdm,yf,");
		sql.append(" case when jjf = '加分' then fz else '0' end jf,");
		sql.append(" case when jjf = '减分' then fz else '0' end kf from (select a.xh, ");
		sql.append(" case when a.fzxm='grszf' then '个人素质分'");
	    sql.append(" when a.fzxm='jsssf' then '教室与宿舍5S分' when a.fzxm='ktf' then '课堂5S分'");
	    sql.append(" when a.fzxm='cxf' then '诚信分' when a.fzxm='qtf' then '其他分' end fzxm, ");
		sql.append(" a.yy, b.xm, b.bjdm, a.lrrq,a.xn, a.xq, a.jjf, a.fz, a.xxsh, substr(a.lrrq, 5, 2) yf");
		sql.append(" from szyc_5sb a left join view_xsjbxx b on a.xh = b.xh)");
		sql.append(" where xn = '" + xn + "' and xq = '" + xq
				+ "' and xxsh = '通过'");
		sql.append("order by yf)) where yf = '" + yf + "' and (");
		for (int i = 0; i < arrBj.length; i++) {
			if (i == 0) {
				sql.append(" bjdm = '" + arrBj[i] + "'");
			} else if (i == arrBj.length - 1) {
				sql.append(" or bjdm = '" + arrBj[i] + "')");
			} else {
				sql.append(" or bjdm = '" + arrBj[i] + "'");
			}
			if(arrBj.length ==1){
				sql.append(")");
			}
		}
		//System.out.println(sql.toString());
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "xh", "xm", "lrrq", "yy",
						"fzxm", "jf", "kf", "zf" });
		return list;
	}
	
	/**
	 * 获得5s汇总List
	 * 
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> dao_get5shzList(String xn, String xq,
			String kssj,String jssj, String[] arrBj) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append(" select xymc,bjmc,xh,yy,xm,lrrq,bjdm,");
		sql.append(" case when fzxm='grszf' then '个人素质分'");
	    sql.append(" when fzxm='jsssf' then '教室与宿舍5S分' when fzxm='ktf' then '课堂5S分'");
	    sql.append(" when fzxm='cxf' then '诚信分' when fzxm='qtf' then '其他分' end fzxm, ");
		sql.append(" case when jjf = '加分' then fz else '0' end jf,");
		sql.append(" case when jjf = '减分' then fz else '0' end kf");
		sql.append(" from (select b.xymc,b.bjmc,a.xh,a.fzxm,a.yy,b.xm,");
		sql.append(" b.bjdm,a.lrrq,a.xn,a.xq,a.jjf,a.fz,a.xxsh,");
		sql.append(" substr(a.lrrq, 5, 2) yf from szyc_5sb a");
		sql.append(" left join view_xsjbxx b on a.xh = b.xh)");
		sql.append(" where xn = '"+xn+"' and xq = '"+xq+"' and xxsh = '通过'");
		sql.append(" and lrrq >= '" + kssj + "' and lrrq <'"+jssj+"' and(");
		for (int i = 0; i < arrBj.length; i++) {
			if (i == 0) {
				sql.append(" bjdm = '" + arrBj[i] + "'");
			} else if (i == arrBj.length - 1) {
				sql.append(" or bjdm = '" + arrBj[i] + "')");
			} else {
				sql.append(" or bjdm = '" + arrBj[i] + "'");
			}
			if(arrBj.length ==1){
				sql.append(")");
			}
		}
		//System.out.println(sql.toString());
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "xymc", "bjmc", "xh", "xm",
						"lrrq", "yy", "fzxm", "kf", "jf" });
		return list;
	}
	
	/**
	 * 获得本学期起始日期
	 * 
	 * @throws SQLException
	 */
	public String dao_getQsrq() {
		String sql = "select qsrq from xtszb where rownum = 1";
		return dao.getOneRs(sql, new String[] {}, "qsrq");

	}

	/**
	 * 获得学生基本信息
	 * 
	 */
	public HashMap<String, String> dao_getStuInfo(String xh, String pk,String tableName) {
		String sql = "select xh,xm,xb,xymc,zymc,bjmc from view_xsjbxx where xh=?";
		HashMap<String, String> map1 = dao.getMap(sql, new String[] { xh },
				new String[] { "xh", "xm", "xb", "xymc", "zymc", "bjmc" });
		sql = "select distinct(xn) xn,xq xqdm,(select xqmc from xqdzb where xqdm=xq)xq from "+tableName+" where xh||xn||xq=?";
		HashMap<String, String> map2 = dao.getMap(sql, new String[] { pk },
				new String[] { "xn", "xq" });
		map1.put("xn", map2.get("xn"));
		map1.put("xqdm", map2.get("xqdm"));
		map1.put("xq", map2.get("xq"));
		return map1;
	}
	
	/**
	 * 5s审核信息列表
	 */
	public ArrayList<String[]> dao_5sList(String pk){
        StringBuffer sql = new StringBuffer();
        String[]colList=new String[]{"id","r","fzxm","jjf","fz","lrrq","yy","xxsh"};
        sql.append("select t.*,rownum r from (select id, case when fzxm='grszf' then '个人素质分'");
        sql.append(" when fzxm='jsssf' then '教室与宿舍5S分' when fzxm='ktf' then '课堂5S分'");
        sql.append(" when fzxm='cxf' then '诚信分' when fzxm='qtf' then '其他分' end fzxm, ");
        sql.append(" fz, jjf, (select mc from szyc_jjfyyb where dm = yy) yy, lrrq, xxsh from szyc_5sb where xh||xn||xq = ? order by xxsh) t");
        //System.out.println(sql);
        return dao.rsToVator(sql.toString(), new String[]{pk},colList);
	}
	
	/**
	 * 5s审核基本信息
	 */
	public HashMap<String, String> dao_5sXsInfo(String pk) {
		StringBuffer sql = new StringBuffer();
		String[] colList = new String[] { "xh", "xb", "xm", "xymc", "zymc",
				"bjmc", "xq", "xn" };
		sql.append("select a.xh,b.xm,b.xb,b.xymc,b.zymc,b.bjmc,a.xn,");
		sql.append(" (select xqmc from xqdzb where xqdm = a.xq) xq");
		sql.append(" from szyc_5sb a, view_xsjbxx b where a.xh = b.xh");
		sql.append(" and a.xh || a.xn || a.xq = ?  and rownum = 1");
		return dao.getMap(sql.toString(), new String[] { pk }, colList);
	}
	
	public String[] getFzsxForMkdm(String mkdm) {
		String sql = "select zxmjcf,zxmzgf from szgy_zhszycfsszb where zxmdm = ?";
		return dao.getOneRs(sql, new String []{mkdm}, new String[]{"zxmjcf","zxmzgf"});
	}
	/**
	 * 社会实践分值增加
	 * 
	 * @throws SQLException
	 */
	public boolean dao_addShsj(ShsjModel model, HttpServletRequest request)
			throws SQLException {
		boolean flg = false;
		dao = DAO.getInstance();
		String[] sqlList = new String[model.getHdnr().length + 1];
		StringBuffer sql = null;
		try {
			StringBuffer buff = new StringBuffer();
			buff.append(model.getXh()[0]).append(model.getXn()).append(
					model.getXq());
			StandardOperation.delete("SZYC_SHSJFZB", "xh||xn||xq", buff.toString(),
					request);
		} catch (Exception e1) {
			// TODO 自动生成 catch 块
			e1.printStackTrace();
		}
		for (int i = 0; i < model.getHdnr().length; i++) {
			sql = new StringBuffer();
			sql.append("insert into SZYC_SHSJFZB(XH,XN,XQ,HDNR,HDRQ,HDDD,HDSJ,SHFZ,JJF,XXSH)values( '");
			sql.append(model.getXh()[0]);
			sql.append("','");
			sql.append(model.getXn());
			sql.append("','");
			sql.append(model.getXq());
			sql.append("','");
			sql.append(DealString.toGBK(model.getHdnr()[i]));
			sql.append("','");
			sql.append(DealString.toGBK(model.getHdrq()[i]));
			sql.append("','");
			sql.append(DealString.toGBK(model.getHddd()[i]));
			sql.append("','");
			sql.append(DealString.toGBK(model.getHdsj()[i]));
			sql.append("','");
			sql.append(DealString.toGBK(model.getShfz()[i]));
			sql.append("','");
			sql.append(DealString.toGBK(model.getJjf()[i]));
			sql.append("','");
			sql.append(DealString.toGBK(model.getShType()[i]));
			sql.append("')");
			if (Base.isNull(model.getHdnr()[i])) {// 为空则不记录
				sql = new StringBuffer("");
			}
			sqlList[i] = sql.toString();
		}		
		int[] res = dao.runBatch(sqlList);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}
		return flg;
	}
	/**
	 * 获得学生社会实践分值
	 * 
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> dao_getShsj(String pk,
			HttpServletRequest request) throws SQLException {
		String sql = "select hdnr,hdrq,hddd,hdsj,shfz,lrrq,xxsh,jjf from szyc_shsjfzb where xh||xn||xq=? order by xxsh asc";
		return dao.getList(sql, new String[] { pk }, new String[] { "hdnr","hddd","hdrq","hdsj","jjf","shfz","xxsh"});
	}
	/**
	 * 社会实践活动查询页面
	 */
	public ArrayList<String[]> dao_shsjSearch(ShsjModel model,String userType,String userName){
		DAO dao      = DAO.getInstance();
		ArrayList<String[]> result = new ArrayList<String[]>();
		String xh = "";
		if (model.getXh() != null && model.getXh().length > 0) {
			xh = model.getXh()[0];
		}
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xm   = model.getXm();
		String xn   = model.getXn();
		String xq   = model.getXq();
		xh = Base.isNull(xh) ? "%" : xh ;
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";		
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		StringBuffer querry2 = new StringBuffer(" where 1=1 ");
		querry.append(" and a.xh like ?");
		querry.append(" and xm like ?");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");

		querry.append("bzr".equalsIgnoreCase(userType) ? " and exists (select 1 from fdybjb b where zgh like '"+ userName + "' and a.bjdm = b.bjdm)": "");
		querry2.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry2.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");	
		String[] colList = new String[]{"pk","xn","xq","xh","xm","nj","bjmc","zf","hdf"};
		StringBuffer  sql = new StringBuffer();
		sql.append("select b.zf,nvl(c.hdf,0) hdf, a.xh||a.xn||a.xq pk,a.xn,(select xqmc from xqdzb d where a.xq=d.xqdm)xq,a.xh,nj,xydm,zydm,bjdm,xm,xymc,zymc,bjmc, ");
		sql.append("( case when (select count(xh) from szyc_shsjfzb d "+querry2+" and d.xh=a.xh and xxsh='通过')=a.cout then '全部通过' ");
		sql.append("when (select count(xh) from szyc_shsjfzb d "+querry2+" and d.xh=a.xh and xxsh='未审核')=a.cout then '未审核'  ");
		sql.append("when (select count(xh) from szyc_shsjfzb d "+querry2+" and d.xh=a.xh and xxsh='不通过')=a.cout then '全部不通过' else '部分通过' end  ");
		sql.append(")sh from (select a.xn,a.xq,a.xh,nj,xydm,zydm,bjdm,xm,xymc,bjmc,zymc, ");
		sql.append("(select count(xh) from szyc_shsjfzb d  "+querry2+"  and d.xh=a.xh )cout ");
		sql.append("from (select  distinct xh,xn,xq from szyc_shsjfzb  "+querry2+" )a left join view_xsxxb b on a.xh=b.xh )a ");
		
		sql.append("left join (select xh, xn, xq, ");
		sql.append("sum(pf) zf from (select xh, xn, xq, case when jjf = '加分' then shfz else '-' || shfz end pf ");
		sql.append("from szyc_shsjfzb) group by xh, xn, xq) b on a.xn = b.xn and a.xq = b.xq and a.xh = b.xh ");
		
		sql.append("left join (select xh, xn,xq, ");
		sql.append("sum(pf) hdf from (select xh, xn, xq, case when jjf = '加分' then shfz else '-' || shfz end pf ");
		sql.append("from szyc_shsjfzb where xxsh='通过') group by xh, xn, xq) c on a.xn = c.xn and a.xq = c.xq and a.xh = c.xh");
		result = dao.rsToVator(sql+querry.toString(), new String[]{xh,xm},colList);
		return result;
	}
		
	public ArrayList<String[]> getPointSetting() {
		String sql = "select zxmdm,zxmjcf,zxmzgf,mkmc from szgy_zhszycfsszb";
		return dao.rsToVator(sql, new String []{}, new String[]{"zxmdm","mkmc","zxmjcf","zxmzgf"});
	}
	
	public boolean updatePointSetting(FsszModel model) throws SQLException {
		String [] mkdms = model.getMkdm();
		String [] jcfzs = model.getJcfz();
		String [] zgfzs = model.getZgfz();
		String [] sqlmap = new String [mkdms.length];
		for(int i= 0;i<mkdms.length;i++){
			if(mkdms[i]!=null&&!mkdms[i].equalsIgnoreCase("")){
			String sqltmp = "update szgy_zhszycfsszb set zxmjcf = '"+jcfzs[i]+"',zxmzgf = '"+zgfzs[i]+"' where zxmdm='"+mkdms[i]+"'";
			sqlmap[i]=sqltmp;
			}
		}
		dao.runBatch(sqlmap);
		return true;
	}
	/**
	 * 社会实践活动审核查询
	 */
	public ArrayList<String[]> dao_shsjChkSearch(ShsjModel model){
		ArrayList<String[]> result = new ArrayList<String[]>();
		String xh = "";
		if (model.getXh() != null && model.getXh().length > 0) {
			xh = model.getXh()[0];
		}
		
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xm   = model.getXm();
		String xn   = model.getXn();
		String xq   = model.getXq();
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		StringBuffer querry2 = new StringBuffer(" where 1=1 ");
		querry.append(Base.isNull(xh)?"":" and a.xh='"+xh.trim()+"' ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
		querry.append(Base.isNull(xm)?"":" and xm like '%"+xm.trim()+"%' ");	
		querry2.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry2.append(Base.isNull(xn)?"":" and xq='"+xq+"' ");	
		String[] colList = new String[]{"pk","xn","xq","xh","xm","nj","bjmc","zf","hdf","sh"};
		StringBuffer  sql = new StringBuffer();
		sql.append("select rownum r,a.xh||a.xn||a.xq pk,a.xn,(select xqmc from xqdzb d where a.xq=d.xqdm)xq,a.xh,nj,xydm,zydm,bjdm,xm,xymc,zymc,bjmc,b.zf,nvl(c.hdf,0) hdf, ");
		sql.append("( case when (select count(xh) from szyc_shsjfzb d "+querry2+" and d.xh=a.xh and xxsh='通过')=a.cout then '全部通过' ");
		sql.append("when (select count(xh) from szyc_shsjfzb d "+querry2+" and d.xh=a.xh and xxsh='未审核')=a.cout then '未审核'  ");
		sql.append("when (select count(xh) from szyc_shsjfzb d "+querry2+" and d.xh=a.xh and xxsh='不通过')=a.cout then '全部不通过' else '部分通过' end  ");
		sql.append(")sh from (select a.xn,a.xq,a.xh,nj,xydm,zydm,bjdm,xm,xymc,bjmc,zymc, ");
		sql.append("(select count(xh) from szyc_shsjfzb d  "+querry2+"  and d.xh=a.xh )cout ");
		sql.append("from (select  distinct xh,xn,xq from szyc_shsjfzb  "+querry2+" )a left join view_xsxxb b on a.xh=b.xh )a ");
		
		sql.append("left join (select xh, xn, xq, ");
		sql.append("sum(pf) zf from (select xh, xn, xq, case when jjf = '加分' then shfz else '-' || shfz end pf ");
		sql.append("from szyc_shsjfzb) group by xh, xn, xq) b on a.xn = b.xn and a.xq = b.xq and a.xh = b.xh ");
		
		sql.append("left join (select xh, xn,xq, ");
		sql.append("sum(pf) hdf from (select xh, xn, xq, case when jjf = '加分' then shfz else '-' || shfz end pf ");
		sql.append("from szyc_shsjfzb where xxsh='通过') group by xh, xn, xq) c on a.xn = c.xn and a.xq = c.xq and a.xh = c.xh");
		
		try {
			result = CommonQueryDAO.commonQuery(sql.toString(), querry.toString(), new String[]{},colList, model);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}
	/**
	 * 社会实践活动审核
	 */
	public boolean dao_shsjChk(String pkVStr,String shType) throws Exception{
		DAO dao = DAO.getInstance();	
		shType = ("yes".equalsIgnoreCase(shType))?"通过":"不通过";
		String[] pkValueA = pkVStr.split("!!");		
		String[] updtPkSql  = new String[pkValueA.length];	
		for (int i = 0; i < pkValueA.length; i++) {
			updtPkSql[i] = Base.isNull(pkValueA[i])?"":"update szyc_shsjfzb set xxsh='"+shType+"'  where id= '"+pkValueA[i]+"'";							
		}              
		boolean doFlag = false;
		int[] array = dao.runBatch(updtPkSql);
		doFlag = dao.checkBatch(array);   
		return doFlag;
	}
	/**
	 * 社会实践活动信息列表
	 */
	public ArrayList<String[]> dao_shsjViewAndChkSearch(String xh,String xn,String xq){
        StringBuffer sql = new StringBuffer();
        String[]colList=new String[]{"id","r","hdnr","hddd","hdrq","hdsj","jjf","shfz","xxsh"};
        sql.append("select id, rownum r,(case when length(hdnr)>50 then substr(hdnr,0,50)||'...' else hdnr end)hdnr,hdrq,hddd,hdsj,jjf,shfz,xxsh from szyc_shsjfzb where xh=? and xn=? and xq=?");
        return dao.rsToVator(sql.toString(), new String[]{xh,xn,xq},colList);
	}	
	/**
	 * 社会实践活动信息删除
	 */
	public String dao_shsjDel(String[] keys){
		StringBuffer sb = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// 得到主键
			sql = "delete from szyc_shsjfzb where xh||xn||xq = '" + pk + "'";
			// 把主键组合成sql语句
			sb.append(sql).append("!!#!!");
		}
		// sql语句拆分成数组
		pksql = sb.toString().split("!!#!!");
		int[] judge = null;
		try {
			judge = dao.runBatch(pksql);
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		String whichpk = "";
		// 检查哪一条删除失败
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] < 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
			}
		}
		return whichpk;
	}
	/**
	 * 得分限制方法
	 * @param mkType zznl"组织能力",shsj"社会实践"
	 * @param id
	 * @param pkValue
	 * @return
	 */
	public String getPointLimitXthd(String xxk,String id,String pkValue){ 
		String retStr="";
		float jcf=0;//基础分
		float zgf=0;//最高分
		float shzf=0;//所获总分
		String tableName = "szyq_dshdjzb";
		String xxklx="";
		if("ivlt".equals(xxk)){
			tableName = "szyq_ivtltb";
			xxklx="ivtlt";
		}else if("wthd".equals(xxk)){
			tableName = "szyq_xthddjb";
			xxklx="wthd";
		}else if("yybd".equals(xxk)){
			tableName = "szyq_yybdjzb";
			xxklx="yybd";
		}else{
			tableName = "szyq_dshdjzb";
			xxklx="dshd";
		}
		if(StringUtils.isNotNull(xxk)){//组织能力
			String pkvString = id;
			//已通过的所获分
			String sumjf = dao.getOneRs("select nvl(sum(pf),0)sum  from "+tableName+" where xh||xn||xq=? and jjf='加分' and id not in("+pkvString+") and xxsh='通过' ", new String[]{pkValue},"sum");
			String sumkf = dao.getOneRs("select nvl(sum(pf),0)sum  from "+tableName+" where xh||xn||xq=? and jjf='减分' and id not in("+pkvString+") and xxsh='通过' ", new String[]{pkValue},"sum");
			sumjf  = Base.isNull(sumjf)?"0":sumjf;
			sumkf = Base.isNull(sumkf)?"0":sumkf;
			String sum =String.valueOf(Float.parseFloat(sumjf)-Float.parseFloat(sumkf));
			//所选中记录的所获分
			String sumtjf = dao.getOneRs("select nvl(sum(pf),0)sum  from "+tableName+" where xh||xn||xq=? and jjf='加分' and id in("+pkvString+") ", new String[]{pkValue},"sum");
			String sumtkf = dao.getOneRs("select nvl(sum(pf),0)sum  from "+tableName+" where xh||xn||xq=? and jjf='减分' and id in("+pkvString+") ", new String[]{pkValue},"sum");
			sumtjf  = Base.isNull(sumtjf)?"0":sumtjf;
			sumtkf = Base.isNull(sumtkf)?"0":sumtkf;
			String sumt =String.valueOf(Float.parseFloat(sumtjf)-Float.parseFloat(sumtkf));
			String[] fzValue = getFzsxForMkdm(xxklx);									
			shzf = Float.parseFloat(sum)+Float.parseFloat(sumt);
			if(fzValue!=null){
				jcf = Float.parseFloat(Base.isNull(fzValue[0])?"0":fzValue[0]);
				zgf = Float.parseFloat(Base.isNull(fzValue[1])?"0":fzValue[1]);
			}
			if(shzf+jcf>zgf){
				if("ivlt".equals(xxk)){
					retStr = "IVT论坛基础分："+jcf+" 最高分："+zgf+" ，现提交总分已超过最高分，请确认后重新提交！";
				}else if("wthd".equals(xxk)){
					retStr = "文团活动基础分："+jcf+" 最高分："+zgf+" ，现提交总分已超过最高分，请确认后重新提交！";
				}else if("yybd".equals(xxk)){
					retStr = "语言表达基础分："+jcf+" 最高分："+zgf+" ，现提交总分已超过最高分，请确认后重新提交！";
				}else{
					retStr = "读书活动基础分："+jcf+" 最高分："+zgf+" ，现提交总分已超过最高分，请确认后重新提交！";
				}
				retStr+="\n\n公式：基础分+已通过得分+所选记录得分<=最高分";
			}
		} 
		return retStr;
	}
	/**
	 * 得分限制方法
	 * @param mkType zznl"组织能力",shsj"社会实践"
	 * @param id
	 * @param pkValue
	 * @return
	 */
	public String getPointLimit(String mkType,String id,String pkValue){
		String retStr="";
		float jcf=0;//基础分
		float zgf=0;//最高分
		float shzf=0;//所获总分
		if("zznl".equalsIgnoreCase(mkType)){//组织能力
//			String[] pkvTem = id.split("!!");
			String pkvString = id;
//			for(int i=0;i<pkvTem.length;i++){
//				pkvString += pkvTem[i];
//				pkvString += "',";
//			}
//			pkvString.subSequence(0,pkvString.length()-1);
			//已通过的所获分
			String sumjf = dao.getOneRs("select nvl(sum(shfz),0)sum  from szyc_zznlfzb where xh||xn||xq=? and jjf='加分' and id not in("+pkvString+") and xxsh='通过' ", new String[]{pkValue},"sum");
			String sumkf = dao.getOneRs("select nvl(sum(shfz),0)sum  from szyc_zznlfzb where xh||xn||xq=? and jjf='加分' and id not in("+pkvString+") and xxsh='通过' ", new String[]{pkValue},"sum");
			sumjf  = Base.isNull(sumjf)?"0":sumjf;
			sumkf = Base.isNull(sumkf)?"0":sumkf;
			String sum =String.valueOf(Float.parseFloat(sumjf)-Float.parseFloat(sumkf));
			//所选中记录的所获分
			String sumtjf = dao.getOneRs("select nvl(sum(shfz),0)sum  from szyc_zznlfzb where xh||xn||xq=? and jjf='加分' and id in("+pkvString+") ", new String[]{pkValue},"sum");
			String sumtkf = dao.getOneRs("select nvl(sum(shfz),0)sum  from szyc_zznlfzb where xh||xn||xq=? and jjf='减分' and id in("+pkvString+") ", new String[]{pkValue},"sum");
			sumtjf  = Base.isNull(sumtjf)?"0":sumtjf;
			sumtkf = Base.isNull(sumtkf)?"0":sumtkf;
			String sumt =String.valueOf(Float.parseFloat(sumtjf)-Float.parseFloat(sumtkf));
			String[] fzValue = getFzsxForMkdm(mkType);									
			shzf = Float.parseFloat(sum)+Float.parseFloat(sumt);
			if(fzValue!=null){
				jcf = Float.parseFloat(Base.isNull(fzValue[0])?"0":fzValue[0]);
				zgf = Float.parseFloat(Base.isNull(fzValue[1])?"0":fzValue[1]);
			}
			if(shzf+jcf>zgf){
				retStr = "组织能力基础分："+jcf+" 最高分："+zgf+" ，现提交总分已超过最高分，请确认后重新提交！";
				retStr+="\n\n公式：基础分+已通过得分+所选记录得分<=最高分";
			}
		} else if("shsj".equalsIgnoreCase(mkType)){//组织能力
			String pkvString = id;
			//已通过的所获分
			String sumjf = dao.getOneRs("select nvl(sum(shfz),0)sum  from szyc_shsjfzb where xh||xn||xq=? and jjf='加分' and id not in("+pkvString+") and xxsh='通过' ", new String[]{pkValue},"sum");
			String sumkf = dao.getOneRs("select nvl(sum(shfz),0)sum  from szyc_shsjfzb where xh||xn||xq=? and jjf='加分' and id not in("+pkvString+") and xxsh='通过' ", new String[]{pkValue},"sum");
			sumjf  = Base.isNull(sumjf)?"0":sumjf;
			sumkf = Base.isNull(sumkf)?"0":sumkf;
			String sum =String.valueOf(Float.parseFloat(sumjf)-Float.parseFloat(sumkf));
			//所选中记录的所获分
			String sumtjf = dao.getOneRs("select nvl(sum(shfz),0)sum  from szyc_shsjfzb where xh||xn||xq=? and jjf='加分' and id in("+pkvString+") ", new String[]{pkValue},"sum");
			String sumtkf = dao.getOneRs("select nvl(sum(shfz),0)sum  from szyc_shsjfzb where xh||xn||xq=? and jjf='减分' and id in("+pkvString+") ", new String[]{pkValue},"sum");
			sumtjf  = Base.isNull(sumtjf)?"0":sumtjf;
			sumtkf = Base.isNull(sumtkf)?"0":sumtkf;
			String sumt =String.valueOf(Float.parseFloat(sumtjf)-Float.parseFloat(sumtkf));
			String[] fzValue = getFzsxForMkdm(mkType);									
			shzf = Float.parseFloat(sum)+Float.parseFloat(sumt);
			if(fzValue!=null){
				jcf = Float.parseFloat(Base.isNull(fzValue[0])?"0":fzValue[0]);
				zgf = Float.parseFloat(Base.isNull(fzValue[1])?"0":fzValue[1]);
			}
			if(shzf+jcf>zgf){
				retStr = "社会实践基础分："+jcf+" 最高分："+zgf+" ，现提交总分已超过最高分，请确认后重新提交！";
				retStr+="\n\n公式：基础分+已通过得分+所选记录得分<=最高分";
			}
		} 
		return retStr;
	}
	
	/**
	 * 社会实践、组织能力汇总
	 */
	public List<HashMap<String,String>> dao_getShsjHz(ShsjModel model,String tableName,String userType,String userName){
		DAO dao      = DAO.getInstance();
		List<HashMap<String,String>> result = new ArrayList<HashMap<String,String>>();		
		String[] colList = new String[]{"xh","xm","shfz",};
		result = dao.getList(shsjHzTj(model,tableName,userType,userName), new String[]{},colList);
		return result;
	}
	/**
	 * 社会实践汇总、组织能力条件
	 */
	public String shsjHzTj(ShsjModel model,String tableName,String userType,String userName){
		String xh = "";
		if (model.getXh() != null && model.getXh().length > 0) {
			xh = model.getXh()[0];
		}
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xm   = model.getXm();
		String xn   = model.getXn();
		String xq   = model.getXq();
		StringBuffer querry = new StringBuffer(" where 1=1 ");		
		querry.append(Base.isNull(xh)?"":" and xh='"+xh.trim()+"' ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
		querry.append(Base.isNull(xm)?"":" and xm like '%"+xm.trim()+"%' ");
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");	
		if("bzr".equalsIgnoreCase(userType)){
			querry.append("and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='"+userName+"')");
		}
		querry.append(" and xxsh='通过' order by xh");	
		StringBuffer  sql = new StringBuffer();
		if("szyc_shsjfzb".equalsIgnoreCase(tableName)){
			sql.append("select xh,xm,shfz from(select a.xn,a.xq,a.xh,a.jjf||':'||a.shfz shfz,a.jjf,a.xxsh,b.xm,b.nj,b.xydm,b.zydm,b.bjdm ");
			sql.append("from szyc_shsjfzb a left join view_xsjbxx b on a.xh=b.xh) a ");
		}else if("szyc_zznlfzb".equalsIgnoreCase(tableName)){
			sql.append("select xh,xm,shfz from(select a.xn,a.xq,a.xh,a.jjf||':'||a.shfz shfz,a.jjf,a.xxsh,b.xm,b.nj,b.xydm,b.zydm,b.bjdm ");
			sql.append("from szyc_zznlfzb a left join view_xsjbxx b on a.xh=b.xh) a ");
		}
		return sql+querry.toString();
	}
	/**
	 * 社会实践、组织能力汇总记录按学号分组获取最大数
	 */
	public  String maxJl(ShsjModel model,String tableName,String userType,String userName){
		String sql = "select max(cout)cout from (select count(xh)cout from( "+shsjHzTj(model,tableName,userType,userName) +") group by xh )";
		return dao.getOneRs(sql,new String[]{},"cout");
	}
	public ArrayList<String[]> getZhszfCjList(ZhszcpModel model,String userType,String userName) {
		ArrayList<String[]> result = new ArrayList<String[]>();
		String xh = model.getXh();
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xm   = model.getXm();
		String xn   = model.getXn();
		String xq   = model.getXq();
		String zhszzf = model.getZhszzf();
		
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		querry.append(Base.isNull(xh)?"":" and xh='"+xh.trim()+"' ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
		querry.append(Base.isNull(xm)?"":" and xm like '%"+xm.trim()+"%' ");	
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry.append(Base.isNull(xn)?"":" and xq='"+xq+"' ");	
		querry.append(StringUtils.isNull(zhszzf)?"":" and zhszzf='"+zhszzf+"' ");
		
		String[] colList = new String[]{"xh","xn","xqmc","xm","xymc","bjmc","wsmkf","shsj","zznl","dshdf","ivtlt","wthd","yybdf","xthd","zhszzf","zhszpm"};
		String  sql = "select rownum r,xh,xn,xqmc,xm,xymc,bjmc,wsmkf,shsj,zznl,dshdf,ivtlt,wthd,yybdf,xthd,zhszzf,zhszpm from view_szgy_zhszcp";
		if ("bzr".equalsIgnoreCase(userType)) {
			querry.append(" and exists(select 1 from fdybjb a where a.zgh='"+userName+"' and a.bjdm = view_szgy_zhszcp.bjdm)");
		}else if("xy".equalsIgnoreCase(userType)){
			querry.append(" and xydm= (select distinct (b.xydm) from yhb a, view_njxyzybj b where a.yhm = '"+userName+"' and a.szbm = b.xydm)");
		}
		try {
			result = CommonQueryDAO.commonQuery(sql, querry.toString(), new String[]{}, colList, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean autoAccount(String xn, String xq, String xydm, String zydm, String bjdm)
			throws Exception {
		String delWh = "";
		String insWh = "";
		
		delWh = " and exists (select 1 from view_xsjbxx b where b.xh=a.xh ";
		insWh = " (select b.xh from view_xsjbxx b where 1=1 ";
			
		if(StringUtils.isNotNull(xydm)){
			delWh += "and b.xydm='" + xydm + "' ";
			insWh += "and b.xydm='" + xydm + "' ";
		}
		
		if(StringUtils.isNotNull(zydm)){
			delWh += "and b.zydm='" + zydm + "' ";
			insWh += "and b.zydm='" + zydm + "' ";
		}

		if(StringUtils.isNotNull(bjdm)){
			delWh += "and b.bjdm='" + bjdm + "' ";
			insWh += "and b.bjdm='" + bjdm + "' ";
		}
		
		delWh += ")";
		insWh += ")";
		
		return dao.runProcuder("{call szgyyq_zhszcp_autocount(?, ?, ?, ?)}",
						new String[] { xn, xq, delWh, insWh });
	}
	public List<HashMap<String, String>> getZhszfCjTitle() {
		String[] colList = new String[]{"xh","xn","xq","xm","xymc","bjmc","wsmkf","shsj","zznl","dshdf","ivtlt","wthd","yybdf","xthd","zhszzf","zhszpm"};
		String[] colListCN = dao.getColumnNameCN(colList, "view_szgy_zhszcp");
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);// 表头
		return topTr;
	}
	/**
	 *学团活动.汇总
	 * @throws Exception 
	 */
	public List<HashMap<String,String>> dao_exportDataXthd(XthdModel model,String xxk, String userType,String userName,OutputStream os)
	throws Exception {
		DAO dao      = DAO.getInstance();
		List<HashMap<String,String>> result = new ArrayList<HashMap<String,String>>();		
		String[] colList = new String[]{"xh","xm","pf",};
		result = dao.getList(xthdHzTj(model,xxk,userType,userName), new String[]{},colList);
		return result;
	}
	/**
	 * 学团活动汇总条件
	 */
	public String xthdHzTj(XthdModel model,String xxk,String userType,String userName){
		String xh = "";
		if (model.getXh() != null && model.getXh().length > 0) {
			xh = model.getXh()[0];
		}
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xm   = model.getXm();
		String xn   = model.getXn();
		String xq   = model.getXq();
		String tableName="szyq_dshdjzb";
		if("ivlt".equals(xxk)){
			tableName = "szyq_ivtltb";
		}else if("wthd".equals(xxk)){
			tableName = "szyq_xthddjb";
		}else if("yybd".equals(xxk)){
			tableName = "szyq_yybdjzb";
		}else{
			tableName = "szyq_dshdjzb";
		}
		StringBuffer querry = new StringBuffer(" where 1=1 ");		
		querry.append(Base.isNull(xh)?"":" and xh='"+xh.trim()+"' ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
		querry.append(Base.isNull(xm)?"":" and xm like '%"+xm.trim()+"%' ");
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
		if("bzr".equalsIgnoreCase(userType)){
			querry.append("and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='"+userName+"')");
		}
		querry.append(" and xxsh='通过' order by xh");		
		StringBuffer  sql = new StringBuffer();
		sql.append("select xh,xm,pf from(select a.xn,a.xq,a.xh,a.jjf||':'||a.pf pf,a.jjf,a.xxsh,b.xm,b.nj,b.xydm,b.zydm,b.bjdm ");
		sql.append("from "+tableName+" a left join view_xsjbxx b on a.xh=b.xh) a ");
		
		//System.out.println(sql+querry.toString());
		return sql+querry.toString();
	}
	/**
	 * 学团活动汇总记录按学号分组获取最大数
	 */
	public  String maxXthd(XthdModel model,String xxk,String userType,String userName){
		String sql = "select max(cout)cout from (select count(xh)cout from( "+xthdHzTj(model,xxk,userType,userName) +") group by xh )";
		//System.out.println(sql);
		return dao.getOneRs(sql,new String[]{},"cout");
	}
	
	/**
	 * 返回班主任所属班级学生列表
	 * @param zgh 职工号
	 * @return list
	 * @author luojw
	 */
	public List<HashMap<String, String>> getBzrXsList(String tableName,
			String zgh, String xn, String xq, String nj, String xh, String xm,
			String xydm, String zydm, String bjdm) {
		nj = Base.isNull(nj) ? "%" : nj;
		xh = Base.isNull(xh) ? "%" : xh;
		xm = Base.isNull(xm) ? "%" : xm;
		xydm = Base.isNull(xydm) ? "%" : xydm;
		zydm = Base.isNull(zydm) ? "%" : zydm;
		bjdm = Base.isNull(bjdm) ? "%" : bjdm;

		StringBuffer sb = new StringBuffer();
		sb.append("select a.xh, a.xm, a.xb, a.xymc,a.zymc,a.bjmc from view_xsjbxx a where exists (select 1 ");
		sb.append("from fdybjb b where a.bjdm = b.bjdm and b.zgh = ?)");
		sb.append("and a.nj like ? ");
		sb.append("and a.xh like ? ");
		sb.append("and a.xm like ? ");
		sb.append("and a.xydm like ? ");
		sb.append("and a.zydm like ? ");
		sb.append("and a.bjdm like ? order by a.xydm,a.zydm,a.bjdm,a.xh");
		List<HashMap<String, String>> list = dao.getList(sb.toString(),
				new String[] { zgh, nj, xh, xm, xydm, zydm, bjdm },
				new String[] { "xh", "xm", "xb", "xymc", "zymc", "bjmc" });
		//System.out.print(sb);
		return list;
	}
	
	/**
	 * 获得加减分原因列表
	 * @return list
	 * @author luojw
	 */
	public List<HashMap<String, String>> getYyList(){
		DAO dao = DAO.getInstance();
		String sql = "select dm,mc from szyc_jjfyyb order by dm";
		return dao.getList(sql, new String[] {}, new String[] { "dm", "mc" });
	}

	/**
	 * 保存审核状态
	 * @return list
	 * @author luojw
	 * @throws Exception
	 */
	public boolean saveSh(String tableName, String[] key, String shzt)
			throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		sql.append("update " + tableName + " set xxsh ='" + shzt + "'");
		for (int i = 0; i < key.length; i++) {
			if (i == 0) {
				sql.append(" where xh||xn||xq ='" + key[i] + "'");
			} else {
				sql.append(" or xh||xn||xq ='" + key[i] + "'");
			}
		}

		return dao.runUpdate(sql.toString(), new String[] {});
	}
	
	/**
	 * 获得班主任班级列表
	 * @return list
	 * @author luojw
	 */
	public List<HashMap<String, String>> getBzrBj(String zgh){
		DAO dao = DAO.getInstance();
		String sql = "select bjdm, bjmc from view_njxyzybj a where exists (select 1 from fdybjb b where a.bjdm = b.bjdm and b.zgh = ? )";
		return dao.getList(sql, new String[] {zgh}, new String[] { "bjdm", "bjmc" });
	}
	
	/**
	 * 获得班主任下属班级列表
	 * 
	 * @return list
	 * @author luojw
	 */
	public List<HashMap<String, String>> getBjList(String userName) {
		DAO dao = DAO.getInstance();
		String sql = "select a.bjdm, a.bjmc from view_njxyzybj a where exists (select 1 from"
				+ " fdybjb b where a.bjdm = b.bjdm and b.zgh = ?) order by a.bjdm";
		return dao.getList(sql, new String[] { userName }, new String[] {
				"bjdm", "bjmc" });
	}
	
	/**
	 * 获得指定表的指定字段
	 * 
	 * @author luojw
	 */
	public String getOneValue(String tableName, String dm, String pk, String pkValue) {
		
		DAO dao = new DAO();
		
		return dao.getOneValue(tableName, dm, pk, pkValue);
	}
}
