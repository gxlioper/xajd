<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.*" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript" src="js/xsxx/comm/xjyd/xjyd.js"></script>
		<script type="text/javascript">
			function plsh(){
				tipsWindown("ϵͳ��ʾ","id:shDiv","350","200","true","","true","id");
			}
		</script>
	</head>
	<body>

		<html:form action="/xsxxXjyd" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<input type="hidden" id="isFdy"     name="isFdy"  	 value="${isFdy }" />
			<input type="hidden" id="userName"  name="userName"  value="${userName }" />
			<input type="hidden" id="userType"  name="userType"  value="${userType }" />
			<input type="hidden" id="tableName" name="tableName" value="${tableName}" />
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			
			<input type="hidden" name="ydhxyV" value="" />
			<input type="hidden" name="ydhzyV" value="" />
			<input type="hidden" name="ydhbjV" value="" />
			
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
									onclick="showAuditingWindow('primarykey_cbv','xsxxXjyd.do?method=xjydDgshcx',750,550);"
									class="btn_sh">��� </a>
							</li>
							<%--<li>
								<a href="#"
									id="btn_up"
									onclick="doSubmit('primarykey_cbv','xsxxXjyd.do?method=plshTj')"
									class="btn_up">�����ύ </a>
							</li>
							--%><li>
								<a href="#"
									id="btn_cs"
									onclick="showViewWindow('primarykey_cbv','xsxxXjyd.do?method=lcgzXjyd','550','350',false,'�빴ѡһ����¼!')"
									class="btn_cs">���̸��� </a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						
						<tbody>
							<tr>
								<th>ѧ��</th>
								<td>
									<html:text property="xh"></html:text>
								</td>
								<th>����</th>
								<td>
									<html:text property="xm"></html:text>
								</td>
								<th>�춯���</th>
								<td>
									<html:hidden property="save_shlcid" styleId="shlcid"/>
									<html:select property="ydlbm" styleId="ydlbm" style="width:180px" >
										<html:option value=""></html:option>
										<html:options collection="ydlbList" property="ydlbm" labelProperty="ydlbmc"/>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>����ʱ��</th>
								<td>
									<html:text property="sqkssj" style="width:80px"
										onblur="dateFormatChg(this)"
										onclick="return showCalendar('sqkssj','y-mm-dd');"></html:text>
									-
									<html:text property="sqjssj" style="width:80px"
										onblur="dateFormatChg(this)"
										onclick="return showCalendar('sqjssj','y-mm-dd');"></html:text>
								</td>
								<th>�춯ǰ�꼶</th>
								<td>
									<html:select property="ydqnj" styleId="nj"
										onchange="initZyList();initBjList()" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>�춯���꼶</th>
								<td>
									<html:select property="ydhnj" styleId="ydhnj"
										onchange="initYdhZyList();initYdhBjList()" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>�춯ǰ<bean:message key="lable.xsgzyxpzxy" /></th>
								<td>
									<html:select property="ydqxydm"
										onchange="initZyList();initBjList();" styleId="xy"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="ydqXyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>�춯ǰרҵ</th>
								<td>
									<html:select property="ydqzydm"
										onchange="initBjList();" styleId="zy" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="ydqZyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>�춯ǰ�༶</th>
								<td>
									<html:select property="ydqbdm" styleId="bj"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="ydqBjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>�춯��<bean:message key="lable.xsgzyxpzxy" /></th>
								<td>
									<html:select property="ydhxydm"
										onchange="initYdhZyList();initYdhBjList()" styleId="ydhxy"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="ydhXyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>�춯��רҵ</th>
								<td>
									<html:select property="ydhzydm"
										onchange="initYdhBjList();" styleId="ydhzy" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="ydhZyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>�춯��༶</th>
								<td>
									<html:select property="ydhbdm" styleId="ydhbj"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="ydhBjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr>
							<th>
								ѧ��
							</th>
							<td>
								<html:select property="xn" style="width:180px" 
									styleId="xn" styleClass="select" >
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>	
							<th>
								ѧ��
							</th>
							<td>
								<html:select property="xq" style="width:180px" 
									styleId="xq" styleClass="select" >
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
							<th>
							</th>
							<td>
							</td>
						</tr>
							<tr>
								<%--<th>��˽��</th>
								<td>
									<html:select property="shzt">
										<html:option value=""></html:option>
										<html:options collection="shztList" property="en" labelProperty="cn"/>
									</html:select>
								</td>
								<th>�Ƿ��ύ</th>
								<td>
									<html:select property="sftj">
										<html:option value=""></html:option>
										<html:option value="��">��</html:option>
										<html:option value="��">��</html:option>
									</html:select>
								</td>
								
								--%><%--<th>��˷�Χ</th>
								<td colspan="3">
									�춯ǰ��<bean:message key="lable.xb" /><input type="radio" name="isbxy" value="true" checked="true"/>
									�춯��<bean:message key="lable.xb" /><input type="radio" name="isbxy" value="false"/>
								</td>
								--%><td colspan="6">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('xsxxXjyd.do?method=xjydshquery&doType=query')">
											�� ѯ
										</button>
										<button type="button" onclick="searchReset();return false;">
										 	�� ��
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
					<span> ��ѯ���&nbsp;&nbsp; 
						<logic:notEmpty name="rs">
							<font color="blue">��ʾ������Ϊ����Ҫ��˵����ݣ�������ͷ��������</font>
						</logic:notEmpty> 
					</span>
				</h3>


				<div class="con_overlfow">
					<table class="dateline tablenowrap" width="100%">
						<thead>
							<tr>
								<td>
									<input type="checkbox" disabled="disabled" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="0" scope="request">
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
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"/>
										</td>
										<logic:iterate id="v" name="s" offset="1">
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
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
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
				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=xsxxXjydForm"></jsp:include>
				<!--��ҳ��ʾ-->
			</div>
			
			
			<!-- ������˵����� -->
			<div id="shDiv" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>����д�����Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									���״̬
								</th>
								<td>
									<html:select property="shjg" style="width:120px" styleId="shjg">
										<html:options collection="shztList" property="en" labelProperty="cn"/>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									������<br/>
									<font color="red"><��500��></font>
								</th>
								<td>
									<html:textarea property="shyj" styleId="shyj" style="width:95%" rows="3" onblur="chLeng(this,500)"></html:textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button type="button" name="�� ��" onclick="if(confirm('��ȷ��Ҫ�����ύ��?')){refreshForm('xsxxXjyd.do?method=plshXjyd&sftj=��&shjg='+$('shjg').value+'&shyj='+$('shyj').value)}">
											�� ��
										</button>
										<button type="button" name="ȡ��" onclick="closeWindown();return false;">
											ȡ ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
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
