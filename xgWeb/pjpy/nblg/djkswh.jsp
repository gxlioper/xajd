<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>

<script language="javascript" src="js/AjaxFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
<script type="text/javascript">
function dispDjks(flag) {
	var val = document.getElementById(flag).value;
	var xxdm = document.getElementById('xxdm').value;
	if (xxdm=='13022') {
		if (val == 'djksb') {
			document.getElementById('djksdm').disabled=false;
		} else {
			document.getElementById('djksdm').selectedIndex=0;
			document.getElementById('djksdm').disabled=true;
		}
	}
}
</script>	
</head>	
<body onload="xyDisabled('xy');dispDjks('cjlx');">
	<html:form action="/pjpynblgwh" method="post">
	<input type="hidden" id="userType" name="userType"
				value="${userType }"/>
				<input type="hidden" name="zyV" id="zyV" />
		<input type="hidden" name="bjV" id="bjV" />
		<input type="hidden" name="tableName" id="tableName" value="${tableName }"/>
		<input type="hidden" name="realTable" id="realTable" value="${realTable }"/>
		
		<input type="hidden" id="xxdm" value="${xxdm }"/>
		<div class="tab_cur">
					<p class="location">
						<em><bean:message key="pjpy_nblg_djkswh" bundle="pjpynblg"/></em>
					</p>
		</div>
		<logic:equal value="yes" name="writeAble">
					<div class="toolbox">
					<div class="buttonbox">	
						<input type="hidden" name="ts" id="ts"/>
						<ul>
							<li><a href="#" class="btn_sz" onclick="showTopWin('pjpy_nblg_cjtjgl.do',650,400)">�ɼ�������������</a></li>
							<li><a href="#" class="btn_dr" onclick="impAndChkData()">�ɼ�����</a></li>
							<li><a href="#" class="btn_cs" onclick="jwcjtb('djkscjtb.do?cjlx=')">�ɼ�ͬ��</a></li>
						    <li><a href="#" class="btn_yl" onclick="expTab('rsTable','ѧ���ɼ�����һ����','')">��ӡ/Ԥ��</a></li>
						    <logic:equal value="13022" name="xxdm">
						    <li><a href="xlsDown/nblgdjkscj.xls" class="btn_down" target="_blank">��������ģ������</a></li>
						    <li><a href="xlsDown/nblgcjb.xls" class="btn_down" target="_blank">�ɼ�ģ������</a></li>
						    </logic:equal>
						</ul>
					</div>
					</div>
		</logic:equal>	
		<div class="searchtab">
				<table width="100%" class="" border="0">
					<tbody>
					<tr>
					<th align="left">ѧ��</th>
						<td><html:select property="xn" styleId="xn" style="width:90px" styleClass="select">
						<html:options collection="xnList"  property="xn" labelProperty="xn"/>
					</html:select>
						</td>
						<th align="left">ѧ��</th>
						<td>
							<html:select property="xq" styleId="xq" style="width:60px" styleClass="select">
						<html:option value=""></html:option>
						<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
					</html:select>
						</td>
						<th align="left">�ɼ�����</th>
						<td><html:select property="cjlx" styleId="cjlx" onchange="dispDjks('cjlx');refreshForm('pjpy_djkswh.do?cjb='+document.getElementById('cjlx').value);" style="width:120px">
						<html:option value="cjb">ѧ���ɼ���</html:option>
						<html:option value="djksb">ѧ���ȼ����Ա�</html:option>
					</html:select>
						</td>
						
					</tr>
					<tr>
					<logic:equal value="13022" name="xxdm">
						<th align="left">�ȼ�����</th>
						<td><html:select property="djksdm" styleId="djksdm" style="width:200px"
					       onchange="refreshForm('pjpy_djkswh.do')" styleClass="select">
						<html:option value=""></html:option>
						<html:options collection="djksList" property="djksdm" labelProperty="djksmc"/>
					</html:select>
						</td>
						</logic:equal>
						<th align="left"><bean:message key="lable.xsgzyxpzxy" /></th>
							<td><html:select property="xydm" styleId="xy" style="width:180px" 
					onchange="initZyList();initBjList()" styleClass="select">
						<html:option value=""></html:option>
						<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
					</html:select>
						    </td>	
						    <th align="left">רҵ</th>
							<td><html:select property="zydm" styleId="zy"
					onchange="initBjList()" style="width:180px" styleClass="select">
						<html:option value=""></html:option>
						<html:options collection="zyList" property="zydm" labelProperty="zymc"/>
					</html:select>
							</td>
					</tr>
					<tr>
						
							<th align="left">�༶</th>
							<td><html:select property="bjdm" styleId="bj" style="width:180px" styleClass="select">
						<html:option value=""></html:option>
						<html:options collection="bjList" property="bjdm" labelProperty="bjmc"/>
					</html:select>
						    </td>
					</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="8">
							<div class="btn">
							<input type="hidden" name="go" value="a" />
							<button id="search_go" 
							onclick="if (document.getElementById('cjlx').value=='') {alert('�ɼ�����Ϊ��ѡ����ȷ�ϣ�');return;} else allNotEmpThenGo('/xgxt/djksqry.do');">
							�� ѯ
							</button>
							 <button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
								�� ��
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
							��ѯ���&nbsp;&nbsp;<font color="blue">��ʾ��������ͷ��������</font> 
						</span>
					</h3>
					<table width="99%" id="rsTable" class="dateline">
							<thead>
									<tr align="left" style="cursor:hand">
										<logic:iterate id="tit" name="topTr" >
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)">
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" align="center"
								style="cursor:hand;" >
								<logic:iterate id="v" name="s" >
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
					<TABLE width="99%" id="rsTable1" class="tbstyle">
											<TR>
												<TD height=3></TD>
											</TR>
											<TR>
												<TD>
													<jsp:include flush="true"
														page="/sjcz/turnpage.jsp?form=pjpyNblgActionForm"></jsp:include>
												</TD>
											</TR>
											<TR>
												<TD height=3></TD>
											</TR>
										</TABLE>
			</logic:notEmpty>
			</div>
			<div id="tmp"></div>
			<script type="text/javascript" src="js/bottomButton.js"></script>
			<script language="javascript">
				function jwcjtb(url){
					if (document.getElementById('cjlx').value=='' || 
					document.getElementById('cjlx').value==null
					|| document.getElementById('xn').value=='' || document.getElementById('xn').value==null) {
						alert('����������ѧ�꣬�ɼ�����Ϊ��ѡ����ȷ�ϣ�');
						return;
					} else {
						if (confirm('�ɼ�ͬ������ѧ��,�ɼ�����Ϊ��λ��ȡ��������ϵͳ�ɼ�!\n�ôβ������ķѽϳ�ʱ��,ȷ��Ҫ������?')) {
							url += document.getElementById('cjlx').value;
							url += '&xn=';
							url += document.getElementById('xn').value;
							url += '&xq=';
							url += document.getElementById('xq').value;
							refreshForm(url);
							BatAlert.showTips('���ڲ�������ȴ�...');
						} else {
							return;
						}
					}
				}
				</script>
				<logic:present name="inserted">
					<logic:equal value="yes" name="inserted">
						<script>
							alert('�����ɹ���');
							//document.getElementById('search_go').onclick();
						</script>
					</logic:equal>
					<logic:equal value="no" name="inserted">
						<script>
							alert('����ʧ�ܣ�');
							//document.getElementById('search_go').onclick();
						</script>
					</logic:equal>
				</logic:present>
	</html:form>
</body>
</html>