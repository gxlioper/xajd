<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script type='text/javascript' src='/xgxt/dwr/interface/moneyFormat.js'></script>
<script type="text/javascript">
<!--
	function getDxje(){
		var sqzje = $('sqzje').value;
		
		if (0 != sqzje) {
			moneyFormat.format(sqzje,function(data){
				temp.innerHTML += data+"&nbsp;&nbsp;(��&nbsp;&nbsp;"+sqzje+"&nbsp;&nbsp;Ԫ)";
			})
		} else {
			temp.innerHTML += "��Ԫ"+"&nbsp;&nbsp;(��&nbsp;&nbsp;"+sqzje+"&nbsp;&nbsp;Ԫ)";
		}
		
	}
	
//-->
</script>
<!-- ͷ�ļ� -->
<html:html>
<body onload="getDxje();">
	<!-- ��ӡ�ؼ�begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
	<!-- end -->
	<html:form action="/typj" method="post">
		<input type="hidden" name="sqzje" id="sqzje" value="${rs.sqzje }"/>
		
		
		<table width="95%" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
					<h2>
						����ѧԺѧ������ѧ��������V1.2
					</h2>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="7%"></td>
							<td width="7%"></td>
							<td width="8%"></td>
							<td width="8%"></td>
							<td width="4%"></td>
							<td width="6%"></td>
							<td width="5%"></td>
							<td width="5%"></td>
							<td width="8%"></td>
							<td width="9%"></td>
							<td width="8%"></td>
							<td width="10%"></td>
							<td width="8%"></td>
							<td width="8%"></td>
						</tr>
						<tr height="40px">
							<td align="center">����</td>
							<td align="center" colspan="2">${rs.xm }</td>
							<td align="center">�Ա�</td>
							<td align="center" colspan="2">${rs.xb }</td>
							<td align="center" colspan="2">Ժϵ</td>
							<td align="center" colspan="2">${rs.xymc }</td>
							<td align="center">�꼶<br/>�༶</td>
							<td align="center" colspan="3">${rs.nj }<br/>
								${rs.bjmc }
							</td>
						</tr>
						<tr height="40px">
							<td align="center">����</td>
							<td align="center"colspan="2">${rs.ssbh }</td>
							<td align="center">��ϵ�绰</td>
							<td align="center"colspan="7">${rs.lxdh }</td>
							<td align="center">�����루Ԫ��</td>
							<td align="center"colspan="2">${rs.jtyzsr }</td>
						</tr>
						<tr height="40px">
							<td align="center">��ͥ<br/>��ַ</td>
							<td align="center" colspan="10">${rs.jtdz }</td>
							<td align="center">���˿ڣ��ˣ�</td>
							<td align="center"colspan="2">${rs.jtrs }</td>
						</tr>
						<tr style="height:40px">
							<td rowspan="${cyNum+1 }" align="center">
									��<br>
									ͥ<br>
									��<br>
									Ա<br>
									��<br>
									��
							</td>
							<td align="center" colspan="2">
									����
							</td>
							<td align="center" colspan="2">
									��ν
							</td>
							<td align="center" colspan="2">
									����
							</td>
							<td align="center" colspan="2">
									�����루Ԫ��
							</td>
							<td align="center" colspan="5">
									������ѧϰ����λ
							</td>
						</tr>
						<logic:iterate name="cyList" id="cy">
						<tr style="height:30px">
							<td align="center"  colspan="2">
									${cy.cyxm }&nbsp;
							</td>
							<td align="center"  colspan="2">
									${cy.mc }&nbsp;
							</td>
							<td align="center" colspan="2">
									${cy.cynl }&nbsp;
							</td>
							<td align="center" colspan="2">
								<logic:present name="cy" property="cynsr">
									<script>
										document.write(Math.round(Number(${cy.cynsr })/12));
									</script>
								</logic:present>
								&nbsp;
							</td>
							<td align="center"  colspan="5">
									${cy.cygzdw }&nbsp;
							</td>
						</tr>
						</logic:iterate>
						<tr style="height:40px">
							<td  align="center"colspan="2">Ӧ���ܷ��ã�Ԫ��</td>
							<td  align="center"colspan="2">${rs.yjzfy }</td>
							<td  align="center"colspan="3">�ѽ��ܷ��ã�Ԫ��</td>
							<td  align="center"colspan="3">${rs.sjzfy }</td>
							<td  align="center"colspan="2">Ƿ���ܷ��ã�Ԫ��</td>
							<td  align="center"colspan="2">${rs.qjzfy }</td>
						</tr>
						<tr style="height:40px">
							<td align="center" colspan="3">����ɷѷ�ʽ��ѡ��<br/>����һ��򡰡̡���</td>
							<td colspan="11">
								<logic:equal name="rs" property="sqjffs" value="����">
									1�̣������⣩<u>&nbsp;${rs.sqzje }&nbsp;</u>Ԫ  2�������_____Ԫ  3������_______Ԫ
								</logic:equal>
								<logic:equal name="rs" property="sqjffs" value="����">
									1�������⣩____Ԫ  2�̣������<u>&nbsp;${rs.sqzje }&nbsp;</u>Ԫ  3������_______Ԫ
								</logic:equal>
								<logic:equal name="rs" property="sqjffs" value="����">
									1�������⣩____Ԫ  2�������_____Ԫ  3�̣�����<u>&nbsp;${rs.sqzje }&nbsp;</u>Ԫ
								</logic:equal>
								<logic:notPresent name="rs" property="sqjffs">
									1�������⣩____Ԫ  2�������_____Ԫ  3������_______Ԫ
								</logic:notPresent>
							</td>
						</tr>
						<tr style="height:40px">
							<td colspan="2" align="center">�����ܽ��</td>
							<td colspan="12">
								<div id="temp" align="center">
									
								</div>
							</td>
						</tr>
						<tr>
							<td align="center">������<br/>��ŵ</td>
							<td colspan="13">
								<p style="height:180px">
									<br/><br/><br/><br/><br/>
									&nbsp;&nbsp;&nbsp;&nbsp;�������ṩ��ͥ�����ʵ����Ū�����٣�Ը�����ѧУ������
								</p>
								<div align="right">
									������ǩ����
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;									
								</div>
							</td>
						</tr>
						<tr>
							<td align="center">Ժϵѧ<br/>������<br/>������<br/>���</td>
							<td colspan="13">
								<p style="height:180px">
									<br/><br/><br/>
									���о�����ͬ�������<br/>
									<logic:equal name="rs" property="sqjffs" value="����">
									1�������⣩<u>&nbsp;${rs.sqzje }&nbsp;</u>Ԫ  2�������_____Ԫ  3������_______Ԫ
								</logic:equal>
								<logic:equal name="rs" property="sqjffs" value="����">
									1�������⣩____Ԫ  2�������<u>&nbsp;${rs.sqzje }&nbsp;</u>Ԫ  3������_______Ԫ
								</logic:equal>
								<logic:equal name="rs" property="sqjffs" value="����">
									1�������⣩____Ԫ  2�������_____Ԫ  3������<u>&nbsp;${rs.sqzje }&nbsp;</u>Ԫ
								</logic:equal>
								
								<logic:notPresent name="rs" property="sqjffs">
									1�������⣩____Ԫ  2�������_____Ԫ  3������_______Ԫ
								</logic:notPresent>
									
								</p>
								<div align="right">
									����Աǩ�£�
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									 Ժ��ϵ���쵼ǩ�£�
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;		
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;									
								</div>
							</td>
						</tr>
					</table>
					<div align="right">
						����ѧԺѧ���������������Ʊ�
					</div>
				</td>
			</tr>
		</table>
	</html:form>
	<div align="center" class='noPrin'>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
			ҳ������
		</button>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
			��ӡԤ��
		</button>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
			ֱ�Ӵ�ӡ
		</button>
	</div>
</body>
</html:html>
