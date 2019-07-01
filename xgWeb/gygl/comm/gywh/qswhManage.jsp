<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
		
		function addQssj(){
			var url="/xgxt/gyglGywh.do?method=qsxxwh&doType=add";
			showTopWin(url,800,600);
		}
	
		
		//�޸�
		function modiQssj(){
			var pkValue=document.getElementsByName("checkVal");
			var n=0;
			for(i=0;i<pkValue.length;i++){
				if(pkValue[i].checked){
					n++;
				}
			}
			
			if(n>1){
				viewTempDiv("������Ϣ����","plUpdate",410,245);
			}else if(n==1 || curr_row){
				var pkValue = curr_row.getElementsByTagName('input')[0].value;
				var url = '/xgxt/gyglGywh.do?method=qsxxwh&doType=update&pkValue='+pkValue;
				showTopWin(url,800,600);
				
			}else if (null == curr_row) {
					alert('��ѡ��һ��');
					return false;
			}
		}
		
		function updateQsxx(){
			
			var url = '/xgxt/gyglGywh.do?method=qswhManage&doType=plsz';
			var pkValue=document.getElementsByName("checkVal");
			var rzqkArr=document.getElementsByName("rzqkArr");
			var xbxdArr=document.getElementsByName("xbxdArr");
			
			var xbxdHidArr=new Array();
			var rzqkHidArr=new Array();
			var n=0;
			for(i=0;i<pkValue.length;i++){
				if(pkValue[i].checked){
					rzqkHidArr[n]=rzqkArr[i];
					xbxdHidArr[n]=xbxdArr[i];
					n++;
				}
			}
			
			for(i=0;i<rzqkHidArr.length;i++){
			
				var rzqk=document.createElement("input");
				rzqk.type="hidden";
				rzqk.name="rzqkHidArr";
				rzqk.value=rzqkHidArr[i].value;
				
				var xbxd=document.createElement("input");
				xbxd.type="hidden";
				xbxd.name="xbxdHidArr";
				xbxd.value=xbxdHidArr[i].value;
				
				document.forms[0].appendChild(rzqk);
				document.forms[0].appendChild(xbxd);
			}
			
			if($("xbHid").value!="���޸�" || $("kfhzHid").value!="���޸�" || $("sfxgHid").value!="���޸�" || $("fpbjHid").value!="���޸�"){
				refreshForm(url);
			}else{
				hiddenMessage(true,true);
			}
		}
		
		function showQsxx(pkValue){
			var url = '/xgxt/gyglGywh.do?method=qsxxwh&doType=view&pkValue='+pkValue;
			showTopWin(url,800,600);
		}
		
		function delQssj(){
			var pkV=document.getElementsByName("checkVal");
			blog=false;
			for(i=0;i<pkV.length;i++){
				if(pkV[i].checked && !pkV[i].disabled){
					blog=true;
				}
			}
			if(blog){
				if(confirm("ȷ��Ҫɾ��ѡ�еļ�¼��?")){
					var mklx=$("mklx").value;
					var url="/xgxt/gyglGywh.do?method=qswhManage";
					url+="&doType=del&mklx="+mklx;
					refreshForm(url);
					hiddenMessage(true,true);
					BatAlert.showTips('���ڲ��������Ե�...');
				}else {
					return false;
				}
			}else{
				alert("û����Ҫ����ļ�¼!");
				return false;
			}
		
			
		}
		
		function searchRs(){
			allNotEmpThenGo('/xgxt/gyglGywh.do?method=qswhManage');
		}
		
		//��һ������
		function nextCz(){
			var next = $("next_cz").value;
		
			if(next == "gb"){//�ر�
				hiddenMessage(true,true);
			}else {
				refreshForm(next);
			}
		}
		
		//��ʾ����ҳ��
		function showLoadPage(){
			$("div_help").style.display='none';
			//�๦�ܲ���
			$("dgncz").style.display="none";
			//��ѯ���
			$("cxjg").style.display="none";
			//��ʾ
			$("page_loading").style.display="";
			//������ʾ��Ϣ
			$("prompt").innerHTML="���ڽ��д�λ���ɲ���,���Ժ�!";
		}
		
		function createCw(){
			if(confirm("��λ�Զ����ɹ��������ڵ���������Ϣ���ڴ�λ��Ϣ��\n��Ϊ�����Զ����ɴ�λ��ʹ�ñ�ϵͳ������������������\n����б��������Ƿ���д�λ�Զ�����?")){
				showLoadPage();
				refreshForm('/xgxt/gygl_gywh_qswh.do?doType=cwsc');
			}
		}
		
		function changeDisabled(blog){
			$("sfbz_id").disabled=blog;
		}
		
		function changeValue(obj,bool){
			$('sfxgHid').value=obj.value;
			changeDisabled(bool);
		}
		</script>
	</head>
	<body>

		<html:form action="/gyglGywh" method="post">
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
				</p>
			</div>			
			<!-- ���� end-->
			<!-- ��ʾ��Ϣ START-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					������ģ�飬������ɶ����ҵ����ӡ��޸ĺͲ鿴���ܡ����"����"��ť��������һ���µ����ҡ�ѡ��һ�����Ҽ�¼���<br/>	
					���޸�"��ť"���Զ����е�������Ϣ�����޸ġ���ѡ������¼�󣬵���޸İ�ť�ɽ��������޸Ĳ����������޸Ĳ���ֻ��<br/>
					��δס�����ҽ����޸ģ��ڽ��������Ա��޸�ʱֻ���޸�¥���Ա�����Ϊ����ϡ������ҡ���ѡһ���������Һ󣬵��<br/>
					"ɾ��"��ť����ɾ��һ����������Ҽ�¼�������Ҫ���������������ң����Ե��"�����Զ�����"��ť�������ҡ����"��<br/>	
					��"���Բ鿴������ϸ��Ϣ������λ���ɡ����������ڵ���������Ϣ�󣬶����ҽ����Զ���λ�����ɡ�
				</p>
				<a class="close" title="����"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- ��ʾ��Ϣ end-->	
			<!-- ģ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="path" value="gygl_gywh_qswh.do"/>
			<input type="hidden" id="mklx" name="mklx" value="${mklx}"/>
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
<%--						<li><a href="#" onclick="delJcsj();return false;" class="btn_ccg"> ���� </a></li>--%>
<%--						<li><a href="#" onclick="delJcsj();return false;" class="btn_sz"> ���� </a></li>--%>
						<li><a href="#" onclick="addQssj();return false;" class="btn_zj"> ���� </a></li>
						<li><a href="#" onclick="modiQssj();return false;" class="btn_xg"> �޸� </a></li>
						<li><a href="#" onclick="delQssj();return false;" class="btn_sc"> ɾ�� </a></li>
						<li><a href="#" class="btn_dr" onclick="impAndChkData();return false;">��������</a></li>		
						<li><a href="#" class="btn_dc" onclick="setSearchTj();configureExportData();return false;">��������</a></li>
						<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">�����ֶ�ȷ��</a></li>
						<li><a href="#" class="btn_csh" onclick="refreshForm('/xgxt/gygl_gywh_zdsc.do?tzPath=qswh');return false;">�����Զ�����</a></li>
						<li><a href="#" class="btn_cs" onclick="createCw();return false;">��λ����</a></li>
					</ul>
				</div>
				
				
				
<%--			<!-- new �汾 -->--%>
			     <logic:equal name="superSearch" value="yes">
			     	<%@ include file="/comm/search/superSearchArea.jsp"%>
			     </logic:equal>

			</div>
			<div class="formbox" id="cxjg">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty>  
						<logic:notEmpty name="rs">
						<font color="blue"></font>	
						</logic:notEmpty>
					</span>
				</h3>

				<logic:notEmpty name="rs">
				<div class="con_overlfow" >
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()" />

								</td>
								<logic:iterate id="tit" name="topTr"   indexId="index" offset="1" length="11">
									<td id="<bean:write name="tit" property="en"/>"
										 nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								<td>�鿴������Ϣ</td>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
									<td>
										<input type="checkbox" name="checkVal" id="pkV"
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
										/>
										<input type="hidden" name="rzqkArr" value="<logic:iterate id="v" name="s" offset="12" length="1">${v}</logic:iterate>"/>
										<input type="hidden" name="xbxdArr" value="<logic:iterate id="v" name="s" offset="13" length="1">${v}</logic:iterate>"/>
									</td>
									<logic:iterate id="v" name="s" offset="2" length="11">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
									<td>
										<a href="#" onclick="showQsxx('<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>')"><font color="blue">�鿴</font></a>
									</td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
					</div>
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglGywhForm"></jsp:include>
				</logic:notEmpty>
			</div>
			<div id="plUpdate" style="display: none">
					<div class="tab">
						<table class="formlist">
							<thead>
								<tr>
									<th colspan="2">
										<span>
										������Ϣ����(ֻ�е�������ס���Ϊ��ʱ�ſ��޸�)
										</span>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th>
										�Ա�
									</th>
									<td>
										<input type="hidden" name="xbHid" id="xbHid" value="���޸�"/>
										<html:radio property="xb"  value="���޸�"  onclick="$('xbHid').value=this.value"/>���޸�
										<html:radio property="xb"  value="��"  onclick="$('xbHid').value=this.value"/>��
										<html:radio property="xb"  value="Ů"  onclick="$('xbHid').value=this.value"/>Ů
									</td>
								</tr>
								<tr>
									<th>
										�ɷ��ס
									</th>
									<td>
										<input type="hidden" name="kfhzHid" id="kfhzHid" value="���޸�"/>
										<html:radio property="kfhz"  value="���޸�"  onclick="$('kfhzHid').value=this.value"/>���޸�
										<html:radio property="kfhz"  value="����"   onclick="$('kfhzHid').value=this.value"/>����
										<html:radio property="kfhz"  value="����"   onclick="$('kfhzHid').value=this.value"/>����
									</td>
								</tr>
								<tr>
									<th>
										�շѱ�׼
									</th>
									<td>
										<input type="hidden" name="sfxgHid" id="sfxgHid" value="���޸�"/>
										<input type="radio" name="sfxg"  value="���޸�" checked onclick="changeValue(this,'true')"/>���޸�
										<input type="radio" name="sfxg" value="�޸�" onclick="changeValue(this,'');"/>�޸�
										<html:text property="sfbz" styleId="sfbz_id"  disabled="true"/>
									</td>
								</tr>
								<tr>
									<th>
										������
									</th>
									<td>
										
										<input type="hidden" name="fpbjHid" id="fpbjHid" value="���޸�"/>
										<html:radio property="fpbj" value="���޸�" onclick="$('fpbjHid').value=this.value"/>���޸�
										<html:radio property="fpbj" value="һ��"  onclick="$('fpbjHid').value=this.value"/>һ��
										<html:radio property="fpbj" value="����"  onclick="$('fpbjHid').value=this.value"/>����
									</td>
								</tr>
							</tbody>
							<tfoot>
							<tr>
								<td colspan="2">
									<div class="btn">
										<!-- ���� -->
										<button onclick="updateQsxx()">
											�� ��
										</button>
										<!-- �ر� -->
										<button onclick="hiddenMessage(true,true);">
											�� ��
										</button>
									</div>
								</td>
							</tr>
							</tfoot>
						</table>
					</div>
				</div>
		</html:form>
		<%@ include file="/comm/delMessage.jsp"%>
		<%@ include file="/comm/loading.jsp"%>
		<logic:notPresent name="delMessage">
				<logic:present name="message">
				<script>
					alert($("message").value);	
					$("message").value = "";
					$("doType").value = "";	
				</script>
				</logic:present>
		</logic:notPresent>	
	</body>
</html>
