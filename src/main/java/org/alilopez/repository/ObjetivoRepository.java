package org.alilopez.repository;

import org.alilopez.config.DatabaseConfig;
import org.alilopez.dto.UltimoObjetivoDTO;
import org.alilopez.model.Objetivo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ObjetivoRepository {

    // Obtener todos los objetivos de un usuario, con los datos de sesión
    public List<Objetivo> findObjetivos() throws SQLException {
        List<Objetivo> objetivos = new ArrayList<>();
        String query = "SELECT * FROM objetivo";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Objetivo o = new Objetivo();
                o.setIdObjetivo(rs.getInt("idObjetivo"));
                o.setIdUsuario(rs.getInt("idUsuario"));
                o.setNombre(rs.getString("nombre"));
                o.setDescripcion(rs.getString("descripcion"));
                o.setTotalPomodoros(rs.getInt("totalPomodoros"));
                o.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
                o.setDuracionPomodoro(rs.getFloat("duracionPomodoro"));
                o.setDuracionDescanso(rs.getFloat("duracionDescanso"));

                objetivos.add(o);
            }
        }
        return objetivos;
    }

    // Obtener un objetivo específico por su ID
    public Objetivo findByIdObjetivo(int idObjetivo) throws SQLException {
        Objetivo objetivo = null;
        String query = "SELECT * FROM objetivo WHERE idObjetivo = ?";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idObjetivo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Objetivo o = new Objetivo();
                    o.setIdObjetivo(rs.getInt("idObjetivo"));
                    o.setIdUsuario(rs.getInt("idUsuario"));
                    o.setNombre(rs.getString("nombre"));
                    o.setDescripcion(rs.getString("descripcion"));
                    o.setTotalPomodoros(rs.getInt("totalPomodoros"));
                    o.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
                    o.setDuracionPomodoro(rs.getFloat("duracionPomodoro"));
                    o.setDuracionDescanso(rs.getFloat("duracionDescanso"));

                    return o;
                }
            }
        }
        return objetivo;
    }

    // Guardar un nuevo objetivo (sin sesión, solo objetivo)
    public int save(Objetivo objetivo) throws SQLException {
        String query = "INSERT INTO objetivo (idUsuario, nombre, descripcion, totalPomodoros, duracionPomodoro, duracionDescanso) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, objetivo.getIdUsuario());
            stmt.setString(2, objetivo.getNombre());
            stmt.setString(3, objetivo.getDescripcion());
            stmt.setInt(4, objetivo.getTotalPomodoros());
            stmt.setFloat(5, objetivo.getDuracionPomodoro());
            stmt.setFloat(6, objetivo.getDuracionDescanso());
            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                } else {
                    throw new SQLException("No se pudo obtener el ID del objetivo insertado.");
                }
            }
        }
    }

    // Actualizar un objetivo existente
    public boolean update(Objetivo objetivo) throws SQLException {
        String query = "UPDATE objetivo SET nombre = ?, descripcion = ? WHERE idObjetivo = ?";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, objetivo.getNombre());
            stmt.setString(2, objetivo.getDescripcion());
            stmt.setInt(3, objetivo.getIdObjetivo());
            return stmt.executeUpdate() > 0;
        }
    }

    // Eliminar un objetivo
    public boolean delete(int idObjetivo) throws SQLException {
        String query = "DELETE FROM objetivo WHERE idObjetivo = ?";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idObjetivo);
            return stmt.executeUpdate() > 0;
        }
    }

    // Obtener los objetivos de un usuario específico
    public List<Objetivo> findObjetivosByIdUser(int idUsuario) throws SQLException {
        List<Objetivo> objetivos = new ArrayList<>();
        String query = "SELECT * FROM objetivo WHERE idUsuario = ?";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idUsuario);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Objetivo o = new Objetivo();
                    o.setIdObjetivo(rs.getInt("idObjetivo"));
                    o.setIdUsuario(rs.getInt("idUsuario"));
                    o.setNombre(rs.getString("nombre"));
                    o.setDescripcion(rs.getString("descripcion"));
                    o.setTotalPomodoros(rs.getInt("totalPomodoros"));
                    o.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
                    o.setDuracionPomodoro(rs.getFloat("duracionPomodoro"));
                    o.setDuracionDescanso(rs.getFloat("duracionDescanso"));

                    objetivos.add(o);
                }
            }
            return objetivos;
        }
    }

    // Obtener los objetivos entregados (con evidencia)
    public List<Objetivo> findObjetivosEntregados(int idUsuario) throws SQLException {
        List<Objetivo> objetivos = new ArrayList<>();
        String query = "SELECT o.*" +
                "FROM objetivo o " +
                "JOIN evidencia_objetivo eo ON o.idObjetivo = eo.idObjetivo " +
                "JOIN sesion_objetivo so ON o.idObjetivo = so.idObjetivo " +
                "JOIN sesion s ON so.idSesion = s.idSesion " +
                "WHERE eo.idUsuario = ? AND eo.estado = 1";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Objetivo o = new Objetivo();
                    o.setIdObjetivo(rs.getInt("idObjetivo"));
                    o.setIdUsuario(rs.getInt("idUsuario"));
                    o.setNombre(rs.getString("nombre"));
                    o.setDescripcion(rs.getString("descripcion"));
                    o.setTotalPomodoros(rs.getInt("totalPomodoros"));
                    o.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
                    o.setDuracionPomodoro(rs.getFloat("duracionPomodoro"));
                    o.setDuracionDescanso(rs.getFloat("duracionDescanso"));

                    objetivos.add(o);
                }
            }
        }
        return objetivos;
    }

    // Obtener los objetivos no entregados
    public List<Objetivo> findObjetivosNoEntregados(int idUsuario) throws SQLException {
        List<Objetivo> objetivos = new ArrayList<>();
        String query = "SELECT o.*" +
                "FROM objetivo o " +
                "LEFT JOIN evidencia_objetivo eo ON o.idObjetivo = eo.idObjetivo AND eo.idUsuario = ? " +
                "JOIN sesion_objetivo so ON o.idObjetivo = so.idObjetivo " +
                "JOIN sesion s ON so.idSesion = s.idSesion " +
                "WHERE o.idUsuario = ? AND eo.idObjetivo IS NULL";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idUsuario);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Objetivo o = new Objetivo();
                    o.setIdObjetivo(rs.getInt("idObjetivo"));
                    o.setIdUsuario(rs.getInt("idUsuario"));
                    o.setNombre(rs.getString("nombre"));
                    o.setDescripcion(rs.getString("descripcion"));
                    o.setTotalPomodoros(rs.getInt("totalPomodoros"));
                    o.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
                    o.setDuracionPomodoro(rs.getFloat("duracionPomodoro"));
                    o.setDuracionDescanso(rs.getFloat("duracionDescanso"));

                    objetivos.add(o);
                }
            }
        }
        return objetivos;
    }

    // Obtener el último objetivo creado por el usuario
    public Objetivo findUltimoObjetivoByUsuario(int idUsuario) throws SQLException {
        String query = "SELECT * FROM objetivo WHERE idUsuario = ? ORDER BY idObjetivo DESC LIMIT 1";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Objetivo o = new Objetivo();
                o.setIdObjetivo(rs.getInt("idObjetivo"));
                o.setIdUsuario(rs.getInt("idUsuario"));
                o.setNombre(rs.getString("nombre"));
                o.setDescripcion(rs.getString("descripcion"));
                o.setTotalPomodoros(rs.getInt("totalPomodoros"));
                o.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
                o.setDuracionPomodoro(rs.getFloat("duracionPomodoro"));
                o.setDuracionDescanso(rs.getFloat("duracionDescanso"));


                return o;
            }
        }
        return null;
    }
}
