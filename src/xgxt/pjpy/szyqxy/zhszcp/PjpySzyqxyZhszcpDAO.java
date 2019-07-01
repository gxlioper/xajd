package xgxt.pjpy.szyqxy.zhszcp;

import java.io.OutputStream;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;

import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;
public class PjpySzyqxyZhszcpDAO {

	private DAO dao = DAO.getInstance();
	
	@SuppressWarnings("unused")
	private ArrayList<String> values = null;
	
	public static PjpySzyqxyZhszcpDAO mydao = new PjpySzyqxyZhszcpDAO();
	
	public static PjpySzyqxyZhszcpDAO getInstance() {
		return mydao;
	}
	/**
	 * ��ȡ��ͷ
	 * @param en
	 * @param cn
	 * @return
	 */
	public List<HashMap<String, String>>  getTitle(String[] en, String[] cn){
		return  dao.arrayToList(en, cn);
	}
	/**
	 * ��ȡ�����Ϣ
	 * @param table ����
	 * @param querry ����
	 * @return
	 */
	public HashMap<String,String> dao_getInfo(String table,String querry){
		DAO dao = DAO.getInstance();
		String[] colList =  dao.getColumnName("select xh,xb,xm,nj,xymc,zymc,bjmc from "+table);
		for(int i=0;i<colList.length;i++){
			colList[i]=colList[i].toLowerCase();
		}
		String sql = "select xh,xb,xm,nj,xymc,zymc,bjmc from "+table;
		return dao.getMap(sql+querry.toString(),new String[]{},colList);
	}
	/**
	 * ��֯�������˲�ѯ
	 */
	public ArrayList<String[]> dao_zznlChkSearch(ZhszcpModel model){
		ArrayList<String[]> result = new ArrayList<String[]>();
		String xh = model.getXh();
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xm   = model.getXm();
		String xn   = model.getXn();
		String xq   = model.getXq();
		xh = Base.isNull(xh) ? "%" : xh ;
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";		
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		StringBuffer querry2 = new StringBuffer(" where 1=1 ");
		querry.append(" and a.xh like ?");
		querry.append(" and xm like ?");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
		querry2.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry2.append(Base.isNull(xn)?"":" and xq='"+xq+"' ");	
		String[] colList = new String[]{"pk","xn","xq","xh","xm","nj","xymc","bjmc","zf","hdf","sh"};
		StringBuffer  sql = new StringBuffer();
		sql.append("select rownum r,a.xh||a.xn||a.xq pk,a.xn,(select xqmc from xqdzb d where a.xq=d.xqdm)xq,a.xh,nj,xydm,zydm,bjdm,xm,xymc,bjmc,b.zf,nvl(c.hdf,0) hdf, ");
		sql.append("( case when (select count(xh) from szyc_zznlfzb d "+querry2+" and d.xh=a.xh and xxsh='ͨ��')=a.cout then 'ȫ��ͨ��' ");
		sql.append("when (select count(xh) from szyc_zznlfzb d "+querry2+" and d.xh=a.xh and xxsh='δ���')=a.cout then 'δ���'  ");
		sql.append("when (select count(xh) from szyc_zznlfzb d "+querry2+" and d.xh=a.xh and xxsh='��ͨ��')=a.cout then 'ȫ����ͨ��' else '����ͨ��' end  ");
		sql.append(")sh from (select a.xn,a.xq,a.xh,nj,xydm,zydm,bjdm,xm,xymc,bjmc, ");
		sql.append("(select count(xh) from szyc_zznlfzb d  "+querry2+"  and d.xh=a.xh )cout ");
		sql.append("from (select  distinct xh,xn,xq from szyc_zznlfzb  "+querry2+" )a left join view_xsxxb b on a.xh=b.xh )a ");
		
		sql.append("left join (select xh, xn, xq, ");
		sql.append("sum(pf) zf from (select xh, xn, xq, case when jjf = '�ӷ�' then shfz else '-' || shfz end pf ");
		sql.append("from szyc_zznlfzb) group by xh, xn, xq) b on a.xn = b.xn and a.xq = b.xq and a.xh = b.xh ");
		
		sql.append("left join (select xh, xn,xq, ");
		sql.append("sum(pf) hdf from (select xh, xn, xq, case when jjf = '�ӷ�' then shfz else '-' || shfz end pf ");
		sql.append("from szyc_zznlfzb where xxsh='ͨ��') group by xh, xn, xq) c on a.xn = c.xn and a.xq = c.xq and a.xh = c.xh");
		
		try {
			result = CommonQueryDAO.commonQuery(sql.toString(), querry.toString(), new String[]{xh,xm},colList, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}	
	/**
	 * ��֯�������˲�ѯ
	 */
	public ArrayList<String[]> dao_xthdChkSearch(ZhszcpModel model,String xxk){
		ArrayList<String[]> result = new ArrayList<String[]>();
		String xh = model.getXh();
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xm   = model.getXm();
		String xn   = model.getXn();
		String xq   = model.getXq();
		String tableName = "szyq_dshdjzb";
		if("ivlt".equals(xxk)){
			tableName = "szyq_ivtltb";
		}else if("wthd".equals(xxk)){
			tableName = "szyq_xthddjb";
		}else if("yybd".equals(xxk)){
			tableName = "szyq_yybdjzb";
		}else{
			tableName = "szyq_dshdjzb";
		}
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		StringBuffer querry2 = new StringBuffer(" where 1=1 ");
		querry.append(Base.isNull(xh)?"":" and a.xh='"+xh.trim()+"' ");
		querry.append(Base.isNull(nj)?"":" and a.nj='"+nj+"' ");
		querry.append(Base.isNull(xydm)?"":" and a.xydm='"+xydm+"' ");
		querry.append(Base.isNull(zydm)?"":" and a.zydm='"+zydm+"' ");
		querry.append(Base.isNull(bjdm)?"":" and a.bjdm='"+bjdm+"' ");
		querry.append(Base.isNull(xm)?"":" and a.xm like '%"+xm.trim()+"%' ");	
		querry2.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry2.append(Base.isNull(xn)?"":" and xq='"+xq+"' ");	
		String[] colList = new String[]{"pk","xn","xq","xh","xm","nj","xymc","bjmc","zf","hdf","sh"};
		StringBuffer  sql = new StringBuffer();
		sql.append("select rownum r,a.xh||a.xn||a.xq pk,a.xn,(select xqmc from xqdzb d where a.xq=d.xqdm)xq,a.xh,a.nj,a.xydm,a.zydm,a.bjdm,a.xm,a.xymc,a.bjmc,b.zf,nvl(c.hdf,0) hdf, ");
		sql.append("( case when (select count(xh) from "+tableName+" d "+querry2+" and d.xh=a.xh and xxsh='ͨ��')=a.cout then 'ȫ��ͨ��' ");
		sql.append("when (select count(xh) from "+tableName+" d "+querry2+" and d.xh=a.xh and xxsh='δ���')=a.cout then 'δ���'  ");
		sql.append("when (select count(xh) from "+tableName+" d "+querry2+" and d.xh=a.xh and xxsh='��ͨ��')=a.cout then 'ȫ����ͨ��' else '����ͨ��' end  ");
		sql.append(")sh from (select a.xn,a.xq,a.xh,nj,xydm,zydm,bjdm,xm,xymc,bjmc, ");
		sql.append("(select count(xh) from "+tableName+" d  "+querry2+"  and d.xh=a.xh )cout ");
		sql.append("from (select  distinct xh,xn,xq from "+tableName+"  "+querry2+" )a left join view_xsxxb b on a.xh=b.xh )a ");
		
		sql.append("left join (select xh, xn, xq, ");
		sql.append("sum(pf) zf from (select xh, xn, xq, case when jjf = '�ӷ�' then pf else '-' || pf end pf ");
		sql.append("from "+tableName+") group by xh, xn, xq) b on a.xn = b.xn and a.xq = b.xq and a.xh = b.xh ");
		
		sql.append("left join (select xh, xn,xq, ");
		sql.append("sum(pf) hdf from (select xh, xn, xq, case when jjf = '�ӷ�' then pf else '-' || pf end pf ");
		sql.append("from "+tableName+" where xxsh='ͨ��') group by xh, xn, xq) c on a.xn = c.xn and a.xq = c.xq and a.xh = c.xh");
		//System.out.println(sql);
		result = null;
		
		try {
			result = CommonQueryDAO.commonQuery(sql.toString(), querry.toString(), new String[]{},colList, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}	
	/**
	 * ��֯�������Ϣ�б�
	 */
	public ArrayList<String[]> dao_zznlViewAndChkSearch(String xh,String xn,String xq){
        StringBuffer sql = new StringBuffer();
        String[]colList=new String[]{"id","r","hdzt","hdrq","hddj","jjf","shfz","xxsh"};
        sql.append("select id, rownum r,hdzt,hddj,hdpf,hdrq,jjf,shfz,xxsh from szyc_zznlfzb where xh=? and xn=? and xq=?");
        return dao.rsToVator(sql.toString(), new String[]{xh,xn,xq},colList);
	}
	/**
	 * ѧ�Ż���Ϣ�б�
	 */
	public ArrayList<String[]> dao_zznlViewAndChkSearch(String xh,String xn,String xq,String xxk){
        StringBuffer sql = new StringBuffer();
        String tableName = "szyq_dshdjzb";
        String[]colList;
		if("ivlt".equals(xxk)){
			tableName = "szyq_ivtltb";
			 colList=new String[]{"id","r","jztm","xthdrq","jcdj","ccdj","jjf","pf","xxsh"};
		     sql.append("select id, rownum r,jztm,xthdrq,jcdj,ccdj,jjf,pf,xxsh from "+tableName+" where xh=? and xn=? and xq=?");
		}else if("wthd".equals(xxk)){
			tableName = "szyq_xthddjb";
			 colList=new String[]{"id","r","hdnr","xthdrq","jldj","jjf","pf","xxsh"};
		     sql.append("select id, rownum r,hdnr,xthdrq,jldj,jjf,pf,xxsh from "+tableName+" where xh=? and xn=? and xq=?");
		}else if("yybd".equals(xxk)){
			tableName = "szyq_yybdjzb";
			 colList=new String[]{"id","r","yybdnr","xthdrq","jjf","pf","xxsh"};
		     sql.append("select id, rownum r,yybdnr,xthdrq,jjf,pf,xxsh from "+tableName+" where xh=? and xn=? and xq=?");
		}else{
			tableName = "szyq_dshdjzb";
			 colList=new String[]{"id","r","dsmc","dsrq","dsxd","sfhj","jjf","pf","xxsh"};
		     sql.append("select id, rownum r,dsmc,dsrq,dsxd,sfhj,jjf,pf,xxsh from "+tableName+" where xh=? and xn=? and xq=?");
		}
        return dao.rsToVator(sql.toString(), new String[]{xh,xn,xq},colList);
	}
	/**
	 * ��֯��������
	 */
	public boolean dao_zznlChk(String pkVStr,String shType) throws Exception{
		DAO dao = DAO.getInstance();	
		shType = ("yes".equalsIgnoreCase(shType))?"ͨ��":"��ͨ��";
		String[] pkValueA = pkVStr.split("!!");		
		String[] updtPkSql  = new String[pkValueA.length];	
		for (int i = 0; i < pkValueA.length; i++) {
			updtPkSql[i] = Base.isNull(pkValueA[i])?"":"update szyc_zznlfzb set xxsh='"+shType+"'  where id= '"+pkValueA[i]+"'";							
		}              
		boolean doFlag = false;
		int[] array = dao.runBatch(updtPkSql);
		doFlag = dao.checkBatch(array);   
		return doFlag;
	}
	/**
	 * ���Ż���
	 */
	public boolean dao_wthdChk(String pkVStr,String shType,String xxk) throws Exception{
		DAO dao = DAO.getInstance();	
		shType = ("yes".equalsIgnoreCase(shType))?"ͨ��":"��ͨ��";
		String[] pkValueA = pkVStr.split("!!");		
		String[] updtPkSql  = new String[pkValueA.length];	
		String tableName = "szyq_dshdjzb";
        if("ivlt".equals(xxk)){
			tableName = "szyq_ivtltb";
		}else if("wthd".equals(xxk)){
			tableName = "szyq_xthddjb";
		}else if("yybd".equals(xxk)){
			tableName = "szyq_yybdjzb";
		}else{
			tableName = "szyq_dshdjzb";
		}
		for (int i = 0; i < pkValueA.length; i++) {
			updtPkSql[i] = Base.isNull(pkValueA[i])?"":"update "+tableName+" set xxsh='"+shType+"'  where id= '"+pkValueA[i]+"'";							
		}              
		boolean doFlag = false;
		int[] array = dao.runBatch(updtPkSql);
		doFlag = dao.checkBatch(array);   
		return doFlag;
	}

	//��֯��������
	public String dao_addZznl(ZznlModel model,HttpServletRequest request){
		String sFlag = "";
		dao = DAO.getInstance();
		String[] sqlList = new String[model.getHdzt().length+1];
		StringBuffer sql = null;
		try {
			StringBuffer buff = new StringBuffer();
			buff.append(model.getXh()[0]).append(model.getXn()).append(model.getXq());
			StandardOperation.delete("szyc_zznlfzb", "xh||xn||xq", buff.toString(), request);
		} catch (Exception e1) {
			// TODO �Զ����� catch ��
			e1.printStackTrace();
		}
		for (int i = 0; i < model.getHdzt().length; i++) {
			sql = new StringBuffer();
			sql.append("insert into szyc_zznlfzb (xh,xn,xq,hdzt,hddj,hdrq,hdpf,shfz,jjf,xxsh) values('");
			sql.append(model.getXh()[0]);
			sql.append("','");
			sql.append(model.getXn());
			sql.append("','");
			sql.append(model.getXq());
			sql.append("','");
			sql.append(DealString.toGBK(model.getHdzt()[i]).replace("'", "��"));
			sql.append("','");
			sql.append(DealString.toGBK(model.getHddj()[i]).replace("'", "��"));
			sql.append("','");
			sql.append(DealString.toGBK(model.getHdrq()[i]));
			sql.append("','");
			//sql.append(DealString.toGBK(model.getHdpf()[i]));
			sql.append("','");
			sql.append(DealString.toGBK(model.getShfz()[i]));
			sql.append("','");
			sql.append(DealString.toGBK(model.getJjf()[i]));
			sql.append("','");
			sql.append(DealString.toGBK(model.getXxsh()[i]));
			sql.append("')");
			if (StringUtils.isNull(model.getHdzt()[i])) {//Ϊ���򲻼�¼
				sql = new StringBuffer("");				
			}
			sqlList[i] = sql.toString();
		}
		//String updateSql = "update sxzzddszb set shzt = 'δ���' where xh = '"+model.getXh()+"' and xn = '"+model.getXn()+"'";
		//sqlList[model.getFs().length] = updateSql;
		int[] iFlag = null;
		try {
			iFlag = dao.runBatch(sqlList);
		} catch (SQLException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}//��������
		for (int i=0;i<iFlag.length;i++) {
			if(iFlag[i] <= -1){//��¼����ʧ�ܵ��к�
				sFlag = (iFlag[i] + 1) + ",";
			}
		}
		if (!StringUtils.isNull(sFlag)) {
			return sFlag.substring(0, sFlag.length() - 1);
		} else {
			return "";
		}
	}
	
	//��֯������ѯ
	public ArrayList<String[]> dao_QueryZznl(ZznlModel model,String userType,String userName){
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		if (model.getXh() != null && model.getXh().length > 0) {
			querry.append(" and a.xh='" + model.getXh()[0].trim() + "' ");
		}
		querry.append(Base.isNull(model.getXn())?"":" and a.xn='"+model.getXn()+"' ");
		querry.append(Base.isNull(model.getNj())?"":" and a.nj='"+model.getNj()+"' ");
		querry.append(Base.isNull(model.getXydm())?"":" and a.xydm='"+model.getXydm()+"' ");
		querry.append(Base.isNull(model.getZydm())?"":" and a.zydm='"+model.getZydm()+"' ");
		querry.append(Base.isNull(model.getBjdm())?"":" and a.bjdm='"+model.getBjdm()+"' ");
		querry.append(Base.isNull(model.getXm())?"":" and a.xm like '%"+model.getXm()+"%' ");
		querry.append(Base.isNull(model.getXq())?"":" and a.xq='"+getXqmc(model.getXq())+"' ");
		querry.append("bzr".equalsIgnoreCase(userType) ? " and exists (select 1 from fdybjb b where zgh like '"+ userName + "' and a.bjdm = b.bjdm)": "");	
		
		StringBuffer sb = new StringBuffer();
		sb.append("left join (select xh, xn, (select xqmc from xqdzb where xq = xqdm) xq, ");
		sb.append("sum(shfz) zf from (select xh, xn, xq, case when jjf = '�ӷ�' then shfz else '-' || shfz end shfz ");
		sb.append("from szyc_zznlfzb) group by xh, xn, xq) b on a.xn = b.xn and a.xq = b.xq and a.xh = b.xh ");
		
		sb.append("left join (select xh, xn, (select xqmc from xqdzb where xq = xqdm) xq, ");
		sb.append("sum(shfz) hdf from (select xh, xn, xq, case when jjf = '�ӷ�' then shfz else '-' || shfz end shfz ");
		sb.append("from szyc_zznlfzb where xxsh='ͨ��') group by xh, xn, xq) c on a.xn = c.xn and a.xq = c.xq and a.xh = c.xh");
		
		String sql = "select a.*,b.zf,nvl(c.hdf,0) hdf from (select a.xh||xn||xq pk,a.xh,xn,(select xqmc from xqdzb b where a.xq=b.xqdm) xq,xm,nj,xydm,xymc,zydm,zymc,bjdm,bjmc from "
				+ "(select distinct xh,xn,xq from szyc_zznlfzb) a left outer join view_xsxxb b on a.xh=b.xh) a "
				+ sb.toString() + querry.toString();
		String[] inputValue = {} ;
		String[] outputValue = {"pk","xh","xn","xq","xm","xymc","bjmc","zf","hdf" };
		ArrayList<String[]> rs = dao.rsToVator(sql, inputValue, outputValue);

		return rs;
	}
	//ѧ�Ż��ѯ
	public ArrayList<String[]> dao_QueryXthd(XthdModel model,String xxk){
		
		String userName = model.getUserName();
		String userType = model.getUserType();
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		String bzr = " and exists (select 1 from fdybjb b where zgh like '"
			+ userName + "' and a.bjdm = b.bjdm)";
		if (model.getXh() != null && model.getXh().length > 0) {
			querry.append(" and a.xh='"+ model.getXh()[0].trim() + "' ");
		}
		querry.append(Base.isNull(model.getXn())?"":" and a.xn='"+model.getXn()+"' ");
		querry.append(Base.isNull(model.getNj())?"":" and a.nj='"+model.getNj()+"' ");
		querry.append(Base.isNull(model.getXydm())?"":" and a.xydm='"+model.getXydm()+"' ");
		querry.append(Base.isNull(model.getZydm())?"":" and a.zydm='"+model.getZydm()+"' ");
		querry.append(Base.isNull(model.getBjdm())?"":" and a.bjdm='"+model.getBjdm()+"' ");
		querry.append(Base.isNull(model.getXm())?"":" and a.xm like '%"+model.getXm()+"%' ");
		querry.append(Base.isNull(model.getXq())?"":" and a.xq='"+getXqmc(model.getXq())+"' ");
		querry.append("bzr".equalsIgnoreCase(userType)?bzr:"");
		String sql="";
		String[] outputValue;
		StringBuffer sb = new StringBuffer();
		if("ivlt".equals(xxk)){
			
			sb.append("left join (select xh, xn, (select xqmc from xqdzb where xq = xqdm) xq, ");
			sb.append("sum(pf) zf from (select xh, xn, xq, case when jjf = '�ӷ�' then pf else '-' || pf end pf ");
			sb.append("from szyq_ivtltb) group by xh, xn, xq) b on a.xn = b.xn and a.xq = b.xq and a.xh = b.xh ");
			
			sb.append("left join (select xh, xn, (select xqmc from xqdzb where xq = xqdm) xq, ");
			sb.append("sum(pf) hdf from (select xh, xn, xq, case when jjf = '�ӷ�' then pf else '-' || pf end pf ");
			sb.append("from szyq_ivtltb where xxsh='ͨ��') group by xh, xn, xq) c on a.xn = c.xn and a.xq = c.xq and a.xh = c.xh");
			
			sql = "select rownum r,a.*,b.zf,nvl(c.hdf,0) hdf from (select a.xh||xn||xq pk,a.xh,xn,(select xqmc from xqdzb b where a.xq=b.xqdm) xq,xm,nj,xydm,xymc,zydm,zymc,bjdm,bjmc from "
					+ "(select distinct xh,xn,xq from szyq_ivtltb) a left outer join view_xsxxb b on a.xh=b.xh) a "
					+ sb.toString() + querry.toString();
			outputValue = new String[] { "pk", "xh", "xn", "xq", "xm", "xymc","bjmc","zf","hdf" };
		}else if("wthd".equals(xxk)){
			
			sb.append("left join (select xh, xn, (select xqmc from xqdzb where xq = xqdm) xq, ");
			sb.append("sum(pf) zf from (select xh, xn, xq, case when jjf = '�ӷ�' then pf else '-' || pf end pf ");
			sb.append("from szyq_xthddjb) group by xh, xn, xq) b on a.xn = b.xn and a.xq = b.xq and a.xh = b.xh ");
			
			sb.append("left join (select xh, xn, (select xqmc from xqdzb where xq = xqdm) xq, ");
			sb.append("sum(pf) hdf from (select xh, xn, xq, case when jjf = '�ӷ�' then pf else '-' || pf end pf ");
			sb.append("from szyq_xthddjb where xxsh='ͨ��') group by xh, xn, xq) c on a.xn = c.xn and a.xq = c.xq and a.xh = c.xh");
			
			sql = "select rownum r,a.*,b.zf,nvl(c.hdf,0) hdf  from (select a.xh||xn||xq pk,a.xh,xn,(select xqmc from xqdzb b where a.xq=b.xqdm) xq,xm,nj,xydm,xymc,zydm,zymc,bjdm,bjmc from "
					+ "(select distinct xh,xn,xq from szyq_xthddjb) a left outer join view_xsxxb b on a.xh=b.xh) a "
					+ sb.toString() + querry.toString();
			outputValue = new String[]{"pk","xh","xn","xq","xm","xymc","bjmc","zf","hdf"};
		}else if("yybd".equals(xxk)){
			
			sb.append("left join (select xh, xn, (select xqmc from xqdzb where xq = xqdm) xq, ");
			sb.append("sum(pf) zf from (select xh, xn, xq, case when jjf = '�ӷ�' then pf else '-' || pf end pf ");
			sb.append("from szyq_yybdjzb) group by xh, xn, xq) b on a.xn = b.xn and a.xq = b.xq and a.xh = b.xh ");
			
			sb.append("left join (select xh, xn, (select xqmc from xqdzb where xq = xqdm) xq, ");
			sb.append("sum(pf) hdf from (select xh, xn, xq, case when jjf = '�ӷ�' then pf else '-' || pf end pf ");
			sb.append("from szyq_yybdjzb where xxsh='ͨ��') group by xh, xn, xq) c on a.xn = c.xn and a.xq = c.xq and a.xh = c.xh");
			
			sql = "select rownum r,a.*,b.zf,nvl(c.hdf,0) hdf from (select a.xh||xn||xq pk,a.xh,xn,(select xqmc from xqdzb b where a.xq=b.xqdm) xq,xm,nj,xydm,xymc,zydm,zymc,bjdm,bjmc from "
					+ "(select distinct xh,xn,xq from szyq_yybdjzb) a left outer join view_xsxxb b on a.xh=b.xh) a "
					+ sb.toString() + querry.toString();
			outputValue = new String[]{"pk","xh","xn","xq","xm","xymc","bjmc","zf","hdf"};
		}else{
			
			sb.append("left join (select xh, xn, (select xqmc from xqdzb where xq = xqdm) xq, ");
			sb.append("sum(pf) zf from (select xh, xn, xq, case when jjf = '�ӷ�' then pf else '-' || pf end pf ");
			sb.append("from szyq_dshdjzb) group by xh, xn, xq) b on a.xn = b.xn and a.xq = b.xq and a.xh = b.xh ");
			
			sb.append("left join (select xh, xn, (select xqmc from xqdzb where xq = xqdm) xq, ");
			sb.append("sum(pf) hdf from (select xh, xn, xq, case when jjf = '�ӷ�' then pf else '-' || pf end pf ");
			sb.append("from szyq_dshdjzb where xxsh='ͨ��') group by xh, xn, xq) c on a.xn = c.xn and a.xq = c.xq and a.xh = c.xh");
			
			sql = "select rownum r,a.*,b.zf,nvl(c.hdf,0) hdf from (select a.xh||xn||xq pk,a.xh,xn,(select xqmc from xqdzb b where a.xq=b.xqdm) xq,xm,nj,xydm,xymc,zydm,zymc,bjdm,bjmc from "
					+ "(select distinct xh,xn,xq from szyq_dshdjzb) a left outer join view_xsxxb b on a.xh=b.xh) a "
					+ sb.toString() + querry.toString();
			
			outputValue = new String[]{"pk","xh","xn","xq","xm","xymc","bjmc","zf","hdf"};
		}
		String[] inputValue = {} ;
		ArrayList<String[]> rs = dao.rsToVator(sql, inputValue, outputValue);
		return rs;
	}
	//ѧ��ˢѧ����Ϣ
	public HashMap<String,String> dao_getXsxxInfo(String xh){
		HashMap<String,String> map = new HashMap<String, String>();
		String sql = "select * from view_xsxxb where xh=?";
		String[] output = new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc"};
		String[] outputValue = new String[output.length];
		if(StringUtils.isArrayNotNull(output)){
			for(int i = 0;i<output.length;i++){
				outputValue[i] = output[i].toLowerCase();
			}
		}
		map = dao.getMap(sql, new String[]{xh}, outputValue);
		return map;
	}
	//���ѧ����֯������Ϣ
	public ArrayList<HashMap<String, String>> dao_getXszznl(String pk){
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		String sql = "select rownum rnum,a.* from szyc_zznlfzb a where a.xh||a.xn||a.xq=?";
		String[] outputValue = {"rnum","hdzt","hddj","hdpf","hdrq","shfz","jjf"};
		rs = dao.getArrayList(sql, new String[]{pk}, outputValue);
		return rs;
	}
	//���ѧ����֯����ɾ��
	public String dao_getZznlDel(String[] keys){

		StringBuffer sb = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// �õ�����
			sql = "delete from szyc_zznlfzb where xh||xn||xq = '" + pk + "'";
			// ��������ϳ�sql���
			sb.append(sql).append("!!#!!");
		}
		// sql����ֳ�����
		pksql = sb.toString().split("!!#!!");
		int[] judge = null;
		try {
			judge = dao.runBatch(pksql);
		} catch (SQLException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		String whichpk = "";
		// �����һ��ɾ��ʧ��
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] < 0) {
				whichpk = whichpk + " ��" + String.valueOf(i) + "������ɾ��ʧ��;\n";
			}
		}
		return whichpk;
	}
	
	//ѧ�Żɾ��
	public String dao_getXthdDel(String[] keys,String xxk){

		StringBuffer sb = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		String tableName="";
		if("ivlt".equals(xxk)){
			tableName = "szyq_ivtltb";
		}else if("wthd".equals(xxk)){
			tableName = "szyq_xthddjb";
		}else if("yybd".equals(xxk)){
			tableName = "szyq_yybdjzb";
		}else if("dshd".equals(xxk)){
			tableName = "szyq_dshdjzb";
		}
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// �õ�����
			sql = "delete from "+tableName+" where xh||xn||xq = '" + pk + "'";
			// ��������ϳ�sql���
			sb.append(sql).append("!!#!!");
		}
		// sql����ֳ�����
		pksql = sb.toString().split("!!#!!");
		int[] judge = null;
		try {
			judge = dao.runBatch(pksql);
		} catch (SQLException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		String whichpk = "";
		// �����һ��ɾ��ʧ��
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] < 0) {
				whichpk = whichpk + " ��" + String.valueOf(i) + "������ɾ��ʧ��;\n";
			}
		}
		return whichpk;
	}
	/**
	 * ��ȡ��ѯ��ͷ
	 * @param colListCN
	 * @return
	 */
	public ArrayList<HashMap<String, String>> dao_getSearchTitle(String[] colListCN ) {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < colListCN.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
		
		//ѧ�Ż
	public String dao_addXthd(XthdModel model,HttpServletRequest request,String xxk) throws Exception{
		String sFlag = "";
		dao = DAO.getInstance();
		String[] sqlList = null ;
		StringBuffer sql = null;
		String tableName = "szyq_dshdjzb";
		if("ivlt".equals(xxk)){
			tableName = "szyq_ivtltb";
			sqlList = new String[model.getJztm().length+1];
		}else if("wthd".equals(xxk)){
			tableName = "szyq_xthddjb";
			sqlList = new String[model.getHdnr().length+1];
		}else if("yybd".equals(xxk)){
			tableName = "szyq_yybdjzb";
			sqlList = new String[model.getYybdnr().length+1];
		}else{
			tableName = "szyq_dshdjzb";
			sqlList = new String[model.getDsmc().length+1];
		}
		if("szyq_dshdjzb".equals(tableName)){
			StringBuffer buff = new StringBuffer();
			buff.append(model.getXh()[0]).append(model.getXn()).append(model.getXq());
			StandardOperation.delete("szyq_dshdjzb", "xh||xn||xq", buff.toString(), request);
			for (int i = 0; i < model.getDsmc().length; i++) {
				sql = new StringBuffer();
				sql.append("insert into szyq_dshdjzb (xh,xn,xq,dsmc,dsrq,dsxd,sfhj,jjf,pf,xxsh) values('");
				sql.append(model.getXh()[0]);
				sql.append("','");
				sql.append(model.getXn());
				sql.append("','");
				sql.append(model.getXq());
				sql.append("','");
				sql.append(DealString.toGBK(model.getDsmc()[i]).replace("'", "��"));
				sql.append("','");
				sql.append(DealString.toGBK(model.getDsrq()[i]));
				sql.append("','");
				sql.append(DealString.toGBK(model.getDsxd()[i]).replace("'", "��"));
				sql.append("','");
				sql.append(DealString.toGBK(model.getSfhj()[i]));
				sql.append("','");
				sql.append(DealString.toGBK(model.getJjf()[i]).replace("'", "��"));
				sql.append("','");
				sql.append(DealString.toGBK(model.getPf()[i]).replace("'", "��"));
				sql.append("','");
				sql.append(DealString.toGBK(model.getXxsh()[i]));
				sql.append("')");
				if (StringUtils.isNull(model.getDsmc()[i])) {//Ϊ���򲻼�¼
					sql = new StringBuffer("");				
				}
				sqlList[i] = sql.toString();
			}
		}
		if("szyq_yybdjzb".equals(tableName)){
			StringBuffer buff = new StringBuffer();
			buff.append(model.getXh()[0]).append(model.getXn()).append(model.getXq());
			StandardOperation.delete("szyq_yybdjzb", "xh||xn||xq", buff.toString(), request);
			for (int i = 0; i < model.getYybdnr().length; i++) {
				sql = new StringBuffer();
				sql.append("insert into szyq_yybdjzb (xh,xn,xq,yybdnr,xthdrq,jjf,pf,xxsh) values('");
				sql.append(model.getXh()[0]);
				sql.append("','");
				sql.append(model.getXn());
				sql.append("','");
				sql.append(model.getXq());
				sql.append("','");
				sql.append(DealString.toGBK(model.getYybdnr()[i]).replace("'", "��"));
				sql.append("','");
				sql.append(DealString.toGBK(model.getXthdrq()[i]));
				sql.append("','");
				sql.append(DealString.toGBK(model.getJjf()[i]));
				sql.append("','");
				sql.append(DealString.toGBK(model.getPf()[i]));
				sql.append("','");
				sql.append(DealString.toGBK(model.getXxsh()[i]));
				sql.append("')");
				if (StringUtils.isNull(model.getYybdnr()[i])) {//Ϊ���򲻼�¼
					sql = new StringBuffer("");				
				}
				sqlList[i] = sql.toString();
			}
		}
		if("szyq_ivtltb".equals(tableName)){
			StringBuffer buff = new StringBuffer();
			buff.append(model.getXh()[0]).append(model.getXn()).append(model.getXq());
			StandardOperation.delete("szyq_ivtltb", "xh||xn||xq", buff.toString(), request);
			for (int i = 0; i < model.getJztm().length; i++) {
				sql = new StringBuffer();
				sql.append("insert into szyq_ivtltb (xh,xn,xq,jztm,xthdrq,jcdj,ccdj,jjf,pf,xxsh) values('");
				sql.append(model.getXh()[0]);
				sql.append("','");
				sql.append(model.getXn());
				sql.append("','");
				sql.append(model.getXq());
				sql.append("','");
				sql.append(DealString.toGBK(model.getJztm()[i]).replace("'", "��"));
				sql.append("','");
				sql.append(DealString.toGBK(model.getXthdrq()[i]));
				sql.append("','");
				sql.append(DealString.toGBK(model.getJcdj()[i]).replace("'", "��"));
				sql.append("','");
				sql.append(DealString.toGBK(model.getCcdj()[i]).replace("'", "��"));
				sql.append("','");
				sql.append(DealString.toGBK(model.getJjf()[i]));
				sql.append("','");
				sql.append(DealString.toGBK(model.getPf()[i]));
				sql.append("','");
				sql.append(DealString.toGBK(model.getXxsh()[i]));
				sql.append("')");
				if (StringUtils.isNull(model.getJztm()[i])) {//Ϊ���򲻼�¼
					sql = new StringBuffer("");				
				}
				sqlList[i] = sql.toString();
			}
		}
		if("szyq_xthddjb".equals(tableName)){
			StringBuffer buff = new StringBuffer();
			buff.append(model.getXh()[0]).append(model.getXn()).append(model.getXq());
			StandardOperation.delete("szyq_xthddjb", "xh||xn||xq", buff.toString(), request);
			for (int i = 0; i < model.getHdnr().length; i++) {
				sql = new StringBuffer();
				sql.append("insert into szyq_xthddjb (xh,xn,xq,hdnr,xthdrq,jldj,jjf,pf,xxsh) values('");
				sql.append(model.getXh()[0]);
				sql.append("','");
				sql.append(model.getXn());
				sql.append("','");
				sql.append(model.getXq());
				sql.append("','");
				sql.append(DealString.toGBK(model.getHdnr()[i]).replace("'", "��"));
				sql.append("','");
				sql.append(DealString.toGBK(model.getXthdrq()[i]));
				sql.append("','");
				sql.append(DealString.toGBK(model.getJldj()[i]).replace("'", "��"));
				sql.append("','");
				sql.append(DealString.toGBK(model.getJjf()[i]));
				sql.append("','");
				sql.append(DealString.toGBK(model.getPf()[i]));
				sql.append("','");
				sql.append(DealString.toGBK(model.getXxsh()[i]));
				sql.append("')");
				if (StringUtils.isNull(model.getHdnr()[i])) {//Ϊ���򲻼�¼
					sql = new StringBuffer("");				
				}
				sqlList[i] = sql.toString();
			}
		}
		//String updateSql = "update sxzzddszb set shzt = 'δ���' where xh = '"+model.getXh()+"' and xn = '"+model.getXn()+"'";
		//sqlList[model.getFs().length] = updateSql;
		int[] iFlag = null;
		try {
			iFlag = dao.runBatch(sqlList);
		} catch (SQLException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}//��������
		for (int i=0;i<iFlag.length;i++) {
			if(iFlag[i] <= -1){//��¼����ʧ�ܵ��к�
				sFlag = (iFlag[i] + 1) + ",";
			}
		}
		if (!StringUtils.isNull(sFlag)) {
			return sFlag.substring(0, sFlag.length() - 1);
		} else {
			return "";
		}
	}
	//���ѧ�Ż��Ϣ
	public ArrayList<HashMap<String, String>> dao_getXsXthd(String pk,String xxk){
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		String sql="";
		String[] outputValue;
		if("ivlt".equals(xxk)){
			sql = "select rownum rnum,a.* from szyq_ivtltb a where a.xh||a.xn||a.xq=?";
			outputValue = new String[]{"rnum","jztm","xthdrq","jcdj","ccdj","jjf","pf"};
		}else if("wthd".equals(xxk)){
			sql = "select rownum rnum,a.* from szyq_xthddjb a where a.xh||a.xn||a.xq=?";
			outputValue = new String[]{"rnum","hdnr","xthdrq","jldj","jjf","pf"};
		}else if("yybd".equals(xxk)){
			sql = "select rownum rnum,a.* from szyq_yybdjzb a where a.xh||a.xn||a.xq=?";
			outputValue = new String[]{"rnum","yybdnr","xthdrq","jjf","pf"};
		}else{
			sql = "select rownum rnum,a.* from szyq_dshdjzb a where a.xh||a.xn||a.xq=?";
			outputValue = new String[]{"rnum","dsmc","dsrq","dsxd","sfhj","jjf","pf"};
		}
		
		rs = dao.getArrayList(sql, new String[]{pk}, outputValue);
		return rs;
	}
	//���ѧ�Ż��Ϣ
	public ArrayList<HashMap<String, String>> dao_getXsXthdDwr(String xh,String xn,String xq,String xxk){
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		String sql="";
		String xsxq = "";
		try {
			xsxq = getXqdm(xq);
		} catch (SQLException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		StringBuffer pk = new StringBuffer();
		pk.append(xh).append(xn).append(xsxq);
		String[] outputValue;
		if("ivlt".equals(xxk)){
			sql = "select rownum rnum,a.* from szyq_ivtltb a where a.xh||a.xn||a.xq=? order by xxsh";
			outputValue = new String[]{"rnum","jztm","xthdrq","jcdj","ccdj","xxsh","jjf","pf"};
		}else if("wthd".equals(xxk)){
			sql = "select rownum rnum,a.* from szyq_xthddjb a where a.xh||a.xn||a.xq=? order by xxsh";
			outputValue = new String[]{"rnum","hdnr","xthdrq","jldj","jjf","pf","xxsh"};
		}else if("yybd".equals(xxk)){
			sql = "select rownum rnum,a.* from szyq_yybdjzb a where a.xh||a.xn||a.xq=? order by xxsh";
			outputValue = new String[]{"rnum","yybdnr","xthdrq","jjf","pf","xxsh"};
		}else{
			sql = "select rownum rnum,a.* from szyq_dshdjzb a where a.xh||a.xn||a.xq=? order by xxsh";
			outputValue = new String[]{"rnum","dsmc","dsrq","dsxd","sfhj","jjf","pf","xxsh" };
		}
		
		rs = dao.getArrayList(sql, new String[]{pk.toString()}, outputValue);
		return rs;
	}
	//���ѧ�Ż��Ϣ
	public ArrayList<HashMap<String, String>> dao_getZZnlDwr(String xh,String xn,String xq){
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		String sql="";
		String xsxq = "";
		try {
			xsxq = getXqdm(xq);
		} catch (SQLException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		StringBuffer pk = new StringBuffer();
		pk.append(xh).append(xn).append(xsxq);
		String[] outputValue;
		sql = "select rownum rnum,a.* from szyc_zznlfzb a where a.xh||a.xn||a.xq=? order by xxsh";
		outputValue = new String[]{"rnum","hdzt","hdrq","hddj","hdpf","jjf","shfz","xxsh" };
		rs = dao.getArrayList(sql, new String[]{pk.toString()}, outputValue);
		return rs;
	}
	/**
	 * ��ȡ�����������༶
	 * @param userName
	 * @return
	 * @throws SQLException 
	 */
	public List<String>  getBzrBjList(String userName) throws SQLException{
		String sql = "select bjdm from fdybjb where zgh=?";
		return  dao.getList(sql, new String[]{userName}, "bjdm");
	}
	
	/**
	 * ��ȡѧ������
	 * @param xqdm
	 * @return
	 * @throws SQLException 
	 */
	public String  getXqmc(String xqdm){
		String sql = "select xqmc from xqdzb where xqdm=?";
		return  dao.getOneRs(sql, new String[]{xqdm}, "xqmc");
	}
	/**
	 * ��ȡѧ�ڴ���
	 * @param xqdm
	 * @return
	 * @throws SQLException 
	 */
	public String  getXqdm(String xqmc) throws SQLException{
		String sql = "select xqdm from xqdzb where xqmc like ?";
		return  dao.getOneRs(sql, new String[]{xqmc}, "xqdm");
	}
	
	/**
	 * 5S��ֵ��ѯ
	 */
	public ArrayList<String[]> dao_Query5S(FiveSModel model,String userType,String userName){
		
		String xh = DealString.toGBK(model.getFivexh().trim());
		String xm = DealString.toGBK(model.getXm().trim());
		String bzr = " and exists (select 1 from fdybjb b where zgh like '"
				+ userName + "' and a.bjdm = b.bjdm)";
		xh = Base.isNull(xh) ? "%" : "%" + xh + "%";
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";
		
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		
		querry.append(Base.isNull(model.getXn())?"":" and a.xn='"+model.getXn()+"' ");
		querry.append(Base.isNull(model.getNj())?"":" and nj='"+model.getNj()+"' ");
		querry.append(Base.isNull(model.getXydm())?"":" and xydm='"+model.getXydm()+"' ");
		querry.append(Base.isNull(model.getZydm())?"":" and zydm='"+model.getZydm()+"' ");
		querry.append(Base.isNull(model.getBjdm())?"":" and bjdm='"+model.getBjdm()+"' ");
		querry.append(Base.isNull(model.getXq())?"":" and a.xq='"+getXqmc(model.getXq())+"' ");
		querry.append(" and a.xh like ?");
		querry.append(" and a.xm like ?");
		querry.append("bzr".equalsIgnoreCase(userType)?bzr:"");
		
		StringBuffer sql = new StringBuffer();
		sql.append("select a.*,nvl(b.zf,0) zf,nvl(c.zf,0) wsf from (select a.xh||xn||xq pk,a.xh,xn,(select xqmc from xqdzb b ");
		sql.append("where a.xq=b.xqdm) xq,xm,nj,xydm,xymc,zydm,zymc,bjdm,bjmc from ");
		sql.append("(select distinct xh,xn,xq from szyc_5sb) a left outer join view_xsxxb b on a.xh=b.xh) a ");
		
		sql.append("left join (select xh, xn, (select xqmc from xqdzb where xq = xqdm) xq, ");
		sql.append("sum(fz) zf from (select xh, xn, xq, case when jjf = '�ӷ�' then fz else '-' || fz end fz ");
		sql.append("from szyc_5sb) group by xh, xn, xq) b on a.xn = b.xn and a.xq = b.xq and a.xh = b.xh ");
		
		sql.append("left join (select xh, xn, (select xqmc from xqdzb where xq = xqdm) xq, ");
		sql.append("sum(fz) zf from (select xh, xn, xq, case when jjf = '�ӷ�' then fz else '-' || fz end fz ");
		sql.append("from szyc_5sb where xxsh='ͨ��') group by xh, xn, xq) c on a.xn = c.xn and a.xq = c.xq and a.xh = c.xh");
		sql.append(querry.toString());
		
		String[] inputValue = {xh,xm} ;
		String[] outputValue = {"pk","xh","xn","xq","xm","xymc","bjmc","zf","wsf"};
		ArrayList<String[]> rs = dao.rsToVator(sql.toString(), inputValue, outputValue);
		System.out.println(sql);
		return rs;
	}
	
	/**
	 * 5S��ֵ��˲�ѯ
	 * @throws Exception 
	 */
	public ArrayList<String[]> dao_View5S(FiveSModel model){
		
		String xh = DealString.toGBK(model.getFivexh().trim());
		String xm = DealString.toGBK(model.getXm().trim());
		String shzt = DealString.toGBK(model.getShzt());
		xh = Base.isNull(xh) ? "%" : "%" + xh + "%";
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";
		
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		
		querry.append(Base.isNull(model.getXn())?"":" and a.xn='"+model.getXn()+"' ");
		querry.append(Base.isNull(model.getNj())?"":" and nj='"+model.getNj()+"' ");
		querry.append(Base.isNull(model.getXydm())?"":" and xydm='"+model.getXydm()+"' ");
		querry.append(Base.isNull(model.getZydm())?"":" and zydm='"+model.getZydm()+"' ");
		querry.append(Base.isNull(model.getBjdm())?"":" and bjdm='"+model.getBjdm()+"' ");
		querry.append(Base.isNull(model.getXq())?"":" and a.xq='"+getXqmc(model.getXq())+"' ");
		querry.append(" and a.xh like ?");
		querry.append(" and a.xm like ?");
		
		StringBuffer sql = new StringBuffer();
		sql.append("select rownum r,a.* from (select a.*,case when b.wsh >0 and b.tg = 0 and b.btg=0 then 'δ���'");
		sql.append(" when b.wsh =0 and b.tg > 0 and b.btg=0 then 'ͨ��'");
		sql.append(" when b.wsh =0 and b.tg = 0 and b.btg>0 then '��ͨ��'");
		sql.append(" when b.tg <> 0 then '����ͨ��'");
		sql.append(" when b.wsh <>0 and b.tg = 0 and b.btg<>0 then '����δ���' end xxsh from (select a.*,nvl(b.zf,0) wsf,nvl(c.zf,0) zf");
		sql.append(" from (select a.xh||xn||xq pk,a.xh,xn,(select xqmc from xqdzb b ");
		sql.append(" where a.xq=b.xqdm) xq,xm,nj,xydm,xymc,zydm,zymc,bjdm,bjmc from ");
		sql.append(" (select distinct xh,xn,xq from szyc_5sb) a left outer join view_xsxxb b on a.xh=b.xh) a");
		sql.append(" left join (select xh, xn, (select xqmc from xqdzb where xq = xqdm) xq,");
		sql.append(" sum(fz) zf from (select xh, xn, xq, case when jjf = '�ӷ�' then fz else '-' || fz end fz");
		sql.append(" from szyc_5sb where xxsh = 'ͨ��') group by xh, xn, xq) b on a.xn = b.xn and a.xq = b.xq and a.xh = b.xh");
		sql.append(" left join (select xh, xn, (select xqmc from xqdzb where xq = xqdm) xq,");
		sql.append(" sum(fz) zf from (select xh, xn, xq, case when jjf = '�ӷ�' then fz else '-' || fz end fz");
		sql.append(" from szyc_5sb) group by xh, xn, xq) c on a.xn = c.xn and a.xq = c.xq and a.xh = c.xh");
		sql.append(querry.toString());
		sql.append(" ) a left join (select pk,sum(wsh) wsh,sum(tg) tg,sum(btg) btg");
		sql.append(" from (select pk, case when xxsh = 'δ���' then 1 else 0 end wsh,");
		sql.append(" case when xxsh = 'ͨ��' then 1 else 0 end tg,");
		sql.append(" case when xxsh = '��ͨ��' then 1 else 0 end btg");
		sql.append(" from (select xh || xn || xq pk, xxsh from szyc_5sb)) group by pk) b on a.pk = b.pk) a");
		sql.append(Base.isNull(shzt) ? "" : " where xxsh='" + shzt + "'");
		
		String[] inputValue = {xh,xm} ;
		String[] outputValue = { "pk", "xh", "xn", "xq", "xm", "xymc", "bjmc",
				"zf", "wsf", "xxsh" };
		ArrayList<String[]> rs = null;
		try {
			rs = CommonQueryDAO.commonQuery(sql.toString(), "", inputValue, outputValue, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(sql);
		return rs;
	}
	
	/**
	 * 5s��ֵ����
	 * 
	 * @throws SQLException
	 */
	public boolean dao_add5s(FiveSModel model, HttpServletRequest request)
			throws SQLException {
		boolean flg = false;
		dao = DAO.getInstance();
		String[] sqlList = new String[model.getFzxm().length + 1];
		StringBuffer sql = null;
		try {
			StringBuffer buff = new StringBuffer();
			buff.append(model.getXh()[0]).append(model.getXn()).append(
					model.getXq());
			StandardOperation.delete("szyc_5sb", "xh||xn||xq", buff.toString(),
					request);
		} catch (Exception e1) {
			// TODO �Զ����� catch ��
			e1.printStackTrace();
		}
		for (int i = 0; i < model.getFzxm().length; i++) {
			sql = new StringBuffer();
			sql.append("insert into szyc_5sb (xh,xn,xq,fzxm,jjf,fz,lrrq,yy,xxsh) values('");
			sql.append(model.getXh()[0]);
			sql.append("','");
			sql.append(model.getXn());
			sql.append("','");
			sql.append(model.getXq());
			sql.append("','");
			sql.append(model.getFzxm()[i]);
			sql.append("','");
			sql.append(model.getJjf()[i]);
			sql.append("','");
			sql.append(model.getFz()[i]);
			sql.append("','");
			sql.append(model.getLrrq()[i]);
			sql.append("','");
			sql.append(DealString.toGBK(model.getYy()[i]).replace("'","��"));
			sql.append("','");
			sql.append(DealString.toGBK(model.getXxsh()[i]));
			sql.append("')");
			if (StringUtils.isNull(model.getFzxm()[i])) {// Ϊ���򲻼�¼
				sql = new StringBuffer("");
			}
			sqlList[i] = sql.toString();
		}
		// String updateSql = "update sxzzddszb set shzt = 'δ���' where xh =
		// '"+model.getXh()+"' and xn = '"+model.getXn()+"'";
		// sqlList[model.getFs().length] = updateSql;
		int[] res = dao.runBatch(sqlList);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}

		return flg;
	}
	
	/**
	 * 5s��ֵ���
	 * 
	 * @throws SQLException
	 */
	public boolean dao_sh5s(String[] key, String shzt) throws SQLException {
		boolean flg = false;
		dao = DAO.getInstance();
		if (!Base.isNull(shzt)) {
			shzt = "yes".equalsIgnoreCase(shzt) ? "ͨ��" : "��ͨ��";
		}
		StringBuffer sql = new StringBuffer();
		String[] sqlList = null;
		if (key != null && key.length > 0) {
			sqlList = new String[key.length];
			for (int i = 0; i < key.length; i++) {
				sql = new StringBuffer();
				sql.append("update szyc_5sb set xxsh='");
				sql.append(shzt);
				sql.append("' where id='");
				sql.append(key[i]);
				sql.append("'");
				sqlList[i] = sql.toString();
			}
		}
		int[] res = dao.runBatch(sqlList);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}

		return flg;
	}
	
	/**
	 * ������ͨ��5s��
	 * 
	 * @throws SQLException
	 * 
	 */
	public boolean dao_get5sfzList(String[] key, String pk, String kjf)
			throws SQLException {

		String sql = "select jjf,fz from szyc_5sb where xxsh='ͨ��' and xh||xn||xq=?";
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { pk }, new String[] { "jjf", "fz" });
		float zf = 0;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String jjf = list.get(i).get("jjf");
				String fz = list.get(i).get("fz");
				if ("�ӷ�".equalsIgnoreCase(jjf)) {
					zf += Base.isNull(fz) ? 0 : Float.parseFloat(fz);
				} else {
					zf -= Base.isNull(fz) ? 0 : Float.parseFloat(fz);
				}
			}
		}
		if (key != null && key.length > 0) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < key.length; i++) {
				if (i == 0) {
					sb.append("(id='" + key[i] + "'");
				} else if (i == key.length - 1) {
					sb.append(" or id='" + key[i] + "')");
				} else {
					sb.append(" or id='" + key[i] + "'");
				}
			}
			if (key.length == 1) {
				sb.append(")");
			}
			sql = "select jjf,fz from szyc_5sb where xxsh<>'ͨ��' and xh||xn||xq=? and "
					+ sb.toString();
			List<HashMap<String, String>> jjfList = dao.getList(sql.toString(),
					new String[] { pk }, new String[] { "jjf", "fz" });
			if (jjfList != null && jjfList.size() > 0) {
				for (int i = 0; i < jjfList.size(); i++) {
					String jjf = jjfList.get(i).get("jjf");
					String fz = jjfList.get(i).get("fz");
					if ("�ӷ�".equalsIgnoreCase(jjf)) {
						zf += Base.isNull(fz) ? 0 : Float.parseFloat(fz);
					} else {
						zf -= Base.isNull(fz) ? 0 : Float.parseFloat(fz);
					}
				}
			}
		}
		if (!Base.isNull(kjf)) {
			if (zf > Float.parseFloat(kjf)) {
				return false;
			}
		}

		return true;
	}
	
	/**
	 * 5s��ֵɾ��
	 * 
	 * @throws SQLException
	 */
	public boolean dao_del5s(String pks) throws SQLException {

		boolean flg = false;
		StringBuffer sb = new StringBuffer();
		String[] keys = pks.split("!!#!!");
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// �õ�����
			sql = "delete from szyc_5sb where xh||xn||xq = '" + pk + "'";
			// ��������ϳ�sql���
			sb.append(sql).append("!!#!!");
		}
		// sql����ֳ�����
		String[] pksql = sb.toString().split("!!#!!");
		int[] res = dao.runBatch(pksql);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}

		return flg;
	}
	
	/**
	 * ���ѧ��5s��ֵ
	 * 
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> dao_get5s(String pk,
			HttpServletRequest request) throws SQLException {
		String sql = "select fzxm,jjf,fz,lrrq,yy,xxsh from szyc_5sb where xh||xn||xq=? order by xxsh,fzxm";
		return dao.getList(sql, new String[] { pk }, new String[] { "fzxm",
				"jjf", "fz","lrrq","yy","xxsh" });
	}
	
	/**
	 * ��ø�ѧ���·�
	 * 
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> dao_getYf(String xq) throws SQLException {
		String xqmc=getXqmc(xq);
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		if("��".equalsIgnoreCase(xqmc)){
			for(int i=2;i<7;i++){
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("yfdm", "0" + String.valueOf(i));
				map.put("yfmc", String.valueOf(i)+"��");
				list.add(map);
			}
		} else if ("��".equalsIgnoreCase(xqmc)) {
			for (int i = 9; i <= 12; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				if (i < 10) {
					map.put("yfdm", "0" + String.valueOf(i));
				} else {
					map.put("yfdm", String.valueOf(i));
				}
				map.put("yfmc", String.valueOf(i) + "��");
				list.add(map);
				if (i == 12) {
					map = new HashMap<String, String>();
					map.put("yfdm", "1");
					map.put("yfmc", "1��");
					list.add(map);
				}
			}
		}
		return list;
	}
	
	/**
	 * �������
	 * 
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> dao_getZc() throws SQLException {
		String sql = "select xqzs from xtszb where rownum=1";
		String xqzs = dao.getOneRs(sql, new String[] {}, "xqzs");
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		if (!Base.isNull(xqzs)) {
			for (int i = 1; i <= Integer.parseInt(xqzs); i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("zcdm", String.valueOf(i));
				map.put("zcmc", "��" + String.valueOf(i) + "��");
				list.add(map);
			}
		}
		return list;
	}
	
	/**
	 * ��ð༶����
	 * 
	 * @throws SQLException
	 */
	public String dao_getBjmc(String[] arrBj) throws SQLException {
		StringBuffer sq = new StringBuffer();
		StringBuffer bjmc = new StringBuffer();
		for (int i = 0; i < arrBj.length; i++) {
			if (i == 0) {
				sq.append(" where bjdm = '" + arrBj[i] + "'");
			} else {
				sq.append(" or bjdm = '" + arrBj[i] + "'");
			}
		}
		if (arrBj.length == 1) {
			//sq.append(")");
		}
		String sql = "select bjmc from view_njxyzybj";
		List<String> bjInfo = dao.getList(sql + sq.toString(), new String[] {},
				"bjmc");

		if (bjInfo != null && bjInfo.size() > 0) {
			for (int i = 0; i < bjInfo.size(); i++) {
				if (i == bjInfo.size() - 1) {
					bjmc.append(bjInfo.get(i));
				} else {
					bjmc.append(bjInfo.get(i) + ",");
				}
			}
		}
		return bjmc.toString();
	}
		
	/**
	 * ���5sѧ�ڻ���List
	 * 
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> dao_getXqhzList(String xn,String xq,String[] arrBj)
			throws SQLException {
		List<HashMap<String, String>> xqList = dao_getYf(xq);
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from(select a.xh,a.xm,a.bjdm,a.one,a.two,a.thr,a.four,a.fiv,(b.jf - b.kf) grf,");
		sql.append(" (a.one + a.two + a.thr + a.four + a.fiv) zf from (select xh,");
		sql.append(" xm,bjdm, sum(onejf) - sum(onekf) one, sum(twojf) - sum(twokf) two,");
		sql.append(" sum(thrjf) - sum(thrkf) thr, sum(forjf) - sum(forkf) four,");
		sql.append(" sum(fivjf) - sum(fivkf) fiv from (select xh,xm,bjdm,");
		sql.append(" case when yf = '"+xqList.get(0).get("yfdm")+"' then jf else 0 end onejf,");
		sql.append(" case when yf = '"+xqList.get(0).get("yfdm")+"' then kf else 0 end onekf,");
		sql.append(" case when yf = '"+xqList.get(1).get("yfdm")+"' then jf else 0 end twojf,");
		sql.append(" case when yf = '"+xqList.get(1).get("yfdm")+"' then kf else 0 end twokf,");
		sql.append(" case when yf = '"+xqList.get(2).get("yfdm")+"' then jf else 0 end thrjf,");
		sql.append(" case when yf = '"+xqList.get(2).get("yfdm")+"' then kf else 0 end thrkf,");
		sql.append(" case when yf = '"+xqList.get(3).get("yfdm")+"' then jf else 0 end forjf,");
		sql.append(" case when yf = '"+xqList.get(3).get("yfdm")+"' then kf else 0 end forkf,");
		sql.append(" case when yf = '"+xqList.get(4).get("yfdm")+"' then jf else 0 end fivjf,");
		sql.append(" case when yf = '"+xqList.get(4).get("yfdm")+"' then kf else 0 end fivkf");
		sql.append(" from (select xh, xm,bjdm, yf, sum(jf) jf, sum(kf) kf");
		sql.append(" from (select xh,xm,bjdm,yf,case when jjf = '�ӷ�' then fz else");
		sql.append(" '0' end jf,case when jjf = '����' then fz else '0'");
		sql.append(" end kf from (select a.xh, b.xm, b.bjdm,a.xn, a.xq, a.jjf, a.fz,a.xxsh,");
		sql.append(" substr(a.lrrq, 5, 2) yf from szyc_5sb a left join view_xsjbxx b ");
		sql.append(" on a.xh = b.xh) where xn = '"+xn+"' and xq = '"+xq+"' and xxsh='ͨ��' order by yf)");
		sql.append(" group by xh, xm, yf,bjdm))  group by xh, xm,bjdm) a left join ");
		sql.append(" (select xh, xn, xq, yf, sum(jf) jf, sum(kf) kf from (select xh, ");
		sql.append(" xn, xq, yf, case when jjf = '�ӷ�' then fz else '0' end jf, ");
		sql.append(" case when jjf = '����' then fz else '0' end kf from (select a.xh, ");
		sql.append(" a.xn, a.xq, a.jjf, a.fz, a.xxsh, substr(a.lrrq, 5, 2) yf  ");
		sql.append(" from szyc_5sb a where a.fzxm = 'grszf' and a.xxsh = 'ͨ��')) group by xn, xq, xh, yf) ");	
		sql.append(" b on a.xh = b.xh and b.xn = '"+xn+"' and b.xq = '"+xq+"') where");
		for (int i = 0; i < arrBj.length; i++) {
			if (i == 0) {
				sql.append(" bjdm = '" + arrBj[i] + "'");
			} else {
				sql.append(" or bjdm = '" + arrBj[i] + "'");
			}
		}
		//System.out.println(sql.toString());
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] {"xh","xm","one","two","thr","four","fiv","grf","zf"});
		return list;
	}
	
	/**
	 * ���5s�¶Ȼ���List
	 * 
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> dao_getYdhzList(String xn, String xq,
			String yf, String[] arrBj) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("select xh, xm, lrrq, yy, fzxm,jf,kf,(jf-kf) zf");
		sql.append(" from (select xh, xm, yy, fzxm, lrrq, bjdm, yf, jf, kf");
		sql.append(" from (select xh,yy,xm,fzxm,lrrq,bjdm,yf,");
		sql.append(" case when jjf = '�ӷ�' then fz else '0' end jf,");
		sql.append(" case when jjf = '����' then fz else '0' end kf from (select a.xh, ");
		sql.append(" case when a.fzxm='grszf' then '�������ʷ�'");
	    sql.append(" when a.fzxm='jsssf' then '����������5S��' when a.fzxm='ktf' then '����5S��'");
	    sql.append(" when a.fzxm='cxf' then '���ŷ�' when a.fzxm='qtf' then '������' end fzxm, ");
		sql.append(" a.yy, b.xm, b.bjdm, a.lrrq,a.xn, a.xq, a.jjf, a.fz, a.xxsh, substr(a.lrrq, 5, 2) yf");
		sql.append(" from szyc_5sb a left join view_xsjbxx b on a.xh = b.xh)");
		sql.append(" where xn = '" + xn + "' and xq = '" + xq
				+ "' and xxsh = 'ͨ��'");
		sql.append("order by yf)) where yf = '" + yf + "' and (");
		for (int i = 0; i < arrBj.length; i++) {
			if (i == 0) {
				sql.append(" bjdm = '" + arrBj[i] + "'");
			} else if (i == arrBj.length - 1) {
				sql.append(" or bjdm = '" + arrBj[i] + "')");
			} else {
				sql.append(" or bjdm = '" + arrBj[i] + "'");
			}
			if(arrBj.length ==1){
				sql.append(")");
			}
		}
		//System.out.println(sql.toString());
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "xh", "xm", "lrrq", "yy",
						"fzxm", "jf", "kf", "zf" });
		return list;
	}
	
	/**
	 * ���5s����List
	 * 
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> dao_get5shzList(String xn, String xq,
			String kssj,String jssj, String[] arrBj) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append(" select xymc,bjmc,xh,yy,xm,lrrq,bjdm,");
		sql.append(" case when fzxm='grszf' then '�������ʷ�'");
	    sql.append(" when fzxm='jsssf' then '����������5S��' when fzxm='ktf' then '����5S��'");
	    sql.append(" when fzxm='cxf' then '���ŷ�' when fzxm='qtf' then '������' end fzxm, ");
		sql.append(" case when jjf = '�ӷ�' then fz else '0' end jf,");
		sql.append(" case when jjf = '����' then fz else '0' end kf");
		sql.append(" from (select b.xymc,b.bjmc,a.xh,a.fzxm,a.yy,b.xm,");
		sql.append(" b.bjdm,a.lrrq,a.xn,a.xq,a.jjf,a.fz,a.xxsh,");
		sql.append(" substr(a.lrrq, 5, 2) yf from szyc_5sb a");
		sql.append(" left join view_xsjbxx b on a.xh = b.xh)");
		sql.append(" where xn = '"+xn+"' and xq = '"+xq+"' and xxsh = 'ͨ��'");
		sql.append(" and lrrq >= '" + kssj + "' and lrrq <'"+jssj+"' and(");
		for (int i = 0; i < arrBj.length; i++) {
			if (i == 0) {
				sql.append(" bjdm = '" + arrBj[i] + "'");
			} else if (i == arrBj.length - 1) {
				sql.append(" or bjdm = '" + arrBj[i] + "')");
			} else {
				sql.append(" or bjdm = '" + arrBj[i] + "'");
			}
			if(arrBj.length ==1){
				sql.append(")");
			}
		}
		//System.out.println(sql.toString());
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "xymc", "bjmc", "xh", "xm",
						"lrrq", "yy", "fzxm", "kf", "jf" });
		return list;
	}
	
	/**
	 * ��ñ�ѧ����ʼ����
	 * 
	 * @throws SQLException
	 */
	public String dao_getQsrq() {
		String sql = "select qsrq from xtszb where rownum = 1";
		return dao.getOneRs(sql, new String[] {}, "qsrq");

	}

	/**
	 * ���ѧ��������Ϣ
	 * 
	 */
	public HashMap<String, String> dao_getStuInfo(String xh, String pk,String tableName) {
		String sql = "select xh,xm,xb,xymc,zymc,bjmc from view_xsjbxx where xh=?";
		HashMap<String, String> map1 = dao.getMap(sql, new String[] { xh },
				new String[] { "xh", "xm", "xb", "xymc", "zymc", "bjmc" });
		sql = "select distinct(xn) xn,xq xqdm,(select xqmc from xqdzb where xqdm=xq)xq from "+tableName+" where xh||xn||xq=?";
		HashMap<String, String> map2 = dao.getMap(sql, new String[] { pk },
				new String[] { "xn", "xq" });
		map1.put("xn", map2.get("xn"));
		map1.put("xqdm", map2.get("xqdm"));
		map1.put("xq", map2.get("xq"));
		return map1;
	}
	
	/**
	 * 5s�����Ϣ�б�
	 */
	public ArrayList<String[]> dao_5sList(String pk){
        StringBuffer sql = new StringBuffer();
        String[]colList=new String[]{"id","r","fzxm","jjf","fz","lrrq","yy","xxsh"};
        sql.append("select t.*,rownum r from (select id, case when fzxm='grszf' then '�������ʷ�'");
        sql.append(" when fzxm='jsssf' then '����������5S��' when fzxm='ktf' then '����5S��'");
        sql.append(" when fzxm='cxf' then '���ŷ�' when fzxm='qtf' then '������' end fzxm, ");
        sql.append(" fz, jjf, (select mc from szyc_jjfyyb where dm = yy) yy, lrrq, xxsh from szyc_5sb where xh||xn||xq = ? order by xxsh) t");
        //System.out.println(sql);
        return dao.rsToVator(sql.toString(), new String[]{pk},colList);
	}
	
	/**
	 * 5s��˻�����Ϣ
	 */
	public HashMap<String, String> dao_5sXsInfo(String pk) {
		StringBuffer sql = new StringBuffer();
		String[] colList = new String[] { "xh", "xb", "xm", "xymc", "zymc",
				"bjmc", "xq", "xn" };
		sql.append("select a.xh,b.xm,b.xb,b.xymc,b.zymc,b.bjmc,a.xn,");
		sql.append(" (select xqmc from xqdzb where xqdm = a.xq) xq");
		sql.append(" from szyc_5sb a, view_xsjbxx b where a.xh = b.xh");
		sql.append(" and a.xh || a.xn || a.xq = ?  and rownum = 1");
		return dao.getMap(sql.toString(), new String[] { pk }, colList);
	}
	
	public String[] getFzsxForMkdm(String mkdm) {
		String sql = "select zxmjcf,zxmzgf from szgy_zhszycfsszb where zxmdm = ?";
		return dao.getOneRs(sql, new String []{mkdm}, new String[]{"zxmjcf","zxmzgf"});
	}
	/**
	 * ���ʵ����ֵ����
	 * 
	 * @throws SQLException
	 */
	public boolean dao_addShsj(ShsjModel model, HttpServletRequest request)
			throws SQLException {
		boolean flg = false;
		dao = DAO.getInstance();
		String[] sqlList = new String[model.getHdnr().length + 1];
		StringBuffer sql = null;
		try {
			StringBuffer buff = new StringBuffer();
			buff.append(model.getXh()[0]).append(model.getXn()).append(
					model.getXq());
			StandardOperation.delete("SZYC_SHSJFZB", "xh||xn||xq", buff.toString(),
					request);
		} catch (Exception e1) {
			// TODO �Զ����� catch ��
			e1.printStackTrace();
		}
		for (int i = 0; i < model.getHdnr().length; i++) {
			sql = new StringBuffer();
			sql.append("insert into SZYC_SHSJFZB(XH,XN,XQ,HDNR,HDRQ,HDDD,HDSJ,SHFZ,JJF,XXSH)values( '");
			sql.append(model.getXh()[0]);
			sql.append("','");
			sql.append(model.getXn());
			sql.append("','");
			sql.append(model.getXq());
			sql.append("','");
			sql.append(DealString.toGBK(model.getHdnr()[i]));
			sql.append("','");
			sql.append(DealString.toGBK(model.getHdrq()[i]));
			sql.append("','");
			sql.append(DealString.toGBK(model.getHddd()[i]));
			sql.append("','");
			sql.append(DealString.toGBK(model.getHdsj()[i]));
			sql.append("','");
			sql.append(DealString.toGBK(model.getShfz()[i]));
			sql.append("','");
			sql.append(DealString.toGBK(model.getJjf()[i]));
			sql.append("','");
			sql.append(DealString.toGBK(model.getShType()[i]));
			sql.append("')");
			if (Base.isNull(model.getHdnr()[i])) {// Ϊ���򲻼�¼
				sql = new StringBuffer("");
			}
			sqlList[i] = sql.toString();
		}		
		int[] res = dao.runBatch(sqlList);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}
		return flg;
	}
	/**
	 * ���ѧ�����ʵ����ֵ
	 * 
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> dao_getShsj(String pk,
			HttpServletRequest request) throws SQLException {
		String sql = "select hdnr,hdrq,hddd,hdsj,shfz,lrrq,xxsh,jjf from szyc_shsjfzb where xh||xn||xq=? order by xxsh asc";
		return dao.getList(sql, new String[] { pk }, new String[] { "hdnr","hddd","hdrq","hdsj","jjf","shfz","xxsh"});
	}
	/**
	 * ���ʵ�����ѯҳ��
	 */
	public ArrayList<String[]> dao_shsjSearch(ShsjModel model,String userType,String userName){
		DAO dao      = DAO.getInstance();
		ArrayList<String[]> result = new ArrayList<String[]>();
		String xh = "";
		if (model.getXh() != null && model.getXh().length > 0) {
			xh = model.getXh()[0];
		}
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xm   = model.getXm();
		String xn   = model.getXn();
		String xq   = model.getXq();
		xh = Base.isNull(xh) ? "%" : xh ;
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";		
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		StringBuffer querry2 = new StringBuffer(" where 1=1 ");
		querry.append(" and a.xh like ?");
		querry.append(" and xm like ?");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");

		querry.append("bzr".equalsIgnoreCase(userType) ? " and exists (select 1 from fdybjb b where zgh like '"+ userName + "' and a.bjdm = b.bjdm)": "");
		querry2.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry2.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");	
		String[] colList = new String[]{"pk","xn","xq","xh","xm","nj","bjmc","zf","hdf"};
		StringBuffer  sql = new StringBuffer();
		sql.append("select b.zf,nvl(c.hdf,0) hdf, a.xh||a.xn||a.xq pk,a.xn,(select xqmc from xqdzb d where a.xq=d.xqdm)xq,a.xh,nj,xydm,zydm,bjdm,xm,xymc,zymc,bjmc, ");
		sql.append("( case when (select count(xh) from szyc_shsjfzb d "+querry2+" and d.xh=a.xh and xxsh='ͨ��')=a.cout then 'ȫ��ͨ��' ");
		sql.append("when (select count(xh) from szyc_shsjfzb d "+querry2+" and d.xh=a.xh and xxsh='δ���')=a.cout then 'δ���'  ");
		sql.append("when (select count(xh) from szyc_shsjfzb d "+querry2+" and d.xh=a.xh and xxsh='��ͨ��')=a.cout then 'ȫ����ͨ��' else '����ͨ��' end  ");
		sql.append(")sh from (select a.xn,a.xq,a.xh,nj,xydm,zydm,bjdm,xm,xymc,bjmc,zymc, ");
		sql.append("(select count(xh) from szyc_shsjfzb d  "+querry2+"  and d.xh=a.xh )cout ");
		sql.append("from (select  distinct xh,xn,xq from szyc_shsjfzb  "+querry2+" )a left join view_xsxxb b on a.xh=b.xh )a ");
		
		sql.append("left join (select xh, xn, xq, ");
		sql.append("sum(pf) zf from (select xh, xn, xq, case when jjf = '�ӷ�' then shfz else '-' || shfz end pf ");
		sql.append("from szyc_shsjfzb) group by xh, xn, xq) b on a.xn = b.xn and a.xq = b.xq and a.xh = b.xh ");
		
		sql.append("left join (select xh, xn,xq, ");
		sql.append("sum(pf) hdf from (select xh, xn, xq, case when jjf = '�ӷ�' then shfz else '-' || shfz end pf ");
		sql.append("from szyc_shsjfzb where xxsh='ͨ��') group by xh, xn, xq) c on a.xn = c.xn and a.xq = c.xq and a.xh = c.xh");
		result = dao.rsToVator(sql+querry.toString(), new String[]{xh,xm},colList);
		return result;
	}
		
	public ArrayList<String[]> getPointSetting() {
		String sql = "select zxmdm,zxmjcf,zxmzgf,mkmc from szgy_zhszycfsszb";
		return dao.rsToVator(sql, new String []{}, new String[]{"zxmdm","mkmc","zxmjcf","zxmzgf"});
	}
	
	public boolean updatePointSetting(FsszModel model) throws SQLException {
		String [] mkdms = model.getMkdm();
		String [] jcfzs = model.getJcfz();
		String [] zgfzs = model.getZgfz();
		String [] sqlmap = new String [mkdms.length];
		for(int i= 0;i<mkdms.length;i++){
			if(mkdms[i]!=null&&!mkdms[i].equalsIgnoreCase("")){
			String sqltmp = "update szgy_zhszycfsszb set zxmjcf = '"+jcfzs[i]+"',zxmzgf = '"+zgfzs[i]+"' where zxmdm='"+mkdms[i]+"'";
			sqlmap[i]=sqltmp;
			}
		}
		dao.runBatch(sqlmap);
		return true;
	}
	/**
	 * ���ʵ�����˲�ѯ
	 */
	public ArrayList<String[]> dao_shsjChkSearch(ShsjModel model){
		ArrayList<String[]> result = new ArrayList<String[]>();
		String xh = "";
		if (model.getXh() != null && model.getXh().length > 0) {
			xh = model.getXh()[0];
		}
		
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xm   = model.getXm();
		String xn   = model.getXn();
		String xq   = model.getXq();
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		StringBuffer querry2 = new StringBuffer(" where 1=1 ");
		querry.append(Base.isNull(xh)?"":" and a.xh='"+xh.trim()+"' ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
		querry.append(Base.isNull(xm)?"":" and xm like '%"+xm.trim()+"%' ");	
		querry2.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry2.append(Base.isNull(xn)?"":" and xq='"+xq+"' ");	
		String[] colList = new String[]{"pk","xn","xq","xh","xm","nj","bjmc","zf","hdf","sh"};
		StringBuffer  sql = new StringBuffer();
		sql.append("select rownum r,a.xh||a.xn||a.xq pk,a.xn,(select xqmc from xqdzb d where a.xq=d.xqdm)xq,a.xh,nj,xydm,zydm,bjdm,xm,xymc,zymc,bjmc,b.zf,nvl(c.hdf,0) hdf, ");
		sql.append("( case when (select count(xh) from szyc_shsjfzb d "+querry2+" and d.xh=a.xh and xxsh='ͨ��')=a.cout then 'ȫ��ͨ��' ");
		sql.append("when (select count(xh) from szyc_shsjfzb d "+querry2+" and d.xh=a.xh and xxsh='δ���')=a.cout then 'δ���'  ");
		sql.append("when (select count(xh) from szyc_shsjfzb d "+querry2+" and d.xh=a.xh and xxsh='��ͨ��')=a.cout then 'ȫ����ͨ��' else '����ͨ��' end  ");
		sql.append(")sh from (select a.xn,a.xq,a.xh,nj,xydm,zydm,bjdm,xm,xymc,bjmc,zymc, ");
		sql.append("(select count(xh) from szyc_shsjfzb d  "+querry2+"  and d.xh=a.xh )cout ");
		sql.append("from (select  distinct xh,xn,xq from szyc_shsjfzb  "+querry2+" )a left join view_xsxxb b on a.xh=b.xh )a ");
		
		sql.append("left join (select xh, xn, xq, ");
		sql.append("sum(pf) zf from (select xh, xn, xq, case when jjf = '�ӷ�' then shfz else '-' || shfz end pf ");
		sql.append("from szyc_shsjfzb) group by xh, xn, xq) b on a.xn = b.xn and a.xq = b.xq and a.xh = b.xh ");
		
		sql.append("left join (select xh, xn,xq, ");
		sql.append("sum(pf) hdf from (select xh, xn, xq, case when jjf = '�ӷ�' then shfz else '-' || shfz end pf ");
		sql.append("from szyc_shsjfzb where xxsh='ͨ��') group by xh, xn, xq) c on a.xn = c.xn and a.xq = c.xq and a.xh = c.xh");
		
		try {
			result = CommonQueryDAO.commonQuery(sql.toString(), querry.toString(), new String[]{},colList, model);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}
	/**
	 * ���ʵ������
	 */
	public boolean dao_shsjChk(String pkVStr,String shType) throws Exception{
		DAO dao = DAO.getInstance();	
		shType = ("yes".equalsIgnoreCase(shType))?"ͨ��":"��ͨ��";
		String[] pkValueA = pkVStr.split("!!");		
		String[] updtPkSql  = new String[pkValueA.length];	
		for (int i = 0; i < pkValueA.length; i++) {
			updtPkSql[i] = Base.isNull(pkValueA[i])?"":"update szyc_shsjfzb set xxsh='"+shType+"'  where id= '"+pkValueA[i]+"'";							
		}              
		boolean doFlag = false;
		int[] array = dao.runBatch(updtPkSql);
		doFlag = dao.checkBatch(array);   
		return doFlag;
	}
	/**
	 * ���ʵ�����Ϣ�б�
	 */
	public ArrayList<String[]> dao_shsjViewAndChkSearch(String xh,String xn,String xq){
        StringBuffer sql = new StringBuffer();
        String[]colList=new String[]{"id","r","hdnr","hddd","hdrq","hdsj","jjf","shfz","xxsh"};
        sql.append("select id, rownum r,(case when length(hdnr)>50 then substr(hdnr,0,50)||'...' else hdnr end)hdnr,hdrq,hddd,hdsj,jjf,shfz,xxsh from szyc_shsjfzb where xh=? and xn=? and xq=?");
        return dao.rsToVator(sql.toString(), new String[]{xh,xn,xq},colList);
	}	
	/**
	 * ���ʵ�����Ϣɾ��
	 */
	public String dao_shsjDel(String[] keys){
		StringBuffer sb = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// �õ�����
			sql = "delete from szyc_shsjfzb where xh||xn||xq = '" + pk + "'";
			// ��������ϳ�sql���
			sb.append(sql).append("!!#!!");
		}
		// sql����ֳ�����
		pksql = sb.toString().split("!!#!!");
		int[] judge = null;
		try {
			judge = dao.runBatch(pksql);
		} catch (SQLException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		String whichpk = "";
		// �����һ��ɾ��ʧ��
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] < 0) {
				whichpk = whichpk + " ��" + String.valueOf(i) + "������ɾ��ʧ��;\n";
			}
		}
		return whichpk;
	}
	/**
	 * �÷����Ʒ���
	 * @param mkType zznl"��֯����",shsj"���ʵ��"
	 * @param id
	 * @param pkValue
	 * @return
	 */
	public String getPointLimitXthd(String xxk,String id,String pkValue){ 
		String retStr="";
		float jcf=0;//������
		float zgf=0;//��߷�
		float shzf=0;//�����ܷ�
		String tableName = "szyq_dshdjzb";
		String xxklx="";
		if("ivlt".equals(xxk)){
			tableName = "szyq_ivtltb";
			xxklx="ivtlt";
		}else if("wthd".equals(xxk)){
			tableName = "szyq_xthddjb";
			xxklx="wthd";
		}else if("yybd".equals(xxk)){
			tableName = "szyq_yybdjzb";
			xxklx="yybd";
		}else{
			tableName = "szyq_dshdjzb";
			xxklx="dshd";
		}
		if(StringUtils.isNotNull(xxk)){//��֯����
			String pkvString = id;
			//��ͨ���������
			String sumjf = dao.getOneRs("select nvl(sum(pf),0)sum  from "+tableName+" where xh||xn||xq=? and jjf='�ӷ�' and id not in("+pkvString+") and xxsh='ͨ��' ", new String[]{pkValue},"sum");
			String sumkf = dao.getOneRs("select nvl(sum(pf),0)sum  from "+tableName+" where xh||xn||xq=? and jjf='����' and id not in("+pkvString+") and xxsh='ͨ��' ", new String[]{pkValue},"sum");
			sumjf  = Base.isNull(sumjf)?"0":sumjf;
			sumkf = Base.isNull(sumkf)?"0":sumkf;
			String sum =String.valueOf(Float.parseFloat(sumjf)-Float.parseFloat(sumkf));
			//��ѡ�м�¼�������
			String sumtjf = dao.getOneRs("select nvl(sum(pf),0)sum  from "+tableName+" where xh||xn||xq=? and jjf='�ӷ�' and id in("+pkvString+") ", new String[]{pkValue},"sum");
			String sumtkf = dao.getOneRs("select nvl(sum(pf),0)sum  from "+tableName+" where xh||xn||xq=? and jjf='����' and id in("+pkvString+") ", new String[]{pkValue},"sum");
			sumtjf  = Base.isNull(sumtjf)?"0":sumtjf;
			sumtkf = Base.isNull(sumtkf)?"0":sumtkf;
			String sumt =String.valueOf(Float.parseFloat(sumtjf)-Float.parseFloat(sumtkf));
			String[] fzValue = getFzsxForMkdm(xxklx);									
			shzf = Float.parseFloat(sum)+Float.parseFloat(sumt);
			if(fzValue!=null){
				jcf = Float.parseFloat(Base.isNull(fzValue[0])?"0":fzValue[0]);
				zgf = Float.parseFloat(Base.isNull(fzValue[1])?"0":fzValue[1]);
			}
			if(shzf+jcf>zgf){
				if("ivlt".equals(xxk)){
					retStr = "IVT��̳�����֣�"+jcf+" ��߷֣�"+zgf+" �����ύ�ܷ��ѳ�����߷֣���ȷ�Ϻ������ύ��";
				}else if("wthd".equals(xxk)){
					retStr = "���Ż�����֣�"+jcf+" ��߷֣�"+zgf+" �����ύ�ܷ��ѳ�����߷֣���ȷ�Ϻ������ύ��";
				}else if("yybd".equals(xxk)){
					retStr = "���Ա������֣�"+jcf+" ��߷֣�"+zgf+" �����ύ�ܷ��ѳ�����߷֣���ȷ�Ϻ������ύ��";
				}else{
					retStr = "���������֣�"+jcf+" ��߷֣�"+zgf+" �����ύ�ܷ��ѳ�����߷֣���ȷ�Ϻ������ύ��";
				}
				retStr+="\n\n��ʽ��������+��ͨ���÷�+��ѡ��¼�÷�<=��߷�";
			}
		} 
		return retStr;
	}
	/**
	 * �÷����Ʒ���
	 * @param mkType zznl"��֯����",shsj"���ʵ��"
	 * @param id
	 * @param pkValue
	 * @return
	 */
	public String getPointLimit(String mkType,String id,String pkValue){
		String retStr="";
		float jcf=0;//������
		float zgf=0;//��߷�
		float shzf=0;//�����ܷ�
		if("zznl".equalsIgnoreCase(mkType)){//��֯����
//			String[] pkvTem = id.split("!!");
			String pkvString = id;
//			for(int i=0;i<pkvTem.length;i++){
//				pkvString += pkvTem[i];
//				pkvString += "',";
//			}
//			pkvString.subSequence(0,pkvString.length()-1);
			//��ͨ���������
			String sumjf = dao.getOneRs("select nvl(sum(shfz),0)sum  from szyc_zznlfzb where xh||xn||xq=? and jjf='�ӷ�' and id not in("+pkvString+") and xxsh='ͨ��' ", new String[]{pkValue},"sum");
			String sumkf = dao.getOneRs("select nvl(sum(shfz),0)sum  from szyc_zznlfzb where xh||xn||xq=? and jjf='�ӷ�' and id not in("+pkvString+") and xxsh='ͨ��' ", new String[]{pkValue},"sum");
			sumjf  = Base.isNull(sumjf)?"0":sumjf;
			sumkf = Base.isNull(sumkf)?"0":sumkf;
			String sum =String.valueOf(Float.parseFloat(sumjf)-Float.parseFloat(sumkf));
			//��ѡ�м�¼�������
			String sumtjf = dao.getOneRs("select nvl(sum(shfz),0)sum  from szyc_zznlfzb where xh||xn||xq=? and jjf='�ӷ�' and id in("+pkvString+") ", new String[]{pkValue},"sum");
			String sumtkf = dao.getOneRs("select nvl(sum(shfz),0)sum  from szyc_zznlfzb where xh||xn||xq=? and jjf='����' and id in("+pkvString+") ", new String[]{pkValue},"sum");
			sumtjf  = Base.isNull(sumtjf)?"0":sumtjf;
			sumtkf = Base.isNull(sumtkf)?"0":sumtkf;
			String sumt =String.valueOf(Float.parseFloat(sumtjf)-Float.parseFloat(sumtkf));
			String[] fzValue = getFzsxForMkdm(mkType);									
			shzf = Float.parseFloat(sum)+Float.parseFloat(sumt);
			if(fzValue!=null){
				jcf = Float.parseFloat(Base.isNull(fzValue[0])?"0":fzValue[0]);
				zgf = Float.parseFloat(Base.isNull(fzValue[1])?"0":fzValue[1]);
			}
			if(shzf+jcf>zgf){
				retStr = "��֯���������֣�"+jcf+" ��߷֣�"+zgf+" �����ύ�ܷ��ѳ�����߷֣���ȷ�Ϻ������ύ��";
				retStr+="\n\n��ʽ��������+��ͨ���÷�+��ѡ��¼�÷�<=��߷�";
			}
		} else if("shsj".equalsIgnoreCase(mkType)){//��֯����
			String pkvString = id;
			//��ͨ���������
			String sumjf = dao.getOneRs("select nvl(sum(shfz),0)sum  from szyc_shsjfzb where xh||xn||xq=? and jjf='�ӷ�' and id not in("+pkvString+") and xxsh='ͨ��' ", new String[]{pkValue},"sum");
			String sumkf = dao.getOneRs("select nvl(sum(shfz),0)sum  from szyc_shsjfzb where xh||xn||xq=? and jjf='�ӷ�' and id not in("+pkvString+") and xxsh='ͨ��' ", new String[]{pkValue},"sum");
			sumjf  = Base.isNull(sumjf)?"0":sumjf;
			sumkf = Base.isNull(sumkf)?"0":sumkf;
			String sum =String.valueOf(Float.parseFloat(sumjf)-Float.parseFloat(sumkf));
			//��ѡ�м�¼�������
			String sumtjf = dao.getOneRs("select nvl(sum(shfz),0)sum  from szyc_shsjfzb where xh||xn||xq=? and jjf='�ӷ�' and id in("+pkvString+") ", new String[]{pkValue},"sum");
			String sumtkf = dao.getOneRs("select nvl(sum(shfz),0)sum  from szyc_shsjfzb where xh||xn||xq=? and jjf='����' and id in("+pkvString+") ", new String[]{pkValue},"sum");
			sumtjf  = Base.isNull(sumtjf)?"0":sumtjf;
			sumtkf = Base.isNull(sumtkf)?"0":sumtkf;
			String sumt =String.valueOf(Float.parseFloat(sumtjf)-Float.parseFloat(sumtkf));
			String[] fzValue = getFzsxForMkdm(mkType);									
			shzf = Float.parseFloat(sum)+Float.parseFloat(sumt);
			if(fzValue!=null){
				jcf = Float.parseFloat(Base.isNull(fzValue[0])?"0":fzValue[0]);
				zgf = Float.parseFloat(Base.isNull(fzValue[1])?"0":fzValue[1]);
			}
			if(shzf+jcf>zgf){
				retStr = "���ʵ�������֣�"+jcf+" ��߷֣�"+zgf+" �����ύ�ܷ��ѳ�����߷֣���ȷ�Ϻ������ύ��";
				retStr+="\n\n��ʽ��������+��ͨ���÷�+��ѡ��¼�÷�<=��߷�";
			}
		} 
		return retStr;
	}
	
	/**
	 * ���ʵ������֯��������
	 */
	public List<HashMap<String,String>> dao_getShsjHz(ShsjModel model,String tableName,String userType,String userName){
		DAO dao      = DAO.getInstance();
		List<HashMap<String,String>> result = new ArrayList<HashMap<String,String>>();		
		String[] colList = new String[]{"xh","xm","shfz",};
		result = dao.getList(shsjHzTj(model,tableName,userType,userName), new String[]{},colList);
		return result;
	}
	/**
	 * ���ʵ�����ܡ���֯��������
	 */
	public String shsjHzTj(ShsjModel model,String tableName,String userType,String userName){
		String xh = "";
		if (model.getXh() != null && model.getXh().length > 0) {
			xh = model.getXh()[0];
		}
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xm   = model.getXm();
		String xn   = model.getXn();
		String xq   = model.getXq();
		StringBuffer querry = new StringBuffer(" where 1=1 ");		
		querry.append(Base.isNull(xh)?"":" and xh='"+xh.trim()+"' ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
		querry.append(Base.isNull(xm)?"":" and xm like '%"+xm.trim()+"%' ");
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");	
		if("bzr".equalsIgnoreCase(userType)){
			querry.append("and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='"+userName+"')");
		}
		querry.append(" and xxsh='ͨ��' order by xh");	
		StringBuffer  sql = new StringBuffer();
		if("szyc_shsjfzb".equalsIgnoreCase(tableName)){
			sql.append("select xh,xm,shfz from(select a.xn,a.xq,a.xh,a.jjf||':'||a.shfz shfz,a.jjf,a.xxsh,b.xm,b.nj,b.xydm,b.zydm,b.bjdm ");
			sql.append("from szyc_shsjfzb a left join view_xsjbxx b on a.xh=b.xh) a ");
		}else if("szyc_zznlfzb".equalsIgnoreCase(tableName)){
			sql.append("select xh,xm,shfz from(select a.xn,a.xq,a.xh,a.jjf||':'||a.shfz shfz,a.jjf,a.xxsh,b.xm,b.nj,b.xydm,b.zydm,b.bjdm ");
			sql.append("from szyc_zznlfzb a left join view_xsjbxx b on a.xh=b.xh) a ");
		}
		return sql+querry.toString();
	}
	/**
	 * ���ʵ������֯�������ܼ�¼��ѧ�ŷ����ȡ�����
	 */
	public  String maxJl(ShsjModel model,String tableName,String userType,String userName){
		String sql = "select max(cout)cout from (select count(xh)cout from( "+shsjHzTj(model,tableName,userType,userName) +") group by xh )";
		return dao.getOneRs(sql,new String[]{},"cout");
	}
	public ArrayList<String[]> getZhszfCjList(ZhszcpModel model,String userType,String userName) {
		ArrayList<String[]> result = new ArrayList<String[]>();
		String xh = model.getXh();
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xm   = model.getXm();
		String xn   = model.getXn();
		String xq   = model.getXq();
		String zhszzf = model.getZhszzf();
		
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		querry.append(Base.isNull(xh)?"":" and xh='"+xh.trim()+"' ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
		querry.append(Base.isNull(xm)?"":" and xm like '%"+xm.trim()+"%' ");	
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry.append(Base.isNull(xn)?"":" and xq='"+xq+"' ");	
		querry.append(StringUtils.isNull(zhszzf)?"":" and zhszzf='"+zhszzf+"' ");
		
		String[] colList = new String[]{"xh","xn","xqmc","xm","xymc","bjmc","wsmkf","shsj","zznl","dshdf","ivtlt","wthd","yybdf","xthd","zhszzf","zhszpm"};
		String  sql = "select rownum r,xh,xn,xqmc,xm,xymc,bjmc,wsmkf,shsj,zznl,dshdf,ivtlt,wthd,yybdf,xthd,zhszzf,zhszpm from view_szgy_zhszcp";
		if ("bzr".equalsIgnoreCase(userType)) {
			querry.append(" and exists(select 1 from fdybjb a where a.zgh='"+userName+"' and a.bjdm = view_szgy_zhszcp.bjdm)");
		}else if("xy".equalsIgnoreCase(userType)){
			querry.append(" and xydm= (select distinct (b.xydm) from yhb a, view_njxyzybj b where a.yhm = '"+userName+"' and a.szbm = b.xydm)");
		}
		try {
			result = CommonQueryDAO.commonQuery(sql, querry.toString(), new String[]{}, colList, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean autoAccount(String xn, String xq, String xydm, String zydm, String bjdm)
			throws Exception {
		String delWh = "";
		String insWh = "";
		
		delWh = " and exists (select 1 from view_xsjbxx b where b.xh=a.xh ";
		insWh = " (select b.xh from view_xsjbxx b where 1=1 ";
			
		if(StringUtils.isNotNull(xydm)){
			delWh += "and b.xydm='" + xydm + "' ";
			insWh += "and b.xydm='" + xydm + "' ";
		}
		
		if(StringUtils.isNotNull(zydm)){
			delWh += "and b.zydm='" + zydm + "' ";
			insWh += "and b.zydm='" + zydm + "' ";
		}

		if(StringUtils.isNotNull(bjdm)){
			delWh += "and b.bjdm='" + bjdm + "' ";
			insWh += "and b.bjdm='" + bjdm + "' ";
		}
		
		delWh += ")";
		insWh += ")";
		
		return dao.runProcuder("{call szgyyq_zhszcp_autocount(?, ?, ?, ?)}",
						new String[] { xn, xq, delWh, insWh });
	}
	public List<HashMap<String, String>> getZhszfCjTitle() {
		String[] colList = new String[]{"xh","xn","xq","xm","xymc","bjmc","wsmkf","shsj","zznl","dshdf","ivtlt","wthd","yybdf","xthd","zhszzf","zhszpm"};
		String[] colListCN = dao.getColumnNameCN(colList, "view_szgy_zhszcp");
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);// ��ͷ
		return topTr;
	}
	/**
	 *ѧ�Ż.����
	 * @throws Exception 
	 */
	public List<HashMap<String,String>> dao_exportDataXthd(XthdModel model,String xxk, String userType,String userName,OutputStream os)
	throws Exception {
		DAO dao      = DAO.getInstance();
		List<HashMap<String,String>> result = new ArrayList<HashMap<String,String>>();		
		String[] colList = new String[]{"xh","xm","pf",};
		result = dao.getList(xthdHzTj(model,xxk,userType,userName), new String[]{},colList);
		return result;
	}
	/**
	 * ѧ�Ż��������
	 */
	public String xthdHzTj(XthdModel model,String xxk,String userType,String userName){
		String xh = "";
		if (model.getXh() != null && model.getXh().length > 0) {
			xh = model.getXh()[0];
		}
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xm   = model.getXm();
		String xn   = model.getXn();
		String xq   = model.getXq();
		String tableName="szyq_dshdjzb";
		if("ivlt".equals(xxk)){
			tableName = "szyq_ivtltb";
		}else if("wthd".equals(xxk)){
			tableName = "szyq_xthddjb";
		}else if("yybd".equals(xxk)){
			tableName = "szyq_yybdjzb";
		}else{
			tableName = "szyq_dshdjzb";
		}
		StringBuffer querry = new StringBuffer(" where 1=1 ");		
		querry.append(Base.isNull(xh)?"":" and xh='"+xh.trim()+"' ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
		querry.append(Base.isNull(xm)?"":" and xm like '%"+xm.trim()+"%' ");
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
		if("bzr".equalsIgnoreCase(userType)){
			querry.append("and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='"+userName+"')");
		}
		querry.append(" and xxsh='ͨ��' order by xh");		
		StringBuffer  sql = new StringBuffer();
		sql.append("select xh,xm,pf from(select a.xn,a.xq,a.xh,a.jjf||':'||a.pf pf,a.jjf,a.xxsh,b.xm,b.nj,b.xydm,b.zydm,b.bjdm ");
		sql.append("from "+tableName+" a left join view_xsjbxx b on a.xh=b.xh) a ");
		
		//System.out.println(sql+querry.toString());
		return sql+querry.toString();
	}
	/**
	 * ѧ�Ż���ܼ�¼��ѧ�ŷ����ȡ�����
	 */
	public  String maxXthd(XthdModel model,String xxk,String userType,String userName){
		String sql = "select max(cout)cout from (select count(xh)cout from( "+xthdHzTj(model,xxk,userType,userName) +") group by xh )";
		//System.out.println(sql);
		return dao.getOneRs(sql,new String[]{},"cout");
	}
	
	/**
	 * ���ذ����������༶ѧ���б�
	 * @param zgh ְ����
	 * @return list
	 * @author luojw
	 */
	public List<HashMap<String, String>> getBzrXsList(String tableName,
			String zgh, String xn, String xq, String nj, String xh, String xm,
			String xydm, String zydm, String bjdm) {
		nj = Base.isNull(nj) ? "%" : nj;
		xh = Base.isNull(xh) ? "%" : xh;
		xm = Base.isNull(xm) ? "%" : xm;
		xydm = Base.isNull(xydm) ? "%" : xydm;
		zydm = Base.isNull(zydm) ? "%" : zydm;
		bjdm = Base.isNull(bjdm) ? "%" : bjdm;

		StringBuffer sb = new StringBuffer();
		sb.append("select a.xh, a.xm, a.xb, a.xymc,a.zymc,a.bjmc from view_xsjbxx a where exists (select 1 ");
		sb.append("from fdybjb b where a.bjdm = b.bjdm and b.zgh = ?)");
		sb.append("and a.nj like ? ");
		sb.append("and a.xh like ? ");
		sb.append("and a.xm like ? ");
		sb.append("and a.xydm like ? ");
		sb.append("and a.zydm like ? ");
		sb.append("and a.bjdm like ? order by a.xydm,a.zydm,a.bjdm,a.xh");
		List<HashMap<String, String>> list = dao.getList(sb.toString(),
				new String[] { zgh, nj, xh, xm, xydm, zydm, bjdm },
				new String[] { "xh", "xm", "xb", "xymc", "zymc", "bjmc" });
		//System.out.print(sb);
		return list;
	}
	
	/**
	 * ��üӼ���ԭ���б�
	 * @return list
	 * @author luojw
	 */
	public List<HashMap<String, String>> getYyList(){
		DAO dao = DAO.getInstance();
		String sql = "select dm,mc from szyc_jjfyyb order by dm";
		return dao.getList(sql, new String[] {}, new String[] { "dm", "mc" });
	}

	/**
	 * �������״̬
	 * @return list
	 * @author luojw
	 * @throws Exception
	 */
	public boolean saveSh(String tableName, String[] key, String shzt)
			throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		sql.append("update " + tableName + " set xxsh ='" + shzt + "'");
		for (int i = 0; i < key.length; i++) {
			if (i == 0) {
				sql.append(" where xh||xn||xq ='" + key[i] + "'");
			} else {
				sql.append(" or xh||xn||xq ='" + key[i] + "'");
			}
		}

		return dao.runUpdate(sql.toString(), new String[] {});
	}
	
	/**
	 * ��ð����ΰ༶�б�
	 * @return list
	 * @author luojw
	 */
	public List<HashMap<String, String>> getBzrBj(String zgh){
		DAO dao = DAO.getInstance();
		String sql = "select bjdm, bjmc from view_njxyzybj a where exists (select 1 from fdybjb b where a.bjdm = b.bjdm and b.zgh = ? )";
		return dao.getList(sql, new String[] {zgh}, new String[] { "bjdm", "bjmc" });
	}
	
	/**
	 * ��ð����������༶�б�
	 * 
	 * @return list
	 * @author luojw
	 */
	public List<HashMap<String, String>> getBjList(String userName) {
		DAO dao = DAO.getInstance();
		String sql = "select a.bjdm, a.bjmc from view_njxyzybj a where exists (select 1 from"
				+ " fdybjb b where a.bjdm = b.bjdm and b.zgh = ?) order by a.bjdm";
		return dao.getList(sql, new String[] { userName }, new String[] {
				"bjdm", "bjmc" });
	}
	
	/**
	 * ���ָ�����ָ���ֶ�
	 * 
	 * @author luojw
	 */
	public String getOneValue(String tableName, String dm, String pk, String pkValue) {
		
		DAO dao = new DAO();
		
		return dao.getOneValue(tableName, dm, pk, pkValue);
	}
}
