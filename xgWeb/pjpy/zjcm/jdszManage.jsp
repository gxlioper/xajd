<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script type="text/javascript" src="js/pjpy/pjpy_szyqxy.js"></script>
	<script language="javascript"  src="js/sztzFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript"  src="js/pjpy/pjpyFunction.js"></script>
	<script type="text/javascript">	
	
	//判断显示奖学金列表或者是荣誉称号列表
	function chLx(){
		var lx = $("lx").value;
		if("jxj" == lx){
			$("jxj").style.display = "";
			$("jxjsp").style.display = "";
			$("rychsp").style.display = "none";
			$("rych").style.display = "none";
		}else if("rych" == lx){
			$("jxj").style.display = "none";
			$("jxjsp").style.display = "none";
			$("rychsp").style.display = "";
			$("rych").style.display = "";
		}else{
			$("jxj").style.display = "none";
			$("rych").style.display = "none";
		}
	}
	
	//清空兼得情况
	function qkJd(){
		if (confirm("将要清空该项目的所有兼得情况，请确认！")) {
			var url = "/xgxt/zjcmPjpy.do?method=jdszManage&doType=cancel";
			saveUpdate(url,'xn-xq-lx');
		}	
	}
	
	//保存奖学金（荣誉称号）兼得情况
	function saveJd(){
	
		var lx = $("lx").value;
		var jxjmsg = "";
		var rychmsg = "";
		var msg = "所选择";
		var jxjnum = "0";
		var rychnum = "0";

		if("jxj" == lx){
			if($("jxjdm").value == ""){
				alert("请确认被设置的奖学金");
				return false;
			}
		}else if("rych" == lx){
			if($("rychdm").value == ""){
				alert("请确认被设置的荣誉称号");
				return false;
			}
		}
			
		if($("jxjnum")){
			jxjnum = $("jxjnum").value;
		}
		if($("rychnum")){
			rychnum = $("rychnum").value;
		}
		
		for(var i = 1;i<jxjnum;i++){
			var jxjdm = "jxjdm"+i;
			var jxjmc = "jxjmc"+i;
			if($(jxjdm)){
				if($(jxjdm).checked){
					if(jxjmsg == ""){
						jxjmsg += $(jxjmc).value;
					}else{
						jxjmsg += ","+$(jxjmc).value;
					}
				}
			}
		}
		
		for(var i = 1;i<rychnum;i++){
			var rychdm = "rychdm"+i;
			var rychmc = "rychmc"+i;
			if($(rychdm)){
				if($(rychdm).checked){
					if(rychmsg == ""){
						rychmsg += $(rychmc).value;
					}else{
						rychmsg += ","+$(rychmc).value;
					}
				}
			}
		}
		
		if(jxjmsg != ""){
			msg+="不可兼得的奖学金包括("+jxjmsg+")\n";
		}
		if(rychmsg != ""){
			msg+="不可兼得的荣誉称号包括("+rychmsg+")\n";
		}
		if(jxjmsg == "" && rychmsg == "" ){
			msg+="不可兼得项目为空，请确认";
			alert(msg);
			return false;
		}
		msg += "请确认！！"
		if (confirm(msg)) {
			var url = "/xgxt/zjcmPjpy.do?method=jdszManage&doType=save";
			saveUpdate(url,'xn-xq-lx');
		}	
	}
	
	//显示已经保存好的兼得情况
	function showCheck(){
		var jxjnum = "0";
		var rychnum = "0";
		
		if($("jxjnum")){
			jxjnum = $("jxjnum").value;
		}
		if($("rychnum")){
			rychnum = $("rychnum").value;
		}
		
		if($("jdnum")){
			var jdnum = $("jdnum").value;
			if(jdnum != ""){
				for(var i = 0; i < jdnum; i++){
					var lxid = "fjdlx"+i;
					var dmid = "fjddm"+i;
					if($(lxid)){
						var dm = $(dmid).value;
						if($(lxid).value == "jxj"){
							for(var j = 1;j<jxjnum;j++){
								var jxjdm = "jxjdm"+j;
								var jxjmc = "jxjmc"+j;
								if($(jxjdm)){
									if($(jxjdm).value == dm){
										$(jxjdm).checked = true;
									}
								}
							}
						}
						if($(lxid).value == "rych"){
							for(var j = 1;j<rychnum;j++){
								var rychdm = "rychdm"+j;
								var rychmc = "rychmc"+j;
								if($(rychdm)){
									if($(rychdm).value == dm){
										$(rychdm).checked = true;
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	function getJdqk(){
		var xn = $("xn").value;
		var xq = $("xq").value;
		var lx = $("lx").value;
		var jxjdm = $("jxjdm").value;
		var rychdm = $("rychdm").value;
		
		if(xn != "" && xq != "" && lx != "" && (jxjdm != "" || rychdm != "")){
			var url = "/xgxt/zjcmPjpy.do?method=jdszManage&doType=view";
			refreshForm(url);
		}
	}
	</script>
	
	<body onload="showCheck();chLx();">
		<html:form action="/zjcmPjpy">
			<input type = "hidden" name="jxjnum"  id="jxjnum" value="${jxjnum }"/>
			<input type = "hidden" name="rychnum" id="rychnum" value="${rychnum }"/>
			<input type = "hidden" name="jdnum" id="jdnum" value="${jdnum }"/>
			<logic:present name="rsList">
			<logic:iterate name="rsList" id="jd" indexId="index">
				<input type="hidden" id="fjdlx${index }" value="${jd.fjdlx }"/>
				<input type="hidden" id="fjddm${index }" value="${jd.fjddm }"/>
			</logic:iterate>
			</logic:present>
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：<bean:write name="title" />
				</div>
			</div>
			<table class="tbstyle" border="0" id="rsTable" align="center"
				style="width: 100%">
				<thead>
					<tr>
						<td colspan="4" align="center">
							兼得设置
						</td>
					</tr>
				</thead>
					<tr>
						<td align="right" width="20%">
							<font color="red">*</font>评奖学年：
						</td>
						<td align="left" width="30%">
							<html:select name="rs" property="xn" style="" onchange="getJdqk();">
								<html:options collection="xnList" property="xn" labelProperty="xn" />
							</html:select>			
						</td>
						<td align="right" width="20%">
							<font color="red">*</font>评奖学期：
						</td>
						<td align="left" width="30%">
							<html:select name="rs" property="xq" style="" onchange="getJdqk();">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							<font color="red">*</font>类型：
						</td>
						<td align="left">
							<html:select name="rs" property="lx" style="" styleId="lx" onchange="chLx();getJdqk();">
								<html:options collection="lxList" property="en" labelProperty="cn" />
							</html:select>		
						</td>								
						<td align="right">
							<font color="red">*</font>
							<span id="jxjsp" style="">奖学金：</span>
							<span id="rychsp" style="display:none">荣誉称号：</span>
						</td>
						<td align="left">
							<span id="jxj" style="">
								<html:select name="rs" property="jxjdm" style="" styleId="jxjdm" onchange="getJdqk();">
									<html:options collection="jxjList" property="dm" labelProperty="mc" />
								</html:select>
							</span>
							<span id="rych" style="display:none">
								<html:select name="rs" property="rychdm" style="" styleId="rychdm" onchange="getJdqk();">
									<html:options collection="rychList" property="dm" labelProperty="mc" />
								</html:select>
							</span>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							不可兼得的奖学金：
						</td>
					</tr>	
					<logic:iterate name="jxjList" id="s" indexId="index">
						<%if((index.intValue()+1)%4==1){%>   
						<tr>	
						<%}%>
							<logic:equal name="index" value="0">
							<td>
								<font color="blue">请选择：</font>
							</td>
							</logic:equal>
							<logic:notEqual name="index" value="0">
							<td>
								<input type="hidden" id="jxjmc${index}" value="${s.mc }">
								<input type="checkbox" id="jxjdm${index}" name="jxjjd"
									value="${s.dm }" style="CURSOR: hand;" />&nbsp;${s.mc }
							</td>
 							</logic:notEqual>
 							<logic:notEmpty name="jxjtd">
 								<logic:equal name="index" value="${jxjnum }">
 								<%int jxjtd = Integer.parseInt(request.getAttribute("jxjtd").toString());%>
 								<%for(int i=0; i < jxjtd; i++){%>   
	 							<td></td>
	 							<%}%>
	 							</logic:equal>
 							</logic:notEmpty>
						<%if((index.intValue()+1)%4==0){%>   		
						</tr>
						<%}%>
					</logic:iterate>
					<tr>
						<td colspan="4" align="center">
							不可兼得的荣誉称号：
						</td>
					</tr>
					<logic:iterate name="rychList" id="s" indexId="index">
						<%if((index.intValue()+1)%4==1){%>   
						<tr>	
						<%}%>
							<logic:equal name="index" value="0">
							<td>
								<font color="blue">请选择：</font>
							</td>
							</logic:equal>
							<logic:notEqual name="index" value="0">
							<td>
								<input type="hidden" id="rychmc${index}" value="${s.mc }">
								<input type="checkbox" id="rychdm${index}" name="rychjd"
									value="${s.dm }" style="CURSOR: hand;" />&nbsp;${s.mc }
							</td>
 							</logic:notEqual>
 							<logic:notEmpty name="rychtd">
 								<logic:equal name="index" value="${rychnum }">
 								<%int rychtd = Integer.parseInt(request.getAttribute("rychtd").toString());%>
 								<%for(int i=0; i < rychtd; i++){%>   
	 							<td></td>
	 							<%}%>
	 							</logic:equal>
 							</logic:notEmpty>
						<%if((index.intValue()+1)%4==0){%>   		
						</tr>
						<%}%>
					</logic:iterate>
					<tr>
						<td colspan="4" align="center">
							<button class="button2" id="buttonSave" onclick="saveJd()"
								style="width: 80px">
								保	存
							</button>
							&nbsp;&nbsp;
							<button class="button2" id="buttonSave" onclick="qkJd()"
								style="width: 80px">
								清	空
							</button>
						</td>
					</tr>
			</table>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('操作成功！');
						dialogArgumentsQueryChick();
						window.close();
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('操作失败！');
					</script>
				</logic:equal>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
