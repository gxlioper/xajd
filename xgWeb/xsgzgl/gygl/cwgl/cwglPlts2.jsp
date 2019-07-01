<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type='text/javascript' src="js/uicomm.js"></script>
	<script type='text/javascript' src="js/String.js"></script>
	
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
	
	<script	type="text/javascript">
	//学籍异动、毕业处理后退宿的页面，武昌首义个性化
	
	function loadXsxx(){
		var xh=jQuery('#xh').val();
		if(""==xh||null==xh){
			xh=jQuery('#xhstr').val();
		}
		jQuery.post('gyglnew_cwgl.do?method=loadXsxx',{xh:xh},function(data){
			var xh = data.xh;
			if(xh!=undefined ){
				
				var xm = data.xm;
				var xb = data.xb;
				var ldmc = data.ldmc;
				var qsh = data.qsh;
				var cwh = data.cwh;
				var xymc = data.xymc;
				var nj = data.nj;
				var option = "<tr><input type='hidden' name='pk_xh' value='"+xh+"'/><td align='left'>" + xh + 
							"</td><td align='left'>" + xm + "</td><td align='left'>" + xb + "</td><td align='left'>" + nj + "</td><td>" + xymc + 
							"</td><td align='left'>" + ldmc + "</td><td align='left'>" + qsh + "</td><td align='left'>" + cwh + 
							"</td></tr>"
				
				jQuery('#xsxx').append(option);		
				jQuery('#xstr').css({display:''});	
			}else{
				jQuery('#buttonSave').css({display:'none'});	
				jQuery('#czsm').append("该学生无寝室信息");	
			}					
		},'json'); 
	}
		
	function loadXs(){
		var xh=jQuery('#xh').val();
		var xhstr=jQuery('#xhstr').val();
		var count =jQuery('#count').val();
		
		if(""!=xh&&null!=xh){
			loadXsxx();
			return false;
		}
		jQuery('#xhtd').html("当前共选中<font color='red'>"+count+"</font>个学生，可执行退宿操作");
		if(count=="1"){//选中一个学生，加载学生信息
			loadXsxx();
		}
	}
		
	function saveTsxx(){
		var xn = jQuery('#xn').val();
		if(!check("tsyy-tssj-xn-xq")){
			return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
		}else{
			jQuery("#buttonSave").attr({disabled:'disabled'});
			saveData('gyglnew_cwgl.do?method=cwglPlts2&doType=save','');
		}
	} 
			
	jQuery(function(){
		loadXs()
		
	})
		
		/**
		 * 验证是否存在空项
		 * @param ids 要验证的控件id "-"分隔
		 * @return
		 */
		function check(ids){
			var id=ids.split("-");
			for(var i=0;i<id.length;i++){
				var lddm=jQuery("#"+id[i]).val();
				if(lddm==null||""==lddm){
					//alert(id[i]);
					return false;
				}
			}
			return true;
		}
		
		function refershParent1(){
	
			if(frameElement.api){
				var api = frameElement.api,W = api.opener;
				jQuery(W.document).find('#search_go').click();
				closeDialog();
			} else {
				jQuery(parent.window.document).find('#search_go').click();
				iFClose();
			}
		}
	</script>
</head>
<body >
	<html:form action="/gyglnew_cwgl" method="post">	
		<input type="hidden" id="xh" name="xh" value="${xh}"/>
		<input type="hidden" id="xhstr" name="xhstr" value="${shstr}"/>
		<input type="hidden" id="searchTjstr" name="searchTjstr" value="${searchTjstr}"/>
		<input type="hidden" id="count" value="${count}"/>
		<%--<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>退宿信息增加</a>
			</p>
		</div>		
		--%>
		<div class="prompt" id="span_qsh">
	       <h3><span>提示：</span></h3>
	       <p>退宿时，若是“是否初始化床位所属”选择“是”，系统自动初始化床位所属，即床位分配状态变更为未分配</p>
	   	</div>
		
		<div class="tab">
		<table class="formlist" width="95%">			
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>退宿信息:</span>
					</th>
				</tr>
			</thead>			
			<tbody>
				<tr >
					<th width="16%">
						<span class="red">*</span>退宿操作说明				
					</th>
					<td colspan="3" id="xhtd">
						<span id="czsm"></span>
					</td>
				</tr>
				<tr id="xstr" style="display: none">
					<th width="16%">
						<span class="red">*</span>退宿学生信息				
					</th>
					<td colspan="3" >
						<table id="xsxx" style="width: 100%">
							<tr>
								<th style="text-align: left">学号</th>
								<th style="text-align: left">姓名</th>
								<th style="text-align: left">性别</th>
								<th style="text-align: left">年级</th>
								<th style="text-align: left"><bean:message key="lable.xsgzyxpzxy" /></th>
								<th style="text-align: left">楼栋</th>
								<th style="text-align: left">寝室</th>
								<th style="text-align: left">床位</th>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<th width="16%">
						<span class="red">*</span>退宿原因				
					</th>
					<td>
						<html:select property="tsyy" styleId="tsyy" >						
							<html:options collection="tsyyList" labelProperty="tsyymc" property="tsyydm"/>
						</html:select>
					</td>
					<th width="16%">
						<span class="red">*</span>退宿时间				
					</th>
					<td>
						<html:text property="tssj" styleId="tssj" onkeypress="onlyBackSpace(this,event);" style="width:100px;"
							onclick="return showCalendar(this.id,'yyyy-MM-dd','','',320,10)" ></html:text>
					</td>
				</tr>
				<tr>
					<th align="right">
						<span class="red">*</span>学年/学期
					</th>
					<td align="left">
						<html:select property="xn" styleId="xn" disabled="false">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
						</html:select>
						<html:select property="xq" styleId="xq" disabled="false" >
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
						</html:select>
					</td>
					<th width="16%">
						<span class="red">*</span>床位是否初始化			
					</th>
					<td>
						<logic:equal value="xx" name="userStatus" scope="session">
							<input type="radio" name="sfqccwss" value="是" checked="checked"/>是
							<input type="radio" name="sfqccwss" value="否" />否
						</logic:equal>
						<logic:notEqual value="xx" name="userStatus" scope="session">
							<input type="radio" name="sfqccwss" value="是" disabled="disabled"/>是
							<input type="radio" name="sfqccwss" value="否" checked="checked" disabled="disabled"/>否
						</logic:notEqual>
					</td>
				</tr>
				<tr>
					<th>
						备注
						<br /><font color="red">(限1000字)</font>
					</th>
					<td colspan="3">
						<html:textarea property='bz' style="width:95%" styleId="bz" rows='4'/>
					</td>
				</tr>
			</tbody>
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
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
		<script type="text/javascript">0
		showAlert("保存成功",{},{"clkFun":function(){
				if (parent.window){
					refershParent1();
				}
			}});
		</script>
	</logic:present>
</body>
</html>
