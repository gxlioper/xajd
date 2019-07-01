package xgxt.xszz.tjgy;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class XxcjForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();
	
	private String doType;
	
	private String xh;//学号
	
	private String xn;//学年
	
	private String xq;//学期
	
	//信息采集第一部分
	private String xf;//学费
	
	private String lszn;//烈士子女
	
	private String ge;//孤儿
	
	private String db;//低保
	
	private String tkjz;//特困救助
	
	private String yf;//优扶
	
	private String cjxs;//残疾学生
	
	//信息采集第二部分
	private String dbsx;//东部市县
	
	private String dbcz;//东部村镇
	
	private String dbpk;//东部贫困
	
	private String zbsx;//中部市县
	
	private String zbcz;//中部村镇
	
	private String zbpk;//中部贫困 
	
	private String xbsx;//西部市县
	
	private String xbcz;//西部村镇
	
	private String xbpk;//西部贫困
	
	//信息采集第三部分
	private String sqjt;//双亲家庭
	
	private String dqlfy;//单亲离父养
	
	private String dqlmy;//单亲离母养
	
	private String dqlqt;//单亲离其它
	
	private String dqwfy;//单亲亡父养
	
	private String dqwmy;//单亲亡母养
	
	private String dqwqt;//单亲亡其它
	
	private String fyfqt;//抚养费其它
	
	//信息采集第四部分
	private String fmwn;//父母务农
	
	private String fgmn;//父工母务
	
	private String mgfn;//母工父农
	
	private String fmgz;//父母工作
	
	private String fgmxg;//父工母下岗
	
	private String mgfxg;//母工父下岗
	
	private String fmxg;//父母下岗
	
	private String fmxgyfjy;//父母下岗一方就业
	
	private String jtjjqt;//家庭经济其它
	
	//信息采集第五部分
	private String fmstlh;//父母身体良好 
	
	private String fqstcpc;//父亲身体偏差
	
	private String dqfqstpc;//单亲父亲身体偏差
	
	private String mqstpc;//母亲身体偏差
	
	private String dqmqstpc;//单亲母亲身体偏差
	
	private String fqstjc;//父亲身体极差
	
	private String dqfqstjc;//单亲父亲身体极差
	
	private String mqstjc;//母亲身体极差
	
	private String dqmqstjc;//单亲母亲身体极差
	
	private String fmcj;//父母残疾
	
	private String fcj;//父残疾
	
	private String dqfc;//单亲父残
	
	private String mcj;//母残疾
	
	private String dqmc;//单亲母残
	
	//信息采集第六部分
	private String fwsx;//父50以下
	
	private String fwls;//父51-60
	
	private String flqs;//父61-70
	
	private String qsys;//父71以上
	
	private String mswyx;//母45以下
	
	private String msww;//母45-55
	
	private String mwlw;//母56-65
	
	private String mlls;//母66以上
	
	//信息采集第七部分
	private String sylrs;//赡养老人数
	
	private String lrsth;//老人身体良好
	
	private String lrstc;//老人身体差
	
	//信息采集第八部分
	private String gjzx;//哥姐在学
	
	private String gjwn;//哥姐务农
	
	private String gjdg;//哥姐打工
	
	private String gjgz;//哥姐工作
	
	private String gjstc;//哥姐身体差
	
	private String gjqt;//哥姐其它
	
	//信息采集第九部分
	private String dmcx;//弟妹缀学
	
	private String dmzxx;//弟妹中小学
	
	private String dmgz;//弟妹高中
	
	private String dmdx;//弟妹大学
	
	private String dmstc;//弟妹身体差
	
	private String dmcj;//弟妹残疾
	
	private String dmqt;//弟妹其它情况
	
	//信息采集第十部分
	private String zrzh;//自然灾害
	
	private String zdjb;//重大疾病
	
	private String tfsj;//突发事件
	
	private String qzqk;//欠债情况
	
	private String y1;
	
	private String y2;
	
	private String y3;
	
	private String y4;
	
	private String y5;
	
	private String y6;
	
	private String y7;
	
	private String y8;
	
	private String y9;
	
	private String z;
	
	private String querylike_xh;
	
	private String querylike_xm;
	
	private String queryequals_nj;
	
	private String queryequals_xydm;
	
	private String queryequals_zydm;
	
	private String queryequals_bjdm;
	
	private String queryequals_xn;
	
	private String queryequals_xq;
	
	private String queryequals_xysh;
	
	private String queryequals_xxsh;
	
	private String shyj;
	
	private String shzt;
	
	private String xyshr;
	
	private String xyshsj;
	
	private String xyshyj;
	
	private String xxshr;
	
	private String xxshsj;
	
	private String xxshyj;
	
	private String xysh;
	
	private String xxsh;
	
	private String ywgzqk;//有无工作情况
	
	private String ycount;//有效y值个数，为了方便计算，默认为1
	
	public String getXxsh() {
		return xxsh;
	}

	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}

	public String getXysh() {
		return xysh;
	}

	public void setXysh(String xysh) {
		this.xysh = xysh;
	}

	public String getShyj() {
		return shyj;
	}

	public void setShyj(String shyj) {
		this.shyj = shyj;
	}

	public String getQueryequals_bjdm() {
		return queryequals_bjdm;
	}

	public void setQueryequals_bjdm(String queryequals_bjdm) {
		this.queryequals_bjdm = queryequals_bjdm;
	}

	public String getQueryequals_nj() {
		return queryequals_nj;
	}

	public void setQueryequals_nj(String queryequals_nj) {
		this.queryequals_nj = queryequals_nj;
	}

	public String getQueryequals_xn() {
		return queryequals_xn;
	}

	public void setQueryequals_xn(String queryequals_xn) {
		this.queryequals_xn = queryequals_xn;
	}

	public String getQueryequals_xq() {
		return queryequals_xq;
	}

	public void setQueryequals_xq(String queryequals_xq) {
		this.queryequals_xq = queryequals_xq;
	}

	public String getQueryequals_xydm() {
		return queryequals_xydm;
	}

	public void setQueryequals_xydm(String queryequals_xydm) {
		this.queryequals_xydm = queryequals_xydm;
	}

	public String getQueryequals_zydm() {
		return queryequals_zydm;
	}

	public void setQueryequals_zydm(String queryequals_zydm) {
		this.queryequals_zydm = queryequals_zydm;
	}

	public String getQuerylike_xh() {
		return querylike_xh;
	}

	public void setQuerylike_xh(String querylike_xh) {
		this.querylike_xh = querylike_xh;
	}

	public String getQuerylike_xm() {
		return querylike_xm;
	}

	public void setQuerylike_xm(String querylike_xm) {
		this.querylike_xm = querylike_xm;
	}

	public String getCjxs() {
		return cjxs;
	}

	public void setCjxs(String cjxs) {
		this.cjxs = cjxs;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public String getDbcz() {
		return dbcz;
	}

	public void setDbcz(String dbcz) {
		this.dbcz = dbcz;
	}

	public String getDbpk() {
		return dbpk;
	}

	public void setDbpk(String dbpk) {
		this.dbpk = dbpk;
	}

	public String getDbsx() {
		return dbsx;
	}

	public void setDbsx(String dbsx) {
		this.dbsx = dbsx;
	}

	public String getDmcj() {
		return dmcj;
	}

	public void setDmcj(String dmcj) {
		this.dmcj = dmcj;
	}

	public String getDmcx() {
		return dmcx;
	}

	public void setDmcx(String dmcx) {
		this.dmcx = dmcx;
	}

	public String getDmdx() {
		return dmdx;
	}

	public void setDmdx(String dmdx) {
		this.dmdx = dmdx;
	}

	public String getDmgz() {
		return dmgz;
	}

	public void setDmgz(String dmgz) {
		this.dmgz = dmgz;
	}

	public String getDmqt() {
		return dmqt;
	}

	public void setDmqt(String dmqt) {
		this.dmqt = dmqt;
	}

	public String getDmstc() {
		return dmstc;
	}

	public void setDmstc(String dmstc) {
		this.dmstc = dmstc;
	}

	public String getDmzxx() {
		return dmzxx;
	}

	public void setDmzxx(String dmzxx) {
		this.dmzxx = dmzxx;
	}

	public String getDqfc() {
		return dqfc;
	}

	public void setDqfc(String dqfc) {
		this.dqfc = dqfc;
	}

	public String getDqfqstjc() {
		return dqfqstjc;
	}

	public void setDqfqstjc(String dqfqstjc) {
		this.dqfqstjc = dqfqstjc;
	}

	public String getDqlfy() {
		return dqlfy;
	}

	public void setDqlfy(String dqlfy) {
		this.dqlfy = dqlfy;
	}

	public String getDqlmy() {
		return dqlmy;
	}

	public void setDqlmy(String dqlmy) {
		this.dqlmy = dqlmy;
	}

	public String getDqlqt() {
		return dqlqt;
	}

	public void setDqlqt(String dqlqt) {
		this.dqlqt = dqlqt;
	}

	public String getDqmc() {
		return dqmc;
	}

	public void setDqmc(String dqmc) {
		this.dqmc = dqmc;
	}

	public String getDqmqstjc() {
		return dqmqstjc;
	}

	public void setDqmqstjc(String dqmqstjc) {
		this.dqmqstjc = dqmqstjc;
	}

	public String getDqmqstpc() {
		return dqmqstpc;
	}

	public void setDqmqstpc(String dqmqstpc) {
		this.dqmqstpc = dqmqstpc;
	}

	public String getDqwfy() {
		return dqwfy;
	}

	public void setDqwfy(String dqwfy) {
		this.dqwfy = dqwfy;
	}

	public String getDqwmy() {
		return dqwmy;
	}

	public void setDqwmy(String dqwmy) {
		this.dqwmy = dqwmy;
	}

	public String getDqwqt() {
		return dqwqt;
	}

	public void setDqwqt(String dqwqt) {
		this.dqwqt = dqwqt;
	}

	public String getFcj() {
		return fcj;
	}

	public void setFcj(String fcj) {
		this.fcj = fcj;
	}

	public String getFgmn() {
		return fgmn;
	}

	public void setFgmn(String fgmn) {
		this.fgmn = fgmn;
	}

	public String getFgmxg() {
		return fgmxg;
	}

	public void setFgmxg(String fgmxg) {
		this.fgmxg = fgmxg;
	}

	public String getFlqs() {
		return flqs;
	}

	public void setFlqs(String flqs) {
		this.flqs = flqs;
	}

	public String getFmcj() {
		return fmcj;
	}

	public void setFmcj(String fmcj) {
		this.fmcj = fmcj;
	}

	public String getFmgz() {
		return fmgz;
	}

	public void setFmgz(String fmgz) {
		this.fmgz = fmgz;
	}

	public String getFmstlh() {
		return fmstlh;
	}

	public void setFmstlh(String fmstlh) {
		this.fmstlh = fmstlh;
	}

	public String getFmwn() {
		return fmwn;
	}

	public void setFmwn(String fmwn) {
		this.fmwn = fmwn;
	}

	public String getFmxg() {
		return fmxg;
	}

	public void setFmxg(String fmxg) {
		this.fmxg = fmxg;
	}

	public String getFmxgyfjy() {
		return fmxgyfjy;
	}

	public void setFmxgyfjy(String fmxgyfjy) {
		this.fmxgyfjy = fmxgyfjy;
	}


	public String getFqstcpc() {
		return fqstcpc;
	}

	public void setFqstcpc(String fqstcpc) {
		this.fqstcpc = fqstcpc;
	}

	public String getFqstjc() {
		return fqstjc;
	}

	public void setFqstjc(String fqstjc) {
		this.fqstjc = fqstjc;
	}

	public String getFwls() {
		return fwls;
	}

	public void setFwls(String fwls) {
		this.fwls = fwls;
	}

	public String getFwsx() {
		return fwsx;
	}

	public void setFwsx(String fwsx) {
		this.fwsx = fwsx;
	}

	public String getFyfqt() {
		return fyfqt;
	}

	public void setFyfqt(String fyfqt) {
		this.fyfqt = fyfqt;
	}

	public String getGe() {
		return ge;
	}

	public void setGe(String ge) {
		this.ge = ge;
	}

	public String getGjdg() {
		return gjdg;
	}

	public void setGjdg(String gjdg) {
		this.gjdg = gjdg;
	}

	public String getGjgz() {
		return gjgz;
	}

	public void setGjgz(String gjgz) {
		this.gjgz = gjgz;
	}

	public String getGjqt() {
		return gjqt;
	}

	public void setGjqt(String gjqt) {
		this.gjqt = gjqt;
	}

	public String getGjstc() {
		return gjstc;
	}

	public void setGjstc(String gjstc) {
		this.gjstc = gjstc;
	}

	public String getGjwn() {
		return gjwn;
	}

	public void setGjwn(String gjwn) {
		this.gjwn = gjwn;
	}

	public String getGjzx() {
		return gjzx;
	}

	public void setGjzx(String gjzx) {
		this.gjzx = gjzx;
	}

	public String getJtjjqt() {
		return jtjjqt;
	}

	public void setJtjjqt(String jtjjqt) {
		this.jtjjqt = jtjjqt;
	}

	public String getLrstc() {
		return lrstc;
	}

	public void setLrstc(String lrstc) {
		this.lrstc = lrstc;
	}

	public String getLrsth() {
		return lrsth;
	}

	public void setLrsth(String lrsth) {
		this.lrsth = lrsth;
	}

	public String getLszn() {
		return lszn;
	}

	public void setLszn(String lszn) {
		this.lszn = lszn;
	}

	public String getMcj() {
		return mcj;
	}

	public void setMcj(String mcj) {
		this.mcj = mcj;
	}

	public String getMgfn() {
		return mgfn;
	}

	public void setMgfn(String mgfn) {
		this.mgfn = mgfn;
	}

	public String getMgfxg() {
		return mgfxg;
	}

	public void setMgfxg(String mgfxg) {
		this.mgfxg = mgfxg;
	}

	public String getMlls() {
		return mlls;
	}

	public void setMlls(String mlls) {
		this.mlls = mlls;
	}

	public String getMqstjc() {
		return mqstjc;
	}

	public void setMqstjc(String mqstjc) {
		this.mqstjc = mqstjc;
	}

	public String getMqstpc() {
		return mqstpc;
	}

	public void setMqstpc(String mqstpc) {
		this.mqstpc = mqstpc;
	}

	public String getMsww() {
		return msww;
	}

	public void setMsww(String msww) {
		this.msww = msww;
	}

	public String getMswyx() {
		return mswyx;
	}

	public void setMswyx(String mswyx) {
		this.mswyx = mswyx;
	}

	public String getMwlw() {
		return mwlw;
	}

	public void setMwlw(String mwlw) {
		this.mwlw = mwlw;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getQsys() {
		return qsys;
	}

	public void setQsys(String qsys) {
		this.qsys = qsys;
	}

	public String getQzqk() {
		return qzqk;
	}

	public void setQzqk(String qzqk) {
		this.qzqk = qzqk;
	}

	public String getSqjt() {
		return sqjt;
	}

	public void setSqjt(String sqjt) {
		this.sqjt = sqjt;
	}

	public String getSylrs() {
		return sylrs;
	}

	public void setSylrs(String sylrs) {
		this.sylrs = sylrs;
	}

	public String getTfsj() {
		return tfsj;
	}

	public void setTfsj(String tfsj) {
		this.tfsj = tfsj;
	}

	public String getTkjz() {
		return tkjz;
	}

	public void setTkjz(String tkjz) {
		this.tkjz = tkjz;
	}

	public String getXbcz() {
		return xbcz;
	}

	public void setXbcz(String xbcz) {
		this.xbcz = xbcz;
	}

	public String getXbpk() {
		return xbpk;
	}

	public void setXbpk(String xbpk) {
		this.xbpk = xbpk;
	}

	public String getXbsx() {
		return xbsx;
	}

	public void setXbsx(String xbsx) {
		this.xbsx = xbsx;
	}

	public String getXf() {
		return xf;
	}

	public void setXf(String xf) {
		this.xf = xf;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getYf() {
		return yf;
	}

	public void setYf(String yf) {
		this.yf = yf;
	}

	public String getZbcz() {
		return zbcz;
	}

	public void setZbcz(String zbcz) {
		this.zbcz = zbcz;
	}

	public String getZbpk() {
		return zbpk;
	}

	public void setZbpk(String zbpk) {
		this.zbpk = zbpk;
	}

	public String getZbsx() {
		return zbsx;
	}

	public void setZbsx(String zbsx) {
		this.zbsx = zbsx;
	}

	public String getZdjb() {
		return zdjb;
	}

	public void setZdjb(String zdjb) {
		this.zdjb = zdjb;
	}

	public String getZrzh() {
		return zrzh;
	}

	public void setZrzh(String zrzh) {
		this.zrzh = zrzh;
	}

	public String getDqfqstpc() {
		return dqfqstpc;
	}

	public void setDqfqstpc(String dqfqstpc) {
		this.dqfqstpc = dqfqstpc;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String getDoType() {
		return doType;
	}

	public void setDoType(String doType) {
		this.doType = doType;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getXxshr() {
		return xxshr;
	}

	public void setXxshr(String xxshr) {
		this.xxshr = xxshr;
	}

	public String getXxshsj() {
		return xxshsj;
	}

	public void setXxshsj(String xxshsj) {
		this.xxshsj = xxshsj;
	}

	public String getXxshyj() {
		return xxshyj;
	}

	public void setXxshyj(String xxshyj) {
		this.xxshyj = xxshyj;
	}

	public String getXyshr() {
		return xyshr;
	}

	public void setXyshr(String xyshr) {
		this.xyshr = xyshr;
	}

	public String getXyshsj() {
		return xyshsj;
	}

	public void setXyshsj(String xyshsj) {
		this.xyshsj = xyshsj;
	}

	public String getXyshyj() {
		return xyshyj;
	}

	public void setXyshyj(String xyshyj) {
		this.xyshyj = xyshyj;
	}

	public String getY1() {
		return y1;
	}

	public void setY1(String y1) {
		this.y1 = y1;
	}

	public String getY2() {
		return y2;
	}

	public void setY2(String y2) {
		this.y2 = y2;
	}

	public String getY3() {
		return y3;
	}

	public void setY3(String y3) {
		this.y3 = y3;
	}

	public String getY4() {
		return y4;
	}

	public void setY4(String y4) {
		this.y4 = y4;
	}

	public String getY5() {
		return y5;
	}

	public void setY5(String y5) {
		this.y5 = y5;
	}

	public String getY6() {
		return y6;
	}

	public void setY6(String y6) {
		this.y6 = y6;
	}

	public String getY7() {
		return y7;
	}

	public void setY7(String y7) {
		this.y7 = y7;
	}

	public String getY8() {
		return y8;
	}

	public void setY8(String y8) {
		this.y8 = y8;
	}

	public String getY9() {
		return y9;
	}

	public void setY9(String y9) {
		this.y9 = y9;
	}

	public String getZ() {
		return z;
	}

	public void setZ(String z) {
		this.z = z;
	}

	public String getQueryequals_xxsh() {
		return queryequals_xxsh;
	}

	public void setQueryequals_xxsh(String queryequals_xxsh) {
		this.queryequals_xxsh = queryequals_xxsh;
	}

	public String getQueryequals_xysh() {
		return queryequals_xysh;
	}

	public void setQueryequals_xysh(String queryequals_xysh) {
		this.queryequals_xysh = queryequals_xysh;
	}

	public String getYwgzqk() {
		return ywgzqk;
	}

	public void setYwgzqk(String ywgzqk) {
		this.ywgzqk = ywgzqk;
	}

	public String getYcount() {
		return ycount;
	}

	public void setYcount(String ycount) {
		this.ycount = ycount;
	}
	
}
