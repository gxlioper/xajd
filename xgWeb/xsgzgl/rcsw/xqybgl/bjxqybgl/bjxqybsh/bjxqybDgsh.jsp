<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		
	<script type="text/javascript">
	

	jQuery(function(){
		jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.sqid}&tt="+new Date().getTime());
		jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${model.splc}&shid=${model.shid}");
	});
	
	function saveShzt(){
		
		var shzt = jQuery("#shjg").val();
		if(jQuery("#shjg").val() == "0"){
			showAlertDivLayer("��ѡ�����״̬��");
			return false;
		}
		var shyj = jQuery("#shyj").val();
		if (jQuery.trim(shyj) == ""){
			showAlertDivLayer("����д��������");
			return false;
		}
		var message;
		if(jQuery("#shjg").val() == "1"){
			message = "ͨ��";
		}
		if(jQuery("#shjg").val() == "2"){
			message = "��ͨ��";
		}
		if(jQuery("#shjg").val() == "3"){
			message = "�˻�";
		}
		
		showConfirmDivLayer("��ȷ��" + message + "��������",{"okFun":function(){
			var url = "rcsw_xqybgl_bjxqybgl_bjxqybshgl.do?method=bjxqybDgsh&type=save";
			ajaxSubFormWithFun("bjxqybshForm",url,function(data){
				showAlertDivLayer(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
			});
		}});
		
	}
	
	</script>
</head>
<body>
	<html:form action="/rcsw_xqybgl_bjxqybgl_bjxqybshgl"  styleId="bjxqybshForm">

		<html:hidden name="model" property="sqid" styleId="sqid"/>
		<html:hidden name="model" property="xn" styleId="xn"/>		
		<html:hidden name="model" property="xq" styleId="xq"/>
		<html:hidden name="model" property="yf" styleId="yf"/>			
		<html:hidden name="model" property="bygzkzqk" styleId="bygzkzqk"/>
		<html:hidden name="model" property="xsgzrd" styleId="xsgzrd"/>		
		<html:hidden name="model" property="xssxdt" styleId="xssxdt"/>
		<html:hidden name="model" property="xstsjgzjy" styleId="xstsjgzjy"/>		
		<html:hidden name="model" property="txsj" styleId="txsj"/>
		<html:hidden name="model" property="txr" styleId="txr"/>		
		<html:hidden name="model" property="shzt" styleId="shzt"/>
		<html:hidden name="model" property="splc" styleId="splc"/>	
		<html:hidden name="model" property="bjdm" styleId="bjdm"/>		
	
			
		<div style="height:460px;overflow-x:hidden;overflow-y:auto;">
			
		
			<table width="100%" class="formlist">
			<thead>
				<tr>
					<th colspan="4">
						<span>�±���д</span>
					</th>
				</tr>
			</thead>
			<tbody>
			
		
			<tr>
				<th align="right" width="20%">
					ѧ��
				</th>
				<td align="left" width="30%">
					${infoList.xn}
				</td>
				<th align="right" width="20%">
					ѧ��
				</th>
				<td align="left" width="30%">
					${infoList.xqmc}
				</td>
			</tr>
			<tr>
				<th align="right" width="20%">
					�꼶
				</th>
				<td align="left" width="30%" >
					${infoList.xn}
				</td>			
				<th align="right" width="20%">
					ѧԺ
				</th>
				<td align="left" width="30%" >
					${infoList.xymc}
				</td>
			</tr>
				
			<tr>
				<th align="right" width="20%">
					רҵ
				</th>
				<td align="left" width="30%" >
					${infoList.zymc}
				</td>			
				<th align="right" width="20%">
					�༶
				</th>
				<td align="left" width="30%" >
					${infoList.bjmc}
				</td>
			</tr>
			
			<tr>
				<th align="right" width="20%">
					�·�
				</th>
				<td align="left" width="30%" >
					${infoList.yf}
				</td>			
				<th align="right" width="20%">
					��д��
				</th>
				<td align="left" width="30%" >
					${infoList.lrrxm}
				</td>
			</tr>


		
			<tr>
				<th align="right" width="20%">
					���¹�����չ���
				</th>
				
				<td align="left" width="80%" colspan="3">
					${infoList.bygzkzqk}
				</td>
			</tr>
			
			<tr>
				<th align="right" width="20%">
					ѧ����ע�ȵ�
				</th>				
				<td align="left" width="80%" colspan="3">
					${infoList.xsgzrd}	
				</td>
			</tr>
			
			<tr>
				<th align="right" width="20%">
					ѧ��˼�붯̬
				</th>				
				<td align="left" width="80%" colspan="3">
					${infoList.xssxdt}
				</td>
			</tr>
			
			<tr>
				<th align="right" width="20%">
					ѧ�����󼰹�������
				</th>				
				<td align="left" width="80%" colspan="3">
					${infoList.xstsjgzjy}
				</td>
			</tr>
			
			
			</tbody>
			<thead>
				<tr>
					<th colspan="4">
						<span>�����ʷ</span>
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
						<th>
							<font color="red">*</font>��˽��
						</th>
						<td colspan="3" id="shjgSpan">
							
						</td>
					</tr>
			</tr>
			<tr>
				<th width="20%">
					<font color="red">*&nbsp;</font> ������
					<br />
					<font color="red">��200��</font>
				</th>
				<td colspan="3">
					<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=bjxqyb&id=shyj" />
					<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top:5px" onblur="checkLen(this,200);"></textarea>
				</td>
			</tr>
			</tbody>
			</table>
			
		</div>
		</td>
		</tr>
		</table>
		</div>
		<table width="100%" border="0" class="formlist">	
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="����"  onclick="saveShzt();return false;">
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
