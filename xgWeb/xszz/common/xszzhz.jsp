<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
<script language="javascript" src="js/function.js"></script>
<script language="javascript">
function zzhzOutput(){
	var str = "";
	str += "/xgxt/xszzhz_output.do";
	var seldiv = document.getElementById("tarSel");
	var selectTab = document.getElementById("selectTab").value;
	var targSel = seldiv.getElementsByTagName("select")[0];
	var actionStr = "?queryStr=";
	if((selectTab == null) || (selectTab == "")){
		alert("请选择要导出的项目！！");
		return false;
	}
	if(targSel.options.length==0){
		alert("请选择导出字段！！");
		return false;
	}
	for(var i=0;i<targSel.options.length;i++){
		if(i==targSel.options.length-1){
			actionStr += targSel.options[i].value;
			continue;
		}
		actionStr += targSel.options[i].value+"!!";
	}
	actionStr += "&tableName="+document.getElementById("selectTab").value;
	actionStr += "&nj="+document.getElementById("nj").value;
	actionStr += "&xh="+document.getElementById("xh").value;
	actionStr += "&xydm="+document.getElementById("xydm").value;
	actionStr += "&zydm="+document.getElementById("zydm").value;
	actionStr += "&bjdm="+document.getElementById("bjdm").value;
	str += actionStr;
	window.open(str);
}

function zzhzOutput_nd(){
	var str = "";
	str += "/xgxt/xszzhz_output.do";
	var seldiv = document.getElementById("tarSel");
	var selectTab = document.getElementById("selectTab").value;
	var targSel = seldiv.getElementsByTagName("select")[0];
	var actionStr = "?queryStr=";
	if((selectTab == null) || (selectTab == "")){
		alert("请选择要导出的项目！！");
		return false;
	}
	if(targSel.options.length==0){
		alert("请选择导出字段！！");
		return false;
	}
	for(var i=0;i<targSel.options.length;i++){
		if(i==targSel.options.length-1){
			actionStr += targSel.options[i].value;
			continue;
		}
		actionStr += targSel.options[i].value+"!!";
	}
	actionStr += "&tableName="+document.getElementById("selectTab").value;
	actionStr += "&nj="+document.getElementById("nj").value;
	actionStr += "&xh="+document.getElementById("xh").value;
	actionStr += "&xydm="+document.getElementById("xydm").value;
	actionStr += "&zydm="+document.getElementById("zydm").value;
	actionStr += "&bjdm="+document.getElementById("bjdm").value;
	actionStr += "&nd="+document.getElementById("nd").value;
	str += actionStr;
	window.open(str);
}

function zzhzOutput_xn(){
	var str = "";
	str += "/xgxt/xszzhz_output.do";
	var seldiv = document.getElementById("tarSel");
	var selectTab = document.getElementById("selectTab").value;
	var targSel = seldiv.getElementsByTagName("select")[0];
	var actionStr = "?queryStr=";
	if((selectTab == null) || (selectTab == "")){
		alert("请选择要导出的项目！！");
		return false;
	}
	if(targSel.options.length==0){
		alert("请选择导出字段！！");
		return false;
	}
	for(var i=0;i<targSel.options.length;i++){
		if(i==targSel.options.length-1){
			actionStr += targSel.options[i].value;
			continue;
		}
		actionStr += targSel.options[i].value+"!!";
	}
	actionStr += "&tableName="+document.getElementById("selectTab").value;
	actionStr += "&nj="+document.getElementById("nj").value;
	actionStr += "&xh="+document.getElementById("xh").value;
	actionStr += "&xydm="+document.getElementById("xydm").value;
	actionStr += "&zydm="+document.getElementById("zydm").value;
	actionStr += "&bjdm="+document.getElementById("bjdm").value;
	actionStr += "&xn="+document.getElementById("xn").value;
	str += actionStr;
	window.open(str);
}

function ondblckEv(obj,all){
	var targTabCols = document.getElementById("targTabCols");
	var srcColLength = document.getElementById("srctab").options.length;
	var selArr = new Array();
	var temp = new Array();
	
	if((all != null || all != "") && all=="right"){
		targTabCols.outerHTML = obj.outerHTML;
		var o = document.getElementsByName("srctab")[1];
		o.id="targTabCols";
		o.name="targTabCols";
		return true;
	} else if((all != null || all != "") && all=="left"){
		document.getElementById("tarSel").innerHTML = '<select id=\"targTabCols\" name=\"targTabCols\" ondblclick=\"ondblckEv(this)\"  multiple=\"multiple\"  style=\"width:100%;height:300\"></select>';
		return true;
	}
	for(var i=0;i<obj.options.length;i++){
		if(obj.options[i].selected){
			var subArr = new Array();
			subArr.push(obj.options[i].value);
			subArr.push(obj.options[i].innerText);
			selArr.push(subArr);
			temp.push(obj.options[i]);
		}
	}
	if(obj.id=="srctab"){
		for(var i=0;i<selArr.length;i++){
			var op = document.createElement("option");
			op.value = selArr[i][0];
			var text = document.createTextNode(selArr[i][1]);
			op.appendChild(text);
			targTabCols.appendChild(op);
		}
	} else {
		for(var i=0;i<temp.length;i++){
			targTabCols.removeChild(temp[i]);
		}
	}
}
</script>
</head>
<body>
	<html:form action="/xszzhz">
		<div class="tab_cur">
				<p class="location">
				<em>您的当前位置：</em><a>
					<bean:write name="tips" scope="request" />
					</a>
				</p>
		</div>
		<div class="toolbox">
		<div class="searchtab">
			<table width="100%" border="0">
				<tbody>
					<tr>
						<th>
							年级
						</th>
						<td>	
							<html:select property="nj" style="width:80px"
								onchange="refreshForm('xszzhz.do')">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
						</td>
						<logic:equal name="xnnd" value="xn">
						<th>
						学年
						</th>
						<td>
							<html:select property="xn" style="width:100px" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
						</td>	
						</logic:equal>
						<logic:equal name="xnnd" value="nd">
						<th>
						年度
						</th>
						<td>
							<html:select property="nd" style="width:100px" styleId="nd">
									<html:options collection="ndList" property="nd"
										labelProperty="nd" />
								</html:select>
						</td>
						</logic:equal>
						<th>
							学号
						</th>
						<td>
							<html:text property="xh" style="width:85px"></html:text>
						</td>
<%--						<td width="10" rowspan="2" align="center" valign="middle">--%>
<%--						</td>--%>
<%--						<td width="10" rowspan="2" align="center" valign="middle">--%>
<%--							<button class="button2" style="height:40px;width:40px" onclick="">--%>
<%--								&nbsp;选择&nbsp;--%>
<%--							</button>--%>
<%--						</td>--%>
					</tr>
					<tr>
						<th>
							<bean:message key="lable.xsgzyxpzxy" />
						</th>
						<td>
							<html:select property="xydm" style="width:180px" styleId="xy"
								onchange="refreshForm('xszzhz.do')" disabled="${disable}">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</td>	
						<th>
							专业
						</th>
						<td>
							<html:select property="zydm" style="width:180px" styleId="zy"
								onchange="refreshForm('xszzhz.do')">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
						</td>	
						<th>
							班级
						</th>
						<td>
							<html:select property="bjdm" style="width:180px" styleId="bj">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<table width="99%"  border="0" class="formlist">
			<thead>
   				<tr>
       				<th colspan="4"><span>选择导出项目</span>
       				<html:select styleId="selectTab" property="hzxmb"
						value="${tableName}" onchange="refreshForm('xszzhz.do')">
						<html:option value=""></html:option>
						<html:options collection="zzxmList" property="hzxmb"
							labelProperty="hzxmmc" />
					</html:select>
       				</th>
       			</tr>
  			</thead>
			
			<tbody>
				<tr  height="250px">
					<td width="45%" >
						<html:select name="rs" property="en" styleId="srctab"
							multiple="multiple" ondblclick="ondblckEv(this)"
							style="width:100%;height:300px">
							<html:options collection="srcTabColsList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
					<td width=10% align="center">
						<button class="button2" style="width:90%"
							onclick="ondblckEv(document.getElementById('srctab'))">
							&gt;
						</button>
						<br/>
							<button class="button2" style="width:90%"
								onclick="ondblckEv(document.getElementById('srctab'),'right')">
								&gt;&gt;
							</button>
						<br/>
							<button class="button2" style="width:90%"
								onclick="ondblckEv(document.getElementById('targTabCols'))">
								&lt;
							</button>
						<br/>
							<button class="button2" style="width:90%"
								onclick="ondblckEv(document.getElementById('targTabCols'),'left')">
								&lt;&lt;
							</button>
						<br/>
					</td>
					<td width=45%>
						<div id="tarSel">
							<select id="targTabCols" name="targTabCols" 
								ondblclick="ondblckEv(this)" multiple="multiple"
								style="width:100%;height:300px">
							</select>
						</div>
					</td>
				</tr>
				<tr>
				<td colspan="4" >
				<div class="buttontool" align="center">
					<logic:equal name="xnnd" value="null">
						<button class="button2" onclick="zzhzOutput()">
							输出数据
						</button>
					</logic:equal>
					<logic:equal name="xnnd" value="xn">
						<button class="button2" onclick="zzhzOutput_xn()">
							输出数据
						</button>
					</logic:equal>
					<logic:equal name="xnnd" value="nd">
						<button class="button2" onclick="zzhzOutput_nd()">
							输出数据
						</button>
					</logic:equal>
				</div>
				</td>
				</tr>
				</tbody>
			</table>
	</html:form>
</body>
<script>
     
  </script>
</html>
