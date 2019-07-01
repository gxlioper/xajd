<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<style type="text/css">
			.djbx .djbx_right .djbx_page .dy a.nocur{
				background:url("<%=stylePath %>images/djbx_but2.gif") no-repeat scroll 0 0 rgba(0, 0, 0, 0)
			}
		</style>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#currenImg").attr("src",jQuery("#imgList img").eq(0).attr("src"));
			});
			
			function toBefore(guid){
				var api = frameElement.api, W = api.opener;
				var beforeLi = jQuery(W.document).find("#"+guid).prev();
				var _guid;
				var ywid = jQuery("#ywid").val();
				var mkdm = jQuery("#mkdm").val();
				var dybb = jQuery("#dybb").val();
				
				if (beforeLi.html() != null){
					_guid = beforeLi.attr("id");
				} else {
					_guid = jQuery(W.document).find(".djb_list_con li").last().attr("id");
				}
				api.reload(window,"comm_bbdmpz.do?method=queryBbdm&guid=" + _guid +  "&ywid=" + ywid + "&mkdm=" + mkdm +"&dybb=" + dybb);
			}
			
			function toNext(guid){
				var api = frameElement.api, W = api.opener;
				var nextLi = jQuery(W.document).find("#"+guid).next();
				var _guid;
				var ywid = jQuery("#ywid").val();
				var mkdm = jQuery("#mkdm").val();
				var dybb = jQuery("#dybb").val();
				
				if (nextLi.html() != null){
					_guid = nextLi.attr("id");
				} else {
					_guid = jQuery(W.document).find(".djb_list_con li").first().attr("id");
				}
				
				api.reload(window,"comm_bbdmpz.do?method=queryBbdm&guid=" + _guid +  "&ywid=" + ywid + "&mkdm=" + mkdm +"&dybb=" + dybb);
			}
			
			function updateBbdm(){
				
				var guid = jQuery("#guid").val();
				var ywid = jQuery("#ywid").val();
				var mkdm = jQuery("#mkdm").val();
				
				jQuery.post("comm_bbdmpz.do?method=setBbdm",{guid:guid,ywid:ywid,mkdm:mkdm},function(data){
					if (Boolean(data)){
						showAlert("设置成功！",{},{"clkFun":function(){
							jQuery("#cz").removeAttr("onclick");
							jQuery("#cz").unbind("click");
							jQuery("#cz").bind("click",qx);
							jQuery("#cz").attr("class","nocur");
							jQuery("#bgImg").show();
						}});
						
					}
				});
			}
			function qx(){
				var guid = jQuery("#guid").val();
				var ywid = jQuery("#ywid").val();
				var mkdm = jQuery("#mkdm").val();
				jQuery.post("comm_bbdmpz.do?method=setBbdm",{guid:"",ywid:ywid,mkdm:mkdm},function(data){
					if (Boolean(data)){
						showAlert("设置成功！",{},{"clkFun":function(){
							jQuery("#cz").removeAttr("onclick");
							jQuery("#cz").unbind("click");
							jQuery("#cz").bind("click",updateBbdm);
							jQuery("#cz").attr("class","cur");
							jQuery("#bgImg").hide();
						}});
						
					}
				});
			}
	    </script>
		
	</head>

	<body>
		<input type="hidden" id="bbdm" value="${bbdmModel.bbdm }"/>
		<input type="hidden" id="guid" value="${bbdmModel.guid }"/>
		<input type="hidden" id="mkdm" value="${bbdmModel.mkdm }"/>
		<input type="hidden" id="ywid" value="${bbdmModel.ywid }"/>
		<input type="hidden" id="dybb" value="${bbdmModel.dybb }"/>
		<div class="djbx">
			<div class="djbx_left">
				<div class="djbx_left1">
					<a href="javascript:toBefore('${bbdmModel.guid }');" id="prev" title="上一张表格"></a>
				</div>
				<div class="djbx_left2">
					<div class="djbx_left2_zz" style="display: block">
						<img src="<%=stylePath%>images/djbx.gif" id="bgImg"
							<logic:notEqual value="${bbdmModel.guid }" name="bbdmModel" property="dybb">
							style="display:none;"
							</logic:notEqual>
						/>
					</div>
					<div class="djbx_left2_img">
						<div>
							<ul>
								<li>
									<img src="" width="441" height="582" id="currenImg"/>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="djbx_left3">
					<a href="javascript:toNext('${bbdmModel.guid }');" id="next" title="下一张表格"></a>
				</div>
			</div>

			<div class="djbx_right">
				<h3>
				</h3>
				<div class="djbx_page">
					<ul id="imgList">
						<logic:present name="bbxxList">
							<logic:iterate id="bbxx" name="bbxxList" indexId="i">
								<li>
									<a href="javascript:void(0);">
										<img src="<%=request.getContextPath() %>${bbxx.tplj }" width="44" height="57" 
											 onclick="jQuery('#currenImg').attr('src',this.src);"
										/>
									</a>
									<br />
									第${i+1 }页
								</li>
							</logic:iterate>
						</logic:present>
					</ul>
					<div>
						<img src="<%=stylePath%>images/djbx_xian.gif" />
					</div>
					<div class="dy">
						<logic:equal value="${bbdmModel.guid }" property="dybb" name="bbdmModel" >
								<a id="cz" href="#" class="nocur" onclick="qx();"></a>
						</logic:equal>
						<logic:notEqual value="${bbdmModel.guid }"  property="dybb" name="bbdmModel" >
								<a id="cz" href="#" class="cur" onclick="updateBbdm();"></a>
						</logic:notEqual>
						
					</div>
				</div>
			</div>
		</div>

	</body>
</html>
