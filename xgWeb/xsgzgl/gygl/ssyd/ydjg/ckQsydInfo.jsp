<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
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
	<body>
		<html:form method="post" styleId="form" action="/ydjg">
		<div style='tab;width:100%;height:100%;overflow-x:hidden;overflow-y:auto;'>
		<div style='tab;width:100%;height:300px;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
				<thead>
						<tr>
							<th colspan="4">
								<span>��λ��Ϣ</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="10%">
							¥������
						</th>
						<td align="left">
							${cwxxData.ldmc}
						</td>
						<th align="right">
							���Һ�
						</th>
						<td align="left">
							${cwxxData.qsh}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							��λ��
						</th>
						<td align="left">
							${cwxxData.cwh}
						</td>
						<th align="right">
							���ҵ绰
						</th>
						<td align="left">
							${cwxxData.qsdh}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							�շѱ�׼
						</th>
						<td align="left">
							${cwxxData.sfbz}
						</td>
						<th align="right">
							�����꼶
						</th>
						<td align="left">
							${cwxxData.nj}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							����<bean:message key="lable.xb" />
						</th>
						<td align="left">
							${cwxxData.xymc}
						</td>
						<th align="right">
							�����༶
						</th>
						<td align="left">
							${cwxxData.bjmc}
						</td>
					</tr>
				</tbody>
				<thead>
						<tr>
							<th colspan="4">
								<span>�����춯��Ϣ</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="4">
							<logic:notEmpty name="currQsYdList">
								<table width="100%" border="0" class="formlist">
									<thead>
										<th style="text-align: left;">ѧ��ѧ��</th>
										<th style="text-align: left;">ѧ��</th>
										<th style="text-align: left;">����</th>
										<th style="text-align: left;">�Ա�</th>
										<th style="text-align: left;">�꼶</th>
										<th style="text-align: left;"><bean:message key="lable.xb" /></th>
										<th style="text-align: left;">רҵ</th>
										<th style="text-align: left;">�༶</th>
										<th style="text-align: left;">��סʱ��</th>
										<th style="text-align: left;">����ʱ��</th>
									</thead>
									<tbody>
										<logic:iterate id="s" name="currQsYdList">
										  <tr>
											<td><bean:write name="s" property="xn"/>&nbsp;<bean:write name="s" property="xqmc"/></td>
											<td><bean:write name="s" property="xh"/></td>
											<td><bean:write name="s" property="xm"/></td>
											<td><bean:write name="s" property="xb"/></td>
											<td><bean:write name="s" property="nj"/></td>
											<td><bean:write name="s" property="xymc"/></td>
											<td><bean:write name="s" property="zymc"/></td>
											<td><bean:write name="s" property="bjmc"/></td>
											<td><bean:write name="s" property="ydqqsrzsj"/></td>
											<td><bean:write name="s" property="tstzsj"/></td>
										  </tr>
										</logic:iterate>
									</tbody>
								</table>
							</logic:notEmpty>
							<logic:empty name="currQsYdList">
								<span style="color:green; font-weight:bold; font-size:12px;padding-left:10px;"> ��ǰѧ��û�������춯��Ϣ��</span>			
							</logic:empty>
						</td>
					</tr>
				</tbody>
			</table>
			
			<table width="100%"  border="0" class="formlist">
			    <thead>
			      <tr>
			      	<th colspan="2">
			      	  <a href="#" class="down" onclick="showTbody(this,'myTbody');return false">���������춯��Ϣ</a>
			   	    </th>
			      </tr>
			    </thead>
			</table>
			
			<div class="regform" style="padding-top:0px;">
				<div id="myTbody" style="padding-bottom:10px;">
					<logic:notEmpty name="notCurrQsYdList">
						<table width="100%" border="0" class="formlist">
							<thead>
								<th>ѧ��ѧ��</th>
								<th>ѧ��</th>
								<th>����</th>
								<th>�Ա�</th>
								<th>�꼶</th>
								<th><bean:message key="lable.xb" /></th>
								<th>רҵ</th>
								<th>�༶</th>
								<th>��סʱ��</th>
								<th>����ʱ��</th>
							</thead>
							<tbody>
								<logic:iterate id="s" name="notCurrQsYdList">
									<tr>
									<td><bean:write name="s" property="xn"/>&nbsp;<bean:write name="s" property="xqmc"/></td>
									<td><bean:write name="s" property="xh"/></td>
									<td><bean:write name="s" property="xm"/></td>
									<td><bean:write name="s" property="xb"/></td>
									<td><bean:write name="s" property="nj"/></td>
									<td><bean:write name="s" property="xymc"/></td>
									<td><bean:write name="s" property="zymc"/></td>
									<td><bean:write name="s" property="bjmc"/></td>
									<td><bean:write name="s" property="ydqqsrzsj"/></td>
									<td><bean:write name="s" property="tstzsj"/></td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</logic:notEmpty>
					<logic:empty name="notCurrQsYdList">
						<span style="color:green; font-weight:bold; font-size:12px;padding-left:10px;"> û�и��������춯��Ϣ��</span>			
					</logic:empty>
				</div>
			</div>				
		</div>
		</div>
		<div>
			<table width="100%" border="0" class="formlist">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button"  onclick="iFClose();" id="buttonClose">
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
