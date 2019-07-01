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
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_数据源_基础数据维护_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class SjyJcsjszService extends XsxxCommService {

	SjyJcsjszDAO dao = new SjyJcsjszDAO();

	/**
	 * 获得字段设置查询结果列表
	 * 
	 * @author 伟大的骆
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

		// 表
		String tableName = "xg_view_xsxx_zdszb";
		// 查询条件
		StringBuilder query = new StringBuilder();
		query.append(myQuery.getQueryString());

		// 输入值
		String[] inPutList = myQuery.getInputList();

		return getRsArrList(tableName, query.toString(), inPutList, colList,
				"", model);
	}
	
	/**
	 * 获得操作步骤列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getStepList(SjyJcsjszForm model) {

		// 操作步骤
		String step = model.getStep();
		// 列表行数
		int rowNum = Integer.parseInt(model.getRowNum());
		// 操作步骤数
		int stepNum = Integer.parseInt(model.getStepNum());

		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (int i = 1; i <= rowNum; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			if (i <= stepNum) {
				String dm = "step" + i;
				String mc = "第" + i + "步";
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
	 * 获得待设置字段列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, Object>> getDszZdList(SjyJcsjszForm model) {

		String tableName = "xg_xsxx_zdszb";
		String query = " order by to_number(xssx)";
		String[] inPutList = new String[] {};
		String[] colList = new String[] {"zd","zdm"};
		String sql = "";

		// 待分配字段列表
		List<HashMap<String, String>> dszZdList = getRsList(tableName, query,
				inPutList, colList, sql);

		// 稍微处理一下数据
		List<HashMap<String, String>> oriList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("zd", "allChoose");
		map.put("zdm", "全选");
		oriList.add(map);
		oriList.addAll(dszZdList);
		
		// 创建tableList
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
	 * 获得需设置字段列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, Object>> getXszZdList(SjyJcsjszForm model,List<HashMap<String, String>> kczzdList) {

		// 稍微处理一下数据
		List<HashMap<String, String>> oriList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("zd", "allChoose");
		map.put("zdm", "全选");
		oriList.add(map);
		oriList.addAll(kczzdList);
		
		// 创建tableList
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
	 * 构建tanbleList
	 * 
	 * @author 伟大的骆
	 */
	private List<HashMap<String, Object>> createTableList(
			List<HashMap<String, String>> oriList,
			HashMap<String, String> tableMap, String[] ch_zd) {
		
		// 处理后列表
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		
		if (oriList != null && oriList.size() > 0) {

			// 默认行数
			int defTrNum = Integer.parseInt(tableMap.get("defTrNum"));
			// 表单行数
			int trNum = 0;
			// 表单列数
			int tdNum = Integer.parseInt(tableMap.get("tdNum"));
			// 补空格列
			int spaceNum = 0;
			//有数据的最后行
			int lastTr = 0;
			// 代码
			String dm = tableMap.get("dm");
			// 名称
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
	 * 获得可操作字段列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getKczzdList(SjyJcsjszForm model) {

		// 选中字段
		String[] ch_zd = model.getCh_zd();
		// 字段名
		String[] ch_zdm = model.getCh_zdm();
		// 显示名称
		String[] xsmc = model.getXsmc();
		// 学工为准
		String[] xgwz = model.getXgwz();
		// 录入限制
		String[] lrxz = model.getLrxz();
		// 可否为空
		String[] wkxz = model.getWkxz();
		// 录入形式
		String[] lrxs = model.getLrxs();
		// 来源表
		String[] lyb = model.getLyb();
		// 来源表
		String[] lybm = model.getLybm();
		// 是否启用
		String[] sfqy = model.getSfqy();
		// 来源表数量
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
	 * 保存字段设置
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveZdsz(SjyJcsjszForm model, User user) throws Exception {

		// 字段
		String[] zd = model.getCh_zd();
		if (zd == null || zd.length == 0) {
			zd = model.getZd();
		}
		
		// 显示名称
		String[] xsmc = model.getXsmc();
		// 学工为准
		String[] xgwz = model.getXgwz();
		// 录入限制
		String[] lrxz = model.getLrxz();
		// 可否为空
		String[] wkxz = model.getWkxz();
		// 录入形式
		String[] lrxs = model.getLrxs();
		// 来源表
		String[] lyb = model.getLyb();
		// 是否启用
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
	 * 保存字段设置
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public HashMap<String, Object> getZdszXgInfo(String zd) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		// 字段信息
		HashMap<String, String> zdInfo = getZdszInfo(zd);

		map.putAll(zdInfo);

		String[] bm_arr = new String[] { "nj", "xydm", "zydm", "bjdm" };
		String[] qx_arr = new String[] { "syd", "jg", "hkszd" };
		List<HashMap<String, String>> lybList = getZdLybList(zd);
		
		boolean isbm = false;
		boolean isqx = false;

		// 录入形式List
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
			lrxs.put("en", "特殊格式");
			lrxs.put("cn", "特殊格式");
			lrxsList.add(lrxs);
		} else if (isqx) {
			HashMap<String,String> lrxs = new HashMap<String,String>();
			lrxs.put("en", "输入框");
			lrxs.put("cn", "输入框");
			lrxsList.add(lrxs);
			
			lrxs = new HashMap<String,String>();
			lrxs.put("en", "特殊格式");
			lrxs.put("cn", "特殊格式");
			lrxsList.add(lrxs);
		}else if(lybList!=null && lybList.size()>0){
			HashMap<String,String> lrxs = new HashMap<String,String>();
			lrxs.put("en", "输入框");
			lrxs.put("cn", "输入框");
			lrxsList.add(lrxs);
			
			lrxs = new HashMap<String,String>();
			lrxs.put("en", "下拉列表");
			lrxs.put("cn", "下拉列表");
			lrxsList.add(lrxs);
			
			lrxs = new HashMap<String,String>();
			lrxs.put("en", "单选框");
			lrxs.put("cn", "单选框");
			lrxsList.add(lrxs);
		}else{
			HashMap<String,String> lrxs = new HashMap<String,String>();
			lrxs.put("en", "输入框");
			lrxs.put("cn", "输入框");
			lrxsList.add(lrxs);
			
			lrxs = new HashMap<String,String>();
			lrxs.put("en", "文本域");
			lrxs.put("cn", "文本域");
			lrxsList.add(lrxs);
			
			lrxs = new HashMap<String,String>();
			lrxs.put("en", "时间格式");
			lrxs.put("cn", "时间格式");
			lrxsList.add(lrxs);
		}

		map.put("lybList", lybList);
		map.put("lrxsList", lrxsList);
		
		return map;
	}
	
	/**
	 * 获得字段设置信息
	 * 
	 * @author 伟大的骆
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
	 * 获得字段来源表李彪
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZdLybList(String zd) {

		String tableName = "xg_xsxx_zdlyb";
		String query = " where zd = ? ";
		String[] colList = new String[] { "lyb", "lybm" };

		return getRsList(tableName, query, new String[] { zd }, colList, "");
	}

	/**
	 * 根据字段设置创建新存储过程
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean createNewProcedure(SjyJcsjszForm model)
			throws Exception {
		
		DAO dao = DAO.getInstance();
		
		// 建存储过程的语句
		StringBuilder createSql = new StringBuilder();
		createSql.append("create or replace procedure pro_xg_jcsj_stuInfo_tj is ");
		createSql.append("begin ");
		
		//将学工系统中不存在的学生导入到系统中
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
		String query = " where xgwz = '否' ";
		
		// 字段设置
		List<HashMap<String, String>> zdszList = getRsList(tableName, query,
				new String[] {}, new String[] { "zd" }, "");
		
		if (zdszList != null && zdszList.size() > 0) {
			
			// 不执行更新的字段
			String[] notUpdate = new String[] { "nj", "xydm", "zydm" };
			// 不执行更新的字段
			String[] addRecode = new String[] { "zzmm" };
			
			for (int i = 0; i < zdszList.size(); i++) {
				
				HashMap<String, String> map = zdszList.get(i);	
				String zd = map.get("zd");
				
				//非不更新字段，执行更新操作
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
				
				// 关联内容字段，在学工中构建信息记录
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
		
		// 执行建视图语句
		boolean flag = dao.creatView(createSql.toString(), new String[]{});
		
		return flag;
	}
	
	/**
	 * 根据字段设置创建新视图
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public String getQuery(String zd){
		
		StringBuilder query = new StringBuilder();
		
		if ("xb".equalsIgnoreCase(zd)) {// 性别规则
			query.append(" and ( ");
			query.append(" b.xb = '男' or b.xb = '女' ");
			query.append(" ) ");
		} else if ("bjdm".equalsIgnoreCase(zd)) {// 所属班级规则
			query.append(" and exists ( ");
			query.append(" select 1 from xg_jcsj_bjdmb c ");
			query.append(" where b.bjdm = c.bjdm ");
			query.append(" ) ");
		} else if ("xjztm".equalsIgnoreCase(zd)) {// 学籍状态规则
			query.append(" and exists ( ");
			query.append(" select 1 from dm_zju_xjzt c ");
			query.append(" where b.xjztm = c.zxdmmc ");
			query.append(" ) ");
		}else if ("jg".equalsIgnoreCase(zd)
				||"syd".equalsIgnoreCase(zd)
				||"hkszd".equalsIgnoreCase(zd)) {// 行政区块规则(籍贯，生源地，户口所在地)
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
	 * 将数据增加到其他功能模块
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public String[] getAddGnmkSql(String zd,String[] pk){
		
		ArrayList<String> sqlList = new ArrayList<String>();
		
		if("xh".equalsIgnoreCase(zd)){// 学号（不以学工为准的话，移到历史表）
			
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
			
		} else if ("zzmm".equalsIgnoreCase(zd)) {// 政治面貌（01：正式党员）

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
	
	//===========================以下方法by qlj=========================
	/**
	 * 获取显示模块列表
	 * @author 今天中五百万 
	 */
	public List<HashMap<String,String>>getXsmkList(SjyJcsjszForm model){
		
		return dao.getXsmkList(model);
	}
	
	/**
	 * 获取学生信息详细页配置
	 * @author 今天中五百万 
	 */
	public List<HashMap<String, String>> getXxypz(SjyJcsjszForm model) {

		return dao.getXxypz(model);
	}
	
	/**
	 * 保存详细页配置界面
	 * @author 今天中五百万  
	 * @throws Exception 
	 */
	public boolean saveXxysz(SjyJcsjszForm model) throws Exception{
		boolean blog=true;
		
		blog=saveXssx(model);
		blog=saveXsypz(model);
		
		return blog;
	}
	
	/**
	 * 保存详细页显示顺序
	 * @author 今天中五百万  
	 * @throws Exception 
	 */
	public boolean saveXssx(SjyJcsjszForm model) throws Exception{
		
		// 可移动TABLE的隐藏域
		String[] xsxm_can = model.getXsxm_can();
		int len = xsxm_can.length;
		String[] xssx = new String[len];
		for (int i = 0; i < xsxm_can.length; i++) {
			xssx[i] = String.valueOf(i + 1);
		}
		
		return dao.updateXssx(xsxm_can, xssx);
	}
	
	/**
	 * 保存详细页显示顺序
	 * @author 今天中五百万  
	 * @throws Exception 
	 */
	public boolean saveXsypz(SjyJcsjszForm model) throws Exception{
		
		// 不可移动TABLE的隐藏域
		String[] xsxm_no = model.getXsxm_no();
		String[] xslxArr = model.getSfxs_no();
		
		return dao.updateXxypz(xsxm_no, xslxArr);
	}
	//=================================================================
}