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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<script type="text/javascript">
	function showHide(){
	
	   if(document.getElementsByName("tjtype")[0].checked){
	      document.getElementById('yx').style.display='';
	      document.getElementById('hg').style.display='none'
	   }else if(document.getElementsByName("tjtype")[1].checked){
	   	 document.getElementById('yx').style.display='none';		        
		  document.getElementById('hg').style.display='';	
	   }		     	  			
    }
	</script>
	<body>
		<fieldset>
			<legend>
				ͳ��
			</legend>
			ѧ�꣺
			<select name="" disabled>
				<option value="">
					2008-2009
				</option>
			</select>
			ѧ�ڣ�
			<select name="" disabled>
				<option value="">
					һ
				</option>
			</select>
			<input type="radio" name="tjtype" value="1" checked onclick="showHide()">
			����������
			<input type="radio" name="tjtype" value="2" onclick="showHide()">
			���պϸ���
		</fieldset>
		<div id="yx">
			<br>
			<br>
			<br>
			<br>
			<div align="center">
				<font color="#0000FF" size="+2"><b>2008-2009ѧ��һѧ�ڻ���������������б�</b>
				</font>
			</div>
			<table width="100%" class="tbstyle">
				<tr>
					<td width="76" rowspan="2">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							���ҷֲ����������
						</div>
					</td>
				</tr>
				<tr>
					<td width="40">
						<div align="center">
							¥��
						</div>
					</td>
					<td width="309">
						<div align="center">
							����/����
						</div>
					</td>
				</tr>
				<tr>
					<td rowspan="5">
						���������<bean:message key="lable.xsgzyxpzxy" />
					</td>
					<td>
						һ��¥
					</td>
					<td>
						101/91��103/92��105/94��106/90��107/90��201/90��202/90��301/90��303/90��305/90��306/90��307/90
					</td>
				</tr>
				<tr>
					<td>
						����¥
					</td>
					<td>
						101/91��102/90��103/92��104/93��201/94��203/91��204/92��205/94��501/92��502/94��503/90��504/90
					</td>
				</tr>
				<tr>
					<td>
						����¥
					</td>
					<td>
						101/91��102/90��103/92��104/93��201/94��203/91��204/92��205/94��501/92��502/94��503/90��504/90
					</td>
				</tr>
				<tr>
					<td>
						�ĺ�¥
					</td>
					<td>
						101/91��102/90��103/92��104/93��201/94��203/91��204/92��205/94��501/92��502/94��503/90��504/90
					</td>
				</tr>
				<tr>
					<td>
						����¥
					</td>
					<td>
						101/91��102/90��103/92��104/93��201/94��203/91��204/92��205/94��501/92��502/94��503/90��504/90
					</td>
				</tr>
				<tr>
					<td rowspan="4">
						�����<bean:message key="lable.xsgzyxpzxy" />
					</td>
					<td>
						�ĺ�¥
					</td>
					<td>
						101/90��101/90��101/90��101/90��101/90��101/90��101/90��101/90��101/90��101/90��101/90��101/90
					</td>
				</tr>
				<tr>
					<td>
						�ߺ�¥
					</td>
					<td>
						101/91��102/90��103/92��104/93��201/94��203/91��204/92��205/94��501/92��502/94��503/90��504/90
					</td>
				</tr>
				<tr>
					<td>
						�˺�¥
					</td>
					<td>
						101/91��102/90��103/92��104/93��201/94��203/91��204/92��205/94��501/92��502/94��503/90��504/90
					</td>
				</tr>
				<tr>
					<td>
						�ź�¥
					</td>
					<td>
						101/91��102/90��103/92��104/93��201/94��203/91��204/92��205/94��501/92��502/94��503/90��504/90
					</td>
				</tr>
				<tr>
					<td rowspan="4">
						����<bean:message key="lable.xsgzyxpzxy" />
					</td>
					<td>
						һ��¥
					</td>
					<td>
						401/90��402/90��403/90��404/90��405/90��601/90��602/90��603/90��604/90��605/90��607/90��608/90
					</td>
				</tr>
				<tr>
					<td>
						����¥
					</td>
					<td>
						501/91��502/90��503/92��504/93��701/94��703/91��704/92��705/94��901/92��902/94��903/90��904/90
					</td>
				</tr>
				<tr>
					<td>
						�˺�¥
					</td>
					<td>
						101/91��102/90��103/92��104/93��201/94��203/91��204/92��205/94��501/92��502/94��503/90��504/90
					</td>
				</tr>
				<tr>
					<td>
						�ź�¥
					</td>
					<td>
						101/91��102/90��103/92��104/93��201/94��203/91��204/92��205/94��501/92��502/94��503/90��504/90
					</td>
				</tr>
			</table>
		</div>
		<div id="hg" style="display: none">
			<br>
			<br>
			<br>
			<br>
			<div align="center">
				<font color="#0000FF" size="+2"><b>2008-2009ѧ��һѧ�ڻ�úϸ����������б�</b>
				</font>
			</div>
			<table width="100%" id="rsTable" class="tbstyle">
				<tr>
					<td width="96" rowspan="2">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							���ҷֲ����������
						</div>
					</td>
				</tr>
				<tr>
					<td width="40">
						<div align="center">
							¥��
						</div>
					</td>
					<td width="309">
						<div align="center">
							����/����
						</div>
					</td>
				</tr>
				<tr>
					<td rowspan="5">
						���������<bean:message key="lable.xsgzyxpzxy" />
					</td>
					<td>
						һ��¥
					</td>
					<td>
						111/71��113/72��115/74��116/71��117/71��211/71��212/71��311/71��313/71��315/71��316/71��317/71
					</td>
				</tr>
				<tr>
					<td>
						����¥
					</td>
					<td>
						111/71��112/71��113/72��114/73��211/74��213/71��214/72��215/74��511/72��512/74��513/71��514/71
					</td>
				</tr>
				<tr>
					<td>
						����¥
					</td>
					<td>
						111/71��112/71��113/72��114/73��211/74��213/71��214/72��215/74��511/72��512/74��513/71��514/71
					</td>
				</tr>
				<tr>
					<td>
						�ĺ�¥
					</td>
					<td>
						111/71��112/71��113/72��114/73��211/74��213/71��214/72��215/74��511/72��512/74��513/71��514/71
					</td>
				</tr>
				<tr>
					<td>
						����¥
					</td>
					<td>
						111/71��112/71��113/72��114/73��211/74��213/71��214/72��215/74��511/72��512/74��513/71��514/71
					</td>
				</tr>
				<tr>
					<td rowspan="4">
						�����<bean:message key="lable.xsgzyxpzxy" />
					</td>
					<td>
						�ĺ�¥
					</td>
					<td>
						111/71��111/71��111/71��111/71��111/71��111/71��111/71��111/71��111/71��111/71��111/71��111/71
					</td>
				</tr>
				<tr>
					<td>
						�ߺ�¥
					</td>
					<td>
						111/71��112/71��113/72��114/73��211/74��213/71��214/72��215/74��511/72��512/74��513/71��514/71
					</td>
				</tr>
				<tr>
					<td>
						�˺�¥
					</td>
					<td>
						111/71��112/71��113/72��114/73��211/74��213/71��214/72��215/74��511/72��512/74��513/71��514/71
					</td>
				</tr>
				<tr>
					<td>
						�ź�¥
					</td>
					<td>
						111/71��112/71��113/72��114/73��211/74��213/71��214/72��215/74��511/72��512/74��513/71��514/71
					</td>
				</tr>
				<tr>
					<td rowspan="4">
						����<bean:message key="lable.xsgzyxpzxy" />
					</td>
					<td>
						һ��¥
					</td>
					<td>
						411/71��412/71��413/71��414/71��415/71��611/71��612/71��613/71��614/71��615/71��617/71��618/71
					</td>
				</tr>
				<tr>
					<td>
						����¥
					</td>
					<td>
						511/71��512/71��513/72��514/73��711/74��713/71��714/72��715/74��711/72��712/74��713/71��714/71
					</td>
				</tr>
				<tr>
					<td>
						�˺�¥
					</td>
					<td>
						111/71��112/71��113/72��114/73��211/74��213/71��214/72��215/74��511/72��512/74��513/71��514/71
					</td>
				</tr>
				<tr>
					<td>
						�ź�¥
					</td>
					<td>
						111/71��112/71��113/72��114/73��211/74��213/71��214/72��215/74��511/72��512/74��513/71��514/71
					</td>
				</tr>
			</table>
		</div>

				<div class="buttontool" id="btn" align="center" >
					<button type="button" class="button2"
						onclick=""
						style="width:80px" id="buttonSave">
						�� ӡ
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;	
					<button type="button" class="button2"
						onclick=""
						style="width:80px" id="buttonSave">
						Excel����
					</button>				
				</div>	
	</body>
</html>
