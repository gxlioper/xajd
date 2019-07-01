package xgxt.xsxx.comm.xjyd;

import java.util.HashMap;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.gygl.gywh.DelDetectModel;

public class XsxxXjydDAO extends DAO {

	
	/**
	 * ɾ�����ʹ�ü��
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
		sql.append(" and shzt <>'δ���' ");
		sql.append(" and rownum = 1 ");
		
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = dao.getMap(sql.toString(), new String[]{},
				new String[] { "xh","ydlbm" });

		String message = "";

		if (!Base.isNull(map.get("xh"))) {
			message = "��������Ѿ������ͨ��������������е�����ʹ���ˣ�����ɾ����������ȷ�ϡ�";
		}
		return message;
	}
	/**
	 * �޸����ʹ�ü��
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
		sql.append(" and shzt <>'δ���' ");
		sql.append(" and rownum = 1 ");

		DAO dao = DAO.getInstance();
		HashMap<String, String> map = dao.getMap(sql.toString(), new String[]{},
				new String[] { "xh","ydlbm" });

		String message = "";

		if (!Base.isNull(map.get("xh"))) {
			message = "��������Ѿ������ͨ��������������е�����ʹ���ˣ������޸ģ�������ȷ�ϡ�";
		}
		return message;
	}
	/**
	 * ����춯��������˷�Χ�Ƿ����ʹ��
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
		sql.append(" and shzt <>'δ���' ");
		sql.append(" and rownum = 1 ");

		DAO dao = DAO.getInstance();
		HashMap<String, String> map = dao.getMap(sql.toString(), new String[]{},
				new String[] { "xh","ydlbm" });

		String message = "";

		if (!Base.isNull(map.get("xh"))) {
			message = "��������Ѿ������ͨ��������������е�����ʹ���ˣ��������������˷�Χ��������ȷ�ϡ�";
		}
		return message;
	}
}
