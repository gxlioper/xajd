<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			
			function plsh(){
				viewTempDiv('��λ���','shdiv',380,200);
			}
			
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��λ���</a>
			</p>
		</div>

		<html:form action="/jyweb" method="post">
			<!-- �๦�ܲ�����һ -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#"
								onclick="showAuditingWindow('primarykey_cbv','jywebUseCheckSession.do?method=companyUpdate&doType=sh',800,580);"
								class="btn_sh">��� </a>
						</li>
						<li>
							<a href="#"
								onclick="if(isChecked('primarykey_cbv') && confirm('��ȷ��Ҫȡ���������ѡ�ļ�¼��')){refreshForm('jywebUseCheckSession.do?method=companyAuditing&doType=qxsh')}"
								class="btn_qxsh">ȡ����� </a>
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
											onclick="allNotEmpThenGo('jywebUseCheckSession.do?method=companyAuditing&doType=query')">
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
									<html:text property="querylike_dwmc" maxlength="25" style="width:175px"></html:text>
								</td>
								<th>
									ע��ʱ��
								</th>
								<td>
									<html:text property="querygreaterequal_zcsj"
										onblur="dateFormatChg(this);"
										onclick="showCalendar(this.id,'y-mm-dd')"
										style="width:80px"
										styleId="querygreaterequal_zcsj"></html:text>
									-
									<html:text property="querylessequal_zcsj"
										onblur="dateFormatChg(this);"
										onclick="showCalendar(this.id,'y-mm-dd')"
										style="width:80px"
										styleId="querylessequal_zcsj"></html:text>
								</td>
								<th>
									��ҵ����
								</th>
								<td>
									<html:select property="queryequals_hyfl" style="width:180px">
										<html:options collection="hyflList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									��λ����
								</th>
								<td>
									<html:select property="queryequals_dwxz" style="width:180px">
										<html:options collection="dwxzList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
								<th>
									���״̬
								</th>
								<td>
									<html:select property="queryequals_shzt"  style="width:180px">
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
					<span> ��ѯ���&nbsp;&nbsp; 
						<logic:notEmpty name="rs">
							<font color="blue" >��ʾ��������ͷ��������</font>
							<font color="red" style="font-weight:normal;"> </font>
						</logic:notEmpty>
					</span>
				</h3>

				
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr>
								<td>
									<input type="checkbox" disabled="true" style="height:17.5px" />
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
											<input type="checkbox" name="primarykey_cbv" value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" alt="<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>"/>
										</td>
										<td>
											<a onclick="showTopWin('jywebUseCheckSession.do?method=companyUpdate&doType=view&pkValue=<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>','800','580');" 
												class="pointer" style="color:#0A63BF" href="#">
												<logic:iterate id="v" name="s" offset="2" length="1">${v }</logic:iterate>
											</a>
										</td>
										<logic:iterate id="v" name="s" offset="3" >
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
									for (int i = 0 ; i < (Integer)request.getAttribute("maxNum") ; i++){
								
								 %>
									<tr>
										<td>
											<input type="checkbox" disabled="disabled"/>
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
				
				<div class="open_win01" style="display:none;" id="shdiv">
					<table width='80%' class='formlist'>
						<tbody>
							<tr>
								<th>
					      			������<br/>
					      			<font color="red"><��500��></font>
					      		</th>
					      		<td colspan="3">
					      			<html:textarea property="save_shyj"  onblur="checkLen(this,500)" style="width:90%" rows="5"></html:textarea>
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
										<button onclick="if (confirm('��ȷ��Ҫ�������ѡ�ļ�¼��')){refreshForm('jywebUseCheckSession.do?method=companyAuditing&save_shzt=ͨ��&doType=sh')}">
											ͨ��
										</button>
										<button onclick="if (confirm('��ȷ��Ҫ�������ѡ�ļ�¼��')){refreshForm('jywebUseCheckSession.do?method=companyAuditing&save_shzt=��ͨ��&doType=sh')}">
											��ͨ��
										</button>
										<button onclick="if (confirm('��ȷ��Ҫ�˻�����ѡ�ļ�¼��')){refreshForm('jywebUseCheckSession.do?method=companyAuditing&save_shzt=�˻�&doType=sh')}">
											�˻�
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
		
		<logic:present name="message">
			<script defer="defer">
				alert("${message}");
			</script>
		</logic:present>
	</body>
</html>
