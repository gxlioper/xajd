<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List"/>
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
			function bysblPlsh(shjg){
				var shjb = $('shjb').value;
				var userType = $('userType').value;
				
				if (!isChecked("primarykey_cbv")){
					return false;
				}
				
				if (array.length > $('sybh').value && 'ͨ��'==shjg){
					alert("��ҵЭ�����Ų������䣬����ά��Э������!");
					return false;
				}
				
				refreshForm('/xgxt/jygl.do?method=xysblsh&doType=sh&shjg='+shjg);
			}
			
			function plsh(){
				viewTempDiv("Э���鲹�����","shdiv",380,200);
			}
			
		</script>
	</head>
	<body onload="xyDisabled('xy');hiddenTr($('moreTerm'));purviewControl();">

		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/jygl" method="post">
			<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<input type="hidden" id="userName" name="userName"
				value="${userName }" />
			<input type="hidden" id="tableName" name="tableName"
				value="${ tableName}" />
			<input type="hidden" id="realTable" name="realTable"
				value="${realTable }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<input type="hidden" id="shjb" value="${cssz.xysbbshjb }"/>
			<input type="hidden" id=sybh value="${sybh }"/>

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
									onclick="showAuditingWindow('primarykey_cbv','/xgxt/jygl.do?method=xysblsq&doType=sh',700,500);"
									class="btn_sh">��� </a>
							</li>
							<li>
								<a href="#"
									id="btn_qxsh"
									onclick="if(isChecked('primarykey_cbv') && confirm('��ȷ��Ҫȡ���������ѡ�ļ�¼��')){refreshForm('/xgxt/jygl.do?method=xysblsh&doType=qxsh')}"
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
											<html:checkbox onclick="hiddenTr(this)" property="moreTerm"
												styleId="moreTerm" />
											<strong>��������</strong>
										</label>
									</div>
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/jygl.do?method=xysblsh&doType=query')">
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
									<html:select property="queryequals_nj" style="width:160px"
										onchange="initZyList();initBjList()" styleId="nj">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<html:text property="querylike_xh" maxlength="20"
										style="width:155px"></html:text>
								</td>
								<th>
									����
								</th>
								<td>
									<html:text property="querylike_xm" maxlength="20"
										style="width:155px"></html:text>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="queryequals_xydm"
										onchange="initZyList();initBjList()" styleId="xy"
										style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="queryequals_zydm"
										onchange="initBjList()" styleId="zy" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									�༶
								</th>
								<td>
									<html:select property="queryequals_bjdm" styleId="bj"
										style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr style="display:none">
								<th>
									ԭ��ҵ
									<br />
									Э����
								</th>
								<td>
									<html:text property="querylike_jyxybh" style="width:155px"></html:text>
								</td>
								<th>
									�¾�ҵ
									<br />
									Э����
								</th>
								<td>
									<html:text property="querylike_newjyxybh" style="width:155px"></html:text>
								</td>
								<th>
									�������
								</th>
								<td>
									<html:select property="queryequals_bblbdm" style="width:160px">
										<html:options collection="bblbList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
							</tr>
							<tr style="display:none">
								<logic:equal value="2" name="cssz" property="xysbbshjb">
									<th>
										<bean:message key="lable.xsgzyxpzxy" />���
									</th>
									<td>
										<html:select property="queryequals_xysh" style="width:160px">
											<html:option value=""></html:option>
											<html:options collection="shztList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
									<th>
										<bean:message key="lable.xsgzyxpzxy" />�����
									</th>
									<td>
										<html:text property="querylike_xyshr" style="width:155px"></html:text>
									</td>
								</logic:equal>
								<th>
									ѧУ���
								</th>
								<td>
									<html:select property="queryequals_xxsh" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="shztList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<logic:equal value="1" name="cssz" property="xysbbshjb">
									<th>
										ѧУ�����
									</th>
									<td>
										<html:text property="querylike_xxshr" style="width:155px"></html:text>
									</td>
									<th>
										��
									</th>
									<td>
										<html:select property="queryequals_je" styleId="je"
											style="width:180px">
											<html:option value=""></html:option>
											<html:options collection="nfList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
								</logic:equal>
							</tr>
							<logic:equal value="2" name="cssz" property="xysbbshjb">
							<tr style="display:none">
								<th>
									ѧУ�����
								</th>
								<td>
									<html:text property="querylike_xxshr" style="width:155px"></html:text>
								</td>
								<th>
									��
								</th>
								<td>
									<html:select property="queryequals_je" styleId="je"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="nfList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<th></th>
								<td></td>
							</tr>
							</logic:equal>
						</tbody>
					</table>
				</div>
			</div>


			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; 
						<logic:notEmpty name="rs">
							<font color="blue">��ʾ��������ͷ��������,ʣ���ҵЭ����<font color="red">${sybh }</font>��</font>
						</logic:notEmpty>
					</span>
				</h3>

				
					<div class="con_overlfow">
						<table summary="" class="dateline tablenowrap" align=""
							width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<td nowrap>
										<input type="checkbox" name="cb" onclick="selectAll()" disabled="disabled"/>
									</td>
									<logic:iterate id="tit" name="topTr" offset="3" scope="request">
										<td onclick="tableSort(this)" nowrap>
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
													value="<logic:iterate id="v" name="s" offset="2" length="1">${v }</logic:iterate>"
													alt="<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>"
													<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate> />
											</td>
											<td>
												<a
													href="javascript:showTopWin('/xgxt/jygl.do?method=xysblsq&doType=view&pkValue=<logic:iterate id="v" name="s" offset="2" length="1">${v }</logic:iterate>','700','500');"
													class="pointer" style="color:#0A63BF"> 
													<logic:iterate id="v" name="s" offset="3" length="1">${v }</logic:iterate>
												</a>
											</td>
											<logic:iterate id="v" name="s" offset="4" >
												<td>
													<bean:write name="v" />
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
										for (int i = 0 ; i < (Integer)request.getAttribute("maxNum") ; i++){
									
									 %>
										<tr>
											<td>
												<input type="checkbox" disabled="disabled"/>
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
										<button onclick="if (confirm('��ȷ��Ҫ�������ѡ�ļ�¼��')){bysblPlsh('ͨ��')}">
											ͨ��
										</button>
										<button onclick="if (confirm('��ȷ��Ҫ�������ѡ�ļ�¼��')){bysblPlsh('��ͨ��')}">
											��ͨ��
										</button>
										<button onclick="if (confirm('��ȷ��Ҫ�˻�����ѡ�ļ�¼��')){bysblPlsh('�˻�')}">
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
			<script language="javascript">
	        	alert("${message}");
	        </script>
		</logic:present>
	</body>
</html>
