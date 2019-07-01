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
					条件设置
			</legend>
			<input  id="go" name="go" style="display:none" onclick="refreshForm('pjpy_jgsdx_tjszdefault.do')" />
			<table width="100%" class="tbstyle">
					<thead>
						<tr>
						    <td align=left id="vXn">学年：
						    	<html:select property="xn" styleId="xn" style="width:90px">
						    		<html:options collection="xnList" property="xn" labelProperty="xn"/>
						    	</html:select>
						    	&nbsp;&nbsp;&nbsp;&nbsp;
								奖学金：
								<html:select property="jxjdm" style="width:200px" styleId="jxjdm"
									onchange="refreshForm('pjpy_jgsdx_tjszdefault.do')">
									<html:option value=""></html:option>
									<html:options collection="jxjList" property="jxjdm" labelProperty="jxjmc" />
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;
								违处处分限制：<html:radio property="sfwj" value="1"></html:radio>包含&nbsp;&nbsp;&nbsp;
												<html:radio property="sfwj" value="0"></html:radio>不包含
							</td>
						</tr>
						<tr>
							<td align="left">
								条件：
								<html:select property="zdm" style="width:160px" onchange="chgYsf()" styleId="zdm">
									<html:option value=""></html:option>
									<html:options collection="zdList" property="en"
										labelProperty="cn" />
								</html:select>
								--&gt;
								<select name="ysf" id="ysf" style="width:120px">
									<option value=""></option>
									<option value="&gt;=">
										大于或等于
									</option>
									<option value="&gt;">
										大于
									</option>
									<option value="=">
										等于
									</option>
									<option value="&lt;">
										小于
									</option>
									<option value="&lt;=">
										小于或等于
									</option>
								</select>
								--&gt;
								<html:text property="val" styleId="val" style="width:120px"></html:text>
								<input type="hidden" id="go" name="go" value="go"/>		
								<button class="button2"
									onclick="if(allFilledbyjgs()){document.forms[0].go.value='go';refreshForm('jxjtjszsave.do');return true;}return false;">
									添加条件
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
						现有条件
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
					修改条件
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="modijxjtjsz('jxjszcjdel.do?pkValue=','del')">
					删除条件
				</button>
			</div>
	</html:form>
	<script language="javascript">
document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
document.getElementById("btn").style.width = "96%";
window.setInterval("initBTNTool('btn')",1);
function modijxjtjsz(url,type){
	if (curr_row==null || curr_row=='') {
		alert('请选择所要操作的数据行！');
		return;
	} else {
		url += curr_row.cells[0].getElementsByTagName("input")[0].value;
		if (type=='del') {
			if (confirm('确定要删除所选择的数据吗？')) {
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
	alert('所有选项不得为空！');
	return false;
	} else {
	return true;
	}
}
function chgYsf(){
	var ysf = document.getElementById('ysf');
	if (document.getElementById('zdm').value=='rownum') {
		var option1 = new Option("前","&lt;=");
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
				alert("操作成功!");
				refreshForm('pjpy_jgsdx_tjszdefault.do')
			</script>
		</logic:equal>
		<logic:equal value="no" name="deleted">
			<script>
				alert("操作失败！");
				refreshForm('pjpy_jgsdx_tjszdefault.do')
			</script>
		</logic:equal>
		</logic:present>
		<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
				alert("操作成功!");
				refreshForm('pjpy_jgsdx_tjszdefault.do')
			</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
				alert("操作失败！");
				refreshForm('pjpy_jgsdx_tjszdefault.do')
			</script>
			</logic:equal>
		</logic:present>
</body>