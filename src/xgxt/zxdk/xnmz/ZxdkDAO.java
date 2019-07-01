package xgxt.zxdk.xnmz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.GetTime;

public class ZxdkDAO extends CommDAO{
	
	// ===================������ 2011.10.11 begin==============================
	
	/**
	 * ��ȡ��������б�
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>> getShlcList(){
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select lcid,lcmc,replace(max(gzgw),',','->')gzgw   from ( ");
		sql.append(" select lcid,lcmc,to_char(wm_concat (gzgw)over( ");
		sql.append(" partition by  lcid,lcmc order by lcid, xh)) as gzgw from ( ");
		sql.append(" select a.id lcid,a.mc lcmc,b.mc gzgw,c.xh from ");
		sql.append(" xg_xtwh_splc a,xg_xtwh_spgw b,xg_xtwh_spbz c ");
		sql.append(" 	where a.id=c.splc and b.id=c.spgw and LOWER(a.djlx)='zxdk'	) a )group by lcid,lcmc ");
		
		String[]outputValue={"lcid","lcmc","gzgw"};
		return dao.getList(sql.toString(), new String[]{}, outputValue);
	}
	
	/**
	 * �ж��Ƿ�������ʱ����
	 * @return
	 */
	public String checkZxdkSqsj(){
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select count(1)num from xg_zxdk_szb where ");
		sql.append(" sqkssj<=to_char(sysdate,'yyyymmdd') ");
		sql.append(" and sqjssj>=to_char(sysdate,'yyyymmdd') ");
		
		return dao.getOneRs(sql.toString(), new String[]{}, "num");
	}
	
	/**
	 * �ж��Ƿ������ʱ����
	 * @return
	 */
	public String checkZxdkShsj(){
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select count(1)num from xg_zxdk_szb where ");
		sql.append(" shkssj<=to_char(sysdate,'yyyymmdd') ");
		sql.append(" and shjssj>=to_char(sysdate,'yyyymmdd') ");
		
		return dao.getOneRs(sql.toString(), new String[]{}, "num");
	}
	
	/**
	 * ��ȡ������Ϣ
	 */
	public HashMap<String,String>getSzMap(){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select sqkssj,sqjssj,shkssj,shjssj,splc from xg_zxdk_szb where rownum =1  ");
		
		String[]outputValue={"sqkssj","sqjssj","shkssj","shjssj","splc"};
		return dao.getMap(sql.toString(), new String[]{}, outputValue);
	}
	
	/**
	 * ��ȡѧ��������Ϣ
	 */
	public HashMap<String,String>getStuInfo(ZxdkForm model){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select to_number(to_char(sysdate,'yyyy'))- to_number(substr(csrq,0,4))nl, ");
		sql.append(" xh,xm,sfzh,mzmc,xymc,zymc,nj,bjmc,csrq,xb,xz,  ");
		sql.append(" byny,lxdh,qqhm,dzyx,jtdz,jtdh,jtyb,hkxz,pycc from view_xsxxb ");
		sql.append(" where xh=? ");
		
		String[]outputValue={"xh","xm","sfzh","mzmc","xymc","zymc","nj","bjmc","csrq","xb","xz"
				,"byny","lxdh","qqhm","dzyx","jtdz","jtdh","hkxz","nl","jtyb","pycc"};
		return dao.getMap(sql.toString(), new String[]{model.getXh()}, outputValue);
	}
	
	/**
	 * ��ȡѧ����ѧ������Ϣ
	 */
	public HashMap<String,String>getZxdkInfo(ZxdkForm model){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select a.*,'ysq' sfysq, ");
		sql.append(" case when shzt<>'δ���' then 'ysh' else 'wsh' end sfsh ");
		sql.append(" from xg_zxdk_xssq a where guid=?  and shzt<>'��ͨ��'  ");
		
		String[]outputValue={"xh", "lxrxm", "lxrjtzz", "lxrgzdw",
				"lxrgddh", "lxryddh", "jzrxm", "jzrzjh", "jzrzjlxdm", "jzrdz",
				"jzrgx", "jzryb", "jzrdh",
				"jzryj", "jtysr", "grwzdz", "dkkssj", "dknx", "dkzje",
				"onexnxfje", "onexnqsfje","onexnshf", "onexnzje", "twoxnxfje",
				"twoxnqsfje","twoxnshf", "twoxnzje", "threexnxfje", "threexnqsfje",
				"threexnshf","threexnzje", "fourxnxfje", "fourxnqsfje","fourxnshf", "fourxnzje",
				"fivexnxfje", "fivexnqsfje","fivexnshf", "fivexnzje", "sqsj", "fqxm",
				"fqsfzh", "fqgzdw", "fqlxfs", "mqxm", "mqsfzh", "mqgzdw",
				"mqlxfs","shzt","sfysq","sfsh","guid"};
		return dao.getMap(sql.toString(), new String[]{model.getGuid()}, outputValue);
	}
	
	/**
	 * ��ȡ֤������
	 */
	public List<HashMap<String,String>>getZjlx(ZxdkForm model){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select zjlxdm,zjlxmc from xg_dmk_zjlx  ");
		
		String[]outputValue={"zjlxdm","zjlxmc"};
		return dao.getList(sql.toString(), new String[]{}, outputValue);
	}
	
	/**
	 * ��ѧ������
	 * @param model
	 * @param user
	 * @param colList
	 * @param query
	 * @param inPutList
	 * @return List<String[]>
	 */
	public List<String[]>getZxdkInfo(ZxdkForm model, User user,
			String[] colList, String query, String[] inPutList)
			throws Exception{
		
		StringBuilder sql=new StringBuilder();
		
		sql.append("  select rownum r,xh,xm,xb,xymc,zymc,bjmc,nj,shzt, ");
		sql.append("  case when shzt='δ���' then '' else 'disabled' end dis,guid||'!!@@!!'||xh pkValue from");
		sql.append(" (select c.xh,c.xm,c.xb,c.xymc,c.zymc,c.bjmc,a.shzt, ");
		sql.append("  c.nj,c.xydm,c.zydm,c.bjdm,a.guid,c.mz from xg_zxdk_xssq a ");
		sql.append("  left join view_xsbfxx c on a.xh=c.xh ) a where 1=1  ");
		
		return CommonQueryDAO.commonQuery(sql.toString(), query, inPutList, colList, model);
	}
	
	
	/**
	 * ��ѧ�������
	 * @param model
	 * @param user
	 * @param colList
	 * @param query
	 * @param inPutList
	 * @return List<String[]>
	 */
	public List<String[]>getZxdkSh(ZxdkForm model, User user,
			String[] colList, String query, String[] inPutList)
			throws Exception{
		DAO dao=DAO.getInstance();
		List<String>inputV=new ArrayList<String>();
		StringBuilder sql=new StringBuilder();
		String shjb=model.getShjb();
		String gwid=model.getShgw();
		
		sql.append(" select rownum r,case when b.shzt='ͨ��' or b.shzt='��ͨ��' ");
		sql.append(" then 'disabled' else '' end disabled, ");
		sql.append(" c.xh,c.xm,c.xb,c.xymc,c.zymc,c.bjmc,c.nj,a.shzt,a.guid||'!!@@!!'||a.xh pkValue ");
		sql.append(" from ");

		if(!Base.isNull(shjb) && "1".equalsIgnoreCase(shjb)){
    		
    		sql.append(" xg_zxdk_zxdkshb ");
    	}else if(!"1".equalsIgnoreCase(shjb)){
    		int xh=Integer.parseInt(shjb);
    		xh--;
	    	sql.append(" ( select * from xg_zxdk_zxdkshb a where  xtgwid=?  ");
	    	sql.append(" and exists(select 1 from xg_zxdk_zxdkshb b where exists ");
	    	sql.append(" (select 1 from xg_xtwh_spbz c where b.xtgwid=c.spgw and xh=? ");
	    	sql.append(" and splc=(select splc from xg_zxdk_szb where rownum=1) ");
	    	sql.append(" )and shzt='ͨ��' and a.xh=b.xh )) ");
	    	
	    	
	    	inputV.add(gwid);
	    	inputV.add(String.valueOf(xh));
    	}
    	
		sql.append(" a ");
		sql.append("  left join view_xsbfxx c on a.xh=c.xh  ");
		
		sql.append(" left join(select xh,shzt,guid from xg_zxdk_zxdkshb b where exists ");
		sql.append(" (select 1 from xg_xtwh_spbz c where b.xtgwid=c.spgw and xh=? ");
		sql.append(" and splc=(select splc from xg_zxdk_szb where rownum=1) ");
		sql.append(" ))b on a.xh=b.xh and a.guid=b.guid ");
	    sql.append(" where xtgwid=?");
	    int xh=Integer.parseInt(shjb);
	    inputV.add(String.valueOf(xh+1));
	    System.out.println(sql);
		inPutList=dao.unionArray(inputV.toArray(new String[]{}), inPutList);
		return CommonQueryDAO.commonQuery(sql.toString(), query, inPutList, colList, model);
	}
	
	/**
	 * ������ѧ������˱��ʼ��Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveZxdkShInfo(ZxdkForm model) throws Exception{
		List<String[]>params=new ArrayList<String[]>();
		StringBuilder sql=new StringBuilder();
		sql.append(" insert into xg_zxdk_zxdkshb(xn,xq,nd,xh,xtgwid,shzt,shsj,shyj,sftj,shr,guid)");
		sql.append(" select a.dqxn,a.dqxq,a.dqnd,? xh,spgw,'δ���'shzt,''shsj,''shyj,''sftj,''shr,? from ");
		sql.append(" (select dqxn,dqxq,dqnd from xtszb where rownum=1)a left join ");
		sql.append(" (select spgw from xg_zxdk_szb a left join xg_xtwh_spbz b on a.splc=b.splc) b on 1=1 ");
		String[]inputV=new String[]{model.getXh(),model.getGuid()};
		params.add(inputV);
		return saveArrDate(sql.toString(), params, "xg_zxdk_zxdkshb", model.getUser());
	}
	
	public boolean delZxdkInfo(ZxdkForm model) throws Exception{
		
		String[]checkVal=model.getCheckVal();
		int length=checkVal.length;
		String [] sql=new String[length*2];
		int n=0;
		for (int i=0;i<length;i++){
			sql[n]=" delete from xg_zxdk_xssq where xh='"+checkVal[i]+"' ";
			sql[++n]=" delete from xg_zxdk_zxdkshb where xh='"+checkVal[i]+"' ";
		}
		
		return saveArrDate(sql);
		
	}
	
	public List<HashMap<String, String>> zxdkLcgz(ZxdkForm model) {
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append(" select b.mc gwmc,a.shsj,a.shr,a.shzt  from xg_zxdk_zxdkshb a, ");
		sql.append(" (select b.spgw,c.mc from xg_zxdk_szb a, ");
		sql.append(" xg_xtwh_spbz b,xg_xtwh_spgw c where a.splc=b.splc ");
		sql.append(" and b.spgw=c.id  order by b.xh)b where a.xtgwid=b.spgw ");
		sql.append(" and guid=? ");
		String[] outputValue = { "gwmc", "shsj", "shr", "shzt" };
		
		return dao.getList(sql.toString(), new String[] {model.getGuid()}, outputValue);
	}
	
	  /**
     * �����û�����ȡ��ǰģ��
     * ���漰�����û�����������λ
     * @param model
     * @return List<HashMap<String,String>>
     */
    public List<HashMap<String,String>>getUserSpgw(ZxdkForm model){
    	DAO dao=DAO.getInstance();
    	StringBuilder sql=new StringBuilder();
    	String splc=model.getSplc();
    	User user=model.getUser();
    	String userName=user.getUserName();
    	sql.append(" select c.id,c.mc,a.xh from xg_xtwh_spbz a ");
    	sql.append(" left join xg_xtwh_spgwyh b on a.spgw=b.spgw ");
    	sql.append(" left join xg_xtwh_spgw c on a.spgw=c.id  ");
    	sql.append(" where splc=? and spyh=?  ");
    	
    	String[]inputV={splc,userName};
    	String[]outPut={"id","mc","xh"};
    	return dao.getList(sql.toString(), inputV, outPut);
    }
	
    /**
     * �޸����״̬
     * @param model
     * @return
     * @throws Exception
     */
    public boolean updateShzt(ZxdkForm model) throws Exception {
		StringBuilder sql = new StringBuilder();

		sql.append(" update xg_zxdk_zxdkshb set shzt=?,shyj=?,shsj=?,shr=?");
		sql.append(" where guid =? ");
		sql.append(" and xtgwid=? ");

		List<String[]> inputV = new ArrayList<String[]>();
		String[] pkValue = model.getPrimary_key();
		String shzt = model.getShzt();
		String gwid= model.getShgw();
		String shyj= model.getShyj();
		String shsj= GetTime.getNowTime2();
		String shr = model.getUser().getUserName();
		
		for (int i = 0; i < pkValue.length; i++) {
			String[] values = {  shzt,shyj,shsj,shr,pkValue[i],gwid};
			inputV.add(values);
		}
		return saveArrDate(sql.toString(), inputV, "xg_zxdk_zxdkshb", model
				.getUser());
	}
    
    /**
     * �޸����״̬
     * @param model
     * @return
     * @throws Exception
     */
    public boolean updateJzryj(ZxdkForm model) throws Exception {
		StringBuilder sql = new StringBuilder();

		sql.append(" update xg_zxdk_xssq set jzryj=?, shzt=? ");
		sql.append(" where guid =? ");
		
		if("ͨ��".equalsIgnoreCase(model.getShzt())){
			//�����һ��
			if("0".equalsIgnoreCase(checkShjb(model))){
				model.setShzt("�����");
			}
    	}
    
		List<String[]> inputV = new ArrayList<String[]>();
		String[] pkValue = model.getPrimary_key();

		String jzryj=model.getJzryj();
		String shzt=model.getShzt();
		
		for (int i = 0; i < pkValue.length; i++) {
			String[] values = {jzryj,shzt,pkValue[i]};
			inputV.add(values);
		}
		return saveArrDate(sql.toString(), inputV, "xg_zxdk_xssq", model
				.getUser());
	}
    
    public String checkShjb(ZxdkForm model){
    	
    	DAO dao=DAO.getInstance();
    	StringBuilder sql=new StringBuilder();
    	
    	sql.append(" select count(1)num from xg_xtwh_spbz ");
    	sql.append(" where splc=(select splc from xg_zxdk_szb where rownum=1) and spgw=? ");
    	sql.append(" and xh=(select max(xh) from xg_xtwh_spbz where  ");
    	sql.append(" splc=(select splc from xg_zxdk_szb where rownum=1)) ");
    	
    	return dao.getOneRs(sql.toString(), new String[]{model.getShgw()}, "num");
    } 
    
    public String getGuid(){
    	
    	DAO dao=DAO.getInstance();
    	StringBuilder sql=new StringBuilder();
    	sql.append("  select sys_guid()guid from dual ");
    	
    	return dao.getOneRs(sql.toString(), new String[]{}, "guid");
    }
    
    public List<HashMap<String,String>>getShList(ZxdkForm model){
    	
    	DAO dao=DAO.getInstance();
    	StringBuilder sql=new StringBuilder();
    	
    	sql.append(" select xtgwid,shzt,shsj,shyj from xg_zxdk_zxdkshb where guid=? and( shzt<>'δ���' or xtgwid=?) ");
    	
    	return dao.getList(sql.toString(), new String[] { model.getGuid(),model.getShgw() },
				new String[] { "xtgwid","shzt", "shsj", "shyj" });
    }
    
    public List<HashMap<String,String>>getGwxxList(ZxdkForm model){
    	
    	DAO dao=DAO.getInstance();
    	StringBuilder sql=new StringBuilder();
    	
    	sql.append("  select spgw,b.mc from xg_xtwh_spbz a left join xg_xtwh_spgw b on a.spgw=b.id where   ");
    	sql.append(" splc=(select splc from xg_zxdk_szb where rownum='1') order by xh ");
    	
    	return dao.getList(sql.toString(), new String[] { },
				new String[] { "spgw","mc" });
    }
	// ===================������ 2011.10.11 end==============================
}
