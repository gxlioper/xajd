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
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/checkXsInfo.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript">
		function cffs(fs){
			document.getElementById("fs").value=fs;
		}
		function disabtext(){
			var dotype = document.getElementById("doType").value;
			if(dotype == "update"){
				document.getElementById("buttonFindStu").style.display = "none";
				document.getElementById("xh").disabled = true;
				document.getElementById("xm").disabled = true;
				document.getElementById("xb").disabled = true;
				document.getElementById("xn").disabled = true;
				document.getElementById("xqmc").disabled = true;
				document.getElementById("nj").disabled = true;
				document.getElementById("xymc").disabled = true;
				document.getElementById("zymc").disabled = true;
				document.getElementById("bjmc").disabled = true;
				document.getElementById("cwh").disabled = true;
				document.getElementById("ldmc").disabled = true;
				document.getElementById("qsh").disabled = true;
				document.getElementById("cwh").disabled = true;
				//document.getElementById("wjsj").disabled = true;
			}
		}
	</script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
<%--	<body onload="checkWinType();dataManLoad();">--%>
	<body onload="disabtext();">		
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：公寓管理 - 住宿纪律管理 - 录入
				</div>
			</div>
			<logic:notEmpty name="rs">
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pk" name="pk"
					value="${rs.pk}" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-nj-xymc-zymc-bjmc" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-nj-xymc-zymc-bjmc" />
		        <input type="hidden" id="url" name="url" value="/zjcmxy_Gygl.do?method=zsjlAdd" />		        			
				<input type="hidden" id="userType" name="userType"
					value="<bean:write name="userType" scope="session"/>" />
<%--				<input type="hidden" id="xxdm" name="xxdm"--%>
<%--					value="<bean:write name="xxdm" scope="request"/>" />--%>
				<html:hidden name='rs' property="xsxx" styleId="xsxx" />
				<input type="hidden" id="ssbh" name="ssbh"
					value="${rs.ssbh}" />
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="4" align="center">
								住宿纪律录入
							</td>
						</tr>
					</thead>					
					<tr>
						<td align="right">
							<font color="red">*</font>学号：
						</td>
						<td align="left">
							<html:text name='rs' property="xh" readonly="readonly" onblur="dctStuXh()"
								styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button onclick="showTopWin('/xgxt/stu_LdQsInfo.do',750,550);"
								class="btn_01" id="buttonFindStu" style="display:" >
								选择
							</button>
						</td>
						<td align="right">
							姓名:
						</td>
						<td align="left">
							<html:text name='rs' property="xm" styleId="xm" />
						</td>						
					</tr>					
					<tr>
						<td align="right">
							性别：
						</td>
						<td align="left">
							<html:text name='rs' property="xb" styleId="xb" />
						</td>
						<td align="right">
							<font color="red">*</font>学年：
						</td>
						<td align="left">
							<logic:notEqual value="add" name="doType">
								<html:text name="rs" property="xn" value="${rs.xn}" readonly="true"></html:text>
							</logic:notEqual>
							<logic:equal value="add" name="doType">
								<html:select name="rs" property="xn" style="width:90px" styleId="xn" >
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</logic:equal>
						</td>											
					</tr>
					<tr>
						<td align="right">
							年级：
						</td>
						<td align="left">
							<html:text name='rs' property="nj" styleId="nj" />
						</td>
						<td align="right">
							<font color="red">*</font>学期：
						</td>
						<td align="left">
							<logic:notEqual value="add" name="doType">
								<html:hidden name="rs" property="xq" value="${rs.xq}"/>
								<input type="text" id="xqmc" value="${rs.xqmc}" readonly="true"/>
							</logic:notEqual>							
							<logic:equal value="add" name="doType">
								<html:select name="rs" property="xq" style="width:90px" styleId="xq">
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
							</logic:equal>
						</td>				
					</tr>					
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							<html:text name='rs' property="xymc" styleId="xymc" />
						</td>
						<td align="right">
							<font color="red">*</font>楼栋名称：
						</td>
						<td align="left">
						   <html:hidden name="rs" property="lddm" value="${rs.lddm}"></html:hidden>
							<logic:notEqual value="add" name="doType">
								<html:text name='rs' property="ldmc" styleId="ldmc" readonly="true" />									
							</logic:notEqual>
							<logic:equal value="add" name="doType">	
							<html:text name='rs' property="ldmc" styleId="ldmc" readonly="true" />													    
<%--								<html:select name="rs" property="lddm" style="width:150px" styleId="lddm" onchange="GetQshList()">--%>
<%--									<html:option value=""></html:option>--%>
<%--									<html:options collection="ldList" property="lddm" labelProperty="ldmc" />--%>
<%--								</html:select>--%>
							</logic:equal>
						</td>				
					</tr>
					<tr>
						<td align="right">
							专业：
						</td>
						<td align="left">
							<html:text name='rs' property="zymc" styleId="zymc" />
						</td>
						<td align="right">
							<font color="red">*</font>寝室号：
						</td>
						<td align="left">
							<logic:notEqual value="add" name="doType">
								<html:text name="rs" property="qsh" styleId="qsh" value="${rs.qsh}" readonly="true"></html:text>
							</logic:notEqual>
							<logic:equal value="add" name="doType">
							<html:text name="rs" property="qsh" styleId="qsh"  readonly="true"/>													
<%--								<input type="hidden" name="qshV" value=""/>--%>
<%--								<input type="hidden" name="rs" property="qsh"/>--%>
<%--								<html:select name="rs" property="qsh" style="width:110px">--%>
<%--									<html:option value=""></html:option>--%>
<%--									<html:options collection="ssList" property="qsh"--%>
<%--										labelProperty="qsh" />--%>
<%--								</html:select>--%>
							</logic:equal>
						</td>			
					</tr>
					<tr>
						<td align="right">
							班级：
						</td>
						<td align="left">
							<html:text name='rs' property="bjmc" styleId="bjmc" />
						</td>
						<td align="right" nowrap="nowrap">
							<font color="red">*</font>(发生)时间：									
						</td>
						<td align="left">
<%--							<logic:notEqual value="add" name="doType">--%>
<%--								<html:text name="rs" property="wjsj" value="${rs.wjsj}" readonly="true"></html:text>--%>
<%--							</logic:notEqual>							--%>
<%--							<logic:equal value="add" name="doType">--%>
								<html:text name="rs" property="wjsj" readonly="true" onblur="dateFormatChg(this)" 
									onclick="return showCalendar('wjsj','y-mm-dd');" style="cursor:hand "/>
<%--							</logic:equal>		--%>
						</td>
					</tr>
						<tr>
							<td align="right">
								床号：
							</td>
							<td align="left">
								<html:text name="rs" property="cwh" styleId="c" readonly="true"/>
							</td>
							<td align="right">
							</td>
							<td align="left">
							</td>
						</tr>
						<tr>
							<td align="right">
									<font color="red">*</font>纪律类别：
							</td>
							<td align="left"  colspan="3">
								<html:select name="rs" property="wjlbdm" style="width:"
									styleId="wjlb">
									<html:options collection="wjlbList" property="wjlbdm"
										labelProperty="wjlbmc" />
								</html:select>
								&nbsp;&nbsp;
								<!--<logic:notEqual name="xxdm" value="11641">
								<input type="checkbox" name="saveType" value="more" title="多个操作" onclick="showXsxx()"/>
							    寝室全体成员&nbsp;&nbsp;&nbsp;&nbsp;
							    <span><font color="blue">选中即为该寝室全体成员添加所输入纪律信息</font></span>						    
							   	</logic:notEqual>-->
							   	<logic:equal name="doType" value="add">
								<input type="checkbox" name="saveType" value="more" title="多个操作" onclick="showXsxx()"/>
							    寝室成员
							    </logic:equal>
							</td>							
						</tr>
					<logic:equal name="doType" value="add">
					<tr id="rsxsxx" style="">
						<td colspan="4">
						    <fieldset>
									<legend>
									    <span id="ldqs"></span>同寝室人数：共<span id="rsNum"></span>人
									</legend>
							<table width="100%" class="tbstyle">
								
								<tbody id="rsTables">
								  
								</tbody>
								
							</table>
							</fieldset>
						</td>
					</tr>
					</logic:equal>
					<tr>
						<td align="right" nowrap="nowrap">
							<font color="red">*</font>违纪说明：							
							<br>
							<span style="color: red">
							<限500字>	</span>										
						</td>
						<td align="left" colspan="3">
							<textarea rows="5" cols="78" name="wjsy" id="wjsy" type="_moz">${rs.wjsy}</textarea>			
						</td>					
					</tr>
<%--					<tr>--%>
<%--						<td align="right">--%>
<%--							处理结果：							--%>
<%--							<br>--%>
<%--							<span style="color: red">--%>
<%--							<限100字>	</span>										--%>
<%--						</td>--%>
<%--						<td align="left" colspan="3">--%>
<%--							<textarea rows="5" cols="78" name="cljg" id="cljg" type="_moz">${rs.cljg}</textarea>			--%>
<%--						</td>					--%>
<%--					</tr>--%>
<%--					<tr>--%>
<%--						<td align="right">--%>
<%--							备注：							--%>
<%--							<br>--%>
<%--							<span style="color: red">--%>
<%--							<限100字>	</span>										--%>
<%--						</td>--%>
<%--						<td align="left" colspan="3">--%>
<%--							<textarea rows="5" cols="78" name="bz" id="bz" type="_moz">${rs.bz}</textarea>			--%>
<%--						</td>					--%>
<%--					</tr>--%>
				</table>
				<div class="buttontool" align="center">
<%--					<button class="button2" onclick="dataCanModi(true)" style="width:80px" id="buttonModi">--%>
<%--						修 改--%>
<%--					</button>--%>
					<button class="button2" onclick="isexistschk();" style="width:80px" id="buttonSave">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="Close();return false;" style="width:80px" id="buttonClose">
						关 闭
					</button>
				</div>
			</logic:notEmpty>		
		</html:form>
		<logic:equal value="ok" name="done">
		<script language="javascript">
		alert("操作成功！");
		Close();
		window.dialogArguments.document.getElementById('search_go').click();   
		</script>
		</logic:equal>
		<logic:equal value="no" name="done">
		<script language="javascript" >
		  alert("操作失败！");
		Close();
		window.dialogArguments.document.getElementById('search_go').click();   
		</script>
		</logic:equal>
  </body>
	<logic:equal value="notIn" name="message">
		<script>
			alert("该学生目前尚未住宿！");
		</script>
	</logic:equal>
	<script type="text/javascript">	  
		  function isexistschk(){
			  var xsxx = document.getElementById("xsxx").value;
		        var xh = document.getElementById("xh").value;
		        var wjsj = document.getElementById("wjsj").value;
		        var wjlbdm = document.getElementById("wjlbdm").value;
		        dwr.engine.setAsync(true);
				checkXsInfo.checkXsInfo(xh,xsxx,wjsj,wjlbdm,"",function initTjList(data){
					       if (data != "") {
								alert(data);
								return ;
							}else{
							//	showMsgWin("有错误出现：远程数据读取失败！");
								zsjlSave('xh-xn-xq-lddm-qsh-fs-wjsj-wjlbdm-wjsy');
							}	
					    });
				   dwr.engine.setAsync(true);
			  }    
	      function zsjlSave(mustFill){ 
  	        var cljg = "";
	        var bz   = "";
	        var wjsy = "";	        
	        if($("bz")){
	           bz = $("bz").value;
	        }	  
	        if($("cljg")){
	           cljg = $("cljg").value;
	        }	
	        if($("wjsy")){
	           wjsy = $("wjsy").value;
	        }	
	        if(wjsy.length>500){
	            alert("事由字数过大，限500字内！");
	            return false;
	        }	      
	        if(cljg.length>100){
	            alert("处理结果字数过大，限100字内！");
	            return false;
	        }	      
	        if(bz.length>100){
	            alert("备注字数过大，限100字内！");
	            return false;
	        }	      
	           var eles = mustFill.split("-");
	           for (i = 0; i < eles.length; i++) {
	               if($(eles[i])!=null||$(eles[i])){
		             if ($(eles[i]).value == "") {
			          alert("请将带\"*\"号的项目输入完整！");
			          return false;
		             }
		           }
	           }
	           if($("doType").value == "add"){	         
	           var saveType = document.getElementById("saveType").checked;         
		           if(saveType){
		               if(confirm("确定向选择该寝室成员添加所输入纪律信息?")){
		            	   dataDoSavezjcm(mustFill);
		               }else{
		                  return false;
		               }  
		           }else{
		        	   dataDoSavezjcm(mustFill);
		           }
	           }else{
	        	   dataDoSavezjcm(mustFill);
	           }
	      }
	      function dataDoSavezjcm(mustFill) {
		      var doType = document.getElementById("doType").value;
		      var xh = document.getElementById("xh").value;
	    		var eles = mustFill.split("-");
	    		for (i = 0; i < eles.length; i++) {
	    		    if($(eles[i])!=null||$(eles[i])){
	    			    if ($(eles[i]).value == "") {
	    				  alert("请将带\"*\"号的项目输入完整！");
	    				  return false;
	    			   }
	    			}
	    		}
	    		var url = "/xgxt/zjcmxy_Gygl.do?method=zsjlAdd&xh="+xh;
	    		url += "&act=save&doType"+doType;
	    		document.forms[0].action = url;
	    		document.forms[0].submit();
	    		var btnList = document.getElementsByTagName("button");
	    		for (i = 0; i < btnList.length; i++) {
	    			btnList[i].disabled = true;
	    		}
	    		dialogArgumentsQueryChick();
	    	}
	      function showXsxx(){
	           if(document.getElementById("saveType").checked){
	               document.getElementById("rsxsxx").style.display="";
	              getRzXsXx1();
	           }else{
	               document.getElementById("rsxsxx").style.display="none";
	           }
	      }
	function getRzXsXx1(){
	var ssbh = "";
	var xh = $("xh").value;
	if($("ssbh")){
	  ssbh = $("ssbh").value;
	}else{
	  ssbh = $("lddm").value+"-"+$("qsh").value;
	}
	GetListData.GetQsRzXsXxHH(ssbh,xh,TjRzSsXsXx1);
	}
	
	function cheXs(xsxx){
		alert(xsss);
	}
function TjRzSsXsXx1(data){
	var xx="";
	var cellfu =[function(data) {return data;}];
	if (data != null && typeof data == 'object') {
		if ($("rsTables").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("rsTables");		
			//DWRUtil.addRows("rsTables",data,cellfu,{escapeHtml:false});	
			for(var i=0;i<data.length;i++){
			var xsxx=data[i].replace("&nbsp;","").replace("&nbsp;","").replace("&nbsp;","").replace("&nbsp;","").replace("&nbsp;","").replace("&nbsp;","");
			var che = document.createElement("input");
				che.type="checkbox";
				che.id = "che" + i;
				che.name="che" + i;
				che.value = xsxx;
			DWRUtil.addRows('rsTables',['dd'],[che,xsxx]);	
			$(che.id).onclick=function(){
				//xx = xx.replace(this.value,"") + this.value + splitSignOne;
				//if(!document.getElementById(this.id).checked){
				var rsinput = document.getElementById("rsTables").getElementsByTagName("INPUT");
				xx = "";
					for(var j=0;j<rsinput.length;j++){
						//alert("=="+rsinput[j].checked);
						if(rsinput[j].checked){
							xx += (rsinput[j].value + splitSignOne);
						}
					}
				//}
				document.getElementById("xsxx").value = xx;	
				}
			}
			}	
			if($("rsNum")){
			   $("rsNum").innerText = data.length+1;
			}
			if($("ldqs")){
			  var ldmc=($("ldmc"))?$("ldmc").value:document.forms[0].lddm.options[document.forms[0].lddm.selectedIndex].text;
			  $("ldqs").innerText = ldmc+$("qsh").value;
			}
		}else{
			showMsgWin("有错误出现：远程数据读取失败！");
		}
	var seobj = $("rsTables").getElementsByTagName("input");
	for(var i = 0;i<seobj.length;i++){
		if(!seobj[i].checked){
			$("rsTables").getElementsByTagName("input")[i].checked = true;
			xx += (seobj[i].value + splitSignOne)
			//alert(xx);
		}
	}
	document.getElementById("xsxx").value = xx;	
}
	</script>
	
</html>
