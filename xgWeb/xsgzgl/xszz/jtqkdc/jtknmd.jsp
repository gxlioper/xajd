<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type='text/javascript'>
		function scmd(){
		window.location.href="scmd.xls";
		}
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/xszz_jtqkdc" method="post" styleId="jtqkdcForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<p align="center" style="font-size:20px">��ͥ��������ѧ���϶��ȼ��ͱ�׼</p>
				<p style="font-size:14px">
��ͥ��������ѧ���϶��ȼ��϶��ȼ���Ϊһ�����ѡ����Ƚ��Ѻ���������������</br>
��һ����ͥ������������ѧ�����ձ�׼��ѧ��������ƽ���ɻ�õ�ȫ�����������ά�ֻ��������ͥ�˾���������500Ԫ�����£�������У�ڼ��¾�����ѵ���350Ԫ����ͬʱ�߱��������֮һ�ߣ������϶�Ϊ��ͥ������������ѧ����
1���¶������ף����������޹��������ģ�</br>
2����ĸ�����Ѹߡ����������Դ�����ͥȱ���Ͷ�������ͥ���޹̶�������Դ�������޹��������ߣ�
3����ͥ���ڵ�������ƶ��ɽ���ҵ�������������Ȼ�ֺ����������Թ���ȷ�����ϡ��١��ߡ�����������˼�ͥ�޾������������΢����������֧��ѧ�ӷ��ü�����ѵģ�</br>
4��������Դ���ڵصͱ���ͥ����ʿ��ͥ������ḣ�������໤������ũ���屣����ͥ����Ů��</br>
5������ѧ����Դ���ؼ��������ŷ��ŵġ�������֤ͥ�������������Ͼ���֤����ѧ����</br>
6�������������������ͻ�����¼�����ɼ�ͥ���ü������ѵġ�
</br>
��������ͥ���ñȽ�����ѧ�����ձ�׼����ͥ�˾���������600Ԫ���£�������У�ڼ���ƽ������ѵ���450Ԫ��ͬʱ�߱��������֮һ�ߣ����϶�Ϊ��ͥ��������ѧ����</br>
1����ĸ˫����һ���м�����ͥ�������ѵ�ѧ����</br>
2�����׼�ͥ��ֻ�и�ĸ��һ��Ϊ�����ṩ������Դ����ͥ�������ѵ�ѧ����</br>
3������Ů��ѧ����ͥ�������ѵ�ѧ����</br>
4����ĸ˫�¸ڣ���ͥ�������ѵ�ѧ����</br>
5����ũ���Ҽ�ͥ�������ѵ�ѧ����ָũ���ũҵ��û������������Դ�ؼ�ͥ����</br>
6�������������������ͻ���¼�����ɼ�ͥ�������ѵġ�</br>
��������ͥ����һ������ѧ�����ձ�׼����ͥ�˾���������700Ԫ���£�������У�ڼ���ƽ������ѵ���550Ԫ�ģ���δ�ﵽ�ر�����ѧ���ͱȽ�����ѧ�����϶���׼�����ڸ���ԭ����ѧ�����˼����ͥ���ܳＯ�����ʽ𣬲�����֧������У����������õģ����϶�Ϊ��ͥ����һ������ѧ����</br>
���ģ�ǰ���С����������ͻ���¼���һ��ָ���¼������֮һ��</br>
1����ͥ��Ա���ش󼲲���֧�����ҽ�Ʒ��õģ�</br>
2����ĸ���쵼�¼�ͥ���������½��ģ�</br>
3����ͥ�������ɿ�������Ȼ�ֺ��ģ�</br>
4����ͥ��ͻ���Ա����������Ʋ��ش���ʧ�ģ�</br>
5������������¼�ͥ������ʱ���ѵġ�</br>
</p>
				 </div>
			    <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" onclick="scmd()">
									һ������
								</button>
								<button type="button" onclick="Close();return false;">
									ȡ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</div>
		</html:form>
	</body>
</html>

