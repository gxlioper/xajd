<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>	
		<script type="text/javascript">
		function save(){
			if($("sqkssj") && $("sqkssj").value==""){
				alertError("���뿪ʼʱ�䲻��Ϊ��!");
				return false;
			}
			
			if($("sqjssj") && $("sqjssj").value==""){
				alertError("�������ʱ�䲻��Ϊ��!");
				return false;
			}
			
			if($("shkssj") && $("shkssj").value==""){
				alertError("��˿�ʼʱ�䲻��Ϊ��!");
				return false;
			}
			
			if($("shjssj") && $("shjssj").value==""){
				alertError("��˽���ʱ�䲻��Ϊ��!");
				return false;
			}
			
			if(jQuery("input[name=splc]") && !jQuery("input[name=splc]").prop("checked")){
				alertError("�������̲���Ϊ��!");
				return false;
			}
			
			if($("sqkssj") && $("sqjssj")){
				if(!checkSjTj('sqkssj','���뿪ʼʱ��','sqjssj','�������ʱ��')){
					return false;
				}
			}
			if($("shkssj") && $("shjssj")){
				if(!checkSjTj('shkssj','��˿�ʼʱ��','shjssj','��˽���ʱ��')){
					return false;
				}
			}
			
			
			var url="/xgxt/zxdk_xnmz.do?method=zxdkSz&doType=save";
			refreshForm(url);
		}
		</script>
	</head>
	<body onload="">
		<html:form action="/zxdk_xnmz" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
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
					��ģ�����ڶ�"��ѧ����"ģ�������������á�����"����ʱ��"��"���ʱ��"��"�������"�����á�<br/>
					�����"�������"���޿�ѡ��������̣�������"ϵͳά��-�������̹���-��������"ģ�����������<br/>
					�̵�ά����
				</p>
				<a class="close" title="����"
					onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- ��ʾ��Ϣ end-->
			<div class="tab">
				<table width="100%" border="0" class="formlist">

					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">

									<button class="button2" id="btn_bc" onclick="save();return false;">
										�� ��
									</button>

								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<th colspan="4">
								<span>��������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:16%">
								<font color="red">*</font>���뿪ʼʱ��
							</th>
							<td style="width:34%">
								<input type="text" name="sqkssj"
									class="jssj"
									onclick="return showCalendar('sqkssj','ymmdd');"
									id="sqkssj"
									value="${rs.sqkssj }" />
							</td>
							<th style="width:16%">
								<font color="red">*</font>�������ʱ��
							</th>
							<td style="width:34%">
								<input type="text" name="sqjssj"
									class="jssj"
									onclick="return showCalendar('sqjssj','ymmdd');"
									id="sqjssj"
									value="${rs.sqjssj }" 
									 />
							</td>
						</tr>

						<tr>
							<th style="width:16%">
								<font color="red">*</font>��˿�ʼʱ��
							</th>
							<td id="" style="width:34%">
								<input type="text" name="shkssj"
									class="jssj"
									onclick="return showCalendar('shkssj','ymmdd');"
									id="shkssj"
									value="${rs.shkssj }" />
							</td>

							<th>
								<font color="red">*</font>��˽���ʱ��
							</th>
							<td id="" style="width:34%">
								<input type="text" name="shjssj"
									class="jssj"
									onclick="return showCalendar('shjssj','ymmdd');"
									id="shjssj"
									value="${rs.shjssj }" />
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								<font color="red">*</font>�������
							</th>
							<td colspan="3" style="width:84%">
								<logic:notEmpty name="shlcList">
								<logic:iterate name="shlcList" id="shlc" indexId="index">
									<logic:equal name="rs" property="splc" value="${shlc.lcid}">
										<input type="radio" name="splc" value="${shlc.lcid}" checked="checked"/>${shlc.lcmc}:${shlc.gzgw }<br/>
									</logic:equal>
									<logic:notEqual name="rs" property="splc" value="${shlc.lcid}">
										<input type="radio" name="splc" value="${shlc.lcid}"/>${shlc.lcmc}:${shlc.gzgw }<br/>
									</logic:notEqual>
								</logic:iterate>
								</logic:notEmpty>
								<logic:empty name="shlcList">
									<font color="red">����ά����ѧ����ģ����������</font>
								</logic:empty>
							</td>
						</tr>
					</tbody>
				</table>
			</div>


			<logic:present name="message">
				<script type="text/javascript">
					alertInfo("${message}!");
				</script>
			</logic:present>
			<script type="text/javascript" defer="defer">
				if($("shlc_0")){
					$("shlc_0").checked=true;
				}
				
				
			</script>
		</html:form>
	</body>
</html>
