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
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
		
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/xxxg/js/xgjgCk.js"></script>
			<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
   		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    	<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript">
				//���ø��� 
			function  getfj(){
				var fjid = jQuery('#fj').val();
				if("10704" == jQuery("#xxdm").val()){
					fjid = jQuery('#zd6_fj').val();
				}
				jQuery('#fjid').val(fjid);
				var gid = jQuery('#fjid').val();
				jQuery.MultiUploader_q({
					gid : gid
					});
			}
		</script>
	</head>
	<body >	
	<html:form action="/xsxx_xsxxxgsh" method="post" styleId="form1">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="sfxsgban" id="sfxsgban" value="${sfxsgban} "/>
			<input type="hidden" name="zpsfbt" id="zpsfbt" />
			<input type="hidden" name="zpsfcz" id="zpsfcz"  value="${zpsfcz }"/>
			<input type="hidden" name="fj" id="fj" value="${rs.zd6 }" />
			<input type="hidden" name="xh" id="xh" value='${xh}'/>
			<input type="hidden" name="sqid" id="sqid" value='${sqid}'/>
			<!-- ������� -->
			<div style="height: 50px;">
				<div id="navigation" style="top: 0; background: #fff; position: fixed; width: 100%;_position:absolute;_bottom:auto;_top:expression(eval(document.documentElement.scrollTop));">
					<div class="title_xxxx">
						<span class="people_xx"><span id="xmView"></span> ��ѧ�ţ� ${xh }��</span>
						<span class="wxts">��ܰ���ѣ� <span>�����������
								���Կ��ٶ�λ������Ҫ�鿴����Ϣ</span>
						</span>
					</div>
				</div>
			</div>
			<div class="demo_xxxx" style="margin-top: 20px; overflow-x:hidden;" id="content">
			</div>
				<div style="height: 15px"></div>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="5">
								<div class="btn">
									<button name="�ر�" onclick="Close()" type="button"
										id="buttonClose">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>		
			<div id="zpHidDiv" style="display: none;">			
				<jsp:include flush="true" page="../xsxxgl/zpxs.jsp"></jsp:include>
			</div>

			<div id="jtcyxxHidDiv" style="display: none;">
				<jsp:include flush="true" page="../xsxxgl/jtcyxxxs.jsp"></jsp:include>
			</div>
							
			<logic:equal name="xxdm" value="10704">
			<div id="fjHidDiv" style="display: none;">
				<input type='hidden' name='zd6_fj' id='zd6_fj'/>
			</div>
			</logic:equal>
			
			</html:form>
		
		<!-- ��ʾ��Ϣ -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
	</body>
	<script type='text/javascript'>
		jQuery(function() {		
			parent.moreClick();
		});
	</script>
</html>

