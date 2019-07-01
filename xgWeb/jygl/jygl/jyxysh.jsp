<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
		<script type="text/javascript" src="js/jygl/jygl.js"></script>
		<script type="text/javascript">
			function plsh(){
				viewTempDiv('��ҵЭ�����','shdiv',380,200);
			}
		</script>
	</head>
	<body onload="xyDisabled('xy');hiddenTr($('moreTerm'));purviewControl()">
		<html:form action="/jygl" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			
			<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="session"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="${ tableName}" />
			<input type="hidden" id="realTable" name="realTable"
				value="${realTable }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="userName" id="userName"
				value="${userName }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />

			<!-- ��ʦ��Ȩ���õ� -->
			<logic:present name="purview">
				<input type="hidden" id="purview" name="purview" value="${ purview }" /> 
				<input type="hidden" id=" operateBound " name="operateBound" value="${ operateBound }" />
			</logic:present>

			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#"
									id="btn_sh"
									onclick="showAuditingWindow('primarykey_cbv','/xgxt/jygl.do?method=jyxyShow&doType=sh',900,600);"
									class="btn_sh">��� </a>
							</li>
							<li>
								<a href="#"
									id="btn_qxsh"
									onclick="if(isChecked('primarykey_cbv') && confirm('��ȷ��Ҫȡ���������ѡ�ļ�¼��')){refreshForm('/xgxt/jygl.do?method=jyxysh&doType=qxsh')}"
									class="btn_qxsh">ȡ����� </a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="bz">
					                    <label>
					                      <html:checkbox onclick="hiddenTr(this)" property="moreTerm" styleId="moreTerm"/>
					                      <strong>��������</strong>
					                   </label>
					                </div>
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/jygl.do?method=jyxysh&doType=query')">
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
									�꼶
								</th>
								<td>
									<html:select property="nj"
										style="width:180px"
										onchange="initZyList();initBjList()" styleId="rxnf">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<html:text property="xh" maxlength="20"
										style="width:175px"></html:text>
								</td>
								<th>
									����
								</th>
								<td>
									<html:text property="xm" maxlength="20"
										style="width:175px"></html:text>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm"
										onchange="initZyList();initBjList()" styleId="xy"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="zydm"
										onchange="initBjList()" styleId="zy" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									�༶
								</th>
								<td>
									<html:select property="bjdm" styleId="bj"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr style="display:none">
								<th>
									��ҵ��־
								</th>
								<td>
									<html:select property="jybz" style="width:180px">
										<html:options collection="jybzList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
								<th>
									��λ����
								</th>
								<td>
									<html:select property="dwxz" style="width:180px">
										<html:options collection="dwxzList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
								<th>
									�Ƿ�Կ�
								</th>
								<td>
									<html:select property="sfdk" style="width:180px">
										<html:option value="">----��ѡ��----</html:option>
										<html:options collection="isNot" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
							</tr>
							<tr style="display:none">
								<th>
									��ҵ��ҵ
								</th>
								<td>
									<logic:equal value="10388" name="xxdm" scope="session">
										<html:select property="hyfldm" style="width:180px">
											<html:options collection="hyList" property="dm"
												labelProperty="mc" />
										</html:select>
									</logic:equal>
									<logic:notEqual value="10388" name="xxdm" scope="session">
										<html:select property="jyhy" style="width:180px">
											<html:options collection="hyList" property="dm"
												labelProperty="mc" />
										</html:select>
									</logic:notEqual>
								</td>

								<th>
									<logic:equal value="10388" name="xxdm" scope="session">
											����Ա���
									</logic:equal>
									<logic:notEqual value="10388" name="xxdm" scope="session">
										<bean:message key="lable.xsgzyxpzxy" />���
									</logic:notEqual>
								</th>
								<td>
									<html:select property="xysh" style="width:180px">
										<html:option value="">----��ѡ��----</html:option>
										<html:options collection="shztList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<th>
									ѧУ���
								</th>
								<td>
									<html:select property="xxsh" style="width:180px">
										<html:option value="">----��ѡ��----</html:option>
										<html:options collection="shztList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
							</tr>
							
							<tr style="display:none">
								<th><bean:message key="lable.xsgzyxpzxy" />�����</th>
								<td><html:text property="xyshr" style="width:175px"></html:text></td>
								<th>ѧУ�����</th>
								<td><html:text property="xxshr" style="width:175px"></html:text></td>
								
								<logic:notEqual value="10388" name="xxdm" scope="session">
									<th>
										��
									</th>
									<td>
										<html:select property="je" styleId="je"
											style="width:180px">
											<html:option value=""></html:option>
											<html:options collection="nfList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
								</logic:notEqual>
								<logic:equal value="10388" name="xxdm" scope="session">
									<th>
										��ҵ���
									</th>
									<td>
										<html:select property="bynf" styleId="bynf"
											style="width:180px">
											<html:option value=""></html:option>
											<html:option value="δ��ҵ">δ��ҵ</html:option>
											<html:options collection="nfList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
								</logic:equal>
							</tr>
						</tbody>
					</table>
				</div>

				<div class="formbox">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; 
							<logic:notEmpty name="rs">
								<font color="blue">��ʾ��������ͷ��������</font>
							</logic:notEmpty> 
						</span>
					</h3>

					<div class="con_overlfow">
						<table summary="" class="dateline tablenowrap" align=""
							width="100%">
							<thead>
								<tr>
									<td>
										<input type="checkbox" disabled="true" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="3" scope="request">
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
													<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>
													alt="<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>"
													value="<logic:iterate id="v" name="s" offset="2" length="1">${v }</logic:iterate>" />
											</td>

											<td>
												<a
													onclick="showTopWin('/xgxt/jygl.do?method=jyxyShow&doType=view&pkValue=<logic:iterate id="v" name="s" offset="2" length="1">${v }</logic:iterate>','900','600');"
													class="pointer" style="color:#0A63BF" href="#"> <logic:iterate
														id="v" name="s" offset="3" length="1">${v }</logic:iterate>
												</a>
											</td>

											<logic:iterate id="v" name="s" offset="4">
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
										<logic:iterate id="t" name="topTr" offset="3" scope="request">
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
										<logic:iterate id="t" name="topTr" offset="3" scope="request">
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
					<!--��ҳ��ʾ-->
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=jyglActionForm"></jsp:include>
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
					      			${userNameReal }
					      		</td>
					      		<th>���ʱ��</th>
					      		<td>
					      			${now }
					      		</td>
					      	</tr>
						<tbody>
						<tfoot>
							<tr>
								<td colspan='4'>
									<div class='btn'>
										<button onclick="if (confirm('��ȷ��Ҫ�������ѡ�ļ�¼��')){refreshForm('/xgxt/jygl.do?method=jyxysh&shzt=ͨ��&doType=sh')}">
											ͨ��
										</button>
										<button onclick="if (confirm('��ȷ��Ҫ�������ѡ�ļ�¼��')){refreshForm('/xgxt/jygl.do?method=jyxysh&shzt=��ͨ��&doType=sh')}">
											��ͨ��
										</button>
										<button onclick="if (confirm('��ȷ��Ҫ�˻�����ѡ�ļ�¼��')){refreshForm('/xgxt/jygl.do?method=jyxysh&shzt=�˻�&doType=sh')}">
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
