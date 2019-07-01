<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<base target="_self" />
<head>


	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		function chkSfzh(obj) {
	var sfzh = obj.value.toLowerCase();
	var OldID;
	if(sfzh.length == 15){
/*	    if(sfzh.substring(6,8)<'80'){
	    BatAlert.MyAlert("您输入的身份证号码是８０年以前的，请输入考生本人的身份证号码！","",function(){
			obj.select();
			obj.focus();
		});
		return false;
	    }else{
		OldID = sfzh;
		return true;
		}*/
		OldID = sfzh;
		return true;
	}else if(sfzh.length == 18){
/*	    if(sfzh.substring(6,10)<'1980'){
	    BatAlert.MyAlert("您输入的身份证号码是１９８０年以前的，请输入考生本人的身份证号码！","",function(){
			obj.select();
			obj.focus();
		});
		return false;
	    }else{
		OldID = sfzh.substring(0, 6) + sfzh.substring(8,sfzh.length-1);
		}*/
		
		OldID = sfzh.substring(0, 6) + sfzh.substring(8,sfzh.length-1);
	}else{
		alert("请输入正确的身份证号码！");
		return false;
	}
	var W = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
	var A = new Array("1", "0", "x","9", "8", "7", "6", "5", "4", "3", "2");
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
		alert("请输入正确的身份证号码！");
		return false;
	}
	return true;
}
		function yz(){
			var xh = document.getElementById('xh').value;
			var sfzh = document.getElementById('sfzh');
			var jg = document.getElementById('jg').value;
			var xl = document.getElementById('xl').value;
			var yhkh = document.getElementById('yhkh').value;
			var ykth = document.getElementById('ykth').value;
			var fkh = document.getElementById('fkh').value;
			var jtdz = document.getElementById('jtdz').value;
			var chjl = document.getElementById('chjl').value;
			var sqly = document.getElementById('sqly').value;
			if((xh == null) || (xh == "")){
				alert("学号不能为空!");
				return false;
			}
			if(!chkSfzh(sfzh)){
				return false;
			}
			
			if((jg == null) || (jg == "")){
				alert("籍贯不能为空!!");
				return false;
			}
			if((xl == null) || (xl == "")){
				alert("学历不能为空!!");
				return false;
			}
			
			if((yhkh == null) || (yhkh == "")){
				alert("银行卡号不能为空!!");
				return false;
			}
			if((ykth == null) || (ykth == "")){
				alert("一卡通号不能为空!!");
				return false;
			}
			if((fkh == null) || (fkh == "")){
				alert("饭卡号不能为空!!");
				return false;
			}
			if((jtdz == null) || (jtdz == "")){
				alert("家庭地址不能为空!!");
				return false;
			}
			if(chjl != null && chjl.length > 600){
				alert("曾获奖励不能大于600字!");
				return false;
			}
			if(sqly != null && sqly.length > 1000){
				alert("申请理由不能大于1000字!");
				return false;
			}
			document.forms[0].action = "/xgxt/xcxyXszz.do?method=saveXsXmsq";
			document.forms[0].submit();
		}
		
		function toPrintOut(titName){//输出相应的打印页面
			document.forms[0].target = "_blank";
			document.forms[0].action = "/xgxt/xcxyXszz.do?method=printSqb";
			document.forms[0].submit();	
			document.forms[0].target = "_self";
		}
		

  function getInfo(code){
  	if(code == 13){
  		document.forms[0].action = "/xgxt/xcxyXszz.do?method=getXmsq";
  		document.forms[0].submit();
  	}
  }
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 填写申请表 -
			<bean:write name="zzxm" property="xmmc" />
			申请
		</div>
	</div>
	<logic:equal name="sfksq" value="no">
		<center>
			<br>
			<br>
			<br>
			<p>
				<font size="2" color="red">现在不在申请时间内！</font>
			</p>
		</center>
	</logic:equal>
	<logic:equal name="sfksq" value="yes">
		<html:form action="xcxyXszz.do?method=getXmsq" method="post"
			enctype="multipart/form-data">
			<input type="hidden"
				value="<bean:write name="zzxm" property="xmdm"/>" name="xmdm"
				id="xmdm">
			<input type="hidden"
				value="<bean:write name="zzxm" property="xmlc"/>" name="xmlc"
				id="xmlc">
			<input type="hidden"
				value="<bean:write name="zzxm" property="xmmc"/>" name="xmmc"
				id="xmmc">
			<logic:present name="result">
				<logic:match value="checking" name="result">
					<script language="javascript">
	         			alert("您提交的申请正在审核中，不能重复提交！");
	         		</script>
				</logic:match>
			</logic:present>
			<logic:present name="result">
				<logic:match value="yes" name="result">
					<script language="javascript">
	         			alert("保存成功！");
	         		</script>
				</logic:match>
				<logic:match value="no" name="result">
					<script language="javascript">
	         			alert("保存失败！");
	         		</script>
				</logic:match>
				<logic:match value="info" name="result">
					<script language="javascript">
	         			alert("您上传的文件大于10M！");
	         		</script>
				</logic:match>
			</logic:present>
			<table class="tbstyle" style="width:100%" id="rsT">
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="right" width="16%">
							<font color="red">*</font>学号：
						</td>
						<td align="left" width="34%">
							<html:text name='rs' property="xh" styleId="xh"
								readonly="readonly" onkeypress="getInfo(event.keyCode);" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="right" width="16%">
							<font color="red">*</font>学号：
						</td>
						<td align="left" width="34%">
							<input type="text" id="xh" name="xh" readonly="readonly"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xh" />" readonly="true">
						</td>
					</logic:equal>
					<td width="16%">
						<div align="right">
							姓名：
						</div>
					</td>
					<td width="34%">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr height="28">
					<td>
						<div align="right">
							出生年月：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="csrq" />
					</td>
					<td>
						<div align="right">
							性别：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xb" />
					</td>
				</tr>
				<tr height="28">
					<td>
						<div align="right">
							入学时间：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="rxrq" />
					</td>
					<td>
						<div align="right">
							民族：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="mzmc" />
					</td>
				</tr>
				<tr height="28">
					<td>
						<div align="right">
							大学：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xxmc" />
					</td>
					<td>
						<div align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xymc" />
					</td>
				</tr>
				<tr height="28">
					<td>
						<div align="right">
							专业：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="zymc" />
					</td>
					<td>
						<div align="right">
							班级：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="bjmc" />
					</td>
				</tr>
				<tr height="28">
					<td>
						<div align="right">
							学制：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xz" />
					</td>
					<td>
						<div align="right">
							政治面貌：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="zzmmmc" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							宿舍编号：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="ssbh" />
					</td>
					<td>
						<div align="right">
							<font color="red">*</font>身份证号：
						</div>
					</td>
					<td>
						<input type="text" id="sfzh" name="sfzh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfzh"/>" maxlength="30">
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							<font color="red">*</font>籍贯：
						</div>
					</td>
					<td>
						<input type="text" id="jg" name="jg" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jg"/>" maxlength="15">
					</td>
					<td>
						<div align="right">
							<font color="red">*</font>学历：
						</div>
					</td>
					<td>
						<html:select name="rs" property="xl" styleId="xl"
							style="width:120px;">
							<html:option value=""></html:option>
							<html:option value="专科">专科</html:option>
							<html:option value="本科">本科</html:option>
							<html:option value="硕士">硕士</html:option>
							<html:option value="博士">博士</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							<font color="red">*</font>银行卡号：
						</div>
					</td>
					<td>
						<input type="text" id="yhkh" name="yhkh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yhkh"/>" maxlength="30"
							onkeyup="checkInputData(this)">
					</td>
					<td>
						<div align="right">
							<font color="red">*</font>一卡通号：
						</div>
					</td>
					<td>
						<input type="text" id="ykth" name="ykth"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="ykth"/>" maxlength="20">
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							<font color="red">*</font>饭卡号：
						</div>
					</td>
					<td>
						<input type="text" id="fkh" name="fkh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="fkh"/>" maxlength="20">
					</td>
					<td>
						<div align="right">
							<font color="red">*</font>联系电话：
						</div>
					</td>
					<td>
						<input type="text" id="lxdh" name="lxdh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lxdh"/>" maxlength="30"
							onkeyup="checkInputData(this)">
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							<font color="red">*</font>家庭地址：
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jtdz" name="jtdz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtdz"/>" maxlength="60">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							曾获何种
						</div>
						<p align="center">
							奖励
							<br>
							<font color="red">(限制600个字以内)</font>
					</td>
					<td colspan="3">
						<logic:present name="rs" property="chjl">
							<html:textarea name="rs" property="chjl" rows='8' styleId="chjl"
								style="width:100%" />
						</logic:present>
						<logic:notPresent name="rs" property="chjl">
							<textarea name="chjl" rows='6' style="width:100%" type="_moz"
								id="chjl">
								<logic:iterate id="map" name="xszzList">
									<bean:write name="map" property="xn" />学年  获得<bean:write
										name="map" property="mc" />；</logic:iterate>
							</textarea>
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<td>
						<div style="vertical-align: middle;text-align: right;">
							本学年所获资助：
						</div>
					</td>
					<td colspan="3" style="vertical-align: text-top;">
						<logic:iterate id="map" name="xnxszzList">
							<bean:write name="map" property="xn" />学年  获得<bean:write
								name="map" property="mc" />；
						</logic:iterate>
					</td>
				</tr>
				<tr>
					<td>
						<div style="vertical-align: middle;text-align: right;">
							考核学年所受处分：
						</div>
					</td>
					<td colspan="3" style="vertical-align: text-top;">
						<logic:iterate id="map" name="xnxswjcfList">
							<bean:write name="map" property="xn" />学年  <bean:write
								name="map" property="cfyymc" />
							<bean:write name="map" property="cflbmc" />；
						</logic:iterate>
					</td>
				</tr>
				<tr>
					<td align="center">
						<br>
						申请理由
						<br>
						<br>
						<font color="red">(限制1000个字以内)</font>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="sqly" rows="10" styleId="sqly"
							style="width:100%" />
					</td>
				</tr>
				<tr>
					<td style="vertical-align: middle;text-align: right;">
						上传贫困证明：
					</td>
					<td align="left" colspan="3">
						<input type="file" name="uploadFile" style="width:70%">
						&nbsp;&nbsp;
						<font color="red">(要求文件大小10M以下)</font><br>
						<logic:present name="rs" property="wjsclj">
							<font color="red">已上传文件：</font>
							<a href="/xgxt/xcxyXszz.do?method=DownLoadFile&wjsclj=<bean:write name="rs" property="wjsclj"/>"><bean:write name="rs" property="wjmc" /></a>
						</logic:present>
					</td>
				</tr>
			</table>

			<div class="buttontool" id="btn"
				style="position: absolute;width:100%">
				<button type="button" class="button2" onClick="yz();">
					提交申请
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="toPrintOut();">
					打印报表
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2"
					onclick="showTopWin('xcxyXszz.do?method=getXscj&xh='+document.getElementById('xh').value,700,600)">
					学生成绩单
				</button>
			</div>
		</html:form>
	</logic:equal>
</body>
</html:html>
