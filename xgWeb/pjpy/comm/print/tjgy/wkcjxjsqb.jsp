<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<object id=eprint classid="clsid:CA03A5A8-9890-49BE-BA4A-8C524EB06441"
			codebase="images/webprint.cab" viewasext>
		</object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
	</head>
	<body>
		<center>
		    <p><span style="font-size:18px;font-family:����">�������ͨ��У����ѧ���Ƽ��ǼǱ�</span></p>
		    <p style="height:50px"></p>
		
  <table border="0" width="652px">
  <tr>
  <td align="left">ѧУ����</td>
  <td align="left">${xxmc }</td>
  <td  align="right">�Ƽ��ܽ������</td>
  <td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
  </tr>
  </table>	
  </center>
   <table class="printtab"> 
    <tr align="center"> 
      <td width=91 colspan=2 nowrap class="Normal"> <b>����</b></td> 
      <td width=94 nowrap class="Normal">${rs.xm }</td> 
      <td width=59 nowrap class="Normal"> <b>�Ա�</b></td> 
      <td width=99 colspan=3 nowrap class="Normal">${rs.xb } </td> 
      <td width=84 colspan=2 nowrap class="Normal"> <b>��������</b></td> 
      <td width=96 nowrap class="Normal">${rs.csrq }</td> 
      <td width=132 rowspan=4 nowrap class="Normal"> 
      <img src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
									border="0" align="absmiddle" style="width:118px;height:130px" />
      </td> 
    </tr> 
    <tr align="center"> 
      <td width=91 colspan=2 nowrap class="Normal"> <b>�꼶</b></td> 
      <td width=94 nowrap class="Normal">${rs.nj }</td> 
      <td width=59 nowrap class="Normal"> <b>ϵ</b></td> 
      <td width=99 colspan=3 nowrap class="Normal">${rs.xymc } </td> 
      <td width=84 colspan=2 nowrap class="Normal"> <b>רҵ</b></td> 
      <td width=96 nowrap class="Normal">${rs.zymc } </td> 
    </tr> 
    <tr align="center"> 
      <td width=91 colspan=2 nowrap class="Normal"> <b>���ְ��</b></td> 
      <td width=94 nowrap class="Normal">&nbsp; </td> 
      <td width=59 nowrap class="Normal"> <b>�س�</b></td> 
      <td width=99 colspan=3 nowrap class="Normal">${rs.tc } </td> 
      <td width=84 colspan=2 nowrap class="Normal"> <b>������ò</b></td> 
      <td width=96 nowrap class="Normal">${rs.zzmmmc } </td> 
    </tr> 
    <tr align="center"> 
      <td width=91 colspan=2 nowrap class="Normal"> <b>ͨѶ��ַ</b></td> 
      <td width=252 colspan=5 nowrap class="Normal">&nbsp; ${rs.jtdz }</td> 
      <td width=84 colspan=2 nowrap class="Normal"> <b>�ʱ�</b></td> 
      <td width=96 nowrap class="Normal">${rs.jtyb } </td> 
    </tr> 
    <tr> 
      <td width=91 colspan=2 nowrap class="Normal" align="center"> 
      <b>
               ��<br/>
               Ҫ<br/>
               ��<br/>
               ��</b>
      </td> 
      <td width=564 colspan=9 valign=top nowrap class="Normal"> <p style="height:200px"></p></td> 
    </tr> 
    <tr align="center"> 
      <td width=288 colspan=5 class="Normal"> <b>ѧ���ܳɼ����꼶��רҵ����λ��</b></td> 
      <td width=367 colspan=6 nowrap class="Normal">&nbsp; </td> 
    </tr> 
    <tr align="center"> 
      <td width=288 colspan=5 class="Normal"> <b>ѧ���ۺϲ������꼶��רҵ����λ��</b></td> 
      <td width=367 colspan=6 nowrap class="Normal">&nbsp; </td> 
    </tr> 
    <tr  height="200px"> 
      <td width=77 nowrap class="Normal" align="center"> <b>
                  ѧ<br/>
                  ��<br/>
                  ��<br/>
                  ��<br/>
                  ��<br/>
                  ��<br/>
                  ��<br/>
                  ��</b></td> 
      <td width=579 colspan=10 valign=top nowrap class="Normal"> </td> 
    </tr> 
    <tr > 
      <td  rowspan=3 nowrap class="Normal" align="center" > 
      <b>
                   ѧ<br/>
                   У<br/>
                   ��<br/>
                   ��<br/>
      </b>
      </td> 
      <td colspan="5">
      <p style="height:180px"></p>
         <p align="right">����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
         <p align="right">&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;�� </p>
      </td>
      <td  colspan=2 rowspan=3 nowrap class="Normal" align="center" > 
      <b>
                  ��<br/>
                  ��<br/>
                  ί<br/>
                  ��<br/>
                  ��<br/>
                  ��<br/></b></td> 
       <td colspan="3">
         <p style="height:180px"></p>
         <p align="right">����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
         <p align="right">&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;�� </p>
       </td>
    </tr> 
  </table> 
    <table align="center">
    <tr>
    <td width="650">
    <div align="left">
                           ע���˱�һʽ���ݣ�����һ��ѧ�����ű��棬һ�ݴ��뱾�˵�����
    </div>
    </td>
    </tr>
    </table>
		<div align="center" class='noPrin'>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				ҳ������
			</button>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				��ӡԤ��
			</button>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;"
				id="printButton">
				ֱ�Ӵ�ӡ
			</button>
		</div>
	</body>
</html>
