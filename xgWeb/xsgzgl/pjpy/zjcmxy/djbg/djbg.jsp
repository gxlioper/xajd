<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//Dth XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/Dth/xhtml1-transitional.dth">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
	</head>

	<body>
		<html:form action="/stu_info_add" method="post">
			<div align="center" style="font-size:25px;font:'����' ">
				<b>�㽭��ýѧԺ${rs.xmmc }����ߵǼǱ�</b>
			</div>
			<br/>
			<div align="center" style="font-size:12px; ">
				${rs.xn }&nbsp; ѧ��
				һ&nbsp; ѧ�� 
				<u>${rs.xymc }&nbsp;</u>ѧԺ
				<u>${rs.zymc }&nbsp;</u>רҵ
				<u>${rs.bjmc }&nbsp;</u>��
			</div>
			<table width="100%" id="rsT" class="printstyle">
				<tr> 
			      <th width=60 colspan=2 height="30" > �� ��</th> 
			      <th width=84 >${rs.xm }&nbsp;</th> 
			      <th width=60 colspan=2 > �� ��</th> 
			      <th width=96 colspan=2 >${rs.xb }&nbsp;</th> 
			      <th width=84 colspan=3 > ��������</th> 
			      <th width=84 colspan=3 >${rs.csrq }&nbsp;</th> 
			      <th width=84 > ������ò</th> 
			      <th width=84 colspan=2 >${rs.zzmmmc }&nbsp;</th> 
			    </tr>
			    <tr> 
			      <th width=60 colspan=2 height="30" > �� ��</th> 
			      <th width=84 >${rs.mzmc }&nbsp; </th> 
			      <th width=60 colspan=2 > ְ ��</th> 
			      <th width=96 colspan=2 >${rs.zw }&nbsp; </th> 
			      <th width=84 colspan=3 > ƽ������</th> 
			      <th width=84 colspan=3 >${rs.pjf }&nbsp;</th> 
			      <th width=84 > ������</th> 
			      <th width=84 colspan=2 >&nbsp;${rs.xmmc } </th> 
			    </tr> 
			    <tr> 
			      <th width=40 rowspan=8  height="30" style="text-align:center"> ��<br>ѧ<br>��<br>ѧ<br>ϰ<br>��<br>��</th> 
			      <th width=150 colspan=3 height="30" > �� �� �� ��</th> 
			      <th width=47 colspan=2 > �ɼ�</th> 
			      <th width=159 colspan=5 > �� �� �� ��</th> 
			      <th width=47 > �ɼ�</th> 
			      <th width=149 colspan=3 > �� �� �� ��</th> 
			      <th width=48 > �ɼ�</th> 
			    </tr> 
			    <tr> 
			      <th width=150 colspan=3 height="30" >${rs.kcmc1 }&nbsp; </th> 
			      <th width=47 colspan=2 >${rs.cj1 }&nbsp; </th> 
			      <th width=159 colspan=5 >${rs.kcmc2 }&nbsp;</th> 
			      <th width=47 >${rs.cj2 }&nbsp; </th> 
			      <th width=149 colspan=3 >${rs.kcmc3 }&nbsp; </th> 
			      <th width=48 >${rs.cj3 }&nbsp; </th> 
			    </tr> 
			    <tr> 
			      <th width=150 colspan=3 height="30" >${rs.kcmc4 }&nbsp; </th> 
			      <th width=47 colspan=2 >${rs.cj4 }&nbsp; </th> 
			      <th width=159 colspan=5 >${rs.kcmc5}&nbsp;</th> 
			      <th width=47 >${rs.cj5 }&nbsp; </th> 
			      <th width=149 colspan=3 >${rs.kcmc6 }&nbsp; </th> 
			      <th width=48 >${rs.cj6 }&nbsp; </th> 
			    </tr> 
			    <tr> 
			      <th width=150 colspan=3 height="30" >${rs.kcmc7 }&nbsp; </th> 
			      <th width=47 colspan=2 >${rs.cj7 }&nbsp; </th> 
			      <th width=159 colspan=5 >${rs.kcmc8}&nbsp;</th> 
			      <th width=47 >${rs.cj8 }&nbsp; </th> 
			      <th width=149 colspan=3 >${rs.kcmc9 }&nbsp; </th> 
			      <th width=48 >${rs.cj9 }&nbsp; </th> 
			    </tr> 
			    <tr> 
			      <th width=150 colspan=3 height="30" >${rs.kcmc10 }&nbsp; </th> 
			      <th width=47 colspan=2 >${rs.cj10 }&nbsp; </th> 
			      <th width=159 colspan=5 >${rs.kcmc11}&nbsp;</th> 
			      <th width=47 >${rs.cj11 }&nbsp; </th> 
			      <th width=149 colspan=3 >${rs.kcmc12 }&nbsp; </th> 
			      <th width=48 >${rs.cj12 }&nbsp; </th> 
			    </tr> 
			    <tr> 
			      <th width=150 colspan=3 height="30" >${rs.kcmc13 }&nbsp; </th> 
			      <th width=47 colspan=2 >${rs.cj13 }&nbsp; </th> 
			      <th width=159 colspan=5 >${rs.kcmc14}&nbsp;</th> 
			      <th width=47 >${rs.cj14 }&nbsp; </th> 
			      <th width=149 colspan=3 >${rs.kcmc15 }&nbsp; </th> 
			      <th width=48 >${rs.cj15 }&nbsp; </th> 
			    </tr> 
			     <tr> 
			      <th width=150 colspan=3 height="30" >${rs.kcmc16 }&nbsp; </th> 
			      <th width=47 colspan=2 >${rs.cj16 }&nbsp; </th> 
			      <th width=159 colspan=5 >${rs.kcmc17}&nbsp;</th> 
			      <th width=47 >${rs.cj17 }&nbsp; </th> 
			      <th width=149 colspan=3 >${rs.kcmc18 }&nbsp; </th> 
			      <th width=48 >${rs.cj18 }&nbsp; </th> 
			    </tr> 
			    <tr> 
			      <th width=150 colspan=3 height="30" >${rs.kcmc19 }&nbsp; </th> 
			      <th width=47 colspan=2 >${rs.cj19 }&nbsp; </th> 
			      <th width=159 colspan=5 >${rs.kcmc20}&nbsp;</th> 
			      <th width=47 >${rs.cj20 }&nbsp; </th> 
			      <th width=149 colspan=3 >${rs.kcmc21 }&nbsp; </th> 
			      <th width=48 >${rs.cj21 }&nbsp; </th> 
			    </tr> 
			    <tr> 
			      <th width=15 height = "300" > ��Ҫ�¼�</th> 
			      <th width=600 colspan=15 >&nbsp; </th> 
			    </tr> 
			    <tr> 
			      <th width=40  height = "150" style="text-align:center" > ѧ<br>Ժ<br>��<br>��<br>ί<br>��<br>��<br>��<br>��</th> 
			      <th width=276 colspan=7 > <br>���� �£� ��&nbsp; ��&nbsp; ��</th> 
			      <th width=40 style="text-align:center" > ѧ<br>У<br>��<br>��</th> 
			      <th width=289 colspan=7 > <br>���� �£� ��&nbsp; ��&nbsp; ��</th> 
			    </tr> 
			    <tr height=0> 
			      <th width=36 ></th> 
			      <th width=24 ></th> 
			      <th width=84 ></th> 
			      <th width=42 ></th> 
			      <th width=18 ></th> 
			      <th width=29 ></th> 
			      <th width=67 ></th> 
			      <th width=12 ></th> 
			      <th width=35 ></th> 
			      <th width=37 ></th> 
			      <th width=8 ></th> 
			      <th width=47 ></th> 
			      <th width=29 ></th> 
			      <th width=84 ></th> 
			      <th width=36 ></th> 
			      <th width=48 ></th> 
			    </tr> 
			</table>
			<br/>
			<div>
				ע��1������һʽ�ķݣ�ѧ�����˵���һ�ݣ�ѧԺһ�ݣ���ίһ�ݣ�Уѧ����һ�ݣ� 2���øֱ���д���ּ��������ѧԺ��ѧУ���£��쵼ǩ�ַ���Ч��
			</div>
		</html:form>
	</body>
</html>
