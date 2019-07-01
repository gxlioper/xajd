/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �й����ʴ�ѧ��Ԣ����DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-8-6 ����05:15:33</p>
 */
package xgxt.xsgygl.zgdzdx;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.MD5;
import xgxt.xsgygl.dao.GyglShareDAO;

public class GyglZgdzdxDAO {

	public boolean dao_RoomAutoCreate(GyglZgdzdxModel myModel) throws SQLException{
		DAO dao       = DAO.getInstance();     
		boolean doFlag = false;
		String lddm = myModel.getLddm();
		String cs   = myModel.getCs();//����
		String cfjs = myModel.getCfjs();//������ 
		String jcws = myModel.getJcws();//��λ��
		String sfbz = myModel.getSfbz();//�շѱ�׼
		String fpbz = myModel.getFpbz();//��ס����
		String sffqfj = myModel.getSffqfj();//��������
		
		sffqfj = ("����".equalsIgnoreCase(fpbz))?"���ⷿ��":"";
		
		String[] ssbhArr  = null;//����������
		String[] fjhArr   = null;//���������
		String[] delSql   = null;
		String[] inserSql = null;		
//		int sql = cfjs.length();
		StringBuffer inserSqlStr = new StringBuffer();
		StringBuffer delSqlStr   = new StringBuffer();

		if(!Base.isNull(lddm)
				&&!Base.isNull(cs)
				&&!Base.isNull(cfjs)
				&&!Base.isNull(jcws)
				&&!Base.isNull(fpbz)){

			int cfjsCout = Integer.parseInt(cfjs);
			int jcwsCout = Integer.parseInt(jcws);
			int csCout = Integer.parseInt(dao.getOneRs("select cs from sslddmb where lddm=?",new String[]{lddm},"cs"));//��øö�¥�Ĳ�����

			String[] qshGz = dao.getOneRs("select lchsfbl,fjsxhws from qshbpgzb ", new String[]{},new String[]{"lchsfbl","fjsxhws"});

			if(qshGz!=null
					&&qshGz.length==2
					&&Integer.parseInt(qshGz[1])>=cfjs.length()){//ֵ���ڲ������Һŷ���˳����λ������ÿ�����ʵ�ʷ�����λ��
				if(cs=="all"||cs.equalsIgnoreCase("all")){//������¥�����Զ����ɷ����

					StringBuffer ssbhStr = new StringBuffer();
					StringBuffer fjhStr  = new StringBuffer();
					StringBuffer csStr         = new StringBuffer();
					ssbhArr = new String[cfjsCout];
					fjhArr  = new String[cfjsCout];

					for(int i=0;i<csCout;i++){//�����ö�¥����
						cs = String.valueOf(i+1);//�ǲ������
						String csV = String.valueOf(i+1);//�������
						if(Integer.parseInt(qshGz[0])==1){//����С��10ʱ����
							if(i+1<10){
								csV = "0"+(i+1);
							}
						}
						for(int j=0;j<cfjsCout;j++){//����������ƴ�������ż�����ţ��ڴˣ�ÿ���㷿������ͬ�������Ź���¥������-���Һ�			    		
							ssbhArr[j] = lddm+"-";
							String fjsxh  = String.valueOf(j+1);//����˳���
							String strTrm = "";//����Ų����ַ���
							if(fjsxh.length()<Integer.parseInt(qshGz[1])){//����˳���λ��С�������е�λ��
								for(int n=1;n<=Integer.parseInt(qshGz[1])-fjsxh.length();n++){
									strTrm += "0";
								}
							}
							fjsxh = strTrm+fjsxh;//����˳���ǰ����
							fjhArr[j]  = csV+fjsxh;					
							ssbhArr[j] += fjhArr[j];			    		
							ssbhStr.append(ssbhArr[j]).append("!SSBH!");
							fjhStr.append(fjhArr[j]).append("!FJH!");
							csStr.append(i+1).append("!CS!");
						}
					}

					ssbhArr = ssbhStr.toString().split("!SSBH!");
					fjhArr  = fjhStr.toString().split("!FJH!");
					String[] csArr = csStr.toString().split("!CS!");
					delSqlStr.append("delete from cwxxb a where a.ssbh in (select ssbh from ssxxb where lddm='"+lddm+"') or not exists(select 1 from ssxxb b where a.ssbh=b.ssbh)").append("!DEL!");//ɾ������¥��λ��Ϣ
					delSqlStr.append("delete from ssxxb where lddm='"+lddm+"' ").append("!DEL!");//ɾ������¥������Ϣ					
					for(int i=0;i<ssbhArr.length;i++){//��������������ƴ��SQL���						
						inserSqlStr.append("insert into ssxxb(ssbh,lddm,qsh,cws,fpbj,cs,sfbz,sffqfj) values('"+ssbhArr[i]+"','"+lddm+"','"+fjhArr[i]+"','"+jcws+"','"+fpbz+"','"+csArr[i]+"','"+sfbz+"','"+sffqfj+"')").append("!IN!");
						for(int j=0;j<jcwsCout;j++){//����ÿ�����䴲λ�����ڴˣ�ÿ�����䴲λ����ͬ��
							inserSqlStr.append("insert into cwxxb(ssbh,cwh)values('"+ssbhArr[i]+"','"+(j+1)+"')").append("!IN!");
						}
					}
				}else{//��¥������Զ����ɷ���ż�������
					String csV = cs;//�������
					if(Integer.parseInt(qshGz[0])==1){//����С��10ʱ����
						if(Integer.parseInt(cs)<10){
							csV = "0"+cs;
						}
					}
					ssbhArr = new String[cfjsCout];
					fjhArr  = new String[cfjsCout];		
					for(int i=0;i<cfjsCout;i++){//����������ƴ�������ż�����ţ��ڴˣ��˲㷿������ͬ��
						ssbhArr[i] =lddm+"-";
						String fjsxh  = String.valueOf(i+1);//����˳���
						String strTrm = "";//�����ַ���
						if(fjsxh.length()<Integer.parseInt(qshGz[1])){//˳���С�������е�λ��
							for(int n=1;n<=Integer.parseInt(qshGz[1])-fjsxh.length();n++){
								strTrm += "0";
							}
						}
						fjsxh = strTrm+fjsxh;//����˳���ǰ����
						fjhArr[i]  = csV+fjsxh;
						ssbhArr[i] += fjhArr[i];
					}
					delSqlStr.append("delete from ssxxb where lddm='"+lddm+"' and cs='"+cs+"' ").append("!DEL!");
					delSqlStr.append("delete from cwxxb a where not exists(select 1 from ssxxb b where a.ssbh=b.ssbh)").append("!DEL!");
					for(int i=0;i<ssbhArr.length;i++){//��������������ƴ��SQL���						
						delSqlStr.append("delete from cwxxb where ssbh='"+ssbhArr[i]+"'").append("!DEL!");
						inserSqlStr.append("insert into ssxxb(ssbh,lddm,qsh,cws,fpbj,cs,sfbz,sffqfj) values('"+ssbhArr[i]+"','"+lddm+"','"+fjhArr[i]+"','"+jcws+"','"+fpbz+"','"+cs+"','"+sfbz+"','"+sffqfj+"')").append("!IN!");
						for(int j=0;j<jcwsCout;j++){//����ÿ�����䴲λ�����ڴˣ�ÿ�����䴲λ����ͬ��
							inserSqlStr.append("insert into cwxxb(ssbh,cwh)values('"+ssbhArr[i]+"','"+(j+1)+"')").append("!IN!");
						}
					}
				}
				delSql          = delSqlStr.toString().split("!DEL!");
				inserSql        = inserSqlStr.toString().split("!IN!");
				String[] exesql = dao.unionArray(delSql, inserSql);
				int[] array     = dao.runBatch(exesql);
				doFlag          = dao.checkBatch(array);
			}else{
				doFlag = false;
			}
		}
		return doFlag;
	}

	public String[] dao_QshBpGzQrr(){
		DAO dao = DAO.getInstance();
		String[] bpgz = dao.getOneRs("select lchsfbl,fjsxhws,ms from qshbpgzb ", new String[]{},new String[]{"lchsfbl","fjsxhws","ms"});
		return bpgz;
	}
	public List<String[]> dao_alreadyCTLCQrr(String lddm,String cs){
		DAO dao                             = DAO.getInstance();
		List<String[]> result = new ArrayList<String[]>();
		StringBuffer sql     = new StringBuffer();
		StringBuffer querry  = new StringBuffer("where 1=1 ");
		String[] opCol         = new String[]{"�к�","ssbh","ldmc","cs", "qsh", "cws", "fpbj", "sfbz","sffqfj"};		
		if(!Base.isNull(cs)){
			if(cs.equalsIgnoreCase("all")){
				querry.append("and 1=1 ");
			}else{
				querry.append("and b.cs='"+cs+"' ");
			}
		} else {
			querry.append("and 1=2 ");
		}		
		querry.append(Base.isNull(lddm)?"and 1=2": "and b.lddm='"+lddm+"' ");
		sql.append("select rownum �к�,ssbh,ldmc,qsh,cws,fpbj,cs,sfbz,sffqfj from ");
		if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(dao.getXxdm())){//�й����ʴ�ѧ
			sql.append("(select ssbh,(select yqmc from sslddmb a,yqdmb c  where a.lddm=b.lddm and a.yqdm=c.yqdm )||(select ldmc from sslddmb a  where a.lddm=b.lddm)ldmc,qsh,cws,fpbj,cs,sfbz,sffqfj from ssxxb b  ");
		}else{
			sql.append("(select ssbh,(select ldmc from sslddmb a  where a.lddm=b.lddm)ldmc,qsh,cws,fpbj,cs,sfbz,sffqfj from ssxxb b  ");
		}
		sql.append(querry.toString()+" order by to_number(cs),qsh )");	
		result = dao.rsToVator(sql.toString(),new String[]{},opCol);
		return result;	          
	}

	public List<HashMap<String,String>> dao_getCTLCQrrTit(){
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		// ������dao_alreadyCTLCQrr�����е��������һ��
		String[] opCols = { "�к�","ssbh","ldmc","cs","qsh", "cws","fpbj", "sfbz","sffqfj"};
		String[] cnCols = { "�к�","���","¥������","����","���Һ�","�ܴ�λ��(��������)","������","�շѱ�׼(Ԫ)","���ⷿ��˵��"};
		for (int i = 0; i < opCols.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", opCols[i]);
			map.put("cn", cnCols[i]);
			result.add(map);
			map = null;
		}
		return result;	  
	}
	public boolean dao_roomBatchDel(String delPk) throws Exception{
		DAO dao = DAO.getInstance();		   
		String[] pkValueA = delPk.split("!!");
		String[] delFjSql = new String[pkValueA.length];
		String[] delCwSql = new String[pkValueA.length];
		for (int i = 0; i < pkValueA.length; i++) {
			delFjSql[i] = "delete from ssxxb where ssbh='"+pkValueA[i]+"'";
			delCwSql[i] = "delete from cwxxb where ssbh='"+pkValueA[i]+"'";

		}
		String[] exesql = dao.unionArray(delFjSql, delCwSql);
		int[] array = dao.runBatch(exesql);
		boolean doFlag = dao.checkBatch(array);
		return doFlag;
	}
	public HashMap<String,String>dao_roomInfoMod(String pkValue){
		DAO dao    =  DAO.getInstance();
		HashMap<String,String> result = new HashMap<String,String>();		
		String sql = " select ssbh,(select ldmc from sslddmb a  where a.lddm=b.lddm)ldmc,lddm,qsh,cws jcws,fpbj fpbz,cs,sfbz,sffqfj from ssxxb b where b.ssbh=? ";
		if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(dao.getXxdm())){//�й����ʴ�ѧ
			sql = " select ssbh,(select yqmc from sslddmb a,yqdmb c  where a.lddm=b.lddm and a.yqdm=c.yqdm )||(select ldmc from sslddmb a  where a.lddm=b.lddm)ldmc,lddm,qsh,cws jcws,fpbj fpbz,cs,sfbz,sffqfj from ssxxb b where b.ssbh=? ";	
		}
		result     = dao.getMap(sql,new String[]{pkValue},new String[]{"ssbh","ldmc","lddm","qsh","jcws","fpbz", "cs","sfbz","sffqfj"});
		return result;
	}
	public boolean dao_roomInfoModSave(GyglZgdzdxModel model) throws Exception{
		DAO dao          = DAO.getInstance();
		String ssbh      = model.getSsbh();
		String lddm      = model.getLddm();					 
		String jcws      = model.getJcws();
		String sfbz      = model.getSfbz();
		String fpbz      = model.getFpbz();
		String cs        = model.getCs();
		String qsh       = model.getQsh();
		String sffqfj    = model.getSffqfj();
		StringBuffer sql = new StringBuffer();
		sql.append("delete from ssxxb where ssbh='"+ssbh+"' ").append("!!");
		sql.append("delete from cwxxb where ssbh='"+ssbh+"' ").append("!!");
		sql.append("insert into ssxxb(ssbh,lddm,qsh,cws,fpbj,cs,sfbz,sffqfj)values('"+ssbh+"','"+lddm+"','"+qsh+"','"+jcws+"','"+fpbz+"','"+cs+"','"+sfbz+"','"+sffqfj+"')").append("!!");
		for(int i=1;i<=Integer.parseInt(jcws);i++){
			sql.append(" insert into cwxxb(ssbh,cwh)values('"+ssbh+"','"+i+"')").append("!!");
		}
		String[] exesql = sql.toString().split("!!"); 
		int[] array     = dao.runBatch(exesql);
		boolean doFlag = dao.checkBatch(array);
		return doFlag;
	}

	public boolean dao_roomInfoModRelatedSave(GyglZgdzdxModel model) throws Exception{
		@SuppressWarnings("unused")
		DAO dao          = DAO.getInstance();
		@SuppressWarnings("unused")
		String ssbh      = model.getSsbh();
		@SuppressWarnings("unused")
		String lddm      = model.getLddm();
		@SuppressWarnings("unused")
		String jcws      = model.getJcws();
		@SuppressWarnings("unused")
		String sfbz      = model.getSfbz();
		@SuppressWarnings("unused")
		String fpbz      = model.getFpbz();
		@SuppressWarnings("unused")
		String cs        = model.getCs();
		String qsh       = model.getQsh();
		@SuppressWarnings("unused")
		String fjsxh     = qsh.substring(qsh.lastIndexOf("0")+1,qsh.length());//��������һ��������������˳���
		@SuppressWarnings("unused")
		String[] ssbhV   = new String[Integer.parseInt(cs)];//����¥���������Ƶ�������
		for(int i=0;i<Integer.parseInt(cs);i++){

		}
		return false;
	}
	/** 
	 * �Ƿ�ʿ��
	 * Method sfbss
	 * @param username �û���
	 * @return boolean
	 */
	public boolean sfbss(String username){
		DAO dao      = DAO.getInstance();
		String sql   = "select xh from bss_xxb where xh=?";
		String exist = dao.getOneRs(sql, new String[]{username}, "xh");
		boolean is = exist.equals("")?false:true;
		return is;
	}
	/** 
	 * �Ƿ�����ס
	 * Method sfyrz
	 * @param username �û���
	 * @return boolean
	 */
	public boolean sfyrz(String username){
		DAO dao      = DAO.getInstance();
		String sql   = "select xh from xszsxxb where xh=?";
		String exist = dao.getOneRs(sql, new String[]{username}, "xh");
		boolean is = exist.equals("")?false:true;
		return is;
	}
	/** 
	 * ����Ա�
	 * Method getXsXb
	 * @param username �û���
	 * @return String
	 */
	public String getXsXb(String username){
		DAO dao      = DAO.getInstance();
		String sql   = "select xb from bss_xxb where xh=?";
		return dao.getOneRs(sql, new String[]{username}, "xb");
	}
	/** 
	 * ƴ�Ĳ�ѯ����
	 * Method getCondition
	 * @param model GyglZgdzdxModel����
	 * @return String
	 */
	public String getCondition(GyglZgdzdxModel model){
		StringBuffer sb = new StringBuffer();
		sb.append(" where 1=1 ");
		if(model.getXiaoqu() != null && !model.getXiaoqu().equals("")){
			sb.append(" and xqdm ='");
			sb.append(model.getXiaoqu());
			sb.append("' ");
		}
		if(model.getLddm() != null && !model.getLddm().equals("")){
			sb.append(" and lddm ='");
			sb.append(model.getLddm());
			sb.append("' ");
		}
		if(model.getCs() != null && !model.getCs().equals("")){
			sb.append(" and cs ='");
			sb.append(model.getCs());
			sb.append("' ");
		}
//		if(model.getSfbz1() != null && !model.getSfbz1().equals("")){
//		sb.append(" and to_number(sfbz) <='");
//		sb.append(model.getSfbz1());
//		sb.append("' ");
//		}
//		if(model.getSfbz2() != null && !model.getSfbz2().equals("")){
//		sb.append(" and to_number(sfbz) >='");
//		sb.append(model.getSfbz2());
//		sb.append("' ");
//		}
		if(model.getSfbz() != null && !model.getSfbz().equals("")){
			sb.append(" and sfbz='");
			sb.append(model.getSfbz());
			sb.append("' ");
		}
		return sb.toString();
	}  
	/** 
	 * ���������Ϣlist
	 * Method getSsxxList
	 * @param condition ��ѯ����
	 * @param xb �Ա�
	 * @return List<String[]>
	 */
	public List<String[]> getSsxxList(String condition,String xb){
		DAO dao         = DAO.getInstance();
		StringBuffer sb = new StringBuffer();
		sb.append("select ssbh ����,xqmc,ldmc,'��'||cs||'��' cs,qsh,fpbj,sffqfj,cws,yrzrs,to_number(cws)-yrzrs krzrs,sfbz");
		sb.append(",xqdm,lddm from (select a.*,(select count(*) from xszsxxb b where b.ssbh=a.ssbh) yrzrs from view_ssxx a where a.fpbj='��ʿ') ");
		sb.append(condition);
		sb.append((Base.isNull(xb)?" ":" and (xbxd='"+xb+"' or SFFQFJ='��' or xbxd='���')"));
		sb.append(" and to_number(cws)-yrzrs>0 order by xqdm,lddm,qsh");
		String[] cols = new String[]{"����","xqmc","ldmc","cs","qsh","fpbj","sffqfj","cws","yrzrs","krzrs","sfbz"};
		return dao.rsToVator(sb.toString(), new String[]{}, cols);
	} 
	/** 
	 * ��ʿ����ѡ�����ύ
	 * Method saveDorm
	 * @param xh ѧ��
	 * @param ssbh ������
	 * @return boolean
	 */
	public boolean saveDorm(String xh,String ssbh) throws Exception{
		DAO dao    = DAO.getInstance();
		boolean is = false;
		String sql = "delete from xszsxxb where xh=?";
		is = dao.runUpdate(sql, new String[]{xh});
		if(is){
			sql = "select a.cwh,(select sfbz from view_ssxx b where a.ssbh=b.ssbh) zsf from (select a.cwh,a.ssbh from (select cwh,ssbh from cwxxb where ssbh=?) a " +
			"left join xszsxxb b on a.ssbh = b.ssbh and a.cwh=b.cwh where b.cwh is null order by cwh) a where rownum=1 ";
			String[] temp = dao.getOneRs(sql, new String[]{ssbh},new String[]{"cwh","zsf"});
			sql = "insert into xszsxxb (xh,ssbh,cwh,zsf) values (?,?,?,?)";
			if(temp != null){
				is  = dao.runUpdate(sql, new String[]{xh,ssbh,temp[0],temp[1]});
			}else{
				is = false;
			}
		}
		return is;
	}
	/** 
	 * �鿴��½ѧ������ʿ����ס����
	 * Method viewYzInfo
	 * @param xh ѧ��
	 * @return HashMap<String,String>
	 */
	public HashMap<String,String> viewYzInfo(String xh) {
		DAO dao    = DAO.getInstance();
		String sql = "select a.xh,b.xm,c.ldmc,'��'||c.cs||'��' cs,c.qsh,c.qsdh,c.sffqfj,c.sfbz,a.rzrq,a.jzrq  from xszsxxb a,bss_xxb b,view_ssxx c where a.xh=b.xh and a.xh=? and a.ssbh=c.ssbh";
		String[] cols = new String[]{"xh","xm","ldmc","cs","qsh","sffqfj","sfbz","rzrq","jzrq"};
		List<HashMap<String,String>> list = dao.getList(sql, new String[]{xh}, cols);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	public boolean dao_roomCompartSave(String newMappingItems,String oldMappingItems) throws SQLException{
		DAO dao = DAO.getInstance();
		String[] oldValue = oldMappingItems.split(",");
		String[] newValue = newMappingItems.split(",");
		String[] delStr  = new String[oldValue.length];
		String[] insStr  = new String[newValue.length];
		if(!Base.isNull(oldMappingItems)){
			for(int i=0;i<oldValue.length;i++){//������ֵ�����ȡssbh��ƴ��ɾ�����
				String[] oldVtem = oldValue[i].split("/");
				delStr[i] = "delete from ssfpb where  nj='"+oldVtem[0]+"' and ssbh='"+oldVtem[2]+"'  ";
			}
		}else{
			delStr[0]="delete from ssfpb where 1=2 ";
		}
		if(!Base.isNull(newMappingItems)){
			for(int i=0;i<newValue.length;i++){//������ֵ�����ȡ���ֵ��ƴ�Ӳ������
				String[] newVtem = newValue[i].split("/");
				insStr[i] = "insert into ssfpb(nj,xydm,ssbh,cws,bjdm,cwh)values('"+newVtem[0]+"','"+newVtem[1]+"','"+newVtem[2]+"','"+newVtem[3]+"','0','0') ";
			}
		}else{
			insStr[0] = "delete from ssfpb where 1=2 ";
		}
		String[] exesql =  dao.unionArray(delStr,insStr);
		int[] array     = dao.runBatch(exesql);
		boolean doFlag = dao.checkBatch(array);
		return doFlag;
	}

	public boolean dao_roomCodeSave(String chsfbl,String fjhws ) throws Exception{
		DAO dao = DAO.getInstance();
		boolean doFlag;
		String sql = "delete from qshbpgzb ";
		doFlag = dao.runUpdate(sql, new String[]{});
		if(doFlag){
			String ms = "";
			if(chsfbl=="0"||chsfbl.equalsIgnoreCase("0")){
				ms = "���(С��10��ʱ��߲�����)+����˳���("+fjhws+"λ��)";
			}else if(chsfbl=="1"||chsfbl.equalsIgnoreCase("1")){
				ms = "���(С��10��ʱ��߲���)+����˳���("+fjhws+"λ��)";
			}
			sql = "insert into qshbpgzb(lchsfbl,fjsxhws,ms)values(?,?,?)";
			doFlag = dao.runUpdate(sql, new String[]{chsfbl,fjhws,ms});
		}
		return doFlag;
	}
	/** 
	 * �������ֲ�ͼ��
	 * Method getDormImageWiew
	 * @param xqdm У������
	 * @param lddm ¥������
	 * @param qsh  ���Һ�
	 * @return List
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public List getDormImageWiew(String xqdm,String yqdm,String lddm,String lc,String qsh,String nj,String xy,String userName) throws Exception{
		DAO dao = DAO.getInstance();
		String query = " where 1=1 ";
		String info  = "";
		String lddm1 = "";
		String cs    = "";
//		String ldnum = "";
		String temp  = "";
		String fjs   = "";
		String cws   = "";
		String kcws  = "";
		String sfbz  = "";	
		String xxdm  = dao.getXxdm();
        String xymcV = dao.getOneRs("select bmmc from zxbz_xxbmdm where bmdm='"+xy+"'", new String[]{},"bmmc");
		if(xqdm != null && !xqdm.equals("") && !xqdm.equalsIgnoreCase("null")){
			query += " and xqdm='"+xqdm+"' ";
		}
		if(lddm != null && !lddm.equals("") && !lddm.equalsIgnoreCase("null")){
			query += " and lddm='"+lddm+"' ";
		}
		if(qsh != null && !qsh.equals("") && !qsh.equals("qsh") && !qsh.equalsIgnoreCase("null")){
			query += " and qsh='"+qsh+"' ";
		}
		if(!Base.isNull(lc) && !lc.equals("lc") && !lc.equalsIgnoreCase("null")&& !lc.equalsIgnoreCase("all")){
			query += " and cs='"+lc+"' ";
		}
		query+=Base.isNull(yqdm)?"":" and yqdm ='"+yqdm+"' ";
		if(GyglShareDAO.isSssAdmin(userName)){
			query += " and (fpbj='˶ʿ' or fpbj='��ʿ') ";
		}
		StringBuffer sql = new StringBuffer();
		String query2 = " and 1=1 ";
		if(!Base.isNull(nj)&&!Base.isNull(xy)){
			query2+=" and a.ssbh in(select ssbh from (select a.ssbh from xszsxxb a,view_xsjbxx b where a.xh=b.xh and b.nj='"+nj+"' and b.xydm='"+xy+"')union (select ssbh from wxs_xszsxxb where nj='"+nj+"' and xymc like '%"+xymcV+"%')) ";
		}else if(!Base.isNull(nj)){
			query2+=" and a.ssbh in(select ssbh from ( select a.ssbh from xszsxxb a,view_xsjbxx b where a.xh=b.xh and b.nj='"+nj+"')union(select ssbh from wxs_xszsxxb where nj='"+nj+"' )) ";
		}else if(!Base.isNull(xy)){
			query2+=" and a.ssbh in(select ssbh from ( select a.ssbh from xszsxxb a,view_xsjbxx b where a.xh=b.xh and b.xydm='"+xy+"')union(select ssbh from wxs_xszsxxb where nj='"+nj+"')) ";
		}
		if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
			sql.append("select info,lddm,cs,fjs,cws,yzcws,wxscws,cws - (yzcws+wxscws) kcws,ldnum,(select sfbz from ssxxb b where a.lddm=b.lddm and a.cs=b.cs and rownum=1)sfbz ");
			sql.append("from(select distinct xqmc || ' ' || yqmc || ldmc || '(' || xbxd || ')' info,lddm, cs,(select count(ssbh) from ssxxb b where a.lddm=b.lddm and a.cs=b.cs ) fjs,");
			sql.append(" (select sum(cws) from ssxxb b where a.lddm=b.lddm and a.cs=b.cs ) cws,(select count(b.xh) from xszsxxb b,ssxxb c where a.lddm=c.lddm and a.cs=c.cs and b.ssbh=c.ssbh and not exists(select 1 from newstusinfo d where d.ksh=b.xh))yzcws,");
			sql.append(" (select count(b.xh) from wxs_xszsxxb b, ssxxb c where a.lddm = c.lddm and a.cs = c.cs and b.ssbh = c.ssbh) wxscws, ");			
			sql.append("count(distinct lddm) ldnum from view_ssxx a "+query+query2+" group by xqdm, xqmc, yqdm, yqmc, lddm, ldmc, xbxd, cs ) a order by lddm, to_number(cs) ");
		}else{			
			sql.append("select info,lddm,cs,fjs,cws,yzcws,wxscws,cws - (yzcws+wxscws) kcws,ldnum,(select sfbz from ssxxb b where a.lddm=b.lddm and a.cs=b.cs and rownum=1)sfbz ");
			sql.append("from(select distinct xqmc||' '||ldmc||'('||xbxd||')' info,lddm, cs,(select count(ssbh) from ssxxb b where a.lddm=b.lddm and a.cs=b.cs ) fjs,");
			sql.append(" (select sum(cws) from ssxxb b where a.lddm=b.lddm and a.cs=b.cs ) cws,(select count(b.xh) from xszsxxb b,ssxxb c where a.lddm=c.lddm and a.cs=c.cs and b.ssbh=c.ssbh and not exists(select 1 from newstusinfo d where d.ksh=b.xh))yzcws,");
			sql.append(" (select count(b.xh) from wxs_xszsxxb b, ssxxb c where a.lddm = c.lddm and a.cs = c.cs and b.ssbh = c.ssbh) wxscws, ");
			sql.append("count(distinct lddm) ldnum from view_ssxx a "+query+query2+" group by xqdm, xqmc, yqdm, yqmc, lddm, ldmc, xbxd, cs ) a order by lddm, to_number(cs) ");
		}
		List ldinfo = dao.rsToVator(sql.toString(), new String[]{}, new String[]{"info","lddm","cs","ldnum","fjs","cws","kcws","sfbz"});
		List list1 = new ArrayList();
//		query+=Base.isNull(nj)?"":" and nj ='"+nj+"' ";
//		query+=Base.isNull(xy)?"":" and xydm ='"+xy+"' ";
		String njmc = Base.isNull(nj)?"":nj+"�� ";
		String xymc = Base.isNull(xy)?"":xymcV+"  ";
		if(ldinfo !=null){
			for(int i=0;i<ldinfo.size();i++){
				String[] tem = (String[])ldinfo.get(i);
				HashMap map = new HashMap();
				info  = tem[0];
				lddm1 = tem[1];
				cs    = tem[2];
//				ldnum = tem[3];
				fjs   = tem[4];
				cws   = tem[5];
				kcws  = tem[6];
				sfbz  = tem[7];	
				
				if(temp !=null && !temp.equals(lddm1)){//�жϲ�ͬ¥��ʱ��put¥����Ϣ
					map.put("info", njmc+xymc+info);
				}
				map.put("fjs", fjs);
				map.put("cws", cws);
				map.put("kcws", kcws);
				map.put("sfbz", sfbz);
				map.put("lddm", lddm1);
				List list2 = new ArrayList();
				//for(int j=1;j<=Integer.parseInt(cs);j++){
				HashMap map1 = new HashMap();
				map1.put("css", "��"+cs+"��");
				if(!Base.isNull(cs)){
					List list3 = new ArrayList();   					
					sql = new StringBuffer();					
//					sql.append( " select distinct ssbh,qsh,(case when (fpbj ='����') then 'green' when (to_number(wxscws)>0 and fpbj <> '����' )  then '#800080'  when (to_number(zsrs)=to_number(cws) and fpbj<>'����') then  '#000000'  when  (to_number(zsrs) = 0 and fpbj<>'����' ) then  'red' else  'orange'  end ) color, " );
//					sql.append(" (case when fpbj = '����' then sffqfj when (to_number(zsrs+wxscws) = 0 and fpbj <> '����') then '�շ�' else ms end)   ms  ");
//					sql.append("  from (  select distinct a.ssbh, a.qsh,a.cws,fpbj,sffqfj,b.ms,(select count(b.xh) from wxs_xszsxxb b where  b.ssbh = a.ssbh) wxscws, (select count(xh) from xszsxxb a where a.ssbh=b.ssbh)zsrs from view_ssxx a left join " );
//					sql.append(" ( select ssbh,max(ltrim(sys_connect_by_path(xymc || nj || '��','��'),'��')) ms from ((select distinct ssbh,nj,xymc,row_number() over(partition by ssbh order by ssbh, nj, xymc) pno,");
//					sql.append(" row_number() over(partition by ssbh order by ssbh,nj,xymc) - 1 sno from ( select distinct ssbh,nj,xymc from (select a.ssbh,b.nj,b.xymc from xszsxxb a, view_xsjbxx b " );
//					sql.append(" where a.xh = b.xh)union (select ssbh,nj,xymc from wxs_xszsxxb )) group by ssbh,nj,xymc)) start with pno = 1 connect by prior pno = sno and prior ssbh = ssbh group by ssbh )b on b.ssbh=a.ssbh ");
//					sql.append(query+"  and cs=?  and lddm=? "+query2+") order by ssbh" ); 
      				sql.append("  select distinct ssbh,qsh,(case when (fpbj ='����') then 'green' when (to_number(wxscws)>0 and fpbj <> '����' )  then '#800080' "); 
					sql.append("  when (to_number(zsrs)=to_number(cws) and fpbj<>'����') then  '#000000'  when  (to_number(zsrs) = 0 and fpbj<>'����' ) then  "); 
					sql.append("  'red' else  'orange'  end ) color  "); 
					sql.append("  from ( select distinct a.ssbh, a.qsh,a.cws,fpbj,sffqfj,"); 
					sql.append(" (select count(b.xh) from wxs_xszsxxb b where  b.ssbh = a.ssbh) wxscws, (select count(b.xh) from xszsxxb b where not exists(select 1 from newstusinfo c where c.ksh=b.xh) and  b.ssbh=a.ssbh)zsrs  "); 
					sql.append(" from view_ssxx a  "+query+ " and cs=?  and lddm=?  "+query2+"   ) order by ssbh "); 
					list3 = dao.getList(sql.toString(), new String[]{cs,lddm1},new String[]{"qsh","color","ssbh"});  
//					sql.append(" select distinct ssbh,qsh,(case when (fpbj ='����') then 'green' when (to_number(wxscws)>0 and fpbj <> '����' )  then '#800080' "); 
//					sql.append(" when (to_number(zsrs)=to_number(cws) and fpbj<>'����') then  '#000000'  when  (to_number(zsrs) = 0 and fpbj<>'����' ) then  "); 
//					sql.append(" 'red' else  'orange'  end ) color,  (case when fpbj = '����' then sffqfj when (to_number(zsrs+wxscws) = 0 and fpbj <> '����') "); 
//					sql.append(" then '�շ�' else ms end)   ms  from ( select distinct a.ssbh, a.qsh,a.cws,fpbj,sffqfj,"); 
//					sql.append(" (select ms from ( select ssbh,max(ltrim(sys_connect_by_path(xymc || nj || '��','��'),'��')) ms from ( "); 
//					sql.append(" (select distinct ssbh,nj,xymc,row_number() over(partition by ssbh order by ssbh, nj, xymc) pno, row_number() over(partition by ssbh order by ssbh,nj,xymc) - 1 sno "); 
//					sql.append(" from ( select distinct ssbh,nj,xymc from (select a.ssbh,a.nj,a.xymc from view_xszsxx a )union  "); 
//					sql.append(" (select ssbh,nj,xymc from wxs_xszsxxb )) group by ssbh,nj,xymc)) start with pno = 1 connect by prior pno = sno  "); 
//					sql.append(" and prior ssbh = ssbh group by ssbh )c where c.ssbh=a.ssbh )ms, "); 
//					sql.append(" (select count(b.xh) from wxs_xszsxxb b where  b.ssbh = a.ssbh) wxscws, (select count(xh) from xszsxxb b where b.ssbh=a.ssbh)zsrs  "); 
//					sql.append(" from view_ssxx a  "+query+ " and cs=?  and lddm=?  "+query2+"   ) order by ssbh "); 
//					list3 = dao.getList(sql.toString(), new String[]{cs,lddm1},new String[]{"qsh","color","ms","ssbh"}); 				
					map1.put("qshcolor", list3);
					list2.add(map1);
					map.put("lddms", list2);
					list1.add(map);
				}
				//}				
				if(temp !=null && !temp.equals(lddm1)){//����¥���븳��������temp��
					temp  = lddm1;
				}
			}

		}
		return list1;
	}
	/** 
	 * ���ĳ���������Ϣ
	 * Method getOneSsInfo
	 * @param ssbh ������
	 * @return HashMap
	 */
	@SuppressWarnings("unchecked")
	public HashMap getOneSsInfo(String ssbh) throws Exception{
		DAO dao = DAO.getInstance();
//		String tablename = "";
		String[] cols = new String[]{"ldmc","qsh","qsdh","sfbz","krzrs","yrzrs","fpbz","sffqfj","bz"};
		String sql = "select yqmc||ldmc ldmc,qsh,qsdh,sfbz,cws krzrs,(select count(xh) from ((select ssbh,xh from view_xszsxx )union(select ssbh,xh from wxs_xszsxxb )union (select ssbh,xh from view_ssszsxx)) where ssbh=?) yrzrs,fpbj fpbz,sffqfj,bz from view_ssxx where ssbh=?";
		List list  = dao.getList(sql, new String[]{ssbh,ssbh},cols);
		HashMap map = new HashMap();
		if(list !=null && list.size()>0){
			map = (HashMap)list.get(0);
			//============2011.3.16 edit by luojw==================
			//ΪӦ��ͼ��ͳ���޷�����ʿ����˶ʿ��
			
//			if(map.get("fpbz")!=null && map.get("fpbz").equals("��ʿ")){
//				tablename = "sss_xxb";
				sql = "select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc from sss_xxb a,xszsxxb b where a.xh=b.xh and b.ssbh=?";
//			}else if(map.get("fpbz")!=null && map.get("fpbz").equals("˶ʿ")){
//				tablename = "sss_xxb";
				sql = "select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc from sss_xxb a,xszsxxb b where a.xh=b.xh and b.ssbh=?";
//			}else{
//				tablename = "view_xsxxb";
				sql = " select * from ((select distinct xh,xm,xb,nj,xymc,zymc,bjmc,ssbh from view_xszsxx )union (select distinct '��У��:    '||xh,xm,xb,nj,xymc,zymc,bjmc,ssbh from wxs_xszsxxb )) where ssbh=?";
//			}
			
			List list2 = dao.rsToVator2(sql, new String[]{ssbh}, new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc"});
			map.put("xsxx",list2);
		}else{
			map = null;
		}
		return map;
	}
	/** 
	 * ����ĳ���������Ϣ
	 * Method saveInfo
	 * @param myModel GyglZgdzdxModel����
	 * @return boolean
	 */
	public boolean saveInfo(GyglZgdzdxModel myModel) throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "update ssxxb set qsdh=?,bz=?,sfbz=?,sffqfj=?,fpbj=? where ssbh=?";
		return dao.runUpdate(sql, new String[]{myModel.getQsdh(),myModel.getBz(),
				myModel.getSfbz(),myModel.getSffqfj(),myModel.getFpbz(),myModel.getSsbh()});
	}
	/** 
	 * У����Ϣ
	 * Method validateInfo
	 * @param String xh ѧ��
	 * @param String strSysDatetime ����ʱ��
	 * @param String verify ��֤
	 * @return int
	 */
	public int validateInfo(String xh,String strSysDatetime,String verify)
	throws Exception{
		DAO dao = DAO.getInstance();
		MD5 md5 = new MD5();
		int value = 0;
		String strKey = "";
		String time = "";
		String sql = "select zfssokey from view_zf_key where rownum=1";
		String[] tmp = dao.getOneRs(sql, new String[] {},
				new String[] { "zfssokey" });
		if (tmp == null) {
			value = 1;
			return value;
		}
		strKey = tmp[0];
		sql = "select round(to_number(sysdate - to_date('"
			+ strSysDatetime
			+ "','YYYY-MM-DDHH24:MI:SS'))*1440*60) SYSDATESTR from view_zf_key";
		tmp = dao.getOneRs(sql, new String[] {}, new String[] { "SYSDATESTR" });
		if (tmp == null) {
			value = 2;
			return value;
		}
		time = tmp[0];
		if (Integer.parseInt(time) > 60) {
			value = 3;
			return value;
		}
		String res = md5.getMD5ofStr(xh + strKey + strSysDatetime);
		if (!res.equalsIgnoreCase(verify)) {
			value = 4;
			return value;
		}
		return 0;
	}
	/** 
	 * ���ĳ��ѧ���������������Ϣ
	 * Method viewSsInfo
	 * @param xh ѧ��
	 * @param tableName �����ͼ��
	 * @return HashMap
	 */
	@SuppressWarnings("unchecked")
	public HashMap viewSsInfo(String xh,String tableName) throws Exception{
		DAO dao = DAO.getInstance();
		String[] cols = new String[]{"xh","xm","xb","xymc","zymc","bjmc","ldmc","qsh","qsdh","sfbz","krzrs","yrzrs","fpbz","sffqfj","bz"};
		StringBuffer sb = new StringBuffer();
		String sql = "";
		sb.append("select c.xh,c.xm,c.xb,c.xymc,c.zymc,c.bjmc,a.ldmc,a.qsh,a.qsdh,a.sfbz,a.cws krzrs,(select count(a.xh)");
		sb.append(" from xszsxxb a,(select ssbh from xszsxxb where xh=?) b where a.ssbh=b.ssbh ) yrzrs,a.fpbj fpbz,a.sffqfj,a.bz from view_ssxx a,xszsxxb b,");
		sb.append(tableName);
		sb.append(" c where a.ssbh=b.ssbh and b.xh=c.xh and c.xh=?");
		List list  = dao.getList(sb.toString(), new String[]{xh,xh},cols);
		HashMap map = new HashMap();
		if(list !=null && list.size()>0){
			map = (HashMap)list.get(0);
			sql = "select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc from "+tableName+" a,(select ssbh from xszsxxb where xh=?) b,xszsxxb c where c.ssbh=b.ssbh and a.xh=c.xh and a.xh<>?";
			List list2 = dao.rsToVator2(sql, new String[]{xh,xh}, new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc"});
			map.put("xsxx",list2);
		}else{
			map = null;
		}
		return map;
	}

	public boolean dao_bedCompartSave(String newMappingItems,String oldMappingItems) throws SQLException{
		DAO dao = DAO.getInstance();
		boolean doFlag = false;
		String[] delSql    = null;
		String[] inserSql  = null;
		if(((oldMappingItems != null) && !oldMappingItems.equals(""))){
			//ɾ�����ƴ��
			String delItems1[]     = oldMappingItems.split(",");
			int delNum             =  delItems1.length;
			String delItems2[][]   = new String[delNum][];
			delSql                 = new String[delNum];
			for (int i = 0; i < delNum; i++) {
				delItems2[i] = delItems1[i].split("/");
				delSql[i]    = " delete from xszsxxb where xh = '"+delItems2[i][0]+"' "; 
			}
		}else{
			delSql     = new String[1];
			delSql[0]  = " delete from xszsxxb where 1=2 ";
		}
		if ((newMappingItems != null) && !newMappingItems.equals("")) {	
			//�������ƴ��
			String inserItems1[] = newMappingItems.split(",");
			int num = inserItems1.length;
			String inserItems2[][] = new String[num][];
			inserSql                 = new String[num];
			for(int i=0;i<num;i++){
				inserItems2[i] = inserItems1[i].split("/");
				inserSql[i]      = " insert into xszsxxb(xh,ssbh,cwh,rzrq) values('"+inserItems2[i][0]+"','"+inserItems2[i][1]+"','"+inserItems2[i][2]+"','"+inserItems2[i][3]+"') ";
			}
		}else{
			inserSql = new String[1];
			inserSql[0] =  "delete from xszsxxb where 1=2"; 
		}
		String[] exesql = dao.unionArray(delSql, inserSql);
		int[] array = dao.runBatch(exesql);
		doFlag = dao.checkBatch(array);
		return doFlag;
	}
	public String[] dao_getStuInF(String userName,String xh){
		String[] arrValue = null;
		DAO dao = DAO.getInstance();
		if(GyglShareDAO.isSssAdmin(userName)){
			arrValue   = dao.getOneRs("select a.nj,a.xydm,a.bjdm,a.xb,b.lddm,b.cs,b.xqdm from view_ssszsxx a,view_ssxx b where a.ssbh=b.ssbh and xh=?  ",
					new String[]{xh},new String[]{"nj","xydm","bjdm","xb","lddm","cs","xqdm"});
		}else{
			arrValue   = dao.getOneRs("select a.nj,a.xydm,a.bjdm,a.xb,b.lddm,b.cs,b.xqdm from view_xszsxx a,view_ssxx b where a.ssbh=b.ssbh and xh=?  ",
					new String[]{xh},new String[]{"nj","xydm","bjdm","xb","lddm","cs","xqdm"});
		}
		return arrValue;
	}
	public boolean dao_yqManagerAdd(GyglYqManagerModel model) throws Exception{
		DAO  dao    = DAO.getInstance();
		String yqdm = model.getYqdm();
		String xm   = model.getXm();
		String rzrq = model.getRzrq();
		String lzrq = model.getLzrq();
		String lxdh = model.getLxdh();
		String dzyx = model.getDzyx();
		String bz   = model.getBz();
		String pk   = "yqdm||xm";
		boolean done = false;
		String sql  = "delete from yqfzrb where "+pk+"= ?";
		done = dao.runUpdate(sql,new String[]{yqdm+xm});
		if(done){
			sql     = " insert into yqfzrb(yqdm,xm,lxdh,dzyx,bz,rzrq,lzrq)values(?,?,?,?,?,?,?) ";
			done    = dao.runUpdate(sql,new String[]{yqdm,xm,lxdh,dzyx,bz,rzrq,lzrq});
		}
		return done;    	
	}

	public ArrayList<HashMap<String, String>> dao_getyqManagerTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] colList   = null;
		String[] colListCN = null;
		colList = new String[]{"prkey", "yqmc","xm","rzrq","lxdh","sfzz"};//��ѯ��ʾ�ֶ� 
		colListCN = new String[]{ "����","԰��","����","��ְ����","��ϵ�绰","�Ƿ���ְ" };
		for (int i = 0; i < colList.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", colList[i]);
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
	public ArrayList<String[]> dao_getyqManagerResult(String yqdm,String xm,String sfzz) {
		DAO dao = DAO.getInstance();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String pk   = "yqdm||xm||rzrq";
		String sql    =  "";
		String[] colList = null;
		StringBuffer querry = new StringBuffer();
		//��ѯ����
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(yqdm)?"":" and yqdm='"+yqdm+"' ");
		querry.append(Base.isNull(xm)?"":" and xm like '%"+xm+"%' ");	
		querry.append(Base.isNull(sfzz)?"":" and sfzz like '%"+sfzz+"%' ");	
		colList = new String[]{"prkey", "yqmc","xm","rzrq","lxdh","sfzz"};//��ѯ��ʾ�ֶ�
		sql     = " select "+pk+" prkey,yqmc,xm,rzrq,lxdh,sfzz from view_yqfzrxx ";  
//		ִ�в�ѯ
		rs = dao.rsToVator(sql + querry.toString(), new String[]{},colList);
		return rs;
	}
	public HashMap<String,String> dao_yqManagerInfo(String pkValue) {
		DAO dao = DAO.getInstance();
		String pk   = "yqdm||xm||rzrq";
		String sql = "select yqdm,xm,bz,rzrq,lzrq,lxdh,dzyx,sfzz from view_yqfzrxx where "+pk+"=?";
		String[] colList = new String[]{ "yqdm","xm","bz","rzrq","lzrq","lxdh","dzyx","sfzz"};
		return dao.getMap(sql,new String[]{pkValue},colList);
	}
	public boolean dao_yqManagerModi(GyglYqManagerModel model,String pkValue) throws Exception{
		DAO  dao    = DAO.getInstance();
		String yqdm = model.getYqdm();
		String xm   = model.getXm().trim();
		String rzrq = model.getRzrq();
		String lzrq = model.getLzrq();
		String lxdh = model.getLxdh();
		String dzyx = model.getDzyx();
		String bz   = model.getBz();
		String sfzz = model.getSfzz();
		String pk   = "yqdm||xm||rzrq";
		boolean done = false;
		String sql    = " update yqfzrb set yqdm=?,xm=?,lxdh=?,dzyx=?,bz=?,rzrq=?,lzrq=?,sfzz=? where "+pk+"=? ";
		done    = dao.runUpdate(sql,new String[]{yqdm,xm,lxdh,dzyx,bz,rzrq,lzrq,sfzz,pkValue});	    	
		return done;    	
	} 
	public boolean dao_yqManagerDel(String pkValue) throws Exception{
		DAO  dao    = DAO.getInstance();	    	
		String pk   = "yqdm||xm||rzrq";
		boolean done = false;
		String sql    = " delete yqfzrb where "+pk+"=? ";
		done    = dao.runUpdate(sql,new String[]{pkValue});	    	
		return done;    	
	} 
	public boolean dao_ldManagerAdd(GyglLdManagerModel model) throws Exception{
		DAO  dao    = DAO.getInstance();
		String lddm = model.getLddm();
		String lz   = model.getLz().trim();
		String rzrq = model.getRzrq();
		String lzrq = model.getLzrq();
		String lxdh = model.getLxdh();
		String dzyx = model.getDzyx();
		String bz   = model.getBz();
		String pk   = "lddm||lz||rzrq";
		boolean done = false;
		String sql  = "delete from zgdd_lzxxb where "+pk+"= ?";
		done = dao.runUpdate(sql,new String[]{lddm+lz+rzrq});
		if(done){
			sql     = " insert into zgdd_lzxxb(lddm,lz,rzrq,lzrq,lxdh,dzyx,bz)values(?,?,?,?,?,?,?) ";
			done    = dao.runUpdate(sql,new String[]{lddm,lz,rzrq,lzrq,lxdh,dzyx,bz});
		}
		return done;    	
	}
	public ArrayList<HashMap<String, String>> dao_getldManagerTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] colList   = null;
		String[] colListCN = null;
		colList = new String[]{"prkey", "yqmc","ldmc","lz","lxdh","rzrq","sfzz"};//��ѯ��ʾ�ֶ� 
		colListCN = new String[]{ "����","԰��","¥������","¥��","��ϵ�绰","��ְ����","�Ƿ���ְ" };
		for (int i = 0; i < colList.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", colList[i]);
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
	public ArrayList<String[]> dao_getldManagerResult(GyglLdManagerModel model ) {
		DAO dao = DAO.getInstance();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String pk   = "lddm||lz||rzrq";
		String sql    =  "";
		String[] colList = null;
		StringBuffer querry = new StringBuffer();
		//��ѯ����
		String yqdm = model.getYqdm();
		String lddm = model.getLddm();
		String lz   = model.getLz().trim();
		String sfzz = model.getSfzz();
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(yqdm)?"":" and yqdm='"+yqdm+"' ");
		querry.append(Base.isNull(lddm)?"":" and lddm='"+lddm+"' ");
		querry.append(Base.isNull(lz)?"":" and lz like '%"+lz+"%' ");	
		querry.append(Base.isNull(sfzz)?"":" and sfzz like '%"+sfzz+"%' ");	
		colList = new String[]{"prkey", "yqmc","ldmc","lz","lxdh","rzrq","sfzz"};//��ѯ��ʾ�ֶ� 
		sql     = " select "+pk+" prkey,yqmc,ldmc,lz,lxdh,rzrq,sfzz from zgdd_view_lzxx ";  
//		ִ�в�ѯ
		rs = dao.rsToVator(sql + querry.toString(), new String[]{},colList);
		return rs;
	}
	public HashMap<String,String> dao_ldManagerInfo(String pkValue) {
		DAO dao = DAO.getInstance();
		String pk   = "lddm||lz||rzrq";
		String sql = "select * from zgdd_view_lzxx where "+pk+"=?";
		String[] colList = new String[]{ "yqdm","lddm","lz","rzrq","lzrq","lxdh","dzyx","sfzz"};
		return dao.getMap(sql,new String[]{pkValue},colList);
	}
	public boolean dao_ldManagerModi(GyglLdManagerModel model,String pkValue) throws Exception{
		DAO  dao    = DAO.getInstance();
		String lddm = model.getLddm();
		String lz   = model.getLz().trim();
		String rzrq = model.getRzrq();
		String lzrq = model.getLzrq();
		String lxdh = model.getLxdh();
		String dzyx = model.getDzyx();
		String bz   = model.getBz();
		String sfzz = model.getSfzz();
		String pk   = "lddm||lz||rzrq";
		boolean done  = false;
		String sql     = " update zgdd_lzxxb set lddm=?,lz=?,rzrq=?,lzrq=?,lxdh=?,dzyx=?,bz=?,sfzz=? where "+pk+"=?";
		done    = dao.runUpdate(sql,new String[]{lddm,lz,rzrq,lzrq,lxdh,dzyx,bz,sfzz,pkValue});		    	
		return done;    	
	}
	public boolean dao_ldManagerDel(String pkValue) throws Exception{
		DAO  dao    = DAO.getInstance();	    	
		String pk   = "lddm||lz||rzrq";
		boolean done = false;
		String sql    = " delete zgdd_lzxxb where "+pk+"=? ";
		done    = dao.runUpdate(sql,new String[]{pkValue});	    	
		return done;    	
	} 
	public boolean dao_lcManagerAdd(GyglLcManagerModel model) throws Exception{
		DAO  dao    = DAO.getInstance();
		String lddm = model.getLddm();
		String cs   = model.getCs();
		String cz   = model.getCz().trim();
		String rzrq = model.getRzrq();
		String lzrq = model.getLzrq();
		String lxdh = model.getLxdh();
		String dzyx = model.getDzyx();
		String bz   = model.getBz();
		String pk   = "lddm||cs||cz||rzrq";
		boolean done = false;
		String sql  = "delete from zgdd_czxxb where "+pk+"= ?";
		done = dao.runUpdate(sql,new String[]{lddm+cs+cz+rzrq});
		if(done){
			sql     = " insert into zgdd_czxxb(lddm,cs,cz,rzrq,lzrq,lxdh,dzyx,bz)values(?,?,?,?,?,?,?,?) ";
			done    = dao.runUpdate(sql,new String[]{lddm,cs,cz,rzrq,lzrq,lxdh,dzyx,bz});
		}
		return done;    	
	}
	public ArrayList<HashMap<String, String>> dao_getlcManagerTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] colList   = null;
		String[] colListCN = null;
		colList = new String[]{"prkey", "yqmc","ldmc","cs","cz","xm","bjmc","zsqk","lxdh","rzrq","sfzz"};//��ѯ��ʾ�ֶ� 
		colListCN = new String[]{ "����","԰��","¥������","����","�㳤(ѧ��)","����","�༶","ס��","��ϵ�绰","��ְ����","�Ƿ���ְ" };
		for (int i = 0; i < colList.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", colList[i]);
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
	public ArrayList<String[]> dao_getlcManagerResult(GyglLcManagerModel model ) {
		DAO dao = DAO.getInstance();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String pk   = "lddm||cs||cz||rzrq";
		String sql    =  "";
		String[] colList = null;
		StringBuffer querry = new StringBuffer();
		//��ѯ����
		String yqdm = model.getYqdm();
		String lddm = model.getLddm();
		String cs   = model.getCs();
		String cz   = model.getCz().trim();
		String sfzz = model.getSfzz();
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(yqdm)?"":" and yqdm='"+yqdm+"' ");
		querry.append(Base.isNull(lddm)?"":" and lddm='"+lddm+"' ");
		querry.append(Base.isNull(cs)?"":" and cs='"+cs+"' ");
		querry.append(Base.isNull(cz)?"":" and cz like '%"+cz+"%' ");	
		querry.append(Base.isNull(sfzz)?"":" and sfzz like '%"+sfzz+"%' ");	
		colList = new String[]{"prkey", "yqmc","ldmc","cs","cz","xm","bjmc","zsqk","lxdh","rzrq","sfzz"};//��ѯ��ʾ�ֶ� 
		sql     = " select "+pk+" prkey,yqmc,ldmc,cs,cz,xm,bjmc,zsqk,lxdh,rzrq,sfzz from zgdd_view_czxx ";  
//		ִ�в�ѯ
		rs = dao.rsToVator(sql + querry.toString(), new String[]{},colList);
		return rs;
	}
	public HashMap<String,String> dao_lcManagerInfo(String pkValue) {
		DAO dao = DAO.getInstance();
		String pk   = "lddm||cs||cz||rzrq";
		String sql = "select * from zgdd_view_czxx where "+pk+"=?";
		String[] colList = new String[]{ "yqdm","yqmc","ldmc","lddm","cz","cs","rzrq","lzrq","lxdh","dzyx","bz","sfzz"};
		return dao.getMap(sql,new String[]{pkValue},colList);
	}
	public boolean dao_lcManagerModi(GyglLcManagerModel model,String pkValue) throws Exception{
		DAO  dao    = DAO.getInstance();
		String lddm = model.getLddm();
		String cs   = model.getCs();
		String cz   = model.getCz().trim();
		String rzrq = model.getRzrq();
		String lzrq = model.getLzrq();
		String lxdh = model.getLxdh();
		String dzyx = model.getDzyx();
		String bz   = model.getBz();
		String sfzz = model.getSfzz();
		String pk   = "lddm||cs||cz||rzrq";
		boolean done  = false;
		String sql     = " update zgdd_czxxb set lddm=?,cs=?,cz=?,rzrq=?,lzrq=?,lxdh=?,dzyx=?,bz=?,sfzz=? where "+pk+"=?";
		done    = dao.runUpdate(sql,new String[]{lddm,cs,cz,rzrq,lzrq,lxdh,dzyx,bz,sfzz,pkValue});		    	
		return done;    	
	}
	public boolean dao_lcManagerDel(String pkValue) throws Exception{
		DAO  dao    = DAO.getInstance();	    	
		String pk   = "lddm||cs||cz||rzrq";
		boolean done = false;
		String sql    = " delete zgdd_czxxb where "+pk+"=? ";
		done    = dao.runUpdate(sql,new String[]{pkValue});	    	
		return done;    	
	}
	public boolean dao_yqzbManagerAdd(GyglYqzbModel model) throws Exception{
		DAO  dao    = DAO.getInstance();
		String yqdm = model.getYqdm();
		String xm   = model.getXm();		
		String rq   = model.getRq();
		String bz   = model.getBz();
//		String pk   = "id";
		boolean done = false;		
		String sql     = " insert into yqzbqkb(id,rq,yqdm,zbr,zbqk)values(S_YQZBQK_ID.Nextval,?,?,?,?) ";
		done    = dao.runUpdate(sql,new String[]{rq,yqdm,xm,bz});		
		return done;    	
	}
	public ArrayList<HashMap<String, String>> dao_getyqzbManageTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] colList   = null;
		String[] colListCN = null;
		colList = new String[]{"prkey", "yqmc","rq","zbr","zbqk"};//��ѯ��ʾ�ֶ� 
		colListCN = new String[]{ "����","԰��","����","ֵ����","ֵ���¼"};
		for (int i = 0; i < colList.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", colList[i]);
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
	public ArrayList<String[]> dao_getyqzbManageResult(GyglYqzbModel model ) {
		DAO dao = DAO.getInstance();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String pk   = "id";
		String sql    =  "";
		String[] colList = null;
		StringBuffer querry = new StringBuffer();
		//��ѯ����
		String yqdm = model.getYqdm();
		String xm   = model.getXm().trim();
		String lzrq = model.getLzrq();
		String rzrq = model.getRzrq();
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(yqdm)?"":" and yqdm='"+yqdm+"' ");
		querry.append(Base.isNull(xm)?"":" and zbr like '%"+xm+"%' ");	
		if(!Base.isNull(lzrq)&&!Base.isNull(rzrq)){
			querry.append(" and rq between '"+rzrq+"' and '"+lzrq+"' ");	
		}else if(!Base.isNull(rzrq)&&Base.isNull(lzrq)){
			querry.append(" and rq = '"+rzrq+"' ");
		}else if(Base.isNull(rzrq)&&!Base.isNull(lzrq)){
			querry.append(" and rq = '"+lzrq+"' ");
		}
	//
		colList = new String[]{"prkey", "yqmc","rq","zbr","zbqk"};//��ѯ��ʾ�ֶ� 
		sql     = " select "+pk+" prkey, yqmc,rq,zbr,(case when zbqk is null then '' else substr(zbqk,1,10)||'...' end)zbqk from zgdd_yqzbqk ";  
//		ִ�в�ѯ
		rs = dao.rsToVator(sql + querry.toString(), new String[]{},colList);
		return rs;
	}
	public HashMap<String,String> dao_yqzbManageInfo(String pkValue) {
		DAO dao = DAO.getInstance();
		String pk   = "id";
		String sql = "select yqdm,rq,zbr xm,zbqk bz from zgdd_yqzbqk where "+pk+"=?";
		String[] colList = new String[]{"yqdm","rq","xm","bz" };
		return dao.getMap(sql,new String[]{pkValue},colList);
	}
	public boolean dao_yqzbManageModi(GyglYqzbModel model,String pkValue) throws Exception{
		DAO  dao    = DAO.getInstance();
		String yqdm = model.getYqdm();
		String zbr   = model.getXm().trim();
		String rq   = model.getRq();		
		String zbqk   = model.getBz();
		String pk   = "id";
		boolean done  = false;
		String sql     = " update zgdd_yqzbqk set yqdm=?,zbr=?,rq=?,zbqk=? where "+pk+"=?";
		done    = dao.runUpdate(sql,new String[]{yqdm,zbr,rq,zbqk,pkValue});		    	
		return done;    	
	}
	public boolean dao_yqzbManageDel(String pkValue) throws Exception{
		DAO  dao    = DAO.getInstance();	    	
		String pk   = "id";
		boolean done = false;
		String sql    = " delete zgdd_yqzbqk where "+pk+"=? ";
		done    = dao.runUpdate(sql,new String[]{pkValue});	    	
		return done;    	
	}
	public boolean dao_yqhdManagerAdd(GyglYqhdModel model) throws Exception{
		DAO  dao    = DAO.getInstance();
		String xn   = model.getXn();
		String xq   = model.getXq();
		String yqdm = model.getYqdm();
		String rq   = model.getRq();
		String hdmc = model.getHdmc().trim();
		String sj   = model.getSj().trim();
		String fzr  = model.getXm().trim();
		String zzdw = model.getZzdw().trim();
		String cjry = model.getCjry().trim();
		String hdnr = model.getHdnr();
		String pk   = "yqdm||rq||hdmc";
		boolean done = false;
		String sql  = "delete from yqhdxxb where "+pk+"= ?";
		done = dao.runUpdate(sql,new String[]{yqdm+rq+hdmc});
		if(done){
			sql     = " insert into yqhdxxb(xn,xq,yqdm,rq,hdmc,sj,fzr,zzdw,cjry,hdnr)values(?,?,?,?,?,?,?,?,?,?) ";
			done    = dao.runUpdate(sql,new String[]{xn,xq,yqdm,rq,hdmc,sj,fzr,zzdw,cjry,hdnr});
		}
		return done;    	
	}
	public ArrayList<HashMap<String, String>> dao_getyqhdManageTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] colList   = null;
		String[] colListCN = null;
		colList = new String[]{"prkey","xn","xqmc","hdmc" ,"yqmc","rq","fzr","zzdw","hdnr"};//��ѯ��ʾ�ֶ� 
		colListCN = new String[]{ "����","ѧ��","ѧ��","�","԰��","����","�������","��֯��λ","�����"};
		for (int i = 0; i < colList.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", colList[i]);
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
	public ArrayList<String[]> dao_getyqhdManageResult(GyglYqhdModel model ) {
		DAO dao = DAO.getInstance();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String pk   = "yqdm||rq||hdmc";
		String sql    =  "";
		String[] colList = null;
		StringBuffer querry = new StringBuffer();
		//��ѯ����
		String xn = model.getXn();
		String xq   = model.getXq();
		String yqdm = model.getYqdm();
		String xm   = model.getXm().trim();
		String lzrq = model.getLzrq();
		String rzrq = model.getRzrq();
		String zzdw = model.getZzdw();
		String hdnr = model.getHdnr();
		String hdmc = model.getHdmc();
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(yqdm)?"":" and yqdm='"+yqdm+"' ");
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
		querry.append(Base.isNull(xm)?"":" and fzr like '%"+xm+"%' ");
		querry.append(Base.isNull(zzdw)?"":" and zzdw like '%"+zzdw+"%' ");
		querry.append(Base.isNull(hdnr)?"":" and hdnr like '%"+hdnr+"%' ");
		querry.append(Base.isNull(hdmc)?"":" and hdmc like '%"+hdmc+"%' ");
		if(!Base.isNull(lzrq)&&!Base.isNull(rzrq)){
			querry.append(" and rq between '"+rzrq+"' and '"+lzrq+"' ");	
		}else if(!Base.isNull(rzrq)&&Base.isNull(lzrq)){
			querry.append(" and rq = '"+rzrq+"' ");
		}else if(Base.isNull(rzrq)&&!Base.isNull(lzrq)){
			querry.append(" and rq = '"+lzrq+"' ");
		}
		colList = new String[]{"prkey","xn","xqmc","hdmc" ,"yqmc","rq","fzr","zzdw","hdnr"};//��ѯ��ʾ�ֶ� 
		sql     = " select "+pk+" prkey, xn,xqmc,hdmc,yqmc,rq,fzr,zzdw,(case when hdnr is null then '' else substr(hdnr,1,10)||'...' end)hdnr from view_yqhdxx ";  
//		ִ�в�ѯ
		rs = dao.rsToVator(sql + querry.toString(), new String[]{},colList);
		return rs;
	}
	public HashMap<String,String> dao_yqhdManageInfo(String pkValue) {
		DAO dao = DAO.getInstance();
		String pk   = "yqdm||rq||hdmc";
		String sql = "select xn,xq,yqdm,rq,hdmc,sj,fzr xm,zzdw,cjry,hdnr from yqhdxxb where "+pk+"=?";
		String[] colList = new String[]{"xn","xq","yqdm","rq","hdmc","sj","xm","zzdw","cjry","hdnr"};
		return dao.getMap(sql,new String[]{pkValue},colList);
	}
	public boolean dao_yqhdManageModi(GyglYqhdModel model,String pkValue) throws Exception{
		DAO  dao    = DAO.getInstance();
		String xn   = model.getXn();
		String xq   = model.getXq();
		String yqdm = model.getYqdm();
		String rq   = model.getRq();
		String hdmc = model.getHdmc().trim();
		String sj   = model.getSj().trim();
		String fzr  = model.getXm().trim();
		String zzdw = model.getZzdw().trim();
		String cjry = model.getCjry().trim();
		String hdnr = model.getHdnr();
	    String pk   = "yqdm||rq||hdmc";
		boolean done  = false;
		String sql     = " update yqhdxxb set xn=?,xq=?,yqdm=?,rq=?,hdmc=?,sj=?,fzr=?,zzdw=?,cjry=?,hdnr=? where "+pk+"=?";
		done    = dao.runUpdate(sql,new String[]{xn,xq,yqdm,rq,hdmc,sj,fzr,zzdw,cjry,hdnr,pkValue});		    	
		return done;    	
	}
	public boolean dao_yqhdManageDel(String pkValue) throws Exception{
		DAO  dao    = DAO.getInstance();	    	
		String pk   = "yqdm||rq||hdmc";
		boolean done = false;
		String sql    = " delete yqhdxxb where "+pk+"=? ";
		done    = dao.runUpdate(sql,new String[]{pkValue});	    	
		return done;    	
	}
	public List<HashMap<String,String>> getWxsSSList(String xb){
		DAO  dao    = DAO.getInstance();
		StringBuffer querry = new StringBuffer();
		querry.append((Base.isNull(xb))?" and 1=2 ":" and (xbxd='���' or xbxd='"+xb+"') ");
 	    String sql = " select distinct a.ssbh dm,a.ldmc||a.qsh mc from view_ssxx a where fpbj='һ��' and not exists (" 
		    + " select b.ssbh,b.cwssum from (select ssbh,sum(cws) cwssum from ssfpb group by ssbh )b where a.ssbh=b.ssbh and a.cws=b.cwssum "
		    + " ) "+querry+" order by a.ssbh";
		
		return dao.getList(sql,new String[]{},new String[]{"dm","mc"});
	}
	public List<HashMap<String,String>> getWxsCwList(String ssbh){
		DAO  dao    = DAO.getInstance();
		StringBuffer querry = new StringBuffer();
		querry.append((Base.isNull(ssbh))?" and 1=2 ":" and a.ssbh='"+ssbh+"' ");
		//��������ְҵ����ѧԺ ��λ�Ŵ������ģ����Ի��޸�
		String sb = "";
		if("12898".equals(Base.xxdm)){
			sb = "a.cwh";
		}else{
			sb = "to_number(a.cwh)";
		}
		String sql  = " select a.cwh dm,a.cwh mc from cwxxb a  where (a.cwh not in (select cwh from xszsxxb b where a.ssbh=b.ssbh)) and  "
			   +" (a.cwh not in (select cwh from wxs_xszsxxb b where a.ssbh=b.ssbh)) "+querry+" order by "+sb+" ";		
		return dao.getList(sql,new String[]{},new String[]{"dm","mc"});
	}
	public boolean dao_wxsDormUserAdd(GyglWxsDormUModel model) throws Exception{
		DAO  dao    = DAO.getInstance();
		String xh   = model.getXh().trim();
		String xm   = model.getXm().trim();
		String xb   = model.getXb();
		String ssbh = model.getSsbh();
		String cwh  = model.getCwh();
		String xymc = model.getXymc();
		String zymc = model.getZymc();
		String bjmc = model.getBjmc();
		String nj   = model.getNj();
		String bz   = model.getBz();
		String rzrq = model.getRzrq();
		String pk   = "xh";
		boolean done = false;
		String sql  = "delete from wxs_xszsxxb where "+pk+"= ?";
		done = dao.runUpdate(sql,new String[]{xh});
		if(done){
			sql     = " insert into wxs_xszsxxb(xh,ssbh,cwh,xm,xb,nj,xymc,zymc,bjmc,bz,rzrq)values(?,?,?,?,?,?,?,?,?,?,?) ";
			done    = dao.runUpdate(sql,new String[]{xh,ssbh,cwh,xm,xb,nj,xymc,zymc,bjmc,bz,rzrq});
		}
		return done;    	
	}
	public ArrayList<HashMap<String, String>> dao_getWxsDUManageTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] colList   = null;
		String[] colListCN = null;
		colList = new String[]{"prkey","xh","xm","xb","yqmc","ldmc" ,"qsh","cwh","rzrq","sfbz"};//��ѯ��ʾ�ֶ� 
		colListCN = new String[]{ "����","ѧ��","����","�Ա�","԰��","¥��","���Һ�","��λ��","��ס����","�շѱ�׼"};
		for (int i = 0; i < colList.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", colList[i]);
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
	public ArrayList<String[]> dao_getWxsDUManageResult(GyglWxsDormUModel model ) {
		DAO dao = DAO.getInstance();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String pk   = "xh";
		String sql    =  "";
		String[] colList = null;
		StringBuffer querry = new StringBuffer();
		//��ѯ����
		String xqdm = model.getXiaoqu();
		xqdm = Base.isNull(xqdm) ? model.getXqdm() : xqdm;
		String lddm = model.getLddm();
		String cs = model.getCs();
		String yqdm = model.getYqdm();
		String qsh   = model.getQsh();
		String xh = model.getXh();
		String xm = model.getXm();
		String xb = model.getXb();		
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(yqdm)?"":" and yqdm='"+yqdm+"' ");
		querry.append(Base.isNull(lddm)?"":" and lddm='"+lddm+"' ");
		querry.append(Base.isNull(xh)?"":" and xh like '%"+xh.trim()+"%' ");
		querry.append(Base.isNull(xm)?"":" and xm like '%"+xm.trim()+"%' ");
		querry.append(Base.isNull(xqdm)?"":" and xqdm = '"+xqdm+"' ");
		querry.append(Base.isNull(cs)?"":" and cs ='"+cs+"' ");
		querry.append(Base.isNull(qsh)?"":" and qsh ='"+qsh+"' ");
		querry.append(Base.isNull(xb)?"":" and xb = '"+xb+"' ");	
		colList = new String[]{"prkey","xh","xm","xb","yqmc","ldmc" ,"qsh","cwh","rzrq","sfbz"};//��ѯ��ʾ�ֶ� 
		sql     = " select "+pk+" prkey, xh,xm,xb,yqmc,ldmc,qsh,cwh,rzrq,sfbz from view_wxs_xszsxx ";  
//		ִ�в�ѯ
		rs = dao.rsToVator(sql + querry.toString(), new String[]{},colList);
		return rs;
	}
	public boolean dao_wxsDormUserModi(GyglWxsDormUModel model,String pkValue) throws Exception{
		DAO  dao    = DAO.getInstance();
		//String xh   = model.getXh().trim();
		String xm   = model.getXm().trim();
		String xb   = model.getXb();
		String ssbh = model.getSsbh();
		String cwh  = model.getCwh();
		String xymc = model.getXymc();
		String zymc = model.getZymc();
		String bjmc = model.getBjmc();
		String nj   = model.getNj();
		String bz   = model.getBz();
		String rzrq = model.getRzrq();
		String pk   = "xh";
		boolean done = false;
		String sql     = " update wxs_xszsxxb set ssbh=?,cwh=?,xm=?,xb=?,nj=?,xymc=?,zymc=?,bjmc=?,bz=?,rzrq=?  where "+pk+"=?  ";
		done    = dao.runUpdate(sql,new String[]{ssbh,cwh,xm,xb,nj,xymc,zymc,bjmc,bz,rzrq,pkValue});	
		return done;    	
	}
	public HashMap<String,String> dao_wxsDormUserInfo(String pkValue) {
		DAO dao = DAO.getInstance();
		String pk   = "xh";
		String sql = "select xh,ssbh,cwh,xm,xb,nj,xymc,zymc,bjmc,bz,rzrq,xqdm,xqmc,yqdm,yqmc,lddm,ldmc,qsh from view_wxs_xszsxx where "+pk+"=?";
		String[] colList = new String[]{"xh","ssbh","cwh","xm","xb","nj","xymc","zymc","bjmc","bz","rzrq","xqdm","xqmc","yqdm","yqmc","lddm","ldmc","qsh"};
		return dao.getMap(sql,new String[]{pkValue},colList);
	}
	public boolean dao_wxsDormUserDel(String toHistory,String delPk,String tssj) throws SQLException{
		DAO dao = DAO.getInstance();
		String sql = "";
		String[] pkValueA = delPk.split("!!");
		String[] instPkSql = null;
		String[] delPkSql  = new String[pkValueA.length];
		StringBuffer instSqlStr = new StringBuffer();
		for(int i=0;i<pkValueA.length;i++){
			sql = " insert into xszslsxxb(xh,ssbh,bz,cwh,rzrq,tfrq,xm,xb,nj,xymc,zymc,bjmc,ldmc,qsh,xsbz) " 
				+" select xh,ssbh,bz,cwh,rzrq,'"+tssj+"',xm,xb,nj,xymc,zymc,bjmc,yqmc||ldmc,qsh,'�Ǳ�У��'  from view_wxs_xszsxx where xh='"+pkValueA[i]+"'";
			instSqlStr.append(Base.isNull(pkValueA[i])?"":sql).append("!!");						
		}
		instPkSql = instSqlStr.toString().split("!!");
	    for (int i = 0; i < pkValueA.length; i++) {
	    	delPkSql[i] = Base.isNull(pkValueA[i])?"":"delete wxs_xszsxxb where xh= '"+pkValueA[i]+"' ";							
	    } 
	    boolean doFlag = false;
        if("yes".equalsIgnoreCase(toHistory)){//����ѧ��ס����Ϣɾ����ת�Ƶ�ס����ʷ��Ϣ����   
        	String[] exesql = dao.unionArray(instPkSql, delPkSql);
        	int[] array = dao.runBatch(exesql);
        	doFlag = dao.checkBatch(array);    	
        }else{
        	int[] array = dao.runBatch(delPkSql);
        	doFlag = dao.checkBatch(array);   
        }
		 return doFlag;
	}
	public ArrayList<HashMap<String, String>> dao_getdyDManageTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] colList = new String[]{"xqmc","yqmc","ldmc","qsh","cy"};//��ѯ��ʾ�ֶ� 
		String[] colListCN = new String[]{ "У��","԰��","¥��","���Һ�","���ҵ�Ա��Ա"};
		for (int i = 0; i < colList.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", colList[i]);
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
	public ArrayList<String[]> dao_getdyDManageResult(String xqdm,String yqdm,String lddm) {
		DAO dao = DAO.getInstance();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String sql             =  "";
		StringBuffer querry = new StringBuffer();
		//��ѯ����
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(yqdm)?"":" and yqdm='"+yqdm+"' ");
		querry.append(Base.isNull(lddm)?"":" and lddm='"+lddm+"' ");
		querry.append(Base.isNull(xqdm)?"":" and xqdm = '"+xqdm+"' ");
		String[] colList = new String[]{"xqmc","yqmc","ldmc","qsh","cy"};//��ѯ��ʾ�ֶ� 
        sql = " select * from view_dyssxx ";
		//		ִ�в�ѯ
		rs = dao.rsToVator(sql+querry.toString() , new String[]{},colList);
		return rs;
	}
    public boolean dao_jswmhdSqSave(GyglJswmhdModel model,String userName) throws Exception{
        DAO       dao  =DAO.getInstance();
    	String zbdw    =model.getZbdw();//���쵥λ
    	String fzrxm   =model.getFzrxm();//����������
    	String fzrlxfs =model.getFzrlxfs();//��������ϵ��ʽ��
    	String jsrxm   =model.getJsrxm();//����������
    	String jsrlxfs =model.getJsrlxfs();//��������ϵ��ʽ
    	String hddd    =model.getHddd();//��ص�
    	String hdksrq  =model.getHdksrq();//���ʼ���ڣ��գ�
    	String hdjsrq  =model.getHdjsrq();//��������ڣ��գ�
    	
    	String hdkssj  =model.getHdkssj();//���ʼʱ�䣨ʱ��
    	String hdjssj  =model.getHdjssj();//�����ʱ�䣨ʱ��
    	String cjrs    =model.getCjrs();//�μ�����
    	String sqrxm   =model.getSqrxm();//����������
    	String hdnr    =model.getHdnr();//�����
    	String hdmc    =model.getHdmc();//�����    	
        StringBuffer sql = new StringBuffer("insert into jswmhdsqb(hdmc,zbdw,fzrxm,fzrlxfs,jsrxm,jsrlxfs,hddd,hdksrq,hdjsrq,hdkssj,hdjssj,");
    	             sql.append("cjrs,hdnr,sqrdlm,sqrxm) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        return dao.runUpdate(sql.toString(), new String[]{hdmc,zbdw,fzrxm,fzrlxfs,jsrxm,jsrlxfs,hddd,hdksrq,hdjsrq,hdkssj,hdjssj,cjrs,hdnr,userName,sqrxm});
    }
    public ArrayList<String[]> dao_hdShMResult(GyglJswmhdModel model,String userType){
    	DAO     dao    = DAO.getInstance();
    	ArrayList<String[]> rs = new ArrayList<String[]>();
    	String[] colList       = null;
    	StringBuffer querry    = new StringBuffer(" where 1=1 ");
    	String hdmc   = model.getHdmc();
    	String hdnr   = model.getHdnr();
    	String zzdw   = model.getZzdw();
    	String yesNo  = model.getYesNo();
    	String hdksrq = model.getHdksrq();
    	String hdjsrq = model.getHdjsrq();
    	querry.append((Base.isNull(hdmc)?"":" and hdmc like '%"+hdmc+"%' "));
    	querry.append((Base.isNull(hdnr)?"":" and hdnr like '%"+hdnr+"%' "));
    	querry.append((Base.isNull(zzdw)?"":" and zzdw like '%"+zzdw+"%' "));
    	if(!Base.isNull(hdksrq)&&!Base.isNull(hdjsrq)){
    		querry.append(" and hdksrq like '"+hdksrq+"%' ");
    	    querry.append(" and hdjsrq like '"+hdjsrq+"%' ");
    	}else if(!Base.isNull(hdksrq)){
    		querry.append(" and hdksrq like '"+hdksrq+"%' ");
    	}else if(!Base.isNull(hdjsrq)){
    		querry.append(" and hdjsrq like '"+hdjsrq+"' ");
    	}
    	    	
    	String sql = "";
        
    	if("xx".equalsIgnoreCase(userType)){
    		querry.append((Base.isNull(yesNo)?"":" and xxsh='"+yesNo+"' "));
    		querry.append(" and xysh='ͨ��' ");
    		querry.append(" order by sqsj desc");
    		sql = "select rowid rid, hdmc,hddd,hdksrq||hdkssj hdksrq,hdjsrq||hdjssj hdjsrq,(case when hdnr is null then '' else substr(hdnr,1,10)||'...' end)hdnr,xxsh from jswmhdsqb ";
    		colList = new String[]{"rid","hdmc","hddd","hdksrq","hdjsrq","hdnr","xxsh"};
    	} else if("xy".equalsIgnoreCase(userType)){
    		querry.append((Base.isNull(yesNo)?"":" and xysh='"+yesNo+"' "));
    		querry.append(" order by sqsj desc");
    		sql = "select rowid rid,hdmc,hddd,hdksrq||hdkssj hdksrq,hdjsrq||hdjssj hdjsrq,(case when hdnr is null then '' else substr(hdnr,1,5)||'...' end)hdnr,xysh from jswmhdsqb ";
    		colList = new String[]{"rid","hdmc","hddd","hdksrq","hdjsrq","hdnr","xysh"};
    	}
    	rs = dao.rsToVator(sql+querry.toString() , new String[]{},colList);
     	return rs;
    }
    public ArrayList<HashMap<String, String>> dao_hdShMTitle(String userType) {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] colListCN = new String[]{ "����","�����","��ص�","���ʼ����","���������","�����","Ժϵ���"};
		String[] colList = new String[]{"rid","hdmc","hddd","hdksrq","hdjsrq","hdnr","xxsh"};
		if("xx".equalsIgnoreCase(userType)){
			colListCN = new String[]{ "����","�����","��ص�","���ʼ����","���������","�����","ѧУ���"};
		}				
		for (int i = 0; i < colList.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", colList[i]);
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
	public HashMap<String,String> dao_jswmhdShInfo(String pkValue) {
		DAO dao = DAO.getInstance();
		String sql = "select hdmc,zbdw,hddd,hdksrq||hdkssj hdksrq,hdjsrq||hdjssj hdjsrq,fzrxm,fzrlxfs,jsrxm,jsrlxfs,cjrs,hdnr,sqrxm,'Ժϵ��ˣ�'||xysh||'  '||'ѧУ��ˣ�'||xxsh shzt,sqsj from jswmhdsqb where rowid=?";
		String[] colList = new String[]{"hdmc","zbdw","hddd","hdksrq","hdjsrq","fzrxm","fzrlxfs","jsrxm","jsrlxfs","cjrs","hdnr","sqrxm","shzt","sqsj" };
		return dao.getMap(sql,new String[]{pkValue},colList);
	}
	public boolean dao_jswmhdSh(String rid,String userType,String shValue) throws Exception{
		DAO dao = DAO.getInstance();
		String shV = "δ���";
		String shType = "xysh";
		if("yes".equalsIgnoreCase(shValue)){
			shV="ͨ��";
		}else{
			shV="��ͨ��";
		}
		if("xx".equalsIgnoreCase(userType)){
			shType = "xxsh";
		}
		String sql = " update jswmhdsqb set "+shType+"=? where rowid=? ";
		return dao.runUpdate(sql, new String[]{shV,rid});
	}
	public boolean getjswmShJudge(String shType,String rid){
		DAO dao  = DAO.getInstance();				
		String sql = "select count(*)cout from jswmhdsqb where rowid=? and "+shType+"<>'δ���' ";
		String strV = dao.getOneRs(sql,  new String[]{rid},"cout");
		if(strV.equalsIgnoreCase("0")){
			return false;
		}else{
			return true;
		}
	}
    public ArrayList<HashMap<String, String>> dao_jswmhdMTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] colListCN = new String[]{ "����","�����","���쵥λ","��ص�","���ʼ����","���������","�����"};
		String[] colList = new String[]{"rid","hdmc","zbdw","hddd","hdksrq","hdjsrq","hdnr"};						
		for (int i = 0; i < colList.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", colList[i]);
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
    public ArrayList<String[]> dao_jswmhdMResult(GyglJswmhdModel model){
    	DAO     dao    = DAO.getInstance();
    	ArrayList<String[]> rs = new ArrayList<String[]>();
    	String[] colList       = null;
    	StringBuffer querry    = new StringBuffer(" where 1=1 ");
    	String hdmc   = model.getHdmc();
    	String hdnr   = model.getHdnr();
    	String zzdw   = model.getZzdw();
     	String hdksrq = model.getHdksrq();
    	String hdjsrq = model.getHdjsrq();
    	String nd     = model.getNd();
    	querry.append((Base.isNull(hdmc)?"":" and hdmc like '%"+hdmc+"%' "));
    	querry.append((Base.isNull(hdnr)?"":" and hdnr like '%"+hdnr+"%' "));
    	querry.append((Base.isNull(zzdw)?"":" and zzdw like '%"+zzdw+"%' "));
    	querry.append((Base.isNull(nd)?"":" and hdksrq like '"+nd+"%' "));
    	if(!Base.isNull(hdksrq)&&!Base.isNull(hdjsrq)){
    		querry.append(" and hdksrq like '"+hdksrq+"%' ");
    	    querry.append(" and hdjsrq like '"+hdjsrq+"%' ");
    	}else if(!Base.isNull(hdksrq)){
    		querry.append(" and hdksrq like '"+hdksrq+"%' ");
    	}else if(!Base.isNull(hdjsrq)){
    		querry.append(" and hdjsrq like '"+hdjsrq+"%' ");
    	}
    	    	
    	String sql = "";
       	querry.append(" and xysh='ͨ��' and xxsh='ͨ��' ");
    	querry.append(" order by sqsj desc");
    	sql = "select rowid rid, hdmc,hddd,zbdw,hdksrq||hdkssj hdksrq,hdjsrq||hdjssj hdjsrq,(case when hdnr is null then '' else substr(hdnr,1,10)||'...' end)hdnr from jswmhdsqb ";
    	colList = new String[]{"rid","hdmc","zbdw","hddd","hdksrq","hdjsrq","hdnr"};

    	rs = dao.rsToVator(sql+querry.toString() , new String[]{},colList);
     	return rs;
    }
    
    public boolean dao_jswmhdSave(GyglJswmhdModel model,String userName) throws Exception{
        DAO       dao  =DAO.getInstance();
    	String zbdw    =model.getZbdw();//���쵥λ
    	String fzrxm   =model.getFzrxm();//����������
    	String fzrlxfs =model.getFzrlxfs();//��������ϵ��ʽ��
    	String jsrxm   =model.getJsrxm();//����������
    	String jsrlxfs =model.getJsrlxfs();//��������ϵ��ʽ
    	String hddd    =model.getHddd();//��ص�
    	String hdksrq  =model.getHdksrq();//���ʼ���ڣ��գ�
    	String hdjsrq  =model.getHdjsrq();//��������ڣ��գ�
    	
    	String hdkssj  =model.getHdkssj();//���ʼʱ�䣨ʱ��
    	String hdjssj  =model.getHdjssj();//�����ʱ�䣨ʱ��
    	String cjrs    =model.getCjrs();//�μ�����
    	String sqrxm   =model.getSqrxm();//¼��������
    	String hdnr    =model.getHdnr();//�����
    	String hdmc    =model.getHdmc();//�����    	
        StringBuffer sql = new StringBuffer("insert into jswmhdsqb(hdmc,zbdw,fzrxm,fzrlxfs,jsrxm,jsrlxfs,hddd,hdksrq,hdkssj,hdjsrq,hdjssj,");
    	             sql.append("cjrs,hdnr,sqrdlm,sqrxm,xysh,xxsh) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        return dao.runUpdate(sql.toString(), new String[]{hdmc,zbdw,fzrxm,fzrlxfs,jsrxm,jsrlxfs,hddd,hdksrq,hdkssj,hdjsrq,hdjssj,cjrs,hdnr,userName,sqrxm,"ͨ��","ͨ��"});
    }
    public HashMap<String,String> getJsWmHdInfo(String pkValue){
    	DAO   dao  = DAO.getInstance();
    	String sql = "select hdmc,zbdw,hddd,hdksrq,hdkssj,hdjsrq,hdjssj,fzrxm,fzrlxfs,jsrxm,jsrlxfs,cjrs,hdnr from jswmhdsqb where rowid=? ";
    	pkValue = pkValue.replace(" ", "+");
    	return  dao.getMap(sql,new String[]{pkValue},new String[]{"hdmc","zbdw","hddd","hdksrq","hdkssj","hdjsrq","hdjssj","fzrxm","fzrlxfs","jsrxm","jsrlxfs","cjrs","hdnr"});                 
    }
    public boolean dao_jswmhdModi(GyglJswmhdModel model,String pkValue) throws Exception{
        DAO       dao  =DAO.getInstance();
    	String zbdw    =model.getZbdw();//���쵥λ
    	String fzrxm   =model.getFzrxm();//����������
    	String fzrlxfs =model.getFzrlxfs();//��������ϵ��ʽ��
    	String jsrxm   =model.getJsrxm();//����������
    	String jsrlxfs =model.getJsrlxfs();//��������ϵ��ʽ
    	String hddd    =model.getHddd();//��ص�
    	String hdksrq  =model.getHdksrq();//���ʼ���ڣ��գ�
    	String hdjsrq  =model.getHdjsrq();//��������ڣ��գ�    	
    	String hdkssj  =model.getHdkssj();//���ʼʱ�䣨ʱ��
    	String hdjssj  =model.getHdjssj();//�����ʱ�䣨ʱ��
    	String cjrs    =model.getCjrs();//�μ�����
    	String hdnr    =model.getHdnr();//�����
    	String hdmc    =model.getHdmc();//�����    	
        StringBuffer sql = new StringBuffer("update jswmhdsqb set hdmc=?,zbdw=?,fzrxm=?,fzrlxfs=?,jsrxm=?,jsrlxfs=?," );
        		         sql.append("hddd=?,hdksrq=?,hdkssj=?,hdjsrq=?,hdjssj=?,cjrs=?,hdnr=? where rowid=? ");	            
        return dao.runUpdate(sql.toString(), new String[]{hdmc,zbdw,fzrxm,fzrlxfs,jsrxm,jsrlxfs,hddd,hdksrq,hdkssj,hdjsrq,hdjssj,cjrs,hdnr,pkValue});
    }
	public boolean dao_jswmhdDel(String pkValue) throws Exception{
		DAO  dao    = DAO.getInstance();	    			
		boolean done = false;
		String sql    = " delete jswmhdsqb where rowid=? ";
		done    = dao.runUpdate(sql,new String[]{pkValue});	    	
		return done;    	
	}
    public ArrayList<HashMap<String, String>> dao_FeeChgTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] colListCN = new String[]{ "���","Ժϵ","ѧ��","����","ԭס����","ԭס�޷ѱ�׼","��ס�޷�","��ס�޷ѱ�׼","�������","�������","��ע"};
		String[] colList = new String[]{"r","xymc","xh","xm","yzss","yzsfbz","xzss","xzsfbz","bgrq","bglx","bz"};						
		for (int i = 0; i < colList.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", colList[i]);
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
    public ArrayList<String[]> dao_FeeChgResult(GyglZsfBgTjModel model){
    	DAO     dao    = DAO.getInstance();
    	ArrayList<String[]> rs = new ArrayList<String[]>();
    	String[] colList       = null;
    	StringBuffer querry    = new StringBuffer(" where 1=1 ");
        String nj  = model.getNj();
        String xydm = model.getXydm();
        String zydm = model.getZydm();
        String bjdm = model.getBjdm();
    	String hdksrq = model.getHdksrq();
    	String hdjsrq = model.getHdjsrq();
    	querry.append((Base.isNull(nj)?"":" and nj = '"+nj+"' "));
    	querry.append((Base.isNull(xydm)?"":" and (xydm = '"+xydm+"' or xydm='noxydm' )"));
    	querry.append((Base.isNull(zydm)?"":" and (zydm = '"+zydm+"' or zydm='nozydm' )"));
    	querry.append((Base.isNull(bjdm)?"":" and (bjdm = '"+bjdm+"' or bjdm='nobjdm' )"));
    	    	
    	if(!Base.isNull(hdksrq)&&!Base.isNull(hdjsrq)){
    		querry.append(" and bgrq between '"+hdksrq+"' and '"+hdjsrq+"' ");
    	}else if(!Base.isNull(hdksrq)){
    		querry.append(" and hdksrq like '"+hdksrq+"%' ");
    	}else if(!Base.isNull(hdjsrq)){
    		querry.append(" and hdjsrq like '"+hdjsrq+"%' ");
    	}	
    	colList = new String[]{"r","xymc","xh","xm","yzss","yzsfbz","xzss","xzsfbz","bgrq","bglx","bz"};						   	
    	String sql = " select rownum r,xymc,xh,xm,yzss,yzsfbz,xzss,xzsfbz,bgrq,bglx,bz from view_zsfbgtjxx";
     	rs = dao.rsToVator(sql+querry.toString() , new String[]{},colList);
     	return rs;
    }
    public List<HashMap<String, String>> dao_getGyFdyCsList(String lddm){
    	DAO dao = DAO.getInstance();
    	return dao.getList("  select '' dm,'--��ѡ��--'mc from dual union all select lc dm,lc mc from (select distinct substr(qsh,1,1) lc from ssxxb where lddm =?) order by dm nulls first", 
				new String[] {lddm}, new String[] { "dm","mc" });	
    }
    public List<HashMap<String, String>> dao_getGyFdySsList(String lddm){
    	DAO dao = DAO.getInstance();
    	return dao.getList(" select '' dm,'--��ѡ��--'mc from dual union all select distinct qsh dm,qsh mc from ssxxb where lddm=? order by dm nulls first ",
				new String[] {lddm}, new String[] { "dm","mc" });	
    }    
}
