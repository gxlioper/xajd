<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- 头文件 -->
<html:html>
<script type='text/javascript' src='/xgxt/dwr/interface/dealDate.js'></script>
<body>
	<!-- 打印控件begin -->
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
						闽江学院校内助学借款申请审批表V1
					</h2>
				</td>
			</tr>
			<tr>
				<td>所在院（系）：${rs.xymc }</td>
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
							<td align="center">借款人姓名</td>
							<td align="center" colspan="2">${rs.xm }</td>
							<td align="center">性<br/>别</td>
							<td align="center">${rs.xb }</td>
							<td align="center" colspan="2">出生日期</td>
							<td align="center" colspan="2">
								<input type="hidden" id="csrq" value="${rs.csrq }">
								<script>
									var csrq = $('csrq').value;
									
									if (csrq.length==10){
										var temp = csrq.split('-');
										document.write(temp[0]+"&nbsp;年&nbsp;"+temp[1]+"&nbsp;月&nbsp;"+temp[2]+"&nbsp;日&nbsp;");
									} else if (csrq.length==8){
										document.write(csrq.substring(0,4)+"&nbsp;年&nbsp;"+csrq.substring(4,6)+"&nbsp;月&nbsp;"+csrq.substring(6,8)+"&nbsp;日&nbsp;");
									} else {
										document.write("年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日");
									}
								</script>
							</td>
						</tr>
						<tr height="35px">
							<td align="center">专业年级</td>
							<td align="center"colspan="2">${rs.zymc }&nbsp;&nbsp;&nbsp;${rs.nj }</td>
							<td align="center">学<br/>号</td>
							<td align="center">${rs.xh }</td>
							<td align="center" colspan="2">联系电话</td>
							<td align="center" colspan="2">${rs.lxdh }</td>
						</tr>
						<tr height="35px">
							<td align="center">家庭住址</td>
							<td align="center"colspan="6">${rs.jtdz }</td>
							<td align="center">邮编</td>
							<td align="center">${rs.jtyb }</td>
						</tr>
						<tr height="35px">
							<td align="center">身份证号码</td>
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
								if ("父亲".equals(map.get("mc"))){
									fqxm = null==map.get("cyxm")?"":map.get("cyxm");
									fqdw = null==map.get("cygzdw")?"":map.get("cygzdw");
									fqdh = null==map.get("cydh")?"":map.get("cydh");
								} else if ("母亲".equals(map.get("mc"))){
									mqxm = null==map.get("cyxm")?"":map.get("cyxm");
									mqdw = null==map.get("cygzdw")?"":map.get("cygzdw");
									mqdh = null==map.get("cydh")?"":map.get("cydh");
								}
							}
						 %>
						<tr height="35px">
							<td align="center">父亲姓名</td>
							<td align="center" colspan="2"><%=fqxm%></td>
							<td align="center" colspan="2">身份证号码</td>
							<td align="center" colspan="4"></td>
						</tr>
						<tr height="35px">
							<td align="center">母亲姓名</td>
							<td align="center" colspan="2"><%=mqxm%></td>
							<td align="center" colspan="2">身份证号码</td>
							<td align="center" colspan="4"></td>
						</tr>
						<tr height="35px">
							<td align="center">父亲工作单位</td>
							<td align="center" colspan="5"><%=fqdw%></td>
							<td align="center" colspan="2">联系电话</td>
							<td align="center"><%=fqdh%></td>
						</tr>
						<tr height="35px">
							<td align="center">母亲工作单位</td>
							<td align="center"colspan="5"><%=mqdw%></td>
							<td align="center" colspan="2">联系电话</td>
							<td align="center"><%=mqdh%></td>
						</tr>
						<tr height="40px">
							<td align="center">申请借款金额</td>
							<td colspan="8">
								<logic:equal value="4" name="rs" property="xz">
									本科生<u>&nbsp;&nbsp;&nbsp;&nbsp;${rs.xmzzjb }&nbsp;&nbsp;&nbsp;&nbsp;</u>级借款，
									借款金额<u>&nbsp;&nbsp;&nbsp;&nbsp;${rs.xmzzje }&nbsp;&nbsp;&nbsp;&nbsp;</u>元<br/>
								</logic:equal>
								<logic:equal value="3" name="rs" property="xz">
									专科生<u>&nbsp;&nbsp;&nbsp;&nbsp;${rs.xmzzjb }&nbsp;&nbsp;&nbsp;&nbsp;</u>级借款，
									借款金额<u>&nbsp;&nbsp;&nbsp;&nbsp;${rs.xmzzje }&nbsp;&nbsp;&nbsp;&nbsp;</u>元
								</logic:equal>
								
							</td>
						</tr>
						<tr height="35px">
							<td align="center">借款期限</td>
							<td colspan="8">
								<input type="hidden" id="kssj" value="${rs.jkkssj}">
								<input type="hidden" id="jssj" value="${rs.jkjssj}">
							
								总共<u>&nbsp;&nbsp;
									<script>
										dwr.engine.setAsync(false);
										dealDate.getMonths($('kssj').value,$('jssj').value,function(data){
											document.write(data);
										});
										dwr.engine.setAsync(true);
									</script>
								&nbsp;&nbsp;</u>个月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								自
								<script>
									var kssj = $('kssj').value;
									
									if (kssj.length==10){
										var temp = kssj.split('-');
										document.write("<u>&nbsp;&nbsp;"+temp[0]+"&nbsp;&nbsp;</u>&nbsp;年&nbsp;<u>&nbsp;&nbsp;"+temp[1]+"&nbsp;&nbsp;</u>&nbsp;月&nbsp;");
									} else if (kssj.length==8){
										document.write("<u>&nbsp;&nbsp;"+kssj.substring(0,4)+"&nbsp;&nbsp;</u>&nbsp;年&nbsp;<u>&nbsp;&nbsp;"+kssj.substring(4,6)+"&nbsp;&nbsp;</u>&nbsp;月&nbsp;");
									} else {
										document.write("<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>年&nbsp;&nbsp;&nbsp;<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>月&nbsp;&nbsp;&nbsp;");
									}
								</script>
								 至
								 <script>
									var jssj = $('jssj').value;
									
									if (jssj.length==10){
										var temp = jssj.split('-');
										document.write("<u>&nbsp;&nbsp;"+temp[0]+"&nbsp;&nbsp;</u>&nbsp;年&nbsp;<u>&nbsp;&nbsp;"+temp[1]+"&nbsp;&nbsp;</u>&nbsp;月&nbsp;");
									} else if (jssj.length==8){
										document.write("<u>&nbsp;&nbsp;"+jssj.substring(0,4)+"&nbsp;&nbsp;</u>&nbsp;年&nbsp;<u>&nbsp;&nbsp;"+jssj.substring(4,6)+"&nbsp;&nbsp;</u>&nbsp;月&nbsp;");
									} else {
										document.write("<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>年&nbsp;&nbsp;&nbsp;<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>月&nbsp;&nbsp;&nbsp;");
									}
								</script>
							</td>
						</tr>
						<tr>
							<td colspan="9">
								<p style="height:60px">
									&nbsp;&nbsp;&nbsp;&nbsp;本人保证以上填写内容真实无误，并予以认可。
								</p>
								<div align="right">
									借款申请人（签章）：
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="9">
								<p style="height:60px">
									&nbsp;&nbsp;&nbsp;&nbsp;借款申请人表内所填资料属实，特此证明。
								</p>
								<div align="right">
									辅导员（签章）：
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td align="center">系（院）领导<br/>意见</td>
							<td colspan="8">
								<p style="height:60px">
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyyj }
								</p>
								<div align="right">
									系（院）领导（签章）：
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td align="center">资助管理中心意见</td>
							<td colspan="8">
								<p style="height:60px">
									&nbsp;&nbsp;&nbsp;&nbsp;
								</p>
								<div align="right">
									部（处）长（签章）：
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td align="center">备&nbsp;&nbsp;&nbsp;&nbsp;注</td>
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
			页面设置
		</button>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
			打印预览
		</button>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
			直接打印
		</button>
	</div>
</body>
</html:html>
