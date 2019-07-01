package xgxt.xsxx.comm.xjyd;

import java.util.HashMap;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.gygl.gywh.DelDetectModel;

public class XsxxXjydDAO extends DAO {

	
	/**
	 * 删除类别使用检测
	 * @param model
	 * @return
	 */
	public String checkDel(DelDetectModel model){
		
		CommService service = new CommService();
		String[] pkValue = model.getPkValue();
		StringBuilder sql = new StringBuilder();// SQL
		sql.append("select xh,ydlbm from bks_xjydxx ");
		sql.append("where 1 = 1 ");

		if (pkValue != null && pkValue.length > 0) {
			sql.append("and ( ");
			for (int i = 0; i < pkValue.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				//System.out.println(service.unicode2Gbk(pkValue[i]));
				sql.append(" ydlbm = '"+service.unicode2Gbk(pkValue[i])+"' ");
			}
			sql.append(")");
		}
		sql.append(" and shzt <>'未审核' ");
		sql.append(" and rownum = 1 ");
		
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = dao.getMap(sql.toString(), new String[]{},
				new String[] { "xh","ydlbm" });

		String message = "";

		if (!Base.isNull(map.get("xh"))) {
			message = "该类别下已经有审核通过或是正在审核中的申请使用了，不可删除，请重新确认。";
		}
		return message;
	}
	/**
	 * 修改类别使用检测
	 * @param model
	 * @return
	 */
	public String checkUpdate(DelDetectModel model){
		
		CommService service = new CommService();
		String[] pkValue = model.getPkValue();
		StringBuilder sql = new StringBuilder();// SQL
		sql.append("select xh,ydlbm from bks_xjydxx ");
		sql.append("where 1 = 1 ");

		if (pkValue != null && pkValue.length > 0) {
			sql.append("and ( ");
			for (int i = 0; i < pkValue.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				//System.out.println(service.unicode2Gbk(pkValue[i]));
				sql.append(" ydlbm = '"+service.unicode2Gbk(pkValue[i])+"' ");
			}
			sql.append(")");
		}
		sql.append(" and shzt <>'未审核' ");
		sql.append(" and rownum = 1 ");

		DAO dao = DAO.getInstance();
		HashMap<String, String> map = dao.getMap(sql.toString(), new String[]{},
				new String[] { "xh","ydlbm" });

		String message = "";

		if (!Base.isNull(map.get("xh"))) {
			message = "该类别下已经有审核通过或是正在审核中的申请使用了，不可修改，请重新确认。";
		}
		return message;
	}
	/**
	 * 检测异动类别设置人范围是否可以使用
	 * @param model
	 * @return
	 */
	public String checkSqrfw(DelDetectModel model){
		
		CommService service = new CommService();
		String[] pkValue = model.getPkValue();
		StringBuilder sql = new StringBuilder();// SQL
		sql.append("select xh,ydlbm from bks_xjydxx ");
		sql.append("where 1 = 1 ");

		if (pkValue != null && pkValue.length > 0) {
			sql.append("and ( ");
			for (int i = 0; i < pkValue.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				//System.out.println(service.unicode2Gbk(pkValue[i]));
				sql.append(" ydlbm = '"+service.unicode2Gbk(pkValue[i])+"' ");
			}
			sql.append(")");
		}
		sql.append(" and shzt <>'未审核' ");
		sql.append(" and rownum = 1 ");

		DAO dao = DAO.getInstance();
		HashMap<String, String> map = dao.getMap(sql.toString(), new String[]{},
				new String[] { "xh","ydlbm" });

		String message = "";

		if (!Base.isNull(map.get("xh"))) {
			message = "该类别下已经有审核通过或是正在审核中的申请使用了，不可设置申请人范围，请重新确认。";
		}
		return message;
	}
}
