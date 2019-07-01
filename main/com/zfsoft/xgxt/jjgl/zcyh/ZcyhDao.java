/**
 * @部门:学工产品事业部
 * @日期：2014-8-28 上午11:08:45 
 */  
package com.zfsoft.xgxt.jjgl.zcyh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.util.UniqID;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-8-28 上午11:08:45 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZcyhDao extends SuperDAOImpl<ZcyhForm> {

	/**
	 * 
	 * @描述:获取子女信息
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-28 上午11:16:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public HashMap<String , String> getZnxxMapById(String id) throws Exception{
		String sql = "select a.* from XSGGFW_JJFW_JZZNXXB a where a.znid = ? ";
		
		return dao.getMapNotOut(sql, new String[]{id});
	}
	
	/**
	 * 
	 * @描述:获取注册用户信息
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-28 上午11:27:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String , String> getZcyhMapById(String id) throws Exception{
		String sql = "select a.* from XSGGFW_JJFW_YHZCXXB a where a.yhm = ? ";
		
		return dao.getMapNotOut(sql, new String[]{id});
	}
	
	/**
	 * 
	 * @描述:获取所有子女信息
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-28 上午11:19:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param yhm
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getZnxxMapByYhm(String yhm) throws Exception{
		String sql = "select a.* from XSGGFW_JJFW_JZZNXXB a where a.yhm = ? ";
		return dao.getListNotOut(sql,  new String[]{yhm});
	}
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ZcyhForm t)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		//白名单
		if(StringUtils.equals("w", t.getType())){
			sql.append("select t1.* from XSGGFW_JJFW_YHZCXXB t1 where not exists (select 1 from XSGGFW_JJFW_JZHMD t2 where t2.zcid = t1.yhm and t2.sfsx = '1') ");
		}else if(StringUtils.equals("b", t.getType())){
			sql.append("select t2.yy , t2.sj , t2.sfsx , t1.* from XSGGFW_JJFW_JZHMD t2 left join XSGGFW_JJFW_YHZCXXB t1 on t2.zcid = t1.yhm where t2.sfsx = '1' ");
		}
		
		if (!StringUtil.isNull(t.getYhm())){
			sql.append(" and t1.yhm like '%'||?||'%' ");
			params.add(t.getYhm());
		}

		if (!StringUtil.isNull(t.getXm())){
			sql.append(" and t1.xm like '%'||?||'%' ");
			params.add(t.getXm());
		}

		if (!StringUtil.isNull(t.getSfzh())){
			sql.append(" and t1.sfzh like '%'||?||'%' ");
			params.add(t.getSfzh());
		}
		return super.getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	
	/**
	 * 
	 * @描述:设置黑名单
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-29 下午03:11:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param yhm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean szHmd(ZcyhForm model)throws Exception{
		String sql = "insert into XSGGFW_JJFW_JZHMD (zcid , yy , sj , sfsx) values ( ? ,? ,? ,?)";
		return dao.insert(sql, new String[]{model.getYhm() , model.getHmdyy() , model.getHmdsj() , "1"});
	}
	
	/**
	 * 
	 * @描述:Cancel黑名单
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-29 下午03:11:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param yhm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelHmd(ZcyhForm model)throws Exception{
		String sql = "update XSGGFW_JJFW_JZHMD set sfsx = '0' where zcid = ?";
		return dao.update(sql, new String[]{model.getYhm()}) > 0;
	}
	
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ZcyhForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(ZcyhForm.class);
		super.setKey("yhm");
		super.setTableName("XSGGFW_JJFW_YHZCXXB");
	}

	/**
	 * 判断用户名是否被注册.
	 *
	 * @param yhm
	 * @return
	 * @throw
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-11-27 09:26
	 */
    public boolean isYhmExist(String yhm) {

    	String sql = "SELECT count(1) num FROM XSGGFW_JJFW_YHZCXXB WHERE YHM = ?";
    	String num = dao.getOneRs(sql,new String[]{yhm},"num");
		return Integer.parseInt(num) > 0;
    }

	/**
	 * 判断身份证号是否被注册.
	 *
	 * @param zcyhForm
	 * @return
	 * @throw
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-11-27 09:54
	 */
	public boolean isSfzhExist(ZcyhForm zcyhForm) {

		StringBuilder sbd = new StringBuilder();

		List<String> paraList = new ArrayList<String>();
		paraList.add(zcyhForm.getSfzh());

		sbd.append("SELECT count(1) num FROM XSGGFW_JJFW_YHZCXXB WHERE sfzh = ?");
		if(zcyhForm.getYhm() != null){
			sbd.append(" AND yhm != ?");
			paraList.add(zcyhForm.getYhm());
		}

		String num = dao.getOneRs(sbd.toString(),paraList.toArray(new String[]{}),"num");
		return Integer.parseInt(num) > 0;
	}

	/**
	 * 判断联系电话是否被注册.
	 *
	 * @param zcyhForm
	 * @return
	 * @throw
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-11-27 09:54
	 */
	public boolean isLxdhExist(ZcyhForm zcyhForm) {

		StringBuilder sbd = new StringBuilder();

		List<String> paraList = new ArrayList<String>();
		paraList.add(zcyhForm.getLxdh());

		sbd.append("SELECT count(1) num FROM XSGGFW_JJFW_YHZCXXB WHERE lxdh = ?");
		if(zcyhForm.getYhm() != null){
			sbd.append(" AND yhm != ?");
			paraList.add(zcyhForm.getYhm());
		}

		String num = dao.getOneRs(sbd.toString(),paraList.toArray(new String[]{}),"num");
		return Integer.parseInt(num) > 0;
	}

	/**
	 * 新增家长信息.
	 *
	 * @param zcyhForm
	 * @return
	 * @throw
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-11-27 10:26
	 */
	public boolean jzxxSaveForAdd(ZcyhForm zcyhForm) throws Exception {

		StringBuilder sbd = new StringBuilder();
		sbd.append("INSERT INTO XSGGFW_JJFW_YHZCXXB (YHM, XM, SFZH, LXDH, JTZZ, GZDW, ZT, ZCSJ,XB) ");
		sbd.append("VALUES (?, ?, ?, ?, ?, ?, ?,?,?)");
		String [] inputValue = new String[]{zcyhForm.getYhm(),zcyhForm.getXm(),zcyhForm.getSfzh(),
				zcyhForm.getLxdh(),zcyhForm.getJtzz(),zcyhForm.getGzdw(),zcyhForm.getZt(),zcyhForm.getZcsj(),zcyhForm.getXb()};
		return dao.runUpdate(sbd.toString(),inputValue);
	}

	/**
	 * 更新家长信息.
	 *
	 * @param zcyhForm
	 * @return
	 * @throw
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-11-27 10:26
	 */
	public boolean jzxxSaveForEdit(ZcyhForm zcyhForm) throws Exception {

		StringBuilder sbd = new StringBuilder();
		sbd.append("UPDATE XSGGFW_JJFW_YHZCXXB SET XM = ?,SFZH = ?,LXDH = ?,");
		sbd.append("JTZZ = ?,GZDW = ?,ZT = ?,ZCSJ = ?,XB = ? WHERE YHM = ?");
		String [] inputValue = new String[]{zcyhForm.getXm(),zcyhForm.getSfzh(),zcyhForm.getLxdh(),zcyhForm.getJtzz(),
				zcyhForm.getGzdw(),zcyhForm.getZt(),zcyhForm.getZcsj(),zcyhForm.getXb(),zcyhForm.getYhm()};
		return dao.runUpdate(sbd.toString(),inputValue);
	}

	/**
	 * 保存子女信息（先删后插）.
	 *
	 * @param znxxModelList
	 * @param yhm
	 * @return
	 * @throw
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-11-27 10:26
	 */
	public boolean saveZnxx(List<ZnxxModel> znxxModelList, String yhm) throws Exception {

		String sql_del = "DELETE FROM XSGGFW_JJFW_JZZNXXB WHERE YHM = ?";
		boolean result = dao.runUpdate(sql_del,new String[]{yhm});
		if(result){
			String sql_insert = "INSERT INTO XSGGFW_JJFW_JZZNXXB (ZNID, XM, XB, CSRQ, ZDXX, NJ, YHM) VALUES (?, ?,?, ?, ?, ?, ?)";
			List<String[]> paraList = new ArrayList<String[]>();
			for(ZnxxModel znxxModel:znxxModelList){
				String [] para = new String[] {UniqID.getInstance().getUniqIDHash(),znxxModel.getXm(),znxxModel.getXb(),znxxModel.getCsrq(),
				znxxModel.getZdxx(),znxxModel.getNj(),yhm};
				paraList.add(para);
			}
			result = dao.runBatchBoolean(sql_insert,paraList);
		}
		return result;
	}

	/**
	 * 根据用户名数组删除子女信息.
	 *
	 * @param ids
	 * @return
	 * @throw
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-11-27 17:17
	 */
	public int delZnxx(String[] ids) throws Exception {

		if(ids == null || ids.length == 0){
			return 0;
		}else {
			StringBuilder sbd = new StringBuilder();
			sbd.append("DELETE FROM XSGGFW_JJFW_JZZNXXB WHERE YHM IN (");
			for(int i=0;i<ids.length;i++){
				sbd.append(ids[i]);
				if(i != ids.length){
					sbd.append(",");
				}
			}
			sbd.append(")");
			int num = dao.runDelete(sbd.toString(),ids);
			return num;
		}
	}

	/**
	 * 根据家长用户名查询子女信息列表
	 * @param sqr
	 * @return
	 */
	public List<HashMap<String,String>> getZnxxListByPid(String sqr) {

		String sql = "select ZNID,XM,XB from XSGGFW_JJFW_JZZNXXB WHERE YHM = ?";
		return dao.getListNotOut(sql,new String[]{sqr});
	}

	/**
	 * @功能描述:获取此时最大用户名
	 * @auther: 王晨辉[1692]
	 */
	public String getMaxYhm() throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT max(yhm) yhm FROM XSGGFW_JJFW_YHZCXXB order by yhm desc");
		return dao.getOneRs(sql.toString(),new String[]{},"yhm");
	}
}
