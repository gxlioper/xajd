<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/gygl/gyglFunction.js"></script>
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ��Ϣά�� - ��Դ���Զ�����</a>
			</p>
		</div>
		<!-- ���� end-->	
		<html:form action="/zgdzdx_Gygl" method="post">
		<input type="hidden" id="delPk" name="delPk"/>
		<input type="hidden" id="searchGo" name="searchGo" onclick="refreshForm('/xgxt/roomAutoCreate.do');"/>
				<div id="items" name="items" style="display:none; position: absolute;background-color: #D0E0EE; width: 300px" >
					<table class="formlist" style=" width: 300px">						
					<thead>
						<tr>
							<th colspan="2">
								<span>Ĭ�����Һű��Ź���Ϊ�����+����˳���</span>
							</th>
						</tr>
					</thead>
					<tbody>	
						<tr>
							<th align="right">
								��ţ�
							</th>
							<td align="left">
								<html:select property="chsfbl"  styleId="chsfbl">							
								<html:option value="0">��</html:option>
								<html:option value="1">��</html:option>	
								</html:select>
								 (С��10��ʱ����Ƿ���)
							</td>
						</tr>
						<tr>
							<th align="right">
								����˳��ţ�
							</th>
							<td align="left">
<%--								<html:text property="fjhws" styleId="fjhws" style="width:50px" maxlength="1" onkeypress="chkonlynum()"  onblur="onlyNumInput(this)"/>--%>
								<html:select property="fjhws"  styleId="fjhws">								
								<html:option value="2">2</html:option>
								<html:option value="3">3</html:option>
								<html:option value="4">4</html:option>
								<html:option value="5">5</html:option>	
								<html:option value="6">6</html:option>	
								<html:option value="7">7</html:option>	
								<html:option value="8">8</html:option>	
								<html:option value="9">9</html:option>
								<html:option value="10">10</html:option>			
								</html:select>
							 	(λ��)
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan=2 align=center>
							<div class="btn">
								<button  style="height: 19px" onclick="roomCodeSave()">
									ȷ ��
								</button>
								&nbsp;&nbsp;
								<button  style="height: 19px" onclick="document.getElementById('items').style.display='none'">
									�� ��
								</button>
								</div>
							</td>
						</tr>
						</tfoot>
					</table>
				</div>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#"
								onclick="modifyFYKNote()"
								class="btn_xg">�޸�</a>
						</li>
						<li>
							<a href="#"
								onclick="batch()"
								class="btn_sc">ɾ��</a>
						</li>
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
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="roomATCreat(this)">
											����
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- ��һ�� -->
							<tr>
								<th>
									У��
								</th>
								<td>
									<html:select property="xiaoqu" styleId="xiaoqu" onchange="clearData();refreshForm('/xgxt/roomAutoCreate.do');">
									<html:option value="">--��ѡ��--</html:option>
									<html:options collection="xiaoQList" labelProperty="mc"
										property="dm" />
								</html:select>
								</td>
								<th>
									¥������
								</th>
								<td>
									<html:select property="lddm" styleId="lddm"  onchange="clearData();refreshForm('/xgxt/roomAutoCreate.do');">
									<html:option value="">--��ѡ��--</html:option>
									<html:options collection="ldList" labelProperty="mc"
										property="dm" />
								</html:select>
								</td>
								<th>
									¥��
								</th>
								<td>
									<html:select property="cs" styleId="cs" onchange="clearData();refreshForm('/xgxt/roomAutoCreate.do');">
									<html:option value="">--��ѡ��--</html:option>
									<html:options collection="lcList" labelProperty="mc"
										property="dm" />
								</html:select>
								</td>
							</tr>
							<!-- �ڶ��� -->
							<tr>
								<th>
									�㷿����
								</th>
								<td>
									<html:text property="cfjs" styleId="cfjs" 
										style="width:50px"
										onkeydown="return onlyNum(this,2)"
										onmousedown="return onlyNum(this,2)"
										maxlength="2" 
										style="width : 20%;ime-mode:disabled"/>
								</td>
								<th>
									���䴲λ��(��������)
								</th>
								<td>
									<html:text property="jcws" styleId="jcws" 
										maxlength="2" onkeypress="chkonlynum()"  
										onblur="onlyNumInput(this)" style="width:50px" />
								</td>
								<th>
									���������
								</th>
								<td>
									<html:select property="fpbz" styleId="fpbz">
									    <html:option value="">--��ѡ��--</html:option>
										<html:options collection="fpbjList" property="en" labelProperty="cn"/>
									</html:select>
								</td>
							</tr>
							<!-- ������ -->
							<tr>
								<th>
									�����׼�շ�
								</th>
								<td>
									<html:text property="sfbz" styleId="sfbz" 
										onkeypress="return sztzNumInputValue(this,7,event)" style="width:50px" />
								</td>
								<th>
									���Һű������ã�	(˫���ұ��ı���)	
								</th>
								<td colspan="3">
									<html:text property="qshbpgz" styleId="qshbpgz"
										readonly="true" style="cursor:hand;width:300px " 
										ondblclick="showGzSet()"/>							 
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- �������� end-->
				<!-- ��ѯ���-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; 
							<logic:empty name="rs">
								<script type="text/javascript">
								    var ldtext = document.forms[0].lddm.options[document.forms[0].lddm.selectedIndex].text;
									var cstext = document.forms[0].cs.options[document.forms[0].cs.selectedIndex].text;
									if($("cs").value!=""){
									     document.write(ldtext+"&nbsp;&nbsp;");							
									     if($("cs").value!="all"){
											document.write(cstext);						  
									     }
									}
									
								</script>
								<font color="red">���޷������ݣ�</font>
							</logic:empty>
							<logic:notEmpty name="rs">
							�ܷ�������
							<bean:write name="rsNum" />
							&nbsp;�� &nbsp;&nbsp;&nbsp;
							</logic:notEmpty>
						</span>
					</h3>
					<logic:notEmpty name="rs">
					<table summary="" class="dateline" align="" width="100%">
						<!-- ��ͷ -->
						<thead>
							<tr align="center" style="cursor:hand">
								<td title="ȫѡɾ��" align="left">
									<input type="checkbox" name="fyxx" value="all" onclick="chec()"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<!-- ��ͷ end-->
						<!--���� -->
							<logic:iterate name="rs" id="s">
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
									ondblclick="">
									<td title="��ѡɾ��" align="left">
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="checkbox" name="pkV"
												value="<bean:write name="v"/>"/>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<td>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						<!--���� end-->
					</logic:notEmpty>
				</div>
				<!-- ��ѯ��� end-->
			</div>
			<logic:equal value="true" name="flag">
			 <script language="javascript">
			   alert("ɾ���ɹ���");
			   </script>
			</logic:equal>
			<logic:equal value="false" name="flag">
			 <script language="javascript">
			   alert("ɾ��ʧ�ܣ�");
			   </script>
			</logic:equal>
		</html:form>
	</body>
</html>

