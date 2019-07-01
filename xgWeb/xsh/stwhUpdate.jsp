<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<%@ taglib uri="/WEB-INF/customTag.tld" prefix="customTag"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript">
	function setFzr(obj) {
			var text = obj.value.trim();
			if (text=='ѧ��') {
				document.getElementById('buttonFindStu').style.display = '';
				DWRUtil.setValue('fzr','');
			} else {
				document.getElementById('buttonFindStu').style.display = 'none';
				DWRUtil.setValue('fzr',$('userName').value);
			}
	}
	
	function setFzrByAdmin(obj){
		var text = obj.value.trim();
		if (text == '��ʦ') {
			document.getElementById('fzrButton').style.display = '';
			document.getElementById('stuButton').style.display = 'none';
			
		} else if (text == 'ѧ��'){
			document.getElementById('fzrButton').style.display = 'none';
			document.getElementById('stuButton').style.display = '';
		} else {
			document.getElementById('stuButton').style.display = 'none';
			document.getElementById('fzrButton').style.display = 'none';
		}
		DWRUtil.setValue('fzr','');
	}
	
	
	function setBmdm(obj) {
		var text = obj.value.trim();
		
		if (text == 'ѧУ'){
			$('bmdm').value = "";
			$('bmdm').disabled=true;
		} else {
			var flg;
			$('bmdm').disabled=false;
			
			if (text == jQuery("#xbmc").val()) {
				flg='xy';
			} else if (text == 'רҵ') {
				flg='zy';
			} else {
				flg='bj';
			}
			
			dwr.engine.setAsync(false);
			GetListData.getXyZyBj(flg,$('userType').value,$('userDep').value,function(data){
				DWRUtil.removeAllOptions('bmdm');	
				
				DWRUtil.addOptions('bmdm',["----��ѡ��-----"]);	
				DWRUtil.addOptions('bmdm',data,"dm","mc");	
				if ('' != $('bmdmValue').value){
					DWRUtil.setValue('bmdm', $('bmdmValue').value);
				}
			})
			dwr.engine.setAsync(true);
			
			if ("" != $('bmdmTempValue').value){
				$('bmdm').value = $('bmdmTempValue').value;
			}
			
			if ("" != $('fzrlbTempValue').value){
				$('fzrlb').value = $('fzrlbTempValue').value;
			}
		}
		
	}
	
	function loadZdmc(){
		if($("zdmc") && $("zdmc").value=="stu"){
			document.getElementById('fzrButton').style.display = 'none';
			document.getElementById('stuButton').style.display = '';
			$('fzrlb').value ="ѧ��"
		}
		
	}
	
</script>
	</head>
	<body onload="setBmdm($('stxz'));loadZdmc()">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/xsh" method="post">
			<input type="hidden" name="pk" value="${pk }" />
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<input type="hidden" value="${rs.bmdm }" id="bmdmValue" />
			<input type="hidden" id="url" name="url"
				value="/xsh.do?method=stwhUpdate&doType=${doType }&pk=${pk }" />
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh" />
			<input type="hidden" name="xh" value="${xh }" id="xh" />
			<input type="hidden" name="userType" value="${userType }"
				id="userType" />
			<input type="hidden" name="userDep" value="${userDep }" id="userDep" />
			<input type="hidden" name="userName" value="${userName }"
				id="userName" />
			<input type="hidden" id="bmdmTempValue" value="${xshForm.bmdm }" />
			<input type="hidden" id="fzrlbTempValue" value="${xshForm.fzrlb }" />
			<input type="hidden" id="zdmc"  name ="zdmc" value="${zdmc}" />	
			<logic:present name="rs" property="stclsj">
				<input type="hidden" name="stclsj" value="${rs.stclsj }" />
			</logic:present>
			<logic:notPresent name="rs" property="stclsj">
				<input type="hidden" name="stclsj" value="${now }" />
			</logic:notPresent>

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>����ά��</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<logic:notEqual value="view" name="doType">
										<logic:notEqual value="update" name="doType">
											<button id="buttonSave"
												onclick="saveUpdate('/xgxt/xsh.do?method=stwhUpdate&doType=save','stmc-stxz');">
												����
											</button>
										</logic:notEqual>
									</logic:notEqual>

									<logic:equal value="update" name="doType">
										<button id="buttonSave"
											onclick="saveUpdate('/xgxt/xsh.do?method=stwhUpdate&doType=modify','stmc-stxz');">
											����
										</button>
									</logic:equal>
									<button id="buttonSave" onclick="window.close();return false;">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>��������
							</th>
							<td width="30%">
								<logic:notEqual value="update" name="doType">
									<logic:equal value="view" name="doType">
										<html:text property="stmc" maxlength="25" name="rs"
											onkeyup="chgPkValue(this)"></html:text>
									</logic:equal>
									<logic:notEqual value="view" name="doType">
										<html:text property="stmc" maxlength="25"
											onkeyup="chgPkValue(this)"></html:text>
									</logic:notEqual>
								</logic:notEqual>
								<logic:equal value="update" name="doType">
									<html:text property="stmc" maxlength="25" name="rs"
										readonly="true" onkeyup="chgPkValue(this)"></html:text>
								</logic:equal>
							</td>
							<th width="16%">
								��ʼ��
							</th>
							<td width="30%">
								<logic:notEqual value="update" name="doType">
									<logic:notEqual value="view" name="doType">
										<html:text property="stcsr" value="${userName }"
											readonly="true"></html:text>
									</logic:notEqual>
								</logic:notEqual>
								<logic:equal value="update" name="doType">
									<html:text property="stcsr" name="rs" readonly="true"></html:text>
								</logic:equal>
								<logic:equal value="view" name="doType">
									<html:text property="stcsr" name="rs" readonly="true"></html:text>
								</logic:equal>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��������
							</th>
							<td>
								<logic:notEqual value="view" name="doType">
									<logic:notEqual value="update" name="doType">
										<html:select property="stxz" onchange="setBmdm(this)"
											styleId="stxz">
											<html:option value=""></html:option>
											<html:options collection="list" property="en"
												labelProperty="cn" />
										</html:select>
									</logic:notEqual>
								</logic:notEqual>
								<logic:equal value="view" name="doType">
									<html:select property="stxz" onchange="setBmdm(this)" name="rs"
										disabled="true">
										<html:option value=""></html:option>
										<html:options collection="list" property="en"
											labelProperty="cn" />
									</html:select>
								</logic:equal>
								<logic:equal value="update" name="doType">
									<html:select property="stxz" onchange="setBmdm(this)" name="rs"
										styleId="stxz">
										<html:option value=""></html:option>
										<html:options collection="list" property="en"
											labelProperty="cn" />
									</html:select>
								</logic:equal>
							</td>
							<th>
								<logic:notPresent name="cdtyxg">
									��������
								</logic:notPresent>
								<logic:present name="cdtyxg">
									�ҿ�<bean:message key="lable.xb" />
								</logic:present>
							</th>
							<td>
								<html:select property="bmdm" name="rs" styleId="bmdm"
									style="width:200px">
									<html:option value=""></html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								ָ����ʦ
							</th>
							<td>
								<logic:equal value="view" name="doType">
									${rs.zdls }
								</logic:equal>
								<logic:equal value="update" name="doType">
									<html:text property="zdls" readonly="true" styleId="zdls"
										name="rs"></html:text>
									<button
										onclick="showTopWin('/xgxt/xsh.do?method=jsInfo&zdmc=zdls',750,500);"
										class="btn_01" id="zdlsButton">
										ѡ��
									</button>
								</logic:equal>
								<logic:notEqual value="view" name="doType">
									<logic:notEqual value="update" name="doType">
										<html:text property="zdls" readonly="true" styleId="zdls"></html:text>
										<button
											onclick="showTopWin('/xgxt/xsh.do?method=jsInfo&zdmc=zdls',750,500);"
											class="btn_01" id="zdlsButton">
											ѡ��
										</button>
									</logic:notEqual>
								</logic:notEqual>
							</td>
							<th>
								��ص�
							</th>
							<td>
								<logic:equal value="view" name="doType">
									${rs.sthddd }
								</logic:equal>
								<logic:equal value="update" name="doType">
									<html:text property="sthddd" maxlength="50" name="rs"></html:text>
								</logic:equal>
								<logic:notEqual value="view" name="doType">
									<logic:notEqual value="update" name="doType">
										<html:text property="sthddd" maxlength="50"></html:text>
									</logic:notEqual>
								</logic:notEqual>
							</td>
						</tr>
						<logic:notEqual value="view" name="doType">
							<tr>
								<th>
									���������
								</th>
								<td>
									<logic:equal value="stu" name="userType" scope="session">
										<html:select property="fzrlb" styleId="fzrlb" disabled="true"
											value="${rs.fzrlb }">
											<html:option value=""></html:option>
											<html:option value="ѧ��">ѧ��</html:option>
											<html:option value="��ʦ">��ʦ</html:option>
										</html:select>
									</logic:equal>
									<logic:equal value="xy" name="userType" scope="session">
										<html:select property="fzrlb" styleId="fzrlb" onchange="setFzr(this);"
											value="${rs.fzrlb }">
											<html:option value=""></html:option>
											<html:option value="ѧ��">ѧ��</html:option>
											<html:option value="��ʦ">��ʦ</html:option>
										</html:select>
									</logic:equal>
									<logic:notEqual value="xy" name="userType" scope="session">
										<logic:notEqual value="stu" name="userType" scope="session">
											<html:select property="fzrlb" styleId="fzrlb" onchange="setFzrByAdmin(this);"
												value="${rs.fzrlb }">
												<html:option value=""></html:option>
												<html:option value="ѧ��">ѧ��</html:option>
												<html:option value="��ʦ">��ʦ</html:option>
											</html:select>
										</logic:notEqual>
									</logic:notEqual>
								</td>
								<th>
									������
								</th>
								<td>
									<logic:equal value="stu" name="userType" scope="session">
										<html:text property="fzr" readonly="true" value="${userName }"
											name="rs"></html:text>
									</logic:equal>
									<logic:equal value="xy" name="userType" scope="session">
										<html:text property="fzr" readonly="true" styleId="fzr"
											value="${xh }" name="rs"></html:text>

										<button onclick="showTopWin('/xgxt/stu_info.do',800,600);"
											class="btn_01" id="buttonFindStu" style="display :none">
											ѡ��
										</button>
									</logic:equal>
									<logic:notEqual value="stu" name="userType" scope="session">
										<logic:notEqual value="xy" name="userType" scope="session">

											<logic:present name="xh">
												<html:text property="fzr" readonly="true" value="${xh}"
													styleId="fzr"></html:text>
											</logic:present>
											<logic:notPresent name="xh">
												<html:text property="fzr" readonly="true" value="${rs.fzr}"
													styleId="fzr"></html:text>
											</logic:notPresent>

											<button
												onclick="showTopWin('/xgxt/xsh.do?method=jsInfo&zdmc=fzr',750,500);"
												class="btn_01" id="fzrButton" style="display :none">
												ѡ��
											</button>
											<button onclick="showTopWin('/xgxt/stu_info.do?zdmc=stu',800,600);"
												class="btn_01" id="stuButton" style="display :none">
												ѡ��
											</button>
										</logic:notEqual>
									</logic:notEqual>
								</td>
							</tr>
							<logic:present name="cdtyxg">
							<tr>
								<th>
									�Ƿ���������
								</th>
								<td>
									<html:select name="rs" property="sfyxst" styleId="sfyxst">
										<html:option value=""></html:option>
										<html:option value="��">��</html:option>
										<html:option value="��">��</html:option>
									</html:select>
								</td>
								<th>
									
								</th>
								<td>
									
								</td>
							</tr>
							</logic:present>
						</logic:notEqual>


						<logic:equal value="view" name="doType">
							<tr>
								<th>
									������
								</th>
								<td>
									${rs.fzr }
								</td>
								<logic:present name="cdtyxg">
									<th>
										�Ƿ���������
									</th>
									<td>
										${rs.sfyxst }
									</td>
								</logic:present>
								<logic:notPresent name="cdtyxg">
									<th></th>
									<td></td>
								</logic:notPresent>
							</tr>
						</logic:equal>
						<tr>
							<th>
								������ּ
							</th>
							<td colspan="3" style="word-break:break-all;">
								<logic:equal value="view" name="doType">
									${rs.stzz }
								</logic:equal>
								<logic:equal value="update" name="doType">
									<html:textarea property="stzz" rows="8" style="width:90%"
										onblur="checkLen(this,400)" name="rs"></html:textarea>
								</logic:equal>
								<logic:notEqual value="view" name="doType">
									<logic:notEqual value="update" name="doType">
										<html:textarea property="stzz" rows="8" style="width:90%"
											onblur="checkLen(this,400)"></html:textarea>
									</logic:notEqual>
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th>
								���ʽ
							</th>
							<td colspan="3" style="word-break:break-all;">
								<logic:equal value="view" name="doType">
									${rs.hdxs }
								</logic:equal>
								<logic:equal value="update" name="doType">
									<html:textarea property="hdxs" rows="8" style="width:90%"
										onblur="checkLen(this,400)" name="rs"></html:textarea>
								</logic:equal>
								<logic:notEqual value="view" name="doType">
									<logic:notEqual value="update" name="doType">
										<html:textarea property="hdxs" rows="8" style="width:90%"
											onblur="checkLen(this,400)"></html:textarea>
									</logic:notEqual>
								</logic:notEqual>
							</td>
						</tr>
					</tbody>
					<customTag:customTable rsname="rs" nodeslist="xshForm"
						doType="updata" scope="request" />
				</table>
			</div>
		</html:form>
		<logic:present name="result">
			<logic:equal value="true" name="result">
				<script language="javascript">
	         	alert("����ɹ���");
	         	if(window.dialogArguments){
					 window.close();
					 window.dialogArguments.document.getElementById('search_go').click();
				}
	         	</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	</script>
			</logic:equal>
		</logic:present>
	</body>