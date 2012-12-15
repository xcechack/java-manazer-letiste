<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Airportmanager REST client</title>
        <script type="text/javascript" src="js/jquery.js"></script> <!-- EXTERNAL -->
        <script type="text/javascript" src="js/jquery.cookie.js"></script> <!-- EXTERNAL -->
        <script type="text/javascript" src="js/jquery.json.js"></script> <!-- EXTERNAL -->
        <script type="text/javascript" src="js/ajax.js"></script> <!-- INTERNAL -->
        <script type="text/javascript" src="js/prettify.js"></script> <!-- EXTERNAL - PRETTIFY -->
        <script type="text/javascript" src="js/kickstart.js"></script> <!-- EXTERNAL - KICKSTART -->
        <link rel="stylesheet" type="text/css" href="css/kickstart.css" media="all" /> <!-- EXTERNAL - KICKSTART -->
        <link rel="stylesheet" type="text/css" href="css/custom.css" media="all" /> <!-- EXTERNAL - KICKSTART -->
    </head>
    <body>
        <a id="top-of-page"></a>
        <div id="wrap" class="clearfix">
            <div class="col_12">
                <h1>Airportmanager REST client</h1>
                <ul class="tabs left">
                    <li><a href="#tabr1">Planes</a></li>
                    <li><a href="#tabr2">Destinations</a></li>
                    <li><a href="#tabr3">Settings</a></li>
                </ul>

                
                <div id="tabr1" class="tab-content"> <!-- PLANES -->
                    <ul class="tabs left">
                        <li><a href="#tabr1_1">All planes</a></li>
                        <li><a href="#tabr1_2">Plane by ID</a></li>
                        <li><a href="#tabr1_3">Planes by Producer</a></li>
                        <li><a href="#tabr1_4">Planes by Type</a></li>
                        <li><a href="#tabr1_5">Planes by Number of seats</a></li>
                        <li><a href="#tabr1_6">Planes by minimal number of seats</a></li>
                    </ul>
                    
                    <div id="tabr1_1" class="tab-content">
                        <h3>All planes</h3>
                        <button class="medium green" id="load_all_planes">
                            <span class="icon medium" data-icon="]" style="display: inline-block;"></span>
                            Load
                        </button>
                        <div class="notice error hide" id="all_planes_error"></div>
                        <div id="all_planes_table_placeholder">
                            
                        </div>
                    </div>
                    
                    <div id="tabr1_2" class="tab-content">
                        <h3>Plane by ID</h3>
                        <form method="post" action="#" id="plane_by_id_form">
                            <label for="plane_by_id_input">ID: </label>
                            <input type="text" id="plane_by_id_input" name="plane_by_id_input" value="" placeholder="1"/>
                            <button class="small green">
                                <span class="icon small" data-icon="]" style="display: inline-block;"></span>
                                Load
                            </button>
                        </form>
                        <div class="notice error hide" id="plane_by_id_error"></div>
                        <div id="planes_by_id_table_placeholder">

                        </div>
                    </div>
                    
                    <div id="tabr1_3" class="tab-content">
                        <h3>Planes by Producer</h3>
                        <form method="post" action="#" class="planes_forms">
                            <label for="plane_by_producer_input">Producer: </label>
                            <input type="text" id="plane_by_producer_input" name="plane_by_producer_input" value="" placeholder="Airbus"/>
                            <button class="small green">
                                <span class="icon small" data-icon="]" style="display: inline-block;"></span>
                                Load
                            </button>
                        </form>
                        <div class="notice error hide" id="plane_by_producer_error"></div>
                        <div id="planes_by_producer_table_placeholder">

                        </div>
                    </div>
                    
                    <div id="tabr1_4" class="tab-content">
                        <h3>Planes by Type</h3>
                        <form method="post" action="#" class="planes_forms">
                            <label for="plane_by_type_input">Type: </label>
                            <input type="text" id="plane_by_type_input" name="plane_by_type_input" value="" placeholder="A320"/>
                            <button class="small green">
                                <span class="icon small" data-icon="]" style="display: inline-block;"></span>
                                Load
                            </button>
                        </form>
                        <div class="notice error hide" id="plane_by_type_error"></div>
                        <div id="planes_by_type_table_placeholder">

                        </div>
                    </div>
                    
                    <div id="tabr1_5" class="tab-content">
                        <h3>Planes by Number of seats</h3>
                        <form method="post" action="#" class="planes_forms">
                            <label for="plane_by_seats_input">Seats: </label>
                            <input type="text" id="plane_by_seats_input" name="plane_by_seats_input" value="" placeholder="78"/>
                            <button class="small green">
                                <span class="icon small" data-icon="]" style="display: inline-block;"></span>
                                Load
                            </button>
                        </form>
                        <div class="notice error hide" id="plane_by_seats_error"></div>
                        <div id="planes_by_seats_table_placeholder">

                        </div>
                    </div>
                    <div id="tabr1_6" class="tab-content">
                        <h3>Planes by Minimal Number of seats</h3>
                        <form method="post" action="#" class="planes_forms">
                            <label for="plane_by_min_seats_input">Seats: </label>
                            <input type="text" id="plane_by_min_seats_input" name="plane_by_min_seats_input" value="" placeholder="78"/>
                            <button class="small green">
                                <span class="icon small" data-icon="]" style="display: inline-block;"></span>
                                Load
                            </button>
                        </form>
                        <div class="notice error hide" id="plane_by_min_seats_error"></div>
                        <div id="planes_by_min_seats_table_placeholder">

                        </div>
                    </div>
                </div> <!-- /PLANES -->
                
                <div id="tabr2" class="tab-content"><!-- DESTINATIONS -->
                     <ul class="tabs left">
                        <li><a href="#tabr2_1">All destinations</a></li>
                        <li><a href="#tabr2_2">Destination by ID</a></li>
                        <li><a href="#tabr2_3">Destination by City</a></li>
                        <li><a href="#tabr2_4">Destination by Country</a></li>
                        <li><a href="#tabr2_7">Add new</a></li>
                    </ul>
                    
                    <div id="tabr2_1" class="tab-content">
                        <h3>All destinations</h3>
                        <button class="medium green" id="load_all_destinations">
                            <span class="icon medium" data-icon="]" style="display: inline-block;"></span>
                            Load
                        </button>
                        <div class="notice error hide" id="all_destinations_error"></div>
                        <div id="all_destinations_table_placeholder">
                            
                        </div>
                    </div>
                    
                    <div id="tabr2_2" class="tab-content">
                        <h3>Destination by ID</h3>
                        <form method="post" action="#" id="destination_by_id_form">
                            <label for="destination_by_id_input">ID: </label>
                            <input type="text" id="destination_by_id_input" name="destination_by_id_input" value="" placeholder="1" required/>
                            <button class="small green">
                                <span class="icon small" data-icon="]" style="display: inline-block;"></span>
                                Load
                            </button>
                        </form>
                        <div class="notice error hide" id="destination_by_id_error"></div>
                        <div id="destination_by_id_table_placeholder">
                            
                        </div>
                    </div>
                    <div id="tabr2_3" class="tab-content">
                        <h3>Destination by City</h3>
                        <form method="post" action="#" id="destination_by_city_form">
                            <label for="destination_by_city_input">ID: </label>
                            <input type="text" id="destination_by_city_input" name="destination_by_city_input" value="" placeholder="Prague" required/>
                            <button class="small green">
                                <span class="icon small" data-icon="]" style="display: inline-block;"></span>
                                Load
                            </button>
                        </form>
                        <div class="notice error hide" id="destination_by_city_error"></div>
                        <div id="destination_by_city_placeholder">
                            
                        </div>
                    </div>
                    <div id="tabr2_4" class="tab-content">
                        <h3>Destination by Country</h3>
                        <form method="post" action="#" id="destination_by_country_form">
                            <label for="destination_by_country_input">ID: </label>
                            <input type="text" id="destination_by_country_input" name="destination_by_country_input" value="" placeholder="Czech republic" required/>
                            <button class="small green">
                                <span class="icon small" data-icon="]" style="display: inline-block;"></span>
                                Load
                            </button>
                        </form>
                        <div class="notice error hide" id="destination_by_country_error"></div>
                        <div id="destination_by_country_placeholder">
                            
                        </div>
                    </div>
                    <div id="tabr2_7" class="tab-content">
                        <h3>Add new destination</h3>
                        <form method="post" action="#" id="destination_add_new_form">
                            <label for="destination_add_new_city_input">City: </label>
                            <input type="text" id="destination_add_new_city_input" name="destination_add_new_city_input" value="" placeholder="Prague" required/>
                            <label for="destination_add_new_country_input">Country: </label>
                            <input type="text" id="destination_add_new_country_input" name="destination_add_new_country_input" value="" placeholder="Czech republic" required/>
                            <button class="small green">
                                <span class="icon small" data-icon="]" style="display: inline-block;"></span>
                                Save
                            </button>
                        </form>
                        <div class="notice error hide" id="destination_add_new_error"></div>
                    </div>
                </div><!-- /DESTINATIONS -->
                
                <div id="tabr3" class="tab-content">
                    <form action="#" method="post">
                        <label for="settings_url">Server url: </label>
                        <input type="text" id="settings_url" name="settings_url" value="" style="width:300px" required/><br/><br/>
                        <button class="small green" type="button" id="settings_save">Save</button>
                    </form>
                    
                </div>
            </div>
        </div>
    </body>
</html>
