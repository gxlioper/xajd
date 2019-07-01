<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body onload="bjDisabled('nj-xy-zy-bj');myxyDisabled('xy');checkType()">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>		
    <html:form action="/csmz_sztz.do?method=data_search" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="xxdm" name="xxdm"
				value="<bean:write name="xxdm" scope="request"/>" />
	        <input type="hidden" name="userType" id="userType" 
		        value="<bean:write name="userType" scope="request"/>">	
		     <input type="hidden" name="userName" id="userName" 
		        value="<bean:write name="userName" scope="session"/>">	
			<input type="hidden" id="pkVStr" name="pkVStr" value="" />			        	
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${tips }</a>
				</p>
			</div>
			<div class="toolbox">
				 <!-- 按钮 -->
				 <div class="buttonbox">
				    <ul>
					<li> <a href="#" id="checkpass" onclick="batchCheck('yes')" class="btn_shtg"> 通过 </a> </li>
				    <li> <a href="#" id="checknopass" onclick="batchCheck('no')" class="btn_shbtg"> 不通过 </a> </li> 
				    </ul>
				 </div>
					<div class="searchtab">
						<table width="100%" border="0">
						      <tfoot>
						        <tr>
						          <td colspan="6">
						            <div class="btn">
						              <input type="hidden" name="go" value="a" />
						              <button class="btn_cx" id="search_go" 
						              	onclick="document.forms[0].go.value='go';refreshForm('/xgxt/csmz_sztz.do?method=data_search&act='+$('act').value)">
						              	查 询
						              </button>
						              <button class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
						              	重 置
						              </button>
						            </div>
						          </td>
						      </tr>
						</tfoot>
						<tbody>
							<tr>
									<th align="left">
										学年
									</th>
									<td>
										<html:select property="xn" style="width:100px" styleId="xn">
										<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
									</td>
									<th>
										学期
									</th>
									<td>
										<html:select property="xq" style="width:90px" styleId="xq">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
										</html:select>
									</td>
									<th>
										所属科目
									</th>
									<td>
										<html:select property="kmdm" style="width:150px"
											styleId="kmdm">
											<html:option value=""></html:option>
											<html:options collection="kmList" property="kmdm"
												labelProperty="kmm" />
										</html:select>
									</td>	
									
								</tr>
								<tr>
									<th>
										项目名称
									</th>
									<td>
										<html:text property="xmmc" styleId="xmmc"></html:text>	
																				
									</td>
									<th >										
										部门
									</th>
									<td>
										<html:select property="xydm" styleId="xy"
											style="width:150px" >
											<option value=""></option>
											<html:options collection="bmList" property="bmdm"
												labelProperty="bmmc"></html:options>
										</html:select>
									</td>
									<th>
										项目级别
									</th>
									<td>
										<html:select property="xmjb" styleId="xmjb"
											style="background-color:#FFFFFF;width:150px">											
											<html:options collection="xmjbList" property="en"
												labelProperty="cn"></html:options>
										</html:select>
									</td>
								</tr>
								<tr>
									<th>
										审核状态
									</th>
									<td>
										<html:select property="yesNo" style="width:150px">
										    <html:option value=""></html:option>
											<html:options collection="chkList" property="en"
												labelProperty="cn" />
										</html:select>	
									</td>
									<th>
									</th>
									<td>
									</td>
									<th>
									</th>
									<td>
									</td>
								</tr>
						</tbody>
					</table>
					</div>
				</div>
				<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs">
			 		 		记录数：
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;							
							<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序;</font>
			 		 </logic:notEmpty>
				    </span>
				    </h3>
				    <logic:notEmpty name="rs">
							 <table summary="" class="dateline" align="" width="100%" id="rsTable">
								<thead>
									<tr  style="cursor:hand">
									<td title="全选">
										<input type="checkbox"  name="fyxx"  value="all" 
											onclick="chec()">
									</td>
										<logic:iterate id="tit" name="topTr" offset="2">
											<td align="center" id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" >
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<tbody>						
									<logic:iterate name="rs" id="s">
										<tr onclick="rowOnClick(this);"
											style="cursor:hand;background-color:
                                        <logic:iterate id="v" name="s" offset="0" length="1">
                                        <bean:write name="v"/>
                                        </logic:iterate>
                                        "ondblclick="CheckDo()">
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<logic:iterate id="v" name="s" offset="1" length="1">
												<input type="checkbox" name="pkV"
													value="<bean:write name="v"/>">
											</logic:iterate>
										</logic:iterate>
									</td>
									<td>
												<logic:iterate id="v" name="s" offset="1" length="1">
													<input type="hidden" value="<bean:write name="v"/>" />
												</logic:iterate>
												<logic:iterate id="v" name="s" offset="2" length="1">
													<bean:write name="v" />
												</logic:iterate>
											</td>
											<logic:iterate id="v" name="s" offset="3">
												<td >
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>	
									</tbody>						
							</table>
							<jsp:include flush="true"
									page="/sjcz/turnpage.jsp?form=sztzForm"></jsp:include>	 	
				  </logic:notEmpty>
				  </div>
									  									
	
	</html:form>
	<script type="text/javascript" src="js/bottomButton.js"></script>		
    </body>
<script type="text/javascript">
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
function chec(){
       for(i=0;i<document.getElementsByName("pkV").length;i++){
      	  document.getElementsByName("pkV")[i].checked=document.getElementsByName("fyxx")[0].checked;
       }
}
function CheckDo(){
    var	w = 700;
	var	h = 500;
	var realTable = document.getElementById("realTable").value;	
	url = "/xgxt/csmz_sztz.do?method=tzxmShView";	
	url += "&pkValue=";
	url += curr_row.cells[1].getElementsByTagName("input")[0].value;
	showTopWin(url, w, h);	
}
function myxyDisabled(ele) {
    var userT = document.getElementById("userType").value;
	if (userT == "xy"||userT =="stu") {
		var tmp = ele.split("-");
		for (i = 0; i < tmp.length; i++) {
			document.getElementById(tmp[i]).disabled = true;
		}
	}
}

 function batchCheck(str){
           var url = "/xgxt/csmz_sztz.do?method=plTzxmCheck&check="+str; 
		   var RowsStr="!!";		  
		   var userType = $("userType").value;		   
		   xyshDone = (str=="yes")?"通过":"不通过";
		   var pkVArray = "'";
		   for (i=0; i<document.getElementsByName("pkV").length; i++){
	    	  if(document.getElementsByName("pkV")[i].checked){
	    		 RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
	    		 pkVArray+=document.getElementsByName("pkV")[i].value+"','";
	    	  }	    	  
		   }
		   
		   if (RowsStr=="!!"){
			   alert("请选择要操作的记录！\n(单击每条记录前复选框)");
			   return false;
		   }
		   document.forms[0].pkVStr.value = RowsStr;
		   pkVArray=pkVArray.substring(0,pkVArray.length-2);
		   		      
		   getSztzData.getInfoEx2("csmz_tzxmb"," id in ("+pkVArray+") and xysh<>'通过' ",function(boolean){
		        if(boolean){
		        	if($("userType").value=="xy"){
		        			refreshForm(url);   
		        	}else{
			             if (confirm("所选中记录中，有<bean:message key="lable.xsgzyxpzxy" />审核\"不通过\"或\"未审核\"的记录！\n\n确定要\""+xyshDone+"\"所选记录？")){
			             	refreshForm(url);   
			             }
		            }
		        }else{		            		
		         if (confirm("确定要\""+xyshDone+"\"所选记录？")){
			        refreshForm(url);   
		         }
		       }
		   });
	             
  }
</script>
</html>	
