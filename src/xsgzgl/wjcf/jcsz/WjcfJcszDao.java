package xsgzgl.wjcf.jcsz;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

/**
 * 
 * 
 * �����ƣ�WjcfJcszDao ��������Υ�ʹ��ֻ�������Dao �����ˣ�yijd ����ʱ�䣺2012-6-19 ����09:20:00 �޸ı�ע��
 * 
 * @version
 * 
 */
public class WjcfJcszDao extends DAO {
	/**
	 * Υ�ʹ��� �������ѯ
	 * 
	 * @param model
	 * @param outputValue
	 * @return
	 * @throws Exception
	 */
	public List<String[]> cflbdmCx(WjcfJcszForm model) throws Exception {
		String sql = "  select a.*,"
				+ " rownum r from (select a.cflbdm, a.cflbmc, nvl(b.lcxx,'�������') spl,  replace(replace(replace(a.sfkss, 'no', '��������'),"
				+ " 'xs',"
				+ " 'ѧ��������'),"
				+ " 'js',"
				+ " '��ʦ������') sfkss,"
				+ " replace(replace(replace(a.sfksqjc, 'no', '���ɽ��'),"
				+ "'xs',"
				+ "  'ѧ���ɽ��'),"
				+ "  'js',"
				+ " '��ʦ�ɽ��') sfksqjc, a.ssslgzr ,"
				+ "(case when c.cflbmc is null and d.cflbdm is null then 'n' else 'y' end"
				+ " ) sfsy"
				+ " from XG_WJCF_CFLBDMB a"
				+ " left join ( select splc, mc, replace(lcxx,';','->')lcxx  from (select splc,a.mc,  to_char(WM_CONCAT(c.mc) over(partition by splc order by xh )) lcxx, xh,row_number() over(partition by splc order by xh desc ) as ddd"
				+ "  from xg_xtwh_splc a, xg_xtwh_spbz b, xg_xtwh_spgw c  where djlx = 'wjcf' and a.id = b.splc   and b.spgw = c.id) b   where ddd = 1  ) b"
//				+ " to_char(WM_CONCAT(c.mc)"
//				+ " over(partition by splc order by xh)) lcxx"
//				+ " from xg_xtwh_splc a, xg_xtwh_spbz b, xg_xtwh_spgw c"
//				+ " where djlx = 'wjcf'"
//				+ " and a.id = b.splc"
//				+ " and b.spgw = c.id)"
//				+ " group by splc) b"
				+ " on a.spl = b.splc  left join (select a.cflbmc from xg_wjcf_wjcfb a group by a.cflbmc) c on a.cflbmc=c.cflbmc"
				+ " left join (select a.cflbdm from xg_wjcf_wjcfsbb a group by a.cflbdm) d on a.cflbdm=d.cflbdm) a  ";

		String[] outputValue = new String[] { "sfsy", "cflbdm", "cflbmc",
				"spl", "sfkss", "sfksqjc", "ssslgzr" };
		MakeQuery mq = new MakeQuery();
		mq.makeQuery(new String[] {}, new String[] { "cflbmc" }, model);
		return CommonQueryDAO.commonQuery(sql + mq.getQueryString()+"order by a.cflbmc", "", mq
				.getInputList(), outputValue, model);
	}

	/**
	 * Υ�ʹ��� �������ѯ�ӿ�
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> cflbdmCx() throws Exception {
		String sql = " select cflbdm dm,cflbmc mc from xg_wjcf_cflbdmb order by cflbmc";
		String[] inputValue = new String[] {};
		String[] outputValue = new String[] { "dm", "mc" };
		return getList(sql, inputValue, outputValue);
	}
	/**
	 * Υ�ʹ��� �������ѯ�ӿ�
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> cflbmcCx() throws Exception {
		String sql = " select cflbmc dm, cflbmc mc from xg_wjcf_cflbdmb order by cflbmc";
		String[] inputValue = new String[] {};
		String[] outputValue = new String[] { "dm", "mc" };
		return getList(sql, inputValue, outputValue);
	}

	/**
	 * Υ�ʹ��� ����������
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean cflbdmZj(WjcfJcszForm model) throws Exception {
		String sql = " insert into XG_WJCF_CFLBDMB(cflbmc,spl,sfkss,sfksqjc,ssslgzr) values(?,?,?,?,?) ";
		return runUpdate(sql, new String[] { model.getCflbmc(), model.getSpl(),
				model.getSfkss(), model.getSfksqjc(), model.getSsslgzr() });
	}

	/**
	 * Υ�ʹ��� �������޸�
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean cflbdmXg(WjcfJcszForm model) throws Exception {
		String sql = " update XG_WJCF_CFLBDMB set cflbmc=?,spl=?,sfkss=?,sfksqjc=?,ssslgzr=? where cflbdm=? ";
		String[] input = new String[] { model.getCflbmc(), model.getSpl(),
				model.getSfkss(), model.getSfksqjc(), model.getSsslgzr(),
				model.getCflbdm() };
		return runUpdate(sql, input);
	}

	/**
	 * Υ�ʹ��� ������ɾ��
	 * 
	 * @param cflbdms
	 * @return
	 * @throws Exception
	 */
	public boolean cflbdmSc(List<String[]> cflbdms) throws Exception {
		String sql = " delete XG_WJCF_CFLBDMB where cflbdm=? ";
		int[] rows=runBatch(sql, cflbdms);
		boolean flag = checkBatchResult(rows);
		return flag;
	}

	/**
	 * Υ�ʹ��� ������ �鿴
	 * 
	 * @param cflbdm
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> cflbdmCk(String cflbdm) throws Exception {
		String sql = " select cflbdm,cflbmc,spl,sfkss,sfksqjc,ssslgzr from XG_WJCF_CFLBDMB where cflbdm=? ";
		String[] inputValue = new String[] { cflbdm };
		return getMapNotOut(sql, inputValue);
	}

	/**
	 * Υ�ʹ��� ԭ������ѯ
	 * 
	 * @param model
	 * @param outputValue
	 * @return
	 * @throws Exception
	 */
	public List<String[]> cfyydmCx(WjcfJcszForm model) throws Exception {
		String sql = " select a.*,rownum r from (select a.cfyydm,a.cfyymc,"
				+ " (case when b.cfyymc is null and c.cfyydm is null then 'n' else 'y' end"
				+ " ) sfsy from xg_wjcf_cfyydmb a left join (select a.cfyymc from xg_wjcf_wjcfb a group by a.cfyymc) b on a.cfyymc=b.cfyymc"
				+ " left join (select a.cfyydm from xg_wjcf_wjcfsbb a group by a.cfyydm) c on a.cfyydm=c.cfyydm) a  ";
		String[] outputValue = new String[] { "sfsy", "cfyydm", "cfyymc" };
		MakeQuery mq = new MakeQuery();
		mq.makeQuery(new String[] {}, new String[] { "cfyymc" }, model);
		return CommonQueryDAO.commonQuery(sql + mq.getQueryString()+"order by a.cfyymc", "", mq
				.getInputList(), outputValue, model);
	}

	/**
	 * Υ�ʹ��� ԭ������ѯ�ӿ�
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> cfyydmCx() throws Exception {
		String sql = " select cfyydm dm,cfyymc mc from xg_wjcf_cfyydmb order by cfyymc";
		String[] inputValue = new String[] {};
		String[] outputValue = { "dm", "mc" };
		return getList(sql, inputValue, outputValue);
	}
	
	/**
	 * Υ�ʹ��� ԭ������ѯ�ӿ�
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> cfyymcCx() throws Exception {
		String sql = " select cfyymc dm, cfyymc mc from xg_wjcf_cfyydmb order by cfyymc";
		String[] inputValue = new String[] {};
		String[] outputValue = { "dm", "mc" };
		return getList(sql, inputValue, outputValue);
	}

	/**
	 * Υ�ʹ��� ԭ���������
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean cfyydmZj(WjcfJcszForm model) throws Exception {
		String sql = " insert into xg_wjcf_cfyydmb(cfyymc) values(?) ";
		return runUpdate(sql,new String[]{model.getCfyymc()});
	}

	/**
	 * Υ�ʹ��� ԭ������޸�
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean cfyydmXg(WjcfJcszForm model) throws Exception {
		String sql = " update xg_wjcf_cfyydmb set cfyymc=? where cfyydm=? ";
		String[] input = new String[] { model.getCfyymc(), model.getCfyydm() };
		return runUpdate(sql, input);
	}

	/**
	 * Υ�ʹ��� ԭ�����ɾ��
	 * 
	 * @param cfyydms
	 * @return
	 * @throws Exception
	 */
	public boolean cfyydmSc(List<String[]> cfyydms) throws Exception {
		String sql = " delete xg_wjcf_cfyydmb where cfyydm=? ";
		int[] rows=runBatch(sql, cfyydms);
		boolean flag = checkBatchResult(rows);
		return flag;
	}

	/**
	 * Υ�ʹ��� ԭ����� �鿴
	 * 
	 * @param cfyydm
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> cfyydmCk(String cfyydm) throws Exception {
		String sql = " select cfyydm,cfyymc from xg_wjcf_cfyydmb where cfyydm=? ";
		String[] inputValue = new String[] { cfyydm };
		return getMapNotOut(sql, inputValue);
	}
	
	/**
	 * ��������������ѯ
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> ssjcsplCx(WjcfJcszForm model) throws Exception{
		String sql=" select * from (select ssspl,jcspl,rownum r," +
				"(select count(*) ssjg from xg_wjcf_wjcfssb " +
				"where ssjg in ('shz','wsh')) ssjg," +
				"(select count(*) sqjg from xg_wjcf_wjcfjcsqb " +
				"where sqjg in ('shz','wsh')) sqjg " +
				"from XG_WJCF_SSJCSPLB) a where a.r=1 ";
		return getMap(sql, new String[]{}, new String[]{"ssspl","jcspl","ssjg","sqjg"});
	}
	
	/**
	 * ������������  ����
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public boolean ssjcsplBc(WjcfJcszForm model) throws Exception{
		String sql="insert into XG_WJCF_SSJCSPLB(ssspl,jcspl) values(?,?)";
		return runUpdate(sql,new String[]{model.getSsspl(),model.getJcspl()});
	}
	
	/**
	 * ������������  ɾ����������
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public boolean ssjcsplScSy() throws Exception{
		String sql = " delete XG_WJCF_SSJCSPLB ";
		return runUpdate(sql, new String[]{});
	}
	
	/**
	 * ��ѯ��������ϱ��Ƿ����
	 * @param cflbdm
	 * @return
	 * @throws Exception
	 */
	public String cxCfsbBycflbdm(String cflbdm) throws Exception{
		return getOneRs("select count(a.cfid) cnt from xg_wjcf_wjcfshb a," +
				"xg_wjcf_wjcfsbb b where a.cfid=b.cfid and (a.shzt = 'wsh' or sftj ='0') and b.cflbdm=?", new String[]{cflbdm}, "cnt");
	}
	
	
	/**
	 * Υ�ʹ������ �Զ�������
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> cflbdmExportData(WjcfJcszForm model) throws Exception {
		String sql = "  select a.*,"
				+ " rownum r from (select a.cflbdm, a.cflbmc, nvl(b.lcxx,'�������') spl,  replace(replace(replace(a.sfkss, 'no', '��������'),"
				+ " 'xs',"
				+ " 'ѧ��������'),"
				+ " 'js',"
				+ " '��ʦ������') sfkss,"
				+ " replace(replace(replace(a.sfksqjc, 'no', '���ɽ��'),"
				+ "'xs',"
				+ "  'ѧ���ɽ��'),"
				+ "  'js',"
				+ " '��ʦ�ɽ��') sfksqjc, a.ssslgzr ,"
				+ "(case when c.cflbmc is null and d.cflbdm is null then 'n' else 'y' end"
				+ " ) sfsy"
				+ " from XG_WJCF_CFLBDMB a"
				+ " left join (select splc, replace(max(lcxx), ';', '->') lcxx"
				+ " from (select splc,"
				+ " to_char(WM_CONCAT(c.mc)"
				+ " over(partition by splc order by xh)) lcxx"
				+ " from xg_xtwh_splc a, xg_xtwh_spbz b, xg_xtwh_spgw c"
				+ " where djlx = 'wjcf'"
				+ " and a.id = b.splc"
				+ " and b.spgw = c.id)"
				+ " group by splc) b"
				+ " on a.spl = b.splc  left join (select a.cflbmc from xg_wjcf_wjcfb a group by a.cflbmc) c on a.cflbmc=c.cflbmc"
				+ " left join (select a.cflbdm from xg_wjcf_wjcfsbb a group by a.cflbdm) d on a.cflbdm=d.cflbdm) a  ";

		String[] outputValue = new String[] { "sfsy", "cflbdm", "cflbmc",
				"spl", "sfkss", "sfksqjc", "ssslgzr" };
		MakeQuery mq = new MakeQuery();
		mq.makeQuery(new String[] {}, new String[] { "cflbmc" }, model);
		return CommonQueryDAO.commonQueryforExportList(sql + mq.getQueryString()+"order by a.cflbmc", "", mq
				.getInputList(), outputValue, model);
	}
	
	/**
	 * Υ�ʹ��� ԭ������Զ�������
	 * 
	 * @param model
	 * @param outputValue
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> cfyydmExportCx(WjcfJcszForm model) throws Exception {
		String sql = " select a.*,rownum r from (select a.cfyydm,a.cfyymc,"
				+ " (case when b.cfyymc is null and c.cfyydm is null then 'n' else 'y' end"
				+ " ) sfsy from xg_wjcf_cfyydmb a left join (select a.cfyymc from xg_wjcf_wjcfb a group by a.cfyymc) b on a.cfyymc=b.cfyymc"
				+ " left join (select a.cfyydm from xg_wjcf_wjcfsbb a group by a.cfyydm) c on a.cfyydm=c.cfyydm) a  ";
		String[] outputValue = new String[] { "sfsy", "cfyydm", "cfyymc" };
		MakeQuery mq = new MakeQuery();
		mq.makeQuery(new String[] {}, new String[] { "cfyymc" }, model);
		return CommonQueryDAO.commonQueryforExportList(sql + mq.getQueryString()+"order by a.cfyymc", "", mq
				.getInputList(), outputValue, model);
	}
	
}
