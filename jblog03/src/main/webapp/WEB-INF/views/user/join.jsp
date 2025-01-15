<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script
	src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
	$(function() {
		$("#btn-checkemail")
				.click(
						function() {
							var blogId = $("#blog-id").val();

							if (blogId == "") {
								return;
							}

							$
									.ajax({
										url : "${pageContext.request.contextPath}/api/user/checkBlogId?id="
												+ blogId,
										type : "get",
										dataType : "json",
										success : function(response) {
											if (response.exist) {
												alert("아이디가 존재합니다. 다른 아이디를 사용해 주세요.");
												$("#blog-id").val("");
												$("#blog-id").focus();
												return;
											}

											$("#img-checkemail").show();
											$("#btn-checkemail").hide();
										},
										error : function(xhr, status, err) {
											console.error(err);
										}
									});
						});
	});
</script>
</head>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/main/header.jsp" />
		<form:form modelAttribute="userVo" class="join-form" id="join-form" method="post"
			action="${pageContext.request.contextPath}/user/join">
			<label class="block-label" for="name">이름</label>
 			<form:input id="name" name="name" type="text" value="" path="name"/>
 			<p style="padding: 5px 0; margin: 0; color: #f00; text-align: left">
				<form:errors path="name" />
			</p>

			<label class="block-label" for="blog-id">아이디</label>
			<form:input id="blog-id" name="id" type="text" path="id"/>
			<input id="btn-checkemail" type="button" value="id 중복체크">
			<img id="img-checkemail" style="display: none;"
				src="${pageContext.request.contextPath}/assets/images/check.png">
			<p style="padding: 5px 0; margin: 0; color: #f00; text-align: left">
				<form:errors path="id" />
			</p>

			<label class="block-label" for="password">패스워드</label>
			<form:input id="password" name="password" type="password" path="password"/>
			<p style="padding: 5px 0; margin: 0; color: #f00; text-align: left">
				<form:errors path="password" />
			</p>

			<fieldset>
				<legend>약관동의</legend>
				<form:checkbox path="agreeProv" id="agree-prov" name="agreeProv" value="y"/>
				<label class="l-float">서비스 약관에 동의합니다.</label>
				<p style="padding: 5px 0; margin: 0; color: #f00; text-align: left">
					<form:errors path="agreeProv" />
				</p>
			</fieldset>

			<input type="submit" value="가입하기">

		</form:form>
	</div>
</body>
</html>
