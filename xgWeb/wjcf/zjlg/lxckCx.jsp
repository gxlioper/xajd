<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript">
			function lxcksq() {
				var pkValue = curr_row.getElementsByTagName("input")[0].value;
				if (window.dialogArguments != null) {
					window.close();
					window.dialogArguments.document.forms[0].action="wjcf_zjlg_addLxckxx.do?pkValue=" + pkValue;
					window.dialogArguments.document.forms[0].submit();
				} else {
					document.forms[0].action="wjcf_zjlg_addLxckxx.do?pkValue=" + pkValue;
					document.forms[0].submit();
				}
			}
		</script>
	</head>
	<body onload="">
	<html:form action="/wjcfzjlgwh.do" method="post">
		<input type="hidden" name="operType" value="query"/>
		<input type="hidden" name="failInfo" id="failInfo" value="${failinfo }"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>Υ�ʹ��� - ������� - ��У�쿴</a>
			</p>
		</div>
		<div class="searchtab">
			<table width="100%" class="">
				<tbody>
					<tr>
						<th>ѧ��</th>
						<td><html:text property="xh" styleId="xh"></html:text></td>
						<th>����</th>
						<td><html:text property="xm" styleId="xm"></html:text></td>
						<th>��У�쿴���ʱ��</th>
						<td><html:text property="lxcksj" styleId="lxcksj" style="width:100px"></html:text></td>
					</tr>
					<tr>
						<td colspan="6">
						<logic:equal value="11647" name="xxdm">
						<font color="red">(�����뵱ǰʱ���ǰ��Ҫ�������У�쿴��Ϣ�͵�ǰʱ��κ���δ�������У�쿴��Ϣ.)</font>
						</logic:equal>
						<logic:notEqual value="11647" name="xxdm">
						<font color="red">(��ʽXXXX��XX��XX����XXXXXXXX,Ҳ��ֻ��������»��ս��в�ѯ)</font>
						</logic:notEqual>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="6">
						<div class="btn">
						<input type="hidden" name="go" value="a" />
						<button type="button" id="search_go" onclick="refreshForm('wjcf_zjlg_lxckcx.do?act=qry')">
							��ѯ
						</button>
						 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
							����
						 </button>
						</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
				
			<div class="formbox">
				<logic:empty name="rs">
				    <h3 class="datetitle_01">
				    <span>
				    	��ѯ���&nbsp;&nbsp;
							<font color="red">δ�ҵ��κμ�¼��</font> 
				    </span>
				    </h3>
				 </logic:empty>
				<logic:notEmpty name="rs">
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;
							<logic:equal value="11647" name="xxdm">
								<font color="blue">��ʾ�뵱ǰʱ���ǰ��Ҫ�������У�쿴��Ϣ�͵�ǰʱ��κ���δ�������У�쿴��Ϣ.</font>
								</logic:equal>
								<logic:notEqual value="11647" name="xxdm">
									<font color="blue">��ʾĬ�ϲ�ѯ��������������У�쿴ʱ��Ϊ��ǰʱ�������;Ҳ������������У�쿴ʱ����в�ѯ(�ꡢ�¡��վ���)</font>
								</logic:notEqual>
								</span>
					</h3>
					<table width="100%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>" onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onmousemove="rowOnClick(this)" style="cursor:hand;"
								align="center">
									<input type="hidden" id="cbv" name="cbv"
										value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
								<logic:iterate id="v" name="s" offset="1" length="9">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<td>
									<button type="button" id="btn_sq" class="button2" onclick="lxcksq()">����걨</button>
								</td>
							</tr>
						</logic:iterate>
					</table>
<%--					<TABLE width="99%" id="rsTable1" class="tbstyle">--%>
<%--											<TR>--%>
<%--												<TD height=3></TD>--%>
<%--											</TR>--%>
<%--											<TR>--%>
<%--												<TD>--%>
<%--													<jsp:include flush="true"--%>
<%--														page="/sjcz/turnpage.jsp?form=wjcfZjlgActionForm"></jsp:include>--%>
<%--												</TD>--%>
<%--											</TR>--%>
<%--											<TR>--%>
<%--												<TD height=3></TD>--%>
<%--											</TR>--%>
<%--										</TABLE>--%>
			</logic:notEmpty>
		</div>
	</html:form>
</body>
