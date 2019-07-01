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
<script language="javascript">
		function back(){
			document.forms[0].action = "/xgxt/zgmsxy_xszz.do?method=knsrdsq";
			document.forms[0].submit();
		}
	</script>
<body>
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
							<h2>
						<strong>
								高等学校学生及家庭情况调查表
						</strong>
							</h2> 
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						学校：
						<u>&nbsp;中国美术<bean:message key="lable.xsgzyxpzxy" />&nbsp;</u> <bean:message key="lable.xsgzyxpzxy" />(课部):
						<logic:empty name="rs" property="xymc">
						___________________
						</logic:empty>
						<logic:notEmpty name="rs" property="xymc">
							<u> &nbsp;<bean:write name="rs" property="xymc" />&nbsp; </u>
						</logic:notEmpty>
						专业:
						<logic:empty name="rs" property="zymc">
						___________________
						</logic:empty>
						<logic:notEmpty name="rs" property="zymc">
							<u> &nbsp;<bean:write name="rs" property="zymc" />&nbsp; </u>
						</logic:notEmpty>
						年级:
						<logic:empty name="rs" property="nj">
						_______
						</logic:empty>
						<logic:notEmpty name="rs" property="nj">
							<u> &nbsp;<bean:write name="rs" property="nj" />&nbsp; </u>
						</logic:notEmpty>
					</div>
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr>
							<td rowspan="4" width="6%">
								<div align="center">
									学
									<br />
									生
									<br />
									本
									<br />
									人
									<br />
									基
									<br />
									本
									<br />
									情
									<br />
									况
								</div>
							</td>
							<td width="12%">
								<div align="center">
									姓名
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="xm" />
								</div>
							</td>
							<td width="8%">
								<div align="center">
									性别
								</div>
							</td>
							<td width="10%">
								<div align="center">
									<bean:write name="rs" property="xb" />
								</div>
							</td>
							<td width="12%">
								<div align="center">
									出生年月
								</div>
							</td>
							<td width="12%">
								<div align="center">
									<bean:write name="rs" property="csny" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									民族
								</div>
							</td>
							<td width="12%">
								<div align="center">
									<bean:write name="rs" property="mzmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									身份证
									<br />
									号码
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="sfzh" />
								</div>
							</td>
							<td>
								<div align="center">
									政治
									<br />
									面貌
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="zzmmmc" />
								</div>
							</td>
							<td>
								<div align="center">
									入学前
									<br />
									户口
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:empty name="rs" property="xh">
										□&nbsp;城镇&nbsp;&nbsp;□&nbsp;农村
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
										<logic:equal name="rs" property="rxqhk" value="城镇">
											√&nbsp;城镇&nbsp;&nbsp;□&nbsp;农村
										</logic:equal>
										<logic:equal name="rs" property="rxqhk" value="农村">
											□&nbsp;城镇&nbsp;&nbsp;√&nbsp;农村
										</logic:equal>
									</logic:notEmpty>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家庭
									<br />
									人口数
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="jtrks" />
								</div>
							</td>
							<td>
								<div align="center">
									毕业
									<br />
									学校
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="byxx" />
								</div>
							</td>
							<td>
								<div align="center">
									个人
									<br />
									特长
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="grtc" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									孤残
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:empty name="rs" property="xh">
										□&nbsp;是&nbsp;&nbsp;□&nbsp;否
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
										<logic:equal name="rs" property="sfgc" value="是">
											√&nbsp;是&nbsp;&nbsp;□&nbsp;否
										</logic:equal>
										<logic:equal name="rs" property="sfgc" value="否">
											□&nbsp;是&nbsp;&nbsp;√&nbsp;否
										</logic:equal>
									</logic:notEmpty>
								</div>
							</td>
							<td>
								<div align="center">
									单亲
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:empty name="rs" property="xh">
										□&nbsp;是&nbsp;&nbsp;□&nbsp;否
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
										<logic:equal name="rs" property="sfdq" value="是">
											√&nbsp;是&nbsp;&nbsp;□&nbsp;否
										</logic:equal>
										<logic:equal name="rs" property="sfdq" value="否">
											□&nbsp;是&nbsp;&nbsp;√&nbsp;否
										</logic:equal>
									</logic:notEmpty>
								</div>
							</td>
							<td>
								<div align="center">
									烈士子女
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:empty name="rs" property="xh">
										□&nbsp;是&nbsp;&nbsp;□&nbsp;否
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
										<logic:equal name="rs" property="sflszn" value="是">
											√&nbsp;是&nbsp;&nbsp;□&nbsp;否
										</logic:equal>
										<logic:equal name="rs" property="sflszn" value="否">
											□&nbsp;是&nbsp;&nbsp;√&nbsp;否
										</logic:equal>
									</logic:notEmpty>
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="2">
								<div align="center">
									家庭
									<br />
									通讯
									<br />
									地址
								</div>
							</td>
							<td>
								<div align="center">
									详细通
									<br />
									讯地址
								</div>
							</td>
							<td colspan="8">
								<bean:write name="rs" property="jt_xxtxdz" />
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									邮政编码
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="jt_yzbm" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									联系电话
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="jt_lxdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="7">
								<div align="center">
									家
									<br />
									庭
									<br />
									成
									<br />
									员
									<br />
									情
									<br />
									况
								</div>
							</td>
							<td>
								<div align="center">
									姓名
								</div>
							</td>
							<td width="8%">
								<div align="center">
									年龄
								</div>
							</td>
							<td width="10%">
								<div align="center">
									与学生
									<br />
									关系
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									工作(学习)单位
								</div>
							</td>
							<td>
								<div align="center">
									职业
								</div>
							</td>
							<td>
								<div align="center">
									年收入
									<br />
									(元)
								</div>
							</td>
							<td>
								<div align="center">
									健康
									<br />
									状况
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
									&nbsp;
									<bean:write name="rs" property="jtcy1_nl" />
									&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy1_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="jtcy1_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy1_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy1_sr" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy1_jkzk" />
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
									&nbsp;
									<bean:write name="rs" property="jtcy2_nl" />
									&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy2_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="jtcy2_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy2_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy2_sr" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy2_jkzk" />
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
									&nbsp;
									<bean:write name="rs" property="jtcy3_nl" />
									&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy3_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="jtcy3_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy3_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy3_sr" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy3_jkzk" />
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
									&nbsp;
									<bean:write name="rs" property="jtcy4_nl" />
									&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy4_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="jtcy4_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy4_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy4_sr" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy4_jkzk" />
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
									&nbsp;
									<bean:write name="rs" property="jtcy5_nl" />
									&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy5_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="jtcy5_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy5_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy5_sr" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy5_jkzk" />
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
									&nbsp;
									<bean:write name="rs" property="jtcy6_nl" />
									&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy6_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="jtcy6_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy6_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy6_sr" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy6_jkzk" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									影响
									<br />
									家庭
									<br />
									经济
									<br />
									状况
									<br />
									有关
									<br />
									信息
								</div>
							</td>
							<td colspan="9">
								家庭全年收入
								<logic:empty name="rs" property="jtqnsr">
								_____________
								</logic:empty>
								<logic:notEmpty name="rs" property="jtqnsr">
								<u>&nbsp;<bean:write name="rs" property="jtqnsr" />&nbsp;</u>
								</logic:notEmpty>
								(元),学生本学年已获资助情况
								<logic:empty name="rs" property="xybxnyhzzqk">
								______________________________
								<br />
								<br />
								_________________________________________________________________________
								</logic:empty>
								<logic:notEmpty name="rs" property="xybxnyhzzqk">
								<u>&nbsp;<bean:write name="rs" property="xybxnyhzzqk" />&nbsp;</u>
								</logic:notEmpty>
								。
								<br />
								<br />
								家庭遭受自然灾害情况：
								<logic:empty name="rs" property="jtzszrzhqk">
								________________
								</logic:empty>
								<logic:notEmpty name="rs" property="jtzszrzhqk">
								<u>&nbsp;<bean:write name="rs" property="jtzszrzhqk" />&nbsp;</u>
								</logic:notEmpty>
								。家庭遭受突发意外事件：
								<logic:empty name="rs" property="jtzstfywsj">
								____________________
								</logic:empty>
								<logic:notEmpty name="rs" property="jtzstfywsj">
								<u>&nbsp;<bean:write name="rs" property="jtzstfywsj" />&nbsp;</u>
								</logic:notEmpty>
								。
								<br />
								<br />
								家庭成员因残疾、年迈而劳动能力弱情况：
								<logic:empty name="rs" property="jtcyycjssldl">
								___________________________________________
								</logic:empty>
								<logic:notEmpty name="rs" property="jtcyycjssldl">
								<u>&nbsp;<bean:write name="rs" property="jtcyycjssldl" />&nbsp;</u>
								</logic:notEmpty>
								。
								<br />
								<br />
								家庭成员失业情况：
								<logic:empty name="rs" property="jtcysyqk">
								________________
								</logic:empty>
								<logic:notEmpty name="rs" property="jtcysyqk">
								<u>&nbsp;<bean:write name="rs" property="jtcysyqk" />&nbsp;</u>
								</logic:notEmpty>
								。家庭欠债情况：
								<logic:empty name="rs" property="jtqzqk">
								_____________________________
								</logic:empty>
								<logic:notEmpty name="rs" property="jtqzqk">
								<u>&nbsp;<bean:write name="rs" property="jtqzqk" />&nbsp;</u>
								</logic:notEmpty>
								。
								<br />
								<br />
								其他情况：
								<logic:empty name="rs" property="qtqk">
								________________________________________________________________
								</logic:empty>
								<logic:notEmpty name="rs" property="qtqk">
								<u>&nbsp;<bean:write name="rs" property="qtqk" />&nbsp;</u>
								</logic:notEmpty>
								。
								<br />
								<br />
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									签
									<br />
									章
								</div>
							</td>
							<td>
								<div align="center">
									学生
									<br />
									本人
								</div>
							</td>
							<td>
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								&nbsp;
							</td>
							<td>
								<div align="center">
									学生家长
									<br />
									或监护人
								</div>
							</td>
							<td colspan="2">
								&nbsp;
							</td>
							<td>
								<div align="center">
									学生家庭所在地
									<br />
									乡镇或街道民政
									<br />
									部门
								</div>
							</td>
							<td colspan="3">
								<br />
								<br />
								当地低保标准：&nbsp;
								<logic:empty name="rs" property="dddbbz">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="dddbbz">
								<bean:write name="rs" property="dddbbz" />
								</logic:notEmpty>
								&nbsp;元/人.月
								<br />
								经办人签字：
								<br />
								单位名称:
								<div align="center">
									(加盖公章)
								</div>
								<div align="right">
									________年____月____日&nbsp;&nbsp;
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
