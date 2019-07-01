package xgxt.qgzx.nblg;

import java.util.HashMap;

import xgxt.utils.GetTime;


/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �������ڹ���ѧģ��Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-01-14</p>
 */
public class QgzxNblgService {
	QgzxNblgDAO dao = new QgzxNblgDAO();
	
	/**
	 * ��ѯѧ����λ������Ϣ
	 * @param model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXsgwsqInfo(QgzxNblgForm model){
		HashMap<String, String> map = new HashMap<String, String>();
		map = dao.getStuInfo(model.getXh());
		map.put("gwmc", dao.getGwmc(model.getXmdm()));
		map.put("sfcjqggw", dao.checkOtherPost(model) == true ? "��" : "��");
		map.put("sfpkf", dao.isKns(model.getXh())==true ? "��" : "��");
		map.put("kcjqgzxsj", model.getKcjqgzxsj());
		map.put("bz", model.getBz());
		map.put("year", GetTime.getNowYear());
		map.put("month", GetTime.getNowMonth());
		map.put("day", GetTime.getNowDay());
		return map;
	}
	
	/**
	 * ��ѯ��λ������Ϣ
	 * @param model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getGwsqInfo(QgzxNblgForm model){
		HashMap<String, String> map = new HashMap<String, String>();
		map = dao.getStuInfo(model.getXh());
		map.put("bz", model.getBz());
		map.put("year", GetTime.getNowYear());
		map.put("month", GetTime.getNowMonth());
		map.put("day", GetTime.getNowDay());
		map.put("sqdw", model.getSqdw());
		map.put("lxdh", model.getLxdh());
		map.put("gznr", model.getGznr());
		map.put("gzsj", model.getGzsj());
		map.put("yxrs", model.getXyrs());
		map.put("sqsj", GetTime.getNowTime3());	
		map.put("gwdm", model.getGwdm());
		map.put("myqgzxsj", model.getMyqgzxsj());
		map.put("mssj", model.getMssj());
		map.put("tsyq", model.getTsyq());
		map.put("dwzp", model.getDwzp());
		map.put("xyrs", model.getXyrs());
		map.put("rylsqk", model.getRylsqk());
		map.put("bmmc", dao.getBmmc(model.getBmdm()));
		map.put("fzr", model.getFzr());
		return map;
	}
	
	/**
	 * �������ڱ�
	 * @param model
	 * */
	public void printYkqb_ser(QgzxNblgForm model){
		dao.printYkqb_db(model);
	}
	
	/**
	 * ��Ա���ܱ�
	 * @param model
	 * */
	public void printYyhz_ser(QgzxNblgForm model){
		dao.printYyhz_db(model);
	}
	
	/**
	 * �ڹ���ѧ�±���
	 * @param model
	 * */
	public void printYbb_ser(QgzxNblgForm model){
		dao.printYbb_db(model);
	}
	
}
