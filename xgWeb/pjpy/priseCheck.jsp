<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
	</head>
	<body onload="xyDisabled('xy');">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/typj" method="post">
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
			<input type="hidden" name="realTable" id="realTable" value="xsjxjb" />
			<input type="hidden" name="tableName" id="tableName"
				value="view_xsjxjb" />
			<input type="hidden" name="pk" id="pk" value="xh||jxjdm||xn||xq" />
			<input type="hidden" name="act" id="act" value="modi" />
			<input type="hidden" id="userName" name="userName"
				value="${userName }" />
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<input type="hidden" id="isFdy" name="isFdy" value="${isFdy }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<input type="hidden" id="shjb" name="shjb" value="${shjb }" />
			<logic:equal value="xy" name="userType" scope="session">
				<logic:notEqual value="true" name="isFdy" scope="session">
					<input type="hidden" name="queryequals_fdysh" value="ͨ��" />
				</logic:notEqual>
			</logic:equal>
			<logic:notEqual value="xy" name="userType" scope="session">
				<input type="hidden" name="queryequals_xysh" value="ͨ��" />
			</logic:notEqual>



			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#"
									onclick="if (null == curr_row){alert('��ѡ��һ��');return false} else if (true==curr_row.getElementsByTagName('input')[0].disabled){alert('�ϼ��������ͨ��,�����޸�');return false;}showInfo('/xgxt/typj.do?method=priseCheckOne','sh','800','600')"
									class="btn_sh"> �������</a>
							</li>
							<li>
								<a href="#"
									onclick="shformdata('/xgxt/typj.do?method=priseCheck&shjg=ͨ��&doType=sh');"
									class="btn_shtg"> ����ͨ��</a>
							</li>
							<li>
								<a href="#"
									onclick="shformdata('/xgxt/typj.do?method=priseCheck&shjg=��ͨ��&doType=sh');"
									class="btn_shbtg">������ͨ��</a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/typj.do?method=priseCheck&doType=query')">
											�� ѯ
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
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
									<html:select property="queryequals_nj"
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
									<html:hidden property="queryequals_xn" value="${xn }" />
									<html:select property="queryequals_xn" value="${xn }"
										disabled="true">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									���
								</th>
								<td>
									<html:hidden property="queryequals_nd" value="${nd }" />
									<html:select property="queryequals_nd" value="${nd }"
										disabled="true">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									ѧ��
								</th>
								<td>
									<html:text property="querylike_xh" maxlength="20"
										style="width:80px"></html:text>
								</td>
								<th>
									����
								</th>
								<td>
									<html:text property="querylike_xm" maxlength="20"
										style="width:80px"></html:text>
								</td>
								<th>
									��ѧ��
								</th>
								<td>
									<html:select property="queryequals_jxjdm" styleId="jxjdm"
										onchange="allNotEmpThenGo('/xgxt/typj.do?method=jxjsh')">
										<html:options collection="jxjList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="queryequals_xydm"
										onchange="initZyList();initBjList()" styleId="xy"
										style="width:200px">
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
										onchange="initBjList()" styleId="zy" style="width:200px">
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
										style="width:200px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<logic:equal value="xy" name="userType" scope="session">
									<logic:equal value="true" name="isFdy" scope="session">
										<th>
											����Ա���
										</th>
										<td>
											<html:select property="queryequals_fdysh">
												<html:option value=""></html:option>
												<html:options collection="shztList" property="en"
													labelProperty="cn" />
											</html:select>
										</td>
									</logic:equal>
									<logic:equal value="false" name="isFdy" scope="session">
										<th>
											<bean:message key="lable.xsgzyxpzxy" />
										</th>
										<td>
											<html:select property="queryequals_xysh">
												<html:option value=""></html:option>
												<html:options collection="shztList" property="en"
													labelProperty="cn" />
											</html:select>
										</td>
									</logic:equal>
								</logic:equal>
								<logic:notEqual value="xy" name="userType" scope="session">
									<th>
										ѧУ���
									</th>
									<td>
										<html:select property="queryequals_xxsh">
											<html:option value=""></html:option>
											<html:options collection="shztList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
								</logic:notEqual>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> <logic:notEmpty name="rs">
							<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��������ͷ��������</font>
						</logic:notEmpty> </span>
				</h3>

				<logic:notEmpty name="rs">
					<div class="con_overlfow">
						<table summary="" class="dateline tablenowrap" align=""
							width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<td nowrap>
										<%--											<input type="checkbox" name="cb" onclick="selectAll()" style="height:17.5px" >--%>
									</td>
									<logic:iterate id="tit" name="topTr" offset="3" scope="request">
										<td id="${tit.en}" onclick="tableSort(this)" nowrap>
											${tit.cn}
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										name="<logic:iterate id="v" name="s" offset="1" length="1">
										    <bean:write name="v"/>
										    </logic:iterate>"
										ondblclick="viewMore('view')"
										style="cursor:hand;background-color:
										    <logic:iterate id="v" name="s" offset="1" length="1">
										    <bean:write name="v"/>
										    </logic:iterate>
										     ">
										<td align=center name="a">
											<logic:iterate id="v" name="s" offset="2" length="1">
												<input type="checkbox" name="primarykey_cbv" id="cbv"
													value="<bean:write name="v"/>"
													<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate> />
												<input type="hidden" name="check"
													value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
											</logic:iterate>

										</td>
										<logic:iterate id="v" name="s" offset="3">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</div>
					<!--��ҳ��ʾ-->
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=guizhdxForm"></jsp:include>
					<!--��ҳ��ʾ-->
				</logic:notEmpty>
			</div>
		</html:form>
		<logic:present name="result">
			<script>
				alert(''+$('message').value);
				document.getElementById('search_go').click();
			</script>
		</logic:present>
	</body>
</html>
