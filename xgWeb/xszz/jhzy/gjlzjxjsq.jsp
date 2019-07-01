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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function dataSave(){
			var xh = document.getElementById('xh').value;
			var sqly = document.getElementById('sqly').value;
			var chhzjl = document.getElementById('chhzjl').value;
			
			if((xh == null) || (xh == "")){
				alert("学号不能为空!");
				return false;
			}
	        if(chhzjl != null){
	         	if(chhzjl.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("曾经获何种奖励不能大于200个字符");
	          		 return false;
	       		 }
	       	}			
			if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 2000){	         
	          		 alert("申请理由不能大于2000个字符");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/jhzyjsxy_xszz.do?method=gjlzjxjsq&doType=save";
			document.forms[0].submit();
			$("buttonSave").disabled=true;
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//输出相应的打印页面
			document.forms[0].action = "/xgxt/jhzyjsxy_xszz.do?method=gjlzjxjsqb";
			document.forms[0].submit();
		}
		function changeSr(){
			var jtzrks = document.getElementById('jtzrks').value;
			var rjysr = document.getElementById('rjysr').value;
			
			if((jtzrks == null) || (jtzrks == "")){
				jtzrks = "0";
			}
			if((rjysr == null) || (rjysr == "")){
				rjysr = "0";
			}
			document.getElementById('jtyzsr').value = toInt(jtzrks) * toInt(rjysr);
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 国家励志奖学金申请表
		</div>
	</div>
	<html:form action="/jhzyjsxy_xszz" method="post">
		<input type="hidden" id="url" name="url"
			value="/jhzyjsxy_xszz.do?method=gjlzjxjsq" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-bjmc" />
		<input type="hidden" id="pkVal" name="pkVal"
			value="<bean:write name="rsJxj" property="pkVal" />">
		<input type="hidden" id="xxshyj" name="xxshyj"
			value="<bean:write name="rsJxj" property="xxshyj" />">

		<logic:present name="done">
			<logic:equal value="true" name="done">
				<script language="javascript">
	         	alert("提交成功！");
	         	</script>
			</logic:equal>
			<logic:equal value="false" name="done">
				<script language="javascript">
	         	alert("提交失败！");
	         	</script>
			</logic:equal>
		</logic:present>
		<logic:present name="isPASS">
			<logic:match value="is" name="isPASS">
				<script language="javascript">
	         			alert("已通过学校审核，不能再申请！");
	         		</script>
			</logic:match>
		</logic:present>
		<logic:equal value="-1" name="sfksq">
			<p align="center">
				<font color="red">目前不在申请时间范围内，暂不开放申请！</font>
			</p>
			<script language="javascript">
		  $("buttonSave").disabled=true;
  </script>
		</logic:equal>
		<table class="tbstyle" width="90%">
			<tr>
				<logic:equal name="userOnLine" value="teacher" scope="session">
					<td align="center" width="16%">
						<font color="red">*</font>学号
					</td>
					<td align="left" width="34%">
						<html:text name='rs' property="xh" styleId="xh"
							readonly="true"
							onkeypress="autoFillStuInfo(event.keyCode,this)" />
						<logic:notEqual value="modi" name="type">
						<button type="button" onclick="showTopWin('/xgxt/stu_info.do?kns=yes',750,550);"
							class="btn_01" id="buttonFindStu">
							选择
						</button>
						</logic:notEqual>
					</td>
				</logic:equal>
				<logic:equal name="userOnLine" value="student" scope="session">
					<td align="center" width="16%">
						<font color="red">*</font>学号
					</td>
					<td align="left" width="34%">
						<input type="text" id="xh" name="xh" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xh" />" readonly="true">
					</td>
				</logic:equal>
				<td width="16%">
					<div align="center">
						姓名
					</div>
				</td>
				<td width="34%">
					<input type="text" readonly="readonly" id="xm" name="xm"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xm"/>" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						性别
					</div>
				</td>
				<td>
					<input type="text" id="xb" readonly="readonly" name="xb"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xb"/>">
				</td>
				<td>
					<div align="center">
						出生日期
					</div>
				</td>
				<td>
					<input type="text" id="csrq" name="csrq" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="csrq"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						民族
					</div>
				</td>
				<td>
					<input type="text" id="mzmc" readonly="readonly" name="mzmc"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="mzmc"/>">
				</td>
				<td>
					<div align="center">
						政治面貌
					</div>
				</td>
				<td>
					<input type="text" id="zzmmmc" name="zzmmmc" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="zzmmmc"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						年级
					</div>
				</td>
				<td>
					<input type="text" id="nj" readonly="readonly" name="nj"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="nj"/>">
				</td>
				<td>
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />名称
					</div>
				</td>
				<td>
					<input type="text" id="xymc" name="xymc" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xymc"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						专业名称
					</div>
				</td>
				<td>
					<input type="text" id="zymc" readonly="readonly" name="zymc"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="zymc"/>">
				</td>
				<td>
					<div align="center">
						班级名称
					</div>
				</td>
				<td>
					<input type="text" id="bjmc" name="bjmc" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="bjmc"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						身份证号
					</div>
				</td>
				<td>
					<input type="text" id="sfzh" readonly="readonly" name="sfzh"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="sfzh"/>">
				</td>
				<td>
					<div align="center">
						学制
					</div>
				</td>
				<td>
					<input type="text" id="xz" name="xz" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xz"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						年度
					</div>
				</td>
				<td>
					<input type="text" id="nd" name="nd" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="nd"/>">
				</td>
				<td>
					<div align="center">
						入学时间
					</div>
				</td>
				<td>
					<input type="text" id="rxrq" name="rxrq" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="rxrq"/>">
				</td>

			</tr>
			<tr>
				<td>
					<div align="center">
						曾获何种奖励
					</div>
				</td>
				<td colspan="3">
					<html:textarea name="rsJxj" property="chhzjl" rows='3'
						style="width:100%" onblur="yzdx(this,'sqly')" />

				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						家庭户口
					</div>
				</td>
				<td>
					<html:radio name="rsJxj" property="jthk" value="城镇">城镇</html:radio>
					&nbsp;&nbsp;
					<html:radio name="rsJxj" property="jthk" value="农村">农村</html:radio>
				</td>
				<td>
					<div align="center">
						家庭人口总数
					</div>
				</td>
				<td>
					<html:text name="rsJxj" property="jtzrks" maxlength="10"></html:text>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						家庭月总收入
					</div>
				</td>
				<td>
					<html:text name="rsJxj" property="jtyzsr" maxlength="10"></html:text>
				</td>
				<td>
					<div align="center">
						人均月收入
					</div>
				</td>
				<td>
					<html:text name="rsJxj" property="rjysr" maxlength="10"></html:text>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						收入来源
					</div>
				</td>
				<td>
					<html:text name="rsJxj" property="srly" maxlength="50"></html:text>
				</td>
				<td>
					<div align="center">
						家庭地址
					</div>
				</td>
				<td>
					<html:text name="rsJxj" property="jtzz" style="width:100%"
						maxlength="100"></html:text>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						邮政编码
					</div>
				</td>
				<td>
					<html:text name="rsJxj" property="yzbm" maxlength="6"
						onblur='onlyNumInput(this);'></html:text>
				</td>
				<td>
					<div align="center">
						个人联系电话
					</div>
				</td>
				<td>
					<html:text name="rsJxj" property="lxdh" maxlength="20"
						onblur='onlyNumInput(this);'></html:text>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						学习等情况
					</div>
				</td>
				<td colspan="3">
					<div>
						该学年必修课程数&nbsp;
						<html:text name="rsJxj" property="gxnbxkcs" style="width:50px"
							maxlength="4" onkeypress="return numInputValue(this,4,event)"
							onblur='onlyNumInput(this);' />
						&nbsp;门，其中， 优秀&nbsp;
						<html:text name="rsJxj" property="yxkcs" style="width:50px"
							maxlength="4" onkeypress="return numInputValue(this,4,event)"
							onblur='onlyNumInput(this);' />
						&nbsp;门，良好&nbsp;
						<html:text name="rsJxj" property="lhkcs" style="width:50px"
							maxlength="4" onkeypress="return numInputValue(this,4,event)"
							onblur='onlyNumInput(this);' />
						&nbsp;门
					</div>
					<div>
						成绩排名(所在班级)&nbsp;
						<html:text name="rsJxj" property="cjpm" style="width:50px"
							maxlength="4" onkeypress="return numInputValue(this,4,event)"
							onblur='onlyNumInput(this);' />
						&nbsp;(名次/总人数)
					</div>
					<div>
						综合考评成绩&nbsp;
						<html:text name="rsJxj" property="zhkpcj" style="width:50px"
							maxlength="4" onkeypress="return sztzNumInputValue(this,4,event)"
							onblur='onlyNumInput(this);' />
						&nbsp;分，排名&nbsp;
						<html:text name="rsJxj" property="zhkppm" style="width:50px"
							maxlength="4" onkeypress="return numInputValue(this,4,event)"
							onblur='onlyNumInput(this);' />
						&nbsp;(名次/总人数)
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						申请理由
					</div>
				</td>
				<td colspan="3">
					<html:textarea name="rsJxj" property="sqly" rows='10'
						style="width:100%" onblur="yzdx(this,'sqly')" />
					<input type="hidden" id="sqly" name="sqly" value="">
				</td>
			</tr>
		</table>
		<div class="buttontool" id="btn" style="position: absolute;width:90%">
			<button type="button" class="button2" id="buttonSave" onClick="dataSave()">
				提交申请
			</button>
			<button type="button" class="button2" onClick="toPrintOut();" id="buttonPrint">
				打&nbsp;&nbsp;&nbsp;&nbsp;印
			</button>
		</div>
	</html:form>
</body>
<logic:equal name="isKns" value="false">
	<script language="javascript">
	      alert("该生目前不是困难生，不能进行申请!");
		  $("buttonSave").disabled=true;
		</script>
</logic:equal>
</html:html>
