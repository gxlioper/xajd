<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script type="text/javascript" src="pjpy/nbzy/nbzyJs.js">
</script>
		<script language="javascript">
function sf(){
	if(document.getElementById('tname').value=="nbzy_tjszb"){
		document.getElementById("基本条件设置").className = "ha";
		document.getElementById("英语条件设置").className = "";
	}
	if(document.getElementById('tname').value=="nblg_jxjyytjszb"){
		document.getElementById("英语条件设置").className = "ha";
		document.getElementById("基本条件设置").className = "";
	}
	
}
	function savetj() {
	var xn = document.getElementById('xn').value;
	var jxjdm = document.getElementById('jxjdm').value;
	var tj = document.getElementById('tj').value;
	if (xn==''||jxjdm==''||tj=='') {
		alert('所有条件不能为空,请确认!');
		return;	
	} else {
		refreshForm('pjpy_nblg_tjszsave.do');
	}
	
}
function chgCode(objTr) {
		document.forms[0].tname.value = objTr.id;
		var tmpName = objTr.id;
		document.forms[0].action = 'pjpy_nblg_tjsz.do';
		document.forms[0].submit();
}

	

function chkdatatype(obj) {
	obj.value = obj.value.replace(/[^(\d|\.)]/g,'');
		var inputStr = obj.value;
		
		if(!(inputStr.match(/\d+/g) || inputStr.match(/\d+\.?\d{0,1}/g))){
			alert('数据格式不正确，只能是数字！');
			obj.value = '';
			return false;
		}
		return true;
}
</script>
	</head>
	<body onload="pageCardOn('');sf()">
		<html:form action="/pjpynblgwh" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>评奖评优 - 参数设置 - 奖学金条件设置</a>
				</p>
			</div>
			<input type="hidden" id="zyV" name="zyV" value="" />
			<input type="hidden" id="bjV" name="bjV" value="" />
			<input type="hidden" id="search_go"
				onclick="refreshForm('pjpy_nblg_tjsz.do');" />
			<input type="hidden" id="tname" name="tname" value="${tname }" />
			<!-- 页签 -->
			<div class="compTab" id="card">
				<div class="comp_title" id="card">
					<ul id="ul1">
						<logic:iterate id="card" name="pageCard">
							<li id="<bean:write name="card" property="cn" />">
								<a href="#" onclick="chgCode(this);"
									id="<bean:write name="card" property="en" />"> <span><bean:write
											name="card" property="cn" /> </span> </a>
							</li>
						</logic:iterate>
					</ul>
				</div>
			</div>
			<!-- 页签 end-->
			<logic:equal value="yes" name="writeAble">
				<div class="toolbox">
					<div class="buttonbox">
						<input type="hidden" name="ts" id="ts" />
						<ul>
							<li>
								<a href="#" class="btn_sc"
									onclick="deldata1('pjpy_nblg_tjsz.do?act=del');">删除条件</a>
							</li>
							<li>
								<a href="#" class="btn_ccg" onclick="savetj()">保存条件</a>
							</li>
						</ul>
					</div>
				</div>
			</logic:equal>
			<div class="xxk" style="display: none">
				<logic:notEmpty name="pageCard">
					<logic:iterate id="card" name="pageCard">
						<ul>
							<li id="<bean:write name="card" property="en"/>l"
								class="xxk_off_l"></li>
							<li id="<bean:write name="card" property="en"/>"
								onclick="chgCode(this)" class="xxk_off_m">
								&nbsp;
								<bean:write name="card" property="cn" />
								&nbsp;
							</li>
							<li id="<bean:write name="card" property="en"/>r"
								class="xxk_off_r"></li>
						</ul>
					</logic:iterate>
				</logic:notEmpty>
			</div>
			<div class="searchtab">
				<table width="100%" class="" border="0">
					<tbody>
						<tr>
							<th align="left">
								学年
							</th>
							<td>
								<html:select property="xn" style="width:90px"
									styleClass="select" styleId="xn"
									onchange="refreshForm('pjpy_nblg_tjsz.do');">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
							<th align="left">
								奖学金
							</th>
							<td>
								<html:select property="jxjdm" styleId="jxjdm"
									onchange="refreshForm('pjpy_nblg_tjsz.do');"
									style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="jxjList" property="jxjdm"
										labelProperty="jxjmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<logic:equal value="nbzy_tjszb" name="tname">
								<th align="left">
									条件
								</th>
								<td>
									<html:select property="tj" styleId="tj">
										<html:option value=""></html:option>
										<html:options collection="tjList" property="en"
											labelProperty="cn" />
									</html:select>
									&nbsp;&nbsp; ---&gt; &nbsp;&nbsp;
									<html:text property="bl" styleId="bl" style="width:100px"
										onblur="ckinpdata(this)"></html:text>
									<font color="red">(%)</font>
								</td>
							</logic:equal>
							<logic:notEqual value="nbzy_tjszb" name="tname">
								<th align="left">
									条件
								</th>
								<td>
									<html:select property="tj" styleId="tj" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="tjList" property="en"
											labelProperty="cn" />
									</html:select>
									&nbsp;&nbsp; ---&gt;
									<font color="red">(大于等于)</font>
									<html:text property="fs" styleId="fs" style="width:100px"
										onblur="chkdatatype(this)"></html:text>
									<font color="red">(分)</font>
								</td>
							</logic:notEqual>
						</tr>
					</tbody>
				</table>
				<div class="formbox">
					<logic:empty name="rs">
						<h3 class="datetitle_01">
							<span> 查询结果&nbsp;&nbsp; <font color="red">未找到任何记录！</font>
							</span>
						</h3>
					</logic:empty>
					<logic:notEmpty name="rs">
						<fieldset>
							<legend>
								记录数： ${rsNum}
							</legend>
							<table summary="" id="rsTable" class="dateline" width="100%">
								<thead>
									<tr align="center" style="cursor: hand">
										<td>
											<input type="checkbox" name="qbxz" value="all"
												onclick=
	chec('qbxz');
>
										</td>
										<logic:iterate id="tit" name="topTr" offset="1">
											<td id="<bean:write name="tit" property="en"/>"
												onclick=
	tableSort(this);
nowrap="nowrap">
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick=
	rowOnClick(this);
style="cursor: hand;">
										<td align=center>
											<input type="checkbox" id="cbv" name="cbv"
												value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
						</fieldset>
					</logic:notEmpty>
					<div id="tmpdiv"></div>
				</div>
			</div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js">
	
</script>
		<logic:present name="deleted">
			<logic:equal value="yes" name="deleted">
				<script>
	alert('操作成功！');
	document.getElementById('search_go').onclick();
</script>
			</logic:equal>
			<logic:equal value="no" name="deleted">
				<script>
	alert('操作失败!');
	document.getElementById('search_go').onclick();
</script>
			</logic:equal>
		</logic:present>
	</body>
</html>