<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/pjpy/pjpyComm.js"></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		function onShow(){

		}
		
		function theEnd(){
		
			
				var url = "general_pjpy_index_ajax.do?method=dataToHis";
	
			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				var parameter={};
				jQuery.post(url,
					parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result);
						submitPjlc("ok");
						if(window.dialogArguments.document.getElementById("btn_sx")){
							window.dialogArguments.document.getElementById("btn_sx").click();
						}
					}
				);
		}
		
		jQuery(function(){
			onShow();
		})
		
		</script>
	</head>
	<body  >
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
<%--			<p class="help">--%>
<%--				<a href="#" onclick="return false;" --%>
<%--					onmouseover ="showHelpDiv()"--%>
<%--					onmouseout="showHelpDiv()"--%>
<%--				>--%>
<%--				��������</a><img src="<%=stylePath%>/images/ico_new02.gif" />--%>
<%--			</p>--%>
		</div>
		<!-- ���� end-->
		
		<!-- ��ʾ��Ϣ end-->
<%--		<div class="prompt">--%>
<%--			<h3>--%>
<%--				<span>ϵͳ��ʾ��</span>--%>
<%--			</h3>--%>
<%--			<p>--%>
<%--				����ƶ������Ͻ�<font color="blue">��������</font>���ɲ鿴��ģ������˵����</br>--%>
<%--				<span id="div_help" style="display: none">--%>
<%--				1.������Ĭ��չʾ���Ǳ�����ѧ��ѧ�ڵ����ݡ�</br>--%>
<%--				</span>--%>
<%--			</p>--%>
<%--		</div>--%>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="hidden_start" value="no"/>
			
			
				<table width="95%" border="0" class="formlist">	
					<thead>
						<tr style="height:22px">
							<th colspan="4">
								<span>
									<logic:equal name="pjzq" value="xn">
										${pjxn }ѧ�������������
									</logic:equal>
								</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
						<tr>
							<td width="">
								<div class="tab" style="overflow-x:hidden;overflow-y:auto;height:200px;width: 100%">
								<table width="99%" class="formlist">
									<tr>
										<th width="24%">
											<div align="left">��Ŀ����</div>
										</th>
										<th width="24%">
											<div align="left">����/�ϱ� ����</div>
										</th>
										<th width="24%">
											<div align="left">�������</div>
										</th>
										<th width="24%">
											<div align="left">�ܽ��</div>
										</th>
									</tr>
									<logic:notEmpty name="bcpjtjInfo">
										<logic:iterate name="bcpjtjInfo" id="bcpjtj">
										<tr>
											<td>
												${bcpjtj.xmmc }
											</td>
											<td>
												${bcpjtj.sqrs }
											</td>
											<td>
												${bcpjtj.hdrs }
											</td>
											<td>
												${bcpjtj.zje }
											</td>
										</tr>
										</logic:iterate>
									</logic:notEmpty>
								</table>
								</div>
							</td>
						</tr>
					</tbody>
					
					<tbody>
						<tr>
							<td>
							<!--��ע begin-->
							<div class="readme"  id="div_csh_yes" style="height: 200px">
								<h2>��ȷ�Ϻ�ϵͳ����ִ�����²���</h2>
								<div class="readcon">
									<ul>
										<li id="li_001">�������ڵ�������¼����������ʷ�⣬�������Щ��Ϣ</li>
										<li id="li_002">��ѧ���û�����ǰ��"�ҵ����� - ������ʷ���"���в鿴</li>
										<li id="li_004">ѧ���û�����ǰ��"�ҵ����� - �ҵ��������"���в鿴</li>
										<li id="li_008">��ѧ����Ϣ����Ҳ���Խ��в鿴������������ؽ��</li>
										<li id="li_005">��ѧ���û�����ǰ��"�ҵ����� - ������ʷ���"���н��������Ŀ�ǼǱ�Ĵ�ӡ</li>
									</ul>
								</div>
							</div>	
							</td>
						</tr>
					</tbody>
					
			    </table>
		    
			<div>
				<table width="99%" border="0" class="formlist">	
				<tfoot>
						<tr>
							<td>
								<div class="btn">
									<button type="button"  onclick="theEnd()">ȷ ��</button>
									<button type="button"  onclick="Close();return false;">�� ��</button>
								</div>
							</td>
						</tr>
				    </tfoot>
				   </table>
			</div>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>