/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.ttgl.stgl.stgljg;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class StglDao extends SuperDAOImpl<StglForm>{

	@Override
	public List<HashMap<String, String>> getPageList(StglForm t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(StglForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select a.jgid,a.stlx,a.stqc,a.stjc,a.styx,a.ywzddw,a.stzdls,a.stjs,a.gzh,decode(a.stzt,0,'预备期',1,'正式',2,'已注销') stzt,decode(a.sjly,0,'结果数据',1,'流程数据') sjly,b.xm stzdlsxm ,c.bmmc,(select count(1) from XG_TTGL_STCYB where jgid=a.jgid and shzt='1') strs from xg_ttgl_stgljgb a left join fdyxxb b on a.stzdls=b.zgh "); 
		sql.append(" left join zxbz_xxbmdm c on a.ywzddw = c.bmdm  ) ");
		sql.append(" where 1 = 1 "); 
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		super.setKey("jgid");
		super.setTableName("xg_ttgl_stgljgb");
	}
	public boolean isExist(StglForm model) {
		String sql = "select count(1) num from xg_ttgl_stgljgb where stlx=? and stqc = ? " ;
		String num = dao.getOneRs(sql, new String[]{model.getStlx(),model.getStqc()}, "num");
		return Integer.valueOf(num)>0;
	}

	public boolean isExistforUpdate(StglForm model) {
		String sql = "select count(1) num from xg_ttgl_stgljgb where  stlx=? and stqc = ? and jgid <> ?" ;
		String num = dao.getOneRs(sql, new String[]{model.getStlx(),model.getStqc(),model.getJgid()}, "num");
		return Integer.valueOf(num)>0;
	}

	public List<HashMap<String, String>> getbmList() {
		return dao.getYjbmList();
	}

	public List<HashMap<String, String>> getStuCx(StglForm t, User user) throws Exception {
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		sql.append(" select xh,xm,xb,xydm,a.bjdm,xymc,bjmc,nj,zymc,zydm,sjhm,c.symc from view_xsbfxx a left join xg_xtwh_sybjglb b on a.bjdm=b.bjdm ");
		sql.append(" left join xg_xtwh_sydmb c on b.sydm=c.sydm "); 
		sql.append(" where 1=1");
		List<String> paraList = new ArrayList<String>();
		sql.append(searchTj);
		sql.append(" order by bjdm asc,xydm asc,nj asc");
		paraList.addAll(Arrays.asList(inputV));
		return getPageList(t, sql.toString(), paraList.toArray(new String[]{}));
	}

	public boolean delFzrb(String jgid) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_ttgl_stglfzrb where jgid = ?");
		return dao.runUpdate(sql.toString(),new String[]{jgid});
	}

	public boolean saveFzrb(List<String[]> paraList) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_ttgl_stglfzrb(xh,jgid,fzrfz) values(?,?,?)");
		return dao.runBatchBoolean(sql.toString(), paraList);
	}

	public boolean saveTzsb(List<String[]> tzsList) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into XG_TTGL_STGLTZSB(xh,jgid) values(?,?)");
		return dao.runBatchBoolean(sql.toString(), tzsList);
	}

	public List<HashMap<String, String>> getFzrxx(StglForm myForm) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh,b.xm,b.xb,b.bjmc,b.sjhm,b.xymc,b.zymc,d.symc,a.fzrfz from  xg_ttgl_stglfzrb a");
		sql.append(" left join view_xsbfxx b on a.xh = b.xh");
		sql.append(" left join xg_xtwh_sybjglb c on b.bjdm=c.bjdm");
		sql.append(" left join xg_xtwh_sydmb d on c.sydm=d.sydm where ");
		if ("1".equals(myForm.getSjly())) {
			sql.append(" a.sqid = ?");
		}else {
			sql.append(" a.jgid = ?");
		}
		
		return dao.getListNotOut(sql.toString(),new String[]{myForm.getJgid()});
	}

    public List<HashMap<String, String>> getTzsxx(StglForm myForm) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select a.xh,b.xm,b.xb,b.bjmc,b.sjhm,b.xymc,b.zymc,d.symc from  XG_TTGL_STGLTZSB a");
        sql.append(" left join view_xsbfxx b on a.xh = b.xh");
        sql.append(" left join xg_xtwh_sybjglb c on b.bjdm=c.bjdm");
        sql.append(" left join xg_xtwh_sydmb d on c.sydm=d.sydm where ");
        if ("1".equals(myForm.getSjly())) {
            sql.append(" a.sqid = ?");
        }else {
            sql.append(" a.jgid = ?");
        }

        return dao.getListNotOut(sql.toString(),new String[]{myForm.getJgid()});
    }

	public String getfdyxm(String stzdls) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select xm from fdyxxb where zgh=?");
		return dao.getOneRs(sql.toString(), new String[] {stzdls}, "xm");
	}

	public boolean delFzrbInfo(String[] ids) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_ttgl_stglfzrb where jgid in(");
		for (int i = 0; i < ids.length; i++) {
			sql.append("?");
			if(i != ids.length-1){
				sql.append(",");
			}
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(),ids );
	}

    public boolean delTzsInfo(String[] ids) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" delete from XG_TTGL_STGLTZSB where jgid in(");
        for (int i = 0; i < ids.length; i++) {
            sql.append("?");
            if(i != ids.length-1){
                sql.append(",");
            }
        }
        sql.append(")");
        return dao.runUpdate(sql.toString(),ids );
    }

	public HashMap<String, String> getStxxInfo(StglForm t) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.xm zdlsxm,t3.bmmc,t4.mc as zzlbmc,t5.mc as ndzzztmc");
		sql.append(" from xg_ttgl_stgljgb t1 left join fdyxxb t2 on t1.stzdls = t2.zgh");
		sql.append(" left join zxbz_xxbmdm t3 on t1.ywzddw = t3.bmdm  ");
        sql.append(" left join XG_TTGL_ZZLBDMB t4 on t1.zzlb = t4.dm");
        sql.append(" left join XG_TTGL_NDZZZTDMB t5 on t1.ndzzzt = t5.dm");
		sql.append(" where t1.jgid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getJgid()});
	}

	public boolean deleteExist(StglForm model) throws Exception {
		StringBuilder sql = new StringBuilder(
		" delete from xg_ttgl_stgljgb where stlx=? and stqc = ? ");
		return dao.runUpdate(sql.toString(), new String[] { model.getStlx(),model.getStqc()});
	}

	public boolean isCanDel(String jgid) {
		StringBuffer sb=new StringBuffer();
		sb.append("select sjly from xg_ttgl_stgljgb where jgid=? ");
		Map<String,String> map= dao.getMapNotOut(sb.toString(),new String[]{jgid});
		String sjly=map.get("sjly");
		//如果未提交才可以提交
		return sjly.equals("0")?true:false;
	}

	public HashMap<String, String> getStxxjg(String jgid) {
		StringBuffer sb=new StringBuffer();
		sb.append("select a.stqc  from xg_ttgl_stgljgb a");
		sb.append(" where  a.jgid=?");
		return dao.getMapNotOut(sb.toString(),new String[]{jgid});
	}

	public boolean saveStcyb(List<String[]> paraList) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_ttgl_stcyb(xh,jgid,shzt,tnzw,sjly) values(?,?,?,?,?)");
		return dao.runBatchBoolean(sql.toString(), paraList);
	}

	public boolean delStcyInfo(String[] ids) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_ttgl_stcyb where jgid in(");
		for (int i = 0; i < ids.length; i++) {
			sql.append("?");
			if(i != ids.length-1){
				sql.append(",");
			}
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(),ids );
	}

	public boolean delStcyb(String jgid) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_ttgl_stcyb where jgid = ?");
		return dao.runUpdate(sql.toString(),new String[]{jgid});
	}

	public boolean stzx(StglForm model) throws Exception {
		String[] inputV = new String[1];
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_ttgl_stgljgb set stzt = '2' where jgid = ?");
		inputV[0] = model.getJgid();
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}

	public List<HashMap<String, String>> getcyList(StglForm myForm) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh,a.tnzw,b.xm,b.xb,b.bjmc,b.sjhm,b.xymc,b.zymc,d.symc from  xg_ttgl_stcyb a");
		sql.append(" left join view_xsbfxx b on a.xh = b.xh");
		sql.append(" left join xg_xtwh_sybjglb c on b.bjdm=c.bjdm");
		sql.append(" left join xg_xtwh_sydmb d on c.sydm=d.sydm where ");
		sql.append(" a.jgid = ?");
		return dao.getListNotOut(sql.toString(),new String[]{myForm.getJgid()});
	}

    /**
     * 获取学生组织经费来源集合
     */
	public List<HashMap<String, String>> getXszzjflyList(){
        StringBuilder sql = new StringBuilder();
        sql.append("select a.dm,a.mc from XG_TTGL_ZZJJLYDMB a");
	    return dao.getListNotOut(sql.toString(),new String[]{});
    }

    /**
     * 获取年度组织状态
     */
    public List<HashMap<String, String>> getNdzzztList(){
        StringBuilder sql = new StringBuilder();
        sql.append("select a.dm,a.mc from XG_TTGL_NDZZZTDMB a");
        return dao.getListNotOut(sql.toString(),new String[]{});
    }

    /**
     * 获取组织类别
     */
    public List<HashMap<String, String>> getZzlbList(){
        StringBuilder sql = new StringBuilder();
        sql.append("select a.dm,a.mc from XG_TTGL_ZZLBDMB a");
        return dao.getListNotOut(sql.toString(),new String[]{});
    }
}
