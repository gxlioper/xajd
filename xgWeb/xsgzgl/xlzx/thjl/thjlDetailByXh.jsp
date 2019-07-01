<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<style type="text/css">	
			.demo_data2 {
			   /* border: 1px solid #DEDEDE;*/
				display: inline;
			    float: left;
			    height: 15px;
			    margin: 0px 0px 0;
			    padding: 0px;
			    width: 80px;
			}
		</style>
		<script language="javascript" src="xsgzgl/xlzx/thjl/js/thjlDetailByXh.js"></script>
		
		<script language="javascript" defer="defer">
		jQuery(function() {
			jQuery("#myTbody").css("display","none");
		});
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
		
		</script>
	</head>
	<body onload="init();">
	<input type="hidden" name="knlxdm" id="knlxdm" value="${thjlInfo.knlxdm}" />
		<html:form action="/xlzx_thjl" method="post">
			<div style='width:100%; overflow-y:auto;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr > 
							<th colspan="4">
								<span>ѧ��������Ϣ</span><div align="right"></div>
							</th>
						</tr>
					</thead>
						<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp" %>
					  <thead>
						<tr >
							<th colspan="4">
								<span≯����¼��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="thjlInfo">
					<tr>
						<th width="16%">
								̸����ʦ
						</th>
						<td width="34%">
							${thjlInfo.jsxm}
						</td>
						<th width="16%">
								ְ����
						</th>
						<td width="34%">
							${thjlInfo.zgh}
							<input type="hidden" id="zgh" name="zgh" value="${thjlInfo.zgh }"  />
						</td>						
					</tr>
					<tr>
						<th width="16%">
									�Ա�
						</th>
						<td width="34%">
							${thjlInfo.jsxb}
						</td>
						<th width="16%">
									����
						</th>
						<td width="34%">
							${thjlInfo.jsbmmc}
						</td>
					</tr>
					<tr>
						<th width="16%">
								̸��ʱ��
						</th>
						<td width="34%" colspan="3">
							${thjlInfo.thsj }
						</td>
					</tr>
					<tr style="height:80px;">
						<th>
							̸����¼<br/>
						</th>
						<td colspan="3">
							${thjlInfo.thnr}
						</td>
					</tr>
					<logic:equal value="11527" name="xxdm">
					<tr>
							<th>
								������Ϣ
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden property="fjid" styleId="fjid"  value="${thjlInfo.fjid}"/>
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										var gid = jQuery('#fjid').val();
										jQuery.MultiUploader_q({
											gid : gid
											});
									});
								</script>
							</td>
						</tr>
						</logic:equal>
					</tbody>
				
				</table>
				<br/>
				<table width="100%"  border="0" class="formlist">
				    <thead>
				      <tr>
				      	<th colspan="2">
				      	  <a href="#" class="down" onclick="showTbody(this,'myTbody');return false">��ʷ̸����¼</a>
				   	    </th>
				      </tr>
				    </thead>
				</table>
				<div class="regform" style="padding-top:0px;">
					<div id="myTbody" style="padding-bottom:10px;">
						<logic:notEmpty name="hisThjlList">
							<table width="100%" border="0" class="formlist">
							  <tbody>
								<logic:iterate name="hisThjlList" id="hisThjl" indexId="index">
									<tr onclick="deploy('${index}');return false" title="[չ��/����]"><th width="16%"≯��ʱ��</th><td width="34%">${hisThjl.thsj}</td><th width="16%"≯����ʦ</th>
										<td><span>${hisThjl.jsxm}</span><span style="float: right;"><a href="#" class="up">չ��/����</a></span></td>
									</tr>
									<tr id="${index}" style="display:none" height="50"><th width="16%" align="right" ≯������</th>
										<td colspan="3" align="left">${hisThjl.thnr}</td>
									</tr>
									<tr style="height:5px"></tr>
								</logic:iterate>
							  </tbody>
							</table>
						</logic:notEmpty>
						<logic:empty name="hisThjlList">
							<span style="color:green; font-weight:bold; font-size:12px;padding-left:10px;"> û�и���̸����¼</span>			
						</logic:empty>
					</div>
				</div>
			</div>
			
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="3">
								<div class="btn">
									<button onclick="Close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>

		</html:form>
	</body>
</html>

