<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
				
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript" src="js/stuinfoFunction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getXjydInfo.js'></script>
		<script type="text/javascript" src="js/xgutil.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type="text/javascript" src="js/xsxx/xsxxplczFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/xsxxgl/js/xsxxgl.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/xsxxgl/js/xsxxglZj.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			if("10511"!=jQuery("#xxdm").val()){
			jQuery("#addGkPic").css("display","none");
			jQuery("#stuGkImg").css("display","none");
			jQuery("#gkzpscbtn").css("display","none");
			}
			});
		</script>
	</head>
	<body >
			
		<html:form action="/xsxx_xsxxgl"  method="post" styleId="form1">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="tab"  id="content" style="overflow-x:hidden;">
		</div>
		<div style="height: 15px"></div>
		<table width="100%" border="0" class="formlist">
			<tfoot>
				<tr>
					<td colspan="5">
						<div class="bz">"<span class="red">*</span>"Ϊ������</div>
						<div class="btn">
							<button type="button"  name="����" onclick="saveZxsSq()" id="buttonSave">
								�� ��
							</button>
							<button type="button"  name="�ر�" onclick="Close()" id="buttonClose">�� ��</button>					           
						</div>
					</td>
				</tr>
			</tfoot>
		</table>	
		</html:form>	
	
	
		<div id="zpHidDiv" style="display:none;">		
				<jsp:include flush="true" page="zpzj.jsp"></jsp:include>
		</div>

			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxxNew.jsp"%>

	</body>
</html>

