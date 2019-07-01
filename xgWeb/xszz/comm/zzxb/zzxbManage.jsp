<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���� �����-->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/xszz/xszzComm.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getXszzInfo.js'></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript">
			function loadXmInfo(obj){
				//��Ŀ����
				var xmdm = obj.value;
				
				if ("" != xmdm){
					getXszzInfo.getXszzInfoByXmdm(xmdm,function(data){
						
						//��������
						var sqzq = data.sqzq;
						//��˼���
						var shjb = data.shjb;
					
						$('sqzq').value = sqzq;
					
						if (sqzq == "ѧ��"){
							$('xn').disabled=false;
							$('nd').disabled=true;
							$('xq').disabled=true;
						} else if (sqzq == "ѧ��"){
							$('xn').disabled=false;
							$('nd').disabled=true;
							$('xq').disabled=false;
						} else if (sqzq == "���"){
							$('xn').disabled=true;
							$('nd').disabled=false;
							$('xq').disabled=true;
						} else{
							$('xn').disabled=true;
							$('nd').disabled=true;
							$('xq').disabled=true;
						}
						
						
						//���ͨ���ļ�¼�����죬
						//����Ҫ������˼���Ѷ�Ӧ������ֶ���Ϊͨ����Ϊ��ѯ����
						if (shjb == "һ�����"){
							$('shzt1').value = "ͨ��";
							$('shzt2').value = "";
							$('shzt3').value = "";
						} else if (shjb == "�������"){
							$('shzt1').value = "";
							$('shzt2').value = "ͨ��";
							$('shzt3').value = "";
						} else if (shjb == "�������"){
							$('shzt1').value = "";
							$('shzt2').value = "";
							$('shzt3').value = "ͨ��";
						}
					});	
				}
			}
			
			
			function plsb(){
			
				var url = '/xgxt/commXszz.do?method=zzxbManage&doType=zzxb';
				var sqzq = $('sqzq').value;
			
				if ("ѧ��"==sqzq){
					saveUpdate(url,"select_xn");
				}
				
				if ("ѧ��"==sqzq){
					saveUpdate(url,"select_xn-select_xq");
				}
				
				if ("���"==sqzq){
					saveUpdate(url,"select_nd");
				}
				
			}
			
			function updateZzxb(doType){
				var url = '/xgxt/commXszz.do?method=zzxbUpdate';
				
				if (curr_row == null){
					alert("��ѡ����Ҫ�޸ĵ�����!");
					return false;
				} else {
					var isxb = curr_row.cells[curr_row.cells.length-1].innerText.trim();
					var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
					
					if ("�´�"==isxb && "update"==doType){
						alert("���������ݲ����޸�");
						return false;
					} else {
						url += "&pk="+pkValue;
						url += "&doType="+doType;
					}
					
				}
				showTopWin(url,800,600);
			}
			
			
			function delData(){
				var flg =true;
				if (isChecked("primarykey_checkVal")){
					for (var i = 0 ; i < array.length ; i++){
						var obj = array[i].parentNode.parentNode;
						var text = obj.cells[obj.cells.length-1].innerText.trim();
						if ("�´�"==text){
							alert("���������ݲ���ɾ������ȷ��!");
							flg = false;
							break;
						}
					}
				}else {
					flg = false;
				}
				
				if (flg){
					sumitInfo('/xgxt/commXszz.do?method=zzxbManage','del');
				}
			}
			
			
			function plsbCheck(){
				
			
				var flg = true;
				var sqzq = $('sqzq').value;
				
				if (sqzq=="��һ��" || sqzq=="������"){
					alert("��ǰ������Ŀ����������Ϊ:"+sqzq+",����������!���������ô���Ŀ����������Ϊѧ�ꡢ��Ȼ�ѧ��!");
					return false;
				}
				
				sqzq = sqzq=="ѧ��" ? "ѧ�ꡢѧ��" : sqzq;
			
				if (isChecked('primarykey_checkVal')){
				
					var text = array[0].parentNode.getElementsByTagName("input")[1].value;
					
					for (var i = 0 ; i<array.length ;i++){
						var tempText = array[i].parentNode.getElementsByTagName("input")[1].value;
						
						if (text != tempText){
							flg = false;
							array = new Array();
							alert("����ͬһ���������ڵ����ݲ��ܽ�����������,������ѡ��"+sqzq+"����ѯ���ٽ�����������!");
							break;
						}
					}
				
					if (flg){
						viewTempDiv('��������','zzxbDiv',500,280);
					}
					
				}
			}
			
		</script>
	</head>

	<body onload="loadXmInfo($('xmdm'))">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/commXszz">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ֻ�����ͨ�������ݲ��ܽ������� -->
			<input type="hidden" name="shzt1" id="shzt1" />
			<input type="hidden" name="shzt2" id="shzt2" />
			<input type="hidden" name="shzt3" id="shzt3" />
			<input type="hidden" name="shzt3" id="shzt3" />
			<input type="hidden" name="sqzq" id="sqzq" />
			<!-- ������ end-->
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" onclick="plsbCheck();" class="btn_sr">��������</a>
							</li>
							<li>
								<a href="#"
									onclick="updateZzxb('update');"
									class="btn_xg">�޸�</a>
							</li>
							<li>
								<a href="#"
									onclick="delData()"
									class="btn_sc">ɾ��</a>
							</li>
							<li>
								<a href="#" onclick="impInfo()" class="btn_dr">����</a>
							</li>
							<li>
								<a href="#" onclick="expInfo()" class="btn_dc">����</a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<!-- ��ť end-->
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										<button type="button"  class="btn_cx" id="search_go"
											onclick="if($('xmdm').value==''){alert('��ѡ����Ŀ')}else{allNotEmpThenGo('/xgxt/commXszz.do?method=zzxbManage&doType=query')}">
											�� ѯ
										</button>
										<button type="button"  class="btn_cz" id="btn_cz" onclick="">
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
									ѧ��
								</th>
								<td>
									<html:select property="xn" style="width: 150px" styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									���
								</th>
								<td>
									<html:select property="nd" style="" styleId="nd"
										style="width: 150px">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<html:select property="xq" style="" styleId="xq"
										style="width: 150px">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
							</tr>
							<!-- �ڶ��� -->
							<tr>
								<th>
									�꼶
								</th>
								<td>
									<html:select property="nj" style="width: 150px" styleId="nj"
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
									<!-- ѧ���û� -->
									<logic:equal name="userType" value="stu" scope="session">
										<html:text property="xh" styleId="xh" style="" value="${userName }" readonly="true" />
									</logic:equal>
									<!-- ѧ���û� -->
									<logic:notEqual name="userType" value="stu" scope="session">
										<html:text property="xh" styleId="xh" style="" maxlength="20" />
									</logic:notEqual>
								</td>
								<th>
									����
								</th>
								<td>
									<html:text property="xm" styleId="xm" style="" maxlength="20" />
								</td>
							</tr>
							<!-- ������ -->
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<!-- ��ѧԺ�û� -->
									<logic:equal name="userType" value="xy" scope="session">
										<html:hidden property="xydm" value="${userDep }" />
										<html:select property="xydm" style="width: 150px" styleId="xy"
											disabled="true">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:equal>
									<!-- ��ѧԺ�û� end -->

									<!-- ��ѧԺ�û� -->
									<logic:notEqual name="userType" value="xy" scope="session">
										<html:select property="xydm" style="width: 150px" styleId="xy">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
									<!-- ��ѧԺ�û� end -->
								</td>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="zydm" style="width: 150px" styleId="zy"
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
									<html:select property="bjdm" style="width: 150px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<!-- ������ -->
							<tr>
								<th>
									��Ŀ
								</th>
								<td>
									<!-- onchange�¼�����������Ŀ���������� -->
									<html:select property="xmdm" style="width: 150px"
										styleId="xmdm" onchange="loadXmInfo(this)">
										<%--										<html:option value=""></html:option>--%>
										<%--										<html:options collection="xmList" property="xmdm" labelProperty="xmmc" />--%>
										<!-- 
												���ھ��㽭�Ƽ�Ҫ����ѧ�����д˹��ܣ������������ٿ��ţ�
												������ѧ�����и�������������֮��
											 -->
										<html:option value="5003">������ѧ����</html:option>
									</html:select>
								</td>
								<th>
									���뿪ʼ����
								</th>
								<td>
									<html:text property="kssj" styleId="kssj"
										onblur="dateFormatChg(this)" style="cursor:hand;"
										onclick="return showCalendar('kssj','y-mm-dd');" />
								</td>
								<th>
									�����������
								</th>
								<td>
									<html:text property="jssj" styleId="jssj"
										onblur="dateFormatChg(this)" style="cursor:hand;"
										onclick="return showCalendar('jssj','y-mm-dd');" />
								</td>
							</tr>
							<!-- ��˵���ǵ����� -->
							<tr>
								<th>
									����״̬
								</th>
								<td>
									<html:select property="isxb" style="width:150px" styleId="isxb">
										<html:option value=""></html:option>
										<html:option value="�´�">�´�</html:option>
										<html:option value="����">����</html:option>
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- �������� end-->
				<!-- ��ѯ���-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; <logic:notEmpty name="rsList">
								<font color="blue">��ʾ��������ͷ��������˫����¼�ɲ鿴��ϸ��Ϣ.</font>
							</logic:notEmpty> <logic:empty name="rsList">
								<font color="red">δ�ҵ��κμ�¼��</font>
							</logic:empty> </span>
					</h3>
					<logic:notEmpty name="rsList">
						<div class="con_overlfow">
							<table summary="" class="dateline tablenowrap" align="" width="100%">
								<!-- ��ͷ -->
								<thead>
									<tr align="center" style="cursor:hand">
										<td>
<%--											<input type="checkbox" id="selall" name="selall"--%>
<%--												onclick="selAll()" />--%>
										</td>
										<logic:iterate id="tit" name="topTr" offset="0">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<!-- ��ͷ end-->
								<!--���� -->
								<logic:iterate name="rsList" id="rs" indexId="index">
									<tr onclick="rowOnClick(this);" style="cursor:hand"
										ondblclick="updateZzxb('view');">
										<td align="center">
											<input type="checkbox" id="checkVal"
												name="primarykey_checkVal" value="${rs.pk }" />
											<input type="hidden" name="zq" value="${rs.sqzq }"/>
										</td>
										<td align="center">
											${rs.xh }
										</td>
										<td align="center">
											${rs.xm }
										</td>
										<td align="center">
											${rs.xb }
										</td>
										<td align="center">
											${rs.nj }
										</td>
										<td align="center">
											${rs.xymc }
										</td>
										<td align="center">
											${rs.zymc }
										</td>
										<td align="center">
											${rs.bjmc }
										</td>
										<td align="center">
											${rs.sqsj }
										</td>
										<td align="center">
											${rs.isxb }
										</td>
									</tr>
								</logic:iterate>
								<!--���� end -->
							</table>
						</div>
						<!--��ҳ-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=xszzTyForm"></jsp:include>
						<!--��ҳend-->
					</logic:notEmpty>
				</div>
				<!-- ��ѯ��� end-->




				<!-- �������쵯���� -->
				<div class="open_win01" style="display:none;" id="zzxbDiv">
					<table width='300' class='formlist'>
						<thead>
							<tr>
								<td colspan="2">
									<span>��ѡ����Ҫ���쵽��${xmInfo.sqzq }</span>
								</td>
							</tr>
						</thead>
						<tbody>
							<logic:present name="xmInfo">
								<logic:equal value="ѧ��" name="xmInfo" property="sqzq">
									<tr>
										<th width="20%">
											<font color="red">*</font>ѧ��
										</th>
										<td>
											<html:select property="save_xn" styleId="select_xn" style="width:150px">
												<html:options collection="xnList" property="xn"
													labelProperty="xn" />
											</html:select>
										</td>
									</tr>
								</logic:equal>
								<logic:equal value="ѧ��" name="xmInfo" property="sqzq">
									<tr>
										<th  width="20%">
											<font color="red">*</font>ѧ��
										</th>
										<td>
											<html:select property="save_xn" styleId="select_xn"  style="width:150px">
												<html:options collection="xnList" property="xn"
													labelProperty="xn" />
											</html:select>
										</td>
									</tr>
									<tr>
										<th  width="20%">
											<font color="red">*</font>ѧ��
										</th>
										<td>
											<html:select property="save_xq" styleId="select_xq"  style="width:150px">
												<html:options collection="xqList" property="xqdm"
													labelProperty="xqmc" />
											</html:select>
										</td>
									</tr>
								</logic:equal>
								<logic:equal value="���" name="xmInfo" property="sqzq">
									<tr>
										<th  width="20%">
											<font color="red">*</font>���
										</th>
										<td>
											<html:select property="save_nd" styleId="select_nd"  style="width:150px">
												<html:options collection="ndList" property="nd"
													labelProperty="nd" />
											</html:select>
										</td>
									</tr>
								</logic:equal>
							</logic:present>
							<tr>
								<th  width="20%">��ע<br/><font color="red"><��500��></font></th>
								<td>
									<html:textarea property="save_bz" style="width:85%" rows="5" onblur="checkLen(this,500)"></html:textarea>
								</td>
							</tr>
							
							
						<tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"
										<span class="red">*</span>"Ϊ������
									</div>
									<div class='btn'>
										<button type="button"  onclick='plsb()'>
											����
										</button>
										<button type="button"  onclick="hiddenMessage(true,true);return false;">
											�ر�
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
		</html:form>
		<!-- ��ʾ��Ϣ -->
		<%@ include file="../other/tsxx.jsp"%>
		<!-- ��ʾ��Ϣ end-->

		<!-- �������ƵĲ� -->
		<%@ include file="/comm/exp/commExp.jsp"%>
		<!-- �������ƵĲ� end-->
	</body>
</html>
