<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/wdpj/sqsh/js/bdpz.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function saveJxsb(type){
				if (jQuery("#sqly").val() == ""){
						showAlert("�뽫��������д������");
						return false;
				}
				if (jQuery("#sqly").val().length > 500){
					showAlert("���������������Ϊ500�����Ѿ���������ȷ�ϣ���");
					return false;
				}
				var url = "bzjl_sqsh.do?method=saveUpdateSqb&type="+type;
				ajaxSubFormWithFun("sqshForm",url,function(data){
					showAlert(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
				});
			}
			function showSqly(){
				var url = "bzjl_sqsh.do?method=sqlyPdf";
                 window.open(url);

			}
			
			jQuery(function(){
				if("11799" == jQuery("#xxdm").val()){
					 if(
						"���ѧ��" == jQuery("#xmmc1").text().trim() || 
						"���ѧ�𣨿Ƽ����£�" == jQuery("#xmmc1").text().trim() || 
						"���ѧ�������˶���" == jQuery("#xmmc1").text().trim() || 
						"���ѧ�����ջ��" == jQuery("#xmmc1").text().trim() || 
						"���ѧ��ѧϰ����1��" == jQuery("#xmmc1").text().trim() || 
						"���ѧ��ѧϰ����3��" == jQuery("#xmmc1").text().trim() || 
						"���ѧ��ѧϰ������" == jQuery("#xmmc1").text().trim() ){
						jQuery("#dxjxjTr").show();
					}
				}
			});
			//���ӻ���Ϣ
			function addHjxx(){
				var xh = jQuery("#xh").val();
				var url = "bzjl_sqsh.do?method=getHjjgAdd&xh="+xh;
				showDialog('ѡ�����뽱��',800,550,url);
			}
		</script>
	</head>
	<body>
		<html:form action="/bzjl_sqsh" method="post" styleId="sqshForm">
			<input type="hidden" id="xmdm" name="xmdm" value="${xmwhModel.xmdm }"/>
			<html:hidden property="xh" styleId="xh" />
			<html:hidden property="sqid" styleId="sqid" />
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }"/>
			<div style='tab;width:100%;height:415px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
						<%@ include file="/xsgzgl/xpjpy/wdpj/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>���뽱��</span>&nbsp;&nbsp;	
								<logic:equal value="1" name="hjjgxskg">
								<a href="javascript:void(0);" onclick="addHjxx()" >
										<font color="blue"  id="hjbutton" ><u>ѡ�����Ϣ</u></font>	
									</a>
								</logic:equal>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								��������
							</th>
							<td colspan="3">
								<bean:write property="xn" name="xpjSqshModel"/>&nbsp;<bean:write property="xqmc" name="xpjSqshModel"/>
							</td>
						</tr>
						<tr>
							<th>
								��Ŀ����
							</th>
							<td id="xmmc1">
								${xmwhModel.xmmc }
							</td>
							<th>
								��Ŀ���
							</th>
							<td>
								${xmwhModel.xmje }
							</td>
						</tr>
						<logic:equal value="	10279" name="xxdm">
						<tr>
							<th>
								��������<br/><font color="red">(��500��)</font>
							</th>
							<td colspan="3">
								<html:textarea  property="ylzd1" styleId="ylzd1"  style="width:94%;height:50px"  onblur="chLengs(this,500);"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								��ʱ��<br/><font color="red">(��200��)</font>
							</th>
							<td colspan="3">
								<div>
									<html:textarea  property="ylzd3" styleId="ylzd3"  style="width:94%;height:50px"  onblur="chLengs(this,200);"></html:textarea>
								</div>
							</td>
						</tr>
						<tr>
							<th >
								���쵥λ<br/><font color="red">(��200��)</font>
							</th>
							<td colspan="3">
								<html:textarea  property="ylzd4" styleId="ylzd4"  style="width:94%;height:50px"  onblur="chLengs(this,200);"></html:textarea>
							</td>
						</tr>
						</logic:equal>
						<logic:equal value="10355" name="xxdm">
							<logic:equal value="1" name="hjjgxskg">
								<tr id="hjtr">
								<th>����Ϣ</th>
								<td colspan="3">
									<table width="100%">
									<thead>
										<tr>
											<td width="30%">������</td>
											<td width="15%">��ʱ��</td>
											<td width="55%">�佱��λ</td>
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
						<tr id="fjxx">
							<th>
								������Ϣ
								<logic:equal value="10279" name="xxdm">								
									</br>								
									<font color="red">����������רҵ�������ϴ�֤�鼰���ϣ�</font>
								</logic:equal>
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

												//�Ϻ�Ϸ��ѧԺ������1�������ϴ���ʾ
												if(${xxdm=='10279'}){
													var html = "</br><span style='margin-left:36px'>4.�ϴ��ļ���ѧ��������</span>";
													jQuery('#tips').append(html);
												}
											});
										</script>
							</td>
						</tr>
						<%--����ҽҩ�ߵ�ר�Ƹ��Ի��ֶ�--%>
						<logic:equal value="70002" name="xxdm">
						<tr>
							<th>
								���ܺν���
								<br />(��500��)
							</th>
							<td colspan="3" style="word-break:break-all;">
								<!-- <div id="txsmDiv" style="color:red"></div> -->
								<html:textarea property="djjl" styleId="djjl" style="width:100%;" rows="5" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
						</logic:equal>
						<tr>
							<th><logic:equal value="70002" name="xxdm">
									��Ҫ�¼�
								</logic:equal>
								<logic:notEqual value="70002" name="xxdm">
									��������
								</logic:notEqual>��д˵��</th>
							<td colspan="3"><p>${xmwhModel.kgbz }</p></td>
						</tr>

						<tr id="sqlyTr">
							<logic:notEqual value="12056" name="xxdm">
							<th>
								<span class="red">*</span><logic:equal value="70002" name="xxdm">
									��Ҫ�¼�
								</logic:equal>
								<logic:notEqual value="70002" name="xxdm">
									��������
								</logic:notEqual>
								<br />(��500��)
							</th>
							<td colspan="3" style="word-break:break-all;">
							<%-- <div id="txsmDiv" style="color:red">${xmwhModel.kgbz }</div> --%>
								<%--���칤�̴�ѧ���ѧ����Ի�--%>
								<div  id="dxjxjTr" style="display: none;margin-bottom:5px; ">
									<span  style="color:red">�����뵥�ѧ���ͬѧ���������ɴ���������д��������</span>
									<button type="button"  onclick="showSqly();" id="buttonClose">
										��д����
									</button>
								</div>
								<html:textarea property="sqly" styleId="sqly" style="width:95%;" rows="5" onblur="checkLen(this,500);"></html:textarea>
							</td>
							</logic:notEqual>
							<logic:equal value="12056" name="xxdm">
							<th>
								<span class="red">*</span>��������
								<br />(��150-250��)
							</th>
							<td colspan="3" style="word-break:break-all;">
								<%-- <div id="txsmDiv" style="color:red">${xmwhModel.kgbz }</div> --%>
								<html:textarea property="sqly" styleId="sqly" style="width:95%;" rows="5" onblur="checkLenBtw(this,150,250);"></html:textarea>
							</td>
							</logic:equal>
						</tr>
					</tbody>
				</table>
			</div>
			<div class='tab'>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="saveJxsb('save');return false;">
										����ݸ�
									</button>
									<button type="button" onclick="saveJxsb('submit');return false;">
										�ύ����
									</button>
									<button type="button"  onclick="iFClose();" id="buttonClose">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
		<script type="text/javascript">
		jQuery(function(){			
			var xxdm = jQuery("#xxdm").val();
			if('11527' == xxdm){
				var xmmc = jQuery("#xmmc1").text();
				if(xmmc.trim() == '����ʡ2017�������ҵ��'){															
					var html = '<tr id="djb"><th>�ǼǱ�����</th><td colspan="3"><a href="xsgzgl/xpjpy/wdpj/sqsh/sjdjb_11527.docx" target="_blank">ʡ���ǼǱ�����</a></td></tr>'
					jQuery("#fjxx").before(html);
					
				}
			    if(xmmc.trim() == '���ϳ���ѧԺ2017�������ҵ��'){			    				    					    	
			    	var html = '<tr id="djb"><th>�ǼǱ�����</th><td colspan="3"><a href="xsgzgl/xpjpy/wdpj/sqsh/xjdjb_11527.docx" target="_blank">У���ǼǱ�����</a></td></tr>'
					jQuery("#fjxx").before(html);			    	
			    }
			}
		})
		</script>
	</body>
</html>

