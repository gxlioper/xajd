<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- ͷ�ļ� -->
<html:html>
<script type='text/javascript' src='/xgxt/dwr/interface/dealDate.js'></script>
<body>
	<!-- ��ӡ�ؼ�begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
	<!-- end -->
	<html:form action="/typj" method="post">
		<table width="95%" border="0" id="theTable" align="center">
			<tr>
				<td align="left">
					<h3>
						514
					</h3>
				</td>
			</tr>
			<tr>
				<td align="center">
					<h2>
						����ѧԺУ����ѧ�������������V1
					</h2>
				</td>
			</tr>
			<tr>
				<td>����Ժ��ϵ����${rs.xymc }</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="15%"></td>
							<td width="6%"></td>
							<td width="10%"></td>
							<td width="7%"></td>
							<td width="15%"></td>
							<td width="10%"></td>
							<td width="10%"></td>
							<td width="7%"></td>
							<td width="20%"></td>
						</tr>
						<tr height="35px">
							<td align="center">���������</td>
							<td align="center" colspan="2">${rs.xm }</td>
							<td align="center">��<br/>��</td>
							<td align="center">${rs.xb }</td>
							<td align="center" colspan="2">��������</td>
							<td align="center" colspan="2">
								<input type="hidden" id="csrq" value="${rs.csrq }">
								<script>
									var csrq = $('csrq').value;
									
									if (csrq.length==10){
										var temp = csrq.split('-');
										document.write(temp[0]+"&nbsp;��&nbsp;"+temp[1]+"&nbsp;��&nbsp;"+temp[2]+"&nbsp;��&nbsp;");
									} else if (csrq.length==8){
										document.write(csrq.substring(0,4)+"&nbsp;��&nbsp;"+csrq.substring(4,6)+"&nbsp;��&nbsp;"+csrq.substring(6,8)+"&nbsp;��&nbsp;");
									} else {
										document.write("��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��");
									}
								</script>
							</td>
						</tr>
						<tr height="35px">
							<td align="center">רҵ�꼶</td>
							<td align="center"colspan="2">${rs.zymc }&nbsp;&nbsp;&nbsp;${rs.nj }</td>
							<td align="center">ѧ<br/>��</td>
							<td align="center">${rs.xh }</td>
							<td align="center" colspan="2">��ϵ�绰</td>
							<td align="center" colspan="2">${rs.lxdh }</td>
						</tr>
						<tr height="35px">
							<td align="center">��ͥסַ</td>
							<td align="center"colspan="6">${rs.jtdz }</td>
							<td align="center">�ʱ�</td>
							<td align="center">${rs.jtyb }</td>
						</tr>
						<tr height="35px">
							<td align="center">���֤����</td>
							<td align="center" colspan="8">${rs.sfzh }</td>
						</tr>
						<%
							String fqxm = "";
							String fqdw = "";
							String fqdh = "";
							String mqxm = "";
							String mqdw = "";
							String mqdh = "";
							List<HashMap<String, String>> cyList = (List<HashMap<String, String>>)request.getAttribute("cyList");
							for(HashMap<String, String> map : cyList){
								if ("����".equals(map.get("mc"))){
									fqxm = null==map.get("cyxm")?"":map.get("cyxm");
									fqdw = null==map.get("cygzdw")?"":map.get("cygzdw");
									fqdh = null==map.get("cydh")?"":map.get("cydh");
								} else if ("ĸ��".equals(map.get("mc"))){
									mqxm = null==map.get("cyxm")?"":map.get("cyxm");
									mqdw = null==map.get("cygzdw")?"":map.get("cygzdw");
									mqdh = null==map.get("cydh")?"":map.get("cydh");
								}
							}
						 %>
						<tr height="35px">
							<td align="center">��������</td>
							<td align="center" colspan="2"><%=fqxm%></td>
							<td align="center" colspan="2">���֤����</td>
							<td align="center" colspan="4"></td>
						</tr>
						<tr height="35px">
							<td align="center">ĸ������</td>
							<td align="center" colspan="2"><%=mqxm%></td>
							<td align="center" colspan="2">���֤����</td>
							<td align="center" colspan="4"></td>
						</tr>
						<tr height="35px">
							<td align="center">���׹�����λ</td>
							<td align="center" colspan="5"><%=fqdw%></td>
							<td align="center" colspan="2">��ϵ�绰</td>
							<td align="center"><%=fqdh%></td>
						</tr>
						<tr height="35px">
							<td align="center">ĸ�׹�����λ</td>
							<td align="center"colspan="5"><%=mqdw%></td>
							<td align="center" colspan="2">��ϵ�绰</td>
							<td align="center"><%=mqdh%></td>
						</tr>
						<tr height="40px">
							<td align="center">��������</td>
							<td colspan="8">
								<logic:equal value="4" name="rs" property="xz">
									������<u>&nbsp;&nbsp;&nbsp;&nbsp;${rs.xmzzjb }&nbsp;&nbsp;&nbsp;&nbsp;</u>����
									�����<u>&nbsp;&nbsp;&nbsp;&nbsp;${rs.xmzzje }&nbsp;&nbsp;&nbsp;&nbsp;</u>Ԫ<br/>
								</logic:equal>
								<logic:equal value="3" name="rs" property="xz">
									ר����<u>&nbsp;&nbsp;&nbsp;&nbsp;${rs.xmzzjb }&nbsp;&nbsp;&nbsp;&nbsp;</u>����
									�����<u>&nbsp;&nbsp;&nbsp;&nbsp;${rs.xmzzje }&nbsp;&nbsp;&nbsp;&nbsp;</u>Ԫ
								</logic:equal>
								
							</td>
						</tr>
						<tr height="35px">
							<td align="center">�������</td>
							<td colspan="8">
								<input type="hidden" id="kssj" value="${rs.jkkssj}">
								<input type="hidden" id="jssj" value="${rs.jkjssj}">
							
								�ܹ�<u>&nbsp;&nbsp;
									<script>
										dwr.engine.setAsync(false);
										dealDate.getMonths($('kssj').value,$('jssj').value,function(data){
											document.write(data);
										});
										dwr.engine.setAsync(true);
									</script>
								&nbsp;&nbsp;</u>����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								��
								<script>
									var kssj = $('kssj').value;
									
									if (kssj.length==10){
										var temp = kssj.split('-');
										document.write("<u>&nbsp;&nbsp;"+temp[0]+"&nbsp;&nbsp;</u>&nbsp;��&nbsp;<u>&nbsp;&nbsp;"+temp[1]+"&nbsp;&nbsp;</u>&nbsp;��&nbsp;");
									} else if (kssj.length==8){
										document.write("<u>&nbsp;&nbsp;"+kssj.substring(0,4)+"&nbsp;&nbsp;</u>&nbsp;��&nbsp;<u>&nbsp;&nbsp;"+kssj.substring(4,6)+"&nbsp;&nbsp;</u>&nbsp;��&nbsp;");
									} else {
										document.write("<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>��&nbsp;&nbsp;&nbsp;<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>��&nbsp;&nbsp;&nbsp;");
									}
								</script>
								 ��
								 <script>
									var jssj = $('jssj').value;
									
									if (jssj.length==10){
										var temp = jssj.split('-');
										document.write("<u>&nbsp;&nbsp;"+temp[0]+"&nbsp;&nbsp;</u>&nbsp;��&nbsp;<u>&nbsp;&nbsp;"+temp[1]+"&nbsp;&nbsp;</u>&nbsp;��&nbsp;");
									} else if (jssj.length==8){
										document.write("<u>&nbsp;&nbsp;"+jssj.substring(0,4)+"&nbsp;&nbsp;</u>&nbsp;��&nbsp;<u>&nbsp;&nbsp;"+jssj.substring(4,6)+"&nbsp;&nbsp;</u>&nbsp;��&nbsp;");
									} else {
										document.write("<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>��&nbsp;&nbsp;&nbsp;<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>��&nbsp;&nbsp;&nbsp;");
									}
								</script>
							</td>
						</tr>
						<tr>
							<td colspan="9">
								<p style="height:60px">
									&nbsp;&nbsp;&nbsp;&nbsp;���˱�֤������д������ʵ���󣬲������Ͽɡ�
								</p>
								<div align="right">
									��������ˣ�ǩ�£���
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="9">
								<p style="height:60px">
									&nbsp;&nbsp;&nbsp;&nbsp;��������˱�������������ʵ���ش�֤����
								</p>
								<div align="right">
									����Ա��ǩ�£���
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td align="center">ϵ��Ժ���쵼<br/>���</td>
							<td colspan="8">
								<p style="height:60px">
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyyj }
								</p>
								<div align="right">
									ϵ��Ժ���쵼��ǩ�£���
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td align="center">���������������</td>
							<td colspan="8">
								<p style="height:60px">
									&nbsp;&nbsp;&nbsp;&nbsp;
								</p>
								<div align="right">
									������������ǩ�£���
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td align="center">��&nbsp;&nbsp;&nbsp;&nbsp;ע</td>
							<td colspan="8">
								<p style="height:50px">
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.bz }
								</p>
							</td>
						</tr>
					</table>
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
