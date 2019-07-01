<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"  %>
<body onload="">
	<script language="javascript" src="js/pjpyFunction.js"></script>
	<html:form action="/pjpyjgsdxwh" method="post">
		<div class="title">
					<div class="title_img" id="title_m">
						<bean:message key="pjpy_jgsdx_tjsz" bundle="pjpyjgsdx"/>
					</div>
		</div>
		<fieldset>
			<legend>
					��������
			</legend>
			<input  id="go" name="go" style="display:none" onclick="refreshForm('pjpy_jgsdx_tjszdefault.do')" />
			<table width="100%" class="tbstyle">
					<thead>
						<tr>
						    <td align=left id="vXn">ѧ�꣺
						    	<html:select property="xn" styleId="xn" style="width:90px">
						    		<html:options collection="xnList" property="xn" labelProperty="xn"/>
						    	</html:select>
						    	&nbsp;&nbsp;&nbsp;&nbsp;
								��ѧ��
								<html:select property="jxjdm" style="width:200px" styleId="jxjdm"
									onchange="refreshForm('pjpy_jgsdx_tjszdefault.do')">
									<html:option value=""></html:option>
									<html:options collection="jxjList" property="jxjdm" labelProperty="jxjmc" />
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;
								Υ���������ƣ�<html:radio property="sfwj" value="1"></html:radio>����&nbsp;&nbsp;&nbsp;
												<html:radio property="sfwj" value="0"></html:radio>������
							</td>
						</tr>
						<tr>
							<td align="left">
								������
								<html:select property="zdm" style="width:160px" onchange="chgYsf()" styleId="zdm">
									<html:option value=""></html:option>
									<html:options collection="zdList" property="en"
										labelProperty="cn" />
								</html:select>
								--&gt;
								<select name="ysf" id="ysf" style="width:120px">
									<option value=""></option>
									<option value="&gt;=">
										���ڻ����
									</option>
									<option value="&gt;">
										����
									</option>
									<option value="=">
										����
									</option>
									<option value="&lt;">
										С��
									</option>
									<option value="&lt;=">
										С�ڻ����
									</option>
								</select>
								--&gt;
								<html:text property="val" styleId="val" style="width:120px"></html:text>
								<input type="hidden" id="go" name="go" value="go"/>		
								<button class="button2"
									onclick="if(allFilledbyjgs()){document.forms[0].go.value='go';refreshForm('jxjtjszsave.do');return true;}return false;">
									�������
								</button>
							</td>
						</tr>
					</thead>
				</table>
		</fieldset>
		<br />
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						��������
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center">
								<logic:iterate id="tit" name="topTr" offset="2">
										<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate id="s" name="rs" scope="request">
							<tr align="center" onclick="rowOnClick(this)" style="cursor:hand" >
							<td>
								<input type="hidden" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
								<input type="hidden" value="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>"/>
								<logic:iterate id="v" name="s" offset="2" length="1">
										<bean:write name="v"/>
								</logic:iterate>
							</td>
								<logic:iterate id="v" name="s" offset="3">
									<td>
										<bean:write name="v"/>
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
			<div class="buttontool" id="btn"
				style="position: absolute;left:1%;top:100px" width="100%">
				<button class="button2" onclick="modijxjtjsz('jxjszcjmodi.do?pkValue=','modi')">
					�޸�����
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="modijxjtjsz('jxjszcjdel.do?pkValue=','del')">
					ɾ������
				</button>
			</div>
	</html:form>
	<script language="javascript">
document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
document.getElementById("btn").style.width = "96%";
window.setInterval("initBTNTool('btn')",1);
function modijxjtjsz(url,type){
	if (curr_row==null || curr_row=='') {
		alert('��ѡ����Ҫ�����������У�');
		return;
	} else {
		url += curr_row.cells[0].getElementsByTagName("input")[0].value;
		if (type=='del') {
			if (confirm('ȷ��Ҫɾ����ѡ���������')) {
				refreshForm(url);
				return;
			}
			return;
		}else {
			url += '&jxjdm=';
			url += curr_row.cells[0].getElementsByTagName("input")[1].value;
			showTopWin(url,'550','380');
		}
	}
}
function allFilledbyjgs(){
	if (document.getElementById('xn').selectedIndex<=0 || document.getElementById('jxjdm').selectedIndex<=0 
	|| document.getElementById('zdm').selectedIndex<=0 
	|| document.getElementById('val').value=='' 
	|| document.getElementById('val').value==null) {
	alert('����ѡ���Ϊ�գ�');
	return false;
	} else {
	return true;
	}
}
function chgYsf(){
	var ysf = document.getElementById('ysf');
	if (document.getElementById('zdm').value=='rownum') {
		var option1 = new Option("ǰ","&lt;=");
		ysf.options[0] = option1;
		ysf.options[0].selected = true;
		ysf.disabled=true;
	} else {
		document.getElementById('ysf').disabled=false;
		var option0 = new Option("","");
		ysf.options[0] = option0;
		ysf.options[0].selected = true;
	}
}
</script>
<logic:present name="deleted">
		<logic:equal value="yes" name="deleted">
			<script>
				alert("�����ɹ�!");
				refreshForm('pjpy_jgsdx_tjszdefault.do')
			</script>
		</logic:equal>
		<logic:equal value="no" name="deleted">
			<script>
				alert("����ʧ�ܣ�");
				refreshForm('pjpy_jgsdx_tjszdefault.do')
			</script>
		</logic:equal>
		</logic:present>
		<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
				alert("�����ɹ�!");
				refreshForm('pjpy_jgsdx_tjszdefault.do')
			</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
				alert("����ʧ�ܣ�");
				refreshForm('pjpy_jgsdx_tjszdefault.do')
			</script>
			</logic:equal>
		</logic:present>
</body>