<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/getRcswDAO.js'></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">	
	
	//������Ŀά��
	function saveXmwh(){
		if($("rqnum")){
			var rqnum = $("rqnum").value;
			var flag = false;
			
			if( rqnum != ""){
				for(var i=0;i<rqnum;i++){
					var id = "ffrq"+ i;
					if($(id)){
						if($(id).checked==true){
							flag = true;
						}
					}
				}
			}
		}
		if(flag){
			saveUpdate('/xgxt/zjxyRcsw.do?method=swffXmwhUpdate&doType=save','xn-xq-nd-xmlx-ffsj');
		}else{
			alert("������ѡ��һ��������Ⱥ����ȷ��!!")
			return false;
		}
	}
	
	//������Ŀά�� ֮��ѧԺ
	function saveXm(){
		if($("xmmc").value==""){
			alert("��Ŀ���Ʋ���Ϊ��!");
			return false;
		}
		if($("xn").value==""){
			alert("ѧ�겻��Ϊ��!");
			return false;
		}
		if($("xmlx").value==""){
			alert("��Ŀ���Ͳ���Ϊ��!");
			return false;
		}
		if($("xq").value==""){
			alert("ѧ�ڲ���Ϊ��!");
			return false;
		}
		if($("ffsj").value==""){
			alert("���ſ�ʼʱ�䲻��Ϊ��!");
			return false;
		}
		if($("nd").value==""){
			alert("��Ȳ���Ϊ��!");
			return false;
		}
		if($("ffjssj").value==""){
			alert("���Ž���ʱ�䲻��Ϊ��!");
			return false;
		}
		if($("ffdd").value==""){
			alert("���ŵص㲻��Ϊ��!");
			return false;
		}
		if(checkSjTj("ffsj","����ʼʱ��","ffjssj","�������ʱ��")){
			saveUpdate('/xgxt/zjxyRcsw.do?method=swffXmwhUpdate&doType=save','xn-xq-nd-xmlx-ffsj');
		}else {
			return false;
		}
		
	}
	
	//������Ŀά�� ֮��ѧԺ
	function updateXm(){
		if($("xmmc").value==""){
			alert("��Ŀ���Ʋ���Ϊ��!");
			return false;
		}
		if($("xn").value==""){
			alert("ѧ�겻��Ϊ��!");
			return false;
		}
		if($("xmlx").value==""){
			alert("��Ŀ���Ͳ���Ϊ��!");
			return false;
		}
		if($("xq").value==""){
			alert("ѧ�ڲ���Ϊ��!");
			return false;
		}
		if($("ffsj").value==""){
			alert("���ſ�ʼʱ�䲻��Ϊ��!");
			return false;
		}
		if($("nd").value==""){
			alert("��Ȳ���Ϊ��!");
			return false;
		}
		if($("ffjssj").value==""){
			alert("���Ž���ʱ�䲻��Ϊ��!");
			return false;
		}
		if($("ffdd").value==""){
			alert("���ŵص㲻��Ϊ��!");
			return false;
		}
		if(checkSjTj("ffsj","����ʼʱ��","ffjssj","�������ʱ��")){
			saveUpdate('/xgxt/zjxyRcsw.do?method=swffXmwhUpdate&doType=modi','xn-xq-nd-xmlx-ffsj');
		}else {
			return false;
		}
		
	}
	
	//���÷�����Ⱥ
	function setFfrq(){
		var yffrq = $("yffrq").value;
		var rqnum = $("rqnum").value;
		if(yffrq != ""){
			var ffrq = yffrq.split("!!@@!!");
			for(var i=0;i<rqnum;i++){
				var id = "ffrq"+ i;
				if($(id)){
					for(var j =0;j<ffrq.length;j++){
						if($(id).value == ffrq[j]){
							$(id).checked = true;
						}
					}
				}
			}
		}
	}
	</script>
	</head>

	<body onload="setFfrq()">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/rcswLnny">
			<%@ include file="/rcsw/hiddenValue.jsp"%>
			<input type="hidden" name="rqnum" id="rqnum" value="${rqnum }" />
			<input type="hidden" name="yffrq" id="yffrq" value="${rs.ffrq }" />
			<!--  ��ʱд��,ֻ����ѧ�� -->
			<html:hidden property="save_ffrq" styleId="ffrq" value="�༶" />
			
		
				
				<table width="90%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��һ�չ淶��</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
						<tr>
							<th width="15%">
								ѧ��
							</th>
							<td width="30%">
								${xsxx.xh}
							</td>	
							<th width="15%">
								����
							</th>
							<td width="30%">
								${xsxx.xm}
							</td>
						</tr>
						<tr>	
							<th>
								�Ա�
							</th>
							<td >
								${xsxx.xb}
							</td>	
							<th>
								���֤��
							</th>
							<td >
								${xsxx.sfzh}
							</td>
						</tr>
						<tr>
							<th>
								�꼶
							</th>
							<td>
								${xsxx.nj}
							</td>	
							<th>
								<bean:message key="lable.xb" />
							</th>
							<td >
								${xsxx.xymc}
							</td>
						</tr>
						<tr>
							<th>
								רҵ
							</th>
							<td >
								${xsxx.xymc}
							</td>	
							<th>
								�༶
							</th>
							<td >
								${xsxx.bjmc}
							</td>
						</tr>
					</tbody>
				</table>
			
			<div id="xsqNr" style="width:98%;height: 250px;overflow:scroll;overflow-x:hidden">
			<logic:iterate name="xmlxList" id="xmlx">
				<table width="80%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>${xmlx.xmlxmc } </span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr><td colspan="4">
				<table summary="" class="dateline" width="96%">
						<thead>
							<tr>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="xmxxList" id="xmxx">
						<logic:equal name="xmlx" property="xmlxdm" value="${xmxx.xmlxdm }">
						<tr>
							<td>
								${xmxx.xmmc}
							</td>
							<td>
								${xmxx.xn}
							</td>
							<td>
								${xmxx.xqmc}
							</td>
							<td>
								${xmxx.nd}
							</td>
							<td>
								${xmxx.dfsj}
							</td>
							<td>
								${xmxx.fz}
							</td>
						</tr>	
						</logic:equal>
						</logic:iterate>
				</table>
				</td></tr>
				</tbody>
			</logic:iterate>
			</div>
		</html:form>
	</body>
</html>
