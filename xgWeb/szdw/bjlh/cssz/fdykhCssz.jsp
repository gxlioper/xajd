<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="xgxt.szdw.bjlh.SzkhCssz"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/jxglFunction.js"></script>
	<script type='text/javascript'
		src='/xgxt/dwr/interface/getXmlgSzdwDAO.js'></script>
	<script language="javascript">
	function changeInput(obj){
		checkInputNum(obj);
		if(obj.value=="")return false;
		if(obj == document.getElementById("jskhpfbl")){
			document.getElementById("xskhpfbl").value = 100 - eval(document.getElementById("jskhpfbl").value);
		}else if(obj == document.getElementById("xskhpfbl")){
			document.getElementById("jskhpfbl").value = 100 - eval(document.getElementById("xskhpfbl").value);
		}
		<%--
		if((document.getElementById("xspfyxbl").value)>eval(document.getElementById("xskhpfbl").value)){
			alert("ѧ����Ч���ֱ������ܴ���ѧ�����ֱ�����");
			document.getElementById("xspfyxbl").value="";
			document.getElementById("xspfyxbl").focus();
			return false;
		}
		--%>
		
	}
	function saveCssz(obj){
		var url = '/xgxt/bjlh_cssz.do?method=fdykhCssz&doType=save';
		var khkssj = document.getElementById("khkssj").value;
		var khjssj = document.getElementById("khjssj").value;
		var xxdm = document.getElementById("xxdm").value;
		if(khkssj != "" && khjssj != "" && khkssj > khjssj){
			alert("ʱ����Ⱥ������ȷ����ȷ�ϣ�");
			return false;
		}
		//����ְҵ����ѧԺ���Ի�
		if(xxdm !=null && "13060"==xxdm){
			var khlrjzsj = document.getElementById("khlrjzsj").value;
			if(khlrjzsj<khkssj || khlrjzsj>khjssj){
				alert("����¼���ֹʱ�䲻�ڿ��˿�ʼʱ��Ϳ��˽���ʱ�䷶Χ�ڣ���ȷ�ϣ�");
				return false;
			}
			saveUpdate(url,'xn-khkssj-khjssj-jskhpfbl-xskhpfbl-xspfyxbl-khlrjzsj');
		}else{
			saveUpdate(url,'xn-khkssj-khjssj-jskhpfbl-xskhpfbl-xspfyxbl');
		}
		
	}
</script>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a><bean:write name="title" /> </a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/bjlh_cssz" method="post">
			<div class="tab">
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm }"/>
				<table class="formlist" border="0" align="center"
					style="width: 100%">
					<thead>
						<tr>
							<th colspan="2">
								<span>��������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>Ĭ��ѧ�꣺
							</td>
							<td align="left" style="width: 60%">
								<html:select name="rs" property="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>�����Ƿ�������
							</td>
							<td align="left" style="width: 60%">
								<html:radio name="rs" property="khsfqd" value="1">��</html:radio>
								<html:radio name="rs" property="khsfqd" value="0">��</html:radio>
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>���˿�ʼʱ�䣺
							</td>
							<td >
								<html:text name="rs" property="khkssj" styleId="khkssj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('khkssj','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>���˽���ʱ�䣺
							</td>
							<td >
								<html:text name="rs" property="khjssj" styleId="khjssj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('khjssj','y-mm-dd');" />
							</td>
						</tr>
						<!-- ����ְҵ����ѧԺ���Ի�begin -->
						<logic:equal name="xxdm" value="13060">
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>����¼���ֹʱ�䣺
							</td>
							<td >
								<html:text name="rs" property="khlrjzsj" styleId="khlrjzsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('khlrjzsj','y-mm-dd');" />
							</td>
						</tr>
						</logic:equal>
						<!-- ����ְҵ����ѧԺ���Ի�end -->
						<!-- ��ͨ��ʦ���Ի�begin -->
						<logic:notEqual name="xxdm" value="110501">
						<!-- ������Ա��Ϊallʱû�б������� -->
						<%if("all".equalsIgnoreCase(SzkhCssz.KHRY)){%>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>��ʦ�������ֱ�����
							</td>
							<td align="left" style="width: 50%">
								<html:text name="rs" property="jskhpfbl"
									onblur="changeInput(this)" styleId="jskhpfbl" maxlength="3" />
								%
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>ѧ���������ֱ�����
							</td>
							<td align="left" style="width: 50%">
								<html:text name="rs" property="xskhpfbl"
									onblur="changeInput(this)" styleId="xskhpfbl" maxlength="3" />
								%
							</td>
						</tr>
						<%} %>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>ѧ��������Ч������
							</td>
							<td align="left" style="width: 50%">
								<html:text name="rs" property="xspfyxbl"
									onblur="changeInput(this)" styleId="xspfyxbl" maxlength="3" />
								%
							</td>
						</tr>
						</logic:notEqual>
					</tbody>
					<tfoot>
						<tr>
							<td align="center" colspan="2">							
			        			<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="saveCssz(this);" id="buttonSave">
										����
									</button>
									<button type="button" name="����" type="reset">�� ��</button>									
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<logic:present name="message1">
	<script defer="defer">
		if('${message1}' && '${message1}' != ""){
			
			alertInfo('${message1}');
			
		}
	</script>
</logic:present>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>