<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="������� zfsoft" />
		<base target="_self">
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript">
	
	   function jxglrwbmxxsh(){
	       var pkValue = $("xh").value;
	       var xxsh = $("xxsh").value;
	       var btgyy = $("btgyy").value;
	       
	       
	       
	       if(xxsh=="δͨ��X"&&btgyy==""){
	          alert("����д��ͨ��ԭ��");
	          return false;       
	       }
	       
	       if(xxsh=="��ͨ����"&&btgyy!=""){
	          alert("����Ҫ��д��ͨ��ԭ��");
	          return false;
	       }
	       
	       if(xxsh=="δ���"){
	          if(confirm("ȷ��Ҫ�øü�¼����δ���״̬��")){
	          BatAlert.showTips('�����ύ����ȴ�...');
	              document.forms[0].action="jxglrwbmxxsh.do?doType=xxsh&pkValue="+pkValue;
                  document.forms[0].submit();
	          }else{
	            return false;
	          }
	       }
	       BatAlert.showTips('�����ύ����ȴ�...');
	       document.forms[0].action="jxglrwbmxxsh.do?doType=xxsh&pkValue="+pkValue;
           document.forms[0].submit();
	   }
	
	function jxglrwbmzbxxsh(){
	       var pkValue = $("xh").value;
	       var xxsh = $("zbbgssh").value;
	       var btgyy = $("zbbgsshbtgyy").value;
	              
	       if(xxsh=="δͨ��X"&&btgyy==""){
	          alert("����д��ͨ��ԭ��");
	          return false;       
	       }
	       
	       if(xxsh=="��ͨ����"&&btgyy!=""){
	          alert("����Ҫ��д��ͨ��ԭ��");
	          return false;
	       }
	       
	       if(xxsh=="δ���"){
	          if(confirm("ȷ��Ҫ�øü�¼����δ���״̬��")){
	          BatAlert.showTips('�����ύ����ȴ�...');
	              document.forms[0].action="jxglrwbmxxsh.do?doType=zbxxsh&pkValue="+pkValue;
                  document.forms[0].submit();
	          }else{
	            return false;
	          }
	       }
	       BatAlert.showTips('�����ύ����ȴ�...');
	       document.forms[0].action="jxglrwbmxxsh.do?doType=zbxxsh&zbbgssh="+xxsh+"&pkValue="+pkValue;
           document.forms[0].submit();
	   }
	</script>

	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<input type="hidden" id="msg" name="msg" value="${msg}"/>
		<html:form action="/jxglrwbmxxsh" method="post">
			<fieldset>
				<legend>
					���������
				</legend>
				<table width="100%" class="tbstyle" id="rwbm">
					<thead>
						<tr height="25">
							<td colspan="7" align="right">
								ѧ�ţ�
								<html:text name="rs" property="xh" readonly="true" />
							</td>
						</tr>
					</thead>
				<tr height="30">
					<td width="12%" align="right">
						������
					</td>
					<td width="15%">
						<bean:write name="rs" property="xm" />
					</td>
					<td width="14%" align="right">
						�Ա�
					</td>
					<td width="15%">
						<bean:write name="rs" property="xb" />
					</td>
					<td width="15%" align="right">
						���壺
					</td>
					<td width="15%">
						<bean:write name="rs" property="mz" />
					</td>
					<td rowspan="4" width="5%" align="center" title="һ����">
						<logic:equal value="�㽭��ҵְҵ����ѧԺ" name="xxmc" scope="session">
								<img border="0" style="height:133px;width:100px;" src="/xgxt/pictures/<bean:write name="rs" property="sfzh" />.jpg">
						</logic:equal>
						<logic:notEqual value="�㽭��ҵְҵ����ѧԺ" name="xxmc" scope="session">
							<logic:equal value="12872" name="xxdm">
								<logic:present name="showsfzh">
									<img border="0" style="height:133px;width:100px;" src="/xgxt/pictures/<bean:write name="rs" property="sfzh" />.jpg">
								</logic:present>
								<logic:present name="showxh">
									<img border="0" style="height:133px;width:100px;" src="/xgxt/pictures/<bean:write name="rs" property="xh" />.jpg">
								</logic:present>
							</logic:equal>
							<logic:notEqual value="12872" name="xxdm">
								<%--�㽭����ְҵ����ѧԺ--%>
								<logic:equal value="12861" name="xxdm">
									<img src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=<bean:write name="rs" property="xh" />"
										border="0" align="absmiddle" style="width:140;height:160" />
								</logic:equal>
								<%--end�㽭����ְҵ����ѧԺ--%>

								<%--���㽭����ְҵ����ѧԺ--%>
								<logic:notEqual value="12861" name="xxdm">
									<%--�ǽ�����Ϣְҵ����ѧԺ--%>
									<logic:notEqual value="13108" name="xxdm">
										<img
											src="<%=request.getContextPath()%>/stuPic.jsp?xh=<bean:write name="rs" property="xh" />"
											border="0" align="absmiddle" style="width:140;height:160" />
									</logic:notEqual>
									<%--������Ϣְҵ����ѧԺ--%>
									<logic:equal value="13108" name="xxdm">
											<img border="0" style="height:133px;width:100px;"
												src="/xgxt/pictures/<bean:write name="rs" property="sfzh" />.jpg">
									</logic:equal>
								</logic:notEqual>
								<%--end���㽭����ְҵ����ѧԺ--%>
							</logic:notEqual>
						</logic:notEqual>
					</td>
				</tr>
				<!-- �ǹ��ݴ�ѧ -->
				<logic:notEqual name="xxdm" value="10657">
				<tr height="30">
					<td align="right">
						���᣺
					</td>
					<td>
						<bean:write name="rs" property="jg" />
					</td>
					<td align="right">
						�������£�
					</td>
					<td>
						<bean:write name="rs" property="csny" />
					</td>
					<td align="right">
						�����ţ���
					</td>
					<td>
						<bean:write name="rs" property="zzmm" />

					</td>
				</tr>
				<tr height="30">
					<td align="right">
						��Уʱ�䣺
					</td>
					<td>
						<bean:write name="rs" property="rxsj" />
					</td>
					<td align="right">
						רҵ��ϵ����
					</td>
					<td>
						<bean:write name="rs" property="zymc" />
					</td>
					<td align="right">
						��ͥ����
					</td>
					<td>
						<bean:write name="rs" property="jtcs" />
					</td>
				</tr>
				</logic:notEqual>
				<!-- ���ݴ�ѧ -->
				<logic:equal name="xxdm" value="10657">
				<tr height="30">
					<td align="right">
						���᣺
					</td>
					<td>
						<html:text name="rs" property="jg" />
					</td>
					<td align="right">
						�������£�
					</td>
					<td>
						<html:text name="rs" property="csny" />
					</td>
					<td align="right">
						��ͥ����
					</td>
					<td>
						<html:text name="rs" property="jtcs" />
					</td>
				</tr>
				<tr height="30">
					<td align="right">
						�꼶��
					</td>
					<td>
						<html:select name="rs" property="nj" style="" onchange="initZyList()">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj" labelProperty="nj" />
						</html:select>
					</td>
					<td align="right">
						<bean:message key="lable.xb" />��
					</td>
					<td>
						<html:select name="rs" property="xydm" style="" styleId="xy" onchange="initZyList();">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm" labelProperty="xymc" />
						</html:select>
					</td>
					<td align="right">
						רҵ��ϵ����
					</td>
					<td>
						<html:select name="rs" property="zydm" style="" styleId="zy" onchange="">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm" labelProperty="zymc" />
						</html:select>
					</td>
				</tr>
				<tr height="30">
					<td align="right">
						��Уʱ�䣺
					</td>
					<td>
						<html:text name="rs" property="rxsj" />
					</td>
					<td align="right">
						�����ţ���
					</td>
					<td>
						${rs.zzmm }
					</td>
					<td align="right">
						�뵳(��)ʱ�䣺
					</td>
					<td>
						<html:text name="rs" property="rdsj" />
					</td>
				</tr>
				<tr height="30">
					<td align="right">
						�������ڵأ�
					</td>
					<td colspan="6">
						<html:select name="rs" property="hkshen" styleId="hkshen" onchange="loadShi('hkshen','hkshi','hkxian');" disabled="true">
							<html:option value="">--��ѡ��--</html:option>
							<html:options collection="ssList" property="ssdm" labelProperty="ssmc" />
						</html:select>
						<html:select name="rs" property="hkshi" styleId="hkshi"
							onchange="loadXian('hkshi','hkxian')" disabled="true">
							<html:options collection="hkshiList" property="shidm" labelProperty="shimc" />
						</html:select>
						<html:select name="rs" property="hkxian" styleId="hkxian" disabled="true">
							<html:options collection="hkxianList" property="xiandm" labelProperty="xianmc" />
						</html:select>
					</td>
				</tr>
				<tr height="30">
					<td align="right">
						��ѧǰ�Ļ������ڵأ�
					</td>
					<td colspan="6">
						<html:select name="rs" property="syshen" styleId="syshen" onchange="loadShi('hkshen','hkshi','hkxian');" disabled="true">
							<html:option value="">--��ѡ��--</html:option>
							<html:options collection="ssList" property="ssdm" labelProperty="ssmc" />
						</html:select>
						<html:select name="rs" property="syshi" styleId="syshi"
							onchange="loadXian('hkshi','hkxian')" disabled="true">
							<html:options collection="syshiList" property="shidm" labelProperty="shimc" />
						</html:select>
						<html:select name="rs" property="syxian" styleId="syxian" disabled="true">
							<html:options collection="syxianList" property="xiandm" labelProperty="xianmc" />
						</html:select>
					</td>
				</tr>
				</logic:equal>
				<!-- end -->
				<tr height="30">
					<td align="right">
						�к��س���
					</td>
					<td>
						<bean:write name="rs" property="tc" />
					</td>
					<td align="right">
						ѧ��֤�ţ�
					</td>
					<td>
						<bean:write name="rs" property="xszh" />
					</td>
					<td align="right">
						���֤�ţ�
					</td>
					<td>
						<bean:write name="rs" property="sfzh" />
					</td>
				</tr>
				<!-- �ǹ��ݴ�ѧ -->
				<logic:notEqual name="xxdm" value="10657">
				<tr height="30">
					<td align="right">
						��ͥסַ��
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jtzz" />
					</td>
					<td align="right">
						��ϵ�绰��
					</td>
					<td>
						<bean:write name="rs" property="lxdh" />
					</td>
					<td align="center">
					</td>
				</tr>
				<tr height="30">
					<td align="center">
						�Ƿ��ܹ�
						<br>
						����ѵ��
					</td>
					<td align="center">
						<bean:write name="rs" property="sfjsxl" />
					</td>
					<td align="center">
						��ѵ����
						<br>
						��ʱ��
					</td>
					<td colspan="2">
						<html:textarea name="rs" property="sxnr" style="width:100%"
							rows="4" readonly="true" />
					</td>
					<td align="right">
						���������
					</td>
					<td>
						<html:textarea name="rs" property="jcqk" style="width:100px"
							rows="4" readonly="true" />
					</td>
				</tr>
				</logic:notEqual>
				<!-- ���ݴ�ѧ -->
				<logic:equal name="xxdm" value="10657">
				<tr height="30">
					<td align="right">
						��ͥסַ��
					</td>
					<td colspan="3">
						<html:text name="rs" property="jtzz" style="width:100%" />
					</td>
					<td align="right">
						��ϵ�绰��
					</td>
					<td colspan="2">
						<html:text name="rs" property="lxdh"/>
					</td>
				</tr>
				<tr>
					<td align="center">
						�Ƿ��ܹ�
						<br>
						����ѵ��
					</td>
					<td align="left">
						<html:select name="rs" property="sfjsxl" style="width:80px">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
					</td>
					<td align="center" rowspan="2">
						��ѵ����
						<br>
						��ʱ��
					</td>
					<td colspan="2" rowspan="2">
						<html:textarea name="rs" property="sxnr" style="width:100%"
							rows="4" />
					</td>
					<td align="right" rowspan="2">
						���������
					</td>
					<td rowspan="2">
						<html:textarea name="rs" property="jcqk" style="width:100px"
							rows="4" />
					</td>
				</tr>
				<tr>
					<td align="center">
						�Ǽ�����
					</td>
					<td align="left">
						<html:select name="rs" property="lx" style="" onchange="">
							<html:options collection="djlxList" property="dm" labelProperty="mc" />
						</html:select>
					</td>
				</tr>	
				</logic:equal>
				<tr height="30">
					<td align="center">
						��У���֣�
					</td>
					<td colspan="6">
						<html:textarea name="rs" property="zxbx" rows="5"
							style="width:100%" readonly="true" />
					</td>
				</tr>
				<!-- �ǹ��ݴ�ѧ -->
				<logic:notEqual name="xxdm" value="10657">
				<tr height="30">
					<td align="center">
						��ͥ��Ҫ
						<br>
						��Ա
					</td>
					<td colspan="6">
						<html:textarea name="rs" property="jtzycy" rows="5"
							style="width:100%" />
					</td>
				</tr>
				</logic:notEqual>
				<!-- end -->
				<!-- ���ݴ�ѧ -->
				<logic:equal name="xxdm" value="10657">			
					<tr height="30">
						<td align="center" rowspan="4">
							��ͥ��Ҫ
							<br>
							��Ա
						</td>
						<td>
							�뱾�˹�ϵ
						</td>
						<td>
							����
						</td>
						<td colspan="3">
							������λ
						</td>
						<td>
							��ϵ�绰
						</td>
					</tr>
					<tr>
						<td>
							${rs.jtcy1_gx }&nbsp;
						</td>
						<td>
							${rs.jtcy1_xm }&nbsp;
						</td>
						<td colspan="3">
							${rs.jtcy1_gzdz }&nbsp;
						</td>
						<td>
							${rs.jtcy1_lxdh1 }&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							${rs.jtcy2_gx }&nbsp;
						</td>
						<td>
							${rs.jtcy2_xm }&nbsp;
						</td>
						<td colspan="3">
							${rs.jtcy2_gzdz }&nbsp;
						</td>
						<td>
							${rs.jtcy2_lxdh1 }&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							${rs.jtcy3_gx }&nbsp;
						</td>
						<td>
							${rs.jtcy3_xm }&nbsp;
						</td>
						<td colspan="3">
							${rs.jtcy3_gzdz }&nbsp;
						</td>
						<td>
							${rs.jtcy3_lxdh1 }&nbsp;
						</td>
					</tr>
				</logic:equal>
					<tr height="30">
						<td align="center">
							���˼���
						</td>
						<td colspan="6">
							<html:textarea name="rs" property="grjl" rows="15"
								style="width:100%" readonly="true" />
						</td>
					</tr>
					<tr height="30">
						<td rowspan="2" align="center">
							���
							<br>
							�����ף�
						</td>
						<td rowspan="2">
							<bean:write name="rs" property="sg" />
						</td>
						<td rowspan="2" align="center">
							����
							<br>
							��ǧ�ˣ�
						</td>
						<td rowspan="2">
							<bean:write name="rs" property="tz" />
						</td>
						<td rowspan="2" align="center">
							����
						</td>
						<td align="right">
							���ۣ�
						</td>
						<td>
							<bean:write name="rs" property="slright" />
						</td>
					</tr>
					<tr height="30">
						<td align="right">
							���ۣ�
						</td>
						<td>
							<bean:write name="rs" property="slleft" />
						</td>
					</tr>
					<tr height="30">
						<td align="center">
							��ͥ������
							<br>
							��ʷ���
						</td>
						<td colspan="6">
							<html:textarea name="rs" property="jtjgrbs" rows="6"
								style="width:100%" readonly="true" />
						</td>
					</tr>
					<logic:notPresent name="zb">
					<tr>
						<td align="right">
							�Ǽ�ʱ�䣺
						</td>
						<td>
							<bean:write name="rs" property="djsj" />
						</td>
						<td align="right">
							��˽����
						</td>
						<td>
							<html:select name="rs" property="xxsh">
								<html:option value="δ���"></html:option>
								<html:option value="��ͨ����"></html:option>
								<html:option value="δͨ��X"></html:option>
							</html:select>
						</td>
						<td align="center">
							����ˣ�
							<bean:write name="rs" property="xxshr" />
						</td>
						<td align="right">
							���ʱ�䣺
						</td>
						<td>
							<bean:write name="rs" property="shsj" />
						</td>
					</tr>
					<tr>
						<td align="right">
							��ͨ��ԭ��
						</td>
						<td colspan="6">
							<html:textarea name="rs" property="btgyy" rows="5"
								style="width:100%" />
						</td>
					</tr>
					</logic:notPresent>
					<logic:present name="zb">
					<tr>
						<td align="right">
							�Ǽ�ʱ�䣺
						</td>
						<td>
							<bean:write name="rs" property="djsj" />
						</td>
						<td align="right">
							��˽����
						</td>
						<td>
							<html:select name="rs" property="zbbgssh">
								<html:option value="δ���"></html:option>
								<html:option value="��ͨ����"></html:option>
								<html:option value="δͨ��X"></html:option>
							</html:select>
						</td>
						<td align="center">
							����ˣ�
							<bean:write name="rs" property="zbbgsshr" />
						</td>
						<td align="right">
							���ʱ�䣺
						</td>
						<td>
							<bean:write name="rs" property="zbbgsshsj" />
						</td>
					</tr>
					<tr>
						<td align="right">
							��ͨ��ԭ��
						</td>
						<td colspan="6">
							<html:textarea name="rs" property="zbbgsshbtgyy" rows="5"
								style="width:100%" />
						</td>
					</tr>
					</logic:present>
					<tr align="center">
						<td colspan="7">
							<logic:notPresent name="zb">
							<button type="button" class="button2" onclick="jxglrwbmxxsh();">
								�ύ
							</button>
							</logic:notPresent>
							<logic:present name="zb">
							<button type="button" class="button2" onclick="jxglrwbmzbxxsh();">
								�ύ
							</button>
							</logic:present>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" type="reset">
								����
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
								onclick="Close();window.dialogArguments.document.getElementById('query_go').click();">
								�ر�
							</button>
						</td>
					</tr>
				</table>
			</fieldset>
			<logic:present name="msg">
				<script>
					alert(''+document.getElementById('msg').value);
					Close();
				</script>
				</logic:present>
			<logic:notEmpty name="xxsh">
				<logic:equal name="xxsh" value="ok">
					<script>
                      alert("��˳ɹ�!");
                      Close();
                      window.dialogArguments.document.getElementById('query_go').click();
                    </script>
				</logic:equal>
				<logic:equal name="xxsh" value="no">
					<script>
                      alert("���ʧ��");
                    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
