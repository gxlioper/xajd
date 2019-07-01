<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<script language="javascript"
	src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
<script type="text/javascript" src="pjpy/ynys/ynysJs/ynys.js"></script>
<script type="text/javascript" src="js/BatAlert.js"></script>
<script type="text/javascript" src="pjpy/nblg/pjpynblg.js"></script>
</head>
<body onload="xyDisabled('xy');">
	<html:form action="/pjpynblgzhcpwh" method="post">
		<div class="tab_cur">
					<p class="location">
						<em>��ǰ����λ�ã�</em><a>�������� �� ��Ϣά�� �� �ۺ����ʲ���</a>
					</p>
		</div>
		<logic:equal value="yes" name="writeAble">
					<div class="toolbox">
					<div class="buttonbox">	
						<input type="hidden" name="ts" id="ts"/>
						<ul>
							<li><a href="#" class="btn_cs" onclick="autoCount()">�Զ�����</a></li>
						    <li><a href="#" class="btn_dc" onclick="zhszcpDataExp()">���ݵ���</a></li>
						</ul>
					</div>
					</div>
		</logic:equal>	
		<input type="hidden" id="zyV" name="zyV" value="" />
		<input type="hidden" id="bjV" name="bjV" value="" />
		<input type="hidden" id="userType" name="userType"
			value="${userType }" />
		<input type="hidden" id="tableName" name="tableName"
			value="${tableName}" />
		<input type="hidden" id="realTable" name="realTable"
			value="${realTable}" />
		<input type="hidden" id="failInfo" name="failInfo"
			value="${failinfo }" />
		<input type="hidden" id="pt" value="pt"/>
		
        <input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
		<!-- ����ɾ����Ϣ��ʾ -->
			<div class="searchtab">
				<table width="100%" class="" border="0">
					<tbody>
					<tr>
					<th align="left">�꼶</th>
						<td><html:select property="nj" styleId="nj" style="width:90px"
								onchange="initZyList();initBjList()" styleClass="select">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
						</td>
						<th align="left">ѧ��</th>
						<td>
							<html:select property="xn" style="width:90px" styleClass="select"
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
						<th align="left">ѧ��</th>
						<td><html:text property="xh" styleId="xh" styleClass="inputtext"
								style="width:100px"></html:text>
						  &nbsp;&nbsp;����&nbsp;&nbsp;		
						 <html:text property="xm" styleId="xm" styleClass="inputtext"
								style="width:100px"></html:text>
						</td>
					</tr>
					<tr>
						<th align="left"><bean:message key="lable.xsgzyxpzxy" /></th>
							<td><html:select property="xydm" onchange="initZyList();initBjList()"
								styleClass="select" style="width:180px" styleId="xy">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						    </td>	
						    <th align="left">רҵ</th>
							<td><html:select property="zydm" onchange="initBjList()"
								style="width:180px" styleClass="select" styleId="zy">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
							</td>
							<th align="left">�༶</th>
							<td><html:select property="bjdm" style="width:180px"
								styleClass="select" styleId="bj">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						    </td>
					</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="8">
							<div class="btn">
							<input type="hidden" name="go" value="a" />
							<button type="button" id="search_go" 
							onclick="refreshForm('pjpy_nblg_zhszcpwh.do');">
							�� ѯ
							</button>
							 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
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
							��ѯ���&nbsp;&nbsp;<font color="blue">˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font> 
						</span>
					</h3>
						<table width="99%" id="rsTable" class="dateline">
							<thead>
									<tr align="left" style="cursor:hand">
									<td><input type="checkbox" onclick="selectAllCheckBox()" /></td>
										<logic:iterate id="tit" name="topTr" >
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)">
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand;"
									ondblclick="">
									<td align=center>
										<input type="checkbox" id="cbv" name="cbv"
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
									</td>
									<logic:iterate id="v" name="s" offset="1">
										<td align=center>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
				</logic:notEmpty>
				<div id="tmpdiv"></div>
			</div>
	</html:form>
	<script type="text/javascript" src="js/bottomButton.js"></script>
	<logic:present name="deleted">
		<logic:equal value="yes" name="deleted">
			<script>
	 			alert('�����ɹ���');
	 			document.getElementById('search_go').onclick();
	 		</script>
		</logic:equal>
		<logic:equal value="no" name="deleted">
			<script>
	 			alert('' + document.getElementById('failInfo').value());
	 			document.getElementById('search_go').onclick();
	 		</script>
		</logic:equal>
	</logic:present>
</body>
</html>