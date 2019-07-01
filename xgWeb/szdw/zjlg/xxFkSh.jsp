<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="/xgxt/js/jsFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/GetListData.js"></script>
	<script language="javascript" src="/xgxt/js/pjpy/pjpy_zjsyzy.js"></script>
	<script type="text/javascript">
		function chkView(){
			var xxfkdm = $("xxfkdm").value; 	
		    var url = "/xgxt/szdw_zjlg.do?method=xxFkChek&xxfkdm="+xxfkdm+"&pkValue=";
		    var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
			url += pk;
		    showTopWin(url,"850","600");              		                  
		 }
		 function check(str){
		           var userType = $("userType").value;
		           var xxfkdm = $("xxfkdm").value; 	
		           var url = "/xgxt/szdw_zjlg.do?method=xxFkCk&xxfkdm="+xxfkdm+"&go=go&check="+str;
				   var RowsStr="";		  
				   var userType = $("userType").value;		   
				   xyshDone = (str=="yes")?"通过":"不通过";
				   var pkVArray = "'";
				   for (i=0; i<document.getElementsByName("pkV").length; i++){
			    	  if(document.getElementsByName("pkV")[i].checked){	    		 
			    		 pkVArray+=document.getElementsByName("pkV")[i].value+"','"
			    		 RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
			    	  }	    	  
				   }		   
				   if (RowsStr==""){
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
	<body >
		<center>
			<html:form action="/szdw_zjlg" method="post">
<%--				<input type="hidden" id="realTable" name="realTable" value="${realTable}" />--%>
<%--				<input type="hidden" id="tableName" name="tableName" value="${tableName}" />--%>
				<input type="hidden" id="userType" name="userType"
					value="${userType}" />
				<input type="hidden" id="userName" name=" userName "
					value="${userName}" />
				<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="fdyQx" scope="session"/>"/>		
				<input type="hidden" id="xyV" name="xyV"  value="" />
				<input type="hidden" id="zyV" name="zyV"  value="" />
				<input type="hidden" id="bjV" name="bjV"  value="" />
				<input type="hidden" id="pkVStr" name="pkVStr"  value="" />
				<input type="hidden" id="xxfkdm" name="xxfkdm"  value="<bean:write name="xxfkdm"/>" />
				<div class="tab_cur">
					<p class="location">
						<em>您的当前位置:</em><a>单个信息审核 - <bean:write name = "xxfkmc"/></a>
					</p>
				</div>
				<logic:equal value="yes" name="writeAble">
					<div class="toolbox">
					<div class="buttonbox">	
						<ul>
							<li><a href="#" class="btn_shtg" onclick="check('yes')" id="checkpass">通过</a></li>
							<li><a href="#" class="btn_shbtg" onclick="check('no')" id="checknopass">不通过</a></li>
							<li><a href="#" class="btn_sc" onclick="">删除</a></li>
							<li><a href="#" class="btn_dr" onclick="impAndChkData()">导入</a></li>
							<li><a href="#" class="btn_dc" onclick="dataExport()">导出</a></li>								
						</ul>
					</div>
					</div>
				</logic:equal>
				<div class="searchtab">		
					<table width="100%" class="" border="0">
						<tbody>
							<tr>
								<th>部门</th>
								<td><html:select property="bmdm" styleId="bmdm" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="bmList" property="bmdm"
											labelProperty="bmmc" />
									</html:select></td>
								<th>姓名</th>
								<td><html:text property="xm" /></td>
								<th>职工号</th>
								<td><html:text property="zgh" />													
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="6">
								<div class="btn">
								<input type="hidden" name="go" value="a" />
								<button type="button" id="search_go" onclick="refreshForm('/xgxt/szdw_zjlg.do?method=xxFkSh&go=go');">
									查询
								</button>
								 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
									重置
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
								查询结果&nbsp;&nbsp;<font color="blue">双击一行可以进行审核；单击表头可以排序</font> 
							</span>
						</h3>
						<div class="con_overlfow">
							 <table summary="" id="rsTable" class="dateline" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<td align="center">
										<input type="checkbox" name="all" value="all" onclick="chec()"/>
									</td>
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" style="cursor:hand"
										ondblclick="chkView()">
										<td align="center">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v" />"/>
											<input type="checkbox" name="pkV" value="<bean:write name="v"/>" />
										</logic:iterate>										
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
						</div>
					<!--分页显示-->
				     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xjchForm"></jsp:include>
					<!--分页显示-->
				</logic:notEmpty>
				</div>
			</html:form>
			<div id="tmpdiv"></div>
		</center>
	</body>
</html>
