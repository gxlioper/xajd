/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description:安徽建筑工业学院公寓管理DAO </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-7-1 下午01:54:37</p>
 */
package xgxt.xsgygl.zjlgdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;

public class GyglZjlgDAO{
	
	ArrayList<String> values = null;//查询条件值列表
	/**
	 *拼查询条件
	 */
	public String getQueryWhereSql_db(GyglZjlgModel model){
		values = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		sb.append(" 1=1 ");
		if(!Base.isNull(model.getXn())){
			sb.append("and xn=? ");
			values.add(model.getXn());
		}
		if(!Base.isNull(model.getXq())){
			sb.append("and xq=? ");
			values.add(model.getXq());
		}
		if(!Base.isNull(model.getLddm())){
			sb.append("and lddm=? ");
			values.add(model.getLddm());
		}
		if(!Base.isNull(model.getLc())){
			sb.append("and cs=? ");
			values.add(model.getLc());
		}
		if(!Base.isNull(model.getQsh())){
			sb.append("and qsh=? ");
			values.add(model.getQsh());
		}
		if(!Base.isNull(model.getXxsh())){
			sb.append("and xxsh=? ");
			values.add(model.getXxsh());
		}
		if(!Base.isNull(model.getXysh())){
			sb.append("and xysh=? ");
			values.add(model.getXysh());
		}
		if(!Base.isNull(model.getSqrq1())){
			sb.append("and sqsj >= ? ");
			values.add(model.getSqrq1());
		}
		if(!Base.isNull(model.getSqrq2())){
			sb.append("and sqsj <= ? ");
			values.add(model.getSqrq2());
		}
		if(!Base.isNull(model.getSfcx()) && "是".equals(model.getSfcx())){
			sb.append("and sfcx = ? ");
			values.add(model.getSfcx());
		} 
		if(!Base.isNull(model.getSfcx()) && "否".equals(model.getSfcx())){
			sb.append("and sfcx is null ");
		}
		return sb.toString();
	}
	
   
	/**
	 * 获得最近一次申请A级寝室情况
	 */
	public HashMap<String,String> getAjqssqqk_db(String xh){
		DAO dao = DAO.getInstance();
		String sql = "select * from (select a.*,b.lxdh,sqsj,xxsh,shsj,sqly,to_char(sysdate,'yyyymmdd') xtsj,xn,(select xqmc from xqdzb where xqdm=b.xq) xq from (select lddm,cs lc,qsh,ssbh from view_xszsxx where xh=?) a left join ajqsb b" +
				" on a.ssbh=b.ssbh and b.xn=? and b.xq=? order by sqsj desc) where rownum=1";
		List<HashMap<String,String>> list = dao.getListNotOut(sql, new String[]{xh,Base.currXn,Base.currXq});
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 保存A级寝室
	 * @throws Exception 
	 */
	public boolean saveAjqs_db(GyglZjlgModel model) throws Exception{
		String sql = "";
		String[] values = null;
		DAO dao = DAO.getInstance();
		String lxdh = DealString.toGBK(model.getLxdh());
		String sqly = DealString.toGBK(model.getSqly());
		if("apply".equals(model.getAct())){
			sql = "insert into ajqsb (xn,xq,ssbh,lxdh,sqly) values(?,?,?,?,?)";
			values = new String[]{Base.currXn,Base.currXq,model.getSsbh(),lxdh,sqly};
		}else{
			sql = "update ajqsb set lxdh=?,sqly=? where ssbh=? and sqsj=?";
			values = new String[]{lxdh,sqly,model.getSsbh(),model.getSqsj()};
		}
		return dao.runUpdate(sql, values);
	}
	
	/**
	 * 查询当前是否A级寝室
	 * @throws Exception 
	 */
	public boolean isAjqs_db(String xh) {
		String sql = "select ssbh from ajqsb where xxsh='通过' and sfcx is null" +
				" and xn=? and xq=? and ssbh in(select ssbh from xszsxxb where xh=?)";
		DAO dao = DAO.getInstance();
		String ssbh = dao.getOneRs(sql, new String[]{Base.currXn,Base.currXq,xh}, "ssbh");
		if(Base.isNull(ssbh)){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 查询是否符合必须有过A级寝室的条件
	 * @throws Exception 
	 */
	public boolean isAjqsCondition_db(String xh,String lb) {
		String sql = "";
		String[] colVal= null;
		if("wmqs".equals(lb)){
			sql = "select ssbh from ajqsb where xxsh='通过' and rownum=1" +
			" and xn=? and xq=? and ssbh in(select ssbh from xszsxxb where xh=?)";
			colVal = new String[]{Base.currXn,Base.currXq,xh};
		}else if("tsqs".equals(lb)){
			sql = "select ssbh from ajqsb where xxsh='通过' and rownum=1" +
			" and xn=? and ssbh in(select ssbh from xszsxxb where xh=?)";
			colVal = new String[]{Base.currXn,xh};
		}
		DAO dao = DAO.getInstance();
		String ssbh = dao.getOneRs(sql, colVal, "ssbh");
		if(Base.isNull(ssbh)){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 审核A级寝室查询
	 */
	public List<HashMap<String,String>> queryAjqsSh_db(GyglZjlgForm form,GyglZjlgModel model){
		DAO dao = DAO.getInstance();
		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();
		StringBuffer querysb = new StringBuffer();
		ArrayList<String> list = new ArrayList<String>();
		List<HashMap<String,String>> rs = new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>> rs1 = new ArrayList<HashMap<String,String>>();
		querysb.append(" 1=1 ");
		String query = "";
		String temp = "";
		if(!Base.isNull(model.getSqrq1())){
			querysb.append(" and jcsj<? ");
			list.add(model.getSqrq1());
		}else if(!Base.isNull(model.getSqrq2())){
			querysb.append(" and jcsj<? ");
			list.add(model.getSqrq2());
			temp = " where sqsj<= '"+model.getSqrq2()+"'";
		}
		if(!Base.isNull(model.getFs())){
			querysb.append(" and fs>=?  ");
			list.add(model.getFs());
		}
		if(!Base.isNull(model.getZs())){
			query = " and jcsj>cxsj ";
		}
		list.add(model.getXn());
		list.add(model.getXq());
		try {
			sb.append("select a.*,ssbh||sqsj pk from (select c.*,d.ldmc,d.cs lc,d.qsh,rownum r,(select xqmc from xqdzb where xqdm=c.xq)xqmc from ajqsb c,view_ssxx d where c.ssbh=d.ssbh and ");
			sb.append(getQueryWhereSql_db(model));
			
			if(!Base.isNull(model.getFs()) || !Base.isNull(model.getZs())){
				sb.append(" and exists (select 1 from (select a.ssbh,zs,a.jcsj,rank() over (partition by a.ssbh order by zs desc) px");
				sb.append(" from gywsjcb a,(select ssbh, nvl(max(cxsj),'00000000') cxsj from ajqsb "+temp+" group by ssbh) b where a.ssbh=b.ssbh ");
				sb.append(query+" and "+querysb.toString()+" and xn=? and xq=?) a,(select a.ssbh,zs,rank() over (partition by a.ssbh ");
				sb.append("order by zs desc) px from gywsjcb a,(select ssbh, nvl(max(cxsj),'00000000') cxsj from ajqsb "+temp+" group by ssbh) b where ");
				sb.append("a.ssbh=b.ssbh "+query+" and "+querysb.toString()+" and xn=? and xq=?) b where a.ssbh=b.ssbh and a.zs-b.zs=? and ");
				sb.append("a.px+a.zs=b.px+b.zs and a.ssbh=c.ssbh and c.sqsj>a.jcsj) ");
			
				values.addAll(list);
				values.addAll(list);
				if(!Base.isNull(model.getZs())){
					values.add((Integer.parseInt(model.getZs())-1)+"");
				}else{
					values.add("0");
				}
			}
			
			sb.append(") a ");
			sb1.append(sb.toString());
			sb1.append("where r<="+(form.getPages().getStart() + form.getPages().getPageSize()));
			sb1.append(" and r>");
			sb1.append(form.getPages().getStart());

			rs = dao.getListNotOut(sb1.toString(), values.toArray(new String[0]));
			rs1 = dao.getListNotOut(sb.toString(), values.toArray(new String[0]));
			if(rs1 != null){
				form.getPages().setMaxRecord(rs1.size());
			}else{
				form.getPages().setMaxRecord(0);
			}
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			form.getPages().setMaxRecord(0);
			return rs;
		}
	}
	
	/**
	 * 审核A级寝室及维护
	 */
	public boolean ajqssh_db(GyglZjlgModel model,String doType,String pks){
		DAO dao = DAO.getInstance();
		boolean flag = false;
		String xxsh = "";
		String sql = "";
		if("pass".equals(doType)){
			xxsh = "通过";
		}else{
			xxsh = "不通过";
		}
		try {
			sql = "update ajqsb set xxsh=?, shsj=to_char(sysdate,'yyyymmdd') where instr(?,'!@!'||ssbh||sqsj||'!@!')>0";
			flag = dao.runUpdate(sql, new String[]{xxsh,pks});
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 手动撤消或解除撤消A级寝室
	 */
	public boolean ajqscx_db(String pks,String doType){
		DAO dao = DAO.getInstance();
		boolean flag = false;
		String sql = "";
		String sj = "''";
		String[] colv = null;
		if("cx".equals(doType)){
			sj = "to_char(sysdate,'yyyymmdd')";
			colv = new String[]{"是",pks};			
		}else{
			colv = new String[]{"",pks};
		}
		try {
			sql = "update ajqsb set sfcx=? , cxsj="+sj+" where instr(?,'!@!'||ssbh||sqsj||'!@!')>0";
			flag = dao.runUpdate(sql, colv);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	/**
	 * 删除A级寝室
	 */
	public boolean ajqsdele_db(String pks){
		DAO dao = DAO.getInstance();
		boolean flag = false;
		String sql = "";
		try {
			sql = "delete from ajqsb where instr(?,'!@!'||ssbh||sqsj||'!@!')>0";
			flag = dao.runUpdate(sql, new String[]{pks});
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	/**
	 * 自动撤消A级寝室
	 */
	public boolean ajqscxAuto_db(){
		DAO dao = DAO.getInstance();
		boolean flag = false;
		String fs = "85";
		String cs = "3";
		String xn = Base.currXn;
		String xq = Base.currXq;
		StringBuffer sb = new StringBuffer();
		sb.append("update ajqsb a set sfcx=?,cxsj=(select jcsj from (select a.jcsj,a.ssbh,rank() over(partition by a.ssbh order by a.jcsj nulls ");
		sb.append("last)px from gywsjcb a,(select distinct max(shsj) shsj,ssbh from ajqsb where xxsh='通过' and sfcx is null and xn=? and xq=? group by ssbh)");
		sb.append("b where a.ssbh=b.ssbh and a.jcsj > b.shsj and fs<? and a.xn=? and a.xq=?) where px=? and ssbh=a.ssbh) where exists (");
		sb.append("select 1 from (select a.jcsj,a.ssbh,rank() over(partition by a.ssbh order by a.jcsj nulls last)px from gywsjcb a,(select distinct max(shsj) ");
		sb.append("shsj,ssbh from ajqsb where xxsh='通过' and sfcx is null and xn=? and xq=? group by ssbh)");
		sb.append("b where a.ssbh=b.ssbh and a.jcsj > b.shsj and fs<? and a.xn=? and a.xq=?) where px=? and ssbh=a.ssbh) and xn=? and xq=? and sfcx is null");
		String[] colv = new String[]{"是",xn,xq,fs,xn,xq,cs,xn,xq,fs,xn,xq,cs,xn,xq};
		try {
			flag = dao.runUpdate(sb.toString(),colv);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 获得申请寝室信息
	 */
	public HashMap<String,String> getQssqxx_db(String pkValue){
		DAO dao = DAO.getInstance();
		String sql = "select a.*,b.ldmc,b.cs,b.qsh,(select xqmc from xqdzb where xqdm=a.xq) xqmc from ajqsb a,view_ssxx b where a.ssbh=b.ssbh and a.ssbh||a.sqsj=?";
		List<HashMap<String,String>> list = dao.getListNotOut(sql, new String[]{pkValue});
		if(list != null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 获得寝室成员
	 */
	public String[] getQscy_db(String ssbh){
		DAO dao = DAO.getInstance();
		List<String> templist = new ArrayList<String>();
		String sql = "select xm from view_xszsxx where ssbh=?";
		List<String[]> list = dao.rsToVator(sql, new String[]{ssbh}, new String[]{"xm"});
		if(list != null){
			for(int i=0;i<list.size();i++){
				templist.add(list.get(i)[0]);
			}
		}
		if(templist.size()>0){
			return templist.toArray(new String[0]);
		}else{
			return null;
		}
	}
	/**
	 * 获得寝室成员的成绩
	 */
	public List<String[]> getQscycj_db(String ssbh){
		DAO dao = DAO.getInstance();
		HashMap<String,String> map = dao.getBeforeXqInfo();
		String sql = "select xn,(select xqmc from xqdzb where xqdm=a.xq) xq,xm,bjmc,kcmc,kcxz,cj from view_cjb a where xn=? and a.xq=? and xh in(select xh from xszsxxb where ssbh=?) order by xh";
		return dao.rsToVator(sql, new String[] {map.get("xn"),map.get("xq"),ssbh}, new String[] { "xn", "xq","xm","bjmc", "kcmc", "kcxz","cj" });
	}
	/**
	 * 获得连续两周成绩
	 */
	public List<HashMap<String,String>> getTwoWeekWscj_db(String ssbh,String sqsj,String xn,String xq){
		DAO dao = DAO.getInstance();
		String[] cjz = null;
		StringBuffer sb = new StringBuffer();
		sb.append("select max(zs) zs from (select distinct a.ssbh,a.zs from (select ssbh,zs,rank() over (partition by ssbh");
		sb.append(" order by zs desc) px from gywsjcb where jcsj < ? and jcsj>(select nvl(max(cxsj),'00000000') from ajqsb where ssbh=? and cxsj<?) and fs>=? and xn=? and xq=? and ssbh=? ) a,(select ssbh,zs,rank() over");
		sb.append(" (partition by ssbh order by zs desc) px from gywsjcb where jcsj < ? and jcsj>(select nvl(max(cxsj),'00000000') from ajqsb where ssbh=? and cxsj<?) and fs>=? and xn=? and xq=? and ssbh=? ) b where");
		sb.append(" a.ssbh=b.ssbh and a.zs-b.zs=1 and a.px+a.zs=b.px+b.zs) group by ssbh");
		String[] colv = new String[]{sqsj,ssbh,sqsj,"85",xn,xq,ssbh,sqsj,ssbh,sqsj,"85",xn,xq,ssbh};
		String zs = dao.getOneRs(sb.toString(), colv, "zs");
		if(!Base.isNull(zs)){
			cjz = new String[]{zs,Integer.parseInt(zs)-1+"",xn,xq,ssbh};
		}else{
			return null;
		}
		String sql = "select zs,fs from gywsjcb where (zs=? or zs=?) and xn=? and xq=? and ssbh=?order by zs";
		return dao.getList(sql, cjz, new String[]{"zs","fs"});
	}
	
	/**
	 * A级寝室审核结果查询
	 */
	public List<HashMap<String,String>> queryAjqsShcx_db(GyglZjlgForm form,GyglZjlgModel model){
		DAO dao = DAO.getInstance();
		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();
		StringBuffer querysb = new StringBuffer();
		ArrayList<String> list = new ArrayList<String>();
		List<HashMap<String,String>> rs = new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>> rs1 = new ArrayList<HashMap<String,String>>();
		querysb.append(" 1=1 ");
		if(!Base.isNull(model.getSqrq1())){
			querysb.append(" and sqsj>=? ");
			list.add(model.getSqrq1());
		}
		if(!Base.isNull(model.getSqrq2())){
			querysb.append(" and sqsj<=? ");
			list.add(model.getSqrq2());
		}
		try {
			sb.append("select a.*,ssbh||sqsj pk from (select a.*,b.ldmc,b.cs lc,b.qsh,rownum r,(select xqmc from xqdzb where xqdm=a.xq)xqmc from ajqsb a,view_ssxx b where a.ssbh=b.ssbh and ");
			sb.append(getQueryWhereSql_db(model)+" and ");
			sb.append(querysb.toString());
			sb.append(") a ");
			sb1.append(sb.toString());
			sb1.append("where r<="+(form.getPages().getStart() + form.getPages().getPageSize()));
			sb1.append(" and r>");
			sb1.append(form.getPages().getStart());
			values.addAll(list);
			rs = dao.getListNotOut(sb1.toString(), values.toArray(new String[0]));
			rs1 = dao.getListNotOut(sb.toString(), values.toArray(new String[0]));
			if(rs1 != null){
				form.getPages().setMaxRecord(rs1.size());
			}else{
				form.getPages().setMaxRecord(0);
			}
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			form.getPages().setMaxRecord(0);
			return rs;
		}
	}
	
	/**
	 * A级寝室审核结果查询(学生)
	 */
	public List<HashMap<String,String>> xsQueryAjqsShcx_db(String xh){
		DAO dao = DAO.getInstance();
		String sql = "select a.*,b.ldmc,b.cs lc,b.qsh,a.ssbh||a.sqsj pk,(select xqmc from xqdzb where xqdm=a.xq)xqmc from ajqsb a,view_ssxx b where a.ssbh=b.ssbh and a.ssbh in(select ssbh from view_xszsxx where xh=?) order by sqsj desc";
		return dao.getListNotOut(sql, new String[]{xh});
	}
	
	/**
	 * 获得寝室所属学院
	 */
	public String getQsxymc_db(String ssbh){
		DAO dao = DAO.getInstance();
		String xymcs = "";
		String sql = "select distinct xymc from view_xszsxx a where exists (select 1 from view_xszsxx where ssbh=? and a.xh=xh)";
		List<HashMap<String,String>> list = dao.getListNotOut(sql, new String[]{ssbh});
		if(list != null && list.size()>0){
			for(int i=0;i<list.size();i++){
				xymcs += list.get(0).get("xymc")+",";
			}
			if(!Base.isNull(xymcs)){
				xymcs = xymcs.substring(0, xymcs.length()-1);
			}
		}
		return xymcs;
	}
	
	/**
	 * 获得最近一次申请文明寝室情况
	 */
	public HashMap<String,String> getWmqssqqk_db(String xh){
		DAO dao = DAO.getInstance();
		String sql = "select * from (select a.*,b.lxdh,sqsj,xysh,xxsh,sqly,to_char(sysdate,'yyyymmdd') xtsj,xn,(select xqmc from xqdzb where xqdm=b.xq) xq from (select lddm,cs lc,qsh,ssbh from view_xszsxx where xh=?) a left join wmqsb b" +
				" on a.ssbh=b.ssbh and b.xn=? and b.xq=? order by sqsj desc) where rownum=1";
		List<HashMap<String,String>> list = dao.getListNotOut(sql, new String[]{xh,Base.currXn,Base.currXq});
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 批量审核文明,特色寝室
	 */
	public boolean wmtsqssh_db(String tableName,String doType,String pks,String userType){
		DAO dao = DAO.getInstance();
		boolean flag = false;
		String sh = "";
		String shr = "";
		String sql = "";
		if("pass".equals(doType)){
			sh = "通过";
		}else{
			sh = "不通过";
		}
		if("admin".equals(userType) || "xx".equals(userType)){
			shr = "xxsh";
		}else{
			shr = "xysh";
		}
		try {
			if("admin".equals(userType) || "xx".equals(userType)){
				sql = "update "+tableName+" set "+shr+"=? where instr(?,'!@!'||ssbh||sqsj||'!@!')>0";
			}else{
				sql = "update "+tableName+" set "+shr+"=? where instr(?,'!@!'||ssbh||sqsj||'!@!')>0 and xxsh='未审核'";
			}	
			flag = dao.runUpdate(sql, new String[]{sh,pks});
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 保存文明寝室
	 * @throws Exception 
	 */
	public boolean saveWmqs_db(GyglZjlgModel model) throws Exception{
		String sql = "";
		String[] values = null;
		DAO dao = DAO.getInstance();
		String lxdh = DealString.toGBK(model.getLxdh());
		String sqly = DealString.toGBK(model.getSqly());
		if("apply".equals(model.getAct())){
			sql = "insert into wmqsb (xn,xq,ssbh,lxdh,sqly) values(?,?,?,?,?)";
			values = new String[]{Base.currXn,Base.currXq,model.getSsbh(),lxdh,sqly};
		}else{
			sql = "update wmqsb set lxdh=?,sqly=? where ssbh=? and sqsj=?";
			values = new String[]{lxdh,sqly,model.getSsbh(),model.getSqsj()};
		}
		return dao.runUpdate(sql, values);
	}
	
	/**
	 * 审核文明寝室查询
	 */
	public List<HashMap<String,String>> queryWmqsSh_db(GyglZjlgForm form,GyglZjlgModel model,String doType,String xydm){
		DAO dao = DAO.getInstance();
		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();
		ArrayList<String> list = new ArrayList<String>();
		List<HashMap<String,String>> rs = new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>> rs1 = new ArrayList<HashMap<String,String>>();
		try {
			if("xx".equals(doType) || "admin".equals(doType)){
				if(!Base.isNull(model.getZs())){
					list.add(Integer.parseInt(model.getZs())*7+"");
					sb.append("select b.*,rownum r,b.ssbh||b.sqsj pk from (select xn,xq,ssbh,min(shsj) shsj from ajqsb where xxsh = '通过'  group by xn,xq,ssbh) a,(select a.*,(to_date(sqsj,'yyyy-mm-dd')-?) sj from view_wmqsb a where ");			
					sb.append(getQueryWhereSql_db(model)+" ) b where a.ssbh=b.ssbh and a.xn=b.xn and a.xq=b.xq  and to_date(a.shsj,'yyyy-mm-dd')<=b.sj and xysh='通过'");
				}else{
					sb.append("select a.*,rownum r,a.ssbh||a.sqsj pk from view_wmqsb a where "+getQueryWhereSql_db(model)+" and xysh='通过' ");			
				}
				list.addAll(values);
			}else{
				if(!Base.isNull(model.getZs())){
					list.add(Integer.parseInt(model.getZs())*7+"");
					sb.append("select b.*,rownum r,b.ssbh||b.sqsj pk from (select xn,xq,ssbh,min(shsj) shsj from ajqsb where xxsh = '通过'  group by xn,xq,ssbh) a,(select a.*,(to_date(sqsj,'yyyy-mm-dd')-?) sj from view_wmqsb a where "+getQueryWhereSql_db(model)+" ) ");			
					sb.append("b where a.ssbh=b.ssbh and a.xn=b.xn and a.xq=b.xq and to_date(a.shsj,'yyyy-mm-dd')<=b.sj and exists(select 1 from view_xszsxx where xydm=? and ssbh=a.ssbh)");
				}else{
					sb.append("select a.*,rownum r,a.ssbh||a.sqsj pk from view_wmqsb a where "+getQueryWhereSql_db(model)+" and exists(select 1 from view_xszsxx where xydm=? and ssbh=a.ssbh)");			
				}
				list.addAll(values);
				list.add(xydm);
			}			
			sb1.append("select a.* from ("+sb.toString()+" ) a ");
			sb1.append("where r<="+(form.getPages().getStart() + form.getPages().getPageSize()));
			sb1.append(" and r>");
			sb1.append(form.getPages().getStart());
			rs = dao.getListNotOut(sb1.toString(), list.toArray(new String[0]));
			rs1 = dao.getListNotOut(sb.toString(), list.toArray(new String[0]));
			if(rs1 != null){
				form.getPages().setMaxRecord(rs1.size());
			}else{
				form.getPages().setMaxRecord(0);
			}
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			form.getPages().setMaxRecord(0);
			return rs;
		}
	}
	
	/**
	 * 删除文明，特色寝室
	 */
	public boolean wmtsqsdele_db(String pks,String userType,String tableName){
		DAO dao = DAO.getInstance();
		boolean flag = false;
		String sql = "";
		try {
			if("xx".equals(userType) || "admin".equals(userType)){
				sql = "delete from "+tableName+" where instr(?,'!@!'||ssbh||sqsj||'!@!')>0";
			}else{
				sql = "delete from "+tableName+" where instr(?,'!@!'||ssbh||sqsj||'!@!')>0 and xxsh='未审核'";
			}		
			flag = dao.runUpdate(sql, new String[]{pks});
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 获得文明寝室申请条件
	 */
	public HashMap<String,String> getSqwmqsCondition_db(String ssbh,String sqsj,String xn,String xq){
		DAO dao = DAO.getInstance();
		HashMap<String,String> map = new HashMap<String,String>();
		String sql = "select xn,(select xqmc from xqdzb where xqdm=a.xq)xq,max(shsj) shsj from ajqsb a where xn=? and xq=? and ssbh=? and to_date(shsj,'yyyy-mm-dd')<=(to_date(?,'yyyy-mm-dd')-?) group by xn,xq,ssbh";
		List<HashMap<String,String>> list= dao.getListNotOut(sql, new String[]{xn,xq,ssbh,sqsj,"14"});
		if(list != null && list.size()>0){
			map = list.get(0);
		}
		return map;
	}
	
	/**
	 * 获得申请寝室信息
	 */
	public HashMap<String,String> getWmqssqxx_db(String pkValue){
		DAO dao = DAO.getInstance();
		String sql = "select * from view_wmqsb a where a.ssbh||a.sqsj=?";
		List<HashMap<String,String>> list = dao.getListNotOut(sql, new String[]{pkValue});
		if(list != null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 文明寝室审核结果查询
	 */
	public List<HashMap<String,String>> queryWmqsShcx_db(GyglZjlgForm form,GyglZjlgModel model,String userType,String xydm){
		DAO dao = DAO.getInstance();
		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();
		String query = "";
		List<HashMap<String,String>> rs = new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>> rs1 = new ArrayList<HashMap<String,String>>();
		try {
			sb.append("select a.*,ssbh||sqsj pk from (select a.*,b.ldmc,b.cs lc,b.qsh,rownum r,(select xqmc from xqdzb where xqdm=a.xq)xqmc from wmqsb a,view_ssxx b where a.ssbh=b.ssbh and ");
			sb.append(getQueryWhereSql_db(model));
			if(!("xx".equals(userType) || "admin".equals(userType))){
				query = " and exists(select 1 from view_xszsxx where xydm=? and ssbh=a.ssbh)";
				values.add(xydm);
			}
			sb.append(query+" ) a ");
			sb1.append(sb.toString());
			sb1.append("where r<="+(form.getPages().getStart() + form.getPages().getPageSize()));
			sb1.append(" and r>");
			sb1.append(form.getPages().getStart());
			rs = dao.getListNotOut(sb1.toString(), values.toArray(new String[0]));
			rs1 = dao.getListNotOut(sb.toString(), values.toArray(new String[0]));
			if(rs1 != null){
				form.getPages().setMaxRecord(rs1.size());
			}else{
				form.getPages().setMaxRecord(0);
			}
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			form.getPages().setMaxRecord(0);
			return rs;
		}
	}
	
	/**
	 * 文明寝室审核结果查询(学生)
	 */
	public List<HashMap<String,String>> xsQueryWmqsShcx_db(String xh){
		DAO dao = DAO.getInstance();
		String sql = "select a.*,b.ldmc,b.cs lc,b.qsh,a.ssbh||a.sqsj pk,(select xqmc from xqdzb where xqdm=a.xq)xqmc from wmqsb a,view_ssxx b where a.ssbh=b.ssbh and a.ssbh in(select ssbh from view_xszsxx where xh=?) order by sqsj desc";
		return dao.getListNotOut(sql, new String[]{xh});
	}
	
	/**
	 * 特色寝室审核结果查询(学生)
	 */
	public List<HashMap<String,String>> xsQueryTsqsShcx_db(String xh){
		DAO dao = DAO.getInstance();
		String sql = "select a.*,b.ldmc,b.cs lc,b.qsh,a.ssbh||a.sqsj pk from tsqsb a,view_ssxx b where a.ssbh=b.ssbh and a.ssbh in(select ssbh from view_xszsxx where xh=?) order by sqsj desc";
		return dao.getListNotOut(sql, new String[]{xh});
	}
	
	/**
	 * 获得最近一次申请特色寝室情况
	 */
	public HashMap<String,String> getTsqssqqk_db(String xh){
		DAO dao = DAO.getInstance();
		String sql = "select * from (select a.*,b.lxdh,sqsj,xysh,xxsh,jtgznr,ykzgzqk,to_char(sysdate,'yyyymmdd') xtsj,xn from (select lddm,cs lc,qsh,ssbh from view_xszsxx where xh=?) a left join tsqsb b" +
				" on a.ssbh=b.ssbh and b.xn=? order by sqsj desc) where rownum=1";
		List<HashMap<String,String>> list = dao.getListNotOut(sql, new String[]{xh,Base.currXn});
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 保存特色寝室
	 * @throws Exception 
	 */
	public boolean saveTsqs_db(GyglZjlgModel model) throws Exception{
		String sql = "";
		String[] values = null;
		DAO dao = DAO.getInstance();
		String lxdh = DealString.toGBK(model.getLxdh());
		String ykzgzqk = DealString.toGBK(model.getYkzgzqk());
		String jtgznr = DealString.toGBK(model.getJtgznr());
		if("apply".equals(model.getAct())){
			sql = "insert into tsqsb (xn,ssbh,lxdh,jtgznr,ykzgzqk) values(?,?,?,?,?)";
			values = new String[]{Base.currXn,model.getSsbh(),lxdh,jtgznr,ykzgzqk};
		}else{
			sql = "update tsqsb set lxdh=?,jtgznr=?,ykzgzqk=? where ssbh=? and sqsj=?";
			values = new String[]{lxdh,jtgznr,ykzgzqk,model.getSsbh(),model.getSqsj()};
		}
		return dao.runUpdate(sql, values);
	}
	
	/**
	 * 审核特色寝室查询
	 */
	public List<HashMap<String,String>> queryTsqsSh_db(GyglZjlgForm form,GyglZjlgModel model,String doType,String xydm){
		DAO dao = DAO.getInstance();
		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();
		List<HashMap<String,String>> rs = new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>> rs1 = new ArrayList<HashMap<String,String>>();
		try {
			if("xx".equals(doType) || "admin".equals(doType)){
				sb.append("select b.*,rownum r,b.ssbh||b.sqsj pk from view_tsqsb b where exists (select 1 from ajqsb where xxsh='通过' and ssbh=b.ssbh and xn=b.xn) and xysh='通过' and ");			
				sb.append(getQueryWhereSql_db(model));
			}else{
				sb.append("select b.*,rownum r,b.ssbh||b.sqsj pk from view_tsqsb b where exists (select 1 from ajqsb where xxsh='通过' and ssbh=b.ssbh and xn=b.xn) and ");			
				sb.append(getQueryWhereSql_db(model)+" and exists(select 1 from view_xszsxx where xydm=? and ssbh=b.ssbh)");			
				values.add(xydm);
			}			
			sb1.append("select a.* from ("+sb.toString()+" ) a ");
			sb1.append("where r<="+(form.getPages().getStart() + form.getPages().getPageSize()));
			sb1.append(" and r>");
			sb1.append(form.getPages().getStart());
			rs = dao.getListNotOut(sb1.toString(), values.toArray(new String[0]));
			rs1 = dao.getListNotOut(sb.toString(), values.toArray(new String[0]));
			if(rs1 != null){
				form.getPages().setMaxRecord(rs1.size());
			}else{
				form.getPages().setMaxRecord(0);
			}
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			form.getPages().setMaxRecord(0);
			return rs;
		}
	}
	
	/**
	 * 获得申请寝室信息
	 */
	public HashMap<String,String> getTsqssqxx_db(String pkValue){
		DAO dao = DAO.getInstance();
		String sql = "select * from view_tsqsb a where a.ssbh||a.sqsj=?";
		List<HashMap<String,String>> list = dao.getListNotOut(sql, new String[]{pkValue});
		if(list != null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 获得特色寝室申请条件
	 */
	public HashMap<String,String> getSqtsqsCondition_db(String ssbh,String sqsj,String xn){
		DAO dao = DAO.getInstance();
		HashMap<String,String> map = new HashMap<String,String>();
		String sql = "select xn,xq,max(sqsj) sqsj from wmqsb where xn=? and sqsj<=? and ssbh=? and xxsh='通过' group by xn,xq,ssbh";
		List<HashMap<String,String>> list= dao.getListNotOut(sql, new String[]{xn,sqsj,ssbh});
		if(list != null && list.size()>0){
			map = list.get(0);
		}
		return map;
	}
	
	/**
	 * 特色寝室审核结果查询
	 */
	public List<HashMap<String,String>> queryTsqsShcx_db(GyglZjlgForm form,GyglZjlgModel model,String userType,String xydm){
		DAO dao = DAO.getInstance();
		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();
		String query = "";
		List<HashMap<String,String>> rs = new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>> rs1 = new ArrayList<HashMap<String,String>>();
		try {
			sb.append("select a.*,ssbh||sqsj pk from (select a.*,b.ldmc,b.cs lc,b.qsh,rownum r from tsqsb a,view_ssxx b where a.ssbh=b.ssbh and ");
			sb.append(getQueryWhereSql_db(model));
			if(!("xx".equals(userType) || "admin".equals(userType))){
				query = " and exists(select 1 from view_xszsxx where xydm=? and ssbh=a.ssbh)";
				values.add(xydm);
			}
			sb.append(query+" ) a ");
			sb1.append(sb.toString());
			sb1.append("where r<="+(form.getPages().getStart() + form.getPages().getPageSize()));
			sb1.append(" and r>");
			sb1.append(form.getPages().getStart());
			rs = dao.getListNotOut(sb1.toString(), values.toArray(new String[0]));
			rs1 = dao.getListNotOut(sb.toString(), values.toArray(new String[0]));
			if(rs1 != null){
				form.getPages().setMaxRecord(rs1.size());
			}else{
				form.getPages().setMaxRecord(0);
			}
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			form.getPages().setMaxRecord(0);
			return rs;
		}
	}
	
	/**
	 * 楼层长队伍查询
	 */
	public ArrayList<String[]>dao_dormCadreStat(GyglZjlgModel model){
		DAO  dao = DAO.getInstance();
		String lddm = model.getLddm();
		String lc   = model.getLc();
		String qsh  = model.getQsh();
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		StringBuffer sql = new StringBuffer();
		querry.append(Base.isNull(lddm)?"":" and lddm='"+lddm+"' ");
		querry.append(Base.isNull(lc)?"":" and cs='"+lc+"' ");
		querry.append(Base.isNull(qsh)?"":" and qsh='"+qsh+"' ");
		sql.append(" select rownum r,a.* from (select xqmc,ldmc,cs,qsh, ");
		sql.append("(select xm from gygl_lzxxb a where a.lddm=b.lddm and xn='"+Base.currXn+"' and xq='"+Base.currXq+"' and rownum=1)gyfdy, ");
		sql.append("(select '('||lz||')'||(select xm from view_xsjbxx c where a.lz=c.xh) from lzxxb a where a.lddm=b.lddm and sfzz='是' and rownum=1) lz, ");
		sql.append("(select '('||cz||')'||(select xm from view_xsjbxx c where a.cz=c.xh) from czxxb a where a.lddm=b.lddm and a.lc=b.cs and sfzz='是' and rownum=1) cz, ");
		sql.append("(select '('||qsz||')'||(select xm from view_xsjbxx c where a.qsz=c.xh) from qszxxb a where a.ssbh=b.ssbh and sfzz='是' and rownum=1) qsz  ");
		sql.append("from view_ssxx b "+querry+" order by  lddm ,to_number(cs),to_number(qsh) )a");
		return dao.rsToVator(sql.toString(), new String[]{}, new String[]{"r","xqmc","ldmc","cs","qsh","gyfdy","lz","cz","qsz"});
	}

	
	/**
	 * 根据宿舍编号查询寝室长
	 * @param ssbh
	 * @return
	 */
	public String getQszBySsbh(String ssbh){
		DAO  dao = DAO.getInstance();
		String sql = "select xm from view_xsjbxx where xh =(select qsz from qszxxb where ssbh=? and rownum=1)";
		
		return dao.getOneRs(sql, new String[]{ssbh}, "xm");
	}
	
}
