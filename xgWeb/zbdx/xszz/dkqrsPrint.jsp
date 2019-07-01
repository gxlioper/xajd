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
	<script language="javascript" src="js/webPrint.js"></script>
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
			var xh = document.getElementById('xh').value;
			var qsdh = document.getElementById('qsdh').value;
			var yzbm = document.getElementById('yzbm').value;
			var sfzh = document.getElementById('sfzh').value;
			var jtcy1_ysr = document.getElementById('jtcy1_ysr').value;
			var jtcy2_ysr = document.getElementById('jtcy2_ysr').value;
			var jtcy3_ysr = document.getElementById('jtcy3_ysr').value;
			var jtcy4_ysr = document.getElementById('jtcy4_ysr').value;
			var jtcy5_ysr = document.getElementById('jtcy5_ysr').value;
			var jttgje = document.getElementById('jttgje').value;
			var zxje = document.getElementById('zxje').value;
			var jxje = document.getElementById('jxje').value;
			var qgzxje = document.getElementById('qgzxje').value;
			var xnwxdkje = document.getElementById('xnwxdkje').value;
			var qtsrje = document.getElementById('qtsrje').value;
			var zxdkje = document.getElementById('zxdkje').value;
			var yffzxdkje = document.getElementById('yffzxdkje').value;
			var zzff1qsje = document.getElementById('zzff1qsje').value;
			var zzff1jsje = document.getElementById('zzff1jsje').value;
			var sqzzly = document.getElementById('sqzzly').value;
			
			if(!isNumber(xh)){
				alert("学号必须为整数!");
				return false;
			}else if((qsdh != null) && (qsdh != "") && (!isNumber(qsdh))){
				alert("寝室电话必须为整数!");
				return false;
			}else if((yzbm != null) && (yzbm != "") && (!isNumber(yzbm))){
				alert("邮政编码必须为整数!");
				return false;
			}else if((sfzh != null) && (sfzh != "") && (!checkSfzh(sfzh))){
				return false;
			}else if((jtcy1_ysr != null) && (jtcy1_ysr != "") && (!isNumber(jtcy1_ysr))){
				alert("家庭成员1月收入必须为整数!");
				return false;
			}else if((jtcy2_ysr != null) && (jtcy2_ysr != "") && (!isNumber(jtcy2_ysr))){
				alert("家庭成员2月收入必须为整数!");
				return false;
			}else if((jtcy3_ysr != null) && (jtcy3_ysr != "") && (!isNumber(jtcy3_ysr))){
				alert("家庭成员3月收入必须为整数!");
				return false;
			}else if((jtcy4_ysr != null) && (jtcy4_ysr != "") && (!isNumber(jtcy4_ysr))){
				alert("家庭成员4月收入必须为整数!");
				return false;
			}else if((jtcy5_ysr != null) && (jtcy5_ysr != "") && (!isNumber(jtcy5_ysr))){
				alert("家庭成员5月收入必须为整数!");
				return false;
			}else if((jttgje != null) && (jttgje != "") && (!isNumber(jttgje))){
				alert("本学年家庭提供金额必须为整数!");
				return false;
			}else if((zxje != null) && (zxje != "") && (!isNumber(zxje))){
				alert("本学年助学金必须为整数!");
				return false;
			}else if((jxje != null) && (jxje != "") && (!isNumber(jxje))){
				alert("本学年奖学金必须为整数!");
				return false;
			}else if((qgzxje != null) && (qgzxje != "") && (!isNumber(qgzxje))){
				alert("本学年勤工助学收入必须为整数!");
				return false;
			}else if((xnwxdkje != null) && (xnwxdkje != "") && (!isNumber(xnwxdkje))){
				alert("本学年校内无息贷学金必须为整数!");
				return false;
			}else if((qtsrje != null) && (qtsrje != "") && (!isNumber(qtsrje))){
				alert("本学年其他收入必须为整数!");
				return false;
			}else if((zxdkje != null) && (zxdkje != "") && (!isNumber(zxdkje))){
				alert("助学贷款申请金额必须为整数!");
				return false;
			}else if((yffzxdkje != null) && (yffzxdkje != "") && (!isNumber(yffzxdkje))){
				alert("助学贷款已发放金额必须为整数!");
				return false;
			}else if((zzff1qsje != null) && (zzff1qsje != "") && (!isNumber(zzff1qsje))){
				alert("第一志愿起始金额必须为整数!");
				return false;
			}else if((zzff1jsje != null) && (zzff1jsje != "") && (!isNumber(zzff1jsje))){
				alert("第一志愿结束金额必须为整数!");
				return false;
			}else if(sqzzly != null){
	         	if(sqzzly.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("申请资助理由不能大于100个字符");
	          		 return false;
	       		 }
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
	    <input type="hidden" id="url" name="url" value="/xszz/knbz.jsp" />
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
			<table width="100%" id="rsTable" class="tbstyle">  
				  <tr>
							<td height="31">
								<div align="left">
									<font color="red">*</font>学号：
								</div>
							</td>
							<td colspan="2">
								<div align="left">
									&nbsp;<bean:write name='map' property="xh" />
								</div>
							</td>
				    
				    <td height="31" colspan="2">姓名</td>
				    <td colspan="2">&nbsp;<bean:write name="map" property="xm"/></td>
				  </tr>
				  <tr>
				    <td height="31">性别</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="xb"/></td>
				    <td height="31" colspan="2">年级</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="nj"/></td>
				  </tr>
				  <tr>
				    <td height="31"><bean:message key="lable.xsgzyxpzxy" /></td>
				    <td height="31" colspan="2">&nbsp;<bean:write name='map' property="xy" />
				    </td>
				    <td height="31" colspan="2"><font color="red">*</font>年度 </td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="nd"/></td>
				  </tr>
				  <tr>
				    <td height="31">专业 </td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="zymc"/></td>
				    <td height="31" colspan="2"><font color="red">*</font>学年</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="xn"/></td>
				  </tr>
				  <tr>
				    <td height="31">班级</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="bj"/></td>
				    <td height="31" colspan="2"><font color="red">*</font>学期 </td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="xq"/></td>
				  </tr>
				  <tr>
				    <td height="31"><font color="red">本地工行帐号或者卡号</font></td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="ghzh"/></td>
				    <td height="31" colspan="2"><font color="red">贷款期限</font></td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="dkqx"/></td>
				  </tr>
				  <tr>
				    <td height="31">申请贷款总额</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="dkze"/>元</td>
				    <td height="31" colspan="2">亲友与本人关系</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="qygx"/></td>
				  </tr>
				  <tr>
				    <td height="31">申请贷款学费</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="sqdkxf"/>元</td>
				    <td height="31" colspan="2">亲友联系电话</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="qylxdh"/></td>
				  </tr>
				  <tr>
				    <td height="31">申请贷款生活费</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="qsdkshf"/>元</td>
				    <td height="31" colspan="2">亲友身份证号码</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="qysfzh"/></td>
				  </tr>
				  <tr>
				    <td height="31">申请贷款住宿费</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="sqdkzsf"/>元</td>
				    <td height="31" colspan="2">亲友工作单位</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="qygzdw"/></td>
				  </tr>
				  <tr>
				    <td height="31">有效联系方式</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="yxlxfs"/></td>
				    <td height="31" colspan="2">亲友住址</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="qyzz"/></td>
				  </tr>
				  <tr>
				    <td height="48">父亲姓名</td>
				    <td height="48" colspan="2">&nbsp;<bean:write name="map" property="fqxm"/></td>
				    <td height="48" colspan="2">母亲姓名</td>
				    <td height="48" colspan="2">&nbsp;<bean:write name="map" property="mqxm"/></td>
				  </tr>
				  <tr>
				    <td height="48">父亲身份证号码</td>
				    <td height="48" colspan="2">&nbsp;<bean:write name="map" property="fqsfzh"/></td>
				    <td height="48" colspan="2">母亲身份证号码</td>
				    <td height="48" colspan="2">&nbsp;<bean:write name="map" property="mqsfzh"/></td>
				  </tr>
				  <tr>
				    <td height="48">父亲工作单位</td>
				    <td height="48" colspan="2">&nbsp;<bean:write name="map" property="fqgzdw"/></td>
				    <td height="48" colspan="2">母亲工作单位</td>
				    <td height="48" colspan="2">&nbsp;<bean:write name="map" property="mqgzdw"/></td>
				  </tr>
				  <tr>
				    <td height="48">父亲联系方式</td>
				    <td height="48" colspan="2">&nbsp;<bean:write name="map" property="fqlxfs"/></td>
				    <td height="48" colspan="2">母亲联系方式</td>
				    <td height="48" colspan="2">&nbsp;<bean:write name="map" property="mqlxfs"/></td>
				  </tr>
				  <tr>
				    <td height="48">首次毕业去向</td>
				    <td height="48" colspan="2">&nbsp;<bean:write name="map" property="scbyqx"/></td>
				    <td height="48" colspan="2">发放时间</td>
				    <td height="48" colspan="2">&nbsp;<bean:write name="map" property="ffsj"/></td>
				  </tr>
				  <tr>
				    <td height="48">合同号1</td>
				    <td height="48" colspan="2">&nbsp;<bean:write name="map" property="hth1"/></td>
				    <td height="48" colspan="2">合同号2</td>
				    <td height="48" colspan="2">&nbsp;<bean:write name="map" property="hth2"/></td>
				  </tr>
				  <tr>
				    <td height="31">合同经办金融机构1</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="htjbjg1"/></td>
				    <td height="31" colspan="2">合同经办金融机构2</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="htjbjg2"/></td>
				  </tr>
				  <tr>
				    <td height="31">分支机构名称1</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="fzjgmc1"/></td>
				    <td height="31" colspan="2">分支机构名称2</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="fzjgmc2"/></td>
				  </tr>
				  <tr>
				    <td width="11%" height="31">贷款金额1</td>
				    <td width="8%" colspan="2">&nbsp;<bean:write name="map" property="dkje1"/>
				      元</td>	
				    <td width="15%" height="31" colspan="2">贷款金额2</td><td colspan="2">&nbsp;<bean:write name="map" property="dkje2"/>
				      元 </td>
				  </tr>
				  <tr>
				    <td width="11%" height="31">还款年份1</td>
				    <td width="8%" colspan="2">&nbsp;<bean:write name="map" property="hknf1"/>
				      </td>	
				    <td width="15%" height="31" colspan="2">还款年份2</td><td colspan="2">&nbsp;<bean:write name="map" property="hknf2"/>
				      </td>
				  </tr>
				  <tr>
				    <td width="11%" height="31">合同号3</td>
				    <td width="8%" colspan="2">&nbsp;<bean:write name="map" property="hth3"/>
				      </td>	
				    <td width="15%" height="31" colspan="2">当前工作单位</td><td colspan="2">&nbsp;<bean:write name="map" property="dqgzdw"/>
				      </td>
				  </tr>
				  <tr>
				    <td width="11%" height="31">合同经办金融机构3</td>
				    <td width="8%" colspan="2">&nbsp;<bean:write name="map" property="htjbjg3"/>
				      </td>	
				    <td width="15%" height="31" colspan="2">当前单位地址</td><td colspan="2">&nbsp;<bean:write name="map" property="dqdwdz"/>
				       </td>
				  </tr>
				  <tr>
				    <td width="11%" height="31">分支机构名称3</td>
				    <td width="8%" colspan="2">&nbsp;<bean:write name="map" property="fzjgmc3"/>
				      </td>	
				    <td width="15%" height="31" colspan="2">当前单位邮编</td><td colspan="2">&nbsp;<bean:write name="map" property="dqdwyb"/>
				      </td>
				  </tr>
				  <tr>
				    <td width="11%" height="31">贷款金额3</td>
				    <td width="8%" colspan="2">&nbsp;<bean:write name="map" property="dkjg3"/>元
				      </td>	
				    <td width="15%" height="31" colspan="2">当前单位联系方式</td><td colspan="2">&nbsp;<bean:write name="map" property="dqdwlxfs"/>
				      </td>
				  </tr>
				  <tr>
				    <td width="11%" height="31">还款年份3</td>
				    <td width="8%" colspan="2">&nbsp;<bean:write name="map" property="hknf"/>
				      </td>	
				    <td width="15%" height="31" colspan="2">邮件发送标记</td><td colspan="2">&nbsp;<bean:write name="map" property="yjfsbj"/>
				      </td>
				  </tr>
				  <tr>
				    <td width="11%" height="31"><bean:message key="lable.xsgzyxpzxy" />审核</td>
				    <td width="8%" colspan="2">&nbsp;<bean:write name="map" property="xysh"/>
				      </td>	
				    <td width="15%" height="31" colspan="2">学校审核</td><td colspan="2">&nbsp;<bean:write name="map" property="xxsh"/>
				      </td>
				  </tr>
				  <tr>
				    <td width="11%" height="31">发放贷款总额</td>
				    <td width="8%" colspan="2">&nbsp;<bean:write name="map" property="fsdkze"/>元
				      </td>	
				    <td width="15%" height="31" colspan="2">发放贷款学费</td><td colspan="2">&nbsp;<bean:write name="map" property="fsdkzsf"/>
				    元</td>
				  </tr>
				  <tr>
				    <td width="11%" height="31">发放贷款生活费</td>
				    <td width="8%" colspan="2">&nbsp;<bean:write name="map" property="fsdkshf"/>元</td>	
				    <td width="15%" height="31" colspan="2">发放贷款住宿费</td><td colspan="2">&nbsp;<bean:write name="map" property="yffzxdkje"/>元
				      </td>
				  </tr>
				  <tr>
				    <td width="11%" height="31">申请理由</td>
				    <td width="8%" colspan="6">&nbsp;<bean:write name="map" property="sqly"/>
				      </td>	
				  </tr>
				  <tr>
				    <td width="11%" height="31">备注</td>
				    <td width="8%" colspan="6">&nbsp;<bean:write name="map" property="beizhu"/>
				      </td>	
				  </tr>				 
			　　</table>
			
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;width:100%">
							<button class="button2" onclick="expTabWebPrint('rsTable','中北大学贷款确认书',null,null,null,null);"
								style="width:80px">
								打印
							</button>
						</div>
					</center>
				
				
	    </html:form>
	    <script src="js/bottomButton.js" ></script>
	    <script language="javascript">
if(document.getElementById("btn") && !window.dialogArguments){
	document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
	document.getElementById("btn").style.width = "96%";
	window.setInterval("initBTNTool('btn')",1);
}
</script>
  </body>
</html:html>
