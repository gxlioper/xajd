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
			refreshForm("whlg_xszz.do?method=gjzxdksp&doType=add");
		}
	
		function toPrintOut(){//输出相应的打印页面
			document.forms[0].action = "";
			document.forms[0].submit();
		}
		
		//unchecked
		function cancelXz(){
			var xlns = document.getElementsByName("xlns");
			for(var i=0;i<xlns.length;i++){
				xlns[i].checked = false;
			}	
		}
		
		function cancelSs(){
			var xl = document.getElementsByName("xl");
			for(var i=0;i<xl.length;i++){
				if(xl[i].value == "研究生"){
					xl[i].checked = false;
				}else if(xl[i].value == "本科"){
					xl[i].checked = true;
				}
			}
		}
	</script>
</head>
<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 国家助学贷款申请审批 - 国家助学贷款申请审批
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
	<html:form action="/whlg_xszz.do?method=gjzxdksp" method="post">
		<input type="hidden" id="url" name="url"
			value="/whlg_xszz.do?method=gjzxdksp" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-bjmc-zzmmmc-sfzh-csrq-xz-zymc-nj" />
		<table class="tbstyle" width="100%">
			<tbody>
				<tr>
					<td rowspan="13">
						借
						<br>
						款
						<br>
						人
						<br>
						基
						<br>
						本
						<br>
						资
						<br>
						料
						<br>
					</td>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" style="width:12%">
							<font color="red">*</font>学号
						</td>
						<td align="left">
							<html:text name='rs' property="xh" styleId="xh"
								onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="center" style="width:12%">
							<font color="red">*</font>学号
						</td>
						<td align="left">
							<input type="text" id="xh" name="xh"
								value="<bean:write name='rs' property="xh" />"
								readonly="readonly" />
						</td>
					</logic:equal>
					<td>
						<div align="center">
							借款人姓名
						</div>
					</td>
					<td scope="col">
						<input type="text" id="xm" name="xm"
							value="<bean:write name='rs' property="xm" />"
							readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							出生年月
						</div>
					</td>
					<td>
						<input type="text" id="csrq" name="csrq"
							value="<bean:write name='rs' property="csrq" />"
							readonly="readonly" />
					</td>
					<td scope="row">
						<div align="center">
							性别
						</div>
					</td>
					<td>
						<logic:notEmpty name="rs" property="xb">
							<logic:match value="男" name="rs" property="xb">
								<input type="radio" name="xb" value="男" checked
									readonly="readonly">&nbsp;&nbsp;男
							    <input type="radio" name="xb" value="女" readonly="readonly">&nbsp;&nbsp;女
							</logic:match>
							<logic:notMatch value="男" name="rs" property="xb">
								<input type="radio" name="xb" value="男" readonly="readonly">&nbsp;&nbsp;男
							    <input type="radio" name="xb" value="女" checked
									readonly="readonly">&nbsp;&nbsp;女
							</logic:notMatch>
						</logic:notEmpty>
						<logic:empty name="rs" property="xb">
							<input type="radio" name="xb" value="男" readonly="readonly">&nbsp;&nbsp;男
							    <input type="radio" name="xb" value="女" readonly="readonly">&nbsp;&nbsp;女
						</logic:empty>
						<script type="text/javascript">
							var xm = document.getElementById("xm").value;
							if(xm != null && xm != ""){
								var xbGro = document.getElementsByName("xb");
								for(var i=0;i<xbGro.length;i++){
									xbGro[i].disabled = true;
								}
							}
						</script>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							民族
						</div>
					</td>
					<td>
						<input type="text" id="mzmc" name="mzmc"
							value="<bean:write name='rs' property="mzmc" />"
							readonly="readonly" />
					</td>
					<td scope="row">
						<div align="center">
							身份证号码
						</div>
					</td>
					<td>
						<input type="text" id="sfzh" name="sfzh"
							value="<bean:write name='rs' property="sfzh" />"
							readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td align="center">
						年级
					</td>
					<td>
						<input type="text" id="nj" name="nj" readonly="readonly"
							value="<bean:write name='rs' property="nj" />">
					</td>
					<td scope="row">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td>
						<input type="text" id="xymc" name="xymc"
							value="<bean:write name='rs' property="xymc" />"
							readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							专业
						</div>
					</td>
					<td>
						<input type="text" id="zymc" name="zymc"
							value="<bean:write name='rs' property="zymc" />"
							readonly="readonly" />
					</td>
					<td>
						<div align="center">
							班级
						</div>
					</td>
					<td>
						<input type="text" id="bjmc" name="bjmc"
							value="<bean:write name='rs' property="bjmc" />"
							readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td align="center">
						学制
					</td>
					<td>
						<input type="text" id="xz" name="xz" readonly="readonly"
							value="<bean:write name='rs' property="xz" />">
					</td>
					<td>
						<div align="center">
							就读学校
						</div>
					</td>
					<td>
						<input type="text" id="xxmc" name="xxmc"
							value="<bean:write name='rs' property="xxmc" />">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							入学年份
						</div>
					</td>
					<td>
						<input type="text" id="rxnf" name="rxnf"
							value="<bean:write name='rs' property="rxnf" />" />
					</td>
					<td scope="row">
						<div align="center">
							毕业时间
						</div>
					</td>
					<td>
						<input type="text" id="bynf" name="bynf"
							value="<bean:write name='rs' property="bynf"/>">
					</td>
				</tr>
				<tr>
					<td align="center">
						宿舍电话
					</td>
					<td>
						<input type="text" id="ssdh" name="ssdh"
							value="<bean:write name='rs' property="ssdh" />">
					</td>
					<td align="center">
						工商银行联名卡卡号
					</td>
					<td>
						<input type="text" id="ghlmkh" name="ghlmkh"
							value="<bean:write name='rs' property="ghlmkh" />">
					</td>
				</tr>
				<tr>
					<td align="center">
						学历
					</td>
					<td colspan="3">
						<input type="radio" name="xl" value="本科" />
						&nbsp;&nbsp;本 (
						<input type="radio" name="xlns" value="4" onclick="cancelSs()" />
						&nbsp;&nbsp;四 &nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="xlns" value="5" onclick="cancelSs()" />
						&nbsp;&nbsp;五
						)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="xl" value="研究生" onclick="cancelXz()" />
						&nbsp;&nbsp;研
						<input type="hidden" value="<bean:write name="rs" property="xl"/>"
							id="xlHidden" />
						<script type="text/javascript">
							var xlHidden = document.getElementById("xlHidden").value;
							var xlGro = document.getElementsByName("xl");
						/*	for(var k=0;k<xlGro.length;k++){
								if(xlGro[k].value == xlHidden){
									xlGro[k].checked = true;
								}
							}
						*/	
							var xz  = document.getElementById("xz").value;
							var var_xlns = document.getElementsByName("xlns");
							for(var i=0;i<var_xlns.length;i++){
								if(var_xlns[i].value == xz){
									var_xlns[i].checked = true;
									xlGro[0].checked = true;  //这里代表本科
								}
							}
							
							//判断在 学制 上有没有数据
							if (xz != null && xz != ""){
								for(var k=0;k<xlGro.length;k++){
									xlGro[k].disabled = true;
								}
								for(var j=0;j<var_xlns.length;j++){
									var_xlns[j].disabled = true;
								}
							}
							</script>
					</td>
				</tr>
			</tbody>
			<tr style="height:5px"></tr>
			<tbody>
				<tr>
					<td align="center" rowspan="3">
						<div align="center">
							贷
							<br>
							款
							<br>
							信
							<br>
							息
							<br>
						</div>
					</td>
					<td>
						<div align="center">
							申请贷款金额
						</div>
					</td>
					<td>
						<div>
							总金额：
							<input type="text" id="sqdkzje" name="sqdkzje"
								style="width:100px"
								value="<bean:write name='rs' property="sqdkzje" />">
							元
							<br>
							其中在校期间：（从申请贷款当年算起）
							<br>
							<div align="right">
								学杂费贷款总额：
								<input type="text" id="xzfdkzje" name="xzfdkzje"
									style="width:100px"
									value="<bean:write name='rs' property="xzfdkzje" />">
								元
								<br>
								住宿费贷款总额：
								<input type="text" id="zsfdkzje" name="zsfdkzje"
									style="width:100px"
									value="<bean:write name='rs' property="zsfdkzje" />">
								元
							</div>
						</div>
					</td>
					<td rowspan="2">
						<div align="center">
							年度放款金额
						</div>
					</td>
					<td rowspan="2">
						<div>
							<input type="text" id="nd_one" name="nd_one" style="width:60px"
								value="<bean:write name='rs' property="nd_one" />">
							&nbsp;&nbsp;年 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="text" id="nd_one_fkje" name="nd_one_fkje"
								style="width:60px"
								value="<bean:write name='rs' property="nd_one_fkje" />">
							&nbsp;&nbsp;元
							<br>
							<input type="text" id="nd_two" name="nd_two" style="width:60px"
								value="<bean:write name='rs' property="nd_two" />">
							&nbsp;&nbsp;年 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="text" id="nd_two_fkje" name="nd_two_fkje"
								style="width:60px"
								value="<bean:write name='rs' property="nd_two_fkje" />">
							&nbsp;&nbsp;元
							<br>
							<input type="text" id="nd_three" name="nd_three"
								style="width:60px"
								value="<bean:write name='rs' property="nd_three" />">
							&nbsp;&nbsp;年 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="text" id="nd_three_fkje" name="nd_three_fkje"
								style="width:60px"
								value="<bean:write name='rs' property="nd_three_fkje" />">
							&nbsp;&nbsp;元
							<br>
							<input type="text" id="nd_four" name="nd_four" style="width:60px"
								value="<bean:write name='rs' property="nd_four" />">
							&nbsp;&nbsp;年 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="text" id="nd_four_fkje" name="nd_four_fkje"
								style="width:60px"
								value="<bean:write name='rs' property="nd_four_fkje" />">
							&nbsp;&nbsp;元
							<br>
							<input type="text" id="nd_five" name="nd_five" style="width:60px"
								value="<bean:write name='rs' property="nd_five" />">
							&nbsp;&nbsp;年 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="text" id="nd_five_fkje" name="nd_five_fkje"
								style="width:60px"
								value="<bean:write name='rs' property="nd_five_fkje" />">
							&nbsp;&nbsp;元
							<br>
						</div>
					</td>
				</tr>
				<tr>
					<td align="center">
						在校年限
					</td>
					<td>
						<input type="text" id="zxnx" name="zxnx"
							value="<bean:write name='rs' property="zxnx" />">
						&nbsp;&nbsp;年&nbsp;&nbsp;(小写)
					</td>
				</tr>
				<tr>
					<td align="center">
						贷款期限
					</td>
					<td colspan="3">
						<input type="text" id="dkqx_months" name="dkqx_months"
							value="<bean:write name='rs' property="dkqx_months" />">
						&nbsp;&nbsp;月
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 自
						<html:text name='rs' property="dkqx_kssj" styleId="dkqx_kssj"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('dkqx_kssj','y-mm-dd');" />
						&nbsp;&nbsp;&nbsp;&nbsp; 至
						<html:text name='rs' property="dkqx_jssj" styleId="dkqx_jssj"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('dkqx_jssj','y-mm-dd');" />
					</td>
				</tr>
			</tbody>
			<tr style="height:3px">
			</tr>
			<tbody>
				<tr>
					<td rowspan="7">
						<div align="center">
							家
							<br>
							庭
							<br>
							基
							<br>
							本
							<br>
							情
							<br>
							况
						</div>
					</td>
					<td align="center">
						家庭详细住址
					</td>
					<td colspan="3">
						<input type="text" id="jkr_jtxxzz" name="jkr_jtxxzz"
							style="width:80%"
							value="<bean:write name='rs' property="jkr_jtxxzz" />">
					</td>
				</tr>
				<tr>
					<td align="center">
						邮编
					</td>
					<td>
						<input type="text" id="jkr_yb" name="jkr_yb"
							value="<bean:write name='rs' property="jkr_yb" />">
					</td>
					<td align="center">
						家庭联系电话
					</td>
					<td>
						<input type="text" id="jtdh" name="jtdh"
							value="<bean:write name='rs' property="jtdh" />">
					</td>
				</tr>
				<tr>
					<td align="center">
						父亲姓名
					</td>
					<td>
						<input type="text" id="jkr_fq_xm" name="jkr_fq_xm"
							value="<bean:write name='rs' property="jkr_fq_xm" />">
					</td>
					<td align="center">
						父亲身份证号码
					</td>
					<td>
						<input type="text" id="jkr_fq_sfzh" name="jkr_fq_sfzh"
							value="<bean:write name='rs' property="jkr_fq_sfzh" />">
					</td>
				</tr>
				<tr>
					<td align="center">
						父亲职业
					</td>
					<td colspan="3">
						<input type="text" id="jkr_fq_zy" name="jkr_fq_zy"
							style="width:80%"
							value="<bean:write name='rs' property="jkr_fq_zy" />">
					</td>

				</tr>
				<tr>
					<td align="center">
						母亲姓名
					</td>
					<td>
						<input type="text" id="jkr_mq_xm" name="jkr_mq_xm"
							value="<bean:write name='rs' property="jkr_mq_xm" />">
					</td>
					<td align="center">
						母亲身份证号码
					</td>
					<td>
						<input type="text" id="jkr_mq_sfzh" name="jkr_mq_sfzh"
							value="<bean:write name='rs' property="jkr_mq_sfzh" />">
					</td>
				</tr>
				<tr>
					<td align="center">
						母亲职业
					</td>
					<td colspan="3">
						<input type="text" id="jkr_mq_zy" name="jkr_mq_zy"
							style="width:80%"
							value="<bean:write name='rs' property="jkr_mq_zy" />">
					</td>
				</tr>
				<tr>
					<td align="center">
						家庭月收入
					</td>
					<td>
						<input type="text" id="jtysr" name="jtysr"
							value="<bean:write name='rs' property="jtysr" />">
						元
					</td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
			<tr style="height:3px">
			</tr>
			<tbody>
				<tr>
					<td align="center">
						<div align="center">
							备
							<br>
							注
						</div>
					</td>
					<td colspan="4">
						<span><font color="blue">限500个字符</font>
						</span>
						<html:textarea name='rs' property='bz' style="width:99%"
							styleId="bz" rows='6' />
					</td>
				</tr>
				<tr>
					<td align="center">
						<div align="center">
							信
							<br>
							贷
							<br>
							员
							<br>
							意
							<br>
							见
						</div>
					</td>
					<td colspan="4">
						<span><font color="blue">限500个字符</font>
						</span>
						<html:textarea name='rs' property='xdyyj' style="width:99%"
							styleId="xdyyj" rows='4' />
					</td>
				</tr>
				<tr>
					<td align="center">
						<div align="center">
							科
							<br>
							长
							<br>
							意
							<br>
							见
						</div>
					</td>
					<td colspan="4">
						<span><font color="blue">限500个字符</font>
						</span>
						<html:textarea name='rs' property='kzyj' style="width:99%"
							styleId="kzyj" rows='4' />
					</td>
				</tr>
				<tr>
					<td align="center">
						<div align="center">
							有
							<br>
							权
							<br>
							批
							<br>
							准
							<br>
							人
							<br>
							意
							<br>
							见
						</div>
					</td>
					<td colspan="4">
						<span><font color="blue">限500个字符</font>
						</span>
						<html:textarea name='rs' property='yqspryj' style="width:99%"
							styleId="yqspryj" rows='6' />
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
