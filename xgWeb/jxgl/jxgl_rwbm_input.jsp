<%@ page language="java" contentType="text/html; charset=GBK"%>

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
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script language="javascript">
	function savetheinfo(){   
	var xh = document.getElementById("xh").value;
	var jtzz = document.getElementById("jtzz").value;

	
	if(xh==""){
	alert("ѧ�Ų���Ϊ�գ�");
	return false;
	}
	if(jtzz!=""&&jtzz.length>30){
	alert("��ϵ��ַ���ȹ�������ԣ�");
	return false;
	}
	
	
	  if(confirm("��ȷ�ϼ������")){
	        BatAlert.showTips('�����ύ����ȴ�...');
		 	document.forms[0].action = "jxglrwbm.do?doType=save";
		 	document.forms[0].submit();
		 	return true;
		 	}else{
		 	return false;
		 	}
	}
	
	function returntobegin(){   
		 	document.forms[0].action = "/xgxt/savegrjl.do";
		 	document.forms[0].submit();
	}
	
    function isNumber(s){
	var p = /^(-|\+)?\d+$/;
	return p.test(s); 
    } 
    
      //exclude left and right space; 
	function trim(s){
 		return rtrim(ltrim(s)); 
	}
	//exclude left space; 
	function ltrim(s){
 		return s.replace( /^\s*/, ""); 
	} 
	//exclude right space; 
	function rtrim(s){ 
 		return s.replace( /\s*$/, ""); 
	}
    
    function isEmail(s){
	s = trim(s); 
 	var p = /^[_\.0-9a-z-]+@([0-9a-z][0-9a-z-]+\.){1,4}[a-z]{2,3}$/i; 
 	return p.test(s);
    }
    
    
    function updategrzp(xh){
            showTopWin("jxglzpsc.do?xh="+xh, 500, 280);
    }
    
    
    
    
    
	</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<html:form action="/jxglrwbm" method="post" enctype="multipart/form-data">
			<%@ include file="/jxgl/hiddenValue.jsp"%>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã���ѵ���� - �������� - ���鱨���Ǽ�
				</div>
			</div>
			<logic:notEmpty name="readonly">
				<logic:equal name="readonly" value="readonly">
					<p align="center">
						��ҳ��Ӧ��ѧ������������ԱȨ��Ϊֻ����
					</p>
				</logic:equal>
			</logic:notEmpty>
			<logic:empty name="readonly">
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
					<td width="15%" align="right">
						������
					</td>
					<td width="15%">
						<html:text name="rs" property="xm" />
					</td>
					<td width="14%" align="right">
						�Ա�
					</td>
					<td width="15%">
						<html:text name="rs" property="xb" />
					</td>
					<td width="15%" align="right">
						���壺
					</td>
					<td width="15%">
						<html:select name="rs" property="mz" styleId="mz"
							style="width:100%">
							<html:options collection="mzList" property="mzmc"
								labelProperty="mzmc" />
						</html:select>
					</td>
					<td rowspan="5" width="5%" align="center" title="һ����">
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
						<html:text name="rs" property="jg" />
					</td>
					<td align="right">
						�������£�
					</td>
					<td>
						<html:text name="rs" property="csny" />
					</td>
					<td align="right">
						�����ţ���
					</td>
					<td>
						<html:select name="rs" property="zzmm" styleId="zzmm"
							style="width:100%">
							<html:option value=""></html:option>
							<html:options collection="zzmmList" property="zzmm"
								labelProperty="zzmm" />
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
						רҵ��ϵ����
					</td>
					<td>
						<html:select name="rs" property="zymc" styleId="zymc"
							style="width:100%">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zymc"
								labelProperty="zymc" />
						</html:select>
					</td>
					<td align="right">
						��ͥ����
					</td>
					<td>
						<html:text name="rs" property="jtcs" />
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
						<bean:message key="lable.xsgzyxpzxy" />��
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
						<html:select name="rs" property="zzmm" styleId="zzmm"
							style="width:100%">
							<html:option value=""></html:option>
							<html:options collection="zzmmList" property="zzmm"
								labelProperty="zzmm" />
						</html:select>

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
					<td colspan="5">
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
					<td align="center" rowspan="4">
						<button type="button" class="button2" onclick="updategrzp(<bean:write name="rs" property="xh"/>)">
							�ϴ���Ƭ
						</button>
					</td>
				</tr>
				<tr height="30">
					<td align="right">
						��ѧǰ�Ļ������ڵأ�
					</td>
					<td colspan="5">
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
						<html:text name="rs" property="tc" />
					</td>
					<td align="right">
						ѧ��֤�ţ�
					</td>
					<td>
						<html:text name="rs" property="xszh" />
					</td>
					<td align="right">
						���֤�ţ�
					</td>
					<td>
						<html:text name="rs" property="sfzh" />
					</td>
				</tr>
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
					<td>
						<html:text name="rs" property="lxdh" />
					</td>
				</tr>
				</logic:equal>
				<!-- �ǹ��ݴ�ѧ -->
				<logic:notEqual name="xxdm" value="10657">
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
					<td>
						<html:text name="rs" property="lxdh" />
				</td>			
				</tr>			
				<tr height="30">
					<td align="center">
						�Ƿ��ܹ�
						<br>
						����ѵ��
					</td>
					<td align="center">
						<html:select name="rs" property="sfjsxl" style="width:80px">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
					</td>
					<td align="center">
						��ѵ����
						<br>
						��ʱ��
					</td>
					<td colspan="2">
						<html:textarea name="rs" property="sxnr" style="width:100%"
							rows="4" />
					</td>
					<td align="right">
						���������
					</td>
					<td>
						<html:textarea name="rs" property="jcqk" style="width:100px"
							rows="4" />
					</td>
				</tr>
				</logic:notEqual>
				<!-- ���ݴ�ѧ -->
				<logic:equal name="xxdm" value="10657">
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
						<html:select property="lx" style="" onchange="">
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
							style="width:100%" />
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
							style="width:100%" />
					</td>
				</tr>
				<tr height="30">
					<td rowspan="2" align="center">
						���
						<br>
						�����ף�
					</td>
					<td rowspan="2">
						<html:text name="rs" property="sg" />
					</td>
					<td rowspan="2" align="center">
						����
						<br>
						��ǧ�ˣ�
					</td>
					<td rowspan="2">
						<html:text name="rs" property="tz" />
					</td>
					<td rowspan="2" align="center">
						����
					</td>
					<td align="right">
						���ۣ�
					</td>
					<td>
						<html:text name="rs" property="slright" style="width:100px" />
					</td>
				</tr>
				<tr height="30">
					<td align="right">
						���ۣ�
					</td>
					<td>
						<html:text name="rs" property="slleft" style="width:100px" />
					</td>
				</tr>
				<tr height="30">
					<td align="center" colspan="2">
						��ͥ�����˲�ʷ���
					</td>
					<td colspan="5">
						<html:textarea name="rs" property="jtjgrbs" rows="6"
							style="width:100%" />
					</td>
				</tr>
				<!-- begin ���ΰ 2009/3/30 -->
				<logic:equal value="11647" name="xxdm" scope = "session">
				<tr height="30">
					<td align="center" colspan="2">
						��ͥ���
					</td>
					<td colspan="6">
						<html:textarea name="rs" property="jtyj" rows="6"
							style="width:100%" />
					</td>
				</tr>
				</logic:equal>
				<!-- end ���ΰ 2009/3/30 -->
			</table>
			<div class="buttontool" align="center">
	
						<button type="button" class="button2" onclick="savetheinfo();" style="width:80px" >�ύ</button>&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" type="reset" style="width:80px">�ر�</button>
			</div>
			</logic:empty>

			<logic:notEmpty name="save">
				<logic:equal name="save" value="ok">
					<script>
                        alert("�ύ�ɹ���");
                   </script>
				</logic:equal>
				<logic:equal name="save" value="no">
					<script>
                        alert("�ύʧ�ܣ������Ƿ��ظ��ύ");
                    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
