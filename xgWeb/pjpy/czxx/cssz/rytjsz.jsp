<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript">	  
     		//���ƶ���ҳ��ֻ��һ���ı���,�س��ύ�¼� ��дonkeydown�¼�,ע��Ḳ�����еĻس��¼�
		function document.onkeydown() {     
			  var e=event.srcElement;     
			  if(event.keyCode==13) {
				  	return false;     
			  }     
		} 
        //END
		function chkxy(){
				var ischecked = document.getElementById("chkallxy").checked;
				var chkoneList = document.getElementsByName("chkonexy");
				
				for (var i=0;i<chkoneList.length;i++) {
					chkoneList[i].checked = ischecked;
				}
			}
			
	    function clearData() {
	    	document.getElementById('cj').value='';
				var chkoneList = document.getElementsByName("chkonexy");
				
				for (var i=0;i<chkoneList.length;i++) {
					chkoneList[i].checked = false;
				}
	    }
	</script>
<body>
	<html:form action="/czxxPjpyCssz" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰ����λ�ã��������� - �������� - ���������������
			</div>
		</div>
		<div class="rightcontent">
			<fieldset>
				<legend>
					�������ƺŵĻ�����������
				</legend>
				<table width="95%" class="tbstyle" align="center">
					<thead>
						<tr align="center">
							<td align="right" width="30%">
								�����ɼ���
							</td>
							<td align="left" width="65%">
								<html:text property="cj" styleId="cj" maxlength="4"
									style="width:60px" onkeyup="ckinpdata(this)"></html:text>
								������
							</td>
						</tr>
						<tr>
							<td align="right">
								��ѧ������ѧ�𣨻��ϵ����
							</td>
							<td align="left">
								<input type="checkbox" id="chkallxy" name="chkallxy"
									onclick="chkxy()" style="CURSOR: hand;" />
								<B> ȫѡ:</B>
								<logic:notEmpty name="dmList">
									<table border="0" cellpadding="0" cellspacing="0">
										<logic:iterate name="dmList" id="xyV" indexId="index">
											<%
											if ((index.intValue() + 1) % 3 == 1) {
											%>

											<tr class="alt">
												<%
												}
												%>
												<td nowrap="nowrap">
													<input type="checkbox" id="chkonexy" name="chkonexy"
														value="${xyV.dm }" style="CURSOR: hand;" ${xyV.chk } />
													${xyV.mc }
												</td>
												<%
												if ((index.intValue() + 1) % 3 == 0) {
												%>
											</tr>
											<%
											}
											%>
										</logic:iterate>
									</table>
								</logic:notEmpty>
							</td>
						</tr>
					</thead>
					<logic:equal value="yes" name="writeAble">
						<tr height="35">
							<td align="center" colspan="2">
								<button type="button" class="button2" onclick="refreshForm('pjpy_czxx_rytjsz.do?act=save')"
									style="width: 60px">
									�� ��
								</button>
								&nbsp;&nbsp;
								<button type="button" class="button2" onclick="clearData()" style="width: 60px">
									�� ��
								</button>
							</td>
						</tr>
					</logic:equal>
				</table>
			</fieldset>

		</div>
	</html:form>
	<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
	<center>

	</center>
	<logic:present name="inserted">
		<logic:equal value="yes" name="inserted">
			<script>
					alert("�����ɹ�!");
				</script>
		</logic:equal>
					<logic:equal value="no" name="inserted">
						<script>
							alert("����ʧ��,ԭ����������ݿ����Ѵ�����ͬ��¼!");
						</script>
					</logic:equal>
	</logic:present>
</body>
