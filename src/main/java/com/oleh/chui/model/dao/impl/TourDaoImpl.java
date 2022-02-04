package com.oleh.chui.model.dao.impl;

import com.oleh.chui.model.dao.TourDao;
import com.oleh.chui.model.dao.impl.query.TourQueries;
import com.oleh.chui.model.dao.mapper.TourMapper;
import com.oleh.chui.model.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TourDaoImpl implements TourDao {

    private final TourMapper tourMapper = new TourMapper();
    private final Logger logger = LogManager.getLogger(TourDaoImpl.class);

    @Override
    public void create(Tour entity) {
        Connection connection = ConnectionPoolHolder.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(TourQueries.CREATE)) {
            statement.setString(1, entity.getName());
            statement.setBigDecimal(2, entity.getPrice());
            statement.setString(3, entity.getCity().getCity());
            statement.setString(4, entity.getCity().getCountry().getCountry());
            statement.setString(5, entity.getDescription());
            statement.setInt(6, entity.getMaxDiscount());
            statement.setDouble(7, entity.getDiscountStep());
            statement.setString(8, entity.getTourType().getValue().name());
            statement.setString(9, entity.getHotelType().getValue().name());
            statement.setInt(10, entity.getPersonNumber());
            statement.setDate(11, Date.valueOf(entity.getStartDate()));
            statement.setDate(12, Date.valueOf(entity.getEndDate()));
            statement.setInt(13, entity.getNightsNumber());
            statement.setBoolean(14, entity.isBurning());

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("{}, when trying to create new Tour", e.getMessage());
            throw new RuntimeException(e);
        } finally {
            ConnectionPoolHolder.closeConnection(connection);
        }
    }

    @Override
    public Optional<Tour> findById(long id) {
        Connection connection = ConnectionPoolHolder.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(TourQueries.FIND_BY_ID)) {
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(tourMapper.extractFromResultSet(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("{}, when trying to find Tour by ID={}", e.getMessage(), id);
            throw new RuntimeException(e);
        } finally {
            ConnectionPoolHolder.closeConnection(connection);
        }
    }

    @Override
    public List<Tour> findAll() {
        Connection connection = ConnectionPoolHolder.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(TourQueries.FIND_ALL)) {
            List<Tour> tourList = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Tour tour = tourMapper.extractFromResultSet(resultSet);
                tourList.add(tour);
            }

            return tourList;
        } catch (SQLException e) {
            logger.error("{}, when trying to find all Tours", e.getMessage());
            throw new RuntimeException(e);
        } finally {
            ConnectionPoolHolder.closeConnection(connection);
        }
    }

    @Override
    public void update(Tour entity) {
        Connection connection = ConnectionPoolHolder.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(TourQueries.UPDATE)) {
            statement.setString(1, entity.getName());
            statement.setBigDecimal(2, entity.getPrice());
            statement.setLong(3, entity.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("{}, when trying to update Tour" , e.getMessage());
            throw new RuntimeException(e);
        } finally {
            ConnectionPoolHolder.closeConnection(connection);
        }
    }

    @Override
    public void delete(long id) {
        Connection connection = ConnectionPoolHolder.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(TourQueries.DELETE)) {
            statement.setLong(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("{}, when trying to delete Tour by ID={}", e.getMessage(), id);
            throw new RuntimeException(e);
        } finally {
            ConnectionPoolHolder.closeConnection(connection);
        }
    }

}
