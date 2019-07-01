/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package xgxt.gygl.sspy.pysq;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @className	： SspysqDao
 * @description	：  宿舍评优申请Dao层
 * @author 		：CP（1352）
 * @date		： 2018-4-27 下午03:14:37
 * @version 	V1.0
 */
public class SspysqDao extends SuperDAOImpl<SspysqForm>{
	@Override
	public List<HashMap<String, String>> getPageList(SspysqForm t)
			throws Exception {
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(SspysqForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,(select splc from xg_gfjy_gfjyjcszb) splcnew");
		sql.append("  from (select t1.*,t4.xm,t5.pyxmmc,t2.ldmc,t2.xydm,t2.bjdm,t2.zydm,t3.xqmc,");
		sql.append(" decode(t1.shzt, '0','未提交', '1', '通过','2','不通过', '3','已退回', '4','需重审', ");
		sql.append(" '5', '审核中', '','无需审核',t1.shzt) shztmc from xg_gygl_sspysqb t1 ");
		sql.append(" left join view_xg_gygl_new_cwxx t2 on t1.xh = t2.xh ");
		sql.append(" left join  xqdzb t3 on t1.xq = t3.xqdm");
		sql.append(" left join xsxxb t4 on t1.xh = t4.xh ");
		sql.append(" left join xg_gygl_new_sspyxmdmb t5 on t1.pyxmdm = t5.pyxmdm ");
		sql.append("  ) a where 1 = 1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_gygl_sspysqb");
		super.setKey("sqid");
	}

	public boolean isExist(SspysqForm model) {
		String sql = "select count(1) num from xg_gygl_sspysqb where xn = ? and xq =? and lddm=? and qsh=? and pyxmdm=? and shzt <> '2'" ;
		String num = dao.getOneRs(sql, new String[]{model.getXn(),model.getXq(),model.getLddm(),model.getQsh(),model.getPyxmdm()}, "num");
		return Integer.valueOf(num)>0;
	}

	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from xg_gygl_pyxmcsszb ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}

	public boolean isExistforUpdate(SspysqForm model) {
		String sql = "select count(1) num from xg_gygl_sspysqb where xn = ? and xq =? and lddm=? and qsh=? and pyxmdm=? and shzt <> '2' and sqid <> ?" ;
		String num = dao.getOneRs(sql, new String[]{model.getXn(),model.getXq(),model.getLddm(),model.getQsh(),model.getPyxmdm(),model.getSqid()}, "num");
		return Integer.valueOf(num)>0;
	}

	public List<HashMap<String, String>> getPyxmList() {
		StringBuilder sql=new StringBuilder();
		sql.append(" select pyxmdm,pyxmmc from xg_gygl_new_sspyxmdmb ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}

	public Map<String, String> getQsForPk(String ldqsxx) {
		StringBuilder sql = new StringBuilder();
		sql.append("select b.lddm||b.qsh pkValue,a.ldmc,a.ldxb,a.ldcs,a.qsch,a.sfhlc,b.* ")
		.append("from xg_gygl_new_ldxxb a,(select a.*,b.bmmc xymc,(case when to_number(a.ch)>-1 then a.ch else 'B'||abs(a.ch) end) chmc from xg_gygl_new_qsxxb a ")
		.append("left join zxbz_xxbmdm b on a.xydm=b.bmdm)b where a.lddm=b.lddm and b.lddm||b.qsh=?");
		return dao.getMapNotOut(sql.toString(), new String[]{ldqsxx});
	}
//根据楼栋寝室号获取宿舍成员信息
	public List<HashMap<String, String>> getCwForQs(String[] inputValue,
			String[] outputValue) {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.cwh," +
				"case when a.xh is null then ' ' else  a.xh end xh," +
				"case when b.xm is null then ' ' else  b.xm end xm," +
				"case when b.bjmc is null then ' ' else  b.bjmc end xsbjmc ")
			.append("from (select * from view_xg_gygl_new_cwxx a where lddm||qsh=?)a ")
			.append("left join view_xsbfxx b on a.xh=b.xh order by a.cwh ");
		return dao.getList(sql.toString(), inputValue, outputValue);
	}
//当前记录是否可被删除
	public boolean isCanDel(String sqid) {
		StringBuffer sb=new StringBuffer();
		sb.append("select shzt from xg_gygl_sspysqb where sqid=? ");
		String shzt=dao.getOneRs(sb.toString(), new String[] {sqid}, "shzt");
		return null==shzt||shzt.equals("0")?true:false;
	}
//获取楼栋+寝室号信息，用于删除时提醒
	public HashMap<String, String> getSspyxx(String sqid) {
		StringBuffer sb=new StringBuffer();
		sb.append("select b.ldmc,a.qsh from xg_gygl_sspysqb a");
		sb.append(" left join view_xg_gygl_new_cwxx b on a.lddm =b.lddm ");
		sb.append(" where a.sqid=?");
		return dao.getMapNotOut(sb.toString(),new String[]{sqid});
	}
//提交时，更新申请表
	public boolean updateSspy(SspysqForm model) throws Exception {
		String[] inputV = new String[3];
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_gygl_sspysqb ");
		sql.append(" set shzt = ? ,splc = ? where sqid = ?");
		inputV[0] = model.getShzt();
		inputV[1] = model.getSplc();
		inputV[2] = model.getSqid();
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}

	public HashMap<String, String> getInfoBySqid(String sqid) {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.pyxmmc from xg_gygl_sspysqb a left join xg_gygl_new_sspyxmdmb b on a.pyxmdm=b.pyxmdm");
		sql.append(" where sqid = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{sqid});
	}

}
