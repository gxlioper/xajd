<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/jyglDAO.js"></script>
		<script type="text/javascript">
			function scxysbh(){
				var count = $('count').value;
				jyglDAO.saveXybh(count,function(data){
					hiddenMessage(true,true);
					if (data){
						alert('操作成功!');
						$('search_go').click();
					}else {
						alert('请输入有效数字!');
					}
				});
			}
		</script>
	</head>
	<body onload="xyDisabled('xy');purviewControl();">

		<html:form action="/jygl" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<input type="hidden" name="userName" value="${userName }" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="session"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="${ tableName}" />
			<input type="hidden" id="realTable" name="realTable"
				value="${realTable }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />

			<!-- 大师的权限用的 -->
			<logic:present name="purview">
				<input type="hidden" id="purview" name="purview" value="${ purview }" /> 
				<input type="hidden" id=" operateBound " name="operateBound" value="${ operateBound }" />
			</logic:present>



			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#"
									id="btn_zj"
									onclick="viewTempDiv('协议书编号自动生成','inputDiv',300,140);"
									class="btn_zj"> 生成协议书编号 </a>
							</li>
							<li>
								<a href="#"
									id="btn_sc"
									onclick="deletedata('/xgxt/jygl.do?method=jyxybhwh&doType=del')"
									class="btn_sc"> 删除 </a>
							</li>
							<li>
								<a href="#" onclick="impAndChkData()" class="btn_dr" id="btn_dr"> 导入 </a>
							</li>
							<li>
								<a href="#"
									id="btn_dc"
									onclick="expData('/xgxt/jygl.do?method=jyxybhwh&doType=expData')"
									class="btn_dc"> 导出 </a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/jygl.do?method=jyxybhwh&doType=query')">
											查 询
										</button>
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>就业协议编号</th>
								<td>
									<html:text property="querylike_jyxybh" style="width:180px"/>
								</td>
								<th>是否已使用</th>
								<td>
									<html:select property="queryequals_sfsy" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="isNot" property="en" labelProperty="cn"/>
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
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
									<td>
										<input type="checkbox" disabled="true"/>
									</td>
									<logic:iterate id="tit" name="topTr" offset="2" scope="request">
										<td onclick="tableSort(this)">
											${tit}
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:notEmpty name="rs">
									<logic:iterate name="rs" id="s">
										<tr onclick="rowOnClick(this)">
											<td>
												<input type="checkbox" id="cbv" name="primarykey_cbv"
													value="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>"
													<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate> />
												<input type="hidden" value="<bean:write name="v" />" />
											</td>
											<logic:iterate id="v" name="s" offset="2">
												<td>
													${v }
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
									
									<%
												for (int i = 0; i < (Integer) request
												.getAttribute("maxNum")
												- ((List) request.getAttribute("rs")).size(); i++) {
									%>
									<tr>
										<td>
											<input type="checkbox" disabled="disabled" />
										</td>
										<logic:iterate id="t" name="topTr" offset="2" scope="request">
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
												for (int i = 0; i < (Integer) request
												.getAttribute("maxNum"); i++) {
									%>
									<tr>
										<td>
											<input type="checkbox" disabled="disabled" />
										</td>
										<logic:iterate id="t" name="topTr" offset="2" scope="request">
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
					<!--分页显示-->
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=jyglActionForm"></jsp:include>
					<!--分页显示-->
			</div>

			<!-- 弹出层，输入生成编号的个数 -->
			<div class="open_win01" style="display:none;" id="inputDiv">
				<table width='300' class='formlist'>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>生成个数
							</th>
							<td>
								<input type="text" maxlength="3" name="count" id="count"onkeyup="value=value.replace(/[^\d]/g,'')" />
							</td>
						</tr>
					<tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class='btn'>
									<button onclick='scxysbh();'>
										保存
									</button>
									<button onclick="hiddenMessage(true,true);return false;">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>


		</html:form>
		<logic:present name="message">
			<script language="javascript" defer="defer">
	         	alert("${message}");
	         </script>
		</logic:present>
	</body>
</html>
