<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript">
	jQuery(function(){
		jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${jg.bjid}&tt="+new Date().getTime());
		jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${jg.splc}&shid=${jg.shid}");
	});
	function saveSh(){
		var shzt = jQuery("#shjg").val();
		if (jQuery("#shjg").val() == "" || jQuery("#shyj").val() == ""){
			showAlert("�뽫��������д������");
			return false;
		}

		var url = "cxdd_pysh.do?method=Dgsh&type=save";
		ajaxSubFormWithFun("CxddShForm",url,function(data){
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
	<style type="text/css">
	 .center{text-align:center}
	</style>
</head>
<body>
	<html:form action="/cxdd_pysh" method="post" styleId="CxddShForm">
		
		<html:hidden  property="bjid" styleId="bjid"/>
		<html:hidden  property="bjdm" styleId="bjdm"/>		
		<html:hidden name="jg" property="splc" styleId="splc"/>
			
		<html:hidden name="jg" property="shid" styleId="shid"/>
	     <%-- 
		<html:hidden  property="cjbz" styleId="cjbz"/>
	        --%>
		<html:hidden name="jg"  property="xn" styleId="xn"/>
  		<html:hidden name="jg"  property="xq" styleId="xq"/>
		<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
		   <table width="100%" border="0" class="formlist">			
			       <thead>
						<tr>
							<th colspan="4">
								<span><font color="blue">${xn}${xqmc} ${bjmc}</font> ���еȵ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr width="100%">
							<td width="100%" colspan="5">
							   <table width="100%">
							   		<tr width="100%">
							   			<th style='text-align:center'>ѧ��</th>
							   			<th style='text-align:center'>����</th>
							   			<th style='text-align:center'>�༶</th>
							   			<th style='text-align:center'>����</th>
							   			<th style='text-align:center'>����</th>
							   		</tr>
							   		<logic:iterate id="i" name="rslist">
							   		<tr width="100%">
							   			<td class='center' width='15%'>${i.xh}</td>
							   			<td class='center' width='15%'>${i.xm}</td>
							   			<td class='center' width='15%'>${i.bjmc}</td>
							   			<td class='center' width='15%'>${i.cxdjmc}</td>
							   			<td class='center' width='40%'>${i.py}</td>
							   		</tr>
							   		</logic:iterate>
							   </table>
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
					<td id="shjgSpan" colspan="3">
						
					</td>
				</tr>
				
				<tr>
					<th width="20%">
						<font color="red">*&nbsp;</font> ������
						<br />
						<font color="red">(��200��)</font>
					</th>
					<td colspan="3">
						<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=cxddxsb&id=shyj" />
						<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
					</td>
				</tr>
			</tbody>	
		  </table>
		</div>
		<div style="height: 30px"></div>
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
