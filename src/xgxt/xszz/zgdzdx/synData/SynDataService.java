package xgxt.xszz.zgdzdx.synData;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.SaveForm;
import xgxt.xszz.XszzService;
import xgxt.xszz.XszzTyForm;
import xgxt.xszz.zgdzdx.JtqkdcModel;
import xgxt.xszz.zgdzdx.KnsrdModel;

import common.Globals;
import common.XszzXmdm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 中国地大 学生资助 同步数据-->N32模块的方法
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 骆嘉伟
 * @version 1.0
 */

public class SynDataService extends XszzService {

	/**
	 * 同步家庭情况调查
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public void synJtqkdc(JtqkdcModel jtModel, HttpServletRequest request)
			throws Exception {

		XszzTyForm commModel = new XszzTyForm();

		// 学号
		commModel.setXh(jtModel.getXh());
		// 学年
		commModel.setXn(jtModel.getXn());
		// 年度
		commModel.setNd(jtModel.getNd());
		// 家庭户口
		commModel.setJthk(jtModel.getRxqhk());
		// 生源
		commModel.setSy(jtModel.getSy());
		// 家庭人口数
		commModel.setJtrs(jtModel.getJtrks());
		// 是否孤残
		commModel.setSfgc(jtModel.getSfgc());
		// 是否单亲
		commModel.setSfdq(jtModel.getSfdq());
		// 是否烈士子女
		commModel.setLszn(jtModel.getSflszn());
		// 家庭详细通讯地址
		commModel.setJtdz(jtModel.getJt_xxtxdz());
		// 家庭邮政编码
		commModel.setJtyb(jtModel.getJt_yzbm());
		// 家庭联系电话
		commModel.setJtdh(jtModel.getJt_lxdh());
		// 家庭全年收入
		commModel.setJtnzsr(jtModel.getJtqnsr());
		// 人均年收入
		commModel.setJtrjsr(jtModel.getRjnsr());

		// 是否农村贫困地区
		commModel.setSfncpkdq(jtModel.getSfncpkdq());
		// 是否城镇下岗家庭
		commModel.setSfczxgjt(jtModel.getSfczxgjt());
		// 是否父母新残疾
		commModel.setSffmxcj(jtModel.getSffmxcj());
		// 是否患重大疾病
		commModel.setSfhzdjb(jtModel.getSfhzdjb());
		// 是否单亲家庭
		commModel.setSfdqjt(jtModel.getSfdqjt());
		// 是否孤儿
		commModel.setSfgr(jtModel.getSfgr());
		// 是否自然灾害
		commModel.setSfzrzh(jtModel.getSfzrzh());
		// 是否家庭人口多
		commModel.setSfjtrkd(jtModel.getSfjtrkd());
		// 是否其它
		commModel.setSfqt(jtModel.getSfqt());
		// 其它内容
		commModel.setQtnr(jtModel.getQtnr());

		// 贫困原因详细说明
		commModel.setPkyyxxsm(jtModel.getPkyyxxsm());
		// 家中欠债情况
		commModel.setJtqzqk(jtModel.getJzqzqk());
		// 学生入学前已获资助情况
		commModel.setYhzzqk(jtModel.getXsrxqyhzzqk());
		// 其他情况
		commModel.setJtqtqk(jtModel.getQtqk());
		// 民政部门详细通讯地址
		commModel.setMzbm_xxtxdz(jtModel.getMzbm_xxtxdz());
		// 民政部门邮政编码
		commModel.setMzbm_yzbm(jtModel.getMzbm_yzbm());
		// 民政部门联系电话
		commModel.setMzbm_lxdh(jtModel.getMzbm_lxdh());
		// 申请时间
		commModel.setSqsj(jtModel.getSqsj().replace("-", ""));
		// 审核结果
		commModel.setShzt1(jtModel.getSh());
		// 项目代码
		commModel.setXmdm(XszzXmdm.XSZZ_JTQKDC);

		String[] onezd = new String[] { "xmdm", "xh", "xn", "nd", "jthk", "sy",
				"jtrs", "sfgc", "sfdq", "lszn", "jtdz", "jtyb", "jtdh",
				"jtnzsr", "jtrjsr", "sfncpkdq", "sfczxgjt", "sffmxcj",
				"sfhzdjb", "sfdqjt", "sfgr", "sfzrzh", "sfjtrkd", "sfqt",
				"qtnr", "pkyyxxsm", "jtqzqk", "yhzzqk", "jtqtqk",
				"mzbm_xxtxdz", "mzbm_yzbm", "mzbm_lxdh", "sqsj", "shzt1" };

		String tableName = "jtqkdcb";
		String pk = "xh||xn";
		String pkValue = commModel.getXh() + commModel.getXn();

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { pkValue });

		saveXszz(saveForm, commModel, request);
	}

	/**
	 * 家庭成员
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public void synJtcy(JtqkdcModel jtModel)
			throws Exception {

		XszzTyForm commModel = new XszzTyForm();

		// 学号
		String xh = jtModel.getXh();
		commModel.setXh(xh);

		String jtcy1_xm = Base.chgNull(jtModel.getJtcy1_xm(), "", 1);
		String jtcy1_nl = Base.chgNull(jtModel.getJtcy1_nl(), "", 1);
		String jtcy1_gx = Base.chgNull(jtModel.getJtcy1_gx(), "", 1);
		String jtcy1_gzdw = Base.chgNull(jtModel.getJtcy1_gzdw(), "", 1);
		String jtcy1_zy = Base.chgNull(jtModel.getJtcy1_zy(), "", 1);
		String jtcy1_sr = Base.chgNull(jtModel.getJtcy1_sr(), "", 1);
		String jtcy1_jkzk = Base.chgNull(jtModel.getJtcy1_jkzk(), "", 1);
		String jtcy2_xm = Base.chgNull(jtModel.getJtcy2_xm(), "", 1);
		String jtcy2_nl = Base.chgNull(jtModel.getJtcy2_nl(), "", 1);
		String jtcy2_gx = Base.chgNull(jtModel.getJtcy2_gx(), "", 1);
		String jtcy2_gzdw = Base.chgNull(jtModel.getJtcy2_gzdw(), "", 1);
		String jtcy2_zy = Base.chgNull(jtModel.getJtcy2_zy(), "", 1);
		String jtcy2_sr = Base.chgNull(jtModel.getJtcy2_sr(), "", 1);
		String jtcy2_jkzk = Base.chgNull(jtModel.getJtcy2_jkzk(), "", 1);
		String jtcy3_xm = Base.chgNull(jtModel.getJtcy3_xm(), "", 1);
		String jtcy3_nl = Base.chgNull(jtModel.getJtcy3_nl(), "", 1);
		String jtcy3_gx = Base.chgNull(jtModel.getJtcy3_gx(), "", 1);
		String jtcy3_gzdw = Base.chgNull(jtModel.getJtcy3_gzdw(), "", 1);
		String jtcy3_zy = Base.chgNull(jtModel.getJtcy3_zy(), "", 1);
		String jtcy3_sr = Base.chgNull(jtModel.getJtcy3_sr(), "", 1);
		String jtcy3_jkzk = Base.chgNull(jtModel.getJtcy3_jkzk(), "", 1);
		String jtcy4_xm = Base.chgNull(jtModel.getJtcy4_xm(), "", 1);
		String jtcy4_nl = Base.chgNull(jtModel.getJtcy4_nl(), "", 1);
		String jtcy4_gx = Base.chgNull(jtModel.getJtcy4_gx(), "", 1);
		String jtcy4_gzdw = Base.chgNull(jtModel.getJtcy4_gzdw(), "", 1);
		String jtcy4_zy = Base.chgNull(jtModel.getJtcy4_zy(), "", 1);
		String jtcy4_sr = Base.chgNull(jtModel.getJtcy4_sr(), "", 1);
		String jtcy4_jkzk = Base.chgNull(jtModel.getJtcy4_jkzk(), "", 1);
		String jtcy5_xm = Base.chgNull(jtModel.getJtcy5_xm(), "", 1);
		String jtcy5_nl = Base.chgNull(jtModel.getJtcy5_nl(), "", 1);
		String jtcy5_gx = Base.chgNull(jtModel.getJtcy5_gx(), "", 1);
		String jtcy5_gzdw = Base.chgNull(jtModel.getJtcy5_gzdw(), "", 1);
		String jtcy5_zy = Base.chgNull(jtModel.getJtcy5_zy(), "", 1);
		String jtcy5_sr = Base.chgNull(jtModel.getJtcy5_sr(), "", 1);
		String jtcy5_jkzk = Base.chgNull(jtModel.getJtcy5_jkzk(), "", 1);

		// 成员姓名
		String[] cyxm = new String[5];
		cyxm[0] = jtcy1_xm;
		cyxm[1] = jtcy2_xm;
		cyxm[2] = jtcy3_xm;
		cyxm[3] = jtcy4_xm;
		cyxm[4] = jtcy5_xm;
		commModel.setCyxm(cyxm);

		// 成员年龄
		String[] cynl = new String[5];
		cynl[0] = jtcy1_nl;
		cynl[1] = jtcy2_nl;
		cynl[2] = jtcy3_nl;
		cynl[3] = jtcy4_nl;
		cynl[4] = jtcy5_nl;
		commModel.setCynl(cynl);
		
		// 成员关系
		String[] cygx = new String[5];
		cygx[0] = jtcy1_gx;
		cygx[1] = jtcy2_gx;
		cygx[2] = jtcy3_gx;
		cygx[3] = jtcy4_gx;
		cygx[4] = jtcy5_gx;
		commModel.setCygx(cygx);

		// 成员工作单位
		String[] cygzdw = new String[5];
		cygzdw[0] = jtcy1_gzdw;
		cygzdw[1] = jtcy2_gzdw;
		cygzdw[2] = jtcy3_gzdw;
		cygzdw[3] = jtcy4_gzdw;
		cygzdw[4] = jtcy5_gzdw;
		commModel.setCygzdw(cygzdw);
		
		// 成员职业
		String[] cyzy = new String[5];
		cyzy[0] = jtcy1_zy;
		cyzy[1] = jtcy2_zy;
		cyzy[2] = jtcy3_zy;
		cyzy[3] = jtcy4_zy;
		cyzy[4] = jtcy5_zy;
		commModel.setCyzy(cyzy);
		
		// 成员年收入
		String[] cynsr = new String[5];
		cynsr[0] = jtcy1_sr;
		cynsr[1] = jtcy2_sr;
		cynsr[2] = jtcy3_sr;
		cynsr[3] = jtcy4_sr;
		cynsr[4] = jtcy5_sr;
		commModel.setCynsr(cynsr);
		
		// 成员健康状况
		String[] cyjkzk = new String[5];
		cyjkzk[0] = jtcy1_jkzk;
		cyjkzk[1] = jtcy2_jkzk;
		cyjkzk[2] = jtcy3_jkzk;
		cyjkzk[3] = jtcy4_jkzk;
		cyjkzk[4] = jtcy5_jkzk;
		commModel.setCyjkzk(cyjkzk);
		
		String[] arrzd = new String[] { "cyxm", "cynl", "cygx", "cygzdw",
				"cyzy", "cynsr", "cyjkzk" };
		String[] onezd = new String[] { "xh" };
		String[] notnull = new String[] { "cyxm" };

		String tableName = "xszz_jtcyb";
		String pk = "xh";
		String pkValue = commModel.getXh();

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		saveForm.setNotnull(notnull);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { pkValue });

		saveXszz(saveForm, commModel);
	}
	
	/**
	 * 同步家庭情况调查审核状态
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public void synJtqkdcSh(JtqkdcModel jtModel, HttpServletRequest request)
			throws Exception {

		String xh = Base.chgNull(jtModel.getXh(), "", 1);
		String xn = Base.chgNull(jtModel.getXn(), "", 1);
		String sh = Base.chgNull(jtModel.getSh(), "", 1);

		StandardOperation.update("jtqkdcb", new String[] { "shzt1" },
				new String[] { sh }, "xn||xh", xn + xh, request);
	}
	
	/**
	 * 删除家庭情况调查信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void synJtqkdcDel(String cbVal)
			throws Exception {
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			sqlT[i] = "delete jtqkdcb where xn||xh='"+pkT[i]+"'";
		}
		saveArrDate(sqlT);
	}
	
	/**
	 * 批量修改家庭情况调查审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void synJtqkdcShPl(String cbVal, String shjg) throws Exception {
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			sqlT[i] = "update jtqkdcb set shzt1='" + shjg + "' where xn||xh='"
					+ pkT[i] + "'";
		}
		saveArrDate(sqlT);
	}
	
	/**
	 * 同步困难生申请信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public void synKnsSq(KnsrdModel model)
			throws Exception {

		// 学号
		String xh = model.getXh();
		// 学年
		String xn = model.getXn();
		// 申请时间
		String sqsj = model.getSqsj().replace("-", "");
		// 项目代码
		String xmdm = XszzXmdm.XSZZ_KNS;

		String[] sql = new String[4];

		sql[0] = "delete from xszz_knsb where xh = '" + xh + "' and xn = '"
				+ xn + "'";
		sql[1] = "delete from xszz_shztb where xh = '" + xh + "' and xn = '"
				+ xn + "' and xmdm = '" + xmdm + "'";
		sql[2] = "insert into xszz_knsb(xn,xh,sqsj,xmdm) values('" + xn + "','"
				+ xh + "','" + sqsj + "','" + xmdm + "')";
		sql[3] = "insert into xszz_shztb(xn,xh,sqsj,xmdm) values('" + xn
				+ "','" + xh + "','" + sqsj + "','" + xmdm + "')";
		saveArrDate(sql);
	}
	
	/**
	 * 同步困难生审核信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public void synKnsSh(KnsrdModel model)
			throws Exception {

		// 学号
		String xh = model.getXh();
		// 学年
		String xn = model.getXn();
		// 项目代码
		String xmdm = XszzXmdm.XSZZ_KNS;
		// 审核状态
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
				
		String[] sql = new String[2];

		sql[0] = "update xszz_knsb set shzt3 = '通过',xmzzjb = '" + xxsh
				+ "' where xh = '" + xh + "' and xn = '" + xn
				+ "' and xmdm = '" + xmdm + "'";
		
		sql[1] = "update xszz_shztb set shzt3 = '通过',xmzzjb = '" + xxsh
		+ "' where xh = '" + xh + "' and xn = '" + xn
		+ "' and xmdm = '" + xmdm + "'";
		
		saveArrDate(sql);
	}
	
	/**
	 * 批量修改困难生认定审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void synKnsShPl(String cbVal, String shType, String shjg,
			HttpServletRequest request) throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length * 2];
		// 项目代码
		String xmdm = XszzXmdm.XSZZ_KNS;
		int n = 0;

		for (int i = 1; i < pkT.length; i++) {
			if ("xx".equalsIgnoreCase(userType)
					|| "admin".equalsIgnoreCase(userType)) {
				if ("3".equalsIgnoreCase(shType)) {

					sqlT[n] = "update xszz_knsb set shzt3 = '通过',xmzzjb='"
							+ shjg + "' where xn||xh||xmdm='" + pkT[i] + xmdm
							+ "'";
					n++;

					sqlT[n] = "update xszz_shztb set shzt3 = '通过',xmzzjb='"
							+ shjg + "' where xn||xh||xmdm='" + pkT[i] + xmdm
							+ "'";
					n++;
				}
			}
		}
		saveArrDate(sqlT);
	}
	
	/**
	 * 删除困难生认定信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void synKnsDel(String cbVal, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length * 2];
		// 项目代码
		String xmdm = XszzXmdm.XSZZ_KNS;
		int n = 0;
		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				
				sqlT[n] = "delete xszz_knsb where xn||xh||xmdm='" + pkT[i]
						+ xmdm + "'";
				n++;

				sqlT[n] = "delete xszz_shztb where xn||xh||xmdm='" + pkT[i]
						+ xmdm + "'";
				n++;
				
			} else {
				
				sqlT[n] = "delete xszz_knsb where xn||xh||xmdm='" + pkT[i]
						+ xmdm + "' and xmzzjb is not null ";
				n++;

				sqlT[n] = "delete xszz_shztb where xn||xh||xmdm='" + pkT[i]
						+ xmdm + "' and xmzzjb is not null ";
				n++;
			}
		}
		saveArrDate(sqlT);
	}
	
	/**
	 * 同步导入数据
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void synImpData() throws Exception {

		String[] sql = new String[8];

		// 困难生信息
		sql[0] = "delete xszz_shztb where xmdm = '5002' ";

		sql[1] = "delete xszz_knsb a where xmdm = '5002' ";

		sql[2] = "insert into xszz_shztb (xn,xh,sqsj,xmdm,shzt3,xmzzjb) "
				+ "select xn,xh,substr(xn,0,4)||'1001' sqsj,'5002','通过',xxsh from zgdzdx_kns_pksrd where xxsh <>'未审核'";

		sql[3] = "insert into xszz_knsb (xn,xh,sqsj,xmdm,shzt3,xmzzjb) "
				+ "select xn,xh,substr(xn,0,4)||'1001' sqsj,'5002','通过',xxsh from zgdzdx_kns_pksrd where xxsh <>'未审核'";

		//家庭情况
		
		sql[4] = "delete jtqkdcb a where xmdm = '5001' ";
		
//		sql[6] = "insert into xszz_shztb (xn,xh,sqsj,xmdm,shzt1) "
//			+ "select xn,xh,substr(xn,0,4)||'1001' sqsj,'5001',sh from zgdzdx_kns_jtqkdc";
		
		sql[5] = "insert into jtqkdcb (xmdm, xh, xn, nd, jthk, sy,"
				+ "jtrs, sfgc, sfdq, lszn, jtdz, jtyb, jtdh,"
				+ "jtnzsr, jtrjsr, sfncpkdq, sfczxgjt, sffmxcj,"
				+ "sfhzdjb, sfdqjt, sfgr, sfzrzh, sfjtrkd, sfqt,"
				+ "qtnr, pkyyxxsm, jtqzqk, yhzzqk, jtqtqk,"
				+ "mzbm_xxtxdz, mzbm_yzbm, mzbm_lxdh, sqsj, shzt1) "
				+ "select '5001', xh, xn, nd, rxqhk, sy,"
				+ "jtrks, sfgc, sfdq, sflszn, jt_xxtxdz, jt_yzbm, jt_lxdh,"
				+ "jtqnsr, rjnsr, sfncpkdq, sfczxgjt, sffmxcj,"
				+ "sfhzdjb, sfdqjt, sfgr, sfzrzh, sfjtrkd, sfqt,"
				+ "qtnr, pkyyxxsm, jzqzqk, xsrxqyhzzqk, qtqk,"
				+ "mzbm_xxtxdz, mzbm_yzbm, mzbm_lxdh, substr(xn,0,4)||'1001' sqsj, sh"
				+" from zgdzdx_kns_jtqkdc";

		sql[6] = "delete xszz_jtcyb a where exists (select 1 from zgdzdx_kns_jtqkdc b where a.xh = b.xh)";
		
		sql[7] = "insert into xszz_jtcyb(xh,cyxm,cynl,cygx,cygzdw,cyzy,cynsr,cyjkzk)"
				+" select xh,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy1_zy,jtcy1_sr,jtcy1_jkzk from zgdzdx_kns_jtqkdc where jtcy1_xm is not null"
				+" union all select xh,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy2_zy,jtcy2_sr,jtcy2_jkzk from zgdzdx_kns_jtqkdc where jtcy2_xm is not null"
				+" union all select xh,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy3_zy,jtcy3_sr,jtcy3_jkzk from zgdzdx_kns_jtqkdc where jtcy3_xm is not null"
				+" union all select xh,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy4_zy,jtcy4_sr,jtcy4_jkzk from zgdzdx_kns_jtqkdc where jtcy4_xm is not null"
				+" union all select xh,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,jtcy5_zy,jtcy5_sr,jtcy5_jkzk from zgdzdx_kns_jtqkdc where jtcy5_xm is not null";
		
		String xxdm = Base.xxdm;
		
		//中国地质大学
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
			saveArrDate(sql);
		}
	}
}
