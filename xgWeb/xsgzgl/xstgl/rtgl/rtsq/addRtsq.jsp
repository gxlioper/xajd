<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xstgl/rtgl/rtsq/js/rtsq.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			jQuery("#stxmmc").attr({readonly:"readonly"});
			if(jQuery("#xh") != null){
				jQuery("#xh").attr({readonly:"readonly"});
			}
			jQuery("#selxm").unbind('click').bind('click', function(){
			    var xh = jQuery("#xh").val() || jQuery("a[class='name']").text();
				if(xh == ''){
					showAlert("������д������Ϣ��");
					return false;
				}else{
				  var url = "stglRtsq.do?method=getStxmList&xh="+xh;
					showDialog('������Ŀѡ��',770,550,url);
				}
		   });
		});
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/stglRtsq" method="post" styleId="RtsqForm">
		<input type="hidden" id="usertype" value="${usertype}"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<logic:notEqual name="${usertype}" value="stu" >
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					</logic:notEqual>
					<logic:equal name="${usertype}" value="stu">
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					</logic:equal>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ŀά��</span>
							</th>
						</tr>
					</thead>
					<tbody id="stxm_body">
						<tr>
							<th><font color="red">*</font>������Ŀ����</th>
							<td>
								<html:text property="stxmmc" styleId="stxmmc" style="width:124px;"/>
								<button class="btn_01" id="selxm" type="button" >ѡ��</button>
								<input type="hidden" name="stid" id ="stid">
								<input type="hidden" id="splc" name="splc"/>
								<input type="hidden" name="xmlbdm" id ="xmlbdm"/>
							</td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th>�������</th>
							<td name="stlb">
								
							</td>
							<th>��Ŀ���</th>
							<td name="xmlb">
							   
							</td>
							
						</tr>
						<tr>
							<th>��Чѧ��</th>
							<td name="xn">
								
							</td>
							<th>�ҿ���λ</th>
							<td name="gkdw">
								
							</td> 
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
							   <a style="text-align: left;" onclick="showPfzmx(this);" class="down"
									href="javascript:void(0);"> <font color="blue">���չ��/����</font>
								</a>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_toggle" style="display:none">
					<!--  �汾����ȥ��kssj��jssj
						<tr>
							<th>������Ч��ʼʱ��</th>
							<td name="kssj"></td>
							<th>������Ч��ֹʱ��</th>
							<td name="jssj"></td>
						</tr>
					-->
						<tr>
							<th>���������</th>
							<td name="fzrlb"></td>
							<th>���Ÿ�����</th>
							<td name="stfzrxm"></td>
						</tr>
						<%--<tr>--%>
							<%--<th>ָ����ʦ</th>--%>
							<%--<td name="zdlsxm"></td>--%>
							<%--<th>ָ����ʦְ��</th>--%>
							<%--<td name="zdlszc"></td>--%>
						<%--</tr>--%>
						<%--<tr>--%>
							<%--<th>ָ����ʦ��ϵ��ʽ</th>--%>
							<%--<td name="zdlslxfs"></td>--%>
							<%--<th>��������</th>--%>
							<%--<td name="ssbm"></td>--%>
						<%--</tr>--%>

						<tr>
							<th>������ϵ�绰</th>
							<td name="lxdh"></td>
							<th>������</th>
							<td name="jtr"></td>
						</tr>
						<tr>
							<th>���ų���ʱ��</th>
							<td name="stclsj"></td>
							<th>����ʱ��</th>
							<td name="sqsj"></td>
						</tr>
						<tr>
							<th>
								���ż��
							</th>
							<td colspan="3" name="stsm">
								
							</td>
						</tr>
						<tr>
							<th>
								���Ż����
							</th>
							<td colspan="3" name="sthjqk">
								
							</td>
						</tr>
					<tr>
					<thead id="zdlsthead" style="display:none">
					<tr>
						<th colspan="4">
							<span>ָ����ʦ</span>
						</th>
					</tr>
					</thead>
					<tbody id="zdlstbody" style="display:none">
					<tr colspan="4">
						<td width="100%" colspan="4">
							<table width="100%" id="tablebody">
								<tbody id="nr">
								<tr>
									<th width="30%" style="text-align:left;">ָ����ʦ����</th>
									<th width="20%" style="text-align:left;">��������</th>
									<th width="20%" style="text-align:left;">��ϵ�绰</th>
									<th width="20%" style="text-align:left;">ְ��</th>
								</tr>
								</tbody>
							</table>
						</td>
					</tr>
					</tbody>
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
							<!-- 
							<th>������Ա���</th>
							<td>
								<html:select property="rylbdm" styleId="rylbdm" style="width:50%">
									<html:options collection="rylblist" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
							 -->
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
								    <button type="button" id="bc" onclick="saveRtSq('save');">
										��    ��
									</button>
									<button type="button" id="tjsq" onclick="saveRtSq('submit');">
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