<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<jsp:directive.page import="java.util.ArrayList" />
<jsp:directive.page import="java.util.HashMap" />
<!-- ͷ�ļ� -->
<html:html>
<body>
	<!-- ��ӡ�ؼ�begin -->
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style media='print'>
			.noPrin{display:none;}
		</style>
	<!-- end -->
	<html:form action="/typj" method="post">
		<p align=center style='text-align:center'>
			<b><span
				style='font-size:18.0pt;font-family:����;"Times New Roman"'>����ũҵ��ѧѧ����ͥ��������</span>
			</b>
		</p>
		<br />
		<p align=left>
			<b><span
				style='font-size:14.0pt;font-family:������;"Times New Roman";"Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ժ��ϵ����</span>
			</b><u><span lang=EN-US style='font-size:14.0pt;'>${rs.xymc }
			</span>
			</u><b><span
				style='font-size:14.0pt;font-family:������;"Times New Roman"'>רҵ��</span>
			</b><u><span lang=EN-US style='font-size:14.0pt;'>${rs.zymc }
			</span>
			</u><b><span
				style='font-size:14.0pt;font-family:������;"Times New Roman"'>ѧ�ţ�</span>
			</b><u><span lang=EN-US style='font-size:14.0pt;'>
				${rs.xh }
			</span>
			</u>
		</p>
		<table width="100%" class="printstyle" align="center">
			<tr style="height:40px">
				<td width="5.5%" rowspan=5>
					<p align=center style='
  text-align:center'>
						<b><span style='font-family:������'>ѧ<br/>��<br/>��<br/>��<br/>��<br/>��<br/>��<br/>��</span> </b>
					</p>
				</td>
				<td width="11%" colspan=2 >
					<p align=center style='text-align:center'>
						<span style='font-family:������'>����</span>
					</p>
				</td>
				<td width="22%" colspan=4 align=center>
					${rs.xm }
				</td>
				<td width="5.5%">
					<p align=center style='text-align:center'>
						<span style='font-family:������'>�Ա�</span>
					</p>
				</td>
				<td width="11%" colspan=2 align=center>
					${rs.xb }
				</td>
				<td width="13%" colspan=3>
					<p align=center style='text-align:center'>
						<span style='font-family:������'>��������</span>
					</p>
				</td>
				<td width="13%" colspan=3 align=center>
					${rs.csrq }
				</td>
				<td width="15%" colspan=2 rowspan=4>
					<p align=center style='text-align:center'>
						<b><span style='  font-family:����'>����Ƭ��</span> </b>
					</p>
				</td>
			</tr>
			<tr style="height:40px">
				<td colspan=2>
					<p align=center style='text-align:center'>
						<span style='font-family:������'>����<br/>��ò</span>
					</p>
				</td>
				<td colspan=4 align=center>
					${rs.zzmmmc }
				</td>
				<td>
					<p align=center style='text-align:center'>
						<span style='font-family:������'>����</span>
					</p>
				</td>
				<td colspan=2 align=center>
					${rs.mzmc }
				</td>
				<td colspan=3>
					<p align=center style='text-align:center'>
						<span style='font-family:������'>��ҵ��ѧ</span>
					</p>
				</td>
				<td colspan=3 align=center>
					
				</td>
			</tr>
			<tr style="height:40px">
				<td colspan=2>
					<p align=center style='text-align:center'>
						<span style='font-family:������'>���֤<br/>����</span>
					</p>
				</td>
				<td colspan=5 align=center>
					${rs.sfzh}
				</td>
				<td colspan=2>
					<p align=center style='text-align:center'>
						<span style='font-family:������'>����<br/>�س�</span>
					</p>
				</td>
				<td colspan=6 align=center>
					&nbsp;
				</td>
			</tr>
			<tr style="height:40px">
				<td colspan=2>
					<p align=center style='text-align:center'>
						<span style='font-family:������'>��ͥ��<br/>ϸ��ַ</span>
					</p>
				</td>
				<td colspan=13 align=center>
					${rs.jtdz }
				</td>
			</tr>
			<tr style="height:40px">
				<td colspan=2>
					<p align=center style='text-align:center'>
						<span style='font-family:������'>����<br/>����</span>
					</p>
				</td>
				<td colspan=5 align=center>
					${rs.jtyb }
				</td>
				<td colspan=3 align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:������'>��ϵ�绰</span>
					</p>
				</td>
				<td colspan=7 align=center>
					<p>
						${rs.jtdh }
					</p>
				</td>
			</tr>
			<tr style="height:40px">
				<td rowspan=2>
					<p align=center style='text-align:center'>
						<b><span style='  font-family:����;"Times New Roman"'>��ͥ<br/>����<br/>����</span>
						</b>
					</p>
				</td>
				<td colspan=2 align=center>
					<p align=center style='text-align:center;line-height:90%'>
						<span style='font-family:����;"Times New Roman"'>����</span>
					</p>
				</td>
				<td colspan=15 align=center>
					<logic:equal name="rs" property="jthk" value="����">
					<p align=center style='text-align:center'>
						<span style='font-family:����;"Times New Roman"'>��ͥ�˿ڹ�</span><u><span
							lang=EN-US>&nbsp;&nbsp;${rs.jtrs }&nbsp;&nbsp; </span> </u><span
							style='font-family:����;"Times New Roman"'>�ˣ�ȫ����������</span><u><span
							lang=EN-US>&nbsp;&nbsp;${rs.jtnzsr }&nbsp;&nbsp;
						</span> </u><span style='font-family:����;
  &quot;Times New Roman&quot;'>Ԫ���˾�������</span><u><span
							lang=EN-US>&nbsp;&nbsp;${rs.jtrjsr }&nbsp;&nbsp;</span> </u><span
							style='font-family:����;"Times New Roman"'>Ԫ</span>
					</p>
					</logic:equal>
					<logic:notEqual name="rs" property="jthk" value="����">
					<p align=center style='text-align:center'>
						<span style='font-family:����;"Times New Roman"'>��ͥ�˿ڹ�</span><u><span
							lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span> </u><span
							style='font-family:����;"Times New Roman"'>�ˣ�ȫ����������</span><u><span
							lang=EN-US>&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> </u><span
							style='font-family:����;"Times New Roman"'>Ԫ���˾�������</span><u><span
							lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span> </u><span
							style='font-family:����;"Times New Roman"'>Ԫ</span>
					</p>
					</logic:notEqual>
				</td>
			</tr>
			<tr style="height:40px">
				<td colspan=2 align=center>
					<p align=center style='text-align:center;line-height:90%'>
						<span style='font-family:����;"Times New Roman"'>ũ��</span>
					</p>
				</td>
				<td colspan=15 align=center>
					<logic:equal name="rs" property="jthk" value="ũ��">
					<p align=center style='text-align:center'>
						<span style='font-family:����;"Times New Roman"'>��ͥ�˿ڹ�</span><u><span
							lang=EN-US>&nbsp;&nbsp;${rs.jtrs }&nbsp;&nbsp; </span> </u><span
							style='font-family:����;"Times New Roman"'>�ˣ�ȫ����������</span><u><span
							lang=EN-US>&nbsp;&nbsp;${rs.jtnzsr }&nbsp;&nbsp;
						</span> </u><span style='font-family:����;
  &quot;Times New Roman&quot;'>Ԫ���˾�������</span><u><span
							lang=EN-US>&nbsp;&nbsp;${rs.jtrjsr }&nbsp;&nbsp;</span> </u><span
							style='font-family:����;"Times New Roman"'>Ԫ</span>
					</p>
					</logic:equal>
					<logic:notEqual name="rs" property="jthk" value="ũ��">
					<p align=center style='text-align:center'>
						<span style='font-family:����;"Times New Roman"'>��ͥ�˿ڹ�</span><u><span
							lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span> </u><span
							style='font-family:����;"Times New Roman"'>�ˣ�ȫ����������</span><u><span
							lang=EN-US>&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> </u><span
							style='font-family:����;"Times New Roman"'>Ԫ���˾�������</span><u><span
							lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span> </u><span
							style='font-family:����;"Times New Roman"'>Ԫ</span>
					</p>
					</logic:notEqual>
				</td>
			</tr>
			<tr style="height:40px">
				<td rowspan=6>
					<p align=center style='
  text-align:center'>
						<b><span style='font-family:������'>��<br/>ͥ<br/>��<br/>Ա<br/>��<br/>��</span> </b>
					</p>
				</td>
				<td colspan=2 align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:������'>����</span>
					</p>
				</td>
				<td align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:������'>����</span>
					</p>
				</td>
				<td colspan=3 align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:������'>�뱾�˹�ϵ</span>
					</p>
				</td>
				<td colspan=6 align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:������'>������ѧϰ����λ</span>
					</p>
				</td>
				<td width="6%" align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:������'>ְҵ</span>
					</p>
				</td>
				<td colspan=2 width=11% align=center>
					<p >
						<span style='font-family:������'>�����루Ԫ��</span>
					</p>
				</td>
				<td colspan=2  align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:������'>����״��</span>
					</p>
				</td>
			</tr>
			
				<%
				ArrayList<HashMap<String,String>>cyList=(ArrayList<HashMap<String,String>>)request.getAttribute("cyList");
				int len=0;
				if(cyList!=null && cyList.size()>0){
					len=cyList.size();
				}
				
				for(int i=0;i<len;i++){
					HashMap<String,String>cyMap=cyList.get(i);
				%>
				<tr>
				 <td colspan=2 align=center>
						<div align="center">
							<%=cyMap.get("cyxm")==null ? "" : cyMap.get("cyxm")%>
						</div>
					</td>
					<td align=center>
						<div align="center">
							<%=cyMap.get("cynl")==null ? "" : cyMap.get("cynl")%>
						</div>
					</td>
					<td colspan=3 align=center>
						<div align="center">
							<%=cyMap.get("mc")==null ? "" : cyMap.get("mc")%>
						</div>
					</td>
					<td colspan=6 align=center>
						<div align="center">
							<%=cyMap.get("cygzdw")==null ? "" : cyMap.get("cygzdw")%>
						</div>
					</td>
					<td width="6%" align=center>
						<div align="center">
							<%=cyMap.get("cyzy")==null ? "" : cyMap.get("cyzy")%>
						</div>
					</td>
					<td  colspan=2 width="10%" align=center>
						<div align="center">
							<%=cyMap.get("cynsr")==null ? "" : cyMap.get("cynsr")%>&nbsp;&nbsp;
						</div>
					</td>
					<td colspan=2  align=center>
						<div align="center">
							<%=cyMap.get("cyjkzk")==null ? "" : cyMap.get("cyjkzk")%>
						</div>
					</td>
				</tr>
				<%
				}
				%> 
				 
				 
				<%
				
				for(int i=0;i<5-len;i++){
				%>
				<tr>
					<td colspan=2 align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
					<td align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
					<td colspan=3 align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
					<td colspan=6 align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
					<td width="6%" align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
					<td  colspan=2 width="10%" align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
					<td colspan=2  align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
				</tr>
				<%
				}
				%>
			<tr style="height:40px">
				<td rowspan=3>
					<p align=center style='text-align:center'>
						<b><span style='  font-family:����;"Times New Roman"'>����<br/>���<br/>˵��</span>
						</b>
					</p>
				</td>
				<td colspan=17>
					<p style='line-height:90%'>
						<span lang=EN-US style='font-family:
  ����'>1.</span><span
							style='font-family:����'>�ڵ����Ƿ�������ܵ�����<u><span lang=EN-US>
							<logic:equal name="rs" property="kzzd4" value="��">
							&nbsp;&nbsp;��&nbsp;&nbsp;
							</logic:equal>
							<logic:equal name="rs" property="kzzd4" value="��">
							&nbsp;&nbsp;��&nbsp;&nbsp;
							</logic:equal>
							</span> </u>���Ǣڷ�����ǣ��������<u><span lang=EN-US>&nbsp;&nbsp;${rs.kzzd5 }&nbsp;&nbsp;&nbsp;&nbsp;
							</span> </u>��������λ<u>��<span lang=EN-US>&nbsp;${rs.kzzd6 }&nbsp;
							</span> </u> </span>
					</p>
				</td>
			</tr>
			<tr style="height:40px">
				<td colspan=17>
					<p style='line-height:90%'>
						<span lang=EN-US style='font-family:
  ����'>2.</span><span
							style='font-family:����'>����Ϊ<u><span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span> </u>���ٹ¶��ڵ�����Ů�۲м�����ʿ��Ů���Ÿ���ͥ��Ů���¸�ְ����Ů��</span>
					</p>
				</td>
			</tr>
			<tr style="height:40px">
				<td colspan=17>
					
						<span lang=EN-US style='font-family:����'>3.</span><span lang=EN-US
							style='font-family:������'> </span><span style='font-family:������'>��ͥ������Ȼ�ֺ������<u><span
								lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span> </u>����ͥ����ͻ�������¼���<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��</span><br/>
						<span style='font-family:������'>&nbsp;&nbsp;&nbsp;��ͥ��Ա��м����������Ͷ������������<u><span
								lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span> </u>��</span>
						<br/>
						<span style='font-family:������'>&nbsp;&nbsp;&nbsp;��ͥ��Աʧҵ�����<u><span
								lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span> </u>����ͥǷծ�����<u><span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span> </u>��</span>
						<br/>
						<span style='font-family:������'>&nbsp;&nbsp;&nbsp;���������<u><span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span> </u>��</span>
					
				</td>
			</tr>
			<tr >
				<td>
					<p align=center style='
  text-align:center'>
						<b><span style='font-family:������'>ǩ<br/>��</span> </b>
					</p>
				</td>
				<td width="5.5%">
					<p align=center style='text-align:center'>
						<span style='font-family:������'>ѧ<br/>��<br/>��<br/>��</span>
					</p>
				</td>
				<td colspan=3 width=14%>
					&nbsp;
				</td>
				<td>
					<p align=center style='text-align:center'>
						<span style='font-family:������'>ѧ��<br/>�ҳ�<br/>���<br/>����</span>
					</p>
				</td>
				<td colspan=3>
					&nbsp;
				</td>
				<td colspan=3>
					<p align=center style='text-align:center'>
						<span style='font-family:������'>ѧ����ͥ<br/>���ڵ���<br/>���ֵ�<br/>��������</span>
					</p>
				</td>
				<td colspan=6>
					<p>
						<span style='font-family:������'>������ǩ�֣�<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span> </span>
					</p>
					<p>
						<span style='font-family:������'>��λ���ƣ�</span>
					</p>
					<p>
						<span style='font-family:������'>���Ӹǹ��£�</span>
					</p>
					<p align=center style='text-align:center;
  '>
						<span lang=EN-US style='font-family:������'>&nbsp;&nbsp;&nbsp;&nbsp;
						</span><span style='font-family:������'>��<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;
						</span>��<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp; </span>��</span>
					</p>
				</td>
			</tr>
			<tr style="height:40px">
				<td rowspan=2>
					<p align=center style='text-align:center'>
						<b><span style='font-family:������'>����<br/>����<br/>��Ϣ</span> </b>
					</p>
				</td>
				<td colspan=4>
					<p align=center style='text-align:center'>
						<span style='font-family:������'>��ϸͨѶ��ַ</span>
					</p>
				</td>
				<td colspan=13>
					${rs.kzzd1 }
				</td>
			</tr>
			<tr style="height:40px">
				<td colspan=4>
					<p align=center style='text-align:center'>
						<span style='font-family:������'>��������</span>
					</p>
				</td>
				<td colspan=4>
					${rs.kzzd3 }
				</td>
				<td colspan=3>
					<p align=center style='text-align:center'>
						<span style='font-family:������'>��ϵ�绰</span>
					</p>
				</td>
				<td colspan=6>
					<p>
						${rs.kzzd2 }
					</p>
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
