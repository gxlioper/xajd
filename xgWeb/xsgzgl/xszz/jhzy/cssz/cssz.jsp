<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" defer="defer">
		function saveSpl(){
			var array = jQuery(".sj_class");
			var flag = true;
			jQuery(array).each(function (i,n) {
				if (jQuery(n).val()=="" || jQuery(n).val()==null) {
					flag = false;
					alertError("��*���ֶα�����д��");
					return false;	
				}
			});
			if (flag) {
				var xn = jQuery('#xn').val();
				var jtqkdzkssj   = jQuery("#jtqkdzkssj").val(); 
				var jtqkdzjssj   = jQuery("#jtqkdzjssj").val();
				var knssqkssj    = jQuery("#knssqkssj").val();
				var knssqjssj    = jQuery("#knssqjssj").val();

				var lzjxjsqkssj  = jQuery("#lzjxjsqkssj").val();
				var lzjxjsqjssj  = jQuery("#lzjxjsqjssj").val();

				var gjzxjsqkssj  = jQuery("#gjzxjsqkssj").val();
				var gjzxjsqjssj  = jQuery("#gjzxjsqjssj").val();
				
				
				if (jtqkdzjssj < jtqkdzkssj) {
					alertError("��ͥ�������ʱ��������ڿ�ʼʱ�䣡");
					return false;
				}
				if (knssqjssj < knssqkssj) {
					alertError("�������϶��������ʱ��������ڿ�ʼʱ�䣡");
					return false;
				}
				if (lzjxjsqjssj < lzjxjsqkssj) {
					alertError("������־��ѧ���������ʱ��������ڿ�ʼʱ�䣡");
					return false;
				}
			
				if (gjzxjsqjssj < gjzxjsqkssj) {
					alertError("������ѧ���������ʱ��������ڿ�ʼʱ�䣡");
					return false;
				}
			
				refreshForm('xszz_jhzy_cssz.do?act=save');
			}
		}
		</script>
	</head>
	<body onload="" >
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>ѧ������ - �������� - ��������</a>
			</p>
		</div>
		<!-- ���� end-->
		
		<!-- ��ʾ��Ϣ end-->
<!--		<div class="prompt">-->
<!--			<h3>-->
<!--				<span>ϵͳ��ʾ��</span>-->
<!--			</h3>-->
<!--			<p>-->
<!--				-->
<!--			</p>-->
<!--		</div>-->
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/csszGl" method="post">
			
			<!-- ά����Ϣ -->
			<table class="formlist" width="">
				<thead onclick="">
					<tr>
						<th colspan="2">
							<span>ʱ������</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="30%">
							<font color="red">*</font>
							����ѧ��
						</th>
						<td width="">
							<html:select property="xn" styleClass="sj_class" styleId="xn" style="width:155px" value="${rs.xn}">
								<html:options collection="xnList" property="xn" labelProperty="xn"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<th width="30%">
							<font color="red">*</font>
							��ͥ���������ʼʱ��
						</th>
						<td width="">
							<html:text property="jtqkdzkssj" styleId="jtqkdzkssj" styleClass="sj_class" onclick="return showCalendar('jtqkdzkssj','y-mm-dd');" 
							onblur="dateFormatChg(this)" style="cursor:hand;" value="${rs.jtqkdzkssj}"></html:text>
							->
							<html:text property="jtqkdzjssj" styleId="jtqkdzjssj" styleClass="sj_class" onclick="return showCalendar('jtqkdzjssj','y-mm-dd');" 
							onblur="dateFormatChg(this)" style="cursor:hand;" value="${rs.jtqkdzjssj}"></html:text>
						</td>
					</tr>
					<tr>
						<th width="30%">
							<font color="red">*</font>
							�������϶�������ʼʱ������
						</th>
						<td width="">
							<html:text property="knssqkssj" styleId="knssqkssj" styleClass="sj_class" onclick="return showCalendar('knssqkssj','y-mm-dd');" 
							onblur="dateFormatChg(this)" style="cursor:hand;" value="${rs.knssqkssj}"></html:text>
							->
							<html:text property="knssqjssj" styleId="knssqjssj" styleClass="sj_class" onclick="return showCalendar('knssqjssj','y-mm-dd');" 
							onblur="dateFormatChg(this)" style="cursor:hand;" value="${rs.knssqjssj}"></html:text>
						</td>
					</tr>
					<tr>
						<th width="30%">
							<font color="red">*</font>
							������־��ѧ��������ʼʱ������
						</th>
						<td width="">
							<html:text property="lzjxjsqkssj" styleId="lzjxjsqkssj" styleClass="sj_class" onclick="return showCalendar('lzjxjsqkssj','y-mm-dd');" 
							onblur="dateFormatChg(this)" style="cursor:hand;" value="${rs.lzjxjsqkssj}"></html:text>
							->
							<html:text property="lzjxjsqjssj" styleId="lzjxjsqjssj" styleClass="sj_class" onclick="return showCalendar('lzjxjsqjssj','y-mm-dd');" 
							onblur="dateFormatChg(this)" style="cursor:hand;" value="${rs.lzjxjsqjssj}"></html:text>
						</td>
					</tr>
					<tr>
						<th width="30%">
							<font color="red">*</font>
							������ѧ��������ʼʱ������
						</th>
						<td width="">
							<html:text property="gjzxjsqkssj" styleId="gjzxjsqkssj" styleClass="sj_class" onclick="return showCalendar('gjzxjsqkssj','y-mm-dd');" 
							onblur="dateFormatChg(this)" style="cursor:hand;" value="${rs.gjzxjsqkssj}"></html:text>
							->
							<html:text property="gjzxjsqjssj" styleId="gjzxjsqjssj" styleClass="sj_class" onclick="return showCalendar('gjzxjsqjssj','y-mm-dd');" 
							onblur="dateFormatChg(this)" style="cursor:hand;" value="${rs.gjzxjsqjssj}"></html:text>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan='2'>
							<div class="btn">
								<!-- ���� -->
								<button type="button" onclick="saveSpl();return false;">
									����
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- ά����Ϣ end-->
		</html:form>
		<logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					alertInfo("����ʧ�ܣ�");
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					alertInfo("�����ɹ���");
					</script>
				</logic:equal>
		</logic:notEmpty>
	</body>
</html>