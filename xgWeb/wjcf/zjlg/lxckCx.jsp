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
				<em>您的当前位置:</em><a>违纪处分 - 处分审核 - 留校察看</a>
			</p>
		</div>
		<div class="searchtab">
			<table width="100%" class="">
				<tbody>
					<tr>
						<th>学号</th>
						<td><html:text property="xh" styleId="xh"></html:text></td>
						<th>姓名</th>
						<td><html:text property="xm" styleId="xm"></html:text></td>
						<th>留校察看解除时间</th>
						<td><html:text property="lxcksj" styleId="lxcksj" style="width:100px"></html:text></td>
					</tr>
					<tr>
						<td colspan="6">
						<logic:equal value="11647" name="xxdm">
						<font color="red">(键入离当前时间段前需要解除的留校察看信息和当前时间段后尚未解除的留校察看信息.)</font>
						</logic:equal>
						<logic:notEqual value="11647" name="xxdm">
						<font color="red">(格式XXXX年XX月XX日如XXXXXXXX,也可只输入年或月或日进行查询)</font>
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
							查询
						</button>
						 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
							重置
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
				    	查询结果&nbsp;&nbsp;
							<font color="red">未找到任何记录！</font> 
				    </span>
				    </h3>
				 </logic:empty>
				<logic:notEmpty name="rs">
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;
							<logic:equal value="11647" name="xxdm">
								<font color="blue">提示离当前时间段前需要解除的留校察看信息和当前时间段后尚未解除的留校察看信息.</font>
								</logic:equal>
								<logic:notEqual value="11647" name="xxdm">
									<font color="blue">提示默认查询出来的数据是留校察看时间为当前时间的数据;也可以在输入留校察看时间进行查询(年、月、日均可)</font>
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
									<button type="button" id="btn_sq" class="button2" onclick="lxcksq()">解除申报</button>
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
