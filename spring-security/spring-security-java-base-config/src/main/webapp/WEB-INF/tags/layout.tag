<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="title" type="java.lang.String" required="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Security | ${title}</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/application.js"></script>

</head>

<nav class="navbar navbar-expand bg-primary navbar-dark">

	<div class="container">

		<a href="${pageContext.request.contextPath}/"> <i class="bi-house"></i>
			Spring Security
		</a>

		<ul class="navbar-nav">
			<security:authorize access="hasAuthority('Admin')">
				<li class="nav-item"><a
					href="${pageContext.request.contextPath}/admin"
					class="nav-link ${title eq 'Admin' ? 'active' : '' }"> <i
						class="bi-shield"></i> Admin
				</a></li>
			</security:authorize>
			<security:authorize access="hasAnyAuthority('Admin' , 'Memeber')">
				<li class="nav-item"><a
					href="${pageContext.request.contextPath}/member"
					class="nav-link ${title eq 'Member' ? 'active' : ''}"> <i
						class="bi-person"></i> Member
				</a></li>
			</security:authorize>
		</ul>

	</div>

</nav>

</body>
</html>
