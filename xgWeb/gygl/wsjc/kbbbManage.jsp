<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/gygl/gyglTyFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
		<script language="javascript" src="js/gygl/wsjc.js"></script>
		<script language="javascript" src="js/pclUtil.js"></script>
		<script language="javascript">
		//�����հױ���
		function expKbbb(){
			var url = "/xgxt/commWsjc.do?method=kbbbManage&doType=exp";

			var xy = $("xy").value;
			var xqdm = $("xqdm").value;
			var lddm = $("lddm").value;
			var cs = $("cs").value;
			var qsh = $("qsh").value;
			var zc = "";
			
			if($("zc")){
			 	zc = $("zc").value;
			}
			
			if(xy == "" && lddm == "" && lddm == "" && cs == "" && qsh == "" && zc == "" ){
				if (confirm("δѡ���κ����������������󣬿�����ɵ���ʧ�ܣ�ȷ�ϵ�����")) {
					document.forms[0].action = url;
					document.forms[0].target = "_blank";
					document.forms[0].submit();
					document.forms[0].target = "_self";	
				}
			}else{
				document.forms[0].action = url;
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";	
			}
		}
		</script>
	</head>
	<body onload="">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a><bean:write name="title" /></a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/commWsjc">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>		
			<div class="tab">		
				<!-- ��Ŀ������� -->
				<table class="formlist" border="0" align="center" style="width: 100%">
					<thead>
						<tr>
							<th colspan="2">
								<span>��������</span>
							</th>
						</tr>
					</thead>
					<tbody>		
						<tr style="">
							<th align="right" width="35%">
								����<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left" width="65%">
								<logic:equal value="true" name="isxy">
									<html:hidden property="xydm"/>
									<html:select property="xydm" style="width: 200px" styleId="xy" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc" />
									</html:select>
								</logic:equal>
								<logic:notEqual value="true" name="isxy">
									<html:select property="xydm" style="width: 200px" styleId="xy" onchange="">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc" />
									</html:select>
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th align="right">
								У��
							</th>
							<td align="left">
								<html:select property="xqdm" style="width: 200px" styleId="xqdm" onchange="setLdList()">
									<html:options collection="xqdmList" property="dm" labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right">
								¥��
							</th>
							<td align="left">
								<!-- ��Ԣ�ϰ汾 -->
								<logic:equal name="gyglEdition" value="old">
									<html:select property="lddm" style="width: 200px" styleId="lddm" onchange="setXqList();setCsList();setQsList();">
										<html:options collection="ldList" property="dm" labelProperty="mc" />
									</html:select>
								</logic:equal>
								<!-- ��Ԣ�°汾 -->
								<logic:equal name="gyglEdition" value="new">
									<html:select property="lddm" style="width: 200px" styleId="lddm" onchange="setXqList();setCsList();setQsList();">
										<html:option value="">----��ѡ��----</html:option>
										<html:options collection="ldList" property="lddm" labelProperty="ldmc" />
									</html:select>
								</logic:equal>
							</td>
						</tr>
						<tr>
							<th align="right">
								����
							</th>
							<td align="left">
								<html:select property="cs" style="width: 200px" styleId="cs" onchange="setQsList();">
									<html:options collection="csList" property="dm" labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right">
								���Һ�
							</th>
							<td align="left">
								<html:select property="qsh" style="width: 200px" styleId="qsh" onchange="">
									<html:options collection="qsList" property="dm" labelProperty="mc" />
								</html:select>	
							</td>
						</tr>
						<!-- ������� �������Ϊ�ܵ���� -->
						<logic:equal name="jczq" value="��">
						<tr>
							<th align="right">
								�ܴ�
							</th>
							<td align="left">
								<html:select property="zc" style="width: 200px" styleId="zc" onchange="">
									<html:option value="">----��ѡ��----</html:option>
									<html:options collection="zcList" property="dm" labelProperty="mc" />
								</html:select>	
							</td>
						</tr>
						</logic:equal>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">	
								<div class="bz">
									<font color="blue">
									ע����������Ϣ��<bean:message key="lable.xb" />Ϊ�գ�����������Ƿ������������ģ�鱻���䡣
										</br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										��δѡ���κ��������������������������⵼�µ���ʧ�ܡ�
									</font>
								</div>						
								<div class="btn">
									<button onclick="expKbbb()" id="buttonSave">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>