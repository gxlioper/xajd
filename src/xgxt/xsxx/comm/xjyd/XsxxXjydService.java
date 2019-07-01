package xgxt.xsxx.comm.xjyd;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.audit.spgc.AuditingManage;
import xgxt.audit.spgc.AuditingModel;
import xgxt.form.User;
import xgxt.gygl.gywh.DelDetectModel;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;

public class XsxxXjydService extends AuditingManage{


	private XsxxXjydDAO dao = new XsxxXjydDAO();
	
	
	
	/**
	 * �ж�ѧ���Ƿ��ظ�����
	 * @param xh
	 * @return
	 */
	protected boolean sfcf(String xh){
		String sql = "select id from bks_xjydxx where xh=? and sqsj = to_char(sysdate,'yyyy-mm-dd')";
		String id = dao.getOneRs(sql, new String[]{xh}, "id");
		
		return StringUtils.isNull(id);
	}
	
	/**
	 * �ж�ͳһѧ��ѧ���Ƿ��ظ�����
	 * @param xh,xn
	 * @return
	 * @author honglin
	 * @date 2012-10-22
	 */
	protected boolean sfcfByXn(String xh,String xn,String ydlbm){
		String sql = "select id from bks_xjydxx where xh=? and xn = ? and ydlbm=?";
		String id = dao.getOneRs(sql, new String[]{xh,xn,ydlbm}, "id");
		
		return StringUtils.isNull(id);
	}
	
	
	/**
	 * �춯����ѯ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	protected List<String[]> getYdlbList(XsxxXjydForm model) throws Exception{
		
		String[] colList = new String[]{"ydlbm","ydlbmc","xjzt","sfzx","shlc","sqrfw"};
		String[] queryArr = new String[]{"xjzt","sfzx","ydlbm"};
		String[] queryLikeArr = new String[]{};
		
		//��ȡ��ѯ����������ֵ
		Map<String,Object> map = CommonQueryDAO.getQuerySQL(model, queryArr, queryLikeArr);
		
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,rownum r from(select a.ydlbm,a.ydlbmc,a.xjzt,")
		   .append("a.sfzx,a.sqrfw,b.mc shlc from dm_ydlb a left join ")
		   .append(" xg_xtwh_splc b on a.shlcid=b.id) a where 1=1 ")
		   .append(map.get("sql"));
		
		 return CommonQueryDAO.commonPageQuery(model.getPages(), sql.toString(), (String[])map.get("input"), colList);
	}
	
	
	
	/**
	 * �������������˷�Χ
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	protected boolean plszSqrfw(XsxxXjydForm model) throws SQLException{
		
		String[] pkValues = model.getPrimarykey_cbv();
		String sqrfw = model.getSave_sqrfw();
		
		if (null != pkValues && pkValues.length > 0){
			String[] sqlArr = new String[pkValues.length];
			
			for (int i = 0 ; i < pkValues.length ; i++){
				StringBuilder sql = new StringBuilder();
				
				sql.append("update dm_ydlb set sqrfw='")
				   .append(sqrfw)
				   .append("' where ydlbm='")
				   .append(pkValues[i])
				   .append("' ");
				sqlArr[i] = sql.toString();
			}
			
			int[] result = dao.runBatch(sqlArr);
			return dao.checkBatch(result);
		}
		
		return false;
	}
	
	
	
	/**
	 * ��ѯ�û�����������춯���
	 * @param user
	 * @return
	 */
	protected List<HashMap<String,String>> getYdlbByUser(User user){
		
		String userType = user.getUserType();
		String fdyQx = user.getFdyQx();
		String bzrQx = user.getBzrQx();
		String sqrfw = "";
		
		if ("stu".equalsIgnoreCase(userType)){
			sqrfw = "%ѧ��%";
		} else if ("xy".equalsIgnoreCase(userType) && Boolean.parseBoolean(fdyQx)){
			sqrfw = "%����Ա%";
		} else if ("xy".equalsIgnoreCase(userType) && Boolean.parseBoolean(bzrQx)){
			sqrfw = "%������%";
		} else if ("xy".equalsIgnoreCase(userType)){
			sqrfw = "%ѧԺ%";
		} else {
			sqrfw = "%ѧ����%";
		}
		
		String sql = "select ydlbm,ydlbmc from dm_ydlb where sqrfw='ȫ��' or sqrfw like ? ";
		return dao.getListNotOut(sql, new String[]{sqrfw});
	} 
	/**
	 * ��ѯ�û�����������춯���
	 * @param user
	 * @return
	 */
	protected List<HashMap<String,String>> getYdlbAll(User user){
		String sql = "select ydlbm,ydlbmc from dm_ydlb ";
		return dao.getListNotOut(sql, new String[]{});
	} 
	
	
	
	/**
	 * �����춯����������̱��
	 * @param ydlb
	 * @return
	 */
	public HashMap<String,String> getShlcidByYdlb(String ydlb){
		
		String sql = "select shlcid,xjzt,sfzx from dm_ydlb where ydlbm=?";
		
		return dao.getMapNotOut(sql, new String[]{ydlb});
	}
	
	
	
	/**
	 * ѧ���춯�����ѯ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	protected List<String[]> queryXjyd(XsxxXjydForm model,User user) throws Exception{
		
		String[] queryArr = new String[]{"shzt","ydqnj","ydhnj","ydqxydm","xn",
						"ydhxydm","ydqzydm","ydhzydm","ydqbdm","ydhbdm"};
		String[] queryLikeArr = new String[]{"xh","xm","ydlbm","xq"};
		Map<String,Object> map = CommonQueryDAO.getQuerySQL(model, queryArr, queryLikeArr);
		Map<String,Object> sqsjMap = getSqsjSql(model);
		
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[]{"dis","id","xh","xm","xn","xqmc","ydlbmc","ydhbjmc","ydqbjmc","sqsj","shzt"};
		
		sql.append(" select a.*,rownum r from (select ")
		   .append(" case when a.shzt='�˻�' or  a.shzt='δ���' then '' else 'disabled' end dis,")
		   .append(" a.*,b.xm,c.ydlbmc,d.xqmc from bks_xjydxx a ")
		   .append(" left join view_xsbfxx b on a.xh=b.xh ")
		   .append(" left join dm_ydlb c on a.ydlbm=c.ydlbm ")
		   .append(" left join xqdzb d on a.xq=d.xqdm ")
		   .append(" ) a where 1=1 ")
		   .append(map.get("sql"))
		   .append(sqsjMap.get("sql"))
		   .append(getCxfwSQL(user,model));
		return CommonQueryDAO.commonPageQuery(model.getPages(), sql.toString(),
				StringUtils.joinStrArr((String[]) map.get("input"),
						(String[]) sqsjMap.get("input")), colList);
	}
	
	
	
	/**
	 * ����ʱ������SQL
	 * @param model
	 * @return
	 */
	private HashMap<String,Object> getSqsjSql(XsxxXjydForm model){
		StringBuilder sql = new StringBuilder();
		List<String> input = new ArrayList<String>();
		
		if (StringUtils.isNotNull(model.getSqkssj()) && StringUtils.isNull(model.getSqjssj())) {
			sql.append(" and to_date(sqsj,'yyyy-mm-dd') >= to_date(?,'yyyymmdd')");
			input.add(model.getSqkssj());
		}
		
		if (StringUtils.isNotNull(model.getSqjssj()) && StringUtils.isNull(model.getSqkssj()) ) {
			sql.append(" and to_date(sqsj,'yyyy-mm-dd') <= to_date(?,'yyyymmdd')");
			input.add(model.getSqjssj());
		}
		
		if (StringUtils.isNotNull(model.getSqkssj()) && StringUtils.isNotNull(model.getSqjssj())) {
			sql.append(" and to_date(sqsj,'yyyy-mm-dd') between to_date(?,'yyyymmdd') and to_date(?,'yyyymmdd')");
			input.add(model.getSqkssj());
			input.add(model.getSqjssj());
		}
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("sql", sql.toString());
		map.put("input", input.toArray(new String[]{}));
		return map;
	}
	
	
	
	/**
	 * ѧ���춯ɾ��
	 * @param model
	 * @return
	 */
	protected boolean delXjyd(XsxxXjydForm model){
		
		String[] pkValues = model.getPrimarykey_cbv();
		
		if (null != pkValues && pkValues.length > 0){
			
			StringBuilder sql = new StringBuilder();
			sql.append("delete from bks_xjydxx where id in (");
			
			for (int i = 0 ; i < pkValues.length ; i++){
				sql.append("'")
				   .append(pkValues[i])
				   .append("'");
				
				if (i != pkValues.length-1){
					sql.append(",");
				} else {
					sql.append(")");
				}
			}
			
			String delShztSql = sql.toString().replace("bks_xjydxx", "xg_xsxx_xjydshztb");
			
			try {
				return dao.runUpdate(sql.toString(), new String[]{}) && dao.runUpdate(delShztSql, new String[]{});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	
	/**
	 * ѧ���춯��˲�ѯ
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	protected List<String[]> queryXjydsh(XsxxXjydForm model,User user) throws Exception{
		
		String[] queryArr = new String[]{"ydqnj","ydhnj","ydqxydm","xn",
					"ydhxydm","ydqzydm","ydhzydm","ydqbdm","ydhbdm"};
		String[] queryLikeArr = new String[]{"xh","xm","ydlbm","xn","xq"};
		Map<String,Object> map = CommonQueryDAO.getQuerySQL(model, queryArr, queryLikeArr);
		Map<String,Object> sqsjMap = getSqsjSql(model);
		
		String userDep = user.getUserDep();
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[]{"id","xh","xm","xn","xqmc","ydlbmc","ydqxymc","ydqbjmc","ydhxymc","ydhbjmc","sqsj"};
		sql.append(" select rownum r,id,xh,xm,xn,xq,xqmc,")
		   .append(" ydlbmc,ydqxymc,ydqbjmc,ydhxymc,ydhbjmc,sqsj,ydhbdm,")
		   .append(" ydqnj,ydhnj,ydqxydm,ydhxydm,ydqzydm,ydhzydm,ydqbdm from (")
		   .append(" select a.nextspgw,a.mc,b.*,e.xqmc,c.xm,d.ydlbmc,");
		
		if ("xy".equalsIgnoreCase(user.getUserType())){
			 sql.append("case when b.ydqxydm = '").append(userDep).append("' and b.ydhxydm <> '").append(userDep).append("' then '��' ")
			 	.append("when b.ydhxydm = '").append(userDep).append("' and b.ydqxydm <> '").append(userDep).append("' then '��' ")
			    .append("else '����' end ydbz ");
		} else {
			sql.append("'����' ydbz ");
		}
		
		sql.append("  from (select a.*,b.mc from (")
		   .append(" select row_number() over(partition by id order by shsj desc) lev,")
		   .append(" xg_xsxx_xjydshztb.* from xg_xsxx_xjydshztb where sftj = '��' ")
		   .append(" ) a left join xg_xtwh_spgw b on a.nextspgw=b.id ")
		   .append(" where lev = 1  and exists ")
		   .append("  (select 1 from (select spgw, spyh from xg_xtwh_spgwyh where spyh = ?) b ")
		   .append(" where a.nextspgw = b.spgw)) a ")
		   .append(" left join bks_xjydxx b on a.id= b.id")
		   .append(" left join ").append(Base.xsxxbAll).append(" c on b.xh = c.xh")
		   .append(" left join dm_ydlb d on b.ydlbm = d.ydlbm")
		   .append(" left join xqdzb e on b.xq=e.xqdm ")
		   .append(" ) a where 1 = 1 and (mc like '%'||ydbz||'%' or ydbz='����') ")
		   .append(map.get("sql"))
		   .append(sqsjMap.get("sql"))
		   .append(getCxfwSQL(user,model));
		return CommonQueryDAO.commonPageQuery(model.getPages(), sql.toString(),
				StringUtils.joinStrArr(new String[]{user.getUserName()},
						(String[]) map.get("input"),(String[]) sqsjMap.get("input")), colList);
		
	}

	
	
	/**
	 * ����ID��ѯѧ���춯��Ϣ
	 * @param id
	 * @return
	 */
	protected HashMap<String,String> getXjydById(String id){
		
		String sql = "select a.*,b.xm,b.lxdh,b.xb from bks_xjydxx a left join view_xsbfxx b on a.xh=b.xh where a.id=?";
		
		return dao.getMapNotOut(sql, new String[]{id});
	}
	
	
	
	/**
	 * ������ѯ�����Ϣ
	 * @param id
	 * @param userName
	 * @return
	 */
	protected HashMap<String,String> getShxxById(String id,String userName){
		
		AuditingModel model = new AuditingModel();
		model.setId(id);
		model.setShr(userName);
		String xtgwid = getSpgw(model, "xg_xsxx_xjydshztb");
		
		String sql = "select shzt,shyj from xg_xsxx_xjydshztb where id=? and xtgwid=? and shr=? and sftj='��'";
		
		return dao.getMapNotOut(sql, new String[]{id,xtgwid,userName});
	}
	
	
	
	/**
	 * ��ѯ�����¼��Ӧ�������Ϣ
	 * @param id
	 * @return
	 */
	protected List<HashMap<String,String>> getShxxList(String id){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.shzt,a.shsj,a.shyj,a.shr,nvl(c.xm,a.shr) xm,nvl(b.mc,'����') mc ")
		   .append(" from xg_xsxx_xjydshztb a left join xg_xtwh_spgw b on ")
		   .append(" a.xtgwid=b.id left join yhb c on a.shr=c.yhm where a.sftj='��' and a.id=? order by shsj");
		
		return dao.getListNotOut(sql.toString(), new String[]{id});
	}
	
	
	
	/**
	 * �춯����Ϣ
	 * @param id
	 * @return
	 */
	private List<HashMap<String,String>> getYdhxx(String[] id){
		
		if (null != id && id.length > 0){
			
			StringBuilder sql = new StringBuilder();
			
			sql.append("select xh,ydhnj,ydhxydm,ydhxymc,ydhzydm,ydhzymc,")
			   .append("ydhbdm,ydhbjmc,ydhxjztm,ydhsfzx,xn from bks_xjydxx ")
			   .append("where shzt='ͨ��' and id in (");
			
			for (int i = 0 ; i < id.length ; i++){
				
				sql.append("'")
				   .append(id[i])
				   .append("'");
				
				if (i != id.length-1){
					sql.append(",");
				} else {
					sql.append(")");
				}
				
			}
			return dao.getListNotOut(sql.toString(), new String[]{});
		}
		return null;
	}
	/**
	 * ���ͨ����ѧ����Ϣ�ύ���춯��ʽ��
	 * @param input
	 * @return
	 */
	protected boolean sumbitStuYdxx(String[] input){
		boolean flag = false;
		String sql = "insert into xg_xsxx_xjydb(xn,xq,xh,ydqnj,ydqxydm,ydqxymc,ydqzydm,ydqzymc,ydqbjdm,ydqbjmc,ydhnj,ydhxydm,ydhxymc,ydhzydm,ydhzymc,ydhbjdm,ydhbjmc,ydlx,xjztm,sfzx,sfyby)"
			+" select xn,xq,xh,ydqnj,ydqxydm,ydqxymc,ydqzydm,ydqzymc,ydqbdm,ydqbjmc,ydhnj,ydhxydm,ydhxymc,ydhzydm,ydhzymc,ydhbdm,ydhbjmc,b.ydlbmc,ydhxjztm,ydhsfzx,'��' sfyby "
			+"from bks_xjydxx a left join dm_ydlb b on a.ydlbm = b.ydlbm where a.id = ?";
		try {
			flag = dao.runUpdate(sql, input);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * �춯��ѧ����Ϣ�ύ��ʽ��
	 * @param id
	 * @return
	 */
	protected boolean submitStuInfo(String[] id){
		
		List<HashMap<String,String>> data = getYdhxx(id);
		
		if (null != data && data.size() > 0){
			
			String[] sqlArr = new String[data.size()];
			
			for (int i = 0 ; i < data.size() ; i++){
				
				StringBuilder sql = new StringBuilder();
				sql.append("update xsxxb set xy=nvl('")
				   .append(data.get(i).get("ydhxymc"))
				   .append("',''),zymc=nvl('")
				   .append(data.get(i).get("ydhzymc"))
				   .append("',''),bjmc=nvl('")
				   .append(data.get(i).get("ydhbjmc"))
				   .append("',''),xydm=nvl('")
				   .append(data.get(i).get("ydhxydm"))
				   .append("',''),zydm=nvl('")
				   .append(data.get(i).get("ydhzydm"))
				   .append("',''),bjdm=nvl('")
				   .append(data.get(i).get("ydhbdm"))
				   .append("',''),nj=nvl('")
				   .append(data.get(i).get("ydhnj"))
				   .append("',''),xjztm=nvl('")
				   .append(data.get(i).get("ydhxjztm"))
				   .append("',''),sfzx=nvl('")
				   .append(data.get(i).get("ydhsfzx"))
				   .append("','') where xh='")
				   .append(data.get(i).get("xh"))
				   .append("'");
				sqlArr[i] = sql.toString();
			}
			
			int[] result;
			try {
				result = dao.runBatch(sqlArr);
				return dao.checkBatch(result);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		return true;
	}
	
	
	
	/**
	 * ѧ���ɼ�
	 * @param xh
	 * @return
	 */
	protected List<HashMap<String,String>> getXscjByXh(String xh){
		
		String sql = "select a.xn,(select xqmc from xqdzb b where a.xq=b.xqdm )xq,kcmc,kcxz,cj,xf,bkcj,jd from view_cjb a where xh=?";
		
		return dao.getListNotOut(sql, new String[]{xh});
	}
	
	
	
	/**
	 * �����б�
	 * @param flg
	 * @return
	 */
	protected List<HashMap<String,String>> getList(String flg){
		
		if ("shzt".equalsIgnoreCase(flg)){
			return dao.getChkList(32);
		} else if ("shjg".equalsIgnoreCase(flg)){
			return dao.getChkList(33);
		}
		
		return null;
	}
	
	
	
	/**
	 * ��ѯ��ΧSQL
	 * @param user
	 * @param sql
	 */
	private String getCxfwSQL(User user, XsxxXjydForm model) {
		
		StringBuilder sql = new StringBuilder();
		
		if (Boolean.valueOf(user.getFdyQx())){
			sql.append(" and (exists (select 1 from fdybjb b where a.ydqbdm = b.bjdm ");
			sql.append(" and b.zgh = '" + user.getUserName() + "') or ");
			sql.append(" exists (select 1 from fdybjb b where a.ydhbdm = b.bjdm ");
			sql.append(" and b.zgh = '" + user.getUserName() + "'))");
		} else if ("xy".equals(user.getUserType())){
			sql.append(" and ((ydqxydm='").append(user.getUserDep()).append("') or (ydhxydm='")
			   .append(user.getUserDep()).append("'))");
		}
		
		return sql.toString();
	}
	
	
	
	/**
	 * ������˺���еĲ���
	 */
	protected boolean auditingAfter(Object o) {
		AuditingModel model = (AuditingModel) o;
		//��ǰ��˸�λ�����Ϊ�ձ�ʾ�ύ�����������
		String xtgwid = model.getXtgwid();
		String shzt = "";
		boolean flg = false;//�Ƿ�Ҫ���춯��Ϣ�ύѧ����Ϣ��ʽ��
		
		if (StringUtils.isNull(xtgwid)){
			//�����¼�ύ���������󣬰�������е����״̬��Ϊ����аɣ�
			shzt = "�����";
		} else {
			//���������Ǿ�����˲���
			if ("��ͨ��".equalsIgnoreCase(model.getShzt())){
				//���״̬Ϊ��ͨ������Ҫֹͣ�ģ�����������״̬��Ϊ����ͨ����
				shzt = "��ͨ��";
			} else if ("ͨ��".equalsIgnoreCase(model.getShzt()) && isLastAudit(model)){
				//ͨ���Ļ����µ�ǰ������λ����һ������������һ����Ҫ�������¼���е����״̬��Ϊͨ��
				shzt = "ͨ��";
				//������Ҫ���춯��Ϣ�ύ��ѧ����Ϣ��ʽ��
				flg = true;
			} else if ("�˻�".equalsIgnoreCase(model.getShzt()) && "Applicant".equalsIgnoreCase(model.getNextPost()) ){
				//������˻ز����������˻ص�Ŀ����������
				shzt = "�˻�";
			}
		}
		
		//�޸�������е����״̬
		if (StringUtils.isNotNull(shzt)){
			String sql = "update bks_xjydxx set shzt=? where id=?";
			try {
				dao.runUpdate(sql, new String[]{shzt,model.getId()});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (flg){
			//�ύ�춯��Ϣ���ƶ���ʽ��
			sumbitStuYdxx(new String[]{model.getId()});
			//�ύ�춯��Ϣ����ʽ��
			submitStuInfo(new String[]{model.getId()});
		}
		
		return super.auditingAfter(model);
	}



	/**
	 * ������ˣ�ÿ����¼��˺�Ĳ���
	 */
	protected boolean doAuditingAfter(Object o) {
		
		return auditingAfter(o);
	}
	
	
	
	
	/**
	 * ѧ���춯�ĺŴ���
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	protected List<String[]> queryWhcl(XsxxXjydForm model,User user) throws Exception{
			
			String[] queryArr = new String[]{"shzt","ydqnj","ydhnj","ydqxydm","xn",
							"ydhxydm","ydqzydm","ydhzydm","ydqbdm","ydhbdm","shzt","ydlbm"};
			String[] queryLikeArr = new String[]{"xh","xm"};
			Map<String,Object> map = CommonQueryDAO.getQuerySQL(model, queryArr, queryLikeArr);
			Map<String,Object> sqsjMap = getSqsjSql(model);
			
			StringBuilder sql = new StringBuilder();
			String[] colList = new String[]{"dis","id","clwh","xn","xh","xm","ydlbmc","ydqbjmc","ydhbjmc","sqsj"};
			
			sql.append(" select a.*,rownum r from (select ")
			   .append(" case when a.shzt='�˻�' or  a.shzt='δ���' then '' else 'disabled' end dis,")
			   .append(" a.*,b.xm,c.ydlbmc from bks_xjydxx a ")
			   .append(" left join view_xsbfxx b on a.xh=b.xh ")
			   .append(" left join dm_ydlb c on a.ydlbm=c.ydlbm) a ")
			   .append(" where 1=1 ")
			   .append(map.get("sql"))
			   .append(sqsjMap.get("sql"))
			   .append(getCxfwSQL(user,model));
			
			if ("��".equals(model.getSfcl())){
				sql.append(" and clwh > ' '");
			} else if ("��".equals(model.getSfcl())){
				sql.append(" and clwh is null ");
			}
			
			return CommonQueryDAO.commonPageQuery(model.getPages(), sql.toString(),
					StringUtils.joinStrArr((String[]) map.get("input"),
							(String[]) sqsjMap.get("input")), colList);
	}
	
	
	
	/**
	 * ���洦���ĺ�
	 * @param model
	 * @return
	 */
	protected boolean saveClwh(XsxxXjydForm model){
		
		String[] pkValue = model.getPrimarykey_cbv();
		StringBuilder sql = new StringBuilder();
		
		if (pkValue != null && pkValue.length > 0){
			
			sql.append("update bks_xjydxx set clwh=? where id in (");
			
			for (int i = 0 ; i < pkValue.length ; i++){
				sql.append("'")
				   .append(pkValue[i])
				   .append("'");
				if (i != pkValue.length-1){
					sql.append(",");
				} else {
					sql.append(")");
				}
			}
			
			DAO dao = DAO.getInstance();
			try {
				return dao.runUpdate(sql.toString(), new String[]{model.getClwh()});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	/**
	 * ɾ�����ʹ�ü��
	 * @param model
	 * @return
	 */
	public String checkDel(DelDetectModel model){
		XsxxXjydDAO dao = new XsxxXjydDAO();
		return dao.checkDel(model);
	}
	/**
	 * �޸����ʹ�ü��
	 * @param model
	 * @return
	 */
	public String checkUpdate(DelDetectModel model){
		XsxxXjydDAO dao = new XsxxXjydDAO();
		return dao.checkUpdate(model);
	}
	/**
	 * ����춯��������˷�Χ�Ƿ����ʹ��
	 * @param model
	 * @return
	 */
	public String checkSqrfw(DelDetectModel model){
		XsxxXjydDAO dao = new XsxxXjydDAO();
		return dao.checkSqrfw(model);
	}
	
	/**
	 * �춯ͳ�Ʋ�ѯ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public List<String[]> xjydtj(XsxxXjydForm model) throws Exception{
		
		List<HashMap<String,String>> maplist = getXjydlblist();
		String ss = "";
		
		if(null!=maplist&&maplist.size()>0){
			String[] colList = new String[maplist.size()+3];
			colList[0]=model.getTjfw();
			if("ydqxydm".equals(model.getTjfw())){
				colList[1]="xymc";
			}if("ydqzydm".equals(model.getTjfw())){
				colList[1]="zymc";
			}if("ydqbdm".equals(model.getTjfw())){
				colList[1]="bjmc";
			}
			if("ydqnj".equals(model.getTjfw())){
				colList[1]="ydqnjmc";
			}
			if("xn".equals(model.getTjfw())){
				colList[1]="xnmc";
			}
			colList[2]="ydzrs";
			for(int i =0;i<maplist.size();i++){
				if(i!=maplist.size()-1){
					ss+="count(decode(ydlbm,'"+maplist.get(i).get("ydlbm")+"',1))||'('||round(count(decode(ydlbm,'"+maplist.get(i).get("ydlbm")+"',1))/count(1)* 100, 2) ||'%)' ydlb"+maplist.get(i).get("ydlbm")+",";
				}else{
					ss+="count(decode(ydlbm,'"+maplist.get(i).get("ydlbm")+"',1))||'('||round(count(decode(ydlbm,'"+maplist.get(i).get("ydlbm")+"',1))/count(1)* 100, 2) ||'%)' ydlb"+maplist.get(i).get("ydlbm")+" ";
				}
				colList[i+3]="ydlb"+maplist.get(i).get("ydlbm")+"";	
			}
		
		String[] queryArr = new String[]{"xjzt","sfzx","ydlbm"};
		String[] queryLikeArr = new String[]{};
		
		//��ȡ��ѯ����������ֵ
		Map<String,Object> map = CommonQueryDAO.getQuerySQL(model, queryArr, queryLikeArr);
		
		StringBuilder sql = new StringBuilder();
		if("ydqxydm".equals(model.getTjfw())){
			sql.append("select case when b.xymc is null then '�ϼ�' else b.xymc end xymc,a.* from ( select a.*,rownum r from (select "+model.getTjfw()+",count(1) ydzrs,")
			   .append(ss)
			   .append(" from (select a.*,c.ydlbmc ")
			   .append(" from bks_xjydxx a ")
			   .append(" left join dm_ydlb c on a.ydlbm = c.ydlbm  where a.shzt='ͨ��' " )
			   .append(" and to_date('"+model.getSqkssj()+"','yyyy-MM-dd')<=to_date(sqsj,'yyyy-MM-dd') and to_date('"+model.getSqjssj()+"','yyyy-MM-dd') >=to_date(sqsj,'yyyy-MM-dd')  and ydqxydm is not null )")
			   .append(" group by rollup("+model.getTjfw()+")) a where 1=1 ) a left join (select distinct(b.XYDM),b.xymc from view_njxyzybj b) b on a.ydqxydm = b.XYDM order by r");
		}
		else if("ydqzydm".equals(model.getTjfw())){
			sql.append("select case when b.zymc is null then '�ϼ�' else b.zymc end zymc,a.* from ( select a.*,rownum r  from (select "+model.getTjfw()+",count(1) ydzrs,")
			   .append(ss)
			   .append(" from (select a.*,c.ydlbmc ")
			   .append(" from bks_xjydxx a ")
			   .append(" left join dm_ydlb c on a.ydlbm = c.ydlbm  where a.shzt='ͨ��' " )
			   .append(" and to_date('"+model.getSqkssj()+"','yyyy-MM-dd')<=to_date(sqsj,'yyyy-MM-dd') and to_date('"+model.getSqjssj()+"','yyyy-MM-dd') >=to_date(sqsj,'yyyy-MM-dd') and ydqzydm is not null)")
			   .append(" group by rollup("+model.getTjfw()+")) a where 1=1 ) a left join (select distinct(b.ZYDM),b.zymc from view_njxyzybj b) b on a.ydqzydm = b.ZYDM order by r");
		}else if("ydqbdm".equals(model.getTjfw())){
			sql.append("select case when b.bjmc is null then '�ϼ�' else b.bjmc end bjmc, a.* from ( select a.*,rownum r  from (select "+model.getTjfw()+",count(1) ydzrs,")
			   .append(ss)
			   .append(" from (select a.*, c.ydlbmc ")
			   .append(" from bks_xjydxx a ")
			   .append(" left join dm_ydlb c on a.ydlbm = c.ydlbm  where a.shzt='ͨ��' " )
			   .append(" and to_date('"+model.getSqkssj()+"','yyyy-MM-dd')<=to_date(sqsj,'yyyy-MM-dd') and to_date('"+model.getSqjssj()+"','yyyy-MM-dd') >=to_date(sqsj,'yyyy-MM-dd')and ydqbdm is not null)")
			   .append(" group by rollup("+model.getTjfw()+")) a where 1=1 ) a left join (select distinct(b.BJDM),b.bjmc from view_njxyzybj b) b on a.ydqbdm = b.BJDM order by r");
		}
		else{
			sql.append("select a.*,rownum r from (select "+model.getTjfw()+",case when "+model.getTjfw()+" is null then '�ϼ�' else "+model.getTjfw()+" end "+model.getTjfw()+"mc,count(1) ydzrs,")
			   .append(ss)
			   .append(" from (select a.*, c.ydlbmc ")
			   .append(" from bks_xjydxx a ")
			   .append(" left join dm_ydlb c on a.ydlbm = c.ydlbm  where a.shzt='ͨ��' " )
			   .append(" and to_date('"+model.getSqkssj()+"','yyyy-MM-dd')<=to_date(sqsj,'yyyy-MM-dd') and to_date('"+model.getSqjssj()+"','yyyy-MM-dd') >=to_date(sqsj,'yyyy-MM-dd')and "+model.getTjfw()+" is not null)")
			   .append(" group by rollup("+model.getTjfw()+")) a where 1=1 order by r");
		}
		
		 return CommonQueryDAO.commonQueryNotFy( sql.toString(), "",(String[])map.get("input"), colList,model);
		}else{
			 return null;
		}
	}
	
	/**
	 * ѧ���춯����ѯ��ȡ��ͷ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getXjydTop(XsxxXjydForm model){
		List<HashMap<String,String>> maplist = getXjydlblist();	
		
		if(null!=maplist&&maplist.size()>0){
			String[] en =new String[maplist.size()+2];
			String[] cn =new String[maplist.size()+2];
			if(null!=model.getTjfw()&&"ydqxydm".equals(model.getTjfw())){
				en[0]="ydqxydm";
				cn[0]="ѧԺ";
			}
			if(null!=model.getTjfw()&&"ydqzydm".equals(model.getTjfw())){
				en[0]="ydqzydm";
				cn[0]="רҵ";
			}
			if(null!=model.getTjfw()&&"ydqbdm".equals(model.getTjfw())){
				en[0]="ydqbdm";
				cn[0]="�༶";
			}
			if(null!=model.getTjfw()&&"ydqnj".equals(model.getTjfw())){
				en[0]="ydqnj";
				cn[0]="�꼶";
			}
			if(null!=model.getTjfw()&&"xn".equals(model.getTjfw())){
				en[0]="xn";
				cn[0]="ѧ�� ";
			}
			if(cn[0]==null){
				en[0]="ydqxydm";
				cn[0]="ѧԺ";
			}
			en[1]="ydzrs";
			cn[1]="�춯ѧ������";
			for(int i =0;i<maplist.size();i++){
				en[i+2]=maplist.get(i).get("ydlbm");
				cn[i+2]=maplist.get(i).get("ydlbmc");
			}
			return dao.arrayToList(en, cn);
		}else{
			return null;
		}
	}
	
	/**
	 * ѧ���춯������ͷ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String[] getXjydDcTop(XsxxXjydForm model){
		List<HashMap<String,String>> maplist = getXjydlblist();	
		if(null!=maplist&&maplist.size()>0){
			String[] cn =new String[maplist.size()+3];
			if(null!=model.getTjfw()&&"ydqxydm".equals(model.getTjfw())){
				cn[0]="ѧԺ����";
				cn[1]="ѧԺ";
			}
			if(null!=model.getTjfw()&&"ydqzydm".equals(model.getTjfw())){
				cn[0]="רҵ����";
				cn[1]="רҵ";
			}
			if(null!=model.getTjfw()&&"ydqbdm".equals(model.getTjfw())){
				cn[0]="�༶����";
				cn[1]="�༶";
			}
			if(null!=model.getTjfw()&&"ydqnj".equals(model.getTjfw())){
				cn[0]="�꼶����";
				cn[1]="�꼶";
			}
			if(null!=model.getTjfw()&&"xn".equals(model.getTjfw())){
				cn[0]="ѧ�����";
				cn[1]="ѧ�� ";
			}
			if(cn[1]==null){
				cn[0]="ѧԺ����";
				cn[1]="ѧԺ";
			}
			cn[2]="�춯ѧ������";
			for(int i =0;i<maplist.size();i++){
				cn[i+3]=maplist.get(i).get("ydlbmc");
			}
			return cn;
		}else{
			return null;
		}
	}
	
	/**
	 * ѧ���춯����ѯ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public List<HashMap<String,String>> getXjydlblist(){
		String lbsql ="select * from dm_ydlb order by ydlbm";
		return dao.getList(lbsql, new String[]{},new String[]{"ydlbm","ydlbmc"});
	}
	
	/**
	 * ѧ���춯����ѯ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public List<HashMap<String,String>> getXjydlbmc(String ydlbm){
		String lbsql ="select * from dm_ydlb where ydlbm = ?";
		return dao.getList(lbsql, new String[]{ydlbm},new String[]{"ydlbm","ydlbmc"});
	}
	
	/**
	 * ѧ���춯����ѯ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public List<HashMap<String,String>> getNjxyzybjmc(String type,String dm){
		String sql ="";
		if("ydqxydm".equals(type)){
			 sql ="select * from (select distinct(a.XYDM),a.xymc from view_njxyzybj a) a where a.xydm=?";	
			 return dao.getList(sql, new String[]{dm},new String[]{"xydm","xymc"});
		}else if("ydqzydm".equals(type)){
			 sql ="select * from (select distinct(a.ZYDM),a.zymc from view_njxyzybj a) a where a.zydm=?";	
			 return dao.getList(sql, new String[]{dm},new String[]{"zydm","zymc"});
		}else {
			sql ="select * from (select distinct(a.BJDM),a.bjmc from view_njxyzybj a) a where a.bjdm=?";	
			return dao.getList(sql, new String[]{dm},new String[]{"bjdm","bjmc"});
		}
	}
	
	/**
	 * �춯ͳ�Ʋ�ѯ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public List<String[]> cxXjydxs(XsxxXjydForm model,String fwcs) throws Exception{
		
		List<HashMap<String,String>> maplist = getXjydlblist();
		
		if(null!=maplist&&maplist.size()>0){
			String[] colList = new String[]{"xh","xm","ydqxymc","ydqzymc","ydqbjmc","sqsj"};
		
		String[] queryArr = new String[]{};
		String[] queryLikeArr = new String[]{};
		String tj="";
		if(model.getTjfw()!=null&&"ydqxydm".equals(model.getTjfw())&&!"".equals(fwcs)){
			tj+= " and a.ydqxydm = '"+fwcs+"'";
		}
		if(model.getTjfw()!=null&&"ydqzydm".equals(model.getTjfw())&&!"".equals(fwcs)){
			tj+= " and a.ydqzydm =  '"+fwcs+"'";
		}
		if(model.getTjfw()!=null&&"ydqbdm".equals(model.getTjfw())&&!"".equals(fwcs)){
			tj+= " and a.ydqbdm = '"+fwcs+"'";
		}
		if(model.getTjfw()!=null&&"ydqnj".equals(model.getTjfw())&&!"".equals(fwcs)){
			tj+= " and a.ydqnj = '"+fwcs+"'";
		}
		if(model.getTjfw()!=null&&"xn".equals(model.getTjfw())&&!"".equals(fwcs)){
			tj+= " and a.xn= '"+fwcs+"'";
		}
		if(model.getYdlbm()!=null&&!"".equals(model.getYdlbm())){
			tj+= " and a.ydlbm = '"+model.getYdlbm()+"'";
		}
		//��ȡ��ѯ����������ֵ
		Map<String,Object> map = CommonQueryDAO.getQuerySQL(model, queryArr, queryLikeArr);
		
		StringBuilder sql = new StringBuilder();
		sql.append("select a.xh,a.xm,a.ydqxymc,a.ydqzymc,a.ydqbjmc,a.sqsj,rownum r from ")
		   .append("(select a.*, b.xm, c.ydlbmc ")
		   .append(" from bks_xjydxx a left join view_xsbfxx b on a.xh = b.xh")
		   .append(" left join dm_ydlb c on a.ydlbm = c.ydlbm  where a.shzt='ͨ��' " )
		  .append(" and to_date('"+model.getSqkssj()+"','yyyy-MM-dd')<=to_date(sqsj,'yyyy-MM-dd') and to_date('"+model.getSqjssj()+"','yyyy-MM-dd') >=to_date(sqsj,'yyyy-MM-dd')and "+model.getTjfw()+" is not null order by a.xh)")
		   .append(" a where 1=1 ");
		 return CommonQueryDAO.commonPageQuery(model.getPages(), sql.toString()+tj,(String[])map.get("input"), colList);
		}else{
			 return null;
		}
	}
	
	/**
	 * �춯ͳ�Ʋ�ѯ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public List<String[]> getStuXjydList(XsxxXjydForm model) throws Exception{
		
		
		String[] colList = new String[]{"xn","xq","ydlbmc","ydqxymc","ydqbjmc","ydhxymc","ydhbjmc","sqsj"};
		
		StringBuilder sql = new StringBuilder();
		sql.append("select a.xn,a.xq,b.ydlbmc,a.ydqxymc,a.ydqbjmc,a.ydhxymc,a.ydhbjmc,a.sqsj from bks_xjydxx a ");
		sql.append("left join dm_ydlb b on a.ydlbm=b.ydlbm ");
		sql.append("where a.shzt='ͨ��' and a.xh='");
		sql.append(model.getXh());
		sql.append("' order by a.sqsj desc");
		return CommonQueryDAO.commonQueryNotFy(sql.toString(), "", new String[]{}, colList,model);
	}
}
