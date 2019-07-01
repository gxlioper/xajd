<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/rcsw/rcswFunction.js"></script>
	</head>
	<body onload="xyDisabled('xy');">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/zjxyRcsw" method="post">
			<%@ include file="/rcsw/hiddenValue.jsp"%>
			<logic:notEmpty name="msg">
				<div align="center">
					<font color="red" size="6"><bean:write name="msg" />
					</font>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<div class="toolbox">
					<div class="searchtab">
						<table width="100%" border="0">
							<tfoot>
								<tr>
									<td colspan="6">
										<div class="btn">
											<button type="button" class="btn_cx" id="search_go"
												onclick="BatAlert.showTips('��ѯ�����У����Ժ�');allNotEmpThenGo('/xgxt/zjxyRcsw.do?method=swffJgcxManage');">
												�� ѯ
											</button>
											<input type="hidden" name="go" value="a" />
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
										<html:select property="xn" style="" onchange="">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
									</td>
									<th>
										ѧ��
									</th>
									<td>
										<html:select property="xq" style="" onchange="">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
										</html:select>
									</td>
									<th>
										���
									</th>
									<td>
										<html:select property="nd" style="" onchange="">
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
										<logic:equal name="isAdmin" value="true">
											<html:text property="zgh" style="width:85px" maxlength="20" />
										</logic:equal>
										<logic:equal name="isAdmin" value="false">
											<logic:equal value="false" name="isXy">
												<html:text property="zgh" style="width:85px" readonly="true" />
											</logic:equal>
											<logic:equal value="true" name="isXy">
												<html:text property="zgh" style="width:85px" maxlength="20" />
												<input type="hidden" name="xydm" value="${userDep }"/>
											</logic:equal>													
										</logic:equal>
									</td>
									<th>
										����
									</th>
									<td>
										<html:text property="xm" style="width:85px" maxlength="20" />
									</td>
									<th>
										��Ŀ����
									</th>
									<td>
										<html:select property="xmlx" style=""
											styleId="xmlx">
											<html:options collection="xmlxList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>
										�Ƿ����
									</th>
									<td>
										<html:select property="isff" style="" styleId="isff">
											<html:option value=""></html:option>
											<html:options collection="ffList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
									<logic:notEqual name="xxdm" value="13275">
									<th>
										���
									</th>
									<td>
										<html:select property="lx" style="" styleId="lx">
											<html:options collection="ffrqList" property="dm"
												labelProperty="mc" />
											<html:option value="��ͨ">��ͨ</html:option>
										</html:select>
									</td>
									</logic:notEqual>
									<logic:equal name="xxdm" value="13275">
									<th>
									</th>
									<td>
									</td>
									</logic:equal>
									<th>
									</th>
									<td>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="formbox">
						<h3 class="datetitle_01">
							<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
									<font color="red">δ�ҵ��κμ�¼��</font>
								</logic:empty> </span>
						</h3>

						<logic:notEmpty name="rs">
						<div class="con_overlfow" >
							<table summary="" class="dateline" align="" width="100%">
								<thead>
									<tr align="center" style="cursor:hand">
										<logic:iterate id="tit" name="topTr" offset="0">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<tbody>
									<logic:iterate name="rs" id="s" indexId="index">
										<tr onclick="rowOnClick(this);" style="cursor:hand">
											<logic:iterate id="v" name="s" offset="0">
												<td align="left">
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</tbody>
							</table>
						</div>
							<!--��ҳ��ʾ-->
							<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=rcswForm"></jsp:include>
							<script type="text/javascript">
								$('choose').className="hide";
							</script>
							<!--��ҳ��ʾ-->
						</logic:notEmpty>
					</div>
				</div>
			</logic:empty>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("����ʧ��");
			</script>
		</logic:equal>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
