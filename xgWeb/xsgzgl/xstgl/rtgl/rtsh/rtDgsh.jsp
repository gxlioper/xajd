<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xstgl/rtgl/rtsh/js/rtsh.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function(){
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${form.rtid}&tt="+new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${form.splc}&shid=${form.shid}");
		});
		function saveSh(){
			var shzt = jQuery("#shjg").val();
			if (jQuery("#shjg").val() == "" || jQuery("#shyj").val() == ""){
				showAlert("�뽫��������д������");
				return false;
			}
			var url = "stglRtsh.do?method=Rtdgsh&type=save";
			ajaxSubFormWithFun("RtshForm",url,function(data){
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
		<html:form action="/stglRtsh" method="post" styleId="RtshForm">
				<html:hidden  name ="form" property="rtid" styleId="rtid"/>
				<html:hidden  name ="form" property="xh" styleId="xh"/>
				<html:hidden  name ="form" property="rtxq" styleId="rtxq"/>		
				<html:hidden  name ="form" property="rtxn" styleId="rtxn"/>		
				<html:hidden  name ="form" property="stid" styleId="stid"/>		
				<html:hidden  name ="form" property="splc" styleId="splc"/>
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
								<span>������Ŀά��</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>������Ŀ����</th>
							<td>${stxx.stxmmc}</td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th>�������</th>
							<td>${stxx.stlbmc}</td>
							<th>��Ŀ���</th>
							<td>${stxx.xmlbmc}</td>
							
						</tr>
						<tr>
							<th>��Чѧ��</th>
							<td>${stxx.xn }</td>
							<th>�ҿ���λ</th>
							<td>${stxx.gkdw}</td>
						</tr>
						<!-- 
						<tr>
							<th>������Ч��ʼʱ��</th>
							<td>${stxx.kssj }</td>
							<th>������Ч��ֹʱ��</th>
							<td>${stxx.jssj }</td>
						</tr>
						 -->
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
						<tr>
							<th>���������</th>
							<td>${stxx.fzrlb }</td>
							<th>���Ÿ�����</th>
							<td>${stxx.stfzr }</td>
						</tr>
						<tr>
							<th>������ϵ�绰</th>
							<td>${stxx.lxdh }</td>
							
							<th>������</th>
							<td>${stxx.jtr }</td> 
						</tr>
						<tr>
							<th>���ų���ʱ��</th>
							<td>${stxx.stclsj }</td>
							<th>����ʱ��</th>
							<td>${stxx.sqsj }</td>
						</tr>
						<tr>
							<th>
								���ż��
							</th>
							<td colspan="3" name="stsm">
								${stxx.stsm}
							</td>
						</tr>
						<tr>
							<th>
								���Ż����
							</th>
							<td colspan="3" name="sthjqk">
								${stxx.sthjqk}
							</td>
						</tr>
					</tbody>
					<tbody>
						<tr>
							<th>��������</th>
							<td>${stcyxx.sqly}</td>
							<th>������Ա���</th>
							<td>${stcyxx.rylbmc}</td>
						</tr>
						<tr>
							<th>�س�</th>
							<td colspan="3">${stcyxx.tc}</td>
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
						<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=shztrtsh&id=shyj" />
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