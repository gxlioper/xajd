<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/xszz/xszzFunction.js"></script>
		<script language="javascript">
		//����ģ��
		function xzmb(){
			var url = "/xgxt/commImpExp.do?method=commImpExp&doType=exp";
			wjcfDataExport(url);
		}
		</script>
	</head>
	<body onload="">
		<html:form action="/commImpExp" enctype="multipart/form-data">
			<!-- ������ -->
			<%@ include file="/xszz/hiddenValue.jsp"%>
			<!-- ������ end-->
			<!-- ���� -->
			<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>

			<br>
			<br>
			<div class="tab">
			<table class="formlist" align="center" width="80%">
			<tbody>
				<tr>
					<td align="right" style="width: 20%">
						�����ļ�ѡ��
					</td>
					<td>
						<input type="file" name="uploadFile" style="width:60%" id="kk" contenteditable="false"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="red">ע���ļ���СС��&lt;10M&gt;</font>
						<br>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="red">������ģ����ͨ����ģ�����أ��û��ֶ�������ܻ����쳣</font>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="button" class="button2" 
							id="buttonSave"
							onclick="if(check_File('kk','')){saveUpdate('/xgxt/commImpExp.do?method=commImpExp&doType=imp','')}"
							style="width: 80px">
							ȷ&nbsp;&nbsp;��
						</button>
						&nbsp;&nbsp;
						<button type="button" class="button2" 
							id="buttonSave"
							onclick="xzmb()"
							style="width: 80px">
							����ģ��
						</button>
					</td>
				</tr>
				</tbody>
			</table>
			</div>
		</html:form>
		<!-- ��ʾ��Ϣ -->
		<%@ include file="other/tsxx.jsp"%>
	</body>
</html>