<%@ page language="java" contentType="text/html; charset=GBK"%>

	<thead>
		<tr>
        	<th colspan="5" onclick="deploy('hi_xjyd');"><span>ѧ���춯��Ϣ</span></th>
        </tr>
	</thead>
	<tbody id="hi_xjyd">
		<tr>
			<th width="15%">�춯���</th>
			<td width="25%">
         		<div id="ydxh">${rs.ydxh }</div>
			</td>
			<th width="15%">�����ĺ�</th>
			<td width="45%" colspan="2">
         		<div id="clwh">${rs.clwh }</div>
			</td>
		</tr>
		<tr>
			<th>�춯ԭ��</th>
			<td>
         		<div id="ydyy">${rs.ydyy }</div>
			</td>
			<th>�춯˵��</th>
			<td>
         		<div id="ydsm">${rs.ydsm }</div>
			</td>
		</tr>
		<tr>
			<th>�춯���</th>
			<td>
         		<div id="ydlbmc">${rs.ydlbmc }</div>
			</td>
			<th>�춯����</th>
			<td>
         		<div id="ydrq">${rs.ydrq }</div>
			</td>
		</tr>
		<tr>
			<th>ѧ��״̬</th>
			<td>
         		<div id="xjztm">${rs.xjzt}</div>
			</td>
			<th>�Ƿ���У</th>
			<td>
         		<div id="sfxx">${rs.ydhsfzx }</div>
			</td>
		</tr>
	</tbody>