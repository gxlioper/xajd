<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
		<script type="text/javascript" src="js/jygl/jygl.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/jyglDAO.js"></script>
		<script type="text/javascript">

			function batchSaveData(){
			
				if (!$('save_jyxybh')){
					return false;
				}
				refreshForm("/xgxt/jygl.do?method=xyslqdj&doType=save");
			}
			
			function xybhZdtc(){
				
				var sybh = $('sybh').value;
				var xybh = document.getElementsByName('save_jyxybh');			
				
				if (0==xybh.length){
					alert("无可填充记录!");
					return false;
				}
				
				if (0==sybh){
					alert("就业协议编号已使用完，请先维护协议书编号!");
					return false;
				}
				
				jyglDAO.getWsybh(function(data){
					var wsybh = data.split("@");
					
					var i = 0 ; 
					var n = 0;
					
					while (i < xybh.length){
						if ("" == xybh[i].value){
							if (n < wsybh.length){
								xybh[i].value = wsybh[n];
								n++;
							}
						}
						
						i++;
					}
				});
			}
		
	</script>

	</head>
	<body onload="xyDisabled('xy');purviewControl();">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/jygl" method="post">
			<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<input type="hidden" id="userName" name="userName"
				value="${userName }" />
			<input type="hidden" id="userDep" name="userDep" value="${userDep }" />
			<input type="hidden" id="tableName" name="tableName"
				value="${ tableName}" />
			<input type="hidden" id="realTable" name="realTable"
				value="${realTable }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<input type="hidden" value="${sybh }" name="sybh" id="sybh" />

			<input type="hidden" name="isComm" value="true"/> 
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
								<a href="#" onclick="batchSaveData();" class="btn_ccg" id="btn_bc"> 保存 </a>
							</li>
							<li>
								<a href="#" onclick="xybhZdtc();" class="btn_xg" id="btn_tc"> 协议编号自动填充 </a>
							</li>
							<li>
								<a href="#"
									id="btn_sc"
									onclick="deletedata('/xgxt/jygl.do?method=xyslqdj&doType=del');"
									class="btn_sc"> 删除 </a>
							</li>
							<li>
								<a href="#" onclick="impAndChkData();" class="btn_dr" id="btn_dr"> 导入 </a>
							</li>
							<li>
								<a href="#" class="btn_dc" onclick="configureExportData();return false;" id="btn_dc">导出数据</a>
							</li>
							<li>
								<a href="#" class="btn_qx" onclick="choiceFields();return false;" id="btn_qx">导出字段确认</a>
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
											onclick="allNotEmpThenGo('/xgxt/jygl.do?method=xyslqdj&doType=query')">
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
								<th>
									年级
								</th>
								<td>
									<html:select property="queryequals_nj" styleId="nj"
										onchange="initZyList();initBjList()" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									学号
								</th>
								<td>
									<logic:equal value="stu" name="userType" scope="session">
										<html:text property="querylike_xh" maxlength="20"
											style="width:155px" value="${userName }" readonly="true"></html:text>
									</logic:equal>
									<logic:notEqual value="stu" name="userType" scope="session">
										<html:text property="querylike_xh" maxlength="20"
											style="width:155px"></html:text>
									</logic:notEqual>

								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="querylike_xm" maxlength="20"
										style="width:155px"></html:text>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="queryequals_xydm"
										onchange="initZyList();initBjList()" styleId="xy"
										style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="queryequals_zydm"
										onchange="initBjList()" styleId="zy" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="queryequals_bjdm" styleId="bj"
										style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>领取情况</th>
								<td>
									<html:select property="queryequals_lqqk" style="width:160px">
										<html:option value=""></html:option>
										<html:option value="已领取">已领取</html:option>
										<html:option value="未领取">未领取</html:option>
									</html:select>
								</td>
								<th>就业协议编号</th>
								<td>
									<html:text property="querylike_jyxybh" style="width:175px"></html:text>
								</td>
								<th>
									届
								</th>
								<td>
									<html:select property="queryequals_je" styleId="je"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="nfList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>



			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:notEmpty name="rs">
							<font color="blue">提示：单击表头可以排序,剩余就业协议编号<font color="red">${sybh
									}</font>个</font>
						</logic:notEmpty> </span>
				</h3>


				<div class="con_overlfow">
					<table summary="" class="dateline tablenowrap" align=""
						width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td nowrap>
									<input type="checkbox" name="cb" onclick="selectAll()" disabled="true"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="1" scope="request">
									<td onclick="tableSort(this)" nowrap>
										${tit}
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s" indexId="n">
									<tr onclick="rowOnClick(this)">
										<td align=center>
											<input type="checkbox" id="cbv" name="primarykey_cbv"
												value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
											<html:hidden property="save_xh" value="${v }" />
										</td>
										<logic:iterate id="v" name="s" offset="1" length="6">
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="7" length="1">
											<td>
												<logic:equal value="未领取" name="v">
													<input type="radio" name="save_lqqk${n }" value="未领取"
														checked="checked" />未领取
													<input type="radio" name="save_lqqk${n }" value="已领取" />已领取
												</logic:equal>
												<logic:equal value="已领取" name="v">
													<input type="radio" name="save_lqqk${n }" value="未领取" />未领取
													<input type="radio" name="save_lqqk${n }" value="已领取"
														checked="checked" />已领取
												</logic:equal>
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="8" length="1">
											<td>
												<html:text property="save_jyxybh" maxlength="20"
													style="width:100px" value="${v }" readonly="true"></html:text>
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
								
								<%
									for (int i = 0 ; i < (Integer)request.getAttribute("maxNum") - ((List)request.getAttribute("rs")).size() ; i++){
									
								 %>
									<tr>
										<td>
											<input type="checkbox" disabled="disabled"/>
										</td>
										<logic:iterate id="t" name="topTr" offset="1" scope="request">
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
									<logic:iterate id="t" name="topTr" offset="1" scope="request">
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
		</html:form>
		<logic:present name="message">
			<script language="javascript">
	         	alert("${message}");
	         </script>
		</logic:present>
	</body>
</html>
