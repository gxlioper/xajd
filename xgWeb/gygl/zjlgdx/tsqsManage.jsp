<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="/xgxt/js/jsFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/GetListData.js"></script>
	<script language="javascript" src="/xgxt/js/pjpy/pjpy_zjsyzy.js"></script>
	<script type="text/javascript">
		function chkView(){
			var xn = $('sqxn').value;
   			var url = "/xgxt/zjlg_gygl.do?method=viewTsqsSqxx&pkValue=";
   			var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
			url += pk;
			url += "&xn="+xn;
    		showTopWin(url,"600","600");              		                  
 		}
		function queryData(){
			var xn=document.getElementById('xn').value;
			if(xn == ''){
				alert('ѧ��Ϊ��ѡ��');
				return false;
			}
			refreshForm('/xgxt/zjlg_gygl.do?method=tsqsshcx&doType=query');
		}

	</script>
	<body>
		<center>
			<html:form action="/zjlg_gygl" method="post">
				<input type="hidden" name="sqxn" id="sqxn" value="${myform.xn }" />
				<input type="hidden" name="realTable" id="realTable" value="<bean:write name="realTable"/>"/>
				<input type="hidden" name="tableName" id="tableName" value="<bean:write name="tableName"/>"/>
				
				<div class="tab_cur">
					<p class="location">
						<em>���ĵ�ǰλ��:</em><a>��Ԣ���� - ��ɫ���ҹ��� - ��˽����ѯ - ��ɫ������˽����ѯ</a>
					</p>
				</div>
				<div class="toolbox">
					<div class="buttonbox">	
						<ul>
							<logic:equal value="yes" name="writeAble">
							<li><a href="#" class="btn_dr" onclick="impAndChkData()">����</a></li>
							</logic:equal>
							<li><a href="#" class="btn_dc" onclick="dataExport()">����</a></li>								
						</ul>
					</div>
				</div>
				<div class="searchtab">		
					<table width="100%" class="" border="0">
						<tbody>
							<tr>
								<th>ѧ��</th>
								<td><html:select property="xn" styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select></td>
								<th>¥��</th>
								<td><html:select property="lddm" styleId="lddm"
										onchange="getLcList()">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="ldList" property="lddm"
											labelProperty="ldmc" />
									</html:select></td>
								<th>¥��</th>
								<td><html:select property="lc" styleId="lc"
										onchange="getQshList2()">
										<html:options collection="lcList" property="dm"
											labelProperty="mc" />
									</html:select></td>
							</tr>
							<tr>
								<th>���Һ�</th>
								<td><html:select property="qsh" styleId="qsh">
										<html:options collection="qshList" property="dm"
											labelProperty="mc" />
									</html:select></td>
								<th>ѧУ���</th>
								<td><html:select property="xxsh" styleId="xxsh">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="chkList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<th></th><td></td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="6">
								<div class="btn">
								<input type="hidden" name="go" value="a" />
								<button id="search_go" onclick="queryData();">
									��ѯ
								</button>
								 <button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
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
								��ѯ���&nbsp;&nbsp;<font color="blue">˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font> 
							</span>
						</h3>
						<table width="100%" id="rsTable" class="dateline">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="chkView()">
									<td align="center">
										<input type="hidden" name="pkV"
											value="<bean:write name="s" property="pk"/>" />
										<bean:write name="s" property="xn" />
									</td>
									<td align="center">
										<bean:write name="s" property="ssbh" />
									</td>
									<td align="center">
										<bean:write name="s" property="ldmc" />
									</td>
									<td align="center">
										<bean:write name="s" property="lc" />
									</td>
									<td align="center">
										<bean:write name="s" property="qsh" />
									</td>
									<td align="center">
										<bean:write name="s" property="lxdh" />
									</td>
									<td align="center">
										<bean:write name="s" property="sqsj" />
									</td>
									<td align="center">
										<bean:write name="s" property="xysh" />
									</td>
									<td align="center">
										<bean:write name="s" property="xxsh" />
									</td>

								</tr>
							</logic:iterate>
						</table>
						
						<!--��ҳ��ʾ-->
					     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=zjlg_gyglForm"></jsp:include>
						<!--��ҳ��ʾ-->
						<br />
						<br />
				</logic:notEmpty>
			</div>
			</html:form>
			<div id="tmpdiv"></div>
		</center>
	</body>
</html>
