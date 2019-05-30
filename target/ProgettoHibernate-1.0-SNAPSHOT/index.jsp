<%@ page import="it.alexandria.hibernate.model.Risorsa"%>
<%@ page import="it.alexandria.hibernate.model.Carrello"%>
<%@ page import="java.util.*"%>
<html lang="Italiano">
<%
	List<Risorsa> risorse=(List<Risorsa>)request.getSession().getAttribute("risorseRicercate");
	Carrello carrello=(Carrello) request.getSession().getAttribute("carrello");
%>

<head>
    <title>AleXandria</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="AleXandria">
    <link rel="stylesheet" type="text/css" href="styles/bootstrap4/bootstrap.min.css">
    <link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="styles/main_styles.css">
    <link rel="stylesheet" type="text/css" href="styles/responsive.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="images/AleXandria_Logo.png">
</head>

<body>

    <div class="super_container " style="overflow:scroll;">

        <!-- Header -->

        <header class="header trans_300 ">

            <!-- Top Navigation -->

            <div class="top_nav ">
                <div class="container ">
                    <div class="row ">
                        <div class="col-md-6 text-right ">
                            <div class="top_nav_right ">
                                <ul class="top_nav_menu ">

                                    <!-- Language -->

                                    <li class="language ">
                                        <a href="# ">
											Italiano
											<i class="fa fa-angle-down "></i>
										</a>
                                        <ul class="language_selection ">
                                            <li><a href="# ">English</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Main Navigation -->

            <div class="main_nav_container ">
                <div class="container ">
                    <div class="row ">
                        <div class="col-lg-12 text-right ">
                            <nav class="navbar ">
                                <ul class="navbar_menu ">
                                    <li><a href="search">home</a></li>
                                    <li><a href="library">libreria</a></li>
                                </ul>
                                <ul class="navbar_user ">
                                    <li><a href="boxmessages"><i class="fa fa-envelope " aria-hidden="true "></i></a></li>
                                    <li><a href="profile"><i class="fa fa-user " aria-hidden="true "></i></a></li>
                                    <li class="checkout ">
                                        <a href="cart.jsp">
                                            <i class="fa fa-shopping-cart " aria-hidden="true "></i>
                                            <span id="checkout_items " class="checkout_items "><%=carrello.getRisorseSelezionate().size()%></span>
                                        </a>
                                    </li>
                                </ul>
                                <div class="hamburger_container ">
                                    <i class="fa fa-bars " aria-hidden="true "></i>
                                </div>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>

        </header>

        <div class="fs_menu_overlay "></div>

        <!-- Hamburger Menu -->

        <div class="hamburger_menu ">
            <div class="hamburger_close "><i class="fa fa-times " aria-hidden="true "></i></div>
            <div class="hamburger_menu_content text-right ">
                <ul class="menu_top_nav ">
                    <li class="menu_item has-children ">
                        <a href="# ">
							Italiano
							<i class="fa fa-angle-down "></i>
						</a>
                        <ul class="menu_selection ">
                            <li><a href="# ">English</a></li>
                        </ul>
                    </li>
                    <li class="menu_item "><a href="index.html ">home</a></li>
                    <li class="menu_item "><a href="library.html ">libreria</a></li>
                </ul>
            </div>
        </div>

        <!-- Best Of -->
        <div class="best_of " style="overflow:scroll;height:90vh;width:100vw;">
            <div class="container ">
                <div class="row align-items-center ">
                    <div class="col text-center ">
                        <div class="best_of_sorting ">
                            <ul class="arrivals_grid_sorting clearfix button-group filters-button-group ">
                                <li class="grid_sorting_button button d-flex flex-column justify-content-center align-items-center active is-checked " data-filter="* ">Tutto</li>
                                <li class="grid_sorting_button button d-flex flex-column justify-content-center align-items-center " data-filter=".libri ">Libri</li>
                                <li class="grid_sorting_button button d-flex flex-column justify-content-center align-items-center " data-filter=".appunti ">Appunti</li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="row ">
                    <div class="col ">
                        <div class="search-container ">
                            <form action="/search " method="get">
                                <input class="search expandright " id="searchright " type="search " name="q " placeholder="Search ">
                                <label class="button1 searchbutton " for="searchright "><span class="mglass ">&#9906;</span></label>
                            </form>
                        </div>
                        <div class="product-grid " data-isotope='{ "itemSelector ": ".product-item ", "layoutMode ": "fitRows " }'>

                            <!-- Product 1 -->
							<% for(Risorsa r : risorse) { %>
                            <div class="product-item appunti" style="height:30vh;">
                                <div class="product discount product_filter " style="height:100%;width:100%;">
                                    <div class="product_image " style="height:80%;width:auto;">
                                        <img src=<%="images//"+r.getUrl()%> alt="">
                                    </div>
                                    <div class="product_info " style="height:20%;width:auto;">
										<h6 class="product_name" style="margin:0;"><a onclick="$('#mostra_risorsa<%=r.getId()%>').submit()"><%=r.getTitolo()%></a></h6>
										<div class="product_price ">&euro;<%=r.getPrezzo()%></div>
                                    </div>
                                    <div class="red_button add_to_cart_button ">
                                    <a onclick="$('#aggiungi_al_carrello<%=r.getId()%>').submit()">Aggiungi al Carrello</a></div>
                                    <form method="get" action="profile" id="aggiungi_al_carrello<%=r.getId()%>">
										<input type="hidden" value="aggiungi" name="type">
										<input type="hidden" name="id" value="<%=r.getId()%>">
										<input type="hidden" id="quantity" name="quantity" value="1">
									</form>
                                </div>
                            </div>
								<form  method="get" action="resource" id="mostra_risorsa<%=r.getId()%>">
									<input type="hidden" name="type" value="mostra">
									<input type="hidden" name="id" value="<%=r.getId()%>">
								</form>
                            <% } %>

                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <footer class="footer ">
            <div class="container ">
                <div class="row ">
                    <div class="col-lg-6 ">
                        <div class="footer_nav_container d-flex flex-sm-row flex-column align-items-center justify-content-lg-start justify-content-center text-center ">
                            <ul class="footer_nav ">
                                <li><a href="contact.html ">Contattaci</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-6 ">
                        <div class="footer_social d-flex flex-row align-items-center justify-content-lg-end justify-content-center ">
                            <ul>
                                <li><a href="# "><i class="fa fa-facebook " aria-hidden="true "></i></a></li>
                                <li><a href="# "><i class="fa fa-twitter " aria-hidden="true "></i></a></li>
                                <li><a href="https://www.instagram.com/_.alex_andria._ "><i class="fa fa-instagram "
											aria-hidden="true "></i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="row ">
                    <div class="col-lg-12 ">
                        <div class="footer_nav_container ">
                            <div class="cr ">©2019 All Rights Reserverd.</div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
    </div>

    <script src="js/jquery-3.2.1.min.js "></script>
    <script src="styles/bootstrap4/popper.js "></script>
    <script src="styles/bootstrap4/bootstrap.min.js "></script>
    <script src="plugins/Isotope/isotope.pkgd.min.js "></script>
    <script src="js/custom.js "></script>
</body>

</html>