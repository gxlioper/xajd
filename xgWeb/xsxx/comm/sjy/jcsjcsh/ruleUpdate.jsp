<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">		
		//���ع���ѡ��
		function showRuleManage(){
			var url = "/xgxt/sjyJcsjcsh.do?method=ruleManage";			
			refreshForm(url);
		}
		
		//��������ƶ�
		function saveRule(){
		
			var num =  document.getElementsByName("zdh").length;
			var flag = true;
		
			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("zdh")[i];
				if(obj.value ==  ""){
					flag = false;
				}
			}
			
			var url = "sjyJcsjcsh.do?method=ruleUpdate&doType=save";
			
			if(flag){
				if (confirm("��Ҫִ�б����������ȷ�����ƶ��ĸ������")) {
					if($("rule").value == "xsxxb_xzqkm"){
						setZdhValue();
					}
					saveUpdate(url,"");
				}
			}else{
				if (confirm("���в��ֹ���δ�ƶ���ȷ����ִ�б��������")) {
					if($("rule").value == "xsxxb_xzqkm"){
						setZdhValue();
					}
					saveUpdate(url,"");
				}
			}
		}
		</script>
	</head>
	<body onload="">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>����Դ - �����ƶ�</a>
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
				��������Ѿ��Ƿ��Ϲ�������ݣ������账������ǲ����Ϲ�������ݣ���Ҫ������Ӧ��ת�����ſ���
				ʹ����뵽ѧ����ʽ���С�
				</br>
				<font color="blue">ע���粻ȷ�ϣ��벻Ҫ����óȻ�޸ģ�����ϵ�����Ա�������ݴ���</font>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->	
		
		<html:form action="/sjyJcsjcsh">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="czxm" value="${czxm }"/>
			<input type="hidden" name="rule" value="${rule }"/>
			<input type="hidden" name="zd" value="${zd }"/>
			<input type="hidden" name="lyb" value="${lyb }"/>
			<!-- ������ end-->
			
			<div class="tab">	
				<!-- �����ƶ� -->	
						
					<!-- ���Ŵ����_������� -->
					<logic:equal name="rule" value="bmdmb_bmlb">
						<%@ include file="rule/bmdmb_bmlb.jsp"%>
					</logic:equal>
					<!-- רҵ�����_�ϼ����� -->
					<logic:equal name="rule" value="zydmb_bmdm">
						<%@ include file="rule/zydmb_bmdm.jsp"%>
					</logic:equal>
					<!-- �༶�����_����רҵ -->
					<logic:equal name="rule" value="bjdmb_zydm">
						<%@ include file="rule/bjdmb_zydm.jsp"%>
					</logic:equal>
					<!-- ѧ����Ϣ��_�����༶ -->
					<logic:equal name="rule" value="xsxxb_bjdm">
						<%@ include file="rule/xsxxb_bjdm.jsp"%>
					</logic:equal>
					<!-- ѧ����Ϣ��_�Ա� -->
					<logic:equal name="rule" value="xsxxb_xb">
						<%@ include file="rule/xsxxb_xb.jsp"%>
					</logic:equal>
					<!-- ѧ����Ϣ��_ѧ��״̬ -->
					<logic:equal name="rule" value="xsxxb_xjztm">
						<%@ include file="rule/xsxxb_xjztm.jsp"%>
					</logic:equal>
					<!-- ѧ����Ϣ��_�������� -->
					<logic:equal name="rule" value="xsxxb_xzqkm">
						<%@ include file="rule/xsxxb_xzqkm.jsp"%>
					</logic:equal>
					
				<!-- �����ƶ� end-->
					
				<!-- ������ť -->
				<table class="formlist" border="0" align="center" style="width: 100%;">
					<tfoot>
						<tr>
							<td>
								<div class="btn">
									
									<logic:notEqual name="color" value="green">
										<button type="button" onclick="saveRule()" id="buttonSave">
											�� ��
										</button>
									</logic:notEqual>
										
									<button type="button" onclick="showRuleManage()" id="buttonClose">
										�� ��
									</button>
									
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				<!-- ������ť end-->
			</div>
			<!-- ��ʾ��Ϣ -->
			<logic:present name="message">
				<script defer="defer">
					if($("message") && $("message").value != ""){
					
						alert($("message").value);
						
						$("message").value = "";
						$("doType").value = "";
					}
				</script>
			</logic:present>
			<!-- ��ʾ��Ϣ end-->
		</html:form>
	</body>
</html>