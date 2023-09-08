package com.repository;

import com.entity.Plane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaneRepository {
    private final String INSERT_QUERY = "INSERT INTO plane (mark, model, type, mileage, price, id_profile) " +
            "VALUES (?, ?, ?, ?, ?, ?);";
    private final String SELECT_BY_ID_QUERY = "SELECT id, mark, model, type, mileage, price, id_profile " +
            "FROM plane WHERE id = ?;";
    private final String SELECT_BY_ID_PROFILE_QUERY = "SELECT id, mark, model, type, mileage, price, id_profile " +
            "FROM plane WHERE id_profile = ?;";
    private final String UPDATE_QUERY = "UPDATE plane " +
            "SET mark = ?, model = ?, type = ?, mileage = ?, price = ?, id_profile = ? WHERE id = ?";
    private final String DELETE_QUERY = "DELETE FROM plane WHERE id = ?;";

    public void insert(Plane plane) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
            setStatementFromPlane(statement, plane);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Plane getById(long id) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return getPlaneFromResultSet(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Plane> getByIdProfile(long idProfile) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            List<Plane> planes = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_PROFILE_QUERY);
            statement.setLong(1, idProfile);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                planes.add(getPlaneFromResultSet(resultSet));
            }
            return planes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Plane plane) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
            setStatementFromPlane(statement, plane);
            statement.setLong(7, plane.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(long id) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setStatementFromPlane(PreparedStatement statement, Plane plane) throws SQLException {
        statement.setString(1, plane.getMark());
        statement.setString(2, plane.getModel());
        statement.setString(3, plane.getType());
        statement.setInt(4, plane.getMileage());
        statement.setInt(5, plane.getPrice());
        statement.setLong(6, plane.getIdProfile());
    }

    private Plane getPlaneFromResultSet(ResultSet resultSet) throws SQLException {
        return Plane.builder()
                .id(resultSet.getLong("id"))
                .mark(resultSet.getString("mark"))
                .model(resultSet.getString("model"))
                .type(resultSet.getString("type"))
                .mileage(resultSet.getInt("mileage"))
                .price(resultSet.getInt("price"))
                .idProfile(resultSet.getLong("id_profile"))
                .build();
    }
}
