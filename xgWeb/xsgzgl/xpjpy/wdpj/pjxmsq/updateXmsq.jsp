<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function saveJxsb(){
				if (jQuery("#sqly").val() == ""){
					showAlert("�뽫��������д������");
					return false;
				}
				
				var url = "xpj_sqsh.do?method=saveUpdateSqb&type=save";
				ajaxSubFormWithFun("sqshForm",url,function(data){
					showAlert(data["message"],{},{"clkFun":function(){
						if (parent.window){
							var api = frameElement.api;
							W = api.opener;
							var grid = W.jQuery("#dataTable").reloadGrid();
							iFClose();
						}
					}});
				});
			}

			//���ӻ���Ϣ
			function addHjxx(){
				var xh = jQuery("#xh").val();
				var url = "xpj_sqsh.do?method=getHjjgAdd&xh="+xh;
				showDialog('ѡ�����뽱��',800,550,url);
			}

			jQuery(function(){
                //ie9�������޴��¼�
                jQuery("#sqly").bind("input",function(){
                    chCount($(this),180,200);
                });
			});
		</script>
	</head>
	<body>
		<html:form action="/xpj_pjxmsq" method="post" styleId="sqshForm">
			<html:hidden property="sqid" styleId="sqid" />
			<html:hidden property="xh" styleId="xh" />
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;' >
				<table width="100%" border="0" class="formlist">
					<logic:notEqual name="UserType" value="stu">
						<thead>
							<tr>
								<th colspan="4">
									<span>������Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="20%">
									ѧ��
								</th>
								<td width="30%">
									<a href="javascript:void(0);" class="name" 
									   onclick="showDialog('ѧ��������Ϣ',700,500,'xsxx_tygl.do?method=ckZxsxx&xh=${jbxx.xh }')"
									   style="margin-left: 1px;"
									 >
									 ${jbxx.xh }
									</a>
								</td>
								<th width="20%">
									����
								</th>
								<td width="30%">
									${jbxx.xm }
								</td>
							</tr>
							<tr>
								<th>
									�Ա�
								</th>
								<td>
									${jbxx.xb }
								</td>
								<th>
									���֤��
								</th>
								<td>
									${jbxx.sfzh }
								</td>
							</tr>
							<tr>
								<th>
									�꼶
								</th>
								<td>
									${cpbjxx.nj }
								</td>
								<th>
									<bean:message key="lable.xb" />
								</th>
								<td>
									${cpbjxx.xymc }
								</td>
							</tr>
							<tr>
								<th>
									רҵ
								</th>
								<td>
									${cpbjxx.zymc }
								</td>
								<th>
									�༶
								</th>
								<td>
									${cpbjxx.bjmc }
								</td>
							</tr>
						</tbody>
					</logic:notEqual>
					<thead>
						<tr>
							<th colspan="4">
								<span>���뽱��&nbsp;&nbsp;
									<logic:equal value="1" name="hjjgxskg">
								<a href="javascript:void(0);" onclick="addHjxx()" >
										<font color="blue"  id="hjbutton" ><u>ѡ�����Ϣ</u></font>	
									</a>
								</logic:equal>
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">��������</th>
							<td colspan="3">${pjzqmc}</td>
						</tr>
						<tr>
							<th width="20%">
								��Ŀ����
							</th>
							<td width="30%">
								${xmwhModel.xmmc }
							</td>
							<th width="20%">
								��Ŀ���
							</th>
							<td width="30%">
								${xmwhModel.xmje }
							</td>
						</tr>
						<logic:equal value="10355" name="xxdm">
						<logic:equal value="1" name="hjjgxskg">
						<tr id="hjtr">
								<th>����Ϣ</th>
								<td colspan="3">
									<table width="100%">
									<thead>
										<tr>
											<td width="30%">������</td>
											<td width="20%">��ʱ��</td>
											<td width="50%">�佱��λ</td>
										</tr>
									</thead>
									<tbody id="hjxx">
										<logic:iterate id="i" name="hjjgList">
											<tr>
												<td>${i.hjmc}</td>
												<td>${i.hjsj}</td>
												<td>${i.fjdw}</td>
											</tr>									
										</logic:iterate>
									</tbody>
								</table>
								</td>
							</tr>
						</logic:equal>
						</logic:equal>
						<tr>
							<th>
								������Ϣ
							</th>
							<td colspan="3">
								<html:hidden property="ylzd5" styleId="ylzd5" />
								<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										jQuery.MultiUploader({
											maxcount : ${xxdm=='12713'?10:3},
											//��׺
											accept : 'png|gif|jpg|zip|rar|doc|docx|pdf',
											//����ļ���С ��λM
											maxsize: ${xxdm=='12713'?30:10},
											//��Ÿ������������id
											elementid : 'ylzd5'
											});
										
										//�㽭ͬ�ÿƼ�ְҵ����ѧԺ��׷��2�������ϴ���ʾ
										if(${xxdm=='12647'}){
											var tipsPrepend = "�� �ļ�����ݻ�����������磺�����ҵ��������������֤������Ƭ��</br>"+
															  "�� �����ϴ�����ļ�����ɹ�ѡ��һ�����ٴε��ѡ���ļ�������</br>";
											jQuery('#tips').prepend(tipsPrepend);
										}
									});
								</script>
							</td>
						</tr>
						<tr>
							<th>����������д˵��</th>
							<td colspan="3"><p>${xmwhModel.kgbz }</p></td>
						</tr>
						<tr>
							<logic:notEqual value="12056" name="xxdm">
							<th>
								<span class="red">*</span>��������</br>(˼�����Ρ����ʵ�����ۺ����ʵ÷֡�ѧϰ������)</br><font color="red" >(��180-200��)</font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<%-- <div id="txsmDiv" style="color:red">${xmwhModel.kgbz }</div> --%>
								<html:textarea property="sqly" styleId="sqly" style="width:100%;" rows="5" onblur="checkLenBtw(this,180,200);"></html:textarea>
							</td>
							</logic:notEqual>
							<logic:equal value="12056" name="xxdm">
							<th>
								<span class="red">*</span>��������</br>(˼�����Ρ����ʵ�����ۺ����ʵ÷֡�ѧϰ������)</br><font color="red">(��150-250��)</font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<%-- <div id="txsmDiv" style="color:red">${xmwhModel.kgbz }</div> --%>
								<html:textarea property="sqly" styleId="sqly" style="width:100%;" rows="5" onblur="checkLenBtw(this,150,250);"></html:textarea>
							</td>
							</logic:equal>
						</tr>
					</tbody>
				</table>
				</div>
				<div>
					<table  width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="bz">"<span class="red">*</span>"Ϊ������</div>
									<div class="btn">
										<button type="button" type="button" onclick="saveJxsb();">
											�� ��
										</button>
										
										<button type="button" type="button" onclick="iFClose();">
											�� ��
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

