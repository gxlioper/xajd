<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
			function doDel(checkname,url,type){
				var checkBoxArr = document.getElementsByName(checkname);
				var flag = false;
				for(var i=0;i<checkBoxArr.length;i++){
					if(checkBoxArr[i].checked==true){
						
						if (checkBoxArr[i].alt=='disabled'){
							alert('您选择的特色班级有在校学生不能删除,请确认!');
							return false;
						} else {
							flag = true;
						}
					}
				}
				
				if (flag){
					var confirmValue;
					if(type=='del'){
						confirmValue='确定要删除所选择的数据吗？';
					}
					
					if (confirm(confirmValue)){
						document.forms[0].action = url;
						document.forms[0].submit();
					}
				}
			}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
	
	<html:form action="/tsbj">
		<input type="hidden" id="tableName" name="tableName"
				value="xg_view_xsxx_tsbj" />
		<input type="hidden" id="realTable" name="realTable"
				value="xg_xsxx_tsbjb" />
	
		<div class="toolbox">
			<!-- 按钮 -->
			<logic:equal value="yes" name="writeAble">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" id="btn_zj"
							   onclick="showTopWin('tsbj.do?method=tsbjAdd','450','300')"
							   class="btn_zj"> 增加 </a>
						</li>
						<li>
							<a href="#" id="btn_xg"
							   onclick="showViewWindow('primarykey_cbv','tsbj.do?method=tsbjView','450','300')"
							   class="btn_xg"> 修改 </a>
						</li>
						<li>
							<a href="#" id="btn_sc"
								onclick="doDel('primarykey_cbv','tsbj.do?method=tsbjDel','del')"
								class="btn_sc"> 删除 </a>
						</li>
						<li>
							<a href="#" onclick="impAndChkData()" class="btn_dr" id="btn_dr"> 导入 </a>
						</li>
						<li>
							<a href="#"
								id="btn_dc"
								onclick="configureExportData();return false;"
								class="btn_dc"> 导出 </a>
						</li>
						<li>
							<a href="#" class="btn_qx" onclick="choiceFields();return false;" id="btn_qx">导出字段确认</a>
						</li>
					</ul>
				</div>
			</logic:equal>
			<div class="searchtab">
				<table width="100%" border="0" id="searchTab">
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go"
										onclick="allNotEmpThenGo('tsbj.do?method=tsbjgl&doType=query')">
										查 询
									</button>
									<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
										重 置
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>班级代码</th>
							<td>
								<html:text property="tsbjdm"></html:text>
							</td>
							<th>班级名称</th>
							<td>
								<html:text property="tsbjmc"></html:text>
							</td>
							<th>创建人</th>
							<td>
								<html:text property="cjrxm"></html:text>
							</td>
						</tr>
						<tr>
							<th>创建时间</th>
							<td colspan="5">
								<html:text property="cjkssj"
										   styleClass="jssj"
										   onclick="return showCalendar('cjkssj','y-mm-dd');"
								></html:text>
								
								-
								<html:text property="cjjssj"
										   styleClass="jssj"
										   onclick="return showCalendar('cjjssj','y-mm-dd');"	
								></html:text>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; 
						<logic:notEmpty name="rs">
							<font color="blue">提示：单击表头可以排序</font>
						</logic:notEmpty> 
					</span>
				</h3>


				<div class="con_overlfow">
					<table summary="" class="dateline tablenowrap" align=""
						width="100%">
						<thead>
							<tr>
								<td width="17px">
									<input type="checkbox" disabled="disabled"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="0" scope="request">
									<td onclick="tableSort(this)">
										${tit}
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s">
									<tr>
										<td>
											<input type="checkbox" id="cbv" name="primarykey_cbv"
												alt="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"
												value="<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>" />
										</td>
										<logic:iterate id="v" name="s" offset="1" length="4">
											<td>
												${v }
											</td>
										</logic:iterate>
										<td>
											<a
											href="tsbj.do?method=tsbjxsgl&doType=query&tsbjdm=<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>&tsbjmc=<logic:iterate id="v" name="s" offset="2" length="1">${v }</logic:iterate>"
											class="pointer" style="color:#0A63BF"> 
											<logic:iterate id="v" name="s" offset="5" length="1">${v }</logic:iterate>
										</td>
									</tr>
								</logic:iterate>

								<%
										for (int i = 0; i < (Integer) request.getAttribute("maxNum")
										- ((List) request.getAttribute("rs")).size(); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<logic:iterate id="t" name="topTr" offset="0" scope="request">
										<td>
											&nbsp;
										</td>
									</logic:iterate>
								</tr>
								<%
								}
								%>

							</logic:notEmpty>
							<logic:empty name="rs">
								<%
								for (int i = 0; i < (Integer) request.getAttribute("maxNum"); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<logic:iterate id="t" name="topTr" offset="0" scope="request">
										<td>
											&nbsp;
										</td>
									</logic:iterate>
								</tr>
								<%
								}
								%>
							</logic:empty>
						</tbody>
					</table>
				</div>
			<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=tsbjForm"></jsp:include>
		</div>
		</html:form>
	</body>
</html>
