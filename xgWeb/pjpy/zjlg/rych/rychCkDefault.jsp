<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script language="javascript" src="/xgxt/pjpy/nbzy/nbzyJs.js"></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script type="text/javascript"><!--

	function rychView(){   
	    var url = "/xgxt/zjlgPjpy.do?method=rychCkView&pkValue=";
	    var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
		url += pk;
	    showTopWin(url,"800","400");
	}
	function checkType(){
	   var userType = $("userType").value;
	   if(userType=="xx"||userType=="admin"){
	      if($("checkpass")){ $("checkpass").value="学校审核通过";}
	      if($("checknopass")){ $("checknopass").value="学校审核不通过";}
	   }else if(userType=="xy"){
	      if($("checkpass")){ $("checkpass").value="<bean:message key="lable.xsgzyxpzxy" />审核通过";}
	      if($("checknopass")){ $("checknopass").value="<bean:message key="lable.xsgzyxpzxy" />审核不通过";}
	   }
	} 
	function batchCheck(str){
	           var userType = $("userType").value;
	           var url = "/xgxt/zjlgPjpy.do?method=rychCk&check="+str; 
			   var RowsStr="!!";		  
			   var userType = $("userType").value;		   
			   xyshDone = (str=="yes")?"通过":"不通过";
			   var pkVArray = "'";
			   for (i=0; i<document.getElementsByName("pk").length; i++){
		    	  if(document.getElementsByName("pk")[i].checked){	    		 
		    		 pkVArray+=document.getElementsByName("pk")[i].value+"','"
		    		 RowsStr+=document.getElementsByName("pk")[i].value+"!!";
		    	  }	    	  
			   }		   
			   if (RowsStr=="!!"){
				   alert("请选择要操作的记录！\n(单击每条记录前复选框)");
				   return false;
			   }
			   document.forms[0].pkVStr.value = RowsStr;
			   pkVArray=pkVArray.substring(0,pkVArray.length-2);		   
			   if (confirm("确定要\""+xyshDone+"\"所选记录？")){
				     refreshForm(url);
			   }         		                  
	  }
	 function rych_Shfs(){
		var shfs = document.getElementById("shfs").value;
		var userType = $("userType").value;
	    if(userType=="xy"){
	       	document.getElementById("xy").disabled = true;		
	        if(shfs=="xyfs"){
	            document.getElementById("zy").disabled = true;
			    document.getElementById("bj").disabled = true;
	        }else if(shfs=="zyfs"||shfs=="xyfs"){           
	        	document.getElementById("zy").disabled = false;
			    document.getElementById("bj").disabled = true;
	        }else if(shfs=="bjfs"){           
	            document.getElementById("zy").disabled = false;
			    document.getElementById("bj").disabled = false;
	        }
			return false;
	    }
	    
		if(shfs=="xyfs"){
			document.getElementById("xy").disabled = false;		
			document.getElementById("zy").disabled = true;
			document.getElementById("bj").disabled = true;
		}else if(shfs=="zyfs"){
			document.getElementById("xy").disabled = false;
			document.getElementById("zy").disabled = false;
			document.getElementById("bj").disabled = true;
		}else if(shfs=="bjfs"){
			document.getElementById("xy").disabled = false;
			document.getElementById("zy").disabled = false;
			document.getElementById("bj").disabled = false;
		}
	}
	function rychCkSearch(){
	var shfs = document.getElementById("shfs").value;
	    if(shfs=="xyfs"){
	      if($("xy").value==""){
	      alert("审核方式为\"<bean:message key="lable.xsgzyxpzxy" />\"，请选择<bean:message key="lable.xsgzyxpzxy" />！");
	      return false; 
	      }
	    }
	    if(shfs=="zyfs"){
	      if($("zy").value==""){
	      alert("审核方式为\"专业\"，请选择专业！");
	      return false;
	      } 
	    }
	    if(shfs=="bjfs"){
	      if($("bj").value==""){
	      alert("审核方式为\"班级\"，请选择班级！");
	      return false;
	      } 
	    }    
	    refreshForm('zjlgPjpy.do?method=rychCkDefault');
	    document.getElementById('search_go').disabled=true;
	} 
	
	function checkback(){
     
      var RowsStr="!!";
      var pkVArray = "'";
		   for (i=0; i<document.getElementsByName("pk").length; i++){
	    	  if(document.getElementsByName("pk")[i].checked){	    		 
	    		 pkVArray+=document.getElementsByName("pk")[i].value+"','"
	    		 RowsStr+=document.getElementsByName("pk")[i].value+"!!";
	    		// var jxjsfsh = document.getElementsByName("jxjsfsh")[i].value; 
	    		// if(jxjsfsh == "通过"){
				 //    alert("请选择学校审核没有通过的学生！！");
				///  return false;
  	  	        // }
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
          var url = "/xgxt/zjlgPjpy.do?method=audit&go=go&workFlowName=rychlc&status=th&tableName=zjlg_xsrychb&thly="+thly; 
          refreshForm(url);
          $("kfbtnSave1").disabled=true; 
}
function checkview(){
      var RowsStr="!!";
      var pkR = "'";
      var num=0;
		   for (i=0; i<document.getElementsByName("pk").length; i++){
	    	  if(document.getElementsByName("pk")[i].checked){	    		 
	    		 pkR=document.getElementsByName("pk")[i].value;
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
		   var url = "/xgxt/zjlgPjpy.do?method=recordView&tableName=zjlg_xsrychb&pkR="+pkR; 
           showTopWin(url,750,550);
}
	--></script>
</head>
<body onload="xyDisabled('xy');checkType();rych_Shfs()">
	<html:form action="/zjlgPjpy" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>评奖评优 - 审核 - 荣誉称号审核</a>
			</p>
		</div>
		
		<input type="hidden" id="zyV" name="zyV" value="" />
		<input type="hidden" id="bjV" name="bjV" value="" />
		<input type="hidden" id="userType" name="userType" value="${userType }" />
		<input type="hidden" id="pkVStr" name="pkVStr" value="" />
		
		<div class="toolbox">
			<div class="buttonbox">	
				<ul>
					<li><a href="#" class="btn_shtg" onclick="batchCheck('yes')" id="checkpass">通  过</a></li>
					<li><a href="#" class="btn_shbtg" onclick="batchCheck('no')" id="checknopass">不通过</a></li>
					<logic:equal value="xx" name="userType">
					<li><a href="#" class="btn_fh" id="checkback" onclick="checkback();">退回</a></li>
					</logic:equal>
					<li><a href="#" class="btn_sx" id="checkview" onclick="checkview();">流程追踪</a></li>
				</ul>
			</div>
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
		<div class="searchtab">		
			<table width="100%" class="tbstyle" border="0">
				<tbody>
					<tr>
						<th>审核方式</th>
						<td><html:select property="shfs" styleId="shfs"
								onchange="rych_Shfs()">
								<html:option value="xyfs"><bean:message key="lable.xsgzyxpzxy" /></html:option>
								<html:option value="zyfs">专业</html:option>
								<html:option value="bjfs">班级</html:option>
							</html:select></td>
						<th>年级</th>
						<td><html:select property="nj" styleId="nj"
								onchange="initZyList();initBjList()" styleClass="select"
								style="width:90px">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select></td>
						<th>学年</th>
						<td><html:select property="xn" styleClass="select" disabled="true"
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select></td>
						</tr>
						<tr>
							<th>荣誉称号</th>
							<td><html:select property="rychdm" styleId="rychdm">
								<html:options collection="rychList" property="dm"
									labelProperty="mc" />
							</html:select></td>
							<th>审核状态</th>
							<td><html:select property="yesNo" styleId="yesNo">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<th></th><td></td>
					</tr>
					<tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td><html:select property="xydm" onchange="initZyList();initBjList()" style="width:150px"
								styleClass="select" styleId="xy">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select></td>
						<th>专业</th>
						<td><html:select property="zydm" onchange="initBjList()" style="width:150px"
								styleClass="select" styleId="zy">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select></td>
						<th>班级</th>
						<td><html:select property="bjdm" styleClass="select" styleId="bj" style="width:150px">
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
						<input type="hidden" name="go" value="go" />
						<button type="button" id="search_go" onclick="refreshForm('zjlgPjpy.do?method=rychCkDefault')">
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
							查询结果&nbsp;&nbsp;<font color="blue">双击一行可以查看详细信息；单击表头可以排序</font> 
						</span>
					</h3>
					<table width="99%" id="rsTable" class="dateline">
							<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="1" scope="request">
										<td id="${tit.en}" onclick="tableSort(this)">
											${tit.cn}
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand;"
									ondblclick="rychView()">
									<td align=center>
										<input type="checkbox" id="pk" name="pk"
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
				</logic:notEmpty>
			<div id="tmpdiv"></div>
		</div>
		<logic:equal name="pass" value="no">
			<script>
				alert("<bean:write name="clin"/>");			    
			</script>
		</logic:equal>
	</html:form>
	<script type="text/javascript" src="js/bottomButton.js"></script>
</body>
