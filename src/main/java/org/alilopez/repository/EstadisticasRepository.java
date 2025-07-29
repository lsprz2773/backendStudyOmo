package org.alilopez.repository;
import java.time.LocalDate;
import java.util.Map;
import org.alilopez.config.DatabaseConfig;
import java.sql.*;
import java.util.HashMap;

public class EstadisticasRepository {

    public int contarPomodorosTerminados(int idUsuario) {
        String sql = "SELECT COUNT(*) FROM Pomodoro WHERE idUsuario = ? AND estado = 'completado'";
        try {
            Connection conn = DatabaseConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1);
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public int contarObjetivosAlcanzados(int idUsuario) {
        String sql = "SELECT COUNT(*) FROM Evidencia_Objetivo eo WHERE eo.idUsuario = ? AND eo.estado = 1";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int contarIntentosFallidos(int idUsuario) {
        String sql = "SELECT COUNT(*) FROM Evidencia_Tarea et JOIN Tarea t ON et.idTarea = t.idTarea WHERE et.idUsuario = ? AND et.estadoEntrega = 0";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int calcularTiempoTotalMinutos(int idUsuario) {
        String sql = "SELECT SUM(p.duracion) FROM Pomodoro p WHERE p.idUsuario = ? AND p.estado = 'completado'";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Map<LocalDate, Integer> obtenerTiempoPorDia(int idUsuario) {
        String sql = """
        SELECT DATE(p.fechaCreacion) AS fecha, SUM(p.duracionReal / 60) AS totalMinutos
        FROM Sesion p
        JOIN Sesion_Objetivo so ON p.idSesion = so.idSesion
        JOIN Objetivo o ON so.idObjetivo = o.idObjetivo
        WHERE o.idUsuario = ? AND p.estado = 'completado'
        AND p.fechaCreacion >= CURDATE() - INTERVAL 6 DAY
        GROUP BY fecha
    """;

        Map<LocalDate, Integer> resultado = new HashMap<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                int minutos = rs.getInt("totalMinutos");
                resultado.put(fecha, minutos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }
    public Map<LocalDate, Integer> obtenerPomodorosPorDia(int idUsuario) {
        String sql = """
        SELECT DATE(s.fechaCreacion) AS fecha, SUM(s.pomodoros) AS totalPomodoros
        FROM Sesion s
        JOIN Sesion_Objetivo so ON s.idSesion = so.idSesion
        JOIN Objetivo o ON so.idObjetivo = o.idObjetivo
        WHERE o.idUsuario = ? AND s.estado = 'completado'
        AND s.fechaCreacion >= CURDATE() - INTERVAL 6 DAY
        GROUP BY fecha
    """;

        Map<LocalDate, Integer> resultado = new HashMap<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                int pomodoros = rs.getInt("totalPomodoros");
                resultado.put(fecha, pomodoros);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public Map<LocalDate, Integer> obtenerObjetivosPorDia(int idUsuario) {
        String sql = """
        SELECT DATE(fechaEnvio) AS fecha, COUNT(*) AS total
        FROM Evidencia_Objetivo
        WHERE idUsuario = ? 
        AND fechaEnvio >= CURDATE() - INTERVAL 6 DAY
        GROUP BY fecha
    """;

        Map<LocalDate, Integer> resultado = new HashMap<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                int total = rs.getInt("total");
                resultado.put(fecha, total);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public Map<LocalDate, Integer> obtenerFallosPorDia(int idUsuario) {
        String sql = """
            SELECT DATE(fechaEnvio) AS fecha, COUNT(*) AS total
            FROM Evidencia_Tarea
            WHERE idUsuario = ? 
            AND estadoEntrega = 'no_entregada'
            AND fechaEnvio >= CURDATE() - INTERVAL 6 DAY
            GROUP BY fecha
            """;
        Map<LocalDate, Integer> resultado = new HashMap<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                int total = rs.getInt("total");
                resultado.put(fecha, total);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }


}