<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">	
	function allFilleds(val) {
		var arrayList = val.split('-');

		var cjlx = GetRadioValue("cjlx");
		if(cjlx == "jxj"){
			var jxjdm = document.getElementById("jxjdm").value
			if(jxjdm==''){
				alert("奖学金不得为空!");
				return false;
			}
		}else if(cjlx == "rych"){
			var rychdm = document.getElementById("rychdm").value
			if(rychdm==''){
				alert("荣誉称号不得为空!");
				return false;
			}
		}
		var zdmlx = document.getElementById(arrayList[2]).value
		if(document.getElementById(arrayList[0]).value==''){
			alert("学年不得为空!");
			return false;
		}
		//if($("szlx").value !="xjbj"){
		//	if(document.getElementById(arrayList[5]).value==''){
		//		alert("条件对象不得为空!");
		//		return false;
		//	}
		//}
		if(document.getElementById(arrayList[2]).value==''){
			alert("条件不得为空!");
			return false;
		}
		if(document.getElementById(arrayList[3]).value==''){
			alert("条件不得为空!");
			return false;
		}
		if(document.getElementById(arrayList[4]).value==''){
			alert("条件不得为空!");
			return false;
		}
		
	 var val =  $("val");
	 if(document.getElementById(arrayList[2]).value!="cf" && document.getElementById(arrayList[2]).value!="bjgkm"){
		 var valV =val.value.replace(/[^(\d|\.)]/g,'');
		 if(!valV.match(/\d+/g)){		
			alert("条件值须为数字类型的数据！");
			$("val").focus()
			return false;
		 }
	 }
	 
	 if($("zdm").value=="zhpm"||$("zdm").value=="dypm"||$("zdm").value=="zypm"||$("zdm").value=="typm"){
	     if(val.value.indexOf(".")!=-1){
	       alert("输入的条件值有误，不能为小数！");
	       return false;
	     }
	 }
	 var zdmc = document.forms[0].zdm.options[document.forms[0].zdm.selectedIndex].text;
     refreshForm("/xgxt/jhzy_pjpysz.do?method=tjsz&type=save");
     if($("buttonSave")){
        $("buttonSave").disabled=true;
     }
     if($("tzxn")){
         $("tzxn").disabled=true;
      }
          if($("search_go")){
        $("search_go").disabled=true;
     }
          if($("buttonDel")){
        $("buttonDel").disabled=true;
     }
	}
	
	function searchTj(){
		var xn = $("xn").value;
		var szlx = GetRadioValue("cjlx");
		if(xn == ""){
			alert("学年不能为空，请确认！！");
			return false;
		}
		if(szlx == "jxj"){
			if($("jxjdm").value == ""){
				alert("奖学金名称不能为空，请确认！！");
				return false;
			}
		}
		if(szlx == "rych"){
			if($("rychdm").value == ""){
				alert("荣誉称号名称不能为空，请确认！！");
				return false;
			}
		}

	  allNotEmpThenGo('/xgxt/jhzy_pjpysz.do?method=tjsz&go=go&szlx='+szlx);
	  if($("buttonSave")){
        $("buttonSave").disabled=true;
      }
          if($("search_go")){
        $("search_go").disabled=true;
      }
          if($("buttonDel")){
        $("buttonDel").disabled=true;
      }		
	}
	
	function delTj(){
		if (curr_row == null) {
			alert("请选择要删除的条件！");
			return false;
		}
	  if(confirm("确定要删除所选记录！")){
	     var pk = curr_row.cells[0].getElementsByTagName('input')[0].value;
	     refreshForm("/xgxt/jhzy_pjpysz.do?method=tjsz&type=del&pk="+pk);
	     if($("buttonSave")){
            $("buttonSave").disabled=true;
         }
         if($("search_go")){
            $("search_go").disabled=true;
         }
         if($("buttonDel")){
            $("buttonDel").disabled=true;
         }			     
	  }
	}
	
	function chZdm(value){
		var zdmval = $("zdm").options[document.getElementById('zdm').selectedIndex].innerText;
		$("zdmval").value=zdmval;
	}
	function tjShow(){
		var value = $("zdm").value;
		if(value == "sfyxbj"){
			$("tj").value="=";
			$("tj").disabled=true;
			$("val").style.display="none";
			$("sfyxbj").style.display = "";
		}else{
			$("tj").value="";
			$("tj").disabled=false;
			$("val").style.display="";
			$("sfyxbj").style.display = "none";
			$("sfyxbj").value = "";
		}
	}
	function tjszShow(){
		var value = GetRadioValue("cjlx");
		if(value == "rych"){
			$("rychdiv").style.display="";
			$("jxjdiv").style.display = "none";
			$("xqdiv").style.display="none";
		}else{
			$("rychdiv").style.display="none";
			$("jxjdiv").style.display = "";
			$("xqdiv").style.display="";
		}
	}
	function GetRadioValue(RadioName){
	    var obj;   
	    obj=document.getElementsByName(RadioName);
	    if(obj!=null){
	        var i;
	        for(i=0;i<obj.length;i++){
	            if(obj[i].checked){
	                return obj[i].value;           
	            }
	        }
	    }
	    return null;
	}
	function szlxtj(){
		var szlx = GetRadioValue("cjlx");
		if(szlx == null){
			$("cjlx").checked=true;
		}
		if(szlx=="jxj"){
			$("jxjdiv").style.display="";
			$("xqdiv").style.display="inline";
		}else{
			$("rychdiv").style.display="";
			$("xqdiv").style.display="none";
		}
	}
	function changetj(){
		var zdmlx = document.getElementById("zdm").value;
		var val = document.getElementById("val").value;
		if(zdmlx=="cf" || zdmlx=="bjgkm"){
			 document.getElementById("tj").value = "=";
			 document.getElementById("val").value = "是";
			 document.getElementById("tj").disabled = "disabled";
			 document.getElementById("val").disabled = "disabled";
		 }else{
			 if(!isNumber(val)){
				 document.getElementById("tj").value = "";
				 document.getElementById("val").value = "";
				 document.getElementById("tj").disabled = "";
				 document.getElementById("val").disabled = "";
			 }
		 }
	}
	 function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		    } 
	</script>
	<body onload="szlxtj();changetj();">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/pjpy/pjpy_zjlg.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getCpzfp.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/jhzy_pjpysz.do?method=tjsz" method="post">
			<input type="hidden" name="szlx" id="szlx" value="<bean:write name="szlx"/>"/>
			<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="fdyQx" scope="session"/>" />
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>"/>
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：<bean:write name="title" />
				</div>
			</div>
			<div class="rightcontent">
				<fieldset>
					<legend>
						查 询
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>   
								<td colspan="2">
										<html:radio property="cjlx" value="rych" onclick="tjszShow();" ></html:radio>
										荣誉称号
									&nbsp; 
										<html:radio property="cjlx" value="jxj" onclick="tjszShow();"></html:radio>
											奖学金
								</td>
							</tr>
							<tr>
								<td align="left">
									学年：
									<html:select property="xn" styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
<%--									年度：--%>
									<html:select property="nd" style="width:90px;display: none" styleId="nd">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
									<span id="xqdiv">
									学期：
									<html:select property="xq" styleId="xq">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>	
									</span>	
									<span  style="display: none" id="rychdiv">
									荣誉称号：
									<html:select property="rychdm" styleId="rychdm">
										<html:option value=""></html:option>
										<html:options collection="rychList" property="dm"
												labelProperty="mc" />
									</html:select>
									</span>
									<span style="display: none" id="jxjdiv">
									奖学金：
									<html:select property="jxjdm" styleId="jxjdm">
										<html:option value=""></html:option>
										<html:options collection="jxjList" property="jxjdm"
												labelProperty="jxjmc" />
									</html:select>
									</span>
								</td>
							</tr>
							<tr>
								<td align="left" nowrap>
									条件：
									<html:select property="zdm" styleId="zdm" onchange="chZdm(this.value);changetj();">
										<html:option value=""></html:option>
										<html:options collection="zdList" property="zdmc"
												labelProperty="zdsm" />
									</html:select>
									<html:hidden property="zdmval"></html:hidden>
									<html:select property="tj" styleId="tj" style="width:120px">
											<html:option value=""></html:option>
											<html:option value="&gt;=">
												大于或等于
											</html:option>
											<html:option value="=">
												等于
											</html:option>
											<html:option value="&gt;">
												大于
											</html:option>
											<html:option value="&lt;">
												小于
											</html:option>
											<html:option value="&lt;=">
												小于或等于
											</html:option>
									</html:select>
									<html:text property="val" style="width:120px" onkeypress='return sztzNumInputValue(this,6,event)' />
									<html:select property="sfyxbj" styleId="sfyxbj" style="width:120px;display:none">
										<html:option value=""></html:option>
										<html:option value="是">是</html:option>
										<html:option value="否">否</html:option>
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="hidden" name="go" value="a" />
									<logic:equal name="writeAble" value="yes">			
										<button type="button" class="button2" id="buttonSave"
											onclick="allFilleds('xn-xq-zdm-tj-val-sfyxbj')">
											添加条件
										</button>
										&nbsp;&nbsp;
									</logic:equal>
									<button type="button" class="button2" id="search_go"
										onclick="searchTj();">
										查询
									</button>
								</td>
							</tr>
						</thead>
					</table>
				</fieldset>
				<logic:empty name="rs">
					<p align="center">
						未找到任何记录！
					</p>
				</logic:empty>
				<logic:notEmpty name="rs">
					<fieldset>
						<legend>
							
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">					
									<td onclick="tableSort(this)" nowrap>
										学年
									</td>
									<logic:notEqual name="szlx" value="rych">
									<td onclick="tableSort(this)" nowrap>
										学期
									</td>
									</logic:notEqual>
									<td onclick="tableSort(this)" nowrap>
										设置类型
									</td>
									<td onclick="tableSort(this)" nowrap>
										条件名称
									</td>
									<td onclick="tableSort(this)" nowrap>
										条件
									</td>
									<td onclick="tableSort(this)" nowrap>
										条件值
									</td>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowOnClick(this);" style="cursor:hand">
									<td align="left">
										<input type="hidden" name="pk" value="<bean:write name="s" property="pk"/>" />
										<bean:write name="s" property="xn" />
									</td>
									<logic:notEqual name="szlx" value="rych">
									<td align="center">
										<bean:write name="s" property="xq" />
									</td>
									</logic:notEqual>
									<td align="center">
										<bean:write name="s" property="jxjdm" />
									</td>
									<td align="center">
										<bean:write name="s" property="tjmc" />
									</td>
									<td align="center">
										<bean:write name="s" property="tjlx" />
									</td>
									<td align="center">
										<bean:write name="s" property="tjz" />
									</td>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
							<logic:equal name="writeAble" value="yes">							
								<button type="button" class="button2" id="buttonDel" onclick="delTj()" >
									删除条件
								</button>
								&nbsp;&nbsp;
								<button type="button" class="button2" id="tzxn"
								onclick="showTopWin('chg_prise_xn.do',300,200)"
								style="width:100px">
								调整学年
							</button>
							</logic:equal>					
					</div>
				</center>
				<div id="tmpdiv"></div>
			</div>
		</html:form>
		<logic:equal value="yes" name="result">
			<script>
				//alert("操作成功!");
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("操作失败");
			</script>
		</logic:equal>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
