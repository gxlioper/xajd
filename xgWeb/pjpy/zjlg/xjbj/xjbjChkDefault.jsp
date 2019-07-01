<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script language="javascript" src="/xgxt/pjpy/nbzy/nbzyJs.js"></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script type="text/javascript">

		function xjbjView(){   
		    var url = "/xgxt/zjlgPjpy.do?method=xjbjCkView&pkValue=";
		    var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
			url += pk;
		    showTopWin(url,"800","500");
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
		           var url = "/xgxt/zjlgPjpy.do?method=xjbjCk&check="+str; 
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
		</script>
	</head>
<body onload="xyDisabled('xy');checkType();">
	<html:form action="/zjlgPjpy" method="post">
		
		<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>评奖评优 - 审核 - 先进班级审核</a>
				</p>
		</div>
		
		<input type="hidden" id="zyV" name="zyV" value="" />
		<input type="hidden" id="bjV" name="bjV" value="" />
		<input type="hidden" id="userType" name="userType" value="${userType}" />
		<input type="hidden" id="pkVStr" name="pkVStr" value="" />
		
			<div class="toolbox">
			<div class="buttonbox">	
				<ul>
					<li><a href="#" class="btn_shtg" onclick="batchCheck('yes')" id="checkpass">通 过</a></li>
					<li><a href="#" class="btn_shbtg" onclick="batchCheck('no')" id="checknopass">不通过</a></li>
										
				</ul>
			</div>
			</div>
		
	      <div class="searchtab">
			<table width="100%" class="" border="0">
				<tbody>
					<tr>
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
						<th>审核状态</th>
						<td><html:select property="yesNo" styleId="yesNo">
								<option value=""></option>
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="lable.xsgzyxpzxy" /></th>
						<td><html:select property="xydm" onchange="initZyList();initBjList()"
								 styleId="xy">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm" style="width:150px"
									labelProperty="xymc" />
							</html:select></td>
							<th>专业</th>
							<td><html:select property="zydm" onchange="initBjList()" style="width:150px"
								 styleId="zy">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select></td>
							<th>班级</th>
							<td><html:select property="bjdm" styleId="bj" style="width:150px">
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
		              		<input type="hidden" id="go" name="go" value="go" />
		              		<button type="button" id="search_go" 
		              		onclick="refreshForm('zjlgPjpy.do?method=xjbjChkDefault');document.getElementById('search_go').disabled=true;">
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
		<div id="result">
			<div class="searchcontent">
			
			
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
							<tbody>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand;"
									ondblclick="xjbjView()">
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
							</tbody>
						</table>
				</logic:notEmpty>
				</div>
				<br />
				<br />
				
				<div id="tmpdiv"></div>
			</div>
		</div>
		<logic:equal name="pass" value="no">
			<script>
	alert("<bean:write name="clin"/>");			    
   </script>
		</logic:equal>
	</html:form>
	<script type="text/javascript" src="js/bottomButton.js"></script>
</body>
</html>
