<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xszz/zzxmtj/zzxmtj.js"></script>
		<script language="javascript" src="js/commanFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getInsureInfo.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/zzxmTj.js'></script>
		<script language="javascript" src="js/xgutil.js"></script>
	</head>
	<body onload="checkSffj();getZzZq();changeXyZyBj();">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title}</a>
			</p>
		</div>
		<html:form action="xszzZzxmtj" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ end-->
			<input type="hidden" id="disableEle" name="disableEle"
				value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<input type="hidden" name="isBzr" id="isBzr" value="${bzrQx}" />
			<input type="hidden" id="pkValue" name="pkValue" value="${pkValue }" />
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<input type="hidden" name="cols" value="" />
			<!-- ������ end-->
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#"
									onclick="zzxmtj('/xgxt/xszzZzxmtj.do?method=zzxmTjbb');"
									class="btn_tj">ͳ �� �� ��</a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<!-- ��ť end-->
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="btn_cx" id="search_go" onclick="checkXzTj();return false;">
											�� ѯ
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn_cz" id="btn_cz"
											onclick="czSearchCond('xn-xq-nd-nj-xy-zy-bj-kssjs-jssjs-zzxm-xmdj-xzx');return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- ��һ�� -->
							<tr>
								<th>
									�꼶
								</th>
								<td>
									<html:select property="nj" styleId="nj" style="width:120px"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<html:select property="xn" styleId="xn" style="width:120px">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									���
								</th>
								<td>
									<html:select property="nd" styleId="nd" style="width:120px">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
								<th>
									ѧ��

								</th>
								<td>
									<html:select property="xq" styleId="xq" style="width:120px">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
							</tr>
							<!-- �ڶ��� -->
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<logic:equal name="userType" value="xy">
										<html:select property="query_xydm" disabled="true"
											style="width:120px">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										<html:hidden property="xydm" value="${userDep}" styleId="xy" />
									</logic:equal>
									<logic:notEqual name="userType" value="xy">
										<html:select property="xydm"
											onchange="initZyList();initBjList();" style="width:120px"
											styleId="xy">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
								</td>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="zydm" onchange="initBjList();"
										style="width:120px" styleId="zy">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									�༶
								</th>
								<td>
									<html:select property="bjdm" styleId="bj" style="width:120px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
								<td colspan="2">
								</td>
							</tr>
							<!-- ������ -->
							<tr>
								<th>
									<font color="red">*</font> ������Ŀ
								</th>
								<td>
									<html:select property="zzxm"
										onchange="getXmdj();getZzZq();checkSffj()" style="width:120px"
										styleId="zzxm">
										<html:option value=""></html:option>
										<html:options collection="zzxmList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>

								<th>
									<font color="red">*</font> ͳ�Ʒ�Χ

								</th>
								<td>
									<html:select property="xzx" styleId="xzx"
										onchange="changeXyZyBj()" style="width:120px">
										<html:option value="">---��ѡ��---</html:option>
										<html:option value="nj">�꼶</html:option>
										<html:option value="xydm"><bean:message key="lable.xb" /></html:option>
										<html:option value="zydm">רҵ</html:option>
										<html:option value="bjdm">�༶</html:option>
									</html:select>
								</td>
								<th>
									����ʱ��
								</th>
								<td colspan="4">
									<html:text property="kssj" styleId="kssjs" style="width : 80px"
										onclick="return showCalendar('kssjs','y-mm-dd');"
										onblur="dateFormatChg(this)" readonly="true" />
									����
									<html:text property="jssj" styleId="jssjs" style="width : 80px"
										onclick="return showCalendar('jssjs','y-mm-dd');"
										onblur="dateFormatChg(this)" readonly="true" />
								</td>
							</tr>
							<tr>
								<th>
									<span id="xmdjdiv" style="display:none"> ��Ŀ�ȼ� </span>
								</th>
								<td>
									<span id="xmdjVdiv" style="display:none"> <html:select
											property="xmzzjb" styleId="xmdj" style="width:120px">
											<html:options collection="xmdjList" property="dm"
												labelProperty="mc" />
										</html:select> </span>
								</td>
								<td colspan="6">
							</tr>
						</tbody>
					</table>
				</div>
				<!-- �������� end-->
				<!-- ��ѯ���-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
								<font color="red">δ�ҵ��κμ�¼��</font>
							</logic:empty> </span>
					</h3>
					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<!-- ��ͷ -->
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
							<!-- ��ͷ end-->
							<!--���� -->
							<logic:iterate name="rs" id="s">
								<tr style="cursor:hand">
									<logic:iterate id="v" name="s" offset="0">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<!--���� end-->
						</table>
					</logic:notEmpty>
				</div>
				<!-- ��ѯ��� end-->
			</div>
		</html:form>
	</body>
</html>
