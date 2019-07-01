package xgxt.studentInfo.zgdzdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.comm.search.SearchService;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �й����ʴ�ѧѧ����ϢDAO
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-10-21</p>
 */
public class XsxxZgdzdxDAO extends DAO {
	
	ArrayList<String> values = new ArrayList<String>();
	
	/**
	 * ��ѯѧ����Ϣ
	 * @param CommanForm model
	 * @return List<String[]> 
	 * */
	public List<String[]> selectXsxx(XsxxZgdzdxForm model){
		int  start= model.getPages().getStart();
		int  end= model.getPages().getStart() + model.getPages().getPageSize();
		String[] outputValue = getCols();
		String tj = getCondition(model).toString();
		
		String sql = "select count(1) num from view_xsxxb_zgdzdx a " + tj; 
		String count = getOneRs(sql, values != null ? values.toArray(new String[0]) : new String[]{} , "num");
		count = StringUtils.isNull(count) ? "0" : count;
		model.getPages().setMaxRecord(Integer.parseInt(count));//�ܼ�¼��
		
		//���ݲ�ѯ
		sql = "select * from (select a.xh,a.xm,a.xb,a.xz,a.nj,a.bjmc,a.xjztm,a.ssbh,a.byny,a.xslbmc,a.xslxmc,ydlbmc,rownum r,rownum �к�,xsqrxxbz  from view_xsxxb_zgdzdx a " + tj + ") where r> " + start + " and r<=" + end;
		return rsToVator(sql,  values != null ? values.toArray(new String[0]) : new String[]{}, outputValue);
	}
	
	/**
	 * ��ȡѧ����Ϣ��ѯ��ʾ���ֶ�
	 * @return String[]
	 * */
	public String[] getCols(){
		String[] colList = null;
		boolean modiFlag = checkModifyXsxx();
		if(modiFlag){
			colList = new String[]{"xh", "r", "xm", "xb", "nj", "xz", "bjmc","xjztm", "ssbh", "byny", "xslbmc", "xslxmc","ydlbmc", "xsqrxxbz" };
		}else{
			colList = new String[] { "�к�", "r",  "xh", "xm", "xb", "nj", "xz", "bjmc","xjztm", "ssbh" ,"byny", "xslbmc", "xslxmc", "ydlbmc", "xsqrxxbz" };
		}
		
		return colList;
	}
	
	/**
	 * �ж��Ƿ�����޸�ѧ����Ϣ
	 * */
	public boolean checkModifyXsxx(){
		String flag = getOneRs("select modistuinfo from xtszb", new String[] {}, "modistuinfo");
		flag = StringUtils.isNull(flag) ? "1" : flag;
		return Integer.parseInt(flag) == 1 ? true : false;
	}
	
	/**
	 * ��ȡѧ����Ϣ��ѯ����
	 * @param CommanForm model
	 * @return StringBuffer
	 * */
	public StringBuffer getCondition(XsxxZgdzdxForm model){
		StringBuffer sb = new StringBuffer(" where 1=1 ");
		//��ҳ���ֵת��Ϊ����
		model.setXh(DealString.toGBK(model.getXh()));
		model.setXm(DealString.toGBK(model.getXm()));
		model.setXb(DealString.toGBK(model.getXb()));
		model.setSfzh(DealString.toGBK(model.getSfzh()));
		model.setKsh(DealString.toGBK(model.getKsh()));
		model.setXz(DealString.toGBK(model.getXz()));
		model.setJg(DealString.toGBK(model.getJg()));
		model.setByny(DealString.toGBK(model.getByny()));
		model.setSsbh(DealString.toGBK(model.getSsbh()));
		model.setJtcyxm(DealString.toGBK(model.getJtcyxm()));
		model.setXjzt(DealString.toGBK(model.getXjzt()));
		String xsqrxxbz = model.getXsqrxxbz();
		
		//��Ӳ�ѯ����
		if (!StringUtils.isNull(model.getXh())) {
			sb.append(" and a.xh like '%'||?||'%'");
			values.add(model.getXh());
		}
		if (!StringUtils.isNull(model.getXm())) {
			sb.append(" and a.xm like '%'||?||'%'");
			values.add(model.getXm());
		}
		if (!StringUtils.isNull(model.getSfzh())) {
			sb.append(" and a.sfzh like '%'||?||'%'");
			values.add(model.getSfzh());
		}
		if (!StringUtils.isNull(model.getKsh())) {
			sb.append(" and a.ksh like '%'||?||'%'");
			values.add(model.getKsh());
		}
		if (!StringUtils.isNull(model.getXz())) {
			sb.append(" and a.xz like '%'||?||'%'");
			values.add(model.getXz());
		}
		if (!StringUtils.isNull(model.getJg())) {
			sb.append(" and a.jg like '%'||?||'%'");
			values.add(model.getJg());
		}
		if (!StringUtils.isNull(model.getByny())) {
			sb.append(" and a.byny like '%'||?||'%'");
			values.add(model.getByny());
		}
		if (!StringUtils.isNull(model.getSsbh())) {
			sb.append(" and a.ssbh like '%'||?||'%'");
			values.add(model.getSsbh());
		}
		if (!StringUtils.isNull(model.getJtcyxm())) {
			sb.append(" and exists(select 1 from view_xsjtxx b where a.xh=b.xh and (b.jtcy1_xm like ? or b.jtcy2_xm like ? or b.jtcy3_xm like ?))");
			values.add(model.getJtcyxm());
			values.add(model.getJtcyxm());
			values.add(model.getJtcyxm());
		}
		if (!StringUtils.isNull(model.getNj())) {
			sb.append(" and a.nj = ?");
			values.add(model.getNj());
		}
		if (!StringUtils.isNull(model.getXydm())) {
			sb.append(" and a.xydm = ?");
			values.add(model.getXydm());
		}
		if (!StringUtils.isNull(model.getZydm())) {
			sb.append(" and a.zydm = ?");
			values.add(model.getZydm());
		}
		if (!StringUtils.isNull(model.getBjdm())) {
			sb.append(" and a.bjdm = ?");
			values.add(model.getBjdm());
		}
		if (!StringUtils.isNull(model.getMz())) {
			sb.append(" and a.mz = ?");
			values.add(model.getMz());
		}
		if (!StringUtils.isNull(model.getZzmm())) {
			sb.append(" and a.zzmm = ?");
			values.add(model.getZzmm());
		}
		if (!StringUtils.isNull(model.getXslb())) {
			sb.append(" and a.xslb = ?");
			values.add(model.getXslb());
		}
		if (!StringUtils.isNull(model.getXslx())) {
			sb.append(" and a.xslx = ?");
			values.add(model.getXslx());
		}
		if (!StringUtils.isNull(model.getXb())) {
			sb.append(" and a.xb = ?");
			values.add(model.getXb());
		}
		if (!StringUtils.isNull(model.getXjzt())) {
			sb.append(" and a.xjztm = ?");
			values.add(model.getXjzt());
		}
		if (!StringUtils.isNull(model.getYdlbm())) {
			sb.append(" and a.ydlbm = ?");
			values.add(model.getYdlbm());
		}
		if (!StringUtils.isNull(xsqrxxbz)) {
			sb.append(" and exists(select 1 from xsxxb b where a.xh=b.xh and b.xsqrxxbz=?)");
			values.add(xsqrxxbz);
		}
		//��ҵ����Ϣ��ѯ
		if (!StringUtils.isNull(model.getSfyby()) && "yes".equalsIgnoreCase(model.getSfyby())) {
			sb.append(" and a.nfby='��'");
		}
		//�Ƿ��Ǹ���Ա
		if(model.isFdy()){
			sb.append(" and exists(select 1 from view_fdybbj f where a.bjdm=f.bjdm and f.zgh=?)");
			values.add(model.getUserName());
		}
		return sb;
	}
	
	/**
	 * �жϱ��еļ�¼�Ƿ����
	 * @param String tableName
	 * @param String pk
	 * @param String pkValue
	 * @reutrn boolean
	 * */
	public boolean checkExists(String tableName, String pk, String pkValue) {
		boolean flag = false;
		DAO dao = new DAO();
		String sql = "select count(*) num from " + tableName + " where " + pk + "=?";
		String num = dao.getOneRs(sql, new String[]{pkValue}, "num");
		if (!"0".equalsIgnoreCase(num)) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * ��ѯ�춯������
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getYdlbList(){
		String sql = "select ydlbm dm,ydlbmc mc from dm_ydlb order by ydlbm";
		return getList(sql, new String[]{}, new String[]{"dm","mc"});
	}
	
	/**
	 * ��ʼ��ѧ��ȷ����Ϣ��־
	 * @return boolean
	 * */
	public boolean modifyXsqrxxbz(){
		boolean result = false;
		String sql = "update xsxxb set xsqrxxbz='��'";
		try{
			result = runUpdate(sql, new String[]{});
		}catch(Exception e){
			result = false;
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * �����û������ֶ���Ϣ
	 * @param model
	 * @param request
	 * @return boolean
	 * */
	public boolean saveYhbtzdxx(XsxxZgdzdxForm model,HttpServletRequest request){
		boolean flag = false;
		DAO dao = DAO.getInstance();
		String yhjs = model.getYhjs();
		String xsxxzd = model.getXsxxzd();
		String jtxxzd = model.getJtxxzd();
		
		try {
			if(xsxxzd != null && !"".equalsIgnoreCase(xsxxzd)){
				//����ѧ��������Ϣ�����Ȩ����Ϣ
				String[] xsxx = xsxxzd.split("!!");
				String[] colCN = getColumnNameCN(xsxx, "xsxxb");	
				String sql = "delete from xsxx_btzdfpb where yhjs||zdssb = ?";
				flag = dao.runUpdate(sql, new String []{yhjs+"xsxxb"});
					String [] sqlMap = new String [xsxx.length]; 
					if(flag){
						for(int i=0; i<xsxx.length; i++){
							String sqlTmp = "insert into xsxx_btzdfpb (yhjs,zdm,zdzwmc,zdssb) values ('"+yhjs+"','"+xsxx[i]+"','"+colCN[i]+"','xsxxb')";
							sqlMap[i]=sqlTmp;
						}
						dao.runBatch(sqlMap);
					}				
			}else{
				String sql = "delete from xsxx_btzdfpb where yhjs||zdssb = ?";
				flag = dao.runUpdate(sql, new String []{yhjs+"xsxxb"});
			}
			if(jtxxzd != null && !"".equalsIgnoreCase(jtxxzd)){
				//����ѧ����ͥ��Ϣ�����Ȩ����Ϣ
				String[] jtxx = jtxxzd.split("!!");
				String[] colCN = getColumnNameCN(jtxx, "xsfzxxb");
				String sql = "delete from xsxx_btzdfpb where yhjs||zdssb = ?";
				flag = dao.runUpdate(sql, new String []{yhjs+"xsfzxxb"});
				String [] sqlMap = new String [jtxx.length]; 
				if(flag){
					for(int i=0; i<jtxx.length; i++){
						String sqlTmp = "insert into xsxx_btzdfpb (yhjs,zdm,zdzwmc,zdssb) values ('"+yhjs+"','"+jtxx[i]+"','"+colCN[i]+"','xsfzxxb')";
						sqlMap[i]=sqlTmp;
					}
					dao.runBatch(sqlMap);
				}
			}else{
				String sql = "delete from xsxx_btzdfpb where yhjs||zdssb = ?";
				flag = dao.runUpdate(sql, new String []{yhjs+"xsfzxxb"});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * �޸�ѧ����ҵ�����Ϣ
	 * @param model
	 * @param isFdy
	 * @param userName
	 * @return boolean
	 * */
	public boolean updateByxgxx(XsxxZgdzdxForm model, boolean isFdy, String userName){
		DAO dao = DAO.getInstance();
		boolean result = false;
		
			//�������
			StringBuilder sql = getUpdateColumn(model);
			//�޸�����
			sql.append(" where 1=1 ");
			if(StringUtils.isNotNull(model.getNj())){
				sql.append(" and nj=?");
				values.add(model.getNj());
			}
			if(StringUtils.isNotNull(model.getXydm())){
				sql.append(" and xydm=?");
				values.add(model.getXydm());
			}
			if(StringUtils.isNotNull(model.getZydm())){
				sql.append(" and zydm=?");
				values.add(model.getZydm());
			}
			if(StringUtils.isNotNull(model.getBjdm())){
				sql.append(" and bjdm=?");
				values.add(model.getBjdm());
			}
			if(isFdy){//����Ա
				sql.append(" and exists (select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh=?)");
				values.add(userName);
			}
			
			try {

				String searchTj = SearchService.getSearchTj(model
						.getSearchModel());
				String[] inputV = SearchService.getTjInput(model
						.getSearchModel());

				sql.append(searchTj);
				
				if(inputV!=null && inputV.length>0){
					for (int i = 0; i < inputV.length; i++) {
						values.add(inputV[i]);
					}
				}
				
//				��xsxxb�в����ڵ�ѧ�����뵽ѧ����Ϣ����
				try{
					StringBuilder insertXssql = new StringBuilder("insert into xsxxb(xh,xm,xb,xydm,zydm,bjdm,nj,xz,xjztm,");
					insertXssql.append("ssbh,mz,zzmm,qsdh,ssch,rzrq,zsjzrq,syd,csrq,pycc,rxrq,jg,hkszd,sfzx,lxdh,sjhm,jtdh,");
					insertXssql.append("jtdz,jtyb,sfzh,dzyx)(select xh,xm,xb,xydm,zydm,bjdm,nj,xz,xjztm,ssbh,mz,zzmm,qsdh,ssch,");
					insertXssql.append("zsrq,zsjzrq,syd,csrq,pycc,rxrq,jg,hkszd,sfzx,lxdh,sjhm,jtdh,jtdz,jtyb,sfzh,dzyx from ");
					insertXssql.append("view_xsxxb a where not exists(select 1 from xsxxb b where a.xh=b.xh)");
					insertXssql.append(searchTj);
					insertXssql.append(")");
					result = dao.runUpdate(insertXssql.toString(), inputV);
				}catch(Exception e){
					e.printStackTrace();
					result = false;
				}
				if(result){
					result = runUpdate(sql.toString(), values != null ? values
						.toArray(new String[0]) : new String[] {});
				}
			}catch(Exception e){
				e.printStackTrace();
				result = false;
			}
		
		return result;		
	}
	
	public StringBuilder getUpdateColumn(XsxxZgdzdxForm model){
		StringBuilder sql = new StringBuilder("update xsxxb a set xh=xh ");
		if(StringUtils.isNotNull(model.getXjzt())){
			sql.append(", xjztm=?");
			values.add(model.getXjzt());
		}
		if(StringUtils.isNotNull(model.getSfzx())){
			sql.append(", sfzx=?");
			values.add(model.getSfzx());
		}
		if(StringUtils.isNotNull(model.getSfyby())){
			sql.append(", sfyby=?");
			values.add(model.getSfyby());
		}
		if(StringUtils.isNotNull(model.getNfby())){
			sql.append(", nfby=?");
			values.add(model.getNfby());
		}
		if(StringUtils.isNotNull(model.getSfbys())){
			sql.append(", sfbys=?");
			values.add(model.getSfbys());
		}
		if(StringUtils.isNotNull(model.getByny())){
			sql.append(", byny=?");
			values.add(model.getByny());
		}
		return sql;
	}
	
	/**
	 * ����ѡ��������޸�ѧ����ҵ�����Ϣ
	 * @param model
	 * @return boolean
	 * */
	public boolean updateByxgxxByData(XsxxZgdzdxForm model){
		DAO dao = DAO.getInstance();
		boolean flag = false;
		String pk = model.getPk();
		String[] xh = pk.split("!!");
		String message = "";
		try{
		//��xsxxb�в����ڵ�ѧ�����뵽ѧ����Ϣ����
		StringBuilder sql = new StringBuilder("insert into xsxxb(xh,xm,xb,xydm,zydm,bjdm,nj,");
		sql.append("xz,xjztm,ssbh,mz,zzmm,qsdh,ssch,rzrq,zsjzrq,syd,csrq,");
		sql.append("pycc,rxrq,jg,hkszd,sfzx,lxdh,sjhm,jtdh,jtdz,jtyb,sfzh,");
		sql.append("dzyx)(select xh,xm,(case a.xb when '1' then '��' when '2' then 'Ů'");
		sql.append("else a.xb end) xb,xydm,zydm,bjdm,to_char(nj),substr(xz,0,1) xz,xjztm,");
		sql.append("'' ssbh,'' mz,'' zzmm,'' qsdh,'' ssch,'' rzrq,'' zsjzrq,");
		sql.append("'' syd,'' csrq,pycc,'' rxrq,'' jg, '' hkszd,sfzx,'' lxdh, '' sjhm,'' jtdh,'' jtdz,");
		sql.append("'' jtyb,sfzh,'' dzyx from bks_xsjbxx a where not exists(select 1 from xsxxb b where a.xh=b.xh) and a.xh in ("); 
		for(int i=0; i<xh.length-1; i++){
			sql.append("'");
			sql.append(xh[i]);
			sql.append("',");
		}
		if(xh.length>1){
			sql.append("'");
			sql.append(xh[xh.length-1]);
			sql.append("'");
		}
		sql.append(")) ");
		
		dao.runUpdate(sql.toString(), new String[]{});
		
		for(int i=0; i<xh.length; i++){
			if(xh[i] != null && !"".equalsIgnoreCase(xh[i])){
				values = new ArrayList<String>();
				flag = dao.runUpdate(getUpdateColumn(model)+" where xh='" + xh[i] + "'", values != null ? values.toArray(new String[0]) : new String[]{});
				if(!flag){
					if(message == ""){
						message += i+1;
					}else{
						message += "," + (i+1);
					}					 
				}
			}
		}
		} catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
}
