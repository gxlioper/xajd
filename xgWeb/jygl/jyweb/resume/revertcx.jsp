<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
	</head>
	<body>
		<html:form action="/jyweb" method="post">
			<!-- �๦�ܲ�����һ -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a
								onclick="showUpdateWindow('primarykey_cbv','jywebUseCheckSession.do?method=revertcx&doType=view',600,400,'','�빴ѡһ����Ҫ�鿴������')"
								href="#" class="btn_ck"> ��Ϣ�鿴</a>
						</li>
						<li>
							<a
								href="javascript:batchData('primarykey_cbv','jywebUseCheckSession.do?method=revertcx&doType=del','del')"
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
											onclick="allNotEmpThenGo('jywebUseCheckSession.do?method=revertcx&doType=query')">
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
									��λ����
								</th>
								<td>
									<input type="text" name="querylike_dwmc" />
								</td>
								<th>
									��λ����
								</th>
								<td>
									<html:text property="querylike_gwmc">
									</html:text>
								</td>
							</tr>
							<tr>
								<th>
									�ظ�ʱ��
								</th>
								<td>
									<html:text styleId="fbsj1" property="querygreaterequal_sj"
										onclick="showCalendar(this.id,'y-mm-dd')"
										onblur="dateFormatChg(this);"></html:text>
									-
									<html:text styleId="fbsj2" property="querylessequal_sj"
										onclick="showCalendar(this.id,'y-mm-dd')"
										onblur="dateFormatChg(this);"></html:text>
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
					<span> ��ѯ���&nbsp;&nbsp; </span>
				</h3>

				<table summary="" class="dateline" align="" width="100%">
					<thead>
						<tr>
							<td>
								<input type="checkbox" disabled="true" style="height:17.5px" />
							</td>
							<logic:iterate id="tit" name="topTr" offset="1" scope="request">
								<td id="${tit.en}" onclick="tableSort(this)">
									${tit.cn}
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
										<input type="hidden"
											value="<logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>" />
										<a
											onclick="showTopWin('jywebUseCheckSession.do?method=revertcx&doType=view&pkValue=<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>',600,400);"
											class="pointer" style="color:#0A63BF" href="#"> <logic:iterate
												id="v" name="s" offset="1" length="1">${v}</logic:iterate> </a>
									</td>

									<logic:iterate id="v" name="s" offset="2">
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
								for (int i = 0; i < (Integer) request.getAttribute("maxNum"); i++) {
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
		</html:form>
	</body>
</html>
