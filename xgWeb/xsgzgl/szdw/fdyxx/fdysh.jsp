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
				initXgsh();
				var lcid = jQuery("#lcid").val();
				var shid = jQuery("#shid").val();
				var sqid = jQuery("#ywid").val();
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=" + sqid + "&tt="+ new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=" + lcid + "&shid=" + shid);
			});
		</script>
	</head>
	<body >	
		<html:form action="/szdw_fdyxx" method="post" styleId="form1">
			<html:hidden property="zgh" styleId="zgh"/>
			<html:hidden property="sqid" styleId="sqid" />
			<input type="hidden" name="dshSqid" id="dshSqid" value='${dshSqid}'/>
			<input type="hidden" name="shzSqid" id="shzSqid" value='${shzSqid}'/>
			<input type="hidden" name="gwid" id="gwid" value='${gwid}'/>
			<input type="hidden" name="ywid" id="ywid" value='${ywid}'/>
			<input type="hidden" name="lcid" id="lcid" value='${lcid}'/>
			<input type="hidden" name="shid" id="shid" value='${shid}'/>
			
			<div class="demo_fdyxx" id="content"></div>
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
			<table width="100%" border="0" class="formlist" style="margin-top: 5px;margin-bottom: 35px;">
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
					<tr>
						<th width="16%" >
							<font width="13%" color="red">*</font>��˽��
						</th>
						<td  colspan="3" id="shjgSpan">
							
						</td>
					</tr>
					<tr>
						<th>
							������<br/><span class="red">����200�֣�</span>
						</th>
						<td colspan="3">
							<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=fdyxx&id=shyj" />
							<textarea rows="5" style="width: 90%;margin-top: 5px;" id="shyj" name="shyj" onblur="checkLen(this,200);"></textarea>
						</td>
					</tr>
				</tbody>
			</table>		
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="5">
							<div class="bz">"<span class="red">*</span>"Ϊ������  </div>
							<div class="btn">
									<button name="����" id="buttonSave" type="button" onclick="saveAudit();">
										�� ��
									</button>
								<button type="button"  name="�ر�" onclick="Close()" id="buttonClose">�� ��</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</html:form>
		<div id="zpHidDiv" style="display: none;">		
			<div align="center">
				<img src="teaPic.jsp?zgh=${fdyxxModel.zgh }" style="height:133px;width:100px;" border="0"   id="zhaopian"/>
				</div>
			</div>	
		</div>
	</body>
</html>

