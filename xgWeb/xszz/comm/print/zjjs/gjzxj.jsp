<%@ page language="java" contentType="text/html; charset=GBK"%>
<html>
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<meta http-equiv=Content-Type content="text/html; charset=gb2312">
		<link rel=Edit-Time-Data href="��ѧ��.files/editdata.mso">
		<title>������ѧ������������</title>
		<!-- ��ӡ�ؼ�begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
		<style>
			.radic {
				position:relative;
			}
			.radic em {
				position:absolute;
				top:3px; 
				left:-4px;
				font-family:Arial;
				font-size:22px;
			}
		</style>
	</head>
	<body bgcolor="#FFFFFF" lang=ZH-CN>
		<input type="hidden" id="sfzh" value="${rs.sfzh }" />

		<div align="center">
			<p align=center>
				<b><span
					style='font-size:22.0pt;font-family:����;letter-spacing:1.0pt'>��${rs.xn}ѧ�꣩������ѧ������������</span>
				</b>
			</p>
			<br />
			<div align="left" style="width:99%;">
				<b>ѧУ��</b><b>${xxmc }</b>&nbsp;
				<b>Ժϵ��</b><b>${rs.xymc }</b>&nbsp;
				<b>רҵ��</b><b>${rs.zymc }</b>&nbsp;
				<b>�༶��</b><b>${rs.bjmc }</b>
			</div>
			<table class="printtab nowrap" border="0" align="center" style="width:99%">
				<%--<tr class="print">
					<td width="7%">
					</td>
					<td width="12%">
					</td>
					
					<td width="4.5%">
					</td>
					<td width="4.5%">
					</td>
					<td width="2.2%">
					</td>
					<td width="2.3%">
					</td>
					<td width="2%">
					</td>
					
					<td width="2.5%">
					</td>
					<td width="4.5%">
					</td>
					<td width="4.5%">
					</td>

					<td width="4.5%">
					</td>
					<td width="4.5%">
					</td>
					<td width="4.5%">
					</td>
					<td width="2.2%">
					</td>
					
					<td width="2.3%">
					</td>
					<td width="4.5%">
					</td>
					<td width="5%">
					</td>
					<td width="2.2%">
					</td>
					<td width="2.3%">
					</td>
					<td width="4.5%">
					</td>
					<td width="4.5%">
					</td>
					<td width="4.5%">
					</td>
					<td width="4.5%">
					</td>
					<td width="4.5%">
					</td>
				</tr>

				--%><tr class="nowrap" height="35px">
					<th rowspan=4 align=center  width="7%">
						<br />
						��
						<br />
						��
						<br />
						��
						<br />
						��
						<br />
					</th>
					<td align=center  width="12%">
						����
					</td>
					<td colspan="5" align=center width="15%">
						${rs.xm }
					</td>
					<td colspan="3" align=center width="12%">
						�Ա�
					</td>
					<td colspan="4" align=center width="16.5%">
						${rs.xb }
					</td>
					<td colspan="4" align=center width="13.5%">
						��������
					</td>
					<td colspan="6" align=center width="24%">
						${rs.csrq }
					</td>
				</tr>
				<tr height="35px" class="nowrap">
					<td align="center">
						ѧ��
					</td>
					<td colspan="5" align="center">
						${rs.xh }
					</td>
					<td colspan="3" align=center>
						����
					</td>
					<td colspan="4" align="center">
						${rs.mzmc }
					</td>
					<td colspan="4" align=center>
						��ѧʱ��
					</td>
					<td colspan="6" align="center">
						${rs.rxrq }
					</td>
				</tr>
				<tr height="35px" class="nowrap">
					<td align="center">
						������ò
					</td>
					<td colspan="6" align="center" width="17%">
						${rs.zzmmmc }
					</td>
					<td colspan="4" align="center" width="20%">
						��ϵ�绰
					</td>
					<td colspan="12" align="center">
						${rs.lxdh }
					</td>
				</tr>
				<tr height="35px" class="nowrap">
					<td align="center">
						���֤��
					</td>
					<td id="s0" align="center" width="4.5%">
						&nbsp;
					</td>
					<td id="s1" align="center" width="4.5%">
						&nbsp;
					</td>
					<td id="s2" colspan="2" align="center">
						&nbsp;
					</td>
					<td id="s3" colspan="2" align="center">
						&nbsp;
					</td>
					<td id="s4" align="center" width="4.5%">
						&nbsp;
					</td>
					<td id="s5" align="center" width="4.5%"> 
						&nbsp;
					</td>
					<td id="s6" align="center" width="4.5%">
						&nbsp;
					</td>
					<td id="s7" align="center" width="4.5%">
						&nbsp;
					</td>
					<td id="s8" align="center" width="4.5%">
						&nbsp;
					</td>
					<td id="s9" colspan="2" align="center">
						&nbsp;
					</td>
					<td id="s10" align="center" width="4.5%">
						&nbsp;
					</td>
					<td id="s11" align="center" width="4.5%">
						&nbsp;
					</td>
					<td id="s12" align="center" colspan="2" >
						&nbsp;
					</td>
					<td id="s13" align="center" width="4.5%">
						&nbsp;
					</td>
					<td id="s14" align="center" width="4.5%">
						&nbsp;
					</td>
					<td id="s15" align="center" width="4.5%">
						&nbsp;
					</td>
					<td id="s16" align="center" width="4.5%">
						&nbsp;
					</td>
					<td id="s17" align="center" width="4.5%">
						&nbsp;
					</td>
				</tr>
				<tr height="35px" class="nowrap">
					<th rowspan=4 align="center">
						<br />
						��
						<br />
						ͥ
						<br />
						��
						<br />
						��
						<br />
						��
						<br />
						��
						<br />
					</td>
					<td align="center">
						��ͥ����
					</td>
					<td colspan="12" align="center">
						<logic:present name="rs" property="jthk">
							<logic:equal value="����" property="jthk" name="rs">
								<span class="radic">A<em>��</em></span>������&nbsp;&nbsp;&nbsp;&nbsp;B��ũ��
							</logic:equal>
							<logic:equal value="ũ��" property="jthk" name="rs">
								A������&nbsp;&nbsp;&nbsp;&nbsp;<span class="radic">B<em>&radic;</em></span>��ũ��
							</logic:equal>
							<logic:equal value="" property="jthk" name="rs">
								A������ B��ũ��
							</logic:equal>
						</logic:present>
						<logic:notPresent name="rs" property="jthk">
							A������ B��ũ��
						</logic:notPresent>
					</td>
					<td colspan="4" align="center">
						������Դ
					</td>
					<td colspan="6" align="center">
						${rs.srly }
					</td>
				</tr>
				<tr height="35px" class="nowrap">
					<td align="center" valign="center" style="padding:0px 0px 0px 0px">
						��ͥ����
						<br />
						����
					</td>
					<td colspan="12" align="center">
						${rs.jtyzsr }
					</td>
					<td colspan="4" align="center" valign="center"  style="padding:0px 0px 0px 0px">
						��ͥ�˿�
						<br />
						����
					</td>
					<td colspan="6" align="center">
						${rs.jtrs }
					</td>
				</tr>
				<tr height="35px" class="nowrap">
					<td align="center">
						��ͥסַ
					</td>
					<td colspan="12" align="center">
						${rs.jtdz }
					</td>
					<td colspan="4" align="center">
						��������
					</td>
					<td colspan="6" align="center">
						${rs.jtyb }
					</td>
				</tr>
				<tr height="35px" class="nowrap">
					<td align="center">
						�϶����
					</td>
					<td colspan="22" align="center">
						A����ͥ�����ر�����
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						B����ͥ����һ������
					</td>
				</tr>

				<tr style="height:22px" height="35px" class="nowrap">
					<th rowspan="5" align="center">
						<br />
						��
						<br />
						ͥ
						<br />
						��
						<br>
						Ա
						<br>
						��
						<br>
						��
						<br />
					</th>
					<td align="center">
						����
					</td>
					<td colspan="3" align="center" width="12.5%">
						����
					</td>
					<td colspan="5" align="center" width="14.5%">
						�뱾�˹�ϵ
					</td>
					<td colspan="14" align="center">
						������ѧϰ��λ
					</td>
				</tr>
				<logic:iterate name="cyList" id="cy" length="4">
					<tr height="35px" class="nowrap">
						<td align="center">
							${cy.cyxm }&nbsp;&nbsp;
						</td>
						<td colspan="3" align="center">
							${cy.cynl }&nbsp;&nbsp;
						</td>
						<td colspan="5" align="center">
							${cy.mc }&nbsp;&nbsp;
						</td>
						<td colspan="14" align="center">
							${cy.cygzdw }&nbsp;&nbsp;
						</td>
					</tr>
				</logic:iterate>
				<%
							int cyNum = Integer.valueOf(request.getAttribute("cyNum")
							.toString());
					for (int i = 0; i < 4 - cyNum; i++) {
				%>
				<tr class="nowrap" style="height:35px">
					<td align="center">
						&nbsp;
					</td>
					<td align="center" colspan="3">
						&nbsp;
					</td>
					<td align="center" colspan="5">
						&nbsp;
					</td>
					<td align="center" colspan="14">
						&nbsp;
					</td>
				</tr>
				<%
				}
				%>

				<tr height="110px" class="nowrap">
					<th align=center>
						<br />
						����
						<br />
						����
						<br />
					</th>
					<td colspan="23">
						<p style="height: 50px">
							&nbsp;&nbsp;&nbsp;&nbsp; ${rs.sqly }
						</p>
						<p align=right>
							������ǩ������ǩ����
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr height="110px" class="nowrap">
					<th align="center">
						<br />
						Ժ
						<br />
						(ϵ)
						<br />
						��
						<br />
						��
						<br />
					</th>
					<td colspan="23">
						<p style="height: 50px">
							&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align=right>
							��Ժϵ���£�
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr height="110px" class="nowrap">
					<th align=center>
						<br />
						ѧ
						<br />
						У
						<br />
						��
						<br />
						��
						<br />
					</th>
					<td colspan="23">
						<p style="height: 50px">
							&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align=right>
							��ѧУ���£�
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp;
						</p>
					</td>
				</tr>
			</table>
			<br />
			<div align="right">
				�Ʊ��㽭ʡѧ�������������� 2010���
			</div>
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
			<script type="text/javascript">
		    	var sfzh = jQuery('#sfzh').val();
		    	for(var i=0;i<sfzh.length;i++){
			    	var id = "#s" + i;
			    	var sfzhs = sfzh.substring(i,i+1);
			    	if(jQuery(id)){
			    		jQuery(id).html(sfzhs); 
			    	}
		    	}
		    </script>
		</div>
		<p>
			&nbsp;
		</p>
	</body>
</html>
