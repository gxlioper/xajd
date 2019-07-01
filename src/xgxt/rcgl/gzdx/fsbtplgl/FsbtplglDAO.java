package xgxt.rcgl.gzdx.fsbtplgl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
/**
 * 贵州大学副食补贴批量管理
 * @author yeyipin
 */
public class FsbtplglDAO {
	DAO dao = DAO.getInstance();
	/**
	 * 获取副食补贴代码维护
	 * @return
	 */
	public List<HashMap<String, String>> getFsbtList(){
		String sql = "select dm,mc,btje from xg_rcsw_fsbtdmb";
		return dao.getListNotOut(sql, new String[]{});
	}
	
	/**
	 * 获取副食补贴批量发放信息
	 * @param model
	 * @param userStatus
	 * @return
	 * @throws Exception 
	 */
	public List<String[]> getCxList(FsbtplglForm model, HttpServletRequest request,User user, String[] outPutList) throws Exception {

		StringBuilder sql = new StringBuilder();

		sql.append(" select rownum r,a.* from (select a.* from ")
			.append("(select b.nd || b.yf || b.xh || b.btdm pkValue,a.xh,a.xm,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,b.nd,b.yf,")
			.append("case when b.nd is not null and b.yf is not null then b.nd||'.'||b.yf else '' end as btny,b.btdm,b.btmc,b.btje,b.jsr,b.ffsj from view_xsbfxx a ")
		    .append("left join (select a.nd,a.yf,a.xh,a.btdm,a.jsr,a.ffsj,b.mc btmc,b.btje from xg_rcsw_fsbtffb a,xg_rcsw_fsbtdmb b where a.btdm=b.dm and ")
		    .append("nd =? and yf = ? and btdm = ? ")
		    .append(")b on a.xh = b.xh)a ")
			.append(model.getCondition())
			.append(getUserSql(user))
			.append(" order by a.bjdm) a ");
		String[] inPutList = {model.getNd(),model.getYf(),model.getBtdm()};
		return CommonQueryDAO.commonQuery(sql.toString(), "", inPutList, outPutList, model);
	}
	
	/**
	 * 批量清空
	 * @param pkValues
	 * @return
	 */
	public boolean batchDel(String[] pkValues){
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
	 * @throws SQLException 
	 */
	public boolean saveFsbt(FsbtplglForm model,User user) throws SQLException{
		DAO dao = DAO.getInstance();
		String[] pk_xh = model.getPk_xh();
		String nd = model.getNd();
		String yf = model.getYf();
		String btdm = model.getBtdm();
		String jsr = model.getJsr();
		String ffsj = model.getFfsj();
		List<String[]> deleteList = new ArrayList<String[]>();
		List<String[]> list = new ArrayList<String[]>();
		for(int i = 0; i < pk_xh.length; i++){
			String[] deleteArr = new String[]{nd,yf,pk_xh[i],btdm};
			String[] arr = new String[]{nd,yf,pk_xh[i],btdm,jsr,ffsj};
			deleteList.add(deleteArr);
			list.add(arr);
		}
		String delSql = "delete from xg_rcsw_fsbtffb where nd = ? and yf = ? and xh = ? and btdm =? ";
		String sql = "insert into xg_rcsw_fsbtffb(nd,yf,xh,btdm,jsr,ffsj) values(?,?,?,?,?,?) ";
		int[] res = dao.runBatch(delSql, deleteList);
		if(dao.checkBatch(res)){
			return false;
		}
		res = dao.runBatch(sql, list);
		return !dao.checkBatch(res);
	}
	/**
	 * 获取副食补贴信息
	 * @param pkValue
	 * @return
	 */
	public Map<String, String> getFsbtInfo(String pkValue){
		StringBuilder sql = new StringBuilder();
		 
		sql.append("select a.*,b.xymc,b.zymc,b.xm,b.bjmc from ")
			.append("(select a.*,b.mc,b.btje from xg_rcsw_fsbtffb a,xg_rcsw_fsbtdmb b where a.btdm=b.dm) a ")
			.append("left join view_xsbfxx b on a.xh=b.xh where a.nd||a.yf||a.xh||a.btdm = ?");

		return dao.getMapNotOut(sql.toString(), new String[]{pkValue});
	}
	
	/**
	 * 获得用户权限SQL条件
	 * @param user
	 * @return
	 */
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
	/**
	 * 获取学生信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getXsxx(String xh, HttpServletRequest request){
		String sql="select a.* from view_xsjbxx a where xh = ? ";
		return dao.getMapNotOut(sql, new String[]{xh});
	}
	/**
	 * 获得默认补贴代码
	 * @return
	 */
	public String getMrbtdm() {
		return dao.getOneRs(" select dm from xg_rcsw_fsbtdmb where rownum = '1' order by dm ", new String[]{}, "dm");
	}
	/**
	 * 获得默认月份
	 * @return
	 */
	public String getMrYf() {
		return dao.getOneRs(" select to_number(to_char(sysdate,'MM')) nd from dual ", new String[]{}, "nd");
	}
	/**
	 * 根据条件获得学号
	 * @param tj
	 * @return
	 * @throws Exception
	 */
	public String[] getXhs(String tj) throws Exception {
		String sql = "select distinct xh from view_xsjbxx ";
		return dao.getRs(sql+tj, new String[]{}, "xh");
	}
	/**
	 * 获得补贴信息
	 * @param btdm
	 * @return
	 */
	public Map<String, String> getBtxx(String btdm) {
		return dao.getMap("select * from xg_rcsw_fsbtdmb where dm = ? ", new String[]{btdm}, new String[]{"mc","btje"});
	}
}
