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
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �й��ش� ѧ������ ͬ������-->N32ģ��ķ���
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ���ΰ
 * @version 1.0
 */

public class SynDataService extends XszzService {

	/**
	 * ͬ����ͥ�������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public void synJtqkdc(JtqkdcModel jtModel, HttpServletRequest request)
			throws Exception {

		XszzTyForm commModel = new XszzTyForm();

		// ѧ��
		commModel.setXh(jtModel.getXh());
		// ѧ��
		commModel.setXn(jtModel.getXn());
		// ���
		commModel.setNd(jtModel.getNd());
		// ��ͥ����
		commModel.setJthk(jtModel.getRxqhk());
		// ��Դ
		commModel.setSy(jtModel.getSy());
		// ��ͥ�˿���
		commModel.setJtrs(jtModel.getJtrks());
		// �Ƿ�²�
		commModel.setSfgc(jtModel.getSfgc());
		// �Ƿ���
		commModel.setSfdq(jtModel.getSfdq());
		// �Ƿ���ʿ��Ů
		commModel.setLszn(jtModel.getSflszn());
		// ��ͥ��ϸͨѶ��ַ
		commModel.setJtdz(jtModel.getJt_xxtxdz());
		// ��ͥ��������
		commModel.setJtyb(jtModel.getJt_yzbm());
		// ��ͥ��ϵ�绰
		commModel.setJtdh(jtModel.getJt_lxdh());
		// ��ͥȫ������
		commModel.setJtnzsr(jtModel.getJtqnsr());
		// �˾�������
		commModel.setJtrjsr(jtModel.getRjnsr());

		// �Ƿ�ũ��ƶ������
		commModel.setSfncpkdq(jtModel.getSfncpkdq());
		// �Ƿ�����¸ڼ�ͥ
		commModel.setSfczxgjt(jtModel.getSfczxgjt());
		// �Ƿ�ĸ�²м�
		commModel.setSffmxcj(jtModel.getSffmxcj());
		// �Ƿ��ش󼲲�
		commModel.setSfhzdjb(jtModel.getSfhzdjb());
		// �Ƿ��׼�ͥ
		commModel.setSfdqjt(jtModel.getSfdqjt());
		// �Ƿ�¶�
		commModel.setSfgr(jtModel.getSfgr());
		// �Ƿ���Ȼ�ֺ�
		commModel.setSfzrzh(jtModel.getSfzrzh());
		// �Ƿ��ͥ�˿ڶ�
		commModel.setSfjtrkd(jtModel.getSfjtrkd());
		// �Ƿ�����
		commModel.setSfqt(jtModel.getSfqt());
		// ��������
		commModel.setQtnr(jtModel.getQtnr());

		// ƶ��ԭ����ϸ˵��
		commModel.setPkyyxxsm(jtModel.getPkyyxxsm());
		// ����Ƿծ���
		commModel.setJtqzqk(jtModel.getJzqzqk());
		// ѧ����ѧǰ�ѻ��������
		commModel.setYhzzqk(jtModel.getXsrxqyhzzqk());
		// �������
		commModel.setJtqtqk(jtModel.getQtqk());
		// ����������ϸͨѶ��ַ
		commModel.setMzbm_xxtxdz(jtModel.getMzbm_xxtxdz());
		// ����������������
		commModel.setMzbm_yzbm(jtModel.getMzbm_yzbm());
		// ����������ϵ�绰
		commModel.setMzbm_lxdh(jtModel.getMzbm_lxdh());
		// ����ʱ��
		commModel.setSqsj(jtModel.getSqsj().replace("-", ""));
		// ��˽��
		commModel.setShzt1(jtModel.getSh());
		// ��Ŀ����
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
	 * ��ͥ��Ա
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public void synJtcy(JtqkdcModel jtModel)
			throws Exception {

		XszzTyForm commModel = new XszzTyForm();

		// ѧ��
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

		// ��Ա����
		String[] cyxm = new String[5];
		cyxm[0] = jtcy1_xm;
		cyxm[1] = jtcy2_xm;
		cyxm[2] = jtcy3_xm;
		cyxm[3] = jtcy4_xm;
		cyxm[4] = jtcy5_xm;
		commModel.setCyxm(cyxm);

		// ��Ա����
		String[] cynl = new String[5];
		cynl[0] = jtcy1_nl;
		cynl[1] = jtcy2_nl;
		cynl[2] = jtcy3_nl;
		cynl[3] = jtcy4_nl;
		cynl[4] = jtcy5_nl;
		commModel.setCynl(cynl);
		
		// ��Ա��ϵ
		String[] cygx = new String[5];
		cygx[0] = jtcy1_gx;
		cygx[1] = jtcy2_gx;
		cygx[2] = jtcy3_gx;
		cygx[3] = jtcy4_gx;
		cygx[4] = jtcy5_gx;
		commModel.setCygx(cygx);

		// ��Ա������λ
		String[] cygzdw = new String[5];
		cygzdw[0] = jtcy1_gzdw;
		cygzdw[1] = jtcy2_gzdw;
		cygzdw[2] = jtcy3_gzdw;
		cygzdw[3] = jtcy4_gzdw;
		cygzdw[4] = jtcy5_gzdw;
		commModel.setCygzdw(cygzdw);
		
		// ��Աְҵ
		String[] cyzy = new String[5];
		cyzy[0] = jtcy1_zy;
		cyzy[1] = jtcy2_zy;
		cyzy[2] = jtcy3_zy;
		cyzy[3] = jtcy4_zy;
		cyzy[4] = jtcy5_zy;
		commModel.setCyzy(cyzy);
		
		// ��Ա������
		String[] cynsr = new String[5];
		cynsr[0] = jtcy1_sr;
		cynsr[1] = jtcy2_sr;
		cynsr[2] = jtcy3_sr;
		cynsr[3] = jtcy4_sr;
		cynsr[4] = jtcy5_sr;
		commModel.setCynsr(cynsr);
		
		// ��Ա����״��
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
	 * ͬ����ͥ����������״̬
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
	 * ɾ����ͥ���������Ϣ
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
	 * �����޸ļ�ͥ���������˽��
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
	 * ͬ��������������Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public void synKnsSq(KnsrdModel model)
			throws Exception {

		// ѧ��
		String xh = model.getXh();
		// ѧ��
		String xn = model.getXn();
		// ����ʱ��
		String sqsj = model.getSqsj().replace("-", "");
		// ��Ŀ����
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
	 * ͬ�������������Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public void synKnsSh(KnsrdModel model)
			throws Exception {

		// ѧ��
		String xh = model.getXh();
		// ѧ��
		String xn = model.getXn();
		// ��Ŀ����
		String xmdm = XszzXmdm.XSZZ_KNS;
		// ���״̬
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
				
		String[] sql = new String[2];

		sql[0] = "update xszz_knsb set shzt3 = 'ͨ��',xmzzjb = '" + xxsh
				+ "' where xh = '" + xh + "' and xn = '" + xn
				+ "' and xmdm = '" + xmdm + "'";
		
		sql[1] = "update xszz_shztb set shzt3 = 'ͨ��',xmzzjb = '" + xxsh
		+ "' where xh = '" + xh + "' and xn = '" + xn
		+ "' and xmdm = '" + xmdm + "'";
		
		saveArrDate(sql);
	}
	
	/**
	 * �����޸��������϶���˽��
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
		// ��Ŀ����
		String xmdm = XszzXmdm.XSZZ_KNS;
		int n = 0;

		for (int i = 1; i < pkT.length; i++) {
			if ("xx".equalsIgnoreCase(userType)
					|| "admin".equalsIgnoreCase(userType)) {
				if ("3".equalsIgnoreCase(shType)) {

					sqlT[n] = "update xszz_knsb set shzt3 = 'ͨ��',xmzzjb='"
							+ shjg + "' where xn||xh||xmdm='" + pkT[i] + xmdm
							+ "'";
					n++;

					sqlT[n] = "update xszz_shztb set shzt3 = 'ͨ��',xmzzjb='"
							+ shjg + "' where xn||xh||xmdm='" + pkT[i] + xmdm
							+ "'";
					n++;
				}
			}
		}
		saveArrDate(sqlT);
	}
	
	/**
	 * ɾ���������϶���Ϣ
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
		// ��Ŀ����
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
	 * ͬ����������
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void synImpData() throws Exception {

		String[] sql = new String[8];

		// ��������Ϣ
		sql[0] = "delete xszz_shztb where xmdm = '5002' ";

		sql[1] = "delete xszz_knsb a where xmdm = '5002' ";

		sql[2] = "insert into xszz_shztb (xn,xh,sqsj,xmdm,shzt3,xmzzjb) "
				+ "select xn,xh,substr(xn,0,4)||'1001' sqsj,'5002','ͨ��',xxsh from zgdzdx_kns_pksrd where xxsh <>'δ���'";

		sql[3] = "insert into xszz_knsb (xn,xh,sqsj,xmdm,shzt3,xmzzjb) "
				+ "select xn,xh,substr(xn,0,4)||'1001' sqsj,'5002','ͨ��',xxsh from zgdzdx_kns_pksrd where xxsh <>'δ���'";

		//��ͥ���
		
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
		
		//�й����ʴ�ѧ
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
			saveArrDate(sql);
		}
	}
}
