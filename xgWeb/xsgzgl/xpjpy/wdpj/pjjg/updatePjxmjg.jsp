<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		
		<script	type="text/javascript">
			jQuery(function(){
				changeXmmc(false);
			});
			
			function changeXmmc(flg){
				if(flg){
					// ����
					jQuery("#xmmc").val("");
					jQuery("#xmje").val("");
				}
				
				//ȡ�����ݱ�xg_pjpy_new_pjxmb; �ֶΣ�xmmc
				var autoSetting = {
					dataTable:"xg_pjpy_new_pjxmb",
					dataField:"xmmc",
					dataFieldKey:"xmje",
					dataFieldKeyId:"xmje",
					sqlTj: getSqlTj,
					scrollHeight:135
				}
				// ģ��������������Ŀ���ơ�
				jQuery("#xmmc").setAutocomplete(autoSetting);
			}
			
			function getSqlTj(){
				var xn = jQuery("#xn").val();
				var xq = jQuery("#xq").val();
				var sqlTj = " and xn = '"+xn+"' ";
				if(xq){
					sqlTj += " and xq = '"+xq+"' ";
				}else{
					sqlTj += " and xq = 'on' ";
				}
                sqlTj += " and xzdm = '1' ";
				return sqlTj;
			}
			
			//����ѧ����Ϣ
			function getStuInfo(){
				jQuery.ajaxSetup({async:false});
				var parameter = {};
				parameter["xh"]=escape(jQuery("#xh").val());
				var url="xpj_pjjg.do?method=addPjxmjg";
				jQuery.getJSON(url,parameter,
						function(data){
							if(data!=null){
								jQuery("#xm").html(data.xm);
								jQuery("#nj").html(data.nj);
								jQuery("#xymc").html(data.xymc);
								jQuery("#zymc").html(data.zymc);
								jQuery("#bjmc").html(data.bjmc);
								jQuery("#zzmm").html(data.ldmc);
								jQuery("#mz").html(data.qsh);
								jQuery("#yhmc").html(data.cwh);
								jQuery("#yhkh").html(data.qsdh);
								changeTqs();
							}
						}
					);
				jQuery.ajaxSetup({async:true});
			}

			//����
			function saveForm(){
				var xh = jQuery("#xh").val();
				var xn = jQuery("#xn").val();
				var xq = jQuery("#xq").val();
				var lxdm = jQuery("#lxdm").val();
				var xzdm = jQuery("#xzdm").val();
				var xmmc = jQuery("#xmmc").val();

				if("" == xn){
					showAlert("ѧ�Ų���Ϊ��");
					return false;
				}if("" == xn|| "" == lxdm|| "" == xzdm|| "" == xmmc){
					showAlert("�뽫������Ϣ���д�*�ŵ���д����");
					return false;
				}
				if (jQuery("#sqly").val().length > 500){
					showAlert("���������������Ϊ500�����Ѿ���������ȷ�ϣ���");
					return false;
				}
				 var url = "xpj_pjjg.do?method=updatePjxmjg&type=update";
				 
			      ajaxSubFormWithFun("pjxmjgForm",url,function(data){
			    	  
			    	  if (data["success"] == "false"){
			    		  var msg = "��ѧ����"+jQuery.trim(xn)+"ѧ�꣬";
			    		  if (jQuery("#xq option:selected").text() != ""){
			    			  msg += jQuery("#xq option:selected").text() + "ѧ�ڣ�";
			    		  }
			    		  msg += "�ѻ��"+jQuery.trim(xmmc)+"��";
			    		  showAlert(msg);
			    	  } else {
			    		  showAlert(data["message"],{},{"clkFun":function(){
			        			if (parent.window){
			     				 refershParent();
			        			}
			      		  }});
			    	  }
			    	  
			      });
			}

		</script>
		
	</head>
		
	<body>
		<html:form action="/xpj_pjjg" method="post" styleId="pjxmjgForm" onsubmit="return false;">
		<html:hidden property="id"/>
		<input type="hidden" id="cpnj" name="cpnj" value="${jbxx.nj}"/>
			<input type="hidden" id="cpxydm" name="cpxydm" value="${jbxx.xydm}"/>
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}"/>
			<div class="tab" style='width:100%;overflow-x:hidden;overflow-y:auto;' >
				<table width="100%" border="0" class="formlist">
					<thead>
					<tr>
						<th colspan="4">
						<span>ѧ����Ϣ</span>
						</th>
					</tr>
					</thead>
						<%@ include file="/xsgzgl/xpjpy/wdpj/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
							<span>������Ŀ������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th><span class="red">*</span>ѧ��</th>
							<td
								<logic:notEqual value="1" name="pjzq"> colspan="3" </logic:notEqual>>
								
								<html:select  property="xn" styleId="xn" style="width:130px" onchange="changeXmmc(true);">
								<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
							<logic:equal value="1" name="pjzq">
								<th><span class="red">*</span>ѧ��</th>
								<td>
									<html:select  property="xq" styleId="xq" style="width:130px" onchange="changeXmmc(true);">
									<html:option value=""></html:option>
									<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
									</html:select>
								</td>
							</logic:equal>
					    </tr>
					    <tr>
							<th>��Ŀ����</th>
							<td>
								<html:select  property="lxdm" styleId="lxdm" style="width:130px">
								<html:options collection="xmlxList" labelProperty="mc" property="dm"/>
								</html:select>
							</td>
							<th><span class="red">*</span>��Ŀ����</th>
							<td>
								<html:hidden property="xzdm"></html:hidden>
								<logic:equal name="xzdm" value="2">
									���ý���
								</logic:equal>
								<logic:notEqual name="xzdm" value="2">
									��ѧ��
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>��Ŀ����</th>
							<td>
								<html:text  property="xmmc" styleId="xmmc"  style="width:180px;" maxlength="20"  styleClass="text_nor"></html:text>
							</td>
					    
							<th>���</th>
							<td>
								<html:text  property="xmje" styleId="xmje"  style="width:120px;" maxlength="10"  styleClass="text_nor" onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>
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
						<logic:equal value="13943" name="xxdm">
						<tr>
							<th>�佱��λ</th>
							<td colspan="3">
								<html:text  property="bjdw" styleId="bjdw"  style="width:180px;" maxlength="100"  styleClass="text_nor" ></html:text>
							</td>
						</tr>
						</logic:equal>
						<tr>
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
					    <tr>	
							<th>
							<span class="red">*</span>����ʱ��
							</th>
							<td colspan="3">
								<html:text property="sqsj" styleId="sqsj" onclick="return showCalendar('sqsj','yyyy-MM-dd HH:mm:ss');" readonly="true" ></html:text>
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
								<html:select property="sqlyyy" styleId="sqlyyy">
									<html:option value="A" >��ͥ������Ȼ�ֺ�</html:option>
									<html:option value="B" >��ͥ����ͻ�������¼�</html:option>
									<html:option value="C" >��ͥ��Ա��м�</html:option>
									<html:option value="D" >�������Ͷ����������</html:option>
									<html:option value="E" >��ͥ�����ѧ��Ů�϶�</html:option>
									<html:option value="F" >��ͥ��Աʧҵ</html:option>
									<html:option value="G" >��ͥǷծ</html:option>
									<html:option value="H" >����</html:option>
									<html:option value="I" >����������ͥ</html:option>
									<html:option value="J">�ͱ�</html:option>
								</html:select>
							</td>
						</tr>
					    <tr>
							<logic:notEqual value="12056" name="xxdm">
							<th>
								<logic:equal value="70002" name="xxdm">
									��Ҫ�¼�
								</logic:equal>
								<logic:notEqual value="70002" name="xxdm">
									��������
								</logic:notEqual>
								<br /><font color="red">(��100��)</font>
							</th>
							<td colspan="3">
								<html:textarea property='sqly' style="width:98%" styleId="sqly" rows='8' onblur="checkLen(this,100);"/>
							</td>
						</logic:notEqual>
						<logic:equal value="12056" name="xxdm">
							<th>
								<span class="red">*</span>��������
								<br /><font color="red">(��150-250��)</font>
							</th>
							<td colspan="3">
								<html:textarea property='sqly' style="width:98%" styleId="sqly" rows='8' onblur="checkLenBtw(this,150,250);"/>
							</td>
						</logic:equal>
			      </tr>
					</tbody>
				</table>
			</div>
			<div style="height: 50px"></div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<table  width="100%" border="0" class="formlist">
					<tfoot>
							<tr>
								<td colspan="4">
									<div class="bz">"<span class="red">*</span>"Ϊ������</div>
									<div class="btn">
										<button type="button" type="button" onclick="saveForm();">
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
		</html:form>
	</body>
</html>

