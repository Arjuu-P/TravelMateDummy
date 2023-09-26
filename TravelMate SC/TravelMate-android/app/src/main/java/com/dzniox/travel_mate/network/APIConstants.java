package com.dzniox.travel_mate.network;

import retrofit2.http.Query;

public class APIConstants {


    private APIConstants() {

    }


    public static class URLs {
        public static final String MESSAGE_URL = "http://api.bulksmsgateway.in/sendmessage.php/";
        public static final String LIVE_URL = "http://amaldas.in/fab/travel_mate/";
        static final String BASE_URL = LIVE_URL;

        private URLs() {

        }
    }

    public static class ErrorCodes {
        public static final int NEED_TO_SUBSCRIBE_TO_MAKE_PAYMENT = 901;
        public static final int NO_DEFAULT_CARD_FOUND = 111;
        public static final int NOT_VERIFIED = 1001;
        static final int TOKEN_EXPIRED = 1003;
        static final int USER_DOESNT_EXIST = 1002;
        static final int USER_RECORD_DELETED_CONTACT_ADMIN = 3000;
        static final int INVALID_TOKEN = 1004;
        static final int USER_LOGIN_DECLINED = 1000;
        public static final int ID_TOKEN_REQUIRED = 1005;

        private ErrorCodes() {
        }

    }

    public static class APIs {
        public static final String LOGIN =  "login.php";
        public static final String REGISTER =  "register-user.php";
        public static final String GET_SPOT =  "get-spot.php";
        public static final String SEARCH_SPOT =  "search-spot.php";
        public static final String GET_WISHLIST =  "get-wishlist.php";
        public static final String ADD_WISHLIST =  "add-wishlist.php";
        public static final String REMOVE_WISHLIST =  "remove-wishlist.php";
        public static final String GET_LIKED_SPOT =  "get-liked-spot.php";
        public static final String ADD_SPOT =  "add-spot.php";
        public static final String GET_BUS =  "get-bus.php";
        public static final String GET_HOTEL =  "get-hotels.php";

        public static final String GET_MY_BOOKINGS =  "get-my-bookings.php";
        public static final String GET_MY_EXPENSE =  "get-expence.php";
        public static final String INSERT_MY_EXPENSE =  "insert-expence.php";

        public static final String INSERT_BOOKING =  "insert-booking.php";

        public static final String GET_BUS_ID =  "get-bus-id.php";
        public static final String GET_HOTEL_ID =  "get-hotel-id.php";

        public static final String INSERT_HOTEL =  "insert-hotel.php";
        public static final String INSERT_BUS =  "insert-bus.php";

        public static final String INSERT_EXPENCE =  "insert-expence.php";
        public static final String DELETE_BOOKING =  "delete-booking.php";


        public static final String INSERT_REVIEW =  "insert-review.php";
        public static final String GET_REVIEW =  "get-review.php";
        public static final String DELETE_REVIEW =  "delete-review.php";

        private APIs() {
        }
    }


    public static class Params {

        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        public static final String PHONE = "phone";
        public static final String AMOUNT = "amount";

        public static final String MOBILE = "mobile";
        public static final String MESSAGE = "message";
        public static final String SENDER = "sender";
        public static final String TYPE = "type";
        public static final String TEMPLATE_ID = "template_id";


        public static final String TITLE = "title";
        public static final String USER_ID = "user_id";
        public static final String SPOT_ID = "spot_id";
        public static final String WISHLIST_ID = "wishlist_id";

        public static final String DESCRIPTION = "description";


        public static final String ERROR_CODE = "error_code";
        public static final String ERROR_MSG = "error_message";

        public static final String DISTRICT_ID = "district_id";
        public static final String KEY = "key";

        public static final String LOCATION = "location_name";
        public static final String LOCATION_DESC = "location_desc";
        public static final String IMAGE = "image";
        public static final String MAP_URL = "map_link";
        public static final String PETROL = "pump_location";
        public static final String ATM = "atm_link";
        public static final String SPOT_TYPE = "spot_type";

        public static final String HOTLE_BOOKING = "hotel_booking";
        public static final String BUS_BOOKING = "bus_booking";
        public static final String BOOKING_ID = "booking_id";
        public static final String DAY = "day";
        public static final String FROM_DATE = "from_date";

        Params() {

        }
    }

}
