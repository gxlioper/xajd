<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/ystgl/rtgl/rtsq/js/rtsq.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			jQuery("#stxmmc").attr({readonly:"readonly"});
			jQuery("#selxm").unbind('click').bind('click', function(){
			    var xh =  jQuery("a[class='name']").text();
				  var url = "ystglRtsq.do?method=getYstxmList&xh="+xh;
					showDialog('��������Ŀѡ��',770,550,url);
		   });
		});
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/ystglRtsq" method="post" styleId="YstRtsqForm">
		<html:hidden property="rtid"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="ystxm_body">
						<tr>
							<th><font color="red">*</font>��������Ŀ����</th>
							<td>
								<html:text property="ystxmmc" value="${ystxx.ystxmmc}" styleId="ystxmmc" style="width:124px;"/>
								<button class="btn_01" id="selxm" type="button" >ѡ��</button>
									<html:hidden property="ystid" styleId="ystid"/>
							</td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th>���������</th>
							<td name="ystlb">
								${ystxx.ystlbmc}
							</td>
							<th>��Ŀ���</th>
							<td name="xmlb">
								${ystxx.xmlbmc}
							    
							</td>
							
						</tr>
						<tr>
							<th>��ϵ�绰</th>
							<td name="lxdh">
								${ystxx.lxdh }
							</td>
							<th>�ҿ���λ</th>
							<td name="gkdw">
								${ystxx.gkdwmc}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
							   <a style="text-align: left;" onclick="showYstmx(this);" class="down"
									href="javascript:void(0);"> <font color="blue">���չ��/����</font>
								</a>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_toggle" style="display:none">
						<tr>
							<th>���������</th>
							<td name="fzrlb">${ystxx.fzrlb }</td>
							<th>�����Ÿ�����</th>
							<td name="stfzrxm">${ystxx.stfzrxm }</td>
							
						</tr>
						<tr>
							<th>ָ����ʦ</th>
							<td name="zdls">${ystxx.zdlsxm }</td>
							<th>ָ����ʦְ��</th>
							<td name="zcmc">${ystxx.zcmc }</td>
						</tr>
						<tr>
							<th>ָ����ʦ��ϵ��ʽ</th>
							<td name="zdlslxfs">${ystxx.zdlslxfs }</td>
							<th>��������</th>
							<td name="ssbmmc">${ystxx.ssbmmc }</td>
						</tr>
						<tr>
							<th>����ʱ��</th>
							<td name="ystclsj">${ystxx.ystclsj }</td>
							<th>����ʱ��</th>
							<td name="sqsj">${ystxx.sqsj }</td> 
						</tr>
						<tr>
							<th>
								�����ż��
							</th>
							<td colspan="3" name="ystjj">
								${ystxx.ystjj }
							</td>
						</tr>
						<tr>
							<th>
								�����Ż����
							</th>
							<td colspan="3" name="ysthjqk">
								${ystxx.ysthjqk }
							</td>
						</tr>
						
					</tbody>
					<tbody>
						<tr>
							<th><font color="red">*</font>�س�</br><font color="red">(��100��)</font></th>
							<td colspan="3">
								<html:textarea property="tc" styleId="tc" 
								   onkeyup="checkzs(this);" 
								   style="width:99%;" rows="3"></html:textarea>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>��������</br><font color="red">(��100��)</font></th>
							<td colspan="3">
								<html:textarea property="sqly" styleId="sqly" 
								   onkeyup="checkzs(this);" 
								   style="width:99%;" rows="3"></html:textarea>
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
								    <button type="button" id="bc" onclick="saveYstRtsq('update');">
										�޸�
									</button>
									<button type="button" id="tjsq" onclick="saveYstRtsq('updatesubmit');">
										�ύ����
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