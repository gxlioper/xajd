package xgxt.rcgl.gzdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;


public class RcglGzdxDAO extends DAO {
	
	ArrayList<String> values = null;//查询条件值列表
	/**
	 *拼查询条件
	 */
	public String getQueryWhereSql_db(RcglGzdxForm model){
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
		if(!Base.isNull(model.getZydm())){
			sb.append("and zydm=? ");
			values.add(model.getZydm());
		}
		if(!Base.isNull(model.getBjdm())){
			sb.append("and bjdm=? ");
			values.add(model.getBjdm());
		}
		if(!Base.isNull(model.getXxsh())){
			sb.append("and xxsh=? ");
			values.add(model.getXxsh());
		}
		if(!Base.isNull(model.getYyrq())){
			sb.append("and yyrq = ? ");
			values.add(model.getYyrq());
		}
		if(!Base.isNull(model.getBm())){
			sb.append("and bm = ? ");
			values.add(model.getBm());
		}
		if(!Base.isNull(model.getCddm())){
			sb.append("and cddm = ? ");
			values.add(model.getCddm());
		}
		if(!Base.isNull(model.getYysb())){
			sb.append("and yysb = ?");
			values.add(model.getYysb());
		}
		if(!Base.isNull(model.getXh())){
			sb.append("and xh like ? ");
			values.add("%"+model.getXh()+"%");
		}
		if(!Base.isNull(model.getXm())){
			sb.append("and xm like ? ");
			values.add("%"+model.getXm()+"%");
		}
		if(!Base.isNull(model.getZbryxm())){
			sb.append("and guid in (select guid from  zyyysqb where instr(zbry,?)>0) ");
			values.add(model.getZbryxm());
		}
		if(!Base.isNull(model.getZbry())){
			if("wsz".equals(model.getZbry())){
				sb.append("and zbry is null ");
			}else{
				sb.append("and zbry is not null ");
			}
		}
		return sb.toString();
	}
	
	/**
	 *获得预约资源场地
	 */
	public List<HashMap<String, String>> getZyyycd_db(){
		String sql = "select dm,mc from cdyyb order by dm";
		return this.getListNotOut(sql, new String[]{});
	}
	
	/**
	 *获得预约设备
	 */
	public List<HashMap<String, String>> getZyyysb_db(){
		String sql = "select dm,mc from sbyyb order by dm";
		return this.getListNotOut(sql, new String[]{});
	}
	
	/**
	 *保存资源申请
	 */
	public boolean saveZyyysq_db(RcglGzdxForm model,String userType,String userName) {
		if(!saveCheck(model)){
			return false;
		}
		String sql = "insert into zyyysqb (bm,cddm,yyrq,yysjd,fzr,lxdh,hdnr,yysb,bz,week,xxsh,sqr,hdcd,sqsj) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String xxsh = "未审核";
		String yysjd = model.getHour()+":"+model.getMinute()+"-"+model.getHour2()+":"+model.getMinute2();
		DAO dao = DAO.getInstance();
		String xm = "";
		try {
			xm = dao.getRs("select * from yhb where yhm = ?", new String[]{userName}, "xm")[0];
		} catch (Exception e1) {
			// TODO 自动生成 catch 块
			e1.printStackTrace();
		}
		try {
			return runUpdate(sql, new String[] { model.getBm(),
					model.getCddm(), model.getYyrq(), yysjd,
					model.getFzr(), model.getLxdh(), model.getHdnr(),
					model.getYysb(), model.getBz(),model.getWeek(),xxsh,xm ,model.getHdcd(),model.getSqsj()});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 检查时间段是否冲突
	 * @param model
	 * @return
	 */
	public boolean saveCheck(RcglGzdxForm model){
		String sj1 = model.getHour()+"."+model.getMinute();
		String sj2 = model.getHour2()+"."+model.getMinute2();
		String yysjd = model.getHour()+":"+model.getMinute()+"-"+model.getHour2()+":"+model.getMinute2();
		StringBuffer sb = new StringBuffer();
		String flg = "";
		if(null != model.getCddm()){
			flg = " and cddm ='"+model.getCddm()+"'";
		}else if(null != model.getYysb()){
			flg = " and yysb ='"+model.getYysb()+"'";
		}
		sb.append("select * from zyyysqb  where ");
		sb.append("((substr(yysjd,0,2)||'.'||substr(yysjd,4,2)<=? and substr(yysjd,7,2)||'.'||substr(yysjd,10,2)>=? and yyrq=? " );
		sb.append(flg);
		sb.append(")  or ");
		sb.append("(substr(yysjd,0,2)||'.'||substr(yysjd,4,2)>=? and substr(yysjd,0,2)||'.'||substr(yysjd,4,2)<? and yyrq=? ");
		sb.append(flg);
		sb.append(")  or ");
		sb.append("(substr(yysjd,7,2)||'.'||substr(yysjd,10,2)>? and substr(yysjd,7,2)||'.'||substr(yysjd,10,2)<=? and yyrq=? ");
		sb.append(flg);
		sb.append(")  or ");
		sb.append("(yysjd=? and yyrq=? ");
		sb.append(flg);
		sb.append(")) and (xxsh='通过' or xxsh='未审核' )  ");
		DAO dao = DAO.getInstance();
		List r = dao.getList(sb.toString(), new String[]{sj1,sj2,model.getYyrq(),sj1,sj2,model.getYyrq(),sj1,sj2,model.getYyrq(),yysjd,model.getYyrq()}, new String[]{"guid"});
		if(r.size()>0){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 检查审核时时间段是否冲突
	 * @param model
	 * @return
	 */
	public boolean shCheck(String pkValue){
		DAO dao = DAO.getInstance();
		String sql="select * from zyyysqb where instr(?,'!@!'||guid||'!@!') > 0";
		HashMap<String,String> hashMap=dao.getMap(sql, new String[]{pkValue}, new String[]{"yysjd","cddm","yysb","yyrq"});
		String yyrq = hashMap.get("yyrq");
		String yysjd= hashMap.get("yysjd");
		String[] sjd = yysjd.split("-");
		StringBuffer sb = new StringBuffer();
		String flg = "";
		if(null != hashMap.get("cddm")){
			flg = " and cddm ='"+hashMap.get("cddm")+"'";
		}else if(null != hashMap.get("yysb")){
			flg = " and yysb ='"+hashMap.get("yysb")+"'";
		}
		sb.append("select * from zyyysqb  where ");
		sb.append("((substr(yysjd,0,2)||'.'||substr(yysjd,4,2)<=? and substr(yysjd,7,2)||'.'||substr(yysjd,10,2)>=? and yyrq=? " );
		sb.append(flg);
		sb.append(")  or ");
		sb.append("(substr(yysjd,0,2)||'.'||substr(yysjd,4,2)>=? and substr(yysjd,0,2)||'.'||substr(yysjd,4,2)<? and yyrq=? ");
		sb.append(flg);
		sb.append(")  or ");
		sb.append("(substr(yysjd,7,2)||'.'||substr(yysjd,10,2)>? and substr(yysjd,7,2)||'.'||substr(yysjd,10,2)<=? and yyrq=? ");
		sb.append(flg);
		sb.append(")  or ");
		sb.append("(yysjd=? and yyrq=? ");
		sb.append(flg);
		sb.append(")) and (xxsh='通过' )  ");
		List r = dao.getList(sb.toString(), new String[]{sjd[0],sjd[1],yyrq,sjd[0],sjd[1],yyrq,sjd[0],sjd[1],yyrq,yysjd,yyrq}, new String[]{"guid"});
		if(r.size()>0){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 2010.9.20 qlj
	 * 判断场地是否
	 * 可以被借出
	 */
	public boolean checkCdkj(RcglGzdxForm model){
		DAO dao=DAO.getInstance();
		String sql="  select count(*)sfkj from cdyyb b where b.dm=? and SFKJ='yes' ";
		HashMap<String,String>hashMap=dao.getMap(sql,new String[]{model.getCddm()}, new String[]{"sfkj"});
		if("0".equalsIgnoreCase(hashMap.get("sfkj"))){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 2010.9.20 qlj
	 * 判断场地是否
	 * 可以被借出
	 */
	public boolean checkSbkj(RcglGzdxForm model){
		DAO dao=DAO.getInstance();
		String sql="  select count(*)sfkj from sbyyb b where b.dm=? and SFKJ='yes' ";
		HashMap<String,String>hashMap=dao.getMap(sql,new String[]{model.getYysb()}, new String[]{"sfkj"});
		if("0".equalsIgnoreCase(hashMap.get("sfkj"))){
			return false;
		}else{
			return true;
		}
	}
	
	
	/**
	 *获得预约设备
	 */
	public List<HashMap<String, String>> getZyyyd_db(){
		String sql = "select dm,mc from sbyyb";
		return this.getListNotOut(sql, new String[]{});
	}
	
	/**
	 *预约审核
	 */
	public boolean zyyySh_db(String pkvals,String xxsh) {
		String sh = "";
		if("tg".equals(xxsh)){
			sh = "通过";
		}else{
			sh = "不通过";
		}
		String sql = "update zyyysqb set xxsh=? where instr(?,'!@!'||guid||'!@!') > 0";
		 try {
			return runUpdate(sql, new String[]{sh,pkvals});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 *广州大学
	 *预约审核
	 */
	public boolean zyyySh_gzdx(String pkvals,String xxsh,String shpf) {
		String sh = "";
		if("tg".equals(xxsh)){
			sh = "通过";
		}else{
			sh = "不通过";
		}
		String sql = "update zyyysqb set xxsh=?,shpf=? where instr(?,'!@!'||guid||'!@!') > 0";
		 try {
			return runUpdate(sql, new String[]{sh,shpf,pkvals});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 *查询预约审核数据
	 */
	public List<HashMap<String, String>> zyyyShQuery_db(RcglGzdxForm model) {
		String sql = "select a.*,(case nvl(a.xxsh,'未审核') when '通过' then '#99CCFF' when '未审核' then '#FFFFFF' else '#FF9999' end) bgcolor,(select bmmc from zxbz_xxbmdm where bmdm=a.bm)bmmc," +
				"(select mc from cdyyb where dm=a.cddm)cdmc,rownum r from zyyysqb a where "+getQueryWhereSql_db(model)+"order by xxsh,yyrq desc";
		List<HashMap<String, String>> templist = getList(sql, values.toArray(new String[]{}), new String[]{"guid"});
		model.getPages().setMaxRecord(templist.size());
		sql = "select * from ("+sql+") where r<="+(model.getPages().getStart() + model.getPages().getPageSize())+" and r>"+model.getPages().getStart();
		System.out.println(sql);
		return getListNotOut(sql, values.toArray(new String[]{}));
	}
	
	/**
	 *获得单个预约信息
	 */
	public HashMap<String, String> getOneYyxx_db(String pkval) {
		String sql = "select * from zyyysqb where guid=?";
		HashMap<String, String> map = getMapNotOut(sql, new String[]{pkval});
		String yysjd = map.get("yysjd");
		if(!Base.isNull(yysjd)){
			map.put("hour", yysjd.substring(0,2));
			map.put("minute", yysjd.substring(3,5));
			map.put("hour2", yysjd.substring(6,8));
			map.put("minute2", yysjd.substring(9,11));
		}
		return map;
	}
	
	/**
	 *修改单个预约信息
	 */
	public boolean updateOneYyxx_db(String pkval,RcglGzdxForm model) {
		
		String sql = "update zyyysqb set bm=?,cddm=?,yyrq=?,yysjd=?,fzr=?,lxdh=?,hdnr=?,yysb=?,bz=?,week=? where guid=?";
		String yysjd = model.getHour()+":"+model.getMinute()+"-"+model.getHour2()+":"+model.getMinute2();
		String[] colVal = new String[] { model.getBm(),
				model.getCddm(), model.getYyrq(), yysjd,
				model.getFzr(), model.getLxdh(), model.getHdnr(),
				model.getYysb(), model.getBz(),model.getWeek(),pkval };
		try {
			return this.runUpdate(sql, colVal);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 *删除预约信息
	 */
	public boolean deleteYyxx_db(String pkvals) {
		String sql = "delete from zyyysqb  where instr(?,'!@!'||guid||'!@!') > 0";
		try {
			return this.runUpdate(sql, new String[]{pkvals});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 *检查是否为学生管理员
	 */
	public String isXsgly_db(String xh) {
		String sql = "select xh,sfqy from xsglyb where xh=?";
		String[] xsxh = getOneRs(sql, new String[]{xh}, new String[]{"xh","sfqy"});
		if(xsxh != null){
			if("启用".equals(xsxh[1])){
				return "yes";
			}else{
				return "wqy";
			}
		}else{
			return "no";
		}
	}
	
	/**
	 *查询值班人员
	 */
	public List<HashMap<String, String>> queryZbry_db(RcglGzdxForm model) {
		String sql = "select * from (select a.xh,xm,xb,xymc,zymc,bjmc,bjdm,zydm,xydm,a.bz from zbryk a,view_xsjbxx b where " +
				"a.xh=b.xh) where "+getQueryWhereSql_db(model);
		return this.getListNotOut(sql, values.toArray(new String[]{}));
	}
	
	/**
	 *删除值班人员
	 */
	public boolean deleteZbry_db(String pkvals) {
		String sql = "delete from zbryk  where instr(?,'!@!'||xh||'!@!') > 0";
		try {
			return this.runUpdate(sql, new String[]{pkvals});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 *获得某值班人员信息
	 */
	public HashMap<String,String> getOneZbryxx_db(String xh) {
		String sql = "select a.xh,xm,xb,xymc,zymc,bjmc,a.bz from zbryk a,view_xsjbxx b where a.xh=b.xh and a.xh=?";
		return this.getMapNotOut(sql, new String[]{xh});
	}
	
	/**
	 *修改某值班人员信息
	 */
	public boolean updateOneZbryxx_db(RcglGzdxForm model) {
		String sql = "update zbryk set bz=? where xh=?";
		try {
			return runUpdate(sql, new String[]{model.getBz(),model.getXh()});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 *增加某值班人员信息
	 */
	public boolean addOneZbryxx_db(RcglGzdxForm model) {
		String sql = "insert into zbryk (xh,bz) values(?,?)";
		try {
			return runUpdate(sql, new String[]{model.getXh(),model.getBz()});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 *获得某学生信息
	 */
	public HashMap<String,String> getOneXsxx_db(String xh) {
		String sql = "select * from view_xsjbxx where xh=?";
		return this.getMapNotOut(sql, new String[]{xh});
	}
	
	/**
	 *设置值班人员
	 */
	public boolean zbrysz_db(String pkvals,String guid) {
		String sql = "select xm,lxdh from view_xsjbxx where instr(?,'!@!'||xh||'!@!')>0";
		List<HashMap<String,String>> list = getListNotOut(sql, new String[]{pkvals});
		HashMap<String,String> map = null;
		String xms = "";
		String lxdh = "";
		if(list.size() > 0){
			for(int i=0;i<list.size();i++){
				map = list.get(i);
				xms += map.get("xm")+",";
			}
			lxdh = list.get(0).get("lxdh");
		}
		if(!Base.isNull(xms)){
			xms = xms.substring(0, xms.length()-1);
		}
		sql = "update zyyysqb set zbry=?,zbdh=? where guid=?";	
		try {
			return runUpdate(sql, new String[]{xms,lxdh,guid});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 *查询值班人员
	 */
	public List<HashMap<String, String>> queryZbrysz_db(RcglGzdxForm model) {
		String sql = "select a.*,(select bmmc from zxbz_xxbmdm where bmdm=a.bm)bmmc,"
				+ "(select mc from cdyyb where dm=a.cddm)cdmc,rownum r from zyyysqb a where xxsh='通过' and "
				+ getQueryWhereSql_db(model) + "order by yyrq desc";
		List<HashMap<String, String>> templist = getList(sql, values
				.toArray(new String[] {}), new String[] { "guid" });
		model.getPages().setMaxRecord(templist.size());
		sql = "select * from (" + sql + ") where r<="
				+ (model.getPages().getStart() + model.getPages().getPageSize())
				+ " and r>" + model.getPages().getStart();
		return getListNotOut(sql, values.toArray(new String[] {}));
	}
	
	/**
	 *删除学生管理员
	 */
	public boolean deleteXsgly_db(String pkvals) {
		String sql = "delete from xsglyb  where instr(?,'!@!'||xh||'!@!') > 0";
		try {
			return this.runUpdate(sql, new String[]{pkvals});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 *查询学生管理员
	 */
	public List<HashMap<String, String>> queryXsgly_db(RcglGzdxForm model) {
		String sql = "select * from (select a.xh,xm,xb,xymc,zymc,bjmc,bjdm,zydm,xydm,a.sfqy,a.bz from xsglyb a,view_xsjbxx b where " +
				"a.xh=b.xh) where "+getQueryWhereSql_db(model);
		return this.getListNotOut(sql, values.toArray(new String[]{}));
	}
	
	/**
	 *增加学生管理员信息
	 */
	public boolean addOneXsglyxx_db(RcglGzdxForm model) {
		String sql = "insert into xsglyb (xh,sfqy,bz) values(?,?,?)";
		try {
			return this.runUpdate(sql, new String[]{model.getXh(),model.getSfqy(),model.getBz()});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 *修改学生管理员信息
	 */
	public boolean updateOneXsglyxx_db(RcglGzdxForm model) {
		String sql = "update xsglyb set sfqy=?,bz=? where xh=?";
		try {
			return runUpdate(sql, new String[]{model.getSfqy(),model.getBz(),model.getXh()});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 *获得学生管理员信息
	 */
	public HashMap<String,String> getOneXsglyxx_db(String xh) {
		String sql = "select a.xh,xm,xb,xymc,zymc,bjmc,a.bz,a.sfqy from xsglyb a,view_xsjbxx b where a.xh=b.xh and a.xh=?";
		return this.getMapNotOut(sql, new String[]{xh});
	}
	
	/**
	 * 获得户口须知
	 * */
	public String getTzgg_db(String lb) {
		DAO dao = DAO.getInstance();
		String sql = "select nr from xzb where dm=?";
		return dao.getOneRs(sql, new String[]{lb}, "nr");
	}
}
