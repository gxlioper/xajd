package xgxt.studentInfo.zgdzdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.comm.search.SearchService;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 中国地质大学学生信息DAO
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-10-21</p>
 */
public class XsxxZgdzdxDAO extends DAO {
	
	ArrayList<String> values = new ArrayList<String>();
	
	/**
	 * 查询学生信息
	 * @param CommanForm model
	 * @return List<String[]> 
	 * */
	public List<String[]> selectXsxx(XsxxZgdzdxForm model){
		int  start= model.getPages().getStart();
		int  end= model.getPages().getStart() + model.getPages().getPageSize();
		String[] outputValue = getCols();
		String tj = getCondition(model).toString();
		
		String sql = "select count(1) num from view_xsxxb_zgdzdx a " + tj; 
		String count = getOneRs(sql, values != null ? values.toArray(new String[0]) : new String[]{} , "num");
		count = StringUtils.isNull(count) ? "0" : count;
		model.getPages().setMaxRecord(Integer.parseInt(count));//总记录数
		
		//数据查询
		sql = "select * from (select a.xh,a.xm,a.xb,a.xz,a.nj,a.bjmc,a.xjztm,a.ssbh,a.byny,a.xslbmc,a.xslxmc,ydlbmc,rownum r,rownum 行号,xsqrxxbz  from view_xsxxb_zgdzdx a " + tj + ") where r> " + start + " and r<=" + end;
		return rsToVator(sql,  values != null ? values.toArray(new String[0]) : new String[]{}, outputValue);
	}
	
	/**
	 * 获取学生信息查询显示的字段
	 * @return String[]
	 * */
	public String[] getCols(){
		String[] colList = null;
		boolean modiFlag = checkModifyXsxx();
		if(modiFlag){
			colList = new String[]{"xh", "r", "xm", "xb", "nj", "xz", "bjmc","xjztm", "ssbh", "byny", "xslbmc", "xslxmc","ydlbmc", "xsqrxxbz" };
		}else{
			colList = new String[] { "行号", "r",  "xh", "xm", "xb", "nj", "xz", "bjmc","xjztm", "ssbh" ,"byny", "xslbmc", "xslxmc", "ydlbmc", "xsqrxxbz" };
		}
		
		return colList;
	}
	
	/**
	 * 判断是否可以修改学生信息
	 * */
	public boolean checkModifyXsxx(){
		String flag = getOneRs("select modistuinfo from xtszb", new String[] {}, "modistuinfo");
		flag = StringUtils.isNull(flag) ? "1" : flag;
		return Integer.parseInt(flag) == 1 ? true : false;
	}
	
	/**
	 * 获取学生信息查询条件
	 * @param CommanForm model
	 * @return StringBuffer
	 * */
	public StringBuffer getCondition(XsxxZgdzdxForm model){
		StringBuffer sb = new StringBuffer(" where 1=1 ");
		//将页面的值转换为中文
		model.setXh(DealString.toGBK(model.getXh()));
		model.setXm(DealString.toGBK(model.getXm()));
		model.setXb(DealString.toGBK(model.getXb()));
		model.setSfzh(DealString.toGBK(model.getSfzh()));
		model.setKsh(DealString.toGBK(model.getKsh()));
		model.setXz(DealString.toGBK(model.getXz()));
		model.setJg(DealString.toGBK(model.getJg()));
		model.setByny(DealString.toGBK(model.getByny()));
		model.setSsbh(DealString.toGBK(model.getSsbh()));
		model.setJtcyxm(DealString.toGBK(model.getJtcyxm()));
		model.setXjzt(DealString.toGBK(model.getXjzt()));
		String xsqrxxbz = model.getXsqrxxbz();
		
		//添加查询条件
		if (!StringUtils.isNull(model.getXh())) {
			sb.append(" and a.xh like '%'||?||'%'");
			values.add(model.getXh());
		}
		if (!StringUtils.isNull(model.getXm())) {
			sb.append(" and a.xm like '%'||?||'%'");
			values.add(model.getXm());
		}
		if (!StringUtils.isNull(model.getSfzh())) {
			sb.append(" and a.sfzh like '%'||?||'%'");
			values.add(model.getSfzh());
		}
		if (!StringUtils.isNull(model.getKsh())) {
			sb.append(" and a.ksh like '%'||?||'%'");
			values.add(model.getKsh());
		}
		if (!StringUtils.isNull(model.getXz())) {
			sb.append(" and a.xz like '%'||?||'%'");
			values.add(model.getXz());
		}
		if (!StringUtils.isNull(model.getJg())) {
			sb.append(" and a.jg like '%'||?||'%'");
			values.add(model.getJg());
		}
		if (!StringUtils.isNull(model.getByny())) {
			sb.append(" and a.byny like '%'||?||'%'");
			values.add(model.getByny());
		}
		if (!StringUtils.isNull(model.getSsbh())) {
			sb.append(" and a.ssbh like '%'||?||'%'");
			values.add(model.getSsbh());
		}
		if (!StringUtils.isNull(model.getJtcyxm())) {
			sb.append(" and exists(select 1 from view_xsjtxx b where a.xh=b.xh and (b.jtcy1_xm like ? or b.jtcy2_xm like ? or b.jtcy3_xm like ?))");
			values.add(model.getJtcyxm());
			values.add(model.getJtcyxm());
			values.add(model.getJtcyxm());
		}
		if (!StringUtils.isNull(model.getNj())) {
			sb.append(" and a.nj = ?");
			values.add(model.getNj());
		}
		if (!StringUtils.isNull(model.getXydm())) {
			sb.append(" and a.xydm = ?");
			values.add(model.getXydm());
		}
		if (!StringUtils.isNull(model.getZydm())) {
			sb.append(" and a.zydm = ?");
			values.add(model.getZydm());
		}
		if (!StringUtils.isNull(model.getBjdm())) {
			sb.append(" and a.bjdm = ?");
			values.add(model.getBjdm());
		}
		if (!StringUtils.isNull(model.getMz())) {
			sb.append(" and a.mz = ?");
			values.add(model.getMz());
		}
		if (!StringUtils.isNull(model.getZzmm())) {
			sb.append(" and a.zzmm = ?");
			values.add(model.getZzmm());
		}
		if (!StringUtils.isNull(model.getXslb())) {
			sb.append(" and a.xslb = ?");
			values.add(model.getXslb());
		}
		if (!StringUtils.isNull(model.getXslx())) {
			sb.append(" and a.xslx = ?");
			values.add(model.getXslx());
		}
		if (!StringUtils.isNull(model.getXb())) {
			sb.append(" and a.xb = ?");
			values.add(model.getXb());
		}
		if (!StringUtils.isNull(model.getXjzt())) {
			sb.append(" and a.xjztm = ?");
			values.add(model.getXjzt());
		}
		if (!StringUtils.isNull(model.getYdlbm())) {
			sb.append(" and a.ydlbm = ?");
			values.add(model.getYdlbm());
		}
		if (!StringUtils.isNull(xsqrxxbz)) {
			sb.append(" and exists(select 1 from xsxxb b where a.xh=b.xh and b.xsqrxxbz=?)");
			values.add(xsqrxxbz);
		}
		//毕业生信息查询
		if (!StringUtils.isNull(model.getSfyby()) && "yes".equalsIgnoreCase(model.getSfyby())) {
			sb.append(" and a.nfby='是'");
		}
		//是否是辅导员
		if(model.isFdy()){
			sb.append(" and exists(select 1 from view_fdybbj f where a.bjdm=f.bjdm and f.zgh=?)");
			values.add(model.getUserName());
		}
		return sb;
	}
	
	/**
	 * 判断表中的记录是否存在
	 * @param String tableName
	 * @param String pk
	 * @param String pkValue
	 * @reutrn boolean
	 * */
	public boolean checkExists(String tableName, String pk, String pkValue) {
		boolean flag = false;
		DAO dao = new DAO();
		String sql = "select count(*) num from " + tableName + " where " + pk + "=?";
		String num = dao.getOneRs(sql, new String[]{pkValue}, "num");
		if (!"0".equalsIgnoreCase(num)) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 查询异动类别代码
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getYdlbList(){
		String sql = "select ydlbm dm,ydlbmc mc from dm_ydlb order by ydlbm";
		return getList(sql, new String[]{}, new String[]{"dm","mc"});
	}
	
	/**
	 * 初始化学生确认信息标志
	 * @return boolean
	 * */
	public boolean modifyXsqrxxbz(){
		boolean result = false;
		String sql = "update xsxxb set xsqrxxbz='否'";
		try{
			result = runUpdate(sql, new String[]{});
		}catch(Exception e){
			result = false;
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 保存用户必填字段信息
	 * @param model
	 * @param request
	 * @return boolean
	 * */
	public boolean saveYhbtzdxx(XsxxZgdzdxForm model,HttpServletRequest request){
		boolean flag = false;
		DAO dao = DAO.getInstance();
		String yhjs = model.getYhjs();
		String xsxxzd = model.getXsxxzd();
		String jtxxzd = model.getJtxxzd();
		
		try {
			if(xsxxzd != null && !"".equalsIgnoreCase(xsxxzd)){
				//保存学生个人信息的相关权限信息
				String[] xsxx = xsxxzd.split("!!");
				String[] colCN = getColumnNameCN(xsxx, "xsxxb");	
				String sql = "delete from xsxx_btzdfpb where yhjs||zdssb = ?";
				flag = dao.runUpdate(sql, new String []{yhjs+"xsxxb"});
					String [] sqlMap = new String [xsxx.length]; 
					if(flag){
						for(int i=0; i<xsxx.length; i++){
							String sqlTmp = "insert into xsxx_btzdfpb (yhjs,zdm,zdzwmc,zdssb) values ('"+yhjs+"','"+xsxx[i]+"','"+colCN[i]+"','xsxxb')";
							sqlMap[i]=sqlTmp;
						}
						dao.runBatch(sqlMap);
					}				
			}else{
				String sql = "delete from xsxx_btzdfpb where yhjs||zdssb = ?";
				flag = dao.runUpdate(sql, new String []{yhjs+"xsxxb"});
			}
			if(jtxxzd != null && !"".equalsIgnoreCase(jtxxzd)){
				//保存学生家庭信息的相关权限信息
				String[] jtxx = jtxxzd.split("!!");
				String[] colCN = getColumnNameCN(jtxx, "xsfzxxb");
				String sql = "delete from xsxx_btzdfpb where yhjs||zdssb = ?";
				flag = dao.runUpdate(sql, new String []{yhjs+"xsfzxxb"});
				String [] sqlMap = new String [jtxx.length]; 
				if(flag){
					for(int i=0; i<jtxx.length; i++){
						String sqlTmp = "insert into xsxx_btzdfpb (yhjs,zdm,zdzwmc,zdssb) values ('"+yhjs+"','"+jtxx[i]+"','"+colCN[i]+"','xsfzxxb')";
						sqlMap[i]=sqlTmp;
					}
					dao.runBatch(sqlMap);
				}
			}else{
				String sql = "delete from xsxx_btzdfpb where yhjs||zdssb = ?";
				flag = dao.runUpdate(sql, new String []{yhjs+"xsfzxxb"});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 修改学生毕业相关信息
	 * @param model
	 * @param isFdy
	 * @param userName
	 * @return boolean
	 * */
	public boolean updateByxgxx(XsxxZgdzdxForm model, boolean isFdy, String userName){
		DAO dao = DAO.getInstance();
		boolean result = false;
		
			//更新语句
			StringBuilder sql = getUpdateColumn(model);
			//修改条件
			sql.append(" where 1=1 ");
			if(StringUtils.isNotNull(model.getNj())){
				sql.append(" and nj=?");
				values.add(model.getNj());
			}
			if(StringUtils.isNotNull(model.getXydm())){
				sql.append(" and xydm=?");
				values.add(model.getXydm());
			}
			if(StringUtils.isNotNull(model.getZydm())){
				sql.append(" and zydm=?");
				values.add(model.getZydm());
			}
			if(StringUtils.isNotNull(model.getBjdm())){
				sql.append(" and bjdm=?");
				values.add(model.getBjdm());
			}
			if(isFdy){//辅导员
				sql.append(" and exists (select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh=?)");
				values.add(userName);
			}
			
			try {

				String searchTj = SearchService.getSearchTj(model
						.getSearchModel());
				String[] inputV = SearchService.getTjInput(model
						.getSearchModel());

				sql.append(searchTj);
				
				if(inputV!=null && inputV.length>0){
					for (int i = 0; i < inputV.length; i++) {
						values.add(inputV[i]);
					}
				}
				
//				将xsxxb中不存在的学生插入到学生信息表中
				try{
					StringBuilder insertXssql = new StringBuilder("insert into xsxxb(xh,xm,xb,xydm,zydm,bjdm,nj,xz,xjztm,");
					insertXssql.append("ssbh,mz,zzmm,qsdh,ssch,rzrq,zsjzrq,syd,csrq,pycc,rxrq,jg,hkszd,sfzx,lxdh,sjhm,jtdh,");
					insertXssql.append("jtdz,jtyb,sfzh,dzyx)(select xh,xm,xb,xydm,zydm,bjdm,nj,xz,xjztm,ssbh,mz,zzmm,qsdh,ssch,");
					insertXssql.append("zsrq,zsjzrq,syd,csrq,pycc,rxrq,jg,hkszd,sfzx,lxdh,sjhm,jtdh,jtdz,jtyb,sfzh,dzyx from ");
					insertXssql.append("view_xsxxb a where not exists(select 1 from xsxxb b where a.xh=b.xh)");
					insertXssql.append(searchTj);
					insertXssql.append(")");
					result = dao.runUpdate(insertXssql.toString(), inputV);
				}catch(Exception e){
					e.printStackTrace();
					result = false;
				}
				if(result){
					result = runUpdate(sql.toString(), values != null ? values
						.toArray(new String[0]) : new String[] {});
				}
			}catch(Exception e){
				e.printStackTrace();
				result = false;
			}
		
		return result;		
	}
	
	public StringBuilder getUpdateColumn(XsxxZgdzdxForm model){
		StringBuilder sql = new StringBuilder("update xsxxb a set xh=xh ");
		if(StringUtils.isNotNull(model.getXjzt())){
			sql.append(", xjztm=?");
			values.add(model.getXjzt());
		}
		if(StringUtils.isNotNull(model.getSfzx())){
			sql.append(", sfzx=?");
			values.add(model.getSfzx());
		}
		if(StringUtils.isNotNull(model.getSfyby())){
			sql.append(", sfyby=?");
			values.add(model.getSfyby());
		}
		if(StringUtils.isNotNull(model.getNfby())){
			sql.append(", nfby=?");
			values.add(model.getNfby());
		}
		if(StringUtils.isNotNull(model.getSfbys())){
			sql.append(", sfbys=?");
			values.add(model.getSfbys());
		}
		if(StringUtils.isNotNull(model.getByny())){
			sql.append(", byny=?");
			values.add(model.getByny());
		}
		return sql;
	}
	
	/**
	 * 根据选择的数据修改学生毕业相关信息
	 * @param model
	 * @return boolean
	 * */
	public boolean updateByxgxxByData(XsxxZgdzdxForm model){
		DAO dao = DAO.getInstance();
		boolean flag = false;
		String pk = model.getPk();
		String[] xh = pk.split("!!");
		String message = "";
		try{
		//将xsxxb中不存在的学生插入到学生信息表中
		StringBuilder sql = new StringBuilder("insert into xsxxb(xh,xm,xb,xydm,zydm,bjdm,nj,");
		sql.append("xz,xjztm,ssbh,mz,zzmm,qsdh,ssch,rzrq,zsjzrq,syd,csrq,");
		sql.append("pycc,rxrq,jg,hkszd,sfzx,lxdh,sjhm,jtdh,jtdz,jtyb,sfzh,");
		sql.append("dzyx)(select xh,xm,(case a.xb when '1' then '男' when '2' then '女'");
		sql.append("else a.xb end) xb,xydm,zydm,bjdm,to_char(nj),substr(xz,0,1) xz,xjztm,");
		sql.append("'' ssbh,'' mz,'' zzmm,'' qsdh,'' ssch,'' rzrq,'' zsjzrq,");
		sql.append("'' syd,'' csrq,pycc,'' rxrq,'' jg, '' hkszd,sfzx,'' lxdh, '' sjhm,'' jtdh,'' jtdz,");
		sql.append("'' jtyb,sfzh,'' dzyx from bks_xsjbxx a where not exists(select 1 from xsxxb b where a.xh=b.xh) and a.xh in ("); 
		for(int i=0; i<xh.length-1; i++){
			sql.append("'");
			sql.append(xh[i]);
			sql.append("',");
		}
		if(xh.length>1){
			sql.append("'");
			sql.append(xh[xh.length-1]);
			sql.append("'");
		}
		sql.append(")) ");
		
		dao.runUpdate(sql.toString(), new String[]{});
		
		for(int i=0; i<xh.length; i++){
			if(xh[i] != null && !"".equalsIgnoreCase(xh[i])){
				values = new ArrayList<String>();
				flag = dao.runUpdate(getUpdateColumn(model)+" where xh='" + xh[i] + "'", values != null ? values.toArray(new String[0]) : new String[]{});
				if(!flag){
					if(message == ""){
						message += i+1;
					}else{
						message += "," + (i+1);
					}					 
				}
			}
		}
		} catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
}
