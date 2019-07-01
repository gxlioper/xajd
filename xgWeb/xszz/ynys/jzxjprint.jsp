<%@ page language="java" contentType="text/html;charset=GBK"%>

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
<base target="_self">
<head>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta http-equiv="Pragma" http-equiv="no-cache" />
	<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
	<meta http-equiv="Expires" http-equiv="0" />
	<meta name="Copyright" content="正方软件 zfsoft" />
</head>
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
<body>
	<html:form action="gjjzxjsqb.do" method="post">
		<input type="hidden" name="tableName" id="tableName"
			value="<bean:write name="sqlb" />" />
		<p align="center" style="font-size:24px">
			${sqlb}申请表
		</p>
		<table width="100%" class="tbstyle" id="theTable">
			<tr>
				<logic:equal name="isYNYS" value="is">
					<logic:equal value="gjzxdkjbzx" name="titleName">
						<td width="46" rowspan="4">
					</logic:equal>
					<logic:notEqual value="gjzxdkjbzx" name="titleName">
						<td width="46" rowspan="6">
					</logic:notEqual>
				</logic:equal>
				<logic:notEqual name="isYNYS" value="is">
					<td width="46" rowspan="4">
				</logic:notEqual>
				
					<p align="center">
						本
					</p>
					<p align="center">
						人
					</p>
					<p align="center">
						情
					</p>
					<p align="center">
						况
					</p>
				</td>
				<td width="58" height="25" nowrap>
					<p align="center">
						姓名
					</p>
				</td>
				<td height="25">
					<div align="center">
						<bean:write name="rs" property="xm" />
					</div>
				</td>
				<td width="134" height="25">
					<p align="center">
						性别
					</p>
				</td>
				<td width="44" height="25">
					<bean:write name="rs" property="xb" />
				</td>
				<td height="25">
					<p align="center">
						出生年月
					</p>
				</td>
				<td height="25">
					<bean:write name="rs" property="csny" />
				</td>
				<td width="62" height="25">
					<p align="center">
						民 族
					</p>
				</td>
				<td width="65" height="25" align="center">
					<bean:write name="rs" property="mzmc" />
				</td>
			</tr>
			<tr>
				<td height="25">
					<p align="center">
						学号
					</p>
				</td>
				<td height="25" colspan="3">
					<bean:write name="rs" property="xh" />
				</td>
				<td height="25">
					<p align="center">
						入学时间
					</p>
				</td>
				<td height="25" colspan="3" align="center">
					<bean:write name="rs" property="rxny" />
				</td>
			</tr>
			<logic:equal name="isYNYS" value="is">
			<!--非云南艺术学院国家助学贷款奖补专项资金助学金-->
			<logic:notEqual value="gjzxdkjbzx" name="titleName">
			<tr>
				<td height="25">
					<p align="center">
						政治面貌
					</p>
				</td>
				<td height="25" colspan="3">
					<bean:write name="rs" property="zzmmmc" />
				</td>
				<td height="25">
					<p align="center">
						身份证号
					</p>
				</td>
				<td height="25" colspan="3" align="center">
					<bean:write name="rs" property="sfzh" />
				</td>
			</tr>
			<tr>
				<td height="25">
					<p align="center">
						上学年综合测评成绩
					</p>
				</td>
				<td height="25" colspan="3">
					<bean:write name="rs" property="sxncj" />
				</td>
				<td height="25">
					<p align="center">
						联系电话
					</p>
				</td>
				<td height="25" colspan="3" align="center">
					<bean:write name="rs" property="lxdh" />
				</td>
			</tr>
			</logic:notEqual>
			<!--end非云南艺术学院国家助学贷款奖补专项资金助学金-->
			</logic:equal>
			
			<!--非云南艺术学院国家助学贷款奖补专项资金助学金-->
			<logic:notEqual value="gjzxdkjbzx" name="titleName">
			<tr>
				<td height="28" colspan="8" nowrap>
					<p align="center">
						&nbsp;
						<bean:write name="rs" property="xxmc" />
						&nbsp;大学&nbsp;
						<bean:write name="rs" property="xy" />
						&nbsp;<bean:message key="lable.xsgzyxpzxy" />&nbsp;
						<bean:write name="rs" property="xmc" />
						&nbsp;系&nbsp;
						<bean:write name="rs" property="bjmc" />
						&nbsp;班
					</p>
				</td>
			</tr>
			</logic:notEqual>
			<!--end非云南艺术学院国家助学贷款奖补专项资金助学金-->
			<logic:equal value="gjzxdkjbzx" name="titleName">
			<tr>
				<td height="28" colspan="8" nowrap>
					<p align="center">
						&nbsp;
						<bean:write name="rs" property="xy" />
						&nbsp;<bean:message key="lable.xb" />&nbsp;                 
						<bean:write name="rs" property="nj" />
						&nbsp;级&nbsp;               
						<bean:write name="rs" property="xmc" />
						&nbsp;专业                                                       
					</p>
				</td>
			</tr>
			</logic:equal>
			<tr>
				<td width="58" height="3%">
					<p align="center">
						曾获何种
					</p>
					<p align="center">
						奖 励
					</p>
				</td>
				<td height="3%" colspan="7">
					<bean:write name="rs" property="jlxx" />
				</td>
			</tr>
			<tr>
				<td width="46" rowspan="3" align="center">
					家
					<br/>
					庭
					<br/>
					经
					<br/>
					济
					<br/>
					情
					<br/>
					况
				</td>
				<td width="58" height="66">
					<p align="center">
						<!--云南艺术学院国家助学贷款奖补专项资金助学金-->
						<logic:equal value="gjzxdkjbzx" name="titleName">家庭</logic:equal>
						<!--end云南艺术学院国家助学贷款奖补专项资金助学金-->
						<!--非云南艺术学院国家助学贷款奖补专项资金助学金-->
						<logic:notEqual value="gjzxdkjbzx" name="titleName">家庭户口</logic:notEqual>
						<!--end非云南艺术学院国家助学贷款奖补专项资金助学金-->
					</p>
				</td>
				<!--云南艺术学院国家助学贷款奖补专项资金助学金-->
				<logic:equal value="gjzxdkjbzx" name="titleName">
					<td height="66" colspan="4">
						<p align="center">
							<logic:equal value="农村" name="rs" property="radJthk">
								A、城镇（  ）&nbsp;&nbsp;&nbsp;&nbsp;B、农村（√  ）
							</logic:equal>
							<logic:equal value="城镇" name="rs" property="radJthk">
								A、城镇（√   ）&nbsp;&nbsp;&nbsp;&nbsp;B、农村（ ）
							</logic:equal>							
						</p>
					</td>
				</logic:equal>
				<!--end云南艺术学院国家助学贷款奖补专项资金助学金-->

				<!--云南艺术学院国家助学贷款奖补专项资金助学金-->
				<logic:notEqual value="gjzxdkjbzx" name="titleName">
					<td height="66" colspan="4">
						<p align="center">
							A、城镇（  ）&nbsp;&nbsp;&nbsp;&nbsp;B、农村（  ）
						</p>
					</td>
				</logic:notEqual>
				<!--end云南艺术学院国家助学贷款奖补专项资金助学金-->

				
				<td width="107" height="66">
					<p align="center">
						<!--云南艺术学院国家助学贷款奖补专项资金助学金-->
						<logic:equal value="gjzxdkjbzx" name="titleName">家庭人口数</logic:equal>
						<!--end云南艺术学院国家助学贷款奖补专项资金助学金-->
						<!--非云南艺术学院国家助学贷款奖补专项资金助学金-->
						<logic:notEqual value="gjzxdkjbzx" name="titleName">家庭人口总数</logic:notEqual>
						<!--end非云南艺术学院国家助学贷款奖补专项资金助学金-->
					</p>
				</td>
				<td height="66" colspan="2">
					<div align="center">
						<bean:write name="rs" property="hkrs" />
					</div>
				</td>
			</tr>
			<tr>
				<td width="58" height="43">
					<p align="center">
						家庭月总收入
					</p>
				</td>
				<td width="120" height="43">
					<bean:write name="rs" property="jtyzsr" />
				</td>
				<td height="43" colspan="2">
					<p align="center">
						<!--云南艺术学院国家助学贷款奖补专项资金助学金-->
						<logic:equal value="gjzxdkjbzx" name="titleName">人均月收</logic:equal>
						<!--end云南艺术学院国家助学贷款奖补专项资金助学金-->
						<!--非云南艺术学院国家助学贷款奖补专项资金助学金-->
						<logic:notEqual value="gjzxdkjbzx" name="titleName">人均月收入</logic:notEqual>
						<!--end非云南艺术学院国家助学贷款奖补专项资金助学金-->						
					</p>
				</td>
				<td width="109" height="43">
					<bean:write name="rs" property="jtrjsr" />
				</td>
				<td width="107" height="43" align="center">
					收入来源
				</td>
				<td height="43" colspan="2">
					<div align="center">
						<bean:write name="rs" property="jtsrly" />
					</div>
				</td>
			</tr>
			<tr>
				<td width="58" height="55">
					<p align="center">
						家庭住址
					</p>
				</td>
				<td height="55" colspan="4">
					<div align="center">
						<bean:write name="rs" property="jtzz" />
					</div>
				</td>
				<td width="107" height="55">
					<p align="center">
						邮政编码
					</p>
				</td>
				<td height="55" colspan="2">
					<div align="center">
						<bean:write name="rs" property="yzbm" />
					</div>
				</td>
			</tr>
			<tr>
				<td width="46" rowspan="6">
					<p align="center">
						家
					</p>
					<p align="center">
						庭
					</p>
					<p align="center">
						成
					</p>
					<p align="center">
						员
					</p>
					<p align="center">
						情
					</p>
					<p align="center">
						况
					</p>
				</td>
				<td width="58" height="20">
					<p align="center">
						姓名
					</p>
				</td>
				<td width="120" height="30">
					<p align="center">
						年龄
					</p>
				</td>
				<td height="30" colspan="2">
					<p align="center">
						与本人关系
					</p>
				</td>
				<td height="30" colspan="4">
					<p align="center">
						工作或学习单位
					</p>
				</td>
			</tr>
			<tr>
				<td width="58" height="30">
					<div align="center">
						<bean:write name="rs" property="jtcy1_xm" />
					</div>
				</td>
				<td width="120" height="30">
					<div align="center">
						&nbsp;
						<bean:write name="rs" property="jtcy1_nl" />
						&nbsp;
					</div>
				</td>
				<td height="30" colspan="2">
					<div align="center">
						<bean:write name="rs" property="jtcy1_gx" />
					</div>
				</td>
				<td width="109" height="30" colspan="4">
					<div align="center">
						<bean:write name="rs" property="jtcy1_gzdz" />
					</div>
				</td>
			</tr>
			<tr>
				<td width="58" height="30">
					<div align="center">
						<bean:write name="rs" property="jtcy2_xm" />
					</div>
				</td>
				<td width="120" height="30">
					<div align="center">
						&nbsp;
						<bean:write name="rs" property="jtcy2_nl" />
						&nbsp;
					</div>
				</td>
				<td height="30" colspan="2">
					<div align="center">
						<bean:write name="rs" property="jtcy2_gx" />
					</div>
				</td>
				<td width="109" height="30" colspan="4">
					<div align="center">
						<bean:write name="rs" property="jtcy2_gzdz" />
					</div>
				</td>
			</tr>
			<tr>
				<td width="58" height="30">
					<div align="center">
						<bean:write name="rs" property="jtcy3_xm" />
					</div>
				</td>
				<td width="120" height="30">
					<div align="center">
						&nbsp;
						<bean:write name="rs" property="jtcy3_nl" />
						&nbsp;
					</div>
				</td>
				<td height="30" colspan="2">
					<div align="center">
						<bean:write name="rs" property="jtcy3_gx" />
					</div>
				</td>
				<td width="109" height="30" colspan="4">
					<div align="center">
						<bean:write name="rs" property="jtcy3_gzdz" />
					</div>
				</td>
			</tr>
			<tr>
				<td width="58" height="30">
					<div align="center">
						<bean:write name="rs" property="jtcy4_xm" />
					</div>
				</td>
				<td width="120" height="30">
					<div align="center">
						&nbsp;
						<bean:write name="rs" property="jtcy4_nl" />
						&nbsp;
					</div>
				</td>
				<td height="30" colspan="2">
					<div align="center">
						<bean:write name="rs" property="jtcy4_gx" />
					</div>
				</td>
				<td width="109" height="30" colspan="4">
					<div align="center">
						<bean:write name="rs" property="jtcy4_gzdz" />
					</div>
				</td>
			</tr>
			<tr>
				<td width="58" height="30">
					<div align="center">
						<bean:write name="rs" property="jtcy5_xm" />
					</div>
				</td>
				<td width="120" height="30">
					<div align="center">
						&nbsp;
						<bean:write name="rs" property="jtcy5_nl" />
						&nbsp;
					</div>
				</td>
				<td height="30" colspan="2">
					<div align="center">
						<bean:write name="rs" property="jtcy5_gx" />
					</div>
				</td>
				<td width="109" height="30" colspan="4">
					<div align="center">
						<bean:write name="rs" property="jtcy5_gzdz" />
					</div>
				</td>
			</tr>
			<logic:equal name="titleName" value="xy">
			<tr>
				<td height="30" colspan="9" valign="top">
					奖学金级别:<bean:write name="rs" property="jbmc" />
				</td>
			</tr>
			</logic:equal>
			<tr>
				<td height="60" colspan="9" valign="top">
					<p>
						申请理由：
					</p>
					<bean:write name="rs" property="sqly" />
					<p/>
					<p/>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						申请人签名：
						<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年						
						&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;&nbsp;日
					</p>
				</td>
			</tr>
			<!--云南艺术学院国家助学贷款奖补专项资金助学金-->
			<logic:equal value="gjzxdkjbzx" name="titleName">
			<tr>
				<td height="89" colspan="4" valign="top">
					<p>
						<bean:message key="lable.xb" />审核意见：
					</p>
					<bean:write name="rs" property="xyshyj" />&nbsp;
					<p>
						<br>
						<br>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
					</p>
				</td>
				<td height="89" colspan="5" valign="top">
					<p>
						学校审核意见：
					</p>
					<bean:write name="rs" property="xxshyj" />&nbsp;
					<p>
						<br>
						<br>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
					</p>
				</td>
			</tr>
			</logic:equal>
			<!--end云南艺术学院国家助学贷款奖补专项资金助学金-->

			<logic:notEqual value="gjzxdkjbzx" name="titleName">
			<tr>
				<td height="89" colspan="9" valign="top">
					<p>
						<bean:message key="lable.xsgzyxpzxy" />审核意见：
					</p>
					<bean:write name="rs" property="xyshyj" />
					<p>
						<br>
						<br>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						党委副书记签名：
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年
						&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;&nbsp;日
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="9" valign="top">
					<p>
						学校审核意见：
					</p>
					<bean:write name="rs" property="xxshyj" />
					<p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						（公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年
						&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;&nbsp;日
					</p>
				</td>
			</tr>
			</logic:notEqual>
			
		</table>
	</html:form>
	<div align=center>
		<logic:equal name="isZXJ" value="is">
			<input  value="打印"
				onclick="expTab('theTable','国家助学金申请表')" />
		</logic:equal>
		<logic:equal name="isZXJ" value="no">
			<input  value="打印" name="button_print"
				onclick="expTab('theTable','国家奖学金申请表')" />
		</logic:equal>
	</div>
</body>
</html:html>
