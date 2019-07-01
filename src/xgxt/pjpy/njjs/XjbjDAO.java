package xgxt.pjpy.njjs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;

public class XjbjDAO extends DAO {

	/**
	 * ���ݰ༶�����ѯ�೤��ѧ��
	 * @param bjdm
	 * @return
	 */
	public String getBzxhByBjdm(String bjdm){
		
		String sql = "select xh from sxjy_bjgbxxb where sysdate between to_date(kssj,'yyyymmdd') and to_date(jssj,'yyyymmdd') and bjdm=?";
			
		return getOneRs(sql, new String[]{bjdm}, "xh");
	}
	
	
	/**
	 * ���ݰ༶�����ѯ������
	 * @param bjdm
	 * @return
	 * @throws SQLException 
	 */
	public String[] getBzrByBjdm(String bjdm) throws SQLException{
		
		String sql = "select b.xm bzr from bzrbbb a,yhb b where b.yhm=a.zgh and a.bjdm=?";
		
		return getArray(sql, new String[]{bjdm}, "bzr");
	}
	
	
	/**
	 * ���ݰ༶�����ѯ�༶����
	 * @param bjdm
	 * @return
	 */
	public String getBjrsByBjdm(String bjdm){
		
		String sql = "select count(1) count from view_xsjbxx where bjdm=?";
		
		return getOneRs(sql, new String[]{bjdm}, "count");
	}
	
	
	
	/**
	 * ���������Ƚ��༶�����Ϣ
	 * @param model
	 * @param spgw
	 * @return
	 * @throws SQLException
	 */
	public boolean saveXjbjbbsh(XjbjForm model,String[] spgw) throws SQLException{
		
		String[] sqlArr = new String[spgw.length];

		for (int i = 0 ; i < spgw.length ; i++){
			StringBuilder sql = new StringBuilder();
			sql.append("insert into xg_pjpy_njjs_yxbjtshb(bjdm,sqsj,xtgwid) values ('")
			   .append(model.getSave_bjdm())
			   .append("','")
			   .append(model.getSave_sqsj())
			   .append("','")
			   .append(spgw[i])
			   .append("')");
			
			sqlArr[i] = sql.toString();
		}
		int[] result = runBatch(sqlArr);
		return checkBatch(result);
	}
	
	
	
	
	/**
	 * �Ƚ��༶���ѯ
	 * @param model
	 * @param colList
	 * @param gwmc
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXjbj(User user,XjbjForm model,String[] colList,String[] gwmc,String query) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		String[] queryList = new String[]{"nj","xydm","zydm","bjdm"};
		String[] queryLikeList = new String[]{};
		Map<String, Object> map = CommonQueryDAO.getQuerySQL(model,queryList,queryLikeList);
		
		sql.append(" select a.*,rownum r from(select a.bjdm||a.sqsj pkValue,a.*,")
		   .append(" case when ").append(gwmc[0]).append("='ͨ��' or ").append(gwmc[0])
		   .append(" ='��ͨ��' then 'disabled' end disabled,v.bjmc,v.nj,v.xydm,v.zydm,y.mc yxlxmc ")
	       .append(" from (select bjdm,yxlx,bjrs,bzxh,bzrzgh,zysj,bz,sqr,sqrlx,sqsj ");
		
		for (int i = 0 ; i < gwmc.length ; i++){
			sql.append(",max(")
			   .append(gwmc[i])
			   .append(") ")
			   .append(gwmc[i]);
		}
		
		sql.append(" from (select t.bjdm,t.yxlx,t.bjrs,t.bzxh,t.bzrzgh,t.zysj,t.bz,t.sqr,t.sqrlx,t.sqsj ");
		for (int i = 0 ; i < gwmc.length ; i++){
			sql.append(",case when b.mc='")
			   .append(gwmc[i])
			   .append("' then a.shzt end ")
			   .append(gwmc[i]);
		}
		sql.append(" from xg_pjpy_njjs_yxbjt t")
		   .append(" left join xg_pjpy_njjs_yxbjtshb a on t.bjdm=a.bjdm and t.sqsj=a.sqsj")
		   .append(" left join xg_xtwh_spgw b on a.xtgwid=b.id")
		   .append(" ) group by bjdm,yxlx,bjrs,bzxh,bzrzgh,zysj,bz,sqr,sqrlx,sqsj) a ")
		   .append(" left join view_njxyzybj v on a.bjdm=v.bjdm ")
		   .append(" left join xg_pjpy_yxbjlxb y on a.yxlx=y.dm ")
		   .append(" ) a where 1=1 ")
		   .append(map.get("sql"))
		   .append(query)
		   .append(CommonQueryDAO.getQuerySqlByUser(user, "a", "xydm", "bjdm"));
		//����ʱ�����
		HashMap<String,Object> sqsjSql = getSqsjSql(model);
		sql.append(sqsjSql.get("sql"));
		
		return CommonQueryDAO.commonPageQuery(model.getPages(),sql.toString(),
				StringUtils.joinStrArr((String[]) map.get("input"),(String[])sqsjSql.get("input")),colList);
	}
	
	
	
	/**
	 * ����ʱ������SQL
	 * @param model
	 * @return
	 */
	public HashMap<String,Object> getSqsjSql(XjbjForm model){
		StringBuilder sql = new StringBuilder();
		List<String> input = new ArrayList<String>();
		
		if (StringUtils.isNotNull(model.getSqkssj()) && StringUtils.isNull(model.getSqjssj())) {
			sql.append(" and to_date(sqsj,'yyyy-mm') >= to_date(?,'yyyy-mm')");
			input.add(model.getSqkssj());
		}
		
		if (StringUtils.isNotNull(model.getSqjssj()) && StringUtils.isNull(model.getSqkssj()) ) {
			sql.append(" and to_date(sqsj,'yyyy-mm') <= to_date(?,'yyyy-mm')");
			input.add(model.getSqjssj());
		}
		
		if (StringUtils.isNotNull(model.getSqkssj()) && StringUtils.isNotNull(model.getSqjssj())) {
			sql.append(" and to_date(sqsj,'yyyy-mm') between to_date(?,'yyyy-mm') and to_date(?,'yyyy-mm')+1");
			input.add(model.getSqkssj());
			input.add(model.getSqjssj());
		}
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("sql", sql.toString());
		map.put("input", input.toArray(new String[]{}));
		return map;
	}
	
	
	/**
	 * �����������༯��
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean plshYxbjt(XjbjForm model,String[] pkValues,String gnmc) throws Exception{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" update xg_pjpy_njjs_yxbjtshb set shzt=?,shr=?,shsj=?,shyj=? where bjdm||sqsj||xtgwid in (")
		   .append(" select a.bjdm||a.sqsj||a.xtgwid from xg_pjpy_njjs_yxbjtshb a where a.bjdm||a.sqsj in (");
		
		for (int i = 0 ; i < pkValues.length; i++){
			sql.append("'").append(pkValues[i]).append("'");
			if (i == pkValues.length-1){
				sql.append(")");
			} else {
				sql.append(",");
			}
		}
		
		sql.append(" and exists (select 1 from (select b.id,b.mc from xg_xtwh_spbz a,xg_xtwh_spgw b ")
		   .append(" where a.spgw=b.id and a.splc= (select lcid from xg_ty_shlcszb where gnmc=?) ")
		   .append(" order by xh) b where a.xtgwid=b.id and b.mc=?))");
		
		return runUpdate(sql.toString(), new String[]{model.getShjg(),model.getShr(),model.getShsj(),model.getShyj(),gnmc,model.getShgw()});
	}
	
	
	/**
	 * ������һ�����״̬Ϊ��������
	 * @return
	 * @throws Exception 
	 */
	public boolean resetNextShzt(XjbjForm model,String[] pkValues,String gnmc,String nextShgw) throws Exception{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" update xg_pjpy_njjs_yxbjtshb set shzt='������' where bjdm||sqsj||xtgwid in (")
		   .append(" select a.bjdm||a.sqsj||a.xtgwid from xg_pjpy_njjs_yxbjtshb a where a.bjdm||a.sqsj in (");
		
		for (int i = 0 ; i < pkValues.length; i++){
			sql.append("'").append(pkValues[i]).append("'");
			if (i == pkValues.length-1){
				sql.append(")");
			} else {
				sql.append(",");
			}
		}
		
		sql.append(" and exists (select 1 from (select b.id,b.mc from xg_xtwh_spbz a,xg_xtwh_spgw b ")
		   .append(" where a.spgw=b.id and a.splc= (select lcid from xg_ty_shlcszb where gnmc=?) ")
		   .append(" order by xh) b where a.xtgwid=b.id and b.mc=?)) and shzt='�˻�'");
		return runUpdate(sql.toString(), new String[]{gnmc,nextShgw});
	}
	
	
	
	
	/**
	 * ����༯����˲�ѯ
	 * @param model
	 * @param colList
	 * @param lcmc
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXjbjsh(User user,XjbjForm model,String[] colList,String[] gwmc,String beforeGwmc,String nextGwmc) throws Exception{
		StringBuilder sql = new StringBuilder();
		String[] queryArr = new String[]{"nj","xydm","zydm","bjdm"};
		String[] queryLikeArr = new String[]{};
		List<String> input = new ArrayList<String>();
		
		Map<String, Object> map = CommonQueryDAO.getQuerySQL(model,queryArr,queryLikeArr);
		
		sql.append(" select a.*,rownum r from(select a.bjdm||a.sqsj pkValue,a.*,");
		//�ж���һ��������λ������ˣ����˻أ���disabled
		if (StringUtils.isNotNull(nextGwmc)){
			String shgw = model.getShgw();
			sql.append(" case when ").append(shgw).append(" ='ͨ��' or ")
			   .append(shgw).append("='��ͨ��' then 'disabled' when ").append(shgw)
			   .append("='�˻�' then 'th' end isdis,");
			sql.append(" case when ").append(nextGwmc).append(" ='ͨ��' or ")
			   .append(nextGwmc).append("='��ͨ��' then 'disabled' else '' end disabled");
		} else {
			sql.append("'' isdis,");
			sql.append("'' disabled");
		}
		   
		sql.append(",v.bjmc,v.nj,v.xydm,v.zydm,y.mc yxlxmc from (")
		   .append(" select bjdm,yxlx,bjrs,bzxh,bzrzgh,zysj,bz,sqr,sqrlx,sqsj ");
		
		for (int i = 0 ; i < gwmc.length ; i++){
			sql.append(",max(")
			   .append(gwmc[i])
			   .append(") ")
			   .append(gwmc[i]);
		}
		
		sql.append(" from (select t.bjdm,t.yxlx,t.bjrs,t.bzxh,t.bzrzgh,t.zysj,t.bz,t.sqr,t.sqrlx,t.sqsj ");
		
		for (int i = 0 ; i < gwmc.length ; i++){
			sql.append(",case when b.mc='")
			   .append(gwmc[i])
			   .append("' then a.shzt end ")
			   .append(gwmc[i]);
		}
		sql.append(" from xg_pjpy_njjs_yxbjt t")
		   .append(" left join xg_pjpy_njjs_yxbjtshb a on t.bjdm=a.bjdm and t.sqsj=a.sqsj")
		   .append(" left join xg_xtwh_spgw b on a.xtgwid=b.id")
		   .append(" ) group by bjdm,yxlx,bjrs,bzxh,bzrzgh,zysj,bz,sqr,sqrlx,sqsj) a ")
		   .append(" left join view_njxyzybj v on a.bjdm=v.bjdm")
		   .append(" left join xg_pjpy_yxbjlxb y on a.yxlx=y.dm");
		
		if (StringUtils.isNotNull(beforeGwmc)){
			sql.append(" where a.")
			   .append(beforeGwmc)
			   .append(" ='ͨ��'");
		}
		   
		sql.append(" ) a where 1=1 ")
		   .append(map.get("sql"))
		   .append(CommonQueryDAO.getQuerySqlByUser(user, "a", "xydm", "bjdm"));
		
		//����ʱ�����
		HashMap<String,Object> sqsjSql = getSqsjSql(model);
		sql.append(sqsjSql.get("sql"));
		
		//���״̬����
		if (StringUtils.isNotNull(model.getShgw()) && StringUtils.isNotNull(model.getShzt())){
			sql.append(" and ").append(model.getShgw()).append("=?");
			input.add(model.getShzt());
		}
		
		return CommonQueryDAO.commonPageQuery(model.getPages(),sql.toString(),
				StringUtils.joinStrArr((String[]) map.get("input"),(String[])sqsjSql.get("input"),input.toArray(new String[]{})),colList);
	}
	
	
	
	/**
	 * �Ƚ��༶�����Ϣ
	 * @param pkValue
	 * @param gnmc
	 * @return
	 */
	public List<HashMap<String,String>> getXjbjShxx(String pkValue,String gnmc){
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.xh,a.spgw,b.mc,c.shzt,c.shsj,c.shyj,c.shr from ")
		   .append(" (select * from xg_xtwh_spbz where splc=(select lcid from xg_ty_shlcszb where gnmc=?)) a ")
		   .append(" right join ")
		   .append(" (select b.id,b.mc from xg_xtwh_spbz a,xg_xtwh_spgw b where a.spgw=b.id and ")
		   .append(" a.splc= (select lcid from xg_ty_shlcszb where gnmc=?) order by xh) b on a.spgw=b.id")
		   .append(" left join (select * from xg_pjpy_njjs_yxbjtshb where bjdm||sqsj=?) c on a.spgw=c.xtgwid")
		   .append(" order by a.xh");
		
		return getListNotOut(sql.toString(), new String[]{gnmc,gnmc,pkValue});
	}
	
	
	/**
	 * �Ƚ��༶ɾ��
	 * @param pkValues
	 * @return
	 * @throws Exception 
	 */
	public boolean delXjbj(String[] pkValues) throws Exception{
		StringBuilder delSqjl = new StringBuilder();//ɾ�������¼SQL
		String delShjl = "";//ɾ����˼�¼SQL
		
		delSqjl.append("delete from xg_pjpy_njjs_yxbjt where bjdm||sqsj in (");
		
		for (int i = 0 ; i < pkValues.length ; i++){
			delSqjl.append("'")
				   .append(pkValues[i])
				   .append("'");
			
			if (i != pkValues.length-1){
				delSqjl.append(",");
			} 
		}
		delSqjl.append(")");
		delShjl = delSqjl.toString().replace("xg_pjpy_njjs_yxbjt", "xg_pjpy_njjs_yxbjtshb");
		
		return runUpdate(delSqjl.toString(), new String[]{}) && runUpdate(delShjl, new String[]{}) ;
	}
	
	
	
	/**
	 * �Ƚ��༶��ӡ����
	 * @param gnmc
	 * @param sqsj
	 * @return
	 */
	public List<String[]> getXjbjPrintData(String gnmc,String sqsj){
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select rownum r,a.* from (select a.*,b.xymc,b.bjmc from (")
		   .append(" select bjdm,bzrzgh from xg_pjpy_njjs_yxbjt a where exists (")
		   .append(" select 1 from (select * from xg_pjpy_njjs_yxbjtshb where xtgwid=")
		   .append(" (select spgw from xg_xtwh_spbz where ")
		   .append(" splc=(select lcid from xg_ty_shlcszb where gnmc=?) and xh=")
		   .append(" (select max(xh) spxh from xg_xtwh_spbz where ")
		   .append(" splc=(select lcid from xg_ty_shlcszb where gnmc=?)))")
		   .append(" and shzt='ͨ��' and sqsj=?) b ")
		   .append(" where a.bjdm=b.bjdm and a.sqsj=b.sqsj) ) a")
		   .append(" left join view_njxyzybj b on a.bjdm=b.bjdm order by xymc) a");
		   //.append(" left join yhb c on a.bzrzgh=c.yhm")
		
		return rsToVator(sql.toString(), new String[]{gnmc,gnmc,sqsj}, new String[]{"xymc","r","bjmc","bzrzgh"});
	}
	
	
}
