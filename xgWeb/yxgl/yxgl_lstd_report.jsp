<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	      <style>
			.title{
			  font-family:"��������";
			  font-size:24px;
			  font-weight:bolder
			}
			p{
				font-size:16px;
				font-family:"����";
				letter-spacing:2;
			}
			div{
			margin-left:2em;
			margin-right:2em;
			
			}
			.style{
				font-size: 14px;
				font-family: "����_GB2312";
			}
			.wholeStyle{
			line-height:25px;			
			}
			.style7 {font-size: 16px}
			.stylebold{
			   font-weight: 900;
			}
		</style>
  </head>
  			<script type="text/javascript" src="/xgxt/dwr/interface/getNewStuBdd.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
    		<script type="text/javascript" src="/xgxt/js/yxglFunction.js"></script>
  <body>
            <input type="hidden" id="sfjyFlag" name="sfjyFlag" value="<bean:write name="rs" property="sfjy"/>">
			<div align="left" class="wholeStyle">
			<p align="center" class="title">������ҵ��ѧ����ɫͨ����ѧ��֪ͨ��</p>
			<p style="font-family:���Ŀ���;font-size: 18px">��ţ�<bean:write name='rs' property='lsh' /> ѧ�ţ�<bean:write name='rs' property='xh' /> 
			<br/><bean:message key="lable.xsgzyxpzxy" />��<bean:write name='rs' property='xymc' /> �༶��<bean:write name='rs' property='bjmc' /> ������<bean:write name='rs' property='xm' /></p>			
			<p style="font-family:'����'; text-indent: 2.5em;">�������ͥ�������ѣ�ѧУ������ͨ������ɫͨ������ѧ�����������������ڵ�<bean:message key="lable.xsgzyxpzxy" />����֧������������ѧ�������룬���˽�ѧУ������������ߣ�
			<br/>������ǩ�֣�</p> 
			<p class="style7" align="right" >������ҵ��ѧѧ��������������
			<br/><bean:write name='rs' property='dqrq' /></p>
			<p style="font-family:'����'; text-indent: 1em;font-size:13px;"><bean:message key="lable.xsgzyxpzxy" />�����ͥ��������ѧ��������ʦ��ϵ��ʽ��
			<br/>������<bean:write name='rs' property='lsxm' />&nbsp;&nbsp;�칫�绰��<bean:write name='rs' property='lxdh' /> &nbsp;&nbsp;�칫�ص㣺<bean:write name='rs' property='bgdd' />
			<br/>&nbsp;&nbsp;&nbsp;&nbsp;������ѧ������ָ�����������˷��ŵ����������������Ϣ�����ڽ��ѧ���ڹ��ڸߵ�ѧУ�Ͷ�����ѧ�ѡ�ס�޷Ѻ�����ѵ���ѧ���ʵ��һ�����롢һ�����š����ڷ��ŵĹ���ʽ����������һ�㲻����10�꣬����������ѧ����ҵ�����꣬���ѧ�����ڱ�ҵ��1��2�꿪ʼ���6���ڱ��뻹�壻���ѧ�����������ÿ��ÿѧ�겻����6000Ԫ��������ѧ����ʵ�н������У�ڼ�100%�����������Ϣ������˱�ҵ��Ĵ�����Ϣ��ΥԼ��Ϣ���䱾��ȫ��֧���� </p>
			<p style="font-family:'����'; text-indent: 1em;font-size:13px;">������ҵ��ѧ���������ѧ������Ҫ׼���Ĳ��ϣ�
			<br/>1���������֤�����ڱ��򻧿�Ǩ��֤������ӡ��A4ֽ�ϣ��� 
			<br/>2����һ�����ϣ��������������ż���һ�����ϣ������������ܲ��Ź������ͥ�������ѵ�֤����
			<br/>3����ĸ���򷨶��໤�ˣ�˫�������֤�����ڱ���ӡ������ӡ��A4ֽ�ϣ���ĸ��һ�ţ���
			<br/>4��δ���������ṩ�����໤������ͬ������������֤����
			<br/>-------------------------------------------------------------------</p>
			<p style="font-weight:bolder;text-indent: 4em;" align="center">������ҵ��ѧ����ɫͨ����֪ͨ�� </p> 
			<p style="font-family:���Ŀ���;font-size: 18px">��ţ�<bean:write name='rs' property='lsh' /> ѧ�ţ�<bean:write name='rs' property='xh' />
			<br/><bean:message key="lable.xsgzyxpzxy" />��<bean:write name='rs' property='xymc' /> �༶��<bean:write name='rs' property='bjmc' /> ������<bean:write name='rs' property='xm' /></p>	
			<p class="style7">���񴦣�
			<br/>���ڸ�����ͥ�������ѣ���ʱ���ܽ��ɣ�
			<br/>ѧ  ��:<bean:write name='rs' property='hyjxf' />Ԫ       ס�޷ѣ�<bean:write name='rs' property='hyjqsf' />Ԫ
			<br/>&nbsp;&nbsp;&nbsp;&nbsp;ѧ�������Ѿ�����������룬�����Ϊ����������ѧ����Ϊл��
			<br/>������ǩ�֣�</p> 
			<p class="style7" align="right" >������ҵ��ѧѧ��������������
			<br/><bean:write name='rs' property='dqrq' /></p>
			<img id="txm" src="/xgxt/BarcodeServlet?image=3&type=6&data=<bean:write name='rs' property='ksh' />&height=90&fontsize=20&barWidth=0.5" align="right" style="position:absolute;top:200;left:100;width:250px;height:80px"/>
			<img id="tss" src="/xgxt/BarcodeServlet?image=3&type=6&data=<bean:write name='rs' property='ksh' />&height=90&fontsize=20&barWidth=0.5" align="right" style="position:absolute;top:750;left:445;width:250px;height:80px"/>
			</div>
  </body>
</html:html>