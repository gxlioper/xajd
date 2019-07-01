<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript"
			src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/hbsf/hbsfjs.js"></script>
		<script type="text/javascript">
<!--
		function printB() {
		var pkValue = "";
		if (curr_row != null && curr_row != '') {
			pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
		}
		window.open('wjcf_zjcm_jclxcbb.do?pkValue=' + pkValue);
	}
//-->
</script>
	</head>
	<body onload="xyDisabled('xy');">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>Υ�ʹ��� - �����У�쿴 - ����ά��</a>
			</p>
		</div>

		<html:form action="/wjcfzjlgwh.do" method="post">
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="operType" value="query" />
			<input type="hidden" name="tableName" value="${tableName }" />
			<input type="hidden" name="realTable" value="${realTable }" />
			<input type="hidden" name="pt" id="pt" />
			<input type="hidden" name="userType" id="userType"
				value="${userType }" />
			<input type="hidden" name="failInfo" id="failInfo"
				value="${failinfo }" />


			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#"
									onclick="showTopWin('wjcf_zjlg_addLxckxx.do',640,510)"
									class="btn_zj"> ���� </a>
							</li>
							<li>
								<a href="#"
									onclick="modiAndDel('wjcf_zjlg_modiLxckxx.do?pkValue=','modi',640,510)"
									class="btn_xg"> �޸� </a>
							</li>
							<li>
								<a href="#" onclick="deldata('wjcf_zjlg_delLxckxx.do')"
									class="btn_sc"> ɾ�� </a>
							</li>
							<li>
								<a href="#" onclick="impAndChkData()" class="btn_dr"> ���� </a>
							</li>
							<li>
								<a href="#" onclick="dataExport()" class="btn_dc"> ���� </a>
							</li>
							<li>
								<a href="#" onclick="printB()" class="btn_dy"> ��ӡ </a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="refreshForm('wjcf_zjlg_lxcksjwh.do')">
											��ѯ
										</button>
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											����
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
									<html:select property="nj" styleId="nj" style="width:80px"
										styleClass="select" onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<html:select property="xn" styleId="xn" style="width:90px"
										styleClass="select">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									���
								</th>
								<td>
									<html:select property="nd" styleId="nd" style="width:90px">
										<html:options collection="xnList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm" style="width:150px"
										onchange="initZyList();initBjList()" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="zydm" onchange="initBjList()"
										style="width:150px" styleId="zy">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									�༶
								</th>
								<td>
									<html:select property="bjdm" styleId="bj" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								
								<th>
									ѧ��
								</th>
								<td>
									<html:text property="xh" styleId="xh" styleClass="inputtext"
										style="width:150px">
									</html:text>
								</td>
								<th>
									����
								</th>
								<td>
									<html:text property="xm" styleId="xm" styleClass="inputtext"
										style="width:150px">
									</html:text>
								</td>
								
								<th>
									�����У�쿴ʱ��
								</th>
								<td>
									<html:text property="lxcksj" styleId="lxcksj"
										style="width:150px" onblur="dateFormatChg(this)" style="cursor:hand;"
											onclick="return showCalendar('lxcksj','y-mm-dd');"></html:text>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
								<font color="red">δ�ҵ��κμ�¼��</font>
							</logic:empty>
							<logic:notEmpty name="rs">
								<font color="blue"> ��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��</font>
							</logic:notEmpty>
						</span>
					</h3>

					<logic:notEmpty name="rs">
						<table summary="" class="dateline" width="100%">
							<thead>
								<tr>
									<td>
										<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="2">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" style="cursor:hand;"
										ondblclick="modiAndDel('wjcf_zjlg_modiLxckxx.do?operType=view&pkValue=','modi',640,510)">
										<td nowrap="nowrap">
											<input type="checkbox" id="cbv" name="cbv"
												value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"
												<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate> />
										</td>
										<logic:iterate id="v" name="s" offset="2">
											<td nowrap="nowrap">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
						<!--��ҳ��ʾ-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=wjcfZjlgActionForm"></jsp:include>
						<!--��ҳ��ʾ-->
					</logic:notEmpty>
				</div>
			</div>
			<div id="tmpdiv"></div>
		</html:form>
		<logic:present name="deleted">
			<logic:equal value="yes" name="deleted">
				<script>
					alert('�����ɹ�!');
					//document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
			<logic:equal value="no" name="deleted">
				<script>
					alert('����ʧ��!');
					//document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
