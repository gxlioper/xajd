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
			var xq = $('sqxq').value;
   			var url = "/xgxt/zjlg_gygl.do?method=viewSqxx&pkValue=";
   			var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
			url += pk;
			url += "&xn="+xn+"&xq="+xq;
    		showTopWin(url,"600","500");              		                  
 		}
		function queryData(){
			var xn=document.getElementById('xn').value;
			var xq=document.getElementById('xq').value;
			if(xn == ''||xq == ''){
				alert('ѧ��,ѧ��Ϊ��ѡ��');
				return false;
			}
			refreshForm('/xgxt/zjlg_gygl.do?method=ajqsshcx&doType=query');
		}
		function getRqVal(name){
			var rq=document.getElementById(name).value;
			if (rq!=""){
				rqs=rq.split("-");
				rq="";
				for (var i=0;i<rqs.length;i++){
					rq+=rqs[i];
				}
				document.getElementById(name).value=rq;
			}
		}
	</script>
	</head>
	<body>
		<center>
			<html:form action="/zjlg_gygl" method="post">
				<input type="hidden" name="sqxn" id="sqxn" value="${myform.xn }" />
				<input type="hidden" name="sqxq" id="sqxq" value="${myform.xq }" />
				<input type="hidden" name="realTable" id="realTable" value="<bean:write name="realTable"/>"/>
				<input type="hidden" name="tableName" id="tableName" value="<bean:write name="tableName"/>"/>
				
				<div class="tab_cur">
					<p class="location">
						<em>���ĵ�ǰλ��:</em><a>��Ԣ���� - A�����ҹ��� - ��˽����ѯ - A��������˽����ѯ</a>
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
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<th>ѧ��</th>
								<td><html:select property="xn" styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select></td>
								<th>ѧ��</th>
								<td><html:select property="xq" styleId="xn" style="width:80px">
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select></td>
								<th>¥��</th>
								<td><html:select property="lddm" styleId="lddm"
										onchange="getLcList()">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="ldList" property="lddm"
											labelProperty="ldmc" />
									</html:select></td>
							</tr>
							<tr>
								<th>¥��</th>
								<td><html:select property="lc" styleId="lc"
										onchange="getQshList2()">
										<html:options collection="lcList" property="dm"
											labelProperty="mc" />
									</html:select></td>
								<th>���Һ�</th>
								<td><html:select property="qsh" styleId="qsh">
										<html:options collection="qshList" property="dm"
											labelProperty="mc" />
									</html:select></td>
								<th></th><td></td>
							</tr>
							<tr>
								<th>���״̬</th>
								<td><html:select property="xxsh" styleId="xxsh">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="chkList" property="en"
											labelProperty="cn" />
									</html:select></td>
								<th>�Ƿ���</th>
								<td><html:select property="sfcx" styleId="sfcx">
										<html:option value="">--��ѡ��--</html:option>
										<html:option value="��">��</html:option>
										<html:option value="��">��</html:option>
									</html:select></td>
								<td colspan="2">�������ڴ�
									<html:text property="sqrq1" styleId="sqrq1" readonly="true"
										style="width:80px"
										onclick="this.value='';return showCalendar('sqrq1','y-mm-dd');"
										onblur="getRqVal('sqrq1')"></html:text>
									��
									<html:text property="sqrq2" styleId="sqrq2" readonly="true"
										style="width:80px"
										onclick="this.value='';return showCalendar('sqrq2','y-mm-dd');"
										onblur="getRqVal('sqrq2')"></html:text>
								</td>
							</tr>
						</thead>
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
										<bean:write name="s" property="xqmc" />
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
										<bean:write name="s" property="xxsh" />
									</td>
									<td align="center">
										<bean:write name="s" property="shsj" />
									</td>
									<td align="center">
										<bean:write name="s" property="sfcx" />
									</td>
									<td align="center">
										<bean:write name="s" property="cxsj" />
									</td>
								</tr>
							</logic:iterate>
						</table>
						<!--��ҳ��ʾ-->
					     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=zjlg_gyglForm"></jsp:include>
						<!--��ҳ��ʾ-->
					<br />
				</logic:notEmpty>
				</div>
			</html:form>
			<div id="tmpdiv"></div>
		</center>
	</body>
</html>
