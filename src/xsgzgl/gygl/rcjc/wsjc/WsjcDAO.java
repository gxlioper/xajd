package xsgzgl.gygl.rcjc.wsjc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: wujian
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-7-9 下午14:19:22
 * </p>
 */

public class WsjcDAO extends CommDAO {

	/**
	 * 卫生检查首页面信息查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getWsjcCx(WsjcForm myForm) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		// 用户对象
		User user = myForm.getUser();
		String[] colList = new String[] { "guid", "xymc", "ldmc", "bjmc", "qsh", "ldxb", "yhm", "wsdj", "dgldq", "jcsj" };
		// 用户属性
		String userType = user.getUserType();
		// ====================过滤条件===================================
		user.setUserStatus(userType);
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		String query = " where 1 = 1 ";
		query += searchTj;
		StringBuilder sql = new StringBuilder();
		sql.append(" select guid,qsh,wsdj,dgldq,jcsj,yhm,ldmc,ldxb,xymc,bjmc,nj,xydm,zydm,bjdm,lddm,jcry,xb,rownum r ");
		sql.append(" from(select e.lddm || '!@' || e.qsh || '!@' || e.jcsj guid, ");
		sql.append(" e.qsh,e.wsdj,e.dgldq,e.jcsj,e.yhm,e.ldmc,e.ldxb,e.ldxb xb, ");
		sql.append(" (select bmmc from zxbz_xxbmdm where f.xydm = bmdm) xymc, ");
		sql.append(" (select bjmc from bks_bjdm where f.bjdm = bjdm) bjmc, ");
		sql.append(" f.nj,f.xydm xydm, ");
		sql.append(" (select zydm from bks_bjdm where f.bjdm = bjdm) zydm, ");
		sql.append(" f.bjdm bjdm,e.lddm lddm,e.jcry ");
		sql.append(" from (select c.lddm, c.qsh, c.wsdj, c.dgldq, c.jcsj, c.jcry,c.yhm, d.ldmc, d.ldxb ");
		sql.append(" from (select a.lddm, a.qsh, a.wsdj, a.dgldq, a.jcsj,a.jcry, b.yhm ");
		sql.append(" from xg_gygl_jhzy_wsdgljcb a ");
		sql.append(" left join XG_GYGL_NEW_GYFDYB b on a.lddm = b.lddm) c ");
		sql.append(" left join XG_GYGL_NEW_LDXXB d on c.lddm = d.lddm) e ");
		sql.append(" left join (select distinct lddm, qsh, xydm, bjdm,nj from XG_GYGL_NEW_CWXXB) f on e.lddm = f.lddm and e.qsh =  f.qsh) ");
		sql.append(query);
		// ====================SQL拼装 end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonQuery(sql.toString(), "", inputV, colList, myForm);
		return list;
	}

	/**
	 * 级联查询-获取所有楼栋号
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getAllLd(String[] inputValue,String[] outputValue) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct lddm,ldmc from XG_GYGL_NEW_LDXXB");
		return dao.getList(sql.toString(), inputValue, outputValue);
	}

	/**
	 * 级联查询-由楼栋号获取寝室号
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getQsForLd(String[] inputValue,String[] outputValue) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("select qsh,qsh from XG_GYGL_NEW_QSXXB where lddm=?");
		return dao.getList(sql.toString(), inputValue, outputValue);
	}

	/**
	 * 级联查询-由楼栋号和寝室号获取其他信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getInfo(String[] inputValue,String[] outputValue) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct yhm sgxfdy, (select bmmc from zxbz_xxbmdm where c.xydm = bmdm) xy, (select bjmc from bks_bjdm where c.bjdm = bjdm) bj, qsxb xb from XG_GYGL_NEW_GYFDYB d ");
		sql.append("join (select a.lddm, a.qsh, a.xydm, a.bjdm, qsxb from XG_GYGL_NEW_QSXXB b ");
		sql.append("join (select distinct lddm, qsh, xydm, bjdm from XG_GYGL_NEW_CWXXB where lddm=? and qsh=?) a on a.lddm = b.lddm and a.qsh = a.qsh) c on c.lddm = d.lddm");
		return dao.getList(sql.toString(), inputValue, outputValue);
	}

	/**
	 * 卫生检查信息保存
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean wsjcBc(WsjcForm myForm, String username) throws Exception {
		boolean flag = false;
		DAO dao = DAO.getInstance();

		String wsdjbz = myForm.getWsdjbz();
		String dgldqbz = myForm.getDgldqbz();
		String jcry = myForm.getJcry();
		String jcsj = myForm.getJcsj();
		String ld = myForm.getLd();
		String qs = myForm.getQs();
		String dgldq = myForm.getDgldq();
		String wsdj = myForm.getWsdj();

		String sql = "insert into xg_gygl_jhzy_wsdgljcb (LDDM,QSH,WSDJ,WSDJBZ,DGLDQ,DGLDQBZ,JCSJ,JCRY) values (?,?,?,?,?,?,?,?)";
		flag = dao.runUpdate(sql.toString(), new String[] { ld, qs, wsdj, wsdjbz, dgldq, dgldqbz, jcsj, jcry });
		return flag;
	}

	/**
	 * 卫生检查信息删除
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean wsjcSc(WsjcForm myForm, String[] valArr, String username)throws SQLException {
		List<String[]> sqlList = new ArrayList<String[]>();
		String sql = "delete from xg_gygl_jhzy_wsdgljcb where lddm||qsh||jcsj=?";
		String[] val = null;
		for (String threeVal : valArr) {
			val = new String[1];
			val[0] = threeVal.replaceAll("!@", "");
			sqlList.add(val);
		}
		DAO dao = DAO.getInstance();
		boolean flag = dao.checkBatchResult(dao.runBatch(sql, sqlList));
		return flag;
	}

	/**
	 * 查询卫生检查信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getWsjcMap(WsjcForm wsjcForm) {
		DAO dao = DAO.getInstance();
		String guid = wsjcForm.getGuid();
		String[] valArr = guid.split("!@");
		StringBuilder sql = new StringBuilder();
		sql.append("select e.lddm ld,e.qsh qs,e.wsdj, e.dgldq, e.jcry, e.jcsj, e.wsdjbz,e.dgldqbz,e.yhm sgxfdy, e.ldmc, e.ldxb xb, (select bmmc from zxbz_xxbmdm where f.xydm = bmdm) xy,(select bjmc from bks_bjdm where f.bjdm = bjdm) bj ");
		sql.append(" from (select c.lddm, c.qsh, c.wsdj, c.dgldq, c.jcry, c.jcsj,c.wsdjbz,c.dgldqbz, c.yhm, d.ldmc, d.ldxb ");
		sql.append(" from (select a.lddm, a.qsh, a.wsdj, a.dgldq, a.jcry, a.jcsj,a.wsdjbz,a.dgldqbz, b.yhm ");
		sql.append(" from xg_gygl_jhzy_wsdgljcb a left join XG_GYGL_NEW_GYFDYB b on a.lddm = b.lddm) c ");
		sql.append(" left join XG_GYGL_NEW_LDXXB d on c.lddm = d.lddm) e ");
		sql.append(" left join (select distinct lddm,qsh,xydm,bjdm from XG_GYGL_NEW_CWXXB) f on e.lddm = f.lddm and e.qsh = f.qsh where e.lddm=? and e.qsh=? and jcsj=? ");
		return dao.getMapNotOut(sql.toString(), new String[] { valArr[0], valArr[1], valArr[2] });
	}

	/**
	 * 卫生检查信息修改
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean wsjcXg(WsjcForm myForm, String username) throws Exception {
		boolean flag = false;
		DAO dao = DAO.getInstance();
		String wsdjbz = myForm.getWsdjbz();
		String dgldqbz = myForm.getDgldqbz();
		String jcry = myForm.getJcry();
		String jcsj = myForm.getJcsj();
		String ld = myForm.getLd();
		String qs = myForm.getQs();
		String dgldq = myForm.getDgldq();
		String wsdj = myForm.getWsdj();
		String sql = "delete from xg_gygl_jhzy_wsdgljcb where Lddm=? and QSH=? and JCSJ=?";
		flag = dao.runUpdate(sql.toString(), new String[] { ld, qs, jcsj });
		if (flag == true) {
			sql = "insert into xg_gygl_jhzy_wsdgljcb (LDDM,QSH,WSDJ,WSDJBZ,DGLDQ,DGLDQBZ,JCSJ,JCRY) values (?,?,?,?,?,?,?,?)";
			flag = dao.runUpdate(sql.toString(), new String[] { ld, qs, wsdj, wsdjbz, dgldq, dgldqbz, jcsj, jcry });
		}
		return flag;
	}
}