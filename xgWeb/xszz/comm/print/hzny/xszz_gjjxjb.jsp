<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<jsp:directive.page import="java.util.ArrayList" />
<jsp:directive.page import="java.util.HashMap" />
<!-- ͷ�ļ� -->
<html>
<body>
	<!-- ��ӡ�ؼ�begin -->
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style media='print'>
			.noPrin{display:none;}
		</style>
	<!-- end -->


  <p align=center><b><span
style='font-size:22.0pt;font-family:����;letter-spacing:1.0pt'>��${xn}ѧ�꣩���ҽ�ѧ������������</span></b></p> 
  <p align=center><b>ѧУ��</b>${xxmc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Ժϵ��</b>${rs.xymc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>ѧ�ţ�${rs.xh }</b></p> 
  <input type="hidden" id="sfzh" value="${rs.sfzh }"/>
  <table width="100%" class="printstyle" align="center">
    <tr style="height:45px"> 
      <td width="8%" rowspan=4 align="center"><B>����<br/>���</B></td> 
      <td width="12%" align="center">����</td> 
      <td width="15%" align="center" colspan=4 >&nbsp;${rs.xm } </td> 
      <td width="8%" align="center" colspan=4 >�Ա�</td> 
      <td align="center" colspan=5 >&nbsp;${rs.xb } </td> 
      <td align="center"  colspan=4  >��������</td> 
      <td align="center"  colspan=6 >&nbsp;${rs.csrq } </td> 
    </tr> 
    <tr style="height:45px"> 
      <td align="center" >������ò</td> 
      <td align="center" colspan=4 >&nbsp;${rs.zzmmmc } </td> 
      <td align="center" colspan=4 >����</td> 
      <td align="center" colspan=5 >&nbsp;${rs.mzmc } </td> 
      <td align="center" colspan=4 >��ѧʱ��</td> 
      <td align="center" colspan=6 >&nbsp;${rs.rxrq } </td> 
    </tr> 
    <tr style="height:45px"> 
      <td align="center">רҵ</td> 
      <td align="center" colspan=4 >&nbsp; ${rs.zymc }</td> 
      <td align="center" colspan=4 >ѧ��</td> 
      <td align="center" colspan=5 >&nbsp;${rs.xz } </td> 
      <td align="center" colspan=4 >��ϵ�绰</td> 
      <td align="center" colspan=6 >&nbsp;${rs.lxdh } </td> 
    </tr> 
    <tr style="height:45px"> 

      <td align="center" >���֤��</td> 
      <td align="center" width=4.2% id="s0">&nbsp; </td> 
      <td align="center" width=4.2% id="s1">&nbsp; </td> 
      <td align="center" width=4.2% id="s2">&nbsp; </td> 
      <td align="center" width=4.2% id="s3" colspan=2 >&nbsp; </td> 
      <td align="center" width=4.2% id="s4">&nbsp; </td> 
      <td align="center" width=4.2% id="s5">&nbsp; </td> 
      <td align="center" width=4.2% id="s6" colspan=2 >&nbsp; </td> 
      <td align="center" width=4.2% id="s7" colspan=2 >&nbsp; </td> 
      <td align="center" width=4.2% id="s8">&nbsp; </td> 
      <td align="center" width=4.2% id="s9" colspan=2 >&nbsp; </td> 
      <td align="center" width=4.2% id="s10">&nbsp; </td> 
      <td align="center" width=4.2% id="s11">&nbsp; </td> 
      <td align="center" width=4.2% id="s12" colspan=2 >&nbsp; </td> 
      <td align="center" width=4.2% id="s13">&nbsp; </td> 
      <td align="center" width=4.2% id="s14">&nbsp; </td> 
      <td align="center" width=4.2% id="s15">&nbsp; </td> 
      <td align="center" width=4.2% id="s16">&nbsp; </td> 
      <td align="center" width=4.2% id="s17">&nbsp; </td> 
    </tr> 
    <tr style="height:45px"> 
      <td align="center" rowspan=2 ><B>ѧϰ<br/>���</B></td> 
      <td align="center" colspan=11 >�ɼ�������<u>&nbsp;&nbsp;&nbsp; &nbsp;</u><u>/</u><u>&nbsp; &nbsp;</u>������/��������</span></p></td> 
      <td align="center" colspan=13 >ʵ���ۺϿ����������ǡ������</td> 
    </tr> 
    <tr style="height:45px"> 
      <td colspan=11 align="center">���޿�<u>${rs.bxkms}</u>�ţ����м�������<u>����</u>��</td> 
      <td colspan=13 align="center">���ǣ�������<u>&nbsp;&nbsp;&nbsp;&nbsp;</u><u>/&nbsp;&nbsp;&nbsp;&nbsp;������/��������</td> 
    </tr> 
    <tr style="height:45px"> 
      <td rowspan=5 align="center" >
      <B>��ѧ<br/>�ڼ�<br/>��Ҫ<br/>��<br/>���</B></td> 
      <td align="center" >����</td> 
      <td align="center"  colspan=13 >��������</td> 
      <td align="center" colspan=10 >�佱��λ</td> 
    </tr> 
    <tr style="height:45px"> 
      <td align="center">&nbsp;${rs.hjrq1} </td> 
      <td align="center" colspan=13 >&nbsp;${rs.hjmc1}</td> 
      <td align="center" colspan=10 >&nbsp;${rs.bjdw1}</td> 
    </tr> 
    <tr style="height:45px"> 
      <td align="center" >&nbsp;${rs.hjrq2} </td> 
      <td align="center" colspan=13 >&nbsp;${rs.hjmc2} </td> 
      <td align="center" colspan=10 >&nbsp;${rs.bjdw2} </td> 
    </tr> 
    <tr style="height:45px"> 
      <td align="center"  >&nbsp;${rs.hjrq3} </td> 
      <td align="center"   colspan=13 >&nbsp;${rs.hjmc3}  </td> 
      <td align="center"  colspan=10 >&nbsp;${rs.bjdw3} </td> 
    </tr> 
    <tr style="height:45px"> 
      <td align="center">&nbsp; ${rs.hjrq4}</td> 
      <td align="center" colspan=13 >&nbsp;${rs.hjmc4}  </td> 
      <td align="center" colspan=10 >&nbsp;${rs.bjdw4} </td> 
    </tr> 
    <tr style="height:320px"> 
      <td align="center" ><B>����<br/>����</B></td> 
      <td colspan=24 align="right" valign="bottom" >
      	<p align="left" style="vertical-align: top;height:200px" >${rs.sqly }</p>
      	������ǩ��(��ǩ)�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/><br/><br/>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp;&nbsp; &nbsp;��</td> 
    </tr> 
    <tr height=0> 
      <td width=55 ></td> 
      <td width=84 ></td> 
      <td width=29 ></td> 
      <td width=29 ></td> 
      <td width=29 ></td> 
      <td width=21 ></td> 
      <td width=8 ></td> 
      <td width=29 ></td> 
      <td width=29 ></td> 
      <td width=6 ></td> 
      <td width=23 ></td> 
      <td width=1 ></td> 
      <td width=28 ></td> 
      <td width=29 ></td> 
      <td width=9 ></td> 
      <td width=20 ></td> 
      <td width=29 ></td> 
      <td width=29 ></td> 
      <td width=6 ></td> 
      <td width=23 ></td> 
      <td width=29 ></td> 
      <td width=29 ></td> 
      <td width=29 ></td> 
      <td width=29 ></td> 
      <td width=29 ></td> 
    </tr> 
  </table> 
 	<br/><br/><br/>
   <table width="100%" class="printstyle" align="center" style="font-size:12.0pt;font-family:����;">
    <tr  style="height: 300px"> 
      <td width="7.5%" align="center"><B>�Ƽ�<br/>����</B><br/>(100<br/>��)</td> 
      <td style="vertical-align: text-bottom">
      <p align="right" style="vertical-align:bottom;">�Ƽ��ˣ�����Ա������Σ�ǩ����&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;</p>
      <p align="right" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp; &nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;</p>
      </td> 
    </tr> 
    <tr  style="height: 300px"> 
      <td align="center"><B>Ժ<br/><br/>��ϵ��<br/><br/>��<br/><br/>��</B></td> 
      <td width=605 ><p style="height: 200px"><br/><br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;����飬���������ʵ����<bean:message key="lable.xb" />��ѡ����ȫԺ��Χ�ڹ�ʾ
      <u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>�죬�����飬<br/>����ȷ����ͬѧΪ2010-2011ѧ����ҽ�ѧ���ѡ�ˡ�<br/></p>
        <p align="right" style="vertical-align:bottom;">Ժϵ����ѧ�������쵼ǩ����&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;</p>
        <p align="right" style="vertical-align:bottom;">��Ժϵ���£�&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;</p>
        <p align="right" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp; &nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </p></td>
    </tr> 
    <tr style="height: 300px"> 
      <td align="center"><B>ѧ<br/><br/>У<br/><br/>��<br/><br/>��</B></td> 
     <td width=605 ><p style="height: 200px"><br/><br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;�����󣬲���У�ڹ�ʾ
      <u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>�������գ������飬�ֱ�����׼��ͬѧ��ù�<br/>�ҽ�ѧ��<br/></p>
        <p align="right" style="vertical-align:bottom;">��ѧУ���£�&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;</p>
        <p align="right" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp; &nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </p></td>
    </tr> 
  </table> 
  <p align="right">�Ʊ�ȫ��ѧ��������������&nbsp;&nbsp;2010���&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
 
  <div algin="left" style='layout-grid:15.6pt;line-height:28.0pt;'> 
  <p align=center style='
text-align:center;
line-height:150%;layout-grid-mode:char;'><b><span style='font-size:16.0pt;line-height:
150%;font-family:����_GB2312;color:#333333'>�����ҽ�ѧ��������������д˵��</span></b></p> 
  <span style='font-size:14.0pt;
font-family:����_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;��<bean:message key="lable.xb" />��ѧ������վ���ػ�ӡ�����ҽ�ѧ����������������֯��Ա������д��</span><br/> 
  <span lang=EN-US
style='font-size:14.0pt;font-family:����_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;1. </span><span
style='font-size:14.0pt;font-family:����_GB2312'>���Ϊһҳ���������棬������������ҳ����</span><span
style='font-size:14.0pt;font-family:����_GB2312;'>�����дӦ���ּ���������Ϣ������</span><span
style='font-size:14.0pt;font-family:����_GB2312'>����Ϳ�����ݻ���ֿհ������ȫ��ʹ�ð��������֣�������д��ʽ<span
lang=EN-US>****</span>��<span lang=EN-US>**</span>��<span lang=EN-US>**</span>�ա�����ӡʹ������������壩</span><br/> 
 <span lang=EN-US
style='font-size:14.0pt;font-family:����_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;2. </span><span
style='font-size:14.0pt;font-family:����_GB2312'>��������ѧ�����дΪ��������ʼ����ѧ�����һѧ�ꡣ��<span
lang=EN-US>2010</span>���＾ѧ�����Ӧ��д��<span lang=EN-US>2009</span>��<span
lang=EN-US>2010</span>ѧ�ꡱ���Դ����ơ�</span><br/> 
  <span lang=EN-US
style='font-size:14.0pt;font-family:����_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;3. </span><span
style='font-size:14.0pt;font-family:����_GB2312'>�����ѧϰ�ɼ�������Χָ���꼶��רҵ���ۺϿ����ɼ�������Χָ�༶������ע����ѡ��Χ����������</span><br/> 
  <span lang=EN-US
style='font-size:14.0pt;font-family:����_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;4. </span><span
style='font-size:14.0pt;font-family:����_GB2312'>����С��������ɡ�������дӦ��ȫ����ʵ��</span><span
style='font-size:14.0pt;font-family:����_GB2312;'>�ܹ���ʵ��ӳѧ��ѧϰ</span><span
style='font-size:14.0pt;font-family:����_GB2312'>�ɼ�����</span><span
style='font-size:14.0pt;font-family:����_GB2312;'>�����ʵ���������������ۺ����ʵȷ����ر�ͻ����</span><span
style='font-size:14.0pt;font-family:����_GB2312'>����������<span lang=EN-US>200</span>�����ҡ�</span><br/> 
  <span lang=EN-US
style='font-size:14.0pt;font-family:����_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;5. </span><span
style='font-size:14.0pt;font-family:����_GB2312'>����С��Ƽ������������дӦ��������Ҫ������������<span
lang=EN-US>100</span>�����ҡ��Ƽ��˱���������ѧ���ĸ���Ա������Σ���������Ȩ�Ƽ���</span><br/> 
  <span lang=EN-US
style='font-size:14.0pt;font-family:����_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;6. </span><span
style='font-size:14.0pt;font-family:����_GB2312'>����з���ǩ��֮���������������Ա����ǩд�����������˴�д�Ƽ������ǩ����</span> <br/> 
  <span lang=EN-US
style='font-size:14.0pt;font-family:����_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;7. </span><span
style='font-size:14.0pt;font-family:����_GB2312'>��Ժ��ϵ�������һ���мӸ�Ժ��ϵ���������¡�</span><br/> 
 <span lang=EN-US
style='font-size:14.0pt;font-family:����_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;8. </span><span
style='font-size:14.0pt;font-family:����_GB2312'>ѧ����֤���֤������ֻ�辭��<bean:message key="lable.xb" />��飬��������ͣ���Ҫ���ñ������顣</span><br/> 
</div> 
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
    <script type="text/javascript">
    	var sfzh = $('sfzh').value;
    	for(var i=0;i<sfzh.length;i++){
    	var id = "s" + i;
    	var sfzhs = sfzh.substring(i,i+1);
    	if($(id)){
    		$(id).innerHTML = sfzhs; 
    	}
    	}
    
    </script>
    </div>
    <p>&nbsp;</p>
</body>
</html>
