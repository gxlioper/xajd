<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<script language="javascript">
	function bzzrs(num){
		var idnum="pkV"+num;
		var pk = document.getElementById(idnum).value;
		showTopWin("/xgxt/XsgyglHhDispatch.do?method=zrscj&pk="+pk,600,480);
	}
	</script>
	</head>
	
	<body >
		
		<html:form action="/wsjc" method="post">
			<input type="hidden" id="tableName" name="tableName"
						value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="act" name="act"
						value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
						value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
						value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="dxq" name="dxq"
							value="<bean:write name="writeAble" scope="request"/>" />
			<input type="hidden" id="userType" name="userType"
						value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="xxdm" name="xxdm"
						value="<bean:write name="xxdm" scope="request"/>" />
			<input type="hidden" name="lcV" id="lcV" value=""/>
			<input type="hidden" name="qshV" id="qshV" />	
			<div class="tab_cur">
				<p class="location">
				<em>您的当前位置：</em><a><bean:write name="tips" scope="request" /></a>
				</p>
			</div>											
			<div class="toolbox">
			 <logic:equal value="yes" name="writeAble" scope="request">	
			 <div class="buttonbox">
			    <ul>
				<li> <a href="#"  onclick="viewMore('add')" class="btn_zj"> 增加 </a> </li>
			    <li> <a href="#"  onclick="viewMore('modi')" class="btn_xg"> 修改 </a> </li>
				<li> <a href="#"  onclick="viewMore('del')" class="btn_sc"> 删除 </a> </li>
				<li> <a href="#"  onclick="impAndChkData();" class="btn_dr"> 导入 </a> </li>
				<li> <a href="#"  onclick="dataExport()" class="btn_dc"> 导出 </a> </li>
				<logic:equal value="11647" name="xxdm"><!-- 浙江传媒 -->
					<li> <a href="#" onclick="wsjctj()" class="btn_tj"> 统计 </a> </li>
				</logic:equal>
			 </div>
			 </logic:equal>
		 
			 <div class="searchtab">
				<table width="100%" border="0">
					<tfoot>
						<tr>
				 		<td colspan="6" >
							<input type="hidden" name="go" value="a" />
							<div class="btn">
		             			 <button type="button" class="btn_cx" id="search_go" 
									onclick="allNotEmpThenGo('/xgxt/wsjc.do')">
									查 询
								 </button>
		                         &nbsp;&nbsp;&nbsp;&nbsp;
		                         <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
		              	           	 重 置
		                         </button>
		                    </div>
						</td>
					</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>
								学年
							</th>
							<td>
								<html:select  property="xn"  styleId="xn">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</td>
							<th>
								学期
							</th>
							<td>
								<html:select  property="xq"  styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
							</td>
							<th>
<%--								<logic:present name="showhzy">--%>
								楼栋名
							</th>
							<td>
									<html:select property="lddm"  styleId="lddm"
										onchange="getLcList()">
										
										<html:options collection="ldList" property="lddm"
											labelProperty="ldmc" />
									</html:select>
							</td>
						</tr>
						<tr>
							<th>
								楼层
							</th>
							<td>
										<html:select property="lc"  styleId="lc"
										onchange="getQshList2()">
										
										<html:options collection="lcList" property="dm"
											labelProperty="mc" />
									</html:select>
							</td>
							<th>
								寝室号
							</th>
							<td>
										<html:select property="qsh"  styleId="qsh">
										
										<html:options collection="qshList" property="dm"
											labelProperty="mc" />
									</html:select>
							</td>
							 <logic:notPresent name="noshowbm">
							 <th>
								检查部门
							</th>
							<td>
								<html:select  property="jcbm"  styleId="bmdm">
									<html:option value=""></html:option>
									<html:options collection="bmList" property="bmdm" labelProperty="bmmc" />
								</html:select>
							</td>
							</logic:notPresent>
							 <logic:present name="noshowbm">
							<td colsapn="2">
							</logic:present>
						</tr>
<%--								</logic:present>--%>
<%--								<logic:notPresent name="showhzy">--%>
<%--							        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 楼栋名:--%>
<%--								        <html:select property="lddm" style="width:120px"--%>
<%--											onchange="GetQshList()" styleId="lddm">											--%>
<%--											<html:options collection="ldList" property="lddm"--%>
<%--												labelProperty="ldmc" />--%>
<%--										</html:select>								--%>
<%--								    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;寝室号：--%>
<%--								       <html:select property="qsh" style="width:110px" styleId="qsh">--%>
<%--											<html:option value=""></html:option>--%>
<%--											<html:options collection="qshList" property="dm"--%>
<%--												labelProperty="mc" />--%>
<%--										</html:select>--%>
<%--								</logic:notPresent>--%>
						</tr>
						<tr>
							<td colspan="6">
								&nbsp;&nbsp;检查时间
								<logic:notEqual name="xxdm" value="11641">
									<html:text property="gzkssj" readonly="true"  onblur="dateFormatChg(this)"
										onclick="return showCalendar('gzkssj','y-mm-dd','ag');" style="cursor:hand;width:80px " />
										-
									<html:text property="gzjssj" readonly="true" onblur="dateFormatChg(this)"
										onclick="return showCalendar('gzjssj','y-mm-dd','ag');" style="cursor:hand;width:80px " />	
								</logic:notEqual>
								<logic:equal name="xxdm" value="11641">
									<html:select property="jcsj" styleId="jcsj">
										<html:option value=""></html:option>
										<html:options collection="zsList" property="en" labelProperty="cn" />
									</html:select>
								</logic:equal>
								<!-- 中国地质大学 -->
								<logic:equal name="xxdm" value="10491">		
								&nbsp;&nbsp;卫生检查等级
									<html:select property="dj" style="width:150px" styleId="dj" onchange="">
										<html:option value=""></html:option>
										<html:options collection="djList" property="mc" labelProperty="mc" />
									</html:select>
								</logic:equal>
								<!-- 中国地质大学 end-->
							</td>
						</tr>
					</tbody>
				</table>		
			<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs" >
			 		 		显示记录数：${rsNum }
								&nbsp;
								<font color="blue"> 
									提示：双击一行可以查看详细信息并修改；单击表头可以排序;</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
			
			<logic:notEmpty name="rs">
	   		<div class="con_overlfow">
				 <table summary="" id="rsTable" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:notEqual name="xxdm" value="11641">
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								</logic:notEqual>
								<logic:equal name="xxdm" value="11641">
								<logic:iterate id="tit" name="topTr" offset="1" length="10">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
									<td>
										值日生
									</td>
								</logic:equal>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s" indexId="index">
							<tr onclick="rowMoreClick(this,'',event);"
								style="cursor:hand" ondblclick="viewMore('view')">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:notEqual name="xxdm" value="11641">
								<logic:iterate id="v" name="s" offset="2">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
								</logic:notEqual>
								<logic:equal name="xxdm" value="11641">
								<logic:iterate id="v" name="s" offset="2" length="9">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="11" length="1">
									<td>
										<input type="hidden" name="pkV${index}"id="pkV${index}" value="<bean:write name="v" />"/>
										<button type="button" class="button2" onclick="bzzrs(${index});"
										style="width:80px">
										值日生
										</button>	
									</td>
								</logic:iterate>
								</logic:equal>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
					</div>
			</logic:notEmpty>
			</div>								
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
		<script type="text/javascript">
		  function wsjctj(){
		  	document.forms[0].action = "/xgxt/wsjctj.do";
	        document.forms[0].target = "_blank";
	        document.forms[0].submit();
	        document.forms[0].target = "_self";
		  }
		</script>
  </body>
</html>
