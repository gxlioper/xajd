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
			alert("����������������");
			return false;
		 }
		 if(!isNumber(xss)){
				alert("ѧ��������������");
				return false;
			 }
		 if(!isNumber(wshgl) || wshgl>100){
				alert("�����ϸ��ʱ�����С��100������");
				return false;
			 }
		 if(!isNumber(wsyxl) || wsyxl>100){
				alert("���������ʱ�����С��100������");
				return false;
			 }
		 if(!isNumber(xjqsl) || xjqsl>100){
				alert("�Ǽ������ʱ�����С��100������");
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
	    			alert("�뽫��\"*\"�ŵ���Ŀ����������");
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
	    			alert(dzsjmc.replace("<BR>", "")+"���ܳ���"+splitCd[i]+"���֣�");
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
					��ǰ����λ�ã� ��Ԣ���� - ������Ԣ¥���� - ������Ԣ¥�����
				</div>
			</div>
			<logic:equal value="stu" name="userType" scope="session">
				<logic:equal value="no" name="isLz">
					<div style="color: red;font-size: 18" align="center">
						����Ŀǰ����¥�����ݲ��ܽ�������¥�����룡
					</div>
				</logic:equal>
			</logic:equal>
			<div align=center>
				<table border=1 cellspacing=0 cellpadding=0 width="100%"
					class="tbstyle">
					<tr>
						<td class="Normal" align="right">
							<font color="red">*</font>¥����
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
							<font color="red">*</font>����<bean:message key="lable.xsgzyxpzxy" />
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
							<font color="red">*</font>����ѧ��
						</td>
						<td colspan=2 class="Normal">
							<html:select property="xn" style="width:100px" styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
						<td class="Normal" align="right">
							�꼶
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
							������
						</td>
						<td colspan=2 class="Normal">
							<html:text property="qss" maxlength="8"></html:text>
						</td>
						<td class="Normal" align="right">
							ѧ����
						</td>
						<td colspan=2 class="Normal">
							<html:text property="xss" maxlength="8"></html:text>
						</td>
					</tr>
					<tr>
						<td class="Normal" align="right">
							�����ϸ���
						</td>
						<td colspan=2 class="Normal">
							<html:text property="wshgl" maxlength="5"></html:text>
							%
						</td>
						<td class="Normal" align="right">
							����������
						</td>
						<td colspan=2 class="Normal">
							<html:text property="wsyxl" maxlength="5"></html:text>
							%
						</td>
					</tr>
					<tr>
						<td class="Normal" align="right">
							�Ǽ�������
						</td>
						<td colspan=2 class="Normal">
							<html:text property="xjqsl" maxlength="5"></html:text>
							%
						</td>
					</tr>
					<tr id="gywmgstr">
						<td class="Normal" align="right">
							��Ԣ������
							<br>
							���������
						</td>
						<td colspan=5 valign=bottom class="Normal">
							<%--							¥��ǩ����&nbsp; ��&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; ��--%>
							<html:textarea property="gywmgs" rows="5" style="width: 100%"></html:textarea>
						</td>
					</tr>
					<tr id="fdyyjtr">
						<td class="Normal" align="right">
							¥������Ա���
						</td>
						<td colspan=5 valign=bottom class="Normal">
							<%--							����Աǩ���� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
							<%--							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
							<%--							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;--%>
							<%--							��&nbsp;&nbsp;&nbsp;&nbsp; ��--%>
							<html:textarea property="fdyyj" rows="5" style="width: 100%"></html:textarea>
						</td>
					</tr>
					<tr id="xyyjtr">
						<td class="Normal" align="right">
							����<bean:message key="lable.xsgzyxpzxy" />���
						</td>
						<td colspan=5 valign=bottom class="Normal">
							<%--							���� �£�--%>
							<%--							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
							<%--							��&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp; ��--%>
							<html:textarea property="xyyj" rows="5" style="width: 100%"></html:textarea>
						</td>
					</tr>
					<tr id="xxyjtr">
						<td class="Normal" align="right">
							ѧ�������
						</td>
						<td colspan=5 valign=bottom class="Normal">
							<%--							���� �£�--%>
							<%--							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
							<%--							��&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp; ��--%>
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
				ע�������һʽ����.
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
					����
				</button>
				&nbsp;&nbsp;
				<button class="button2" onclick="printwmgylgl();"
					style="width: 80px" id="buttonClose">
					��ӡ
				</button>
			</div>
		</html:form>
		<logic:equal value="ok" name="done">
			<script language="javascript">
alert("�����ɹ���");
//Close();
			//window.dialogArguments.document.getElementById('search_go').click();
</script>

		</logic:equal>
		<logic:equal value="yes" name="isexists">
			<script language="javascript">
			alert("��¥���Ѿ��ڱ�ѧ�������ˣ������ظ����룡");
			//Close();
			//window.dialogArguments.document.getElementById('search_go').click();
	</script>
		</logic:equal>
		<logic:equal value="no" name="done">
			<script language="javascript">
				alert("����ʧ�ܣ�");
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
