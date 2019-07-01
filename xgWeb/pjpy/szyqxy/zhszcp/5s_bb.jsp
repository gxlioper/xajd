<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/pjpy/pjpy_szyqxy.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getSzPjpyDAO.js'></script>
		<script type="text/javascript">	

function addBjP(){
	var fromIndx = $("bj").selectedIndex;
	var toIndx = $("bjP").options.length;
	if(fromIndx < 0){
		return false;
	}

	for(var i=0;i<toIndx;i++){
		if($("bj").options[fromIndx].value == $("bjP").options[i].value){
			return false;
		}
	}
	$("bjP").options[$("bjP").options.length] = new Option($("bj").options[fromIndx].text,$("bj").options[fromIndx].value);
	$("bj").options[fromIndx] = null;
	if($("bj").options.length > 0){
		$("bj").options[0].selected = true;
		//$("delBj").disabled = false;
	}else{		
		//$("addBj").disabled = true;
	}
	if($("bjP").options.length > 0){
		$("bjP").options[0].selected = true;
	}
}

function delBjP(){
	var toIndx = $("bjP").selectedIndex;
	var fromIndx = $("bj").options.length;
	if(toIndx < 0){
		return false;
	}
	for(var i=0;i<fromIndx;i++){
		if($("bjP").options[toIndx].value == $("bj").options[i].value){
			$("bjP").options[toIndx] = null;
			return false;
		}
	}
	$("bj").options[$("bj").options.length] = new Option($("bjP").options[toIndx].text,$("bjP").options[toIndx].value);
	$("bjP").options[toIndx] = null;
	if($("bjTlist").options.length > 0){
		$("bjP").options[0].selected = true;
		//$("addBj").disabled = false;
	}else{		
		//$("delBj").disabled = true;
	}	
	if($("bj").options.length > 0){
		$("bj").options[0].selected = true;
	}
	
}
function print(){
	var xn = $("xn").value;
	var xq = $("xq").value;
	if(xn == "" || xq == ""){
		alert("学年学期不能为空，请确认！！");
		return false;
	}
	if($("bjP").options.length == 0){
		alert("请确定班级！！");
		return false;
	}
	for(var i = 0 ; i < $("bjP").options.length; i++){
		var tmp = document.createElement("input");
		tmp.type = "hidden";
		tmp.name = "checkVal";
		tmp.value = $("bjP").options[i].value;
		document.forms[0].appendChild(tmp);
	}
	document.forms[0].action = "/xgxt/pjpyszyqwh.do?method=szyc_5sPrint&bblx="+$("bblx").value;
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}
function chBblx(bblx){
	if(bblx == "ydhz"){
		$("yf").style.display = "";
		$("zc").style.display = "none";
	}else if(bblx == "xqhz"){
		$("yf").style.display = "none";
		$("zc").style.display = "none";
	}else if(bblx == "hz"){
		$("yf").style.display = "none";
		$("zc").style.display = "";
	}

}
	</script>
	</head>
	<body onload="xyDisabled('xy');">
		<html:form action="/pjpyszyqwh">
			<input type="hidden" id="url" name="url"
				value="/pjpy/szyqxy/zhszcp/5s_Add.jsp" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xh-xm-xb-xymc-zymc-bjmc" />
			<input type="hidden" id="pk" name="pk" value="${pk}" />
			<input type="hidden" id="xnV" name="xnV" value="${xnV}" />
			<input type="hidden" id="xqV" name="xqV" value="${xqV}" />
			<input type="hidden" name="njV" id="njV" />
			<input type="hidden" name="ndV" id="ndV" />
			<input type="hidden" name="xyV" id="xyV" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" id="userType" name="userType"
				value="${userType}" />



			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<logic:notEqual name="doType" value="view">
										<button type="button" name="打 印" onclick="print()">
											打 印
										</button>
									</logic:notEqual>
									<button type="button" name="关闭" onclick="window.close();return false;">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="13%">
								学年
							</th>
							<td>
								<html:select property="xn" style="" styleClass="select"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
							<th width="10%">
								学期
							</th>
							<td>
								<html:select property="xq" style="" styleClass="select"
									styleId="xq" onchange="setYfList(this.value)">
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								报表类型
							</th>
							<td>
								<html:select property="bblx" styleClass="select" styleId="bblx"
									onchange="chBblx(this.value)">
									<html:option value="xqhz">学期5S汇总表</html:option>
									<html:option value="ydhz">月度5S汇总表</html:option>
									<html:option value="hz">5S汇总表</html:option>
								</html:select>
							</td>
							<th>
								时间
							</th>
							<td align="left">
								<html:select property="yf" style="display: none"
									styleClass="select" styleId="yf">
									<html:options collection="yfList" property="yfdm"
										labelProperty="yfmc" />
								</html:select>
								<html:select property="zc" style="display: none"
									styleClass="select" styleId="zc">
									<html:options collection="zcList" property="zcdm"
										labelProperty="zcmc" />
								</html:select>
							</td>
						</tr>
						<logic:notEqual name="userType" value="bzr">
							<tr style="height: 23px">
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm"
										onchange="initPrintZyList();initPrintBjList();"
										styleClass="select" style="" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									专业
								</th>
								<td align="left">
									<html:select property="zydm" onchange="initPrintBjList();"
										style="" styleClass="select" styleId="zy">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
							</tr>
						</logic:notEqual>
						<tr>
							<th valign="middle">
								<p>
									班
								</p>
								<p>
									级
								</p>
							</th>
							<td width="40%">
								<html:select property="bjdm" styleId="bj" size="12"
									style="width:100% ">
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
							<td nowrap width="10%">
								<button type="button" class="button2" style="width:100%" id="addBj"
									onclick="addBjP()">
									&gt;&nbsp;&gt;
								</button>
								<br />
								<br />
								<br />
								<button type="button" class="button2" style="width:100%" id="delBj"
									onclick="delBjP();">
									&lt;&nbsp;&lt;
								</button>
							</td>
							<td width="">
								<html:select property="bjdm" styleId="bjP" size="12"
									style="width:100% ">
									<html:options collection="bjPList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('操作成功！');
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('操作失败！');
					</script>
				</logic:equal>
			</logic:present>
			<logic:present name="msg">
				<script>
					alert($("msg").value);
				</script>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
