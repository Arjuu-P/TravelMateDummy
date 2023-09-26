package com.dzniox.travel_mate.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.dzniox.travel_mate.network.APIConstants.APIs;
import static com.dzniox.travel_mate.network.APIConstants.Params;

public interface APIInterface {

    @GET(APIs.LOGIN)
    Call<String> loginUser(
            @Query(Params.EMAIL) String email,
            @Query(Params.PASSWORD) String password
    );


    @GET(APIConstants.APIs.REGISTER)
    Call<String> signUpUser(
            @Query(Params.EMAIL) String email,
            @Query(Params.PASSWORD) String password,
            @Query(Params.NAME) String name,
            @Query(Params.PHONE) String phone
    );

    @GET(APIs.GET_SPOT)
    Call<String> getSpot(
            @Query(Params.DISTRICT_ID) String district_id,
            @Query(Params.SPOT_ID) String spot_id
    );

    @GET(APIs.SEARCH_SPOT)
    Call<String> searchSpot(
            @Query(Params.KEY) String key
    );

    @GET(APIs.GET_WISHLIST)
    Call<String> getWishlist(
            @Query(Params.USER_ID) String user_id,
            @Query(Params.SPOT_ID) String spot_id
            );

    @GET(APIs.ADD_WISHLIST)
    Call<String> addWishlist(
            @Query(Params.USER_ID) String user_id,
            @Query(Params.SPOT_ID) String spot_id
    );

    @GET(APIs.REMOVE_WISHLIST)
    Call<String> removeWishlist(
            @Query(Params.WISHLIST_ID) String wishlist_id
    );

    @GET(APIs.GET_LIKED_SPOT)
    Call<String> getLikedSpot(
            @Query(Params.USER_ID) String user_id
    );

    @GET(APIs.GET_MY_BOOKINGS)
    Call<String> getMyBookings(
            @Query(Params.USER_ID) String user_id
    );
    @GET(APIs.GET_MY_EXPENSE)
    Call<String> getMyExpences(
            @Query(Params.USER_ID) String user_id
    );

    @GET(APIs.INSERT_MY_EXPENSE)
    Call<String> insertMyExpences(
            @Query(Params.USER_ID) String user_id,
            @Query(Params.NAME) String name,
            @Query(Params.AMOUNT) String amount,
            @Query(Params.DESCRIPTION) String description

            );

    @GET(APIs.ADD_SPOT)
    Call<String> addSpot(
            @Query(Params.NAME) String name,
            @Query(Params.LOCATION) String location,
            @Query(Params.LOCATION_DESC) String location_desc,
            @Query(Params.IMAGE) String image,
            @Query(Params.MAP_URL) String map_url,
            @Query(Params.PETROL) String petrol,
            @Query(Params.ATM) String atm,
            @Query(Params.DISTRICT_ID) String district_id,
            @Query(Params.SPOT_TYPE) String spot_type

            );

    @GET(APIs.GET_BUS)
    Call<String> getBus();

    @GET(APIs.GET_HOTEL)
    Call<String> getHotel();


    @GET(APIs.INSERT_BOOKING)
    Call<String> insertBooking(
            @Query(Params.HOTLE_BOOKING) String hotel_booking,
            @Query(Params.BUS_BOOKING) String bus_booking,
            @Query(Params.USER_ID) String user_id,
            @Query(Params.BOOKING_ID) String booking_id,
            @Query(Params.DAY) String day,
            @Query(Params.FROM_DATE) String from_date
    );

    @GET(APIs.GET_BUS_ID)
    Call<String> getBusId (
            @Query(Params.ID) String id
    );

    @GET(APIs.GET_HOTEL_ID)
    Call<String> getHotelId (
            @Query(Params.ID) String id
    );

    @GET(APIs.INSERT_HOTEL)
    Call<String> addHotel (
            @Query(Params.NAME) String name,
            @Query(Params.DESCRIPTION) String description,
            @Query(Params.IMAGE) String image,
            @Query(Params.MAP_URL) String map_link
            );

    @GET(APIs.INSERT_BUS)
    Call<String> addBus (
            @Query(Params.NAME) String name,
            @Query(Params.DESCRIPTION) String description,
            @Query(Params.IMAGE) String image
    );

    @GET(APIs.INSERT_EXPENCE)
    Call<String> addExpense (
            @Query(Params.USER_ID) String user_id,
            @Query(Params.NAME) String name,
            @Query(Params.AMOUNT) String amount,
            @Query(Params.DESCRIPTION) String description
    );

    @GET(APIs.DELETE_BOOKING)
    Call<String> deleteBooking (
            @Query(Params.ID) String id
    );

    @GET(APIs.DELETE_REVIEW)
    Call<String> deleteReview(
            @Query(Params.ID) String id
    );

    @GET(APIs.INSERT_REVIEW)
    Call<String> insertReview(
            @Query(Params.TITLE) String review,
            @Query(Params.USER_ID) String user_id
    );

    @GET(APIs.GET_REVIEW)
    Call<String> getReviews();
}

