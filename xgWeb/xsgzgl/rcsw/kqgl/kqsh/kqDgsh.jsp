<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/kqgl/zjsy/js/zjsykq.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/kqgl/kqsh/js/kqsh.js"></script>
		<script type="text/javascript">
		function qqxszj(html){
			jQuery("#tbody_qqryxx").append(html);	
			}
		
		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.id}&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${model.splc}&shid=${model.id}");
		});
		
		</script>
	</head>		
	<body>	
		<html:form action="/zjsy_kqsh" method="post" styleId="KqshForm" onsubmit="return false;">
		<html:hidden property="id" styleId="id"/>
		<html:hidden property="xn" styleId="xn"/>
		<html:hidden property="xq" styleId="xq"/>
		<html:hidden property="yf" styleId="yf"/>
		<html:hidden property="zc" styleId="zc"/>
		<html:hidden property="bjdm" styleId="bjdm"/>
		<input type="hidden" id="objStr" name="objStr"/>
		<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;' >
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
						<span>������Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
					    <tr>
							<th width="20%">ѧ��ѧ��</th>
							<td>
								<span>${KqshForm.xn}</span><span>${KqshForm.xqmc}</span>
							</td>
						  <th>�·�</th>
							<td>
								<span>${KqshForm.toyf}</span>
							</td>
					    </tr>
					    <tr>
					        <th>�ܴ�</th>
							<td>
								<span>${KqshForm.tozc}</span>
							</td>
					        <th>
								�༶
							</th>
							<td>
								<span>${KqshForm.bjmc}</span>
							</td>
							
						</tr>
					    <tr>
					   		 <th>Ӧ��������</th>
								<td colspan="3">
									<span>${KqshForm.cqrs}</span>
								</td>
						</tr>
			      		  <tr>
							<th>
								��ע
							</th>
							<td colspan="3">
								<span>${KqshForm.bz}</span>
							</td>
			      		</tr>
					</tbody>
				</table>
			   <table width="100%" border="0" class="datelist" style="margin:2px auto;">
					<thead>
						<tr>
							<td width='25%'>ѧ��</td>
							<td width='20%'>����</td>
							<td width='20%'>���ٴ���</td>
							<td width='20%'>�¼ٴ���</td>
							<td width='20%'>���ν���</td>
						</tr>
					</thead>
					<tbody id="tbody_qqryxx">
						<logic:notEmpty name="kqinfoList">
						  	<logic:iterate id="i" name="kqinfoList" indexId="index01">
								<tr>
									<td name="xh">${i.xh}</td>
									<td>${i.xm}</td>
									<td>${i.bjcs}</td>
									<td>${i.sjcs}</td>
									<td>${i.kkjs}</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="kqinfoList">
							<tr>
								<td align="center" colspan="5">
									���쳣����ѧ��
								</td>
							</tr>
						</logic:empty>
				    </tbody>
				</table>
			</div>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
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
							<tr>
								<th >
									��˽��
								</th>
								<td id="shjgSpan">
									
								</td>
							</tr>
					</tr>
					<tr>
						<th width="20%">
							<font color="red">*&nbsp;</font> ������
							<br />
							<font color="red">(��200��)</font>
						</th>
						<td colspan="3">
							<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=kqsh&id=shyj" />
							<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
						</td>
					</tr>
					</tbody>
				</table>
			</div>
			<div style="height: 15px"></div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">	
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="����"  onclick="saveSh();return false;">
									�� ��
								</button>
								<button type="button" name="�ر�" id="buttonClose" onclick="Close();return false;">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
	</body>
</html>