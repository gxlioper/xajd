<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />

		<script type="text/javascript">
		function modi1(url){
			if(curr_row != null){
<%--				+'&queryequals_xn='+ $('xn').value + '&queryequals_xq=' + $('xq').value--%>
<%--				+'&queryequals_yd='+$('yd').value--%>
				showTopWin(url + '&bjdm='+curr_row.getElementsByTagName('input')[0].value
				+'&yd='+$('yd').value,700,500);
				return true;
			}else{
				alert('��ѡ��Ҫ¼��İ༶��');
				return false;
			}
		}
		
	function changeYd(obj){
		$('yd').value = obj.value;
		$('text_yd').innerHTML = obj.value;
	}
	
    function djzh(){
	var d_width = document.body.clientWidth;
	var d_height = document.body.clientHeight ;
	var d_left = 0;
	var d_top = 0;
	var d_color = "#EEF4F9";
	var d_width_top = 250;
	var d_height_top = 120;
	var d_left_top = (d_width - d_width_top) / 2;
	var d_top_top = (d_height - d_height_top)/ 2;
	var d_color_top = "#EEF4F9";
	dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
	dd_html += "</div>";
	dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
	dd_html += "<table width='300' class='tbstyle'>";
	dd_html += "<thead>";
	dd_html += "<tr height='30'>";
	dd_html += "<td colspan='2'>";
	dd_html += "----------------��ѡ��Ҫ¼����¶�---------------";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "</thead>";
	dd_html += "<tbody>";
	
	dd_html += "<tr height='30'>";
	dd_html += "<td align='right' width='40%'>";
	dd_html += "��ѡ���¶�:";
	dd_html += "</td>";
	dd_html += "<td align='left'>";
	dd_html += "<select name='sel' id ='sel'>" 
	dd_html += "<option value='01'>��1�¶�</option>"
	dd_html += 	"<option value='02'>��2�¶�</option>"
	dd_html += 	"<option value='03'>��3�¶�</option>"
	dd_html += 	"<option value='04'>��4�¶�</option>"
	dd_html += "</select>"
	dd_html += "</td>";
	dd_html += "</tr>";
	
	dd_html += "<tr bgcolor='EEF4F9'>";
	dd_html += "<td colspan='2' align='center'>";
	dd_html += "<button type='button' class='button2' onclick=changeYd($('sel'));closeAdd1();>ȷ��</button>";
	dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
	dd_html += "<button type='button' class='button2' onclick='closeAdd1()'>�ر�</button>";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tbody>";
	dd_html += "</table>";
	dd_html += "</div>";
	document.getElementById('tmpdiv1').innerHTML = dd_html;
}
		</script>
	</head>

	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/qgzxFunction.js"></script>

		<html:form action="ghxy_bjbz.do?method=bjbzflr" method="post">
			<input type="hidden" id="pkValue" value="${pkValue}" />
			<input type="hidden" id="yd" name="yd" value="01"/>
			<input type="hidden" id="xn" name="xn" value="${xn }"/>
			<input type="hidden" id="xq" name="xq" value="${xq }"/>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�${title }
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="center">
							ְ���ţ�
							<bean:write name="userName" scope="session" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							������
							<bean:write name="userNameReal" scope="session" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							ѧ�꣺${xn }
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							ѧ�ڣ�${xq }
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							�¶ȣ�<span id="text_yd">01</span>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
				</thead>
			</table>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					δ�ҵ��κμ�¼��
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						�����༶��Ϣ
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr>
								<td align="center" colspan="2">
									�����༶
								</td>
							</tr>
							<tr>
								<logic:iterate id="topTd" name="topTr">
									<td align="center">
										${topTd }
									</td>
								</logic:iterate>

							</tr>
						</thead>
						<logic:iterate id="str" name="rs">
							<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
								<logic:iterate id="s" name="str" indexId="index">
									<logic:equal value="0" name="index">
										<input type="hidden" name="bjdm" value="${s }"/>
									</logic:equal>
									<td align="center">
										${s}
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
			<div id="tmpdiv1"></div>
			<div class="buttontool" id="btn"
				style="position: absolute;left:1%;top:100px" width="100%">
				<logic:equal value="yes" name="writeAble">
				<button type="button" class="button2"
					onclick="modi1('ghxy_bjbz.do?method=bjbzflrone&doType=query');"
					style="width:80px">
					¼ ��
				</button>
				<button type="button" class="button2"
					onclick="djzh();"
					style="width:80px">
					�����¶�
				</button>
				</logic:equal>
			</div>

			<script language="javascript">
						document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
						document.getElementById("btn").style.width = "96%";
						window.setInterval("initBTNTool('btn')",1);
			</script>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>

		</html:form>
	</body>
</html>
