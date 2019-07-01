<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var tr = jQuery('#shjg');
				var sel = jQuery("select",tr);
				var opt = jQuery('#shzt').attr('innerHTML');
				
				for (var i = 0 ; i < sel.length; i++){
					jQuery(sel[i]).append(opt);
				}
				
				setShzt();
			})
			
			function setShzt(){
				var tempShzt = jQuery('#tempShzt').val();
				
				if ('' != tempShzt){
					var shzt = tempShzt.replace('[','').replace(']','').split(',');
					for (var i = 0 ; i < shzt.length ; i++){
						var shid = 	'#'+shzt[i].split(':')[0].trim();
						var shValue = shzt[i].split(':')[1].trim();
						
						jQuery(shid).val(shValue);
					}
				}
			}
		</script>
	</head>

	<body onload="xyDisabled('xy');">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/njjsXjbj" method="post">
			<input type="hidden" name="userName" id="userName" value="${userName}" />
			<input type="hidden" name="userType" id="userType" value="${userType}" />
			<input type="hidden" name="userDep"  id="userDep"  value="${userDep}" />
		
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<input type="hidden" name="realTable" id="realTable"
				value="${realTable }" />
			<input type="hidden"  id="tempShzt" value="${tempShzt}" />

			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" id="btn_zj"
									onclick="showTopWin('njjsXjbj.do?method=xjbjsq&doType=add',700,500)"
									class="btn_zj"> 增加 </a>
							</li>
							<li>
								<a href="#" id="btn_xg"
									onclick="showUpdateWindow('primarykey_cbv','njjsXjbj.do?method=xjbjUpdate',700,500)"
									class="btn_xg"> 修改 </a>
							</li>
							<li>
								<a href="#" id="btn_sc"
									onclick="batchData('primarykey_cbv','njjsXjbj.do?method=xjbjManage&doType=del','del')"
									class="btn_sc"> 删除 </a>
							</li>
							<li>
								<a href="#" id="btn_dc"
									onclick="expData('njjsXjbj.do?method=xjbjManage&doType=exp')"
									class="btn_dc"> 导出 </a>
							</li>
							<li>
								<a href="#" id="btn_dy"
									onclick='tipsWindown("系统提示","id:expDiv","340","120","true","","true","id");'
									class="btn_dy"> 打印报表 </a>
							</li>
							
							<%--
							<li>
								<a href="#" id="btn_sc" onclick="impAndChkData();"
									class="btn_dr"> 导入 </a>
							</li>
							--%>
							
							
						</ul>
					</div>
				</logic:equal>
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="bz">
										<%--<label>
											<html:checkbox onclick="hiddenTr(this)" property="moreTerm"
												styleId="moreTerm" />
											<strong>更多条件</strong>
										</label>
									--%>
									</div>
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('njjsXjbj.do?method=xjbjManage&doType=query')">
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
									<html:select property="nj" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm" style="width:180px"
										styleId="xy" onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm" styleId="zy"
										onchange="initBjList()" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
							</tr>
							
							
							<tr>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" styleId="bj"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
								<th>
									申请时间
								</th>
								<td colspan="3">
									<html:text property="sqkssj" style="width:80px"
										onclick="return showCalendar('sqkssj','yyyy-MM');"></html:text>
									-
									<html:text property="sqjssj" style="width:80px"
										onclick="return showCalendar('sqjssj','yyyy-MM');"></html:text>
										
									<html:select property="shzt" styleId="shzt" style="display:none">
										<html:option value=""></html:option>
										<html:options collection="shjgList" property="en" labelProperty="cn"/>
									</html:select>
								</td>
							</tr>
							<tr id="shjg">
								<logic:iterate id="g" name="gwmc">
									<th>${g }</th>
									<td>
										<select name="${g }" style="width:180px" id="${g }"></select>
									</td>
								</logic:iterate>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:notEmpty name="rs">
							<font color="blue">提示：单击表头可以排序</font>
						</logic:notEmpty> </span>
				</h3>
				<div class="con_overlfow">
					<table summary="" class="dateline tablenowrap" align=""
						width="100%">
						<thead>
							<tr>
								<td>
									<input type="checkbox" disabled="disabled" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="1" scope="request">
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
												<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>
												value="<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>" />
										</td>
										<td>
											<logic:iterate id="x" name="s" offset="2" length="1">
												<a
												href="javascript:showTopWin('njjsXjbj.do?method=xjbjDgsh&doType=view&pkValue=<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>',700,500)"
												class="pointer" style="color:#0A63BF"> 
													${x }
												</a>
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="3">
											<td>
												<logic:equal name="v" value="未审核">
													<p>
														<img src="<%=stylePath%>images/ico_dsh.gif" width="52"
															height="18" />
													</p>
												</logic:equal>
												<logic:equal name="v" value="通过">
													<p>
														<img src="<%=stylePath%>images/ico_shtg.gif" width="52"
															height="18" />
													</p>
												</logic:equal>
												<logic:equal name="v" value="不通过">
													<p>
														<img src="<%=stylePath%>images/ico_shbtg.gif" width="52"
															height="18" />
													</p>
												</logic:equal>
												<logic:equal name="v" value="退回">
													<p>
														<img src="<%=stylePath%>images/ico_shth.gif" width="52"
															height="18" />
													</p>
												</logic:equal>
												<logic:equal name="v" value="需重审">
													<p>
														<img src="<%=stylePath%>images/ico_shxcs.gif" width="52"
															height="18" />
													</p>
												</logic:equal>

												<logic:notEqual name="v" value="退回">
													<logic:notEqual name="v" value="需重审">
														<logic:notEqual name="v" value="不通过">
															<logic:notEqual name="v" value="通过">
																<logic:notEqual name="v" value="未审核">
													${v }
												</logic:notEqual>
															</logic:notEqual>
														</logic:notEqual>
													</logic:notEqual>
												</logic:notEqual>
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
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xjbjForm"></jsp:include>
			</div>
			<logic:present name="message">
				<script>
					alert("${message}");
				</script>
			</logic:present>
		
		
		
			<div id="expDiv" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>请选择打印年月</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									<span class="red">*</span>时间
								</th>
								<td>
									<html:text property="sqsj" readonly="true"
										onclick="return showCalendar('sqsj','yyyy-MM');"></html:text>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<button name="打印" onclick="if('' != $('sqsj').value){expData('njjsXjbj.do?method=xjbjPrint&sqsj='+$('sqsj').value)}">
											打 印
										</button>
										<button name="取消" onclick="closeWindown();return false;">
											取 消
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</html:form>
		
		<%@ include file="/comm/other/exception.jsp"%>
	</body>
</html>
