<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsxx/comm/jbsz/ymsz.js"></script>
		<script language="javascript" defer="defer">
		//��ѯ���
		function searchRs(){
			allNotEmpThenGo('/xgxt/xsxxJbsz.do?method=xsqsz');
		}
		
		//������ʾ������
		function saveXsqsz(){
			var hadRs = $("hadRs").value;
			if(hadRs == "yes"){
				if (confirm("����ҳ����ʾ����ʾ�����ã���ȷ�ϣ�")) {
					saveUpdate('/xgxt/xsxxJbsz.do?method=xsqsz&doType=save','');
				}
			}else{
				alert("���Ȳ�ѯ����Ҫ���õ���ʾ������ִ�иò�����");
			}
		}
		
		//�����������ò�
		function showPlszDiv(){
		
			var num = document.getElementsByName("primarykey_checkVal").length;
			var flag = false;
			
			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("primarykey_checkVal")[i];
				if(obj.checked == true){
					flag = true;
					break;
				}
			}
			
			if(flag){
				viewTempDiv("��ʾ������","xsqszDiv",350,200);
			}else{
				alert("�빴ѡ��Ҫ���õ���ʾ����");
			}
		}
		
				//������������
		function setPlsz(){
		
			var num = document.getElementsByName("primarykey_checkVal").length;
			var flag = false;
			
			//�Ƿ�չ��
			var sfzk = $("sz_sfzk_value").value;
			//�Ƿ�����
			var sfqy = $("sz_sfqy_value").value;
			//�Ƿ��ö�
			var sfzd = $("sz_sfzd_value").value;
	
			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("primarykey_checkVal")[i];
				if(obj.checked == true){
					//�Ƿ�չ��
					if(sfzk != "������"){
						for(var j=0;j<document.getElementsByName("sfzk"+i).length;j++){
						
							if(document.getElementsByName("sfzk"+i)[j].value == sfzk){
								document.getElementsByName("sfzk"+i)[j].checked = true;
								break;
							}
						}
						$("hid_sfzk_"+i).value=sfzk;
					}
					//�Ƿ�����
					if(sfqy != "������"){
						for(var j=0;j<document.getElementsByName("sfqy"+i).length;j++){
							if(document.getElementsByName("sfqy"+i)[j].value == sfqy){
								document.getElementsByName("sfqy"+i)[j].checked = true;
								break;
							}
						}
						$("hid_sfqy_"+i).value=sfqy;
					}
					//�Ƿ��ö�
					if(sfzd != "������"){
						for(var j=0;j<document.getElementsByName("sfzd"+i).length;j++){
							if(document.getElementsByName("sfzd"+i)[j].value == sfzd){
								document.getElementsByName("sfzd"+i)[j].checked = true;
								break;
							}
						}
						$("hid_sfzd_"+i).value=sfzd;
					}
				}
			}
			
			hiddenMessage(true,true);
		}
		
		</script>
	</head>
	<body onload="">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/xsxxJbsz">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="hadRs" id="hadRs" value="${hadRs }"/>

			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#"
									onclick="saveXsqsz();return false;"
									class="btn_ccg">����</a>
							</li>
							<li>
								<a href="#"
									onclick="showPlszDiv();return false;"
									class="btn_sz">����</a>
							</li>
						</logic:equal>
					</ul>
				</div>
				<!-- ��ť end-->	
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										
										<input type="hidden" name="go" value="a"/>
										<button type="button" class="btn_cx" id="search_go" onclick="searchRs();return false;">
											�� ѯ
										</button>
										<button type="button" class="btn_cz" id="btn_cz" 
											onclick="czSearchCond('sfzk-sfzd-sfqy-xsqmc');">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- ��һ�� -->
							<tr>
								<th>
									�Ƿ�Ĭ��չ��
								</th>
								<td>
									<html:select property="xsqsfzk" style="width: 150px" styleId="sfzk">
										<html:option value=""></html:option>
										<html:options collection="sfList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>
									�Ƿ��ö�
								</th>
								<td>
									<html:select property="xsqsfzd" style="width: 150px" styleId="sfzd">
										<html:option value=""></html:option>
										<html:options collection="sfList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>
									�Ƿ�����
								</th>
								<td>
									<html:select property="xsqsfqy" style="width: 150px" styleId="sfqy">
										<html:option value=""></html:option>
										<html:options collection="sfList" property="en" labelProperty="cn" />
									</html:select>
								</td>
							</tr>
							<!-- �ڶ��� -->
							<tr>
								<th>
									��ʾ������
								</th>
								<td>
									<html:text property="search_xsqmc" 
										style="" maxlength="20" styleId="xsqmc"
										onkeypress="if(pressEnter(event)){searchRs();return false;}"/>
								</td>
								<th>
									
								</th>
								<td>
									
								</td>
								<th>
									
								</th>
								<td>
									
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- �������� end-->
			</div>
			<!-- �๦�ܲ����� end -->
			
			<!-- ��ѯ���-->
			<div class="formbox">		
				<h3 class="datetitle_01">
					<span>
						��ѯ���
						<logic:empty name="rsArrList">
							&nbsp;&nbsp;<font color="red">δ�ҵ��κμ�¼��</font> 
						</logic:empty>
						<logic:notEmpty name="rsArrList">
							&nbsp;&nbsp;<font color="blue">��ʾ˳��������2λ���֣����Ϊ�գ�ϵͳ����Ϊ��������ĩβ</font> 
						</logic:notEmpty>
					</span>
				</h3>
				
				<table summary="" class="dateline" align="" width="100%">
					<!-- ��ͷ -->
					<thead>
						<tr align="center" style="cursor:hand">
							<td width="5px">
								<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
							</td>
							<logic:iterate id="tit" name="topTr" offset="1">
								<td id="<bean:write name="tit" property="en"/>"
<%--									onclick="tableSort(this)" --%>
									nowrap>
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<!-- ��ͷ end-->
					<!--���� -->
					<logic:equal name="hadRs" value="yes">
						<logic:iterate name="rsArrList" id="s" indexId="index">
							<tr onclick="rowOnClick(this);" style="cursor:hand">
								<td align="center" width="5px">
									<input type="checkbox" id="checkVal" 
										name="primarykey_checkVal"  
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"/>
								</td>
								<!-- ��ʾ������ -->
								<logic:iterate id="v" name="s" offset="0" length="1">
									<td align="left" style="display: none">
										${v }
										<input type="hidden" name="xsqdm" id="xsqdm${index }" value="${v }"/>
									</td>
								</logic:iterate>
								<!-- ��ʾ������ -->
								<logic:iterate id="v" name="s" offset="1" length="1">
									<td align="left" nowrap="nowrap">
										${v }
									</td>
								</logic:iterate>
								<!-- ��ʾ˳�� -->
								<logic:iterate id="v" name="s" offset="2" length="1">
									<td align="left" nowrap="nowrap">
										<input type="text" name="xssx" id="xssx${index }" 
											value="${v }" style="width: 70px"
											onkeydown="return onlyNum(this,2)"
											onmousedown="return onlyNum(this,2)"
											maxlength="2"
											style="width : 50%;ime-mode:disabled"/>
									</td>
								</logic:iterate>
								<!-- �Ƿ�չ�� -->
								<logic:iterate id="v" name="s" offset="3" length="1">
									<td align="left" nowrap="nowrap">
										<logic:equal name="v" value="��">
											<input type="radio" name="sfzk${index }" value="��" onclick="clickRadioValue(${index },'sfzk',this.value)" checked="checked"/>��
											<input type="radio" name="sfzk${index }" value="��" onclick="clickRadioValue(${index },'sfzk',this.value)"/>��
										</logic:equal>
										<logic:equal name="v" value="��">
											<input type="radio" name="sfzk${index }" value="��" onclick="clickRadioValue(${index },'sfzk',this.value)"/>��
											<input type="radio" name="sfzk${index }" value="��" onclick="clickRadioValue(${index },'sfzk',this.value)" checked="checked"/>��
										</logic:equal>
										<input type="hidden" name="sfzk" id="hid_sfzk_${index }" value="${v }"/>
									</td>
								</logic:iterate>
								<!-- �Ƿ����� -->
								<logic:iterate id="v" name="s" offset="4" length="1">
									<td align="left" nowrap="nowrap">
										<logic:equal name="v" value="��">
											<input type="radio" name="sfqy${index }" value="��" onclick="clickRadioValue(${index },'sfqy',this.value)" checked="checked"/>��
											<input type="radio" name="sfqy${index }" value="��" onclick="clickRadioValue(${index },'sfqy',this.value)"/>��
										</logic:equal>
										<logic:equal name="v" value="��">
											<input type="radio" name="sfqy${index }" value="��" onclick="clickRadioValue(${index },'sfqy',this.value)"/>��
											<input type="radio" name="sfqy${index }" value="��" onclick="clickRadioValue(${index },'sfqy',this.value)" checked="checked"/>��
										</logic:equal>
										<input type="hidden" name="sfqy" id="hid_sfqy_${index }" value="${v }"/>
									</td>
								</logic:iterate>
								<!-- �Ƿ��ö� -->
								<logic:iterate id="v" name="s" offset="5" length="1">
									<td align="left" nowrap="nowrap">
										<logic:equal name="v" value="��">
											<input type="radio" name="sfzd${index }" value="��" onclick="clickRadioValue(${index },'sfzd',this.value)" checked="checked"/>��
											<input type="radio" name="sfzd${index }" value="��" onclick="clickRadioValue(${index },'sfzd',this.value)"/>��
										</logic:equal>
										<logic:equal name="v" value="��">
											<input type="radio" name="sfzd${index }" value="��" onclick="clickRadioValue(${index },'sfzd',this.value)"/>��
											<input type="radio" name="sfzd${index }" value="��" onclick="clickRadioValue(${index },'sfzd',this.value)" checked="checked"/>��
										</logic:equal>
										<input type="hidden" name="sfzd" id="hid_sfzd_${index }" value="${v }"/>
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</logic:equal>
					<!--���� end-->
					<!-- ������ -->
					<%@ include file="/comm/noRows.jsp"%>
					<!-- ������ end-->
				</table>
				<!--��ҳ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xsxxJbszForm"></jsp:include>
				<!--��ҳend-->
			</div>
			<!-- ��ѯ��� end-->
			
			<div id="xsqszDiv" style="display: none">
				<div class="tab">
					<table class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�������ݣ�ע����Ҫִ�б�������������ã�</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">�Ƿ�չ��</th>
								<td>
									<input type="radio" name="sz_sfzk" value="������" onclick="setPlszHiddenValue('sfzk',this.value)" checked="checked"/>������
									<input type="radio" name="sz_sfzk" value="��" onclick="setPlszHiddenValue('sfzk',this.value)"/>��
									<input type="radio" name="sz_sfzk" value="��" onclick="setPlszHiddenValue('sfzk',this.value)"/>��
									<input type="hidden" id="sz_sfzk_value" value="������"/>
								</td>
							</tr>
							<tr>
								<th>�Ƿ�����</th>
								<td>
									<input type="radio" name="sz_sfqy" value="������" onclick="setPlszHiddenValue('sfqy',this.value)" checked="checked"/>������
									<input type="radio" name="sz_sfqy" value="��" onclick="setPlszHiddenValue('sfqy',this.value)"/>��
									<input type="radio" name="sz_sfqy" value="��" onclick="setPlszHiddenValue('sfqy',this.value)"/>��
									<input type="hidden" id="sz_sfqy_value" value="������"/>
								</td>
							</tr>
							<tr>
								<th>�Ƿ��ö�</th>
								<td>
									<input type="radio" name="sz_sfzd" value="������" onclick="setPlszHiddenValue('sfzd',this.value)" checked="checked"/>������
									<input type="radio" name="sz_sfzd" value="��" onclick="setPlszHiddenValue('sfzd',this.value)"/>��
									<input type="radio" name="sz_sfzd" value="��" onclick="setPlszHiddenValue('sfzd',this.value)"/>��
									<input type="hidden" id="sz_sfzd_value" value="������"/>
								</td>
							</tr>								
						</tbody>
						<tfoot>
						<tr>
							<td colspan='2'>
								<div class="btn">

									<button type="button" onclick="setPlsz()" id="buttonSave">
										ȷ ��
									</button>
									
									<button type="button" onclick="hiddenMessage(true,true);return false;" id="buttonClose">
										�� ��
									</button>
								</div>
							</td>
						</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>