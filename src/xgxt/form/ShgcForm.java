package xgxt.form;
import org.apache.struts.action.*;
import org.apache.struts.upload.FormFile;

import xgxt.base.DealString;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
public class ShgcForm extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SearchModel searchModel = new SearchModel();
	//DealString deal = new DealString();
	private String gdzldm;
	
	private String daqx;
	private String errMsg;
	private String[] errMsgs;
	private String xh = "";
	private String xm = "";
	private String xb = "1";
	private String xz;
	private String xydm = "";
	private String xymc = "";
	private String zydm = "";
	private String zymc = "";
	private String bjdm = "";
	private String bjmc = "";
	private String jtdh = "";//��ͥ�绰
	private String sjh = "";//�ֻ���
	private String bzxh = "";//�೤ѧ��
	private String bzxm = "";//�೤����
	private String bzrxm = "";//����������
	private String hzsqly = "";//����������������
	private String sfbk = "";//�Ƿ񲹿�
	private String nd = "";
	private String xn = "";
	private String xq = "";
	private String nj ="";
	private String shjg="";//��˽��
	private String ygzdwmc="";
	private String ygzdwlxr="";
	private String ygzdwlxdh="";
	private String xgzdwmc="";
	private String xgzdwdz="";
	private String qy="";
	private String yzbm="";
	private String xgzdwlxr="";
	private String xgzdwbm="";
	private String xgzdwlxdh="";
	private String xgzdwsjh="";
	private String sqly = "";
	private String sfzh = "";
	private String zdyy;//ת��ԭ��
	private String zddwmc = "";//ת����λ����
	private String zddwdz = "";//ת����λ��ַ	
	private String zysj = "";//ת��ʱ��
	private String dasfzx;//�����Ƿ���У
	private String hzh;//��ִ��
	private String sqrq;//��������
	private String lxfs;//��ϵ��ʽ
	private String hkssqx;//������������
	private String hkssjd;//���������ֵ�
	private String hkxxdz;//������ϸ��ַ
	private String zddwyb;//ת����λ�ʱ�
	private String dazcsj;//����ת��ʱ��
	private String rxsj;//��Уʱ��
	private String bysj;//��ҵʱ��	
	private String xysh;//ѧԺ���
	private String xxsh;//ѧУ���
	private String gzdasfzx;//���е����Ƿ���У
	private String csrq;//��������
	private String zdfs;//ת����ʽ
	private String mz;//����
	private String lxdh;//��ϵ�绰
	private String dah;//������
	private String jsr;//������
	private String glr;//������
	private String jsnr;//��������
	private String dabcwz;//��������λ��
	private String bynf;//��ҵ���
	private String daly;//������Դ
	private String sfydycl;//�Ƿ��е�Ա����
	private String bz;//��ע
	private String mzmc;//��������
	private String lddm;
	private String xmdm;
	private String KShdsj;
	private String JShdsj;
	private String sfsh;
	private String shzt;
	private String [] xhArray;
	private String [] xmArray;
	private String [] xnArray;
	private String [] xqArray;
	private String [] cjArray;
	private String [] xtyxb;
	private String zlbmc;
	private String sftj;
	private String gkcj;//�߿��ɼ�
	private String gkyycj;//�߿�Ӣ��ɼ�
	private String dxyysp;//��ѧӢ��ˮƽ
	private String jsjsp;//�����ˮƽ
	private String jg;//����
	private String dkjl;//�����¼
	private String snbjpm;//����༶����
	private String gwxz;//��λ����
	private String zzmm;//������ò
	private String zzmmmc;//������ò
	private String syd;//��Դ�أ�ѧ����Դ�أ�
	private String wjsj; //Υ��ʱ��
	private String shsj;//���ʱ��

	private String zc; //������
	private String cxryqd; //��ϯ��Աǩ��
	private String cxrs; //��ϯ����
	private String hyjl; //�����¼
	private String jlr; //��¼��
	private String rq; //����
	
	private String cfyy;//����ԭ�����
	private String cflbmc;//�����������
	private String cfyymc;//����ԭ������
	private String cfhbx;//���ֺ����
	private String jcjl;//���ͼ�¼
	private String bzryj;//���������
	private String xyyj;//ѧԺ���
	private String kbbmyj;//��첿�����
	private String xscyj;//ѧ�������
	private String xxyj; //ѧУ���
	private String cclb;//�������
	private String yrdwdm;//���˵�λ����
	private String cfyj;//��������
	FormFile uploadFile;
	private String cflb;
	private String xxmc;
	private String wjh;
	private String dazcfs;
	private String sfyhz;
	private String hztp;
	private String jyh;   //��Ҫ��
	private String zdlb;
	private String daclr; //����������
	private String daclrlxfs; //������������ϵ��ʽ
	private String xgcyj;//ѧ�������
	private String dwxz; //��λ����
	private String xl; //ѧ��
	private String zdqk; //�ڵ����
	private String byny; //��ҵ����
	private String kssj; //��ʼʱ��
	private String jssj; //����ʱ��
	private String []dadj;//�����Ǽ�
//	private String sfydycl;
	private String zlsfqq; //�����Ƿ���ȫ
	private String dabh;
	private String sjr;
	private String sjjgmc;
	private String sjsj;
	private String xndabh;
	private String djr;	
	private String dayjr;
	
	private String ksnf;
	private String jsnf;
	private String ksxn;
	private String jsxn;
	
	//�Ƿ��֪ѧ��
	private String sfgzxs;
	
	public String getSfgzxs() {
		return sfgzxs;
	}
	public void setSfgzxs(String sfgzxs) {
		this.sfgzxs = sfgzxs;
	}
	public String getJsxn() {
		return jsxn;
	}
	public void setJsxn(String jsxn) {
		this.jsxn = jsxn;
	}
	public String getKsxn() {
		return ksxn;
	}
	public void setKsxn(String ksxn) {
		this.ksxn = ksxn;
	}
	public String getJsnf() {
		return jsnf;
	}
	public void setJsnf(String jsnf) {
		this.jsnf = jsnf;
	}
	public String getKsnf() {
		return ksnf;
	}
	public void setKsnf(String ksnf) {
		this.ksnf = ksnf;
	}
	public String[] getDadj() {
		return dadj;
	}
	public void setDadj(String[] dadj) {
		this.dadj = dadj;
	}
	public String getZdqk() {
		return zdqk;
	}
	public void setZdqk(String zdqk) {
		this.zdqk = zdqk;
	}
	public String getDwxz() {
		return dwxz;
	}
	public void setDwxz(String dwxz) {
		this.dwxz = dwxz;
	}
	public String getXl() {
		return xl;
	}
	public void setXl(String xl) {
		this.xl = xl;
	}
	public String getXgcyj() {
		return xgcyj;
	}
	public void setXgcyj(String xgcyj) {
		this.xgcyj = xgcyj;
	}
	public String getCflb() {
		return cflb;
	}
	public void setCflb(String cflb) {
		this.cflb = cflb;
	}

	public FormFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getCfyj() {
		return cfyj;
	}

	public void setCfyj(String cfyj) {
		this.cfyj = cfyj;
	}

	public String getZzmm() {
		return zzmm;
	}

	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}

	public String getZzmmmc() {
		return zzmmmc;
	}
	public void setZzmmmc(String zzmmmc) {
		this.zzmmmc = zzmmmc;
	}
	public String getGwxz() {
		return gwxz;
	}

	public void setGwxz(String gwxz) {
		this.gwxz = gwxz;
	}

	public String getDkjl() {
		return dkjl;
	}

	public void setDkjl(String dkjl) {
		this.dkjl = dkjl;
	}

	public String getDxyysp() {
		return dxyysp;
	}

	public void setDxyysp(String dxyysp) {
		this.dxyysp = dxyysp;
	}

	public String getGkcj() {
		return gkcj;
	}

	public void setGkcj(String gkcj) {
		this.gkcj = gkcj;
	}

	public String getGkyycj() {
		return gkyycj;
	}

	public void setGkyycj(String gkyycj) {
		this.gkyycj = gkyycj;
	}

	public String getJg() {
		return jg;
	}

	public void setJg(String jg) {
		this.jg = jg;
	}

	public String getJsjsp() {
		return jsjsp;
	}

	public void setJsjsp(String jsjsp) {
		this.jsjsp = jsjsp;
	}

	public String getSnbjpm() {
		return snbjpm;
	}

	public void setSnbjpm(String snbjpm) {
		this.snbjpm = snbjpm;
	}

	public String getSftj() {
		return sftj;
	}

	public void setSftj(String sftj) {
		this.sftj = sftj;
	}

	public String getZlbmc() {
		return zlbmc;
	}

	public void setZlbmc(String zlbmc) {
		this.zlbmc = zlbmc;
	}

	public String[] getXtyxb() {
		return xtyxb;
	}

	public void setXtyxb(String[] xtyxb) {
		this.xtyxb = xtyxb;
	}

	public String getSfsh() {
		return sfsh;
	}

	public void setSfsh(String sfsh) {
		this.sfsh = sfsh;
	}

	public String getJShdsj() {
		return JShdsj;
	}

	public void setJShdsj(String shdsj) {
		JShdsj = shdsj;
	}

	public String getKShdsj() {
		return KShdsj;
	}

	public void setKShdsj(String shdsj) {
		KShdsj = shdsj;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String getLddm() {
		return lddm;
	}

	public void setLddm(String lddm) {
		this.lddm = lddm;
	}

	public void setZddwmc(String zddwmc){
		this.zddwmc = zddwmc;
	}

	public String getZddwmc(){
		return this.zddwmc;
	}

	public void setZddwdz(String zddwdz){
		this.zddwdz = zddwdz;
	}

	public String getZddwdz(){
		return this.zddwdz;
	}

	public void setSfzh(String sfzh){
		this.sfzh = sfzh;
	}

	public String getSfzh(){
		return this.sfzh;
	}

	public void setSqly(String sqly){
		this.sqly = sqly;
	}

	public String getSqly(){
		return this.sqly;
	}

	public void setXgzdwsjh(String xgzdwsjh){
		this.xgzdwsjh = xgzdwsjh;
	}

	public String getXgzdwsjh(){
		return this.xgzdwsjh;
	}

	public void setXgzdwlxdh(String xgzdwlxdh){
		this.xgzdwlxdh = xgzdwlxdh;
	}

	public String getXgzdwlxdh(){
		return this.xgzdwlxdh;
	}

	public void setXgzdwbm(String xgzdwbm){
		this.xgzdwbm = xgzdwbm;
	}

	public String getXgzdwbm(){
		return this.xgzdwbm;
	}

	public void setXgzdwlxr(String xgzdwlxr){
		this.xgzdwlxr = xgzdwlxr;
	}

	public String getXgzdwlxr(){
		return this.xgzdwlxr;
	}

	public void setYzbm(String yzbm){
		this.yzbm = yzbm;
	}

	public String getYzbm(){
		return this.yzbm;
	}

	public void setQy(String qy){
		this.qy = qy;
	}

	public String getQy(){
		return this.qy;
	}

	public void setXgzdwdz(String xgzdwdz){
		this.xgzdwdz = xgzdwdz;
	}

	public String getXgzdwdz(){
		return this.xgzdwdz;
	}

	public void setXgzdwmc(String xgzdwmc){
		this.xgzdwmc = xgzdwmc;
	}

	public String getXgzdwmc(){
		return this.xgzdwmc;
	}

	public void setYgzdwlxdh(String ygzdwlxdh){
		this.ygzdwlxdh = ygzdwlxdh;
	}

	public String getYgzdwlxdh(){
		return this.ygzdwlxdh;
	}

	public void setYgzdwlxr(String ygzdwlxr){
		this.ygzdwlxr = ygzdwlxr;
	}

	public String getYgzdwlxr(){
		return this.ygzdwlxr;
	}

	public void setYgzdwmc(String ygzdwmc){
		this.ygzdwmc = ygzdwmc;
	}

	public String getYgzdwmc(){
		return this.ygzdwmc;
	}

	public String getShjg(){
		return this.shjg;
	}

	public void setShjg(String shjg){
		this.shjg = shjg;
	}

	public String getNj(){
		return this.nj;
	}

	public void setNj(String nj){
		this.nj = nj;
	}

	public String getNd(){
		return this.nd;
	}

	public void setNd(String nd){
		this.nd = nd;
	}

	public String getXn(){
		return this.xn;
	}

	public void setXn(String xn){
		this.xn = xn;
	}

	public String getXq(){
		return this.xq;
	}

	public void setXq(String xq){
		this.xq = xq;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}


	public void setSfbk(String sfbk){//���������Ƿ񲹿�
		this.sfbk = sfbk;
	}

	public String getSfbk(){
		return this.sfbk;
	}

	public void setHzsqly(String hzsqly){//����������������
		this.hzsqly = hzsqly;
	}

	public String getHzsqly(){
		return this.hzsqly;
	}

	public void setBzrxm(String bzrxm){
		this.bzrxm = bzrxm;
	}

	public String getBzrxm(){
		return this.bzrxm;
	}

	public void setBzxm(String bzxm){
		this.bzxm = bzxm;
	}

	public String getBzxm(){
		return this.bzxm;
	}


	public void setBzxh(String bzxh){
		this.bzxh = bzxh;
	}

	public String getBzxh(){
		return this.bzxh;
	}

	public void setSjh(String sjh){
		this.sjh = sjh;
	}

	public String getSjh(){
		return this.sjh;
	}

	public void setJtdh(String jtdh){
		this.jtdh = jtdh;
	}

	public String getJtdh(){
		return this.jtdh;
	}

	public void setXh(String xh){
		this.xh = xh;
	}

	public String getXh(){
		return this.xh;
	}

	public void setXm(String xm){
		this.xm = xm;
	}

	public String getXm(){
		return this.xm;
	}

	public void setXb(String xb){
		this.xb = xb;
	}

	public String getXb(){
		return this.xb;
	}

	public void setXydm(String xydm){
		this.xydm = xydm;
	}

	public String getXydm(){
		return this.xydm;
	}

	public void setXymc(String xymc){
		this.xymc = xymc;
	}
	public String getXymc(){
		return this.xymc;
	}

	public void setZydm(String zydm){
		this.zydm = zydm;
	}
	public String getZydm(){
		return this.zydm;
	}

	public void setZymc(String zymc){
		this.zymc = zymc;
	}
	public String getZymc(){
		return this.zymc;
	}

	public void setBjdm(String bjdm){
		this.bjdm = bjdm;
	}
	public String getBjdm(){
		return this.bjdm;
	}

	public void setBjmc(String bjmc){
		this.bjmc = bjmc;
	}

	public String getBjmc(){
		return this.bjmc;
	}

	public String[] getErrMsgs() {
		return errMsgs;
	}

	public void setErrMsgs(String[] errMsgs) {
		this.errMsgs = errMsgs;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String[] getCjArray() {
		return cjArray;
	}

	public void setCjArray(String[] cjArray) {
		this.cjArray = cjArray;
	}

	public String[] getXhArray() {
		return xhArray;
	}

	public void setXhArray(String[] xhArray) {
		this.xhArray = xhArray;
	}

	public String[] getXmArray() {
		return xmArray;
	}

	public void setXmArray(String[] xmArray) {
		this.xmArray = xmArray;
	}

	public String[] getXnArray() {
		return xnArray;
	}

	public void setXnArray(String[] xnArray) {
		this.xnArray = xnArray;
	}

	public String[] getXqArray() {
		return xqArray;
	}

	public void setXqArray(String[] xqArray) {
		this.xqArray = xqArray;
	}

	public String getDasfzx() {
		return dasfzx;
	}

	public void setDasfzx(String dasfzx) {
		this.dasfzx = dasfzx;
	}

	public String getHzh() {
		return hzh;
	}

	public void setHzh(String hzh) {
		this.hzh = hzh;
	}

	public String getDazcsj() {
		return dazcsj;
	}

	public void setDazcsj(String dazcsj) {
		this.dazcsj = dazcsj;
	}

	public String getHkssjd() {
		return hkssjd;
	}

	public void setHkssjd(String hkssjd) {
		this.hkssjd = hkssjd;
	}

	public String getHkssqx() {
		return hkssqx;
	}

	public void setHkssqx(String hkssqx) {
		this.hkssqx = hkssqx;
	}

	public String getHkxxdz() {
		return hkxxdz;
	}

	public void setHkxxdz(String hkxxdz) {
		this.hkxxdz = hkxxdz;
	}

	public String getLxfs() {
		return lxfs;
	}

	public void setLxfs(String lxfs) {
		this.lxfs = lxfs;
	}

	public String getSqrq() {
		return sqrq;
	}

	public void setSqrq(String sqrq) {
		this.sqrq = sqrq;
	}

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

	public String getZddwyb() {
		return zddwyb;
	}

	public void setZddwyb(String zddwyb) {
		this.zddwyb = zddwyb;
	}

	public String getGzdasfzx() {
		return gzdasfzx;
	}

	public void setGzdasfzx(String gzdasfzx) {
		this.gzdasfzx = gzdasfzx;
	}

	public String getZdyy() {
		return zdyy;
	}

	public void setZdyy(String zdyy) {
		this.zdyy = zdyy;
	}

	public String getCsrq() {
		return csrq;
	}

	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getMz() {
		return mz;
	}

	public void setMz(String mz) {
		this.mz = mz;
	}

	public String getZdfs() {
		return zdfs;
	}

	public void setZdfs(String zdfs) {
		this.zdfs = zdfs;
	}

	public String getDabcwz() {
		return dabcwz;
	}

	public void setDabcwz(String dabcwz) {
		this.dabcwz = dabcwz;
	}

	public String getGlr() {
		return glr;
	}

	public void setGlr(String glr) {
		this.glr = glr;
	}

	public String getJsnr() {
		return jsnr;
	}

	public void setJsnr(String jsnr) {
		this.jsnr = jsnr;
	}

	public String getJsr() {
		return jsr;
	}

	public void setJsr(String jsr) {
		this.jsr = jsr;
	}

	public String getDah() {
		return dah;
	}

	public void setDah(String dah) {
		this.dah = dah;
	}
	
	Pages pages = new Pages();

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getBynf() {
		return bynf;
	}

	public void setBynf(String bynf) {
		this.bynf = bynf;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getSfydycl() {
		return sfydycl;
	}

	public void setSfydycl(String sfydycl) {
		this.sfydycl = sfydycl;
	}

	public String getBysj() {
		return bysj;
	}

	public void setBysj(String bysj) {
		this.bysj = bysj;
	}

	public String getRxsj() {
		return rxsj;
	}

	public void setRxsj(String rxsj) {
		this.rxsj = rxsj;
	}

	public String getBzryj() {
		return bzryj;
	}

	public void setBzryj(String bzryj) {
		this.bzryj = bzryj;
	}

	public String getCclb() {
		return cclb;
	}

	public void setCclb(String cclb) {
		this.cclb = cclb;
	}

	public String getCfhbx() {
		return cfhbx;
	}

	public void setCfhbx(String cfhbx) {
		this.cfhbx = cfhbx;
	}

	public String getCflbmc() {
		return cflbmc;
	}

	public void setCflbmc(String cflbmc) {
		this.cflbmc = cflbmc;
	}

	public String getCfyy() {
		return cfyy;
	}

	public void setCfyy(String cfyy) {
		this.cfyy = cfyy;
	}

	public String getCfyymc() {
		return cfyymc;
	}

	public void setCfyymc(String cfyymc) {
		this.cfyymc = cfyymc;
	}

	public String getCxrs() {
		return cxrs;
	}

	public void setCxrs(String cxrs) {
		this.cxrs = cxrs;
	}

	public String getCxryqd() {
		return cxryqd;
	}

	public void setCxryqd(String cxryqd) {
		this.cxryqd = cxryqd;
	}

	public String getHyjl() {
		return hyjl;
	}

	public void setHyjl(String hyjl) {
		this.hyjl = hyjl;
	}

	public String getJcjl() {
		return jcjl;
	}

	public void setJcjl(String jcjl) {
		this.jcjl = jcjl;
	}

	public String getJlr() {
		return jlr;
	}

	public void setJlr(String jlr) {
		this.jlr = jlr;
	}

	public String getKbbmyj() {
		return kbbmyj;
	}

	public void setKbbmyj(String kbbmyj) {
		this.kbbmyj = kbbmyj;
	}

	public String getRq() {
		return rq;
	}

	public void setRq(String rq) {
		this.rq = rq;
	}

	public String getShsj() {
		return shsj;
	}

	public void setShsj(String shsj) {
		this.shsj = shsj;
	}

	public String getWjsj() {
		return wjsj;
	}

	public void setWjsj(String wjsj) {
		this.wjsj = wjsj;
	}

	public String getXxyj() {
		return xxyj;
	}

	public void setXxyj(String xxyj) {
		this.xxyj = xxyj;
	}

	public String getXyyj() {
		return xyyj;
	}

	public void setXyyj(String xyyj) {
		this.xyyj = xyyj;
	}

	public String getXscyj() {
		return xscyj;
	}

	public void setXscyj(String xscyj) {
		this.xscyj = xscyj;
	}

	public String getZc() {
		return zc;
	}

	public void setZc(String zc) {
		this.zc = zc;
	}

	public String getSyd() {
		return  syd;
	}

	public void setSyd(String syd) {
		this.syd = DealString.toGBK(syd);
	}

	public String getMzmc() {
		return mzmc;
	}

	public void setMzmc(String mzmc) {
		this.mzmc = mzmc;
	}

	public String getDaly() {
		return daly;
	}

	public void setDaly(String daly) {
		this.daly = daly;
	}

	public String getYrdwdm() {
		return yrdwdm;
	}

	public void setYrdwdm(String yrdwdm) {
		this.yrdwdm = yrdwdm;
	}

	public String getZysj() {
		return zysj;
	}

	public void setZysj(String zysj) {
		this.zysj = zysj;
	}

	public String getWjh() {
		return wjh;
	}

	public void setWjh(String wjh) {
		this.wjh = wjh;
	}

	public String getXxmc() {
		return xxmc;
	}

	public void setXxmc(String xxmc) {
		this.xxmc = xxmc;
	}

	public String getDazcfs() {
		return dazcfs;
	}

	public void setDazcfs(String dazcfs) {
		this.dazcfs = dazcfs;
	}

	public String getHztp() {
		return hztp;
	}

	public void setHztp(String hztp) {
		this.hztp = hztp;
	}

	public String getJyh() {
		return jyh;
	}

	public void setJyh(String jyh) {
		this.jyh = jyh;
	}

	public String getSfyhz() {
		return sfyhz;
	}

	public void setSfyhz(String sfyhz) {
		this.sfyhz = sfyhz;
	}

	public String getZdlb() {
		return zdlb;
	}

	public void setZdlb(String zdlb) {
		this.zdlb = zdlb;
	}
	public String getDaclr() {
		return daclr;
	}
	public void setDaclr(String daclr) {
		this.daclr = daclr;
	}
	public String getDaclrlxfs() {
		return daclrlxfs;
	}
	public void setDaclrlxfs(String daclrlxfs) {
		this.daclrlxfs = daclrlxfs;
	}
	public String getXz() {
		return xz;
	}
	public void setXz(String xz) {
		this.xz = xz;
	}
	public String getByny() {
		return byny;
	}
	public void setByny(String byny) {
		this.byny = byny;
	}
	public String getJssj() {
		return jssj;
	}
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	public String getKssj() {
		return kssj;
	}
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	public String getZlsfqq() {
		return zlsfqq;
	}
	public void setZlsfqq(String zlsfqq) {
		this.zlsfqq = zlsfqq;
	}
	public String getDabh() {
		return dabh;
	}
	public void setDabh(String dabh) {
		this.dabh = dabh;
	}
	public String getSjr() {
		return sjr;
	}
	public void setSjr(String sjr) {
		this.sjr = sjr;
	}
	public String getSjjgmc() {
		return sjjgmc;
	}
	public void setSjjgmc(String sjjgmc) {
		this.sjjgmc = sjjgmc;
	}
	public String getSjsj() {
		return sjsj;
	}
	public void setSjsj(String sjsj) {
		this.sjsj = sjsj;
	}
	public String getXndabh() {
		return xndabh;
	}
	public void setXndabh(String xndabh) {
		this.xndabh = xndabh;
	}
	public String getDjr() {
		return djr;
	}
	public void setDjr(String djr) {
		this.djr = djr;
	}
	public String getDayjr() {
		return dayjr;
	}
	public void setDayjr(String dayjr) {
		this.dayjr = dayjr;
	}
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	public String getGdzldm() {
		return gdzldm;
	}
	public void setGdzldm(String gdzldm) {
		this.gdzldm = gdzldm;
	}
	public String getDaqx() {
		return daqx;
	}
	public void setDaqx(String daqx) {
		this.daqx = daqx;
	}	
}
