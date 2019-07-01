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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function yz(){
			var userType = document.getElementById('userType').value;
			var xxshjg = document.getElementById('xxshjg').value;
			if(("未审核" != xxshjg ) && (userType != "admin")){
				alert("学校已审核，不能再修改审核结果!");
	          	return false;
			}
			 refreshForm('/xgxt/zgkydx_xszz.do?method=knsshSave');
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 审核 - 困难生审核 - 单个审核
				</div>
			</div>
			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("保存成功！");
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("保存失败！");
	         	</script>
				</logic:match>
			</logic:present>
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="8" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td colspan="2">
						<div align="center">
							学号
						</div>
					</td>
					<td align="left" colspan="3">
						<bean:write name='rs' property="xh" />
						<input type="hidden" id="xh" name="xh"
							value="<bean:write name='rs' property="xh" />" />
					</td>
					<td colspan="2">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="30%">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							性别
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xb" />
					</td>
					<td colspan="2">
						<div align="center">
							学制
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xz" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							年级
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="nj" />
					</td>
					<td colspan="2">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />名称
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xymc" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							专业名称
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="zymc" />
					</td>
					<td colspan="2">
						<div align="center">
							班级名称
						</div>
					</td>
					<td>
						<bean:write name="rs" property="bjmc" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							年度
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="nd" />
						<input type="hidden" id="nd" name="nd"
							value="<bean:write name='rs' property="nd" />" />
					</td>
					<td colspan="2">
						<div align="center">
							身份证号
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sfzh" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							宿舍
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="ss" />
					</td>
					<td colspan="2">
						<div align="center">
							电话
						</div>
					</td>
					<td>
						<bean:write name="rs" property="dh" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭所在地
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="jtszd" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							父亲单位电话
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="fqdwlxdh" />
					</td>
					<td colspan="2">
						<div align="center">
							母亲单位电话
						</div>
					</td>
					<td>
						<bean:write name="rs" property="mqdwlxdh" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭电话及邮编
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jtlxdhjyzbm" />
					</td>
					<td colspan="2">
						<div align="center">
							所在基层组织电话
						</div>
					</td>
					<td>
						<bean:write name="rs" property="szjclxdh" />
					</td>
				</tr>
				<tr>
					<td width="6%" rowspan="8">
						<div align="center">
							家
							<br />
							庭
							<br />
							成
							<br />
							员
						</div>
					</td>
					<td width="14%">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="10%">
						<div align="center">
							性别
						</div>
					</td>
					<td width="10%">
						<div align="center">
							与本人
							<br />
							关系
						</div>
					</td>
					<td width="10%">
						<div align="center">
							年龄
						</div>
					</td>
					<td width="10%">
						<div align="center">
							户口
							<br />
							类型
						</div>
					</td>
					<td width="10%">
						<div align="center">
							从业
							<br />
							状态
						</div>
					</td>
					<td>
						<div align="center">
							工作单位(就读学校)
						</div>
					</td>
				</tr>
				<tr>
					<td align="center">
						<bean:write name="rs" property="jtcy1_xm" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy1_xb" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy1_gx" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy1_nl" />
					</td>
					<td align="center">
						<logic:empty name="rs" property="jtcy1_xm">
						&nbsp;
					</logic:empty>
						<logic:notEmpty name="rs" property="jtcy1_xm">
							<bean:write name="rs" property="jtcy1_hklx" />
						</logic:notEmpty>
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy1_cyzt" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy1_gzdw" />
					</td>
				</tr>
				<tr>
					<td align="center">
						<bean:write name="rs" property="jtcy2_xm" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy2_xb" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy2_gx" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy2_nl" />
					</td>
					<td align="center">
						<logic:empty name="rs" property="jtcy2_xm">
						&nbsp;
					</logic:empty>
						<logic:notEmpty name="rs" property="jtcy2_xm">
							<bean:write name="rs" property="jtcy2_hklx" />
						</logic:notEmpty>
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy2_cyzt" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy2_gzdw" />
					</td>
				</tr>
				<tr>
					<td align="center">
						<bean:write name="rs" property="jtcy3_xm" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy3_xb" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy3_gx" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy3_nl" />
					</td>
					<td align="center">
						<logic:empty name="rs" property="jtcy3_xm">
						&nbsp;
					</logic:empty>
						<logic:notEmpty name="rs" property="jtcy3_xm">
							<bean:write name="rs" property="jtcy3_hklx" />
						</logic:notEmpty>
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy3_cyzt" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy3_gzdw" />
					</td>
				</tr>
				<tr>
					<td align="center">
						<bean:write name="rs" property="jtcy4_xm" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy4_xb" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy4_gx" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy4_nl" />
					</td>
					<td align="center">
						<logic:empty name="rs" property="jtcy4_xm">
						&nbsp;
					</logic:empty>
						<logic:notEmpty name="rs" property="jtcy4_xm">
							<bean:write name="rs" property="jtcy4_hklx" />
						</logic:notEmpty>
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy4_cyzt" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy4_gzdw" />
					</td>
				</tr>
				<tr>
					<td align="center">
						<bean:write name="rs" property="jtcy5_xm" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy5_xb" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy5_gx" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy5_nl" />
					</td>
					<td align="center">
						<logic:empty name="rs" property="jtcy5_xm">
						&nbsp;
					</logic:empty>
						<logic:notEmpty name="rs" property="jtcy5_xm">
							<bean:write name="rs" property="jtcy5_hklx" />
						</logic:notEmpty>
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy5_cyzt" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy5_gzdw" />
					</td>
				</tr>
				<tr>
					<td align="center">
						<bean:write name="rs" property="jtcy6_xm" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy6_xb" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy6_gx" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy6_nl" />
					</td>
					<td align="center">
						<logic:empty name="rs" property="jtcy6_xm">
						&nbsp;
					</logic:empty>
						<logic:notEmpty name="rs" property="jtcy6_xm">
							<bean:write name="rs" property="jtcy6_hklx" />
						</logic:notEmpty>
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy6_cyzt" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy6_gzdw" />
					</td>
				</tr>
				<tr>
					<td align="center">
						<bean:write name="rs" property="jtcy7_xm" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy7_xb" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy7_gx" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy7_nl" />
					</td>
					<td align="center">
						<logic:empty name="rs" property="jtcy7_xm">
						&nbsp;
					</logic:empty>
						<logic:notEmpty name="rs" property="jtcy7_xm">
							<bean:write name="rs" property="jtcy7_hklx" />
						</logic:notEmpty>
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy7_cyzt" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy7_gzdw" />
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<font color="red"> 注:1.家庭成员指除学生本人之外共同生活的直属血亲及兄弟姐妹； <br />
							&nbsp;&nbsp;&nbsp;2.户口类型分：A&nbsp;(农业户口)&nbsp;&nbsp;&nbsp;B&nbsp;(非农业户口)；
							<br />
							&nbsp;&nbsp;&nbsp;3.从业状态一栏仅填写工作、务农、下岗、打工、退休、上学(大学、中专、高中、初中、小学)等。
						</font>
					</td>
				</tr>
				<tr>
					<td colspan="8">
						申请贫困生具体理由
						<font color="red">(请在符合的情况后面简要说明)</font>
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div align="center">
							来自国家级贫困县的农村地区
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sqly_lzgjjpkxdncdq" />
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div align="center">
							属于享受最低生活保障的城镇家庭
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sqly_syxszdshbzdczjt" />
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div align="center">
							孤儿或经济困难的单亲家庭
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sqly_gehjjknddqjt" />
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div align="center">
							父母一方或双方下岗(失业)
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sqly_fmyfhsfxg" />
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div align="center">
							家庭成员中无18-55岁的青壮劳动力
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sqly_jtcywqzndl" />
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div align="center">
							家庭成员因残疾或疾病而丧失劳动能力
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sqly_jtcyycjhjbrssndl" />
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div align="center">
							家庭成员因患重大疾病需支付大额医疗费用
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sqly_jtcyyzdjbxzfdefy" />
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div align="center">
							家庭中有两个或以上成员正接受非义务教育
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sqly_jtcyzylghyscyzjsfywjy" />
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div align="center">
							家庭及成员因遭遇自然灾害或其他突发灾变
							<br />
							造成人身及财产的重大损失
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sqly_jtcyzszrzh" />
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div align="center">
							其他导致家庭经济困难情况
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sqly_qtqk" />
					</td>
				</tr>
				<tr>
					<td rowspan="8">
						<div align="center">
							家庭
							<br />
							经济
							<br />
							情况
							<br />
							证明
							<br />
							材料
							<br />
							清单
						</div>
					</td>
					<td rowspan="2">
						<div align="center">
							材料一
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							材料名称
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="zmcl1_mc" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							出具材料机关及联系电话
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="zmcl1_jg" />
					</td>
					<td>
						<bean:write name="rs" property="zmcl1_dh" />
					</td>
				</tr>
				<tr>
					<td rowspan="2">
						<div align="center">
							材料二
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							材料名称
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="zmcl2_mc" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							出具材料机关及联系电话
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="zmcl2_jg" />
					</td>
					<td>
						<bean:write name="rs" property="zmcl2_dh" />
					</td>
				</tr>
				<tr>
					<td rowspan="2">
						<div align="center">
							材料三
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							材料名称
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="zmcl3_mc" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							出具材料机关及联系电话
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="zmcl3_jg" />
					</td>
					<td>
						<bean:write name="rs" property="zmcl3_dh" />
					</td>
				</tr>
				<tr>
					<td rowspan="2">
						<div align="center">
							材料四
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							材料名称
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="zmcl4_mc" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							出具材料机关及联系电话
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="zmcl4_jg" />
					</td>
					<td>
						<bean:write name="rs" property="zmcl4_dh" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							申请时间
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sqsj" />
					</td>
					<td colspan="3">
					</td>
				</tr>
				<tr>
					<td colspan="8">
						贫困学生分为特别贫困学生(特困生)和一般贫困学生两类。
						<br />
						&nbsp;&nbsp;&nbsp;&nbsp;①&nbsp;特困生分为A、B两档：
						<br />
						&nbsp;&nbsp;&nbsp;&nbsp;特困生(A档)基本条件：家庭经济极其困难，基本上无生活来源；
						<br />
						&nbsp;&nbsp;&nbsp;&nbsp;特困生(B档)基本条件：家庭经济特别困难，在校实际生活费用低于平均100元/月。
						<br />
						&nbsp;&nbsp;&nbsp;&nbsp;②一般贫困学生(C档)基本条件：家庭经济比较困难，符合贫困学生申请建档条件。
						<br />
					</td>
				</tr>
				<logic:equal name="userType" value="xy">
					<tr>
						<td colspan="2">
							<div align="center">
								民主评议人数
							</div>
						</td>
						<td colspan="3">
							<input type="text" id="pyrs" name="pyrs" maxlength="5"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="pyrs"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
						<td colspan="2">
							<div align="center">
								A档人数
							</div>
						</td>
						<td>
							<input type="text" id="adrs" name="adrs" maxlength="5"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="adrs"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								B档人数
							</div>
						</td>
						<td colspan="3">
							<input type="text" id="bdrs" name="bdrs" maxlength="5"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="bdrs"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
						<td colspan="2">
							<div align="center">
								C档人数
							</div>
						</td>
						<td>
							<input type="text" id="cdrs" name="cdrs" maxlength="5"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="cdrs"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								不同意人数
							</div>
						</td>
						<td colspan="3">
							<input type="text" id="btyrs" name="btyrs" maxlength="5"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="btyrs"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
						<td colspan="2">
							<div align="center">
								存在不予建档人数
							</div>
						</td>
						<td>
							<input type="text" id="csbyjdrs" name="csbyjdrs" maxlength="5"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="csbyjdrs"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								存在不予建档具体情况
							</div>
						</td>
						<td colspan="6">
							<input type="text" id="byjdjtqk" name="byjdjtqk" maxlength="200"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="byjdjtqk"/>">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								调查情况
							</div>
						</td>
						<td colspan="3">
							<html:select name="rs" property="dcfyqk">
								<html:option value=""></html:option>
								<html:options collection="dcfyqkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<td colspan="2">
							<div align="center">
								审核时间
							</div>
						</td>
						<td>
							<input type="text" id=xbshsj name="xbshsj" readonly="readonly"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="xbshsj"/>">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								班级评议结果
							</div>
						</td>
						<td colspan="3">
							<html:select name="rs" property="bjpyjg">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
							<input type="hidden" id="xxshjg" name="xxshjg"
								value="<bean:write name='rs' property="xxshjg" />" />
						</td>
						<td colspan="2">
							<div align="center">
								系部审核结果
							</div>
						</td>
						<td>
							<html:select name="rs" property="xbshjg">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="userType" value="admin">
					<tr>
						<td colspan="2">
							<div align="center">
								民主评议人数
							</div>
						</td>
						<td colspan="3">
							<bean:write name="rs" property="pyrs"/>
							<input type="hidden" id="pyrs" name="pyrs"
								value="<bean:write name='rs' property="pyrs" />" />
						</td>
						<td colspan="2">
							<div align="center">
								A档人数
							</div>
						</td>
						<td>
							<bean:write name="rs" property="adrs"/>
							<input type="hidden" id="adrs" name="adrs"
								value="<bean:write name='rs' property="adrs" />" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								B档人数
							</div>
						</td>
						<td colspan="3">
							<bean:write name="rs" property="bdrs"/>
							<input type="hidden" id="bdrs" name="bdrs"
								value="<bean:write name='rs' property="bdrs" />" />
						</td>
						<td colspan="2">
							<div align="center">
								C档人数
							</div>
						</td>
						<td>
							<bean:write name="rs" property="cdrs"/>
							<input type="hidden" id="cdrs" name="cdrs"
								value="<bean:write name='rs' property="cdrs" />" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								不同意人数
							</div>
						</td>
						<td colspan="3">
							<bean:write name="rs" property="btyrs"/>
							<input type="hidden" id="btyrs" name="btyrs"
								value="<bean:write name='rs' property="btyrs" />" />
						</td>
						<td colspan="2">
							<div align="center">
								存在不予建档人数
							</div>
						</td>
						<td>
							<bean:write name="rs" property="csbyjdrs"/>
							<input type="hidden" id="csbyjdrs" name="csbyjdrs"
								value="<bean:write name='rs' property="csbyjdrs" />" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								存在不予建档具体情况
							</div>
						</td>
						<td colspan="6">
							<bean:write name="rs" property="byjdjtqk"/>
							<input type="hidden" id="byjdjtqk" name="byjdjtqk"
								value="<bean:write name='rs' property="byjdjtqk" />" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								调查情况
							</div>
						</td>
						<td colspan="3">
							<bean:write name="rs" property="dcfyqk"/>
							<input type="hidden" id="dcfyqk" name="dcfyqk"
								value="<bean:write name='rs' property="dcfyqk" />" />
						</td>
						<td colspan="2">
							<div align="center">
								系部审核时间
							</div>
						</td>
						<td>
							<bean:write name="rs" property="xbshsj"/>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								班级评议结果
							</div>
						</td>
						<td colspan="3">
							<bean:write name="rs" property="bjpyjg"/>
							<input type="hidden" id="bjpyjg" name="bjpyjg"
								value="<bean:write name='rs' property="bjpyjg" />" />
						</td>
						<td colspan="2">
							<div align="center">
								系部审核结果
							</div>
						</td>
						<td>
							<bean:write name="rs" property="xbshjg"/>
							<input type="hidden" id="xbshjg" name="xbshjg"
								value="<bean:write name='rs' property="xbshjg" />" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								学校审核结果
							</div>
						</td>
						<td colspan="3">
							<html:select name="rs" property="xxshjg">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<td colspan="2">
							<div align="center">
								审核时间
							</div>
						</td>
						<td>
							<bean:write name="rs" property="xxshsj"/>
						</td>
					</tr>
				</logic:equal>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2" onclick="yz()" style="width:80px"
					id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2"
					onclick="Close();window.dialogArguments.document.getElementById('search_go').click();"
					style="width:80px" id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
	</body>
</html>
