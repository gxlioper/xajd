<%@ page language="java" pageEncoding="GB2312" contentType="text/html;charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <base target="_self"/>
    
    <title><bean:message key="lable.title" /> 贷款确认书</title>

	<meta http-equiv="pragma" content="No-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<%
	response.setHeader("Prama","no-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires",0);
	 %>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		/**
		 * @describe 报表输出打印
		 */
		function printAction() {
			document.forms[0].action = "zbdx_xszz.do?method=dkqrsPrint";
			document.forms[0].submit();
		}
		function validate(){
			return true;
			var xh = $('xh').value.trim();
			var ghzh = $('ghzh').value.trim();
			var dkqx = $('dkqx').value.trim();
			var dkze = $('dkze').value.trim();
			var qygx = $('qygx').value.trim();
			var sqdkxf = $('sqdkxf').value.trim();
			var qylxdh = $('qylxdh').value.trim();
			var qsdkshf = $('qsdkshf').value.trim();
			var qysfzh = $('qysfzh').value.trim();
			var sqdkzsf = $('sqdkzsf').value.trim();
			var qygzdw = $('qygzdw').value.trim();
			var yxlxfs = $('yxlxfs').value.trim();
			var qyzz = $('qyzz').value.trim();
			var fqxm = $('fqxm').value.trim();
			var mqxm = $('mqxm').value.trim();
			var fqsfzh = $('fqsfzh').value.trim();
			var mqsfzh = $('mqsfzh').value.trim();
			var fqgzdw = $('fqgzdw').value.trim();
			var mqgzdw = $('mqgzdw').value.trim();
			var fqlxfs = $('fqlxfs').value.trim();
			var mqlxfs = $('mqlxfs').value.trim();
			var scbyqx = $('scbyqx').value.trim();
			var ffsj = $('ffsj').value.trim();
			var hth1 = $('hth1').value.trim();
			var hth2 = $('hth2').value.trim();
			var htjbjg1 = $('htjbjg1').value.trim();
			var htjbjg2 = $('htjbjg2').value.trim();
			var fzjgmc1 = $('fzjgmc1').value.trim();
			var fzjgmc2 = $('fzjgmc2').value.trim();
			var dkje1 = $('dkje1').value.trim();
			var dkje2 = $('dkje2').value.trim();
			var hknf1 = $('hknf1').value.trim();
			var hknf2 = $('hknf2').value.trim();
			var hth3 = $('hth3').value.trim();
			var dqgzdw = $('dqgzdw').value.trim();
			var htjbjg3 = $('htjbjg3').value.trim();
			var fzjgmc3 = $('fzjgmc3').value.trim();
			var dqdwyb = $('dqdwyb').value.trim();
			var dkjg3 = $('dkjg3').value.trim();
			var dqdwlxfs = $('dqdwlxfs').value.trim();
			var hknf = $('hknf').value.trim();
			var yjfsbj = $('yjfsbj').value.trim();
			var xysh = $('xysh').value.trim();
			var xxsh = $('xxsh').value.trim();
			var fsdkze = $('fsdkze').value.trim();
			var fsdkzsf = $('fsdkzsf').value.trim();
			var fsdkshf = $('fsdkshf').value.trim();
			var yffzxdkje = $('yffzxdkje').value.trim();
			var sqly = $('sqly').value.trim();
			var beizhu = $('beizhu').value.trim();
						
			if(!isNumber(xh)){
				alert("学号必须为整数!");
				$('xh').focus();
				return false;
			}
			if((nj != null) && (nj != "") && (!isNumber(nj))){
				alert("年级必须为整数!");
				$('nj').focus();
				return false;
			}
			if((nd != null) && (nd != "") && (!isNumber(nd))){
				alert("年度必须为整数!");
				$('nd').focus();
				return false;
			}
			if((xn != null) && (xn != "") && (!isNumber(xn))){
				alert("学年必须为整数!");
				$('xn').focus();
				return false;
			}
			if((xq != null) && (xq != "") && (!isNumber(xq))){
				alert("学期必须为整数!");
				$('xq').focus();
				return false;
			}
			if((ghzh != null) && (ghzh != "") && (!isNumber(ghzh))){
				alert("本地工行帐号或者卡号必须为整数!");
				$('ghzh').focus();
				return false;
			}
			if((dkqx != null) && (dkqx != "") && (!isNumber(dkqx))){
				alert("贷款期限必须为整数!");
				$('dkqx').focus();
				return false;
			}
			if((dkze != null) && (dkze != "") && (!isNumber(dkze))){
				alert("申请贷款总额必须为整数!");
				$('dkze').focus();
				return false;
			}
			if((sqdkxf != null) && (sqdkxf != "") && (!isNumber(sqdkxf))){
				alert("申请贷款学费必须为整数!");
				$('sqdkxf').focus();
				return false;
			}
			if((qylxdh != null) && (qylxdh != "") && (!isNumber(qylxdh))){
				alert("请输入正确的 亲友联系电话!");
				$('qylxdh').focus();
				return false;
			}
			if((sqdkshf != null) && (sqdkshf != "") && (!isNumber(sqdkshf))){
				alert("申请贷款生活费 必须为整数!");
				$('sqdkshf').focus();
				return false;
			}
			if((qysfzh != null) && (qysfzh != "") && (!checkSfzh(qysfzh))){
				//alert("请输入正确的 亲友身份证号！");
				//$('qysfzh').focus();
				return false;
			}
			if((sqdkzsf != null) && (sqdkzsf != "") && (!isNumber(sqdkzsf))){
				alert("申请贷款住宿费 必须为整数!");
				$('xn').focus();
				return false;
			}
			if((fqsfzh != null) && (fqsfzh != "") && (!checkSfzh(fqsfzh))){
				//alert("请输入正确的身份证号!");
				//$('fqsfzh').focus();
				return false;
			}
			if((mqsfzh != null) && (mqsfzh != "") && (!checkSfzh(mqsfzh))){
				//alert("请输入正确的身份证号!");
				//$('mqsfzh').focus();
				return false;
			}
			if((dkje1 != null) && (dkje1 != "") && (!isNumber(dkje1))){
				alert("贷款金额1 必须为整数!");
				$('dkje1').focus();
				return false;
			}
			if((dkje2 != null) && (dkje2 != "") && (!isNumber(dkje2))){
				alert("贷款金额2 必须为整数!");
				$('dkje2').focus();
				return false;
			}
			if((dqdwyb != null) && (dqdwyb != "") && (!isNumber(dqdwyb))){
				alert("当前单位邮编 必须为整数!");
				$('dqdwyb').focus();
				return false;
			}
			if((dkje3 != null) && (dkje3 != "") && (!isNumber(dkje3))){
				alert("贷款金额3 必须为整数!");
				$('dkje3').focus();
				return false;
			}
			if((fsdkze != null) && (fsdkze != "") && (!isNumber(fsdkze))){
				alert("发放贷款总额 必须为整数!");
				$('fsdkze').focus();
				return false;
			}
			if((fsdkxf != null) && (fsdkxf != "") && (!isNumber(fsdkxf))){
				alert("发放贷款学费 必须为整数!");
				$('fsdkxf').focus();
				return false;
			}
			if((fsdkshf != null) && (fsdkshf != "") && (!isNumber(fsdkshf))){
				alert("发放贷款生活费 必须为整数!");
				$('xn').focus();
				return false;
			}
			if((yffzxdkje != null) && (yffzxdkje != "") && (!isNumber(yffzxdkje))){
				alert("发放贷款住宿费 必须为整数!");
				$('yffzxdkje').focus();
				return false;
			}
			
			return true;
		}

		function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
		
		function checkSfzh(sfzh) {
   			sfzh=sfzh.toLowerCase()
			var OldID;
			if(sfzh.length == 15){
				OldID = sfzh;
				return true;
			}else if(sfzh.length == 18){
				OldID = sfzh.substring(0, 6) + sfzh.substring(8,sfzh.length-1);
			}else{
				alert("请输入正确的身份证号码！","",function(){
					obj.select();
					obj.focus();
				});
				return false;
			}
			var W = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
			var A = new Array("1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2");
			var i, j, S;
			var NewID, ID, strNF;
			NewID = OldID.substring(0, 6) + "19" + OldID.substring(6,OldID.length);
			S = 0;
			for( i = 0; i <= 16; i++){
				j = NewID.substring(i, i+1) * W[i];
				S = S + j;
			}
			S = S % 11;
			if(sfzh != NewID + A[S]){
				alert("请输入正确的身份证号码！","",function(){
					obj.select();
					obj.focus();
				});
				return false;
			}
			return true;
		}
	</script>	
  </head>
  
  <body>
  	
  	
		<logic:equal value="false" name="sfksq">
	         	非申请时间!! 
	    </logic:equal>
		<logic:present name="aa">
<script>
		
			alert("确定修改？！");
		</script>
</logic:present>
	    <html:form action="/knbz.do" method="post">
	    <input type="hidden" id="url" name="url" value="/zbdx_xszz.do?method=getDkqrInfo" />
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-bjmc" />
	    		<logic:present name="doresult">
					<logic:match value="yes" name="doresult">
						<script language="javascript">
	         	alert("保存成功！");
	         	</script>
					</logic:match>
					<logic:match value="no" name="doresult">
						<script language="javascript">
	         	alert("保存失败！");
	         	</script>
					</logic:match>
				</logic:present>
	    <div class="title">
				<div class="title_img" id="title_m">
					贷款确认书
				</div>
		  </div>
			<table width="100%"   class="tbstyle">  
				  <tr>
				  
				  		<logic:equal name="userOnLine" value="teacher" scope="session">
							<td height="31">
								<div align="left">
									<font color="red">*</font>学号：
								</div>
							</td>
							<td>
								<div align="left">
									<html:text name='map' property="xh" styleId="xh"
										onkeypress="" />
									<button onclick="showTopWin('zbdx_stu_info.do?method=stuInfoQuery',750,550);"
										class="btn_01" id="buttonFindStu">
										选择
									</button>
								</div>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td height="31">
								<div align="left">
									<font color="red">*</font>学号：
								</div>
							</td>
							<td>
								<div align="left">
									<input type="text" id="xh" name="xh"
										style="width:100%;heigh:100%"
										value="<bean:write name='map' property="xh" />" readonly="true">
								</div>
							</td>
						</logic:equal>
				    <td height="31">姓名</td>
				    <td><input type="text" width="100%" readonly id="xm" name="xm" value="<bean:write name="map" property="xm"/>"></td>
				  </tr>
				  <tr>
				    <td height="31">性别</td>
				    <td height="31"><input type="text" readonly width="100%" id="xb" name="xb" value="<bean:write name="map" property="xb"/>"></td>
				    <td height="31">年级</td>
				    <td height="31"><input type="text" readonly width="100%" name="nj" id="nj" value="<bean:write name="map" property="nj"/>"></td>
				  </tr>
				  <tr>
				    <td height="31"><bean:message key="lable.xsgzyxpzxy" /></td>
				    <td height="31">
				    <input type="text" readonly style="width:100%"
										value="<bean:write name='map' property="xy" />"
										name="bysj" id="bysj" />				    </td>
				    <td height="31"><font color="red">*</font>年度 </td>
				    <td height="31"><input type="text" width="100%" name="nd" id="nd" value="<bean:write name="map" property="nd"/>"></td>
				  </tr>
				  <tr>
				    <td height="31">专业 </td>
				    <td height="31"><input type="text" width="100%" readonly  name="zymc" id="zymc" value="<bean:write name="map" property="zymc"/>"></td>
				    <td height="31"><font color="red">*</font>学年</td>
				    <td height="31"><input type="text" readonly width="100%" name="xn" id="xn" value="<bean:write name="map" property="xn"/>"></td>
				  </tr>
				  <tr>
				    <td height="31">班级</td>
				    <td height="31"><input type="text" readonly width="100%"  name="bjmc" id="bjmc" value="<bean:write name="map" property="bjmc"/>"></td>
				    <td height="31"><font color="red">*</font>学期 </td>
				    <td height="31"><input type="text" readonly width="100%" name="xq" id="xq" value="<bean:write name="map" property="xq"/>"></td>
				  </tr>
				  <tr>
				    <td height="31"><font color="red">本地工行帐号或者卡号</font></td>
				    <td height="31"><input type="text" width="100%" name="ghzh" id="ghzh" value="<bean:write name="map" property="ghzh"/>"></td>
				    <td height="31"><font color="red">贷款期限</font></td>
				    <td height="31"><input type="text" width="100%" name="dkqx" id="sfzh" value="<bean:write name="map" property="dkqx"/>"></td>
				  </tr>
				  <tr>
				    <td height="31">申请贷款总额</td>
				    <td height="31"><input type="text" width="100%" name="dkze" id="dkze" value="<bean:write name="map" property="dkze"/>
				      ">元</td>
				    <td height="31">亲友与本人关系</td>
				    <td height="31"><input type="text" width="100%" name="qygx" id="qygx" value="<bean:write name="map" property="qygx"/>"></td>
				  </tr>
				  <tr>
				    <td height="31">申请贷款学费</td>
				    <td height="31"><input type="text" width="100%" name="sqdkxf" id="sqdkxf" value="<bean:write name="map" property="sqdkxf"/>
				      ">元</td>
				    <td height="31">亲友联系电话</td>
				    <td height="31"><input type="text" width="100%" name="qylxdh" id="qylxdh" value="<bean:write name="map" property="qylxdh"/>"></td>
				  </tr>
				  <tr>
				    <td height="31">申请贷款生活费</td>
				    <td height="31"><input type="text" width="100%" name="qsdkshf" id="qsdkshf" value="<bean:write name="map" property="qsdkshf"/>
				      ">元</td>
				    <td height="31">亲友身份证号码</td>
				    <td height="31"><input type="text" width="100%" name="qysfzh" id="qysfzh" value="<bean:write name="map" property="qysfzh"/>"></td>
				  </tr>
				  <tr>
				    <td height="31">申请贷款住宿费</td>
				    <td height="31"><input type="text" width="100%" name="sqdkzsf" id="sqdkzsf" value="<bean:write name="map" property="sqdkzsf"/>
				      ">元</td>
				    <td height="31">亲友工作单位</td>
				    <td height="31"><input type="text" width="100%" name="qygzdw" id="qygzdw" value="<bean:write name="map" property="qygzdw"/>"></td>
				  </tr>
				  <tr>
				    <td height="31">有效联系方式</td>
				    <td height="31"><input type="text" width="100%" name="yxlxfs" id="yxlxfs" value="<bean:write name="map" property="yxlxfs"/>"></td>
				    <td height="31">亲友住址</td>
				    <td height="31"><input type="text" width="100%" name="qyzz" id="qyzz" value="<bean:write name="map" property="qyzz"/>"></td>
				  </tr>
				  <tr>
				    <td height="48">父亲姓名</td>
				    <td height="48"><input type="text" width="100%" name="fqxm" id="fqxm" value="<bean:write name="map" property="fqxm"/>"></td>
				    <td height="48">母亲姓名</td>
				    <td height="48"><input type="text" width="100%" name="mqxm" id="mqxm" value="<bean:write name="map" property="mqxm"/>"></td>
				  </tr>
				  <tr>
				    <td height="48">父亲身份证号码</td>
				    <td height="48"><input type="text" width="100%" name="fqsfzh" id="fqsfzh" value="<bean:write name="map" property="fqsfzh"/>"></td>
				    <td height="48">母亲身份证号码</td>
				    <td height="48"><input type="text" width="100%" name="mqsfzh" id="mqsfzh" value="<bean:write name="map" property="mqsfzh"/>"></td>
				  </tr>
				  <tr>
				    <td height="48">父亲工作单位</td>
				    <td height="48"><input type="text" width="100%" name="fqgzdw" id="fqgzdw" value="<bean:write name="map" property="fqgzdw"/>"></td>
				    <td height="48">母亲工作单位</td>
				    <td height="48"><input type="text" width="100%" name="mqgzdw" id="mqgzdw" value="<bean:write name="map" property="mqgzdw"/>"></td>
				  </tr>
				  <tr>
				    <td height="48">父亲联系方式</td>
				    <td height="48"><input type="text" width="100%" name="fqlxfs" id="fqlxfs" value="<bean:write name="map" property="fqlxfs"/>"></td>
				    <td height="48">母亲联系方式</td>
				    <td height="48"><input type="text" width="100%" name="mqlxfs" id="mqlxfs" value="<bean:write name="map" property="mqlxfs"/>"></td>
				  </tr>
				  <tr>
				    <td height="48">首次毕业去向</td>
				    <td height="48"><input type="text" width="100%" name="scbyqx" id="scbyqx" value="<bean:write name="map" property="scbyqx"/>"></td>
				    <td height="48">发放时间</td>
				    <td height="48"><input type="text" onblur="dateFormatChg(this)" style="cursor:hand;" onclick="return showCalendar('ffsj','y-mm-dd');" width="100%" name="ffsj" id="ffsj" value="<bean:write name="map" property="ffsj"/>"></td>
				  </tr>
				  <tr>
				    <td height="48">合同号1</td>
				    <td height="48"><input type="text" width="100%" name="hth1" id="hth1" value="<bean:write name="map" property="hth1"/>"></td>
				    <td height="48">合同号2</td>
				    <td height="48"> <input type="text" width="100%" name="hth2" id="hth2" value="<bean:write name="map" property="hth2"/>"></td>
				  </tr>
				  <tr>
				    <td height="31">合同经办金融机构1</td>
				    <td height="31"><input type="text" width="100%" name="htjbjg1" id="htjbjg1" value="<bean:write name="map" property="htjbjg1"/>"></td>
				    <td height="31">合同经办金融机构2</td>
				    <td height="31"><input type="text" width="100%" name="htjbjg2" id="htjbjg2" value="<bean:write name="map" property="htjbjg2"/>"></td>
				  </tr>
				  <tr>
				    <td height="31">分支机构名称1</td>
				    <td height="31"><input type="text" width="100%" name="fzjgmc1" id="fzjgmc1" value="<bean:write name="map" property="fzjgmc1"/>"></td>
				    <td height="31">分支机构名称2</td>
				    <td height="31"><input type="text" width="100%" name="fzjgmc2" id="fzjgmc2" value="<bean:write name="map" property="fzjgmc2"/>"></td>
				  </tr>
				  <tr>
				    <td height="31">贷款金额1</td>
				    <td><input type="text" width="100%" name="dkje1" id="dkje1" value="<bean:write name="map" property="dkje1"/>"> 
				      元</td>	
				    <td width="15%" height="31">贷款金额2</td><td><input type="text" width="100%" name="dkje2" id="dkje2" value="<bean:write name="map" property="dkje2"/>"> 
				      元 </td>
				  </tr>
				  <tr>
				    <td height="31">还款年份1</td>
				    <td><input type="text" width="100%" onblur="dateFormatChg(this)" style="cursor:hand;" onclick="return showCalendar('hknf1','yyyy');" name="hknf1" id="hknf1" value="<bean:write name="map" property="hknf1"/>"> 
				      </td>	
				    <td width="15%" height="31">还款年份2</td><td><input type="text" width="100%" onblur="dateFormatChg(this)" style="cursor:hand;" onclick="return showCalendar('hknf2','yyyy');" name="hknf2" id="hknf2" value="<bean:write name="map" property="hknf2"/>"> 
				      </td>
				  </tr>
				  <tr>
				    <td height="31">合同号3</td>
				    <td><input type="text" width="100%" name="hth3" id="hth3" value="<bean:write name="map" property="hth3"/>"> 
				      </td>	
				    <td width="15%" height="31">当前工作单位</td><td><input type="text" width="100%" name="dqgzdw" id="dqgzdw" value="<bean:write name="map" property="dqgzdw"/>"> 
				      </td>
				  </tr>
				  <tr>
				    <td height="31">合同经办金融机构3</td>
				    <td><input type="text" width="100%" name="htjbjg3" id="htjbjg3" value="<bean:write name="map" property="htjbjg3"/>"> 
				      </td>	
				    <td width="15%" height="31">当前单位地址</td><td><input type="text" width="100%" name="dqdwdz" id="dqdwdz" value="<bean:write name="map" property="dqdwdz"/>"> 
				       </td>
				  </tr>
				  <tr>
				    <td height="31">分支机构名称3</td>
				    <td><input type="text" width="100%" name="fzjgmc3" id="fzjgmc3" value="<bean:write name="map" property="fzjgmc3"/>"> 
				      </td>	
				    <td width="15%" height="31">当前单位邮编</td><td><input type="text" width="100%" name="dqdwyb" id="dqdwyb" value="<bean:write name="map" property="dqdwyb"/>"> 
				      </td>
				  </tr>
				  <tr>
				    <td height="31">贷款金额3</td>
				    <td><input type="text" width="100%" name="dkjg3" id="dkjg3" value="<bean:write name="map" property="dkjg3"/>">元
				      </td>	
				    <td width="15%" height="31">当前单位联系方式</td><td><input type="text" width="100%" name="dqdwlxfs" id="dqdwlxfs" value="<bean:write name="map" property="dqdwlxfs"/>"> 
				      </td>
				  </tr>
				  <tr>
				    <td height="31">还款年份3</td>
				    <td><input type="text" width="100%" name="hknf" id="hknf" onblur="dateFormatChg(this)" style="cursor:hand;" onclick="return showCalendar('hknf','yyyy');" value="<bean:write name="map" property="hknf"/>"> 
				      </td>	
				    <td width="15%" height="31">邮件发送标记</td><td><input type="text" width="100%" name="yjfsbj" id="yjfsbj" value="<bean:write name="map" property="yjfsbj"/>"> 
				      </td>
				  </tr>
				  <tr>
				    <td height="31"><bean:message key="lable.xsgzyxpzxy" />审核</td>
				    <td><input type="text" width="100%" name="xysh" id="xysh" value="<bean:write name="map" property="xysh"/>"> 
				      </td>	
				    <td width="15%" height="31">学校审核</td><td><input type="text" width="100%" name="xxsh" id="xxsh" value="<bean:write name="map" property="xxsh"/>"> 
				      </td>
				  </tr>
				  <tr>
				    <td height="31">发放贷款总额</td>
				    <td><input type="text" width="100%" name="fsdkze" id="fsdkze" value="<bean:write name="map" property="fsdkze"/>">元
				      </td>	
				    <td height="31">发放贷款学费</td><td><input type="text" width="100%" name="fsdkzsf" id="fsdkzsf" value="<bean:write name="map" property="fsdkzsf"/>
				    "> 
				      元</td>
				  </tr>
				  <tr>
				    <td height="31">发放贷款生活费</td>
				    <td><input type="text" width="100%" name="fsdkshf" id="fsdkshf" value="<bean:write name="map" property="fsdkshf"/>
				    ">元</td>	
				    <td height="31">发放贷款住宿费</td><td><input type="text" width="100%" name="yffzxdkje" id="yffzxdkje" value="<bean:write name="map" property="yffzxdkje"/>
				    "> 元
				      </td>
				  </tr>
				  <tr>
				    <td height="31">申请理由</td>
				    <td colspan="3"><input type="text" width="100%" name="sqly" id="sqly" value="<bean:write name="map" property="sqly"/>"> 
				      </td>	
				  </tr>
				  <tr>
				    <td height="31">备注</td>
				    <td colspan="3"><input type="text" width="100%" name="beizhu" id="beizhu" value="<bean:write name="map" property="beizhu"/>"> 
				      </td>	
				  </tr>				 
			　　</table>
			
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;width:100%">
							<button class="button2" onclick="if (validate())refreshForm('zbdx_xszz.do?method=dkqrsSave');"
								style="width:80px">
								提交申请
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="printAction()"
								style="width:80px">
								输出打印
							</button>
						</div>
					</center>
				
				
	    </html:form>
	    <script src="js/bottomButton.js" ></script>
	    <script language="javascript">
if($("btn") && !window.dialogArguments){
	$("btn").style.pixelTop = document.body.clientHeight - 35;
	$("btn").style.width = "96%";
	window.setInterval("initBTNTool('btn')",1);
}
</script>
  </body>
</html:html>
