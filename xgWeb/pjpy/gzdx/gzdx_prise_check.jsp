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
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
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
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<input type="hidden" name="zyV" id="zyV" value="" />
	<input type="hidden" name="bjV" id="bjV" value="" />
	<script language="javascript" src="js/BatAlert.js"></script>
	<script language="javascript" src="js/webPrint.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/pjpyFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/gzdxpjpy.js'></script>
	<script type="text/javascript" src="pjpy/pjpyjs/pjpy.js"></script>
	<script type="text/javascript">
		function jxjsh(shType) {
			var url = "/xgxt/prise_check.do?checkall=yes&shzt=" + shType;
			var checkBoxArr = document.getElementsByName("checkVal");
			var flag = false;
			var pk = "";
			var xh = "";
			var out = "";
			var act = "";
			var xn = document.getElementById('xn').value;
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					flag = true;
					var xxsh  = replaceBlankSpace(checkBoxArr[i].parentNode.parentNode.cells[15].innerText);
					if (xxsh != 'ͨ��') {
						pk += checkBoxArr[i].value + "!@";
						xh += replaceBlankSpace(checkBoxArr[i].parentNode.parentNode.cells[2].innerText) +"!@";
					}
				}
			}
			if (flag){
				if ('pass'==shType) {
					dwr.engine.setAsync(false);
					gzdxpjpy.checkPlshdata(pk,xh,xn,function (data) {
						//���ص�������
						if (data != null) {
							act = data[0];
							out = data[1];							
						}
					});
					dwr.engine.setAsync(true); 
				}
				//����ֵ��Ϊ������ʾ�û������Ϣ
				if (out != null && out != "") {
					if (act=="true") {
						alert("����ѧ��ֻ�ܻ�һ����ѧ��,��ǰ��˵�������,ѧ��Ϊ:\n" + out + "\n��ѧ���ж����ѧ��������ͨ������!");
					} else {
						alert("����ѧ��ֻ�ܻ�һ����ѧ��,��ǰ��˵�������,ѧ��Ϊ:\n" + out +"\n��ѧ�����걨��������ѧ�������ͨ��,���������!");
					}
					return false;					
				}
				if (confirm('ȷ��Ҫ�����ѡ���������')){
					document.forms[0].action = url;
					document.forms[0].submit();
					if ($("pt")) {
						BatAlert.showTips('���ڲ�������ȴ�...');
					}
				}
			}else{
				alert("û��ѡ����Ӧ��¼����ѡ��֮���ٽ��в�������");
			}
		}
		function viewSh() {
			var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
			var url = "pjpy_gzdx_viewJxjSh.do?lb=jxj&pkValue=" + pkValue;
			showTopWin(url, 650, 500);
		}
	</script>
		<body onload="xyDisabled('xy');">
			<html:form action="/prise_conf_rs" method="post">
				<div class="title">
					<div class="title_img" id="title_m">
						��ǰ����λ�ã�
						<bean:write name="tips" scope="request" />
					</div>
				</div>
				<input type="hidden" id="userType" name="userType"
					value="${userType }" />
					<input type="hidden" id="xxdm" name="xxdm"
					value="${xxdm }" />
					<input type="hidden" id="isFdy" name="isFdy"
					value="" />
				<input type="hidden" id="act" name="act"
					value="<bean:write name="act" scope="request"/>" />
				<input type="hidden" id="pt"/>
				<fieldset>
					<legend>
						��������
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									ѧ�꣺
									<html:select property="xn" style="width:95px" disabled="true"
										styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
									&nbsp;&nbsp;��ѧ��
										<html:select property="xmdm" style="width:160px"
											onchange="refreshForm('/xgxt/prise_check.do')"
											styleId="jxjdm">
											<html:option value=""></html:option>
											<html:options collection="jxjList" property="jxjdm"
												labelProperty="jxjmc" />
										</html:select>
									
								&nbsp;&nbsp;״̬:
								<html:select property="zt" styleId="zt">
											<html:option value=""></html:option>
											<html:option value="δ���">δ���</html:option>
											<html:option value="��ͨ��">��ͨ��</html:option>
											<html:option value="ͨ��">ͨ��</html:option>
										</html:select>
								&nbsp;&nbsp;ѧ��:
								<html:text property="xh" styleId="xh" style="width:100px"></html:text>
								&nbsp;&nbsp;����:
								<html:text property="xm" styleId="xm" style="width:100px"></html:text>
								</td> 
											<td width="10" rowspan="2" align="center" valign="middle">
												<input type="hidden" name="go" value="a" />
												<button class="button2" style="height:40px" id="search_go"
													onclick="refreshForm('/xgxt/prise_check.do')">
													��ѯ
												</button>
											</td>
							</tr>
							<tr>
								<td align="left" nowrap>
									�꼶��
									<html:select property="nj" styleId="nj"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									<bean:message key="lable.xsgzyxpzxy" />��
										<html:select property="xydm" style="width:170px" styleId="xy"
											onchange="initZyList();initBjList()">
											<html:option value="">    ȫ��    </html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									<span id="dispZy"> &nbsp;&nbsp;רҵ�� <html:select
											property="zydm" style="width:150px;" styleId="zy"
											onchange="initBjList()">
											<html:option value="">    ȫ��    </html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select> </span>
									<span id="dispBj"> &nbsp;&nbsp;�༶�� <html:select
											property="bjdm" style="width:120px" styleId="bj">
											<html:option value="">    ȫ��    </html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select> </span>
								</td>
							</tr>
						</thead>
					</table>
				</fieldset>
				<logic:empty name="rs">
					<p align="center">
						δ�ҵ��κμ�¼��
					</p>
				</logic:empty>
				<logic:notEmpty name="rs">
					<fieldset>
						<legend>
							��¼����
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">��ʾ��˫��һ�п��Բ鿴������Ϣ�������Ըı����״̬��������ͷ�������� </font>
						</legend>
							<table width="100%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
										<td>
											<input type="checkbox" id="selall" name="selall"
												onclick="selAll()">
										</td>
										<logic:iterate id="tit" name="topTr" offset="2">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" align="center" ondblclick="viewSh()"
										style="cursor:hand;background-color:
					    				<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>">
										<td>
											<input type="checkbox" id="checkVal" name="checkVal"
												value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>">
										</td>
										<logic:iterate id="v" name="s" offset="2">
											<td  nowrap="nowrap">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
					</fieldset>
				</logic:notEmpty>
					<logic:match value="yes" name="rw" scope="request">
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<button class="button2" onclick="jxjsh('pass')" id="btn_tg">���ͨ��</button>
							<button class="button2" onclick="jxjsh('nopass')" id="btn_btg">��˲�ͨ��</button>
						</div>
					</logic:match>
				<div id="tmpdiv"></div>
			</html:form>
			<script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 40;
					document.getElementById("btn").style.width = "96%";
					window.setInterval("initBTNTool('btn')",1);
				</script>
		</body>
</html>
