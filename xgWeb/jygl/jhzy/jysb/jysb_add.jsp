<%@ include file="/syscommon/pagehead.ini"%>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<html>
	<head>
		<title>��ҵ������Ϣϵͳ</title>
				<%
				response.setHeader("Pragma","No-cache");
				response.setHeader("Cache-Control","no-cache");
				response.setDateHeader("Expires", 0);
				%>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta name="Copyright" content="������� zfsoft" />
	</head>
	<script language="javascript">
	function add(){
      var xymc = document.getElementById("xydm").value;   
      var sbsjd = document.getElementById("sbsjd").value; 
      var bynf = document.getElementById("bynf").value; 
      var xl = document.getElementById("xl").value;
     
      if(xymc==""){
      alert("<bean:message key="lable.xsgzyxpzxy" />���Ʋ���Ϊ�գ�");
      return false;
      }
      if(sbsjd==""){
      alert("�ϱ�ʱ��㲻��Ϊ�գ�");
      return false;
      }
      if(bynf==""){
      alert("��ҵ��ݲ���Ϊ�գ�");
      return false;
      }
      if(xl==""){
      alert("ѧ������Ϊ�գ�");
      return false;
      }
        
		 document.forms[0].action = "/xgxt/jhzyjysb.do?method=jysbadd&doType=save";
		 document.forms[0].submit();
		 BatAlert.showTips('�����ύ�����Ժ�...');
    }
    function jsl(type){
        var qyl = 0;
        var ypl = 0;
        var jyl = 0;
        var byrs = new Number(document.getElementById("byrs").value);
        var qyrs = new Number(document.getElementById("qyrs").value);
        var yprs = new Number(document.getElementById("yprs").value);
        var jyrs = new Number(document.getElementById("jyrs").value);
		if(type="qyrs"){
			if(qyrs>byrs){
				document.getElementById("qyrs").value="";
				alert("ǩԼ�������ܴ��ڱ�ҵ����");
				return;
			}else{
				if(qyrs!=0 || byrs!=0){
				qyl = (qyrs/byrs*100).toString().substring(0, 4);
				}
				document.getElementById("qyl").value=qyl;
			}
		}
		if(type="yprs"){
			if(yprs>byrs){
				document.getElementById("yprs").value="";
				alert("ӦƸ�������ܴ��ڱ�ҵ����");
				return;
			}else{
				if(qyrs!=0 || byrs!=0){
				ypl = (yprs/byrs*100).toString().substring(0, 4);
				}
				document.getElementById("ypl").value=ypl;
			}
		}
		if(type="jyrs"){
			if(jyrs>byrs){
				document.getElementById("jyrs").value="";
				alert("��ҵ�������ܴ��ڱ�ҵ����");
				return;
			}else{
				if(jyrs!=0 || byrs!=0){
				jyl = (jyrs/byrs*100).toString().substring(0, 4);
				}
				document.getElementById("jyl").value=jyl;
			}
		}
    }
	</script>
	<body onload="jsl('qyrs');jsl('yprs');jsl('jyrs')">
		<html:form action="/jhzyjysb" method="post">
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="center" colspan="4" height="">
							<b>��ҵ�ϱ�</b>
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right">
						<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />����
					</td>
					<td colspan="1">
						<input id="xydt" name="xydt" value="${bmmc }" readonly="readonly"/>
						<html:select property="xydm" style=";display:none"
									styleId="xy" onchange="">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm" labelProperty="xymc" />
						</html:select>
					</td>
					<td align="right">
						<font color="red">*</font>רҵ����
					</td>
					<td>
						<html:select property="zydm" style="width:;"
									styleId="zydm" onchange="">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm" labelProperty="zymc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>��ҵ���
					</td>
					<td>
						<html:select property="bynf" style="width:130px"
									styleId="bynf" onchange="">
							<html:option value=""></html:option>
							<html:options collection="ndList" property="nd" labelProperty="nd" />
						</html:select>
					</td>
					<td align="right">
						<font color="red">*</font>�ϱ�ʱ���
					</td>
					<td>
						<html:text property="sbsjd" readonly="true" onclick="return showCalendar('sbsjd','y-mm-dd');"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>ѧ��
					</td>
					<td>
						<html:select property="xl">
							<html:option value=""></html:option>
							<html:option value="������">������</html:option>
							<html:option value="ר����">ר����</html:option>
							<html:option value="��ְ��">��ְ��</html:option>
						</html:select>
					</td>
					<td align="right">
						��ҵ����
					</td>
					<td>
						<html:text  property="byrs" maxlength="8" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
				</tr>
				<tr>
					<td align="right">
						ǩԼ����
					</td>
					<td>
						<html:text  property="qyrs" maxlength="8" onblur="jsl('qyrs');" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
					<td align="right">
						ǩԼ��
					</td>
					<td>
						<html:text  property="qyl" value="0%" readonly="true"/>%
					</td>
				</tr>
				<tr>
					<td align="right">
						ӦƸ����
					</td>
					<td>
						<html:text  property="yprs" onblur="jsl('yprs');" maxlength="8" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
					<td align="right">
						ӦƸ��
					</td>
					<td>
						<html:text  property="ypl" value="0%" readonly="true"/>%
					</td>
				</tr>
				<tr>
					<td align="right">
						��ҵ����
					</td>
					<td>
						<html:text  property="jyrs" onblur="jsl('jyrs');" maxlength="8" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
					<td align="right">
						��ҵ��
					</td>
					<td>
						<html:text  property="jyl" value="0%" readonly="true"/>%
					</td>
				</tr>
				<tr>
					<td align="right">
						�������
					</td>
					<td>
						<html:text  property="lhrs" maxlength="8" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
					<td align="right" nowrap="nowrap"">
						��������ѧ����
					</td>
					<td>
						<html:text  property="cghsxrs" maxlength="8" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
				</tr>
				
			</table>
			<div align="center">
				<button class="button2" onclick="add()" style="width:80px">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();" style="width:80px">
					�� ��
				</button>
			</div>
		</html:form>

		<logic:notEmpty name="save">
			<logic:equal name="save" value="yes">
				    <script>
                      alert("�ύ�ɹ���");
                     // Close();
                     // window.dialogArguments.document.getElementById('search_go').click();
                    </script>
			</logic:equal>
			<logic:equal name="save" value="no">
				<script>
                      alert("����ʧ��!");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="istj">
			<logic:equal name="istj" value="istj">
				<script>
                      alert("��רҵ�Ѿ��ϱ��˸ñ�ҵ��ݵ���Ϣ!");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="nosqsj">
			<logic:equal value="nosqsj" name="nosqsj">
				<script>
				alert("�ϱ�ʱ�䲻��ѧУ���õ�ʱ���ڣ������ϱ�!");
				</script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="noxy">
			<logic:equal value="noxy" name="noxy">
				<script>
				alert("ֻ��<bean:message key="lable.xsgzyxpzxy" />�û��ſ����ϱ�!");
				</script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>

