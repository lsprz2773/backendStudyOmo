package org.alilopez.controller;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.alilopez.model.Sesion;
import org.alilopez.service.SesionService;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class SesionController {
    private final SesionService sesionService;

    public SesionController(SesionService sesionService) {
        this.sesionService = sesionService;
    }

    public void getAll(Context ctx) {
        try {
            List<Sesion> sesions = sesionService.getAll();
            ctx.json(sesions);
        } catch (SQLException e) {
            e.printStackTrace();
            ctx.status(500).result("Error al obtener sesiones");
        }
    }

    public void getById(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Sesion sesion = sesionService.getByIdSesion(id);
            if (sesion != null) {
                ctx.json(sesion);
            } else {

                ctx.status(HttpStatus.NOT_FOUND).result("Sesion no encontrada");
            }
        } catch (Exception e) {
            ctx.status(404).result("Error al obtener sesion");
        }
    }

    public void create(Context ctx) {
        try {
            Sesion sesion = ctx.bodyAsClass(Sesion.class);
            if (sesion.getFechaCreacion() == null) {
                sesion.setFechaCreacion(LocalDateTime.now());
            }
            sesionService.createSesion(sesion);
            ctx.status(201).result("Sesion creada");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(400).result("Error al crear sesion");
        }
    }

    public void update(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Sesion sesion = ctx.bodyAsClass(Sesion.class);
            if (sesion.getFechaCreacion() == null) {
                sesion.setFechaCreacion(LocalDateTime.now());
            }
            sesion.setIdSesion(id); // Asegúrate de que el ID en la URL y en el objeto coincidan
            boolean updated = sesionService.updateSesion(sesion);
            if (updated) {
                ctx.status(HttpStatus.OK).result("Sesion actualizada");
            } else {
                ctx.status(HttpStatus.NOT_FOUND).result("Sesion no encontrada");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(HttpStatus.BAD_REQUEST).result("Error al actualizar sesion");
        }
    }

    public void delete(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            boolean deleted = sesionService.deleteSesion(id);
            if (deleted) {
                ctx.status(HttpStatus.OK).result("Sesion eliminado");
            } else {
                ctx.status(HttpStatus.NOT_FOUND).result("Sesion no encontrada");
            }
        } catch (Exception e) {
            ctx.status(HttpStatus.BAD_REQUEST).result("Error al eliminar sesion");
        }
    }
}
