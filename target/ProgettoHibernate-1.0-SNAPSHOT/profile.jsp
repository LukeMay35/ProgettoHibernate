<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="it.alexandria.hibernate.model.Categoria"%>
<%@ page import="it.alexandria.hibernate.model.UCMapping"%>
<%@ page import="it.alexandria.hibernate.model.Carrello"%>

<html lang="it">
		<%
		HttpSession sessione=request.getSession();
		Carrello carrello=(Carrello) request.getSession().getAttribute("carrello");
		String username = (String) sessione.getAttribute("username");
		String nome = (String) sessione.getAttribute("nome");
		String cognome = (String) sessione.getAttribute("cognome");
		Date data = (Date) sessione.getAttribute("data");
		String email = (String) sessione.getAttribute("email");
		String numeroTel = (String) sessione.getAttribute("numeroTel");
		String indirizzo = (String) sessione.getAttribute("indirizzo");
		List<UCMapping> interessi = (List<UCMapping>) sessione.getAttribute("interessi");
%>
<html lang="it">
<head>
<title>AleXandria-Profile</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="icon" href="images/AleXandria_Logo.png">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css"
	href="styles/bootstrap4/bootstrap.min.css">
<link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="styles/profile_styles.css">
<link rel="stylesheet" type="text/css" href="styles/responsive.css">
</head>

<body>

	<div class="super_container">

		<!-- Header -->

		<header class="header trans_300">

			<!-- Top Navigation -->

			<div class="top_nav">
				<div class="container">
					<div class="row">
						<div class="col-md-6 text-right">
							<div class="top_nav_right">
								<ul class="top_nav_menu">

									<!-- Language -->

									<li class="language"><a href="#"> Italiano <i class="fa fa-angle-down"></i></a>
										<ul class="language_selection">
											<li><a href="#">English</a></li>
										</ul></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- Main Navigation -->

			<div class="main_nav_container">
				<div class="container">
					<div class="row">
						<div class="col-lg-12 text-right">
							<div class="logo_container">
								<div class="logo_image">
									<img src="images/AleXandria_Logo.png" alt="AleXandria">
								</div>
								<div class="logo_name">
									<a href="index.jsp">AleX<span>andria</span></a>
								</div>
							</div>
							<nav class="navbar">
								<ul class="navbar_menu">
									<li><a href="search">home</a></li>
									<li><a href="library">libreria</a></li>
								</ul>
								<ul class="navbar_user">
									<li><a href="boxmessages"><i class="fa fa-envelope"
											aria-hidden="true"></i></a></li>
									<li><a href="profile"><i class="fa fa-user"
											aria-hidden="true"></i></a></li>
									<li class="checkout"><a href="cart.jsp"> <i class="fa fa-shopping-cart" aria-hidden="true"></i> <span
											id="checkout_items" class="checkout_items"><%=carrello.getRisorseSelezionate().size() %></span>
									</a></li>
								</ul>
								<div class="hamburger_container">
									<i class="fa fa-bars" aria-hidden="true"></i>
								</div>
							</nav>
						</div>
					</div>
				</div>
			</div>

		</header>

		<div class="fs_menu_overlay"></div>

		<!-- Hamburger Menu -->

		<div class="hamburger_menu">
			<div class="hamburger_close">
				<i class="fa fa-times" aria-hidden="true"></i>
			</div>
			<div class="hamburger_menu_content text-right">
				<ul class="menu_top_nav">
					<li class="menu_item has-children"><a href="#"> Italiano <i
							class="fa fa-angle-down"></i>
					</a>
						<ul class="menu_selection">
							<li><a href="#">English</a></li>
						</ul></li>
					<li class="menu_item"><a href="index.jsp">home</a></li>
					<li class="menu_item"><a href="library">libreria</a></li>
				</ul>
			</div>
		</div>



		<!-- Profile -->

		<div class="profile_container">
			<div class="profile">
				<i class="fa fa-user" aria-hidden="true"></i>
				<h5 class="username"><%=username%></h5>
				<h6 class=" name_surname "><%=nome+" "+cognome%></h6>
				<%
				SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd");
			   Calendar rapr=Calendar.getInstance();
			   rapr.setTime(data);
			   String formatted=format.format(rapr.getTime());
			   %>
			   Data di Nascit&agrave;: <h6 class=" birthday "><%=formatted%></h6>
			   Email: <h6 class=" email "><%=email%></h6>
			   Tel: <h6 class=" tel "><%=numeroTel%></h6>
			   Indirizzo: <h6 class=" address "><%=indirizzo%></h6>
			   Interessi:<br>
			   <ul class="profile_interests">
				   <%
				   for(UCMapping c : interessi){
					   out.println("<li>"+c.getCategoria().toString()+"</li>");
				   }
				   %>
			   </ul>
				
			<div id="profile_error"></div>
			<div id="profile_ok"></div>
			
			</div>
			<div class="profile_modify">
				<b>Clicca per Modificare i dati Personali!</b><br>
				<button onclick="showModify()">
					<i class="fa fa-edit"></i>
				</button>
				<div id="modify" class="modify">
				 <br>
				<strong style="color: red">Lasciare vuoti i campi che non si vogliono modificare!</strong>
				<br><br>
					<form class="modify_form" name="modify_form" onsubmit="return modify()" action="loginsubmit" method="post">
							<input type="hidden" name="type" value="cambiaPassword">
                        	<input type="hidden" name="username" value=<%=username%>>
						Email: <br> <input type="email" name="email" maxlength="30"><br>
						Tel: <br> <input type="tel" name="tel" maxlength="20"><br>
					    Indirizzo: <br>
						<textarea name="address" rows="2" cols="30" maxlength="100"></textarea>
						<br>Scegli fra gli Interessi: <br>

						<div id="interests" class="interests" tabindex="100">
							<span class="anchor" onclick="showInterests()">Interessi</span>
							<ul class="items" id="items">
									<%
									for(Categoria c: Categoria.values()){
									  out.println("<li><input type=\"checkbox\" name=\""+c.toString()+"\" value=\""+c.toString()+"\"/> "+c.toString()+"</li>");
									}
									%>
							</ul>
						</div>
						<br> Password: <br> <input type="password"
							name="passwordVecchia" placeholder="Password" minlength="8"
							maxlength="20"><br> Nuova Password: <br> <input
							type="password" name="passwordNuova" placeholder="Password"
							minlength="8" maxlength="20"><br> Conferma nuova
						    Password: <br> <input type="password"
							name="confirm_new_password" placeholder="Password" minlength="8" maxlength="20"><br> 
							<div id="error"></div>
							<input id="modify_button"
							class="modify_button" type="submit" value="Modifica">
					</form>
				</div>
			</div>
		</div>
			<br>
			<br>
            <div class="logout">
			<form name="logout" action="loginsubmit" method="GET" >
				<input type="hidden" name="type" value="logout">			
				<button type="submit" class="logout_btn" style="background-color: transparent; border-style: none"><h4 style=" color: #fe4c40;">Esci <i class="fa fa-sign-out"></i></h4></button>
			</form>
			</div>	
			<br>
			<br>
	</div>
	<!-- Footer -->
	<footer class="footer zz">
		<div class="container">
			<div class="row">
				<div class="col-lg-6">
					<div
						class="footer_nav_container d-flex flex-sm-row flex-column align-items-center
                    justify-content-lg-start justify-content-center text-center">
						<ul class="footer_nav">
							<li><a href="contact.html">Contattaci</a></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-6">
					<div
						class="footer_social d-flex flex-row align-items-center justify-content-lg-end
                    justify-content-center">
						<ul>
							<li><a href="#"><i class=" fa fa-facebook"
									aria-hidden=" true "></i></a></li>
							<li><a href="#"><i class=" fa fa-twitter"
									aria-hidden=" true "></i></a></li>
							<li><a href="https://www.instagram.com/_.alex_andria._"><i
									class="fa fa-instagram" aria-hidden=" true "></i></a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="footer_nav_container">
						<div class="cr">©2019 All Rights Reserved.</div>
					</div>
				</div>
			</div>
		</div>
	</footer>
	<script src="js/profile.js"></script>
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="styles/bootstrap4/popper.js"></script>
	<script src="styles/bootstrap4/bootstrap.min.js"></script>
	<script src="js/contact_custom.js"></script>

</body>

</html>