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
	var xxdm="${xxdm}";
		jQuery(function(){
			// ���水ť
			jQuery('#buttonSave').click(function(){
				var fpbmflag = jQuery("#fpbmth").css("display");
				
				var mustfill = ""
				if(fpbmflag != "none"){
					if(jQuery("#fpbm").val() == ""){
						 showAlert("���䲿�Ų���Ϊ�գ�");
						 return false;
					}
					if(xxdm == "12688"){
						if(jQuery("#fpxq").val() == ""){
							 showAlert("����У������Ϊ�գ�");
							 return false;
						}
					}
				}
				
				saveData('gyglnew_gybxgl.do?method=gybxglfpUpdate&doType=save',mustfill);
			});
			
			// �رհ�ť
			jQuery('#buttonClose').click(function(){
				Close();
			});	
			
			jQuery("#fpzt").change(function(){
				changeFpzt();
			});
			
			changeFpzt();
		});
		
		function changeFpzt(){
			
			var fpzt = jQuery("#fpzt").val();
			if("1"==fpzt){
				jQuery("#fpbmth").show();
				jQuery("#fpbmtd").show();
				if(xxdm == "12688"){
					jQuery("#fpxqth").show();
					jQuery("#fpxqtd").show();
				}
				
			}else{
				jQuery("#fpbmth").hide();
				jQuery("#fpbmtd").hide();
				jQuery("#fpbm").val("");
				if(xxdm == "12688"){
					jQuery("#fpxqth").hide();
					jQuery("#fpxqtd").hide();
					jQuery("#fpxq").val("");
				}
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
		
		<div class="tab" style="width:100%;height:480px;overflow-x:hidden;overflow-y:auto;">
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
					<td>
						<html:select property="fpzt" value="${rs.fpzt}" styleId="fpzt">
							<html:option value="0">δ����</html:option>
							<html:option value="1">�ѷ���</html:option>
						</html:select>
					</td>
					<th style="display:none" id="fpbmth"><span>*</span>���䲿��</th>
					<td style="display:none" id="fpbmtd">
						<html:select property="fpbm" styleId="fpbm" value="${rs.fpbm}"  style="width:200px">
							<html:option value=""></html:option>
							<html:options collection="bmlist" labelProperty="bmmc" property="bmdm"/>
						</html:select>
					</td>
				</tr>
				<!-- ��������ְҵ����ѧԺ���Ի� -->
				<logic:equal value="12688" name="xxdm">
					<tr>
						<th style="display:none" id="fpxqth"><span>*</span>����У��</th>
						<td style="display:none" id="fpxqtd">
							<html:select property="fpxq" styleId="fpxq" value="${rs.fpxq}"  style="width:200px">
								<html:option value=""></html:option>
								<html:options collection="xqlist" labelProperty="xqmc" property="dm"/>
							</html:select>
						</td>
					</tr>
				</logic:equal>
			</tbody>
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
