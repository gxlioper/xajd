<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script type="text/javascript">
<!--
//���ƶ���ҳ��ֻ��һ���ı���,�س��ύ�¼� ��дonkeydown�¼�,ע��Ḳ�����еĻس��¼�
		function document.onkeydown() {     
			  var e=event.srcElement;     
			  if(event.keyCode==13) {
				  	return false;     
			  }     
		} 
        //END
</script>
<body>
	<html:form action="/wjcfzjcmwh" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰλ�ã�Υ�ʹ��� - ������� - ��У�쿴ʱ������
			</div>
		</div>
		<fieldset >
			<br><br>
			<div align="center">
			<table width="95%"  class="tbstyle" >
				<thead>
					<tr height="35">
						<td align="right" width="50%">
							��У�쿴ʱ���(��)��
						</td>
						<td width="50%">
							<html:text property="sj" styleId="sj" style="width:50px" maxlength="3" onkeyup="chkIsNum(this)"></html:text>
						</td>
					</tr>
				</thead>
				<logic:equal value="yes" name="writeAble">
				<tr height="35">
					<td align="center" colspan="2">
						<button type="button" class="button2" onclick="refreshForm('wjcf_zjcm_lxcksjsz.do?act=save')"
							style="width: 60px">
							�� ��
						</button>
						&nbsp;&nbsp;
						<button type="button" class="button2" onclick="refreshForm('wjcf_zjcm_lxcksjsz.do')"
							style="width: 60px">
							�� �� 
						</button>
					</td>
				</tr>
				</logic:equal>
			</table>
			</div>
		</fieldset>
		<logic:present name="inserted">
		<logic:equal value="true" name="inserted">
			<script>
				alert('����ɹ�!');
			</script>
		</logic:equal>
		<logic:equal value="false" name="inserted">
			<script>
				alert('����ʧ��!');
			</script>
		</logic:equal>
		</logic:present>
		</html:form>
	</body>
