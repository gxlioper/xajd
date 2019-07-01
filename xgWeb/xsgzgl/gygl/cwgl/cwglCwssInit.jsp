<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
	
	<script	type="text/javascript">
		function loadXs(){
			
			var api = frameElement.api,W = api.opener;
			//复选框选中学号
			var pks = W.document.getElementsByName("primarykey_checkVal");
			//查询的总数据集的 条数			
			var num = W.document.getElementById("wrzcws").value;
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
				jQuery('#xhtd').html("当前查询结果中共有<font color='red'>"+num+"</font>个未入住且已分配的床位，可执行床位所属初始化操作");
				if(num == 0){
					jQuery('#buttonSave').attr({disabled:'disabled'});
				}
			}else{//选中学生
				jQuery('#cwStr').val(RowsStr);
				jQuery('#xhtd').html("当前共选中<font color='red'>"+count+"</font>个未入住且已分配的床位，可执行床位所属初始化操作");

			}
		}
		
		function saveTsxx(){
			saveData('gyglnew_cwgl.do?method=cwglCwssInit&doType=init','');
		}
				
		jQuery(function(){
			loadXs();
		})
		
		function refreshParent3(){
			var api = frameElement.api,W = api.opener;
			if(W == undefined){
				if(W && W.document.getElementById('search_go')){
					W.document.getElementById('search_go').click();
				}
			}else{
				if(	W && W.document.getElementById('search_go')){
					W.document.getElementById('search_go').click();		
				}
			}
			Close();
		}
	</script>
</head>
<body>
	<html:form action="/gyglnew_cwgl" method="post">	
		<input type="hidden" id="cwStr" name="cwStr" value=""/>
		<input type="hidden" id="searchTjstr" name="searchTjstr" value=""/>
		<input type="hidden" id="count" value=""/>
		<%--<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>初始化床位所属</a>
			</p>
		</div>		
		--%>
		<div class="prompt" id="span_qsh">
	       <h3><span>提示：</span></h3>
	       <p>初始化床位所属时，若是“是否初始化对应寝室所属”选择“是”，那么当寝室中的床位均无所属，即未分配给<bean:message key="lable.xsgzyxpzxy" />时，系统自动初始化寝室所属，寝室分配状态变更为未分配</p>
	   	</div>
		
		<div class="tab">
		<table class="formlist" width="95%">			
			<thead>
				<tr style="height:22px">
					<th colspan="2">
						<span>初始化床位所属</span>
					</th>
				</tr>
			</thead>			
			<tbody>
				<tr >
					<th width="16%">
						<font color="red">*</font>初始化床位数量				
					</th>
					<td id="xhtd">
						
					</td>
				</tr>
				<tr>
					<th width="16%">
						<font color="red">*</font>初始化类型			
					</th>
					<td>
						<html:select property="cshlx" styleId="cshlx" >						
							<html:options collection="cshlxList" labelProperty="mc" property="dm"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th width="16%">
						<font color="red">*</font>是否初始化对应寝室所属				
					</th>
					<td>
						<input type="radio" name="sfqcqsss" value="是" checked="checked"/>是
						<input type="radio" name="sfqcqsss" value="否"/>否
					</td>
				</tr>
			</tbody>
			<tfoot>
			      <tr>
			        <td colspan="2"><div class="bz">"<span class="red">*</span>"为必填项</div>
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
			alertInfo('保存成功', function(){
				refreshParent3();
			});
			
		</script>
	</logic:present>
</body>
</html>
