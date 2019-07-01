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
		
			jQuery(function(){
				var api = frameElement.api;
				W = api.opener;
				var grid = W.jQuery("#dataTable");
				var rows = grid.getSeletRow();
				var tbody = jQuery('#sqjx');
				var xmmc = "";
				for(i = 0 ; i < rows.length ; i ++){
					var tr = jQuery("<tr></tr>");
					var item = rows[i];
					var xmdm = jQuery("<input type='hidden' name='xmdms' value='"+item['xmdm']+"'/>");
					var td1 = jQuery("<td></td>").append(item['xmmc']).append(xmdm);
					var td2 = jQuery("<td></td>").append(item['xmlx']);
					var td3 = jQuery("<td></td>").append(item['xmje']);

					tr.append(td1);
					tr.append(td2);
					tr.append(td3);
					tbody.append(tr);
					jQuery("#txsmDiv").html(item['kgbz']);
					xmmc += item['xmmc']
				}
			});


			
			/**
			 * ��������
			 * @returns {Boolean}
			 */
			function saveJxsq(type){
				
				if (jQuery("#sqly").val() == ""){
					showAlert("�뽫��������д������");
					return false;
				}

				if("10355"==${xxdm}){
					if(jQuery("#sqly").val().indexOf("��") >= 0){
						showAlert("�������ɲ��ܴ�'��'�������ַ���");
						return false;
					}
				}
				
				var url = "xpj_sqsh.do?method=saveJxsq&type="+type;
				//var url = "xpj_pjxmsq.do?method=saveJxsq";
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
			 function showSqly(){
					var url = "xpj_sqsh.do?method=sqlyPdf";
	                 window.open(url);

				}

            //���ӻ���Ϣ
            function addHjxx(){
                var xh = jQuery("#xh").val();
                var url = "xpj_sqsh.do?method=getHjjgAdd&xh="+xh;
                showDialog('ѡ�����뽱��',800,550,url);
            }
		</script>
	</head>
	<body>
		<html:form action="/xpj_sqsh" method="post" styleId="sqshForm">
			<html:hidden property="sqid" />
			<input type="hidden" name="xh" value="${jbxx.xh}"  id="xh" style="width:120px;"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;' >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>
									������Ŀ������Ϣ
									&nbsp;&nbsp;
									<a href="javascript:void(0);" onclick="addHjxx()" >
										<font color="blue" style="display:none"  id="hjbutton" ><u>ѡ�����Ϣ</u></font>
									</a>
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
							<th>����</th>
							<td colspan="3">
								<table width="95%">
									<thead>
										<tr>
											<td>��������</td>
											<td>�������</td>
											<td>���</td>
										</tr>
									</thead>
									<tbody id="sqjx">
										
									</tbody>
								</table>
							</td>
						</tr>
						<tr id="hjtr" style="display:none">
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
										
									</tbody>
								</table>
								</td>
							</tr>
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
							<td colspan="3"><p id="txsmDiv"></p></td>
						</tr>
						<tr>
							<logic:notEqual value="12056" name="xxdm">
							<th>
								<span class="red">*</span>��������</br>(˼�����Ρ����ʵ�����ۺ����ʵ÷֡�ѧϰ������)</br>
								<font color="red" >(��500������)</font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								 <!-- <div id="txsmDiv" style="color:red"></div> -->
								 <%--���칤�̴�ѧ���ѧ����Ի�--%>
									<div  id="dxjxjTr" style="display: none;margin-bottom:5px; ">
										<span  style="color:red">�����뵥�ѧ���ͬѧ���������ɴ���������д��������</span>
										<input type="hidden" name="xmmc_11799" id="xmmc_11799" value=""/>
										<button type="button"  onclick="showSqly();" id="buttonClose">
											��д����
										</button>
									</div>
								<html:textarea property="sqly" styleId="sqly" style="width:100%;" rows="5" onblur="checkLen(this,500)"></html:textarea>
							</td>
							</logic:notEqual>
							<logic:equal value="12056" name="xxdm">
							<th>
								<span class="red">*</span>��������</br>(˼�����Ρ����ʵ�����ۺ����ʵ÷֡�ѧϰ������)</br><font color="red">(��150-250��)</font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<!-- <div id="txsmDiv" style="color:red"></div> -->
								<html:textarea property="sqly" styleId="sqly" style="width:100%;" rows="5" onblur="checkLenBtw(this,150,250);"></html:textarea>
							</td>
							</logic:equal>
						</tr>
					</tbody>
				</table>
				</div>
				<div style="height: 35px"></div>
				<div>
					<table  width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="bz">"<span class="red">*</span>"Ϊ������</div>
									<div class="btn">
										<button type="button"  onclick="saveJxsq('save');">
											����ݸ�
										</button>
									
										<button type="button"  onclick="saveJxsq('submit');">
											�ύ����
										</button>
										
										<button type="button"  onclick="iFClose();">
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

