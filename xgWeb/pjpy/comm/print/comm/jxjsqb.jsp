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
		    <p><span style="font-size:18px;font-family:����">${rs.xmmc }��ѧ�������</span></p>
		    <p style="height:50px"></p>
		</center>
  <table border="0" width="652px">
  <tr>
  <td></td>
  <td width="652px" align="right">������ڣ�&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��</td>
  </tr>
  </table>		
  <table class="printtab" > 
    <tr > 
      <td width=58  valign=center >
         <p align="center">
			�� ��
		 </p>
      </td> 
      <td width=89  valign=center >
         <p align="center">
			${rs.xm }
		 </p>
      </td> 
      <td width=47  valign=center >
         <p align="center">
			�� ��
		 </p>  
      </td> 
      <td width=50  valign=center >
         <p align="center">
			${rs.xb }
		 </p>
      </td> 
      <td width=65  valign=center >
        <p align="center">
			  ������ò
		 </p>
      </td> 
      <td width=63  valign=center >
         <p align="center">
			${rs.zzmmmc }
		 </p>
      </td> 
      <td width=63  valign=center >
         <p align="center">
			רҵ����
		 </p>
      </td> 
      <td width=72  valign=center >&nbsp;</td> 
      <td width=151  valign=center  colspan=2  rowspan=3 >
      <img src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
									border="0" align="absmiddle" style="width:118px;height:130px" />
      </td> 
    </tr> 
    <tr > 
      <td width=58  valign=center >
         <p align="center">
			ѧ��
		 </p>
      </td> 
      <td width=89  valign=center >
         <p align="center">
			${rs.xh }
		 </p>
      </td> 
      <td width=47  valign=center >
          <p align="center">
                                 ����
		  </p>
      </td> 
      <td width=50  valign=center >
          <p align="center">
			${rs.mzmc }
		  </p>
      </td> 
      <td width=65  valign=center >
           <p align="center">
			��������
		   </p>
      </td> 
      <td width=63  valign=center >
           <p align="center">
		    ${rs.csrq }
		   </p>
      </td> 
      <td width=63  valign=center >
          <p align="center">
		               ����ְ��
		  </p>
      </td> 
      <td width=72  valign=center >&nbsp;</td> 
    </tr> 
    <tr > 
      <td width=58  valign=center >
      ��������<p align="center">
                                   רҵ�༶
         </p>
      
      </td> 
      <td width=186  valign=center  colspan=3 >&nbsp;</td> 
      <td width=65  valign=center >
          <p align="center">
			��ϵ�绰
		   </p>
      </td> 
      <td width=198  valign=center  colspan=3 >&nbsp;</td> 
    </tr> 
    <tr > 
      <td width=58  valign=center >
            <p align="center">
                                         ��ͥ��ַ
            </p>
      </td> 
      <td width=315  valign=center  colspan=5 >&nbsp;</td> 
      <td width=63  valign=center >
          <p align="center">
                                         �ʱ�
          </p>
      </td> 
      <td width=72  valign=center >&nbsp;</td> 
      <td width=75  valign=center >
          <p align="center">
                                         �걨�ȼ�
          </p>
      </td> 
      <td width=75  valign=center >&nbsp;</td> 
    </tr> 
    <tr > 
      <td   align=center >
                  ��<br/>
                 ѧ<br/>
                 ��<br/>
                 ��<br/>
                 ��<br/>
                 ��<br/>
                 ��<br/>
                 ��<br/>
      </td> 
      <td width=602  valign=center  colspan=9 ><p style="height:320px"></p></td> 
    </tr> 
    <tr > 
      <td  align=center >
       ��<br/>
		Ҫ<br/>
		��<br/>
		��<br/>
		��<br/>
		��<br/>
		��<br/>
		��<br/>
      </td> 
      <td width=602  valign=top  colspan=9 >
      <p>�������μӸ�����������֡��� </p>
      <p style="height:330px"></p>
      </td> 
    </tr> 
    </table>
    <p style="height:30px"></p>
    <table class="printtab">
    <tr > 
      <td width=55  align=center >
                      ��<br/>
                      ��<br/>
                      Ա<br/>
                      ��<br/>
                      ��<br/>
      </td> 
      <td width=613  valign=top >
          <p  style="height:40px"></p>
          <p style="height:160px"></p>
          <p align="right">ǩ��:
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:10px"></p>
          <p align="right">��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:20px"></p>
      </td> 
    </tr> 
   <tr > 
      <td width=55  align=center >
                      ѧ<br/>
                      Ժ<br/>
                      ��<br/>
                      ��<br/>
                      ��<br/>
                      ��<br/>
      </td> 
      <td width=613  valign=top >
          <p  style="height:30px"></p>
          <p style="height:160px">	&nbsp;&nbsp;&nbsp;&nbsp;</p>
          <p align="right">ǩ�������£�:
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:10px"></p>
          <p align="right">��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:20px"></p>
      </td> 
    </tr> 
   <tr > 
      <td width=55  align=center >
                      ѧ<br/>
                      У<br/>
                      ��<br/>
                      ��<br/>
                      ��<br/>
                      ��<br/>
      </td> 
      <td width=613  valign=top >
          <p  style="height:40px"></p>
          <p style="height:160px">&nbsp;&nbsp;&nbsp;&nbsp;	</p>
          <p align="right">ǩ�������£�:
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:10px"></p>
          <p align="right">��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:20px"></p>
      </td> 
    </tr> 
    <tr > 
      <td width=55  align=center >��ע</td> 
      <td width=600  align=center >
            <p style="height:20px"></p>
            <p align="left">1���˱���������ʵ������д��</p>
            <p align="left">2������һʽ���ݣ�������д����Ҫ�����¼�룬�������ӡ��</p>
            <p style="height:20px"></p>
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
