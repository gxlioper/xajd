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
<head>
	<base target="_self" />
	<title><bean:message key="lable.title" /></title>
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
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		function checkStuInfo(){
			var xh = document.getElementById('xh').value;
			if(xh == ""){
				alert("学号不能为空!");
				return ;
			}
			refreshForm("whlg_xszz.do?method=gjzxdkhkxysq&doType=add");
		}
		
		function toPrintOut(){//输出相应的打印页面
			document.forms[0].action = "";
			document.forms[0].submit();
		}
	</script>
</head>
<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 国家助学贷款还款协议 - 国家助学贷款还款协议
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				现在不在申请时间内！！！
			</p>
		</center>
	</logic:equal>
	<%--
	<logic:equal name="sfksq" value="1">
		--%>
	<html:form action="/whlg_xszz.do?method=gjzxdkhkxysq" method="post">
		<input type="hidden" id="url" name="url"
			value="/whlg_xszz.do?method=gjzxdkhkxysq" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-bjmc-nj-zzmmmc-sfzh-zymc" />
		<input type="hidden" id="userOnLine" name="userOnLine"
			value="<bean:write name="userOnLine" scope="session"/>" />
		<%--<input type="hidden" id="sqsj" name="sqsj"
				value="<bean:write name="rs" property="sqsj" />">
			<input type="hidden" id="xysh" name="xysh"
				value="<bean:write name="rs" property="xysh" />">
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj" />">
			<input type="hidden" id="xxsh" name="xxsh"
				value="<bean:write name="rs" property="xxsh" />">
			<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj" />">
			--%>
		<table class="tbstyle" width="100%">
			<tbody>
			<tr>
				<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" style="width:10%">
							<font color="red">*</font>学号
						</td>
						<td align="left" >
							<html:text name='rs' property="xh" styleId="xh"
								onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="center" style="width:10%">
							<font color="red">*</font>学号
						</td>
						<td align="left">
							<input type="text" id="xh" name="xh"
								value="<bean:write name='rs' property="xh" />" readonly="readonly"/>
						</td>
					</logic:equal>
					<td>
						<div align="center">
							政治面貌
						</div>
					</td>
					<td>
						<input type="text" id="zzmmmc" name="zzmmmc"				
							value="<bean:write name='rs' property="zzmmmc" />" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							性别
						</div>
					</td>
					<td>
						<input type="text" id="xb" name="xb" 
							value="<bean:write name='rs' property="xb" />" readonly="readonly"/>
					</td>
					<td>
						<div align="center">
							身份证号
						</div>
					</td>
					<td>
						<input type="text" id="sfzh" name="sfzh" 
							value="<bean:write name='rs' property="sfzh" />" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td>
						<input type="text" id="xymc" name="xymc"
							value="<bean:write name='rs' property="xymc" />" readonly="readonly"/>
					</td>
					<td scope="row">
						<div align="center">
							专业
						</div>
					</td>
					<td>
						<input type="text" id="zymc" name="zymc"		
						value="<bean:write name='rs' property="zymc" />" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							姓名
						</div>
					</td>
					<td  scope="col">
						<input type="text" id="xm" name="xm"
							value="<bean:write name='rs' property="xm" />" readonly="readonly"/>
					</td>
					<td scope="row">
						<div align="center">
							班级
						</div>
					</td>
					<td>
						<input type="text" id="bjmc" name="bjmc"
							
							value="<bean:write name='rs' property="bjmc" />" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="4" bgcolor="#D0E0EE">
						中国银行国家助学贷款还款协议
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							借款人（甲方）
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jkr" name="jkr"  style="width:80%"
							value="<bean:write name='rs' property="jkr" />"/>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							有效证件号码
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="yxzjhm" name="yxzjhm" style="width:80%"
							value="<bean:write name='rs' property="yxzjhm" />"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							住所
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="zs" name="zs" style="width:80%"
							value="<bean:write name='rs' property="zs" />"/>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							工作单位
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="gzdw" name="gzdw" style="width:80%"
							value="<bean:write name='rs' property="gzdw" />"/>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							邮政编码
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="yzbm" name="yzbm"  style="width:80%"
						value="<bean:write name='rs' property="yzbm" />"/>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							联系电话
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="lxdh" name="lxdh" style="width:80%"
							value="<bean:write name='rs' property="lxdh" />"/>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							贷款人（乙方）
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="bjmc" name="bjmc" value="中国银行股份有限公司武汉东湖支行"
							readonly="readonly"  style="width:80%" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							地址
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="bjmc" name="bjmc" value="武汉市洪山区卓刀泉南路39号"
							readonly="readonly"   style="width:80%"/>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							邮政编码
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="bjmc" name="bjmc" value="430079"
							readonly="readonly"   style="width:80%"/>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							联系电话
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="bjmc" name="bjmc" style="width:80%"
							value="027-87521649、027-87521897" readonly="readonly" />
					</td>
				</tr>
			</tbody>
			<tbody id="tableadddel">
				<tr style="height:150px">
					<td colspan="4">
						<div style="width:90%">
							本协议为甲方和乙方签订的《中国银行国家助学贷款借款合同》（合同编号：
							<input type="text" id="htbh" name="htbh"
								value="<bean:write name='rs' property="htbh" />" />
							号）<br> 约定的从属协议，用以明确甲方向乙方归还国家助学贷款计划。经甲、乙两方协商同意后，订立如下还款协议：<br><br>
							一、截止
							<html:text name='rs' property="jzrq" styleId="jzrq"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('jzrq','y-mm-dd');" />
							， 甲方从乙方获得国家助学贷款共计人民币
							<input type="text" id="fkje" name="fkje"
								value="<bean:write name='rs' property="fkje" />" />
							元（大写）； <br><br>
							二、甲方于
							<html:text name='rs' property="jf_lkxxsj" styleId="jf_lkxxsj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('jf_lkxxsj','y-mm-dd');" />
							日因<input type="text" id="jf_lkxx_yy" name="jf_lkxx_yy"
								value="<bean:write name='rs' property="jf_lkxx_yy" />" />
							原因， 正式离开
							<input type="text" id="jflkxx_mc" name="jflkxx_mc"
								value="<bean:write name='rs' property="jflkxx_mc" />" />
							（所在学校）。 <br><br>
							三、甲方采用以下第
								<input type="text" id="fk_fs" name="fk_fs"
								value="<bean:write name='rs' property="fk_fs" />" />
							方式 按
							<input type="text" id="fk_jtfs" name="fk_jtfs"
								value="<bean:write name='rs' property="fk_jtfs" />" />
							（月） <br>分
							<input type="text" id="fjq" name="fjq"
								value="<bean:write name='rs' property="fjq" />" />
							期 归还贷款本金及利息，<br>从
							<html:text name='rs' property="fklx_kssj" styleId="fklx_kssj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('fklx_kssj','y-mm-dd');" />
							至
							<html:text name='rs' property="fklx_jssj" styleId="fklx_jssj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('fklx_jssj','y-mm-dd');" />
							共
							<input type="text" id="fklxys" name="fklxys"
								value="<bean:write name='rs' property="fklxys" />" />
							月归还贷款利息；<br> 从
							<html:text name='rs' property="fkbjlx_kssj" styleId="fkbjlx_kssj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('fkbjlx_kssj','y-mm-dd');" />
							至
							<html:text name='rs' property="fkbjlx_jssj" styleId="fkbjlx_jssj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('fkbjlx_jssj','y-mm-dd');" />
							共
							<input type="text" id="fkbjlxys" name="fkbjlxys"
								value="<bean:write name='rs' property="fkbjlxys" />" />
							月归还贷款本金及利息：<br><br>
							四、经双方确认的借款，在本还款协议履行期间，按贷款期限执行中国人民银行同档次法定利率。<br>
							如遇法定利率调整，乙方将执行调整后的利率，无须另行通知甲方；
							<br><br>
							五、甲方授权乙方直接从甲方在乙方开立的账户中扣款，用于归还借款本息，<br> 账户户名为：
							<input type="text" id="zffm" name="zffm"
								value="<bean:write name='rs' property="zffm" />" />
							，账户号为：
							<input type="text" id="zfh" name="zfh"
								value="<bean:write name='rs' property="zfh" />" />
							；<br><br>
							六、甲方承诺在离校手续办妥后一个月内将《中国银行国家助学贷款联系方式确认函》寄送回乙方；<br>
							如甲方工作单位、联系方式有变动，必须及时通知乙方；
							<br><br>七、本协议所有条款甲方已经与乙方进行了充分的协商；
							<br><br>八、本协议作为《国家助学贷款借款合同》的组成部分，与《国家助学贷款借款合同》具有同等法律效力。
						</div>
					</td>
				</tr>
			</tbody>
		</table>
		<div class="buttontool" id="btn" style="position: absolute;width:100%">
			<logic:notEqual value="view" name="rs" property="doType">
				<button type="button" class="button2" onClick="checkStuInfo();" style="width:80px">
					提交申请
				</button>&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onClick="toPrintOut();" style="width:80px">
					打 印
				</button>&nbsp;&nbsp;&nbsp;&nbsp;
				</logic:notEqual>
			<logic:notEqual value="student" scope="session" name="userOnLine">
				<button type="button" class="button2" onClick="Close();" style="width:80px">
					关闭
				</button>
			</logic:notEqual>
		</div>
	</html:form>
	<%--
	</logic:equal>
--%>
</body>
<logic:present name="ok">
	<logic:match value="ok" name="ok">
		<script language="javascript">
	   			alert("保存成功！");
	   			if(window.dialogArguments){
	   				dialogArgumentsQueryChick();
	   				Close();
	   			}
	   		
	        </script>
	</logic:match>
	<logic:match value="no" name="ok">
		<script language="javascript">
	        	alert("保存失败！");
	        </script>
	</logic:match>
</logic:present>
<logic:present name="have">
	<logic:match value="have" name="have">
		<script language="javascript">
	         	alert("已通过审核，不能申请！");
	         </script>
	</logic:match>
</logic:present>
</html:html>
