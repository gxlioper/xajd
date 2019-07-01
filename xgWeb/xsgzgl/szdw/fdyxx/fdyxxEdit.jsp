<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/fdyxx/js/fdyxxEdit.js"></script>
		<script language="javascript" src="js/xsgzgl/szdw/dwwh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
    	<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
   		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				onShow("xg");
			  }
			);
		</script>

	</head>
	<body >	
		<html:form action="/szdw_fdyxx" method="post" styleId="form1">
			<html:hidden property="zgh" styleId="zgh"/>
			<input type="hidden" name="xxdm" id="xxdm"  value="${xxdm }"/>
			<input type="hidden"  id="jssfs"  value="${jssf }"/>
			<input type="hidden" name="zpsfbt" id="zpsfbt" />
			<input type="hidden" name="zpsfcz" id="zpsfcz"  value="${zpsfcz }"/>
			<div class="demo_fdyxx"  id="content">

			</div>
			<div style="height: 45px"></div>
			<table width="100%" border="0" class="formlist" id="gjl" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="5">
							<div class="bz">"<span class="red">*</span>"为必填项  </div>
							<div class="btn">
									<button name="保存" id="buttonSave" type="button" onclick="saveForm();">
										保 存
									</button>
								<button type="button"  name="关闭" onclick="Close()" id="buttonClose">关 闭</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<div id="zpHidDiv" style="display: none;">		
				<div align="center">
					<img src="teaPic.jsp?zgh=${fdyxxModel.zgh }" style="height:133px;width:100px;" border="0"   id="zhaopian"/>
					</div>
					<div align="center">
						<button type="button" onclick="showZpscDiv();" style="width:100px" id="buttonSave">上传照片</button>
					</div>
				</div>	
			</div>
		
		</html:form>
	</body>
</html>

