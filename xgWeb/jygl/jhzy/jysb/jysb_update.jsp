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
	function add(){
      var xymc = document.getElementById("xydm").value;   
      var sbsjd = document.getElementById("sbsjd").value; 
      var bynf = document.getElementById("bynf").value; 
      var xl = document.getElementById("xl").value;
     
      if(xymc==""){
      alert("<bean:message key="lable.xsgzyxpzxy" />名称不能为空！");
      return false;
      }
      if(sbsjd==""){
      alert("上报时间点不能为空！");
      return false;
      }
      if(bynf==""){
      alert("毕业年份不能为空！");
      return false;
      }
      if(xl==""){
      alert("学历不能为空！");
      return false;
      }
        
		 document.forms[0].action = "/xgxt/jhzyjysb.do?method=jysbupdate&doType=save";
		 document.forms[0].submit();
		 BatAlert.showTips('正在提交，请稍侯...');
    }
    function jsl(type){
        var qyl;
        var ypl;
        var jyl;
        var byrs = new Number(document.getElementById("byrs").value);
        var qyrs = new Number(document.getElementById("qyrs").value);
        var yprs = new Number(document.getElementById("yprs").value);
        var jyrs = new Number(document.getElementById("jyrs").value);
		if(type="qyrs"){
			if(qyrs>byrs){
				document.getElementById("qyrs").value="";
				alert("签约人数不能大于毕业人数");
				return;
			}else{
				qyl = (qyrs/byrs*100).toString().substring(0, 4);
				document.getElementById("qyl").value=qyl;
			}
		}
		if(type="yprs"){
			if(yprs>byrs){
				document.getElementById("yprs").value="";
				alert("应聘人数不能大于毕业人数");
				return;
			}else{
				ypl = (yprs/byrs*100).toString().substring(0, 4);
				document.getElementById("ypl").value=ypl;
			}
		}
		if(type="jyrs"){
			if(jyrs>byrs){
				document.getElementById("jyrs").value="";
				alert("就业人数不能大于毕业人数");
				return;
			}else{
				jyl = (jyrs/byrs*100).toString().substring(0, 4);
				document.getElementById("jyl").value=jyl;
			}
		}
    }
	</script>
	<body>
		<html:form action="/jhzyjysb" method="post">
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="center" colspan="4" height="">
							<b>就业上报</b>
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right">
						<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />名称
					</td>
					<td colspan="1">
					<input id="pkValue" name="pkValue" type="hidden" value="${rs1.id}"/>
					<input id="xydt" name="xydt" value="${bmmc }" readonly="readonly"/>
						<html:select name="rs1" property="xydm" style="width:130px;display:none"
									styleId="xy" onchange="">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"labelProperty="xymc" />
						</html:select>
					</td>
					<td align="right">
						<font color="red">*</font>专业名称
					</td>
					<td>
						<html:select name="rs1" property="zydm" style="width:;"
									styleId="zydm" onchange="">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm" labelProperty="zymc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>毕业年份
					</td>
					<td>
						<html:select name="rs1" property="bynf" style="width:130px"
									styleId="bynf" onchange="">
							<html:option value=""></html:option>
							<html:options collection="ndList" property="nd" labelProperty="nd" />
						</html:select>
					</td>
					<td align="right">
						<font color="red">*</font>上报时间点
					</td>
					<td>
						<html:text name="rs1" property="sbsjd" readonly="true" onclick="return showCalendar('sbsjd','y-mm-dd');"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>学历
					</td>
					<td>
						<html:select name="rs1" property="xl">
							<html:option value=""></html:option>
							<html:option value="本科生">本科生</html:option>
							<html:option value="专科生">专科生</html:option>
							<html:option value="高职生">高职生</html:option>
						</html:select>
					</td>
					<td align="right">
						毕业人数
					</td>
					<td>
						<html:text name="rs1" property="byrs" maxlength="8" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
				</tr>
				<tr>
					<td align="right">
						签约人数
					</td>
					<td>
						<html:text name="rs1" property="qyrs" onblur="jsl('qyrs');" maxlength="8" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
					<td align="right">
						签约率
					</td>
					<td>
						<html:text name="rs1" property="qyl" />%
					</td>
				</tr>
				<tr>
					<td align="right">
						应聘人数
					</td>
					<td>
						<html:text name="rs1" property="yprs" onblur="jsl('yprs');" maxlength="8" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
					<td align="right">
						应聘率
					</td>
					<td>
						<html:text name="rs1" property="ypl" />%
					</td>
				</tr>
				<tr>
					<td align="right">
						就业人数
					</td>
					<td>
						<html:text name="rs1" property="jyrs" onblur="jsl('jyrs');" maxlength="8" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
					<td align="right">
						就业率
					</td>
					<td>
						<html:text name="rs1" property="jyl" />%
					</td>
				</tr>
				<tr>
					<td align="right">
						灵活人数
					</td>
					<td>
						<html:text name="rs1" property="lhrs" maxlength="8" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
					<td align="right" nowrap="nowrap"">
						出国和升学人数
					</td>
					<td>
						<html:text name="rs1" property="cghsxrs" maxlength="8" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
				</tr>
				
			</table>
			<div align="center">
				<button class="button2" onclick="add()" style="width:80px">
					提 交
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();" style="width:80px">
					关 闭
				</button>
			</div>
		</html:form>

		<logic:notEmpty name="save">
			<logic:equal name="save" value="yes">
				    <script>
                      alert("提交成功！");
                     // Close();
                     // window.dialogArguments.document.getElementById('search_go').click();
                    </script>
			</logic:equal>
			<logic:equal name="save" value="no">
				<script>
                      alert("操作失败!");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>

