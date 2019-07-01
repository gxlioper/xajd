<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/tttzxm/comm/js/comm.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/tttzxm/sh/js/ttxmsh.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
	
			jQuery(function(){
				calculateRs();
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${form.ttsqid}&tt="+new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${form.splc}&shid=${form.shid}");
			});
		</script>
		<style type = "text/css">
			#xxdz{width:250px;}
			#autotable table th{text-align:left} 
			#autotable table td{text-align:left} 
		</style>
	</head>
	<body>
		<html:form action="/ttxm_sh" method="post" styleId="TttzxmShForm">
			<html:hidden  name ="form" property="ttsqid" styleId="ttsqid"/>
			<html:hidden  name ="form" property="dzxh" styleId="dzxh"/>
			<html:hidden  name ="form" property="xmdm" styleId="xmdm"/>		
			<html:hidden  name ="form" property="splc" styleId="splc"/>
			<html:hidden  name ="form" property="sqsj" styleId="sqsj"/>
			<html:hidden  name ="form" property="shid" styleId="shid"/>
			<html:hidden  name ="form" property="shzt" styleId="shzt"/>
		    <input type="hidden" id="usertype" value="${usertype}"/>
			<logic:equal name="usertype" value="stu" >
				<input type="hidden" name="dzxh" id="dzxh" value="${xsmap.xh}"/>
			</logic:equal>
			<logic:notEqual name="usertype" value="stu" >
				<input type="hidden" id="dzxh" name="dzxh"/>
			</logic:notEqual>
			<input type="hidden" name="splc" id="splc"/>
			<input type="hidden" name="xmdm" id="xmdm"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ŀ��Ϣ</span>
                               <a  style="text-align: left;" onclick="showPfzmx(this,'orign');" class="up"
									href="javascript:void(0);"> <font color="blue">���չ��/����</font>
								</a>
							</th>
						</tr>
					</thead>
					<tbody id="autotbody">
						<tr>
							<th width="20%">��Ŀ����</th>
							<td width="30%">
								${xmxxmap.xmmc}
							</td>
							<th width="20%">��Ŀ����</th>
							<td width="30%" id="xmjbmc" >
							  	${xmxxmap.xmjbmc}
                             </td>
						</tr>
						<tr>
							<th>ѧ��</th>
							<td id="xn" >
								${xmxxmap.xn}
							</td>
							<th>ѧ��</th>
							<td id="xq" >
							    ${xmxxmap.xqmc}
							</td>
						</tr>
						<tr>
							<th>�걨����</th>
							<td id="sbbmmc" >
								${xmxxmap.bmmc}
							</td>
							<th>��ϵ��ʽ</th>
							<td id="lxdh" name="lxdh">
								${xmxxmap.lxdh}
							</td>
						</tr>
						<tr>
							<th>������Ŀ</th>
							<td id="sskmmc" name="sskmmc">
								${xmxxmap.sskmmc}
							</td>
							<th>����ѧ��</th>
							<td id="jcxf" name="jcxf">
								${xmxxmap.jcxf}
							</td>
						</tr>
						<tr>
							<th>�ɲ����Ŷ���</th>
							<td id="kcyrs" name="kcyrs">
								${xmxxmap.kcyrs}
							</td>
							<th>�������Ŷ���</th>
							<td id="sqrs" name="sqrs">
								${xmxxmap.sqrs}
							</td>
						</tr>
						<tr>
							<th>��ͨ���Ŷ���</th>
							<td id="tgrs" name="tgrs">
								${xmxxmap.tgrs}
							</td>
							<th>���ʼʱ��</th>
							<td id="xmkssj" name="xmkssj">
								${xmxxmap.xmkssj}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ա��Ϣ </span> 
									<label  id="cyrs" style="margin-left: 15px;margin-top: 4px;display: block;float: left;"></label>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr colspan="4">
							<td width="100%" colspan="4">
								<div width="100%" id="autotable">
								<table width="100%" id="tablebody">
									<tr>
										<th width="10%">��ɫ</th>
									    <th width="20%">ѧ��</th>
									    <th width="20%">����</th>
									    <th width="20%">ѧԺ</th>
									    <th width="30%">�༶</th>
									</tr>
									<tr>
										<td>�ӳ�</td>
									    <td width="20%">${dzxxmap.xh}</td>
									    <td width="20%">${dzxxmap.xm}</td>
									    <td width="20%">${dzxxmap.xymc}</td>
									    <td width="20%">${dzxxmap.bjmc}</td>
									</tr>
									<logic:iterate id="i" name="dyzzlist">
										<tr>
										    <td>��Ա</td>
										    <td >${i.xh}</td>
										    <td >${i.xm}</td>
										    <td >${i.xymc}</td>
										    <td >${i.bjmc}</td>
										</tr>
									</logic:iterate>
								</table>
							</div>
							</td>
							
						</tr>
						<tr>
							
						</tr>
						<tr>
							<th width="20%">�Ŷ�����</th>
							<td colspan="3">
								${TttzxmShForm.tdmc}
							</td>
						</tr>
						<tr>
							<th width="20%">��������</th>
							<td colspan="3">
								${TttzxmShForm.sqly}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4" id="shlccx">
							
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th >
								��˽��
							</th>
							<td id="shjgSpan">
								
							</td>
						</tr>
						
						<tr>
							<th width="20%">
								<font color="red">*&nbsp;</font> ������
								<br />
								<font color="red">(��200��)</font>
							</th>
							<td colspan="3">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=sztzttxm&id=shyj" />
								<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
							</td>
						</tr>
					</tbody>	
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="����"  onclick="saveSh();return false;">
									         �� ��
								    </button>
									<button type="button" onclick="iFClose();">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
	
</html>