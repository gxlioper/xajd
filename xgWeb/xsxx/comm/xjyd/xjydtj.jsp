<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.*" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
			function tongji(url){
				var sqkssj = document.getElementById("sqkssj").value;
				var sqjssj = document.getElementById("sqjssj").value;
				if(""==sqkssj||null==sqkssj||""==sqjssj||null==sqjssj){
					alertInfo("请输入异动时间！");
					return false;
					}
				refreshForm(url);
				}

				//查询学生信息
				function cxXjydxs(obj1,obj2){
					var sqkssj = document.getElementById("sqkssj").value;
					var sqjssj = document.getElementById("sqjssj").value;
					var tjfw = document.getElementById("tjfw").value;
					var ydlb = document.getElementById(obj2).value;
					var url='xsxxXjyd.do?method=cxXjydxs&sqkssj='+sqkssj+'&sqjssj='+sqjssj+'&tjfw='+tjfw+'&ydlbm='+ydlb+'&fwcs='+obj1+'';
					showTopWin(url,'800','430');
					}

				//导出
				function exportData(){
					var sqkssj = document.getElementById("sqkssj").value;
					var sqjssj = document.getElementById("sqjssj").value;
					var tjfw = document.getElementById("tjfw").value;
					if(""==sqkssj||null==sqkssj||""==sqjssj||null==sqjssj){
						alertInfo("请先输入异动时间！");
						return false;
						}
					document.forms[0].action = 'xsxxXjyd.do?method=exportData';
					document.forms[0].target = "_blank";
					document.forms[0].submit();
					document.forms[0].target = "_self";
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
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			
			<!-- 大师的权限用的 -->
			<logic:present name="purview">
				<input type="hidden" id="purview" name="purview" value="${ purview }" /> 
				<input type="hidden" id=" operateBound " name="operateBound" value="${ operateBound }" />
			</logic:present>
			
				<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
				<li><a href="#" class="btn_dc" onclick="exportData();return false;">导出数据</a></li>
				</div>
				
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						
						<tbody>
							<tr>
								<th>
									异动时间
								</th>
								<td>
									<html:text property="sqkssj" style="width:80px"
										onblur="dateFormatChg(this)"
										onclick="return showCalendar('sqkssj','y-mm-dd');"></html:text>
									--
									<html:text property="sqjssj" style="width:80px"
										onblur="dateFormatChg(this)"
										onclick="return showCalendar('sqjssj','y-mm-dd');"></html:text>
								</td>
								<th>
									统计范围
								</th>
								<td>
									<html:select property="tjfw">
										<html:option value="ydqxydm"><bean:message key="lable.xb" /></html:option>
										<html:option value="ydqzydm">专业</html:option>
										<html:option value="ydqbdm">班级</html:option>
										<html:option value="xn">学年</html:option>
										<html:option value="ydqnj">年级</html:option>
									</html:select>
								</td>

								<td>
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="tongji('xsxxXjyd.do?method=xjydtj&doType=query')">
											统 计
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
					<span> 统计结果&nbsp;&nbsp; 
					</span>
				</h3>


				<div class="con_overlfow">
					<table class="dateline tablenowrap" width="100%">
						<thead>
							<tr>
								<logic:iterate id="tit" name="topTr" offset="0" scope="request"  indexId="index">
									<td>
										${tit.cn}
										<input type="hidden" id="top_${index}" value="${tit.en}"/>
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s"  indexId="indexs">
									<tr>
										<logic:iterate id="v" name="s" offset="1" indexId="index">
												<td>
												<logic:equal name="index" value="1">
													${v }
													<logic:equal name="indexs" value="${indexnum}">
													合计
													</logic:equal>
												</logic:equal>
												<logic:equal name="index" value="2">
												${v }
												</logic:equal>
											<logic:notEqual name="index" value="1">
												<logic:notEqual name="index" value="2">
												
												<a href="#" class="name" onclick="cxXjydxs('${s[0]}','top_${index-1}');return false;"><font color="blue">${v } </font></a>
												</logic:notEqual>
											</logic:notEqual>
											</td>
										</logic:iterate>
											
									</tr>
								</logic:iterate>

								<%
											for (int i = 0; i < (Integer) request.getAttribute("maxNum")
											- ((List) request.getAttribute("rs")).size(); i++) {
								%>
								<tr>
							
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
				
			</div>
			
		</html:form>
		<logic:present name="message">
			<script language="javascript">
         		alert("${message}");
         	</script>
		</logic:present>
	</body>
</html>
