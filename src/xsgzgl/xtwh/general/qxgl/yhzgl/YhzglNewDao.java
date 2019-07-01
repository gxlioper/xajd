package xsgzgl.xtwh.general.qxgl.yhzgl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.util.UniqID;

import xgxt.DAO.DAO;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xsgzgl.comm.BasicDAO;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系统维护_权限管理_用户组管理_dao类
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
public class YhzglNewDao extends BasicDAO{
	
	/**
	 * 获得用户组信息结果集
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */
	public ArrayList<String[]> getYhzxxList(YhzglNewForm myForm,User user) 
		throws IllegalArgumentException, SecurityException, IllegalAccessException, 
		InvocationTargetException, NoSuchMethodException{
		
		// ====================过滤条件===================================
		MakeQuery makeQuery = new MakeQuery();
		String[] col = new String[]{};
		String[] colLike = new String[]{"zmc"};
		
		makeQuery.makeQuery(col, colLike, myForm);		
		
		String[] inputV = makeQuery.getInputList();		
		String searchTj = makeQuery.getQueryString();			
		// ====================过滤条件 end================================
		
		// ====================SQL拼装===================================
		StringBuffer sql = new StringBuffer();
		
		sql.append("select rownum r,a.* from(");
		//修改此处sql时请注意用户组查询页面删除功能是否正常，删除判断获取用户个数通过标签获取
		sql.append(" select a.*,case  when a.zdm = '6727' then  '<a href=\"javascript:xsxxts();\" ");
		sql.append(" style=color:blue><B>' || nvl(b.yhs, 0) || '</B></a>'  else ");
		sql.append("'<a href=\"javascript:viewYhxx('||chr(39)||a.zdm||chr(39)||')\" ");
		sql.append(" style=color:blue><B>'||nvl(b.yhs,0)||'</B></a>' end yhsxs,nvl(b.yhs,0) yhs from yhzb a left join (");
		sql.append(" select zdm,count(*) yhs from view_yhz_yhxxb group by zdm union select '6727' zdm,count(1) num from view_xsbfxx ");
		sql.append(" where (sfzx = '在校' or sfzx is null)  ");
		sql.append(" ) b on a.zdm = b.zdm order by to_number(yhs) desc ,zmc) a ");
		sql.append(searchTj);
//		sql.append(getUserSql(user));
		sql.append(" order by r ");
		// ====================SQL拼装 end================================

		String[] colList = new String[] { "zdm", "zmc", "yhsxs"};

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonQuery(sql.toString(),"", inputV, colList, myForm);
		return list;

	}
	
	/**
	 * 验证用户组名称是否存在
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */
	public Boolean checkZmc(String zmc){
		DAO dao = DAO.getInstance();
		boolean flag = true;
		String count = "0";
		
		count = dao.getOneRs("select count(1) num from yhzb where zmc = ? ", new String[]{zmc}, "num");
		
		if(!"0".equalsIgnoreCase(count)){
			flag = false;
		}
		
		return flag;
	}
	
	/**
	 * 增加用户组
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */	
	public Boolean addYhzxx(YhzglNewForm myForm){
		DAO dao = DAO.getInstance();		
		boolean flag = true;
		
		String zmc = myForm.getZmc();
		String guid = UniqID.getInstance().getUniqIDHash();
		try {
			flag = dao.runUpdate("insert into yhzb(zdm,zmc) values(?,?)", new String[]{guid,zmc.trim()});
		} catch (Exception e) {			
			flag = false;
			System.out.println("<------- 用户组增加失败 ------>");
			e.printStackTrace();
		}
		
		return flag;
	}
	/**
	 * 复制用户组
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */	
	public Boolean copyYhzxx(YhzglNewForm myForm){
		DAO dao = DAO.getInstance();		
		boolean flag = true;
		String guid = UniqID.getInstance().getUniqIDHash();
		String zmc = myForm.getZmc();
		//String seq = "0";
		//seq = dao.getOneRs(" select lpad(to_char(lsh_yhz.nextval),8,'0')seq from dual ", new String[]{}, "seq");
		
		String[] sql =new String[2];
		
		try {
			sql[0] = "insert into yhzb(zdm,zmc) values('"+guid+"','"+zmc.trim()+"')";
			sql[1] = "insert into yhzqxb select '"+guid+"' zdm,b.gnmkdm,b.dxq from yhzqxb b where b.zdm ='"+myForm.getZdm()+"'";
			flag = dao.saveArrDate(sql);
		} catch (Exception e) {			
			flag = false;
			System.out.println("<------- 用户组复制失败 ------>");
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 修改用户组
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */	
	public Boolean updateYhzxx(YhzglNewForm myForm){
		DAO dao = DAO.getInstance();		
		boolean flag = true;
		
		String zmc = myForm.getZmc();
		String zdm = myForm.getZdm();
		
		try {
			flag = dao.runUpdate("update yhzb set zmc = ? where zdm = ?", new String[]{zmc.trim(),zdm});
		} catch (Exception e) {			
			flag = false;
			System.out.println("<------- 用户组修改失败 ------>");
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 判断选中的用户组是否含用户
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */
	public Boolean checkYhz(String[] yhz){
		DAO dao = DAO.getInstance();
		boolean flag = true;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(count(1)) num from yhb where zdm in ( "); 
		for(int i = 0;i<yhz.length;i++){
			sql.append("'"+yhz[i]+"',");
		}
		String sqls  = sql.substring(0, sql.length()-1)+" ) group by zdm";
		String num =dao.getOneRs(sqls, new String[]{}, "num");
		
		if(num != null && !"0".equalsIgnoreCase(num)){
			flag = false;
		}
		
		return flag;
	} 
	
	/**
	 * 删除用户组
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */	
	public Boolean deleteYhzxx(YhzglNewForm myForm){	
		boolean flag = true;
		
		String[] primarykey_checkVal = myForm.getPrimarykey_checkVal();
		List<String[]> params = new ArrayList<String[]>();
		for(int i=0;i<primarykey_checkVal.length;i++){
			params.add(new String[]{primarykey_checkVal[i]});
		}
		 
		try {
			flag = saveArrDate("delete from yhzb where zdm = ?", params, "");
			if(flag){
				flag = saveArrDate("delete from yhzqxb where zdm = ?", params, "");
			}
		} catch (Exception e) {			
			flag = false;
			System.out.println("<------- 用户组修改失败 ------>");
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 获得某用户指定等级的功能模块
	 * @param lv:分 1， 2， 3
	 * @param userName
	 * @return
	 */
	public List<HashMap<String, String>> getGnmkList(String zdm, int lv) {
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
		sql.append("left join (select * from yhzqxb where zdm =?) b on a.gnmkdm = b.gnmkdm where a.sfqy='是'  ");
		
		if("6727".equalsIgnoreCase(zdm) ){
			if(!"7".equalsIgnoreCase(length))
				sql.append(" and substr(a.gnmkdm,0,3) in (select substr(gnmkdm,0,3) from gnmkdmb where mklx = 'all' or mklx = 'stu') ");
			if("7".equalsIgnoreCase(length))
				sql.append(" and (a.mklx = 'all' or a.mklx = 'stu') ");
		}else{
			sql.append(" and (a.mklx != 'stu' or a.mklx is null) ");
		}
		
		sql.append(jbxz);
		sql.append(" order by to_number(a.xssx),a.gnmkdm");

		String[] inputs = new String[]{zdm};
		String[] colList = new String[]{"dm", "mc","dxq","checked" };
		
		return DAO.getInstance().getList(sql.toString(), inputs, colList);
	}

	/**
	 * 获得多级的功能模块
	 */
	public List<HashMap<String, String>> getDjGnmkList(String zdm) {
		StringBuilder sql = new StringBuilder();

		sql.append("select a.gnmkdm id,a.fjgndm pId,a.gnmkmc name,case when b.gnmkdm is null then 'false' else 'true' end checked,b.dxq from gnmkdmb_dj a " );
		sql.append("left join (select * from yhzqxb where zdm =?) b on a.gnmkdm = b.gnmkdm where a.sfqy='1'  ");

		if("6727".equalsIgnoreCase(zdm) ){
				sql.append(" and (a.mklx = 'all' or a.mklx = 'stu') ");
		}else{
			sql.append(" and (a.mklx != 'stu' or a.mklx is null) ");
		}

		sql.append(" order by to_number(a.xssx),a.gnmkdm");

		String[] inputs = new String[]{zdm};
		String[] colList = new String[]{"id", "pId","name","checked","dxq" };

		return DAO.getInstance().getList(sql.toString(), inputs, colList);
	}
	
	/**
	 * 获得功能按钮权限
	 * @param userName
	 * @return
	 */
	public List<HashMap<String, String>> getBtnList(String userName){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.gnmkdm,a.btndm,a.btnmc, decode(b.cdgndm, '', '', 'yes') jsyy,decode(c.cdid, '', '', 'yes') yhyy ")
			.append("from (select gnmkdm, 'view' btndm, '查询' btnmc from gnmkdmb where length(gnmkdm)=7 ")
			.append("union select a.gnmkdm,a.btndm,a.btnmc from xg_view_xtwh_gnmkbtn a) a ")
			.append("left join (select distinct gnmkdm,cdgndm from xg_xtwh_jscdqxb a ")
			.append("where exists (select 1 from xg_xtwh_yhjsb b where yhm = ? and b.jsdm = a.jsdm)) b ")
			.append("on a.gnmkdm = b.gnmkdm and a.btndm=b.cdgndm left join ")
			.append("(select gnmkdm,cdid from xg_xtwh_yhcdqxb a where a.yhm = ?) c on a.gnmkdm=c.gnmkdm and a.btndm = c.cdid");
		
		String[] inputs = new String[]{userName, userName};
		String[] colList = new String[]{"gnmkdm", "btndm", "btnmc", "jsyy", "yhyy"};
		
		List<HashMap<String, String>> list = DAO.getInstance().getList(sql.toString(), inputs, colList);
		return list;
	}
	
	/**
	 * 获得用户组信息
	 * 
	 * @date 2013-01-11
	 * @author zhanghui
	 */
	public HashMap<String,String> getYhzxx(String zdm){
		DAO dao = DAO.getInstance();	
		StringBuilder sql = new StringBuilder("select a.*,nvl(b.num,'0') num from yhzb a left join ");
		if("6727".equals(zdm)){
			sql.append("(select '6727' zdm,count(1) num from view_xsbfxx where (sfzx = '在校' or sfzx is null)) b ");
		}else {
			sql.append("(select zdm,count(1) num from view_yhz_yhxxb group by zdm) b ");
		}
		sql.append("on a.zdm =b.zdm where a.zdm = ?");
		HashMap<String,String> map = dao.getMapNotOut(sql.toString(), new String[]{zdm});
		return map;
	}
	
	/**
	 * 获得用户组功能模块代码
	 * 
	 * @date 2013-01-11
	 * @author zhanghui
	 */
	public String[] getYhzqx(String zdm) throws SQLException{
		DAO dao = DAO.getInstance();	
		String[] yhzgnmk = new String[]{};
		yhzgnmk = dao.getArray("select * from yhzqxb where zdm = ? order by gnmkdm ", new String[]{zdm}, "gnmkdm");
		return yhzgnmk;
	}
	
	public String[] getYhzmc(String zdm) throws Exception{
		DAO dao = DAO.getInstance();	
		String[] zmc = new String[]{};
		zmc = dao.getArray("select * from YHZB where zdm = ?  ", new String[]{zdm}, "zmc");
		return zmc;
	}

}
