<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/ystgl/rtgl/rtsh/js/rtsh.js"></script>
		<script type="text/javascript" src="xsgzgl/ystgl/rtgl/comm/js/comm.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function(){
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${form.rtid}&tt="+new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${form.splc}&shid=${form.shid}");
		});
		function saveSh(){
			
			var shzt = jQuery("#shjg").val();
			if (jQuery("#shjg").val() == "" || jQuery("#shyj").val().trim() == ""){
				showAlert("�뽫��������д������");
				return false;
			}
			var url = "ystglRtsh.do?method=Rtdgsh&type=save";
			ajaxSubFormWithFun("YstRtshForm",url,function(data){
				  if(data["message"]=="����ɹ���"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		    	 }else{
		    		 showAlert(data["message"]);
		    		}
				});
		   }
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/ystglRtsh" method="post" styleId="YstRtshForm">
				<html:hidden  name ="form" property="rtid" styleId="rtid"/>
				<html:hidden  name ="form" property="xh" styleId="xh"/>
				<html:hidden  name ="form" property="splc" styleId="splc" value="${ystxx.splc}"/>
				<html:hidden  name ="form" property="sqsj" styleId="sqsj"/>
				<html:hidden  name ="form" property="shid" styleId="shid"/>
				<html:hidden  name ="form" property="shzt" styleId="shzt"/>
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
					<tbody>
						<tr>
							<th>��������Ŀ����</th>
							<td>${ystxx.ystxmmc}</td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th>���������</th>
							<td>${ystxx.ystlbmc}</td>
							<th>��Ŀ���</th>
							<td>${ystxx.xmlbmc}</td>
							
						</tr>
						<tr>
							<th>��Чѧ��</th>
							<td>${ystxx.xn }</td>
							<th>�ҿ���λ</th>
							<td>${ystxx.gkdwmc}</td>
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
							<td>${ystxx.fzrlb }</td>
							<th>�����Ÿ�����</th>
							<td>${ystxx.stfzrxm }</td>
						</tr>
						<tr>
							<th>ָ����ʦ</th>
							<td>${ystxx.zdlsxm }</td>
							<th>ָ����ʦְ��</th>
							<td>${ystxx.zcmc }</td>
						</tr>
						<tr>
							<th>ָ����ʦ��ϵ��ʽ</th>
							<td>${ystxx.zdlslxfs }</td>
							<th>��������</th>
							<td>${ystxx.ssbmmc }</td>
						</tr>
						<tr>
							<th>��������ϵ�绰</th>
							<td>${ystxx.lxdh }</td>
							
							<th>������</th>
							<td>${ystxx.jtr }</td> 
						</tr>
						<tr>
							<th>�����ų���ʱ��</th>
							<td>${ystxx.ystclsj }</td>
							<th>����ʱ��</th>
							<td>${ystxx.sqsj }</td>
						</tr>
						<tr>
							<th>
								�����ż��
							</th>
							<td colspan="3" name="stsm">
								${ystxx.ystjj}
							</td>
						</tr>
						<tr>
							<th>
								�����Ż����
							</th>
							<td colspan="3" name="ysthjqk">
								${ystxx.ysthjqk}
							</td>
						</tr>
					</tbody>
					<tbody>
						<tr>
							<th>��������</th>
							<td colspan="3">${ystxx.sqly}</td>
						</tr>
						<tr>
							<th>�س�</th>
							<td colspan="3">${ystxx.tc}</td>
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
						<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=ystrtsh&id=shyj" />
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