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
					<span>学生台账</span>
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
						户口所在地
					</th>
					<td align="left"  style="width: 25%">
						${xsxxcjForm.hkszdmc }
					</td>
					<th  style="width: 15%">
						家庭住址
					</th>
					<td colspan="2"  style="width: 45%">
						${xsxxcjForm.jtdzmc }
					</td>
				</tr>
				<tr>
					<th>
						生源地
					</th>
					<td align="left">
						${xsxxcjForm.sydmc }
					</td>
					<th>
						籍贯
					</th>
					<td colspan="2">
						${xsxxcjForm.jgmc }
					</td>
				</tr>
				<tr>
					<th>
						家庭联系方式
					</th>
					<td align="left" colspan="4">
						${xsxxcjForm.jtlxfs }
					</td>
				</tr>
				<tr>
					<th>
						户口是否迁入学校
					</th>
					<td align="left">
						${xsxxcjForm.hksfjrxx }
					</td>
					<th>
						是否住校
					</th>
					<td colspan="2">
						${xsxxcjForm.sfzx }
					</td>
				</tr>
				<tr>
					<th>
						是否申请入党
					</th>
					<td>
						${xsxxcjForm.sfsqrd }
					</td>
					<th>
						是否顶岗实习
					</th>
					<td  colspan="2">
						${xsxxcjForm.sfdgsx }
					</td>
				</tr>
				<tr>
					<th>
						递交申请书时间
					</th>
					<td align="left">
						${xsxxcjForm.djsqssj }
					</td>
					<th>
						入党时间
					</th>
					<td  colspan="2">
						${xsxxcjForm.rdsj }
					</td>
				</tr>
				<tr>
					<th>
						转正时间
					</th>
					<td colspan="4">
						${xsxxcjForm.zzsj }
					</td>
				</tr>
				<tr>
					<th>
						是否少数民族
					</th>
					<td>
						${xsxxcjForm.sfssmz }
					</td>
					<th>
						少数民族名称
					</th>
					<td colspan="2">
						${xsxxcjForm.mzmc }
					</td>
				</tr>
				<tr>
					<th>
						是否临时培训
					</th>
					<td>
						${xsxxcjForm.sflspx }
					</td>
					<th>
						培训名称
					</th>
					<td colspan="2">
						${xsxxcjForm.pxmc }
					</td>
				</tr>
				<tr>
					<th>
						是否宗教信仰
					</th>
					<td>
						${xsxxcjForm.sfzjxy }
					</td>
					<th></th>
					<td colspan="2"></td>
				</tr>
				<tr>
					<th>
						信仰宗教名称
					</th>
					<td>
						${xsxxcjForm.xyzjmc }
					</td>
					<th>
						参加宗教时间
					</th>
					<td colspan="2">
						${xsxxcjForm.cjzjsj }
					</td>
				</tr>
				<tr>
					<th>
						是否经济困难
					</th>
					<td>
						${xsxxcjForm.sfjjkn }
					</td>
					<th>
						经济困难原因
					</th>
					<td colspan="2">
						${xsxxcjForm.jjknyy }
					</td>
				</tr>
				<tr>
					<th>
						身体是否疾病
					</th>
					<td>
						${xsxxcjForm.stsfcj }
					</td>
					<th>
						身体疾病原因
					</th>
					<td colspan="2">
						${xsxxcjForm.stcjyy }
					</td>
				</tr>

				<tr>
					<th>
						是否学习困难
					</th>
					<td>
						${xsxxcjForm.sfxxkn }
					</td>
					<th>
						学习困难原因
					</th>
					<td colspan="2">
						${xsxxcjForm.xxknyy }
					</td>
				</tr>


				<tr>
					<th>
						是否家庭困扰
					</th>
					<td>
						${xsxxcjForm.sfjtkr }
					</td>
					<th>
						家庭困扰原因
					</th>
					<td colspan="2">
						${xsxxcjForm.jtkryy }
					</td>
				</tr>

				<tr>
					<th>
						是否有其他困扰
					</th>
					<td>
						${xsxxcjForm.sfyqtkr }
					</td>
					<th>
						其他困扰原因
					</th>
					<td colspan="2">
						${xsxxcjForm.qtkryy }
					</td>
				</tr>
			</logic:notEmpty>
			<logic:empty name="xsxxcjForm" property="xh">
				<tr>
					<td colspan="5" align="center">
						暂无数据！
					</td>
				</tr>
			</logic:empty>
		</tbody>
	</table>
</div>