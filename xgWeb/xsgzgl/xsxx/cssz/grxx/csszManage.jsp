<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//����Ƿ����
		function clickSfsh(){
		
			var sfsh = $("hid_sfsh").value;
			
			if("��" == sfsh){
				$("tr_splc").style.display = "";
				$("tr_lssh").style.display = "";
			}else{
				$("tr_splc").style.display = "none";
				$("tr_lssh").style.display = "none";
				
				$("rad_lcid_no").checked="true";
				$("hid_lcid").value="��";
				
				$("shkssj").value = "";//��˿�ʼʱ��
				$("shjssj").value = "";//��˽���ʱ��
			}
		}
		
		//��һ��
		function nextStep(tag){
			if(tag == "ok"){
				var sfsh = $("hid_sfsh").value;//�Ƿ����
				var lcid = $("hid_lcid").value;//����ID
				var sqkssj = $("sqkssj").value;//���뿪ʼʱ��
				var sqjssj = $("sqjssj").value;//�������ʱ��
				var shkssj = $("shkssj").value;//��˿�ʼʱ��
				var shjssj = $("shjssj").value;//��˽���ʱ��
				
				if(sqkssj == ""){
					alertError("�޸Ŀ�ʼʱ��Ϊ�գ���ȷ��");
					return false;
				}
				if(sqjssj == ""){
					alertError("�޸Ľ���ʱ��Ϊ�գ���ȷ��");
					return false;
				}
				if(parseInt(sqkssj)>parseInt(sqjssj)){
					alertError("�޸Ŀ�ʼʱ�䲻�����ڽ���ʱ��");
					return false;
				}
				
				if(sfsh == "��"){

					if(lcid == "��"){
						alertError("�����Ҫ��˵Ļ�����ѡ���������");
						return false;
					}			
					if(shkssj == ""){
						alertError("��˿�ʼʱ��Ϊ�գ���ȷ��");
						return false;
					}
					if(shjssj == ""){
						alertError("��˽���ʱ��Ϊ�գ���ȷ��");
						return false;
					}
					if(parseInt(shkssj)>parseInt(shjssj)){
						alertError("��˿�ʼʱ�䲻�����ڽ���ʱ��");
						return false;
					}
					if(parseInt(sqjssj)>parseInt(shkssj)){
						alertError("��˿�ʼʱ�䲻�������޸Ľ���ʱ��");
						return false;
					}
				}
				
							
				var url="xsxx_cssz_grxx_method.do?method=saveCssz";
					$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
				//����
			 	var parameter = {
					"sfsh":escape(sfsh),
					"lcid":lcid,
					"sqkssj":sqkssj,
					"sqjssj":sqjssj,
					"shkssj":shkssj,
					"shjssj":shjssj
				};
				
				jQuery.post(url,parameter,function(result){
					refreshForm("xsxx_cssz_grxx_method.do?method=xszdManage");
				});
			}
		}
		</script>
	</head>
	<body onload="clickSfsh()" >
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>			
		<!-- ���� end-->
		<!-- ��ʾ��Ϣ START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				����ƶ������Ͻ�<font color="blue">��������</font>���ɲ鿴��ģ������˵����</br>
				<span id="div_help" style="display: none">
				1.���ѧ����Ϣ�޸Ĳ���Ҫ��˵Ļ�����ѧ���ύ�����,<font color="blue">ֱ�Ӹ���</font>ѧ����Ϣ�⡣</br>
				2.�����Ҫ��˵ģ���չ������ģ��Ϊ<font color="blue">ѧ����Ϣ</font>���������</br>
				3.ѧ��ֻ������<font color="blue">�޸�ʱ�䷶Χ��</font>�����޸ģ�����������뱻<font color="blue">�˻�</font>�Ļ�����ʹ����ʱ�䷶Χ��Ҳ�����޸ġ�</br>
				4.��ʦֻ������<font color="blue">���ʱ�䷶Χ��</font>������ˡ�
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/xsxx_cssz" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ end-->
			
			<!-- ά����Ϣ -->
			<table class="formlist" width="">
				<thead onclick="">
					<tr>
						<th colspan="2">
							<span>������Ϣ�޸Ĳ�������</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="30%">
							�Ƿ���Ҫ���
						</th>
						<td width="">
							<html:radio name="rs" property="sfsh" value="��" onclick="$('hid_sfsh').value=this.value;clickSfsh()"/>��
							<html:radio name="rs" property="sfsh" value="��" onclick="$('hid_sfsh').value=this.value;clickSfsh()"/>��
							<html:hidden name="rs" property="sfsh" styleId="hid_sfsh"/>
						</td>
					</tr>
					<tr style="display:none" id="tr_splc">
						<th>
							�������ѡ��
						</th>
						<td>
							<html:hidden name="rs" property="lcid" styleId="hid_lcid"/>
							<div style="width:100%;height:130px;overflow-x:hidden;overflow-y:auto;">
								<input type="radio" name="rad_lcid" id="rad_lcid_no"
										onclick="$('lcid').value=this.value"
										value="��"
								/>��
								
								<br/>
								<logic:iterate name="splcList" id="splcRs">
									<input type="radio" name="rad_lcid" 
										onclick="$('lcid').value=this.value"
										value="${splcRs.dm }"
										<logic:equal name="rs" property="lcid" value="${splcRs.dm }">checked="checked"</logic:equal>
									/>${splcRs.mc }
										
									<br/>
								</logic:iterate>
							</div>
						</td>
					</tr>
					<tr>
						<th width="">
							ѧ���޸�ʱ��
						</th>
						<td width="">
							<html:text name="rs" property="sqkssj" styleId="sqkssj" style="" 
								onclick="return showCalendar('sqkssj','ymmdd');"/>
							����
							<html:text name="rs" property="sqjssj" styleId="sqjssj" style="" 
								onclick="return showCalendar('sqjssj','ymmdd');"/>
						</td>
					</tr>
					<tr style="display:none" id="tr_lssh">
						<th width="">
							��ʦ���ʱ��
						</th>
						<td width="">
							<html:text name="rs" property="shkssj" styleId="shkssj" style="" 
								onclick="return showCalendar('shkssj','ymmdd');"/>
							����
							<html:text name="rs" property="shjssj" styleId="shjssj" style="" 
								onclick="return showCalendar('shjssj','ymmdd');"/>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan='2'>
							<div class="btn">
								<!-- ���� -->
								<button type="button" onclick="confirmInfo('��ȷ����������',nextStep);">
									��һ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- ά����Ϣ end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>

		</html:form>
	</body>
</html>