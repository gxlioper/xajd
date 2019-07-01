package xgxt.xtwh.zpgl;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class XtwhZpglDAO {

	/**
	 * 学生照片信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZpxxList(XtwhZpglForm model,String[]outPut,User user) throws Exception {
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		MakeQuery mQuery = new MakeQuery();
		StringBuilder sql = new StringBuilder();

		String[] colList = { "nj", "xydm", "zydm", "bjdm" };
		String[] colLikeList = { "xh", "xm", "sfzh", "ksh", "sfdrzp","sfdrxszp" };
		mQuery.makeQuery(colList, colLikeList, model);
		sql.append(" select * from (");
		sql.append(" select a.*,rownum r from ");
		sql.append(" (select a.xh,a.xm,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.nj,a.sfzh,a.ksh, ");
		sql.append(" case when nvl(length(b.zp),'0')=0 then '未导入' else '已导入' end sfdrzp, ");
		sql.append(" case when nvl(length(b.xszp),'0')=0 then '未导入' else '已导入' end sfdrxszp from view_xsxxb_zxs a ");
		sql.append(" left join xszpb b on a.xh=b.xh )a  where 1=1");
		sql.append( searchTjByUser);
		sql.append(")a");
		return CommonQueryDAO.commonQuery(sql.toString(), mQuery
				.getQueryString(), mQuery.getInputList(), outPut, model);
	}

	/**
	 * 根据照片名获取与之对应的学生的xh的Map
	 * 
	 * @param photoNameType
	 *            照片命名方式：xh、sfzh、ksh
	 * @param photoName
	 *            照片名，不带后缀
	 * @return
	 */
	public HashMap<String, String> getXhByType(String photoNameType,
			String photoName) {

		DAO dao = DAO.getInstance();
		String sql = "select xh from view_xsbfxx where " + photoNameType + "=?";
		HashMap<String, String> map = dao.getMap(sql,
				new String[] { photoName }, new String[] { "xh" });
		return map;
	}

	/**
	 * 统计照片库中该lqh的记录数
	 * 
	 * @param lqh
	 *            录取号
	 * @return
	 */
	public String countDataRowNum(String lqh) {
		DAO dao = DAO.getInstance();
		String sql = "select count(*) num from xszpb where xh = ?";
		return dao.getOneRs(sql, new String[] { lqh }, "num");
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
	 * 
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getXszpList(XtwhZpglForm model) {

		DAO dao = DAO.getInstance();
		// StringBuilder sql = new StringBuilder();
		// sql
		// .append("  select a.xh,a.zp,b.xm,b.ksh,b.sfzh from xszpb a left join view_xsbfxx b on a.xh=b.xh ");
		// return dao.getList(sql.toString(), new String[] {}, new String[] {
		// "xh", "xm", "zp", "ksh", "sfzh" });

		MakeQuery mQuery = new MakeQuery();
		StringBuilder sql = new StringBuilder();

		String[] colList = { "nj", "xydm", "zydm", "bjdm" };
		String[] colLikeList = { "xh", "xm", "sfzh", "ksh", "sfdrzp",
				"sfdrxszp" };
		try {
			mQuery.makeQuery(colList, colLikeList, model);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		sql.append(" select a.*,rownum r from ");
		sql
				.append(" (select a.xh,a.xm,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.nj,a.sfzh,a.ksh, ");
		sql
				.append(" case when b.zp is null and nvl(length(b.zp),'0')=0 then '未导入' else '已导入' end sfdrzp, ");
		sql
				.append(" case when b.xszp is null and nvl(length(b.xszp),'0')=0 then '未导入' else '已导入' end sfdrxszp from view_xsbfxx a ");
		sql.append(" left join xszpb b on a.xh=b.xh )a  ");

		String sqlTj = null;
		if ("xs_zs".equals(model.getZpType())) {// 若没有新生照片则导出招生照片
			sqlTj = " and (sfdrxszp='已导入' or sfdrzp='已导入')";
		} else if ("xszp".equals(model.getZpType())) {// 导出新生照片
			sqlTj = " and sfdrxszp='已导入' ";
		} else {// 导出招生照片
			sqlTj = " and sfdrzp='已导入' ";
		}
		String[] outputValue = new String[] { "xh", "xm", "ksh", "sfzh" };
		return dao.getList(sql.toString() + mQuery.getQueryString() + sqlTj,
				mQuery.getInputList(), outputValue);
		// CommonQueryDAO.commonQueryNotFy(sql.toString(), mQuery
		// .getQueryString(), mQuery.getInputList(), outPut, model);

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
	public void insertPhoto_xs(String lqh, InputStream instream) {
		DAO dao = DAO.getInstance();
		String sql = "insert into xszpb(xszp,xh) values(?,?)";
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
	public void updatePhoto_xs(String lqh, InputStream instream) {

		DAO dao = DAO.getInstance();
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
	 * 按学号获取照片对象
	 * 
	 * @param xh
	 * @return
	 * @throws SQLException
	 */
	public InputStream getPhotoStream(String xh) throws SQLException {

		String sql = "select zp from xszpb where xh=?";
		DAO dao = DAO.getInstance();
		Blob blob = dao.getOneBlob(sql, new String[] { xh }, "zp");
		if (blob == null) {
			return null;
		} else {
			return blob.getBinaryStream();
		}

	}

}
