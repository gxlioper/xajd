/**
 * @部门:学工产品事业部
 * @日期：2013-9-26 下午01:51:13 
 */  
package xsgzgl.xsxx.general.xszpgl;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xucy[工号:754]
 * @时间： 2013-9-26 下午01:51:13 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XszpglDao extends SuperDAOImpl<XszpglForm>{

	
	public List<HashMap<String, String>> getPageList(XszpglForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	//查询列表
	public List<HashMap<String, String>> getPageList(XszpglForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		StringBuilder tableSql = new StringBuilder();

		tableSql.append("select * from (select a.*,case when b.zp is null then '否' else '是' end sfdr,case when b.xszp is null then '否' else '是' end xssfdr,rownum r from (");
		tableSql.append("select a.* from view_xsxxcxjg a ");
		tableSql.append("where 1=1 and (sfzx='在校' or sfzx is null) ");
		
		tableSql.append(" ) a left join xszpb b on a.xh = b.xh ) a where 1=1 ");
		tableSql.append(searchTjByUser);
		tableSql.append(searchTj);
		tableSql.append(" order by nj desc nulls last,xydm,zydm,bjdm,xh ");
		return getPageList(t, tableSql.toString(), StringUtils.joinStrArr(inputV,
				new String[] {}));
	}

	/**
	 * 根据照片名获取与之对应的学生的xh的Map
	 * @param photoNameType 照片命名方式：xh、sfzh、ksh
	 * @param photoName 照片名，不带后缀
	 * @return
	 */
	public HashMap<String, String> getXhByType(String photoNameType,String photoName){
		
		DAO dao=DAO.getInstance();
		String sql = "select xh from view_xsbfxx where " + photoNameType + "=?";
		HashMap<String,String> map = dao.getMap(sql, new String[]{photoName},new String[]{"xh"});
		return map;
	}
	
	/**
	 * 统计照片库中该xh的记录数
	 * @param 
	 * @return
	 */
	public String countDataRowNum(String xh){
		DAO dao=DAO.getInstance();
		String sql = "select count(*) num from xszpb where xh = ?";
		return dao.getOneRs(sql, new String[]{xh}, "num");
	}
	
	/**
	 * 新生照片数据入库，插入操作
	 * @param lqh 录取号
	 * @param instream 照片数据流
	 * @throws Exception 
	 */
	public void insertPhoto_xs(String lqh,InputStream instream) {
		DAO dao=DAO.getInstance();
		String sql = "insert into xszpb(xszp,xh) values(?,?)";
		try {
			dao.upBlob(sql, lqh, instream.available(), instream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 新生照片数据入库，更新操作
	 * @param lqh 录取号
	 * @param instream 照片数据流
	 * @throws Exception 
	 */
	public void updatePhoto_xs(String lqh,InputStream instream) {
		
		DAO dao=DAO.getInstance();
		String sql = "update xszpb set xszp = ? where xh = ?";
		try {
			dao.upBlob(sql, lqh, instream.available(), instream);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}
	
	/**
	 * 照片数据入库，插入操作
	 * 
	 * @param lqh
	 *            录取号
	 * @param instream
	 *            照片数据流
	 * @throws Exception
	 */
	public void insertPhoto(String lqh, InputStream instream) {
		DAO dao = DAO.getInstance();
		String sql = "insert into xszpb(zp,xh) values(?,?)";
		try {
			dao.upBlob(sql, lqh, instream.available(), instream);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}

	/**
	 * 照片数据入库，更新操作
	 * 
	 * @param lqh
	 *            录取号
	 * @param instream
	 *            照片数据流
	 * @throws Exception
	 */
	public void updatePhoto(String lqh, InputStream instream) {

		DAO dao = DAO.getInstance();
		String sql = "update xszpb set zp = ? where xh = ?";
		try {
			dao.upBlob(sql, lqh, instream.available(), instream);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取所有学生照片
	 * @return  List<HashMap<String, String>>
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<HashMap<String, String>> getXszpList(XszpglForm t, User user) 
		throws Exception {

		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		StringBuilder tableSql = new StringBuilder();

		tableSql.append("select * from (select a.*,case when b.zp is null then '否' else '是' end sfdr,case ");
		tableSql.append("when b.xszp is null then '否' else '是' end xssfdr, rownum r from (");
		tableSql.append("select a.* from view_xsxxcxjg a ");
		tableSql.append("where 1=1 and (sfzx='在校' or sfzx is null) ");
		
		tableSql.append(" ) a left join xszpb b on a.xh = b.xh ) a where 1=1  and sfdr='是' ");
		tableSql.append(searchTjByUser);
		tableSql.append(searchTj);
		tableSql.append(" order by nj desc nulls last,xydm,zydm,bjdm,xh ");
		String[] outputValue=new String[]{"xh", "xm","ksh", "sfzh" };
		return dao.getList(tableSql.toString(), inputV, outputValue);
	}
	
	protected void setTableInfo() {
		super.setTableName("");
		super.setKey("xh");// 主键一定要和FORM中的set方法的属性一致,即大小写
		super.setClass(XszpglForm.class);
	}

	
}
