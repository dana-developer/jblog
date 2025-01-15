<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
pageContext.setAttribute("newLine", "\n");
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/blog/header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<c:choose>
						<c:when test='${not empty post}'>
							<h4>${post.title }</h4>
							<p>${fn:replace(post.contents, newLine, "<br>")}
						</c:when>
						<c:otherwise>
							<h4>아직 글이 없습니다.</h4>
							<p>글을 작성해주세요.
						</c:otherwise>
					</c:choose>
					<p>
				</div>
				<ul class="blog-list">
					<c:forEach items="${postList}" var="post" varStatus="status">
						<c:choose>
							<c:when test='${currentPostId == post.id}'>
								<li><a style="color: red"
									href="${pageContext.request.contextPath }/${blog.blogId}/${currentCategoryId}/${post.id}">${post.title }</a>
									<span>${post.regDate }</span></li>
							</c:when>
							<c:otherwise>
								<li><a
									href="${pageContext.request.contextPath }/${blog.blogId}/${currentCategoryId}/${post.id}">${post.title }</a>
									<span>${post.regDate }</span></li>
							</c:otherwise>
						</c:choose>

					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<c:choose>
					<c:when test='${blog.profile == ""}'>
						<img style="height: 200px"
							src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg">
					</c:when>
					<c:otherwise>
						<img style="height: 200px"
							src="${pageContext.request.contextPath }${blog.profile }">
					</c:otherwise>
				</c:choose>
			</div>
		</div>



		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${categoryList}" var="category" varStatus="status">
					<c:choose>
						<c:when test='${currentCategoryId == category.id}'>
							<li><a style="color: red"
								href="${pageContext.request.contextPath}/${blog.blogId}/${category.id}">${category.name }</a>
							</li>
						</c:when>
						<c:otherwise>
							<li><a
								href="${pageContext.request.contextPath}/${blog.blogId}/${category.id}">${category.name }</a></li>
						</c:otherwise>
					</c:choose>

				</c:forEach>
			</ul>
		</div>

		<c:import url="/WEB-INF/views/blog/footer.jsp" />
	</div>
</body>
</html>