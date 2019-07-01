<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript" src="js/messageFunctaion.js"></script>
		<script type="text/javascript">
			//��ѯ�����
			function searchRs(){
			
				allNotEmpThenGo('szdw_teaInfo.do?method=teaManage');
			}
		
		
			function showAddPage(){
				tipsWindown("ϵͳ��ʾ","id:addJbxx","380","170","true","","true","id");
			}
			
			function checkZgh(){
				var isExists = true;
				var zgh = jQuery('#zgh').val();
				if (zgh == ''){
					alertInfo('ְ���Ų���Ϊ�գ�');
					return false;
				}
				//��֤�Ƿ����
				jQuery.post('szdw_teaInfo.do?method=checkZgh',{zgh:zgh},function(data){
					if (data == 'true'){
						//jQuery('p',jQuery('#yzMessage')).text('ְ����"'+zgh+'"�Ѵ��ڣ�����ʹ�ã�');
						//showMessage('yzMessage','msg_error');
						alertInfo('ְ����"'+zgh+'"�Ѵ��ڣ�����ʹ�ã�');
						isExists = false;
						return false;
						
					} 
				});
				return isExists;
			}
			
			
			function addTea(){
				
				var zgh = jQuery('#zgh').val();
				
				jQuery.ajaxSetup({async:false});
				if (checkZgh()){
					var xm = jQuery('#xm').val();
					if (xm == ''){
						alertInfo('��������Ϊ�գ�');
						//showMessage('xmMessage','msg_error');
						return false;
					}
					jQuery.post('szdw_teaInfo.do?method=addTea',{zgh:zgh,xm:encodeURI(xm)},function(data){
						if (data != ''){
							confirmInfo('����ɹ����Ƿ����ƽ�ʦ��ϸ��Ϣ?',function(t){
								if (t == 'ok'){
									showTopWin('szdw_teaInfo.do?method=teaInfo&zgh='+zgh,700,550);
								}
								closeWindown();//�رյ�����
								searchRs();
							})
						} else {
							alertInfo('����ʧ�ܣ�');
						}
					});
				}
				jQuery.ajaxSetup({async:true});
			}
			
			
			
			function tgZyhxxk(){
				var selected = jQuery('input[name=primarykey_cbv]:checked');
				
				if(selected.length == 0){
					alertInfo("�빴ѡ������ת���û���Ľ�ʦ");
					return false;
				} else {
					var array = jQuery("[name=primarykey_cbv]:checked");
						for (var i=0;i<array.length;i++) {
							var ssbm = jQuery(array[i]).parent().parent().find("td").eq(4).html();
							if(ssbm==""){
								alertInfo("�������Ų���Ϊ��!");
								return false;
							}
						}
					tipsWindown("ϵͳ��ʾ","id:viewYhz","450","190","true","","true","id");
				}
			}
			
			
			function tbTeaInfo() {
				var zdm = jQuery('#zdm').val();
				var dwdm = jQuery('#dwdm').val();
				
				if(zdm == ''){
					alertInfo("��ѡ�������û���");
					return false;
				}
				if(dwdm == ''){
					alertInfo("��ѡ���û����ڵ�λ");
					return false;
				}
				
				confirmInfo('��ȷ��Ҫͬ����ѡ�Ľ�ʦ���û�����?',function(t){
					if (t == 'ok'){
						refreshForm('szdw_teaInfo.do?method=tbTeaInfo&zdm='+zdm+'&dwdm='+dwdm);
					}
				})				
			}
		</script>
	</head>

	<body>
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
			</p>
		</div>
		<!-- ���� end-->
		
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
				1.��������ò�ѯ��ʾ�в������������Ե��<font color="blue">��ѡ</font>����������ʾ�С�<br/>
				2.��ѡ��ϣ����ӵ��û���ļ�¼��ִ��<font color="blue">������û���Ϣ��</font>�����Խ��ý�ʦ��ӵ��û��⣬��ʼ����888888��<br/>
				3.�������ɾ��ĳ��ʦ��¼�������޷��ɹ����������ڸý�ʦ�����Ű༶δ���ͷš�<br/>
				4.˫����¼�����Բ鿴�ý�ʦ����ϸ��Ϣ��
				</span>
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/szdw_teaInfo" method="post">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
					<div class="buttonbox">
						<ul>
							<logic:equal name="writeAble" value="yes">
								<li>
									<a href="#" class="btn_zj"
										onclick="showAddPage();return false;"
										id="btn_qx">����</a>
								</li>
								
								<li>
									<a href="#" class="btn_xg"
										onclick="showUpdateWindow('primarykey_cbv','szdw_teaInfo.do?method=teaInfo','720','550');return false;"
										id="btn_qx">�޸�</a>
								</li>
								
								<li>
									<a href="#" class="btn_sc"
										onclick="batchData('primarykey_cbv','szdw_teaInfo.do?method=delTeaInfo','del');return false;"
										id="btn_qx">ɾ��</a>
								</li>
								
								<li>
									<a href="#" onclick="impAndChkData();return false;" class="btn_dr"> ���� </a>
								</li>
								<li>
									<a href="#" onclick="tgZyhxxk();return false;" class="btn_sx"> ������û���Ϣ�� </a>
								</li>
							</logic:equal>
							<li>
								<a href="#" class="btn_dc"
									onclick='expData("szdw_teaInfo.do?method=expTeaList");return false;'
									id="btn_qx">����</a>
							</li>
							<%--<li>
								<a href="#" class="btn_sz"
									onclick='tipsWindown("ϵͳ��ʾ","id:szcol","620","360","true","","true","id");return false;'
									id="btn_qx">��ѡ</a>
							</li>
							
						--%></ul>
					</div>
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<%--<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('szdw_teaInfo.do?method=teaManage')">
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
						</tbody>
					</table>
				</div>
			--%></div> 

			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; 
					<logic:notEmpty name="rs">
							<font color="blue"></font>
					</logic:notEmpty> </span>
				</h3>


				<div class="con_overlfow">
					<table class="dateline tablenowrap" width="100%">
						<thead>
							<tr>
								<td width="17px">
									<input type="checkbox" disabled="disabled" alt=""/>
									
								</td>
								<logic:iterate id="tit" name="topTr" offset="0" scope="request">
									<td onclick="tableSort(this)">
										${tit}
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" 
										style="cursor:hand"
										ondblclick="showTopWin('szdw_teaInfo.do?method=teaInfo&doType=view&pkValue=${s.zgh }','720','550');return false;"
									>
										<td>
											<input type="checkbox" name="primarykey_cbv" value="${s.zgh }"/>
										</td>
										<logic:iterate id="c" name="colList">
											<td>
												<bean:write name="s" property="${c }"/>
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>

								<%
											for (int i = 0; i < (Integer) request
											.getAttribute("maxNum")
											- ((List) request.getAttribute("rs")).size(); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<logic:iterate id="t" name="topTr" offset="0" scope="request">
										<td>
											&nbsp;
										</td>
									</logic:iterate>
								</tr>
								<%
								}
								%>

							</logic:notEmpty>
							<logic:empty name="rs">
								<%
											for (int i = 0; i < (Integer) request
											.getAttribute("maxNum"); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<logic:iterate id="t" name="topTr" offset="0" scope="request">
										<td>
											&nbsp;
										</td>
									</logic:iterate>
								</tr>
								<%
								}
								%>
							</logic:empty>
						</tbody>
					</table>
				</div>

				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=teaInfoForm"></jsp:include>
				
			</div>
			<!-- ������ʦ����һ�� ������� -->
			<div id="addJbxx" style="display:none">
				<jsp:include flush="true" page="simpleInfo.jsp"></jsp:include>
			</div>
			
			<%--<!-- ��ʦ���� ����ѡ -->
			<div id="szcol" style="display:none;" >
				<jsp:include flush="true" page="colList.jsp"></jsp:include>
			</div>
			
			
			--%><div id="viewYhz" style="display:none">	
				<jsp:include flush="true" page="tbxx.jsp"></jsp:include>
			</div>
			
			<logic:present name="message">
				<script defer="defer">
					alertInfo('${message}');
				</script>
			</logic:present>
		</html:form>
	</body>
</html>
