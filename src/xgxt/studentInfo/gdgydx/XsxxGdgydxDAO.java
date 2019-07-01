package xgxt.studentInfo.gdgydx;

import xgxt.DAO.DAO;

public class XsxxGdgydxDAO extends DAO{
	
	/**
	 * ͬ����������
	 * @param whereSql
	 * @return boolean
	 * */
	public boolean sjtb(String whereSql){
		boolean flag = false;
		//��ѧ����Ϣɾ��		
		String sql = "delete from bks_xsjbxx c where 1=1 and exists (select 1 from xsjbxxb@dblink_jw a where a.xh=c.xh "+whereSql + ")";
		try {
			//����ѧԺ��רҵ���༶�Ȼ�����Ϣ			
			flag = runProcuder("{call updateXyzybj()}", new String[]{});
			if(flag){
				flag = runUpdate(sql, new String[]{});	
				if(flag){
					//��ѧ����Ϣ����
					sql = "insert into bks_xsjbxx(xh,bmdm,bjdm,xkmldm,zydm,jzgh,xm,mm,xmpy,cym,pyfs,xslbm,xfzbz,xz,rxny" +
						  ",nj,zjlbm,sfzh,xbm,gatqm,jgm,yydj,xxnx,xjztm,pyfx,zyfx,eje,yxxsbz,lqh,rxfsm,xsdl,ywxtbh,zhgxsj" +
						  ",bz,ksh)(select a.xh,b.BMDM,b.bjdm,'' XKMLDM,b.ZYDM,b.JZGH,a.xm,a.MM,a.XMPY,a.zym cym," +
						  "'' pyfs,a.xslb XSLBM,'' XFZBZ,a.xz,a.rxrq RXNY,a.dqszj NJ,'' ZJLBM,a.sfzh,decode(a.xb,'��','1','Ů','2') XBM," +
						  "a.gatm GATQM,a.jg JGM,a.dj YYDJ,a.xxnx,a.xjzt XJZTM,a.pyfx,a.zyfx,'' EJE,'' YXXSBZ,'' LQH,a.rxfs RXFSM," +
						  "'' XSDL,'' YWXTBH,'' ZHGXSJ,a.bz,'' ksh from xsjbxxb@dblink_jw a,bks_bjdm b where a.xzb=b.bjmc "+whereSql+")";
					flag = insert(sql, new String[]{});
					if(flag){						
						sql = "delete from bks_xsqtxx c where 1=1 and exists (select 1 from xsjbxxb@dblink_jw a where a.xh=c.xh "+whereSql+")";//ɾ��ѧ��������Ϣ 
						flag = runUpdate(sql, new String[]{});
						if(flag){//����ѧ��������Ϣ
							sql = "insert into bks_xsqtxx(xh,zjxy,jkzkm,xxm,hyzkm,gbm,jrny,zzmmm,mzdm,hkszd,hkxzm,gfsbz,cwrdjjfzrq,rdjjfz," +
								  "bybz,bsldbz,bbldbz,pksbz,dpsbz,exwbz,jhsbz,lxsbz,ybybz,ybynf,byzx,lydq,csdm,csrq,tc,ywxtbh,zhgxsj,bz)" +
								  "(select a.xh,'' zjxy,'' JKZKM,'' xxm,'' hyzkm,'' gbm,'' jrny,b.zzmmdm zzmmm,c.mzdm,'' hkszd,'' hkxzm,'' gfsbz," +
								  "'' CWRDJJFZRQ,'' RDJJFZ,'' BYBZ,'' BSLDBZ,'' BBLDBZ,'' PKSBZ,'' DPSBZ,'' EXWBZ,'' JHSBZ,'' LXSBZ,'' YBYBZ," +
								  "'' YBYNF,'' BYZX,'' LYDQ,'' CSDM,a.csrq,'' tc,'' YWXTBH,'' ZHGXSJ,'' BZ from xsjbxxb@dblink_jw a " +
								  "left join zzmmdmb b on a.zzmm=b.zzmmmc left join mzdmb c on a.mz=c.mzmc where 1=1 " + whereSql +")";
							flag = insert(sql, new String[]{});		
							if(flag){//����ѧ����ͥ��Ϣ
								sql = "delete from xsfzxxb  c where 1=1 and exists (select 1 from xsjbxxb@dblink_jw a where a.xh=c.xh "+whereSql+")";//ɾ��ѧ����ͥ��Ϣ
								flag = runUpdate(sql, new String[]{});
								if(flag){
									sql = "insert into xsfzxxb(xh,jtszd,yb,lxdh1,jtcy1_xm,jtcy1_gx,jtcy1_gzdz,jtcy1_yzbm,jtcy1_lxdh2,jtcy1_nl,jtcy1_zzmm," +
											"jtcy2_xm,jtcy2_gx,jtcy2_gzdz,jtcy2_yzbm,jtcy2_lxdh2,jtcy2_nl,jtcy2_zzmm)(select xh,jtdz,jtyb,jtdh,fqxm,'����'," +
											"fqdw,fqdwyb,fqdwdh,fqcsrq,fqzzmm,mqxm,'ĸ��',mqdw,mqdwyb,mqdwdh,mqcsrq,mqzzmm from xsjtb@dblink_jw c  where 1=1 " +
											"and exists(select 1 from xsjbxxb@dblink_jw a,bks_bjdm b where a.xzb=b.bjmc "+whereSql+" and a.xh=c.xh))";
									flag = insert(sql, new String[]{});
								}
							}
						}
					}
				}				
			}		
		} catch (Exception e) {			
			e.printStackTrace();
			return false;
		}		
		return flag;
	}
}
