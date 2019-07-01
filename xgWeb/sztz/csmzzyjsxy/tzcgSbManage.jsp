<%@ page language="java" contentType="text/html; charset=GBK"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<%@ include file="/syscommon/head.ini"%>
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
	<body onload="bjDisabled('nj-xy-zy-bj');myxyDisabled('xy');removeXnXq();">
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
		     <input type="hidden" name="pkVStr" id="pkVStr" value="">		        		
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${tips }</a>
				</p>
			</div>
			<div class="toolbox">
				 <!-- 按钮 -->
				 <div class="buttonbox">
				    <ul>
				    <logic:equal value="yes" name="writeAble" scope="request">
					<li> <a href="#" onclick="MyMoreDo('add');return false;" class="btn_zj"> 增加 </a> </li>
				      <li> <a href="#" onclick="MyMoreDo('modi');return false;" class="btn_xg"> 修改 </a> </li>
					<li> <a href="#" onclick="del();return false;" class="btn_sc"> 删除 </a> </li>
				    </logic:equal>
				    <li> <a href="#" onclick="dataExport();return false;" class="btn_dc"> 导出数据 </a> </li>
				    </ul>
				 </div>
			    
			     <div class="searchtab">
					<table width="100%" border="0">
					      <tfoot>
					        <tr>
					          <td colspan="6">
					            <div class="btn">
					              <input type="hidden" name="go" value="a" />
					              <button  id="search_go" 
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
										年级
									</th>
									<td>
										<html:select property="nj" style="width:80px"
											onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>
									</td>
									
									
									<th>
										学号
									</th>
									<td>
										<html:text property="xh" style="width:85px"></html:text>
									</td>
									<th>
										姓名
									</th>
									<td>
										<html:text property="xm" style="width:85px"></html:text>
									</td>
										
								</tr>
								<tr>
									<th >
										<bean:message key="lable.xsgzyxpzxy" />(部门)
									</th>
									<td>
										<html:select property="xydm" style="width:180px" styleId="xy"
											onchange="initZyList();initBjList()">
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
											onchange="initBjList()">
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
								<tr>
										<th nowrap>
											项目名称
										</th>
										<td>
											<html:text property="xmmc" style="width:85px"></html:text>										
										</td>
										<logic:equal value="no" name="ptcx">
										<th>
											学年
										</th>
										<td>
										<html:select property="xn" style="width:100px" styleId="xn"
											onchange="genNdList(this)">
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
										</logic:equal>
										<logic:notEqual  value="no" name="ptcx">
										<th></th>
										<td></td>
										<th></th>
										<td></td>
										</logic:notEqual>
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
			    </span>
			    </h3>

				<logic:notEmpty name="rs">
					<table summary="" class="dateline" align="" width="100%" id="rsTable">
								<thead>
									<tr >
										<td align="center">
										<input type="checkbox" name="cb" value="cb" onclick="selectAll()">
									   </td>
										<logic:iterate id="tit" name="topTr" offset="2">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" >
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<tbody>
									<logic:iterate name="rs" id="s">
										<tr onclick="rowOnClick(this);"
											style="cursor:hand;"
											ondblclick="MyMoreDo('view')">
											<td align="center">
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="hidden" value="<bean:write name="v" />"/>
											<input type="checkbox" name="pk" value="<bean:write name="v"/>">
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
    function myxyDisabled(ele) {
      var userT = document.getElementById("userType").value;
	  if (userT == "xy"||userT =="stu") {
		var tmp = ele.split("-");
		for (i = 0; i < tmp.length; i++) {
			document.getElementById(tmp[i]).disabled = true;
		}
	  }
    }
    function del(){          
           var url = "/xgxt/csmz_sztz.do?method=cgsbInfoDel&go=go&act=cgsb"; 
		   var RowsStr="";		  		 
		   var pkVArray = "'";
		   for (i=0; i<document.getElementsByName("pk").length; i++){
	    	  if(document.getElementsByName("pk")[i].checked){	    		 
	    		 pkVArray+=document.getElementsByName("pk")[i].value+"','"
	    		 RowsStr+=document.getElementsByName("pk")[i].value+"!!";
	    	  }	    	  
		   }		   
		   if (RowsStr==""){
			   alert("请选择要操作的记录！\n(单击每条记录前复选框)");
			   return false;
		   }
		   document.forms[0].pkVStr.value = RowsStr;
		   pkVArray=pkVArray.substring(0,pkVArray.length-2);		   		   
		   if (confirm("确定要删除所选记录？")){
		      refreshForm(url);		          	
		   }	                  
 }
  </script>
</html>	
