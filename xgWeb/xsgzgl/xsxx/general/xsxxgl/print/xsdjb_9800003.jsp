<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/head.ini"%>
<title>�㶫ʡ�Ṥҵ��ʦѧԺѧ��ѧ����</title>
<style>
.font_style td{font-size:11pt;font-family:����; }

.head_table td{font-size:12.0pt;font-family:����;
			   mso-ascii-font-family:"Times New Roman";
			   mso-hansi-font-family:"Times New Roman"}
</style>
<%--<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>--%>
<object id="webbrowser" classid="clsid:8856f961-340a-11d0-a96b-00c04fd705a2" height="0"
  width="0" viewastext>
  </object>

<object id=eprint classid="clsid:CA03A5A8-9890-49BE-BA4A-8C524EB06441" codebase="<%=request.getContextPath()%>/xgWeb/images/webprint.cab" viewasext> </object>
<script>   

		//������ҳ��ӡ��ҳüҳ��Ϊ�� 
		function PageSetup_Null() 
		{ 
			try 
			{ 
 
				//webbrowser.printing.portrait = true;//��ӡ
				
			} 
			catch(e){
			    //alert(e);
			} 
		}
		
		function Print() {

			PageSetup_Null();
		  	document.all.webbrowser.ExecWB(6,2);// ��ӡ
		}
		 
		window.onload = function(){	
			Print();
			window.setTimeout("a()","5000");
		}
		function a(){
			
			var xhstr = document.getElementById("xhstr").value;/**xhstr�а��������б�ѡ�еĿ�����*/
			var tempArray = xhstr.split(',');
			if(tempArray[0] == null || tempArray[0] == ""){
				alertInfo("��ӡ������");
				return false;
			}
			else
			{
				document.forms[0].action="xsxx_tygl.do?method=plPrintJsp";
				document.forms[0].submit();
			}
					
		}
</script>

</head>

<body lang=ZH-CN style='text-justify-trim:punctuation'>



	
<div align=center>
<html:form action="/xszz_sqsh" method="post" styleId="xszzSqshForm">
	<input type="hidden" name="xhstr" id="xhstr" value="<bean:write name="xhstr" />"/>
<table width="980px" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
					<b><br/>
					<span style='font-size:21.0pt;font-family:��������;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${xxmc }ѧ��ѧ����
					</span>
					<br/>
					</b>
					<br/>
					<div align="left">
						<table class="head_table">
							<tr>
								<td>רҵϵ��</td>
								<td width="120px">${jbxx.xymc}</td>
								<td>רҵ��</td>
								<td width="120px">${jbxx.zymc}</td>
								<td>�༶��</td>
								<td width="120px">${jbxx.bjmc}</td>
								<td>ѧ�ƣ�</td>
								<td width="60px">${jbxx.xz}</td>
								<td>��ѧʱ�䣺</td>
								<td width="100px">${jbxx.rxrq}</td>
								<td>ѧ���ţ�</td>
								<td width="80px">${jbxx.xh}</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="font_style" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" style='border-collapse:collapse;border:none;'>
						<tr>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
						</tr>
						<tr style="height: 50px">
							<td align="center" colspan="1" >
								<b>����</b>
							</td>
							<td align="center" colspan="2" >
								${jbxx.xm }
							</td>
							<td align="center" colspan="1" >
								<b>�Ա�</b>
							</td>
							<td align="center" colspan="1" >
								${jbxx.xb }
							</td>
							<td align="center" colspan="1" >
								<b>����<br/>����</b>
							</td>
							<td align="center" colspan="3" >
								${jbxx.csrq }
							</td>
							<td align="center" colspan="1" >
								<b>���<br/>֤��</b>
							</td>
							<td align="center" colspan="4" >
								${jbxx.sfzh }
							</td>
							<td align="center" colspan="1" >
								<b>����</b>
							</td>
							<td align="center" colspan="2" >
								${jbxx.mzmc }
							</td>
							<td align="center" colspan="2" rowspan="3" >
								<img style="width:100px;height:130px;" src="xsxx_xsgl.do?method=showPhoto&xh=${jbxx.xh}" border="0"/>
							</td>
						</tr>
						<tr style="height: 50px">
							<td align="center" colspan="1" >
								<b>����<br/>��ò</b>
							</td>
							<td align="center" colspan="2" >
								${jbxx.zzmmmc }
							</td>
							<td align="center" colspan="1" >
								<b>����</b>
							</td>
							<td align="center" colspan="5" >
								${jbxx.jgmc }
							</td>
							<td align="center" colspan="1">
								<b>����<br/>����</b>
							</td>
							<td align="center" colspan="4">
							<logic:equal name="jbxx" property="zd5" value="����">
								������&nbsp;������&nbsp;��ũ��
							</logic:equal>
							<logic:equal name="jbxx" property="zd5" value="�����ũ">
								������&nbsp;������&nbsp;��ũ��
							</logic:equal>
							<logic:equal name="jbxx" property="zd5" value="ũ��">
								������&nbsp;������&nbsp;��ũ��
							</logic:equal>
							<logic:notEqual name="jbxx" property="zd5" value="����">
								<logic:notEqual name="jbxx" property="zd5" value="�����ũ">
									<logic:notEqual name="jbxx" property="zd5" value="ũ��">
										������&nbsp;������&nbsp;��ũ��
									</logic:notEqual>
								</logic:notEqual>
							</logic:notEqual>
							</td>
							<td align="center" colspan="1">
								<b>�Ƿ�<br/>�ͱ�</b>
							</td>
							<td align="center" colspan="2" >
								
							</td>
						</tr>
						<tr style="height: 50px">
							<td align="center" colspan="1" >
								<b>�Ļ�<br/>�̶�</b>
							</td>
							<td align="center" colspan="2" >
								${jbxx.pyccmc }
							</td>
							<td align="center" colspan="1" >
								<b>��ҵ<br/>ѧУ</b>
							</td>
							<td align="center" colspan="5" >
								${jbxx.RXQDW }
							</td>
							<td align="center" colspan="2">
								<b>���á��س�</b>
							</td>
							<td align="center" colspan="3" >
								${jbxx.tc }
							</td>
							<td align="center" colspan="1">
								<b>����<br/>״��</b>
							</td>
							<td align="center" colspan="2">
								${jbxx.jkzk }
							</td>
						</tr>
						<tr style="height: 25px">
							<td rowspan="2">
								<b>��ͥ��<br/>ϸ��ַ</b>
							</td>
							<td rowspan="2" colspan="8">
								${jbxx.jtszd}
							</td>
							<td align="center" colspan="2">
								<b>��ͥ��ϵ��</b>
							</td>
							<td align="center" colspan="3" >
								${jbxx.jtcy1_xm}
							</td>
							<td align="center" colspan="2">
								<b>�����ֻ�����</b>
							</td>
							<td align="center" colspan="3" >
								${jbxx.sjhm}
							</td>
						</tr>
						<tr style="height: 25px">
							<td align="center" colspan="2">
								<b>��ͥ�̶��绰</b>
							</td>
							<td align="center" colspan="3" >
								${jbxx.jtdh}
							</td>
							<td align="center" colspan="2">
								<b>�ҳ��ֻ�����</b>
							</td>
							<td align="center" colspan="3" >
								${jbxx.jtcy1_lxdh2}
							</td>
						</tr>
						<tr style="height: 40px">
							<td rowspan="5">��<br/>ͥ<br/>��<br/>Ҫ<br/>��<br/>Ա</td>
							<td colspan="2">
								<b>����</b>
							</td>
							<td>
								<b>�Ա�</b>
							</td>
							<td>
								<b>����</b>
							</td>
							<td colspan="2">
								<b>�뱾�˹�ϵ</b>
							</td>
							<td style="width: 60px">
								<b>������ò</b>
							</td>
							<td colspan="6">
								<b>������λ</b>
							</td>
							<td align="center" colspan="2">
								<b>ְ��</b>
							</td>
							<td align="center" colspan="3" >
								<b>�绰����</b>
							</td>
						</tr>
						<tr style="height: 40px">
							<td colspan="2">
								${jbxx.jtcy1_xm}
							</td>
							<td>
							</td>
							<td>
							</td>
							<td colspan="2">
								${jbxx.jtcy1_gx}
							</td>
							<td>
							</td>
							<td colspan="6">
								${jbxx.jtcy1_gzdz}
							</td>
							<td align="center" colspan="2">
								${jbxx.jtcy1_zw}
							</td>
							<td align="center" colspan="3" >
								${jbxx.jtcy1_lxdh2}
							</td>
						</tr>
						<tr style="height: 40px">
							<td colspan="2">
								${jbxx.jtcy2_xm}
							</td>
							<td>
							</td>
							<td>
							</td>
							<td colspan="2">
								${jbxx.jtcy2_gx}
							</td>
							<td>
							</td>
							<td colspan="6">
								${jbxx.jtcy2_gzdz}
							</td>
							<td align="center" colspan="2">
								${jbxx.jtcy2_zw}
							</td>
							<td align="center" colspan="3" >
								${jbxx.jtcy2_lxdh2}
							</td>
						</tr>
						<tr style="height: 40px">
							<td colspan="2">
								${jbxx.jtcy3_xm}
							</td>
							<td>
							</td>
							<td>
							</td>
							<td colspan="2">
								${jbxx.jtcy3_gx}
							</td>
							<td>
							</td>
							<td colspan="6">
								${jbxx.jtcy3_gzdz}
							</td>
							<td align="center" colspan="2">
								${jbxx.jtcy3_zw}
							</td>
							<td align="center" colspan="3" >
								${jbxx.jtcy3_lxdh2}
							</td>
						</tr>
						<tr style="height: 40px">
							<td colspan="2">
							</td>
							<td>
							</td>
							<td>
							</td>
							<td colspan="2">
							</td>
							<td>
							</td>
							<td colspan="6">
							</td>
							<td align="center" colspan="2">
							</td>
							<td align="center" colspan="3" >
							</td>
						</tr>
						<tr style="height: 40px">
							<td rowspan="4"><b>ѧ<br/>ϰ<br/>��<br/>��</b></td>
							<td colspan="7"><b>��ʼʱ��</b></td>
							<td colspan="6">
								<b>�Ͷ�ѧУ</b>
							</td>
							<td align="center" colspan="2">
								<b>ְ��</b>
							</td>
							<td align="center" colspan="3" >
								<b>��ע</b>
							</td>
						</tr>
						<tr style="height: 40px">
							<td colspan="7">
								<b>&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��</b>
							</td>
							<td colspan="6">
							</td>
							<td align="center" colspan="2">
							</td>
							<td align="center" colspan="3" >
							</td>
						</tr>
						<tr style="height: 40px">
							<td colspan="7">
								<b>&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��</b>
							</td>
							<td colspan="6">
							</td>
							<td align="center" colspan="2">
							</td>
							<td align="center" colspan="3" >
							</td>
						</tr>
						<tr style="height: 40px">
							<td colspan="7">
								<b>&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��</b>
							</td>
							<td colspan="6">
							</td>
							<td align="center" colspan="2">
							</td>
							<td align="center" colspan="3" >
							</td>
						</tr>
						
					</table>
				</td>
			</tr>
			<tr>
				<td align="left">
				<br/>
					<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
					ע����ѧ����һʽ���ݣ�һ����Ϊѧ����ҵ������һ��<bean:message key="lable.xb" />�浵��
					</span>
				</td>
			</tr>
			<tr><td><br/><br/></td></tr>
			<tr>
				<td>
					<table width="100%" class="font_style" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" style='border-collapse:collapse;border:none;'>
						<tr>
							<td style="width: 6.25%"></td>
							<td style="width: 6.25%"></td>
							<td style="width: 6.25%"></td>
							<td style="width: 6.25%"></td>
							<td style="width: 6.25%"></td>
							<td style="width: 6.25%"></td>
							<td style="width: 6.25%"></td>
							<td style="width: 6.25%"></td>
							<td style="width: 6.25%"></td>
							<td style="width: 6.25%"></td>
							<td style="width: 6.25%"></td>
							<td style="width: 6.25%"></td>
							<td style="width: 6.25%"></td>
							<td style="width: 6.25%"></td>
							<td style="width: 6.25%"></td>
							<td style="width: 6.25%"></td>
						</tr>
						<tr>
							<td colspan="8">
								<b>��һѧ���������</b>
							</td>
							<td colspan="8">
								<b>�ڶ�ѧ���������</b>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<b>��һѧ��</b>
							</td>
							<td colspan="4">
								<b>�ڶ�ѧ��</b>
							</td>
							<td colspan="4">
								<b>��һѧ��</b>
							</td>
							<td colspan="4">
								<b>�ڶ�ѧ��</b>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<p align="left" style="height: 200px">
								<br/>
									&nbsp;&nbsp;&nbsp;&nbsp;${xscxpyxx.py001}
								</p>
								<p align="left">���еȼ���${xscxpyxx.dj001}&nbsp;&nbsp;�����Σ�${xscxpyxx.bzr001}</p>
								<br/>
							</td>
							<td colspan="4">
								<p align="left" style="height: 200px">
								<br/>
									&nbsp;&nbsp;&nbsp;&nbsp;${xscxpyxx.py002}
								</p>
								<p align="left">���еȼ���${xscxpyxx.dj002}&nbsp;&nbsp;�����Σ�${xscxpyxx.bzr002}</p>
								<br/>
							</td>
							<td colspan="4">
								<p align="left" style="height: 200px">
								<br/>
									&nbsp;&nbsp;&nbsp;&nbsp;${xscxpyxx.py101}
								</p>
								<p align="left">���еȼ���${xscxpyxx.dj101}&nbsp;&nbsp;�����Σ�${xscxpyxx.bzr101}</p>
								<br/>
							</td>
							<td colspan="4">
								<p align="left" style="height: 200px">
								<br/>
									&nbsp;&nbsp;&nbsp;&nbsp;${xscxpyxx.py102}
								</p>
								<p align="left">���еȼ���${xscxpyxx.dj102}&nbsp;&nbsp;�����Σ�${xscxpyxx.bzr102}</p>
								<br/>
							</td>
						</tr>
						<tr>
							<td style="height: 50px"><b>����<br/>���</b></td>
							<td colspan="3"></td>
							<td style="height: 50px"><b>����<br/>���</b></td>
							<td colspan="3"></td>
							<td style="height: 50px"><b>����<br/>���</b></td>
							<td colspan="3"></td>
							<td style="height: 50px"><b>����<br/>���</b></td>
							<td colspan="3"></td>
						</tr>
						<tr>
							<td colspan="8">
								<b>����ѧ���������</b>
							</td>
							<td colspan="2" rowspan="2"><b>ѧ���춯��¼</b></td>
							<td colspan="6" rowspan="2"><b>��ҵ����</b></td>
						</tr>
						<tr>
							<td colspan="4">
								<b>��һѧ��</b>
							</td>
							<td colspan="4">
								<b>�ڶ�ѧ��</b>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<p align="left" style="height: 200px">
								<br/>
									&nbsp;&nbsp;&nbsp;&nbsp;${xscxpyxx.py201}
								</p>
								<p align="left">���еȼ���${xscxpyxx.dj201}&nbsp;&nbsp;�����Σ�${xscxpyxx.bzr201}</p>
								<br/>
							</td>
							<td colspan="4">
								<p align="left" style="height: 200px">
								<br/>
									&nbsp;&nbsp;&nbsp;&nbsp;${xscxpyxx.py202}
								</p>
								<p align="left">���еȼ���${xscxpyxx.dj202}&nbsp;&nbsp;�����Σ�${xscxpyxx.bzr202}</p>
								<br/>
							</td>
							<td rowspan="2" colspan="2">
								
							</td>
							<td rowspan="2" colspan="6">
								<p align="left" style="font-size: 11pt">���������</p>
								<p style="height: 210px"></p>
								<p align="center">�������Ÿ���</p>
							</td>
						</tr>
						<tr>
							<td style="height: 50px"><b>����<br/>���</b></td>
							<td colspan="3"></td>
							<td style="height: 50px"><b>����<br/>���</b></td>
							<td colspan="3"></td>
						</tr>
						
						
					</table>
				</td>
			</tr>
		</table>
	</html:form>
	</div>
</body>
</html>