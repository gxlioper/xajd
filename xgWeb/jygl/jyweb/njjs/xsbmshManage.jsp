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
				<em>���ĵ�ǰλ��:</em><a>ѧ���������</a>
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
							<a href="#"
								onclick="viewTempDiv('ѧ���������','shdiv',380,150);"
								class="btn_sh">��� </a>
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
											onclick="allNotEmpThenGo('jywebNjjs.do?method=xsbmshManage')">
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
									ѧ��
								</td>
								<td>
									<html:text property="querylike_xh"></html:text>
								</td>
								<td>
									����
								</td>
								<td>
									<html:text property="querylike_xm"></html:text>
								</td>
								<td>
									��λ����
								</td>
								<td>
									<html:text property="querylike_dwmc"></html:text>
								</td>
							</tr>
							<tr>
								<td>
									���������
								</td>
								<td>
									<html:select property="queryequals_bzrsh">
										<html:option value=""></html:option>
										<html:option value="δ���">δ���</html:option>
										<html:option value="ͨ��">ͨ��</html:option>
										<html:option value="��ͨ��">��ͨ��</html:option>
									</html:select>
								</td>
								<td>
									ϵ�������
								</td>
								<td>
									<html:select property="queryequals_xysh">
										<html:option value=""></html:option>
										<html:option value="δ���">δ���</html:option>
										<html:option value="ͨ��">ͨ��</html:option>
										<html:option value="��ͨ��">��ͨ��</html:option>
									</html:select>
								</td>
								<td>
									��ҵ�����
								</td>
								<td>
									<html:select property="queryequals_xxsh">
										<html:option value=""></html:option>
										<html:option value="δ���">δ���</html:option>
										<html:option value="ͨ��">ͨ��</html:option>
										<html:option value="��ͨ��">��ͨ��</html:option>
									</html:select>
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
										
											<td>
												<a
												onclick="window.open('jywebUseCheckSession.do?method=resumeView&pkValue=<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>');"
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
			
			
			<div class="open_win01" style="display:none;" id="shdiv">
					<table width='80%' class='formlist'>
						<tbody>
							<tr>
								<th>
					      		���״̬
					      		</th>
					      		<td colspan="3">
					      			<select id="select_shzt" >
					      				<option value="ͨ��">ͨ��</option>
					      				<option value="��ͨ��">��ͨ��</option>
					      			</select>
					      		</td>
							</tr>
							<tr>
					      		<th>�����</th>
					      		<td>
					      			${jyweb_realName }
					      		</td>
					      		<th>���ʱ��</th>
					      		<td>
					      			${nowTime }
					      		</td>
					      	</tr>
						<tbody>
						<tfoot>
							<tr>
								<td colspan='4'>
									<div class='btn'>
										<button onclick="if (confirm('��ȷ��Ҫ�������ѡ�ļ�¼��')){refreshForm('jywebNjjs.do?method=xsbmAuditing&save_shzt='+jQuery('#select_shzt').val())}">
											ȷ��
										</button>
										<button onclick="hiddenMessage(true,true);return false;">
											�ر�
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
		</html:form>
	</body>
</html>
