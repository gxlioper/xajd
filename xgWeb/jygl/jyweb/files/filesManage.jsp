<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�ļ�����</a>
			</p>
		</div>
	
		<html:form action="/jyweb" method="post">
			<input type="hidden" name="realTable" id="realTable"
				value="${realTable }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<!-- �๦�ܲ�����һ -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a
								href="javascript:showTopWin('jywebUseCheckSession.do?method=fileUpdate','500','300');"
								class="btn_zj"> ���� </a>
						</li>
						<li>
							<a
								href="javascript:batchData('primarykey_cbv','jywebUseCheckSession.do?method=filesManage&doType=del','del')"
								class="btn_sc"> ɾ�� </a>
						</li>
					</ul>
				</div>
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('jywebUseCheckSession.do?method=filesManage&doType=query')">
											�� ѯ
										</button>
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									�ļ���
								</th>
								<td>
									<html:text property="querylike_wjm" maxlength="100"></html:text>
								</td>
								<th>
									�ϴ�ʱ��
								</th>
								<td>
									<html:text property="querygreaterequal_scsj"
										styleId="querygreaterequal_scsj"
										onclick="showCalendar(this.id,'y-mm-dd')"
										onblur='dateFormatChg(this)'></html:text>
									-
									<html:text property="querylessequal_scsj"
										styleId="querylessequal_scsj"
										onclick="showCalendar(this.id,'y-mm-dd')"
										onblur='dateFormatChg(this)'></html:text>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<!-- From���� start-->
			<!---------------------���--�и�ѡ������ݱ�------------------->
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; 
						<logic:notEmpty name="rs">
							<font color="blue" >��ʾ��������ͷ��������</font>
						</logic:notEmpty>
					</span>
				</h3>

				
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr>
								<td>
									<input type="checkbox" disabled="true" style="height:17.5px"/>
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
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
										</td>
										<td>
											<a
												onclick="window.open('/xgxt/jyweb.do?method=fileView&pkValue=<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>');"
												class="pointer" style="color:#0A63BF" href="#"> 
													<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>
											</a>
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
					<!--��ҳ��ʾ-->
					<jsp:include flush="true"
						page="/jygl/jyweb/turnPage.jsp?form=jyglActionForm"></jsp:include>
					<!--��ҳ��ʾ-->
				</div>
			<logic:present name="message">
				<script defer="defer">
					alert("${message}");
				</script>
			</logic:present>
		</html:form>
	</body>
</html>
