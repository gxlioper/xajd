<%@ page language="java" import="java.util.*"  pageEncoding="GB18030"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style media='print'>
		.noPrin{display:none;}
	</style>
  </head>
  
  <body>
  	&nbsp;<br/>
  	<p style='text-align:center'>
		<b><span style='font-size:18.0pt;'>
			�Ͼ���ʦѧԺ<u>&nbsp;&nbsp;${rs.bynd }&nbsp;&nbsp;</u>���ҵ����ҵ�Ƽ���
			</span>
		</b>
	</p>
	<br/><br/><br/>
	<table align="center">
		<tr>
			<td width="800px;">
				<b>
					<span style='font-size:12.0pt;height: 20px;'> 
						��ţ�NGJ-QD-15-05&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��ţ�A/0&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��ˮ�ţ�${lsh }
					</span>
				</b>
				<table class="printtab" width="100%" height="800px;">
			  		<tr>
			  			<td colspan="2" width="15%" align="center">����</td>
			  			<td colspan="2" width="17%" align="center">${stu.xm }</td>
			  			<td width="10%" align="center">�Ա�</td>
			  			<td width="10%" align="center">${stu.xb }</td>
			  			<td width="10%" align="center">����</td>
			  			<td width="18%" align="center">${stu.mzmc }</td>
			  			<td rowspan="4" width="20%" align="center">
			  				<img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}"
								 width="96" height="128" />
			  			</td>
			  		</tr>
			  		<tr>
			  			<td colspan="3" align="center">��������</td>
			  			<td colspan="2" align="center">${stu.csrq }</td>
			  			<td colspan="2" align="center">������ò</td>
			  			<td align="center">${stu.zzmmmc }</td>
			  		</tr>
			  		<tr>
			  			<td colspan="3" align="center">��ͥ��ַ</td>
			  			<td colspan="5" align="center">${jtcy.jtszd}</td>
			  		</tr>
			  		<tr>
			  			<td colspan="3" align="center">��������</td>
			  			<td colspan="2" align="center">${jtcy.jtyb }</td>
			  			<td colspan="2" align="center">����״��</td>
			  			<td align="center">${stu.jkzk }</td>
			  		</tr>
			  		<tr height="50px">
			  			<td colspan="3" align="center">��  ��</td>
			  			<td colspan="4" align="center">${stu.tc }</td>
			  			<td align="center">��ϵ�绰</td>
			  			<td align="center">${stu.lxdh }</td>
			  		</tr>
			  		<tr>
			  			<td colspan="4" align="center">��ѧУ�κ�ְ��</td>
			  			<td colspan="5" align="center">
			  				${zw }
			  			</td>
			  		</tr>
			  		<tr>
			  			<td colspan="2" align="center">����<br/><br/>���</td>
			  			<td colspan="7" valign="top">
			  				<p>
			  					${rs.jcqk }
			  				</p>
			  			</td>
			  		</tr>
			  		<tr>
			  			<td align="center">��<br/><br/>ҵ<br/><br/>��<br/><br/>��</td>
			  			<td colspan="8" valign="top">
			  				<p>
			  					&nbsp;&nbsp;${rs.byjd }
			  				</p>
			  				<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
			  				<p align="right">
			  					�����Σ�
			  					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  				</p>
			  			</td>
			  		</tr>
			  	</table>
			</td>
			<td>
				&nbsp;
			</td>
			<td width="800px;">
				<span style='font-size:12.0pt;height: 20px;'> 
					�༶��<u>&nbsp;&nbsp;&nbsp;${stu.bjmc }&nbsp;&nbsp;&nbsp;</u>
					רҵ��<u>&nbsp;&nbsp;&nbsp;${stu.zymc }&nbsp;&nbsp;&nbsp;</u>
				</span>
			  	<table class="printtab" width="100%" height="800px;">
			  		<tr>
			  			<td colspan="10" align="center">ѧ   ϰ   ��   ��</td>
			  		</tr>
			  		<tr>
			  			<td width="8%" align="center">���</td>
			  			<td colspan="3" width="32%" align="center">��  Ŀ</td>
			  			<td width="10%" align="center">�ɼ�</td>
			  			<td width="8%" align="center">���</td>
			  			<td colspan="3" width="30%" align="center">��  Ŀ</td>
			  			<td width="10%" align="center">�ɼ�</td>
			  		</tr>
			  		
			  		<%
			  			List<HashMap<String,String>> cjList = (List<HashMap<String,String>>)request.getAttribute("cjList");
			  			
			  			int i = 0 ;
			  			
			  			while (cjList.size()-1 > i){
			  		 %>
			  		
			  			<tr>
			  				<td align="center"><%=i+1 %></td>
			  				<td align="center" colspan="3"><%=null != cjList.get(i).get("kcmc") ? cjList.get(i).get("kcmc") : "" %></td>
			  				<td align="center"><%=null != cjList.get(i).get("cj") ? cjList.get(i).get("cj") : "" %></td>
			  				<%i++; %>
			  				<td align="center"><%=i+1 %></td>
			  				<td align="center" colspan="3"><%=null != cjList.get(i).get("kcmc") ? cjList.get(i).get("kcmc") : "" %></td>
			  				<td align="center"><%=null != cjList.get(i).get("cj") ? cjList.get(i).get("cj") : "" %></td>
			  			</tr>
					<%
						i++;
						}
					 %>  		
			  		
			  		<tr>
			  			<td align="center" colspan="2" width="13%" style='font-size:11.0pt;font-family:����;"Times New Roman"'>
			  				ְҵ����<br/>�ȼ�
			  			</td>
			  			<td align="center" width="12%" style='font-size:11.0pt;font-family:����;"Times New Roman"'>
			  				${rs.zyjsdj }
			  			</td>
			  			<td align="center" width="13%" style='font-size:11.0pt;font-family:����;"Times New Roman"'>
			  				ְҵ����<br/>
							���۳ɼ�
			  			</td>
			  			<td align="center" width="12%" style='font-size:11.0pt;font-family:����;"Times New Roman"'>
			  				${rs.zjjdllcj }
			  			</td>
			  			<td align="center" colspan="2" width="13%" style='font-size:11.0pt;font-family:����;"Times New Roman"'> 
			  				ְҵ����<br/>���ܳɼ�
			  			</td>
			  			<td align="center" width="12%" style='font-size:11.0pt;font-family:����;"Times New Roman"'>
			  				${rs.zjjdjncj }
			  			</td>
			  			<td align="center" width="13%" style='font-size:11.0pt;font-family:����;"Times New Roman"'>�����ȵ�</td>
			  			<td align="center" width="12%" style='font-size:11.0pt;font-family:����;"Times New Roman"'>
			  				${rs.cxdd }
			  			</td>
			  		</tr>
			  		<tr>
			  			<td align="center" colspan="2" style='font-size:11.0pt;font-family:����;"Times New Roman"'>
			  				<bean:message key="lable.xb" /><br/>�Ƽ�<br/>���
			  			</td>
			  			<td colspan="3" valign="top" style='font-size:11.0pt;font-family:����;"Times New Roman"'>
			  				<p>
			  				&nbsp;&nbsp;&nbsp;&nbsp;${rs.xytjyj }
			  				</p>
			  			</td>
			  			<td align="center" colspan="2" style='font-size:11.0pt;font-family:����;"Times New Roman"'>
			  				����<br/>��λ<br/>���
			  			</td>
			  			<td colspan="3" valign="top" style='font-size:11.0pt;font-family:����;"Times New Roman"'>
			  				<p>
			  					&nbsp;&nbsp;&nbsp;&nbsp;${rs.jsdwyj }
			  				</p>
			  			</td>
			  		</tr>
			  		<tr>
			  			<td align="center" colspan="2" style='font-size:11.0pt;font-family:����;"Times New Roman"'>
			  				��ע
			  			</td>
			  			<td colspan="8" valign="top" style='font-size:11.0pt;font-family:����;"Times New Roman"'>
			  				<p>
			  					&nbsp;&nbsp;&nbsp;&nbsp;${rs.bz }
			  				</p>
			  			</td>
			  		</tr>
			  	</table>
			</td>
		</tr>
	</table>
  	
  	<div align="center" class='noPrin'>
		<button class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
			ҳ������
		</button>
		<button class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
			��ӡԤ��
		</button>
		<button class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
			ֱ�Ӵ�ӡ
		</button>
	</div>
  </body>
</html>
