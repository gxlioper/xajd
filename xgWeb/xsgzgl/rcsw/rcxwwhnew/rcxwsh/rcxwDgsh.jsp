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
	
		jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.id}&tt="+new Date().getTime());
		jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${model.splc}&shid=${model.shid}");
		// ======== ������ֵ begin========
		var fzlxmc = '${xwlbxx[0].rcxwfzlxmc}';
		var fzlx = '${xwlbxx[0].rcxwfzlx}';
		var fzsfgd='${xwlbxx[0].fzsfgd}';
		var fzqj = '${xwlbxx[0].fzqj}';
		var zgfz = '${xwlbxx[0].rcxwlbzgfz}';
		var zdfz = '${xwlbxx[0].rcxwlbzdfz}';
		var shfz = '${rs.shfz}';
		var html = "";
		html += "<input type='hidden' id='shfzsfgd' value='"+fzsfgd+"'/>";
		html += "<input type='hidden' name='rcxwfzlx' value='"+fzlx+"'/>";
		if(fzsfgd!=null && fzsfgd=="gd"){
			html += shfz+"<font color=\"blue\">��"+fzlxmc+"���̶�ֵ��</font><input type='hidden' name='shfz' id='shfz' value='"+shfz+"'/>";
			html += "<input type='hidden' id='shzgfz' value='"+shfz+"!!"+shfz+"'/>";
		}else {
			html += "<input type='text' name='shfz' id='shfz' style=\"width:50px\" onkeyup=\"checkInputNum(this)\" maxlength='8' value='"+shfz+"'/><font color=\"blue\">��"+fzlxmc+"����Χ"+zdfz+"-"+zgfz+"��</font>"; 
			html += "<input type='hidden' id='shzgfz' value='"+zdfz+"!!"+zgfz+"'/>";
		}
		jQuery("#shfzTd").html(html);
		// ======== ������ֵ end========
	});


	
	function saveShzt(){
		var shzt = jQuery("#shjg").val();
		if(jQuery("#shjg").val() == "0"){
			showAlertDivLayer("��ѡ�����״̬��");
			return false;
		}
		// ======== ������ֵ begin========
		var shfz = jQuery('#shfz').val();
		var shzgfz = jQuery('#shzgfz').val();
		if(shfz==''){
			showAlert("������ֵ����Ϊ�գ�");
			return false;
		}
		var arr = shzgfz.split("!!");
		if(arr[0]!=arr[1]){
			if(parseFloat(shfz)<parseFloat(arr[0])||parseFloat(shfz)>parseFloat(arr[1])){
				showAlert("������ֵ�����ڷ�ֵ��Χ�ڣ�");
				return false;
			}
		}
		// ======== ������ֵ end========
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
			var url = "rcsw_rcxwwhnew_rcxwshgl.do?method=rcxwDgsh&type=save";
			ajaxSubFormWithFun("rcxwshForm",url,function(data){
				showAlertDivLayer(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
			});
		}});
	}
    function yl() {
        var fjmc=jQuery("#fjmc").val();
        var fjlj=jQuery("#fjlj").val();
        var url="rcsw_rcxwwhnew_rcxwjggl.do?method=downloadFile";
        var URL="rcsw_rcxwwhnew_wjyl.do?method=preview&fjlj="+fjlj+"&fjmc="+fjmc+"&url="+url;
        window.open(encodeURI(URL));
    }
	</script>
</head>
<body>
	<input type="hidden" id="xxdm" value="${xxdm}"/>
	<html:form action="/rcsw_rcxwwhnew_rcxwshgl" method="post" styleId="rcxwshForm">
			
			<html:hidden name="model" property="id" styleId="id"/>
			<html:hidden name="model" property="xh" styleId="xh"/>		
			<html:hidden name="model" property="splc" styleId="splc"/>
			<html:hidden name="model" property="rcxwlbdldm" styleId="rcxwlbdldm"/>
			<html:hidden name="model" property="rcxwlbxldm" styleId="rcxwlbxldm"/>
			<html:hidden name="model" property="rcxwlbdm" styleId="rcxwlbdm"/>
			<html:hidden name="model" property="xn" styleId="xn"/>
			<html:hidden name="model" property="xq" styleId="xq"/>
			<html:hidden name="model" property="fz" styleId="fz"/>
			<html:hidden name="model" property="rcxwjlsj" styleId="rcxwjlsj"/>
			<html:hidden name="model" property="bz" styleId="bz"/>
			<html:hidden name="model" property="shid" styleId="shid"/>
			<html:hidden name="model" property="jlr" styleId="jlr"/>
			<html:hidden name="model" property="fssj" styleId="fssj"/>
			<html:hidden name="model" property="gfly" styleId="gfly"/>
			
		<div style="height:460px;overflow-x:hidden;overflow-y:auto;">
			
		<table class="formlist" width="95%">
			<thead>
				<tr>
					<th colspan="4">
						<span>ѧ����Ϣ</span>
					</th>
				</tr>
			</thead>
			<%@ include file="/xsgzgl/rcsw/rcxwwhnew/comm/viewStudent.jsp" %>
			<thead>
				<tr>
					<th colspan="4">
						<span>
							<logic:equal name="xxdm" value="10704">
								�ۺϲ�����¼��Ϣ
							</logic:equal>
							<logic:notEqual value="10704" name="xxdm">							
								��Ϊ��¼��Ϣ
							</logic:notEqual>
						</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th>
					ѧ��
				</th>
				<td>
					${rs.xn}
				</td>
				<th>
					ѧ��
				</th>
				<td>
					${rs.xqmc}
				</td>
			</tr>
			<tr>
				<th>
					<logic:equal value="10704" name="xxdm">
						�ۺϲ������
					</logic:equal>
					<logic:notEqual value="10704" name="xxdm">
						��Ϊ���				
					</logic:notEqual>
				</th>
				<td>
					${rs.rcxwlbmc}
				</td>
				<th>
					<logic:equal value="10704" name="xxdm">
						�ۺϲ�������
					</logic:equal>
					<logic:notEqual value="10704" name="xxdm">
						��Ϊ����				
					</logic:notEqual>
				</th>
				<td>
						${rs.rcxwlbdlmc}(${rs.ssxymc})
				</td>
			</tr>
			<tr>	
				<th>
					<logic:equal value="10704" name="xxdm">
						�ۺϲ���С��
					</logic:equal>
					<logic:notEqual value="10704" name="xxdm">
						��ΪС��				
					</logic:notEqual>
				</th>
				<td  >
					${rs.rcxwlbxlmc}
				</td>
				<th>
				   	����˵��
				</th>
				<td style="word-break:break-all;" width="650px">
					${rs.rcxwlbbz}
				</td>
			</tr>
			<tr>
				<th>������ֵ</th>
				<td>${rs.fz }</td>							
				<th >����ʱ��</th>
				<td >
					${rs.fssj}
				</td>
		    </tr>
		    <tr>
				<th>��¼��</th>
				<td>${rs.jlrxm }</td>							
				<th >��¼ʱ��</th>
				<td >
					${rs.rcxwjlsj}
				</td>
		    </tr>

		    <logic:equal value="13022" name="xxdm">
		    <tr>
		    	<th>����</th>
		    	<td colspan="3" id="fileTd">
		    		<logic:notEmpty name="rs" property="fjlj">
		    			<a href="javascript:void(0);" onclick="window.open('rcsw_rcxwwhnew_rcxwxxwhgl.do?method=downloadFile&id=${rs.id }');return false;" class="name">����</a>&nbsp;${rs.fjmc}
		    		</logic:notEmpty>
		    	</td>
		    </tr>
		    </logic:equal>
		    <logic:equal value="12942" name="xxdm">
		    <tr>
		    	<th>����</th>
		    	<td colspan="3" id="fileTd">
		    		<logic:notEmpty name="rs" property="fjlj">
		    			<a href="javascript:void(0);" onclick="window.open('rcsw_rcxwwhnew_rcxwxxwhgl.do?method=downloadFile&id=${rs.id }');return false;" class="name">����</a>&nbsp;${rs.fjmc}
		    		</logic:notEmpty>
		    	</td>
		    </tr>
		    </logic:equal>
			<logic:equal value="10699" name="xxdm">
				<tr>
					<th>����</th>
					<td colspan="3" id="fileTd">
						<logic:notEmpty name="rs" property="fjlj">
							<input type="hidden" value="${rs.fjmc}" id="fjmc"/>
							<input type="hidden" value="${rs.fjlj}" id="fjlj"/>
							<a href="javascript:void(0);" id="yl" onclick="yl();return false;" class="name">Ԥ��</a>
							<a href="javascript:void(0);" onclick="window.open('rcsw_rcxwwhnew_rcxwxxwhgl.do?method=downloadFile&id=${rs.id }');return false;" class="name">����</a>&nbsp;${rs.fjmc}
						</logic:notEmpty>
					</td>
				</tr>
			</logic:equal>
		    <tr>	
					<th>
						��������
					</th>
					<td colspan="3" style="word-break:break-all;" width="650px">
						${rs.gfly }
					</td>
			</tr>
			<tr>	
					<th>
						��ע
					</th>
					<td colspan="3" style="word-break:break-all;" width="650px">
						${rs.bz }
					</td>
			</tr>
			</tbody>
			</table>
			<table width="100%" border="0" class="formlist">
				<thead>
						<tr>
							<th colspan="4">
								<span>�����Ϣ</span>
							</th>
						</tr>
					</thead>				
					<tbody>
						<tr>
							<td colspan="4" id="shlccx">
							
							</td>
						</tr>
					
					</tbody>
			</table>
			<table width="100%" class="formlist">
			<tbody>
			<tr>
				<th width="20%">
					<font color="red">*</font>��˽��
				</th>
				<td id="shjgSpan" width="30%">
					
				</td>
				<th width="20%">
					<font color="red">*</font>������ֵ
				</th>
				<td width="30%" id="shfzTd">
				</td>
			</tr>
			<tr>
				<th width="20%">
					<font color="red">*&nbsp;</font> ������
					<br />
					<font color="red">��200��</font>
				</th>
				<td colspan="3">
					<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=rcxw&id=shyj" />
					<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:95%;margin-top:5px" onblur="checkLen(this,200);">${rs.shyj}</textarea>
				</td>
			</tr>
			
			</tbody>
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
