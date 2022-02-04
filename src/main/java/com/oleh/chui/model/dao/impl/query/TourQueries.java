package com.oleh.chui.model.dao.impl.query;

public class TourQueries {

    private TourQueries() {}

    public static final String CREATE =
            "INSERT INTO tours" +
            " (name, price, city_id, description, max_discount, discount_step, tour_type_id, hotel_type_id," +
            " person_number, start_date, end_date, nights_number, is_burning)" +
            " VALUES(?, ?, (SELECT city_id FROM cities JOIN countries USING (country_id)" +
                            " WHERE city = ? AND country = ?)," +
            " ?, ?, ?, (SELECT tour_type_id FROM tour_types WHERE tour_type = ?)," +
            " (SELECT hotel_type_id FROM hotel_types WHERE hotel_type = ?), ?, ?, ?, ?, ?)";

    public static final String FIND_BY_ID =
            "SELECT * FROM tours" +
            " JOIN hotel_types USING (hotel_type_id)" +
            " JOIN tour_types USING (tour_type_id)" +
            " JOIN cities USING (city_id)" +
            " JOIN countries USING (country_id)" +
            " WHERE tour_id = ?";

    public static final String FIND_ALL =
            "SELECT * FROM tours" +
            " JOIN hotel_types USING (hotel_type_id)" +
            " JOIN tour_types USING (tour_type_id)" +
            " JOIN cities USING (city_id)" +
            " JOIN countries USING (country_id)";

    // TODO: THINK WHAT FIELDS HAVE TO BE CHANGED
    public static final String UPDATE =
            "UPDATE tours SET" +
            " name = ?," +
            " price = ?" +
            " WHERE tour_id = ?";

    public static final String DELETE = "DELETE FROM tours WHERE tour_id = ?";

}
