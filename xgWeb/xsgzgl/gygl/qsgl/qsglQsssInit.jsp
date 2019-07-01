<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type='text/javascript' src="js/uicomm.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script type='text/javascript' src="js/comm/message.js"></script>
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
	
	<script	type="text/javascript">
		function loadQs(){
			var api = frameElement.api,W = api.opener;
			//复选框选中学号
			var pks = W.document.getElementsByName("primarykey_cbv");
			//可初始化寝室数
			var num = W.document.getElementById("kcshqss").value;
			//查询的数据集的查询条件
			var searchTjstr = W.document.getElementById("searchTjstr").value;

			var RowsStr="";
			var count =0;
			for (i=0; i<pks.length; i++){
	 			if(pks[i].checked){
	 				RowsStr+=pks[i].value+"!!splitOne!!";
	 				count++;
	 			}
			}
			if(count==0){//未选中学生
				jQuery('#searchTjstr').val(searchTjstr);
				jQuery('#xhtd').html("当前查询结果中共有<font color='red'>"+num+"</font>个可初始化的寝室，可执行寝室所属初始化操作");
				if(num == 0){
					jQuery('#buttonSave').attr({disabled:'disabled'});
				}
			}else{//选中学生
				jQuery('#qsStr').val(RowsStr);
				jQuery('#xhtd').html("当前共选中<font color='red'>"+count+"</font>个寝室，可执行寝室所属初始化操作");

			}
		}
		
		function saveTsxx(){
			saveData('gyglnew_qsgl.do?method=qsglQsssInit&doType=init','');
		}
	</script>
</head>
<body onload="loadQs()">
	<html:form action="/gyglnew_cwgl" method="post">	
		<input type="hidden" id="qsStr" name="qsStr" value=""/>
		<input type="hidden" id="searchTjstr" name="searchTjstr" value=""/>
		<input type="hidden" id="count" value=""/>
		<%--<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>退宿信息增加</a>
			</p>
		</div>		
		--%>
		<div class="prompt" id="span_qsh" >
	       <h3><span>提示：</span></h3>
	       <p>初始化寝室所属时，“是否初始化寝室床位所属”选择“是”，则同时初始化寝室中床位的所属</p>
	   	</div>
		
		<div class="tab">
		<table class="formlist" width="95%">			
			<thead>
				<tr style="height:22px">
					<th colspan="2" width="23%">
						<span>初始化寝室所属</span>
					</th>
				</tr>
			</thead>			
			<tbody>
				<tr >
					<th width="23%">
						初始化寝室数量				
					</th>
					<td id="xhtd">
						
					</td>
				</tr>
				<tr>
					<th width="23%">
						<font color="red">*</font>是否初始化寝室床位所属				
					</th>
					<td>
						<input type="radio" name="sfqccwss" value="是" checked="checked"/>是
						<input type="radio" name="sfqccwss" value="否"/>否
					</td>
				</tr>
			</tbody>
			<tfoot>
			      <tr>
			        <td colspan="2">
<%--			        <div class="bz">"<span class="red">*</span>"为必填项</div>--%>
			          <div class="btn">
			          	<button type="button"  name="提交" id="buttonSave" onclick="saveTsxx();">保存</button>
			            <button type="button"  name="关闭" id="buttonClose" onclick="Close();return false;">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
		</div>
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
		 	showAlert("保存成功",{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
			
		</script>
	</logic:present>
</body>
</html>
