
package xgxt.wjcf.hygxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 淮阴工学院违纪处分DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-09-23</p>
 */
public class WjcfHygxyDAO {

	DAO dao = DAO.getInstance();
	List<String> values = new ArrayList<String>();//查询条件值
	
	/**
	 * 传入二个数组来获取查询表头
	 * @param enList 英文列表
	 * @param cnList 中文列表
	 * @return
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getQueryTitle(String[] enList,
			String[] cnList) throws Exception {
		ArrayList<HashMap<String, String>> resList = new ArrayList<HashMap<String, String>>();
		if (enList != null && cnList != null) {
			for (int i = 0; i < enList.length; i++) {
				HashMap<String, String> resMap = new HashMap<String, String>();
				resMap.put("en", enList[i]);//英文字段
				resMap.put("cn", cnList[i]);//中文字段
				resList.add(resMap);
			}
		}
		return resList;
	}
	
	/**
	 * 跟踪教育查询结果
	 * @param gzjyModel
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getGzjyQueryResult(WjcfGzjyModel gzjyModel)
			throws Exception {
		ArrayList<String[]> resList = new ArrayList<String[]>();
		StringBuffer query = new StringBuffer();
		query = getProperties(gzjyModel);
		String[] opList = new String[] { "pk", "rownum", "xh", "xm", "nj",
				"xn", "xq", "bjmc", "jyzt" };
		String sql = "select xh||xn||xq||jysj pk,rownum,xh," 
					+ "xm,nj,xn,xq,bjmc,jyzt from view_wjcf_gzjy";
		resList = dao.rsToVator(sql + query.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
		return resList;
	}
	
	
	/**
	 * 跟踪教育 
	 * @param gzjyModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getProperties(WjcfGzjyModel gzjyModel) throws Exception {
		StringBuffer whereSql = new StringBuffer(" where 1=1 ");
		values = new ArrayList<String>();
		if (!StringUtils.isNull(gzjyModel.getXh())) {
			whereSql.append("and xh = ?");
			values.add(gzjyModel.getXh());
		}
		if (!StringUtils.isNull(gzjyModel.getXm())) {
			whereSql.append("and xm like ?");
			values.add("%" + gzjyModel.getXm() + "%");
		}
		if (!StringUtils.isNull(gzjyModel.getNj())) {
			whereSql.append("and nj = ?");
			values.add(gzjyModel.getNj());
		}
		if (!StringUtils.isNull(gzjyModel.getXq())) {
			whereSql.append("and xq = ?");
			values.add(gzjyModel.getXq());
		}
		if (!StringUtils.isNull(gzjyModel.getBjdm())) {
			whereSql.append("and bjdm = ?");
			values.add(gzjyModel.getBjdm());
		}
		if (!StringUtils.isNull(gzjyModel.getZydm())) {
			whereSql.append("and zydm = ?");
			values.add(gzjyModel.getZydm());
		}
		if (!StringUtils.isNull(gzjyModel.getXydm())) {
			whereSql.append("and xydm = ?");
			values.add(gzjyModel.getXydm());
		}
		if (!StringUtils.isNull(gzjyModel.getXn())) {
			whereSql.append("and xn = ?");
			values.add(gzjyModel.getXn());
		}
		if (!StringUtils.isNull(gzjyModel.getCflb())) {
			whereSql.append(" and cflb = ?");
			values.add(gzjyModel.getCflb());
		}
		if (!StringUtils.isNull(gzjyModel.getCfyy())) {
			whereSql.append(" and cfyy = ?");
			values.add(gzjyModel.getCfyy());
		}
		return whereSql;
	}
	
	/**
	 * 获取学生违纪信息
	 * @param xh
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuWjxx(String xh, String pkValue) throws Exception {
		HashMap<String, String> rsMap = new HashMap<String, String>();
		String[] opList = new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc","cflbmc","cfyymc","cfsj"};
		rsMap = dao.getMap("select xh,xm,xb,nj,xymc,zymc,bjmc,cflbmc,cfyymc,cfsj from view_wjcf where xh||xn||nd||sbsj=?", new String[]{pkValue}, opList);
		return rsMap;
	} 
	
	/**
	 * 保存跟踪教育信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveGzjy(WjcfGzjyModel model, HttpServletRequest request) throws Exception {
		return StandardOperation.insert("wjcf_gzjyb", new String[] { "xh",
				"xn", "nd", "xq", "jysj", "jyzt", "jyrbx", "bz", "cfxn",
				"cfxq", "cfsbsj", "jyr","cfpk" }, new String[] { model.getXh(),
				model.getXn(), model.getNd(), model.getXq(), model.getJysj(),
				DealString.toGBK(model.getJyzt()),
				DealString.toGBK(model.getJyrbx()),
				DealString.toGBK(model.getBz()), model.getCfxn(),
				model.getCfxq(), model.getCfsbsj(),
				DealString.toGBK(model.getJyr()), model.getCfpk() }, request);
	}
	
	/**
	 * 显示单个跟踪教育信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> viewGzjy(String pkValue) throws Exception {
		HashMap<String, String> rsMap = new HashMap<String, String>();
		String sql = "select a.*,b.cfyymc,b.cflbmc,b.cfsj from view_wjcf_gzjy a left join view_wjcf b on a.cfpk=(b.xh||b.xn||b.nd||b.sbsj) where a.xh||a.xn||a.xq||a.jysj=? ";
		String[] opList = new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc","cfyymc","cflbmc","cfsj","xn","xq","nd","jyr","jysj","jyzt","jyrbx","cfpk"};
		rsMap = dao.getMap(sql, new String[]{pkValue}, opList);
		return rsMap;
	}
	
	/**
	 * 修改保存跟踪教育信息
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean updateGzjy(WjcfGzjyModel model, String pkValue, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		if (StringUtils.isEqual(pkValue, model.getXh() + model.getCfxn()
				+ model.getXq() + model.getJysj())) {
			boolean bDel = StandardOperation.delete("wjcf_gzjyb", "xh||xn||xq||jysj", pkValue, request);
			if (bDel) {
				bFlag = StandardOperation.insert("wjcf_gzjyb", new String[] { "xh",
						"xn", "nd", "xq", "jysj", "jyzt", "jyrbx", "bz", "cfxn",
						"cfxq", "cfsbsj", "jyr","cfpk" }, new String[] { model.getXh(),
						model.getXn(), model.getNd(), model.getXq(), model.getJysj(),
						DealString.toGBK(model.getJyzt()),
						DealString.toGBK(model.getJyrbx()),
						DealString.toGBK(model.getBz()), model.getCfxn(),
						model.getCfxq(), model.getCfsbsj(),
						DealString.toGBK(model.getJyr()), model.getCfpk() }, request);
			}
		} else {
			StandardOperation.delete("wjcf_gzjyb", "xh||xn||xq||jysj", pkValue, request);
			StandardOperation.delete("wjcf_gzjyb", "xh||xn||xq||jysj", model.getXh() + model.getCfxn()
					+ model.getXq() + model.getJysj(), request);
			bFlag = StandardOperation.insert("wjcf_gzjyb", new String[] { "xh",
					"xn", "nd", "xq", "jysj", "jyzt", "jyrbx", "bz", "cfxn",
					"cfxq", "cfsbsj", "jyr","cfpk" }, new String[] { model.getXh(),
					model.getXn(), model.getNd(), model.getXq(), model.getJysj(),
					DealString.toGBK(model.getJyzt()),
					DealString.toGBK(model.getJyrbx()),
					DealString.toGBK(model.getBz()), model.getCfxn(),
					model.getCfxq(), model.getCfsbsj(),
					DealString.toGBK(model.getJyr()), model.getCfpk() }, request);
		}
		return bFlag;
	}
	public HashMap<String, String> wyhslone(String pkValue) {
		return dao.getMapNotOut("select * from view_wjcf_xsssxx where xh||xn||sssj=?", new String[]{pkValue});
	}
	
	/**
	 * 批量删除跟踪教育信息
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String deleteGzjy(String[] keys, HttpServletRequest request) throws Exception {
		String del = "";//删除未成功记录数
		for (int i = 0; i < keys.length; i++) {
			String whichpk = keys[i];// 得到主键
			boolean bFlag = StandardOperation.delete("wjcf_gzjyb", "xh||xn||xq||jysj", whichpk, request);
			if (!bFlag){//删除不成功
				del += (i+1);
				del += ",";
			}
		}
		return StringUtils.isNull(del) ? "" : del.substring(0, del.length() - 1);
	}
	
	/**
	 * 批量发文
	 * @param pkVal
	 * @param cfwh
	 * @param cfsj
	 * @return
	 * @throws Exception
	 */
	public boolean plfw(String pkVal, String cfwh, String cfsj) throws Exception{
		if (!StringUtils.isNull(pkVal)) {
			
			String[] list = pkVal.split("!");
			String[] sql = new String[list.length];
			for (int i=0;i<list.length;i++) {
				StringBuffer sqlBuf = new StringBuffer("");
				sqlBuf.append(" update wjcfb set cfsj='");
				sqlBuf.append(cfsj);
				sqlBuf.append("',cfwh='");
				sqlBuf.append(cfwh);
				sqlBuf.append("' where xh||xn||nd||sbsj='");
				sqlBuf.append(list[i]);
				sqlBuf.append("'");
				sql[i] = sqlBuf.toString();
			}
			int[] flag = dao.runBatch(sql);
			for (int i=0;i<flag.length;i++) {
				if(flag[i] <= -1){
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public List<String[]> wyhslQuery(WjcfGzjyModel model) throws Exception {
		StringBuffer whereSql = getProperties(model);
		String[] colList = new String[] {"pk","rownum","xn","xh","xm","bjmc","cflbmc","cfyymc","cfsj","cfwh","wyhsl"};
		String sql = "select a.*,rownum from (select xh||xn||sssj pk,xh,xydm,zydm,bjdm,xymc,zymc,nj,xb,xn,xm,bjmc,(select b.cflbdm from cflbdmb b where a.cflbmc=b.cflbmc) cflb,(select b.cfyydm from cfyydmb b where a.cfyymc=b.cfyymc) cfyy,cflbmc,cfyymc,cfwh,cfsj,wyhsl from view_wjcf_xsssxx a) a";
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
	}
	
	public List<HashMap<String, String>> wyhslTitle() {
		String[] enList = new String[]{"pk","rownum","xh","xh","xm","bjmc","cflbmc","cfyymc","cfsj","cfwh","wyhsl"};
		String[] cnList = new String[]{"pk","行号","学年","学号","姓名","班级","处分类别","处分原因","处分时间","处分文号","委员会受理"};
		return dao.arrayToList(enList, cnList);
	}
	
	public boolean updateWhysl(WjcfGzjyModel model, String pkValue, HttpServletRequest request) throws Exception{
		return StandardOperation.update("wjcf_xsssb", new String[] { "wyhsl",
				"wyhsllr" }, new String[] { DealString.toGBK(model.getWyhsl()),
				DealString.toGBK(model.getWyhsllr()) }, "xh||xn||sssj",
				pkValue, request);
	}
	
	/**
	 * 批量受理
	 * @param pkVal
	 * @param cfwh
	 * @param cfsj
	 * @return
	 * @throws Exception
	 */
	public boolean plsl(String pkVal, String wyhsl, String wyhsllr) throws Exception{
		if (!StringUtils.isNull(pkVal)) {
			
			String[] list = pkVal.split("!");
			String[] sql = new String[list.length];
			for (int i=0;i<list.length;i++) {
				StringBuffer sqlBuf = new StringBuffer("");
				sqlBuf.append(" update wjcf_xsssb set wyhsl='");
				sqlBuf.append(wyhsl);
				sqlBuf.append("',wyhsllr='");
				sqlBuf.append(wyhsllr);
				sqlBuf.append("' where xh||xn||sssj='");
				sqlBuf.append(list[i]);
				sqlBuf.append("'");
				sql[i] = sqlBuf.toString();
			}
			int[] flag = dao.runBatch(sql);
			for (int i=0;i<flag.length;i++) {
				if(flag[i] <= -1){
					return false;
				}
			}
			return true;
		}
		return false;
	}
}
