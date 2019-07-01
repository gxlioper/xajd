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
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	
	
	<script language="javascript">
	function savejyxy(){
	      document.forms[0].action = "jyxy_input.do?doType=save";
          document.forms[0].submit();       
	}
	
	</script>
	<logic:equal value="teacher" name="userOnLine">
		<script language="javascript">
			alert('该页面为学生填写页面！');
		</script>
	</logic:equal>
	<body>
		<html:form action="/jyxy_input" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：就业管理 - 就业协议方案 - 就业协议录入
				</div>
			</div>

			<html:hidden name="rs" property="xh" />

			<table width="100%" id="rsT" class="tbstyle">
				<thead>
					<tr style="height:25px">
						<td colspan="9" align="center">
							<b>填写就业协议</b>
						</td>
					</tr>
				</thead>

				<tr style="height:22px" align="center">
					<td rowspan="4" width="5%">
						学
						<br>
						生
						<br>
						情
						<br>
						况
					</td>
					<td width="8%">
						姓名
					</td>
					<td>
						<html:text name="rs" property="xm" style="width:100%"
							readonly="true" />
					</td>
					<td width="8%">
						性别
					</td>
					<td>
						<html:text name="rs" property="xb" style="width:100%"
							readonly="true" />
					</td>
					<td width="8%">
						年龄
					</td>
					<td width="15%">
						<html:text name="rs" property="nl" style="width:100%" />
					</td>
					<td width="5%">
						民族
					</td>
					<td>
						<html:text name="rs" property="mz" style="width:100%" />
					</td>
				</tr>
				<tr style="height:22px" align="center">
					<td>
						政治面貌
					</td>
					<td>
						<html:text name="rs" property="zzmm" style="width:100%" />
					</td>
					<td>
						培养方式
					</td>
					<td colspan="2">
						<html:text name="rs" property="pyfs" style="width:100%" />
					</td>
					<td>
						学历
					</td>
					<td colspan="2">
						<html:text name="rs" property="xl" style="width:100%" />
					</td>
				</tr>
				<tr align="center">
					<td>
						专业
					</td>
					<td colspan="3">
						<html:text name="rs" property="zymc" style="width:100%" />
					</td>
					<td>
						学制
					</td>
					<td colspan="3">
						<html:text name="rs" property="xz" style="width:100%"
							readonly="true" />
					</td>
				</tr>
				<tr align="center">
					<td>
						家庭地址
					</td>
					<td colspan="3">
						<html:text name="rs" property="jtdz" style="width:100%" />
					</td>
					<td>
						联系电话
					</td>
					<td colspan="3">
						<html:text name="rs" property="lxdh" style="width:100%" />
					</td>
				</tr>
				<tr>
					<td colspan="9">
						&nbsp;
					</td>
				</tr>
			</table>
			<table width="100%" id="rsT2" class="tbstyle">
				<tr align="center">
					<td rowspan="5" width="7%">
						用
						<br>
						人
						<br>
						单
						<br>
						位
						<br>
						情
						<br>
						况
					</td>
					<td width="10%">
						单位名称
					</td>
					<td colspan="2">
						<html:text name="rs" property="dwmc" style="width:100%" />
					</td>
					<td width="10%">
						单位隶属
					</td>
					<td colspan="2">
						<html:text name="rs" property="dwls" style="width:100%" />
					</td>
				</tr>
				<tr align="center">
					<td>
						联系人
					</td>
					<td width="17%">
						<html:text name="rs" property="dwlxr" style="width:100%" />
					</td>
					<td width="8%">
						联系电话
					</td>
					<td>
						<html:text name="rs" property="dwlxdh" />
					</td>
					<td width="10%">
						邮政编码
					</td>
					<td>
						<html:text name="rs" property="dwyb" style="width:100%" />
					</td>
				</tr>
				<tr align="center">
					<td>
						通讯地址
					</td>
					<td colspan="3">
						<html:text name="rs" property="dwdz" style="width:100%" />
					</td>
					<td>
						所有制性质
					</td>
					<td align="left">
						<html:select name="rs" property="dwxz" styleId="dwxz" style="width:100px">
							<html:option value=""></html:option>
							<html:options collection="dwxzdm2List" property="dwxz"
								labelProperty="dwxz" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						单位性质
					</td>
					<td colspan="5">
						<input type="radio" name="hyfl" value="机关">
						机关&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="hyfl" value="科研设计单位">
						科研设计单位&nbsp;&nbsp;
						<input type="radio" name="hyfl" value="高等学校">
						高等学校&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="hyfl" value="中等专科学校">
						中等专科学校&nbsp;
						<input type="radio" name="hyfl" value="中学">
						中学
						<br>
						<input type="radio" name="hyfl" value="小学">
						小学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="hyfl" value="幼儿园">
						幼儿园&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="hyfl" value="私立中小学">
						私立中小学&nbsp;
						<input type="radio" name="hyfl" value="其他教育单位">
						其他教育单位&nbsp;
						<input type="radio" name="hyfl" value="医疗卫生单位">
						医疗卫生单位
						<br>
						<input type="radio" name="hyfl" value="电视台">
						电视台&nbsp;&nbsp;
						<input type="radio" name="hyfl" value="其他事业单位">
						其他事业单位&nbsp;&nbsp;
						<input type="radio" name="hyfl" value="金融单位">
						金融单位&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="hyfl" value="国有企业">
						国有企业&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="hyfl" value="三资企业">
						三资企业 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="hyfl" value="其他企业">
						其他企业 &nbsp;&nbsp;
						<input type="radio" name="hyfl" value="部队">
						部队
					</td>
				</tr>
				<tr>
					<td align="center">
						档案转寄<br>详细地址
					</td>
					<td colspan="5">
						<html:text name="rs" property="dayjdz" style="width:100%" />
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button class="button2" onclick="savejyxy()">
					提 交 协 议
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" type="reset">
					重 置
				</button>
			</div>
			<logic:notEmpty name="save">
				<logic:equal name="save" value="ok">
					<script>
    alert("提交成功！");
    </script>
				</logic:equal>
				<logic:equal name="save" value="no">
					<script>
    alert("提交失败！请检查是否重复提交！");
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
