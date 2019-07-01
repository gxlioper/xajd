<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript">
function zzhzOutput(){
	var str = "";
	str += "/xgxt/new_common_xszz.do?method=xszzhzExp";
	var seldiv = document.getElementById("tarSel");
	var selectTab = document.getElementById("selectTab").value;
	var targSel = seldiv.getElementsByTagName("select")[0];
	var actionStr = "&queryStr=";
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
	str += "/xgxt/new_common_xszz.do?method=xszzhzExp";
	var seldiv = document.getElementById("tarSel");
	var selectTab = document.getElementById("selectTab").value;
	var targSel = seldiv.getElementsByTagName("select")[0];
	var actionStr = "&queryStr=";
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
	str += "/xgxt/new_common_xszz.do?method=xszzhzExp";
	var seldiv = document.getElementById("tarSel");
	var selectTab = document.getElementById("selectTab").value;
	var targSel = seldiv.getElementsByTagName("select")[0];
	var actionStr = "&queryStr=";
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
		document.getElementById("tarSel").innerHTML = '<select id=\"targTabCols\" name=\"targTabCols\" ondblclick=\"ondblckEv(this)\"  multiple=\"multiple\"  onmouseover=\"null\" style=\"width:100%;height:100%\"></select>';
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
			//alert(selArr[i][0]);
			//alert(selArr[i][1]);
			//targTabCols.appendChild(op);
			targTabCols.options[targTabCols.options.length] = new Option(selArr[i][1],selArr[i][0]);
		}
	} else {
		for(var i=0;i<temp.length;i++){
			//targTabCols.removeChild(temp[i]);
			targTabCols.options[i]=null;
		}
	}
}
</script>
	</head>
<body onload="xyDisabled('xy');">
	<!-- 标题 -->
	<div class="tab_cur">
		<p class="location">
			<em>您的当前位置：</em><a><bean:write name="tips" scope="request" /></a>
		</p>
	</div>
	<!-- 标题 end-->
	<html:form action="/new_common_xszz.do?method=xszzNewhz">
		<input type="hidden" id="userType" name="userType" 
				value="<bean:write name="userType" scope="request"/>" />
		<input type="hidden" name="xyV" value=""/>
		<input type="hidden" name="zyV" value=""/>
		<input type="hidden" name="bjV" value=""/>
		<input type="hidden" name="zyV" id="zyV" />
		<input type="hidden" name="bjV" id="bjV" />

			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="xnnd" value="null">
							<li>
								<a href="#"
									onclick="zzhzOutput();return false;"
									class="btn_dc">输出数据</a>
							</li>
						</logic:equal>
						<logic:equal name="xnnd" value="xn">
							<li>
								<a href="#"
									onclick="zzhzOutput_xn();return false;"
									class="btn_dc">输出数据</a>
							</li>
						</logic:equal>
						<logic:equal name="xnnd" value="nd">
							<li>
								<a href="#"
									onclick="zzhzOutput_nd();return false;"
									class="btn_dc">输出数据</a>
							</li>
						</logic:equal>
					</ul>
				</div>
				<!-- 按钮 end-->	
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tbody>
							<!-- 第一行 -->
							<tr>
								<th>
									年级
								</th>
								<td>
									<html:select name="rs" property="nj" style="width:80px"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>							
								</td>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select name="rs" property="xydm" style="width:180px" styleId="xy"
										onchange="initZyList();initBjList()" disabled="${disable}">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select name="rs" property="zydm" style="width:180px" styleId="zy"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									班级
								</th>
								<td>
									<html:select name="rs" property="bjdm" style="width:180px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>				
								</td>
								<th>
									学号
								</th>
								<td>
									<html:text name="rs" property="xh" style="width:85px"></html:text>
								</td>
								<th>
									<logic:equal name="xnnd" value="xn">
										学年
									</logic:equal>
									<logic:equal name="xnnd" value="nd">
										年度
									</logic:equal>
								</th>
								<td>
									<logic:equal name="xnnd" value="xn">
										<html:select name="rs" property="xn" style="width:100px" styleId="xn">
											<html:options collection="xnList" property="xn" labelProperty="xn" />
										</html:select>
									</logic:equal>
									<logic:equal name="xnnd" value="nd">
										<html:select name="rs" property="nd" style="width:100px" styleId="nd">
											<html:options collection="ndList" property="nd" labelProperty="nd" />
										</html:select>
									</logic:equal>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 过滤条件 end-->
			</div>
			<!-- 多功能操作区 end -->
		<table class="formlist" border="0" align="center" style="width: 100%">
			<thead>
				<tr>
					<th colspan="4">
						<span>选择导出字段</span>
					</th>
				</tr>
			</thead>
			<tbody>		
				<tr style="height: 23px">
					<th width="10%">
						导出项目
					</th>
					<td>
						<html:select styleId="selectTab" property="hzxmb"
							value="${tableName}" onchange="refreshForm('new_common_xszz.do?method=xszzNewhz')">
							<html:option value=""></html:option>
							<html:options collection="zzxmList" property="hzxmb"
								labelProperty="hzxmmc" />
						</html:select>
					</td>
				</tr>
				<tr style="height: 420px">
					<td colspan="2">
						<table class="tbstyle" style="width:100%;height: 100%">
							<tr style="height: 100%">
								<td width="45%">
									<html:select name="rs" property="en" styleId="srctab"
										multiple="multiple" ondblclick="ondblckEv(this)"
										style="width:100%;height:100%" onmouseover="null">
										<html:options collection="srcTabColsList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<td width=10% align="center">
									<p>
										<button class="button2" style="width:90%"
											onclick="ondblckEv(document.getElementById('srctab'),'right')">
											&gt;&gt;
										</button>
									</p><br/><br/>
									<p>
										<button class="button2" style="width:90%"
											onclick="ondblckEv(document.getElementById('srctab'))">
											&gt;
										</button>
									</p><br/><br/>
									<p>
										<button class="button2" style="width:90%"
											onclick="ondblckEv(document.getElementById('targTabCols'))">
											&lt;
										</button>
									</p><br/><br/>
									<p>
										<button class="button2" style="width:90%"
											onclick="ondblckEv(document.getElementById('targTabCols'),'left')">
											&lt;&lt;
										</button>
									</p>
								</td>
								<td width=45%>
									<div id="tarSel">
										<select id="targTabCols" name="targTabCols"
											ondblclick="ondblckEv(this)" multiple="multiple"
											style="width:100%;height:100%" onmouseover="null">
										</select>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
	</html:form>
</body>
</html>
