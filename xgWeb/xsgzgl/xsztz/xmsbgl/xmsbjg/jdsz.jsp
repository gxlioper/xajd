<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		jQuery(function(){
			jQuery(".ipt").focus(function(){
				//jQuery(this).removeClass(redipt);
			});
		});
		function saveXmjdSz(){
			//当有记录时进行验证，无记录时无需验证，直接进行保存操作，后台将直接进行delete操作。
			if(jQuery(".ipt").length != 0){
				var alldataWrite = true;
				var mcNotRepeat = true;
				var sxNotRepeat = true;
				//必填验证
				jQuery(".ipt").each(function(){
					if(this.value == null || jQuery.trim(this.value).length == 0){
						alldataWrite = false;
						jQuery(this).addClass("redipt");
					}
				});
				if(!alldataWrite){
					showAlert("请将标红的输入框填写完整！");
					return false;
				}
				//名称重复验证
				jQuery("[name='jdmc']").each(function(){
					var val = this.value;
					var obj = jQuery("input[name='jdmc'][value="+val+"]");
					if(obj.length > 1){
						mcNotRepeat = false;
						jQuery(obj).addClass("blueipt");
					}else{
						jQuery(obj).removeClass("blueipt");
					}
				});
				if(!mcNotRepeat){
					showAlert("项目阶段名称不能重复！");
					return false;
				}
				//阶段顺序重复验证
				jQuery("[name='jdsx']").each(function(){
					var val = this.value;
					var obj = jQuery("[name='jdsx'][value="+val+"]");
					if(obj.length > 1){
						sxNotRepeat = false;
						jQuery(obj).addClass("purpleipt");
					}else{
						jQuery(obj).removeClass("purpleipt");
					}
				});
				if(!sxNotRepeat){
					showAlert("阶段顺序不能重复！");
					return false;
				}
			}
			//保存项目阶段设置
			var url = "xmsbXmsbjg.do?method=saveXmjdSz";
			ajaxSubFormWithFun("XmsbjgForm", url, function(data) {
				 if(data["message"]=="保存成功！"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		    	 }else{
		    		 showAlert(data["message"]);
		    		}
				});
		}
		function selectAll(obj){
			if(obj.checked){
				jQuery("[name='chk']").not("[disabled]").attr("checked","checked");
			}else{
				jQuery("[name='chk']").removeAttr("checked");
			}
			
		}
		function delRow(){
			var obj = jQuery("[name='chk']:checked").parents("tr");
			if(obj.length == 0){
				showAlert("请先勾选项目阶段数据！");
				return false;
			}
			jQuery(obj).remove();
		}
		function addRow(){
			jQuery("#tbody_xmjd").append(
				"<tr class='autorow'>"+
             		"<td width='5%'><input type='checkbox' name='chk'  /><input type='hidden' name='jdid' /></td>"+
				    "<td width='15%'><input type='text' maxlength='50'  onblur = 'removeRed(this)'  class='ipt' name='jdmc' /></td>"+
				    "<td width='15%'><input type='text' maxlength='3' onblur = 'removeRed(this)' onkeyup='checkInputNum(this)' class='ipt' name='jdf' /></td>"+
				    "<td width='15%'><input type='text' maxlength='3' onblur = 'removeRed(this)' onkeyup='checkInput(this)' class='ipt' name='jdsx'/></td>"+
             	"</tr>"
			);
		}

		//onblur时进行验证
		function removeRed(obj){
			var objclass = jQuery(obj).attr("class");
			var objval = obj.value;
			if(objclass.indexOf("redipt") != -1){
				if(jQuery(obj).val() !=null && jQuery.trim(objval).length != 0){
					jQuery(obj).removeClass("redipt");
				}
			}
			if(objclass.indexOf("blueipt") != -1){
				jQuery("[name='jdmc']").each(function(){
					var val = this.value;
					var obj = jQuery("input[name='jdmc'][value="+val+"]");
					if(obj.length <= 1){
						jQuery(obj).removeClass("blueipt");
					}
				});
			}
			if(objclass.indexOf("purpleipt") != -1){
				jQuery("[name='jdsx']").each(function(){
					var val = this.value;
					var obj = jQuery("[name='jdsx'][value="+val+"]");
					if(obj.length <= 1){
						jQuery(obj).removeClass("purpleipt");
					}
				});
			}
		}
		</script>
		<style type="text/css">
			.ipt{width:90%;}
			.redipt{border-color:red;}
			.blueipt{border-color:blue;}
			.purpleipt{border-color:purple;}
		</style>
	</head>
	<body style="width: 100%">
		<html:form action="/xmsbXmsbjg" method="post" styleId="XmsbjgForm" onsubmit="return false;">
			<html:hidden property="xmdm" styleId="xmdm" value ="${xmdm}"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<tbody id="tbody_xmjd">
						<tr>
							<th colspan="4">
									<span style="float:left">项目阶段</span>
								<img  style="float:left;margin-left:4px" src="xsxx/fbgl/images/add-icons-1.gif" alt="增加" onclick="addRow();return false;">
								<img  style="float:left;margin-left:4px" src="xsxx/fbgl/images/delete-icons-2.gif" alt="删除" onclick="delRow();return false;">
								<a href="javascript:void(0)"  style="float:left;margin-left:40px;color:blue;font-weight:bold" >${xmmc}</a>
							</th>
                         </tr>
                          <tr>
							<td width='5%'><input type="checkbox" name="th" onclick="selectAll(this);" /></td>
							<td width='15%'>项目阶段名称</td>
							<td width='15%'>阶段分</td>
							<td width='15%'>阶段顺序</td>
                         </tr>
                         <logic:iterate id="i" name="xmjdsz">
                         	<tr class="autorow">
                         		
                         	<logic:equal value="0" name="i" property="num">
                         		<td width='5%'>
                         			<input type="checkbox" name="chk"  />
                         			<input type="hidden" name="jdid" value="${i.jdid}"/>
                         		</td>
                         	   <td width='15%'><input type="text" class="ipt" onblur = 'removeRed(this)'  maxlength="50"  name="jdmc" value="${i.jdmc}"/></td>
							    <td width='15%'><input type="text" class="ipt" onblur = 'removeRed(this)'  onkeyup="checkInputNum(this)" maxlength="3" name="jdf" value="${i.jdf}"/></td>
							    <td width='15%'><input type="text" class="ipt" onblur = 'removeRed(this)'  onkeyup="checkInput(this)" maxlength="3" name="jdsx" value="${i.jdsx}"/></td>
                         		
                         	</logic:equal>
                       		<logic:notEqual value="0" name="i" property="num">
                       			<td width='5%'>
                         			<input type="checkbox" name="chk" disabled="disabled"  />
                         			<input type="hidden" name="jdid" value="${i.jdid}"/>
                         		</td>
                         	   <td width='15%'><input readonly="readonly"type="text" class="ipt" onblur = 'removeRed(this)'  maxlength="50"  name="jdmc" value="${i.jdmc}"/></td>
							    <td width='15%'><input readonly="readonly" type="text" class="ipt" onblur = 'removeRed(this)'  onkeyup="checkInputNum(this)" maxlength="3" name="jdf" value="${i.jdf}"/></td>
							    <td width='15%'><input readonly="readonly" type="text" class="ipt" onblur = 'removeRed(this)'  onkeyup="checkInput(this)" maxlength="3" name="jdsx" value="${i.jdsx}"/></td>
                       		</logic:notEqual>
                         	
							   
                         	</tr>
                         </logic:iterate>
					</tbody>
				</table>
			</div>
			  <div style="height:30px"></div>   
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" onclick="saveXmjdSz();">
										保存
									</button>
									<button type="button" onclick="iFClose();">
										关闭
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

