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
		<script>   
var mb = 0;
/*
function colorOn(){
	for(i = 0;i<mbT.rows.length;i++){
		for(j = 0;j<mbT.rows[i].cells.length;j++){
			document.all.mbT.rows[i].cells[j].style.backgroundColor = "#FFFFFF";
			document.all.mbT.rows[i].cells[mb].style.backgroundColor = "#ffdead";
		}
	}
}
*/
function printZS(mod){
	var xxdm = document.getElementById("xxdm").value;
	var url = "noticePrintOne.do";
    if(mod > 2 || mod==1){
    if(xxdm=="12957" || xxdm == "10491"){//������Ϣ�͵��ʴ�ѧ
	    var  rqvar = new Date();
		var rq =  rqvar.getYear()+"-"+rqvar.getMonth()+"-"+rqvar.getDay();
		var fzrq = prompt("   �����뷢֤���ڣ�(���ڸ�ʽΪxxxx-xx-xx)",'');
		var bfrq = prompt("   �����벹�����ڣ�(���ڸ�ʽΪxxxx-xx-xx)",'');
		var yxrq = prompt("   ��������Ч������(���ڸ�ʽΪxxxx-xx-xx)",'');
		var dzdxyz ;
		if(mod == 3 && xxdm == "10491"){
		    dzdxyz = "wztz";
		}
		if(mod == 1 && xxdm == "10491"){
		    dzdxyz = "dgdy";
		}
	    if (confirm("ȷ��Ҫ���˲�����?")){
			window.open("noticePrintOne.do?isLd=no&xh="+document.getElementById("ycxh").value+"&fzrq=" + fzrq + "&bfrq=" + bfrq + "&yxrq=" + yxrq+"&dzdxyz="+dzdxyz);
			return true;
		}else{
		return false;
		}
	}
	if(xxdm=="10513"){//����ʦ��
	   var  rqvar = new Date();
	   var rq =  rqvar.getYear()+"-"+rqvar.getMonth()+"-"+rqvar.getDay();
	   var fzrq = prompt("   �����뷢֤���ڣ�(���ڸ�ʽΪxxxx-xx-xx)","");
	   var yxrq = prompt("   ��������Ч������(���ڸ�ʽΪxxxx-xx-xx)","");
	   
	   if (confirm("ȷ��Ҫ���˲�����?")){
	       window.open("noticePrintOne.do?isLd=no&xh="+document.getElementById("ycxh").value+"&fzrq=" + fzrq + "&yxrq=" + yxrq);
	       return true;
	   }else{
	   return false;
	   }
	 }
  }
	window.open(url);
}


    function noticePrintxsz(){
	var  rqvar = new Date();
	var xxdm = document.getElementById("xxdm").value;
	var rq =  rqvar.getYear()+"-"+rqvar.getMonth()+"-"+rqvar.getDay();
	if(xxdm=="12957"){//������Ϣ
		var fzrq = prompt("   �����뷢֤���ڣ�(���ڸ�ʽΪxxxx-xx-xx)","");
		var bfrq = prompt("   �����벹�����ڣ�(���ڸ�ʽΪxxxx-xx-xx)","");
		var yxrq = prompt("   ��������Ч������(���ڸ�ʽΪxxxx-xx-xx)","");
	}
	if(xxdm=="10491"){//�й����ʴ�ѧ
		var fzrq = prompt("   �����뷢֤���ڣ�(���ڸ�ʽΪxxxx-xx-xx)","");
		var bfrq = prompt("   �����벹�����ڣ�(���ڸ�ʽΪxxxx-xx-xx)","");
		var yxrq = prompt("   ��������Ч������(���ڸ�ʽΪxxxx-xx-xx)","");
	}
	if(xxdm=="10513"){//����ʦ��
		var fzrq = prompt("   �����뷢֤���ڣ�(���ڸ�ʽΪxxxx-xx-xx)","");
		var yxrq = prompt("   ��������Ч������(���ڸ�ʽΪxxxx-xx-xx)","");
	}
        if (confirm("ȷ��Ҫ���˲�����?")){
    	var xh;
		if($("tabPri").rows.length > 1){
			rowOnClick($("tabPri").rows[0]);
			xh=$("tabPri").rows[0].cells[1].innerText.trim();
			if(xxdm=="12957"){//������Ϣ
				window.open("noticePrintOne.do?xh="+xh+"&fzrq=" + fzrq + "&bfrq=" + bfrq + "&yxrq=" + yxrq);
			}else if(xxdm=="10491"){//�й����ʴ�ѧ
				window.open("noticePrintOne.do?xh="+xh+"&fzrq=" + fzrq + "&bfrq=" + bfrq + "&yxrq=" + yxrq);
			}else if(xxdm=="10513"){//����ʦ��
				window.open("noticePrintOne.do?xh="+xh+"&fzrq=" + fzrq + "&yxrq=" + yxrq);
			}else{
			  window.open("noticePrintOne.do");
			}	
			BatAlert.closeTips();
		 } else{
		    BatAlert.MyAlert("û�пɴ�ӡ�����ݣ�");
			return false;
		 }
		 return true;
	}
     else{
	     return false;
	}   
  }
</script>
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<style media="print">
.noprint{
	display:none;
}
.print{
	display:block;
}
</style>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sharedFunction.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript" src="js/stuinfoFunction.js"></script>
		<script language="javascript" src="js/BatAlert.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/log_search" method="post">
			<input type="hidden" id="xxdm"
				value="<bean:write name="xxdm" scope="session"/>" />
			<div class="title noprint">
				<div class="title_img">
					��ǰ����λ�ã��ճ����� - ѧ��֤���� - ѧ��֤��ӡ
				</div>
			</div>
			<div class="noprint">
				<fieldset>
					<legend>
						�� ѯ &amp; �� ��
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									<input type="hidden" value="a" id="nj" name="nj">
									<input type="hidden" value="a" id="xmdm" name="jxjdm">
									<input type="checkbox" style="display:none" id="chgMode">
									<bean:message key="lable.xsgzyxpzxy" />��
									<html:select property="xydm" style="width:180px" styleId="xy"
										onchange="refreshForm('stu_card_print.do')">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button class="button2" style="height:40px" id="search_go"
										onclick="listPriseConf('/xgxt/stu_card_print.do')">
										��ѯ
									</button>
								</td>
							</tr>
							<tr>
								<td align="left" nowrap>
									רҵ��
									<html:select property="zydm" style="width:180px" styleId="zy"
										onchange="refreshForm('stu_card_print.do')">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
									&nbsp;&nbsp;�༶��
									<html:select property="bjdm" style="width:180px" styleId="bjdm">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
						</thead>
					</table>
				</fieldset>
			</div>

			<div class="noprint">
				<fieldset>
					<legend align="center">
						�Ǵ�ӡ��
					</legend>
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
							</legend>
							<table width="100%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
										<logic:iterate id="tit" name="topTr">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<tbody id="tabPri">
									<logic:iterate name="rs" id="s">

										<tr onclick="rowOnClick(this)" style="cursor:hand;">
											<logic:iterate id="v" name="s">
												<td>
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</tbody>
							</table>
						</fieldset>
					</logic:notEmpty>
				</fieldset>
				<div class="buttontool noprint"
					style="position: absolute;left:1%;top:100px" id="btn">
					<logic:equal name="xxdm" value="normal">
						<input type='button' class='button2' value='������ӡ'
							onclick="printZS(1)">
						<input type='button' class='button2' value='֤������'
							onclick="noticePrintxsz()">
						<input type='button' class='button2' value='λ�õ���'
							onclick="printZS(3)">
					</logic:equal>
					<logic:equal name="xxdm" value="szxx">
						<input type='button' class='button2' value='������ӡ'
							onclick="printZS(1)">
						<input type='button' class='button2' value='֤������'
							onclick="noticePrintxsz()">
						<input type='button' class='button2' value='λ�õ���'
							onclick="printZS(3)">
					</logic:equal>
						<logic:equal name="xxdm" value="zgdzdx">
						<input type='button' class='button2' value='������ӡ'
							onclick="printZS(1)">
						<input type='button' class='button2' value='֤������'
							onclick="noticePrintxsz()">
						<input type='button' class='button2' value='λ�õ���'
							onclick="printZS(3)">
					</logic:equal>
					<logic:equal name="xxdm" value="hbsf">
						<input type='button' class='button2' value='������ӡ'
							onclick="printZS(1)">
						<input type='button' class='button2' value='֤������'
							onclick="noticePrintxsz()">
						<input type='button' class='button2' value='���ò���ҳ��'
							onclick="printZS(3)">
					</logic:equal>
				</div>
			</div>
			<input type="hidden" id="ycxh" name="ycxh" value="" />
		</html:form>
		<script language="javascript">
		document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
		document.getElementById("btn").style.width = "96%";
		window.setInterval("initBTNTool('btn')",1);
		</script>
	</body>
</html>
