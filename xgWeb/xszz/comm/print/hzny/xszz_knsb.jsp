<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<jsp:directive.page import="java.util.ArrayList" />
<jsp:directive.page import="java.util.HashMap" />
<!-- ͷ�ļ� -->
<html:html>
<body>
	<!-- ��ӡ�ؼ�begin -->
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style media='print'>
			.noPrin{display:none;}
		</style>
	<!-- end -->
	<html:form action="/typj" method="post">
		  <p align=center style='text-align:center'><b><span
style='font-size:18.0pt;font-family:����'>����ũҵ��ѧ��ͥ��������ѧ���϶������</span></b></p> 
			<table width="100%" id="rsT" class="printstyle" align="center">
      <tr  style="height: 45px" align="center"> 
        <td width="5%" rowspan=4> <p align=center style='
  text-align:center'><b><span
  style='font-family:������'>ѧ<br/>��<br/>��<br/>��<br/>��<br/>��<br/>��<br/>��</span></b></p></td> 
        <td width="9%"> <p align=center style='text-align:center'><span
  style='font-family:������'>��&nbsp;��</td> 
        <td width="18%"  colspan=3>&nbsp;${rs.xm } </td> 
        <td width="6%"  colspan=2> <p align=center style='text-align:center'><span
  style='font-family:������'>��&nbsp;��</span></p></td> 
        <td width="11%"  colspan=2>&nbsp;${rs.xb } </td> 
        <td width="8.5%"  colspan=2> <p align=center style='text-align:center'><span
  style='font-family:������'>��������</span></p></td> 
        <td width="14%"  colspan=2>&nbsp; ${rs.csrq }</td> 
        <td width="7%" >��&nbsp;��</td> 
        <td width="7%" >${rs.mzmc }</td>
      </tr> 
      <tr  style="height: 45px" align=center> 
        <td > ���֤<br/>����</td> 
        <td  colspan=5>&nbsp;${rs.sfzh } </td> 
        <td  colspan=2> <p align=center style='text-align:center'><span
  style='font-family:������'>������ò</span></p></td> 
        <td  colspan=2>&nbsp;${rs.zzmmmc } </td> 
        <td  colspan=2> <p align=center style='text-align:center'><span
  style='font-family:������'>��ͥ�˾�<br/>������</span></p></td> 
        <td   colspan=2> <p><span style='font-family:������'>${rs.jtrjsr }Ԫ</span></p></td> 
      </tr> 
      <tr  style="height: 45px" align=center> 
        <td >ѧ&nbsp;Ժ</td> 
        <td colspan=3 align=center >${rs.xymc } </td> 
        <td colspan=2 align=center>ר&nbsp;ҵ</td> 
        <td colspan=4>${rs.zymc }</td> 
        <td width="7%">ѧ&nbsp;��</td> 
        <td colspan=3>&nbsp; ${rs.xh }</td> 
      </tr> 
      <tr  style="height: 45px" align=center> 
        <td> <p align=center style='text-align:center'><span
  style='font-family:������'>��&nbsp;��</td> 
        <td  width="13%">&nbsp;${rs.nj } </td> 
        <td  colspan=2 width="5%"> <p align=center style='text-align:center'><span
  style='font-family:������'>��</span></p></td> 
        <td  colspan=2>&nbsp;${rs.bjmc } </td> 
        <td  colspan=2> <p align=center style='text-align:center'><span
  style='font-family:������'>��У��ϵ�绰</span></p></td> 
        <td  colspan=6>&nbsp; ${rs.lxdh }</td> 
      </tr> 
      <tr  > 
        <td style="height: 210px" > <p align=center style='text-align:center'><b>
        <span style='font-family:������'>
        	ѧ<br/>��<br/>��<br/>��<br/>��<br/>��<br/>��<br/>��<br/>��<br/>��
        </span></b></p></td> 
       
        <td colspan=14 valign="bottom" align=right> 
          <p align="left"><span lang=EN-US style='font-family:������;height:100px;text-align: left'  >${rs.sqly }</span></p>
           <p><span style='font-family:������;text-align: right'  >ѧ��ǩ�֣�<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></span>��<b><u><span
  lang=EN-US>&nbsp;&nbsp; </span></u></b>��<u><span lang=EN-US>&nbsp;&nbsp; </span></u>��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p> 
          <p align="left"><b><span style='font-family:������' >ע��������ϸ���˵����</span></b></p></td> 
      </tr> 
      <tr  style="height: 120px"> 
        <td rowspan=3> <p align=center style='
  text-align:center'><b><span
  style='font-family:������'>��<br/>��<br/>��<br/>��</span></b></p></td> 
        <td  rowspan=3 > <p align=center style='
  text-align:center'><span
  style='font-family:������'>��<br/>��<br/>��<br/>��</span></p></td> 
        <td colspan=4 style="height: 65px"> A.��ͥ�������ѡ�</td> 
        <td rowspan=3  align="center">��<br/>��<br/>��<br/>��</td> 
        <td colspan=8 rowspan=3 valign="bottom">�϶�����С���鳤ǩ�֣�<span
  lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp; <u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></span>��<u><span
  lang=EN-US>&nbsp;&nbsp;&nbsp; </span></u>��<u><span lang=EN-US>&nbsp;&nbsp;&nbsp; </span></u>��</span></p></td> 
      </tr> 
      <tr  style="height: 65px"> 
        <td colspan=4>B.��ͥ�����ر����ѡ�</td>
  	  </tr> 
      <tr style="height: 65px">  
        <td colspan=4>C.��ͥ���ò����ѡ�</td> 
        </tr> 
      <tr  style="height: 250px"> 
        <td> <p align=center style='
  text-align:center'><b><span
  style='font-family:������'>��<br/>��<br/>��<br/>��</span></b></p></td> 
        <td align="center">Ժ��ϵ��<br/>���</td> 
        <td colspan=6 valign=top> 
          <span style="line-height: 30px">���϶�����С���Ƽ���Ժ��ϵ���϶���<br/>����������˺�<br/>
          ��&nbsp;ͬ���϶�����С�������<br/>
          ��&nbsp;��ͬ���϶�����С�����������<br/>Ϊ<u><span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u><span
  lang=EN-US>&nbsp;</span>��<br/>
          �϶��������鳤ǩ�֣�
        <p align="right"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;<br/>
        ���Ӹ�Ժϵ���£�&nbsp;&nbsp;</p> </span>
        <td  colspan=2  align=center> 
        ѧ����<br/>����<br/>������</p>
        </td> 
        <td  colspan=5 valign=top>
         <span style="line-height: 30px">
		��ѧ������Ժ��ϵ�����룬�����������<br/>ʵ��<br/>
		��ͬ���϶������������<br/>
		����ͬ���϶����������������<br/>&nbsp;&nbsp;&nbsp;&nbsp;Ϊ��<u>&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��<br/> 
        ������ǩ�֣�
        <p align="right">&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;
         <br/>���Ӹǲ��Ź��£�&nbsp;&nbsp;</p></span></td>
      </tr> 
    </table> 
  </div> 
  <p><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style='font-family:����'>ע���˱���д������͡�����ũҵ��ѧѧ����ͥ��������һ��������С�������϶���</span></b></p> 
	</html:form>
	<div align="center" class='noPrin'>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
			ҳ������
		</button>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
			��ӡԤ��
		</button>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
			ֱ�Ӵ�ӡ
		</button>
	</div>
</body>
</html:html>
