<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<html>

	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<title>�㽭��ҵְҵ����ѧԺѧ��Ʒ�¿�</title>
		<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<object id=eprint classid="clsid:CA03A5A8-9890-49BE-BA4A-8C524EB06441" codebase="images/webprint.cab" viewasext> </object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<script type="text/javascript">
		function printPdk(){
			var shr= prompt("����������� :","");
			shr = null !=shr ? shr : "";
			window.open('moralCard.do?method=viewMoralCard&pkValue=${rs.xh}&flg=true&shr='+shr);
		}
		
		
		function printPage() {
			 var body = window.document.body.innerHTML;
             var printArea = parent.document.getElementById("ifm").innerHTML;
             window.document.body.innerHTML = printArea;
             window.print("", 5000);
             window.document.body.innerHTML = body;
        }
        
        function pdkPrint() {
			if (null == document.all.eprint.defaultPrinterName || document.all.eprint.defaultPrinterName.length==0){
				alert("���Ȱ�װ��ӡ������ִ�д˹��ܣ�");
				return;
			}
		  document.all.eprint.InitPrint();
		  document.all.eprint.SetMarginMeasure(1);//1mm��default, 2 inch
		  document.all.eprint.header = "";
		  document.all.eprint.footer = "";
		  document.all.eprint.Print(true);//ֱ�Ӵ�ӡ
	   }
        
        
        <logic:equal value='ld' name="flg">
        jQuery(function(){
        	var pk = jQuery('#pkValue').val();
        	var xhArr = jQuery('#xhArr').val();
        	
        	if ('' != pk){
        		pdkPrint();
        	}
        	
        	if ('' != xhArr){
	        	setTimeout('printMoralCard()',5000);
        	}
        })
        </logic:equal>
        function printMoralCard(){
        	var xhArr = jQuery('#xhArr').val();
        	var shr = jQuery('#shr').val();
        	var xh = xhArr.split(',');
        
        	if (null != xh && xh.length > 0){
        		var pkValue = xh[0];
        		var temp = '';
        		for (var i=0;i < xh.length; i++){
        			if (i != 0){
	        			temp+=xh[i];
	        			temp+=',';
        			}
        		}
        		temp = temp.substring(0,temp.length-1);
        		document.forms[0].action = 'moralCard.do?method=viewMoralCard&flg=ld&shr='+shr+'&xhArr='+temp+'&pkValue='+pkValue;
				document.forms[0].submit();
        	}
        }
        
	</script>
	</head>
	<body>
		<html:form action="/moralCard" method="post">
			<input type="hidden" value="${xhArr}" name="xhArr" id="xhArr" />
			<input type="hidden" value="${shr}" name="shr" id="shr" />
			<input type="hidden" value="${flg}" name="flg" id="flg" />
			<input type="hidden" value="${pkValue}" name="pkValue" id="pkValue" />
			<% String xxdm = (String)request.getAttribute("xxdm"); %>
			<center>
				<span style="font-size:22px;font-family:����">${xxmc}ѧ��Ʒ�¿�</span>
			
				<br/>
				<table class="printtab" width="85%" style="font-size:12px">
					<tr style="height:0px;line-height:0px;font-size:0px;">
						<td width="12%"></td>
						<td width="10%"></td>
						<td width="10%"></td>
						<td width="3%"></td>
						<td width="5%"></td>
						<td width="5%"></td>
						<td width="4%"></td>
						<td width="4%"></td>
						<td width="8%"></td>
						<td width="5%"></td>
						<td width="7%"></td>
						<td width="7%"></td>
						<td width="5%"></td>
						<td></td>
					</tr>
					<tr height="28px">
						<td align="center">
							ѧ&nbsp;&nbsp;&nbsp;&nbsp;��
						</td>
						<td align="center">
							${rs.xh }
						</td>
						<td align="center" colspan="2">
							��&nbsp;&nbsp;&nbsp;&nbsp;��
						</td>
						<td align="center" colspan="4" >
							${rs.mzmc }
						</td>
						<td align="center" colspan="2">
							����<bean:message key="lable.xb" />
						</td>
						<td align="center" colspan="2"> 
							${rs.xymc }
						</td>
						<td align="center" rowspan="4" colspan="2">
							<img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}"
								 width="96" height="128" /> 
						</td>
					</tr>
					<tr height="28px">
						<td align="center">
							��&nbsp;&nbsp;&nbsp;&nbsp;��
						</td>
						<td align="center">
							${rs.xm }
						</td>
						<td align="center" colspan="2">
							��&nbsp;&nbsp;&nbsp;&nbsp;��
						</td>
						<td align="center" colspan="4">
							${rs.jgmc }
						</td>
						<td align="center" colspan="2">
							ר&nbsp;&nbsp;&nbsp;&nbsp;ҵ
						</td>
						<td align="center" colspan="2">
							${rs.zymc }
						</td>
					</tr>
					<tr height="28px">
						<td align="center">
							��&nbsp;��&nbsp;��
						</td>
						<td align="center">
							${rs.cym }
						</td>
						<td align="center" colspan="2">
							�������
						</td>
						<td align="center" colspan="4">
							${rs.pyccmc }
						</td>
						<td align="center" colspan="2">
							��&nbsp;&nbsp;&nbsp;&nbsp;��
						</td>
						<td align="center" colspan="2">
							${rs.bjmc }
						</td>
					</tr>
					<tr height="28px">
						<td align="center">
							��&nbsp;&nbsp;&nbsp;&nbsp;��
						</td>
						<td align="center">
							${rs.xb }
						</td>
						<td align="center" colspan="2">
							��ұ��ò
						</td>
						<td align="center" colspan="4">
							${rs.zzmmmc }
						</td>
						<td align="center" colspan="2">
							ѧ&nbsp;&nbsp;&nbsp;&nbsp;��
						</td>
						<td align="center" colspan="2">
							${rs.xz }
						</td>
					</tr>
					<tr height="28px">
						<td align="center">
							���֤��
						</td>
						<td align="center" colspan="7">
							${rs.sfzh }
						</td>
						<td align="center" colspan="2">
							��������
						</td>
						<td align="center" colspan="4" >
							${rs.csrq }
						</td>
					</tr>
					<tr height="28px">
						<td align="center">
							��ѧ����
						</td>
						<td align="center" colspan="7">
							${rs.rxrq }
						</td>
						<td align="center" colspan="2">
							��ҵ����
						</td>
						<td align="center" colspan="4">
							${rs.byny }
						</td>
					</tr>
					<tr height="28px">
						<td align="left" colspan="2">
							��ͥסַ��ϸͨѶ��ַ
						</td>
						<td align="left" colspan="12" >
							${jtcy.jtszd}
						</td>
					</tr>
					<tr height="28px">
						<td align="left" colspan="2">
							��ϵ�绰
						</td>
						<td align="center" colspan="6">
							${rs.sjhm }
						</td>
						<td align="center" colspan="2">
							��&nbsp;&nbsp;&nbsp;&nbsp;��
						</td>
						<td align="center" colspan="4" >
							${rs.jtyb }
						</td>
					</tr>
					<tr height="28px">
						<td align="center" colspan="14" >
							<b>��Ҫ��ͥ����ϵ</b>
						</td>
					</tr>
					<tr height="28px">
						<td align="center">
							��ν
						</td>
						<td align="center">
							����
						</td>
						<td align="center" colspan="4" style='<%if("12871".equals(xxdm)){%>display:none;<%}%>'>
							������ò
						</td>
						<td align="center" colspan="<%if("12871".equals(xxdm)){%>8<%}else{%>4<%} %>">
							������λ
						</td>
						<td align="center" colspan="2">
							<%if("12871".equals(xxdm)){%>
								��λ�绰
							<%}else{%>
								ְ��
							<%} %>
						</td>
						<td align="center" colspan="2">
							<%if("12871".equals(xxdm)){%>
								���˵绰
							<%}else{%>
								��ϵ�绰
							<%} %>
						</td>
					</tr>
					<tr height="28px">
						<td align="center">
							${jtcy.jtcy1_gx}
						</td>
						<td align="center">
							${jtcy.jtcy1_xm}
						</td>
						<td align="center" colspan="4" style='<%if("12871".equals(xxdm)){%>display:none;<%}%>'>
							${jtcy.jtcy1_zzmmmc }
						</td>
						<td align="center" colspan="<%if("12871".equals(xxdm)){%>8<%}else{%>4<%} %>">
							${jtcy.jtcy1_gzdz }
						</td>
						<td align="center" colspan="2">
							<%if("12871".equals(xxdm)){%>
								${jtcy.jtcy1_lxdh2}
							<%}else{%>
								${jtcy.jtcy1_zw}
							<%} %>
						</td>
						<td align="center" colspan="2">
							${jtcy.jtcy1_lxdh1}
						</td>
					</tr>
					<tr height="28px">
						<td align="center">
							${jtcy.jtcy2_gx}
						</td>
						<td align="center">
							${jtcy.jtcy2_xm}
						</td>
						<td align="center" colspan="4" style='<%if("12871".equals(xxdm)){%>display:none;<%}%>'>
							${jtcy.jtcy2_zzmmmc}
						</td>
						<td align="center" colspan="<%if("12871".equals(xxdm)){%>8<%}else{%>4<%} %>">
							${jtcy.jtcy2_gzdz }
						</td>
						<td align="center" colspan="2">
							<%if("12871".equals(xxdm)){%>
								${jtcy.jtcy2_lxdh2}
							<%}else{%>
								${jtcy.jtcy2_zw}
							<%} %>
						</td>
						<td align="center" colspan="2">
							${jtcy.jtcy2_lxdh1}
						</td>
					</tr>
					<tr height="28px">
						<td align="center">
							${jtcy.jtcy3_gx}
						</td>
						<td align="center">
							${jtcy.jtcy3_xm}
						</td>
						<td align="center" colspan="4" style='<%if("12871".equals(xxdm)){%>display:none;<%}%>'>
							${jtcy.jtcy3_zzmmmc}
						</td>
						<td align="center" colspan="<%if("12871".equals(xxdm)){%>8<%}else{%>4<%} %>">
							${jtcy.jtcy3_gzdz }
						</td>
						<td align="center" colspan="2">
							<%if("12871".equals(xxdm)){%>
								${jtcy.jtcy3_lxdh2}
							<%}else{%>
								${jtcy.jtcy3_zw}
							<%} %>
						</td>
						<td align="center" colspan="2">
							${jtcy.jtcy3_lxdh1}
						</td>
					</tr>
					<tr height="28px">
						<td align=center colspan="14">
							<b>�����ȵ�</b>
						</td>
					</tr>

					<%
							List<HashMap<String, String>> dyddList = (List<HashMap<String, String>>) request.getAttribute("dyddList");
							int i = 0;
							while (i < dyddList.size()) {
					%>
					<tr height="28px">
						<td align="center">
								<%=i < dyddList.size() ? dyddList.get(i).get("xqmc") : ""%> 
						</td>
						<td align="center">
							<%=i < dyddList.size() ? (null == dyddList.get(i).get("pjjg") ? "" : dyddList.get(i).get("pjjg")): ""%>
						</td>
						<%
						i++;
						%>
						<td align="center" colspan="2">
							<%=i < dyddList.size() ? dyddList.get(i).get("xqmc") : ""%> 
						</td>
						<td align="center" colspan="3">
							<%=i < dyddList.size() ? (null == dyddList.get(i).get("pjjg") ? "" : dyddList.get(i).get("pjjg"))
							: ""%> 
						</td>
						<%
						i++;
						%>
						<td align="center" colspan="2">
							<%=i < dyddList.size() ? dyddList.get(i).get(
							"xqmc") : ""%> 
						</td>
						<td align="center" colspan="2">
							<%=i < dyddList.size() ? (null == dyddList.get(i).get("pjjg") ? "" : dyddList.get(i).get("pjjg")): ""%> 
						</td>
						<%
						i++;
						%>
						<td align="center" colspan="2">
							<%=i < dyddList.size() ? dyddList.get(i).get(
							"xqmc") : ""%> 
						</td>
						<td align="center">
							<%=i < dyddList.size() ? (null == dyddList.get(i).get("pjjg") ? "" : dyddList.get(i).get("pjjg")): ""%> 
						</td>
					</tr>
					<%
							i++;
							}
							
							
							int rows = 0 ;
							
							if(dyddList.isEmpty()){
								rows = 3;
							} else {
								if (dyddList.size()%3 == 0){
									rows = 3 - dyddList.size()/3;
								} else {
									rows = 3 - (Math.round(dyddList.size()/3)+1);
								}
							}
						
						int count = 0;
						while(rows>	count){
					%>
					<tr height="28px">
						<td align="center">
							&nbsp;
						</td>
						<td align="center" >
						</td>
						<td align="center" colspan="2">
						</td>
						<td align="center" colspan="2">
						</td>
						<td align="center" colspan="3">
						</td>
						<td align="center" colspan="2">
						</td>
						<td align="center" colspan="2">
						</td>
						<td align="center">
						</td>
					</tr>
					<%
						count++;
						}
					 %>
				

					<tr height="28px">
						<td align="center" colspan=14>
								<b>���ͼ�¼</b>
						</td>
					</tr>
					<tr height="28px">
						<td align="center">
							��&nbsp;&nbsp;��
						</td>
						<td align="center" colspan=5>
							��&nbsp;&nbsp;��
						</td>
						<td align="center" colspan=5>
							��&nbsp;&nbsp;��
						</td>
						<td align="center" colspan=4>
							��&nbsp;&nbsp;ע
						</td>
					</tr>

					<logic:iterate id="p" name="pjpyList" offset="0">
						<tr height="28px">
							<td align="center">
								${p.sqsj }
							</td>
							<td align="center" colspan=5>
								${p.hjwh}
							</td>
							<td align="center" colspan=5>
								${p.xmmc }
							</td>
							<td align="center" colspan=4>
								${p.xmlxmc }
							</td>
						</tr>
					</logic:iterate>
					
					<%--<logic:iterate id="r" name="rychList" offset="0">
						<tr height="28px">
							<td align="center">
								${r.rychhdsj }
							</td>
							<td align="center" colspan=5>
								
							</td>
							<td align="center" colspan=5>
								${r.rychmc }
							</td>
							<td align="center" colspan=5>
								${r.bz }
							</td>
						</tr>
					</logic:iterate>--%>
					
					<logic:iterate id="w" name="wjcfList" offset="0">
						<tr height="28px">
							<td align="center">
								${w.cfsj }
							</td>
							<td align="center" colspan=5>
								${w.cfwh }
							</td>
							<td align="center" colspan=5>
								${w.cflbmc }
							</td>
							<td align="center" colspan=4>
								${w.cfyymc }
							</td>
						</tr>
					</logic:iterate>
					<logic:iterate id="z" name="jcwjcfList" offset="0">
						<tr height="28px">
							<td align="center">
								${z.jcsj }
							</td>
							<td align="center" colspan=5>
								${z.jcwh }
							</td>
							<td align="center" colspan=5>
								${z.cflbmc }
							</td>
							<td align="center" colspan=4>
								${z.jcyj }
							</td>
						</tr>
					</logic:iterate>
					<logic:present name="xszzList">
						<logic:iterate id="x" name="xszzList" offset="0">
							<tr height="28px">
								<td align="center">
									${x.sqsj }
								</td>
								<td align="center" colspan=5>
								
								</td>
								<td align="center" colspan=5>
									${x.xmmc }
								</td>
								<td align="center" colspan=4>
									${x.lbmc }
								</td>
							</tr>
						</logic:iterate>
					</logic:present>
					<!-- ������  begin-->
					<%
						List<HashMap<String, String>> pjpyList = (List<HashMap<String, String>>) request.getAttribute("pjpyList");
						//List<HashMap<String, String>> rychList = (List<HashMap<String, String>>) request.getAttribute("rychList");
						List<HashMap<String, String>> wjcfList = (List<HashMap<String, String>>) request.getAttribute("wjcfList");
						List<HashMap<String, String>> jcwjcfList = (List<HashMap<String, String>>) request.getAttribute("jcwjcfList");

						List<HashMap<String, String>> xszzList = (List<HashMap<String, String>>) request.getAttribute("xszzList");
					
						int p = null != pjpyList ? pjpyList.size() : 0;
						//int r = null != rychList ? rychList.size() : 0;
						int w = null != wjcfList ? wjcfList.size() : 0;
						int x = null != xszzList ? xszzList.size() : 0;
						int z = null != jcwjcfList ? jcwjcfList.size() :0;
						int m = 0;
						//int jcRows = 5 - (p+r+w+x+z);

						int jcRows = 5 - (p+w+x+z);
						
						while (jcRows > m){
					 %>
					<tr height="28px">
								<td align="center">
									&nbsp;
								</td>
								<td align="center" colspan=5>
								
								</td>
								<td align="center" colspan=5>
								</td>
								<td align="center" colspan=4>
								</td>
							</tr>
					
					<%
						m++;
						}
					 %>
					 <!-- ������  end-->
				</table>
				<br/>
				<p style=''>
					<b><span style='font-size: 12.0pt;  font-family: ����'><bean:message key="lable.xb" />��ǩ�£�</span>
					</b><span style='font-size: 12.0pt;font-family: ����'>${xxmc }</span><span 
						style='font-size: 9.0pt; '>&nbsp; </span><span 
						style='font-size: 12.0pt; '>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><b><span
						style='font-size: 12.0pt;  font-family: ����'>���</span> </b><b><span
						style='font-size: 12.0pt; '> </span> </b><font-size: 12.0pt;span style='font-family: ����'>${shr
						}</span><span  style='font-size: 12.0pt; '>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</span><span  style='font-size: 12.0pt;  font-family: ����'>&nbsp;</span><b><span
						style='font-size: 12.0pt;  font-family: ����'>����</span> </b>
						<span>
						<script language=Javascript> 
						var now=new Date();
						document.write("<span  style='font-size: 12.0pt; '>"+now.getFullYear()+"��"+(now.getMonth()+1)+"��"+now.getDate()+"��</span>") 
						</script>
						</span>
				</p>
				</center>
			</div>

			<logic:notPresent name="flg">
				<div align="right" class='noPrin'>
					<button type="button" class='button2'
						onclick="printPdk();">
						�� ӡ
					</button>
					<logic:notEqual value="stu" name="userType">
						<%--<button type="button" class='button2'
							onclick="document.location='moralCardIndex.do?doType=query'">
							�� ��
						</button>
					--%>
					<button type="button"  name="�ر�" onclick="Close()" id="buttonClose">�� ��</button>
					</logic:notEqual>
				</div>
			</logic:notPresent>
			<logic:present name="flg">
				<div align="center" class='noPrin'>
					<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
						ҳ������
					</button>
					<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
						��ӡԤ��
					</button>
					<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;"
						id="printButton">
						ֱ�Ӵ�ӡ
					</button>
				</div>
			</logic:present>
			</div>
		</html:form>
	</body>

</html>
