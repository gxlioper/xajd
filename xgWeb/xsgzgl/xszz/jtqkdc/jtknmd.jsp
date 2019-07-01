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
				<p align="center" style="font-size:20px">家庭经济困难学生认定等级和标准</p>
				<p style="font-size:14px">
家庭经济困难学生认定等级认定等级分为一般困难、困比较难和特殊困难三档。</br>
（一）家庭经济特殊困难学生参照标准：学生本人月平均可获得的全部生活费难以维持基本生活（家庭人均月收入在500元及以下，本人在校期间月均生活费低于350元），同时具备以下情况之一者，可以认定为家庭经济特殊困难学生。
1、孤儿、单亲，且其亲属无供养能力的；</br>
2、父母年事已高、患病长期卧床，家庭缺乏劳动力，家庭又无固定经济来源且亲友无供养能力者；
3、家庭所在地区属于贫困山区且当年遭受严重自然灾害，或者来自国家确定的老、少、边、穷地区，本人家庭无经济收入或收入微薄，不足以支付学杂费用及生活费的；</br>
4、属于生源所在地低保家庭、烈士家庭、由社会福利机构监护和列入农村五保户家庭的子女；</br>
5、持有学生生源地县级民政部门发放的《特困家庭证明》或《最低生活保障救助证》的学生；</br>
6、出现其它特殊情况或突发性事件，造成家庭经济极度困难的。
</br>
（二）家庭经济比较困难学生参照标准：家庭人均月收入在600元以下，本人在校期间月平均生活费低于450元，同时具备以下情况之一者，可认定为家庭经济困难学生。</br>
1、父母双方或一方残疾，家庭经济困难的学生；</br>
2、单亲家庭，只有父母中一方为本人提供经济来源，家庭经济困难的学生；</br>
3、多子女上学，家庭经济困难的学生；</br>
4、父母双下岗，家庭经济困难的学生；</br>
5、纯农户且家庭经济困难的学生（指农村除农业外没有其他收入来源地家庭）；</br>
6、出现其它特殊情况或突发事件，造成家庭经济困难的。</br>
（三）家庭经济一般困难学生参照标准：家庭人均月收入在700元以下，本人在校期间月平均生活费低于550元的，且未达到特别困难学生和比较困难学生的认定标准，由于各种原因导致学生本人及其家庭所能筹集到的资金，不足以支付其在校生活基本费用的，可认定为家庭经济一般困难学生。</br>
（四）前述中“特殊情况或突发事件”一般指以下几种情况之一：</br>
1、家庭成员因患重大疾病需支付大额医疗费用的；</br>
2、父母离异导致家庭收入明显下降的；</br>
3、家庭遭遇不可抗力或自然灾害的；</br>
4、家庭因突发性变故造成人身及财产重大损失的；</br>
5、其他情况导致家庭经济暂时困难的。</br>
</p>
				 </div>
			    <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" onclick="scmd()">
									一键生成
								</button>
								<button type="button" onclick="Close();return false;">
									取消
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

