<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/xfjs.js'></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript">	
	function delXsccqk(){
		var RowsStr="!!";		
		for (i=0; i<document.getElementsByName("checkVal").length; i++){
	    	if(document.getElementsByName("checkVal")[i].checked){
	    		RowsStr+=document.getElementsByName("checkVal")[i].value+"!!";
	    	}
		}
		document.forms[0].delPk.value = RowsStr;
		
		if (RowsStr=="!!"){
			alert("��ѡ��Ҫɾ���ļ�¼��");
			return false;
		}
		
		if (!confirm("ȷ��Ҫɾ����ѡ��¼��")){
			return false;
		}
		document.forms[0].go.value = "go";
		refreshForm("/xgxt/pjpyxfjs.do?method=xsccqkSc");
	}
	
	function modiXsccqk(){
		var url = "/xgxt/pjpyxfjs.do?method=xsccqkXg&pk=";
		var pk="";	
		if(curr_row == null ){
				alert("��ѡ����Ҫ�޸ĵļ�¼�У�\n����һ�м���!");
				return false;
			} 	
				
		pk= curr_row.cells[0].getElementsByTagName("input")[0].value;			
		url+=pk;
		
		
		showTopWin(url,800,600);	
	}
	
	function dataExport(){
		var url = "pjpyxfjs.do?method=expXsccqk";
		url += "&nj="+val('nj');
		url += "&xydm="+val('xy');
		url += "&zydm="+val('zy');
		url += "&bjdm="+val('bj');
		url += "&xn="+val('xn');
		url += "&xq="+val('xq');
<%--		url += "&ccrq="+val('ccrq');--%>
		url += "&jclxdm="+val('jclxdm');
		url += "&sfyr="+val('sfyr');
		url += "&sfzgdsjcl="+val('sfzgdsjcl');
		url += "&sfcl="+val('sfcl');
		url += "&ccrqks="+val('ccrqks');
		url += "&ccrqjs="+val('ccrqjs');
		
		window.open(url);
	}
	
	function modiBjcc(){
		var url = "/xgxt/pjpyxfjs.do?method=modiBjccqk&pk=";
		var pk="";	
		if(curr_row == null ){
				alert("��ѡ����Ҫ�޸ĵļ�¼�У�\n����һ�м���!");
				return false;
			} 	
				
		pk= curr_row.cells[0].getElementsByTagName("input")[0].value;			
		url+=pk;
		//�ж��Ƿ�����޸�(�޸���������û�з����İ༶�����Ϣ�����޸ģ����򣬲������޸�)		
		xfjs.selectBjccqkfk(pk,function(data){
			if(data != null && data == true){
				alert('�˴γ���Ѿ����ڷ�����Ϣ,���ܽ����޸Ĳ�����');
				return false;
			}else{
				showTopWin(url,800,600);		
			}
		});		
	}
	</script>
</head>
	<body onload="xyDisabled('xy');">		
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
	
		<html:form action="/pjpyxfjs" method="post">
			<input type="hidden" name="pjxn" id="pjxn" value="${pjxn}"/>
			<input type="hidden" name="pjxq" id="pjxq" value="${pjxq}"/>
			<input type="hidden" name="xyV" id="xyV" value=""/>
			<input type="hidden" name="zyV" id="zyV" value=""/>
			<input type="hidden" name="bjV" id="bjV" value=""/>
			<input type="hidden" name="njV" id="njV" value=""/>
			<input type="hidden" name="delPk" id="delPk" value=""/>
			
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="isFdy" name="isFdy" value="false" />
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>"/>
			<logic:notEmpty name="msg">
				<br/><br/>
				<div align="center"><FONT color="red"><bean:write name="msg"/></FONT></div>
			</logic:notEmpty>

			<logic:empty name="msg">
			
			
			
			
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<!--ѧУ�û�-->						
							<logic:equal value="xx" name="userStatus">
								<li>
									<a href="#"
										onclick="showTopWin('pjpyxfjs.do?method=xsccqkZj',600,400)"
										class="btn_zj"> ���� </a>
								</li>
								<li>
									<a href="#"
										onclick="modiBjcc()"
										class="btn_xg"> �޸� </a>
								</li>
								<li>
									<a href="#"
										onclick="delXsccqk()"
										class="btn_sc"> ɾ�� </a>
								</li>
								<li>
									<a href="#" onclick="impAndChkData()" class="btn_dr"> ���� </a>
								</li>
							</logic:equal>
							<!--ѧУ�û�-->
							
							<!--��ѧУ�û�-->						
<%--							<logic:notEqual value="xx" name="userStatus">--%>
								<li>
									<a href="#"
										onclick="modiXsccqk()"
										class="btn_xg"> �� �� </a>
								</li>
<%--							</logic:notEqual>--%>
							<!--��ѧУ�û�-->

							<li>
								<a href="#"
									onclick="dataExport()"
									class="btn_dc"> ���� </a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/pjpyxfjs.do?method=xsccqkWh')">
											�� ѯ
										</button>
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
									�꼶
								</th>
								<td>
									<html:select property="nj" styleId="nj" style="width:160px"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<html:select property="xn" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<html:select property="xq" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>	
								</td>
							</tr>
			
							<tr>
								<th>
									�������
								</th>
								<td colspan="3">
									<html:text property="ccrqks" style="width:160px"
									           onclick="return showCalendar('ccrqks','y-mm-dd');" readonly="true" styleId="weidth:80px"></html:text>
									-
									<html:text property="ccrqjs" style="width:160px"
									           onclick="return showCalendar('ccrqjs','y-mm-dd');" readonly="true" styleId="weidth:80px"></html:text>
								</td>
								<th>
									�������
								</th>
								<td>
									<html:select property="jclxdm" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="jclxList" property="jclxdm" labelProperty="jclxmc"/>
									</html:select>	
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm" style="width:160px" styleId="xy"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="zydm" style="width:160px" styleId="zy"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									�༶
								</th>
								<td>
									<html:select property="bjdm" style="width:160px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr>
									<th>�Ƿ�����</th>
									<td><html:select property="sfyr" style="width:160px">
										<html:option value=""></html:option>
										<html:option value="1">��</html:option>
										<html:option value="0">��</html:option>
									</html:select></td>
									<th>�Ƿ��ڹ涨ʱ���ڴ���</th>
									<td><html:select property="sfzgdsjcl" style="width:160px">
										<html:option value=""></html:option>
										<html:option value="1">��</html:option>
										<html:option value="0">��</html:option>
									</html:select></td>
									<th>�Ƿ���</th>
									<td><html:select property="sfcl" style="width:160px">
										<html:option value=""></html:option>
										<html:option value="1">��</html:option>
										<html:option value="0">��</html:option>
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			
			
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty>
						<logic:notEmpty name="rs">
							<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
						</logic:notEmpty>	
					 </span>
				</h3>

				<logic:notEmpty name="rs">
				<div class="con_overlfow">
					<table summary="" class="dateline tablenowrap" align="" width="100%">
						<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" id="selall" name="selall"
											onclick="selAll()">
									</td>
									<logic:iterate id="tit" name="topTr" offset="0">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap="nowrap">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
						<tbody>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this);" style="cursor:hand" ondblclick="showTopWin('pjpyxfjs.do?method=bjccqkxx&pk='+this.cells[0].getElementsByTagName('input')[0].value,700,500)">
									<td>
									<input type="checkbox" id="checkVal" name="checkVal" 
								 		value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
									</td>	
									<td>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2" >
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
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=pjpyXfjsForm"></jsp:include>
					<!--��ҳ��ʾ-->
				</logic:notEmpty>
			</div>
			<div id="tmpdiv"></div>
			<div id="tmpdiv1"></div>
			</logic:empty>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("����ʧ�ܣ�");
			</script>
		</logic:equal>
	</body>
</html>
