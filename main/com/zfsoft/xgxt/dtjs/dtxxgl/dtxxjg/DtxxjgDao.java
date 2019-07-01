/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:07:04
 */
package com.zfsoft.xgxt.dtjs.dtxxgl.dtxxjg;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import org.apache.commons.beanutils.BeanUtils;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2013-9-9 下午12:07:04
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class DtxxjgDao extends SuperDAOImpl<DtxxjgForm> {

	/*
     * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

    @Override
    public List<HashMap<String, String>> getPageList(DtxxjgForm t, User user)
            throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
                "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(t.getSearchModel());

        //StringBuffer sql = new StringBuffer(getBaseSql());

        StringBuffer sql = new StringBuffer("select a.*,t2.sydm,t3.symc from VIEW_NEW_DC_DTXX_JG a ");
        sql.append(" left join XG_XTWH_SYBJGLB t2 on t2.bjdm = a.bjdm ");
        sql.append(" left join XG_XTWH_SYDMB t3 on t2.sydm = t3.sydm ");
        sql.append(" where 1=1 ");
        sql.append(searchTjByUser);
        sql.append(" ");
        sql.append(searchTj);
        return this.getPageList(t, sql.toString(), inputV);
    }

    /**
     * @param xh
     * @param jddm
     * @return HashMap<String,String> 返回类型
     * @描述:获取个人某个阶段的党团信息
     * @作者：张昌路[工号：982]
     * @日期：2013-10-28 下午03:27:38
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public HashMap<String, String> getGrDtjgxx(String xh, String jddm) {
        StringBuffer sb = new StringBuffer();
        sb.append("select * from XG_DTJS_XSDTXXJLB where xh=? and jddm=?");
        return dao.getMapNotOut(sb.toString(), new String[]{xh, jddm});
    }

    /**
     * @param xh
     * @return List<HashMap<String,String>> 返回类型
     * @描述:获得党团信息结果相关所有阶段信息
     * @作者：张昌路[工号：982]
     * @日期：2013-10-29 上午10:05:35
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public List<HashMap<String, String>> getDtxxjgJdxx(String xh) {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from XG_DTJS_XSDTXXJLB where xh=?");
        return dao.getListNotOut(sql.toString(), new String[]{xh});
    }

	/*
     * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

    @Override
    public List<HashMap<String, String>> getPageList(DtxxjgForm t)
            throws Exception {
        List<String> param = new ArrayList<String>();
        StringBuffer sql = new StringBuffer();
        sql.append("select * from (");
        sql.append(" select a.*,xsxx.xm");
        sql.append(" from XG_DTJS_XSDTXXJLB a");
        // 学生信息
        sql.append(" left join view_xsxxb xsxx on a.xh=xsxx.xh ");
        sql.append(" ) a where 1=1");
        if (StringUtils.isNotNull(t.getDtxxjgid())) {
            sql.append(" and a.xh||a.jddm=?");
            param.add(t.getDtxxjgid());
        }
        return this.getPageList(t, sql.toString(), param
                .toArray(new String[]{}));
    }

    /*
     * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
     */
    @Override
    protected void setTableInfo() {
        this.setKey("xh||jddm");
        this.setTableName("XG_DTJS_XSDTXXJLB");
        this.setClass(DtxxjgForm.class);
    }

    /**
     * @return String 返回类型
     * @描述:获取基础sql
     * @作者：张昌路[工号：982]
     * @日期：2013-10-25 上午10:39:59
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public String getBaseSql() {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from (");
        sql.append(" select a.*,a.xh||a.jddm dtxxjgid,sqxx.splc,b.jdmc,xsxx.xm,xsxx.csrq,xsxx.mzmc,xsxx.xb,xsxx.nj,xsxx.xymc,xsxx.xydm,xsxx.zymc,xsxx.zydm,xsxx.bjmc,xsxx.bjdm,xsxx.zzmmmc");
        sql.append(" from XG_DTJS_XSDTXXJLB a");
        //阶段信息
        sql.append(" left join XG_DTJS_JBSZB b on a.jddm=b.jddm");
        //学生信息
        sql.append(" left join view_xsxxb xsxx on a.xh=xsxx.xh ");
        //只查询最大阶段（当前阶段）
        sql.append(" right join (select xh,max(t.jddm) maxjddm from XG_DTJS_XSDTXXJLB t group by xh)m on a.jddm=m.maxjddm and a.xh=m.xh");
        //获取对应审批流id
        sql.append(" left join XG_DTJS_DTXXSQ sqxx on a.sqid=sqxx.dtxxsqid");
        sql.append(" ) a where 1=1");
        return sql.toString();
    }

    @Override
    public DtxxjgForm getModel(DtxxjgForm t) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append(getBaseSql());
        sql.append(" and a.xh||a.jddm=?");
        HashMap<String, String> map = dao.getMapNotOut(sql.toString(),
                new String[]{t.getDtxxjgid()});
        DtxxjgForm df = new DtxxjgForm();
        BeanUtils.copyProperties(df, map);
        return df;
    }
    /*
          描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#runUpdate(java.lang.Object)
	 */

    @Override
    public boolean runUpdate(DtxxjgForm t) throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append("update XG_DTJS_XSDTXXJLB set kssj=?,sqid=?,grxj=?,zd1=?,zd2=?,zd3=?,zd5=?,zd8=?,zd9=?,zd10=? where xh||jddm=?");
        return dao.runUpdate(sb.toString(), new String[]{t.getKssj(), t.getSqid(), t.getGrxj(), t.getZd1(), t.getZd2(), t.getZd3(), t.getZd5(),
                t.getZd8(), t.getZd9(), t.getZd10(), t.getDtxxjgid()});
    }

    /**
     * @param model
     * @param user
     * @return
     * @throws Exception List<HashMap<String,String>> 返回类型
     * @throws
     * @描述:浙江商业获取预备党员名册信息
     * @作者：ChenQ[工号：856]
     * @日期：2015-6-25 上午09:56:59
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public List<HashMap<String, String>> getMcExportData(DtxxjgForm model, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(model.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
                "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(model.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append("select * from (select t1.xh, t4.kssj rdjjfssj, t2.zd8,t2.zd9, t2.zd10, ");
        sql.append(" t5.xm,t5.sfzh,t5.xb,t5.mzmc,t5.jgmc,bjmc,");
        sql.append(" t3.kssj rdsqsj from VIEW_NEW_DC_DTXX_JG t1 left join (select xh,zd8,zd9,zd10 ");
        sql.append(" from XG_DTJS_XSDTXXJLB where jddm in (select jddm from xg_dtjs_jbszb ");
        sql.append(" where jdmc = '党校结业时间')) t2 on t1.xh = t2.xh left join (select xh, kssj ");
        sql.append(" from XG_DTJS_XSDTXXJLB where jddm in (select jddm from xg_dtjs_jbszb ");
        sql.append(" where jdmc = '入党申请')) t3  on t1.xh = t3.xh left join (select xh, kssj ");
        sql.append("from XG_DTJS_XSDTXXJLB where jddm in (select jddm from xg_dtjs_jbszb ");
        sql.append(" where jdmc = '入党积极分子')) t4 on t1.xh = t4.xh left join view_xsxxb t5 ");
        sql.append(" on t1.xh=t5.xh where t1.jddm in  ");
        sql.append("  (select jddm from xg_dtjs_jbszb where jdmc = '发展对象')) a where 1=1 ");
        sql.append(searchTjByUser);
        sql.append(" ");
        sql.append(searchTj);
        return dao.getListNotOut(sql.toString(), inputV);
    }

    /**
     * @param model
     * @param user
     * @return
     * @throws Exception List<HashMap<String,String>> 返回类型
     * @throws
     * @描述:获取学生如职务，评奖结果，综测排名的信息
     * @作者：ChenQ[工号：856]
     * @日期：2015-6-25 上午09:58:32
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public List<HashMap<String, String>> getMcInfoData(DtxxjgForm model, User user) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("select m1.xh,m2.xmmc,m3.isbjg,m4.zwmc,m5.xqpm  from VIEW_NEW_DC_DTXX_JG m1 left join ( ");
        sql.append(" select xh,wm_concat(xn||'获得'||xmmc) xmmc from (select xn,xh,replace(wm_concat(xmmc),';',',')xmmc from (  ");
        sql.append(" select  xn,xh,case when num=1 then xmmc else xmmc||num||'次' end  ");
        sql.append(" xmmc from ( select xn,xh,xmmc,count(1) num from xg_pjpy_new_pjjgb group by xn,xh,xmmc order by xn)) ");
        sql.append(" group by xn,xh) group by xh ) m2 on m1.xh=m2.xh left join (select a.xh,case when b.num>0 then '是' else '否' ");
        sql.append(" end isbjg from view_xsbfxx a left join (select xh,count(1) num from  view_zhhcjb where cj<60  ");
        sql.append(" group by xh  ) b on a.xh=b.xh) m3 on m1.xh=m3.xh left join ( ");
        sql.append(" select a.xh,wm_concat(zwmc) zwmc from xg_szdw_xsgb_dwb a left join xg_szdw_xsgb_zwb b  ");
        sql.append(" on a.zwid = b.zwid where a.zzzt='1'  group by a.xh ) m4 on m1.xh=m4.xh ");
        sql.append(" left join ( select xh,replace (wm_concat(pm),';',',') xqpm from ( ");
        sql.append(" select a.xn,a.xq,a.xh,a.bjpm||'/'||nvl(n.num,0) pm from xg_zhcp_zcfsb a left join xg_zhcp_zcxmb b ");
        sql.append("  on a.xmdm=b.xmdm left join xg_pjpy_new_cpmdb m on a.xn=m.xn and a.xq=m.xq and a.xh=m.xh  ");
        sql.append(" left join ( select xn,xq,bjdm,count(1) num  from  xg_pjpy_new_cpmdb  group by  xn,xq,bjdm) n ");
        sql.append(" on n.bjdm=m.bjdm and n.xn=m.xn and m.xq=n.xq where b.fjdm='N') group by xh ) m5 on m1.xh=m5.xh ");
        sql.append("  where m1.jddm in  ");
        sql.append("  (select jddm from xg_dtjs_jbszb where jdmc = '发展对象') ");
        return dao.getListNotOut(sql.toString(), new String[]{});
    }

    /**
     * @param xydm
     * @return List<HashMap<String,String>> 返回类型
     * @throws
     * @描述: 重庆三峡医高专-预审发展对象
     * @作者：沈晓波[工号：1123]
     * @日期：2016-3-23 上午10:47:24
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public List<HashMap<String, String>> getDtxxYsfzList(String xymc) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" select rownum xsxh, a.* from (select t1.xh, ");
        sql.append(" (case when t2.kssj is null then t2.kssj else substr(t2.kssj, 0, 4) || '.' || substr(t2.kssj, 6, 2) || '.' || substr(t2.kssj, 9, 2) end) rdjjfzsj, ");
        sql.append(" (case when t3.kssj is null then t3.kssj else substr(t3.kssj, 0, 4) || '.' || substr(t3.kssj, 6, 2) || '.' || substr(t3.kssj, 9, 2) end) fzdxsj, ");
        sql.append(" t4.xm, t4.xydm, t4.xymc, t4.bjmc, t4.xb, ");
        sql.append(" (case when t4.csrq is null then t4.csrq else substr(t4.csrq, 0, 4) || '.' || substr(t4.csrq, 6, 2) end) csny, t4.jgmc, t5.pyccmc ");
        sql.append(" from XG_DTJS_XSDTXXJLB t1 ");
        sql.append(" left join (select xh, kssj ");
        sql.append(" from XG_DTJS_XSDTXXJLB ");
        sql.append(" where jddm in (select jddm ");
        sql.append(" from xg_dtjs_jbszb ");
        sql.append(" where jdmc = '入党积极分子')) t2 on t1.xh = t2.xh ");
        sql.append(" left join (select xh, kssj ");
        sql.append(" from XG_DTJS_XSDTXXJLB ");
        sql.append(" where jddm in (select jddm from xg_dtjs_jbszb where jdmc = '发展对象')) t3 ");
        sql.append(" on t1.xh = t3.xh ");
        sql.append(" left join view_xsxxb t4 ");
        sql.append(" on t1.xh = t4.xh ");
        sql.append(" left join XG_XSXX_PYCCDMB t5 on t4.pycc = t5.pyccdm ");
        sql.append(" where t1.jddm in (select jddm from xg_dtjs_jbszb where jdmc = '预审发展对象')) a where xymc = ? ");

        return dao.getListNotOut(sql.toString(), new String[]{xymc});

    }

    /**
     * @param xymc
     * @return
     * @throws Exception List<HashMap<String,String>> 返回类型
     * @throws
     * @描述: 重庆三峡医高专-预备党员
     * @作者：沈晓波[工号：1123]
     * @日期：2016-3-24 下午01:52:09
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public List<HashMap<String, String>> getDtxxYbdyList(String xymc) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" select rownum xsxh, a.* from (select t1.xh, ");
        sql.append(" (case when t2.kssj is null then t2.kssj else substr(t2.kssj, 0, 4) || '.' || substr(t2.kssj, 6, 2) || '.' || substr(t2.kssj, 9, 2) end) rdjjfzsj, ");
        sql.append(" (case when t3.kssj is null then t3.kssj else substr(t3.kssj, 0, 4) || '.' || substr(t3.kssj, 6, 2) || '.' || substr(t3.kssj, 9, 2) end) fzdxsj, ");
        sql.append(" t4.xm, t4.xydm, t4.xymc, t4.bjmc, t4.xb, ");
        sql.append(" (case when t4.csrq is null then t4.csrq else substr(t4.csrq, 0, 4) || '.' || substr(t4.csrq, 6, 2) end) csny, t4.jgmc ");
        sql.append(" from XG_DTJS_XSDTXXJLB t1 ");
        sql.append(" left join (select xh, kssj ");
        sql.append(" from XG_DTJS_XSDTXXJLB ");
        sql.append(" where jddm in (select jddm ");
        sql.append(" from xg_dtjs_jbszb ");
        sql.append(" where jdmc = '入党积极分子')) t2 on t1.xh = t2.xh ");
        sql.append(" left join (select xh, kssj ");
        sql.append(" from XG_DTJS_XSDTXXJLB ");
        sql.append(" where jddm in (select jddm from xg_dtjs_jbszb where jdmc = '发展对象')) t3 ");
        sql.append(" on t1.xh = t3.xh ");
        sql.append(" left join view_xsxxb t4 ");
        sql.append(" on t1.xh = t4.xh ");
        sql.append(" right join (select xh, max(t.jddm) maxjddm from XG_DTJS_XSDTXXJLB t group by xh) m on t1.jddm = m.maxjddm and t1.xh = m.xh ");
        sql.append(" where t1.jddm in (select jddm from xg_dtjs_jbszb where jdmc = '预备党员')) a where xymc = ? ");

        return dao.getListNotOut(sql.toString(), new String[]{xymc});

    }

    /**
     * @param xymc
     * @return
     * @throws Exception List<HashMap<String,String>> 返回类型
     * @throws
     * @描述: 重庆三峡医高专-预备党员转正（正式党员）
     * @作者：沈晓波[工号：1123]
     * @日期：2016-3-24 下午03:37:03
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public List<HashMap<String, String>> getDtxxZsdyList(String xymc) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" select rownum xsxh, a.* from (select t1.xh, ");
        sql.append(" (case when t2.kssj is null then t2.kssj else substr(t2.kssj, 0, 4) || '.' || substr(t2.kssj, 6, 2) || '.' || substr(t2.kssj, 9, 2) end) fzdxsj, ");
        sql.append(" t4.xm, t4.xydm, t4.xymc, t4.bjmc, t4.xb, ");
        sql.append(" (case when t4.csrq is null then t4.csrq else substr(t4.csrq, 0, 4) || '.' || substr(t4.csrq, 6, 2) end) csny, t4.jgmc, t5.pyccmc ");
        sql.append(" from XG_DTJS_XSDTXXJLB t1 ");
        sql.append(" left join (select xh, kssj ");
        sql.append(" from XG_DTJS_XSDTXXJLB ");
        sql.append(" where jddm in (select jddm from xg_dtjs_jbszb where jdmc = '发展对象')) t2 ");
        sql.append(" on t1.xh = t2.xh ");
        sql.append(" left join view_xsxxb t4 ");
        sql.append(" on t1.xh = t4.xh ");
        sql.append(" left join XG_XSXX_PYCCDMB t5 on t4.pycc = t5.pyccdm ");
        sql.append(" right join (select xh, max(t.jddm) maxjddm from XG_DTJS_XSDTXXJLB t group by xh) m on t1.jddm = m.maxjddm and t1.xh = m.xh ");
        sql.append(" where t1.jddm in (select jddm from xg_dtjs_jbszb where jdmc = '正式党员')) a where xymc = ? ");
        return dao.getListNotOut(sql.toString(), new String[]{xymc});
    }

    /**
     * @param t
     * @param user
     * @return
     * @throws Exception List<HashMap<String,String>> 返回类型
     * @描述：江西航空个性化导出
     * @作者：卓耐[工号:1391]
     * @日期：2017年4月6日
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public List<HashMap<String, String>> getExportList_13871(DtxxjgForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuffer sql = new StringBuffer();
        sql.append("select * from ( ");
        sql.append("select t1.xh,t1.xm,t1.xb,t1.mzmc,t1.jgmc,t1.zymc,t1.bjmc,t1.xymc,t1.csrq,t1.sfzh,t2.kssj rdsqsj,t3.kssj tysj, ");
        sql.append("t4.kssj rdjjfzsj,t5.kssj fzdxsj,t6.kssj ybdysj,t6.zd3 rdzysbh,t7.kssj zsdysj,'' n1,'' n2 from  ");
        sql.append("view_new_dc_dtxx_jg t1 ");
        sql.append("left join (select xh,kssj from XG_DTJS_XSDTXXJLB where jddm=(select max(jddm) from XG_DTJS_JBSZB where jdmc ='入党申请')) t2 ");
        sql.append("on t1.xh=t2.xh ");
        sql.append("left join (select xh,kssj from XG_DTJS_XSDTXXJLB where jddm=(select max(jddm) from XG_DTJS_JBSZB where jdmc ='推优')) t3 ");
        sql.append("on t1.xh=t3.xh ");
        sql.append("left join (select xh,kssj from XG_DTJS_XSDTXXJLB where jddm=(select max(jddm) from XG_DTJS_JBSZB where jdmc ='入党积极分子')) t4 ");
        sql.append("on t1.xh=t4.xh ");
        sql.append("left join (select xh,kssj from XG_DTJS_XSDTXXJLB where jddm=(select max(jddm) from XG_DTJS_JBSZB where jdmc ='发展对象')) t5 ");
        sql.append("on t1.xh=t5.xh ");
        sql.append("left join (select xh,kssj,zd3 from XG_DTJS_XSDTXXJLB where jddm=(select max(jddm) from XG_DTJS_JBSZB where jdmc ='预备党员')) t6 ");
        sql.append("on t1.xh=t6.xh ");
        sql.append("left join (select xh,kssj from XG_DTJS_XSDTXXJLB where jddm=(select max(jddm) from XG_DTJS_JBSZB where jdmc ='正式党员')) t7 ");
        sql.append("on t1.xh=t7.xh ");
        sql.append(")t where 1=1 ");
        sql.append(searchTjByUser);
        sql.append(" ");
        sql.append(searchTj);
        return this.getPageList(t, sql.toString(), inputV);
    }

    /**
    * @description: TODO 积极分子备案登记
    * @param t
	* @param user
    * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
    * @author Wang ChenHui
    * @date 2019/5/24 15:10
    */
    public List<HashMap<String, String>> getDtxxJjfzList(DtxxjgForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuffer sql = new StringBuffer();
        sql.append("select * from (");
        sql.append("select t1.*,case when ceil(To_date(t1.sqrdsj, 'yyyy-mm-dd') - To_date(t1.CSRQ, 'yyyy-mm-dd'))/365>18 then '√' else 'X' end sqnj ");
        sql.append("from ");
        sql.append("(select ROWNUM id,'' dzbmc,b.BJMC,b.XM,b.XB,b.MZMC,b.CSRQ,b.JGMC,d.XLMC,c.XW,c.ZW,c.RXRQ, a.jddm, ");
        sql.append(" (select KSSJ from XG_DTJS_XSDTXXJLB xdx where xdx.JDDM = '03' and xdx.xh =a.xh ) sqrdsj,");
        sql.append("'' dzbthsj,'' qrjjfzsj,'' pylxr1,'' pylxr2 ");
        sql.append("from XG_DTJS_XSDTXXJLB a ");
        sql.append("left join VIEW_XSXXB b on a.XH = b.XH ");
        sql.append("left join XSXXB c on a.XH = c.XH ");
        sql.append("left join XLDMB d on d.XLDM = c.XLDM)t1 ");
        sql.append(")t where 1=1 and t.jddm = '04'");
        sql.append(searchTjByUser);
        sql.append(" ");
        sql.append(searchTj);
        return this.getPageList(t, sql.toString(), inputV);
    }

    /**
    * @description: TODO 发展对象备案登记
    * @param t
	* @param user
    * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
    * @author Wang ChenHui
    * @date 2019/5/24 15:10
    */
    public List<HashMap<String, String>> getDtxxFzdxList(DtxxjgForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuffer sql = new StringBuffer();
        sql.append("select * from (");
        sql.append("select ROWNUM id,'' dzbmc,b.BJMC,b.XM,b.XB,b.MZMC,b.CSRQ,b.JGMC,d.XLMC,c.XW,c.ZW,c.RXRQ,a.JDDM, ");
        sql.append("(select KSSJ from XG_DTJS_XSDTXXJLB xdx where xdx.JDDM = '03' and xdx.xh =a.xh ) sqrdsj, ");
        sql.append("(select KSSJ from XG_DTJS_XSDTXXJLB xdx where xdx.JDDM = '04' and xdx.xh =a.xh ) jjfzsj, ");
        sql.append("'' fzdxsj,'' rdjsr1,'' rdjsr2 ");
        sql.append("from XG_DTJS_XSDTXXJLB a ");
        sql.append("left join VIEW_XSXXB b on a.XH = b.XH ");
        sql.append("left join XSXXB c on a.XH = c.XH ");
        sql.append("left join XLDMB d on d.XLDM = c.XLDM ");
        sql.append(")t where 1=1 and t.jddm = '06'");
        sql.append(searchTjByUser);
        sql.append(" ");
        sql.append(searchTj);
        return this.getPageList(t, sql.toString(), inputV);
    }

    /**
    * @description: TODO 预备党员接收登记
    * @param t
	* @param user
    * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
    * @author Wang ChenHui
    * @date 2019/5/24 15:09
    */
    public List<HashMap<String, String>> getYbdyList(DtxxjgForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuffer sql = new StringBuffer();
        sql.append("select * from (");
        sql.append("select t1.*,case when ceil(To_date(to_char(sysdate,'yyyy-mm-dd'), 'yyyy-mm-dd') - To_date(t1.CSRQ, 'yyyy-mm-dd'))/365>18 then '√' else 'X' end nj ");
        sql.append("from (select ROWNUM id,'' dzbmc,b.BJMC,b.XM,b.XB,b.MZMC,b.CSRQ,b.JGMC,a.JDDM, ");
        sql.append("'' qrfzdxsj, ");
        sql.append("a.XH,c.SFZH,'' rdsj,'' fdwyfspsj ,a.GRXJ ");
        sql.append("from XG_DTJS_XSDTXXJLB a ");
        sql.append("left join VIEW_XSXXB b on a.XH = b.XH ");
        sql.append("left join XSXXB c on a.XH = c.XH ");
        sql.append("left join XLDMB d on d.XLDM = c.XLDM)t1 ");
        sql.append(")t where 1=1 and t.jddm ='07' ");
        sql.append(searchTjByUser);
        sql.append(" ");
        sql.append(searchTj);
        return this.getPageList(t, sql.toString(), inputV);
    }

    /**
    * @description: TODO 预备党员转正请示列表
    * @param t
	* @param user
    * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
    * @author Wang ChenHui
    * @date 2019/5/24 15:02
    */
    public List<HashMap<String, String>> getZsdyList(DtxxjgForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuffer sql = new StringBuffer();
        sql.append("select * from (");
        sql.append("select ROWNUM id,'' dzbmc,b.BJMC,b.XM,b.XB,b.MZMC,b.CSRQ,b.JGMC,a.JDDM, ");
        sql.append("(select KSSJ from XG_DTJS_XSDTXXJLB xdx where xdx.JDDM = '08' and xdx.xh =a.xh ) ybdysj, ");
        sql.append("'' zzsj,a.XH,c.SFZH,a.GRXJ ");
        sql.append("from XG_DTJS_XSDTXXJLB a ");
        sql.append("left join VIEW_XSXXB b on a.XH = b.XH ");
        sql.append("left join XSXXB c on a.XH = c.XH ");
        sql.append("left join XLDMB d on d.XLDM = c.XLDM ");
        sql.append(")t where 1=1 and t.jddm = '08' ");
        sql.append(searchTjByUser);
        sql.append(" ");
        sql.append(searchTj);
        return this.getPageList(t, sql.toString(), inputV);
    }
}
