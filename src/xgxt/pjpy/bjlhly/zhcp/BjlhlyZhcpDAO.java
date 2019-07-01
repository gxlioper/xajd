package xgxt.pjpy.bjlhly.zhcp;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;

public class BjlhlyZhcpDAO extends CommDAO {

	DAO dao = DAO.getInstance();

	/**
	 * 查询综测结果信息列表
	 * 
	 * @param model
	 * @param colList
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public List<String[]> getZcjgList(BjlhlyZhcpForm model, String[] colList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		User user = model.getUser();
		SearchService searchService = new SearchService();
		HashMap<String, String> notCtrlStatus = new HashMap<String, String>();
		notCtrlStatus.put("gygly", "yes");
		user.setNotCtrlStatus(notCtrlStatus);
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = searchService.getSearchTjByUser("a", user);
		searchTj += searchTjByUser;
		String[] inputSearch = SearchService.getTjInput(model.getSearchModel());

		StringBuilder sql = new StringBuilder();

		sql.append(" select rownum r,a.* from( select a.*,nj njmc from  ");
		sql.append(" xg_view_pjpy_bjlhly_zsbxscj a order by xydm,zydm,bjdm,bjpm)a where 1=1  ");

		System.out.println(sql);
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj,
				inputSearch, colList, model);
	}

	/**
	 * 第三上学期学生成绩同步
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean dsscjTb() throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_pjpy_bjlhly_zsbxscjb a ");
		sql.append(" set dsscj = (select dsscj ");
		sql.append(" from xg_pjpy_bjlhly_zsbxscjtbb b where  a.xh = b.xh) ");

		return dao.runUpdate(sql.toString(), new String[] {});
	}

	/**
	 * 执行综合总分计算存储过程
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean zhfjs(BjlhlyZhcpForm model) throws Exception {
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String nj = model.getNj();
		return dao.runProcuder("{call pro_xg_zhcp_bjlhly_zkszcf (?,?,?,?)}",
				new String[] { xydm, zydm, bjdm, nj });
	}

	/**
	 * 获取学年、学期分组信息
	 * 
	 * @return
	 */
	public List<HashMap<String,String>>getGroupList(BjlhlyZhcpForm model){
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select xn,xq,xqmc from ( ");
		sql.append(" select xn, xq,(select xqmc from xqdzb " );
		sql.append(" where xqdm=xq)xqmc, kcmc, cj from cjb where xh = ? ");
		sql.append(" )group by xn,xq,xqmc");
		
		return dao.getList(sql.toString(), new String[]{model.getXh()}, new String[]{"xn","xq","xqmc"});
	}
	
	/**
	 * 获取学生成绩信息
	 * 
	 * @return
	 */
	public List<HashMap<String,String>>getCjList(BjlhlyZhcpForm model){
		
		StringBuilder sql=new StringBuilder();
	
		sql.append(" select xn, xq,(select xqmc from xqdzb " );
		sql.append(" where xqdm=xq)xqmc, kcmc, cj from cjb where xh = ? ");
	
		
		return dao.getList(sql.toString(), new String[]{model.getXh()}, new String[]{"xn","xq","xqmc","kcmc","cj"});
	}
}
