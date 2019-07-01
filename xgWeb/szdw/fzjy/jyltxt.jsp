<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/sxjyFunction.js"></script>
	<script language="javascript">
	
	//这里没有XYDM需要DISABLED
<%--	function check_user()--%>
<%--	{--%>
<%--		var user=document.all['userType'].value;--%>
<%--		if("xy"==user)--%>
<%--		{--%>
<%--			document.getElementById('xydm').disabled=true;--%>
<%--		}--%>
<%--		else if("xx"==user)--%>
<%--		{--%>
<%--			document.getElementById('xydm').disabled=false;--%>
<%--		}--%>
<%--	}--%>
	
	function yzRadio(url,w,h){
	var tmps = document.getElementsByName("tableName");
	var sfxz = false;
	for(i = 0; i<tmps.length;i++){
		if(tmps[i].checked){
			sfxz = true;
		}
	}
	if(sfxz){
		return true;
	}else{
		alert("请选择要操作的表");
		return false;
	}
}
</script>
</head>
<%--check_user()--%>
	<body onload="">
		<center>
			<html:form action="/szdwfzjy" method="post">
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope = "session"/>" />
			<input type="hidden" id="method" name="method"
				value="jyltxt" />
			<input type="hidden" id="title" name="title"
				value="<bean:write name="title"/>" />
			<input type="hidden" id="writeAble" name="writeAble"
				value="<bean:write name="writeAble"/>" />
				<div class="tab_cur">
					<p class="location">
						<em>您的当前位置:</em><a><bean:write name = "title" /></a>
					</p>
				</div>
				<div class="toolbox">
				 <!-- 按钮 -->
				 
				 <logic:equal value="yes" name="writeAble">
				 <div class="buttonbox">
				    <ul>
					<li> <a href="#" onclick="fzjyYzRadio('/xgxt/szdwfzjy.do?method=jyltxtOne&tableName=',600,400);" class="btn_zj"> 增加 </a> </li>
				      <li> <a href="#" onclick="if(curr_row == null){alert('请选择要修改的行!');return false;}else{fzjyYzRadio('/xgxt/szdwfzjy.do?method=jyltxtOne&pk='+curr_row.cells[0].getElementsByTagName('input')[0].value+'&tableName=',600,480)}" class="btn_xg"> 修改 </a> </li>
					<li> <a href="#" onclick="if(curr_row == null){alert('请选择要删除的行!');return false;}else{refreshForm('/xgxt/szdwfzjy.do?method=delJyltxtOne&go=go&pk='+curr_row.cells[0].getElementsByTagName('input')[0].value)}" class="btn_sc"> 删除 </a> </li>
					<li> <a href="#" onclick="if(yzRadio()){impAndChkData();}" class="btn_dr"> 导入 </a> </li>
					<li> <a href="#" onclick="if(yzRadio()){dataExport()}" class="btn_dc"> 导出 </a> </li>
					<li> <a href="#" onclick="if(yzRadio()){dataStencilExport()}" class="btn_dc"> 下载模版 </a> </li>
				    </ul>
				 </div>
				 </logic:equal>
				<div class="searchtab">
				<table width="100%" border="0">
		      <tfoot>
		        <tr>
		          <td colspan="6">
		            <div class="btn">
		               <input type="hidden" name="go" value="a" />
		              <button type="button" class="btn_cx" id="search_go" 
		              	onclick="if(yzRadio()){allNotEmpThenGo('/xgxt/szdwfzjy.do');}">
		              	查 询
		              </button>
		              &nbsp;&nbsp;&nbsp;&nbsp;
		              <button type="button" class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
		              	重 置
		              </button>
		            </div>
		          </td>
		        </tr>
		      </tfoot>
	  			<tbody>
	   			<tr> 
	      		<th align="left" nowrap>
	      				主题　
	      		</th>
	      		<td>
	        		<html:text  property="zt" />
	        	</td>
	        	<th>
	        		起始时间
	        	</th>
	        	<td>
	        	<html:text  property="kssj" styleId="kssj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('kssj','y-mm-dd');" />
				</td>
				<th>
					结束时间
				</th>
				<td>
				<html:text  property="jssj" styleId="jssj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('Jssj','y-mm-dd');" />
	        	</td>
	    		</tr> 
	    		<tr>
								<td align="left" colspan="6">
									<html:radio property="tableName" value="fzjy_xsrxjyb" >新生入学教育</html:radio>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<html:radio property="tableName" value="fzjy_fdyltb" >辅导员论坛</html:radio>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<html:radio property="tableName" value="view_yxdxsxsltb" >优秀大学生论坛</html:radio>	
									&nbsp;&nbsp;&nbsp;&nbsp;
									<html:radio property="tableName" value="fzjy_hyjtb" >弘毅讲坛</html:radio>
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
						<font color="blue">提示：双击一行可以选定；单击表头可以排序</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
				<logic:notEmpty name="rs">
					 <table summary="" class="dateline" align="" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">

									<logic:iterate id="tit" name="topTr" offset="1" length="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
									<logic:iterate id="tit" name="topTr" offset="2">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
							<logic:iterate name="rs" id="s">
							<logic:equal value="yes" name="writeAble">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="fzjyYzRadio('/xgxt/szdwfzjy.do?method=jyltxtOne&pk='+curr_row.cells[0].getElementsByTagName('input')[0].value+'&tableName=',600,480)">	
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v" />" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:equal>
							<logic:notEqual value="yes" name="writeAble">
								<tr onclick="rowOnClick(this)" style="cursor:hand">	
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v" />" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:notEqual>
							</logic:iterate>
							</tbody>
						</table>
				</logic:notEmpty>
			</div>
				<logic:notEmpty name="delete">
				<logic:equal name="delete" value="ok">
					<script>
                      alert("删除成功!");
                    </script>
				</logic:equal>
				<logic:equal name="delete" value="no">
					<script>
                      alert("删除失败");
                    </script>
				</logic:equal>
				</logic:notEmpty>
			</html:form>
		</center>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
