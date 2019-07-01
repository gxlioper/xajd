<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type='text/javascript' src="js/uicomm.js"></script>
	
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
	<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
	<script	type="text/javascript">
		jQuery(function(){
			// ���水ť
			jQuery('#buttonSave').click(function(){
				var clzt = jQuery("#clzt").val();
				var mustfill = ""
					
				if("�Ѵ���"==clzt){
					mustfill = "clzt-wxsj";
					jQuery("#bcl_body input,#bcl_body textarea").val("");
					jQuery("#zbcl_body input,#zbcl_body textarea").val("");
				}else if("�ݲ�����"==clzt){
					jQuery("#bcl_body input,#bcl_body textarea").val("");
					jQuery("#ycl_body input,#ycl_body textarea").val("");
				}else if("������"==clzt){
					jQuery("#zbcl_body input,#zbcl_body textarea").val("");
					jQuery("#ycl_body input,#ycl_body textarea").val("");
				}else{
					jQuery("#bcl_body input,#bcl_body textarea").val("");
					jQuery("#ycl_body input,#ycl_body textarea").val("");
					jQuery("#zbcl_body input,#zbcl_body textarea").val("");
				}
				
				saveData('gyglnew_gybxgl.do?method=gybxglUpdate&doType=save',mustfill);
			});
			
			// �رհ�ť
			jQuery('#buttonClose').click(function(){
				Close();
			});	
			
			jQuery("#clzt").change(function(){
				changeClzt();
			});
			
			changeClzt();
		});
		
		function changeClzt(){
			var clzt = jQuery("#clzt").val();
			
			if("�Ѵ���"==clzt){
				jQuery("#bcl_body").css("display","none");
				jQuery("#zbcl_body").css("display","none");
				jQuery("#ycl_body").css("display","");
			}else if("������"==clzt){
				jQuery("#bcl_body").css("display","");	
				jQuery("#ycl_body").css("display","none");
				jQuery("#zbcl_body").css("display","none");
			}else if("�ݲ�����"==clzt){
				jQuery("#zbcl_body").css("display","");	
				jQuery("#ycl_body").css("display","none");
				jQuery("#bcl_body").css("display","none");
			}else{
				jQuery("#bcl_body").css("display","none");	
				jQuery("#ycl_body").css("display","none");
				jQuery("#zbcl_body").css("display","none");
			}
		}
		
	</script>
	<style type="text/css">
		table{
			border-collapse:collapse;
		}
		table th{
			width:20%;
		}
		
		table td{
			width:30%;
		}
		
		table span{
			color:red;
		}
		
		#jydw{
			width:88%;
		}
		
		textarea {
			word-break:break-all;
		}
	</style>

</head>
<body>
	<html:form action="/gyglnew_gybxgl" method="post">
		<input type="hidden" name="xh" value="${param.xh }"/>
		<input type="hidden" name="pk" value="${param.pk }"/>
		
		<div class="tab" style="width:100%;height:460px;overflow-x:hidden;overflow-y:auto;">
		<table class="formlist">
			<thead>
				<tr>
					<th colspan="4">
						<span>ѧ����Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>ѧ��</th>
					<td>
						${rs.xh }
					</td>
					<th>����</th>
					<td>
						${rs.xm }
					</td>
				</tr>
				<tr>
					<th>�꼶</th>
					<td>
						${rs.nj }
					</td>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						${rs.xymc }
					</td>
				</tr>
				<tr>
					<th>רҵ</th>
					<td>
						${rs.zymc }
					</td>
					<th>�༶</th>
					<td>
						${rs.bjmc }
					</td>
				</tr>
				<tr>
				<th>ס������</th>
				<td>
					<logic:notEqual value="" name="rs" property="ldmc">
						${rs.ldmc}��
					</logic:notEqual>
					<logic:notEqual value="" name="rs" property="qsh">
						${rs.qsh}��
					</logic:notEqual>
					<logic:notEqual value="" name="rs" property="cwh">
						${rs.cwh}�Ŵ�
					</logic:notEqual>
				</td>
				<th>���ҵ绰</th>
				<td>${rs.qsdh}</td>
				</tr>	
			</tbody>
			<thead>
				<tr>
					<th colspan="4">
						<span>������Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
				
				<tr>
					<th>�����̶�</th>
					<td>
						${rs.jjcd }
					</td>
					<th>��ϵ�绰</th>
					<td>
						${rs.lxdh }
					</td>
					
				</tr>
				<tr>
				<th>�������</th>
					<td>
						${rs.bxlbmc }
					</td>
				<th>�����������</th>
					<td>
						${rs.bxlbzxmc }
					</td>
				<tr>
					<th>����ά��ʱ��</th>
					<td colspan="3">
						${rs.qwwxsj_ks } - ${rs.qwwxsj_js }
					</td>
				</tr>
				<tr>
					<th>��������</th>
					<td colspan="3" style="word-break:break-all;">
						${rs.bxnr }
					</td>
				</tr>
				<tr>
							<th align="right">
								������Ϣ
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										var gid = "${rs.filepath}";
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
						<span>������Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th><span>*</span>����״̬</th>
					<td colspan="3">
						<html:select property="clzt" value="${rs.clzt}" styleId="clzt">
							<html:option value="δ����">δ����</html:option>
							<html:option value="������">������</html:option>
							<html:option value="�ݲ�����">�ݲ�����</html:option>
							<html:option value="�Ѵ���">�Ѵ���</html:option>
						</html:select>
					</td>
				</tr>
			</tbody>
			<tbody id="ycl_body" style="display: none">
				<tr>
					<th><span>*</span>ʵ��ά��ʱ��</th>
					<td>
						<html:text property="wxsj" styleId="wxsj" value="${rs.wxsj}" readonly="true"
							onkeypress="onlyBackSpace(this,event);" onclick="return showCalendar(this.id,'yyyy-MM-dd')"></html:text>
					</td>
					<th>ά����Ա</th>
					<td>
						<html:text property="wxry" maxlength="20" value="${rs.wxry}"></html:text>
					</td>
				</tr>
				<tr>
					<th>ά�޷���</th>
					<td>
						<html:text property="wxfy" maxlength="10" value="${rs.wxfy}" onkeyup="checkInputNum(this);"></html:text>
					</td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>ά������<br/><span>(��������500)</span></th>
					<td colspan="3">
						<html:textarea property="wxnr" value="${rs.wxnr }" cols="4" rows="3" style="word-break:break-all;width: 95%" onblur="checkLen(this,500);" ></html:textarea>
					</td>
				</tr>
			</tbody>
			<tbody id="bcl_body" style="display: none">
				<tr>
					<th>������ԭ��<br/><span>(��������100)</span></th>
					<td colspan="3">
						<html:textarea property="bclyy" cols="4" rows="3" style="word-break:break-all;width: 95%" value="${rs.bclyy}" onblur="checkLen(this,100);"></html:textarea>
					</td>
				</tr>
			</tbody>
			<tbody id="zbcl_body" style="display: none">
				<tr>
					<th>�ݲ�����ԭ��<br/><span>(��������100)</span></th>
					<td colspan="3">
						<html:textarea property="zbclyy" cols="4" rows="3" style="word-break:break-all;width: 95%" value="${rs.zbclyy}" onblur="checkLen(this,100);"></html:textarea>
					</td>
				</tr>
			</tbody>
		</table>
		</div>
		<table class="formlist">
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          	<logic:notEqual value="view" name="doType">
			          		<button type="button" id="buttonSave">����</button>
			          	</logic:notEqual>
			            <button type="button" id="buttonClose">�ر�</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
		</table>
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
			
			 showAlert(jQuery('#message').val(),{},{"clkFun":function(){
 				if (parent.window){
 					refershParent();
 				}
 			}});
		</script>
	</logic:present>
</body>
</html>
