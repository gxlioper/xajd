<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<%--<object id=eprint classid="clsid:CA03A5A8-9890-49BE-BA4A-8C524EB06441"
			codebase="images/webprint.cab" viewasext>
		</object>--%>
		<style media='print'>
			.noPrin{display:none;}
		</style>
	</head>
	<body>
		<center>
		    <p><span style="font-size:23px;font-family:����">��ͨ��У���ҽ�ѧ������������</span></p>
		    <p style="height:10px"></p>
			<table>
				<tr>
				<th colspan="8" align="center">(${rs.xn }ѧ��)</th>
				</tr>
				<tr><th colspan="8" align="center"> <p style="height:5px"></p></th></tr>
				<tr>
				  <th width=40  height="30" align="center"> ѧУ��</th> 
				  <td width=125  align="left">�㽭����ְҵѧԺ&nbsp;</td> 
				  <th width=40  align="center"> Ժϵ��</th> 
		  	  	  <td width=125 align="left" >${rs.xymc }</td> 
		  	      <th width=40 align="center" > רҵ��</th> 
		          <td width=125 align="left" >${rs.zymc }</td> 
				  <th width=40 align="center" > �༶��</th> 
				  <td width=125 align="left">${rs.bjmc }&nbsp;</td>
				</tr>
			</table>
		</center>
		<p style="height:10px"></p>
  <table class="printtab" > 
    <tr > 
      <th rowspan="4" colspan="2" width=70>
      	��<br/>��<br/>��<br/>��<br/>
      </th>
      <td colspan="3" width=70 align="center">����</td>
      <td colspan="4" width=90 align="center">${rs.xm}</td>
      <td colspan="3" width=60 align="center">�Ա�</td>
      <td colspan="4" width=90 align="center">${rs.xb }</td>
      <td colspan="3" width=70 align="center">��������</td>
      <td colspan="4" width=90 align="center">${rs.csrq }</td>
    </tr> 
    <tr>
      <td colspan="3" width=70 align="center">ѧ��</td>
      <td colspan="4" width=90 align="center">${rs.xh}</td>
      <td colspan="3" width=60 align="center">����</td>
      <td colspan="4" width=90 align="center">${rs.mzmc }</td>
      <td colspan="3" width=70 align="center">��ѧʱ��</td>
      <td colspan="4" width=90 align="center">${rs.rxrq }</td>
    </tr>
    <tr>
      <td colspan="3" width=70 align="center">���֤��</td>
      <td width=25>${rs.sfzh0 }</td>
      <td width=25>${rs.sfzh1 }</td>
      <td width=25>${rs.sfzh2 }</td>
      <td width=25>${rs.sfzh3 }</td>
      <td width=25>${rs.sfzh4 }</td>
      <td width=25>${rs.sfzh5 }</td>
      <td width=25>${rs.sfzh6 }</td>
      <td width=25>${rs.sfzh7 }</td>
      <td width=24>${rs.sfzh8 }</td>
      <td width=24>${rs.sfzh9 }</td>
      <td width=24>${rs.sfzh10 }</td>
      <td width=24>${rs.sfzh11 }</td>
      <td width=24>${rs.sfzh12 }</td>
      <td width=24>${rs.sfzh13 }</td>
      <td width=24>${rs.sfzh14 }</td>
      <td width=24>${rs.sfzh15 }</td>
      <td width=24>${rs.sfzh16 }</td>
      <td width=24>${rs.sfzh17 }</td>
    </tr>
    <tr>
      <td colspan="3" align="center">������ò</td>
      <td colspan="5" align="center">${rs.zzmmmc }</td>
      <td colspan="4" align="center">��ϵ�绰</td>
      <td colspan="9" align="center">${rs.sjhm }</td>
    </tr>
    <tr>
      <th colspan="2" align="center" width=70>
		ѧ<br/>ϰ<br/>��<br/>��<br/>
      </th>
      <td width=590  valign=top colspan="21">
      	<p  >��ѧ����޿γ���<U>&nbsp;&nbsp;${rs.bxkms }&nbsp;&nbsp;</U>�ţ����У�����<U>&nbsp;&nbsp;${rs.yxkms }&nbsp;&nbsp;</U>�ţ�����<U>&nbsp;&nbsp;${rs.lhkms }&nbsp;&nbsp;</U>��<br/>
      		�ɼ�������רҵ���꼶����<U>&nbsp;&nbsp;${rs.zyfnjzypm }&nbsp;&nbsp;</U>������/��������<br/>
      		�ۺϿ����ɼ�<U>&nbsp;&nbsp;${rs.zd1 }&nbsp;&nbsp;</U>�֣�����<U>&nbsp;&nbsp;${rs.zcfnjzypm }&nbsp;&nbsp;</U>������/�������������޴����д���ޡ���<br/>
      	</p>
      </td>
    </tr>
    <tr>
      <th rowspan="2" colspan="2" align="center" width=70>
		��<br/>��<br/>��<br/>��<br/>
      </th>
      <td width=590  valign=top colspan="21">
      	<p>��Ҫ���</p>
      	<textarea rows="" cols="" class="text_nobor" style="word-break:break-all;width:97%;overflow:hidden;min-height: 250px;_height: 300px;font-size:14px" >&nbsp;&nbsp;&nbsp;&nbsp;${rs.kzzd1 }</textarea>
      </td>
    </tr>
    <tr>
      <td height="30" width=590  valign="middle" colspan="21">���У�Ժ������<U>&nbsp;&nbsp;${rs.kzzd2 }&nbsp;&nbsp;</U>�
      У������<U>&nbsp;&nbsp;${rs.kzzd3 }&nbsp;&nbsp;</U>�ʡ�����Ͻ���<U>&nbsp;&nbsp;${rs.kzzd4 }&nbsp;&nbsp;</U>��</td>
    </tr>
    
    <tr>
      <th  colspan="2" align="center" width=70>
                      ��<br/>
                      ��<br/>
                      ��<br/>
                      ��<br/>
      </th>
      <td width=590  valign=top colspan="21">
          <p  style="height:10px"></p>
          <textarea rows="" cols="" class="text_nobor" style="word-break:break-all;width:97%;overflow:hidden;min-height: 250px;_height: 300px;font-size:14px" >&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }</textarea>
          <p align="right">������ǩ��:
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:5px"></p>
          <p align="right">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:5px"></p>
      </td>  
    </tr>
    </table>
    <div style='page-break-before:always;'>&nbsp;</div>
    <table class="printtab">
    <tr > 
      <th width=70  align=center >
      �꼶<br/>��רҵ��<br/>�Ƽ�<br/>���<br/>
      </th> 
      <td width=590  valign=top >
          <p  style="height:20px"></p>
          <p style="height:180px"></p>
          <p >�Ƽ���:
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
		      ����ְ��
          </p>
          <p style="height:10px"></p>
          <p align="right">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:20px"></p>
      </td> 
    </tr> 
   <tr > 
      <th width=70  align=center >
                      Ժ<br/>
                      ��ϵ��<br/>
                      ��<br/>
                      ��<br/>
      </th> 
      <td width=590  valign=top >
          <p  style="height:20px"></p>
          <textarea rows="" cols="" class="text_nobor" style="word-break:break-all;width:97%;overflow:hidden;min-height: 250px;_height: 300px;font-size:14px" >&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyyj }</textarea>
          <p align="right">
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;�����£�
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:10px"></p>
          <p align="right">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:20px"></p>
      </td> 
    </tr> 
   <tr > 
      <th width=70  align=center >
                      ѧ<br/>У<br/>��<br/>��<br/>
      </th> 
      <td width=590  valign=top >
          <p  style="height:20px"></p>
          <p style="height:180px" > &nbsp;&nbsp;&nbsp;&nbsp;	�����󣬲���
          <U>&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;</U>��Χ�ڹ�ʾ
          <U>&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;</U>
          	�죬�����飬��ͬ���ͬѧ��ñ�ѧ����ҽ�ѧ��</p>
          <p align="right">
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;�����£�
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:10px"></p>
          <p align="right">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:20px"></p>
      </td> 
    </tr> 
  </table> 
	</body>
</html>
