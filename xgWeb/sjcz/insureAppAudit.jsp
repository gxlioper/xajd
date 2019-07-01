<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script language="javascript" src="js/qtsjFunction.js"></script>
	</head>
	<body onload="xyDisabled('xy')">

		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a><bean:write name="tips" scope="request" />
				</a>
			</p>
		</div>


		<html:form action="/prise_conf_rs" method="post">
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="bmlb" name="bmlb" value="xy" />


			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#"
									onclick="dataBatch('insurePassAuditing.do?yesNo=pass')"
									class="btn_shtg"> ���ͨ�� </a>
							</li>
							<li>
								<a href="#"
									onclick="deletedata('/xgxt/jygl.do?method=bysxc&doType=del')"
									class="btn_shbtg"> ��˲�ͨ�� </a>
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
										<input type="hidden" name="go" value="a" />
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/InsureAppAudit.do')">
											�� ѯ
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									ѧ��
								</th>
								<td>
									<html:select property="xn" style="width:120px" disabled="true"
										styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									���
								</th>
								<td>
									<html:select property="nd" style="width:90px" disabled="true"
										styleId="nd">
										<html:options collection="xnList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
								<th>
									�꼶
								</th>
								<td>
									<html:select property="nj" style="width:90px;padding-left:80px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm" style="width:230px" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									��������
								</th>
								<td>
									<html:select property="bxxzdm" style="width:230px"
										styleId="bxxzdm">
										<html:option value=""></html:option>
										<html:options collection="tbxzdmList" property="bxxzdm"
											labelProperty="bxxzmc" />
									</html:select>
								</td>
								<th>

								</th>
								<td>
									<input id="chgMode" type="checkbox"
										style="border:0px;display:none" />
								</td>
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
							<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ�������Ըı����״̬��������ͷ��������</font>
						</logic:notEmpty> </span>
				</h3>

				<logic:notEmpty name="rs">
					<div class="con_overlfow">
						<table summary="" class="dateline tablenowrap" align=""
							width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" name="all" value="all" onclick="chec()" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="2">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										style="cursor:hand;background-color:
							    <logic:iterate id="v" name="s" offset="0" length="1">
							    <bean:write name="v"/>
							    </logic:iterate>
							    "
										ondblclick="chkPriseOne('/xgxt/insureChkOne.do?act=view&pkVal=',600,400)">
										<td>
											<logic:iterate id="v" name="s" offset="1" length="1">
												<input type="checkbox" name="pkV"
													value="<bean:write name="v"/>" />
											</logic:iterate>
										</td>
										<td>
											<logic:iterate id="v" name="s" offset="1" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="2" length="1">
												<bean:write name="v" />
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="3">
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</div>
				</logic:notEmpty>
			</div>
		</html:form>
		<logic:present name="result">
			<logic:equal value="true" name="result">
				<logic:notEmpty name="mes">
					<input type="hidden" id="mes" name="mes" value="${mes}" />
					<script>
					alert(document.getElementById('mes').value);
				</script>
				</logic:notEmpty>
				<logic:empty name="mes">
					<script>
					alert('�����ɹ���');
				</script>
				</logic:empty>
			</logic:equal>
			<logic:equal value="false" name="result">
				<logic:notEmpty name="mes">
					<input type="hidden" id="mes" name="mes" value="${mes}" />
					<script>
					alert(document.getElementById('mes').value);
				</script>
				</logic:notEmpty>
				<logic:empty name="mes">
					<script>
					alert('����ʧ�ܣ�');
				</script>
				</logic:empty>
			</logic:equal>
			<script>
			document.getElementById('search_go').onclick();
		</script>
		</logic:present>
	</body>
</html>
