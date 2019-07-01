package xsgzgl.xtwh.general.qxgl.yhgl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.Encrypt;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xsgzgl.comm.BasicDAO;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系统维护_权限管理_用户管理_dao类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author zhanghui
 * @version 1.0
 */
public class YhglNewDao extends BasicDAO{
	
	/**
	 * 获得用户信息结果集
	 * 
	 * @date 2013-01-08
	 * @author zhanghui
	 */
	public ArrayList<String[]> getYhxxList(YhglNewForm myForm,User user) 
		throws IllegalArgumentException, SecurityException, IllegalAccessException, 
		InvocationTargetException, NoSuchMethodException{
		
		DAO dao = DAO.getInstance();
		
		// ====================过滤条件===================================
		MakeQuery makeQuery = new MakeQuery();
		String[] col=null;
		
		if ("12898".equals(Base.xxdm)) {
			if("wfp".equals(myForm.getFpzt())){
				 col = new String[]{"qx","sffz","sffdy","sfbzr","sfbl"};
				}else{
				 col = new String[]{"zdm","qx","sffz","sffdy","sfbzr","sfbl"};
			}
		}else {
			if("wfp".equals(myForm.getFpzt())){
				 col = new String[]{"qx","sffz","sffdy","sfbzr"};
				}else{
				 col = new String[]{"zdm","qx","sffz","sffdy","sfbzr"};
			}
		}
		
		
		String[] colLike = new String[]{"yhm","xm"};
		
		makeQuery.makeQuery(col, colLike, myForm);		
		
		String[] inputV = makeQuery.getInputList();		
		String searchTj = makeQuery.getQueryString();			
		
		String tj = " ";
		
		if(myForm.getSzbm() != null && !"".equalsIgnoreCase(myForm.getSzbm())){
			tj = dao.getOneRs("select case when bmjb = '2' then ' and szbm = '||chr(39)||bmdm||chr(39) " +
					"else 'and (szbm = '||chr(39)||bmdm||chr(39)||' or " +
					"szbm in (select bmdm from zxbz_xxbmdm where bmfdm ='||chr(39)||bmdm||chr(39)||') )' end tj " +
					"from zxbz_xxbmdm where bmdm = ?",new String[]{myForm.getSzbm()}, "tj");
		}
			
		
		// ====================过滤条件 end===============================
		
		// ====================SQL拼装===================================		
		StringBuffer sql = new StringBuffer();
		String yhTable = " view_yhz_yhxxb ";
		if(myForm.getZdm() == null || "".equals(myForm.getZdm()) || "wfp".equals(myForm.getFpzt())){
			yhTable = " (select distinct yhm,kl,yhbzdms zdm,yhbzdms,xm,szbm,dwdm,xssx,qx,zmc from view_yhz_yhxxb) ";
		}
		
		sql.append("select rownum r,a.* from(");
		sql.append(" select a.*,case when qx =1 then '启用' else '停用' end qyzt, ");
		sql.append(" case when a.zdm is null then '否' else '是' end sffz, c.bmmc,");
		if ("12898".equals(Base.xxdm)) {
			sql.append(" decode(f.sfbl, 0, '是',1, '否',null,'是') sfbl,");
		}
		sql.append(" decode(d.num,null,'否','是') sffdy,decode(e.num,null,'否','是') sfbzr from "+yhTable+" a  ");
		//sql.append(" left join yhzb b on a.zdm = b.zdm  ");
		sql.append(" left join zxbz_xxbmdm c on a.szbm = c.bmdm ");
		sql.append(" left join (select c.zgh, count(1) num from fdybjb c where bjdm in (select bjdm from xsxxb where (sfzx = '在校' or sfzx is null)) group by c.zgh) d");
		sql.append(" on a.yhm=d.zgh	");
		sql.append(" left join (select d.zgh, count(1) num from bzrbbb d where bjdm in (select bjdm from xsxxb where (sfzx = '在校' or sfzx is null)) group by d.zgh) e");
		sql.append(" on a.yhm=e.zgh");
		if ("12898".equals(Base.xxdm)) {
			sql.append(" left join fdyxxb f on a.yhm=f.zgh ");
		}
		sql.append(" order by qx desc,a.zdm,szbm,a.xm ) a ");
		sql.append(searchTj+tj);
		if("wfp".equals(myForm.getFpzt())){
			sql.append(" and( ");
			sql.append(" yhbzdms not like '%'||'"+myForm.getZdm()+"'||'%' or yhbzdms is null)");
		}
//		sql.append(getUserSql(user));
		String[] colList = new String[] { };
		sql.append(" order by r ");
		// ====================SQL拼装 end================================
		if ("12898".equals(Base.xxdm)) {
			colList = new String[] { "yhm", "yhm", "xm", "zmc", "bmmc", "sffz", "qyzt" ,"sffdy", "sfbzr","sfbl"};
		}else {
			colList = new String[] { "yhm", "yhm", "xm", "zmc", "bmmc", "sffz", "qyzt" ,"sffdy", "sfbzr"};
		}

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonQuery(sql.toString(),"", inputV, colList, myForm);
		return list;

	}
	
	/**
	 * 验证用户名是否存在
	 * 
	 * @date 2013-01-08
	 * @author zhanghui
	 */
	public Boolean checkYhm(String yhm){
		DAO dao = DAO.getInstance();
		boolean flag = true;
		String count = "0";
		
		count = dao.getOneRs("select count(1) num from yhb where yhm = ? ", new String[]{yhm}, "num");
		
		if(!"0".equalsIgnoreCase(count)){
			flag = false;
		}
		
		return flag;
	}
	
	/**
	 * 保存用户权限
	 * 
	 * @date 2013-01-08
	 * @author zhanghui
	 */
	public Boolean saveYhqx(String yhm){

		DAO dao = DAO.getInstance();
		boolean flag = true;
		
		try {
			//判断用户是否已有权限
			String num = dao.getOneRs("select count(1) num from yhqxb where yhm = ? ", new String[]{yhm}, "num");
			//若权限已存在，先删除，后增加
			if(null !=num && !"0".equalsIgnoreCase(num)){
				flag = dao.runUpdate("delete from yhqxb where yhm = ? ", new String[]{yhm});
			}
			flag = dao.runUpdate("insert into yhqxb select b.yhm,a.gnmkdm,a.dxq from " +
					"yhzqxb a,yhb b where a.zdm = b.zdm and b.yhm =? ", new String[]{yhm});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 删除用户
	 * 
	 * @date 2013-01-09
	 * @author zhanghui
	 */	
	public Boolean deleteYhxx(YhglNewForm myForm){	
		boolean flag = true;
		
		String[] primarykey_checkVal = myForm.getPrimarykey_checkVal();
		List<String[]> params = new ArrayList<String[]>();
		for(int i=0;i<primarykey_checkVal.length;i++){
			params.add(new String[]{primarykey_checkVal[i]});
		}
		
		try {
			flag = saveArrDate("delete from yhb where yhm = ?", params, "");
			if(flag){
				flag = saveArrDate("delete from yhqxb where yhm = ?", params, "");
			}
		} catch (Exception e) {			
			flag = false;
			System.out.println("<------- 用户删除失败 ------>");
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 获取用户信息
	 */
	public HashMap<String,String> getYhxx(String yhm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.yhm,a.xm,a.zdm,a.dwdm,a.szbm,a.qx,a.qxzt,a.dwmc,b.zmc,a.bmmc ");
		sql.append(" from view_yhxx a ");
		sql.append(" left join (select distinct yhm,zmc from view_yhz_yhxxb) b on a.yhm=b.yhm ");
		sql.append(" where a.yhm=? ");
		return DAO.getInstance().getMap(sql.toString(), new String[]{yhm}, new String[]{"yhm","xm","zdm","dwdm","szbm","qx","qxzt","dwmc","zmc","bmmc"});
	}
	
	/**
	 * 获得某用户指定等级的功能模块
	 * @param lv:分 1， 2， 3
	 * @param userName
	 * @return
	 */
	public List<HashMap<String, String>> getGnmkList(String userName,String yhm,String zdm, int lv) {
		StringBuilder sql = new StringBuilder();
		String jbxz = "";
		String length = "3";
		
		// 获取功能菜单级别
		switch (lv) {
		case 1: jbxz="and length(a.gnmkdm)=3 "; length = "3"; break;
		case 2: jbxz="and length(a.gnmkdm)=5 "; length = "5"; break;
		case 3: jbxz="and length(a.gnmkdm)=7 "; length = "7"; break;
		default: jbxz=""; break;
		}
		
		sql.append("select rownum r,a.gnmkdm dm,a.gnmkmc mc,case when b.gnmkdm is null then '' else 'checked' end checked,b.dxq from gnmkdmb a " );
		sql.append("left join (select * from yhqxb where yhm =?) b on a.gnmkdm = b.gnmkdm where a.sfqy='是' ");
		
		//用户仅可分配自己有的权限
//		sql.append("and a.gnmkdm in (select gnmkdm from yhqxb where yhm = ?)");
		
		//非学生组用户加载非学生或为空的功能模块
		sql.append(" and (a.mklx != 'stu' or a.mklx is null) ");
		
		sql.append(jbxz);
		sql.append(" order by to_number(a.xssx),a.gnmkdm");

		String[] inputs = new String[]{yhm};
		String[] colList = new String[]{"dm", "mc","dxq","checked" };
		
		return DAO.getInstance().getList(sql.toString(), inputs, colList);
	}

	/**
	 * 获得多级的功能模块
	 */
	public List<HashMap<String, String>> getDjGnmkList(String yhm) {
		StringBuilder sql = new StringBuilder();

		sql.append("select a.gnmkdm id,a.fjgndm pId,a.gnmkmc name,case when b.gnmkdm is null then 'false' else 'true' end checked,b.dxq from GNMKDMB_DJ a " );
		sql.append("left join (select * from yhqxb where yhm = ?) b on a.gnmkdm = b.gnmkdm where a.sfqy='1' ");

		//非学生组用户加载非学生或为空的功能模块
		sql.append(" and (a.mklx != 'stu' or a.mklx is null) ");
		sql.append(" order by to_number(a.xssx),a.gnmkdm");

		String[] inputs = new String[]{yhm};
		String[] colList = new String[]{"id", "pId","name","checked","dxq" };

		return DAO.getInstance().getList(sql.toString(), inputs, colList);
	}
	
	/**
	 * 获得用户功能模块代码
	 * 
	 * @date 2013-01-11
	 * @author zhanghui
	 */
	public String[] getYhqx(String yhm) throws SQLException{
		DAO dao = DAO.getInstance();	
		String[] yhgnmk = new String[]{};
		yhgnmk = dao.getArray("select * from yhqxb where yhm = ? ", new String[]{yhm}, "gnmkdm");
		return yhgnmk;
	}
	
	/**
	 * 初始化用户密码
	 * 
	 * @date 2013-01-09
	 * @author zhanghui
	 */	
	public Boolean cshYhmm(YhglNewForm myForm){	

		Encrypt ept = new Encrypt();
		
		boolean flag = true;
		String kl = myForm.getKl();
		String[] primarykey_checkVal = myForm.getPrimarykey_checkVal();
		List<String[]> params = new ArrayList<String[]>();
		for(int i=0;i<primarykey_checkVal.length;i++){
			params.add(new String[]{primarykey_checkVal[i]});
		}
		
		try {
			flag = saveArrDate("update yhb set kl= '"+ept.encrypt(kl)+"' where yhm = ?", params, "");
		} catch (Exception e) {			
			flag = false;
			System.out.println("<------- 用户密码初始化失败 ------>");
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 批量修改用户信息
	 * 
	 * @date 2013-01-18
	 * @author zhanghui
	 */	
	public Boolean plUpdateYhxx(YhglNewForm myForm){	

		boolean flag = true;
		String zdm = myForm.getZdm();
		String qx  = myForm.getQx();
		
		//sql拼接
		StringBuffer sql = new StringBuffer();
		StringBuffer query = new StringBuffer();
		
		StringBuffer qxsql_del = new StringBuffer();
		StringBuffer qxsql_ins = new StringBuffer();
		
		sql.append("update yhb set ");
		//用户停用的时候，需要置zdm为空。
		//if(zdm != null && !"".equalsIgnoreCase(zdm)){
			query.append("zdm = zdm||',");
			query.append(zdm);
			query.append("',");
			qxsql_del.append("delete yhqxb a where a.yhm = ? and exists (select 1 from yhzqxb b where b.zdm = ? and a.gnmkdm=b.gnmkdm)  ");
			qxsql_ins.append("insert into yhqxb select ? yhm,a.gnmkdm,a.dxq from yhzqxb a where zdm = ? ");
		//}
		if(qx != null && !"".equalsIgnoreCase(qx)){
			query.append("qx = '");
			query.append(qx);
			query.append("',");
		}
		sql.append(query.substring(0, query.length()-1));
		sql.append(" where yhm = ? ");
		
		//
		
		
		String[] primarykey_checkVal = myForm.getPrimarykey_checkVal();
		List<String[]> params = new ArrayList<String[]>();
		List<String[]> paramsDelIns = new ArrayList<String[]>();
		for(int i=0;i<primarykey_checkVal.length;i++){
			params.add(new String[]{primarykey_checkVal[i]});
			paramsDelIns.add(new String[]{primarykey_checkVal[i], zdm});
		}
		
		try {
			flag = saveArrDate(sql.toString(), params, "");
			if(flag && (zdm != null && !"".equalsIgnoreCase(zdm))){
				flag = saveArrDate(qxsql_del.toString(),paramsDelIns,"");
				if(flag){
					flag = saveArrDate(qxsql_ins.toString(),paramsDelIns,"");
				}
			}
		} catch (Exception e) {			
			flag = false;
			System.out.println("<------- 用户信息批量修改失败 ------>");
			e.printStackTrace();
		}
		
		return flag;
	}
	/**
	 * 批量取消分配
	 */
	public Boolean plQxYhxx(YhglNewForm myForm){
		
		boolean flag = true;
		String zdm = myForm.getZdm();
		String qx  = myForm.getQx();
		
		//sql拼接
		StringBuffer sql = new StringBuffer();
		StringBuffer query = new StringBuffer();
		
		StringBuffer qxsql_del = new StringBuffer();
		
		sql.append("update yhb set ");
		//用户停用的时候，需要置zdm为空。
		//if(zdm != null && !"".equalsIgnoreCase(zdm)){
		query.append("zdm = replace(zdm,'"+zdm+"',''), ");
		qxsql_del.append("delete yhqxb a where a.yhm = ? and exists (select 1 from yhzqxb b where b.zdm = ? and a.gnmkdm=b.gnmkdm)  ");
		//}
		if(qx != null && !"".equalsIgnoreCase(qx)){
			query.append("qx = '");
			query.append(qx);
			query.append("',");
		}
		sql.append(query.substring(0, query.length()-1));
		sql.append(" where yhm = ? ");
		
		//
		
		
		String[] primarykey_checkVal = myForm.getPrimarykey_checkVal();
		List<String[]> params = new ArrayList<String[]>();
		List<String[]> paramsDelIns = new ArrayList<String[]>();
		for(int i=0;i<primarykey_checkVal.length;i++){
			params.add(new String[]{primarykey_checkVal[i]});
			paramsDelIns.add(new String[]{primarykey_checkVal[i], zdm});
		}
		
		try {
			flag = saveArrDate(sql.toString(), params, "");
			if(flag && (zdm != null && !"".equalsIgnoreCase(zdm))){
				flag = saveArrDate(qxsql_del.toString(),paramsDelIns,"");
			}
		} catch (Exception e) {			
			flag = false;
			System.out.println("<------- 用户信息批量取消分配失败 ------>");
			e.printStackTrace();
		}
		
		return flag;
	}
	/**
	 * 用户分组
	 */
	public Boolean yhfz(YhglNewForm myForm){	

		boolean flag = true;
		String zdm = myForm.getZdm();
		String[] zdmArr = zdm.split(","); 
		String qx  = myForm.getQx();
		
		//sql拼接
		StringBuffer sql = new StringBuffer();
		StringBuffer query = new StringBuffer();
		
		List<String> qxsql_del_ins = new ArrayList<String>();
		
		sql.append("update yhb set ");
		//用户停用的时候，需要置zdm为空。
		//if(zdm != null && !"".equalsIgnoreCase(zdm)){
			query.append("zdm = '");
			query.append(zdm);
			query.append("',");
		//}
		if(qx != null && !"".equalsIgnoreCase(qx)){
			query.append("qx = '");
			query.append(qx);
			query.append("',");
		}
		sql.append(query.substring(0, query.length()-1));
		sql.append(" where yhm = ? ");
		
		//
		
		String[] primarykey_checkVal = myForm.getPrimarykey_checkVal();
		List<String[]> params = new ArrayList<String[]>();
		for(int i=0;i<primarykey_checkVal.length;i++){
			params.add(new String[]{primarykey_checkVal[i]});
			qxsql_del_ins.add("delete yhqxb a where a.yhm = '"+primarykey_checkVal[i]+"' and exists (select 1 from yhzqxb b where b.zdm in (select zdm from view_yhz_yhxxb where yhm = '"+primarykey_checkVal[i]+"') and a.gnmkdm=b.gnmkdm)  ");
			for (int j = 0; j < zdmArr.length; j++) {
				qxsql_del_ins.add("delete yhqxb a where a.yhm = '"+primarykey_checkVal[i]+"' and exists (select 1 from yhzqxb b where b.zdm = '"+zdmArr[j]+"' and a.gnmkdm=b.gnmkdm)  ");
				qxsql_del_ins.add("insert into yhqxb select '"+primarykey_checkVal[i]+"' yhm,a.gnmkdm,a.dxq from yhzqxb a where zdm = '"+zdmArr[j]+"' ");
			}
		}
		
		try {
			flag = saveArrDate(qxsql_del_ins.toArray(new String[]{}));
			if(flag && (zdm != null && !"".equalsIgnoreCase(zdm))){
				flag = saveArrDate(sql.toString(), params, "");
			}
		} catch (Exception e) {			
			flag = false;
			System.out.println("<------- 用户分组失败 ------>");
			e.printStackTrace();
		}
		
		return flag;
	}
	/**
	 * 用户停用
	 */
	public Boolean yhty(YhglNewForm myForm){	
		
		boolean flag = true;
		//sql拼接
		StringBuffer sql = new StringBuffer();
		
		List<String> qxsql_del = new ArrayList<String>();
		
		sql.append("update yhb set zdm = null,qx = '0' where yhm = ?");
		//
		
		String[] primarykey_checkVal = myForm.getPrimarykey_checkVal();
		List<String[]> params = new ArrayList<String[]>();
		for(int i=0;i<primarykey_checkVal.length;i++){
			params.add(new String[]{primarykey_checkVal[i]});
			qxsql_del.add("delete yhqxb a where a.yhm = '"+primarykey_checkVal[i]+"'  "); //清空所有权限
		}
		
		try {
			flag = saveArrDate(qxsql_del.toArray(new String[]{}));
			if(flag){
				flag = saveArrDate(sql.toString(), params, "");
			}
		} catch (Exception e) {			
			flag = false;
			System.out.println("<------- 用户停用失败 ------>");
			e.printStackTrace();
		}
		
		return flag;
	}
	/**
	 * 是否思政可见（辽宁机电个性化）
	 */
	public int[] szkj(String[] zghs, String sfbl) throws Exception {
		 DAO dao = DAO.getInstance();
		StringBuilder sb = new StringBuilder();
		String[] arr = new String[zghs.length];
		for (int i = 0; i < zghs.length; i++) {
			sb = new StringBuilder();
			sb.append("update fdyxxb set" );
			sb.append(" sfbl= '"+sfbl+"',");
			String sql = sb.toString();
			sql = sql.substring(0, sql.length()-1);
			String endsql= sql+ " where zgh='"+zghs[i]+"'";
			arr[i] = endsql;
		}
		return dao.runBatch(arr);
	}
}
