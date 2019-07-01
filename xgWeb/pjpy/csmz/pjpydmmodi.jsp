<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script language="javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
<body onload="">
	<html:form action="/pjpycsmzwh" method="post">
	<div class="title">
		当前所在位置：系统维护 - 代码维护 - 评奖评优
	</div>
	<fieldset>
				<legend>
					基础代码维护
				</legend>
				<input type="hidden" id="xydm" name="xydm" value="${xydm }"/>
				<input type="hidden" id="realTable" name="realTable" value="${realTable }"/>
		<table width='98%' align='center' class='tbstyle' id="rsTable">
			<thead>
				<tr align='center'>
					<td colspan='2'>
						* 修 改 代 码 *
					</td>
				</tr>
			</thead>
				<logic:present name="jxjdmb">
					<logic:equal value="yes" name="jxjdmb">
						<tr align="" style="width:35px">
						<td align="right"> 
							<font  color="red">*</font>奖学金代码：
						</td>
						<td align="left">
							<html:text property="jxjdm" styleId="jxjdm" styleClass="inputtext" maxlength="10" readonly="true"></html:text>
						</td>
						</tr>
						<tr align="" style="width:35px">
						<td align="right"> 
							<font  color="red">*</font>奖学金名称：
						</td>
						<td align="left">
							<html:text property="jxjmc" styleId="jxjmc" styleClass="inputtext"></html:text>
						</td>
						</tr>
						<tr align="" style="width:35px">
						<td align="right"> 
							奖学金类别：
						</td>
						<td align="left">
							<html:select property="jxjlb" style="width:150px"
							 styleId="jxjlb" styleClass="select" disabled="true">
								 <html:option value=""></html:option>
								<html:options collection="jxjlbList" property="en" labelProperty="cn"/>
							</html:select>
						</td>
						</tr>
						<tr align="" style="width:35px">
						<td align="right"> 
							<font  color="red">*</font>奖学金级别：
						</td>
						<td align="left">
							<html:text property="jxjjb" styleId="jxjjb" styleClass="inputtext" maxlength="2" onblur="ckdata(this)"></html:text>
						</td>
						</tr>
						<tr align="" style="width:35px">
						<td align="right"> 
							<font  color="red">*</font>奖励金额：
						</td>
						<td align="left">
							<html:text property="jlje" styleId="jlje" styleClass="inputtext" maxlength="6"></html:text>
						</td>
						</tr>
						<tr align="" style="width:35px">
						<td align="right"> 
							设置成绩学分标准：
						</td>
						<td align="left">
							<html:text property="szjdbz" styleId="szjdbz" styleClass="inputtext" maxlength="5" onblur="ckdata(this)"></html:text>
						</td>
						</tr>
						<tr align="" style="width:35px">
						<td align="right"> 
							素质拓展学分标准：
						</td>
						<td align="left">
							<html:text property="sztzxfbz" styleId="sztzxfbz" styleClass="inputtext" maxlength="5" onblur="ckdata(this)"></html:text>
						</td>
						</tr>
					</logic:equal>
				</logic:present>
				<logic:present name="rychdmb">
					<logic:equal value="yes" name="rychdmb">
						<tr style="width:35px">
							<td align="right">
								<font  color="red">*</font>荣誉称号代码：
							</td>
							<td align="left">
								<html:text property="rychdm" styleId="rychdm" styleClass="inputtext" maxlength="10" readonly="true"></html:text>
							</td>
						</tr>
						<tr style="width:35px">
							<td align="right">
								<font  color="red">*</font>荣誉称号名称：
							</td>
							<td align="left">
								<html:text property="rychmc" styleId="rychmc" styleClass="inputtext"></html:text>
							</td>
						</tr>
						<tr style="width:35px">
							<td align="right">
								荣誉称号类别：
							</td>
							<td align="left">
								<html:select property="rychlb" styleId="rychlb"
								style="width:150px" styleClass="select" disabled="true">
									<html:options collection="jxjlbList" property="en" labelProperty="cn"/>
								</html:select>
							</td>
						</tr>
					</logic:equal>
				</logic:present>
				<logic:present name="jxjxdmb">
					<logic:equal value="yes" name="jxjxdmb">
						<tr style="width:35px">
							<td align="right">
								<font  color="red">*</font>获奖代码：
							</td>
							<td align="left">
								<html:text property="jxdm" styleId="jxdm" styleClass="inputtext" maxlength="10" readonly="true"></html:text>								
							</td>
						</tr>
						<tr style="width:35px">
							<td align="right">
								<font  color="red">*</font>获奖名称：
							</td>
							<td align="left">
								<html:text property="jxmc" styleId="jxmc" styleClass="inputtext"></html:text>								
							</td>
						</tr>
					</logic:equal>
				</logic:present>	
		</table>
		<div class="buttontool" align="center" >
			<logic:equal value="yes" name="writable">
				<logic:equal value="yes" name="iswritable">
					<button class="button2" style="" id="btn_save"
						onclick="savedm('dmmodi.do');">
						&nbsp;&nbsp;保&nbsp;&nbsp;存&nbsp;&nbsp;
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</logic:equal>
					<button class="button2" onclick="window.close();return false;" >
						&nbsp;&nbsp;关&nbsp;&nbsp;闭&nbsp;&nbsp;
					</button>
			</logic:equal>
		</div>
	</fieldset>
	<logic:notEmpty name="inserted">
				<logic:equal value="no" name="inserted">
					<script language="javascript">
		alert("操作失败！");
		</script>
				</logic:equal>
				<logic:equal value="yes" name="inserted">
					<script language="javascript">
		alert("操作成功！");
		</script>
				</logic:equal>
				<logic:equal value="nowirtable" name="inserted">
					<script language="javascript">
		alert("您无权修改此信息！");
		</script>
				</logic:equal>
				<script language="javascript">
			Close();
			var tN = window.dialogArguments.document.forms[0].tname.value;
			window.dialogArguments.document.getElementById(tN).click();
		</script>
			</logic:notEmpty>
	</html:form>
	
	<script type="text/javascript">
		function savedm(url) {
			var realTable = document.getElementById('realTable').value;
			url += '?realTable=';
			url += realTable;
			if (realTable=='jxjdmb') {
				url += '&jxjlb=';
				url += document.getElementById('jxjlb').value;
				if (document.getElementById('jxjdm').value=='' || document.getElementById('jxjdm').value==null 
				|| document.getElementById('jxjmc').value=='' || document.getElementById('jxjmc').value==null 
				|| document.getElementById('jxjjb').value=='' || document.getElementById('jxjjb').value==null 
				|| document.getElementById('jlje').value=='' || document.getElementById('jlje').value==null) {
					alert('带*号为必填，请确认！');
					return;
				}
			}
			if (realTable=='rychdmb') {
				url += '&rychlb=';
				url += document.getElementById('rychlb').value;
				if (document.getElementById('rychdm').value==null || document.getElementById('rychdm').value==''
				|| document.getElementById('rychmc').value==null || document.getElementById('rychmc').value=='') {
				alert('带*号为必填，请确认！');
				return;
				}
			}
			if (realTable=='jxjxdmb') {
				if (document.getElementById('jxdm').value==null || document.getElementById('jxdm').value==''
				|| document.getElementById('jxmc').value==null || document.getElementById('jxmc').value=='') {
					alert('带*号为必填，请确认！');
					return;
				}
			}
			refreshForm(url);
			document.getElementById('btn_save').disabled = true;
			//window.close();
		}
		function onlynum() {
			if (event.keyCode>=48 && event.keyCode<=57) {
				event.returnValue = true;
			}else {
				event.returnValue = false;
			}
		}
	</script>
</body>
