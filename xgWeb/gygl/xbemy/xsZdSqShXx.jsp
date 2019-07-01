<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/xsgyglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>	
	</head>
	<body onload="xyDisabled('xy');">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>公寓管理 - 审核 - 学生走读(外住)审核 </a>
			</p>
		</div>
		
		<!-- 标题 end-->
		<html:form action="/XsgyglDispatch.do?method=ssYdSqShXx" method="post">
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" name="lcV" id="lcV" value=""/>
			<input type="hidden" name="qshV" id="qshV" />
		     <input type="hidden" name="zyV" id="zyV" />				
			<input type="hidden" name="bjV" id="bjV" />	
			<input type="hidden" name="userType" id="userType"
			       value="<bean:write name="userType"/>"/>																
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
						<li>
							<a href="#" 
								onclick="dataExport()"
								class="btn_dc">导出</a>
						</li>
						</logic:equal>
					</ul>
				</div>
				<!-- 按钮 end-->	
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="document.forms[0].go.value='go';refreshForm('/xgxt/XsgyglDispatch.do?method=xsZdSqShXx');">
											查询
										</button>
										  <button class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
							              	重 置
							              </button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- 第一行 -->
							<tr>
								<th>
									年级
								</th>
								<td>
									<html:select property="nj" style="width:90px"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>												
								</td>
								<th>
									学年
								</th>
								<td>
									<html:select property="xn" style="width:90px" styleId="xn">
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
									学号
								</th>
								<td>
									<html:text property="xh" style="width:80px" styleId="xh" />	
								</td>
							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									院系
								</th>
								<td>
									<html:select property="xydm" style="width:100px" styleId="xy"
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
									<html:select property="zydm" style="width:100px" styleId="zy"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td colspan="3">
									<html:select property="bjdm" style="width:100px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<!-- 第三行 -->
							<tr>
								<th>
									外住类型
								</th>
								<td>
									<html:select property="wzlxdm" styleId="wzlxdm">
										<html:option value=""></html:option>
										<html:options collection="wzlxList" property="wzlxdm"
											labelProperty="wzlxmc" />
									</html:select>							
								</td>
								<th>
									外住开始日期
								</th>
								<td>
									<html:text property="wzksrq" onblur="dateFormatChg(this)"
										onclick="return showCalendar('wzksrq','y-mm-dd');"
										style="cursor:hand;width:80px" />
								</td>
								<th>
									审核状态
								</th>
								<td colspan="3">
									<html:select property="yesNo" styleId="yesNo">
										<html:option value=""></html:option>
										<html:options collection="chkList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 过滤条件 end-->	
				<!-- 查询结果-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; 
							<logic:empty name="rs">
								<font color="red">未找到任何记录！</font>
							</logic:empty>
						</span>
					</h3>
					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<!-- 表头 -->
							<thead>
								<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="2">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
							</thead>
							<!-- 表头 end-->
							<!--内容 -->
							<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								style="cursor:hand;background-color:
	    					<logic:iterate id="v" name="s" offset="0" length="1">
						    	<bean:write name="v"/>
						    </logic:iterate>" ondblclick="CheckDo()">
								<td>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="3">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
							<!--内容 end-->
						</table>
					</logic:notEmpty>
				</div>
				<!-- 查询结果 end-->
			</div>	
		</html:form>					
  </body>

	<script type="text/javascript">
  function CheckDo(){
    var	w = 600;
	var	h = 400;	
	url = "/xgxt/XsgyglDispatch.do?method=xsZdSqSh";	
	url += "&pkValue=";
	url += curr_row.cells[0].getElementsByTagName("input")[0].value;
	showTopWin(url, w, h);	
  }
  </script>
</html>

