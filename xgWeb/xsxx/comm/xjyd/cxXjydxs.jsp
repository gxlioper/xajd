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
		//查询学生信息
		function cxXjydxs(){
			var sqkssj = document.getElementById("sqkssj").value;
			var sqjssj = document.getElementById("sqjssj").value;
			var tjfw = document.getElementById("tjfw").value;
			var ydlb = document.getElementById("ydlbm").value;
			var fwcs = document.getElementById("fwcs").value;
			var url='xsxxXjyd.do?method=cxXjydxs&sqkssj='+sqkssj+'&sqjssj='+sqjssj+'&tjfw='+tjfw+'&ydlbm='+ydlb+'&fwcs='+fwcs+'';
			refreshForm(url);
			}
		</script>
		
	</head>
	<body>

		<html:form action="/xsxxXjyd" method="post">
			<input type="hidden" id="isFdy"     name="isFdy"  	 value="${isFdy }" />
			<input type="hidden" id="userName"  name="userName"  value="${userName }" />
			<input type="hidden" id="userType"  name="userType"  value="${userType }" />
			<input type="hidden" id="tableName" name="tableName" value="${ tableName}" />
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" name="sqkssj" id="sqkssj" value="${sqkssj}" />
			<input type="hidden" name="sqjssj" id="sqjssj"  value="${sqjssj}" />
			<input type="hidden" name="tjfw" id="tjfw" value="${tjfwdm}" />
			<input type="hidden" name="ydlbm" id="ydlbm" value="${ydlbm }" />
			<input type="hidden" name="fwcs" id="fwcs" value="${fwcs }" />
			<input type="hidden" name="search_go" id="search_go" onclick="cxXjydxs()"/>

			<div class="formbox">
				<h3 class="datetitle_01">
					<span>
					 异动时间:${sqkssj}--${sqjssj}&nbsp;&nbsp;&nbsp;&nbsp; 
					异动类别:${ydlbmc}&nbsp;&nbsp;&nbsp;&nbsp;统计范围:${tjfw}&nbsp;&nbsp;&nbsp;&nbsp;范围条件:${fwcsmc}
					</span>
				</h3>


				<div class="con_overlfow">
					<table class="dateline tablenowrap" width="100%">
						<thead>
							<tr>
								<logic:iterate id="tit" name="topTr" offset="0" scope="request">
									<td onclick="tableSort(this)">
										${tit}
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s"  indexId="indexs">
									<tr>
										<logic:iterate id="v" name="s" offset="0" indexId="index">
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
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=xsxxXjydForm"></jsp:include>
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
