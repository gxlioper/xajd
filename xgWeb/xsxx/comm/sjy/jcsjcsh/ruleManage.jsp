<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">		
		//�����ֶ�����
		function showRuleUpdate(rule,color){
			var url = "/xgxt/sjyJcsjcsh.do?method=ruleUpdate";
				url+="&rule="+rule;
				url+="&color="+color;
				
			showTips('���������У���ȴ�......');
			
			refreshForm(url);
		}
		
		//ִ�й���
		function doRule(){
			if (confirm("��Ҫ�������ƶ��Ĺ��򣬸��¸���ʱ����ȷ�ϲ���\nע��δ�ƶ�������ֶν����ᱻ����")) {
				saveUpdate('/xgxt/sjyJcsjcsh.do?method=ruleManage&doType=tb','');
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
				���ֶι�����<font color="green">��ɫ</font>�����ʾ���ֶ�ֵ�����Ϲ���
				<font color="red">��ɫ</font>���ʾ���ڲ��Ϸ���¼����Ҫ���й�����ӡ�
				��ɹ����ƶ���������ִ�С���ť��ִ���ƶ��õĹ���
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->	
		
		<html:form action="/sjyJcsjsz">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="czxm" value="${czxm }"/>
			<!-- ������ end-->
			
			<div class="tab">		
				<!-- ҳ�������� -->
				<table class="formlist" border="0" align="center" style="width: 100%;">
					<thead>
						<tr>
							<td colspan="4"></td>
						</tr>
					</thead>
					<tbody>
						<logic:iterate name="ruleList" id="trRule">
							<tr style="">	
								<logic:iterate name="trRule" property="tdList" id="tdRule">
								<td style="width: 25%" bgcolor="${tdRule.color }">
									<a href="#" onclick="showRuleUpdate('${tdRule.dm }','${tdRule.color }')" >${tdRule.mc }</a>
								</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									
									<button type="button" onclick="doRule()" id="buttonSave">
										ִ ��
									</button>
										
									<button type="button" onclick="Close();return false;" id="buttonClose">
										�� ��
									</button>
									
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			<!-- ��ʾ��Ϣ end-->
		</html:form>
	</body>
</html>