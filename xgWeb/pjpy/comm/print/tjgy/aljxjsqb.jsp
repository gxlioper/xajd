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
		    <p><span style="font-size:26px;font-family:����">�� �� �� ѧ ��</span></p>
		    <p style="height:50px"></p>
            <p><span style="font-size:22px;font-family:����_GB2312">��  ��  ��</span></p>  
		    <p style="height:250px"></p>
		    <table>
		    <tr>
		    <td>
            <p><span style="font-size:22px;font-family:����_GB2312">ѧ   У��</span>
            </td>
            <td style="border-bottom:1px solid #070707" align="center">
            <span style="font-size:22px;font-family:����_GB2312">
	               ${xxmc}
            </span>
            </td>
            </tr>
            <tr>
	        <td ><p><span style="font-size:22px;font-family:����_GB2312">Ժ   ϵ��</span></td>
	        <td  style="border-bottom:1px solid #070707" align="center"><span style="font-size:22px;font-family:����_GB2312">
	        ${rs.xymc}
	        </span></td>
	        </tr>
	        <tr>
	        <td ><p><span style="font-size:22px;font-family:����_GB2312">��   ����</span></td>
	        <td  style="border-bottom:1px solid #070707" align="center"><span style="font-size:22px;font-family:����_GB2312">
	        ${rs.bjmc}
	        </span>
	        </td>
	        </tr>
	        <tr>
	        <td>
	        <p><span style="font-size:22px;font-family:����_GB2312">��   ����</span></td>
	         <td style="border-bottom:1px solid #070707" align="center"><span style="font-size:22px;font-family:����_GB2312;">
	        ${rs.xm}
	        </span></td>
	        </tr>
	        </table>
            <p style="height:200px"></p>
            <p><span style="font-size:18px;font-family:����_GB2312">������ѧ�����ίԱ��</span></p>
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
      <td width=59  align=center >
        <p align="center">
			${rs.xb }
		</p>
      </td> 
      <td width=65  align=center >
        <p align="center">
                                 ����
		</p>
      </td> 
      <td width=68  align=center >
        <p align="center">
			${rs.mzmc }
		</p>
      </td> 
      <td width=60  align=center >
        <p align="center">
			����<br/>����
		</p>
      </td> 
      <td width=83  align=center >
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
      <td width=58  align=center  rowspan=2 >
         <p align="center">
			  ����<br/>��ò
		 </p>
      </td> 
      <td width=88  align=center  rowspan=2 >
         <p align="center">
			${rs.zzmmmc }
		 </p>
      
      </td> 
      <td width=65  align=center  rowspan=2 >
         <p align="center">
		          ����<br/>ְ��
	     </p>
      </td> 
      <td width=59  align=center  rowspan=2 >&nbsp;</td> 
      <td width=65  align=center  rowspan=2 >
         <p align="center">
			��Դ<br/>ʡ��
		 </p>
      </td> 
      <td width=68  align=center  rowspan=2 >
         <p align="center">
			${rs.syd }
		 </p>
      </td> 
      <td width=60  align=center >
                                    ũ��
      </td> 
      <td width=83  align=center >
            &nbsp;
      </td> 
    </tr> 
    <tr > 
      <td width=60  align=center >
                                     ����
      </td> 
      <td width=83  align=center >&nbsp;</td> 
    </tr> 
    <tr > 
      <td width=147  align=center  colspan=2 >
                                     �ۺϲ�������
      </td> 
      <td width=65  align=center >&nbsp;</td> 
      <td width=125  align=center  colspan=2 >
                                     רҵ����
      </td> 
      <td width=128  align=center  colspan=2 >&nbsp;</td> 
      <td width=83  align=center >�س�</td> 
      <td width=106  align=center >&nbsp;</td> 
    </tr> 
    <tr > 
      <td width=58  align=center >
                 ��<br/>
                 ѧ<br/>
                 ��<br/>
                 ��<br/>
                 ��<br/>
                 ��<br/>
                 ��<br/>
                 ��<br/>
       </td> 
      <td width=597  align=center  colspan=8 ><p style="height:200px"></p></td> 
    </tr> 
    <tr > 
      <td width=655    colspan=9 >
       <p  style="height:10px"></p>
       <p style="height:10px" align="center"><span style="font-size:12px;font-family:����;font-weight:bold;">����ʱװ�������޹�˾���</span></p>
       <p  style="height:20px"></p>
       <p align="left">
       <span style="font-family:&#23435;&#20307;" >
       <p align="left">&nbsp;&nbsp;&nbsp;&nbsp;�������F�1986���������ۣ���Ҫ���I�r�b�u�켰�N�ۘI�գ����a�����O춏V�|ʡ���ڼ��|ݸ�ȵأ��F��W�����ض�������Ʒ��ָ֮�������̣��F���F�T���_1200�ˡ�</p>
       <p align="left">&nbsp;&nbsp;&nbsp;&nbsp;���F���k�ˏ����A�������漮���һ���P����������գ��F������؄e�����^������ȫ��ʹ���2006��s�@��ہ��ތ��Ņf���C�l�ܳ����Ű׽𪄡�</p>
       <p align="left"> &nbsp;&nbsp;&nbsp;&nbsp; �������������������������Q���Թ�˾���x����У�O���������W��ÿ���ṩ5�fԪ���Ԫ����c���b���P�Wϵ�������b�OӋ�c����רҵ����������רҵ��ˇ�g�OӋרҵ�����װ��ط���֮�ܳ��W�����ԹĄ�W�����^�l���M������Ϊ�ҹ���֯��װ��ҵ����ؕ�I��</p>
      </span>
      </p>
      </td> 
    </tr> 
    <tr > 
      <td width=655  align=top  colspan=9 >
          <p  style="height:20px"></p>
          <p align="center"><span style="font-weight:bold; font-size:15px;font-family:����;">��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��</span></p>
          <p  style="height:20px"></p>
          <p align="left">&nbsp;&nbsp;&nbsp;&nbsp;����������Ӧ���������˵��������ı��֡�������Ṥ�����μ������������뽱ѧ��Ķ�����Ŭ��Ŀ�ꡣ��</p>
          <p style="height:300px"></p>
      </td> 
    </tr> 
  </table> 
  <p style="height:30px"></p>
  <table class="printtab"> 
    <tr > 
      <td width=688  valign=top >
          <p  style="height:20px"></p>
          <p align="center"><span style="font-weight:bold; font-size:15px;font-family:����;">��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��</span></p>
          <p  style="height:20px"></p>
          <p style="height:890px"></p>
      </td> 
    </tr> 
  </table> 
  <p style="height:20px"></p>
  <table class="printtab"> 
    <tr > 
      <td width=55  align=center >
                      ��<br/>
                      ��<br/>
                      ��<br/>
                      ��<br/>
                      Ա<br/>
                      ��<br/>
                      ��<br/>
      </td> 
      <td width=633  valign=top >
          <p  style="height:10px"></p>
          <p style="height:150px">&nbsp;&nbsp;&nbsp;&nbsp;��1.����������д�����ʵ��2.�������˸������������Ҫ�ۺ����ۣ�3.�Ƿ�ͬ�������룩</p>
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
      <td width=633  valign=top >
          <p  style="height:10px"></p>
          <p style="height:150px">&nbsp;&nbsp;&nbsp;&nbsp;	��ͬѧ���������������ʵ���������������֣���<bean:message key="lable.xb" />��ѧ������ίԱ����ˣ���������������ͬ�������ð�����ѧ��</p>
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
                     ��<br/>
                     ��<br/>
                      ��<br/>
                      ѧ<br/>
                      ��<br/>
                      ��<br/>
                      ��<br/>
                      ί<br/>
                      Ա<br/>
                      ��<br/>
                      ��<br/>
                      ��<br/>
                      ��<br/>
                      ��<br/>
      </td> 
      <td width=633  valign=top >
          <p  style="height:10px"></p>
          <p style="height:300px">&nbsp;&nbsp;&nbsp;&nbsp;���ݸ�ͬѧ��������֣���������ѧ������ίԱ����ˣ���������������ͬ�������ð�����ѧ��</p>
          <p align="left">����ίԱ������ǩ����                                   
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
                           ��   ��
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
            <p align="left">1.����һʽ���ݣ�������д����Ҫ����Դ�ӡ��</p>
            <p align="left">2.ѧУ��Ժ��ϵ���༶������д��</p>
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
