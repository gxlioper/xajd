<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script language="javascript"
	src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script language="javascript" src="pjpy/ynys/ynysJs/ynys.js"></script>
<script type="text/javascript">
<!--
		//���ƶ���ҳ��ֻ��һ���ı���,�س��ύ�¼� ��дonkeydown�¼�,ע��Ḳ�����еĻس��¼�
		function document.onkeydown() {     
			  var e=event.srcElement;     
			  if(event.keyCode==13) {
				  	return false;     
			  }     
		}
//-->
</script>
<body onload="checkWinType();">
	<html:form action="/xmlgpjpy" method="post">
		<input type="hidden" name="pt" id="pt"/>
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰλ��:�������� - �������� - ��ѧ���������
			</div>
		</div>
		<table style="width:100%" class="tbstyle">
			<thead>
				<tr>
					<td colspan="2" align="center">
						�޸����ñ���
					</td>
				</tr>
			</thead>
			<tr style="width:22px">
				<td align="right" width="50%">
					ѧ�꣺
				</td>
				<td align="left">
					${rs.xn }
				</td>
			</tr>
			<tr style="width:22px">
				<td align="right">
					��ѧ��
				</td>
				<td align="left">
					${rs.mc }
				</td>
			</tr>
			<tr style="width:22px">
				<td align="right">
					���ţ�
				</td>
				<td align="left">
					${rs.bmmc }
				</td>
			</tr>
			<tr style="width:22px">
				<td align="right">
					�꼶��
				</td>
				<td align="left">
					${rs.nj }
				</td>
			</tr>
			<tr style="width:22px">
				<td align="right">
					����������
				</td>
				<td align="left">
					${rs.bmrs }
				</td>
			</tr>

			<tr style="width:22px">
				<td align="right">
					<font color="red">*</font>�񽱱�����
				</td>
				<td align="left">
					<html:text property="bl" styleId="bl" style="width:90px" onkeyup="ckinpdata(this)" maxlength="4"></html:text>
					<font color="red">% (����1-100֮�������)</font>
				</td>
			</tr>
						<tr style="width:22px">
				<td align="right">
					����������
				</td>
				<td align="left">
					${rs.tzrs } (��)
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
				<button type="button" class="button2" id="btn_save"
					onclick="saveinfo('pjpy_xmlg_modiBlszxx.do?operType=save','bl')"
					style="width:80px">
					�� ��
				</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" class="button2" id="btn_close" onclick="Close();return false;"
				style="width:80px" id="buttonClose">
				�� ��
			</button>
		</div>
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>
