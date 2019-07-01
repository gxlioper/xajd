package xgxt.pjpy.comm.pjpy.pjlc.jgcx;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.gygl.gywh.DelDetectModel;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

public class PjpyJgcxDAO extends DAO{

	
	/**
	 * 评奖结果查询，审核状态只显示当前最高级别的审核状态
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	protected List<String[]> findPjjg(PjpyJgcxForm model,String[] colList,String query) throws Exception{
		
		Pages pages = model.getPages();
		StringBuilder sql = new StringBuilder();
		String[] queryArr = new String[]{"pjxn","pjxq","pjnd","xmlx","xmxz","xmfw","xmdm","nj","xydm","zydm","bjdm","shzt"};
		String[] queryLikeArr = new String[]{"xm","xh"};
		
		Map<String,Object> map = CommonQueryDAO.getQuerySQL(model, queryArr, queryLikeArr);
		List<String> input = new ArrayList<String>();
		
		sql.append("select rownum r,a.xmdm||a.pjxn||a.pjxq||a.pjnd||a.xh pkValue,a.pjxn,nvl(t.xqmc,'无') pjxqmc,")
		   .append("a.pjnd,a.xh,a.xm,a.nj,a.xymc,a.zymc,a.bjmc,a.xmmc,a.xmje,a.sqsj,a.shzt,x.yhkh,x.yhmc ")
		   .append(" from xg_view_pjpy_jgcx a left join xqdzb t on t.xqdm=a.pjxq   ")
		   .append(" left join (select xh,yhkh, ")
		   .append(" (select yhmc from dmk_yh b where a.yhdm=b.yhdm)yhmc from xsxxb a) x on a.xh=x.xh where 1=1 ")
		   .append(map.get("sql"));
		
		setSqjsjSQL(model, sql, input);
		sql.append(query);
		//查询条件继续追加SQL
		return CommonQueryDAO.commonQuery(pages, sql.toString(), StringUtils.joinStrArr((String[])map.get("input"),input.toArray(new String[]{})), colList);
	}


	/**
	 * 申请时间Sql
	 * @param model
	 * @param sql
	 * @param input
	 */
	private void setSqjsjSQL(PjpyJgcxForm model, StringBuilder sql, List<String> input) {
		if (StringUtils.isNotNull(model.getSqkssj()) && StringUtils.isNull(model.getSqjssj())){
			sql.append(" and to_date(replace(replace(replace(sqsj,'年',''),'月',''),'日',''),'yyyymmdd') >= to_date(?,'yyyy-mm-dd')");
			input.add(model.getSqkssj());
		}
		
		if (StringUtils.isNotNull(model.getSqjssj()) && StringUtils.isNull(model.getSqkssj())){
			sql.append(" and to_date(replace(replace(replace(sqsj,'年',''),'月',''),'日',''),'yyyymmdd') <= to_date(?,'yyyy-mm-dd')");
			input.add(model.getSqjssj());
		}
		
		if (StringUtils.isNotNull(model.getSqkssj()) && StringUtils.isNotNull(model.getSqjssj())){
			sql.append(" and to_date(replace(replace(replace(sqsj,'年',''),'月',''),'日',''),'yyyymmdd') between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd') ");
			input.add(model.getSqkssj());
			input.add(model.getSqjssj());
		}
	}

	
	/**
	 * 评奖结果查询，根据项目代码动态出各级审核状态
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	protected List<String[]> findPjjgByXmdm(PjpyJgcxForm model,String[] colList,String[] lcmc,String query) throws Exception{
		
		Pages pages = model.getPages();
		String[] queryArr = new String[]{"pjxn","pjxq","pjnd","xmlx","xmxz","xmfw","xmdm","nj","xydm","zydm","bjdm"};
		String[] queryLikeArr = new String[]{"xm","xh"};
		
		boolean flg = null != lcmc && lcmc.length > 0;
		Map<String,Object> map = CommonQueryDAO.getQuerySQL(model, queryArr, queryLikeArr);
		List<String> input = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,x.yhkh,x.yhmc,rownum r from (select t.xmdm||t.pjxn||t.pjxq||t.pjnd||t.xh pkValue,t.xmdm,t.pjxn,t.pjxq,t.pjnd,v.xqmc pjxqmc,")
		   .append("t.xm,t.nj,t.xydm,t.zydm,t.bjdm,t.xymc,t.zymc,t.bjmc,t.xmmc,t.xmlx,t.xmxz,t.xmfw,t.xh,t.sqsj,t.sqly,t.tjr,t.xmje");
		
		if (flg){
			sql.append(",");
			for (int i = 0 ; i < lcmc.length ; i++){
				sql.append("nvl(max(")
				   .append(lcmc[i])
				   .append("),'未审核')")
				   .append(lcmc[i]);
				
				if (i != lcmc.length-1){
					sql.append(",");
				}
			}
		}
		
		sql.append(" from (select t.*,e.xmje,e.xmmc,e.xmlx,e.xmxz,e.xmfw,d.xm,d.nj,d.pjxydm xydm,")
		   .append("d.pjxymc xymc,d.pjzydm zydm,d.pjzymc zymc,d.pjbjdm bjdm,d.pjbjmc bjmc");
		if (flg){
			sql.append(",");
			for (int i = 0 ; i < lcmc.length ; i++){
				sql.append("case when c.mc='")
				   .append(lcmc[i])
				   .append("' then a.shzt end ")
				   .append(lcmc[i]);
				
				if (i != lcmc.length-1){
					sql.append(",");
				}
			}
		}
		
		sql.append(" from xg_pjpy_pjxmsqb t ")
		   .append(" left join xg_pjpy_pjxmshb a on t.xmdm=a.xmdm and t.pjxn=a.pjxn and t.pjxq=a.pjxq and t.pjnd=a.pjnd and t.xh=a.xh")
		   .append(" left join xg_xtwh_spbz b on a.xtgwid = b.spgw and a.xtgwid = b.spgw")
		   .append(" left join xg_xtwh_spgw c on b.spgw=c.id")
		   .append(" left join view_xg_pjpy_ryqd d on t.xh=d.xh ")//2011.11.9 qlj 无需审核的记录取不到学院专业班级
		   .append(" left join ( select t.xmmc,t.xmdm,t.xmlx,t.xmxz,t.xmfw,t.xmje,")
		   .append(" case when t.sqzq='xn' then t.pjxn||'无无' when t.sqzq='xq' then t.pjxn||t.pjxq||'无' else '无无'||t.pjnd end zq ")
		   .append(" from xg_pjpy_pjxmwhb t")
		   .append("  )e on a.xmdm=e.xmdm and a.pjxn||a.pjxq||a.pjnd=e.zq")
		   .append(" ) t left join xqdzb v on v.xqdm=t.pjxq")
		   .append(" group by xmdm,pjxn,pjxq,pjnd,xh,xm,sqsj,sqly,tjr,nj,xmje,")
		   .append("xydm,zydm,bjdm,xymc,zymc,bjmc,xmmc,xmlx,xmxz,xmfw,xqmc) a ")
		   .append(" left join (select xh,yhkh,(select yhmc from dmk_yh b where a.yhdm=b.yhdm) ")
		   .append(" yhmc from xsxxb a) x on a.xh=x.xh where 1=1  ")
		   .append(map.get("sql"));
		
		setSqjsjSQL(model, sql, input);//申请时间过滤
		sql.append(query);
		//各级审核岗位过滤条件
		String[] lcmcValue = model.getLcmcValue();
		
		if (null != lcmc && null != lcmcValue){
			for (int i = 0 ; i < lcmc.length ; i++){
				
				if (StringUtils.isNotNull(lcmcValue[i])){
					sql.append(" and ")
					   .append(lcmc[i])
					   .append("=?");
					input.add(lcmcValue[i]);
				}
			}
		}
		
		return CommonQueryDAO.commonQuery(pages, sql.toString(), StringUtils.joinStrArr((String[])map.get("input"),input.toArray(new String[]{})), colList);
	}


	/**
	 * 根据项目代码查询评奖审核各级流程名称
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	public String[] getLcmcByXmdm(PjpyJgcxForm model) throws SQLException {
		String lcmcSql = "select b.mc from xg_xtwh_spbz a,xg_xtwh_spgw b where a.spgw=b.id and a.splc= (select lcid from xg_pjpy_pjxmwhb where xmdm=? and rownum=1) order by xh";
		String[] lcmc = getArray(lcmcSql, new String[]{model.getXmdm()}, "mc");
		return lcmc;
	}
	
	
	/**
	 * 删除评奖申请、审核记录
	 * @param pkValues
	 * @return
	 * @throws Exception
	 */
	public boolean delPjjl(String[] pkValues) throws Exception{
		
		StringBuilder delSqjl = new StringBuilder();//删除申请记录SQL
		String delShjl = "";//删除审核记录SQL
		
		delSqjl.append("delete from xg_pjpy_pjxmsqb where xmdm||pjxn||pjxq||pjnd||xh in (");
		
		for (int i = 0 ; i < pkValues.length ; i++){
			delSqjl.append("'")
				   .append(pkValues[i])
				   .append("'");
			
			if (i != pkValues.length-1){
				delSqjl.append(",");
			} 
		}
		delSqjl.append(")");
		delShjl = delSqjl.toString().replace("xg_pjpy_pjxmsqb", "xg_pjpy_pjxmshb");
		
		return runUpdate(delSqjl.toString(), new String[]{}) && runUpdate(delShjl, new String[]{}) ;
	}
	
	/**
	 * 删除评奖申请、审核记录
	 * @param pkValues
	 * @return
	 * @throws Exception
	 */
	public String checkDel(DelDetectModel model){
		
		CommService service = new CommService();
		String[] pkValue = model.getPkValue();
		StringBuilder sql = new StringBuilder();// SQL
		sql.append("select xh,xm,mc from xg_view_pjpy_xmsh ");
		sql.append("where 1 = 1 ");

		if (pkValue != null && pkValue.length > 0) {
			sql.append("and ( ");
			for (int i = 0; i < pkValue.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" xmdm||pjxn||pjxq||pjnd||xh = '"+service.unicode2Gbk(pkValue[i])+"' ");
			}
			sql.append(")");
		}
		sql.append(" and shzt <>'未审核' ");
		sql.append(" and rownum = 1 ");

		DAO dao = DAO.getInstance();
		HashMap<String, String> map = dao.getMap(sql.toString(), new String[]{},
				new String[] { "xh", "xm", "mc" });

		String message = "";

		if (!Base.isNull(map.get("xh"))) {
			message = map.get("xm") + "(" + map.get("xh") + ")已经被"
					+ map.get("mc") + "审核过了，不可删除，请重新确认。";
		}

		return message;
	}	
}
