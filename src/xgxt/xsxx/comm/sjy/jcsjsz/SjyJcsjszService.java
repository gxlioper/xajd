package xgxt.xsxx.comm.sjy.jcsjsz;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.gygl.qsgl.GyglQsglForm;
import xgxt.utils.MakeQuery;
import xgxt.xsxx.comm.XsxxCommService;
import xgxt.xsxx.comm.jbsz.XsxxJbszForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_����Դ_��������ά��_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class SjyJcsjszService extends XsxxCommService {

	SjyJcsjszDAO dao = new SjyJcsjszDAO();

	/**
	 * ����ֶ����ò�ѯ����б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getZdszRsList(SjyJcsjszForm model, User user,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		String[] queryList = new String[] { "search_sjy", "search_xgwz",
				"search_lrxz", "search_wkxz", "search_lrxs", "search_sfqy" };
		String[] queryLikeList = new String[] { "search_zdm", "search_ymxs",
				"search_xsmc" };

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		// ��
		String tableName = "xg_view_xsxx_zdszb";
		// ��ѯ����
		StringBuilder query = new StringBuilder();
		query.append(myQuery.getQueryString());

		// ����ֵ
		String[] inPutList = myQuery.getInputList();

		return getRsArrList(tableName, query.toString(), inPutList, colList,
				"", model);
	}
	
	/**
	 * ��ò��������б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getStepList(SjyJcsjszForm model) {

		// ��������
		String step = model.getStep();
		// �б�����
		int rowNum = Integer.parseInt(model.getRowNum());
		// ����������
		int stepNum = Integer.parseInt(model.getStepNum());

		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (int i = 1; i <= rowNum; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			if (i <= stepNum) {
				String dm = "step" + i;
				String mc = "��" + i + "��";
				map.put("dm", dm);
				map.put("mc", mc);
				if (dm.equalsIgnoreCase(step)) {
					map.put("color", "blue");
				} else {
					map.put("color", "black");
				}
			} else {
				map.put("dm", "");
				map.put("mc", "&nbsp;");
			}

			list.add(map);
		}

		return list;
	}

	/**
	 * ��ô������ֶ��б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, Object>> getDszZdList(SjyJcsjszForm model) {

		String tableName = "xg_xsxx_zdszb";
		String query = " order by to_number(xssx)";
		String[] inPutList = new String[] {};
		String[] colList = new String[] {"zd","zdm"};
		String sql = "";

		// �������ֶ��б�
		List<HashMap<String, String>> dszZdList = getRsList(tableName, query,
				inPutList, colList, sql);

		// ��΢����һ������
		List<HashMap<String, String>> oriList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("zd", "allChoose");
		map.put("zdm", "ȫѡ");
		oriList.add(map);
		oriList.addAll(dszZdList);
		
		// ����tableList
		HashMap<String, String> tableMap = new HashMap<String, String>();
		tableMap.put("defTrNum", "9");
		tableMap.put("tdNum", "6");
		tableMap.put("dm", "zd");
		tableMap.put("mc", "zdm");
		List<HashMap<String, Object>> list = createTableList(oriList,
				tableMap, model.getCh_zd());
		
		return list;
	}

	/**
	 * ����������ֶ��б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, Object>> getXszZdList(SjyJcsjszForm model,List<HashMap<String, String>> kczzdList) {

		// ��΢����һ������
		List<HashMap<String, String>> oriList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("zd", "allChoose");
		map.put("zdm", "ȫѡ");
		oriList.add(map);
		oriList.addAll(kczzdList);
		
		// ����tableList
		HashMap<String, String> tableMap = new HashMap<String, String>();
		tableMap.put("defTrNum", "8");
		tableMap.put("tdNum", "6");
		tableMap.put("dm", "zd");
		tableMap.put("mc", "zdm");
		List<HashMap<String, Object>> list = createTableList(oriList,
				tableMap, model.getCh_zd());
		
		return list;
	}
	
	/**
	 * ����tanbleList
	 * 
	 * @author ΰ�����
	 */
	private List<HashMap<String, Object>> createTableList(
			List<HashMap<String, String>> oriList,
			HashMap<String, String> tableMap, String[] ch_zd) {
		
		// ������б�
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		
		if (oriList != null && oriList.size() > 0) {

			// Ĭ������
			int defTrNum = Integer.parseInt(tableMap.get("defTrNum"));
			// ������
			int trNum = 0;
			// ������
			int tdNum = Integer.parseInt(tableMap.get("tdNum"));
			// ���ո���
			int spaceNum = 0;
			//�����ݵ������
			int lastTr = 0;
			// ����
			String dm = tableMap.get("dm");
			// ����
			String mc = tableMap.get("mc");
			
			if (oriList.size() % tdNum == 0) {
				trNum = oriList.size() / tdNum;
			} else {
				trNum = oriList.size() / tdNum + 1;
				spaceNum = tdNum - oriList.size() % tdNum;
			}
	
			lastTr = trNum;
			
			if(trNum<defTrNum){
				trNum = defTrNum;
			}
			for (int i = 1; i <= trNum; i++) {
				
				HashMap<String, Object> trMap = new HashMap<String, Object>();
				
				List<HashMap<String,String>> tdList = new ArrayList<HashMap<String, String>>();
				
				int n = 0;
				
				for (int j = 0; j < oriList.size(); j++) {

					if (!"yes".equalsIgnoreCase(oriList.get(j).get("used"))) {
						if (n < tdNum) {
							tdList.add(oriList.get(j));
							
							if (ch_zd != null && ch_zd.length > 0) {
								
								boolean flag = false;
								
								for (int k = 0; k < ch_zd.length; k++) {
									if(ch_zd[k].equalsIgnoreCase(oriList.get(j).get("zd"))){
										flag = true;
										break;
									}
								}
								
								if(flag){
									oriList.get(j).put("checked", "yes");
								}else{
									oriList.get(j).put("checked", "false");
								}
							}else{
								oriList.get(j).put("checked", "false");
							}
							
							oriList.get(j).put("used", "yes");
							n++;
						} else {
							break;
						}
					}
				}
				
				if (i == lastTr) {
					for (int j = 0; j < spaceNum; j++) {
						HashMap<String, String> spaceMap = new HashMap<String, String>();
						spaceMap.put(dm, "");
						spaceMap.put(mc, "&nbsp;");
						tdList.add(spaceMap);
					}
				}

				trMap.put("trNum", String.valueOf(i));

				if (tdList != null && tdList.size() > 0) {
					trMap.put("tdList", tdList);
				} else {
					for (int j = 0; j < tdNum; j++) {
						HashMap<String, String> spaceMap = new HashMap<String, String>();
						spaceMap.put(dm, "");
						spaceMap.put(mc, "&nbsp;");
						tdList.add(spaceMap);
					}
					trMap.put("tdList", tdList);
				}

				list.add(trMap);
			}
		}
		return list;
	}
	
	/**
	 * ��ÿɲ����ֶ��б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getKczzdList(SjyJcsjszForm model) {

		// ѡ���ֶ�
		String[] ch_zd = model.getCh_zd();
		// �ֶ���
		String[] ch_zdm = model.getCh_zdm();
		// ��ʾ����
		String[] xsmc = model.getXsmc();
		// ѧ��Ϊ׼
		String[] xgwz = model.getXgwz();
		// ¼������
		String[] lrxz = model.getLrxz();
		// �ɷ�Ϊ��
		String[] wkxz = model.getWkxz();
		// ¼����ʽ
		String[] lrxs = model.getLrxs();
		// ��Դ��
		String[] lyb = model.getLyb();
		// ��Դ��
		String[] lybm = model.getLybm();
		// �Ƿ�����
		String[] sfqy = model.getSfqy();
		// ��Դ������
		List<HashMap<String,String>> lybNumList = dao.getZdLybNum(ch_zd);
		
		List<HashMap<String, String>> zdList = new ArrayList<HashMap<String, String>>();

		if (ch_zd != null && ch_zd.length > 0) {

			ArrayList<String> zd = new ArrayList<String>();
			for (int i = 0; i < ch_zd.length; i++) {
				if (!"allChoose".equalsIgnoreCase(ch_zd[i])) {
					zd.add(ch_zd[i]);
				}
			}

			for (int i = 0; i < zd.size(); i++) {

				HashMap<String, String> map = new HashMap<String, String>();
				map.put("zd", zd.get(i));
				map.put("zdm", ch_zdm[i]);
				
				if(xsmc!=null&&xsmc.length>0){
					map.put("xsmc", xsmc[i]);
				}
				
				if(xgwz!=null&&xgwz.length>0){
					map.put("xgwz", xgwz[i]);
				}
				
				if(lrxz!=null&&lrxz.length>0){
					map.put("lrxz", lrxz[i]);
				}
				
				if(wkxz!=null&&wkxz.length>0){
					map.put("wkxz", wkxz[i]);
				}
				
				if(lybNumList!=null&&lybNumList.size()>0){
					for (int j = 0; j < lybNumList.size(); j++) {
						if (zd.get(i).equalsIgnoreCase(lybNumList.get(j).get("zd"))) {
							map.put("lybNum", lybNumList.get(j).get("num"));
						}
					}
				}
				
				if(lrxs!=null&&lrxs.length>0){
					map.put("lrxs", lrxs[i]);
				}
				
				if(lyb!=null&&lyb.length>0){
					map.put("lyb", lyb[i]);
				}
				
				if(lybm!=null&&lybm.length>0){
					map.put("lybm", lybm[i]);
				}
				
				if(sfqy!=null&&sfqy.length>0){
					map.put("sfqy", sfqy[i]);
				}
				zdList.add(map);
			}
		}

		return zdList;
	}
	
	/**
	 * �����ֶ�����
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveZdsz(SjyJcsjszForm model, User user) throws Exception {

		// �ֶ�
		String[] zd = model.getCh_zd();
		if (zd == null || zd.length == 0) {
			zd = model.getZd();
		}
		
		// ��ʾ����
		String[] xsmc = model.getXsmc();
		// ѧ��Ϊ׼
		String[] xgwz = model.getXgwz();
		// ¼������
		String[] lrxz = model.getLrxz();
		// �ɷ�Ϊ��
		String[] wkxz = model.getWkxz();
		// ¼����ʽ
		String[] lrxs = model.getLrxs();
		// ��Դ��
		String[] lyb = model.getLyb();
		// �Ƿ�����
		String[] sfqy = model.getSfqy();

		List<String[]> params = new ArrayList<String[]>();

		if (zd != null && zd.length > 0) {
			for (int i = 0; i < zd.length; i++) {
				List<String> valueList = new ArrayList<String>();
				valueList.add(xsmc[i]);
				valueList.add(xgwz[i]);
				valueList.add(lrxz[i]);
				valueList.add(wkxz[i]);
				valueList.add(lrxs[i]);
				valueList.add(lyb[i]);
				valueList.add(sfqy[i]);
				valueList.add(zd[i]);

				params.add(valueList.toArray(new String[] {}));
			}
		}

		return dao.saveZdsz(params, user);
	}
	
	/**
	 * �����ֶ�����
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public HashMap<String, Object> getZdszXgInfo(String zd) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		// �ֶ���Ϣ
		HashMap<String, String> zdInfo = getZdszInfo(zd);

		map.putAll(zdInfo);

		String[] bm_arr = new String[] { "nj", "xydm", "zydm", "bjdm" };
		String[] qx_arr = new String[] { "syd", "jg", "hkszd" };
		List<HashMap<String, String>> lybList = getZdLybList(zd);
		
		boolean isbm = false;
		boolean isqx = false;

		// ¼����ʽList
		List<HashMap<String,String>> lrxsList = new ArrayList<HashMap<String,String>>();

		for (int i = 0; i < bm_arr.length; i++) {
			if (zd.equalsIgnoreCase(bm_arr[i])) {
				isbm = true;
				break;
			}
		}

		for (int i = 0; i < qx_arr.length; i++) {
			if (zd.equalsIgnoreCase(qx_arr[i])) {
				isqx = true;
				break;
			}
		}

		if (isbm) {
			HashMap<String,String> lrxs = new HashMap<String,String>();
			lrxs.put("en", "�����ʽ");
			lrxs.put("cn", "�����ʽ");
			lrxsList.add(lrxs);
		} else if (isqx) {
			HashMap<String,String> lrxs = new HashMap<String,String>();
			lrxs.put("en", "�����");
			lrxs.put("cn", "�����");
			lrxsList.add(lrxs);
			
			lrxs = new HashMap<String,String>();
			lrxs.put("en", "�����ʽ");
			lrxs.put("cn", "�����ʽ");
			lrxsList.add(lrxs);
		}else if(lybList!=null && lybList.size()>0){
			HashMap<String,String> lrxs = new HashMap<String,String>();
			lrxs.put("en", "�����");
			lrxs.put("cn", "�����");
			lrxsList.add(lrxs);
			
			lrxs = new HashMap<String,String>();
			lrxs.put("en", "�����б�");
			lrxs.put("cn", "�����б�");
			lrxsList.add(lrxs);
			
			lrxs = new HashMap<String,String>();
			lrxs.put("en", "��ѡ��");
			lrxs.put("cn", "��ѡ��");
			lrxsList.add(lrxs);
		}else{
			HashMap<String,String> lrxs = new HashMap<String,String>();
			lrxs.put("en", "�����");
			lrxs.put("cn", "�����");
			lrxsList.add(lrxs);
			
			lrxs = new HashMap<String,String>();
			lrxs.put("en", "�ı���");
			lrxs.put("cn", "�ı���");
			lrxsList.add(lrxs);
			
			lrxs = new HashMap<String,String>();
			lrxs.put("en", "ʱ���ʽ");
			lrxs.put("cn", "ʱ���ʽ");
			lrxsList.add(lrxs);
		}

		map.put("lybList", lybList);
		map.put("lrxsList", lrxsList);
		
		return map;
	}
	
	/**
	 * ����ֶ�������Ϣ
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public HashMap<String, String> getZdszInfo(String zd) {

		String tableName = "xg_xsxx_zdszb";
		String pk = "zd";
		String pkValue = zd;
		String[] colList = new String[] { "zd", "zdm", "xsmc", "xgwz", "lrxz" ,"wkxz","lrxs","lyb","sfqy"};

		return getRsInfo(tableName, pk, pkValue, colList);
	}
	
	/**
	 * ����ֶ���Դ�����
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZdLybList(String zd) {

		String tableName = "xg_xsxx_zdlyb";
		String query = " where zd = ? ";
		String[] colList = new String[] { "lyb", "lybm" };

		return getRsList(tableName, query, new String[] { zd }, colList, "");
	}

	/**
	 * �����ֶ����ô����´洢����
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean createNewProcedure(SjyJcsjszForm model)
			throws Exception {
		
		DAO dao = DAO.getInstance();
		
		// ���洢���̵����
		StringBuilder createSql = new StringBuilder();
		createSql.append("create or replace procedure pro_xg_jcsj_stuInfo_tj is ");
		createSql.append("begin ");
		
		//��ѧ��ϵͳ�в����ڵ�ѧ�����뵽ϵͳ��
		createSql.append("insert into xsxxb ");
		createSql.append("(bjdm,nj,xh,byny,bz,csrq,cym,dzyx,xb, ");
		createSql.append("fdyxm,xm,mz,zzmm,sfzh,pycc,syd,xz,sfzc, ");
		createSql.append("sfzd,sfzx,sfbys,sfyby,rxrq,xjztm,lxdh, ");
		createSql.append("sjhm,ssbh,qsdh,qqhm,yhdm,yhkh,xmpy, ");
		createSql.append("sg,tz,tc,kslb,rxfs,pyfs,hkszd,jg,kh, ");
		createSql.append("ksh,nfby) ");
				
		createSql.append("select bjdm,nj,xh,byny,bz,csrq,cym,dzyx, ");
		createSql.append("xb,fdyxm,xm,mz,zzmm,sfzh,pycc,syd,xz, ");
		createSql.append("sfzc,sfzd,sfzx,sfbys,sfyby,rxrq,xjztm, ");
		createSql.append("lxdh,sjhm,ssbh,qsdh,qqhm,yhdm,yhkh,xmpy, ");
		createSql.append("sg,tz,tc,kslb,rxfs,pyfs,hkszd,jg,kh, ");
		createSql.append("ksh,nfby from xg_jcsj_xsxxb_temp a ");
		createSql.append("where not exists(select 1 from xsxxb b where a.xh=b.xh); ");
		
		createSql.append("commit; ");
		
		String tableName = "xg_xsxx_zdszb";
		String query = " where xgwz = '��' ";
		
		// �ֶ�����
		List<HashMap<String, String>> zdszList = getRsList(tableName, query,
				new String[] {}, new String[] { "zd" }, "");
		
		if (zdszList != null && zdszList.size() > 0) {
			
			// ��ִ�и��µ��ֶ�
			String[] notUpdate = new String[] { "nj", "xydm", "zydm" };
			// ��ִ�и��µ��ֶ�
			String[] addRecode = new String[] { "zzmm" };
			
			for (int i = 0; i < zdszList.size(); i++) {
				
				HashMap<String, String> map = zdszList.get(i);	
				String zd = map.get("zd");
				
				//�ǲ������ֶΣ�ִ�и��²���
				if(!isExistInArr(notUpdate, zd)){
					createSql.append(" update xsxxb a set ");
					createSql.append(zd);
					createSql.append(" = (");
					createSql.append(" select ");
					createSql.append(zd);
					createSql.append(" from xg_jcsj_xsxxb_temp b ");
					createSql.append(" where a.xh = b.xh) ");
					createSql.append(" where exists ( ");
					createSql.append(" select 1 from xg_jcsj_xsxxb_temp b ");
					createSql.append(" where a.xh = b.xh ");
					createSql.append(getQuery(zd));
					createSql.append(" ); ");
					createSql.append("commit; ");
				}
				
				// ���������ֶΣ���ѧ���й�����Ϣ��¼
				if (isExistInArr(addRecode, zd)) {
					String[] addSql = getAddGnmkSql(zd,null);
					if (addSql != null && addSql.length > 0) {
						for (int j = 0; j < addSql.length; j++) {
							createSql.append(addSql[j]);
							createSql.append(";");
							createSql.append("commit; ");
						}
					}	
				}
			}	
		}
		
		createSql.append("end pro_xg_jcsj_stuInfo_tj; ");
		
		// ִ�н���ͼ���
		boolean flag = dao.creatView(createSql.toString(), new String[]{});
		
		return flag;
	}
	
	/**
	 * �����ֶ����ô�������ͼ
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public String getQuery(String zd){
		
		StringBuilder query = new StringBuilder();
		
		if ("xb".equalsIgnoreCase(zd)) {// �Ա����
			query.append(" and ( ");
			query.append(" b.xb = '��' or b.xb = 'Ů' ");
			query.append(" ) ");
		} else if ("bjdm".equalsIgnoreCase(zd)) {// �����༶����
			query.append(" and exists ( ");
			query.append(" select 1 from xg_jcsj_bjdmb c ");
			query.append(" where b.bjdm = c.bjdm ");
			query.append(" ) ");
		} else if ("xjztm".equalsIgnoreCase(zd)) {// ѧ��״̬����
			query.append(" and exists ( ");
			query.append(" select 1 from dm_zju_xjzt c ");
			query.append(" where b.xjztm = c.zxdmmc ");
			query.append(" ) ");
		}else if ("jg".equalsIgnoreCase(zd)
				||"syd".equalsIgnoreCase(zd)
				||"hkszd".equalsIgnoreCase(zd)) {// �����������(���ᣬ��Դ�أ��������ڵ�)
			query.append(" and exists ( select 1 from ");
			query.append(" (select c.xh,c.sheng,c.shi,c.xian, ");
			query.append(" (select d.qxmc from dmk_qx d where c.sheng = d.qxdm) shengmc, ");
			query.append(" (select d.qxmc from dmk_qx d where c.shi = d.qxdm) shimc, ");
			query.append(" (select d.qxmc from dmk_qx d where c.xian = d.qxdm) xianmc ");
			query.append(" from (select xh,substr(newSsx, 1, 6) sheng, ");
			query.append(" substr(newSsx, 7, 6) shi,substr(newSsx, 13, 6) xian ");
			query.append(" from (select xh, replace("+zd+", '/', '') newSsx ");
			query.append(" from xg_jcsj_xsxxb_temp)) c) e");
			query.append(" where e.xh = b.xh ");
			query.append(" and (e.sheng is null or e.shengmc is not null) ");
			query.append(" and (e.shi is null or e.shimc is not null) ");
			query.append(" and (e.xian is null or e.xianmc is not null) ");
			query.append(" ) ");
		}
		
		return query.toString();
	}
	
	/**
	 * ���������ӵ���������ģ��
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public String[] getAddGnmkSql(String zd,String[] pk){
		
		ArrayList<String> sqlList = new ArrayList<String>();
		
		if("xh".equalsIgnoreCase(zd)){// ѧ�ţ�����ѧ��Ϊ׼�Ļ����Ƶ���ʷ��
			
			StringBuilder sql = new StringBuilder();
			
			sql.append("insert into xg_xsxx_xslsxxb ");
			sql.append("(bjdm,nj,xh,byny,bz,csrq,cym,dzyx,xb, ");
			sql.append("fdyxm,xm,mz,zzmm,sfzh,pycc,syd,xz,sfzc, ");
			sql.append("sfzd,sfzx,sfbys,sfyby,rxrq,xjztm,lxdh, ");
			sql.append("sjhm,ssbh,qsdh,qqhm,yhdm,yhkh,xmpy, ");
			sql.append("sg,tz,tc,kslb,rxfs,pyfs,hkszd,jg,kh, ");
			sql.append("ksh,nfby) ");
					
			sql.append("select bjdm,nj,xh,byny,bz,csrq,cym,dzyx, ");
			sql.append("xb,fdyxm,xm,mz,zzmm,sfzh,pycc,syd,xz, ");
			sql.append("sfzc,sfzd,sfzx,sfbys,sfyby,rxrq,xjztm, ");
			sql.append("lxdh,sjhm,ssbh,qsdh,qqhm,yhdm,yhkh,xmpy, ");
			sql.append("sg,tz,tc,kslb,rxfs,pyfs,hkszd,jg,kh, ");
			sql.append("ksh,nfby from xsxxb a ");
			sql.append("where not exists(select 1 from xg_jcsj_xsxxb_temp b where a.xh=b.xh) ");
			if (pk != null && pk.length > 0) {
				sql.append("and (");
				for (int i = 0; i < pk.length; i++) {
					if (i != 0) {
						sql.append(" or ");
					}
					sql.append(" a.xh = '" + pk[i] + "' ");
				}
				sql.append(")");
			}
			
			sqlList.add(sql.toString());
			
			sql = new StringBuilder();
			sql.append(" delete from xsxxb a ");
			sql.append(" where not exists(select 1 from xg_jcsj_xsxxb_temp b where a.xh=b.xh) ");
			if (pk != null && pk.length > 0) {
				sql.append("and (");
				for (int i = 0; i < pk.length; i++) {
					if (i != 0) {
						sql.append(" or ");
					}
					sql.append(" a.xh = '" + pk[i] + "' ");
				}
				sql.append(")");
			}
			
			sqlList.add(sql.toString());
			
		} else if ("zzmm".equalsIgnoreCase(zd)) {// ������ò��01����ʽ��Ա��

			StringBuilder sql = new StringBuilder();
			sql.append(" delete from dyxxb a where exists (select 1 ");
			sql.append(" from xg_jcsj_xsxxb_temp b where a.xh = b.xh ");
			sql.append(" and b.zzmm = '01') ");	
			if (pk != null && pk.length > 0) {
				sql.append("and (");
				for (int i = 0; i < pk.length; i++) {
					if (i != 0) {
						sql.append(" or ");
					}
					sql.append(" a.xh = '" + pk[i] + "' ");
				}
				sql.append(")");
			}
			
			sqlList.add(sql.toString());
			
			sql = new StringBuilder();
			sql.append(" insert into dyxxb  (xn, xq, nd, xh) ");
			sql.append(" select (select dqxn from xtszb where rownum = 1) xn, ");
			sql.append(" (select dqxq from xtszb where rownum = 1) xq, ");
			sql.append(" (select dqnd from xtszb where rownum = 1) nd, ");
			sql.append(" xh from xg_jcsj_xsxxb_temp a where zzmm = '01' ");
			if (pk != null && pk.length > 0) {
				sql.append("and (");
				for (int i = 0; i < pk.length; i++) {
					if (i != 0) {
						sql.append(" or ");
					}
					sql.append(" a.xh = '" + pk[i] + "' ");
				}
				sql.append(")");
			}
			
			sqlList.add(sql.toString());
		}
		
		return sqlList.toArray(new String[]{});
	}
	
	//===========================���·���by qlj=========================
	/**
	 * ��ȡ��ʾģ���б�
	 * @author ����������� 
	 */
	public List<HashMap<String,String>>getXsmkList(SjyJcsjszForm model){
		
		return dao.getXsmkList(model);
	}
	
	/**
	 * ��ȡѧ����Ϣ��ϸҳ����
	 * @author ����������� 
	 */
	public List<HashMap<String, String>> getXxypz(SjyJcsjszForm model) {

		return dao.getXxypz(model);
	}
	
	/**
	 * ������ϸҳ���ý���
	 * @author �����������  
	 * @throws Exception 
	 */
	public boolean saveXxysz(SjyJcsjszForm model) throws Exception{
		boolean blog=true;
		
		blog=saveXssx(model);
		blog=saveXsypz(model);
		
		return blog;
	}
	
	/**
	 * ������ϸҳ��ʾ˳��
	 * @author �����������  
	 * @throws Exception 
	 */
	public boolean saveXssx(SjyJcsjszForm model) throws Exception{
		
		// ���ƶ�TABLE��������
		String[] xsxm_can = model.getXsxm_can();
		int len = xsxm_can.length;
		String[] xssx = new String[len];
		for (int i = 0; i < xsxm_can.length; i++) {
			xssx[i] = String.valueOf(i + 1);
		}
		
		return dao.updateXssx(xsxm_can, xssx);
	}
	
	/**
	 * ������ϸҳ��ʾ˳��
	 * @author �����������  
	 * @throws Exception 
	 */
	public boolean saveXsypz(SjyJcsjszForm model) throws Exception{
		
		// �����ƶ�TABLE��������
		String[] xsxm_no = model.getXsxm_no();
		String[] xslxArr = model.getSfxs_no();
		
		return dao.updateXxypz(xsxm_no, xslxArr);
	}
	//=================================================================
}