package xgxt.pjpy.comm.pjpy.tjsz;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.utils.String.StringUtils;

public class PjpyTjszUtils {
	
	/**
	 * ���ĳѧ������ĳ��Ŀʱ���е�����
	 * @param xh
	 * @param xmdm
	 * @return
	 */
	public List<HashMap<String, String>> getAllTj(String xh, String xmdm){
		String sql = "select * from xg_view_pjpy_tjms where xmdm=? and " +
					 "(tjfw=(select xydm from view_xg_pjpy_ryqd where xh=?) or tjfw='all')";
		
		DAO dao = DAO.getInstance();

		String[] output = new String[] { "tjdm", "gx", "tjz", "tjms", "tjlx",
				"tablename", "zd", "condition", "xn", "xq", "nd", "tsgs",
				"bzd", "xmdm" };
		
		return dao.getList(sql, new String[] { xmdm, xh }, output);
	}
	
	/**
	 * �ж�����
	 * @param xh
	 * @param map
	 * @return
	 */
	public String judgeTj(String xh, Map<String, String> map){
		String yx = "";
		String tjdm = map.get("tjdm");
		
		if("cj".equalsIgnoreCase(tjdm)){
			yx = judgeCj(xh,map,null);
		}else if("zpcj".equalsIgnoreCase(tjdm)){//�����ɼ�
			yx = judgeCj(xh,map,"����");
		}else if("tycj".equalsIgnoreCase(tjdm)){
			yx = judgeTyf(xh,map);
		}else if("cql".equalsIgnoreCase(tjdm)){
			yx = judgeCql(xh,map);
		}else if("cxpd".equalsIgnoreCase(tjdm)){
			yx = judgeCxpd(xh,map);
		}else if("avgcj".equalsIgnoreCase(tjdm)){//ƽ���ɼ�
			yx = judgeAvgCj(xh,map);
		}else if("cxf".equalsIgnoreCase(tjdm)){//���з�
			yx = judgeCxf(xh,map);
		}else if("pjjd".equalsIgnoreCase(tjdm)){
			yx = judgePjjd(xh,map);
		}
		
		return yx;
	}
	
	/**
	 * �ɼ������ж�
	 * @param xh
	 * @param map
	 * @return
	 */
	public String judgeCj(String xh, Map<String, String> map,String lx){
		String message = "";
		String sqzq = map.get("sqzq");
		
		DAO dao = DAO.getInstance();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select min(cj) cj from xg_view_cjxx_dkcj where xh=? ");
		sql.append(Base.isNull(lx) ? "" : " and lx = '" + lx + "' ");
		List<String> input = new ArrayList<String>();
		
		input.add(xh);
		
		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		
		// �������ڲ�ͬѧ�꣬ѧ�ڣ����
		if("xn".equalsIgnoreCase(sqzq)){
			sql.append("and xn=? ");
			
			input.add(pjxn);
		}else if("xq".equalsIgnoreCase(sqzq)){
			sql.append("and xn=? and xq=? ");
			
			input.add(pjxn);
			input.add(pjxq);
		}else {
			sql.append("and nd=?");
			
			input.add(pjnd);
		}
		
		Map<String, String> cjMap = dao.getMapNotOut(sql.toString(), (String[])input.toArray(new String[]{}));
		String mbz = cjMap.get("cj");
		mbz = StringUtils.isNull(mbz) ? "0" : mbz;
		
		String tjz = map.get("tjz");
		String gx = map.get("gx");
		String tjms = map.get("tjms");
		
		boolean flag = compareTo(mbz, tjz, gx);
		
		if (!flag) {
			if(Base.isNull(lx)){
				message = "�������Ŀ��Ҫ��" + tjms + ",��ͷ���" + mbz + ",����������������";
			}else{
				message = "�������Ŀ��Ҫ��" + tjms + ",�����ߴ���ĳ�ſγ�" +lx+"Ϊ"+ mbz + "��,����������������";
			}
		}
		return message;
	}
	
	/**
	 * Ŀ��ֵ��ȷ��ֵ�Ƚ�
	 * @param mbz
	 * @param tjz
	 * @param gx
	 * @return
	 */
	public boolean compareTo(String mbz,String tjz,String gx){
		boolean flag = false;
		if(StringUtils.isNotNull(mbz) && StringUtils.isNotNull(tjz)){
			if("=".equalsIgnoreCase(gx)){
				flag = mbz.equalsIgnoreCase(tjz) ? true : false;
			}else if(">".equalsIgnoreCase(gx)){
				if(true){ // �Ƿ������ж�
					BigDecimal bmbz = new BigDecimal(mbz);
					BigDecimal btjz = new BigDecimal(tjz);
					
					flag = bmbz.compareTo(btjz)>0 ? true : false;
				}
			}else if("<".equalsIgnoreCase(gx)){
				if(true){
					BigDecimal bmbz = new BigDecimal(mbz);
					BigDecimal btjz = new BigDecimal(tjz);
					
					flag = bmbz.compareTo(btjz)<0 ? true : false;
				}
			}else if(">=".equalsIgnoreCase(gx)){
				if(true){
					BigDecimal bmbz = new BigDecimal(mbz);
					BigDecimal btjz = new BigDecimal(tjz);
					
					flag = bmbz.compareTo(btjz)>0 || bmbz.compareTo(btjz) ==0 ? true : false;
				}
			}else if("<=".equalsIgnoreCase(gx)){
				if(true){
					BigDecimal bmbz = new BigDecimal(mbz);
					BigDecimal btjz = new BigDecimal(tjz);
					
					flag = bmbz.compareTo(btjz)<0 || bmbz.compareTo(btjz) ==0 ? true : false;
				}
			}
		}
		
		return flag;
	}
	
	
	/**
	 * �жϲ�������
	 * @param xh
	 * @param map
	 * @return
	 */
	public String judgeCxpd(String xh, Map<String, String> map){
		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;

		DAO dao = DAO.getInstance();

		// ������Ϣ
		String message = null;
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		// ����˵��
		String tjms = map.get("tjms");
		// ��ϵ
		String gx = map.get("gx");
		// ����ֵ
		String tjz = map.get("tjz");

		StringBuilder sql = new StringBuilder();
		sql.append(" select cxf from xg_pjpy_cxfpdb ");
		sql.append(" where 1 = 1 ");
		sql.append(" and xh = '" + xh + "' ");
		sql.append(" and pjxn = '" + pjxn + "' ");
		sql.append(" and pjxq = '" + pjxq + "' ");
		sql.append(" and pjnd = '" + pjnd + "' ");
		
		// ������
		String cxf = dao.getOneRs(sql.toString(), new String[] {}, "cxf");
		
		boolean flag = compareTo(cxf, tjz, gx);
		
		if (!flag) {
			message = "�������Ŀ��Ҫ��" + tjms + ",��������������";
		}

		return message;
	}
	
	//==========================����ΰ�����==============================================
	/**
	 * ���������������������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public String judgeTyf(String xh, Map<String, String> jxjMap){

		//TODO
		//�����ֳɼ�ȡ����ͼxg_view_cjxx_tyf�����ֿγ����������֣������������Ա�޸ĸ���ͼ����
		
		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;

		DAO dao = DAO.getInstance();

		// ������Ϣ
		String message = null;
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		// ��������
		String sqzq = jxjMap.get("sqzq");
		// ��Ŀ����
		//String xmmc = jxjMap.get("xmmc");
		// ����˵��
		String tjms = jxjMap.get("tjms");
		// ��ϵ
		String gx = jxjMap.get("gx");
		// ����ֵ
		String tjz = jxjMap.get("tjz");

		StringBuilder sql = new StringBuilder();
		sql.append(" select avg(tycj) tycj from (");
		sql.append(" select tycj from xg_view_cjxx_tyf ");
		sql.append(" where 1 = 1 ");
		sql.append(" and xh = '" + xh + "' ");

		if ("xn".equalsIgnoreCase(sqzq)) {// �������ڣ�ѧ��
			sql.append(" and xn = '" + pjxn + "' ");
		} else if ("xq".equalsIgnoreCase(sqzq)) {// �������ڣ�ѧ��
			sql.append(" and xn = '" + pjxn + "' ");
			sql.append(" and xq = '" + pjxq + "' ");
		} else {// �������ڣ����
			sql.append(" and nd = '" + pjnd + "' ");
		}
		sql.append(" ) ");

		// �����ɼ�
		String tycj = dao.getOneRs(sql.toString(), new String[] {}, "tycj");
		tycj = StringUtils.isNull(tycj) ? "0" : tycj;
		
		boolean flag = compareTo(tycj, tjz, gx);

		if (!flag) {
			message = "�������Ŀ��Ҫ��" + tjms + ",�����߷���Ϊ" + tycj + ",��������������";
		}

		return message;
	}

	/**
	 * ����������ų���������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public String judgeCql(String xh, Map<String, String> jxjMap){

		//TODO
		//������ȡ�Ա�xg_pjpy_cqlb��cql�ֶα��������֣������95%����ά��95
		DAO dao = DAO.getInstance();

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;
		// ������Ϣ
		String message = null;
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		// ����˵��
		String tjms = jxjMap.get("tjms");
		// ��ϵ
		String gx = jxjMap.get("gx");
		// ����ֵ
		String tjz = jxjMap.get("tjz");

		StringBuilder sql = new StringBuilder();
		sql.append(" select cql from xg_pjpy_cqlb ");
		sql.append(" where 1 = 1 ");
		sql.append(" and xh = '" + xh + "' ");
		sql.append(" and pjxn = '" + pjxn + "' ");
		sql.append(" and pjxq = '" + pjxq + "' ");
		sql.append(" and pjnd = '" + pjnd + "' ");

		// ������
		String cql = dao.getOneRs(sql.toString(), new String[] {}, "cql");
		cql = StringUtils.isNull(cql) ? "0" : cql;
		
		boolean flag = compareTo(cql, tjz, gx);

		if (!flag) {
			message = "�������Ŀ��Ҫ��" + tjms + ",�����߳�����Ϊ" + cql + "%,����������������";
		}

		return message;
	}
	
	/**
	 * ����������Ų��з�����
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public String judgeCxf(String xh, Map<String, String> jxjMap){

		DAO dao = DAO.getInstance();

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;
		// ������Ϣ
		String message = null;
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		// ����˵��
		String tjms = jxjMap.get("tjms");
		// ��ϵ
		String gx = jxjMap.get("gx");
		// ����ֵ
		String tjz = jxjMap.get("tjz");

		StringBuilder sql = new StringBuilder();
		sql.append(" select jjf,fz from zjjt_cxflrb ");
		sql.append(" where 1 = 1 ");
		sql.append(" and pjxh = '" + xh + "' ");
		sql.append(" and xn = '" + pjxn + "' ");
		sql.append(" and xq = '" + pjxq + "' ");
		//sql.append(" and shjg = 'ͨ��' ");
		
		// ���з�
		int cxf = 0;
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "jjf", "fz" });

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);
				// �Ӽ���
				String jjf = map.get("jjf");
				// ��ֵ
				String fz = map.get("fz");

				if ("�ӷ�".equalsIgnoreCase(jjf)) {
					cxf += Integer.parseInt(fz);
				} else if ("����".equalsIgnoreCase(jjf)) {
					cxf -= Integer.parseInt(fz);
				}
			}
		}
		
		boolean flag = compareTo(String.valueOf(cxf), tjz, gx);

		if (!flag) {
			message = "�������Ŀ��Ҫ��" + tjms + ",�����߲��з�Ϊ" + cxf + ",����������������";
		}

		return message;
	}
	
	/**
	 * �ж���������ƽ���ֳɼ�����
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public String judgeAvgCj(String xh, Map<String, String> jxjMap){

		DAO dao = DAO.getInstance();

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;
		// ������Ϣ
		String message = null;
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		// ����˵��
		String tjms = jxjMap.get("tjms");
		// ��������
		String sqzq = jxjMap.get("sqzq");
		// ��ϵ
		String gx = jxjMap.get("gx");
		// ����ֵ
		String tjz = jxjMap.get("tjz");

		StringBuilder sql = new StringBuilder();
		sql.append(" select round(avg(cj),3) pjf from xg_view_cjxx_pjf ");
		sql.append(" where 1 = 1 ");
		sql.append(" and xh = '" + xh + "' ");
		
		if ("xn".equalsIgnoreCase(sqzq)) {// �������ڣ�ѧ��
			sql.append(" and xn = '" + pjxn + "' ");
		} else if ("xq".equalsIgnoreCase(sqzq)) {// �������ڣ�ѧ��
			sql.append(" and xn = '" + pjxn + "' ");
			sql.append(" and xq = '" + pjxq + "' ");
		} else {// �������ڣ����
			sql.append(" and nd = '" + pjnd + "' ");
		}
		
		// ƽ����
		String pjf = dao.getOneRs(sql.toString(), new String[] {}, "pjf");
		pjf = StringUtils.isNull(pjf) ? "0" : pjf;
		
		boolean flag = compareTo(pjf, tjz, gx);

		if (!flag) {
			message = "�������Ŀ��Ҫ��" + tjms + ",������ƽ����Ϊ" + pjf + ",����������������";
		}

		return message;
	}
	
	/**
	 * �ж���������ƽ���ֳɼ�����
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public String judgePjjd(String xh, Map<String, String> jxjMap){

		DAO dao = DAO.getInstance();

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;
		// ������Ϣ
		String message = null;
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����˵��
		String tjms = jxjMap.get("tjms");
		// ��ϵ
		String gx = jxjMap.get("gx");
		// ����ֵ
		String tjz = jxjMap.get("tjz");

		StringBuilder sql = new StringBuilder();
		sql.append(" select sum(jd)/sum(xf)*100 pjjd from xg_view_cjxx_pjjd ");
		sql.append(" where 1 = 1 ");
		sql.append(" and xh = '" + xh + "' ");
		sql.append(" and xn = '" + pjxn + "' ");
		
		// ƽ����
		String pjjd = dao.getOneRs(sql.toString(), new String[] {}, "pjjd");
		pjjd = StringUtils.isNull(pjjd) ? "0" : pjjd;
		
		boolean flag = compareTo(pjjd, tjz, gx);

		if (!flag) {
			message = "�������Ŀ��Ҫ��" + tjms + ",������ƽ������Ϊ" + pjjd + ",����������������";
		}

		return message;
	}
	
	/**
	 * ���ѧ�������ʸ�
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	public String getStuPjzg(String xh, Map<String, String> map){
		
		// ��������
		String tjlx = map.get("tjlx");

		// ��ʾ��Ϣ
		String message = "";

		if ("logicRelation".equalsIgnoreCase(tjlx)) {// �߼���ϵ
			message = judgeLogicRelation(xh, map);
		} else if ("logicReverse".equalsIgnoreCase(tjlx)) {// �����߼���ϵ
			message = judgeLogicReverse(xh, map);
		} else if ("minRelation".equalsIgnoreCase(tjlx)) {// ��С�ֹ�ϵ
			message = judgeMinRelation(xh, map);
		} else if ("avgRelation".equalsIgnoreCase(tjlx)) {// ƽ���ֹ�ϵ
			message = judgeAvgRelation(xh, map);
		}else if ("instanceReverse".equalsIgnoreCase(tjlx)) {// ĳ�����ϵ
			message = judgeInstanceReverse(xh, map);
		}else {//���÷�����������
			message =specialtiesOperation(xh,map);
		}
		
		return message;
	}
	
	/**
	 * �ж��߼���ϵ
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String judgeLogicRelation(String xh, Map<String, String> map) {	
		
		String message = "";

		// ����˵��
		String tjms = map.get("tjms");
		// ��ϵ
		String gx = map.get("gx");
		// ����ֵ
		String tjz = map.get("tjz");
		// ������Դ��
		String tablename = map.get("tablename");
		// �ֶ�
		String zd = map.get("zd");
		// �����ʽ
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;
		
		// �Ƚ�ֵ
		String bjz = getBjz(xh, map, "");
		bjz = Base.isNull(bjz) ? "0" : bjz;
		
		try {
			
			boolean flag = compareTo(bjz, tjz, gx, true);
			
			//�����������Ļ�
			if (!flag) {
				message = "�������Ŀ��Ҫ��" + tjms + ",������Ϊ" + bjz + tsgs
						+ ",����������������";
			}
		} catch (Exception e) {
			System.out.println("��" + tablename + "���д��ڷ����֣�" + zd + "����¼������");
		}
		
		return message;
	}
	
	/**
	 * �жϷ����߼���ϵ
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String judgeLogicReverse(String xh, Map<String, String> map) {	
		
		String message = "";

		// ����˵��
		String tjms = map.get("tjms");
		// ��ϵ
		String gx = map.get("gx");
		// ����ֵ
		String tjz = map.get("tjz");
		// ������Դ��
		String tablename = map.get("tablename");
		// �ֶ�
		String zd = map.get("zd");
		// �����ʽ
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;
		
		// �Ƚ�ֵ
		String bjz = getBjz(xh, map, "");
		bjz = Base.isNull(bjz) ? "0" : bjz;
		
		try {
			
			boolean flag = compareTo(bjz, tjz, gx, false);
			
			// �����������Ļ�
			if (!flag) {
				message = "�������Ŀ��Ҫ��" + tjms + ",������Ϊ" + bjz + tsgs
						+ ",����������������";
			}
		} catch (Exception e) {
			System.out.println("��" + tablename + "���д��ڷ����֣�" + zd + "����¼������");
		}
		
		return message;
	}
	
	/**
	 * �ж��߼���ϵ
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String judgeMinRelation(String xh, Map<String, String> map) {	
		
		String message = "";

		// ����˵��
		String tjms = map.get("tjms");
		// ��ϵ
		String gx = map.get("gx");
		// ����ֵ
		String tjz = map.get("tjz");
		// ������Դ��
		String tablename = map.get("tablename");
		// �ֶ�
		String zd = map.get("zd");
		// �����ʽ
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;
		
		// �Ƚ�ֵ
		String bjz = getBjz(xh, map, "Min");
		bjz = Base.isNull(bjz) ? "0" : bjz;
		
		try {
			
			boolean flag = compareTo(bjz, tjz, gx, true);
			
			//�����������Ļ�
			if (!flag) {
				message = "�������Ŀ��Ҫ��" + tjms + ",������Ϊ" + bjz + tsgs
						+ ",����������������";
			}
		} catch (Exception e) {
			System.out.println("��" + tablename + "���д��ڷ����֣�" + zd + "����¼������");
		}
		
		return message;
	}
	
	/**
	 * �ж��߼���ϵ
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String judgeAvgRelation(String xh, Map<String, String> map) {	
		
		String message = "";

		// ����˵��
		String tjms = map.get("tjms");
		// ��ϵ
		String gx = map.get("gx");
		// ����ֵ
		String tjz = map.get("tjz");
		// ������Դ��
		String tablename = map.get("tablename");
		// �ֶ�
		String zd = map.get("zd");
		// �����ʽ
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;
		
		// �Ƚ�ֵ
		String bjz = getBjz(xh, map, "AVG");
		bjz = Base.isNull(bjz) ? "0" : bjz;
		bjz = String.valueOf(Math.round(Double.parseDouble(bjz)));
		
		try {
			
			boolean flag = compareTo(bjz, tjz, gx, true);
			
			//�����������Ļ�
			if (!flag) {
				message = "�������Ŀ��Ҫ��" + tjms + ",������Ϊ" + bjz + tsgs
						+ ",����������������";
			}
		} catch (Exception e) {
			System.out.println("��" + tablename + "���д��ڷ����֣�" + zd + "����¼������");
		}
		
		return message;
	}
	
	/**
	 * �ж��߼���ϵ
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String judgeInstanceReverse(String xh, Map<String, String> map) {

		String message = "";

		// ����˵��
		String tjms = map.get("tjms");
		// ��ϵ
		String gx = map.get("gx");
		// ����ֵ
		String tjz = map.get("tjz");
		// ������Դ��
		String tablename = map.get("tablename");
		// �ֶ�
		String zd = map.get("zd");
		// �����ʽ
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// �Ƚ�ֵ
		String bjz = getBjz(xh, map, "count");
		bjz = Base.isNull(bjz) ? "0" : bjz;

		boolean flag = false;

		//if ("��".equalsIgnoreCase(tjz)) {// ���ڸ�����
			if (Integer.parseInt(bjz) > 0) {
				flag = true;
			}
		//}

		// �����������Ļ�
		if (!flag) {
			message = "�������Ŀ��Ҫ��" + tjms + ",������Ϊ�������������";
		}

		return message;
	}
	
	/**
	 * Ŀ��ֵ��ȷ��ֵ�Ƚ�
	 * 
	 * @param bjz
	 * @param tjz
	 * @param gx
	 * @return
	 */
	private boolean compareTo(String bjz, String tjz, String gx,
			boolean relation) {

		boolean flag = false;

		// �Ƚ�ֵ������ֵ�ǿ�
		if (StringUtils.isNotNull(bjz) && StringUtils.isNotNull(tjz)) {
			// ��ϵΪ"="
			if ("=".equalsIgnoreCase(gx)) {
				flag = bjz.equalsIgnoreCase(tjz) ? true : false;
			}
			// ��ϵΪ">"
			else if (">".equalsIgnoreCase(gx)) {

				BigDecimal bbjz = new BigDecimal(bjz);
				BigDecimal btjz = new BigDecimal(tjz);

				if (relation) {
					flag = bbjz.compareTo(btjz) > 0 ? true : false;
				} else {
					flag = btjz.compareTo(bbjz) > 0 ? true : false;
				}
			}
			// ��ϵΪ">="
			else if (">=".equalsIgnoreCase(gx)) {

				BigDecimal bbjz = new BigDecimal(bjz);
				BigDecimal btjz = new BigDecimal(tjz);

				if (relation) {
					flag = bbjz.compareTo(btjz) > 0
							|| bbjz.compareTo(btjz) == 0 ? true : false;
				} else {
					flag = btjz.compareTo(bbjz) > 0
							|| btjz.compareTo(bbjz) == 0 ? true : false;
				}
			}
			// ��ϵΪ"<"
			else if ("<".equalsIgnoreCase(gx)) {

				BigDecimal bbjz = new BigDecimal(bjz);
				BigDecimal btjz = new BigDecimal(tjz);

				if (relation) {
					flag = bbjz.compareTo(btjz) < 0 ? true : false;
				} else {
					flag = btjz.compareTo(bbjz) < 0 ? true : false;
				}

			}
			// ��ϵΪ"<="
			else if ("<=".equalsIgnoreCase(gx)) {

				BigDecimal bbjz = new BigDecimal(bjz);
				BigDecimal btjz = new BigDecimal(tjz);

				if (relation) {
					flag = bbjz.compareTo(btjz) < 0
							|| bbjz.compareTo(btjz) == 0 ? true : false;
				} else {
					flag = btjz.compareTo(bbjz) < 0
							|| btjz.compareTo(bbjz) == 0 ? true : false;
				}

			}
		}

		return flag;
	}
	
	/**
	 * ����ҵ���ж�(��������) 
	 * ps ������������sqlx��������
	 * @param xh
	 * @param map
	 * @return
	 */
	public String specialtiesOperation(String xh,Map<String,String> map){
		
		//��������
		String tjlx = map.get("tjlx");
		String message="";
		
		try {
			HashMap<String,Object>tjInfo=new HashMap<String,Object>();
			
			PjpyTjszMethod pjpyTjszMethod=new PjpyTjszMethod();
			Class myClass = pjpyTjszMethod.getClass();
			
			tjInfo.putAll(map);
			tjInfo.put("sqlx", "sq");
			tjInfo.put("xh", new String[]{xh});
			
			message = (String) myClass
					.getMethod(tjlx,(Class[]) new Class[]{HashMap.class}).invoke(pjpyTjszMethod,
							tjInfo);
		} catch (Exception e) {
			System.out.println("������������,method:"+tjlx+"��������;");
			e.printStackTrace();
		}
		
		return message;
	}
	
	/**
	 * ����ҵ���ж�(��������) 
	 * ps ������������sqlx��������
	 * @param xh
	 * @param map
	 * @return
	 */
	public String specialtiesOperation(String[]xh,Map<String,String> map){
		
		//��������
		String tjlx = map.get("tjlx");
		String message="";
		
		try {
			HashMap<String,Object>tjInfo=new HashMap<String,Object>();
			
			PjpyTjszMethod pjpyTjszMethod=new PjpyTjszMethod();
			Class myClass = pjpyTjszMethod.getClass();
			
			tjInfo.putAll(map);
			tjInfo.put("sqlx", "sb");
			tjInfo.put("xh", xh);
			
			message = (String) myClass
					.getMethod(tjlx,(Class[]) new Class[]{HashMap.class}).invoke(pjpyTjszMethod,
							tjInfo);
		} catch (Exception e) {
			System.out.println("������������,method:"+tjlx+"��������;");
			e.printStackTrace();
		}
		
		return message;
	}
	
	/**
	 * ��ñȽ�ֵ
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String getBjz(String xh, Map<String, String> map, String lx) {

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;

		// ��Ŀ����
		String xmdm = map.get("xmdm");
		// ��������
		String tjdm = map.get("tjdm");
		// ����˵��
		String tjms = map.get("tjms");
		// ��ϵ
		String gx = map.get("gx");
		// ����ֵ
		String tjz = map.get("tjz");
		// ������Դ��
		String tablename = map.get("tablename");
		// �ֶ�
		String zd = map.get("zd");
		// ��������
		String condition = map.get("condition");
		// �����ʽ
		String tsgs = map.get("tsgs");
		// ѧ������
		String xn = map.get("xn");
		// ѧ������
		String xq = map.get("xq");
		// �������
		String nd = map.get("nd");
		// ���ֶ�
		String bzd = map.get("bzd");
		bzd = Base.isNull(bzd) ? "" : bzd;

		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();

		// ��ǰѧ��
		String dqxn = Base.currXn;
		// ��ǰѧ��
		String dqxq = Base.currXq;
		// ��ǰ���
		String dqnd = Base.currNd;

		// ��������ֵ
		ArrayList<String> inputV = new ArrayList<String>();
		inputV.add(xh);

		if ("cxpd��".equalsIgnoreCase(tjdm)) {
			inputV.add(xmdm);
		}

		StringBuilder sql = new StringBuilder();

		sql.append(" select bjz from (");
		sql.append(" select ");

		// ���ͷǿ�
		if (!Base.isNull(lx)) {
			sql.append(lx);
			sql.append("(");
			sql.append(zd);
			sql.append(")");
		} else {
			sql.append(zd);
		}
		sql.append(" bjz from ");
		sql.append(tablename);
		sql.append(" a ");
		sql.append(" where 1 = 1 ");
		sql.append(" and xh = ? ");
		// ��������
		sql.append(Base.isNull(condition) ? "" : condition);

		// ��Ҫ����ѧ��
		if ("pjxn".equalsIgnoreCase(xn)) {
			sql.append(" and " + bzd + "xn = ? ");
			inputV.add(pjxn);
		} else if ("dqxn".equalsIgnoreCase(xn)) {
			sql.append(" and " + bzd + "xn = ? ");
			inputV.add(dqxn);
		}

		// ��Ҫ����ѧ��
		if ("pjxq".equalsIgnoreCase(xq)) {
			sql.append(" and " + bzd + "xq = ? ");
			inputV.add(pjxq);
		} else if ("dqxq".equalsIgnoreCase(xq)) {
			sql.append(" and " + bzd + "xq = ? ");
			inputV.add(dqxq);
		}

		// ��Ҫ�������
		if ("pjnd".equalsIgnoreCase(nd)) {
			sql.append(" and " + bzd + "nd = ? ");
			inputV.add(pjnd);
		} else if ("dqnd".equalsIgnoreCase(nd)) {
			sql.append(" and " + bzd + "nd = ? ");
			inputV.add(dqnd);
		}

		sql.append(" ) where rownum = 1 ");

		DAO dao = DAO.getInstance();
		// �Ƚ�ֵ
		System.out.println(sql);
		String bjz = dao.getOneRs(sql.toString(), inputV
				.toArray(new String[] {}), "bjz");

		return bjz;
	}
	
	//=======================��Ŀ�ϱ���������===============================
	/**
	 * ������ϱ�������
	 * @param bjdm
	 * @param xmdm
	 * @return
	 */
	public List<HashMap<String, String>> getXmTj(String bjdm, String xmdm){
		String sql = "select * from xg_view_pjpy_tjms where xmdm=? and " +
					 "(tjfw=(select distinct xydm from view_xg_pjpy_ryqd where pjbjdm=?) or tjfw='all')";
		
		DAO dao = DAO.getInstance();

		String[] output = new String[] { "tjdm", "gx", "tjz", "tjms", "tjlx",
				"tablename", "zd", "condition", "xn", "xq", "nd", "tsgs",
				"bzd", "xmdm" };
		
		return dao.getList(sql, new String[] { xmdm, bjdm }, output);
	}
	
	/**
	 * ����������ʸ��ѧ��
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	public String getNoPjzgXh(String[] xh, Map<String, String> map) {

		// ��������
		String tjlx = map.get("tjlx");
		// ����˵��
		String tjms = map.get("tjms");

		// ���ʸ�����ѧ��
		String noPjzgXh = "";

		if ("logicRelation".equalsIgnoreCase(tjlx)) {// �߼���ϵ
			noPjzgXh = judgeLogicRelation(xh, map);
		} else if ("logicReverse".equalsIgnoreCase(tjlx)) {// �����߼���ϵ
			noPjzgXh = judgeLogicReverse(xh, map);
		} else if ("minRelation".equalsIgnoreCase(tjlx)) {// ��С�ֹ�ϵ
			noPjzgXh = judgeMinRelation(xh, map);
		} else if ("avgRelation".equalsIgnoreCase(tjlx)) {// ƽ���ֹ�ϵ
			noPjzgXh = judgeAvgRelation(xh, map);
		} else if ("instanceReverse".equalsIgnoreCase(tjlx)) {// ĳ�����ϵ
			noPjzgXh = judgeInstanceReverse(xh, map);
		}else {//����ҵ��
			noPjzgXh = specialtiesOperation(xh, map);
		}

		// �����������Ļ�
		if (!Base.isNull(noPjzgXh)) {
			DAO dao = DAO.getInstance();
			String xm = dao.getOneValue("view_xsjbxx", "xm", "xh", noPjzgXh);
			noPjzgXh = "�������Ŀ��Ҫ��" + tjms + "," + xm + "(" + noPjzgXh
					+ ")����������������";
		}

		return noPjzgXh;
	}
	
	/**
	 * �ж��߼���ϵ
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String judgeLogicRelation(String[] xh, Map<String, String> map) {

		// ����˵��
		String tjms = map.get("tjms");
		// ��ϵ
		String gx = map.get("gx");
		// ����ֵ
		String tjz = map.get("tjz");
		// ������Դ��
		String tablename = map.get("tablename");
		// �ֶ�
		String zd = map.get("zd");
		// �����ʽ
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// �Ƚ�ֵ
		List<HashMap<String, String>> bjzList = getBjz(xh, map, "");

		String noPjzgXh = compareTo(xh, bjzList, tjz, gx, true);
		
		return noPjzgXh;
	}
	
	/**
	 * �жϷ����߼���ϵ
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String judgeLogicReverse(String[] xh, Map<String, String> map) {

		// ����˵��
		String tjms = map.get("tjms");
		// ��ϵ
		String gx = map.get("gx");
		// ����ֵ
		String tjz = map.get("tjz");
		// ������Դ��
		String tablename = map.get("tablename");
		// �ֶ�
		String zd = map.get("zd");
		// �����ʽ
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// �Ƚ�ֵ
		List<HashMap<String, String>> bjzList = getBjz(xh, map, "");

		String noPjzgXh = compareTo(xh, bjzList, tjz, gx, false);
		
		return noPjzgXh;

	}
	
	/**
	 * �ж�Сֵ��ϵ
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String judgeMinRelation(String[] xh, Map<String, String> map) {	

		// ����˵��
		String tjms = map.get("tjms");
		// ��ϵ
		String gx = map.get("gx");
		// ����ֵ
		String tjz = map.get("tjz");
		// ������Դ��
		String tablename = map.get("tablename");
		// �ֶ�
		String zd = map.get("zd");
		// �����ʽ
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;
		
		// �Ƚ�ֵ
		List<HashMap<String, String>> bjzList = getBjz(xh, map, "Min");

		String noPjzgXh  = compareTo(xh, bjzList, tjz, gx, true);
		
		return noPjzgXh;
	}
	
	/**
	 * �ж�ƽ��ֵ��ϵ
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String judgeAvgRelation(String[] xh, Map<String, String> map) {

		String message = "";

		// ����˵��
		String tjms = map.get("tjms");
		// ��ϵ
		String gx = map.get("gx");
		// ����ֵ
		String tjz = map.get("tjz");
		// ������Դ��
		String tablename = map.get("tablename");
		// �ֶ�
		String zd = map.get("zd");
		// �����ʽ
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// �Ƚ�ֵ
		List<HashMap<String, String>> bjzList = getBjz(xh, map, "AVG");

		String noPjzgXh = compareTo(xh, bjzList, tjz, gx, true);

		return noPjzgXh;
	}
	
	/**
	 * �ж����ϵ
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String judgeInstanceReverse(String[] xh, Map<String, String> map) {

		String message = "";

		// ����˵��
		String tjms = map.get("tjms");
		// ��ϵ
		String gx = map.get("gx");
		// ����ֵ
		String tjz = map.get("tjz");
		// ������Դ��
		String tablename = map.get("tablename");
		// �ֶ�
		String zd = map.get("zd");
		// �����ʽ
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// �Ƚ�ֵ
		List<HashMap<String, String>> bjzList = getBjz(xh, map, "count");

		String noPjzgXh  = compareTo(xh, bjzList, tjz, gx, true);
		
		return noPjzgXh;
	}
	
	/**
	 * ��ñȽ�ֵ
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private List<HashMap<String, String>> getBjz(String xh[],
			Map<String, String> map, String lx) {

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;

		// ��Ŀ����
		String xmdm = map.get("xmdm");
		// ��������
		String tjdm = map.get("tjdm");
		// ����˵��
		String tjms = map.get("tjms");
		// ��ϵ
		String gx = map.get("gx");
		// ����ֵ
		String tjz = map.get("tjz");
		// ������Դ��
		String tablename = map.get("tablename");
		// �ֶ�
		String zd = map.get("zd");
		// ��������
		String condition = map.get("condition");
		// �����ʽ
		String tsgs = map.get("tsgs");
		// ѧ������
		String xn = map.get("xn");
		// ѧ������
		String xq = map.get("xq");
		// �������
		String nd = map.get("nd");
		// ���ֶ�
		String bzd = map.get("bzd");
		bzd = Base.isNull(bzd) ? "" : bzd;

		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();

		// ��ǰѧ��
		String dqxn = Base.currXn;
		// ��ǰѧ��
		String dqxq = Base.currXq;
		// ��ǰ���
		String dqnd = Base.currNd;

		// ��������ֵ
		ArrayList<String> inputV = new ArrayList<String>();

		if ("cxpd��".equalsIgnoreCase(tjdm)) {
			inputV.add(xmdm);
		}

		StringBuilder sql = new StringBuilder();

		for (int i = 0; i < xh.length; i++) {
			if (i != 0) {
				sql.append(" union all ");
			}
			sql.append(" select a.xh,nvl(b.bjz,0) bjz from ");	
			sql.append(" (select '" + xh[i] + "' xh from dual) a left join ");
			sql.append(" (");
			sql.append(" select ");

			// ���ͷǿ�
			if (!Base.isNull(lx)) {
				sql.append(lx);
				sql.append("(");
				sql.append(zd);
				sql.append(")");
			} else {
				sql.append(zd);
			}
			sql.append(" bjz from ");
			sql.append(tablename);
			sql.append(" a ");
			sql.append(" where 1 = 1 ");
			sql.append(" and xh = '" + xh[i] + "' ");

			// ��������
			sql.append(Base.isNull(condition) ? "" : condition);

			// ��Ҫ����ѧ��
			if ("pjxn".equalsIgnoreCase(xn)) {
				sql.append(" and " + bzd + "xn = ? ");
				inputV.add(pjxn);
			} else if ("dqxn".equalsIgnoreCase(xn)) {
				sql.append(" and " + bzd + "xn = ? ");
				inputV.add(dqxn);
			}

			// ��Ҫ����ѧ��
			if ("pjxq".equalsIgnoreCase(xq)) {
				sql.append(" and " + bzd + "xq = ? ");
				inputV.add(pjxq);
			} else if ("dqxq".equalsIgnoreCase(xq)) {
				sql.append(" and " + bzd + "xq = ? ");
				inputV.add(dqxq);
			}

			// ��Ҫ�������
			if ("pjnd".equalsIgnoreCase(nd)) {
				sql.append(" and " + bzd + "nd = ? ");
				inputV.add(pjnd);
			} else if ("dqnd".equalsIgnoreCase(nd)) {
				sql.append(" and " + bzd + "nd = ? ");
				inputV.add(dqnd);
			}

			sql.append(" ) b on 1=1");
		}

		DAO dao = DAO.getInstance();

		// �Ƚ�ֵ
		List<HashMap<String, String>> list = dao.getList(sql.toString(), inputV
				.toArray(new String[] {}), new String[] { "xh", "bjz" });

		return list;
	}
	
	/**
	 * Ŀ��ֵ��ȷ��ֵ�Ƚ�
	 * 
	 * @param bjz
	 * @param tjz
	 * @param gx
	 * @return
	 */
	private String compareTo(String[] xh,
			List<HashMap<String, String>> bjzList, String tjz, String gx,
			boolean relation) {

		boolean flag = false;

		// ���ʸ�ѧ��
		String noPjzgXh = "";

		for (int i = 0; i < bjzList.size(); i++) {

			HashMap<String, String> bjzMap = bjzList.get(i);
			String bjz = bjzMap.get("bjz");

			// �Ƚ�ֵ������ֵ�ǿ�
			if (StringUtils.isNotNull(bjz) && StringUtils.isNotNull(tjz)) {
				// ��ϵΪ"="
				if ("=".equalsIgnoreCase(gx)) {
					if("��".equalsIgnoreCase(tjz) && !"0".equalsIgnoreCase(bjz)){
						bjz = "��";
					}
					flag = bjz.equalsIgnoreCase(tjz) ? true : false;
				}
				// ��ϵΪ">"
				else if (">".equalsIgnoreCase(gx)) {

					BigDecimal bbjz = new BigDecimal(bjz);
					BigDecimal btjz = new BigDecimal(tjz);

					if (relation) {
						flag = bbjz.compareTo(btjz) > 0 ? true : false;
					} else {
						flag = btjz.compareTo(bbjz) > 0 ? true : false;
					}
				}
				// ��ϵΪ">="
				else if (">=".equalsIgnoreCase(gx)) {

					BigDecimal bbjz = new BigDecimal(bjz);
					BigDecimal btjz = new BigDecimal(tjz);

					if (relation) {
						flag = bbjz.compareTo(btjz) > 0
								|| bbjz.compareTo(btjz) == 0 ? true : false;
					} else {
						flag = btjz.compareTo(bbjz) > 0
								|| btjz.compareTo(bbjz) == 0 ? true : false;
					}
				}
				// ��ϵΪ"<"
				else if ("<".equalsIgnoreCase(gx)) {

					BigDecimal bbjz = new BigDecimal(bjz);
					BigDecimal btjz = new BigDecimal(tjz);

					if (relation) {
						flag = bbjz.compareTo(btjz) < 0 ? true : false;
					} else {
						flag = btjz.compareTo(bbjz) < 0 ? true : false;
					}

				}
				// ��ϵΪ"<="
				else if ("<=".equalsIgnoreCase(gx)) {

					BigDecimal bbjz = new BigDecimal(bjz);
					BigDecimal btjz = new BigDecimal(tjz);

					if (relation) {
						flag = bbjz.compareTo(btjz) < 0
								|| bbjz.compareTo(btjz) == 0 ? true : false;
					} else {
						flag = btjz.compareTo(bbjz) < 0
								|| btjz.compareTo(bbjz) == 0 ? true : false;
					}

				}
			}

			if (!flag) {
				noPjzgXh = bjzMap.get("xh");
				break;
			}
		}

		return noPjzgXh;
	}

	//=======================��Ŀ�ϱ���������(������)===============================
	/**
	 * ����������ʸ��ѧ��
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	public String[] getNoPjzgXh(String[] xh, Map<String, String> map, String lx) {

		// ��������
		String tjlx = map.get("tjlx");
		// ����˵��
		String tjms = map.get("tjms");

		// ���ʸ�����ѧ��
		String[] noPjzgXh = null;

		if ("logicRelation".equalsIgnoreCase(tjlx)) {// �߼���ϵ
			noPjzgXh = judgeLogicRelation(xh, map, null);
		} else if ("logicReverse".equalsIgnoreCase(tjlx)) {// �����߼���ϵ
			noPjzgXh = judgeLogicReverse(xh, map, null);
		} else if ("minRelation".equalsIgnoreCase(tjlx)) {// ��С�ֹ�ϵ
			noPjzgXh = judgeMinRelation(xh, map, null);
		} else if ("avgRelation".equalsIgnoreCase(tjlx)) {// ƽ���ֹ�ϵ
			noPjzgXh = judgeAvgRelation(xh, map, null);
		} else if ("instanceReverse".equalsIgnoreCase(tjlx)) {// ĳ�����ϵ
			noPjzgXh = judgeInstanceReverse(xh, map, null);
		}

		return noPjzgXh;
	}
	
	/**
	 * �ж��߼���ϵ
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String[] judgeLogicRelation(String[] xh, Map<String, String> map,
			String lx) {

		// ����˵��
		String tjms = map.get("tjms");
		// ��ϵ
		String gx = map.get("gx");
		// ����ֵ
		String tjz = map.get("tjz");
		// ������Դ��
		String tablename = map.get("tablename");
		// �ֶ�
		String zd = map.get("zd");
		// �����ʽ
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// �Ƚ�ֵ
		List<HashMap<String, String>> bjzList = getBjz(xh, map, "");

		String[] noPjzgXh = compareTo(xh, bjzList, tjz, gx, true,null);
		
		return noPjzgXh;
	}
	
	/**
	 * �жϷ����߼���ϵ
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String[] judgeLogicReverse(String[] xh, Map<String, String> map,
			String lx) {

		// ����˵��
		String tjms = map.get("tjms");
		// ��ϵ
		String gx = map.get("gx");
		// ����ֵ
		String tjz = map.get("tjz");
		// ������Դ��
		String tablename = map.get("tablename");
		// �ֶ�
		String zd = map.get("zd");
		// �����ʽ
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// �Ƚ�ֵ
		List<HashMap<String, String>> bjzList = getBjz(xh, map, "");

		String[] noPjzgXh = compareTo(xh, bjzList, tjz, gx, false,null);
		
		return noPjzgXh;

	}
	
	/**
	 * �ж�Сֵ��ϵ
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String[] judgeMinRelation(String[] xh, Map<String, String> map,
			String lx) {	

		// ����˵��
		String tjms = map.get("tjms");
		// ��ϵ
		String gx = map.get("gx");
		// ����ֵ
		String tjz = map.get("tjz");
		// ������Դ��
		String tablename = map.get("tablename");
		// �ֶ�
		String zd = map.get("zd");
		// �����ʽ
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;
		
		// �Ƚ�ֵ
		List<HashMap<String, String>> bjzList = getBjz(xh, map, "Min");

		String[] noPjzgXh  = compareTo(xh, bjzList, tjz, gx, true,null);
		
		return noPjzgXh;
	}
	
	/**
	 * �ж�ƽ��ֵ��ϵ
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String[] judgeAvgRelation(String[] xh, Map<String, String> map,
			String lx) {

		String message = "";

		// ����˵��
		String tjms = map.get("tjms");
		// ��ϵ
		String gx = map.get("gx");
		// ����ֵ
		String tjz = map.get("tjz");
		// ������Դ��
		String tablename = map.get("tablename");
		// �ֶ�
		String zd = map.get("zd");
		// �����ʽ
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// �Ƚ�ֵ
		List<HashMap<String, String>> bjzList = getBjz(xh, map, "AVG");

		String[] noPjzgXh = compareTo(xh, bjzList, tjz, gx, true,null);

		return noPjzgXh;
	}
	
	/**
	 * �ж����ϵ
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String[] judgeInstanceReverse(String[] xh, Map<String, String> map,
			String lx) {

		String message = "";

		// ����˵��
		String tjms = map.get("tjms");
		// ��ϵ
		String gx = map.get("gx");
		// ����ֵ
		String tjz = map.get("tjz");
		// ������Դ��
		String tablename = map.get("tablename");
		// �ֶ�
		String zd = map.get("zd");
		// �����ʽ
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// �Ƚ�ֵ
		List<HashMap<String, String>> bjzList = getBjz(xh, map, "count");

		String[] noPjzgXh = compareTo(xh, bjzList, tjz, gx, true, null);

		return noPjzgXh;
	}
	
	/**
	 * Ŀ��ֵ��ȷ��ֵ�Ƚ�
	 * 
	 * @param bjz
	 * @param tjz
	 * @param gx
	 * @return
	 */
	private String[] compareTo(String[] xh,
			List<HashMap<String, String>> bjzList, String tjz, String gx,
			boolean relation,String lx) {

		boolean flag = false;

		// ���ʸ�ѧ��
		ArrayList<String> noPjzgXh = new ArrayList<String>();

		for (int i = 0; i < bjzList.size(); i++) {

			HashMap<String, String> bjzMap = bjzList.get(i);
			String bjz = bjzMap.get("bjz");

			// �Ƚ�ֵ������ֵ�ǿ�
			if (StringUtils.isNotNull(bjz) && StringUtils.isNotNull(tjz)) {
				// ��ϵΪ"="
				if ("=".equalsIgnoreCase(gx)) {
					if("��".equalsIgnoreCase(tjz) && !"0".equalsIgnoreCase(bjz)){
						bjz = "��";
					}
					flag = bjz.equalsIgnoreCase(tjz) ? true : false;
				}
				// ��ϵΪ">"
				else if (">".equalsIgnoreCase(gx)) {

					BigDecimal bbjz = new BigDecimal(bjz);
					BigDecimal btjz = new BigDecimal(tjz);

					if (relation) {
						flag = bbjz.compareTo(btjz) > 0 ? true : false;
					} else {
						flag = btjz.compareTo(bbjz) > 0 ? true : false;
					}
				}
				// ��ϵΪ">="
				else if (">=".equalsIgnoreCase(gx)) {

					BigDecimal bbjz = new BigDecimal(bjz);
					BigDecimal btjz = new BigDecimal(tjz);

					if (relation) {
						flag = bbjz.compareTo(btjz) > 0
								|| bbjz.compareTo(btjz) == 0 ? true : false;
					} else {
						flag = btjz.compareTo(bbjz) > 0
								|| btjz.compareTo(bbjz) == 0 ? true : false;
					}
				}
				// ��ϵΪ"<"
				else if ("<".equalsIgnoreCase(gx)) {

					BigDecimal bbjz = new BigDecimal(bjz);
					BigDecimal btjz = new BigDecimal(tjz);

					if (relation) {
						flag = bbjz.compareTo(btjz) < 0 ? true : false;
					} else {
						flag = btjz.compareTo(bbjz) < 0 ? true : false;
					}

				}
				// ��ϵΪ"<="
				else if ("<=".equalsIgnoreCase(gx)) {

					BigDecimal bbjz = new BigDecimal(bjz);
					BigDecimal btjz = new BigDecimal(tjz);

					if (relation) {
						flag = bbjz.compareTo(btjz) < 0
								|| bbjz.compareTo(btjz) == 0 ? true : false;
					} else {
						flag = btjz.compareTo(bbjz) < 0
								|| btjz.compareTo(bbjz) == 0 ? true : false;
					}

				}
			}

			if (!flag) {
				noPjzgXh.add(bjzMap.get("xh"));
			}
		}

		return noPjzgXh.toArray(new String[] {});
	}
}
