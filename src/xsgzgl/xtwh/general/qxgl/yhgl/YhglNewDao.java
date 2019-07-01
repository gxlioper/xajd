package xsgzgl.xtwh.general.qxgl.yhgl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.Encrypt;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xsgzgl.comm.BasicDAO;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ϵͳά��_Ȩ�޹���_�û�����_dao��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author zhanghui
 * @version 1.0
 */
public class YhglNewDao extends BasicDAO{
	
	/**
	 * ����û���Ϣ�����
	 * 
	 * @date 2013-01-08
	 * @author zhanghui
	 */
	public ArrayList<String[]> getYhxxList(YhglNewForm myForm,User user) 
		throws IllegalArgumentException, SecurityException, IllegalAccessException, 
		InvocationTargetException, NoSuchMethodException{
		
		DAO dao = DAO.getInstance();
		
		// ====================��������===================================
		MakeQuery makeQuery = new MakeQuery();
		String[] col=null;
		
		if ("12898".equals(Base.xxdm)) {
			if("wfp".equals(myForm.getFpzt())){
				 col = new String[]{"qx","sffz","sffdy","sfbzr","sfbl"};
				}else{
				 col = new String[]{"zdm","qx","sffz","sffdy","sfbzr","sfbl"};
			}
		}else {
			if("wfp".equals(myForm.getFpzt())){
				 col = new String[]{"qx","sffz","sffdy","sfbzr"};
				}else{
				 col = new String[]{"zdm","qx","sffz","sffdy","sfbzr"};
			}
		}
		
		
		String[] colLike = new String[]{"yhm","xm"};
		
		makeQuery.makeQuery(col, colLike, myForm);		
		
		String[] inputV = makeQuery.getInputList();		
		String searchTj = makeQuery.getQueryString();			
		
		String tj = " ";
		
		if(myForm.getSzbm() != null && !"".equalsIgnoreCase(myForm.getSzbm())){
			tj = dao.getOneRs("select case when bmjb = '2' then ' and szbm = '||chr(39)||bmdm||chr(39) " +
					"else 'and (szbm = '||chr(39)||bmdm||chr(39)||' or " +
					"szbm in (select bmdm from zxbz_xxbmdm where bmfdm ='||chr(39)||bmdm||chr(39)||') )' end tj " +
					"from zxbz_xxbmdm where bmdm = ?",new String[]{myForm.getSzbm()}, "tj");
		}
			
		
		// ====================�������� end===============================
		
		// ====================SQLƴװ===================================		
		StringBuffer sql = new StringBuffer();
		String yhTable = " view_yhz_yhxxb ";
		if(myForm.getZdm() == null || "".equals(myForm.getZdm()) || "wfp".equals(myForm.getFpzt())){
			yhTable = " (select distinct yhm,kl,yhbzdms zdm,yhbzdms,xm,szbm,dwdm,xssx,qx,zmc from view_yhz_yhxxb) ";
		}
		
		sql.append("select rownum r,a.* from(");
		sql.append(" select a.*,case when qx =1 then '����' else 'ͣ��' end qyzt, ");
		sql.append(" case when a.zdm is null then '��' else '��' end sffz, c.bmmc,");
		if ("12898".equals(Base.xxdm)) {
			sql.append(" decode(f.sfbl, 0, '��',1, '��',null,'��') sfbl,");
		}
		sql.append(" decode(d.num,null,'��','��') sffdy,decode(e.num,null,'��','��') sfbzr from "+yhTable+" a  ");
		//sql.append(" left join yhzb b on a.zdm = b.zdm  ");
		sql.append(" left join zxbz_xxbmdm c on a.szbm = c.bmdm ");
		sql.append(" left join (select c.zgh, count(1) num from fdybjb c where bjdm in (select bjdm from xsxxb where (sfzx = '��У' or sfzx is null)) group by c.zgh) d");
		sql.append(" on a.yhm=d.zgh	");
		sql.append(" left join (select d.zgh, count(1) num from bzrbbb d where bjdm in (select bjdm from xsxxb where (sfzx = '��У' or sfzx is null)) group by d.zgh) e");
		sql.append(" on a.yhm=e.zgh");
		if ("12898".equals(Base.xxdm)) {
			sql.append(" left join fdyxxb f on a.yhm=f.zgh ");
		}
		sql.append(" order by qx desc,a.zdm,szbm,a.xm ) a ");
		sql.append(searchTj+tj);
		if("wfp".equals(myForm.getFpzt())){
			sql.append(" and( ");
			sql.append(" yhbzdms not like '%'||'"+myForm.getZdm()+"'||'%' or yhbzdms is null)");
		}
//		sql.append(getUserSql(user));
		String[] colList = new String[] { };
		sql.append(" order by r ");
		// ====================SQLƴװ end================================
		if ("12898".equals(Base.xxdm)) {
			colList = new String[] { "yhm", "yhm", "xm", "zmc", "bmmc", "sffz", "qyzt" ,"sffdy", "sfbzr","sfbl"};
		}else {
			colList = new String[] { "yhm", "yhm", "xm", "zmc", "bmmc", "sffz", "qyzt" ,"sffdy", "sfbzr"};
		}

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonQuery(sql.toString(),"", inputV, colList, myForm);
		return list;

	}
	
	/**
	 * ��֤�û����Ƿ����
	 * 
	 * @date 2013-01-08
	 * @author zhanghui
	 */
	public Boolean checkYhm(String yhm){
		DAO dao = DAO.getInstance();
		boolean flag = true;
		String count = "0";
		
		count = dao.getOneRs("select count(1) num from yhb where yhm = ? ", new String[]{yhm}, "num");
		
		if(!"0".equalsIgnoreCase(count)){
			flag = false;
		}
		
		return flag;
	}
	
	/**
	 * �����û�Ȩ��
	 * 
	 * @date 2013-01-08
	 * @author zhanghui
	 */
	public Boolean saveYhqx(String yhm){

		DAO dao = DAO.getInstance();
		boolean flag = true;
		
		try {
			//�ж��û��Ƿ�����Ȩ��
			String num = dao.getOneRs("select count(1) num from yhqxb where yhm = ? ", new String[]{yhm}, "num");
			//��Ȩ���Ѵ��ڣ���ɾ����������
			if(null !=num && !"0".equalsIgnoreCase(num)){
				flag = dao.runUpdate("delete from yhqxb where yhm = ? ", new String[]{yhm});
			}
			flag = dao.runUpdate("insert into yhqxb select b.yhm,a.gnmkdm,a.dxq from " +
					"yhzqxb a,yhb b where a.zdm = b.zdm and b.yhm =? ", new String[]{yhm});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * ɾ���û�
	 * 
	 * @date 2013-01-09
	 * @author zhanghui
	 */	
	public Boolean deleteYhxx(YhglNewForm myForm){	
		boolean flag = true;
		
		String[] primarykey_checkVal = myForm.getPrimarykey_checkVal();
		List<String[]> params = new ArrayList<String[]>();
		for(int i=0;i<primarykey_checkVal.length;i++){
			params.add(new String[]{primarykey_checkVal[i]});
		}
		
		try {
			flag = saveArrDate("delete from yhb where yhm = ?", params, "");
			if(flag){
				flag = saveArrDate("delete from yhqxb where yhm = ?", params, "");
			}
		} catch (Exception e) {			
			flag = false;
			System.out.println("<------- �û�ɾ��ʧ�� ------>");
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * ��ȡ�û���Ϣ
	 */
	public HashMap<String,String> getYhxx(String yhm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.yhm,a.xm,a.zdm,a.dwdm,a.szbm,a.qx,a.qxzt,a.dwmc,b.zmc,a.bmmc ");
		sql.append(" from view_yhxx a ");
		sql.append(" left join (select distinct yhm,zmc from view_yhz_yhxxb) b on a.yhm=b.yhm ");
		sql.append(" where a.yhm=? ");
		return DAO.getInstance().getMap(sql.toString(), new String[]{yhm}, new String[]{"yhm","xm","zdm","dwdm","szbm","qx","qxzt","dwmc","zmc","bmmc"});
	}
	
	/**
	 * ���ĳ�û�ָ���ȼ��Ĺ���ģ��
	 * @param lv:�� 1�� 2�� 3
	 * @param userName
	 * @return
	 */
	public List<HashMap<String, String>> getGnmkList(String userName,String yhm,String zdm, int lv) {
		StringBuilder sql = new StringBuilder();
		String jbxz = "";
		String length = "3";
		
		// ��ȡ���ܲ˵�����
		switch (lv) {
		case 1: jbxz="and length(a.gnmkdm)=3 "; length = "3"; break;
		case 2: jbxz="and length(a.gnmkdm)=5 "; length = "5"; break;
		case 3: jbxz="and length(a.gnmkdm)=7 "; length = "7"; break;
		default: jbxz=""; break;
		}
		
		sql.append("select rownum r,a.gnmkdm dm,a.gnmkmc mc,case when b.gnmkdm is null then '' else 'checked' end checked,b.dxq from gnmkdmb a " );
		sql.append("left join (select * from yhqxb where yhm =?) b on a.gnmkdm = b.gnmkdm where a.sfqy='��' ");
		
		//�û����ɷ����Լ��е�Ȩ��
//		sql.append("and a.gnmkdm in (select gnmkdm from yhqxb where yhm = ?)");
		
		//��ѧ�����û����ط�ѧ����Ϊ�յĹ���ģ��
		sql.append(" and (a.mklx != 'stu' or a.mklx is null) ");
		
		sql.append(jbxz);
		sql.append(" order by to_number(a.xssx),a.gnmkdm");

		String[] inputs = new String[]{yhm};
		String[] colList = new String[]{"dm", "mc","dxq","checked" };
		
		return DAO.getInstance().getList(sql.toString(), inputs, colList);
	}

	/**
	 * ��ö༶�Ĺ���ģ��
	 */
	public List<HashMap<String, String>> getDjGnmkList(String yhm) {
		StringBuilder sql = new StringBuilder();

		sql.append("select a.gnmkdm id,a.fjgndm pId,a.gnmkmc name,case when b.gnmkdm is null then 'false' else 'true' end checked,b.dxq from GNMKDMB_DJ a " );
		sql.append("left join (select * from yhqxb where yhm = ?) b on a.gnmkdm = b.gnmkdm where a.sfqy='1' ");

		//��ѧ�����û����ط�ѧ����Ϊ�յĹ���ģ��
		sql.append(" and (a.mklx != 'stu' or a.mklx is null) ");
		sql.append(" order by to_number(a.xssx),a.gnmkdm");

		String[] inputs = new String[]{yhm};
		String[] colList = new String[]{"id", "pId","name","checked","dxq" };

		return DAO.getInstance().getList(sql.toString(), inputs, colList);
	}
	
	/**
	 * ����û�����ģ�����
	 * 
	 * @date 2013-01-11
	 * @author zhanghui
	 */
	public String[] getYhqx(String yhm) throws SQLException{
		DAO dao = DAO.getInstance();	
		String[] yhgnmk = new String[]{};
		yhgnmk = dao.getArray("select * from yhqxb where yhm = ? ", new String[]{yhm}, "gnmkdm");
		return yhgnmk;
	}
	
	/**
	 * ��ʼ���û�����
	 * 
	 * @date 2013-01-09
	 * @author zhanghui
	 */	
	public Boolean cshYhmm(YhglNewForm myForm){	

		Encrypt ept = new Encrypt();
		
		boolean flag = true;
		String kl = myForm.getKl();
		String[] primarykey_checkVal = myForm.getPrimarykey_checkVal();
		List<String[]> params = new ArrayList<String[]>();
		for(int i=0;i<primarykey_checkVal.length;i++){
			params.add(new String[]{primarykey_checkVal[i]});
		}
		
		try {
			flag = saveArrDate("update yhb set kl= '"+ept.encrypt(kl)+"' where yhm = ?", params, "");
		} catch (Exception e) {			
			flag = false;
			System.out.println("<------- �û������ʼ��ʧ�� ------>");
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * �����޸��û���Ϣ
	 * 
	 * @date 2013-01-18
	 * @author zhanghui
	 */	
	public Boolean plUpdateYhxx(YhglNewForm myForm){	

		boolean flag = true;
		String zdm = myForm.getZdm();
		String qx  = myForm.getQx();
		
		//sqlƴ��
		StringBuffer sql = new StringBuffer();
		StringBuffer query = new StringBuffer();
		
		StringBuffer qxsql_del = new StringBuffer();
		StringBuffer qxsql_ins = new StringBuffer();
		
		sql.append("update yhb set ");
		//�û�ͣ�õ�ʱ����Ҫ��zdmΪ�ա�
		//if(zdm != null && !"".equalsIgnoreCase(zdm)){
			query.append("zdm = zdm||',");
			query.append(zdm);
			query.append("',");
			qxsql_del.append("delete yhqxb a where a.yhm = ? and exists (select 1 from yhzqxb b where b.zdm = ? and a.gnmkdm=b.gnmkdm)  ");
			qxsql_ins.append("insert into yhqxb select ? yhm,a.gnmkdm,a.dxq from yhzqxb a where zdm = ? ");
		//}
		if(qx != null && !"".equalsIgnoreCase(qx)){
			query.append("qx = '");
			query.append(qx);
			query.append("',");
		}
		sql.append(query.substring(0, query.length()-1));
		sql.append(" where yhm = ? ");
		
		//
		
		
		String[] primarykey_checkVal = myForm.getPrimarykey_checkVal();
		List<String[]> params = new ArrayList<String[]>();
		List<String[]> paramsDelIns = new ArrayList<String[]>();
		for(int i=0;i<primarykey_checkVal.length;i++){
			params.add(new String[]{primarykey_checkVal[i]});
			paramsDelIns.add(new String[]{primarykey_checkVal[i], zdm});
		}
		
		try {
			flag = saveArrDate(sql.toString(), params, "");
			if(flag && (zdm != null && !"".equalsIgnoreCase(zdm))){
				flag = saveArrDate(qxsql_del.toString(),paramsDelIns,"");
				if(flag){
					flag = saveArrDate(qxsql_ins.toString(),paramsDelIns,"");
				}
			}
		} catch (Exception e) {			
			flag = false;
			System.out.println("<------- �û���Ϣ�����޸�ʧ�� ------>");
			e.printStackTrace();
		}
		
		return flag;
	}
	/**
	 * ����ȡ������
	 */
	public Boolean plQxYhxx(YhglNewForm myForm){
		
		boolean flag = true;
		String zdm = myForm.getZdm();
		String qx  = myForm.getQx();
		
		//sqlƴ��
		StringBuffer sql = new StringBuffer();
		StringBuffer query = new StringBuffer();
		
		StringBuffer qxsql_del = new StringBuffer();
		
		sql.append("update yhb set ");
		//�û�ͣ�õ�ʱ����Ҫ��zdmΪ�ա�
		//if(zdm != null && !"".equalsIgnoreCase(zdm)){
		query.append("zdm = replace(zdm,'"+zdm+"',''), ");
		qxsql_del.append("delete yhqxb a where a.yhm = ? and exists (select 1 from yhzqxb b where b.zdm = ? and a.gnmkdm=b.gnmkdm)  ");
		//}
		if(qx != null && !"".equalsIgnoreCase(qx)){
			query.append("qx = '");
			query.append(qx);
			query.append("',");
		}
		sql.append(query.substring(0, query.length()-1));
		sql.append(" where yhm = ? ");
		
		//
		
		
		String[] primarykey_checkVal = myForm.getPrimarykey_checkVal();
		List<String[]> params = new ArrayList<String[]>();
		List<String[]> paramsDelIns = new ArrayList<String[]>();
		for(int i=0;i<primarykey_checkVal.length;i++){
			params.add(new String[]{primarykey_checkVal[i]});
			paramsDelIns.add(new String[]{primarykey_checkVal[i], zdm});
		}
		
		try {
			flag = saveArrDate(sql.toString(), params, "");
			if(flag && (zdm != null && !"".equalsIgnoreCase(zdm))){
				flag = saveArrDate(qxsql_del.toString(),paramsDelIns,"");
			}
		} catch (Exception e) {			
			flag = false;
			System.out.println("<------- �û���Ϣ����ȡ������ʧ�� ------>");
			e.printStackTrace();
		}
		
		return flag;
	}
	/**
	 * �û�����
	 */
	public Boolean yhfz(YhglNewForm myForm){	

		boolean flag = true;
		String zdm = myForm.getZdm();
		String[] zdmArr = zdm.split(","); 
		String qx  = myForm.getQx();
		
		//sqlƴ��
		StringBuffer sql = new StringBuffer();
		StringBuffer query = new StringBuffer();
		
		List<String> qxsql_del_ins = new ArrayList<String>();
		
		sql.append("update yhb set ");
		//�û�ͣ�õ�ʱ����Ҫ��zdmΪ�ա�
		//if(zdm != null && !"".equalsIgnoreCase(zdm)){
			query.append("zdm = '");
			query.append(zdm);
			query.append("',");
		//}
		if(qx != null && !"".equalsIgnoreCase(qx)){
			query.append("qx = '");
			query.append(qx);
			query.append("',");
		}
		sql.append(query.substring(0, query.length()-1));
		sql.append(" where yhm = ? ");
		
		//
		
		String[] primarykey_checkVal = myForm.getPrimarykey_checkVal();
		List<String[]> params = new ArrayList<String[]>();
		for(int i=0;i<primarykey_checkVal.length;i++){
			params.add(new String[]{primarykey_checkVal[i]});
			qxsql_del_ins.add("delete yhqxb a where a.yhm = '"+primarykey_checkVal[i]+"' and exists (select 1 from yhzqxb b where b.zdm in (select zdm from view_yhz_yhxxb where yhm = '"+primarykey_checkVal[i]+"') and a.gnmkdm=b.gnmkdm)  ");
			for (int j = 0; j < zdmArr.length; j++) {
				qxsql_del_ins.add("delete yhqxb a where a.yhm = '"+primarykey_checkVal[i]+"' and exists (select 1 from yhzqxb b where b.zdm = '"+zdmArr[j]+"' and a.gnmkdm=b.gnmkdm)  ");
				qxsql_del_ins.add("insert into yhqxb select '"+primarykey_checkVal[i]+"' yhm,a.gnmkdm,a.dxq from yhzqxb a where zdm = '"+zdmArr[j]+"' ");
			}
		}
		
		try {
			flag = saveArrDate(qxsql_del_ins.toArray(new String[]{}));
			if(flag && (zdm != null && !"".equalsIgnoreCase(zdm))){
				flag = saveArrDate(sql.toString(), params, "");
			}
		} catch (Exception e) {			
			flag = false;
			System.out.println("<------- �û�����ʧ�� ------>");
			e.printStackTrace();
		}
		
		return flag;
	}
	/**
	 * �û�ͣ��
	 */
	public Boolean yhty(YhglNewForm myForm){	
		
		boolean flag = true;
		//sqlƴ��
		StringBuffer sql = new StringBuffer();
		
		List<String> qxsql_del = new ArrayList<String>();
		
		sql.append("update yhb set zdm = null,qx = '0' where yhm = ?");
		//
		
		String[] primarykey_checkVal = myForm.getPrimarykey_checkVal();
		List<String[]> params = new ArrayList<String[]>();
		for(int i=0;i<primarykey_checkVal.length;i++){
			params.add(new String[]{primarykey_checkVal[i]});
			qxsql_del.add("delete yhqxb a where a.yhm = '"+primarykey_checkVal[i]+"'  "); //�������Ȩ��
		}
		
		try {
			flag = saveArrDate(qxsql_del.toArray(new String[]{}));
			if(flag){
				flag = saveArrDate(sql.toString(), params, "");
			}
		} catch (Exception e) {			
			flag = false;
			System.out.println("<------- �û�ͣ��ʧ�� ------>");
			e.printStackTrace();
		}
		
		return flag;
	}
	/**
	 * �Ƿ�˼���ɼ�������������Ի���
	 */
	public int[] szkj(String[] zghs, String sfbl) throws Exception {
		 DAO dao = DAO.getInstance();
		StringBuilder sb = new StringBuilder();
		String[] arr = new String[zghs.length];
		for (int i = 0; i < zghs.length; i++) {
			sb = new StringBuilder();
			sb.append("update fdyxxb set" );
			sb.append(" sfbl= '"+sfbl+"',");
			String sql = sb.toString();
			sql = sql.substring(0, sql.length()-1);
			String endsql= sql+ " where zgh='"+zghs[i]+"'";
			arr[i] = endsql;
		}
		return dao.runBatch(arr);
	}
}
