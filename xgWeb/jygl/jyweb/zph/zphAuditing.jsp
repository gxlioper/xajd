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
				viewTempDiv('��Ƹ��Ϣ���','shdiv',380,200);
			}
		</script>
	</head>
	<body>

		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��Ƹ��Ϣ���</a>
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
								onclick="showAuditingWindow('primarykey_cbv','jywebUseCheckSession.do?method=zphUpdate&doType=sh',680,550);"
								class="btn_sh">��� </a>
						</li>
						<li>
							<a href="#"
								onclick="if(confirm('��ȷ��Ҫȡ���������ѡ�ļ�¼��')){refreshForm('jywebUseCheckSession.do?method=zphAuditing&doType=qxsh')}"
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
											onclick="allNotEmpThenGo('jywebUseCheckSession.do?method=zphAuditing&doType=query')">
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
									<html:select property="queryequals_zplxdm" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="zplxList" property="dm" labelProperty="mc"/>
									</html:select>
								</td>
								<th>
									��Ƹ����
								</th>
								<td>
									<html:text property="querylike_zphbt" maxlength="50" style="width:175px"></html:text>
								</td>
								<th>
									����ʱ��
								</th>
								<td>
									<html:text property="querygreaterequal_fbsj" 
										maxlength="20"
										style="width:80px"
										styleId="querygreaterequal_fbsj"
										onclick="showCalendar(this.id,'y-mm-dd')"
										onblur='dateFormatChg(this)'></html:text>
									-
									<html:text property="querylessequal_fbsj" 
										maxlength="20"
										style="width:80px"
										styleId="querylessequal_fbsj"
										onclick="showCalendar(this.id,'y-mm-dd')"
										onblur='dateFormatChg(this)'></html:text>
								</td>
							</tr>
							<tr>
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
								<th></th>
								<td></td>
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
									<input type="checkbox" disabled="true"  />
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
												alt="<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>"
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
										</td>
										<td>
											<a
											onclick="window.open('/xgxt/jyweb.do?method=zphView&pkValue=<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>');"
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
										for (int i = 0; i < (Integer) request
										.getAttribute("maxNum")
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
										<button onclick="if (confirm('��ȷ��Ҫ�������ѡ�ļ�¼��')){refreshForm('jywebUseCheckSession.do?method=zphAuditing&save_shzt=ͨ��&doType=sh')}">
											ͨ��
										</button>
										<button onclick="if (confirm('��ȷ��Ҫ�������ѡ�ļ�¼��')){refreshForm('jywebUseCheckSession.do?method=zphAuditing&save_shzt=��ͨ��&doType=sh')}">
											��ͨ��
										</button>
										<button onclick="if (confirm('��ȷ��Ҫ�������ѡ�ļ�¼��')){refreshForm('jywebUseCheckSession.do?method=zphAuditing&save_shzt=�˻�&doType=sh')}">
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
	</body>
</html>
