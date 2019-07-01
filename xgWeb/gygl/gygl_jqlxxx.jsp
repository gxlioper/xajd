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

	<body onload="xyDisabled('xy');bjLimit('bj');">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="title" scope="request" /></a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/holidayPutUpInfo" method="post">
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="isFdy" id="isFdy" value="<bean:write name="fdyQx" scope="session"/>" />
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#"
									onclick="viewInfo('add','holiday_lsxx.do')"
									class="btn_zj">增加</a>
							</li>
							<li>
								<a href="#"
									onclick="viewInfo('modi','holiday_lsxx.do')"
									class="btn_xg">修改</a>
							</li>
							<li>
								<a href="#"
									onclick="viewInfo('del','holiday_lsxx.do')"
									class="btn_sc">删除</a>
							</li>
							<li>
								<a href="#" 
									onclick="impAndChkData()"
									class="btn_dr">导入</a>
							</li>		
							<logic:equal name="xxdm" value="11641">	
							<li>
								<a href="#" 
									onclick="jqPrint()"
									class="btn_dy">打印</a>
							</li>	
							</logic:equal>			
						</logic:equal>
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
											onclick="allNotEmpThenGo('/xgxt/holidayPutUpInfo.do')">
											查询
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- 第一行 -->
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
									<html:select  property="xq" styleId="xq">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
									</html:select>		
								</td>
								<th>
									学号
								</th>
								<td>
									<logic:equal name="userOnLine" value="student">
										<html:text  property="textxh" disabled="true"  styleId="textxh" value="${textxh}"/>
										<html:hidden property="xh" value="${textxh}" />
									</logic:equal>
									<logic:notEqual name="userOnLine" value="student">
										<html:text  property="xh"  styleId="xh"/>
									</logic:notEqual>
								</td>
							</tr>
							<logic:present name="showxbemy">
							<tr>
								<th>
									性别
								</th>
								<td>
									<html:select property="xb" styleId="xb">
									<html:option value=""></html:option>
									<html:option value="男">男</html:option>
									<html:option value="女">女</html:option>
									</html:select>								
								</td>
								<th>
									民族
								</th>
								<td>
									<html:select property="mz"  styleId="mz">
								    <html:option value=""></html:option>
								    <html:options collection="mzList" property="mzdm"
												labelProperty="mzmc" />
								  </html:select>		
								</td>
								<th>
									籍贯
								</th>
								<td>
									<html:text property="jg" styleId="jg" style="width:80px"></html:text>
								</td>
							</tr>
							</logic:present>
							<!-- 第二行 -->
							<tr>
								<th>
									院系
								</th>
								<td>
									<html:select property="xydm"  styleId="xy"
										onchange="initZyList();initBjList()" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc" style="width:180px"/>
									</html:select>											
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm"  styleId="zy" style="width:180px"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm" labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm"  styleId="bj" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
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
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)"  >
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
							</thead>
							<!-- 表头 end-->
							<!--内容 -->
							<logic:iterate name="rs" id="s">
								<tr onclick="rowMoreClick(this,'',event);"
									style="cursor:hand" ondblclick="viewInfo('view','/xgxt/holiday_lsxx.do')">
									<td >
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td wrap>
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
		<logic:equal value="ok" name="result">
			<script>
				alert("操作成功！");
				document.getElementById("search_go").click();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("操作失败！");
				document.getElementById("search_go").click();
			</script>
		</logic:equal>
		<script type="text/javascript">
		function jqPrint(){
			if(curr_row == null){
				alert("请选择要打印的数据！\n（单击相应的行）");
				return false;
			}
			var pk=pkValue = curr_row.getElementsByTagName("input")[0].value;
			window.open('/xgxt/XsgyglDispatch.do?method=jqlxPrint&pk='+pk);
		}
		</script>		
  </body>
</html>
