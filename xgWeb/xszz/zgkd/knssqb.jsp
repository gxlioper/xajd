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
	<title><bean:message key="lable.title" /></title>
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
<script language="javascript">
		function back(){
			document.forms[0].action = "/xgxt/zgkydx_xszz.do?method=knssq";
			document.forms[0].submit();
		}
	</script>
<body>
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="15%">
								<div align="center">
									姓名
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name="rs" property="xm" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									性别
								</div>
							</td>
							<td width="10%">
								<div align="center">
									<bean:write name="rs" property="xb" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									学号
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name="rs" property="xh" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									班级
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name="rs" property="bjmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									身份证号
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="sfzh" />
								</div>
							</td>
							<td>
								<div align="center">
									宿舍
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="ss" />
								</div>
							</td>
							<td>
								<div align="center">
									电话
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="dh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家庭居住地址
								</div>
							</td>
							<td colspan="7">
								<div align="center">
									<bean:write name="rs" property="jtszd" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									家庭联系电话及邮编
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="jtlxdhjyzbm" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									父亲单位联系电话
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="fqdwlxdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									所在基层组织联系电话
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="szjclxdh" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									母亲单位联系电话
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="mqdwlxdh" />
								</div>
							</td>
						</tr>
					</table>
					<table width="100%" class="tbstyle">
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
							<td width="30%">
								<div align="center">
									工作单位(就读学校)
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
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy1_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy1_xb" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy1_gx" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy1_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy1_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<logic:empty name="rs" property="jtcy1_xm">
										&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="jtcy1_xm">
										<bean:write name="rs" property="jtcy1_hklx" />
									</logic:notEmpty>
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy1_cyzt" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy2_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy2_xb" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy2_gx" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy2_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy2_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<logic:empty name="rs" property="jtcy2_xm">
										&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="jtcy2_xm">
										<bean:write name="rs" property="jtcy2_hklx" />
									</logic:notEmpty>
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy2_cyzt" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy3_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy3_xb" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy3_gx" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy3_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy3_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<logic:empty name="rs" property="jtcy3_xm">
										&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="jtcy3_xm">
										<bean:write name="rs" property="jtcy3_hklx" />
									</logic:notEmpty>
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy3_cyzt" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy4_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy4_xb" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy4_gx" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy4_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy4_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<logic:empty name="rs" property="jtcy4_xm">
										&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="jtcy4_xm">
										<bean:write name="rs" property="jtcy4_hklx" />
									</logic:notEmpty>
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy4_cyzt" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy5_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy5_xb" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy5_gx" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy5_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy5_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<logic:empty name="rs" property="jtcy5_xm">
										&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="jtcy5_xm">
										<bean:write name="rs" property="jtcy5_hklx" />
									</logic:notEmpty>
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy5_cyzt" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy6_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy6_xb" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy6_gx" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy6_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy6_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<logic:empty name="rs" property="jtcy6_xm">
										&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="jtcy6_xm">
										<bean:write name="rs" property="jtcy6_hklx" />
									</logic:notEmpty>
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy6_cyzt" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy7_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy7_xb" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy7_gx" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy7_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy7_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<logic:empty name="rs" property="jtcy7_xm">
										&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="jtcy7_xm">
										<bean:write name="rs" property="jtcy7_hklx" />
									</logic:notEmpty>
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy7_cyzt" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="8">
								注:1.家庭成员指除学生本人之外共同生活的直属血亲及兄弟姐妹；
								<br />
								&nbsp;&nbsp;&nbsp;2.户口类型分：A&nbsp;(农业户口)&nbsp;&nbsp;&nbsp;B&nbsp;(非农业户口)；
								<br />
								&nbsp;&nbsp;&nbsp;3.从业状态一栏仅填写工作、务农、下岗、打工、退休、上学(大学、中专、高中、初中、小学)等。
							</td>
						</tr>
						<tr>
							<td colspan="8">
								申请建档具体理由(请直接在对应情况前的□画“√”，并在相应横线上简要说明):
								<br />
								<br />
								<logic:empty name="rs" property="sqly_lzgjjpkxdncdq">
								&nbsp;&nbsp;□来自国家级贫困县的农村地区；__________________________________________________
								</logic:empty>
								<logic:notEmpty name="rs" property="sqly_lzgjjpkxdncdq">
								&nbsp;&nbsp;√来自国家级贫困县的农村地区；<u>&nbsp;<bean:write name="rs"
											property="sqly_lzgjjpkxdncdq" />&nbsp;</u>
								</logic:notEmpty>
								<br />
								<br />
								<logic:empty name="rs" property="sqly_syxszdshbzdczjt">
								&nbsp;&nbsp;□属于享受最低生活保障的城镇家庭；_______________________________________________
								</logic:empty>
								<logic:notEmpty name="rs" property="sqly_syxszdshbzdczjt">
								&nbsp;&nbsp;√属于享受最低生活保障的城镇家庭；<u>&nbsp;<bean:write name="rs"
											property="sqly_syxszdshbzdczjt" />&nbsp;</u>
								</logic:notEmpty>
								<br />
								<br />
								<logic:empty name="rs" property="sqly_gehjjknddqjt">
								&nbsp;&nbsp;□孤儿或经济困难的单亲家庭；____________________________________________________
								</logic:empty>
								<logic:notEmpty name="rs" property="sqly_gehjjknddqjt">
								&nbsp;&nbsp;√孤儿或经济困难的单亲家庭；<u>&nbsp;<bean:write name="rs"
											property="sqly_gehjjknddqjt" />&nbsp;</u>
								</logic:notEmpty>
								<br />
								<br />
								<logic:empty name="rs" property="sqly_fmyfhsfxg">
								&nbsp;&nbsp;□父母一方或双方下岗(失业)；____________________________________________________
								</logic:empty>
								<logic:notEmpty name="rs" property="sqly_fmyfhsfxg">
								&nbsp;&nbsp;√父母一方或双方下岗(失业)；<u>&nbsp;<bean:write name="rs"
											property="sqly_fmyfhsfxg" />&nbsp;</u>
								</logic:notEmpty>
								<br />
								<br />
								<logic:empty name="rs" property="sqly_jtcywqzndl">
								&nbsp;&nbsp;□家庭成员中无18-55岁的青壮劳动力；_____________________________________________
								</logic:empty>
								<logic:notEmpty name="rs" property="sqly_jtcywqzndl">
								&nbsp;&nbsp;√家庭成员中无18-55岁的青壮劳动力；<u>&nbsp;<bean:write
											name="rs" property="sqly_jtcywqzndl" />&nbsp;</u>
								</logic:notEmpty>
								<br />
								<br />
								<logic:empty name="rs" property="sqly_jtcyycjhjbrssndl">
								&nbsp;&nbsp;□家庭成员因残疾或疾病而丧失劳动能力；____________________________________________
								</logic:empty>
								<logic:notEmpty name="rs" property="sqly_jtcyycjhjbrssndl">
								&nbsp;&nbsp;√家庭成员因残疾或疾病而丧失劳动能力；<u>&nbsp;<bean:write
											name="rs" property="sqly_jtcyycjhjbrssndl" />&nbsp;</u>
								</logic:notEmpty>
								<br />
								<br />
								<logic:empty name="rs" property="sqly_jtcyyzdjbxzfdefy">
								&nbsp;&nbsp;□家庭成员因患重大疾病需支付大额医疗费用；_________________________________________
								</logic:empty>
								<logic:notEmpty name="rs" property="sqly_jtcyyzdjbxzfdefy">
								&nbsp;&nbsp;√家庭成员因患重大疾病需支付大额医疗费用；<u>&nbsp;<bean:write
											name="rs" property="sqly_jtcyyzdjbxzfdefy" />&nbsp;</u>
								</logic:notEmpty>
								<br />
								<br />
								<logic:empty name="rs" property="sqly_jtcyzylghyscyzjsfywjy">
								&nbsp;&nbsp;□家庭中有两个或以上成员正接受非义务教育；_________________________________________
								</logic:empty>
								<logic:notEmpty name="rs" property="sqly_jtcyzylghyscyzjsfywjy">
								&nbsp;&nbsp;√家庭中有两个或以上成员正接受非义务教育；<u>&nbsp;<bean:write
											name="rs" property="sqly_jtcyzylghyscyzjsfywjy" />&nbsp;</u>
								</logic:notEmpty>
								<br />
								<br />
								<logic:empty name="rs" property="sqly_jtcyzszrzh">
								&nbsp;&nbsp;□家庭及成员因遭遇自然灾害或其他突发灾变，造成人身及财产的重大损失；_____________________
								</logic:empty>
								<logic:notEmpty name="rs" property="sqly_jtcyzszrzh">
								&nbsp;&nbsp;√家庭及成员因遭遇自然灾害或其他突发灾变，造成人身及财产的重大损失；<u>&nbsp;<bean:write
											name="rs" property="sqly_jtcyzszrzh" />&nbsp;</u>
								</logic:notEmpty>
								<br />
								<br />
								<logic:empty name="rs" property="sqly_qtqk">
								&nbsp;&nbsp;□其他导致家庭经济困难情况；____________________________________________________
								<br />
									<br />
								&nbsp;&nbsp;__________________________________________________________________________
								</logic:empty>
								<logic:notEmpty name="rs" property="sqly_qtqk">
								&nbsp;&nbsp;√其他导致家庭经济困难情况；<u>&nbsp;<bean:write name="rs"
											property="sqly_qtqk" />&nbsp;</u>
								</logic:notEmpty>
								<br />
								<br />
								<div align="right">
									申请人:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;
								</div>
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
								<div align="center">
									<bean:write name="rs" property="zmcl1_mc" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<div align="center">
									出具材料机关及联系电话
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="zmcl1_jg" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="zmcl1_dh" />
								</div>
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
								<div align="center">
									<bean:write name="rs" property="zmcl2_mc" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<div align="center">
									出具材料机关及联系电话
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="zmcl2_jg" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="zmcl2_dh" />
								</div>
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
								<div align="center">
									<bean:write name="rs" property="zmcl3_mc" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<div align="center">
									出具材料机关及联系电话
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="zmcl3_jg" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="zmcl3_dh" />
								</div>
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
								<div align="center">
									<bean:write name="rs" property="zmcl4_mc" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<div align="center">
									出具材料机关及联系电话
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="zmcl4_jg" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="zmcl4_dh" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="8">
								<br />
								<strong>&nbsp;&nbsp;本人承诺以上陈述的情况及本人提供的全部证明材料均属实，并承担由于不实材料所造成的一切后果。<br />
									&nbsp;&nbsp;本人也将正视困难，勤俭求学，诚实守信，努力做到自尊、自信、自强、拼搏、乐观、向上，以优异的成绩回报父母的养育的国家、学校的培养。<br />
									<br /> </strong>
								<div align="right">
									承诺人:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									建档
									<br />
									标准
									<br />
									及班
									<br />
									级民
									<br />
									主评
									<br />
									议情
									<br />
									况
								</div>
							</td>
							<td colspan="7">
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
								<br />
								&nbsp;&nbsp;&nbsp;&nbsp;班级民主评议情况：参与民主评议的总人数为
								<logic:empty name="rs" property="pyrs">______</logic:empty>
								<logic:notEmpty name="rs" property="pyrs">
									<u>&nbsp;<bean:write name="rs" property="pyrs" />&nbsp;</u>
								</logic:notEmpty>
								人；其中同意建立A档的
								<logic:empty name="rs" property="adrs">______</logic:empty>
								<logic:notEmpty name="rs" property="adrs">
									<u>&nbsp;<bean:write name="rs" property="adrs" />&nbsp;</u>
								</logic:notEmpty>
								票；同意建立B档的
								<logic:empty name="rs" property="bdrs">______</logic:empty>
								<logic:notEmpty name="rs" property="bdrs">
									<u>&nbsp;<bean:write name="rs" property="bdrs" />&nbsp;</u>
								</logic:notEmpty>
								票；同意建立C档的
								<logic:empty name="rs" property="cdrs">______</logic:empty>
								<logic:notEmpty name="rs" property="cdrs">
									<u>&nbsp;<bean:write name="rs" property="cdrs" />&nbsp;</u>
								</logic:notEmpty>
								票；不同意建档的
								<logic:empty name="rs" property="btyrs">______</logic:empty>
								<logic:notEmpty name="rs" property="btyrs">
									<u>&nbsp;<bean:write name="rs" property="btyrs" />&nbsp;</u>
								</logic:notEmpty>
								票；反映存在不予建档情况的
								<logic:empty name="rs" property="csbyjdrs">______</logic:empty>
								<logic:notEmpty name="rs" property="csbyjdrs">
									<u>&nbsp;<bean:write name="rs" property="csbyjdrs" />&nbsp;</u>
								</logic:notEmpty>
								票。同学反映存在不予建档的具体情况为:
								<logic:empty name="rs" property="byjdjtqk">________________________</logic:empty>
								<logic:notEmpty name="rs" property="byjdjtqk">
									<u>&nbsp;<bean:write name="rs" property="byjdjtqk" />&nbsp;</u>
								</logic:notEmpty>
								，经调查同学反映情况&nbsp;
								<logic:empty name="rs" property="byjdjtqk">
								□属实&nbsp;&nbsp;□不属实。
								</logic:empty>
								<logic:notEmpty name="rs" property="byjdjtqk">
									<logic:equal name="rs" property="dcfyqk" value="属实">
										√属实&nbsp;&nbsp;□不属实。
									</logic:equal>
									<logic:notEqual name="rs" property="dcfyqk" value="属实">
										□属实&nbsp;&nbsp;√不属实。
									</logic:notEqual>
								</logic:notEmpty>
								<br />
								<br />
								&nbsp;&nbsp;&nbsp;&nbsp;班级评议结果：建议该生为&nbsp;
								<logic:equal name="rs" property="bjpyjg" value="A档">
									√A档
								</logic:equal>
								<logic:notEqual name="rs" property="bjpyjg" value="A档">
									□A档
								</logic:notEqual>
								&nbsp;&nbsp;
								<logic:equal name="rs" property="bjpyjg" value="B档">
									√B档
								</logic:equal>
								<logic:notEqual name="rs" property="bjpyjg" value="B档">
									□B档
								</logic:notEqual>
								&nbsp;&nbsp;
								<logic:equal name="rs" property="bjpyjg" value="C档">
									√C档
								</logic:equal>
								<logic:notEqual name="rs" property="bjpyjg" value="C档">
									□C档
								</logic:notEqual>
								<br />
								<br />
								<div align="right">
									班主任签字:____________&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									系部
									<br />
									意见
								</div>
							</td>
							<td colspan="7">
								<br />
								&nbsp;&nbsp;&nbsp;&nbsp;经班级民主评议和系审核，同意该生为
								<logic:equal name="rs" property="xbshjg" value="A档">
									√A档
								</logic:equal>
								<logic:notEqual name="rs" property="xbshjg" value="A档">
									□A档
								</logic:notEqual>
								&nbsp;&nbsp;
								<logic:equal name="rs" property="xbshjg" value="B档">
									√B档
								</logic:equal>
								<logic:notEqual name="rs" property="xbshjg" value="B档">
									□B档
								</logic:notEqual>
								&nbsp;&nbsp;
								<logic:equal name="rs" property="xbshjg" value="C档">
									√C档
								</logic:equal>
								<logic:notEqual name="rs" property="xbshjg" value="C档">
									□C档
								</logic:notEqual>
								家庭经济困难学生。
								<br />
								<br />
								<div align="right">
									签字(盖章)：_____________&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />
									<br />
									意见
								</div>
							</td>
							<td colspan="7">
								<br />
								&nbsp;&nbsp;&nbsp;&nbsp;经学校审核，同意该生为
								<logic:equal name="rs" property="xxshjg" value="A档">
									√A档
								</logic:equal>
								<logic:notEqual name="rs" property="xxshjg" value="A档">
									□A档
								</logic:notEqual>
								&nbsp;&nbsp;
								<logic:equal name="rs" property="xxshjg" value="B档">
									√B档
								</logic:equal>
								<logic:notEqual name="rs" property="xxshjg" value="B档">
									□B档
								</logic:notEqual>
								&nbsp;&nbsp;
								<logic:equal name="rs" property="xxshjg" value="C档">
									√C档
								</logic:equal>
								<logic:notEqual name="rs" property="xxshjg" value="C档">
									□C档
								</logic:notEqual>
								家庭经济困难学生。
								<br />
								<br />
								<div align="right">
									签&nbsp;&nbsp;章：_____________&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
	<div align="center">
		<input  value="打印" name="button_print"
			onclick="expTab('theTable','')" />
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input  value="返回" onclick="back();" />
	</div>
</body>
</html:html>
