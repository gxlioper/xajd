<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<html>
	<head>
		<meta http-equiv=Content-Type content="text/html; charset=gbk">
		<title>湖州师范<bean:message key="lable.xsgzyxpzxy" />求真<bean:message key="lable.xsgzyxpzxy" />星级寝室评比办法</title>
		<base target="_self">
	</head>
	<script type="text/javascript">
	 function dataSave(){
		 var url = "/xgxt/jhzy_gygl.do?method=gyfdyupdate&doType=save";
		 var yzdz = "gywmgstr-fdyyjtr-xyyjtr-xxyjtr";
		 var yzcd = "800-800-800-800";
		 var mustFill = "lzm-xydm-xn";
		 checkdataSave(url,yzdz,yzcd,mustFill);
		 }
	 function checkdataSave(url,yzdz,yzcd,mustFill){
		 var eles = mustFill.split("-");
	    	for (i = 0; i < eles.length; i++) {
	    		if (document.getElementById(eles[i]).value == "") {
	    			alert("请将带\"*\"号的项目输入完整！");
	    			return;
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
	    			return;
	    		}
	    	}
	    	document.forms[0].action = url;
	    	document.forms[0].submit();
	    	 document.getElementById("buttonSave").disabled=true;
	 }
	 function printwmgylgl(){
			document.forms[0].action = "/xgxt/jhzy_gygl.do?method=wmgylprint&xmk=bjqsz";
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
			<div align=center>
				<table border=1 cellspacing=0 cellpadding=0 width="100%"
					class="tbstyle">
					<tr>
						<td class="Normal" align="right">
							<font color="red">*</font>楼幢名
						</td>
						<td colspan=2 class="Normal">
							<html:select name="rs" property="lzm" styleId="lzm"
								disabled="true">
								<html:options collection="ldList" property="dm"
									labelProperty="mc" />
								<input id="pkValue" name="pkValue"
									value="<bean:write name="rs" property="pk"/>" type="hidden" />
							</html:select>
							<html:hidden name="rs" property="lzm"></html:hidden>
						</td>
						<td class="Normal" align="right">
							<font color="red">*</font>所属<bean:message key="lable.xsgzyxpzxy" />
						</td>
						<td colspan=2 class="Normal">
							<html:select name="rs" property="xydm" styleClass="select"
								styleId="xy" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td class="Normal" align="right">
							<font color="red">*</font>申请学年
						</td>
						<td colspan=2 class="Normal">
							<html:select name="rs" property="xn" style="width:100px"
								styleId="xn" disabled="true">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
						<td class="Normal" align="right">
							年级
						</td>
						<td colspan=2 class="Normal">
							<html:select name="rs" property="nj" style="width:100px"
								styleId="xn" disabled="true">
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td class="Normal" align="right">
							寝室数
						</td>
						<td colspan=2 class="Normal">
							<html:text name="rs" property="qss" maxlength="8"></html:text>
						</td>
						<td class="Normal" align="right">
							学生数
						</td>
						<td colspan=2 class="Normal">
							<html:text name="rs" property="xss" maxlength="8"></html:text>
						</td>
					</tr>
					<tr>
						<td class="Normal" align="right">
							卫生合格率
						</td>
						<td colspan=2 class="Normal">
							<html:text name="rs" property="wshgl" maxlength="5"></html:text>
							%
						</td>
						<td class="Normal" align="right">
							卫生优秀率
						</td>
						<td colspan=2 class="Normal">
							<html:text name="rs" property="wsyxl" maxlength="5"></html:text>
							%
						</td>
					</tr>
					<tr>
						<td class="Normal" align="right">
							星级寝室率
						</td>
						<td colspan=2 class="Normal">
							<html:text name="rs" property="xjqsl" maxlength="5"></html:text>
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
							<html:textarea name="rs" property="gywmgs" rows="5"
								style="width: 100%"></html:textarea>
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
							<html:textarea name="rs" property="fdyyj" rows="5"
								style="width: 100%"></html:textarea>
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
							<html:textarea name="rs" property="xyyj" rows="5"
								style="width: 100%"></html:textarea>
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
							<html:textarea name="rs" property="xxyj" rows="5"
								style="width: 100%"></html:textarea>
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
					id="buttonSave">
					保存
				</button>
				&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width: 80px"
					id="buttonClose">
					关闭
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
</html>
