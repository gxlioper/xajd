<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsxx/fbgl/fbjg/js/fbjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript">
			jQuery(function($){
				$("#error").hide();
				$("#nj").bind("change",function(){
					jQuery("#error").hide();
					//�Զ����þ���ѧ����Ϣ
					autoSetXsxx();
				});
			});
		</script>
	</head>
	<body>
		<div>
		<html:form action="/fbglbbgl.do?method=add&type=query">
			<input type="hidden" id="pk" value="${pk}" />
			<table width="100%" border="0" class="formlist">
				<tr>
					<td>
					<div style="" id="div_help" class="prompt">
						<h3>
							<span>��ʾ��</span>
						</h3>
						<p>
							ȷ����᳷�����꼶�ύ�����а༶��ѧ����Ϣ
						</p>
					</div>
					</td>
				</tr>
			</table>
			<table width="100%" border="0" class="formlist">
				<tr>
					<th width="40%"><font color='red'>*</font>ѡ���ύ�꼶</th>
					<td>
						<html:select property="nj" styleId="nj"
							style="width:125px;">
							<html:options collection="njList" property="nj"
								labelProperty="nj" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th width="40%">�ɳ���</th>
					<td id="ytj">
						${ytj}
					</td>
				</tr>
				<tr>
					<th width="40%">�ѳ���</th>
					<td id="wtj">
						${wtj}
					</td>
				</tr>
			</table>
		</html:form>
		</div>
		<%@include file="/xsgzgl/comm/browser/progressBar.jsp"%>
		<div style="height:35px;"></div>
		<div>
			<table width="100%" border="0" class="formlist" id="below" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
					<tr>
						<td colspan="2">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button"  onclick="cxzsk();return false;" id="buttonSave">
									ȷ  ��
								</button>							
								<button type="button"  onclick="iFClose();" id="buttonClose">
									��  ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</body>
</html>
