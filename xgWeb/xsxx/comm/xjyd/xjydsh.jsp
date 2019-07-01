<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.*" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript" src="js/xsxx/comm/xjyd/xjyd.js"></script>
		<script type="text/javascript">
			function plsh(){
				tipsWindown("系统提示","id:shDiv","350","200","true","","true","id");
			}
		</script>
	</head>
	<body>

		<html:form action="/xsxxXjyd" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<input type="hidden" id="isFdy"     name="isFdy"  	 value="${isFdy }" />
			<input type="hidden" id="userName"  name="userName"  value="${userName }" />
			<input type="hidden" id="userType"  name="userType"  value="${userType }" />
			<input type="hidden" id="tableName" name="tableName" value="${tableName}" />
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			
			<input type="hidden" name="ydhxyV" value="" />
			<input type="hidden" name="ydhzyV" value="" />
			<input type="hidden" name="ydhbjV" value="" />
			
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
									id="btn_sh"
									onclick="showAuditingWindow('primarykey_cbv','xsxxXjyd.do?method=xjydDgshcx',750,550);"
									class="btn_sh">审核 </a>
							</li>
							<%--<li>
								<a href="#"
									id="btn_up"
									onclick="doSubmit('primarykey_cbv','xsxxXjyd.do?method=plshTj')"
									class="btn_up">数据提交 </a>
							</li>
							--%><li>
								<a href="#"
									id="btn_cs"
									onclick="showViewWindow('primarykey_cbv','xsxxXjyd.do?method=lcgzXjyd','550','350',false,'请勾选一条记录!')"
									class="btn_cs">流程跟踪 </a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						
						<tbody>
							<tr>
								<th>学号</th>
								<td>
									<html:text property="xh"></html:text>
								</td>
								<th>姓名</th>
								<td>
									<html:text property="xm"></html:text>
								</td>
								<th>异动类别</th>
								<td>
									<html:hidden property="save_shlcid" styleId="shlcid"/>
									<html:select property="ydlbm" styleId="ydlbm" style="width:180px" >
										<html:option value=""></html:option>
										<html:options collection="ydlbList" property="ydlbm" labelProperty="ydlbmc"/>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>申请时间</th>
								<td>
									<html:text property="sqkssj" style="width:80px"
										onblur="dateFormatChg(this)"
										onclick="return showCalendar('sqkssj','y-mm-dd');"></html:text>
									-
									<html:text property="sqjssj" style="width:80px"
										onblur="dateFormatChg(this)"
										onclick="return showCalendar('sqjssj','y-mm-dd');"></html:text>
								</td>
								<th>异动前年级</th>
								<td>
									<html:select property="ydqnj" styleId="nj"
										onchange="initZyList();initBjList()" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>异动后年级</th>
								<td>
									<html:select property="ydhnj" styleId="ydhnj"
										onchange="initYdhZyList();initYdhBjList()" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>异动前<bean:message key="lable.xsgzyxpzxy" /></th>
								<td>
									<html:select property="ydqxydm"
										onchange="initZyList();initBjList();" styleId="xy"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="ydqXyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>异动前专业</th>
								<td>
									<html:select property="ydqzydm"
										onchange="initBjList();" styleId="zy" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="ydqZyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>异动前班级</th>
								<td>
									<html:select property="ydqbdm" styleId="bj"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="ydqBjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>异动后<bean:message key="lable.xsgzyxpzxy" /></th>
								<td>
									<html:select property="ydhxydm"
										onchange="initYdhZyList();initYdhBjList()" styleId="ydhxy"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="ydhXyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>异动后专业</th>
								<td>
									<html:select property="ydhzydm"
										onchange="initYdhBjList();" styleId="ydhzy" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="ydhZyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>异动后班级</th>
								<td>
									<html:select property="ydhbdm" styleId="ydhbj"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="ydhBjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr>
							<th>
								学年
							</th>
							<td>
								<html:select property="xn" style="width:180px" 
									styleId="xn" styleClass="select" >
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>	
							<th>
								学期
							</th>
							<td>
								<html:select property="xq" style="width:180px" 
									styleId="xq" styleClass="select" >
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
							<th>
							</th>
							<td>
							</td>
						</tr>
							<tr>
								<%--<th>审核结果</th>
								<td>
									<html:select property="shzt">
										<html:option value=""></html:option>
										<html:options collection="shztList" property="en" labelProperty="cn"/>
									</html:select>
								</td>
								<th>是否提交</th>
								<td>
									<html:select property="sftj">
										<html:option value=""></html:option>
										<html:option value="是">是</html:option>
										<html:option value="否">否</html:option>
									</html:select>
								</td>
								
								--%><%--<th>审核范围</th>
								<td colspan="3">
									异动前本<bean:message key="lable.xb" /><input type="radio" name="isbxy" value="true" checked="true"/>
									异动后本<bean:message key="lable.xb" /><input type="radio" name="isbxy" value="false"/>
								</td>
								--%><td colspan="6">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('xsxxXjyd.do?method=xjydshquery&doType=query')">
											查 询
										</button>
										<button type="button" onclick="searchReset();return false;">
										 	重 置
										</button>
									</div>
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
							<font color="blue">提示：以下为您需要审核的数据，单击表头可以排序</font>
						</logic:notEmpty> 
					</span>
				</h3>


				<div class="con_overlfow">
					<table class="dateline tablenowrap" width="100%">
						<thead>
							<tr>
								<td>
									<input type="checkbox" disabled="disabled" />
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
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"/>
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td>
												${v }
											</td>
										</logic:iterate>
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
				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=xsxxXjydForm"></jsp:include>
				<!--分页显示-->
			</div>
			
			
			<!-- 批量审核弹出层 -->
			<div id="shDiv" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>请填写审核信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									审核状态
								</th>
								<td>
									<html:select property="shjg" style="width:120px" styleId="shjg">
										<html:options collection="shztList" property="en" labelProperty="cn"/>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									审核意见<br/>
									<font color="red"><限500字></font>
								</th>
								<td>
									<html:textarea property="shyj" styleId="shyj" style="width:95%" rows="3" onblur="chLeng(this,500)"></html:textarea>
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
										<button type="button" name="提 交" onclick="if(confirm('您确定要批量提交吗?')){refreshForm('xsxxXjyd.do?method=plshXjyd&sftj=是&shjg='+$('shjg').value+'&shyj='+$('shyj').value)}">
											提 交
										</button>
										<button type="button" name="取消" onclick="closeWindown();return false;">
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
		<logic:present name="message">
			<script language="javascript">
         		alert("${message}");
         	</script>
		</logic:present>
	</body>
</html>
