<%@ page language="java" contentType="text/html; charset=gb2312"%>
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
		<title>就业管理信息系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
    <script type="text/javascript" src="js/BatAlert.js"></script>
	<script language="javascript">
	function update(){
    
         var pkValue = $("pkValue").value;
         BatAlert.showTips('正在修改，请稍侯...');
		 document.forms[0].action = "stuinfoUpdate.do?doType=update";
		 document.forms[0].submit();
        
    }
    
	
	</script>

	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<html:form action="/stuinfoUpdate" method="post">
			<input type="hidden" name="pkValue"
				value="<bean:write name="rs" property="rid" />" />
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="center" colspan="4" height="">
							<b>学生就业信息修改</b>
						</td>
					</tr>
				</thead>
				<tr>
					<td align="center" colspan="4">
						学生就业基本情况
					</td>
				</tr>
				<tr>
					<td colspan="4">
						&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 学号：
						<bean:write name="rs" property="xh"  />
					</td>
				</tr>
				<tr>
					<td align="right">
						姓名：
					</td>
					<td>
						<bean:write name="rs" property="xm" />
					</td>
					<td align="right">
						性别：
					</td>
					<td>
						<bean:write name="rs" property="xb" />
					</td>
				</tr>
				<tr>
					<td align="right">
						年级：
					</td>
					<td>
						<bean:write name="rs" property="nj" />
					</td>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td>
						<bean:write name="rs" property="xymc" />
					</td>
				</tr>
				<tr>
					<td align="right">
						专业：
					</td>
					<td>
						<bean:write name="rs" property="zymc" />
					</td>
					<td align="right">
						班级：
					</td>
					<td>
						<bean:write name="rs" property="bjmc" />
					</td>
				</tr>
				<tr>
					<td align="right">
						单位证明：
					</td>
					<td colspan="3">
						<html:select name="rs" property="dwzm">
						    <html:option value="">--请选择--</html:option>
						    <html:option value="无">无</html:option>
							<html:option value="有">有</html:option>	
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						专业对口情况：
					</td>
					<td>
						<html:select name="rs" property="zydkqk">
						    <html:option value="">--请选择--</html:option>
							<html:option value="无单位">无单位</html:option>
							<html:option value="完全对口">完全对口</html:option>
							<html:option value="基本对口">基本对口</html:option>
							<html:option value="不对口">不对口</html:option>
						</html:select>
					</td>
					<td align="right">
						就业部门：
					</td>
					<td>
						<html:text name="rs" property="jybm" />
					</td>
				</tr>
				<tr>
					<td align="right">
						岗位性质：
					</td>
					<td>
						<html:select name="rs" property="gwxz">
						   <html:option value="">--请选择--</html:option>
						   <html:option value="无单位">无单位</html:option>
						   <html:option value="业务人员">业务人员</html:option>
						   <html:option value="行政人员">行政人员</html:option>
						   <html:option value="管理人员">管理人员</html:option>
						   <html:option value="技术人员">技术人员</html:option>
						   <html:option value="车间生产线">车间生产线</html:option>
						   <html:option value="其他">其他</html:option>
						</html:select>
					</td>
					<td align="right">
						岗位名称：
					</td>
					<td>
						<html:text name="rs" property="gwmc" />
					</td>
				</tr>
				<tr>
					<td align="right">
						工资情况：
					</td>
					<td>
						<html:select name="rs" property="gzqk" >
						<html:option value="">--请选择--</html:option>
						<html:option value="无单位">无单位</html:option>
						<html:option value="1000元以下">1000元以下</html:option>
						<html:option value="1000-1500元">1000-1500元</html:option>
						<html:option value="1500-2000元">1500-2000元</html:option>
						<html:option value="2000-4000元">2000-4000元</html:option>
						<html:option value="4000元以上">4000元以上</html:option>
						</html:select>
					</td>
					<td align="right">
						工作地：
					</td>
					<td>
						<html:select name="rs" property="gzd" >
								<html:option value=""></html:option>
								<html:options collection="gzdList"
									property="mc" labelProperty="mc" />
							</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						是否就业：
					</td>
					<td>
						<html:select name="rs" property="sfjy">
						    <html:option value="">--请选择--</html:option>
						    <html:option value="否">否</html:option>
							<html:option value="是">是</html:option>
							
						</html:select>
					</td>
					<td align="right">
						是否签约：
					</td>
					<td>
						<html:select name="rs" property="sfqy">
						    <html:option value="">--请选择--</html:option>
						    <html:option value="否">否</html:option>
							<html:option value="是">是</html:option>
							
						</html:select>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						就业单位基本情况
					</td>
				</tr>
				<tr>
					<td align="right">
						单位名称：
					</td>
					<td>
						<html:text name="rs" property="dwmc" />
					</td>
					<td align="right">
						单位性质：
					</td>
					<td>
						<html:select name="rs" property="dwxz">
							<html:option value="">--请选择--</html:option>
							<html:option value="个私">个私</html:option>
							<html:option value="国有企业">国有企业</html:option>
							<html:option value="机关事业">机关事业</html:option>
							<html:option value="三资企业">三资企业</html:option>
							<html:option value="股份制企业">股份制企业</html:option>
							<html:option value="金融企业">金融企业</html:option>
							<html:option value="自主创业">自主创业</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						单位地址：
					</td>
					<td>
						<html:text name="rs" property="dwdz" />
					</td>
					<td align="right">
						单位联系人：
					</td>
					<td>
						<html:text name="rs" property="dwlxr" />
					</td>
				</tr>
				<tr>
					<td align="right">
						单位电话：
					</td>
					<td >
						<html:text name="rs" property="dwdh" />
					</td>
					<td align="right">
						登记时间：
					</td>
					<td >
						<bean:write name="rs" property="djsj" />
					</td>
				</tr>

			</table>
			<div align="center">
				<button class="button2" onclick="update();return false;" style="width:80px">
					提 交
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" type="reset" style="width:80px">
					重 置
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2"
					onclick="Close();window.dialogArguments.document.getElementById('search_go').click();"
					type="reset" style="width:80px">
					关 闭
				</button>
			</div>
		</html:form>

		<logic:notEmpty name="update">
			<logic:equal name="update" value="ok">
				<script>
                      alert("修改成功！");
                    </script>
			</logic:equal>
			<logic:equal name="update" value="no">
				<script>
                      alert("操作失败!");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>

