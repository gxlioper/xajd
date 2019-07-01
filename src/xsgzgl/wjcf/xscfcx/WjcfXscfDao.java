		
package xsgzgl.wjcf.xscfcx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xsgzgl.wjcf.cfssgl.WjcfCfssglForm;


/**
 * <p>
 * Title: ѧ����������ϵͳ
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: ltt
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-7-17 ����12:36:30
 * </p>
 */
public class WjcfXscfDao extends CommDAO {

	/**
	 * ����������ճ̹���
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> xscfCx(WjcfXscfActionForm model)
			throws Exception {
		DAO dao = DAO.getInstance();
		return dao.rsToVator("select a.cfid,a.xn,a.xq,(case when a.cfggw is not null then  a.cfggw  else a.cflbmc end) cflbmc,a.cfyymc," +
				"(case when  a.fwjg ='wcycf' then 'ά��ԭ����' when a.fwjg='cxcf' then '��������' when a.fwjg='ggcf' then '���Ĵ���' else a.fwjg end) fwjg," +
				"a.fwwh,a.fwsj,a.cz,a.ssjg,a.jcjg,a.sfkss,a.sfksqjc,a.ssslgzr from (select cfid,xn,(select xqmc from xqdzb b where a.xq=b.xqdm) xq,cflbmc,cfyymc,cfggw," +
				"(case when jcwh is not null then jcsj when sssj is not null then sssj else cfsj end) fwsj," +
				"(case when jcwh is not null then jcwh when sswh is not null then sswh else cfwh end) fwwh," +
				"(case when jcwh is not null then '�������' when ssjg is not null then ssjg else '���ֳ���' end) fwjg,'' cz," +
				"(select ssjg from xg_wjcf_wjcfssb b where a.cfid=b.cfid) ssjg," +
				"(select sqjg from xg_wjcf_wjcfjcsqb b where a.cfid=b.cfid) jcjg," +
				"(select sfkss from xg_wjcf_cflbdmb b where a.cflbmc=b.cflbmc) sfkss," +
				"(select sfksqjc from xg_wjcf_cflbdmb b where a.cflbmc=b.cflbmc) sfksqjc," +
				"(select (to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd') - to_date(cfsj,'yyyy-mm-dd'))||'!!'||ssslgzr from xg_wjcf_cflbdmb b where a.cflbmc=b.cflbmc) ssslgzr" +
				" from xg_view_wjcf_wjcfb a where xh = ?) a order by fwsj desc",
				new String[] { model.getXh() },
				new String[] { "cfid", "xn", "xq", "cflbmc", "cfyymc", "fwsj",
						"fwwh", "fwjg", "cz", "ssjg", "jcjg", "sfkss", "sfksqjc", "ssslgzr" });
	}
	
	/**
	 * �������߱���
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean xsssSave(WjcfXscfActionForm model) throws Exception{
		DAO dao = DAO.getInstance();
		return dao.updateBlob(
				"insert into xg_wjcf_wjcfssb(fj,sqly,sqsj,cfid,ssjg,fjmc) values(?,?,?,?,'wsh',?)",
				new String[] { model.getSqly(), model.getSqsj(),
						model.getCfid(),model.getFjmc() }, model.getFj().getInputStream()
						.available(), model.getFj().getInputStream());
	}
	
	/**
	 * ������������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean cfjcSave(WjcfXscfActionForm model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.runUpdate(
						"insert into xg_wjcf_wjcfjcsqb(cfid,sqsj,sqly,sqjg) values (?,?,?,'wsh') ",
						new String[] { model.getCfid(), model.getSqsj(),
								model.getJcsqly() });
	}
	
	/**
	 * ȡ������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean xsssCx(WjcfXscfActionForm model) throws Exception{
		DAO dao = DAO.getInstance();
		return dao.runUpdate("delete from xg_wjcf_wjcfssb where cfid=?", new String[]{model.getCfid()});
	}
	
	/**
	 * ȡ���������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean jcsqCx(WjcfXscfActionForm model) throws Exception{
		DAO dao = DAO.getInstance();
		return dao.runUpdate("delete from xg_wjcf_wjcfjcsqb where cfid=?", new String[]{model.getCfid()});
	}
	
	/**
	 * ��ȡ���������б�
	 * @return
	 */
	public List<HashMap<String, String>> getJcshpList() {
		String sql = "select b.spgw from xg_wjcf_ssjcsplb a left join  xg_xtwh_spbz b  on a.jcspl = b.splc";
		DAO dao = DAO.getInstance();
		return dao.getList(sql, new String[]{}, new String[]{"spgw"});
	}
	
	/**
	 * ���ӽ�����
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean cfssshZj(List<WjcfCfssglForm> modelList) throws Exception{
		StringBuffer sql = new StringBuffer();
		DAO dao = DAO.getInstance();
		sql.append("insert into xg_wjcf_wjcfjcshb(cfid,xtgwid,shzt,sftj) values(?,?,?,?)");
		List<String[]> inputValue=new ArrayList<String[]>();
		String[] input=null;
		for (WjcfCfssglForm cf : modelList) {
			input=new String[4];
			input[0]=cf.getCfid();
			input[1]=cf.getXtgwid();
			input[2]=cf.getShzt();
			input[3]=cf.getSftj();
			inputValue.add(input);
		}
		
		int[] rows = dao.runBatch(sql.toString(), inputValue);
		boolean flag = dao.checkBatchResult(rows);
		return flag;
	}
	
	/**
	 * ɾ���������������Ϣ
	 * @param cfid
	 * @return
	 * @throws Exception
	 */
	public boolean cfsssplSc(String cfid) throws Exception{
		DAO dao = DAO.getInstance();
		return dao.runUpdate("delete from xg_wjcf_wjcfssshb where cfid=?", new String[]{cfid});
	}
	
	/**
	 * ɾ�����ֽ�������Ϣ
	 * @param cfid
	 * @return
	 * @throws Exception
	 */
	public boolean cfjcsplSc(String cfid) throws Exception{
		DAO dao = DAO.getInstance();
		return dao.runUpdate("delete from xg_wjcf_wjcfjcshb where cfid=?", new String[]{cfid});
	}
}
