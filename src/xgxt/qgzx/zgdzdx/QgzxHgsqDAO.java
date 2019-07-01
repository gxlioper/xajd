package xgxt.qgzx.zgdzdx;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.qgzx.dao.QgzxDao;
import xgxt.utils.String.StringUtils;

public class QgzxHgsqDAO extends DAO {
	DAO dao = DAO.getInstance();
	
	/**获取岗位允许申请时间*/
	public String[] getStuTime(){
		String sql = "select xn,nd,xq,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc from gwsqsjb a where rownum=1";
		return dao.getOneRs(sql, new String[] {}, new String[] { "xn", "nd", "xq","xqmc" });
	}
	
	/**获取岗位名称列表*/
	public List<HashMap<String, String>> getGwNameList(String isLsgw){
		
		String query = "";
		
		if ("yes".equals(isLsgw)) {
			query = " and gwxz<>(select gwxzdm from gwxzdmb where gwxzmc='临时岗位')";
		}
		
		String sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from view_gwxx a where SHJG='通过' and gzjsrq>=to_char(sysdate,'yyyymmdd')"+query;
		return dao.getList(sql, new String[] {}, new String[] { "gwdm","gwdmgwsbsj" });
	}
	
	/**获取岗位名称*/
	public String[] getGwName(String tmp){
		String sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from gwxxb where gwdm||gwsbsj =?";
		return dao.getOneRs(sql, new String[] { tmp }, new String[] { "gwdm", "gwdmgwsbsj" });
	}
	
	/**获取学生通过学院推荐记录*/
	public String getXytjcount(String xh,String xn,String nd,String xq){
		String sql = "select count(*) num from qgzxsqb where xh=? and xn=? and nd=? and xq=? and xxsh='通过'";
		return dao.getOneRs(sql, new String[] { xh, xn, nd, xq }, "num");
	}
	
	/**获取学生岗位审核记录*/
	public String getGwshcount(String xh,String xn,String nd,String xq){
		String sql = "select count(*)num from xsgwxxb where xh=? and xn=? and nd=? and xq=? and xxyj='通过'";
		return dao.getOneRs(sql, new String[] {xh,xn,nd,xq}, "num");
	}
	
	/**获取学生在岗岗位*/
	public String getGwdm(String xh,String xn,String nd,String xq){
		String sql = "select gwdm||'-'||gwsbsj gwdmgwsbsj from xsgwxxb where xh=? and xn=? and nd=? and xq=? and xxyj='通过'";
		return dao.getOneRs(sql, new String[] {xh,xn,nd,xq}, "gwdmgwsbsj");
	}
	
	/**删除学生勤工助学表时间
	 * @throws Exception */
	public void deleteQgzxTime(String xh) throws Exception{
		String sql = "delete from xsqgzxsjb where xh=?";
		dao.runUpdate(sql, new String[]{xh});
	}
	
	/**保存学生勤工助学时间*/
	public void saveQgzxTime(String xh,String tmp1,String tmp2,String tmp3) throws Exception{
		String sql = "insert into xsqgzxsjb(xh,xq,sj,kxbz)values(?,?,?,?)";
		dao.insert(sql,new String[]{xh,tmp1,tmp2,tmp3});
	}
	
	/**保存学生勤工助学换岗其他信息*/
	public boolean hg_save(String[] tmp) throws SQLException{
		if(checkExists("zgdzdx_xshgxxb", "xh||gwdm||gwsbsj", tmp[0]+tmp[1]+tmp[2])){			
			return false;
		}
		String sql = "insert into zgdzdx_xshgxxb(xh,gwdm,gwsbsj,lxdh,xn,nd,xq,sqly,yhtc,hgdm,hgsj,bz)values(?,?,?,?,?,?,?,?,?,?,?,?)";
		return dao.insert(sql,tmp);
	}
	
	/**通过学院代码获取岗位列表*/
	public List<HashMap<String, String>> getGwListForXydm(String userName){
		String sql = "select distinct gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from view_gwxx a where exists(select 1 from yrdwdmb b where a.sqdw=b.yrdwdm and b.dlm=? )";
		return dao.getList(sql, new String[] { userName }, new String[] {"gwdm", "gwdmgwsbsj" });
	}
	
	/**通过学院代码获取岗位列表*/
	public List<HashMap<String, String>> getGwList(String userName){
		QgzxDao qgzxDao = new QgzxDao();
		String sql = "select distinct gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from view_gwxx a ";
		if(qgzxDao.isYrdwUser(userName)){
			sql += " where exists(select 1 from yrdwdmb b where a.sqdw=b.yrdwdm and b.dlm='" + userName + "')";
		}
		return dao.getList(sql, new String[] {}, new String[] { "gwdm", "gwdmgwsbsj" });
	}
	
	/**获取岗位性质列表*/
	public List<HashMap<String, String>> getGwXzList(){
		String sql = "select gwxzdm,gwxzmc from gwxzdmb";
		return dao.getList(sql, new String[] {}, new String[] {"gwxzdm", "gwxzmc" });
	}
	
	/**获取申请换岗学生列表*/
	public ArrayList<String[]> getHgXs(StringBuffer querry,String[] colList){
		String sql = "select xh ||'-'|| gwdm ||'-'|| gwsbsj ||'-'|| hgdm ||'-'|| hgsj 主键, rownum 行号,"
       +"a.*,(select xqmc from xqdzb b where a.xq = b.xqdm) xqmc "
       +"from view_zgdzdx_xshgxxb a" + querry.toString();
		System.out.println(sql);
		return dao.rsToVator(sql, new String[] {}, colList);
	}
	
	/**获取单条学生换岗详细信息*/
	public HashMap<String, String> getQgzxOne(String pkValue){
		String [] tmp = pkValue.split("-");
		String sql = "select a.*,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc from view_zgdzdx_xshgxxb a where xh=? and gwdm=? and gwsbsj=? and hgdm=? and hgsj=?";
		return dao.getMapNotOut(sql, tmp);
	}
	
	/**学生换岗信息更新
	 * @throws Exception */
	public boolean hgxx_update(String[] tmp) throws Exception{
		String sql = "update zgdzdx_xshgxxb set lxdh=?,xn=?,nd=?,xq=?,sqly=?,yhtc=?,"
			+ "hgdm=?,hgsj=?,bz=? where xh=? and gwdm=? and gwsbsj=? and hgdm=? and hgsj=?";
		return dao.runUpdate(sql, tmp);
	}
	
	/**学生换岗审核
	 * @throws Exception */
	public boolean hgsqsh(String[] tmp,String userType,String type) throws Exception{
		String sql = "update zgdzdx_xshgxxb set "+userType+"yj = '"+type+"' where "
				+" xh=? and gwdm=? and gwsbsj=? and hgdm=? and hgsj=?";
		return dao.runUpdate(sql, tmp);
	}
	
	/**学生换岗审核后其他记录设置为不通过
	 * @throws Exception */
	public boolean hgsqshOther(String[] tmp,String userType,String type) throws Exception{
		String sql = "update zgdzdx_xshgxxb set "+userType+"yj = '"+type+"' where "
				+" xh=? and gwdm<>? and gwsbsj<>? and hgdm<>? and hgsj<>?";
		return dao.runUpdate(sql, tmp);
	}
	
	/**获取学生在岗位信息*/
	public HashMap<String, String> getXsgwxx(String xh,String gwdm,String gwsbsj){
		String sql = "select * from xsgwxxb where xh=? and gwdm=? and gwsbsj=?";
		return dao.getMapNotOut(sql, new String[]{xh,gwdm,gwsbsj});
	}
	
	/**获取学生换岗信息*/
	public HashMap<String, String> getXshgxx(String[] tmp){
		String sql = "select * from zgdzdx_xshgxxb where xh=? and gwdm=? and gwsbsj=? and hgdm=? and hgsj=?";
		return dao.getMapNotOut(sql, tmp);
	}
	
	/**更新学生岗位信息表
	 * @throws Exception */
	public boolean updateXsgwxxb(HashMap<String, String> map,String xh,String gwdm,String gwsbsj) throws Exception{
		String[] tmp = {map.get("xyyj"),map.get("xxyj"),map.get("lxdh"),map.get("xn"),map.get("nd"),
				map.get("xq"),map.get("sqly"),map.get("yhtc"),map.get("hgdm"),map.get("hgsj"),
				map.get("bz"),xh,gwdm,gwsbsj};
		String sql = "update xsgwxxb set xyyj=?,xxyj=?,lxdh=?,xn=?,nd=?,xq=?,xssq=?,yhtc=?,gwdm=?,gwsbsj=?,bz=?"
			+ " where xh=? and gwdm=? and gwsbsj=?";
		return dao.runUpdate(sql, tmp);
	}
	
	/**插入调岗记录
	 * @throws SQLException */
	public boolean insertDgjl(String xn,String xq,HashMap<String, String> gwmap,HashMap<String, String> hgmap) throws SQLException{
		String[] tmp = {xn,xq,hgmap.get("xh"),hgmap.get("sqsj"),hgmap.get("xn"),hgmap.get("xq"),hgmap.get("nd"),
				hgmap.get("hgdm"),hgmap.get("hgsj"),gwmap.get("xn"),gwmap.get("xq"),gwmap.get("nd"),
				gwmap.get("gwdm"),gwmap.get("gwsbsj")};
		String sql="insert into qgzx_gwtzb(xn,xq,xh,tzsj,tzhgzxn,tzhgzxq,tzhgznd,tzhgw,tzhgwsbsj,"
			+ "tzqgzxn,tzqgzxq,tzqgznd,tzqgw,tzqgwsbsj)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		return dao.insert(sql, tmp);
	}
	
	/**保存辞岗信息
	 * @throws SQLException */
	public boolean cgxx_save(String[] tmp) throws SQLException{
		if(checkExists("zgdzdx_xscgxxb", "xh||gwdm||gwsbsj", tmp[0]+tmp[1]+tmp[2])){
			return false;
		}
		String sql = "insert into zgdzdx_xscgxxb(xh,gwdm,gwsbsj,lxdh,xn,nd,xq,sqly,bz)values(?,?,?,?,?,?,?,?,?)";
		return dao.insert(sql, tmp);
	}
	
	/**更新辞岗信息
	 * @throws Exception */
	public boolean cgxx_update(String[] tmp) throws Exception{
		String sql = "update zgdzdx_xscgxxb set lxdh=?,sqly=?,bz=? where xh=? and gwdm=? and gwsbsj=?";
		return dao.runUpdate(sql,tmp);
	}
	
	/**获取申请辞岗学生列表*/
	public ArrayList<String[]> getCgXs(StringBuffer querry,String[] colList){
		String sql = "select xh ||'-'|| gwdm ||'-'|| gwsbsj 主键, rownum 行号,"
				     +"a.*,(select xqmc from xqdzb b where a.xq = b.xqdm) xqmc "
				     +"from view_zgdzdx_xscgxxb a" + querry.toString();
		System.out.println(sql);
		return dao.rsToVator(sql, new String[] {}, colList);
	}
	
	/**学生辞岗审核
	 * @throws Exception */
	public boolean cgsqsh(String[] tmp,String userType,String type) throws Exception{
		String sql = "update zgdzdx_xscgxxb set "+userType+"yj = '"+type+"' where "
				+" xh=? and gwdm=? and gwsbsj=?";
		return dao.runUpdate(sql, tmp);
	}
	
	/**获取学生辞岗信息*/
	public HashMap<String, String> getXscgxx(String[] tmp){
		String sql = "select * from zgdzdx_xscgxxb where xh=? and gwdm=? and gwsbsj=?";
		return dao.getMapNotOut(sql, tmp);
	}
	
	/**删除学生岗位信息表中辞岗学生记录
	 * @throws Exception */
	public boolean deleteXsgwxxb(String xh,String gwdm,String gwsbsj) throws Exception{
		String sql = "delete xsgwxxb where xh=? and gwdm=? and gwsbsj=?";
		return dao.runUpdate(sql, new String[] {xh,gwdm,gwsbsj});
	}
	
	/**插入调岗记录
	 * @throws SQLException */
	public boolean insertDgjl(String xn,String xq,HashMap<String, String> gwmap) throws SQLException{
		boolean result = true;
		String[] tmp = {xn,xq,gwmap.get("xh"),gwmap.get("xn"),gwmap.get("xq"),gwmap.get("nd"),
				gwmap.get("gwdm"),gwmap.get("gwsbsj")};
		String tzhgw = "已辞岗";
		String xxdm = StandardOperation.getXxdm();
		if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
			//广州大学
			tzhgw = "退岗";
		}
		if(StringUtils.isNotNull(gwmap.get("xh"))){
			String sql="insert into qgzx_gwtzb(xn,xq,xh,tzhgw,"
				+ "tzqgzxn,tzqgzxq,tzqgznd,tzqgw,tzqgwsbsj,tzsj,shjg)values(?,?,?,'" + tzhgw + "',?,?,?,?,?,to_char(SYSDATE,'YYYYMMDDHH24MISS'),'通过')";
			result = dao.insert(sql, tmp);
		}
		return result;
	}
	
	/**获取单条学生辞岗详细信息*/
	public HashMap<String, String> getQgzxcgOne(String pkValue){
		String [] tmp = pkValue.split("-");
		String sql = "select a.*,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc from view_zgdzdx_xscgxxb a where xh=? and gwdm=? and gwsbsj=?";
		return dao.getMapNotOut(sql, tmp);
	}
	
	/**获取用人单位信息*/
	public HashMap<String, String> getYrdwxx(String userName){
		String sql = "select * from yrdwdmb where dlm=?";
		return dao.getMapNotOut(sql, new String[] {userName});
	}
	
	/**中国地质大学获取用人单位辞退学生临时信息*/
	public ArrayList<String[]> getYrdwctxslsxx(String userName){
		String sql = "select xh,gwdm,gwsbsj,lxdh,bj,yhm from zgdzdx_yrdwghxslsb where yhm=? and bj='辞退'";
		return dao.rsToVator4(sql, new String[]{userName}, new String[]{"xh","gwdm","gwsbsj","lxdh","bj","yhm"});
	}
	/**中国地质大学获取用人单位更换学生临时信息*/
	public ArrayList<String[]> getYrdwghxslsxx(String userName){
		String sql = "select xh,gwdm,gwsbsj,lxdh,bj,yhm from zgdzdx_yrdwghxslsb where yhm=? and bj='换岗'";
		return dao.rsToVator4(sql, new String[]{userName}, new String[]{"xh","gwdm","gwsbsj","lxdh","bj","yhm"});
	}
	/**中国地质大学获取用人单位申请学生临时信息*/
	public ArrayList<String[]> getYrdwsqxslsxx(String userName){
		String sql = "select xh,gwdm,gwsbsj,lxdh,bj,yhm from zgdzdx_yrdwghxslsb where yhm=? and bj='申请'";
		return dao.rsToVator4(sql, new String[]{userName}, new String[]{"xh","gwdm","gwsbsj","lxdh","bj","yhm"});
	}
	
	/**中国地质大学用人单位更换学生正式数据保存
	 * @throws Exception */
	public boolean insertYrdwghXs(String xh,String gwdm,String gwsbsj,String lxdh,String bj,
			String yhm,String xn,String nd,String xq,String sqly,String bz) throws Exception{
		String sql = "insert into zgdzdx_yrdwghxsb (xh,gwdm,gwsbsj,lxdh,bj,yhm,"
			+ "xn,nd,xq,sqly,bz)values(?,?,?,?,?,?,?,?,?,?,?)";
		return dao.insert(sql, new String[]{xh,gwdm,gwsbsj,lxdh,bj,yhm,xn,nd,xq,sqly,bz});
	}
	
	/**清理临时表中的学生记录
	 * @throws Exception */
	public boolean deleteYrdwghxslsb(String userName) throws Exception{
		String sql = "delete zgdzdx_yrdwghxslsb where yhm=?";
		return dao.runUpdate(sql, new String[]{userName});
	}
	
	/**获取用人单位更换学生名单中是否有重复未审核记录*/
	public String getYrdwghXs(String xh,String gwdm,String gwsbsj,String xn,String nd,String xq){
		String sql = "select count(*) num from zgdzdx_yrdwghxsb where xh=? "
			+"and gwdm=? and gwsbsj=? and xn=? and nd=? and xq=?";
		return dao.getOneRs(sql, new String[] {xh,gwdm,gwsbsj,xn,nd,xq}, "num");
	}
	
	/**获取用人单位更换学生名单列表*/
	public ArrayList<String[]> getYrdwGhXs(StringBuffer querry,String[] colList){
		String sql = "select xh ||'-'|| gwdm ||'-'|| gwsbsj ||'-'|| bj 主键, rownum 行号,"
       +"a.*,(select xqmc from xqdzb b where a.xq = b.xqdm) xqmc "
       +"from view_zgdzdx_yrdwghxsxxb a" + querry.toString();
		return dao.rsToVator(sql, new String[] {}, colList);
	}
	
	/**获取单条用人单位更换学生详细信息*/
	public HashMap<String, String> getYrdwGhXsOne(String pkValue){
		String [] tmp = pkValue.split("-");
		String sql = "select * from view_zgdzdx_yrdwghxsxxb where xh=? and gwdm=? and gwsbsj=? and bj=?";
		return dao.getMapNotOut(sql, tmp);
	}
	
	/**用人单位更换学生审核
	 * @throws Exception */
	public boolean yrdwGhxssh(String[] tmp,String type) throws Exception{
		String sql = "update zgdzdx_yrdwghxsb set xxyj = '"+type+"' where "
				+" xh=? and gwdm=? and gwsbsj=? and bj=?";
		return dao.runUpdate(sql, tmp);
	}
	
	/**插入调岗记录
	 * @throws SQLException */
	public boolean insertYrdwDgjl(String xn,String xq,HashMap<String, String> gwmap,HashMap<String, String> hgmap) throws SQLException{
		String[] tmp = {xn,xq,hgmap.get("xh"),hgmap.get("sqsj"),hgmap.get("xn"),hgmap.get("xq"),hgmap.get("nd"),
				hgmap.get("gwdm"),hgmap.get("gwsbsj"),gwmap.get("xn"),gwmap.get("xq"),gwmap.get("nd"),
				gwmap.get("gwdm"),gwmap.get("gwsbsj")};
		String sql="insert into qgzx_gwtzb(xn,xq,xh,tzsj,tzhgzxn,tzhgzxq,tzhgznd,tzhgw,tzhgwsbsj,"
			+ "tzqgzxn,tzqgzxq,tzqgznd,tzqgw,tzqgwsbsj)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		return dao.insert(sql, tmp);
	}
	
	/**用人单位更换学生更新学生岗位信息表
	 * 用人单位换岗，学生处审核同意，则将学生岗位信息表中的信息改为不通过,学生就是通过勤工助学状态
	 * @throws Exception */
	public boolean updateXsgwxxb(String xh,String gwdm,String gwsbsj) throws Exception{
		String[] tmp = {xh,gwdm,gwsbsj};
		String sql = "update xsgwxxb set xxyj='不通过' where xh=? and gwdm=? and gwsbsj=?";
		return dao.runUpdate(sql, tmp);
	}
	
	/**插入调岗记录
	 * @throws SQLException */
	public boolean insertYrdwDgjl2(String xn,String xq,HashMap<String, String> gwmap,HashMap<String, String> hgmap) throws SQLException{
		String[] tmp = {xn,xq,hgmap.get("xh"),hgmap.get("sqsj"),hgmap.get("xn"),hgmap.get("xq"),hgmap.get("nd"),
				"","",gwmap.get("xn"),gwmap.get("xq"),gwmap.get("nd"),
				gwmap.get("gwdm"),gwmap.get("gwsbsj")};
		String sql="insert into qgzx_gwtzb(xn,xq,xh,tzsj,tzhgzxn,tzhgzxq,tzhgznd,tzhgw,tzhgwsbsj,"
			+ "tzqgzxn,tzqgzxq,tzqgznd,tzqgw,tzqgwsbsj)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		return dao.insert(sql, tmp);
	}
	
	/**申请通过插入岗位信息表
	 * @throws SQLException */
	public boolean insertXsgwxxb(HashMap<String, String> hgmap) throws SQLException{
		String[] tmp = {hgmap.get("xh"),hgmap.get("gwdm"),hgmap.get("sqsj"),hgmap.get("xxyj"),
				hgmap.get("xn"),hgmap.get("nd"),hgmap.get("xq"),hgmap.get("gwsbsj")};
		String sql = "insert into xsgwxxb(xh,gwdm,sqsj,xxyj,xn,nd,xq,gwsbsj) values(?,?,?,?,?,?,?,?)";
		return dao.insert(sql, tmp);
	}
	
	/**学生换岗审核后其他记录设置为不通过
	 * @throws Exception */
	public boolean yrdwHgsqshOther(String[] tmp,String userType,String type) throws Exception{
		String sql = "update zgdzdx_xshgxxb set "+userType+"yj = '"+type+"' where "
				+" xh=? and gwdm<>? and gwsbsj<>?";
		return dao.runUpdate(sql, new String[]{tmp[0],tmp[1],tmp[2]});
	}
	
	/**获取单条用人单位更换学生详细信息*/
	public HashMap<String, String> getYrdwGhXsOne(String xh,String gwdm,String gwsbsj,String bj){
		String sql = "select * from view_zgdzdx_yrdwghxsxxb where xh=? and gwdm=? and gwsbsj=? and bj=?";
		return dao.getMapNotOut(sql, new String[]{xh,gwdm,gwsbsj,bj});
	}
	
	/**更新辞岗学生在勤工助学表中的审核状态
	 * @throws Exception */
	public boolean updateQgzxsqb(String xh) throws Exception{
		String sql = "update qgzxsqb set xysh='不通过',xxsh='不通过' where xh=?";
		return dao.runUpdate(sql, new String[] {xh});
	}
	
	/**
	 * 判断用户是否是用人单位用户
	 * @param String userName
	 * @return boolean
	 * */
	public boolean isYrdw(String userName){
		String sql = "select count(*) count from yrdwdmb where dlm=?";
		String result = getOneRs(sql, new String[]{userName},"count");
		result = StringUtils.isNull(result) ? "0" : result; 
		return Integer.parseInt(result) > 0 ? true : false; 
	}
	
	/**
	 * 检测记录是否存在
	 * @param String tableName
	 * @param String pk
	 * @param String pkValue
	 * @return boolean
	 * */
	public boolean checkExists(String tableName, String pk, String pkValue){
		String sql = "select count(*) num from " + tableName + " where " + pk + "=?";
		String num = getOneRs(sql, new String[]{pkValue}, "num");
		return Integer.parseInt(StringUtils.isNull(num) ? "0" : num)>0 ? true : false;
	}
	
	/**
	 * 查询学生在的岗位列表
	 * @param String xh
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String,String>> selectXsgwList(String xh){
		String[] outputValue = {"gwdm","gwdmgwsbsj"};
		String sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from view_xsgwxx where xh=? and sfyx='有效'";
		return getList(sql, new String[]{xh}, outputValue);
	}
	
	/**更新学生岗位信息表
	 * @throws Exception */
	public boolean updateXsgwxx(HashMap<String, String> map,String xh,String gwdm,String gwsbsj) throws Exception{
		
		String[]sqlArr=new String[2];
		
		sqlArr[0]=" delete from xsgwxxb where xh='"+xh+"' and gwdm='"+map.get("hgdm")+"' and gwsbsj='"+map.get("hgsj")+"' ";
		sqlArr[1]=" insert into xsgwxxb(xh,xyyj,xxyj,lxdh,xn,nd,xq,xssq,yhtc,gwdm,gwsbsj) values('"+map.get("xh")+"','"+map.get("xyyj")+"'," +
				"'"+map.get("xxyj")+"','"+map.get("lxdh")+"','"+map.get("xn")+"','"+map.get("nd")+"','"+map.get("xq")+"'," +
						" '"+map.get("sqly")+"','"+map.get("yhtc")+"','"+map.get("hgdm")+"','"+map.get("hgsj")+"') ";	
		int []bool=dao.runBatch(sqlArr);
		boolean blog=true;
		if(bool[1]==0){
			blog=false;
		}
		return blog;
	}
}
