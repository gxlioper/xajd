<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- ͷ�ļ� -->
<html:html>
<body>
	<!-- ��ӡ�ؼ�begin -->
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style media='print'>
			.noPrin{display:none;};
			.PageNext{
			page-break-after: always;
		}
		</style>
	<!-- end -->
	<html:form action="/stu_info_add" method="post">
		<table width="100%" border="0" id="theTable" align="center">
			<tr>
				<td height="80px" align="center">
					<span  height="60px" style="font-family: ����;margin-bottom: 10px;vertical-align: bottom"><h2>��ͨ���Ƹߵ�ѧУ������ѧ������������</h2></span>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="90%" class="printStyle">
						<tr  align="center" >
							<td  rowspan=6 align="center" valign="middle">
								��<br/>��<br/>��<br/>��
								</p>
							</td>
							<td height="45px">
								��&nbsp;&nbsp;&nbsp;��
							</td>
							<td  height="45px">
								${rs.xm}
							</td>
							<td   height="45px">
								�Ա�
							</td>
							<td colspan=2 >
								${rs.xb}
							</td>
							<td >
								��������
							</td>
							<td  >
								${rs.csrq}
							</td>
							<td rowspan=4 >
								<img src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
									border="0" align="absmiddle" width="96" height="128"  />
							</td>
						</tr>
						<tr align="center">
							<td height="45px">
								��&nbsp;&nbsp;&nbsp;��
							</td>
							<td >
								${rs.mzmc}
							</td>
							<td >
								����<br/>��ò
							</td>
							<td colspan=2 >
								${rs.zzmmmc}
							</td>
							<td >
								��ѧʱ��
							</td>
							<td >
								${rs.rxrq}
							</td>
						</tr>
						<tr align="center" height="25px">
							<td >
								���֤����
							</td>
							<td colspan=6 >
								${rs.sfzh }
							</td>
						</tr>
						<tr align="center" height="25px">
							<td >
								��ϵ�绰
							</td>
							<td colspan=6 >
								${rs.sjhm }
							</td>
						</tr>
						<tr  align="center" height="25px">
							<td  colspan=8 >
								<p>
									<span lang=EN-US >${rs.xxmc}</span>
									<span lang=EN-US style="margin-left:40px ">${rs.xymc}</span>
									<span lang=EN-US style="margin-left:40px ">${rs.nj}</span>
									<span lang=EN-US style="margin-left:40px "> ${rs.bjmc}</span> 
								</p>
							</td>
						</tr>
						<tr height="45px"  align="center">
							<td colspan=2 >
								������ֽ���
							</td>
							<td  colspan=6 >
								&nbsp;
							</td>
						</tr>
						<tr height="45px" align="center">
							<td rowspan=3 >
								��<br/>ͥ<br/>��<br/>��<br/>��<br/>��
							</td>
							<td >
								��ͥ����
							</td>
							<td colspan=5 >
								<logic:present name="rs" property="jthk">
									<logic:equal value="����" property="jthk" name="rs">
										����
									</logic:equal>
									<logic:equal value="ũ��" property="jthk" name="rs">
										ũ��
									</logic:equal>
								</logic:present>
								<logic:notPresent name="rs" property="jthk">
									A������ B��ũ��
								</logic:notPresent>	
							</td>
							<td  height="45px" align="center" >
								��ͥ��<br/>������
							</td>
							<td >
								${rs.jtrs}
							</td>
						</tr>
						<tr height="45px" align="center">
							<td >
								��ͥ��������
							</td>
							<td >
								${rs.jtyzsr}
							</td>
							<td colspan=3 >
								�˾�������
							</td>
							<td >
								${rs.jtrjysr}
							</td>
							<td >
								����<br/>��Դ
							</td>
							<td >
								${rs.srly }
							</td>
						</tr>
						<tr height="45px" align="center">
							<td >
								��ͥסַ
							</td>
							<td colspan=5 >
								${rs.jtdz }
							</td>
							<td >
								����<br/>����
							</td>
							<td >
								${rs.jtyb }
							</td>
						</tr>
						<tr align="center" >
							<td rowspan=6 valign="center">
								��<br/>ͥ<br/>��<br/>Ա<br/>��<br/>��
							</td>
							<td >
								��&nbsp;��
							</td>
							<td >
								����
							</td>
							<td colspan=3 >
								�뱾�˹�ϵ
							</td>
							<td colspan=3 >
								������ѧϰ��λ
							</td>
						</tr>
						<logic:iterate name="cyList" id="cy">
						<tr >
							<td align="center">
									${cy.cyxm }&nbsp;
							</td>
							<td align="center">
									${cy.cynl }&nbsp;
							</td>
							<td align="center"  colspan="3">
									${cy.mc }&nbsp;
							</td>
							<td align="center"  colspan="3">
									${cy.cyzy }&nbsp;
							</td>
						</tr>
						</logic:iterate>
						<tr style="height:250px">
							<td colspan=9 valign=top >
								<span style='font-family:����;"Times New Roman";vertical-align: top;height: 200px' >�������ɣ�${rs.sqly }</span>
								<br/>
								<span lang=EN-US style="float: right;margin-right: 50px">
									<span  style="margin-right: 200px">������ǩ����</span>
									<span style='font-family:����;
 										 &quot;Times New Roman&quot;'>��</span><span
										lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><span
										style='font-family:����;"Times New Roman"'>��</span><span
										lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><span
										style='font-family:����;"Times New Roman"'>��</span>
								</span>
							</td>
						</tr>
						<tr style="height:150px">
							<td colspan=9 valign=top >
								<span style='font-family:����;"Times New Roman";vertical-align: top;height: 100px' >ѧУ��������${rs.shzt3yj }</span>
								<br/>
								<span lang=EN-US style="float: right;margin-right: 50px">
									<span  style="margin-right: 250px">(����)</span>
									<span style='font-family:����;
 										 &quot;Times New Roman&quot;'>��</span><span
										lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><span
										style='font-family:����;"Times New Roman"'>��</span><span
										lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><span
										style='font-family:����;"Times New Roman"'>��</span>
								</span>
								</p>
							</td>
						</tr>
						<tr height=0>
							<td width=7.5%></td>
							<td width=14%></td>
							<td width=8%></td>
							<td width=7% ></td>
							<td width=4% ></td>
							<td width=5% ></td>
							<td width=10% ></td>
							<td width=8% ></td>
							<td width=10% ></td>
						</tr>
					</table>
					
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
