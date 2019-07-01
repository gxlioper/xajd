<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/comm/message.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="comm/editor/kindeditor-min.js"></script>
		<script language="javascript" src="comm/editor/zh_CN.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript">
		jQuery(function(){
            var dwlb = ${model.dwlb};
            if(dwlb == "01"){
                jQuery(".xnxx").attr("style","");
                jQuery(".xwxx").attr("style","display:none");
            } else {
                jQuery(".xnxx").attr("style","display:none");
                jQuery(".xwxx").attr("style","");
            }
		})
		</script>
	</head>
	<body >

		<html:form styleId="qgzxJcdmwhForm" action="/qgzx_jcdmwh_ajax" method="post" onsubmit="return false;">
			<table align="center" class="formlist">
					<thead>
						<tr>
							<th colspan="4" style="width: 25%">
								<span>用人单位代码维护</span>
							</th>
						</tr>
					</thead>
				<tbody>
				<tr>
					<th>
						单位类别
					</th>
					<td colspan="3">
						<input type="radio" name="dwlb" value="01" <logic:equal name="model" property="dwlb" value="01">checked="checked" </logic:equal> disabled>校内单位</input>
						<input type="radio" name="dwlb" value="02" <logic:equal name="model" property="dwlb" value="02">checked="checked" </logic:equal> disabled>校外企业</input>
					</td>
				</tr>
				</tbody>
					<tbody>
					<tr class="xnxx" >
						<th>
							学院(部门)
						</th>
						<td>
							<select name="xydm" id="xydm" disabled>
								<option value=""></option>
								<logic:iterate id="xy" name="xyList">
									<option value="${xy.xydm}" <logic:equal name="model" property="xydm" value="${xy.xydm}">selected="selected"</logic:equal>>${xy.xymc}</option>
								</logic:iterate>
							</select>
						</td>
						<th>工号</th>
						<td>
							${model.zgh}
						</td>
					</tr>
					<tr class="xwxx" style="display: none">
						<th>
							单位名称
						</th>
						<td>
							${model.yrdwmc}
						</td>
						<th>
							行业
						</th>
						<td>
							${model.hy}
						</td>
					</tr>
					<tr>
						<th>
							负责人姓名
						</th>
						<td id="xmtr">
								${model.xm}
						</td>
						<th>
							联系电话
						</th>
						<td>
							${model.lxdh}
						</td>
					</tr>
					<tr class="xwxx" style="display: none">
						<th>
							用户名
						</th>
						<td>
							${model.yhm}
						</td>
						<th>
							密码
						</th>
						<td>
							${model.mm}
						</td>
					</tr>
					<tr>
						<th>
							办公地点
						</th>
						<td>
							${model.bgdd}
						</td>
						<th>
							办公电话
						</th>
						<td>
							${model.bgdh}
						</td>
					</tr>
					<tr>
						<th>
							电子邮件
						</th>
						<td>
							${model.dzyx}
						</td>
						<th>
							QQ
						</th>
						<td>
							${model.qq}
						</td>
					</tr>
					<tr>
						<th class="xnxx">
							申报IP
						</th>
						<td class="xnxx">
							${model.sbip}
						</td>
						<th class="xwxx" style="display: none">
							身份证号
						</th>
						<td class="xwxx" style="display: none">
							${model.sfzh}
						</td>
						<th>
							工资上限
						</th>
						<td>
							${model.gzsx}
						</td>
					</tr>
					<tr>
						<th>
							联系学生
						</th>
						<td>
							${model.lxxs}
						</td>
						<th>
							学生手机
						</th>
						<td>
							${model.xssh}
						</td>
					</tr>
					<tr>
						<th class="xnxx">
							学院（部门）简介</br><span class="red">(限制在2000字内)</span>
						</th>
						<th class="xwxx" style="display: none">
							学企业简介</br><span class="red">(限制在2000字内)</span>
						</th>
						<td colspan="3">
							${model.jj}
						</td>
					</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="关闭" onclick="Close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
		<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>
