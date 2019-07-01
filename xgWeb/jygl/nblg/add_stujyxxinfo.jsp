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
	function add(){
      var xh = document.getElementById("xh").value;   
      var dwzm = document.getElementById("dwzm").value; 
      var zydkqk = document.getElementById("zydkqk").value; 
      var gwxz = document.getElementById("gwxz").value;
      var gzqk = document.getElementById("gzqk").value;
      var sfjy = document.getElementById("sfjy").value;
      var sfqy = document.getElementById("sfqy").value;
     
      if(xh==""){
      alert("学号不能为空！");
      return false;
      }
      if(dwzm==""){
      alert("请填写单位证明选项！");
      return false;
      }
      if(zydkqk==""){
      alert("请填写专业对口选项！");
      return false;
      }
      if(gwxz==""){
      alert("请填写岗位性质选项！");
      return false;
      }
      if(gzqk==""){
      alert("请填写工资情况选项！");
      return false;
      }
      if(sfjy==""){
      alert("请填写是否就业选项！");
      return false;
      }
      if(sfqy==""){
      alert("请填写是否签约选项！");
      return false;
      }
      
         BatAlert.showTips('正在提交，请稍侯...');
		 document.forms[0].action = "stuxsjyxxinput.do?act=save";
		 document.forms[0].submit();
        
    }
    
	</script>

	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<html:form action="/stuxsjyxxinput" method="post">
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="center" colspan="4" height="">
							<b>学生就业信息录入</b>
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
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 学号：
						<html:text name="rs" property="xh" readonly="true"></html:text>
						<button onclick="showTopWin('stuinfoquery.do',780,500);"
							class="btn_01" id="buttonFindStu">
							选择
						</button>
					</td>
				</tr>
				<tr>
					<td align="right">
						姓名：
					</td>
					<td>
						<html:text name="rs" property="xm" readonly="true"/>
					</td>
					<td align="right">
						性别：
					</td>
					<td>
						<html:text name="rs" property="xb" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						年级：
					</td>
					<td>
						<html:text name="rs" property="nj" readonly="true"/>
					</td>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td>
						<html:text name="rs" property="xymc" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						专业：
					</td>
					<td>
						<html:text name="rs" property="zymc" readonly="true"/>
					</td>
					<td align="right">
						班级：
					</td>
					<td>
						<html:text name="rs" property="bjmc" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						单位证明：
					</td>
					<td colspan="3">
						<html:select name="rs" property="dwzm">
						    <html:option value="">--请选择--</html:option>
							<html:option value="有">有</html:option>
							<html:option value="无">无</html:option>
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
						<html:option value="">---请选择---</html:option>
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
						<html:option value="">----请选择----</html:option>
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
						<html:text name="rs" property="gzd" />
					</td>
				</tr>
				<tr>
					<td align="right">
						是否就业：
					</td>
					<td>
						<html:select name="rs" property="sfjy">
							<html:option value="">--请选择--</html:option>
							<html:option value="是">是</html:option>
							<html:option value="否">否</html:option>
						</html:select>
					</td>
					<td align="right">
						是否签约：
					</td>
					<td>
						<html:select name="rs" property="sfqy">
							<html:option value="">--请选择--</html:option>
							<html:option value="是">是</html:option>
							<html:option value="否">否</html:option>
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
					<td colspan="3">
						<html:text name="rs" property="dwdh" />
					</td>
				</tr>

			</table>
			<div align="center">
				<button class="button2" onclick="add()" style="width:80px">
					提 交
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" type="reset" style="width:80px">
					重 置
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();" style="width:80px">
					关 闭
				</button>
			</div>
		</html:form>

		<logic:notEmpty name="save">
			<logic:equal name="save" value="ok">
				    <script>
                      alert("提交成功！");
                      Close();
                      window.dialogArguments.document.getElementById('search_go').click();
                    </script>
			</logic:equal>
			<logic:equal name="save" value="no">
				<script>
                      alert("重复提交！操作失败!");
                      Close();
                       window.dialogArguments.document.getElementById('search_go').click();
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>

