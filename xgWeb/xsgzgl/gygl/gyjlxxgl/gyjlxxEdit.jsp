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
	function loadXsxx(){
		jQuery.post('gyglnew_gyjlgl.do?method=loadXsxx',{xh:jQuery('#xh').val()},function(data){
			var xh = data.xh;
			if(xh!=undefined ){
				var xm = data.xm;
				var xymc = data.xymc;
				var zymc = data.zymc;
				var bjmc = data.bjmc;
											
				var option = "<tr><input type='hidden' name='pk_xh' value='"+xh+"'/><td align='right'>" + xh + 
							"</td><td align='right'>" + xm + "</td><td>" + xymc + 
							"</td><td align='right'>" + zymc + "</td><td align='right'>" + bjmc + "</td></tr>"
				
				jQuery('#xsxx').append(option);		
				jQuery('#xstr').css({display:''});	
			}else{
				jQuery('#xstr').css({display:'none'});	
			}					
		},'json');
	}
	function getJllbList(obj){
		jQuery.post('gyglnew_gyjlgl.do?method=getJllbList',{jldldm:obj.value},function(data){
				var option = "<option value=''>--请选择纪律类别--</option>";
				for(var i = 0; i < data.length; i++){
					option +="<option value='"+data[i].jllbdm+"'>"+data[i].jllbmc+"</option>";
				}
				jQuery('#jllbdm').empty().append(option);			
		},'json');
	}	
		function loadXs(){
			//复选框选中学号
			var pks = window.dialogArguments.document.getElementsByName("primarykey_checkVal");
			var xhs = window.dialogArguments.document.getElementsByName("xh");
			//查询的总数据集的 条数			
			var num = window.dialogArguments.document.getElementById("num").value;
			//查询的数据集的查询条件
			var searchTjstr = window.dialogArguments.document.getElementById("searchTjstr").value;
			
			var RowsStr="";
			var count =0;
			for (i=0; i<pks.length; i++){
	 			if(pks[i].checked){
	 				RowsStr+=xhs[i].value+"!!splitOne!!";
	 				count++;
	 			}
			}
			if(count==0){//未选中学生
				jQuery('#searchTjstr').val(searchTjstr);
				jQuery('#xhtd').html("当前查询结果中共有<font color='red'>"+num+"</font>个学生，可执行违纪操作");
				if(num == "1"){//总数据集仅一条数据，加载学生信息
					jQuery('#xhstr').val(xhs[0].value);
					jQuery('#xh').val(xhs[0].value);
					loadXsxx();
				}
				if(num == 0){
					jQuery('#buttonSave').attr({disabled:'disabled'});
				}
			}else{//选中学生
				jQuery('#xhstr').val(RowsStr);
				jQuery('#xhtd').html("当前共选中<font color='red'>"+count+"</font>个学生，可执行违纪操作");
				if(count=="1"){//选中一个学生，加载学生信息
					jQuery('#xh').val(RowsStr.replace("!!splitOne!!",""));
					loadXsxx();
				}
			}
		}
		
		function save(){
			if(jQuery('#wjsj').val()==""){
				alertInfo("请填写违纪时间！");
				return false;
			}
			if(jQuery('select[name=jldldm]').val()==""||jQuery('select[name=jllbdm]').val()==""){
				alertInfo("请选择违纪类型！");
				return false;
			}
			saveData('gyglnew_gyjlgl.do?method=gyjlxxEdit&doType=save','');
		}
		
		jQuery(function(){
			loadXs();
		})

		
	</script>
</head>
<body>
	<html:form action="/gyglnew_gyjlgl" method="post">	
		<input type="hidden" id="xh" name="xh"　value=""/>
		<input type="hidden" id="xhstr" name="xhstr" value=""/>
		<input type="hidden" id="searchTjstr" name="searchTjstr" value=""/>
		<input type="hidden" id="count" value=""/>
		
		<div class="tab">
		<table class="formlist" width="95%">			
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>违纪信息</span>
					</th>
				</tr>
			</thead>			
			<tbody>
				<tr >
					<th width="16%">
						<font color="red">*</font>违纪操作说明				
					</th>
					<td colspan="3" id="xhtd">
						
					</td>
				</tr>
				<tr id="xstr" style="display: none">
					<th width="16%">
						<font color="red">*</font>违纪学生信息				
					</th>
					<td colspan="3" >
						<table id="xsxx" style="width: 100%">
							<tr>
								<th>学号</th><th>姓名</th><th><bean:message key="lable.xsgzyxpzxy" /></th>
								<th>专业</th><th>班级</th>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<th width="16%">
						<font color="red">*</font>违纪时间				
					</th>
					<td colspan="3">
						<html:text property="wjsj" styleId="wjsj" onkeypress="onlyBackSpace(this,event);"
							onclick="return showCalendar(this.id,'yyyy-MM-dd HH:mm:ss')" ></html:text>
					</td>
				</tr>
				<tr>
					<th width="16%">
						<font color="red">*</font>违纪类型				
					</th>
					<td>
						<html:select property="jldldm" styleId="jldldm" onchange="getJllbList(this)">
							<html:option value="">--请选择纪律大类--</html:option>
							<html:options collection="jldlList" labelProperty="jldlmc" property="jldldm"/>
						</html:select>
					</td>
					<td colspan="2">
						<html:select property="jllbdm" styleId="jllbdm" >
							<html:option value="">--请选择纪律类别--</html:option>	
							<html:options collection="jllbList" labelProperty="jllbmc" property="jllbdm"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						备注
						<br /><font color="red">(限制在1000字内)</font>
					</th>
					<td colspan="3">
						<html:textarea property='bz' style="width:95%" styleId="bz" rows='3'/>
					</td>
				</tr>
			</tbody>
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          	<button type="button"  name="提交" id="buttonSave" onclick="save();return false;">保存</button>
			            <button type="button"  name="关闭" id="buttonClose" onclick="window.close();return false;">关闭</button>
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
				refreshParent2();
			});
			
		</script>
	</logic:present>
</body>
</html>
