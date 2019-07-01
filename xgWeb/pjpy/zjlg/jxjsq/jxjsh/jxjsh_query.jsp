<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/pjpy/pjpy_dwr.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getJxjRs.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getJxjRstg.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getCpzfp.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script language="javascript" src="js/comm/commFunction.js"></script>
	<script language="javascript">
/*
全部选中
*/    
  function chec(){
	  
     for(i=0;i<document.getElementsByName("pkV").length;i++){
  	    document.getElementsByName("pkV")[i].checked=document.getElementsByName("qbxz")[0].checked;
     }
  }		


	function viewMoreinfojxj(doType){
		var url ="/xgxt/zjlgPjpy.do?method=jxjCheck&act=view&pkValue=";
		var pkValue = "";
		var jxjcxzj = "";
		if(curr_row.getElementsByTagName("input")[0]){
			jxjcxzj = curr_row.getElementsByTagName("input")[1].value;
		}
		 if (jxjcxzj != ""){
		 pkValue = curr_row.getElementsByTagName("input")[0].value;
		 url += pkValue+"&jxjcxzj="+jxjcxzj;
		 url += "&xh="+curr_row.getElementsByTagName("input")[3].value;
		 showTopWin(url, 800, 650,false);
		 }else{
		
		 }
	}
function checkType(){
   var userType = $("userType").value;
   if(userType=="xx"||userType=="admin"){
      if($("checkpass")){ $("checkpass").value="学校审核通过";}
      if($("checknopass")){ $("checknopass").value="学校审核不通过";}
      if($("shyjLable")){ $("shyjLable").innerHTML="学校审核意见";}
   }else if(userType=="xy"){
      if($("checkpass")){ $("checkpass").value="<bean:message key="lable.xsgzyxpzxy" />审核通过";}
      if($("checknopass")){ $("checknopass").value="<bean:message key="lable.xsgzyxpzxy" />审核不通过";}
      if($("shyjLable")){ $("shyjLable").innerHTML="<bean:message key="lable.xsgzyxpzxy" />审核意见";}
   }
}
function batchCheck(str){
	var bmlb;
	var bmdm;
	
	var userType=$("userType").value;
	if(document.getElementById("isZds")){
	var isZds = document.getElementById("isZds").value;//审核方式
	}
	

	if(isZds=="xysh"){
		var xydm = document.getElementById("xydm").value;//审核方式
		if(xydm==""){
		alert("请选择<bean:message key="lable.xsgzyxpzxy" />！！！");
		return false;
		}
	}
	if(isZds=="cpzsh"){
		var cpzdm = document.getElementById("cpzdm").value;//审核方式
		if(cpzdm==""){
		alert("请选择参评组！！！");
		return false;
		}
	}
	if(isZds=="bjsh"){
		var bjdm = document.getElementById("bjdm").value;//审核方式
		if(bjdm==""){
		alert("请选择班级！！！");
		return false;
		}
	}
	
	if($("jxjdm") && $("jxjdm").value==""){
		alert("请选择需审核的奖学金!");
		return false;
	}
	
	
   var userType = $("userType").value;
   var url = "/xgxt/1.do?method=plCheck&check="+str; 
   $("check").value= str;
   var RowsStr="!!";		  
   var userType = $("userType").value;		   
   xyshDone = (str=="yes")?"通过":"不通过";
   var pkVArray = "'";
   for (i=0; i<document.getElementsByName("pkV").length; i++){
   	  if(document.getElementsByName("pkV")[i].checked){	    		 
   		 pkVArray+=document.getElementsByName("pkV")[i].value+"','"
   		 RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
   	  }	    	  
   }		   
   if (RowsStr=="!!"){
	   alert("请选择要操作的记录！\n(单击每条记录前复选框)");
	   return false;
   }
   document.forms[0].pkVStr.value = RowsStr;
   pkVArray=pkVArray.substring(0,pkVArray.length-2);
   if (confirm("确定要\""+xyshDone+"\"所选记录？")){
	 //showMessage('showDiv',true,'#C7DEFC');    
	 viewTempDiv('审核意见','showDiv','400','',null)      
   }
}

function qxsave(){
	hiddenMessage(true,true); 
}
function jxjShSave(){
    var shyj = $("shyj").value;
    var num = 0;
    var jxjnumszg = 0;
	var jxjnumstg = 0;
	var isZds = document.getElementById("isZds").value;//审核方式
	var xxxysbh = document.getElementById("xxxysbh").value;
    for (i=0; i<document.getElementsByName("pkV").length; i++){
  	  if(document.getElementsByName("pkV")[i].checked){	   
  	  	  var jxjsfsh = document.getElementsByName("jxjsfsh")[i].value; 
  	  	  var cklx = document.getElementById('shlx').value;
  	  	  if(cklx != 'btg'){
  	  	  if(jxjsfsh == "通过"){
				alert("请选择学校审核没有通过的学生！！");
				return false;
  	  	  }
  	  	  }		 
  		 num++;
  	  }	    	  
	   }
			
			if(document.getElementById("jxjdm")){
			var jxjdm = document.getElementById("jxjdm").value;
			}
			if(document.getElementById("xn")){
			var xn = document.getElementById("xn").value;
			}
			if(document.getElementById("cpzdm")){
			var cpzdm = document.getElementById("cpzdm").value;
			}
			if(document.getElementById("xydm")){
			var xydm = document.getElementById("xydm").value;
			}
			if(document.getElementById("bjdm")){
			var bjdm = document.getElementById("bjdm").value;
			}
			if(isZds=="xysh"){
				bmlb = "xydm";
				bmdm = xydm;
			}else if(isZds=="cpzsh"){
				bmlb = "cpzdm";
				bmdm = cpzdm;
			}else if(isZds=="bjsh"){
				bmlb = "bjdm";
				bmdm = bjdm;
			}
			
			var xydm = document.getElementById("xydm").value;
			    dwr.engine.setAsync(false);
			   	getJxjRs.getJxjrsxz(bmlb,bmdm,jxjdm,xn,xxxysbh,function jxjNum(data){
			   		if (data != null || typeof data == 'object') {
			   			jxjnumszg = data;
			   			//alert(jxjnumszg);
					}else{
						showMsgWin("有错误出现：远程数据读取失败！");
					}
					});
			   	dwr.engine.setAsync(true);
                dwr.engine.setAsync(false);
			   	getJxjRstg.getJxjrsxz1(bmlb,bmdm,jxjdm,xn,xxxysbh,function jxjNums(data1){
			   		if (data1 != null || typeof data1 == 'object') {
			   			jxjnumstg = data1;
			   			//alert(jxjnumstg);
					}else{
						showMsgWin("有错误出现：远程数据读取失败！");
					}
					});
			   	dwr.engine.setAsync(true);
				
			    //alert(jxjnumszg);
			   	//alert(jxjnumstg);
			   	 if(cklx != 'btg'){
			if(num>(jxjnumszg-jxjnumstg)){
				if((jxjnumszg-jxjnumstg)<=0){
					alert("该奖学金的人数上限为"+"【"+jxjnumszg+"】,还有【0】个人可以审核通过");
					}else{
					alert("该奖学金的人数上限为"+"【"+jxjnumszg+"】,还有【"+(jxjnumszg-jxjnumstg)+"】个人可以审核通过");
				}
				return false;
				}
			   	 }

    if(shyj.length>200){
        alert("审核意见字数过长，限200字内！");
        return false;
    }
    var url = "/xgxt/zjlgPjpy.do?method=jxjSqSh&go=go&shyj="+shyj; 
    refreshForm(url);
    $("kfbtnSave").disabled=true;
}
function isshfs(){
	var shfs = document.getElementById("isZds").value;
	if(shfs=="xysh"){
		document.getElementById("xydm").disabled = false;
		document.getElementById("cpzdm").disabled = true;
		document.getElementById("zydm").disabled = true;
		document.getElementById("bjdm").disabled = true;
	}else if(shfs=="cpzsh"){
		document.getElementById("xydm").disabled = false;
		document.getElementById("cpzdm").disabled = false;
		document.getElementById("zydm").disabled = true;
		document.getElementById("bjdm").disabled = true;
		var cpztt = document.getElementById("cpzdm").value;
		if(cpztt == null){
		initCpzList();
		}
	}else if(shfs=="zysh"){
		document.getElementById("xydm").disabled = false;
		document.getElementById("cpzdm").disabled = true;
		document.getElementById("zydm").disabled = false;
		document.getElementById("bjdm").disabled = true;
	}else if(shfs=="bjsh"){
		document.getElementById("xydm").disabled = false;
		document.getElementById("cpzdm").disabled = true;
		document.getElementById("zydm").disabled = false;
		document.getElementById("bjdm").disabled = false;
	}
}

function jxjshquery(){
	var jxjdm = document.getElementById("jxjdm").value;
	var nj = document.getElementById("nj").value;
	var cpzdm;
	var xydm;
	var bjdm;
	if(document.getElementById("cpzdm")){
		cpzdm = document.getElementById("cpzdm").value;
	}
	if(document.getElementById("xydm")){
		xydm = document.getElementById("xydm").value;
	}
	if(document.getElementById("bjdm")){
		bjdm = document.getElementById("bjdm").value;
	}
	var isZds = document.getElementById("isZds").value;//审核方式
	if(jxjdm==""){
	alert("请选择奖学金");
	return false;
	}
	//if(xn==""){
	//	alert("请选择学年");
	//	return false;
	//}

	if(isZds=="xysh"){
		if(xydm == ""){
			alert("您的审核方式是<bean:message key="lable.xsgzyxpzxy" />，请选择<bean:message key="lable.xsgzyxpzxy" />！！");
			return false;
		}
		if(nj==""){
			alert("您的审核方式是<bean:message key="lable.xsgzyxpzxy" />，请选择年级！！");
			return false;
		}
	}else if(isZds=="cpzsh"){
		if(cpzdm == ""){
			alert("您的审核方式是参评组，请选择参评组！！");
			return false;
		}
	}else if(isZds=="bjsh"){
		if(bjdm == ""){
			alert("您的审核方式是班级，请选择班级！！");
			return false;
		}
	}

		refreshForm('zjlgPjpy.do?method=jxjshQuery&go=go')
}
function checkshlx(type){
	if(type=='no'){
		document.getElementById('shlx').value='btg';
	}else{
		document.getElementById('shlx').value='';
	}
}

function checkback(){
     
      var RowsStr="!!";
      var pkVArray = "'";
		   for (i=0; i<document.getElementsByName("pkV").length; i++){
	    	  if(document.getElementsByName("pkV")[i].checked){	    		 
	    		 pkVArray+=document.getElementsByName("pkV")[i].value+"','"
	    		 RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
	    		 var jxjsfsh = document.getElementsByName("jxjsfsh")[i].value; 
	    		 if(jxjsfsh == "通过"){
				     alert("请选择学校审核没有通过的学生！！");
				  return false;
  	  	         }
	    	  }	    	  
		   }		   
		   if (RowsStr=="!!"){
			   alert("请选择要操作的记录！\n(单击每条记录前复选框)");
			   return false;
		   }
		   if (confirm("确定要退回所选记录？")){
			 //showMessage('showDiv',true,'#C7DEFC');    
			 viewTempDiv('退回理由','showDivBack','400','',null)      
		   }
		   document.forms[0].pkVStr.value = RowsStr;
		   pkVArray=pkVArray.substring(0,pkVArray.length-2);
		  
		   
}
function jxjBackSave(){
         var thly = $("thly").value;
         if(thly.length>200){
                alert("审核意见字数过长，限200字内！");
                return false;
          }
          var url = "/xgxt/zjlgPjpy.do?method=audit&go=go&workFlowName=jxjlc&tableName=xsjxjb&status=th&thly="+thly; 
          refreshForm(url);
          $("kfbtnSave1").disabled=true; 
}
function checkview(){
      var RowsStr="!!";
      var pkR = "'";
      var num=0;
		   for (i=0; i<document.getElementsByName("pkV").length; i++){
	    	  if(document.getElementsByName("pkV")[i].checked){	    		 
	    		 pkR=document.getElementsByName("pkV")[i].value;
	    		 num++;
	    	  }	    	  
		   }		   
		   if(num==0){
		      alert("请选择一条要操作的记录！\n(单击每条记录前复选框)");
		      return false;
		   }
		   if (num>1){
			   alert("请选择一条要操作的记录！\n(单击每条记录前复选框)");
			   return false;
		   }
		   var url = "/xgxt/zjlgPjpy.do?method=recordView&tableName=xsjxjb&pkR="+pkR; 
           showTopWin(url,750,550);
}

function goSearch(){
	refreshForm('zjlgPjpy.do?method=jxjshQuery&go=go');
}
</script>
</head>

<body onload="xyDisabled('xy');checkType();isshfs();">
	<html:form action="/zjlgPjpy" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>评奖评优 - 审核 - 奖学金审核</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>			
		<!-- 标题 end-->
		<!-- 提示信息 START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<font color="red"><bean:message key="lable.xb" />审核时，请先选择<bean:message key="lable.xb" />和需审核的奖学金项目，参评组审核时请选择参评组和需审核的奖学金项目，否则无法审核。</font><br/>
				<logic:notEmpty name="jxjSqrs" >
					<font color="blue"><bean:message key="lable.xb" />一共可以申请${jxjSqrs.xyjxjrs },已申请${jxjSqrs.xysqrs }人，还可申请${jxjSqrs.xyksqrs }人。
					<logic:notEmpty name="jxjSqrs" property="cpzjxjrs">
					<br/>
					参评组一共可以申请${jxjSqrs.cpzjxjrs },已申请${jxjSqrs.cpzsqrs }人，还可申请${jxjSqrs.cpzksqrs }人。<br/>
					</logic:notEmpty></font>
				</logic:notEmpty>
				
			</p>
			<a class="close" title="隐藏"
				onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		<logic:equal value="yes" name="writeAble">
			<div class="toolbox">
			<div class="buttonbox">	
				<ul>
					<li><a href="#" class="btn_shtg" id="checkpass" onclick="batchCheck('yes');checkshlx('yes');">通 过</a></li>
					<li><a href="#" class="btn_shbtg" id="checknopass" onclick="batchCheck('no');checkshlx('no');">不通过</a></li>
					<logic:equal value="xx" name="userType">
					<li><a href="#" class="btn_fh" id="checkback" onclick="checkback();">退回</a></li>
					</logic:equal>
					<li><a href="#" class="btn_sx" id="checkview" onclick="checkview();">流程追踪</a></li>
				</ul>
			</div>
			</div>
		</logic:equal>
		<input type="hidden" id="zyV" name="zyV" value="" />
		<input type="hidden" id="bjV" name="bjV" value="" />
		<input type="hidden" id="pt" name="pt" />
		<input type="hidden" id="shlx" name="xhlx" />
		<input type="hidden" id="userType" name="userType" value="${userType }" />
		<input type="hidden" id="xxxysbh" name="xxxysbh" value="<bean:write name="userType" scope="session"/>" />
		<input type="hidden" id="tableName" name="tableName" value="" />
		<input type="hidden" id="realTable" name="realTable" value="xsjxjb" />
		<input type="hidden" style="" id="failbg" name="failbg" value="${errorStr }" />
		<!-- 批量信息主键 -->
		<input type="hidden" name="pkVStr" value="" />
		<input type="hidden" name="check" value="" />
		<div id="showDiv" style="display: none" align="center">
			<fieldset style="width: 100%; height: 100%">
				<legend>
					审核意见填写<font color="red">(200字内)</font>
				</legend>
				<table class='formlist'>
					<tr>
						<td align='left'>
							<html:textarea property="shyj" rows="12" cols="50"></html:textarea>
						</td>
					</tr>
					<tfoot>
						<tr>
							<td colspan='2'>
								<button type="button" class='button2' id="kfbtnSave" onclick='jxjShSave()'>
									提交
								</button>
								<button type="button" class='button2' id="qxsave" onclick='hiddenMessage(true,true);refreshForm("zjlgPjpy.do?method=jxjshQuery&go=go")'>
									关闭
								</button>
							</td>
						</tr>
					</tfoot>
				</table>
			</fieldset>
		</div>
      <div id="showDivBack" style="display: none" align="center">
			<fieldset style="width: 100%; height: 100%">
				<legend>
					退回理由填写<font color="red">(200字内)</font>
				</legend>
				<table class='formlist'>
					<tr>
						<td align='left'>
							<html:textarea property="thly" rows="12" cols="50"></html:textarea>
						</td>
					</tr>
					<tfoot>
						<tr>
							<td colspan='2'>
								<button type="button" class='button2' id="kfbtnSave1" onclick='jxjBackSave()'>
									提交
								</button>
								<button type="button" class='button2' id="qxsave1" onclick='hiddenMessage(true,true);refreshForm("zjlgPjpy.do?method=jxjshQuery&go=go")'>
									关闭
								</button>
							</td>
						</tr>
					</tfoot>
				</table>
			</fieldset>
		</div>
		<div id="showDivView" style="display: none" align="center">
			<fieldset style="width: 100%; height: 100%">
				<legend>
					流程追踪
				</legend>
				<logic:notEmpty name="rs1">
				<table width="100%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" >
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs1" id="s">
							<tr onclick="rowOnClick(this);" style="cursor:hand;">
								<logic:iterate id="v" name="s">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					<tfoot>
						<tr>
							<td colspan='2'>
								<button type="button" class='button2' id="qxsave1" onclick='hiddenMessage(true,true);refreshForm("zjlgPjpy.do?method=jxjshQuery&go=go")'>
									关闭
								</button>
							</td>
						</tr>
					</tfoot>
				</table>
				</logic:notEmpty>
			</fieldset>
		</div>
		<div class="searchtab">		
			<table width="100%" class="tbstyle">
				<tbody>
					<tr>
						<th>年级</th>
						<td><html:select property="nj" styleId="nj" styleClass="select"
								style="width:90px">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select></td>
						<th>学年</th>
						<td><input name="xndt" value="<bean:write name="xnval"/>"
								disabled="disabled" />
							<html:hidden property="xn"></html:hidden>
						</td>
						<th>奖学金</th>
						<td><html:select property="jxjdm" styleClass="select" styleId="jxjdm"
								onchange="goSearch()">
								<html:option value=""></html:option>
								<html:options collection="jzxjxmList" property="jxjdm"
									labelProperty="jxjmc" />
							</html:select></td>
						</tr>
						<tr>
							<th>学号</th>
							<td><html:text property="xh" styleId="xh"></html:text>
							</td>
							<th>姓名</th>
							<td><html:text property="xm" styleId="xm"></html:text></td>
							<th>审核方式</th>
							<td><html:select property="isZds" onchange="isshfs();goSearch()">
								<html:option value="xysh"><bean:message key="lable.xsgzyxpzxy" /></html:option>
								<html:option value="cpzsh">参评组</html:option>
								<html:option value="bjsh">班级</html:option>
							</html:select></td>
						</tr>
						<tr>
							<th>审核状态</th>
							<td><html:select property="yesNo" onchange="">
								<html:option value=""></html:option>
								<html:option value="未审核">未审核</html:option>
								<html:option value="通过">通过</html:option>
								<html:option value="不通过">不通过</html:option>
							</html:select>
							</td>
							<th>参评组</th>
							<td><html:select property="cpzdm"
								onchange="initZyList();initBjList();goSearch()" styleClass="select"
								style="width:150px" styleId="cpz">
								<html:option value=""></html:option>
								<html:options collection="cpzList" property="cpzdm"
									labelProperty="cpzmc" />
							</html:select>
							<input type="hidden" name="cpzV" value="" /></td>
							<th></th>
							<td></td>
					</tr>
					<tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td><logic:equal name="userType" value="xy">
							<input id="shbmmcfg" name="shbmmcfg" value="${shbmmc}" disabled="disabled"/>
							<html:select property="xydm"
								onchange="initZyList();initBjList();initCpzList();"
								styleClass="select" style="width:180px;display: none" styleId="xy">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
							</logic:equal>
							<logic:notEqual name="userType" value="xy">
								<html:select property="xydm"
								onchange="initZyList();initBjList();initCpzList();goSearch()"
								styleClass="select" style="width:150px" styleId="xy">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
							</logic:notEqual></td>
							<th>专业</th>
							<td><html:select property="zydm" onchange="initBjList()"
								style="width:150px" styleClass="select" styleId="zy"
								disabled="true">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select></td>
							<th>班级</th>
							<td><html:select property="bjdm" style="width:180px"
								styleClass="select" styleId="bj">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
							</td>
						</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="6">
						<div class="btn">
							<input type="hidden" name="act" value="qry" />
							<button type="button" id="search_go" onclick="refreshForm('zjlgPjpy.do?method=jxjshQuery&go=go')">
								查 询
							</button>
						 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
							重 置
						 </button>
						</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		
		<div class="formbox">
			<logic:empty name="rs">
			    <h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
						<font color="red">未找到任何记录！</font> 
			    </span>
			    </h3>
			 </logic:empty>
			<logic:notEmpty name="rs">
				<h3 class="datetitle_01">
					<span>
						查询结果&nbsp;&nbsp;记录数：
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%--							<font color="blue">提示：该奖学金一共可以申请<bean:write name="jxjszrs"/>人，--%>
<%--													已经申请<bean:write name="jxjysqrs"/>人，--%>
<%--													还可申请<bean:write name="jxjhxsyrs"/>人</font>--%>
					
					</span>
				</h3>
				<table width="100%" id="rsTable" class="dateline">
							<thead>
								<tr align="center" style="cursor: hand">
									<td>
										<input type="checkbox" name="qbxz" value="all"
											onclick="chec('qbxz')" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="1" length="12">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap="nowrap">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor: hand"
									ondblclick="viewMoreinfojxj('view')">

									<td align="center">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="checkbox" name="pkV" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="hidden" id="jxjcxzj"
												value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="8" length="1">
											<input type="hidden" id="jxjsfsh" name="jxjsfsh"
												value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="2" length="1">
											<input type="hidden" id="rtr" name="reter"
												value="<bean:write name="v"/>" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2" length="12">
										<td align="center">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
				</logic:notEmpty>
				
				<div id="tmpdiv"></div>
			</div>
	</html:form>
	<script type="text/javascript" src="js/bottomButton.js"></script>
	<logic:present name="deleted">
		<logic:equal value="yes" name="deleted">
			<script>
	alert('操作成功！');
	document.getElementById('search_go').onclick();
</script>
		</logic:equal>
		<logic:equal value="no" name="deleted">
			<script>
	var tt = document.getElementById('failInfo').value();
	alert(tt);
	document.getElementById('search_go').onclick();
</script>
		</logic:equal>
	</logic:present>
	<logic:notEmpty name="errorStr">
		<script>
	var tt = document.getElementById('failbg').value;
	var gnmk = tt.replace(/\;/g, ";\n");
	alert(gnmk);
	//document.getElementById('search_go').onclick();
</script>
	</logic:notEmpty>

<logic:equal name="zycjbf" value="jxjjd1">
			<script>
			        alert("励志单项奖学金和优秀学生奖学金不能兼得");			    
			        </script>
			</logic:equal>
			
			<logic:equal name="zycjbf" value="jxjjd2">
			<script>
			        alert("优秀学生奖学金不能兼得");			    
			        </script>
			</logic:equal>
</body>
</html>
