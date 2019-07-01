<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript">	
	
		</script>
	</head>
	<body>
		<html:form action="/njjsDtjs" method="post">
			<%@ include file="/dtjs/hiddenValue.jsp"%>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<logic:notEmpty name="msg">
				<div align="center">
					<font color="red" size="6"><bean:write name="msg" /></font>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<logic:equal value="yes" name="writeAble">
					<div class="toolbox">
					<div class="buttonbox">	
						<ul>
							<li><a href="#" class="btn_zj" onclick="showTopWin('/xgxt/njjsDtjs.do?method=tyxxUpdate',800,600);">增加</a></li>
							<li><a href="#" class="btn_xg" onclick="showInfo('/xgxt/njjsDtjs.do?method=tyxxUpdate','update','800','600')">修改</a></li>
							<li><a href="#" class="btn_sc" onclick="sumitInfo('/xgxt/njjsDtjs.do?method=tyxxManage','del')">删除</a></li>
							<li><a href="#" class="btn_dr" onclick="impAndChkData()">导入</a></li>
							<li><a href="#" class="btn_dc" onclick="wjcfDataExport('njjsDtjs.do?method=tyxxManage&doType=exp')">导出</a></li>								
						</ul>
					</div>
					</div>
				</logic:equal>
				<div class="searchtab">
						<table width="100%">
							<tfoot>
								<tr>
									<td colspan="6">
									<div class="btn">
									<input type="hidden" name="go" value="a" />
									<button type="button" id="search_go" onclick="allNotEmpThenGo('/xgxt/njjsDtjs.do?method=tyxxManage');">
										查 询
									</button>
									 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
										重 置
									 </button>
									</div>
									</td>
								</tr>
							</tfoot>
							<thead>
								<tr>
									<th>年级</th>
									<td>
										<html:select property="queryequals_nj" style="" onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj" labelProperty="nj" />
										</html:select>
									</td>
									<th>学号</th>
									<td><html:text property="querylike_xh" styleId="xh" style="width:100px" maxlength="20"/></td>
									<th>姓名</th>
									<td><html:text property="querylike_xm" styleId="xm" style="width:100px" maxlength="20"/></td>
								</tr>
								<tr>
									<th><bean:message key="lable.xsgzyxpzxy" /></th>
									<td><logic:equal name="userType" value="xy">
											<html:hidden property="queryequals_xydm"/>
											<html:select property="queryequals_xydm" style="width: 150px" styleId="xy" disabled="true">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm" labelProperty="xymc" />
											</html:select>
										</logic:equal>
										<logic:notEqual name="userType" value="xy">
											<html:select property="queryequals_xydm" style="width: 150px" styleId="xy" onchange="initZyList();initBjList();">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm" labelProperty="xymc" />
											</html:select>
										</logic:notEqual>
									</td>
									<th>专业</th>
									<td><html:select property="queryequals_zydm" style="width: 150px" styleId="zy" onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm" labelProperty="zymc" />
										</html:select></td>
									<th>班级</th>
									<td><html:select property="queryequals_bjdm" style="width: 150px" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>政治面貌</th>
									<td><html:select property="queryequals_zzmm" style="width: 150px" styleId="zzmm">
											<html:option value="">----请选择----</html:option>
											<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc" />
										</html:select>
									</td>
									<th>入党时间</th>
									<td colspan="3"><html:text property="querygreaterequal_rdsj" styleId="querygreaterequal_rdsj"
											onblur="dateFormatChg(this)" style="cursor:hand;width: 100px"
											onclick="return showCalendar('querygreaterequal_rdsj','y-mm-dd');"/>	
										-
										<html:text property="querylessequal_rdsj" styleId="querylessequal_rdsj"
											onblur="dateFormatChg(this)" style="cursor:hand;width: 100px"
											onclick="return showCalendar('querylessequal_rdsj','y-mm-dd');"/>	
									</td>
								</tr>
								<tr>
									<th>民族</th>
									<td><html:select property="queryequals_mz" style="width: 150px" styleId="mz">
											<html:option value="">----请选择----</html:option>
											<html:options collection="mzList" property="mzdm" labelProperty="mzmc" />
										</html:select></td>
										
									<th>入团时间</th>
									<td colspan="3">
										<html:text property="querygreaterequal_rtsj" styleId="querygreaterequal_rtsj"
											onblur="dateFormatChg(this)" style="cursor:hand;width: 100px"
											onclick="return showCalendar('querygreaterequal_rtsj','y-mm-dd');"/>	
										-
										<html:text property="querylessequal_rtsj" styleId="querylessequal_rtsj"
											onblur="dateFormatChg(this)" style="cursor:hand;width: 100px"
											onclick="return showCalendar('querylessequal_rtsj','y-mm-dd');"/>
									</td>									
								</tr>
							</thead>
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
							<table width="100%" id="rsTable" class="dateline">
								<thead>	
									<tr align="center" style="cursor:hand">
										<td>
											<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="1">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);" style="cursor:hand" 
										ondblclick="showInfo('/xgxt/njjsDtjs.do?method=tyxxUpdate','view','800','600')">
										<td align="center">
											<input type="checkbox" id="checkVal" 
												   name="primarykey_checkVal"  
												   value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
										</td>
										<logic:iterate id="v" name="s" offset="1" length="1">
										<td align="center">
											<bean:write name="v" />	
										</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="2">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
							<!--分页显示-->
						     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=dtjsTyForm"></jsp:include>
							<!--分页显示-->
					</logic:notEmpty>
				</div>
			</logic:empty>
		</html:form>
		<logic:notEmpty name="result">
			<logic:equal name="result" value="true">
			<script>
				if($("message") && $("message").value != ""){
					alert($("message").value);
					$("message").value = "";
					$("doType").value = "";
				}
			</script>
			</logic:equal>
		</logic:notEmpty>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
