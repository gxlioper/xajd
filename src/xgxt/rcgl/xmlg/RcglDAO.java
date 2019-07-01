package xgxt.rcgl.xmlg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.lang.Math;

import xgxt.DAO.DAO;
import xgxt.action.Base;


public class RcglDAO extends DAO {
	
	ArrayList<String> values = null;//查询条件值列表
	/**
	 *拼查询条件
	 */
	public String getQueryWhereSql_db(RcglForm model){
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
		if(!Base.isNull(model.getXydm())){
			sb.append("and xydm=? ");
			values.add(model.getXydm());
		}
		if(!Base.isNull(model.getXxsh())){
			sb.append("and xxsh=? ");
			values.add(model.getXxsh());
		}
		if(!Base.isNull(model.getJcsj())){
			sb.append("and jcsj = ? ");
			values.add(model.getJcsj());
		}
		return sb.toString();
	}
	
	//本系内申请保存
	public boolean  saveBxjthdsq_db(RcglForm form,String lb){
		DAO dao = DAO.getInstance();
		String table = "";
		if("bx".equals(lb)){
			table = "rcsw_bxxshdb";
		}else{
			table = "rcsw_kxxshdb";
		}
		String zzdw = form.getZzdw();
		String fzr = form.getFzr();
		String fzrdh = form.getFzrdh();
		String ddjs = form.getDdjs();
		String ddjsdh = form.getDdjsdh();
		String hdnr = form.getHdnr();
		String aqcs = form.getAqcs();
		String sql = "insert into "+table+" (bh,xn,xq,zzdw,fzr,fzrdh,ddjs,ddjsdh,cxsj,hdnr,aqcs) values(generate_id.nextval,?,?,?,?,?,?,?,?,?,?)";
		String[] coValu = new String[]{Base.currXn,Base.currXq,zzdw,fzr,fzrdh,ddjs,ddjsdh,form.getCxsj(),hdnr,aqcs};		
		try {
			return dao.runUpdate(sql, coValu);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//查询出勤统计
	public List<HashMap<String,String>> queryCqtj_db(RcglForm form){
		DAO dao = DAO.getInstance();
		String sql = "select xydm||jcsj pk,a.*,rownum r from view_cqtjb a where "+getQueryWhereSql_db(form);
		List<HashMap<String,String>> temlist = dao.getList(sql, values.toArray(new String[0]), new String[]{"pk"});
		form.getPages().setMaxRecord(temlist.size());
		sql = "select * from ("+sql+") where r<="+(form.getPages().getStart() + form.getPages().getPageSize())+ "and r>"+form.getPages().getStart();
		return dao.getListNotOut(sql, values.toArray(new String[0]));
	}
	
	//增加出勤统计
	public boolean addCqtj_db(RcglForm form){
		DAO dao = DAO.getInstance();
		String cql = Math.round((Float.parseFloat(form.getSdrs())*10000/Integer.parseInt(form.getYdrs())))+"";
		cql = Float.parseFloat(cql)/100+"%";
		String sql = "insert into rcsw_cqtjb (xn,xq,xydm,ydrs,sdrs,qjrs,kkrs,jcsj,cql) values (?,?,?,?,?,?,?,?,?)";
		String[] colval = new String[]{Base.currXn,Base.currXq,form.getXydm(),form.getYdrs(),form.getSdrs(),form.getQjrs(),
				form.getKkrs(),form.getJcsj(),cql};
		try {
			return dao.runUpdate(sql, colval);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//获得出勤统计修改信息
	public HashMap<String,String> getCqtjUpdateXx_db(String pk){
		DAO dao = DAO.getInstance();
		HashMap<String,String> map = new HashMap<String,String>();
		String sql = "select * from view_cqtjb where xydm||jcsj=?";
		List<HashMap<String,String>> list = dao.getListNotOut(sql, new String[]{pk});
		if(list != null && list.size()>0){
			map =  list.get(0);
		}
		return map;
	}
	
	//保存出勤统计修改信息
	public boolean saveCqtjUpdateXx_db(String pk,RcglForm form){
		DAO dao = DAO.getInstance();
		String cql = Math.round((Float.parseFloat(form.getSdrs())*10000/Integer.parseInt(form.getYdrs())))+"";
		cql = Float.parseFloat(cql)/100+"%";
		String sql = "update rcsw_cqtjb set ydrs=?,sdrs=?,qjrs=?,kkrs=?,cql=? where xydm||jcsj=?";
		String[] colval = new String[]{form.getYdrs(),form.getSdrs(),form.getQjrs(),form.getKkrs(),cql,pk};
		try {
			return dao.runUpdate(sql, colval);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//删除出勤统计信息
	public boolean deleteCqtjXx_db(String pks){
		DAO dao = DAO.getInstance();
		String sql = "delete from rcsw_cqtjb where instr(?,'!@!'||xydm||jcsj||'!@!') > 0";
		try {
			return dao.runUpdate(sql, new String[]{pks});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	//出勤统计表
	public List<HashMap<String,String>> cqtjPrint_db(String jcsj){
		DAO dao = DAO.getInstance();
		String sql = "select * from view_cqtjb where jcsj=?";
		String[] colVal = new String[]{"xymc","ydrs","sdrs","qjrs","kkrs","cql"};
		return dao.getList(sql, new String[]{jcsj}, colVal);
	}
}
