package xsgzgl.pjpy.general.wdpj.pjtj;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: zfsoft</p>
 * <p>Author: qlj</p>
 * <p>Version: 1.0</p>
 * <p>Time:2012-8-10 ����02:17:44</p>
 */
public class WdpjPjtjMethod extends CommService{
	
	
	// -------------------------ǰ�ý�ѧ��������� begin -------------------------
	/**
	 * ǰ�ý�ѧ����Ի��޸�
	 * @param map
	 * @return
	 * @throws SQLException 
	 */
	public String checkQzjxj(HashMap<String,String>map) throws SQLException{
		
		DAO dao=DAO.getInstance();
		
		//��������
		String sqlx=(String)map.get("sqlx");
		//��Ϣ
		String message="";
		//��������ϱ�ѧ��ѧ��
		String xh=map.get("xh");
		
		String condition =(String)map.get("condition");
		
		String[] jxjmc=condition.split("!!@@!!");
		// -------------------���Ի�ҵ�� sql begin-------------------------
		StringBuilder sql=new StringBuilder();
		sql.append(" select xh from xg_pjpy_pjxmsqb a,xg_pjpy_pjxmwhb b  ");
		sql.append(" where (a.sqjg = 'tg' or b.sfsh='no') and a.xmdm=b.xmdm");
		sql.append(" and a.pjxn=b.pjxn and a.pjxq=b.pjxq and a.pjnd=b.pjnd ");
		sql.append(" and xh =? ");
		
		sql.append(" and ( ");
		
		for(int i=0;i<jxjmc.length;i++){
			
			if(i!=0){
				sql.append(" or ");
			}
			sql.append(" xmmc like '"+jxjmc[i]+"' ");
		}
		
		sql.append(" )  ");
		// -------------------���Ի�ҵ�� sql end-------------------------
		
		String[]pjzgXh=dao.getArray(sql.toString(), new String[]{xh}, "xh");
		
		//����˵��
		String tjms = (String)map.get("tjms");

		boolean flag = (pjzgXh==null || pjzgXh.length==0 ? false :true );
		
		if (!flag) {
			
			//�����������Ļ�
			message = "�������Ŀ��Ҫ��" + tjms + ",�����߲���������������";

		}
		return message;
	}
	
	// -------------------------ǰ�ý�ѧ��������� end---------------------------
	
	
	
	// -------------------------Υ�ʹ���������� begin ---------------------
	/**
	 * �ж�����ѧ���Ƿ���Υ�ʹ���
	 * @param map
	 * @return
	 * @throws SQLException 
	 */
	public String checkWjcfByPjXn(HashMap<String,String>map) throws SQLException{
		
		DAO dao=DAO.getInstance();
		
		//��������
		String sqlx=(String)map.get("sqlx");
		//����ѧ��
		String glxn=(String)map.get("xn");
		//����ѧ��
		String glxq=(String)map.get("xq");	
		//��Ϣ
		String message="";
		//��������ϱ�ѧ��ѧ��
		String xh=map.get("xh");
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select xh from wjcfb ");
		sql.append(" where xh=? ");
		
		if("pjxn".equalsIgnoreCase(glxn)){
			sql.append(" and xn = (select pjxn from xg_pjpy_xtszb where rownum = 1)  ");
		}else if("dqxn".equalsIgnoreCase(glxn)){
			sql.append(" and xn = (select dqxn from xtszb where rownum = 1)  ");
		}
		
		if("pjxq".equalsIgnoreCase(glxq)){
			sql.append(" and xq = (select pjxq from xg_pjpy_xtszb where rownum = 1)  ");
		}else if("dqxq".equalsIgnoreCase(glxq)){
			sql.append(" and xq = (select dqxq from xtszb where rownum = 1)  ");
		}
		
		sql.append(" and xxsh = 'ͨ��'  ");
		sql.append(" group by xh  ");
		
		String[]nozgXh=dao.getArray(sql.toString(), new String[]{xh}, "xh");
		
		//����˵��
		String tjms = (String)map.get("tjms");

		boolean flag = (nozgXh==null || nozgXh.length==0 ? true : false);
		
		if (!flag) {
			
			//�����������Ļ�
			message = "�������Ŀ��Ҫ��" + tjms + ",�����߲���������������";

		}
		return message;
	}
	
	/**
	 * �ж��Ƿ���Υ�ʹ���
	 * @param map
	 * @return
	 * @throws SQLException 
	 */
	public String checkWjcf(HashMap<String,String>map) throws SQLException{
		
		DAO dao=DAO.getInstance();
		
		//��������
		String sqlx=(String)map.get("sqlx");
		//��Ϣ
		String message="";
		//��������ϱ�ѧ��ѧ��
		String xh=map.get("xh");
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select xh from wjcfb ");
		sql.append(" where xh =? ");
		sql.append(" and xxsh = 'ͨ��'  ");
		sql.append(" group by xh  ");
		
		String[]nozgXh=dao.getArray(sql.toString(), new String[]{xh}, "xh");
		
		//����˵��
		String tjms = (String)map.get("tjms");

		boolean flag = (nozgXh==null || nozgXh.length==0 ? true : false);
		
		if (!flag) {
			
			//�����������Ļ�
			message = "�������Ŀ��Ҫ��" + tjms + ",�����߲���������������";

		}
		return message;
	}
	
	//  -------------------------Υ�ʹ���������� end ---------------------
	
	
	
	// -------------------------�������Ŀ��� begin--------------------------
	/**
	 * �ж��Ƿ���ڲ������Ŀ
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public String checkBjgkm(HashMap<String,String>map) throws SQLException{
		
		DAO dao=DAO.getInstance();
		
		//��Ϣ
		String message="";
		//��������ϱ�ѧ��ѧ��
		String xh=map.get("xh");
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select xh,min(cj)cj from view_zhhcjb ");
		sql.append(" where xh = ");
		sql.append("'"+xh+"' group by xh ");
		
		HashMap<String,String>search=dao.getMapNotOut(sql.toString(), new String[]{"xh","cj"});
		
		//����˵��
		String tjms = (String)map.get("tjms");

		boolean flag = (search==null || search.size()==0 ? true : false);
		
		if (!flag) {
			
			int minCj=Integer.parseInt(search.get("cj"));
			
			if(minCj<0){
				//�����������Ļ�
				message = "�������Ŀ��Ҫ��" + tjms + ",�����߲���������������";

			}
		}
		
		return message;
	}
	
	/**
	 * �ж��Ƿ���ڲ������Ŀ(����ѧ��)
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public String checkBjgkmByXn(HashMap<String,String>map) throws SQLException{
		
		DAO dao=DAO.getInstance();
		
		//��Ϣ
		String message="";
		//��������ϱ�ѧ��ѧ��
		String xh=map.get("xh");
		//����ѧ��
		String glxn=(String)map.get("xn");
		//����ѧ��
		String glxq=(String)map.get("xq");	
		
		String condition=map.get("condition");
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select xh,min(cj)cj from view_zhhcjb ");
		sql.append(" where xh = ");
		sql.append("'"+xh+"' ");
		
		if(!Base.isNull(condition)){
			sql.append(condition);
		}
		
		if("pjxn".equalsIgnoreCase(glxn)){
			sql.append(" and xn = (select pjxn from xg_pjpy_xtszb where rownum = 1)  ");
		}else if("dqxn".equalsIgnoreCase(glxn)){
			sql.append(" and xn = (select dqxn from xtszb where rownum = 1)  ");
		}
		
		if("pjxq".equalsIgnoreCase(glxq)){
			sql.append(" and xq = (select pjxq from xg_pjpy_xtszb where rownum = 1)  ");
		}else if("dqxq".equalsIgnoreCase(glxq)){
			sql.append(" and xq = (select dqxq from xtszb where rownum = 1)  ");
		}
		sql.append(" group by xh ");
		
		HashMap<String,String>search=dao.getMapNotOut(sql.toString(), new String[]{});
		
		//����˵��
		String tjms = (String)map.get("tjms");

		boolean flag = (search==null || search.size()==0 ? true : false);
		
		if (!flag) {
			
			double minCj=Double.parseDouble(search.get("cj"));
			
			if(minCj<60){
				//�����������Ļ�
				message = "�������Ŀ��Ҫ��" + tjms + ",�����߲���������������";

			}
		}
		
		return message;
	}
	// -------------------------�������Ŀ��� end--------------------------
	
	
	// -------------------------������������� begin--------------------------	
	/**
	 * �Ƿ�������
	 * @param map
	 * @return
	 * @throws SQLException 
	 */
	public String checkKnskz(HashMap<String,String>map) throws SQLException{
		
		DAO dao=DAO.getInstance();
		
		
		//��Ϣ
		String message="";
		//��������ϱ�ѧ��ѧ��
		String xh=map.get("xh");
		//����ѧ��
		String glxn=(String)map.get("xn");
		//����ѧ��
		String glxq=(String)map.get("xq");	
		
		String condition=(String)map.get("condition");
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select xh from xszz_knsb ");
		sql.append(" where xh =? ");
		
		sql.append(" and ( ");
		
		String[]knlb=condition.split("!!@@!!");
		for(int i=0;i<knlb.length;i++){
			
			if(i!=0){
				sql.append(" or ");
			}
			sql.append(" xmzzjb ='"+knlb[i]+"' ");
		}
		
		sql.append(" )  ");
		
		if("pjxn".equalsIgnoreCase(glxn)){
			sql.append(" and xn = (select pjxn from xg_pjpy_xtszb where rownum = 1)  ");
		}else if("dqxn".equalsIgnoreCase(glxn)){
			sql.append(" and xn = (select dqxn from xtszb where rownum = 1)  ");
		}
		
		if("pjxq".equalsIgnoreCase(glxq)){
			sql.append(" and xq = (select pjxq from xg_pjpy_xtszb where rownum = 1)  ");
		}else if("dqxq".equalsIgnoreCase(glxq)){
			sql.append(" and xq = (select dqxq from xtszb where rownum = 1)  ");
		}
		
		sql.append(" and ((exists(select 1 from xszz_zzxmb where shjb='һ�����' and xmdm='5002') and shzt1='ͨ��') ");
		sql.append(" or (exists(select 1 from xszz_zzxmb where shjb='�������' and xmdm='5002') and shzt2='ͨ��') ");
		sql.append(" or (exists(select 1 from xszz_zzxmb where shjb='�������' and xmdm='5002') and shzt3='ͨ��') ) ");
		
		String[]nozgXh=dao.getArray(sql.toString(), new String[]{xh}, "xh");
		
		//����˵��
		String tjms ="��ͥ�������Ϊ"+condition.replaceAll("!!@@!!", "��");

		boolean flag = (nozgXh==null || nozgXh.length==0 ? true : false);
		
		if (flag) {
				//�����������Ļ�
			message = "�������Ŀ��Ҫ��" + tjms + ",�����߲���������������";

		}
		return message;
	}


	/**
	 * �Ƿ�������
	 * @param map
	 * @return
	 * @throws SQLException 
	 */
	public String checkKns(HashMap<String,String>map) throws SQLException{
		
		DAO dao=DAO.getInstance();
		
		//��������
		String sqlx=(String)map.get("sqlx");
		//����ѧ��
		String glxn=(String)map.get("xn");
		//����ѧ��
		String glxq=(String)map.get("xq");	
		//��Ϣ
		String message="";
		//��������ϱ�ѧ��ѧ��
		String xh=map.get("xh");
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select xh from xszz_shztb  ");
		sql.append(" where xmdm='5002' and xh=?  ");
		
		if("pjxn".equalsIgnoreCase(glxn)){
			sql.append(" and xn = (select pjxn from xg_pjpy_xtszb where rownum = 1)  ");
		}else if("dqxn".equalsIgnoreCase(glxn)){
			sql.append(" and xn = (select dqxn from xtszb where rownum = 1)  ");
		}
		
		if("pjxq".equalsIgnoreCase(glxq)){
			sql.append(" and xq = (select pjxq from xg_pjpy_xtszb where rownum = 1)  ");
		}else if("dqxq".equalsIgnoreCase(glxq)){
			sql.append(" and xq = (select dqxq from xtszb where rownum = 1)  ");
		}
		
		sql.append(" and ((exists(select 1 from xszz_zzxmb where shjb='һ�����' and xmdm='5002') and shzt1='ͨ��') ");
		sql.append(" or (exists(select 1 from xszz_zzxmb where shjb='�������' and xmdm='5002') and shzt2='ͨ��') ");
		sql.append(" or (exists(select 1 from xszz_zzxmb where shjb='�������' and xmdm='5002') and shzt3='ͨ��') ) ");
		
		
		String[]nozgXh=dao.getArray(sql.toString(), new String[]{xh}, "xh");
		//����˵��
		String tjms = (String)map.get("tjms");

		boolean flag = (nozgXh==null || nozgXh.length==0 ? true : false);
		
		if (flag) {
			
			//�����������Ļ�
			message = "�������Ŀ��Ҫ��������Ϊ������,�����߲���������������";

		}
		return message;
	}

	// -------------------------������������� begin--------------------------	
	
	
	// ----------------------------ѧУ���Ի����� begin -----------------------------
	
	// ==========================���ݴ�ѧ���Ի����� begin===============================
	
	/**
	 * ����ѧ��������������õȼ���ѧ���ƽ��ѧ�ּ���>20%��
	 * @param map
	 * @return
	 * @throws SQLException 
	 */
	public String shxsPjTjByGzdx(HashMap<String,String>map) throws SQLException{
		
		DAO dao=DAO.getInstance();
		
		//��������
		String sqlx=(String)map.get("sqlx");
		//��Ϣ
		String message="";
		//��������ϱ�ѧ��ѧ��
		String xh=map.get("xh");
		
		String condition =(String)map.get("condition");
		
		String[] jxjmc=condition.split("!!@@!!");
		// -------------------���Ի�ҵ�� sql begin-------------------------
		StringBuilder sql=new StringBuilder();
		sql.append(" select xh from xg_pjpy_pjxmsqb a,xg_pjpy_pjxmwhb b  ");
		sql.append(" where (a.sqjg = 'tg' or b.sfsh='no') and a.xmdm=b.xmdm");
		sql.append(" and a.pjxn=b.pjxn and a.pjxq=b.pjxq and a.pjnd=b.pjnd ");
		sql.append(" and xh =? ");
		
		sql.append(" and ( ");
		
		for(int i=0;i<jxjmc.length;i++){
			
			if(i!=0){
				sql.append(" or ");
			}
			sql.append(" xmmc like '"+jxjmc[i]+"' ");
		}
		
		sql.append(" )  ");
		// -------------------���Ի�ҵ�� sql end-------------------------
		
		String pjzgXh=dao.getOneRs(sql.toString(), new String[]{xh}, "xh");
		boolean flag = (Base.isNull(pjzgXh) ? false :true );
		
		if(!flag){
			
			sql=new StringBuilder(" select * from xg_view_gzdx_jqpjfpm where xh=? and jqpjfpmbl <=2");
			pjzgXh=dao.getOneRs(sql.toString(), new String[]{xh}, "xh");
		}
		
		//����˵��
		String tjms = (String)map.get("tjms");
		
		flag = (Base.isNull(pjzgXh) ? false :true );
		if (!flag) {
			
			//�����������Ļ�
			message = "�������Ŀ��Ҫ��" + tjms + ",�����߲���������������";

		}
		return message;
	}
	
	
	/**
	 * �������Ŀ������ͨ��
	 * 
	 * @param xh
	 * @param map
	 * @return
	 * @author CMJ
	 */
	public String judgebjgReverse(HashMap<String,String>map) throws SQLException{
		WdpjPjtjDAO dao = new WdpjPjtjDAO();
		WdpjPjtjService serive=new WdpjPjtjService();

		String message = "";

		// ����˵��
		String tjms = map.get("tjms");
		// �����ʽ
		String tsgs = map.get("tsgs");
		
		// ��ϵ
		String gx = map.get("gx");
		// ����ֵ
		String tjz = map.get("tjz");
		
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// �Ƚ�ֵ
		String xh=map.get("xh");
		String bjz = dao.getBjz(xh, map, "count");
		bjz = Base.isNull(bjz) ? "0" : bjz;

		boolean flag = false;

		flag = serive.compareTo(bjz, tjz, gx, true);
		if(flag){
			//�Ƿ�ͨ������
			flag=dao.isTgbk(xh);
		}

		// �����������Ļ�
		if (!flag) {
			message = "�������Ŀ��Ҫ��" + tjms + ",������Ϊ�������������";
		}

		return message;
	}
	

	// ==========================�㶫ˮ��ˮ����Ի����� begin===============================
	/**
	 * �жϹ㶫ˮ��ˮ�������
	 * @param map
	 * @return
	 * @throws SQLException 
	 */
	public String checkGdslsdJbj(HashMap<String,String>map) throws SQLException{
		
		DAO dao=DAO.getInstance();
		
		//��Ϣ
		String message="";
		//��������ϱ�ѧ��ѧ��
		String xh=map.get("xh");
		
		// -------------------���Ի�ҵ�� sql begin-------------------------
		StringBuilder sql=new StringBuilder();
		sql.append(" select xh,xn,xq,nd,bjdm,pmjbbl,bxkpjf,xxkpjf,pm from ( ");
		sql.append(" select xh,xn,xq,nd,bjdm,pmjbbl,bxkpjf,xxkpjf, ");
		sql.append(" (rank() over(partition by bjdm order by pmjbbl desc, ");
		sql.append(" bxkpjf desc,xxkpjf desc)) pm ");
		sql.append(" from (select a.xh, a.xn, a.xq, a.nd, a.bjdm, a.pmjbbl, b.bxkpjf, c.xxkpjf ");
		sql.append(" from (select a.xh,a.xn,'no' xq,'no' nd,a.bjdm, ");
		sql.append(" round((b.bjpm - a.bjpm) / a.num * 100, 2) pmjbbl from ( ");
		sql.append(" select xh,(select pjxn from xg_pjpy_xtszb where rownum = 1) xn,bjdm,bjpm,num ");
		sql.append(" from (select xh,a.bjdm,b.num,(rank() over(partition by a.bjdm order by avgcj desc)) bjpm ");
		sql.append(" from (select a.xh, a.bjdm, nvl(avgcj, 0) avgcj from (select xh, bjdm  from xg_pjpy_pjrykb a ");
		sql.append(" where exists (select 1  from xg_pjpy_pjrykb b where a.bjdm = b.bjdm)) a ");
		sql.append(" left join (select xh, round(avg(cj), 2) avgcj from view_zhhcjb ");
		sql.append(" where xn =(select pjxn  from xg_pjpy_xtszb) ");
		sql.append(" and xq =(select xqdm  from xqdzb   where xqjb = '1' and rownum = 1) ");
		sql.append(" group by xh, xn) b on a.xh = b.xh) a ");
		sql.append(" left join (select bjdm, count(1) num  from xg_pjpy_pjrykb group by bjdm) b on a.bjdm = b.bjdm)) a ");
		sql.append(" left join (select xh,  (select pjxn  from xg_pjpy_xtszb  where rownum = 1) xn, bjdm, bjpm ");
		sql.append(" from (select xh, a.bjdm,  b.num, (rank() over(partition by a.bjdm order by avgcj desc)) bjpm ");
		sql.append(" from (select a.xh, a.bjdm,nvl(avgcj, 0) avgcj from (select xh, bjdm  from xg_pjpy_pjrykb a ");
		sql.append(" where exists (select 1 from xg_pjpy_pjrykb b  where a.bjdm = b.bjdm)) a ");
		sql.append(" left join (select xh,  round(avg(cj), 2) avgcj  from view_zhhcjb where xn =  (select pjxn from xg_pjpy_xtszb) ");
		sql.append(" and xq = (select xqdm from xqdzb  where xqjb = '2'   and rownum = 1) ");
		sql.append(" group by xh, xn) b on a.xh = b.xh) a left join (select bjdm, count(1) num ");
		sql.append(" from xg_pjpy_pjrykb group by bjdm) b on a.bjdm = ");
		sql.append(" b.bjdm)) b on a.xh = b.xh and a.xn = b.xn where b.bjpm - a.bjpm >= 5) a ");
		sql.append(" left join (select xh, round(avg(cj), 2) bxkpjf from view_zhhcjb where kcxz = '���޿�' ");
		sql.append(" and xn = (select pjxn from xg_pjpy_xtszb) group by xh) b on a.xh = b.xh ");
		sql.append(" left join (select xh, round(avg(cj), 2) xxkpjf from view_zhhcjb where kcxz = '��ѡ��' ");
		sql.append(" and xn = (select pjxn from xg_pjpy_xtszb) ");
		sql.append(" group by xh) c on a.xh = c.xh)) where pm<=2  ");

		sql.append(" and  xh=?  ");
		// -------------------���Ի�ҵ�� sql end-------------------------
		
		String pjzgXh=dao.getOneRs(sql.toString(), new String[]{xh}, "xh");
		boolean flag = (Base.isNull(pjzgXh) ? false :true );
		
		//����˵��
		String tjms = (String)map.get("tjms");
		
		flag = (Base.isNull(pjzgXh) ? false :true );
		if (!flag) {
			
			//�����������Ļ�
			message = "�������Ŀ��Ҫ��" + tjms + ",�����߲���������������";

		}
		return message;
	}
	
	
	// ==========================�㽭�ʵ���Ի����� begin===============================
	/**
	 * 
	 * @����:�㽭�ʵ�ְҵ����ѧԺ �����Ǽ�������ѡ����
	 * @���ߣ��ո־�[���ţ�1075]
	 * @���ڣ�2015-12-31 ����02:31:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param map
	 * @return
	 * @throws SQLException
	 * String �������� 
	 * @throws
	 */
	public String checkYwxjqsCount(HashMap<String,String>map) throws SQLException{
		
		DAO dao=DAO.getInstance();
		WdpjPjtjService serive = new WdpjPjtjService();
		
		//��Ϣ
		String message="";
		//��������ϱ�ѧ��ѧ��
		String xh=map.get("xh");
		//����ѧ��
		String glxn=(String)map.get("xn");
		//����ѧ��
		String glxq=(String)map.get("xq");	
		
		String condition=map.get("condition");
		
		StringBuilder sql=new StringBuilder();
		List<String> params = new ArrayList<String>();
		sql.append(" select nvl(sum(b.cnt),0) cnt ");
		sql.append(" from xg_gygl_new_cwxxb a ");
		sql.append(" left join (select LDDM, QSH, xn, xq, count(1) cnt ");
		sql.append(" from (select LDDM, QSH, DJ, xn, xq, jcyf, tjzt ");
		sql.append(" from VIEW_XG_GYGL_NEW_WSJC_QSFSB_YF where 1 = 1 ");
		
		if(!Base.isNull(condition)){
			sql.append(condition);
		}
		
		if("pjxn".equalsIgnoreCase(glxn)){
			sql.append(" and xn = (select pjxn from xg_pjpy_xtszb where rownum = 1)  ");
		}else if("dqxn".equalsIgnoreCase(glxn)){
			sql.append(" and xn = (select dqxn from xtszb where rownum = 1)  ");
		}
		
		if("pjxq".equalsIgnoreCase(glxq)){
			sql.append(" and xq = (select pjxq from xg_pjpy_xtszb where rownum = 1)  ");
		}else if("dqxq".equalsIgnoreCase(glxq)){
			sql.append(" and xq = (select dqxq from xtszb where rownum = 1)  ");
		}
		sql.append(" and dj = '5��') ");
		sql.append(" group by LDDM, QSH, xn, xq) b ");
		sql.append(" on a.lddm = b.lddm ");
		sql.append(" and a.qsh = b.qsh ");
		sql.append(" where a.xh = ? ");
		params.add(xh);
		
		HashMap<String,String> search = dao.getMapNotOut(sql.toString(), params.toArray(new String[]{}));
		
		//����˵��
		String tjms = (String)map.get("tjms");
		
		// ��ϵ
		String gx = (String)map.get("gx");
		// ����ֵ
		String tjz = (String)map.get("tjz");
		
		// �Ƚ�ֵ
		String bjz = search.get("cnt");
		bjz = Base.isNull(bjz) ? "0" : bjz;

		boolean flag = false;

		flag = serive.compareTo(bjz, tjz, gx, true);

		// �����������Ļ�
		if (!flag) {
			message = "�������Ŀ��Ҫ��" + tjms + gx + tjz + ",������Ϊ�������������";
		}

		return message;
	}

	// ----------------------------ѧУ���Ի����� end   -----------------------------
}
