<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
	</head>
	<body>
		<!--ѡ�-->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>���Ź���</a>
			</p>
		</div>
		<!--ѡ�-->
	
		<html:form action="/jyweb" method="post">
			<!-- �๦�ܲ�����һ -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a
								href="javascript:showTopWin('jywebUseCheckSession.do?method=newUpdate','800','600');"
								class="btn_zj"> ���� </a>
						</li>
						<li>
							<a  href="#"
								onclick="showUpdateWindow('primarykey_cbv','jywebUseCheckSession.do?method=newUpdate&type=wjbt&doType=update','800','600')"
								class="btn_xg"> �޸� </a>
						</li>
						<li>
							<a
								href="javascript:batchData('primarykey_cbv','jywebUseCheckSession.do?method=newsManage&doType=del','del')"
								class="btn_sc"> ɾ�� </a>
						</li>
						<li>
							<a
								href="javascript:expData('jywebUseCheckSession.do?method=newsManage&doType=expData')"
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
											onclick="allNotEmpThenGo('jywebUseCheckSession.do?method=newsManage&doType=query')">
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
								<td>
									��������
								</td>
								<td>
									<html:select property="queryequals_wjlx">
										<html:option value=""></html:option>
										<html:options collection="newLxList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<td>
									����
								</td>
								<td>
									<html:text property="querylike_wjbt" maxlength="25" />
								</td>
								<td>
									����ʱ��
								</td>
								<td>
									<html:text property="querygreaterequal_fbsj"
										style="width:100px"
										onblur='dateFormatChg(this)' styleId="querygreaterequal_fbsj"
										onclick="showCalendar(this.id,'y-mm-dd')" />
									-
									<html:text property="querylessequal_fbsj"
										style="width:100px"
										onclick="showCalendar(this.id,'y-mm-dd')"
										styleId="querylessequal_fbsj"
										onblur='dateFormatChg(this)' />
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<!-- From���� start-->
			<!---------------------���--�и�ѡ������ݱ�------------------->
			<div class="formbox">
				<h3 class="datetitle_01" >
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
										<logic:iterate id="v" name="s" offset="1" length="1">
											<td>
												<a onclick="window.open('/xgxt/jyweb.do?method=newsInfo&type=wjbt&pkValue=<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>');" 
													class="pointer" style="color:#0A63BF" href="#">
													${v }
												</a>
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="2">
											<td>
												${v }
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
										for (int i = 0 ; i < (Integer)request.getAttribute("maxNum") ; i++){
									
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
