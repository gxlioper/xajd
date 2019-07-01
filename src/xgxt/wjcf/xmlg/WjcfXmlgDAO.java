package xgxt.wjcf.xmlg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.apache.struts.upload.FormFile;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.String.StringUtils;

public class WjcfXmlgDAO {

	private DAO dao = DAO.getInstance();

	// 查询处分打印报表信息
	public HashMap<String, String> getCfbprint(WjcfXmlgModel model) {
		return CommonQueryDAO.getStuInfo(model.getXh());
	}

	// 查询处分打印报表信息
	public HashMap<String, String> getCfbprintDetails(WjcfXmlgModel model) {
		if (StringUtils.isNull(model.getPkValue())) {
			return null;
		} else {
			return dao
					.getMapNotOut(
							"select a.xh,a.xm,a.xb,a.bz,cflbmc,a.bjmc,a.xymc,a.zymc,a.nj,xxclyj,xndclyj,b.jg,b.csrq from view_wjcf a left join view_xsxxb b on a.xh=b.xh where a.xh||a.xn||a.nd||a.sbsj=?",
							new String[] { model.getPkValue() });
		}
	}

	/**
	 * 处分申报查询表头字段信息
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> queryCfsbxxTitle() {
		String[] enList = null;
		String[] cnList = null;
		if (Globals.XXDM_WHSYFWXY.equalsIgnoreCase(Base.xxdm)) {
			enList = new String[] { "pk", "dis", "r", "xn", "nd", "xh", "xm",
					"xymc", "bjmc", "cflbmc", "cfyymc", "xsqr", "xysh", "xxsh" };
			cnList = new String[] { "pk", "dis", "行号", "学年", "年度", "学号", "姓名",
					Base.YXPZXY_KEY, "班级", "处分类别", "处分原因", "是否告知学生", Base.YXPZXY_KEY+"审核", "学校审核" };
		} else if (Globals.XXDM_XMLGXY.equalsIgnoreCase(Base.xxdm)) {
			enList = new String[] { "pk", "dis", "r", "xn", "nd", "xh", "xm",
					"xymc", "bjmc", "cflbmc", "cfyymc", "xsqr", "xysh", "xxsh" };
			cnList = new String[] { "pk", "dis", "行号", "学年", "年度", "学号", "姓名",
					Base.YXPZXY_KEY, "班级", "处分类别", "处分原因", "学生确认", Base.YXPZXY_KEY+"审核", "学校审核" };
		} else {
			enList = new String[] { "pk", "dis", "r", "xn", "nd", "xh", "xm",
					"xymc", "bjmc", "cflbmc", "cfyymc", "xxsh" };
			cnList = new String[] { "pk", "dis", "行号", "学年", "年度", "学号", "姓名",
					Base.YXPZXY_KEY, "班级", "处分类别", "处分原因", "学校审核" };
		}
		return dao.arrayToList(enList, cnList);
	}

	public List<HashMap<String, String>> queryCfsbshxxByxyTitle() {
		String[] enList = new String[] { "pk", "dis", "r", "xn", "nd", "xh",
				"xm", "bjmc", "cflbmc", "cfyymc", "cfsj", "cfwh", "xysh",
				"xxsh" };
		String[] cnList = new String[] { "pk", "dis", "行号", "学年", "年度", "学号",
				"姓名", "班级", "处分类别", "处分原因", "处分时间", "处分文号", Base.YXPZXY_KEY+"审核", "学校审核" };
		return dao.arrayToList(enList, cnList);
	}

	public List<HashMap<String, String>> queryCfsbshxxByxxTitle() {
		String[] enList = new String[] { "pk", "dis", "r", "xn", "nd", "xh",
				"xm", "bjmc", "cflbmc", "cfyymc", "cfsj", "cfwh", "xxsh",
				"xndsh" };
		String[] cnList = new String[] { "pk", "dis", "行号", "学年", "年度", "学号",
				"姓名", "班级", "处分类别", "处分原因", "处分时间", "处分文号", "学校审核", "校办审核" };
		return dao.arrayToList(enList, cnList);
	}

	public List<HashMap<String, String>> queryCfsbshxxByxbTitle() {
		String[] enList = new String[] { "pk", "dis", "r", "xn", "nd", "xh",
				"xm", "bjmc", "cflbmc", "cfyymc", "cfsj", "cfwh", "xndsh" };
		String[] cnList = new String[] { "pk", "dis", "行号", "学年", "年度", "学号",
				"姓名", "班级", "处分类别", "处分原因", "处分时间", "处分文号", "校办审核" };
		return dao.arrayToList(enList, cnList);
	}

	/**
	 * 学院处分申报查询数据结果信息（带分页）
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryCfsbxxResultByxy(WjcfXmlgModel model,
			String[] colList) throws Exception {
		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj", "xn",
				"nd", "cflb", "cfyy" ,"xxsh"};
		String[] queryLikeList = new String[] { "xh", "xm" };
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, queryLikeList, model);
		String sql = "select xh||xn||nd||sbsj pk,(case when xxsh!='未审核' or xsqr='已确认' or xndsh!='未审核' then 'disabled' "
				+ "else '' end) dis,xn,nd,xh,xm,xymc,bjmc,cflbmc,cfyymc,xysh,xxsh,cfsj,cfwh,xsqr,(case when xxsh='解除处分(处分申诉)' or xxsh='解除处分' or xxsh='解除处分(处分解除)' then '#BBBBBB' else '' end) bg,xndsh from view_wjcf ";
		if (colList == null) {
			
			if (Globals.XXDM_WHSYFWXY.equalsIgnoreCase(Base.xxdm) || Globals.XXDM_XMLGXY.equalsIgnoreCase(Base.xxdm)) {
				colList = new String[] { "pk", "dis", "bg","r", "xn", "nd", "xh", "xm",
						"xymc", "bjmc", "cflbmc", "cfyymc", "xsqr", "xysh", "xxsh" };
			} else {
				colList = new String[] { "pk", "dis", "bg","r", "xn", "nd", "xh", "xm",
						"xymc", "bjmc", "cflbmc", "cfyymc", "xxsh" };
			}
		}

		return CommonQueryDAO.commonQuery("select a.*,rownum r from ("+sql, queryObject.getQueryString() + " order by xxsh) a ",
				queryObject.getInputList(), colList, model);
	}

	/**
	 * 学校处分申报查询数据结果信息（带分页）
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryCfsbxxResultByxx(WjcfXmlgModel model,
			String[] colList) throws Exception {
		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj", "xn",
				"nd", "cflb", "cfyy" ,"xxsh"};
		String[] queryLikeList = new String[] { "xh", "xm" };
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, queryLikeList, model);
		String sql = StringUtils.joinStr(
				"select xh||xn||nd||sbsj pk,",
				"xn,nd,xh,xm,xymc,bjmc,cflbmc,cfyymc,xysh,xxsh,",
				"xndsh,cfsj,cfwh,xsqr,shbm,(case when xxsh='解除处分(处分申诉)' or xxsh='解除处分(处分解除)' or xxsh='解除处分' then '#BBBBBB' else '' end) bg,  " ,
				" case when  xxsh!='未审核' or xsqr='已确认'  then 'disabled' else '' end dis",
				" from view_wjcf ");
		if (colList == null) {
			if (Globals.XXDM_WHSYFWXY.equalsIgnoreCase(Base.xxdm) || Globals.XXDM_XMLGXY.equalsIgnoreCase(Base.xxdm)) {
				colList = new String[] { "pk", "dis", "bg","r", "xn", "nd", "xh", "xm",
						"xymc", "bjmc", "cflbmc", "cfyymc", "xsqr", "xysh", "xxsh" };
			} else {
				colList = new String[] { "pk", "dis","bg", "r", "xn", "nd", "xh", "xm",
						"xymc", "bjmc", "cflbmc", "cfyymc", "xxsh" };
			}
		}

		return CommonQueryDAO.commonQuery("select a.*,rownum r from (" + sql, queryObject.getQueryString() + " order by xxsh) a",
				queryObject.getInputList(), colList, model);
	}

	/**
	 * 校办处分申报查询数据结果信息（带分页）
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryCfsbxxResultByxb(WjcfXmlgModel model,
			String[] colList) throws Exception {
		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj", "xn",
				"nd", "cflb", "cfyy" };
		String[] queryLikeList = new String[] { "xh", "xm" };
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, queryLikeList, model);
		String sql = "select xh||xn||nd||sbsj pk,'' dis,rownum r,xn,nd,xh,xm,xymc,bjmc,cflbmc,cfyymc,xysh,xxsh,xndsh,cfsj,cfwh from view_wjcf ";

		if (colList == null) {
			colList = new String[] { "pk", "dis", "r", "xn", "nd", "xh", "xm",
					"xymc", "bjmc", "cflbmc", "cfyymc" };
		}

		String cfjb = dao.getOneRs(
				"select cfjb from cflbdmb where cflbmc like '记过%'",
				new String[] {}, "cfjb");

		String wheresql = " where xxsh='通过' and cfjb >="
				+ (StringUtils.isNull(cfjb) ? "0" : cfjb);
		if (!StringUtils.isNull(queryObject.getQueryString())) {
			wheresql = " and xxsh='通过' and cfjb >="
					+ (StringUtils.isNull(cfjb) ? "0" : cfjb);
		}

		return CommonQueryDAO.commonQuery(sql, queryObject.getQueryString()
				+ wheresql, queryObject.getInputList(), colList, model);
	}

	/**
	 * 处分申报信息修改
	 * 
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewCfsbxx(WjcfXmlgModel model) {
		String sql = "select xh||xn||nd||sbsj pk,xh,xn,nd,xm,xymc,zymc,xsqr,bjmc,nj,xb,cflb,wjsj,"
				+ "cflbmc,cfyy,cfyymc,sbsj,cfwh,cfsj,jtwjsy,wjsj,xyclyj,fjsclj,bz,xxsh,xgcyj,bzryj,xxyj,xyyj,sbr,(select b.zzmmmc from view_xsxxb b where a.xh=b.xh) zzmmmc,lswjjl from view_wjcf a where xh||xn||nd||sbsj=?";
		return dao.getMapNotOut(sql, new String[] { model.getPkValue() });
	}

	/**
	 * 修改处分申报信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean modiCfsbxx(WjcfXmlgModel model) {
		boolean result = false;
		if (StringUtils.isNull(model.getPkValue())) {
			return result;
		}

		if (uploadFile(model, model.getUploadFile())) {// 如果文件上传成功,则更新数据
			String sql = "update wjcfb set cflb=?,cfyy=?,xn=?,nd=?,bz=?,xyclyj=?,lswjjl=?,wjsj=? where xh||xn||nd||sbsj=?";
			String[] inpList = { model.getCflb(), model.getCfyy(),
					model.getXn(), model.getNd(), model.getJtwjsy(),
					model.getXyclyj(), model.getLswjjl(), model.getWjsj(),
					model.getPkValue() };
			if (!StringUtils.isNull(model.getFjsclj())) {
				sql = "update wjcfb set cflb=?,cfyy=?,xn=?,nd=?,bz=?,xyclyj=?,fjsclj=?,lswjjl=?,wjsj=? where xh||xn||nd||sbsj=?";
				inpList = new String[] { model.getCflb(), model.getCfyy(),
						model.getXn(), model.getNd(), model.getJtwjsy(),
						model.getXyclyj(), model.getFjsclj(),
						model.getLswjjl(), model.getWjsj(), model.getPkValue() };
			}
			if (Base.xxdm.equalsIgnoreCase(Globals.XXDM_NNZYJSXY)) {
				sql = "update wjcfb set cflb=?,cfyy=?,xn=?,nd=?,bz=?,xyclyj=?,fjsclj=?,lswjjl=?,wjsj=?,xyyj=?,xxyj=?,bzryj=?,xgcyj=? where xh||xn||nd||sbsj=?";
				inpList = new String[] { model.getCflb(), model.getCfyy(),
						model.getXn(), model.getNd(), model.getJtwjsy(),
						model.getXyclyj(), model.getFjsclj(),
						model.getLswjjl(), model.getWjsj(), model.getXyyj(),
						model.getXxyj(), model.getBzryj(), model.getXgcyj(),
						model.getPkValue() };
			}
			try {
				result = dao.runUpdate(sql, inpList);
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}

		return result;
	}

	/**
	 * 上传文件使用方法
	 * 
	 * @param model
	 * @param file
	 * @return
	 */
	public boolean uploadFile(WjcfXmlgModel model, FormFile file) {
		boolean result = true;
		String filePath = null;
		String dir = model.getRealPath() + "upload";// TOMCAT路径
		File f = new File(dir);
		if (!f.exists()) {
			f.mkdir();
		}
		String dateStr = "";
		Timestamp date = new Timestamp(System.currentTimeMillis());
		dateStr += date.toString().substring(0, 19);
		dateStr = dateStr.replaceAll("-", "").replaceAll(" ", "").replaceAll(
				":", "");
		// FormFile file = model.getUploadFile();
		if (file == null) {
			String xxdm = StandardOperation.getXxdm();
			if (!Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
				result = false;
			}

		} else {
			try {
				int size = file.getFileSize();
				if (size > 0) {// 有文件上传
					String fName = dateStr + file.getFileName();
					InputStream in = file.getInputStream();
					filePath = dir + "/" + fName;
					OutputStream out = new FileOutputStream(filePath);
					int bytesRead = 0;
					byte[] buffer = new byte[size];
					while ((bytesRead = in.read(buffer, 0, size)) != -1) {
						out.write(buffer, 0, bytesRead);
					}
					out.close();
					in.close();
				}
			} catch (Exception e) {
				result = false;
			}
		}
		// 上传成功，则将文件路径进行赋值
		model.setFjsclj(filePath);
		return result;
	}

	/**
	 * 删除处分申报信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean delCfsbxx(WjcfXmlgModel model) {
		boolean result = false;
		if (model.getKeys() == null) {
			return result;
		}
		String[] sqlArr = new String[model.getKeys().length];
		for (int i = 0; i < sqlArr.length; i++) {
			StringBuffer sql = new StringBuffer(
					"delete from wjcfb where xh||xn||nd||sbsj='");
			sql.append(model.getKeys()[i]);
			sql.append("'");
			sqlArr[i] = sql.toString();
		}

		int[] res;
		try {
			res = dao.runBatch(sqlArr);
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			return result;
		}
		result = dao.checkBatch(res);
		return result;
	}

	/**
	 * 处分申报信息修改(学院)
	 * 
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewCfsbxxByxy(WjcfXmlgModel model) {
		String sql = "select xh||xn||nd||sbsj pk,xh,xn,nd,xm,xymc,zymc,bjmc,nj,xb,cflb,"
				+ "cflbmc,cfyy,cfyymc,sbsj,cfwh,cfsj,jtwjsy,wjsj,xyclyj,fjsclj,bz,xysh sh,xxsh,xyyj yj,xndsh from view_wjcf where xh||xn||nd||sbsj=?";
		return dao.getMapNotOut(sql, new String[] { model.getPkValue() });
	}

	/**
	 * 处分申报信息修改(学校)
	 * 
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewCfsbxxByxx(WjcfXmlgModel model) {
		String sql = "select xh||xn||nd||sbsj pk,xh,xn,nd,xm,xymc,zymc,bjmc,nj,xb,cflb,"
				+ "cflbmc,cfyy,cfyymc,sbsj,cfwh,cfsj,jtwjsy,wjsj,xyclyj,fjsclj,bz,xysh,xxsh sh,xxclyj yj,xndsh from view_wjcf where xh||xn||nd||sbsj=?";
		return dao.getMapNotOut(sql, new String[] { model.getPkValue() });
	}

	/**
	 * 处分申报信息修改(校办)
	 * 
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewCfsbxxByxb(WjcfXmlgModel model) {
		String sql = "select xh||xn||nd||sbsj pk,xh,xn,nd,xm,xymc,zymc,bjmc,nj,xb,cflb,"
				+ "cflbmc,cfyy,cfyymc,sbsj,cfwh,cfsj,jtwjsy,wjsj,xyclyj,fjsclj,bz,xysh,xndsh sh,xndclyj yj,xxsh,xxclyj from view_wjcf where xh||xn||nd||sbsj=?";
		return dao.getMapNotOut(sql, new String[] { model.getPkValue() });
	}

	/**
	 * 学院用户操作保存处分审核信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveCfsbshxxByxy(WjcfXmlgModel model) throws Exception {
		if (StringUtils.isNull(model.getPkValue())) {
			return false;
		} else {
			return dao.runUpdate(
					"update wjcfb set xysh=?,xyyj=? where xh||xn||nd||sbsj=?",
					new String[] { model.getSh(), model.getYj(),
							model.getPkValue() });
		}
	}

	/**
	 * 学校用户操作保存处分审核信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveCfsbshxxByxx(WjcfXmlgModel model) throws Exception {
		if (StringUtils.isNull(model.getPkValue())) {
			return false;
		} else {
			return dao
					.runUpdate(
							"update wjcfb set xxsh=?,xxclyj=?,cfsj=?,cfwh=? where xh||xn||nd||sbsj=?",
							new String[] { model.getSh(), model.getYj(),
									model.getCfsj(), model.getCfwh(),
									model.getPkValue() });
		}
	}

	/**
	 * 校办用户操作保存处分审核信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveCfsbshxxByxb(WjcfXmlgModel model) throws Exception {
		boolean result = false;
		if (StringUtils.isNull(model.getPkValue())) {
			return result;
		} else {
			result = dao
					.runUpdate(
							"update wjcfb set xndsh=?,xndclyj=?,cfsj=?,cfwh=? where xh||xn||nd||sbsj=?",
							new String[] { model.getSh(), model.getYj(),
									model.getCfsj(), model.getCfwh(),
									model.getPkValue() });
		}
		return result;
	}

	/**
	 * 学院用户操作，批量审核处分信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveCfsbplxxByxy(WjcfXmlgModel model) throws Exception {
		if (model.getKeys() == null) {
			return false;
		} else {
			String[] sqlArr = new String[model.getKeys().length];
			for (int i = 0; i < model.getKeys().length; i++) {
				StringBuffer sql = new StringBuffer("update wjcfb set xysh='");
				sql.append(model.getSh());
				sql.append("',xyyj='");
				sql.append(model.getYj());
				sql.append("',cfsj='");
				sql.append(model.getCfsj());
				sql.append("',cfwh='");
				sql.append(model.getCfwh());
				sql.append("' where xh||xn||nd||sbsj='");
				sql.append(model.getKeys()[i]);
				sql.append("'");
				sqlArr[i] = sql.toString();
			}
			int[] result = dao.runBatch(sqlArr);
			return dao.checkBatch(result);
		}
	}

	/**
	 * 学校用户操作，批量审核处分信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveCfsbplxxByxx(WjcfXmlgModel model) throws Exception {
		if (model.getKeys() == null) {
			return false;
		} else {
			String[] sqlArr = new String[model.getKeys().length];
			for (int i = 0; i < model.getKeys().length; i++) {
				StringBuffer sql = new StringBuffer("update wjcfb set xxsh='");
				sql.append(model.getSh());
				sql.append("',xxclyj='");
				sql.append(model.getYj());
				sql.append("',cfsj='");
				sql.append(model.getCfsj());
				sql.append("',cfwh='");
				sql.append(model.getCfwh());
				sql.append("' where xh||xn||nd||sbsj='");
				sql.append(model.getKeys()[i]);
				sql.append("'");
				sqlArr[i] = sql.toString();
			}
			int[] result = dao.runBatch(sqlArr);
			return dao.checkBatch(result);
		}
	}

	/**
	 * 校办用户操作，批量审核处分信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveCfsbplxxByxb(WjcfXmlgModel model) throws Exception {
		if (model.getKeys() == null) {
			return false;
		} else {
			String[] sqlArr = new String[model.getKeys().length];
			for (int i = 0; i < model.getKeys().length; i++) {
				StringBuffer sql = new StringBuffer("update wjcfb set xndsh='");
				sql.append(model.getSh());
				sql.append("',xndclyj='");
				sql.append(model.getYj());
				sql.append("',cfsj='");
				sql.append(model.getCfsj());
				sql.append("',cfwh='");
				sql.append(model.getCfwh());
				sql.append("' where xh||xn||nd||sbsj='");
				sql.append(model.getKeys()[i]);
				sql.append("'");
				sqlArr[i] = sql.toString();
			}
			int[] result = dao.runBatch(sqlArr);
			return dao.checkBatch(result);
		}
	}

	// 审核列表
	public List<HashMap<String, String>> loadShlist() {
		return dao.getChkList(3);
	}

	/**
	 * 学院查询留校察看数据(带分页)
	 * 
	 * @param model
	 * @param colList
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryLxckResultByxy(WjcfXmlgModel model)
			throws Exception {
		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj", "xn",
				"nd", "cflb", "cfyy", "cfsj" };
		String[] queryLikeList = new String[] { "xh", "xm" };
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, queryLikeList, model);
		String sql = "select xh||xn||nd||sbsj pk,(case when xxsh='通过' or xndsh='通过' then 'disabled' "
				+ "else '' end) dis,rownum r,xn,nd,xh,xm,xymc,bjmc,xysh,xxsh,xndsh,jcsj,jcwh from view_wjcf_zjlg_lxck ";

		String[] colList = new String[] { "pk", "dis", "r", "xn", "nd", "xh",
				"xm", "bjmc", "xysh", "xxsh", "xndsh", "jcsj", "jcwh" };

		return CommonQueryDAO.commonQuery(sql, queryObject.getQueryString(),
				queryObject.getInputList(), colList, model);
	}

	/**
	 * 学校查询留校察看数据(带分页)
	 * 
	 * @param model
	 * @param colList
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryLxckResultByxx(WjcfXmlgModel model)
			throws Exception {
		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj", "xn",
				"nd", "cflb", "cfyy", "cfsj" };
		String[] queryLikeList = new String[] { "xh", "xm" };
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, queryLikeList, model);
		String sql = "select xh||xn||nd||sbsj pk,'' dis,rownum r,xn,nd,xh,xm,xymc,bjmc,xysh,xxsh,xndsh,"
				+ "jcsj,jcwh from view_wjcf_zjlg_lxck ";

		String[] colList = new String[] { "pk", "dis", "r", "xn", "nd", "xh",
				"xm", "bjmc", "xysh", "xxsh", "xndsh", "jcsj", "jcwh" };

		return CommonQueryDAO.commonQuery(sql, queryObject.getQueryString(),
				queryObject.getInputList(), colList, model);
	}

	/**
	 * 校领导查询留校察看数据(带分页)
	 * 
	 * @param model
	 * @param colList
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryLxckResultByxnd(WjcfXmlgModel model)
			throws Exception {
		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj", "xn",
				"nd", "cflb", "cfyy", "cfsj" };
		String[] queryLikeList = new String[] { "xh", "xm" };
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, queryLikeList, model);
		String sql = "select xh||cfxn||cfnd||cfsbsj pk,'' dis,rownum r,xn,nd,xh,xm,xymc,bjmc,xysh,xxsh,xndsh,"
				+ "jcsj,jcwh from view_wjcf_zjlg_lxck ";

		String[] colList = new String[] { "pk", "dis", "r", "xn", "nd", "xh",
				"xm", "bjmc", "xndsh", "jcsj", "jcwh" };

		String wheresql = " where xxsh='通过'";
		if (!StringUtils.isNull(queryObject.getQueryString())) {
			wheresql = " and xxsh='通过'";
		}

		return CommonQueryDAO.commonQuery(sql, queryObject.getQueryString()
				+ wheresql, queryObject.getInputList(), colList, model);
	}

	/**
	 * 校办查询留校察看数据(带分页)
	 * 
	 * @param model
	 * @param colList
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryLxckResultByxb(WjcfXmlgModel model)
			throws Exception {
		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj", "xn",
				"nd", "cflb", "cfyy", "cfsj" };
		String[] queryLikeList = new String[] { "xh", "xm" };
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, queryLikeList, model);
		String sql = "select xh||xn||nd||sbsj pk,'' dis,rownum r,xn,nd,xh,xm,xymc,bjmc,xysh,xxsh,xndsh,"
				+ "jcsj,jcwh from view_wjcf_zjlg_lxck ";

		String[] colList = new String[] { "pk", "dis", "r", "xn", "nd", "xh",
				"xm", "bjmc", "xysh", "xxsh", "xndsh", "jcsj", "jcwh" };

		String sqlwhere = " where xxsh='通过'";
		if (!StringUtils.isNull(queryObject.getQueryString())) {
			sqlwhere = " and xxsh='通过'";
		}

		return CommonQueryDAO.commonQuery(sql, queryObject.getQueryString()
				+ sqlwhere, queryObject.getInputList(), colList, model);
	}

	/**
	 * 仅查询处分为留校察看的处分信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryWjcfxxByLxck(WjcfXmlgModel model)
			throws Exception {
		String[] queryList = new String[] { "xh" };
		String[] likeList = new String[] { "cfsj" };
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, likeList, model);
		String sql = "select xh||xn||nd||sbsj pk,'' dis,rownum r,xn,nd,xh,xm,bjmc,cflbmc,cfyymc,cfsj,cfwh from view_wjcf a ";

		String[] colList = new String[] { "pk", "dis", "r", "xn", "xh", "xm",
				"bjmc", "cflbmc", "cfyymc", "cfsj", "cfwh" };

		String sqlwhere = " where xxsh='通过' and cfsj is not null and cfwh is not null and ssjg is null and cxwh is null and cflbmc "
				+ "like '留校察看%' and not exists(select 1 from wjcf_zjlg_lxckb b where a.xh=b.xh and a.xn=b.cfxn and a.nd=b.cfnd and a.sbsj=b.cfsbsj)";
		if (!StringUtils.isNull(queryObject.getQueryString())) {
			sqlwhere = " and xxsh='通过' and cfsj is not null and cfwh is not null and ssjg is null and cxwh is null and cflbmc like '留校察看%' "
					+ "and not exists(select 1 from wjcf_zjlg_lxckb b where a.xh=b.xh and a.xn=b.cfxn and a.nd=b.cfnd and a.sbsj=b.cfsbsj)";
		}

		return CommonQueryDAO.commonQuery(sql, queryObject.getQueryString()
				+ sqlwhere, queryObject.getInputList(), colList, model);
	}

	public List<HashMap<String, String>> queryWjcfxxByLxck() {
		String[] en = new String[] { "pk", "dis", "r", "xn", "xh", "xm",
				"bjmc", "cflbmc", "cfyymc", "cfsj", "cfwh" };
		String[] cn = new String[] { "pk", "dis", "行号", "学年", "学号", "姓名", "班级",
				"处分类别", "处分原因", "处分时间", "处分文号" };
		return dao.arrayToList(en, cn);
	}

	/**
	 * 查询留校察看表头数据
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> queryLxckTitle() {
		String[] enList = new String[] { "pk", "dis", "r", "xn", "nd", "xh",
				"xm", "bjmc", "xysh", "xxsh", "xndsh", "jcsj", "jcwh" };
		String[] cnList = new String[] { "pk", "dis", "行号", "学年", "年度", "学号",
				"姓名", "班级", Base.YXPZXY_KEY+"审核", "学校审核", "校办审核", "解除文号", "解除时间" };
		return dao.arrayToList(enList, cnList);
	}

	/**
	 * 查询留校察看表头数据
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> queryLxckTitleByxb() {
		String[] enList = new String[] { "pk", "dis", "r", "xn", "nd", "xh",
				"xm", "bjmc", "xndsh", "jcsj", "jcwh" };
		String[] cnList = new String[] { "pk", "dis", "行号", "学年", "年度", "学号",
				"姓名", "班级", "审核", "解除文号", "解除时间" };
		return dao.arrayToList(enList, cnList);
	}

	/**
	 * 查询学生留校察看信息
	 * 
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> queryStuLxckxx(String pkValue) {
		HashMap<String, String> rs = new HashMap<String, String>();
		rs = dao
				.getMapNotOut(
						"select * from (select a.sbsj,a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,a.cflbmc,a.cfyymc,cfsj,cfwh,xn,nd,b.zzmmmc,b.jg from view_wjcf a left join view_xsxxb b on a.xh=b.xh) where xh||xn||nd||sbsj=?",
						new String[] { pkValue });

		boolean isBys = StandardOperation.isBys(StringUtils
				.isNull(rs.get("xh")) ? "" : rs.get("xh"));
		String cfsj = rs.get("cfsj");
		// 如果该生是毕业生
		Calendar c = Calendar.getInstance();
		if (isBys) {
			if (!StringUtils.isNull(cfsj) && cfsj.length() == 8) {
				c.set(Integer.parseInt(cfsj.substring(0, 4)), Integer
						.parseInt(cfsj.substring(4, 6)), Integer.parseInt(cfsj
						.substring(6, 8)));
				c.add(Calendar.MONTH, 6);// 毕业生的话查看期就是半年
			}

		} else {
			if (!StringUtils.isNull(cfsj) && cfsj.length() == 8) {
				c.set(Integer.parseInt(cfsj.substring(0, 4)), Integer
						.parseInt(cfsj.substring(4, 6)), Integer.parseInt(cfsj
						.substring(6, 8)));
				c.add(Calendar.MONTH, 12);// 非毕业生查看期是一年
			}
		}
		if (!StringUtils.isNull(cfsj) && cfsj.length() == 8) {
			rs.put("lxcfsj", cfsj.substring(0, 4) + "年" + cfsj.substring(4, 6)
					+ "月" + cfsj.substring(6, 8) + "日");
		}
		rs.put("lxcksj", c.get(Calendar.YEAR)
				+ "年"
				+ (c.get(Calendar.MONTH) < 10 ? "0" + c.get(Calendar.MONTH) : c
						.get(Calendar.MONTH))
				+ "月"
				+ (c.get(Calendar.DAY_OF_MONTH) < 10 ? "0"
						+ c.get(Calendar.DAY_OF_MONTH) : c
						.get(Calendar.DAY_OF_MONTH)) + "日");
		return rs;
	}

	/**
	 * 保存留校察看申请信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveLxcksqxx(WjcfXmlgModel model) throws Exception {
		return dao
				.runUpdate(
						"insert into wjcf_zjlg_lxckb (xh,xn,nd,cfxn,cfnd,cfsbsj,cflb,cfyy,xsbx) values (?,?,?,?,?,?,?,?,?)",
						new String[] { model.getXh(), model.getXn(),
								model.getNd(), model.getCfxn(),
								model.getCfnd(), model.getCfsbsj(),
								model.getCflb(), model.getCfyy(),
								model.getXsbx() });
	}

	/**
	 * 留校察看打印
	 * 
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> lxckPrint(String pkValue) {
		HashMap<String, String> rs = new HashMap<String, String>();
		rs = dao
				.getMapNotOut(
						"select xh,xm,xb,nj,xymc,zymc,bjmc,xsbx,cfsj,cfwh,"
								+ "cflbmc,jg,zzmmmc,bjpyyj,fdyjdyj,xyyj,xxyj,xndshyj from view_wjcf_zjlg_lxck"
								+ " where xh||cfxn||cfnd||cfsbsj=?",
						new String[] { pkValue });
		boolean isBys = StandardOperation.isBys(StringUtils
				.isNull(rs.get("xh")) ? "" : rs.get("xh"));
		String cfsj = rs.get("cfsj");
		// 如果该生是毕业生
		Calendar c = Calendar.getInstance();
		if (isBys) {
			if (!StringUtils.isNull(cfsj) && cfsj.length() == 8) {
				c.set(Integer.parseInt(cfsj.substring(0, 4)), Integer
						.parseInt(cfsj.substring(4, 6)), Integer.parseInt(cfsj
						.substring(6, 8)));
				c.add(Calendar.MONTH, 6);// 毕业生的话查看期就是半年
			}

		} else {
			if (!StringUtils.isNull(cfsj) && cfsj.length() == 8) {
				c.set(Integer.parseInt(cfsj.substring(0, 4)), Integer
						.parseInt(cfsj.substring(4, 6)), Integer.parseInt(cfsj
						.substring(6, 8)));
				c.add(Calendar.MONTH, 12);// 非毕业生查看期是一年
			}
		}
		if (!StringUtils.isNull(cfsj) && cfsj.length() == 8) {
			rs.put("lxcfsj", cfsj.substring(0, 4) + "年" + cfsj.substring(4, 6)
					+ "月" + cfsj.substring(6, 8) + "日");
		}
		rs.put("lxcksj", c.get(Calendar.YEAR)
				+ "年"
				+ (c.get(Calendar.MONTH) < 10 ? "0" + c.get(Calendar.MONTH) : c
						.get(Calendar.MONTH))
				+ "月"
				+ (c.get(Calendar.DAY_OF_MONTH) < 10 ? "0"
						+ c.get(Calendar.DAY_OF_MONTH) : c
						.get(Calendar.DAY_OF_MONTH)) + "日");
		return rs;
	}

	/**
	 * 删除留校察看信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean delLxckxx(WjcfXmlgModel model) throws Exception {
		if (model.getKeys() == null) {
			return false;
		} else {
			String[] sqlArr = new String[model.getKeys().length];
			for (int i = 0; i < model.getKeys().length; i++) {
				StringBuffer sql = new StringBuffer(
						"delete from wjcf_zjlg_lxckb where xh||xn||nd||sbsj='");
				sql.append(model.getKeys()[i]);
				sql.append("'");
				sqlArr[i] = sql.toString();
			}
			int[] res = dao.runBatch(sqlArr);
			return dao.checkBatch(res);
		}
	}

	/**
	 * 查询单条留校察看信息
	 * 
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewLxck(String pkValue) {
		HashMap<String, String> rs = new HashMap<String, String>();
		rs = dao
				.getMapNotOut(
						"select xh,xm,xb,nj,xymc,zymc,bjmc,xsbx,cfsj,cfwh,"
								+ "cflbmc,jg,zzmmmc,bjpyyj,fdyjdyj,xyyj,xxyj,xndshyj,xn,nd,cfxn,cfnd,cfyymc from view_wjcf_zjlg_lxck"
								+ " where xh||xn||nd||sbsj=?",
						new String[] { pkValue });
		boolean isBys = StandardOperation.isBys(StringUtils
				.isNull(rs.get("xh")) ? "" : rs.get("xh"));
		String cfsj = rs.get("cfsj");
		// 如果该生是毕业生
		Calendar c = Calendar.getInstance();
		if (isBys) {
			if (!StringUtils.isNull(cfsj) && cfsj.length() == 8) {
				c.set(Integer.parseInt(cfsj.substring(0, 4)), Integer
						.parseInt(cfsj.substring(4, 6)), Integer.parseInt(cfsj
						.substring(6, 8)));
				c.add(Calendar.MONTH, 6);// 毕业生的话查看期就是半年
			}

		} else {
			if (!StringUtils.isNull(cfsj) && cfsj.length() == 8) {
				c.set(Integer.parseInt(cfsj.substring(0, 4)), Integer
						.parseInt(cfsj.substring(4, 6)), Integer.parseInt(cfsj
						.substring(6, 8)));
				c.add(Calendar.MONTH, 12);// 非毕业生查看期是一年
			}
		}
		if (!StringUtils.isNull(cfsj) && cfsj.length() == 8) {
			rs.put("lxcfsj", cfsj.substring(0, 4) + "年" + cfsj.substring(4, 6)
					+ "月" + cfsj.substring(6, 8) + "日");
		}
		rs.put("lxcksj", c.get(Calendar.YEAR)
				+ "年"
				+ (c.get(Calendar.MONTH) < 10 ? "0" + c.get(Calendar.MONTH) : c
						.get(Calendar.MONTH))
				+ "月"
				+ (c.get(Calendar.DAY_OF_MONTH) < 10 ? "0"
						+ c.get(Calendar.DAY_OF_MONTH) : c
						.get(Calendar.DAY_OF_MONTH)) + "日");
		return rs;
	}

	/**
	 * 修改留校察看申请信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean modiLxcksqxx(WjcfXmlgModel model) throws Exception {
		return dao.runUpdate(
				"update wjcf_zjlg_lxckb set xsbx=? where xh||xn||nd||sbsj=?",
				new String[] { model.getXsbx(), model.getPkValue() });
	}

	/**
	 * 学院用户查询留校察看单个审核信息
	 * 
	 * @param model
	 * @return
	 */
	public HashMap<String, String> queryLxckshxxByxy(WjcfXmlgModel model) {
		HashMap<String, String> rs = new HashMap<String, String>();
		rs = dao
				.getMapNotOut(
						"select xh,xm,xb,nj,xymc,zymc,bjmc,xn,nd,cfxn,cfnd,cfsj,cfwh,"
								+ "xsbx,bjpyyj,fdyjdyj,xyyj yj,xysh sh,xxsh,cflbmc,cfyymc from view_wjcf_zjlg_lxck where xh||cfxn||cfnd||cfsbsj=?",
						new String[] { model.getPkValue() });
		boolean isBys = StandardOperation.isBys(StringUtils
				.isNull(rs.get("xh")) ? "" : rs.get("xh"));
		String cfsj = rs.get("cfsj");
		// 如果该生是毕业生
		Calendar c = Calendar.getInstance();
		if (isBys) {
			if (!StringUtils.isNull(cfsj) && cfsj.length() == 8) {
				c.set(Integer.parseInt(cfsj.substring(0, 4)), Integer
						.parseInt(cfsj.substring(4, 6)), Integer.parseInt(cfsj
						.substring(6, 8)));
				c.add(Calendar.MONTH, 6);// 毕业生的话查看期就是半年
			}

		} else {
			if (!StringUtils.isNull(cfsj) && cfsj.length() == 8) {
				c.set(Integer.parseInt(cfsj.substring(0, 4)), Integer
						.parseInt(cfsj.substring(4, 6)), Integer.parseInt(cfsj
						.substring(6, 8)));
				c.add(Calendar.MONTH, 12);// 非毕业生查看期是一年
			}
		}
		if (!StringUtils.isNull(cfsj) && cfsj.length() == 8) {
			rs.put("lxcfsj", cfsj.substring(0, 4) + "年" + cfsj.substring(4, 6)
					+ "月" + cfsj.substring(6, 8) + "日");
		}
		rs.put("lxcksj", c.get(Calendar.YEAR)
				+ "年"
				+ (c.get(Calendar.MONTH) < 10 ? "0" + c.get(Calendar.MONTH) : c
						.get(Calendar.MONTH))
				+ "月"
				+ (c.get(Calendar.DAY_OF_MONTH) < 10 ? "0"
						+ c.get(Calendar.DAY_OF_MONTH) : c
						.get(Calendar.DAY_OF_MONTH)) + "日");
		return rs;
	}

	/**
	 * 学校用户查询留校察看单个审核信息
	 * 
	 * @param model
	 * @return
	 */
	public HashMap<String, String> queryLxckshxxByxx(WjcfXmlgModel model) {
		HashMap<String, String> rs = new HashMap<String, String>();
		rs = dao
				.getMapNotOut(
						"select xh,xm,xb,nj,xymc,zymc,bjmc,xn,nd,cfxn,cfnd,cfsj,cfwh,"
								+ "xsbx,bjpyyj,fdyjdyj,xxyj yj,xxsh sh,xndsh,cflbmc,cfyymc,xyyj from view_wjcf_zjlg_lxck where xh||cfxn||cfnd||cfsbsj=?",
						new String[] { model.getPkValue() });
		boolean isBys = StandardOperation.isBys(StringUtils
				.isNull(rs.get("xh")) ? "" : rs.get("xh"));
		String cfsj = rs.get("cfsj");
		// 如果该生是毕业生
		Calendar c = Calendar.getInstance();
		if (isBys) {
			if (!StringUtils.isNull(cfsj) && cfsj.length() == 8) {
				c.set(Integer.parseInt(cfsj.substring(0, 4)), Integer
						.parseInt(cfsj.substring(4, 6)), Integer.parseInt(cfsj
						.substring(6, 8)));
				c.add(Calendar.MONTH, 6);// 毕业生的话查看期就是半年
			}

		} else {
			if (!StringUtils.isNull(cfsj) && cfsj.length() == 8) {
				c.set(Integer.parseInt(cfsj.substring(0, 4)), Integer
						.parseInt(cfsj.substring(4, 6)), Integer.parseInt(cfsj
						.substring(6, 8)));
				c.add(Calendar.MONTH, 12);// 非毕业生查看期是一年
			}
		}
		if (!StringUtils.isNull(cfsj) && cfsj.length() == 8) {
			rs.put("lxcfsj", cfsj.substring(0, 4) + "年" + cfsj.substring(4, 6)
					+ "月" + cfsj.substring(6, 8) + "日");
		}
		rs.put("lxcksj", c.get(Calendar.YEAR)
				+ "年"
				+ (c.get(Calendar.MONTH) < 10 ? "0" + c.get(Calendar.MONTH) : c
						.get(Calendar.MONTH))
				+ "月"
				+ (c.get(Calendar.DAY_OF_MONTH) < 10 ? "0"
						+ c.get(Calendar.DAY_OF_MONTH) : c
						.get(Calendar.DAY_OF_MONTH)) + "日");
		return rs;
	}

	/**
	 * 学院保存留校察看审核信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveLxckshxxByxy(WjcfXmlgModel model) throws Exception {
		return dao
				.runUpdate(
						"update wjcf_zjlg_lxckb set xysh=?,xyyj=?,bjpyyj=?,fdyjdyj=? where xh||cfxn||cfnd||cfsbsj=?",
						new String[] { model.getSh(), model.getYj(),
								model.getBjpyyj(), model.getFdyjdyj(),
								model.getPkValue() });
	}

	/**
	 * 学校保存留校察看审核信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveLxckshxxByxx(WjcfXmlgModel model) throws Exception {
		return dao
				.runUpdate(
						"update wjcf_zjlg_lxckb set xxsh=?, xxyj=? where xh||cfxn||cfnd||cfsbsj=?",
						new String[] { model.getSh(), model.getYj(),
								model.getPkValue() });
	}

	/**
	 * 校办保存留校察看审核信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveLxckshxxByxb(WjcfXmlgModel model) throws Exception {
		boolean result = dao
				.runUpdate(
						"update wjcf_zjlg_lxckb set xndsh=?, xndshyj=?,jcsj=?,jcwh=?,jcjg=? where xh||cfxn||cfnd||cfsbsj=?",
						new String[] { model.getSh(), model.getYj(),
								model.getJcsj(), model.getJcwh(),
								model.getJcjg(), model.getPkValue() });
		// 更新违纪处分表信息
		if (result) {
			dao
					.runUpdate(
							"update wjcfb set cxjg=?,cxclsj=?,cxclwh=? where xh||xn||nd||sbsj=?",
							new String[] { model.getJcjg(), model.getJcsj(),
									model.getJcwh(), model.getPkValue() });
		}
		return result;
	}

	/**
	 * 批量保存留校察看信息
	 * 
	 * @param userType
	 * @param pkValues
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean savePlshLxckxx(String userType, String[] pkValues,
			WjcfXmlgModel model) throws Exception {
		if (pkValues == null) {
			return false;
		}
		String[] sqlArr = new String[pkValues.length];
		String[] updateSql = new String[pkValues.length];
		if ("xy".equalsIgnoreCase(userType)) {
			for (int i = 0; i < pkValues.length; i++) {
				StringBuffer whereSql = new StringBuffer(
						"update wjcf_zjlg_lxckb set xysh='");
				whereSql.append(model.getSh());
				whereSql.append("',xyyj='");
				whereSql.append(model.getYj());
				whereSql.append("',bjpyyj='");
				whereSql.append(model.getBjpyyj());
				whereSql.append("',fdyjdyj='");
				whereSql.append(model.getFdyjdyj());
				whereSql.append("' where xh||cfxn||cfnd||cfsbsj='");
				whereSql.append(pkValues[i]);
				whereSql.append("'");
				sqlArr[i] = whereSql.toString();
			}
		} else {
			for (int i = 0; i < pkValues.length; i++) {
				StringBuffer whereSql = new StringBuffer(
						"update wjcf_zjlg_lxckb set xxsh='");
				whereSql.append(model.getSh());
				whereSql.append("',xxyj='");
				whereSql.append(model.getYj());
				whereSql.append("' where xh||cfxn||cfnd||cfsbsj='");
				whereSql.append(pkValues[i]);
				whereSql.append("'");
				sqlArr[i] = whereSql.toString();
			}
		}
		int[] flag = dao.runBatch(sqlArr);
		// 更新违纪处分表信息
		dao.runBatch(updateSql);
		return dao.checkBatch(flag);
	}

	/**
	 * 批量保存留校察看信息
	 * 
	 * @param userType
	 * @param pkValues
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean savePlshLxckxxByxb(String[] pkValues, WjcfXmlgModel model)
			throws Exception {
		if (pkValues == null) {
			return false;
		}
		String[] sqlArr = new String[pkValues.length];
		String[] updateSql = new String[pkValues.length];

		for (int i = 0; i < pkValues.length; i++) {
			StringBuffer whereSql = new StringBuffer(
					"update wjcf_zjlg_lxckb set xndsh='");
			whereSql.append(model.getSh());
			whereSql.append("',xndshyj='");
			whereSql.append(model.getYj());
			whereSql.append("',jcsj='");
			whereSql.append(model.getJcsj());
			whereSql.append("',jcwh='");
			whereSql.append(model.getJcwh());
			whereSql.append("',jcjg='");
			whereSql.append(model.getJcjg());
			whereSql.append("' where xh||cfxn||cfnd||cfsbsj='");
			whereSql.append(pkValues[i]);
			whereSql.append("'");
			sqlArr[i] = whereSql.toString();
			// 修改处分表中的数据
			StringBuffer updaSql = new StringBuffer("update wjcfb set cxjg='");
			updaSql.append(model.getJcjg());
			updaSql.append("',cxclsj='");
			updaSql.append(model.getJcsj());
			updaSql.append("',cxclwh='");
			updaSql.append(model.getJcwh());
			updaSql.append("' where xh||xn||nd||sbsj='");
			updaSql.append(pkValues[i]);
			updaSql.append("'");
			updateSql[i] = updaSql.toString();
		}
		int[] flag = dao.runBatch(sqlArr);
		// 更新违纪处分表信息
		dao.runBatch(updateSql);
		return dao.checkBatch(flag);
	}

	/**
	 * 学校用户查询留校察看单个审核信息
	 * 
	 * @param model
	 * @return
	 */
	public HashMap<String, String> queryLxckshxxByxb(WjcfXmlgModel model) {
		HashMap<String, String> rs = new HashMap<String, String>();
		rs = dao
				.getMapNotOut(
						"select xh,xm,xb,nj,xymc,zymc,bjmc,xn,nd,cfxn,cfnd,cfsj,cfwh,"
								+ "xsbx,bjpyyj,fdyjdyj,xxyj,xxsh,xndsh sh,xndshyj yj,cflbmc,cfyymc,xyyj,jcsj,jcwh,jcjg from view_wjcf_zjlg_lxck where xh||cfxn||cfnd||cfsbsj=?",
						new String[] { model.getPkValue() });
		boolean isBys = StandardOperation.isBys(StringUtils
				.isNull(rs.get("xh")) ? "" : rs.get("xh"));
		String cfsj = rs.get("cfsj");
		// 如果该生是毕业生
		Calendar c = Calendar.getInstance();
		if (isBys) {
			if (!StringUtils.isNull(cfsj) && cfsj.length() == 8) {
				c.set(Integer.parseInt(cfsj.substring(0, 4)), Integer
						.parseInt(cfsj.substring(4, 6)), Integer.parseInt(cfsj
						.substring(6, 8)));
				c.add(Calendar.MONTH, 6);// 毕业生的话查看期就是半年
			}

		} else {
			if (!StringUtils.isNull(cfsj) && cfsj.length() == 8) {
				c.set(Integer.parseInt(cfsj.substring(0, 4)), Integer
						.parseInt(cfsj.substring(4, 6)), Integer.parseInt(cfsj
						.substring(6, 8)));
				c.add(Calendar.MONTH, 12);// 非毕业生查看期是一年
			}
		}
		if (!StringUtils.isNull(cfsj) && cfsj.length() == 8) {
			rs.put("lxcfsj", cfsj.substring(0, 4) + "年" + cfsj.substring(4, 6)
					+ "月" + cfsj.substring(6, 8) + "日");
		}
		rs.put("lxcksj", c.get(Calendar.YEAR)
				+ "年"
				+ (c.get(Calendar.MONTH) < 10 ? "0" + c.get(Calendar.MONTH) : c
						.get(Calendar.MONTH))
				+ "月"
				+ (c.get(Calendar.DAY_OF_MONTH) < 10 ? "0"
						+ c.get(Calendar.DAY_OF_MONTH) : c
						.get(Calendar.DAY_OF_MONTH)) + "日");
		return rs;
	}

	// 解除处分列表
	public List<HashMap<String, String>> loadJcList() {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String[] cn = { "解除留校察看", "维护原处分" };
		for (String s : cn) {
			HashMap<String, String> oneDataMap = new HashMap<String, String>();
			oneDataMap.put("en", s);
			oneDataMap.put("cn", s);
			list.add(oneDataMap);
		}
		return list;
	}

	/**
	 * 学院用户操作，批量审核处分信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveCfsbplxxByxy_zjcm(WjcfXmlgModel model) throws Exception {
		if (model.getKeys() == null) {
			return false;
		} else {
			String[] sqlArr = new String[model.getKeys().length];
			for (int i = 0; i < model.getKeys().length; i++) {
				StringBuffer sql = new StringBuffer("update wjcfb set xysh='");
				sql.append(model.getSh());
				sql.append("',xyclyj='");
				sql.append(model.getYj());
				sql.append("',cfsj='");
				sql.append(model.getCfsj());
				sql.append("',cfwh='");
				sql.append(model.getCfwh());
				if (!StringUtils.isNull(model.getLxcksj())) {
					sql.append("',lxcksj='");
					sql.append(model.getLxcksj());
				}
				if (!StringUtils.isNull(model.getFsjname())) {
					sql.append("',fsjname='");
					sql.append(model.getFsjname());
				}
				sql.append("' where xh||xn||sbsj='");
				sql.append(model.getKeys()[i]);
				sql.append("'");
				System.out.println(sql.toString());
				sqlArr[i] = sql.toString();
			}

			int[] result = dao.runBatch(sqlArr);
			return dao.checkBatch(result);
		}
	}

	/**
	 * 学校用户操作，批量审核处分信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveCfsbplxxByxx_zjcm(WjcfXmlgModel model) throws Exception {
		if (model.getKeys() == null) {
			return false;
		} else {
			String[] sqlArr = new String[model.getKeys().length];
			for (int i = 0; i < model.getKeys().length; i++) {
				StringBuffer sql = new StringBuffer("update wjcfb set xxsh='");
				sql.append(model.getSh());
				sql.append("',xxclyj='");
				sql.append(model.getYj());
				sql.append("',cfsj='");
				sql.append(model.getCfsj());
				sql.append("',cfwh='");
				sql.append(model.getCfwh());
				if (!StringUtils.isNull(model.getLxcksj())) {
					sql.append("',lxcksj='");
					sql.append(model.getLxcksj());
				}
				sql.append("' where xh||xn||sbsj='");
				sql.append(model.getKeys()[i]);
				sql.append("'");
				sqlArr[i] = sql.toString();
			}
			int[] result = dao.runBatch(sqlArr);
			return dao.checkBatch(result);
		}
	}

	public static void main(String... strings) {
		Calendar c = Calendar.getInstance();
		c.set(2009, 11, 20);
		c.add(Calendar.MONTH, 12);
		System.out.println(c.get(Calendar.YEAR) + "年" + c.get(Calendar.MONTH)
				+ "月" + c.get(Calendar.DAY_OF_MONTH) + "日");
	}
}
