<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="pjpy/jtpj/jtpjjg/js/jtpjjg.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				jQuery("#jxid").bind("change",function(){
					loadJxxx();
				});
			});
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/jtpjjg.do?method=add&type=save">
			<html:hidden property="splcid" styleId="splcid"/>
			<input type="hidden" id="mrpjjtiid"/> 
			<html:hidden property="type" value="add" styleId="type"/>
			<html:hidden property="jgid" styleId="jgid"/>
			<html:hidden property="pdxn" styleId="pdxn"/>
			<html:hidden property="pdxq" styleId="pdxq"/>
			<input type="hidden" id="first" value="0"/>
			<input type="hidden" id="xxdm" value="${xxdm}" />
<%--			<html:hidden property="jtpjdw" styleId="jtpjdw"/>--%>
			<div style='tab;width:100%;height:460px;overflow-x:hidden;overflow-y:auto;'>
				<table width="" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>集体奖项结果</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th  width="20%" >
								<span class="red">*</span>申请周期
							</th>
							<td colspan="3">
								<html:select property="sqxn" styleId="sqxn" disabled="false" onchange="loadJtpjList();return false;">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
								</html:select>
								<html:select property="sqxq" styleId="sqxq" disabled="false" onchange="loadJtpjList();return false;">
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th >
								<span class="red">*</span>奖项
							</th>
							<td>
								<html:select property="jxid" styleId="jxid" style="width:200px;">
									<html:option value=""></html:option>
									<html:options collection="jxList" property="jxid" labelProperty="jxmc" />
								</html:select>
							</td>
							<th width="20%" >
								评定周期
							</th>
							<td id="pdzq" width="30%">
							</td>
						</tr>
						<tr>
							<th>
								奖项说明
							</th>
							<td colspan="3" id="jxsm">
							</td>
						</tr>
					</tbody>
				</table>
				<div id="jtpjxx">
				
				</div>
				<table width="" border="0" class="formlist">
					<tbody id="rdfsLocation">
						
					</tbody>
					<tr>
						<th>
							附件
						</th>
						<td colspan="3">
							<html:hidden property="filepath" styleId="fjid"/>
							<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
							<script type="text/javascript">
								//调用附件 
								jQuery(function(){
									jQuery.MultiUploader({
										maxcount : 3,
										//后缀
										accept : 'png|gif|jpg|zip|rar|doc|docx',
										//最大文件大小 单位M
										maxsize: 10,
										//存放附件的隐藏域的id
										elementid : 'fjid'
										});
								});
							</script>						
						</td>
					</tr>
					<tr>
						<th width="20%" style="border-top: 0px;">
							<span class="red">*</span>申请理由
							<br />
							<font color="red">(限500字)</font>
						</th>
						<td colspan="3" style="border-top: 0px;">
							<html:textarea property="sqly" rows="4" cols="70" styleId="sqly" onblur="checkLen(this,500);"></html:textarea>
						</td>
					</tr>
				</table>
			</div>
			<div style="hight: 25px"></div>
			<div>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button"
										onclick="save('jtpjjg.do?method=add&type=save','sqxn-sqxq-jxid-pjjtid-pjjtmc-sqly');return false;"
										id="buttonSave">
										保 存
									</button>
									<button type="button" onclick="iFClose();" id="buttonClose">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>
