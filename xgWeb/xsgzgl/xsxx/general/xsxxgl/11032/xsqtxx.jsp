<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<div>
	<a name="tab_xstz"></a>
	<table style="margin-bottom: 5px" width="100%" border="0"
		class="formlist">

		<thead>
			<tr onclick="" style="cursor: hand;">
				<th colspan="5">
					<span>ѧ��̨��</span>
				</th>
			</tr>
		</thead>
	</table>
	<table style="margin-bottom: 5px" width="100%" border="0"
		class="formlist" id="tab_xstz">
		<tbody id="hi_xstz">
			<logic:notEmpty name="xsxxcjForm" property="xh">
				<tr>
					<th style="width: 15%">
						�������ڵ�
					</th>
					<td align="left"  style="width: 25%">
						${xsxxcjForm.hkszdmc }
					</td>
					<th  style="width: 15%">
						��ͥסַ
					</th>
					<td colspan="2"  style="width: 45%">
						${xsxxcjForm.jtdzmc }
					</td>
				</tr>
				<tr>
					<th>
						��Դ��
					</th>
					<td align="left">
						${xsxxcjForm.sydmc }
					</td>
					<th>
						����
					</th>
					<td colspan="2">
						${xsxxcjForm.jgmc }
					</td>
				</tr>
				<tr>
					<th>
						��ͥ��ϵ��ʽ
					</th>
					<td align="left" colspan="4">
						${xsxxcjForm.jtlxfs }
					</td>
				</tr>
				<tr>
					<th>
						�����Ƿ�Ǩ��ѧУ
					</th>
					<td align="left">
						${xsxxcjForm.hksfjrxx }
					</td>
					<th>
						�Ƿ�סУ
					</th>
					<td colspan="2">
						${xsxxcjForm.sfzx }
					</td>
				</tr>
				<tr>
					<th>
						�Ƿ������뵳
					</th>
					<td>
						${xsxxcjForm.sfsqrd }
					</td>
					<th>
						�Ƿ񶥸�ʵϰ
					</th>
					<td  colspan="2">
						${xsxxcjForm.sfdgsx }
					</td>
				</tr>
				<tr>
					<th>
						�ݽ�������ʱ��
					</th>
					<td align="left">
						${xsxxcjForm.djsqssj }
					</td>
					<th>
						�뵳ʱ��
					</th>
					<td  colspan="2">
						${xsxxcjForm.rdsj }
					</td>
				</tr>
				<tr>
					<th>
						ת��ʱ��
					</th>
					<td colspan="4">
						${xsxxcjForm.zzsj }
					</td>
				</tr>
				<tr>
					<th>
						�Ƿ���������
					</th>
					<td>
						${xsxxcjForm.sfssmz }
					</td>
					<th>
						������������
					</th>
					<td colspan="2">
						${xsxxcjForm.mzmc }
					</td>
				</tr>
				<tr>
					<th>
						�Ƿ���ʱ��ѵ
					</th>
					<td>
						${xsxxcjForm.sflspx }
					</td>
					<th>
						��ѵ����
					</th>
					<td colspan="2">
						${xsxxcjForm.pxmc }
					</td>
				</tr>
				<tr>
					<th>
						�Ƿ��ڽ�����
					</th>
					<td>
						${xsxxcjForm.sfzjxy }
					</td>
					<th></th>
					<td colspan="2"></td>
				</tr>
				<tr>
					<th>
						�����ڽ�����
					</th>
					<td>
						${xsxxcjForm.xyzjmc }
					</td>
					<th>
						�μ��ڽ�ʱ��
					</th>
					<td colspan="2">
						${xsxxcjForm.cjzjsj }
					</td>
				</tr>
				<tr>
					<th>
						�Ƿ񾭼�����
					</th>
					<td>
						${xsxxcjForm.sfjjkn }
					</td>
					<th>
						��������ԭ��
					</th>
					<td colspan="2">
						${xsxxcjForm.jjknyy }
					</td>
				</tr>
				<tr>
					<th>
						�����Ƿ񼲲�
					</th>
					<td>
						${xsxxcjForm.stsfcj }
					</td>
					<th>
						���弲��ԭ��
					</th>
					<td colspan="2">
						${xsxxcjForm.stcjyy }
					</td>
				</tr>

				<tr>
					<th>
						�Ƿ�ѧϰ����
					</th>
					<td>
						${xsxxcjForm.sfxxkn }
					</td>
					<th>
						ѧϰ����ԭ��
					</th>
					<td colspan="2">
						${xsxxcjForm.xxknyy }
					</td>
				</tr>


				<tr>
					<th>
						�Ƿ��ͥ����
					</th>
					<td>
						${xsxxcjForm.sfjtkr }
					</td>
					<th>
						��ͥ����ԭ��
					</th>
					<td colspan="2">
						${xsxxcjForm.jtkryy }
					</td>
				</tr>

				<tr>
					<th>
						�Ƿ�����������
					</th>
					<td>
						${xsxxcjForm.sfyqtkr }
					</td>
					<th>
						��������ԭ��
					</th>
					<td colspan="2">
						${xsxxcjForm.qtkryy }
					</td>
				</tr>
			</logic:notEmpty>
			<logic:empty name="xsxxcjForm" property="xh">
				<tr>
					<td colspan="5" align="center">
						�������ݣ�
					</td>
				</tr>
			</logic:empty>
		</tbody>
	</table>
</div>