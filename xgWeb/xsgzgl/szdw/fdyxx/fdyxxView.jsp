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
                        //�Ƿ��ڱಹ��
                        var sfzbSelect = jQuery("#sfzb");
                        var sfzbTr = sfzbSelect.parent().parent();
                        //ѡ�����ʾ��ע
                        var bztr = "<tr id='bzbbzTr'><th>���ڱ౸ע</th><td colspan='4'>"+data["bzbbz"]+"</td></tr>"
                        sfzbTr.after(bztr);
                        sfzbChange();

                        //3+2����Ա����
                        var sfdrsjzfdySelect = jQuery("#sfdrsjzfdy")
                        var sfdrsjzfdyTr = sfdrsjzfdySelect.parent().parent();
                        var lxdmsjTr = "<tr id='lxdmsjTr'><th>����\"3+2\"����Աʱ��</th><td>"+data["drsj"]+"</td>";
                        lxdmsjTr += "<th>��У����</th><td colspan='2'>"+data["lxbmmc"]+"</td></tr>";

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
						<tr><th width="15%" >������Ϣ<br/>
							<font color="red">����������ص�<br/>����</font> </th>
							<td width="85%" colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden property="kzzd19" styleId="kzzd19"/>
								<script type="text/javascript">
									//���ø��� 
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
						<tr><th width="15%" >������Ϣ<br/>
							<font color="red">����������ص�<br/>����</font> </th>
							<td width="85%" colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<input type="hidden" id="kzzd19"/>
								<script type="text/javascript">
									//���ø��� 
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
								<button type="button"  name="�ر�" onclick="Close()" id="buttonClose">�� ��</button>
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

