<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/comm/commFunction.js"></script>
	<script type="text/javascript">
		//�������
		function shformdata(url){
			var checkBoxArr = document.getElementsByName("primarykey_cbv");
			var fdyshArr = document.getElementsByName("fdyshArr");
			var userType = document.getElementById('userType').value; 
			var isFdy = document.getElementById('isFdy').value;
			var flag = false;
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					flag = true;
				}
			}
			if (flag){
				for (var i = 0 ; i < checkBoxArr.length ; i++){
					if(checkBoxArr[i].checked==true){
					 	if (jQuery(checkBoxArr[i]).attr('alt')=="disabled"){
							alertInfo("����ѡ�ļ�¼����������˻������<br/>�����ݣ���ȷ��");
							return false;
						} 
					 	if(isFdy!='true' && userType=='xy'){
							if(fdyshArr[i].value!='ͨ��'){
								alertInfo("����ѡ�ļ�¼�����ϼ����δͨ��<br/>�����ݣ���ȷ��");
								return false;
							}
						}
					}
				}
				if (confirm('ȷ��Ҫ�����ѡ���������')){
					document.forms[0].action = url;
					document.forms[0].submit();
					if ($("pt")) {
						BatAlert.showTips('���ڲ�������ȴ�...');
					}
				}
			}else{
				alert("û��ѡ����Ӧ��¼����ѡ��֮���ٽ��в�������");
			}
		}

		//�������
		function shOne(type){
			var pk = '';
			var userType = document.getElementById('userType').value; 
			var isFdy = document.getElementById('isFdy').value;
			if(curr_row){
				pk = curr_row.getElementsByTagName('input')[0].value;
				//if(userType=='xx' || userType=='admin'){
					//alertInfo("�������Ȩ�ޣ�");
					//return false;
				//}
			}
			if('gx'==type){
				var checkBoxArr = document.getElementsByName("primarykey_cbv");
				var fdyshArr = document.getElementsByName("fdyshArr");
				var c = 0;
				for(var i=0;i<checkBoxArr.length;i++){
					if(checkBoxArr[i].checked==true){
						if(checkBoxArr[i].alt=='disabled'){
							alertInfo("����ѡ�ļ�¼����������˻������<br/>�����ݣ���ȷ��");
							return false;
						}
						if(isFdy!='true' && userType=='xy'){
							if(fdyshArr[i].value!='ͨ��'){
								alertInfo("����ѡ�ļ�¼�����ϼ����δͨ��<br/>�����ݣ���ȷ��");
								return false;
							}
						}
						pk = checkBoxArr[i].value;
						c++;
					}
				}
				if(c==0){
					alert('��ѡ����˵�����');
					return false;
				}
				if(c>1){
					alert('ֻ����˵�����¼��');
					return false;
				}
			}else{
				if(curr_row == null){
					alert('��ѡ����˵����ݣ�');
					return false;
				}
				if(isFdy=='true'){
					if(curr_row.getElementsByTagName('input')[2].value!='δ���'){
						alertInfo("������ļ�¼������˻�����ˣ�<br/>��ȷ��");
						return false;
					}
				}else if(userType=='xy'){
					if(curr_row.getElementsByTagName('input')[1].value!='ͨ��'){
						alertInfo("������ļ�¼�ϼ����δͨ����<br/>��ȷ��");
						return false;
					}
				}
			}
			var url = '/xgxt/jqlxgl.do?method=jqlxshDetial';
			url+="&pk="+pk;
			showTopWin(url,'800','600');
		}

		//ɾ��
		function del(checkboxName,url){
			var checkbox = jQuery('input[name='+checkboxName+']:checked');
			if(checkbox.length == 0){
				alertInfo('��ѡ����Ҫɾ��������');
				return false;
			} 
			var checkBoxArr = document.getElementsByName("primarykey_cbv");
			var fdyshArr = document.getElementsByName("fdyshArr");
			var xyshArr = document.getElementsByName("xyshArr");
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					if(checkBoxArr[i].alt=='disabled' || fdyshArr[i].value!='δ���' || xyshArr[i].value!='δ���'){
						alertInfo("����ѡ�ļ�¼����������˻������<br/>�����ݣ���ȷ��");
						return false;
					}
				}
			}
			//��ʾȷ����Ϣ
			confirmInfo('ȷ��Ҫɾ����ѡ���������', function(tag){
				if(tag == 'ok'){
					document.forms[0].action = url;
					document.forms[0].submit();
				}
			});
			if ($("pt")){
				BatAlert.showTips('���ڲ�������ȴ�...');
			}
		}

		//�޸�
		function showViewWindow(checkboxName,url,width,height,currPage,message){
			var pkValues = document.getElementsByName(checkboxName);
			var fdyshArr = document.getElementsByName("fdyshArr");
			var xyshArr = document.getElementsByName("xyshArr");
			var tempArr=new Array(),n=0 ;
			
			for (var i = 0 ; i < pkValues.length ; i++){
				
				if (pkValues[i].checked){
					tempArr[n] = pkValues[i];
					if(pkValues[i].alt=='disabled' || fdyshArr[i].value!='δ���' || xyshArr[i].value!='δ���'){
						alertInfo("����ѡ�ļ�¼����������˻������<br/>�����ݣ���ȷ��");
						return false;
					}
					n++;
				}
			}
			
			if (tempArr.length != 1){
				if (null == message){
					alertInfo("�빴ѡһ����Ҫ�޸ĵ�����");
				} else {
					alertInfo(message);
				}
			
				return false;
			} else if (currPage){
				url+="&pkValue="+tempArr[0].value;
				refreshForm(url);
			} else {
				url+="&pkValue="+tempArr[0].value;
				showTopWin(url,width,height);
			}
		}

	</script>
</head>
	<body onload="xyDisabled('xy');">		
		<html:form action="/jqlxgl" method="post">
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			
			<div class="toolbox">	
			<!-- ��ť -->
			  <div class="buttonbox">
			    <ul>
					<!--��дȨ-->
					<logic:equal value="yes" name="writeAble">
						<logic:notEqual value="xy" name="userType">
						<li> <a href="#" onclick="showViewWindow('primarykey_cbv','jqlxgl.do?method=jqlxsqDetial&buttonCtrl=ok','770','550');" class="btn_xg">�޸�</a> </li>
						<li> <a href="#" onclick="del('primarykey_cbv','jqlxgl.do?method=jqlxsh&doType=del');" class="btn_sc">ɾ��</a> </li>
						</logic:notEqual>
						<logic:equal value="xy" name="userType">
							<logic:equal value="true" name="isFdy" scope="session">
								<li> <a href="#" onclick="showViewWindow('primarykey_cbv','jqlxgl.do?method=jqlxsqDetial&buttonCtrl=ok','770','550');" class="btn_xg">�޸�</a> </li>
								<li> <a href="#" onclick="del('primarykey_cbv','jqlxgl.do?method=jqlxsh&doType=del');" class="btn_sc">ɾ��</a> </li>
							</logic:equal>
						</logic:equal>
						<logic:equal value="true" name="isFdy" scope="session">
							<li> <a href="#" onclick="shOne('gx');" class="btn_sh">�������</a> </li>
							<li> <a href="#" onclick="shformdata('/xgxt/jqlxgl.do?method=jqlxsh&shjg=ͨ��&doType=sh');" class="btn_shtg">���ͨ��</a> </li>
							<li> <a href="#" onclick="shformdata('/xgxt/jqlxgl.do?method=jqlxsh&shjg=��ͨ��&doType=sh');" class="btn_shbtg">��˲�ͨ��</a> </li>
						</logic:equal>
						<logic:notEqual value="true" name="isFdy" scope="session">
						<logic:equal value="xy" name="userType">
							<li> <a href="#" onclick="shOne('gx');" class="btn_sh">�������</a> </li>
							<li> <a href="#" onclick="shformdata('/xgxt/jqlxgl.do?method=jqlxsh&shjg=ͨ��&doType=sh');" class="btn_shtg">���ͨ��</a> </li>
							<li> <a href="#" onclick="shformdata('/xgxt/jqlxgl.do?method=jqlxsh&shjg=��ͨ��&doType=sh');" class="btn_shbtg">��˲�ͨ��</a> </li>
						</logic:equal>
						</logic:notEqual>
					</logic:equal>
					<!-- 
					<li><a href="#" onclick="configureExportData();return false;" class="btn_dc">����</a> </li>
					<li><a href="#" class="btn_qx" onclick="choiceFields();return false;" id="btn_qx">�����ֶ�ȷ��</a></li>
					 -->
					 <logic:equal name="writeAble" value="yes" >
					<li><a href="#" class="btn_dc" onclick="expData('/xgxt/jqlxgl.do?method=jqlxsh&doType=expData');">����</a></li>
					</logic:equal>
					<!--end��дȨ-->
			    </ul>
			  </div>			 
			 <!--��ѯ����-->
			<div class="searchtab">
		    <table width="100%" border="0">
		      <tfoot>
		        <tr>
		          <td colspan="6">
		            <div class="btn">
					  	<input type="hidden" name="go" value="a" />
						<button type="button" class="btn_cx" 
								id="search_go"
								onclick="allNotEmpThenGo('/xgxt/jqlxgl.do?method=jqlxsh')">
							��ѯ
						</button>
		            </div>
		          </td>
		        </tr>
		      </tfoot>
			  <tbody>
		      	<tr>	
		      		<th>�꼶</th>
					<td>
						<html:select property="queryequals_nj" onchange="initZyList();initBjList()" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj" labelProperty="nj"/>
						</html:select>
					</td>				
					<th>ѧ��</th>
					<td>
						<html:text property="querylike_xh" maxlength="20" style="width:180px"></html:text>
					</td>	
					<th>����</th>
					<td>
						<html:text property="querylike_xm" maxlength="20" style="width:180px"></html:text>
					</td>	
				</tr>	
				<tr>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						<html:select property="queryequals_xydm" onchange="initZyList();initBjList()" style="width:180px" styleId="xy">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>
					</td>
					<th>רҵ</th>
					<td>
						<html:select property="queryequals_zydm" onchange="initBjList()" style="width:180px" styleId="zy">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm"
								labelProperty="zymc" />
						</html:select>
					</td>
					<th>�༶</th>
					<td>
						<html:select property="queryequals_bjdm" style="width:180px" styleId="bj">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm"
								labelProperty="bjmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>ѧ��</th>
					<td>
						<html:select property="queryequals_xn" style="width:180px">
							<html:options collection="xnList" property="xn" labelProperty="xn"/>
						</html:select>
					</td>
					<logic:equal value="true" name="isFdy" scope="session">
						<th>��˽��</th>
						<td>
							<html:select property="queryequals_fdysh" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="shztList" property="en" labelProperty="cn"/>
							</html:select>
						</td>
						<th></th>
						<td></td>
					</logic:equal>
					<logic:notEqual value="true" name="isFdy" scope="session">
						<logic:equal value="xy" name="userType">
							<th>��˽��</th>
							<td>
								<html:select property="queryequals_xysh" style="width:180px">
									<html:option value=""></html:option>
									<html:options collection="shztList" property="en" labelProperty="cn"/>
								</html:select>
							</td>
							<th></th>
							<td></td>
						</logic:equal>	
						<logic:notEqual value="xy" name="userType">
							<th></th>
							<td></td>
							<th></th>
							<td></td>
						</logic:notEqual>
					</logic:notEqual>
				</tr>
			  </tbody>
			</table>				
		</div>
		
		
		<div class="formbox" id="result">
			<h3 class="datetitle_01">
		    <span>
		    	��ѯ���&nbsp;&nbsp;
		    	<logic:empty name="rs">
					<font color="red">δ�ҵ��κμ�¼��</font> 
		 		 </logic:empty>
		 		 <logic:notEmpty name="rs">
					<font color="blue">��ʾ��˫�����Բ鿴��ϸ��Ϣ����ˣ�������ͷ��������</font> 
		 		 </logic:notEmpty>
		    </span>
		    </h3>
			<logic:notEmpty name="rs">
			  <div class="con_overlfow">
			  <table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
			    <thead>
			      <tr align="center" style="cursor:hand">
			      		<td><input type="checkbox"/>
			      		</td>
						<logic:iterate id="tit" name="topTr" offset="3" scope="request">
							<td id="${tit.en}"
								onclick="tableSort(this)">
								${tit.cn}
							</td>
						</logic:iterate>
					</tr>
			    </thead>
			    <tbody>
					<logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this)"
							ondblclick="shOne('');"
							style="cursor:hand;background: <logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>">
							<td>
								<input type="checkbox" id="cbv" name="primarykey_cbv" alt="<logic:iterate id="v" name="s" offset="2" length="1">${v}</logic:iterate>" value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
								<input type="hidden" name="fdyshArr" value="<logic:iterate id="v" name="s" offset="12" length="1">${v}</logic:iterate>"/>
								<input type="hidden" name="xyshArr" value="<logic:iterate id="v" name="s" offset="13" length="1">${v}</logic:iterate>"/>	
							</td>
							<logic:iterate id="v" name="s" offset="3">
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
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=jqlxForm"></jsp:include>
			    <!--��ҳ��ʾ-->
			</logic:notEmpty>
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
	       	 	 alert("" + $('message').value);
				document.getElementById('search_go').click();
	         	</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
