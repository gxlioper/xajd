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
		    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				var url = "szdw_fdyxx.do?method=getFdyxx";
				jQuery.ajax( {
					type : "post",
					async : false,
					url : url,
					data : {
						zgh : jQuery("#zgh").val()
					},
					dataType : "json",
					success : function(data) {
						zdybdInit(gndm, data,{xs:true},"ck");
                        //是否在编补丁
                        var sfzbSelect = jQuery("#sfzb");
                        var sfzbTr = sfzbSelect.parent().parent();
                        //选择否显示备注
                        var bztr = "<tr id='bzbbzTr'><th>非在编备注</th><td colspan='4'>"+data["bzbbz"]+"</td></tr>"
                        sfzbTr.after(bztr);
                        sfzbChange();

                        //3+2辅导员补丁
                        var sfdrsjzfdySelect = jQuery("#sfdrsjzfdy")
                        var sfdrsjzfdyTr = sfdrsjzfdySelect.parent().parent();
                        var lxdmsjTr = "<tr id='lxdmsjTr'><th>担任\"3+2\"辅导员时间</th><td>"+data["drsj"]+"</td>";
                        lxdmsjTr += "<th>留校部门</th><td colspan='2'>"+data["lxbmmc"]+"</td></tr>";

                        sfdrsjzfdyTr.after(lxdmsjTr);

                        sfdrsjzfdyChange();
					}
				});
			});
		</script>
	</head>
	<body >	
		<html:form action="/szdw_fdyxx" method="post" styleId="form1">
			<html:hidden property="zgh" styleId="zgh"/>
			<input type="hidden" name="xxdm" id="xxdm"  value="${xxdm }"/>
			<div class="demo_fdyxx"  id="content">
			
			</div>
			<logic:equal value="11318" name="xxdm">
				<div id="fjHidDiv" style="display: none;">
					<table width="100%" border="0" style="" class="formlist">
					<tbody>
						<tr><th width="15%" >附件信息<br/>
							<font color="red">与上述项相关的<br/>附件</font> </th>
							<td width="85%" colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden property="kzzd19" styleId="kzzd19"/>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										var gid = jQuery('#kzzd19').val();
										jQuery.MultiUploader_q({
											gid : gid
											});
									});
								</script>
							 </td>
						 </tr>
					 </tbody>
					</table>
				</div>
			</logic:equal>
			<logic:equal value="10704" name="xxdm">
				<div id="fjHidDiv">
					<table width="100%" border="0" style="" class="formlist">
					<tbody>
						<tr><th width="15%" >附件信息<br/>
							<font color="red">与上述项相关的<br/>附件</font> </th>
							<td width="85%" colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<input type="hidden" id="kzzd19"/>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										var gid = jQuery('#kzzd19').val();
										jQuery.MultiUploader_q({
											gid : gid
											});
									});
								</script>
							 </td>
						 </tr>
					 </tbody>
					</table>
				</div>
			</logic:equal>
			<div style="height: 35px"></div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="5">
							<div class="btn">
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
				</div>	
			</div>
		
		</html:form>
	</body>
</html>

