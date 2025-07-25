package org.alilopez.service;

import org.alilopez.dto.*;
import org.alilopez.model.Sesion;
import org.alilopez.model.Tarea;
import org.alilopez.model.UsuarioGrupo;
import org.alilopez.repository.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class TareaService {
    private final TareaRepository tareaRepository;
    private final SesionRepository sesionRepository;
    private final SesionTareaRepository sesionTareaRepository;
    private final SesionGrupoRepository sesionGrupoRepository;
    private final UsuarioGrupoRepository usuarioGrupoRepository;

    public TareaService(TareaRepository tareaRepository, SesionRepository sesionRepository, SesionTareaRepository sesionTareaRepository, SesionGrupoRepository sesionGrupoRepository, UsuarioGrupoRepository usuarioGrupoRepository) {
        this.tareaRepository = tareaRepository;
        this.sesionRepository = sesionRepository;
        this.sesionTareaRepository = sesionTareaRepository;
        this.sesionGrupoRepository = sesionGrupoRepository;
        this.usuarioGrupoRepository = usuarioGrupoRepository;
    }

    public List<Tarea> getAll() throws SQLException {
        return tareaRepository.findTareas();
    }

    public Tarea getByIdTarea(int id) throws SQLException {
        return tareaRepository.findByIdTarea(id);
    }

    public void createTarea(Tarea tarea) throws SQLException {
        tareaRepository.save(tarea);
    }

    public boolean updateTarea(Tarea tarea) throws SQLException {
        return tareaRepository.update(tarea);
    }

    public boolean deleteTarea(int id) throws SQLException {
        return tareaRepository.delete(id);
    }

    public List<Tarea> getTareasByIdGrupo(int idGrupo) throws SQLException {
        return tareaRepository.findTareasByIdGrupo(idGrupo);
    }


    public void crearTareaConSesion(CrearTareaRequest data) throws SQLException {
        // Crear tarea
        Tarea tarea = new Tarea();
        tarea.setTitulo(data.titulo);
        tarea.setDescripcion(data.descripcion);
        tarea.setRecursoURL(data.recursoURL);
        tarea.setFechaCreacion(LocalDateTime.now());
        tarea.setFechaEntrega(data.fechaEntrega);
        tarea.setIdGrupo(data.idGrupo);
        int idTarea = tareaRepository.save(tarea);

    }

    public List<UsuarioGrupo> getAlumnosPorTarea(int idTarea) throws SQLException {
        return usuarioGrupoRepository.findAlumnosByTarea(idTarea);
    }

    public List<AlumnoEvidenciaDTO> getAlumnosConTareaCompletada(int idTarea, int idGrupo) throws SQLException {
        return tareaRepository.findAlumnosConTareaCompletada(idTarea, idGrupo);
    }

    public List<AlumnoNoEvidenciaDTO> getAlumnosQueNoEntregaron(int idTarea, int idGrupo) throws SQLException {
        return tareaRepository.findAlumnosQueNoEntregaron(idTarea, idGrupo);
    }

    public List<TareaAlumnoDTO> getTareasCompletadasPorAlumno(int idUsuario, int idGrupo) throws SQLException {
        return tareaRepository.findTareasCompletadasPorAlumno(idUsuario, idGrupo);
    }

    public List<TareaAlumnoDTO> getTareasPendientesPorAlumno(int idUsuario, int idGrupo) throws SQLException {
        return tareaRepository.findTareasPendientesPorAlumno(idUsuario, idGrupo);
    }

}
