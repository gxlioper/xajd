<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
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
				}else if("������"==clzt){
				}
				saveData('gyglnew_gybxgl.do?method=gybxglUpdate&doType=save',mustfill);
			});
			
			// �رհ�ť
			jQuery('#buttonClose').click(function(){
				Close();
			});	
			
			changeClzt();
		});
		
		function changeClzt(){
			var clzt = jQuery("#clzt").val();
			
			if("�Ѵ���"==clzt){
				jQuery("#bcl_body").css("display","none");
				jQuery("#ycl_body").css("display","");
				jQuery("#zbcl_body").css("display","none");
			}else if("������"==clzt){
				jQuery("#bcl_body").css("display","");	
				jQuery("#ycl_body").css("display","none");
				jQuery("#zbcl_body").css("display","none");
			}else if ("�ݲ�����"==clzt){
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
		
		<div class="tab" style="width:100%;overflow-x:hidden;overflow-y:auto;">
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
				</tr>
				<tr>
					
					<th>����ά��ʱ��</th>
					<td colspan="3">
						<logic:notEqual name="rs" property="qwwxsj_ks" value="">
						${rs.qwwxsj_ks } �� ${rs.qwwxsj_js }
						</logic:notEqual>
					</td>
				</tr>
				<tr>
					<th>��������</th>
					<td colspan="3">
<%--						<html:textarea property="bxnr" value="${rs.bxnr }" cols="4" rows="3" --%>
<%--							readonly="true" style="width: 95%"></html:textarea>--%>
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
					<th>����״̬</th>
					<td colspan="3">
						${rs.clzt}
						<input type="hidden" id="clzt" value="${rs.clzt }"/>
					</td>
				</tr>
			</tbody>
			<tbody id="ycl_body" style="display: none">
				<tr>
					<th>ʵ��ά��ʱ��</th>
					<td>
						${rs.wxsj}
					</td>
					<th>ά����Ա</th>
					<td>
						${rs.wxry}
					</td>
				</tr>
				<tr>
					<th>ά�޷���</th>
					<td>
						${rs.wxfy}
					</td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>ά������<br/></th>
					<td colspan="3">
<%--						<html:textarea property="wxnr" value="${rs.wxnr }" cols="4" rows="3" --%>
<%--							readonly="true" style="width: 95%"></html:textarea>--%>
						${rs.wxnr }
					</td>
				</tr>
			</tbody>
			<tbody id="bcl_body" style="display: none">
				<tr>
					<th>������ԭ��<br/></th>
					<td colspan="3">
<%--						<html:textarea property="bclyy" cols="4" rows="3" style="width: 95%" --%>
<%--							readonly="true"	value="${rs.bclyy}"></html:textarea>--%>
						${rs.bclyy}
					</td>
				</tr>
			</tbody>
			<tbody id="zbcl_body" style="display: none">
				<tr>
					<th>�ݲ�����ԭ��<br/></th>
					<td colspan="3">
<%--						<html:textarea property="zbclyy" cols="4" rows="3" style="width: 95%" --%>
<%--							readonly="true"	value="${rs.zbclyy}"></html:textarea>--%>
						${rs.zbclyy}
					</td>
				</tr>
			</tbody>
			<logic:notEqual name="xxdm" value="1103202">
				<thead>
				<tr>
					<th colspan="4">
						<span>��������</span>
					</th>
				</tr>
				</thead>
				<tbody>
				<tr>
					<th>����̶�</th>
					<td>${rs.mycd }</td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>����</th>
					<td colspan="3">
							<%--						<html:textarea property="pj" rows="3" cols="4" style="width: 95%"--%>
							<%--							value="${rs.pj}" readonly="true"></html:textarea>--%>
							${rs.pj}
					</td>
				</tr>
				</tbody>
			</logic:notEqual>
		</table>
		</div>
		<table class="formlist">
			<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
			           <button type="button" name="�ر�" id="buttonClose" onclick="Close();return false;">�ر�</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
		</table>
	</html:form>
</body>
</html>
