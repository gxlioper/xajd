<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript">
	function allFilleds(val) {
		var arrayList = val.split('-');
		for (var i=0; i<arrayList.length;i++) {
		if ($(arrayList[i])) {
			if (document.getElementById(arrayList[i]).value=='') {
				alert("�����������Ϊ��!");
				return false;
			}
		}
	    }
	 var val =  $("val");
	 var valV =val.value.replace(/[^(\d|\.)]/g,'');
	 if(!valV.match(/\d+/g)){		
		alert("����ֵ��Ϊ�������͵����ݣ�");
		$("val").focus()
		return false;
	 }
	 if($("zdm").value=="cpzfpm"){
	     if(val.value.indexOf(".")!=-1){
	       alert("���������ֵ����");
	       return false;
	     }
	 }
	 //chkInput(val);
	 var zdmc = document.forms[0].zdm.options[document.forms[0].zdm.selectedIndex].text;
     document.forms[0].go.value='go';refreshForm('/xgxt/prise_condition.do?zdmc='+zdmc);return true;
	}
	function zhcpClin(){
	    var tjVal = $("zdm").value;
	    if(tjVal=="cpzfpm"){
	       $("zhszcppm2").innerText = "  %";
	    }else{
	       $("zhszcppm1").innerText = "";
	       $("zhszcppm2").innerText = "";	       
	    }
	}
</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<html:form action="/prise_condition" method="post">
		
			<div class="tab_cur">
					<p class="location">
						<em>��ǰ����λ�ã�</em><a>�������� - �������� - ��������</a>
					</p>
				</div>	
<input type="hidden" name="search_go" id="search_go"
					onclick="refreshForm('/xgxt/prise_condition.do')" />
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
						<ul>
					<logic:notEqual value="10656" name="xxdm">
					<li><a class="btn_sc" onclick="delPriseCondi()">
						ɾ������
					</a></li>
					<li>
					<a class="btn_hsz" onclick="delAllPriseCondi()">
						�������
					</a>
					</li>
			</logic:notEqual>
						</ul>
					</div>
			
				<div class="searchtab">
					<table width="100%" border="0">
					
						<tbody>
								<tr>
									<th>
										ѧ��
										</th>
										<td>
											<html:select property="xn" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
										</td>
										<logic:notEqual value="10656" name="xxdm">
								<th>��ѧ��</th>
								<td>
								<html:select property="xmdm" style="width:200px" styleId="jxjdm"
										onchange="refreshForm('/xgxt/prise_condition.do')">
										<html:option value=""></html:option>
										<html:options collection="jxjList" property="jxjdm"
											labelProperty="jxjmc" />
									</html:select>
									</td>
								</logic:notEqual>
									<td colspan="">
								<logic:equal name="xxdm" value="10656" scope="session">
								<logic:notEqual value="xy" name="userType">
									
										<button type="button" class="button2"
											onclick="if(!check('xn')) return false;showTop($('xn').value);">
											�趨����
										</button>
									
									</logic:notEqual>
								</logic:equal>
									</td>
								</tr>
								<tr>
									<logic:notEqual value="10656" name="xxdm">
									<th>����</th>
									<td colspan="4"> 
								<logic:equal value="12865" name="xxdm">
										<html:select property="zdm" styleId="zdm" onchange="zhcpClin()">
											<html:option value=""></html:option>
											<html:options collection="zdList" property="en"
												labelProperty="cn" />
										</html:select>
										
								--&gt;
								<select name="ysf" id="ysf" style="width:120px">
											<option value=""></option>
											<option value="&gt;=">
												���ڻ����
											</option>
										</select>
									</logic:equal>

									<logic:notEqual value="12865" name="xxdm">
										<html:select property="zdm" style="width:120px" styleId="zdm">
											<html:option value=""></html:option>
											<html:options collection="zdList" property="zd"
												labelProperty="zdsm" />
										</html:select>														
								--&gt;
								<select name="ysf" id="ysf" style="width:120px">
											<option value=""></option>
											<option value="&gt;=">
												���ڻ����
											</option>
<%--											<option value="=">--%>
<%--												����--%>
<%--											</option>--%>
<%--											<option value="&gt;">--%>
<%--												����--%>
<%--											</option>--%>
<%--											<option value="&lt;">--%>
<%--												С��--%>
<%--											</option>--%>
<%--											<option value="&lt;=">--%>
<%--												С�ڻ����--%>
<%--											</option>--%>
										</select>
									</logic:notEqual>								
								--&gt;
							   <span id="zhszcppm1"></span>
								<input type="text" name="val" value="" style="width:120px;ime-mode:disabled" maxlength="5" onkeypress='return sztzNumInputValue(this,6,event)' />
							   <span id="zhszcppm2"></span>	
									<input type="hidden" name="go" value="a" />
									<button type="button" class=""
										onclick="allFilleds('xn-jxjdm-zdm-val-ysf-zdcz')">
										�������
									</button>
								</logic:notEqual>
								&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											�� ��
										</button>
								</td>
								</tr>
						</tbody>
					</table>
				</div>
			</div>	
			
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��������
						</span>
				</h3>

				<logic:notEmpty name="rs">
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td align="center" id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							
<%--								<td onclick="tableSort(this)">--%>
<%--									���--%>
<%--								</td>--%>
<%--								<td onclick="tableSort(this)">--%>
<%--									��ѧ��--%>
<%--								</td>--%>
<%--								<td onclick="tableSort(this)">--%>
<%--									�����ֶ�--%>
<%--								</td>--%>
<%--								<td onclick="tableSort(this)">--%>
<%--									��������--%>
<%--								</td>--%>
<%--								<td onclick="tableSort(this)">--%>
<%--									����--%>
<%--								</td>--%>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rs" id="s">
							<tr align="left" onclick="rowOnClick(this)" style="cursor:hand">
<%--								<input type="hidden" value="${rs.pk }" />--%>
<%--								<td>--%>
<%--									<bean:write name="rs" property="rownum" />--%>
<%--								</td>--%>
<%--								<td>--%>
<%--									<bean:write name="rs" property="jxjmc" />--%>
<%--								</td>--%>
<%--								<td>--%>
<%--									<bean:write name="rs" property="tjzdm" />--%>
<%--								</td>--%>
<%--								<td>--%>
<%--									<bean:write name="rs" property="zdcz" />--%>
<%--								</td>--%>
<%--								<td>--%>
<%--									<bean:write name="rs" property="tj" />--%>
<%--								</td>--%>                             
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2" >
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
					<!--��ҳ��ʾ-->
					
					<!--��ҳ��ʾ-->
				</logic:notEmpty>
				
				
				<logic:notEmpty name="res">
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center">
							<td onclick="tableSort(this)">
								�к�
							</td>
							<td onclick="tableSort(this)">
								ѧ��
							</td>
							<td onclick="tableSort(this)">
								�ɼ���ռ����(%)
							</td>
							<td onclick="tableSort(this)">
								�ۺ�������ռ����(%)
							</td>
						</tr>
						</thead>
						<tbody>
							<logic:iterate id="r" name="res" scope="request">
						<tr align="center" onclick="rowOnClick(this)" style="cursor:hand">
							<logic:iterate id="v" name="r" offset="0">
								<td align=center>
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
						</tbody>
					</table>
					<!--��ҳ��ʾ-->
					
					<!--��ҳ��ʾ-->
				</logic:notEmpty>
				
			</div>

			<logic:equal value="true" name="flag">
				<script>
					alert('�����ɹ���');
				</script>
			</logic:equal>
			<logic:equal value="false" name="flag">
				<script>
					alert('����ʧ�ܣ�');
				</script>
			</logic:equal>
			<logic:present name="add">
			<logic:equal name="add" value = "yes">
				<script language="javascript">
					alert("���������ɹ�");
				</script>
			</logic:equal>
			<logic:equal name="add" value = "no">
				<script language="javascript">
					alert("��������ʧ�ܣ���ȷ��û�и�����������������");
				</script>
			</logic:equal>
			</logic:present>
		</html:form>

	</body>
</html>
