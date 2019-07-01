<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<html>
	<head>
		<meta http-equiv=Content-Type content="text/html; charset=gbk">
		<title></title>
	</head>
	<script type="text/javascript">
	 function dataSave(){
		 var url = "/xgxt/jhzy_gygl.do?method=wmgylSq&doType=save";
		 var yzdz = "gywmgstr-fdyyjtr-xyyjtr-xxyjtr";
		 var yzcd = "800-800-800-800";
		 var mustFill = "lzm-xydm-xn";
		 var qss = document.getElementById("qss").value;
		 var xss = document.getElementById("xss").value;
		 var wshgl = document.getElementById("wshgl").value;
		 var wsyxl = document.getElementById("wsyxl").value;
		 var xjqsl = document.getElementById("xjqsl").value;
		 if(!isNumber(qss)){
			alert("寝室数必须是数字");
			return false;
		 }
		 if(!isNumber(xss)){
				alert("学生数必须是数字");
				return false;
			 }
		 if(!isNumber(wshgl) || wshgl>100){
				alert("卫生合格率必须是小于100的数字");
				return false;
			 }
		 if(!isNumber(wsyxl) || wsyxl>100){
				alert("卫生优秀率必须是小于100的数字");
				return false;
			 }
		 if(!isNumber(xjqsl) || xjqsl>100){
				alert("星级寝室率必须是小于100的数字");
				return false;
			 }
		 checkdataSave(url,yzdz,yzcd,mustFill);
		 }
	 function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		    } 
	 function checkdataSave(url,yzdz,yzcd,mustFill){
		 var eles = mustFill.split("-");
	    	for (var i = 0; i < eles.length; i++) {
	    		if (document.getElementById(eles[i]) && document.getElementById(eles[i]).value == "") {
	    			alert("请将带\"*\"号的项目输入完整！");
	    			return false;
	    		}
	    	}
	    	var splitDz = "";
	    	var splitCd = "";
	    	if(yzdz != ""){
	    		splitDz = yzdz.split("-");
	    	}
	    	if(yzcd != ""){
	    		splitCd = yzcd.split("-");
	    	}
	    	for (i = 0; i < splitDz.length; i++) {
	    		var dzsjcd = document.getElementById(splitDz[i]).cells[1].getElementsByTagName('textarea')[0].value;
	    		var dzsjmc = document.getElementById(splitDz[i]).cells[0].innerHTML;
	    		if (dzsjcd.length>splitCd[i]) {
	    			alert(dzsjmc.replace("<BR>", "")+"不能超过"+splitCd[i]+"个字！");
	    			return false;
	    		}
	    	}
	    	document.forms[0].action = url;
	    	document.forms[0].submit();
	    	document.getElementById("buttonSave").disabled=true;
	 }
	 function printwmgylgl(){
			document.forms[0].action = "/xgxt/jhzy_gygl.do?method=wmgylprint";
			document.forms[0].target = "_blank";
		    document.forms[0].submit();
		    document.forms[0].target = "_self";
	 }
	</script>
	<body bgcolor="#FFFFFF" class="Normal" lang=ZH-CN>
		<html:form action="/jhzy_gygl" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置： 公寓管理 - 文明公寓楼申请 - 文明公寓楼申请表
				</div>
			</div>
			<logic:equal value="stu" name="userType" scope="session">
				<logic:equal value="no" name="isLz">
					<div style="color: red;font-size: 18" align="center">
						该生目前不是楼长，暂不能进行文明楼栋申请！
					</div>
				</logic:equal>
			</logic:equal>
			<div align=center>
				<table border=1 cellspacing=0 cellpadding=0 width="100%"
					class="tbstyle">
					<tr>
						<td class="Normal" align="right">
							<font color="red">*</font>楼幢名
						</td>
						<td colspan=2 class="Normal">
							<logic:equal value="stu" name="userType" scope="session">
								<logic:equal value="yes" name="isLz">
									<bean:write name="ldmc" />
								</logic:equal>
								<logic:equal value="no" name="isLz">
									<html:select property="lzm" styleId="lzm">
										<html:options collection="ldList" property="dm"
											labelProperty="mc" />
									</html:select>
								</logic:equal>
							</logic:equal>
							<logic:notEqual value="stu" name="userType" scope="session">
								<html:select property="lzm" styleId="lzm">
									<html:options collection="ldList" property="dm"
										labelProperty="mc" />
								</html:select>
							</logic:notEqual>
						</td>
						<td class="Normal" align="right">
							<font color="red">*</font>所属<bean:message key="lable.xsgzyxpzxy" />
						</td>
						<td colspan=2 class="Normal">
							<logic:equal value="stu" name="userType" scope="session">
								<logic:equal value="yes" name="isLz">
									<bean:write name="xymc" />
								</logic:equal>
								<logic:equal value="no" name="isLz">
									<html:select property="xydm" styleClass="select" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</logic:equal>
							</logic:equal>
							<logic:notEqual value="stu" name="userType" scope="session">
								<html:select property="xydm" styleClass="select" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</logic:notEqual>
						</td>
					</tr>
					<tr>
						<td class="Normal" align="right">
							<font color="red">*</font>申请学年
						</td>
						<td colspan=2 class="Normal">
							<html:select property="xn" style="width:100px" styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
						<td class="Normal" align="right">
							年级
						</td>
						<td colspan=2 class="Normal">
							<logic:equal value="stu" name="userType" scope="session">
								<logic:equal value="yes" name="isLz">
									<bean:write name="nj" />
								</logic:equal>
								<logic:equal value="no" name="isLz">
									<html:select property="nj" style="width:100px" styleId="xn">
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</logic:equal>
							</logic:equal>
							<logic:notEqual value="stu" name="userType" scope="session">
								<html:select property="nj" style="width:100px" styleId="xn">
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</logic:notEqual>
						</td>
					</tr>
					<tr>
						<td class="Normal" align="right">
							寝室数
						</td>
						<td colspan=2 class="Normal">
							<html:text property="qss" maxlength="8"></html:text>
						</td>
						<td class="Normal" align="right">
							学生数
						</td>
						<td colspan=2 class="Normal">
							<html:text property="xss" maxlength="8"></html:text>
						</td>
					</tr>
					<tr>
						<td class="Normal" align="right">
							卫生合格率
						</td>
						<td colspan=2 class="Normal">
							<html:text property="wshgl" maxlength="5"></html:text>
							%
						</td>
						<td class="Normal" align="right">
							卫生优秀率
						</td>
						<td colspan=2 class="Normal">
							<html:text property="wsyxl" maxlength="5"></html:text>
							%
						</td>
					</tr>
					<tr>
						<td class="Normal" align="right">
							星级寝室率
						</td>
						<td colspan=2 class="Normal">
							<html:text property="xjqsl" maxlength="5"></html:text>
							%
						</td>
					</tr>
					<tr id="gywmgstr">
						<td class="Normal" align="right">
							公寓文明建
							<br>
							设情况概述
						</td>
						<td colspan=5 valign=bottom class="Normal">
							<%--							楼长签名：&nbsp; 年&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日--%>
							<html:textarea property="gywmgs" rows="5" style="width: 100%"></html:textarea>
						</td>
					</tr>
					<tr id="fdyyjtr">
						<td class="Normal" align="right">
							楼幢辅导员意见
						</td>
						<td colspan=5 valign=bottom class="Normal">
							<%--							辅导员签名： &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
							<%--							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
							<%--							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;--%>
							<%--							月&nbsp;&nbsp;&nbsp;&nbsp; 日--%>
							<html:textarea property="fdyyj" rows="5" style="width: 100%"></html:textarea>
						</td>
					</tr>
					<tr id="xyyjtr">
						<td class="Normal" align="right">
							所属<bean:message key="lable.xsgzyxpzxy" />意见
						</td>
						<td colspan=5 valign=bottom class="Normal">
							<%--							（盖 章）--%>
							<%--							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
							<%--							年&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp; 日--%>
							<html:textarea property="xyyj" rows="5" style="width: 100%"></html:textarea>
						</td>
					</tr>
					<tr id="xxyjtr">
						<td class="Normal" align="right">
							学生处意见
						</td>
						<td colspan=5 valign=bottom class="Normal">
							<%--							（盖 章）--%>
							<%--							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
							<%--							年&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp; 日--%>
							<html:textarea property="xxyj" rows="5" style="width: 100%"></html:textarea>
						</td>
					</tr>
					<tr height=0>
						<td width=96 class="Normal"></td>
						<td width=96 class="Normal"></td>
						<td width=108 class="Normal"></td>
						<td width=120 class="Normal"></td>
						<td width=96 class="Normal"></td>
						<td width=84 class="Normal"></td>
					</tr>
				</table>
			</div>
			<div>
				注：本表格一式三份.
			</div>
			<div class="buttontool" id="btn" align="center">

				<button class="button2" onclick="dataSave();" style="width: 80px"
					id="buttonSave"
					<logic:equal value="stu" name="userType" scope="session">
				   <logic:equal value="no" name="isLz">
					disabled='true'
			       	  </logic:equal>
			       </logic:equal>
			       >
					保存
				</button>
				&nbsp;&nbsp;
				<button class="button2" onclick="printwmgylgl();"
					style="width: 80px" id="buttonClose">
					打印
				</button>
			</div>
		</html:form>
		<logic:equal value="ok" name="done">
			<script language="javascript">
alert("操作成功！");
//Close();
			//window.dialogArguments.document.getElementById('search_go').click();
</script>

		</logic:equal>
		<logic:equal value="yes" name="isexists">
			<script language="javascript">
			alert("该楼幢已经在本学年申请了，不必重复申请！");
			//Close();
			//window.dialogArguments.document.getElementById('search_go').click();
	</script>
		</logic:equal>
		<logic:equal value="no" name="done">
			<script language="javascript">
				alert("操作失败！");
				//Close();
				//window.dialogArguments.document.getElementById('search_go').click();
		</script>
		</logic:equal>
	</body>
	<script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
					document.getElementById("btn").style.width = "96%";
					window.setInterval("initBTNTool('btn')",1);
	</script>
</html>
