<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/tzxm/sq/js/xsxmsqsearch.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function(){
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${form.sqid}&tt="+new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${form.splc}&shid=${form.shid}");
		});
		function saveSh(){
			var shzt = jQuery("#shjg").val();
			if (jQuery("#shjg").val() == "" || jQuery("#shyj").val() == ""){
				showAlert("�뽫��������д������");
				return false;
			}
			var url = "xmsqgl_xmsh.do?method=XsXmDgsh&type=save";
			ajaxSubFormWithFun("XsXmShForm",url,function(data){
				// if(data["message"]=="����ɹ���"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		    	// }else{
		    		// showAlert(data["message"]);
		    		//}
				});
		   }
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/xmsqgl_xmsh" method="post" styleId="XsXmShForm">
				<html:hidden  name ="form" property="sqid" styleId="sqid"/>
				<html:hidden  name ="form" property="xh" styleId="xh"/>
				<html:hidden  name ="form" property="xq" styleId="xq"/>		
				<html:hidden  name ="form" property="xn" styleId="xn"/>		
				<html:hidden  name ="form" property="xmdm" styleId="xmdm"/>		
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
								<span>��Ŀ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>��Ŀ����</th>
							<td>
								${hdmap.xmmc}
							</td>
							<th>��Ŀ����</th>
							<td id="xmjbmc" >
								${hdmap.xmjbmc}
                             </td>
						</tr>
						<tr>
							<th>ѧ��</th>
							<td id="xn" >
								${hdmap.xn}
							</td>
							<th>ѧ��</th>
							<td id="xq" >
								${hdmap.xqmc}
							</td>
						</tr>
						<tr>
							<th>�걨����</th>
							<td id="sbbmmc" >
								${hdmap.sbbmmc}
							</td>
							<th>��ϵ��ʽ</th>
							<td id="lxdh" name="lxdh">
								${hdmap.lxdh}
							</td>
						</tr>
						<tr>
							<th>������Ŀ</th>
							<td id="sskmmc" name="sskmmc">
								${hdmap.sskmmc}
							</td>
							<th>����ѧ��</th>
							<td id="jcxf" name="jcxf">
								${hdmap.jcxf}
							</td>
						</tr>
						<tr>
							<th>�ɲ�������</th>
							<td id="kcyrs" name="kcyrs">
								${hdmap.kcyrs}
							</td>
							<th>����������</th>
							<td id="sqrs" name="sqrs">
								${hdmap.sqrs}
							</td>
						</tr>
						<tr>
							<th>��ͨ������</th>
							<td id="tgrs" name="tgrs">
								${hdmap.tgrs}
							</td>
							<th>��Ŀ��ʼʱ��</th>
							<td id="xmkssj" name="xmkssj">
								${hdmap.xmkssj}
							</td>
						</tr>
						<tr>
							<th></font>��������</th>
							<td colspan="3">
								${form.sqly}
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
						<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=sztzxsxmsh&id=shyj" />
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