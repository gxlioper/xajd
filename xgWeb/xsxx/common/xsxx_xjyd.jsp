<%@ page language="java" contentType="text/html; charset=GBK"%>

	<thead>
		<tr>
        	<th colspan="5" onclick="deploy('hi_xjyd');"><span>学籍异动信息</span></th>
        </tr>
	</thead>
	<tbody id="hi_xjyd">
		<tr>
			<th width="15%">异动序号</th>
			<td width="25%">
         		<div id="ydxh">${rs.ydxh }</div>
			</td>
			<th width="15%">处理文号</th>
			<td width="45%" colspan="2">
         		<div id="clwh">${rs.clwh }</div>
			</td>
		</tr>
		<tr>
			<th>异动原因</th>
			<td>
         		<div id="ydyy">${rs.ydyy }</div>
			</td>
			<th>异动说明</th>
			<td>
         		<div id="ydsm">${rs.ydsm }</div>
			</td>
		</tr>
		<tr>
			<th>异动类别</th>
			<td>
         		<div id="ydlbmc">${rs.ydlbmc }</div>
			</td>
			<th>异动日期</th>
			<td>
         		<div id="ydrq">${rs.ydrq }</div>
			</td>
		</tr>
		<tr>
			<th>学籍状态</th>
			<td>
         		<div id="xjztm">${rs.xjzt}</div>
			</td>
			<th>是否在校</th>
			<td>
         		<div id="sfxx">${rs.ydhsfzx }</div>
			</td>
		</tr>
	</tbody>