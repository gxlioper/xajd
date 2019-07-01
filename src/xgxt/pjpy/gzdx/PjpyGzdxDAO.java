package xgxt.pjpy.gzdx;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.CommonUpdata;
import xgxt.utils.MakeQuery;
import xgxt.utils.String.StringUtils;

public class PjpyGzdxDAO {

	private DAO dao = DAO.getInstance();
	
	
	/**
	 * ��ѯ����б�
	 * @return
	 */
	public List<HashMap<String, String>> getShList() {
		return dao.getChkList(3);
	}
	
	/**
	 * ��ѯѧ������ѧ��Ĵ�����Ϣ
	 * @param xh
	 * @param xn
	 * @return
	 */
	public List<String[]> queryStuwjcfxx(String xh, String xn) {
		String sql = "select xn,cflbmc,cfyymc,cfsj,cfwh from view_wjcf where" +
				" xxsh='ͨ��' and cfwh is not null";
		StringBuffer whereSql = new  StringBuffer();
		if (!StringUtils.isNull(xh)) {
			whereSql.append(" and xh='");
			whereSql.append(xh);
			whereSql.append("'");
		}
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn='");
			whereSql.append(xn);
			whereSql.append("'");
		}
		return dao.rsToVator(sql + whereSql.toString(), new String[] {},
				new String[] { "xh", "cflbmc", "cfyymc", "sfsj", "cfwh" });
	}
	
	/**
	 * ��ѯѧ���ɼ���Ϣ
	 * @param xh
	 * @param xn
	 * @return
	 */
	public List<String[]> queryStucjxx(String xh, String xn) {
		String sql = "select xn,xq,kcmc,kcxz,cj from cjb where 1=1";
		StringBuffer whereSql = new  StringBuffer();
		if (!StringUtils.isNull(xh)) {
			whereSql.append(" and xh='");
			whereSql.append(xh);
			whereSql.append("'");
		}
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn='");
			whereSql.append(xn);
			whereSql.append("'");
		}
		return dao.rsToVator(sql + whereSql.toString(), new String[] {},
				new String[] { "xh", "xq", "kcmc", "kcxz", "cj" });
	}
	
	/**
	 * ���洦��������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveCfszxx(PjpyGzdxModel model)throws Exception {
		String[] sql = new String[]{"delete from hjcfxzb","insert into " +
				"hjcfxzb(cflb) values ('"+model.getCflb()+"')"};
		int[] result = dao.runBatch(sql);
		return dao.checkBatch(result);
	}
	
	/**
	 * ��ѯ����������Ϣ
	 * @return
	 */
	public PjpyGzdxModel queryCfszxx() {
		HashMap<String, String> rs = dao.getMapNotOut("select cflb from " +
				"hjcfxzb where rownum=1", new String[]{});
		PjpyGzdxModel model = new PjpyGzdxModel();
		model.setCflb(rs.get("cflb"));
		return model;
	}
	
	/**
	 * ��ѧ��������������������б�
	 * @return
	 */
	public List<HashMap<String, String>> getRsshList() {
		String[] enList = new String[]{"ͨ��", "�޸�", "��ͨ��"};
		return dao.arrayToList(enList, enList);
	}
	
	/**
	 * ����Ӣ,���Ķ�������,�����б�
	 * @param enList
	 * @param cnList
	 * @return
	 */
	public List<HashMap<String, String>> getList(String[] enList,
			String[] cnList) {
		return dao.arrayToList(enList, cnList);
	}
	
	/**
	 * ��ѯ�ۺ����ʲ�����Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryZhszcpxx(PjpyGzdxModel model) throws Exception{
		String sql = "select xh||xn pk,rownum r,xh,xm,bjmc,xn,nvl(case when instr(to_char(xycpf),'.',1,1)=1 then '0'||to_char(xycpf) when instr(to_char(xycpf),'-',1,1)=1 and instr(to_char(xycpf),'.',2,1)=2 then replace(xycpf,'-','-0') else to_char(xycpf) end, '0') xycpf,nvl(case when instr(to_char(zhbxf),'.',1,1)=1 then '0'||to_char(zhbxf) when instr(to_char(zhbxf),'-',1,1)=1 and instr(to_char(zhbxf),'.',2,1)=2 then replace(zhbxf,'-','-0') else to_char(zhbxf) end, '0') zhbxf," +
				"nvl(case when instr(to_char(xwbxf),'.',1,1)=1 then '0'||to_char(xwbxf) when instr(to_char(xwbxf),'-',1,1)=1 and instr(to_char(xwbxf),'.',2,1)=2 then replace(xwbxf,'-','-0') else to_char(xwbxf) end, '0') xwbxf,nvl(case when instr(to_char(tcbxf),'.',1,1)=1 then '0'||to_char(tcbxf) when instr(to_char(tcbxf),'-',1,1)=1 and instr(to_char(tcbxf),'.',2,1)=2 then replace(tcbxf,'-','-0') else to_char(tcbxf) end, '0') tcbxf,nvl(case when instr(to_char(zf),'.',1,1)=1 then '0'||to_char(zf) when instr(to_char(zf),'-',1,1)=1 and instr(to_char(zf),'.',2,1)=2 then replace(zf,'-','-0') else to_char(zf) end, '0') zf,bjpm from view_gzdx_zhszcp ";
		
		String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm", "xn"};
		String[] likeList = new String[]{"xh", "xm"};
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, likeList, model);
		
		String[] colList = new String[] { "pk", "r", "xh", "xm", "bjmc", "xn",
				"xycpf", "zhbxf", "xwbxf", "tcbxf", "zf", "bjpm" };
		return CommonQueryDAO.commonQuery(sql, queryObject.getQueryString()
			, queryObject.getInputList(), colList, model);
	}
	
	/**
	 * �ۺ����ʲ����Զ�����
	 * @param xn
	 * @param xydm
	 * @return
	 * @throws Exception
	 */
	public boolean zhszcpAccount(PjpyGzdxModel model) throws Exception {
		return dao.runProcuder("{call GZDX_ZHSZCPAUTOCOUNT(?,?)}",
				new String[] { model.getXn(), model.getXydm() });
	}
	
	/**
	 * �����ۺ����ʲ�������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean addZhszcpxx(PjpyGzdxModel model) throws Exception{
		return dao.insert("insert into gzdx_zhszcpb(xh,xn,xycpf,zhbxf,xwbxf," +
				"tcbxf,zf) values (?,?,?,?,?,?,?)", new String[] {
				model.getXh(), model.getXn(), model.getXycpf(),
				model.getZhbxf(), model.getXwbxf(), model.getTcbxf(),
				model.getZf() });
	}
	
	/**
	 * �����ۺ����ʲ�������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean updateZhszcpxx(PjpyGzdxModel model) throws Exception{
		return dao
				.runUpdate(
						"update gzdx_zhszcpb set xycpf=?,zhbxf=?,xwbxf=?," +
						"tcbxf=?,zf=? where xh||xn=?",
						new String[] { model.getXycpf(), model.getZhbxf(),
								model.getXwbxf(), model.getTcbxf(),
								model.getZf(), model.getPkValue() });
	}
	
	/**
	 * ��ѯ�����ۺ����ʲ�����Ϣ
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> queryZhszcpOnexx(String pkValue) {
		return dao.getMapNotOut("select xh,xn,xm,xb,nj,xymc,zymc,bjmc," +
				"xycpf,zhbxf,xwbxf,tcbxf,zf,bjpm from view_gzdx_zhszcp " +
				"where xh||xn=?", new String[]{pkValue});
	}
	
	/**
	 * ɾ���ۺ����ʲ�����Ϣ
	 * @param keys
	 * @return
	 * @throws Excepton
	 */
	public boolean deleteZhszcpxx(String[] keys) throws Exception {
		if (keys == null) {
			return false;
		} else {
			String[] sqlArr = new String[keys.length];
			for (int i=0;i<keys.length;i++) {
				StringBuffer sql = new StringBuffer("delete from gzdx_zhszcpb " +
						"where xh||xn='");
				sql.append(keys[i]);
				sql.append("'");
				sqlArr[i] = sql.toString();
			}
			int[] result = dao.runBatch(sqlArr);
			return dao.checkBatch(result);
		}
	}
	
	/**
	 * �ۺϱ��ַ��걨�޸ı���
	 * @throws SQLException 
	 */
	public boolean dao_xsZhbxfsb(ZhbxxfModel model,String act,String strV) throws SQLException{
		DAO  dao  = DAO.getInstance();
		String pk = "xh||xn||dm||lb";
		String xh = model.getXh();
		String xn = model.getXn();
		String[] dm = model.getPldm();
		String[] lb = model.getPllb();
		String[] nr = model.getPlnr();
		String[] del = new String[lb.length];
		String[] ins = new String[lb.length];
		String[] ups = new String[lb.length];
		String[] strTempV1 = strV.split("#");
		String[] chkArr = null;
		if("modi".equalsIgnoreCase(act)){
			String strv = "";
			if (!Base.isNull(strV)){
				for(String strtemp:strTempV1){
					String[] strTempV2 =strtemp.split("!!");
					String str = xh+xn+strTempV2[1]+strTempV2[0];
					boolean flag =false;
					for(int i=0;i<lb.length;i++){
						if(str.equals(xh+xn+dm[i]+lb[i])){
							flag=true;
							break;
						}
					}
					if(!flag){
						strv+="'"+str+"',";
					}
				}				
			}							
			if(!Base.isNull(strv)){
				strv=strv.substring(0,strv.length()-1);
			}else{
				strv="''";
			}
			String[] deltem  = new String[]{"delete from zhszbxfb where "+pk+" in ("+strv+")"};
			for(int i=0;i<lb.length;i++){
				if(!Base.isNull(dm[i])){
					ups[i]="update zhszbxfb set dm='"+dm[i]+"',lb='"+lb[i]+"',nr='"+nr[i]+"' where "+pk+"='"+xh+xn+dm[i]+lb[i]+"'";
					ins[i]="insert into zhszbxfb(xh,xn,dm,lb,nr) select '"+xh+"'xh,'"+xn+"'xn,'"+dm[i]+"'dm,'"+lb[i]+"'lb,'"+nr[i]+"' nr "
					      +"from dual where (select count(*) from zhszbxfb  where "+pk+" ='"+xh+xn+dm[i]+lb[i]+"' )=0 ";					
				}
			}
			chkArr=dao.unionArray(ups,deltem);
			chkArr = dao.unionArray(chkArr,ins);
		}else{
			for(int i=0;i<lb.length;i++){
				if(!Base.isNull(dm[i])){
					del[i]=" delete from  zhszbxfb where  xh||xn='"+xh+xn+"'";
					ins[i]=" insert into zhszbxfb(xh,xn,dm,lb,nr)values('"+xh+"','"+xn+"','"+dm[i]+"','"+lb[i]+"','"+nr[i]+"')" ;
				}
			}
			chkArr=dao.unionArray(del,ins);
		}	
		int[] relArr = dao.runBatch(chkArr);
		return dao.checkBatch(relArr);
//		return true;
	}
	/**
	 * �ۺϱ��ֲַ�ѯ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> xsZhbxfQuery(ZhbxxfModel model) throws Exception{
		String sql = "select a.*,rownum r from (select a.pk,a.xn,b.nj,b.xm,b.xh,b.xb,b.xydm,b.zydm,b.bjdm,b.xymc,b.zymc,b.bjmc, " 
			        +"(select (case when count(xh)>0 then '������' else '' end)pfyf from zhszbxfb  c where c.xh||c.xn=a.pk and fs is not null)sfpf "
				    +" from (select xh||xn pk,xh,xn from zhszbxfb  group by xh,xn order by xh)a left join view_xsjbxx b on a.xh=b.xh )a ";

		String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm", "xn"};
		String[] likeList = new String[]{"xh", "xm"};
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, likeList, model);
		String[] colList = new String[] { "pk", "r", "xn","xh", "xm", "xymc", "zymc","bjmc","sfpf"};
		return CommonQueryDAO.commonQuery(sql, queryObject.getQueryString()
			, queryObject.getInputList(), colList, model);
	}
	/**
	 * ����ɾ���ۺϱ���
	 * @param pkV
	 * @throws SQLException
	 */
	public void delZhbx(String pkV) throws SQLException{
		DAO dao = DAO.getInstance();
		String[] pkvArr = pkV.split("!!");
		String[] del = new String[pkvArr.length];
		int i=0;
		for(String pkv:pkvArr){			
			del[i]="delete from zhszbxfb where xh||xn='"+pkv+"'";
			i++;
		}
		int[] relArr = dao.runBatch(del);
		dao.checkBatch(relArr);
	}
		
	/**
	 * �����ۺϱ��ַ�,�ۺϲ����ܷ�
	 * @param xycpf
	 * @param xwbxf
	 * @param tcbxf
	 * @return
	 */
	public String[] countBxfZf(String xycpf,String xwbxf,String tcbxf){
		double xy = StringUtils.isNull(xycpf) ? 0 : Double.valueOf(xycpf.trim());
		double xw = StringUtils.isNull(xwbxf) ? 0 : Double.valueOf(xwbxf.trim());
		double tc = StringUtils.isNull(tcbxf) ? 0 : Double.valueOf(tcbxf.trim());	
		xy = xy > 100 ? 0 : xy;
		xw = xw > 100 ? 0 : xw;
		tc = tc > 100 ? 0 : tc;
		HashMap<String, String> blMap = dao.getMapNotOut("select xyfbl,bxfbl," +
				"xwbxfbl,tcbxfbl from zjcm_zhszcpblszb", new String[]{});
		String[] result = new String[2];
		if (blMap != null) {
			double xbl = StringUtils.isNull(blMap.get("xyfbl")) ? 0 : Double
					.valueOf(blMap.get("xyfbl"));
			double bbl = StringUtils.isNull(blMap.get("bxfbl")) ? 0 : Double
					.valueOf(blMap.get("bxfbl"));
			double xwbl = StringUtils.isNull(blMap.get("xwbxfbl")) ? 0 : Double
					.valueOf(blMap.get("xwbxfbl"));
			double tcbl = StringUtils.isNull(blMap.get("tcbxfbl")) ? 0 : Double
					.valueOf(blMap.get("tcbxfbl"));
			double bxf = xw * xwbl / 100 + tc * tcbl / 100;
			result[0] = String.valueOf(round(bxf, 2));
			result[1] = String.valueOf(round((round(bxf, 2) * bbl / 100 
					+ round(xy, 2) * xbl / 100),2));
		}
		return result;
	}
	
	/**  
	* �ṩС��λ�������봦��  
	* @param v ��Ҫ�������������  
	* @param scale С���������λ  
	* @return ���������Ľ��  
	*/  
	public double round(double v,int scale){  
		String temp="#,##0.";  
		for (int i=0;i<scale ;i++ )  
		{  
			temp+="0";  
		}  
		return Double.valueOf(new java.text.DecimalFormat(temp).format(v));  
	}
	
	/**
	 * ͨ������ѯ��ѧ��,�����ƺ��б�
	 * @param lb
	 * @return
	 */
	public List<HashMap<String, String>> getDmList(String lb) {
		List<HashMap<String, String>> rs = new 
										ArrayList<HashMap<String,String>>();
		if ("jxj".equalsIgnoreCase(lb) || StringUtils.isNull(lb)) {
			rs = dao.getList("select jxjdm dm, jxjmc mc from jxjdmb",
					new String[] {}, new String[] { "dm", "mc" });
		} else {
			rs = dao.getList("select rychdm dm, rychmc mc from rychdmb",
					new String[] {}, new String[] { "dm", "mc" });
		}
		return rs;
	}
	
	/**
	 * �����ۺ����ʲ���������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> exportZhszcpxx(PjpyGzdxModel model) throws Exception {
		
		
		String sql = "select xh,xm,nvl(case when instr(to_char(xycpf),'.',1,1)=1 then '0'||to_char(xycpf) when instr(to_char(xycpf),'-',1,1)=1 and instr(to_char(xycpf),'.',2,1)=2 then replace(xycpf,'-','-0') else to_char(xycpf) end, '0') xycpf,nvl(case when instr(to_char(zhbxf),'.',1,1)=1 then '0'||to_char(zhbxf) when instr(to_char(zhbxf),'-',1,1)=1 and instr(to_char(zhbxf),'.',2,1)=2 then replace(zhbxf,'-','-0') else to_char(zhbxf) end, '0') zhbxf,nvl(case when instr(to_char(zf),'.',1,1)=1 then '0'||to_char(zf) when instr(to_char(zf),'-',1,1)=1 and instr(to_char(zf),'.',2,1)=2 then replace(zf,'-','-0') else to_char(zf) end, '0') zf,bjpm,bjdm from view_gzdx_zhszcp";
		String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm", "xn" };
		String[] likeList = new String[] { "xh", "xm" };
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, likeList, model);

		String[] colList = new String[] { "xh", "xm", "xycpf", "zhbxf", "zf", "bjpm" };
		return dao.rsToVator("select xh,xm,xycpf,zhbxf,zf,bjpm from (" + sql + queryObject
				.getQueryString() + ") order by bjdm,to_number(bjpm)", queryObject.getInputList(), colList);
	}
	
	/**
	 * ��ѯѧԺרҵ�༶�꼶
	 * @param model
	 * @return
	 */
	public String queryXyzynjbj(PjpyGzdxModel model) {
		String sql = "select xymc,nvl(zymc,'    ') zymc,nvl(to_char(nj),'    ') nj,nvl(bjmc,'    ') bjmc from view_njxyzybj where xydm = ?";
		StringBuffer whereSql = new StringBuffer();
		boolean b1 = false;
		boolean b2 = false;
		boolean b3 = false;
		if (!StringUtils.isNull(model.getZydm())) {
			whereSql.append(" and zydm='");
			whereSql.append(model.getZydm());
			whereSql.append("'");
			b1 = true;
		}
		if (!StringUtils.isNull(model.getNj())) {
			whereSql.append(" and nj='");
			whereSql.append(model.getNj());
			whereSql.append("'");
			b2 = true;
		}
		if (!StringUtils.isNull(model.getBjdm())) {
			whereSql.append(" and bjdm='");
			whereSql.append(model.getBjdm());
			whereSql.append("'");
			b3 = true;
		}
		sql = sql + whereSql.toString() + " and rownum=1";
		HashMap<String, String> rs = dao.getMapNotOut(sql, new String[]{model.getXydm()});
//		if (!b1 && !b2 && !b3) {
//			rs.put("zymc", "    ");
//			rs.put("nj", "    ");
//			rs.put("bjmc", "    ");
//		}
		if (!b1) {
			rs.put("zymc", "    ");
		}
		if (!b2) {
			rs.put("nj", "    ");
		}
		if (!b3) {
			rs.put("bjmc", "    ");
		}
		return rs.get("xymc") + " ѧԺ" + rs.get("zymc") + " רҵ" + rs.get("nj")
				+ "�꼶" + rs.get("bjmc") + "�༶";
	}
	
	/**
	 * ��ѯ���ϱ�����
	 * @param model
	 * @return
	 */
	public List<String[]> queryHjsbdata(PjpyGzdxModel model, String userType) throws Exception{
		String dis = "";
		if ("xy".equalsIgnoreCase(userType)) {
			dis = "disabled";
		}
		String cfjb = dao.getOneRs("select (select b.cfjb from cflbdmb b where " +
				"a.cflb=b.cflbdm) jb from hjcfxzb a", new String[]{}, "jb");
		
		String sql = "select a.*,rownum r,(case when xxsh='ͨ��' then '"+dis+"'" +
				" else '' end) dis,(case when cj > 0 or wj >0 then '#CCCCCC' " +
				"when cfxz > 0 then '#DDDDDD' else '' end) bgcolor,(case when cj >0 then '��' else '��' end) " +
				"gk,(case when wj > 0 then '��' else '��' end) cf,(case when cfxz > 0 then '��' else '��' end) wjxz from (select "
				+
				"a.*,a.xh||'!@'||a.xn||'!@'||b.jxjdm pk,b.jxjdm dm,b.xxsh,(select jxjmc from jxjdmb " +
				"c where c.jxjdm=b.jxjdm) mc from (select a.*,(select count(xh) " +
				"from cjb b where a.xh=b.xh and a.xn=b.xn and (b.cj < 60 or " +
				"b.bkcj is not null or b.cxcj is not null)) cj,(select count(xh)" +
				" from wjcfb b where a.xh=b.xh and a.xn=b.xn and b.cfwh is " +
				"not null) wj,(select count(c.xh) from (select c.xh,c.xn,c.cflb," +
				"(select d.cfjb from cflbdmb d where c.cflb=d.cflbdm) cfjb " +
				"from wjcfb c where cfwh is not null) c where a.xh=c.xh and c.xn=a.xn and c.cfjb>'"+cfjb+"') cfxz,(rank() over (partition by xn,bjdm order by " +
				"to_number(zf) desc nulls last)) bjpm from (select '"
				+ model.getXn()
				+ "' xn,a.xh,a.xm,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.xb,a.bjdm," +
						"a.bjmc,(select (nvl(case when instr(to_char(b.xycpf),'.',1,1)=1 then '0'||to_char(b.xycpf) when instr(to_char(b.xycpf),'-',1,1)=1 and instr(to_char(b.xycpf),'.',2,1)=2 then replace(b.xycpf,'-','-0') else to_char(b.xycpf) end, '0')) xycpf from gzdx_zhszcpb b where a.xh=b.xh and b.xn='"
				+ model.getXn()
				+ "') xycpf,(select (nvl(case when instr(to_char(b.zhbxf),'.',1,1)=1 then '0'||to_char(b.zhbxf) when instr(to_char(b.zhbxf),'-',1,1)=1 and instr(to_char(b.zhbxf),'.',2,1)=2 then replace(b.zhbxf,'-','-0') else to_char(b.zhbxf) end, '0')) zhbxf from gzdx_zhszcpb b where a.xh=b.xh and b.xn='"
				+ model.getXn()
				+ "') zhbxf,(select (nvl(case when instr(to_char(b.zf),'.',1,1)=1 then '0'||to_char(b.zf) when instr(to_char(b.zf),'-',1,1)=1 and instr(to_char(b.zf),'.',2,1)=2 then replace(b.zf,'-','-0') else to_char(b.zf) end, '0')) zf from gzdx_zhszcpb b where a.xh=b.xh and b.xn='"
				+ model.getXn()
				+ "') zf from view_xsjbxx a) a) a left join xsjxjb b on a.xh=b.xh and b.xn='"
				+ model.getXn() + "' order by cj,wj,bjpm) a";
		if ("rych".equalsIgnoreCase(model.getLb())) {
			sql = "select a.*,rownum r,(case when xxsh='ͨ��' then '"+dis+"'" +
			" else '' end) dis,(case when cj > 0 or wj >0 then '#CCCCCC' " +
			"when cfxz > 0 then '#DDDDDD' else '' end) bgcolor,(case when cj >0 then '��' else '��' end) " +
			"gk,(case when wj > 0 then '��' else '��' end) cf,(case when cfxz > 0 then '��' else '��' end) wjxz from (select "
			+
			"a.*,a.xh||'!@'||a.xn||'!@'||b.rychdm pk,b.rychdm dm,b.xxsh,(select rychmc from rychdmb " +
			"c where c.rychdm=b.rychdm) mc from (select a.*,(select count(xh) " +
			"from cjb b where a.xh=b.xh and a.xn=b.xn and (b.cj < 60 or " +
			"b.bkcj is not null or b.cxcj is not null)) cj,(select count(xh)" +
			" from wjcfb b where a.xh=b.xh and a.xn=b.xn and b.cfwh is " +
			"not null) wj,(select count(c.xh) from (select c.xh,c.xn,c.cflb," +
			"(select d.cfjb from cflbdmb d where c.cflb=d.cflbdm) cfjb " +
			"from wjcfb c where cfwh is not null) c where a.xh=c.xh and c.xn=a.xn and c.cfjb>'"+cfjb+"') cfxz,(rank() over (partition by xn,bjdm order by " +
			"to_number(zf) desc nulls last)) bjpm from (select '"
			+ model.getXn()
			+ "' xn,a.xh,a.xm,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.xb,a.bjdm," +
					"a.bjmc,(select (nvl(case when instr(to_char(b.xycpf),'.',1,1)=1 then '0'||to_char(b.xycpf) when instr(to_char(b.xycpf),'-',1,1)=1 and instr(to_char(b.xycpf),'.',2,1)=2 then replace(b.xycpf,'-','-0') else to_char(b.xycpf) end, '0')) xycpf from gzdx_zhszcpb b where a.xh=b.xh and b.xn='"
			+ model.getXn()
			+ "') xycpf,(select (nvl(case when instr(to_char(b.zhbxf),'.',1,1)=1 then '0'||to_char(b.zhbxf) when instr(to_char(b.zhbxf),'-',1,1)=1 and instr(to_char(b.zhbxf),'.',2,1)=2 then replace(b.zhbxf,'-','-0') else to_char(b.zhbxf) end, '0')) zhbxf from gzdx_zhszcpb b where a.xh=b.xh and b.xn='"
			+ model.getXn()
			+ "') zhbxf,(select (nvl(case when instr(to_char(b.zf),'.',1,1)=1 then '0'||to_char(b.zf) when instr(to_char(b.zf),'-',1,1)=1 and instr(to_char(b.zf),'.',2,1)=2 then replace(b.zf,'-','-0') else to_char(b.zf) end, '0')) zf from gzdx_zhszcpb b where a.xh=b.xh and b.xn='"
			+ model.getXn()
			+ "') zf from view_xsjbxx a) a) a left join xsrychb b on a.xh=b.xh and b.xn='"
			+ model.getXn() + "' order by cj,wj,bjpm) a";
		}
		
		// -----------2010/5/25 edit by luojw -------------
		String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm", "xn" };
		String[] likeList = new String[] { "xh", "xm" };
		// -------------end -----------------
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, likeList, model);
		
		String[] colList = new String[] { "pk", "dis", "bgcolor",
				"xh", "xm", "bjmc", "xn", "xycpf", "zhbxf", "zf", "bjpm", "gk",
				"cf","wjxz", "mc", "xxsh" };
		return CommonQueryDAO.commonQuery(sql, queryObject.getQueryString()
			, queryObject.getInputList(), colList, model);
	}
	
	/**
	 * ��ѯѧԺ������ѧ��ĵ�������
	 * @param model
	 * @return
	 */
	public String[] getXyxzrs(String xn, String xydm, String lb, String dm) {
		if ("jxj".equalsIgnoreCase(lb)) {
			return dao.getOneRs("select (select nvl((case when tzrs is not null then tzrs else " +
					"jyrs end),0) rs from gzdx_xyjxjrsb where xn=? and xydm=? and " +
					"key=? and dm=?) rs,(select count(*) from view_xsjxjb where xn=? and xydm=? and jxjdm=?) sbrs from dual", new String[] {
									xn,xydm,lb,dm, xn,xydm,dm }, new String[]{"rs", "sbrs"});
		} else {
			return dao.getOneRs("select (select nvl((case when tzrs is not null then tzrs else " +
					"jyrs end),0) rs from gzdx_xyjxjrsb where xn=? and xydm=? and " +
					"key=? and dm=?) rs,(select count(*) from view_xsrychb where xn=? and xydm=? and rychdm=?) sbrs from dual", new String[] {
									xn,xydm,lb,dm, xn,xydm,dm }, new String[]{"rs", "sbrs"});
		}
		
	}
	
	/**
	 * �ۺϱ������ֱ���
	 * @throws SQLException 
	 */
	public boolean dao_zhbxpf(ZhbxxfModel model) throws SQLException{
		DAO  dao  = DAO.getInstance();
		String pk = "xh||xn||dm||lb";
		String xh = model.getXh();
		String xn = model.getXn();
		String[] dm = model.getPldm();
		String[] lb = model.getPllb();
		String[] fs = model.getFs();
		String[] fslb = model.getFslb();
		String[] ups = new String[lb.length];					
		for(int i=0;i<lb.length;i++){
			if(!Base.isNull(dm[i])){
				ups[i]="update zhszbxfb set fs='"+fs[i]+"',fslb='"+fslb[i]+"' where "+pk+"='"+xh+xn+dm[i]+lb[i]+"'";							
			}
		}
	
		int[] relArr = dao.runBatch(ups);
		return dao.checkBatch(relArr);
	}
	
	/**
	 * �걨������
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveHjmdData(PjpyGzdxModel model) throws Exception {
		if (model.getCbv() == null) {
			return false;
		} else {
			String[] cbv = model.getCbv();
			String[] sqlArr = new String[cbv.length];
			if ("jxj".equalsIgnoreCase(model.getLb())) {
				for (int i = 0; i < cbv.length; i++) {
					String[] xhxnList = cbv[i].split("!@");
					if (xhxnList != null && xhxnList.length >= 2) {
						StringBuffer sql = new StringBuffer(
								"insert into xsjxjb (xh,xn,xq,jxjdm) select xh,xn,xq,jxjdm from (select '");
						sql.append(xhxnList[0]);
						sql.append("' xh,'");
						sql.append(xhxnList[1]);
						sql.append("' xn,'");
						sql.append(model.getXq());
						sql.append("' xq,'");
						sql.append(model.getDm());
						sql
								.append("' jxjdm from dual) a where not exists (select 1 from xsjxjb b where a.xh=b.xh and a.xn=b.xn and a.jxjdm=b.jxjdm)");
						sqlArr[i] = sql.toString();
					}
				}
			} else {
				for (int i = 0; i < cbv.length; i++) {
					String[] xhxnList = cbv[i].split("!@");
					if (xhxnList != null && xhxnList.length >= 2) {
						StringBuffer sql = new StringBuffer(
								"insert into xsrychb (xh,xn,xq,rychdm) select xh,xn,xq,rychdm from (select '");
						sql.append(xhxnList[0]);
						sql.append("' xh,'");
						sql.append(xhxnList[1]);
						sql.append("' xn,'");
						sql.append(model.getXq());
						sql.append("' xq,'");
						sql.append(model.getDm());
						sql
								.append("' rychdm from dual) a where not exists (select 1 from xsrychb b where a.xh=b.xh and a.xn=b.xn and a.rychdm=b.rychdm)");
						sqlArr[i] = sql.toString();
					}
				}
			}

			int[] result = dao.runBatch(sqlArr);
			return dao.checkBatch(result);
		}
	}
	
	/**
	 * ɾ��������
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean deleteHjmdData(PjpyGzdxModel model) throws Exception {
		if (model.getCbv() == null) {
			return false;
		} else {
			String[] cbv = model.getCbv();
			String[] sqlArr = new String[cbv.length];
			if ("jxj".equalsIgnoreCase(model.getLb())) {
				for (int i = 0; i < cbv.length; i++) {
					StringBuffer sql = new StringBuffer("delete from xsjxjb where xh||'!@'||xn||'!@'||jxjdm = '");
					sql.append(cbv[i]);
					sql.append("'");
					sqlArr[i] = sql.toString(); 
				}
			} else {
				for (int i = 0; i < cbv.length; i++) {
					StringBuffer sql = new StringBuffer("delete from xsrychb where xh||'!@'||xn||'!@'||rychdm = '");
					sql.append(cbv[i]);
					sql.append("'");
					sqlArr[i] = sql.toString();
				}
			}

			int[] result = dao.runBatch(sqlArr);
			return dao.checkBatch(result);
		}
	}
	
	/**
	 * ��ѯѧ���ۺ����ʲ�����Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String, String> queryStuZhszcpxx(PjpyGzdxModel model) {
		if ("jxj".equalsIgnoreCase(model.getLb())) {
			return dao.getMapNotOut("select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc," +
					"a.bjmc,(select xycpf from gzdx_zhszcpb b where a.xh=b.xh and " +
					"b.xn=?) xycpf,(select zhbxf from gzdx_zhszcpb b where a.xh=b.xh" +
					" and b.xn=?) zhbxf,(select xwbxf from gzdx_zhszcpb b where " +
					"a.xh=b.xh and b.xn=?) xwbxf,(select tcbxf from gzdx_zhszcpb b " +
					"where a.xh=b.xh and b.xn=?) tcbxf,(select zf from gzdx_zhszcpb " +
					"b where a.xh=b.xh and b.xn=?) zf,(select bjpm from " +
					"view_gzdx_zhszcp b where a.xh=b.xh and b.xn=?) bjpm," +
					"(select xxsh from xsjxjb b where a.xh=b.xh and b.xn=? a" +
					"nd b.jxjdm=?) xxsh,(select xxshyj from xsjxjb b where a.xh=b.xh and b.xn=? a" +
					"nd b.jxjdm=?) xxyj from view_xsjbxx a where xh=?",
							new String[] { model.getXn(), model.getXn(),
									model.getXn(), model.getXn(), model.getXn(),
									model.getXn(), model.getXn(), model.getDm(),
									model.getXn(), model.getDm(),
									model.getXh() });
		} else {
			return dao.getMapNotOut("select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc," +
					"a.bjmc,(select xycpf from gzdx_zhszcpb b where a.xh=b.xh and " +
					"b.xn=?) xycpf,(select zhbxf from gzdx_zhszcpb b where a.xh=b.xh" +
					" and b.xn=?) zhbxf,(select xwbxf from gzdx_zhszcpb b where " +
					"a.xh=b.xh and b.xn=?) xwbxf,(select tcbxf from gzdx_zhszcpb b " +
					"where a.xh=b.xh and b.xn=?) tcbxf,(select zf from gzdx_zhszcpb " +
					"b where a.xh=b.xh and b.xn=?) zf,(select bjpm from " +
					"view_gzdx_zhszcp b where a.xh=b.xh and b.xn=?) bjpm," +
					"(select xxsh from xsrychb b where a.xh=b.xh and b.xn=? a" +
					"nd b.rychdm=?) xxsh,(select xxyj from xsrychb b where a.xh=b.xh and b.xn=? a" +
					"nd b.rychdm=?) xxyj from view_xsjbxx a where xh=?",
							new String[] { model.getXn(), model.getXn(),
									model.getXn(), model.getXn(), model.getXn(),
									model.getXn(), model.getXn(), model.getDm(),
									model.getXn(), model.getDm(),
									model.getXh() });
		}
	}
	
	/**
	 * ����������걨����
	 * @param model
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean saveHjsbData(PjpyGzdxModel model, String pkValue)
			throws Exception {
		if ("add".equalsIgnoreCase(model.getKey())) {
			if ("jxj".equalsIgnoreCase(model.getLb())) {
				return dao.runUpdate(
						"insert into xsjxjb(xh,xn,xq,jxjdm) values (?,?,?,?)",
						new String[] { model.getXh(), model.getXn(),
								model.getXq(), model.getDm() });
			} else {
				return dao
						.runUpdate(
								"insert into xsrychb(xh,xn,xq,rychdm) values (?,?,?,?)",
								new String[] { model.getXh(), model.getXn(),
										model.getXq(), model.getDm() });
			}
		} else {
			if ("jxj".equalsIgnoreCase(model.getLb())) {
				return dao
						.runUpdate(
								"update xsjxjb set jxjdm=? where xh||'!@'||xn||'!@'||jxjdm=?",
								new String[] { model.getDm(), pkValue });
			} else {
				return dao
						.runUpdate(
								"update xsrychb set rychdm=? where xh||'!@'||xn||'!@'||rychdm=?",
								new String[] { model.getDm(), pkValue });
			}
		}
	}
	
	/**
	 * ��ѯѧ����ѧ�������ƺŻ���Ϣ
	 * @param pkValue  xh||xn||jxjdm , xh||xn||rychdm
	 * @param lb
	 * @return
	 */
	public HashMap<String, String> viewJxjRychresult(String pkValue, String lb) {
		if ("jxj".equalsIgnoreCase(lb)) {
			return dao.getMapNotOut("select a.*,(select bjpm from view_gzdx_zhszcp b where a.xh=b.xh and a.xn=b.xn) bjpm" +
					" from (select xh,(select xm from view_xsjbxx b where a.xh=b.xh) xm,(select xb from view_xsjbxx b where a.xh=b.xh) xb,(select nj from view_xsjbxx b where a.xh=b.xh) nj,(select xymc from view_xsjbxx b where a.xh=b.xh) xymc,(select zymc from view_xsjbxx b where a.xh=b.xh) zymc,(select bjmc from view_xsjbxx b where a.xh=b.xh) bjmc,(select bjdm from view_xsjbxx b where a.xh=b.xh) bjdm,xn,jxjdm,(select jxjmc from jxjdmb b where a.jxjdm=b.jxjdm) jxjmc,xxsh,xxshyj yj,jxjdm dm,(select jxjmc from jxjdmb b where a.jxjdm=b.jxjdm) mc," +
					"(select b.xycpf from gzdx_zhszcpb b where a.xh=b.xh and a.xn=b.xn) xycpf," +
					"(select b.zhbxf from gzdx_zhszcpb b where a.xh=b.xh and a.xn=b.xn) zhbxf," +
					"(select b.xwbxf from gzdx_zhszcpb b where a.xh=b.xh and a.xn=b.xn) xwbxf," +
					"(select b.tcbxf from gzdx_zhszcpb b where a.xh=b.xh and a.xn=b.xn) tcbxf," +
					"(select b.zf from gzdx_zhszcpb b where a.xh=b.xh and a.xn=b.xn) zf" +
					" from xsjxjb a) a where xh||xn||jxjdm=?", new String[]{pkValue});
		} else {
			return dao.getMapNotOut("select a.*,(select bjpm from view_gzdx_zhszcp b where a.xh=b.xh and a.xn=b.xn) bjpm" +
					" from (select xh,(select xm from view_xsjbxx b where a.xh=b.xh) xm,(select xb from view_xsjbxx b where a.xh=b.xh) xb,(select nj from view_xsjbxx b where a.xh=b.xh) nj,(select xymc from view_xsjbxx b where a.xh=b.xh) xymc,(select zymc from view_xsjbxx b where a.xh=b.xh) zymc,(select bjmc from view_xsjbxx b where a.xh=b.xh) bjmc,(select bjdm from view_xsjbxx b where a.xh=b.xh) bjdm,xn,rychdm,(select rychmc from rychdmb b where a.rychdm=b.rychdm) rychmc,xxsh,xxyj yj,rychdm dm,(select rychmc from rychdmb b where a.rychdm=b.rychdm) mc," +
					"(select b.xycpf from gzdx_zhszcpb b where a.xh=b.xh and a.xn=b.xn) xycpf," +
					"(select b.zhbxf from gzdx_zhszcpb b where a.xh=b.xh and a.xn=b.xn) zhbxf," +
					"(select b.xwbxf from gzdx_zhszcpb b where a.xh=b.xh and a.xn=b.xn) xwbxf," +
					"(select b.tcbxf from gzdx_zhszcpb b where a.xh=b.xh and a.xn=b.xn) tcbxf," +
					"(select b.zf from gzdx_zhszcpb b where a.xh=b.xh and a.xn=b.xn) zf" +
					" from xsrychb a) a where xh||xn||rychdm=?", new String[]{pkValue});
		}
	}
	
	/**
	 * ��ѧ�������ƺŽ����ѯ��ע�������ѧԺ�û���ѯ����ô���ѧУ���ͨ������ô�Ͳ����޸ģ�ѧУ�û���ѯ���ÿ��ơ�
	 * @param lb jxj,rycy
	 * @param userType xy,xx
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryJxjRychResult(String lb,String userType,PjpyGzdxModel model) throws Exception {
		if ("jxj".equalsIgnoreCase(lb)) {
			if ("xy".equalsIgnoreCase(userType)) {
				String sql = "select xh||xn||jxjdm pk,(case when xxsh='ͨ��' then 'disabled' else '' end) dis,rownum r,xh,xm,bjmc,xn,jxjmc,jlje,xxsh from view_xsjxjb ";
	
				String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm", "xn", "jxjdm"};
				String[] likeList = new String[]{"xh", "xm"};
				MakeQuery queryObject = new MakeQuery();
				queryObject.makeQuery(queryList, likeList, model);
				
				String[] colList = new String[] { "pk", "dis", "r", "xh", "xm", "bjmc", "xn",
						"jxjmc", "jlje", "xxsh" };
				return CommonQueryDAO.commonQuery(sql, queryObject.getQueryString()
					, queryObject.getInputList(), colList, model);
			} else {
				String sql = "select xh||xn||jxjdm pk,'' dis,rownum r,xh,xm,bjmc,xn,jxjmc,jlje,xxsh from view_xsjxjb ";
				
				String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm", "xn", "jxjdm"};
				String[] likeList = new String[]{"xh", "xm"};
				MakeQuery queryObject = new MakeQuery();
				queryObject.makeQuery(queryList, likeList, model);
				
				String[] colList = new String[] { "pk", "dis", "r", "xh", "xm", "bjmc", "xn",
						"jxjmc", "jlje", "xxsh" };
				return CommonQueryDAO.commonQuery(sql, queryObject.getQueryString()
					, queryObject.getInputList(), colList, model);
			}
		} else {
			if ("xy".equalsIgnoreCase(userType)) {
				String sql = "select xh||xn||rychdm pk,(case when xxsh='ͨ��' then 'disabled' else '' end) dis,rownum r,xh,xm,bjmc,xn,rychmc,xxsh from view_xsrychb ";
				
				String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm", "xn", "rychdm"};
				String[] likeList = new String[]{"xh", "xm"};
				MakeQuery queryObject = new MakeQuery();
				queryObject.makeQuery(queryList, likeList, model);
				
				String[] colList = new String[] { "pk", "dis", "r", "xh", "xm", "bjmc", "xn",
						"rychmc", "xxsh" };
				return CommonQueryDAO.commonQuery(sql, queryObject.getQueryString()
					, queryObject.getInputList(), colList, model);
			} else {
				String sql = "select xh||xn||rychdm pk,'' dis,rownum r,xh,xm,bjmc,xn,rychmc,xxsh from view_xsrychb ";
				
				String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm", "xn", "rychdm"};
				String[] likeList = new String[]{"xh", "xm"};
				MakeQuery queryObject = new MakeQuery();
				queryObject.makeQuery(queryList, likeList, model);
				
				String[] colList = new String[] { "pk", "dis", "r", "xh", "xm", "bjmc", "xn",
						"rychmc", "xxsh" };
				return CommonQueryDAO.commonQuery(sql, queryObject.getQueryString()
					, queryObject.getInputList(), colList, model);
			}
		}
	}
	
	
	
	/**
	 * ������ӿƼ�ְҵѧԺ
	 * ���칤��ְҵѧԺ
	 * ��ѧ�������ƺŽ����ѯ
	 * @param lb
	 * @param userType
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryJxjRychResultForCq(String lb,String userType,PjpyGzdxModel model) throws Exception {
		if ("jxj".equalsIgnoreCase(lb)) {
			if ("fdy".equalsIgnoreCase(userType)) {
				String sql = "select xh||xn||jxjdm pk,(case when xysh='ͨ��' then 'disabled' else '' end) dis,rownum r,xh,xm,bjmc,xn,jxjmc,jlje,fdysh,xysh,xxsh from view_xsjxjb ";
				
				String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm", "xn", "jxjdm"};
				String[] likeList = new String[]{"xh", "xm"};
				MakeQuery queryObject = new MakeQuery();
				queryObject.makeQuery(queryList, likeList, model);
				
				String[] colList = new String[] { "pk", "dis", "r", "xh", "xm", "bjmc", "xn",
						"jxjmc", "jlje", "fdysh","xysh","xxsh" };
				return CommonQueryDAO.commonQuery(sql, queryObject.getQueryString()
					, queryObject.getInputList(), colList, model);
			} else if ("xy".equalsIgnoreCase(userType)) {
				String sql = "select xh||xn||jxjdm pk,(case when xxsh='ͨ��' then 'disabled' else '' end) dis,rownum r,xh,xm,bjmc,xn,jxjmc,jlje,fdysh,xysh,xxsh from view_xsjxjb ";
	
				String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm", "xn", "jxjdm"};
				String[] likeList = new String[]{"xh", "xm"};
				MakeQuery queryObject = new MakeQuery();
				queryObject.makeQuery(queryList, likeList, model);
				
				String[] colList = new String[] { "pk", "dis", "r", "xh", "xm", "bjmc", "xn",
						"jxjmc", "jlje",  "fdysh","xysh","xxsh" };
				return CommonQueryDAO.commonQuery(sql, queryObject.getQueryString()
					, queryObject.getInputList(), colList, model);
			} else {
				String sql = "select xh||xn||jxjdm pk,'' dis,rownum r,xh,xm,bjmc,xn,jxjmc,jlje,fdysh,xysh,xxsh from view_xsjxjb ";
				
				String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm", "xn", "jxjdm"};
				String[] likeList = new String[]{"xh", "xm"};
				MakeQuery queryObject = new MakeQuery();
				queryObject.makeQuery(queryList, likeList, model);
				
				String[] colList = new String[] { "pk", "dis", "r", "xh", "xm", "bjmc", "xn",
						"jxjmc", "jlje", "fdysh","xysh","xxsh"};
				return CommonQueryDAO.commonQuery(sql, queryObject.getQueryString()
					, queryObject.getInputList(), colList, model);
			}
		} else {
			if ("fdy".equalsIgnoreCase(userType)) {
				String sql = "select xh||xn||rychdm pk,(case when xysh='ͨ��' then 'disabled' else '' end) dis,rownum r,xh,xm,bjmc,xn,rychmc,fdysh,xysh,xxsh from view_xsrychb ";
				
				String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm", "xn", "rychdm"};
				String[] likeList = new String[]{"xh", "xm"};
				MakeQuery queryObject = new MakeQuery();
				queryObject.makeQuery(queryList, likeList, model);
				
				String[] colList = new String[] { "pk", "dis", "r", "xh", "xm", "bjmc", "xn",
						"rychmc",  "fdysh","xysh","xxsh"};
				return CommonQueryDAO.commonQuery(sql, queryObject.getQueryString()
					, queryObject.getInputList(), colList, model);
			}else if ("xy".equalsIgnoreCase(userType)) {
				String sql = "select xh||xn||rychdm pk,(case when xxsh='ͨ��' then 'disabled' else '' end) dis,rownum r,xh,xm,bjmc,xn,rychmc,fdysh,xysh,xxsh from view_xsrychb ";
				
				String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm", "xn", "rychdm"};
				String[] likeList = new String[]{"xh", "xm"};
				MakeQuery queryObject = new MakeQuery();
				queryObject.makeQuery(queryList, likeList, model);
				
				String[] colList = new String[] { "pk", "dis", "r", "xh", "xm", "bjmc", "xn",
						"rychmc",  "fdysh","xysh","xxsh" };
				return CommonQueryDAO.commonQuery(sql, queryObject.getQueryString()
					, queryObject.getInputList(), colList, model);
			} else {
				String sql = "select xh||xn||rychdm pk,'' dis,rownum r,xh,xm,bjmc,xn,rychmc,fdysh,xysh,xxsh from view_xsrychb ";
				
				String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm", "xn", "rychdm"};
				String[] likeList = new String[]{"xh", "xm"};
				MakeQuery queryObject = new MakeQuery();
				queryObject.makeQuery(queryList, likeList, model);
				
				String[] colList = new String[] { "pk", "dis", "r", "xh", "xm", "bjmc", "xn",
						"rychmc", "fdysh","xysh","xxsh"};
				return CommonQueryDAO.commonQuery(sql, queryObject.getQueryString()
					, queryObject.getInputList(), colList, model);
			}
		}
	}
	
	/**
	 * ���ѧ�����뽱ѧ����� һ��ѧ��ֻ�ܻ��һ����
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public boolean checkStujxjsqcs(String xh, String xn, String dm) throws Exception {
		String count = dao
				.getOneRs(
						"select count(*) cot from xsjxjb where xh=? and xn=? and xxsh='ͨ��' and jxjdm<>?",
						new String[] { xh, xn,dm }, "cot");
		return !StringUtils.isNull(count) && !"0".equalsIgnoreCase(count) ? true
				: false;
	}
	/**
	 * ɾ�����׳ƺŻ�ѧ��������
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean deleteData(PjpyGzdxModel model) throws Exception {
		if (model.getCbv() == null) {
			return false;
		} else {
			String[] cbv = model.getCbv();
			String[] sqlArr = new String[cbv.length];
			if ("jxj".equalsIgnoreCase(model.getLb())) {
				for (int i = 0; i < cbv.length; i++) {
					StringBuffer sql = new StringBuffer("delete from xsjxjb where xh||xn||jxjdm = '");
					sql.append(cbv[i]);
					sql.append("'");
					sqlArr[i] = sql.toString(); 
				}
			} else {
				for (int i = 0; i < cbv.length; i++) {
					StringBuffer sql = new StringBuffer("delete from xsrychb where xh||xn||rychdm = '");
					sql.append(cbv[i]);
					sql.append("'");
					sqlArr[i] = sql.toString();
				}
			}
			int[] result = dao.runBatch(sqlArr);
			return dao.checkBatch(result);
		}
	}
	/**
	 * �޸������ƺŻ�ѧ��������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean modiData(PjpyGzdxModel model,String pkValue) throws Exception {
		    String sql = "";
			if ("jxj".equalsIgnoreCase(model.getLb())) {
				String xn = model.getXn();
				String jxjdm = model.getJxjdm();
				sql = " update xsjxjb set xn=? , jxjdm=? where xh||xn||jxjdm=? ";
				return dao.runUpdate(sql, new String[]{xn,jxjdm,pkValue});
			} else {
				String xn = model.getXn();
				String rychdm = model.getRychdm();
				sql = " update xsrychb set xn=? , rychdm=? where xh||xn||rychdm=? ";
				return dao.runUpdate(sql, new String[]{xn,rychdm,pkValue});
			}
	}
	
	/**
	 * ���潱ѧ�������ƺŵ��������Ϣ
	 * @param model
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean saveJxjRychshxx(PjpyGzdxModel model, String pkValue)
			throws Exception {
		if ("jxj".equalsIgnoreCase(model.getLb())) {
			return dao.runUpdate(
					"update xsjxjb set xxsh=?,xxshyj=? where xh||xn||jxjdm=?",
					new String[] { model.getSh(), model.getYj(), pkValue });
		} else {
			return dao.runUpdate(
					"update xsrychb set xxsh=?,xxyj=? where xh||xn||rychdm=?",
					new String[] { model.getSh(), model.getYj(), pkValue });
		}
	}

	/**
	 * ��ѧ���������ǰ��⵱ǰ���ѧ���Ƿ�����˹�������ѧ��
	 * @param pk
	 * @param xh
	 * @param xn
	 * @return ����ѧ���ظ�������
	 */
	public String[] checkPlshdata(String pk, String xh, String xn) throws Exception{
		String[] pkList = StringUtils.isNull(pk) ? null : pk.split("!@");
		String[] xhList = StringUtils.isNull(xh) ? null : xh.split("!@");
		if (xhList == null) {
			return null;
		} else {
			//�����˵�ѧ�����Ƿ����ظ��� ����ѧУ��ѧ��һ��ѧ��ֻ�ܻ�һ��
			String retVal = getXh(xhList);
			if (!StringUtils.isNull(retVal)) {
				return new String[]{"true", retVal};
			}
			StringBuffer sql = new StringBuffer("select distinct xh from xsjxjb where xn=?");
			sql.append(" and xxsh='ͨ��' and xh in ('");
			for (int i=0;i<xhList.length;i++) {
				sql.append(xhList[i]);
				if (i < xhList.length - 1) {
					sql.append("','");
				}
			}
			sql.append("')");
			if (pkList != null) {
				//������ǰ��˵�������,����û��������ѧ��
				sql.append(" and xh||xn||jxjdm not in ('");
				for (int i=0;i<pkList.length;i++) {
					sql.append(pkList[i]);
					if (i < pkList.length - 1) {
						sql.append("','");
					}
				}
				sql.append("')");
			}
			
			String[] retList = dao.getArray(sql.toString(), new String[]{xn}, "xh");
			
			if (retList != null && retList.length > 0) {
				for (String s : retList) {
					retVal += s + ","; 
				}
			}
			return new String[]{"false", retVal};
		}
	}
	
	/**
	 * ����ѧ���б�,����б���ѧ���Ƿ����ظ���
	 * @param xhList
	 * @return
	 */
	public String getXh(String[] xh) {
		if (xh == null) {
			return "";
		}
		String result = "";;
		HashMap<String, String> rs = new HashMap<String, String>();
		HashMap<String, String> ret = new HashMap<String, String>();
		for (int i=0;i<xh.length;i++) {
			if (xh[i].equalsIgnoreCase(rs.get(xh[i]))) {
				ret.put(xh[i], xh[i]);
			}
			rs.put(xh[i], xh[i]);
		}
		Set<String> set = ret.keySet();
		for (String s : set) {
			if (ret.get(s) != null) {
				result += ret.get(s) + ",";
			}
		}
		return result;
	}
	
	/**
	 * ��ѯ�����Ļ�ͳ������
	 * @param model
	 * @return
	 */
	public List<String[]> expHjData(PjpyGzdxModel model)throws Exception {
		List<String[]> rs = new ArrayList<String[]>();
		String xn = model.getXn();
		String sql = "select * from (select a.*,(rank() over (partition by a.xn,"
				+ "a.bjdm order by to_number(a.zf) desc nulls last)) bjpm,b.xyyj "
				+ "jxjmc,c.xyyj rychmc,(select sum(je) from (select xh,xn,jxjdm,"
				+ "(select jlje from jxjdmb b where a.jxjdm=b.jxjdm) je from xsjxjb "
				+ "a where xxsh='ͨ��' and xn='"
				+ xn
				+ "') d where a.xh=d.xh and "
				+ "a.xn=d.xn) je from (select xymc,zymc,xm,xh,xydm,zydm,bjdm,nj,'"
				+ xn
				+ "' xn,(select zf from gzdx_zhszcpb b where a.xh=b.xh and b.xn='"
				+ xn
				+ "') zf from view_xsjbxx a) a left join (select xh,xn,max(ltrim(sys_connect_by_path(mc, ','), ',')) xyyj from (select xh,xn,mc,row_number() over(partition by xh, xn order by mc) rno,row_number() over(partition by xh, xn order by mc) - 1 sno from (select a.xh,a.xn,(select jxjmc mc from jxjdmb c where a.jxjdm = c.jxjdm) mc from xsjxjb a where xxsh='ͨ��' and xn='"
				+ xn
				+ "') where mc is not null) connect by prior sno = rno and prior xh = xh and prior xn = xn group by xh, xn) b on a.xh=b.xh and a.xn=b.xn left join (select xh,xn,xyyj from (select xh,xn,max(ltrim(sys_connect_by_path(mc, ','), ',')) xyyj from (select xh,xn,mc,row_number() over(partition by xh, xn order by mc) rno,row_number() over(partition by xh, xn order by mc) - 1 sno from (select a.xh,a.xn,(select rychmc mc from rychdmb c where a.rychdm = c.rychdm) mc from xsrychb a where xxsh='ͨ��' and xn='"
				+ xn
				+ "') where mc is not null) connect by prior sno = rno and prior xh = xh and prior xn = xn group by xh, xn)) c on a.xh=c.xh and a.xn=c.xn)";

		String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm"};
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, null, model);
		
		String[] colList = new String[] { "xymc", "zymc", "xm", "xh", "zf", "bjpm", "rychmc", "jxjmc", "je"};
		rs = dao.rsToVator(sql + queryObject.getQueryString() + " and (jxjmc is not null or rychmc is not null) order by xydm,zydm,bjdm,bjpm", queryObject.getInputList(), colList);
		
		return rs;
	}
	
	/**
	 * ������������
	 * @param xydm
	 * @param dm
	 * @param key
	 * @param xn
	 * @param bl 
	 * @return
	 * @throws Exception 
	 */
	public boolean rsblsz(String xydm, String dm, String key, String xn, String bl) throws Exception {
		// TODO �Զ����ɷ������
		String query = "";
		if(xydm!=null&&!xydm.equalsIgnoreCase("")){
			query += " and xydm = '";
			query += xydm;
			query += "'";
		}
		if(dm!=null&&!dm.equalsIgnoreCase("")){
			query += " and dm = '";
			query += dm;
			query += "'";
		}
		
		String sql = "update gzdx_xyjxjrsb set jyrs = round(XYZRS*"+bl+"/100),bl ='"+bl+"' where xn = ? and key = ? "+query;
		return dao.runUpdate(sql,new String []{xn,key });  
	}
	
	/**
	 * ��ʼ������
	 * @param key
	 * @return
	 * @throws Exception 
	 */
	public boolean cshrs(String key) throws Exception {
		// TODO �Զ����ɷ������
		String xn = Base.getJxjsqxn();
		String sql = "delete from gzdx_xyjxjrsb where key = ? and xn = ?";
		String tableName = "jxjdmb";
		if(key.equalsIgnoreCase("rych")){
			tableName = "rychdmb";
		}
		boolean updata = dao.runUpdate(sql, new String[]{key,Base.getJxjsqxn()});
		if(updata){
			sql = "insert into gzdx_xyjxjrsb(xn,xydm,dm,xyzrs,key) select distinct '"+xn+"',b.xydm,a."+key+"dm,count(*) num,'"+key+"' from view_xsjbxx b,"+tableName+" a group by b.xydm,a."+key+"dm";
			return dao.runUpdate(sql, new String[]{});
		}else{
			return false;
		}
	}
	
	/**
	 * ���������б�
	 * @param model
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public ArrayList<String[]> getRsszList(PjpyGzdxModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String tableName = "view_gzdx_xyjxjrs";

		String[] queryList = new String[] { "xn", "xydm","dm","xxsh","key"};
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, null, model);

		String[] colList = new String[] { "xn||xydm||dm||key", "xymc","mc", "xyzrs", "bl", "jsrs","jyrs","xysqtzrs","tzrs","xxsh"};
		return CommonQueryDAO.commonQuery(tableName, queryObject.getQueryString(),queryObject.getInputList(), colList, "", model);
	}
	
	/**
	 * �������ñ�ͷ
	 * @return
	 */
	public List<HashMap<String, String>> getRsszTopTr() {
		String tableName = "view_gzdx_xyjxjrs";
		String[] colList = new String[] { "xn||xydm||dm||key", "xymc", "mc", "xyzrs", "bl", "jsrs", "jyrs","xysqtzrs","tzrs","xxsh"};
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		return dao.arrayToList(colList,colListCN);
	}
	
	/**
	 * ��õ�������������Ϣ
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> getDgrsxx(String pkValue) {
		String tableName = "view_gzdx_xyjxjrs";
		String[] colList = new String[] {"bl","dm","jsrs","jyrs","key","keymc","mc","tzrs","xn","xxsh","xydm","xymc","xysqtzrs","xysqtzyy","xyzrs"};
		return CommonQueryDAO.commonQueryOne(tableName, colList, "xn||xydm||dm||key", pkValue);
	}
	
	/**
	 * ����������������
	 * @param pk
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean dgrsUpdate(String pk, PjpyGzdxModel model,HttpServletRequest request) throws Exception {
		String userType = request.getSession().getAttribute("userType") != null ? request
				.getSession().getAttribute("userType").toString()
				: "";
		String pkKey = "xn||xydm||dm||key";
		String tableName = "gzdx_xyjxjrsb";
		String[] colList =new String[] {"bl","dm","jyrs","key","tzrs","xn","xxsh","xydm","xysqtzrs","xysqtzyy","xyzrs"};
		if ("ͨ��".equalsIgnoreCase(model.getXxsh())
				&& !"xy".equalsIgnoreCase(userType)) {
			model.setTzrs(model.getXysqtzrs());
		} else if ("��ͨ��".equalsIgnoreCase(model.getXxsh())
				&& !"xy".equalsIgnoreCase(userType)) {
			model.setTzrs("") ;
			
		}
		return  CommonUpdata.commonUpdata(colList, model, tableName, pkKey, pk, request);
	}
	
	public List<HashMap<String, String>> queryShList() {
		String[] en = {"�޸�", "ͨ��", "��ͨ��"};
		return dao.arrayToList(en, en);
	}
	
	/**
	 * ��������б�
	 * 
	 * @author luojw
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTjList(PjpyGzdxModel model) {

		DAO dao = DAO.getInstance();

		// ��ѧ��������ƺ�
		String lx = model.getLb();
		// ����ѧ��
		String xn = model.getXn();
		// ����ѧ��(���ݴ�ѧ����Ҫѧ��)
		String xq = "no";
		// ��ѧ��������ƺŴ���
		String jxjdm = model.getDm();

		StringBuffer sql = new StringBuffer();
		sql.append("select tjzd,tjlx,tjz from zjcm_pjpy_tjsz ");
		sql.append("where xn = ? ");
		sql.append("  and xq = ? ");
		sql.append("  and lx = ? ");
		sql.append("  and jxjdm = ? ");

		String[] inputValue = new String[] { xn, xq, lx, jxjdm };
		String[] outputValue = new String[] { "tjzd", "tjlx", "tjz" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);
		return list;
	}
	
	/**
	 * �Ƿ����ZCPM����
	 */
	public HashMap<String, String> checkZcpm(PjpyGzdxModel model) {

		DAO dao = DAO.getInstance();

		// ��ѧ��������ƺ�
		String lx = model.getLb();
		// ����ѧ��
		String xn = model.getXn();
		// ����ѧ��(���ݴ�ѧ����Ҫѧ��)
		String xq = "no";
		// ��ѧ��������ƺŴ���
		String jxjdm = model.getDm();

		StringBuffer sql = new StringBuffer();
		sql.append("select count(1)tj from zjcm_pjpy_tjsz ");
		sql.append("where xn = ? ");
		sql.append("  and xq = ? ");
		sql.append("  and lx = ? ");
		sql.append("  and jxjdm = ? ");

		String[] inputValue = new String[] { xn, xq, lx, jxjdm };
		String[] outputValue = new String[] { "tj",  };

		HashMap<String,String>hashMap=dao.getMap(sql.toString(), inputValue, outputValue);
		return hashMap;
	}
}
