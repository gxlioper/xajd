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
						<em><bean:message bundle="pjpyynys" key="pjpy_ynys_zhszcp" /></em>
					</p>
		</div>
		<logic:equal value="yes" name="writeAble">
					<div class="toolbox">
					<div class="buttonbox">	
						<input type="hidden" name="ts" id="ts"/>
						<ul>
							<li><a href="#" class="btn_zj" onclick="dycjAdd()">����</a></li>
							<li><a href="#" class="btn_xg" onclick="dycjUpdate('update')">�޸�</a></li>
							<li><a href="#" class="btn_sc" onclick="deldata('pjpy_nblg_ddpycjwh.do?act=delete')">ɾ��</a></li>
						    <li><a href="#" class="btn_dr" onclick="impAndChkData();">��������</a></li>
						    <li><a href="#" class="btn_dc"  onclick="dataExport()">���ݵ���</a></li>
						    <li><a href="xlsDown/dycjb.xls" target="_blank" class="btn_down" >ģ������</a></li>
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
		<!-- ����ɾ����Ϣ��ʾ -->
		<fieldset>
			<legend>
				������Ŀ
			</legend>
			<table width="100%" class="tbstyle">
				<tr>
					<td>
						<html:radio property="cjlx" styleId="cjlx" value="0" style="cursor:hand"
							onclick="refreshForm('pjpy_nblg_ddpycjwh.do')"></html:radio>
							��������ɼ�
						&nbsp; 
						<html:radio property="cjlx" styleId="cjlx" value="1" style="cursor:hand"
							onclick="refreshForm('pjpy_nblg_ddpycjwh.do')"></html:radio>
						��Ϊ��ʵ�ɼ�
						&nbsp; 
						<html:radio property="cjlx" styleId="cjlx" value="2" style="cursor:hand"
							onclick="refreshForm('pjpy_nblg_ddpycjwh.do')"></html:radio>
						����԰����ʵ�ɼ�
					</td>
				</tr>
			</table>
		</fieldset>
		
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
						</td>
					</tr>
					<tr>
					<th align="left">����</th>
						<td><html:text property="xm" styleId="xm" styleClass="inputtext"
								style="width:100px"></html:text>
						</td>
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
					
					</tr>
					<tr>
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
							onclick="dataQryChk('pjpy_nblg_ddpycjwh.do');">
							�� ѯ
							</button>
							 <input type="hidden" name="act" value="query" />
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
							��ѯ���&nbsp;&nbsp;<font color="blue">������ͷ��������</font> 
						</span>
					</h3>
						<table width="99%" id="rsTable" class="dateline">
							<thead>
								<tr align="center" style="cursor:hand">
									<td nowrap>
										<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="1" scope="request">
										<td id="${tit.en}" onclick="tableSort(this)" nowrap>
											${tit.cn}
										</td>
									</logic:iterate>
								</tr>
							</thead>
								<tbody>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand;"
									ondblclick="dycjUpdate('view')">
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
							</tbody>
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
