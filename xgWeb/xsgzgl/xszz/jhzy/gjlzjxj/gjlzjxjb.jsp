<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<html>
<head>
<title>��ͨ���Ƹ�У���ߵ�ְҵѧУ������־��ѧ�������</title>
</head>
<body > 
<!-- ��ӡ�ؼ�begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
	<!-- end -->
<div class=Section1 style='layout-grid:15.6pt'> 
  
  <p align=center style='text-align:center;'>
  		<b><span style='font-size:18.0pt;font-family:����'>��${rs.xn}ѧ�꣩������־��ѧ������������</span></b></p> 
  		<b>ѧУ��</b>��ְҵ����ѧԺ<b><span style='font-family:"Times New Roman";'> </span></b>
  		<b>Ժϵ</b>��${rs.xymc }<span style='font-family:&quot;Times New Roman&quot;'> </span>
  		<b>רҵ</b>��${rs.zymc }<span style='font-family:"Times New Roman";'> </span>
		<b>�༶</b>��${rs.bjmc }</span>
  	<table width="100%" class="printstyle">
    <tr> 
      <td height="55px" rowspan=4 align="center" >��<br/>��<br/>��<br/>��</td> 
      <td height="55px" width="10%" align="center" >����</td> 
      <td colspan=5 align="center" >${rs.xm }</td> 
      <td colspan=2 align="center" >�Ա�</td> 
      <td colspan=5 align="center" >${rs.xb }</td> 
      <td colspan=6 align="center" >��������</td> 
      <td  colspan=6 align="center" >${rs.csrq }</td> 
    </tr> 
    <tr> 
      <td   height="55px"align="center">ѧ��</td> 
      <td  colspan=5 align="center">${rs.xh }</td> 
      <td  colspan=2 align="center">����</td> 
      <td  colspan=5 align="center">${rs.mzmc }</td> 
      <td  colspan=6 align="center">��ѧʱ��</td> 
      <td  colspan=6 align="center">${rs.rxrq }</td> 
    </tr> 
    <tr> 
      <td height="55px" align="center">������ò</td> 
      <td colspan=12 align="center">${rs.zzmmmc }</td> 
      <td colspan=6 align="center">��ϵ�绰</td> 
      <td colspan=6 align="center">${rs.sjhm }</td> 
    </tr> 
    <tr align="center"> 
    <td height="55px">���֤��</td> 
    <td width="4%" colspan=2 >
		<div align="center">
			<bean:write name='rs' property="sfzh1" />
		</div>
	</td>
	<td width="4%">
		<div align="center">
			<bean:write name='rs' property="sfzh2" />
		</div>
	</td>
	<td width="4%">
		<div align="center">
			<bean:write name='rs' property="sfzh3" />
		</div>
	</td>
	<td width="4%">
		<div align="center">
			<bean:write name='rs' property="sfzh4" />
		</div>
	</td>
	<td width="4%">
		<div align="center">
			<bean:write name='rs' property="sfzh5" />
		</div>
	</td>
	<td width="4%" colspan=2 >
		<div align="center">
			<bean:write name='rs' property="sfzh6" />
		</div>
	</td>
	<td width="4%">
		<div align="center">
			<bean:write name='rs' property="sfzh7" />
		</div>
	</td>
	<td width="4%" >
		<div align="center">
			<bean:write name='rs' property="sfzh8" />
		</div>
	</td>
	<td width="4%" colspan=3 >
		<div align="center">
			<bean:write name='rs' property="sfzh9" />
		</div>
	</td>
	<td width="4%">
		<div align="center">
			<bean:write name='rs' property="sfzh10" />
		</div>
	</td>
	<td width="4%"  colspan=2 >
		<div align="center">
			<bean:write name='rs' property="sfzh11" />
		</div>
	</td>
	<td width="4%">
		<div align="center">
			<bean:write name='rs' property="sfzh12" />
		</div>
	</td>
	<td width="4%"  colspan=2 >
		<div align="center">
			<bean:write name='rs' property="sfzh13" />
		</div>
	</td>
	<td width="4%">
		<div align="center">
			<bean:write name='rs' property="sfzh14" />
		</div>
	</td>
	<td width="4%">
		<div align="center">
			<bean:write name='rs' property="sfzh15" />
		</div>
	</td>
	<td width="4%">
		<div align="center">
			<bean:write name='rs' property="sfzh16" />
		</div>
	</td>
	<td width="4%">
		<div align="center">
			<bean:write name='rs' property="sfzh17" />
		</div>
	</td>
	<td width="4%" >
		<div align="center">
			<bean:write name='rs' property="sfzh18" />
		</div>
	</td>
    </tr> 
    <tr align="center"> 
      <td rowspan=2 >ѧ<br/>ϰ<br/>��<br/>��</td> 
      <td height="55px" colspan=10 >�ɼ�������<U>${rs.cjpm}/${rs.bjzrs}</U>������/��������</td> 
      <td height="55px" colspan=15 >
      	ʵ���ۺϿ���������
      	<logic:notEmpty name="rs" property="sxzhkppm">
      		<logic:equal  name="rs" property="sxzhkppm" value="��">
      			��<img src="/xgxt/pictures/xszz/gou.jpg"></img>
      			���
      		</logic:equal>
      		<logic:equal  name="rs" property="sxzhkppm" value="��">
      			�ǡ�
      			��<img src="/xgxt/pictures/xszz/gou.jpg"></img>
      		</logic:equal>
      	</logic:notEmpty>
      	<logic:empty name="rs" property="sxzhkppm">
      			�ǡ�
      			���
      	</logic:empty>
  	  </td> 
    </tr> 
    <tr align="center"> 
      <td height="55px" colspan=10 >���޿�<U>${rs.bxkms}</U>�ţ����м�������<U>${rs.jgms}</U>��</td> 
      <td colspan=15 >���ǣ�������<U>${rs.zhkppm}/${rs.bjzrs }</U>������/��������</td> 
    </tr> 
    <tr align="center" > 
      <td rowspan=5 >��ѧ��<br/>����Ҫ<br/>��<br/>���</td> 
      <td height="55px" colspan=2 >����</td> 
      <td colspan=10 >��������</td> 
      <td colspan=13 >�佱��λ</td> 
    </tr> 
    <tr align="center"> 
      <td height="55px" colspan=2  >${rs.hjsj1}</td> 
      <td colspan=10  >${rs.hjmc1}</td> 
      <td colspan=13  >${rs.bjdw1}</td> 
    </tr> 
    <tr align="center"> 
      <td height="55px" colspan=2  >${rs.hjsj2}</td> 
      <td colspan=10  >${rs.hjmc2}</td> 
      <td colspan=13  >${rs.bjdw2}</td> 
    </tr> 
    <tr align="center"> 
      <td height="55px" colspan=2  >${rs.hjsj3}</td> 
      <td colspan=10  >${rs.hjmc3}</td> 
      <td colspan=13  >${rs.bjdw3}</td> 
    </tr> 
    <tr align="center"> 
      <td height="55px" colspan=2  >${rs.hjsj4}</td> 
      <td colspan=10  >${rs.hjmc4}</td> 
      <td colspan=13  >${rs.bjdw4}</td> 
    </tr> 
    <tr align="center" > 
      <td width=8% height="55px" rowspan=4 > ��ͥ�������</td> 
      <td  > ��ͥ����</td> 
      <td colspan=11 >
      	<logic:empty name="rs" property="jthk">
			������&nbsp;&nbsp;
			��ũ��
		</logic:empty>
		<logic:equal name="rs" property="jthk" value="����">
			<img src="/xgxt/pictures/xszz/gou.jpg"></img>
			����&nbsp;&nbsp;
			��ũ��
		</logic:equal>
		<logic:equal name="rs" property="jthk" value="ũ��">
			������&nbsp;&nbsp;
			<img src="/xgxt/pictures/xszz/gou.jpg"></img>
			ũ��
		</logic:equal>	
	  </td> 
      <td colspan=4 height="55px">������Դ</td> 
      <td colspan=9 >${rs.srly }</td> 
    </tr> 
    <tr align="center"> 
      <td width=8% height="55px">��ͥ����<br/>����</td> 
      <td colspan=11 >${rs.jtnzsr }</td> 
      <td colspan=4 >��ͥ�˿�<br/>����</td> 
      <td colspan=9 >${rs.jtrks }��</td> 
    </tr> 
    <tr align="center"> 
      <td width=8% height="55px">��ͥסַ</td> 
      <td colspan=11 >${rs.jtdz }</td> 
      <td colspan=4 >��������</td> 
      <td colspan=9 >${rs.jtyb }</td> 
    </tr> 
    <tr align="center"> 
      <td width=8%  height="55px">�϶����</td> 
      <td colspan=24 >
     	<logic:notEmpty name="rs" property="xxtjdc">
			<logic:equal name="rs" property="xxtjdc" value="�ر�����">
				<u>A����ͥ�����ر�����</U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B����ͥ����һ������ 
			</logic:equal>
			<logic:equal name="rs" property="xxtjdc" value="һ������">
				A����ͥ�����ر�����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u>B����ͥ����һ������ </U> 
			</logic:equal>
		</logic:notEmpty>
		<logic:empty name="rs" property="xxtjdc">
				A����ͥ�����ر�����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B����ͥ����һ������
		</logic:empty>
      </td> 
    </tr> 
    </table> 
   <br/> <br/> 
    <table width="100%" class="printstyle">
    <tr height="270px"> 
      <td width=8%  align="center"> ����<br/>����<br/>(200��)</td> 
      <td colspan=25 >
      		<p align="left " style="height: 100px">${rs.sqly }</p>
      		<p align="right" style="padding-right: 100px">������ǩ��(��ǩ)��</p><br/>
      		<p align="right" style="padding-right: 50px">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��</p>
      		<br/><br/>
      </td> 
    </tr> 
    <tr align="center" height="270px"> 
      <td >Ժ<br/>��ϵ��<br/>��<br/>��</td> 
      <td  colspan=25 valign=top >
      		<p align="left " style="height: 100px">${rs.xyshyj }</p>
      		<p align="right" style="padding-right: 100px">Ժϵ����ѧ�������쵼ǩ����</p><br/>
      		<p align="right" style="padding-right: 70px">��Ժϵ���£�</p><br/>
    		<p align="right" style="padding-right: 50px">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��</p><br/>
    </tr> 
    <tr align="center" height="270px"> 
      <td  >ѧ<br/>У<br/>��<br/>��</td> 
      <td  colspan=25 valign=top >
      		<p align="left " style="height: 40px"></p>
			<p align="left " style="height: 100px">�����󣬲���У�ڹ�ʾ<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>�������գ������飬�ֱ�����׼��ͬѧ��ù�����־��ѧ��</p>
      		<p align="right" style="padding-right: 70px">��ѧУ���£�</p><br/>
    		<p align="right" style="padding-right: 50px">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��</p><br/>
	  </td> 
    </tr> 
<%--    <tr height=0 align="center"> --%>
<%--      <td width=64 ></td> --%>
<%--      <td width=72 ></td> --%>
<%--      <td width=42 ></td> --%>
<%--      <td width=6 ></td> --%>
<%--      <td width=28 ></td> --%>
<%--      <td width=28 ></td> --%>
<%--      <td width=28 ></td> --%>
<%--      <td width=28 ></td> --%>
<%--      <td width=22 ></td> --%>
<%--      <td width=6 ></td> --%>
<%--      <td width=31 ></td> --%>
<%--      <td width=25 ></td> --%>
<%--      <td width=9 ></td> --%>
<%--      <td width=1 ></td> --%>
<%--      <td width=18 ></td> --%>
<%--      <td width=28 ></td> --%>
<%--      <td width=27 ></td> --%>
<%--      <td width=1 ></td> --%>
<%--      <td width=28 ></td> --%>
<%--      <td width=20 ></td> --%>
<%--      <td width=8 ></td> --%>
<%--      <td width=28 ></td> --%>
<%--      <td width=27 ></td> --%>
<%--      <td width=29 ></td> --%>
<%--      <td width=28 ></td> --%>
<%--      <td width=28 ></td> --%>
<%--    </tr> --%>
  </table> 
  <p align="right"><span
style='font-family:����;"Times New Roman"'>�Ʊ��㽭ʡѧ�������������ġ�</span><span lang=EN-US>2010</span><span
style='font-family:����;"Times New Roman"'>���</span></p> 
</div> 
<div align="center" class='noPrin'>
		<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
			ҳ������
		</button>
		<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
			��ӡԤ��
		</button>
		<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
			ֱ�Ӵ�ӡ
		</button>
	</div>
</body>
</html>
