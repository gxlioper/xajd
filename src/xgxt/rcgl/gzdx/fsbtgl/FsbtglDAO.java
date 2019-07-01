package xgxt.rcgl.gzdx.fsbtgl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.xsxx.dagl.guizdx.XsxxGuizdxDaglForm;

public class FsbtglDAO {
	
	/**
	 * 获取副食补贴代码维护
	 * @return
	 */
	public List<HashMap<String, String>> getFsbtList(){
		String sql = "select dm,mc,btje from xg_rcsw_fsbtdmb";

		return DAO.getInstance().getListNotOut(sql, new String[]{});
	}
	
	/**
	 * 获取副食补贴信息
	 * @param model
	 * @param userStatus
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public List<String[]> getCxList(FsbtglForm model, User user, String[] outPutList) throws Exception {
		String condition = "";
		
		MakeQuery makeQuery = new MakeQuery();
		
		String[] colList = new String[]{"nd", "yf", "btdm", "xydm", "zydm", "bjdm"};
		String[] colLikeList = new String[]{"xh", "xm"};
		makeQuery.makeQuery(colList, colLikeList, model);
		
		condition = makeQuery.getQueryString();
		String[] inPutList = makeQuery.getInputList();

		condition += user.getQueryCondition();

		StringBuilder sql = new StringBuilder();

		sql.append("select rownum r,a.* from (select a.*,b.xm,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc from ")
			.append("(select a.nd||a.yf||a.xh||a.btdm pkValue,a.*,b.mc,b.btje from ")
		    .append("xg_rcsw_fsbtffb a,xg_rcsw_fsbtdmb b where a.btdm=b.dm) a ")
		    .append("left join view_xsbfxx b on a.xh=b.xh)a ")
			.append(condition)
			.append(getUserSql(user))
			.append("  order by a.nd,a.yf,a.btdm,bjdm ");
		
		return CommonQueryDAO.commonQuery(sql.toString(), "", inPutList, outPutList, model);
	}
	
	/**
	 * 批量删除
	 * @param pkValues
	 * @return
	 */
	public boolean batchDel(String[] pkValues){
		DAO dao = DAO.getInstance();
		boolean flag = false;
		String sql = "delete xg_rcsw_fsbtffb where nd||yf||xh||btdm=?";
		
		List<String[]> params = new ArrayList<String[]>();
		
		for(String pk : pkValues){
			params.add(new String[]{pk});
		}
		try {
			dao.runBatch(sql, params);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 保存副食补贴
	 * @param model
	 * @return
	 */
	public boolean saveFsbt(FsbtglForm model){
		StringBuilder sql = new StringBuilder();
		DAO dao = DAO.getInstance();
		boolean flag = false;
		
		sql.append("insert into xg_rcsw_fsbtffb(nd,yf,xh,btdm,jsr,ffsj) ")
			.append("values(?,?,?,?,?,?)");
		
		String[] inputs = new String[]{model.getNd(), model.getYf(), model.getXh(),
									   model.getBtdm(), model.getJsr(), model.getFfsj()};
		
		try {
			flag = dao.runUpdate(sql.toString(), inputs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public Map<String, String> getStuInfo(String xh){
		String sql = "select a.* from view_xsjbxx a where a.xh=?";
		String[] colList = new String[]{"xh", "xm", "xymc", "zymc", "bjmc" , "sjhm", "lxdh"};
		
		DAO dao = DAO.getInstance();
		return dao.getMap(sql, new String[]{xh}, colList);
	}
	
	/**
	 * 获取请假学生信息
	 * @param xh
	 * @return
	 */
	public Map<String, String> getFsbtInfo(String pkValue){
		StringBuilder sql = new StringBuilder();
		 
		sql.append("select a.*,b.xymc,b.zymc,b.xm,b.bjmc from ")
			.append("(select a.*,b.mc,b.btje from xg_rcsw_fsbtffb a,xg_rcsw_fsbtdmb b where a.btdm=b.dm) a ")
			.append("left join view_xsbfxx b on a.xh=b.xh where a.nd||a.yf||a.xh||a.btdm = ?");
		
		DAO dao = DAO.getInstance();

		return dao.getMapNotOut(sql.toString(), new String[]{pkValue});
	}
	
	public String getUserSql(User user){
		
		String fdyQx=user.getFdyQx();
		String bzrQx=user.getBzrQx();
		String userName=user.getUserName();
		String userDep=user.getUserDep();
		String userType=user.getUserType();
		StringBuilder userQuery=new StringBuilder();
		if("true".equalsIgnoreCase(fdyQx) && "true".equalsIgnoreCase(bzrQx)){
			userQuery.append(" and exists(select 1 from fdybjb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ");
			userQuery.append("  union select 1 from bzrbbb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ");
			userQuery.append(" ) ");
		}else if("true".equalsIgnoreCase(fdyQx)){
			userQuery.append(" and exists(select 1 from fdybjb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ) ");
		}else if("true".equalsIgnoreCase(bzrQx)){
			userQuery.append(" and exists(select 1 from bzrbbb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ) ");
		}else if("xy".equalsIgnoreCase(userType)){
			userQuery.append(" and xydm='"+userDep+"' ");
		}
		return userQuery.toString();
	}
}
