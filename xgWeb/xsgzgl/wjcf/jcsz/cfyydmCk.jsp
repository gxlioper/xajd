<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript">
		//�رյ�����
		function divtmpclose() {
			parent.document.getElementById("tmpdiv1").innerHTML = "";
			try{
				parent.hiddenMessage(true,true);
			}catch(e){				
			}
		}
		</script>
	</head>
	<body>
		<html:form action="/wjcfJcsz_cfyydm" method="post">
			<div class="tab_cur" >
					<p class="location">
						<em>���ĵ�ǰλ��:</em><a>${title } - �鿴</a>
					</p>
			</div>
			<%--<div class="prompt" id="span_qsh" style="display: none">
		       <h3><span>ϵͳ��ʾ��</span></h3>
		       <p>�����Һ��ڱ�¥�����Ѵ��ڣ�<br/></p>
		   	</div>
			--%>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>���ִ�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<logic:present name="rs">
						<tr>
							<th style="width:40%">
								����ԭ������
							</th>
							<td style="width:60%">
								<bean:write name="rs" property="cfyymc"/>
							</td>
						</tr>
						</logic:present>
					</tbody>
				</table>	
				<table width="100%" border="0" class="formlist">					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									<font color="blue">
									</font>
								</div>
								<div class="btn">
									<!-- <button type="button"  class="button2" id="btn_bc"  onclick="modi()">
										�� ��
									</button> -->
									<button type="button"  class="button2"  onclick="divtmpclose();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				
			</div>
		</html:form>
	</body>
</html>
