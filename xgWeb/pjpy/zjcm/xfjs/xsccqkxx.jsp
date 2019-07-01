<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/xfjs.js'></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script>
		function getWjInfo(){
			var pkValue = val('xh')+val('xn')+ val('xq')+val('ccrq')+val('jclxdm')+val('bjdm');	
			xfjs.getWjxx(pkValue,function(data){
				if(data != null && data !=""){
					if(data.cf != null && data.cf != ""){
						document.getElementById('tsTr').style.display='';
						document.getElementById('zkkjs').innerText = data.kkzjs;
						document.getElementById('cfmc').innerText = data.cf;
					}
				}
				document.getElementById('dccckkzjs').innerText = data.kkzjs == null ? "" : data.kkzjs;
			});
		}
	</script>
	</head>
	<body onload="getWjInfo()">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a><bean:write name="title" />
				</a>
			</p>
		</div>

		<html:form action="/pjpyxfjs" method="post">
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�༶ѧ����</span>
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
									<button type="button" onclick="window.close();return false;" id="buttonClose">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>
								ѧ��
							</th>
							<td align="left">
								${model.xh}
								<input type="hidden" id="xh" value="${model.xh}" />
							</td>
							<th>
								ѧ��
							</th>
							<td align="left">
								${model.xn}
								<input type="hidden" value="${model.xn}" name="xn" />
							</td>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td align="left">
								${model.xm}
							</td>
							<th>
								ѧ��
							</th>
							<td align="left">
								${model.xqmc}
								<input type="hidden" id="xq" value="${model.xq}" />
							</td>

						</tr>
						<tr>
							<th>
								�༶
							</th>
							<td align="left">
								${model.bjmc}
								<input type="hidden" id="bjdm" value="${model.bjdm}" />
							</td>
							<th>
								�������
							</th>
							<td align="left">
								${model.ccrq}
								<input type="hidden" id="ccrq" value="${model.ccrq}" />
							</td>
						</tr>

						<tr>
							<th>
								�������
							</th>
							<td align="left">
								${model.jclxmc}
								<input type="hidden" id="jclxdm" value="${model.jclxdm}" />
							</td>
							<th>
								Υ��
							</th>
							<td align="left">
								${model.wjlxmc}
							</td>
						</tr>
						<tr>
							<th>
								���ν���
							</th>
							<td align="left">
								${model.wjcs}
							</td>
							<th>
								���
							</th>
							<td align="left">
								${model.qjlxmc}
							</td>
						</tr>
						<tr>
							<th>
								���γ������ܴ���
							</th>
							<td align="left">
								<div id="dccckkzjs"></div>
							</td>
							<th>
								����û�����
							</th>
							<td align="left">
								<logic:equal value="xx" name="model" property="ccyhlx">
						ѧУ
					</logic:equal>
								<logic:equal value="xy" name="model" property="ccyhlx">
									<bean:message key="lable.xsgzyxpzxy" />
								</logic:equal>
							</td>
						</tr>
						<tr>
							<th>
								��ע
							</th>
							<td align="left" colspan="3">
								${model.bz}
							</td>
						</tr>
						<tr id="tsTr" style="display:none">
							<td align="right" colspan="4">
								<font color="red">��ʾ����ѧ�����γ���ܿ��ν���Ϊ��<b id="zkkjs"></b>,Ӧ�ô���<b
									id="cfmc"></b>���֣�</font>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>
