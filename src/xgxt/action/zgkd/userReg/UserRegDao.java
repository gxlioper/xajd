/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 大学生生涯论坛注册DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-7-7 上午08:56:18</p>
 */
package xgxt.action.zgkd.userReg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.action.zgkd.SyltForm;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;

public class UserRegDao {
	/** 用户注册溪信息保存*/
    public boolean  regToSave(UserRegModel model) throws Exception{
    	DAO dao         = DAO.getInstance();
    	boolean done    = false;
    	String userName = model.getYhm();
    	String nc       = model.getNc();
    	String grqm     = model.getGrqm();
    	String sql      = " delete from sylt_yhb where dlm=? ";
    	done            = dao.runUpdate(sql, new String[]{userName});
    	if(done){
    		//用户级别
    		String yhjb = dao.getOneRs("select count(*) cout from sylt_bkglyppb where glym=? ", new String[]{userName},"cout");
    		yhjb        = Base.isNull(yhjb)?"0":yhjb;//板块匹配代码表中无该用户，则为普通用户,“0”级别
    		sql         = " insert into sylt_yhb(dlm,nc,grqm,yhjb)values(?,?,?,?)";
    		done        = dao.runUpdate(sql,new String[]{userName,nc,grqm,yhjb});
    	}    	
    	return done;
    }
    
    public List<String[]> getRegUserResult(SyltForm syltForm) throws Exception {
    	syltForm.getPages().setPageSize(5);
    	DAO dao                = DAO.getInstance();
    	List<String[]> resList = new ArrayList<String[]>();
    	String sql             = "select * from (select rownum r,dlm yhm,nc,grqm from sylt_yhb)";
    	String[] opList        = new String[]{"r","yhm", "nc", "grqm"};
    	String nc              = syltForm.getNc();
    	String yhm             = syltForm.getYhm();
    	StringBuffer whereSql  = new StringBuffer();
    	
    	whereSql.append(" where 1=1 ");
    	whereSql.append(Base.isNull(nc)?"":" and nc like '%"+nc.trim()+"%' ");
    	whereSql.append(Base.isNull(yhm)?"":" and yhm like '%"+yhm.trim()+"%' ");
    	String sqlstring = whereSql.toString();
    	syltForm.getPages().setMaxRecord(dao.rsToVator(sql + whereSql.toString(),new String[]{},opList).size());
    	whereSql.append(" and r > ");
    	whereSql.append(syltForm.getPages().getStart());
    	whereSql.append(" and r<= ");
    	whereSql.append(syltForm.getPages().getStart() + syltForm.getPages().getPageSize());
    	resList                = dao.rsToVator(sql + whereSql.toString(),new String[]{},opList);
    	
    	if((null!=yhm&&!"".equalsIgnoreCase(yhm))||(null!=nc&&!"".equalsIgnoreCase(nc))){
    		resList = dao.rsToVator(sql+sqlstring,new String[]{},opList);
    	}
    	return resList;
    }
    /**
     * 注册用户查询表头
     * @return
     * @throws Exception
     */
    public List<HashMap<String, String>> getregUserpTitle() throws Exception {
    	List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
    	String[] enList                       = new String[]{ "r", "yhm", "nc", "grqm"};
    	String[] cnList                       = new String[]{ "行号", "用户名", "昵称","个人签名"};
    	for (int i = 0; i < enList.length; i++ ) {
    		HashMap<String, String> tempList = new HashMap<String, String>();
    		tempList.put("en", enList[i]);
    		tempList.put("cn", cnList[i]);
    		topList.add(tempList);
    	}
    	return topList;
    }
    
    public int regUserDel(String[] keys, HttpServletRequest request) throws Exception {		
		int del = 0;
		for (int i = 0; i < keys.length; i++) {
			String whichxh = DealString.toGBK(keys[i]).trim();// 得到主键
			System.out.print("a"+whichxh+"b");
			boolean bFlag = StandardOperation.delete("sylt_yhb", "dlm", whichxh, request);
			if(bFlag){
			   del++;
			}
		}// end for
		return del;
	}
}
