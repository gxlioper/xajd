<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/jxglFunction.js"></script>
	<script type='text/javascript'
		src='/xgxt/dwr/interface/getXmlgSzdwDAO.js'></script>
	<script>
	function Save(){
		jQuery.ajaxSetup({async:false});
		// �õ�JSON����
	    var parameter ={};
	    parameter["lx"]=escape(jQuery("input[type=radio][name=lx]:checked").val());
		var url = "jxgl_cssz_ajax.do?method=save";
        $("divWaiting").style.display="";
		$("divDisable").style.display="";
		jQuery.post(url,parameter,
			function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				alertInfo(result);
			}
		);
		jQuery.ajaxSetup({async:true});
	}
	</script>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a><bean:write name="title" /> </a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>			
		<!-- ���� end-->
		<!-- ��ʾ��Ϣ START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				��ѵ���ƿ�����Ҫ��Ϊ�˿��ƾ�ѵ�������ѵ�������ɵ��Ⱥ�ִ��˳��Ĭ��Ϊ��ѵ���ƻ���
			</p>
			<a class="close" title="����"
			   onclick="this.parentNode.style.display='none'"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->	
		<html:form action="/jxgl_cssz" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ end-->
			<div class="tab">
				<table class="formlist" border="0" align="center"
					style="width: 100%">
					<thead>
						<tr>
							<th colspan="2">
								<span>��������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>��ѵ���ƽ׶�
							</td>
							<td align="left" style="width: 60%">
								<html:radio name="rs" property="lx" styleId="lx" value="jxbz" >��ѵ����</html:radio>
								<html:radio name="rs" property="lx" styleId="lx" value="mdsc" >��ѵ��������</html:radio>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td align="center" colspan="2">							
			        			<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="Save();return false;" id="buttonSave">
										����
									</button>								
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>