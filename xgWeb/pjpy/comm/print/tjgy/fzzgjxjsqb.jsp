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
		    <p style="height:150px"></p>
		    <p><span style="font-size:26px;font-family:����_GB2312">��֮֯��Ƽ����������</span></p>
		    <p style="height:50px"></p>
            <p><span style="font-size:22px;font-family:����_GB2312">ѧ���������</span></p>  
		    <p style="height:300px"></p>
           <table>
		    <tr>
		    <td>
            <p><span style="font-size:22px;font-family:����_GB2312">ѧ   У��</span>
            </td>
            <td style="border-bottom:1px solid #070707"  align="center">
            <span style="font-size:22px;font-family:����_GB2312">
	               ${xxmc}
            </span>
            </td>
            </tr>
	        <tr>
	        <td>
	        <p><span style="font-size:22px;font-family:����_GB2312">��   ����</span></td>
	         <td style="border-bottom:1px solid #070707"  align="center"><span style="font-size:22px;font-family:����_GB2312;">
	         ${rs.xm}
	        </span></td>
	        </tr>
	        </table>
            <p style="height:200px"></p>
            <p><span style="font-size:18px;font-family:����_GB2312">��֮֯��Ƽ����������</span></p>
            <p style="height:20px"></p>
            <p><span style="font-size:18px;font-family:����_GB2312">   ��   ��   ��</span></p>
            <p style="height:100px"></p>
		</center>
  <table class="printtab">
    <tr > 
      <td width=58  align=center >
         <p align="center">
			�� ��
		 </p>
      </td> 
      <td width=88  align=center >
         <p align="center">
			${rs.xm }
		 </p>
      </td> 
      <td width=65  align=center >
         <p align="center">
			�� ��
		 </p>
      </td> 
      <td width=79  align=center >
        <p align="center">
			${rs.xb }
		</p>
      </td> 
      <td width=65  align=center >
        <p align="center">
                                ��������
		</p>
      </td> 
      <td width=68  align=center >
        <p align="center">
			${rs.csrq }
		</p>
      </td> 
      <td width=106  align=center  rowspan=3 >
      <img src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
									border="0" align="absmiddle" style="width:118px;height:130px" />
      </td> 
    </tr> 
    <tr > 
      <td width=58  align=center >
         <p align="center">
			  ����
		 </p>
      </td> 
      <td width=88  align=center  >
         <p align="center">
			${rs.mzmc }
		 </p>
      
      </td> 
      <td width=65  align=center  >
         <p align="center">
		          ����<br/>��ò
	     </p>
      </td> 
      <td   align=center  >${rs.zzmmmc }</td> 
      <td width=65  align=center  >
         <p align="center">
			��ѧ<br/>����
		 </p>
      </td> 
      <td width=88  align=center  >
         <p align="center">
			${rs.rxrq }
		 </p>
      </td> 
    </tr> 
    <tr > 
      <td width=60  align=center >
                                     Ժϵ
      </td> 
      <td width=83  align=center  colspan="2">${xymc }</td> 
       <td   align=center >
                                     רҵ
      </td> 
      <td width=83  align=center colspan="2">${zymc }</td> 
    </tr> 
    <tr > 
      <td align="center">
                      ���� <br/>
                      ���<br/>
                     ����<br/>
      </td> 
      <td colspan="8">
           <p style="height:150px"></p>
      </td>
    </tr> 
     <tr > 
      <td align="center">
                      ��ʱ<br/>
		�ε�<br/>
		�ܺ�<br/>
		����<br/>
      </td> 
      <td colspan="8">
           <p style="height:150px"></p>
      </td>
    </tr> 
    <tr > 
      <td width=688  align=top  colspan=9 >
          <p  style="height:20px"></p>
          <p align="center"><span style="font-weight:bold; font-size:15px;font-family:����;">������Ҫ�¼�</span></p>
          <p  style="height:20px"></p>
          <p style="height:400px"></p>
      </td> 
    </tr> 
  </table> 
  <p style="height:40px"></p>
  <table class="printtab"> 
    <tr > 
      <td width=688  valign=top  colspan="9">
          <p  style="height:20px"></p>
          <p align="center"><span style="font-weight:bold; font-size:15px;font-family:����;">������Ҫ�¼�</span></p>
          <p  style="height:20px"></p>
      </td> 
    </tr> 
    <tr><td colspan="9"><p style="height:870px"></p></td></tr>
  </table> 
  <p style="height:40px"></p>
  
  <table class="printtab"> 
     <tr > 
      <td width=55  align=center >
                      ѧ<br/>
                      У<br/>
                      ��<br/>
                      ��<br/>
                      ��<br/>
                      ��<br/>
      </td> 
      <td width=633  valign=top >
          <p  style="height:50px"></p>
          <p style="height:230px">&nbsp;&nbsp;&nbsp;&nbsp;���ݸ�ͬѧ��������֣���ѧУ��ѧ������ίԱ����ˣ���������������ͬ�������÷�֮֯�⽱ѧ��</p>
          <p align="center">У�쵼ǩ��   
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
                           ����
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:40px"></p>
          <p align="right">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
      </td> 
    </tr> 
    <tr > 
      <td width=55  align=center >
                     ��<br/>
		��<br/>
		��<br/>
                      ��<br/>
                      ��<br/>
                      ��<br/>
                      ��<br/>
      </td> 
      <td width=633  valign=top >
          <p  style="height:10px"></p>
          <p style="height:400px"></p>
          <p align="center">ǩ��
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;      
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
           &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
                            ����                 
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:40px"></p>
          <p align="right">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
      </td> 
    </tr> 
    <tr > 
      <td width=55  align=center >
		      ��<br/>
		      ע<br/>
      </td> 
      <td width=600  align=center >
            <p align="left">1.����һʽ���ݣ����������ӡ��</p>
            <p align="left">2.ѧУ�ꡢԺ��ϵ��רҵ������д��</p>
            <p align="left">�ڡ�רҵ����Ŀ�У�ע����ְ�����ơ�˶ʿ��ʿ��</p>	
	

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
