<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
<body onload="checkWinType();">
	<html:form action="/pjpyzgkdwh" method="post">
		<div class="title">
       		<div class="title_img" id="title_m">
         		��ǰ����λ�ã��������� - ��Ϣά�� - ���ʲ���������ʽ����
       		</div>
    	</div>
			<table style="width:100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="2" align="center">
							������ʽ����
						</td>
					</tr>
				</thead>
				<tr style="width:22px">
					<td align="right" width="50%">
						���ʷ�������ʽ��
					</td>
					<td align="left" width="50%">
						רҵ<html:radio property="pmfs" value="zy"></html:radio>&nbsp;
						�༶<html:radio property="pmfs" value="bj"></html:radio>
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
					<button class="button2" id="btn_save" 
						onclick="refreshForm('pjpy_zgkd_pmfssz.do?act=save');document.getElementById('btn_save').disabled=true"
						style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" id="btn_close" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>	
    	<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
					alert("�����ɹ�!");
					Close();
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert("����ʧ��,δѡ���κ�����!");
				</script>
			</logic:equal>
		</logic:present>
	</html:form>
</body>