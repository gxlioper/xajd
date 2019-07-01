<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript">
			function showTbody(obj,objTbody){
				if(obj.className=="up"){
					obj.className="down";
					obj.parentNode.parentNode.className="cur-tr";
					document.getElementById(objTbody).style.display="none";
				}else{
					obj.className="up";
					obj.parentNode.parentNode.className="";
					document.getElementById(objTbody).style.display="";
				}
			}
			function deploy(id){
				var d = (document.getElementById("xswjbx_"+id).style.display == 'none') ? '' : 'none';
				document.getElementById("xswjbx_"+id).style.display = d; 
				document.getElementById("zxfk_"+id).style.display = d; 
			}
			jQuery(function(){
				if("1"=="${rs.ywzyls}"){
					jQuery("#zyqk_tr").show();
				}else{
					jQuery("#zyqk_tr").hide();
				}
			})
		</script>
	</head>
	<body >
		<html:form action="/xlzx_xlwjga_xlwjgasbgl" method="post" styleId="xlwjgasbForm" onsubmit="return false;">
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:465px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/xszbb/comm/viewStudent.jsp" %>
					<logic:notEmpty name="rs" property="zxfk">
						<thead>
							<tr>
								<th colspan="4">
									<span>���ķ������</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									����ѧԺ�ο�����
								</th>
								<td colspan="3">
									${rs.zxfk }
								</td>
							</tr>
						</tbody>
					</logic:notEmpty>
					<thead>
						<tr>
							<th colspan="4">
								<span>������ϸ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								�ϱ���
							</th>
							<td>
								${userNameReal }
							</td>
							<th>
								��ϵ�绰
							</th>
							<td>
								${rs.sbrlxfs }
							</td>
						</tr>
						<tr>
							<th>
								Σ���̶�
							</th>
							<td colspan="3">
								${rs.wjcdmc }
							</td>
						</tr>
						<tr>
						    <th>
								ѧ��Σ������
							</th>
							<td colspan="3">
								${rs.xswjbx }
							</td>
						</tr>
						<tr>
						    <th>
								ѧԺ�������
							</th>
							<td colspan="3">
								${rs.xyclgc }
							</td>
						</tr>
						<tr>
							<th>
								����סԺ��ʷ
							</th>
							<td colspan="3">
								${rs.ywzylsmc }
							</td>
						</tr>
						<tr id="zyqk_tr">
							
						    <th>
								סԺ���
							</th>
							<td colspan="3">
								${rs.zyqk }
							</td>
						</tr>
						<tr>
						    <th>
								ѧ��Ŀǰ״��
							</th>
							<td colspan="3">
								${rs.xszk }
							</td>
						</tr>
					</tbody>
				</table>
				<table width="100%"  border="0" class="formlist">
				    <thead>
				      <tr>
				      	<th colspan="2">
				      	  <a href="#" class="down" onclick="showTbody(this,'myTbody');return false">��ʷ��¼</a>
				   	    </th>
				      </tr>
				    </thead>
				</table>
				<div class="regform" style="padding-top:0px;">
					<div id="myTbody" style="padding-bottom:10px;">
						<logic:notEmpty name="rsList">
							<table width="100%" border="0" class="formlist">
							  <tbody>
								<logic:iterate name="rsList" id="rsMap" indexId="index">
									<tr onclick="deploy('${index}');return false" title="[չ��/����]">
										<th width="12%">Σ���̶�</th>
										<td width="17%">${rsMap.wjcdmc}</td>
										<th width="12%">Σ������״̬</th>
										<td width="17%">${rsMap.wjgabzmc}</td>
										<th width="12%">�ϱ�ʱ��</th>
										<td width="30%">${rsMap.sbsj}<span style="float: right;"><a href="#" class="up">չ��/����</a></span></td>
									</tr>
									<tr id="xswjbx_${index}" style="display:none" height="50">
										<th width="12%" align="right" >ѧ��Σ������</th>
										<td colspan="5" align="left">${rsMap.xswjbx}</td>
									</tr>
									<tr id="zxfk_${index}" style="display:none" height="50">
										<th width="12%" align="right" >���ķ������</th>
										<td colspan="5" align="left">${rsMap.zxfk}</td>
									</tr>
									<tr style="height:5px"></tr>
								</logic:iterate>
							  </tbody>
							</table>
						</logic:notEmpty>
						<logic:empty name="rsList">
							<span style="color:green; font-weight:bold; font-size:12px;padding-left:10px;">û�и����¼</span>			
						</logic:empty>
					</div>
				</div>
			</div>
		
			<div>
				<table class="formlist">
					<tfoot>
						<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" type="button" onclick="iFClose();return false;">
									�� ��
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

