<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>

	<script type='text/javascript' src='/xgxt/dwr/interface/getDtjsInfo.js'></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script type="text/javascript">	
		 function checAll(name){
			var checs = document.getElementsByName(name);
			var ch = $('cheAll');

			if(ch.checked){
				for(var i=0; i<checs.length; i++){
					if(!checs[i].checked){
						checs[i].checked = "checked"
					}
				}
			}else {
				for(var i=0; i<checs.length; i++){
					if(checs[i].checked){
						checs[i].checked = ""
					}
				}
			}
	 	 }

		 function revAll(name){
	 		var checs = document.getElementsByName(name);
	
			for(var i=0; i<checs.length; i++){
				checs[i].checked = (checs[i].checked ? "" : "checked");
			}
	 	 }

	 	 function hid_tr(s_tr,h_tr){
		 	 document.getElementById(s_tr).style.display = "";
		 	 document.getElementById(h_tr).style.display = "none";
	 	 }

	 	 function loadRsfp(){
			var fpfs = document.getElementsByName('fpfs');

			for(var i=0;i<fpfs.length;i++){
				if(fpfs[i].checked == true){
					fpfs[i].click();
				}
			}
		 }
		 
		 function checkSave(){
		 	var flag = false;
		 	var ches = document.getElementsByName('params');
		 	
		 	for(var i=0; i<ches.length; i++){
		 		if(ches[i].checked){
		 			flag = true;
		 			break;
		 		}
		 	}
		 	
		 	if(flag){
		 		refreshForm('pjpy_comm_rssz.do?method=saveRssz');
		 	}else {
		 		alert('���ȹ�ѡ�������ò��ţ�');
		 	}
		 }
	</script>
	</head>
	
	<body onload="loadRsfp();">
		<html:form action="/pjpy_comm_rssz">
			<input type="hidden" name="szfw" value="${szfw }"/>
			<input type="hidden" name="xmdm" value="${xmdm }"/>
			<input type="hidden" id="userStatus" value="${user.userStatus }"/>
			<input type="hidden" id="userName" value="${user.userName }"/>
			<input type="hidden" id="userDep" value="${user.userDep }"/>
			
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			
			 <table class="formlist" style="margin-bottom:4px" >
    			<thead>
					<tr>
						<td >
							<span>
								<font color="blue">
								ע�����÷�ΧΪ
								<logic:equal name="szfw" value="xy"><bean:message key="lable.xb" /></logic:equal>
								<logic:equal name="szfw" value="zy">רҵ</logic:equal>
								<logic:equal name="szfw" value="bj">�༶</logic:equal>
								<logic:equal name="szfw" value="nj">�꼶</logic:equal>
								</font>
							</span>	
						</td>
					</tr>
				</thead>
			</table>
			
			<div class="leftframe04">
				<%@include file="bmTree.jsp" %>
		    </div>
		    
		    <div class="rightframe04">
			   	<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��ϸ����</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
						<tr>
							<th>����ѧ��</th>
							<td>
								<html:select property="pjxn" style="width: 120px" styleId="xn" disabled="true">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</td>
							<th>����ѧ��</th>
							<td>
								<html:select property="pjxq" style="width: 120px" styleId="xq" disabled="true">
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>�������</th>
							<td>
								<html:select property="pjnd" style="width: 120px" styleId="nd" disabled="true">
									<html:options collection="ndList" property="nd" labelProperty="nd" />
								</html:select>
							</td>
							<th>��Ŀ����</th>
							<td>
								<html:select property="xmdm" style="width:150px" disabled="true">
									<html:options collection="pjxmList" property="xmdm" labelProperty="xmmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>���÷�Χ</th>
							<td>
								<html:select property="szfw" disabled="true">
									<html:option value="xy"><bean:message key="lable.xb" /></html:option>
									<html:option value="zy">רҵ</html:option>
									<html:option value="bj">�༶</html:option>
									<html:option value="nj">�꼶</html:option>
								</html:select>
							</td>
							<th></th><td></td>
						</tr>
					</tbody>
					<thead>
						<tr><th colspan="4"><span>������Ϣ</span></th></tr>
					</thead>
					
					<tbody>
						<tr>
							<th>���䷽ʽ</th>
							<td>
								<label><html:radio property="fpfs" value="bl" onclick="hid_tr('tr_bl','tr_rs');"/>����������</label>
<!--								<label><html:radio property="fpfs" value="rs" onclick="hid_tr('tr_rs','tr_bl');"/>����������</label>-->
							</td>
							<td colspan="2">
								<logic:present name="rssx">
									<font color="red">ע������Ŀ��������Ϊ${rssx }</font>
								</logic:present>
								
								<logic:notPresent name="rssx">
									<font color="red">ע������Ŀ����������</font>
								</logic:notPresent>
							</td>
						</tr>
						<tr id="tr_bl">
							<th>����</th>
							<td colspan="3"><html:text property="bl"></html:text>%</td>
						</tr>
						<tr id="tr_rs" style="display: none"> 
							<th>����</th>
							<td colspan="3"><html:text property="rs"></html:text>��</td>
						</tr>
					</tbody>
					<tfoot>
					<tr>
						<td colspan="4" id="footId">
							<div align="right">
							<button type="button" name="����" class="" onclick="checkSave();">
								�� ��
							</button>
							<button type="button" name="ȡ��" class="" onclick="window.close();return false;">
								�� ��
							</button>
							</div>
						</td>
					</tr>
				</tfoot>
				</table>
		    </div>
			<div id="tmpdiv1"></div>
			<logic:present name="message">
				<input type="hidden" id="message" value="${message }"/>
					<script>
						alert($('message').value);
						dialogArgumentsQueryChick();
						window.close();
					</script>
			</logic:present>
		</html:form>
	</body>
</html>
