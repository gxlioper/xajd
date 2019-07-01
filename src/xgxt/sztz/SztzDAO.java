package xgxt.sztz;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfsoft.utils.StringUtil;

import xgxt.DAO.DAO;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;


public class SztzDAO extends DAO {

	
	/**
	 * 核心能力保存
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveHxnl(SztzForm model) throws Exception{
		
		String[] hxnldm = model.getHxnldm();
		String[] hxnlmc = model.getHxnlmc();
		String[] minfs = model.getMinfs();
		String[] maxfs = model.getMaxfs();
		String kmdm = model.getKmdm();
		
		String delSQL = "delete from xg_sztz_hxnlb where kmdm=?";
		boolean flg = runUpdate(delSQL, new String[]{kmdm});
		
		String insertSQL = "insert into xg_sztz_hxnlb (hxnldm,hxnlmc,kmdm,minfs,maxfs) values (?,?,?,?,?)";
		List<String[]> input = new ArrayList<String[]>();
		
		for (int i = 0 ; i < hxnldm.length ; i++){
			if (StringUtils.isNotNull(hxnldm[i])){
				String[] temp = new String[]{hxnldm[i],hxnlmc[i],kmdm,minfs[i],maxfs[i]};
				input.add(temp);
			}
		}
		
		int[] result = runBatch(insertSQL, input);
		return flg && checkBatchResult(result);
	}
	
	/**
	 * 更新项目申请
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean updateXmsb(SztzForm model) throws Exception{
		String delSQL = "update xg_sztz_xmglb set xn='"+model.getXn()+"',xq='"+model.getXq()+"',kmdm = '"+model.getKmdm()+"',hxnl='"+model.getHxnl()+"',xmlx='"+model.getXmlx()+"'," +
				"xmmc='"+model.getXmmc()+"',shlcid='"+model.getShlcid()+"',jcf='"+model.getJcf()+"',jbkssj='"+model.getJbkssj()+"',jbjssj='"+model.getJbjssj()+"'," +
						"xs='"+model.getXs()+"',fzr='"+model.getFzr()+"',bz='"+model.getBz()+"',rssx='"+model.getRssx()+"',nj='"+model.getNj()+"'," +
								"zbf = '"+model.getZbf()+"',xydm='"+model.getXydm()+"' where id = '"+model.getId()+"'";
		
		return runUpdate(delSQL, new String[]{});
	}
	
	
	/**
	 * 获取相应核心能力
	 * @param pkValue
	 * @return
	 */
	public List<HashMap<String,String>> getHxnl(String pkValue){
		
		String sql = "select hxnldm,hxnlmc,minfs,maxfs from xg_sztz_hxnlb where kmdm=?";
		
		return getListNotOut(sql, new String[]{pkValue});
	}
	
	/**
	 * 获取相应科目代码
	 * @param pkValue
	 * @return
	 */
	public List<HashMap<String,String>> getXmBykmdm(String pkValue){
		String pkValues[] = pkValue.split("!@!");	
		String pk="";
		for (int i=0;i<pkValues.length;i++){
			if(i!=pkValues.length-1){
				pk+="'"+pkValues[i]+"'"+",";
			}else{
				pk+="'"+pkValues[i]+"'";
			}
		}
		String sql = "select * from xg_sztz_xmglb where kmdm in ('"+pk+"')";
		
		return getListNotOut(sql, new String[]{});
	}
	
	
	/**
	 * 获取相应项目
	 * @param pkValue
	 * @return
	 */
	public List<HashMap<String,String>> getXmlist(String pkValue){
		String pkValues[] = pkValue.split("!@!");	
		String pk="";
		for (int i=0;i<pkValues.length;i++){
			if(i!=pkValues.length-1){
				pk+="'"+pkValues[i]+"'"+",";
			}else{
				pk+="'"+pkValues[i]+"'";
			}
		}
		String sql = "select * from xg_sztz_xssqb where id in ("+pk+")";
		
		return getListNotOut(sql, new String[]{});
	}
	
	
	/**
	 * 删除科目及相关核心能力
	 * @param kmdm
	 * @return
	 * @throws Exception
	 */
	public boolean delKmsz(String[] kmdm) throws Exception{
		
		StringBuilder delKmslSQl = new StringBuilder();
		
		delKmslSQl.append("delete from xg_sztz_kmdmb where ");
		
		for (int i = 0 ; i < kmdm.length ; i++){
			delKmslSQl.append("kmdm=?");
			
			if (i != kmdm.length-1){
				delKmslSQl.append(" or ");
			}
		}
		
		String delHxnlSQL = delKmslSQl.toString().replace("xg_sztz_kmdmb", "xg_sztz_hxnlb");
		return runUpdate(delKmslSQl.toString(), kmdm) && runUpdate(delHxnlSQL, kmdm);
	}
	
	
	/**
	 * 保存项目奖项
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveXmjx(SztzForm model) throws Exception{
		
		String[] jxdm = model.getJxdm();
		String[] jxmc = model.getJxmc();
		String[] jxfs = model.getJxfs();
		String id = model.getId();
		
		String delSQL = "delete from xg_sztz_xmjxb where xmid=?";
		boolean flg = runUpdate(delSQL, new String[]{id});
		boolean jxflg = true;
		if(jxdm!=null){
			String insertSQL = "insert into xg_sztz_xmjxb (xmid,jxdm,jxmc,jxfs) values (?,?,?,?)";
			List<String[]> input = new ArrayList<String[]>();
			
			for (int i = 0 ; i < jxdm.length ; i++){
				if (StringUtils.isNotNull(jxdm[i])){
					String[] temp = new String[]{id,jxdm[i],jxmc[i],jxfs[i]};
					input.add(temp);
				}
			}
			int[] result = runBatch(insertSQL, input);
			jxflg = checkBatchResult(result);
		}
		
		return flg && jxflg;
	}
	
	
	/**
	 * 加载项目奖项
	 * @param xmid
	 * @return
	 */
	public List<HashMap<String,String>> getXmjxList(String xmid){
		
		String sql = "select jxdm,jxmc,jxfs from xg_sztz_xmjxb where xmid=?";
		
		return getListNotOut(sql, new String[]{xmid});
	}
	
	
	/**
	 * 删除项目
	 * @param xmid
	 * @return
	 * @throws Exception
	 */
	public boolean delXm(String[] xmid) throws Exception{
		
		StringBuilder delKmslSQl = new StringBuilder();
		
		delKmslSQl.append("delete from xg_sztz_xmglb where ");
		
		for (int i = 0 ; i < xmid.length ; i++){
			delKmslSQl.append("id=?");
			
			if (i != xmid.length-1){
				delKmslSQl.append(" or ");
			}
		}
		
		String delHxnlSQL = delKmslSQl.toString().replace("xg_sztz_xmglb", "xg_sztz_xmshjlb");
		return runUpdate(delKmslSQl.toString(), xmid) && runUpdate(delHxnlSQL, xmid);
	}
	
	
	/**
	 * 加载当前学年、学期项目列表
	 * @return
	 */
	public List<HashMap<String,String>> getXmList(){
		
		String sql = "select id,xmmc,kmmc,hxnlmc,xmlxmc,shlcid " +
				"from xg_view_sztz_xmglb where xn=(select dqxn from xtszb) " +
				"and xq=(select dqxq from xtszb) and shzt='通过'";
		
		return getListNotOut(sql, new String[]{});
	}
	
	
	/**
	 * 学分审核查询
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<String[]> xfshQuery(SztzForm model,User user) throws Exception{
		String[] queryLikeArr = new String[]{"xmmc","xh","xm","sbr"};
		String[] queryArr = new String[]{"xn","xq","kmdm","hxnl","xmlx","nj","xydm","zydm","bjdm"};
		Map<String,Object> map = CommonQueryDAO.getQuerySQL(model, queryArr, queryLikeArr);
		String userQuery = CommonQueryDAO.getQuerySqlByUser(user, "a", "xydm", "bjdm");
		
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[]{"id","xh","xm","xn","xqmc","xmmc","sqsj"};
		
		sql.append(" select rownum r,a.* from (")
		   
		   .append("select a.id,b.xh,")
		   .append("(select xm from view_xsjbxx where xh = b.xh) xm,")
		   .append("(select nj from view_xsjbxx where xh = b.xh) nj,")
		   .append("(select xydm from view_xsjbxx where xh = b.xh) xydm,")
		   .append("(select zydm from view_xsjbxx where xh = b.xh) zydm,")
		   .append("(select bjdm from view_xsjbxx where xh = b.xh) bjdm,")
		   .append("c.xn,c.xq,c.xqmc,c.kmdm,c.hxnl,c.xmlx,c.xmmc,c.sbr,b.sqsj ")
		   .append(" from (select a.id, a.lev, a.nextspgw ")
		   .append("from (select row_number() over(")
		   .append("partition by t.id order by t.shsj desc) lev,")
		   .append("t.id,t.nextspgw from xg_sztz_xssqshjlb t ")
		   .append("where t.sftj = '是') a where lev = 1 and exists ")
		   .append("(select 1 from (select spgw, spyh from xg_xtwh_spgwyh where spyh = ?) b")
		   .append(" where a.nextspgw = b.spgw)) a left join xg_sztz_xssqb b on a.id=b.id ")
		   .append("left join xg_view_sztz_xmglb c on b.xmid = c.id  ")
		   
		   .append(" ) a where 1 = 1")
		   .append(map.get("sql"))
		   .append(userQuery);
		
		return CommonQueryDAO.commonPageQuery(model.getPages(), sql.toString(),
				StringUtils.joinStrArr(new String[]{user.getUserName()},(String[]) map.get("input")), colList);
	}
	
	
	/**
	 * 项目审核查询
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<String[]> xmshQuery(SztzForm model,User user) throws Exception{
		String[] queryLikeArr = new String[]{"xmmc"};
		String[] queryArr = new String[]{"xn","xq","kmdm","hxnl","xmlx"};
		Map<String,Object> map = CommonQueryDAO.getQuerySQL(model, queryArr, queryLikeArr);
//		String userQuery = CommonQueryDAO.getQuerySqlByUser(user, "a", "xydm", "bjdm");
		
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[]{"id","xmmc","xn","xqmc","kmmc","hxnlmc","xmlxmc"};
		
		sql.append(" select rownum r,a.* from (")
		
		   /*.append("select a.id,b.xn,b.xqmc,b.xmmc,b.kmmc,b.hxnlmc,")
		   .append("b.xmlxmc,b.kmdm,b.hxnl,b.xmlx from (")
		   .append("select a.id from xg_sztz_xmshjlb a where exists ")
		   .append("(select 1 from (select id, spjlid from xg_sztz_xmshjlb a ")
		   .append("where exists (select 1 from (select splc, spgw, xh ")
		   .append("from xg_xtwh_spbz a where exists (select 1 ")
		   .append("from xg_xtwh_spgwyh b where a.spgw = b.spgw ")
		   .append("and b.spyh = ? )) b where a.shlcid = b.splc ")
		   .append("and a.nextspgw = b.spgw and ((a.shzt = '通过' or a.shzt is null) or ")
		   .append("(a.xtgwid <> b.spgw and a.shzt > '')))) b where a.spjlid = b.spjlid) ")
		   .append("and not exists (select 1 from (select * from xg_sztz_xmshjlb where nextspgw is null) d where a.id = d.id)")
		   .append(") a left join xg_view_sztz_xmglb b on a.id = b.id")*/
		   
		   .append("select a.id,b.xn,b.xq,b.xqmc,b.xmmc,b.kmmc,")
		   .append("b.hxnlmc,b.xmlxmc,b.kmdm,b.hxnl,b.xmlx,b.xydm ")
		   .append("from (select a.id,a.lev,a.nextspgw ")
		   .append("from (select row_number() over(")
		   .append("partition by t.id order by t.shsj desc) lev,")
		   .append("t.id,t.nextspgw from xg_sztz_xmshjlb t ")
		   .append("where t.sftj = '是') a where lev = 1 ")
		   .append("and exists (select 1 from (select spgw, spyh ")
		   .append("from xg_xtwh_spgwyh where spyh = ?) b ")
		   .append("where a.nextspgw = b.spgw)) a ")
		   .append("left join xg_view_sztz_xmglb b on a.id = b.id ")
		
		   .append(" ) a where 1 = 1");
		   
			System.out.println("Dep="+user.getUserDep()+";Type="+user.getUserType());
		   if(!StringUtil.isNull(user.getUserType())&&user.getUserType().equalsIgnoreCase("xy")){
			   sql.append(" and xydm = '"+user.getUserDep()+"' " );
		   };
		   
		   sql.append(map.get("sql"));
		   
	
		
		return CommonQueryDAO.commonPageQuery(model.getPages(), sql.toString(),
				StringUtils.joinStrArr(new String[]{user.getUserName()},(String[]) map.get("input")), colList);
	}
	
	
	/**
	 * 素质拓展结果查询
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	protected List<String[]> sztzJgcx(SztzForm model,User user) throws Exception{
		
		String[] queryLikeArr = new String[]{"xmmc","xh","xm","sbr"};
		String[] queryArr = new String[]{"xn","xq","kmdm","hxnl","xmlx","shzt","nj","xydm","zydm","bjdm"};
		Map<String,Object> map = CommonQueryDAO.getQuerySQL(model, queryArr, queryLikeArr);
		String userQuery = CommonQueryDAO.getQuerySqlByUser(user, "a", "xydm", "bjdm");
		
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[]{"dis","id","xh","xm","xn","xqmc","xmlxmc","xmmc","kmmc","hxnlmc","xs","shzt"};
		
		sql.append(" select rownum r,")
		   .append(" case when a.shzt='退回' or a.shzt='未审核' then '' else 'disabled' end dis,")
		   .append(" a.id,a.xh,a.xm,a.xn,a.xqmc,a.xmlxmc,a.xmmc,a.kmmc,a.hxnlmc,a.xs,a.shzt from xg_view_sztz_xssqb a ")
		   .append(" where 1=1 ")
		   .append(map.get("sql"))
		   .append(userQuery);
		
		return CommonQueryDAO.commonQuery(model.getPages(), sql.toString(),
				StringUtils.joinStrArr((String[]) map.get("input")), colList);
	}
	
	
	/**
	 * 素质拓展删除
	 * @param pkValues
	 * @return
	 */
	protected boolean delSztz(String[] pkValues){
		
		if (null != pkValues && pkValues.length > 0){
			
			StringBuilder sql = new StringBuilder();
			sql.append("delete from xg_sztz_xssqb where id in (");
			
			for (int i = 0 ; i < pkValues.length ; i++){
				sql.append("'")
				   .append(pkValues[i])
				   .append("'");
				
				if (i != pkValues.length-1){
					sql.append(",");
				} else {
					sql.append(")");
				}
			}
			
			String delShztSql = sql.toString().replace("xg_sztz_xssqb", "xg_sztz_xssqshjlb");
			
			try {
				return runUpdate(sql.toString(), new String[]{}) && runUpdate(delShztSql, new String[]{});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	/**
	 * 素质拓展学分查询
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	protected List<String[]> sztzXfcx(SztzForm model,User user) throws Exception{
		
		String[] queryLikeArr = new String[]{"xmmc","xh","xm","sbr"};
		String[] queryArr = new String[]{"xn","xq","kmdm","hxnl","xmlx","nj","xydm","zydm","bjdm"};
		Map<String,Object> map = CommonQueryDAO.getQuerySQL(model, queryArr, queryLikeArr);
		String userQuery = CommonQueryDAO.getQuerySqlByUser(user, "a", "xydm", "bjdm");
		
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[]{"id","xh","xm","xn","xqmc","xmlxmc","xmmc","sfcx","jcf","maxjxfs","sdxf"};
		
		sql.append(" select rownum r,")
		   .append(" a.id,a.xh,a.xm,a.xn,a.xqmc,a.xmlxmc,a.xmmc,a.kmmc,")
		   .append(" a.hxnlmc,a.jcf,a.sdxf,a.sfcx,maxjxfs from xg_view_sztz_xssqb a ")
		   .append(" where shzt='通过' ")
		   .append(map.get("sql"))
		   .append(userQuery);
		
		return CommonQueryDAO.commonQuery(model.getPages(), sql.toString(),
				StringUtils.joinStrArr((String[]) map.get("input")), colList);
	}
	
	
	/**
	 * 素质拓展打印成绩单数据
	 * @param xh
	 * @return
	 */
	protected List<HashMap<String,String>> getSztzCjdData(String xh){
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select kmmc,hxnlmc,xn,xqmc,minfs,maxfs,sum(sdxf) sdxf,sum(cxxf) cxxf from(")
		   .append(" select kmmc,hxnlmc,xn,xqmc,sfcx,minfs,maxfs,")
		   .append(" case when sfcx='否' then sdxf else '0' end sdxf,")
		   .append(" case when sfcx='是' then sdxf else '0' end cxxf")
		   .append(" from xg_view_sztz_xssqb where xh=? and shzt='通过'")
		   .append(" ) group by kmmc,hxnlmc,xn,xqmc,minfs,maxfs order by kmmc,hxnlmc");
		
		return getListNotOut(sql.toString(), new String[]{xh});
	}
	
	
	/**
	 * 素质拓展成绩单打印周期
	 * @param xh
	 * @return
	 * @throws SQLException
	 */
	protected String[] getSztzCjdZq(String xh) throws SQLException{
		String sql = "select distinct xn||'('||xqmc||')' zq from xg_view_sztz_xssqb where xh=? order by zq";
		
		return getArray(sql, new String[]{xh}, "zq");
	}
	
	
	/**
	 * 素质拓展成绩单打印所属科目、核心能力
	 * @return
	 */
	protected List<HashMap<String,String>> getSztzKmlx(){
		String sql = "select distinct kmmc,hxnlmc from xg_view_sztz_xssqb";
		
		return getListNotOut(sql, new String[]{});
	}
	
	
	/**
	 * 查询申请记录对应的审核信息
	 * @param id
	 * @return
	 */
	protected List<HashMap<String,String>> getShxxList(String id,String shjlb){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.shzt,a.shsj,a.shyj,a.shr,nvl(c.xm,a.shr) xm,")
		   .append(" nvl(b.mc,'申请') mc from  ")
		   .append(shjlb)
		   .append(" a left join xg_xtwh_spgw b on ")
		   .append(" a.xtgwid=b.id left join yhb c on a.shr=c.yhm ")
		   .append("where a.sftj='是' and a.id=? order by shsj");
		
		return getListNotOut(sql.toString(), new String[]{id});
	}
	
	
	/**
	 * 检查科目代码是否存在
	 * @param kmdm
	 * @return
	 */
	protected String checkKmdmExists(String kmdm) {
		
		String sql= "select count(1) count from xg_sztz_kmdmb where kmdm=?";
		
		return getOneRs(sql, new String[]{kmdm}, "count");
	}


	/**
	 * 查询学生可申请的项目及已申请的项目信息
	 * @param model
	 * @return
	 */
	protected List<HashMap<String,String>> getXmsqList(SztzForm model){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.id xmid,xmmc,a.kmmc,a.hxnlmc,a.xmlxmc,a.shlcid,a.rssx,")
		   .append("b.id,b.xh,b.cyjs,b.sfcx,b.cgms,b.bz,b.shzt,c.ysqrs from xg_view_sztz_xmglb a ")
		   .append("left join (select * from xg_sztz_xssqb where xh = ? ) b on a.id = b.xmid ")
		   .append("left join (select xmid, count(1) ysqrs from xg_sztz_xssqb where shzt='未审核' or shzt ='通过' group by xmid) c on a.id=c.xmid ")
		   .append("where xn=(select dqxn from xtszb) and xq=(select dqxq from xtszb) and (xydm = (select xydm from view_xsbfxx where  xh = ?) or xydm='xj') and a.shzt='通过' and (a.nj=? or a.nj='全部')");
		if(model.getQueryequals_kmdm()!=null && model.getQueryequals_kmdm()!=""){
			sql.append(" and a.kmdm=? ");
			if(model.getQueryequals_hxnl()!=null && model.getQueryequals_hxnl()!=""){
				sql.append(" and a.hxnl=? ");
				sql.append(" order by jbkssj desc ");
				return getListNotOut(sql.toString(), new String[]{model.getXh(),model.getXh(),model.getNj(),model.getQueryequals_kmdm(),model.getQueryequals_hxnl()});
			}
			sql.append(" order by jbkssj desc ");
			return getListNotOut(sql.toString(), new String[]{model.getXh(),model.getXh(),model.getNj(),model.getQueryequals_kmdm()});
		}
		sql.append(" order by jbkssj desc ");
		return getListNotOut(sql.toString(), new String[]{model.getXh(),model.getXh(),model.getNj()});
	}
	
	
	/**
	 * 获取素质拓展参数设置
	 * @param xxdm
	 * @return
	 */
	protected HashMap<String,String> getCssz(String xxdm){
		
		String sql = "select a.*,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') dqsj from xg_sztz_csszb a where xxdm=? ";
		
		return getMapNotOut(sql, new String[]{xxdm});
	}
	
	
	
	/**
	 * 查询每学期成绩单
	 * @param xh
	 * @return
	 */
	protected List<HashMap<String,String>> getMxqCjd(String xh){
			
			StringBuilder sql = new StringBuilder();
			
			sql.append(" select kmmc,hxnlmc,xn,xqmc,minfs,maxfs,sfcx,sum(sdxf) sdxf,xmmc ")
			   .append(" from xg_view_sztz_xssqb where xh=? and shzt='通过'")
			   .append(" group by kmmc,hxnlmc,xn,xqmc,minfs,maxfs,sfcx,xmmc order by kmmc,hxnlmc,xn,xqmc");
			
			return getListNotOut(sql.toString(), new String[]{xh});
		}
	protected List<HashMap<String,String>> getMxqCjd(String xh,String xn,String xq){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select kmmc,hxnlmc,xn,xqmc,minfs,maxfs,sfcx,sum(sdxf) sdxf,xmmc ")
		   .append(" from xg_view_sztz_xssqb where xh=? and xn=? and xqmc=? and shzt='通过'")
		   .append(" group by kmmc,hxnlmc,xn,xqmc,minfs,maxfs,sfcx,xmmc order by kmmc,hxnlmc,xn,xqmc");
		
		return getListNotOut(sql.toString(), new String[]{xh,xn,xq});
	}

	/**
	 * 项目打印统计
	 */
	public ArrayList<String[]> xmDytj(SztzForm model) throws Exception{
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[] { "r","xh", "xm","sfcx","sdxf"};
		sql.append("select rownum r,id,xn,xq,xqmc,xmmc,kmdm,kmmc,xmlx,xmlxmc,jcf,hxnl,hxnlmc,xs,fzr,a.xh,xm,sfcx,sdxf from xg_sztz_xssqb a left join view_xsjbxx b on a.xh=b.xh left join xg_view_sztz_xmglb c on a.xmid=c.id where a.shzt = '通过' and id=?");
		return CommonQueryDAO.commonQueryNotFy(sql.toString(), "", new String[]{model.getId()}, colList, model);
	}

	/**
	 * 报表的项目打印信息
	 * @param id
	 * @return
	 */
	public HashMap<String, String> getxmDyxx(String id) {
		String sql = "select * from xg_view_sztz_xmglb where id=?";
		return getMapNotOut(sql, new String[]{id});
	}

	/**
	 * 获取项目名称
	 * @param inputValue
	 * @param outputValue
	 * @return
	 */
	public List<HashMap<String, String>> getXmmc(String[] inputValue,
			String[] outputValue) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("select id,xmmc from xg_sztz_xmglb where xn=? and xq=? and shzt='通过'");
		return dao.getList(sql.toString(), inputValue, outputValue);
	}

	/**
	 * 获得所的学分
	 * @param inputValue
	 * @param outputValue
	 * @return
	 */
	public List<HashMap<String, String>> getSdxf(String[] inputValue,
			String[] outputValue) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("select jxfs from xg_sztz_xmjxb where jxdm=? and xmid=?");
		return dao.getList(sql.toString(), inputValue, outputValue);
	}
	
	/**
	 * 查询学生可申请的项目及已申请的项目信息
	 * @param model
	 * @return
	 */
	protected List<HashMap<String,String>> getXsxmsq(SztzForm model){
		StringBuilder sql = new StringBuilder();
		sql.append("select a.id xmid,xmmc,a.kmmc,a.hxnlmc,a.xmlxmc,a.shlcid,a.rssx,")
		   .append("b.id,b.xh,b.cyjs,b.sfcx,b.cgms,b.bz,b.shzt,c.ysqrs from xg_view_sztz_xmglb a ")
		   .append("left join (select * from xg_sztz_xssqb where xh = ? ) b on a.id = b.xmid ")
		   .append("left join (select xmid,count(1) ysqrs from xg_sztz_xssqb group by xmid) c on a.id=c.xmid ")
		   .append("where xn=(select dqxn from xtszb) and xq=(select dqxq from xtszb) and (xydm = (select xydm from view_xsbfxx where  xh = ?) or xydm='xj') and a.shzt='通过' and (a.nj=? or a.nj='全部') and a.id = ?");
		return getListNotOut(sql.toString(), new String[]{model.getXh(),model.getXh(),model.getNj(),model.getXmid()});
	}
}