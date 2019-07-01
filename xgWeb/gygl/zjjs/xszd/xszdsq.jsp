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
		//����ѧ���߶�
		function saveXszd(tag){
			
			if(tag == "ok"){
				var zdkssj = $("zdkssj").value;
				var zdjssj = $("zdjssj").value;
				
				if(zdkssj == "" || zdjssj == ""){
					alertError("�߶���ʼʱ��ͽ���ʱ�䲻��Ϊ��<br>��ȷ��!");
					return false;
				}else if(zdkssj > zdjssj){
					alertError("�߶���ʼʱ�䲻�����ڽ���ʱ��<br>��ȷ��!");
					return false;
				}else{
					showTips('�����У����Ժ�......');
					var url = "gyglZjjs.do?method=xszdsq&doType=save";
					refreshForm(url);
				}
			}
		}
		</script>
	</head>
	<body onload="" >
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
			</p>
		</div>
		<!-- ���� end-->
		
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
				1.��ϵ�绰����ͥ��ַ����ͥ�绰ȡ��ѧ����Ϣ�����������ʱ�޸ĺ󣬽����޸ĺ��Ϊ׼��</br>
				2.��һ��֮�ڣ���ֻ��������һ���߶����벻Ҫ�ظ�������</br>
				3.����������޷��������水ť�Ļ��������Ǵ˴������Ѿ������ĸ���Ա��˹��ˣ���ǰ�������ѯ���鿴��</br>
				4.�����������������������������㲻ȷ��֮ǰ�Ƿ�������ģ���ǰ�������ѯ���в鿴��</br>
				</span>
			</p>			
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/gyglZjjs" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xh" value="${rs.xh }" />
			<input type="hidden" name="sqsj" value="${rs.sqsj }" />
			<input type="hidden" name="id" value="${rs.id }" />
			<!-- ������ end-->
			
			<!-- ѧ��������Ϣ -->
			<table class="formlist" width="">
				<thead onclick="">
					<tr>
						<th colspan="4">
							<span>ѧ��������Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="20%">ѧ��</th>
						<td width="30%">
							${rs.xh }
						</td>
						<th width="20%">����</th>
						<td width="30%">
							${rs.xm }
						</td>
					</tr>
					<tr>
						<th>�Ա�</th>
						<td>
							${rs.xb }
						</td>
						<th>�༶</th>
						<td>
							${rs.bjmc }
						</td>
					</tr>
					<tr>
						<th>��ϵ�绰</th>
						<td>
							<html:text name="rs" property="lxdh" styleId="lxdh"
							onkeyup="checkInputNum(this)" onblur="checkInputNum(this)" 
							maxlength="20" style="ime-mode:disabled;"
							/>
						</td>
						<th>��ͥ�绰</th>
						<td>
							<html:text name="rs" property="jtdh" styleId="jtdh"
							onkeyup="checkInputNum(this)" onblur="checkInputNum(this)" 
							maxlength="20" style="ime-mode:disabled;"
							/>
						</td>
					</tr>
					<tr>
						<th>��ͥ��ַ</th>
						<td colspan="3">
							<html:text name="rs" property="jtdz" styleId="jtdz" style="width: 545px" maxlength="50"/>
						</td>
					</tr>
					<tr>
						<th>ס�޵�ַ</th>
						<td colspan="3">
							<html:text name="rs" property="zsdd" styleId="zsdd" style="width: 545px" maxlength="50"/>
						</td>
					</tr>
					<tr>
						<th><font color="red">*</font>�߶���ʼʱ��</th>
						<td>
							<html:text name="rs" property="zdkssj" styleId="zdkssj"
								onclick="return showCalendar('zdkssj','ymmdd');" 
								readonly="true"
							/>
						</td>
						<th><font color="red">*</font>�߶�����ʱ��</th>
						<td>
							<html:text name="rs" property="zdjssj" styleId="zdjssj"
								onclick="return showCalendar('zdjssj','ymmdd');" 
								readonly="true"
							/>
						</td>
					</tr>
					<tr>
						<th>
							��������
							<br />
							<font color="red">(����¼��500��)</font>						
						</th>
						<td colspan="3">
							<html:textarea name='rs' property="sqly"  
								styleId="sqly" rows="5" 
								style="word-break:break-all;width:545px" 
								onblur="chLeng(this,'500')">
							</html:textarea>
						</td>
					</tr>
					<tr>
						<th>
							��ע
							<br />
							<font color="red">(����¼��500��)</font>		</th>
						<td colspan="3">
							<html:textarea name='rs' property="bz"  
								styleId="bz" rows="5" 
								style="word-break:break-all;width:545px" 
								onblur="chLeng(this,'500')">
							</html:textarea>
						</td>
					</tr>
					<tr>
						<th>��������</th>
						<td colspan="3">
							<a href="gyglZjjs.do?method=downLoadFile" target="_blank">
								<font color="blue">�������</font>
							</a>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan='4'>
							<div class="btn">
								<!-- ���� -->
								<button onclick="confirmInfo('�Ƿ񱣴��������룿',saveXszd);"
								<logic:notEqual name="rs" property="bjsh" value="δ���">disabled="disabled"</logic:notEqual>
								>
									<bean:message key="lable.btn_bc_space" />
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- ѧ��������Ϣ end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
				
		</html:form>
	</body>
</html>