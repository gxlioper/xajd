<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript">
			jQuery(document).ready(function() {
				setTimeout(show,100);
				
		    });
			function show(){
				
				jQuery(".zt1").each(function(){
		        	var height=jQuery(this).children().height();
					if(height>60){
						jQuery(this).children().addClass("height60");
						jQuery(this).children().children(".more").show();
						
						/*����ƶ��¼�*/
						var ID=jQuery(this).children().attr("id");
						jQuery("#"+ID).hover(function() {
							jQuery("#"+ID).addClass("content1");
							jQuery(this).children(".more").hide();
							jQuery("#"+ID).removeClass("height60");
						},function(){
							jQuery("#"+ID).removeClass("content1");
							jQuery(this).children(".more").show();
							jQuery("#"+ID).addClass("height60");
						});
					}
				});
				
				var lastSpgw = jQuery("div[name=spjl]").last().attr("spgw");
				var lastGwli = jQuery(".step li[name=" + lastSpgw + "]");
				var lastIndex = lastGwli.index();
			
				jQuery.each(jQuery(".step li"), function(i, n) {
					
					if (i < lastIndex) {
						jQuery(n).addClass("ysh");
					} else if (i > lastIndex){
						jQuery(n).addClass("wsh");
					} else {
						if ("tg" == jQuery("div[name=spjl]").last().attr("class")){
							jQuery(n).addClass("ysh");
						} else {
							jQuery(n).addClass("shz");
						}
					}
		
				});
				
				/*��content��ֵ���*/
				var widthall	=	jQuery(".zt1").width();
				jQuery(".zt1 .content").css("width",widthall-57);
				
				/*��li��ֵ���*/
				var len	=	jQuery(".step").width()-10;
				/*���һ��li��ʵ�ʿ��*/
				var len1	=	jQuery(".step ul li").last().width();
				/*��ǰ״̬��ʵ�ʿ��*/
				var len2	=	jQuery(".step .text").width()+30;
				var len_ul	=	len	-	len1	-	len2;
				var num	=	jQuery(".step ul li").length-1;
				var li_width	=	len_ul/num;
				jQuery(".step ul li").css("width",li_width);
				
				jQuery(".step ul li").last().children(".num").css("width","19px");
				jQuery(".step ul li").last().css("width",len1);
				jQuery(".step ul .shz").prevAll("li").addClass("ysh");
				jQuery(".step ul .yzz").prevAll("li").addClass("ysh");
				jQuery(".step ul .shz").nextAll("li").addClass("wsh");
				jQuery(".step ul .yzz").nextAll("li").addClass("wsh");
				
			}
	</script>

	</head>
	<body>
		<html:form action="/xpj_sqsh">
			<div class="splc1" style="height: auto;">

				<div class="step">
					<ul>
						<logic:iterate id="gw" name="spgwList" indexId="i">
							<li name="${gw.spgw }">
								<p class="user">
									${gw.mc}
								</p>
								<p class="num">
									${i+1}
								</p>
							</li>
						</logic:iterate>
					</ul>
					<div class="text">
						<logic:equal value="0" property="shzt" name="xpjSqshModel">
							δ��ʼ
						</logic:equal>
						<logic:equal value="1" property="shzt" name="xpjSqshModel">
							�ѽ�����ͨ����
						</logic:equal>
						<logic:equal value="2" property="shzt" name="xpjSqshModel">
							�ѽ�������ͨ����
						</logic:equal>
						<logic:equal value="5" property="shzt" name="xpjSqshModel">
							�����
						</logic:equal>
					</div>
				</div>

				<div class="clearall"></div>
				<!--��ͨ��-->
				<logic:iterate id="sp" name="spjlList" indexId="idx" length="len">
					<logic:equal value="0" name="sp" property="shzt">
						<div class="zt1">
							<div class="wks" id="dsh${sp.guid}" name="spjl" spgw="${sp.shid}">
								<a class="more" href="javascript:;"></a>
								<div class="type">
									<div class="title">
										�����
									</div>
								</div>
								<div class="content">
									<div>
										��˸�λ��
										<span class="black1">${sp.gwmc}</span>����ˣ�
										<span class="black1">${sp.shr }</span>���ʱ�䣺
										<span class="black1">${sp.shsj }</span>
									</div>
									<div>
										��������
										<span class="black" style="word-break:break-all;">${sp.shyj }</span>
									</div>
								</div>
								<div style="clear: both"></div>
							</div>
						</div>
						<logic:notEqual value="${idx+1}" name="spjlsize">
							<div class="next">
								<img src="<%=stylePath%>images/knssh_wksnext.gif" />
							</div>
						</logic:notEqual>
					</logic:equal>
					<logic:equal value="1" name="sp" property="shzt">
						<div class="zt1">
							<div class="tg" id="tg${sp.guid}" name="spjl" spgw="${sp.shid}">
								<a class="more" href="javascript:;"></a>
								<div class="type">
									<div class="title">
										��ͨ��
									</div>
								</div>
								<div class="content">
									<div>
										��˸�λ��
										<span class="green1">${sp.gwmc }</span>����ˣ�
										<span class="black1">${sp.shr }</span>���ʱ�䣺
										<span class="black1">${sp.shsj }</span>
									</div>
									<div>
										��������
										<span class="green" style="word-break:break-all;">${sp.shyj }</span>
									</div>
								</div>
								<div style="clear: both"></div>
							</div>
						</div>
						<logic:notEqual value="${idx+1}" name="spjlsize">
							<div class="next">
								<img src="<%=stylePath%>images/knssh_tgnext.gif" />
							</div>
						</logic:notEqual>
					</logic:equal>
					<logic:equal value="2" name="sp" property="shzt">
						<div class="zt1">
							<!--δͨ��-->
							<div class="wtg" id="btg${sp.guid}" name="spjl" spgw="${sp.shid}">
								<a class="more" href="javascript:;"></a>
								<div class="type">
									<div class="title">
										��ͨ��
									</div>
								</div>
								<div class="content">
									<div>
										��˸�λ��
										<span class="red1">${sp.gwmc }</span>����ˣ�
										<span class="black1">${sp.shr }</span>���ʱ�䣺
										<span class="black1">${sp.shsj }</span>
									</div>
									<div>
										��������
										<span class="red" style="word-break:break-all;">${sp.shyj }</span>
									</div>
								</div>
								<div style="clear: both"></div>
							</div>
						</div>
						<logic:notEqual value="${idx+1}" name="spjlsize">
							<div class="next">
								<img src="<%=stylePath%>images/knssh_wtgnext.gif" />
							</div>
						</logic:notEqual>
					</logic:equal>
					<logic:equal value="3" name="sp" property="shzt">
						<div class="zt1">
							<!--δͨ��-->
							<div class="wtg" id="yth${sp.guid}" name="spjl" spgw="${sp.shid}">
								<a class="more" href="javascript:;"></a>
								<div class="type">
									<div class="title">
										���˻�
									</div>
								</div>
								<div class="content">
									<div>
										��˸�λ��
										<span class="red1">${sp.gwmc }</span>����ˣ�
										<span class="black1">${sp.shr }</span>���ʱ�䣺
										<span class="black1">${sp.shsj }</span>
									</div>
									<div>
										��������
										<span class="red" style="word-break:break-all;">${sp.shyj }</span>
									</div>
								</div>
								<div style="clear: both" ></div>
							</div>
						</div>
						<logic:notEqual value="${idx+1}" name="spjlsize">
							<div class="next">
								<img src="<%=stylePath%>images/knssh_wtgnext.gif" />
							</div>
						</logic:notEqual>
					</logic:equal>
				</logic:iterate>
			</div>
		</html:form>
	</body>
</html>
