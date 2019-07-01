<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript">
	 function dataSave(){
		 var url = "/xgxt/xsgbxx.do?method=hdchAdd&act=add&tjlx=hdch";
		 var yzdz = "gzzjV";
		 var yzcd = "800";
		 var mustFill = "xh-hdchlx";
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
	    	showTips('���������У���ȴ�......');
	    	document.forms[0].action = url;
	    	document.forms[0].submit();
	    	document.getElementById("buttonSave").disabled=true;
	    	document.getElementById("buttonQx").disabled=true;
	 }
	 
	 function printwmgylgl(){
			document.forms[0].action = "/xgxt/jhzy_gygl.do?method=wmgylprint";
			document.forms[0].target = "_blank";
		    document.forms[0].submit();
		    document.forms[0].target = "_self";
	 }
	</script>
	</head>
	<body bgcolor="#FFFFFF" class="Normal" lang=ZH-CN onload="cheangelx();">
		<html:form action="/xsgbxx" method="post">
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��Ṥ�� - ѧ���ɲ� - ��߻�¼��</a>
				</p>
			</div>
				<input type="hidden" name="zyV" id="zyV" />
				<input type="hidden" name="bjV" id="bjV" />
				<input type="hidden" id="tableName" name="tableName"
					value="zjlg_hdchb" />
				<input type="hidden" id="url" name="url"
					value="/xsgbxx.do?method=getxsInfo&forwardname=hdch" />
				<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh" />
				<input type="hidden" id="tjlx" name="tjlx" value="hdch" />
				<div class="tab">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="6">
									<span>�ֶ�ά��</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									<font color="red">*</font>ѧ��
								</th>
								<td>
									<html:hidden property="id" name="rs" />
									<html:text name="rs" property="xh"
										readonly="true"
										onkeypress="autoFillStuInfo(event.keyCode,this)" />
									<logic:equal value="add" name="doType">
										<logic:notEqual value="stu" name="userName" scope="session">
											<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
												class="btn_01" id="buttonFindStu">
												ѡ��
											</button>
										</logic:notEqual>
									</logic:equal>
								</td>
								<th>
									����
								</th>
								<td>
									<html:text name="rs" property="xm" 
										readonly="true" />
								</td>
							</tr>
							<tr>
								<th>
									�Ա�
								</th>
								<td>
									<html:text name="rs" property="xb" 
										maxlength="10" readonly="true" />
								</td>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:text name="rs" property="xydm"
										style="display: none" readonly="true" />
									<input value="<bean:write name="rs" property="xymc"/>"
										readonly="true" disabled="disabled"
										/>
								</td>
							</tr>
							<tr>
								<th>
									�༶
								</th>
								<td>
									<html:text name="rs" property="bjdm" style="display: none"
										readonly="true" />
									<input value="<bean:write name="rs" property="bjmc"/>"
										disabled="disabled" />
								</td>
								<th>
									<font color="red">*</font>�����ܽ�����
								</th>
								<td>
									<html:select name="rs" property="hdchlx"
										styleId="hdchlx" onchange="cheangelx();">
										<html:option value=""></html:option>
										<html:option value="ѧ��">ѧ��</html:option>
										<html:option value="ѧ��">ѧ��</html:option>
										<html:option value="�·�">�·�</html:option>
									</html:select>
								</td>
							</tr>
							<tr>

								<th>
									<span id="xnV1"> ѧ�� </span>
								</th>
								<td>
									<span id="xnV"> <html:select name="rs" property="xn"
											style="width:" styleId="xn">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select> </span>
								</td>
								<th>
									<span id="xqV1"> ѧ�� </span>
								</th>
								<td>
									<span id="xqV"> <html:select name="rs" property="xq"
											style="width:" styleId="xn">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqmc"
												labelProperty="xqmc" />
										</html:select> </span>
								</td>
							</tr>
							<tr id="yfV">
								<th align="right">
									�·�
								</th>
								<td>
									<html:select name="rs" property="yf" style="width:">
										<html:option value=""></html:option>
										<html:options collection="yfList" property="yf"
											labelProperty="yf" />
									</html:select>
								</td>
								<td align="right">
								</td>
								<td>
							</tr>
							<tr id="gzzjV">
								<th align="center">
									��߻�<br/>
									<font color="red">(��������800)</font>
								</th>
								<td colspan="6">
									<html:textarea name="rs" property="hdcd" rows="8" onblur="chLeng(this,800);"
										style="width: 95%;word-break:break-all;" />
								</td>
							</tr>
						</tbody>
						<tfoot
							<logic:equal value="view" name="view">
					style="display: none"
					</logic:equal>>
							<tr>
								<td colspan="6">
									<div class="bz">
										"
										<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button onclick="dataSave();" id="buttonSave">
											����
										</button>
										<button class="button2" onclick="Close();return false;" id="buttonQx">
											ȡ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
		</html:form>
		<logic:equal value="yes" name="done">
			<script language="javascript">
			alert("�����ɹ���");
			Close();
			window.dialogArguments.document.getElementById('search_go').click();
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
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
		</script>
		</logic:equal>
		<logic:equal value="yes" name="existx">
			<script language="javascript">
			alert("�û��Ѵ��ڣ�");
			//Close();
			//window.dialogArguments.document.getElementById('search_go').click();
	</script>
		</logic:equal>
	</body>
	<script type="text/javascript">
		function cheangelx(){
			var cheangelx = document.getElementById("hdchlx").value;
			if(cheangelx == "ѧ��"){
				document.getElementById("xnV").style.display="inline";
				document.getElementById("xqV").style.display="none";
				document.getElementById("xnV1").style.display="inline";
				document.getElementById("xqV1").style.display="none";
				document.getElementById("yfV").style.display="none";
			}else if(cheangelx == "ѧ��"){
				document.getElementById("xnV").style.display="inline";
				document.getElementById("xqV").style.display="inline";
				document.getElementById("xnV1").style.display="inline";
				document.getElementById("xqV1").style.display="inline";
				document.getElementById("yfV").style.display="none";
			}else if(cheangelx == "�·�"){
				document.getElementById("xnV").style.display="inline";
				document.getElementById("xqV").style.display="inline";
				document.getElementById("xnV1").style.display="inline";
				document.getElementById("xqV1").style.display="inline";
				document.getElementById("yfV").style.display="inline";
			}else{
				document.getElementById("xnV").style.display="none";
				document.getElementById("xqV").style.display="none";
				document.getElementById("xnV1").style.display="none";
				document.getElementById("xqV1").style.display="none";
				document.getElementById("yfV").style.display="none";
			}
	 	}
	</script>
</html>
