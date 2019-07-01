<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	 <%@ include file="/syscommon/head.ini"%>
	 <script type="text/javascript" src="js/check.js"></script>
	 <script type="text/javascript">
	    function setZxpjAction(){
	    	var sqid = jQuery("#sqid").val();
	    	var zxid = jQuery("#zxid").val();
	 		var zxxgmydpf = jQuery("#zxxgmydpf").val();
	 		var xszxpj = jQuery("#xszxpj").val();
	 		if(!checkNotNull("xszxpj")){
	 			showAlert("�뽫��������д������");
	 			return false;
	 		}
	 		if(xszxpj.length > 500){
	 			showAlertDivLayer("��ѯ�����������������"+500+",��ȷ�ϣ�");
	 			return false;
	 		}
	 		jQuery.post("xljk_xsyyzx.do?method=setZxpjAction",{sqid: sqid, zxid: zxid, zxxgmydpf: zxxgmydpf, xszxpj: xszxpj},function(data){
	 			showAlert(data["message"],{},{"clkFun":function(){
		 				if (frameElement.api){
		 					var api = frameElement.api,W = api.opener;
		 					W.reloadXsyysqDataTable();
		 					iFClose();
		 				} 
	 				}
	 			});
			},'json');
	 	}
	 </script>
  </head>
  
  <body>
    <table width="100%" border="0" class="formlist">
    	<thead>
			<tr>
				<th colspan="2">
					<span>��ѯ����</span>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th width="27%">
					<span class="red">*</span>��ѯЧ�����������
				</th>
				<td>
					<html:select property="zxxgmydpf" styleId="zxxgmydpf"  style="width: 150px" value="${zxxgmydpf}">
						<html:option value="1">1</html:option>
						<html:option value="2">2</html:option>
						<html:option value="3">3</html:option>
						<html:option value="4">4</html:option>
						<html:option value="5">5</html:option>
						<html:option value="6">6</html:option>
						<html:option value="7">7</html:option>
						<html:option value="8">8</html:option>
						<html:option value="9">9</html:option>
						<html:option value="10">10</html:option>
					</html:select>
				</td>
			</tr>
			<tr>
				<th width="27%">
					<span class="red">*</span>������ѯ����<br/>
					<span class="red">(��500��)</span>
				</th>
				<td>
					<textarea name="xszxpj" id="xszxpj" rows="5" style="width: 95%;" onkeypress="checkLen(this,500);">${xszxpj }</textarea>
					<html:hidden property="sqid" styleId="sqid" value="${sqid }"/>
					<html:hidden property="zxid" styleId="zxid" value="${zxid }"/>
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">
					<div class="bz">"<span class="red">*</span>"Ϊ������</div>
					<div class="btn">
						<button id="submit_button" type="button"  onclick="setZxpjAction();">
							�� ��
						</button>
						<button type="button" name="�� ��" onclick="iFClose();">
							�� ��
						</button>
					</div>
				</td>
			</tr>
		</tfoot>
    </table>
  </body>
</html>
