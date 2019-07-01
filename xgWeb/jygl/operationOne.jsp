<%@ include file="/syscommon/pagehead.ini"%>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<html>
	<head>
		<title>就业管理信息系统</title>
				<%
				response.setHeader("Pragma","No-cache");
				response.setHeader("Cache-Control","no-cache");
				response.setDateHeader("Expires", 0);
				%>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<script language="javascript">
	function savejyxx(){
      	 var qybz = '';
      	 var dwmc ='';
      	 var sfjy ='' ;
      	 
      	 if (document.getElementById('qybz')) {
      	 	qybz = document.getElementById('qybz').value;
      	 }
      	 
      	 if (document.getElementById('dwmc')) {
      	 	dwmc = document.getElementById('dwmc').value;
      	 }
      	 
      	 if (document.getElementById('sfjy')) {
      	 	sfjy = document.getElementById('sfjy').value;
      	 }
      	 
		 document.forms[0].action = "/xgxt/jyxxlr.do?doType=save";
		 document.forms[0].submit();
		 BatAlert.showTips('正在提交，请稍侯...');
    }
	</script>
	
    <script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>	
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getXjydInfo.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<body>
		<html:form action="/jyxxlr" method="post">
			<table width="100%" class="tbstyle">
				<tr>
					<td align="right">
						学号：
					</td>
					<td colspan="1">
						<html:text name="rs" property="xh" readonly="true"/>
					</td>
					<td align="right">
						姓名：
					</td>
					<td>
						${rs.xm}
					</td>
				</tr>
				<tr>
					<td align="right">
						性别：
					</td>
					<td>
						${rs.xb}
					</td>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />名称：
					</td>
					<td>
						${rs.xymc}
					</td>
				</tr>
				<tr>
					<td align="right">
						专业名称：
					</td>
					<td>
						${rs.zymc}
					</td>
					<td align="right">
						班级名称：
					</td>
					<td>
						${rs.bjmc}
					</td>
				</tr>
				<%--<tr>
					<td align="right">
						年度：
					</td>
					<td>
						${rs.nd}<input type="hidden" name="nd" value="${rs.nd}"/>
					</td>
					<td align="right">
						学生层次：
					</td>
					<td>
						${rs.xsccmc}
					</td>
				</tr>
				--%><logic:notEqual value="xy" name="userType">
					<tr>
					<td align="right">
						签约标志：
					</td>
					<td>
						<html:select name="rs" property="qybz" style="width:100px">
							<html:option value=""></html:option>
							<html:options collection="byqxList" property="dm" labelProperty="mc"/>
						</html:select>
					</td>
						<td align="right">
							是否签约：
						</td>
						<td>
							<html:select name="rs" property="sfqy" style="width:100px">
								<html:option value="否">否</html:option>
								<html:option value="是">是</html:option>
							</html:select>
						</td>	
				</tr>
				</logic:notEqual>
				
				<tr>
					<td align="right">
						单位名称：
					</td>
					<td>
						<html:text name="rs" property="dwmc"  maxlength="50" />
					</td>	
					<td align="right">
						单位地址：
					</td>
					<td>
						<html:text name="rs" property="dwdz"  maxlength="100" />
					</td>
					
				</tr>
				<tr>
					<td align="right">
						专业对口：
					</td>
					<td>
						<html:select name="rs" property="zydk" style="width:100px">
							<html:option value=""></html:option>
							<html:option value="是">是</html:option>
							<html:option value="否">否</html:option>
						</html:select>
					</td>
					<td align="right">
						是否就业：
					</td>
					<td>
						<html:select name="rs" property="sfjy" style="width:100px">
							<html:option value="否">否</html:option>
							<html:option value="是">是</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						就业区域：
					</td>
					<td colspan="3">
						<html:select name="rs" property="jyqy" styleId="syshen" onchange="loadShi('syshen','syshi','syxian')">
								<html:option value="">--请选择--</html:option>
								<html:options collection="ssList" property="ssdm"
									labelProperty="ssmc" />
							</html:select>
							<html:select name="rs" property="syshi" styleId="syshi"
								onchange="loadXian('syshi','syxian')">								
								<html:options collection="syshiList" property="shidm"
									labelProperty="shimc" />
							</html:select>
							<html:select name="rs" property="syxian" styleId="syxian">								
								<html:options collection="syxianList" property="xiandm"
									labelProperty="xianmc" />
							</html:select>
					</td>
				</tr>
				<logic:notEqual value="xy" name="userType">
				<tr>		
					<td align="right">
						单位联系电话：
					</td>
					<td>
						<html:text name="rs" property="lxdh"  maxlength="50" />
					</td>
					<td align="right">
						单位联系人：
					</td>
					<td>
						<html:text name="rs" property="lxr"  maxlength="25" />
					</td>
				</tr>
				</logic:notEqual>
			</table>
			<div align="center">
				<logic:notEqual value="view" name="lx">
				<button class="button2" onclick="savejyxx()" style="width:80px">
					提 交
				</button>
				&nbsp;&nbsp;
				</logic:notEqual>
				<button class="button2" onclick="Close();return false;" style="width:80px">
					关 闭
				</button>
			</div>
		</html:form>

		<logic:notEmpty name="result">
			<logic:equal name="result" value="true">
				    <script>
                      alert("提交成功！");
                      Close();
                      window.dialogArguments.document.getElementById('query_go').click();
                    </script>
			</logic:equal>
			<logic:equal name="result" value="false">
				<script>
                      alert("操作失败!");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>

