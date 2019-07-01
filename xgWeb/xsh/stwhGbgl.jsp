<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
		<script type="text/javascript" src="js/comm/message.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript">
			function setZw(text) {
				
				var zwdm=document.getElementById("select_zwdm");
				if ('' != text) {
					GetListData.getGbList(text.trim(),function(data){
						DWRUtil.removeAllOptions('zwdm');
						DWRUtil.addOptions('zwdm',[""]);
						DWRUtil.addOptions('zwdm',data,"pk","gbmc");	
						zwdm.options[0].text="��Ա";
						zwdm.options[0].value = "";
					})
				}
				
			}
			
			function saveData() {
				var checkBoxArr = document.getElementsByName("primarykey_cbv");
				var flag = false;
				for(var i=0;i<checkBoxArr.length;i++){
					if(checkBoxArr[i].checked==true){
						flag = true;
					}
				}
				if (flag){
<%--					if (confirm('ȷ��Ҫ������ѡ���������')){--%>
						return true;
<%--					}--%>
				}else{
					alert("û��ѡ����Ӧ��¼����ѡ��֮���ٽ��в�������");
					return false;
				}
			}
			
			function showZwDiv() {
				var flg = saveData();
				if (!flg){
					return false;
				}
				
				var zwdm=document.getElementById("select_zwdm");
				viewTempDiv("ְ�����","tempDiv",300,150);
				
				if ('' != $('stmc').value) {
					GetListData.getGbList($('stmc').value.trim(),function(data){
						DWRUtil.removeAllOptions('zwdm');			
						DWRUtil.addOptions('zwdm',[""]);
						DWRUtil.addOptions('zwdm',data,"pk","gbmc");
						$("select_zwdm").options[0].value = "";	
						$("select_zwdm").options[0].text = "��Ա";	
					})
				}
	}
	
	function closeDiv(){
		$('tmpdiv1').style.display='none';
	}
	
	function updateZw() {
		
		document.forms[0].action = '/xgxt/xsh.do?method=stwhGbgl&doType=save';
		document.forms[0].submit();
	}
	
	function updateSfyxstgb() {
		
		document.forms[0].action = '/xgxt/xsh.do?method=stwhGbgl&doType=szyxfb';
		document.forms[0].submit();
	}
	
	function showSfyxstgb(){
		var pkValue=document.getElementsByName("primarykey_cbv");
		
		var flag=false;
		for(var i=0;i<pkValue.length;i++){
			if(pkValue[i].checked){
				flag=true;
				if($("zw_"+i) && $("zw_"+i).value=="��Ա"){
					alert("ֻ�����Ÿɲ�������ѡ�������Ÿɲ�,��ȷ��!");
					return false;
				}
			}
		}
		
		if(!flag){
			alert("�빴ѡ��Ҫ��ѡ�������Ÿɲ������ų�Ա!");
			return false;
		}
		viewTempDiv("�������Ÿɲ�","div_yxstgb",300,150);
		
	}
	
	function yxstgbdjb(){
			var pkValue=document.getElementsByName("primarykey_cbv");
			
			var n=0;
			var pk="";
			for(i=0;i<pkValue.length;i++){
				if(pkValue[i].checked){
					pk=pkValue[i].value;
					n++;
				}	
			}
			
			if(n!=1){
				alert("�빴ѡһ������!");
				return false;
			}
			showOpenWindow('/xgxt/xsh.do?method=yxstgbdjb&pk='+pk,800,600)
		}
	</script>
	</head>
	<body onload="xyDisabled('xy');">

		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/xsh" method="post">
			<input type="hidden" name="flg" value="${flg }" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="session"/>" />
			<input type="hidden" id="userName" name="userName"
				value="<bean:write name="userName" scope="session"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="${ tableName}" />
			<input type="hidden" id="realTable" name="realTable"
				value="xsh_cyglb" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<input type="hidden" name="isFdy" value="${isFdy }" />
			<logic:equal value="xy" name="userType">
				<input type="hidden" name="queryequals_xydm" value="${userDep }" />
			</logic:equal>

			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" onclick="showZwDiv();return false;" class="btn_ccg"> ����ְ����� </a>
							</li>
							<logic:present name="cdtyxg">
								<li>
									<a href="#" onclick="showSfyxstgb();return false;" class="btn_sz"> �������Ÿɲ�
									</a>
								</li>
							</logic:present>
							<li>
								<a href="#"
									onclick="deletedata('/xgxt/xsh.do?method=stwhGbgl&doType=del');return false;"
									class="btn_sc"> ɾ��</a>
							</li>
							<li>
								<a href="#" onclick="impAndChkData();return false;" class="btn_dr">����</a>
							</li>
							<li>
								<a href="#"
									onclick="expData('/xgxt/xsh.do?method=stwhGbgl&doType=expData');return false;"
									class="btn_dc">����</a>
							</li>
							<logic:present name="cdtyxg">
								<li>
									<a href="#" onclick="yxstgbdjb();return false;" class="btn_dy"> �������Ÿɲ��ǼǱ�
									</a>
								</li>
							</logic:present>
						</ul>
					</div>
				</logic:equal>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<input name="go" value="go" type="hidden" />
										<button class="btn_cx" id="search_go"
											onclick="if(''==$('stmc').value){alert('��ѡ������');return false}allNotEmpThenGo('/xgxt/xsh.do?method=stwhGbgl&doType=query')">
											�� ѯ
										</button>
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
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
									<html:select property="queryequals_nj"
										onchange="initZyList();initBjList()" styleId="nj">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<logic:equal value="stu" name="userType" scope="session">
										<logic:present name="stcyddcl">
											<html:text property="querylike_xh" maxlength="20"
												style="width:80px"  ></html:text>
										</logic:present>
										<logic:notPresent name="stcyddcl">
											<html:text property="querylike_xh" maxlength="20"
												style="width:80px" value="${userName }" readonly="true"></html:text>
										</logic:notPresent>

									</logic:equal>
									<logic:notEqual value="stu" name="userType" scope="session">
										<html:text property="querylike_xh" maxlength="20"
											style="width:80px"></html:text>
									</logic:notEqual>

								</td>
								<th>
									����
								</th>
								<td>
									<html:text property="querylike_xm" maxlength="20"
										style="width:80px"></html:text>
									<logic:equal value="xsh" name="flg">
										<html:hidden property="queryequals_stv" styleId="stmc"
											value="ѧ����ѧУ��" />
									</logic:equal>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="queryequals_xydm"
										onchange="initZyList();initBjList()" styleId="xy"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="queryequals_zydm"
										onchange="initBjList()" styleId="zy" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									�༶
								</th>
								<td>
									<html:select property="queryequals_bjdm" styleId="bj"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<logic:notEqual value="xsh" name="flg">
								<tr>
									<th>
										��������
									</th>
									<td>
										<html:select property="queryequals_stv" styleId="stmc"
											onchange="setZw(this.value);">
											<html:option value=""></html:option>
											<html:options collection="stList" property="pk"
												labelProperty="stmc" />
										</html:select>
									</td>
									<logic:present name="cdtyxg">
									<th>
										�Ƿ��������Ÿɲ�
									</th>
									<td>
										<html:select property="queryequals_sfyxstgb" styleId="sfyxstgb">
											<html:option value=""></html:option>
											<html:option value="��">��</html:option>
											<html:option value="��">��</html:option>
										</html:select>
									</td>
									<th>
										ע�����
									</th>
									<td>
										<html:select property="queryequals_zcnd" styleId="zcnd" style="width:150px" >
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
										</html:select>
									</td>
									</logic:present>
									<logic:notPresent name="cdtyxg">
									<th>
									</th>
									<td>
									</td>
									<th>
									</th>
									<td>
									</td>
									</logic:notPresent>
								</tr>
							</logic:notEqual>
						</tbody>
					</table>
				</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
								<font color="red">δ�ҵ��κμ�¼��</font>
							</logic:empty> </span>
					</h3>

					<logic:notEmpty name="rs">
						<div class="con_overlfow" style="width:100%">
							<table summary="" class="dateline tablenowrap" align=""
								width="100%" id="rsTable">
								<thead>
									<tr style="cursor:hand">
										<td nowrap>
											<input type="checkbox" name="cb" onclick="selectAll()"
												disabled="true" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="1"
											scope="request">
											<td id="${tit.en}" onclick="tableSort(this)" nowrap>
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<tbody>
									<logic:iterate name="rs" id="s" indexId="index">
										<tr onclick="rowOnClick(this)" style="cursor:hand;">
											<td>
												<input type="checkbox" id="cbv" name="primarykey_cbv" id="pk_${index }"
													value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
												<input type="hidden" value="<bean:write name="v" />" />
												<input type="hidden" id="zw_${index}" value="<logic:iterate id="m" name="s" offset="11" length="1">${m}</logic:iterate>"/>
											</td>
											<logic:iterate id="v" name="s" offset="1">
												<td>
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</tbody>
							</table>
						</div>
						<!--��ҳ��ʾ-->
						<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xshForm"></jsp:include>
						<!--��ҳ��ʾ-->
					</logic:notEmpty>
				</div>
				<div id="tmpdiv1"></div>
				<div style="display:none" id="tempDiv" class="tab">
					<table width="300px" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>ְ�����</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="btn">
										<button onclick='updateZw();'>
											ȷ��
										</button>
										<button onclick='hiddenMessage(true,true);'>
											�ر�
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th width="20%">
									<font color='red'>*</font>ְ��
								</th>
								<td>
									<select name='zwdm' id='select_zwdm' style="width:100%"></select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				
				<div style="display:none" id="div_yxstgb" class="tab">
					<table width="300px" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�������Ÿɲ�</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="btn">
										<button onclick='updateSfyxstgb();'>
											ȷ��
										</button>
										<button onclick='hiddenMessage(true,true);'>
											�ر�
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th width="20%">
									<font color='red'>*</font>�������Ÿɲ�
								</th>
								<td>
									<select name="sfyxstgb" id='select_sfyxstgb' style="width:100%">
										<option value="��">��</option>
										<option value="��">��</option>
									</select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
		</html:form>
		<logic:present name="result">
			<logic:equal value="true" name="result">
				<script language="javascript">
	         	alert("�����ɹ���");
	         	document.getElementById('search_go').click();
	         	</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
	       	 	 alert("����ʧ�ܣ�");
				document.getElementById('search_go').click();
	         	</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
