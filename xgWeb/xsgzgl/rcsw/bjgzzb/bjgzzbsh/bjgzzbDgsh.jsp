<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.sqid}&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${model.splc}&shid=${model.shid}");
		});
	
		function saveShzt(){
			var shyj = jQuery("#shyj").val();
			if (jQuery.trim(shyj) == ""){
				showAlertDivLayer("����д��������");
				return false;
			}
			var url = "rcsw_xsgzzb_xsgzzbshgl.do?method=xsgzzbDgsh&type=save&gzzblx=bj";
			ajaxSubFormWithFun("xsgzzbshForm",url,function(data){
				showAlertDivLayer(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
			});
		}
		
		</script>
	</head>
<body>
	<html:form action="/rcsw_xsgzzb_xsgzzbshgl" method="post" styleId="xsgzzbshForm">
		<html:hidden name="model" property="sqid" styleId="sqid"/>
		<html:hidden name="model" property="splc" styleId="splc"/>
		<html:hidden name="model" property="shid" styleId="shid"/>
		<html:hidden name="model" property="lrr" styleId="lrr"/>
		
		<div style="height:460px;overflow-x:hidden;overflow-y:auto;">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>�ܱ���Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
						<tr>
							<th width="18%">ѧ��</th>
							<td width="32%">
								${model.xn}
							</td>
							<th width="18%">ѧ��</th>
							<td width="32%">
								${model.xqmc}
							</td>
					    </tr>
					    <tr>
							<th>�꼶</th>
							<td>
								${model.nj}
							</td>
							<th><bean:message key="lable.xy" /></th>
							<td>
								${model.xymc}
							</td>
					    </tr>
					    <tr>
							<th>רҵ</th>
							<td>
								${model.zymc}
							</td>
							<th>�༶</th>
							<td>
								${model.bjmc}
							</td>
					    </tr>
					    </tr>
							<th>�ܴ�</th>
							<td>
								${model.zcmc}
							</td>
							<th>�ܴ���ֹ����</th>
							<td>
								${model.zcksjsrq}
							</td>
					    </tr>
					    <tr>
							<th>
								Ӧ������
							</th>
							<td>
								${model.ydrs}
							</td>
							<th>
								ʵ������
							</th>
							<td>
								${model.sdrs}
							</td>
			      		</tr>
					    <tr>
							<th>
								�������
							</th>
							<td>
								${model.qjrs}
							</td>
							<th>
								�޹�δ��ѧ������
							</th>
							<td>
								${model.wdrs}
							</td>
			      		</tr>
			      		<tr>
							<th>��д��</th>
							<td colspan="3">
								${model.lrrxm}
							</td>
					    </tr>
					    <tr>
							<th>
								���ܶ�ѧ������<br />��������Ҫ����
							</th>
							<td colspan="3">
							    ${model.zynr}
							</td>
			      		</tr>
					    <tr>
							<th>
								����ѧ�����ڵ�<br />��Ҫ����
							</th>
							<td colspan="3">
							    ${model.zywt}
							</td>
			      		</tr>
					    <tr>
							<th>
								����Ϊ�Ϻ����<br />����Բ�
							</th>
							<td colspan="3">
							    ${model.jjdc }
							</td>
			      		</tr>
					    <tr>
					    	<th align="right">
								����
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										var gid = "${model.filepath}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
											});
									});
								</script>
							</td>
			      		</tr>
					</tbody>
			<thead>
				<tr>
					<th colspan="4">
						<span>�����ʷ</span>
					</th>
				</tr>
			</thead>				
			<tbody>
				<tr>
					<td colspan="4" id="shlccx">
					
					</td>
				</tr>
			</tbody>
			<thead>
				<tr>
					<th colspan="4">
						<span>�����Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<tr>
					<th>
						<font color="red">*</font>��˽��
					</th>
					<td colspan="3" id="shjgSpan">
						
					</td>
				</tr>
			</tr>
			<tr>
				<th width="20%">
					<font color="red">*&nbsp;</font> ������
					<br />
					<font color="red">(��200��)</font>
				</th>
				<td colspan="3">
					<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=xsgzzb&id=shyj" />
					<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
				</td>
			</tr>
			</tbody>
			</table>
		</div>
		</td>
		</tr>
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
