<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/wdpj/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/wdpj/sqsh/js/jxsq.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">

			jQuery(function(){

				//��������ѡ��
				loadMkxxSelectOptions();
				//����radioѡ��
				loadMkxxRadioOptions();

				//ie9�������޴��¼�
                jQuery("#sqly").bind("input",function(){
                    chCount($(this),180,200);
                });

				var xh = jQuery("#xh").val();
				if (xh != ""){
					showDialog("ѡ�����뽱��",500,350,"xpj_sqsh.do?method=selectPjxm&xh="+xh+"&xzdm=${xzdm}");
					if(jQuery("#xxdm").val() == "10355"){
						jQuery("#hjxx").load("xpj_sqsh.do?method=loadHjxx&xh="+xh);
					}
				}
				
			});
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

		/**
		 * ѡ��������Ŀ
		 * @return
		 */
		function showPjxm(){
			var xh = jQuery("#xh").val();
			var url = "xpj_sqsh.do?method=selectPjxm&xh="+xh+"&xzdm=${xzdm}";
			if (jQuery.trim(xh) != ""){
                showDialog('ѡ�����뽱��',500,350,url);
			} else {
				showAlertDivLayer("����ѡ��ѧ����");
			}
		}
		</script>
	</head>
	<body>
		<html:form action="/xpj_sqsh.do?method=editSqb" method="post" styleId="sqshForm" onsubmit="return false;">
			<html:hidden property="sqid" />
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }"/>
			<input type="hidden" name="xmmc_11799" id="xmmc_11799" value=""/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">			
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
						<%@ include file="/xsgzgl/xpjpy/wdpj/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>
									���뽱��
									&nbsp;&nbsp;
									<a onclick="showPjxm()"
									   href="javascript:void(0);">
									   <font color="blue"><u>ѡ�����뽱��</u></font>
									   	
									</a>
									<a href="javascript:void(0);" onclick="addHjxx()" >
										<font color="blue" style="display:none" id="hjbutton" ><u>ѡ�����Ϣ</u></font>	
									</a>
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>��������</th>
							<td colspan="3">${pjzq }</td>
						</tr>
						<tr>
							<th>����</th>
							<td colspan="3">
								<table width="100%">
									<thead>
										<tr>
											<td width="30%">��������</td>
											<td width="15%">���</td>
											<td width="55%">����������д˵��</td>
										</tr>
									</thead>
									<tbody id="sqjx"></tbody>
								</table>
							</td>
						</tr>
						<logic:equal value="10355" name="xxdm">
							<tr id="hjtr" style="display:none">
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
										
									</tbody>
								</table>
								</td>
							</tr>
						</logic:equal>
						<logic:equal value="10279" name="xxdm">
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
							<th><span class="red">*</span>��������</th>
							<td colspan="3">
								<select name="sqlyyy" id="sqlyyy">
									<option value="A">��ͥ������Ȼ�ֺ�</option>
									<option value="B" >��ͥ����ͻ�������¼�</option>
									<option value="C" >��ͥ��Ա��м�</option>
									<option value="D" >�������Ͷ����������</option>
									<option value="E" >��ͥ�����ѧ��Ů�϶�</option>
									<option value="F" >��ͥ��Աʧҵ</option>
									<option value="G" >��ͥǷծ</option>
									<option value="H" >����</option>
									<option value="I" >����������ͥ</option>
									<option value="J" >�ͱ�</option>
								</select>
							</td>
						</tr>
						<tr id="sqlyTr">
							<logic:notEqual value="12056" name="xxdm">
							<th>
								<span class="red">*</span>	<logic:equal value="70002" name="xxdm">
									��Ҫ�¼�
								</logic:equal>
								<logic:notEqual value="70002" name="xxdm">
									��������
								</logic:notEqual>
								<br />(��100��)
							</th>
							<td colspan="3" style="word-break:break-all;">
								<!-- <div id="txsmDiv" style="color:red"></div> -->
								<%--���칤�̴�ѧ���ѧ����Ի�--%>
									<div  id="dxjxjTr" style="display: none;margin-bottom:5px; ">
										<span  style="color:red">�����뵥�ѧ���ͬѧ���������ɴ���������д��������</span>
										<button type="button"  onclick="showSqly();" id="buttonClose">
											��д����
										</button>
									</div>
								<html:textarea property="sqly" styleId="sqly" style="width:100%;" rows="5" onblur="checkLenBtw(this,0,100);"></html:textarea>
							</td>
							</logic:notEqual>
							<logic:equal value="12056" name="xxdm">
							<th>
								<span class="red">*</span>��������
								<br />(��150-250��)
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
			<div style="height: 50px"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="saveJxsq('save');return false;">
										����ݸ�
									</button>
									<button type="button" onclick="saveJxsq('submit');return false;">
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
	</body>
</html>

