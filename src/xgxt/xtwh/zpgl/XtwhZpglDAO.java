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
	 * ѧ����Ƭ��Ϣ
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
		sql.append(" case when nvl(length(b.zp),'0')=0 then 'δ����' else '�ѵ���' end sfdrzp, ");
		sql.append(" case when nvl(length(b.xszp),'0')=0 then 'δ����' else '�ѵ���' end sfdrxszp from view_xsxxb_zxs a ");
		sql.append(" left join xszpb b on a.xh=b.xh )a  where 1=1");
		sql.append( searchTjByUser);
		sql.append(")a");
		return CommonQueryDAO.commonQuery(sql.toString(), mQuery
				.getQueryString(), mQuery.getInputList(), outPut, model);
	}

	/**
	 * ������Ƭ����ȡ��֮��Ӧ��ѧ����xh��Map
	 * 
	 * @param photoNameType
	 *            ��Ƭ������ʽ��xh��sfzh��ksh
	 * @param photoName
	 *            ��Ƭ����������׺
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
	 * ͳ����Ƭ���и�lqh�ļ�¼��
	 * 
	 * @param lqh
	 *            ¼ȡ��
	 * @return
	 */
	public String countDataRowNum(String lqh) {
		DAO dao = DAO.getInstance();
		String sql = "select count(*) num from xszpb where xh = ?";
		return dao.getOneRs(sql, new String[] { lqh }, "num");
	}

	/**
	 * ��Ƭ������⣬�������
	 * 
	 * @param lqh
	 *            ¼ȡ��
	 * @param instream
	 *            ��Ƭ������
	 * @throws Exception
	 */
	public void insertPhoto(String lqh, InputStream instream) {
		DAO dao = DAO.getInstance();
		String sql = "insert into xszpb(zp,xh) values(?,?)";
		try {
			dao.upBlob(sql, lqh, instream.available(), instream);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
	}

	/**
	 * ��Ƭ������⣬���²���
	 * 
	 * @param lqh
	 *            ¼ȡ��
	 * @param instream
	 *            ��Ƭ������
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
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡ����ѧ����Ƭ
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
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		sql.append(" select a.*,rownum r from ");
		sql
				.append(" (select a.xh,a.xm,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.nj,a.sfzh,a.ksh, ");
		sql
				.append(" case when b.zp is null and nvl(length(b.zp),'0')=0 then 'δ����' else '�ѵ���' end sfdrzp, ");
		sql
				.append(" case when b.xszp is null and nvl(length(b.xszp),'0')=0 then 'δ����' else '�ѵ���' end sfdrxszp from view_xsbfxx a ");
		sql.append(" left join xszpb b on a.xh=b.xh )a  ");

		String sqlTj = null;
		if ("xs_zs".equals(model.getZpType())) {// ��û��������Ƭ�򵼳�������Ƭ
			sqlTj = " and (sfdrxszp='�ѵ���' or sfdrzp='�ѵ���')";
		} else if ("xszp".equals(model.getZpType())) {// ����������Ƭ
			sqlTj = " and sfdrxszp='�ѵ���' ";
		} else {// ����������Ƭ
			sqlTj = " and sfdrzp='�ѵ���' ";
		}
		String[] outputValue = new String[] { "xh", "xm", "ksh", "sfzh" };
		return dao.getList(sql.toString() + mQuery.getQueryString() + sqlTj,
				mQuery.getInputList(), outputValue);
		// CommonQueryDAO.commonQueryNotFy(sql.toString(), mQuery
		// .getQueryString(), mQuery.getInputList(), outPut, model);

	}

	/**
	 * ��Ƭ������⣬�������
	 * 
	 * @param lqh
	 *            ¼ȡ��
	 * @param instream
	 *            ��Ƭ������
	 * @throws Exception
	 */
	public void insertPhoto_xs(String lqh, InputStream instream) {
		DAO dao = DAO.getInstance();
		String sql = "insert into xszpb(xszp,xh) values(?,?)";
		try {
			dao.upBlob(sql, lqh, instream.available(), instream);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
	}

	/**
	 * ��Ƭ������⣬���²���
	 * 
	 * @param lqh
	 *            ¼ȡ��
	 * @param instream
	 *            ��Ƭ������
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
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
	}

	/**
	 * ��ѧ�Ż�ȡ��Ƭ����
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
