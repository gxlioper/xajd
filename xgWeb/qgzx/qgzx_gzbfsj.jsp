<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script type="text/javascript">	
	function searchCheck(){
		gzbfSave('nd-yf');
	}
	
	//勤工助学数据保存
	function gzbfSave(mustFill){
	if(checkXnNd("xn", "nd")){
		//var xxdm = document.getElementById('xxdm').value;
		var eles = mustFill.split("-");
		//var url = "cjff.do?method=reWorkPut&maxgz="+maxgz;
		var url = "cjff.do?method=reWorkPut";
		for (i = 0; i < eles.length; i++) {
			if (document.getElementById(eles[i]).value == "") {
				alert("请将带\"*\"号的项目输入完整！");
				return false;
			}
		}
		document.forms[0].action = url;
		document.forms[0].submit();
	}
	}
	</script>
</head>
	<body>
		<html:form action="/qgzxLogic" method="post">
			<input type="hidden" id="pk" name="pk" value="${pk}" />
			<input type="hidden" id="pkValue" name="pkValue" value="${pkValue}" />
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>勤工助学 - 工资发放 - 工资补发 - 补发时间设置</a>
				</p>
			</div>
			<div class="tab">
			<table width="100%" id="rsT" class="formlist">
				<thead>
					<tr>
						<th colspan="2">
							<span>补发时间</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th>岗位</th>
					<td>
						<input type="text" disabled="disabled" value="${gwmc}"/>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>学年</th>
					<td >
						<html:select property="xn" styleId="xn">
						<html:options collection="ndList" property="xn" labelProperty="xn"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>年度</th>
					<td >
						<html:select property="nd" styleId="nd">
						<html:options collection="ndList" property="nd" labelProperty="nd"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>学期</th>
					<td>
						<html:select property="xq" styleId="xq">
						<html:option value=""></html:option>
						<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>						</html:select>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>月份</th>
					<td>
						<html:select property="yf" styleId="yf">
						<html:option value=""></html:option>
						<html:options collection="yfList" property="yf" labelProperty="yf"/>
						</html:select>
					</td>					
				</tr>	
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			            <button type="button" class="button2" onclick="searchCheck()">
							提 交
						</button>
						<button type="button" class="button2" onclick="Close();return false;">
							关 闭
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
