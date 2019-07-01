package xgxt.pjpy.comm.zhcp.jbsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjpyCommDAO;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xsgzgl.pjpy.general.PjpyGeneralForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �ۺ����ʲ���_��������_DAO��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class ZhcpJbszDAO extends PjpyCommDAO {
	
		/**
	 * ��ø���Ա������Ϣ
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getFdyDbList(ZhcpJbszForm model,
			User user){

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// ְ����
		String zgh = user.getUserName();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();

		StringBuilder sql = new StringBuilder();

		sql.append("select b.nj,a.bjdm, b.bjmc, nvl(c.kgzt, 'yes') kgzt ");
		sql.append("from (select zgh, bjdm  from fdybjb where zgh = ? ");
		sql.append("union select zgh, bjdm from bzrbbb where zgh = ?) a ");
		sql.append("left join view_njxyzybj b on a.bjdm = b.bjdm ");
		sql.append("left join (select c.bjdm, c.kgzt from xg_pjpy_dypfszb c ");
		sql.append("where c.xn = ? and c.xq = ? and c.nd = ?) c on a.bjdm = c.bjdm ");

		DAO dao = DAO.getInstance();

		String[] inputValue = new String[] { zgh, zgh, pjxn, pjxq, pjnd };
		String[] outputValue = new String[] { "nj", "bjdm","bjmc", "kgzt" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	}

	/**
	 * ��üӷ���Ŀ�б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getJfxmList(ZhcpJbszForm model) {

		DAO dao = DAO.getInstance();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		// ѧ��
		String xh = model.getXh();
		// sql
		StringBuilder sql = new StringBuilder();
		// ����ֵ
		List<String> inputV = new ArrayList<String>();

		sql.append(" select a.* ");
		if(!Base.isNull(xh)){
			sql.append(" ,b.sqfs,b.shfs,b.sqly ");
		}
		sql.append(" from( ");
		sql.append(" select xmdm,jfdm,jfmc,jjf,xxf,sxf,");
		sql.append(" case when length(jfmc) > 6 then ");
		sql.append(" substr(jfmc, 0, 6) || '...' else ");
		sql.append(" jfmc end  showmc ");
		sql.append(" from xg_pjpy_zcxmjfb ");
		sql.append(" where xn=? ");
		sql.append(" and xq=? ");
		sql.append(" and nd=? ");
		sql.append(" ) a  ");
		
		inputV.add(pjxn);
		inputV.add(pjxq);
		inputV.add(pjnd);
		
		if (!Base.isNull(xh)) {
			sql.append(" left join (select xmdm,jfdm,sqfs, shfs, sqly ");
			sql.append(" from xg_pjpy_zcjffsb  where xn = ? ");
			sql.append(" and xq = ? and nd = ? and xh = ?) ");
			sql.append(" b on a.xmdm = b.xmdm  and a.jfdm = b.jfdm ");
			
			inputV.add(pjxn);
			inputV.add(pjxq);
			inputV.add(pjnd);
			inputV.add(xh);
		}
		
		String[] colList = null;
		
		if (Base.isNull(xh)) {
			colList = new String[] { "xmdm", "jfdm", "jfmc", "showmc", "jjf",
					"xxf", "sxf" };
		} else {
			colList = new String[] { "xmdm", "jfdm", "jfmc", "showmc", "jjf",
					"xxf", "sxf", "sqfs", "shfs", "sqly" };
		}
		 		
		return dao.getList(sql.toString(), inputV.toArray(new String[] {}),
				colList);
	}

	// ======================���� made by ΰ�����==============================
	
	// =================����made by �����������======================
	/**
	 * ��ȡ�۲���Ŀ�б�
	 * ZhcpJbszForm model
	 * return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getZcxmList(ZhcpJbszForm model){
		
		DAO dao=DAO.getInstance();
		
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		//sql
		StringBuilder sql=new StringBuilder();
		//����ֵ
		List<String>inputV=new ArrayList<String>();
		
		sql.append(" select xn,xq,nd,xmdm,xmmc,xmjb,sjdm,lyb from xg_pjpy_zcxmb ");
		sql.append(" where 1=1 ");
		sql.append(" and xn=? ");
		sql.append(" and xq=? ");
		sql.append(" and nd=? ");

		inputV.add(pjxn);
		inputV.add(pjxq);
		inputV.add(pjnd);
		
		if(!Base.isNull(model.getXmjb())){
			sql.append(" and xmjb=? ");
			inputV.add(model.getXmjb());
		}
		sql.append(" order by xmmc ");
		
		String[]colList={"xn","xq","nd","xmdm","xmmc","xmjb","sjdm","lyb"};
		return dao.getList(sql.toString(), inputV.toArray(new String[]{}), colList);
	}
	
	/**
	 * ��ȡ�۲���Ŀ�б�
	 * ZhcpJbszForm model
	 * return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getZcmrxmList(ZhcpJbszForm model){
		
		DAO dao=DAO.getInstance();
		
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		//sql
		StringBuilder sql=new StringBuilder();
		//����ֵ
		List<String>inputV=new ArrayList<String>();
		
		sql.append(" select xn,xq,nd,xmdm,xmmc,xmjb,sjdm,lyb from xg_pjpy_zcxmb ");
		sql.append(" where 1=1 and mrxm='yes' ");
		sql.append(" and xn=? ");
		sql.append(" and xq=? ");
		sql.append(" and nd=? ");

		inputV.add(pjxn);
		inputV.add(pjxq);
		inputV.add(pjnd);
		
		if(!Base.isNull(model.getXmjb())){
			sql.append(" and xmjb=? ");
			inputV.add(model.getXmjb());
		}
		sql.append(" order by xmmc ");
		
		String[]colList={"xn","xq","nd","xmdm","xmmc","xmjb","sjdm","lyb"};
		return dao.getList(sql.toString(), inputV.toArray(new String[]{}), colList);
	}
	
	/**
	 * ��ȡ�ϼ������б�
	 * ZhcpJbszForm model
	 * return HashMap<String,String>
	 */
	public List<HashMap<String,String>>getSjdmList(ZhcpJbszForm model){
		
		DAO dao=DAO.getInstance();
		
		//sql
		StringBuilder sql=new StringBuilder();
		//����ֵ
		List<String>inputV=new ArrayList<String>();
		
		
		sql.append(" select distinct(sjdm)sjdm from xg_pjpy_zcxmb where 1=1  ");
		
		if(!Base.isNull(model.getPjxn())){
			sql.append(" and xn=? ");
			inputV.add(model.getPjxn());
		}
		if(!Base.isNull(model.getPjxq())){
			sql.append(" and xq=? ");
			inputV.add(model.getPjxq());
		}
		if(!Base.isNull(model.getPjnd())){
			sql.append(" and nd=? ");
			inputV.add(model.getPjnd());
		}
		
		sql.append("  order by sjdm nulls first ");
		
		String[]colList={"sjdm"};
		return dao.getList(sql.toString(), inputV.toArray(new String[]{}), colList);
	}
	
	/**
	 * ��ȡ�۲���������б�
	 * ZhcpJbszForm model
	 * return HashMap<String,String>
	 */
	public List<HashMap<String,String>>getZcbllxList(ZhcpJbszForm model){
		
		DAO dao=DAO.getInstance();
		
		//sql
		StringBuilder sql=new StringBuilder();
		//����ֵ
		List<String>inputV=new ArrayList<String>();
		
		
		sql.append(" select bldm,blmc from xg_pjpy_zcbldmb where 1=1  ");
		sql.append(" and xn=? ");
		sql.append(" and xq=? ");
		sql.append(" and nd=? ");
		
		
		inputV.add(model.getPjxn());
		inputV.add(model.getPjxq());
		inputV.add(model.getPjnd());
		
		String[]colList={"bldm","blmc"};
		return dao.getList(sql.toString(), inputV.toArray(new String[]{}), colList);
	}
	
	/**
	 * ��ȡ�۲���������б�
	 * ZhcpJbszForm model
	 * return HashMap<String,String>
	 */
	public List<HashMap<String,String>>getZcxmxxList(ZhcpJbszForm model){
		
		DAO dao=DAO.getInstance();
		
		//sql
		StringBuilder sql=new StringBuilder();
		//����ֵ
		List<String>inputV=new ArrayList<String>();
		
		
		sql.append(" select xn,xq,nd,xmdm,xmmc,xmjb,sjdm,bldm,blmc,bl from xg_view_pjpy_zcxm where 1=1  ");
		sql.append(" and xn=? ");
		sql.append(" and xq=? ");
		sql.append(" and nd=? ");
		
		
		inputV.add(model.getPjxn());
		inputV.add(model.getPjxq());
		inputV.add(model.getPjnd());
		
		String[]colList={"xn","xq","nd","xmdm","xmmc","xmjb","sjdm","bldm","blmc","bl"};
		return dao.getList(sql.toString(), inputV.toArray(new String[]{}), colList);
	}
	
	/**
	 * �޸���Ŀ����
	 * @param model
	 * @param user
	 * @return 
	 * @throws Exception
	 */
	public boolean updateZcxmmc(ZhcpJbszForm model, User user) throws Exception {

		List<String[]> params = new ArrayList<String[]>();
		int len = model.getXmdm().length;
		String sql = " update xg_pjpy_zcxmb set xmmc=? where xmdm=? ";
		for (int i = 0; i < len; i++) {
			List<String> inputV = new ArrayList<String>();
			inputV.add(model.getXmmc()[i]);
			inputV.add(model.getXmdm()[i]);
			params.add(inputV.toArray(new String[] {}));
		}
		return saveArrDate(sql, params, "xg_pjpy_zcxmb", user);
	}
	
	/**
	 * �����۲�ѧ�ڻ�ȡ��һ����ѧ��
	 * @param model
	 * @param user
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getUpXqxx() {

		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		String xq = jbszModel.getPjxq();

		sql.append("  select b.*  from (select case ");
		sql.append(" when jb = (select min(xqjb) from xqdzb) then ");
		sql.append(" (select max(xqjb) from xqdzb) else to_char(jb-1) end xqjb ");
		sql.append(" from (select to_number(xqjb) jb from xqdzb where xqdm = ? )) a ");
		sql.append(" left join xqdzb b on a.xqjb = b.xqjb  where rownum = 1 ");
		
		return dao.getMap(sql.toString(), new String[] {xq},
				new String[] { "xqdm", "xqmc", "xqjb" });
	}
	
	/**
	 * �ж��ض������Ƿ�����۲���Ϣ
	 * @param model
	 * @param user
	 * @return
	 */
	public String checkZcByZq(String upXn,String upXq,String upNd){
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1)num from xg_pjpy_zcxmb where xn=? and xq=? and nd=? ");
		
		return dao.getOneRs(sql.toString(), new String[] {upXn,upXq,upNd}, "num");
	}
	// =================����made by �����������======================
}
