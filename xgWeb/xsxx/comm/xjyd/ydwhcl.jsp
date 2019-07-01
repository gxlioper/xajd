<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.*" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript" src="js/xsxx/comm/xjyd/xjyd.js"></script>
		<script>
			function szclwh(){
				var length = jQuery('input[name=primarykey_cbv]:checked').length;
				
				if (length > 0){
					tipsWindown("系统提示","id:clwhDiv","350","150","true","","true","id");
				} else {
					alertInfo('请选择您要设置处理文号的记录！');
				}
			}
			
			function saveClwh(){
				var clwh = jQuery('#clwh').val();
				
				if (clwh == ''){
					alertInfo('请填写处理文号！');
					return false;
				}
				
				confirmInfo('您将要为选择的异动记录设置处理文号:'+clwh+",确定要这样做吗?",function(t){
					if (t == 'ok'){
						document.forms[0].action='xsxxXjyd.do?method=saveClwh&clwh='+clwh;
						document.forms[0].submit();
					}
				});
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
			<input type="hidden" id="tableName" name="tableName" value="${ tableName}" />
			<input type="hidden" id="realTable" name="realTable" value="bks_xjydxx" />
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
									id = "btn_sz"
									onclick="szclwh();"
									class="btn_sz"> 设置处理文号 </a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						
						<tbody>
							<tr>
								<th>
									学号
								</th>
								<td>
									<logic:equal value="stu" name="userType">
										<html:text property="xh" style="width:120px" value="${userName }" readonly="true"></html:text>
									</logic:equal>
									<logic:notEqual value="stu" name="userType">
										<html:text property="xh" style="width:120px"></html:text>
									</logic:notEqual>
								</td>
								<th>姓名</th>
								<td>
									<html:text property="xm" style="width:120px"></html:text>
								</td>
								<th>
									异动类别
								</th>
								<td>
									<html:select property="ydlbm" style="width:120px">
										<html:option value=""></html:option>
										<html:options collection="ydlbAllList" property="ydlbm" labelProperty="ydlbmc"/>
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
										<logic:present name="ydqBjList">
											<html:options collection="ydqBjList" property="bjdm"
												labelProperty="bjmc" />
										</logic:present>
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
										<logic:present name="ydhBjList">
											<html:options collection="ydhBjList" property="bjdm"
												labelProperty="bjmc" />
										</logic:present>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>学年</th>
								<td>
									<html:select property="xn">
										<html:options collection="xnList" property="xn" labelProperty="xn"/>
									</html:select>
								</td>
								<th>是否已设置<br/>处理文号</th>
								<td>
									<html:select property="sfcl">
										<html:option value=""></html:option>
										<html:option value="是">是</html:option>
										<html:option value="否">否</html:option>
									</html:select>
								</td>
								<td colspan="2">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('xsxxXjyd.do?method=ydwhcl&doType=query')">
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
							<font color="blue">提示：单击表头可以排序</font>
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
												alt="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"
												value="<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>"/>
										</td>
										
											<td>
												<a
												href="javascript:showTopWin('xsxxXjyd.do?method=xjydUpdate&doType=view&pkValue=<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>','750','550');"
												class="pointer" style="color:#0A63BF"> 
													<logic:iterate id="v" name="s" offset="2" length="1">${v }</logic:iterate>
												</a>
											</td>
										
										<logic:iterate id="v" name="s" offset="3">
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
			<div id="clwhDiv" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>请输入处理文号</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									<span class="red">*</span>处理文号
								</th>
								<td>
									<input type="text" name="clwh" id="clwh" maxlength="16"
										onkeyup="value=value.replace(/[+&%#]/g,'');"
									/>
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
										<button type="button" name="提 交" onclick="saveClwh();">
											确 定
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
