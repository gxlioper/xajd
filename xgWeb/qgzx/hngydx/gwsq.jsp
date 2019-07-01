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
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript'src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript'src='/xgxt/dwr/interface/cqkjFunc.js'></script>	
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script type="text/javascript">
	function getDw(){
		var pk = document.getElementById("xmdm").value;
		if(pk!=null && pk!=""){
		getOtherData.getDwmc(pk,function(data){
			document.getElementById("sqdw").value = data[0];
			document.getElementById("fzr").value = data[1];
			document.getElementById("gwlxdh").value = data[2];
		});
		}		
	}
	function getFzr(){
		var pk = document.getElementById("gwdm").value;
		if(pk!=null && pk!=""){
			getOtherData.getDwmc(pk,function(data){
				if($("fzr")){
					document.getElementById("fzr").value = data[1];
				}
				if($("dwlxdh")){
					document.getElementById("dwlxdh").value = data[2];
				}
				
			});
			//��ɳ����Ҫ�жϸø�λ�Ƿ��ǡ�־Ը���񡱵�
			if($("xxdm").value == "10827"){
				cqkjFunc.isGwZyfw(pk,function(data){
					if(data == "1"){
					//��ʾ��־Ը����ĸ�λ
						document.getElementById("bc").innerHTML="<font color='red'>�ø�λ[־Ը����]</font>"
					}else{
						document.getElementById("bc").innerHTML= "";
					}
				});
			}
		}		
	}
	
	function getSqtj(){
		var pk = "GWDM||'-'||GWSBSJ";
		var pkValue = document.getElementById("xmdm").value;
		var str;
		if(pk!=null && pk!=""){
			cqkjFunc.getSqtjString(pk,pkValue,function(data){
				document.getElementById("sqtj").innerHTML = data[0];
				if(data[1] == "1"){
					//����һ��ѡ��
					//djsj();
				}
				if(data[1] == "0"){
					//document.getElementById("sfdjsj").innerHTML = "";
					//document.getElementById("sfdjsjInput").innerHTML = "";
				}
			});
		}	
	}
	
function saveXsgw(mustFill){
	var eles = mustFill.split("-");
	for (i = 0; i < eles.length; i++) {
		if (document.getElementById(eles[i]).value == "") {
			alert("�뽫��\"*\"�ŵ���Ŀ����������");
			return false;
		}
	}
	var isModi = "";
	if($("isModi")){
		isModi = document.getElementById("isModi").value;
	}
	if(isModi == "modi"){
	    var xmdmmodi = document.getElementById("xmdmmodi").value;
		refreshForm('xsqgzx.do?method=modXsgwInfo&gwdm=' + xmdmmodi);
	}else{
		var xh = document.getElementById("xh").value;
		var gwdm = document.getElementById("gwdm").value;		
		var query = xh + "-" + gwdm;
		getOtherData.IsDataExist(query,TjIsDataExist);
	}
}
function TjIsDataExist(data){
	if(data != null){
		if(data == "1"){
			if(confirm("���Ѿ�����˸�λ\n�˲�����������������޸ģ�ȷ��Ҫ�޸���")){
				refreshForm('xsqgzx.do?method=addXsgwInfo');
			}else{
				return false;
			}
		}else if( data== "2"){
			alert("���Ѿ�����˸�λ\n���Ѿ�ͨ����ˣ����Ѳ���������˸�λ��");
			return false;
		}else{
			refreshForm('xsqgzx.do?method=addXsgwInfo');
		}
	}else{
		alert("��ȡ����ʧ�ܣ�");
		return false;
	}
}

	function printView(){
		var xh = document.getElementById('xh').value;
		var yhtc = document.getElementById('yhtc').value;
		var gwjl = document.getElementById('gzjl').value;
		var lxdh = document.getElementById('lxdh').value;
		var url = 'xsqgzx.do?method=printXsgwxxb';

		url += '&xh=';
		url += xh;
		url += '&yhtc=';
		url += yhtc;
		url += '&gzjl=';
		url += gwjl;
		url += '&lxdh=';
		url += lxdh;
				
		window.open(url);
	}
	</script>
	<body>
	<html:form action="/xsqgzx.do" method="post">				
		<input type="hidden" name="xxdm" id="xxdm" value="<bean:write name='xxdm'/>"/>
		<input type="hidden" name="doType" id="doType" value="<bean:write name='doType'/>"/>
			<div class="title">
				<logic:equal name="do" value="no">
					<div class="title_img" id="title_m">
						��ǰλ�ã��ڹ���ѧ - ��λ���� - ��д�����
					</div>
				</logic:equal>
				<logic:equal name="do" value="yes">
					<div class="title_img" id="title_m">
						��ǰλ�ã��ڹ���ѧ - ��λ���� - �޸������
					</div>
				</logic:equal>
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
					<logic:equal name="sqsjFlag" value="1">
						<script>
    				alert("�����趨ʱ�䷶Χ��,�ݲ���������!");
    				location.href="about:blank";
    				</script>
					</logic:equal>

				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xh-xm-xb-xymc-nj-zymc-bjmc-jg-zzmmmc-ssbh-lxdh" />
				<input type="hidden" id="url" name="url" value="/xsqgzx.do?method=stationApp" />
				<input type="hidden" name="sfwh" value="sfwh" />
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<logic:equal name="do" value="no">
									<b>��д�����</b>
								</logic:equal>
								<logic:equal name="do" value="yes">
									<b>�޸������</b>
								</logic:equal>
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<td align="right">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left">
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td align="right">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left">
								<bean:write name='rs' property="xh" />
								<input type="hidden" name="xh" id="xh" value="<bean:write name='rs' property="xh" />">
							</td>
						</logic:equal>
						<td align="right">
							<font color="red">*</font>��λ���ƣ�						
						</td>
						<logic:equal value="modi" name="doType">
							<td align="left">
								<input type="hidden" id="isModi" name="isModi"  value="<bean:write name="doType" scope="request"/>" />
								<input type="hidden" name="xmdmmodi" id="xmdmmodi" value="<bean:write name='rs' property='gwdm'/>">
										<html:select name="rs" property="gwdm" styleId="gwdm"
											style="width:150px" disabled="true" onchange="">
											<html:option value=""></html:option>
											<html:options collection="gwList" property="gwdm"
											labelProperty="gwmc" />
										</html:select>
								
							</td>
						</logic:equal>
						<logic:notEqual value="modi" name="doType">						
								<td align="left">							
									<html:select name="rs" property="gwdm" styleId="gwdm"
											style="width:150px" onchange="getFzr();">
											<html:option value=""></html:option>
											<html:options collection="gwList" property="gwdm"
												labelProperty="gwmc" />
										</html:select>
								</td>
						</logic:notEqual>	
					</tr>

					<tr style="height:22px">
						<td align="right">
							������
						</td>
						<td align="left">
							<bean:write name="rs" property="xm"/>
						</td>
						<td align="right">
							��ȣ�
						</td>
						<td align="left">
							<html:text name="rs" property="nd" readonly="true"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�Ա�
						</td>
						<td align="left">
							<bean:write name="rs" property="xb"/>
						</td>
						<td align="right">
							ѧ�꣺
						</td>
						<td align="left">
							<html:text name="rs" property="xn" readonly="true"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�꼶��
						</td>
						<td align="left">
							<bean:write name="rs" property="nj"/>
						</td>
						<td align="right">
							ѧ�ڣ�
						</td>
						<td align="left">
							<html:text name="rs" property="xq" readonly="true"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left">
							<bean:write name="rs" property="xymc"/>
						</td>
						<td align="right">
							��ϵ�绰��
						</td>
						<td align="left">
							<html:text property="dwlxdh" styleId="dwlxdh" name="rs"/>
						</td>
					</tr>						
						<tr>
							<td align="right">
								רҵ��
							</td>
							<td>
								<bean:write name="rs" property="zymc"/>
							</td>	
							<td align="right">
								��ϵ�ˣ�
							</td>
							<td>
								<html:text property="fzr" styleId="fzr" name="rs"/>
							</td>		
						</tr>
						<tr>
							<td align="right">
								�༶��
							</td>
							<td>
								<bean:write name="rs" property="bjmc"/>
							</td>
							<td align="right">
								���᣺
							</td>							
							<td>
								<bean:write name="rs" property="jg"/>
							</td>
						</tr>						
						<tr>
						  <td align="right">����ţ�</td>
						  <td>
						  <bean:write name="rs" property="ssbh"/>
						  </td>
						  <td align="right">
								������ò��
							</td>
							<td>
								<bean:write name="rs" property="zzmmmc"/>
							</td>
				    </tr>
					<tr >
						<logic:notEmpty name="kxList">
								<td colspan="4" bgcolor="#FFDDCC">
									<table border="0" cellpadding="0" cellspacing="0"
										align="center">
										<tr>
										<td colspan="8" align="center">
										�ɲμ��ڹ���ѧ����ʱ��
										</td>
										</tr>
										<tr>
											<td align="center">
												ʱ��
											</td>
											<td>
												����һ
											</td>
											<td>
												���ڶ�
											</td>
											<td>
												������
											</td>
											<td>
												������
											</td>
											<td>
												������
											</td>
											<td>
												������
											</td>
											<td>
												������
											</td>
										</tr>
										<logic:iterate id="kxsj" name="kxList">
											<tr>
												<td>
													${kxsj.sj}
												</td>
												<td align="center">
													<input type="checkbox" name="${kxsj.sjIndex}1" value="1" />
													<input type="hidden" name="index${kxsj.sjIndex}1"
														value="${kxsj.mon}" />
												</td>
												<td align="center">
													<input type="checkbox" name="${kxsj.sjIndex}2" value="1" />
													<input type="hidden" name="index${kxsj.sjIndex}2"
														value="${kxsj.tue}" />
												</td>
												<td align="center">
													<input type="checkbox" name="${kxsj.sjIndex}3" value="1" />
													<input type="hidden" name="index${kxsj.sjIndex}3"
														value="${kxsj.wed}" />
												</td>
												<td align="center">
													<input type="checkbox" name="${kxsj.sjIndex}4" value="1" />
													<input type="hidden" name="index${kxsj.sjIndex}4"
														value="${kxsj.thu}" />
												</td>
												<td align="center">
													<input type="checkbox" name="${kxsj.sjIndex}5" value="1" />
													<input type="hidden" name="index${kxsj.sjIndex}5"
														value="${kxsj.fri}" />
												</td>
												<td align="center">
													<input type="checkbox" name="${kxsj.sjIndex}6" value="1" />
													<input type="hidden" name="index${kxsj.sjIndex}6"
														value="${kxsj.sat}" />
												</td>
												<td align="center">
													<input type="checkbox" name="${kxsj.sjIndex}7" value="1" />
													<input type="hidden" name="index${kxsj.sjIndex}7"
														value="${kxsj.sun}" />
												</td>
											</tr>
										</logic:iterate>
									</table>
										<script language="javascript">
										for(var i=0;i<7;i++){
											for(var j=1;j<8;j++){
											 	if(document.getElementById("index"+i+j)){
											 		if(document.getElementById("index"+i+j).value==1){
														document.getElementById(i+""+j).checked="checked";
												    }
											 	}
											}
										}
									</script>
								</td>
							</logic:notEmpty>

							<logic:empty name="kxList">								
								<td colspan="4" align="center" bgcolor="#FFDDCC">
									<table border="0" cellpadding="0" cellspacing="0"
										align="center" class="tbstyle" >
<%--										<thead>--%>
											<tr
												onclick="if(document.getElementById('tbSj').style.display == 'none'){document.getElementById('tbSj').style.display = ''}else{document.getElementById('tbSj').style.display = 'none'};">
												<td colspan="8" align="center" style="cursor:hand">
													�ɲμ��ڹ���ѧ����ʱ��
												</td>
											</tr>
<%--										</thead>--%>
										<tbody id="tbSj" style="display: none">
											<tr>
												<td align="center">
													ʱ��
												</td>
												<td>
													����һ
												</td>
												<td>
													���ڶ�
												</td>
												<td>
													������
												</td>
												<td>
													������
												</td>
												<td>
													������
												</td>
												<td>
													������
												</td>
												<td>
													������
												</td>
											</tr>
											<logic:iterate id="kxsj" name="whkxList">
												<tr>
													<td>
														${kxsj.sj}
													</td>
													<td align="center">
														<input type="checkbox" name="${kxsj.sjIndex}1" value="1" />
													</td>
													<td align="center">
														<input type="checkbox" name="${kxsj.sjIndex}2" value="1" />
													</td>
													<td align="center">
														<input type="checkbox" name="${kxsj.sjIndex}3" value="1" />
													</td>
													<td align="center">
														<input type="checkbox" name="${kxsj.sjIndex}4" value="1" />
													</td>
													<td align="center">
														<input type="checkbox" name="${kxsj.sjIndex}5" value="1" />
													</td>
													<td align="center">
														<input type="checkbox" name="${kxsj.sjIndex}6" value="1" />
													</td>
													<td align="center">
														<input type="checkbox" name="${kxsj.sjIndex}7" value="1" />
													</td>
												</tr>
											</logic:iterate>
										</tbody>
									</table>
								</td>
							</logic:empty>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right">
							��ϵ�绰��
						</td>
						<td colspan="3">
							<html:text name="rs" property="lxdh" styleId="lxdh" style="width:100%"/>
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right">
							�к��س���
						</td>
						<td colspan="3">
							<html:textarea name='rs' property="yhtc" styleId="yhtc"
								style="width:99%" rows='5' />
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right">
							��λ��¼��
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='gzjl' styleId="gzjl"
								style="width:99%" rows='5' />
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right">
							�������ɣ�
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='sqly' styleId="sqly"
								style="width:99%" rows='5' />
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right">
							��ע��
						</td>
						<td colspan="3">
						<logic:equal value="student" name="userOnLine">
						<html:textarea name='rs' property='bz' styleId="bz"
								style="width:99%" rows='5' readonly="true"/>
						</logic:equal>
						<logic:notEqual value="student" name="userOnLine">
							<html:textarea name='rs' property='bz' styleId="bz"
								style="width:99%" rows='5' />
						</logic:notEqual>
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<logic:equal name="do" value="no" scope="request">
						<button type="button" class="button2" onclick="saveXsgw('xh-gwdm')">
							�� �� �� ��
						</button>
					</logic:equal>
					<logic:equal name="do" value="yes" scope="request">
						<button type="button" class="button2" onclick="saveXsgw('xh-gwdm')">
							�� �� �� ��
						</button>
					</logic:equal>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<logic:notPresent name="printView">
						<button type="button" class="button2" onclick="expAppTab('rsT','�ڹ���ѧ��λ�����','')">
								�� ӡ Ԥ ��
					    </button>
				  </logic:notPresent>
				  <logic:present name="printView">
				  		<button type="button" class="button2" onclick="printView();">
								�� ӡ Ԥ ��
					    </button>
				  </logic:present>
				</div>
			</logic:notEmpty>
			<logic:present name="result">
				<logic:equal name="result" value="true">
					<script>
    alert("�����ɹ���");
    </script>
				</logic:equal>
				<logic:equal name="result" value="false">
					<script>
    alert("����ʧ�ܣ�");
    </script>
				</logic:equal>
				<logic:equal name="result" value="nopks">
					<script>
    alert("����ʧ�ܣ�������ƶ������������");
    </script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
