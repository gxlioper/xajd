<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
	</head>
	<body>

		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>У����Ƹ����</a>
			</p>
		</div>

		<html:form action="/jyweb" method="post">
			<input type="hidden" name="realTable" id="realTable" value="${realTable }" />

			<!-- �๦�ܲ�����һ -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#"
								onclick="refreshForm('jywebUseCheckSession.do?method=ztzpAdd');"
								class="btn_zj"> ���� </a>
						</li>
						<li>
							<a href="#" 
							   onclick="showUpdateWindow('primarykey_cbv','jyweb.do?method=ztzpUpdate&doType=update','','',true)" 
							   class="btn_xg"> �޸� </a>
						</li>
						<li>
							<a
								href="javascript:batchData('primarykey_cbv','jywebUseCheckSession.do?method=ztzpManage&doType=del','del')"
								class="btn_sc"> ɾ�� </a>
						</li>
						<li>
							<a href="javascript:impAndChkData();" class="btn_dr"> ���� </a>
						</li>
						<li>
							<a
								href="javascript:expData('jywebUseCheckSession.do?method=ztzpManage&doType=expData')"
								class="btn_dc"> ���� </a>
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
											onclick="allNotEmpThenGo('jywebUseCheckSession.do?method=ztzpManage&doType=query')">
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
									��Ƹ����
								</th>
								<td>
									<html:text property="querylike_zpzt" style="width:175px"></html:text>
								</td>
								<th>
									��Ƹʱ��
								</th>
								<td>
									<html:text property="querygreaterequal_zpsj"
										styleId="querygreaterequal_zpsj"
										onclick="showCalendar(this.id,'y-mm-dd')"
										onblur="dateFormatChg(this);" style="width:80px"></html:text>
									-
									<html:text property="querylessequal_zpsj"
										styleId="querylessequal_zpsj"
										onclick="showCalendar(this.id,'y-mm-dd')"
										onblur="dateFormatChg(this);" style="width:80px"></html:text>
								</td>
								<th>
									����ʱ��
								</th>
								<td>
									<html:text property="querygreaterequal_fbsj"
										styleId="querygreaterequal_fbsj"
										onclick="showCalendar(this.id,'y-mm-dd')"
										onblur="dateFormatChg(this);" style="width:80px"></html:text>
									-
									<html:text property="querylessequal_fbsj"
										styleId="querylessequal_fbsj"
										onclick="showCalendar(this.id,'y-mm-dd')"
										onblur="dateFormatChg(this);" style="width:80px"></html:text>
								</td>
								
								
							</tr>

							<tr>
								<th>
									��Ƹ�ص�
								</th>
								<td>
									<html:text property="querylike_zpdd" style="width:175px"></html:text>
								</td>
								<th>
									���״̬
								</th>
								<td>
									<html:select property="queryequals_shzt" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="shztList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<th>
								</th>
								<td>
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
					<span> ��ѯ���&nbsp;&nbsp; <logic:notEmpty name="rs">
							<font color="blue">��ʾ��������ͷ��������</font>
						</logic:notEmpty> </span>
				</h3>


				<table summary="" class="dateline" width="100%">
					<thead>
						<tr>
							<td>
								<input type="checkbox" disabled="disabled" style="height:17.5px" />
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
								<tr>
									<td>
										<input type="checkbox" id="cbv" name="primarykey_cbv"
											<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>
											value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
									</td>
									<td>
										<a
											onclick="showTopWin('/xgxt/jyweb.do?method=ztzpUpdate&doType=show&pkValue=<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>',800,600);"
											class="pointer" style="color:#0A63BF" href="#"> 
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
				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/jygl/jyweb/turnPage.jsp?form=jyglActionForm"></jsp:include>
				<!--��ҳ��ʾ-->
			</div>
		</html:form>

		<logic:present name="message">
			<script>
				alert("${message}");
			</script>
		</logic:present>
	</body>
</html>
