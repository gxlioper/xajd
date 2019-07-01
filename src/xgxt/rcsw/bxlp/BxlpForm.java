package xgxt.rcsw.bxlp;

import xgxt.bdsz.BdzdForm;
import xgxt.utils.Pages;

public class BxlpForm extends BdzdForm {

		private static final long serialVersionUID = 7095833289278294419L;

		Pages pages = new Pages();
		
		private String[]checkVal;
		
		private String xh;//学号
		
		private String xm;//姓名	
		
		private String xb;//性别
		
		private String sfzh;//身份证号
		
		private String nj;//年级
		
		private String xydm;//学院
		
		private String zydm;//专业
		
		private String bjdm;//班级
		
		private String nd;//年度
		
		private String month;//月份
		
		private String rq;//日期
		
		private String je;//金额
		
		private String bz;//备注
		
		private String spje;//索赔金额
		
		private String lpje;//理赔金额
		
		private String cxlx;//出险类型
		
		private String zfrq;//支付日期
		
		private String slrq;//受理日期
		
		private String lxdh;//联系电话
		
		private String xn;//学年
		
		private String xq;//学期
		
		private String nx;//年限
		
		private String sfby;//是否毕业
		
		private String bxgsdm;//保险公司
		
		private String tbxzdm;//投保险种
		
		private String shzt;//审核状态
		
		private String fdysh;//辅导员审核
		
		private String xysh;//学院审核
		
		private String xxsh;//学校审核
		
		private String shyj;//审核意见
		
		private String fdyshyj;
		
		private String xyshyj;
		
		private String xxshyj;

		public String getFdyshyj() {
			return fdyshyj;
		}

		public void setFdyshyj(String fdyshyj) {
			this.fdyshyj = fdyshyj;
		}

		public String getXxshyj() {
			return xxshyj;
		}

		public void setXxshyj(String xxshyj) {
			this.xxshyj = xxshyj;
		}

		public String getXyshyj() {
			return xyshyj;
		}

		public void setXyshyj(String xyshyj) {
			this.xyshyj = xyshyj;
		}

		public String getFdysh() {
			return fdysh;
		}

		public void setFdysh(String fdysh) {
			this.fdysh = fdysh;
		}

		public String getShyj() {
			return shyj;
		}

		public void setShyj(String shyj) {
			this.shyj = shyj;
		}

		public String getShzt() {
			return shzt;
		}

		public void setShzt(String shzt) {
			this.shzt = shzt;
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

		public String getSfby() {
			return sfby;
		}

		public void setSfby(String sfby) {
			this.sfby = sfby;
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

		public String getBjdm() {
			return bjdm;
		}

		public void setBjdm(String bjdm) {
			this.bjdm = bjdm;
		}

		public String getJe() {
			return je;
		}

		public void setJe(String je) {
			this.je = je;
		}

		public String getMonth() {
			return month;
		}

		public void setMonth(String month) {
			this.month = month;
		}

		public String getNd() {
			return nd;
		}

		public void setNd(String nd) {
			this.nd = nd;
		}

		public String getNj() {
			return nj;
		}

		public void setNj(String nj) {
			this.nj = nj;
		}

		public String getXb() {
			return xb;
		}

		public void setXb(String xb) {
			this.xb = xb;
		}

		public String getXh() {
			return xh;
		}

		public void setXh(String xh) {
			this.xh = xh;
		}

		public String getXm() {
			return xm;
		}

		public void setXm(String xm) {
			this.xm = xm;
		}

		public String getXydm() {
			return xydm;
		}

		public void setXydm(String xydm) {
			this.xydm = xydm;
		}

		public String getZydm() {
			return zydm;
		}

		public void setZydm(String zydm) {
			this.zydm = zydm;
		}

		public String getSfzh() {
			return sfzh;
		}

		public void setSfzh(String sfzh) {
			this.sfzh = sfzh;
		}

		public String getBz() {
			return bz;
		}

		public void setBz(String bz) {
			this.bz = bz;
		}

		public String getRq() {
			return rq;
		}

		public void setRq(String rq) {
			this.rq = rq;
		}

		public Pages getPages() {
			return pages;
		}

		public void setPages(Pages pages) {
			this.pages = pages;
		}

		public String getCxlx() {
			return cxlx;
		}

		public void setCxlx(String cxlx) {
			this.cxlx = cxlx;
		}

		public String getLpje() {
			return lpje;
		}

		public void setLpje(String lpje) {
			this.lpje = lpje;
		}

		public String getSlrq() {
			return slrq;
		}

		public void setSlrq(String slrq) {
			this.slrq = slrq;
		}

		public String getSpje() {
			return spje;
		}

		public void setSpje(String spje) {
			this.spje = spje;
		}

		public String getZfrq() {
			return zfrq;
		}

		public void setZfrq(String zfrq) {
			this.zfrq = zfrq;
		}

		public String getLxdh() {
			return lxdh;
		}

		public void setLxdh(String lxdh) {
			this.lxdh = lxdh;
		}

		public String getNx() {
			return nx;
		}

		public void setNx(String nx) {
			this.nx = nx;
		}

		public String getBxgsdm() {
			return bxgsdm;
		}

		public void setBxgsdm(String bxgsdm) {
			this.bxgsdm = bxgsdm;
		}

		public String getTbxzdm() {
			return tbxzdm;
		}

		public void setTbxzdm(String tbxzdm) {
			this.tbxzdm = tbxzdm;
		}

		public String[] getCheckVal() {
			return checkVal;
		}

		public void setCheckVal(String[] checkVal) {
			this.checkVal = checkVal;
		}
}
