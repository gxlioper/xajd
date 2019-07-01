<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!-- 头文件 -->
<script type="text/javascript" src="js/xgutil.js"></script>
<script>
	function save(){
		//必填字段是否填写
		if(filedNotNull(['shlcid'])){
			//提交
			refreshForm('xjyd.do?method=updateXjydshlc&doType=save');
		}else{
			alert('请将带*号的项目填写完整！');
			return false;
		}	
	}
	
</script>
</head>

<body>
	<html:form action="/xjyd.do?method=updateXjydshlc" method="post">
		<input type="hidden" name="pkValue" value="${pkValue }" id="pkValue"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title}</a>
			</p>
		</div>

		<div class="tab">
			<table class="formlist" width="100%">
				<thead>
					<tr>
						<th colspan="4"> 
							<span>基本信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="15%">异动类别代码</th>
						<td>
							${rs.ydlbm}
							<input type="hidden" name="save_ydlbm" value="${rs.ydlbm}"/>						
						</td>
						<th width="15%">异动类别名称</th>
						<td width="35%">
							${rs.ydlbmc}
						</td>
					</tr>
					<tr>
						<th>学籍状态</th>
						<td>							
							${rs.xjzt}						
						</td>
						<th>是否在校</th>
						<td>
							${rs.sfzx}
						</td>
					</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4"> 
								<span>审核流程信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th><font color="red">*</font>审核流程</th>
						<td>
							<html:hidden property="save_shlcid" name="rs" styleId="shlcid"/>
							<input type="text" name="shlcmc" id="shlcmc" value="${rs.shlcmc}" readonly="readonly"/>
							<logic:notEqual value="view" name="doType">
								<button type="button" class="btn_01" onclick="showTopWin('commXgInfo.do?method=choiceLc',650,500);" id="button_shlc" style="display: ">选择</button>
							</logic:notEqual>
						</td>
						<th>同级别参与方式</th>
						<td>
							<input type="text" name="tjbcyfs" id="tjbcyfs" value="${rs.tjbcyfs}" readonly="readonly"/>
						</td>
					</tr>								
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
						<div class="buttontool">
			            <logic:notEqual value="view" name="doType">
							<button type="button" class="" onclick="save();return false;" >
								保&nbsp;&nbsp;存
							</button>
			            </logic:notEqual>
						<button type="button" class="" onclick="Close();return false;">
								关&nbsp;&nbsp;闭
						</button>
						</div>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>		
		</div>	

		<logic:present name="result">
			<intput type="hidden" id="message" value="${message}"/>
			<script>
				alert(document.getElementById('message').value);
				if(window.dialogArguments){
			 		window.dialogArguments.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:present>
	</html:form>
</body>
</html>