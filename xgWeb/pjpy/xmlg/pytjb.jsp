<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script type="text/javascript">
   function gsdExp(){
        var xydm = $("xydm").value;
        var zydm = $("zydm").value;
        var bjdm = $("bjdm").value;
        if(xydm==""&&zydm==""&&bjdm==""){
           alert("��ѡ��������");
           return false;
        }
    document.forms[0].action = "/xgxt/xmlgpjpy.do?method=pytjbdc";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
   }   
</script>
<body >
	<html:form action="/xmlgpjpy.do" method="post">
			<input type="hidden" name="zyV" id="zyV" value="" />
		<input type="hidden" name="bjV" id="bjV" value="" />
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰλ��: �������� - ��� - �������-�����ƺŹ�ʾ�������
			</div>
		</div>
		<fieldset>
			<legend>
				����ѡ��
			</legend>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="right" >
							ѧ�꣺
						</td>
						<td align="left" >
							<bean:write name="pjpyXmlgActionForm" property="xn"/>	
						</td>
					</tr>
					
					<tr>
						<td align="right" >
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left" >
							<html:select property="xydm" style="width:175px"
								styleClass="select" styleId="xy"
								onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>												
						</td>
					</tr>	
					<tr>
						<td align="right" >
							רҵ��
						</td>
						<td align="left" >
							<html:select property="zydm" style="width:175px"
								styleClass="select" styleId="zy"
								onchange="initBjList()">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>												
						</td>
					</tr>	
					<tr>
						<td align="right" >
							�༶��
						</td>
						<td align="left" >
							<html:select property="bjdm" style="width:175px"
								styleClass="select" styleId="bj"
								>
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>												
						</td>
					</tr>				
				</thead>					
			</table>
			<center>
			<div class="buttontool" id="btn"	 width="100%">
				<button class="button2" onclick="gsdExp()">
					����Excel
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;">
					�ر�
				</button>
			</div>
		</center>
		</fieldset>
	</html:form>
</body>
