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
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">

</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/nbcsShgzgl" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow">��ǰ����λ�ã���Ṥ�� - ��Ϣά�� - ���ʵ��</span>
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
                       alert("�������ѧ����Ч!");
                    </script>
				</logic:equal>
				<input type="hidden" id="act" name="act"
					value="<bean:write name="act" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url"
					value="/nbcsShgzgl.do?method=shsjAdd" />
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="4" align="center">
								�����Ϣά��
							</td>
						</tr>
					</thead>
					<tr>
						<td align="right">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left">
							<html:text name='rs' property="xh" readonly="readonly"
								styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<logic:equal value="add" name="act">
							<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
							</logic:equal>
						</td>
						<td align="right">
							<font color="red">*</font>ѧ�꣺
						</td>
						<td align="left">
							<html:select name='rs' property="xn" style="width:100px"
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							������
						</td>
						<td align="left">
							<html:text name='rs' property="xm" styleId="xm" />
						</td>
						<td align="right">
							<font color="red">*</font>ѧ�ڣ�
						</td>
						<td align="left">
							<html:select name='rs' property="xq" style="width:100px"
								styleId="xq">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							�Ա�
						</td>
						<td align="left">
							<html:text name='rs' property="xb" styleId="xb" />
						</td>
						<td align="right">
							<font color="red">*</font>������ʣ�
						</td>
						<td align="left">
							<html:select name="rs" property="hdxz" styleId="hdxz">
								<html:option value=""></html:option>
								<html:option value="0">����</html:option>
								<html:option value="1">����������</html:option>
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							�꼶��
						</td>
						<td align="left">
							<html:text name='rs' property="nj" styleId="nj" />
						</td>
						<td align="right">
							ʵ��������
						</td>
						<td align="left">
							<html:text name='rs' property="sjts" styleId="sjts" maxlength="2"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left">
							<html:text name='rs' property="xymc" styleId="xy" />
						</td>
						<td align="right">
							���˽����
						</td>
						<td align="left">
							<html:text name='rs' property="khjg" styleId="kh"  maxlength="30"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							רҵ��
						</td>
						<td align="left">
							<html:text name='rs' property="zymc" styleId="zy" />
						</td>
						<td align="right">
							�༶��
						</td>
						<td align="left">
							<html:text name='rs' property="bjmc" styleId="bj" />
						</td>
					</tr>
					<tr>
						<td align="right">
							����������
							<br>
							<div style="color: red"><��300��>&nbsp;&nbsp;</div>
						</td>
						<td align="left" colspan="3">
							<html:textarea name='rs' property='shry' styleId="shry" style="width:99%"
								rows='5' />
						</td>
					</tr>
					<tr align="left">
						<td align="right"><font color="red">*</font>
							�����Ŀ��
							<br>
							<div style="color: red"><��50��>&nbsp;&nbsp;</div>
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='hdxm' styleId='hdxm' style="width:99%"
								rows='5' />
						</td>
					</tr>
					<tr align="left">
						<td align="right">
							������ݣ�
							<br>
							<div style="color: red"><��300��>&nbsp;&nbsp;</div>
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='hdnr' styleId='hdnr' style="width:99%"
								rows='5' />
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
				<logic:notEqual value="view" name="act">
					<button class="button2" onclick="addSave('xh-xn-xq-hdxz-hdxm')"
						style="width:80px" id="buttonSave">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
				</logic:notEqual>	
					<button class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
					
				</div>
			</logic:notEmpty>
			<logic:equal value="true" name="done">
			  <script type="text/javascript">
				   alert("�����ɹ���");
				   Close();
				   dialogArgumentsQueryChick();
			  </script>
		   </logic:equal>
		   <logic:equal value="false" name="done">
			   <script type="text/javascript">
				   alert("����ʧ��,��ϵͳ���Ѵ������\"*\"����Ŀ��ͬ�ļ�¼��������������ݺ����ύ��");
			   </script>
		   </logic:equal>
		</html:form>
		<script type="text/javascript">
		 function addSave(mustFill){	           
	          var eles = mustFill.split("-");
	          for (i = 0; i < eles.length; i++) {
		           if (document.getElementById(eles[i]).value == "") {
			           alert("�뽫��\"*\"�ŵ���Ŀ����������");
			           return false;
		           }		
	          }
	          if($("shry").value.length>300){
	              alert("�����ƺ��������ܳ���300�֣�");
	              return false;
	          }
	          if($("hdxm").value.length>50){
	              alert("���Ŀ�������ܳ���50�֣�");
	              return false;
	          }
	          if($("hdnr").value.length>300){
	              alert("������������ܳ���300�֣�");
	              return false;
	          }
             
<%--	          var pkValue=$("lddm").value+lz;--%>
<%--	          getSztzData.getInfoEx("lzxxb","lddm||lz",pkValue,"sfzz='��'",function(str){--%>
<%--		         if(str){		         	--%>
<%--		            alert("����Ŀǰ����ְ��¥¥���������ظ���ӣ�");		           		          			        --%>
<%--		         }else{--%>
              refreshForm("/xgxt/nbcsShgzgl.do?method=shsjAdd&doType=save");                
<%--		         }--%>
<%--    	      });	 	           --%>
	     }
		</script>
	</body>
</html>
